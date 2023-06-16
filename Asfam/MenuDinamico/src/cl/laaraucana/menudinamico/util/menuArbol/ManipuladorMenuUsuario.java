/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.laaraucana.menudinamico.util.menuArbol;

import java.util.ArrayList;
import java.util.Iterator;

import cl.laaraucana.menudinamico.vo.MenuVO;

/**
 *
 * @author Luis Veas
 */
public class ManipuladorMenuUsuario {
    
    private ArrayList _listaMenuUsuario;
    private ArrayList _menuPrincipal;
    private ArrayList _menuSecundario;
    
    public ManipuladorMenuUsuario(ArrayList _listaMenuUsuario) {
        this._listaMenuUsuario = _listaMenuUsuario;
        set_menuPrincipal();
    }

    public ArrayList get_listaMenuUsuario()
    {
         return _listaMenuUsuario;
    }

    public ArrayList get_menuPrincipal()
    {
        return _menuPrincipal;
    }
 

    private void set_menuPrincipal()
    {
        if(this._listaMenuUsuario!=null)
        {
            this._menuPrincipal = new ArrayList();        
            Iterator it = this._listaMenuUsuario.iterator();
            MenuVO mupAux=null;

            while(it.hasNext())
            {
                mupAux=(MenuVO)it.next();
                if(mupAux.getNodoPadre()==0)
                {
                    this._menuPrincipal.add(mupAux);
                }
            }
        }
    }

    public void set_menuSecundario(Long codMenuPadre,String funcionEvento) {
        this._menuSecundario = rellenaArbol(this._listaMenuUsuario,codMenuPadre,funcionEvento);
    }


    public ArrayList get_menuSecundario() {
        return _menuSecundario;
    }

    private ArrayList rellenaArbol(ArrayList lista, Long codMenuPadre,String funcionEvento)
    {
        ArrayList resp=new ArrayList();
        Iterator it = lista.iterator();
        MenuVO aux=null;
        boolean swSalir=false;
        boolean swEntroAlIf=false;
        while(it.hasNext() && swSalir==false)
        {
            aux=(MenuVO)it.next();
            if(String.valueOf(aux.getNodoPadre()).compareTo(String.valueOf(codMenuPadre))==0)
            {
                resp.add(rellenaRamaDelArbol(lista,aux,Long.MIN_VALUE,funcionEvento));
                swEntroAlIf=true;
            }
            else
            {
                swSalir=swEntroAlIf;
            }            
        }
        return resp;
    }

    private ElementoMenu rellenaRamaDelArbol(ArrayList lista,MenuVO t, Long codMenuManipulable,String funcionEvento)
    {
        ElementoMenu e=null;
        if(Integer.valueOf(t.getFlgHoja())==1)
        {
            e = new HojaMenu(t.getEnlace(),t.getEtiqueta(), t.getCodMenu(), t.getNodoPadre());
        }
        else
        {
            e = new NodoMenu(t.getEnlace(),t.getEtiqueta(), t.getCodMenu(), t.getNodoPadre());
            codMenuManipulable = t.getCodMenu();
            Iterator it = lista.iterator();
            MenuVO aux=null;
            boolean swSalir=false;
            boolean swEntroAlIf=false;
            while(it.hasNext() && swSalir==false)
            {
                aux=(MenuVO)it.next();
                if(String.valueOf(aux.getNodoPadre()).compareTo(String.valueOf(codMenuManipulable))==0)
                {
                    e.add(rellenaRamaDelArbol(lista,aux,codMenuManipulable,funcionEvento));
                    swEntroAlIf=true;
                }
                else
                {
                    swSalir=swEntroAlIf;
                }
            }
        }
        return e;
    }

}
