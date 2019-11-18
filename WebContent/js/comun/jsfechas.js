// <!-- Modulo de revision de Fechas - en JavaScript -->

  function revisaFechaLim(Fecha, FechaMin, FechaMax) {
	var numdia = 0;
	var nummes = 0;
	var numano = 0;
	var mindia = 0;
	var minmes = 0;
	var minano = 0;
	var maxdia = 0;
	var maxmes = 0;
	var maxano = 0;
	var res = true;
	if (Fecha.length!=10)  return (false);

	// Separadores
	var sep1 = Fecha.substr(2,1);
	var sep2 = Fecha.substr(5,1);
	if( (sep1 !='/') || (sep2 !='/') ) return (false);

	numdia = Fecha.substr(0,2);
	if ( isNaN(numdia)) return (false);	
	nummes = Fecha.substr(3,2);
	if ( isNaN(nummes)) return (false);
	numano = Fecha.substr(6,4);
	if ( isNaN(numano)) return (false);
	mindia = FechaMin.substr(0,2);
	if ( isNaN(mindia)) return (false);
	minmes = FechaMin.substr(3,2);
	if ( isNaN(minmes)) return (false);
	minano = FechaMin.substr(6,4);
	if ( isNaN(minano)) return (false);
	maxdia = FechaMax.substr(0,2);
	if ( isNaN(maxdia)) return (false);
	maxmes = FechaMax.substr(3,2);
	if ( isNaN(maxmes)) return (false);
	maxano = FechaMax.substr(6,4);
	if ( isNaN(maxano)) return (false);

	// Revision basica inicial	
	if( (numdia<1) || (numdia>31) ) return (false);
	if( (nummes<1) || (nummes>12) ) return (false);

	// Revision especifica
	if (nummes == 1) { if (numdia>31) return (false); }
	if (nummes == 2) { if (numdia>29) return (false); }
	if (nummes == 3) { if (numdia>31) return (false); }
	if (nummes == 4) { if (numdia>30) return (false); }
	if (nummes == 5) { if (numdia>31) return (false); }
	if (nummes == 6) { if (numdia>30) return (false); }
	if (nummes == 7) { if (numdia>31) return (false); }
	if (nummes == 8) { if (numdia>31) return (false); }
	if (nummes == 9) { if (numdia>30) return (false); }
	if (nummes == 10) { if (numdia>31) return (false); }
	if (nummes == 11) { if (numdia>30) return (false); }
	if (nummes == 12) { if (numdia>31) return (false); }

	// Annos bisiestos
	if (nummes == 2) { if ((numdia==29) && (numano%4)!=0)  return (false); }

	if( minano>numano ) return (false);
	if( numano == minano ) {
		if( minmes>nummes ) return (false);
		if( (nummes==minmes) && (mindia>numdia) ) return (false);
	}
	if( numano>maxano ) return (false);
	if( numano == maxano ) {
		if( nummes>maxmes ) return (false);
		if( (nummes==maxmes) && (numdia>maxdia) ) return (false);
	} 

	return (true);		
  }


  function revisaFecha(Fecha, AnoMin) {
	var numdia = 0;
	var nummes = 0;
	var numano = 0;

	if (Fecha.length!=10)  return (false);

	numdia = Fecha.substr(0,2);
	
	if ( isNaN(numdia)) return (false);
	nummes = Fecha.substr(3,2);
	
	if ( isNaN(nummes)) return (false);
	numano = Fecha.substr(6,4);
	
	if ( isNaN(numano)) return (false);


	var sep1 = Fecha.substr(2,1);
	var sep2 = Fecha.substr(5,1);

	// Revision basica inicial	
	if( (numdia<1) || (numdia>31) ) return (false);
	if( (nummes<1) || (nummes>12) ) return (false);
	if( (numano<AnoMin) ) return (false);

	// Revision especifica
	if (nummes == 1) { if (numdia>31) return (false); }
	if (nummes == 2) { if (numdia>29) return (false); }
	if (nummes == 3) { if (numdia>31) return (false); }
	if (nummes == 4) { if (numdia>30) return (false); }
	if (nummes == 5) { if (numdia>31) return (false); }
	if (nummes == 6) { if (numdia>30) return (false); }
	if (nummes == 7) { if (numdia>31) return (false); }
	if (nummes == 8) { if (numdia>31) return (false); }
	if (nummes == 9) { if (numdia>30) return (false); }
	if (nummes == 10) { if (numdia>31) return (false); }
	if (nummes == 11) { if (numdia>30) return (false); }
	if (nummes == 12) { if (numdia>31) return (false); }

	// Annos bisiestos
	if (nummes == 2) { if ((numdia==29) && (numano%4)!=0)  return (false); }

	// Separadores
	if( (sep1 !='/') || (sep2 !='/') ) return (false);

	return (true);		
  }


