package ar.com.adriabe.model;

import ar.com.adriabe.model.common.DomainObject;
import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "fibers")
@JsonRootName("fiber")
public class Fiber extends DomainObject {
	/**
	 *
	 */
	private static final long serialVersionUID = 1254562591605942803L;
	private String titulo;
	private String supplierName;
    private double percentage;


	public Fiber() {
	}

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentge) {
        this.percentage = percentge;
    }


}
