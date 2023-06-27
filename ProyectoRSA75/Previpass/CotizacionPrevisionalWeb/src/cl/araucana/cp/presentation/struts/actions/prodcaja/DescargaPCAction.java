package cl.araucana.cp.presentation.struts.actions.prodcaja;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.core.util.Rut;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.hibernate.dao.ParametrosDAO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.mail.ReportaError;
import cl.araucana.cp.mail.data.Mail;
import cl.araucana.cp.nominas.receiver.AdaptedHttpServletRequestWrapper;
import cl.araucana.cp.nominas.receiver.NominasMultiPartInputStream;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ComprobanteMgr;
import cl.araucana.cp.presentation.mgr.ConceptoMgr;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.DistribuidorMgr;
import cl.araucana.cp.presentation.mgr.EmpresaMgr;
import cl.araucana.cp.presentation.mgr.EntidadesMgr;
import cl.araucana.cp.presentation.mgr.ParametroMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.mgr.ProdCajaMgr;
import cl.araucana.cp.presentation.mgr.ReceptorMgr;
import cl.araucana.cp.presentation.mgr.UsuarioMgr;
import cl.araucana.cp.presentation.struts.forms.DescargaPCActionForm;
import cl.araucana.cp.util.reporte.ConstructorEXCEL;
import cl.araucana.cp.util.vo.ConceptoVO;

import com.bh.talon.User;

/*
 * @(#) ConsultaAction.java 1.21 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.21
 */
public class DescargaPCAction extends AppAction
{
	static Logger logger = Logger.getLogger(DescargaPCAction.class);
	private char tipoNomina;
	private ServletContext servletContext;
	
