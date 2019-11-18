package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class CombustibleTO implements Serializable{

	public CombustibleTO() {
		// TODO Auto-generated constructor stub
	}	
	
	private Integer id;	
	private Integer ccss;
	private String ccssDesc;
	private Integer producto;
	private String productoDesc;
	private BigDecimal litros;	
	private String fecha;
	private Integer tipoComprobanteCod;
	private String tipoComprobanteDesc;	
	private String letraComprobante;
	private Integer nroComprobante;
	private Integer estado;
	private String estadoDesc;;
	private Integer responsable;
	private String responsableDesc;
	public Integer getCcss() {
		return ccss;
	}
	public void setCcss(Integer ccss) {
		this.ccss = ccss;
	}
	public String getCcssDesc() {
		return ccssDesc;
	}
	public void setCcssDesc(String ccssDesc) {
		this.ccssDesc = ccssDesc;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public String getEstadoDesc() {
		return estadoDesc;
	}
	public void setEstadoDesc(String estadoDesc) {
		this.estadoDesc = estadoDesc;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLetraComprobante() {
		return letraComprobante;
	}
	public void setLetraComprobante(String letraComprobante) {
		this.letraComprobante = letraComprobante;
	}
	public BigDecimal getLitros() {
		return litros;
	}
	public void setLitros(BigDecimal litros) {
		this.litros = litros;
	}
	public Integer getNroComprobante() {
		return nroComprobante;
	}
	public void setNroComprobante(Integer nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
	public Integer getProducto() {
		return producto;
	}
	public void setProducto(Integer producto) {
		this.producto = producto;
	}
	public String getProductoDesc() {
		return productoDesc;
	}
	public void setProductoDesc(String productoDesc) {
		this.productoDesc = productoDesc;
	}
	public Integer getResponsable() {
		return responsable;
	}
	public void setResponsable(Integer responsable) {
		this.responsable = responsable;
	}
	public String getResponsableDesc() {
		return responsableDesc;
	}
	public void setResponsableDesc(String responsableDesc) {
		this.responsableDesc = responsableDesc;
	}
	public Integer getTipoComprobanteCod() {
		return tipoComprobanteCod;
	}
	public void setTipoComprobanteCod(Integer tipoComprobanteCod) {
		this.tipoComprobanteCod = tipoComprobanteCod;
	}
	public String getTipoComprobanteDesc() {
		return tipoComprobanteDesc;
	}
	public void setTipoComprobanteDesc(String tipoComprobanteDesc) {
		this.tipoComprobanteDesc = tipoComprobanteDesc;
	};
	
	
	

}
