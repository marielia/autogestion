package com.refinor.extranet.faces.reportes;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.refinor.extranet.faces.base.AbstReporte;
import com.refinor.extranet.seguridad.SeguridadAdmin;

@ManagedBean(name = "refListadoInformesDisponiblesBean")
@RequestScoped
public class RefListadoInformesDisponiblesBean extends AbstReporte {

	private Boolean mostrarFrmLista;
	
	public Boolean getMostrarFrmLista() {
		return mostrarFrmLista;
	}

	public void setMostrarFrmLista(Boolean mostrarFrmLista) {
		this.mostrarFrmLista = mostrarFrmLista;
	}

	public RefListadoInformesDisponiblesBean() {
		mostrarFrmLista = true;
	}

	@Override
	public Boolean getPuedeIngresar() {
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		System.out.println("PUEDE INGRESAR: ");
		System.out.println(SeguridadAdmin.getTienePermiso(1));
		return SeguridadAdmin.getTienePermiso(1);
	}

}
