/*Funcion para hacer aparecer desaparecer los controles de la consulta, considerando
si se elege poliza o endosos
27/12/2006
RSEN*/

function mostrarCriterios(){
	var tipoOp =document.getElementById("frmConsulta:tipoOperacion");
	
	if(tipoOp!=null)
	{
	   //entra por poliza_consulta		
		//segun el tipo de operacion hacer aparecer los criterios correspondientes
		if(tipoOp.value==0)
		{
			//mostrar('criterios de poliza');		
			document.getElementById("frmConsulta:divLabelNumeroEndoso").style["display"] = "none";
			document.getElementById("frmConsulta:divInputNumeroEndoso").style["display"] = "none";				
			document.getElementById("frmConsulta:botonBusEnd").style["display"] = "none";
			
			document.getElementById("frmConsulta:msgEndoso").style["display"] = "none";
			document.getElementById("frmConsulta:msgConsPoli").style["display"] = "";
			document.getElementById("frmConsulta:msgError").style["display"] = "";
			
			document.getElementById("frmConsulta:divcmdBuscarEndosos").style["display"] = "none";	
			document.getElementById("frmConsulta:divcmdBuscarDetEnd").style["display"] = "none";
		
			
			//document.getElementById('frmConsulta:divLabelTipoSeguro').style["display"] = "";
			//document.getElementById('frmConsulta:divInputTipoSeguro').style["display"] = "";
			
			document.getElementById('frmConsulta:divlabelPatente').style["display"] = "";
			document.getElementById('frmConsulta:divInputPatente').style["display"] = "";	
			
								
			document.getElementById('frmConsulta:divLabelSeccion').style["display"] = "";
			document.getElementById('frmConsulta:divInputSeccion').style["display"] = "";
			document.getElementById('frmConsulta:divlabelNumeroPersona').style["display"] = "";
			document.getElementById('frmConsulta:divInputNumeroPersona').style["display"] = "";
			document.getElementById('frmConsulta:divlabelFechaDesdeEmision').style["display"] = "";
			document.getElementById('frmConsulta:divInputFechaDesdeEmision').style["display"] = "";
			document.getElementById('frmConsulta:divlabelFechaHastaEmision').style["display"] = "";
			document.getElementById('frmConsulta:divInputFechaHastaEmision').style["display"] = "";
			document.getElementById('frmConsulta:divLabelFechaDesde').style["display"] = "";
			document.getElementById('frmConsulta:divInputFechaDesde').style["display"] = "";
			document.getElementById('frmConsulta:divLabelFechaHasta').style["display"] = "";
			document.getElementById('frmConsulta:divInputFechaHasta').style["display"] = "";
			document.getElementById('frmConsulta:divcmdBuscarPolizas').style["display"] = "";
			document.getElementById("frmConsulta:aux").style["display"] = "";
			document.getElementById("frmConsulta:aux1").style["display"] = "";
			
			if(document.getElementById('frmConsulta:divlabelNumeroCliente')!=null){			
				//criterios comunes a agente organizador y productor
				document.getElementById('frmConsulta:divlabelNumeroCliente').style["display"] = "";
				document.getElementById('frmConsulta:divInputNumeroClienteDelAgente').style["display"] = "";
				document.getElementById('frmConsulta:divlblnombreClienteDelAgente').style["display"] = "";
				document.getElementById('frmConsulta:divinputnombreClienteDelAgente').style["display"] = "";
				document.getElementById('frmConsulta:divlabelEstadoPoliza').style["display"] = "";
				document.getElementById('frmConsulta:divInputEstado').style["display"] = "";	
				
				var cboEst= document.getElementById("frmConsulta:numeroEstado");
				cboEst.value=1;
				cboEst[1].selected;
			}	
			
			if(document.getElementById('frmConsulta:divlabelNumeroAgente')!=null){
				//criterios del agente organizador
				document.getElementById('frmConsulta:divlabelNumeroAgente').style["display"] = "";	
				document.getElementById('frmConsulta:divInputNumeroAgente').style["display"] = "";	
				document.getElementById('frmConsulta:divlblnombreAgente').style["display"] = "";	
				document.getElementById('frmConsulta:divinputnombreAgente').style["display"] = "";	
			}	
			
		}else	
		{
			//mostrar('criterios de endosos');		
			//document.getElementById('frmConsulta:divLabelTipoSeguro').style["display"] = "none";
			//document.getElementById('frmConsulta:divInputTipoSeguro').style["display"] = "none";
			
			document.getElementById('frmConsulta:divlabelPatente').style["display"] = "none";
			document.getElementById('frmConsulta:divInputPatente').style["display"] = "none";	
			
			document.getElementById('frmConsulta:divlabelNumeroPersona').style["display"] = "none";
			document.getElementById('frmConsulta:divInputNumeroPersona').style["display"] = "none";
			document.getElementById('frmConsulta:divlabelFechaDesdeEmision').style["display"] = "none";
			document.getElementById('frmConsulta:divInputFechaDesdeEmision').style["display"] = "none";
			document.getElementById('frmConsulta:divlabelFechaHastaEmision').style["display"] = "none";
			document.getElementById('frmConsulta:divInputFechaHastaEmision').style["display"] = "none";
			document.getElementById('frmConsulta:divLabelFechaDesde').style["display"] = "none";
			document.getElementById('frmConsulta:divInputFechaDesde').style["display"] = "none";
			document.getElementById('frmConsulta:divLabelFechaHasta').style["display"] = "none";
			document.getElementById('frmConsulta:divInputFechaHasta').style["display"] = "none";	
			document.getElementById('frmConsulta:divcmdBuscarPolizas').style["display"] = "none";
			document.getElementById("frmConsulta:aux").style["display"] = "none";	
			document.getElementById("frmConsulta:aux1").style["display"] = "none";	
				
			document.getElementById("frmConsulta:divLabelNumeroEndoso").style["display"] = "";
			document.getElementById("frmConsulta:divInputNumeroEndoso").style["display"] = "";
			document.getElementById("frmConsulta:botonBusEnd").style["display"] = "";
			
			document.getElementById("frmConsulta:msgEndoso").style["display"] = "";
			document.getElementById("frmConsulta:msgConsPoli").style["display"] = "none";
			
			document.getElementById('frmConsulta:divLabelSeccion').style["display"] = "";
			document.getElementById('frmConsulta:divInputSeccion').style["display"] = "";
			
			var cboEnd =document.getElementById("frmConsulta:numeroEndoso");
			if(cboEnd.value==-1){
				document.getElementById("frmConsulta:divcmdBuscarEndosos").style["display"] = "";	
				document.getElementById("frmConsulta:divcmdBuscarDetEnd").style["display"] = "none";
			}
			else
			{
				document.getElementById("frmConsulta:divcmdBuscarEndosos").style["display"] = "none";	
				document.getElementById("frmConsulta:divcmdBuscarDetEnd").style["display"] = "";			
			}		
			
			if(document.getElementById('frmConsulta:divlabelNumeroCliente')!=null){		
				//perfil agente productor y organizador
				document.getElementById('frmConsulta:divlabelNumeroCliente').style["display"] = "none";
				document.getElementById('frmConsulta:divInputNumeroClienteDelAgente').style["display"] = "none";
				document.getElementById('frmConsulta:divlblnombreClienteDelAgente').style["display"] = "none";
				document.getElementById('frmConsulta:divinputnombreClienteDelAgente').style["display"] = "none";
				document.getElementById('frmConsulta:divlabelEstadoPoliza').style["display"] = "none";
				document.getElementById('frmConsulta:divInputEstado').style["display"] = "none";	
			}
			
			if(document.getElementById('frmConsulta:divlabelNumeroAgente')!=null){
				//criterios del agente organizador
				document.getElementById('frmConsulta:divlabelNumeroAgente').style["display"] = "none";	
				document.getElementById('frmConsulta:divInputNumeroAgente').style["display"] = "none";	
				document.getElementById('frmConsulta:divlblnombreAgente').style["display"] = "none";	
				document.getElementById('frmConsulta:divinputnombreAgente').style["display"] = "none";	
			}	
								
	
		}
	}	
}

