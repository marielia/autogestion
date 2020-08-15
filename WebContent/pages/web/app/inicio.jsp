<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:ui="http://java.sun.com/jsf/facelets" 
    xmlns:f="http://xmlns.jcp.org/jsf/core"
    xmlns:h="http://xmlns.jcp.org/jsf/html"
    xmlns:t="http://myfaces.apache.org/tomahawk">
    <f:loadBundle basename="com.asecor.extranet.bundle.Messages_es_AR" var="Message"/>
<f:view> 
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
	content="text/html; charset=windows-1252" />

<link rel="stylesheet" type="text/css"
	href="<h:outputText value="HOLA"/>/css/asecor.css">



</head>
<body>
<h:form >


   
    
     </h:form>
	<!-- Menús, headers y todo lo que vaya antes del contenido -->
	<ui:insert name="contenido">
	 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="top">
      <tr>
        <td align="left" valign="middle"><div class="logoTop"><img src="<h:outputText value="{msg['contexto_sistema']}"/>/img/logo_login.png" width="50" height="46" /></div>
    <div class="tituloPortalTop">PORTAL DE ASEGURADOS</div></td>
        <td align="right" valign="middle">
        
        <div class="topData"><h:commandLink action="#{logonBean.salir}"><img src="<h:outputText value="{msg['contexto_sistema']}"/>/img/icon_logout.png" width="20" height="20" class="icon" />SALIR</h:commandLink></div>
        <div class="topData"><h:commandLink action="#{logonBean.cambiarPassword}"><img src="<h:outputText value="#{Message.contexto_sistema}"/>/img/icon_password.png" width="20" height="20" class="icon" />CAMBIAR CONTRASEÑA</h:commandLink></div>
        <div class="topData"><img src="<h:outputText value="#{Message.contexto_sistema}"/>/img/icon_user.png" width="20" height="20" class="icon" /><h:outputText value="#{logonBean.usuario.nombres} "/></div>
        </td>
      </tr>
    </table>
	   
	</ui:insert>
	<!-- Footer y otros componentes que vayan después del contenido -->
</body>
</f:view>
</html>