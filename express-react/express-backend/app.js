require('dotenv').config(); // Load .env variables

const express = require('express');
const mysql = require('mysql2');
const app = express();
const PORT = 3000;
const cors = require('cors');

app.use(express.json()); // For parsing application/json
app.use(cors()); // Enable CORS for all routes

// MySQL Database connection using .env values
const db = mysql.createConnection({
    host: process.env.MYSQL_HOST,
    user: process.env.MYSQL_USER,
    password: process.env.MYSQL_PASSWORD,
    database: process.env.MYSQL_DB,
    port: process.env.MYSQL_PORT
});

db.connect((err) => {
    if (err) {
        console.error('Error connecting to MySQL:', err);
        return;
    }
    console.log('Connected to MySQL database');
});

// Root endpoint to handle GET requests to "/"
app.get('/', (req, res) => {
    res.json({ message: 'World!' });
});

// Test access endpoint
app.get('/helloworld', (req, res) => {
    res.json({ message: 'Hello, World!' });
});

// Fetch all todos from MySQL database
app.get('/todo', (req, res) => {
    const query = 'SELECT * FROM todos';
    db.query(query, (err, results) => {
        if (err) {
            return res.status(500).json({ error: 'Database query failed' });
        }
        res.json(results);
    });
});

// Fetch a single todo by ID from MySQL database
app.get('/todo/:id', (req, res) => {
    const query = 'SELECT * FROM todos WHERE id = ?';
    db.query(query, [req.params.id], (err, results) => {
        if (err) {
            return res.status(500).json({ error: 'Database query failed' });
        }
        if (results.length === 0) {
            return res.status(404).json({ error: 'Todo not found' });
        }
        res.json(results[0]);
    });
});

// Create a new todo in the MySQL database
app.post('/todo', (req, res) => {
    const { title } = req.body;
    if (!title) {
        return res.status(400).json({ error: 'Title is required' });
    }

    const query = 'INSERT INTO todos (title) VALUES (?)';
    db.query(query, [title], (err, results) => {
        if (err) {
            return res.status(500).json({ error: 'Database insertion failed' });
        }
        res.status(201).json({ id: results.insertId, title });
    });
});

// Update an existing todo by ID in the MySQL database
app.put('/todo/:id', (req, res) => {
    const { title } = req.body;
    if (!title) {
        return res.status(400).json({ error: 'Title is required' });
    }

    const query = 'UPDATE todos SET title = ? WHERE id = ?';
    db.query(query, [title, req.params.id], (err, results) => {
        if (err) {
            return res.status(500).json({ error: 'Database update failed' });
        }
        if (results.affectedRows === 0) {
            return res.status(404).json({ error: 'Todo not found' });
        }
        res.json({ id: req.params.id, title });
    });
});

// Delete a todo by ID from MySQL database
app.delete('/todo/:id', (req, res) => {
    const query = 'DELETE FROM todos WHERE id = ?';
    db.query(query, [req.params.id], (err, results) => {
        if (err) {
            return res.status(500).json({ error: 'Database deletion failed' });
        }
        if (results.affectedRows === 0) {
            return res.status(404).json({ error: 'Todo not found' });
        }
        res.json({ message: 'Todo deleted successfully' });
    });
});

// Start the server
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
