package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class CtasCtesTO implements Serializable {

	private String comprobanteDescripcion;
	private int comprobanteNumero;
	private int sucursal;
	private String fecha;
	private String vencimiento;
	private BigDecimal importe;
	private int estadoInt;
	private String estadoStr;
	private String cliDescripcion;
	private BigDecimal netoFactura;
	private String codClienteAlfa;
	private BigDecimal topeCredito;
	
	private String tipoComprobante;
	private int codCliente;
	public int getCodCliente() {
		return codCliente;
	}


	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}


	public CtasCtesTO() {
		// TODO Auto-generated constructor stub
	}


	public String getComprobanteDescripcion() {
		return comprobanteDescripcion;
	}


	public void setComprobanteDescripcion(String comprobanteDescripcion) {
		this.comprobanteDescripcion = comprobanteDescripcion;
	}




	public int getComprobanteNumero() {
		return comprobanteNumero;
	}


	public void setComprobanteNumero(int comprobanteNumero) {
		this.comprobanteNumero = comprobanteNumero;
	}


	public int getEstadoInt() {
		return estadoInt;
	}


	public void setEstadoInt(int estadoInt) {
		this.estadoInt = estadoInt;
	}


	public String getEstadoStr() {
		return estadoStr;
	}


	public void setEstadoStr(String estadoStr) {
		this.estadoStr = estadoStr;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public BigDecimal getImporte() {
		return importe;
	}


	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}


	public String getVencimiento() {
		return vencimiento;
	}


	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}


	public String getCliDescripcion() {
		return cliDescripcion;
	}


	public void setCliDescripcion(String cliDescripcion) {
		this.cliDescripcion = cliDescripcion;
	}


	public BigDecimal getNetoFactura() {
		return netoFactura;
	}


	public void setNetoFactura(BigDecimal netoFactura) {
		this.netoFactura = netoFactura;
	}


	public String getCodClienteAlfa() {
		return codClienteAlfa;
	}


	public void setCodClienteAlfa(String codClienteAlfa) {
		this.codClienteAlfa = codClienteAlfa;
	}


	public BigDecimal getTopeCredito() {
		return topeCredito;
	}


	public void setTopeCredito(BigDecimal topeCredito) {
		this.topeCredito = topeCredito;
	}


	public int getSucursal() {
		return sucursal;
	}


	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}


	public String getTipoComprobante() {
		return tipoComprobante;
	}


	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

}
