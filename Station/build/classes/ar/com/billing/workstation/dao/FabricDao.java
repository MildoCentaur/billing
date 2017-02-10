/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.dao;

import ar.com.billing.workstation.model.Color;
import ar.com.billing.workstation.model.Fabric;
import ar.com.billing.workstation.utilities.EurekaStringUtilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mildo
 */
public class FabricDao {

    private static List<Fabric> fabrics = Collections.synchronizedList(new ArrayList<Fabric>());

    /**
     * @return the products
     */
    public static List<Fabric> getFabrics() {
        return fabrics;
    }

    public static void addFabric(Fabric aFabric) {
       if(findFabricByName(aFabric.getShortname())==null){
            fabrics.add(aFabric);
       }
       
    }

    protected static Fabric findFabricByName(String fabric, boolean clone) {
        synchronized (fabrics) {
            for (Fabric fab : fabrics) {
                if (fab.getShortname().equalsIgnoreCase(fabric)) {
                    if (clone) {
                        return fab.clone();
                    } else {
                        return fab;
                    }
                }
            }
        }
        return null;
    }

    public static Fabric findFabricByName(String fabric) {
        return findFabricByName(fabric, true);
    }

    public static Fabric findFabricByCodeOrName(String text) {
        boolean exist = false;
        synchronized (fabrics) {
            for (Fabric fab : fabrics) {
                exist = exist || fab.getShortname().equalsIgnoreCase(text);
                exist = exist || fab.getCode().equalsIgnoreCase(text);
                if(exist){
                    return fab;
                }
                        
            }
        }
        return null;
    }
    
    public static boolean existFabricByCodeOrName(String text) {
        return findFabricByCodeOrName(text)!=null;
    }
}
