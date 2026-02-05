package config;

import java.io.InputStream;
import java.util.Properties;

public class EnvConfig {
    private static final Properties PROPS = new Properties();

    static {
        String path = "env/" + RunConfig.env() + ".properties";
        try (InputStream is = EnvConfig.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) throw new RuntimeException("Missing config: " + path);
            PROPS.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load env config", e);
        }
    }

    public static String get(String key) {
        String override = System.getProperty(key); // Jenkins override -Dusername=...
        if (override != null && !override.isBlank()) return override;
        return PROPS.getProperty(key);
    }
}
