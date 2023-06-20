/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.laaraucana.menudinamico.util.menuArbol;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Luis Veas
 */
public class NodoMenu extends ElementoMenu {
    
    private ArrayList children = new ArrayList();

    public NodoMenu(String enlace,String etiqueta, long codMenu, long isParent) {
        super (enlace,etiqueta, codMenu, isParent);
    }

    @Override
    public void add(ElementoMenu ea) {
       this.children.add(ea);
    }

    @Override
    public void remove(ElementoMenu ea) {
        this.children.remove(ea);
    }
    
    private String getClassIds(){
    	String padreHijo = (this.get_isParent()==0) ? "padre" : "padre hijo";
    	String btnVolver = (this.get_etiqueta().trim().toUpperCase().contains("VOLVER")==true)? " volver nodoPadre_"+this.get_isParent():"";
    	return padreHijo + btnVolver;
    }

 public String display() {
	
        String txt = (this.get_enlace().compareTo("#")==0)?"<li><a href='#'>"+this.get_etiqueta()+"</a><ul>":"<li><input id='opcArbol_"+this.get_codMenu()+"' type='checkbox' class='"+getClassIds()+"' name='menuArbol' value='"+this.get_codMenu()+"'/><a href='#' >"+this.get_etiqueta()+"</a><ul>";

        //muestra cada uno de los hijos del nodo

        Iterator obj = children.iterator();

        while (obj.hasNext()) {
            txt=txt+((ElementoMenu)obj.next()).display();
        }
        txt=txt+"</ul></li>";

        return txt;
    }

@Override
	public String display2() {
		String txt = (this.get_enlace().compareTo("#")==0)?"<li><a href='#'>"+this.get_etiqueta()+this.get_codMenu()+"</a><ul>":"<li><a href='#'>"+this.get_etiqueta()+" (Nodo:"+this.get_codMenu()+")</a><input type='button' class='boton update_item' name='menuArbol' onclick=\"javascript:openActualizar_Menu('"+ this.get_codMenu() + "')\" value='Editar'/><input type='button' class='boton delete_item' onclick=\"javascript:openEliminar_Menu('"+ this.get_codMenu() + "')\" value='Eliminar'/><ul>";
		//muestra cada uno de los hijos del nodo
		
		Iterator obj = children.iterator();
		while (obj.hasNext()) {
			txt = txt + ((ElementoMenu) obj.next()).display2();
		}
		txt = txt + "</ul></li>";
		return txt;
}
}
