package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class MListaPrecioTO implements Serializable {
	
	private Integer codCCSS;
	private String descripcionCCSS;
	
	private Integer codProducto;
	private String descripcionProducto; 
	
	private Integer codListaPrecio;
	private String descripcionListaPrecio; 
	
	private String fechaDesde;
	private String fechaHasta;
	
	private Integer codCliente;
	private String descripcionCliente;
	
	private String tipoLista;
	
	private BigDecimal precio;

	public Integer getCodCCSS() {
		return codCCSS;
	}

	public void setCodCCSS(Integer codCCSS) {
		this.codCCSS = codCCSS;
	}

	public Integer getCodListaPrecio() {
		return codListaPrecio;
	}

	public void setCodListaPrecio(Integer codListaPrecio) {
		this.codListaPrecio = codListaPrecio;
	}

	public Integer getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(Integer codProducto) {
		this.codProducto = codProducto;
	}

	public String getDescripcionCCSS() {
		return descripcionCCSS;
	}

	public void setDescripcionCCSS(String descripcionCCSS) {
		this.descripcionCCSS = descripcionCCSS;
	}

	public String getDescripcionListaPrecio() {
		return descripcionListaPrecio;
	}

	public void setDescripcionListaPrecio(String descripcionListaPrecio) {
		this.descripcionListaPrecio = descripcionListaPrecio;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public String getDescripcionCliente() {
		return descripcionCliente;
	}

	public void setDescripcionCliente(String descripcionCliente) {
		this.descripcionCliente = descripcionCliente;
	}

	public String getTipoLista() {
		return tipoLista;
	}

	public void setTipoLista(String tipoLista) {
		this.tipoLista = tipoLista;
	}
	
	
	

}
