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
		<%/*@ include file="/refipass/pages/web/app/header.jsp" */%>
		
	<h:panelGrid>
		<h:column>
			<h:outputText value="&nbsp;" escape="false"/>
		</h:column>
	</h:panelGrid>	
	
	
	<h:form id="frmFiltroCuenta" rendered="#{logonBean.mostrarPagina}">	
		   		   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>  		
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="iconito" columnClasses="titulosGrande" width="80%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<t:column>
				<t:outputText value="#{Message.tit_informe_ingreso_usuarios}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<%/*FILTROS*/%>
			<h:messages styleClass="errorNegro"/>
		
			<h:panelGrid columns="4" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
												
				<h:column>
						<t:outputText value="&nbsp;&nbsp;#{Message.login_label}" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>
						<t:inputText id="usuario" value="#{logonBean.login}" size="20" maxlength="20" styleClass="campo" />
				</h:column>
				<h:column>	</h:column>
				<h:column>	</h:column>
				
				<h:column>
						<t:outputText value="&nbsp;&nbsp;#{Message.clave_label}" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>					
						<t:inputText id="clave" value="#{logonBean.clave}" size="20" maxlength="20" styleClass="campo" />
				</h:column>				
				<h:column>	</h:column>
				<h:column>	</h:column>
								
				<h:column>	</h:column>
				<h:column>	</h:column>
				<h:column>	</h:column>
					
				<h:column>
						<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     <h:column>
				    	<t:commandButton value="#{Message.ingresar_label}" action="#{logonBean.validarUsuario}"
				    	 styleClass="boton"/>
				    </h:column>
				 	</h:panelGrid> 
			 	</h:column>	
			 	</h:panelGrid>

			 	</h:column>
			</h:panelGrid>				
	</h:form>	
		
	<%/*@ include file="/refipass/pages/web/app/footer.jsp" */%>
	
	</body>	
</f:view>
</html> 
						