package ar.com.adriabe.model;

import ar.com.adriabe.model.common.DomainObject;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "banks")
public class Bank extends DomainObject {

    /**
     *
     */
    private static final long serialVersionUID = 5646069692144981142L;
    private String shortName;
    private String longName;
    private int code;


    public Bank() {
    }

    public Bank(int code, String name, String shortName) {
        super();
        this.code = code;
        this.shortName = shortName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }
}
