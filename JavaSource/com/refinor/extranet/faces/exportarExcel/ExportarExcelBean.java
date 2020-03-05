package com.refinor.extranet.faces.exportarExcel;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.itsolver.util.io.FileUtil;

import com.refinor.extranet.faces.base.AbstBackingBean;
import com.refinor.extranet.to.AsientoContableTO;
import com.refinor.extranet.to.CcssRendicionTransferenciaTO;
import com.refinor.extranet.to.ClienteFacturaTO;
import com.refinor.extranet.to.CombustibleTO;
import com.refinor.extranet.to.ConsumoTO;
import com.refinor.extranet.to.CtasCtesTO;
import com.refinor.extranet.to.CuposTO;
import com.refinor.extranet.to.DocumentoAplicadoTO;
import com.refinor.extranet.to.HashTO;
import com.refinor.extranet.to.MAutorizacionTO;
import com.refinor.extranet.to.MChoferTO;
import com.refinor.extranet.to.MClienteTO;
import com.refinor.extranet.to.MCuentaBancoTO;
import com.refinor.extranet.to.MExclusionTO;
import com.refinor.extranet.to.MFacturaVTO;
import com.refinor.extranet.to.MListaPrecioTO;
import com.refinor.extranet.to.MReciboTO;
import com.refinor.extranet.to.MVehiculoTO;
import com.refinor.extranet.to.MovimientoStockTO;
import com.refinor.extranet.to.PercepcionesIIBBTO;
import com.refinor.extranet.to.PercepcionesIVATO;
import com.refinor.extranet.to.ReciboRetencionPresTO;
import com.refinor.extranet.to.RemitoTO;
import com.refinor.extranet.to.RendicionPendienteTO;
import com.refinor.extranet.to.RendicionTranfTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.DataUtil;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.NoExistenItemsException;

public class ExportarExcelBean extends AbstBackingBean {	
	
	public ExportarExcelBean(){
	}

	@Override
	public Boolean getPuedeIngresar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Metodo: generarExcelReporteRemitos
	 * Funcion: Generar excel del reporte de choferes activos e inactivos
	 * @param lstAManipular
	 * @param agente
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */										  
	public String generarExcelReporteAutorizaciones(List lstAManipular,String fecha) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("archivo_autoriaciones_compuesto_label")+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto4 = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("archivo_autoriaciones_compuesto_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	 
			int ind=0;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	    	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 25)));
	    	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	    	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	       
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_autorizacion_label").toUpperCase());
		     
		     ind=ind+1;  
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_pedido_label").toUpperCase());
		     
		     ind=ind+1;           
		     c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("ccss_label").toUpperCase()); 
	        	        
	         ind=ind+1;           
		     c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase()); 
	         
	         ind=ind+1;  
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_comp_man_label").toUpperCase());
	         
		     
	         ind=ind+1;  
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());   
	         
	         ind=ind+1;  
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("litros_a_acargar_label").toUpperCase()); 
	 		 
	         ind=ind+1;  
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("chofer_label").toUpperCase());   
	         
	         ind=ind+1;  
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("vehiculo_label").toUpperCase()); 
	 		
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estiloMonto4.setDataFormat(df.getFormat("#,##0.0000"));	
	         estiloMonto4.setBorderBottom(estiloMonto4.BORDER_THIN);
	         estiloMonto4.setBorderRight(estiloMonto4.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			MAutorizacionTO remitoTO = new MAutorizacionTO();
			int index =0;
			ind=0;
			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 remitoTO = (MAutorizacionTO)lstAManipular.get(index);
				 index++;				
				 ind=0;
				 
				 //nro autorizacion
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getNroAutorizacion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getNroAutorizacion());
			     
//			   fecha pedido
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getFechaPedido()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getFechaPedido());
		         
//			       ccss 
			     ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getCcddDescripcion()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getCcddDescripcion()); 
		         
//			      cliente 
			     ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getCliDescripcion()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getCliDescripcion()); 
			     
			     //nro comprobante manual				
		         ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getNroRemito1()==null || remitoTO.getSucursalManual()+""==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getSucursalManual()+ " - "+remitoTO.getNroRemito1());
		         
		    		         
		         //producto
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getProdDecripcion()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getProdDecripcion());
		         
		         
		         //cantidad
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(remitoTO.getLitrosACargar()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getLitrosACargar().doubleValue());     
		 
		         
		         //chofer
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getChofNombre()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getChofApellido()+", "+ remitoTO.getChofNombre());     
		 
		         
		         //vehiculo
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getPatente()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getPatente());     
		 
		        
			     
		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReporteAsientoConDiferecias(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		
		Properties props= fileUtil.getPropertiesFile();		
		try{					
			nombArch = mensajeria.getMessage().getString("asiento_con_diferencias_label")+"_"+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;				
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto2 = wb.createCellStyle();
							
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("asiento_con_diferencias_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont(); 
			HSSFFont fuenteTitulosMonto2 =  wb.createFont(); 
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
		 	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
		     fuenteTitulosMonto2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
		     
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	                 
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("ccss_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_ejercicio_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_asiento_label").toUpperCase());
		     
		     	     
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("debe_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("haber_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("monto_adeudado_label").toUpperCase());
		             
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         
	         estiloMonto2.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto2.setBorderBottom(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setBorderRight(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setFont(fuenteTitulosMonto2);
	         
	        
	         
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			AsientoContableTO percepcionesIVATO = new AsientoContableTO();
			int index =0;
			ind=0;			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 percepcionesIVATO = (AsientoContableTO)lstAManipular.get(index);
				 index++;				 			     
			     //ccss
				 c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getCcssDesc()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getCcssDesc());
			     
			     
			     //nro ejercicio
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getNroEjercicio()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getNroEjercicio());
			     
			     //nro asiento
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getNroAsiento()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getNroAsiento());
			     
			     	     
			     
			     //debe
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	
			    
				     if(percepcionesIVATO.getValorDebe()==null)
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(percepcionesIVATO.getValorDebe().doubleValue());
				
			     
//			   haber			 
		     ind=ind+1;
		     c = r.createCell((short)ind);	
		     c.setCellStyle(estiloMonto);	
		    
			     if(percepcionesIVATO.getValorHaber()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getValorHaber().doubleValue());
			
	
//			   saldo			 
		     ind=ind+1;
		     c = r.createCell((short)ind);	
		     c.setCellStyle(estiloMonto);	
		     	     
			if(percepcionesIVATO.getSaldo()==null)
		      	 c.setCellValue("");
		    else
		      	 c.setCellValue(percepcionesIVATO.getSaldo().doubleValue());
		

			     
		         ind=0;

		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReporteCtaCteFacturaPorCliente(List lstAManipular,String agente,int tipoUsuario, String fecha) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{					
			nombArch = mensajeria.getMessage().getString("archivo_ctas_ctes_compuesto_label")+"_"+fecha+".xls";			
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo1 = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estiloTitulo3 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";				
			etiqueta=mensajeria.getMessage().getString("archivo_ctas_ctes_compuesto_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
			HSSFFont fuenteTitulosCliente =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		   
	       	     
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
	         
	         fuenteTitulosCliente.setColor((short)0x27);
	         fuenteTitulosCliente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
	         estiloTitulo.setFont(fuenteTitulosCliente);
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);	
	         
	         fuenteEtiquetas.setFontHeightInPoints((short) 12);
	         fuenteEtiquetas.setColor((short)0x20);
	         fuenteEtiquetas.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);         
	         estiloTitulo1.setFont(fuenteEtiquetas);	       
	         estiloTitulo1.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);	
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x2c);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
		     estiloTitulo2.setBorderBottom(estiloTitulo2.BORDER_THIN);
		     estiloTitulo2.setBorderRight(estiloTitulo2.BORDER_THIN);
		     estiloTitulo2.setBorderLeft(estiloTitulo2.BORDER_THIN);
		     estiloTitulo2.setBorderTop(estiloTitulo2.BORDER_THIN);	 
		     
		     estiloTitulo3.setFont(fuenteTitulos);    
		     estiloTitulo3.setFillForegroundColor((short) 0x16);	
		     estiloTitulo3.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);	     
		     estiloTitulo3.setBorderBottom(estiloTitulo3.BORDER_THIN);
		     estiloTitulo3.setBorderRight(estiloTitulo3.BORDER_THIN);
		     estiloTitulo3.setBorderLeft(estiloTitulo3.BORDER_THIN);
		     estiloTitulo3.setBorderTop(estiloTitulo3.BORDER_THIN);	 
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 4+ lstAManipular.size();			
			int index =0;
			int ind=0;		
			
			 r = s.createRow(1);
			 c = r.createCell((short)0);
			 c.setCellStyle(estiloTitulo1);
		     c.setCellValue("Reporte: Cuenta Corriente");
		  
			ClienteFacturaTO clienteFacturaTO = new ClienteFacturaTO();
			Iterator itCliente = lstAManipular.iterator();
			Iterator itFacturas =null;
			Iterator itResumen =null;
			Iterator itDocumentosAplicados =null;
			CtasCtesTO mFacturaVTO= new CtasCtesTO();
			HashTO resumen = new HashTO();
			DocumentoAplicadoTO documentoAplicadoTO= new DocumentoAplicadoTO();
			int fila =  4;
			String nroComprob="";
			while(itCliente.hasNext()){
				 clienteFacturaTO = (ClienteFacturaTO)itCliente.next();
				 
				 //titulo y datos cliente
				 if(clienteFacturaTO.getLstFacturas().size()>0){
				 
				 r = s.createRow(fila);
				 c = r.createCell((short)0);
				 c.setCellStyle(estiloTitulo);
			     c.setCellValue("Cliente: " +clienteFacturaTO.getCliente().getCodClienteAlfa()+" - "+clienteFacturaTO.getCliente().getDescripcion());
			     
			    
			     itFacturas = clienteFacturaTO.getLstFacturas().iterator();
			     itResumen = clienteFacturaTO.getLstResumenCtaCte().iterator();
			     
//			   Ancho de columnas	 del titulo de factura      
					ind=0; //s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 14)));
					//ind=ind+1; s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));
					//ind=ind+1; s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
					//ind=ind+1;
					s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
					ind=ind+1; s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
					ind=ind+1; s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));	
					ind=ind+1; s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
					ind=ind+1; s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
					ind=ind+1; s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
					ind=ind+1; s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
					ind=ind+1; s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 28)));
	
					
					//titulo de factura
				     fila = fila+2;
				     r = s.createRow(fila);			     
				     ind=0;
			        			         
			        /* c = r.createCell((short)ind);
			    	 c.setCellStyle(estiloTitulo2);
			    	 c.setCellValue(mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase());
			    	 ind=ind+1;
			         c = r.createCell((short)ind);
			    	 c.setCellStyle(estiloTitulo2);
			    	 c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
			    	 ind=ind+1;
			    	 c = r.createCell((short)ind);
			    	 c.setCellStyle(estiloTitulo2);
			    	 c.setCellValue(mensajeria.getMessage().getString("limite_credito_label").toUpperCase());
			    	 ind=ind+1;*/
			            
			        c = r.createCell((short)ind);
		            c.setCellStyle(estiloTitulo2);
		            c.setCellValue(mensajeria.getMessage().getString("comprobante_label").toUpperCase());
		           
		            ind=ind+1;
		            c = r.createCell((short)ind);
		            c.setCellStyle(estiloTitulo2);
		            c.setCellValue(mensajeria.getMessage().getString("sucursal_label").toUpperCase());
		            
		            ind=ind+1;
		            c = r.createCell((short)ind);
		            c.setCellStyle(estiloTitulo2);
		            c.setCellValue(mensajeria.getMessage().getString("nro_comprobante_label").toUpperCase());
		            
		            ind=ind+1;
		            c = r.createCell((short)ind);
		            c.setCellStyle(estiloTitulo2);
		            c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());   
		            
		            ind=ind+1;
		            c = r.createCell((short)ind);	      
		            c.setCellStyle(estiloTitulo2);
		            c.setCellValue(mensajeria.getMessage().getString("vencimiento_label").toUpperCase());  
		            
		            ind=ind+1;
		            c = r.createCell((short)ind);	      
		            c.setCellStyle(estiloTitulo2);
		            c.setCellValue(mensajeria.getMessage().getString("total_pesos_label").toUpperCase()); 
		            
		            ind=ind+1;
		            c = r.createCell((short)ind);	      
		            c.setCellStyle(estiloTitulo2);
		            c.setCellValue(mensajeria.getMessage().getString("monto_adeudado_label").toUpperCase()); 
		            
		            ind=ind+1;
		            c = r.createCell((short)ind);	      
		            c.setCellStyle(estiloTitulo2);
		            c.setCellValue(mensajeria.getMessage().getString("estado_label").toUpperCase());  

		            
			     while(itFacturas.hasNext()){			    	 
				    	 mFacturaVTO = (CtasCtesTO)itFacturas.next();				 
					     //datos de casa fila de facturas
					     fila = fila+1;
					     r = s.createRow(fila);			     
						 ind=0;
					     /*c = r.createCell((short)ind);
					     c.setCellStyle(estilofilas);
					     c.setCellValue(mFacturaVTO.getCodClienteAlfa());
					         
				         ind=ind+1;
				         c = r.createCell((short)ind);
				         c.setCellStyle(estilofilas);
				         c.setCellValue(mFacturaVTO.getCliDescripcion());
				         
				         ind=ind+1;        
				         c = r.createCell((short)ind);
				         c.setCellStyle(estilofilas);				         
				         if(mFacturaVTO.getTopeCredito()==null )
				        	 c.setCellValue("");
				         else
				        	 c.setCellValue(mFacturaVTO.getTopeCredito().doubleValue());	

				         ind=ind+1;*/
				         c = r.createCell((short)ind);
				         c.setCellStyle(estilofilas);
				         if(mFacturaVTO.getComprobanteDescripcion()==null )
				        	 c.setCellValue("");
				         else
				        	 c.setCellValue(mFacturaVTO.getComprobanteDescripcion());	         
					     
				         ind=ind+1;
					     c = r.createCell((short)ind);	
					     c.setCellStyle(estilofilas);	        
					     c.setCellValue(mFacturaVTO.getSucursal());
					     
				         ind=ind+1;
					     c = r.createCell((short)ind);	
					     c.setCellStyle(estilofilas);	        
					     c.setCellValue(mFacturaVTO.getComprobanteNumero());
				         
					     ind=ind+1;
				         c = r.createCell((short)ind);
				         c.setCellStyle(estilofilas);
				         if(mFacturaVTO.getFecha()==null )
				        	 c.setCellValue("");
				         else
				        	 c.setCellValue(mFacturaVTO.getFecha());
				         
				         ind=ind+1;
				         c = r.createCell((short)ind);
				         c.setCellStyle(estilofilas);
				         if(mFacturaVTO.getVencimiento()==null )
				        	 c.setCellValue("");
				         else
				        	 c.setCellValue(mFacturaVTO.getVencimiento());
				         
				         ind=ind+1;
				         c = r.createCell((short)ind);
				         c.setCellStyle(estiloMonto);
				         if(mFacturaVTO.getNetoFactura()==null )
				        	 c.setCellValue("");
				         else
				        	 c.setCellValue(mFacturaVTO.getNetoFactura().doubleValue());  
				         
				         ind=ind+1;
				         c = r.createCell((short)ind);
				         c.setCellStyle(estiloMonto);
				         if(mFacturaVTO.getImporte()==null )
				        	 c.setCellValue("");
				         else
				        	 c.setCellValue(mFacturaVTO.getImporte().doubleValue()); 
				         
				         ind=ind+1;
				         c = r.createCell((short)ind);
				         c.setCellStyle(estilofilas);
				         if(mFacturaVTO.getEstadoStr()==null )
				        	 c.setCellValue("");
				         else
				        	 c.setCellValue(mFacturaVTO.getEstadoStr()); 
					     
				         }   
			    	     
				 	fila = fila+2;
				 	
				 	//muestro los datos resumidos				 	 
				 	 while(itResumen.hasNext()){			    	 
				 		 resumen = (HashTO)itResumen.next();				     				    			 
					     r = s.createRow(fila);			     
					     ind=0;
					     
					     c = r.createCell((short)ind);
					     if(!resumen.getTitulo().trim().equals(""))
					      	 c.setCellStyle(estiloTitulo2);
					     
				    	 c.setCellValue(resumen.getTitulo().toUpperCase());
				    						 	
				    	 ind=ind+1;
				         c = r.createCell((short)ind);
				         c.setCellStyle(estiloMonto);
				         if(resumen.getMonto()==null)
				        	 c.setCellValue("");
				         else
				        	 c.setCellValue(resumen.getMonto().doubleValue()); 
					 	
					 	 fila = fila+1;
				 	 }	 
				 	
				 	
					fila = fila+1;	 
				 }
			}		
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReporteDetalleFactura(List lstAManipular,String fecha,BigDecimal saldoFac, BigDecimal montoDoc, BigDecimal saldototal) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{					
			nombArch = mensajeria.getMessage().getString("info_detalle_facturas_label")+"_"+fecha+".xls";			
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo1 = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estiloTitulo3 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";				
			etiqueta=mensajeria.getMessage().getString("info_detalle_facturas_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
			HSSFFont fuenteTitulosCliente =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		   
	       	     
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
	         
	         fuenteTitulosCliente.setColor((short)0x27);
	         fuenteTitulosCliente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
	         estiloTitulo.setFont(fuenteTitulosCliente);
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);	
	         
	         fuenteEtiquetas.setFontHeightInPoints((short) 12);
	         fuenteEtiquetas.setColor((short)0x20);
	         fuenteEtiquetas.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);         
	         estiloTitulo1.setFont(fuenteEtiquetas);	       
	         estiloTitulo1.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);	
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x2c);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
		     estiloTitulo2.setBorderBottom(estiloTitulo2.BORDER_THIN);
		     estiloTitulo2.setBorderRight(estiloTitulo2.BORDER_THIN);
		     estiloTitulo2.setBorderLeft(estiloTitulo2.BORDER_THIN);
		     estiloTitulo2.setBorderTop(estiloTitulo2.BORDER_THIN);	 
		     
		     estiloTitulo3.setFont(fuenteTitulos);    
		     estiloTitulo3.setFillForegroundColor((short) 0x16);	
		     estiloTitulo3.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);	     
		     estiloTitulo3.setBorderBottom(estiloTitulo3.BORDER_THIN);
		     estiloTitulo3.setBorderRight(estiloTitulo3.BORDER_THIN);
		     estiloTitulo3.setBorderLeft(estiloTitulo3.BORDER_THIN);
		     estiloTitulo3.setBorderTop(estiloTitulo3.BORDER_THIN);	 
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 4+ lstAManipular.size();			
			int index =0;
			int ind=0;		
			
			 r = s.createRow(1);
			 c = r.createCell((short)0);
			 c.setCellStyle(estiloTitulo1);
		     c.setCellValue("Reporte: DETALLE DE FACTURAS");
		  
			ClienteFacturaTO clienteFacturaTO = new ClienteFacturaTO();
			Iterator itCliente = lstAManipular.iterator();
			Iterator itFacturas =null;
			Iterator itDocumentosAplicados =null;
			MFacturaVTO mFacturaVTO= new MFacturaVTO();
			DocumentoAplicadoTO documentoAplicadoTO= new DocumentoAplicadoTO();
			int fila =  4;
			String nroComprob="";
			while(itCliente.hasNext()){
				 clienteFacturaTO = (ClienteFacturaTO)itCliente.next();
				 
				 //titulo y datos cliente
				 if(clienteFacturaTO.getLstFacturas().size()>0){
				 
				 r = s.createRow(fila);
				 c = r.createCell((short)0);
				 c.setCellStyle(estiloTitulo);
			     c.setCellValue("Cliente: " +clienteFacturaTO.getCliente().getCodClienteAlfa()+" - "+clienteFacturaTO.getCliente().getDescripcion());
			     
			    
			     itFacturas = clienteFacturaTO.getLstFacturas().iterator();
			     while(itFacturas.hasNext()){
			    	 
				    	 mFacturaVTO = (MFacturaVTO)itFacturas.next();
				     	//Ancho de columnas	 del titulo de factura      
						ind=0;       	
						s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));			
						ind=ind+1;
						s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 15)));			
						ind=ind+1;
						s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 15)));
						ind=ind+1;
						s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
						ind=ind+1;
						s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
						
						//titulo de factura
					     fila = fila+2;
					     r = s.createRow(fila);			     
					     ind=0;
				         c = r.createCell((short)ind);
				         c.setCellStyle(estiloTitulo2);
				         c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());			         
				         ind=ind+1;
				         c = r.createCell((short)ind);
				         c.setCellStyle(estiloTitulo2);
				         c.setCellValue(mensajeria.getMessage().getString("comprobante_label").toUpperCase());   
				         ind=ind+1;        
				         c = r.createCell((short)ind);
				         c.setCellStyle(estiloTitulo2);
					     c.setCellValue(mensajeria.getMessage().getString("nro_comprobante_label").toUpperCase());
					     ind=ind+1;        
				         c = r.createCell((short)ind);
				         c.setCellStyle(estiloTitulo2);
					     c.setCellValue(mensajeria.getMessage().getString("monto_label").toUpperCase());		
					     ind=ind+1;        
				         c = r.createCell((short)ind);
				         c.setCellStyle(estiloTitulo2);
					     c.setCellValue(mensajeria.getMessage().getString("monto_adeudado_label").toUpperCase());

					 
					     //datos de casa fila de facturas
					     fila = fila+1;
					     r = s.createRow(fila);			     
						 ind=0;
					     c = r.createCell((short)ind);
					     c.setCellStyle(estilofilas);
					     c.setCellValue(mFacturaVTO.getFecha()!=null ? mFacturaVTO.getFecha():"");
					         
				         ind=ind+1;
				         c = r.createCell((short)ind);
				         c.setCellStyle(estilofilas);
				         c.setCellValue(mFacturaVTO.getTipo()!=null ? mFacturaVTO.getTipo():"");
				         
				         ind=ind+1;        
				         c = r.createCell((short)ind);
				         c.setCellStyle(estilofilas);
				         
				         if(mFacturaVTO.getNroSucursal()!=null && !mFacturaVTO.getNroSucursal().equals(new Integer(0)))
				        	 nroComprob = mFacturaVTO.getNroSucursal().toString();
				         
				         if(mFacturaVTO.getNroFactura()!=null)
				        	 nroComprob = nroComprob + " "+ mFacturaVTO.getNroFactura().toString();
				         
				         c.setCellValue(nroComprob);
				         
				         nroComprob="";
					        
					     ind=ind+1;        
				         c = r.createCell((short)ind);
				         c.setCellStyle(estiloMonto);
				         c.setCellValue(mFacturaVTO.getTotal()!=null ? mFacturaVTO.getTotal().doubleValue():0);	
					     
					     
					     ind=ind+1;        
				         c = r.createCell((short)ind);
				         c.setCellStyle(estiloMonto);
				         c.setCellValue(mFacturaVTO.getSaldo()!=null ? mFacturaVTO.getSaldo().doubleValue():0);		   
				     
					     
				         if(mFacturaVTO.getLstDocumentoAplicados()!=null && mFacturaVTO.getLstDocumentoAplicados().size()>0){
					         itDocumentosAplicados = mFacturaVTO.getLstDocumentoAplicados().iterator();
					         
//					       titulo de documentos aplicados
						     fila = fila+1;
						     r = s.createRow(fila);			     
						     ind=0;
					         c = r.createCell((short)ind);
					         c.setCellStyle(estiloTitulo3);
					         c.setCellValue(mensajeria.getMessage().getString("fecha_label"));			         
					         ind=ind+1;
					         c = r.createCell((short)ind);
					         c.setCellStyle(estiloTitulo3);
					         c.setCellValue(mensajeria.getMessage().getString("comprobante_label"));   
					         ind=ind+1;        
					         c = r.createCell((short)ind);
					         c.setCellStyle(estiloTitulo3);
						     c.setCellValue(mensajeria.getMessage().getString("nro_comprobante_label"));
						     ind=ind+1;        
					         c = r.createCell((short)ind);
					         c.setCellStyle(estiloTitulo3);
						     c.setCellValue(mensajeria.getMessage().getString("monto_tot_label"));		
						     ind=ind+1;        
					         c = r.createCell((short)ind);
					         c.setCellStyle(estiloTitulo3);
						     c.setCellValue(mensajeria.getMessage().getString("monto_adeudado_label"));
						 
						     while(itDocumentosAplicados.hasNext()){					    	 
						    	 documentoAplicadoTO = (DocumentoAplicadoTO)itDocumentosAplicados.next();
									
								     //datos de casa fila de docum
								     fila = fila+1;
								     r = s.createRow(fila);			     
									 ind=0;
								     c = r.createCell((short)ind);
								     c.setCellStyle(estilofilas);
								     c.setCellValue(documentoAplicadoTO.getFecha()!=null ? documentoAplicadoTO.getFecha():"");
								         
							         ind=ind+1;
							         c = r.createCell((short)ind);
							         c.setCellStyle(estilofilas);
							         c.setCellValue(documentoAplicadoTO.getNombre()!=null ? documentoAplicadoTO.getNombre():"");
							         
							         ind=ind+1;        
							         c = r.createCell((short)ind);
							         c.setCellStyle(estilofilas);
							         c.setCellValue(documentoAplicadoTO.getNumero()!=null ? documentoAplicadoTO.getNumero():"");
							         							        
								     ind=ind+1;        
							         c = r.createCell((short)ind);
							         c.setCellStyle(estiloMonto);
							         c.setCellValue(documentoAplicadoTO.getBruto()!=null ? documentoAplicadoTO.getBruto().doubleValue():0);	
								     
								     
								     ind=ind+1;        
							         c = r.createCell((short)ind);
							         c.setCellStyle(estiloMonto);
							         c.setCellValue(documentoAplicadoTO.getSaldo()!=null ? documentoAplicadoTO.getSaldo().doubleValue():0);		   
							         
						     }
						    
						     if(mFacturaVTO.getLstDocumentoAplicados().size()>0){						     
							     fila = fila+1;
							     r = s.createRow(fila);			     
								 ind=2;
							     c = r.createCell((short)ind);
							     c.setCellStyle(estiloTitulo3);
							     c.setCellValue("Total Pago Aplicado");
							         
						         ind=ind+1;
						         c = r.createCell((short)ind);
						         c.setCellStyle(estiloMonto);
						         c.setCellValue(mFacturaVTO.getPagoAplicado()!=null ? mFacturaVTO.getPagoAplicado().doubleValue():0);
						     }  
				         }
						         
						     
						     
						     
			     }	     
					     
				 fila = fila+2; 
				 
				 }
				 
			
		     
		         
			}
			
			
			if(lstAManipular.size()>0){
				 fila = fila+1;
			     r = s.createRow(fila);			     
				 ind=2;
			     c = r.createCell((short)ind);
			     c.setCellStyle(estiloTitulo3);
			     c.setCellValue(mensajeria.getMessage().getString("saldo_cta_cte_label"));
			         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         c.setCellValue(saldoFac.doubleValue());
		         
		         fila = fila+1;
			     r = s.createRow(fila);			     
				 ind=2;
			     c = r.createCell((short)ind);
			     c.setCellStyle(estiloTitulo3);
			     c.setCellValue(mensajeria.getMessage().getString("documentos_no_aplicados_label"));
			         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         c.setCellValue(montoDoc.doubleValue());
		         
		         fila = fila+1;
			     r = s.createRow(fila);			     
				 ind=2;
			     c = r.createCell((short)ind);
			     c.setCellStyle(estiloTitulo3);
			     c.setCellValue(mensajeria.getMessage().getString("saldo_del_cliente"));
			         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         c.setCellValue(saldototal.doubleValue());
			}
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReporteMovimientoStock2(List lstAManipular,String agente,String fecha) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{					
			nombArch = mensajeria.getMessage().getString("info_movimiento_stock")+"_"+fecha+".xls";			
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto2 = wb.createCellStyle();
			String etiqueta="";				
			etiqueta=mensajeria.getMessage().getString("info_movimiento_stock");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			HSSFFont fuenteTitulosMonto2 =  wb.createFont(); 
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			 fuenteTitulosMonto2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
		    //Ancho de columnas	       
	       	int ind=0;       	
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));			
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 15)));			
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
			
			
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 20)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 15)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 10)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 10)));
	       	     
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);	        
	         
	         ind=0;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("ccss_label").toUpperCase());
	         ind=ind+1;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());   
	         ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("litros_label").toUpperCase());
		     
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("saldo_litros_label").toUpperCase());
		     
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_de_movimiento_label").toUpperCase());		
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("tipo_de_movimiento_label").toUpperCase());		
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_comprobante_label").toUpperCase());		
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("tipo_comprobante_largo_label").toUpperCase());		
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("letra_comprobante_label").toUpperCase());		
	         
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 estiloMonto2.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto2.setBorderBottom(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setBorderRight(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setFont(fuenteTitulosMonto2);
	         
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			MovimientoStockTO movimientoStockTO = new MovimientoStockTO();
			int index =0;
			ind=0;		
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 movimientoStockTO = (MovimientoStockTO)lstAManipular.get(index);
				 index++;				
				 

		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(movimientoStockTO.getDescrCCSS()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(movimientoStockTO.getDescrCCSS());	         
			     
		         ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(movimientoStockTO.getDescrProducto()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(movimientoStockTO.getDescrProducto());
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(movimientoStockTO.getLitros()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(movimientoStockTO.getLitros().doubleValue());
		        
			    
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			    
			     
			     if(movimientoStockTO.getUltimo()!=null && movimientoStockTO.getUltimo().equals(new Integer(1))){
			    	 c.setCellStyle(estiloMonto2);	 
			     }else{
			    	 c.setCellStyle(estiloMonto);	
			     }	
			     
			     if(movimientoStockTO.getSaldoLitros()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(movimientoStockTO.getSaldoLitros().doubleValue());
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(movimientoStockTO.getFechaMovimiento()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(movimientoStockTO.getFechaMovimiento());
			     
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(movimientoStockTO.getDescrMovimiento()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(movimientoStockTO.getDescrMovimiento());
			     
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(movimientoStockTO.getSucursal()==null || movimientoStockTO.getNroComprobante()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(movimientoStockTO.getSucursal().toString()+" - "+ movimientoStockTO.getNroComprobante().toString());
			     
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(movimientoStockTO.getDescrComprobante()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(movimientoStockTO.getDescrComprobante());
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(movimientoStockTO.getLetraC()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(movimientoStockTO.getLetraC());
			     
		         ind=0;
		         
		         
		       }
		
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReporteMovimientoStock(List lstAManipular,String fecha,int codigoStock) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{				
		
			if(codigoStock==1){
				nombArch = mensajeria.getMessage().getString("volumen_vendido_producto_label")+"_"+fecha+".xls";	
			}else if(codigoStock==2){
				nombArch = mensajeria.getMessage().getString("nombre_stock_label")+"_"+fecha+".xls";	
			}
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
					
			
			if(codigoStock==1){
				etiqueta=mensajeria.getMessage().getString("volumen_vendido_producto_label");		
			}else if(codigoStock==2){				
				etiqueta=mensajeria.getMessage().getString("nombre_stock_label");		
			}
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	       
	       	int ind=0;       	
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 10)));			
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 10)));			
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 10)));
	       	     
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);	        
	         
	         ind=0;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("ccss_label").toUpperCase());
	         ind=ind+1;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());   
	         ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("litros_label").toUpperCase());		         
	         
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			MovimientoStockTO movimientoStockTO = new MovimientoStockTO();
			int index =0;
			ind=0;		
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 movimientoStockTO = (MovimientoStockTO)lstAManipular.get(index);
				 index++;				
				 

		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(movimientoStockTO.getDescrCCSS()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(movimientoStockTO.getDescrCCSS());	         
			     
		         ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(movimientoStockTO.getDescrProducto()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(movimientoStockTO.getDescrProducto());
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(movimientoStockTO.getLitros()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(movimientoStockTO.getLitros().doubleValue());
		        
		         ind=0;
		         
		         
		       }
		
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}

	public String generarExcelReporteSubdiarioVentas(List lstAManipular,String fecha,int tipoUsu,int codListado) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
		
			nombArch = mensajeria.getMessage().getString("subdiario_ventas_excel_label")+"_"+fecha+".xls";	
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("subdiario_ventas_excel_label");				
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	       
	       	int ind=0;	       	
	       	
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));			
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));			
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
	       	ind=ind+1;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
	       	ind=ind+1; 			 
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));   		
       	    ind=ind+1;
		    s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	        ind=ind+1;
		    s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	        ind=ind+1;
		    s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	        ind=ind+1;
		    s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	        ind=ind+1;
		    s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	        ind=ind+1;
		    s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	        ind=ind+1;
		    s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
		    ind=ind+1;
		    s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));   
		    ind=ind+1;
		    s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
		    ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	             
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);	        
	         
	         ind=0;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());
	         ind=ind+1;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("tipo_doc_label").toUpperCase());	         
	         ind=ind+1;	        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("tipo_comprobante_label").toUpperCase());   
		     ind=ind+1;
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase());   
		     ind=ind+1;       
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cuit_label").toUpperCase());   
		     ind=ind+1;	         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());   
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("centro_servicio_label").toUpperCase());  
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("prefijo_label").toUpperCase());   
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("numero_label").toUpperCase());   
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("neto_gravado_label").toUpperCase());   
	         ind=ind+1;
			 c = r.createCell((short)ind);	      
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("neto_no_gravado_label").toUpperCase());
		     ind=ind+1;
			 c = r.createCell((short)ind);	      
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("iva_label").toUpperCase());  
			 ind=ind+1;
			 c = r.createCell((short)ind);	      
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("iva2_label").toUpperCase());  
			 ind=ind+1;
			 c = r.createCell((short)ind);	      
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("otros_label").toUpperCase());  
			 ind=ind+1;
			 c = r.createCell((short)ind);	      
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("impuesto_interno_label").toUpperCase());  
			 ind=ind+1;
			 c = r.createCell((short)ind);	      
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("TASA/FONDO/IDC").toUpperCase());  
			 ind=ind+1;
			 c = r.createCell((short)ind);	      
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("itc_label").toUpperCase());
			 ind=ind+1;
			 c = r.createCell((short)ind);	      
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("ley_10081_label").toUpperCase()); 
			ind=ind+1;
			 c = r.createCell((short)ind);	      
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("per_iva_label").toUpperCase());
			 ind=ind+1;
			 c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("per_iibb_label").toUpperCase());
	         ind=ind+1;
			 c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("total_sin_signo_label").toUpperCase());
	         ind=ind+1;
			 c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("condicion_label").toUpperCase());
	         
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			MFacturaVTO mFacturaVTO = new MFacturaVTO();
			int index =0;
			ind=0;		
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 mFacturaVTO = (MFacturaVTO)lstAManipular.get(index);
				 index++;			 

		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mFacturaVTO.getFecha()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getFecha());	         
			     
		         ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mFacturaVTO.getTipo()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getTipo());
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mFacturaVTO.getTipo()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getLetraComprobante());
			     
			     ind=ind+1;			  
		    	 c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mFacturaVTO.getCodClienteAlfa()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getCodClienteAlfa());
			     
			     ind=ind+1;			    
		    	 c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mFacturaVTO.getCuit()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getCuit());
			     
			     ind=ind+1;	
			     DataUtil dataUtil=new DataUtil();
			    
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mFacturaVTO.getCliDescripcion()==null)
		        	 c.setCellValue("");
		         else{
		        	 c.setCellValue( dataUtil.rellenarConBlancos(50, mFacturaVTO.getCliDescripcion(), false));
		        	 }
			     
			     ind=ind+1; 
			     c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mFacturaVTO.getCcss()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getCcss());
			         
		         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mFacturaVTO.getNroSucursal()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getNroSucursal());
		         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mFacturaVTO.getNroFactura()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getNroFactura());	         
		       		        
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);	
		         if(mFacturaVTO.getExento()==0){
		         if(mFacturaVTO.getNetoGravado()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getNetoGravado().doubleValue());
		         }
	         
	         	         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getExento()==1){	
		         if(mFacturaVTO.getNetoNoGravado()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getNetoNoGravado().doubleValue());     
		         }
		         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getIva()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getIva().doubleValue());  
		         		                 
	        	 ind=ind+1;
	        	 c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getIva2()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getIva2().doubleValue());  
	        	 
		         ind=ind+1;
	        	 c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getOtros()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getOtros().doubleValue()); 
	         
	         
	        
	        	 //precio neto
	        	 ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getImpuestoInterno()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getImpuestoInterno().doubleValue());
		         
