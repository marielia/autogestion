package com.refinor.extranet.to;

import java.io.Serializable;

public class MlimiteCargaTO implements Serializable{

	private String agrupado;
	private String descripcion;
	private String limite;
	private String litros;
	private String utilizado;
	private String permitido;
	
	public String getAgrupado() {
		return agrupado;
	}
	public void setAgrupado(String agrupado) {
		this.agrupado = agrupado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLimite() {
		return limite;
	}
	public void setLimite(String limite) {
		this.limite = limite;
	}
	public String getLitros() {
		return litros;
	}
	public void setLitros(String litros) {
		this.litros = litros;
	}
	public String getUtilizado() {
		return utilizado;
	}
	public void setUtilizado(String utilizado) {
		this.utilizado = utilizado;
	}
	public String getPermitido() {
		return permitido;
	}
	public void setPermitido(String permitido) {
		this.permitido = permitido;
	}
	
}