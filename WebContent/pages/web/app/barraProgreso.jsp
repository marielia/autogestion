<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<f:loadBundle basename="com.refinor.extranet.bundle.Messages" var="Message"/>
<f:view>
<html>
<head>
<title>Formulario</title>
<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/prototype.js"></script>

<style>
div {
height: 50px;
width: 50px;
}
img{
height: 40px;
width: 42px;
border: 0;
}
</style>

<script language="javascript">

function cambioFondo(){
 

if ($("divHtml1").style['display']==''){
	$("divHtml1").style['display']='none';
	$("divHtml2").style['display']='';
	$("divHtml3").style['display']='none';
	$("divHtml4").style['display']='none';
	$("divHtml5").style['display']='none';
	$("divHtml6").style['display']='none';
	$("divHtml7").style['display']='none';
	$("divHtml8").style['display']='none';
	$("divHtml9").style['display']='none';
	return false;
}
 if ($("divHtml2").style['display']==''){  
	 $("divHtml1").style['display']='none';
	$("divHtml2").style['display']='none';
	$("divHtml3").style['display']='';
	$("divHtml4").style['display']='none';
	$("divHtml5").style['display']='none';
	$("divHtml6").style['display']='none';
	$("divHtml7").style['display']='none';
	$("divHtml8").style['display']='none';
	$("divHtml9").style['display']='none';
	return false;
  }
 if ($("divHtml3").style['display']==''){  
	 $("divHtml1").style['display']='none';
	$("divHtml2").style['display']='none';
	$("divHtml3").style['display']='none';
	$("divHtml4").style['display']='';
	$("divHtml5").style['display']='none';
	$("divHtml6").style['display']='none';
	$("divHtml7").style['display']='none';
	$("divHtml8").style['display']='none';
	$("divHtml9").style['display']='none';
	return false;
  } 
  
  if ($("divHtml4").style['display']==''){  
	 $("divHtml1").style['display']='none';
	$("divHtml2").style['display']='none';
	$("divHtml3").style['display']='none';
	$("divHtml4").style['display']='none';
	$("divHtml5").style['display']='';
	$("divHtml6").style['display']='none';
	$("divHtml7").style['display']='none';
	$("divHtml8").style['display']='none';
	$("divHtml9").style['display']='none';
	return false;
  }
  
   if ($("divHtml5").style['display']==''){  
	 $("divHtml1").style['display']='none';
	$("divHtml2").style['display']='none';
	$("divHtml3").style['display']='none';
	$("divHtml4").style['display']='none';
	$("divHtml5").style['display']='none';
	$("divHtml6").style['display']='';
	$("divHtml7").style['display']='none';
	$("divHtml8").style['display']='none';
	$("divHtml9").style['display']='none';
	return false;
  }
  
   if ($("divHtml6").style['display']==''){  
	 $("divHtml1").style['display']='none';
	$("divHtml2").style['display']='none';
	$("divHtml3").style['display']='none';
	$("divHtml4").style['display']='none';
	$("divHtml5").style['display']='none';
	$("divHtml6").style['display']='none';
	$("divHtml7").style['display']='';
	$("divHtml8").style['display']='none';
	$("divHtml9").style['display']='none';
	return false;
  }
  
     if ($("divHtml7").style['display']==''){  
	 $("divHtml1").style['display']='none';
	$("divHtml2").style['display']='none';
	$("divHtml3").style['display']='none';
	$("divHtml4").style['display']='none';
	$("divHtml5").style['display']='none';
	$("divHtml6").style['display']='none';
	$("divHtml7").style['display']='none';
	$("divHtml8").style['display']='';
	$("divHtml9").style['display']='none';
	return false;
  }
  
     if ($("divHtml8").style['display']==''){  
	 $("divHtml1").style['display']='none';
	$("divHtml2").style['display']='none';
	$("divHtml3").style['display']='none';
	$("divHtml4").style['display']='none';
	$("divHtml5").style['display']='none';
	$("divHtml6").style['display']='none';
	$("divHtml7").style['display']='none';
	$("divHtml8").style['display']='none';
	$("divHtml9").style['display']='';
	return false;
  }
  
     if ($("divHtml9").style['display']==''){  
	 $("divHtml1").style['display']='';
	$("divHtml2").style['display']='none';
	$("divHtml3").style['display']='none';
	$("divHtml4").style['display']='none';
	$("divHtml5").style['display']='none';
	$("divHtml6").style['display']='none';
	$("divHtml7").style['display']='none';
	$("divHtml8").style['display']='none';
	$("divHtml9").style['display']='none';
	return false;
  }
  
  

} 



</script>
</head>


<body onLoad="setInterval(cambioFondo,200)">

<div id="divHtml1" ><img SRC="/refipass/img/circulo1.gif" /> </div>
<div id="divHtml2" ><img SRC="/refipass/img/circulo2.gif" /> </div>
<div id="divHtml3" ><img SRC="/refipass/img/circulo3.gif" /> </div>
<div id="divHtml4" ><img SRC="/refipass/img/circulo4.gif" /> </div>
<div id="divHtml5" ><img SRC="/refipass/img/circulo5.gif" /> </div>
<div id="divHtml6" ><img SRC="/refipass/img/circulo6.gif" /> </div>
<div id="divHtml7" ><img SRC="/refipass/img/circulo7.gif" /> </div>
<div id="divHtml8" ><img SRC="/refipass/img/circulo8.gif" /> </div>
<div id="divHtml9" ><img SRC="/refipass/img/circulo9.gif" /> </div>

</body>
</html>
</f:view>