package com.refinor.extranet.to;

import java.io.Serializable;

public class MLibroDiarioImpresionTO implements Serializable {
	/**
	 * 
	 */
	public MLibroDiarioImpresionTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String  anioPerdiodoAnterior;
	private String  mesPeriodoAnterior;
	private Long nroPaginaInicioAnterior;
	private Long nroPaginaFinAnterior;
	private String  periodoActualConsulta;
	private Long nroPaginaInicioActual;
	public String getAnioPerdiodoAnterior() {
		return anioPerdiodoAnterior;
	}
	public void setAnioPerdiodoAnterior(String anioPerdiodoAnterior) {
		this.anioPerdiodoAnterior = anioPerdiodoAnterior;
	}
	public String getMesPeriodoAnterior() {
		return mesPeriodoAnterior;
	}
	public void setMesPeriodoAnterior(String mesPeriodoAnterior) {
		this.mesPeriodoAnterior = mesPeriodoAnterior;
	}
	public Long getNroPaginaFinAnterior() {
		return nroPaginaFinAnterior;
	}
	public void setNroPaginaFinAnterior(Long nroPaginaFinAnterior) {
		this.nroPaginaFinAnterior = nroPaginaFinAnterior;
	}
	public Long getNroPaginaInicioActual() {
		return nroPaginaInicioActual;
	}
	public void setNroPaginaInicioActual(Long nroPaginaInicioActual) {
		this.nroPaginaInicioActual = nroPaginaInicioActual;
	}
	public Long getNroPaginaInicioAnterior() {
		return nroPaginaInicioAnterior;
	}
	public void setNroPaginaInicioAnterior(Long nroPaginaInicioAnterior) {
		this.nroPaginaInicioAnterior = nroPaginaInicioAnterior;
	}
	public String getPeriodoActualConsulta() {
		return periodoActualConsulta;
	}
	public void setPeriodoActualConsulta(String periodoActualConsulta) {
		this.periodoActualConsulta = periodoActualConsulta;
	}
	

}
