package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "fabrics")
@JsonRootName("fabric")
public class Fabric extends AuditableDomainObject {

    @Column(unique = true)
    private String code;
    private String shortname;
    private String longname;
    private int pasadas;
    private int mayas;
    private double cottonPercent;
    private double polyesterPercent;
    private double lycraPercent;
    private boolean mercerizado;

    private int galga;
    private int diametro;
    private double rinde;
    private double width;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fabric_id")
    @OrderBy("id")
    private List<Fiber> fibers;

    private boolean puno;
    private boolean cuello;
    private boolean tiras;

    private String comments;

    public Fabric() {
        setId(0l);
    }

    public Fabric(Long i) {
        setId(i);
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

    public String getLongname() {
        return longname;
    }

    public void setLongname(String longname) {
        this.longname = longname;
    }

    public int getPasadas() {
        return pasadas;
    }

    public void setPasadas(int pasadas) {
        this.pasadas = pasadas;
    }

    public int getMayas() {
        return mayas;
    }

    public void setMayas(int mayas) {
        this.mayas = mayas;
    }

    public double getCottonPercent() {
        return cottonPercent;
    }

    public void setCottonPercent(double cottonPercent) {
        this.cottonPercent = cottonPercent;
    }

    public double getPolyesterPercent() {
        return polyesterPercent;
    }

    public void setPolyesterPercent(double polyesterPercent) {
        this.polyesterPercent = polyesterPercent;
    }

    public double getLycraPercent() {
        return lycraPercent;
    }

    public void setLycraPercent(double lycraPercent) {
        this.lycraPercent = lycraPercent;
    }

    public boolean isMercerizado() {
        return mercerizado;
    }

    public void setMercerizado(boolean mercerizado) {
        this.mercerizado = mercerizado;
    }

    public int getGalga() {
        return galga;
    }

    public void setGalga(int galga) {
        this.galga = galga;
    }

    public double getRinde() {
        return rinde;
    }

    public void setRinde(double rinde) {
        this.rinde = rinde;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double ancho) {
        this.width = ancho;
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

    @Transient
    public boolean isMainFabric() {
        return !isCuello() && !isPuno() && !isTira();
    }

    @Transient
    public boolean isComplementary() {
        return isCuello() || isPuno() || isTira();
    }


    public int getFibras() {
        return fibers == null ? 0 : fibers.size();
    }

    public List<Fiber> getFibers() {
        return fibers;
    }

    public void setFibers(List<Fiber> fibers) {
        this.fibers = fibers;
    }

    public int getDiametro() {
        return diametro;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public void update(Fabric fabric) {

        this.shortname = fabric.getShortname();
        this.longname = fabric.getLongname();
        this.pasadas = fabric.getPasadas();
        this.mayas = fabric.getMayas();
        this.cottonPercent = fabric.getCottonPercent();
        this.polyesterPercent = fabric.getPolyesterPercent();
        this.lycraPercent = fabric.getLycraPercent();
        this.mercerizado = fabric.isMercerizado();
        this.galga = fabric.getGalga();
        this.diametro = fabric.getDiametro();
        this.rinde = fabric.getRinde();
        this.width = fabric.getWidth();

        this.fibers = fabric.getFibers();

        this.puno = fabric.isPuno();
        this.cuello = fabric.isCuello();
        this.tiras = fabric.isTira();
        this.comments = fabric.getComments();

    }

    public boolean isTira() {
        return tiras;
    }

    public void setTira(boolean tiras) {
        this.tiras = tiras;
    }
}
