package ar.com.billing.workstation.model;

/**
 * Created by AJMILD1 on 03/06/14.
 */
public class Fabric  implements Cloneable {
    
    private Long id;
    private String code;
    private String shortname;
    private boolean mercerizado;
    private boolean puno;
    private boolean cuello;
    private String comments;

    public Fabric(Long id){
        this.id=id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    
    public boolean isMercerizado() {
        return mercerizado;
    }

    public void setMercerizado(boolean mercerizado) {
        this.mercerizado = mercerizado;
    }

    

    public boolean isPuno() {
        return puno;
    }

    public void setPuno(boolean puno) {
        this.puno = puno;
    }

    public boolean isCuello() {
        return cuello;
    }

    public void setCuello(boolean cuello) {
        this.cuello = cuello;
    }

    public boolean isMainFabric(){
        return !isCuello() && !isPuno();
    }
    public boolean isComplementary(){
        return isCuello() || isPuno();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public Fabric clone(){
        Fabric fabric = new Fabric(id);
        fabric.setCode(code);
        fabric.setComments(comments);
        fabric.setCuello(cuello);
        fabric.setMercerizado(mercerizado);
        fabric.setShortname(shortname);
        fabric.setPuno(puno);
        return fabric;
    }
    
    @Override
    public String toString(){
        return this.code + " " +  this.shortname;
    }

    public Long getId() {
       return this.id;
    }
}
