package com.refinor.extranet.to;

import java.math.BigDecimal;

public class CCSSTO {

	private int codigo;
	private String descripcion;
	private int nroLegJefe;
	private BigDecimal consumidorFinalId;
	private int pcia;       
	private Boolean  esTerceros;        
	private Boolean  esHoffice;       
	    
	public CCSSTO() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNroLegJefe() {
		return nroLegJefe;
	}

	public void setNroLegJefe(int nroLegJefe) {
		this.nroLegJefe = nroLegJefe;
	}

	public BigDecimal getConsumidorFinalId() {
		return consumidorFinalId;
	}

	public void setConsumidorFinalId(BigDecimal consumidorFinalId) {
		this.consumidorFinalId = consumidorFinalId;
	}

	public int getPcia() {
		return pcia;
	}

	public void setPcia(int pcia) {
		this.pcia = pcia;
	}

	public Boolean getEsTerceros() {
		return esTerceros;
	}

	public void setEsTerceros(Boolean esTerceros) {
		this.esTerceros = esTerceros;
	}

	public Boolean getEsHoffice() {
		return esHoffice;
	}

	public void setEsHoffice(Boolean esHoffice) {
		this.esHoffice = esHoffice;
	}



	

}
