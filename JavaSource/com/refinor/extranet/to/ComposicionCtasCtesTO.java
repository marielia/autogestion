package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class ComposicionCtasCtesTO implements Serializable {

	private BigDecimal recibidoACuentas;
	private BigDecimal vencidoA30Dias;
	private BigDecimal vencidoMas30Dias;
	private BigDecimal noVencidas;
	private BigDecimal saldoChequeNoCobrado;
	
	public BigDecimal getSaldoChequeNoCobrado() {
		return saldoChequeNoCobrado;
	}



	public void setSaldoChequeNoCobrado(BigDecimal saldoChequeNoCobrado) {
		this.saldoChequeNoCobrado = saldoChequeNoCobrado;
	}



	public ComposicionCtasCtesTO() {
		// TODO Auto-generated constructor stub
	}

	

	public BigDecimal getNoVencidas() {
		return noVencidas;
	}



	public void setNoVencidas(BigDecimal noVencidas) {
		this.noVencidas = noVencidas;
	}



	public BigDecimal getRecibidoACuentas() {
		return recibidoACuentas;
	}

	public void setRecibidoACuentas(BigDecimal recibidoACuentas) {
		this.recibidoACuentas = recibidoACuentas;
	}

	public BigDecimal getVencidoA30Dias() {
		return vencidoA30Dias;
	}

	public void setVencidoA30Dias(BigDecimal vencidoA30Dias) {
		this.vencidoA30Dias = vencidoA30Dias;
	}

	public BigDecimal getVencidoMas30Dias() {
		return vencidoMas30Dias;
	}

	public void setVencidoMas30Dias(BigDecimal vencidoMas30Dias) {
		this.vencidoMas30Dias = vencidoMas30Dias;
	}

}
