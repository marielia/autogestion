package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class MFacturaVTO implements Serializable{

	
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
	private Integer nroOperCaja;
	private Integer nroRecibo;
	private BigDecimal pagoAplicado;
	private BigDecimal saldo;
	private String tipoComprobante;
	private String feVto;
	
	private BigDecimal precioNeto;
	private BigDecimal iva;
	private BigDecimal iva2;
	private BigDecimal perIVA;
	private BigDecimal perIIBB;
	private BigDecimal tasaFondo;
	private BigDecimal itc;
	private BigDecimal leyCba; //impuestoGr4
	private BigDecimal co2; //impuestoGr5
	private BigDecimal nroAsientoContable;
	private BigDecimal nroEjercicioContable;
	private BigDecimal total;
	private String tipoPagoFactura;
	
	private Integer codArticulo;
	private String descArticulo;
	
	private BigDecimal cantidad;
	private BigDecimal precioKilo;
	private BigDecimal preciototal;
	
	private String patente;
	
	private String dniChofer;
	private String apellidoChofer;
	private String nombreChofer;
	
	private Integer condicion;
	private String letraComprobante;
	private String cuit;
	
	private BigDecimal netoGravado;
	private BigDecimal netoNoGravado;
	private int exento;
	
	private BigDecimal otros;
	private BigDecimal impuestoInterno;
	
	private String ccss;
	
	private List lstDocumentoAplicados;
	
	private Integer nroRendicionRefipass;
	private BigDecimal montoTotalSubdiario;
	private String fechaRendicion;
	private String tipoDocuCli;
	private int dis;
	
	private BigDecimal porcentajeIVA;
		
	public String getTipoDocuCli() {
		return tipoDocuCli;
	}

	public void setTipoDocuCli(String tipoDocuCli) {
		this.tipoDocuCli = tipoDocuCli;
	}

	public BigDecimal getMontoTotalSubdiario() {
		return montoTotalSubdiario;
	}

	public void setMontoTotalSubdiario(BigDecimal montoTotalSubdiario) {
		this.montoTotalSubdiario = montoTotalSubdiario;
	}

	public Integer getNroRendicionRefipass() {
		return nroRendicionRefipass;
	}

	public void setNroRendicionRefipass(Integer nroRendicionRefipass) {
		this.nroRendicionRefipass = nroRendicionRefipass;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
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

	public Integer getNroOperCaja() {
		return nroOperCaja;
	}

	public void setNroOperCaja(Integer nroOperCaja) {
		this.nroOperCaja = nroOperCaja;
	}

	public Integer getNroRecibo() {
		return nroRecibo;
	}

	public void setNroRecibo(Integer nroRecibo) {
		this.nroRecibo = nroRecibo;
	}

	public MFacturaVTO() {
		// TODO Auto-generated constructor stub
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

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public Integer getNroRemito() {
		return nroRemito;
	}

	public void setNroRemito(Integer nroRemito) {
		this.nroRemito = nroRemito;
	}

	public String getCliDescripcion() {
		return cliDescripcion;
	}

	public void setCliDescripcion(String cliDescripcion) {
		this.cliDescripcion = cliDescripcion;
	}

	public String getCodClienteAlfa() {
		return codClienteAlfa;
	}

	public void setCodClienteAlfa(String codClienteAlfa) {
		this.codClienteAlfa = codClienteAlfa;
	}

	public String getFeVto() {
		return feVto;
	}

	public void setFeVto(String feVto) {
		this.feVto = feVto;
	}

	public BigDecimal getItc() {
		return itc;
	}

	public void setItc(BigDecimal itc) {
		this.itc = itc;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getNroAsientoContable() {
		return nroAsientoContable;
	}

	public void setNroAsientoContable(BigDecimal nroAsientoContable) {
		this.nroAsientoContable = nroAsientoContable;
	}

	public BigDecimal getPerIIBB() {
		return perIIBB;
	}

	public void setPerIIBB(BigDecimal perIIBB) {
		this.perIIBB = perIIBB;
	}

	public BigDecimal getPerIVA() {
		return perIVA;
	}

	public void setPerIVA(BigDecimal perIVA) {
		this.perIVA = perIVA;
	}

	public BigDecimal getTasaFondo() {
		return tasaFondo;
	}

	public void setTasaFondo(BigDecimal tasaFondo) {
		this.tasaFondo = tasaFondo;
	}

	public BigDecimal getPrecioNeto() {
		return precioNeto;
	}

	public void setPrecioNeto(BigDecimal precioNeto) {
		this.precioNeto = precioNeto;
	}

	public String getTipoPagoFactura() {
		return tipoPagoFactura;
	}

	public void setTipoPagoFactura(String tipoPagoFactura) {
		this.tipoPagoFactura = tipoPagoFactura;
	}

	public String getApellidoChofer() {
		return apellidoChofer;
	}

	public void setApellidoChofer(String apellidoChofer) {
		this.apellidoChofer = apellidoChofer;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(Integer codArticulo) {
		this.codArticulo = codArticulo;
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public String getDescArticulo() {
		return descArticulo;
	}

	public void setDescArticulo(String descArticulo) {
		this.descArticulo = descArticulo;
	}

	public String getDniChofer() {
		return dniChofer;
	}

	public void setDniChofer(String dniChofer) {
		this.dniChofer = dniChofer;
	}

	public String getNombreChofer() {
		return nombreChofer;
	}

	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public BigDecimal getPrecioKilo() {
		return precioKilo;
	}

	public void setPrecioKilo(BigDecimal precioKilo) {
		this.precioKilo = precioKilo;
	}

	public BigDecimal getPreciototal() {
		return preciototal;
	}

	public void setPreciototal(BigDecimal preciototal) {
		this.preciototal = preciototal;
	}

	public Integer getCondicion() {
		return condicion;
	}

	public void setCondicion(Integer condicion) {
		this.condicion = condicion;
	}

	public String getCcss() {
		return ccss;
	}

	public void setCcss(String ccss) {
		this.ccss = ccss;
	}

	public String getLetraComprobante() {
		return letraComprobante;
	}

	public void setLetraComprobante(String letraComprobante) {
		this.letraComprobante = letraComprobante;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public int getExento() {
		return exento;
	}

	public void setExento(int exento) {
		this.exento = exento;
	}

	public BigDecimal getNetoGravado() {
		return netoGravado;
	}

	public void setNetoGravado(BigDecimal netoGravado) {
		this.netoGravado = netoGravado;
	}

	public BigDecimal getNetoNoGravado() {
		return netoNoGravado;
	}

	public void setNetoNoGravado(BigDecimal netoNoGravado) {
		this.netoNoGravado = netoNoGravado;
	}

	public BigDecimal getIva2() {
		return iva2;
	}

	public void setIva2(BigDecimal iva2) {
		this.iva2 = iva2;
	}

	public BigDecimal getOtros() {
		return otros;
	}

	public void setOtros(BigDecimal otros) {
		this.otros = otros;
	}

	public BigDecimal getImpuestoInterno() {
		return impuestoInterno;
	}

	public void setImpuestoInterno(BigDecimal impuestoInterno) {
		this.impuestoInterno = impuestoInterno;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List getLstDocumentoAplicados() {
		return lstDocumentoAplicados;
	}

	public void setLstDocumentoAplicados(List lstDocumentoAplicados) {
		this.lstDocumentoAplicados = lstDocumentoAplicados;
	}

	public BigDecimal getNroEjercicioContable() {
		return nroEjercicioContable;
	}

	public void setNroEjercicioContable(BigDecimal nroEjercicioContable) {
		this.nroEjercicioContable = nroEjercicioContable;
	}

	public String getFechaRendicion() {
		return fechaRendicion;
	}

	public void setFechaRendicion(String fechaRendicion) {
		this.fechaRendicion = fechaRendicion;
	}

	public int getDis() {
		return dis;
	}

	public void setDis(int dis) {
		this.dis = dis;
	}

	public BigDecimal getPorcentajeIVA() {
		return porcentajeIVA;
	}

	public void setPorcentajeIVA(BigDecimal porcentajeIVA) {
		this.porcentajeIVA = porcentajeIVA;
	}

	public BigDecimal getLeyCba() {
		return leyCba;
	}

	public void setLeyCba(BigDecimal leyCba) {
		this.leyCba = leyCba;
	}
	
	
	public BigDecimal getCO2() {
		return co2;
	}

	public void setCO2(BigDecimal co2) {
		this.co2 = co2;
	}

}
