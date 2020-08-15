<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<!--<%@ page import="com.refinor.extranet.util.Const"%>-->

<f:loadBundle basename="com.asecor.extranet.bundle.Messages_es_AR" var="Message"/>
<html>
<f:view>
	<head>
        <meta http-equiv="Content-Type"
             content="text/html; charset=windows-1252"/>
		<title><h:outputText value="#{Message.nombre_sistema}"/> </title>
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/refinor.css">		
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0">
		<%@ include file="header_salir.jsp" %>
		
	
	<h:form id="frmSalir">
	<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columnClasses="columnaTablaCentrada" columns="1">
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<t:column>
				<t:outputText value="#{Message.tit_cierre_sesion}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
		
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" styleClass="bordeblanco" 
					 columns="1" columnClasses="fondo" width="100%" rowClasses="columnaTablaCentrada">
					 <t:column><h:outputText value="&nbsp;&nbsp;" escape="false"/></t:column>
					
					 
			
			<t:column>
				<h:outputLabel  styleClass="cpo8">
				   <h:outputText value="&nbsp;&nbsp;#{Message.cierre_sesion_texto1}" escape="false"/>
				</h:outputLabel> 
			</t:column>
			<t:column>
					<h:outputLabel  styleClass="cpo8">
						<h:outputText value="&nbsp;&nbsp;#{Message.cierre_sesion_texto2}&nbsp;" escape="false"/><h:outputLink value="#{Message.contexto_sistema}"><h:outputText value="#{Message.click_aqui_text}"/></h:outputLink> 
					</h:outputLabel>
			</t:column>
			 <t:column><h:outputText value="&nbsp;&nbsp;" escape="false"/></t:column>
			
		</t:panelGrid>
	</t:panelGrid>
	</h:form>
	
	
	<%@ include file="footer.jsp" %>
	<% 
//	request.getSession().setAttribute(Const.USUARIO, null);

	request.getSession().invalidate();	
	%>

	</body>	
</f:view>
</html> 
						