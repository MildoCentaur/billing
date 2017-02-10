package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.common.IDeleteable;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "client_contacts")
public class ClientContact extends AuditableDomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1254562591605942803L;
	protected String name;
	protected String nickname;
	protected String phone;
	protected String nextel;
	protected String email;
    @Column(name="office")
	private String office;


	public ClientContact() {
	}
	
	public void setId(Long id){
		super.setId(id);
	}
	public String getName() {
		return name;
	}
	public void setName(String nombre) {
		this.name = nombre;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String apodo) {
		this.nickname = apodo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String telefono) {
		this.phone = telefono;
	}
	public String getNextel() {
		return nextel;
	}
	public void setNextel(String nextel) {
		this.nextel = nextel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String position) {
		this.office = position;
	}

}
