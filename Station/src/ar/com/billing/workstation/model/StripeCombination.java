package ar.com.billing.workstation.model;


/**
 * Created by Mildo on 9/26/14.
 */

public class StripeCombination  {

    private Long id;
    private String colorFingerA;
    private String colorFingerB;
    private String colorFingerC;
    private String colorFingerD;
    private String colorFingerE;
    private String colorFingerF;
    private String name;

    
    public StripeCombination(){
    }

    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return this.id;
    }
    public String getColorFingerF() {
        return colorFingerF;
    }

    public void setColorFingerF(String colorFingerF) {
        this.colorFingerF = colorFingerF;
    }

    public String getColorFingerE() {
        return colorFingerE;
    }

    public void setColorFingerE(String colorFingerE) {
        this.colorFingerE = colorFingerE;
    }

    public String getColorFingerD() {
        return colorFingerD;
    }

    public void setColorFingerD(String colorFingerD) {
        this.colorFingerD = colorFingerD;
    }

    public String getColorFingerC() {
        return colorFingerC;
    }

    public void setColorFingerC(String colorFingerC) {
        this.colorFingerC = colorFingerC;
    }

    public String getColorFingerB() {
        return colorFingerB;
    }

    public void setColorFingerB(String colorFingerB) {
        this.colorFingerB = colorFingerB;
    }

    public String getColorFingerA() {
        return colorFingerA;
    }

    public void setColorFingerA(String colorFingerA) {
        this.colorFingerA = colorFingerA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
