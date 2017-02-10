package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "supplier_contacts")
public class SupplierContact extends AuditableDomainObject {
	/**
	 *
	 */
	private static final long serialVersionUID = 1254562591605942803L;
	protected String name;
	protected String lastname;
	protected String nickname;
	protected String phone;
	protected String nextel;
	protected String email;
	private String position;

	/**
	 * Method 'ClientContacto'
	 *
	 */
	public SupplierContact() {
	}
	
	public void setId(Long id){
		super.setId(id);
	}
	/**
	 * Method 'getNombre'
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method 'setNombre'
	 * 
	 * @param nombre
	 */
	public void setName(String nombre) {
		this.name = nombre;
	}

	/**
	 * Method 'getApellido'
	 * 
	 * @return String
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Method 'setApellido'
	 * 
	 * @param apellido
	 */
	public void setLastname(String apellido) {
		this.lastname = apellido;
	}

	/**
	 * Method 'getApodo'
	 * 
	 * @return String
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Method 'setApodo'
	 * 
	 * @param apodo
	 */
	public void setNickname(String apodo) {
		this.nickname = apodo;
	}

	/**
	 * Method 'getTelefono'
	 * 
	 * @return String
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Method 'setTelefono'
	 * 
	 * @param telefono
	 */
	public void setPhone(String telefono) {
		this.phone = telefono;
	}

	/**
	 * Method 'getNextel'
	 * 
	 * @return String
	 */
	public String getNextel() {
		return nextel;
	}

	/**
	 * Method 'setNextel'
	 * 
	 * @param nextel
	 */
	public void setNextel(String nextel) {
		this.nextel = nextel;
	}

	/**
	 * Method 'getEmail'
	 * 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method 'setEmail'
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