//			       tasa fondo
	        	 ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getTasaFondo()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getTasaFondo().doubleValue());			       

	        	 //itc
	        	 ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getItc()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getItc().doubleValue());
		         
		         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getLeyCba()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getLeyCba().doubleValue());
		         
	        	 //per iva 
	        	 ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getPerIVA()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getPerIVA().doubleValue());
		         
	        	 //per iibb 
	        	 ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getPerIIBB()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getPerIIBB().doubleValue());
		         
	        	 //nro asiento contable
	        	 ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getTotal()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getTotal().doubleValue());	
		         
		         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mFacturaVTO.getTipoPagoFactura()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getTipoPagoFactura());	
		         
	        
		         ind=0;
		         
		         
		       }
		
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReporteSumasSaldos(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		
		Properties props= fileUtil.getPropertiesFile();		
		try{					
			nombArch = mensajeria.getMessage().getString("sumas_saldos_excel_label")+"_"+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;				
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto2 = wb.createCellStyle();
							
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("sumas_saldos_excel_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont(); 
			HSSFFont fuenteTitulosMonto2 =  wb.createFont(); 
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	       	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
		 	
			         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
		     fuenteTitulosMonto2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
		     
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cuenta_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("descripcion_cuenta_label").toUpperCase());
		     
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("neto_label").toUpperCase());		             
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);	         
	         
	         estiloMonto2.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto2.setBorderBottom(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setBorderRight(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setFont(fuenteTitulosMonto2);         
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			AsientoContableTO percepcionesIVATO = new AsientoContableTO();
			int index =0;
			ind=0;			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 percepcionesIVATO = (AsientoContableTO)lstAManipular.get(index);
				 index++;				
				 
				 //cuenta
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(percepcionesIVATO.getCuenta());
			   	 
			   	 //descripcionde la cuenta
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getCuentaDesc()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getCuentaDesc());
			     

			     
			     //debe
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	
			    
				     if(percepcionesIVATO.getValor()==null)
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(percepcionesIVATO.getValor().doubleValue());
				
			     
	     
		         ind=0;

		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	  //  System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReporteMayorContable(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		
		Properties props= fileUtil.getPropertiesFile();		
		try{					
			nombArch = mensajeria.getMessage().getString("mayor_contable_excel_label")+"_"+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;				
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto2 = wb.createCellStyle();
							
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("mayor_contable_excel_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont(); 
			HSSFFont fuenteTitulosMonto2 =  wb.createFont(); 
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	       	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
		 	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 25)));
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 25)));
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
		     fuenteTitulosMonto2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
		     
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cuenta_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("descripcion_cuenta_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("hora_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("ccss_label").toUpperCase());
		     
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
		     
		     
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_ejercicio_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_asiento_label").toUpperCase());
		     
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("descripcion_aciento_contable_label").toUpperCase());
		     
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_compronate_label_x").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("comprobante_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("tipo_comprobante_label").toUpperCase());
		     
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("debe_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("haber_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("monto_adeudado_label").toUpperCase());
		             
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         
	         estiloMonto2.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto2.setBorderBottom(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setBorderRight(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setFont(fuenteTitulosMonto2);
	         
	        
	         
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			AsientoContableTO percepcionesIVATO = new AsientoContableTO();
			int index =0;
			ind=0;	
			
						
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 percepcionesIVATO = (AsientoContableTO)lstAManipular.get(index);
				 index++;				
				 
				 //cuenta
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(percepcionesIVATO.getCuenta());
			   	 
			   	 //descripcionde la cuenta
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getCuentaDesc()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getCuentaDesc());
			     
//			    fecha
			   	 ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(percepcionesIVATO.getFecha());
		         
			    
			     //hora
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getHora()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getHora());
			     
			     
			     //ccss
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getCcssDesc()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getCcssDesc());
			     
			     
			     //cod cliente alfa
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getCodClienteAlfa()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getCodClienteAlfa());
			     
			     
			     //cliente
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getDescripcionCliente()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getDescripcionCliente());
			     
			     //nro ejercicio
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getNroEjercicio()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getNroEjercicio());
			     
			     //nro asiento
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getNroAsiento()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getNroAsiento());
			     
			     //leyenda
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getLeyenda()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getLeyenda());
			     
			     //nrocomprobante
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);
			     if(percepcionesIVATO.getNroSuc()==null){
			    	 c.setCellValue(percepcionesIVATO.getNroComp()+"");
			     }else{
			    	 c.setCellValue(percepcionesIVATO.getNroSuc()+"" + " "+percepcionesIVATO.getNroComp()+"");
			     }
			    
			     
//			   comprobante
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getTipoComprobante()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getTipoComprobante());
			     
//				   tipo comprobante
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getLetra()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getLetra());			     
			     
			     
			     //debe
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	
			     if(percepcionesIVATO.getDebHab().equals("D")){
				     if(percepcionesIVATO.getValor()==null)
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(percepcionesIVATO.getValor().doubleValue());
				}
			     
//			   haber			 
		     ind=ind+1;
		     c = r.createCell((short)ind);	
		     c.setCellStyle(estiloMonto);	
		     if(percepcionesIVATO.getDebHab().equals("H")){
			     if(percepcionesIVATO.getValor()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getValor().doubleValue());
			}
	
//			   saldo			 
		     ind=ind+1;
		     c = r.createCell((short)ind);	
		     
		     if(percepcionesIVATO.getUltimo()!=null && percepcionesIVATO.getUltimo().equals(new Integer(1))){
		    	 c.setCellStyle(estiloMonto2);	 
		     }else{
		    	 c.setCellStyle(estiloMonto);	
		     }		    
		     
			if(percepcionesIVATO.getSaldo()==null)
		      	 c.setCellValue("");
		    else
		      	 c.setCellValue(percepcionesIVATO.getSaldo().doubleValue());
					     
		    ind=0;  			   
		         

		   }
	        
			
				
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();			
		
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReporteLibroDiario(List lstAManipular,String fecha,int tipoUsu,Date fltFechaDesde,Date fltFechaHasta) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		
		Properties props= fileUtil.getPropertiesFile();		
		try{					
			nombArch = mensajeria.getMessage().getString("archivo_txt_libro_diario_label")+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;				
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estilofilasNegrita = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto2 = wb.createCellStyle();
			HSSFCellStyle estiloMonto3 = wb.createCellStyle();
			HSSFCellStyle estiloTituloEncabezado = wb.createCellStyle();
							
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("mayor_contable_excel_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont(); 
			HSSFFont fuenteTitulosEncabezado =  wb.createFont(); 
			HSSFFont fuenteTitulosMonto2 =  wb.createFont(); 
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
	       	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 8)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 7)));
		 	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 9)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 9)));
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 25)));
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 25)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			
			
			         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
		     fuenteTitulosMonto2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
		     
		     fuenteTitulosEncabezado.setColor((short)0x36);
		     fuenteTitulosEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTituloEncabezado.setFont(fuenteTitulosEncabezado);	        
	        
	         DataUtil datautil =  new DataUtil();
	         SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
	 		 SimpleDateFormat sdfAnio = new SimpleDateFormat("yyyy");
	         String mes = sdfMes.format(fltFechaDesde);
	 		 mes = datautil.pasarAMes(mes);
	 		 String anio = sdfAnio.format(fltFechaDesde);
	 		 SimpleDateFormat sdfformatoFecha = new SimpleDateFormat("dd/MM/yy");
	         int col=0;
		     r = s.createRow(0);
		     c = r.createCell((short)col);
		     c.setCellStyle(estiloTituloEncabezado);
		     c.setCellValue(mensajeria.getMessage().getString("refineria_norte_titulo").toUpperCase());
		     
		     r = s.createRow(1);
		     c = r.createCell((short)col);
		     c.setCellStyle(estiloTituloEncabezado);
		     c.setCellValue(mensajeria.getMessage().getString("diario_titulo").toUpperCase());
		     
		     r = s.createRow(2);
		     c = r.createCell((short)col);
		     c.setCellStyle(estiloTituloEncabezado);
		     c.setCellValue(mes.toUpperCase()+" "+anio);
		     
		     r = s.createRow(3);
		     c = r.createCell((short)col);
		     c.setCellStyle(estiloTituloEncabezado);
		     c.setCellValue(mensajeria.getMessage().getString("proceso_titulo").toUpperCase()+" "+sdfformatoFecha.format(fltFechaDesde)+" al "+sdfformatoFecha.format(fltFechaHasta));
		     
		     
		     
		     
		     
			 r = s.createRow(4);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_libro_diario").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("tipo_doc_libro_diario").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("centro_servicio_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("ejercicio_libro_diario").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("asiento_libro_asiento").toUpperCase());
		     
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_factuta_libro_diario").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("descripcion_aciento_contable_label").toUpperCase());		     
		     
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cuenta_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("libro_auxiliar_libro_diario").toUpperCase());
		     
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cuenta_id_libro_diario").toUpperCase());
		     
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("descripcion_cuenta_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("debe_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("haber_label").toUpperCase());
		     
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         
	         estiloMonto2.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto2.setBorderBottom(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setBorderRight(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setFont(fuenteTitulosMonto2);
	         
	         estiloMonto3.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto3.setBorderBottom(estiloMonto3.BORDER_THIN);
	         estiloMonto3.setBorderRight(estiloMonto3.BORDER_THIN);
	         estiloMonto3.setBorderTop(estiloMonto3.BORDER_THICK);	 
	         estiloMonto3.setFont(fuenteTitulosMonto2);
	         
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 
	 		 
	 		 
	 		
	 		estilofilasNegrita.setBorderBottom(estilofilas.BORDER_THIN);
	 		estilofilasNegrita.setBorderRight(estilofilas.BORDER_THIN);
	 		estilofilasNegrita.setBorderLeft(estilofilas.BORDER_THIN);
	 		estilofilasNegrita.setBorderTop(estilofilas.BORDER_THIN);	
	 		estilofilasNegrita.setFont(fuenteTitulosMonto2);
	 		
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 5+ lstAManipular.size();			
			AsientoContableTO asientoContableTO = new AsientoContableTO();
			int index =0;
			ind=0;	
			
						
			for (rownum = (short) 5; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 asientoContableTO = (AsientoContableTO)lstAManipular.get(index);
				 index++;				
				 
				 //fecha
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	
			     if(asientoContableTO.getFecha()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(asientoContableTO.getFecha());
			   	 
			   	 
//				   tipo documento
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(asientoContableTO.getTipoComp()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(asientoContableTO.getTipoComp()+" "+asientoContableTO.getLetra());	
			     
			     //ccss
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(asientoContableTO.getCcssDesc()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(asientoContableTO.getCcddId());
			     
			     //nro ejercicio
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(asientoContableTO.getNroEjercicio()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(asientoContableTO.getNroEjercicio());
			     
			     //nro asiento
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(asientoContableTO.getNroAsiento()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(asientoContableTO.getNroAsiento());
			     
			     //nrocomprobante
			     ind=ind+1;
			   
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);
			     if(asientoContableTO.getNroSuc()==null && asientoContableTO.getNroComp()==null){
			    	 c.setCellValue(""); 			    	 
			     }else{
			    	 c.setCellValue(datautil.rellenarConCeros(4, asientoContableTO.getNroSuc()==null?"": asientoContableTO.getNroSuc().toString())+ ""+datautil.rellenarConCeros(8, asientoContableTO.getNroComp()==null?"": asientoContableTO.getNroComp().toString()));
			     }
			    
			     //descr del asiento
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(asientoContableTO.getLeyenda()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(asientoContableTO.getLeyenda());
			     
			     
			     //nro de cuenta
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(asientoContableTO.getCuenta()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(asientoContableTO.getCuenta());
			     
//			   libro auxiliar
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);
			     c.setCellValue("");
			     
			     //id de la cuenta
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(asientoContableTO.getIdCuenta()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(asientoContableTO.getIdCuenta());		     
			     
			     
			   	 //descripcionde la cuenta
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(asientoContableTO.getCuentaDesc()==null)
		        	 c.setCellValue("");
		         else{
		        	 
		        	 if(asientoContableTO.getUltimo()!=null){        	
		        		 c.setCellStyle(estilofilasNegrita);	      
		        	 }
		        	 c.setCellValue(asientoContableTO.getCuentaDesc());
		         }
			     
//			  
			     
		     
			     
			     
			     //debe
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);
			     
			     if(asientoContableTO.getDebHab()!=null){
				     if(asientoContableTO.getDebHab().equals("D")){
					     if(asientoContableTO.getValor()==null)
				        	 c.setCellValue("");
				         else
				        	 c.setCellValue(asientoContableTO.getValor().doubleValue());
					}
			     } else{			     
				     if(asientoContableTO.getUltimo().equals(new Integer(1))){
				    	 c.setCellStyle(estiloMonto3);	
					     if(asientoContableTO.getValorDebe()==null)
				        	 c.setCellValue("");
				         else
				        	 c.setCellValue(asientoContableTO.getValorDebe().doubleValue());
					}
				 }
			     
//			   haber			 
		     ind=ind+1;
		     c = r.createCell((short)ind);	
		     c.setCellStyle(estiloMonto);	
		     if(asientoContableTO.getDebHab()!=null){
			     if(asientoContableTO.getDebHab().equals("H")){
				     if(asientoContableTO.getValor()==null)
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(asientoContableTO.getValor().doubleValue());
				}
		     } else{
			     if(asientoContableTO.getUltimo().equals(new Integer(1))){
			    	 c.setCellStyle(estiloMonto3);	
				     if(asientoContableTO.getValorDebe()==null)
			        	 c.setCellValue("");
			         else			        	
			        	 c.setCellValue(asientoContableTO.getValorHaber().doubleValue());
			         
				}
			 }
		     
				     
		    ind=0;  			   
		         

		   }
	        
			
				
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();			
		
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	public static long usoMem(){
		return Runtime.getRuntime().totalMemory() -
			Runtime.getRuntime().freeMemory();
	}

	
	public String generarExcelReportePercepcionesIIBB(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		
		Properties props= fileUtil.getPropertiesFile();		
		try{					
			nombArch = mensajeria.getMessage().getString("percepciones_iibb_excel_label")+"_"+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;				
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto2 = wb.createCellStyle();
			HSSFCellStyle estiloMonto1 = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("percepciones_iibb_excel_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  		
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
		 	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 13)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 13)));
			ind=ind+1;	
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 17)));
			ind=ind+1;	
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 14)));
			
			      
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cuit_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_iibb_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("tipo_doc_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("tipo_comprobante_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_factura_largo_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("importe_factura_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_certificado_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("bruto_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("neto_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("itc_tasa_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("ley_10081_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("base_imponible_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("alicuota_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("percepcion_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("provincia_label").toUpperCase());
		     
	        
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         
	         estiloMonto2.setDataFormat(df.getFormat("#,##0.0000"));	
	         estiloMonto2.setBorderBottom(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setBorderRight(estiloMonto2.BORDER_THIN);
	         
	         estiloMonto1.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto1.setBorderBottom(estiloMonto1.BORDER_THIN);
	         estiloMonto1.setBorderRight(estiloMonto1.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			PercepcionesIIBBTO percepcionesIVATO = new PercepcionesIIBBTO();
			int index =0;
			ind=0;			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 percepcionesIVATO = (PercepcionesIIBBTO)lstAManipular.get(index);
				 index++;				
				 
				 //cod cliente alfa
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(percepcionesIVATO.getCodClienteAlfa());
			   	 
			   	 
//			     razon social cliente
			   	 ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(percepcionesIVATO.getDescripcionCli());
		         
			    
			     //cuit cliente
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getCuit()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getCuit());
			     
			     
			     //cuit cliente
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getNroIIBB()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getNroIIBB());
			     
			     
			     //fecha
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getFechaEmisionComprobante()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getFechaEmisionComprobante());
			     
			     //tipo comprobante
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getTipo()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getTipo());
			     
			     //tipo comprobante
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getTipoComprobante()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getTipoComprobante());
			     
//			   nro factura
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getNroFacturaCompuesto()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getNroFacturaCompuesto());
			     
