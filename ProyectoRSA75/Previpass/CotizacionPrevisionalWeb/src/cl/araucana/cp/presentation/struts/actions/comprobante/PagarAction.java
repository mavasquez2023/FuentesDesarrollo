package cl.araucana.cp.presentation.struts.actions.comprobante;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ComprobanteMgr;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.EmpresaMgr;
import cl.araucana.cp.presentation.mgr.PagoEnLineaMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.struts.forms.comprobante.PagarActionForm;
import cl.araucana.cp.presentation.struts.javaBeans.LineaComprobantesAPagar;
import cl.araucana.cp.presentation.struts.javaBeans.PagoEnLinea;
import cl.araucana.spl.util.crypto.CryptResult;
import cl.araucana.spl.util.crypto.SimpleEncoder;
import cl.araucana.spl.util.crypto.TripleDesEngine;
import cl.araucana.spl.util.crypto.TripleDesEngineException;

import com.bh.talon.Message;
import com.bh.talon.MessageList;
import com.bh.talon.User;
/*
* @(#) PagarAction.java 1.26 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * @author aacuna
 * 
 * @version 1.26
 */
public class PagarAction extends AppAction
{
	public static final String PAGO_NOMINAS = "pagoNominas";
	public static final String COMPROBANTE_FICHA = "comprobanteFicha";
	public static final String RESUMEN = "resumen";
	public static final String PARAM_SEND = "send";
	public static final String SIN_NOMINAS = "warnSinCodigos";
	public static final String PAGADOS_CERO = "pagados";
	static Logger logger = Logger.getLogger(PagarAction.class);

	public PagarAction()
	{
		super();
		this.btns.add("verDetalle");
		this.btns.add("pagoCaja");
		this.btns.add("pagoLinea");
		this.btns.add("filtro");
	}

