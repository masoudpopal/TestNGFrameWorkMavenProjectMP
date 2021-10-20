package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    // in order to use the config.properties data we need to read them, or we need some codes to read those data so that is
    //why we need to create another package by the name of UTILS and create a ConfigReader class to read it,as follows.
    static Properties prop;

    // this method will read any property file
    public static Properties readProperties(String filePath) {
        try{
            FileInputStream fis=new FileInputStream(filePath);
            //Properties prop= new Properties();
            prop=new Properties(); // here we say load everything from configFile inside the prop.
           prop.load(fis); // then close it
            fis.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return prop;

    }
    //this method retrieves single value based on specified key
    public static String getPropertyValue(String key){
        // in here all the properties are inside the prop object so when we say (get property) based on key that means
        // we want to get one specific property not all the properties.so instead of (key) you can type browser/url/username/password
        return prop.getProperty(key);
    }
}

