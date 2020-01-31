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
		var ventanaCalendario = null;   
		
		function doCalendario4(nom,control) 
		{
		  	addCalendar(nom, "Seleccione Fecha", control, "frmFiltroCuenta");
		  	ventanaCalendario = showCal(nom);
		}
		
		function doCalendario5(nom,control) 
		{
		  	addCalendar(nom, "Seleccione Fecha", control, "frmFiltroCuenta");
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
		</script>
		
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0">
		<%@ include file="../header.jsp" %>
		
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteExclusionPorClienteBean.mostrarFrmLista}">	
		<t:saveState value="#{refReporteExclusionPorClienteBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteExclusionPorClienteBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_exclusion_por_cliente}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>		
				
		<%/*FILTROS*/%>		
			<h:panelGrid columns="4" width="90%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
				</h:column>	
				<h:column></h:column>
				<h:column></h:column>
				<h:column></h:column>									
			</h:panelGrid>
			
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8-30b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<%/* CLIENTE
				*/ %>
				<t:column>							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.cliente_label}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>
				<t:column>
					<h:selectOneListbox id="cliente" size="1" value="#{refReporteExclusionPorClienteBean.cliente}" styleClass="campo"  >
						<f:selectItems value="#{refReporteExclusionPorClienteBean.clientes}" />
					</h:selectOneListbox>						
				</t:column>	
				
				<%/* TIPO IMPUESTO
				*/ %>
				<t:column>							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.impuesto_label}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>
				<t:column>
					<h:selectOneListbox id="tipoex" size="1" value="#{refReporteExclusionPorClienteBean.tipoExclusion}" styleClass="campo"  >
						<f:selectItems value="#{refReporteExclusionPorClienteBean.tiposExclusion}" />
					</h:selectOneListbox>						
				</t:column>	
				
				<%/* PORCENTAJE DE EXCLUSION
				*/%>
				<t:column>
					<h:outputLabel for="porEx" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.porcentaje_exclusion_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="porExcl" value="#{refReporteExclusionPorClienteBean.porcentajeExclusion}"  size="12" maxlength="10" styleClass="campo">						
					</h:inputText>							
				</t:column>	
				
				
				<%/* PROVINCIA
				*/ %>
				<t:column>
					<h:outputLabel for="prov" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.provincia_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>				
				<t:column>	
					<h:selectOneListbox id="prov" size="1" value="#{refReporteExclusionPorClienteBean.provincia}" styleClass="campo"  >
						<f:selectItems value="#{refReporteExclusionPorClienteBean.provincias}" />
					</h:selectOneListbox>				
				</t:column>	
			</h:panelGrid>				
			
			
			<%/* PERIODO DE VIGENCIA DE EXCLUSION
			*/ %>
			<h:panelGrid columns="1" width="90%" columnClasses="cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputText styleClass="campoNoEditable" value="&nbsp;&nbsp;&nbsp;#{Message.comparando_periodo_vigencia_exclusion_label}" escape="false"/>
				</t:column>				
			</h:panelGrid>	
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8-30b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.entre_fechas_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="fechaDesde" value="#{refReporteExclusionPorClienteBean.fltFechaDesde}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
						<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
					</h:inputText>	
					<h:outputText value="&nbsp;" escape="false"></h:outputText>					
					<h:outputLink value="javascript:doCalendario4('Calendar4','frmFiltroCuenta:fechaDesde');">
					<h:graphicImage style="border: none; vertical-align: middle;" value="/img/calendario.gif" width="25px" height="20"/>
					</h:outputLink>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					<t:outputText value="(#{Message.fecha_mascara_label})"  styleClass="campoNoEditable"/>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					
					
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.al_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>							
					
					<h:inputText id="fechaHasta" value="#{refReporteExclusionPorClienteBean.fltFechaHasta}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
						<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
					</h:inputText>	
					<h:outputText value="&nbsp;" escape="false"></h:outputText>					
					<h:outputLink value="javascript:doCalendario5('Calendar5','frmFiltroCuenta:fechaHasta');">
					<h:graphicImage style="border: none; vertical-align: middle;" value="/img/calendario.gif" width="25px" height="20"/>
					</h:outputLink>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
					<t:outputText value="(#{Message.fecha_mascara_label})"  styleClass="campoNoEditable"/>
					<h:outputText value="&nbsp;" escape="false"></h:outputText>	
							
					
				</t:column>		
			</h:panelGrid>
			
				
			
			<h:panelGrid columns="4" width="90%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>	</h:column>	
				<h:column>	</h:column>
						
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0">						 
				     	<h:column>				     	
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteExclusionPorClienteBean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteExclusionPorClienteBean.pagina}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteExclusionPorClienteBean.pagina.lastPage!=1 && refReporteExclusionPorClienteBean.pagina.totalElements!=0 && refReporteExclusionPorClienteBean.pagina.numpage != 1 && refReporteExclusionPorClienteBean.mostrarLista}" actionListener="#{refReporteExclusionPorClienteBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteExclusionPorClienteBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteExclusionPorClienteBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteExclusionPorClienteBean.pagina.numpage == 1}" rendered="#{refReporteExclusionPorClienteBean.pagina.lastPage!=1 && refReporteExclusionPorClienteBean.pagina.totalElements!=0  && refReporteExclusionPorClienteBean.pagina.numpage != 1 && refReporteExclusionPorClienteBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteExclusionPorClienteBean.mostrarLista}" value="#{refReporteExclusionPorClienteBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteExclusionPorClienteBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteExclusionPorClienteBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteExclusionPorClienteBean.mostrarLista}" value="#{refReporteExclusionPorClienteBean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteExclusionPorClienteBean.nombreArchivo!='' && refReporteExclusionPorClienteBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteExclusionPorClienteBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteExclusionPorClienteBean.pagina.lastPage!=1 && refReporteExclusionPorClienteBean.pagina.totalElements!=0 && refReporteExclusionPorClienteBean.pagina.numpage != refReporteExclusionPorClienteBean.pagina.lastPage && refReporteExclusionPorClienteBean.mostrarLista}" actionListener="#{refReporteExclusionPorClienteBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteExclusionPorClienteBean.pagina.numpage == refReporteExclusionPorClienteBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteExclusionPorClienteBean.pagina.lastPage!=1 && refReporteExclusionPorClienteBean.pagina.totalElements!=0 && refReporteExclusionPorClienteBean.pagina.numpage != refReporteExclusionPorClienteBean.pagina.lastPage && refReporteExclusionPorClienteBean.mostrarLista}" actionListener="#{refReporteExclusionPorClienteBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteExclusionPorClienteBean.pagina.numpage == refReporteExclusionPorClienteBean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 <t:saveState value="#{refReporteExclusionPorClienteBean.ccss}"></t:saveState>
		 <t:saveState value="#{refReporteExclusionPorClienteBean.lstccss}"></t:saveState>
		<t:saveState value="#{refReporteExclusionPorClienteBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteExclusionPorClienteBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteExclusionPorClienteBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteExclusionPorClienteBean.nombreArchivo}"></t:saveState>
		
		<% /* LISTADO 
		<t:div  style="overflow:scroll; height:100%; width:700px;"  rendered="#{refReporteExclusionPorClienteBean.mostrarLista}">  
		*/ %>
		<h:panelGrid width="95%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteExclusionPorClienteBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaNumero, columnaTablaTexto"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0" 
							 rendered="#{refReporteExclusionPorClienteBean.mostrarLista}" >
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_vigencia_desde_label}"/>
						</f:facet>								
						<h:outputText value="#{item.fechaDesde}"  escape="false"/>				
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_vigencia_hasta_label}"/>
						</f:facet>								
						<h:outputText value="#{item.fechaHasta}"  escape="false"/>				
					</h:column>								
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.cliDescripcion}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cod_cliente_Alfa}"/>
						</f:facet>
						<h:outputText value="#{item.codClienteAlfa}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.impuesto_label}"/>
						</f:facet>
						<h:outputText value="#{item.tipoExcDescripcion}"/>
					</h:column>				
						
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.porcentaje_exclusion_label}"/>
						</f:facet>
						<h:outputText value="#{item.porcentajeExclusion}" escape="false">						
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.provincia_label}"/>
						</f:facet>
						<h:outputText value="#{item.provDescripcion}"/>
					</h:column>	
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
				<%/*
		</t:div>
				*/ %>
			<h:panelGrid columns="1" width="80%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteExclusionPorClienteBean.volver}" styleClass="boton"/>	
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
						