// Load environment variables from .env file
require('dotenv').config();
const mysql = require('mysql2');

// Fetch environment variables
const MYSQL_HOST = process.env.MYSQL_HOST;
const MYSQL_USER = process.env.MYSQL_USER;
const MYSQL_PASSWORD = process.env.MYSQL_PASSWORD;
const MYSQL_DB = process.env.MYSQL_DB;
const MYSQL_PORT = process.env.MYSQL_PORT;

function testDbConnection() {
    try {
        // Ensure all environment variables are set
        if (!MYSQL_HOST || !MYSQL_USER || !MYSQL_PASSWORD || !MYSQL_DB) {
            throw new Error("One or more environment variables are not set. Please check your .env file.");
        }

        // Print environment variables to ensure they are loaded
        console.log(`MYSQL_HOST: ${MYSQL_HOST}`);
        console.log(`MYSQL_USER: ${MYSQL_USER}`);
        console.log(`MYSQL_PASSWORD: ${MYSQL_PASSWORD}`);
        console.log(`MYSQL_DB: ${MYSQL_DB}`);
        console.log(`MYSQL_PORT: ${MYSQL_PORT}`);

        // Create a connection to the MySQL database
        const connection = mysql.createConnection({
            host: MYSQL_HOST,
            user: MYSQL_USER,
            password: MYSQL_PASSWORD,
            database: MYSQL_DB,
            port: MYSQL_PORT
        });

        // Connect to the database
        connection.connect((err) => {
            if (err) {
                console.error('Error connecting to MySQL:', err);
                return;
            }
            console.log('Successfully connected to the database.');

            // Test simple query to check connection
            connection.query('SELECT 1', (error, results) => {
                if (error) {
                    console.error('Error executing test query:', error);
                } else {
                    console.log('Test query successful:', results);
                }

                // Execute a query to get the MySQL server version
                connection.query('SELECT VERSION()', (error, results) => {
                    if (error) {
                        console.error('Error fetching MySQL version:', error);
                    } else {
                        console.log(`MySQL Server Version: ${results[0]['VERSION()']}`);
                    }

                    // Execute a query to get the list of databases
                    connection.query('SHOW DATABASES', (error, databases) => {
                        if (error) {
                            console.error('Error fetching databases:', error);
                        } else {
                            console.log('Databases on the server:');
                            databases.forEach(db => {
                                console.log(`- ${db.Database}`);
                            });

                            // Close the connection
                            connection.end();
                        }
                    });
                });
            });
        });
    } catch (error) {
        console.error(error.message);
    }
}

// Run the test function
testDbConnection();
