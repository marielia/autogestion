
package com.asecor.extranet.data;

import java.text.SimpleDateFormat;

// Generated 21/04/2020 02:39:42 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * City generated by hbm2java
 */
public class Polizas implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer codcompania;
	private String nroCertificado;
	private Titulares titular;
	private Integer codSolicitud;
	private Boolean active;
	private Float premio;
	private Date fechaAlta;
	private String codEstadoPoliza;
	private String codRama;
	private String codFormaPago;
	private String nroPoliza;
	private Boolean baja;
	private String plan;
	private String detalleAnulacion;
	private Date fechaAnulacion;

	public Polizas() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodcompania() {
		return codcompania;
	}

	public void setCodcompania(Integer codcompania) {
		this.codcompania = codcompania;
	}

	public String getNroCertificado() {
		return nroCertificado;
	}

	public void setNroCertificado(String nroCertificado) {
		this.nroCertificado = nroCertificado;
	}

	public Titulares getTitular() {
		return titular;
	}

	public void setTitular(Titulares titular) {
		this.titular = titular;
	}

	public Integer getCodSolicitud() {
		return codSolicitud;
	}

	public void setCodSolicitud(Integer codSolicitud) {
		this.codSolicitud = codSolicitud;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Float getPremio() {
		return premio;
	}

	public void setPremio(Float premio) {
		this.premio = premio;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}
	public String getFechaAltaString() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fechaAlta);
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getCodEstadoPoliza() {
		return codEstadoPoliza;
	}

	public void setCodEstadoPoliza(String codEstadoPoliza) {
		this.codEstadoPoliza = codEstadoPoliza;
	}

	public String getCodFormaPago() {
		return codFormaPago;
	}

	public void setCodFormaPago(String codFormaPago) {
		this.codFormaPago = codFormaPago;
	}

	public Polizas(Integer id, Integer codcompania, String nroCertificado, Titulares titular, Integer codSolicitud,
			Boolean active, Float premio, Date fechaAlta, String codEstadoPoliza, String codFormaPago,
			String detalleAnulacion,Date fechaAnulacion) {
		super();
		this.id = id;
		this.codcompania = codcompania;
		this.nroCertificado = nroCertificado;
		this.titular = titular;
		this.codSolicitud = codSolicitud;
		this.active = active;
		this.premio = premio;
		this.fechaAlta = fechaAlta;
		this.codEstadoPoliza = codEstadoPoliza;
		this.codFormaPago = codFormaPago;
		this.detalleAnulacion = detalleAnulacion;
		this.fechaAnulacion=fechaAnulacion;
	}

	public String getCodRama() {
		return codRama;
	}

	public void setCodRama(String codRama) {
		this.codRama = codRama;
	}

	public String getNroPoliza() {
		return nroPoliza;
	}

	public void setNroPoliza(String nroPoliza) {
		this.nroPoliza = nroPoliza;
	}

	public Boolean getBaja() {
		return baja;
	}

	public void setBaja(Boolean baja) {
		this.baja = baja;
	}

	public String getEstado() {
		String resultado = "";
		if (this.codEstadoPoliza.equalsIgnoreCase("X")) {
			if (null != detalleAnulacion && "SUPERA CUOTA 99".contentEquals(detalleAnulacion)) {
				resultado = "RENOVADA";
			} else
				resultado = "CANCELADA";

		}

		else
			resultado = "ACTIVA";

		return resultado;
	}

	public String getCompania() {
		String resultado = "";
		if (this.codcompania == 6)
			resultado = "BONACORSI SEGUROS DE PERSONAS SA ";
		else if (this.codcompania == 19)

			resultado = "PRENECESIDAD SEGUROS SEPELIOS ";

		return resultado;
	}

	public String getFormaPago() {
		String resultado = "CA";
		if (this.codFormaPago.equalsIgnoreCase("CC"))
			resultado = "CUENTA CORRIENTE";
		if (this.codFormaPago.equalsIgnoreCase("CA"))
			resultado = "CAJA DE AHORRO";
		if (this.codFormaPago.equalsIgnoreCase("TC"))
			resultado = "TARJETA DE CREDITO";
		if (this.codFormaPago.equalsIgnoreCase("CAP"))
			resultado = "DESCUENTO ADM PUBLICA";
		if (this.codFormaPago.equalsIgnoreCase("JUB"))
			resultado = "DESCUENTO JUB DE ADM PUBLICA";
		return resultado;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getDetalleAnulacion() {
		return detalleAnulacion;
	}

	public void setDetalleAnulacion(String detalleAnulacion) {
		this.detalleAnulacion = detalleAnulacion;
	}

	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}

	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

}
