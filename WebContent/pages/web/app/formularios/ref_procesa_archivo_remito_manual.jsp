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
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/cal2.js"></script>
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsfechas.js"></script>

		<script>
		
		function habilitarCampos(obj){	
					//alert(obj);	
					
					//alert(document.getElementById("frmProcesaArchivo:cboVen").value);
					
					if(obj=='nonuevo'){
						document.getElementById("frmProcesaArchivo:nuevo").style["display"] = "";
						document.getElementById("frmProcesaArchivo:nonuevo").style["display"] = "none";
						document.getElementById("frmProcesaArchivo:nuevoVendedor").value=true;
						
						document.getElementById("frmProcesaArchivo:apeVenLbl").style["display"] = "";
						document.getElementById("frmProcesaArchivo:apeVen").style["display"] = "";
						document.getElementById("frmProcesaArchivo:apeVen").value = "";
						
						document.getElementById("frmProcesaArchivo:nomVenLbl").style["display"] = "";
						document.getElementById("frmProcesaArchivo:nomVen").style["display"] = "";
						document.getElementById("frmProcesaArchivo:nombre").style["display"] = "";
						document.getElementById("frmProcesaArchivo:nombre").value = "";
						
						document.getElementById("frmProcesaArchivo:cboVenLbl").style["display"] = "none";
						document.getElementById("frmProcesaArchivo:cboVen").style["display"] = "none";
					}
					
					if(obj=='nuevo'){
						document.getElementById("frmProcesaArchivo:nuevo").style["display"] = "none";
						document.getElementById("frmProcesaArchivo:nonuevo").style["display"] = "";
						document.getElementById("frmProcesaArchivo:nuevoVendedor").value=false;
						
						document.getElementById("frmProcesaArchivo:apeVenLbl").style["display"] = "none";
						document.getElementById("frmProcesaArchivo:apeVen").style["display"] = "none";
						
						document.getElementById("frmProcesaArchivo:nomVenLbl").style["display"] = "none";
						document.getElementById("frmProcesaArchivo:nomVen").style["display"] = "none";
						document.getElementById("frmProcesaArchivo:nombre").style["display"] = "none";
						
						document.getElementById("frmProcesaArchivo:cboVenLbl").style["display"] = "";
						document.getElementById("frmProcesaArchivo:cboVen").style["display"] = "";
						
						
					}
					
					
			
			}
		function validar(){
			
				var bolNuevoVen = document.getElementById("frmProcesaArchivo:nuevoVendedor");
			     
			    // alert(bolNuevoVen.value);
			  if(bolNuevoVen!=null){   
			     if(bolNuevoVen.value=='true'){
			     		document.getElementById("frmProcesaArchivo:nuevo").style["display"] = "";
						document.getElementById("frmProcesaArchivo:nonuevo").style["display"] = "none";
						//alert('1');
						
						document.getElementById("frmProcesaArchivo:apeVenLbl").style["display"] = "";
						document.getElementById("frmProcesaArchivo:apeVen").style["display"] = "";
						
						
						document.getElementById("frmProcesaArchivo:nomVenLbl").style["display"] = "";
						document.getElementById("frmProcesaArchivo:nomVen").style["display"] = "";
						document.getElementById("frmProcesaArchivo:nombre").style["display"] = "";
						
						
						document.getElementById("frmProcesaArchivo:cboVenLbl").style["display"] = "none";
						document.getElementById("frmProcesaArchivo:cboVen").style["display"] = "none";
			     }
			     
			      if(bolNuevoVen.value=='false'){
			     		document.getElementById("frmProcesaArchivo:nuevo").style["display"] = "none";
						document.getElementById("frmProcesaArchivo:nonuevo").style["display"] = "";
						//alert('2');
						
						document.getElementById("frmProcesaArchivo:apeVenLbl").style["display"] = "none";
						document.getElementById("frmProcesaArchivo:apeVen").style["display"] = "none";
						document.getElementById("frmProcesaArchivo:apeVen").value = "";
						
						document.getElementById("frmProcesaArchivo:nomVenLbl").style["display"] = "none";
						document.getElementById("frmProcesaArchivo:nomVen").style["display"] = "none";
						document.getElementById("frmProcesaArchivo:nombre").value = "";
						document.getElementById("frmProcesaArchivo:nombre").style["display"] = "none";
						
						document.getElementById("frmProcesaArchivo:cboVenLbl").style["display"] = "";
						document.getElementById("frmProcesaArchivo:cboVen").style["display"] = "";
			     }
			  }   
			
			}	
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0" onload="validar()">
	<%@ include file="../header.jsp" %>
		
	<h:form id="frmProcesaArchivo" enctype="multipart/form-data" >	
	<%/*	
		<c:if var="puedeIngresarS" test="${!subirArchivoRemitoManualBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	*/ %>	  	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  rowClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="Refipass | Procesar Archivo con Remitos Manual"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>		
				
		<%/*FILTROS*/%>		
			<h:panelGrid columns="4" width="95%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;Seleccione el archivo con Remitos Manuales que desea procesar y seleccione el botón 'Enviar' " escape="false" styleClass="titulos"/>
					</t:outputLabel>
				</h:column>	
				<h:column></h:column>
				<h:column></h:column>
				<h:column></h:column>									
			</h:panelGrid>
			
			<h:panelGrid columns="2" width="95%" columnClasses="cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				
				<t:column  >
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;&nbsp;#{Message.path_archivo_a_procesar}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				 					
					<t:inputFileUpload size="70"   id="fichero" value ="#{subirArchivoRemitoManualBean.url}"  storage="file" styleClass="campoUpload" /> 				
				</t:column>	
			</h:panelGrid>
			
			<h:panelGrid columns="1" width="95%" columnClasses="cpo8Minuscula" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>	
					<h:outputText  value="&nbsp;&nbsp;" escape="false"/>												
					<h:outputLink  rendered="#{subirArchivoRemitoManualBean.rutaArchivoABaja!=''}" target="_blank" styleClass="cpo7b" value="#{subirArchivoRemitoManualBean.rutaArchivoABaja}">
						<h:outputText value="Para ver el resultado del proceso y bajar el archivo, haga click aquí. " />
					</h:outputLink>	
				</t:column>				
			</h:panelGrid>
						
			<h:panelGrid columns="1" width="95%" columnClasses="cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 							
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaCentrada" cellspacing="0">						 
				     	<h:column>					        	
				    		<t:commandButton action="#{subirArchivoRemitoManualBean.enviar}"
								value="#{labels.send}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			<t:panelGrid width="95%" columnClasses="columnaTablaTexto" columns="1" cellspacing="0" cellpadding="0">
			<t:column>
			    <t:div styleClass="cpo7n">
			     <t:graphicImage value="/img/info.ico" alt="#{Message.mensaje_mayor_contable_archivo}" width="10px" height="10px"/>
				 <h:outputText value="&nbsp;#{Message.mensaje_mayor_contable_archivo}" escape="false"/>
				</t:div>
			</t:column>		
			</t:panelGrid>
			
			<h:messages styleClass="errorNegro"/>
		  
			<h:panelGrid columns="1" width="95%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteReciboBean.volver}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		
		</h:column>
	</h:panelGrid>
	
	</h:form>
	
	
	
	<t:panelGrid>
		<t:column>
			<h:outputText value="&nbsp;" escape="false"/>
		</t:column>
		<t:column>
			<h:outputText value="&nbsp;" escape="false"/>
		</t:column>
		<t:column>
			<h:outputText value="&nbsp;" escape="false"/>
		</t:column>
	</t:panelGrid>	
		
	<%@ include file="../footer.jsp" %>
	
	</body>	
