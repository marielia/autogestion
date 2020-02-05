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
			
			
	
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0">
		<%@ include file="../header.jsp" %>
		
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteReciboBean.mostrarFrmLista}">	
		<t:saveState value="#{refReporteReciboBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteReciboBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_recibos}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>		
				
		<%/*FILTROS*/%>		
			<h:panelGrid columns="4" width="100%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
				</h:column>	
				<h:column></h:column>
				<h:column></h:column>
				<h:column></h:column>									
			</h:panelGrid>
			
			<h:panelGrid columns="2" width="100%" columnClasses="cpo8-25b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.entre_fechas_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="fechaDesde" value="#{refReporteReciboBean.fltFechaDesde}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
					
					<h:inputText id="fechaHasta" value="#{refReporteReciboBean.fltFechaHasta}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
			
			<%/*  clientes  */%>
			<h:panelGrid columns="2" width="100%" columnClasses="cpo8-25b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				<t:column rendered="#{sessionScope.usuario.tipo==0}">							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.cliente_label}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>
				<t:column rendered="#{sessionScope.usuario.tipo==0}">
					<h:selectOneListbox id="cliente" size="1" value="#{refReporteReciboBean.cliente}" styleClass="campo"  >
						<f:selectItems value="#{refReporteReciboBean.clientes}" />
					</h:selectOneListbox>						
				</t:column>	
				
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.desde_nro_recibo_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="nrorecibodesde" value="#{refReporteReciboBean.fltNroReciboDesde}"  size="12" maxlength="10" styleClass="campo">						
					</h:inputText>
					
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.al_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>							
					
					<h:inputText id="nrorecibohasta" value="#{refReporteReciboBean.fltNroReciboHasta}"  size="12" maxlength="10" styleClass="campo">						
					</h:inputText>				
				</t:column>	
							
			</h:panelGrid>	
			
			
			<%/*  anulado?  */%>
			<h:panelGrid columns="2" width="100%" columnClasses="cpo8-25b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				<t:column >							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.estado_anulado_label}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>
				<t:column >
					<h:selectBooleanCheckbox id="cambiarEstado" 
					   valueChangeListener="#{refReporteReciboBean.cambiarEstado}" 
					   immediate="true" 
					   onchange="submit()" /> 						
				</t:column>								
			</h:panelGrid>	
			
			

				
				
			
			<h:panelGrid columns="4" width="100%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>	</h:column>
				<h:column>	</h:column>
				<h:column>	</h:column>				
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0">						 
				     	<h:column>				     	
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteReciboBean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteReciboBean.pagina}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteReciboBean.pagina.lastPage!=1 && refReporteReciboBean.pagina.totalElements!=0 && refReporteReciboBean.pagina.numpage != 1 && refReporteReciboBean.mostrarLista}" actionListener="#{refReporteReciboBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteReciboBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteReciboBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteReciboBean.pagina.numpage == 1}" rendered="#{refReporteReciboBean.pagina.lastPage!=1 && refReporteReciboBean.pagina.totalElements!=0  && refReporteReciboBean.pagina.numpage != 1 && refReporteReciboBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteReciboBean.mostrarLista}" value="#{refReporteReciboBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteReciboBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteReciboBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteReciboBean.mostrarLista}" value="#{refReporteReciboBean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteReciboBean.nombreArchivo!='' && refReporteReciboBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteReciboBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteReciboBean.pagina.lastPage!=1 && refReporteReciboBean.pagina.totalElements!=0 && refReporteReciboBean.pagina.numpage != refReporteReciboBean.pagina.lastPage && refReporteReciboBean.mostrarLista}" actionListener="#{refReporteReciboBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteReciboBean.pagina.numpage == refReporteReciboBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteReciboBean.pagina.lastPage!=1 && refReporteReciboBean.pagina.totalElements!=0 && refReporteReciboBean.pagina.numpage != refReporteReciboBean.pagina.lastPage && refReporteReciboBean.mostrarLista}" actionListener="#{refReporteReciboBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteReciboBean.pagina.numpage == refReporteReciboBean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 
		 <t:saveState value="#{refReporteReciboBean.cliente}"></t:saveState>
		<t:saveState value="#{refReporteReciboBean.clientes}"></t:saveState>
		<t:saveState value="#{refReporteReciboBean.fltFechaDesde}"></t:saveState>
		<t:saveState value="#{refReporteReciboBean.fltFechaHasta}"></t:saveState>
		<t:saveState value="#{refReporteReciboBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteReciboBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteReciboBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteReciboBean.nombreArchivo}"></t:saveState>
		<t:saveState value="#{refReporteReciboBean.estadoRecibo}"></t:saveState>
		
		<% /* LISTADO */ %>
		
		<h:panelGrid width="100%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteReciboBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,  columnaTablaNumero,columnaTablaTexto, columnaTablaNumero,columnaTablaCentrada,columnaTablaCentrada, columnaTablaCentrada"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteReciboBean.mostrarLista}" >
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_label}"/>
						</f:facet>
						<h:outputText value="#{item.fecha}" escape="false"/>
						
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.numero_label}"/>
						</f:facet>
						<h:outputText value="#{item.numero} " />
						
					</h:column>
					
					<h:column rendered="#{sessionScope.usuario.tipo==0}">
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.cliDescripcion}"/>
					</h:column>	
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.monto_label}"/>
						</f:facet>
						<h:outputText value="#{item.montoTotal}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>
					
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.estado_label}"/>
						</f:facet>
						<h:outputText value="#{item.estadoRecibo}">					
						 </h:outputText>
					</h:column>
					
					
					<h:column rendered="#{refReporteReciboBean.estadoRecibo}">
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_anulado_label}"/>
						</f:facet>
						<h:outputText value="#{item.fechaBaja}"> </h:outputText>
					</h:column>
								
					<h:column rendered="#{!refReporteReciboBean.estadoRecibo}">
						<f:facet name="header">
							<h:outputText value="#{Message.facturas_label}"/>
						</f:facet>
						
					     <t:commandButton value="#{Message.ver_label}"
					     			   styleClass="botonSm" 
									   actionListener="#{refReporteReciboBean.verRemitos}" 								  
									   title="#{Message.ver_facturas_label}" >
									   <f:param name="nroOperCaja" id="nroOperCaja" value="#{item.nroOperCaja}"/>
									   <f:param name="codCliente" id="codCliente" value="#{item.codCliente}"/>									   
