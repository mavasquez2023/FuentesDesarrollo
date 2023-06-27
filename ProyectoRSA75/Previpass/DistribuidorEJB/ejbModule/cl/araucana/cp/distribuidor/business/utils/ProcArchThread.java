package cl.araucana.cp.distribuidor.business.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.business.validaciones.Validacion;
import cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EventoCargaValidacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoSiguienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ValidacionVO;
import cl.araucana.cp.distribuidor.hibernate.dao.CotizanteDAO;
import cl.araucana.cp.distribuidor.hibernate.dao.NominaDAO;
import cl.araucana.cp.distribuidor.hibernate.dao.ParametrosDAO;
import cl.araucana.cp.distribuidor.hibernate.dao.ValidacionDAO;
import cl.araucana.cp.distribuidor.hibernate.utils.HibernateUtil;

/**
 * 
 * @author Cristóbal
 *
 */
public class ProcArchThread extends Thread {	
	protected static Logger logger = Logger.getLogger(ProcArchThread.class);
	private Session session; 
	private ConvenioVO convenio;//, convenioMod; // se usa segundo convenio, uno con datos reales y otro con un merge de los datos de grupoConvenio a usar
	private GrupoConvenioVO grupoConvenio;
	private ValidacionDAO validacionDao;
	private ParametrosDAO parametrosDao;
	private NominaDAO nominaDao; 

	private HashMap causaErrores = new HashMap();		// se guardan solo los tipoCausa que nivelError sea: 'error'
	private HashMap causaAvisos = new HashMap();		// se guardan solo los tipoCausa que nivelError sea: 'aviso'
	private HashMap listaMensajesValidacion = new HashMap();// se guardan los mensajes generados desde el validador.
	private HashMap listaCausaAviso = new HashMap();
	
	private List listaValidaciones = new Vector(); // listas en uso en cada ciclo

	private NominaVO nomina = null;

	private int posSiguiente = 0;
	private  HashMap nominas;
	
	private char tipoProceso;
	private HashMap parametrosNegocio;
	
	private String idPersona;
	
	/** Asigna las conexiones respectivas*/
	public ProcArchThread (char tipoProcesoP, HashMap nominasP, HashMap parametrosNegocioP, String idPersonaP,Session sessionP,ValidacionDAO validacionDaoP,ParametrosDAO parametrosDaoP,
			GrupoConvenioVO grupoConvenioP,ConvenioVO convenioP) {
		this.nominas = nominasP;
		this.session = sessionP;
		this.tipoProceso = tipoProcesoP;
		this.parametrosNegocio = parametrosNegocioP;
		this.grupoConvenio = grupoConvenioP;
		this.convenio = convenioP;
		this.idPersona = idPersonaP;
		this.validacionDao = validacionDaoP;
		this.parametrosDao = parametrosDaoP;
		this.nominaDao = new NominaDAO(this.session);
	} 

