package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class RendicionPendienteTO implements Serializable {

	private Integer codCCSS;
	private String descrCCSS;
	private BigDecimal importe;
	private String fechaUltimaRendicion;
	private String fechaActual;
	private long cantidadDias;
	
	
	public Integer getCodCCSS() {
		return codCCSS;
	}

	public void setCodCCSS(Integer codCCSS) {
		this.codCCSS = codCCSS;
	}

	public String getDescrCCSS() {
		return descrCCSS;
	}

	public void setDescrCCSS(String descrCCSS) {
		this.descrCCSS = descrCCSS;
	}

	public String getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	public String getFechaUltimaRendicion() {
		return fechaUltimaRendicion;
	}

	public void setFechaUltimaRendicion(String fechaUltimaRendicion) {
		this.fechaUltimaRendicion = fechaUltimaRendicion;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public RendicionPendienteTO() {
		// TODO Auto-generated constructor stub
	}

	public long getCantidadDias() {
		return cantidadDias;
	}

	public void setCantidadDias(long cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

}