//				  monto de la factrura
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(percepcionesIVATO.getImporteFactura()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getImporteFactura().doubleValue());	
			     
//				   nro certificado
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getNroCertificado()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getNroCertificado());
			     
			     //articulo/producto
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getDescArticulo()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getDescArticulo());
			     
//				   bruto
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(percepcionesIVATO.getBruto()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getBruto().doubleValue());			     
			     
//				   neto
			     
			    // System.out.println("nro de factura "+percepcionesIVATO.getNroFacturaCompuesto());
			     
			    // System.out.println(percepcionesIVATO.getNetoCalculado());
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(percepcionesIVATO.getNetoCalculado()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getNetoCalculado().doubleValue());						 
			     
//				   itc y tasa
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(percepcionesIVATO.getItcTasa()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getItcTasa().doubleValue());
			     
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(percepcionesIVATO.getLeyCba()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getLeyCba().doubleValue());
			     
//				   base imponible
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(percepcionesIVATO.getBaseImponible()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getBaseImponible().doubleValue());  
			     
//				   alicuota
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(percepcionesIVATO.getAlicuota()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getAlicuota().doubleValue());  
			     
//				  percepcion
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto1);	        
			     if(percepcionesIVATO.getPercepcion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getPercepcion().doubleValue()); 
			     
			     
//			   jurisdiccion
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getProvincia()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getProvincia());
			     
		         ind=0;

		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	  //  System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarTXTReportePercepcionesIVA(List lstAManipular,String agente,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
	
		Properties props= fileUtil.getPropertiesFile();		
		try{			
			nombArch = mensajeria.getMessage().getString("percepciones_iva_label_x")+".txt";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);
	
			String url= props.getProperty("saveArchivosExcel") + nombArch;
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);
	   		
	   		File f = new File(url);	 
	   		
	        FileWriter writer=null;
			try {
				writer = new FileWriter(f,false);						
			} catch (IOException e) {						
				e.printStackTrace();
			}		
			
	        BufferedWriter salida = new BufferedWriter(writer);	  
	        SimpleDateFormat sdfArchivo = new SimpleDateFormat("dd/MM/yyyy");       
	        String fila="";
	        DataUtil datautil =  new DataUtil();      	        
	        if(lstAManipular!=null){
		   		Iterator itObj = lstAManipular.iterator();
		   		PercepcionesIVATO percepcionesIVATO = new PercepcionesIVATO();
		   			
		   		while(itObj.hasNext()){
		   			percepcionesIVATO = (PercepcionesIVATO)itObj.next();
		   			fila= 	datautil.rellenarConBlancos(2,datautil.obtenerCampoString(percepcionesIVATO.getCodComprobanteInt()),false)
		   					+ percepcionesIVATO.getFechaEmisionComprobante()	   			
		   					+datautil.rellenarConBlancos(16,datautil.rellenarConCeros(4,percepcionesIVATO.getNroSucursal().toString())+datautil.rellenarConCeros(8,percepcionesIVATO.getNroFactura().toString()),false)
				   			
		   					+datautil.rellenarConBlancos(16,datautil.obtenerDecimalFormateado(percepcionesIVATO.getImporte().setScale(2, BigDecimal.ROUND_HALF_UP)),true)
		   					+datautil.rellenarConBlancos(3,datautil.obtenerCampoString(percepcionesIVATO.getCodImpuesto()),true)
		   					+datautil.rellenarConBlancos(3,datautil.obtenerCampoString(percepcionesIVATO.getCodRegimen()),true)
		   					+datautil.rellenarConBlancos(1,datautil.obtenerCampoString(percepcionesIVATO.getCodOperacion()),true)
		   					+datautil.rellenarConBlancos(14,datautil.obtenerDecimalFormateado(percepcionesIVATO.getBaseCalculo().setScale(2, BigDecimal.ROUND_HALF_UP)),true)
		   					+ percepcionesIVATO.getFechaEmisionRetencion()
		   					+datautil.rellenarConBlancos(2,datautil.obtenerCampoString(percepcionesIVATO.getCodCondicion()),false)
		   					+datautil.rellenarConBlancos(14,datautil.obtenerDecimalFormateado(percepcionesIVATO.getMontoDeLaRetencion().setScale(2, BigDecimal.ROUND_HALF_UP)),true)
		   					+datautil.rellenarConBlancos(6,datautil.obtenerDecimalFormateado(percepcionesIVATO.getPorcExclusion().setScale(2, BigDecimal.ROUND_HALF_UP)),true)
		   					;
		   					
		   					//System.out.println("Tamaï¿½o de fila medidad de seguridad  --> "+fila.length());
		   			try {
						salida.write(fila);
						salida.newLine();
					} catch (IOException e) {							
						e.printStackTrace();
					}		   			
		   		}
		   		
		   		try {
					//GRABA INTERFAZ
					salida.close();
				} catch (IOException e) {						
					e.printStackTrace();
				}
			   			
	   		  }	
					
			
			
		}/*catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}*/catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReportePercepcionesIVA(List lstAManipular,String agente,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
	
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("percepciones_iva_label")+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto2 = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("percepciones_iva_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  		
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
		 	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			
			      
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cod_comprobante_label_x").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_emision_comprobante_label_x").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_compronate_label_x").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("importe_comprobante_x").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cod_impuesto_label_x").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cod_regimen_label_x").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cod_operacion_label_x").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("base_calculo_label_x").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_emision_retencion_label_x").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cod_condicion_label_x").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("monto_retencion_label_x").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("porcentaje_retencion_label_x").toUpperCase());
		     
	        
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         
	         estiloMonto2.setDataFormat(df.getFormat("#,##0.0000"));	
	         estiloMonto2.setBorderBottom(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setBorderRight(estiloMonto2.BORDER_THIN);
	         
	         
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			PercepcionesIVATO percepcionesIVATO = new PercepcionesIVATO();
			int index =0;
			ind=0;			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 percepcionesIVATO = (PercepcionesIVATO)lstAManipular.get(index);
				 index++;				
				 
				 //cod comprobante
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(percepcionesIVATO.getCodComprobanteInt());
			   	 
			   	 
//			     fecha emision comp
			   	 ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(percepcionesIVATO.getFechaEmisionComprobante());
		         
			    
			     //nro compro
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getNroSucursal()==null || percepcionesIVATO.getNroFactura()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getNroSucursal().toString()+percepcionesIVATO.getNroFactura());
			     
			     
			     //importe
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(percepcionesIVATO.getImporte()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getImporte().doubleValue());
			     
			     //cod impuesto
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getCodImpuesto()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getCodImpuesto());
			     
//			   cod regimen
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getCodRegimen()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getCodRegimen());
			     
//				   cod operacion
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getCodOperacion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getCodOperacion());
			     
//				   base de calculo
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(percepcionesIVATO.getBaseCalculo()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getBaseCalculo().doubleValue());			     
			     
				 
			     //fecha emision retencion
			   	 ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(percepcionesIVATO.getFechaEmisionRetencion());
			   	 
			   	 
//				   cod condicion
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(percepcionesIVATO.getCodCondicion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getCodCondicion());
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(percepcionesIVATO.getMontoDeLaRetencion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getMontoDeLaRetencion().doubleValue());	
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(percepcionesIVATO.getPorcExclusion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(percepcionesIVATO.getPorcExclusion().doubleValue());	
			     
			     
		         ind=0;

		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReporteExclusion(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
	
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("exclusion_impuesto_por_cliente_label")+"_"+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto2 = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("exclusion_impuesto_por_cliente_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  		
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 14)));
		 	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 25)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18))); 
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_vigencia_desde_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_vigencia_hasta_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("impuesto_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("porcentaje_exclusion_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("provincia_label").toUpperCase());
		     		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         
	         estiloMonto2.setDataFormat(df.getFormat("#,##0.0000"));	
	         estiloMonto2.setBorderBottom(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setBorderRight(estiloMonto2.BORDER_THIN);
	         
	         
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			MExclusionTO mExclusionTO = new MExclusionTO();
			int index =0;
			ind=0;			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 mExclusionTO = (MExclusionTO)lstAManipular.get(index);
				 index++;				
				 
				 //fecha inicio de la vigencia
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(mExclusionTO.getFechaDesde());
		         
			    
			     //fecha fin de la vigencia
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mExclusionTO.getFechaHasta()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mExclusionTO.getFechaHasta());
			    
			    		     
			     //cliente
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mExclusionTO.getCliDescripcion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mExclusionTO.getCliDescripcion());
			     
			     //cod cliente alfa
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mExclusionTO.getCodClienteAlfa()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mExclusionTO.getCodClienteAlfa());
			     
//			   descripcion del impuesto
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mExclusionTO.getTipoExcDescripcion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mExclusionTO.getTipoExcDescripcion());
			     
//				   porcentaje de la esxclusion
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(mExclusionTO.getPorcentajeExclusion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mExclusionTO.getPorcentajeExclusion().doubleValue());
			     
		     
			     //provincia
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mExclusionTO.getProvDescripcion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mExclusionTO.getProvDescripcion());
			     
			   
			   	 
		         ind=0;

		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReporteDetallesRendicion(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
	
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("detalles_rendiciones_label")+"_"+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto2 = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("detalles_rendiciones_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  		
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
		 	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			      
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_factura_largo_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("centro_servicio_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cantidad_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("precio_solo_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("total_pesos_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("patente_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("chofer_label").toUpperCase());
		     
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("total_subdiario_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_rendicion_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_rendicion_label").toUpperCase());
		     
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         
	         estiloMonto2.setDataFormat(df.getFormat("#,##0.0000"));	
	         estiloMonto2.setBorderBottom(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setBorderRight(estiloMonto2.BORDER_THIN);
	         
	         
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			MFacturaVTO mFacturaVTO = new MFacturaVTO();
			int index =0;
			ind=0;			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 mFacturaVTO = (MFacturaVTO)lstAManipular.get(index);
				 index++;				
				 
				 //fecha
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(mFacturaVTO.getFecha());
		         
			    
			     //nro factura
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mFacturaVTO.getNroSucursal()==null || mFacturaVTO.getNroFactura()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getNroSucursal().toString()+" - " +mFacturaVTO.getNroFactura());
			    
			     //ccss
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mFacturaVTO.getCcss()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getCcss());
			     
			     
			     //cliente
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mFacturaVTO.getCliDescripcion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getCliDescripcion());
			     
//			   producto
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mFacturaVTO.getDescArticulo()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getDescArticulo());
			     
			     
//				   cantidad
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(mFacturaVTO.getCantidad()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getCantidad().doubleValue());
			     
//				   precio kilo
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto2);	        
			     if(mFacturaVTO.getPrecioKilo()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getPrecioKilo().doubleValue());	
			     
//				   precio total
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(mFacturaVTO.getPreciototal()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getPreciototal().doubleValue());	
			     
			     
			     //patente
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mFacturaVTO.getPatente()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getPatente());
			     
			     
			     //chofer
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     c.setCellValue(mFacturaVTO.getApellidoChofer()+", "+mFacturaVTO.getNombreChofer());
			     
//				 total subdiario
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(mFacturaVTO.getMontoTotalSubdiario()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getMontoTotalSubdiario().doubleValue());	
			     
//				 nro rendicion
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mFacturaVTO.getNroRendicionRefipass()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getNroRendicionRefipass());
			     
			     //fecha renficion
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(mFacturaVTO.getFechaRendicion());
			   	 
		         ind=0;

		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	
	public String generarExcelReporteRendicionesPendientes(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
			String nombArch="";
			short rownum;
			FileUtil fileUtil= new FileUtil();
			Messages mensajeria = new Messages();
			SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
			Properties props= fileUtil.getPropertiesFile();		
			try{					
				nombArch = mensajeria.getMessage().getString("detalle_transferencias_label")+"_"+fecha+".xls";			
				
				//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
				FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet s = wb.createSheet();		
				HSSFRow r = null;
				HSSFCell c = null;	
				
				sdf= new SimpleDateFormat("dd/MM/yyyy");
				
				HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
				HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
				HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
				HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
				HSSFCellStyle estiloTitulo = wb.createCellStyle();
				HSSFCellStyle estiloTitulo1 = wb.createCellStyle();
				HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
				HSSFCellStyle estiloTitulo3 = wb.createCellStyle();
				HSSFCellStyle estilofilas = wb.createCellStyle();
				HSSFCellStyle estiloMonto = wb.createCellStyle();
					
				String etiqueta="";				
				etiqueta=mensajeria.getMessage().getString("detalle_transferencias_label");		
				
				wb.setSheetName(0, etiqueta, 
				                HSSFWorkbook.ENCODING_UTF_16 );
				
				HSSFFont fuenteTitulos =  wb.createFont();  
				HSSFFont fuenteTitulosCliente =  wb.createFont();  
		        HSSFFont fuenteEtiquetas =  wb.createFont();		
				HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
				HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
				HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
				
				//para formate de datos
				HSSFDataFormat df = wb.createDataFormat();
				
				//estilo encabezado 
				fuenteEtiquetasEncabezado.setColor((short)0x12);
				fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
				fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
				estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
				
			   
		       	     
		         //titulos               
		         fuenteTitulos.setColor((short)0x9);
		         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
		         
		         fuenteTitulosCliente.setColor((short)0x27);
		         fuenteTitulosCliente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
		         estiloTitulo.setFont(fuenteTitulosCliente);
		         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);	
		         
		         fuenteEtiquetas.setFontHeightInPoints((short) 12);
		         fuenteEtiquetas.setColor((short)0x20);
		         fuenteEtiquetas.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);         
		         estiloTitulo1.setFont(fuenteEtiquetas);	       
		         estiloTitulo1.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);	
		         
		         estiloTitulo2.setFont(fuenteTitulos);        
			     estiloTitulo2.setFillForegroundColor((short) 0x2c);	
			     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
			     estiloTitulo2.setBorderBottom(estiloTitulo2.BORDER_THIN);
			     estiloTitulo2.setBorderRight(estiloTitulo2.BORDER_THIN);
			     estiloTitulo2.setBorderLeft(estiloTitulo2.BORDER_THIN);
			     estiloTitulo2.setBorderTop(estiloTitulo2.BORDER_THIN);	 
			     
			     estiloTitulo3.setFont(fuenteTitulos);    
			     estiloTitulo3.setFillForegroundColor((short) 0x16);	
			     estiloTitulo3.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);	     
			     estiloTitulo3.setBorderBottom(estiloTitulo3.BORDER_THIN);
			     estiloTitulo3.setBorderRight(estiloTitulo3.BORDER_THIN);
			     estiloTitulo3.setBorderLeft(estiloTitulo3.BORDER_THIN);
			     estiloTitulo3.setBorderTop(estiloTitulo3.BORDER_THIN);	 
		 		 
		         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
		         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
		         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
		         
		         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
		 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
		 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
		 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
		 		 
		 		 HSSFFont fuenteEstado =  wb.createFont();
			     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
			         
				 //fila en la empieza la lista de datos 
				Integer cantFilas = 4+ lstAManipular.size();			
				int index =0;
				int ind=0;		
				
				 r = s.createRow(1);
				 c = r.createCell((short)0);
				 c.setCellStyle(estiloTitulo1);
			     c.setCellValue("Reporte: DETALLE DE TRANSFERENCIAS");
			  
			    CcssRendicionTransferenciaTO clienteFacturaTO = new CcssRendicionTransferenciaTO();
				Iterator itCliente = lstAManipular.iterator();
				Iterator itFacturas =null;
				Iterator itDocumentosAplicados =null;
				RendicionTranfTO mFacturaVTO= new RendicionTranfTO();
				MCuentaBancoTO documentoAplicadoTO= new MCuentaBancoTO();
				int fila =  4;
				String nroComprob="";
				while(itCliente.hasNext()){
					 clienteFacturaTO = (CcssRendicionTransferenciaTO)itCliente.next();
					 
					 //titulo y datos cliente
					 if(clienteFacturaTO.getRendiciones()!=null && clienteFacturaTO.getRendiciones().size()>0){
					 
					 r = s.createRow(fila);
					 c = r.createCell((short)0);
					 c.setCellStyle(estiloTitulo);
				     c.setCellValue("CCSS: " +clienteFacturaTO.getCcss().getDescCcss());
				     
				    
				     itFacturas = clienteFacturaTO.getRendiciones().iterator();
				     while(itFacturas.hasNext()){
				    	 
					    	 mFacturaVTO = (RendicionTranfTO)itFacturas.next();
					     	//Ancho de columnas	 del titulo de factura      
							ind=0;       	
							s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 20)));			
							ind=ind+1;
							s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));			
							ind=ind+1;
							s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
							ind=ind+1;
							s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
							ind=ind+1;
							s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 20)));
							ind=ind+1;
							s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
							ind=ind+1;
							s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
							
							//titulo de factura
						     fila = fila+2;
						     r = s.createRow(fila);			     
						     ind=0;
					         c = r.createCell((short)ind);
					         c.setCellStyle(estiloTitulo2);
					         c.setCellValue(mensajeria.getMessage().getString("operacion_label").toUpperCase());			         
					         ind=ind+1;
					         c = r.createCell((short)ind);
					         c.setCellStyle(estiloTitulo2);
					         c.setCellValue(mensajeria.getMessage().getString("nro_rendicion_label").toUpperCase());   
					         ind=ind+1;        
					         c = r.createCell((short)ind);
					         c.setCellStyle(estiloTitulo2);
						     c.setCellValue(mensajeria.getMessage().getString("nro_ejercicio_label").toUpperCase());
						     ind=ind+1;        
					         c = r.createCell((short)ind);
					         c.setCellStyle(estiloTitulo2);
						     c.setCellValue(mensajeria.getMessage().getString("nro_asiento_label").toUpperCase());		
						     ind=ind+1;        
					         c = r.createCell((short)ind);
					         c.setCellStyle(estiloTitulo2);
						     c.setCellValue(mensajeria.getMessage().getString("cuenta_label").toUpperCase());

						     ind=ind+1;        
					         c = r.createCell((short)ind);
					         c.setCellStyle(estiloTitulo2);
						     c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());
						     
						     ind=ind+1;        
					         c = r.createCell((short)ind);
					         c.setCellStyle(estiloTitulo2);
						     c.setCellValue(mensajeria.getMessage().getString("importe_label").toUpperCase());
						     
						     
						     //datos de casa fila de facturas
						     fila = fila+1;
						     r = s.createRow(fila);			     
							 ind=0;
						     c = r.createCell((short)ind);
						     c.setCellStyle(estilofilas);
						     c.setCellValue(mFacturaVTO.getRendicion().getTipoOperacion()!=null ? mFacturaVTO.getRendicion().getTipoOperacion():"");
						         
					         ind=ind+1;
					         c = r.createCell((short)ind);
					         c.setCellStyle(estilofilas);
					         c.setCellValue(mFacturaVTO.getRendicion().getNroRendicion()!=null?mFacturaVTO.getRendicion().getNroRendicion().doubleValue():0);
					         
					         ind=ind+1;
					         c = r.createCell((short)ind);
					         c.setCellStyle(estilofilas);
					         c.setCellValue(mFacturaVTO.getRendicion().getNroEjercicio()!=null?mFacturaVTO.getRendicion().getNroEjercicio().doubleValue():0);
					         
					         ind=ind+1;
					         c = r.createCell((short)ind);
					         c.setCellStyle(estilofilas);
					         c.setCellValue(mFacturaVTO.getRendicion().getNroAsiento()!=null?mFacturaVTO.getRendicion().getNroAsiento().doubleValue():0);
					         
					         
					         ind=ind+1;
					         c = r.createCell((short)ind);
						     c.setCellStyle(estilofilas);
						     c.setCellValue(mFacturaVTO.getRendicion().getCuentaBanco()!=null ? mFacturaVTO.getRendicion().getCuentaBanco():"");
						 
						     ind=ind+1;
					         c = r.createCell((short)ind);
						     c.setCellStyle(estilofilas);
						     c.setCellValue(mFacturaVTO.getRendicion().getFecha()!=null ? mFacturaVTO.getRendicion().getFecha():"");
						   
						     ind=ind+1;        
					         c = r.createCell((short)ind);
					         c.setCellStyle(estiloMonto);
					         c.setCellValue(mFacturaVTO.getRendicion().getImporte()!=null ? mFacturaVTO.getRendicion().getImporte().doubleValue():0);	
						     
						     
					         if(mFacturaVTO.getLstTransferencias()!=null && mFacturaVTO.getLstTransferencias().size()>0){
						         itDocumentosAplicados = mFacturaVTO.getLstTransferencias().iterator();
						         
//						       /*titulo de documentos aplicados
							 /*    fila = fila+1;
							     r = s.createRow(fila);			     
							     ind=0;
						         c = r.createCell((short)ind);
						         c.setCellStyle(estiloTitulo3);
						         c.setCellValue(mensajeria.getMessage().getString("fecha_label"));			         
						         ind=ind+1;
						         c = r.createCell((short)ind);
						         c.setCellStyle(estiloTitulo3);
						         c.setCellValue(mensajeria.getMessage().getString("comprobante_label"));   
						         ind=ind+1;        
						         c = r.createCell((short)ind);
						         c.setCellStyle(estiloTitulo3);
							     c.setCellValue(mensajeria.getMessage().getString("nro_comprobante_label"));
							     ind=ind+1;        
						         c = r.createCell((short)ind);
						         c.setCellStyle(estiloTitulo3);
							     c.setCellValue(mensajeria.getMessage().getString("monto_tot_label"));		
							     ind=ind+1;        
						         c = r.createCell((short)ind);
						         c.setCellStyle(estiloTitulo3);
							     c.setCellValue(mensajeria.getMessage().getString("monto_adeudado_label"));*/
							 
							     while(itDocumentosAplicados.hasNext()){					    	 
							    	 documentoAplicadoTO = (MCuentaBancoTO)itDocumentosAplicados.next();
										
									     //datos de casa fila de docum
									     fila = fila+1;
									     r = s.createRow(fila);			     
										 ind=0;
									     c = r.createCell((short)ind);
									     c.setCellStyle(estilofilas);
									     c.setCellValue(documentoAplicadoTO.getTipoOperacion()!=null ? documentoAplicadoTO.getTipoOperacion():"");
									         
								         ind=ind+1;
								         c = r.createCell((short)ind);
								         c.setCellStyle(estilofilas);
								         c.setCellValue(documentoAplicadoTO.getNroRendicion()!=null ? documentoAplicadoTO.getNroRendicion():0);
								         
								         ind=ind+1;
								         c = r.createCell((short)ind);
								         c.setCellStyle(estilofilas);
								         c.setCellValue(documentoAplicadoTO.getNroEjercicio()!=null ? documentoAplicadoTO.getNroEjercicio():0);
								     
								         ind=ind+1;
								         c = r.createCell((short)ind);
								         c.setCellStyle(estilofilas);
								         c.setCellValue(documentoAplicadoTO.getNroAsiento()!=null ? documentoAplicadoTO.getNroAsiento():0);
			
								         ind=ind+1;        
								         c = r.createCell((short)ind);
								         c.setCellStyle(estilofilas);
								         c.setCellValue(documentoAplicadoTO.getCuentaBanco()!=null ? documentoAplicadoTO.getCuentaBanco():"");
								         
								         ind=ind+1;        
								         c = r.createCell((short)ind);
								         c.setCellStyle(estilofilas);
								         c.setCellValue(documentoAplicadoTO.getFecha()!=null ? documentoAplicadoTO.getFecha():"");
								        
								         
									     ind=ind+1;        
								         c = r.createCell((short)ind);
								         c.setCellStyle(estiloMonto);
								         c.setCellValue(documentoAplicadoTO.getImporte()!=null ? documentoAplicadoTO.getImporte().doubleValue():0);	
									     
								         
							     }   
							     
					         }
							         
							     
							     
							     
				     }	     
						     
					 fila = fila+2; 
					 
					 }	
			         
				}
		         
				s = wb.createSheet();
				wb.setSheetName(1, "DeletedSheet");
				wb.removeSheetAt(1);
				
				wb.write(out);
				out.close();
			}catch(FileNotFoundException ex){
				ex.printStackTrace();
				throw new FileNotFoundException();
			}catch(IOException ex){
				ex.printStackTrace();
				throw new IOException();		
			}catch(Exception ex){
				ex.printStackTrace();
				throw new Exception();		
			}
		    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
			return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
		}
	
	
	public String generarExcelReporteDetalleTransferencias(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
	
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("detalle_transferencias_label")+"_"+fecha + ".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("detalle_transferencias_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  		
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
		 	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("operacion_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("importe_pendiente_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("dias_pendientes_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("importe_pendiente_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("dias_pendientes_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("dias_pendientes_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("dias_pendientes_label").toUpperCase());
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			RendicionPendienteTO rendicionPendienteTO = new RendicionPendienteTO();
			int index =0;
			ind=0;			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 rendicionPendienteTO = (RendicionPendienteTO)lstAManipular.get(index);
				 index++;				
				 
				 //ccss
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(rendicionPendienteTO.getDescrCCSS());
		         
			    
			     //importe
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(rendicionPendienteTO.getImporte()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(rendicionPendienteTO.getImporte().doubleValue());
			     
			     //cant de dias
			     ind=ind+1;
			     c = r.createCell((short)ind);	
				 c.setCellStyle(estilofilas);	
				 if(rendicionPendienteTO.getCantidadDias()!=-1){
					 c.setCellValue(rendicionPendienteTO.getCantidadDias());
				 }else if(rendicionPendienteTO.getCantidadDias()==-1){
					 c.setCellValue(mensajeria.getMessage().getString("no_info_label"));
				 }			
				 
		         ind=0;

		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReporteCombustible(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
	
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("combustible_label")+"_"+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("combustible_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  		
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
		 	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));	
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));	
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));	
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));	
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));	
			
	        
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("ccss_label").toUpperCase());
		     ind=ind+1; 
		     c = r.createCell((short)ind);
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());
			 ind=ind+1;	     	         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("litros_label").toUpperCase());
	         ind=ind+1;	     	         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("tipo_comprobante_largo_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("letra_comprobante_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_comprobante_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("empleado_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("estado_combustible_label").toUpperCase());
	         
	         
	        
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			CombustibleTO reciboTO = new CombustibleTO();
			int index =0;
			ind=0;			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 reciboTO = (CombustibleTO)lstAManipular.get(index);
				 index++;				
				 
				 //fecha
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(reciboTO.getFecha());
		         
			    
			     //ccss
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getCcssDesc()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getCcssDesc());
			     
			     //producto
			     ind=ind+1;
			     c = r.createCell((short)ind);	
				 c.setCellStyle(estilofilas);	        
				 if(reciboTO.getProductoDesc()==null)
			      	 c.setCellValue("");
			     else
			       	 c.setCellValue(reciboTO.getProductoDesc());
				 	         		         
		         //litros
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(reciboTO.getLitros()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getLitros().doubleValue());
		         
		         
		         
		         //tipo comp
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         c.setCellValue(reciboTO.getTipoComprobanteDesc());
		         
		         //letra
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getLetraComprobante()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getLetraComprobante());		     
		         
		  
				 
		         
		         //nro comp
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getNroComprobante()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getNroComprobante());
			     
				 //empleado
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getResponsableDesc()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getResponsableDesc());
			     
