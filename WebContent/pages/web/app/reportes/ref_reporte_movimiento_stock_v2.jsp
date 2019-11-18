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
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0" onload="desaparece()">
		<%@ include file="../header.jsp" %>
		
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteMovimientoStock.mostrarFrmLista}"  >	
		<t:saveState value="#{refReporteMovimientoStock.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteMovimientoStock.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  rowClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText  value="#{Message.tit_informe_movimiento_stock_2_gestion}"/>
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
			
			<h:panelGrid columns="2" width="100%" columnClasses="cpo8-30b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.entre_fechas_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>
										
				</t:column>						
				
				
				<t:column >	
					
						<h:inputText id="fechaDesde" value="#{refReporteMovimientoStock.fltFechaDesde}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
						
						<h:inputText id="fechaHasta" value="#{refReporteMovimientoStock.fltFechaHasta}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
			
			
			
			<h:panelGrid columns="2" width="100%" columnClasses="cpo8-30b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
		
				<%/* PRODUCTO
				*/ %>
				<t:column>							
					<t:outputLabel  styleClass="cpo8">
						<t:outputText value="&nbsp;&nbsp;#{Message.producto_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<h:selectOneListbox id="producto" size="1" value="#{refReporteMovimientoStock.producto}" styleClass="campo" immediate="true" >
						<f:selectItems value="#{refReporteMovimientoStock.productos}" />
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
					<h:selectOneListbox id="ccss" size="1" value="#{refReporteMovimientoStock.ccss}" styleClass="campo">
						<f:selectItems value="#{refReporteMovimientoStock.lstccss}" />
					</h:selectOneListbox>				
				</t:column>	
													
			</h:panelGrid>
						
			
			<h:panelGrid columns="4" width="100%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>	</h:column>
				<h:column>	</h:column>
				<h:column>	</h:column>				
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0">						 
				     	<h:column>				     	
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteMovimientoStock.buscarMejorado}" styleClass="boton" onclick="aparece()"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
		<t:panelGrid width="90%" columnClasses="columnaTablaTexto" columns="1" cellspacing="0" cellpadding="0">
		<t:column>
		    <t:div styleClass="cpo8n">
		     <t:graphicImage value="/img/info.ico" alt="#{Message.mensaje_mayor_contable_archivo}" width="12px" height="12px"/>
			 <h:outputText value="&nbsp;#{Message.mensaje_mayor_contable_archivo}" escape="false"/>
			</t:div>
		</t:column>
		<t:column rendered="#{refReporteMovimientoStock.mensaje2000Registros!=''}">
		    <t:div styleClass="cpo8nVerde">
		 	 <h:outputText value="&nbsp;#{refReporteMovimientoStock.mensaje2000Registros}" escape="false"/>
			</t:div>
		</t:column>		
		</t:panelGrid>
			
		
		<%/* BARRA DE PROGRESO */ %>	
		 <t:div id="puntero" style="display:none;">		    
		      <t:panelGrid border="0" cellpadding="0" cellspacing="0" 	 columns="1" 
			     columnClasses="columnaTablaCentrada" width="100%" rowClasses="filaTablaCabecera">
				<h:column>
						<f:verbatim>	
						<iframe id="imagenPdf" name="imagenPdf" align='center' frameborder="0" marginheight="0" marginwidth="0" height='50px' width='50px' scrolling="no" src="/refipass/pages/web/app/barraProgreso.jsf">
						</iframe>
						</f:verbatim>		
				</h:column>
				<h:column >			
					<h:outputLabel  styleClass="datoNegrita">
					 <h:outputText id="mensajePunteroPdf" styleClass="errorNegro" value="Se est� generando el informe. Por favor espere." escape="false"/>		
					 </h:outputLabel>				
				</h:column>						
			</t:panelGrid>			
		</t:div> 	
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteMovimientoStock.pagina}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteMovimientoStock.pagina.lastPage!=1 && refReporteMovimientoStock.pagina.totalElements!=0 && refReporteMovimientoStock.pagina.numpage != 1 && refReporteMovimientoStock.mostrarLista}" actionListener="#{refReporteMovimientoStock.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteMovimientoStock.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteMovimientoStock.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteMovimientoStock.pagina.numpage == 1}" rendered="#{refReporteMovimientoStock.pagina.lastPage!=1 && refReporteMovimientoStock.pagina.totalElements!=0  && refReporteMovimientoStock.pagina.numpage != 1 && refReporteMovimientoStock.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteMovimientoStock.mostrarLista}" value="#{refReporteMovimientoStock.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteMovimientoStock.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteMovimientoStock.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteMovimientoStock.mostrarLista}" value="#{refReporteMovimientoStock.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteMovimientoStock.nombreArchivo!='' && refReporteMovimientoStock.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteMovimientoStock.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteMovimientoStock.pagina.lastPage!=1 && refReporteMovimientoStock.pagina.totalElements!=0 && refReporteMovimientoStock.pagina.numpage != refReporteMovimientoStock.pagina.lastPage && refReporteMovimientoStock.mostrarLista}" actionListener="#{refReporteMovimientoStock.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteMovimientoStock.pagina.numpage == refReporteMovimientoStock.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteMovimientoStock.pagina.lastPage!=1 && refReporteMovimientoStock.pagina.totalElements!=0 && refReporteMovimientoStock.pagina.numpage != refReporteMovimientoStock.pagina.lastPage && refReporteMovimientoStock.mostrarLista}" actionListener="#{refReporteMovimientoStock.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteMovimientoStock.pagina.numpage == refReporteMovimientoStock.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 
		<t:saveState value="#{refReporteMovimientoStock.ccss}"></t:saveState>
		 <t:saveState value="#{refReporteMovimientoStock.lstccss}"></t:saveState>
		 <t:saveState value="#{refReporteMovimientoStock.lstArchivos}"></t:saveState>
		 
		 <t:saveState value="#{refReporteMovimientoStock.productos}"></t:saveState>
		<t:saveState value="#{refReporteMovimientoStock.producto}"></t:saveState>
		
		<t:saveState value="#{refReporteMovimientoStock.fltFechaDesde}"></t:saveState>
		<t:saveState value="#{refReporteMovimientoStock.fltFechaHasta}"></t:saveState>
		<t:saveState value="#{refReporteMovimientoStock.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteMovimientoStock.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteMovimientoStock.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteMovimientoStock.nombreArchivo}"></t:saveState>
		
		
		
		<% /* LISTADO */%>
		
		
		<h:panelGrid width="100%" columns="1" cellspacing="0" cellpadding="0" >						 
		<h:column>		
	
    
				<t:dataTable value="#{refReporteMovimientoStock.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,columnaTablaNumero,columnaTablaNumero,columnaTablaTexto,columnaTablaNumeroFijo100,columnaTablaNumeroFijo100,columnaTablaTexto,columnaTablaTexto"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteMovimientoStock.mostrarLista}" id="pruebaexcel" >
					
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.ccss_label}"/>
						</f:facet>
						<h:outputText value="#{item.descrCCSS}" />
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_sucursal_label}"/>
						</f:facet>
						<h:outputText value="#{item.sucursal}" />
						
					</h:column>	
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_compronate_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroComprobante}" />
						
					</h:column>	
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.producto_label}"/>
						</f:facet>
						<h:outputText value="#{item.descrProducto}" />
						
					</h:column>	
									
				
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.litros_label}"/>
						</f:facet>
						
						<h:outputText value="#{item.litros}" >
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						
					</h:column>	
					
										
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.saldo_litros_label}"/>
						</f:facet>
						 <% /* h:outputText value="#{item.saldoLitros}" rendered="#{item.ultimo==1}" styleClass="campo8">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText*/%>
						 
						  <h:outputText value="#{item.saldoLitros}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText >
						
					</h:column>					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_de_movimiento_label}"/>
						</f:facet>
						<h:outputText value="#{item.fechaMovimiento}" />
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.tipo_de_movimiento_label}"/>
						</f:facet>
						<h:outputText value="#{item.descrMovimiento}" />
						
					</h:column>	
														
							
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
				
			
				
				
			<h:panelGrid columns="1" width="100%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteMovimientoStock.volver}" styleClass="boton"/>	
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
						