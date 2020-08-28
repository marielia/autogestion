package com.asecor.extranet.data;

public class SepeliosPlanes implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer codSolicitud;
	private String codPlan;
	private Float importe;
	public Integer getCodSolicitud() {
		return codSolicitud;
	}
	public void setCodSolicitud(Integer codSolicitud) {
		this.codSolicitud = codSolicitud;
	}
	public String getCodPlan() {
		return codPlan;
	}
	public void setCodPlan(String codPlan) {
		this.codPlan = codPlan;
	}
	public Float getImporte() {
		return importe;
	}
	public void setImporte(Float importe) {
		this.importe = importe;
	}
	
}
