package ar.com.adriabe.model;

import ar.com.adriabe.model.common.Accountable;
import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.constant.IVA_TYPE;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity
@Table(name = "clients", uniqueConstraints=@UniqueConstraint(columnNames={"nickname", "name"}))
@JsonRootName("client")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client extends AuditableDomainObject implements Accountable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1002944396854078160L;

	private String name;
	private String addressDelivery;
    private String address;
	private String localidad;
	private String state;
	private String country;
    private Double credit =0.0;
    private Double creditInCheques =0.0;
	private String postalCode;
	private String nickname;
	private String email;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
	private IVA_TYPE IVACondition;

	private String cuit;
	private String phone;
	private String fax;
	private String nextel;
	private Double balanceActivity=0.0;
	private Double balanceBilling=0.0;
    private int clientType;
    private String comments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@OrderBy("id")
	private List<ClientContact> contacts;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@OrderBy("id")
	private List<Store> stores;

	/**
	 * Method 'Client'
	 * 
	 */
	public Client() {
        setId(0l);
        this.localidad="Buenos Aires";
        this.country="Argentina";
        this.state="Buenos Aires";
	}

	public Client(Long idCliente) {
		setId(idCliente);
	}

    public Double getCreditInCheques() {
        return creditInCheques;
    }

    public void setCreditInCheques(Double creditCheques) {
        this.creditInCheques = creditCheques;
    }

    /**
	 * Method 'getName'
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	public void setId(Long id){
		super.setId(id);
	}
	/**
	 * Method 'setName'
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method 'getAddress'
	 * 
	 * @return String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Method 'setAddress'
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Method 'getLocalidad'
	 * 
	 * @return String
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * Method 'setLocalidad'
	 * 
	 * @param localidad
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * Method 'getProvincia'
	 * 
	 * @return String
	 */
	public String getState() {
		return state;
	}

	/**
	 * Method 'setProvincia'
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Method 'getCountry'
	 * 
	 * @return String
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Method 'setCountry'
	 * 
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Method 'getCredit'
	 * 
	 * @return integer credit
	 */
	public Double getCredit() {
		return credit;
	}

	/**
	 * Method 'setCredit'
	 * 
	 * @param credit
	 */
	public void setCredit(Double credit) {
		this.credit = credit;
	}


	/**
	 * Method 'getCodigoPostal'
	 * 
	 * @return String
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Method 'setCodigoPostal'
	 * 
	 * @param postalCode
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	 * Method 'setNickname'
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	/**
	 * Method 'getCondicionIVA'
	 * 
	 * @return String
	 */
	public IVA_TYPE getIVACondition() {
		return IVACondition;
	}

	/**
	 * Method 'setCondicionIVA'
	 * 
	 * @param condicionIVA
	 */
	public void setIVACondition(IVA_TYPE condicionIVA) {
		this.IVACondition = condicionIVA;
	}

	/**
	 * Method 'getCuit'
	 * 
	 * @return String
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Method 'setCuit'
	 * 
	 * @param cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
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
	 * Method 'getFax'
	 * 
	 * @return String
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * Method 'setFax'
	 * 
	 * @param fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
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
	 * @return the contacts
	 */
	public List<ClientContact> getContacts() {
		return contacts;
	}

	/**
	 * @param contactos
	 *            the contacts to set
	 */
	public void setContacts(List<ClientContact> contactos) {
		this.contacts = contactos;
	}


	public void addContact(ClientContact cc) {
		contacts.add(cc);
	}
	/**
	 * @return the saldo
	 */
	public Double getBalanceActivity() {
		return balanceActivity;
	}

	/**
	 * @param saldo
	 *            the saldo to set
	 */
	public void setBalanceActivity(Double saldo) {
		this.balanceActivity = saldo;
	}

    @Transient
	public String getFullName() {
		if (nickname!=null && !nickname.isEmpty())
			return name + " (" + nickname + ")";
		
		return name;
	}
	
	@Transient
	@XmlTransient
    @JsonIgnore
    public String getFullAddress(){
		return address + ", " + localidad + ", " + country;
	}

    @Transient
    @XmlTransient
    @JsonIgnore
    public String getIVAConditionLabel(){
        return IVACondition.getLabel();
    }


    @XmlTransient
    @JsonIgnore
    public Double getBalanceBilling() {
		
		return balanceBilling;
	}

    @XmlTransient
    @JsonIgnore
	public void setBalanceBilling(Double balanceBilling) {
		if(balanceBilling==null) {
			balanceBilling=0.0;
		}
		this.balanceBilling = balanceBilling;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}


    /**
     * Interface IAccountable Method's
     */

    @Override
    public Double getAccountableBalance() {
        return balanceBilling;
    }

    @Override
    public Double getAccountableActivityBalance() {
        return balanceActivity;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public String getAddressDelivery() {
        return addressDelivery;
    }

    public void setAddressDelivery(String addressDelivery) {
        this.addressDelivery = addressDelivery;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", addressDelivery='" + addressDelivery + '\'' +
                ", address='" + address + '\'' +
                ", localidad='" + localidad + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", credit=" + credit +
                ", creditInCheques=" + creditInCheques +
                ", postalCode='" + postalCode + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", IVACondition=" + IVACondition +
                ", cuit='" + cuit + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", nextel='" + nextel + '\'' +
                ", balanceActivity=" + balanceActivity +
                ", balanceBilling=" + balanceBilling +
                ", clientType=" + clientType +
                ", comments='" + comments + '\'' +
                ", contacts=" + contacts +
                ", stores=" + stores +
                '}';
    }
}
