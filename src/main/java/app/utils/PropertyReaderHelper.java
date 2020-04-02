package app.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReaderHelper {
    public String getProperty(String propertyName){
        String value = null;
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.property")){
            properties.load(inputStream);
            value = properties.getProperty(propertyName);
        } catch (IOException e) {
            System.out.println("Can't read properties file");
        }
        return value;
    }
}
