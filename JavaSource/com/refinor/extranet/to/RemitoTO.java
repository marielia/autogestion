package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RemitoTO implements Serializable {

	private String fecha;
	private String hora;
	private Integer nroSucursal;
	private Integer nroRemito;
	private Integer codProducto;
	private String descProducto;
	
	private BigDecimal precioKilo;
	private BigDecimal montoTotal;
	private BigDecimal litros;
	private String patente;
	private Integer codChofer;
	private String nombreChofer;
	private String apellidoChofer;
	
	private String codClienteAlta;
	
	private Integer codUnidadNegocioV;
	private String descrUnidadNegocioV;
	private Integer codGrupoUNV;
	private String descrGrupoUNV;
	
	private Integer codUnidadNegocioC;
	private String descrUnidadNegocioC;
	private Integer codGrupoUNC;
	private String descrGrupoUNC;
	
	private String ccss;
	
	private String codClienteAlfa;
	private String cliDescripcion;
	private BigDecimal itc;
	private BigDecimal tasaFondo;
	private BigDecimal iva;
	private BigDecimal kilometraje;
	private Integer nroLiquidacion;
	private String codBarra;
	private String nroAutorizacion;
	private Integer nroSucursalFactura;
	private Integer nroFactura;
	private String tipoComprobante;
	private BigDecimal iibb;
	
	private int facturado;
	private String facturadoString;
	
	private int condicion;
	private String condicionDesc;
	private BigDecimal precioConImpuestos;
	
	private BigDecimal nroEjercicioContable;
	private BigDecimal nroAsientoContable;
	private BigDecimal precioListaPorCCSSArticuloFecha;
	private String comparacionPrecioCImp;
	private String fechaFacturacion;
	private String reFacturacion;
	private BigDecimal leyCba;
	private BigDecimal totalLeyCba;
	private BigDecimal piva;
	private BigDecimal co2;
	
	public RemitoTO() {
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

	public Integer getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(Integer codProducto) {
		this.codProducto = codProducto;
	}

	public String getDescProducto() {
		return descProducto;
	}

	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}

	

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getLitros() {
		return litros;
	}

	public void setLitros(BigDecimal litros) {
		this.litros = litros;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getNombreChofer() {
		return nombreChofer;
	}

	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}

	public Integer getNroRemito() {
		return nroRemito;
	}

	public void setNroRemito(Integer nroRemito) {
		this.nroRemito = nroRemito;
	}

	public Integer getNroSucursal() {
		return nroSucursal;
	}

	public void setNroSucursal(Integer nroSucursal) {
		this.nroSucursal = nroSucursal;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getCodClienteAlta() {
		return codClienteAlta;
	}

	public void setCodClienteAlta(String codClienteAlta) {
		this.codClienteAlta = codClienteAlta;
	}

	public BigDecimal getPrecioKilo() {
		return precioKilo;
	}

	public void setPrecioKilo(BigDecimal precioKilo) {
		this.precioKilo = precioKilo;
	}

	public Integer getCodUnidadNegocioC() {
		return codUnidadNegocioC;
	}

	public void setCodUnidadNegocioC(Integer codUnidadNegocioC) {
		this.codUnidadNegocioC = codUnidadNegocioC;
	}

	public Integer getCodUnidadNegocioV() {
		return codUnidadNegocioV;
	}

	public void setCodUnidadNegocioV(Integer codUnidadNegocioV) {
		this.codUnidadNegocioV = codUnidadNegocioV;
	}

	public String getDescrUnidadNegocioC() {
		return descrUnidadNegocioC;
	}

	public void setDescrUnidadNegocioC(String descrUnidadNegocioC) {
		this.descrUnidadNegocioC = descrUnidadNegocioC;
	}

	public String getDescrUnidadNegocioV() {
		return descrUnidadNegocioV;
	}

	public void setDescrUnidadNegocioV(String descrUnidadNegocioV) {
		this.descrUnidadNegocioV = descrUnidadNegocioV;
	}

	public Integer getCodGrupoUNC() {
		return codGrupoUNC;
	}

	public void setCodGrupoUNC(Integer codGrupoUNC) {
		this.codGrupoUNC = codGrupoUNC;
	}

	public Integer getCodGrupoUNV() {
		return codGrupoUNV;
	}

	public void setCodGrupoUNV(Integer codGrupoUNV) {
		this.codGrupoUNV = codGrupoUNV;
	}

	public String getDescrGrupoUNC() {
		return descrGrupoUNC;
	}

	public void setDescrGrupoUNC(String descrGrupoUNC) {
		this.descrGrupoUNC = descrGrupoUNC;
	}

	public String getDescrGrupoUNV() {
		return descrGrupoUNV;
	}

	public void setDescrGrupoUNV(String descrGrupoUNV) {
		this.descrGrupoUNV = descrGrupoUNV;
	}

	public String getCcss() {
		return ccss;
	}

	public void setCcss(String ccss) {
		this.ccss = ccss;
	}

	public String getCliDescripcion() {
		return cliDescripcion;
	}

	public void setCliDescripcion(String cliDescripcion) {
		this.cliDescripcion = cliDescripcion;
	}

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	public String getCodClienteAlfa() {
		return codClienteAlfa;
	}

	public void setCodClienteAlfa(String codClienteAlfa) {
		this.codClienteAlfa = codClienteAlfa;
	}

	

	public int getFacturado() {
		return facturado;
	}

	public void setFacturado(int facturado) {
		this.facturado = facturado;
	}

	public BigDecimal getItc() {
		return itc;
	}

	public void setItc(BigDecimal itc) {
		this.itc = itc;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(BigDecimal kilometraje) {
		this.kilometraje = kilometraje;
	}

	public String getNroAutorizacion() {
		return nroAutorizacion;
	}

	public void setNroAutorizacion(String nroAutorizacion) {
		this.nroAutorizacion = nroAutorizacion;
	}

	public Integer getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(Integer nroFactura) {
		this.nroFactura = nroFactura;
	}

	public Integer getNroLiquidacion() {
		return nroLiquidacion;
	}

	public void setNroLiquidacion(Integer nroLiquidacion) {
		this.nroLiquidacion = nroLiquidacion;
	}

	public Integer getNroSucursalFactura() {
		return nroSucursalFactura;
	}

	public void setNroSucursalFactura(Integer nroSucursalFactura) {
		this.nroSucursalFactura = nroSucursalFactura;
	}

	
	public BigDecimal getTasaFondo() {
		return tasaFondo;
	}

	public void setTasaFondo(BigDecimal tasaFondo) {
		this.tasaFondo = tasaFondo;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public int getCondicion() {
		return condicion;
	}

	public void setCondicion(int condicion) {
		this.condicion = condicion;
	}

	public String getCondicionDesc() {
		return condicionDesc;
	}

	public void setCondicionDesc(String condicionDesc) {
		this.condicionDesc = condicionDesc;
	}

	public String getFacturadoString() {
		return facturadoString;
	}

	public void setFacturadoString(String facturadoString) {
		this.facturadoString = facturadoString;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public BigDecimal getIibb() {
		return iibb;
	}

	public void setIibb(BigDecimal iibb) {
		this.iibb = iibb;
	}

	public BigDecimal getPrecioConImpuestos() {
		return precioConImpuestos;
	}

	public void setPrecioConImpuestos(BigDecimal precioConImpuestos) {
		this.precioConImpuestos = precioConImpuestos;
	}

	public BigDecimal getNroAsientoContable() {
		return nroAsientoContable;
	}

	public void setNroAsientoContable(BigDecimal nroAsientoContable) {
		this.nroAsientoContable = nroAsientoContable;
	}

	public BigDecimal getNroEjercicioContable() {
		return nroEjercicioContable;
	}

	public void setNroEjercicioContable(BigDecimal nroEjercicioContable) {
		this.nroEjercicioContable = nroEjercicioContable;
	}

	public BigDecimal getPrecioListaPorCCSSArticuloFecha() {
		return precioListaPorCCSSArticuloFecha;
	}

	public void setPrecioListaPorCCSSArticuloFecha(
			BigDecimal precioListaPorCCSSArticuloFecha) {
		this.precioListaPorCCSSArticuloFecha = precioListaPorCCSSArticuloFecha;
	}

	public String getComparacionPrecioCImp() {
		return comparacionPrecioCImp;
	}

	public void setComparacionPrecioCImp(String comparacionPrecioCImp) {
		this.comparacionPrecioCImp = comparacionPrecioCImp;
	}

	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public String getReFacturacion() {
		return reFacturacion;
	}

	public void setReFacturacion(String reFacturacion) {
		this.reFacturacion = reFacturacion;
	}

	public BigDecimal getLeyCba() {
		return leyCba;
	}

	public void setLeyCba(BigDecimal leyCba) {
		this.leyCba = leyCba;
	}

	public BigDecimal getTotalLeyCba() {
		return totalLeyCba;
	}

	public void setTotalLeyCba(BigDecimal totalLeyCba) {
		this.totalLeyCba = totalLeyCba;
	}

	public BigDecimal getPiva() {
		return piva;
	}

	public void setPiva(BigDecimal piva) {
		this.piva = piva;
	}

	
	public BigDecimal getCO2() {
		return co2;
	}

	public void setCO2(BigDecimal co2) {
		this.co2 = co2;
	}
	

}
