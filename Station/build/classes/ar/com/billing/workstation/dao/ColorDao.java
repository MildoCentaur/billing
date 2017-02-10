/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.dao;

import ar.com.billing.workstation.model.Color;
import ar.com.billing.workstation.utilities.EurekaStringUtilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mildo
 */
public class ColorDao {

    private static List<Color> colors = Collections.synchronizedList(new ArrayList<Color>());

    /**
     * @return the products
     */
    public static List<Color> getColors() {
        return colors;
    }

    public static void addColor(Color aColor) {
       if(findColorByName(aColor.getName())==null){
            colors.add(aColor);
       }
       
    }

    protected static Color findColorByName(String color, boolean clone) {
        synchronized (colors) {
            for (Color col : colors) {
                if (col.getName().equalsIgnoreCase(color)) {
                    if (clone) {
                        return col.clone();
                    } else {
                        return col;
                    }
                }
            }
        }
        return null;
    }

    public static Color findColorByName(String color) {
        return findColorByName(color, true);
    }

    public static Color findColorByCodeOrName(String text) {
        boolean exist = false;
        synchronized (colors) {
            for (Color col : colors) {
                exist = exist || col.getName().equalsIgnoreCase(text);
                exist = exist || col.getCode().equalsIgnoreCase(text);
                if(exist){
                    return col;
                }
                        
            }
        }
        return null;
    }
    
    public static boolean existColorByCodeOrName(String text) {
        return findColorByCodeOrName(text)!=null;
    }
}
