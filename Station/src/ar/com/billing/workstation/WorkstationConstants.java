/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation;

/**
 *
 * @author Mildo
 */
public class WorkstationConstants {
    
    public static final String warehouse = "Adriabe";
    public static final boolean DEBUG = true; 
    public static final String DEBUG_PROPERTIES_FILE = "/Users/Mildo/config.properties";
    public static final String PROPERTIES_FILE = "c:\\workstation\\config.properties";
    
    
    //properties keys
    public static final String PRINT_PORT = "print_port";
    public static final String SERVER_PORT = "server_port";
    public static final String APP_NAME = "app_name";
    public static final String APP_URL = "app_url";
    public static final String HAS_PRINTER = "has_printer";
    
    //Logger
    //public static final String LOGGER_FILE = "c:\\workstation\\KendoWorkstationLogger.mildo";
    public static final String LOGGER_FILE = "/Users/Mildo/KendoWorkstationLogger.mildo";
    
    public static final String LOGGER_FILE_NAME="KendoWorkstationLogger.mildo";
    
    //SEM
    public static final String SEMAPHORE_FILE = "KendoWorkstationSEM.mildo";
    public static int BARCODE_LENGTH = 16;
    
}
