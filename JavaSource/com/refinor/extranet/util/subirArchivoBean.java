package com.refinor.extranet.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.apache.poi.hssf.dev.HSSF;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itsolver.util.io.FileUtil;
import com.refinor.extranet.data.Marticulos;
import com.refinor.extranet.data.Mautorizacion;
import com.refinor.extranet.data.MautorizacionCodigos;
import com.refinor.extranet.data.MautorizacionMotivo;
import com.refinor.extranet.data.Mccss;
import com.refinor.extranet.data.Mchofer;
import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.MdescripcionMotivoAutorizacion;
import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.Mvehiculo;
import com.refinor.extranet.data.Mvendedor;
import com.refinor.extranet.data.TipoComprobante;
import com.refinor.extranet.data.dao.AlmacenDAO;
import com.refinor.extranet.data.dao.MarticulosDAO;
import com.refinor.extranet.data.dao.MautorizacionCodigosDAO;
import com.refinor.extranet.data.dao.MautorizacionDAO;
import com.refinor.extranet.data.dao.MautorizacionMotivoDAO;
import com.refinor.extranet.data.dao.MccssDAO;
import com.refinor.extranet.data.dao.MchoferDAO;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MdescripcionMotivoAutorizacionDAO;
import com.refinor.extranet.data.dao.MvehiculoDAO;
import com.refinor.extranet.data.dao.MvendedorDAO;
import com.refinor.extranet.data.dao.TipoComprobanteDAO;
import com.refinor.extranet.faces.base.AbstBackingBean;
import com.refinor.extranet.to.MAutorizacionTO;
import com.refinor.extranet.to.MEmpleadosTO;
import com.refinor.extranet.util.exception.ArchivoIncorrectoException;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.DatosObligatoriosException;
import com.refinor.extranet.util.exception.ItemsYaExistenException;
import com.refinor.extranet.util.paginado.Page;

public class subirArchivoBean extends AbstBackingBean {
	private UploadedFile  url;
	private String  rutaArchivoABaja;
	protected Session sessionHib;
	private Messages mensajeria;	
		
	private Integer cliente;	
	private Integer motivoAutorizacion;
	private Integer descripcionMotAutorizacion;
	private Integer chofer;
	private Integer patente;
	private Integer tipoCompManual;	
	private Integer nroSucManual;
	private Integer nroRemitoManual;
	private Integer producto;
	private String litrosACargar;
	private Integer tipoCompAnular;	
	private Integer nroSucAnular;
	private Integer nroRemitoAnular;
	private MautorizacionCodigos codigo;
	private Date fechaPedido;	
	private Integer vendedor;
	private List<SelectItem> vendedores;	
	private Integer ccss;	
	private List<SelectItem> lstccss;
	private Boolean nuevoVendedor;
	private String apellidoVendedor;
	private String nombreVendedor;
	

	
		

	public subirArchivoBean(){
		try{
			sessionHib = (new AlmacenDAO()).getSession();	
			mensajeria = new Messages();			
			rutaArchivoABaja = "";
			this.nuevoVendedor= new Boolean(false);
			this.vendedor=-1;
			this.ccss=-1;
			cargarComboVendedor();
			cargarComboCCSS();
			
			}catch(Exception ex){
				ex.printStackTrace();
				AddErrorMessage(ex.getMessage());
			}
	}
	
	/**
	 * Metodo :cargarComboVendedor
	 * Funcon: Cargcar el combo de vendedores
	 *
	 */
	private void cargarComboVendedor(){
		 MvendedorDAO mVendedorDAO = new MvendedorDAO(sessionHib);
		 vendedores = new ArrayList<SelectItem>();
		 vendedores.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 
		try {
			MEmpleadosTO mVendedor;			
			List lstDatos = mVendedorDAO.getVendedorRegistrados();		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mVendedor = (MEmpleadosTO)it.next();
				vendedores.add(new SelectItem(new Integer(mVendedor.getCodigo()),mVendedor.getDescripcionApe()+", "+mVendedor.getDescripcion()));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los vendedores.");
		}	
		
	}
	
	/**
	 * Metodo:cargarComboCCSS
	 * Funcion: cargar el combo de centros de servicios
	 *
	 */
	private void cargarComboCCSS(){
		 MccssDAO mCcssDAO = new MccssDAO(sessionHib);
		 lstccss = new ArrayList<SelectItem>();
		 lstccss.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 
		try {
			MEmpleadosTO  mCcss;
			List lstDatos = mCcssDAO.getCCSSRegistrados();		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mCcss = (MEmpleadosTO)it.next();
				lstccss.add(new SelectItem(new Integer(mCcss.getCodigo()),mCcss.getDescripcion()));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los ccss.");
		}	
		
	}
	
