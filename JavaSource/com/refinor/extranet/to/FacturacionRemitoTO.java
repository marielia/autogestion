package com.refinor.extranet.to;

import java.util.Date;

public class FacturacionRemitoTO {

	private Date fecha;
	 
	private String codTarjeta;
	
	private Integer nroSucursal;
	private Integer nroRemito;
	
	private String patente; 
	private String dniChofer;
	private String pinChofer;
	private String kilometraje;
	
	
	private int  codCliente;
	private String descCliente;
	private String codAlfaCliente;
	
	private Integer codCCSS;
	private String descCCSS;
	
	private int codTurno;
	private String descTurno;
	
	private int codAlmacen;
	private String descAlmacen;
	
	 
	 
	public FacturacionRemitoTO() {
		super();
		 
	}
	public String getPinChofer() {
		return pinChofer;
	}
	public void setPinChofer(String pinChofer) {
		this.pinChofer = pinChofer;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getDniChofer() {
		return dniChofer;
	}
	public void setDniChofer(String dniChofer) {
		this.dniChofer = dniChofer;
	}
	public String getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(String kilometraje) {
		this.kilometraje = kilometraje;
	}
	 
	 
	public String getCodTarjeta() {
		return codTarjeta;
	}
	public void setCodTarjeta(String codTarjeta) {
		this.codTarjeta = codTarjeta;
	}
	public Integer getNroSucursal() {
		return nroSucursal;
	}
	public void setNroSucursal(Integer nroSucursal) {
		this.nroSucursal = nroSucursal;
	}
	public Integer getNroRemito() {
		return nroRemito;
	}
	public void setNroRemito(Integer nroRemito) {
		this.nroRemito = nroRemito;
	}
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public String getDescCliente() {
		return descCliente;
	}
	public void setDescCliente(String descCliente) {
		this.descCliente = descCliente;
	}
	public String getCodAlfaCliente() {
		return codAlfaCliente;
	}
	public void setCodAlfaCliente(String codAlfaCliente) {
		this.codAlfaCliente = codAlfaCliente;
	}
	 
	public Integer getCodCCSS() {
		return codCCSS;
	}
	public void setCodCCSS(Integer codCCSS) {
		this.codCCSS = codCCSS;
	}
	public String getDescCCSS() {
		return descCCSS;
	}
	public void setDescCCSS(String descCCSS) {
		this.descCCSS = descCCSS;
	}
	public int getCodTurno() {
		return codTurno;
	}
	public void setCodTurno(int codTurno) {
		this.codTurno = codTurno;
	}
	public String getDescTurno() {
		return descTurno;
	}
	public void setDescTurno(String descTurno) {
		this.descTurno = descTurno;
	}
	public int getCodAlmacen() {
		return codAlmacen;
	}
	public void setCodAlmacen(int codAlmacen) {
		this.codAlmacen = codAlmacen;
	}
	public String getDescAlmacen() {
		return descAlmacen;
	}
	public void setDescAlmacen(String descAlmacen) {
		this.descAlmacen = descAlmacen;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	


	 
	 
	
	
}
