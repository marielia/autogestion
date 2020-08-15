

	<head>
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/asecor.css">		
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsutiles.js"></script>
		<script>
		function validar() { 				
				var pin = document.getElementById('frmRegistracion:pin');
				var pass = document.getElementById('frmRegistracion:pass'); 
				var repetpass = document.getElementById('frmRegistracion:repetpass'); 
				
				if (pin.value.trim() == ''   ||		
						pass.value.trim() == '' ||	 					
						repetpass.value.trim() == ''    ) {
						alert('Debe completar todos los campos.');
					    return false;
				}  	
				
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

		<div class="loginContainer">
			<div class="loginHeader">
				<div class="logoLogin">
					<img
						src="<h:outputText value="#{Message.contexto_sistema}"/>/img/logo_login.png"
						width="65" height="61" />
				</div>
				<div class="tituloPortal">PORTAL DE ASEGURADOS</div>
				<div class="tituloLogin">
					<h:outputText value="#{Message.confirmacion_label}" />
				</div>
			</div>
			<div class="loginContent">
				<h:form id="frmRegistracion"
					rendered="#{!registrationConfirmationBean.mostrarResultado}"
					onsubmit="return validar();">



					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="left" valign="middle"><h:messages
									styleClass="errorNegro" /></td>
						</tr>

						
						<tr>
							<td colspan="2" align="left" valign="top"><span
								class="mediumFont"><h:outputText
										value="#{Message.tit_contrase_label}" /> </span> <br> <span
								class="smallFontSec"> <t:outputText id="seguridad">
									</t:outputText> <t:outputText id="lblseguridad" value=" "></t:outputText>
							</span> <label for="pass"></label> <h:inputSecret id="pass"
									styleClass="password"
									value="#{registrationConfirmationBean.pass}" maxlength="10"
									onkeypress="muestra_seguridad_clave(this)">
									<f:validateLength minimum="1" />
								</h:inputSecret></td>
						</tr>
						<tr>
							<td colspan="2" align="left" valign="top"><span
								class="mediumFont"><h:outputText
										value="#{Message.tit_repetir_contrase_label}" /> </span> <br> <span
								class="smallFontSec"> <t:outputText
										id="seguridadrepetpass">
									</t:outputText> <t:outputText id="lblseguridadrepetpass" value=" "></t:outputText>
							</span> <label for="repetpass"></label> <h:inputSecret id="repetpass"
									styleClass="password"
									value="#{registrationConfirmationBean.repetpass}"
									maxlength="10" onkeypress="muestra_seguridad_clave(this)">
									<f:validateLength minimum="1" />
								</h:inputSecret></td>
						</tr>
						<tr>
							<td height="100" colspan="2" align="center"><t:saveState
									value="#{registrationConfirmationBean.email}"></t:saveState> 
									<t:commandButton
									id="btnConfirmar" value="CONFIRMAR"   styleClass="botonAceptar"
									 actionListener="#{registrationConfirmationBean.aceptar}" ></t:commandButton>
							</td>
						</tr>
					</table>

				</h:form>


				<h:form id="frmFiltroCuenta2"
					rendered="#{registrationConfirmationBean.mostrarResultado}">

					<div class="loginContainer">
						<table width="100%" border="0" cellspacing="40" cellpadding="0">

							<tr>
								<td align="center" valign="middle" class="title"><h:outputText
										value="#{Message.confirmacion_label}" /></td>
							</tr>

							<tr>
								<td align="center" valign="top">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan="2" align="left" valign="top"><t:saveState
													value="#{registrationConfirmationBean.comesFrom}"></t:saveState>
												<h:outputText
													value="#{Message.texto_confirmacion_resultado_linea} " />
												<h:outputLink
													value="#{Message.contexto_sistema}/pages/web/indexInterno.jsf?comesFrom=#{registrationConfirmationBean.comesFrom}&page=REG">
													<h:outputText id="txtClickAqui"
														value="#{Message.click_aqui_text}" styleClass="linkhome" />
												</h:outputLink></td>
										</tr>

									</table>
								</td>
							</tr>


						</table>
					</div>
				</h:form>
			</div>
		</div>
	</body>	


