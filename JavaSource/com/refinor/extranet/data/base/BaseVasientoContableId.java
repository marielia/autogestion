package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseVasientoContableId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int ccssId;
	private java.lang.String descCcss;
	private java.lang.Long nroEjercicio;
	private long nroAsiento;
	private java.lang.String cuenta;
	private java.lang.String debHab;
	private java.math.BigDecimal valorDebHab;
	private java.lang.String leyenda;
	private java.lang.String fecha;
	private java.lang.String descCuenta;
	private int nroSuc;
	private java.lang.Integer nroFac;
	private java.lang.String compr;
	private java.lang.String letra;
	private java.lang.String codigoCli;
	private java.lang.String cliente;
	private int comprobanteAsociado;
	private java.lang.Long nroRendicion;
	private java.lang.String hora;


	public BaseVasientoContableId () {}
	
	public BaseVasientoContableId (
		int ccssId,
		java.lang.String descCcss,
		java.lang.Long nroEjercicio,
		long nroAsiento,
		java.lang.String cuenta,
		java.lang.String debHab,
		java.math.BigDecimal valorDebHab,
		java.lang.String leyenda,
		java.lang.String fecha,
		java.lang.String descCuenta,
		int nroSuc,
		java.lang.Integer nroFac,
		java.lang.String compr,
		java.lang.String letra,
		java.lang.String codigoCli,
		java.lang.String cliente,
		int comprobanteAsociado,
		java.lang.Long nroRendicion,
		java.lang.String hora) {

		this.setCcssId(ccssId);
		this.setDescCcss(descCcss);
		this.setNroEjercicio(nroEjercicio);
		this.setNroAsiento(nroAsiento);
		this.setCuenta(cuenta);
		this.setDebHab(debHab);
		this.setValorDebHab(valorDebHab);
		this.setLeyenda(leyenda);
		this.setFecha(fecha);
		this.setDescCuenta(descCuenta);
		this.setNroSuc(nroSuc);
		this.setNroFac(nroFac);
		this.setCompr(compr);
		this.setLetra(letra);
		this.setCodigoCli(codigoCli);
		this.setCliente(cliente);
		this.setComprobanteAsociado(comprobanteAsociado);
		this.setNroRendicion(nroRendicion);
		this.setHora(hora);
	}


	/**
	 * Return the value associated with the column: ccssId
	 */
	public int getCcssId () {
		return ccssId;
	}

	/**
	 * Set the value related to the column: ccssId
	 * @param ccssId the ccssId value
	 */
	public void setCcssId (int ccssId) {
		this.ccssId = ccssId;
	}



	/**
	 * Return the value associated with the column: descCCSS
	 */
	public java.lang.String getDescCcss () {
		return descCcss;
	}

	/**
	 * Set the value related to the column: descCCSS
	 * @param descCcss the descCCSS value
	 */
	public void setDescCcss (java.lang.String descCcss) {
		this.descCcss = descCcss;
	}



	/**
	 * Return the value associated with the column: nroEjercicio
	 */
	public java.lang.Long getNroEjercicio () {
		return nroEjercicio;
	}

	/**
	 * Set the value related to the column: nroEjercicio
	 * @param nroEjercicio the nroEjercicio value
	 */
	public void setNroEjercicio (java.lang.Long nroEjercicio) {
		this.nroEjercicio = nroEjercicio;
	}



	/**
	 * Return the value associated with the column: nroAsiento
	 */
	public long getNroAsiento () {
		return nroAsiento;
	}

	/**
	 * Set the value related to the column: nroAsiento
	 * @param nroAsiento the nroAsiento value
	 */
	public void setNroAsiento (long nroAsiento) {
		this.nroAsiento = nroAsiento;
	}



	/**
	 * Return the value associated with the column: cuenta
	 */
	public java.lang.String getCuenta () {
		return cuenta;
	}

	/**
	 * Set the value related to the column: cuenta
	 * @param cuenta the cuenta value
	 */
	public void setCuenta (java.lang.String cuenta) {
		this.cuenta = cuenta;
	}



	/**
	 * Return the value associated with the column: debHab
	 */
	public java.lang.String getDebHab () {
		return debHab;
	}

	/**
	 * Set the value related to the column: debHab
	 * @param debHab the debHab value
	 */
	public void setDebHab (java.lang.String debHab) {
		this.debHab = debHab;
	}



	/**
	 * Return the value associated with the column: valorDebHab
	 */
	public java.math.BigDecimal getValorDebHab () {
		return valorDebHab;
	}

	/**
	 * Set the value related to the column: valorDebHab
	 * @param valorDebHab the valorDebHab value
	 */
	public void setValorDebHab (java.math.BigDecimal valorDebHab) {
		this.valorDebHab = valorDebHab;
	}



	/**
	 * Return the value associated with the column: leyenda
	 */
	public java.lang.String getLeyenda () {
		return leyenda;
	}

	/**
	 * Set the value related to the column: leyenda
	 * @param leyenda the leyenda value
	 */
	public void setLeyenda (java.lang.String leyenda) {
		this.leyenda = leyenda;
	}



	/**
	 * Return the value associated with the column: fecha
	 */
	public java.lang.String getFecha () {
		return fecha;
	}

	/**
	 * Set the value related to the column: fecha
	 * @param fecha the fecha value
	 */
	public void setFecha (java.lang.String fecha) {
		this.fecha = fecha;
	}



	/**
	 * Return the value associated with the column: descCuenta
	 */
	public java.lang.String getDescCuenta () {
		return descCuenta;
	}

	/**
	 * Set the value related to the column: descCuenta
	 * @param descCuenta the descCuenta value
	 */
	public void setDescCuenta (java.lang.String descCuenta) {
		this.descCuenta = descCuenta;
	}



	/**
	 * Return the value associated with the column: nroSuc
	 */
	public int getNroSuc () {
		return nroSuc;
	}

	/**
	 * Set the value related to the column: nroSuc
	 * @param nroSuc the nroSuc value
	 */
	public void setNroSuc (int nroSuc) {
		this.nroSuc = nroSuc;
	}



	/**
	 * Return the value associated with the column: nroFac
	 */
	public java.lang.Integer getNroFac () {
		return nroFac;
	}

	/**
	 * Set the value related to the column: nroFac
	 * @param nroFac the nroFac value
	 */
	public void setNroFac (java.lang.Integer nroFac) {
		this.nroFac = nroFac;
	}



	/**
	 * Return the value associated with the column: compr
	 */
	public java.lang.String getCompr () {
		return compr;
	}

	/**
	 * Set the value related to the column: compr
	 * @param compr the compr value
	 */
	public void setCompr (java.lang.String compr) {
		this.compr = compr;
	}



	/**
	 * Return the value associated with the column: letra
	 */
	public java.lang.String getLetra () {
		return letra;
	}

	/**
	 * Set the value related to the column: letra
	 * @param letra the letra value
	 */
	public void setLetra (java.lang.String letra) {
		this.letra = letra;
	}



	/**
	 * Return the value associated with the column: codigoCli
	 */
	public java.lang.String getCodigoCli () {
		return codigoCli;
	}

	/**
	 * Set the value related to the column: codigoCli
	 * @param codigoCli the codigoCli value
	 */
	public void setCodigoCli (java.lang.String codigoCli) {
		this.codigoCli = codigoCli;
	}



	/**
	 * Return the value associated with the column: cliente
	 */
	public java.lang.String getCliente () {
		return cliente;
	}

	/**
	 * Set the value related to the column: cliente
	 * @param cliente the cliente value
	 */
	public void setCliente (java.lang.String cliente) {
		this.cliente = cliente;
	}



	/**
	 * Return the value associated with the column: comprobanteAsociado
	 */
	public int getComprobanteAsociado () {
		return comprobanteAsociado;
	}

	/**
	 * Set the value related to the column: comprobanteAsociado
	 * @param comprobanteAsociado the comprobanteAsociado value
	 */
	public void setComprobanteAsociado (int comprobanteAsociado) {
		this.comprobanteAsociado = comprobanteAsociado;
	}



	/**
	 * Return the value associated with the column: nroRendicion
	 */
	public java.lang.Long getNroRendicion () {
		return nroRendicion;
	}

	/**
	 * Set the value related to the column: nroRendicion
	 * @param nroRendicion the nroRendicion value
	 */
	public void setNroRendicion (java.lang.Long nroRendicion) {
		this.nroRendicion = nroRendicion;
	}



	/**
	 * Return the value associated with the column: hora
	 */
	public java.lang.String getHora () {
		return hora;
	}

	/**
	 * Set the value related to the column: hora
	 * @param hora the hora value
	 */
	public void setHora (java.lang.String hora) {
		this.hora = hora;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.VasientoContableId)) return false;
		else {
			com.refinor.extranet.data.VasientoContableId mObj = (com.refinor.extranet.data.VasientoContableId) obj;
			if (this.getCcssId() != mObj.getCcssId()) {
				return false;
			}
			if (null != this.getDescCcss() && null != mObj.getDescCcss()) {
				if (!this.getDescCcss().equals(mObj.getDescCcss())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getNroEjercicio() && null != mObj.getNroEjercicio()) {
				if (!this.getNroEjercicio().equals(mObj.getNroEjercicio())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (this.getNroAsiento() != mObj.getNroAsiento()) {
				return false;
			}
			if (null != this.getCuenta() && null != mObj.getCuenta()) {
				if (!this.getCuenta().equals(mObj.getCuenta())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getDebHab() && null != mObj.getDebHab()) {
				if (!this.getDebHab().equals(mObj.getDebHab())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getValorDebHab() && null != mObj.getValorDebHab()) {
				if (!this.getValorDebHab().equals(mObj.getValorDebHab())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getLeyenda() && null != mObj.getLeyenda()) {
				if (!this.getLeyenda().equals(mObj.getLeyenda())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getFecha() && null != mObj.getFecha()) {
				if (!this.getFecha().equals(mObj.getFecha())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getDescCuenta() && null != mObj.getDescCuenta()) {
				if (!this.getDescCuenta().equals(mObj.getDescCuenta())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (this.getNroSuc() != mObj.getNroSuc()) {
				return false;
			}
			if (null != this.getNroFac() && null != mObj.getNroFac()) {
				if (!this.getNroFac().equals(mObj.getNroFac())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getCompr() && null != mObj.getCompr()) {
				if (!this.getCompr().equals(mObj.getCompr())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getLetra() && null != mObj.getLetra()) {
				if (!this.getLetra().equals(mObj.getLetra())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getCodigoCli() && null != mObj.getCodigoCli()) {
				if (!this.getCodigoCli().equals(mObj.getCodigoCli())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getCliente() && null != mObj.getCliente()) {
				if (!this.getCliente().equals(mObj.getCliente())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (this.getComprobanteAsociado() != mObj.getComprobanteAsociado()) {
				return false;
			}
			if (null != this.getNroRendicion() && null != mObj.getNroRendicion()) {
				if (!this.getNroRendicion().equals(mObj.getNroRendicion())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getHora() && null != mObj.getHora()) {
				if (!this.getHora().equals(mObj.getHora())) {
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
			sb.append(new java.lang.Integer(this.getCcssId()).hashCode());
			sb.append(":");
			if (null != this.getDescCcss()) {
				sb.append(this.getDescCcss().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getNroEjercicio()) {
				sb.append(this.getNroEjercicio().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			sb.append(new java.lang.Long(this.getNroAsiento()).hashCode());
			sb.append(":");
			if (null != this.getCuenta()) {
				sb.append(this.getCuenta().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getDebHab()) {
				sb.append(this.getDebHab().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getValorDebHab()) {
				sb.append(this.getValorDebHab().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getLeyenda()) {
				sb.append(this.getLeyenda().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getFecha()) {
				sb.append(this.getFecha().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getDescCuenta()) {
				sb.append(this.getDescCuenta().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			sb.append(new java.lang.Integer(this.getNroSuc()).hashCode());
			sb.append(":");
			if (null != this.getNroFac()) {
				sb.append(this.getNroFac().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getCompr()) {
				sb.append(this.getCompr().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getLetra()) {
				sb.append(this.getLetra().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getCodigoCli()) {
				sb.append(this.getCodigoCli().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getCliente()) {
				sb.append(this.getCliente().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			sb.append(new java.lang.Integer(this.getComprobanteAsociado()).hashCode());
			sb.append(":");
			if (null != this.getNroRendicion()) {
				sb.append(this.getNroRendicion().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getHora()) {
				sb.append(this.getHora().hashCode());
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