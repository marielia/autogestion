<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<f:loadBundle basename="com.asecor.extranet.bundle.Messages_es_AR" var="Message"/>
<html>
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
             content="text/html; charset=windows-1252"/>
		
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/asecor.css">		
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsutiles.js"></script>
		<script>
		function validar() { 				
				var nombres = document.getElementById('frmRegistracion:nombres');
				var fechaNacimiento = document.getElementById('frmRegistracion:fechaNacimiento');
				var telefono = document.getElementById('frmRegistracion:telefono');
				var email = document.getElementById('frmRegistracion:email');
				//var optPN = document.forms['frmRegistracion']['frmRegistracion:rdFormaRegistracion'][1].checked ;
				//var optEM = document.forms['frmRegistracion']['frmRegistracion:rdFormaRegistracion'][0].checked ; 
				// var opt=true;
				 
				//if(optPN == false && optEM == false) 
				//	opt = false;
			 	
				
				if (nombres.value.trim() == '' ||	 					
						fechaNacimiento.value.trim() == '' ||  			
					telefono.value.trim() == '' ||	 					
					email.value.trim() == '' 
						 ) {
						alert('Debe completar todos los campos.');
					    return false;
				} else {
					if(emailCheck(email.value.trim(), false)) {
						
						//if(optPN == true)
						//{
						//	alert('La opcion registrarse por celular esta en desarrollo.');
						//	return false;	
						//}
						
						return true;
					}
					else
						email.focus();
						return false;	
				}	 
				
				return true;
			}
			
			function emailCheck (emailStr, fehlermeldung) { 
					var notallowed = " ;:!$%/()=?*"; 
					if (fehlermeldung == false) { var fehlermeldung = "La cuenta de Email no es v\xE1lida.";} 
					var pos = emailStr.lastIndexOf("@"); 
					var pos2 = emailStr.lastIndexOf("."); 
					if ((pos <= 0) || (pos == emailStr.length - 1) || 
					(pos2 <= 0) || (pos2 == emailStr.length - 1) || (pos2 <= pos + 2)) 
					{alert(fehlermeldung); return false;} 
					for (i=0; i < notallowed.length; i++) { 
						var pos = emailStr.indexOf( notallowed.charAt(i) ); 
						if (pos > -1) {alert(fehlermeldung); return false;} 
					} 
					return true; 
				} 
		</script>
	</head>
	
<body class="cover">
<div class="loginContainer">
  <div class="loginHeader">
    <div class="logoLogin"><img src="<h:outputText value="#{Message.contexto_sistema}"/>/img/logo_login.png" width="65" height="61" /></div>
    <div class="tituloPortal">PORTAL DE ASEGURADOS</div>
<div class="tituloLogin">REGISTRO DE USUARIO</div>
  </div>
  <div class="loginContent">
<h:form id="frmRegistracion" rendered="#{!userRegisterBean.mostrarResultado}" onsubmit="return validar();">	 
				
	
	 
	      
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td align="left" valign="top">  
	            <h:messages styleClass="errorNegro"/>
	            </td> 
	         </tr>
         
	          <tr>
	            <td colspan="2" align="left" valign="top">
	               <span class="mediumFont"><h:outputLabel for="dni" value="#{Message.tit_dni_tr_label}" /></span>	             
 	                <t:inputText id="dni" value="#{userRegisterBean.dni}"  maxlength="20"    />
 	              </td>
	          </tr>
	            <tr>
	          <td colspan="2" align="left" valign="top">
	               
	              <label for="nombres"><span class="mediumFont"><h:outputLabel value="#{Message.tit_nombres_tr_label}" /></span></label>
 	              <t:inputText id="nombres" value="#{userRegisterBean.nombres}"    maxlength="99"  />
	              </td>
	          </tr>
	          <tr>
	            <td colspan="2" align="left" valign="top">
	                
	              <label for="fechaNacimiento" >
	              <span class="mediumFont"><h:outputLabel value="#{Message.tit_nacimiento_tr_label}" /></span>
	              </label>
	              <br>
   	              <t:inputDate type="date" id="fechaNacimiento" styleClass="fechass" value="#{userRegisterBean.fechaNacimiento}"  />  
	             </td>
	          </tr>
	           <tr>
	            <td height="60" colspan="2" align="left" valign="top">
	              <label for="telefono" >
	               <span class="mediumFont"><h:outputLabel value="#{Message.tit_phone_label}" /></span>
 	                </label>
 	              <t:inputText id="telefono" value="#{userRegisterBean.phoneNumber}"   maxlength="30"   />
				 </td>				
	          </tr>
	          <tr>
	            <td height="60" colspan="2" align="left" valign="top">
	            <label for="email" >
	               <span class="mediumFont"><h:outputText value="#{Message.tit_email_label}" /></span>
 	             </label>
 	              <t:inputText id="email" value="#{userRegisterBean.email}"   maxlength="80"   />
				 </td>				
	          </tr>
	         
	         
	       
	          
	          <tr>
	            <td height="100" colspan="2" align="center" valign="top"> 
	            <br>
	                 <t:saveState value="#{userRegisterBean.comesFrom}"></t:saveState>
	               	<h:commandButton type="submit" value="REGISTRAR"  action="#{userRegisterBean.aceptar}"  styleClass="botonAceptar"></h:commandButton>
			</td>
	          </tr>
	          
	          
	        </table>
	    

 	</h:form>
 	
 	
 	<h:form id="frmFiltroCuenta2" rendered="#{userRegisterBean.mostrarResultado}" onsubmit="return validar();">	 
				
	
	  <table width="100%" border="0" cellspacing="40" cellpadding="0">
	    
	    <tr>
	      <td align="center" valign="middle" class="title"><h:outputText value="#{Message.tit_form_registracion}" /></td>
	    </tr>
	     	
	    <tr>
	      <td align="center" valign="top"> 
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td colspan="2" align="left" valign="top"> 
 	            	 <h:outputText value="#{Message.texto_registracion_resultado_linea1}"    />
 	            </td>
	          </tr> 
 	        
	        </table>
	      </td>
	    </tr>
	    
	    
	  </table>
	
 	</h:form>	
 	</div>
 	</div>		
</body>	
</f:view>
</html> 

