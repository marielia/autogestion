package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MChofer table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MChofer"
 */

public abstract class BaseMchofer  implements Serializable {

	public static String REF = "Mchofer";
	public static String PROP_F_BAJA = "FBaja";
	public static String PROP_PIN_CHOF = "pinChof";
	public static String PROP_INICIALIZADO = "inicializado";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_ACTIVO = "activo";
	public static String PROP_UNIDAD_NEG = "unidadNeg";
	public static String PROP_F_ALTA = "FAlta";
	public static String PROP_NOMBRE = "nombre";
	public static String PROP_APELLIDO = "apellido";
	public static String PROP_COD_CLI = "codCli";
	public static String PROP_DNI = "dni";


	// constructors
	public BaseMchofer () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMchofer (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.Integer codCli;
	private java.lang.String nombre;
	private java.lang.String apellido;
	private java.lang.String pinChof;
	private java.lang.Integer dni;
	private java.lang.Boolean inicializado;
	private java.lang.Integer unidadNeg;
	private java.lang.Boolean activo;
	private java.lang.Boolean idUserLock;
	private java.util.Date fAlta;
	private java.util.Date fBaja;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="codigo"
     */
	public int getCodigo () {
		return codigo;
	}

	/**
	 * Set the unique identifier of this class
	 * @param codigo the new ID
	 */
	public void setCodigo (int codigo) {
		this.codigo = codigo;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: cod_cli
	 */
	public java.lang.Integer getCodCli () {
		return codCli;
	}

	/**
	 * Set the value related to the column: cod_cli
	 * @param codCli the cod_cli value
	 */
	public void setCodCli (java.lang.Integer codCli) {
		this.codCli = codCli;
	}



	/**
	 * Return the value associated with the column: nombre
	 */
	public java.lang.String getNombre () {
		return nombre;
	}

	/**
	 * Set the value related to the column: nombre
	 * @param nombre the nombre value
	 */
	public void setNombre (java.lang.String nombre) {
		this.nombre = nombre;
	}



	/**
	 * Return the value associated with the column: apellido
	 */
	public java.lang.String getApellido () {
		return apellido;
	}

	/**
	 * Set the value related to the column: apellido
	 * @param apellido the apellido value
	 */
	public void setApellido (java.lang.String apellido) {
		this.apellido = apellido;
	}



	/**
	 * Return the value associated with the column: pin_chof
	 */
	public java.lang.String getPinChof () {
		return pinChof;
	}

	/**
	 * Set the value related to the column: pin_chof
	 * @param pinChof the pin_chof value
	 */
	public void setPinChof (java.lang.String pinChof) {
		this.pinChof = pinChof;
	}



	/**
	 * Return the value associated with the column: dni
	 */
	public java.lang.Integer getDni () {
		return dni;
	}

	/**
	 * Set the value related to the column: dni
	 * @param dni the dni value
	 */
	public void setDni (java.lang.Integer dni) {
		this.dni = dni;
	}



	/**
	 * Return the value associated with the column: inicializado
	 */
	public java.lang.Boolean isInicializado () {
		return inicializado;
	}

	/**
	 * Set the value related to the column: inicializado
	 * @param inicializado the inicializado value
	 */
	public void setInicializado (java.lang.Boolean inicializado) {
		this.inicializado = inicializado;
	}



	/**
	 * Return the value associated with the column: unidad_neg
	 */
	public java.lang.Integer getUnidadNeg () {
		return unidadNeg;
	}

	/**
	 * Set the value related to the column: unidad_neg
	 * @param unidadNeg the unidad_neg value
	 */
	public void setUnidadNeg (java.lang.Integer unidadNeg) {
		this.unidadNeg = unidadNeg;
	}



	/**
	 * Return the value associated with the column: activo
	 */
	public java.lang.Boolean isActivo () {
		return activo;
	}

	/**
	 * Set the value related to the column: activo
	 * @param activo the activo value
	 */
	public void setActivo (java.lang.Boolean activo) {
		this.activo = activo;
	}



	/**
	 * Return the value associated with the column: IdUserLock
	 */
	public java.lang.Boolean isIdUserLock () {
		return idUserLock;
	}

	/**
	 * Set the value related to the column: IdUserLock
	 * @param idUserLock the IdUserLock value
	 */
	public void setIdUserLock (java.lang.Boolean idUserLock) {
		this.idUserLock = idUserLock;
	}



	/**
	 * Return the value associated with the column: f_alta
	 */
	public java.util.Date getFAlta () {
		return fAlta;
	}

	/**
	 * Set the value related to the column: f_alta
	 * @param fAlta the f_alta value
	 */
	public void setFAlta (java.util.Date fAlta) {
		this.fAlta = fAlta;
	}



	/**
	 * Return the value associated with the column: f_baja
	 */
	public java.util.Date getFBaja () {
		return fBaja;
	}

	/**
	 * Set the value related to the column: f_baja
	 * @param fBaja the f_baja value
	 */
	public void setFBaja (java.util.Date fBaja) {
		this.fBaja = fBaja;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Mchofer)) return false;
		else {
			com.refinor.extranet.data.Mchofer mchofer = (com.refinor.extranet.data.Mchofer) obj;
			return (this.getCodigo() == mchofer.getCodigo());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getCodigo();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}