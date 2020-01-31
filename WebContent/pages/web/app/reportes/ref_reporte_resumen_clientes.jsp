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
		function doCalendario6(nom,control) 
		{
		  	addCalendar(nom, "Seleccione Fecha", control, "frmFiltroCuenta");
		  	ventanaCalendario = showCal(nom);
		}
		
		function doCalendario7(nom,control) 
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
		
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteResumenClientesBean.mostrarFrmLista}">	
		<t:saveState value="#{refReporteResumenClientesBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteResumenClientesBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="Resumen De Cuentas Corrientes De Cliente"/>
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
						<h:outputText  value="&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
				</h:column>	
				<h:column></h:column>
				<h:column></h:column>
				<h:column></h:column>									
			</h:panelGrid>
			
			
			<h:panelGrid columns="2" width="95%" columnClasses="cpo8-15,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			
				<t:column >							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.cliente_label}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
				</t:column>
				<t:column >
					<h:selectOneListbox id="cliente" size="1" value="#{refReporteResumenClientesBean.cliente}" styleClass="campo"  >
						<f:selectItems value="#{refReporteResumenClientesBean.clientes}" />
					</h:selectOneListbox>						
				</t:column>	
											
			</h:panelGrid>			
			
			<h:panelGrid columns="4" width="95%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
										
				<h:column>	</h:column>				
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0">						 
				     	<h:column>				     	
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteResumenClientesBean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		<h:panelGrid columns="4" width="95%" cellspacing="0" cellpadding="2">	
			<h:column>
			<t:outputText  value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />	
			</h:column>	
		</h:panelGrid>
				
		<%/*PAGINACION*/ %>	
		<t:saveState value="#{refReporteResumenClientesBean.cliente}"></t:saveState>
		<t:saveState value="#{refReporteResumenClientesBean.clientes}"></t:saveState>
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>		
		 <h:panelGrid width="95%" cellspacing="0" columns="2"  columnClasses="cpo8-15,cpo8Right" cellpadding="4">
		 	<h:column>
			 	<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.cliente_label}:&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		 	</h:column>
	 		<h:column>
				<t:outputText  value="#{refReporteResumenClientesBean.cuenta.codClienteAlfa}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false" />	
				<t:outputText  value="#{refReporteResumenClientesBean.cuenta.cliente}" escape="false" />
		 	</h:column>		 		 
		 </h:panelGrid>
		 
		 
		 <%/*FACTURADO */ %>
		 <h:panelGrid width="95%" cellspacing="3" columns="2"  columnClasses="cpo8,cpo8Right,cpo8-15,cpo8Right,cpo8-15,cpo8Right" cellpadding="2">
		 	<%/*
		 	<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Total Facturado desde Lista de Facturas (relaciones completas):&nbsp;$&nbsp;" escape="false" />
			</h:column>	
		 	<h:column>		 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.totalFacturadoCompleto}" escape="false" />
		 	</h:column>	
		 	
		 	
		 	<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Total Recibo desde la Lista de Recibo (con relaciones):&nbsp;$&nbsp;" escape="false" />
			</h:column>	
		 	<h:column>		 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.totalReciboCompleto}" escape="false" />
		 	</h:column>
		 	
		 	<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Factura - Recibo:&nbsp;$&nbsp;" escape="false" />
			</h:column>	
		 	<h:column>		 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.totalFacturaMenosReciboCompleto}" escape="false" />
		 	</h:column>	
		 	
		 	<h:column>
		 	<t:outputText styleClass="cpo8-15" value="&nbsp;Remitos desde Lista de Remitos no facturados (con relaciones):&nbsp;$&nbsp;" escape="false" />
			</h:column>	
		 	<h:column>		 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.totalRemitoMpedido}" escape="false" />
		 	</h:column>	
		 	*/ %>
		 	<h:column>
		 	<t:outputText styleClass="cpo8-15" value="&nbsp;Saldo por Comprobante ((Factura-Recibo)+(Remito)):&nbsp;$&nbsp;" escape="false" />
			</h:column>	
		 	<h:column>		 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.saldoCompleto}" escape="false" />
		 	</h:column>	
		 	
		 	
		 	<h:column>
		 		<t:outputText styleClass="cpo8-15" value="&nbsp;Límite de Crédito:&nbsp;$&nbsp;" escape="false" />
			</h:column>	
		 	<h:column>		 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.strLimiteCreditoCliente}" escape="false" />
		 	</h:column>	
		 	
		 	<h:column>
		 		<t:outputText styleClass="cpo8-15" value="&nbsp;Disponible (1):&nbsp;$&nbsp;" escape="false" />
			</h:column>	
		 	<h:column>		 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.disponibleCompleto}" escape="false" />
		 	</h:column>	
		 	
		 	<%/*<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Diferencia:&nbsp;$&nbsp;" escape="false" />	
			</h:column>	
		 	<h:column>	 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.difTotalFacturado}" escape="false" />
		 	</h:column>	*/%>	 	
		 	
		 	 </h:panelGrid>
		 	 
		<t:panelGrid width="95%" columnClasses="columnaTablaTexto" columns="1" cellspacing="0" cellpadding="0">
		<t:column>
		    <t:div styleClass="cpo7n">
		     <t:graphicImage value="/img/info.ico" alt="#{Message.mensaje_mayor_contable_archivo}" width="10px" height="10px"/>
			 <h:outputText value="&nbsp;(1) Representa el Disponible en Head Office." escape="false"/>
			</t:div>
		</t:column>		
		</t:panelGrid>
		 	
		 	<h:panelGrid columns="4" width="95%" cellspacing="0" cellpadding="2">	
			<h:column>
			<t:outputText  value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />	
			</h:column>	
			</h:panelGrid>
		 	
		 	<%/* TOTAL DE RECIBOS */ %>
		 		<%/*  <h:panelGrid width="95%" cellspacing="3" columns="2"  columnClasses="cpo8,cpo8Right,cpo8-15,cpo8Right,cpo8-15,cpo8Right" cellpadding="2">
		 	<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Total Facturado desde la tabla MfacturasV (sin relaciones):&nbsp;$&nbsp;" escape="false" />	
			</h:column>	
		 	<h:column>	 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.totalFacturadoDeMFactV}" escape="false" />
		 	</h:column>	 		 
		 		
		 	
		 	<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Total Recibo desde tabla MRecibos (sin relaciones):&nbsp;$&nbsp;" escape="false" />	
			</h:column>	
		 	<h:column>	 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.totalReciboDeMRec}" escape="false" />
		 	</h:column>	
		 	
		 	<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Factura - Recibo:&nbsp;$&nbsp;" escape="false" />	
			</h:column>	
		 	<h:column>	 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.totalFacturaMenosReciboMfacMrec}" escape="false" />
		 	</h:column>			 	
		 	 </h:panelGrid>
		 	
		 	<h:panelGrid columns="4" width="95%" cellspacing="0" cellpadding="2">	
			<h:column>
			<t:outputText  value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />	
			</h:column>	
			</h:panelGrid>*/ %>
		 	
		 	<%/* TOTAL DE RECIBOS */ %>
		 	 <h:panelGrid width="95%" cellspacing="3" columns="2"  columnClasses="cpo8,cpo8Right,cpo8-15,cpo8Right,cpo8-15,cpo8Right" cellpadding="2">
		
		 	<%/*
		 	<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Total Facturado desde la tabla MCtaCte (sin relaciones):&nbsp;$&nbsp;" escape="false" />	
			</h:column>	
		 	<h:column>	 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.totalFacturadoDeMCtaCte}" escape="false" />
		 	</h:column>	
		 	<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Total Recibo desde tabla MCtaCte (sin relaciones):&nbsp;$&nbsp;" escape="false" />	
			</h:column>	
		 	<h:column>	 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.totalReciboMctacte}" escape="false" />
		 	</h:column>
		 	
		 	
		 	<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Factura - Recibo :&nbsp;$&nbsp;" escape="false" />	
			</h:column>	
		 	<h:column>	 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.totalFacturaMenosReciboMCTaCte}" escape="false" />
		 	</h:column>	
		 	
		 	<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Total Remitos desde la tabla MCtasCte (sin relaciones):&nbsp;$&nbsp;" escape="false" />	
			</h:column>	
		 	<h:column>	 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.totalRemitoCtaCte}" escape="false" />
		 	</h:column>	
		 	
		 	<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Saldo ((Factura-Recibo)+(Remito)):&nbsp;$&nbsp;" escape="false" />	
			</h:column>	
		 	<h:column>	 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.saldo}" escape="false" />
		 	</h:column> */ %>
		 		
		 
		 <!-- Saldo por comprobante cliente -->
		     <h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Saldo por comprobante cliente:&nbsp;$&nbsp;" escape="false" />	
			</h:column>	
		 	<h:column>	 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.saldoPorComprobanteCliente}" escape="false" />
		 	</h:column>	
		 	
		 	
		 	<!-- Limite de credito del cliente -->
		 	<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Límite de crédito:&nbsp;$&nbsp;" escape="false" />	
			</h:column>	
		 	<h:column>	 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.strLimiteCreditoCliente}" escape="false" />
		 	</h:column>	
		 			 
		   <!-- Disponible del cliente en CCSS -->	
		 	<h:column>
			 	<t:outputText styleClass="cpo8-15" value="&nbsp;Disponible (2):&nbsp;$&nbsp;" escape="false" />	
			</h:column>	
		 	<h:column>	 						
				<t:outputText styleClass="cpo8Right"  value="#{refReporteResumenClientesBean.cuenta.disponible}" escape="false" />
		 	</h:column>		 
		 		 	
		    	 
		 </h:panelGrid>
		 
		 <t:panelGrid width="95%" columnClasses="columnaTablaTexto" columns="1" cellspacing="0" cellpadding="0">
		<t:column>
		    <t:div styleClass="cpo7n">
		     <t:graphicImage value="/img/info.ico" alt="#{Message.mensaje_mayor_contable_archivo}" width="10px" height="10px"/>
			 <h:outputText value="&nbsp;(2) Representa el Disponible en los CCSS, pero es aproximado dependiente si la información de cta cte replicó correctamente." escape="false"/>
			</t:div>
		</t:column>		
		</t:panelGrid>
		 
		<t:saveState value="#{refReporteResumenClientesBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteResumenClientesBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteResumenClientesBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteResumenClientesBean.nombreArchivo}"></t:saveState>
				
			
			<t:panelGrid>
				<t:column>
					<h:outputText value="&nbsp;" escape="false"/>
				</t:column>				
			</t:panelGrid>	
		
			
				
				
			<h:panelGrid columns="1" width="90%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteResumenClientesBean.volver}" styleClass="boton"/>	
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
						