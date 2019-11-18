
package com.refinor.extranet.faces.seguridad;

/*import com.refinor.extranet.data.Funcion;
import com.refinor.extranet.data.Menu;
import com.refinor.extranet.data.dao.MenuDAO;
import com.refinor.extranet.data.dao.TipoDocumentoDAO;*/
import com.refinor.extranet.faces.base.AbstBackingBean;
//import com.refinor.extranet.seguridad.PerfilAdmin;
import com.refinor.extranet.util.Const;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.myfaces.custom.navmenu.NavigationMenuItem;
import org.hibernate.Session;

/**
 * @author  mliz
 */
public class MenuBean extends AbstBackingBean {
	private ArrayList<NavigationMenuItem> navItems;
	private Session session;
	
	
	/**
	 * @return  the navItems
	 * @uml.property  name="navItems"
	 */
	public ArrayList getNavItems() {
		return navItems;
	}


	public void setNavItems(ArrayList navItems) {
		this.navItems = navItems;
	}

	public MenuBean() {
	/*	session= (new MenuDAO()).getSession();
		MenuDAO menuDAO= new MenuDAO(session);
		PerfilAdmin perfAdmin= new PerfilAdmin(session);
		navItems= new ArrayList<NavigationMenuItem>();
		List menues= menuDAO.findMenuItems();
		Iterator it= menues.iterator();
		Menu menu;
		NavigationMenuItem item;
		while(it.hasNext()) {
			menu= (Menu)it.next();
			Set funcionesMenu= perfAdmin.getFuncionesMenu(new Integer(menu.getId()), (Set)this.getSessionValue(Const.FUNCIONES));
			if(funcionesMenu.size() > 0) {
				item= new NavigationMenuItem(menu.getDescripcion(), (menu.getUrl() != null ? menu.getUrl() : ""), "/img/separador.gif", false);
				Iterator it2= funcionesMenu.iterator();
				Funcion func;
				NavigationMenuItem[] subItems= new NavigationMenuItem[funcionesMenu.size()];
				int i= 0;
				while(it2.hasNext()) {
					func= (Funcion)it2.next();
					subItems[i++]= new NavigationMenuItem(func.getDescripcion(), func.getIdent(), "/img/option.gif", false);
				}
				item.setNavigationMenuItems(subItems);
				navItems.add(item);
			}
		}*/
	}
	
	public Boolean getPuedeIngresar() {
		return new Boolean(true);
	}
}
