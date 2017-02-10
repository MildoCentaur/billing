/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kendoposclient;

/**
 *
 * @author Adriabe
 */
public class KendoClientConstants {
    
    public static final boolean DEBUG = false;
    public static final String PROPERTIES_FILE ="c:\\POSClient\\config.properties";
    public static final String PROPERTIES_FILE_DEBUG ="/Users/Mildo/POSStation/config.properties";
    public static final String DLL_DIR ="c:\\POSClient";
    
    
    //properties keys
    public static final String PRINT_PORT = "print_port";
    public static final String SERVER_PORT = "server_port";
    public static final String APP_NAME = "app_name";
    public static final String APP_URL = "app_url";
    public static final String HAS_PRINTER = "has_printer";
    
    //Logger
    public static final String LOGGER_FILE = "c:\\POSClient\\KendoPOSStationLogger.log";
    public static final String LOGGER_FILE_DEBUG ="/Users/Mildo/POSStation/KendoPOSStationLogger.log";
    
    
    //Error code
    public static final int PROP_FILE_NOT_FOUND = 0;
    public static final int PROP_NOT_FOUND = 1;
    public static final int SERVER_PORT_ERROR = 2;
    public static final int SERVER_ERROR = 3;
    public static final int PRINT_PORT_ERROR = 4;
    public static final int PRINTER_ERROR = 5;
    public static final int LOGGER_ERROR = 5;
    public static final int BROWSER_ERROR = 6;
    
    
    
}
