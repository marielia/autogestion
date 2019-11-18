package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class MExclusionTO implements Serializable {

	private String fechaDesde;
	private String fechaHasta;
	private String cliDescripcion;
	private String codClienteAlfa;
	private String tipoExcDescripcion;
	private BigDecimal porcentajeExclusion;
	private String provDescripcion;

	public MExclusionTO() {
		// TODO Auto-generated constructor stub
	}

	public String getCliDescripcion() {
		return cliDescripcion;
	}

	public void setCliDescripcion(String cliDescripcion) {
		this.cliDescripcion = cliDescripcion;
	}

	public String getCodClienteAlfa() {
		return codClienteAlfa;
	}

	public void setCodClienteAlfa(String codClienteAlfa) {
		this.codClienteAlfa = codClienteAlfa;
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

	public BigDecimal getPorcentajeExclusion() {
		return porcentajeExclusion;
	}

	public void setPorcentajeExclusion(BigDecimal porcentajeExclusion) {
		this.porcentajeExclusion = porcentajeExclusion;
	}

	public String getProvDescripcion() {
		return provDescripcion;
	}

	public void setProvDescripcion(String provDescripcion) {
		this.provDescripcion = provDescripcion;
	}

	public String getTipoExcDescripcion() {
		return tipoExcDescripcion;
	}

	public void setTipoExcDescripcion(String tipoExcDescripcion) {
		this.tipoExcDescripcion = tipoExcDescripcion;
	}

}
