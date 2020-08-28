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
<%@ include file="header.jsp"%>

<body class="cover">

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><h:form>


					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="top">
						<tr>
							<td align="left" valign="middle"><div class="logoTop">
									<img
										src="<h:outputText value="#{Message.contexto_sistema}"/>/img/logo_login.png"
										width="50" height="46" />
								</div>
								<div class="tituloPortalTop">PORTAL DE ASEGURADOS</div></td>
							<td align="right" valign="middle">

								<div class="topData">
									<h:commandLink action="#{logonBean.salir}">
										<img
											src="<h:outputText value="#{Message.contexto_sistema}"/>/img/icon_logout.png"
											width="20" height="20" class="icon" />SALIR</h:commandLink>
								</div>
								<div class="topData">
									<h:commandLink action="cambio_password">
										<img
											src="<h:outputText value="#{Message.contexto_sistema}"/>/img/icon_password.png"
											width="20" height="20" class="icon" />CAMBIAR CONTRASE&Ntilde;A</h:commandLink>
								</div>
								<div class="topData">
									<h:commandLink action="cambio_usuario">
										<img
											src="<h:outputText value="#{Message.contexto_sistema}"/>/img/icon_user.png"
											width="20" height="20" class="icon" />
										<h:outputText value="#{logonBean.usuario.nombres} " />
									</h:commandLink>
								</div>
								<div class="topData">
									<h:commandLink action="polizasUsuarios">
										<img
											src="<h:outputText value="#{Message.contexto_sistema}"/>/img/home.png"
											width="20" height="20" class="icon" />
										<h:outputText value="INICIO" />
									</h:commandLink>
								</div>
							</td>
						</tr>
					</table>

				</h:form></td>
		</tr>
		<tr>

			<td align="left">
				<div class="mainContainer">


					<div class="mainHeader">DATOS CERTIFICADO</div>
					<div class="mainContentTop">


						<h:panelGrid width="90%" border="0" cellpadding="5"
							cellspacing="1" columns="4" styleClass="mainTable">

							<h:column>
								<h:outputLabel styleClass="mediumFont"
									value="#{Message.tit_nro_certificado_label}  "></h:outputLabel>
							</h:column>
							<h:column>
								<h:inputText disabled="true"
									value="#{consultaPoliza.poliza.nroCertificado} " />

							</h:column>
							<h:column>
								<h:outputLabel styleClass="mediumFont"
									value="#{Message.tit_nro_poliza_label}  "></h:outputLabel>
							</h:column>
							<h:column>
								<h:inputText disabled="true"
									value="#{consultaPoliza.poliza.nroPoliza} " />

							</h:column>
							<h:column>
								<h:outputLabel styleClass="mediumFont"
									value="#{Message.tit_compania}  "></h:outputLabel>
							</h:column>
							<h:column>
								<h:inputText disabled="true"
									value="#{consultaPoliza.poliza.compania} " />
							</h:column>
							<h:column>
								<h:outputLabel styleClass="mediumFont"
									value="#{Message.tit_fecha_alta_label}  :"></h:outputLabel>

							</h:column>
							<h:column>
								<h:inputText disabled="true"
									value="#{consultaPoliza.poliza.fechaAlta}" />
							</h:column>
							<h:column>
								<h:outputLabel styleClass="mediumFont"
									value="#{Message.tit_vigencia_hasta_label}  :"></h:outputLabel>
							</h:column>
							<h:column>
								<h:inputText disabled="true" value="INDETERMINADO" />
							</h:column>

							<h:column>
								<h:outputLabel styleClass="mediumFont"
									value="#{Message.tit_planes_contratados_label}  :"></h:outputLabel>
							</h:column>
							<h:column>
								<h:inputText disabled="true"
									value="#{consultaPoliza.poliza.plan}" />

							</h:column>
							
							<h:column rendered="#{consultaPoliza.poliza.codEstadoPoliza eq 'X' }">
								<h:outputLabel for="motAnu" styleClass="mediumFont"
									value="#{Message.tit_anulacion_tr_label}"></h:outputLabel>
							</h:column>
							<h:column >
							
								<h:inputText disabled="true" id="motAnu" rendered="#{consultaPoliza.poliza.codEstadoPoliza eq 'X' }"
									value="#{consultaPoliza.poliza.detalleAnulacion}" />
							</h:column>
							<h:column rendered="#{consultaPoliza.poliza.codEstadoPoliza eq 'X' }">
								<h:outputLabel for="fechanu" styleClass="mediumFont"
									value="#{Message.tit_fecha_anulacion_tr_label}"></h:outputLabel>
							</h:column>
							<h:column rendered="#{consultaPoliza.poliza.codEstadoPoliza eq 'X'}">
								<h:inputText disabled="true" id="fechanu"
									value="#{consultaPoliza.poliza.fechaAnulacion}" />
							</h:column>
						</h:panelGrid>

					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>

				<div class="mainContainer">
					<div class="mainHeader">COBERTURAS</div>
					<div class="mainContentTop">

						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td><p>
										* <strong>Sepelio</strong>: Cobertura del mismo en caso de
										fallecimiento. Traslado incluido a NIVEL NACIONAL.
										Prestaci&#243;n en cualquier punto del pa&#237;s. <br /> *
										<strong>Inhumaci&#243;n</strong>: Cobertura del alquiler de la
										parcela o nicho por 5 a&#241;os; o bien, la
										cremaci&#243;n&#46;<br /> * <strong>Gastos
											Emergentes</strong>: indemnizaci&#243;n para cubrir gastos ocultos del
										sepelio (impuestos, tasas, coronas, publicaciones, viaje de
										algun familiar, etc&#46;)
									</p>
									<p>
										Beneficios en medicina primaria: <br /> * <strong>M&#233;dico
											a domicilio</strong> 8 a 20 hs.(Solo ciudad de Cordoba) &#45; Con Co
										seguro llamando al 0351-5689861; o al 0800-345-6445 (fin de
										semanas)&#46;<br /> * <strong>Consultorios
											m&#233;dicos</strong> en 25 de mayo 425 &#45; Cordoba&#45; Con Co
										seguro Turnos: 0351&#45;5689861 <br /> * <strong>Plan
											Integral de Odontolog&#237;a</strong> &#45; 50 % de descuento de valor
										del mercado. Turnos: 0351&#45;5689861<br /> * <strong>Descuentos
											en farmacia</strong>: Farmavirtual - 27 de Abril 99 <br /> &#32;
										&#8226; 15% de descuento en la compra de medicamentos,
										perfumer&#237;a, jugueter&#237;a, empresariales,
										dermocosm&#233;tica&#46;<br /> &#32; &#8226; 30% de descuento
										en vadem&#233;cum propio (no acumulativo con otros descuentos)
									</p></td>
							</tr>
						</table>

					</div>
				</div>
			</td>
		</tr>

		<tr>
			<td align="center">
				<div class="mainContainer">
					<div class="mainHeader">ASEGURADOS</div>
					<div class="mainContent">
						<h:dataTable width="90%" border="0" styleClass="mainTable"
							rowClasses="row1,row2" headerClass="mainTableTop"
							value="#{consultaPoliza.adherentes}" var="adherente">
							<h:column>
								<f:facet name="header">
									<h:outputLabel value="#{Message.tit_nombres_tr_label}" />
								</f:facet>
								<h:outputText value="#{adherente.nombre} "></h:outputText>
								<h:outputText value="#{adherente.apellido} "></h:outputText>
							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputLabel value="#{Message.tit_parentesco_tr_label}" />
								</f:facet>
								<h:outputText value="#{adherente.parentesco} "></h:outputText>

							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputLabel value="#{Message.tit_documento_tr_label}" />
								</f:facet>
								<h:outputText value="#{adherente.nroDocumento} "></h:outputText>

							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputLabel value="#{Message.tit_nacimiento_tr_label}" />
								</f:facet>
								<h:outputText value="#{adherente.fechaNacimientoString} "></h:outputText>

							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputLabel value="EDAD" />
								</f:facet>
								<h:outputText value="#{adherente.edad} "></h:outputText>

							</h:column>
						</h:dataTable>

					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td align="center">
				<div class="mainContainer">
					<div class="mainHeader">CUENTA CORRIENTE Y FORMA DE PAGO</div>
					<div class="mainContent">
						<h:panelGrid width="90%" border="0" cellpadding="0"
							cellspacing="0" columns="4">

							<h:column>
								<h:outputLabel value="#{Message.tit_premio_mensual_tr_label}  " />
							</h:column>
							<h:column>
								<h:inputText disabled="true"
									value="#{consultaPoliza.poliza.premio} " />

							</h:column>
							<h:column>
								<h:outputLabel value="#{Message.tit_forma_pago_label}   "></h:outputLabel>
							</h:column>
							<h:column>
								<h:inputText disabled="true"
									value="#{consultaPoliza.poliza.formaPago} " />

							</h:column>
						</h:panelGrid>

						<h:dataTable width="90%" border="0" styleClass="mainTable"
							rowClasses="row1,row2" headerClass="mainTableTop"
							value="#{consultaPoliza.cobranzas}" var="cobranza">


							<h:column>
								<f:facet name="header">
									<h:outputLabel value="#{Message.tit_numero_cuota_label}" />
								</f:facet>

								<h:outputText value="#{cobranza.nroCuota}"></h:outputText>

							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{Message.tit_fecha_vto_label}"></h:outputText>
								</f:facet>

								<h:outputText value="#{cobranza.fechaInicio}"></h:outputText>

							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{Message.tit_importe_cuota_label}"></h:outputText>
								</f:facet>

								<h:outputText value="#{cobranza.importe}"></h:outputText>

							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{Message.tit_importe_pago_label}"></h:outputText>
								</f:facet>

								<h:outputText value="#{cobranza.importePago}" rendered="#{cobranza.importePago gt 0.0 }" />
                                  <h:outputText value="#{cobranza.importe}" rendered="#{cobranza.importePago eq 0.0 }" />
							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{Message.tit_fecha_pago_label}"></h:outputText>
								</f:facet>

								<h:outputText value="#{cobranza.fechaCobranza}"></h:outputText>

							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{Message.tit_forma_pago_label}"></h:outputText>
								</f:facet>

								<h:outputText value="#{consultaPoliza.poliza.formaPago}"></h:outputText>

							</h:column>
						</h:dataTable>
					</div>

				</div>

			</td>
		</tr>
		<tr>
			<td align="center"><h:form>
					<table>
						<tr>
							<td width="200px">
							
							        <t:saveState value="#{consultaPoliza.adherentes}"></t:saveState>
							<h:commandButton id="btnConfirmar" onclick="this.form.target = '_blank'"
									value="IMPRIMIR" styleClass="botonAceptar" 
									actionListener="#{consultaPoliza.exportarPDF}">
									<f:param name="nroCertificado" value="#{consultaPoliza.poliza.nroCertificado}" ></f:param>
									<f:param name="codSolicitud" value="#{consultaPoliza.poliza.codSolicitud}" ></f:param>
									<f:param name="nombre" value="#{consultaPoliza.poliza.titular.nombre}" ></f:param>
									<f:param name="apellido" value="#{consultaPoliza.poliza.titular.apellido}" ></f:param>
									<f:param name="dni" value="#{consultaPoliza.poliza.titular.nroDocumento}" ></f:param>
									<f:param name="fechNac" value="#{consultaPoliza.poliza.titular.fechaNacimientoString}" ></f:param>
									<f:param name="domicilio" value="#{consultaPoliza.poliza.titular.domicilioCompleto}" ></f:param>
									|<f:param name="vigencia" value="#{consultaPoliza.poliza.
									fechaAltaString}" ></f:param>
									
									<f:param name="plan" value="#{consultaPoliza.poliza.plan}" ></f:param>
									
									</h:commandButton></td>
							<td width="200px"><t:commandButton action="polizasUsuarios"
									value="VOLVER" styleClass="botonAceptar"></t:commandButton>
							</td>
							<td width="200px">
							
							       
							<h:commandButton id="btntraeta" onclick="this.form.target = '_blank'"
									value="TARJETA" styleClass="botonAceptar" 
									actionListener="#{consultaPoliza.exportarTarjeta}">
									<f:param name="nroCertificado" value="#{consultaPoliza.poliza.nroCertificado}" ></f:param>
									<f:param name="nombre" value="#{consultaPoliza.poliza.titular.nombre}" ></f:param>
									<f:param name="apellido" value="#{consultaPoliza.poliza.titular.apellido}" ></f:param>
									<f:param name="dni" value="#{consultaPoliza.poliza.titular.nroDocumento}" ></f:param>
									<f:param name="fechNac" value="#{consultaPoliza.poliza.titular.fechaNacimientoString}" ></f:param>
										<f:param name="vigencia" value="#{consultaPoliza.poliza.fechaAltaString}" ></f:param>
									
									<f:param name="plan" value="#{consultaPoliza.poliza.plan}" ></f:param>
									
									</h:commandButton></td>
						</tr>
					</table>



				</h:form></td>
		</tr>
	</table>

</body>
	</html>
</f:view>