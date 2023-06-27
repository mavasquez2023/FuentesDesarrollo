package cl.araucana.adminCpe.presentation.struts.actions.usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.adminCpe.presentation.mgr.EmpresaMgr;
import cl.araucana.adminCpe.presentation.mgr.LectorMgr;
import cl.araucana.adminCpe.presentation.struts.forms.usuario.PermisosRolLectorActionForm;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.presentation.beans.LineaPermisosRolLectorAsignar;

import com.bh.talon.User;
/*
* @(#) PermisosRolLectorAction.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.2
 */
public class PermisosRolLectorAction extends AppAction
{
	private static Logger logger = Logger.getLogger(PermisosRolLectorAction.class);

	/**
	 * permisos lector administrador
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PermisosRolLectorActionForm actForm = (PermisosRolLectorActionForm) form; 
	
		int rutEmpresa = -1;
		int idConvenio = -1;
		int idLectorEmpresa = -1;
		int idGrupoConvenio = -1;
		String acc="";

		Session session = null;
		Transaction tx = null;
		try 
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			LectorMgr lectorMgr = new LectorMgr(session);
			ConvenioMgr convenioMgr = new ConvenioMgr(session);
			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			
			LineaPermisosRolLectorAsignar linea;					
			if(request.getParameter("rutEmpresa") != null)
				rutEmpresa = Integer.parseInt(request.getParameter("rutEmpresa"));
			if(request.getParameter("idConvenio") != null)
				idConvenio = Integer.parseInt(request.getParameter("idConvenio"));
			if(request.getParameter("idGrupoConvenio") != null)
				idGrupoConvenio = Integer.parseInt(request.getParameter("idGrupoConvenio"));
			if(request.getParameter("idLectorEmpresa") != null)
				idLectorEmpresa = Integer.parseInt(request.getParameter("idLectorEmpresa"));			
			if(request.getParameter("acc") != null){
				acc = request.getParameter("acc").trim();
			}
			
			actForm.setRutEmpresa(String.valueOf(rutEmpresa));
			actForm.setIdConvenio(String.valueOf(idConvenio));
			actForm.setIdGrupoConvenio(String.valueOf(idGrupoConvenio));
			actForm.setIdLectorEmpresa(String.valueOf(idLectorEmpresa));
			actForm.setAcc(acc);

			List lista = new ArrayList();
			
			int largoFilas = 0;
			if(request.getParameter("largoFila") != null)
				largoFilas = Integer.parseInt(request.getParameter("largoFila"));
			
			if(idGrupoConvenio != -1)
			{
				GrupoConvenioVO grupoConvenioVO = convenioMgr.getGrupoConvenio(idGrupoConvenio);
				if(grupoConvenioVO != null)
				{
					linea = new LineaPermisosRolLectorAsignar();
					linea.setTipo("Grupo");
					linea.setCodigo(String.valueOf(idGrupoConvenio));
					linea.setDescripcion(grupoConvenioVO.getNombre());
					if(lectorMgr.existeGrupo(idLectorEmpresa,idGrupoConvenio))
						linea.setExiste("1");
					else
						linea.setExiste("0");
				}
			}
			if(rutEmpresa != -1)
			{
				EmpresaVO empresaVO = empresaMgr.getEmpresa(rutEmpresa);
				if(empresaVO != null)
				{
					linea = new LineaPermisosRolLectorAsignar();
					linea.setTipo("Empresa");
					linea.setCodigo(String.valueOf(rutEmpresa));
					linea.setDescripcion(empresaVO.getRazonSocial());
					if(lectorMgr.existeEmpresa(idLectorEmpresa,rutEmpresa))
						linea.setExiste("1");
					else
						linea.setExiste("0");
				}
			}
			if(idConvenio != -1 && rutEmpresa != -1)
			{
				ConvenioVO convenioVO = convenioMgr.getConvenio(rutEmpresa, idConvenio);
				if(convenioVO != null)
				{
					linea = new LineaPermisosRolLectorAsignar();
					linea.setTipo("Convenio");
					linea.setCodigo(String.valueOf(idConvenio));
					linea.setDescripcion(convenioVO.getDescripcion());
					if(lectorMgr.existeConvenio(idLectorEmpresa,rutEmpresa, idConvenio))
						linea.setExiste("1");
					else
						linea.setExiste("0");
					List listaSucursales = empresaMgr.getSucursales(rutEmpresa);
					int cont=0;
					for (Iterator sucursalIter = listaSucursales.iterator(); sucursalIter.hasNext();)
					{
						SucursalVO suc = (SucursalVO) sucursalIter.next();
						if(suc.getIdSucursal()!= null)
						{
							cont++;
							linea = new LineaPermisosRolLectorAsignar();
							linea.setTipo((cont==1)? "Sucursal":"_");
							linea.setCodigo(String.valueOf(suc.getIdSucursal()));
							linea.setDescripcion(String.valueOf(suc.getNombre()));
							if(lectorMgr.existeSucursal(idLectorEmpresa,rutEmpresa, idConvenio, String.valueOf(suc.getIdSucursal())))
								linea.setExiste("1");
							else
								linea.setExiste("0");
							lista.add(linea);
						}
					}
					
				}
			}
			actForm.setLargoFila(lista.size());
			actForm.setConsulta(lista);
			if(largoFilas > 0)
			{
				int rutTemp = -1;
				if(request.getParameter("rutEmpresa")!= null)
					rutTemp = Integer.parseInt(request.getParameter("rutEmpresa"));
				int convenioTemp = -1;
				if(request.getParameter("idConvenio")!= null)
					convenioTemp = Integer.parseInt(request.getParameter("idConvenio"));				
				String sucursalTemp = "";				
				for(int a=0;a<largoFilas;a++)
				{
					String codTemp =request.getParameter("value_"+a);
					if(request.getParameter("check_"+a) != null)
					{
						sucursalTemp = codTemp;
						lectorMgr.guardaSucursal(idLectorEmpresa, rutTemp, convenioTemp, sucursalTemp);
					} else 
					{
						sucursalTemp = codTemp;
						lectorMgr.borraSucursal(idLectorEmpresa, rutTemp, convenioTemp, sucursalTemp);
					}
					
				}				
			}

			tx.commit();
			return mapping.findForward("exito");
		}  catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en PermisosRolLectorAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			
			return mapping.findForward("error");
		} 
	}
}