function revisaFechaMotivo(Fecha, AnoMin) {
	var numdia = 0;
	var nummes = 0;
	var numano = 0;

	if (Fecha.length!=10)  return (2);

	numdia = Fecha.substr(0,2);	
	if ( isNaN(numdia)) return (1);
	
	nummes = Fecha.substr(3,2);	
	if ( isNaN(nummes)) return (1);
	
	numano = Fecha.substr(6,4);	
	if ( isNaN(numano)) return (1);


	var sep1 = Fecha.substr(2,1);
	var sep2 = Fecha.substr(5,1);

	// Revision basica inicial	
	if( (numdia<1) || (numdia>31) ) return (1);
	if( (nummes<1) || (nummes>12) ) return (1);
	if( (numano<AnoMin) ) return (1);

	// Revision especifica
	if (nummes == 1) { if (numdia>31) return (1); }
	if (nummes == 2) { if (numdia>29) return (1); }
	if (nummes == 3) { if (numdia>31) return (1); }
	if (nummes == 4) { if (numdia>30) return (1); }
	if (nummes == 5) { if (numdia>31) return (1); }
	if (nummes == 6) { if (numdia>30) return (1); }
	if (nummes == 7) { if (numdia>31) return (1); }
	if (nummes == 8) { if (numdia>31) return (1); }
	if (nummes == 9) { if (numdia>30) return (1); }
	if (nummes == 10) { if (numdia>31) return (1); }
	if (nummes == 11) { if (numdia>30) return (1); }
	if (nummes == 12) { if (numdia>31) return (1); }

	// Annos bisiestos
	if (nummes == 2) { if ((numdia==29) && (numano%4)!=0)  return (1); }

	// Separadores
	if( (sep1 !='/') || (sep2 !='/') ) return (2);
	
	return (0);		
  }


