


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
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteLibroDiarioBean.mostrarFrmLista}" >	
		<t:saveState value="#{refReporteLibroDiarioBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteLibroDiarioBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
   		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.listado_libro_diario_label}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
				
		<%/*FILTROS*/%>
		
			<h:panelGrid columns="4" width="80%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
				</h:column>	
				<h:column></h:column>
				<h:column></h:column>
				<h:column></h:column>									
			</h:panelGrid>
			
			<h:panelGrid columns="2" width="80%" columnClasses="cpo8-25b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.entre_fechas_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="fechaDesde" value="#{refReporteLibroDiarioBean.fltFechaDesde}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
					
					<h:inputText id="fechaHasta" value="#{refReporteLibroDiarioBean.fltFechaHasta}" onkeypress="javascript:soloFecha(event);" onblur="javascript:validarFecha(this);" size="12" maxlength="10" styleClass="campo">						
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
					
			
			<h:panelGrid columns="3" width="80%" columnClasses="cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
				   		<t:commandButton value="#{Message.generar_archivo_definitiva_label}" actionListener="#{refReporteLibroDiarioBean.buscarDefinitivo}" styleClass="botonV2">
				    	</t:commandButton>				    	
			 	</h:column>	
			 	<h:column>
					 		<t:commandButton value="#{Message.generar_hoja_calculo_label}" actionListener="#{refReporteLibroDiarioBean.buscarExcel}" styleClass="boton">
				    		</t:commandButton>				    	
			 	</h:column>		
				<h:column>
					 		<t:commandButton value="#{Message.vista_previa_label}" actionListener="#{refReporteLibroDiarioBean.buscar}" styleClass="boton">
				    		</t:commandButton>				    	
			 	</h:column>				 	
			</h:panelGrid>
			
		<t:panelGrid width="80%" columnClasses="columnaTablaTexto" columns="1" cellspacing="0" cellpadding="0">
		<t:column>
		    <t:div styleClass="cpo7n">
		     <t:graphicImage value="/img/info.ico" alt="#{Message.mensaje_mayor_contable_archivo}" width="10px" height="10px"/>
			 <h:outputText value="&nbsp;#{Message.mensaje_abrir_archivo_texto}" escape="false"/>
			</t:div>
		</t:column>		
		</t:panelGrid>		
				
		
		<t:saveState value="#{refReporteLibroDiarioBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteLibroDiarioBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteLibroDiarioBean.nombreArchivo}"></t:saveState>
		<t:saveState value="#{refReporteLibroDiarioBean.nombreArchivoTxt}"></t:saveState>				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteLibroDiarioBean.pagina}"></t:saveState>
		<t:saveState value="#{refReporteLibroDiarioBean.subItemsNivel1}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>
		   		<%/*<h:commandButton rendered="#{refReporteLibroDiarioBean.pagina.lastPage!=1 && refReporteLibroDiarioBean.pagina.totalElements!=0 && refReporteLibroDiarioBean.pagina.numpage != 1 && refReporteLibroDiarioBean.mostrarLista}" actionListener="#{refReporteLibroDiarioBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteLibroDiarioBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteLibroDiarioBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteLibroDiarioBean.pagina.numpage == 1}" rendered="#{refReporteLibroDiarioBean.pagina.lastPage!=1 && refReporteLibroDiarioBean.pagina.totalElements!=0  && refReporteLibroDiarioBean.pagina.numpage != 1 && refReporteLibroDiarioBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteLibroDiarioBean.mostrarLista}" value="#{refReporteLibroDiarioBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteLibroDiarioBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteLibroDiarioBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteLibroDiarioBean.mostrarLista}" value="#{refReporteLibroDiarioBean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		*/%>
		   		
		   		<h:outputText styleClass="cpo8-link" rendered="#{refReporteLibroDiarioBean.mostrarLista}" value="&nbsp;&nbsp;:.&nbsp;&nbsp;" escape="false"/>	
			
		   		<h:outputLink  rendered="#{refReporteLibroDiarioBean.nombreArchivo!='' && refReporteLibroDiarioBean.mostrarLista}" target="_blank" styleClass="cpo8-link" value="#{refReporteLibroDiarioBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
				
				<h:outputLink  rendered="#{refReporteLibroDiarioBean.nombreArchivoTxt!='' && refReporteLibroDiarioBean.mostrarLista}" target="_blank" styleClass="cpo8-link" value="#{refReporteLibroDiarioBean.nombreArchivoTxt}">
					<h:outputText value="#{Message.descargar_archivo_text_label}" />
				</h:outputLink>	
				
				<h:outputText styleClass="cpo8-link" rendered="#{refReporteLibroDiarioBean.mostrarLista}" value="&nbsp;&nbsp;.:&nbsp;&nbsp;" escape="false"/>	
			
				<%/*
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteLibroDiarioBean.pagina.lastPage!=1 && refReporteLibroDiarioBean.pagina.totalElements!=0 && refReporteLibroDiarioBean.pagina.numpage != refReporteLibroDiarioBean.pagina.lastPage && refReporteLibroDiarioBean.mostrarLista}" actionListener="#{refReporteLibroDiarioBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteLibroDiarioBean.pagina.numpage == refReporteLibroDiarioBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteLibroDiarioBean.pagina.lastPage!=1 && refReporteLibroDiarioBean.pagina.totalElements!=0 && refReporteLibroDiarioBean.pagina.numpage != refReporteLibroDiarioBean.pagina.lastPage && refReporteLibroDiarioBean.mostrarLista}" actionListener="#{refReporteLibroDiarioBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteLibroDiarioBean.pagina.numpage == refReporteLibroDiarioBean.pagina.lastPage}"/>			   		
		   		*/ %>
		 </h:column>		 
		 </h:panelGrid>
		 
		 
		
		<% /* LISTADO */ %>
		<% /*<t:div  style="overflow:scroll; height:100%; width:700px;"   rendered="#{refReporteLibroDiarioBean.mostrarLista}">  
		 */ %>
		<%/*
		<h:panelGrid width="120%" columns="1" columnClasses="bordeblanco" cellspacing="0" cellpadding="0" >						 
		<h:column>		
		
				<t:dataTable value="#{refReporteLibroDiarioBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaNumero,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaNumero,columnaTablaNumero,columnaTablaTexto, columnaTablaNumero, columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaNumero,columnaTablaNumeroFijo,columnaTablaNumeroFijo,columnaTablaNumeroFijo "
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteLibroDiarioBean.mostrarLista}" >
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_libro_diario}"/>
						</f:facet>
						<h:outputText value="#{item.fecha}" >
						 <f:convertDateTime     pattern="dd/mm/yyyy" />
					    </h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.tipo_doc_libro_diario}"/>
						</f:facet>
						<h:outputText value="#{item.tipoComp}" />
						<h:outputText value="&nbsp;" escape="false" />
						<h:outputText value="#{item.letra}" />
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.ccss_label}"/>
						</f:facet>
						<h:outputText value="#{item.ccddId}" />						
					</h:column>	
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_ejercicio_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroEjercicio}" />
						
					</h:column>	
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_asiento_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroAsiento}" />						
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_factuta_libro_diario}"/>
						</f:facet>
						<h:outputText value="#{item.nroSuc}" />	
						<h:outputText value="&nbsp;" escape="false"/>							
						<h:outputText value="#{item.nroComp}" />	
					</h:column>	
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.descripcion_aciento_contable_label}"/>
						</f:facet>
						<h:outputText value="#{item.leyenda}" />						
					</h:column>		
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cuenta_label}"/>
						</f:facet>
						<h:outputText value="#{item.cuenta}" />						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.descripcion_cuenta_label}"/>
						</f:facet>
						<h:outputText value="#{item.cuentaDesc}" />						
					</h:column>							
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.debe_label}"/>
						</f:facet>
						
						<h:outputText value="#{item.valor}" rendered="#{item.debHab=='D'}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						 
						 <h:outputText value="#{item.valorDebe}" rendered="#{item.ultimo==1}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>							
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.haber_label}"/>
						</f:facet>
						<h:outputText value="#{item.valor}" rendered="#{item.debHab=='H'}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						 <h:outputText value="#{item.valorHaber}" rendered="#{item.ultimo==1}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>						
					</h:column>	
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
				*/ %>
				<%/*
			</t:div>
		*/ %>
		
			<h:panelGrid columns="1" width="80%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteLibroDiarioBean.volver}" styleClass="boton"/>	
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
						