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
	</head>
	<script>
	
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
	                    sueldo = document.getElementById('frmFrmModificar:ltrCargaC'); 
	                }
	                if (vieneDe == 2) {
	                    sueldo = document.getElementById('frmFrmModificar:ltrDiaC'); 
	                }
	                if (vieneDe == 3) {
	                    sueldo = document.getElementById('frmFrmModificar:ltrMesC'); 
	                }
// 	                 if (vieneDe == 4) {
// 	                    sueldo = document.getElementById('frmFrmModificar:ltrCargaA'); 
// 	                }
// 	                if (vieneDe == 5) {
// 	                    sueldo = document.getElementById('frmFrmModificar:ltrDiaA'); 
// 	                }
// 	                if (vieneDe == 6) {
// 	                    sueldo = document.getElementById('frmFrmModificar:ltrMesA'); 
// 	                }  
	                

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
	                    sueldo = document.getElementById('frmFrmModificar:ltrCargaC'); 
	                }
	                if (vieneDe == 2) {
	                    sueldo = document.getElementById('frmFrmModificar:ltrDiaC'); 
	                }
	                if (vieneDe == 3) {
	                    sueldo = document.getElementById('frmFrmModificar:ltrMesC'); 
	                }
	               /*  if (vieneDe == 4) {
	                    sueldo = document.getElementById('frmFrmModificar:ltrCargaA'); 
	                }
	                if (vieneDe == 5) {
	                    sueldo = document.getElementById('frmFrmModificar:ltrDiaA'); 
	                }
	                if (vieneDe == 6) {
	                    sueldo = document.getElementById('frmFrmModificar:ltrMesA'); 
	                } */            


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
	
	
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0">
		<%@ include file="../header.jsp" %>	
	
	<h:form id="frmFiltroCuenta" rendered="#{refReporteCuposCargaBean.mostrarFrmLista}">	
		<t:saveState value="#{refReporteCuposCargaBean.pagina}"></t:saveState>
		<t:saveState value="#{refReporteCuposCargaBean.fltPatente}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteCuposCargaBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  
		  <t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_cupos}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>			
	   		
				
		<%/*FILTROS*/%>
		
			<h:panelGrid columns="4" width="100%" columnClasses="cpo8-Var,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
				</h:column>	
				<h:column></h:column>
				<h:column></h:column>
				<h:column></h:column> 
			</h:panelGrid>
			
			<%/*  familia grupo producto  */%>
			<h:panelGrid columns="8" width="100%" columnClasses="cpo8-Var10,cpo8,cpo8-Var7,cpo8,cpo8-Var7,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				<t:column>							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.familia_label}" escape="false" />							
				</t:column>
				<t:column>
					<h:selectOneListbox  size="1" value="#{refReporteCuposCargaBean.familia}" styleClass="campo"  >
						<f:selectItems value="#{refReporteCuposCargaBean.familias}" />
					</h:selectOneListbox>  
				</t:column>	
				
				<t:column>
				     <t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.grupo_label}" escape="false" />	
				</t:column>
				<t:column>
					<h:selectOneListbox  size="1" value="#{refReporteCuposCargaBean.grupo}" styleClass="campo"  >
						<f:selectItems value="#{refReporteCuposCargaBean.grupos}" />
					</h:selectOneListbox>  
				</t:column>
				
				<t:column>
				     <t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.producto_label}" escape="false" />	
				</t:column>
				<t:column>
					<h:selectOneListbox  size="1" value="#{refReporteCuposCargaBean.producto}" styleClass="campo"  >
						<f:selectItems value="#{refReporteCuposCargaBean.productos}" />
					</h:selectOneListbox>  
				</t:column>
				<t:column>
				    	
				</t:column>
				<t:column>
				     	
				</t:column>
							
			</h:panelGrid>
			
			
			<%/*  clientes  */%>
			<h:panelGrid columns="3" width="100%" columnClasses="cpo8-Var10,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				<h:column>
					      <t:outputLabel for="patente" styleClass="cpo8">
							<t:outputText value="&nbsp;&nbsp;#{Message.patente_label}"  escape="false"/>
						  </t:outputLabel>
				</h:column>	
				<h:column>				     
						<t:inputText id="nombreChofer" value="#{refReporteCuposCargaBean.fltPatente}" size="20" maxlength="50" styleClass="campo" />
				</h:column>	
			    <t:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" /></t:column>
					
				<t:column rendered="#{sessionScope.usuario.tipo==0}">							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.cliente_label}" escape="false" />							
				</t:column>
				<t:column rendered="#{sessionScope.usuario.tipo==0}">
					<h:selectOneListbox id="cliente" size="1" value="#{refReporteCuposCargaBean.cliente}" styleClass="campo"  >
						<f:selectItems value="#{refReporteCuposCargaBean.clientes}" />
					</h:selectOneListbox>						
				</t:column>	
				<t:column><t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" /></t:column>
							
			</h:panelGrid>
			
			<%/*  mes  
			<h:panelGrid columns="3" width="80%" columnClasses="cpo8-25,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				<t:column rendered="#{sessionScope.usuario.tipo==0}">							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.mes_label}" escape="false" />							
				</t:column>
				<t:column rendered="#{sessionScope.usuario.tipo==0}">
					<h:selectOneListbox  size="1" value="#{refReporteCuposCargaBean.mes}" styleClass="campo"  >
						<f:selectItems value="#{refReporteCuposCargaBean.lstMeses}" />
					</h:selectOneListbox>						
					
				
				<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.anio_label}&nbsp;&nbsp;" escape="false" />							
				
					<h:selectOneListbox  size="1" value="#{refReporteCuposCargaBean.anio}" styleClass="campo"  >
						<f:selectItems value="#{refReporteCuposCargaBean.lstAnios}" />
					</h:selectOneListbox>						
				</t:column>	
				<t:column></t:column>
							
			</h:panelGrid>
			*/%>
			
						
			
			<h:panelGrid columns="4" width="100%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>	</h:column>
				<h:column>	</h:column>
				<h:column>   </h:column>				
				<h:column>
					<h:panelGrid columns="2" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero, columnaTablaNumero " cellspacing="0" cellpadding="4">
						<h:column>
			    		    <t:commandButton value="Nuevo" actionListener="#{refReporteCuposCargaBean.alta}" styleClass="boton"/>
			    	    </h:column>						 
				     	<h:column>
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteCuposCargaBean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteCuposCargaBean.pagina}"></t:saveState>
		<t:saveState value="#{refReporteCuposCargaBean.subItemsNivel1}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteCuposCargaBean.pagina.lastPage!=1 && refReporteCuposCargaBean.pagina.totalElements!=0 && refReporteCuposCargaBean.pagina.numpage != 1 && refReporteCuposCargaBean.mostrarLista}" actionListener="#{refReporteCuposCargaBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteCuposCargaBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteCuposCargaBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteCuposCargaBean.pagina.numpage == 1}" rendered="#{refReporteCuposCargaBean.pagina.lastPage!=1 && refReporteCuposCargaBean.pagina.totalElements!=0  && refReporteCuposCargaBean.pagina.numpage != 1 && refReporteCuposCargaBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteCuposCargaBean.mostrarLista}" value="#{refReporteCuposCargaBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteCuposCargaBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteCuposCargaBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteCuposCargaBean.mostrarLista}" value="#{refReporteCuposCargaBean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteCuposCargaBean.nombreArchivo!='' && refReporteCuposCargaBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteCuposCargaBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteCuposCargaBean.pagina.lastPage!=1 && refReporteCuposCargaBean.pagina.totalElements!=0 && refReporteCuposCargaBean.pagina.numpage != refReporteCuposCargaBean.pagina.lastPage && refReporteCuposCargaBean.mostrarLista}" actionListener="#{refReporteCuposCargaBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteCuposCargaBean.pagina.numpage == refReporteCuposCargaBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteCuposCargaBean.pagina.lastPage!=1 && refReporteCuposCargaBean.pagina.totalElements!=0 && refReporteCuposCargaBean.pagina.numpage != refReporteCuposCargaBean.pagina.lastPage && refReporteCuposCargaBean.mostrarLista}" actionListener="#{refReporteCuposCargaBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteCuposCargaBean.pagina.numpage == refReporteCuposCargaBean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 
		 
		
		<% /* LISTADO */ %>
		
	
		
		<h:panelGrid width="100%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteCuposCargaBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,  columnaTablaTexto, columnaTablaTexto,  columnaTablaTexto,columnaTablaTexto, columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaTexto,columnaTablaCentrada,columnaTablaCentrada"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteCuposCargaBean.mostrarLista}" >	
							 
							 
					<h:column  >
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.cliDescripcion}"/>
					</h:column>			
					
						<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.patente_label}"/>
						</f:facet>
						<h:outputText value="#{item.patente} " />
						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="Agrupado"/>
						</f:facet>
						<h:outputText value="#{item.familiaGrupoArticuloDesc} " />						
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.descripcion_label}"/>
						</f:facet>
						<h:outputText value="#{item.descripcion} " />						
					</h:column>
								
								
						<h:column>
						<f:facet name="header">
							<h:outputText value="Ilimitado"/>
						</f:facet>
						<h:outputText value="#{item.ilimitadoStr} " />						
					</h:column>					
				
						<h:column>
						<f:facet name="header">
							<h:outputText value="Litros Por Carga"/>
						</f:facet>
						<h:outputText value="#{item.ltrCarga}"  >
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText> 
						 
					</h:column>	
					
						<h:column>
						<f:facet name="header">
							<h:outputText value="Litros Por Día"/>
						</f:facet>
						<h:outputText value="#{item.ltrDia}"  >
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						 
					 
					</h:column>	
					
						<h:column>
						<f:facet name="header">
							<h:outputText value="Litros Por Mes"/>
						</f:facet>
						<h:outputText value="#{item.ltrMes}"  >
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						 
						 
					</h:column>	
				 
					
 
						<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.activo_label}"/>
						</f:facet>
						<h:outputText value="#{item.activoStr}"/>
					</h:column>		
					
								
					
					
					  <h:column>
						<f:facet name="header">
							<h:outputText value="Cupo"/>
						</f:facet>					
						
						<t:commandButton rendered="#{item.activo}" styleClass="botonSm" 
									   actionListener="#{refReporteCuposCargaBean.verModificar}" 								  
									   title="Modificiar" value="Modificiar">
									   <f:param name="CodVehiculo" id="CodVehiculo" value="#{item.codVehiculo}"/>
									  									  
									   
						</t:commandButton>
						
					</h:column>	
					
					 <h:column>
						<f:facet name="header">
							<h:outputText value="Cupo"/>
						</f:facet>					
						
						<t:commandButton rendered="#{item.activo}" styleClass="botonSm" 
									   actionListener="#{refReporteCuposCargaBean.verBaja}" 								  
									   title="Eliminar" value="Eliminar">
									   <f:param name="CodVehiculoE" id="CodVehiculoE" value="#{item.codVehiculo}"/>
									   
						</t:commandButton>
						
					</h:column>			 
								
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
		
		
			<h:panelGrid columns="1" width="100%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteCuposCargaBean.volver}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
			
		<t:saveState value="#{refReporteCuposCargaBean.vieneDe}"></t:saveState>
		<t:saveState value="#{refReporteCuposCargaBean.mes}"></t:saveState>
		<t:saveState value="#{refReporteCuposCargaBean.anio}"></t:saveState>
		<t:saveState value="#{refReporteCuposCargaBean.cliente}"></t:saveState>
		<t:saveState value="#{refReporteCuposCargaBean.clientes}"></t:saveState>		
		<t:saveState value="#{refReporteCuposCargaBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteCuposCargaBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteCuposCargaBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteCuposCargaBean.nombreArchivo}"></t:saveState>
				
		</h:column>
	</h:panelGrid>
	
	</h:form>	
	
	
	<% /*Modificar limite de carga*/ %>
	<h:form id="frmFrmModificar" rendered="#{refReporteCuposCargaBean.mostrarFrmModificar}">	
	
	<c:if var="puedeIngresarS" test="${!refReporteCuposCargaBean.puedeIngresar}">		
		    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	</c:if> 	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>   		
				
		 <t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_cupos_modificar}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="2" columnClasses="cpo8-Var20, cpo8" width="100%" rowClasses="filaTablaCabecera">									
				
				<t:column/>
				<t:column/>
				<%/*  nombre  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.vehiculo_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<t:inputText id="dominioC" value ="#{refReporteCuposCargaBean.dominio}" maxlength="20" readonly="true" size="20" styleClass="campo" ></t:inputText>		
	 			</t:column>
										
				 
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.art_fam_grupo_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText id="artFamGrupoC" value ="#{refReporteCuposCargaBean.artFamGrupo}" maxlength="20"  size="20" readonly="true" styleClass="campo" ></t:inputText>		
	 			</t:column> 
			
				 
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.descripcion_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="articuloC" value ="#{refReporteCuposCargaBean.articulo}" maxlength="20"  size="30" readonly="true" styleClass="campo"   /> 
				</t:column> 
				
				
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;Ilimitado" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					 <h:selectBooleanCheckbox id="ilimitadoC" value ="#{refReporteCuposCargaBean.ilimitado}"  /> 
				</t:column> 
			 
				
				<%/*  grupo de unidad de negocio  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;Litros por Carga" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="ltrCargaC" value ="#{refReporteCuposCargaBean.ltrCarga}" maxlength="20"  size="20" styleClass="campo" onkeyup="mascaraVolumen(this)" onkeypress="return formatoDecimalesComision(event,1);"  />
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/> 
				    <h:outputText value="&nbsp;[*]" styleClass="datoObligatorio" escape="false"/> 
			    </t:column>
				
				 
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;Litros por Día" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="ltrDiaC" value ="#{refReporteCuposCargaBean.ltrDia}" maxlength="20"  size="20" styleClass="campo" onkeyup="mascaraVolumen(this)" onkeypress="return formatoDecimalesComision(event,2);"   />
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/> 
					<h:outputText value="&nbsp;[*]" styleClass="datoObligatorio" escape="false"/> 	
				</t:column>	
				
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;Litros por Mes" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="ltrMesC" value ="#{refReporteCuposCargaBean.ltrMes}" maxlength="20"  size="20" styleClass="campo"  onkeyup="mascaraVolumen(this)" onkeypress="return formatoDecimalesComision(event,3);"  />
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/> 	
					<h:outputText value="&nbsp;[*]" styleClass="datoObligatorio" escape="false"/> 			
				</t:column>	
				
				
				<t:column/>
				<t:column/>
				
				</t:panelGrid>
				
				<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="1" columnClasses="  cpo8" width="100%" rowClasses="filaTablaCabecera">									
	
				<t:column>	
				<t:panelGrid border="0" cellpadding="" cellspacing="0" styleClass="columnaTablaTexto" 
						columns="1" columnClasses="cpo11-75" width="100%" >							
						<t:column>
							<t:div styleClass="cpo11-75">
								<t:outputText id="datosOblig" value="&nbsp;&nbsp;#{Message.datos_obligatorios_texto}"  styleClass="datoObligatorio" escape="false"/>
						 	</t:div>
					    </t:column>	
						
						<t:column>
							 
							<t:div styleClass="cpo11-75"> 
								<t:outputText id="datosOblig2" value="&nbsp;&nbsp;Solo se permite el punto  como separador decimal [*]	"  styleClass="datoObligatorio" escape="false"/> 
							</t:div>
						</t:column>										
				</t:panelGrid>
				</t:column>	
				 
			</t:panelGrid>		
			
				<t:saveState value="#{refReporteCuposCargaBean.dominio}"></t:saveState>
				<t:saveState value="#{refReporteCuposCargaBean.articulo}"></t:saveState>	
				<t:saveState value="#{refReporteCuposCargaBean.artFamGrupo}"></t:saveState>
				<t:saveState value="#{refReporteCuposCargaBean.artFamGrupoLetra}"></t:saveState>	
				<t:saveState value="#{refReporteCuposCargaBean.ltrMes}"></t:saveState>	
				<t:saveState value="#{refReporteCuposCargaBean.ltrDia}"></t:saveState>	
				<t:saveState value="#{refReporteCuposCargaBean.ltrCarga}"></t:saveState> 
				<t:saveState value="#{refReporteCuposCargaBean.ilimitado}"></t:saveState>
				<t:saveState value="#{refReporteCuposCargaBean.codVehiculo}"></t:saveState> 
				<t:saveState value="#{refReporteCuposCargaBean.codProducto}"></t:saveState>
		
		
		</h:column>
	</h:panelGrid>
	 
				<h:panelGrid columns="2" width="30%" columnClasses="columnaTablaNumero,columnaTablaNumero "  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
			     	<h:column>				    		
			    		<t:commandButton value="#{Message.volver_label}" actionListener="#{refReporteCuposCargaBean.volverPrincipal}" styleClass="boton"/>	
			    	</h:column>
			    	<h:column>	
			    	 <h:commandButton  actionListener="#{refReporteCuposCargaBean.guardarModificacion}" 
						value="#{Message.guardar_label}" styleClass="boton" />
					 </h:column>
			 	</h:panelGrid> 
	</h:form>	
	
	
	
	<% /*baja limite de carga*/ %>
	<h:form id="frmFrmBaja" rendered="#{refReporteCuposCargaBean.mostrarFrmBaja}">	
	
	<c:if var="puedeIngresarS" test="${!refReporteCuposCargaBean.puedeIngresar}">		
		    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	</c:if> 	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>   		
				
		 <t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_cupos_baja}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<t:panelGrid border="0" cellpadding="4" cellspacing="0" styleClass="bordeblanco" 
				columns="2" columnClasses="cpo8-Var20, cpo8" width="100%" rowClasses="filaTablaCabecera">									
				
				<t:column/>
				<t:column/>
				<%/*  nombre  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.vehiculo_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<t:inputText id="dominioe" value ="#{refReporteCuposCargaBean.dominio}" maxlength="20" readonly="true" size="20" styleClass="campo" ></t:inputText>		
	 			</t:column>
										
				 
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.art_fam_grupo_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText id="artFamGrupoe" value ="#{refReporteCuposCargaBean.artFamGrupo}" maxlength="20"  size="20" readonly="true" styleClass="campo" ></t:inputText>		
	 			</t:column> 
			
				 
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.descripcion_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="articuloe" value ="#{refReporteCuposCargaBean.articulo}" maxlength="20"  size="30" readonly="true" styleClass="campo"   /> 
				</t:column> 
				
				
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;Ilimitado" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					 <h:selectBooleanCheckbox id="ilimitadoe" value ="#{refReporteCuposCargaBean.ilimitado}" disabled="true"  /> 
				</t:column> 
			 
				
				<%/*  grupo de unidad de negocio  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;Litros por Carga" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="ltrCargae" value ="#{refReporteCuposCargaBean.ltrCarga}" maxlength="20"  size="20" styleClass="campo" readonly="true"   />
				 
			    </t:column>
				
				 
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;Litros por Día" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="ltrDiae" value ="#{refReporteCuposCargaBean.ltrDia}" maxlength="20"  size="20" styleClass="campo"  readonly="true"  />
				 				
				</t:column>	
				
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;Litros por Mes" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="ltrMese" value ="#{refReporteCuposCargaBean.ltrMes}" maxlength="20"  size="20" styleClass="campo" readonly="true"   />
				 				
				</t:column>	
				
				
			 
				 
			</t:panelGrid>		
			
				<t:saveState value="#{refReporteCuposCargaBean.dominio}"></t:saveState>
				<t:saveState value="#{refReporteCuposCargaBean.articulo}"></t:saveState>	
				<t:saveState value="#{refReporteCuposCargaBean.artFamGrupo}"></t:saveState>
				<t:saveState value="#{refReporteCuposCargaBean.artFamGrupoLetra}"></t:saveState>	
				<t:saveState value="#{refReporteCuposCargaBean.ltrMes}"></t:saveState>	
				<t:saveState value="#{refReporteCuposCargaBean.ltrDia}"></t:saveState>	
				<t:saveState value="#{refReporteCuposCargaBean.ltrCarga}"></t:saveState> 
				<t:saveState value="#{refReporteCuposCargaBean.ilimitado}"></t:saveState>
				<t:saveState value="#{refReporteCuposCargaBean.codVehiculo}"></t:saveState> 
				<t:saveState value="#{refReporteCuposCargaBean.codProducto}"></t:saveState>
		
		</h:column>
	</h:panelGrid>
	 
				<h:panelGrid columns="2" width="30%" columnClasses="columnaTablaNumero,columnaTablaNumero "  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
			     	<h:column>				    		
			    		<t:commandButton value="#{Message.volver_label}" actionListener="#{refReporteCuposCargaBean.volverPrincipal}" styleClass="boton"/>	
			    	</h:column>
			    	<h:column>	
			    	 <h:commandButton  actionListener="#{refReporteCuposCargaBean.guardarBaja}" 
						value="Eliminar" styleClass="boton" />	
					 </h:column>
			 	</h:panelGrid> 
			
	</h:form>	
	
	
	<% /*Modificar limite de carga*/ %>
	<h:form id="frmAlta" rendered="#{refReporteCuposCargaBean.mostrarFrmAlta}">	
	
	<c:if var="puedeIngresarS" test="${!refReporteCuposCargaBean.puedeIngresar}">		
		    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	</c:if> 	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>   		
				
		 <t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_cupos_alta}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="2" columnClasses="cpo8-Var20, cpo8" width="100%" rowClasses="filaTablaCabecera">									
				
				<t:column/>
				<t:column/>
				<%/*  nombre  */%>
				<t:column>							
					<t:outputLabel  styleClass="datoNegrita">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.vehiculo_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				<t:column>
					<t:inputText id="dominioC" value ="#{refReporteCuposCargaBean.dominio}" maxlength="20" readonly="true" size="20" styleClass="campo" ></t:inputText>		
	 			</t:column>
										
				 
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.art_fam_grupo_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText id="artFamGrupoC" value ="#{refReporteCuposCargaBean.artFamGrupo}" maxlength="20"  size="20" readonly="true" styleClass="campo" ></t:inputText>		
	 			</t:column> 
			
				 
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;#{Message.descripcion_label}" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="articuloC" value ="#{refReporteCuposCargaBean.articulo}" maxlength="20"  size="30" readonly="true" styleClass="campo"   /> 
				</t:column> 
				
				
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;Ilimitado" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					 <h:selectBooleanCheckbox id="ilimitadoC" value ="#{refReporteCuposCargaBean.ilimitado}"  /> 
				</t:column> 
			 
				
				<%/*  grupo de unidad de negocio  */%>
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;Litros por Carga" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="ltrCargaC" value ="#{refReporteCuposCargaBean.ltrCarga}" maxlength="20"  size="20" styleClass="campo" onkeyup="mascaraVolumen(this)" onkeypress="return formatoDecimalesComision(event,1);"  />
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/> 
				    <h:outputText value="&nbsp;[*]" styleClass="datoObligatorio" escape="false"/> 
			    </t:column>
				
				 
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;Litros por Día" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="ltrDiaC" value ="#{refReporteCuposCargaBean.ltrDia}" maxlength="20"  size="20" styleClass="campo" onkeyup="mascaraVolumen(this)" onkeypress="return formatoDecimalesComision(event,2);"   />
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/> 
					<h:outputText value="&nbsp;[*]" styleClass="datoObligatorio" escape="false"/> 	
				</t:column>	
				
				<t:column>
					<t:outputLabel styleClass="dato">
						<t:outputText  value="&nbsp;&nbsp;Litros por Mes" escape="false" />
					</t:outputLabel>
				</t:column>
				<t:column>
					<t:inputText  id="ltrMesC" value ="#{refReporteCuposCargaBean.ltrMes}" maxlength="20"  size="20" styleClass="campo"  onkeyup="mascaraVolumen(this)" onkeypress="return formatoDecimalesComision(event,3);"  />
					<h:outputText value="&nbsp;[x]" styleClass="datoObligatorio" escape="false"/> 	
					<h:outputText value="&nbsp;[*]" styleClass="datoObligatorio" escape="false"/> 			
				</t:column>	
				
				
				<t:column/>
				<t:column/>
				
				</t:panelGrid>
				
				<t:panelGrid border="0" cellpadding="4" cellspacing="0"  
				columns="1" columnClasses="  cpo8" width="100%" rowClasses="filaTablaCabecera">									
	
				<t:column>	
				<t:panelGrid border="0" cellpadding="" cellspacing="0" styleClass="columnaTablaTexto" 
						columns="1" columnClasses="cpo11-75" width="100%" >							
						<t:column>
							<t:div styleClass="cpo11-75">
								<t:outputText id="datosOblig" value="&nbsp;&nbsp;#{Message.datos_obligatorios_texto}"  styleClass="datoObligatorio" escape="false"/>
						 	</t:div>
					    </t:column>	
						
						<t:column>
							 
							<t:div styleClass="cpo11-75"> 
								<t:outputText id="datosOblig2" value="&nbsp;&nbsp;Solo se permite el punto  como separador decimal [*]	"  styleClass="datoObligatorio" escape="false"/> 
							</t:div>
						</t:column>										
				</t:panelGrid>
				</t:column>	
				 
			</t:panelGrid>		
			
				<t:saveState value="#{refReporteCuposCargaBean.dominio}"></t:saveState>
				<t:saveState value="#{refReporteCuposCargaBean.articulo}"></t:saveState>	
				<t:saveState value="#{refReporteCuposCargaBean.artFamGrupo}"></t:saveState>
				<t:saveState value="#{refReporteCuposCargaBean.artFamGrupoLetra}"></t:saveState>	
				<t:saveState value="#{refReporteCuposCargaBean.ltrMes}"></t:saveState>	
				<t:saveState value="#{refReporteCuposCargaBean.ltrDia}"></t:saveState>	
				<t:saveState value="#{refReporteCuposCargaBean.ltrCarga}"></t:saveState> 
				<t:saveState value="#{refReporteCuposCargaBean.ilimitado}"></t:saveState>
				<t:saveState value="#{refReporteCuposCargaBean.codVehiculo}"></t:saveState> 
				<t:saveState value="#{refReporteCuposCargaBean.codProducto}"></t:saveState>
		
		
		</h:column>
	</h:panelGrid>
	 
				<h:panelGrid columns="2" width="30%" columnClasses="columnaTablaNumero,columnaTablaNumero "  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
			     	<h:column>				    		
			    		<t:commandButton value="#{Message.volver_label}" actionListener="#{refReporteCuposCargaBean.volverPrincipal}" styleClass="boton"/>	
			    	</h:column>
			    	<h:column>	
			    	 <h:commandButton  actionListener="#{refReporteCuposCargaBean.guardarModificacion}" 
						value="#{Message.guardar_label}" styleClass="boton" />
					 </h:column>
			 	</h:panelGrid> 
	</h:form>	
	
	
	
	<% /*LISTADO DE REMITOS*/ %>
	<h:form id="frmFiltroCuentaSecundario" rendered="#{refReporteCuposCargaBean.mostrarFrmListaSecundaria}">	
	
	<c:if var="puedeIngresarS" test="${!refReporteCuposCargaBean.puedeIngresar}">		
		    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	</c:if> 	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>   		
				
		 <t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_cupos_remitos}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION 	 */%>
		
		 
		 <t:saveState value="#{refReporteCuposCargaBean.pagina}"></t:saveState>
		 <t:saveState value="#{refReporteCuposCargaBean.paginaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteCuposCargaBean.mostrarLista}"></t:saveState>
		 <t:saveState value="#{refReporteCuposCargaBean.mostrarListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteCuposCargaBean.mostrarFrmLista}"></t:saveState>
		 <t:saveState value="#{refReporteCuposCargaBean.mostrarFrmListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteCuposCargaBean.nombreArchivoSecundario}"></t:saveState>
		 
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteCuposCargaBean.paginaSecundaria.lastPage!=1 && refReporteCuposCargaBean.paginaSecundaria.totalElements!=0 && refReporteCuposCargaBean.paginaSecundaria.numpage != 1 && refReporteCuposCargaBean.mostrarListaSecundaria}" actionListener="#{refReporteCuposCargaBean.primeraSecundaria}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteCuposCargaBean.paginaSecundaria.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteCuposCargaBean.anteriorSecundaria}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteCuposCargaBean.paginaSecundaria.numpage == 1}" rendered="#{refReporteCuposCargaBean.paginaSecundaria.lastPage!=1 && refReporteCuposCargaBean.paginaSecundaria.totalElements!=0  && refReporteCuposCargaBean.paginaSecundaria.numpage != 1 && refReporteCuposCargaBean.mostrarListaSecundaria}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteCuposCargaBean.mostrarListaSecundaria}" value="#{refReporteCuposCargaBean.paginaSecundaria.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteCuposCargaBean.paginaSecundaria.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteCuposCargaBean.mostrarListaSecundaria}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteCuposCargaBean.mostrarListaSecundaria}" value="#{refReporteCuposCargaBean.paginaSecundaria.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteCuposCargaBean.nombreArchivoSecundario!='' && refReporteCuposCargaBean.mostrarListaSecundaria}" target="_blank" styleClass="cpo7b" value="#{refReporteCuposCargaBean.nombreArchivoSecundario}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteCuposCargaBean.paginaSecundaria.lastPage!=1 && refReporteCuposCargaBean.paginaSecundaria.totalElements!=0 && refReporteCuposCargaBean.paginaSecundaria.numpage != refReporteCuposCargaBean.paginaSecundaria.lastPage && refReporteCuposCargaBean.mostrarListaSecundaria}" actionListener="#{refReporteCuposCargaBean.siguienteSecundaria}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteCuposCargaBean.paginaSecundaria.numpage == refReporteCuposCargaBean.paginaSecundaria.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteCuposCargaBean.paginaSecundaria.lastPage!=1 && refReporteCuposCargaBean.paginaSecundaria.totalElements!=0 && refReporteCuposCargaBean.paginaSecundaria.numpage != refReporteCuposCargaBean.paginaSecundaria.lastPage && refReporteCuposCargaBean.mostrarListaSecundaria}" actionListener="#{refReporteCuposCargaBean.ultimaSecundaria}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteCuposCargaBean.paginaSecundaria.numpage == refReporteCuposCargaBean.paginaSecundaria.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
	
		 
		
		<% /* LISTADO */ %>
		
		<h:panelGrid width="80%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteCuposCargaBean.subItemsNivel2}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto, columnaTablaTexto,  columnaTablaNumero,columnaTablaNumero, columnaTablaTexto, columnaTablaNumero,columnaTablaNumero,columnaTablaTexto,columnaTablaTexto"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteCuposCargaBean.mostrarListaSecundaria}" >				
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_label}"/>
						</f:facet>
						<h:outputText value="#{item.fecha} " >						
							<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
						</h:outputText>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.hora_label}"/>
						</f:facet>
						<h:outputText value="#{item.hora}" >						
						</h:outputText>
					</h:column>
										
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.sucursal_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroSucursal} " />
						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_remito_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroRemito}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.producto_label}"/>
						</f:facet>
						<h:outputText value="#{item.descProducto}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.total_pesos_label}"/>
						</f:facet>
						<h:outputText value="#{item.montoTotal}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>		
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.litros_label}"/>
						</f:facet>
						<h:outputText value="#{item.litros}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.patente_label}"/>
						</f:facet>
						<h:outputText value="#{item.patente}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.chofer_label}"/>
						</f:facet>
						<h:outputText value="#{item.apellidoChofer}&nbsp; #{item.nombreChofer}" escape="false"/>
					</h:column>
					
					
								
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
		
		
			<h:panelGrid columns="1" width="100%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" actionListener="#{refReporteCuposCargaBean.volverPrincipal}" styleClass="boton"/>	
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
						