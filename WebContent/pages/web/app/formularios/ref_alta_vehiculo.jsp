<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<f:loadBundle basename="com.refinor.extranet.bundle.Messages" var="Message"/>
<f:view>

<html>
	<head>
        <meta http-equiv="Content-Type"
             content="text/html; charset=windows-1252"/>
		<title><h:outputText value="#{Message.nombre_sistema}"/> </title>
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/refinor.css">		
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsutiles.js"></script>				
		<script>		
		<%   
			    session.setAttribute("nroVehiculo",request.getParameter("nroVehiculo"));		
		%>	
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0" >
		<%@ include file="../header.jsp" %>				
		
		
		<h:form id="frmAlta" rendered="#{altaVehiculosBean.pantalla==1}"  >
		<c:if test="${!altaVehiculosBean.puedeIngresar}">
			<c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>	
				
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText value="#{Message.tit_vehiculo_chofer}"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
	  	
	 		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
						
			<h:messages styleClass="errorNegro"/>
			
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="2" columnClasses="cpo8, cpo8" width="70%" rowClasses="filaTablaCabecera">									
				
				<t:column/>
				<t:column/>
				<%/*  patente  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.patente_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<t:inputText id="dominioV" value ="#{altaVehiculosBean.dominio}" maxlength="20"  size="30" styleClass="campo" ></t:inputText>		
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>
				</t:column>
										
							
				
				
				<%/*  inicializado  
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.inicializado_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<h:selectBooleanCheckbox disabled="true" value="#{altaVehiculosBean.inicializado}" />
				</t:column>
				*/%>
				
				<%/*  activo  
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.activo_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<h:selectBooleanCheckbox disabled="true" value="#{altaVehiculosBean.activo}" />
				</t:column>
				*/%>
				
				<%/*  grupo de unidad de negocio  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.grupo_unidad_negocio_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<h:selectOneListbox id="gruposUnidadNegocio" size="1" value="#{altaVehiculosBean.grupoUnidadNegocio}" styleClass="campo" >
						<f:selectItems value="#{altaVehiculosBean.gruposUnidadNegocio}" />
					</h:selectOneListbox>	
					<t:outputText  value="&nbsp;&nbsp;" escape="false" />
					<t:commandButton value="#{Message.buscar_u_n_label}" actionListener="#{altaVehiculosBean.cargarSusUnidadesDeNegocio}" styleClass="boton"/>	
				</t:column>
				
				<%/*  unidad de negocio  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.unidad_negocio_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<h:selectOneListbox id="unidadesNegocio" size="1" value="#{altaVehiculosBean.unidadNegocio}"  styleClass="campo">
						<f:selectItems value="#{altaVehiculosBean.unidadesNegocio}"/>
					</h:selectOneListbox>					
				</t:column>		
				
				
				<t:column/>
				<t:column/>
				
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
				<t:column>
				 <h:commandButton  actionListener="#{altaVehiculosBean.guardar}" 
						value="#{Message.guardar_label}" styleClass="boton" />	
				 <t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;"  escape="false"/>	
				 <t:commandButton value="#{Message.volver_label}" 
					action="#{altaVehiculosBean.volver}" styleClass="boton"/>
					                 
				
				</t:column>	
			</t:panelGrid>		
			
			<t:saveState value="#{altaVehiculosBean.unidadNegocio}"></t:saveState>
			<t:saveState value="#{altaVehiculosBean.unidadesNegocio}"></t:saveState>	
			<t:saveState value="#{altaVehiculosBean.grupoUnidadNegocio}"></t:saveState>	
			<t:saveState value="#{altaVehiculosBean.gruposUnidadNegocio}"></t:saveState>	
			<t:saveState value="#{altaVehiculosBean.dominio}"></t:saveState>	

		</t:panelGrid>	
		
		</h:form>		
		
		
		<h:form id="mostrarFinal" rendered="#{altaVehiculosBean.pantalla==3}">
		<c:if test="${!altaVehiculosBean.puedeIngresar}">
			 <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>
		
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText  value="#{Message.tit_vehiculo_chofer}" rendered="#{sessionScope.usuario.tipo==1}"/>
					<t:outputText value="#{Message.modificacion_vehiculo_msg}" rendered="#{sessionScope.usuario.tipo==0}"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
	  	
	 		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>		
			
			<h:panelGrid columns="1" width="50%" columnClasses="campo10" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
									
				<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>					
				<h:column>
						<h:outputText value="#{altaVehiculosBean.mensajeGuardado}" style="color: #3A25A2; font-size: 11px;" styleClass="campo10"/>
				</h:column>								
				<h:column></h:column>				
				<h:column>					
					<t:commandButton value="#{Message.volver_label}" 
		                 action="#{altaVehiculosBean.volver}" styleClass="boton" rendered="#{sessionScope.usuario.tipo==1}"/>	
		                 
	                 <t:commandButton value="#{Message.volver_label}" 
		                 action="#{altaVehiculosBean.volverListaVehiculos}" styleClass="boton" rendered="#{sessionScope.usuario.tipo==0}"/>	
						                 
				</h:column>
				<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
			 	</h:panelGrid>	
			</t:panelGrid>
		
		
		</h:form>
		
		
		<h:form id="frmModificacion" rendered="#{altaVehiculosBean.pantalla==2}"  >
		<c:if test="${!altaVehiculosBean.puedeIngresar}">
			<c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>	
				
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText value="#{Message.modificacion_vehiculo_msg}"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
	  	
	 		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
						
			<h:messages styleClass="errorNegro"/>
			
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="2" columnClasses="cpo8, cpo8" width="55%" rowClasses="filaTablaCabecera">									
				
				<t:column/>
				<t:column/>
				<%/*  nombre  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.patente_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					
					<t:outputText value ="#{altaVehiculosBean.dominio}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					
					
				</t:column>	
				
				<%/*  codigo de barra  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.codigo_barra_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>	
				    <t:inputText id="nombreC" value ="#{altaVehiculosBean.codBarra}" maxlength="10"  size="30" styleClass="campo" ></t:inputText>		
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>
				</t:column>				
				
				
				<%/*  inicializado  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.inicializado_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<h:selectBooleanCheckbox value="#{altaVehiculosBean.inicializado}" />
				</t:column>
				
				
				<%/*  activo  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.activo_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<h:selectBooleanCheckbox value="#{altaVehiculosBean.activo}" />
				</t:column>
				
				
				<%/*  grupo de unidad de negocio  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.grupo_unidad_negocio_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<%/*
					<h:selectOneListbox id="gruposUnidadNegocio" size="1" value="#{altaVehiculosBean.grupoUnidadNegocio}" styleClass="campo" >
						<f:selectItems value="#{altaVehiculosBean.gruposUnidadNegocio}" />
					</h:selectOneListbox>	
					<t:outputText  value="&nbsp;&nbsp;" escape="false" />
					<t:commandButton value="#{Message.buscar_u_n_label}" actionListener="#{altaVehiculosBean.cargarSusUnidadesDeNegocio}" styleClass="boton"/>	
					*/%>	
					<t:outputText value ="#{altaVehiculosBean.grupoUnidadNegocioDesc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />										
				</t:column>
				
				<%/*  unidad de negocio  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.unidad_negocio_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<%/*
					<h:selectOneListbox id="unidadesNegocio" size="1" value="#{altaVehiculosBean.unidadNegocio}"  styleClass="campo">
						<f:selectItems value="#{altaVehiculosBean.unidadesNegocio}"/>
					</h:selectOneListbox>	
					 */%>
					
					<t:outputText value ="#{altaVehiculosBean.unidadNegocioDesc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />										
									
				</t:column>		
				
				
				<t:column/>
				<t:column/>
				
				<h:column>	
				<%/*
				<t:panelGrid border="0" cellpadding="" cellspacing="0" styleClass="columnaTablaTexto" 
						columns="1" columnClasses="cpo11-20" width="100%" >							
						<t:column>
							<t:div styleClass="cpo11">
								<t:outputText id="datosOblig" value="&nbsp;&nbsp;#{Message.datos_obligatorios_texto}"  styleClass="datoObligatorio" escape="false"/>
							</t:div>
						</t:column>										
				</t:panelGrid>
				*/%>
				</h:column>	
				
				<t:column>
				 <h:commandButton  actionListener="#{altaVehiculosBean.guardarModificacion}" 
						value="#{Message.guardar_label}" styleClass="boton" />	
				 <t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;"  escape="false"/>	
				 <t:commandButton value="#{Message.volver_label}" 
					action="#{altaVehiculosBean.volverListaVehiculos}" styleClass="boton"/>
					                 
				
				</t:column>	
			</t:panelGrid>		
			
			<t:saveState value="#{altaVehiculosBean.unidadNegocio}"></t:saveState>			
			<t:saveState value="#{altaVehiculosBean.unidadesNegocio}"></t:saveState>	
			<t:saveState value="#{altaVehiculosBean.grupoUnidadNegocio}"></t:saveState>	
			<t:saveState value="#{altaVehiculosBean.gruposUnidadNegocio}"></t:saveState>	
			<t:saveState value="#{altaVehiculosBean.dominio}"></t:saveState>		
			<t:saveState value="#{altaVehiculosBean.grupoUnidadNegocioDesc}"></t:saveState>
			<t:saveState value="#{altaVehiculosBean.unidadNegocioDesc}"></t:saveState>
				<t:saveState value="#{altaVehiculosBean.vehiculo}"></t:saveState>
			
			
		</t:panelGrid>	
		
		</h:form>

		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
		<%@ include file="../footer.jsp" %>
	</body>	
</html> 
</f:view>