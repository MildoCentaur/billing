package ar.com.adriabe.model.constant;

public enum IVA_TYPE {
	RI("Responsable inscripto","RI","21%"),
    SNC("Consumidor final", "SNC", "21%"),
    EXT("Extranjero","EXT","21%"),
	EXE("Exento","EXE","0%"),
	MNT("Monotributista","MNT","21%");
	
	private final String label;   // in kilograms
    private final String value; // in meters
    private final String percent;
    IVA_TYPE(String l, String v, String p) {
        this.label = l;
        this.value = v;
        this.percent = p;
    }
    
    public static String getLabelFromValue(String v) {
    	
    	if("RI".compareTo(v)==0) {
    		return RI.label;
    	}else if("SNC".compareTo(v)==0) {
    		return SNC.label;
    	}else if("EXT".compareTo(v)==0) {
    		return EXT.label;
    	}else if("EXE".compareTo(v)==0) {
    		return EXE.label;
    	}
    		
    	return MNT.label;
    }

    public static IVA_TYPE getType(String v) {
        if ("RI".compareTo(v) == 0) {
            return RI;
        } else if ("SNC".compareTo(v) == 0) {
            return SNC;
        } else if ("EXT".compareTo(v) == 0) {
            return EXT;
        } else if ("EXE".compareTo(v) == 0) {
            return EXE;
        }

        return MNT;
    }

    public static String getTypeFromLabel(String result) {
        if (result.compareTo(RI.label) == 0) {
            return RI.value;
        } else if (result.compareTo(SNC.label) == 0) {
            return SNC.value;
        } else if (result.compareTo(EXT.label) == 0) {
            return EXT.value;
        } else if (result.compareTo(EXE.label) == 0) {
            return EXE.value;
        }

        return MNT.value;

	}

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the percent
     */
    public String getPercent() {
        return percent;
    }
}
