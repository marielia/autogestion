<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<f:loadBundle basename="com.asecor.extranet.bundle.Messages_es_AR"
	var="Message" />
<f:view>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/asecor.css">
<title></title>

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




<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Asecor - Portal de Asegurados</title>
<link rel="stylesheet" type="text/css"
	href="<h:outputText value="#{Message.contexto_sistema}"/>/css/asecor.css">
<script type="text/javascript"
	src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsutiles.js"></script>
<script>
	function validar() {
		var user = document.getElementById('frmIngreso:user');
		var password = document.getElementById('frmIngreso:password');

		if (user.value == '' || password.value == '') {
			alert('Debe completar todos los campos.');
			return false;
		}

		return true;
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
				<div class="tituloLogin">LOGIN</div>
			</div>
			<div class="loginContent">
				<h:form id="frmIngreso" rendered="#{logonBean.mostrarPagina}"
					onsubmit="return validar();">

					<c:if test="${!logonBean.puedeIngresar}">
						<c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
					</c:if>

					<div class="loginContainer">

									<table width="100%" border="0" cellspacing="0" cellpadding="0">

										<tr>
											<td align="left" valign="top"><h:messages
													styleClass="errorNegro" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><span class="mediumFont"><h:outputText
														value="#{Message.tit_email_label}" /> </span> <label for="dni"></label>
												<t:inputText id="user" value="#{logonBean.email}"
													maxlength="50" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><span class="mediumFont"><h:outputText
														value="#{Message.tit_contrase_label}" /> </span>  <label for="password"></label><h:inputSecret
													id="password" value="#{logonBean.clave}" maxlength="10" styleClass="password">
													<f:validateLength minimum="1"  />
												</h:inputSecret></td>
										</tr>
										<tr>
										
											<td align="center" valign="top"><br>
											<t:saveState
													value="#{logonBean.comesFrom}"></t:saveState> 
													<t:commandButton  value="INGRESAR" action="#{logonBean.chkLogon}" styleClass="botonAceptar"></t:commandButton>
												
													</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td align="center" valign="top">
												<p>

													<h:outputLink
														value="#{Message.contexto_sistema}/pages/web/app/registracion.jsf">
														<h:outputText id="txtClickAqui"
															value="#{Message.registrarse_label}" />
													</h:outputLink>


												</p>
												<p>
													<h:outputLink
														value="#{Message.contexto_sistema}/pages/web/app/olvido_password.jsf">
														<h:outputText id="txtClickAqui1"
															value="#{Message.Olvido_label}" />
													</h:outputLink>



												</p>


											</td>
										</tr>
									</table>
								
					</div>




				</h:form>
			</div>
		</div>

	</body>
</f:view>
</html>