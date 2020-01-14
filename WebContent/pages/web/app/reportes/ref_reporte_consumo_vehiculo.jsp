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
			
			
	
	function validarCamposConsumo(){		
		var fechaDesde =document.getElementById("frmFiltroCuenta:fechaDesde");
		var fechaHasta =document.getElementById("frmFiltroCuenta:fechaHasta");	
		
		if(fechaDesde.value=="" || fechaHasta.value==""){
	  	  fechaDesde.focus();
		  alert("Debe ingresar Fecha Desde y Fecha Hasta de la consulta.");
		  return false;	
		}else{
		  return true;	
		}	
					
	}	
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0">
		<%@ include file="../header.jsp" %>
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteConsumosVehiculos.mostrarFrmLista}"  onsubmit="return validarCamposConsumo();">	
		<t:saveState value="#{refReporteConsumosVehiculos.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteConsumosVehiculos.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
   		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_consumo_vehiculos}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
				
		<%/*FILTROS*/%>
		
			<h:panelGrid columns="4" width="80%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
				</h:column>	
				<h:column></h:column>
				<h:column></h:column>
				<h:column></h:column>									
			</h:panelGrid>
			
			<h:panelGrid columns="2" width="80%" columnClasses="cpo8-25b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.entre_fechas_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="fechaDesde" value="#{refReporteConsumosVehiculos.fltFechaDesde}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
					
					<h:inputText id="fechaHasta" value="#{refReporteConsumosVehiculos.fltFechaHasta}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
			
			<%/*  clientes  */%>
			<h:panelGrid columns="2" width="80%" columnClasses="cpo8-25b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				<t:column rendered="#{sessionScope.usuario.tipo==0}">							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.cliente_label}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>
				<t:column rendered="#{sessionScope.usuario.tipo==0}">
					<h:selectOneListbox id="cliente" size="1" value="#{refReporteConsumosVehiculos.cliente}" styleClass="campo"  
						 onchange="submit();" immediate="true" 
						 valueChangeListener="#{refReporteConsumosVehiculos.cargarSusGrupos}">
						<f:selectItems value="#{refReporteConsumosVehiculos.clientes}" />
					</h:selectOneListbox>						
				</t:column>	
							
			</h:panelGrid>
			
			<h:panelGrid columns="4" width="80%" columnClasses="cpo8-25b,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
						<t:outputText value="&nbsp;&nbsp;#{Message.grupo_unidad_negocio_abreviado_label}" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>
				<%/*	
						<t:inputText id="descripcionGrupoNegocio" value="#{refReporteConsumosVehiculos.fltDescripcionGrupoUN}" size="20" maxlength="20" styleClass="campo" />
						*/ %>
						<h:selectOneListbox id="gruposUnidadNegocio" size="1" value="#{refReporteConsumosVehiculos.grupoUnidadNegocio}" styleClass="campo" 
									   	onchange="submit();" immediate="true" 
										valueChangeListener="#{refReporteConsumosVehiculos.cargarUnidadesNegocio}" >					
						<f:selectItems value="#{refReporteConsumosVehiculos.gruposUnidadNegocio}" />
						</h:selectOneListbox>
	
				</h:column>
				<h:column>
						
						<t:outputText value="&nbsp;&nbsp;#{Message.unidad_negocio_label}" styleClass="cpo8" escape="false"/>
					
										
				</h:column>
				<h:column>	
				<%/*				
						<t:inputText id="descripcionUnidadNegocio" value="#{refReporteConsumosVehiculos.fltDescripcionUnidadNegocio}" size="20" maxlength="20" styleClass="campo" />
						*/ %>
						<h:selectOneListbox id="unidadesNegocio" size="1" value="#{refReporteConsumosVehiculos.unidadNegocio}"  styleClass="campo"
						immediate="true" >
							<f:selectItems value="#{refReporteConsumosVehiculos.unidadesNegocio}"/>
						</h:selectOneListbox>
				</h:column>
				
				<h:column>
					      <t:outputLabel for="nombre" styleClass="cpo8">
							<t:outputText value="&nbsp;&nbsp;#{Message.patente_label}"  escape="false"/>
						  </t:outputLabel>
				</h:column>	
				<h:column>				     
						<t:inputText id="nombreChofer" value="#{refReporteConsumosVehiculos.fltPatente}" size="20" maxlength="50" styleClass="campo" />
				</h:column>	
			</h:panelGrid>
			
			
			
			<h:panelGrid columns="4" width="80%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
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
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteConsumosVehiculos.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteConsumosVehiculos.pagina}"></t:saveState>
		<t:saveState value="#{refReporteConsumosVehiculos.subItemsNivel1}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>
		   		<h:commandButton rendered="#{refReporteConsumosVehiculos.pagina.lastPage!=1 && refReporteConsumosVehiculos.pagina.totalElements!=0 && refReporteConsumosVehiculos.pagina.numpage != 1 && refReporteConsumosVehiculos.mostrarLista}" actionListener="#{refReporteConsumosVehiculos.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteConsumosVehiculos.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteConsumosVehiculos.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteConsumosVehiculos.pagina.numpage == 1}" rendered="#{refReporteConsumosVehiculos.pagina.lastPage!=1 && refReporteConsumosVehiculos.pagina.totalElements!=0  && refReporteConsumosVehiculos.pagina.numpage != 1 && refReporteConsumosVehiculos.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteConsumosVehiculos.mostrarLista}" value="#{refReporteConsumosVehiculos.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteConsumosVehiculos.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteConsumosVehiculos.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteConsumosVehiculos.mostrarLista}" value="#{refReporteConsumosVehiculos.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteConsumosVehiculos.nombreArchivo!='' && refReporteConsumosVehiculos.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteConsumosVehiculos.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteConsumosVehiculos.pagina.lastPage!=1 && refReporteConsumosVehiculos.pagina.totalElements!=0 && refReporteConsumosVehiculos.pagina.numpage != refReporteConsumosVehiculos.pagina.lastPage && refReporteConsumosVehiculos.mostrarLista}" actionListener="#{refReporteConsumosVehiculos.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteConsumosVehiculos.pagina.numpage == refReporteConsumosVehiculos.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteConsumosVehiculos.pagina.lastPage!=1 && refReporteConsumosVehiculos.pagina.totalElements!=0 && refReporteConsumosVehiculos.pagina.numpage != refReporteConsumosVehiculos.pagina.lastPage && refReporteConsumosVehiculos.mostrarLista}" actionListener="#{refReporteConsumosVehiculos.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteConsumosVehiculos.pagina.numpage == refReporteConsumosVehiculos.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 
		 <t:saveState value="#{refReporteConsumosVehiculos.grupoUnidadNegocio}"></t:saveState>
		<t:saveState value="#{refReporteConsumosVehiculos.gruposUnidadNegocio}"></t:saveState>
		<t:saveState value="#{refReporteConsumosVehiculos.unidadNegocio}"></t:saveState>
		<t:saveState value="#{refReporteConsumosVehiculos.unidadesNegocio}"></t:saveState>
		<t:saveState value="#{refReporteConsumosVehiculos.fltPatente}"></t:saveState>		
		<t:saveState value="#{refReporteConsumosVehiculos.cliente}"></t:saveState>
		<t:saveState value="#{refReporteConsumosVehiculos.clientes}"></t:saveState>
		<t:saveState value="#{refReporteConsumosVehiculos.fltFechaDesde}"></t:saveState>
		<t:saveState value="#{refReporteConsumosVehiculos.fltFechaHasta}"></t:saveState>
		<t:saveState value="#{refReporteConsumosVehiculos.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteConsumosVehiculos.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteConsumosVehiculos.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteConsumosVehiculos.nombreArchivo}"></t:saveState>
		
		<% /* LISTADO */ %>
		
		<h:panelGrid width="80%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteConsumosVehiculos.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,  columnaTablaTexto,columnaTablaTexto, columnaTablaTexto,columnaTablaNumero,columnaTablaNumero,columnaTablaTexto,columnaTablaCentrada"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteConsumosVehiculos.mostrarLista}" >
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.patente_label}"/>
						</f:facet>
						<h:outputText value="#{item.dominio} " />
						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.unidad_negocio_label}"/>
						</f:facet>
						<h:outputText value="#{item.descrUnidadNegocio}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.grupo_unidad_negocio_label}"/>
						</f:facet>
						<h:outputText value="#{item.descrGrupoUN}"/>
					</h:column>	
					
					<h:column rendered="#{sessionScope.usuario.tipo==0}">
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.cliDescripcion}"/>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cantidad_label}"/>
						</f:facet>
						<h:outputText value="#{item.consumoLitros}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>		
					
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
							<h:outputText value="#{Message.producto_label}"/>
						</f:facet>
						<h:outputText value="#{item.descProducto}"/>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.remitos_label}"/>
						</f:facet>
					     <t:commandLink styleClass="linkOperacion" 
									   actionListener="#{refReporteConsumosVehiculos.verRemitos}" 								  
									   title="#{Message.ver_remitos_label}" >
									   <f:param name="nroPatente" id="nroPatente" value="#{item.dominio}"/>									   
									   <f:param name="nroCliente" id="nroCliente" value="#{item.codClienteInt}"/>									   
									   <f:param name="nroProducto" id="nroProducto" value="#{item.codProducto}"/>
									   <f:param name="nroUnidNeg" id="nroUnidNeg" value="#{item.codUnidadNegocio}"/>
									   <f:param name="nroGrupoUnidNeg" id="nroGrupoUnidNeg" value="#{item.codGrupoUN}"/>
									   <h:outputText value="#{Message.ver_label}" /> 
						</t:commandLink>
						</h:column>	
								
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
	</h:column>
	</h:panelGrid>
	
	</h:form>
	
	<h:form id="frmFiltroCuenta1" rendered="#{refReporteConsumosVehiculos.mostrarFrmLista}" >	
		<t:saveState value="#{refReporteConsumosVehiculos.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteConsumosVehiculos.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>		
		
			<h:panelGrid columns="1" width="80%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteConsumosVehiculos.volver}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
				
		</h:column>
	</h:panelGrid>
	
	</h:form>
	
	<% /*LISTADO DE REMITOS*/ %>
	<h:form id="frmFiltroCuentaSecundario" rendered="#{refReporteConsumosVehiculos.mostrarFrmListaSecundaria}">	
	
		<c:if var="puedeIngresarS" test="${!refReporteConsumosVehiculos.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if> 	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>   		
				
		 <t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_consumo_vehiculos_remitos}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION 	 */%>
		
		 
		 <t:saveState value="#{refReporteConsumosVehiculos.pagina}"></t:saveState>
		 <t:saveState value="#{refReporteConsumosVehiculos.paginaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteConsumosVehiculos.mostrarLista}"></t:saveState>
		 <t:saveState value="#{refReporteConsumosVehiculos.mostrarListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteConsumosVehiculos.mostrarFrmLista}"></t:saveState>
		 <t:saveState value="#{refReporteConsumosVehiculos.mostrarFrmListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteConsumosVehiculos.nombreArchivoSecundario}"></t:saveState>
		  <t:saveState value="#{refReporteConsumosVehiculos.fltPatente}"></t:saveState>
		   <t:saveState value="#{refReporteConsumosVehiculos.fltDescripcionUnidadNegocio}"></t:saveState>
		    <t:saveState value="#{refReporteConsumosVehiculos.fltDescripcionGrupoUN}"></t:saveState>
		     <t:saveState value="#{refReporteConsumosVehiculos.fltFechaDesde}"></t:saveState>
		      <t:saveState value="#{refReporteConsumosVehiculos.fltFechaHasta}"></t:saveState>
		 
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>  	 
	   		
		   		<h:commandButton rendered="#{refReporteConsumosVehiculos.paginaSecundaria.lastPage!=1 && refReporteConsumosVehiculos.paginaSecundaria.totalElements!=0 && refReporteConsumosVehiculos.paginaSecundaria.numpage != 1 && refReporteConsumosVehiculos.mostrarListaSecundaria}" actionListener="#{refReporteConsumosVehiculos.primeraSecundaria}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteConsumosVehiculos.paginaSecundaria.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteConsumosVehiculos.anteriorSecundaria}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteConsumosVehiculos.paginaSecundaria.numpage == 1}" rendered="#{refReporteConsumosVehiculos.paginaSecundaria.lastPage!=1 && refReporteConsumosVehiculos.paginaSecundaria.totalElements!=0  && refReporteConsumosVehiculos.paginaSecundaria.numpage != 1 && refReporteConsumosVehiculos.mostrarListaSecundaria}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteConsumosVehiculos.mostrarListaSecundaria}" value="#{refReporteConsumosVehiculos.paginaSecundaria.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteConsumosVehiculos.paginaSecundaria.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteConsumosVehiculos.mostrarListaSecundaria}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteConsumosVehiculos.mostrarListaSecundaria}" value="#{refReporteConsumosVehiculos.paginaSecundaria.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteConsumosVehiculos.nombreArchivoSecundario!='' && refReporteConsumosVehiculos.mostrarListaSecundaria}" target="_blank" styleClass="cpo7b" value="#{refReporteConsumosVehiculos.nombreArchivoSecundario}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteConsumosVehiculos.paginaSecundaria.lastPage!=1 && refReporteConsumosVehiculos.paginaSecundaria.totalElements!=0 && refReporteConsumosVehiculos.paginaSecundaria.numpage != refReporteConsumosVehiculos.paginaSecundaria.lastPage && refReporteConsumosVehiculos.mostrarListaSecundaria}" actionListener="#{refReporteConsumosVehiculos.siguienteSecundaria}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteConsumosVehiculos.paginaSecundaria.numpage == refReporteConsumosVehiculos.paginaSecundaria.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteConsumosVehiculos.paginaSecundaria.lastPage!=1 && refReporteConsumosVehiculos.paginaSecundaria.totalElements!=0 && refReporteConsumosVehiculos.paginaSecundaria.numpage != refReporteConsumosVehiculos.paginaSecundaria.lastPage && refReporteConsumosVehiculos.mostrarListaSecundaria}" actionListener="#{refReporteConsumosVehiculos.ultimaSecundaria}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteConsumosVehiculos.paginaSecundaria.numpage == refReporteConsumosVehiculos.paginaSecundaria.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
	
		 
		
		<% /* LISTADO */ %>
		
		<h:panelGrid width="90%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteConsumosVehiculos.subItemsNivel2}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto, columnaTablaTexto,  columnaTablaNumero,columnaTablaNumero, columnaTablaTexto, columnaTablaNumero, columnaTablaTexto,columnaTablaTexto, columnaTablaNumero,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteConsumosVehiculos.mostrarListaSecundaria}" >				
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_label}"/>
						</f:facet>
						<h:outputText value="#{item.fecha} " >						
							<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
						</h:outputText>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.hora_label}"/>
						</f:facet>
						<h:outputText value="#{item.hora}" >						
						</h:outputText>
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
							<h:outputText value="#{Message.ccss_label}"/>							
						</f:facet>
							<h:outputText value="#{item.ccss}"/>
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
						<h:outputText value="#{item.apellidoChofer},&nbsp; #{item.nombreChofer}" escape="false"/>
					</h:column>
					
					
								
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
		
		
			<h:panelGrid columns="1" width="90%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" actionListener="#{refReporteConsumosVehiculos.volverPrincipal}" styleClass="boton"/>	
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
						