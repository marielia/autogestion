package com.refinor.extranet.util;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import com.refinor.extranet.data.dao.MpedidosDAO;
import com.refinor.extranet.to.MesTO;

public class DataUtil {	
	private Messages mensajeria;
	
	public DataUtil(){
		try {
			mensajeria =  new Messages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String obtenerCampoStringFecha(Date fecha){
		String campoStr ="";
		if(fecha!=null){	
			 SimpleDateFormat sdfArchivo = new SimpleDateFormat("yyyyMMdd");      
			 campoStr= sdfArchivo.format(fecha).trim().toString();
		}
		return campoStr;
	}
	
	public String obtenerCampoString(Integer campo){
		String campoStr ="";
		if(campo!=null &&  !campo.equals(new Integer(-1))){			
			campoStr= campo.toString();
		}
		return campoStr;
	}
	
	public String obtenerCampo(String campo){
		String campoStr ="";
		if(campo!=null){
			campoStr= campo;
		}
		return campoStr;
	}
	
	public String obtenerDecimalFormateado(BigDecimal numeroDecimal){
		String sueldoAux ="";
		if(numeroDecimal!=null){
		  sueldoAux = numeroDecimal.toString();
			System.out.println(sueldoAux);						
			if(sueldoAux.contains(",")){
				sueldoAux=sueldoAux.replace(",", ",");							
			}else if(sueldoAux.contains(".")){
				sueldoAux=sueldoAux.replace(".", ",");							
			}
		}
		return sueldoAux;
	}
	
	/**
	 * Metodo: rellenarConCeros
	 * Funcion: Rellenar con ceros la cadena recibida, agregando tanto ceros para completar la cantidad de posiciones.
	 * @param cantPos Cantidad de posiciones que ocupa el campo que quiero rellenar con 0.
	 * @param cadena  Cadena a completar con ceros
	 * @return String Cadena completada con ceros
	 */
	public String rellenarConCeros(int cantPos, String cadena){
		//tama�o de la cadena recibida
		int cant = cadena.length();
		
		if(cant < cantPos){
			int a = cantPos - cant;
			for(int i=0;i<a;i++){
				cadena = "0"+cadena;
			}
		}
		
		return cadena;
	}
	
		
		/**
		 * Metodo: rellenarConBlancosAIzquierda - para numeros
		 * Funcion: Rellenar con blancos a izquierda la cadena recibida, agregando tantos blancos para completar la cantidad de posiciones.
		 * @param cantPos Cantidad de posiciones que ocupa el campo que quiero rellenar con blanco.
		 * @param cadena  Cadena a completar con blanco
		 * @param izquierda Si es true rellena con blancos a izq (ej numeros), Si es false rellena con blancos a derecha (ej string),
		 * @return String Cadena completada con blanco
		 */
		public String rellenarConBlancos(int cantPos, String cadena,Boolean izquierda){
			//tama�o de la cadena recibida
			if(cadena==null)
				cadena="";
			
			int cant = cadena.length();
			
			if(cant < cantPos){
				int a = cantPos - cant;
				for(int i=0;i<a;i++){
					if(izquierda)
						cadena = " "+cadena;
					else
						cadena = cadena+" ";
				}
			}
			
			return cadena;
		}
		
		public String rellenarConGuiones(int cantPos, String cadena,Boolean izquierda){
			//tama�o de la cadena recibida
			int cant = cadena.length();
			
			if(cant < cantPos){
				int a = cantPos - cant;
				for(int i=0;i<a;i++){
					if(izquierda)
						cadena = "-"+cadena;
					else
						cadena = cadena+"-";
				}
			}
			
			return cadena;
		}
		
	/*	public String obtenerVigenciaCadena(int vigencia){
			if(vigencia==1){
				return Const.PERIODO_MENSUAL;			
			}else if(vigencia==2){
				return Const.PERIODO_BIMESTRAL;
			}else if(vigencia==3){
				return Const.PERIODO_TRIMESTRAL;
			}else if(vigencia==4){
				return Const.PERIODO_CUATRIMESTRAL;
			}else if(vigencia==5){
				return "CINCO MESES";
			}else if(vigencia==6){
				return Const.PERIODO_SEMESTRAL;
			}else if(vigencia==7){
				return "SIETE MESES";
			}else if(vigencia==8){
				return Const.PERIODO_OCTOMESTRAL;
			}else if(vigencia==9){
				return "NUEVE MESES";
			}else if(vigencia==10){
				return "DIEZ MESES";
			}else if(vigencia==11){
				return "ONCE MESES";
			}else if(vigencia==12){
				return Const.PERIODO_ANUAL;
			}else{
				return "";
			}
		}*/		
		
		/**
		 * 
		 * @param mes
		 * @return mes en letras
		 */
		public String pasarAMes(String mes){
			if(mes.equals("01")){
				return "Enero";
			}else if(mes.equals("02")){
				return "Febrero";			
			}else if(mes.equals("03")){
				return "Marzo";			
			}else if(mes.equals("04")){
				return "Abril";			
			}else if(mes.equals("05")){
				return "Mayo";			
			}else if(mes.equals("06")){
				return "Junio";			
			}else if(mes.equals("07")){
				return "Julio";			
			}else if(mes.equals("08")){
				return "Agosto";			
			}else if(mes.equals("09")){
				return "Septiembre";			
			}else if(mes.equals("10")){
				return "Octubre";			
			}else if(mes.equals("11")){
				return "Noviembre";			
			}else if(mes.equals("12")){
				return "Diciembre";			
			}else if(mes.equals("--")){
				return "--";			
			}
			else{
				return "";
			}
	}
		
		public BigDecimal getObtenerCantidadDias(Date fechaInicial,Date fechaFinal){
		 BigDecimal cantidad= new BigDecimal(0);		
		 try {				    
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");			
				String fechaInicioString = df.format(fechaInicial);	   
				fechaInicial = df.parse(fechaInicioString);			    
			    String fechaFinalString = df.format(fechaFinal);
			    fechaFinal = df.parse(fechaFinalString);	
			    df = new SimpleDateFormat("MM");
			    
			    long fechaInicialMs = fechaInicial.getTime();
			    long fechaFinalMs = fechaFinal.getTime();		   
			    long diferencia = fechaInicialMs - fechaFinalMs;		   		    
			    long ldias = diferencia / (1000 * 60 * 60 * 24);	    
			    
			    cantidad = new BigDecimal(ldias);
			    System.out.println("cantdidad--> "+cantidad);
			    
		 } catch (Exception e) {			
				e.printStackTrace();
		}		 	
		    
		 	return cantidad.abs();
		}
		
		
		 public List<MesTO> cargarComboMeses(){	
			 List lstMeses =  new ArrayList<MesTO>();
			 try{			
			 lstMeses = new ArrayList<SelectItem>();
			 lstMeses.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
			 lstMeses.add(new SelectItem(new Integer(1),pasarAMes("01")));
			 lstMeses.add(new SelectItem(new Integer(2),pasarAMes("02")));
			 lstMeses.add(new SelectItem(new Integer(3),pasarAMes("03")));
			 lstMeses.add(new SelectItem(new Integer(4),pasarAMes("04")));
			 lstMeses.add(new SelectItem(new Integer(5),pasarAMes("05")));
			 lstMeses.add(new SelectItem(new Integer(6),pasarAMes("06")));
			 lstMeses.add(new SelectItem(new Integer(7),pasarAMes("07")));
			 lstMeses.add(new SelectItem(new Integer(8),pasarAMes("08")));
			 lstMeses.add(new SelectItem(new Integer(9),pasarAMes("09")));
			 lstMeses.add(new SelectItem(new Integer(10),pasarAMes("10")));
			 lstMeses.add(new SelectItem(new Integer(11),pasarAMes("11")));
			 lstMeses.add(new SelectItem(new Integer(12),pasarAMes("12")));			
			 }catch(Exception ex){
				 ex.printStackTrace();
			 }
			 
			 return lstMeses;
			
		}
		
}