		
		
		<h:form id="frmcambioPwd" rendered="#{cambioPasswordBean.mostrarPantalla}">
		<c:if test="${!cambioPasswordBean.puedeIngresar}">
			<c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>
		
		
				
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  rowClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText value="#{Message.tit_cambio_password}"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
	  	
	 		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
			
			<!--  CUADRO DE INGRESO DE DATOS DE CAMBIO DE PASS  -->
			<h:messages styleClass="errorNegro"/>		
			
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="2" columnClasses="cpo8, cpo8" width="50%" rowClasses="filaTablaCabecera">									
				
				<t:column/>
				<t:column/>
				<!--  USUARIO  -->
				<t:column>				
					<t:saveState value="#{cambioPasswordBean.primerLogon}" />
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.login_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
					<t:outputText   styleClass="campoNoEditable" value ="#{cambioPasswordBean.codClienteAlfa}"/>		
				</t:column>
				<t:column>	</t:column>	
										
				<!--  CONTRASENIA ANTERIOR -->	
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.contrasena_anterior_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputSecret id="pwdAnterior" value ="#{cambioPasswordBean.pwdAnterior}" styleClass="campo"/>
				</t:column>
	
				<!--  CONTRASENIA NUEVA -->	
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText value="&nbsp;&nbsp;#{Message.contrasena_nueva_label}" escape="false"/>
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputSecret  value="#{cambioPasswordBean.pwdNueva}" styleClass="campo"/>
				</t:column>
			
				<!--  CONFIRMACION CONTRASENIA NUEVA -->	
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText value="&nbsp;&nbsp;#{Message.repite_contrasena_nueva_label}" escape="false"/>
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputSecret value="#{cambioPasswordBean.pwdRptNueva}" styleClass="campo"/>
				</t:column>
				
				<t:column/>
				<t:column/>
				
				<t:column/>
				<t:column>
				<t:panelGrid width="100%" cellspacing="0" columns="1" cellpadding="0"  columnClasses="columnaTablaCentrada">
					<t:column>
						<h:commandButton id="cmdGuardarNuevaPwd" actionListener="#{cambioPasswordBean.guardar}" 
										value="#{Message.guardar_label}" styleClass="boton"/>
					</t:column>
				</t:panelGrid>
				</t:column>	
			</t:panelGrid>		
		</t:panelGrid>	
		</h:form>	

		<!--  BOTON INGRESO AL SISTEMA -->
		<h:form id="mostrarFinal" rendered="#{cambioPasswordBean.mostrarFinal}">
		<c:if test="${!cambioPasswordBean.puedeIngresar}">
			 <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>
		
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  rowClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText value="#{Message.tit_cambio_password}"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
	  	
	 		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
			
			<!--  CUADRO DE INGRESO DE DATOS DE CAMBIO DE PASS  -->
			<h:messages styleClass="errorNegro"/>		
			
			<h:panelGrid columns="1" width="80%" columnClasses="campo10" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
									
				<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>					
				<h:column>
						<h:outputText value="#{cambioPasswordBean.mensajeExi}" style="color: #3A25A2; font-size: 11px;" styleClass="campo10"/>
				</h:column>								
				<h:column></h:column>				
				<h:column>
					<t:inputHidden value="#{cambioPasswordBean.primerLogon}" />	
					<h:commandButton id="cmdALogin" action="#{cambioPasswordBean.alogin}" 
						value="#{cambioPasswordBean.botonLabel}"  styleClass="boton"/>	
				</h:column>
				<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
			 	</h:panelGrid>	
			</t:panelGrid>
		
		
		</h:form>