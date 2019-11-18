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
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0">
		<%@ include file="../header.jsp" %>
		
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteDetalleDeFactura.mostrarFrmLista}">	
		<t:saveState value="#{refReporteDetalleDeFactura.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteDetalleDeFactura.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  rowClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_detalle_factura}"/>
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
			
			<%/* Fecha de emision*/ %>
			<h:panelGrid columns="1" width="90%" columnClasses="cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputText styleClass="campoNoEditable" value="&nbsp;&nbsp;&nbsp;#{Message.comparando_por_fecha_emision_label}" escape="false"/>
				</t:column>				
			</h:panelGrid>	
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8-25b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.entre_fechas_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="fechaDesde" value="#{refReporteDetalleDeFactura.fltFechaDesde}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
					
					<h:inputText id="fechaHasta" value="#{refReporteDetalleDeFactura.fltFechaHasta}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
			
			
			
			<%/* Fecha de vencimiento */ %>
			<h:panelGrid columns="1" width="90%" columnClasses="cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputText styleClass="campoNoEditable" value="&nbsp;&nbsp;&nbsp;#{Message.comparando_por_fecha_vencimiento_label}" escape="false"/>
				</t:column>				
			</h:panelGrid>	
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8-25b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.entre_fechas_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="fechaDesdeDos" value="#{refReporteDetalleDeFactura.fltFechaDesdeDos}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
					
					<h:inputText id="fechaHastaDos" value="#{refReporteDetalleDeFactura.fltFechaHastaDos}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8-25b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				<t:column >							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.cliente_label}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>
				<t:column >
					<h:selectOneListbox id="cliente" size="1" value="#{refReporteDetalleDeFactura.cliente}" styleClass="campo"  >
						<f:selectItems value="#{refReporteDetalleDeFactura.clientes}" />
					</h:selectOneListbox>						
				</t:column>	
											
			</h:panelGrid>			
			
			<h:panelGrid columns="4" width="90%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>	</h:column>
				<h:column>	</h:column>
				<h:column>	</h:column>				
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0">						 
				     	<h:column>				     	
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteDetalleDeFactura.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		
				
		<%/*PAGINACION*/ %>	
		<t:saveState value="#{refReporteFacturasBean.cliente}"></t:saveState>
		<t:saveState value="#{refReporteFacturasBean.clientes}"></t:saveState>
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteDetalleDeFactura.pagina}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteDetalleDeFactura.pagina.lastPage!=1 && refReporteDetalleDeFactura.pagina.totalElements!=0 && refReporteDetalleDeFactura.pagina.numpage != 1 && refReporteDetalleDeFactura.mostrarLista}" actionListener="#{refReporteDetalleDeFactura.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteDetalleDeFactura.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteDetalleDeFactura.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteDetalleDeFactura.pagina.numpage == 1}" rendered="#{refReporteDetalleDeFactura.pagina.lastPage!=1 && refReporteDetalleDeFactura.pagina.totalElements!=0  && refReporteDetalleDeFactura.pagina.numpage != 1 && refReporteDetalleDeFactura.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteDetalleDeFactura.mostrarLista}" value="#{refReporteDetalleDeFactura.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteDetalleDeFactura.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteDetalleDeFactura.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteDetalleDeFactura.mostrarLista}" value="#{refReporteDetalleDeFactura.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		
		   		<h:outputLink  rendered="#{refReporteDetalleDeFactura.nombreArchivo!='' && refReporteDetalleDeFactura.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteDetalleDeFactura.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
				
				
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteDetalleDeFactura.pagina.lastPage!=1 && refReporteDetalleDeFactura.pagina.totalElements!=0 && refReporteDetalleDeFactura.pagina.numpage != refReporteDetalleDeFactura.pagina.lastPage && refReporteDetalleDeFactura.mostrarLista}" actionListener="#{refReporteDetalleDeFactura.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteDetalleDeFactura.pagina.numpage == refReporteDetalleDeFactura.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteDetalleDeFactura.pagina.lastPage!=1 && refReporteDetalleDeFactura.pagina.totalElements!=0 && refReporteDetalleDeFactura.pagina.numpage != refReporteDetalleDeFactura.pagina.lastPage && refReporteDetalleDeFactura.mostrarLista}" actionListener="#{refReporteDetalleDeFactura.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteDetalleDeFactura.pagina.numpage == refReporteDetalleDeFactura.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 
		 
		<t:saveState value="#{refReporteDetalleDeFactura.fltFechaDesde}"></t:saveState>
		<t:saveState value="#{refReporteDetalleDeFactura.fltFechaDesde}"></t:saveState>
		<t:saveState value="#{refReporteDetalleDeFactura.fltFechaHasta}"></t:saveState>
		<t:saveState value="#{refReporteDetalleDeFactura.fltFechaDesdeDos}"></t:saveState>
		<t:saveState value="#{refReporteDetalleDeFactura.fltFechaHastaDos}"></t:saveState>
		<t:saveState value="#{refReporteDetalleDeFactura.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteDetalleDeFactura.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteDetalleDeFactura.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteDetalleDeFactura.nombreArchivo}"></t:saveState>
				
		<t:saveState value="#{refReporteDetalleDeFactura.saldoTotal}"></t:saveState>
		<t:saveState value="#{refReporteDetalleDeFactura.montoPagosAplicados}"></t:saveState>
		<t:saveState value="#{refReporteDetalleDeFactura.saldoFacturasYNotasDebito}"></t:saveState>
		
		<% /* LISTADO */%>
		
		
		<h:panelGrid width="90%" columns="1"  cellspacing="0" cellpadding="0" >						 
		<h:column>
		<%/* cliente */ %>
				<t:dataTable value="#{refReporteDetalleDeFactura.subItemsNivel1}" var="item2" 
									 rowClasses="fila2" 
									 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
									 cellspacing="2" border="0"
									 rendered="#{refReporteDetalleDeFactura.mostrarLista}" >
				
				<h:column>	
				<h:panelGrid width="100%" columns="1" columnClasses="bordeblanco" cellspacing="0" cellpadding="0" >						 
				<h:column>					
								<h:panelGrid width="100%" columns="1" 
								 cellspacing="0" cellpadding="0" 
								 columnClasses="subtitulosCli"
								
								 >				
									<h:column>							
										<h:outputText value="CLIENTE >> #{item2.cliente.codClienteAlfa} -  #{item2.cliente.descripcion}" escape=""/>						
									</h:column>
								</h:panelGrid>
				</h:column>		

				<h:column>
				
				<t:div rendered="#{refReporteDetalleDeFactura.mostrarLista && item2.muestraFactura==1}" >
				<h:panelGrid width="100%" columns="6" 
								 cellspacing="1" cellpadding="1" 
								 columnClasses="columnaTablaCentradaFijo,  columnaTablaCentradaFijo, columnaTablaCentradaFijo, columnaTablaCentradaFijo,columnaTablaCentradaFijo"
								 styleClass="subtitulosFact"						 						
								 >	
					<h:column>										
						<h:outputText value="#{Message.fecha_label}" />						
					</h:column>	
					
					<%/*
					<h:column>										
						<h:outputText value="#{Message.fecha_label}" />						
					</h:column>						
					*/ %>											
					<h:column>											
						<h:outputText value="#{Message.comprobante_label}"></h:outputText>
					</h:column>	
					
					<h:column>											
						<h:outputText value="#{Message.nro_comprobante_label}"></h:outputText>
					</h:column>						
					
					<h:column>	
						<h:outputText value="                 #{Message.monto_label}"></h:outputText>							
					</h:column>	
					<%/*
					<h:column>	
						<h:outputText value="#{Message.monto_tot_label}"></h:outputText>							
					</h:column>	
					*/ %>
					<h:column>
						<h:outputText value="                     #{Message.monto_adeudado_label}"></h:outputText>													
					</h:column>						 
		  	    </h:panelGrid>		
				</t:div>
						<t:dataTable value="#{item2.lstFacturas}" var="item" 
									 rowClasses="fila2" 
									 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="1" 
									 cellspacing="1" border="0"
									 rendered="#{refReporteDetalleDeFactura.mostrarLista && item2.muestraFactura==1}"
									 renderedIfEmpty="false" >
						<h:column>	
							<h:panelGrid width="100%" columns="1" columnClasses="bordeblanco" cellspacing="0" cellpadding="0" >						 
							<h:column>					
								<h:panelGrid width="100%" columns="6" 
								 cellspacing="1" cellpadding="0" 
								 columnClasses="columnaTablaTextoFijo,  columnaTablaTextoFijo, columnaTablaNumeroFijo, columnaTablaNumeroFijo,columnaTablaNumeroFijo"
								 >						
													
								<h:column>							
									<h:outputText value="#{item.fecha}" />						
								</h:column>
								
								<%/*
								<h:column>
									<h:outputText value="#{item.feVto}" ></h:outputText>
								</h:column>	
								*/ %>
								
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{Message.tipo_label}"/>
									</f:facet>
									<h:outputText value="#{item.tipo}" />
								</h:column>					
								
								
								<h:column>							
									<h:outputText value="#{item.nroSucursal}&nbsp; #{item.nroFactura}" escape="false"/>							
								</h:column>	
								
								<h:column>							
									<h:outputText value="#{item.total}">
									 <f:convertNumber type="currency" pattern="#,##0.00"/>
									 </h:outputText>
								</h:column>	
								
								<%/*
								<h:column>							
									<h:outputText value="#{item.pagoAplicado}">
									 <f:convertNumber type="currency" pattern="#,##0.00"/>
									 </h:outputText>
								</h:column>	
								**/ %>
								<h:column>							
									<h:outputText value="#{item.saldo}">
									 <f:convertNumber type="currency" pattern="#,##0.00"/>
									 </h:outputText>
								</h:column>	
								</h:panelGrid>
							</h:column>
							
							
							<%/* doc aplicados*/ %>
							<h:column>
														 
							  <t:dataTable value="#{item.lstDocumentoAplicados}" var="item1" 
									 rowClasses="fila1" 
									 columnClasses="columnaTablaTextoFijo,  columnaTablaTextoFijo, columnaTablaNumeroFijo, columnaTablaNumeroFijo,columnaTablaNumeroFijo"
									 headerClass="subtitulosDocAplicados" footerClass="footerTabla" width="100%" cellpadding="2" 
									 cellspacing="1" border="0" 
									 renderedIfEmpty="false"
									 >
							
								<h:column>							
									<f:facet name="header">
										<h:outputText value="#{Message.fecha_label}"/>
									</f:facet>								
									<h:outputText value="#{item1.fecha}" />						
								</h:column>						
																			
								<h:column>
								
									<f:facet name="header">
										<h:outputText value="#{Message.comprobante_label}"/>
									</f:facet>	
									
									<h:outputText value="#{item1.nombre}"></h:outputText>
								</h:column>	
								
								<h:column>		
								
									<f:facet name="header">
										<h:outputText value="#{Message.nro_comprobante_label}"/>
									</f:facet>
									
									<h:outputText value="#{item1.numero}"/>
									
									<f:facet name="footer">
										<h:outputText value="Total Pago Aplicado"/>						  
									</f:facet>
									
								</h:column>						
								
								<h:column>	
								
									<f:facet name="header">
										<h:outputText value="#{Message.monto_tot_label}"/>
									</f:facet>
									
									<h:outputText value="#{item1.bruto}">		
									 <f:convertNumber type="currency" pattern="#,##0.00"/>
									</h:outputText>
									
									<f:facet name="footer">								
										<h:outputText value="#{item.pagoAplicado}" >
										  <f:convertNumber type="currency" pattern="#,##0.00"/>
										</h:outputText>	
									</f:facet>
									
								</h:column>	
								
								
								<h:column>		
								
									<f:facet name="header">
										<h:outputText value="#{Message.monto_adeudado_label}"/>
									</f:facet>		
									
									<h:outputText value="#{item1.saldo}">
									 <f:convertNumber type="currency" pattern="#,##0.00"/>
									</h:outputText>
								</h:column>					
														
								</t:dataTable>
							</h:column> 
							
							
							</h:panelGrid>
							
							
						</h:column>			
						</t:dataTable>
					
					</h:column>	
					</h:panelGrid>
						
				</h:column>
				</t:dataTable>			
				</h:column>
				</h:panelGrid>
			
			
			<t:panelGrid>
				<t:column>
					<h:outputText value="&nbsp;" escape="false"/>
				</t:column>				
			</t:panelGrid>	
		
			<t:div rendered="#{refReporteDetalleDeFactura.mostrarLista}">
			<h:panelGrid columns="2" width="45%" columnClasses="cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="2">						 
				<h:column>
						<t:outputText value="&nbsp;&nbsp;#{Message.saldo_cta_cte_label}:" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>
					    <t:inputText readonly="true" value="#{refReporteDetalleDeFactura.saldoFacturasYNotasDebito}" size="15"  styleClass="alinaerADerechoMonto" >
							<f:convertNumber type="currency" pattern="#,##0.00"/>
						</t:inputText>
				</h:column>
				<h:column>
						<t:outputText value="&nbsp;&nbsp;#{Message.documentos_no_aplicados_label}:" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>						 
						<t:inputText readonly="true" value="#{refReporteDetalleDeFactura.montoPagosAplicados}" size="15"  styleClass="alinaerADerechoMonto" >
							<f:convertNumber type="currency" pattern="#,##0.00"/>
						</t:inputText>												
				</h:column>
				
				<h:column>
					      <t:outputLabel for="nombre" styleClass="cpo8">
							<t:outputText value="&nbsp;&nbsp;#{Message.saldo_total_cliente}:"  escape="false"/>
						  </t:outputLabel>
				</h:column>	
				<h:column>				     
						<t:inputText readonly="true" value="#{refReporteDetalleDeFactura.saldoTotal}" size="15"  styleClass="alinaerADerechoMonto" >
							<f:convertNumber type="currency" pattern="#,##0.00"/>
						</t:inputText>
				</h:column>	
			</h:panelGrid>
			</t:div>	
				
				
			<h:panelGrid columns="1" width="90%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteDetalleDeFactura.volver}" styleClass="boton"/>	
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
						