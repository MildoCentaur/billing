/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.utilities;

import ar.com.billing.workstation.WorkStation;
import ar.com.billing.workstation.WorkstationConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adriabe
 */
public class Configurator {
    
    //properties keys
    public static final String PRINT_PORT = "print_port";
    public static final String SERVER_PORT = "server_port";
    public static final String APP_NAME = "app_name";
    public static final String APP_URL = "app_url";
    public static final String HAS_PRINTER = "has_printer";
    private static Configurator config = null;
    private Properties prop;

    private Configurator() throws Exception {
        prop = loadProperties();
    }

    public static Configurator getInstance() throws Exception {
        if (config == null) {
            config = new Configurator();
        }
        return config;
    }

    public Object findProperty(String propName){
        return prop.getProperty(propName);
    }
    private Properties loadProperties() throws Exception {
        prop = new Properties();
        try {
            //load a properties file
            String filename = (WorkstationConstants.DEBUG)? WorkstationConstants.DEBUG_PROPERTIES_FILE : WorkstationConstants.PROPERTIES_FILE;
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, filename);
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
            //WorkStation.class.getResourceAsStream(WorkstationConstants.PROPERTIES_FILE));        
            return prop;
        } catch (IOException ex) {
             Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, ex.getMessage());
             Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "no se pudo cargar el archivo de propiedades");
            throw new Exception(ex);
        }
    }
}
