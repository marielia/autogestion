package com.refinor.extranet.to;

import java.io.Serializable;

public class RendicionTO implements Serializable {

	private int codRendicion;
	private String descRendicion;
	public int getCodRendicion() {
		return codRendicion;
	}
	public void setCodRendicion(int codRendicion) {
		this.codRendicion = codRendicion;
	}
	public String getDescRendicion() {
		return descRendicion;
	}
	public void setDescRendicion(String descRendicion) {
		this.descRendicion = descRendicion;
	}
	public RendicionTO() {
		// TODO Auto-generated constructor stub
	}

}