function revisaHora(Hora) 
{
	var_hora = 0;
	var_minuto = 0;
	var_separador =" ";
	var_strHora =" ";
	var_strHora =" ";
	var_strMin =" ";

	if ((Hora.length!=5) && (Hora.length!=0)) return (false);

	var_hora   = Hora.substr(0,2);
	var_minuto = Hora.substr(3,2);

	var_hora   = Hora.substr(0,2);
	if ( isNaN(var_hora)) return (false);	

	var_minuto = Hora.substr(3,2);
	if ( isNaN(var_minuto)) return (false);

	var_separador = Hora.substr(2,1);
	if ((var_separador != ':') && (var_separador != '')) return (false);

	var_strHora = Hora.substr(0,2); 
	if ((var_strHora != '00') && (var_strHora != '13') && 
	(var_strHora != '01') && (var_strHora != '14') && 
	(var_strHora != '02') && (var_strHora != '15') && 
	(var_strHora != '03') && (var_strHora != '16') && 
	(var_strHora != '04') && (var_strHora != '17') && 
	(var_strHora != '05') && (var_strHora != '18') && 
	(var_strHora != '06') && (var_strHora != '19') && 
	(var_strHora != '07') && (var_strHora != '20') && 
	(var_strHora != '08') && (var_strHora != '21') && 
	(var_strHora != '09') && (var_strHora != '22') && 
	(var_strHora != '10') && (var_strHora != '23') && 
	(var_strHora != '11') &&  
	(var_strHora != '12') && (var_strHora != ''))  return (false);

	var_strMin = Hora.substr(3,2); 
	if ((var_strMin != '00') && (var_strMin != '02') && (var_strMin != '01') &&
	(var_strMin != '03') && (var_strMin != '04') && 
	(var_strMin != '05') && (var_strMin != '06') && 
	(var_strMin != '07') && (var_strMin != '08') && 
	(var_strMin != '09') && (var_strMin != '10') && 
	(var_strMin != '11') && (var_strMin != '12') && 
	(var_strMin != '13') && (var_strMin != '14') && 
	(var_strMin != '15') && (var_strMin != '16') && 
	(var_strMin != '17') && (var_strMin != '18') && 
	(var_strMin != '19') && (var_strMin != '20') && 
	(var_strMin != '21') && (var_strMin != '22') && 
	(var_strMin != '23') && (var_strMin != '24') && 
	(var_strMin != '25') && (var_strMin != '26') && 
	(var_strMin != '27') && (var_strMin != '28') && 
	(var_strMin != '29') && (var_strMin != '30') && 
	(var_strMin != '31') && (var_strMin != '32') && 
	(var_strMin != '33') && (var_strMin != '34') && 
	(var_strMin != '35') && (var_strMin != '36') && 
	(var_strMin != '37') && (var_strMin != '38') && 
	(var_strMin != '39') && (var_strMin != '40') && 
	(var_strMin != '41') && (var_strMin != '42') && 
	(var_strMin != '43') && (var_strMin != '44') && 
	(var_strMin != '45') && (var_strMin != '46') && 
	(var_strMin != '47') && (var_strMin != '48') && 
	(var_strMin != '49') && (var_strMin != '50') && 
	(var_strMin != '51') && (var_strMin != '52') && 
	(var_strMin != '53') && (var_strMin != '54') && 
	(var_strMin != '55') && (var_strMin != '56') && 
	(var_strMin != '57') && (var_strMin != '58') && 
	(var_strMin != '59') &&  
	(var_strMin != ''))  return (false);			 

	return (true);		
}