<%-- 									   <h:outputText value="#{Message.ver_label}" />  --%>
						</t:commandButton>					
					</h:column>	
					
							
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
		
			<h:panelGrid columns="1" width="100%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteReciboBean.volver}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		
		</h:column>
	</h:panelGrid>
	
	</h:form>
	
	<% /*LISTADO DE REMITOS*/ %>
	<h:form id="frmFiltroCuentaSecundario" rendered="#{refReporteReciboBean.mostrarFrmListaSecundaria}">	
	
		<c:if var="puedeIngresarS" test="${!refReporteReciboBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if> 	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>   		
				
		 <t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.listado_recibor_facturas_label}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION 	 */%>
		
		 
		 <t:saveState value="#{refReporteReciboBean.pagina}"></t:saveState>
		 <t:saveState value="#{refReporteReciboBean.paginaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteReciboBean.mostrarLista}"></t:saveState>
		 <t:saveState value="#{refReporteReciboBean.mostrarListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteReciboBean.mostrarFrmLista}"></t:saveState>
		 <t:saveState value="#{refReporteReciboBean.mostrarFrmListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteReciboBean.nombreArchivoSecundario}"></t:saveState>
	     <t:saveState value="#{refReporteReciboBean.fltFechaDesde}"></t:saveState>
         <t:saveState value="#{refReporteReciboBean.fltFechaHasta}"></t:saveState>
         
		 
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>  	 
	   		
		   		<h:commandButton rendered="#{refReporteReciboBean.paginaSecundaria.lastPage!=1 && refReporteReciboBean.paginaSecundaria.totalElements!=0 && refReporteReciboBean.paginaSecundaria.numpage != 1 && refReporteReciboBean.mostrarListaSecundaria}" actionListener="#{refReporteReciboBean.primeraSecundaria}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteReciboBean.paginaSecundaria.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteReciboBean.anteriorSecundaria}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteReciboBean.paginaSecundaria.numpage == 1}" rendered="#{refReporteReciboBean.paginaSecundaria.lastPage!=1 && refReporteReciboBean.paginaSecundaria.totalElements!=0  && refReporteReciboBean.paginaSecundaria.numpage != 1 && refReporteReciboBean.mostrarListaSecundaria}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteReciboBean.mostrarListaSecundaria}" value="#{refReporteReciboBean.paginaSecundaria.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteReciboBean.paginaSecundaria.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteReciboBean.mostrarListaSecundaria}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteReciboBean.mostrarListaSecundaria}" value="#{refReporteReciboBean.paginaSecundaria.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteReciboBean.nombreArchivoSecundario!='' && refReporteReciboBean.mostrarListaSecundaria}" target="_blank" styleClass="cpo7b" value="#{refReporteReciboBean.nombreArchivoSecundario}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteReciboBean.paginaSecundaria.lastPage!=1 && refReporteReciboBean.paginaSecundaria.totalElements!=0 && refReporteReciboBean.paginaSecundaria.numpage != refReporteReciboBean.paginaSecundaria.lastPage && refReporteReciboBean.mostrarListaSecundaria}" actionListener="#{refReporteReciboBean.siguienteSecundaria}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteReciboBean.paginaSecundaria.numpage == refReporteReciboBean.paginaSecundaria.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteReciboBean.paginaSecundaria.lastPage!=1 && refReporteReciboBean.paginaSecundaria.totalElements!=0 && refReporteReciboBean.paginaSecundaria.numpage != refReporteReciboBean.paginaSecundaria.lastPage && refReporteReciboBean.mostrarListaSecundaria}" actionListener="#{refReporteReciboBean.ultimaSecundaria}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteReciboBean.paginaSecundaria.numpage == refReporteReciboBean.paginaSecundaria.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
	
		 
		
		<% /* LISTADO */ %>
		
		<h:panelGrid width="90%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteReciboBean.subItemsNivel2}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,  columnaTablaTexto, columnaTablaTexto,columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaCentrada"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteReciboBean.mostrarListaSecundaria}" >
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_label}"/>
						</f:facet>
						<h:outputText value="#{item.fecha} " />
						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.tipo_label}"/>
						</f:facet>
						<h:outputText value="#{item.tipo}"/>
					</h:column>
					
					<h:column rendered="#{sessionScope.usuario.tipo==0}">
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.cliDescripcion}"/>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.prefijo_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroSucursal}"/>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.numero_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroFactura}"/>
						
					</h:column>		
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.monto_label}"/>
						</f:facet>
						<h:outputText value="#{item.netoFactura}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.monto_tot_label}"/>
						</f:facet>
						<h:outputText value="#{item.pagoAplicado}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.monto_adeudado_label}"/>
						</f:facet>
						<h:outputText value="#{item.saldo}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>								
								
				</t:dataTable>
		</h:column>
		</h:panelGrid>
		
		
			<h:panelGrid columns="1" width="90%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" actionListener="#{refReporteReciboBean.volverPrincipal}" styleClass="boton"/>	
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
						