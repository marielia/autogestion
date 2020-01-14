<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<f:loadBundle basename="com.refinor.extranet.bundle.Messages" var="Message"/>
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
				var cuit = document.getElementById('frmRegistracion:cuit');
				var email = document.getElementById('frmRegistracion:clave');

				if (cuit.value == '' ||					
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
		    <td width="8" align="left" valign="top" background="/refipass/img/sombra_left.gif">
		   	 <img src="/refipass/img/sombra_left.gif" width="8" height="4">
		    </td>
		    <td align="left" valign="top">
		    
			    <table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr> 
				    <td align="left" valign="top" background="/refipass/img/fondo_logo.jpg"> 
<!-- 				    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="250" height="80"> -->
<!-- 				        <param name="movie" value="/refipass/img/logo.swf"> -->
<!-- 				        <param name="quality" value="high"> -->
<!-- 				        <embed src="/refipass/img/logo.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="250" height="80"></embed> -->
<!-- 				    </object> -->
						<img src="/refipass/img/logo.png" class="logo">
				    </td>
				  </tr>
				  <tr> 
				    <td align="right" valign="middle" > 
				    	
			            <h:outputLink value="#{Message.contexto_sistema}/pages/web/app/salir.jsf">            	
				            <h:outputText styleClass="campo8" value="#{Message.salir_header_label}" escape="false"/>	            
				   		 </h:outputLink>	   		 
				   		 <h:outputText styleClass="campo8" value="&nbsp;&nbsp;" escape="false"/>
			        </td>      
				  </tr>
				  <tr> 
				    <td align="left" valign="top" background="/refipass/img/fondo_top.jpg"><img src="/refipass/img/fondo_top.jpg" width="3" height="19"></td>
				  </tr>
				</table>		
		      
		      </td>					
		    <td width="8" align="right" valign="top" background="/refipass/img/sombra_right.gif">
		    <img src="/refipass/img/sombra_right.gif" width="8" height="4">
		    </td>
		  </tr>
		  <tr>
		    <td width="8" align="left" valign="top" background="/refipass/img/sombra_left.gif">
		    <img src="/refipass/img/sombra_left.gif" width="8" height="4">
		    </td>
		  	<td align="center" valign="top" height="300">
		  	
		
		
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr> 
				    <td width="243" align="left" valign="top"> 
				    <table width="100%" border="0" cellspacing="0" cellpadding="0">
				        <tr> 
				          <td align="left" valign="top"><img src="/refipass/img/logo_refipass.gif" width="243" height="72"></td>
				
				        </tr>
				        <tr> 
				          <td align="left" valign="top"><img src="/refipass/img/foto_camion.jpg" width="243" height="264"></td>
				        </tr>
				        <tr> 
				          <td align="left" valign="top" bgcolor="#d6d6d6">
				          <table width="100%" border="0" cellspacing="10" cellpadding="0">
				              <tr> 
				               	  <td class="campo10">Si desea realizar alguna consulta 
				               	  sobre la Registraci&oacute;n de Usuarios 
				                  llame al siguiente n&uacute;mero de tel&eacute;fono:
				                  </td>				
				              </tr>
				              <tr> 
				                <td class="campo10"><img src="/refipass/img/telefono.gif" width="168" height="18"></td>
				              </tr>
				              <tr> 
				                <td class="campo10"></td>
				              </tr>
				            </table>
				            </td>
				        </tr>
				      </table>
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
						<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
									 columns="1" width="100%">
							<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>			
						</t:panelGrid>
						
						<%/*FILTROS*/%>
							<h:messages styleClass="errorNegro"/>
						
						   <h:form id="frmRegistracion" rendered="#{!registracionBean.mostrarResultado}" onsubmit="return validar();">	 
				
							<h:panelGrid columns="2" width="80%" columnClasses="cpo8,cpo8" styleClass="bordeblanco" cellspacing="0" cellpadding="4">						 
								<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
								<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
								
												
								
								<h:column>
										<t:outputText value="&nbsp;.:&nbsp;#{Message.cuit_label}" styleClass="cpo8" escape="false"/>
								</h:column>
								<h:column>
										<t:inputText id="cuit" value="#{registracionBean.numeroDocumento}" size="20" maxlength="20" styleClass="campo" />
										<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>	
								</h:column>
								
								
								<h:column>
										<t:outputText value="&nbsp;.:&nbsp;#{Message.cuenta_correo_label}" styleClass="cpo8" escape="false"/>
								</h:column>
								<h:column>					
										<t:inputText id="clave" value="#{registracionBean.email}" size="35" maxlength="50" styleClass="campo" />
										<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>	
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
								    	<t:commandButton value="#{Message.aceptar_label}" actionListener="#{registracionBean.aceptar}"
								    	 styleClass="boton"/>
								    </h:column>
								 	</h:panelGrid> 
							 	</h:column>	
							 	<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
							 	</h:panelGrid>
							 	</h:form>	
							 	
							 	  <h:form id="frmFiltroCuenta2" rendered="#{registracionBean.mostrarResultado}">	
							 		<h:panelGrid columns="1" width="80%" columnClasses="campo10" styleClass="columnaTablaTexto" cellspacing="0" cellpadding="4">						 
								
								<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>						
								
								<h:column>
										<h:outputText value="#{Message.texto_registracion_resultado_linea1}" style="color: #3A25A2; font-size: 11px;" styleClass="campo10"/>
								</h:column>								
								<h:column></h:column>
								
								<h:column>	
									<h:outputText value="#{Message.texto_registracion_resultado_linea3}" style="color: #3A25A2; font-size: 11px;" styleClass="campo10"/>
								</h:column>
								<h:column>	</h:column>
								
								<h:column>
										<h:outputText value="#{Message.texto_registracion_resultado_linea4}" style="color: #3A25A2; font-size: 11px;" styleClass="campo10"/>
								</h:column>										
								<h:column>	</h:column>
								
								<h:column>
										<h:outputText value="#{Message.texto_registracion_resultado_linea2}" style="color: #3A25A2; font-size: 11px;" styleClass="campo10"/>
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
		
		
		  <td width="8" align="right" valign="top" background="/refipass/img/sombra_right.gif">&nbsp;</td>
		  </tr>
		  <tr> 
		    <td height="4" align="left" valign="top"><img src="/refipass/img/esquina_left.gif" width="8" height="4"></td>
		    <td height="4" align="left" valign="top" background="/refipass/img/sombra_down.gif"><img src="/refipass/img/sombra_down.gif" width="2" height="4"></td>
		    <td width="8" height="4" align="right" valign="top"><img src="/refipass/img/esquina_right.gif" width="8" height="4"></td>
		  </tr>
		  <tr>
		    <td  colspan="3" height="4" align="left" valign="top" >
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr> 
				    <td align="right" valign="bottom" background="/refipass/img/FND_PIE.gif">&nbsp;</td>
				    <td width="181" align="right" valign="bottom" background="/refipass/img/FND_PIE.gif">
				    <img src="/refipass/img/logorefi_pie.gif" width="181" height="65">
				    </td>
				  </tr>
				</table>
			</td>
		    
		  </tr>
		</table>	
	
	</body>	
</f:view>
</html> 