///////////////////////////////////////////////////////////////////
function insertaSeparadores(control) 
{
	//se obtiene id numerico de tecla presionada
        ev = window.event;        	
	var Fecha = control.value;	
	
	//alert("ev.keyCode = " + ev.keyCode);
	//alert(Fecha.length);      

	//////key permitidas no numericas, sin efecto
	
	// 8 = back space
        // 16 = shift	
	// 35 = inicio
	// 36 = fin
	// 37 = flecha derecha
	// 38 = flecha arriba
	// 39 = flecha izquierda
	// 40 = flecha abajo	
	// 46 = suprimir
		
	if ( (ev.keyCode >= 35 && ev.keyCode <= 40) || (ev.keyCode == 16) || (ev.keyCode == 8) || (ev.keyCode == 46)){
	   return;
	}

      //key de zona de digitacion numerica, para ejecucion de funcion
      // numeros y slash
      if ( (ev.keyCode >= 96 && ev.keyCode <= 105) || (ev.keyCode == 16)){
	   
        //cadena de fecha entre 7 y 10 digitos, sin manejo de separadores
        //por asumir que se ingresa el ano.
	if (Fecha.length >= 7 && Fecha.length < 10){
	   return;
	}
	
	//Llama funcion borra Separadores
        Fecha = borraSeparadores(Fecha);
        
        //alert("bandera 1 entra a separadores = " + Fecha);
        //Llama funcion agrega separadores
        Fecha = agregaSeparadores(Fecha);
        	
	
     }else{
        //alert("keys de otra zona del teclado");
        
        //borrar caracteres no numericos
     	var flg_recorrer = 0;
     	     	
     	for (var i=0; i<Fecha.length; i++) {
	 var caracter = Fecha.substr(i,1);
	 if ( (caracter == "/") ||
	       (caracter == "0") ||
	        (caracter == "1") ||
	         (caracter == "2") ||
	          (caracter == "3") ||
	           (caracter == "4") ||
	            (caracter == "5") ||
	             (caracter == "6") ||	  
	              (caracter == "7") ||
	               (caracter == "8") ||	  
	                (caracter == "9") ) {	                
	                
	            flg_recorrer = 0;
     	 }else{
     	   flg_recorrer = 1;
     	   break;
     	 }
     	 
     	} //for
     	     	     	
        if (flg_recorrer == 1){
     	 var cadena_aux = "";
     	 
     	for (var i=0; i<Fecha.length; i++) {
	 var caracter = Fecha.substr(i,1);
	 if (caracter == "/"){
	   cadena_aux = cadena_aux + Fecha.substr(i,1);
	 }else{
	  if (caracter == "0"){
	    cadena_aux = cadena_aux + Fecha.substr(i,1);
	  }else{
	   if (caracter == "1"){
	    cadena_aux = cadena_aux + Fecha.substr(i,1);
	   }else{
	    if (caracter == "2"){
	     cadena_aux = cadena_aux + Fecha.substr(i,1);
	    }else{
	     if (caracter == "3"){
	      cadena_aux = cadena_aux + Fecha.substr(i,1);
	     }else{
	      if (caracter == "4"){
	       cadena_aux = cadena_aux + Fecha.substr(i,1);
	      }else{
	       if (caracter == "5"){
	        cadena_aux = cadena_aux + Fecha.substr(i,1);
	       }else{
	        if (caracter == "6"){	  
	         cadena_aux = cadena_aux + Fecha.substr(i,1);
	        }else{
	         if (caracter == "7"){
	          cadena_aux = cadena_aux + Fecha.substr(i,1);
	         }else{
	          if (caracter == "8"){	  
	           cadena_aux = cadena_aux + Fecha.substr(i,1);
	          }else{
	           if (caracter == "9"){	  
	            cadena_aux = cadena_aux + Fecha.substr(i,1);
	           }	          
	          }//8	         
	         }//7
	        }//6	       
	       }//5	      
	      }//4	     
	     }//3
	    }//2	 
	   }//1	  
	  }//0
	 }// /                
     	} //for
     	     	
     	Fecha = cadena_aux;
	cadena_aux = "";
	control.value = Fecha;
	
       } //if
       
       /////////////////////////////////////////////////////////////////////////////////

        //cadena de fecha entre 7 y 10 digitos, sin manejo de separadores
        //por asumir que se ingresa el ano.
	if (Fecha.length >= 7 && Fecha.length < 10){
	   return;
	}     	
       
       //if (flg_recorrer == 1 || Fecha.length >= 10){
	//Llama funcion borra Separadores
        Fecha = borraSeparadores(Fecha);
        
       //}
        //Llama funcion agrega separadores
        Fecha = agregaSeparadores(Fecha);       
                
       
       
     } //else
     
     control.value = Fecha;
}

function agregaSeparadores(control) 
{
        var Fecha = control;
        // inserta separadores en fecha en posiciones 2 y 5
	var parte_uno = "";
	var parte_dos = "";
	if (Fecha.length > 2 && Fecha.substr(2,1) != "/" ){
	       parte_uno = Fecha.substr(0,2);
	       parte_dos = Fecha.substr(2,Fecha.length);
	       Fecha = parte_uno + "/" + parte_dos;
	}
	if (Fecha.length > 5 && Fecha.substr(5,1) != "/"  ){
	       parte_uno = Fecha.substr(0,5);
	       parte_dos = Fecha.substr(5,Fecha.length);
	       Fecha = parte_uno + "/" + parte_dos;       
	}	
	
	return(Fecha.substr(0,10));
}

