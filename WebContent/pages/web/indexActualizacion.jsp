<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.afa.extranet.bundle.Messages" var="Message"/>
<f:view>
<html>
<head>

<title><h:outputText value="#{Message.nombre_sistema}" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="textos.css" rel="stylesheet" type="text/css">
<link href="<h:outputText value="#{Message.contexto_sistema}"/>/css/afa.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="/afanet/img/favicon.ico"> 
<script>

</script>
</head>

<body leftmargin="10" topmargin="10" marginwidth="10" marginheight="10" class="pagina">
<table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#c5cdda" bgcolor="#FFFFFF">
  <tr> 
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="top">
        <tr> 
          <td width="3"><img src="/afanet/img/inicio_degrade_top.jpg" width="3" height="61"></td>
          <td width="260" align="left" valign="top" background="/afanet/img/degrade_top.jpg"><img src="/afanet/img/logo_top.jpg"></td>
          <td align="left" valign="middle" background="/afanet/img/degrade_top.jpg"><table width="100%" border="0" cellspacing="10" cellpadding="0">
              <tr align="right" valign="top" class="cpo8"> 
                <td width="541"></td>
                <td width="20" align="center"> | </td>
                <td nowrap>
                   <h:outputText value="#{Message.no_registrado_label}"/>   
                   
                </td>
              </tr>
            </table></td>
          <td width="3" align="right" valign="top"><img src="/afanet/img/final_degrade_top.jpg" width="3" height="61"></td>
        </tr>
      </table></td>
  </tr>
  <tr border="1"> 
    <td bgcolor="#c5cdda"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="64%">&nbsp;</td>
          <td width="36%" align="right" class="cpo8">
            <%/*
            h:outputLink value="#{Message.contexto_sistema}/pages/web/app/salir.jsf">
            <h:outputText styleClass="cpo8-bold" value="#{Message.salir_header_label}" escape="false"/>
            <img src="/afanet/img/icono_salir.gif" width="11" height="11" align="absmiddle">
            </h:outputLink
            */%>
          </td>
        </tr>
      </table></td>
  </tr>
  <tr> 
    <td height="312" align="center" valign="middle" border="1"> 
      <table border="0" align="center" cellpadding="0" cellspacing="10">
      <h:form id="frmRegistracion">
        <tr align="left" valign="top"> 
          <td width="319"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td align="left" valign="top">
                <table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" class="titulos">
                    <tr> 
                      <td width="23"><img src="/afanet/img/icono_titulos.gif" width="23" height="25"></td>
                      <td>                     
	                    <t:outputText value="#{Message.sr_usuario_label}"/>	                    
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
              
              <tr> 
                <td align="left" valign="top">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="fila2">
                    <tr align="left"> 
                      <td colspan="2" valign="middle" class="label9">
                      	<font color="#003366">
	                      	<strong>
	                      	<br>
	                      	<br>	                      		                      	
	                      	<h:outputText id="txtRegist1" value="#{Message.actualizando_label}"/>	
	                      	<br>	
	                      	<br>	
	                      	</strong>								
							<h:outputText id="txtRegist2" value="#{Message.intente_mas_tarde_label}"/>
							<br>
							<br>
							<br>							
							<br>
							<br>
							<br>
							<br>
							<br>
							
						</font> 
					  </td>
                    </tr>   
                </table>
                </td>
              </tr>
            </table>
	    </td>
	    <td>
	          <td width="296">
				  <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="290" height="263">
	              <param name="movie" value="/afanet/img/home.swf">
	              <param name="quality" value="high">
	              <embed src="/afanet/img/home.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="290" height="263"></embed></object>
	          </td>
	    </td>
	  </tr>
	</h:form>
	</table>
	</td>
	</tr>
	</table>
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="5">
	  <tr>
	    <td align="center" valign="middle" class="cpo7"><font color="#c5cdda">&copy; 
	      2006 Federal Seguros | Requiere plug in de</font> <a href="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" target="_blank"><font color="#c5cdda">Flash</font></a> 
	      <font color="#c5cdda">| Desarrollado y dise&ntilde;ado por</font><font color="#6699CC">&nbsp; 
	      </font><a href="http://www.keepsa.com" target="_blank"><font color="#c5cdda">KEEP IN MIND S.A. ®</font></a></td>
	      <font color="#6699CC">&nbsp;|&nbsp; 
	      </font>
	     	      
	      <a href="/afanet/pages/web/app/terminos_legales.html" target="_blank">
	          <font color="#c5cdda">T&eacute;rminos Legales</font>
	      </a>
	  </tr>
	</table>

<%/*  AFA Control de seguimiento de pagina 08082007 RSEN 
*/%>	
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
</script>
<script type="text/javascript">
_uacct = "UA-2276775-1";
urchinTracker();
</script>

</body>
</html>
</f:view>