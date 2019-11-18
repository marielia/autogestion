package com.refinor.extranet.to;

import java.io.Serializable;

public class MesTO implements Serializable {

	private int nroMes;
	private String nombreMes;
	
	public String getNombreMes() {
		return nombreMes;
	}

	public void setNombreMes(String nombreMes) {
		this.nombreMes = nombreMes;
	}

	public int getNroMes() {
		return nroMes;
	}

	public void setNroMes(int nroMes) {
		this.nroMes = nroMes;
	}

	public MesTO() {
		
	}

}
