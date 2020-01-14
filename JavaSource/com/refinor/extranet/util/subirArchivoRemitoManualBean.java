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

 
import org.apache.myfaces.custom.fileupload.UploadedFile;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itsolver.util.io.FileUtil;
import com.refinor.extranet.data.MFacturaManual_tmp;
import com.refinor.extranet.data.MFacturaManual_tmpId;
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
import com.refinor.extranet.data.dao.MFacturaManual_tmpDAO;
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



public class subirArchivoRemitoManualBean extends AbstBackingBean {
	private UploadedFile  url;
	private String  rutaArchivoABaja;
	protected Session sessionHib;
	private Messages mensajeria;	
		
	
	private Date fecha;
	private String nroSucursal;
	private String nroFactura;	
	private String clienteAlfa;
	private Integer codArticulo;
	private String bultos;
	private String dni;
	private String dominio;
	private String nroAutorizacion;
	private String errores;
	private Integer estado;
	private String kilometros;
	
	private Integer motivoAutorizacion;
	private Integer descripcionMotAutorizacion;
	private Integer chofer;
	private Integer patente;
	private Integer tipoCompManual;	
	
	private Integer producto;
	
	private Integer tipoCompAnular;	
	private Integer nroSucAnular;
	private Integer nroRemitoAnular;
	private MautorizacionCodigos codigo; 
	
		

	public subirArchivoRemitoManualBean(){
		try{
			sessionHib = (new AlmacenDAO()).getSession();	
			mensajeria = new Messages();			
			rutaArchivoABaja = "";
		  
			
			}catch(Exception ex){
				ex.printStackTrace();
				AddErrorMessage(ex.getMessage());
			}
	}
	
	 
	
 
	
	
	
	
	public void subirArchivoE(String rutaAlmacenamientoArchivo){
		try{
			 //GUARDO EL ARCHIVO QUE ME SUBIO EL USUARIO
			
			//hago una copia del archivo que el usuario quiere procesar.
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
			/* if(this.ccss.equals( new Integer(-1))){
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
			*/
			
			 if(this.url==null){
				throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));								
			}
			 
