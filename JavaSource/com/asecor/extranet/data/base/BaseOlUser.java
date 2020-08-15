package com.asecor.extranet.data.base;

import java.io.Serializable;
import java.util.Date;


/**
 * This is an object that contains data related to the MUsuarioWeb table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="OlUser"
 */

public abstract class BaseOlUser  implements Serializable {

	public static String REF = "OlUser";
	//public static String PROP_UDER_NAME = "userName";
	public static String PROP_PASSWORD = "password";
	public static String PROP_NAME = "nombres";
	public static String PROP_SURNAME = "surname";
	public static String PROP_DOCUMENT_TYPE_ID = "tipoDocumento";
	public static String PROP_DOCUMENT_NUMBER = "dni";
	public static String PROP_EMAIL = "email";
	public static String PROP_FHONE_NUMBER = "telefono"; 
	public static String PROP_ACTIVE = "active";
	public static String PROP_PASSWORD_CHANGE = "passwordChange";
	public static String PROP_FAILED_LOGONS = "failedLogons";
	public static String PROP_LAST_FAILED_LOGONS = "lastFailedLogon";
	public static String PROP_LAST_LOGIN = "lastLogin";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_UPDATE_USER = "updateUser";
	public static String PROP_DELETE_DATE = "deleteDate";
	public static String PROP_DELETE_USER = "deleteUser";
	public static String PROP_PIN = "pin";
	public static String PROP_CONFIRMED = "confirmed";
	public static String PROP_NACIMIENTO_DATE = "fechaNacimiento";
	public static String PROP_ID = "id";
	public static String PROP_CREADOR_ID = "creadorId"; 

	// constructors
	public BaseOlUser () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseOlUser (int id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {} 

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields 
	//private String userName;
	private String password;
	private String nombres;
	//private String surname;
	private Integer tipoDocumento;
	private Long dni;
	private String email;
	private String telefono;
	private Boolean active;
	private Boolean passwordChange;
	private Integer failedLogons;
	private Date lastFailedLogon;
	private Date lastLogin;
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;
	private Date deleteDate;
	private String deleteUser;
	private String pin;
	private Boolean confirmed;
	private Date fechaNacimiento;

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="id"
     */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId(Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}
 

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String name) {
		this.nombres = name;
	}

/*	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
*/
	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Integer documentTypeId) {
		this.tipoDocumento = documentTypeId;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long documentNumber) {
		this.dni = documentNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getPasswordChange() {
		return passwordChange;
	}

	public void setPasswordChange(Boolean passwordChange) {
		this.passwordChange = passwordChange;
	}

	public Integer getFailedLogons() {
		return failedLogons;
	}

	public void setFailedLogons(Integer failedLogons) {
		this.failedLogons = failedLogons;
	}

	public Date getLastFailedLogon() {
		return lastFailedLogon;
	}

	public void setLastFailedLogon(Date lastFailedLogon) {
		this.lastFailedLogon = lastFailedLogon;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.asecor.extranet.data.TitularWeb)) return false;
		else {
			com.asecor.extranet.data.TitularWeb olUser = (com.asecor.extranet.data.TitularWeb) obj;
			return (this.getId() == olUser.getId());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getId();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}