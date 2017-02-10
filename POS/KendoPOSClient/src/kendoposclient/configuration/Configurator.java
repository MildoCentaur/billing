/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kendoposclient.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import kendoposclient.KendoClientConstants;

/**
 *
 * @author Adriabe
 */
public class Configurator {

    private static Configurator config = null;
    private Properties prop;

    private Configurator() throws KendoPOSException {
        prop = loadProperties();
    }

    public static Configurator getInstance() throws KendoPOSException {
        if (config == null) {
            config = new Configurator();
        }
        return config;
    }

    
    private Properties loadProperties() throws KendoPOSException {
        prop = new Properties();
        try {
            //load a properties file
            //load a properties file
            String filename = KendoClientConstants.DEBUG ? KendoClientConstants.PROPERTIES_FILE_DEBUG : KendoClientConstants.PROPERTIES_FILE;
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, filename);
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
            
            
            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new KendoPOSException("Error al cargar las propiedades de sistema.");
            //throw new KendoPOSException(KendoClientConstants.PROP_FILE_NOT_FOUND, ex);
        }
    }
    
    public Object findProperty(String propName){
        return prop.getProperty(propName);
    }
}
