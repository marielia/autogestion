package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMusuarioWebId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int id;
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


	public BaseMusuarioWebId () {}
	
	public BaseMusuarioWebId (
		int id,
		java.lang.String cuit,
		java.lang.String email,
		java.lang.String clave,
		char estado,
		int cambioClave,
		int intentosFallidos,
		java.util.Date ultimoIntentoFallido,
		java.util.Date fechaAlta,
		int tipo,
		java.lang.Integer creadorId,
		java.lang.String codClienteAlfa) {

		this.setId(id);
		this.setCuit(cuit);
		this.setEmail(email);
		this.setClave(clave);
		this.setEstado(estado);
		this.setCambioClave(cambioClave);
		this.setIntentosFallidos(intentosFallidos);
		this.setUltimoIntentoFallido(ultimoIntentoFallido);
		this.setFechaAlta(fechaAlta);
		this.setTipo(tipo);
		this.setCreadorId(creadorId);
		this.setCodClienteAlfa(codClienteAlfa);
	}


	/**
	 * Return the value associated with the column: id
	 */
	public int getId () {
		return id;
	}

	/**
	 * Set the value related to the column: id
	 * @param id the id value
	 */
	public void setId (int id) {
		this.id = id;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MusuarioWebId)) return false;
		else {
			com.refinor.extranet.data.MusuarioWebId mObj = (com.refinor.extranet.data.MusuarioWebId) obj;
			if (this.getId() != mObj.getId()) {
				return false;
			}
			if (null != this.getCuit() && null != mObj.getCuit()) {
				if (!this.getCuit().equals(mObj.getCuit())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getEmail() && null != mObj.getEmail()) {
				if (!this.getEmail().equals(mObj.getEmail())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getClave() && null != mObj.getClave()) {
				if (!this.getClave().equals(mObj.getClave())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (this.getEstado() != mObj.getEstado()) {
				return false;
			}
			if (this.getCambioClave() != mObj.getCambioClave()) {
				return false;
			}
			if (this.getIntentosFallidos() != mObj.getIntentosFallidos()) {
				return false;
			}
			if (null != this.getUltimoIntentoFallido() && null != mObj.getUltimoIntentoFallido()) {
				if (!this.getUltimoIntentoFallido().equals(mObj.getUltimoIntentoFallido())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getFechaAlta() && null != mObj.getFechaAlta()) {
				if (!this.getFechaAlta().equals(mObj.getFechaAlta())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (this.getTipo() != mObj.getTipo()) {
				return false;
			}
			if (null != this.getCreadorId() && null != mObj.getCreadorId()) {
				if (!this.getCreadorId().equals(mObj.getCreadorId())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getCodClienteAlfa() && null != mObj.getCodClienteAlfa()) {
				if (!this.getCodClienteAlfa().equals(mObj.getCodClienteAlfa())) {
					return false;
				}
			}
			else {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			sb.append(new java.lang.Integer(this.getId()).hashCode());
			sb.append(":");
			if (null != this.getCuit()) {
				sb.append(this.getCuit().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getEmail()) {
				sb.append(this.getEmail().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getClave()) {
				sb.append(this.getClave().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			sb.append(new java.lang.Character(this.getEstado()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getCambioClave()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getIntentosFallidos()).hashCode());
			sb.append(":");
			if (null != this.getUltimoIntentoFallido()) {
				sb.append(this.getUltimoIntentoFallido().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getFechaAlta()) {
				sb.append(this.getFechaAlta().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			sb.append(new java.lang.Integer(this.getTipo()).hashCode());
			sb.append(":");
			if (null != this.getCreadorId()) {
				sb.append(this.getCreadorId().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getCodClienteAlfa()) {
				sb.append(this.getCodClienteAlfa().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}