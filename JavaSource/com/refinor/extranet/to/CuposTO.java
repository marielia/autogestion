package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class CuposTO implements Serializable {

	private Integer codProducto;
	private String descripcion; 
	private String patente;
	
	private Integer codUnidadNegocio;
	private String descrUnidadNegocio;
	
	private BigDecimal cupoLitros;
	private BigDecimal consumoLitros;
	private BigDecimal disponibleLitros; 
	
	private Integer codCliente;
	private String codClienteAlfa;
	private Boolean ilimitado;
	private String observacion;
	private String cliDescripcion;
	
	private String codBarra;
	private Boolean activo;
	private String activoStr;
	private String mes;
	private String anio;
	private Integer mesNro;
	private Integer codVehiculo;
	private String familiaGrupoArticulo;
	private String familiaGrupoArticuloDesc;

	public String getActivoStr() {
		return activoStr;
	}

	public void setActivoStr(String activoStr) {
		this.activoStr = activoStr;
	}

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	public String getCliDescripcion() {
		return cliDescripcion;
	}

	public void setCliDescripcion(String cliDescripcion) {
		this.cliDescripcion = cliDescripcion;
	}

	public Integer getCodUnidadNegocio() {
		return codUnidadNegocio;
	}

	public void setCodUnidadNegocio(Integer codUnidadNegocio) {
		this.codUnidadNegocio = codUnidadNegocio;
	}

	public BigDecimal getConsumoLitros() {
		return consumoLitros;
	}

	public void setConsumoLitros(BigDecimal consumoLitros) {
		this.consumoLitros = consumoLitros;
	}

	public BigDecimal getCupoLitros() {
		return cupoLitros;
	}

	public void setCupoLitros(BigDecimal cupoLitros) {
		this.cupoLitros = cupoLitros;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescrUnidadNegocio() {
		return descrUnidadNegocio;
	}

	public void setDescrUnidadNegocio(String descrUnidadNegocio) {
		this.descrUnidadNegocio = descrUnidadNegocio;
	}

	public BigDecimal getDisponibleLitros() {
		return disponibleLitros;
	}

	public void setDisponibleLitros(BigDecimal disponibleLitros) {
		this.disponibleLitros = disponibleLitros;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public CuposTO() {
		// TODO Auto-generated constructor stub
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

	public Boolean getIlimitado() {
		return ilimitado;
	}

	public void setIlimitado(Boolean ilimitado) {
		this.ilimitado = ilimitado;
	}

	public Integer getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(Integer codProducto) {
		this.codProducto = codProducto;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getMesNro() {
		return mesNro;
	}

	public void setMesNro(Integer mesNro) {
		this.mesNro = mesNro;
	}

	public Integer getCodVehiculo() {
		return codVehiculo;
	}

	public void setCodVehiculo(Integer codVehiculo) {
		this.codVehiculo = codVehiculo;
	}

	public String getFamiliaGrupoArticulo() {
		return familiaGrupoArticulo;
	}

	public void setFamiliaGrupoArticulo(String familiaGrupoArticulo) {
		this.familiaGrupoArticulo = familiaGrupoArticulo;
	}

	public String getFamiliaGrupoArticuloDesc() {
		return familiaGrupoArticuloDesc;
	}

	public void setFamiliaGrupoArticuloDesc(String familiaGrupoArticuloDesc) {
		this.familiaGrupoArticuloDesc = familiaGrupoArticuloDesc;
	}

	

}
