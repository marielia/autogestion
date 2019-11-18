package com.refinor.extranet.to;

import java.math.BigDecimal;

public class PagoAdelTO {

	private Integer nroOper;
	private Integer nroOperCaja;
	private BigDecimal importe;
	private String tipoOper;
	
	
	private Integer order;
	private String fecha;
	private String Tipo;
	private Integer nroSucursal;
	private Integer nroFactura;
	private BigDecimal netoFactura;
	private Integer nroRemito;
	private String cliDescripcion;
	private Integer codCliente;
	private String codClienteAlfa;	
	private Integer nroRecibo;
	private BigDecimal pagoAplicado;
	private BigDecimal saldo;
	private String tipoComprobante;
	private BigDecimal total;
	private String feVto;
	
	
	public String getFeVto() {
		return feVto;
	}


	public void setFeVto(String feVto) {
		this.feVto = feVto;
	}


	public BigDecimal getTotal() {
		return total;
	}


	public void setTotal(BigDecimal total) {
		this.total = total;
	}


	public BigDecimal getImporte() {
		return importe;
	}


	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}


	public Integer getNroOper() {
		return nroOper;
	}


	public void setNroOper(Integer nroOper) {
		this.nroOper = nroOper;
	}


	public Integer getNroOperCaja() {
		return nroOperCaja;
	}


	public void setNroOperCaja(Integer nroOperCaja) {
		this.nroOperCaja = nroOperCaja;
	}


	public String getTipoOper() {
		return tipoOper;
	}


	public void setTipoOper(String tipoOper) {
		this.tipoOper = tipoOper;
	}


	public PagoAdelTO() {
		// TODO Auto-generated constructor stub
	}


	public String getCliDescripcion() {
		return cliDescripcion;
	}


	public void setCliDescripcion(String cliDescripcion) {
		this.cliDescripcion = cliDescripcion;
	}


	public Integer getCodCliente() {
		return codCliente;
	}


	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}


	public String getCodClienteAlfa() {
		return codClienteAlfa;
	}


	public void setCodClienteAlfa(String codClienteAlfa) {
		this.codClienteAlfa = codClienteAlfa;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public BigDecimal getNetoFactura() {
		return netoFactura;
	}


	public void setNetoFactura(BigDecimal netoFactura) {
		this.netoFactura = netoFactura;
	}


	public Integer getNroFactura() {
		return nroFactura;
	}


	public void setNroFactura(Integer nroFactura) {
		this.nroFactura = nroFactura;
	}


	public Integer getNroRecibo() {
		return nroRecibo;
	}


	public void setNroRecibo(Integer nroRecibo) {
		this.nroRecibo = nroRecibo;
	}


	public Integer getNroRemito() {
		return nroRemito;
	}


	public void setNroRemito(Integer nroRemito) {
		this.nroRemito = nroRemito;
	}


	public Integer getNroSucursal() {
		return nroSucursal;
	}


	public void setNroSucursal(Integer nroSucursal) {
		this.nroSucursal = nroSucursal;
	}


	public Integer getOrder() {
		return order;
	}


	public void setOrder(Integer order) {
		this.order = order;
	}


	public BigDecimal getPagoAplicado() {
		return pagoAplicado;
	}


	public void setPagoAplicado(BigDecimal pagoAplicado) {
		this.pagoAplicado = pagoAplicado;
	}


	public BigDecimal getSaldo() {
		return saldo;
	}


	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}


	public String getTipo() {
		return Tipo;
	}


	public void setTipo(String tipo) {
		Tipo = tipo;
	}


	public String getTipoComprobante() {
		return tipoComprobante;
	}


	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

}
