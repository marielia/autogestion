package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class MovimientoStockTO implements Serializable {
	private Integer codCCSS;
	private String descrCCSS;
	private Integer codProducto;
	private String descrProducto;
	private BigDecimal litros;
	private BigDecimal litrosdespachados;
	private BigDecimal saldoLitros;
	
	private String fechaMovimiento;
	private Integer codTipoMovimiento;
	private String descrMovimiento;
	
	private Integer sucursal;
	private Integer nroComprobante;
	private Integer codTipoComprobante;
	private String descrComprobante;
	private String letraC;
	private Integer ultimo;
	private Integer signo;
	
	public Integer getCodTipoComprobante() {
		return codTipoComprobante;
	}
	public void setCodTipoComprobante(Integer codTipoComprobante) {
		this.codTipoComprobante = codTipoComprobante;
	}
	public String getDescrComprobante() {
		return descrComprobante;
	}
	public void setDescrComprobante(String descrComprobante) {
		this.descrComprobante = descrComprobante;
	}
	public String getLetraC() {
		return letraC;
	}
	public void setLetraC(String letraC) {
		this.letraC = letraC;
	}
	public Integer getNroComprobante() {
		return nroComprobante;
	}
	public void setNroComprobante(Integer nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
	public Integer getSucursal() {
		return sucursal;
	}
	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}
	public Integer getCodCCSS() {
		return codCCSS;
	}
	public void setCodCCSS(Integer codCCSS) {
		this.codCCSS = codCCSS;
	}
	public Integer getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(Integer codProducto) {
		this.codProducto = codProducto;
	}
	public String getDescrCCSS() {
		return descrCCSS;
	}
	public void setDescrCCSS(String descrCCSS) {
		this.descrCCSS = descrCCSS;
	}
	public String getDescrProducto() {
		return descrProducto;
	}
	public void setDescrProducto(String descrProducto) {
		this.descrProducto = descrProducto;
	}
	public BigDecimal getLitros() {
		return litros;
	}
	public void setLitros(BigDecimal litros) {
		this.litros = litros;
	}
	public BigDecimal getLitrosdespachados() {
		return litrosdespachados;
	}
	public void setLitrosdespachados(BigDecimal litrosdespachados) {
		this.litrosdespachados = litrosdespachados;
	}
	public Integer getCodTipoMovimiento() {
		return codTipoMovimiento;
	}
	public void setCodTipoMovimiento(Integer codTipoMovimiento) {
		this.codTipoMovimiento = codTipoMovimiento;
	}
	public String getDescrMovimiento() {
		return descrMovimiento;
	}
	public void setDescrMovimiento(String descrMovimiento) {
		this.descrMovimiento = descrMovimiento;
	}
	public String getFechaMovimiento() {
		return fechaMovimiento;
	}
	public void setFechaMovimiento(String fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}
	public BigDecimal getSaldoLitros() {
		return saldoLitros;
	}
	public void setSaldoLitros(BigDecimal saldoLitros) {
		this.saldoLitros = saldoLitros;
	}
	public Integer getUltimo() {
		return ultimo;
	}
	public void setUltimo(Integer ultimo) {
		this.ultimo = ultimo;
	}
	public Integer getSigno() {
		return signo;
	}
	public void setSigno(Integer signo) {
		this.signo = signo;
	}
	
}
