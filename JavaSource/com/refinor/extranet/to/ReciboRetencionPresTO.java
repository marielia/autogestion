package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReciboRetencionPresTO implements Serializable {

	private int nroRecibo;
	private String fechaRecibo;
	private int codTipoRetencion;
	private String tipoRetencion;
	private BigDecimal nroCertificado;
	private String fechaCertificado;
	private BigDecimal importe;
	private int codJuridiccion;
	private String descrJuridiccion;
	private String codClienteAlfa;
	private String razonSocial;
	private String estadoRecibo;
	
	public String getCodClienteAlfa() {
		return codClienteAlfa;
	}

	public void setCodClienteAlfa(String codClienteAlfa) {
		this.codClienteAlfa = codClienteAlfa;
	}

	public int getCodJuridiccion() {
		return codJuridiccion;
	}

	public void setCodJuridiccion(int codJuridiccion) {
		this.codJuridiccion = codJuridiccion;
	}

	public String getDescrJuridiccion() {
		return descrJuridiccion;
	}

	public void setDescrJuridiccion(String descrJuridiccion) {
		this.descrJuridiccion = descrJuridiccion;
	}

	public String getFechaCertificado() {
		return fechaCertificado;
	}

	public void setFechaCertificado(String fechaCertificado) {
		this.fechaCertificado = fechaCertificado;
	}

	public String getFechaRecibo() {
		return fechaRecibo;
	}

	public void setFechaRecibo(String fechaRecibo) {
		this.fechaRecibo = fechaRecibo;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigDecimal getNroCertificado() {
		return nroCertificado;
	}

	public void setNroCertificado(BigDecimal nroCertificado) {
		this.nroCertificado = nroCertificado;
	}

	public int getNroRecibo() {
		return nroRecibo;
	}

	public void setNroRecibo(int nroRecibo) {
		this.nroRecibo = nroRecibo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTipoRetencion() {
		return tipoRetencion;
	}

	public void setTipoRetencion(String tipoRetencion) {
		this.tipoRetencion = tipoRetencion;
	}

	public ReciboRetencionPresTO() {
		// TODO Auto-generated constructor stub
	}

	public int getCodTipoRetencion() {
		return codTipoRetencion;
	}

	public void setCodTipoRetencion(int codTipoRetencion) {
		this.codTipoRetencion = codTipoRetencion;
	}

	public String getEstadoRecibo() {
		return estadoRecibo;
	}

	public void setEstadoRecibo(String estadoRecibo) {
		this.estadoRecibo = estadoRecibo;
	}


}
