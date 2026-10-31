package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final Properties props = new Properties();

    static {
        try (FileInputStream in = new FileInputStream("config.properties")) {
            props.load(in);
        } catch (IOException e) {
            // file missing: fall back to environment variables
        }
    }

    public static String email() {
        return get("LINKEDIN_EMAIL");
    }

    public static String password() {
        return get("LINKEDIN_PASSWORD");
    }

    private static String get(String key) {
        String value = props.getProperty(key);
        if (value == null || value.isBlank()) {
            value = System.getenv(key);
        }
        return value == null ? null : value.trim();
    }
}