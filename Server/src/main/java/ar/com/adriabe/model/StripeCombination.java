package ar.com.adriabe.model;

import ar.com.adriabe.model.common.DomainObject;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Mildo on 9/26/14.
 */
@Entity
@Table(name = "stripe_combination")
@JsonRootName("combination")
public class StripeCombination extends DomainObject {

    private String colorFingerA;
    private String colorFingerB;
    private String colorFingerC;
    private String colorFingerD;
    private String colorFingerE;
    private String colorFingerF;
    private String name;


    public StripeCombination() {
        setId(0l);
    }

    public StripeCombination(Long id) {
        setId(id);
    }

    @JsonProperty("ColorFingerF")
    public String getColorFingerF() {
        return colorFingerF;
    }

    public void setColorFingerF(String colorFingerF) {
        this.colorFingerF = colorFingerF;
    }

    @JsonProperty("ColorFingerE")
    public String getColorFingerE() {
        return colorFingerE;
    }

    public void setColorFingerE(String colorFingerE) {
        this.colorFingerE = colorFingerE;
    }

    @JsonProperty("ColorFingerD")
    public String getColorFingerD() {
        return colorFingerD;
    }

    public void setColorFingerD(String colorFingerD) {
        this.colorFingerD = colorFingerD;
    }

    @JsonProperty("ColorFingerC")
    public String getColorFingerC() {
        return colorFingerC;
    }

    public void setColorFingerC(String colorFingerC) {
        this.colorFingerC = colorFingerC;
    }

    @JsonProperty("ColorFingerB")
    public String getColorFingerB() {
        return colorFingerB;
    }

    public void setColorFingerB(String colorFingerB) {
        this.colorFingerB = colorFingerB;
    }

    @JsonProperty("ColorFingerA")
    public String getColorFingerA() {
        return colorFingerA;
    }

    public void setColorFingerA(String colorFingerA) {
        this.colorFingerA = colorFingerA;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "{" +
                "id= " + getId() +
                ", colorFingerA='" + colorFingerA + '\'' +
                ", colorFingerB='" + colorFingerB + '\'' +
                ", colorFingerC='" + colorFingerC + '\'' +
                ", colorFingerD='" + colorFingerD + '\'' +
                ", colorFingerE='" + colorFingerE + '\'' +
                ", colorFingerF='" + colorFingerF + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String toJSONString() {

        return toString();
    }
}
