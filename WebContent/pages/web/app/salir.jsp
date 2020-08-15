<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.asecor.extranet.util.Const"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<f:loadBundle basename="com.asecor.extranet.bundle.Messages_es_AR" var="Message"/>
<html>
<f:view>
	<head>
        <meta http-equiv="Content-Type"
             content="text/html; charset=windows-1252"/>
		<title><h:outputText value="#{Message.nombre_sistema}"/> </title>
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/login_ol.css">		
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsutiles.js"></script>
		 
	</head>
	
<body class="cover">
<h:form id="frmSalir"  >	 
				
	<div class="loginContainer">
	  <table width="100%" border="0" cellspacing="40" cellpadding="0">
	   
	    <tr>
	      <td align="center" valign="middle" class="title"> <h:outputText value="#{Message.tit_cierre_sesion}"    /></td>
	    </tr>
	     	
	    <tr>
	      <td align="center" valign="top">
	      
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	             <td colspan="2" align="left" valign="top"> 
	                <h:outputText value="#{Message.cierre_sesion_texto1}"    />
 	              </td>
	          </tr> 
	           
	        </table>
	       </td>
	    </tr>
	     <tr>
	    	<td align="left" valign="middle" > </td>
	     </tr>	
	      <tr>
	    	<td align="left" valign="middle" > </td>
	     </tr>	
	      <tr>
	    	<td align="left" valign="middle" > </td>
	     </tr>	
	      <tr>
	    	<td align="left" valign="middle" > </td>
	     </tr>
	      <tr>
	    	<td align="left" valign="middle" > </td>
	     </tr>
	      <tr>
	    	<td align="left" valign="middle" > </td>
	     </tr>	
	  </table>
	</div>
 	</h:form> 
 	
 	<% 
	request.getSession().setAttribute( Const.COMES_FROM, null);
  
	request.getSession().invalidate();	
	%>
	
</body>	
</f:view>
</html> 

