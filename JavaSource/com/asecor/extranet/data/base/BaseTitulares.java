package com.asecor.extranet.data.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * This is an object that contains data related to the MUsuarioWeb table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="Titulares"
 */

public abstract class BaseTitulares  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Titulares";
	//public static String PROP_UDER_NAME = "userName";
	public static String PROP_COD_TITULAR = "codTitular";
	public static String PROP_DOCUMENTO = "nroDocumento";
	public static String PROP_NOMBRE = "nombre";
	public static String PROP_APELLIDO = "apellido";
	public static String PROP_FECHA_NACIMIENTO = "fechaNacimiento";
	private Set polizas = new HashSet(0);
	

	public Set getPolizas() {
		return polizas;
	}

	public void setPolizas(Set polizas) {
		this.polizas = polizas;
	}

	// constructors
	public BaseTitulares () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTitulares (int id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {} 

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer codTitular;

	// fields 

	private String nombre;
	private String apellido;
	private Long nroDocumento;
	private Date fechaNacimiento;
	private String domicilio;
	private String localidad;
	private Integer numero;
	public Integer getCodTitular() {
		return codTitular;
	}

	public void setCodTitular(Integer codTitular) {
		this.codTitular = codTitular;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getId() {
		return codTitular;
	}

	public void setId(Integer codTitular) {
		this.codTitular = codTitular;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(Long nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	

	

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.asecor.extranet.data.Titulares)) return false;
		else {
			com.asecor.extranet.data.Titulares titulares = (com.asecor.extranet.data.Titulares) obj;
			return (this.getId() == titulares.getId());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getId();
		}
		return this.hashCode;
	}

public String getDomicilioCompleto(){
	return this.domicilio.concat(" ").concat(this.numero.toString()).concat(",").concat(this.localidad);
}
	

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public String getFechaNacimientoString() {
		String fecha=" ";
		if(null!=this.fechaNacimiento) {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		fecha=sdf.format(fechaNacimiento);
		}
		return fecha;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

}