	/**
	 * pagar action
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PagarActionForm actForm = (PagarActionForm) form;

		String operacion = request.getParameter("operacion");
		logger.info("PagarAction:operacion:" + operacion + ":txtBoton:" + Constants.TXT_BTNS.getProperty("pagoLinea") + "::");

		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			// Instancia los managers correspondientes
			ProcesoMgr procesoMgr = new ProcesoMgr(session);
			ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);
			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			ConvenioMgr convenioMgr = new ConvenioMgr(session);
			UsuarioCP usuarioCP = (UsuarioCP) user; 
			ActionMessages am = new ActionMessages();

			int convenio = -1;
			int rut = -1;
			String tipoNomina = null;
			long codigoBarra = -1;

			if ((operacion == null) || operacion.equals(""))
			{	// Se llama desde afuera (con URL)
				if (request.getParameter("codigoBarra") == null)
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("inicio"));
			        return redirect;
				}
				codigoBarra = Long.valueOf(request.getParameter("codigoBarra")).longValue();
				NominaVO nomina = comprobanteMgr.getNomina(codigoBarra);

				if (nomina != null)
				{
					rut = nomina.getRutEmpresa();
					convenio = nomina.getIdConvenio();
					tipoNomina = comprobanteMgr.tipoProcesoNomina(nomina);
					logger.info("PagarAction.doTask -> Llamado desde afuera");
				}
			} else
			{// Se llama desde adentro (con submit)
				logger.info("PagarAction.doTask -> Operacion: \"" + operacion + "\"");

				if (Utils.codificaAcentos(operacion).equals(Utils.codificaAcentos(Constants.TXT_BTNS.getProperty("filtro"))))
				{
					rut = Integer.valueOf(actForm.getRut()).intValue();
					convenio = Integer.valueOf(actForm.getConvenio()).intValue();
					tipoNomina = actForm.getTipoNomina();
					NominaVO nomina = procesoMgr.getNomina(tipoNomina, rut, convenio);
					if (nomina != null && nomina.getIdEstado() == Constants.EST_NOM_POR_PAGAR)
						codigoBarra = nomina.getIdCodigoBarras();
					request.setAttribute("cambioParam", "accion=inicio&subAccion=procesos&subSubAccion=pagarNomina&rut=" + rut + "&convenio=" + convenio + "&tipoNomina=" + tipoNomina);
				} else if (Utils.codificaAcentos(operacion).equals(Constants.TXT_BTNS.getProperty("verDetalle")))
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward(COMPROBANTE_FICHA));
					redirect.addParameter("operacion", operacion);
					redirect.addParameter("idCodigoBarras", actForm.getCodigoBarra());
					redirect.addParameter("accion", this.accion);
					redirect.addParameter("subAccion", this.subAccion);
					redirect.addParameter("subSubAccion", "comprobanteFicha");
					request.setAttribute("cambioParam", "accion=inicio&subAccion=procesos&subSubAccion=pagarNomina&codigoBarra=" + actForm.getCodigoBarra());
					tx.commit();
					return redirect;
				} else if (operacion.equals("PDF") || Utils.codificaAcentos(operacion).equals(Constants.TXT_BTNS.getProperty("pagoCaja")) || Utils.codificaAcentos(operacion).equals( Utils.codificaAcentos("Impresión Comprobante")))
				{
					if (actForm.getCodigoBarra() != null && !actForm.getCodigoBarra().equals(""))
					{
						actForm.setCodigos(new String[1]);
						actForm.getCodigos()[0] = Long.toString(Long.parseLong(actForm.getCodigoBarra()));
					} else if (actForm.getConsulta() != null && actForm.getConsulta().size() > 0)
					{
						actForm.setCodigos(new String[actForm.getConsulta().size()]);
						int i = 0;
						for (Iterator it = actForm.getConsulta().iterator(); it.hasNext();)
						{
							actForm.getCodigos()[i++] = Long.toString(((LineaComprobantesAPagar) it.next()).getCodigoBarra());
						}
					}
					logger.debug("Justo antes de ir a pagar por caja los siguientes comprobantes: " + Utils.toStringArray(actForm.getCodigos()));
					boolean result = generarPDF(actForm, response, session);
					tx.commit();
					if (result)
						return null;
					logger.debug("\n\n\nERROR en generacion pdf");
					return mapping.findForward("error");
				} else if (operacion.equals("SPL") || Utils.codificaAcentos(operacion).equals(Constants.TXT_BTNS.getProperty("pagoLinea")) || operacion.equals(Constants.TXT_BTNS.getProperty("pagoLinea")))
				{
					if (actForm.getCodigoBarra() != null && !actForm.getCodigoBarra().equals(""))
					{
						actForm.setCodigos(new String[1]);
						actForm.getCodigos()[0] = Long.toString(Long.parseLong(actForm.getCodigoBarra()));
					} else if (actForm.getConsulta() != null && actForm.getConsulta().size() > 0)
					{
						actForm.setCodigos(new String[actForm.getConsulta().size()]);
						int i = 0;
						for (Iterator it = actForm.getConsulta().iterator(); it.hasNext();)
							actForm.getCodigos()[i++] = Long.toString(((LineaComprobantesAPagar) it.next()).getCodigoBarra());						
					}
					ActionForward result = pagar(mapping, request, actForm, user, session);
					tx.commit();
					return result;
				} else if (operacion.equals(Constants.TXT_BTNS.getProperty("pagarTodos")) || Utils.codificaAcentos(operacion).equals(Constants.TXT_BTNS.getProperty("pagarSeleccionados")))
				{
					// Usuario presiono uno de los botones "PAGAR SELECCIONADAS" o "PAGAR TODAS"
					if (operacion.equals(Constants.TXT_BTNS.getProperty("pagarTodos")))
					{
						String tipo = request.getParameter("tipoNomina");
						ConvenioVO convenioVo;

						//GMALLEA 08-03-2012 Filtar las empresas, deja solo las Independientes
						List empresasList = empresaMgr.getListTipoEmpresas(usuarioCP.getUnionEmpresasLectura(), Constants.TIPO_EMPRESA);
						Set empresasLectura = new HashSet();
						
						for(Iterator it =  empresasList.iterator(); it.hasNext() ; ){
								 EmpresaVO emp =(EmpresaVO)	it.next();
								 empresasLectura.add(new Integer(emp.getIdEmpresa()));
						}
						
						
						//Obtiene los convenios de escritura
						List conveniosEscritura = convenioMgr.getConveniosEscritura(((PersonaVO)user.getUserReference()).getIdPersona().intValue(), empresasLectura);

						//Va a buscar las nominas de los convenios
						NominaVO nomina;
						List codigosPagar = new ArrayList();
						for (Iterator it = conveniosEscritura.iterator(); it.hasNext();) 
						{
							convenioVo = (ConvenioVO) it.next();
							//Carga la nomina para este convenio
							nomina = procesoMgr.getNomina(tipo, convenioVo.getIdEmpresa(), convenioVo.getIdConvenio());
							//Filtra por estado si es distinto de todos
							if (nomina == null || nomina.getIdEstado() != Constants.EST_NOM_POR_PAGAR)
								continue;

							codigosPagar.add(String.valueOf(nomina.getIdCodigoBarras()));
						}
						Iterator t = codigosPagar.iterator();
						String []codes = new String[codigosPagar.size()];
						int i = 0;
						while(t.hasNext())
							codes[i++]=(String)t.next();
						actForm.setCodigos(codes);
					}//if de pagar todos
										
					List consulta = comprobanteMgr.construyeListaComprobantesAPagar(actForm.getCodigos());
					if (consulta != null)
					{
						long total = 0;
						LineaComprobantesAPagar lineaConsulta;
						for (Iterator it = consulta.iterator(); it.hasNext();)
						{
							lineaConsulta = (LineaComprobantesAPagar) it.next();
							lineaConsulta.setNombreConvenio(lineaConsulta.getNombreConvenio().trim());
							lineaConsulta.setRazonSocial(lineaConsulta.getRazonSocial().trim());
							total += lineaConsulta.getTotal();
						}

						if(total == 0)
						{
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.cmpsSinMontoAPagar"));
							this.saveMessages(request, am);	
						}
					} else
					{
						ActionRedirect redirect = new ActionRedirect(mapping.findForward(PAGADOS_CERO));
						redirect.addParameter("accion", "inicio");
						redirect.addParameter("subAccion", "procesos");
						redirect.addParameter("subSubAccion", "");
						redirect.addParameter("limpiaPath", "");

						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("aviso.cmpsPagados"));
						this.saveMessages(request.getSession(), am);
						tx.commit();

						return redirect;
					}
					logger.debug("Retorno CONSULTA: " + consulta);
					actForm.setConsulta(consulta);
					tx.commit();
					return mapping.findForward(PAGO_NOMINAS);
				}
			}

			List tiposNomina = new ArrayList();
			// Obtiene los tipos de nómina
			TipoNominaVO tipoNominaVo;
			for (Iterator it = procesoMgr.getTiposProceso().iterator(); it.hasNext();)
			{
				tipoNominaVo = (TipoNominaVO) it.next();
				tiposNomina.add(new LabelValueBean(tipoNominaVo.getDescripcion(), tipoNominaVo.getIdTipoNomina()));
			}

			if (codigoBarra == -1)
				actForm.setConsulta(null);
			else
			{
				ComprobanteVO cmp = comprobanteMgr.getCmpPorPagar(new Long(codigoBarra));

				if (cmp != null)
				{// Guarda los datos en el action form
					actForm.setCodigoBarra(Long.toString(codigoBarra));
					actForm.setConsulta(comprobanteMgr.construyeResumenComprobanteProvisorio(cmp));
					actForm.setTotal(cmp.getTotal());
					
					if(cmp.getTotal() == 0 && !cmp.tieneAlgoParaPago())
					{
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.cmpSinMontoAPagar"));
						this.saveMessages(request, am);	
					}
				} else
					actForm.setConsulta(null);
			}

			if (actForm.getConsulta() == null) 
			{
				HashMap combos = empresaMgr.getCombosPermisos(true, ((PersonaVO)user.getUserReference()).getIdPersona().intValue(), rut, convenio);
				if (combos == null)
				{
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("permisos.generic"));
					this.saveMessages(request, am);
					tx.commit();
					return mapping.findForward("error");
				}
				actForm.setEmpresas((List)combos.get("empresas"));
				actForm.setObjEmpresas((List)combos.get("objEmpresas"));
				EmpresaVO empresa = (EmpresaVO)combos.get("empresa");
				actForm.setNombreEmpresa(empresa.getRazonSocial());
				actForm.setRut("" + rut);
				actForm.setRutFmt(Utils.formatRut(empresa.getIdEmpresa()));
				actForm.setConvenio(Integer.toString(convenio));
				actForm.setTiposNomina(tiposNomina);
				actForm.setTipoNomina(tipoNomina);
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.comprobanteNoExiste"));
				this.saveMessages(request, am);
			} else
			{
				HashMap combos = empresaMgr.getCombosPermisos(false, ((PersonaVO)user.getUserReference()).getIdPersona().intValue(), rut, convenio);
				if (combos == null)
				{
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("permisos.generic"));
					this.saveMessages(request, am);
					tx.commit();
					return mapping.findForward("error");
				}
				actForm.setEmpresas((List)combos.get("empresas"));
				actForm.setObjEmpresas((List)combos.get("objEmpresas"));
				EmpresaVO empresa = (EmpresaVO)combos.get("empresa");
				actForm.setNombreEmpresa(empresa.getRazonSocial());
				actForm.setRut("" + rut);
				actForm.setRutFmt(Utils.formatRut(empresa.getIdEmpresa()));
				actForm.setConvenio(Integer.toString(convenio));
				actForm.setTiposNomina(tiposNomina);
				actForm.setTipoNomina(tipoNomina);
			}
			
			List listadoEmpresas = procesoMgr.getEmpresasAvisosErroresByConvenio(""+rut, actForm.getNombreEmpresa(), ""+actForm.getTipoNomina(), ""+convenio); 
			
			request.setAttribute("msnAvisos", listadoEmpresas);
			request.setAttribute("tipoNomina", ""+actForm.getTipoNomina());

			tx.commit();
			return mapping.findForward(RESUMEN); // viene desde resumen.jsp, apreto pago individual, en boton x filas
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en PagarAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}
	
	
	/**
	 * generar pdf
	 * @param form
	 * @param response
	 * @param session
	 * @return
	 */
	public boolean generarPDF(ActionForm form, HttpServletResponse response, Session session)
	{
		try
		{
			PagarActionForm formulario = (PagarActionForm) form;
			String[] listaCodigos = formulario.getCodigos();
			List listaCodBarras = new ArrayList();
			for (int i = 0; i < listaCodigos.length; i++)
				logger.info("i" + i + ":" + listaCodigos[i] + ":");// codigos de barras
			for (int i = 0; i < listaCodigos.length; i++)
				listaCodBarras.add(new Long(listaCodigos[i]));// codigos de barras

			ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);			
			
			String nombreArchivo = comprobanteMgr.generarArchivos(listaCodBarras);
			
			FileInputStream fis = new FileInputStream(nombreArchivo);
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=" + nombreArchivo);
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

	/**
	 * pagar
	 * @param mapping
	 * @param request
	 * @param form
	 * @param user
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public ActionForward pagar(ActionMapping mapping, HttpServletRequest request, ActionForm form, User user, Session session) throws Exception
	{logger.info("Entro a Pago en Linea PagarAction.java");
		PagarActionForm formulario = (PagarActionForm) form;
		String msg = "";
		PagoEnLinea pago = new PagoEnLinea();
		try
		{
			PagoEnLineaMgr pagoEnLineaMgr = new PagoEnLineaMgr(session);

			String[] listaCodigos = formulario.getCodigos();

			if (listaCodigos == null || listaCodigos.length == 0)
				msg = SIN_NOMINAS;
			else
			{
				List listaCodBarras = new ArrayList();
				for (int i = 0; i < listaCodigos.length; i++)
					listaCodBarras.add(new Long(listaCodigos[i]));// codigos de barras
				
				pago = pagoEnLineaMgr.preparaPago(user.getLogin(), listaCodBarras, session);

				if (pago == null)
					throw new Exception("problemas al preparar el pago");
				else if (pago.getResult()!= null && pago.getResult().equals("PAGADOS"))
				{
					ActionMessages am = new ActionMessages(); 
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("aviso.cmpPagado"));
					this.saveMessages(request.getSession(), am);
					return mapping.findForward(PAGADOS_CERO);
				}
				List listaParams = new ArrayList();
				listaParams.add(new Integer(Constants.PARAM_CLAVE_SPL));
				listaParams.add(new Integer(Constants.PARAM_COD_SISTEMA_SPL));
				listaParams.add(new Integer(Constants.PARAM_URL_SPL));
				ParametrosHash paramHash = pagoEnLineaMgr.getParametrosHash(listaParams);
				logger.info("Parsear los datos en XML PagarAction.java");
				String xml = pago.toXml();
				logger.info(xml);
				logger.info("Encriptar XML PagarAction.java");
				CryptResult resultadoEncriptacion = getEncriptaResult(paramHash.get("" + Constants.PARAM_CLAVE_SPL), xml, "ISO-8859-1");
				SimpleEncoder encoder = new SimpleEncoder();

				byte[] crypted = resultadoEncriptacion.getCrypted();
				String sCrypted = encoder.bytesToHex(crypted);
				byte[] ivector = resultadoEncriptacion.getIvector();
				String sIvector = encoder.bytesToHex(ivector);

				logger.info("sistema:" + paramHash.get("" + Constants.PARAM_COD_SISTEMA_SPL) + "::");
				logger.info("xml:" + sCrypted + "::");
				logger.info("vector:" + sIvector + "::");
				logger.info("UrlSPL:" + paramHash.get("" + Constants.PARAM_URL_SPL) + "::");
				logger.info("Redireccionar a SPL PagarAction.java");
				request.setAttribute("sistema", paramHash.get("" + Constants.PARAM_COD_SISTEMA_SPL));
				request.setAttribute("xml", sCrypted);
				request.setAttribute("vector", sIvector);
				request.setAttribute("urlSPL", paramHash.get("" + Constants.PARAM_URL_SPL));

				return mapping.findForward(PARAM_SEND);
			}
		} catch (Exception e)
		{
			logger.error("ERROR en PagarAction:", e);
			if (pago != null && !pago.getResult().equals(""))
			{
				Properties mensajesProps = new Properties();
				mensajesProps.load(getClass().getResourceAsStream("/araucana/PagoEnLinea/mensajes.properties"));
				msg = mensajesProps.getProperty(pago.getResult());
				logger.error("ERROR en PagarAction (problema en FOLIO):" + msg);
			}
		}
		MessageList l = new MessageList();
		l.add(new Message("Ha ocurrido un Error", msg));
		request.setAttribute("messageList", l);
		return mapping.findForward(PARAM_ERROR);
	}
	/**
	 * encripta result
	 * @param key
	 * @param plain
	 * @param charset
	 * @return
	 * @throws TripleDesEngineException
	 */
	public CryptResult getEncriptaResult(String key, String plain, String charset) throws TripleDesEngineException
	{
		TripleDesEngine cipher = new TripleDesEngine();
		return cipher.encrypt(key, plain, charset);
	}
}
