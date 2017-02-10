/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.utilities;

/**
 *
 * @author alejandromildiner
 */
public class EurekaStringUtilities {
  
    
   public static boolean containsPattern(String s, String pattern){
    return s.toLowerCase().matches("(.*)"+pattern.toLowerCase()+"(.*)");
  }
}
