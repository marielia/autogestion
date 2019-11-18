package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class MAutorizacionTO implements Serializable {

	private int codAutorizacion;
	private int codVendedor;
	private String vendNombre;
	private String vendApellido;
	private int codCcss;
	private String ccddDescripcion;
	private int codCliente;
	private String cliDescripcion;
	private int codMotivo;
	private String motDescripcion;
	private int codDescripcion;
	private String desDescripcion;
	private int codChofer;
	private String chofNombre;
	private String chofApellido;
	private int codVehiculo;
	private String patente;
	private String fecha;
	private Integer nroRemito1;
	private Integer nroRemito2;
	private BigDecimal  litrosACargar;
	private int codNroAutorizacion;	
	private String nroAutorizacion;
	private String fechaPedido;
	private int tipoComprobanteManual;
	private String tcmDescripcion;
	
	private int tipoComprobanteAnular;
	private String tcaDescripcion;
	private int producto;
	private String prodDecripcion;
	
	private int sucursalManual;
	private int sucursalAnular;
	
	private Boolean tieneAnula;
	private String fechaUsadoAutorizacion;
	private Integer codEmisor;
	
	public Boolean getTieneAnula() {
		return tieneAnula;
	}



	public void setTieneAnula(Boolean tieneAnula) {
		this.tieneAnula = tieneAnula;
	}



	public String getCcddDescripcion() {
		return ccddDescripcion;
	}



	public void setCcddDescripcion(String ccddDescripcion) {
		this.ccddDescripcion = ccddDescripcion;
	}



	public String getChofApellido() {
		return chofApellido;
	}



	public void setChofApellido(String chofApellido) {
		this.chofApellido = chofApellido;
	}



	public String getChofNombre() {
		return chofNombre;
	}



	public void setChofNombre(String chofNombre) {
		this.chofNombre = chofNombre;
	}



	public String getCliDescripcion() {
		return cliDescripcion;
	}



	public void setCliDescripcion(String cliDescripcion) {
		this.cliDescripcion = cliDescripcion;
	}



	public int getCodAutorizacion() {
		return codAutorizacion;
	}



	public void setCodAutorizacion(int codAutorizacion) {
		this.codAutorizacion = codAutorizacion;
	}



	public int getCodCcss() {
		return codCcss;
	}



	public void setCodCcss(int codCcss) {
		this.codCcss = codCcss;
	}



	public int getCodChofer() {
		return codChofer;
	}



	public void setCodChofer(int codChofer) {
		this.codChofer = codChofer;
	}



	public int getCodCliente() {
		return codCliente;
	}



	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}



	public int getCodDescripcion() {
		return codDescripcion;
	}



	public void setCodDescripcion(int codDescripcion) {
		this.codDescripcion = codDescripcion;
	}



	public int getCodMotivo() {
		return codMotivo;
	}



	public void setCodMotivo(int codMotivo) {
		this.codMotivo = codMotivo;
	}



	public int getCodNroAutorizacion() {
		return codNroAutorizacion;
	}



	public void setCodNroAutorizacion(int codNroAutorizacion) {
		this.codNroAutorizacion = codNroAutorizacion;
	}



	public int getCodVehiculo() {
		return codVehiculo;
	}



	public void setCodVehiculo(int codVehiculo) {
		this.codVehiculo = codVehiculo;
	}



	public int getCodVendedor() {
		return codVendedor;
	}



	public void setCodVendedor(int codVendedor) {
		this.codVendedor = codVendedor;
	}



	public String getDesDescripcion() {
		return desDescripcion;
	}



	public void setDesDescripcion(String desDescripcion) {
		this.desDescripcion = desDescripcion;
	}



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public BigDecimal getLitrosACargar() {
		return litrosACargar;
	}



	public void setLitrosACargar(BigDecimal litrosACargar) {
		this.litrosACargar = litrosACargar;
	}



	public String getMotDescripcion() {
		return motDescripcion;
	}



	public void setMotDescripcion(String motDescripcion) {
		this.motDescripcion = motDescripcion;
	}




	public String getNroAutorizacion() {
		return nroAutorizacion;
	}



	public void setNroAutorizacion(String nroAutorizacion) {
		this.nroAutorizacion = nroAutorizacion;
	}



	public Integer getNroRemito1() {
		return nroRemito1;
	}



	public void setNroRemito1(Integer nroRemito1) {
		this.nroRemito1 = nroRemito1;
	}



	public Integer getNroRemito2() {
		return nroRemito2;
	}



	public void setNroRemito2(Integer nroRemito2) {
		this.nroRemito2 = nroRemito2;
	}



	public String getPatente() {
		return patente;
	}



	public void setPatente(String patente) {
		this.patente = patente;
	}



	public String getVendApellido() {
		return vendApellido;
	}



	public void setVendApellido(String vendApellido) {
		this.vendApellido = vendApellido;
	}



	public String getVendNombre() {
		return vendNombre;
	}



	public void setVendNombre(String vendNombre) {
		this.vendNombre = vendNombre;
	}



	public MAutorizacionTO() {
		// TODO Auto-generated constructor stub
	}



	public String getFechaPedido() {
		return fechaPedido;
	}



	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}



	public String getProdDecripcion() {
		return prodDecripcion;
	}



	public void setProdDecripcion(String prodDecripcion) {
		this.prodDecripcion = prodDecripcion;
	}



	public int getProducto() {
		return producto;
	}



	public void setProducto(int producto) {
		this.producto = producto;
	}



	public int getSucursalAnular() {
		return sucursalAnular;
	}



	public void setSucursalAnular(int sucursalAnular) {
		this.sucursalAnular = sucursalAnular;
	}



	public int getSucursalManual() {
		return sucursalManual;
	}



	public void setSucursalManual(int sucursalManual) {
		this.sucursalManual = sucursalManual;
	}



	public String getTcmDescripcion() {
		return tcmDescripcion;
	}



	public void setTcmDescripcion(String tcmDescripcion) {
		this.tcmDescripcion = tcmDescripcion;
	}



	public int getTipoComprobanteAnular() {
		return tipoComprobanteAnular;
	}



	public void setTipoComprobanteAnular(int tipoComprobanteAnular) {
		this.tipoComprobanteAnular = tipoComprobanteAnular;
	}



	public int getTipoComprobanteManual() {
		return tipoComprobanteManual;
	}



	public void setTipoComprobanteManual(int tipoComprobanteManual) {
		this.tipoComprobanteManual = tipoComprobanteManual;
	}



	public String getTcaDescripcion() {
		return tcaDescripcion;
	}



	public void setTcaDescripcion(String tcaDescripcion) {
		this.tcaDescripcion = tcaDescripcion;
	}



	public String getFechaUsadoAutorizacion() {
		return fechaUsadoAutorizacion;
	}



	public void setFechaUsadoAutorizacion(String fechaUsadoAutorizacion) {
		this.fechaUsadoAutorizacion = fechaUsadoAutorizacion;
	}



	public Integer getCodEmisor() {
		return codEmisor;
	}



	public void setCodEmisor(Integer codEmisor) {
		this.codEmisor = codEmisor;
	}

}