//			   estado
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getEstadoDesc()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getEstadoDesc());
		         
		         ind=0;

		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReporteListaPrecios(List lstAManipular,String agente,int tipoUsu,String fecha) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
	
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("lista_precios_labelxls")+"_"+ fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("lista_precios_labelxls");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  		
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
		 	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));	
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));	
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));	
			    
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("ccss_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue("Lista de Precio".toUpperCase());
		     ind=ind+1; 
		     c = r.createCell((short)ind);
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());
			 ind=ind+1;	     	         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("ingresar_fecha_desde_label").toUpperCase());
	         ind=ind+1;	     	         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("ingresar_fecha_hasta_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("precio_litro_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("tipo_lista_label").toUpperCase());
	          
	        
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.0000"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			MListaPrecioTO reciboTO = new MListaPrecioTO();
			int index =0;
			ind=0;			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 reciboTO = (MListaPrecioTO)lstAManipular.get(index);
				 index++;				

				 
				 // ccss			    
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getDescripcionCCSS()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getDescripcionCCSS());
			     
			     //cliente
			     ind=ind+1;
			     c = r.createCell((short)ind);	
				 c.setCellStyle(estilofilas);	        
				 if(reciboTO.getDescripcionCliente()==null)
			      	 c.setCellValue("");
			     else
			       	 c.setCellValue(reciboTO.getDescripcionCliente());
				 
				 
			     //lista de precio
			     ind=ind+1;
			     c = r.createCell((short)ind);	
				 c.setCellStyle(estilofilas);	        
				 if(reciboTO.getDescripcionListaPrecio()==null)
			      	 c.setCellValue("");
			     else
			       	 c.setCellValue(reciboTO.getDescripcionListaPrecio());
				 
				 
			     //producto
			     ind=ind+1;
			     c = r.createCell((short)ind);	
				 c.setCellStyle(estilofilas);	        
				 if(reciboTO.getDescripcionProducto()==null)
			      	 c.setCellValue("");
			     else
			       	 c.setCellValue(reciboTO.getDescripcionProducto());
				 
				 ind=ind+1;
			     c = r.createCell((short)ind);	
				 c.setCellStyle(estilofilas);	        
				 if(reciboTO.getFechaDesde()==null)
			      	 c.setCellValue("");
			     else
			       	 c.setCellValue(reciboTO.getFechaDesde());
				 
				 ind=ind+1;
			     c = r.createCell((short)ind);	
				 c.setCellStyle(estilofilas);	        
				 if(reciboTO.getFechaHasta()==null)
			      	 c.setCellValue("");
			     else
			       	 c.setCellValue(reciboTO.getFechaHasta());
				 	         		         
		         //precio
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(reciboTO.getPrecio()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getPrecio().doubleValue());
		         
//		       tipo de lista
			     ind=ind+1;
			     c = r.createCell((short)ind);	
				 c.setCellStyle(estilofilas);	        
				 if(reciboTO.getTipoLista()==null)
			      	 c.setCellValue("");
			     else
			       	 c.setCellValue(reciboTO.getTipoLista());
		       
		         
		         ind=0;

		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	/**
	 * Metodo: generarExcelReporteRecibo
	 * Funcion: Generar excel del reporte de recibos por fecha
	 * @param lstAManipular
	 * @param agente
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */										  
	public String generarExcelReporteReciboConRetenciones(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
	
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("archivo_recibo_con_retenciones_label")+"_"+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("archivo_recibo_con_retenciones_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  		
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
		 	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));	
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));	
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));	
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));	
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 19)));	
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));	
	        
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_recibo_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_rec_label").toUpperCase());
		     ind=ind+1; 
		     c = r.createCell((short)ind);
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("tipo_retencion_label").toUpperCase());
			 ind=ind+1;	     	         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_certificado_label").toUpperCase());
	         ind=ind+1;	     	         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("fecha_certificado_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("importe_certificado_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("juridiccion_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
	         
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("estado_recibo_label").toUpperCase());
	        
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			ReciboRetencionPresTO reciboTO = new ReciboRetencionPresTO();
			int index =0;
			ind=0;			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 reciboTO = (ReciboRetencionPresTO)lstAManipular.get(index);
				 index++;				
				 
				 //nro recibo
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(reciboTO.getNroRecibo());
		         
			    
			     //fecha recibo
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getFechaRecibo()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getFechaRecibo());
			     
			     //tipo de retencion
			     ind=ind+1;
			     c = r.createCell((short)ind);	
				 c.setCellStyle(estilofilas);	        
				 if(reciboTO.getTipoRetencion()==null)
			      	 c.setCellValue("");
			     else
			       	 c.setCellValue(reciboTO.getTipoRetencion());
				 	         		         
		         //nro certificado
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         c.setCellValue(reciboTO.getNroCertificado().doubleValue());
		         
		         //fecha certificado
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getFechaCertificado()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getFechaCertificado());		     
		         
		         
				 //importe certificado
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(reciboTO.getImporte()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getImporte().doubleValue());
		         
		         //juridiccion
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getDescrJuridiccion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getDescrJuridiccion());
			     
				 //cliente alfa
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getCodClienteAlfa()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getCodClienteAlfa());
			     
//			   cliente razon social
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getRazonSocial()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getRazonSocial());
			     
			     
//				   estado de remito
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getEstadoRecibo()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getEstadoRecibo());
		         
		         ind=0;

		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	  //  System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	
	/**
	 * Metodo: generarExcelReporteReciboConRetenciones
	 * Funcion: Generar excel del reporte de remitos con datos completo
	 * @param lstAManipular
	 * @param 
	 * @return nombre del archivo a levantar
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */										  
	public String generarExcelReporteRemitoCompleto(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
	
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			nombArch = mensajeria.getMessage().getString("archivo_remito_completo_label")+"_"+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto4 = wb.createCellStyle();
			
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("archivo_remito_completo_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  		
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			
			
			
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 13)));
		 	ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 13)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
			
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));	
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));	
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));	
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 19)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 19)));
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			
			
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));
			
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));
			
			
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 13)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 13)));
	        
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());
		     
		     ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("hora_label").toUpperCase());
		     
		     ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("ccss_label").toUpperCase());
	         
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("sucursal_label").toUpperCase());
		     ind=ind+1; 
		     c = r.createCell((short)ind);
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("nro_remito_label").toUpperCase());
			 
			  ind=ind+1;	 
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase());
		         ind=ind+1;	 
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
		         
		         
			 ind=ind+1;	     	         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());
	         ind=ind+1;	     	         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cantidad_label").toUpperCase());
	         
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("precio_litro_label").toUpperCase());
	         
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("total_pesos_label").toUpperCase());  
	         
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_asiento_contable_label").toUpperCase());  
	         
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_ejercicio_contable_label").toUpperCase());  
	         
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("itc_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("tasa_fondo_label").toUpperCase());
	         
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_iibb_label").toUpperCase());
	         
	         ind=ind+1;	 	         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("iva_label").toUpperCase());
	         
	         ind=ind+1;	         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("kilometaje_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_liquidacion_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cod_barra_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_autorizacion_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("sucur_factu_total_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_factura_largo_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("tipo_factura_label").toUpperCase()); 
	         
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("un_chofer_label").toUpperCase());
	         
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("grupo_unidad_negocio_abreviado_chofer_label").toUpperCase());
	         
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("un_camion_label").toUpperCase());
	         
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("grupo_unidad_negocio_abreviado_vehiculo_label").toUpperCase());
	         
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("patente_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("chofer_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("condicion_label").toUpperCase());
	         ind=ind+1;	 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("estado_combustible_label").toUpperCase());
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estiloMonto4.setDataFormat(df.getFormat("#,##0.0000"));	
	         estiloMonto4.setBorderBottom(estiloMonto4.BORDER_THIN);
	         estiloMonto4.setBorderRight(estiloMonto4.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			RemitoTO reciboTO = null;
			int index =0;
			ind=0;			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 reciboTO =new RemitoTO();
				 reciboTO = (RemitoTO)lstAManipular.get(index);
				 index++;				
				 
				
				 //fecha
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			   	 c.setCellValue(reciboTO.getFecha());
			   	 
			   	 //hora
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         c.setCellValue(reciboTO.getHora());
		         
			   	 //ccss
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         c.setCellValue(reciboTO.getCcss());
		         
		         
			   	 //pref remito
			   	 ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getNroSucursal()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getNroSucursal());
			   	 
			   	 
			   	 //nro remito
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getNroRemito()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getNroRemito());
		         
//			   codCliente alfa
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getCodClienteAlfa()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getCodClienteAlfa());		     
				 
			     
			     //  cliente razon social
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getCliDescripcion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getCliDescripcion());
			     
			    
			     //producto
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getDescProducto()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getDescProducto());
			     
			     //cantidad de litros
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(reciboTO.getLitros()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getLitros().doubleValue());
		         
		         //	precio litro
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto4);
		         if(reciboTO.getPrecioKilo()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getPrecioKilo().doubleValue());
			     
			    
				 
				 
				 //total $
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(reciboTO.getMontoTotal()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getMontoTotal().doubleValue());
		         
		        
		        
		         //nro asiento contable
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getNroAsientoContable()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getNroAsientoContable().doubleValue());
			     
		         
			     //nro ejercicio contable
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getNroEjercicioContable()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getNroEjercicioContable().doubleValue());
		         
		         //ITC 
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto4);
		         if(reciboTO.getItc()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getItc().doubleValue());
		         
		         //tasa/fondo
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto4);
		         if(reciboTO.getTasaFondo()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getTasaFondo().doubleValue());
		         
		         
		         ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto4);
		         if(reciboTO.getIibb()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getIibb().doubleValue());
		         
//		       iva
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(reciboTO.getIva()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getIva().doubleValue());
		         
//		      kilometraje
				 ind=ind+1;	
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(reciboTO.getKilometraje()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getKilometraje().doubleValue());
		         
//		       nro liquidacion
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getNroLiquidacion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getNroLiquidacion());
			     
//			  codigo de barra
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getCodBarra()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getCodBarra());
			     
//				  nro autorizacion
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getNroAutorizacion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getNroAutorizacion());
			     
			     
//				 prefijo factura
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getNroSucursalFactura()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getNroSucursalFactura());
			     
			     
//				  nro factura
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getNroFactura()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getNroFactura());
			     
//				  tipo comprobante
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getTipoComprobante()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getTipoComprobante());
		         
			     //unidad de negocio chofer
			     ind=ind+1;
			     c = r.createCell((short)ind);	
				 c.setCellStyle(estilofilas);	        
				 if(reciboTO.getDescrUnidadNegocioC()==null)
			      	 c.setCellValue("");
			     else
			       	 c.setCellValue(reciboTO.getDescrUnidadNegocioC());
				 
				 //grupo unidad de negocio chofer
			     ind=ind+1;
			     c = r.createCell((short)ind);	
				 c.setCellStyle(estilofilas);	        
				 if(reciboTO.getDescrGrupoUNC()==null)
			      	 c.setCellValue("");
			     else
			       	 c.setCellValue(reciboTO.getDescrGrupoUNC());
				 
//				unidad de negocio vehiculo
			     ind=ind+1;
			     c = r.createCell((short)ind);	
				 c.setCellStyle(estilofilas);	        
				 if(reciboTO.getDescrUnidadNegocioV()==null)
			      	 c.setCellValue("");
			     else
			       	 c.setCellValue(reciboTO.getDescrUnidadNegocioV());
				 
//					unidad de negocio vehiculo
			     ind=ind+1;
			     c = r.createCell((short)ind);	
				 c.setCellStyle(estilofilas);	        
				 if(reciboTO.getDescrGrupoUNV()==null)
			      	 c.setCellValue("");
			     else
			       	 c.setCellValue(reciboTO.getDescrGrupoUNV());
				 
		         //patente
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getPatente()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getPatente());		     
		         
		         //chofer
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getApellidoChofer()==null || reciboTO.getNombreChofer()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getApellidoChofer()+", "+ reciboTO.getNombreChofer());
			     
//			   condicion
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getCondicionDesc()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getCondicionDesc());
			     
//				   estado
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     c.setCellValue(reciboTO.getFacturadoString());
		         
		         ind=0;

		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	

	/**
	 * Metodo: generarExcelReporteRemitos
	 * Funcion: Generar excel del reporte de choferes activos e inactivos
	 * @param lstAManipular
	 * @param agente
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */										  
	public String generarExcelReporteCtasCtes(List lstAManipular,String fecha,Integer tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			nombArch = mensajeria.getMessage().getString("archivo_ctas_ctes_label")+"_"+fecha+".xls";	
					
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("archivo_ctas_ctes_label");	
				
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	       
	       	if(tipoUsu.equals(new Integer (0))){
	       		s.setColumnWidth((short)0, (short)((50 * 8) / ((double) 1 / 14)));
	       		s.setColumnWidth((short)1, (short)((50 * 8) / ((double) 1 / 18)));
	       		s.setColumnWidth((short)2, (short)((50 * 8) / ((double) 1 / 12)));
	       		s.setColumnWidth((short)3, (short)((50 * 8) / ((double) 1 / 13)));
	       		s.setColumnWidth((short)4, (short)((50 * 8) / ((double) 1 / 12)));
				s.setColumnWidth((short)5, (short)((50 * 8) / ((double) 1 / 15)));			
				s.setColumnWidth((short)6, (short)((50 * 8) / ((double) 1 / 10)));
				s.setColumnWidth((short)7, (short)((50 * 8) / ((double) 1 / 10)));
				s.setColumnWidth((short)8, (short)((50 * 8) / ((double) 1 / 10)));
				s.setColumnWidth((short)9, (short)((50 * 8) / ((double) 1 / 10)));
				s.setColumnWidth((short)10, (short)((50 * 8) / ((double) 1 / 28)));
	       	}else 	if(tipoUsu.equals(new Integer (1))){
				s.setColumnWidth((short)0, (short)((50 * 8) / ((double) 1 / 13)));
				s.setColumnWidth((short)1, (short)((50 * 8) / ((double) 1 / 15)));			
				s.setColumnWidth((short)2, (short)((50 * 8) / ((double) 1 / 10)));
				s.setColumnWidth((short)3, (short)((50 * 8) / ((double) 1 / 12)));
				s.setColumnWidth((short)4, (short)((50 * 8) / ((double) 1 / 10)));
				s.setColumnWidth((short)5, (short)((50 * 8) / ((double) 1 / 10)));
				s.setColumnWidth((short)6, (short)((50 * 8) / ((double) 1 / 10)));
				s.setColumnWidth((short)7, (short)((50 * 8) / ((double) 1 / 28)));
	       	}
			
		         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);	        
	         
	         int ind=0;
	         if(tipoUsu.equals(new Integer (0))){
	        	 c = r.createCell((short)ind);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase());
		         ind=ind+1;
	        	 c = r.createCell((short)ind);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("limite_credito_label").toUpperCase());
		         ind=ind+1;
	         }
	         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("comprobante_label").toUpperCase());
	        
	         ind=ind+1;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("sucursal_label").toUpperCase());
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_comprobante_label").toUpperCase());
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());   
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("vencimiento_label").toUpperCase());  
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("total_pesos_label").toUpperCase()); 
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("monto_adeudado_label").toUpperCase()); 
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("estado_label").toUpperCase());  
	         
	         
	  		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			CtasCtesTO ctasCtesTO = new CtasCtesTO();
			int index =0;
			ind=0;		
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 ctasCtesTO = (CtasCtesTO)lstAManipular.get(index);
				 index++;				
				 

				 if(tipoUsu.equals(new Integer (0))){
					 c = r.createCell((short)ind);
			         c.setCellStyle(estilofilas);
			         if(ctasCtesTO.getCodClienteAlfa()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(ctasCtesTO.getCodClienteAlfa());	  
			         
			         ind=ind+1;
			         
					 c = r.createCell((short)ind);
			         c.setCellStyle(estilofilas);
			         if(ctasCtesTO.getCliDescripcion()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(ctasCtesTO.getCliDescripcion());	  
			         
			         ind=ind+1;
			         
			         c = r.createCell((short)ind);
			         c.setCellStyle(estiloMonto);
			         if(ctasCtesTO.getTopeCredito()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(ctasCtesTO.getTopeCredito().doubleValue());	  
			         
			         ind=ind+1;
				 }
				 
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(ctasCtesTO.getComprobanteDescripcion()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(ctasCtesTO.getComprobanteDescripcion());	         
			     
		         ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     c.setCellValue(ctasCtesTO.getSucursal());
			     
		         ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     c.setCellValue(ctasCtesTO.getComprobanteNumero());
		         
			     ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(ctasCtesTO.getFecha()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(ctasCtesTO.getFecha());
		         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(ctasCtesTO.getVencimiento()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(ctasCtesTO.getVencimiento());
		         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(ctasCtesTO.getNetoFactura()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(ctasCtesTO.getNetoFactura().doubleValue());  
		         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(ctasCtesTO.getImporte()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(ctasCtesTO.getImporte().doubleValue()); 
		         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(ctasCtesTO.getEstadoStr()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(ctasCtesTO.getEstadoStr());
		         
		         ind=0;
		         
		         
		       }
		
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	/**
	 * Metodo: generarExcelReporteRemitos
	 * Funcion: Generar excel del reporte de choferes activos e inactivos
	 * codListado: 1 - Facturas, 2-Facturas de Recibos, 3-cobranzas
	 * @param lstAManipular
	 * @param agente
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */										  
	public String generarExcelReporteFacturas(List lstAManipular,String fechaStr,int tipoUsu,int codListado) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			if(codListado==3){
				nombArch = mensajeria.getMessage().getString("archivo_cobranzas_label")+"_"+fechaStr+".xls";
			}else{
				nombArch = mensajeria.getMessage().getString("archivo_facutas_label")+"_"+fechaStr+".xls";
			}
					
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			
			if(codListado==3){				
				etiqueta=mensajeria.getMessage().getString("archivo_cobranzas_label");	
			}else{
				etiqueta=mensajeria.getMessage().getString("archivo_facutas_label");	
			}
			
				
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	       
	       	int ind=0;	       	
	       	
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			
			ind=ind+1;
			if(tipoUsu==0){
				s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));
	       		ind=ind+1;
	       		s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));
	       		ind=ind+1;
	       	}
			 //viene por listado de facturas
	         if(codListado==1 && tipoUsu==0){
				s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
				ind=ind+1;
	         }
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			
			if(codListado==3){
				ind=ind+1;
	       		s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));	       		
	       	}
			
			  //viene por listado de facturas
	         if(codListado==1 && tipoUsu==0){
	        	 ind=ind+1;
		       	 s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	        	 ind=ind+1;
		       	 s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	        	 ind=ind+1;
		       	 s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	        	 ind=ind+1;
		       	 s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	        	 ind=ind+1;
		       	 s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	        	 ind=ind+1;
		       	 s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
		       	 ind=ind+1;
		       	 s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));	
		       	 
		       	 ind=ind+1;
		       	 s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));	
		       	 
	        	 ind=ind+1;
		       	 s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));  
		       	 ind=ind+1;
		       	 s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));
		       	 ind=ind+1;
		       	 s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
		       	 ind=ind+1;
		       	 s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 16)));    
	         }
			
		         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);	        
	         
	         ind=0;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());
	         ind=ind+1;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("tipo_label").toUpperCase());
	         
	         ind=ind+1;
	         if(tipoUsu==0){	
	        	 c = r.createCell((short)ind);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase());   
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());   
		         ind=ind+1;
	         } 
