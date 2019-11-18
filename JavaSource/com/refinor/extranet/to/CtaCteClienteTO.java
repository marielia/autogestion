package com.refinor.extranet.to;

import java.io.Serializable;
import java.text.DecimalFormat;

public class CtaCteClienteTO implements Serializable {
	
	private String cliente;
	private int codCLiente;
	private String codClienteAlfa;

	private String strLimiteCreditoCliente;
	private double dblLimiteCreditoCliente;
	
	private String totalFacturadoDeMFactV;
	private String totalFacturadoCompleto;
	private String totalFacturadoDeMCtaCte;
	private String difTotalFacturado;
	
	private String totalReciboDeMRec;
	private String totalReciboCompleto;
	private String totalReciboMctacte;
	private String difTotalRec;
	private String totalFacturaMenosReciboMfacMrec;
	private String totalFacturaMenosReciboCompleto;
	private String totalFacturaMenosReciboMCTaCte;
	
	private String totalRemitoMpedido;
	private String totalRemitoCtaCte;
	private String difRemitoMpedidoVsCtaCte;
	
	private String saldoCompleto;
	private String saldo;	
	private String difSaldo;
	
	
	private String disponibleCompleto;
	private String disponible;
	
	private String saldoPorComprobanteCliente;

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getSaldoCompleto() {
		return saldoCompleto;
	}

	public void setSaldoCompleto(String saldoCompleto) {
		this.saldoCompleto = saldoCompleto;
	}

	public CtaCteClienteTO( String cliente,int codCLiente,String codClienteAlfa, double dblLimiteCreditoCliente){
		this.cliente = cliente;
		this.codCLiente = codCLiente;
		this.codClienteAlfa = codClienteAlfa;	
		this.dblLimiteCreditoCliente = dblLimiteCreditoCliente;
		DecimalFormat format = new DecimalFormat("#.##"); 
		this.strLimiteCreditoCliente = format.format(this.dblLimiteCreditoCliente); 
	}
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public int getCodCLiente() {
		return codCLiente;
	}
	public void setCodCLiente(int codCLiente) {
		this.codCLiente = codCLiente;
	}
	public String getCodClienteAlfa() {
		return codClienteAlfa;
	}
	public void setCodClienteAlfa(String codClienteAlfa) {
		this.codClienteAlfa = codClienteAlfa;
	}

	


	public String getDifTotalRec() {
		return difTotalRec;
	}

	public void setDifTotalRec(String difTotalRec) {
		this.difTotalRec = difTotalRec;
	}

	public String getTotalFacturaMenosReciboCompleto() {
		return totalFacturaMenosReciboCompleto;
	}

	public void setTotalFacturaMenosReciboCompleto(
			String totalFacturaMenosReciboCompleto) {
		this.totalFacturaMenosReciboCompleto = totalFacturaMenosReciboCompleto;
	}

	public String getTotalFacturaMenosReciboMfacMrec() {
		return totalFacturaMenosReciboMfacMrec;
	}

	public void setTotalFacturaMenosReciboMfacMrec(
			String totalFacturaMenosReciboMfacMrec) {
		this.totalFacturaMenosReciboMfacMrec = totalFacturaMenosReciboMfacMrec;
	}

	public String getTotalReciboCompleto() {
		return totalReciboCompleto;
	}

	public void setTotalReciboCompleto(String totalReciboCompleto) {
		this.totalReciboCompleto = totalReciboCompleto;
	}

	public String getTotalReciboDeMRec() {
		return totalReciboDeMRec;
	}

	public void setTotalReciboDeMRec(String totalReciboDeMRec) {
		this.totalReciboDeMRec = totalReciboDeMRec;
	}

	public String getTotalFacturadoCompleto() {
		return totalFacturadoCompleto;
	}

	public void setTotalFacturadoCompleto(String totalFacturadoCompleto) {
		this.totalFacturadoCompleto = totalFacturadoCompleto;
	}

	public String getTotalFacturadoDeMFactV() {
		return totalFacturadoDeMFactV;
	}

	public void setTotalFacturadoDeMFactV(String totalFacturadoDeMFactV) {
		this.totalFacturadoDeMFactV = totalFacturadoDeMFactV;
	}

	public String getDifTotalFacturado() {
		return difTotalFacturado;
	}

	public void setDifTotalFacturado(String difTotalFacturado) {
		this.difTotalFacturado = difTotalFacturado;
	}

	public String getDifRemitoMpedidoVsCtaCte() {
		return difRemitoMpedidoVsCtaCte;
	}

	public void setDifRemitoMpedidoVsCtaCte(String difRemitoMpedidoVsCtaCte) {
		this.difRemitoMpedidoVsCtaCte = difRemitoMpedidoVsCtaCte;
	}

	public String getTotalRemitoCtaCte() {
		return totalRemitoCtaCte;
	}

	public void setTotalRemitoCtaCte(String totalRemitoCtaCte) {
		this.totalRemitoCtaCte = totalRemitoCtaCte;
	}

	public String getTotalRemitoMpedido() {
		return totalRemitoMpedido;
	}

	public void setTotalRemitoMpedido(String totalRemitoMpedido) {
		this.totalRemitoMpedido = totalRemitoMpedido;
	}

	public String getDifSaldo() {
		return difSaldo;
	}

	public void setDifSaldo(String difSaldo) {
		this.difSaldo = difSaldo;
	}

	

	public double getDblLimiteCreditoCliente() {
		return dblLimiteCreditoCliente;
	}

	public void setDblLimiteCreditoCliente(double dblLimiteCreditoCliente) {
		this.dblLimiteCreditoCliente = dblLimiteCreditoCliente;
	}

	public String getStrLimiteCreditoCliente() {
		return strLimiteCreditoCliente;
	}

	public void setStrLimiteCreditoCliente(String strLimiteCreditoCliente) {
		this.strLimiteCreditoCliente = strLimiteCreditoCliente;
	}

	public String getDisponible() {
		return disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}

	public String getDisponibleCompleto() {
		return disponibleCompleto;
	}

	public void setDisponibleCompleto(String disponibleCompleto) {
		this.disponibleCompleto = disponibleCompleto;
	}

	public String getTotalFacturadoDeMCtaCte() {
		return totalFacturadoDeMCtaCte;
	}

	public void setTotalFacturadoDeMCtaCte(String totalFacturadoDeMCtaCte) {
		this.totalFacturadoDeMCtaCte = totalFacturadoDeMCtaCte;
	}

	public String getTotalReciboMctacte() {
		return totalReciboMctacte;
	}

	public void setTotalReciboMctacte(String totalReciboMctacte) {
		this.totalReciboMctacte = totalReciboMctacte;
	}

	public String getTotalFacturaMenosReciboMCTaCte() {
		return totalFacturaMenosReciboMCTaCte;
	}

	public void setTotalFacturaMenosReciboMCTaCte(
			String totalFacturaMenosReciboMCTaCte) {
		this.totalFacturaMenosReciboMCTaCte = totalFacturaMenosReciboMCTaCte;
	}

	public String getSaldoPorComprobanteCliente() {
		return saldoPorComprobanteCliente;
	}

	public void setSaldoPorComprobanteCliente(String saldoPorComprobanteCliente) {
		this.saldoPorComprobanteCliente = saldoPorComprobanteCliente;
	}
	

}
