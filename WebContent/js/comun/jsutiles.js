/* quita espacios a cadenas 
RSEN::afa*/
function Trim( str ) 
{
var resultStr = "";

resultStr = LTrim(str);
resultStr = RTrim(resultStr);

return resultStr;
}

/* Permite el ingreso de solo numeros
RSEN::24122007::afa*/
function soloNumeros(e) {
var f = document.forms[0];
var tecla = (document.all) ? e.keyCode : e.which;

	if (tecla == 0 || tecla == 8) //para que funcione el TAB y el borrado en Mozilla
		return true;
				
	if (tecla < 48 || tecla > 57)//si el caracter no es numerico, no se retorna nada
	{
  		return false;
	}
	else
	{
		return true;
	}
}

 /*Valida el mes ingresado que sea correcto entre 1 y 12
 Agrega 0 cuando solo se ingresa un digito en el mes
IngRSEN::25122006::afa*/ 
function validarMes(obj){

    cadena = obj.value;    
    if(cadena == ""){
        return false;
    }

    var Mes= new String(cadena);
    
    if (isNaN(Mes) || parseFloat(Mes) < 1 || parseFloat(Mes) > 12 || Mes == ""){
        alert('Mes inv\xE1lido.');        
        obj.value = "";
        obj.focus();
        return false;
    }
       
    if (Mes.length == 1)Mes='0'+Mes;
    obj.value = Mes;
    return true;
}

 /*Valida el anio ingresado que sea correcto entre mayor a 1900
IngRSEN::25122006::afa*/ 
function validarAnio(obj){
    cadena = obj.value;    
    
    if(cadena == ""){
        return false;
    }
    
    var Ano= new String(cadena);
    
    if (isNaN(Ano) || Ano.length<4 || Ano.length>4 || parseFloat(Ano)<1900){
        alert('A\xF1o inv\xE1lido - Formato v\xE1lido: AAAA - El a\xF1o debe ser mayor a 1900');
        obj.value = "";
        obj.focus();
        return false;
    }
   
    return true;
}

function emailCheck (emailStr, fehlermeldung) { 
	var notallowed = " ;:!$%/()=?*"; 
	if (fehlermeldung == false) { var fehlermeldung = "Debe corregir el correo";} 
	var pos = emailStr.lastIndexOf("@"); 
	var pos2 = emailStr.lastIndexOf("."); 
	if ((pos <= 0) || (pos == emailStr.length - 1) || 
	(pos2 <= 0) || (pos2 == emailStr.length - 1) || (pos2 <= pos + 2)) 
	{alert(fehlermeldung); return false;} 
	for (i=0; i < notallowed.length; i++) { 
	var pos = emailStr.indexOf( notallowed.charAt(i) ); 
	if (pos > -1) {alert(fehlermeldung); return false;} 
	} 
	return true; 
} 


function aparece(){
	 if(document.getElementById("frmFiltroCuenta:puntero")!=null){
		  document.getElementById("frmFiltroCuenta:puntero").style["display"] = "";			 		   	     		  
			  
	}
}

function desaparece(){
	if(document.getElementById("frmFiltroCuenta:puntero")!=null){
	    document.getElementById("frmFiltroCuenta:puntero").style["display"] = "none";
	 }		
}