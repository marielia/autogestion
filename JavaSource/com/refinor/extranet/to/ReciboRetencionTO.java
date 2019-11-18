package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReciboRetencionTO implements Serializable {

	private int nroRecibo;
	private String fechaRecibo;

	private int codIva;
	private String tipoRetIva;
	private BigDecimal nroCertIva;
	private String fechaIva;
	private BigDecimal importeIva;
	
	private int codGanancias;
	private String tipoRetGanancias;
	private BigDecimal nroCertGanancias;
	private String fechaGanancias;
	private BigDecimal importeGanancias;

	
	
	private int codIngresoBrutos;
	private String tipoRetIngresoBrutos;
	private BigDecimal nroCertIngresoBrutos;
	private String fechaIngresoBrutos;
	private BigDecimal importeIngresoBrutos;
	private int juridIngresoBrutos;
	private String descJuridIngresoBrutos;
	
	
	private int codSIJP;
	private String tipoRetSIJP;
	private BigDecimal nroCertSIJP;
	private String fechaSIJP;
	private BigDecimal importeSIJP;
	
	private int codSegSocP;
	private String tipoRetSegSoc;
	private BigDecimal nroCertSegSoc;
	private String fechaSegSoc;
	private BigDecimal importeSegSoc;
	
	
	private int codTRS;
	private String tipoRetTRS;
	private BigDecimal nroCertTRS;
	private String fechaTRS;
	private BigDecimal importeTRS;
	private int juridTRS;
	private String descTRS;
	
	private String cliDescripcion;
	private String codClienteAlfa;
	private int codClienteInt;
	
	private int codTISSH;
	private String tipoRetTISSH;
	private BigDecimal nroCertTISSH;
	private String fechaCertTISSH;
	private BigDecimal importeTISSH;
	
	private int codTMT;
	private String tipoRetTMT;
	private BigDecimal nroCertTMTartagal;
	private String fechaTmTartagal;
	private BigDecimal importeImpTMTartagal;
	
	

	private int codTEMTUc;
	private String tipoRetTEMTUc;
	private BigDecimal nroPercTEMTUc;
	private String fecPercTEMTUc;
	private BigDecimal impPercTEMTUc;
	
	
	
	
	private String fechaBaja;
	private String estadoRecibo;
	public String getEstadoRecibo() {
		return estadoRecibo;
	}



	public void setEstadoRecibo(String estadoRecibo) {
		this.estadoRecibo = estadoRecibo;
	}



	public String getFechaBaja() {
		return fechaBaja;
	}



	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
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



	public int getCodClienteInt() {
		return codClienteInt;
	}



	public void setCodClienteInt(int codClienteInt) {
		this.codClienteInt = codClienteInt;
	}



	public int getCodGanancias() {
		return codGanancias;
	}



	public void setCodGanancias(int codGanancias) {
		this.codGanancias = codGanancias;
	}



	public int getCodIngresoBrutos() {
		return codIngresoBrutos;
	}



	public void setCodIngresoBrutos(int codIngresoBrutos) {
		this.codIngresoBrutos = codIngresoBrutos;
	}



	public int getCodIva() {
		return codIva;
	}



	public void setCodIva(int codIva) {
		this.codIva = codIva;
	}



	public int getCodSegSocP() {
		return codSegSocP;
	}



	public void setCodSegSocP(int codSegSocP) {
		this.codSegSocP = codSegSocP;
	}



	public int getCodSIJP() {
		return codSIJP;
	}



	public void setCodSIJP(int codSIJP) {
		this.codSIJP = codSIJP;
	}



	public int getCodTRS() {
		return codTRS;
	}



	public void setCodTRS(int codTRS) {
		this.codTRS = codTRS;
	}



	public String getDescJuridIngresoBrutos() {
		return descJuridIngresoBrutos;
	}



	public void setDescJuridIngresoBrutos(String descJuridIngresoBrutos) {
		this.descJuridIngresoBrutos = descJuridIngresoBrutos;
	}



	public String getDescTRS() {
		return descTRS;
	}



	public void setDescTRS(String descTRS) {
		this.descTRS = descTRS;
	}



	public String getFechaGanancias() {
		return fechaGanancias;
	}



	public void setFechaGanancias(String fechaGanancias) {
		this.fechaGanancias = fechaGanancias;
	}



	public String getFechaIngresoBrutos() {
		return fechaIngresoBrutos;
	}



	public void setFechaIngresoBrutos(String fechaIngresoBrutos) {
		this.fechaIngresoBrutos = fechaIngresoBrutos;
	}



	public String getFechaIva() {
		return fechaIva;
	}



	public void setFechaIva(String fechaIva) {
		this.fechaIva = fechaIva;
	}



	public String getFechaRecibo() {
		return fechaRecibo;
	}



	public void setFechaRecibo(String fechaRecibo) {
		this.fechaRecibo = fechaRecibo;
	}



	public String getFechaSegSoc() {
		return fechaSegSoc;
	}



	public void setFechaSegSoc(String fechaSegSoc) {
		this.fechaSegSoc = fechaSegSoc;
	}



	public String getFechaSIJP() {
		return fechaSIJP;
	}



	public void setFechaSIJP(String fechaSIJP) {
		this.fechaSIJP = fechaSIJP;
	}



	public String getFechaTRS() {
		return fechaTRS;
	}



	public void setFechaTRS(String fechaTRS) {
		this.fechaTRS = fechaTRS;
	}



	public BigDecimal getImporteGanancias() {
		return importeGanancias;
	}



	public void setImporteGanancias(BigDecimal importeGanancias) {
		this.importeGanancias = importeGanancias;
	}



	public BigDecimal getImporteIngresoBrutos() {
		return importeIngresoBrutos;
	}



	public void setImporteIngresoBrutos(BigDecimal importeIngresoBrutos) {
		this.importeIngresoBrutos = importeIngresoBrutos;
	}



	public BigDecimal getImporteIva() {
		return importeIva;
	}



	public void setImporteIva(BigDecimal importeIva) {
		this.importeIva = importeIva;
	}



	public BigDecimal getImporteSegSoc() {
		return importeSegSoc;
	}



	public void setImporteSegSoc(BigDecimal importeSegSoc) {
		this.importeSegSoc = importeSegSoc;
	}



	public BigDecimal getImporteSIJP() {
		return importeSIJP;
	}



	public void setImporteSIJP(BigDecimal importeSIJP) {
		this.importeSIJP = importeSIJP;
	}



	public BigDecimal getImporteTRS() {
		return importeTRS;
	}



	public void setImporteTRS(BigDecimal importeTRS) {
		this.importeTRS = importeTRS;
	}



	public int getJuridIngresoBrutos() {
		return juridIngresoBrutos;
	}



	public void setJuridIngresoBrutos(int juridIngresoBrutos) {
		this.juridIngresoBrutos = juridIngresoBrutos;
	}



	public int getJuridTRS() {
		return juridTRS;
	}



	public void setJuridTRS(int juridTRS) {
		this.juridTRS = juridTRS;
	}



	public BigDecimal getNroCertGanancias() {
		return nroCertGanancias;
	}



	public void setNroCertGanancias(BigDecimal nroCertGanancias) {
		this.nroCertGanancias = nroCertGanancias;
	}



	public BigDecimal getNroCertIngresoBrutos() {
		return nroCertIngresoBrutos;
	}



	public void setNroCertIngresoBrutos(BigDecimal nroCertIngresoBrutos) {
		this.nroCertIngresoBrutos = nroCertIngresoBrutos;
	}



	public BigDecimal getNroCertIva() {
		return nroCertIva;
	}



	public void setNroCertIva(BigDecimal nroCertIva) {
		this.nroCertIva = nroCertIva;
	}



	public BigDecimal getNroCertSegSoc() {
		return nroCertSegSoc;
	}



	public void setNroCertSegSoc(BigDecimal nroCertSegSoc) {
		this.nroCertSegSoc = nroCertSegSoc;
	}



	public BigDecimal getNroCertSIJP() {
		return nroCertSIJP;
	}



	public void setNroCertSIJP(BigDecimal nroCertSIJP) {
		this.nroCertSIJP = nroCertSIJP;
	}



	public BigDecimal getNroCertTRS() {
		return nroCertTRS;
	}



	public void setNroCertTRS(BigDecimal nroCertTRS) {
		this.nroCertTRS = nroCertTRS;
	}



	public int getNroRecibo() {
		return nroRecibo;
	}



	public void setNroRecibo(int nroRecibo) {
		this.nroRecibo = nroRecibo;
	}



	public String getTipoRetGanancias() {
		return tipoRetGanancias;
	}



	public void setTipoRetGanancias(String tipoRetGanancias) {
		this.tipoRetGanancias = tipoRetGanancias;
	}



	public String getTipoRetIngresoBrutos() {
		return tipoRetIngresoBrutos;
	}



	public void setTipoRetIngresoBrutos(String tipoRetIngresoBrutos) {
		this.tipoRetIngresoBrutos = tipoRetIngresoBrutos;
	}



	public String getTipoRetIva() {
		return tipoRetIva;
	}



	public void setTipoRetIva(String tipoRetIva) {
		this.tipoRetIva = tipoRetIva;
	}



	public String getTipoRetSegSoc() {
		return tipoRetSegSoc;
	}



	public void setTipoRetSegSoc(String tipoRetSegSoc) {
		this.tipoRetSegSoc = tipoRetSegSoc;
	}



	public String getTipoRetSIJP() {
		return tipoRetSIJP;
	}



	public void setTipoRetSIJP(String tipoRetSIJP) {
		this.tipoRetSIJP = tipoRetSIJP;
	}



	public String getTipoRetTRS() {
		return tipoRetTRS;
	}



	public void setTipoRetTRS(String tipoRetTRS) {
		this.tipoRetTRS = tipoRetTRS;
	}



	public ReciboRetencionTO() {
		// TODO Auto-generated constructor stub
	}



	public int getCodTISSH() {
		return codTISSH;
	}



	public void setCodTISSH(int codTISSH) {
		this.codTISSH = codTISSH;
	}



	public int getCodTMT() {
		return codTMT;
	}



	public void setCodTMT(int codTMT) {
		this.codTMT = codTMT;
	}



	public String getFechaCertTISSH() {
		return fechaCertTISSH;
	}



	public void setFechaCertTISSH(String fechaCertTISSH) {
		this.fechaCertTISSH = fechaCertTISSH;
	}



	public String getFechaTmTartagal() {
		return fechaTmTartagal;
	}



	public void setFechaTmTartagal(String fechaTmTartagal) {
		this.fechaTmTartagal = fechaTmTartagal;
	}



	public BigDecimal getImporteImpTMTartagal() {
		return importeImpTMTartagal;
	}



	public void setImporteImpTMTartagal(BigDecimal importeImpTMTartagal) {
		this.importeImpTMTartagal = importeImpTMTartagal;
	}



	public BigDecimal getImporteTISSH() {
		return importeTISSH;
	}



	public void setImporteTISSH(BigDecimal importeTISSH) {
		this.importeTISSH = importeTISSH;
	}



	public BigDecimal getNroCertTISSH() {
		return nroCertTISSH;
	}



	public void setNroCertTISSH(BigDecimal nroCertTISSH) {
		this.nroCertTISSH = nroCertTISSH;
	}



	



	public BigDecimal getNroCertTMTartagal() {
		return nroCertTMTartagal;
	}



	public void setNroCertTMTartagal(BigDecimal nroCertTMTartagal) {
		this.nroCertTMTartagal = nroCertTMTartagal;
	}



	public String getTipoRetTISSH() {
		return tipoRetTISSH;
	}



	public void setTipoRetTISSH(String tipoRetTISSH) {
		this.tipoRetTISSH = tipoRetTISSH;
	}



	public String getTipoRetTMT() {
		return tipoRetTMT;
	}



	public void setTipoRetTMT(String tipoRetTMT) {
		this.tipoRetTMT = tipoRetTMT;
	}



	public int getCodTEMTUc() {
		return codTEMTUc;
	}



	public void setCodTEMTUc(int codTEMTUc) {
		this.codTEMTUc = codTEMTUc;
	}



	public String getFecPercTEMTUc() {
		return fecPercTEMTUc;
	}



	public void setFecPercTEMTUc(String fecPercTEMTUc) {
		this.fecPercTEMTUc = fecPercTEMTUc;
	}



	public BigDecimal getImpPercTEMTUc() {
		return impPercTEMTUc;
	}



	public void setImpPercTEMTUc(BigDecimal impPercTEMTUc) {
		this.impPercTEMTUc = impPercTEMTUc;
	}



	public BigDecimal getNroPercTEMTUc() {
		return nroPercTEMTUc;
	}



	public void setNroPercTEMTUc(BigDecimal nroPercTEMTUc) {
		this.nroPercTEMTUc = nroPercTEMTUc;
	}



	public String getTipoRetTEMTUc() {
		return tipoRetTEMTUc;
	}



	public void setTipoRetTEMTUc(String tipoRetTEMTUc) {
		this.tipoRetTEMTUc = tipoRetTEMTUc;
	}

}
