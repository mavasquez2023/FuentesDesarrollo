package cl.araucana.adminCpe.presentation.struts.actions.nomina;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.dao.NominaDAO;
import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ComprobanteMgr;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;

import com.bh.talon.User;

/*
 * @(#) GenerarComprobPDFAction.java 1.2 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * @author jsalazar
 * 
 * @version 1.2
 */
public class GenerarComprobPDFAction extends AppAction
{
	private static Logger logger = Logger.getLogger(GenerarComprobPDFAction.class);
	

	/**
	 * PDF
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		String mappingFoward = "xxxx";
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try
		{
			tx = session.beginTransaction();

			if (this.accion.equals("pdf"))
			{
				String codigoStr = request.getParameter("codigo");
				long codigo = Long.parseLong(codigoStr);
				logger.info("GenerarComprobPDFAction: GenerarPDF: " + codigo);
				String[] listaCodBarras = new String[1];
				listaCodBarras[0] = codigoStr;
				generarPDF(response, session, listaCodBarras);
				return null;
			}

			tx.commit();

		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en GenerarComprobPDFAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}

		return mapping.findForward(mappingFoward);
	}

	/**
	 * generar PDF
	 * 
	 * @param form
	 * @param response
	 * @param session
	 * @param idUser
	 * @param listaCodBarras
	 * @return
	 */
	public boolean generarPDF(HttpServletResponse response, Session session, String[] listaCodBarras)
	{
		try
		{
			logger.info("-------- generar PDF --------");
			
			ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);
			List codigos = new ArrayList();
			for (int i = 0; i < listaCodBarras.length; i++)
			{
				codigos.add(new Long(listaCodBarras[i]));
				logger.info(codigos.get(i));
			}

			
			NominaDAO nominaDAO = new NominaDAO(session);
			NominaVO nominaVO = nominaDAO.getNominabyCodigoBarra(new Long(listaCodBarras[0]).longValue());
			
			String tipoEmpresa ="";
			if(nominaVO.getEmpresa().getTipo().equals(Constants.TIPO_EMPRESA)){
				tipoEmpresa= Constants.TIPO_EMPRESA;
			}else{
				tipoEmpresa= Constants.TIPO_EMPRESA_INDEPENDIENTE;
			}
			
			String nameFile = comprobanteMgr.generarArchivos(codigos,tipoEmpresa);
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

			return true;
		} catch (Exception e)
		{
			logger.error("PagarAction:generarPDF: ERROR al generarPDF:", e);
			return false;
		}
	}

}
