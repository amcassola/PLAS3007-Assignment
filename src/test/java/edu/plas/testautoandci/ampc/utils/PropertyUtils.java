package edu.plas.testautoandci.ampc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 25/11/2015
 */
public class PropertyUtils {
    private static Properties properties;

    private static void loadProperties() {
        String filename = System.getProperty("propertyFile");
        if (filename == null){
            throw new RuntimeException("Property file location was not specified. System property [propertyFile] is expected to be found with location of property file as its value.");
        }

        try {
            properties.load(new FileInputStream(filename));
        } catch (IOException ioe){
            throw new RuntimeException("Failed to load properties from file [" + filename + "]", ioe);
        }
    }

    public static Properties getProperties() {
        if (properties == null){
            properties = new Properties();
            loadProperties();
        }
        return properties;
    }

    public static String getProperty(String key){
        return getProperties().getProperty(key);
    }

    public static int getPropertyAsInt(String key){
        String value = getProperty(key);
        if (value == null){
            throw new RuntimeException("Failed to retrieve property with key " + key + ". Property not found.");
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe){
            throw new RuntimeException("Failed to convert property value for property with key " + key + ". Value found: " + value + ", expected integer");
        }
    }

    public static String getLoginUserName(){
        return getProperty("evernote.username");
    }

    public static String getLoginPassword(){
        return getProperty("evernote.password");
    }
}