	public void init(ServletConfig config) throws ServletException {
		
		servletContext = config.getServletContext();
	}
	/**
	 * consulta action
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DescargaPCActionForm actForm = (DescargaPCActionForm) form;
		
		Session session = null;
		Session sessionTES = null;
		Transaction tx = null;
		Transaction txTES = null;
		try
		{
			
			
			session = HibernateUtil.getSession("");
			ParametrosDAO parametrosDAO = new ParametrosDAO(session);
			ParametroVO parametroVO = parametrosDAO.getParametro(new Integer(Constants.PARAM_PERIODO).intValue());
			// seteo tipo proceso
			if (actForm.getTiposProcesos() == null || actForm.getTiposProcesos().size() == 0)
				actForm.setTiposProcesos(((List) (new ComprobanteMgr(session)).getTiposProceso()).subList(0, 1));
			if (actForm.getTipoProceso() == null || actForm.getTipoProceso().equals(""))
			{
				TipoNominaVO tn = (TipoNominaVO) actForm.getTiposProcesos().get(0);
				this.tipoNomina = tn.getIdTipoNomina().charAt(0);
				actForm.setTipoProceso(tn.getIdTipoNomina());
			} else
				this.tipoNomina = actForm.getTipoProceso().charAt(0);
			
			if (actForm.getAccion() != null && actForm.getAccion().equals("excel")){
				Integer periodo= new Integer(parametroVO.getValor().trim());
				sessionTES = HibernateUtil.getSession("TES");
				return generaExcel(actForm, session, sessionTES, request, response, periodo.intValue());
			}else if (actForm.getAccion() != null && actForm.getAccion().equals("procesa")){
				Integer periodo= new Integer(parametroVO.getValor().trim());
				sessionTES = HibernateUtil.getSession("TES");
				return procesaNomina(mapping, actForm, session, sessionTES, request, response, periodo.intValue());
			}
			logger.info("DescargarAction:genera lista nominas para tipoProceso:" + this.tipoNomina + "::");
			ProcesoMgr procesoMgr = new ProcesoMgr(session);
			Map result = procesoMgr.getEmpConvDescargaPC(this.tipoNomina, (PersonaVO) usuario.getUserReference(), (UsuarioCP) usuario);
			List consulta = new ArrayList(result.values());
			logger.info("DescargarAction: número de empresas con Prodcutos Caja:" + consulta.size());
			if (consulta.size() > 0)
			{
				for (Iterator it = consulta.iterator(); it.hasNext();)
				{
					EmpresaVO empresa = (EmpresaVO) it.next();
					List lista = new ArrayList(empresa.getConvenios());
					Collections.sort(lista);
					empresa.setConvenios(lista);
				}
				Collections.sort(consulta);
				int pagina = request.getParameter("paginaNumero") != null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
				HashMap paginacion = Utils.llenarPaginacionCL(pagina, consulta);
				consulta = (List) paginacion.get("data");
				actForm.setNumeroFilas((List) paginacion.get("paginas"));
				actForm.setConsulta(consulta);
			} else
			{
				actForm.setConsulta(null);
				actForm.setNumeroFilas(null);
			}
			return mapping.findForward("descargaPC");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en DescargarAction.doTask()", ex);
			ex.printStackTrace();
			return mapping.findForward("error");
		}
		finally 
		{
			HibernateUtil.closeSession("");
			if(sessionTES!=null){
				HibernateUtil.closeSession("TES");
			}
		}
	}


	/**
	 * genera excel
	 * 
	 * @param actForm
	 * @param usuario
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward procesaNomina(ActionMapping mapping, DescargaPCActionForm actForm, Session session, Session sessionTES, HttpServletRequest request, HttpServletResponse response, int periodo) throws Exception
	{
		int idConvenio=0;
		int rutEmpresa=0;
		String listaConvenios="";
		String filename="";
		
		boolean zip= false;
		try {
			idConvenio = Integer.parseInt(actForm.getIdConvenio());
			rutEmpresa = Integer.parseInt(actForm.getRutEmpresa());
			listaConvenios=rutEmpresa+"_"+ idConvenio;

		} catch (Exception e) {
			listaConvenios= actForm.getListaConvenios();
			if(listaConvenios==null){
				listaConvenios="";
			}
			zip= true;
			//output= response.getOutputStream();
		}
		
		String[] lista= listaConvenios.split(",");

		ConvenioMgr convenioMgr = new ConvenioMgr(session);
		EmpresaMgr empresaMgr = new EmpresaMgr(session);
		ProdCajaMgr prodCajaMgr= new ProdCajaMgr(sessionTES);
		EntidadesMgr entidadesMgr = new EntidadesMgr(session);
		
		for (int i = 0; i < lista.length; i++) {
			String par= lista[i];
			String[] rutconv= par.split("_");
			rutEmpresa= Integer.parseInt(rutconv[0]);
			idConvenio= Integer.parseInt(rutconv[1]);
			
			// seteo datos comunes a todos los cotizantes
			ConvenioVO convenio = convenioMgr.getConvenio(rutEmpresa, idConvenio);
			//se determina si empresa es privada a pública para extraer datos desde distinta BD
			int tipo_empresa= empresaMgr.getEmpresa(rutEmpresa).getPrivada().intValue();
			int idGrupoConvenio = convenio.getIdGrupoConvenio();
			//GrupoConvenioVO grupoConvenio = convenioMgr.getGrupoConvenio(idGrupoConvenio);
			EntidadCCAFVO caja = entidadesMgr.getCaja(convenio.getIdCcaf());
			boolean isCCAF = (caja != null ? true : false);
			
			Collection deudores = prodCajaMgr.getDeudoresNomina(rutEmpresa, periodo, tipo_empresa, actForm.getOpciones());
			List deudores_mapeados= new ArrayList();
			logger.info("DescargaPCAction: Total deudores nómina=" + deudores.size());
			HashMap mapconcepto= new HashMap();
			String sucursalMatriz="";
			int mayor=0;
			if (deudores.size() > 0)
			{
				sucursalMatriz= empresaMgr.getSucursalMatriz(rutEmpresa);
				//MAPEO Deudores
				logger.info("DecargaPCAction: maping lista deudores x concepto...");
				
				for (Iterator iter = deudores.iterator(); iter.hasNext();) {
					Object[] deudor = (Object[]) iter.next();
					HashMap mapdeudor= new HashMap();
					//MES
					mapdeudor.put(new Integer(1), String.valueOf(periodo).substring(4));
					//RUT AFILIADO - DV
					mapdeudor.put(new Integer(2), deudor[0] + "-" + deudor[1]);
					//NOMBRE
					mapdeudor.put(new Integer(3), deudor[4]);
					//APELLIDO
					mapdeudor.put(new Integer(4), deudor[5]);
					//SEXO
					String sexo= (String)deudor[6];
					if (sexo.trim().equals("")){
						sexo= "M";
					}
					mapdeudor.put(new Integer(5), sexo);
					//SUCURSAL
					mapdeudor.put(new Integer(6), sucursalMatriz);
					//CUOTA CREDITO
					mapdeudor.put(new Integer(34), String.valueOf(deudor[2]));
					//CUOTA DENTAL
					mapdeudor.put(new Integer(35), String.valueOf(0));
					//CUOTA LEASING
					mapdeudor.put(new Integer(36), String.valueOf(deudor[3]));
					//CUOTA SEGURO VIDA
					mapdeudor.put(new Integer(37), String.valueOf(0));
					deudores_mapeados.add(mapdeudor);
				}
				//MAPEO Conceptos
				logger.info("DecargaPCAction: maping conceptos según mapeo definido...");
				List conceptos = empresaMgr.getListaConceptos(idGrupoConvenio);
				
				for (Iterator iter = conceptos.iterator(); iter.hasNext();) {
					Object[] concepto = (Object[]) iter.next();
					//Si posición de mapeo no está definido no se guarda.
					if(((Integer)concepto[1]).intValue()!=0){
						ConceptoVO conce= new ConceptoVO();
						conce.setIdConcepto(((Integer)concepto[0]).intValue());
						conce.setNombre((String)concepto[2]);
						conce.setLargo(((Integer)concepto[3]).intValue());
						mapconcepto.put((Integer)concepto[1], conce);
						//se guarda la mayor posición
						if(((Integer)concepto[1]).intValue()>mayor){
							mayor= ((Integer)concepto[1]).intValue();
						}
					}
				}
				//Collections.sort(deudores);
			}
	
			byte[] input=null;
			
			//Borra CRC
			try{
				ConceptoMgr conceptoMgr = new ConceptoMgr(session);
				conceptoMgr.borraCRCMasivo(tipoNomina, idGrupoConvenio);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//se genera la DATA para zipear y enviar a Receiver.do
			logger.info("DecargaPCAction: generando DATA para zippear nomina ...");
			boolean generaEncabezado= false;
			input= ConstructorEXCEL.generaDataProductosCaja(deudores_mapeados, mapconcepto, generaEncabezado, mayor);
			
			String convaux= String.valueOf(idConvenio);
			if(convaux.length()==1){
				convaux= "0"+ convaux;
			}
			Rut rut= new Rut(rutEmpresa);
			String id= rutEmpresa + "" + rut.getDV() + ".R" + convaux;
			byte[] zippedData=zipNominas(input, id);
			AdaptedHttpServletRequestWrapper adaptedRequest = new AdaptedHttpServletRequestWrapper(servletContext, request, zippedData);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/receiver.do" + "?accion=nominas&subAccion=envioUnicaNomina");

			adaptedRequest.setAttribute("adaptedReceiver", Boolean.TRUE);

			logger.info("DecargaPCAction: forwarding zipped nomina ...");

			requestDispatcher.forward(adaptedRequest, response);

			logger.info("DecargaPCAction: zipped nominas forwarded.");	

		}
		if(zip){

		}
		//writer.close();
		return null;
	}
	
	public ActionForward generaExcel(DescargaPCActionForm actForm, Session session, Session sessionTES, HttpServletRequest request, HttpServletResponse response, int periodo) throws Exception
	{
		int idConvenio=0;
		int rutEmpresa=0;
		String listaConvenios="";
		String filename="";
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");		
		//PrintWriter writer = null;
		OutputStream output= response.getOutputStream();
		ZipOutputStream zos= null;
		boolean zip= false;
		try {
			idConvenio = Integer.parseInt(actForm.getIdConvenio());
			rutEmpresa = Integer.parseInt(actForm.getRutEmpresa());
			filename="Nómina Productos Caja - " + rutEmpresa + "-Conv." + idConvenio + "-" + this.tipoNomina + ".csv";
			//response.setContentType("application/vnd.ms-excel");
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			listaConvenios=rutEmpresa+"_"+ idConvenio;
			//writer = response.getWriter();
		} catch (Exception e) {
			listaConvenios= actForm.getListaConvenios();
			filename="Nómina Productos Caja - " + this.tipoNomina  + ".zip";
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			if(listaConvenios==null){
				listaConvenios="";
			}
			zip= true;
			//output= response.getOutputStream();
		}
		
		String[] lista= listaConvenios.split(",");

		ConvenioMgr convenioMgr = new ConvenioMgr(session);
		EmpresaMgr empresaMgr = new EmpresaMgr(session);
		EntidadesMgr entidadesMgr = new EntidadesMgr(session);
		ProdCajaMgr prodCajaMgr= new ProdCajaMgr(sessionTES);
		
		boolean generaEncabezado=true;
		if(zip){
			System.out.println("Opción ZIP");
			zos = new ZipOutputStream(output);
		}
		for (int i = 0; i < lista.length; i++) {
			String par= lista[i];
			String[] rutconv= par.split("_");
			rutEmpresa= Integer.parseInt(rutconv[0]);
			idConvenio= Integer.parseInt(rutconv[1]);
			String convaux= String.valueOf(idConvenio);
			if(convaux.length()==1){
				convaux= "0"+ convaux;
			}
			Rut rut= new Rut(rutEmpresa);
			String id= rutEmpresa + "" + rut.getDV() + ".R" + convaux;
			if(zip){
				ZipEntry entry = new ZipEntry(id);
				zos.putNextEntry(entry);
			}
			// seteo datos comunes a todos los cotizantes
			ConvenioVO convenio = convenioMgr.getConvenio(rutEmpresa, idConvenio);
//			se determina si empresa es privada a pública para extraer datos desde distinta BD
			int tipo_empresa= empresaMgr.getEmpresa(rutEmpresa).getPrivada().intValue();
			int idGrupoConvenio = convenio.getIdGrupoConvenio();
			//GrupoConvenioVO grupoConvenio = convenioMgr.getGrupoConvenio(idGrupoConvenio);
			EntidadCCAFVO caja = entidadesMgr.getCaja(convenio.getIdCcaf());
			boolean isCCAF = (caja != null ? true : false);
			
			// ***** Obtiene deudores y ahorrantes
			Collection deudores = prodCajaMgr.getDeudoresNomina(rutEmpresa, periodo, tipo_empresa, actForm.getOpciones());
			List deudores_mapeados= new ArrayList();
			System.out.println("total deudores nómina=" + deudores.size());
			HashMap mapconcepto= new HashMap();
			String sucursalMatriz="";
			int mayor=0;
			if (deudores.size() > 0)
			{
				sucursalMatriz= empresaMgr.getSucursalMatriz(rutEmpresa);
				//MAPEO Deudores
				System.out.println("Mapeando Lista Deudores");
				
				for (Iterator iter = deudores.iterator(); iter.hasNext();) {
					Object[] deudor = (Object[]) iter.next();
					HashMap mapdeudor= new HashMap();
//					MES
					mapdeudor.put(new Integer(1), String.valueOf(periodo).substring(4));
					//RUT AFILIADO - DV
					mapdeudor.put(new Integer(2), deudor[0] + "-" + deudor[1]);
					//NOMBRE
					mapdeudor.put(new Integer(3), deudor[4]);
					//APELLIDO
					mapdeudor.put(new Integer(4), deudor[5]);
					//SEXO
					String sexo= (String)deudor[6];
					if (sexo.trim().equals("")){
						sexo= "M";
					}
					mapdeudor.put(new Integer(5), sexo);
					//SUCURSAL
					mapdeudor.put(new Integer(6), sucursalMatriz);
					//CUOTA CREDITO
					mapdeudor.put(new Integer(34), String.valueOf(deudor[2]));
					//CUOTA DENTAL
					mapdeudor.put(new Integer(35), String.valueOf(0));
					//CUOTA LEASING
					mapdeudor.put(new Integer(36), String.valueOf(deudor[3]));
					//CUOTA SEGURO VIDA
					mapdeudor.put(new Integer(37), String.valueOf(0));
					deudores_mapeados.add(mapdeudor);
				}
				//MAPEO Conceptos
				List conceptos = empresaMgr.getListaConceptos(idGrupoConvenio);
				
				for (Iterator iter = conceptos.iterator(); iter.hasNext();) {
					Object[] concepto = (Object[]) iter.next();
					//Si posición de mapeo no está definido no se guarda.
					if(((Integer)concepto[1]).intValue()!=0){
						ConceptoVO conce= new ConceptoVO();
						conce.setIdConcepto(((Integer)concepto[0]).intValue());
						conce.setNombre((String)concepto[2]);
						conce.setLargo(((Integer)concepto[3]).intValue());
						mapconcepto.put((Integer)concepto[1], conce);
						//se guarda la mayor posición
						if(((Integer)concepto[1]).intValue()>mayor){
							mayor= ((Integer)concepto[1]).intValue();
						}
					}
				}
				//Collections.sort(deudores);
			}
	
			if(zip){	
				System.out.println("Zipeando registros");
				generaEncabezado= false;
				ConstructorEXCEL.generaNominaProductosCaja(deudores_mapeados, mapconcepto, zos, generaEncabezado, mayor);
				//ConstructorEXCEL.generaTabla(this.tipoNomina, deudores_mapeados, mapconcepto, zos, periodo, Utils.formatRut(rutEmpresa), idConvenio, mayor, (isCCAF ? caja.getNombre().trim() : ""), 
				//		"", "", "", Constants.GRUPO_CONV_PRODUCTOS_CAJA, generaEncabezado);
			}else{
				generaEncabezado= false;
				ConstructorEXCEL.generaNominaProductosCaja(deudores_mapeados, mapconcepto, output, generaEncabezado, mayor);
			}
		}
		if(zip){
			zos.flush();
			zos.close();
		}
		//writer.close();
		output.close();
		return null;
	}
	
	public void zipConsultaExcel(String filename, OutputStream out, ByteArrayInputStream data){
		try {
			byte b[] = new byte[2048];
			System.out.println("generando ZIP");
			ZipOutputStream zos = new ZipOutputStream(out);
			ZipEntry entry = new ZipEntry("Detalle Nomina_Caja.xls");
			zos.putNextEntry(entry);
		    int len;
		    while ((len = data.read(b)) != -1) {
		        zos.write(b, 0, len);
		    }
			zos.flush();
			zos.close();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	private void rechazaNominas(int idEnvio, boolean rechazoPorNodo)
	{
		Transaction tx = null;
		try
		{
			Session session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			ReceptorMgr receptorMgr = new ReceptorMgr(session);
			receptorMgr.rechazaNominas(idEnvio, rechazoPorNodo);
			tx.commit();
		} catch (Exception e)
		{
			if (tx != null)
				tx.rollback();
		}
	}

private byte[] zipNominas(byte[] content, String id) throws IOException {
		
		/*
		 * Calculates reference content length.
		 */
		final int ZIP_ENTRY_SIZE = 128;
		
		int referenceContentLength = content.length;
		
		logger.info(">> Nominas:");

		ByteArrayOutputStream output = new ByteArrayOutputStream(referenceContentLength + ZIP_ENTRY_SIZE );
		
		ZipOutputStream zipOutput = new ZipOutputStream(output);

		ZipEntry zipEntry = new ZipEntry(id);

		zipOutput.putNextEntry(zipEntry);
		zipOutput.write(content);
		zipOutput.closeEntry();
		
		zipOutput.close();
		output.close();
		
		return output.toByteArray();
	}
	
}