package cl.araucana.cp.presentation.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.core.util.PropertiesLoaderException;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAsFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoGeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMovimientoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ValidacionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.exceptions.SesionException;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.DispatcherMgr;
import cl.araucana.cp.presentation.mgr.EmpresaMgr;
import cl.araucana.cp.presentation.mgr.MapeosMgr;
import cl.araucana.cp.receipt.DesEncrypt;
/*
* @(#) ServletSendEnvAppMgr.java 1.11 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 *
 * @version 1.11
 */
public class ServletSendEnvApp extends HttpServlet
{
	private static final long serialVersionUID = 3216763556821098115L;
	static Logger logger = Logger.getLogger(ServletSendEnvApp.class);
	private HashMap grupos = new HashMap();
	/**
	 * get
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		logger.info("sendUserEnv");
		response.setContentType("text/plain");
		PrintWriter writer = response.getWriter();
		String sidParameter = request.getParameter("sid");
		if (sidParameter == null)
			throw new ServletException("No se recibio SID");
		try
		{
			//Obtiene el id del usuario que esta realizando el envio
			logger.info("\n\nPK para validar session activa:" + sidParameter + "::");
			String idPersona = new DesEncrypt().decodifica(sidParameter);
			logger.info("idPersona=" + idPersona + "::");
		} catch (SesionException e)
		{
			logger.debug("session expirada");
			request.getSession().invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("araucana/login/loginCPE.jsp");
			rd.forward(request, response);
			return;
		} catch (Exception e)
		{
			logger.debug("session expirada");
			request.getSession().invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("araucana/login/loginCPE.jsp");
			rd.forward(request, response);
			return;
		}

		Transaction tx = null;
		try
		{
			Session session = HibernateUtil.getSession("");
			String idPersona = new DesEncrypt().decodifica(request.getParameter("sid"));
			tx = session.beginTransaction();
			DispatcherMgr dispatcherMgr = new DispatcherMgr(session);

			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			ConvenioMgr convenioMgr = new ConvenioMgr(session);
			MapeosMgr mapeosMgr = new MapeosMgr(session);

			String period = dispatcherMgr.getParametro(Constants.PARAM_PERIODO_INDEPENDIENTE).getValor().trim();
			UsuarioCP usuarioCP = new UsuarioCP(Collections.singletonMap("login", (new Integer(idPersona)).toString()), session);

			writer.println("status=OK");
			writer.println("periodo=" + period.substring(0, 4) + ":" + period.substring(4));
			Collection tiposNominas = dispatcherMgr.getTiposNominas();
			String idsTiposNominas = "", listaTipos = "";
			for (Iterator it = tiposNominas.iterator(); it.hasNext();)
			{
				TipoNominaVO tn = (TipoNominaVO)it.next();
				idsTiposNominas += tn.getIdTipoNomina();
				listaTipos += tn.getIdTipoNomina() + "#" + tn.getDescripcion().trim() + "&";
			}
			//tiposNominas = idTipo'#'nombreTipo'&'
			writer.println("tiposNominas=" + listaTipos);
			writer.println("encargado=" + usuarioCP.getLogin() + ":" + URLEncoder.encode(((PersonaVO) usuarioCP.getUserReference()).getNameToShow(), "UTF-8"));
			writer.println("nMaxErrores=" + dispatcherMgr.getParametro(Constants.PARAM_NUM_MAX_ERRORES).getValor().trim());
			writer.println("numMaxRegistros=" + dispatcherMgr.getParametro(Constants.PARAM_NUM_MAX_REGISTROS).getValor().trim());

			List conveniosPermEsc = convenioMgr.getConveniosIn(usuarioCP.getConveniosEditorRetVO());
			List conveniosEmpsAdmins = convenioMgr.getConveniosEmpresasIn(usuarioCP.getEmpresasAdmin());
			Set conveniosTodos = new HashSet(CollectionUtils.union(conveniosPermEsc, conveniosEmpsAdmins));

			logger.info("\n\n\nconveniosPermEsc:" + conveniosPermEsc.size() + ":conveniosEmpsAdmins:" + conveniosEmpsAdmins.size() + ":conveniosTodos:" + conveniosTodos.size() + "::");
			validaMapaNominas(idsTiposNominas, convenioMgr, conveniosTodos);

			logger.info("conveniosTodos:" + conveniosTodos.size() + "::");
			Map mapaEmpresaConvenios = new HashMap();
			ConvenioVO conv;
			for (Iterator it = conveniosTodos.iterator(); it.hasNext();)
			{
				conv = (ConvenioVO) it.next();
				if (!mapaEmpresaConvenios.keySet().contains(new Integer(conv.getIdEmpresa())))
					mapaEmpresaConvenios.put(new Integer(conv.getIdEmpresa()), new LinkedList());
				((List) mapaEmpresaConvenios.get(new Integer(conv.getIdEmpresa()))).add(conv);
			}
			writer.println("nEmpresas=" + mapaEmpresaConvenios.size());
			logger.debug("nEmpresas=" + mapaEmpresaConvenios.size());

			List listaConv;
			Integer idEmp;
			String l;
			int iEmp = 1;
			Set idsGCPresentes = new HashSet();
			//'E'N=RUT:NOMBRE:numConvenios:idConvenioN#idGrupoConvenioN, ''= literal
			for (Iterator it = mapaEmpresaConvenios.keySet().iterator(); it.hasNext();) {
				idEmp = (Integer) it.next();
				listaConv = (List) mapaEmpresaConvenios.get(idEmp);
				l = "E" + iEmp++ + "=" + idEmp.toString() + ":"
					+ empresaMgr.getEmpresa(idEmp.intValue()) .getRazonSocial().trim()
					+ ":" + listaConv.size();
				for (Iterator itP = listaConv.iterator(); itP.hasNext();) {
					conv = (ConvenioVO) itP.next();
					l += ":" + conv.getIdConvenio() + "#" + conv.getIdGrupoConvenio();
					idsGCPresentes.add(new Integer(conv.getIdGrupoConvenio()));
				}
				writer.println(l);
				logger.debug(l);
			}

			char[] tipoProceso = idsTiposNominas.toCharArray();
			//'V'TPN=idValidacion#clase$idSiguiente1#valorRespuesta1&idSiguiente2#valorRespuesta2$idConcepto1&idConcepto2&$
			for (int j = 0; j < tipoProceso.length; j++)
			{
				String tp = "" + tipoProceso[j];
				List listaValidaciones = dispatcherMgr.getListaValidaciones(tp);
				writer.println("nV" + tp + "=" + listaValidaciones.size());
				logger.debug("nV" + tp + "=" + listaValidaciones.size());
				int i = 1;
				for (Iterator it = listaValidaciones.iterator(); it.hasNext();)
				{
					ValidacionVO v = (ValidacionVO)it.next();
					logger.debug("V" + tp + i + "=" + v.toLine());
					writer.println("V" + tp + i++ + "=" + v.toLine());
				}
			}
			for (int j = 0; j < tipoProceso.length; j++)
			{
				String tp = "" + tipoProceso[j];
				//'C'TPN=idConcepto#obligatorio#nombre
				List listaConceptos = dispatcherMgr.getListaConceptos(tp);
				//List listaMapeo = dispatcherMgr.getListaMapeo(tp, idsGCPresentes);
				writer.println("nC" + tp + "=" + listaConceptos.size());
				logger.debug("nC" + tp + "=" + listaConceptos.size());
				int i = 1;
				for (Iterator it = listaConceptos.iterator(); it.hasNext();)
				{
					ConceptoVO c = (ConceptoVO) it.next();
					logger.debug("C" + tp + i + "=" + c.toLine());
					writer.println("C" + tp + i++ + "=" + c.toLine());
				}
			}

			List listaGrupos = dispatcherMgr.getListaGrupos(idsGCPresentes);
			List listaMapeos = dispatcherMgr.getListaMapeo(listaGrupos);
			List gc = new ArrayList();
			Set listaGruposCodigoVacio = new HashSet();

			//'GC'TP'='idGrupo:idConcepto1'#'posicion1'#'largo1'$'idConcepto2'#'posicion2'#'largo2'$'
			//'GC'TP'='idGrupo:idConcepto1'#'posicion1'#'largo1'#'tipoSeparacion1'#'caracterSeparador1'$'idConcepto2'#'posicion2'#'largo2#'tipoSeparacion2'#'caracterSeparador2'$'
			for (Iterator it = listaGrupos.iterator(); it.hasNext();)
			{
				GrupoConvenioVO grupo = (GrupoConvenioVO)it.next();
				//TODO SÓLO PARA PRUEBAS
				/*if (grupo.getIdGrupoConvenio() == 114)
					this.grupos.put("" + grupo.getIdGrupoConvenio(), new Boolean(true));*/
				for (int j = 0; j < tipoProceso.length; j++)
				{
					char tp = tipoProceso[j];
					StringBuffer mapeo = getMapaStr(tp, grupo.getIdMapaNom(tp), listaMapeos);
					if (mapeo.length() > 0)
						gc.add("GC" + tp + "=" + grupo.getIdGrupoConvenio() + ":" + mapeo.toString());
					if (!esMapaCodigoValido(grupo, mapeosMgr))
						listaGruposCodigoVacio.add(grupo);
				}
			}

			writer.println("nGC=" + gc.size());
			for (Iterator it = gc.iterator(); it.hasNext();)
			{
				String linea = (String) it.next();
				writer.println(linea);
				logger.debug(linea);
			}

			/*Iterator iter = gc.iterator();
			while (iter.hasNext())
				System.out.println(iter.next());*/

			//Grupos de convenios con codigos invalidos
			//'COD0GC='idGrupo1'#'idGrupo2
			GrupoConvenioVO grupo;
			String sep = "";
			writer.print("COD0GC=");
			for (Iterator it = listaGruposCodigoVacio.iterator(); it.hasNext();)
			{
				grupo = (GrupoConvenioVO) it.next();
				writer.print(sep + grupo.getIdGrupoConvenio());
				sep = "#";
			}
			writer.println("");
			tx.commit();

		} catch (PropertiesLoaderException e)
		{
			writer.println("status=NO USER DATA");
		} catch (Exception e)
		{
			logger.error("problemas enviando ambiente", e);
			writer.println("status=" + e.getMessage());
		}
		writer.close();
	}

	/**
	 * Deja en conveniosTodos solo los convenios habilitados y con sus mapeos de nominas bien configurados
	 * @param idsTiposNominas String con los tipos de nominas a procesa, ej: RGAD
	 * @param convenioMgr
	 * @param conveniosTodos lista de convenios a revisar
	 */
	private void validaMapaNominas(String idsTiposNominas, ConvenioMgr convenioMgr, Set conveniosTodos)
	{
		HashMap validacionGrupos = new HashMap();
		Set conveniosDeshabilitados = new HashSet();
		ConvenioVO convenio;
		for (Iterator it = conveniosTodos.iterator(); it.hasNext();)
		{
			convenio = (ConvenioVO) it.next();
			if (convenio.getHabilitado() == 0)
			{
				logger.debug("\tconv desabilitado:" + convenio.getIdConvenio() + "::");
				conveniosDeshabilitados.add(convenio);
			}
			if (validacionGrupos.containsKey("" + convenio.getIdGrupoConvenio()))
			{
				Boolean validado = (Boolean)validacionGrupos.get("" + convenio.getIdGrupoConvenio());
				if (!validado.booleanValue())//si la configuracion del grupo es invalido
					conveniosDeshabilitados.add(convenio);
			} else
			{
				boolean result = convenioMgr.validaMapaNomGrupo(convenio.getIdGrupoConvenio(), idsTiposNominas);
				validacionGrupos.put("" + convenio.getIdGrupoConvenio(), new Boolean(result));
				if (!result)//si la configuracion del grupo es invalido
				{
					logger.info("quitando convenio:" + convenio.getIdEmpresa() + "::" + convenio.getIdConvenio() + ":grupo:" + convenio.getIdGrupoConvenio() + "::");
					conveniosDeshabilitados.add(convenio);
				}
			}
		}
		conveniosTodos.removeAll(conveniosDeshabilitados);
	}

	/**
	 * mapa codigo valido
	 * @param grupoConvenio
	 * @param mapeosMgr
	 * @return verdadero si se trata de un mapa de codigos bien configurado, falso en caso contrario
	 * @throws DaoException
	 */
	private boolean esMapaCodigoValido(GrupoConvenioVO grupoConvenio, MapeosMgr mapeosMgr) throws DaoException
	{
		if (!this.grupos.containsKey("" + grupoConvenio.getIdGrupoConvenio()))
		{
			List[] mapeosCods = {
				mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoPensionVO.class, EntidadPensionVO.class),
				mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoSaludVO.class, EntidadSaludVO.class, Constants.ID_SALUD_NINGUNA),
				mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAPVVO.class, EntidadApvVO.class, Constants.SIN_APV),
				mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoTipoMovtoVO.class, TipoMovimientoPersonalVO.class),
				mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoTipoMovtoAFVO.class, TipoMvtoPersoAFVO.class),
				mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoGeneroVO.class, GeneroVO.class, Constants.GENERO_FALSO),
				mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAsFamVO.class, AsigFamVO.class, Constants.TRAMO_ASIGFAM_FALSO)
			};

			for (int i = 0; i < mapeosCods.length; i++)
			{
				for (Iterator it = mapeosCods[i].iterator(); it.hasNext();)
				{
					MapeoVO mapeo = (MapeoVO) it.next();
					if (mapeo.getCodigo().trim().equals(""))
					{
						this.grupos.put("" + grupoConvenio.getIdGrupoConvenio(), new Boolean(false));
						logger.info("\n\nrechazando grupo:" + grupoConvenio.getIdGrupoConvenio() + ":i:" + i + ":id mapa:" + mapeo.getId() + "::");
						return false;
					}
				}
			}
			this.grupos.put("" + grupoConvenio.getIdGrupoConvenio(), new Boolean(true));
			return true;
		}
		return ((Boolean)this.grupos.get("" + grupoConvenio.getIdGrupoConvenio())).booleanValue();
	}

	/**
	 * mapa str
	 *
	 * jlandero
	 * 21/02/2011 Se agrega a la cadena el tipo de separación y el caracter separador
	 *
	 * @param tp
	 * @param idMapa
	 * @param listaMapeos
	 * @return cadena de caracteres con la representacion del mapeo de conceptos recibido: idConcepto + '#' + posicion + '#' + largo + '#' + tipoSeparacion + '#' + separador
	 */
	private StringBuffer getMapaStr(char tp, int idMapa, List listaMapeos)
	{
		StringBuffer sb = new StringBuffer();
		for (Iterator it = listaMapeos.iterator(); it.hasNext();)
		{
			MapeoConceptoVO mapeo = (MapeoConceptoVO)it.next();
			if (mapeo.getIdMapa() == idMapa && mapeo.getTipoProceso() == tp)
				//sb.append(mapeo.getIdConcepto() + "#" + mapeo.getPosicion() + "#" + mapeo.getLargo() + "$");
				sb.append(mapeo.getIdConcepto()		+ "#" +
						  mapeo.getPosicion()		+ "#" +
						  mapeo.getLargo()			+ "#" +
						  mapeo.getTipoSeparador()	+ "#" +
						  (mapeo.getCaracterSeparador()==null?"X":mapeo.getCaracterSeparador().toString())	+ "$"); //La X es sólo para no enviar "null" como String
		}
		return sb;
	}
}
