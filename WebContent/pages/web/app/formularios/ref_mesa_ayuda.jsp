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
		<link	 rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/refinor.css">		
		
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/js/codebase/dhtmlxcombo.css">		
		
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsutiles.js"></script>				
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/cal2.js"></script>
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsfechas.js"></script>
		
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/codebase/dhtmlxcommon.js"></script>
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/codebase/dhtmlxcombo.js"></script>
		<script>		
		<%   
			    session.setAttribute("codigoAutorizacion",request.getParameter("codigoAutorizacion"));		
		%>	
		</script>
		
	<script>
		window.dhx_globalImgPath="../../../../js/codebase/imgs/";
	
		
		var ventanaCalendario = null;   
		
		function doCalendario4(nom,control) 
		{
		  	addCalendar(nom, "Seleccione Fecha", control, "frmMesaAyuda");
		  	ventanaCalendario = showCal(nom);
		}
		
		function cerrarVentana() 
		{
		   if(ventanaCalendario != null) 
		   {
			  ventanaCalendario.close();
		   }
		}
		
		function validarFecha(obj)
		{
		    cadena = obj.value; 
		      
		    if(cadena == ""){	  
		        return false;
		    }
		    var Fecha= new String(cadena)
		    var Ano= new String(Fecha.substring(Fecha.lastIndexOf("/")+1,Fecha.length))
		    var Mes= new String(Fecha.substring(Fecha.indexOf("/")+1,Fecha.lastIndexOf("/")))
		    var Dia= new String(Fecha.substring(0,Fecha.indexOf("/")))
		    
		    if (isNaN(Ano) || Ano.length<4 || Ano.length>4 || parseFloat(Ano)<1900){
		        //alerta(60006,msg60006,comunURL);
		        alert('Formato inv\xE1lido, formato v\xE1lido: dd/mm/aaaa - a\xF1o mayor a 1900');                
		        obj.focus();
		        return false;
		    }
		    if (isNaN(Mes) || parseFloat(Mes) < 1 || parseFloat(Mes) > 12 || Mes == ""){
		        alert('Fecha inv\xE1lida');
		        //alerta(60007,msg60007,comunURL);       
		        obj.focus();
		        return false;
		    }
		    if (isNaN(Dia) || parseFloat(Dia) < 1 || parseFloat(Dia) > 31 || Dia == ""){
		        alert('Fecha inv\xE1lida');
		        // alerta(60007,msg60007,comunURL);        
		        obj.focus();
		        return false;
		    }
		    
		    
		    
		     if (Mes == 4 || Mes == 6 || Mes == 9 || Mes == 11) {
		        if (Dia > 30) {
		            alert('Fecha inv\xE1lida');
		            // alerta(60007,msg60007,comunURL);            
		            obj.focus();
		            return false;
		        }
		    }
		  
		   	if (Mes == 2) {
		     	//es bisiesto     	
		     	if (esBisiesto(Ano)){
		     	  if (Dia > 29) {
		            alert('Fecha inv\xE1lida');
		            // alerta(60007,msg60007,comunURL);
		            
		            obj.focus();
		            return false;
		          }     	
		     	} else{     	
			     	 if (Dia > 28) {
			            alert('Fecha inv\xE1lida');
			            // alerta(60007,msg60007,comunURL);	           
			            obj.focus();
			            return false;
			       	 }
			    }   	 
		     }
		     
		     
		    if (Dia.length == 1)
		    		Dia='0'+Dia;
		    		
		   	
		    if (Mes.length == 1)Mes='0'+Mes;
		    obj.value = Dia+'/'+Mes+'/'+Ano;
		    
		    if(fechaMenor(getCurrentDate(),obj.value)){
				alert('La Fecha de Pedido no puede ser mayor a la Fecha Actual.');     			    		       
		        obj.focus();
		        return false;
			}	
	
		    return true;
		    }
		    
		    function esBisiesto(ano){
				return ((ano%4==0 && ano%100!=0)||(ano%400==0)?true:false)
			}
			
			function habilitarCamposAnular(obj){	
						
				if(obj.value==1 || obj.value==2){				
					document.getElementById("frmMesaAyuda:nroRemito1A").value='';
					document.getElementById("frmMesaAyuda:nroRemito2A").value='';					
					document.getElementById("frmMesaAyuda:nroRemito1A").readOnly=true;
					document.getElementById("frmMesaAyuda:nroRemito2A").readOnly=true;
					document.getElementById("frmMesaAyuda:tipoComprobanteAnular").value='';
					document.getElementById("frmMesaAyuda:marcaObli").value='';
				}
				
				if(obj.value==6){
					document.getElementById("frmMesaAyuda:tipoComprobanteAnular").value='FACTURA';					
					document.getElementById("frmMesaAyuda:marcaObli").value='[x]';
					
				}
				
				if(obj.value==7){
					document.getElementById("frmMesaAyuda:tipoComprobanteAnular").value='REMITO';
					document.getElementById("frmMesaAyuda:marcaObli").value='[x]';
				}
				
				
				if(obj.value==6 || obj.value==7){					
					document.getElementById("frmMesaAyuda:nroRemito1A").readOnly=false;
					document.getElementById("frmMesaAyuda:nroRemito2A").readOnly=false;
				}			
				
			}
			
			function habilitarCamposAnularDespues(){	
			var a =document.getElementById("frmMesaAyuda:abc");
						//alert(a.value);
				if(a.value==1 
				|| a.value==2){				
					document.getElementById("frmMesaAyuda:nroRemito1A").value='';
					document.getElementById("frmMesaAyuda:nroRemito2A").value='';					
					document.getElementById("frmMesaAyuda:nroRemito1A").readOnly=true;
					document.getElementById("frmMesaAyuda:nroRemito2A").readOnly=true;
					document.getElementById("frmMesaAyuda:tipoComprobanteAnular").value='';
					document.getElementById("frmMesaAyuda:marcaObli").value='';
				}
				
				if(a.value==6){
					document.getElementById("frmMesaAyuda:tipoComprobanteAnular").value='FACTURA';					
					document.getElementById("frmMesaAyuda:marcaObli").value='[x]';
					
				}
				
				if(a.value==7){
					document.getElementById("frmMesaAyuda:tipoComprobanteAnular").value='REMITO';
					document.getElementById("frmMesaAyuda:marcaObli").value='[x]';
				}
				
				
				if(a.value==6 || 
				a.value==7){					
					document.getElementById("frmMesaAyuda:nroRemito1A").readOnly=false;
					document.getElementById("frmMesaAyuda:nroRemito2A").readOnly=false;
				}			
				
			}
			
			function habilitarCampos(obj){	
					//alert(obj);	
					
					//alert(document.getElementById("frmMesaAyuda:cboVen").value);
					
					if(obj=='nonuevo'){
						document.getElementById("frmMesaAyuda:nuevo").style["display"] = "";
						document.getElementById("frmMesaAyuda:nonuevo").style["display"] = "none";
						document.getElementById("frmMesaAyuda:nuevoVendedor").value=true;
						
						document.getElementById("frmMesaAyuda:apeVenLbl").style["display"] = "";
						document.getElementById("frmMesaAyuda:apeVen").style["display"] = "";
						document.getElementById("frmMesaAyuda:apeVen").value = "";
						
						document.getElementById("frmMesaAyuda:nomVenLbl").style["display"] = "";
						document.getElementById("frmMesaAyuda:nomVen").style["display"] = "";
						document.getElementById("frmMesaAyuda:nombre").style["display"] = "";
						document.getElementById("frmMesaAyuda:nombre").value = "";
						
						document.getElementById("frmMesaAyuda:divCboVen").style["display"] = "none";
						document.getElementById("frmMesaAyuda:cboVenLbl").style["display"] = "none";
					}
					
					if(obj=='nuevo'){
						document.getElementById("frmMesaAyuda:nuevo").style["display"] = "none";
						document.getElementById("frmMesaAyuda:nonuevo").style["display"] = "";
						document.getElementById("frmMesaAyuda:nuevoVendedor").value=false;
						
						document.getElementById("frmMesaAyuda:apeVenLbl").style["display"] = "none";
						document.getElementById("frmMesaAyuda:apeVen").style["display"] = "none";
						
						document.getElementById("frmMesaAyuda:nomVenLbl").style["display"] = "none";
						document.getElementById("frmMesaAyuda:nomVen").style["display"] = "none";
						document.getElementById("frmMesaAyuda:nombre").style["display"] = "none";
						
						document.getElementById("frmMesaAyuda:divCboVen").style["display"] = "";
						document.getElementById("frmMesaAyuda:cboVenLbl").style["display"] = "";
						
						
					}
					
					
			
			}
			
			
			function validar(){		       
		      
				var bolNuevoVen = document.getElementById("frmMesaAyuda:nuevoVendedor");
			     
			    // alert(bolNuevoVen.value);
			  if(bolNuevoVen!=null){   
			     if(bolNuevoVen.value=='true'){
			     		document.getElementById("frmMesaAyuda:nuevo").style["display"] = "";
						document.getElementById("frmMesaAyuda:nonuevo").style["display"] = "none";
						//alert('1');
						
						document.getElementById("frmMesaAyuda:apeVenLbl").style["display"] = "";
						document.getElementById("frmMesaAyuda:apeVen").style["display"] = "";
						
						
						document.getElementById("frmMesaAyuda:nomVenLbl").style["display"] = "";
						document.getElementById("frmMesaAyuda:nomVen").style["display"] = "";
						document.getElementById("frmMesaAyuda:nombre").style["display"] = "";
						
						
						document.getElementById("frmMesaAyuda:divCboVen").style["display"] = "none";
						document.getElementById("frmMesaAyuda:cboVenLbl").style["display"] = "none";
			     }
			     
			      if(bolNuevoVen.value=='false'){
			     		document.getElementById("frmMesaAyuda:nuevo").style["display"] = "none";
						document.getElementById("frmMesaAyuda:nonuevo").style["display"] = "";
						//alert('2');
						
						document.getElementById("frmMesaAyuda:apeVenLbl").style["display"] = "none";
						document.getElementById("frmMesaAyuda:apeVen").style["display"] = "none";
						document.getElementById("frmMesaAyuda:apeVen").value = "";
						
						document.getElementById("frmMesaAyuda:nomVenLbl").style["display"] = "none";
						document.getElementById("frmMesaAyuda:nomVen").style["display"] = "none";
						document.getElementById("frmMesaAyuda:nombre").value = "";
						document.getElementById("frmMesaAyuda:nombre").style["display"] = "none";
						
						document.getElementById("frmMesaAyuda:divCboVen").style["display"] = "";
						document.getElementById("frmMesaAyuda:cboVenLbl").style["display"] = "";
			     }
			  }   
			
			}
			
			/**
		Funcion: formatoDecimales		
		Ej 145236.63 ó 14588,45
		No concidera signo para indicar mil.
		IngRSEN::12042007::
		*/ 
		function formatoDecimales(e,obj){	
								
		var tecla = (document.all) ? e.keyCode : e.which;
		//alert(tecla);
		
			if (tecla == 0 || tecla == 8) //para que funcione el TAB y el borrado en Mozilla
				return true;
						
			if (tecla < 44 || tecla ==45 || tecla ==47 ||  tecla > 57)//si el caracter no es numerico, no se retorna nada
			{
		  		return false;
			}
			else
			{
				//46 punto
				//44 coma		   
			   if(tecla ==46){		   
			   		var sueldo = obj ; 		   		
			   		
			   		//ya hay una coma?
			   		var c = sueldo.value.indexOf(",",0);
			   		if(c==-1){
			   			//no hay una coma
			   		 	var p = sueldo.value.indexOf(".",0);				   		 				   		 		   			   		
				   		if(p==-1){
				   			if(sueldo.value==""){
				   			    return false;
				   			}else
				   			{
				   			    return true;
				   			}
				   		 	
				   		}else{
				   		    return false;
				   		}
			   		}else{
			   		    return false;
			   		}	   		
			   	}
			   		
			   		
			   	if(tecla ==44){		   
			   		var sueldo = obj; 		   		
			   		
			   		//ya hay un punto?
			   		var c = sueldo.value.indexOf(".",0);
			   		if(c==-1){
			   			//no hay una coma
			   		 	var p = sueldo.value.indexOf(",",0);	
				   		if(p==-1){
				   		 	if(sueldo.value==""){
				   			    return false;
				   			}else
				   			{
				   			    return true;
				   			}
				   		}else{
				   		    return false;
				   		}
			   		}else{
			   		    return false;
			   		}
			   	}else{
			      return true;
			   }
				
			}
		
		}
		
		function validarCbo(cbo){
			
		
		  
   		    var cboPatente = document.getElementById("frmMesaAyuda:patente");
			//alert(cboPatente.value);
			
			if(cboPatente.value != -1){
			   return true;
			}
			
			 return false;
		}
		
		
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0" onload="validar()">
		<%@ include file="../header.jsp" %>				
		
		
		<h:form id="frmMesaAyuda" rendered="#{mesaDeAyudaBean.pantalla==1}"  >
		<c:if test="${!mesaDeAyudaBean.puedeIngresar}">
			<c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>	
		
		
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  rowClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText value="#{Message.tit_mesa_ayuda_label}"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
	  	
	 		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
						
			<h:messages styleClass="errorNegro"/>
			
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="subTituloConFondo" width="70%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.datos_autorizacion_label}" escape="false"/>				
				</t:column>
			</t:panelGrid>
		
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="2" columnClasses="cpo7Negrita, cpo8" width="70%" rowClasses="filaTablaCabecera">													
				
				<t:column></t:column>
					
				<t:column>
				<t:div style="text-align: right;;">
				         <t:graphicImage value="/img/atencion.png" alt="#{Message.mensaje_combos}" />
				         </t:div>
				</t:column>
				
				<%/*  vendedor  */%>
				<t:column>	
						<t:outputText id="cboVenLbl"  styleClass="datoNegrita" value="&nbsp;&nbsp;&nbsp;#{Message.vendedor_ccs_label}&nbsp;&nbsp;" escape="false" />							
						<t:outputText styleClass="datoNegrita" id="apeVenLbl" value="&nbsp;&nbsp;&nbsp;#{Message.apellido_label}&nbsp;&nbsp;" escape="false" />							
				</t:column>
				<t:column >
					
					<t:div id="divCboVen">
					<t:selectOneListbox id="cboVen"  size="1" value="#{mesaDeAyudaBean.vendedor}" styleClass="campo" immediate="true" >					
						<f:selectItems value="#{mesaDeAyudaBean.vendedores}" />
					</t:selectOneListbox>
										
          			</t:div>
          				
					<t:inputText id="apeVen" value="#{mesaDeAyudaBean.apellidoVendedor}" styleClass="campo" maxlength="60" size="20"/>				
					<h:outputText value="&nbsp;[x]&nbsp;" styleClass="datoObligatorio" escape="false"/>	
					<h:graphicImage  value="/img/icono_cuadrado.gif" id="nonuevo" style="border: none; vertical-align: middle;" onclick="habilitarCampos('nonuevo')"/>
					<h:graphicImage  value="/img/icono_recuperar.gif" id="nuevo" style="border: none; vertical-align: middle;"  onclick="habilitarCampos('nuevo')" />
					<t:inputHidden id="nuevoVendedor" value="#{mesaDeAyudaBean.nuevoVendedor}" />
					<h:outputText value="&nbsp;#{Message.nuevo_vendedor_label}"  styleClass="campoNoEditable" escape="false"/>
					
					
				</t:column>	
				
				
				<t:column>					
							<t:outputText  styleClass="datoNegrita" id="nomVenLbl" value="&nbsp;&nbsp;&nbsp;#{Message.nombre_label}&nbsp;&nbsp;" escape="false" />							
				</t:column>
				
				<t:column>					
						<t:inputText id="nombre" value="#{mesaDeAyudaBean.nombreVendedor}" styleClass="campo" maxlength="60" size="20"/>				
						<h:outputText id="nomVen" value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>	
		
				</t:column>			
				
				
				
				<%/*  vendedor  
				<t:column>	
				<t:div id="vende"> 					
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.vendedor_ccs_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>
				</t:div>
				
				<t:div id="apeVende">
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.apellido_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>
				</t:div>	
						
				</t:column>
				<t:column>
					<t:div id="cboVende">
						<h:selectOneListbox id="vendedores" size="1" value="#{mesaDeAyudaBean.vendedor}" styleClass="campo" immediate="true" >					
							<f:selectItems value="#{mesaDeAyudaBean.vendedores}" />
						</h:selectOneListbox>
						<h:outputText value="&nbsp;&nbsp;" escape="false"/>	
					</t:div>
					
					<t:div id="inpApeVende">
						<t:inputText  size="20" maxlength="100" value="#{mesaDeAyudaBean.apellidoVendedor}" styleClass="campo" />	
					</t:div>
					
					<h:selectBooleanCheckbox value="#{mesaDeAyudaBean.nuevoVendedor}"  onclick="habilitarCamposAltaVendedor(this)"/>
					<h:outputText id="label" value="&nbsp;#{Message.nuevo_vendedor_label}" escape="false" styleClass="campoNoEditable" />	
					
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>	
				</t:column>	
				
				<t:column>	
					<t:div id="nomVende">
						<t:outputLabel  styleClass="datoNegrita">
							<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.nombre_label}&nbsp;&nbsp;" escape="false" />							
						</t:outputLabel>
					</t:div>						
				</t:column>
				
				<t:column>
					<t:div id="inpNomVende">
						<t:inputText  size="20" maxlength="100" value="#{mesaDeAyudaBean.nombreVendedor}"  styleClass="campo" />	
					</t:div>
				</t:column>				
				
				*/%>
				<%/*  ccss  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.centro_servicio_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<h:selectOneListbox id="ccss" size="1" value="#{mesaDeAyudaBean.ccss}" styleClass="campo" 
						  immediate="true" 
						  onchange="if(validarCbo(this)) submit();" 
						  valueChangeListener="#{mesaDeAyudaBean.cargarProductosCCSS}">
						 
						<f:selectItems value="#{mesaDeAyudaBean.lstccss}" />
					</h:selectOneListbox>
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>	
				</t:column>	
				
				<%/*  clientes  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.cliente_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
				
				
					
						<h:selectOneListbox id="cliente" size="1" value="#{mesaDeAyudaBean.cliente}" styleClass="campo" immediate="true"  
	 					    onchange="submit();" valueChangeListener="#{mesaDeAyudaBean.cargarSusCombosAsociados}">
							<f:selectItems value="#{mesaDeAyudaBean.clientes}" />
						</h:selectOneListbox>
						<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>
					
				</t:column>				
				
				<%/*  motivos de autorizacion  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.motivo_autorizacion_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<h:selectOneListbox id="motivoAutorizacion" size="1" value="#{mesaDeAyudaBean.motivoAutorizacion}" styleClass="campo" 
					 onchange="submit();" immediate="true" 
					 valueChangeListener="#{mesaDeAyudaBean.cargarSusDescripciones}" >
					
						<f:selectItems value="#{mesaDeAyudaBean.motivosAutorizacion}" />
					</h:selectOneListbox>	
					<h:outputText value="&nbsp;[x]&nbsp;&nbsp;" styleClass="datoObligatorio" escape="false"/>
					
				</t:column>	
				
				<%/*  descripcion de autorizacion  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.descripcion_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<h:selectOneListbox id="descripcionMotAutorizacion" size="1" value="#{mesaDeAyudaBean.descripcionMotAutorizacion}" styleClass="campo" immediate="true">
						<f:selectItems value="#{mesaDeAyudaBean.descripcionesMotAutorizacion}" />
					</h:selectOneListbox>
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>	
				</t:column>							
					
			</t:panelGrid>	
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" width="60%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;" escape="false"/>
				</t:column>
			</t:panelGrid>
						
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="subTituloConFondo" width="70%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.datos_chofer_patente_label}" escape="false"/>
				</t:column>
			</t:panelGrid>
		
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="1" columnClasses="cpo7Negrita, cpo8" width="70%" rowClasses="filaTablaCabecera">													
					
					<t:column>
					<t:div style="text-align: right;;">
				         <t:graphicImage value="/img/atencion.png" alt="#{Message.mensaje_combos}" />
				    </t:div>
				   </t:column>
								
					<%/*  chofer  */%>
					<t:column>					
						<h:outputText value="&nbsp;&nbsp;&nbsp#{Message.ingrese_nombre_chofer_label}" styleClass="campoNoEditable" escape="false"/>								
					</t:column>	
					
					
					<t:column>	
					   <t:panelGrid border="0" cellpadding="4" cellspacing="0" 
						columns="2" columnClasses="cpo7Negrita, cpo8" width="70%" rowClasses="filaTablaCabecera">
						<t:column>
							<t:outputLabel  styleClass="datoNegrita">
								<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.nombre_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
							</t:outputLabel>	
						</t:column>
					
						<t:column>	
							<t:inputText  id="nombreChofer" value ="#{mesaDeAyudaBean.nombreChofer}" maxlength="40"  size="30" styleClass="campo"   />		
							<h:outputText  value="&nbsp;&nbsp;" escape="false" /> 
							<t:commandButton value="#{Message.buscar_label}" 
				                 actionListener="#{mesaDeAyudaBean.buscarChoferPorFiltro}" styleClass="boton" />	
				            <h:outputText  value="&nbsp;&nbsp;" escape="false" />
				            <t:saveState value="#{mesaDeAyudaBean.nombreChofer}"></t:saveState>   
						</t:column>					
					
						<t:column>
							<t:outputLabel  styleClass="datoNegrita">
								<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.chofer_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
							</t:outputLabel>					
						</t:column>	
					
						<t:column>															
							<h:selectOneListbox id="chofer" size="1" value="#{mesaDeAyudaBean.chofer}" styleClass="campo"  immediate="true">
								<f:selectItems value="#{mesaDeAyudaBean.choferes}" />
							</h:selectOneListbox>	
							<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>
						</t:column>						
					</t:panelGrid>					
				</t:column>
					
				
				<%/*  chofer  */%>
				<t:column>					
					<h:outputText value="&nbsp;&nbsp;&nbsp#{Message.datos_de_consumo_y_disponibilidad_label}" styleClass="campoNoEditable" escape="false"/>								
				</t:column>	
				
				<t:column>					
					<h:outputText value="&nbsp;&nbsp;&nbsp#{Message.ingrese_patente_vehiculo_label}" styleClass="campoNoEditable" escape="false"/>								
				</t:column>
				
				
				<t:column>	
				   <t:panelGrid border="0" cellpadding="4" cellspacing="0" 
					columns="2" columnClasses="cpo7Negrita, cpo8" width="70%" rowClasses="filaTablaCabecera">				
					
					<t:column>
						<t:outputLabel  styleClass="datoNegrita">
							<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.patente_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
						</t:outputLabel>
					</t:column>
					<t:column>						
						<t:inputText  id="numeroPatente" value ="#{mesaDeAyudaBean.numeroPatente}" maxlength="10"  size="30" styleClass="campo"   />		
						<h:outputText  value="&nbsp;&nbsp;" escape="false" /> 
						<t:commandButton value="#{Message.buscar_label}" 
			                 actionListener="#{mesaDeAyudaBean.buscarPatentePorFiltro}" styleClass="boton" />	
			            <h:outputText  value="&nbsp;&nbsp;" escape="false" />
			            <t:saveState value="#{mesaDeAyudaBean.numeroPatente}"></t:saveState>   
					</t:column>									
			
				<%/*  patente  */%>
					<t:column>							
						<t:outputLabel  styleClass="datoNegrita">
							<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.patente_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
						</t:outputLabel>
					</t:column>
				
				<t:column>
					<h:selectOneListbox id="patente" size="1" value="#{mesaDeAyudaBean.patente}" styleClass="campo" 
					   onchange="submit();" immediate="true" 
					   valueChangeListener="#{mesaDeAyudaBean.cargarLimiteCarga}" >
					   
						<f:selectItems value="#{mesaDeAyudaBean.patentes}" />
					</h:selectOneListbox>
					<h:outputText value="&nbsp;[x]&nbsp;&nbsp;" styleClass="datoObligatorio" escape="false"/>						
				</t:column>				
				</t:panelGrid>				
				</t:column>
								
				<t:column>
				<%/*datos de limite carga de la patente*/ %>
					<t:dataTable value="#{mesaDeAyudaBean.lstLimiteCarga}" var="item" 
							 rowClasses="fila1,fila1" 
							 columnClasses="columnaTablaTexto, columnaTablaTexto, columnaTablaTexto, columnaTablaTexto,  columnaTablaNumero, columnaTablaNumero, columnaTablaNumero"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 renderedIfEmpty="false">
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.mes_label}"/>
						</f:facet>
						<h:outputText value="#{item.mes} " styleClass="campoNoEditable"/>						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.anio_label}"/>
						</f:facet>
						<h:outputText value="#{item.anio} " styleClass="campoNoEditable"/>						
					</h:column>
									
					<h:column>
						<f:facet name="header">
							<h:outputText value="TIPO"/>
						</f:facet>
						<h:outputText value="#{item.familiaGrupoArticuloDesc}" styleClass="campoNoEditable"  />						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.descripcion_label}"/>
						</f:facet>
						<h:outputText value="#{item.descripcion}" styleClass="campoNoEditable"  />						
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cupo_lts_label}"/>
						</f:facet>
						 <h:outputText value="#{item.cupoLitros}" rendered="#{!item.ilimitado}" styleClass="campoNoEditable" >
						 	<f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>						 
						 <h:outputText value="Ilimitado" rendered="#{item.ilimitado}" styleClass="campoNoEditable" />
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.consumo_lst_label}"/>
						</f:facet>
						<h:outputText value="#{item.consumoLitros}" styleClass="campoNoEditable" >
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						</h:outputText>
					</h:column>		
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.disponible_lts_label} "/>
						</f:facet>
						 <h:outputText value="#{item.disponibleLitros}" rendered="#{!item.ilimitado}" styleClass="campoNoEditable" >
						 	<f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>						 
						  <h:outputText value="Ilimitado" rendered="#{item.ilimitado}" styleClass="campoNoEditable" />
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.obs_label} "/>
						</f:facet>
												 
						  <h:outputText value="#{item.observacion}" styleClass="campoNoEditable" />
					</h:column>				
						
					</t:dataTable>	
					
				</t:column>							
					
			</t:panelGrid>
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" width="70%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;" escape="false"/>
				</t:column>
			</t:panelGrid>
			
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="subTituloConFondo" width="70%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.datos_remitos_label}" escape="false"/>
				</t:column>
			</t:panelGrid>
		
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="1" columnClasses="cpo8, cpo8" width="70%" rowClasses="filaTablaCabecera">													
					
				<t:column>
					<t:div style="text-align: right;;">
				         <t:graphicImage value="/img/atencion.png" alt="#{Message.mensaje_combos}" />
				    </t:div>
				</t:column>
				
						
				<%/*  comprobante manual  */%>
				<t:column>							
					
					<t:outputText styleClass="datoNegrita" value="&nbsp;&nbsp;&nbsp;#{Message.nro_remito_manua_label}&nbsp;&nbsp;" escape="false" />	
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>							
																			
					<h:selectOneRadio id="tipoComprobanteManual" value="#{mesaDeAyudaBean.tipoCompManual}" styleClass="campoNoEditable" onclick="habilitarCamposAnular(this)">
					     <f:selectItems  value="#{mesaDeAyudaBean.tiposCompManual}"/>
					</h:selectOneRadio>	
										
				</t:column>
				
				
				
				<%/*  nro remito  */%>
				<t:column>
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.nro_comprobante_manual_label}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>
					<t:inputText  id="nroRemito1M" value ="#{mesaDeAyudaBean.nroRemitoUnoManual}" maxlength="10"  size="10" styleClass="campo"  onkeypress="return soloNumeros(event);" />
					<t:outputText value="&nbsp;-&nbsp;" escape="false" />							
					<t:inputText  id="nroRemito2M" value ="#{mesaDeAyudaBean.nroRemitoDosManual}" maxlength="10"  size="10" styleClass="campo"  onkeypress="return soloNumeros(event);" />
					<%/*<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>*/%>
				</t:column>		
				
				<%/*  producto  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.producto_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>					
					<h:selectOneListbox id="producto" size="1" value="#{mesaDeAyudaBean.producto}" styleClass="campo" immediate="true" >
						<f:selectItems value="#{mesaDeAyudaBean.productos}" />
					</h:selectOneListbox>	
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>
						
				</t:column>
				
				
				<%/*  litors a cargar  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.litros_a_acargar_label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>
					<t:inputText  id="litrosCargar" value ="#{mesaDeAyudaBean.litrosACargar}" maxlength="20"  size="20" styleClass="campo"  onkeypress="return formatoDecimales(event,this);" />		
					<%/*h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/
					*/%>
				</t:column>
							
				
				<t:column>					
					<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.complete_remito_label}"  escape="false" styleClass="campoNoEditable"/>					
				</t:column>	
				
				
				<%/*  nro remito anular  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.nro_remito_anular_label}&nbsp;&nbsp;" escape="false" />							
												
					</t:outputLabel>	
					<t:inputText  id="tipoComprobanteAnular" value="#{mesaDeAyudaBean.tipoComprobanteAnular}" maxlength="10"  size="10" styleClass="campo" readonly="true"/>		
				</t:column>
				<t:column>
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.nro_comprobante_anular_label}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>
					<t:inputText  id="nroRemito1A" value ="#{mesaDeAyudaBean.nroRemitoUnoAnular}" maxlength="10"  size="10" styleClass="campo"  onkeypress="return soloNumeros(event);" />
					<t:outputText value="&nbsp;-&nbsp;" escape="false" />							
					<t:inputText  id="nroRemito2A" value ="#{mesaDeAyudaBean.nroRemitoDosAnular}" maxlength="10"  size="10" styleClass="campo"  onkeypress="return soloNumeros(event);" />
					<t:outputText value="&nbsp;" escape="false" />	
					<t:inputText   id="marcaObli" value="" styleClass="campoNoEditable"  />		
					
				</t:column>		
				
				
				<%/* fecha  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.fecha_pedido_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				
					<h:inputText id="fechaPedido" value="#{mesaDeAyudaBean.fechaPedido}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
					<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
					</h:inputText>	
					<h:outputText value="&nbsp;" escape="false"></h:outputText>					
					<h:outputLink value="javascript:doCalendario4('Calendar4','frmMesaAyuda:fechaPedido');">
					<h:graphicImage style="border: none; vertical-align: middle;" value="/img/calendario.gif" width="25px" height="20"/>
					</h:outputLink>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					<t:outputText value="(#{Message.fecha_mascara_label})"  styleClass="campoNoEditable"/>
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/>
					
					<t:div id="fecha">
					<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.coloque_fecha_label}" escape="false" styleClass="campoNoEditable"/>
					</t:div>
					
				</t:column>	
						
				
				
			</t:panelGrid>
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" width="60%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;" escape="false"/>
				</t:column>
			</t:panelGrid>
			
			<%/*
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="subTituloConFondo" width="70%">				
				<t:column>
					<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.datos_codigos_label}" escape="false"/>
				</t:column>
			</t:panelGrid>
		
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="1" columnClasses="cpo8" width="70%" rowClasses="filaTablaCabecera">													
				
				
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.codigo_autorizacion_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{mesaDeAyudaBean.codigo.nroAuto}" escape="false" />							
					</t:outputLabel>
				</t:column>	
										
			</t:panelGrid>
			*/ %>
			
			<h:messages styleClass="errorNegro"/>
			
			<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="3" columnClasses="nada, nada,nada" width="70%" >				
				<h:column>
				<t:panelGrid border="0" cellpadding="" cellspacing="0" styleClass="columnaTablaTexto" 
						columns="1" columnClasses="cpo11" width="100%" >							
						<t:column>
							<t:div styleClass="cpo11">
								<t:outputText id="datosOblig" value="&nbsp;&nbsp;#{Message.datos_obligatorios_texto}"  styleClass="datoObligatorio" escape="false"/>
							</t:div>
						</t:column>										
				</t:panelGrid>				
				</h:column>	
				<t:column>
				 <h:commandButton  actionListener="#{mesaDeAyudaBean.guardar}" 
						value="#{Message.guardar_label}" styleClass="boton" />	
				 <t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;"  escape="false"/>
				 
				 <t:commandButton value="#{Message.volver_label}" 
					action="#{mesaDeAyudaBean.volver}" styleClass="boton"/>		  		                 
				
				</t:column>	
				<t:column>    
				<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;"  escape="false"/>	
				</t:column>	
			</t:panelGrid>
				

			<t:saveState value="#{mesaDeAyudaBean.vendedor}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.vendedores}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.ccss}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.lstccss}"></t:saveState>
			<t:saveState id="ncliente" value="#{mesaDeAyudaBean.cliente}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.clientes}"></t:saveState>														
			<t:saveState value="#{mesaDeAyudaBean.motivosAutorizacion}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.motivoAutorizacion}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.descripcionMotAutorizacion}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.descripcionesMotAutorizacion}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.chofer}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.choferes}"></t:saveState>					
			<t:saveState value="#{mesaDeAyudaBean.patente}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.patentes}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.codigo}"></t:saveState>		
			<t:saveState value="#{mesaDeAyudaBean.fechaPedido}"></t:saveState>		
			<t:saveState value="#{mesaDeAyudaBean.nroRemitoUnoManual}"></t:saveState>
			<t:saveState value="#{mesaDeAyudaBean.nroRemitoDosManual}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.nroRemitoUnoAnular}"></t:saveState>		
			<t:saveState value="#{mesaDeAyudaBean.nroRemitoDosAnular}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.litrosACargar}"></t:saveState>		
			<t:saveState value="#{mesaDeAyudaBean.codigo}"></t:saveState>			
			<t:saveState value="#{mesaDeAyudaBean.producto}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.productos}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.lstLimiteCarga}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.tipoComprobanteAnular}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.vieneDe}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.codAutorizacion}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.fecha}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.codEmisor}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.idCodigoAuto}"></t:saveState>	
			<t:saveState value="#{mesaDeAyudaBean.nroAutorizacion}"></t:saveState>	
					
		</t:panelGrid>	
		
		</h:form>	
		
		
		<h:form id="mostrarFinal" rendered="#{mesaDeAyudaBean.pantalla==2}">
		<c:if test="${!mesaDeAyudaBean.puedeIngresar}">
			 <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if>
		
		<t:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
			<!--  TITULO  -->		
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  rowClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText value="#{Message.tit_mesa_ayuda_label}"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
	  	
	 		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>		
			
			<h:panelGrid columns="1" width="70%" columnClasses="campo10" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
									
				<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>					
				<h:column>
						<h:outputText value="#{mesaDeAyudaBean.mensajeGuardado}" style="color: #3A25A2; font-size: 11px;" styleClass="campo10"/>
				</h:column>								
				<h:column></h:column>				
				<h:column>	
				
				<t:div rendered="#{mesaDeAyudaBean.vieneDe=='NUE'}">
					<t:commandButton value="#{Message.volver_label}" 
		                 actionListener="#{mesaDeAyudaBean.volverAFormulario}" styleClass="boton" />	
		            <h:outputText  value="&nbsp;&nbsp;" escape="false" />
		            <t:commandButton value="#{Message.inicio_label}" 
		                 action="#{mesaDeAyudaBean.volver}" styleClass="boton" />	    
	           </t:div> 
	           
	           <t:div rendered="#{mesaDeAyudaBean.vieneDe=='MODOK'}">					
		            <t:commandButton value="#{Message.inicio_label}" 
		                 action="#{mesaDeAyudaBean.volver}" styleClass="boton" />	    
	           </t:div> 
		             
		                 
						                 
				</h:column>
				<h:column><h:outputText  value="&nbsp;&nbsp;" escape="false" />	</h:column>
			 	</h:panelGrid>	
			</t:panelGrid>
		
		
		</h:form>
			
		
		
		

		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"   width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
			</t:panelGrid>
		<%@ include file="../footer.jsp" %>
	</body>
	
	
	
	<script> 		 
		 var z=dhtmlXComboFromSelect("frmMesaAyuda:cboVen");
		 z.enableFilteringMode(true);
				
		
				 
		 var cliente=dhtmlXComboFromSelect("frmMesaAyuda:cliente");
		 cliente.enableFilteringMode(true);
		 
		 var chofer=dhtmlXComboFromSelect("frmMesaAyuda:chofer");
		 chofer.enableFilteringMode(true);
		 
		 var patente=dhtmlXComboFromSelect("frmMesaAyuda:patente");
		 patente.enableFilteringMode(true);
		
	</script>
			
</html> 
</f:view>