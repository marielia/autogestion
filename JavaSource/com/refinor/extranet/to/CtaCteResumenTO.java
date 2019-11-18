package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

import com.refinor.extranet.data.Mclientes;

public class CtaCteResumenTO implements Serializable{
	private Mclientes cliente;
	private BigDecimal montoTotalDisponibleCcSs ;
	private BigDecimal montoTotalDisponibleWeb ;
	private BigDecimal montoTotalFacturaCcSs ;
	private BigDecimal montoTotalFacturaWeb ;
	private BigDecimal montoTotalRemitosNoFactCcSs ;
	private BigDecimal montoTotalRemitosNoFactWeb ;
	private BigDecimal montoTotalRecibosCcSs ;
	private BigDecimal montoTotalRecibosWeb ;
	private BigDecimal montoTotalPagosNoAplicadosCcSs ;
	private BigDecimal montoTotalPagosNoAplicadosWeb ;
}
