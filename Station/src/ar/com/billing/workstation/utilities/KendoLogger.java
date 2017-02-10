/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.utilities;

import ar.com.billing.workstation.WorkstationConstants;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Adriabe
 */
public class KendoLogger {
   
  static private FileHandler fileTxt;
  static private SimpleFormatter formatterTxt;

  
  static public void setup() throws IOException {

    // Get the global logger to configure it
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    logger.setLevel(Level.ALL);
    try{
        fileTxt = new FileHandler(WorkstationConstants.LOGGER_FILE);
    }catch(Exception e){
        fileTxt = new FileHandler(WorkstationConstants.LOGGER_FILE_NAME);
    }
    // create txt Formatter
    formatterTxt = new SimpleFormatter();
    fileTxt.setFormatter(formatterTxt);
    logger.addHandler(fileTxt);

  }
}
