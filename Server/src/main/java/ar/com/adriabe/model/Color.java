package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.constant.COLOR_TYPE;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;



@Entity
@Table(name = "colors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Color extends AuditableDomainObject {
    
    private static final long serialVersionUID = 8609609585743951206L;
    private String name;
    private String method;
    @Column(unique=true)
    private String code;
    @Column(unique=true)
    private String coordinate;
    private String comments;

    @Enumerated(EnumType.ORDINAL)
    private COLOR_TYPE type;

    /**
     * Method 'Check'
     * 
     */
    public Color() {
        setId(0l);
    }
    
    public Color(Long idColor) {
    	setId(idColor);
    }
    
    public Color(Long idColor,int num) {
    	setId(idColor);
    	setType(num);
    }
    public Color(Long i,COLOR_TYPE blanco,String color) {
    	setId(i);
    	setType(blanco);
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
    public void setType(COLOR_TYPE type) {
        this.type = type;
    }

    public void setType(int num) {
        this.type = COLOR_TYPE.getColorType(num);
    }
    @JsonIgnore
    public COLOR_TYPE getType() {
        return type;
    }

    @Transient
    public String getColorType() {
        if(type==null){
            return "";
        }
        return type.getLabel();
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", method='" + method + '\'' +
                ", code='" + code + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", comments='" + comments + '\'' +
                ", type=" + type +
                '}';
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

    public String toJSONString() {
        return toString();
    }
}
