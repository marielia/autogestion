/**
 * 
 */
package com.refinor.extranet.util.paginado;

import java.util.List;

/**
 * @author snieto
 *
 */
public class Paginacion {
	private Integer tamanoPagina;
	private Integer paginaActual;
	private Integer totalRegistros;
	private Integer cantidadPaginasTotal;
	private List listaDatos;
	
	public Paginacion(Integer tamanoPagina,Integer paginaActual,List listaDatos){
		this.tamanoPagina = tamanoPagina;
		this.paginaActual = paginaActual;
		this.listaDatos = listaDatos;		 
	}
	
	/**
	 * @return the cantidadPaginasTotal
	 */
	public Integer getCantidadPaginasTotal() {		
		return cantidadPaginasTotal;
	}
	/**
	 * @param cantidadPaginasTotal the cantidadPaginasTotal to set
	 */
	public void setCantidadPaginasTotal(Integer cantidadPaginasTotal) {
		this.cantidadPaginasTotal = cantidadPaginasTotal;
	}
	/**
	 * @return the listaDatos
	 */
	public List getListaDatos() {
		return listaDatos;
	}
	/**
	 * @param listaDatos the listaDatos to set
	 */
	public void setListaDatos(List listaDatos) {
		this.listaDatos = listaDatos;
	}
	/**
	 * @return the paginaActual
	 */
	public Integer getPaginaActual() {
		return paginaActual;
	}
	/**
	 * @param paginaActual the paginaActual to set
	 */
	public void setPaginaActual(Integer paginaActual) {
		this.paginaActual = paginaActual;
	}
	/**
	 * @return the tamanoPagina
	 */
	public Integer getTamanoPagina() {
		return tamanoPagina;
	}
	/**
	 * @param tamanoPagina the tamanoPagina to set
	 */
	public void setTamanoPagina(Integer tamanoPagina) {
		this.tamanoPagina = tamanoPagina;
	}
	/**
	 * @return the totalRegistros
	 */
	public Integer getTotalRegistros() {
		return totalRegistros;
	}
	/**
	 * @param totalRegistros the totalRegistros to set
	 */
	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
}
