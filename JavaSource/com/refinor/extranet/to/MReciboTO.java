package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MReciboTO implements Serializable {

	private BigDecimal montoTotal;
	private Integer numero;
	private String fecha;
	private Integer codCliente;
	private String cliDescripcion;
	private String codClienteAlfa;
	private String tipoComprobanteRecibo;;
	private Integer nroOperCaja;
	private Integer sucursalRecibo;
	private String nombreSucursal;;
	private Integer sucursalFactura;
	private Integer nroFactura;
	private String tipoComprobanteFactura;
	private String fechaVto;
	private String fechaBaja;
	private String estadoRecibo;
	private BigDecimal importeRecibo;
	
	public String getTipoComprobanteFactura() {
		return tipoComprobanteFactura;
	}

	public void setTipoComprobanteFactura(String tipoComprobanteFactura) {
		this.tipoComprobanteFactura = tipoComprobanteFactura;
	}

	public String getCliDescripcion() {
		return cliDescripcion;
	}

	public void setCliDescripcion(String cliDescripcion) {
		this.cliDescripcion = cliDescripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public MReciboTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public Integer getNroOperCaja() {
		return nroOperCaja;
	}

	public void setNroOperCaja(Integer nroOperCaja) {
		this.nroOperCaja = nroOperCaja;
	}

	public String getCodClienteAlfa() {
		return codClienteAlfa;
	}

	public void setCodClienteAlfa(String codClienteAlfa) {
		this.codClienteAlfa = codClienteAlfa;
	}

	public String getTipoComprobanteRecibo() {
		return tipoComprobanteRecibo;
	}

	public void setTipoComprobanteRecibo(String tipoComprobanteRecibo) {
		this.tipoComprobanteRecibo = tipoComprobanteRecibo;
	}

	public Integer getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(Integer nroFactura) {
		this.nroFactura = nroFactura;
	}

	public Integer getSucursalFactura() {
		return sucursalFactura;
	}

	public void setSucursalFactura(Integer sucursalFactura) {
		this.sucursalFactura = sucursalFactura;
	}

	public Integer getSucursalRecibo() {
		return sucursalRecibo;
	}

	public void setSucursalRecibo(Integer sucursalRecibo) {
		this.sucursalRecibo = sucursalRecibo;
	}

	public String getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getFechaVto() {
		return fechaVto;
	}

	public void setFechaVto(String fechaVto) {
		this.fechaVto = fechaVto;
	}

	public String getEstadoRecibo() {
		return estadoRecibo;
	}

	public void setEstadoRecibo(String estadoRecibo) {
		this.estadoRecibo = estadoRecibo;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public BigDecimal getImporteRecibo() {
		return importeRecibo;
	}

	public void setImporteRecibo(BigDecimal importeRecibo) {
		this.importeRecibo = importeRecibo;
	}

	

}
