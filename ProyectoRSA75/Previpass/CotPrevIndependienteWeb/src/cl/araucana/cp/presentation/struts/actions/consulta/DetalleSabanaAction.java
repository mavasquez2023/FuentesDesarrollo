package cl.araucana.cp.presentation.struts.actions.consulta;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.mgr.ComprobanteMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.mgr.SabanaMgr;
import cl.araucana.cp.presentation.struts.forms.DetalleSabanaForm;

import com.bh.talon.User;
/*
* @(#) DetalleSabanaAction.java 1.11 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * @author vagurto
 * 
 * @version 1.11
 */
public class DetalleSabanaAction extends AppAction
{
	static Logger logger = Logger.getLogger(ConsultaAction.class);
	private char tipoNomina;	
	/**
	 * detalle sabana
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DetalleSabanaForm actForm = (DetalleSabanaForm) form; 
		
		Session session = null;
		try 
		{
			session = HibernateUtil.getSession();

			//seteo tipo proceso
			if (actForm.getTiposProcesos() == null || actForm.getTiposProcesos().size() == 0)
				actForm.setTiposProcesos((List)(new ComprobanteMgr(session)).getTiposProceso());
			if (actForm.getTipoProceso() == null || actForm.getTipoProceso().equals(""))
			{
				TipoNominaVO tn = (TipoNominaVO)actForm.getTiposProcesos().get(0);
				this.tipoNomina = tn.getIdTipoNomina().charAt(0);
				actForm.setTipoProceso(tn.getIdTipoNomina());
			} else
				this.tipoNomina = actForm.getTipoProceso().charAt(0);
			if (actForm.getAccion()!= null && actForm.getAccion().equals("pdf"))
				return generarPDF(actForm, session, response);

			logger.info("DetalleSabanaAction:genera lista comprobantes para tipoProceso:" + this.tipoNomina + "::");
			ProcesoMgr procesoMgr = new ProcesoMgr(session);
	
			Map result =  procesoMgr.getEmpConvSabana(this.tipoNomina, (PersonaVO)usuario.getUserReference());
			List consulta = new ArrayList(result.values());
			List lista2 = null;
			if (consulta.size() > 0)
			{	
				for (Iterator it = consulta.iterator(); it.hasNext();)
				{
					EmpresaVO emp = (EmpresaVO)  it.next();
					lista2 = new ArrayList(emp.getConvenios());
					Collections.sort(lista2);
					emp.setConvenios(lista2);
				}
				//Collections
				int pagina = request.getParameter("paginaNumero")!= null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
				HashMap paginacion = Utils.llenarPaginacionCL(pagina, consulta);
				ArrayList lista = new ArrayList((List)paginacion.get("data"));
				Collections.sort(lista);
				actForm.setConsulta(lista);
				actForm.setNumeroFilas((List)paginacion.get("paginas"));//enlaces listos, llegar y mostrar. llama a funcion JS
			}
			else
			{
				actForm.setConsulta(null);
				actForm.setNumeroFilas(null);
			}
			return mapping.findForward("exito");
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en detalleSabanaAction.doTask()", ex);
			return mapping.findForward("error");
		}
	}
	/**
	 * generar pdf
	 * @param actForm
	 * @param usuario
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward generarPDF(DetalleSabanaForm actForm, Session session, HttpServletResponse response) throws Exception
	{
		try
		{
			logger.debug("generarPDF:" + actForm.getIdCodBarras() + "::" + actForm.getRutEmpresa() + "::");
			SabanaMgr sabanaMgr = new SabanaMgr(session);
			
			String nameFile = sabanaMgr.generarPdf(actForm.getIdCodBarras(), actForm.getRutEmpresa(), Constants.TIPO_EMPRESA_INDEPENDIENTE);
			
			FileInputStream fis = new FileInputStream(nameFile);
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=" + nameFile);
			ServletOutputStream out = response.getOutputStream();
			int count;
			while ((count = fis.read()) >= 0)
				out.write(count);
			fis.close();
			out.flush();
			return null;
		} catch (Exception e)
		{
			logger.error("PagarAction:generarPDF: ERROR al generarPDF:", e);
			return null;
		} 
	}	
}

