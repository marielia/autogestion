package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class ImpuestoLeyVialCbaTO implements Serializable {
	
	private BigDecimal valorImpuesto;
	private String productoDescripcion;
	private String impuestoDescripcion
	;
	public String getImpuestoDescripcion() {
		return impuestoDescripcion;
	}
	public void setImpuestoDescripcion(String impuestoDescripcion) {
		this.impuestoDescripcion = impuestoDescripcion;
	}
	public String getProductoDescripcion() {
		return productoDescripcion;
	}
	public void setProductoDescripcion(String productoDescripcion) {
		this.productoDescripcion = productoDescripcion;
	}
	public BigDecimal getValorImpuesto() {
		return valorImpuesto;
	}
	public void setValorImpuesto(BigDecimal valorImpuesto) {
		this.valorImpuesto = valorImpuesto;
	}

}
