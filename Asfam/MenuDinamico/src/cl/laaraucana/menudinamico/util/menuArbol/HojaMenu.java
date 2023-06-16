/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.laaraucana.menudinamico.util.menuArbol;

/**
 *
 * @author Luis Veas
 */
public class HojaMenu extends ElementoMenu{

    public HojaMenu(String enlace,String etiqueta, long codMenu, long isParent) {
        super (enlace,etiqueta, codMenu, isParent);
    }

    @Override
    public void add(ElementoMenu ea) {
        System.out.print("no puede agregar la hoja");
    }

    @Override
    public void remove(ElementoMenu ea) {
        System.out.print("no puede remover la hoja");
    }
    
    private String getClassIds(){
    	//identificar los padre e hijo
    	String padreHijo = (this.get_isParent()==0) ? "padre" : "hijo";
    	String btnVolver = (this.get_etiqueta().trim().toUpperCase().contains("VOLVER")==true)? " volver nodoPadre_"+this.get_isParent():"";
    	return padreHijo + btnVolver;
    }

    public String display() {
        return (this.get_enlace().compareTo("#")==0)?"<li><input type='checkbox' class='"+getClassIds()+"' id='opcArbol_"+this.get_codMenu()+"' name='menuArbol' value='"+this.get_codMenu()+"'/><a href='#'>"+this.get_etiqueta()+"</a></li>":"<li><input type='checkbox' class='"+getClassIds()+"' id='opcArbol_"+this.get_codMenu()+"' name='menuArbol' value='"+this.get_codMenu()+"'/><a href='#' >"+this.get_etiqueta()+"</a></li>";
    }

	public String display2() {
		return (this.get_enlace().compareTo("#")==0)?"<li><a href='#'>"+this.get_etiqueta()+" (Nodo:"+this.get_codMenu()+")</a><input type='button' class='boton update_item' name='menuArbol' onclick=\"javascript:openActualizar_Menu('"+ this.get_codMenu() + "')\" value='Editar'/><input type='button' class='boton delete_item' onclick=\"javascript:openEliminar_Menu('"+ this.get_codMenu() + "')\" value='Eliminar'/></li>":"<li><a href='#'>"+this.get_etiqueta()+" (Nodo:"+this.get_codMenu()+")</a><input type='button' class='boton update_item' name='menuArbol' onclick=\"javascript:openActualizar_Menu('"+ this.get_codMenu() + "')\" value='Editar'/><input type='button' class='boton delete_item' onclick=\"javascript:openEliminar_Menu('"+ this.get_codMenu() + "')\" value='Eliminar'/></li>";

	}
}
