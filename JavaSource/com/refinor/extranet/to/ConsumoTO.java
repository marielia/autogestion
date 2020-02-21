package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ConsumoTO implements Serializable {

	private String dominio;
	private Integer codUnidadNegocio;
	private String descrUnidadNegocio;
	private Integer codGrupoUN;
	private String descrGrupoUN;
	private BigDecimal consumoLitros;
	private BigDecimal montoTotal;
	private Integer codProducto;
	private String descProducto;
	private Integer codClienteInt;
	private Date fechaDesde;
	private Date fechaHasta;
	
	private Integer codChofer;
	private Integer dniChofer;
	private String apellidoChofer;
	private String nombreChofer;
	private String cliDescripcion;
	
	public Integer getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(Integer codProducto) {
		this.codProducto = codProducto;
	}

	public BigDecimal getConsumoLitros() {
		return consumoLitros;
	}

	public void setConsumoLitros(BigDecimal consumoLitros) {
		this.consumoLitros = consumoLitros;
	}

	public String getDescProducto() {
		return descProducto;
	}

	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}

	public String getDescrUnidadNegocio() {
		return descrUnidadNegocio;
	}

	public void setDescrUnidadNegocio(String descrUnidadNegocio) {
		this.descrUnidadNegocio = descrUnidadNegocio;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public ConsumoTO() {
		// TODO Auto-generated constructor stub
	}

	public String getDescrGrupoUN() {
		return descrGrupoUN;
	}

	public void setDescrGrupoUN(String descrGrupoUN) {
		this.descrGrupoUN = descrGrupoUN;
	}

	public Integer getCodClienteInt() {
		return codClienteInt;
	}

	public void setCodClienteInt(Integer codClienteInt) {
		this.codClienteInt = codClienteInt;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getApellidoChofer() {
		return apellidoChofer;
	}

	public void setApellidoChofer(String apellidoChofer) {
		this.apellidoChofer = apellidoChofer;
	}

	public String getNombreChofer() {
		return nombreChofer;
	}

	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}

	public Integer getCodChofer() {
		return codChofer;
	}

	public void setCodChofer(Integer codChofer) {
		this.codChofer = codChofer;
	}

	public Integer getCodGrupoUN() {
		return codGrupoUN;
	}

	public void setCodGrupoUN(Integer codGrupoUN) {
		this.codGrupoUN = codGrupoUN;
	}

	public Integer getCodUnidadNegocio() {
		return codUnidadNegocio;
	}

	public void setCodUnidadNegocio(Integer codUnidadNegocio) {
		this.codUnidadNegocio = codUnidadNegocio;
	}

	public Integer getDniChofer() {
		return dniChofer;
	}

	public void setDniChofer(Integer dniChofer) {
		this.dniChofer = dniChofer;
	}

	public String getCliDescripcion() {
		return cliDescripcion;
	}

	public void setCliDescripcion(String cliDescripcion) {
		this.cliDescripcion = cliDescripcion;
	}

}