    public void run() { 
    	Transaction tx = null;
    	try { 
    		tx = this.session.beginTransaction();
    		//this.session.beginTransaction();
    		logger.info(">>>>>>>>>>>>>>>>>>>>>>> ENTRE A ProcesarArchivoThread");
			List result = new ArrayList();
			List cotizantes = new ArrayList();
			//validacionDao = new ValidacionDAO(session);
			logger.info(">>>>>>>>>>>>>>>>>>>>>>> Creo validacionDao");
			this.causaErrores = this.validacionDao.getTiposCausasErr();
			logger.info(">>>>>>>>>>>>>>>>>>>>>>> Obtengo causaErrores");
			this.causaAvisos = this.validacionDao.getTiposCausasWarn();
			logger.info(">>>>>>>>>>>>>>>>>>>>>>> Obtengo causaAvisos");
			this.listaValidaciones = this.validacionDao.getListaValidaciones("" + this.tipoProceso);
			logger.info(">>>>>>>>>>>>>>>>>>>>>>> Obtengo listaValidaciones");
			
			CotizanteVO cotizante;
			EmpresaVO empresa;
			OpcionProcVO opcion;

			HashMap listaAvisos;
			HashMap hashCargasFamiliares;

			String rutConvenio = "";
			int idConvenio = -1;
			int rutEmpresa = -1;
			logger.info(">>>>>>>>>>>>>>>>>>>>>>> ANtes de  cotizanteDAO");
			CotizanteDAO cotizanteDAO = new CotizanteDAO(this.session);
			logger.info(">>>>>>>>>>>>>>>>>>>>>>> Despues de  cotizanteDAO");
			this.parametrosNegocio.put("rutEspeciales", this.parametrosDao.getRutsEspeciales());

			//Solo para pruebas de estres
			//java.util.Date stressInicio = new java.util.Date();
			java.util.Date stressIniTmp = new java.util.Date();
			java.util.Date stressFinTmp = new java.util.Date();

			logger.info("\nInicio validación VNs Cargas Familiares:" + stressIniTmp.getTime() + "::");

			for (Iterator it = this.nominas.keySet().iterator(); it.hasNext(); ) {
				//TODO PRUEBA!
				//this.session.beginTransaction();

				rutConvenio = String.valueOf(it.next());
				String[] elementos = rutConvenio.split(";");

				rutEmpresa = Integer.parseInt(elementos[0]);
				idConvenio = Integer.parseInt(elementos[1]);

				cotizantes = cotizanteDAO.getCotizantesNomina(rutEmpresa, idConvenio, this.tipoProceso);

				this.convenio = this.validacionDao.getConvenio(idConvenio, rutEmpresa);
				this.nomina = this.validacionDao.getNomina(this.tipoProceso, rutEmpresa, idConvenio);

				//nomina.setIdEstado(Constants.EST_NOM_EN_EJB);
				//session.update(nomina);
				//session.flush();
				int numeroValidaciones=0;
				if (cotizantes != null || cotizantes.size() > 0) {
					for (Iterator it2 = cotizantes.iterator(); it2.hasNext() && numeroValidaciones <= 100;) {

						cotizante = (CotizanteVO) it2.next();

						this.grupoConvenio = this.validacionDao.getGrupoConvenio(this.convenio.getIdGrupoConvenio());
						//this.convenioMod = new ConvenioVO(this.convenio, this.grupoConvenio);
						empresa = this.validacionDao.getEmpresa(cotizante.getRutEmpresa());
						opcion = this.validacionDao.getOpcionProcesos(this.convenio.getIdOpcion()); //this.validacionDao.getOpcionProcesos(this.convenioMod.getIdOpcion());

						this.parametrosNegocio.put("grupoConvenio", this.grupoConvenio);
						this.parametrosNegocio.put("empresaPrivada", empresa.getPrivada());
						this.parametrosNegocio.put("opcionProcesos", opcion);

						this.validacionDao.borraAvisosCargas(this.tipoProceso, rutEmpresa, idConvenio, cotizante.getIdCotizante());

						listaAvisos = new HashMap();

						if (this.listaValidaciones.size() > 0) {
							ValidacionVO siguiente = this.getPrimeraValidacionCargas();
							hashCargasFamiliares = this.getHashCargasPorEmpresa(rutEmpresa, this.convenio.getIdCcaf());

							while (siguiente != null) {
								Class validacion = Class.forName(siguiente.getClaseValidador().trim());
								Class[] argTypes = { HashMap.class, Session.class, ConvenioVO.class, HashMap.class};
								//Object[] argValues = { parametrosNegocio, this.session, this.convenioMod, hashCargasFamiliares };
								Object[] argValues = { this.parametrosNegocio, this.session, this.convenio, hashCargasFamiliares };
								Validacion valida = (Validacion) validacion.getConstructor(argTypes).newInstance(argValues);
								int codResult = valida.validaFromWEB(cotizante);
								if (codResult != 0 && isValidable(codResult)) {

									if (codResult == Constants.CAIDA_SISTEMA || codResult == Constants.SIN_CONCEPTOS)
										codResult = Constants.INICIO_COD_ERROR_GENERICO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));

									String mensaje = ((valida.getMensaje() == null || valida.getMensaje().trim().equals("")) ? siguiente.getIdValidacion() : valida.getMensaje());
									this.listaMensajesValidacion.put(String.valueOf(codResult), mensaje);
									logger.info("\n\n\n Validación incorrecta!:" + siguiente.getIdValidacion() + ":código error:" + codResult + "::");
									result.add(new Integer(codResult));
									
									numeroValidaciones++;
									logger.info("ProcesarArchivoThread: guardaCausaAviso: N:" + this.listaCausaAviso.size());

								}/* else
									logger.debug("Validación correcta!:" + siguiente.getIdValidacion() + "::");*/
								if (siguiente.getIdValidacion().trim().equals(Constants.ULTIMA_VALIDACION_CARGASFAM) || valida.getResultado().trim().equals("E"))
									siguiente = null;
								else
									siguiente = getSiguienteValidacion(valida.getResultado(), siguiente.getSiguientes(), this.listaValidaciones);
							}
							hashCargasFamiliares.clear();
						}

						int idCotizante = cotizante.getIdCotizante();
						List avisosRegistrados = new ArrayList();

						//Se busca el ID_CAUSA máximo de los registros de CAUSAAVISOCXP para no pisar los existentes
						int contador = this.validacionDao.getMaxIdCausaAviso(this.tipoProceso, rutEmpresa, idConvenio, cotizante.getIdCotizante()) + 1;

						for (Iterator i = result.iterator(); i.hasNext();) {
							Integer valor = (Integer) i.next();

							if (!inCausaAviso("" + valor.intValue(), avisosRegistrados)) {
//								clillo 3-12-14 por Reliquidación
								//CausaVO cp = new CausaVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), idCotizante, contador, valor.intValue(), listaAvisos.get(valor) + ", RUT:" + idCotizante);
								CausaVO cp = new CausaVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), idCotizante, contador, valor.intValue(), listaAvisos.get(valor) + ", RUT:" + idCotizante, cotizante.getPeriodo());
								avisosRegistrados.add(transformCausaAviso(this.tipoProceso, cp));
								contador++;
							}
							if (contador > 1)
								cotizante.setTieneAviso(1);
							this.listaCausaAviso.put("" + idCotizante, avisosRegistrados);
						}

						this.validacionDao.guardaCausaAviso(this.listaCausaAviso, cotizante);

						/*if (result.size() > 0) {
							HashMap _result = new HashMap();
							for (Iterator i = result.iterator(); i.hasNext();) {
								Integer tmp = (Integer) i.next();
								if (!_result.containsKey(String.valueOf(tmp)) && isAviso(tmp.intValue()))
									_result.put(String.valueOf(tmp), cotizante.getIdCotizante() + ":");
							}
							try {
								numeroValidaciones = numeroValidaciones +1;
								addCausaAviso("" + cotizante.getIdCotizante(), cotizante.getIdCotizante(), _result, cotizante, tipoProceso);
								if (numeroValidaciones>100) break;
							} catch (Exception e) {}
							logger.info("ProcesarArchivoThread: guardaCausaAviso: N:" + listaCausaAviso.size());
							//resultado.put(rutConvenio, new Boolean(true));
						}*/

						result.clear();
					}//FIN FOR COTIZANTES
				}//FIN IF COTIZANTES

				int idCaja = this.convenio.getIdCcaf();
				EntidadCCAFVO entidadCCAF = this.validacionDao.getEntidadCCAF(idCaja);

				EventoCargaValidacionVO evento = new EventoCargaValidacionVO();
				evento.setAccion("V");
				if (this.listaCausaAviso.size() > 0)
					evento.setComentarios("Proceso: " + this.tipoProceso + ", Empresa: " + rutEmpresa + ", Convenio: " + idConvenio + ". Validación: ERRONEA");
				else
					evento.setComentarios("Proceso: " + this.tipoProceso + ", Empresa: " + rutEmpresa + ", Convenio: " + idConvenio + ". Validación: CORRECTA");

				evento.setFecha(new java.sql.Date(new java.util.Date().getTime()));
				evento.setHora(new java.sql.Timestamp(new java.util.Date().getTime()));
				evento.setRutCaja(entidadCCAF.getIdEntPagadora());
				evento.setUsuario(this.idPersona + "-" + String.valueOf(Utils.generaDV(Integer.parseInt(this.idPersona))));

				this.validacionDao.guardaEventoCargaValidacion(evento);

				cotizantes = null;
				this.listaCausaAviso.clear();
				//PRUEBA
				//HibernateUtil.enableInterceptor();
				
				this.nomina.setIdEstado(Constants.EST_NOM_POR_PAGAR);
				this.session.update(this.nomina);
				this.session.flush();
				
				//PRUEBA
				//this.session.getTransaction().commit();
				logger.info(">>>>>>>>>>>>>>>>>>>>>>> TERMINA NOMINA");
			}// FIN FOR NOMINAS
			
			//this.session.getTransaction().commit();
			stressFinTmp = new java.util.Date();
			logger.info("\n\nFin validación VNs Cargas Familiares:diff:" + (stressFinTmp.getTime() - stressIniTmp.getTime()) + "::" + stressFinTmp.getTime() + "::");
			//logger.info(">>>>>>>>>>>>>>>>>>>>>>> ANTES DE COMMIT");
			tx.commit();
			
			//return resultado;
		}catch (Exception e)
		{
			logger.error("ProcesarArchivoThread:validaCargas:ERROR:", e);
			
			logger.error("ERROR en validaCotizante EJB:", e);
		} finally{
			//this.session.close();
		}
    }
    
	private ValidacionVO getPrimeraValidacionCargas()
	{
		for (Iterator it = this.listaValidaciones.iterator(); it.hasNext();)
		{
			ValidacionVO val = (ValidacionVO) it.next();
			if (val.getIdValidacion().trim().equals(Constants.PRIMERA_VALIDACION_CARGASFAM))
				return val;
		}
		return null;
	}
	
	private HashMap getHashCargasPorEmpresa(int rutEmpresa, int idCcaf)
	{
		HashMap hashCargasFamiliares = new HashMap();
		String keyHashMap;

		List cargasFamiliares = this.nominaDao.getCargasFamiliaresPorEmpresa(rutEmpresa, idCcaf);

		for (Iterator itCargas = cargasFamiliares.iterator(); itCargas.hasNext();) {
			CargaFamiliarVO carga = (CargaFamiliarVO) itCargas.next();
			keyHashMap = carga.getIdEntidadCCAF() + ";" + carga.getRutEmpresa() + ";" + carga.getRutTrabajador();
			hashCargasFamiliares.put(keyHashMap, carga);
		}

		return hashCargasFamiliares;
	}
	
	private boolean isValidable(int id)
	{
		if (this.causaErrores.containsKey("" + id) || this.causaAvisos.containsKey("" + id))
			return true;
		return false;
	}

	private ValidacionVO getSiguienteValidacion(String valor, List listaSiguientes, List lValidaciones)
	{
		for (Iterator it = listaSiguientes.iterator(); it.hasNext();)
		{
			NodoSiguienteVO ns = (NodoSiguienteVO) it.next();
			if (ns.getValor().trim().equals(valor))
			{
				if (this.posSiguiente > lValidaciones.size())
					this.posSiguiente = 0;
				int contador = this.posSiguiente;
				for (Iterator it2 = lValidaciones.listIterator(this.posSiguiente); it2.hasNext();)
				{
					ValidacionVO v = (ValidacionVO) it2.next();
					if (ns.getIdSiguiente().equals(v.getIdValidacion()))
					{
						this.posSiguiente = ++contador;
						//logger.debug("getSiguienteValidacion: encontrado:" + v.getIdValidacion() + "::");
						return v;
					}
					contador++;
				}
				if (this.posSiguiente > 0)// no lo encontró
				{
					this.posSiguiente = 0;
					return getSiguienteValidacion(valor, listaSiguientes, lValidaciones);
				}
			}
		}
		return null;
	}
	
	private boolean inCausaAviso(String valor, List lista)
	{
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			CausaVO _causa = (CausaVO) it.next();
			if (valor.equals("" + _causa.getIdTipoCausa()))
				return true;
		}
		return false;
	}

	private CausaVO transformCausaAviso(char tipoProceso, CausaVO cp)
	{
		String msg = cp.getTexto();
		if (msg != null)
		{
			if (msg.length() > 80)
				msg = msg.substring(0, 80);
		} else
			msg = "";
		cp.setTexto(msg);
		if (tipoProceso == 'R')
			return new CausaAvisoREVO(cp);
		else if (tipoProceso == 'G')
			return new CausaAvisoGRVO(cp);
		else if (tipoProceso == 'A')
			return new CausaAvisoRAVO(cp);
		return new CausaAvisoDCVO(cp);
	}
	
	/*private boolean isAviso(int id)
	{
		if (this.causaAvisos.containsKey("" + id))
			return true;
		return false;
	}
	
	private void addCausaAviso(String idCotizante, int idCotizPendiente, HashMap listaAvisos, CotizanteVO cotizante, char tipoProceso) throws NumberFormatException
	{
		if (listaAvisos != null)
		{
			List avisosRegistrados = this.buscaAvisos(idCotizante);
			int contador = (avisosRegistrados.size() > 0 ? avisosRegistrados.size() : 0) + 1;
			for (Iterator it = listaAvisos.keySet().iterator(); it.hasNext();)
			{
				String valor = String.valueOf(it.next());
				if (!inCausaAviso(valor, avisosRegistrados))
				{
					CausaVO cp = new CausaVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), idCotizPendiente, contador, Integer.parseInt(valor), listaAvisos.get(valor) + ", RUT:" + idCotizante);
					avisosRegistrados.add(transformCausaAviso(tipoProceso, cp));
					contador++;
				}
			}
			if (contador > 1)
				cotizante.setTieneAviso(1);
			this.listaCausaAviso.put("" + idCotizante, avisosRegistrados);
		}
	}
	
	private List buscaAvisos(String idCotizante)
	{
		if (this.listaCausaAviso.containsKey(idCotizante))
			return (List) this.listaCausaAviso.get(idCotizante);
		return new ArrayList();
	}*/
}