package cl.jfactory.planillas.service.util;

import java.util.Hashtable;
import java.util.jar.Attributes;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import groovy.util.slurpersupport.Attribute;

public final class LDAPAuth {
	 
    private String usuario;
    private String clave;
    private String servidor;
    private String dn;
    private String tipoAuth;
    private boolean autenticado;
     
    DirContext dc;
 
    /**
     * Constructor de la conexion con el Motor de LDAP
     *
     * @param server  Servidor en donde se encuentra el LDAP
     * @param dn      Directoria del arbol del LDAP
     * @param ta      Tipo de Autenticacion
     * @param usuario Usuario que desea realizar la conexion
     * @param clave   Clave del usuario
     *
     */
    public LDAPAuth(String server, String dn, String ta,String usuario,String clave) {
        this.servidor = server;
        this.dn = dn;
        this.tipoAuth = ta;
        this.usuario=usuario;
        this.clave=clave;
        inicializarConexion();
    }
 
    public void inicializarConexion() {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, servidor);
        env.put(Context.SECURITY_AUTHENTICATION, tipoAuth);
        env.put(Context.SECURITY_PRINCIPAL, dn);
        env.put(Context.SECURITY_CREDENTIALS, clave);
 
        try {
            dc = new InitialDirContext(env);
            setAutenticado(true);
        } catch (NamingException ex) {
        	UtilLogProcesos.debug("Error Autenticando mediante LDAP, Error causado por : " + ex.toString());
            setAutenticado(false);
        }
    }
 
  
     
    /*Get's y Set's*/
    public boolean isAutenticado() {
        return autenticado;
    }
    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
