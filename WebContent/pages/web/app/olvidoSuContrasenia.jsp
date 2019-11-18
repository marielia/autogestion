!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<f:loadBundle basename="com.refinor.extranet.bundle.Messages" var="Message"/>
<f:view>

<html>
	<head>
		<title><h:outputText value="#{Message.nombre_sistema}"/> </title>
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/refinor.css">		
		<script language="javascript">
			var cancelo= false;
			function validar() { /*
				var cuenta = document.getElementById('frmRegistracion:cuenta');
				var nombre = document.getElementById('frmRegistracion:apellido');
				var apellido = document.getElementById('frmRegistracion:apellido');
				var tipoDocumento = document.getElementById('frmRegistracion:tipoDocumento');
				var numeroDocumento = document.getElementById('frmRegistracion:numeroDocumento');
				var numeroTelefono = document.getElementById('frmRegistracion:numeroTelefono');
				var usuario = document.getElementById('frmRegistracion:codigo');
				var email = document.getElementById('frmRegistracion:email');

				if (cuenta == '' ||
					nombre.value == '' ||
					apellido.value == '' ||
					tipoDocumento.value == '0' ||
					numeroDocumento.value == '' ||
					numeroTelefono.value == '' ||
					usuario.value == '' ||
					email.value == '') {

					alert('Debe completar los campos obligatorios.');
					return false;
				} else {
					if(emailCheck(email.value, false)) 
						return true;
					else
						email.focus();
						return false;	
				}*/				
				var email = document.getElementById('frmRegistracion:email');
				if (email.value == '') {
					alert('Debe completar los campos obligatorios.');
					return false;
				} else {
					if(emailCheck(email.value, false)) 
						return true;
					else
						email.focus();
						return false;	
				}
				
				return true;
			}

			function cancelar() {
				cancelo= true;
			}

			function emailCheck (emailStr, fehlermeldung) { 
				var notallowed = " ;:!$%/()=?*"; 
				if (fehlermeldung == false) { var fehlermeldung = "Correo no v\xE1lido.";} 
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
	
<body leftmargin="10" topmargin="10" marginwidth="10" marginheight="10" class="pagina">
<f:verbatim>
<table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#c5cdda" bgcolor="#FFFFFF">
  <tr> 
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="top">
        <tr> 
          <td width="3"><img src="/refinornet/img/inicio_degrade_top.jpg" width="3" height="61"></td>
          <td width="260" align="left" valign="top" background="/refinornet/img/degrade_top.jpg">
          <img src="/refinornet/img/logo_top.jpg" ></td>
          <td align="left" valign="middle" background="/refinornet/img/degrade_top.jpg"><table width="100%" border="0" cellspacing="10" cellpadding="0">
              <tr align="right" valign="top" class="cpo8"> 
                <td width="541">
                <h:outputText value="#{Message.informacion_actualizada_al_label}:&nbsp;" escape="false" /> 
                <h:outputText id="fechaInfAct" value="#{sessionScope.fechaInformacionActualizada}"/> 
                </td>
                <td width="20" align="center"> | </td>
                <td nowrap>Usuario: no registrado</td>
              </tr>
            </table></td>
          <td width="3" align="right" valign="top"><img src="/refinornet/img/final_degrade_top.jpg" width="3" height="61"></td>
        </tr>
      </table></td>
  </tr>
  <tr border="1"> 
    <td bgcolor="#c5cdda"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="64%">&nbsp;</td>
          <td width="36%" align="right" class="cpo8">
          <h:outputLink value="#{Message.contexto_sistema}/pages/web/app/salir.jsf">
            <h:outputText styleClass="cpo8-bold" value="#{Message.salir_header_label}" escape="false"/>
            <img src="/refinornet/img/icono_salir.gif" width="11" height="11" align="absmiddle">
         </h:outputLink>
         </td>
        </tr>
      </table></td>
  </tr>
  <tr> 
    <td height="312" align="center" valign="middle" border="1"> 
      <table border="0" align="center" cellpadding="0" cellspacing="10">
        <tr align="left" valign="top"> 
          <td width="330"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="fila2">
              <tr> 
                <td align="left" valign="top">
                <table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" class="titulos">
                    <tr> 
                      <td width="23"><img src="/refinornet/img/icono_titulos.gif" width="23" height="25"></td>
                      <td>Olvido su Contrase&ntilde;a</td>
                    </tr>
                  </table>
                  </f:verbatim>

			<!-- * Title *-->
			<h:panelGrid columns="1">			
				<h:column rendered="#{!olvidoSuContraseniaBean.mostrarResultado}">			
				<h:panelGrid  columns="1" width="85%" border="0">
				<t:column>
				<h:outputText  value="#{Message.tit_olvido_contrasenia}:" styleClass="cpo11" />
				</t:column>
				</h:panelGrid>			
				</h:column>					
				<h:column rendered="#{!olvidoSuContraseniaBean.mostrarResultado}">
				</h:column>
				<h:column rendered="#{!olvidoSuContraseniaBean.mostrarResultado}">
				<h:messages styleClass="error"/>
				</h:column>	
				
					
			
			<h:column>			
			<h:form id="frmRegistracion" rendered="#{!olvidoSuContraseniaBean.mostrarResultado}" >
				<h:panelGrid columns="2" columnClasses="cpo7, nada" cellspacing="6">
					<h:column></h:column>				
					<h:column></h:column>									
					<!-- Tipo de Documento -->
					<h:column>
						<h:outputLabel for="tipoDocumento">
							<h:outputText id="lblTipoDocumento" value="#{Message.tipo_documento_label}" style="color: #003366;"/>
						</h:outputLabel>
					</h:column>
					<h:column> 
						<h:selectOneListbox id="tipoDocumento" size="1" value="#{olvidoSuContraseniaBean.tipoDocumento}" styleClass="campo">
							<f:selectItems value="#{olvidoSuContraseniaBean.tiposDocumento}"/>
						</h:selectOneListbox>
						<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>
					</h:column>

					<!-- Nro. Documento -->
					<h:column>
						<h:outputLabel for="numeroDocumento">
							<h:outputText id="lblNumeroDocumento" value="#{Message.numero_documento_label}" style="color: #003366;" />
						</h:outputLabel>
					</h:column>
					<h:column> 
						<h:inputText id="numeroDocumento" value="#{olvidoSuContraseniaBean.numeroDocumento}" size="25" maxlength="15" styleClass="campo"/>
						<h:outputText value="&nbsp;[x]"  styleClass="datoObligatorio" escape="false"/>
					</h:column>		
					

					<h:column>
						<h:outputText value="&nbsp;" escape="false"/>
					</h:column>
					<h:column>
						<h:outputText value="&nbsp;" escape="false"/>
					</h:column>
					<h:column>
						<h:outputText value="&nbsp;" escape="false"/>
					</h:column>
					<h:column>			
					<h:graphicImage value="/img/punto.gif" height="0" width="0" style="border: none;"/>						
						<h:commandButton id="cmdAceptar" actionListener="#{olvidoSuContraseniaBean.aceptar}"  value="#{Message.aceptar_label}" styleClass="boton"/>
					</h:column>										
					<h:column><h:outputText id="datosOblig" value="#{Message.datos_obligatorios_texto}"  styleClass="datoObligatorio"/></h:column>
					<h:column><t:outputText value="&nbsp;" escape="false"/></h:column>
					<h:column><t:outputText value="&nbsp;" escape="false"/></h:column>					
				</h:panelGrid>				
			</h:form>
			
			<h:form id="resultado" rendered="#{olvidoSuContraseniaBean.mostrarResultado}">
				<h:panelGrid columns="1">
					<h:column>
						<h:outputText value="&nbsp;" escape="false"/>
					</h:column>		
					<h:column>
						<h:outputText value="#{Message.texto_registracion_resultado_linea5}" style="color: #3A25A2; font-size: 11px; line-height: 13pt;" styleClass="cpo7"/>
					</h:column>	
					<h:column>
						<h:outputText value="#{Message.texto_registracion_resultado_linea3}" style="color: #3A25A2; font-size: 11px; line-height: 13pt;" styleClass="cpo7"/>
					</h:column>	
					<h:column>
						<h:outputText value="#{Message.texto_registracion_resultado_linea4}" style="color: #3A25A2; font-size: 11px; line-height: 13pt;" styleClass="cpo7"/>
					</h:column>						
					<h:column>
						<h:outputText value="#{Message.texto_registracion_resultado_linea2}" style="vertical-align: middle; color: #3A25A2; font-size: 11px; line-height: 13pt;" styleClass="cpo7"/>
						<h:outputLink value="#{Message.contexto_sistema}/pages/web/index.jsf"><h:outputText id="txtClickAqui" value="#{Message.click_aqui_text}" styleClass="linkhome" /></h:outputLink>
					</h:column>
					<h:column>
						<h:outputText value="&nbsp;" escape="false"/>
					</h:column>	
					<h:column>
						<h:outputText value="&nbsp;" escape="false"/>
					</h:column>
					<h:column>
						<h:outputText value="&nbsp;" escape="false"/>
					</h:column>
					<h:column><t:outputText value="&nbsp;" escape="false"/></h:column>				
					<h:column><t:outputText value="&nbsp;" escape="false"/></h:column>				
					<h:column><t:outputText value="&nbsp;" escape="false"/></h:column>				
				</h:panelGrid>
			</h:form>			
			</h:column>
			</h:panelGrid>						
	    </td>
	  </tr>
	</table>
	</td>
    <td>
          <td width="296">
			  <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="290" height="265">
              <param name="movie" value="/refinornet/img/home.swf">
              <param name="quality" value="high">
              <embed src="/refinornet/img/home.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="290" height="265"></embed></object>
          </td>
    </td>
	</tr>
	</table>
	</td>
	</tr>
	</table>
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="5">
	  <tr>
	    <td align="center" valign="middle" class="cpo7"><font color="#c5cdda">&copy; 
	      2006 Federal Seguros | Requiere plug in de</font> <a href="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" target="_blank"><font color="#c5cdda">Flash</font></a> 
	      <font color="#c5cdda">| Desarrollado y dise&ntilde;ado por</font><font color="#6699CC">&nbsp; 
	      </font><a href="http://www.keepsa.com" target="_blank"><font color="#c5cdda">KEEP AND MIND S.A. ®</font></a>
	      <font color="#6699CC">&nbsp;|&nbsp; 
	      </font>
	     	      
	      <a href="/refinornet/pages/web/app/terminos_legales.html" target="_blank">
	          <font color="#c5cdda">T&eacute;rminos Legales</font>
	      </a>
	      </td>
	  </tr>
	</table>
	
</body>
</html>
</f:view>