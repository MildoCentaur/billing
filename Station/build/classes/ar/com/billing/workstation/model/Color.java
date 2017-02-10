package ar.com.billing.workstation.model;


import ar.com.billing.workstation.model.constant.COLOR_TYPE;


public class Color  implements Cloneable{
    
    private static final long serialVersionUID = 8609609585743951206L;
    private String name;
    private String method;
    private String code;
    private String coordinate;
    private String comments;
    private COLOR_TYPE type;
    private Long id;
    /**
     * Method 'Check'
     * 
     */
    public Color() {
        this.id=0l;
    }
    
    public Color(Long idColor) {
        this.id=idColor;
    }
    
    public Color(Long id,COLOR_TYPE blanco,String color) {
    	this.id=id;
    	setType(blanco.getLabel());
    	this.name=color;
    }

    public String getMethod() {
            return method;
    }
    public void setMethod(String method) {
            this.method = method;
    }
    public String getCode() {
            return code;
    }
    public void setCode(String code) {
            if (code==null || code.isEmpty()) {code="000";}
            else if(code.length()==1){code="00"+code;}
            else if(code.length()==2){code="0"+code;}
            else this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public void setType(String colorLabel) {
        this.type = COLOR_TYPE.getColorLabel(colorLabel);
    }
    public COLOR_TYPE getType() {
        return type;
    }
    public void setType(COLOR_TYPE type) {
        this.type=type;
    }
    
    public String getColorType() {
        return type.getLabel();
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public Color clone(){
        Color color = new Color(id);
        color.setCode(code);
        color.setComments(comments);
        color.setCoordinate(coordinate);
        color.setMethod(method);
        color.setName(name);
        color.setType(type);
        return color;
    }
    
    @Override
    public String toString(){
        return this.name;
    }

    public Long getId() {
        return this.id;
    }
}
