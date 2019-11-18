package com.refinor.extranet.to;

import java.io.Serializable;
import java.util.List;

import com.refinor.extranet.data.Mclientes;

public class ClienteFacturaTO implements Serializable {

	
	private Mclientes cliente;
	private List lstFacturas;
	private int muestraFactura;
	
	private List lstResumenCtaCte;
	
	public ClienteFacturaTO() {
		muestraFactura=0;
	}
	public Mclientes getCliente() {
		return cliente;
	}
	public void setCliente(Mclientes cliente) {
		this.cliente = cliente;
	}
	public List getLstFacturas() {
		return lstFacturas;
	}
	public void setLstFacturas(List lstFacturas) {
		this.lstFacturas = lstFacturas;
	}
	public int getMuestraFactura() {
		return muestraFactura;
	}
	public void setMuestraFactura(int muestraFactura) {
		this.muestraFactura = muestraFactura;
	}
	public List getLstResumenCtaCte() {
		return lstResumenCtaCte;
	}
	public void setLstResumenCtaCte(List lstResumenCtaCte) {
		this.lstResumenCtaCte = lstResumenCtaCte;
	}
	
	
	
	
	
	

}
