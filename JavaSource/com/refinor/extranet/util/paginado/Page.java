package com.refinor.extranet.util.paginado;
import java.io.Serializable;
import java.util.*;

import javax.faces.model.SelectItem;


public class Page implements Serializable {
    private Integer pageSize, numpage, lastPage, totalElements,hasta;
    private java.util.List results;
    private java.util.List page;  
    private Integer nroRegistroDesde;
    private Integer nroRegistroHasta;
    /**
     * constructor 
     *
     */
    public Page(){
    	this.hasta=0;
        this.totalElements = 0;
        this.results = null;	        
        this.numpage = 0;	      
        this.lastPage = 1;	        
        this.pageSize = pageSize;	       
        this.page= null;
    }
    
/**
 * 
 * @param results
 * @param numpage
 * @param pageSize
 * @param totalElements
 */
    public Page(List results, int numpage, int pageSize) {
    	this.hasta=0;
    	//cantidad total de registros
        this.totalElements = results.size(); 
    	//listade datos a paginar completa
        this.results = new ArrayList();
        this.results.addAll(results);
        //pagina 1..2..3
        this.numpage = numpage;
        //ultima pagina
        this.lastPage = getUltimaPagina(totalElements, pageSize);
        //tamaño de la pagina - cant de registros
        this.pageSize = pageSize;
        // cargar la pagina
        this.page= getPagina(numpage);
    }

    public java.util.List getResults() {
	return results;
}

public void setResults(java.util.List results) {
	this.results = results;
}

	/**
     * Devuelve el numero (posicion) del elementos inicial de una pagina dada.
     * A partir del elementos devuelto hay que pintar nPageSize elementos más.
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static int getStartItemByPage(int currentPage, int pageSize) {
        return Math.max((pageSize * (currentPage - 1)) + 1, 1);
        // El Math.max sirve para evitar los numeros negativos, siempre hay
        // algun malvado que puede pedirnos la pagina -10. En esos casos,
        // la pagina devuelta sera 1
    }

    public static int getUltimaPagina(int totalElements, int pageSize) {
        int base = totalElements / pageSize;
        int mod = totalElements % pageSize;
        return base + (mod > 0?1:0);
    }
    
    public List getPagina(int numpage) {    	
        int desde = getStartItemByPage(numpage, pageSize)-1;
        hasta = (desde + pageSize)-1;
        List pagina= new ArrayList();
        System.out.println("desde "+desde);
        System.out.println("hasta "+hasta);
        
        this.nroRegistroDesde=desde+1;
        this.nroRegistroHasta=desde+pageSize;
        
        if(this.nroRegistroHasta.compareTo(totalElements)>0){
        	this.nroRegistroHasta=totalElements;
        }
        
        for(int i=desde;i<this.results.size();i++){
        	if(i>=desde && i<=hasta){
        		pagina.add(this.results.get(i));
        	}
    	}
        
        return pagina;
        
    }
    

	public Integer getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}

	public java.util.List getPage() {
		return page;
	}

	public Integer getHasta() {
		return hasta;
	}

	public void setHasta(Integer hasta) {
		this.hasta = hasta;
	}

	public Integer getLastPage() {
		return lastPage;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	public Integer getNumpage() {
		return numpage;
	}

	public void setNumpage(Integer numpage) {
		this.numpage = numpage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setPage(java.util.List page) {
		this.page = page;
	}

	public Integer getNroRegistroDesde() {
		return nroRegistroDesde;
	}

	public void setNroRegistroDesde(Integer nroRegistroDesde) {
		this.nroRegistroDesde = nroRegistroDesde;
	}

	public Integer getNroRegistroHasta() {
		return nroRegistroHasta;
	}

	public void setNroRegistroHasta(Integer nroRegistroHasta) {
		this.nroRegistroHasta = nroRegistroHasta;
	}

}
