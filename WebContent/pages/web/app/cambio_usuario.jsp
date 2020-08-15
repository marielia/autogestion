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

		<title><h:outputText value="#{Message.nombre_sistema}"/> </title>
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/asecor.css">		
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsutiles.js"></script>
		<script>
		function validar() { 				
				
				var pass = document.getElementById('frmRegistracion:pass'); 
				var repetpass = document.getElementById('frmRegistracion:repetpass'); 
				
				
				
				if (pass.value.trim().length < 4 || repetpass.value.trim().length < 4) {
						alert('La contrase\u00F1a debe tener entre 4 y 10 d\u00EDgitos.');
					    return false;
				}
				
				if (pass.value.trim() != repetpass.value.trim() ) {
					alert('La contrase\u00F1a y repita contrase\u00F1a tiene que ser iguales.');
				    return false;
			    }
				
			 
				return true;
			}
			
		
		
		
		  var numeros = "0123456789";

	      function tiene_numeros(texto) {
	          for (i = 0; i < texto.length; i++) {
	              if (numeros.indexOf(texto.charAt(i), 0) != -1) {
	                  return 1;
	              }
	          }
	          return 0;
	      }

	      var letras = "abcdefghyjklmnñopqrstuvwxyz";

	      function tiene_letras(texto) {
	          texto = texto.toLowerCase();
	          for (i = 0; i < texto.length; i++) {
	              if (letras.indexOf(texto.charAt(i), 0) != -1) {
	                  return 1;
	              }
	          }
	          return 0;
	      }

	      var letras = "abcdefghyjklmnñopqrstuvwxyz";

	      function tiene_minusculas(texto) {
	          for (i = 0; i < texto.length; i++) {
	              if (letras.indexOf(texto.charAt(i), 0) != -1) {
	                  return 1;
	              }
	          }
	          return 0;
	      }

	      var letras_mayusculas = "ABCDEFGHYJKLMNÑOPQRSTUVWXYZ";

	      function tiene_mayusculas(texto) {
	          for (i = 0; i < texto.length; i++) {
	              if (letras_mayusculas.indexOf(texto.charAt(i), 0) != -1) {
	                  return 1;
	              }
	          }
	          return 0;
	      }

	      function seguridad_clave(clave) {
	          var seguridad = 0;
	          if (clave.length != 0) {
	              if (tiene_numeros(clave) && tiene_letras(clave)) {
	                  seguridad += 30;
	              }
	              if (tiene_minusculas(clave) && tiene_mayusculas(clave)) {
	                  seguridad += 30;
	              }
	              if (clave.length >= 4 && clave.length <= 5) {
	                  seguridad += 10;
	              } else {
	                  if (clave.length >= 6 && clave.length <= 8) {
	                      seguridad += 30;
	                  } else {
	                      if (clave.length > 8) {
	                          seguridad += 40;
	                      }
	                  }
	              }
	          }
	          return seguridad
	      }
	      
		 function muestra_seguridad_clave(clave) {
	          var seguridad = seguridad_clave(clave.value);  

	          if (clave.name == "frmRegistracion:pass") {
	        	  
	        	  if(seguridad<20)
        		  {
        		   document.getElementById('frmRegistracion:lblseguridad').innerHTML =" (SEGURIDAD DÉBIL)";
        		  }
	        	  
	        	  if(seguridad>=20 && seguridad<=60)
        		  {
        		   document.getElementById('frmRegistracion:lblseguridad').innerHTML =" (SEGURIDAD MEDIA)";
        		  }
	        	  
	        	  if(seguridad>60 && seguridad<=100)
        		  {
        		   document.getElementById('frmRegistracion:lblseguridad').innerHTML =" (SEGURIDAD FUERTE)";
        		  } 
	            
	          }
	          
	          if (clave.name == "frmRegistracion:repetpass") { 
	              if(seguridad<20)
        		  {
        		   document.getElementById('frmRegistracion:lblseguridadrepetpass').innerHTML =" (SEGURIDAD DÉBIL)";
        		  }
	        	  
	        	  if(seguridad>=20 && seguridad<=60)
        		  {
        		   document.getElementById('frmRegistracion:lblseguridadrepetpass').innerHTML =" (SEGURIDAD MEDIA)";
        		  }
	        	  
	        	  if(seguridad>60 && seguridad<=100)
        		  {
        		   document.getElementById('frmRegistracion:lblseguridadrepetpass').innerHTML =" (SEGURIDAD FUERTE)";
        		  } 
	          } 

			 

	      }
		</script>
	</head>
	
<body class="cover">
<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td><%@ include file="headerTop.jsp" %></td>
  </tr>
  <tr>
  
   <td align="left">
	<div class="mainContainer">
		

			<div class="mainHeader">USUARIO</div>
		<div class="mainContentTop">
				<h:form id="frmRegistra">



					<table width="60%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="left" valign="middle"><h:messages
									styleClass="errorNegro" /></td>
						</tr>

						
						<tr>
							<td align="left" valign="top"><span
								class="mediumFont"><h:outputText
										value="#{Message.tit_nombres_tr_label}" /> </span> <br>  <label for="nom"></label></td><td>
							 <h:inputText value="#{logonBean.usuario.nombres}" id="nom"></h:inputText></td>
						</tr>
						<tr>
							<td align="left" valign="top"><span
								class="mediumFont"><h:outputText
										value="#{Message.tit_phone_label}" /> </span> <br> <label for="tel"></label></td><td>
							 <h:inputText value="#{logonBean.usuario.telefono}" id="tel"></h:inputText></td>
						</tr>
						<tr>
							<td align="left" valign="top"><span
								class="mediumFont"><h:outputText  
										value="#{Message.tit_nacimiento_tr_label}" /> </span> <br>  <label for="cumple"></label></td><td>
							 <h:inputText  disabled="true"  value="#{logonBean.usuario.fechaNacimiento}" id="cumple"></h:inputText></td>
						</tr>
						<tr>
							<td align="left" valign="top"><span
								class="mediumFont"><h:outputText
										value="#{Message.tit_documento_tr_label}" /> </span> <br> <label for="dni"></label></td><td>
							 <h:inputText disabled="true" value="#{logonBean.usuario.dni}" id="dni"></h:inputText></td>
						</tr>
						<tr>
							<td height="100"  align="center">
									<t:commandButton
									id="btnConfirmar" value="CONFIRMAR"   styleClass="botonAceptar"
									 action="#{logonBean.cambiarTelefono}"  onclick="return validar();"></t:commandButton>
									 </td><td>
									 <t:commandButton action="polizasUsuarios"
									 value="CANCELAR"   styleClass="botonAceptar"
									 ></t:commandButton>
							</td>
						</tr>
					</table>

				</h:form>


				
			</div>
		</div>
		</td>
		</tr>
		</table>
	</body>	
</f:view>
</html> 
