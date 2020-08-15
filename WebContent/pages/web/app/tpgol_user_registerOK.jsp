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
        <meta http-equiv="Content-Type"
             content="text/html; charset=windows-1252"/>
		<title><h:outputText value="#{Message.nombre_sistema}"/> </title>
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/refinor.css">		
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsutiles.js"></script>
		<script>
		function validar() { 				
				var name = document.getElementById('frmRegistracion:name');
				var surname = document.getElementById('frmRegistracion:surname');
				var phoneNumber = document.getElementById('frmRegistracion:phoneNumber');
				var email = document.getElementById('frmRegistracion:email');

				if (name.value == '' ||					
					surname.value == '' ||					
					phoneNumber.value == '' ||					
					email.value == '') {
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
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0">
	
	

		<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" >
		 
		  <tr>
		    
		  	<td align="center" valign="top" height="300">
		  	
		
		
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr> 
				    <td width="243" align="left" valign="top"> 
				   
				      </td>
				    <td align="left" valign="top">
				    
				    <table width="100%" border="0" cellspacing="0" cellpadding="0">
				        <tr> 
				          <td bgcolor="#FFFFFF">
				          
				        <h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
						<h:column>  		
						
						<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
						
									 columns="1" columnClasses="titulosGrande" width="100%">
							 <t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
							<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
							<t:column>
								<t:outputText value="&nbsp;&nbsp;#{Message.tit_informe_ingreso_usuarios}" escape="false"  />
							</t:column>
							<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
							<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
						</t:panelGrid>
						 
						
						<%/*FILTROS*/%>
							<h:messages styleClass="errorNegro"/>
						
						   <h:form id="frmRegistracion" rendered="#{!userRegisterBean.mostrarResultado}" onsubmit="return validar();">	 
				
							<h:panelGrid columns="2" width="80%" columnClasses="cpo8,cpo8" styleClass="bordeblanco" cellspacing="0" cellpadding="4">						 
								<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
								<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
								
												
								
								<h:column>
										<t:outputText value="&nbsp;.:&nbsp;#{Message.nombre_label}" styleClass="cpo8" escape="false"/>
								</h:column>
								<h:column>
										<t:inputText id="name" value="#{userRegisterBean.nombres}" size="20" maxlength="20" styleClass="campo" />
										<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>	
								</h:column>
								
								
								<h:column>
										<t:outputText value="&nbsp;.:&nbsp;#{Message.apellido_label}" styleClass="cpo8" escape="false"/>
								</h:column>
								<h:column>					
										<t:inputText id="surname" value="#{userRegisterBean.surname}" size="35" maxlength="50" styleClass="campo" />
										<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>	
								</h:column>	
								
								<h:column>
										<t:outputText value="&nbsp;.:&nbsp;#{Message.email_label}" styleClass="cpo8" escape="false"/>
								</h:column>
								<h:column>					
										<t:inputText id="email" value="#{userRegisterBean.email}" size="35" maxlength="50" styleClass="campo" />
										<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>	
								</h:column>	
								
									<h:column>
										<t:outputText value="&nbsp;.:&nbsp;#{Message.telefono_label}" styleClass="cpo8" escape="false"/>
								</h:column>
								<h:column>					
										<t:inputText id="phoneNumber" value="#{userRegisterBean.phoneNumber}" size="35" maxlength="50" styleClass="campo" />
										<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>	
								</h:column>	
								
								<h:column>
									<t:outputText value="&nbsp;.:&nbsp;QUIERO REGISTRARME CON" styleClass="cpo8" escape="false"/>
								</h:column>
								<h:column>
									 <h:selectOneRadio  value="#{userRegisterBean.estado}" styleClass="campoNoEditable">
									     <f:selectItems  value="#{userRegisterBean.estados}"/>
									</h:selectOneRadio>	
								</h:column>
									 	
					   
					   			
								
												
								<h:column>	
								<t:panelGrid border="0" cellpadding="" cellspacing="0" styleClass="columnaTablaTexto" 
										columns="1" columnClasses="cpo11-20" width="100%" >							
										<t:column>
											<t:div styleClass="cpo11">
												<t:outputText id="datosOblig" value="&nbsp;&nbsp;#{Message.datos_obligatorios_texto}"  styleClass="datoObligatorio" escape="false"/>
											</t:div>
										</t:column>										
								</t:panelGrid>
								</h:column>		
									
								<h:column>
									<h:panelGrid columns="1" width="50%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
								     <h:column>
								    	<t:commandButton value="#{Message.aceptar_label}" actionListener="#{userRegisterBean.aceptar}"
								    	 styleClass="boton"/>
								    </h:column>
								 	</h:panelGrid> 
							 	</h:column>	
							 	<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
							 	</h:panelGrid>
							 	</h:form>	
							 	
							 	  <h:form id="frmFiltroCuenta2" rendered="#{userRegisterBean.mostrarResultado}">	
							 		<h:panelGrid columns="1" width="80%" columnClasses="campo10" styleClass="columnaTablaTexto" cellspacing="0" cellpadding="4">						 
								
								<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>						
								
								<h:column>
										<h:outputText value="#{Message.texto_registracion_resultado_linea1}" style="color: #000000; font-size: 11px;" styleClass="campo10"/>
								</h:column>								
								<h:column></h:column>
								
								<h:column>	
									<h:outputText value="#{Message.texto_registracion_resultado_linea3}" style="color: #000000; font-size: 11px;" styleClass="campo10"/>
								</h:column>
								<h:column>	</h:column>
								
								<h:column>
										<h:outputText value="#{Message.texto_registracion_resultado_linea4}" style="color: #000000; font-size: 11px;" styleClass="campo10"/>
								</h:column>										
								<h:column>	</h:column>
								
								<h:column>
										<h:outputText value="#{Message.texto_registracion_resultado_linea2}" style="color: #000000; font-size: 11px;" styleClass="campo10"/>
										<h:outputLink value="#{Message.contexto_sistema}/pages/web/index.jsf"><h:outputText id="txtClickAqui" value="#{Message.click_aqui_text}" styleClass="linkhome" /></h:outputLink>
								</h:column>											
								
								<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
								<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
									
								
							 	</h:panelGrid>
							 	
							 	</h:form>			
							 	</h:column>
							</h:panelGrid>
							
				          
				          
				          
				          </td>
				
				        </tr>
				        <tr> 
				          <td>&nbsp;</td>
				        </tr>
				      </table>
				      
				      
				      
				 
				    
				      
				      
				      </td>
				  </tr>
				</table>
		
		
		  
		  </tr>
		  
		</table>	
	
	</body>	
</f:view>
</html> 

