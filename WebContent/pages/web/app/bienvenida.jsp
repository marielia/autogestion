<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<f:loadBundle basename="com.asecor.extranet.bundle.Messages_es_AR" var="Message"/>
<f:view>



<html>
	<head>
	<link rel="apple-touch-icon" href="favicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" href="favicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" href="favicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" href="favicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" href="favicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" href="favicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" href="favicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" href="favicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" href="favicon/apple-icon-180x180.png">
<link rel="icon" type="image/png"
	href="favicon/android-icon-192x192.png">
<link rel="icon" type="image/png" href="favicon/favicon-32x32.png">
<link rel="icon" type="image/png" href="favicon/favicon-96x96.png">
<link rel="icon" type="image/png" href="favicon/favicon-16x16.png">

<link rel="manifest" href="favicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage"
	content="favicon/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

<link
	href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;700&display=swap"
	rel="stylesheet">

        <meta http-equiv="Content-Type"
             content="text/html; charset=windows-1252"/>
		
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/asecor.css">		
	
        

	</head>
<body class="cover">
	<div class="formuContainer">
		<div class="loginHeader">

			<div class="tituloPortal">DATOS TITULAR</div>
		</div>
		<div class="loginContent">

			<h:panelGrid width="80%" border="0" cellpadding="0" cellspacing="0"
				columns="4">
				<h:column>
					<h:outputLabel for="nombre" value="Nombre y apellido"></h:outputLabel>
				</h:column>
				<h:column>
					<h:inputText disabled="true" id="nombre"
						value="#{logonBean.titular.nombre} , #{logonBean.titular.apellido}" />


				</h:column>
				<h:column>
					<h:outputLabel value="DNI" for="dni"></h:outputLabel>
				</h:column>
				<h:column>
					<h:inputText disabled="true" id="dni"
						value="#{logonBean.titular.nroDocumento}" />
				</h:column>
				<h:column>
					<h:outputLabel for="domicilio" value="Domicilio"></h:outputLabel>
				</h:column>
				<h:column>
					<h:inputText id="domicilio" disabled="true"
						value="#{logonBean.titular.domicilio}   #{logonBean.titular.numero}" />  &nbsp;
				
				</h:column>
				<h:column>
					<h:outputLabel for="fechaNac" value="fecha Nacimiento"></h:outputLabel>
				</h:column>
				<h:column>
					<h:inputText disabled="true" id="fechaNac"
						value="#{logonBean.titular.fechaNacimiento}" />
				</h:column>


			</h:panelGrid>
		</div>
	</div>
	<div class="formuContainer">
		<div class="loginHeader">

			<div class="tituloPortal">Poliza</div>
		</div>
		<div class="loginContent">

			<h:panelGrid width="90%" border="0" cellpadding="0" cellspacing="0"
				columns="2">
				<h:column>
					<h:outputLabel value="Nombre y apellido"></h:outputLabel>
					<h:outputText
						value="#{logonBean.titular.nombre} , #{logonBean.titular.apellido}" />
				</h:column>
				<h:column>


				</h:column>
				<h:column>
					<h:outputLabel value="DNI"></h:outputLabel>
				</h:column>
				<h:column>
					<h:outputText value="#{logonBean.titular.nroDocumento}" />
				</h:column>
				<h:column>
					<h:outputLabel value="Domicilio"></h:outputLabel>
				</h:column>
				<h:column>
					<h:outputText value="#{logonBean.titular.domicilio}" />  &nbsp;
					<h:outputText value="#{logonBean.titular.numero}" />
				</h:column>
				<h:column>
					<h:outputLabel value="fecha Nacimiento"></h:outputLabel>
				</h:column>
				<h:column>
					<h:outputText value="#{logonBean.titular.fechaNacimiento}" />  &nbsp;
				
					</h:column>

			</h:panelGrid>
		</div>
	</div>
</body>
	</html> 
</f:view>