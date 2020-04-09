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
			<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/cal2.js"></script>
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsfechas.js"></script>
					
		<script>		
		 
		var ventanaCalendario = null;   
		
		function doCalendario(nom,control) 
		{
		  	addCalendar(nom, "Seleccione Fecha", control, "frmAlta");
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
		    return true;
		    }
		    
		    function esBisiesto(ano){
				return ((ano%4==0 && ano%100!=0)||(ano%400==0)?true:false)
			}
			
					
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
		
		
		 function formatoDecimalesComision(e, vieneDe) {

			 
		        var tecla = (document.all) ? e.keyCode : e.which;
		        //alert(tecla); 

		        if (tecla == 0 || tecla == 8) //para que funcione el TAB y el borrado en Mozilla 
		            return true;

		        if (tecla < 44 || tecla == 45 || tecla == 47 || tecla == 44 || tecla > 57)//si el caracter no es numerico, no se retorna nada    
		        {

		            return false;
		        } else {


		            //46 punto   //44 coma 

		             if (tecla == 46) {

		                var sueldo = 0;

		                if (vieneDe == 1) {
		                    sueldo = document.getElementById('frmAlta:cantidad'); 
		                } 

		                //ya hay una coma? 
		                var c = sueldo.value.indexOf(",", 0);

		                if (c == -1) {
		                    //no hay una coma 
		                    var p = sueldo.value.indexOf(".", 0);

		                    if (p == -1) {
		                        if (sueldo.value == "") {
		                            return false;
		                        } else {
		                            return true;
		                        }

		                    } else {
		                        return false;
		                    }

		                } else {

		                    //hay una coma
		                    return false;
		                }

		            }
	  


		            if (tecla == 44) {

		                var sueldo = 0;

		                //ya hay un punto?
		                 if (vieneDe == 1) {
		                    sueldo = document.getElementById('frmAlta:cantidad'); 
		                }      


		                var c = sueldo.value.indexOf(".", 0);

		                if (c == -1) {
		                    //no hay una coma 
		                    var p = sueldo.value.indexOf(",", 0);

		                    if (p == -1) {
		                        if (sueldo.value == "") {
		                            return false;
		                        } else {


		                            return true;
		                        }

		                    } else {
		                        return false;
		                    }

		                } else {
		                    return false;
		                }

		            } else {
		                return true;
		            }
		        }
		    }
		 
		  function mascaraVolumen(valor) {
		        // alert(valor.value);
		        var c = valor.value.indexOf(",", 0);

		        if (c == -1) {
		            //no hay una coma 
		            var p = valor.value.indexOf(".", 0);

		            if (p == -1) {
		                // no hay punto
		            } else {
		                //hay una punto

		                var cad = valor.value.substring(p);
		                if (cad.length > 3) {
		                    valor.value = valor.value.substring(0, p + 3);
		                }
		            }
		        } else {
		            //hay una coma            

		            var cad = valor.value.substring(c);
		            //alert(cad);
		            if (cad.length > 3) {
		                valor.value = valor.value.substring(0, c + 3);
		                //alert(" dd " + valor.value);
		            }
		        }

		        var iComa = valor.value.indexOf(",", 0);
		        var iPunto = valor.value.indexOf(".", 0);
		        //alert(valor.value.length);
		        if (iPunto == -1 && iComa == -1 && valor.value.length == 10) {

		            var entero = 0;
		            var decimal = 0;

		            entero = valor.value.substring(0, 7);
		            decimal = valor.value.substring(7, 9);
		            valor.value = entero + "," + decimal;

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
			
			
			
			
			<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="1" columnClasses=" cpo8Normal " width="100%" rowClasses="filaTablaCabecera"
				rendered="#{facturacionRemitosBean.mostrarCodBarra}"> 
				<%/*  tarjeta  */%>
				<t:column >	 
						&nbsp;&nbsp;1. Ingresar el Nro de Tarjeta. 
				</t:column> 
				</t:panelGrid>
				
			<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="2" columnClasses="cpo8-Var13, cpo8 " width="100%" rowClasses="filaTablaCabecera"
				rendered="#{facturacionRemitosBean.mostrarCodBarra}">									
			  	
				<%/*  tarjeta  */%>
				<t:column >							
					<t:outputLabel  styleClass="dato">
						<t:outputText value="&nbsp;&nbsp;#{Message.nro_tarjeta_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column  >   
					<h:inputSecret id="clave" value="#{facturacionRemitosBean.facturacionRemitoTO.codTarjeta}" 
					  size="30" maxlength="25" styleClass="campo" 	onchange="submit();" immediate="true" >
						<f:validateLength minimum="1"/> 
					</h:inputSecret>
					&nbsp;&nbsp;
					 <h:commandButton  actionListener="#{facturacionRemitosBean.validarVehiculo}" 
						value="Validar" styleClass="boton" /> 
				</t:column> 
				</t:panelGrid>
				
				
				 <%/*  Datos de chofer  */%>
			    <t:panelGrid border="0" cellpadding="4" cellspacing="0"  	columns="1" columnClasses="  cpo8 " width="100%" rowClasses="filaTablaCabecera">									
				<t:column  >
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;Datos del CCSS " escape="false" styleClass="titulos"/>
					</t:outputLabel>
		  	   </t:column>
				</t:panelGrid>
			
			<t:panelGrid border="0" cellpadding="4" cellspacing="0" 
				columns="6" columnClasses="cpo8-Var7,cpo8Normal,cpo8-Var7,cpo8Normal,cpo8-Var7,cpo8Normal " width="100%" rowClasses="filaTablaCabecera">
				<%/*  turno  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.turno_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
				 	 <h:outputText id="descTurno" value ="#{facturacionRemitosBean.facturacionRemitoTO.descTurno}" />			
				</t:column>
				
				
				<%/*  CCSS */%> 
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.ccss_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
			 	 <h:outputText id="descCCSS" value ="#{facturacionRemitosBean.facturacionRemitoTO.descCCSS}" />		
				</t:column>
				
				
				<%/*  almacen  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.almacen_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
				  <h:outputText id="descAlmacen" value ="#{facturacionRemitosBean.facturacionRemitoTO.descAlmacen}" />		
			 	</t:column> 
				
				
			</t:panelGrid>	
			
			    <%/*  Datos de cliente */%>
			    <t:panelGrid border="0" cellpadding="4" cellspacing="0"  	columns="1" columnClasses="  cpo8 " width="100%" rowClasses="filaTablaCabecera">									
				<t:column  >
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;Datos del Cliente " escape="false" styleClass="titulos"/>
					</t:outputLabel>
		  	   </t:column>
				</t:panelGrid>  
				
				
				<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="4" columnClasses="cpo8-Var7,cpo8Normal40,cpo8-Var7,cpo8 " width="100%" rowClasses="filaTablaCabecera">						
				<%/*  cliente  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.cliente_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
				        <h:outputText id="codAlfaCliente" value ="#{facturacionRemitosBean.facturacionRemitoTO.codAlfaCliente}" >
			 			</h:outputText>&nbsp;&nbsp;-&nbsp;&nbsp;	
			 			<h:outputText id="descCliente" value ="#{facturacionRemitosBean.facturacionRemitoTO.descCliente}" >
			 			</h:outputText>
				</t:column>  
	
		        <%/* fecha */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.fecha_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<h:inputText id="fecha" value="#{facturacionRemitosBean.facturacionRemitoTO.fecha}" readonly="true" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
						<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
					</h:inputText>	
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
				    <h:outputLink value="javascript:doCalendario('Calendar','frmAlta:fecha');">
					<h:graphicImage style="border: none; vertical-align: middle;" value="/img/calendario.gif" width="25px" height="20"/>
					</h:outputLink>
				</t:column> 
				</t:panelGrid> 
			
			
			
			
			   <%/*  Datos de chofer  */%>
			    <t:panelGrid border="0" cellpadding="4" cellspacing="0"  	columns="1" columnClasses="  cpo8 " width="100%" rowClasses="filaTablaCabecera">									
				<t:column  >
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.datos_chofer_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
		  	   </t:column>
				</t:panelGrid>
			
			
			
			<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="1" columnClasses=" cpo8Normal " width="100%" rowClasses="filaTablaCabecera"
				 > 
				<%/*  tarjeta  */%>
				<t:column >	 
						&nbsp;&nbsp;2. Ingresar los datos del chofer. 
				</t:column> 
				</t:panelGrid>
			
			<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="8" columnClasses="cpo8-Var7,cpo8Normal,cpo8-Var7,cpo8,cpo8-Var7,cpo8,cpo8-Var7,cpo8 " width="100%" rowClasses="filaTablaCabecera">
			
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.patente_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				 <t:column>
			 			<h:outputText id="patente" value ="#{facturacionRemitosBean.facturacionRemitoTO.patente}" >
			 			</h:outputText>		
		 		</t:column>
				
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.dni_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				 <t:column>
			 			<t:inputText id="dniChofer" value ="#{facturacionRemitosBean.facturacionRemitoTO.dniChofer}"  
			 			maxlength="20"  size="20" styleClass="campo" readonly="#{facturacionRemitosBean.habilitarDatosChofer}"></t:inputText>	
			 			 
		 		</t:column>
		 		
		 		<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.pin2_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				 <t:column>
			 			<t:inputText id="pinChofer" value ="#{facturacionRemitosBean.facturacionRemitoTO.pinChofer}" maxlength="20"  size="4" styleClass="campo" readonly="#{facturacionRemitosBean.habilitarDatosChofer}"></t:inputText>		
		 		</t:column>
		 		
		 		<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.kilometaje_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				 <t:column>
			 			<t:inputText id="kilometraje" value ="#{facturacionRemitosBean.facturacionRemitoTO.kilometraje}" maxlength="20"  size="20" styleClass="campo" readonly="#{facturacionRemitosBean.habilitarDatosChofer}"></t:inputText>		
		 		</t:column> 
				
				</t:panelGrid>
				
				
				<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="4" columnClasses="  cpo8-Var13,cpo8,cpo8-Var13,cpo8 " width="100%" rowClasses="filaTablaCabecera">	
				
				<%/*  grupo de unidad de negocio  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.forma_de_pago_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
				   	<h:selectOneListbox id="formaPago" size="1" value="#{facturacionRemitosBean.formaPago}"  styleClass="campo"
					immediate="true" >	
						<f:selectItems value="#{facturacionRemitosBean.formasPago}"/>
					</h:selectOneListbox>
	     		</t:column> 
	     		
	     		<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.producto_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
				   	<h:selectOneListbox id="producto" size="1" value="#{facturacionRemitosBean.producto}"  style="width:100px" styleClass="campo"
					       onchange="submit();" immediate="true" 
						   valueChangeListener="#{facturacionRemitosBean.cargarProducto}" >	
						<f:selectItems value="#{facturacionRemitosBean.productos}"/>
					</h:selectOneListbox>
	     		</t:column> 
	     </t:panelGrid>
	     		
			
			<% /* seleccio de articulos   */ %>
			<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="1" columnClasses="  cpo8 " width="100%" rowClasses="filaTablaCabecera">									
				<t:column  >
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.seleccion_articulos_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
		  	   </t:column>
				</t:panelGrid>
			 
		  <t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="8" columnClasses="cpo8-Var7, cpo8-Var7,cpo8-Var7, cpo8-Var7,cpo8-Var7, cpo8-Var7, cpo8-Var7, cpo8-Var7" width="100%" rowClasses="filaTablaCabecera">									
		 
				
				<%/*  tarjeta  */%>
				<t:column>							
					<t:outputLabel  styleClass="dato">
						<t:outputText value="&nbsp;&nbsp;Código&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					 <t:inputText id="codArticulo" value ="#{facturacionRemitosBean.codArticulo}" maxlength="20"  size="10" styleClass="campo" readonly="true"></t:inputText>		
				 </t:column>
				 
				 <t:column>							
					<t:outputLabel  styleClass="dato">
						<t:outputText value="&nbsp;&nbsp;Descripcion&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					 <t:inputText id="descArticulo" value ="#{facturacionRemitosBean.descArticulo}" maxlength="20"  size="20" styleClass="campo" readonly="true"></t:inputText>		
				 </t:column>
				 
				  <t:column>							
					<t:outputLabel  styleClass="dato">
						<t:outputText value="&nbsp;&nbsp;Cantidad&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					 <t:inputText id="cantidad" value ="#{facturacionRemitosBean.cantArticulo}" maxlength="20"  size="10" styleClass="campo" 
					 readonly="false" onkeyup="mascaraVolumen(this)" onkeypress="return formatoDecimalesComision(event,1);"></t:inputText>		
				 </t:column>
				 
					<t:column>		
					 &nbsp;&nbsp;
					 <h:commandButton  actionListener="#{facturacionRemitosBean.incluirLinea}" 
						value="Incluir" styleClass="boton" /> 
				</t:column>
				<t:column/>
				 
			</t:panelGrid>	
			
			
			<% /* seleccio de articulos   */ %>
			<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="1" columnClasses="  cpo8 " width="100%" rowClasses="filaTablaCabecera">									
				<t:column  >
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;Detalle de  Productos" escape="false" styleClass="titulos"/>
					</t:outputLabel>
		  	   </t:column>
				</t:panelGrid>
				
				<h:panelGrid width="100%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{facturacionRemitosBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses=" columnaTablaNumero, columnaTablaTexto, columnaTablaNumero,columnaTablaCentrada"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							   >
					<h:column>
						<f:facet name="header">
							<h:outputText value="Codigo Artículo"/>
						</f:facet>
						<h:outputText value="#{item.codArticulo}  " escape="false"/>
						 
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Descripción Artículo"/>
						</f:facet>
						<h:outputText value="#{item.descripcion} " />
						
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Cantidad"/>
						</f:facet>
						<h:outputText value="#{item.cantidad}"/>
					</h:column> 
					
					
						<%/* modificar */%>
					<h:column>  
						<f:facet name="header">
							<h:outputText value="Acción"/>
						</f:facet>	
					     						
						   <t:commandButton styleClass="botonSm" 
					     				value="Quitar"
									   actionListener="#{facturacionRemitosBean.incluirLinea}" 								  
									   title="Quitar" > 
									   <f:param name="codClienteAlfa" id="codClienteAlfa" value="#{item.codArticulo}"/> 
						</t:commandButton>
						
					</h:column>		
						
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
			
			
			
		     	<t:saveState value="#{facturacionRemitosBean.subItemsNivel1}"></t:saveState>
				<t:saveState value="#{facturacionRemitosBean.codArticulo}"></t:saveState>
				<t:saveState value="#{facturacionRemitosBean.descArticulo}"></t:saveState> 
				<t:saveState value="#{facturacionRemitosBean.cantArticulo}"></t:saveState> 
				<t:saveState value="#{facturacionRemitosBean.producto}"></t:saveState>
				<t:saveState value="#{facturacionRemitosBean.productos}"></t:saveState> 
				<t:saveState value="#{facturacionRemitosBean.formaPago}"></t:saveState>
				<t:saveState value="#{facturacionRemitosBean.formasPago}"></t:saveState>	 
				<t:saveState value="#{facturacionRemitosBean.facturacionRemitoTO}"></t:saveState> 
				<t:saveState value="#{facturacionRemitosBean.mostrarCodBarra}"></t:saveState>
				<t:saveState value="#{facturacionRemitosBean.habilitarDatosChofer}"></t:saveState>
			 	
		</t:panelGrid>
		
		
		<h:panelGrid columns="3" width="40%" columnClasses="columnaTablaNumero,columnaTablaNumero "  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
			     	<h:column>				    		
			    	 <t:commandButton value="#{Message.volver_label}" 
					action="#{facturacionRemitosBean.volver}" styleClass="boton"/>
			    	</h:column>
			    	<h:column>				    		
			    	 <t:commandButton value="Limpiar" 
					action="#{facturacionRemitosBean.volver}" styleClass="boton"/>
			    	</h:column>
			    	<h:column>	
			    	 <h:commandButton  actionListener="#{facturacionRemitosBean.generar}" 
						value="Generar" styleClass="boton" />	
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