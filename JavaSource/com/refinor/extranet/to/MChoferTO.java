package com.refinor.extranet.to;

import java.io.Serializable;

public class MChoferTO implements Serializable{

	private Integer codChofer;
	private String nombreChofer;
	private String apellidoChofer;
	private Integer  dniChofer;
	private Integer codUnidadNegocio;
	private String descrUnidadNegocio;
	private Integer codGrupoUN;
	private String descrGrupoUN;
	private String codClienteAlfa;	
	private Boolean activo;
	private String activoDesc;
	private Boolean inicializado;	
	private String inicializadoDesc;
	
	private Integer codCliente;
	private String cliDescripcion;
	private String pin;
	private String fechaAlta;
	private String fechaBaja;
	
	public Boolean getInicializado() {
		return inicializado;
	}

	public void setInicializado(Boolean inicializado) {
		this.inicializado = inicializado;
	}

	public String getInicializadoDesc() {
		return inicializadoDesc;
	}

	public void setInicializadoDesc(String inicializadoDesc) {
		this.inicializadoDesc = inicializadoDesc;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getActivoDesc() {
		return activoDesc;
	}

	public void setActivoDesc(String activoDesc) {
		this.activoDesc = activoDesc;
	}

	public MChoferTO() {
		// TODO Auto-generated constructor stub
	}

	public String getApellidoChofer() {
		return apellidoChofer;
	}

	public void setApellidoChofer(String apellidoChofer) {
		this.apellidoChofer = apellidoChofer;
	}

	public Integer getCodChofer() {
		return codChofer;
	}

	public void setCodChofer(Integer codChofer) {
		this.codChofer = codChofer;
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

	public String getNombreChofer() {
		return nombreChofer;
	}

	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
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

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

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

}
