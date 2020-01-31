<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<f:loadBundle basename="com.refinor.extranet.bundle.Messages" var="Message"/>
<html>
<f:view>
	<head>
        <meta http-equiv="Content-Type"
             content="text/html; charset=windows-1252"/>
		<title><h:outputText value="#{Message.nombre_sistema}"/> </title>
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/refinor.css">		
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsutiles.js"></script>
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/cal2.js"></script>
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsfechas.js"></script>

		<script>
		var ventanaCalendario = null;   
		
		function doCalendario4(nom,control) 
		{
		  	addCalendar(nom, "Seleccione Fecha", control, "frmFiltroCuenta");
		  	ventanaCalendario = showCal(nom);
		}
		
		function doCalendario5(nom,control) 
		{
		  	addCalendar(nom, "Seleccione Fecha", control, "frmFiltroCuenta");
		  	ventanaCalendario = showCal(nom);
		}
		
		function cerrarVentana() 
		{
		   if(ventanaCalendario != null) 
		   {
			  ventanaCalendario.close();
		   }
		}
		
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
		        obj.focus();
		        return false;
		    }
		    if (isNaN(Mes) || parseFloat(Mes) < 1 || parseFloat(Mes) > 12 || Mes == ""){
		        alert('Fecha inv\xE1lida');
		        //alerta(60007,msg60007,comunURL);       
		        obj.focus();
		        return false;
		    }
		    if (isNaN(Dia) || parseFloat(Dia) < 1 || parseFloat(Dia) > 31 || Dia == ""){
		        alert('Fecha inv\xE1lida');
		        // alerta(60007,msg60007,comunURL);        
		        obj.focus();
		        return false;
		    }
		    
		    
		    
		     if (Mes == 4 || Mes == 6 || Mes == 9 || Mes == 11) {
		        if (Dia > 30) {
		            alert('Fecha inv\xE1lida');
		            // alerta(60007,msg60007,comunURL);            
		            obj.focus();
		            return false;
		        }
		    }
		  
		   	if (Mes == 2) {
		     	//es bisiesto     	
		     	if (esBisiesto(Ano)){
		     	  if (Dia > 29) {
		            alert('Fecha inv\xE1lida');
		            // alerta(60007,msg60007,comunURL);
		            
		            obj.focus();
		            return false;
		          }     	
		     	} else{     	
			     	 if (Dia > 28) {
			            alert('Fecha inv\xE1lida');
			            // alerta(60007,msg60007,comunURL);	           
			            obj.focus();
			            return false;
			       	 }
			    }   	 
		     }
		     
		     
		    if (Dia.length == 1)
		    		Dia='0'+Dia;
		    		
		   	
		    if (Mes.length == 1)Mes='0'+Mes;
		    obj.value = Dia+'/'+Mes+'/'+Ano;
		    return true;
		    }
		    
		    function esBisiesto(ano){
				return ((ano%4==0 && ano%100!=0)||(ano%400==0)?true:false)
			}
			
					
		/*Validacion de campo obligatorios*/
		function validarCamposObligatoriosMC(){	
		
				//solo valida para consulta de polizas
				var strMensaje = "Debe ingresar Fecha Desde y Fecha Hasta.";
				var aux=0;
				var fechaDesdeEmisionPoliza = document.getElementById('frmFiltroCuenta:fechaDesde');
				var fechaHastaEmisionPoliza = document.getElementById('frmFiltroCuenta:fechaHasta');
			
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
	
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0" >
		<%@ include file="../header.jsp" %>
		
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteMayorContableBean.mostrarFrmLista}" onsubmit="return validarCamposObligatoriosMC()">	
		<t:saveState value="#{refReporteMayorContableBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteMayorContableBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_mayor_contable}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>		
				
		<%/*FILTROS*/%>		
			<h:panelGrid columns="4" width="90%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
				</h:column>	
				<h:column></h:column>
				<h:column></h:column>
				<h:column></h:column>									
			</h:panelGrid>
			
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8-30b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				
				
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.entre_fechas_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="fechaDesde" value="#{refReporteMayorContableBean.fltFechaDesde}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
						<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
					</h:inputText>	
					<h:outputText value="&nbsp;" escape="false"></h:outputText>					
					<h:outputLink value="javascript:doCalendario4('Calendar4','frmFiltroCuenta:fechaDesde');">
					<h:graphicImage style="border: none; vertical-align: middle;" value="/img/calendario.gif" width="25px" height="20"/>
					</h:outputLink>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					<t:outputText value="(#{Message.fecha_mascara_label})"  styleClass="campoNoEditable"/>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>						
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>
					
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.al_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>							
					
					<h:inputText id="fechaHasta" value="#{refReporteMayorContableBean.fltFechaHasta}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
						<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
					</h:inputText>	
					<h:outputText value="&nbsp;" escape="false"></h:outputText>					
					<h:outputLink value="javascript:doCalendario5('Calendar5','frmFiltroCuenta:fechaHasta');">
					<h:graphicImage style="border: none; vertical-align: middle;" value="/img/calendario.gif" width="25px" height="20"/>
					</h:outputLink>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					<t:outputText value="(#{Message.fecha_mascara_label})"  styleClass="campoNoEditable"/>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>		
					
				</t:column>		
			</h:panelGrid>	
			
			
			
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8-30b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
		
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.desde_cuenta_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="clientedesde" value="#{refReporteMayorContableBean.fltCuentaDesde}"  size="12" maxlength="10" styleClass="campo">						
					</h:inputText>
					
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.a_la_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>							
					
					<h:inputText id="clientehasta" value="#{refReporteMayorContableBean.fltCuentaHasta}"  size="12" maxlength="10" styleClass="campo">						
					</h:inputText>				
				</t:column>	
				
				<%/* CENTRO DE SERVICIO CCSS 
				*/ %>
				<t:column>
					<h:outputLabel  styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.centro_servicio_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:selectOneListbox id="ccss" size="1" value="#{refReporteMayorContableBean.ccss}" styleClass="campo">
						<f:selectItems value="#{refReporteMayorContableBean.lstccss}" />
					</h:selectOneListbox>				
				</t:column>	
													
			</h:panelGrid>
						
			
			<h:panelGrid columns="4" width="90%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
				<t:panelGrid border="0" cellpadding="" cellspacing="0" styleClass="columnaTablaTexto" 
						columns="1" columnClasses="cpo11-20" width="100%" >							
						<t:column>
							<t:div styleClass="cpo11">
								<t:outputText id="datosOblig" value="&nbsp;&nbsp;#{Message.datos_obligatorios_texto}"  styleClass="datoObligatorio" escape="false"/>
							</t:div>
						</t:column>
						
				</t:panelGrid>
				</h:column>
				<h:column>	</h:column>
				<h:column>	</h:column>				
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0">						 
				     	<h:column>				     	
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteMayorContableBean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		<t:panelGrid width="90%" columnClasses="columnaTablaTexto" columns="1" cellspacing="0" cellpadding="0">
		<t:column>
		    <t:div styleClass="cpo7n">
		     <t:graphicImage value="/img/info.ico" alt="#{Message.mensaje_mayor_contable_archivo}" width="10px" height="10px"/>
			 <h:outputText value="&nbsp;#{Message.mensaje_mayor_contable_archivo}" escape="false"/>
			</t:div>
		</t:column>		
		</t:panelGrid>	
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/*
		<h:panelGrid width="95%" cellspacing="3" columns="1" cellpadding="5" 
		rendered="#{refReporteMayorContableBean.mostrarLista}" styleClass="columnaTablaCentrada" >
		<h:column>				
		   		<t:commandButton value="#{Message.generar_archivo_Excel}" actionListener="#{refReporteMayorContableBean.generarExcelNuevo}" styleClass="boton"/>
				
				<h:outputLink  rendered="#{refReporteMayorContableBean.nombreArchivo!='' && refReporteMayorContableBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteMayorContableBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_archivo_abel}" />
				</h:outputLink>				
		 </h:column>
		 </h:panelGrid>
		 */ %>
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteMayorContableBean.pagina}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteMayorContableBean.pagina.lastPage!=1 && refReporteMayorContableBean.pagina.totalElements!=0 && refReporteMayorContableBean.pagina.numpage != 1 && refReporteMayorContableBean.mostrarLista}" actionListener="#{refReporteMayorContableBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteMayorContableBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteMayorContableBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteMayorContableBean.pagina.numpage == 1}" rendered="#{refReporteMayorContableBean.pagina.lastPage!=1 && refReporteMayorContableBean.pagina.totalElements!=0  && refReporteMayorContableBean.pagina.numpage != 1 && refReporteMayorContableBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteMayorContableBean.mostrarLista}" value="#{refReporteMayorContableBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteMayorContableBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteMayorContableBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		
		  		
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteMayorContableBean.mostrarLista}" value="#{refReporteMayorContableBean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>			   		
		   		
		   		<h:outputLink  rendered="#{refReporteMayorContableBean.nombreArchivo!='' && refReporteMayorContableBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteMayorContableBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
				
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteMayorContableBean.pagina.lastPage!=1 && refReporteMayorContableBean.pagina.totalElements!=0 && refReporteMayorContableBean.pagina.numpage != refReporteMayorContableBean.pagina.lastPage && refReporteMayorContableBean.mostrarLista}" actionListener="#{refReporteMayorContableBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteMayorContableBean.pagina.numpage == refReporteMayorContableBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteMayorContableBean.pagina.lastPage!=1 && refReporteMayorContableBean.pagina.totalElements!=0 && refReporteMayorContableBean.pagina.numpage != refReporteMayorContableBean.pagina.lastPage && refReporteMayorContableBean.mostrarLista}" actionListener="#{refReporteMayorContableBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteMayorContableBean.pagina.numpage == refReporteMayorContableBean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		<%/* <t:saveState value="#{refReporteMayorContableBean.items}"></t:saveState> */ %>
		 <t:saveState value="#{refReporteMayorContableBean.ccss}"></t:saveState>
		 <t:saveState value="#{refReporteMayorContableBean.lstccss}"></t:saveState>
		 <t:saveState value="#{refReporteMayorContableBean.fltCuentaHasta}"></t:saveState>
		<t:saveState value="#{refReporteMayorContableBean.fltCuentaDesde}"></t:saveState>
		<t:saveState value="#{refReporteMayorContableBean.fltFechaDesde}"></t:saveState>
		<t:saveState value="#{refReporteMayorContableBean.fltFechaHasta}"></t:saveState>
		<t:saveState value="#{refReporteMayorContableBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteMayorContableBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteMayorContableBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteMayorContableBean.nombreArchivo}"></t:saveState>
		<t:saveState value="#{refReporteMayorContableBean.provincia}"></t:saveState>
		<t:saveState value="#{refReporteMayorContableBean.provincias}"></t:saveState>
		
		
		<% /* LISTADO */%>
		
		<t:div  style="overflow:scroll; height:100%; width:700px;"  rendered="#{refReporteMayorContableBean.mostrarLista}">  
		<h:panelGrid width="90%" columns="1" columnClasses="bordeblanco" cellspacing="0" cellpadding="0" >						 
		<h:column>		
		
				<t:dataTable value="#{refReporteMayorContableBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaNumero,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaNumero,columnaTablaNumero,columnaTablaTexto, columnaTablaNumero, columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaNumero,columnaTablaNumeroFijo,columnaTablaNumeroFijo,columnaTablaNumeroFijo "
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteMayorContableBean.mostrarLista}" >
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cuenta_label}"/>
						</f:facet>
						<h:outputText value="#{item.cuenta}" />
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.descripcion_cuenta_label}"/>
						</f:facet>
						<h:outputText value="#{item.cuentaDesc}" />
						
					</h:column>	
					
					
										
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_label}"/>
						</f:facet>
						<h:outputText value="#{item.fecha}" >
						 <f:convertDateTime 
    pattern="dd/mm/yyyy" />
    </h:outputText>

					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.hora_label}"/>
						</f:facet>
						<h:outputText value="#{item.hora}" />
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.ccss_label}"/>
						</f:facet>
						<h:outputText value="#{item.ccssDesc}" />
						
					</h:column>	
					
					<h:column >
						<f:facet name="header">
							<h:outputText value="#{Message.cod_cliente_Alfa}"/>
						</f:facet>
						<h:outputText value="#{item.codClienteAlfa}"/>
					</h:column>	
					
					<h:column >
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.descripcionCliente}"/>
					</h:column>	
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_ejercicio_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroEjercicio}" />
						
					</h:column>	
					
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_asiento_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroAsiento}" />
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.descripcion_aciento_contable_label}"/>
						</f:facet>
						<h:outputText value="#{item.leyenda}" />
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_compronate_label_x}"/>
						</f:facet>
						<h:outputText value="#{item.nroSuc}" />	
						<h:outputText value="&nbsp;" escape="false"/>							
						<h:outputText value="#{item.nroComp}" />	
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.comprobante_label}"/>
						</f:facet>
						<h:outputText value="#{item.tipoComprobante}" />							
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.tipo_comprobante_label}"/>
						</f:facet>
						<h:outputText value="#{item.letra}" />							
					</h:column>	
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.comprobante_asociado_label}"/>
						</f:facet>
						<h:outputText value="#{item.comprobanteAsociado}" />							
					</h:column>	
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_rendicion_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroRendicion}" />							
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.debe_label}"/>
						</f:facet>
						
						<h:outputText value="#{item.valor}" rendered="#{item.debHab=='D'}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.haber_label}"/>
						</f:facet>
						<h:outputText value="#{item.valor}" rendered="#{item.debHab=='H'}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.monto_adeudado_label}"/>
						</f:facet>
						 <h:outputText value="#{item.saldo}" rendered="#{item.ultimo==1}" styleClass="campo8">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						 
						  <h:outputText value="#{item.saldo}" rendered="#{item.ultimo!=1}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						
					</h:column>	
					
					
					
					
					
					
					<%/*
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.importe_factura_label}"/>
						</f:facet>
						<h:outputText value="#{item.importeFactura}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						
					</h:column>	
					
					*/ %>
					
							
					
					
							
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
				</t:div>
			
				
			<%/*
			<h:panelGrid columns="1" width="90%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteMayorContableBean.volver}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			*/ %>	
		
		</h:column>
	</h:panelGrid>
	
	</h:form>
	
	
	<h:form id="frmFiltroCuenta1" rendered="#{refReporteMayorContableBean.mostrarFrmLista}" >	
		<t:saveState value="#{refReporteMayorContableBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteMayorContableBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>	
		
			<h:panelGrid columns="1" width="90%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteMayorContableBean.volver}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
				
		</h:column>
	</h:panelGrid>
	
	</h:form>
	
	
	
	
	<t:panelGrid width="90%" columnClasses="columnaTablaTexto" columns="1" cellspacing="0" cellpadding="0">
		<t:column>
		     <h:outputText value="&nbsp;" escape="false"/>
			
		</t:column>
		<t:column>
			<h:outputText value="&nbsp;" escape="false"/>
		</t:column>
		<t:column>
			<h:outputText value="&nbsp;" escape="false"/>
		</t:column>
	</t:panelGrid>	
		
	<%@ include file="../footer.jsp" %>
	
	</body>	
</f:view>
</html> 
						