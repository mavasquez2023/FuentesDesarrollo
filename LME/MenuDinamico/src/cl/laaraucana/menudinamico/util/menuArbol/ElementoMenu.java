/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.laaraucana.menudinamico.util.menuArbol;

/**
 *Clase abstracta creada para soportar la implementación
 *de los nodos de un arból.
 *
 * Este es un ejemplo practico de pratón de diseño denominado
 * COMPOSICIÓN
 *
 * @author Luis Veas
 */
public abstract class ElementoMenu {

	private long _codMenu; 
    private String _enlace;
    private String _etiqueta;
    private long _isParent;

    public ElementoMenu(String enlace,String etiqueta, long codMenu, long isParent){
        this._enlace=enlace;
        this._etiqueta=etiqueta;
        this._codMenu=codMenu;
        this._isParent=isParent;

        
    }

    public String get_etiqueta() {
        return _etiqueta;
    }

    public String get_enlace() {
        return _enlace;
    }
    

    public long get_codMenu() {
		return _codMenu;
	}

	public long get_isParent() {
		return _isParent;
	}

	public abstract void add(ElementoMenu ea);
    public abstract void remove(ElementoMenu ea);
    public abstract String display();
    public abstract String display2();
}
