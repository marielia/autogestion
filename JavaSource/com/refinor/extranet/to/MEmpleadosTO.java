package com.refinor.extranet.to;

import java.io.Serializable;

public class MEmpleadosTO implements Serializable {

	private Integer codigo;
	private String descripcion;
	private String descripcionApe;
	private Integer nroLegajo;
	private Boolean activo;
	private Integer ccssid;
	private Integer dni;
	private String descrCCSS;
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Integer getCcssid() {
		return ccssid;
	}

	public void setCcssid(Integer ccssid) {
		this.ccssid = ccssid;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescrCCSS() {
		return descrCCSS;
	}

	public void setDescrCCSS(String descrCCSS) {
		this.descrCCSS = descrCCSS;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Integer getNroLegajo() {
		return nroLegajo;
	}

	public void setNroLegajo(Integer nroLegajo) {
		this.nroLegajo = nroLegajo;
	}

	public MEmpleadosTO() {
		// TODO Auto-generated constructor stub
	}

	public String getDescripcionApe() {
		return descripcionApe;
	}

	public void setDescripcionApe(String descripcionApe) {
		this.descripcionApe = descripcionApe;
	}

}