</f:view>
</html> 
						
						
						
						
						
						
						
						
						
						
		

<%/*

<html>
	<head>
        <meta http-equiv="Content-Type"
             content="text/html; charset=windows-1252"/>
		<title> </title>
		
		<link rel="stylesheet" type="text/css" href="/refipass/css/refinor.css">		
		<script type="text/javascript" src="/refipass/js/comun/jsutiles.js"></script>				
		
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0" >
		
	<f:view>			
	 <h:form id="frmp" enctype="multipart/form-data">		
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  rowClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText value="SUbir un archivo al servidor"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
	  	
	 		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
						
			
			
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="2" columnClasses="cpo8, cpo8" width="70%" rowClasses="filaTablaCabecera">									
				
				<t:column/>
				<t:column/>
				
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;URL&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>					
					

	<t:inputFileUpload  id="fichero" value ="#{subirArchivoRemitoManualBean.url}" styleClass="botton"  
	storage="file"  /> 

	<h:commandButton action="#{subirArchivoRemitoManualBean.enviar}"
				value="#{labels.send}" />
				
				
				<h:outputLink  rendered="#{subirArchivoRemitoManualBean.rutaArchivoABaja!=''}" target="_blank" styleClass="cpo7b" value="#{subirArchivoRemitoManualBean.rutaArchivoABaja}">
					<h:outputText value=" Descargar archivo de salida xcel" />
				</h:outputLink>	
		*/ %>		
				<%/*
				
  <t:outputText value="File Uploaded Successfully." 
		rendered="#{subirArchivoRemitoManualBean.rendSuccess}" 
		style="color:green;font-weight:bold"/>
    <t:outputText value="Error in File Uploading." 
		rendered="#{subirArchivoRemitoManualBean.rendFailure}" 
		style="color:red;font-weight:bold"/>
  
				
		*/ %>
					
			<%/*		
				</t:column>
			</t:panelGrid>
			
			</t:panelGrid>
			
			</h:form>
			</f:view>
			</body>
			
			</html> */%>
			
			
			
			