function borraSeparadores(control) 
{

        var Fecha = control;
	//borrar separadores
	var cadena_aux = "";

	for (var i=0; i<Fecha.length; i++) {
		var caracter = Fecha.substr(i,1);
		if (caracter != "/"){
		   cadena_aux = cadena_aux + Fecha.substr(i,1);
		}                
	}
	Fecha = cadena_aux;
	cadena_aux = "";
	
	return(Fecha);
}

/////////////////////////////////////////////////////////

function fechaMayor(FEC_uno,FEC_dos)
{
  var FecUnoymd = FEC_uno.substr(6,4) + FEC_uno.substr(3,2) + FEC_uno.substr(0,2);
  var FecDosymd = FEC_dos.substr(6,4) + FEC_dos.substr(3,2) + FEC_dos.substr(0,2);
  
	if(FecUnoymd <= FecDosymd) 
         	return (false);
     	return (true);
}  

/////////////////////////////////////////////////////////

function fechaMenor(FEC_uno,FEC_dos)
{
  var FecUnoymd = FEC_uno.substr(6,4) + FEC_uno.substr(3,2) + FEC_uno.substr(0,2);
  var FecDosymd = FEC_dos.substr(6,4) + FEC_dos.substr(3,2) + FEC_dos.substr(0,2);
  
	if(FecUnoymd >= FecDosymd) 
         	return (false);
     	return (true);
}  
//////////////////////////////////////////////////////////////////////
/*No permite el tipeado de caracteres no numericos y guion - . 
  Recibe el evento producido en la pantalla*/
function soloFecha(evento)
{
    var esIE = document.all?true:false;
    var esNS = document.layers?true:false;
    var key = (esIE) ? window.event.keyCode : evento.which;
    var obj = (esIE) ? evento.srcElement : evento.target;
    EsNum = (key==47||(key > 47 && key < 58))? true:false;
    window.event.keyCode = (!EsNum && esIE) ? 0:key;
    evento.which = (!EsNum && esNS) ? 0:key;
    return(EsNum);
}


 ////////////////////////////////////////////////////////////////////////////
 /*Valida la fecha que contiene el objeto pasado por parametro
formato de fecha (dd-mm-yyyyy). Devuelve booleano*/
function validarFecha(obj)
{


    cadena = obj.value;
    
    
    if(cadena == ""){
        return false;
    }
    var Fecha= new String(cadena)
    var Ano= new String(Fecha.substring(Fecha.lastIndexOf("/")+1,Fecha.length))
    var Mes= new String(Fecha.substring(Fecha.indexOf("/")+1,Fecha.lastIndexOf("/")))
    var Dia= new String(Fecha.substring(0,Fecha.indexOf("/")))
    
    if (isNaN(Ano) || Ano.length<4 || Ano.length>4 || parseFloat(Ano)<1900){
        //alerta(60006,msg60006,comunURL);
        alert('Formato inv\xE1lido, formato v\xE1lido: dd/mm/aaaa - a\xF1o mayor a 1900');
        obj.value = "";
        obj.focus();
        return false;
    }
    if (isNaN(Mes) || parseFloat(Mes) < 1 || parseFloat(Mes) > 12 || Mes == ""){
        alert('Fecha inv\xE1lida');
        //alerta(60007,msg60007,comunURL);
        obj.value = "";
        obj.focus();
        return false;
    }
    if (isNaN(Dia) || parseFloat(Dia) < 1 || parseFloat(Dia) > 31 || Dia == ""){
        alert('Fecha inv\xE1lida');
        // alerta(60007,msg60007,comunURL);
        obj.value = "";
        obj.focus();
        return false;
    }
    if (Mes == 4 || Mes == 6 || Mes == 9 || Mes == 11 || Mes == 2) {
        if (Mes == 2 && Dia > 28 || Dia > 30) {
            alert('Fecha inv\xE1lida');
            // alerta(60007,msg60007,comunURL);
            obj.value = "";
            obj.focus();
            return false;
        }
    }
    if (Dia.length == 1)Dia='0'+Dia;
    if (Mes.length == 1)Mes='0'+Mes;
    obj.value = Dia+'/'+Mes+'/'+Ano;
    return true;
}      