	public void subirArchivoE(String rutaAlmacenamientoArchivo){
		try{
			 //GUARDO EL ARCHIVO QUE ME SUBIO EL USUARIO
			//hago una copìa del archivo que el usuario quiere procesar.
	        //rutaAlmacenamientoArchivo: directorio a donde guardo el archivo que sube el usuario
	        File descriptorEntrada = new File(rutaAlmacenamientoArchivo);	        
	        FileOutputStream fileoutstream = new FileOutputStream(descriptorEntrada);
	        //el contenido es un arreglo de bytes que copio al archivo creado
	        fileoutstream.write(url.getBytes()); 
	        fileoutstream.close(); 
		}  catch(Exception x){  
			AddErrorMessage(x.getMessage());
	    }
	}
	
	
	public String enviar(){
		 try
	       {/* System.out.println("GOAL");
	          FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getExternalContext().getApplicationMap().put("fileupload_bytes",    url.getBytes());
	          facesContext.getExternalContext().getApplicationMap().put("fileupload_type",   url.getContentType());
	          facesContext.getExternalContext().getApplicationMap().put("fileupload_name",  url.getName());
	          System.out.println("url.getBytes() ->"+url.getBytes());
	          System.out.println("fileupload_type ->"+url.getContentType());
	          System.out.println("fileupload_name ->"+url.getName());*/
			 
			 //VALIDAR QUE EL ARCHIVO SUBIDO SEA UN XLS			 
			
			 
			 //vliadar Vendedor
			 if(this.ccss.equals( new Integer(-1))){
				 throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
			 }
			 
			 if(!this.nuevoVendedor){
					if(	this.vendedor.equals(new Integer(-1))) 	{
						throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
					}				
			}
			 
			
				
			if(this.nuevoVendedor){				
				if(	this.apellidoVendedor==null || this.apellidoVendedor.trim().equals("") ||
						this.nombreVendedor==null || this.nombreVendedor.trim().equals("") ) 	{
						throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
				}else{
					this.vendedor = guardarVendedor();
				}
			}
			
			
			 if(this.url==null){
				throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));								
			}
			 
			 String extension=url.getName();			 
			 extension =  extension.substring(extension.length()-3, extension.length());
			 System.out.println(extension);
			 if(!extension.trim().toUpperCase().equals("xls".toUpperCase())){
				throw new ArchivoIncorrectoException("Archivo invalido. El archivo seleccionado debe tener extension XLS."); 
			 }
				
			 
			 //1. COPIO EL ARCHIVO QUE ME PASO EL USUARIO
			 SimpleDateFormat  sdf = new SimpleDateFormat("ddMMyyyyHHmm");
			 String fechaHoy = sdf.format(new Date());
			 String fechaCCSSVen=fechaHoy+"_"+this.ccss;
			 
			 FileUtil fileUtil= new FileUtil();
			 Properties props= fileUtil.getPropertiesFile();
			 String rutaArchivoEntrada = props.getProperty("saveArchivosExcel");
			 String nombreArchivoEntrada="Comprobantes_a_Autorizar_"+fechaCCSSVen+".xls";
			 subirArchivoE(rutaArchivoEntrada+nombreArchivoEntrada);
	          
			
				
	          //CARGO EL ARCHIVO QUE ME SUBIO EL USUARIO	         
	          HSSF1 archivoAProcesar = new HSSF1();
	          HSSFWorkbook hojaAProcesar = archivoAProcesar.getWorkbook(rutaArchivoEntrada+nombreArchivoEntrada);
	          HSSFSheet sheet = hojaAProcesar.getSheetAt(0);
	          HSSFRow row;
	          HSSFCell cell;	         
	          String valorCelda="";
	          String filaProcesada="";          
	          String celdaErrorCodigoAutorizacion="";
	          String apellido="";
	          
	          //set up file and stream
		      //File descriptorSalida = new File(getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+"pruebaRefinorSalida.xls");
	          String nombreArchivoSalida="Comprobantes_Autorizados_"+fechaCCSSVen+".xls";
	          File descriptorSalida = new File(rutaArchivoEntrada+nombreArchivoSalida);
		      FileOutputStream outFileStream  = new FileOutputStream(descriptorSalida);
		      PrintWriter outStream = new PrintWriter(outFileStream);		     
	          //System.out.println("fila inicio "+sheet.getFirstRowNum());
	          //System.out.println("fila fin "+sheet.getLastRowNum());
	          
