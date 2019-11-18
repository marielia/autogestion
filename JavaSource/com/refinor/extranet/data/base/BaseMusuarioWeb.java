package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MUsuarioWeb table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MUsuarioWeb"
 */

public abstract class BaseMusuarioWeb  implements Serializable {

	public static String REF = "MusuarioWeb";
	public static String PROP_COD_CLIENTE_ALFA = "codClienteAlfa";
	public static String PROP_CUIT = "cuit";
	public static String PROP_FECHA_ALTA = "fechaAlta";
	public static String PROP_CODIGO_EMPLEADO = "codigoEmpleado";
	public static String PROP_CAMBIO_CLAVE = "cambioClave";
	public static String PROP_ULTIMO_INTENTO_FALLIDO = "ultimoIntentoFallido";
	public static String PROP_CODIGO_ROL = "codigoRol";
	public static String PROP_EMAIL = "email";
	public static String PROP_TIPO = "tipo";
	public static String PROP_CODIGO_CCSS_EMPLEADO = "codigoCcssEmpleado";
	public static String PROP_CLAVE = "clave";
	public static String PROP_INTENTOS_FALLIDOS = "intentosFallidos";
	public static String PROP_ESTADO = "estado";
	public static String PROP_ID = "id";
	public static String PROP_CREADOR_ID = "creadorId";


	// constructors
	public BaseMusuarioWeb () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMusuarioWeb (int id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int id;

	// fields
	private java.lang.String cuit;
	private java.lang.String email;
	private java.lang.String clave;
	private char estado;
	private int cambioClave;
	private int intentosFallidos;
	private java.util.Date ultimoIntentoFallido;
	private java.util.Date fechaAlta;
	private int tipo;
	private java.lang.Integer creadorId;
	private java.lang.String codClienteAlfa;
	private java.lang.Integer codigoEmpleado;
	private java.lang.Integer codigoCcssEmpleado;
	private java.lang.Integer codigoRol;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="id"
     */
	public int getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (int id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: cuit
	 */
	public java.lang.String getCuit () {
		return cuit;
	}

	/**
	 * Set the value related to the column: cuit
	 * @param cuit the cuit value
	 */
	public void setCuit (java.lang.String cuit) {
		this.cuit = cuit;
	}



	/**
	 * Return the value associated with the column: email
	 */
	public java.lang.String getEmail () {
		return email;
	}

	/**
	 * Set the value related to the column: email
	 * @param email the email value
	 */
	public void setEmail (java.lang.String email) {
		this.email = email;
	}



	/**
	 * Return the value associated with the column: clave
	 */
	public java.lang.String getClave () {
		return clave;
	}

	/**
	 * Set the value related to the column: clave
	 * @param clave the clave value
	 */
	public void setClave (java.lang.String clave) {
		this.clave = clave;
	}



	/**
	 * Return the value associated with the column: estado
	 */
	public char getEstado () {
		return estado;
	}

	/**
	 * Set the value related to the column: estado
	 * @param estado the estado value
	 */
	public void setEstado (char estado) {
		this.estado = estado;
	}



	/**
	 * Return the value associated with the column: cambio_clave
	 */
	public int getCambioClave () {
		return cambioClave;
	}

	/**
	 * Set the value related to the column: cambio_clave
	 * @param cambioClave the cambio_clave value
	 */
	public void setCambioClave (int cambioClave) {
		this.cambioClave = cambioClave;
	}



	/**
	 * Return the value associated with the column: intentos_fallidos
	 */
	public int getIntentosFallidos () {
		return intentosFallidos;
	}

	/**
	 * Set the value related to the column: intentos_fallidos
	 * @param intentosFallidos the intentos_fallidos value
	 */
	public void setIntentosFallidos (int intentosFallidos) {
		this.intentosFallidos = intentosFallidos;
	}



	/**
	 * Return the value associated with the column: ultimo_intento_fallido
	 */
	public java.util.Date getUltimoIntentoFallido () {
		return ultimoIntentoFallido;
	}

	/**
	 * Set the value related to the column: ultimo_intento_fallido
	 * @param ultimoIntentoFallido the ultimo_intento_fallido value
	 */
	public void setUltimoIntentoFallido (java.util.Date ultimoIntentoFallido) {
		this.ultimoIntentoFallido = ultimoIntentoFallido;
	}



	/**
	 * Return the value associated with the column: fecha_alta
	 */
	public java.util.Date getFechaAlta () {
		return fechaAlta;
	}

	/**
	 * Set the value related to the column: fecha_alta
	 * @param fechaAlta the fecha_alta value
	 */
	public void setFechaAlta (java.util.Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}



	/**
	 * Return the value associated with the column: tipo
	 */
	public int getTipo () {
		return tipo;
	}

	/**
	 * Set the value related to the column: tipo
	 * @param tipo the tipo value
	 */
	public void setTipo (int tipo) {
		this.tipo = tipo;
	}



	/**
	 * Return the value associated with the column: creador_id
	 */
	public java.lang.Integer getCreadorId () {
		return creadorId;
	}

	/**
	 * Set the value related to the column: creador_id
	 * @param creadorId the creador_id value
	 */
	public void setCreadorId (java.lang.Integer creadorId) {
		this.creadorId = creadorId;
	}



	/**
	 * Return the value associated with the column: cod_cliente_alfa
	 */
	public java.lang.String getCodClienteAlfa () {
		return codClienteAlfa;
	}

	/**
	 * Set the value related to the column: cod_cliente_alfa
	 * @param codClienteAlfa the cod_cliente_alfa value
	 */
	public void setCodClienteAlfa (java.lang.String codClienteAlfa) {
		this.codClienteAlfa = codClienteAlfa;
	}



	/**
	 * Return the value associated with the column: codigo_empleado
	 */
	public java.lang.Integer getCodigoEmpleado () {
		return codigoEmpleado;
	}

	/**
	 * Set the value related to the column: codigo_empleado
	 * @param codigoEmpleado the codigo_empleado value
	 */
	public void setCodigoEmpleado (java.lang.Integer codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}



	/**
	 * Return the value associated with the column: codigo_ccss_empleado
	 */
	public java.lang.Integer getCodigoCcssEmpleado () {
		return codigoCcssEmpleado;
	}

	/**
	 * Set the value related to the column: codigo_ccss_empleado
	 * @param codigoCcssEmpleado the codigo_ccss_empleado value
	 */
	public void setCodigoCcssEmpleado (java.lang.Integer codigoCcssEmpleado) {
		this.codigoCcssEmpleado = codigoCcssEmpleado;
	}



	/**
	 * Return the value associated with the column: codigo_rol
	 */
	public java.lang.Integer getCodigoRol () {
		return codigoRol;
	}

	/**
	 * Set the value related to the column: codigo_rol
	 * @param codigoRol the codigo_rol value
	 */
	public void setCodigoRol (java.lang.Integer codigoRol) {
		this.codigoRol = codigoRol;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MusuarioWeb)) return false;
		else {
			com.refinor.extranet.data.MusuarioWeb musuarioWeb = (com.refinor.extranet.data.MusuarioWeb) obj;
			return (this.getId() == musuarioWeb.getId());
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