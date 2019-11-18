/**
 * 
 */
package com.refinor.extranet.util.paginado;

import java.util.List;

import org.hibernate.Query;

/**
 * @author snieto
 *
 */
public class Pagina {

		   private List resultados;
		   private int tamanoPagina;
		   private int paginaActual;
		   
		   
		   public Pagina(Query query, int paginaActual, int tamanoPagina) {
		       
		       this.paginaActual = paginaActual;
		       this.tamanoPagina = tamanoPagina;
		       resultados = query.setFirstResult(paginaActual * tamanoPagina)
		           .setMaxResults(tamanoPagina+1)
		           .list();
		   
		   }
		   
		   public boolean isNextPage() {
		       return resultados.size() > tamanoPagina;
		   }
		   
		   public boolean isPreviousPage() {
		       return paginaActual > 0;
		   }
		   
		   public List getList() {
		       return isNextPage() ?  resultados.subList(0, tamanoPagina-1) :  resultados;
		   }	   
		   

		/**
		 * @return the paginaActual
		 */
		public int getPaginaActual() {
			return paginaActual;
		}

		/**
		 * @param paginaActual the paginaActual to set
		 */
		public void setPaginaActual(int paginaActual) {
			this.paginaActual = paginaActual;
		}

		/**
		 * @return the resultados
		 */
		public List getResultados() {
			return resultados;
		}

		/**
		 * @param resultados the resultados to set
		 */
		public void setResultados(List resultados) {
			this.resultados = resultados;
		}

		/**
		 * @return the tamanoPagina
		 */
		public int getTamanoPagina() {
			return tamanoPagina;
		}

		/**
		 * @param tamanoPagina the tamanoPagina to set
		 */
		public void setTamanoPagina(int tamanoPagina) {
			this.tamanoPagina = tamanoPagina;
		}



}