	          //con j manejo las filas
		      for (int j=sheet.getFirstRowNum(); j<=sheet.getLastRowNum();j++){
		          row=sheet.getRow(j);
		          
		          
		         // System.out.println("celda inicio "+row.getFirstCellNum());
		         // System.out.println("celda fin "+row.getLastCellNum());
		       
			          for(int indiceCelda=Const.COLUMNA_COD_CLIENTE_ALFA;indiceCelda<=Const.COLUMNA_NRO_AUTORIZACION;indiceCelda++){	
			        	  cell=row.getCell((short)indiceCelda);
			        	  
			        	  if(cell!=null){
					          switch(cell.getCellType()){		
						          case HSSFCell.CELL_TYPE_FORMULA:
						        	  valorCelda = valorCelda + cell.getNumericCellValue();
							          Double amount = new Double(valorCelda);
							          NumberFormat numberFormatter;
							          numberFormatter = NumberFormat.getNumberInstance();
							          valorCelda = numberFormatter.format(amount);
						          break;
					
						          case HSSFCell.CELL_TYPE_NUMERIC:					        	  
						        		  valorCelda=valorCelda+cell.getNumericCellValue();
								          Double amounts = new Double(valorCelda);
								          NumberFormat numberFormatters;
								          numberFormatters = NumberFormat.getNumberInstance();
								          valorCelda = numberFormatters.format(amounts);
								          valorCelda=valorCelda.replace(".", "");
						        	  
						          break;
						          
						          case HSSFCell.CELL_TYPE_STRING:
						        	  valorCelda=valorCelda+cell.getStringCellValue();				          
						          break;
						          
						          case HSSFCell.CELL_TYPE_BLANK:
						        	  valorCelda="";				          
						          break;			
					          }
			        	  }
					      
					      System.out.println("valorCelda 1  "+valorCelda);
					      
					      if(j>0 && /*indiceCelda!=3 && */indiceCelda!=Const.COLUMNA_APELLIDO_CHOFER){ 
					    	  
					    	  if(/*indiceCelda==4 ||*/ indiceCelda==Const.COLUMNA_NOMBRE_CHOFER){					    		  
					    		  celdaErrorCodigoAutorizacion = celdaErrorCodigoAutorizacion + procesarCadaCelda(indiceCelda,apellido.trim()+"-"+valorCelda.trim());
					    	  }else if(indiceCelda!=Const.COLUMNA_NRO_AUTORIZACION){
						          //u es el indice de la celda
						          celdaErrorCodigoAutorizacion = celdaErrorCodigoAutorizacion + procesarCadaCelda(indiceCelda,valorCelda);
					      	   }
					    	  
					    	  
					          if(indiceCelda==Const.COLUMNA_NRO_AUTORIZACION){
					        	  if(celdaErrorCodigoAutorizacion.trim().equals("")){
					        		  //genero el codigo de autorizacion en procesa celda
					        		  valorCelda = procesarCadaCeldaCodAutorizacion(); //codigoautorizacion; 
					        	  }else{
					        		  valorCelda = celdaErrorCodigoAutorizacion;
					        	  }
					          }
					          apellido = "";   
					      } else if(/*indiceCelda==3 ||*/ indiceCelda==Const.COLUMNA_APELLIDO_CHOFER){
					    	  //guardo el apellido del vendedor para concatenar el nombre despues
					    	  apellido = valorCelda;
					    	  
					      }
				          //copio de nuevo la descripcion que tiene la celda					      
					      if(j==0 && indiceCelda==Const.COLUMNA_NRO_AUTORIZACION){
					    	  valorCelda="Nro de Autorizacion";
					      }
				          filaProcesada = filaProcesada+";" + valorCelda;
				          valorCelda="";  
				        
				          if(j>0 && indiceCelda==Const.COLUMNA_NRO_AUTORIZACION){ 
					          // A ESTE NIVEL DEBERIA GUARDAR LA FILA EN LA BASE 
					          //Y GUARDAR EN EL ARCHIVO A BAJAR				          
					          if(celdaErrorCodigoAutorizacion.trim().equals("")) {				        	 
					        	  //si la celda esta vacia, inserto los datos en mAutorizacion
					        	  guardarAutorizacion();
					        	  System.out.println("Archivo Guardado");
					          }	
				          }
				  }
		        
		          filaProcesada= filaProcesada.substring(1, filaProcesada.length());
		          System.out.println("filaProcesada  "+filaProcesada);
		          outStream.println(filaProcesada); 
		          filaProcesada="";
		          celdaErrorCodigoAutorizacion="";
		        }
		      
			    outStream.close();		          
			    rutaArchivoABaja=getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombreArchivoSalida;
			    return null;
		        
