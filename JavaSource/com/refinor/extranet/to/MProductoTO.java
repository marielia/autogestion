package com.refinor.extranet.to;

import java.math.BigDecimal;

public class MProductoTO {

	private Integer codArticulo;
	private String descripcion;
	private BigDecimal cantidad;
	
	public Integer getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(Integer codArticulo) {
		this.codArticulo = codArticulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public MProductoTO() {
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	
	
	
}
