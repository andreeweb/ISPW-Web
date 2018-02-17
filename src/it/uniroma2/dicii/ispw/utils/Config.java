package it.uniroma2.dicii.ispw.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Simple class used to manage the Config.properties file.
 *
 * @author  Andrea Cerra
 */
public class Config {

    private static Config instance = null;
    private Properties properties;

    protected Config() {

    }

    /**
     * Return or inizialize the Config singleton istance
     *
     * @return reference to singleton istance
     */
    public synchronized static final Config getSingletonInstance(){

        if (Config.instance == null){
            Config.instance = new Config();
            instance.initConfig();
        }

        return instance;
    }

    /**
     * Init config file
     */
    private void initConfig(){

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream resourceStream = loader.getResourceAsStream("config.properties");
        properties = new Properties();

        try {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Get property value form config file
     *
     * @param key property
     * @return Property as a String
     */
    public String getProperty(String key){
        return properties.getProperty(key);
    }

}
