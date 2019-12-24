

<f:verbatim>
<table background="/refipass/img/fondo.png" width="100%"  align="center" >
<tr>
    <td>
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
  <tr>
    <td width="8" align="left" valign="top" background="/refipass/img/sombra_left.gif" >
   	 <img src="/refipass/img/sombra_left.gif" width="8" height="4">
    </td>
    <td align="left" >    
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr> 
	    <td colspan="2" align="left" valign="top" > 
<!-- 	    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="250" height="80"> -->
<!-- 	        <param name="movie" value="/refipass/img/logo.png"> -->
<!-- 	        <param name="quality" value="high"> -->
<!-- 	        <embed src="/refipass/img/logo.png" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="250" height="80"></embed> -->
<!-- 	    </object> -->
			<img src="/refipass/img/logo.png">
	    </td>
	  </tr>
	  <tr> 
	  <td align="left" valign="middle">
	  		<font color="#19469C">
	          </f:verbatim>
	          	<h:panelGroup>
	          	<h:outputText styleClass="campo8" value="&nbsp;#{Message.fecha_label}: " escape="false" /> 
	          	<font color="#1B89BB"><h:outputText styleClass="campo8SinNegrita" id="fecha" value="#{sessionScope.fechaActual}"/></font>
	          	</h:panelGroup>
	           <f:verbatim>
            </font>
            <font color="#19469C">        	
	          	<h:outputText styleClass="campo8" value="&nbsp;-&nbsp;#{Message.ultima_modificacion_label}: " escape="false" /> 
	          	<font color="#1B89BB"><h:outputText styleClass="campo8SinNegrita"  value="#{Message.fecha_modifcacion_label}"/></font>
            </font>
        <td>                
	    <td align="right" valign="middle" > 
	    	<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/cambio_password_dos.jsf">            	
	            <h:outputText styleClass="campo8" value="#{Message.cambiar_contrasena_header_label}" escape="false"/>	            
	   		 </h:outputLink>	   		 
	   		 <h:outputText styleClass="campo8" value="&nbsp;&nbsp;" escape="false"/>
	   		 
            <h:outputLink value="#{Message.contexto_sistema}/pages/web/app/salir.jsf">            	
	            <h:outputText styleClass="campo8" value="#{Message.salir_header_label}" escape="false"/>	            
	   		 </h:outputLink>	   		 
	   		 <h:outputText styleClass="campo8" value="&nbsp;&nbsp;" escape="false"/>
        </td>      
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
  	</f:verbatim>