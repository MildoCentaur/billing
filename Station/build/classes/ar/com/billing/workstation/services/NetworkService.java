/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.HTTPTokener;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Mildo
 */
public class NetworkService {
    
     public static JSONObject doPost(String urlString, JSONObject params) throws Exception {
        Object obj = null;
        JSONObject data =  null;
        try {

            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            DataOutputStream printout = new DataOutputStream(con.getOutputStream());
            // This is the POST 
            String key;
            Object param;
            for(Object keyObj : params.keySet()){
                key=(String) keyObj;
                param = params.get(key);
                
                printout.writeBytes(key + "="+param +"&");
            }
            printout.flush();
            printout.close();
            DataInputStream input = new DataInputStream(con.getInputStream());
            
            //Read the response
            obj = new JSONTokener(con.getInputStream()).nextValue();
            if(obj instanceof JSONObject){
                data = (JSONObject) obj;
            }else{
                throw new Exception("Error de Comunicación.");
            }
            
            input.close();
            con.disconnect();
            if (data.getBoolean("hasErrors")){
                JSONArray array = data.getJSONArray("errorListDetailList");
                Logger.getLogger(NetworkService.class.getName()).log(Level.INFO, (String)array.get(0));
                throw new Exception("Error de Comunicación.");
            }
            
            JSONObject result = data.optJSONObject("ajaxResponse");
            if (result == null){
                result = new JSONObject();
                result.put("result", data.optBoolean("ajaxResponse")) ;
            }
            return result;
        } catch (Exception ex) {
            Logger.getLogger(NetworkService.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error de comunicación con el servidor.");
        }
        
    }
    
    public static JSONObject doGet(String urlString, JSONObject params) throws Exception {
        JSONObject data =  null;
        Object obj = null;
        try {
            String par = ""; 
            String key ;
            if(params != null){
                par = (urlString.contains("?"))? "&":"?";
                for (Object aux : params.keySet()){
                    key = (String) aux;
                    par = par + key + "=" + params.getString(key);
                    par = par + "&";
                }
                    
            }
                    
            URL url = new URL(urlString + par);
            
            URLConnection con = (HttpURLConnection) url.openConnection();
            
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "text/xml");

            
            //Read the response
            obj = new JSONTokener(con.getInputStream()).nextValue();
            if(obj instanceof JSONObject)
                data = (JSONObject) obj;
            else if(obj instanceof String){
                data = new JSONObject();
                data.put("string", obj);
            }else{
                data = new JSONObject();
                data.put("array", obj);
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(NetworkService.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error de comunicación con el servidor.");
        }
        return data;
    }
}
