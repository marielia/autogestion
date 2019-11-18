package com.refinor.extranet.faces.reportes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Transaction;

import com.itsolver.util.io.FileUtil;
import com.refinor.extranet.data.MlibroDiarioImpresion;
import com.refinor.extranet.data.MlibroDiarioPagina;
import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.dao.MasientoContableDAO;
import com.refinor.extranet.data.dao.MlibroDiarioImpresionDAO;
import com.refinor.extranet.data.dao.MlibroDiarioPaginaDAO;
import com.refinor.extranet.data.dao.MpedidosDAO;
import com.refinor.extranet.data.dao.VsubdiarioVentasDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.AnioTO;
import com.refinor.extranet.to.AsientoContableTO;
import com.refinor.extranet.to.MFacturaVTO;
import com.refinor.extranet.to.MLibroDiarioImpresionTO;
import com.refinor.extranet.to.MesTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.DataUtil;
import com.refinor.extranet.util.comparator.FacturaTOComparator;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.DatosObligatoriosException;
import com.refinor.extranet.util.exception.NoExistenItemsException;

public class RefReporteLibroDiarioBean extends AbstListado {
	private String nombreArchivoTxt;
	private String vieneDe;	
	private Long nroPaginaInicio ;
	private String periodoDeConsulta ; 
	
	
	public RefReporteLibroDiarioBean() {		
		
	}  


	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException ,Exception{
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);
		
	}
	
	/**
	 * Nombre: buscarDefinitivo
	 * Funcion: Busca la informacion para generar el archivo de texto definitivo del libro diario.
	 * Tiene un formato especial y guarda el registro en la base de datos para recuperar el nro de pagina
	 * @param event
	 */	public void buscarDefinitivo(ActionEvent event){
		this.nombreArchivo="";
		this.nombreArchivoTxt="";
		try{
			//validar fecha desde y fecha hasta  esten ingresadas
			if(this.fltFechaDesde==null || this.fltFechaHasta==null){
				throw new  DatosObligatoriosException(mensajeria.getMessage().getString("ingresar_fecha_msg"));
			}
			
			SimpleDateFormat anio = new SimpleDateFormat("yyyy");			
			SimpleDateFormat mes = new SimpleDateFormat("MM");			
			SimpleDateFormat dia = new SimpleDateFormat("dd");
			SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");	
			
			Calendar cal = GregorianCalendar.getInstance();				
			cal.set(Integer.parseInt(anio.format(fltFechaDesde)),Integer.parseInt(mes.format(fltFechaDesde)) - 1 , Integer.parseInt(dia.format(fltFechaDesde))); // Febrero 2006, los meses empiezan en 0.
			int ultimoDiaDelMes =  cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			int primerDiaDelMes =  cal.getActualMinimum(GregorianCalendar.DAY_OF_MONTH);
			String mesDesde= mes.format(fltFechaDesde);
			String diaDesde= primerDiaDelMes < 10 ? "0"+primerDiaDelMes:primerDiaDelMes+"";
			String anioDesde=anio.format(fltFechaDesde);				
			String mesHasta= mes.format(fltFechaHasta);
			String diaHasta= ultimoDiaDelMes < 10 ? "0"+ultimoDiaDelMes:ultimoDiaDelMes+"";
			String anioHasta=anio.format(fltFechaHasta);
			
			if(anio.format(fltFechaDesde).equals(anio.format(fltFechaHasta)) && 
					mes.format(fltFechaDesde).equals(mes.format(fltFechaHasta))){				
				if(fecha.format(fltFechaDesde).equals(diaDesde+"/"+mesDesde+"/"+anioDesde) &&
						fecha.format(fltFechaHasta).equals(diaHasta+"/"+mesHasta+"/"+anioHasta)){					
					//buscar el ultimo registro de impresion, para seguir con la numeración de paginas
					cargarDatosImpresionDefinitiva();
					generarArchivos(Const.IMPRESION_DEFINITVA_TXT);
				}else{
					throw new  NoExistenItemsException(mensajeria.getMessage().getString("ingresar_periodo_completo_msg")+" "+diaDesde+"/"+mesDesde+"/"+anioDesde+" al "+diaHasta+"/"+mesHasta+"/"+anioHasta  + ").");
				}
				
			}else{
				throw new  NoExistenItemsException(mensajeria.getMessage().getString("ingesar_periodo_valido_msg"));
			}
			
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
			
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
		}catch(DatosObligatoriosException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
		}
	}
	
	 /**
	  * Nombre: cargarDatosImpresionDefinitiva
	  * Funcion: Consultar los datos de la BD y obtener y nro de pagina inicial que le corresponde al archivo de texto
	  * generado.
	  * @throws NoExistenItemsException
	  */
	private void cargarDatosImpresionDefinitiva() throws NoExistenItemsException{
		try{
		MlibroDiarioImpresionDAO mImpresionDAO =  new MlibroDiarioImpresionDAO(getSessionHib());
		MLibroDiarioImpresionTO mImpresion =  new MLibroDiarioImpresionTO();
		mImpresion = mImpresionDAO.getUltimaImpresion();
		
		if(mImpresion!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/yyyy");
			//buscar el nro de pagina que debo usar
			nroPaginaInicio=mImpresion.getNroPaginaInicioActual();
			//System.out.println("nroPaginaInicio "+nroPaginaInicio);
			//buscar el periodo que debo comparar para ver si es el que corresponda
			periodoDeConsulta= mImpresion.getPeriodoActualConsulta();
			//System.out.println("periodoDeConsulta "+periodoDeConsulta);
			
			//08/2009 + 1 = 9/2009 <-esto deberia hbaer ingresado
			if(periodoDeConsulta!=null){
				String fechaDsd= sdf.format(fltFechaDesde);
				//System.out.println("fechaDsd "+fechaDsd);
				
				if(!periodoDeConsulta.equals(fechaDsd)){
					//es mayor el de BD al ingresado por teclado?					
					throw new NoExistenItemsException(mensajeria.getMessage().getString("ingesar_periodo_valido_v2_msg")+" "+periodoDeConsulta.substring(3, 10));					
				}	
			}else{
				periodoDeConsulta=sdf.format(this.fltFechaDesde);
				nroPaginaInicio=new Long(1);
			}
		}else{
			//para el primer registro asigno nro de pagina 1
			nroPaginaInicio=new Long(1);
		}
		
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Nombre: buscar
	 * Funcion: Genero la vista previa del libro diario
	 */
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		this.nombreArchivo="";
		this.nombreArchivoTxt="";
		try{	
			if(this.fltFechaDesde==null || this.fltFechaHasta==null){
				throw new  DatosObligatoriosException(mensajeria.getMessage().getString("ingresar_fecha_msg"));
			}
			
			generarArchivos(Const.VISTA_PREVIA_TXT);
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
	
	
	public void buscarVenta(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		this.nombreArchivo="";
		this.nombreArchivoTxt="";
		try{	
			if(this.fltFechaDesde==null || this.fltFechaHasta==null){
				throw new  DatosObligatoriosException(mensajeria.getMessage().getString("ingresar_fecha_msg"));
			}
			
			generarVenta(Const.VISTA_PREVIA_TXT);
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
	
	
	public void buscarAlicuota(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		this.nombreArchivo="";
		this.nombreArchivoTxt="";
		try{	
			if(this.fltFechaDesde==null || this.fltFechaHasta==null){
				throw new  DatosObligatoriosException(mensajeria.getMessage().getString("ingresar_fecha_msg"));
			}
			
			generarAlicuota(Const.VISTA_PREVIA_TXT);
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
	
	/**
	 * Nombre: buscarExcel
	 * funcion: Genero el archivo excel del libro diario
	 * @param event
	 * @throws NoExistenItemsException
	 * @throws DataAccessErrorException
	 * @throws Exception
	 */
	public void buscarExcel(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		this.nombreArchivo="";
		this.nombreArchivoTxt="";
		try{	
			if(this.fltFechaDesde==null || this.fltFechaHasta==null){
				throw new  DatosObligatoriosException(mensajeria.getMessage().getString("ingresar_fecha_msg"));
			}
			generarArchivos(Const.HOJA_DE_CALCULO_XLS);
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
	
	/**
	 * Nombre: generarArchivos
	 * funcion: Controla que se quiere generar: la vista previa en txt, archivo definitivo txt o un excel.
	 * @param vieneDe
	 * @throws NoExistenItemsException
	 * @throws DataAccessErrorException
	 */
	private void generarArchivos(String vieneDe)throws NoExistenItemsException,DataAccessErrorException{
		try{		
			MasientoContableDAO mAsientoContableDAO = new MasientoContableDAO(getSessionHib());				
			setItems(mAsientoContableDAO.getLibroDiario(fltFechaDesde,fltFechaHasta));	
			//setSubItemsNivel1(getItems());		
			//cargarPagina();	
			setVieneDe(vieneDe);
			if(vieneDe.equals(Const.VISTA_PREVIA_TXT) || vieneDe.equals(Const.IMPRESION_DEFINITVA_TXT)){
				generarArchivoTxt();			
			}else if(vieneDe.equals(Const.HOJA_DE_CALCULO_XLS)){
				generarExcel();
			}
			
			mostrarLista=new Boolean(true);
			mostrarFrmLista=new Boolean(true);
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;			
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			throw ex;		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();		
		}
	}
	
	private void generarVenta(String vieneDe)throws NoExistenItemsException,DataAccessErrorException{
		try{		
			MpedidosDAO mpedidosDAO = new MpedidosDAO(getSessionHib());				
			setItems(mpedidosDAO.getVenta(fltFechaDesde,fltFechaHasta));	
			//setSubItemsNivel1(getItems());		
			//cargarPagina();	
			setVieneDe(vieneDe);
			generarArchivoVentasTxt("Venta_");	
			
			mostrarLista=new Boolean(true);
			mostrarFrmLista=new Boolean(true);
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;			
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			throw ex;		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();		
		}
	}

		
	
	private void generarAlicuota(String vieneDe)throws NoExistenItemsException,DataAccessErrorException{
		try{		
			MpedidosDAO mpedidosDAO = new MpedidosDAO(getSessionHib());				
			setItems(mpedidosDAO.getAlicuota(fltFechaDesde,fltFechaHasta));	
			//setSubItemsNivel1(getItems());		
			//cargarPagina();	
			setVieneDe(vieneDe);
			generarArchivoVentasTxt("Alicuota_");			
			
			
			mostrarLista=new Boolean(true);
			mostrarFrmLista=new Boolean(true);
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;			
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			throw ex;		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();		
		}
	}

	
	/**
	 * Generar archivo excel para bajar a la maquia del usuario que requiere el archivo.
	 */
	public void generarExcel() {
		try{			
			//System.out.println("Paso por generar excel  !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();		
				SimpleDateFormat sdf= new SimpleDateFormat("MMyyyy");
				this.nombreArchivo = exportarExcelBean.generarExcelReporteLibroDiario(getItems(),sdf.format(this.fltFechaDesde),getTipoUsuarioLogueado(),this.fltFechaDesde,this.fltFechaHasta);	
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
	
	/**
	 * Generar archivo txt de la vista previa o definitivo para bajar a la maquia del usuario que requiere el archivo.
	 */
	public void generarArchivoTxt(){
				DataUtil datautil =  new DataUtil();
	   			   			
	   			FileUtil fileUtil= new FileUtil();
		   		Properties props= fileUtil.getPropertiesFile();				   		
		   		String path= props.getProperty("saveArchivosExcel");
		   		SimpleDateFormat sdf =  new SimpleDateFormat("MMyyyy");
		   		String nombreArchivoTxt1=mensajeria.getMessage().getString("archivo_txt_libro_diario_label")+sdf.format(this.fltFechaDesde)+".txt";
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
				AsientoContableTO asientoContableTO = new AsientoContableTO();
				String fila="";
				String encabezado="";
				encabezado = cargarTitulosArchivoTextSubdiario();				
				
				try {
					salida.write(fila);
					salida.newLine();
				} catch (IOException e) {							
					e.printStackTrace();
				}
					            
				DecimalFormat myFormatter = new DecimalFormat("###,##0.00");
				BigDecimal valorHaber= new BigDecimal(0.00);
				BigDecimal valorDebe= new BigDecimal(0.00);
				Integer contador=new Integer(1);
				Long nroPaginaInicio=new Long(0);
				
				if(vieneDe.equals(Const.VISTA_PREVIA_TXT)){
					nroPaginaInicio=new Long(1); //es un nro de pagina no oficial es simplemente orientativo					
				}else if(vieneDe.equals(Const.IMPRESION_DEFINITVA_TXT)){
//					BUSCAR EL NRO DE PAGINA DESDELA BASE DE DATOS - 
					nroPaginaInicio=this.nroPaginaInicio;					
				}
				
				//buscao la cantidad de linea que forma una pagina - mLibroDiarioPagina
				Integer cantidadLineasPorPagina=obtenerCantidadLineasPorPagina();
								
	   			while(itSel.hasNext()){	
	   				
	   				if(contador.equals(new Integer(1))){
	   				   fila=generarNroPagina(nroPaginaInicio)+encabezado;
	   				   contador=new Integer(8);	  
	   				   
	   				}	   				
	   				asientoContableTO = (AsientoContableTO)itSel.next();
	   				
	   				if(asientoContableTO.getDebHab()!=null){
		   				if( asientoContableTO.getDebHab().equals("D")){
		   					valorDebe = asientoContableTO.getValor();
		   					valorHaber= null;
		   				}else if(asientoContableTO.getDebHab().equals("H")){
		   					valorHaber = asientoContableTO.getValor();
		   					valorDebe= null;
		   				}
	   				}else{
	   					if(asientoContableTO.getUltimo().equals(new Integer(1))){
	   						valorDebe=asientoContableTO.getValorDebe();
	   						valorHaber=asientoContableTO.getValorHaber();
	   						fila=agregarLinea();
	   					    contador=contador+1;
	   					/* //System.out.println("fila "+fila);   
	   					 //System.out.println("contador "+contador);
						 //System.out.println("nroPagina "+nroPagina);*/
	   					    if(contador.equals(cantidadLineasPorPagina)){															
	   					    	nroPaginaInicio=nroPaginaInicio+1;								
								fila=fila+generarNroPagina(nroPaginaInicio)+encabezado;
								/* //System.out.println("contador "+contador);
								 //System.out.println("nroPagina "+nroPagina);
								 //System.out.println("fila "+fila);*/
				   				contador=new Integer(8);
				   				try {
									salida.write(fila);
									//salida.newLine();
								} catch (IOException e) {							
									e.printStackTrace();
								}
								fila="";
							}	   					 
	   					}
	   					
	   				}
	   				
	   				fila=fila+datautil.rellenarConBlancos(11, asientoContableTO.getFecha()==null? "":asientoContableTO.getFecha() ,false) 
   			        + datautil.rellenarConBlancos(4, asientoContableTO.getTipoComp()==null?"":asientoContableTO.getTipoComp(),false)  
   			        + datautil.rellenarConBlancos(3, asientoContableTO.getLetra()==null?"":asientoContableTO.getLetra(),false)  
  			        + datautil.rellenarConBlancos(5, asientoContableTO.getCcddId()==null?"":asientoContableTO.getCcddId().toString(),true)  
   			        + datautil.rellenarConBlancos(10, asientoContableTO.getNroEjercicio()==null?"":asientoContableTO.getNroEjercicio().toString(),true)  
   			        + datautil.rellenarConBlancos(8, asientoContableTO.getNroAsiento()==null?"":asientoContableTO.getNroAsiento().toString(),true)  
   			        + datautil.rellenarConBlancos(6,asientoContableTO.getUltimo()==null?datautil.rellenarConCeros(4, asientoContableTO.getNroSuc()==null?"": asientoContableTO.getNroSuc().toString()):"",true)   
   			        + datautil.rellenarConBlancos(8,asientoContableTO.getUltimo()==null?datautil.rellenarConCeros(8, asientoContableTO.getNroComp()==null?"": asientoContableTO.getNroComp().toString()):"",true)  +Const.SEPARADOR_ARCHIVO_2
	   			    + datautil.rellenarConBlancos(50, asientoContableTO.getLeyenda()==null?"":asientoContableTO.getLeyenda(),false)  
	   			    + datautil.rellenarConBlancos(13, asientoContableTO.getCuenta()==null?"":asientoContableTO.getCuenta().toString(),true)+Const.SEPARADOR_ARCHIVO_2         
	   			    + datautil.rellenarConBlancos(14,"",false)+Const.SEPARADOR_ARCHIVO_2         
	   			    + datautil.rellenarConBlancos(10,asientoContableTO.getIdCuenta()==null?"":asientoContableTO.getIdCuenta().toString(),true) +Const.SEPARADOR_ARCHIVO_2 
	   			    + datautil.rellenarConBlancos(50, asientoContableTO.getCuentaDesc(),false)  
	   			    + datautil.rellenarConBlancos(12, valorDebe==null? "" : myFormatter.format(valorDebe),true) +Const.SEPARADOR_ARCHIVO_2   
	   			    + datautil.rellenarConBlancos(12, valorHaber==null? "": myFormatter.format(valorHaber),true)  				   		      
   			;

	   				contador=contador+1;	
	   				
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
			   				contador=new Integer(8);
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
			   		if(vieneDe.equals(Const.IMPRESION_DEFINITVA_TXT)){
			   		   guardarLibroDiario(cantidadLineasPorPagina,this.nroPaginaInicio,nroPaginaInicio);
			   		}
			   		   
				     }		   		
				} catch (Exception e) {						
					e.printStackTrace();
					this.nombreArchivoTxt="";				
					this.AddErrorMessage(e.getMessage());
		   			//setErrorConfirmar();
		   			//return mensajeria.getMessage().getString("alta_datos");
				}
		   		
						
	
	}
	
	
	public void generarArchivoVentasTxt(String nombre){
		DataUtil datautil =  new DataUtil();
			   			
		FileUtil fileUtil= new FileUtil();
   		Properties props= fileUtil.getPropertiesFile();				   		
   		String path= props.getProperty("saveArchivosExcel");
   		SimpleDateFormat sdf =  new SimpleDateFormat("MMyyyy");
   		String nombreArchivoTxt1=nombre+sdf.format(this.fltFechaDesde)+".txt";
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
		String fila="";	
			            
		while(itSel.hasNext())
		{	
				fila= (String) itSel.next();
				
	   			try {
					salida.write(fila);
					salida.newLine();
				} catch (IOException e) {							
					e.printStackTrace();
				}				            
	   			
				fila="";   
				
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
	   		     		   
		     }		   		
		} catch (Exception e) {						
			e.printStackTrace();
			this.nombreArchivoTxt="";				
			this.AddErrorMessage(e.getMessage());
   			//setErrorConfirmar();
   			//return mensajeria.getMessage().getString("alta_datos");
		}
   		
				

}
	
	/**
	 * Nombre: guardarLibroDiario
	 * Funcion: guardar los datos de la impresion definitiva que se hizo ya que una vez generada no
	 *  se puede generar otra vez.
	 * @param cantidadLineasPaginas
	 * @param nroPaginaInicio
	 * @param nroPaginaFin
	 * @throws DataAccessErrorException
	 */
	private void guardarLibroDiario(Integer cantidadLineasPaginas,Long nroPaginaInicio,Long nroPaginaFin) throws DataAccessErrorException{
		try{
			MlibroDiarioImpresion mLibroDiario =  new MlibroDiarioImpresion();
			SimpleDateFormat sdfM= new SimpleDateFormat("MM");
			SimpleDateFormat sdfA= new SimpleDateFormat("yyyy");
			mLibroDiario.setMes(sdfM.format(this.fltFechaDesde));
			mLibroDiario.setAnio(sdfA.format(this.fltFechaDesde));
			mLibroDiario.setFechaAlta(new Date());
			mLibroDiario.setCantidadLineasPaginas(cantidadLineasPaginas);
			mLibroDiario.setNroPaginaInicio(nroPaginaInicio);
			mLibroDiario.setNroPaginaFin(nroPaginaFin);
			mLibroDiario.setCantidadPaginas((nroPaginaFin - nroPaginaInicio)+1);
			//usuario que genera el archivo
			MusuarioWeb musuarioWeb=(MusuarioWeb)getSessionValue("usuario");
			mLibroDiario.setUsuario(new Long(musuarioWeb.getId()));
			
			Transaction tr = null;			
			try{
				tr = getSessionHib().beginTransaction();
				MlibroDiarioImpresionDAO mLibroDiarioDAO =  new MlibroDiarioImpresionDAO(getSessionHib());
				mLibroDiarioDAO.save(mLibroDiario,getSessionHib());
				tr.commit();
			}catch(Exception ex){
				ex.printStackTrace();				
				tr.rollback();
				throw new DataAccessErrorException(mensajeria.getMessage().getString("error_genera_archivo_msg"));
				
			}
		}
		catch(DataAccessErrorException ex){
			ex.printStackTrace();
			throw ex;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
			
		}
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
		mPagina = mPaginaDAO.get(1, getSessionHib());
		return mPagina.getCantidadLineaPorPagina();
		}catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
		
	}
	
	/**
	 * Arma una linea con dos separadores de linea con guion para las columna de importe de debe y haber del informe.
	 * @return 
	 */
	private String agregarLinea(){
		DataUtil datautil =  new DataUtil();
			String fila=datautil.rellenarConBlancos(11, "",false) 
		        + datautil.rellenarConBlancos(4, "",false)  
		        + datautil.rellenarConBlancos(3, "",false) 
		        + datautil.rellenarConBlancos(5, "",true)  
		        + datautil.rellenarConBlancos(10, "",true)  
		        + datautil.rellenarConBlancos(8, "",true)  
		        + datautil.rellenarConBlancos(14,"",true)		        
			    + datautil.rellenarConBlancos(50, "",false)  
			    + datautil.rellenarConBlancos(13,"",true)  
			    + datautil.rellenarConBlancos(16,"",false)  
			    + datautil.rellenarConBlancos(10,"",false)  
			    + datautil.rellenarConBlancos(50, "",false)  
			    + datautil.rellenarConGuiones(14, "",true)  +Const.SEPARADOR_ARCHIVO_2  
			    + datautil.rellenarConGuiones(14, "",true)  + "\n"				   		      
		;
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
	 * Arma la primera linea del archivo definitivo.
	 * Contiene el nro de pagina del archivo definitivo o un nro de pagina no oficial si es vista previa.
	 * @param nroPagina
	 * @return
	 */
	private String generarNroPagina(Long nroPagina){
		DataUtil datautil =  new DataUtil();
		String encabezadoNroPagina="";
		if(vieneDe.equals(Const.VISTA_PREVIA_TXT)){
			encabezadoNroPagina=datautil.rellenarConBlancos(170, "",false)+mensajeria.getMessage().getString("vista_previa_nro_pagina_label")+" ";
		}else if(vieneDe.equals(Const.IMPRESION_DEFINITVA_TXT)){
			encabezadoNroPagina=datautil.rellenarConBlancos(200, "",false)+mensajeria.getMessage().getString("nro_pagina_label")+" ";
		}
		String fila=encabezadoNroPagina+datautil.rellenarConCeros(9,nroPagina.toString())+"\n";
		return fila;
	}
	
	/**
	 * Arma el encabezado del archivo definitivo a donde figura el nombre de la refineria, el mes de
	 *  proceso y fecha desde y hasta del proceso
	 * @return
	 */
	private String agregarEncabezado(){
		DataUtil datautil =  new DataUtil();
		SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
		SimpleDateFormat sdfAnio = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfformatoFecha = new SimpleDateFormat("dd/MM/yy");
		String mes = sdfMes.format(this.fltFechaDesde);
		mes = datautil.pasarAMes(mes);
		String anio = sdfAnio.format(this.fltFechaDesde);
		//el nro de pagina se busca de la tabla de la base de datos
		String fila=datautil.rellenarConBlancos(100, "",false)+mensajeria.getMessage().getString("refineria_norte_titulo")+"\n"
		            +datautil.rellenarConBlancos(100, "",false)+mensajeria.getMessage().getString("diario_titulo")+"\n"
		            +datautil.rellenarConBlancos(100, "",false)+ mes.toUpperCase() + " "+anio +"\n"   
		            +datautil.rellenarConBlancos(100, "",false)+mensajeria.getMessage().getString("proceso_titulo")+sdfformatoFecha.format(this.fltFechaDesde)+" al "+sdfformatoFecha.format(this.fltFechaHasta)
		;
		return fila;
	}
	
	/**
	 * Arma la linea con los nombre de las columnas del informe.
	 * @return
	 */
	private String cargarTitulosArchivoTextSubdiario(){
		DataUtil datautil = new DataUtil();
	String	fila = "";	
	
	fila= agregarEncabezado() + "\n"+
	     agregarLineaCompleta()+ "\n"	
		+datautil.rellenarConBlancos(11, mensajeria.getMessage().getString("fecha_libro_diario"),false) 	
        + datautil.rellenarConBlancos(7,mensajeria.getMessage().getString("tipo_doc_libro_diario"),false) 
        + datautil.rellenarConBlancos(5, mensajeria.getMessage().getString("centro_servicio_label"),true)  
        + datautil.rellenarConBlancos(10, mensajeria.getMessage().getString("ejercicio_libro_diario"),true)
        + datautil.rellenarConBlancos(8, mensajeria.getMessage().getString("asiento_libro_asiento"),true) 
        + datautil.rellenarConBlancos(14, mensajeria.getMessage().getString("nro_factuta_libro_diario"),true)+Const.SEPARADOR_ARCHIVO_2         
        + datautil.rellenarConBlancos(50, mensajeria.getMessage().getString("descripcion_aciento_contable_label"),false)        
        + datautil.rellenarConBlancos(13, mensajeria.getMessage().getString("cuenta_label"),true)+Const.SEPARADOR_ARCHIVO_2        
        + datautil.rellenarConBlancos(14, mensajeria.getMessage().getString("libro_auxiliar_libro_diario"),false)+Const.SEPARADOR_ARCHIVO_2     
        + datautil.rellenarConBlancos(10, mensajeria.getMessage().getString("cuenta_id_libro_diario"),false) 
        + datautil.rellenarConBlancos(50, mensajeria.getMessage().getString("descripcion_cuenta_label"),false) 
        + datautil.rellenarConBlancos(12, mensajeria.getMessage().getString("debe_label"),true) 
        + datautil.rellenarConBlancos(12, mensajeria.getMessage().getString("haber_label"),true) + "\n" +agregarLineaCompleta()+"\n"	;
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
	public String getVieneDe() {
		return vieneDe;
	}
	public void setVieneDe(String vieneDe) {
		this.vieneDe = vieneDe;
	}public Long getNroPaginaInicio() {
		return nroPaginaInicio;
	}public void setNroPaginaInicio(Long nroPaginaInicio) {
		this.nroPaginaInicio = nroPaginaInicio;
	}
	public String getPeriodoDeConsulta() {
		return periodoDeConsulta;
	}
	public void setPeriodoDeConsulta(String periodoDeConsulta) {
		this.periodoDeConsulta = periodoDeConsulta;
	}

}
