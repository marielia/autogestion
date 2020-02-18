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
			    session.setAttribute("nroChofer",request.getParameter("nroChofer"));		
		%>
					
		function validarCamposAlta(){		
		var nombre =document.getElementById("frmAlta:nombreC");
		var apellido =document.getElementById("frmAlta:apellidoC");	
		var dni =document.getElementById("frmAlta:dniC");	
		
			if(nombre.value=="" || apellido.value=="" || dni.value==""){	  	  
			  alert("Debe ingresar los datos obligatorios.");
			  return false;	
			}else{
			  return true;	
			}	
					
		}	
		
		
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0" >
		<%@ include file="../header.jsp" %>				
		
		
		
		<h:form id="frmAlta" rendered="#{altaChoferesBean.pantalla==1}"  >
		<c:if test="${!altaChoferesBean.puedeIngresar}">
			<c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>	
		
		
				
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText value="#{Message.tit_alta_chofer}"/>
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
				<%/*  nombre  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.nombre_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<t:inputText id="nombreC" value ="#{altaChoferesBean.nombre}" maxlength="20"  size="30" styleClass="campo" ></t:inputText>		
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>
				</t:column>
										
				<%/*  apellido  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.apellido_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText id="apellidoC" value ="#{altaChoferesBean.apellido}" maxlength="20"  size="30" styleClass="campo" ></t:inputText>		
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>
				</t:column>
	
				<%/*  pin  
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.pin_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:outputText value ="&nbsp;&nbsp;&nbsp;#{altaChoferesBean.pinChofer}" escape="false" styleClass="campo" />		
				</t:column>
				*/%>
			
				<%/*  dni  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.dni_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="dniC" value ="#{altaChoferesBean.numeroDocumento}" maxlength="20"  size="20" styleClass="campo"  onkeypress="return soloNumeros(event);" />
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>					
				</t:column>
				
				
				<%/*  inicializado  
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.inicializado_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<h:selectBooleanCheckbox disabled="true" value="#{altaChoferesBean.inicializado}" />
				</t:column>
				*/%>
				
				<%/*  activo  
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.activo_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<h:selectBooleanCheckbox disabled="true" value="#{altaChoferesBean.activo}" />
				</t:column>
				*/%>
				
				<%/*  grupo de unidad de negocio  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.grupo_unidad_negocio_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<h:selectOneListbox id="gruposUnidadNegocio" size="1" value="#{altaChoferesBean.grupoUnidadNegocio}" styleClass="campo" >
						<f:selectItems value="#{altaChoferesBean.gruposUnidadNegocio}" />
					</h:selectOneListbox>	
					<t:outputText  value="&nbsp;&nbsp;" escape="false" />
					<t:commandButton value="#{Message.buscar_u_n_label}" actionListener="#{altaChoferesBean.cargarSusUnidadesDeNegocio}" styleClass="boton"/>	
				</t:column>
				
				<%/*  unidad de negocio  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.unidad_negocio_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<h:selectOneListbox id="unidadesNegocio" size="1" value="#{altaChoferesBean.unidadNegocio}"  styleClass="campo">
						<f:selectItems value="#{altaChoferesBean.unidadesNegocio}"/>
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
				 <h:commandButton  actionListener="#{altaChoferesBean.guardar}" 
						value="#{Message.guardar_label}" styleClass="boton" />	
				 <t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;"  escape="false"/>	
				 <t:commandButton value="#{Message.volver_label}" 
					action="#{altaChoferesBean.volver}" styleClass="boton"/>
					                 
				
				</t:column>	
			</t:panelGrid>		
			
			<t:saveState value="#{altaChoferesBean.unidadNegocio}"></t:saveState>
			<t:saveState value="#{altaChoferesBean.unidadesNegocio}"></t:saveState>	
			<t:saveState value="#{altaChoferesBean.grupoUnidadNegocio}"></t:saveState>	
			<t:saveState value="#{altaChoferesBean.gruposUnidadNegocio}"></t:saveState>	
			<t:saveState value="#{altaChoferesBean.nombre}"></t:saveState>	
			<t:saveState value="#{altaChoferesBean.apellido}"></t:saveState>
			<t:saveState value="#{altaChoferesBean.pinChofer}"></t:saveState>
			<t:saveState value="#{altaChoferesBean.numeroDocumento}"></t:saveState>	
		</t:panelGrid>	
		
		</h:form>		
		
		
		<h:form id="mostrarFinal" rendered="#{altaChoferesBean.pantalla==3}">
		<c:if test="${!altaChoferesBean.puedeIngresar}">
			 <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>
		
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
<%-- 			<t:panelGrid border="0" cellpadding="0" cellspacing="0"  --%>
<%-- 					 columns="1"  columnClasses="titulosGrande" width="100%"> --%>
<%-- 				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column> --%>
<%-- 				<t:column> --%>
<%-- 					<t:outputText value="#{Message.tit_alta_chofer}" rendered="#{sessionScope.usuario.tipo==1}"/> --%>
<%-- 					<t:outputText value="#{Message.modificacion_chofer_msg}" rendered="#{sessionScope.usuario.tipo==0}"/> --%>
<%-- 				</t:column> --%>
<%-- 				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column> --%>
<%-- 			</t:panelGrid> --%>
			
				<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
						 columns="1"  columnClasses="titulosGrande" width="100%">
					<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
					<%@ include file="../datosCliente.jsp" %>	
					<t:column>
						<t:outputText value="#{Message.modificacion_chofer_msg}"/>
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
						<h:outputText value="#{altaChoferesBean.mensajeGuardado}" style="color: #3A25A2; font-size: 11px;" styleClass="campo10"/>
				</h:column>								
				<h:column></h:column>				
				<h:column>					
					<t:commandButton value="#{Message.volver_label}" 
		                 action="#{altaChoferesBean.volver}" styleClass="boton" rendered="#{sessionScope.nroChofer==null}"/>	
		                 
	                 <t:commandButton value="#{Message.volver_label}" 
		                 action="#{altaChoferesBean.volverListaChoferes}" styleClass="boton" rendered="#{sessionScope.nroChofer!=null}"/>	
						                 
				</h:column>
				<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
			 	</h:panelGrid>	
			</t:panelGrid>
		
		
		</h:form>
		
		
		<h:form id="frmModificacion" rendered="#{altaChoferesBean.pantalla==2}"  >
		<c:if test="${!altaChoferesBean.puedeIngresar}">
			<c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>	
				
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<%@ include file="../datosCliente.jsp" %>	
				<t:column>
					<t:outputText value="#{Message.modificacion_chofer_msg}"/>
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
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.nombre_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<t:inputText id="nombreC" value ="#{altaChoferesBean.nombre}" maxlength="20"  size="30" styleClass="campo" ></t:inputText>		
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>
					
					<%/* <t:outputText value ="#{altaChoferesBean.nombre}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					
					*/%>
				</t:column>
										
				<%/*  apellido  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.apellido_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText id="apellidoC" value ="#{altaChoferesBean.apellido}" maxlength="20"  size="30" styleClass="campo" ></t:inputText>		
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>
					
					<%/*<t:outputText value ="#{altaChoferesBean.apellido}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />					
					*/%>
				</t:column>
	
				<%/*  pin  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.pin_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>

					<t:inputText value ="#{altaChoferesBean.pinChofer}" maxlength="20"  size="15" styleClass="campo" />	
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>	
				</t:column>
				
			
				<%/*  dni  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.dni_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>					
					<t:inputText  id="dniC" value ="#{altaChoferesBean.numeroDocumento}" maxlength="20"  size="15" styleClass="campo"  onkeypress="return soloNumeros(event);" />
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>				
					
					<%/*
					<t:outputText value ="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;#{altaChoferesBean.numeroDocumento}" escape="false" styleClass="campo" />										
					*/%>
				</t:column>
				
				
				<%/*  inicializado  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.inicializado_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<h:selectBooleanCheckbox value="#{altaChoferesBean.inicializado}" />
				</t:column>
				
				
				<%/*  activo  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.activo_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<h:selectBooleanCheckbox value="#{altaChoferesBean.activo}" />
				</t:column>
				
				
				<%/*  grupo de unidad de negocio  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.grupo_unidad_negocio_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<%/*
					<h:selectOneListbox id="gruposUnidadNegocio" size="1" value="#{altaChoferesBean.grupoUnidadNegocio}" styleClass="campo" >
						<f:selectItems value="#{altaChoferesBean.gruposUnidadNegocio}" />
					</h:selectOneListbox>	
					<t:outputText  value="&nbsp;&nbsp;" escape="false" />
					<t:commandButton value="#{Message.buscar_u_n_label}" actionListener="#{altaChoferesBean.cargarSusUnidadesDeNegocio}" styleClass="boton"/>	
					*/%>	
					<%/*
					<t:outputText value ="#{altaChoferesBean.grupoUnidadNegocioDesc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />										
					*/%>
					<h:selectOneListbox id="gruposUnidadNegocio" size="1" value="#{altaChoferesBean.grupoUnidadNegocio}" styleClass="campo" 
									   	onchange="submit();" immediate="true" 
										valueChangeListener="#{altaChoferesBean.cargarUnidadesNegocio}" >					
						<f:selectItems value="#{altaChoferesBean.gruposUnidadNegocio}" />
					</h:selectOneListbox>
				</t:column>
				
				<%/*  unidad de negocio  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.unidad_negocio_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					
					<h:selectOneListbox id="unidadesNegocio" size="1" value="#{altaChoferesBean.unidadNegocio}"  styleClass="campo">
						<f:selectItems value="#{altaChoferesBean.unidadesNegocio}"/>
					</h:selectOneListbox>	
					 
					<%/*
					<t:outputText value ="#{altaChoferesBean.unidadNegocioDesc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" styleClass="campo" />										
					*/%>			
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
				 <h:commandButton  actionListener="#{altaChoferesBean.guardarModificacion}" 
						value="#{Message.guardar_label}" styleClass="boton" />	
				 <t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;"  escape="false"/>	
				 <t:commandButton value="#{Message.volver_label}" 
					action="#{altaChoferesBean.volverListaChoferes}" styleClass="boton"/>
					                 
				
				</t:column>	
			</t:panelGrid>		
			
			<t:saveState value="#{altaChoferesBean.unidadNegocio}"></t:saveState>			
			<t:saveState value="#{altaChoferesBean.unidadesNegocio}"></t:saveState>	
			<t:saveState value="#{altaChoferesBean.grupoUnidadNegocio}"></t:saveState>	
			<t:saveState value="#{altaChoferesBean.gruposUnidadNegocio}"></t:saveState>	
			<t:saveState value="#{altaChoferesBean.nombre}"></t:saveState>	
			<t:saveState value="#{altaChoferesBean.apellido}"></t:saveState>
			<t:saveState value="#{altaChoferesBean.pinChofer}"></t:saveState>
			<t:saveState value="#{altaChoferesBean.numeroDocumento}"></t:saveState>	
			
			<t:saveState value="#{altaChoferesBean.grupoUnidadNegocioDesc}"></t:saveState>
			<t:saveState value="#{altaChoferesBean.unidadNegocioDesc}"></t:saveState>
			<t:saveState value="#{altaChoferesBean.mchofer}"></t:saveState>
			
			
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