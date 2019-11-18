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
		
	<h:form id="frmFiltroCuenta" rendered="#{refReportePercepcionDeIVABean.mostrarFrmLista}" >	
		<t:saveState value="#{refReportePercepcionDeIVABean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReportePercepcionDeIVABean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
   		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.listado_percepciones_label}"/>
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
					<h:inputText id="fechaDesde" value="#{refReportePercepcionDeIVABean.fltFechaDesde}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
					
					<h:inputText id="fechaHasta" value="#{refReportePercepcionDeIVABean.fltFechaHasta}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
			<h:panelGrid columns="2" width="80%" columnClasses="cpo8-25b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				<t:column>							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.cliente_label}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>
				<t:column>
					<h:selectOneListbox id="cliente" size="1" value="#{refReportePercepcionDeIVABean.cliente}" styleClass="campo"  >
						<f:selectItems value="#{refReportePercepcionDeIVABean.clientes}" />
					</h:selectOneListbox>						
				</t:column>				
				
				<%/* CENTRO DE SERVICIO CCSS 
				*/ %>
				<t:column>
					<h:outputLabel  styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.centro_servicio_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:selectOneListbox id="ccss" size="1" value="#{refReportePercepcionDeIVABean.ccss}" styleClass="campo">
						<f:selectItems value="#{refReportePercepcionDeIVABean.lstccss}" />
					</h:selectOneListbox>				
				</t:column>	
				
							
			</h:panelGrid>
			
			<h:panelGrid columns="1" width="80%" columnClasses="cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReportePercepcionDeIVABean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		<t:saveState value="#{refReportePercepcionDeIVABean.ccss}"></t:saveState>
		<t:saveState value="#{refReportePercepcionDeIVABean.lstccss}"></t:saveState>
		<t:saveState value="#{refReportePercepcionDeIVABean.cliente}"></t:saveState>
		<t:saveState value="#{refReportePercepcionDeIVABean.clientes}"></t:saveState>
		<t:saveState value="#{refReportePercepcionDeIVABean.fltFechaDesde}"></t:saveState>
		<t:saveState value="#{refReportePercepcionDeIVABean.fltFechaHasta}"></t:saveState>
		<t:saveState value="#{refReportePercepcionDeIVABean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReportePercepcionDeIVABean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReportePercepcionDeIVABean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReportePercepcionDeIVABean.nombreArchivo}"></t:saveState>
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReportePercepcionDeIVABean.pagina}"></t:saveState>
		<t:saveState value="#{refReportePercepcionDeIVABean.subItemsNivel1}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>
		   		<h:commandButton rendered="#{refReportePercepcionDeIVABean.pagina.lastPage!=1 && refReportePercepcionDeIVABean.pagina.totalElements!=0 && refReportePercepcionDeIVABean.pagina.numpage != 1 && refReportePercepcionDeIVABean.mostrarLista}" actionListener="#{refReportePercepcionDeIVABean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReportePercepcionDeIVABean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReportePercepcionDeIVABean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReportePercepcionDeIVABean.pagina.numpage == 1}" rendered="#{refReportePercepcionDeIVABean.pagina.lastPage!=1 && refReportePercepcionDeIVABean.pagina.totalElements!=0  && refReportePercepcionDeIVABean.pagina.numpage != 1 && refReportePercepcionDeIVABean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReportePercepcionDeIVABean.mostrarLista}" value="#{refReportePercepcionDeIVABean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReportePercepcionDeIVABean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReportePercepcionDeIVABean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReportePercepcionDeIVABean.mostrarLista}" value="#{refReportePercepcionDeIVABean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReportePercepcionDeIVABean.nombreArchivo!='' && refReportePercepcionDeIVABean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReportePercepcionDeIVABean.nombreArchivo}">
					<h:outputText value=" #{Message.ver_archivo_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReportePercepcionDeIVABean.pagina.lastPage!=1 && refReportePercepcionDeIVABean.pagina.totalElements!=0 && refReportePercepcionDeIVABean.pagina.numpage != refReportePercepcionDeIVABean.pagina.lastPage && refReportePercepcionDeIVABean.mostrarLista}" actionListener="#{refReportePercepcionDeIVABean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReportePercepcionDeIVABean.pagina.numpage == refReportePercepcionDeIVABean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReportePercepcionDeIVABean.pagina.lastPage!=1 && refReportePercepcionDeIVABean.pagina.totalElements!=0 && refReportePercepcionDeIVABean.pagina.numpage != refReportePercepcionDeIVABean.pagina.lastPage && refReportePercepcionDeIVABean.mostrarLista}" actionListener="#{refReportePercepcionDeIVABean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReportePercepcionDeIVABean.pagina.numpage == refReportePercepcionDeIVABean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 
		 
		
		<% /* LISTADO */ %>
		
		<h:panelGrid width="90%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReportePercepcionDeIVABean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaNumero,  columnaTablaTexto,columnaTablaNumero, columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaTexto, columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReportePercepcionDeIVABean.mostrarLista}" >
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cod_comprobante_label}"/>
						</f:facet>
						<h:outputText value="#{item.codComprobanteInt} " />
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_emision_comprobante_label}"/>
						</f:facet>
						<h:outputText value="#{item.fechaEmisionComprobante} " />
						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_compronate_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroSucursal}#{item.nroFactura}" />
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.importe_comprobante}"/>
						</f:facet>
						<h:outputText value="#{item.importe}">
						<f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cod_impuesto_label}"/>
						</f:facet>
						<h:outputText value="#{item.codImpuesto}"/>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cod_regimen_label}"/>
						</f:facet>
						<h:outputText value="#{item.codRegimen}"/>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cod_operacion_label}"/>
						</f:facet>
						<h:outputText value="#{item.codOperacion}"/>
					</h:column>	
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.base_calculo_label}"/>
						</f:facet>
						<h:outputText value="#{item.baseCalculo}">
						<f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_emision_retencion_label}"/>
						</f:facet>
						<h:outputText value="#{item.fechaEmisionRetencion}"/>
						
					</h:column>		
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cod_condicion_label}"/>
						</f:facet>
						<h:outputText value="#{item.codCondicion}"/>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.monto_retencion_label}"/>
						</f:facet>
						<h:outputText value="#{item.montoDeLaRetencion}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.porcentaje_retencion_label}"/>
						</f:facet>
						 <h:outputText value="#{item.porcExclusion}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>									
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
		
		
			<h:panelGrid columns="1" width="80%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReportePercepcionDeIVABean.volver}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
				
		</h:column>
	</h:panelGrid>
	
	</h:form>
	
	<% /*LISTADO DE REMITOS*/ %>
	<h:form id="frmFiltroCuentaSecundario" rendered="#{refReportePercepcionDeIVABean.mostrarFrmListaSecundaria}">	
	
		<c:if var="puedeIngresarS" test="${!refReportePercepcionDeIVABean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if> 	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>   		
				
		 <t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.listado_facturas_remitos_label}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION 	 */%>
		
		 
		 <t:saveState value="#{refReportePercepcionDeIVABean.pagina}"></t:saveState>
		 <t:saveState value="#{refReportePercepcionDeIVABean.paginaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReportePercepcionDeIVABean.mostrarLista}"></t:saveState>
		 <t:saveState value="#{refReportePercepcionDeIVABean.mostrarListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReportePercepcionDeIVABean.mostrarFrmLista}"></t:saveState>
		 <t:saveState value="#{refReportePercepcionDeIVABean.mostrarFrmListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReportePercepcionDeIVABean.nombreArchivoSecundario}"></t:saveState>
	     <t:saveState value="#{refReportePercepcionDeIVABean.fltFechaDesde}"></t:saveState>
      <t:saveState value="#{refReportePercepcionDeIVABean.fltFechaHasta}"></t:saveState>
		 
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>  	 
	   		
		   		<h:commandButton rendered="#{refReportePercepcionDeIVABean.paginaSecundaria.lastPage!=1 && refReportePercepcionDeIVABean.paginaSecundaria.totalElements!=0 && refReportePercepcionDeIVABean.paginaSecundaria.numpage != 1 && refReportePercepcionDeIVABean.mostrarListaSecundaria}" actionListener="#{refReportePercepcionDeIVABean.primeraSecundaria}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReportePercepcionDeIVABean.paginaSecundaria.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReportePercepcionDeIVABean.anteriorSecundaria}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReportePercepcionDeIVABean.paginaSecundaria.numpage == 1}" rendered="#{refReportePercepcionDeIVABean.paginaSecundaria.lastPage!=1 && refReportePercepcionDeIVABean.paginaSecundaria.totalElements!=0  && refReportePercepcionDeIVABean.paginaSecundaria.numpage != 1 && refReportePercepcionDeIVABean.mostrarListaSecundaria}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReportePercepcionDeIVABean.mostrarListaSecundaria}" value="#{refReportePercepcionDeIVABean.paginaSecundaria.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReportePercepcionDeIVABean.paginaSecundaria.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReportePercepcionDeIVABean.mostrarListaSecundaria}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReportePercepcionDeIVABean.mostrarListaSecundaria}" value="#{refReportePercepcionDeIVABean.paginaSecundaria.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReportePercepcionDeIVABean.nombreArchivoSecundario!='' && refReportePercepcionDeIVABean.mostrarListaSecundaria}" target="_blank" styleClass="cpo7b" value="#{refReportePercepcionDeIVABean.nombreArchivoSecundario}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReportePercepcionDeIVABean.paginaSecundaria.lastPage!=1 && refReportePercepcionDeIVABean.paginaSecundaria.totalElements!=0 && refReportePercepcionDeIVABean.paginaSecundaria.numpage != refReportePercepcionDeIVABean.paginaSecundaria.lastPage && refReportePercepcionDeIVABean.mostrarListaSecundaria}" actionListener="#{refReportePercepcionDeIVABean.siguienteSecundaria}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReportePercepcionDeIVABean.paginaSecundaria.numpage == refReportePercepcionDeIVABean.paginaSecundaria.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReportePercepcionDeIVABean.paginaSecundaria.lastPage!=1 && refReportePercepcionDeIVABean.paginaSecundaria.totalElements!=0 && refReportePercepcionDeIVABean.paginaSecundaria.numpage != refReportePercepcionDeIVABean.paginaSecundaria.lastPage && refReportePercepcionDeIVABean.mostrarListaSecundaria}" actionListener="#{refReportePercepcionDeIVABean.ultimaSecundaria}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReportePercepcionDeIVABean.paginaSecundaria.numpage == refReportePercepcionDeIVABean.paginaSecundaria.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
	
		 
		
		<% /* LISTADO */ %>
		
		<h:panelGrid width="90%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReportePercepcionDeIVABean.subItemsNivel2}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,  columnaTablaNumero,columnaTablaNumero, columnaTablaTexto, columnaTablaNumero, columnaTablaTexto,columnaTablaTexto, columnaTablaNumero,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReportePercepcionDeIVABean.mostrarListaSecundaria}" >				
					
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
				    		<t:commandButton value="#{Message.volver_label}" actionListener="#{refReportePercepcionDeIVABean.volverPrincipal}" styleClass="boton"/>	
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
						