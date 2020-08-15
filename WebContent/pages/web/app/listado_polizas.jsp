<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<f:loadBundle basename="com.asecor.extranet.bundle.Messages_es_AR"
	var="Message" />
<f:view>



	<html>
<%@ include file="header.jsp" %>
<body class="cover">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><h:form >


    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="top">
      <tr>
        <td align="left" valign="middle"><div class="logoTop"><img src="<h:outputText value="#{Message.contexto_sistema}"/>/img/logo_login.png" width="50" height="46" /></div>
    <div class="tituloPortalTop">PORTAL DE ASEGURADOS</div></td>
        <td align="right" valign="middle">
        
       <div class="topData"><h:commandLink action="#{logonBean.salir}"><img src="<h:outputText value="#{Message.contexto_sistema}"/>/img/icon_logout.png" width="20" height="20" class="icon" />SALIR</h:commandLink></div>
        <div class="topData"><h:commandLink action="cambio_password"><img src="<h:outputText value="#{Message.contexto_sistema}"/>/img/icon_password.png" width="20" height="20" class="icon" />CAMBIAR CONTRASEÑA</h:commandLink></div>
        <div class="topData"><h:commandLink action="cambio_usuario"><img src="<h:outputText value="#{Message.contexto_sistema}"/>/img/icon_user.png" width="20" height="20" class="icon" /><h:outputText value="#{logonBean.usuario.nombres} "/></h:commandLink></div>
            </td>
      </tr>
    </table>
    
     </h:form></td>
  </tr>
  <tr>
  
 <td align="left">
	<div class="mainContainer">
		

			<div class="mainHeader">DATOS TITULAR</div>
		<div class="mainContentTop">
		

			<h:panelGrid width="90%" border="0" cellpadding="5" cellspacing="1"
				columns="4" styleClass="mainTable">
				<h:column>

					<h:outputLabel for="nombre" value="NOMBRE Y APELLIDO"></h:outputLabel>
				</h:column>
				<h:column>
					<h:inputText  id="nombre" disabled="true"
						value="#{logonBean.titular.nombre},#{logonBean.titular.apellido} " />


				</h:column>
				<h:column>
					<h:outputLabel value="#{Message.tit_documento_tr_label}" for="dni"></h:outputLabel>
				</h:column>
				<h:column>
					<h:inputText disabled="true" id="dni"
						value="#{logonBean.titular.nroDocumento}" />
				</h:column>
				<h:column>
					<h:outputLabel for="domicilio" value="#{Message.tit_domicilio_tr_label}"></h:outputLabel>
				</h:column>
				<h:column>
					<h:inputText id="domicilio" disabled="true"
						value="#{logonBean.titular.domicilioCompleto} " />  &nbsp;
				
				</h:column>
				<h:column>
					<h:outputLabel for="fechaNac" value="#{Message.tit_nacimiento_tr_label}"></h:outputLabel>
				</h:column>
				<h:column>
					<h:inputText disabled="true" id="fechaNac"
						value="#{logonBean.titular.fechaNacimiento}" />
				</h:column>
				

			</h:panelGrid>
		</div>
		</div>
	
	<div class="mainContainer">
		<div class="mainHeader">
			Poliza</div>
	
		<div class="mainContentTop">
			<h:form>
				<h:dataTable width="100%" value="#{logonBean.polizas}" border="0"  
					var="poliza" styleClass="mainTable" rowClasses = "row1,row2" headerClass="mainTableTop">
					


					<h:column >
						<f:facet name="header" >
							<h:outputLabel value="#{Message.tit_nro_certificado_label}" />
						</f:facet>

						<h:outputText value="#{poliza.nroCertificado}"></h:outputText>

					</h:column>
					<h:column >
						<f:facet name="header">
							<h:outputLabel value="#{Message.tit_nro_poliza_label}" />
						</f:facet>
						<h:outputText value="#{poliza.nroPoliza}"></h:outputText>

					</h:column>
					<h:column >
						<f:facet name="header">
								<h:outputLabel value="#{Message.tit_estado_poliza_label}" />
					
						</f:facet>
						<h:outputText value="#{poliza.estado}"></h:outputText>

					</h:column >
					<h:column >
						<f:facet name="header" >
							<h:outputLabel value="#{Message.tit_forma_pago_label}" />
						</f:facet>
						<h:outputText value="#{poliza.formaPago}"></h:outputText>

					</h:column>
					
					<h:column  >
						<f:facet name="header" >
							<h:outputText value="Fecha de Alta"></h:outputText>
						</f:facet>
						<h:outputText value="#{poliza.fechaAlta}"></h:outputText>

					</h:column>
					<h:column  >
					<f:facet name="header" >
							<h:outputText value=" "></h:outputText>
						</f:facet>

<h:commandLink styleClass="ver" action="#{consultaPoliza.detallePolizas(poliza)}" >VER</h:commandLink>


						


					</h:column>
				</h:dataTable>
			</h:form>
		</div>
	</div>
	<td>
	</tr>
	</table>
</body>
	</html>
</f:view>