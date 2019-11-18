package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class HashTO implements Serializable {

	private String titulo;
	private BigDecimal monto;
	public HashTO() {
		this.titulo="";
		this.monto=null;		
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
