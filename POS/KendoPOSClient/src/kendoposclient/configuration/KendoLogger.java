package kendoposclient.configuration;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import kendoposclient.KendoClientConstants;

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
    String filename = KendoClientConstants.DEBUG ?  KendoClientConstants.LOGGER_FILE_DEBUG :KendoClientConstants.LOGGER_FILE;
    fileTxt = new FileHandler(filename);
  
    // create txt Formatter
    formatterTxt = new SimpleFormatter();
    fileTxt.setFormatter(formatterTxt);
    logger.addHandler(fileTxt);

  }
}
