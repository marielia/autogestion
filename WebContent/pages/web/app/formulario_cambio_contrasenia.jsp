		
		
		<h:form id="frmcambioPwd" rendered="#{cambioPasswordBean.mostrarPantalla}">
		<c:if test="${!cambioPasswordBean.puedeIngresar}">
			<c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if> 
				
		 
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />
				</t:column>
				<t:column>
					<t:outputText value="#{Message.tit_cambio_password}"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
	  	
	 		<t:panelGrid border="0" cellpadding="0" cellspacing="0"   columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
			
			<!--  CUADRO DE INGRESO DE DATOS DE CAMBIO DE PASS  -->
			<h:messages styleClass="errorNegro"/>		
			
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="1" columnClasses="cpo8Comun" width="100%" rowClasses="filaTablaCabecera">									
			
			 
				<t:column>				
					<t:saveState value="#{cambioPasswordBean.primerLogon}" /> 
					<t:outputText   styleClass="campoNormal" value ="Por favor ingrese la 'Contraseña actual', luego escriba una nueva en 'Contraseña nueva' y repítala por seguridad en 'Repita contraseña nueva'."/>		
				</t:column>
			 
			 </t:panelGrid>
			 	
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="2" columnClasses="cpo8-Var20, cpo8" width="100%" rowClasses="filaTablaCabecera">		
										
				 
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.contrasena_anterior_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputSecret id="pwdAnterior" value ="#{cambioPasswordBean.pwdAnterior}" styleClass="campo"/>
				</t:column>
	
			 	
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText value="&nbsp;&nbsp;#{Message.contrasena_nueva_label}" escape="false"/>
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputSecret  value="#{cambioPasswordBean.pwdNueva}" styleClass="campo"/>
				</t:column>
			
			 
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText value="&nbsp;&nbsp;#{Message.repite_contrasena_nueva_label}" escape="false"/>
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputSecret value="#{cambioPasswordBean.pwdRptNueva}" styleClass="campo"/>
				</t:column> 
				 
				 
			</t:panelGrid>	
			
				<h:panelGrid columns="2" width="30%" columnClasses="columnaTablaNumero,columnaTablaNumero "  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
			     	<h:column>				    		
			    		<h:commandButton id="cmdALogin" action="#{cambioPasswordBean.alogin}" 
						value="#{Message.volver_label}"  styleClass="boton"/>		
			    	</h:column>
			    	<h:column>	
			    		<h:commandButton id="cmdGuardarNuevaPwd" actionListener="#{cambioPasswordBean.guardar}" 
										value="#{Message.guardar_label}" styleClass="boton"/>
					 </h:column>
			 	</h:panelGrid> 	
		 
		</h:form>	

		<!--  BOTON INGRESO AL SISTEMA -->
		<h:form id="mostrarFinal" rendered="#{cambioPasswordBean.mostrarFinal}">
		<c:if test="${!cambioPasswordBean.puedeIngresar}">
			 <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>
		
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
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
			
			<h:panelGrid columns="1" width="100%" columnClasses="campo10" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
									
				<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>					
				<h:column>
						<h:outputText value="#{cambioPasswordBean.mensajeExi}" style="color: #000000; font-size: 11px;" styleClass="campo10"/>
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