function mostrarErrorTipoEndoso(){
	document.getElementById("frmConsulta:msgError").style["display"] = "none";
}	
/* ----------------------------------------------------------------- */
function mostrarBotonEndoso(){
	var endoso =document.getElementById("frmConsulta:numeroEndoso");
	
	//segun el tipo de operacion hacer aparecer los criterios correspondientes
	if(endoso.value!=-1){
		    //entra por un endoso en particular de la poliza, aparecer boton cmdBuscarDetEnd
			document.getElementById("frmConsulta:divcmdBuscarEndosos").style["display"] = "none";	
			document.getElementById("frmConsulta:divcmdBuscarDetEnd").style["display"] = "";
				
	}	
	else{
	
			//entra por la opcion todos, aparecer boton cmdBuscarEndosos
			document.getElementById("frmConsulta:divcmdBuscarEndosos").style["display"] = "";	
			document.getElementById("frmConsulta:divcmdBuscarDetEnd").style["display"] = "none";
			
	}	
}
/* ----------------------------------------------------------------- */
function limpiarCampos() {
	var tipoOp =document.getElementById("frmConsulta:tipoOperacion");
	if(tipoOp!=null)
	{
	   var cboSec = document.getElementById("frmConsulta:seccion");
	  // var cboTipSeg = document.getElementById("frmConsulta:tipoSeguro");
	   var cboPer = document.getElementById("frmConsulta:numeroPersona");
	   
		//segun el tipo de operacion limpiar los criterios correspondientes
		if(tipoOp.value==0)
		{
			//('criterios de poliza');					
				document.getElementById("frmConsulta:numeroPoliza").value="";				
				document.getElementById("frmConsulta:fechaDesdeEmisionPoliza").value="";
				document.getElementById("frmConsulta:fechaHastaEmisionPoliza").value="";
				document.getElementById("frmConsulta:fechaDesde").value="";
				document.getElementById("frmConsulta:fechaHasta").value="";		
				document.getElementById("frmConsulta:patente").value="";			
				document.getElementById("frmConsulta:msgError").style["display"] = "none";
				
				//cboTipSeg.value=0;
				//cboTipSeg[0].selected;					
				
				cboSec.value=0;
				cboSec[0].selected;				
				
				cboPer.value=0;
				cboPer[0].selected;	
				
				var cboAg = document.getElementById("frmConsulta:numeroAgente");
				if(cboAg!=null){		
					//perfil agente organizador	
					cboAg.value=0;
					cboAg[0].selected;	
							
					document.getElementById("frmConsulta:nombreAgente").value="";				
			    }
			    
			    var cboCli = document.getElementById("frmConsulta:numeroClienteDelAgente");
				if(cboCli!=null){		
					//perfil agente productor y organizador	
					/*cboCli.value=0;
					cboCli[0].selected;	*/
					document.getElementById("frmConsulta:numeroClienteDelAgente").value="";		
							
					var cboEst= document.getElementById("frmConsulta:numeroEstado");
					cboEst.value=1;
					cboEst[1].selected;	
					document.getElementById("frmConsulta:nombreClienteDelAgente").value="";				
			    }
						
		}else{
			//('criterios de endosos');					
			document.getElementById("frmConsulta:numeroPoliza").value="";
			var cboEnd = document.getElementById("frmConsulta:numeroEndoso");	
			var i;
			var cant = cboEnd.length;
			for(i=0;i<cant;i++){
				cboEnd.options[i] = null; 	
				cant=cant-1;			
			}		
			
			var seleccionar = new Option("- Todos -",-1,"","");
			cboEnd[0] = seleccionar;
		}	
	}	
}