//	       viene por listado de facturas
	         if(codListado==1 && tipoUsu==0){
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("centro_servicio_label").toUpperCase());   
		         ind=ind+1;
	         }
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("prefijo_label").toUpperCase());   
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("numero_label").toUpperCase());  
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("monto_label").toUpperCase());   
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("monto_tot_label").toUpperCase());   
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("monto_adeudado_label").toUpperCase());   
	         
	         //listado de cobranzas
	         if(codListado==3){
					ind=ind+1;
					 c = r.createCell((short)ind);	      
			         c.setCellStyle(estiloTitulo);
			         c.setCellValue(mensajeria.getMessage().getString("fecha_vto_label").toUpperCase());        		
		     }
	         
	         
	         //viene por listado de facturas
	         if(codListado==1 && tipoUsu==0){
		        	 ind=ind+1;
					 c = r.createCell((short)ind);	      
			         c.setCellStyle(estiloTitulo);
			         c.setCellValue(mensajeria.getMessage().getString("neto_label").toUpperCase());  
					 ind=ind+1;
					 c = r.createCell((short)ind);	      
			         c.setCellStyle(estiloTitulo);
			         c.setCellValue(mensajeria.getMessage().getString("iva_label").toUpperCase());  
					 ind=ind+1;
					 c = r.createCell((short)ind);	      
			         c.setCellStyle(estiloTitulo);
			         c.setCellValue(mensajeria.getMessage().getString("per_iva_label").toUpperCase());  
					 ind=ind+1;
					 c = r.createCell((short)ind);	      
			         c.setCellStyle(estiloTitulo);
			         c.setCellValue(mensajeria.getMessage().getString("per_iibb_label").toUpperCase());  
					 ind=ind+1;
					 c = r.createCell((short)ind);	      
			         c.setCellStyle(estiloTitulo);
			         c.setCellValue(mensajeria.getMessage().getString("tasa_fondo_label").toUpperCase());  
					 ind=ind+1;
					 c = r.createCell((short)ind);	      
			         c.setCellStyle(estiloTitulo);
			         c.setCellValue(mensajeria.getMessage().getString("itc_label").toUpperCase());  
					 ind=ind+1;
					 c = r.createCell((short)ind);	      
			         c.setCellStyle(estiloTitulo);
			         c.setCellValue(mensajeria.getMessage().getString("ley_10081_label").toUpperCase());  
					 ind=ind+1;
					 
					 c = r.createCell((short)ind);	      
			         c.setCellStyle(estiloTitulo);
			         c.setCellValue("Imp CO2".toUpperCase());  
					 ind=ind+1;
					 
					 
					 c = r.createCell((short)ind);	      
			         c.setCellStyle(estiloTitulo);
			         c.setCellValue(mensajeria.getMessage().getString("nro_asiento_contable_label").toUpperCase());
			         ind=ind+1;
			         c = r.createCell((short)ind);	      
			         c.setCellStyle(estiloTitulo);
			         c.setCellValue(mensajeria.getMessage().getString("nro_ejercicio_contable_label").toUpperCase());
			         ind=ind+1;
			         c = r.createCell((short)ind);	      
			         c.setCellStyle(estiloTitulo);
			         c.setCellValue(mensajeria.getMessage().getString("nro_rendicion_label").toUpperCase());
			       
			         ind=ind+1;
					 c = r.createCell((short)ind);	      
			         c.setCellStyle(estiloTitulo);
			         c.setCellValue(mensajeria.getMessage().getString("fecha_vto_label").toUpperCase());
			         
	         }
	         
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			MFacturaVTO mFacturaVTO = new MFacturaVTO();
			int index =0;
			ind=0;		
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 mFacturaVTO = (MFacturaVTO)lstAManipular.get(index);
				 index++;				
				 

		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mFacturaVTO.getFecha()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getFecha());	         
			     
		         ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mFacturaVTO.getTipo()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getTipo());
			     
			     ind=ind+1;
			     if(tipoUsu==0){
			    	 c = r.createCell((short)ind);	
				     c.setCellStyle(estilofilas);	        
				     if(mFacturaVTO.getCodClienteAlfa()==null)
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getCodClienteAlfa());
				     
				     ind=ind+1;
				     
				     c = r.createCell((short)ind);	
				     c.setCellStyle(estilofilas);	        
				     if(mFacturaVTO.getCliDescripcion()==null)
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getCliDescripcion());
				     
				     ind=ind+1;
			     }		         
		         
			     if(codListado==1 && tipoUsu==0){
				     c = r.createCell((short)ind);
			         c.setCellStyle(estilofilas);
			         if(mFacturaVTO.getCcss()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getCcss());
			         ind=ind+1;
			     }
		         
		        
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mFacturaVTO.getNroSucursal()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getNroSucursal());
		         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mFacturaVTO.getNroFactura()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getNroFactura());
		         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getNetoFactura()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getNetoFactura().doubleValue());   
		         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getPagoAplicado()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getPagoAplicado().doubleValue());     
		         
		         
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(mFacturaVTO.getSaldo()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mFacturaVTO.getSaldo().doubleValue());  
		         
		         
		         if(codListado==3){
		        	 ind=ind+1;
		        	 c = r.createCell((short)ind);
			         c.setCellStyle(estilofilas);
			         if(mFacturaVTO.getFeVto()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getFeVto());  
		        	 
		         }
		         
		         
		         if(codListado==1 && tipoUsu==0){
		        	 //precio neto
		        	 ind=ind+1;
			         c = r.createCell((short)ind);
			         c.setCellStyle(estiloMonto);
			         if(mFacturaVTO.getPrecioNeto()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getPrecioNeto().doubleValue());
			         
		        	 //iva basico
		        	 ind=ind+1;
			         c = r.createCell((short)ind);
			         c.setCellStyle(estiloMonto);
			         if(mFacturaVTO.getIva()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getIva().doubleValue());
			         
		        	 //per iva 
		        	 ind=ind+1;
			         c = r.createCell((short)ind);
			         c.setCellStyle(estiloMonto);
			         if(mFacturaVTO.getPerIVA()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getPerIVA().doubleValue());
			         
		        	 //per iibb 
		        	 ind=ind+1;
			         c = r.createCell((short)ind);
			         c.setCellStyle(estiloMonto);
			         if(mFacturaVTO.getPerIIBB()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getPerIIBB().doubleValue());	

		        	 //tasa fondo
		        	 ind=ind+1;
			         c = r.createCell((short)ind);
			         c.setCellStyle(estiloMonto);
			         if(mFacturaVTO.getTasaFondo()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getTasaFondo().doubleValue());			       

		        	 //itc
		        	 ind=ind+1;
			         c = r.createCell((short)ind);
			         c.setCellStyle(estiloMonto);
			         if(mFacturaVTO.getItc()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getItc().doubleValue());
			         
			         //ley 10081
		        	 ind=ind+1;
			         c = r.createCell((short)ind);
			         c.setCellStyle(estiloMonto);
			         if(mFacturaVTO.getLeyCba()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getLeyCba().doubleValue());
			         
			         //co 2
		        	 ind=ind+1;
			         c = r.createCell((short)ind);
			         c.setCellStyle(estiloMonto);
			         if(mFacturaVTO.getCO2()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getCO2().doubleValue());
			         
			         
		        	 //nro asiento contable
		        	 ind=ind+1;
			         c = r.createCell((short)ind);
			         c.setCellStyle(estilofilas);
			         if(mFacturaVTO.getNroAsientoContable()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getNroAsientoContable().doubleValue());	
			         
//			       nro ejercicio contable
		        	 ind=ind+1;
			         c = r.createCell((short)ind);
			         c.setCellStyle(estilofilas);
			         if(mFacturaVTO.getNroEjercicioContable()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getNroEjercicioContable().doubleValue());	
			         
//				       nro rendicion
		        	 ind=ind+1;
			         c = r.createCell((short)ind);
			         c.setCellStyle(estilofilas);
			         if(mFacturaVTO.getNroRendicionRefipass()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getNroRendicionRefipass());
			         
			         //fecha vto
			         ind=ind+1;
			         c = r.createCell((short)ind);
			         c.setCellStyle(estilofilas);
			         if(mFacturaVTO.getFeVto()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mFacturaVTO.getFeVto());	
			         
		         }
		         ind=0;
		         
		         
		       }
		
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	/**
	 * Metodo: generarExcelReporteRemitos
	 * Funcion: Generar excel del reporte de choferes activos e inactivos
	 * @param lstAManipular
	 * @param agente
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */										  
	public String generarExcelReporteConsumosPorGrupo(List lstAManipular,String fecha,Boolean vienePorVehiculos,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			if(vienePorVehiculos){
				nombArch = mensajeria.getMessage().getString("archivo_cargas_por_UN_vehiculo_label")+"_"+fecha+".xls";
			}else{
				nombArch = mensajeria.getMessage().getString("archivo_cargas_por_UN_choferes_label")+"_"+fecha+".xls";	
			}			
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			if(vienePorVehiculos){
				etiqueta=mensajeria.getMessage().getString("archivo_cargas_por_UN_vehiculo_label");	
			}else{				
				etiqueta=mensajeria.getMessage().getString("archivo_cargas_por_UN_choferes_label");	
			}
			
				
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	       
	       	int i=0;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 20)));
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 20)));
			
			
			if(tipoUsu==0){
				i=i+1;
				s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 22)));
			}
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 8)));			
			
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 8)));
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 20)));
			
		         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);	        
	         i=0;
	         c = r.createCell((short)i);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("grupo_unidad_negocio_label").toUpperCase());
	         i=i+1;
	         c = r.createCell((short)i);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("unidad_negocio_label").toUpperCase());
	         
	         if(tipoUsu==0){
	        	 i=i+1;
	        	 c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());   
	         }
	         i=i+1;
	         c = r.createCell((short)i);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cantidad_label").toUpperCase());   
	         i=i+1;
	         c = r.createCell((short)i);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("total_pesos_label").toUpperCase());  
	         i=i+1;
	         c = r.createCell((short)i);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());   
	         
	  		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			ConsumoTO consumoTO = new ConsumoTO();
			int index =0;
						
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 consumoTO = (ConsumoTO)lstAManipular.get(index);
				 index++;				
				 
				 i=0;
		         c = r.createCell((short)i);
		         c.setCellStyle(estilofilas);
		         if(consumoTO.getDescrGrupoUN()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(consumoTO.getDescrGrupoUN());	         
			     
		         i=i+1;
			     c = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     if(consumoTO.getDescrUnidadNegocio()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(consumoTO.getDescrUnidadNegocio());
			     
			     if(tipoUsu==0){
			    	 i=i+1;
			    	 c = r.createCell((short)i);	
				     c.setCellStyle(estilofilas);	        
				     if(consumoTO.getCliDescripcion()==null)
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(consumoTO.getCliDescripcion());
			     }
		         
			     i=i+1; 
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloMonto);
		         if(consumoTO.getConsumoLitros()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(consumoTO.getConsumoLitros().doubleValue());
		         
		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloMonto);
		         if(consumoTO.getMontoTotal()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(consumoTO.getMontoTotal().doubleValue());

		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloMonto);
		         if(consumoTO.getDescProducto()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(consumoTO.getDescProducto());
		         
		         
		         
		       }
		
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	

	/**
	 * Metodo: generarExcelReporteRemitos
	 * Funcion: Generar excel del reporte de choferes activos e inactivos
	 * @param lstAManipular
	 * @param agente
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */										  
	public String generarExcelReporteConsumos(List lstAManipular,String fecha,Boolean vienePorVehiculo,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			if(!vienePorVehiculo){
				nombArch = mensajeria.getMessage().getString("archivo_cargas_choferes_label")+"_"+fecha+".xls";	
			}else {		
				nombArch = mensajeria.getMessage().getString("archivo_cargas_vehiculo_label")+"_"+fecha+".xls";
			}	
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			if(!vienePorVehiculo){				
				etiqueta=mensajeria.getMessage().getString("archivo_cargas_choferes_label");
			}else {		
				etiqueta=mensajeria.getMessage().getString("archivo_cargas_vehiculo_label");
			}
						
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int i=0;
			if(!vienePorVehiculo){				
				s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 20)));
			}else {		
				s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 8)));
			}
	
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 20)));
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 20)));
			
			
			if(tipoUsu==0){
				i=i+1;
				s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 20)));
			}
			
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 8)));
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 8)));
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 20)));
			
		         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         i=0;
	         c = r.createCell((short)i);
		     c.setCellStyle(estiloTitulo);
		     
			if(!vienePorVehiculo){				
			     c.setCellValue(mensajeria.getMessage().getString("chofer_label").toUpperCase());
			}else {		
			     c.setCellValue(mensajeria.getMessage().getString("patente_label").toUpperCase());
			}
			
			i=i+1;
	         c = r.createCell((short)i);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("unidad_negocio_label").toUpperCase());
	         i=i+1;
	         c = r.createCell((short)i);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("grupo_unidad_negocio_label").toUpperCase());
	         
	         if(tipoUsu==0){
	        	 i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());   
	         }
	         i=i+1;
	         c = r.createCell((short)i);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cantidad_label").toUpperCase());   
	         i=i+1;
	         c = r.createCell((short)i);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("total_pesos_label").toUpperCase());  
	         i=i+1;
	         c = r.createCell((short)i);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());   
	         
	  		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			ConsumoTO consumoTO = new ConsumoTO();
			int index =0;
						
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 consumoTO = (ConsumoTO)lstAManipular.get(index);
				 index++;				
				 i=0;
				 //fecha
			     c = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     
			 	if(!vienePorVehiculo){				
				       c.setCellValue(consumoTO.getApellidoChofer()+", "+consumoTO.getNombreChofer());

				}else {		
				     if(consumoTO.getDominio()==null)
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(consumoTO.getDominio());

				}
		         
			 	 i=i+1;
			     //sucursal
			     c = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     if(consumoTO.getDescrUnidadNegocio()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(consumoTO.getDescrUnidadNegocio());
		         
			     i=i+1;
		         //unidad de negocio
		         c = r.createCell((short)i);
		         c.setCellStyle(estilofilas);
		         if(consumoTO.getDescrGrupoUN()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(consumoTO.getDescrGrupoUN());
		         
		         if(tipoUsu==0){
		        	 i=i+1;
			         c = r.createCell((short)i);
			         c.setCellStyle(estilofilas);
			         if(consumoTO.getCliDescripcion()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(consumoTO.getCliDescripcion());
		         }
		         
		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloMonto);
		         if(consumoTO.getConsumoLitros()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(consumoTO.getConsumoLitros().doubleValue());
		         
		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloMonto);
		         if(consumoTO.getMontoTotal()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(consumoTO.getMontoTotal().doubleValue());

		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloMonto);
		         if(consumoTO.getDescProducto()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(consumoTO.getDescProducto());
		         
		         
		         
		       }
		
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	  //  System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	
	
	
	
	/**
	 * Metodo: generarExcelReporteRemitos
	 * Funcion: Generar excel del reporte de choferes activos e inactivos
	 * @param lstAManipular
	 * @param agente
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public String generarExcelReporteCuposConsumo(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{	
			
			nombArch = mensajeria.getMessage().getString("archivo_consumo")+"_"+fecha+".xls";				
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("archivo_consumo");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	 
			int i=0;
	       	s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 12)));
	       	i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 12)));
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 12)));
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 12)));
	       	i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 10)));
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 13)));
			
			if(tipoUsu==0){
				i=i+1;
				s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 22)));
				
				i=i+1;
				s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 12)));
				
				i=i+1;
				s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 12)));
			}
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 10)));
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 10)));
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 13)));
			
		         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         i=0;
	         
	         c = r.createCell((short)i);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("mes_label").toUpperCase());
		     i=i+1;
		     
		     c = r.createCell((short)i);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("anio_label").toUpperCase());
		     i=i+1;
		     
		     c = r.createCell((short)i);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue("TIPO".toUpperCase());
		     i=i+1;
		     
	         c = r.createCell((short)i);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("descripcion_label").toUpperCase());
		     i=i+1;
				   
		     c = r.createCell((short)i);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("patente_label").toUpperCase());
		     i=i+1;
	         c = r.createCell((short)i);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("unidad_negocio_label").toUpperCase());
	         
	         if(tipoUsu==0){
	        	 i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase()); 
		         
		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("codigo_barra_label").toUpperCase()); 
		         
		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("activo_label").toUpperCase()); 
	         }
	         i=i+1;
	         c = r.createCell((short)i);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cupo_lts_label").toUpperCase());   
	         i=i+1;
	         c = r.createCell((short)i);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("consumo_lst_label").toUpperCase());  
	         i=i+1;
	         c = r.createCell((short)i);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("disponible_lts_label").toUpperCase());   
	         
	  		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			CuposTO cuposTO = new CuposTO();
			int index =0;
					i=0;	
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 cuposTO = (CuposTO)lstAManipular.get(index);
				 index++;				
				 
				 //mes					
			     c = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     if(cuposTO.getMes()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getMes());
		         
			     i=i+1;
			     //anio					
			     c = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     if(cuposTO.getAnio()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getAnio());
			     
			     i=i+1;
				 //descripcion
				
			     c = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     if(cuposTO.getFamiliaGrupoArticuloDesc()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getFamiliaGrupoArticuloDesc());
			     
		         
			     i=i+1;
				 //descripcion
				
			     c = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     if(cuposTO.getDescripcion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getDescripcion());
		         
			     i=i+1;
			     //sucursal
			     c = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     if(cuposTO.getPatente()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getPatente());
		         
			     i=i+1;
		         //unidad de negocio
		         c = r.createCell((short)i);
		         c.setCellStyle(estilofilas);
		         if(cuposTO.getDescrUnidadNegocio()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getDescrUnidadNegocio());
		         
		         if(tipoUsu==0){
		        	 i=i+1;
//		        	cliente descripcion
			         c = r.createCell((short)i);
			         c.setCellStyle(estilofilas);
			         if(cuposTO.getCliDescripcion()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(cuposTO.getCliDescripcion());
			         
			         
			         i=i+1;
//			         codigo de barra
				     c = r.createCell((short)i);
				     c.setCellStyle(estilofilas);
				     if(cuposTO.getCodBarra()==null )
				       	 c.setCellValue("");
				     else
				       	 c.setCellValue(cuposTO.getCodBarra());  
			         
			         i=i+1;
//			         activo
				     c = r.createCell((short)i);
				     c.setCellStyle(estilofilas);
				     if(cuposTO.getActivo()==null )
				       	 c.setCellValue("");
				     else
				       	 c.setCellValue(cuposTO.getActivo()? mensajeria.getMessage().getString("si_label") : mensajeria.getMessage().getString("no_label"));  
				         
		         }
		         
		         
		         
		         
		         
		         i=i+1;
		         //grupo de la unidad de negocio
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloMonto);
		         if(cuposTO.getIlimitado())
		        	 c.setCellValue("Ilimitado");
		         else
		        	 c.setCellValue(cuposTO.getCupoLitros().doubleValue());
		         
		         i=i+1;
		         //activo         
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloMonto);
		         if(cuposTO.getConsumoLitros()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getConsumoLitros().doubleValue());
		         
		         i=i+1;
//		       activo         
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloMonto);
		         if(cuposTO.getDisponibleLitros()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getDisponibleLitros().doubleValue());
		         
		         
		         i=0;
		       }
		
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	public String generarExcelReporteCupos(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("archivo_cupos")+"_"+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("archivo_cupos");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	 
			int i=0;
	       	s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 12)));
	       	i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 10)));
	       	i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 10)));
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 13)));
			
			if(tipoUsu==0){
				i=i+1;
				s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 22)));
				
				i=i+1;
				s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 12)));
				
				i=i+1;
				s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 12)));
			}
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 10)));
			
			
			
		         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         i=0;
	        	    
	         c = r.createCell((short)i);
	         c.setCellStyle(estiloTitulo);
		     c.setCellValue("TIPO".toUpperCase());
		     i=i+1;
				   
		     
	         c = r.createCell((short)i);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("descripcion_label").toUpperCase());
		     i=i+1;
				   
		     c = r.createCell((short)i);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("patente_label").toUpperCase());
		     i=i+1;
	         c = r.createCell((short)i);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("unidad_negocio_label").toUpperCase());
	         
	         if(tipoUsu==0){
	        	 i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase()); 
		         
		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("codigo_barra_label").toUpperCase()); 
		         
		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("activo_label").toUpperCase()); 
	         }
	         i=i+1;
	         c = r.createCell((short)i);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cupo_lts_label").toUpperCase());   
	           
	          
	  		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			CuposTO cuposTO = new CuposTO();
			int index =0;
					i=0;	
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 cuposTO = (CuposTO)lstAManipular.get(index);
				 index++;				
				 			
				 c = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     if(cuposTO.getFamiliaGrupoArticuloDesc()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getFamiliaGrupoArticuloDesc());
		         
			     i=i+1;
			     
			     c = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     if(cuposTO.getDescripcion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getDescripcion());
		         
			     i=i+1;
			     //sucursal
			     c = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     if(cuposTO.getPatente()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getPatente());
		         
			     i=i+1;
		         //unidad de negocio
		         c = r.createCell((short)i);
		         c.setCellStyle(estilofilas);
		         if(cuposTO.getDescrUnidadNegocio()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getDescrUnidadNegocio());
		         
		         if(tipoUsu==0){
		        	 i=i+1;
//		        	cliente descripcion
			         c = r.createCell((short)i);
			         c.setCellStyle(estilofilas);
			         if(cuposTO.getCliDescripcion()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(cuposTO.getCliDescripcion());
			         
			         
			         i=i+1;
//			         codigo de barra
				     c = r.createCell((short)i);
				     c.setCellStyle(estilofilas);
				     if(cuposTO.getCodBarra()==null )
				       	 c.setCellValue("");
				     else
				       	 c.setCellValue(cuposTO.getCodBarra());  
			         
			         i=i+1;
//			         activo
				     c = r.createCell((short)i);
				     c.setCellStyle(estilofilas);
				     if(cuposTO.getActivo()==null )
				       	 c.setCellValue("");
				     else
				       	 c.setCellValue(cuposTO.getActivo()? mensajeria.getMessage().getString("si_label") : mensajeria.getMessage().getString("no_label"));  
				         
		         }
		         
		         
		         
		         
		         
		         i=i+1;
		         //grupo de la unidad de negocio
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloMonto);
		         
		         if(cuposTO.getIlimitado() )
		        	 c.setCellValue("Ilimitado");
		         else
		        	 c.setCellValue(cuposTO.getCupoLitros().doubleValue());
		         
		        
		         
		         
		         i=0;
		       }
		
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	
	public String generarExcelReporteReciboTotal(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
	
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("archivo_recibo_total_label")+"_"+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("archivo_recibo_total_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  		
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
		 	ind=ind+1;		
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	        
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase());
		     
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
		     
		     ind=ind+1; 
		     c = r.createCell((short)ind);
			 c.setCellStyle(estiloTitulo);
			 c.setCellValue(mensajeria.getMessage().getString("fecha_rec_label").toUpperCase());
			 
			 ind=ind+1;         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("tipo_comprobante_largo_label").toUpperCase());
	         
	         ind=ind+1;         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("ccss_label").toUpperCase());
	         
	         ind=ind+1;         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_recibo_label").toUpperCase());
	         
	         ind=ind+1;         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("importe_total_label").toUpperCase());
	         
	         ind=ind+1;         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("monto_tot_label").toUpperCase());
	         
	         ind=ind+1;         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("tipo_comprobante_largo_label").toUpperCase());
	         ind=ind+1;         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("sucur_factu_total_label").toUpperCase());
	         
	         ind=ind+1;         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_factura_largo_label").toUpperCase());
	         
	         ind=ind+1;         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("fecha_ven_label").toUpperCase());
	         
	         ind=ind+1;         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("estado_recibo_label").toUpperCase());
	         
	                 
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			MReciboTO reciboTO = new MReciboTO();
			int index =0;
			ind=0;			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 reciboTO = (MReciboTO)lstAManipular.get(index);
				 index++;				
				 
				 //fecha
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getCodClienteAlfa()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getCodClienteAlfa());
		         
			     ind=ind+1;
			     //cliente
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getCliDescripcion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getCliDescripcion());
			     
			     ind=ind+1;			    
				     //fecha
				     c = r.createCell((short)ind);	
				     c.setCellStyle(estilofilas);	        
				     if(reciboTO.getFecha()==null)
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(reciboTO.getFecha());
				     
				     
				  ind=ind+1;			    
				  //tipo comprobante recibo
				  c = r.createCell((short)ind);	
				  c.setCellStyle(estilofilas);	        
				  if(reciboTO.getTipoComprobanteRecibo()==null)
			      	 c.setCellValue("");
			      else
			      	 c.setCellValue(reciboTO.getTipoComprobanteRecibo());
				  
				  
				  ind=ind+1;			    
				  //pref recibo
				  c = r.createCell((short)ind);	
				  c.setCellStyle(estilofilas);	        
				  if(reciboTO.getNombreSucursal()==null)
			      	 c.setCellValue("");
			      else
			      	 c.setCellValue(reciboTO.getNombreSucursal());
				  
				  ind=ind+1;			    
				  //numero recibo
				  c = r.createCell((short)ind);	
				  c.setCellStyle(estilofilas);	        
				  if(reciboTO.getNumero()==null)
			      	 c.setCellValue("");
			      else
			      	 c.setCellValue(reciboTO.getNumero());
				  
				  ind=ind+1;	         		         
			         //importe recibo
			         c = r.createCell((short)ind);
			         c.setCellStyle(estiloMonto);
			         if(reciboTO.getImporteRecibo()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(reciboTO.getImporteRecibo().doubleValue());
			         
				  ind=ind+1;	         		         
			         //monto
			         c = r.createCell((short)ind);
			         c.setCellStyle(estiloMonto);
			         if(reciboTO.getMontoTotal()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(reciboTO.getMontoTotal().doubleValue());
			         
			         
				  
				  ind=ind+1;			    
				  //tipo de comprobante factura
				  c = r.createCell((short)ind);	
				  c.setCellStyle(estilofilas);	        
				  if(reciboTO.getTipoComprobanteFactura()==null)
			      	 c.setCellValue("");
			      else
			      	 c.setCellValue(reciboTO.getTipoComprobanteFactura());
				  
				  
				  ind=ind+1;			    
				  //pref factura
				  c = r.createCell((short)ind);	
				  c.setCellStyle(estilofilas);	        
				  if(reciboTO.getSucursalFactura()==null)
			      	 c.setCellValue("");
			      else
			      	 c.setCellValue(reciboTO.getSucursalFactura());
				  
				  
				  ind=ind+1;			    
				  //nro factura
				  c = r.createCell((short)ind);	
				  c.setCellStyle(estilofilas);	        
				  if(reciboTO.getNroFactura()==null)
			      	 c.setCellValue("");
			      else
			      	 c.setCellValue(reciboTO.getNroFactura());
				  
				  ind=ind+1;			    
				  //fecha vencimiento
				  c = r.createCell((short)ind);	
				  c.setCellStyle(estilofilas);	        
				  if(reciboTO.getFechaVto()==null)
			      	 c.setCellValue("");
			      else
			      	 c.setCellValue(reciboTO.getFechaVto());
				  
				  
				  ind=ind+1;			    
				  //estado recibo
				  c = r.createCell((short)ind);	
				  c.setCellStyle(estilofilas);	        
				  if(reciboTO.getEstadoRecibo()==null)
			      	 c.setCellValue("");
			      else
			      	 c.setCellValue(reciboTO.getEstadoRecibo());
		         
		         ind=0;

		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	/**
	 * Metodo: generarExcelReporteRecibo
	 * Funcion: Generar excel del reporte de recibos por fecha
	 * @param lstAManipular
	 * @param agente
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */										  
	public String generarExcelReporteRecibo(List lstAManipular,String fecha,int tipoUsu,Boolean anulado) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
	
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("archivo_recibo_label")+"_"+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
		
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();					
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("archivo_recibo_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  		
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
		
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	  
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
		 	ind=ind+1;
			if(tipoUsu==0){
				s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			 	ind=ind+1;
			 	
			}
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));	
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));	
			
			 if (anulado){
				 ind=ind+1;
				 s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			 }
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());
		     ind=ind+1;         
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("numero_label").toUpperCase());
		     ind=ind+1; 
		     if(tipoUsu==0){
		    	 c = r.createCell((short)ind);
			     c.setCellStyle(estiloTitulo);
			     c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
			     ind=ind+1; 
		     }
	         
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("monto_label").toUpperCase());
	         
	         ind=ind+1; 
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("estado_label").toUpperCase());
	         
	         if (anulado){
		         ind=ind+1; 
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("fecha_anulado_label").toUpperCase());
	         }
	         
	        		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			MReciboTO reciboTO = new MReciboTO();
			int index =0;
			ind=0;			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 reciboTO = (MReciboTO)lstAManipular.get(index);
				 index++;				
				 
				 //fecha
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getFecha()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getFecha());
		         
			     ind=ind+1;
			     //numero
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(reciboTO.getNumero()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getNumero());
			     
			     ind=ind+1;
			     if(tipoUsu==0){
				     //cliente
				     c = r.createCell((short)ind);	
				     c.setCellStyle(estilofilas);	        
				     if(reciboTO.getCliDescripcion()==null)
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(reciboTO.getCliDescripcion());
				     
				     ind=ind+1;
			     }
		         		         
		         //monto
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(reciboTO.getMontoTotal()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getMontoTotal().doubleValue());
		         
		         
		         //estado del recibo
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(reciboTO.getEstadoRecibo()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(reciboTO.getEstadoRecibo());
		         
		         
		         if (anulado){
		        	 //fecha anulado del recibo
			         ind=ind+1;
			         c = r.createCell((short)ind);
			         c.setCellStyle(estilofilas);
			         if(reciboTO.getFechaBaja()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(reciboTO.getFechaBaja());
		         }
		         
		         ind=0;

		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	
	/**
	 * Metodo: generarExcelReporteRemitos
	 * Funcion: Generar excel del reporte de choferes activos e inactivos
	 * @param lstAManipular
	 * @param agente
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */										  
	public String generarExcelReporteRemitosConsumo(List lstAManipular,String fecha) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("remitos_label")+"_"+fecha +".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto4 = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("remitos_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	 
			int ind=0;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	    	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	    	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 25)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));			
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));			
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
	       	ind=ind+1;  s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());
		     
		     ind=ind+1;  
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("hora_label").toUpperCase());
		     
		     ind=ind+1;           
		     c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("ccss_label").toUpperCase()); 
	         
	         ind=ind+1;           
		     c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase()); 
	         
	         ind=ind+1;           
		     c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase()); 
	         
	         ind=ind+1;  
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("sucursal_label").toUpperCase());
	         
		     ind=ind+1;  
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_remito_label").toUpperCase());
	         
	         ind=ind+1;  
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());   
	         
	         ind=ind+1;  
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cantidad_label").toUpperCase()); 
	         
	         ind=ind+1;  
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("precio_litro_label").toUpperCase());   
	         
	         ind=ind+1;  
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("total_pesos_label").toUpperCase());  
	         
	         ind=ind+1;  
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("patente_label").toUpperCase());
	         
	         ind=ind+1;  
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("chofer_label").toUpperCase());    
	         
	         ind=ind+1;  
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("un_chofer_label").toUpperCase()); 
	         
	         ind=ind+1;  
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("grupo_unidad_negocio_abreviado_chofer_label").toUpperCase()); 
	         ind=ind+1;  
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("un_camion_label").toUpperCase());
	         ind=ind+1;  
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("grupo_unidad_negocio_abreviado_vehiculo_label").toUpperCase());  
	         
	       
	         
	        
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estiloMonto4.setDataFormat(df.getFormat("#,##0.0000"));	
	         estiloMonto4.setBorderBottom(estiloMonto4.BORDER_THIN);
	         estiloMonto4.setBorderRight(estiloMonto4.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			RemitoTO remitoTO = new RemitoTO();
			int index =0;
			ind=0;
			
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 remitoTO = (RemitoTO)lstAManipular.get(index);
				 index++;				
				 ind=0;
				 
				 //fecha
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getFecha()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getFecha());
			     
