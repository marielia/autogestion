<ui:composition template="/WEB-INF/template.xhtml"
    xmlns="http://www.w3.org/1999/xhtml"
    xmlns:f="http://xmlns.jcp.org/jsf/core"
    xmlns:h="http://xmlns.jcp.org/jsf/html"
    xmlns:ui="http://xmlns.jcp.org/jsf/facelets">
    <ui:define name="content">
<h:form >


    <table  border="0" class="top">
      <tr>
        <td align="left" valign="middle"><div class="logoTop"><img src="<h:outputText value="#{Message.contexto_sistema}"/>/img/logo_login.png" width="50" height="46" /></div>
    <div class="tituloPortalTop">PORTAL DE ASEGURADOS</div></td>
        <td align="right" valign="middle">
        
        <div class="topData"><h:commandLink action="#{logonBean.salir}"><img src="<h:outputText value="#{Message.contexto_sistema}"/>/img/icon_logout.png" width="20" height="20" class="icon" />SALIR</h:commandLink></div>
        <div class="topData"><h:commandLink action="cambio_password"><img src="<h:outputText value="#{Message.contexto_sistema}"/>/img/icon_password.png" width="20" height="20" class="icon" />CAMBIAR CONTRASEÑA</h:commandLink></div>
        <div class="topData"><img src="<h:outputText value="#{Message.contexto_sistema}"/>/img/icon_user.png" width="20" height="20" class="icon" /><h:outputText value="#{logonBean.usuario.nombres} "/></div>
        </td>
      </tr>
    </table>
    
     </h:form>
     </ui:define>
     </ui:composition>