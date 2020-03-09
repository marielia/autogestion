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
		
		
		<h:form id="frmAlta" rendered="#{altaUsuarioWebEmpleadoBean.pantalla==1}"  >
		<c:if test="${!altaUsuarioWebEmpleadoBean.puedeIngresar}">
			<c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>	
				
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText value="#{Message.tit_alta_usu_web_empleado}"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
	  	
	 		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
						
			<h:messages styleClass="errorNegro"/>
			
			
			<h:panelGrid width="70%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
			<h:column>		
				<t:dataTable value="#{altaUsuarioWebEmpleadoBean.empleados}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,columnaTablaNumero,  columnaTablaNumero, columnaTablaTexto, columnaTablaCentrada"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 >
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.empleado_label}"/>
						</f:facet>
						<h:outputText value="#{item.descripcion} " />						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.dni_label}"/>
						</f:facet>
						<h:outputText value="#{item.dni}" rendered="#{item.dni!=0}" />						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_legajo_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroLegajo}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.ccss_label}"/>
						</f:facet>
						<h:outputText value="#{item.descrCCSS}"/>
					</h:column>
					
					<%/* modificar */%>
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.alta_empleados_label}"/>
						</f:facet>	
					     						
						 <t:commandLink styleClass="linkOperacion" 
									   actionListener="#{altaUsuarioWebEmpleadoBean.mostrarAltaEmpleado}" 	 >
									   <f:param name="codEmpleado" id="codEmpleado" value="#{item.codigo}"/>
									   <f:param name="codCCSS" id="codCCSS" value="#{item.ccssid}"/>
									   <f:param name="empleado" id="empleado" value="#{item.descripcion}"/>	
									   <f:param name="nroDoc" id="nroDoc" value="#{item.dni}"/>											   
									   <h:outputText value="#{Message.seleccione_label}" /> 
						</t:commandLink>
						
					</h:column>					
						
				</t:dataTable>	
				</h:column>
			</h:panelGrid>
			
			<h:panelGrid columns="1" width="70%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{altaUsuarioWebEmpleadoBean.volver}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
		</t:panelGrid>	
			<t:saveState value="#{altaUsuarioWebEmpleadoBean.empleados}"></t:saveState>	
			<t:saveState value="#{altaUsuarioWebEmpleadoBean.codEmpleado}"></t:saveState>	
			<t:saveState value="#{altaUsuarioWebEmpleadoBean.codCCSS}"></t:saveState>	
			<t:saveState value="#{altaUsuarioWebEmpleadoBean.nroDocumento}"></t:saveState>	
			<t:saveState value="#{altaUsuarioWebEmpleadoBean.empleado}"></t:saveState>	
			
			
		
		</h:form>		
		
		
		<h:form id="mostrarFinal" rendered="#{altaUsuarioWebEmpleadoBean.pantalla==3}">
		<c:if test="${!altaUsuarioWebEmpleadoBean.puedeIngresar}">
			 <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>
		
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>					
					<t:outputText value="#{Message.alta_usu_web_empleado_label}" rendered="#{sessionScope.usuario.tipo==0}"/>
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
						<h:outputText value="#{altaUsuarioWebEmpleadoBean.mensajeGuardado}" style="color: #000000; font-size: 11px;" styleClass="campo10"/>
				</h:column>								
				<h:column></h:column>				
				<h:column>					
					      
	                 <t:commandButton value="#{Message.volver_label}" 
		                 action="#{altaUsuarioWebEmpleadoBean.volver}" styleClass="boton" rendered="#{sessionScope.usuario.tipo==0}"/>	
						                 
				</h:column>
				<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
			 	</h:panelGrid>	
			</t:panelGrid>
		
		
		</h:form>
		
		<h:form id="cargarDatos" rendered="#{altaUsuarioWebEmpleadoBean.pantalla==2}">
		<c:if test="${!altaUsuarioWebEmpleadoBean.puedeIngresar}">
			 <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>
		
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>					
					<t:outputText value="#{Message.alta_usu_web_empleado_label}" rendered="#{sessionScope.usuario.tipo==0}"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
	  	
	 		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>	
			<h:messages styleClass="errorNegro"/>
				
			
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="2" columnClasses="cpo8, cpo8" width="50%" rowClasses="filaTablaCabecera">									
				
				<t:column/>
				<t:column/>
				
				<%/*  empleado */%>		 
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.empleado_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				
				<t:column>						
					<h:outputText value="#{altaUsuarioWebEmpleadoBean.empleado}" styleClass="cpo8" escape="false"/>
				</t:column>
								
				<%/*  usuario  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.nombre_usuario_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText id="apellidoC" value ="#{altaUsuarioWebEmpleadoBean.nombreUsuario}" maxlength="20"  size="30" styleClass="campo" ></t:inputText>		
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>
				</t:column>
	
							
				<%/*  correo  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.cuenta_correo_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="dniC" value ="#{altaUsuarioWebEmpleadoBean.email}" maxlength="50"  size="30" styleClass="campo"  />
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>					
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
				 <h:commandButton  actionListener="#{altaUsuarioWebEmpleadoBean.guardar}" 
						value="#{Message.guardar_label}" styleClass="boton" />	
				 <t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;"  escape="false"/>	
				 <t:commandButton value="#{Message.volver_label}" 
					actionListener="#{altaUsuarioWebEmpleadoBean.volverPaginaPrincipal}" styleClass="boton"/>
					                 
				
				</t:column>	
			</t:panelGrid>		
		</t:panelGrid>
		<t:saveState value="#{altaUsuarioWebEmpleadoBean.nombreUsuario}"></t:saveState>	
		<t:saveState value="#{altaUsuarioWebEmpleadoBean.email}"></t:saveState>	
		<t:saveState value="#{altaUsuarioWebEmpleadoBean.empleado}"></t:saveState>	
		<t:saveState value="#{altaUsuarioWebEmpleadoBean.codEmpleado}"></t:saveState>	
		<t:saveState value="#{altaUsuarioWebEmpleadoBean.codCCSS}"></t:saveState>	
		<t:saveState value="#{altaUsuarioWebEmpleadoBean.nroDocumento}"></t:saveState>
		</h:form>
		

		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
		<%@ include file="../footer.jsp" %>
	</body>	
</html> 
</f:view>