//			   hora
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getHora()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getHora());
		         
//			       ccss 
			     ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getCcss()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getCcss()); 
		         
//			       cod cliente alfa 
			     ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getCodClienteAlfa()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getCodClienteAlfa()); 
		         
		         
//			       cliente 
			     ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getCliDescripcion()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getCliDescripcion()); 
			     
			     //sucursal
		         ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getNroSucursal()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getNroSucursal());
		         
		         
		         //nro remito
			     ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getNroRemito()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getNroRemito());
		         
		         
		         //producto
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getDescProducto()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getDescProducto());
		         
		         
		         //cantidad
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(remitoTO.getLitros()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getLitros().doubleValue());   
		         
		         
//		       precio c imp
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto4);
		         if(remitoTO.getPrecioConImpuestos()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getPrecioConImpuestos().doubleValue());  
		         
		         //monto
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloMonto);
		         if(remitoTO.getMontoTotal()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getMontoTotal().doubleValue());


		         
		         
		         
//		       patente   
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getPatente()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getPatente());
		         
		         //chofer
		         ind=ind+1; 
		         c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getApellidoChofer()==null || remitoTO.getNombreChofer()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getApellidoChofer()+", "+ remitoTO.getNombreChofer());
		         
		         
		         
		         //unidad de neg chofer
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getDescrUnidadNegocioC()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getDescrUnidadNegocioC());
		         
//		       grupo chofer
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getDescrGrupoUNC()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getDescrGrupoUNC());
		         
		         //grupo vehiculo
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getDescrUnidadNegocioV()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getDescrUnidadNegocioV());
		         
		         //unidad de neg vehiculo
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getDescrGrupoUNV()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getDescrGrupoUNV());
		         
		         
		        
			     
		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	
	public String generarExcelReporteRemitosCTACTE(List lstAManipular,String fecha) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("remitos_ctacte_label")+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto4 = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("remitos_ctacte_label");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	       
	       	s.setColumnWidth((short)0, (short)((50 * 8) / ((double) 1 / 12)));
	    	s.setColumnWidth((short)1, (short)((50 * 8) / ((double) 1 / 10)));
	    	
	    	s.setColumnWidth((short)2, (short)((50 * 8) / ((double) 1 / 15)));
	    	s.setColumnWidth((short)3, (short)((50 * 8) / ((double) 1 / 10)));
	    	s.setColumnWidth((short)4, (short)((50 * 8) / ((double) 1 / 20)));
	    	
			s.setColumnWidth((short)5, (short)((50 * 8) / ((double) 1 / 8)));
			s.setColumnWidth((short)6, (short)((50 * 8) / ((double) 1 / 8)));
			s.setColumnWidth((short)7, (short)((50 * 8) / ((double) 1 / 18)));
			s.setColumnWidth((short)8, (short)((50 * 8) / ((double) 1 / 15)));
			s.setColumnWidth((short)9, (short)((50 * 8) / ((double) 1 / 15)));
			s.setColumnWidth((short)10, (short)((50 * 8) / ((double) 1 / 15)));
			
			s.setColumnWidth((short)10, (short)((50 * 8) / ((double) 1 / 15)));
			s.setColumnWidth((short)10, (short)((50 * 8) / ((double) 1 / 15)));			
			
			s.setColumnWidth((short)11, (short)((50 * 8) / ((double) 1 / 19)));
			s.setColumnWidth((short)12, (short)((50 * 8) / ((double) 1 / 15)));
			s.setColumnWidth((short)13, (short)((50 * 8) / ((double) 1 / 19)));
			s.setColumnWidth((short)14, (short)((50 * 8) / ((double) 1 / 15)));			
			s.setColumnWidth((short)15, (short)((50 * 8) / ((double) 1 / 13)));
			s.setColumnWidth((short)16, (short)((50 * 8) / ((double) 1 / 25)));			

	        
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         
	         c = r.createCell((short)0);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());
		     
		     c = r.createCell((short)1);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("hora_label").toUpperCase());
		     
		     
		     c = r.createCell((short)2);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("ccss_label").toUpperCase());
		     
		     c = r.createCell((short)3);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase());
		     
		     
		     c = r.createCell((short)4);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
		     
		     
		     
	        	         
		     c = r.createCell((short)5);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("sucursal_label").toUpperCase());
	         
	         c = r.createCell((short)6);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_remito_label").toUpperCase());
	         
	         c = r.createCell((short)7);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());   
	         
	         c = r.createCell((short)8);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cantidad_label").toUpperCase());   
	         
	         c = r.createCell((short)9);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("precio_litro_label").toUpperCase());   
	         
	         c = r.createCell((short)10);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("total_pesos_label").toUpperCase()); 
	         
	         c = r.createCell((short)11);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("perc_iva_label").toUpperCase()); 
	         
	         c = r.createCell((short)12);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("perc_iibb_label").toUpperCase()); 
	         
	         
	         c = r.createCell((short)13);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("un_chofer_label").toUpperCase()); 
	         
	         c = r.createCell((short)14);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("grupo_unidad_negocio_abreviado_chofer_label").toUpperCase()); 
	         
	         c = r.createCell((short)15);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("un_camion_label").toUpperCase());
	         
	         c = r.createCell((short)16);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("grupo_unidad_negocio_abreviado_vehiculo_label").toUpperCase());  
	        	         
	         c = r.createCell((short)17);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("patente_label").toUpperCase());
	         
	         c = r.createCell((short)18);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("chofer_label").toUpperCase());   
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estiloMonto4.setDataFormat(df.getFormat("#,##0.0000"));	
	         estiloMonto4.setBorderBottom(estiloMonto4.BORDER_THIN);
	         estiloMonto4.setBorderRight(estiloMonto4.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			RemitoTO remitoTO = new RemitoTO();
			int index =0;
						
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 remitoTO = (RemitoTO)lstAManipular.get(index);
				 index++;				
				 
				 //fecha
			     c = r.createCell((short)0);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getFecha()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getFecha());
			     
//			   hora
			     c = r.createCell((short)1);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getHora()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getHora());
			     
			     
//				   ccss
			     c = r.createCell((short)2);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getCcss()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getCcss());
			     
			     
//				   cod cliente alfa
			     c = r.createCell((short)3);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getCodClienteAlfa()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getCodClienteAlfa());
			     
			     
//				   cli descripcion
			     c = r.createCell((short)4);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getCliDescripcion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getCliDescripcion());
		         
			     
			     //sucursal
			     c = r.createCell((short)5);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getNroSucursal()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getNroSucursal());
		         
		         
		         //nro remito
		         c = r.createCell((short)6);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getNroRemito()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getNroRemito());
		         
		         
		         //producto
		         c = r.createCell((short)7);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getDescProducto()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getDescProducto());
		         
		         
		         //cantidad
		         c = r.createCell((short)8);
		         c.setCellStyle(estiloMonto);
		         if(remitoTO.getLitros()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getLitros().doubleValue());   
		         
		         
//		       precio c imp
		         c = r.createCell((short)9);
		         c.setCellStyle(estiloMonto4);
		         if(remitoTO.getPrecioConImpuestos()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getPrecioConImpuestos().doubleValue());      
		         
		         
		         //monto
		         c = r.createCell((short)10);
		         c.setCellStyle(estiloMonto);
		         if(remitoTO.getMontoTotal()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getMontoTotal().doubleValue());
		         
		         
//		       piva
		         c = r.createCell((short)11);
		         c.setCellStyle(estiloMonto);
		         if(remitoTO.getPiva()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getPiva().doubleValue());
		         
		         
//		       iibb
		         c = r.createCell((short)12);
		         c.setCellStyle(estiloMonto);
		         if(remitoTO.getIibb()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getIibb().doubleValue());
		         
		         //unidad de neg chofer
		         c = r.createCell((short)13);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getDescrUnidadNegocioC()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getDescrUnidadNegocioC());
		         
//		       grupo chofer
		         c = r.createCell((short)14);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getDescrGrupoUNC()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getDescrGrupoUNC());
		         
		         //grupo vehiculo
		         c = r.createCell((short)15);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getDescrUnidadNegocioV()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getDescrUnidadNegocioV());
		         
		         //unidad de neg vehiculo
		         c = r.createCell((short)16);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getDescrGrupoUNV()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getDescrGrupoUNV());
		         
		         
		        

	         
		         
		         
//		       patente         
		         c = r.createCell((short)17);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getPatente()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getPatente());
		         
		         
		         c = r.createCell((short)18);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getApellidoChofer()==null || remitoTO.getNombreChofer()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getApellidoChofer()+", "+ remitoTO.getNombreChofer());
		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	
	/**
	 * Metodo: generarExcelReporteRemitos
	 * Funcion: Generar excel del reporte de choferes activos e inactivos
	 * @param lstAManipular
	 * @param agente
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */										  
	public String generarExcelReporteRemitos(List lstAManipular,String agente) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("archivo_remitos_consumos")+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("archivo_remitos_consumos");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	       
	       	s.setColumnWidth((short)0, (short)((50 * 8) / ((double) 1 / 10)));
	    	s.setColumnWidth((short)1, (short)((50 * 8) / ((double) 1 / 10)));
			s.setColumnWidth((short)2, (short)((50 * 8) / ((double) 1 / 8)));
			s.setColumnWidth((short)3, (short)((50 * 8) / ((double) 1 / 10)));
			s.setColumnWidth((short)4, (short)((50 * 8) / ((double) 1 / 18)));
			s.setColumnWidth((short)5, (short)((50 * 8) / ((double) 1 / 13)));
			s.setColumnWidth((short)6, (short)((50 * 8) / ((double) 1 / 13)));
			s.setColumnWidth((short)7, (short)((50 * 8) / ((double) 1 / 13)));			
			s.setColumnWidth((short)8, (short)((50 * 8) / ((double) 1 / 25)));
	        
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         
	         c = r.createCell((short)0);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());
		     
		     c = r.createCell((short)1);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("hora_label").toUpperCase());
	        	         
		     c = r.createCell((short)2);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("sucursal_label").toUpperCase());
	         
	         c = r.createCell((short)3);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_remito_label").toUpperCase());
	         
	         c = r.createCell((short)4);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());   
	         
	         c = r.createCell((short)5);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("total_pesos_label").toUpperCase());  
	         
	         c = r.createCell((short)6);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("litros_label").toUpperCase());   
	         
	         c = r.createCell((short)7);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("patente_label").toUpperCase());
	         
	         c = r.createCell((short)8);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("chofer_label").toUpperCase());   
	 		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			RemitoTO remitoTO = new RemitoTO();
			int index =0;
						
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 remitoTO = (RemitoTO)lstAManipular.get(index);
				 index++;				
				 
				 //fecha
			     c = r.createCell((short)0);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getFecha()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getFecha());
			     
			     //hora
			     c = r.createCell((short)1);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getHora()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getHora());
		         
			     
			     //sucursal
			     c = r.createCell((short)2);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getNroSucursal()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getNroSucursal());
		         
		         
		         //unidad de negocio
		         c = r.createCell((short)3);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getNroRemito()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getNroRemito());
		         
		         
		         //grupo de la unidad de negocio
		         c = r.createCell((short)4);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getDescProducto()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getDescProducto());
		         
		         //activo         
		         c = r.createCell((short)5);
		         c.setCellStyle(estiloMonto);
		         if(remitoTO.getMontoTotal()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getMontoTotal().doubleValue());

//		       activo         
		         c = r.createCell((short)6);
		         c.setCellStyle(estiloMonto);
		         if(remitoTO.getLitros()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getLitros().doubleValue());
		         
		         
		         
//		       activo         
		         c = r.createCell((short)7);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getPatente()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getPatente());
		         
		         
		         c = r.createCell((short)8);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getApellidoChofer()==null || remitoTO.getNombreChofer()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getApellidoChofer()+", "+ remitoTO.getNombreChofer());
		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	/**
	 * Metodo: generarExcelReporteChofer
	 * Funcion: Generar excel del reporte de choferes activos e inactivos
	 * @param lstAManipular	
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */													  
	public String generarExcelReporteChofer(List lstAManipular,String fecha,Boolean activo,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			if(activo){
				nombArch = mensajeria.getMessage().getString("archivo_choferes_activos")+"_"+fecha+".xls";
			}else{
				nombArch = mensajeria.getMessage().getString("archivo_choferes_bajas")+"_"+fecha+".xls";
			}
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			if(activo){
				etiqueta=mensajeria.getMessage().getString("archivo_choferes_activos");
			}else{
				etiqueta=mensajeria.getMessage().getString("archivo_choferes_bajas");
			}
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	   
			int ind=0;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 25)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 8)));
			ind=ind+1;
			if(tipoUsu==0){
				s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));				
				ind=ind+1;
			}			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));
			ind=ind+1;
			if(tipoUsu==0){
				s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 22)));				
				ind=ind+1;
			}
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
	         
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nombre_label").toUpperCase());
		     ind=ind+1;
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("dni_label").toUpperCase());
		     
		     if(tipoUsu==0){
	        	 ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("pin_label").toUpperCase()); 
	         }
	         
		     ind=ind+1;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("unidad_negocio_label").toUpperCase());
	         ind=ind+1;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("grupo_unidad_negocio_label").toUpperCase()); 
	         
	         if(tipoUsu==0){
	        	 ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase()); 
	         }
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("activo_label").toUpperCase());
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("inicializado_label").toUpperCase()); 
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("fecha_alta_label").toUpperCase());   
	 		 
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("fecha_baja_label").toUpperCase());   
	 		 
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();
			String estado="";
			MChoferTO mChoferTO = new MChoferTO();
			int index =0;
			ind=0;		
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 mChoferTO = (MChoferTO)lstAManipular.get(index);
				 index++;				
				 
				 //nombre del chofer
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mChoferTO.getApellidoChofer()==null || mChoferTO.getNombreChofer()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getApellidoChofer()+", "+ mChoferTO.getNombreChofer());
		         
			     
			     //dni
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mChoferTO.getDniChofer()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getDniChofer());
		         
			     
			     if(tipoUsu==0){
		        	 ind=ind+1;
			         //pin del chofer
			         c = r.createCell((short)ind);
			         c.setCellStyle(estilofilas);
			         if(mChoferTO.getPin()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mChoferTO.getPin());
		         }
		         
		         //unidad de negocio
			     ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getDescrUnidadNegocio()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getDescrUnidadNegocio());
		         
		         ind=ind+1;
		         //grupo de la unidad de negocio
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getDescrGrupoUN()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getDescrGrupoUN());
		         
		         if(tipoUsu==0){
		        	 ind=ind+1;
			         //cliente
			         c = r.createCell((short)ind);
			         c.setCellStyle(estilofilas);
			         if(mChoferTO.getCliDescripcion()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mChoferTO.getCliDescripcion());
		         }
		         
		         //activo
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getActivoDesc()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getActivoDesc());
		         
		         //inicializado
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getInicializadoDesc()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getInicializadoDesc());
		         
		         //fecha alta
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getFechaAlta()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getFechaAlta());
		         
//		       fecha baja
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getFechaBaja()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getFechaBaja());
		         
		         ind=0;
		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	
	/**
	 * Metodo: generarExcelReporteCliente
	 * Funcion: Generar excel del reporte de clientes
	 * @param lstAManipular	
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */													  
	public String generarExcelReporteClientes(List lstAManipular,String fecha) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{				
		
			nombArch = mensajeria.getMessage().getString("archivo_clientes")+"_"+fecha+".xls";			
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("archivo_clientes");
			
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	   
			int ind=0;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;
	       	s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 25)));
	       	ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 20)));
			ind=ind+1;			
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
	         
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 12)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         
	         ind=0;
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase());
		     ind=ind+1;
		     c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase()); 
	         
		     ind=ind+1;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cuit_label").toUpperCase());
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("limite_credito_label").toUpperCase());
	         
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("domicilio_label").toUpperCase()); 
	         
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cod_postal_label").toUpperCase()); 
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("localidad_label").toUpperCase());   
	 		 
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("provincia_cli_label").toUpperCase());   
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cat_iva_label").toUpperCase());   
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("nro_iibb_label").toUpperCase());   
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("inscr_iibb_label").toUpperCase());   
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("cond_vta_label").toUpperCase());   
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("activo_label").toUpperCase());   
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("fecha_baja_label").toUpperCase()); 
	         
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();
			String estado="";
			MClienteTO mChoferTO = new MClienteTO();
			int index =0;
			ind=0;		
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 mChoferTO = (MClienteTO)lstAManipular.get(index);
				 index++;				
				 
				 //cod cliente alfa
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mChoferTO.getCodClienteAlfa()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getCodClienteAlfa());
		         
			     
			     //nombre
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(mChoferTO.getNombre()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getNombre());
		         
		         //cuit
			     ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getCuit()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getCuit());
		         
		         ind=ind+1;
		         //limite de credito
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getLimiteCredito()==null || mChoferTO.getLimiteCredito().doubleValue()==0)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getLimiteCredito().doubleValue());
		         
		         //domicilio
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getDomicilio()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getDomicilio());
		         
		         //cod postal
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getCodPostal()==null || mChoferTO.getCodPostal()==0)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getCodPostal());
		         
		         //localidad
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getLocalidad()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getLocalidad());
		         
