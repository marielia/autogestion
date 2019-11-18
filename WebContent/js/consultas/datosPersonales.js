function validar() { 
	var email = document.getElementById('frmABMTelefonos:correoElectronico');
	if (email.value != '') {
  		if(emailCheck(email.value, false)) 
			return true;
		else
			email.focus();
			return false;	
		}
	return true;
}