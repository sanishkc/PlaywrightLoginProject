package com.playwright.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    // Properties object to hold key-value pairs from config.properties
    private static Properties properties = new Properties();

    /**
     * Static block executes once when the class is loaded
     * It loads config/config.properties from the classpath
     */
    static {
        try {
            // Load config.properties file from resources folder
            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config/config.properties");

            // Load all properties into the Properties object
            properties.load(input);

        } catch (Exception e) {
            // Fail fast if configuration file cannot be loaded
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    /**
     * Reads a value from config.properties
     *
     * Example usage:
     * ConfigReader.get("app.url")
     *
     * @param key property key
     * @return property value
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }

    /**
     * Reads a value from Environment Variables
     * Used mainly for sensitive data like username/password
     * (e.g., GitHub Actions Secrets)
     *
     * Example usage:
     * ConfigReader.getEnv("APP_USERNAME")
     *
     * @param key environment variable name
     * @return environment variable value
     */
    public static String getEnv(String key) {

        // Fetch value from system environment variables
        String value = System.getenv(key);

        // Fail execution if environment variable is missing
        if (value == null) {
            throw new RuntimeException(
                "Environment variable not set: " + key
            );
        }

        return value;
    }

}