//		      provincia
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getProvincia()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getProvincia());
		         
		         
//			      categoria de iva
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getCategoriaIVA()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getCategoriaIVA());
		         
		         
//			      nro iibb
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getNumeroIIBB()==null || mChoferTO.getNumeroIIBB().doubleValue()==0)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getNumeroIIBB().toString());
		         
		         
//			      inscripcion iibb
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getInscripcionIIBB()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getInscripcionIIBB());
		         
//			      condicion venta
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getCondicionVta()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getCondicionVta());	
		         
//			     estado
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getActivoDesc()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getActivoDesc());	
		         
		         
		         
//			    fecha_baja_label
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mChoferTO.getFechaBaja()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mChoferTO.getFechaBaja());	

		         
		         ind=0;
		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
		
	
	/**
	 * Metodo: generarExcelReporteVehiculos
	 * Funcion: Generar excel del reporte de vehiculos activos e inactivos
	 * @param lstAManipular
	 * @param agente
	 * @param activo
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */											  
	public String generarExcelReporteVehiculos(List lstAManipular,String fecha,Boolean activo,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			if(activo){
				nombArch = mensajeria.getMessage().getString("archivo_vehiculos_activos")+"_"+fecha+".xls";
			}else{
				nombArch = mensajeria.getMessage().getString("archivo_vehiculos_bajas")+"_"+fecha+".xls";
			}
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			if(activo){
				etiqueta=mensajeria.getMessage().getString("archivo_vehiculos_activos");
			}else{
				etiqueta=mensajeria.getMessage().getString("archivo_vehiculos_bajas");
			}
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas
		      		int ind=0; 
					s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
					if(tipoUsu==0){
						ind=ind+1;
						s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
					}
					ind=ind+1;
					s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 15)));
					ind=ind+1;
					s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));
					if(tipoUsu==0){
						ind=ind+1;
						s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 18)));
					}
					ind=ind+1;
					s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));
					ind=ind+1;
					s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));		
					ind=ind+1;
					s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));		
					ind=ind+1;
					s.setColumnWidth((short)ind, (short)((50 * 8) / ((double) 1 / 10)));		
		
	        
	         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         
	         	ind=0;
		   	
	         c = r.createCell((short)ind);	        
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("patente_label").toUpperCase());
	         
		     if(tipoUsu==0){
	        	 ind=ind+1;
	        	 c = r.createCell((short)ind);	      
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("codigo_barra_label").toUpperCase()); 
	         }
		     
		     ind=ind+1;
		     c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("unidad_negocio_label").toUpperCase());
	         
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("grupo_unidad_negocio_label").toUpperCase()); 
	         
	         if(tipoUsu==0){
	        	 ind=ind+1;
	        	 c = r.createCell((short)ind);	      
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase()); 
	         }
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("activo_label").toUpperCase());   
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("inicializado_label").toUpperCase());   
	 		 
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("fecha_alta_label").toUpperCase());   
	 		
	         ind=ind+1;
	         c = r.createCell((short)ind);	      
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("fecha_baja_label").toUpperCase());   
	 		
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();
			String estado="";
			MVehiculoTO mVehiculoTO = new MVehiculoTO();
			int index =0;		
			ind=0;
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 mVehiculoTO = (MVehiculoTO)lstAManipular.get(index);
				 index++;				
				 
			     //patente
			   	 c = r.createCell((short)ind);		       
			     c.setCellStyle(estilofilas);	        
			     if(mVehiculoTO.getDominio()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mVehiculoTO.getDominio());
			     
			     if(tipoUsu==0){
		        	 ind=ind+1;
//		        	cod barra
			         c = r.createCell((short)ind);
			         c.setCellStyle(estilofilas);
			         if(mVehiculoTO.getCodBarra()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mVehiculoTO.getCodBarra());
		         }
		         
		         ind=ind+1;
		         //unidad de negocio			    
		         c = r.createCell((short)ind);		       
		         c.setCellStyle(estilofilas);
		         if(mVehiculoTO.getDescrUnidadNegocio()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mVehiculoTO.getDescrUnidadNegocio());
		         
		         ind=ind+1;
		         //grupo de la unidad de negocio		         
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mVehiculoTO.getDescrGrupoUN()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mVehiculoTO.getDescrGrupoUN());
		         
		         if(tipoUsu==0){
		        	 ind=ind+1;
//		        	cliente
			         c = r.createCell((short)ind);
			         c.setCellStyle(estilofilas);
			         if(mVehiculoTO.getCliDescripcion()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(mVehiculoTO.getCliDescripcion());
		         }
		         ind=ind+1;
		         //activo         
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mVehiculoTO.getActivoDesc()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mVehiculoTO.getActivoDesc());
		         ind=ind+1;
		         //inicializado         
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mVehiculoTO.getInicializadoDesc()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mVehiculoTO.getInicializadoDesc());
		         
		         //fecha alta
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mVehiculoTO.getFechaAlta()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mVehiculoTO.getFechaAlta());
		         
