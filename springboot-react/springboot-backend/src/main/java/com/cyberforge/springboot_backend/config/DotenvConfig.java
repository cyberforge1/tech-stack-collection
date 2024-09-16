// Filepath: /Users/softdev/Desktop/github-projects/springboot-react/springboot-backend/src/main/java/com/cyberforge/springboot_backend/config/DotenvConfig.java

package com.cyberforge.springboot_backend.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                     .directory("/Users/softdev/Desktop/github-projects/springboot-react/springboot-backend")
                     .ignoreIfMalformed()
                     .ignoreIfMissing()
                     .load();
    }
}
