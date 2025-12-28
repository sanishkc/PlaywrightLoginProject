package com.playwright.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try {
            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config/config.properties");

            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    // Reads from config.properties
    public static String get(String key) {
        return properties.getProperty(key);
    }

    // Reads from environment variables (GitHub Secrets)
    public static String getEnv(String key) {
        String value = System.getenv(key);
        if (value == null) {
            throw new RuntimeException(
                "Environment variable not set: " + key
            );
        }
        return value;
    }

}
