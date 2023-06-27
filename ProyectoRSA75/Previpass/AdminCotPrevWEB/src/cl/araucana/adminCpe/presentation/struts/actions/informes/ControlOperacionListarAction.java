package cl.araucana.adminCpe.presentation.struts.actions.informes;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
import cl.araucana.adminCpe.presentation.mgr.EnvioMgr;
import cl.araucana.adminCpe.presentation.mgr.NodoMgr;
import cl.araucana.adminCpe.presentation.mgr.NominaMgr;
import cl.araucana.adminCpe.presentation.mgr.ParametroMgr;
import cl.araucana.adminCpe.presentation.mgr.PersonaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.informes.ControlOperacionListarActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaControlOperacion;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EnvioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;

import com.bh.talon.User;

/*
 * @(#) ControlOperacionListarAction.java 1.1 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * 
 * @version 1.1
 */
public class ControlOperacionListarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ControlOperacionListarAction.class);

	/**
	 * control operacion listar
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ControlOperacionListarActionForm actForm = (ControlOperacionListarActionForm) form;

		Session session = null;
		Transaction tx = null;
		try
		{
			String accionInterna = request.getParameter("accionInterna");
			if (accionInterna != null)
			{
				session = HibernateUtil.getSession();
				tx = session.beginTransaction();

				LineaControlOperacion lineaControlOperacion;

				NominaMgr nominaMgr = new NominaMgr(session);
				ConvenioMgr convenioMgr = new ConvenioMgr(session);
				EmpresaMgr empresaMgr = new EmpresaMgr(session);
				PersonaMgr personaMgr = new PersonaMgr(session);
				EnvioMgr envioMgr = new EnvioMgr(session);
				NodoMgr nodoMgr = new NodoMgr(session);
				ParametroMgr parametroMgr = new ParametroMgr(session);

				int rutEmpresa = 0;
				List listaConvenio = null;
				if (accionInterna.equals("EMPRESA"))
				{
					if (request.getParameter("rutEmpresa") != null)
					{
						String rutTmp = request.getParameter("rutEmpresa").trim();
						rutTmp = rutTmp.replaceAll("\\.", "");

						int pos = rutTmp.indexOf('-');
						if (pos > -1)
							rutTmp = rutTmp.substring(0, pos);
						rutEmpresa = Integer.parseInt(rutTmp);
						logger.info("RUT:" + rutEmpresa);
						listaConvenio = convenioMgr.getConveniosEmpresa(rutEmpresa);
					}
				}
				if (accionInterna.equals("GRUPO"))
				{
					String idGrupo = request.getParameter("grupo");
					if (idGrupo != null && !idGrupo.trim().equals(""))
						listaConvenio = convenioMgr.getConveniosGrupo(Integer.parseInt(idGrupo.trim()));
					else
						return mapping.findForward("exito");
				}
				if (listaConvenio != null && listaConvenio.size() > 0)
				{
					actForm.setLista(new ArrayList());
					List tiposNomina = nominaMgr.getTiposNomina();
					HashMap factores = parametroMgr.getFactores(tiposNomina);
					for (Iterator itGrupo = listaConvenio.iterator(); itGrupo.hasNext();)
					{
						ConvenioVO convenioVO = (ConvenioVO) itGrupo.next();
						rutEmpresa = convenioVO.getIdEmpresa();
						EmpresaVO empresaVO = empresaMgr.getEmpresa(rutEmpresa);
						String razonSocial = empresaVO.getRazonSocial();
						String rutFormat = Utils.formatRut(rutEmpresa);
						//int idEncargado = 0;
						int idGrupoConvenio = convenioVO.getIdGrupoConvenio();
						GrupoConvenioVO grupoConvenioVO = convenioMgr.getGrupoConvenio(idGrupoConvenio);
						String grupoConvenio = grupoConvenioVO.getNombre();

						for (Iterator it = tiposNomina.iterator(); it.hasNext();)
						{
							TipoNominaVO tn = (TipoNominaVO)it.next();
							char tipo = tn.getIdTipoNomina().charAt(0);
							NominaVO nomina = nominaMgr.getNomina(tn.getIdTipoNomina(), rutEmpresa, convenioVO.getIdConvenio());

							if (nomina != null)
							{
								lineaControlOperacion = new LineaControlOperacion();
								lineaControlOperacion.setGrupoConvenio(grupoConvenio);
								lineaControlOperacion.setRutEmpresa(rutFormat);
								lineaControlOperacion.setRazonSocial(razonSocial);

								EnvioVO envioVO = envioMgr.getEnvio(nomina.getIdEnvio());
								if (envioVO != null)
								{
									NodoVO nodoVO = nodoMgr.getNodo(envioVO.getIdNodo());

									lineaControlOperacion.setCantidadEnvios(nomina.getNumReenvios());
									//envioMgr.getCantidadEnviadas(tipo, rutEmpresa, idConvenio));
									SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
									lineaControlOperacion.setFecha(formatoFecha.format(envioVO.getRecibido()));
									formatoFecha = new SimpleDateFormat("HH:MM:ss:SS");
									lineaControlOperacion.setHoraInicio(formatoFecha.format(envioVO.getRecibido()));
									formatoFecha = new SimpleDateFormat("HH:MM:ss:SS");
									Timestamp tiempoTermino = new Timestamp(envioVO.getRecibido().getTime() + envioVO.getDuracion());
									lineaControlOperacion.setHoraTermino(formatoFecha.format(tiempoTermino));
									lineaControlOperacion.setTiempoTotal(String.valueOf(envioVO.getDuracion()) + " ms.");
									lineaControlOperacion.setNodo(nodoVO.getDescripcion().trim());
									lineaControlOperacion.setFactorTipo(factores.containsKey("" + tipo) ? (String)factores.get("" + tipo) : "");
									lineaControlOperacion.setNumeroRegistros(nomina.getNumCotizaciones());
									//envioMgr.getNumRegistros(tipo, rutEmpresa, idConvenio, nomina.getIdEnvio()));

									PersonaVO personaVO = personaMgr.getPersona(envioVO.getIdEncargado());
									if (personaVO != null)
										lineaControlOperacion.setUsuario(personaVO.getNombres() + " " + personaVO.getApellidoPaterno() + " " + personaVO.getApellidoMaterno());
									
									actForm.getLista().add(lineaControlOperacion);
								}
							}						
						}
					}
				}
				tx.commit();
			}
			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListaEstructuraMovPersonalAction.doTask()", ex);
			if (tx != null)
			{
				tx.rollback();
			}
			return mapping.findForward("error");
		}
	}

}
