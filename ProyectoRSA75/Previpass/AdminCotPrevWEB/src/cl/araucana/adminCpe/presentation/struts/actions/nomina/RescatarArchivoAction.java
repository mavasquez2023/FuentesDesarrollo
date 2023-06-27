package cl.araucana.adminCpe.presentation.struts.actions.nomina;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.NominaMgr;
import cl.araucana.adminCpe.presentation.mgr.ParametroMgr;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.util.vo.DocumentoVO;

import com.bh.talon.User;

/*
 * @(#) RescatarArchivoAction.java 1.4 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author aacuña
 * 
 * @version 1.4
 */
public class RescatarArchivoAction extends AppAction
{
	private static Logger logger = Logger.getLogger(RescatarArchivoAction.class);

	/**
	 * Rescatar archivo trabajador
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		logger.info("---------> Rescatar txt");
		String idConv = request.getParameter("idConv");
		String rutEmp = request.getParameter("rutEmp");
		String tipoNom = request.getParameter("tipoNom");
		logger.info(idConv + " " + rutEmp + " - " + tipoNom);

		Session session = null;
		try
		{
			session = HibernateUtil.getSession();
			NominaMgr nominaMgr = new NominaMgr(session);
			ParametroMgr parametroMgr = new ParametroMgr(session);
			DocumentoVO doc = nominaMgr.extraeDocumento(Integer.parseInt(rutEmp), tipoNom, Integer.parseInt(idConv));
			String nameFile = "";
			if (doc != null)
			{
				if (doc.getData() == null)
				{
					String mje = "Sin data";
					doc.setData(mje.getBytes());
				} 
				nameFile = Utils.formatRut(doc.getRutEmpresa()) + "." + ("" + doc.getTipoProceso()).toUpperCase() + (doc.getIdConvenio() < 10 ? "0" : "") + doc.getIdConvenio();
			} else
			{
				nameFile = "no data";
				String mje = "Sin data";
				doc = new DocumentoVO();
				doc.setData(mje.getBytes());
			}

			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment; filename=" + nameFile + ".txt");

			ByteArrayInputStream bais = new ByteArrayInputStream(parametroMgr.unzipData(doc.getData()));
			BufferedReader br = new BufferedReader(new InputStreamReader(bais, "ISO-8859-1"));
			String line = "";
			while ((line = br.readLine()) != null) // por cada linea
			{
				response.getOutputStream().print(line);
				response.getOutputStream().print("\r\n");
			}

			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e)
		{
			logger.error(e);
		}
		logger.info(" Salir del rescate txt <<-------");
		return null;
	}
}