//		       fecha baja
		         ind=ind+1;
		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(mVehiculoTO.getFechaBaja()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(mVehiculoTO.getFechaBaja());
		         
		         ind=0;
		   }
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	
	public String generarExcelReporteMayorContableVer1_01(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		
		Properties props= fileUtil.getPropertiesFile();		
		try{					
			nombArch = mensajeria.getMessage().getString("mayor_contable_excel_label")+"_"+fecha+".xls";			
		    //fila en la empieza la lista de datos 
			Integer cantFilas = 2+ lstAManipular.size();			
			AsientoContableTO percepcionesIVATO = new AsientoContableTO();
			int index =0;		
			
			/*formato nuevo*/
			String auxFilaProcesada="";
			String rutaArchivoEntrada = props.getProperty("saveArchivosExcel");
	        File descriptorSalida = new File(rutaArchivoEntrada+nombArch);
		    FileOutputStream outFileStream  = new FileOutputStream(descriptorSalida);
		    PrintWriter outStream = new PrintWriter(outFileStream);
		    String filaProcesada="";
		    DecimalFormat myFormatter = new DecimalFormat("###,###.00");		   
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{				 
				 if(rownum==1){
					 filaProcesada=obtenerTitulosInformeMay();
				 }else{	
					 
						 percepcionesIVATO = (AsientoContableTO)lstAManipular.get(index);
						 index++;
						 
						 //cuenta					   
					   	filaProcesada=percepcionesIVATO.getCuenta();
					   	 
					   	 //descripcionde la cuenta			     
					     if(percepcionesIVATO.getCuentaDesc()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=percepcionesIVATO.getCuentaDesc();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					     
					 	//fecha
					 	auxFilaProcesada = percepcionesIVATO.getFecha();   			   		         
					   	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					    
					     //hora			        
					     if(percepcionesIVATO.getHora()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=percepcionesIVATO.getHora();
					 	 filaProcesada=filaProcesada+";"+auxFilaProcesada;
					     
					     //ccss			     
					     if(percepcionesIVATO.getCcssDesc()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=percepcionesIVATO.getCcssDesc();
					 	 filaProcesada=filaProcesada+";"+auxFilaProcesada;
					     
					     
					     //cod cliente alfa
					 	 if(percepcionesIVATO.getCodClienteAlfa()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=percepcionesIVATO.getCodClienteAlfa();   
					     filaProcesada=filaProcesada+";"+auxFilaProcesada;
					     
					     //cliente		   
					     if(percepcionesIVATO.getDescripcionCliente()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=percepcionesIVATO.getDescripcionCliente();  
					     filaProcesada=filaProcesada+";"+auxFilaProcesada;
					     
					     
					     
					     //nro ejercicio		     
					     if(percepcionesIVATO.getNroEjercicio()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=percepcionesIVATO.getNroEjercicio().toString();  
					     filaProcesada=filaProcesada+";"+auxFilaProcesada;
					     
					     
					    
					     
					     //nro asiento
					     if(percepcionesIVATO.getNroAsiento()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=percepcionesIVATO.getNroAsiento().toString();  
					     filaProcesada=filaProcesada+";"+auxFilaProcesada;
					     
					    
					     
					     //leyenda
					     if(percepcionesIVATO.getLeyenda()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=percepcionesIVATO.getLeyenda();  
					     filaProcesada=filaProcesada+";"+auxFilaProcesada;
					     
					  //	System.out.println("percepcionesIVATO.getNroComp() "+percepcionesIVATO.getNroComp());
					     
					     //nrocomprobante
					     auxFilaProcesada="";
					     if(percepcionesIVATO.getNroSuc()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=percepcionesIVATO.getNroSuc()+" ";
					     
					     if(percepcionesIVATO.getNroComp()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=auxFilaProcesada+percepcionesIVATO.getNroComp()+"";
					    
					     
					     filaProcesada=filaProcesada+";"+auxFilaProcesada;
					    			     
		//			   comprobante
					     if(percepcionesIVATO.getTipoComprobante()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=percepcionesIVATO.getTipoComprobante();  
					     filaProcesada=filaProcesada+";"+auxFilaProcesada;
					         
					     
		//				   tipo comprobante
					     if(percepcionesIVATO.getLetra()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=percepcionesIVATO.getLetra();  
					     filaProcesada=filaProcesada+";"+auxFilaProcesada;
					     
					     //comprobante asociado, es para cheque que tiene asociado el nro de recibo
					     if(percepcionesIVATO.getComprobanteAsociado()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=percepcionesIVATO.getComprobanteAsociado();  
					     filaProcesada=filaProcesada+";"+auxFilaProcesada;
					     
					     
//					   nro de rendicion es para los reg de cuenta banco, pago al contado
					     if(percepcionesIVATO.getNroRendicion()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=percepcionesIVATO.getNroRendicion();  
					     filaProcesada=filaProcesada+";"+auxFilaProcesada;
					    		     
					     //debe		
					     auxFilaProcesada="";
					     if(percepcionesIVATO.getDebHab().equals("D")){
						     if(percepcionesIVATO.getValor()==null)
						    	 auxFilaProcesada="";
					         else{
					        	 auxFilaProcesada=percepcionesIVATO.getValor().toString();					        	
					        	 auxFilaProcesada=myFormatter.format(new BigDecimal(auxFilaProcesada)) ;					        	 
					         }
						}
					     filaProcesada=filaProcesada+";"+auxFilaProcesada;
					     
					     
		//			   haber	
					   auxFilaProcesada="";
				     if(percepcionesIVATO.getDebHab().equals("H")){
					     if(percepcionesIVATO.getValor()==null)
					    	 auxFilaProcesada="";
				         else{
				        	 auxFilaProcesada=percepcionesIVATO.getValor().toString();				        	
				        	 auxFilaProcesada=myFormatter.format(new BigDecimal(auxFilaProcesada)) ;
				         }
					}
				     filaProcesada=filaProcesada+";"+auxFilaProcesada;
				     
				     
		//			   saldo				     
					if(percepcionesIVATO.getSaldo()==null)
						auxFilaProcesada="";
				    else{
				    	auxFilaProcesada=percepcionesIVATO.getSaldo().toString();				    	
				    	
				    	 if(percepcionesIVATO.getUltimo()!=null && percepcionesIVATO.getUltimo().equals(new Integer(1))){
				    		 auxFilaProcesada="Total: "+ myFormatter.format(new BigDecimal(auxFilaProcesada)) ;	 
					     }else{
					    	 auxFilaProcesada=myFormatter.format(new BigDecimal(auxFilaProcesada)) ;
					     }
				    }
						 
					
					filaProcesada=filaProcesada+";"+auxFilaProcesada;				     
			}			   
		    outStream.println(filaProcesada);      

		   }
	        
			
			outStream.close();		
			outFileStream.close();
			
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
		
	private String obtenerTitulosInformeMay(){
		try{
		Messages mensajeria = new Messages();
		DataUtil dataUtil= new DataUtil();
		
		 String titCuenta=mensajeria.getMessage().getString("cuenta_label").toUpperCase();
		 String titDesscCuenta= mensajeria.getMessage().getString("descripcion_cuenta_label").toUpperCase();
		 String titFecha=mensajeria.getMessage().getString("fecha_label").toUpperCase();
		 String titHora=mensajeria.getMessage().getString("hora_label").toUpperCase();
		 String titCCSS=mensajeria.getMessage().getString("ccss_label").toUpperCase();
		 String titCodClienteAlfa=mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase();
		 String titCliente=mensajeria.getMessage().getString("cliente_label").toUpperCase();
		 String titNroEjercicio=mensajeria.getMessage().getString("nro_ejercicio_label").toUpperCase();
		 String titNroAsiento=mensajeria.getMessage().getString("nro_asiento_label").toUpperCase();		 
		 String titDescrAcento=mensajeria.getMessage().getString("descripcion_aciento_contable_label").toUpperCase();
		 String titNroComp=mensajeria.getMessage().getString("nro_compronate_label_x").toUpperCase();
		 String titCompLabel=mensajeria.getMessage().getString("comprobante_label").toUpperCase();
		 String titTipoComp=mensajeria.getMessage().getString("tipo_comprobante_label").toUpperCase();
		 String titComprobanteAsociado=mensajeria.getMessage().getString("comprobante_asociado_label").toUpperCase();
		 String titNroRendicion=mensajeria.getMessage().getString("nro_rendicion_label").toUpperCase();
		 String titDebe=mensajeria.getMessage().getString("debe_label").toUpperCase();
		 String titHabe=mensajeria.getMessage().getString("haber_label").toUpperCase();
		 String titMonto=mensajeria.getMessage().getString("monto_adeudado_label").toUpperCase();
	
		 String filaProcesada=titCuenta+";"+titDesscCuenta+";"+titFecha+";"+titHora+";"+titCCSS+";"+titCodClienteAlfa+";"+titCliente+";"+titNroEjercicio+";"+titNroAsiento+";"+titDescrAcento+";"+titNroComp+";"+titCompLabel+";"+titTipoComp+";"+titComprobanteAsociado+";"+titNroRendicion+";"+titDebe+";"+titHabe+";"+titMonto;
		 return filaProcesada;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	
	}
	
	
	
	private String obtenerTitulosInformeRemCom(int tipoUsu, String codClienteAlfa,String vieneDe,boolean verPrecioCimp){
		try{
		Messages mensajeria = new Messages();
		DataUtil dataUtil= new DataUtil();
		
	  
        String titFecha=mensajeria.getMessage().getString("fecha_label").toUpperCase();
        String titHora=mensajeria.getMessage().getString("hora_label").toUpperCase();
        String titCCSS=mensajeria.getMessage().getString("ccss_label").toUpperCase();
        String titSuc=mensajeria.getMessage().getString("sucursal_label").toUpperCase();
        String titNroRem=mensajeria.getMessage().getString("nro_remito_label").toUpperCase();
        String titCodCliAl=mensajeria.getMessage().getString("cod_cliente_Alfa").toUpperCase();
        String titCodCli=mensajeria.getMessage().getString("cliente_label").toUpperCase();
        String titPro=mensajeria.getMessage().getString("producto_label").toUpperCase();
        String titCant=mensajeria.getMessage().getString("cantidad_label").toUpperCase();
        String titPreLit=mensajeria.getMessage().getString("precio_litro_label").toUpperCase();
        String precioLista="";
        String comparacion="";
        //if(codClienteAlfa.equals("marcela")){
        if(verPrecioCimp){
        	precioLista="Precio De Lista AUX";
        	comparacion="Comparacion de Precios";
        }
        String titTotPes=mensajeria.getMessage().getString("total_pesos_label").toUpperCase();
        String titNroAsCont=mensajeria.getMessage().getString("nro_asiento_contable_label").toUpperCase();
        String titNroEjCont=mensajeria.getMessage().getString("nro_ejercicio_contable_label").toUpperCase();
        String titItc=mensajeria.getMessage().getString("itc_label").toUpperCase();
        String titTasaFon=mensajeria.getMessage().getString("tasa_fondo_label").toUpperCase();
        String titLeyCba=mensajeria.getMessage().getString("ley_10081_label").toUpperCase();
        String titco2="Imp CO2";
        String titNroIIBB=mensajeria.getMessage().getString("perc_iibb_label").toUpperCase();
        String titPerIVA=mensajeria.getMessage().getString("perc_iva_label").toUpperCase();   
        
        String titNroIVA=mensajeria.getMessage().getString("iva_label").toUpperCase();
        String titKil=mensajeria.getMessage().getString("kilometaje_label").toUpperCase();
        String titNroLiq=mensajeria.getMessage().getString("nro_liquidacion_label").toUpperCase();
        String titCodBar=mensajeria.getMessage().getString("cod_barra_label").toUpperCase();
        String titNroAuto=mensajeria.getMessage().getString("nro_autorizacion_label").toUpperCase();
        String titSucFac=mensajeria.getMessage().getString("sucur_factu_total_label").toUpperCase();
        String titNroFacLar=mensajeria.getMessage().getString("nro_factura_largo_label").toUpperCase();
        String titTipFac=mensajeria.getMessage().getString("tipo_factura_label").toUpperCase();
        String titFechaFact=mensajeria.getMessage().getString("fecha_facturacion_label").toUpperCase();
        String titReFacturaciones="";
        if(vieneDe.equals("ref")){
           titReFacturaciones=mensajeria.getMessage().getString("refacturacion_label").toUpperCase()+";";
        }
        String titUnChof=mensajeria.getMessage().getString("un_chofer_label").toUpperCase();
        String titGrupoUniCh=mensajeria.getMessage().getString("grupo_unidad_negocio_abreviado_chofer_label").toUpperCase();
        String titUnCam=mensajeria.getMessage().getString("un_camion_label").toUpperCase();
        String titGrupoUnVe=mensajeria.getMessage().getString("grupo_unidad_negocio_abreviado_vehiculo_label").toUpperCase();
        String titPatente=mensajeria.getMessage().getString("patente_label").toUpperCase();
        String titChof=mensajeria.getMessage().getString("chofer_label").toUpperCase();
        String titCondi=mensajeria.getMessage().getString("condicion_label").toUpperCase();
        String titEestadoCom=mensajeria.getMessage().getString("estado_combustible_label").toUpperCase();
        
        String filaProcesada="";
       
        if(verPrecioCimp){
        	 filaProcesada=titFecha+";"+titHora+";"+titCCSS+";"+titSuc+";"+titNroRem+";"+titCodCliAl+";"+titCodCli
 			+";"+titPro+";"+titCant+";"+titPreLit+";"+precioLista+";"+comparacion+";"+titTotPes+";"+titNroAsCont+";"+titNroEjCont
 			+";"+titItc+";"+titTasaFon+";"+titLeyCba +";"+ titco2   +";"+titNroIIBB+";"+titPerIVA + ";"+titNroIVA+";"+titKil+";"+titNroLiq+";"+titCodBar
 			+";"+titNroAuto+";"+titSucFac+";"+titNroFacLar+";"+titTipFac+";"+titFechaFact+";"+titReFacturaciones+titUnChof+";"+titGrupoUniCh+";"+titUnCam
 			+";"+titGrupoUnVe+";"+titPatente+";"+titChof+";"+titCondi+";"+titEestadoCom;
        }else{
	         filaProcesada=titFecha+";"+titHora+";"+titCCSS+";"+titSuc+";"+titNroRem+";"+titCodCliAl+";"+titCodCli
			+";"+titPro+";"+titCant+";"+titPreLit+";"+titTotPes+";"+titNroAsCont+";"+titNroEjCont
			+";"+titItc+";"+titTasaFon+";"+titLeyCba+";"+ titco2 +";"+titNroIIBB+";"+titPerIVA + ";"+titNroIVA+";"+titKil+";"+titNroLiq+";"+titCodBar
			+";"+titNroAuto+";"+titSucFac+";"+titNroFacLar+";"+titTipFac+";"+titFechaFact+";"+titReFacturaciones+titUnChof+";"+titGrupoUniCh+";"+titUnCam
			+";"+titGrupoUnVe+";"+titPatente+";"+titChof+";"+titCondi+";"+titEestadoCom;
        }
		 return filaProcesada;
		}catch(Exception ex){
			ex.printStackTrace();
			return "";
		}
	
	}
	
	public String generarExcelReporteRemitoCompletoVer1_01(List lstAManipular,String fecha,int tipoUsu,String codClienteAlfa,String vieneDe,boolean verPrecioCimp) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
	
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			nombArch = mensajeria.getMessage().getString("archivo_remito_completo_label")+"_"+fecha+".xls";			
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		         
			
			//fila en la empieza la lista de datos 
			Integer cantFilas = 2+ lstAManipular.size();			
			RemitoTO reciboTO = null;
			int index =0;		
			
			/*formato nuevo*/
			String auxFilaProcesada="";
			String rutaArchivoEntrada = props.getProperty("saveArchivosExcel");
	        File descriptorSalida = new File(rutaArchivoEntrada+nombArch);
		    FileOutputStream outFileStream  = new FileOutputStream(descriptorSalida);
		    PrintWriter outStream = new PrintWriter(outFileStream);
		    String filaProcesada="";
		    DecimalFormat myFormatter = new DecimalFormat("###,###.00");
		    DecimalFormat myFormatter2 = new DecimalFormat("###,###.0000");
		    
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				
				
				 if(rownum==1){
					 filaProcesada=obtenerTitulosInformeRemCom(tipoUsu,codClienteAlfa,vieneDe,verPrecioCimp);
				 }else{						 
						 reciboTO = (RemitoTO)lstAManipular.get(index);					 
						 index++;
						
						 
						//fecha			   
					   	filaProcesada=reciboTO.getFecha();
					   	
					   	//Hora
					   	filaProcesada=filaProcesada+";"+reciboTO.getHora();
					   	
						//ccss
					   	filaProcesada=filaProcesada+";"+reciboTO.getCcss();
					   	 
					   	 //pref del  remito			     
					     if(reciboTO.getNroSucursal()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getNroSucursal().toString();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					 	       
					 	 //nro remito			     
					     if(reciboTO.getNroRemito()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getNroRemito().toString();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada; 	
				         
					 	//codCliente alfa
					 	if(reciboTO.getCodClienteAlfa()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getCodClienteAlfa().toString();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;					 
					     
					     //  cliente razon social
					 	if(reciboTO.getCliDescripcion()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getCliDescripcion().toString();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada; 
					    
					    //producto
					 	if(reciboTO.getDescProducto()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getDescProducto().toString();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada; 					     
					     
					     //cantidad de litros
					 	if(reciboTO.getLitros()==null){
					    	 auxFilaProcesada="";
					 	}else{
				        	 auxFilaProcesada=reciboTO.getLitros().toString();					        	
				        	 auxFilaProcesada=myFormatter.format(new BigDecimal(auxFilaProcesada)) ;			        	
				         }
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
				         
				         //precio litro
					 	if(reciboTO.getPrecioKilo()==null){
					    	 auxFilaProcesada="";
					 	}else{
				        	 auxFilaProcesada=reciboTO.getPrecioKilo().toString();					        	
				        	 auxFilaProcesada=myFormatter2.format(new BigDecimal(auxFilaProcesada)) ;	
				         }	 				        	
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;					 	 
						
					 	//if(codClienteAlfa.equals("marcela")){
					 	if(verPrecioCimp){	
					 		 //precio litro de lista
						 	if(reciboTO.getPrecioListaPorCCSSArticuloFecha()==null){
						    	 auxFilaProcesada="";
						 	}else{
					        	 auxFilaProcesada=reciboTO.getPrecioListaPorCCSSArticuloFecha().toString();					        	
					        	 auxFilaProcesada=myFormatter2.format(new BigDecimal(auxFilaProcesada)) ;	
					         }	 				        	
						 	filaProcesada=filaProcesada+";"+auxFilaProcesada;		
						 	
						 	 //compracion precio litro
						    auxFilaProcesada=reciboTO.getComparacionPrecioCImp();
						 	filaProcesada=filaProcesada+";"+auxFilaProcesada;		
					 		
					 	}
						 //total $
					 	if(reciboTO.getMontoTotal()==null){
					    	 auxFilaProcesada="";
					 	} else{				        	
				        	 auxFilaProcesada=reciboTO.getMontoTotal().toString();					        	
				        	 auxFilaProcesada=myFormatter.format(new BigDecimal(auxFilaProcesada)) ;	
				         }
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;					 	  
				        
				         //nro asiento contable
					 	if(reciboTO.getNroAsientoContable()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getNroAsientoContable().toString();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada; 
				         
					     //nro ejercicio contable
						if(reciboTO.getNroEjercicioContable()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getNroEjercicioContable().toString();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;				 	
				         
				         //ITC 
					 	if(reciboTO.getItc()==null){
					    	 auxFilaProcesada="";
					 	} else{				        	
				        	 auxFilaProcesada=reciboTO.getItc().toString();					        	
				        	 auxFilaProcesada=myFormatter2.format(new BigDecimal(auxFilaProcesada)) ;	
				         }				        	
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					 	
					 	
				         
				         //tasa/fondo
					 	if(reciboTO.getTasaFondo()==null){
					    	 auxFilaProcesada="";
					 	}else{				        	
				        	 auxFilaProcesada=reciboTO.getTasaFondo().toString();					        	
				        	 auxFilaProcesada=myFormatter2.format(new BigDecimal(auxFilaProcesada)) ;	
				         }			        	
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					 	
					 	  //impuesto cordoba
					 	if(reciboTO.getLeyCba()==null){
					    	 auxFilaProcesada="";
					 	}else{				        	
				        	 auxFilaProcesada=reciboTO.getLeyCba().toString();					        	
				        	 auxFilaProcesada=myFormatter2.format(new BigDecimal(auxFilaProcesada)) ;	
				         }			        	
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					 	
					 	
					 	  //impuesto cordoba
					 	if(reciboTO.getCO2()==null){
					    	 auxFilaProcesada="";
					 	}else{				        	
				        	 auxFilaProcesada=reciboTO.getCO2().toString();					        	
				        	 auxFilaProcesada=myFormatter2.format(new BigDecimal(auxFilaProcesada)) ;	
				         }			        	
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					 	
					 	///IIBB
					 	if(reciboTO.getIibb()==null){
					    	 auxFilaProcesada="";
					 	} else{				        	
				        	 auxFilaProcesada=reciboTO.getIibb().toString();					        	
				        	 auxFilaProcesada=myFormatter2.format(new BigDecimal(auxFilaProcesada)) ;	
				         }					        	
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;	
					 	
//					 	periva
					 	if(reciboTO.getPiva()==null){
					    	 auxFilaProcesada="";
					 	} else{				        	
				        	 auxFilaProcesada=reciboTO.getPiva().toString();					        	
				        	 auxFilaProcesada=myFormatter2.format(new BigDecimal(auxFilaProcesada)) ;	
				         }					        	
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;	
				       
				         
		//		       iva
					 	if(reciboTO.getIva()==null){
					    	 auxFilaProcesada="";
					 	}else{				        	
				        	 auxFilaProcesada=reciboTO.getIva().toString();					        	
				        	 auxFilaProcesada=myFormatter.format(new BigDecimal(auxFilaProcesada)) ;	
				         }	
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					 	
//					       kilometraje
					 	if(reciboTO.getKilometraje()==null){
					    	 auxFilaProcesada="";
					 	}else{				        	
				        	 auxFilaProcesada=reciboTO.getKilometraje().toString();					        	
				        	 auxFilaProcesada=myFormatter.format(new BigDecimal(auxFilaProcesada)) ;	
				         }					        	
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					 	
//					       nro liquidacion
					 	if(reciboTO.getNroLiquidacion()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getNroLiquidacion().toString();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;

//					       codigo de barra
					 	if(reciboTO.getCodBarra()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getCodBarra().toString();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;

//					       nro de autorizacion
					 	if(reciboTO.getNroAutorizacion()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getNroAutorizacion().toString();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
		
//					       prefijo factura
					 	if(reciboTO.getNroSucursalFactura()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getNroSucursalFactura().toString();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;

//					       nro factura
					 	if(reciboTO.getNroFactura()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getNroFactura().toString();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;

//					      tipo comprobante
					 	if(reciboTO.getTipoComprobante()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getTipoComprobante();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
		 	
//					      fecha de facturacion
					 	if(reciboTO.getFechaFacturacion()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getFechaFacturacion();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					 	
					 	if(vieneDe.equals("ref")){
	//					     reFacturaciones
						 	if(reciboTO.getReFacturacion()==null)
						    	 auxFilaProcesada="";
					         else
					        	 auxFilaProcesada=reciboTO.getReFacturacion();
						 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					 	}
					 	
//					     unidad de negocio chofer
					 	if(reciboTO.getDescrUnidadNegocioC()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getDescrUnidadNegocioC();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
		 	
//					     //grupo unidad de negocio chofer
					 	if(reciboTO.getDescrGrupoUNC()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getDescrGrupoUNC();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;							 
						 
		//				unidad de negocio vehiculo
					 	if(reciboTO.getDescrUnidadNegocioV()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getDescrUnidadNegocioV();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;	
					 					   
						 
		//					unidad de negocio vehiculo
					 	if(reciboTO.getDescrGrupoUNV()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getDescrGrupoUNV();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					 	
					      //patente
					 	if(reciboTO.getPatente()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getPatente();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					 	
					    	     
				         
				         //chofer
					 	if(reciboTO.getApellidoChofer()==null || reciboTO.getNombreChofer()==null)
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getApellidoChofer()+", "+ reciboTO.getNombreChofer();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					 						    
					     
		//			   condicion
						if(reciboTO.getCondicionDesc()==null )
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getCondicionDesc();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
					 	
//						   estado
					 	if(reciboTO.getFacturadoString()==null )
					    	 auxFilaProcesada="";
				         else
				        	 auxFilaProcesada=reciboTO.getFacturadoString();
					 	filaProcesada=filaProcesada+";"+auxFilaProcesada;
						 			     
				 	}			   
				 outStream.println(filaProcesada); 
		    
			}
	         
			outStream.close();		
			outFileStream.close();	
			
			outFileStream= null;
			outStream= null;
			fileUtil= null;
			mensajeria = null;	
			
			reciboTO= null;
			filaProcesada= null;
			
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	
	
	/**//**
	 * PRUEBA - SU CODIGO ES USADO SOLO DE PRUEBA
	 * @param operacionPoliza
	 * @param lstAManipular
	 * @param cantCel
	 * @throws IOException
	 *//*
	private void generarExcelCtaCte(OperacionPoliza operacionPoliza,List lstAManipular,Integer cantCel) throws IOException{
		short rownum;

		//create a new file -archivo
		FileOutputStream out = new FileOutputStream("c:\\workbook.xls");
		//create a new workbook - libro
		HSSFWorkbook wb = new HSSFWorkbook();
		// create a new sheet - hoja
		HSSFSheet s = wb.createSheet();
		// declare a row object reference - fila
		HSSFRow r = null;
		//declare a cell object reference - celda
		HSSFCell c = null;
		//create 3 cell styles - estilos de celdas
		HSSFCellStyle cs = wb.createCellStyle();
		HSSFCellStyle cs2 = wb.createCellStyle();
		HSSFCellStyle cs3 = wb.createCellStyle();
		HSSFDataFormat df = wb.createDataFormat();
		// create 2 fonts objects - objetos de fuente
		HSSFFont f = wb.createFont();
		HSSFFont f2 = wb.createFont();

		//set font 1 to 12 point type - tamaï¿½o 12
		f.setFontHeightInPoints((short) 12);
		//make it blue - color azul
		f.setColor( (short)0xc );
		//make it bold - negrita
		//arial is the default font
		f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		//set font 2 to 10 point type
		f2.setFontHeightInPoints((short) 10);
		//make it red
		f2.setColor( (short)HSSFFont.COLOR_RED );
		//make it bold
		f2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//tachado
		f2.setStrikeout( true );

		//set cell stlye
		cs.setFont(f);
		//set the cell format 
		cs.setDataFormat(df.getFormat("#,##0.0"));
		
		//set a thin border
		cs2.setBorderBottom(cs2.BORDER_THIN);
		//fill w fg fill color
		cs2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
		//set the cell format to text see HSSFDataFormat for a full list
		cs2.setDataFormat(HSSFDataFormat.getBuiltinFormat("Aguante"));

//		 set the font
		cs2.setFont(f2);

//		 set the sheet name in Unicode
		wb.setSheetName(0, "Silvana " + 
		                   "Hoja 1 IAM", 
		                HSSFWorkbook.ENCODING_UTF_16 );
//		 in case of compressed Unicode
//		 wb.setSheetName(0, "HSSF Test", HSSFWorkbook.ENCODING_COMPRESSED_UNICODE );
//		 create a sheet with 30 rows (0-29)
		
		//prueba silvana
		Integer cantFilas = lstAManipular.size();
		Integer cantCeldas = cantCel;
		//
		
		for (rownum = (short) 0; rownum < cantFilas; rownum++)
		{
		    // create a row
		    r = s.createRow(rownum);
		    // on every other row
		   // if ((rownum % 2) == 0)
		    //{
		        // make the row height bigger  (in twips - 1/20 of a point)
		      //  r.setHeight((short) 0x249);
		    //}

		    //r.setRowNum(( short ) rownum);
		    // create 10 cells (0-9) (the += 2 becomes apparent later
		    for (short cellnum = (short) 0; cellnum < cantCeldas; cellnum ++)
		    {
		        // create a numeric cell
		        c = r.createCell(cellnum);
		        // do some goofy math to demonstrate decimals
		        c.setCellValue(rownum * 10000 + cellnum
		                + (((double) rownum / 1000)
		                + ((double) cellnum / 10000)));
		        c.setCellValue((((OperacionPolizaTO)lstAManipular.get(rownum)).getSaldo().doubleValue()));

		        String cellValue;

		        // create a string cell (see why += 2 in the
		        c = r.createCell((short) (cellnum + 1));
		        
		        // on every other row
		       if ((rownum % 2) == 0)
		        {
		            // set this cell to the first cell style we defined
		            c.setCellStyle(cs);
		            // set the cell's string value to "Test"
		            c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
		            c.setCellValue( "Boca" );
		        }
		        else
		        {
		            c.setCellStyle(cs2);
		            // set the cell's string value to "\u0422\u0435\u0441\u0442"
		            c.setEncoding( HSSFCell.ENCODING_UTF_16 );
		            c.setCellValue( "\u0422\u0435\u0441\u0442" );
		        }


		        // make this column a bit wider
		        s.setColumnWidth((short) (cellnum + 1), (short) ((50 * 8) / ((double) 1 / 20)));
		    }
		}

//		draw a thick black border on the row at the bottom using BLANKS
//		 advance 2 rows
		rownum++;
		rownum++;

		r = s.createRow(rownum);

//		 define the third style to be the default
//		 except with a thick black border at the bottom
		cs3.setBorderBottom(cs3.BORDER_THICK);

//		create 50 cells
		for (short cellnum = (short) 0; cellnum < 50; cellnum++)
		{
		    //create a blank type cell (no value)
		    c = r.createCell(cellnum);
		    // set it to the thick black border style
		    c.setCellStyle(cs3);
		}

//		end draw thick black border


//		 demonstrate adding/naming and deleting a sheet
//		 create a sheet, set its title then delete it
		s = wb.createSheet();
		wb.setSheetName(1, "DeletedSheet");
		wb.removeSheetAt(1);
//		end deleted sheet

//		 write the workbook to the output stream
//		 close our file (don't blow out our file handles
		wb.write(out);
		out.close();
	}
	
	
	
*/	
	
	public void generaArchivoMovimientoStock(List lsrPersonasAP,String archivo, Boolean escribirTitulos){
			try{
			FileUtil fileUtil= new FileUtil();
	   		Properties props= fileUtil.getPropertiesFile();	
	   		Messages mensajeria = new Messages();
	   		String nombArch = props.getProperty("saveArchivosExcel") +archivo;	
	   		
	   		File f = new File(nombArch);	   		
	        FileWriter writer=null;
	        
	        try {
				writer = new FileWriter(f,true);						
			} catch (IOException e) {						
				e.printStackTrace();
			}	
			
	        BufferedWriter salida = new BufferedWriter(writer);	  
	        SimpleDateFormat sdfArchivo = new SimpleDateFormat("yyyyMMdd");       
	        String fila="";             
	        
	   		  if(lsrPersonasAP!=null){
		   		Iterator itObj = lsrPersonasAP.iterator();
		   		MovimientoStockTO movimientoStockTO = new MovimientoStockTO();
		   		DataUtil datautil= new DataUtil();
		   		
		   		if(escribirTitulos){
		   			fila=	 mensajeria.getMessage().getString("ccss_label").toUpperCase()+";"+			       
					         mensajeria.getMessage().getString("producto_label").toUpperCase()+";"+	
					         mensajeria.getMessage().getString("litros_label").toUpperCase()+";"+	
						     mensajeria.getMessage().getString("saldo_litros_label").toUpperCase()+";"+	
						     mensajeria.getMessage().getString("fecha_de_movimiento_label").toUpperCase()+";"+		
						     mensajeria.getMessage().getString("tipo_de_movimiento_label").toUpperCase()+";"+
						     mensajeria.getMessage().getString("sucursal_label").toUpperCase()+";"+	
						     mensajeria.getMessage().getString("nro_comprobante_label").toUpperCase()+";"+			
						     mensajeria.getMessage().getString("tipo_comprobante_largo_label").toUpperCase()+";"+	
						     mensajeria.getMessage().getString("letra_comprobante_label").toUpperCase();	
		   			
				   			try {
								salida.write(fila);
								salida.newLine();
							} catch (IOException e) {							
								e.printStackTrace();
							}		
		   		}
		   		
		   		DecimalFormat myFormatter = new DecimalFormat("###,##0.00");
		   		
		   		while(itObj.hasNext()){
		   			movimientoStockTO = (MovimientoStockTO)itObj.next();
		   			
		   			fila= 	movimientoStockTO.getDescrCCSS() +";"
		   					+ movimientoStockTO.getDescrProducto() +";"
		   					+myFormatter.format(movimientoStockTO.getLitros()) +";"
		   					+myFormatter.format(movimientoStockTO.getSaldoLitros()) +";"
		   					+movimientoStockTO.getFechaMovimiento() +";"
		   					+movimientoStockTO.getDescrMovimiento() +";"	   					
		   					+  (movimientoStockTO.getSucursal()==null?"": movimientoStockTO.getSucursal()) +";"
		   					+movimientoStockTO.getNroComprobante() +";"
		   					+movimientoStockTO.getDescrComprobante() +";"
		   					+movimientoStockTO.getLetraC();			   										   					
		   					
		   			try {
						salida.write(fila);
						salida.newLine();
					} catch (IOException e) {							
						e.printStackTrace();
					}		   			
		   		}
		   		
		   		try {
					//GRABA INTERFAZ
					salida.close();
				} catch (IOException e) {						
					e.printStackTrace();
				}
	   		 
	   		  }  
	   		  
			} catch (IOException e) {						
			e.printStackTrace();
		}			
	}
	
	
	public String generarExcelReporteImpuestosLeyCb(List lstAManipular,String fecha) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{					
			nombArch = mensajeria.getMessage().getString("info_ley_vial_cba")+"_"+fecha+".xls";			
			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
			HSSFCellStyle estiloMonto2 = wb.createCellStyle();
			String etiqueta="";				
			etiqueta=mensajeria.getMessage().getString("info_ley_vial_cba");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			HSSFFont fuenteTitulosMonto2 =  wb.createFont(); 
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			 fuenteTitulosMonto2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
		    //Ancho de columnas	       
	       	int ind=0;       	
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));			
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));			
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 18)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 20)));
			
			
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 15)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 15)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 20)));
			ind=ind+1;
			s.setColumnWidth((short)ind, (short)((50 * 10) / ((double) 1 / 12)));
	       	     
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);	        
	         
	         ind=0;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("fecha_label").toUpperCase());
	         ind=ind+1;
	         c = r.createCell((short)ind);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue(mensajeria.getMessage().getString("hora_label").toUpperCase());   
	         ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("ccss_label").toUpperCase());
		     
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
		     
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("sucursal_label").toUpperCase());		
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("nro_remito_label").toUpperCase());		
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("producto_label").toUpperCase());		
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cantidad_label").toUpperCase());		
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("total_pesos_label").toUpperCase());
		     
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("total_imp_ley_cba").toUpperCase());
		     
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("patente_label").toUpperCase());
		     
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("chofer_label").toUpperCase());
		     
		     
		     ind=ind+1;        
	         c = r.createCell((short)ind);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("estado_combustible_label").toUpperCase());
		     
		     
		     
		     
	         
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 estiloMonto2.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto2.setBorderBottom(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setBorderRight(estiloMonto2.BORDER_THIN);
	         estiloMonto2.setFont(fuenteTitulosMonto2);
	         
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			RemitoTO remitoTO = new RemitoTO();
			int index =0;
			ind=0;		
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 remitoTO = (RemitoTO)lstAManipular.get(index);
				 index++;				
				 

		         c = r.createCell((short)ind);
		         c.setCellStyle(estilofilas);
		         if(remitoTO.getFecha()==null )
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getFecha());	         
			     
		         ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getHora()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getHora());
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estiloMonto);	        
			     if(remitoTO.getCcss()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getCcss());
		        
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getCliDescripcion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getCliDescripcion());
			     
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getNroSucursal()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getNroSucursal());
			     
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getNroRemito()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getNroRemito());
			     
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getDescProducto()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getDescProducto());
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getLitros()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getLitros().doubleValue());
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getMontoTotal()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getMontoTotal().doubleValue());
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getTotalLeyCba()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getTotalLeyCba().doubleValue());
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getPatente()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getPatente());
			     
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getPatente()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getApellidoChofer()+", "+ remitoTO.getNombreChofer());
			     
			     ind=ind+1;
			     c = r.createCell((short)ind);	
			     c.setCellStyle(estilofilas);	        
			     if(remitoTO.getFacturadoString()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(remitoTO.getFacturadoString());
			     
			     
			     
		         ind=0;
		         
		         
		       }
		
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	    //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
	
	public String generarExcelReporteCargaCupos(List lstAManipular,String fecha,int tipoUsu) throws IOException,FileNotFoundException,Exception{
		String nombArch="";
		short rownum;
		FileUtil fileUtil= new FileUtil();
		Messages mensajeria = new Messages();
		SimpleDateFormat sdf= new SimpleDateFormat("yyMMdd");
		Properties props= fileUtil.getPropertiesFile();		
		try{		
			
			
			nombArch = mensajeria.getMessage().getString("archivo_cupos")+"_"+fecha+".xls";			
			//System.out.println("url -->"+props.getProperty("saveArchivosExcel")+nombArch);	
			FileOutputStream out = new FileOutputStream(props.getProperty("saveArchivosExcel")+nombArch);		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet s = wb.createSheet();		
			HSSFRow r = null;
			HSSFCell c = null;	
			
			sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			HSSFCellStyle estiloEtiquetas = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasTitulo = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado = wb.createCellStyle();
			HSSFCellStyle estiloEtiquetasEncabezado2 = wb.createCellStyle();			
			HSSFCellStyle estiloTitulo = wb.createCellStyle();
			HSSFCellStyle estiloTitulo2 = wb.createCellStyle();
			HSSFCellStyle estilofilas = wb.createCellStyle();
			HSSFCellStyle estiloMonto = wb.createCellStyle();
				
			String etiqueta="";
			etiqueta=mensajeria.getMessage().getString("archivo_cupos");		
			
			wb.setSheetName(0, etiqueta, 
			                HSSFWorkbook.ENCODING_UTF_16 );
			
			HSSFFont fuenteTitulos =  wb.createFont();  
	        HSSFFont fuenteEtiquetas =  wb.createFont();		
			HSSFFont fuenteEtiquetasTitulo =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado =  wb.createFont();
			HSSFFont fuenteEtiquetasEncabezado2 =  wb.createFont();	
			
			//para formate de datos
			HSSFDataFormat df = wb.createDataFormat();
			
			//estilo encabezado 
			fuenteEtiquetasEncabezado.setColor((short)0x12);
			fuenteEtiquetasEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
			fuenteEtiquetasEncabezado.setFontHeightInPoints((short) 12);
			estiloEtiquetasEncabezado.setFont(fuenteEtiquetasEncabezado);        
			
		    //Ancho de columnas	 
			int i=0;
	       	s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 22)));
	       	i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 10)));
	       	i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 10)));
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 13)));			
			 
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 10)));
			
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 13)));
			
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 13)));
			 
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 13)));
			
			i=i+1;
			s.setColumnWidth((short)i, (short)((50 * 8) / ((double) 1 / 10)));
			
		         
	         //titulos               
	         fuenteTitulos.setColor((short)0x9);
	         fuenteTitulos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        
	         estiloTitulo.setFont(fuenteTitulos);
	         estiloTitulo.setFillForegroundColor((short) 0x36);	
	         estiloTitulo.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
	         estiloTitulo2.setFont(fuenteTitulos);        
		     estiloTitulo2.setFillForegroundColor((short) 0x36);	
		     estiloTitulo2.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
	         
			 r = s.createRow(0);        
	         estiloTitulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
	         
	         i=0; 
	         c = r.createCell((short)i);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("cliente_label").toUpperCase());
		     
		     i=i+1; 
		     c = r.createCell((short)i);
		     c.setCellStyle(estiloTitulo);
		     c.setCellValue(mensajeria.getMessage().getString("patente_label").toUpperCase());
		     
		     i=i+1;
	         c = r.createCell((short)i);
	         c.setCellStyle(estiloTitulo);
	         c.setCellValue("Agrupado");
	         
	       
	        	 i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("descripcion_label").toUpperCase()); 
		         
		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue( "Ilimitado" .toUpperCase()); 
		         
		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue("Litros Por Carga".toUpperCase()); 
	         
		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue("Litros Por Día".toUpperCase()); 
		         
		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue("Litros Por Mes".toUpperCase()); 
		         
		         
		         i=i+1;
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloTitulo);
		         c.setCellValue(mensajeria.getMessage().getString("activo_label").toUpperCase()); 
	           
	          
	  		 
	         estiloMonto.setDataFormat(df.getFormat("#,##0.00"));	
	         estiloMonto.setBorderBottom(estiloMonto.BORDER_THIN);
	         estiloMonto.setBorderRight(estiloMonto.BORDER_THIN);
	         
	         estilofilas.setBorderBottom(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderRight(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderLeft(estilofilas.BORDER_THIN);
	 		 estilofilas.setBorderTop(estilofilas.BORDER_THIN);	 		 
	 		 
	 		 HSSFFont fuenteEstado =  wb.createFont();
		     fuenteEstado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         
			 //fila en la empieza la lista de datos 
			Integer cantFilas = 1+ lstAManipular.size();			
			CuposTO cuposTO = new CuposTO();
			int index =0;
					i=0;	
			for (rownum = (short) 1; rownum < cantFilas; rownum++)
			{
				 r = s.createRow(rownum);
				 cuposTO = (CuposTO)lstAManipular.get(index);
				 index++;	
				 				 
				
//		        	cliente descripcion
			         c = r.createCell((short)i);
			         c.setCellStyle(estilofilas);
			         if(cuposTO.getCliDescripcion()==null )
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(cuposTO.getCliDescripcion());
			         
			         i=i+1; 
			         c = r.createCell((short)i);	
				     c.setCellStyle(estilofilas);	        
				     if(cuposTO.getPatente()==null)
			        	 c.setCellValue("");
			         else
			        	 c.setCellValue(cuposTO.getPatente());
			         
				     i=i+1; 
				 c  = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     if(cuposTO.getFamiliaGrupoArticuloDesc()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getFamiliaGrupoArticuloDesc());
		         
			     i=i+1; 
			     c = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     if(cuposTO.getDescripcion()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getDescripcion());
		         
			     i=i+1; 
			    
			     c = r.createCell((short)i);	
			     c.setCellStyle(estilofilas);	        
			     if(cuposTO.getIlimitadoStr()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getIlimitadoStr()); 
		         
		         
		         i=i+1;		        
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloMonto); 
		         if(cuposTO.getLtrCarga()==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getLtrCarga().doubleValue()); 
		         
		         i=i+1;		        
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloMonto); 
		         if(cuposTO.getLtrDia() ==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getLtrDia().doubleValue()); 
		         
		         i=i+1;		          
		         c = r.createCell((short)i);
		         c.setCellStyle(estiloMonto); 
		         if(cuposTO.getLtrMes() ==null)
		        	 c.setCellValue("");
		         else
		        	 c.setCellValue(cuposTO.getLtrMes().doubleValue());
		         
		         i=i+1;			        
//		         activo
			     c = r.createCell((short)i);
			     c.setCellStyle(estilofilas);
			     if(cuposTO.getActivo()==null )
			       	 c.setCellValue("");
			     else
			       	 c.setCellValue(cuposTO.getActivo()? mensajeria.getMessage().getString("si_label") : mensajeria.getMessage().getString("no_label"));  
			     
			     
		         
		         i=0;
		       }
		
	         
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);
			
			wb.write(out);
			out.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			throw new FileNotFoundException();
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException();		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();		
		}
	   // //System.out.println("url -->"+getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch);
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+nombArch;
	}
	
}