/* ----------------------------------------------------------------- */
function validarBusEndosos()
{
/*	var nropoliza = document.getElementById("frmConsulta:numeroPoliza");
	
	if(nropoliza.value=='' || nropoliza.value==null ||  nropoliza.value<=0){
		alert("Debe ingresar el Numero de Poliza.");
		nropoliza.focus();
		return false;
	}
	else{	
		return true;
	}*/
	return true;
}
/* ----------------------------------------------------------------- */
/* Manejo de calendario*/
var ventanaCalendario = null;      
	  			
			function doCalendario1(nom,control) 
			{
			  	addCalendar(nom, "Seleccione Fecha", control, "frmConsulta");
			  	ventanaCalendario = showCal(nom);
			}
			
			function doCalendario2(nom,control) 
			{
			  	addCalendar(nom, "Seleccione Fecha", control, "frmConsulta");
			  	ventanaCalendario = showCal(nom);
			}
			function doCalendario3(nom,control) 
			{
			  	addCalendar(nom, "Seleccione Fecha", control, "frmConsulta");
			  	ventanaCalendario = showCal(nom);
			}   
			function doCalendario4(nom,control) 
			{
			  	addCalendar(nom, "Seleccione Fecha", control, "frmConsulta");
			  	ventanaCalendario = showCal(nom);
			}      
			   
			function cerrarVentana() 
			{
			   if(ventanaCalendario != null) 
			   {
				  ventanaCalendario.close();
			   }
			}
			
