



	/*Validacion de campo obligatorios*/
	function validarCamposRep(){	
	//solo valida para consulta de polizas
	var strMensaje = "Debe ingresar Fecha Desde y Fecha Hasta.";
	var aux=0;
	var fechaDesdeEmisionPoliza = document.getElementById('frmConsulta:fechaEmisionDesde');
	var fechaHastaEmisionPoliza = document.getElementById('frmConsulta:fechaEmisionHasta');

	if(fechaDesdeEmisionPoliza.value != ""){				
			if(fechaHastaEmisionPoliza.value!=""){
				if(getCantidadDeDias(fechaDesdeEmisionPoliza, fechaHastaEmisionPoliza)){
						aux=1;	
						strMensaje = "El rango de fechas no puede ser superior a 1 mes.";
						//document.getElementById('frmConsulta:fechaEmisionDesde').value="";	
						//document.getElementById('frmConsulta:fechaEmisionHasta').value="";		
				}	
			}else{
				  aux=1;
				  strMensaje = "Debe ingresar Fecha Hasta.";					
			}		
	}else{
		   if(fechaHastaEmisionPoliza.value!=""){	
			     aux=1;			
				 strMensaje = "Debe ingresar Fecha Desde.";					
			}else{
			aux=1;		
			}	
	}			
				
	if(aux==1){
		alert(strMensaje);					
		return false;
	}else{
	    //todo esta bien			   
		return true;			
	}	
					
}	

//********************************************************************************	
	/*Validacion de campo obligatorios*/
	function validarCamposRepPreliquidacion(){	
	//solo valida para consulta de polizas
	var strMensaje = "Debe ingresar Per\xEDodo Hasta de consulta.";
	var aux=0;
	var mesFlt = document.getElementById('frmConsulta:periodoMesFlt');
	var anioFlt = document.getElementById('frmConsulta:periodoAnioFlt');

	if(anioFlt.value == "" || mesFlt.value ==""){			
		aux=1;				  
	}		
				
	if(aux==1){
		alert(strMensaje);					
		return false;
	}else{
	    //todo esta bien			   
		return true;			
	}	
					
}	


//********************************************************************************

	function getCantidadDeDias(objfechaDesde,objfechaHasta){   
	   //Obtiene dia, mes y año
	   var fecha1 = new fechaFunBusPol(objfechaDesde.value)   
	   var fecha2 = new fechaFunBusPol(objfechaHasta.value)
		
	   //Obtiene objetos Date
	   var miFecha1 = new Date( fecha1.anio, fecha1.mes, fecha1.dia )
	   var miFecha2 = new Date( fecha2.anio, fecha2.mes, fecha2.dia )
	   	   
	  var diferencia = miFecha1.getTime() - miFecha2.getTime()
	   var dias = Math.floor(diferencia / (1000 * 60 * 60 * 24))  
	   var segundos = Math.floor(diferencia / 1000)	  
	   
	   if(Math.abs(dias) > 31){
	     return true;
	   }
	   else
	   {
	     return false;  
	   }
		
	    
	   
	}		
	
	function fechaFunBusPol(cadena) {	
	   //Separador para la introduccion de las fechas
	   var separador = "/"	
	   //Separa por dia, mes y año
	   if ( cadena.indexOf( separador ) != -1 ) {
	        var posi1 = 0
	        var posi2 = cadena.indexOf( separador, posi1 + 1 )
	        var posi3 = cadena.indexOf( separador, posi2 + 1 )
	        this.dia = cadena.substring( posi1, posi2 )
	        this.mes = cadena.substring( posi2 + 1, posi3 )
	        this.anio = cadena.substring( posi3 + 1, cadena.length )
	   } else {
	        this.dia = 0
	        this.mes = 0
	        this.anio = 0   
	   }
	}	