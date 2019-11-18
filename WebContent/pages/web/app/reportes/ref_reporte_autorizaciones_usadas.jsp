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
		
		
	<h:form id="frmFiltroCuenta" rendered="#{mesaDeAyudaBean.mostrarFrmLista}">	
		
		<t:saveState value="#{mesaDeAyudaBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!mesaDeAyudaBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  rowClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_reporte_autorizaciones_label}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>		
				
		<%/*FILTROS*/%>		
			<h:panelGrid columns="1" width="95%" columnClasses="cpo7-7" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			<h:column>
				<t:outputLabel for="consulte" styleClass="titulos">
					<h:outputText  value="&nbsp;&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
				</t:outputLabel>
			</h:column>	
			</h:panelGrid>
		
			<h:panelGrid columns="4" width="95%" columnClasses="cpo7-7,cpo7-7,cpo7-7,cpo7-7" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
									
			<%/*  vendedor  */%>
				<t:column>							
					<t:outputLabel  styleClass="cpo7-7">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.vendedor_ccs_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<h:selectOneListbox id="vendedores" size="1" value="#{mesaDeAyudaBean.vendedor}" styleClass="campo" immediate="true" >					
						<f:selectItems value="#{mesaDeAyudaBean.vendedores}" />
					</h:selectOneListbox>
						
				</t:column>	
				
				
				<%/*  ccss  */%>
				<t:column>							
					<t:outputLabel  styleClass="cpo7-7">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.centro_servicio_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<h:selectOneListbox id="ccss" size="1" value="#{mesaDeAyudaBean.ccss}" styleClass="campo"
						onchange="submit();" immediate="true" 
						 valueChangeListener="#{mesaDeAyudaBean.cargarProductosCCSS}">
						<f:selectItems value="#{mesaDeAyudaBean.lstccss}" />
					</h:selectOneListbox>
					
				</t:column>	
				
				<%/*  clientes  */%>
				<t:column>							
					<t:outputLabel  styleClass="cpo7-7">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.cliente_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<h:selectOneListbox id="cliente" size="1" value="#{mesaDeAyudaBean.cliente}" styleClass="campo" 
						 onchange="submit();" immediate="true" 
						 valueChangeListener="#{mesaDeAyudaBean.cargarSusCombosAsociados}">
						<f:selectItems value="#{mesaDeAyudaBean.clientes}" />
					</h:selectOneListbox>	
					
				</t:column>	
				<h:column></h:column>
				<h:column></h:column>	
				<%/*  chofer  */%>
				<t:column>							
					<t:outputLabel  styleClass="cpo7-7">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.chofer_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<h:selectOneListbox id="chofer" size="1" value="#{mesaDeAyudaBean.chofer}" styleClass="campo"  immediate="true">
						<f:selectItems value="#{mesaDeAyudaBean.choferes}" />
					</h:selectOneListbox>	
					
						
				</t:column>		
				
				
				<%/*  patente  */%>
				<t:column>							
					<t:outputLabel  styleClass="cpo7-7">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.patente_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<h:selectOneListbox id="patente" size="1" value="#{mesaDeAyudaBean.patente}" styleClass="campo" 
					 onchange="submit();" immediate="true" 
					 valueChangeListener="#{mesaDeAyudaBean.cargarLimiteCarga}" >
						<f:selectItems value="#{mesaDeAyudaBean.patentes}" />
					</h:selectOneListbox>
						
					
				</t:column>	
				
				<%/*  motivos de autorizacion  */%>
				<t:column>							
					<t:outputLabel  styleClass="cpo7-7">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.motivo_autorizacion_corto_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<h:selectOneListbox id="motivoAutorizacion" size="1" value="#{mesaDeAyudaBean.motivoAutorizacion}" styleClass="campo" onchange="submit();" immediate="true" 
					 valueChangeListener="#{mesaDeAyudaBean.cargarSusDescripciones}" >
						<f:selectItems value="#{mesaDeAyudaBean.motivosAutorizacion}" />
					</h:selectOneListbox>	
					
				</t:column>	
				
				<%/*  descripcion de autorizacion  */%>
				<t:column>							
					<t:outputLabel  styleClass="cpo7-7">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.descripcion_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<h:selectOneListbox id="descripcionMotAutorizacion" size="1" value="#{mesaDeAyudaBean.descripcionMotAutorizacion}" styleClass="campo" immediate="true">
						<f:selectItems value="#{mesaDeAyudaBean.descripcionesMotAutorizacion}" />
					</h:selectOneListbox>
					
				</t:column>
				
				<%/*  emisor 
				<t:column rendered="#{sessionScope.usuario.codigoRol==1}">	*/%>
				<t:column>						
					<t:outputLabel  styleClass="cpo7-7">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.emisor_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<%/* <t:column rendered="#{sessionScope.usuario.codigoRol==1}">		*/ %>
				<t:column>		
					<h:selectOneListbox id="empleado" size="1" value="#{mesaDeAyudaBean.empleado}" styleClass="campo" immediate="true">
						<f:selectItems value="#{mesaDeAyudaBean.empleados}" />
					</h:selectOneListbox>					
				</t:column>	
			</h:panelGrid>
			
			<h:panelGrid columns="1" width="95%" columnClasses="cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputText styleClass="campoNoEditable" value="&nbsp;&nbsp;&nbsp;#{Message.comparando_fecha_pedido_label}" escape="false"/>
				</t:column>
				<t:column/>
				<t:column/>
				<t:column/>
		</h:panelGrid>
			
		<h:panelGrid columns="3" width="95%" columnClasses="cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo7-7">
						<h:outputText  value="&nbsp;&nbsp;&nbsp;#{Message.entre_fechas_label}" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="fltFechaDesde" value="#{mesaDeAyudaBean.fltFechaDesde}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
						<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
					</h:inputText>	
					<h:outputText value="&nbsp;" escape="false"></h:outputText>					
					<h:outputLink value="javascript:doCalendario4('Calendar4','frmFiltroCuenta:fltFechaDesde');">
					<h:graphicImage style="border: none; vertical-align: middle;" value="/img/calendario.gif" width="25px" height="20"/>
					</h:outputLink>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					<t:outputText value="(#{Message.fecha_mascara_label})"  styleClass="campoNoEditable"/>
										
					
					<h:outputLabel for="estado" styleClass="cpo7-7">
						<h:outputText  value="&nbsp;&nbsp;#{Message.al_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>							
					
					<h:inputText id="fltFechaHasta" value="#{mesaDeAyudaBean.fltFechaHasta}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
						<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
					</h:inputText>	
					<h:outputText value="&nbsp;" escape="false"></h:outputText>					
					<h:outputLink value="javascript:doCalendario5('Calendar5','frmFiltroCuenta:fltFechaHasta');">
					<h:graphicImage style="border: none; vertical-align: middle;" value="/img/calendario.gif" width="25px" height="20"/>
					</h:outputLink>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					<t:outputText value="(#{Message.fecha_mascara_label})"  styleClass="campoNoEditable"/>
							
					
				</t:column>				
				<t:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" /></t:column>
				
			</h:panelGrid>
			
			<h:panelGrid columns="3" width="95%" columnClasses="cpo7-7,cpo7-7,cpo7-7" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
												
			<%/*  comprobante  */%>
				<t:column>							
					<t:outputLabel  styleClass="cpo7-7">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.compr_manual_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<h:selectOneRadio id="tipoComprobanteManual" value="#{mesaDeAyudaBean.tipoCompManual}" styleClass="campoNoEditable" >
					     <f:selectItem itemValue="-1" itemLabel="Ver todos" />					    
					     <f:selectItems  value="#{mesaDeAyudaBean.tiposCompManual}"/>
					</h:selectOneRadio>						
				</t:column>	
				<t:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" /></t:column>
			</h:panelGrid>
		
			<h:panelGrid columns="4" width="95%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
												
				<%/*  nro comprobante  */%>
				<t:column>							
					<t:outputLabel  styleClass="cpo7-7">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.nro_comp_man_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<t:inputText  id="nroRemito1M" value ="#{mesaDeAyudaBean.nroRemitoUnoManual}" maxlength="10"  size="10" styleClass="campo"  onkeypress="return soloNumeros(event);" />
					<t:outputText value="&nbsp;-&nbsp;" escape="false" />							
					<t:inputText  id="nroRemito2M" value ="#{mesaDeAyudaBean.nroRemitoDosManual}" maxlength="10"  size="10" styleClass="campo"  onkeypress="return soloNumeros(event);" />
				</t:column>	
				<t:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" /></t:column>
				
				<%/*  clientes  */%>
				<t:column>							
					<t:outputLabel  styleClass="cpo7-7">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.producto_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<h:selectOneListbox id="producto" size="1" value="#{mesaDeAyudaBean.producto}" styleClass="campo" immediate="true" >
						<f:selectItems value="#{mesaDeAyudaBean.productos}" />
					</h:selectOneListbox>	
					
				</t:column>	
				<h:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" /></h:column>
				<h:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" /></h:column>			
				
				<%/*  nro autorizacion  */%>
				<t:column>							
					<t:outputLabel  styleClass="cpo7-7">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.nro_auto_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<t:inputText  value ="#{mesaDeAyudaBean.nroAutorizacion}" maxlength="30"  size="30" styleClass="campo"  />
				</t:column>	
				<h:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							</h:column>
				<h:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" /></h:column>
			</h:panelGrid>	
			
				
			
			<h:panelGrid columns="1" width="95%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" /></h:column>	
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaCentrada" cellspacing="0">						 
				     	<h:column>				     	
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{mesaDeAyudaBean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
		 		</h:column>
							 	
			</h:panelGrid>
			
		
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION  */%>
		<t:saveState value="#{mesaDeAyudaBean.pagina}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{mesaDeAyudaBean.pagina.lastPage!=1 && mesaDeAyudaBean.pagina.totalElements!=0 && mesaDeAyudaBean.pagina.numpage != 1 && mesaDeAyudaBean.mostrarLista}" actionListener="#{mesaDeAyudaBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{mesaDeAyudaBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{mesaDeAyudaBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{mesaDeAyudaBean.pagina.numpage == 1}" rendered="#{mesaDeAyudaBean.pagina.lastPage!=1 && mesaDeAyudaBean.pagina.totalElements!=0  && mesaDeAyudaBean.pagina.numpage != 1 && mesaDeAyudaBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{mesaDeAyudaBean.mostrarLista}" value="#{mesaDeAyudaBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{mesaDeAyudaBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{mesaDeAyudaBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{mesaDeAyudaBean.mostrarLista}" value="#{mesaDeAyudaBean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{mesaDeAyudaBean.nombreArchivo!='' && mesaDeAyudaBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{mesaDeAyudaBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
				
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{mesaDeAyudaBean.pagina.lastPage!=1 && mesaDeAyudaBean.pagina.totalElements!=0 && mesaDeAyudaBean.pagina.numpage != mesaDeAyudaBean.pagina.lastPage && mesaDeAyudaBean.mostrarLista}" actionListener="#{mesaDeAyudaBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{mesaDeAyudaBean.pagina.numpage == mesaDeAyudaBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{mesaDeAyudaBean.pagina.lastPage!=1 && mesaDeAyudaBean.pagina.totalElements!=0 && mesaDeAyudaBean.pagina.numpage != mesaDeAyudaBean.pagina.lastPage && mesaDeAyudaBean.mostrarLista}" actionListener="#{mesaDeAyudaBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{mesaDeAyudaBean.pagina.numpage == mesaDeAyudaBean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		
		 
		
		<% /* LISTADO */%>
		
		<h:panelGrid width="90%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{mesaDeAyudaBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,columnaTablaTexto,  columnaTablaTexto, columnaTablaTexto, columnaTablaNumero, columnaTablaTexto,columnaTablaNumero,columnaTablaCentrada"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{mesaDeAyudaBean.mostrarLista}" >
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_autorizacion_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroAutorizacion} " />						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_pedido_label}"/>
						</f:facet>
						<h:outputText value="#{item.fechaPedido}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.ccss_label}"/>
						</f:facet>
						<h:outputText value="#{item.ccddDescripcion}"/>
					</h:column>
					
					
					
					<%/*
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.vendedor_ccs_label}"/>
						</f:facet>
						<h:outputText value="#{item.vendApellido}, " escape="false"/>
						<h:outputText value="#{item.vendNombre}"/>
					</h:column>			
					*/ %>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.cliDescripcion}"/>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_comp_man_label}"/>
						</f:facet>
						<h:outputText value="#{item.sucursalManual}"/>
						<h:outputText value=" - "/>
						<h:outputText value="#{item.nroRemito1}"/>
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.producto_label}"/>
						</f:facet>
						<h:outputText value="#{item.prodDecripcion}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.litros_a_acargar_label}"/>
						</f:facet>
						<h:outputText value="#{item.litrosACargar}">
						<f:convertNumber type="currency" pattern="#,##0.00"/>
						</h:outputText>
					</h:column>
					
					
					<%/*
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.motivo_autorizacion_corto_label}"/>
						</f:facet>
						<h:outputText value="#{item.motDescripcion}"/>
					</h:column>
					*/ %>
					
					<%/* ver */%>
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.mas_datos_label}"/>
						</f:facet>	
					     						
						 <t:commandLink styleClass="linkOperacion" 
									   actionListener="#{mesaDeAyudaBean.verTodosLosDatos}" 								  
									   title="#{Message.mas_datos_label}" >
									   <f:param name="codAutorizacion" id="codAutorizacion" value="#{item.codAutorizacion}"/>									   
									   <h:outputText value="#{Message.ver_label}" /> 
						</t:commandLink>						
					</h:column>	
					
					<%/* modificar */%>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="Modificar"/>
						</f:facet>	
					     
					     <t:div>					     
					    
						<h:outputLink styleClass="linkOperacion" rendered="#{item.fechaUsadoAutorizacion==''}"			 
							value="/refipass/pages/web/app/formularios/ref_mesa_ayuda.jsf?codigoAutorizacion=#{item.codAutorizacion}" title="Modificar">
							 <h:outputText value="Modificar" />
						</h:outputLink>
							
						</t:div>						
					</h:column>
					
					
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
	
		<h:panelGrid columns="1" width="90%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{mesaDeAyudaBean.volver}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		
		</h:column>
	</h:panelGrid>
	
			<t:saveState value="#{mesaDeAyudaBean.vendedor}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.vendedores}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.ccss}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.lstccss}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.cliente}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.clientes}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.motivosAutorizacion}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.motivoAutorizacion}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.descripcionMotAutorizacion}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.descripcionesMotAutorizacion}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.chofer}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.choferes}"></t:saveState>					
			<t:saveState value="#{mesaDeAyudaBean.patente}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.patentes}"></t:saveState>						
			<t:saveState value="#{mesaDeAyudaBean.fltFechaDesde}"></t:saveState>		
			<t:saveState value="#{mesaDeAyudaBean.fltFechaHasta}"></t:saveState>		
			<t:saveState value="#{mesaDeAyudaBean.subItemsNivel1}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.mostrarLista}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.mostrarFrmLista}"></t:saveState>	
			
			<t:saveState value="#{mesaDeAyudaBean.nroRemitoUnoManual}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.nroRemitoDosManual}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.producto}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.nroAutorizacion}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.tipoCompManual}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.empleado}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.empleados}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.nombreArchivo}"></t:saveState>			
			
	</h:form>
	
	
	
	<h:form id="frmDatosAutorizacion" rendered="#{!mesaDeAyudaBean.mostrarFrmLista}"  >
		<c:if test="${!mesaDeAyudaBean.puedeIngresar}">
			<c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>	
				
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  rowClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText value="#{Message.tit_datos_autorizacion_label}"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
	  	
	 		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
						
			<h:messages styleClass="errorNegro"/>
			
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="subTituloConFondo" width="70%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.datos_autorizacion_label}" escape="false"/>
				</t:column>
			</t:panelGrid>
		
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="3" columnClasses="cpo7Negrita, cpo8,cpo8" width="70%" rowClasses="filaTablaCabecera">													
				
				<%/*  vendedor  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.vendedor_ccs_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>
				
				</t:column>
				<t:column>
					  		    <t:outputText value ="#{mesaDeAyudaBean.autorizacion.vendApellido},&nbsp;#{mesaDeAyudaBean.autorizacion.vendNombre}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					
	
				</t:column>	
				<t:column>					
						<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>
				
				<%/*  ccss  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.centro_servicio_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
			
				</t:column>
				<t:column>
								 		 <t:outputText value ="#{mesaDeAyudaBean.autorizacion.ccddDescripcion}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					
	
				</t:column>	
				<t:column>					
						<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>
				
				<%/*  clientes  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.cliente_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>	
						
				</t:column>
				<t:column>
									 <t:outputText value ="#{mesaDeAyudaBean.autorizacion.cliDescripcion}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					
	 
				</t:column>	
				<t:column>					
						<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>	
				
				<%/*  motivos de autorizacion  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.motivo_autorizacion_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>
							
				</t:column>
				<t:column>
										<t:outputText value ="#{mesaDeAyudaBean.autorizacion.motDescripcion}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					
		
				</t:column>	
				<t:column>					
						<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>
				
				<%/*  descripcion de autorizacion  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.descripcion_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>	
		
				</t:column>
				<t:column>
								<t:outputText value ="#{mesaDeAyudaBean.autorizacion.desDescripcion}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					
	
				
				</t:column>	
				<t:column>					
						<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>						
					
			</t:panelGrid>	
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" width="60%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;" escape="false"/>
				</t:column>
			</t:panelGrid>
						
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="subTituloConFondo" width="70%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.datos_chofer_patente_label}" escape="false"/>
				</t:column>
			</t:panelGrid>
		
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="1" columnClasses="cpo7Negrita, cpo8" width="70%" rowClasses="filaTablaCabecera">													
							
				<%/*  chofer  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.chofer_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
					<t:outputText value ="#{mesaDeAyudaBean.autorizacion.chofApellido},&nbsp;#{mesaDeAyudaBean.autorizacion.chofNombre}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					

				</t:column>		
					
				<%/*  patente  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.patente_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>
					<t:outputText value ="#{mesaDeAyudaBean.autorizacion.patente}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					
							
				</t:column>	
					
			</t:panelGrid>
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" width="70%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;" escape="false"/>
				</t:column>
			</t:panelGrid>
			
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="subTituloConFondo" width="70%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.datos_remitos_label}" escape="false"/>
				</t:column>
			</t:panelGrid>
		
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="1" columnClasses="cpo8, cpo8" width="70%" rowClasses="filaTablaCabecera">													
							
				<%/*  comprobante manual  */%>
				<t:column>							
					
					<t:outputText styleClass="datoNegrita" value="&nbsp;&nbsp;&nbsp;#{Message.nro_remito_manua_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />	
					<h:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" styleClass="datoObligatorio" escape="false"/>							
																			
					<t:outputText value ="#{mesaDeAyudaBean.autorizacion.tcmDescripcion}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					

										
				</t:column>
				
				
				<%/*  nro remito  */%>
				<t:column>
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.nro_comprobante_manual_label}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>
					<t:outputText value ="#{mesaDeAyudaBean.autorizacion.sucursalManual}-#{mesaDeAyudaBean.autorizacion.nroRemito1}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					
			</t:column>		
				
				<%/*  producto  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.producto_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>					
					<t:outputText value ="#{mesaDeAyudaBean.autorizacion.prodDecripcion}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					

						
				</t:column>
				
				
				<%/*  litors a cargar  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.litros_a_acargar_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>
					<t:outputText value ="#{mesaDeAyudaBean.autorizacion.litrosACargar}" styleClass="campo" >					
					 	<f:convertNumber type="currency" pattern="#,##0.00"/>
					 </t:outputText>
				</t:column>
							
				
				<t:column rendered="#{mesaDeAyudaBean.autorizacion.tieneAnula}" >					
					<t:outputText rendered="#{mesaDeAyudaBean.autorizacion.tieneAnula}" value="&nbsp;&nbsp;&nbsp;#{Message.datos_anulacion_label}"  escape="false" styleClass="campoNoEditable"/>					
				</t:column>	
				
				
				<%/*  nro remito anular  */%>
				<t:column  rendered="#{mesaDeAyudaBean.autorizacion.tieneAnula}">							
					<t:outputText styleClass="datoNegrita" rendered="#{mesaDeAyudaBean.autorizacion.tieneAnula}" value="&nbsp;&nbsp;&nbsp;#{Message.nro_remito_anular_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
					<t:outputText rendered="#{mesaDeAyudaBean.autorizacion.tieneAnula}" value ="#{mesaDeAyudaBean.autorizacion.tcaDescripcion}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					
				</t:column>
				<t:column rendered="#{mesaDeAyudaBean.autorizacion.tieneAnula}">
					<t:outputText styleClass="datoNegrita" rendered="#{mesaDeAyudaBean.autorizacion.tieneAnula}" value="&nbsp;&nbsp;&nbsp;#{Message.nro_comprobante_anular_label}&nbsp;" escape="false" />							
					<t:outputText rendered="#{mesaDeAyudaBean.autorizacion.tieneAnula}" value ="#{mesaDeAyudaBean.autorizacion.sucursalAnular}-#{mesaDeAyudaBean.autorizacion.nroRemito2}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					
				</t:column>		
				
				
				<%/* fecha  */%>
				<t:column>							
					<t:outputText styleClass="datoNegrita" value="&nbsp;&nbsp;&nbsp;#{Message.fecha_pedido_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
									
					<t:outputText value ="#{mesaDeAyudaBean.autorizacion.fechaPedido}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					

					
				</t:column>	
						
				
				
			</t:panelGrid>
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" width="60%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;" escape="false"/>
				</t:column>
			</t:panelGrid>
			
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="subTituloConFondo" width="70%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.datos_codigos_label}" escape="false"/>
				</t:column>
			</t:panelGrid>
		
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="1" columnClasses="cpo8" width="70%" rowClasses="filaTablaCabecera">													
				
				<%/*  codigo  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.codigo_autorizacion_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				
					<t:outputText value ="#{mesaDeAyudaBean.autorizacion.nroAutorizacion}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					

				</t:column>	
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.fecha_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
					<t:outputText value ="#{mesaDeAyudaBean.autorizacion.fecha}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					
				</t:column>	
										
			</t:panelGrid>
			
			<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="1" columnClasses="nada" width="70%" >					
				<t:column>					
				 <t:commandButton value="#{Message.volver_label}" 
					actionListener="#{mesaDeAyudaBean.volverPrincipal}" styleClass="boton"/>			                 
				
				</t:column>	
				<t:column>    
				<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;"  escape="false"/>	
				</t:column>	
			</t:panelGrid>
			
					
		</t:panelGrid>	
		
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
						