/* ----------------------------------------------------------------- */
function validar() { /*
				var cuenta = document.getElementById('frmRegistracion:cuenta');
				var nombre = document.getElementById('frmRegistracion:apellido');
				var apellido = document.getElementById('frmRegistracion:apellido');
				var tipoDocumento = document.getElementById('frmRegistracion:tipoDocumento');
				var numeroDocumento = document.getElementById('frmRegistracion:numeroDocumento');
				var numeroTelefono = document.getElementById('frmRegistracion:numeroTelefono');
				var usuario = document.getElementById('frmRegistracion:codigo');
				var email = document.getElementById('frmRegistracion:email');

				if (cuenta == '' ||
					nombre.value == '' ||
					apellido.value == '' ||
					tipoDocumento.value == '0' ||
					numeroDocumento.value == '' ||
					numeroTelefono.value == '' ||
					usuario.value == '' ||
					email.value == '') {

					alert('Debe completar los campos obligatorios.');
					return false;
				} else {
					if(emailCheck(email.value, false)) 
						return true;
					else
						email.focus();
						return false;	
				}*/
		return true;
	}
			
	/*Validacion de campo obligatorios para busqueda de polizas*/
	function validarCampos(){	
	//solo valida para consulta de polizas
	var tipoOp =document.getElementById("frmConsulta:tipoOperacion");	
	if(tipoOp!=null)
	{	
		if(tipoOp.value==0)
		{		
			var strMensaje = "Debe ingresar por lo menos alguno de los siguientes filtros: Rama y N\xFAmero de P\xF3liza, Asegurado, Patente o Rango de Fechas de Emisi\xF3n o Vigencia que no supere los 90 d\xEDas. Cualquier otro criterio deber\xE1 ser combinado con alguno de los anteriores.";
			var aux=0;
			var patente = document.getElementById('frmConsulta:patente');
			var fechaDesdeEmisionPoliza = document.getElementById('frmConsulta:fechaDesdeEmisionPoliza');
			var fechaHastaEmisionPoliza = document.getElementById('frmConsulta:fechaHastaEmisionPoliza');
			var fechaDesde = document.getElementById('frmConsulta:fechaDesde');
			var fechaHasta = document.getElementById('frmConsulta:fechaHasta');
			var numeroPoliza = document.getElementById('frmConsulta:numeroPoliza');
			var seccion = document.getElementById('frmConsulta:seccion');
			var aseguradoDelAg = document.getElementById('frmConsulta:numeroClienteDelAgente');	
			var nombreaseguradoDelAg = document.getElementById('frmConsulta:nombreClienteDelAgente');		
			var aseguradoCli = document.getElementById('frmConsulta:numeroPersona');
			
			if(aseguradoCli!=null){
				asegurado = aseguradoCli;
			}
				
			if(aseguradoDelAg!=null){
			    asegurado = aseguradoDelAg;
			}
			
			
			
		
						
			if(     (asegurado==null || asegurado.value==0) && 
			        (nombreaseguradoDelAg==null || nombreaseguradoDelAg.value=="") && 
					(patente==null || patente.value=="0" || patente.value=="")
					&& (fechaDesdeEmisionPoliza==null || fechaDesdeEmisionPoliza.value=="")
					&& (fechaHastaEmisionPoliza==null || fechaHastaEmisionPoliza.value=="")
					&& (fechaDesde.value=="")
					&& (fechaHasta.value=="")			
					&& (numeroPoliza==null || numeroPoliza.value==0)			
					){		
					aux=1;				
				}			
				
					
				if(aux==1){
					if(seccion!=null && seccion.value!=0){
						strMensaje = "Si quiere acotar la consulta por Rama debe ingresar tambi\xE9n el N\xFAmero de P\xF3liza o Rango de Fechas de Emisi\xF3n o Vigencia que no supere los 90 d\xEDas.";				
					}
				}				
			  
				if(fechaDesdeEmisionPoliza.value != ""){				
					if(fechaHastaEmisionPoliza.value!=""){
						if(getCantidadDeDias(fechaDesdeEmisionPoliza, fechaHastaEmisionPoliza)){
							aux=1;	
							strMensaje = "El rango de fechas no puede ser superior a 90 d\xEDas.";			
						}	
					}else{
					  aux=1;
					  strMensaje = "Debe ingresar la Fecha Emisi\xF3n Hasta.";					
					}		
				}else{
				   if(fechaHastaEmisionPoliza.value!=""){	
				     aux=1;			
					 strMensaje = "Debe ingresar la Fecha Emisi\xF3n Desde.";					
					}	
				}			
				
				
				if(fechaDesde.value != ""){				
					if(fechaHasta.value!=""){
						if(getCantidadDeDias(fechaDesde, fechaHasta)){
							aux=1;	
							strMensaje = "El rango de fechas no puede ser superior a 90 d\xEDas.";			
						}	
					}else{
					 aux=1;
					 strMensaje = "Debe ingresar la Fecha Vigencia Hasta.";					
					}		
				}else{
				   if(fechaHasta.value!=""){
				     aux=1;				
					 strMensaje = "Debe ingresar la Fecha Vigencia Desde.";					
					}	
				}							
						
				if(aux==1){
					alert(strMensaje);
					limpiarCampos();
					seccion.focus();
					return false;
				}else{
				    //todo esta bien			   
					return true;			
				}	
			}else{
				return true;		
			}	
		}
		else{
			return true;		
		}	
					
	}	
	
	function getCantidadDeDias(objfechaDesde,objfechaHasta){   
	   //Obtiene dia, mes y año
	   var fecha1 = new fechaFunBusPol(objfechaDesde.value)   
	   var fecha2 = new fechaFunBusPol(objfechaHasta.value)
		
	   //Obtiene objetos Date
	   var miFecha1 = new Date( fecha1.anio, fecha1.mes, fecha1.dia )
	   var miFecha2 = new Date( fecha2.anio, fecha2.mes, fecha2.dia )
		
	   //Resta fechas y redondea
	   var diferencia = miFecha1.getTime() - miFecha2.getTime()
	   var dias = Math.floor(diferencia / (1000 * 60 * 60 * 24))  
	   var segundos = Math.floor(diferencia / 1000)	  
	   
	   if(Math.abs(dias) > 90){
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