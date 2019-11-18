package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class PercepcionesIVATO implements Serializable{

	private String codComprobanteStr;
	private Integer codComprobanteInt;
	private String fechaEmisionComprobante;
	private Integer nroSucursal;
	private Integer nroFactura;
	private BigDecimal importe;
	private Integer codImpuesto;
	private Integer codRegimen;
	private Integer codOperacion;
	private BigDecimal baseCalculo;
	private String fechaEmisionRetencion;
	private Integer codCondicion;
	private BigDecimal montoDeLaRetencion;
	private BigDecimal porcExclusion;
	
	
	
	public PercepcionesIVATO() {
		// TODO Auto-generated constructor stub
	}



	public BigDecimal getBaseCalculo() {
		return baseCalculo;
	}



	public void setBaseCalculo(BigDecimal baseCalculo) {
		this.baseCalculo = baseCalculo;
	}



	public Integer getCodComprobanteInt() {
		return codComprobanteInt;
	}



	public void setCodComprobanteInt(Integer codComprobanteInt) {
		this.codComprobanteInt = codComprobanteInt;
	}




	public String getCodComprobanteStr() {
		return codComprobanteStr;
	}



	public void setCodComprobanteStr(String codComprobanteStr) {
		this.codComprobanteStr = codComprobanteStr;
	}



	public Integer getCodCondicion() {
		return codCondicion;
	}



	public void setCodCondicion(Integer codCondicion) {
		this.codCondicion = codCondicion;
	}



	public Integer getCodImpuesto() {
		return codImpuesto;
	}



	public void setCodImpuesto(Integer codImpuesto) {
		this.codImpuesto = codImpuesto;
	}



	public Integer getCodOperacion() {
		return codOperacion;
	}



	public void setCodOperacion(Integer codOperacion) {
		this.codOperacion = codOperacion;
	}



	public Integer getCodRegimen() {
		return codRegimen;
	}



	public void setCodRegimen(Integer codRegimen) {
		this.codRegimen = codRegimen;
	}



	public String getFechaEmisionComprobante() {
		return fechaEmisionComprobante;
	}



	public void setFechaEmisionComprobante(String fechaEmisionComprobante) {
		this.fechaEmisionComprobante = fechaEmisionComprobante;
	}



	public String getFechaEmisionRetencion() {
		return fechaEmisionRetencion;
	}



	public void setFechaEmisionRetencion(String fechaEmisionRetencion) {
		this.fechaEmisionRetencion = fechaEmisionRetencion;
	}



	public BigDecimal getImporte() {
		return importe;
	}



	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}



	public BigDecimal getMontoDeLaRetencion() {
		return montoDeLaRetencion;
	}



	public void setMontoDeLaRetencion(BigDecimal montoDeLaRetencion) {
		this.montoDeLaRetencion = montoDeLaRetencion;
	}



	public Integer getNroSucursal() {
		return nroSucursal;
	}



	public void setNroSucursal(Integer nroSucursal) {
		this.nroSucursal = nroSucursal;
	}



	public BigDecimal getPorcExclusion() {
		return porcExclusion;
	}



	public void setPorcExclusion(BigDecimal porcExclusion) {
		this.porcExclusion = porcExclusion;
	}



	public Integer getNroFactura() {
		return nroFactura;
	}



	public void setNroFactura(Integer nroFactura) {
		this.nroFactura = nroFactura;
	}

}
