package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the mFacturasV table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MFacturaManual_tmp"
 */

public abstract class BaseMFacturaManual_tmp  implements Serializable {

	public static String REF = "MFacturaManual_tmp";
	public static String PROP_FECHA = "fecha";
	public static String PROP_NRO_FACTURA = "nroFactura";
	public static String PROP_NRO_SUCURSAL = "nroSucursal";
	public static String PROP_CLIENTE_ALFA = "clienteAlfa";	
	public static String PROP_COD_ARTICULO = "codArticulo";	
	public static String PROP_BULTOS = "bultos";	
	public static String PROP_DNI = "dni";
	public static String PROP_DOMINIO = "dominio";
	public static String PROP_NRO_AUTORIZACION = "nroAutorizacion";	
	public static String PROP_ESTADO = "estado";
	public static String PROP_KILOMETROS = "kilometros";
	public static String PROP_ERRORES = "errores"; 
	public static String PROP_ID = "id";

	// constructors
	public BaseMFacturaManual_tmp () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMFacturaManual_tmp (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date fecha;
	private String fechaStr;
	private java.lang.Integer nroFactura;
	private java.lang.Integer nroSucursal; 
	private java.lang.String clienteAlfa;
	private java.lang.Integer codArticulo;
	private java.lang.Integer bultos;
	private java.math.BigDecimal bultosBig;
	private java.lang.Integer dni;
	private java.lang.String dominio;
	private java.lang.String nroAutorizacion;
	private java.lang.String estado;
	private java.math.BigDecimal kilometros;
	private java.lang.String errores;
	 



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="id"
     */
	public java.lang.Integer getId () {
		return id;
	}
	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}  


	 
 

	public java.lang.Integer getBultos() {
		return bultos;
	}

	public void setBultos(java.lang.Integer bultos) {
		this.bultos = bultos;
	}
	
	public java.math.BigDecimal getBultosBig() {
		return bultosBig;
	}

	public void setBultosBig(java.math.BigDecimal bultosBig) {
		this.bultosBig = bultosBig;
	}

	public java.lang.String getClienteAlfa() {
		return clienteAlfa;
	}

	public void setClienteAlfa(java.lang.String clienteAlfa) {
		this.clienteAlfa = clienteAlfa;
	}

	public java.lang.Integer getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(java.lang.Integer codArticulo) {
		this.codArticulo = codArticulo;
	}

	public java.lang.Integer getDni() {
		return dni;
	}

	public void setDni(java.lang.Integer dni) {
		this.dni = dni;
	}

	public java.lang.String getEstado() {
		return estado;
	}

	public void setEstado(java.lang.String estado) {
		this.estado = estado;
	}

	public java.lang.String getDominio() {
		return dominio;
	}

	public void setDominio(java.lang.String dominio) {
		this.dominio = dominio;
	}

	public java.lang.String getErrores() {
		return errores;
	}

	public void setErrores(java.lang.String errores) {
		this.errores = errores;
	}

	 
	public java.util.Date getFecha() {
		return fecha;
	}

	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	
	public String getFechaStr() {
		return fechaStr;
	}

	public void setFechaStr(String fechaStr) {
		this.fechaStr = fechaStr;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public java.math.BigDecimal getKilometros() {
		return kilometros;
	}

	public void setKilometros(java.math.BigDecimal kilometros) {
		this.kilometros = kilometros;
	}

	public java.lang.String getNroAutorizacion() {
		return nroAutorizacion;
	}

	public void setNroAutorizacion(java.lang.String nroAutorizacion) {
		this.nroAutorizacion = nroAutorizacion;
	}

	public java.lang.Integer getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(java.lang.Integer nroFactura) {
		this.nroFactura = nroFactura;
	}

	public java.lang.Integer getNroSucursal() {
		return nroSucursal;
	}

	public void setNroSucursal(java.lang.Integer nroSucursal) {
		this.nroSucursal = nroSucursal;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MFacturaManual_tmp)) return false;
		else {
			com.refinor.extranet.data.MFacturaManual_tmp mFacturaManual_tmp = (com.refinor.extranet.data.MFacturaManual_tmp) obj;
			return (this.getId() == mFacturaManual_tmp.getId());
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


}