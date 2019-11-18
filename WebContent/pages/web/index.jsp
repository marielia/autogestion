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
				var cuit = document.getElementById('frmIngreso:usuario');
				var email = document.getElementById('frmIngreso:clave');

				if (cuit.value == '' ||					
					email.value == '') {
						alert('Debe completar los campos obligatorios.');
					return false;
				} 				
				return true;
			}
			
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0" >
	<h:form id="frmIngreso" rendered="#{logonBean.mostrarPagina}" onsubmit="return validar();">
	<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" >
	  <tr>
	    <td width="8" align="left" valign="top" background="/refipass/img/sombra_left.gif">
	   	 <img src="/refipass/img/sombra_left.gif" width="8" height="4">
	    </td>
	    <td align="left" valign="top">
    
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr> 
	    <td colspan="2" align="left" valign="top" background="/refipass/img/fondo_logo.jpg"> <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="250" height="80">
	        <param name="movie" value="/refipass/img/logo.swf">
	        <param name="quality" value="high">
	        <embed src="/refipass/img/logo.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="250" height="80"></embed></object></td>
	  </tr>
	  <tr> 
	  <td align="left" valign="middle">
	  		
	  		<f:verbatim> 
	  		<font color="#19469C">        	
	          	<h:outputText styleClass="campo8" value="&nbsp;#{Message.ultima_modificacion_label}: " escape="false" /> 
	          	<font color="#1B89BB"><h:outputText styleClass="campo8SinNegrita"  value="#{Message.fecha_modifcacion_label}"/></font>
            </font>
            </f:verbatim> 
       </td>
        <td>  </td>              
	    <td align="right" valign="middle" > </td>      
	  </tr>
	  </table>
	  
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
                <td class="campo10">Si desea obtener su nombre de usuario y su contrase&ntilde;a 
                  Refipass, cont&aacute;ctenos haciendo 
                  <h:outputLink value="#{Message.contexto_sistema}/pages/web/app/registracion.jsf" rendered="#{sessionScope.tipo_usuario!=0}">
					<h:outputText id="txtClickAqui" value="#{Message.click_aqui_text}" styleClass="campoNoEditableSinFondo" rendered="#{sessionScope.tipo_usuario!=0}"/>
  				  </h:outputLink>	
                  o llame al siguiente n&uacute;mero de tel&eacute;fono:
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
		
					 columns="1" rowClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<t:column>
				<t:outputText value="&nbsp;&nbsp;#{Message.tit_informe_inicio_session}" escape="false"  />
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
		
			<h:panelGrid columns="2" width="60%" styleClass="bordeblanco" columnClasses="cpo8,cpo8" cellspacing="0" cellpadding="4">						 
				<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
				<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
								
				
				<h:column>
						<t:outputText value="&nbsp;.:&nbsp;#{Message.login_label}" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>
						<t:inputText id="usuario" value="#{logonBean.codigoCliente}" size="20" maxlength="20" styleClass="campo" />
				</h:column>			
				<h:column>
						<t:outputText value="&nbsp;.:&nbsp;#{Message.clave_label}" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>											
						<h:inputSecret id="clave" value="#{logonBean.clave}" size="20" maxlength="25" styleClass="campo">
						<f:validateLength minimum="1"/>
						</h:inputSecret>
					
				</h:column>				
				
				<h:column>	</h:column>					
				<h:column>
					<h:panelGrid columns="1" width="50%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				    <h:column>	</h:column>				
				     <h:column>
				    	 <%/*t:commandButton value="#{Message.cancelar_label}" action="#{logonBean.cancelar}"
				    	 styleClass="boton"/>
				    	 <t:outputText value="&nbsp;&nbsp;" escape="false"/
				    	 */%>
				    	<t:commandButton value="#{Message.ingresar_label}" action="#{logonBean.chkLogon}"
				    	 styleClass="boton"/>
				    </h:column>
				 	</h:panelGrid> 
			 	</h:column>	
			 	</h:panelGrid>
		
			 	</h:column>
			</h:panelGrid>	
          
          
          
          </td>

        </tr>
        <tr> 
          <td>&nbsp;</td>
        </tr>
      </table></td>
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


		
	</h:form>	
	</body>	
</f:view>
</html> 

