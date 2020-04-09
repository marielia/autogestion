package com.refinor.extranet.to;

import java.io.Serializable;

public class MTurnoTO implements Serializable {

	private int codTurno;
	private String descTurno;
	private int codSector;
	private int codTurnoVigencia;
	private int codEstadoTurno;
	private int codAlmacen;
	private String descAlmacen;
	 
	
	public MTurnoTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCodTurno() {
		return codTurno;
	}
	public void setCodTurno(int codTurno) {
		this.codTurno = codTurno;
	}
	 
	public int getCodSector() {
		return codSector;
	}
	public void setCodSector(int codSector) {
		this.codSector = codSector;
	}
	public int getCodTurnoVigencia() {
		return codTurnoVigencia;
	}
	public void setCodTurnoVigencia(int codTurnoVigencia) {
		this.codTurnoVigencia = codTurnoVigencia;
	}
	public int getCodEstadoTurno() {
		return codEstadoTurno;
	}
	public void setCodEstadoTurno(int codEstadoTurno) {
		this.codEstadoTurno = codEstadoTurno;
	}
	public int getCodAlmacen() {
		return codAlmacen;
	}
	public void setCodAlmacen(int codAlmacen) {
		this.codAlmacen = codAlmacen;
	}
	public String getDescTurno() {
		return descTurno;
	}
	public void setDescTurno(String descTurno) {
		this.descTurno = descTurno;
	}
	public String getDescAlmacen() {
		return descAlmacen;
	}
	public void setDescAlmacen(String descAlmacen) {
		this.descAlmacen = descAlmacen;
	}
	 
	
	 

}
