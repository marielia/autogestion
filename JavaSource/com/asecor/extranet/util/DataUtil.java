package com.asecor.extranet.util;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import com.asecor.extranet.to.DateTO; 

public class DataUtil {	
	private Messages mensajeria;
	public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
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
		
		public DateTO getFechaActual()
		{
				DateTO dateTO = new DateTO();
				java.util.Date FechaActual= new java.util.Date(); 
				Calendar fecha = Calendar.getInstance(); 
				dateTO.setAnio(  FechaActual.getYear() );
				dateTO.setMes(fecha.get(Calendar.MONTH));
				dateTO.setDia( fecha.get(Calendar.DAY_OF_MONTH));
				dateTO.setHora( fecha.get(Calendar.HOUR_OF_DAY));
				dateTO.setMinuto( fecha.get(Calendar.MINUTE));
				dateTO.setSegundo( fecha.get(Calendar.SECOND));
				return dateTO;
          
		}
		
		
		
}