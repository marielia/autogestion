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
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteVehiculosBean.mostrarFrmLista}">	
		<t:saveState value="#{refReporteVehiculosBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteVehiculosBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
   		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_vehiculos}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
				
		<%/*FILTROS*/%>
		
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
				</h:column>	
				<h:column></h:column>
			</h:panelGrid>
				
				
			
			<h:panelGrid columns="4" width="90%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				<t:column rendered="#{sessionScope.usuario.tipo==0}">							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.cliente_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>
				<t:column rendered="#{sessionScope.usuario.tipo==0}">
					<h:selectOneListbox id="cliente" size="1" value="#{refReporteVehiculosBean.cliente}" styleClass="campo"  
					 	 onchange="submit();" immediate="true" 
						 valueChangeListener="#{refReporteVehiculosBean.cargarSusGrupos}">
						<f:selectItems value="#{refReporteVehiculosBean.clientes}" />
					</h:selectOneListbox>						
				</t:column>	
				
				<t:column/>
				<t:column/>
				<h:column>
						<t:outputText value="&nbsp;&nbsp;#{Message.grupo_unidad_negocio_label}" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>
					<%/* 
						<t:inputText id="descripcionGrupoNegocio" value="#{refReporteVehiculosBean.fltDescripcionGrupoUN}" size="20" maxlength="20" styleClass="campo" />
				    */ %>
				    <h:selectOneListbox id="gruposUnidadNegocio" size="1" value="#{refReporteVehiculosBean.grupoUnidadNegocio}" styleClass="campo" 
									   	onchange="submit();" immediate="true" 
										valueChangeListener="#{refReporteVehiculosBean.cargarUnidadesNegocio}" >					
						<f:selectItems value="#{refReporteVehiculosBean.gruposUnidadNegocio}" />
					</h:selectOneListbox>	
				</h:column>								
				
				<h:column>
						<t:outputText value="&nbsp;&nbsp;#{Message.unidad_negocio_label}" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>	
							<%/* 	
						<t:inputText id="descripcionUnidadNegocio" value="#{refReporteVehiculosBean.fltDescripcionUnidadNegocio}" size="20" maxlength="20" styleClass="campo" />
						  */ %>
						  <h:selectOneListbox id="unidadesNegocio" size="1" value="#{refReporteVehiculosBean.unidadNegocio}"  styleClass="campo"
						immediate="true" >
							<f:selectItems value="#{refReporteVehiculosBean.unidadesNegocio}"/>
						</h:selectOneListbox>
				</h:column>
				
				<h:column>
					      <t:outputLabel for="nombre" styleClass="cpo8">
							<t:outputText value="&nbsp;&nbsp;#{Message.patente_label}"  escape="false"/>
						  </t:outputLabel>
				</h:column>	
				<h:column>				     
						<t:inputText id="nombreChofer" value="#{refReporteVehiculosBean.fltPatente}" size="20" maxlength="50" styleClass="campo" />
				</h:column>	
			</h:panelGrid>
			
			
			
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.estado_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>
				</t:column>
				<t:column> 
					<h:selectOneRadio  value="#{refReporteVehiculosBean.estado}" styleClass="campoNoEditable">
					     <f:selectItems  value="#{refReporteVehiculosBean.estados}"/>
					</h:selectOneRadio>								
				</t:column>
				
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;" escape="false"/>
					</h:outputLabel>
				</t:column>
				<t:column> 
					<h:selectOneRadio  value="#{refReporteVehiculosBean.estadoIni}" styleClass="campoNoEditable">
					     <f:selectItems  value="#{refReporteVehiculosBean.estadosIni}"/>
					</h:selectOneRadio>								
				</t:column>						
			</h:panelGrid>	
			
			<h:panelGrid columns="4" width="90%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>	</h:column>
				<h:column>	</h:column>
				<h:column>	</h:column>				
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteVehiculosBean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		<t:saveState value="#{refReporteVehiculosBean.grupoUnidadNegocio}"></t:saveState>
		<t:saveState value="#{refReporteVehiculosBean.gruposUnidadNegocio}"></t:saveState>
		<t:saveState value="#{refReporteVehiculosBean.unidadNegocio}"></t:saveState>
		<t:saveState value="#{refReporteVehiculosBean.unidadesNegocio}"></t:saveState>		
		<t:saveState value="#{refReporteVehiculosBean.cliente}"></t:saveState>
		<t:saveState value="#{refReporteVehiculosBean.clientes}"></t:saveState>
		<t:saveState value="#{refReporteVehiculosBean.estado}"></t:saveState>
		<t:saveState value="#{refReporteVehiculosBean.estadoIni}"></t:saveState>
		<t:saveState value="#{refReporteVehiculosBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteVehiculosBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteVehiculosBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteVehiculosBean.nombreArchivo}"></t:saveState>
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteVehiculosBean.pagina}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteVehiculosBean.pagina.lastPage!=1 && refReporteVehiculosBean.pagina.totalElements!=0 && refReporteVehiculosBean.pagina.numpage != 1 && refReporteVehiculosBean.mostrarLista}" actionListener="#{refReporteVehiculosBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteVehiculosBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteVehiculosBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteVehiculosBean.pagina.numpage == 1}" rendered="#{refReporteVehiculosBean.pagina.lastPage!=1 && refReporteVehiculosBean.pagina.totalElements!=0  && refReporteVehiculosBean.pagina.numpage != 1 && refReporteVehiculosBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteVehiculosBean.mostrarLista}" value="#{refReporteVehiculosBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteVehiculosBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteVehiculosBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteVehiculosBean.mostrarLista}" value="#{refReporteVehiculosBean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteVehiculosBean.nombreArchivo!='' && refReporteVehiculosBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteVehiculosBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteVehiculosBean.pagina.lastPage!=1 && refReporteVehiculosBean.pagina.totalElements!=0 && refReporteVehiculosBean.pagina.numpage != refReporteVehiculosBean.pagina.lastPage && refReporteVehiculosBean.mostrarLista}" actionListener="#{refReporteVehiculosBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteVehiculosBean.pagina.numpage == refReporteVehiculosBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteVehiculosBean.pagina.lastPage!=1 && refReporteVehiculosBean.pagina.totalElements!=0 && refReporteVehiculosBean.pagina.numpage != refReporteVehiculosBean.pagina.lastPage && refReporteVehiculosBean.mostrarLista}" actionListener="#{refReporteVehiculosBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteVehiculosBean.pagina.numpage == refReporteVehiculosBean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 
		 
		
		<% /* LISTADO */ %>
		
		<h:panelGrid width="90%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteVehiculosBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,   columnaTablaTexto, columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaCentrada"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteVehiculosBean.mostrarLista}" >
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.patente_label}"/>
						</f:facet>
						<h:outputText value="#{item.dominio} " />
						
					</h:column>
					
					<%/*
					<h:column rendered="#{sessionScope.usuario.tipo==0}">
						<f:facet name="header">
							<h:outputText value="#{Message.codigo_barra_label}"/>
						</f:facet>
						<h:outputText value="#{item.codBarra}"/>
					</h:column>
					*/ %>
					
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
							<h:outputText value="#{Message.activo_label}"/>
						</f:facet>
						<h:outputText value="#{item.activoDesc}"/>
					</h:column>		
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.inicializado_label}"/>
						</f:facet>
						<h:outputText value="#{item.inicializadoDesc}"/>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_alta_label}"/>
						</f:facet>
						<h:outputText value="#{item.fechaAlta}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_baja_label}"/>
						</f:facet>
						<h:outputText value="#{item.fechaBaja}"/>
					</h:column>
					
					<%/* modificar */%>
					<h:column rendered="#{sessionScope.usuario.tipo == 0}">
						<f:facet name="header">
							<h:outputText value="#{Message.vehiculo_label}"/>
						</f:facet>	
					     						
						<h:outputLink styleClass="linkOperacion" rendered="#{item.codigo!=0}"			 
							value="/refipass/pages/web/app/formularios/ref_alta_vehiculo.jsf?nroVehiculo=#{item.codigo}" title="#{Message.ver_datos_vehiculo_label}">
							 <h:outputText value=" #{Message.ver_label}" />
						</h:outputLink>
						
					</h:column>		
								
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
		
		
			<h:panelGrid columns="1" width="90%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteVehiculosBean.volver}" styleClass="boton"/>	
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
						