package com.refinor.extranet.faces.reportes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.event.ActionEvent;
import javax.mail.internet.MimeUtility;


import com.itsolver.util.io.FileUtil;
import com.refinor.extranet.data.MlibroDiarioPagina;
import com.refinor.extranet.data.dao.MfacturasVDAO;
import com.refinor.extranet.data.dao.MlibroDiarioImpresionDAO;
import com.refinor.extranet.data.dao.MlibroDiarioPaginaDAO;
import com.refinor.extranet.data.dao.VsubdiarioVentasDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.AsientoContableTO;
import com.refinor.extranet.to.MFacturaVTO;
import com.refinor.extranet.to.MLibroDiarioImpresionTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.DataUtil;
import com.refinor.extranet.util.comparator.FacturaTOComparator;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;

public class RefReporteSubdiarioVentas extends AbstListado {

	private String nombreArchivoTxt;
	private Long nroPaginaInicio ;
	private String periodoDeConsulta ; 
	public RefReporteSubdiarioVentas() {
		// TODO Auto-generated constructor stub
	}

	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException ,Exception{
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);
		
	}
	
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		try{			
			VsubdiarioVentasDAO vSubdiarioVentasDAO = new VsubdiarioVentasDAO(getSessionHib());			
			setItems(vSubdiarioVentasDAO.getSubDiarioPorClienteFecha2(cliente,fltFechaDesde,fltFechaHasta,condicion,ccss));	
			setItems2(vSubdiarioVentasDAO.getSubDiarioPorClienteFecha(cliente,fltFechaDesde,fltFechaHasta,condicion,ccss));	
				
			setSubItemsNivel1(getItems2());	
			//Collections.sort(getItems(), new FacturaTOComparator());
			cargarPagina();	
			//probar
			cargarDatosImpresionDefinitiva();			
			generarArchivoTxt();
			mostrarLista=new Boolean(true);
			mostrarFrmLista=new Boolean(true);
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
			
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
		}
	}
	
	

	@Override
	public void generarExcel() {
		try{			
			System.out.println("Paso por generar excel  !!");	
			if(getItems2()!=null && getItems2().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();		
				int codListado=1;
				this.nombreArchivo =  exportarExcelBean.generarExcelReporteSubdiarioVentas(getItems2(),getFechaActualStr(),getTipoUsuarioLogueado(),codListado);	
			}
		}catch(IOException ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}

	}
	
	private void cargarDatosImpresionDefinitiva() throws NoExistenItemsException{
		try{
		/*MlibroDiarioImpresionDAO mImpresionDAO =  new MlibroDiarioImpresionDAO(getSessionHib());
		MLibroDiarioImpresionTO mImpresion =  new MLibroDiarioImpresionTO();
		mImpresion = mImpresionDAO.getUltimaImpresion();
		
		if(mImpresion!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/yyyy");*/
			//buscar el nro de pagina que debo usar
			//nroPaginaInicio=mImpresion.getNroPaginaInicioActual();
			//System.out.println("nroPaginaInicio "+nroPaginaInicio);
			//buscar el periodo que debo comparar para ver si es el que corresponda
			//periodoDeConsulta= mImpresion.getPeriodoActualConsulta();
			//System.out.println("periodoDeConsulta "+periodoDeConsulta);
			
			//08/2009 + 1 = 9/2009 <-esto deberia hbaer ingresado
			/*if(periodoDeConsulta!=null){
				String fechaDsd= sdf.format(fltFechaDesde);
				//System.out.println("fechaDsd "+fechaDsd);
				
				if(!periodoDeConsulta.equals(fechaDsd)){
					//es mayor el de BD al ingresado por teclado?					
					throw new NoExistenItemsException(mensajeria.getMessage().getString("ingesar_periodo_valido_v2_msg")+" "+periodoDeConsulta.substring(3, 10));					
				}	
			}else{
				periodoDeConsulta=sdf.format(this.fltFechaDesde);*/
				nroPaginaInicio=new Long(1);
			//}
		/*}else{
			//para el primer registro asigno nro de pagina 1
			nroPaginaInicio=new Long(1);
		}*/
		
		}/*catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}*/catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	/**
	 * Generar archivo txt de la vista previa o definitivo para bajar a la maquia del usuario que requiere el archivo.
	 */
	public void generarArchivoTxt(){
				DataUtil datautil =  new DataUtil();
	   			   			
	   			FileUtil fileUtil= new FileUtil();
		   		Properties props= fileUtil.getPropertiesFile();				   		
		   		String path= props.getProperty("saveArchivosExcel");
		   		SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yy");
		   		String nombreArchivoTxt1=mensajeria.getMessage().getString("archivo_txt_subiario_venta_label")+getFechaActualStr()+".txt";
		   		String rutaArchivo=path +nombreArchivoTxt1; 	   		
		   		
	   			File f = new File(rutaArchivo);
	            FileWriter writer=null;
				try {
					writer = new FileWriter(f,false);						
				} catch (IOException e) {						
					e.printStackTrace();
				}
	            BufferedWriter salida = new BufferedWriter(writer);	            
	            List listaReg=getItems();    
	            
	            //armar la interfaz	
				Iterator itSel = listaReg.iterator();
				MFacturaVTO mFacturaVTO = new MFacturaVTO();
				//AsientoContableTO asientoContableTO = new AsientoContableTO();
				String fila="";
				String encabezado="";
				encabezado = cargarTitulosArchivoTextSubdiarioNuevoFormato();				
				
				try {
					salida.write(fila);
					salida.newLine();
				} catch (IOException e) {							
					e.printStackTrace();
				}
					      
				
				DecimalFormat myFormatter = new DecimalFormat("###,##0.00");
				DecimalFormatSymbols dfS = new DecimalFormatSymbols();
				dfS.setDecimalSeparator(',');
				dfS.setGroupingSeparator('.');
				myFormatter.setDecimalFormatSymbols(dfS);
				DecimalFormat myFormatterIva = new DecimalFormat("###,##0.000");
				myFormatterIva.setDecimalFormatSymbols(dfS);
				/*BigDecimal valorHaber= new BigDecimal(0.00);
				BigDecimal valorDebe= new BigDecimal(0.00);*/
				Integer contador=new Integer(1);
				Long nroPaginaInicio=new Long(0);
				
				
//					BUSCAR EL NRO DE PAGINA DESDELA BASE DE DATOS - 
				nroPaginaInicio=this.nroPaginaInicio;					
				
				//buscao la cantidad de linea que forma una pagina - mLibroDiarioPagina
				Integer cantidadLineasPorPagina=obtenerCantidadLineasPorPagina();
								
	   			while(itSel.hasNext()){	
	   				
	   				if(contador.equals(new Integer(1))){
	   				   fila=generarNroPagina(nroPaginaInicio)+encabezado;
	   				   contador=new Integer(7);	  
	   				   
	   				}	   				
	   				mFacturaVTO = (MFacturaVTO)itSel.next();
	   				
	   				
	   				Date fecha=new Date();
	   				try{
	   				 fecha = sdf.parse(mFacturaVTO.getFecha());
	   				}catch (Exception ex){
	   					ex.printStackTrace();
	   				}
	   				String tipoComp=obtenerTipoDocumento(mFacturaVTO.getTipoComprobante(),mFacturaVTO.getLetraComprobante());
	   				System.out.println("tipoComp "+tipoComp);
	   				String letra=mFacturaVTO.getLetraComprobante()==null?"":mFacturaVTO.getLetraComprobante();
	   				String suc= mFacturaVTO.getNroSucursal()==null?"":datautil.rellenarConCeros(4,mFacturaVTO.getNroSucursal().toString());
	   				String nrof=mFacturaVTO.getNroFactura()==null?"":datautil.rellenarConCeros(8,mFacturaVTO.getNroFactura().toString());
	   				int a = mFacturaVTO.getCliDescripcion().length();
	   				if(a<19) a=mFacturaVTO.getCliDescripcion().length();
	   				else
	   				         a=19;
	   				
	   				String primeroTresCol="";
	   				
	   				if(mFacturaVTO.getCliDescripcion()!=null){
	   					if(mFacturaVTO.getCliDescripcion().trim().contains("Total")){
	   						primeroTresCol = datautil.rellenarConBlancos(41, mFacturaVTO.getCliDescripcion(),true)
	   						+ datautil.rellenarConBlancos(4, " ",true)
	   						+ datautil.rellenarConBlancos(18, mFacturaVTO.getTipoDocuCli(),false);
	   					}else{
	   						primeroTresCol= Const.SEPARADOR_ARCHIVO_2
	   						 + datautil.rellenarConBlancos(9, mFacturaVTO.getFecha()==null? "":sdf.format(fecha) ,false) 
	      			         + datautil.rellenarConBlancos(16,tipoComp+letra+suc+nrof,false)  
	      			         + datautil.rellenarConBlancos(20, mFacturaVTO.getCliDescripcion().substring(0, a),false) 
	      			         + datautil.rellenarConBlancos(3, mFacturaVTO.getTipoDocuCli(),true)
	      			         + datautil.rellenarConBlancos(14, mFacturaVTO.getCuit()==null?"":mFacturaVTO.getCuit(),false);
	   					}
	   				}
	   				
	   				String ultimasColumnas="";
	   				
	   				if(mFacturaVTO.getCliDescripcion()!=null){
	   					if(!mFacturaVTO.getCliDescripcion().trim().contains("General")){
		   					ultimasColumnas=Const.SEPARADOR_ARCHIVO_2
		   			        + datautil.rellenarConBlancos(8, "",false) + Const.SEPARADOR_ARCHIVO_2	
					        + datautil.rellenarConBlancos(15, "",false)  + Const.SEPARADOR_ARCHIVO_2
					        + datautil.rellenarConBlancos(19, "",false)  + Const.SEPARADOR_ARCHIVO_2
					        + datautil.rellenarConBlancos(2, "",false)  + Const.SEPARADOR_ARCHIVO_2
					        + datautil.rellenarConBlancos(13, "",false)  + Const.SEPARADOR_ARCHIVO_2
					        + datautil.rellenarConGuiones(15, "",false)   + Const.SEPARADOR_ARCHIVO_2				        
					        + datautil.rellenarConGuiones(15, "",false)  + Const.SEPARADOR_ARCHIVO_2
					        + datautil.rellenarConGuiones(15, "",false)  + Const.SEPARADOR_ARCHIVO_2
					        + datautil.rellenarConBlancos(7, "",false)  + Const.SEPARADOR_ARCHIVO_2
					        + datautil.rellenarConGuiones(14, "",false)  + Const.SEPARADOR_ARCHIVO_2 
					        + datautil.rellenarConGuiones(14, "",false)  + Const.SEPARADOR_ARCHIVO_2
					        + datautil.rellenarConGuiones(15, "",false)  + Const.SEPARADOR_ARCHIVO_2  
					        + datautil.rellenarConGuiones(15, "",false)  + Const.SEPARADOR_ARCHIVO_2
					        + datautil.rellenarConGuiones(15, "",false) ;
	   					}
	   					
	   				}
	   				fila=fila	   				  
  			          + primeroTresCol  			         
   			          + datautil.rellenarConBlancos(14, myFormatter.format(mFacturaVTO.getTotal()),true)   			         
   			          + datautil.rellenarConBlancos(16,obtenerReturn(mFacturaVTO.getExento(), mFacturaVTO.getNetoGravado(),mFacturaVTO.getNetoNoGravado(),0),true)
   			          + datautil.rellenarConBlancos(16, obtenerReturn(mFacturaVTO.getExento(), mFacturaVTO.getNetoGravado(),mFacturaVTO.getNetoNoGravado(),1),true)
   			          + datautil.rellenarConBlancos(8,  mFacturaVTO.getPorcentajeIVA()==null?"":myFormatterIva.format(mFacturaVTO.getPorcentajeIVA()),true) 			         
   			          + datautil.rellenarConBlancos(15, mFacturaVTO.getIva()==null?"":myFormatter.format(mFacturaVTO.getIva()),true) 
   			          + datautil.rellenarConBlancos(15, "  ",true) 	
   			          + datautil.rellenarConBlancos(16, mFacturaVTO.getPerIVA()==null?"":myFormatter.format(mFacturaVTO.getPerIVA()),true) 
   			          + datautil.rellenarConBlancos(16, mFacturaVTO.getPerIIBB()==null?"":myFormatter.format(mFacturaVTO.getPerIIBB().add(mFacturaVTO.getLeyCba())),true) 
   			          + datautil.rellenarConBlancos(16, mFacturaVTO.getItc()==null?"":myFormatter.format(mFacturaVTO.getItc().add(mFacturaVTO.getTasaFondo()).add(mFacturaVTO.getCO2())),true) + "\n" 
   			        
   			         //guiones
   			        +ultimasColumnas
   			          				   		      
   			;
	   				//System.out.println("fila "+fila);
	   				contador=contador+2;	
	   				
			   			try {
							salida.write(fila);
							salida.newLine();
						} catch (IOException e) {							
							e.printStackTrace();
						}				            
			   			////System.out.println("Tamaï¿½o de fila --> "+fila.length()); 			
						fila="";
						
						if(contador.equals(cantidadLineasPorPagina)){							
							nroPaginaInicio=nroPaginaInicio+1;
							fila=generarNroPagina(nroPaginaInicio)+encabezado;
			   				contador=new Integer(7);
			   				try {
								salida.write(fila);
								//salida.newLine();
							} catch (IOException e) {							
								e.printStackTrace();
							}
							fila="";							
						}					    
						   
						
				}
				
	   			try {
	   				//GRABA INTERFAZ	   				
	   				salida.close();
				} catch (IOException e) {						
					e.printStackTrace();
				}			
					
				try {				
							
				////System.out.println("Datos a guardar fila completa "+fila);
					String[] result= fileUtil.crearArchivoVacio(rutaArchivo);			   		
			   		if(((String)result[0]).equals("SI") || ((String)result[0]).equals("EX")) {
			   		   this.nombreArchivoTxt = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombreArchivoTxt1;
			   		 // Process proceso = Runtime.getRuntime().exec("wordpad "+rutaArchivo);
			   		   // //System.out.println("nombreArchivoTxt "+nombreArchivoTxt);	
			   		   //guardar en la tabla
			   		/*if(vieneDe.equals(Const.IMPRESION_DEFINITVA_TXT)){
			   		   guardarLibroDiario(cantidadLineasPorPagina,this.nroPaginaInicio,nroPaginaInicio);
			   		}*/
			   		   
				     }		   		
				} catch (Exception e) {						
					e.printStackTrace();
					this.nombreArchivoTxt="";				
					this.AddErrorMessage(e.getMessage());
		   			//setErrorConfirmar();
		   			//return mensajeria.getMessage().getString("alta_datos");
				}
		   		
						
	
	}
	
	private String cargarTitulosArchivoTextSubdiarioNuevoFormato(){
		DataUtil datautil = new DataUtil();
	String	fila= 	agregarEncabezado() + "\n"
    	//+agregarLineaCompleta()+ "\n"	
    	+Const.SEPARADOR_ARCHIVO_2
    	+datautil.rellenarConBlancos(8, "Fecha",false) + Const.SEPARADOR_ARCHIVO_2	
        + datautil.rellenarConBlancos(15,"    Número",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(19, "",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(2, "",false) + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(13, "  Número",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(15, "",false)  + Const.SEPARADOR_ARCHIVO_2        
        + datautil.rellenarConBlancos(15, "",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(15, "",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(7, "",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(14, "     IVA",false)  + Const.SEPARADOR_ARCHIVO_2 
        + datautil.rellenarConBlancos(14, "    IVA",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(15, "     IVA",false)  + Const.SEPARADOR_ARCHIVO_2  
        + datautil.rellenarConBlancos(15,"    IVA",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(15, "  Impuestos",false) + "\n" 
        
        +Const.SEPARADOR_ARCHIVO_2
        +datautil.rellenarConBlancos(8, "Factura",false) + Const.SEPARADOR_ARCHIVO_2	
        + datautil.rellenarConBlancos(15,"    Factura",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(19, "   Razón Social",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(2, "TD",false) + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(13, "  de CUIT",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(15, "     Bruto",false)  + Const.SEPARADOR_ARCHIVO_2        
        + datautil.rellenarConBlancos(15, "    Gravado",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(15, "  No Gravado",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(7, " % IVA",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(14, "   Impuesto",false)  + Const.SEPARADOR_ARCHIVO_2 
        + datautil.rellenarConBlancos(14, "No Inscripto",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(15, "  Percepción",false)  + Const.SEPARADOR_ARCHIVO_2  
        + datautil.rellenarConBlancos(15," Perc. IIBB.",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConBlancos(15, "  ITC",false) + "\n" 

        +Const.SEPARADOR_ARCHIVO_2
        +datautil.rellenarConGuiones(8, "",false) + Const.SEPARADOR_ARCHIVO_2	
        + datautil.rellenarConGuiones(15, "",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConGuiones(19, "",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConGuiones(2, "",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConGuiones(13, "",false)  + Const.SEPARADOR_ARCHIVO_2
        + datautil.rellenarConGuiones(15, "",false)   + Const.SEPARADOR_ARCHIVO_2        
        + datautil.rellenarConGuiones(15, "",false)  + Const.SEPARADOR_ARCHIVO_2
        +  datautil.rellenarConGuiones(15, "",false)  + Const.SEPARADOR_ARCHIVO_2
        +  datautil.rellenarConGuiones(7, "",false)  + Const.SEPARADOR_ARCHIVO_2
        +  datautil.rellenarConGuiones(14, "",false)  + Const.SEPARADOR_ARCHIVO_2 
        +  datautil.rellenarConGuiones(14, "",false)  + Const.SEPARADOR_ARCHIVO_2
        +  datautil.rellenarConGuiones(15, "",false)  + Const.SEPARADOR_ARCHIVO_2  
        +  datautil.rellenarConGuiones(15, "",false)  + Const.SEPARADOR_ARCHIVO_2
        +  datautil.rellenarConGuiones(15, "",false) + "\n" 
     ;
	return fila;
	}
	private String obtenerReturn(int excento,BigDecimal valorGravado,BigDecimal valorNoGravado,int dis){
		BigDecimal valorRetorno=new BigDecimal("0.00");
		DecimalFormat myFormatter = new DecimalFormat("###,##0.00");
		DecimalFormatSymbols dfS = new DecimalFormatSymbols();
		dfS.setDecimalSeparator(',');
		dfS.setGroupingSeparator('.');
		myFormatter.setDecimalFormatSymbols(dfS);
		
		if(excento==0 && dis==0){
			valorRetorno =valorGravado;
		}else if(excento==1 && dis==1){
			valorRetorno =valorNoGravado;
		}else if(excento==2 && dis==0){
			valorRetorno =valorGravado;
		}else if(excento==2 && dis==1){
			valorRetorno =valorNoGravado;
		}

		return myFormatter.format(valorRetorno) ;
		
	}
	private String obtenerTipoDocumento(String tipo, String letra){
		String tipoDocumentoNro="";
		//(01- Factura A ; 02 - ND A ; 03 - NC A;  06- Fact B ; 07 - ND  B ; 08- NC		B)

		if(tipo!=null && letra!=null){
			if(letra.trim().toUpperCase().equals("A")){
				if(tipo.trim().toUpperCase().equals("FC")){
					tipoDocumentoNro="01";
				}else if(tipo.trim().toUpperCase().equals("ND")){
					tipoDocumentoNro="02";
				}else if(tipo.trim().toUpperCase().equals("NC")){
					tipoDocumentoNro="03";
				}
			}else if(letra.trim().toUpperCase().equals("B")){
				if(tipo.trim().toUpperCase().equals("FC")){
					tipoDocumentoNro="06";
				}else if(tipo.trim().toUpperCase().equals("ND")){
					tipoDocumentoNro="07";
				}else if(tipo.trim().toUpperCase().equals("NC")){
					tipoDocumentoNro="08";
				}
			}
		}
		return tipoDocumentoNro;
	}
	
	private String generarNroPagina(Long nroPagina){
		DataUtil datautil =  new DataUtil();
		String encabezadoNroPagina="";
		
		encabezadoNroPagina=datautil.rellenarConBlancos(179, "",false)+"Nro. Página "+" ";
		
		String fila=encabezadoNroPagina+ nroPagina.toString()+"\n"; //+ datautil.rellenarConCeros(9,nroPagina.toString())+"\n";
		return fila;
	}
	
	/**
	 * Nombre: obtenerCantidadLineasPorPagina
	 * Funcion: Busca en la pagina cuantas lineas forman una pagina de impresion.
	 * El valor está en una tabla MlibroDiarioPagina.
	 * @return nro de lineas por pagina
	 */
	private Integer obtenerCantidadLineasPorPagina(){
		try{
		MlibroDiarioPaginaDAO mPaginaDAO = new MlibroDiarioPaginaDAO(getSessionHib());
		MlibroDiarioPagina mPagina = new MlibroDiarioPagina();
		mPagina = mPaginaDAO.get(2, getSessionHib());
		return mPagina.getCantidadLineaPorPagina();
		}catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
		
	}
	
	
	
	/**/
	public void generarArchivoTxt2(){
				DataUtil datautil =  new DataUtil();
	   			   			
	   			FileUtil fileUtil= new FileUtil();
		   		Properties props= fileUtil.getPropertiesFile();				   		
		   		String path= props.getProperty("saveArchivosExcel");
		   		String nombreArchivoTxt1=mensajeria.getMessage().getString("archivo_txt_subiario_venta_label")+getFechaActualStr()+".txt";
		   		String rutaArchivo=path +nombreArchivoTxt1; 	   		
		   		
	   			File f = new File(rutaArchivo);
	            FileWriter writer=null;
				try {
					writer = new FileWriter(f,false);						
				} catch (IOException e) {						
					e.printStackTrace();
				}
	            BufferedWriter salida = new BufferedWriter(writer);	            
	            List listaReg=getItems();
	            //armar la interfaz	
				Iterator itSel = listaReg.iterator();
				MFacturaVTO mFacturaVTO = new MFacturaVTO();
				String fila="";

				fila = cargarTitulosArchivoTextSubdiario();	
				//fila = cargarTitulosArchivoTextSubdiarioNuevoFormato();
				
				try {
					salida.write(fila);
					salida.newLine();
				} catch (IOException e) {							
					e.printStackTrace();
				}
					            
				DecimalFormat myFormatter = new DecimalFormat("###,##0.00");
	   			while(itSel.hasNext()){	   				
	   				mFacturaVTO = (MFacturaVTO)itSel.next();									
			   			fila= 	datautil.rellenarConBlancos(8, mFacturaVTO.getFecha(),false) + Const.SEPARADOR_ARCHIVO
			   			        + datautil.rellenarConBlancos(8, mFacturaVTO.getTipo(),false)  + Const.SEPARADOR_ARCHIVO
			   			        + datautil.rellenarConBlancos(12, mFacturaVTO.getLetraComprobante(),false)  + Const.SEPARADOR_ARCHIVO
			   			        + datautil.rellenarConBlancos(11, mFacturaVTO.getCodClienteAlfa(),false)  + Const.SEPARADOR_ARCHIVO
			   			        + datautil.rellenarConBlancos(10, mFacturaVTO.getCuit(),false)  + Const.SEPARADOR_ARCHIVO
			   			        + datautil.rellenarConBlancos(55, mFacturaVTO.getCliDescripcion(),false)  + Const.SEPARADOR_ARCHIVO
			   			        + datautil.rellenarConBlancos(30, mFacturaVTO.getCcss(),false)  + Const.SEPARADOR_ARCHIVO
			   			        + datautil.rellenarConBlancos(7, mFacturaVTO.getNroSucursal().toString(),true)  + Const.SEPARADOR_ARCHIVO
			   			     + datautil.rellenarConBlancos(6, mFacturaVTO.getNroFactura().toString(),true)  + Const.SEPARADOR_ARCHIVO
			   			+  datautil.rellenarConBlancos(15,(mFacturaVTO.getExento()==0 ?  myFormatter.format(mFacturaVTO.getNetoGravado())  : "0,00"),true) + Const.SEPARADOR_ARCHIVO 
			   			+  datautil.rellenarConBlancos(15, (mFacturaVTO.getExento()==1 ? myFormatter.format(mFacturaVTO.getNetoNoGravado())  : "0,00"),true) + Const.SEPARADOR_ARCHIVO 
			   			+ datautil.rellenarConBlancos(11, myFormatter.format(mFacturaVTO.getIva()),true)  + Const.SEPARADOR_ARCHIVO  
			   			+ datautil.rellenarConBlancos(11, myFormatter.format(mFacturaVTO.getIva2()),true)  + Const.SEPARADOR_ARCHIVO
			   			+ datautil.rellenarConBlancos(11, myFormatter.format(mFacturaVTO.getOtros()),true)  + Const.SEPARADOR_ARCHIVO
			   			+ datautil.rellenarConBlancos(16, myFormatter.format(mFacturaVTO.getImpuestoInterno()),true)  + Const.SEPARADOR_ARCHIVO
			   			+ datautil.rellenarConBlancos(11, myFormatter.format(mFacturaVTO.getTasaFondo()),true)  + Const.SEPARADOR_ARCHIVO
			   			+ datautil.rellenarConBlancos(11, myFormatter.format(mFacturaVTO.getItc()),true)  + Const.SEPARADOR_ARCHIVO
			   			+ datautil.rellenarConBlancos(11, myFormatter.format(mFacturaVTO.getPerIVA()),true)  + Const.SEPARADOR_ARCHIVO
			   			+ datautil.rellenarConBlancos(11, myFormatter.format(mFacturaVTO.getPerIIBB()),true)  + Const.SEPARADOR_ARCHIVO
			   			+ datautil.rellenarConBlancos(11, myFormatter.format(mFacturaVTO.getTotal()),true)  + Const.SEPARADOR_ARCHIVO
			   			+ datautil.rellenarConBlancos(10, mFacturaVTO.getTipoPagoFactura().toString(),true)  
			   			;
			   			try {
							salida.write(fila);
							salida.newLine();
						} catch (IOException e) {							
							e.printStackTrace();
						}				            
			   			//System.out.println("Tamaï¿½o de fila --> "+fila.length());   			
					   			
				}
				
	   			try {
	   				//GRABA INTERFAZ
	   				salida.close();
				} catch (IOException e) {						
					e.printStackTrace();
				}			
					
				try {				
							
				//System.out.println("Datos a guardar fila completa "+fila);
					String[] result= fileUtil.crearArchivoVacio(rutaArchivo);			   		
			   		if(((String)result[0]).equals("SI") || ((String)result[0]).equals("EX")) {
			   		   this.nombreArchivoTxt = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombreArchivoTxt1;
			   		 // Process proceso = Runtime.getRuntime().exec("wordpad "+rutaArchivo);
			   		   // System.out.println("nombreArchivoTxt "+nombreArchivoTxt);	
				     }		   		
				} catch (Exception e) {						
					e.printStackTrace();
					this.nombreArchivoTxt="";
					System.out.println(mensajeria.getMessage().getString("no_se_grabo_datos_msg")+"(BD)");	 
					this.AddErrorMessage(mensajeria.getMessage().getString("no_se_grabo_datos_msg")+"(BD)");
		   			//setErrorConfirmar();
		   			//return mensajeria.getMessage().getString("alta_datos");
				}
		   		
						
	
	}
	
	public void generarArchivoTxt(ActionEvent event){	
		try{
			FileUtil fileUtil= new FileUtil();
			Properties props= fileUtil.getPropertiesFile();				   		
	   		String path= props.getProperty("saveArchivosExcel");
	   		String nombreArchivoTxt1=mensajeria.getMessage().getString("archivo_txt_subiario_venta_label")+getFechaActualStr()+".txt";
	   		String rutaArchivo=path +nombreArchivoTxt1; 
		    Process proceso = Runtime.getRuntime().exec("wordpad.exe \""+rutaArchivo+"\"");
		
		    
	        
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	private String cargarTitulosArchivoTextSubdiario(){
		DataUtil datautil = new DataUtil();
	String	fila= 	datautil.rellenarConBlancos(10, mensajeria.getMessage().getString("fecha_label").toUpperCase(),false) + Const.SEPARADOR_ARCHIVO	
        + datautil.rellenarConBlancos(8,mensajeria.getMessage().getString("tipo_doc_label"),false).toUpperCase()  + Const.SEPARADOR_ARCHIVO
        + datautil.rellenarConBlancos(12, mensajeria.getMessage().getString("tipo_comprobante_label"),false).toUpperCase()  + Const.SEPARADOR_ARCHIVO
        + datautil.rellenarConBlancos(8, mensajeria.getMessage().getString("cod_cliente_Alfa"),false).toUpperCase()  + Const.SEPARADOR_ARCHIVO
        + datautil.rellenarConBlancos(11, mensajeria.getMessage().getString("cuit_label"),false).toUpperCase()  + Const.SEPARADOR_ARCHIVO
        + datautil.rellenarConBlancos(55, mensajeria.getMessage().getString("cliente_label"),false).toUpperCase()  + Const.SEPARADOR_ARCHIVO
        + datautil.rellenarConBlancos(30, mensajeria.getMessage().getString("centro_servicio_label"),false).toUpperCase()  + Const.SEPARADOR_ARCHIVO
        + datautil.rellenarConBlancos(7, mensajeria.getMessage().getString("prefijo_label"),true).toUpperCase()  + Const.SEPARADOR_ARCHIVO
     + datautil.rellenarConBlancos(6, mensajeria.getMessage().getString("numero_label"),true).toUpperCase()  + Const.SEPARADOR_ARCHIVO
+  datautil.rellenarConBlancos(15, mensajeria.getMessage().getString("neto_gravado_label"),true).toUpperCase()  + Const.SEPARADOR_ARCHIVO 
+  datautil.rellenarConBlancos(15, mensajeria.getMessage().getString("neto_no_gravado_label"),true).toUpperCase()  + Const.SEPARADOR_ARCHIVO
+ datautil.rellenarConBlancos(11, mensajeria.getMessage().getString("iva_label"),true).toUpperCase()  + Const.SEPARADOR_ARCHIVO  
+ datautil.rellenarConBlancos(11,mensajeria.getMessage().getString("iva2_label"),true).toUpperCase()  + Const.SEPARADOR_ARCHIVO
+ datautil.rellenarConBlancos(11, mensajeria.getMessage().getString("otros_label"),true).toUpperCase()  + Const.SEPARADOR_ARCHIVO
+ datautil.rellenarConBlancos(16, mensajeria.getMessage().getString("impuesto_interno_label"),true).toUpperCase()  + Const.SEPARADOR_ARCHIVO
+ datautil.rellenarConBlancos(11, mensajeria.getMessage().getString("tasa_fondo_label"),true).toUpperCase()  + Const.SEPARADOR_ARCHIVO
+ datautil.rellenarConBlancos(11, mensajeria.getMessage().getString("itc_label"),true).toUpperCase()  + Const.SEPARADOR_ARCHIVO
+ datautil.rellenarConBlancos(11, mensajeria.getMessage().getString("per_iva_label"),true).toUpperCase()  + Const.SEPARADOR_ARCHIVO
+ datautil.rellenarConBlancos(11, mensajeria.getMessage().getString("per_iibb_label"),true).toUpperCase()  + Const.SEPARADOR_ARCHIVO
+ datautil.rellenarConBlancos(11, mensajeria.getMessage().getString("total_sin_signo_label"),true).toUpperCase() + Const.SEPARADOR_ARCHIVO
+ datautil.rellenarConBlancos(10, mensajeria.getMessage().getString("condicion_label"),true).toUpperCase() + "\n" +agregarLineaCompleta()+"\n";
	return fila;
	}
	
	
	/**
	 * Arma una linea completa de guiones que separa el nombre de las columnas y los datos
	 * @return
	 */
	private String agregarLineaCompleta(){
		DataUtil datautil =  new DataUtil();
		String fila=datautil.rellenarConGuiones(223, "",false) ;
		return fila;
	}
	
	
	
	/**
	 * Arma el encabezado del archivo definitivo a donde figura el nombre de la refineria, el mes de
	 *  proceso y fecha desde y hasta del proceso
	 * @return
	 */
	private String agregarEncabezado(){
		DataUtil datautil =  new DataUtil();		
		SimpleDateFormat sdfformatoFecha = new SimpleDateFormat("dd/MM");
		String desde = "";
		String hasta = "";
		if(this.fltFechaDesde!=null && !this.fltFechaDesde.toString().equals("")){
			desde="desde "+ sdfformatoFecha.format(this.fltFechaDesde);
		}
		
		if(this.fltFechaHasta!=null && !this.fltFechaHasta.toString().equals("")){
			hasta=" hasta "+ sdfformatoFecha.format(this.fltFechaHasta);
		}
		//el nro de pagina se busca de la tabla de la base de datos
		String fila=datautil.rellenarConBlancos(88, "",false)+mensajeria.getMessage().getString("refineria_norte_titulo")+" -"+"\n"
		            +datautil.rellenarConBlancos(83, "",false)+"IVA VENTAS-  C.U.I.T: 30-65823369-2"+"\n"		             
		            +datautil.rellenarConBlancos(86, "",false)+"Periodo: "+desde+hasta
		;
		return fila;
	}
	
	@Override
	public Boolean getPuedeIngresar() {
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}

	public String getNombreArchivoTxt() {
		return nombreArchivoTxt;
	}

	public void setNombreArchivoTxt(String nombreArchivoTxt) {
		this.nombreArchivoTxt = nombreArchivoTxt;
	}
	

}
