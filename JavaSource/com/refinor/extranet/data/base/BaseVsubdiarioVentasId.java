package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseVsubdiarioVentasId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private java.util.Date fecha;
	private java.lang.String tipoComprobante;
	private java.lang.String tipo;
	private java.lang.Integer nroComprobante;
	private java.lang.String cuit;
	private java.lang.String descCliente;
	private java.math.BigDecimal netoGravado;
	private int exento;
	private java.math.BigDecimal iva;
	private java.math.BigDecimal iva2;
	private java.math.BigDecimal otrasPercepciones;
	private java.math.BigDecimal totalComprobante;
	private java.lang.Integer hiperCliente;
	private java.lang.Integer catIva;
	private java.lang.String descripcion;
	private java.math.BigDecimal impuestoInterno;
	private java.math.BigDecimal itc;
	private java.math.BigDecimal tasaFondo;
	private short nroSucursal;
	private java.math.BigDecimal iibb;
	private java.math.BigDecimal piva;
	private java.math.BigDecimal nogravado;


	public BaseVsubdiarioVentasId () {}
	
	public BaseVsubdiarioVentasId (
		java.util.Date fecha,
		java.lang.String tipoComprobante,
		java.lang.String tipo,
		java.lang.Integer nroComprobante,
		java.lang.String cuit,
		java.lang.String descCliente,
		java.math.BigDecimal netoGravado,
		int exento,
		java.math.BigDecimal iva,
		java.math.BigDecimal iva2,
		java.math.BigDecimal otrasPercepciones,
		java.math.BigDecimal totalComprobante,
		java.lang.Integer hiperCliente,
		java.lang.Integer catIva,
		java.lang.String descripcion,
		java.math.BigDecimal impuestoInterno,
		java.math.BigDecimal itc,
		java.math.BigDecimal tasaFondo,
		short nroSucursal,
		java.math.BigDecimal iibb,
		java.math.BigDecimal piva,
		java.math.BigDecimal nogravado) {

		this.setFecha(fecha);
		this.setTipoComprobante(tipoComprobante);
		this.setTipo(tipo);
		this.setNroComprobante(nroComprobante);
		this.setCuit(cuit);
		this.setDescCliente(descCliente);
		this.setNetoGravado(netoGravado);
		this.setExento(exento);
		this.setIva(iva);
		this.setIva2(iva2);
		this.setOtrasPercepciones(otrasPercepciones);
		this.setTotalComprobante(totalComprobante);
		this.setHiperCliente(hiperCliente);
		this.setCatIva(catIva);
		this.setDescripcion(descripcion);
		this.setImpuestoInterno(impuestoInterno);
		this.setItc(itc);
		this.setTasaFondo(tasaFondo);
		this.setNroSucursal(nroSucursal);
		this.setIibb(iibb);
		this.setPiva(piva);
		this.setNogravado(nogravado);
	}


	/**
	 * Return the value associated with the column: Fecha
	 */
	public java.util.Date getFecha () {
		return fecha;
	}

	/**
	 * Set the value related to the column: Fecha
	 * @param fecha the Fecha value
	 */
	public void setFecha (java.util.Date fecha) {
		this.fecha = fecha;
	}



	/**
	 * Return the value associated with the column: Tipo_Comprobante
	 */
	public java.lang.String getTipoComprobante () {
		return tipoComprobante;
	}

	/**
	 * Set the value related to the column: Tipo_Comprobante
	 * @param tipoComprobante the Tipo_Comprobante value
	 */
	public void setTipoComprobante (java.lang.String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}



	/**
	 * Return the value associated with the column: Tipo
	 */
	public java.lang.String getTipo () {
		return tipo;
	}

	/**
	 * Set the value related to the column: Tipo
	 * @param tipo the Tipo value
	 */
	public void setTipo (java.lang.String tipo) {
		this.tipo = tipo;
	}



	/**
	 * Return the value associated with the column: Nro_Comprobante
	 */
	public java.lang.Integer getNroComprobante () {
		return nroComprobante;
	}

	/**
	 * Set the value related to the column: Nro_Comprobante
	 * @param nroComprobante the Nro_Comprobante value
	 */
	public void setNroComprobante (java.lang.Integer nroComprobante) {
		this.nroComprobante = nroComprobante;
	}



	/**
	 * Return the value associated with the column: CUIT
	 */
	public java.lang.String getCuit () {
		return cuit;
	}

	/**
	 * Set the value related to the column: CUIT
	 * @param cuit the CUIT value
	 */
	public void setCuit (java.lang.String cuit) {
		this.cuit = cuit;
	}



	/**
	 * Return the value associated with the column: Desc_Cliente
	 */
	public java.lang.String getDescCliente () {
		return descCliente;
	}

	/**
	 * Set the value related to the column: Desc_Cliente
	 * @param descCliente the Desc_Cliente value
	 */
	public void setDescCliente (java.lang.String descCliente) {
		this.descCliente = descCliente;
	}



	/**
	 * Return the value associated with the column: Neto_Gravado
	 */
	public java.math.BigDecimal getNetoGravado () {
		return netoGravado;
	}

	/**
	 * Set the value related to the column: Neto_Gravado
	 * @param netoGravado the Neto_Gravado value
	 */
	public void setNetoGravado (java.math.BigDecimal netoGravado) {
		this.netoGravado = netoGravado;
	}



	/**
	 * Return the value associated with the column: Exento
	 */
	public int getExento () {
		return exento;
	}

	/**
	 * Set the value related to the column: Exento
	 * @param exento the Exento value
	 */
	public void setExento (int exento) {
		this.exento = exento;
	}



	/**
	 * Return the value associated with the column: IVA
	 */
	public java.math.BigDecimal getIva () {
		return iva;
	}

	/**
	 * Set the value related to the column: IVA
	 * @param iva the IVA value
	 */
	public void setIva (java.math.BigDecimal iva) {
		this.iva = iva;
	}



	/**
	 * Return the value associated with the column: IVA2
	 */
	public java.math.BigDecimal getIva2 () {
		return iva2;
	}

	/**
	 * Set the value related to the column: IVA2
	 * @param iva2 the IVA2 value
	 */
	public void setIva2 (java.math.BigDecimal iva2) {
		this.iva2 = iva2;
	}



	/**
	 * Return the value associated with the column: Otras_Percepciones
	 */
	public java.math.BigDecimal getOtrasPercepciones () {
		return otrasPercepciones;
	}

	/**
	 * Set the value related to the column: Otras_Percepciones
	 * @param otrasPercepciones the Otras_Percepciones value
	 */
	public void setOtrasPercepciones (java.math.BigDecimal otrasPercepciones) {
		this.otrasPercepciones = otrasPercepciones;
	}



	/**
	 * Return the value associated with the column: Total_Comprobante
	 */
	public java.math.BigDecimal getTotalComprobante () {
		return totalComprobante;
	}

	/**
	 * Set the value related to the column: Total_Comprobante
	 * @param totalComprobante the Total_Comprobante value
	 */
	public void setTotalComprobante (java.math.BigDecimal totalComprobante) {
		this.totalComprobante = totalComprobante;
	}



	/**
	 * Return the value associated with the column: HiperCliente
	 */
	public java.lang.Integer getHiperCliente () {
		return hiperCliente;
	}

	/**
	 * Set the value related to the column: HiperCliente
	 * @param hiperCliente the HiperCliente value
	 */
	public void setHiperCliente (java.lang.Integer hiperCliente) {
		this.hiperCliente = hiperCliente;
	}



	/**
	 * Return the value associated with the column: CatIva
	 */
	public java.lang.Integer getCatIva () {
		return catIva;
	}

	/**
	 * Set the value related to the column: CatIva
	 * @param catIva the CatIva value
	 */
	public void setCatIva (java.lang.Integer catIva) {
		this.catIva = catIva;
	}



	/**
	 * Return the value associated with the column: Descripcion
	 */
	public java.lang.String getDescripcion () {
		return descripcion;
	}

	/**
	 * Set the value related to the column: Descripcion
	 * @param descripcion the Descripcion value
	 */
	public void setDescripcion (java.lang.String descripcion) {
		this.descripcion = descripcion;
	}



	/**
	 * Return the value associated with the column: impuestoInterno
	 */
	public java.math.BigDecimal getImpuestoInterno () {
		return impuestoInterno;
	}

	/**
	 * Set the value related to the column: impuestoInterno
	 * @param impuestoInterno the impuestoInterno value
	 */
	public void setImpuestoInterno (java.math.BigDecimal impuestoInterno) {
		this.impuestoInterno = impuestoInterno;
	}



	/**
	 * Return the value associated with the column: itc
	 */
	public java.math.BigDecimal getItc () {
		return itc;
	}

	/**
	 * Set the value related to the column: itc
	 * @param itc the itc value
	 */
	public void setItc (java.math.BigDecimal itc) {
		this.itc = itc;
	}



	/**
	 * Return the value associated with the column: tasaFondo
	 */
	public java.math.BigDecimal getTasaFondo () {
		return tasaFondo;
	}

	/**
	 * Set the value related to the column: tasaFondo
	 * @param tasaFondo the tasaFondo value
	 */
	public void setTasaFondo (java.math.BigDecimal tasaFondo) {
		this.tasaFondo = tasaFondo;
	}



	/**
	 * Return the value associated with the column: nroSucursal
	 */
	public short getNroSucursal () {
		return nroSucursal;
	}

	/**
	 * Set the value related to the column: nroSucursal
	 * @param nroSucursal the nroSucursal value
	 */
	public void setNroSucursal (short nroSucursal) {
		this.nroSucursal = nroSucursal;
	}



	/**
	 * Return the value associated with the column: iibb
	 */
	public java.math.BigDecimal getIibb () {
		return iibb;
	}

	/**
	 * Set the value related to the column: iibb
	 * @param iibb the iibb value
	 */
	public void setIibb (java.math.BigDecimal iibb) {
		this.iibb = iibb;
	}



	/**
	 * Return the value associated with the column: piva
	 */
	public java.math.BigDecimal getPiva () {
		return piva;
	}

	/**
	 * Set the value related to the column: piva
	 * @param piva the piva value
	 */
	public void setPiva (java.math.BigDecimal piva) {
		this.piva = piva;
	}



	/**
	 * Return the value associated with the column: nogravado
	 */
	public java.math.BigDecimal getNogravado () {
		return nogravado;
	}

	/**
	 * Set the value related to the column: nogravado
	 * @param nogravado the nogravado value
	 */
	public void setNogravado (java.math.BigDecimal nogravado) {
		this.nogravado = nogravado;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.VsubdiarioVentasId)) return false;
		else {
			com.refinor.extranet.data.VsubdiarioVentasId mObj = (com.refinor.extranet.data.VsubdiarioVentasId) obj;
			if (null != this.getFecha() && null != mObj.getFecha()) {
				if (!this.getFecha().equals(mObj.getFecha())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getTipoComprobante() && null != mObj.getTipoComprobante()) {
				if (!this.getTipoComprobante().equals(mObj.getTipoComprobante())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getTipo() && null != mObj.getTipo()) {
				if (!this.getTipo().equals(mObj.getTipo())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getNroComprobante() && null != mObj.getNroComprobante()) {
				if (!this.getNroComprobante().equals(mObj.getNroComprobante())) {
					return false;
				}
			}
			else {
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
			if (null != this.getDescCliente() && null != mObj.getDescCliente()) {
				if (!this.getDescCliente().equals(mObj.getDescCliente())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getNetoGravado() && null != mObj.getNetoGravado()) {
				if (!this.getNetoGravado().equals(mObj.getNetoGravado())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (this.getExento() != mObj.getExento()) {
				return false;
			}
			if (null != this.getIva() && null != mObj.getIva()) {
				if (!this.getIva().equals(mObj.getIva())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getIva2() && null != mObj.getIva2()) {
				if (!this.getIva2().equals(mObj.getIva2())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getOtrasPercepciones() && null != mObj.getOtrasPercepciones()) {
				if (!this.getOtrasPercepciones().equals(mObj.getOtrasPercepciones())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getTotalComprobante() && null != mObj.getTotalComprobante()) {
				if (!this.getTotalComprobante().equals(mObj.getTotalComprobante())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getHiperCliente() && null != mObj.getHiperCliente()) {
				if (!this.getHiperCliente().equals(mObj.getHiperCliente())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getCatIva() && null != mObj.getCatIva()) {
				if (!this.getCatIva().equals(mObj.getCatIva())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getDescripcion() && null != mObj.getDescripcion()) {
				if (!this.getDescripcion().equals(mObj.getDescripcion())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getImpuestoInterno() && null != mObj.getImpuestoInterno()) {
				if (!this.getImpuestoInterno().equals(mObj.getImpuestoInterno())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getItc() && null != mObj.getItc()) {
				if (!this.getItc().equals(mObj.getItc())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getTasaFondo() && null != mObj.getTasaFondo()) {
				if (!this.getTasaFondo().equals(mObj.getTasaFondo())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (this.getNroSucursal() != mObj.getNroSucursal()) {
				return false;
			}
			if (null != this.getIibb() && null != mObj.getIibb()) {
				if (!this.getIibb().equals(mObj.getIibb())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getPiva() && null != mObj.getPiva()) {
				if (!this.getPiva().equals(mObj.getPiva())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getNogravado() && null != mObj.getNogravado()) {
				if (!this.getNogravado().equals(mObj.getNogravado())) {
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
			if (null != this.getFecha()) {
				sb.append(this.getFecha().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getTipoComprobante()) {
				sb.append(this.getTipoComprobante().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getTipo()) {
				sb.append(this.getTipo().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getNroComprobante()) {
				sb.append(this.getNroComprobante().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getCuit()) {
				sb.append(this.getCuit().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getDescCliente()) {
				sb.append(this.getDescCliente().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getNetoGravado()) {
				sb.append(this.getNetoGravado().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			sb.append(new java.lang.Integer(this.getExento()).hashCode());
			sb.append(":");
			if (null != this.getIva()) {
				sb.append(this.getIva().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getIva2()) {
				sb.append(this.getIva2().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getOtrasPercepciones()) {
				sb.append(this.getOtrasPercepciones().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getTotalComprobante()) {
				sb.append(this.getTotalComprobante().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getHiperCliente()) {
				sb.append(this.getHiperCliente().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getCatIva()) {
				sb.append(this.getCatIva().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getDescripcion()) {
				sb.append(this.getDescripcion().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getImpuestoInterno()) {
				sb.append(this.getImpuestoInterno().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getItc()) {
				sb.append(this.getItc().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getTasaFondo()) {
				sb.append(this.getTasaFondo().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			sb.append(new java.lang.Integer(this.getNroSucursal()).hashCode());
			sb.append(":");
			if (null != this.getIibb()) {
				sb.append(this.getIibb().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getPiva()) {
				sb.append(this.getPiva().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getNogravado()) {
				sb.append(this.getNogravado().hashCode());
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