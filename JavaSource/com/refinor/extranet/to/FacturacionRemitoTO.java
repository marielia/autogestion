package com.refinor.extranet.to;

public class FacturacionRemitoTO {

	private String fecha;
	private String hora;
	private String codTarjeta;
	
	private String patente; 
	private String dniChofer;
	private String pinChofer;
	private String kilometraje;
	
	
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
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
	private Integer nroSucursal;
	private Integer nroRemito;
	 
	
	
}
