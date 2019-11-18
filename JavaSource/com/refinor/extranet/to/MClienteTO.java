package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class MClienteTO implements Serializable{

	private Integer codigo;
	private String codClienteAlfa;
	private String nombre;	
	
	private String cuit;
	private String correo;
	private String telefono;
	private String domicilio;
	
	private Integer  codPostal;
	private String localidad;
	private String provincia;
	private String categoriaIVA;
	private BigDecimal limiteCredito;
	private BigDecimal numeroIIBB;

	private Boolean activo;
	private String activoDesc;
	private String condicionVta;
	private String inscripcionIIBB;

	private String fechaBaja;
	
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
	public String getCategoriaIVA() {
		return categoriaIVA;
	}
	public void setCategoriaIVA(String categoriaIVA) {
		this.categoriaIVA = categoriaIVA;
	}
	
	public String getCodClienteAlfa() {
		return codClienteAlfa;
	}
	public void setCodClienteAlfa(String codClienteAlfa) {
		this.codClienteAlfa = codClienteAlfa;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(Integer codPostal) {
		this.codPostal = codPostal;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public BigDecimal getNumeroIIBB() {
		return numeroIIBB;
	}
	public void setNumeroIIBB(BigDecimal numeroIIBB) {
		this.numeroIIBB = numeroIIBB;
	}
	public String getCondicionVta() {
		return condicionVta;
	}
	public void setCondicionVta(String condicionVta) {
		this.condicionVta = condicionVta;
	}
	public String getInscripcionIIBB() {
		return inscripcionIIBB;
	}
	public void setInscripcionIIBB(String inscripcionIIBB) {
		this.inscripcionIIBB = inscripcionIIBB;
	}
	public String getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	}
