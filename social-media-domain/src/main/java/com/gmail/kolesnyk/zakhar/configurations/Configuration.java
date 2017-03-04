package com.gmail.kolesnyk.zakhar.configurations;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Configuration {
    private final String PATH_TO_PROPERTY = "social-media-domain/configurations/main.cfg.properties";
//    private final String PATH_TO_LOCALIZED_PROPERTY = "resources/uiInterface";
    private Properties properties;
    private ResourceBundle resourceBundle;

    public Configuration(/*Locale locale*/) {
//        resourceBundle = ResourceBundle.getBundle(PATH_TO_LOCALIZED_PROPERTY, locale);

        try {
            properties = new Properties();
            properties.load(new FileInputStream(PATH_TO_PROPERTY));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }

//    public ResourceBundle getResourceBundle() {
//        return resourceBundle;
//    }

    public void saveProperty() {
        try {
            properties.store(new FileOutputStream(PATH_TO_PROPERTY), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public ResourceBundle changeLocale(Locale locale){
//        return resourceBundle=ResourceBundle.getBundle(PATH_TO_LOCALIZED_PROPERTY, locale);
//    }
}