	          /*HSSFRow row=sheet.getRow(17);
	          Date p=null;
	          HSSFCell cell=row.getCell((short)1);
	          p=cell.getDateCellValue();
	          SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
	          String fecha = formatDate.format(p);
	          System.out.println("p"+ fecha);*/

	        
	        
//	        set up file and stream
	     /*   File descriptorSalida = new File("c:\\pruebaRefinorSalida.xls");
	      	FileOutputStream outFileStream  = new FileOutputStream(descriptorSalida);
	      	PrintWriter outStream = new PrintWriter(outFileStream);*/

	     
	      	/*for(int i=0;i<5;i++){
	      	 	//write values of primitive data types to the stream
		      	//escribo una linea en archivo
	      		outStream.println("987654321;silvana;EROFGGGG;"+i);     	
	      	}

	      	//output done, so close the stream
	      	outStream.close();*/    
	      	//return "ver_documento";         

	      } 
	      catch(DatosObligatoriosException x){
				 x.printStackTrace();
				 AddErrorMessage(x.getMessage());
				 return null;
			 }
	      catch(ArchivoIncorrectoException x){
			 x.printStackTrace();
			 AddErrorMessage(x.getMessage());
			 return null;
		 }catch(Exception x){    x.printStackTrace();
	    	 AddErrorMessage(new DataAccessErrorException().getMessage());
	         return null;
	      }

	    }
	
	public void guardarAutorizacion() throws DatosObligatoriosException, ParseException{
		Transaction tx= null;
		try{

			//validaciones de datos obligatorios
			/*if(		this.ccss.equals(new Integer(-1)) ||
					this.cliente.equals(new Integer(-1)) ||
					this.motivoAutorizacion.equals(new Integer(-1)) ||
					this.descripcionMotAutorizacion.equals(new Integer(-1)) ||
					this.chofer.equals(new Integer(-1)) ||
					this.patente.equals(new Integer(-1)) ||
					this.producto.equals(new Integer(-1)) ||					
					this.tipoCompManual==null) 	{
				throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
			}	
			
			if(this.tipoCompManual.equals(6) || this.tipoCompManual.equals(7)){
				if(	this.nroRemitoUnoAnular==null ||
					this.nroRemitoDosAnular==null ) 	{
					throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
				}	
				
			}*/
			
			
			
			
			Mautorizacion mAutorizacion = new Mautorizacion();	
			mAutorizacion.setCodVendedor(this.vendedor);
			mAutorizacion.setCodCcss(this.ccss);
			mAutorizacion.setCodCliente(this.cliente);
			mAutorizacion.setCodMotivo(this.motivoAutorizacion);	
			mAutorizacion.setDescripcion("");
			mAutorizacion.setCodDescripcion(this.descripcionMotAutorizacion);
			mAutorizacion.setCodChofer(this.chofer);
			mAutorizacion.setCodVehiculo(this.patente);
			
			
			if(this.nroRemitoManual!=null && this.nroSucManual!=null){				
				mAutorizacion.setSucursalManual(this.nroSucManual);
				mAutorizacion.setNroRemito1(this.nroRemitoManual);				
				//verificar que no este registrado en Mautorizacion
				//validarExistenciaRemito(this.nroRemitoUnoManual,this.nroRemitoDosManual);
			}
			
			if(this.litrosACargar!=null && !this.litrosACargar.trim().equals("")) {
				if(this.litrosACargar.contains(",")){
					this.litrosACargar= this.litrosACargar.replace(',', '.');
				}				
				mAutorizacion.setLitrosAcargar(new BigDecimal(this.litrosACargar));
			}				
				
			mAutorizacion.setProducto(this.producto);	
			mAutorizacion.setTipoComprobanteManual(this.tipoCompManual);		
			
			
			if(this.nroRemitoAnular!=null && this.nroSucAnular!=null){				
				mAutorizacion.setNroRemito2(this.nroRemitoAnular);
				mAutorizacion.setSucursalAnular(nroSucAnular);
				
				//PREGUNTAR
				if(this.tipoCompManual.equals(new Integer(6))){
					//si es nota de credito, le asocio factura
					mAutorizacion.setTipoComprobanteAnular(new Integer(1));
				}else if(this.tipoCompManual.equals(new Integer(7))){
					//si es remito credito, le asocio remito
					mAutorizacion.setTipoComprobanteAnular(new Integer(2));					
				}
			}
						
			SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaHoy = sdf.format(new Date());
			mAutorizacion.setFecha(sdf.parse(fechaHoy));			
			mAutorizacion.setFechaPedido(sdf.parse(fechaHoy));			
			mAutorizacion.setIdCodigoAuto(this.codigo.getCodigo());
			
			MusuarioWeb musuarioWeb=(MusuarioWeb)getSessionValue("usuario");
			mAutorizacion.setCodEmisor(musuarioWeb.getId());			
			
			try {
				tx= sessionHib.beginTransaction();
					MautorizacionDAO mAautorizacionDAO = new MautorizacionDAO(sessionHib);
					mAautorizacionDAO.save(mAutorizacion, sessionHib);					
				tx.commit();							
				System.out.println(mensajeria.getMessage().getString("registro_ok_autorizacion_msg")+" "+this.codigo.getNroAuto());
				//pantalla=2;
			} catch(Exception excep) {
				excep.printStackTrace();
				tx.rollback();
				throw new DataAccessErrorException();
			}
			
		}/*catch(DatosObligatoriosException ex){
			ex.printStackTrace();		
			AddErrorMessage(ex.getMessage());
		}catch(ItemsYaExistenException ex){
			ex.printStackTrace();		
			AddErrorMessage(ex.getMessage());
		}*/catch(DataAccessErrorException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(new DataAccessErrorException().getMessage());
		}
		
	}
	
	/**
	 * Metodo: guardarVendedor
	 * Funcion: Guardar un vendedor
	 * @return codigo de vendedor
	 * @throws DataAccessErrorException
	 */
	public Integer guardarVendedor() throws DataAccessErrorException{
		Transaction tx= null;
		int codVen=0;
		try {
			tx= sessionHib.beginTransaction();
				MvendedorDAO mVendedorDAO = new MvendedorDAO(sessionHib);
				Mvendedor mVendedor = new Mvendedor();
				mVendedor.setNombre(this.nombreVendedor);
				mVendedor.setApellido(this.apellidoVendedor);
				mVendedor.setIdUserLock(new Boolean(false));
				codVen= mVendedorDAO.save(mVendedor, sessionHib);					
			tx.commit();							
			return codVen;
		} catch(Exception excep) {
			excep.printStackTrace();
			tx.rollback();
			throw new DataAccessErrorException();
		}
	}
	
	private String procesarCadaCelda(int indiceCelda,String valorCelda){
		String rpta = "";
		try{
			
			switch (indiceCelda){
				/*case ://obtener el codigo de ccss - indice 0
				rpta = obtenerCodCCSS(valorCelda);
				break;*/
				
				case 0://obtener el codigo de cliente - indice 1
				rpta = obtenerCodCliente(valorCelda);
				break;
				
				/*case 4://obtener el codigo de empleado, entro con nombre y apellido - indice 3 y 4
				rpta = obtenerCodVendedor(valorCelda);
				break;*/
				
				case 2://obtener el codigo motivo de autorizacion - indice 5
				rpta = obtenerCodMotivoAutorizacion(valorCelda);
				break;
				
				case 3://obtener el codigo de la descripcion de motivo de autorizacion - indice 6
				rpta = obtenerCodDescripcionMotivoAutorizacion(valorCelda);
				break;
				
				case 5://obtener el codigo de chofer, entro con nombre y apellido - indice 7 y 8
				rpta = obtenerCodChofer(valorCelda);
				break;
				
				case 6://obtener el codigo de vehiculo, entro con el dominio - indice 9
				rpta = obtenerCodVehiculo(valorCelda);
				break;
				
				case 7://obtener el codigo de tipo de comprobante manual, entro con el dominio - indice 10
				rpta = obtenerCodTipoComprobanteManual(valorCelda);
				break;
				
				case 8://obtener el nro de suc manual- indice 11
				rpta = obtenerNroSucursalManual(valorCelda);
				break;
				
				case 9://obtener el nro de remito manual- indice 12
				rpta = obtenerNroRemitoManual(valorCelda);
				break;
				
				case 10://obtener el cod de producto- indice 13
				rpta = obtenerCodProducto(valorCelda);
				break;
				
				case 11://obtener la cantidad de litros- indice 14
				rpta = obtenerCantidadLitros(valorCelda);
				break;				
				
				case 12://obtener cod de comprobantre anular- indice 15
				rpta = obtenerCodTipoComprobanteAnular(valorCelda);
				break;
				
				case 13://obtener nro suc anular- indice 16
				rpta = obtenerNroRemitoAnular(valorCelda);
				break;
				
				case 14://obtener nro remito anular- indice 17
				rpta = obtenerNroSucursalAnular(valorCelda);
				break;
					
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();			
		}
		return rpta;
	}
	
	private String obtenerCodCCSS(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			MccssDAO dao = new MccssDAO(sessionHib);
			Mccss mccss= dao.getCCSSporDescripcion(valorCelda);
			ccss =  new Integer(mccss.getCodCcss());
			System.out.println("ccss "+ccss);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro CCSS.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" CCSS. ";
		}
		return rpta;
	}
	
	private String obtenerCodCliente(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			MclientesDAO dao = new MclientesDAO(sessionHib);
			Mclientes mclientes= dao.getClienteporCodClienteAlfa(valorCelda);
			cliente =  new Integer(mclientes.getCodigo());
			System.out.println("cliente "+cliente);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro cliente.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Cliente. ";
		}
		return rpta;
	}
	
	private String obtenerCodVendedor(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			MvendedorDAO dao = new MvendedorDAO(sessionHib);
			Mvendedor mvendedor= dao.getVendedorPorNombre(valorCelda);
			vendedor =  new Integer(mvendedor.getCodigo());
			System.out.println("vendedor "+vendedor);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro vendedor.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Vendedor. ";
		}
		return rpta;
	}
	
	private String obtenerCodMotivoAutorizacion(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			MautorizacionMotivoDAO dao = new MautorizacionMotivoDAO(sessionHib);
			MautorizacionMotivo mautorizacionMotivo= dao.getMotivoAutorizacionPorDescripcion(valorCelda);
			motivoAutorizacion =  new Integer(mautorizacionMotivo.getCodigo());
			System.out.println("motivoAutorizacion "+motivoAutorizacion);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro Motivo Autorizacion.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Motivo Autorizacion. ";
		}
		return rpta;
	}
	
	private String obtenerCodDescripcionMotivoAutorizacion(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			MdescripcionMotivoAutorizacionDAO dao = new MdescripcionMotivoAutorizacionDAO(sessionHib);
			MdescripcionMotivoAutorizacion mdescripcionMotivoAutorizacion= dao.getDescripcionMotivoAutorizacionPorDescripcion(valorCelda,motivoAutorizacion);
			descripcionMotAutorizacion =  new Integer(mdescripcionMotivoAutorizacion.getCodigo());
			System.out.println("descripcionMotAutorizacion "+descripcionMotAutorizacion);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro Descripcion Motivo Autorizacion.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Descripcion Motivo Autorizacion. ";
		}
		return rpta;
	}
		
	private String obtenerCodChofer(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			MchoferDAO dao = new MchoferDAO(sessionHib);
			Mchofer mchofer= dao.getChoferPorNombreCliente(valorCelda,cliente);
			chofer =  new Integer(mchofer.getCodigo());
			System.out.println("Chofer "+chofer);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro chofer.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Chofer. ";
		}
		return rpta;
	}
		
	private String 	obtenerCodVehiculo(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			MvehiculoDAO dao = new MvehiculoDAO(sessionHib);
			Mvehiculo mvehiculo= dao.getVehiculoPorDominioYClienteEstado(valorCelda,cliente,1);
			patente =  new Integer(mvehiculo.getCodigo());
			System.out.println("Vehiculo "+patente);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro Vehiculo.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Vehiculo. ";
		}
		return rpta;
	}
		
	private String 	obtenerCodTipoComprobanteManual(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			TipoComprobanteDAO dao = new TipoComprobanteDAO(sessionHib);
			TipoComprobante tipoComprobante= dao.getTipoComprobantePorDescripcion(valorCelda);
			tipoCompManual =  new Integer(tipoComprobante.getId());
			System.out.println("tipoCompManual "+tipoCompManual);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro Tipo Comp. Manual.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Tipo Comp. Manual. ";
		}
		return rpta;
	}
	private String 	obtenerNroSucursalManual(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{			
			 nroSucManual= new Integer(valorCelda);
			System.out.println("nroSucManual "+nroSucManual);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro nroSucManual.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Suc. Manual. ";
		}
		return rpta;
	}
	
	private String 	obtenerNroRemitoManual(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{	
			BigDecimal auxNroRem =  new BigDecimal(valorCelda);
			nroRemitoManual =  Integer.parseInt(auxNroRem.toString());
			System.out.println("Nro. Comp. Manual "+nroRemitoManual);			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error en el Nro. Comp. Manual.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Nro. Comp. Manual. ";
		}
		
		try{	
			//validar si existe ya registrado en nro de suc y remito manual en mautorizacion
			validarExistenciaRemito(nroSucManual,nroRemitoManual);						
		}catch(ItemsYaExistenException e){
			e.printStackTrace();
			System.out.println("Ya existe la suc y comp manual registrado en mautorizacion.");
			rpta = e.getMessage();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro al validar Suc. y Comp. Manual.");
			rpta = "Erro al validar"+" Suc. y Comp. Manual. ";
		}
		
		return rpta;
	}
	
	public void validarExistenciaRemito(Integer nroSuc, Integer nroComp)throws Exception,ItemsYaExistenException{
		try{
			MautorizacionDAO autorizacionDAO = new MautorizacionDAO(sessionHib);
			Boolean existe = autorizacionDAO.getAutorizacionPorCompManual(nroSuc,nroComp);
			if(existe){
				String mens= mensajeria.getMessage().getString("el_comprobante_label")+" "+nroSuc.toString()+ " "+nroComp.toString()+" "+mensajeria.getMessage().getString("tiene_autorizacion_label");
				throw new ItemsYaExistenException(mens);
			}
		}catch(ItemsYaExistenException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();			
		}
	}
	
	private String obtenerCodProducto(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			MarticulosDAO dao = new MarticulosDAO(sessionHib);
			Marticulos marticulos= dao.getProductoPorDescripcion(valorCelda);
			producto =  new Integer(marticulos.getCodigo());
			System.out.println("producto "+producto);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro producto.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Producto. ";
		}
		return rpta;
	}
	
	
	private String 	obtenerCantidadLitros(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{			
			 litrosACargar= valorCelda;
			System.out.println("litrosACargar "+litrosACargar);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro litros a cargar.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" litros a cargar. ";
		}
		return rpta;
	}
	
	
	private String 	obtenerCodTipoComprobanteAnular(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			
			if(!valorCelda.trim().equals("")){
				TipoComprobanteDAO dao = new TipoComprobanteDAO(sessionHib);
				TipoComprobante tipoComprobante= dao.getTipoComprobantePorDescripcion(valorCelda);
				tipoCompAnular =  new Integer(tipoComprobante.getId());
				
				if(tipoCompAnular.equals(new Integer(1)) || tipoCompAnular.equals(new Integer(2))){
					if(tipoCompAnular.equals(new Integer(1))){
						if(!tipoCompManual.equals(new Integer(6))){
							rpta="El Tipo de Comprobante Anular es incorrecto.";
						}
					}else if(tipoCompAnular.equals(new Integer(2))){
						if(!tipoCompManual.equals(new Integer(7))){
							rpta="El Tipo de Comprobante Anular es incorrecto.";
						}
					}			
				}else{
					rpta="El Tipo de Comprobante Anular es incorrecto.";
				}
				System.out.println("tipoCompAnular "+tipoCompAnular);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro Tipo Comp. Anular.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Tipo Comp. Anular. ";
		}
		return rpta;
	}
	
	private String 	obtenerNroSucursalAnular(String valorCelda){
//		rpta vacia indica ok
		String rpta="";
		try{	
			if(!valorCelda.trim().equals("")){
				nroSucAnular= new Integer(valorCelda);
			 	System.out.println("nroSucAnular "+nroSucAnular);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro nroSucAnular.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Suc. Anular. ";
		}
		return rpta;
	}
	
	private String 	obtenerNroRemitoAnular(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{	
			if(!valorCelda.trim().equals("")){
				BigDecimal auxNroRem =  new BigDecimal(valorCelda);
				nroRemitoAnular =  Integer.parseInt(auxNroRem.toString());
				System.out.println("Nro. Comp. Anular "+nroRemitoAnular);	
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error en el Nro. Comp. Anular.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Nro. Comp. Anular. ";
		}	
		return rpta;
	}

	private String procesarCadaCeldaCodAutorizacion(){
//		rpta vacia indica ok
		String rpta="";
		try{	
			MautorizacionCodigosDAO mAutorizacionCodigosDAO= new MautorizacionCodigosDAO(sessionHib);		
			codigo= new MautorizacionCodigos();
			codigo= mAutorizacionCodigosDAO.getCodigoAutorizacion();			
			rpta = codigo.getNroAuto();
			System.out.println("nro de autorizacion "+codigo.getNroAuto());	
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error en el nro de autorizacion .");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" nro de autorizacion . ";
		}	
		return rpta;
	}
		
	/*	public String enviar (){
		 try
	       { System.out.println("GOAL");
	          FacesContext facesContext = FacesContext.getCurrentInstance();
	          facesContext.getExternalContext().getApplicationMap().put("fileupload_bytes",    url.getBytes());
	          facesContext.getExternalContext().getApplicationMap().put("fileupload_type",   url.getContentType());
	          facesContext.getExternalContext().getApplicationMap().put("fileupload_name",  url.getName());
	          
	          System.out.println("url.getBytes() ->"+url.getBytes());
	          System.out.println("fileupload_type ->"+url.getContentType());
	          System.out.println("fileupload_name ->"+url.getName());
	          
	          File descriptor = new File("c:\\pruebaRefinor.csv");
	          //aqui van los datos - subir un archivo
	          FileOutputStream fileoutstream = new FileOutputStream(descriptor);

	          fileoutstream.write(url.getBytes()); //contenido es el arreglo de bytes
	          fileoutstream.close(); 
	          
//EJEMPLO 2
//	        set up file and stream
	      	
	      	FileOutputStream outFileStream  = new FileOutputStream(descriptor);
	      	PrintWriter outStream = new PrintWriter(outFileStream);

	      	//write values of primitive data types to the stream
	      	//escribo valores en el archivo
	      	outStream.println("987654321;silvana;EROFGGGG");
	      	outStream.println("987654321;silvana222 mmmm ;EROFGGGG");

	      	//output done, so close the stream
	      	outStream.close();
	    
	      	return "ver_documento";
	          
	          
	          
	          

	      }

	      catch(Exception x)
	      {    AddErrorMessage(x.getMessage());
	         return null;
	      }

	    }*/
	
	
	public Boolean getPuedeIngresar() {	
		return true;
	}	
	
	public UploadedFile getUrl() {
		return url;
	}
	public void setUrl(UploadedFile url) {
		this.url = url;
	}
	public String getRutaArchivoABaja() {
		return rutaArchivoABaja;
	}


	public void setRutaArchivoABaja(String rutaArchivoABaja) {
		this.rutaArchivoABaja = rutaArchivoABaja;
	}


	public Messages getMensajeria() {
		return mensajeria;
	}


	public void setMensajeria(Messages mensajeria) {
		this.mensajeria = mensajeria;
	}


	public Session getSessionHib() {
		return sessionHib;
	}


	public void setSessionHib(Session sessionHib) {
		this.sessionHib = sessionHib;
	}


	public Integer getCcss() {
		return ccss;
	}


	public void setCcss(Integer ccss) {
		this.ccss = ccss;
	}


	public Integer getCliente() {
		return cliente;
	}


	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public Integer getVendedor() {
		return vendedor;
	}


	public void setVendedor(Integer vendedor) {
		this.vendedor = vendedor;
	}


	public Integer getDescripcionMotAutorizacion() {
		return descripcionMotAutorizacion;
	}


	public void setDescripcionMotAutorizacion(Integer descripcionMotAutorizacion) {
		this.descripcionMotAutorizacion = descripcionMotAutorizacion;
	}


	public Integer getChofer() {
		return chofer;
	}


	public void setChofer(Integer chofer) {
		this.chofer = chofer;
	}


	public Integer getNroRemitoManual() {
		return nroRemitoManual;
	}


	public void setNroRemitoManual(Integer nroRemitoManual) {
		this.nroRemitoManual = nroRemitoManual;
	}


	public Integer getNroSucManual() {
		return nroSucManual;
	}


	public void setNroSucManual(Integer nroSucManual) {
		this.nroSucManual = nroSucManual;
	}


	public String getLitrosACargar() {
		return litrosACargar;
	}


	public void setLitrosACargar(String litrosACargar) {
		this.litrosACargar = litrosACargar;
	}


	public Integer getNroRemitoAnular() {
		return nroRemitoAnular;
	}


	public void setNroRemitoAnular(Integer nroRemitoAnular) {
		this.nroRemitoAnular = nroRemitoAnular;
	}


	public Integer getNroSucAnular() {
		return nroSucAnular;
	}


	public void setNroSucAnular(Integer nroSucAnular) {
		this.nroSucAnular = nroSucAnular;
	}


	public Integer getProducto() {
		return producto;
	}


	public void setProducto(Integer producto) {
		this.producto = producto;
	}


	public Integer getTipoCompAnular() {
		return tipoCompAnular;
	}


	public void setTipoCompAnular(Integer tipoCompAnular) {
		this.tipoCompAnular = tipoCompAnular;
	}


	public List<SelectItem> getLstccss() {
		return lstccss;
	}


	public void setLstccss(List<SelectItem> lstccss) {
		this.lstccss = lstccss;
	}


	public List<SelectItem> getVendedores() {
		return vendedores;
	}


	public void setVendedores(List<SelectItem> vendedores) {
		this.vendedores = vendedores;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}


	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}


	public MautorizacionCodigos getCodigo() {
		return codigo;
	}


	public void setCodigo(MautorizacionCodigos codigo) {
		this.codigo = codigo;
	}


	public Integer getTipoCompManual() {
		return tipoCompManual;
	}


	public void setTipoCompManual(Integer tipoCompManual) {
		this.tipoCompManual = tipoCompManual;
	}


	public Integer getPatente() {
		return patente;
	}


	public void setPatente(Integer patente) {
		this.patente = patente;
	}


	public Integer getMotivoAutorizacion() {
		return motivoAutorizacion;
	}


	public void setMotivoAutorizacion(Integer motivoAutorizacion) {
		this.motivoAutorizacion = motivoAutorizacion;
	}



	public String getApellidoVendedor() {
	return apellidoVendedor;
	}

	public void setApellidoVendedor(String apellidoVendedor) {
		this.apellidoVendedor = apellidoVendedor;
	}

	public String getNombreVendedor() {
		return nombreVendedor;
	}

	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}

	public Boolean getNuevoVendedor() {
		return nuevoVendedor;
	}

	public void setNuevoVendedor(Boolean nuevoVendedor) {
		this.nuevoVendedor = nuevoVendedor;
	}
    
}


       

