package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class MCuentaBancoTO implements Serializable {
private Integer nroOper;
private Integer codCcss;
private String descripcionCcss;
private Integer nroEjercicio;
private Integer nroAsiento;
private String cuentaBanco;
private String fecha;
private BigDecimal importe;
private String tipoOperacion;
private String codOperacion;
private Integer nroRendicion;
private Integer nroOperRendicion;

	
	public MCuentaBancoTO() {
		// TODO Auto-generated constructor stub
	}


	public Integer getCodCcss() {
		return codCcss;
	}


	public void setCodCcss(Integer codCcss) {
		this.codCcss = codCcss;
	}


	public String getCodOperacion() {
		return codOperacion;
	}


	public void setCodOperacion(String codOperacion) {
		this.codOperacion = codOperacion;
	}


	public String getCuentaBanco() {
		return cuentaBanco;
	}


	public void setCuentaBanco(String cuentaBanco) {
		this.cuentaBanco = cuentaBanco;
	}


	public String getDescripcionCcss() {
		return descripcionCcss;
	}


	public void setDescripcionCcss(String descripcionCcss) {
		this.descripcionCcss = descripcionCcss;
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


	public Integer getNroAsiento() {
		return nroAsiento;
	}


	public void setNroAsiento(Integer nroAsiento) {
		this.nroAsiento = nroAsiento;
	}


	public Integer getNroEjercicio() {
		return nroEjercicio;
	}


	public void setNroEjercicio(Integer nroEjercicio) {
		this.nroEjercicio = nroEjercicio;
	}


	public Integer getNroOper() {
		return nroOper;
	}


	public void setNroOper(Integer nroOper) {
		this.nroOper = nroOper;
	}


	public Integer getNroOperRendicion() {
		return nroOperRendicion;
	}


	public void setNroOperRendicion(Integer nroOperRendicion) {
		this.nroOperRendicion = nroOperRendicion;
	}


	public Integer getNroRendicion() {
		return nroRendicion;
	}


	public void setNroRendicion(Integer nroRendicion) {
		this.nroRendicion = nroRendicion;
	}


	public String getTipoOperacion() {
		return tipoOperacion;
	}


	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

}
