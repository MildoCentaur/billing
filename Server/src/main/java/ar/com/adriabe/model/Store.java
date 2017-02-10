package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stores")
public class Store extends AuditableDomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3174243481567890642L;
	private String feria;
	private String puesto;
	private String pasillo;
	private String atiende;
	
	public Store() {
		
	}
	public Store(String feria, String puesto, String pasillo, String atiende) {
		super();
		this.feria = feria;
		this.puesto = puesto;
		this.pasillo = pasillo;
		this.atiende = atiende;
	}
	public String getAtiende() {
		return this.atiende;
	}
	public void setAtiende(String atiende) {
		this.atiende = atiende;
	}
	public String getPasillo() {
		return pasillo;
	}
	public void setPasillo(String pasillo) {
		this.pasillo = pasillo;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getFeria() {
		return feria;
	}
	public void setFeria(String feria) {
		this.feria = feria;
	}
}
