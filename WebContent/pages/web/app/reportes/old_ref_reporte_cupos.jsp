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
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0">
		<%@ include file="../header.jsp" %>	
	
	<h:form id="frmFiltroCuenta" rendered="#{refReporteCuposBean.mostrarFrmLista}">	
		<t:saveState value="#{refReporteCuposBean.pagina}"></t:saveState>
		<t:saveState value="#{refReporteCuposBean.fltPatente}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteCuposBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  
		  <t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_cupos}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>			
	   		
				
		<%/*FILTROS*/%>
		
			<h:panelGrid columns="4" width="80%" columnClasses="cpo8-25,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
				</h:column>	
				<h:column></h:column>
				<h:column></h:column>
				<h:column></h:column>									
				
				<h:column>
					      <t:outputLabel for="patente" styleClass="cpo8-25">
							<t:outputText value="&nbsp;&nbsp;#{Message.patente_label}"  escape="false"/>
						  </t:outputLabel>
				</h:column>	
				<h:column>				     
						<t:inputText id="nombreChofer" value="#{refReporteCuposBean.fltPatente}" size="20" maxlength="50" styleClass="campo" />
				</h:column>	
				<h:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" /></h:column>	
				<h:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" /></h:column>	
			</h:panelGrid>
			
			<%/*  clientes  */%>
			<h:panelGrid columns="3" width="80%" columnClasses="cpo8-25,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				<t:column rendered="#{sessionScope.usuario.tipo==0}">							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.cliente_label}" escape="false" />							
				</t:column>
				<t:column rendered="#{sessionScope.usuario.tipo==0}">
					<h:selectOneListbox id="cliente" size="1" value="#{refReporteCuposBean.cliente}" styleClass="campo"  >
						<f:selectItems value="#{refReporteCuposBean.clientes}" />
					</h:selectOneListbox>						
				</t:column>	
				<t:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" /></t:column>
							
			</h:panelGrid>
			
			<%/*  mes  */%>
			<h:panelGrid columns="3" width="80%" columnClasses="cpo8-25,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				<t:column rendered="#{sessionScope.usuario.tipo==0}">							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.mes_label}" escape="false" />							
				</t:column>
				<t:column rendered="#{sessionScope.usuario.tipo==0}">
					<h:selectOneListbox  size="1" value="#{refReporteCuposBean.mes}" styleClass="campo"  >
						<f:selectItems value="#{refReporteCuposBean.lstMeses}" />
					</h:selectOneListbox>						
					
				
				<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.anio_label}&nbsp;&nbsp;" escape="false" />							
				
					<h:selectOneListbox  size="1" value="#{refReporteCuposBean.anio}" styleClass="campo"  >
						<f:selectItems value="#{refReporteCuposBean.lstAnios}" />
					</h:selectOneListbox>						
				</t:column>	
				<t:column></t:column>
							
			</h:panelGrid>
			
			
						
			
			<h:panelGrid columns="4" width="80%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>	</h:column>
				<h:column>	</h:column>
				<h:column>	</h:column>				
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteCuposBean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteCuposBean.pagina}"></t:saveState>
		<t:saveState value="#{refReporteCuposBean.subItemsNivel1}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteCuposBean.pagina.lastPage!=1 && refReporteCuposBean.pagina.totalElements!=0 && refReporteCuposBean.pagina.numpage != 1 && refReporteCuposBean.mostrarLista}" actionListener="#{refReporteCuposBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteCuposBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteCuposBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteCuposBean.pagina.numpage == 1}" rendered="#{refReporteCuposBean.pagina.lastPage!=1 && refReporteCuposBean.pagina.totalElements!=0  && refReporteCuposBean.pagina.numpage != 1 && refReporteCuposBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteCuposBean.mostrarLista}" value="#{refReporteCuposBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteCuposBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteCuposBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteCuposBean.mostrarLista}" value="#{refReporteCuposBean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteCuposBean.nombreArchivo!='' && refReporteCuposBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteCuposBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteCuposBean.pagina.lastPage!=1 && refReporteCuposBean.pagina.totalElements!=0 && refReporteCuposBean.pagina.numpage != refReporteCuposBean.pagina.lastPage && refReporteCuposBean.mostrarLista}" actionListener="#{refReporteCuposBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteCuposBean.pagina.numpage == refReporteCuposBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteCuposBean.pagina.lastPage!=1 && refReporteCuposBean.pagina.totalElements!=0 && refReporteCuposBean.pagina.numpage != refReporteCuposBean.pagina.lastPage && refReporteCuposBean.mostrarLista}" actionListener="#{refReporteCuposBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteCuposBean.pagina.numpage == refReporteCuposBean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 
		 
		
		<% /* LISTADO */ %>
		
		<h:panelGrid width="90%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteCuposBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,  columnaTablaTexto, columnaTablaTexto,  columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaCentrada"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteCuposBean.mostrarLista}" >				
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.mes_label}"/>
						</f:facet>
						<h:outputText value="#{item.mes} " />						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.anio_label}"/>
						</f:facet>
						<h:outputText value="#{item.anio} " />						
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.descripcion_label}"/>
						</f:facet>
						<h:outputText value="#{item.descripcion} " />						
					</h:column>
										
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.patente_label}"/>
						</f:facet>
						<h:outputText value="#{item.patente} " />
						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.unidad_negocio_label}"/>
						</f:facet>
						<h:outputText value="#{item.descrUnidadNegocio}"/>
					</h:column>
					
					<h:column rendered="#{sessionScope.usuario.tipo==0}">
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.cliDescripcion}"/>
					</h:column>
					
					<h:column rendered="#{sessionScope.usuario.tipo==0}">
						<f:facet name="header">
							<h:outputText value="#{Message.codigo_barra_label}"/>
						</f:facet>
						<h:outputText value="#{item.codBarra}"/>
					</h:column>	
					
					<h:column rendered="#{sessionScope.usuario.tipo==0}">
						<f:facet name="header">
							<h:outputText value="#{Message.activo_label}"/>
						</f:facet>
						<h:outputText value="#{item.activoStr}"/>
					</h:column>		
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cupo_lts_label}"/>
						</f:facet>
						<h:outputText value="#{item.cupoLitros}" rendered="#{!item.ilimitado}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						 
						 <h:outputText value="Ilimitado" rendered="#{item.ilimitado}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.consumo_lst_label}"/>
						</f:facet>
						<h:outputText value="#{item.consumoLitros}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						</h:outputText>
					</h:column>		
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.disponible_lts_label} "/>
						</f:facet>
						<h:outputText value="#{item.disponibleLitros}" rendered="#{!item.ilimitado}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						 
						  <h:outputText value="Ilimitado" rendered="#{item.ilimitado}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.remitos_label}"/>
						</f:facet>					
						
						<t:commandLink styleClass="linkOperacion" 
									   actionListener="#{refReporteCuposBean.verRemitos}" 								  
									   title="#{Message.ver_remitos_label}" >
									   <f:param name="nroPatente" id="nroPatente" value="#{item.patente}"/>
									   <f:param name="nroCliente" id="nroCliente" value="#{item.codCliente}"/>
									   <f:param name="nroProducto" id="nroProducto" value="#{item.codProducto}"/>
									   <f:param name="mes" id="mes" value="#{item.mesNro}"/>
									   <f:param name="anio" id="anio" value="#{item.anio}"/>
									   <h:outputText value="#{Message.ver_label}" /> 
						</t:commandLink>
						
					</h:column>		
								
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
		
		
			<h:panelGrid columns="1" width="80%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteCuposBean.volver}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
			
		<t:saveState value="#{refReporteCuposBean.mes}"></t:saveState>
		<t:saveState value="#{refReporteCuposBean.anio}"></t:saveState>
		<t:saveState value="#{refReporteCuposBean.cliente}"></t:saveState>
		<t:saveState value="#{refReporteCuposBean.clientes}"></t:saveState>		
		<t:saveState value="#{refReporteCuposBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteCuposBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteCuposBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteCuposBean.nombreArchivo}"></t:saveState>
				
		</h:column>
	</h:panelGrid>
	
	</h:form>	
	
	
	
	
	<% /*LISTADO DE REMITOS*/ %>
	<h:form id="frmFiltroCuentaSecundario" rendered="#{refReporteCuposBean.mostrarFrmListaSecundaria}">	
	
	<c:if var="puedeIngresarS" test="${!refReporteCuposBean.puedeIngresar}">		
		    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	</c:if> 	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>   		
				
		 <t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_cupos_remitos}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION 	 */%>
		
		 
		 <t:saveState value="#{refReporteCuposBean.pagina}"></t:saveState>
		 <t:saveState value="#{refReporteCuposBean.paginaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteCuposBean.mostrarLista}"></t:saveState>
		 <t:saveState value="#{refReporteCuposBean.mostrarListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteCuposBean.mostrarFrmLista}"></t:saveState>
		 <t:saveState value="#{refReporteCuposBean.mostrarFrmListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteCuposBean.nombreArchivoSecundario}"></t:saveState>
		 
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteCuposBean.paginaSecundaria.lastPage!=1 && refReporteCuposBean.paginaSecundaria.totalElements!=0 && refReporteCuposBean.paginaSecundaria.numpage != 1 && refReporteCuposBean.mostrarListaSecundaria}" actionListener="#{refReporteCuposBean.primeraSecundaria}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteCuposBean.paginaSecundaria.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteCuposBean.anteriorSecundaria}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteCuposBean.paginaSecundaria.numpage == 1}" rendered="#{refReporteCuposBean.paginaSecundaria.lastPage!=1 && refReporteCuposBean.paginaSecundaria.totalElements!=0  && refReporteCuposBean.paginaSecundaria.numpage != 1 && refReporteCuposBean.mostrarListaSecundaria}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteCuposBean.mostrarListaSecundaria}" value="#{refReporteCuposBean.paginaSecundaria.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteCuposBean.paginaSecundaria.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteCuposBean.mostrarListaSecundaria}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteCuposBean.mostrarListaSecundaria}" value="#{refReporteCuposBean.paginaSecundaria.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteCuposBean.nombreArchivoSecundario!='' && refReporteCuposBean.mostrarListaSecundaria}" target="_blank" styleClass="cpo7b" value="#{refReporteCuposBean.nombreArchivoSecundario}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteCuposBean.paginaSecundaria.lastPage!=1 && refReporteCuposBean.paginaSecundaria.totalElements!=0 && refReporteCuposBean.paginaSecundaria.numpage != refReporteCuposBean.paginaSecundaria.lastPage && refReporteCuposBean.mostrarListaSecundaria}" actionListener="#{refReporteCuposBean.siguienteSecundaria}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteCuposBean.paginaSecundaria.numpage == refReporteCuposBean.paginaSecundaria.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteCuposBean.paginaSecundaria.lastPage!=1 && refReporteCuposBean.paginaSecundaria.totalElements!=0 && refReporteCuposBean.paginaSecundaria.numpage != refReporteCuposBean.paginaSecundaria.lastPage && refReporteCuposBean.mostrarListaSecundaria}" actionListener="#{refReporteCuposBean.ultimaSecundaria}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteCuposBean.paginaSecundaria.numpage == refReporteCuposBean.paginaSecundaria.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
	
		 
		
		<% /* LISTADO */ %>
		
		<h:panelGrid width="80%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteCuposBean.subItemsNivel2}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto, columnaTablaTexto,  columnaTablaNumero,columnaTablaNumero, columnaTablaTexto, columnaTablaNumero,columnaTablaNumero,columnaTablaTexto,columnaTablaTexto"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteCuposBean.mostrarListaSecundaria}" >				
					
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
							<h:outputText value="#{Message.total_pesos_label}"/>
						</f:facet>
						<h:outputText value="#{item.montoTotal}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>		
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.litros_label}"/>
						</f:facet>
						<h:outputText value="#{item.litros}">
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
					
					
								
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
		
		
			<h:panelGrid columns="1" width="80%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" actionListener="#{refReporteCuposBean.volverPrincipal}" styleClass="boton"/>	
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
						