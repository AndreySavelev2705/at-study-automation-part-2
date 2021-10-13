package at.study.automation.property;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

public class Property {

    private static final String propertiesName = System.getProperty("properties", "default.properties");
    private static final Properties properties = new Properties();
    private static boolean isInitialized = false;

    @SneakyThrows
    private static void init() {
        properties.load(new FileInputStream("src/test/resources/" + propertiesName));
        isInitialized = true;
    }

    public static String getStringProperty(String key) {
        if (!isInitialized) {
            init();
        }
        return properties.getProperty(key);
    }
    public static Integer getIntegerProperty(String key) {
        return Integer.parseInt(getStringProperty(key));
    }
    public static Boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getStringProperty(key));
    }
}
