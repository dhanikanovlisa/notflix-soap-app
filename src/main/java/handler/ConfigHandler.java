package handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHandler {
    private Properties prop;

    public ConfigHandler(String filename) {
        this.prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
            this.prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String get(String key) {
        return this.prop.getProperty(key);
    }

    public static void main(String[] args) {
        ConfigHandler ph = new ConfigHandler("config.email");
        System.out.println(ph.get("mail.smtp.password"));
    }
}