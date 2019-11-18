/*
 * Created on Jun 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.refinor.extranet.seguridad;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;



import com.refinor.extranet.util.Const;

/**
 * @author mdiz
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SeguridadAdmin {	
	
/*	public static final int MENU_COTIZADOR_AUTOS= 				101; 
	public static final int MENU_COTIZADOR_RIESGOS_VARIOS= 			102; 
	public static final int MENU_COTIZADOR_ROBO= 					103; 
	public static final int MENU_COTIZADOR_COMBINADO_FAMILIAR= 		104; 
	public static final int MENU_COTIZADOR_ACCIDENTES_PERSONALES= 	105; 
	public static final int MENU_COTIZADOR_CRISTALES= 				106; 
	public static final int MENU_COTIZADOR_INTEGRAL_DE_COMERCIO= 	107; 
	public static final int MENU_COTIZADOR_RESPONSABILIDAD_CIVIL= 	108; 
	public static final int MENU_COTIZADOR_SEGURO_TECNICO= 			109; 
	public static final int MENU_COTIZADOR_CONVENIO_MERC_POR_EDAD= 	110; 
	public static final int MENU_COTIZADOR_CONVENIO_MERC_POR_GRUPO= 111; 
	public static final int MENU_COTIZADOR_LEYES_LABORALES= 		112; 
	public static final int MENU_COTIZADOR_TRABAJADORES_RURALES= 	113; 
	public static final int MENU_COTIZADOR_VIDA_COLECTIVO_VOLUNTARIO= 114; 
	public static final int MENU_COTIZADOR_SEPELIO = 				115; 
	public static final int MENU_COTIZADOR_PACTO_SOCIAL = 			116; 
	public static final int MENU_COTIZADOR_VIDA_INDIVIDUAL= 		117;
	
	public static final int MENU_CONSULTA_POLIZAS_ENDOSOS= 		201; 
	public static final int MENU_CONSULTA_COBRANZAS= 			202; 
	public static final int MENU_CONSULTA_CUENTA_CORRIENTE= 	203; 	
	public static final int MENU_CONSULTA_PAGOS_A_TERCEROS= 	204; 
	public static final int MENU_CONSULTA_SINIESTROS= 			205; 
	public static final int MENU_CONSULTA_CUPONES_Y_FACTURAS= 	206; 
	
	public static final int MENU_DATOS_PERSONALES= 				301; 
	public static final int MENU_NOVEDADES_DE_PERSONA= 			302; 
	
	public static final int MENU_USUARIOS= 						501; 
	public static final int MENU_ROLES= 						502; 
*/
	
	
	private static boolean verificarLogueo() {
		ExternalContext extCtx = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession sesion = (HttpSession)extCtx.getSession(false);
		return sesion.getAttribute(Const.USUARIO) != null;
	}
	
	public static boolean tieneFuncion(Integer funcionId) {
		if (verificarLogueo()) {
			/*ExternalContext extCtx = FacesContext.getCurrentInstance().getExternalContext(); 
			HttpSession sesion = (HttpSession)extCtx.getSession(false);			
			PermisosAdmin permisosAdmin = new PermisosAdmin();
			HashSet<Funcion> fun = (HashSet<Funcion>)sesion.getAttribute(Const.FUNCIONES);
			permisosAdmin.setFunciones(fun);	
			-	
			return permisosAdmin.tieneFuncion(funcionId);*/	
			return new Boolean(true);
		}
		else {
			return new Boolean(false);
		}
	}

	public static Boolean getTienePermiso(int funcionId) {
		return new Boolean(tieneFuncion(new Integer(funcionId)));
	}

		
	

}