			 //borrar lo que tiene la tabla mFacturaManual_tmp
			  sessionHib = (new AlmacenDAO()).getSession();	
			  MFacturaManual_tmpDAO mFacturaManual_tmpDAO =  new MFacturaManual_tmpDAO(sessionHib);
		      List<MFacturaManual_tmp> lstMFN1 = mFacturaManual_tmpDAO.getMFacturaManual_tmp();
			  for (MFacturaManual_tmp mFacturaManual_tmp : lstMFN1) {				  
				  mFacturaManual_tmpDAO.delete(mFacturaManual_tmp);
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
			 String fechaCCSSVen=fechaHoy;
			 
			 FileUtil fileUtil= new FileUtil();
			 Properties props= fileUtil.getPropertiesFile();
			 String rutaArchivoEntrada = props.getProperty("saveArchivosExcel");
			 String nombreArchivoEntrada="Remitos_Manuales_"+fechaCCSSVen+".xls";
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
		      
	          //con j manejo las filas
		      for (int j=sheet.getFirstRowNum(); j<=sheet.getLastRowNum();j++){
		          row=sheet.getRow(j);
		          
		          
		         // System.out.println("celda inicio "+row.getFirstCellNum());
		         // System.out.println("celda fin "+row.getLastCellNum());
		       
			          for(int indiceCelda=0 ; indiceCelda <= 9;indiceCelda++)
			          {	
			        	  cell=row.getCell((short)indiceCelda);
			        	  
			        	  
			        	  if(indiceCelda > 0){
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
			        	  }else
			        	  {
			        		  if(j>0 && cell!=null && cell.getDateCellValue()!=null){
			        		     valorCelda = cell.getDateCellValue().toString();
			        		  }
			        		  
			        	  }
					      
					      System.out.println("valorCelda 1  "+valorCelda); 
					     
					    	  
					      if(j>0 && indiceCelda > 0  ){//nro sucursal
					    	  celdaErrorCodigoAutorizacion = celdaErrorCodigoAutorizacion + procesarCadaCelda(indiceCelda,valorCelda.trim());
					    	  
					      }else if(j>0 && indiceCelda == 0  ){//fecha
					     
					    	  celdaErrorCodigoAutorizacion = celdaErrorCodigoAutorizacion + procesarCadaCelda(indiceCelda,cell.getDateCellValue());
					    	  
					      } 
					     
				          filaProcesada = filaProcesada+";" + valorCelda;
				          valorCelda="";  
				        
				          if(j>0 && indiceCelda == 9){ 
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
		        
		          filaProcesada="";
		          celdaErrorCodigoAutorizacion="";
		        }
		      
		      
		     
		      //verificar cual esta duplicado y cambiar el estado en la tabla y ponerle remito duplicado
		       /* sessionHib = (new AlmacenDAO()).getSession();	
		        mFacturaManual_tmpDAO =  new MFacturaManual_tmpDAO(sessionHib);
		        mFacturaManual_tmpDAO.ProcesarDuplicadosFacturaManuales_tmp();*/
		        
		      String mensaje="";
		        try
		        {
			       //llamar al sp para que genere remito manual
			        sessionHib = (new AlmacenDAO()).getSession();	
			        mFacturaManual_tmpDAO =  new MFacturaManual_tmpDAO(sessionHib);
			        mensaje = mFacturaManual_tmpDAO.procesaFacturaManual();
		        }catch(Exception x)
		        {
		        	 System.out.println( " Error al procesar los remitos manuales:  " + x.getMessage() );
		        }
		        
		        //salida del archivo de rpta
		        String nombreArchivoSalida="Remitos_Manuales_"+fechaCCSSVen+".xls";
		        File descriptorSalida = new File(rutaArchivoEntrada+nombreArchivoSalida);
			    FileOutputStream outFileStream  = new FileOutputStream(descriptorSalida);
			   
			    
			    if(mensaje.equals("OK"))
			    {
				    sessionHib = (new AlmacenDAO()).getSession();	
				    mFacturaManual_tmpDAO =  new MFacturaManual_tmpDAO(sessionHib);
			        List<MFacturaManual_tmp> lstMFN = mFacturaManual_tmpDAO.getMFacturaManuales_tmp();
			        PrintWriter outStream = new PrintWriter(outFileStream);
			     // el que formatea
		        	SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy"); 
		        	BigDecimal kilometros = null;
		        	BigDecimal bultos = null;
		        	String estado="";
		        	String fecha="";
		        	String error="";
		        	filaProcesada = "Fecha;Sucursal;Nro Remito;Cliente;DNI Chofer;Dominio;Cod Articulo;Litros;kilometros;Cod Autorizacion;Estado;Observaciones";
		        	outStream.println(filaProcesada);
		        	filaProcesada = "";	 
			        for (MFacturaManual_tmp manual_tmp : lstMFN) { 
			        	
			        	 kilometros =   manual_tmp.getKilometros() == null ? new BigDecimal("0")  :  manual_tmp.getKilometros() ;
			        	 bultos = manual_tmp.getBultosBig() == null ? new BigDecimal("0")  :  manual_tmp.getBultosBig() ;
			        	 estado =  "Procesado"  ;
			        	 fecha = manual_tmp.getFechaStr(); //() !=null ? formateador.format(manual_tmp.getFecha()) : "");
			        	 error = manual_tmp.getErrores().trim().equals("") ? "El remito se guardo correctamente." : manual_tmp.getErrores();
			        	 filaProcesada = fecha  +";" + 
			        	 manual_tmp.getNroSucursal()+";" + 
			        	 manual_tmp.getNroFactura()+";" + 
			        	 manual_tmp.getClienteAlfa()+";" + 
			        	 manual_tmp.getDni()+";" + 
			        	 manual_tmp.getDominio()+";" + 
			        	 
			        	 manual_tmp.getCodArticulo()+";" + 
			        	 bultos +";" + 
			        	 kilometros +";" +
			        	 manual_tmp.getNroAutorizacion()+";" +
			        	 estado+";" +
			        	 error;
			        	 
			        	outStream.println(filaProcesada);
			        	filaProcesada="";
					}		        
			        
			       // filaProcesada= filaProcesada.substring(1, filaProcesada.length()); 
			         
				    outStream.close();		          
				    rutaArchivoABaja=getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombreArchivoSalida;
			    }else
			    {
					throw new DatosObligatoriosException(mensaje);								
					
			    }
			    
			    return null;
		        
	        
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
			
			
			MFacturaManual_tmp mFacturaManual  = new MFacturaManual_tmp(); 
			 
			mFacturaManual.setFecha(this.fecha);  
			 
			
			if(this.nroFactura!=null && !this.nroFactura.trim().equals("")) { 
			 
				String nroFac = nroFactura.replace(",", "");
				 nroFac = nroFac.replace(".", "");
				mFacturaManual.setNroFactura(Integer.parseInt(nroFac));
			}	
			
			
			if(this.nroSucursal!=null && !this.nroSucursal.trim().equals("")) { 
				String nroSuc = nroSucursal.replace(",", "");
				nroSuc = nroSuc.replace(".", "");
				mFacturaManual.setNroSucursal(Integer.parseInt(nroSuc.replace(",", "")) ); 
			}	
			
			
			if(this.bultos!=null && !this.bultos.trim().equals("")) {
				if(this.bultos.contains(",")){
					this.bultos= this.bultos.replace(',', '.');
				}				
				mFacturaManual.setBultos(Integer.parseInt(this.bultos));
			}	 
			
			 
			
			
			mFacturaManual.setClienteAlfa(this.clienteAlfa);			
			mFacturaManual.setCodArticulo(this.codArticulo);
			
			if(this.dni!=null && !this.dni.trim().equals("")) 
			{
				String dnis = dni.replace(",", "");
				dnis = dnis.replace(".", "");
				mFacturaManual.setDni(Integer.parseInt(dnis.replace(",", "")));
			}
			
			mFacturaManual.setDominio(this.dominio);
			mFacturaManual.setNroAutorizacion(this.nroAutorizacion);
			mFacturaManual.setEstado("I");
			mFacturaManual.setErrores("");
			
			if(this.kilometros!=null && !this.kilometros.trim().equals("")) {
				if(this.kilometros.contains(",")){
					this.kilometros= this.kilometros.replace(',', '.');
				}				
				mFacturaManual.setKilometros(new BigDecimal( this.kilometros));
			} 
			
			//MusuarioWeb musuarioWeb=(MusuarioWeb)getSessionValue("usuario");
			//mFacturaManual.setCodEmisor(musuarioWeb.getId());			
			
			try {
				 sessionHib = (new AlmacenDAO()).getSession();	
				tx= sessionHib.beginTransaction();
					com.refinor.extranet.data.dao.MFacturaManual_tmpDAO mFacturaManual_tmpDAO = new com.refinor.extranet.data.dao.MFacturaManual_tmpDAO(sessionHib);
					mFacturaManual_tmpDAO.save(mFacturaManual, sessionHib);					
				tx.commit();							
				System.out.println( " se guardo el registro " );
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
	
	 
	
	
	private String procesarCadaCelda(int indiceCelda,Date valorCelda){
		String rpta = "";
		try{
		this.fecha=valorCelda;  
			
		}catch(Exception e){
			e.printStackTrace();			
		}
		return rpta;
	}
	
	private String procesarCadaCelda(int indiceCelda,String valorCelda){
		String rpta = "";
		try{
			
			switch (indiceCelda){ 
				
				case 0://fecha
				rpta = obtenerFecha(valorCelda);
				break;  
				
				case 1://nro sucursales
					rpta = obtenerNroSucursal(valorCelda);
					break;
					
				case 2://nro factura
					rpta = obtenerNroFactura(valorCelda);
					break;					
					
				case 3://  codigo de cliente   alfa
					rpta = obtenerCodCliente(valorCelda);
					break;	
					
				case 4:// 
					rpta = obtenerDNIChofer(valorCelda);
					break;
					
				case 5://o 
					rpta = obtenerDominioVehiculo(valorCelda);
					break;
					 
				case 6:// 
					rpta = obtenerCodProducto(valorCelda);
					break;
					
				case 7:// 
					rpta = obtenerCantidadLitros(valorCelda);
					break;  
				
				case 8://
					rpta = obtenerKilometros(valorCelda);
					break;
					
				case 9://o 
					rpta = obtenerNroAutorizacion(valorCelda);
					break; 
				 
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();			
		}
		return rpta;
	}
	
	private String 	obtenerFecha(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{			
			 fecha = new Date( valorCelda);
			System.out.println("nroSucManual "+ fecha);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro nroSucManual.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Suc. Manual. ";
		}
		return rpta;
	}
	
	private String 	obtenerNroSucursal(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{			
			 nroSucursal=  valorCelda ;
			System.out.println("nroSucManual "+nroSucursal);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro nroSucManual.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Suc. Manual. ";
		}
		return rpta;
	}
	
	private String obtenerCodCliente(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{  
			clienteAlfa =  valorCelda;
			System.out.println("cliente "+clienteAlfa);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro cliente.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" Cliente. ";
		}
		return rpta;
	}
	
	
	private String 	obtenerNroFactura(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{ 
			nroFactura =   valorCelda;
			System.out.println("nro remito "+nroFactura);			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error en el nro remito.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" nro remito ";
		}
		
		/*try{	
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
		}*/
		
		return rpta;
	}
	
	
	private String obtenerCodProducto(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{  
			codArticulo =  Integer.parseInt(valorCelda);
			System.out.println("codArticulo "+codArticulo );
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro producto.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" codArticulo. ";
		}
		return rpta;
	}
	
	
	private String 	obtenerCantidadLitros(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{			
			 bultos =    valorCelda  ;
			System.out.println("litrosACargar "+bultos);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro litros a cargar.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" litros a cargar. ";
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
		
	private String obtenerDNIChofer(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			 
			dni = valorCelda;
			System.out.println("Chofer "+dni);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro chofer.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" dni Chofer. ";
		}
		return rpta;
	}
		
	private String 	obtenerDominioVehiculo(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			 
			dominio =  valorCelda;
			System.out.println("Vehiculo "+dominio);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro Vehiculo.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" dominio Vehiculo. ";
		}
		return rpta;
	}
		
	private String 	obtenerNroAutorizacion(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			 
			nroAutorizacion =  valorCelda;
			System.out.println("Vehiculo "+nroAutorizacion);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro nroAutorizacion.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" nro Autorizacion. ";
		}
		return rpta;
	}
		
	private String 	obtenerEstado(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			 
			estado = Integer.parseInt(valorCelda);
			System.out.println("Vehiculo "+estado);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro estado.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" estado. ";
		}
		return rpta;
	}
	
	
	private String 	obtenerErrores(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			 
			errores =  valorCelda;
			System.out.println("errores "+errores);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro errores.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" errores. ";
		}
		return rpta;
	}
	
	
	private String 	obtenerKilometros(String valorCelda){
		//rpta vacia indica ok
		String rpta="";
		try{
			 
			kilometros =   valorCelda   ;
			System.out.println("kilometros "+kilometros);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No se encontro kilometros.");
			rpta = mensajeria.getMessage().getString("msj_no_se_contro")+" kilometros. ";
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



	 
    
}


       

