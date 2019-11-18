<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<f:loadBundle basename="com.edenor.plus.bundle.Messages" var="Message"/>
<jsp:useBean id="registracionBean" scope="request" type="com.edenor.plus.faces.web.RegistracionBean" />				

<html>
	<head>
		<title>Actualización de Datos</title>
		<link rel="stylesheet" type="text/css" href="../css/edenorplus.css">
		<link rel="stylesheet" type="text/css" href="../css/edenorplusweb.css">		
		<script language="javascript">
			function validar() {
				var nombre = document.getElementById('frmActualizacion:apellido');
				var apellido = document.getElementById('frmActualizacion:apellido');
				var tipoDocumento = document.getElementById('frmActualizacion:tipoDocumento');
				var numeroDocumento = document.getElementById('frmActualizacion:numeroDocumento');
				var numeroTelefono = document.getElementById('frmActualizacion:numeroTelefono');
				var email = document.getElementById('frmActualizacion:email');

				if (nombre.value == '' ||
					// apellido.value == '' ||
					tipoDocumento.value == '0' ||
					numeroDocumento.value == '' ||
					numeroTelefono.value == '' ||
					email.value == '') {

					alert('Debe completar los campos obligatorios.');
					return false;
				} else {
					return true;
				}
			}

			function validarCuenta() {
				if (document.getElementById('frmActualizacion:cuentaNueva').value == '') {
					alert('Debe ingresar la cuenta.');
					return false;
				} else {
					return true;
				}  
			}

			function borrarCuenta() {
				return confirm('¿Está seguro que desea eliminar la cuenta?');
			}
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0">
		<f:view>
			<jsp:include page="tabla_marco_enc_logon.jsp" />
			<f:verbatim><table border="0"></f:verbatim>
			<h:form id="frmActualizacion"><f:verbatim><tr>
			<td valign="top"></f:verbatim>					
			<!-- * Title *-->
			<h:panelGrid columns="1">					
			<h:column>
			<h:outputText  value="<img src='/plus/img/tit-tusdatos.gif' align='right' border='0'>" escape="false"/>
			</h:column>
				
			<h:column>
		
			<h:messages style="color: red"/>

			
				<h:inputHidden value="#{registracionBean.id}" />
				<h:panelGrid columns="2" columnClasses="datoNegritaR, nada, datoNegritaR, nada">
					<!-- Nombre -->
					<h:column>
						<h:outputLabel for="nombre" styleClass="PARRAFOPLUS_NORMAL">
							<h:outputText id="lblNombre" value="#{Message.nombre_razsoc_label}" />
						</h:outputLabel>
					</h:column>
					<h:column> 
						<h:inputText id="nombre" value="#{registracionBean.nombre}" size="25" maxlength="50" styleClass="inputText"></h:inputText>
					</h:column>
					
					<!-- Apellido -->
					<h:column>
						<h:outputLabel for="apellido" styleClass="PARRAFOPLUS_NORMAL">
							<h:outputText id="lblApellido" value="#{Message.apellido_label}" />
						</h:outputLabel>
					</h:column>
					<h:column> 
						<h:inputText id="apellido" value="#{registracionBean.apellido}" size="25" maxlength="50" styleClass="inputText"></h:inputText>
					</h:column>

					<!-- Tipo de Documento -->
					
					<!-- Nro. Documento -->
					<h:column>
						<h:outputLabel for="numeroDocumento" styleClass="PARRAFOPLUS_NORMAL">
							<h:outputText id="lblNumeroDocumento" value="Documento (tipo/Nro)" />
						</h:outputLabel>
					</h:column>
					<h:column> 
						<h:selectOneListbox id="tipoDocumento" size="1" value="#{registracionBean.tipoDocumento}"  styleClass="SelectDNI">
							<f:selectItems value="#{registracionBean.tiposDocumento}"/>
						</h:selectOneListbox>
						<h:inputText id="numeroDocumento" value="#{registracionBean.numeroDocumento}" size="12" maxlength="15" styleClass="inputText" />
					</h:column>

					<!-- Nro. Teléfono -->
					<h:column>
						<h:outputLabel for="numeroTelefono" styleClass="PARRAFOPLUS_NORMAL">
							<h:outputText id="lblNumeroTelefono" value="#{Message.numero_telefono_label}" />
						</h:outputLabel>
					</h:column>
					<h:column> 
						<h:inputText id="numeroTelefono" value="#{registracionBean.numeroTelefono}" size="25" maxlength="20" styleClass="inputText" />
					</h:column>
					
					<!-- Tipo de Cliente -->
					

					<!-- Usuario -->
					<h:column>
						<h:outputText id="lblCodigo" value="#{Message.username_label}"  styleClass="PARRAFOPLUS_NORMAL"/>
					</h:column>
					<h:column> 
						<h:outputText value="#{registracionBean.codigo}" />
						<h:inputHidden value="#{registracionBean.codigo}" />
					</h:column>

					<!-- Email -->
					<h:column>
						<h:outputLabel for="email" styleClass="PARRAFOPLUS_NORMAL">
							<h:outputText id="lblEmail" value="#{Message.email_label}" />
						</h:outputLabel>
					</h:column>
					<h:column> 
						<h:inputText id="email" value="#{registracionBean.email}" size="25" maxlength="80" styleClass="inputText" />
					</h:column>

					<!-- Recine Info. Adicional -->
					<h:column>
						<h:outputLabel for="recibeInfoAdicional" styleClass="PARRAFOPLUS_NORMAL">
							<h:outputText id="lblRecibeInfoAdicional" value="#{Message.recibe_info_adicional_label}" />
						</h:outputLabel>
					</h:column>
					<h:column> 
						<h:selectOneRadio id="recibeInfoAdicional" value="#{registracionBean.recibeInfoAdicional}"  >
							<f:selectItem itemLabel="Si" itemValue="S" />
							<f:selectItem itemLabel="No" itemValue="N" />							
						</h:selectOneRadio>
					</h:column>
				</h:panelGrid>
				<h:panelGrid columns="3" rendered="#{registracionBean.id != null}">
					<!-- Cuenta -->
					<h:column>
						<h:outputLabel for="cuentaNueva" styleClass="datoNegrita">
							<h:outputText id="lblCuentaNueva" value="#{Message.cuenta_label}" />
						</h:outputLabel>
						<h:outputText  value="<br>" escape="false"/>
						<h:outputText  value="<br>" escape="false"/>
					</h:column>
					<h:column> 
						<h:inputText id="cuentaNueva" value="#{registracionBean.cuentaNueva}" size="12" maxlength="12" styleClass="inputText" />
						<h:outputText  value="<br>" escape="false"/>
						<h:commandButton id="cmdAgregarCuenta" action="#{registracionBean.agregarCuenta}" value="#{Message.agregar_cuenta_label}" styleClass="boton" onclick="javascript: return validarCuenta();" />
						<!--h:commandButton id="cmdBorrarCuenta" action="#{registracionBean.borrarCuenta}" value="#{Message.borrar_cuenta_label}" styleClass="boton" onclick="javascript: return borrarCuenta();" /-->
					</h:column>
					<h:column>
						<h:selectOneListbox id="cuenta" size="2" value="#{registracionBean.cuenta}">
							<f:selectItems value="#{registracionBean.cuentas}"/>
						</h:selectOneListbox>					
					</h:column>				
				</h:panelGrid>
				<h:panelGrid columns="1">
					<h:column>
						<h:outputLink value="/plus/pages/web/app/cambiopwdsitio.jsf" style="font-weight: bold; ">
							<h:outputText value="#{Message.cambio_password_label}  " />
						</h:outputLink>
						<h:commandButton id="cmdAceptar" action="#{registracionBean.aceptar}" onmouseout="this.src='/plus/img/bot-aceptar-on.gif'" onmouseover="this.src='/plus/img/bot-aceptar-off.gif'" image="/plus/img/bot-aceptar-on.gif" onclick="javascript: return validar();" />
						<!--h:commandButton id="cmdCancelar" action="#{registracionBean.cancelar}" value="#{Message.cancelar_label}" styleClass="boton"/-->
					</h:column>
				</h:panelGrid>											
			</h:column>
			</h:panelGrid>					
			<f:verbatim>
			</td>
			<td width="20"></td>
			</tr></f:verbatim></h:form>
			<f:verbatim></table></f:verbatim>				
			<jsp:include page="tabla_marco_pie_logon.jsp" />
		</f:view>
	</body>	
</html>  
