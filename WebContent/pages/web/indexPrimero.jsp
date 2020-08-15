<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<f:loadBundle basename="com.asecor.extranet.bundle.Messages_es_AR" var="Message"/>
<html xmlns="http://www.w3.org/1999/xhtml">
 
<f:view>
	<head>
         <link rel="apple-touch-icon" sizes="57x57" href="../../favicon/apple-icon-57x57.png">
		<link rel="apple-touch-icon" sizes="60x60" href="../../favicon/apple-icon-60x60.png">
		<link rel="apple-touch-icon" sizes="72x72" href="../../favicon/apple-icon-72x72.png">
		<link rel="apple-touch-icon" sizes="76x76" href="../../favicon/apple-icon-76x76.png">
		<link rel="apple-touch-icon" sizes="114x114" href="../../favicon/apple-icon-114x114.png">
		<link rel="apple-touch-icon" sizes="120x120" href="../../favicon/apple-icon-120x120.png">
		<link rel="apple-touch-icon" sizes="144x144" href="../../favicon/apple-icon-144x144.png">
		<link rel="apple-touch-icon" sizes="152x152" href="../../favicon/apple-icon-152x152.png">
		<link rel="apple-touch-icon" sizes="180x180" href="../../favicon/apple-icon-180x180.png">
		<link rel="icon" type="image/png" sizes="192x192"  href="../../favicon/android-icon-192x192.png">
		<link rel="icon" type="image/png" sizes="32x32" href="../../favicon/favicon-32x32.png">
		<link rel="icon" type="image/png" sizes="96x96" href="../../favicon/favicon-96x96.png">
		<link rel="icon" type="image/png" sizes="16x16" href="../../favicon/favicon-16x16.png">
		<link rel="manifest" href="../../favicon/manifest.json">
		<meta name="msapplication-TileColor" content="#ffffff">
		<meta name="msapplication-TileImage" content="../../favicon/ms-icon-144x144.png">
		<meta name="theme-color" content="#ffffff">
		
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><h:outputText value="#{Message.nombre_sistema}"/> </title>
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/asecor.css">		
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsutiles.js"></script>
		 
		 
	</head>
	<body class="cover" > 
	
	 	<h:form id="frmIngreso"  rendered="#{logoFirstBean.ingresar}" >    
 			<c:if test="#{!logoFirstBean.puedeIngresar}">
				<c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
			</c:if> 
 	    </h:form> 
 
	</body>	
</f:view>
</html>