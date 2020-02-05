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
		
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteChoferesBean.mostrarFrmLista}">	
		<t:saveState value="#{refReporteChoferesBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteChoferesBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_choferes}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>		
				
		<%/*FILTROS*/%>		
			<h:panelGrid columns="2" width="100%" columnClasses="cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			<h:column>
				<t:outputLabel for="consulte" styleClass="titulos">
					<h:outputText  value="&nbsp;&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
				</t:outputLabel>
			</h:column>	
			<h:column></h:column>			
			</h:panelGrid>
			
			
			
			
			<h:panelGrid columns="4" width="100%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column rendered="#{sessionScope.usuario.tipo==0}">							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.cliente_label}" escape="false" />							
				</t:column>
				<t:column rendered="#{sessionScope.usuario.tipo==0}">
					<h:selectOneListbox id="cliente" size="1" value="#{refReporteChoferesBean.cliente}" styleClass="campo"  
						 onchange="submit();" immediate="true" 
						 valueChangeListener="#{refReporteChoferesBean.cargarSusGrupos}">
						<f:selectItems value="#{refReporteChoferesBean.clientes}" />
					</h:selectOneListbox>						
				</t:column>
				<t:column rendered="#{sessionScope.usuario.tipo==0}"/>
				<t:column rendered="#{sessionScope.usuario.tipo==0}"/>
				
				<h:column>
						<t:outputText value="&nbsp;&nbsp;#{Message.grupo_unidad_negocio_label}" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>
					<%/* 
						<t:inputText id="descripcionGrupoNegocio" value="#{refReporteChoferesBean.fltDescripcionGrupoUN}" size="20" maxlength="20" styleClass="campo" />
				    */ %>
				    <h:selectOneListbox id="gruposUnidadNegocio" size="1" value="#{refReporteChoferesBean.grupoUnidadNegocio}" styleClass="campo" 
									   	onchange="submit();" immediate="true" 
										valueChangeListener="#{refReporteChoferesBean.cargarUnidadesNegocio}" >					
						<f:selectItems value="#{refReporteChoferesBean.gruposUnidadNegocio}" />
					</h:selectOneListbox>	
				</h:column>
				<h:column>
						<t:outputText value="&nbsp;#{Message.unidad_negocio_label}" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>	
						<%/* 		
						<t:inputText id="descripcionUnidadNegocio" value="#{refReporteChoferesBean.fltDescripcionUnidadNegocio}" size="20" maxlength="20" styleClass="campo" />
						*/ %>
						<h:selectOneListbox id="unidadesNegocio" size="1" value="#{refReporteChoferesBean.unidadNegocio}"  styleClass="campo"
						immediate="true" >
							<f:selectItems value="#{refReporteChoferesBean.unidadesNegocio}"/>
						</h:selectOneListbox>
				</h:column>
				
				<h:column>
					      <t:outputLabel for="nombre" styleClass="cpo8">
							<t:outputText value="&nbsp;&nbsp;#{Message.nombre_label}"  escape="false"/>
						  </t:outputLabel>
				</h:column>	
				<h:column>				     
						<t:inputText id="nombreChofer" value="#{refReporteChoferesBean.fltNombreChofer}" size="20" maxlength="20" styleClass="campo" />
				</h:column>	
				<h:column>
					      <t:outputLabel for="dni" styleClass="cpo8">
							<t:outputText value="&nbsp;#{Message.dni_label}"  escape="false"/>
						  </t:outputLabel>
				</h:column>	
				<h:column>				     
						<t:inputText id="dni" value="#{refReporteChoferesBean.fltDNI}" size="20" maxlength="20" styleClass="campo" />
				</h:column>	
			</h:panelGrid>
			
				
			
			<h:panelGrid columns="2" width="100%" columnClasses="cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.estado_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>
				</t:column>
				<t:column> 
					<h:outputText  value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false"/>		
					<h:selectOneRadio  value="#{refReporteChoferesBean.estado}" styleClass="campoNoEditable">
					     <f:selectItems  value="#{refReporteChoferesBean.estados}"/>
					</h:selectOneRadio>								
				</t:column>	
				
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;" escape="false"/>
					</h:outputLabel>
				</t:column>
				<t:column> 
					<h:selectOneRadio  value="#{refReporteChoferesBean.estadoIni}" styleClass="campoNoEditable">
					     <f:selectItems  value="#{refReporteChoferesBean.estadosIni}"/>
					</h:selectOneRadio>								
				</t:column>									
			</h:panelGrid>	
			
			<h:panelGrid columns="4" width="100%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>	</h:column>
				<h:column>	</h:column>
				<h:column>	</h:column>				
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0">						 
				     	<h:column>				     	
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteChoferesBean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		<t:saveState value="#{refReporteChoferesBean.grupoUnidadNegocio}"></t:saveState>
		<t:saveState value="#{refReporteChoferesBean.gruposUnidadNegocio}"></t:saveState>
		<t:saveState value="#{refReporteChoferesBean.unidadNegocio}"></t:saveState>
		<t:saveState value="#{refReporteChoferesBean.unidadesNegocio}"></t:saveState>
		<t:saveState value="#{refReporteChoferesBean.fltNombreChofer}"></t:saveState>
		<t:saveState value="#{refReporteChoferesBean.fltDNI}"></t:saveState>
		<t:saveState value="#{refReporteChoferesBean.cliente}"></t:saveState>
		<t:saveState value="#{refReporteChoferesBean.clientes}"></t:saveState>
		<t:saveState value="#{refReporteChoferesBean.estado}"></t:saveState>
		<t:saveState value="#{refReporteChoferesBean.estadoIni}"></t:saveState>
		<t:saveState value="#{refReporteChoferesBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteChoferesBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteChoferesBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteChoferesBean.nombreArchivo}"></t:saveState>
		
		
		
		
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteChoferesBean.pagina}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteChoferesBean.pagina.lastPage!=1 && refReporteChoferesBean.pagina.totalElements!=0 && refReporteChoferesBean.pagina.numpage != 1 && refReporteChoferesBean.mostrarLista}" actionListener="#{refReporteChoferesBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteChoferesBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteChoferesBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteChoferesBean.pagina.numpage == 1}" rendered="#{refReporteChoferesBean.pagina.lastPage!=1 && refReporteChoferesBean.pagina.totalElements!=0  && refReporteChoferesBean.pagina.numpage != 1 && refReporteChoferesBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteChoferesBean.mostrarLista}" value="#{refReporteChoferesBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteChoferesBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteChoferesBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteChoferesBean.mostrarLista}" value="#{refReporteChoferesBean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteChoferesBean.nombreArchivo!='' && refReporteChoferesBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteChoferesBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteChoferesBean.pagina.lastPage!=1 && refReporteChoferesBean.pagina.totalElements!=0 && refReporteChoferesBean.pagina.numpage != refReporteChoferesBean.pagina.lastPage && refReporteChoferesBean.mostrarLista}" actionListener="#{refReporteChoferesBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteChoferesBean.pagina.numpage == refReporteChoferesBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteChoferesBean.pagina.lastPage!=1 && refReporteChoferesBean.pagina.totalElements!=0 && refReporteChoferesBean.pagina.numpage != refReporteChoferesBean.pagina.lastPage && refReporteChoferesBean.mostrarLista}" actionListener="#{refReporteChoferesBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteChoferesBean.pagina.numpage == refReporteChoferesBean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 
		 
		
		<% /* LISTADO */ %>
		
		<h:panelGrid width="100%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteChoferesBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,  columnaTablaNumero, columnaTablaTexto, columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaCentrada"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteChoferesBean.mostrarLista}" >
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nombre_label}"/>
						</f:facet>
						<h:outputText value="#{item.apellidoChofer}, " escape="false"/>
						<h:outputText value="#{item.nombreChofer}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.dni_label}"/>
						</f:facet>
						<h:outputText value="#{item.dniChofer} " />
						
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
					<h:column> 
						<%/*rendered="#{sessionScope.usuario.tipo == 0}"*/%> 
						<f:facet name="header">
							<h:outputText value="#{Message.chofer_label}"/>
						</f:facet>	
					     						
						<h:outputLink rendered="#{item.dniChofer!=0}"			 
							value="/refipass/pages/web/app/formularios/ref_alta_chofer.jsf?nroChofer=#{item.codChofer}" title="#{Message.ver_datos_chofer_label}">
							 <h:outputText value=" #{Message.ver_label}" styleClass="botonSm" />
						</h:outputLink>
						
					</h:column>					
						
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
		
		
		<h:panelGrid columns="1" width="100%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteChoferesBean.volver}" styleClass="boton"/>	
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
						