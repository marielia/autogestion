package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class DocumentoAplicadoTO implements Serializable {

	private String nombre;
	private String numero;
	private String fecha;
	private BigDecimal bruto;
	private BigDecimal saldo;
	private BigDecimal pagoAplicadoTotal;
	
	public BigDecimal getPagoAplicadoTotal() {
		return pagoAplicadoTotal;
	}

	public void setPagoAplicadoTotal(BigDecimal pagoAplicadoTotal) {
		this.pagoAplicadoTotal = pagoAplicadoTotal;
	}

	public DocumentoAplicadoTO() {
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getBruto() {
		return bruto;
	}

	public void setBruto(BigDecimal bruto) {
		this.bruto = bruto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
