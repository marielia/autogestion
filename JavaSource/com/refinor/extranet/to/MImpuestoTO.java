package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class MImpuestoTO implements Serializable {
	private Integer inscripcionIIBB;
	private Integer inscripcionIVA;
	private Integer provincia;
	private Integer actividad;
	private Integer tipoProducto;
	private BigDecimal alicuota;
	
	public Integer getInscripcionIIBB() {
		return inscripcionIIBB;
	}
	public void setInscripcionIIBB(Integer inscripcionIIBB) {
		this.inscripcionIIBB = inscripcionIIBB;
	}
	public Integer getInscripcionIVA() {
		return inscripcionIVA;
	}
	public void setInscripcionIVA(Integer inscripcionIVA) {
		this.inscripcionIVA = inscripcionIVA;
	}
	public Integer getActividad() {
		return actividad;
	}
	public void setActividad(Integer actividad) {
		this.actividad = actividad;
	}
	public BigDecimal getAlicuota() {
		return alicuota;
	}
	public void setAlicuota(BigDecimal alicuota) {
		this.alicuota = alicuota;
	}
	public Integer getProvincia() {
		return provincia;
	}
	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}
	public Integer getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(Integer tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
}
