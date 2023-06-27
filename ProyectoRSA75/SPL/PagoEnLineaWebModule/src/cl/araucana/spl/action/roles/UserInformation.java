package cl.araucana.spl.action.roles;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import araucana.security.Opcion;
import cl.araucana.spl.beans.Rol;
import cl.araucana.spl.dao.UtilisRolDAO;
import cl.araucana.spl.dao.config.DaoConfig;

import com.ibatis.dao.client.DaoManager;

public class UserInformation implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 3505313839548071813L;
	
	 /** Logger de traza aplicativa */
	private static Logger logger = Logger.getLogger(UserInformation.class);
	
	Collection acciones;
    
    private String usuario;
    private String rut;
    private boolean administrador;

    private UtilisRolDAO daoUtilisRolDAO;
    
    
    public UserInformation()
    {
    	acciones = null;
        usuario = null;
        rut = null;
    }
    
    public UserInformation(String user)
    {
    	logger.info("Entro a UserInformation ..... ");
    	DaoManager mgr = DaoConfig.getDaoManager();
    	daoUtilisRolDAO = (UtilisRolDAO)mgr.getDao(UtilisRolDAO.class);

        acciones = null;
        usuario = null;
        rut = null;
        userInformation(user);
        
    }

    private void userInformation(String user)
    {
		logger.info("Entro a UserInformation.userInformation ..... ");
		try
	        {
	            InfoUsuarioBO infoUsuarioBO = this.getInfoUsuario(user);
	            InfoUsuarioDVO infoUsuarioDVO = infoUsuarioBO.getInfoUsuarioDVO();
	            
	            usuario = infoUsuarioDVO.getUsuario();
	            rut = infoUsuarioDVO.getRut();
	            administrador = infoUsuarioBO.isAdministrador();
	            OpcionDVO opciones[] = infoUsuarioBO.getOpcionDVOs();
	            if(opciones != null && opciones.length > 0)
	            {
	                Collection acciones = new ArrayList();
	                for(int x = 0; x < opciones.length; x++)
	                {
	                    OpcionDVO opcionDVO = opciones[x];
	                    Opcion opcion = new Opcion();
	                    opcion.setId(opcionDVO.getCodigo());
	                    opcion.setDesc(opcionDVO.getDescripcion());
	                    acciones.add(opcion);
	                }
	
	                setAcciones(acciones);
	            }
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
       }

    public Collection getAcciones(){
        return acciones;
    }

    public void setAcciones(Collection acciones){
        this.acciones = acciones;
    }

    public boolean hasAccess(String operacion){
            return hasAccessV2(operacion);
    }

    private boolean hasAccessV2(String operacion){
		boolean hasAccess = false;
		if(acciones != null)
		{
		    for(Iterator it = acciones.iterator(); it.hasNext() && !hasAccess;)
		    {
		        Opcion opcion = (Opcion)it.next();
		        if(opcion.getId().trim().equalsIgnoreCase(operacion.trim()))
		        {
		            hasAccess = true;
		        }
		    }
		
		}
		return hasAccess;
    }
       /**
		 * Metodo encargado de  Buscar la Informacion del Usuario<p>
		 *
		 * Registro de Versiones:<ul>
		 * <li>17/03/2014 [gmallea - schema ltda.]: Versi�n Inicial</li>
		 * </ul><p>
		 * 
		 * @param  String codSistema, String rutUsuario 
		 * @return InfoUsuarioBO
		 * @throws Exception
		 *
	*/
	public InfoUsuarioBO getInfoUsuario(String usuario) throws Exception{
			
		logger.info("Entro a UserInformation.getInfoUsuario ..... ");
		
		InfoUsuarioBO infoUsuarioBO = new InfoUsuarioBO();
		
		InfoUsuarioDVO infoUsuarioDVO = new InfoUsuarioDVO();
		
		infoUsuarioDVO.setRut(usuario);
		infoUsuarioDVO.setUsuario(usuario);
		
		infoUsuarioBO.setInfoUsuarioDVO(infoUsuarioDVO);

		//Saco el cuerpo del Rut
		String[] datos = usuario.split("-");
		long rut = Long.parseLong(datos[0]);
		
		logger.info("Opciones a buscar para el rut = " + rut);
		
		//Obtengo la lista de opciones del sistema		
		cl.araucana.spl.action.roles.OpcionDVO[] listaOpciones = daoUtilisRolDAO.getListaOpcionesBySistemaUsuario(""+rut);

		logger.info("Cantidad de opciones encontradas  rut " + rut +" Opciones = " + listaOpciones.length);
		
		logger.info("Roles a buscar para el rut = " + rut);
		
		Rol[] roles = daoUtilisRolDAO.getListaRol(""+rut);
		
		logger.info("Cantidad de Roles encontrados  rut " + rut +" Roles = " + roles.length);
		
		infoUsuarioBO.setAdministrador( (roles != null && roles.length > 0) ? true : false );
		
		logger.info("El usuario " + rut + " es administrado = " + infoUsuarioBO.isAdministrador());
		
		infoUsuarioBO.setOpcionDVOs(listaOpciones);
		
		return infoUsuarioBO;
	}
}