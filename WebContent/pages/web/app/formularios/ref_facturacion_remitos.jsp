<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<f:loadBundle basename="com.refinor.extranet.bundle.Messages" var="Message"/>
<f:view>

<html>
	<head>
        <meta http-equiv="Content-Type"
             content="text/html; charset=windows-1252"/>
		<title><h:outputText value="#{Message.nombre_sistema}"/> </title>
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/refinor.css">		
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsutiles.js"></script>				
		<script>		
		<%   
			if (request.getParameter("nroChofer")!=null) {
			    session.setAttribute("nroChofer",request.getParameter("nroChofer"));	
			}
		%>
					
		function validarCamposAlta(){		
		var nombre =document.getElementById("frmAlta:nombreC");
		var apellido =document.getElementById("frmAlta:apellidoC");	
		var dni =document.getElementById("frmAlta:dniC");	
		
			if(nombre.value=="" || apellido.value=="" || dni.value==""){	  	  
			  alert("Debe ingresar los datos obligatorios.");
			  return false;	
			}else{
			  return true;	
			}	
					
		}	
		
		
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0" >
		<%@ include file="../header.jsp" %>				
		
		
		
		<h:form id="frmAlta" rendered="#{facturacionRemitosBean.pantalla==1}"  >
		
	  	<c:if test="${!facturacionRemitosBean.puedeIngresar}">
			<c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>	  
		
		
				
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText value="#{Message.tit_facturacion_remitos}"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
	  	
	 		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
						
			<h:messages styleClass="errorNegro"/>
			
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="2" columnClasses="cpo8-Var13, cpo8 " width="100%" rowClasses="filaTablaCabecera">									
			  	
				<%/*  tarjeta  */%>
				<t:column>							
					<t:outputLabel  styleClass="dato">
						<t:outputText value="&nbsp;&nbsp;#{Message.nro_tarjeta_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>				 
					<h:inputSecret id="clave" value="#{facturacionRemitosBean.facturacionRemitoTO.codTarjeta}" size="30" maxlength="25" styleClass="campo">
						<f:validateLength minimum="1"/>
					</h:inputSecret>
				</t:column> 
				</t:panelGrid>
				
				
			    <%/*  Datos de chofer  */%>
			    <t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco"	columns="1" columnClasses="  cpo8 " width="100%" rowClasses="filaTablaCabecera">									
				<t:column  >
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.datos_chofer_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
		  	   </t:column>
				</t:panelGrid>
				
				
				<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="8" columnClasses="cpo8-Var13,cpo8,cpo8,cpo8,cpo8,cpo8,cpo8,cpo8 " width="100%" rowClasses="filaTablaCabecera">
			
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.patente_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				 <t:column>
			 			<t:inputText id="patente" value ="#{facturacionRemitosBean.facturacionRemitoTO.patente}" maxlength="20"  size="20" styleClass="campo" ></t:inputText>		
		 		</t:column>
				
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.dni_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				 <t:column>
			 			<t:inputText id="dniChofer" value ="#{facturacionRemitosBean.facturacionRemitoTO.dniChofer}" maxlength="20"  size="20" styleClass="campo" ></t:inputText>		
		 		</t:column>
		 		
		 		<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.pin2_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				 <t:column>
			 			<t:inputText id="pinChofer" value ="#{facturacionRemitosBean.facturacionRemitoTO.pinChofer}" maxlength="20"  size="20" styleClass="campo" ></t:inputText>		
		 		</t:column>
		 		
		 		<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.kilometaje_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				 <t:column>
			 			<t:inputText id="kilometraje" value ="#{facturacionRemitosBean.facturacionRemitoTO.kilometraje}" maxlength="20"  size="20" styleClass="campo" ></t:inputText>		
		 		</t:column>
		 		
				
				</t:panelGrid>
				
				
				
				<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="8" columnClasses="cpo8 " width="100%" rowClasses="filaTablaCabecera">						
				<%/*  cliente  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.cliente_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
			 		 
				</t:column>
	
		        <%/* fecha */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.fecha_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
		 		</t:column>
				 <t:column/>
				<t:column/>
				
			
				<%/*  turno  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.turno_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
				 	 				
				</t:column>
				
				
				<%/*  CCSS */%> 
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.ccss_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
			 	
				</t:column>
				
				
				<%/*  almacen  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.almacen_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
			 		</t:column>
				
				
				<%/*  grupo de unidad de negocio  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.forma_de_pago_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
				   	<h:selectOneListbox id="formaPago" size="1" value="#{facturacionRemitosBean.unidadNegocio}"  styleClass="campo"
					immediate="true" >	
						<f:selectItems value="#{facturacionRemitosBean.unidadesNegocio}"/>
					</h:selectOneListbox>
	     		</t:column> 
	     		
	     		<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.producto_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
				   	<h:selectOneListbox id="producto" size="1" value="#{facturacionRemitosBean.unidadNegocio}"  styleClass="campo"
					immediate="true" >	
						<f:selectItems value="#{facturacionRemitosBean.unidadesNegocio}"/>
					</h:selectOneListbox>
	     		</t:column> 
			</t:panelGrid>	
			
			
			
			<% /* seleccio de articulos   */ %>
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="6" columnClasses="  cpo8 " width="100%" rowClasses="filaTablaCabecera">									
				<t:column  >
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.seleccion_articulos_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
		  	   </t:column>
				</t:panelGrid>
			 
		  <t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="6" columnClasses="cpo8-Var20, cpo8,cpo8-Var20, cpo8,cpo8-Var20, cpo8" width="100%" rowClasses="filaTablaCabecera">									
		 
				
				<%/*  tarjeta  */%>
				<t:column>							
					<t:outputLabel  styleClass="dato">
						<t:outputText value="&nbsp;&nbsp;#{Message.nro_tarjeta_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
					<t:column>		
					 
				</t:column>
				<t:column/>
				<t:column/>
				<t:column/>
				<t:column/> 
			</t:panelGrid>	
			
			
			
			
				
			
			<t:saveState value="#{facturacionRemitosBean.unidadNegocio}"></t:saveState>
			<t:saveState value="#{facturacionRemitosBean.unidadesNegocio}"></t:saveState>	 
			<t:saveState value="#{facturacionRemitosBean.facturacionRemitoTO}"></t:saveState>
			 
			 	
		</t:panelGrid>
		
		
		<h:panelGrid columns="2" width="30%" columnClasses="columnaTablaNumero,columnaTablaNumero "  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
			     	<h:column>				    		
			    	 <t:commandButton value="#{Message.volver_label}" 
					action="#{facturacionRemitosBean.volver}" styleClass="boton"/>
			    	</h:column>
			    	<h:column>	
			    	 <h:commandButton  actionListener="#{facturacionRemitosBean.guardar}" 
						value="#{Message.guardar_label}" styleClass="boton" />	
					 </h:column>
			 	</h:panelGrid> 	
		
		</h:form>		
		
		
	

		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
		<%@ include file="../footer.jsp" %>
	</body>	
</html> 
</f:view>