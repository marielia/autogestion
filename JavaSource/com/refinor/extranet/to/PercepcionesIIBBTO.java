package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class PercepcionesIIBBTO implements Serializable {

	private String codComprobanteStr; // fc, nd, nc
	private Integer codComprobanteInt; // 1 2 3
	private String fechaEmisionComprobante; //fecha
	private Integer nroSucursal; 
	private Integer nroFactura;
	private Integer codCliente;
	private String codClienteAlfa;
	private String descripcionCli;
	private String cuit;
	private String tipoComprobante;
	private String nroCertificado;
	
	private BigDecimal bruto;
	private BigDecimal neto;
	private BigDecimal netoCalculado;
	private BigDecimal itcTasa;
	private BigDecimal itc;
	private BigDecimal tasa;
	
	private BigDecimal baseImponible;
	private BigDecimal alicuota;
	
	private BigDecimal percepcion;
	private BigDecimal importeFactura;
	
	private String provincia;
	private String tipo;
	private String nroFacturaCompuesto;
	
	private Integer codArticulo;
	private String descArticulo;
	
	private String nroIIBB;
	//YAPA - PARCHE
	private String descricpionGastos1;
	private Integer codProvincia;
	
	private Integer codInscripcionIBCliente;
	private Integer codActividadCliente;
	private Integer codCategoriaIVACliente;
	private Boolean esCombustible;
	private Boolean esGasoil;
	
	private BigDecimal leyCba;
	
	public Integer getCodActividadCliente() {
		return codActividadCliente;
	}
	public void setCodActividadCliente(Integer codActividadCliente) {
		this.codActividadCliente = codActividadCliente;
	}
	public Integer getCodCategoriaIVACliente() {
		return codCategoriaIVACliente;
	}
	public void setCodCategoriaIVACliente(Integer codCategoriaIVACliente) {
		this.codCategoriaIVACliente = codCategoriaIVACliente;
	}
	public Integer getCodInscripcionIBCliente() {
		return codInscripcionIBCliente;
	}
	public void setCodInscripcionIBCliente(Integer codInscripcionIBCliente) {
		this.codInscripcionIBCliente = codInscripcionIBCliente;
	}
	public Integer getCodProvincia() {
		return codProvincia;
	}
	public void setCodProvincia(Integer codProvincia) {
		this.codProvincia = codProvincia;
	}
	public Boolean getEsCombustible() {
		return esCombustible;
	}
	public void setEsCombustible(Boolean esCombustible) {
		this.esCombustible = esCombustible;
	}
	public Boolean getEsGasoil() {
		return esGasoil;
	}
	public void setEsGasoil(Boolean esGasoil) {
		this.esGasoil = esGasoil;
	}
	public BigDecimal getAlicuota() {
		return alicuota;
	}
	public void setAlicuota(BigDecimal alicuota) {
		this.alicuota = alicuota;
	}
	public BigDecimal getBaseImponible() {
		return baseImponible;
	}
	public void setBaseImponible(BigDecimal baseImponible) {
		this.baseImponible = baseImponible;
	}
	public BigDecimal getBruto() {
		return bruto;
	}
	public void setBruto(BigDecimal bruto) {
		this.bruto = bruto;
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
	public Integer getCodComprobanteInt() {
		return codComprobanteInt;
	}
	public void setCodComprobanteInt(Integer codComprobanteInt) {
		this.codComprobanteInt = codComprobanteInt;
	}
	public String getCodComprobanteStr() {
		return codComprobanteStr;
	}
	public void setCodComprobanteStr(String codComprobanteStr) {
		this.codComprobanteStr = codComprobanteStr;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getDescripcionCli() {
		return descripcionCli;
	}
	public void setDescripcionCli(String descripcionCli) {
		this.descripcionCli = descripcionCli;
	}
	

	public String getFechaEmisionComprobante() {
		return fechaEmisionComprobante;
	}
	public void setFechaEmisionComprobante(String fechaEmisionComprobante) {
		this.fechaEmisionComprobante = fechaEmisionComprobante;
	}
	public BigDecimal getItc() {
		return itc;
	}
	public void setItc(BigDecimal itc) {
		this.itc = itc;
	}
	public BigDecimal getItcTasa() {
		return itcTasa;
	}
	public void setItcTasa(BigDecimal itcTasa) {
		this.itcTasa = itcTasa;
	}
	public BigDecimal getNeto() {
		return neto;
	}
	public void setNeto(BigDecimal neto) {
		this.neto = neto;
	}
	public String getNroCertificado() {
		return nroCertificado;
	}
	public void setNroCertificado(String nroCertificado) {
		this.nroCertificado = nroCertificado;
	}
	public Integer getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(Integer nroFactura) {
		this.nroFactura = nroFactura;
	}
	public Integer getNroSucursal() {
		return nroSucursal;
	}
	public void setNroSucursal(Integer nroSucursal) {
		this.nroSucursal = nroSucursal;
	}
	public BigDecimal getPercepcion() {
		return percepcion;
	}
	public void setPercepcion(BigDecimal percepcion) {
		this.percepcion = percepcion;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public BigDecimal getTasa() {
		return tasa;
	}
	public void setTasa(BigDecimal tasa) {
		this.tasa = tasa;
	}
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNroFacturaCompuesto() {
		return nroFacturaCompuesto;
	}
	public void setNroFacturaCompuesto(String nroFacturaCompuesto) {
		this.nroFacturaCompuesto = nroFacturaCompuesto;
	}
	public BigDecimal getImporteFactura() {
		return importeFactura;
	}
	public void setImporteFactura(BigDecimal importeFactura) {
		this.importeFactura = importeFactura;
	}
	public Integer getCodArticulo() {
		return codArticulo;
	}
	public void setCodArticulo(Integer codArticulo) {
		this.codArticulo = codArticulo;
	}
	public String getDescArticulo() {
		return descArticulo;
	}
	public void setDescArticulo(String descArticulo) {
		this.descArticulo = descArticulo;
	}
	public String getNroIIBB() {
		return nroIIBB;
	}
	public void setNroIIBB(String nroIIBB) {
		this.nroIIBB = nroIIBB;
	}
	public BigDecimal getNetoCalculado() {
		return netoCalculado;
	}
	public void setNetoCalculado(BigDecimal netoCalculado) {
		this.netoCalculado = netoCalculado;
	}
	public String getDescricpionGastos1() {
		return descricpionGastos1;
	}
	public void setDescricpionGastos1(String descricpionGastos1) {
		this.descricpionGastos1 = descricpionGastos1;
	}
	public BigDecimal getLeyCba() {
		return leyCba;
	}
	public void setLeyCba(BigDecimal leyCba) {
		this.leyCba = leyCba;
	}
	
	
}
