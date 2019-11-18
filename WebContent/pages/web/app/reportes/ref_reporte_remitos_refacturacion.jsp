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
		
		function doCalendario6(nom,control) 
		{
		  	addCalendar(nom, "Seleccione Fecha", control, "frmFiltroCuenta");
		  	ventanaCalendario = showCal(nom);
		}
		
		function doCalendario7(nom,control) 
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
			
			
		
		
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0" >
		<%@ include file="../header.jsp" %>
		
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteRemitosCompletoBean.mostrarFrmLista}" >	
		<t:saveState value="#{refReporteRemitosCompletoBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteRemitosCompletoBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   
		   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
		
	<h:column>
		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  rowClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_remitos_refacturados}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
		
				
		<%/*FILTROS
		*/%>		
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
			
			<h:panelGrid columns="1" width="90%" columnClasses="cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputText styleClass="campoNoEditable" value="&nbsp;&nbsp;&nbsp;#{Message.comparando_por_fecha_de_remito_label}" escape="false"/>
				</t:column>				
			</h:panelGrid>
			
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8-30b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.entre_fechas_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="fechaDesde" value="#{refReporteRemitosCompletoBean.fltFechaDesde}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
						<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
					</h:inputText>	
					<h:outputText value="&nbsp;" escape="false"></h:outputText>					
					<h:outputLink value="javascript:doCalendario4('Calendar4','frmFiltroCuenta:fechaDesde');">
					<h:graphicImage style="border: none; vertical-align: middle;" value="/img/calendario.gif" width="25px" height="20"/>
					</h:outputLink>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					<t:outputText value="(#{Message.fecha_mascara_label})"  styleClass="campoNoEditable"/>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					
					
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.al_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>							
					
					<h:inputText id="fechaHasta" value="#{refReporteRemitosCompletoBean.fltFechaHasta}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
						<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
					</h:inputText>	
					<h:outputText value="&nbsp;" escape="false"></h:outputText>					
					<h:outputLink value="javascript:doCalendario5('Calendar5','frmFiltroCuenta:fechaHasta');">
					<h:graphicImage style="border: none; vertical-align: middle;" value="/img/calendario.gif" width="25px" height="20"/>
					</h:outputLink>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					<t:outputText value="(#{Message.fecha_mascara_label})"  styleClass="campoNoEditable"/>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
							
					
				</t:column>		
			</h:panelGrid>	
			
			
			
			<h:panelGrid columns="1" width="90%" columnClasses="cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputText styleClass="campoNoEditable" value="&nbsp;&nbsp;&nbsp;#{Message.comparando_por_fecha_facturacion_label}" escape="false"/>
				</t:column>				
			</h:panelGrid>
			
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8-30b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.entre_fechas_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="fechaDesdeDos" value="#{refReporteRemitosCompletoBean.fltFechaDesdeDos}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
						<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
					</h:inputText>	
					<h:outputText value="&nbsp;" escape="false"></h:outputText>					
					<h:outputLink value="javascript:doCalendario6('Calendar6','frmFiltroCuenta:fechaDesdeDos');">
					<h:graphicImage style="border: none; vertical-align: middle;" value="/img/calendario.gif" width="25px" height="20"/>
					</h:outputLink>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					<t:outputText value="(#{Message.fecha_mascara_label})"  styleClass="campoNoEditable"/>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					
					
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.al_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>							
					
					<h:inputText id="fechaHastaDos" value="#{refReporteRemitosCompletoBean.fltFechaHastaDos}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
						<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
					</h:inputText>	
					<h:outputText value="&nbsp;" escape="false"></h:outputText>					
					<h:outputLink value="javascript:doCalendario7('Calendar7','frmFiltroCuenta:fechaHastaDos');">
					<h:graphicImage style="border: none; vertical-align: middle;" value="/img/calendario.gif" width="25px" height="20"/>
					</h:outputLink>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					<t:outputText value="(#{Message.fecha_mascara_label})"  styleClass="campoNoEditable"/>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
							
					
				</t:column>		
			</h:panelGrid>	
			
			<h:panelGrid columns="1" width="90%" columnClasses="cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputText styleClass="campoNoEditable" value="&nbsp;&nbsp;&nbsp;#{Message.comparando_codigo_cliente_label}" escape="false"/>
				</t:column>				
			</h:panelGrid>	
			
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8-30b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
		
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.desde_el_cliente_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="clientedesde" value="#{refReporteRemitosCompletoBean.fltClienteDesde}"  size="12" maxlength="10" styleClass="campo">						
					</h:inputText>
					
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.al_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>							
					
					<h:inputText id="clientehasta" value="#{refReporteRemitosCompletoBean.fltClienteHasta}"  size="12" maxlength="10" styleClass="campo">						
					</h:inputText>				
				</t:column>	
				
				<%/* nro sucursal
				*/%>
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.nro_sucursal_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="sucursal" value="#{refReporteRemitosCompletoBean.fltNroSucursal}"  size="12" maxlength="10" styleClass="campo">						
					</h:inputText>
					
								
				</t:column>	
				
				<% /* nro de remito
				*/ %>
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.desde_nro_remito_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="nrorecibodesde" value="#{refReporteRemitosCompletoBean.fltNroReciboDesde}"  size="12" maxlength="10" styleClass="campo">						
					</h:inputText>
					
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.al_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>							
					
					<h:inputText id="nrorecibohasta" value="#{refReporteRemitosCompletoBean.fltNroReciboHasta}"  size="12" maxlength="10" styleClass="campo">						
					</h:inputText>				
				</t:column>	
				
				
				<%/*  ccss  */%>
				<t:column>							
					<h:outputLabel for="ccssi" styleClass="cpo8">
						<t:outputText value="&nbsp;&nbsp;#{Message.centro_servicio_label}&nbsp;&nbsp;" escape="false" />							
					</h:outputLabel>		
				</t:column>
				
				<t:column>
					<h:selectOneListbox id="ccss" size="1" value="#{refReporteRemitosCompletoBean.ccss}" styleClass="campo" 
						  immediate="true"   >					 
						<f:selectItems value="#{refReporteRemitosCompletoBean.lstccss}" />
					</h:selectOneListbox>
					
				</t:column>	
				
				
				<%/* condicion
				*/ %>
				<t:column>
					<h:outputLabel for="condicion" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.condicion_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:selectOneListbox id="condiciones" size="1" value="#{refReporteRemitosCompletoBean.condicion}" styleClass="campo"  >
						<f:selectItems value="#{refReporteRemitosCompletoBean.condiciones}" />
					</h:selectOneListbox>				
				</t:column>	
				
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.estado_combustible_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:selectOneListbox id="estados" size="1" value="#{refReporteRemitosCompletoBean.estadoRemito}" styleClass="campo"  >
						<f:selectItems value="#{refReporteRemitosCompletoBean.estadosRemito}" />
					</h:selectOneListbox>				
				</t:column>			
									
			</h:panelGrid>
			
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.consultar_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>
				</t:column>
				<t:column> 
						
					<h:selectOneRadio  value="#{refReporteRemitosCompletoBean.optRefacturacion}" styleClass="campoNoEditable" layout="pageDirection">
					     <f:selectItems  value="#{refReporteRemitosCompletoBean.optRefacturaciones}"/>
					</h:selectOneRadio>								
				</t:column>	
			</h:panelGrid>
			
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8-30b,cpo8" styleClass="columnaTablaTexto" cellspacing="0" cellpadding="4">						 
			
				<t:column >							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;Ver error precio con impuesto&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>
				<t:column >
					<h:selectBooleanCheckbox id="cambiarEstado" 
					    valueChangeListener="#{refReporteRemitosCompletoBean.cambiarEstado}" 
					   immediate="true" 
					   onchange="submit()" /> 						
				</t:column>								
			</h:panelGrid>		
						
			
			<h:panelGrid columns="4" width="90%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>	</h:column>
				<h:column>	</h:column>
				<h:column>	</h:column>				
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0">						 
				     	<h:column>				     	
				    		<t:commandButton value="#{Message.buscar_label}" id="buscarboton" actionListener="#{refReporteRemitosCompletoBean.buscarRefacturados}" styleClass="boton"/>
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
			
	<h:panelGrid width="90%" cellspacing="3" columns="1" cellpadding="5" 
		rendered="#{refReporteRemitosCompletoBean.mostrarLista}" styleClass="columnaTablaTexto" >
		<h:column>				
		   		<t:commandButton value="#{Message.generar_archivo_Excel}" actionListener="#{refReporteRemitosCompletoBean.generarExcelNuevoRef}" styleClass="boton"/>
				<h:outputLink  rendered="#{refReporteRemitosCompletoBean.nombreArchivo!='' && refReporteRemitosCompletoBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteRemitosCompletoBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_archivo_abel}" />
				</h:outputLink>				
		 </h:column>
		 </h:panelGrid>
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteRemitosCompletoBean.pagina}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteRemitosCompletoBean.pagina.lastPage!=1 && refReporteRemitosCompletoBean.pagina.totalElements!=0 && refReporteRemitosCompletoBean.pagina.numpage != 1 && refReporteRemitosCompletoBean.mostrarLista}" actionListener="#{refReporteRemitosCompletoBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteRemitosCompletoBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteRemitosCompletoBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteRemitosCompletoBean.pagina.numpage == 1}" rendered="#{refReporteRemitosCompletoBean.pagina.lastPage!=1 && refReporteRemitosCompletoBean.pagina.totalElements!=0  && refReporteRemitosCompletoBean.pagina.numpage != 1 && refReporteRemitosCompletoBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteRemitosCompletoBean.mostrarLista}" value="#{refReporteRemitosCompletoBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteRemitosCompletoBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteRemitosCompletoBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteRemitosCompletoBean.mostrarLista}" value="#{refReporteRemitosCompletoBean.pagina.totalElements}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false"/>	
		   		
		     	<%/*<h:outputLink  rendered="#{refReporteRemitosCompletoBean.nombreArchivo!='' && refReporteRemitosCompletoBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteRemitosCompletoBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	*/%>
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteRemitosCompletoBean.pagina.lastPage!=1 && refReporteRemitosCompletoBean.pagina.totalElements!=0 && refReporteRemitosCompletoBean.pagina.numpage != refReporteRemitosCompletoBean.pagina.lastPage && refReporteRemitosCompletoBean.mostrarLista}" actionListener="#{refReporteRemitosCompletoBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteRemitosCompletoBean.pagina.numpage == refReporteRemitosCompletoBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteRemitosCompletoBean.pagina.lastPage!=1 && refReporteRemitosCompletoBean.pagina.totalElements!=0 && refReporteRemitosCompletoBean.pagina.numpage != refReporteRemitosCompletoBean.pagina.lastPage && refReporteRemitosCompletoBean.mostrarLista}" actionListener="#{refReporteRemitosCompletoBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteRemitosCompletoBean.pagina.numpage == refReporteRemitosCompletoBean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 
		 
		<t:saveState value="#{refReporteRemitosCompletoBean.fltNroSucursal}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.fltNroReciboHasta}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.fltNroReciboDesde}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.fltClienteHasta}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.fltClienteDesde}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.fltFechaDesde}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.fltFechaHasta}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.items}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.nombreArchivo}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.condicion}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.condiciones}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.estadoRemito}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.estadosRemito}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.fltFechaDesdeDos}"></t:saveState>
		<t:saveState value="#{refReporteRemitosCompletoBean.fltFechaHastaDos}"></t:saveState>
				
		<t:saveState value="#{refReporteRemitosCompletoBean.verPrecioCimp}"></t:saveState>
		<% /* LISTADO */ %>
		<%/*
		<h:commandLink action="#{refReporteRemitosCompletoBean.exportHtmlTableAsExcel}">
    		 <h:outputText value=" #{Message.descargar_label}" />
		</h:commandLink>
		*/ %>
		


		<t:div  style="overflow:scroll; height:100%; width:700px;"  rendered="#{refReporteRemitosCompletoBean.mostrarLista}">  
	
		<h:panelGrid width="95%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>	
		<%/*
		 <t:buffer into="#{refReporteRemitosCompletoBean.nombreArchivo}">	
		 */ %>
				<t:dataTable value="#{refReporteRemitosCompletoBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaNumero,columnaTablaNumero,columnaTablaTexto,columnaTablaNumero,columnaTablaNumero,columnaTablaTexto,columnaTablaTexto,  columnaTablaNumero,columnaTablaTexto, columnaTablaTexto "
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteRemitosCompletoBean.mostrarLista}" 
							 >
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_label}"/>
						</f:facet>
						<h:outputText value="#{item.fecha} " >													
						</h:outputText>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.hora_label}"/>
						</f:facet>
						<h:outputText value="#{item.hora} " >						
						</h:outputText>
					</h:column>
								
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.ccss_label}"/>							
						</f:facet>
							<h:outputText value="#{item.ccss}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cod_cliente_Alfa}"/>
						</f:facet>
						<h:outputText value="#{item.codClienteAlfa} " />
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.cliDescripcion} " />
						
					</h:column>		
							
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.sucursal_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroSucursal} " />
						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_remito_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroRemito}"/>
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.producto_label}"/>
						</f:facet>
						<h:outputText value="#{item.descProducto}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cantidad_label}"/>
						</f:facet>
						<h:outputText value="#{item.litros}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>
					<%/*
					<h:column>
						<f:facet name="header">							
							<h:outputText value="#{Message.un_chofer_label}"/>
						</f:facet>
						<h:outputText value="#{item.descrGrupoUNC}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.un_camion_label}"/>							
						</f:facet>
							<h:outputText value="#{item.descrGrupoUNV}"/>
					</h:column>
					*/ %>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.total_pesos_label}"/>
						</f:facet>
						<h:outputText value="#{item.montoTotal}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>		
					
				
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.patente_label}"/>
						</f:facet>
						<h:outputText value="#{item.patente}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.chofer_label}"/>
						</f:facet>
						<h:outputText value="#{item.apellidoChofer}&nbsp; #{item.nombreChofer}" escape="false"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.condicion_label}"/>
						</f:facet>
						<h:outputText value="#{item.condicionDesc}" escape="false"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.estado_combustible_label}"/>
						</f:facet>
						<h:outputText value="#{item.facturadoString}" escape="false"/>
					</h:column>
					
							
				</t:dataTable>	
				<%/*
				</t:buffer>
				
				<h:outputText value="#{refReporteRemitosCompletoBean.nombreArchivo}" escape="false"/>
				*/ %>
				</h:column>
				</h:panelGrid>
			</t:div>
			
		
		
		
		
			<h:panelGrid columns="1" width="90%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteRemitosCompletoBean.volver}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		
		</h:column>
	</h:panelGrid>
	
	</h:form>
	
	
	<t:panelGrid>
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
						