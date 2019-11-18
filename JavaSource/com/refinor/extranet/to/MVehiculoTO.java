package com.refinor.extranet.to;

import java.io.Serializable;

public class MVehiculoTO implements Serializable {
	
	
	private Integer codigo;
	private String dominio;
	private String codBarra;
	private Boolean activo;
	private String activoDesc;
	private Boolean inicializado;	
	private String inicializadoDesc;
	
		
	private Integer codUnidadNegocio;
	private String descrUnidadNegocio;
	
	private Integer codGrupoUN;
	private String descrGrupoUN;
	
	private Integer codCliente;	
	private String codClienteAlfa;	
		
	private String cliDescripcion;
	private String fechaAlta;
	private String fechaBaja;
	
	
	public String getFechaAlta() {
		return fechaAlta;
	}


	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}


	public String getFechaBaja() {
		return fechaBaja;
	}


	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}


	public MVehiculoTO() {
		// TODO Auto-generated constructor stub
	}


	public String getCodBarra() {
		return codBarra;
	}


	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}


	

	public Integer getCodCliente() {
		return codCliente;
	}


	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}


	public String getCodClienteAlfa() {
		return codClienteAlfa;
	}


	public void setCodClienteAlfa(String codClienteAlfa) {
		this.codClienteAlfa = codClienteAlfa;
	}


	public Integer getCodGrupoUN() {
		return codGrupoUN;
	}


	public void setCodGrupoUN(Integer codGrupoUN) {
		this.codGrupoUN = codGrupoUN;
	}


	public Integer getCodigo() {
		return codigo;
	}


	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


	public Integer getCodUnidadNegocio() {
		return codUnidadNegocio;
	}


	public void setCodUnidadNegocio(Integer codUnidadNegocio) {
		this.codUnidadNegocio = codUnidadNegocio;
	}


	public String getDescrGrupoUN() {
		return descrGrupoUN;
	}


	public void setDescrGrupoUN(String descrGrupoUN) {
		this.descrGrupoUN = descrGrupoUN;
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


	public Boolean getActivo() {
		return activo;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	public Boolean getInicializado() {
		return inicializado;
	}


	public void setInicializado(Boolean inicializado) {
		this.inicializado = inicializado;
	}


	public String getActivoDesc() {
		return activoDesc;
	}


	public void setActivoDesc(String activoDesc) {
		this.activoDesc = activoDesc;
	}


	public String getInicializadoDesc() {
		return inicializadoDesc;
	}


	public void setInicializadoDesc(String inicializadoDesc) {
		this.inicializadoDesc = inicializadoDesc;
	}


	public String getCliDescripcion() {
		return cliDescripcion;
	}


	public void setCliDescripcion(String cliDescripcion) {
		this.cliDescripcion = cliDescripcion;
	}

}
