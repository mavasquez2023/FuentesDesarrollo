package cl.araucana.cp.distribuidor.business.mgr;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.core.util.NamesParser;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.business.utils.ProcArchThread;
import cl.araucana.cp.distribuidor.business.validaciones.Validacion;
import cl.araucana.cp.distribuidor.hibernate.beans.ApellidoCompuestoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DocumentoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MovtoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoSiguienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ValidacionVO;
import cl.araucana.cp.distribuidor.hibernate.dao.BulkNominaDAO;
import cl.araucana.cp.distribuidor.hibernate.dao.CotizanteDAO;
import cl.araucana.cp.distribuidor.hibernate.dao.NominaDAO;
import cl.araucana.cp.distribuidor.hibernate.dao.ParametrosDAO;
import cl.araucana.cp.distribuidor.hibernate.dao.ValidacionDAO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.hibernate.exceptions.SeccionException;
import cl.araucana.cp.distribuidor.hibernate.utils.HibernateUtil;
import cl.araucana.cp.mail.ReportaError;
import cl.araucana.cp.mail.data.Mail;
import cl.araucana.to.ConsultaDatosTO;
import cl.araucana.validadorSis.bpro.ValidadorSisBusiness;
import cl.araucana.validadorSis.model.afp.UtilsAFP;
import cl.araucana.validadorSis.model.local.ConsultaRespuesta;
import cl.araucana.validadorSis.model.local.UtilsLocal;

public class ProcesadorMgr
{
	protected static Logger logger = Logger.getLogger(ProcesadorMgr.class);
	private Session session;
	private ConvenioVO convenio, convenioMod; // se usa segundo convenio, uno con datos reales y otro con un merge de los datos de grupoConvenio a usar
	private GrupoConvenioVO grupoConvenio;
	private ValidacionDAO validacionDao;
	private ParametrosDAO parametrosDao;

	private NominaDAO nominaDao;
	private CotizanteDAO cotizanteDAO;
	
	private List secciones;
	private ComprobanteVO comprobante;
	private HashMap listaConceptosHM = new HashMap(); // para tener listas en memoria y no ir a preguntar a la DB
	private HashMap listaValidacionesHM = new HashMap();
	private HashMap listaMapeoHM = new HashMap();
	private HashMap causaErrores = new HashMap();// se guardan solo los tipoCausa que nivelError sea: 'error'
	private HashMap causaAvisos = new HashMap();// se guardan solo los tipoCausa que nivelError sea: 'aviso'
	private HashMap listaMensajesValidacion = new HashMap();// se guardan los mensajes generados desde el validador.
	private HashMap listaCausaAviso = new HashMap();
	private List lstValidaMovPers = null;
	private List lstValidaAPVs = null;
	private List listaConceptos = new Vector(); // listas en uso en cada ciclo
	private List listaValidaciones = new Vector(); // listas en uso en cada ciclo
	private HashMap listaMapeo = new HashMap(); // listas en uso en cada ciclo
	protected NominaVO nomina = null;
	private HashMap mapeoValores = new HashMap();// mapeo del valor de cada concepto, para cada cotizacion
	private int count;

	private DocumentoVO documento = null;
	private int posSiguiente = 0;// ubicacion del siguiente encontrado, para no recorrer la lista desde cero cada vez
	private StringWriter sw = new StringWriter();
	private Date inicioNomina = new Date(3153600000000L);// 100 annos
	private Date finalNomina = new Date(1);
	private boolean mailEnviado = false;
	private boolean isMovimiento = false;
	
	private HashMap listadoAValidar = new HashMap();
	private HashMap hashDatos = new HashMap();

	private StringBuffer lineaObtenida; //Obtiene la linea del archivo para luego informar si hay algun Error
	private int contadorLinea=0; //Obtiene el numero del archivo en que tubo el error
	
	
	int contadorMvto = 0;
	int cotizanteIdTmp = 0;

	private List listaMapeosValidaciones;

	public ProcesadorMgr(Session session)
	{
		init(session);
	}

	private void init(Session sess)
	{
		this.session = sess;
		this.validacionDao = new ValidacionDAO(sess);
		this.parametrosDao = new ParametrosDAO(sess);
		this.nominaDao     = new NominaDAO(sess);
		this.cotizanteDAO = new CotizanteDAO(sess);
		
	}

//	clillo 3-12-14 por Reliquidación
	//public int eliminar(boolean pendiente, int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador, HashMap parametrosNegocio)
	public int eliminar(boolean pendiente, int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador, HashMap parametrosNegocio, int periodo)
	{
		try
		{
//			clillo 3-12-14 por Reliquidación
			//this.validacionDao.borraCausaAviso(tipoProceso, rutEmpresa, idConvenio, idTrabajador);
			this.validacionDao.borraCausaAviso(tipoProceso, rutEmpresa, idConvenio, idTrabajador, periodo);
			this.nomina = this.validacionDao.getNomina(tipoProceso, rutEmpresa, idConvenio);
			this.convenio = this.validacionDao.getConvenio(idConvenio, rutEmpresa);
			this.grupoConvenio = this.validacionDao.getGrupoConvenio(this.convenio.getIdGrupoConvenio());
			this.convenioMod = new ConvenioVO(this.convenio, this.grupoConvenio);
			CotizanteVO oldCotiz = null;
			List apvsOld = new ArrayList();
			if (pendiente)// es pendiente
//				clillo 3-12-14 por Reliquidación
				//this.validacionDao.borraPendiente(tipoProceso, idTrabajador, idConvenio, rutEmpresa);
				this.validacionDao.borraPendiente(tipoProceso, idTrabajador, idConvenio, rutEmpresa, periodo);
			else
			{
//				clillo 3-12-14 por Reliquidación
				//oldCotiz = this.validacionDao.eliminaTrab(rutEmpresa, idConvenio, tipoProceso, idTrabajador);
				oldCotiz = this.validacionDao.eliminaTrab(rutEmpresa, idConvenio, tipoProceso, idTrabajador, periodo);
				apvsOld = oldCotiz.getApvList();
				this.nomina.restNumCotizOK();
				this.convenio.restNumCotizOK();
			}
			this.nomina.restNumCotizaciones();
			this.convenio.restNumCotizaciones();
			if (this.nomina.getNumCotizaciones() == 0)
			{
				this.validacionDao.eliminarNomina(this.nomina);// y comprobante y secciones asociadas, si existen
				this.convenio.restNumNominas();
			} else
			{
				long codBarras = this.nomina.getIdCodigoBarras();
				if (this.validacionDao.getNumPendientes(tipoProceso, idConvenio, rutEmpresa) == 0)// nomina aprobada!
				{
					this.nomina.setAceptada(new Timestamp(System.currentTimeMillis()));

					if (codBarras == 0)
					{
						HashMap cotizantes = this.validacionDao.getCotizantes(tipoProceso, this.nomina);

						logger.info("nomina aprobada! \nrutEmpresa:" + this.nomina.getRutEmpresa() + " idConvenio:" + this.nomina.getIdConvenio() + "\n\n"
								+ "debe generarse comprobante provisorio\n\n\n");
						int idDocumento = this.validacionDao.getIdDocumento(tipoProceso, rutEmpresa, idConvenio);
						generaComprobante(tipoProceso, idDocumento, this.nomina.getNumCotizaciones(), 0, cotizantes, parametrosNegocio);
					} else
					{
						if (oldCotiz != null)
							oldCotiz.setApvList(apvsOld);
						actualizaComprobante(tipoProceso, this.nomina.getNumCotizaciones(), codBarras, oldCotiz, null, parametrosNegocio);
						this.validacionDao.eliminarComprobante(codBarras);
					}

					this.validacionDao.guardaComprobante(this.comprobante, this.secciones);
					this.nomina.setIdCodigoBarras(this.comprobante.getIdCodigoBarra());
					this.nomina.setIdEstado(Constants.EST_NOM_POR_PAGAR);
					if (pendiente)
						this.convenio.sumNumNominasOK();// si la cotizacion era pendiente
					this.convenio.sumNumNominasCorr();
				} else
				{
					this.validacionDao.eliminarComprobante(codBarras);
					this.nomina.setIdCodigoBarras(0);
				}
				this.validacionDao.guardaNomina(this.nomina);
			}
			this.validacionDao.guardaConvenio(this.convenio);
			return 1;
		} catch (Exception e)
		{
			return -1;
		}
	}
	
	/**
	 * Setea en los parametros de negocio la cantidad de Cotizantes registrados en la Nómina y si por lo menos uno de los trabajadores informa SIS
	 * @author asepulveda 01-07-2010
	 * 
	 * @param HashMap parametrosNegocio
	 * @return HashMap parametrosNegocio
	 * @throws IOException
	 */
	private HashMap seteaInfoBasicaSISByNomina(HashMap parametrosNegocio, char tipoProceso, int rutEmpresa, int idConvenio) throws DaoException, IOException 
	{	
		
		NominaVO nominaPaso =  this.validacionDao.getNomina(tipoProceso, rutEmpresa, idConvenio);
		parametrosNegocio.put("cantidadCotizantes", new  Integer(nominaPaso.getNumCotizaciones()));
		parametrosNegocio.put("informaSisEnNomina", new Boolean(nominaPaso.getInformaSIS() == 1 ? true : false));
		return parametrosNegocio;
	} 

	/**
	 * Valida la nómina por modificaciones vía web
	 * 
	 * @param idCotizPend
	 * @param oldRut
	 * @param cotizante
	 * @param parametrosNegocio
	 * @param periodo_old
	 * @return
	 * @throws Exception
	 */
//	clillo 3-12-14 por Reliquidación
	//public List valida(int idCotizPend, String oldRut, CotizanteVO cotizante, HashMap parametrosNegocio) throws Exception
	public List valida(int idCotizPend, String oldRut, CotizanteVO cotizante, HashMap parametrosNegocio, int periodo_old) throws Exception
	{	
		boolean generarCP= false;
		Transaction tx = null;
		int idDocumento=0;
		try
		{
			List result = new ArrayList();
			this.session.clear();
			tx = this.session.beginTransaction();
			this.causaErrores = this.validacionDao.getTiposCausasErr();
			this.causaAvisos = this.validacionDao.getTiposCausasWarn();

			char tipoProceso = cotizante.getTipoProceso();
			this.convenio = this.validacionDao.getConvenio(cotizante.getIdConvenio(), cotizante.getRutEmpresa());
			this.grupoConvenio = this.validacionDao.getGrupoConvenio(this.convenio.getIdGrupoConvenio());
			
			parametrosNegocio.put("grupoConvenio", this.grupoConvenio);
			
			//TODO 09-05-2012 GMALLEA Se limpia el parametro mapeoFonasa
			parametrosNegocio.put("mapeoFonasa", "");
			
			//GMALLEA 24-0-2013 Se actualizan los tipo de pago cada ves que modifiquen una nomina a (1) pagado
			NominaVO nominaVO = this.nominaDao.getNomina(""+cotizante.getTipoProceso(),new Long(cotizante.getRutEmpresa()).longValue(),cotizante.getIdConvenio());
			if(nominaVO.getIdCodigoBarras() > 0)
				this.nominaDao.actualizaTipoPagoSecciones(nominaVO.getIdCodigoBarras());
			//fin gmallea
			this.convenioMod = new ConvenioVO(this.convenio, this.grupoConvenio);
			EmpresaVO empresa = this.validacionDao.getEmpresa(cotizante.getRutEmpresa());
			parametrosNegocio.put("empresaPrivada", empresa.getPrivada());
			parametrosNegocio.put("tipoEmpresa", empresa.getTipo());
			OpcionProcVO opcion = this.validacionDao.getOpcionProcesos(this.convenioMod.getIdOpcion());
			parametrosNegocio.put("opcionProcesos", opcion);
			parametrosNegocio.put("rutEspeciales", this.parametrosDao.getRutsEspeciales());
			
			parametrosNegocio = this.seteaInfoBasicaSISByNomina(parametrosNegocio, tipoProceso, cotizante.getRutEmpresa(), cotizante.getIdConvenio());

			//this.validacionDao.borraCausaAviso(tipoProceso, cotizante.getRutEmpresa(), cotizante.getIdConvenio(), cotizante.getIdCotizante());

			boolean isNuevo = false;
			if (tipoProceso == 'R')
				isNuevo = this.session.get(CotizacionREVO.class, new CotizacionREVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), cotizante.getIdCotizante())) == null ? true : false;
			if (tipoProceso == 'G')
				isNuevo = this.session.get(CotizacionGRVO.class, new CotizacionGRVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), cotizante.getIdCotizante())) == null ? true : false;
			if (tipoProceso == 'A')
//				clillo 3-12-14 por Reliquidación
				//isNuevo = this.session.get(CotizacionRAVO.class, new CotizacionRAVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), cotizante.getIdCotizante())) == null ? true : false;
				isNuevo = this.session.get(CotizacionRAVO.class, new CotizacionRAVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), cotizante.getIdCotizante(), cotizante.getPeriodo())) == null ? true : false;
			if (tipoProceso == 'D')
				isNuevo = this.session.get(CotizacionDCVO.class, new CotizacionDCVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), cotizante.getIdCotizante())) == null ? true : false;

			this.listaValidaciones = this.validacionDao.getListaValidaciones("" + tipoProceso);
			boolean tieneError = false;
			HashMap listaAvisos = new HashMap();

			//Solo para pruebas de estres
			java.util.Date stressInicio = new java.util.Date();
			java.util.Date stressIniTmp = new java.util.Date();
			java.util.Date stressFinTmp = new java.util.Date();
			stressInicio = new java.util.Date();
			logger.info("\n\ncomienza validacion:" + stressInicio.getTime() + "::");

			//asepulveda 06/07-200
			//csanchez   13/04/2011 Se mueve código por problemas al validar Cotización Obligatoria AFP
			int previsionSIS = 0;

			boolean validarSis = this.seDebeValidarSIS(parametrosNegocio);

			if (tipoProceso == 'R')
				previsionSIS = ((CotizacionREVO)cotizante.getCotizacion()).getPrevisionSIS();
			else if (tipoProceso == 'G')
				previsionSIS = ((CotizacionGRVO)cotizante.getCotizacion()).getPrevisionSIS();
			else if (tipoProceso == 'A')
				previsionSIS = ((CotizacionRAVO)cotizante.getCotizacion()).getPrevisionSIS();

			if(!validarSis && previsionSIS > 0)
				validarSis = true;

			parametrosNegocio.put("informaSisEnNomina", new Boolean(validarSis));

			if (this.listaValidaciones.size() > 0)
			{
				HashMap hashCargasFamiliares = this.getHashCargasPorEmpresa(cotizante.getRutEmpresa(), this.convenio.getIdCcaf());
				ValidacionVO siguiente = getPrimeraValidacion();
				while (siguiente != null)
				{
					Class validacion = Class.forName(siguiente.getClaseValidador().trim());
					Class[] argTypes = { HashMap.class, Session.class, ConvenioVO.class };
					Object[] argValues = { parametrosNegocio, this.session, this.convenioMod };
					Validacion valida = (Validacion) validacion.getConstructor(argTypes).newInstance(argValues);

					if (siguiente.getClaseValidador().trim().endsWith("VN110") ||
						siguiente.getClaseValidador().trim().endsWith("VN120") ||
						siguiente.getClaseValidador().trim().endsWith("VN130") ||
						siguiente.getClaseValidador().trim().endsWith("VN140")) {
						Class[] argTypes2 = { HashMap.class, Session.class, ConvenioVO.class, HashMap.class};
						Object[] argValues2 = { parametrosNegocio, this.session, this.convenioMod, hashCargasFamiliares };
						valida = (Validacion) validacion.getConstructor(argTypes2).newInstance(argValues2);
					}

					int codResult = valida.validaFromWEB(cotizante);
					if ((codResult != 0 && isValidable(codResult)) || codResult >= 1000)
					{// por cada validacion, guardo errores encontrados
						if (codResult == Constants.CAIDA_SISTEMA || codResult == Constants.SIN_CONCEPTOS)
							codResult = Constants.INICIO_COD_ERROR_GENERICO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));
						if (codResult == Constants.CONCEPTOS_SIN_VALOR)
							codResult = Constants.INICIO_COD_ERROR_VACIO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));
						int contTrabPes = 0;
						String _codResult = "" + codResult;
						if (_codResult.length() > 4)
						{
							while (contTrabPes < _codResult.length())
							{
								if (isValidable(codResult))
								{
									codResult = Integer.parseInt(_codResult.substring(contTrabPes, 3 + contTrabPes));
									String mensaje = ((valida.getMensaje() == null || valida.getMensaje().trim().equals("")) ? siguiente.getIdValidacion() : valida.getMensaje());
									this.listaMensajesValidacion.put(String.valueOf(codResult), mensaje);
									logger.info("\n\n\n validacion incorrecta!:" + siguiente.getIdValidacion() + ":codigo error:" + codResult + "::");
									result.add(new Integer(codResult));
								}
								contTrabPes += 3;
							}
						} else
						{
							String mensaje = ((valida.getMensaje() == null || valida.getMensaje().trim().equals("")) ? siguiente.getIdValidacion() : valida.getMensaje());
							this.listaMensajesValidacion.put(String.valueOf(codResult), mensaje);
							logger.info("\n\n\n validacion incorrecta!:" + siguiente.getIdValidacion() + ":codigo error:" + codResult + "::");
							result.add(new Integer(codResult));
						}
					} else
						logger.debug("validacion correcta!:" + siguiente.getIdValidacion() + "::");
					siguiente = getSiguienteValidacion(valida.getResultado(), siguiente.getSiguientes(), this.listaValidaciones);
				}
			} // fin validaciones
			
			List listaParams = new ArrayList();
			
			listaParams.add(new Integer(Constants.ACTIVO_VALIDACION_SIS_INDEPENDIENTE));
			listaParams.add(new Integer(Constants.ACTIVO_VALIDACION_SIS_EMPRESA));
			
			ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);
			
			String validacionSIS ="";
			if(empresa.getTipo().equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
				validacionSIS = paramHash.get("" + Constants.ACTIVO_VALIDACION_SIS_INDEPENDIENTE);
			}else{
				validacionSIS = paramHash.get("" + Constants.ACTIVO_VALIDACION_SIS_EMPRESA);
			}
			
			//String validacionSIS = (String)parametrosNegocio.get("" + Constants.ACTIVO_VALIDACION_SIS);
			if (validacionSIS != null && validacionSIS.toUpperCase().equals("SI")){
				if (tipoProceso == 'R' && cotizante.getIdEntPensionReal() != 0){
					result = validaCotizanteSis(result, parametrosNegocio, cotizante);
				}
			}
			
			List apvs = new ArrayList();
			List apvsOld = new ArrayList();
			if (tipoProceso == 'R')
			{
				this.lstValidaMovPers = this.validacionDao.getLstValidaMovPers();
				this.lstValidaAPVs = this.validacionDao.getLstValidaAPVs();

				result.addAll(registraMovimiento(cotizante.isAfpVoluntario(), cotizante, parametrosNegocio));
				result.addAll(registraAPV(cotizante, parametrosNegocio));
				apvs = cotizante.getApvList();

				int diasXMes = new Integer((String) parametrosNegocio.get("" + Constants.PARAM_DIAS_X_MES)).intValue();
				List listaMovtos = ((CotizacionREVO) cotizante.getCotizacion()).getMovimientoPersonal();
				List listaMovtosAF = ((CotizacionREVO) cotizante.getCotizacion()).getMovimientoPersonalAF();
				if (cotizante.getNumDiasTrabajados() < diasXMes && ((listaMovtos == null || listaMovtos.size() == 0) && (listaMovtosAF == null || listaMovtosAF.size() == 0)))
				{
					if (this.listaMensajesValidacion.get(String.valueOf(new Integer(Constants.TIPO_CAUSA_MOVTO_PERSO_NECESARIO * 10))) == null)
						this.listaMensajesValidacion.put(String.valueOf(new Integer(Constants.TIPO_CAUSA_MOVTO_PERSO_NECESARIO * 10)), "Num.DIAS TRAB MENOR DIAS MES");
					result.add(new Integer(Constants.TIPO_CAUSA_MOVTO_PERSO_NECESARIO * 10));
				}
			}
			int idCotizante = cotizante.getIdCotizante();
			List avisosRegistrados = new ArrayList();
			int contador = 1;
			for (Iterator i = result.iterator(); i.hasNext();)
			{
				Integer valor = (Integer) i.next();
				if (!isAviso(valor.intValue()))
					tieneError = true;
				if (!inCausaAviso("" + valor.intValue(), avisosRegistrados, cotizante.getPeriodo()))
				{
//					clillo 3-12-14 por Reliquidación
					//CausaVO cp = new CausaVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), idCotizante, contador, valor.intValue(), this.listaMensajesValidacion.get(valor + "") + ", RUT:" + idCotizante);
					CausaVO cp = new CausaVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), idCotizante, contador, valor.intValue(), this.listaMensajesValidacion.get(valor + "") + ", RUT:" + idCotizante, cotizante.getPeriodo());
					//CausaVO cp = new CausaVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), idCotizante, contador, valor.intValue(), listaAvisos.get(valor) + ", RUT:" + idCotizante);
					avisosRegistrados.add(transformCausaAviso(tipoProceso, cp));
					contador++;
				}
				if (contador > 1)
					cotizante.setTieneAviso(1);
				this.listaCausaAviso.put(cotizante.getPeriodo() + "" + idCotizante, avisosRegistrados);
			}

			stressFinTmp = new java.util.Date();
			logger.info("\n\nfin validaciones:diff:" + (stressFinTmp.getTime() - stressIniTmp.getTime()) + "::" + stressFinTmp.getTime() + ":tieneError:" + tieneError + "::");

			if (!tieneError)
			{
//				clillo 3-12-14 por Reliquidación
				//this.validacionDao.borraCausaAviso(tipoProceso, cotizante.getRutEmpresa(), cotizante.getIdConvenio(), cotizante.getIdCotizante());
				this.validacionDao.borraCausaAviso(tipoProceso, cotizante.getRutEmpresa(), cotizante.getIdConvenio(), cotizante.getIdCotizante(), cotizante.getPeriodo());
				
				CotizanteVO oldCotiz = null;
				if (idCotizPend > 0)
//					clillo 3-12-14 por Reliquidación
					//this.validacionDao.borraPendiente(tipoProceso, idCotizPend, cotizante.getIdConvenio(), cotizante.getRutEmpresa());
					this.validacionDao.borraPendiente(tipoProceso, idCotizPend, cotizante.getIdConvenio(), cotizante.getRutEmpresa(), cotizante.getPeriodo());
				else if (!oldRut.equals("new"))
				{
//					clillo 3-12-14 por Reliquidación
					//oldCotiz = this.validacionDao.getCotizante(tipoProceso, cotizante.getRutEmpresa(), cotizante.getIdConvenio(), oldRut, cotizante.getIdCotizante());
					oldCotiz = this.validacionDao.getCotizante(tipoProceso, cotizante.getRutEmpresa(), cotizante.getIdConvenio(), oldRut, cotizante.getIdCotizante(), periodo_old);

					if (oldCotiz != null)
						apvsOld = oldCotiz.getApvList();
				}

				//if (!this.validacionDao.guardaCotizante(tipoProceso, oldRut, cotizante))
				if (!this.validacionDao.guardaCotizante(tipoProceso, oldRut, cotizante, periodo_old))
					result.add(new Integer(Constants.TIPO_CAUSA_COTIZANTE_NO_GUARDADO));
				else
				{
					this.validacionDao.guardaCausaAviso(this.listaCausaAviso, cotizante);
					this.nomina = this.validacionDao.getNomina(tipoProceso, cotizante.getRutEmpresa(), cotizante.getIdConvenio());
					long codBarras = this.nomina.getIdCodigoBarras();
					long folio = 0;
					/*
					 * eliminar folio (si existe) validar estado nomina (puede pasar a "porPagar") eliminar si pendiente generar comprobante (secciones) actualizar valores convenio
					 */
					if (idCotizPend > 0)
					{
						this.nomina.sumNumCotizOK();
						this.nomina.sumNumCotizCorr();
						this.convenio.sumNumCotizCorr();
						this.convenio.sumNumCotizOK();
					} else
					{
						if (isNuevo)
						{
							this.nomina.sumNumCotizaciones();
							this.nomina.sumNumCotizOK();
						}
					}
					this.nomina.setIdCodigoBarras(0);
					if (this.validacionDao.getNumPendientes(tipoProceso, cotizante.getIdConvenio(), cotizante.getRutEmpresa()) == 0)// nomina aprobada!
					{
						this.nomina.setAceptada(new Timestamp(System.currentTimeMillis()));

						if (codBarras == 0)// genera comprobante
						{
							//clillo 4-11-15 se genera comprobante en segundo plano
							//HashMap cotizantes = this.validacionDao.getCotizantes(tipoProceso, this.nomina);
							logger.info("nomina aprobada! \nrutEmpresa:" + this.nomina.getRutEmpresa() + " idConvenio:" + this.nomina.getIdConvenio() + "\n"
									+ "Debe generarse nuevo comprobante provisorio.\n\n");
							idDocumento = this.validacionDao.getIdDocumento(tipoProceso, cotizante.getRutEmpresa(), cotizante.getIdConvenio());
							//clillo 4-11-15 se genera comprobante en segundo plano
							//generaComprobante(tipoProceso, idDocumento, this.nomina.getNumCotizaciones(), folio, cotizantes, parametrosNegocio);
							generarCP= true;
						} else
						// actualiza comprobante
						{
							cotizante.setApvList(apvs);

							if (oldCotiz != null){
								oldCotiz.setApvList(apvsOld);
								
							//GAMALLEA 01-02-2012 Se obtiene la seccion desde la BD, para validar si el comprobante antiguo tiene INP Mutual..
							CotizanteDAO cotizanteDAO = new CotizanteDAO(session);
							List listSeccionInp = cotizanteDAO.getDetalleSecciones(codBarras, Constants.ID_TIPO_SECCION_INP);
							
							for(Iterator it = listSeccionInp.iterator() ; it.hasNext();){
								
								SeccionVO seccionVO = (SeccionVO) it.next();
									//GMALLEA 01-02-2012 Si el valor de Mm4 es mayor a cero significa que ya se registro el comprobante con INP Mutual (Acc Trabajo)
									if(seccionVO.getMm4() > 0) {
							
										oldCotiz.getCotizacion().setINPMutualOld(true);
									}
								}
							}
							actualizaComprobante(tipoProceso, this.nomina.getNumCotizaciones(), codBarras, oldCotiz, cotizante, parametrosNegocio);
							this.validacionDao.eliminarComprobante(codBarras);
							this.nomina.setCrc(0); //Para permitir el reenvío de la nómina
							//clillo 4-11-15 se actualiza comprobante 
							this.validacionDao.guardaComprobante(this.comprobante, this.secciones);
							this.nomina.setIdCodigoBarras(this.comprobante.getIdCodigoBarra());
							this.nomina.setIdEstado(Constants.EST_NOM_POR_PAGAR);
							this.convenio.sumNumNominasOK();
							this.convenio.sumNumNominasCorr();
						}

						//this.validacionDao.guardaComprobante(this.comprobante, this.secciones);
						//this.nomina.setIdCodigoBarras(this.comprobante.getIdCodigoBarra());
						//this.nomina.setIdEstado(Constants.EST_NOM_POR_PAGAR);
						//this.convenio.sumNumNominasOK();
						//this.convenio.sumNumNominasCorr();
					} else
						this.validacionDao.eliminarComprobante(codBarras);

					//asepulveda 06/07-200
					/*int previsionSIS = 0;

					boolean validarSis = this.seDebeValidarSIS(parametrosNegocio);

					if (tipoProceso == 'R')
						previsionSIS = ((CotizacionREVO)cotizante.getCotizacion()).getPrevisionSIS();
					else if (tipoProceso == 'G')
						previsionSIS = ((CotizacionGRVO)cotizante.getCotizacion()).getPrevisionSIS();
					else if (tipoProceso == 'A')
						previsionSIS = ((CotizacionRAVO)cotizante.getCotizacion()).getPrevisionSIS();

					if(!validarSis && previsionSIS > 0)
						validarSis = true;*/

					this.nomina.setInformaSIS(validarSis ? 1 : 0);

					this.validacionDao.guardaNomina(this.nomina);
				}

			}
			if (!this.validacionDao.guardaConvenio(this.convenio))
				result.add(new Integer(Constants.TIPO_CAUSA_CONVENIO_NO_GUARDADO));
			if (cotizante.getApellidoMat().trim().indexOf(" ") > -1)
				this.validacionDao.guardaApelCpto(cotizante.getApellidoMat().trim());
			if (cotizante.getApellidoPat().trim().indexOf(" ") > -1)
				this.validacionDao.guardaApelCpto(cotizante.getApellidoPat().trim());
			if (cotizante.isAfpVoluntario())
			{
				CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
				if (cotizacion.getAfpvAplldioMatDpndiente().trim().indexOf(" ") > -1)
					this.validacionDao.guardaApelCpto(cotizacion.getAfpvAplldioMatDpndiente().trim());
				if (cotizacion.getAfpvAplldioPatDpndiente().trim().indexOf(" ") > -1)
					this.validacionDao.guardaApelCpto(cotizacion.getAfpvAplldioPatDpndiente().trim());
			}
			if (result.size() > 0)
			{
				HashMap _result = new HashMap();
				for (Iterator i = result.iterator(); i.hasNext();)
				{
					Integer tmp = (Integer) i.next();
					if (!_result.containsKey(String.valueOf(tmp)) && isAviso(tmp.intValue()))
						_result.put(String.valueOf(tmp), cotizante.getIdCotizante() + ":");
				}
				try
				{
					addCausaAviso("" + cotizante.getIdCotizante(), cotizante.getIdCotizante(), _result, cotizante, tipoProceso);
				} catch (Exception e)
				{
				}
				logger.info("ProcesadorMgr: guardaCausaAviso: N:" + this.listaCausaAviso.size());
			}
			return result;
		} catch (Exception e)
		{
			logger.info("\n\n\nProcesadorMgr:valida:ERROR:", e);
			logger.error("\n\n\nProcesadorMgr:valida:ERROR:", e);
			throw e;
		}finally{
			try {
				tx.commit();
				HibernateUtil.closeSession();
			} catch (Exception e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
			if (generarCP){
				long folio = 0;
				char tipoProceso= cotizante.getTipoProceso();
				//clillo 4-11-15 se genera comprobante en segundo plano
				//generaComprobante(tipoProceso, idDocumento, this.nomina.getNumCotizaciones(), folio, cotizantes, parametrosNegocio);
				ComprobanteThread comprobante= new ComprobanteThread(tipoProceso, idDocumento, this.nomina, this.convenio, folio, parametrosNegocio);
				comprobante.start();
			}
		}
	}
	/**
	 * Valida la nómina recibida vía el envío de ésta.
	 * @param idDescriptor
	 * @param mapConceptos
	 * @param parametrosNegocio
	 * @throws Exception
	 */
	public void valida(String idDescriptor, Properties mapConceptos, HashMap parametrosNegocio) throws Exception
	{
		
		String user = (String) parametrosNegocio.get("user");
		
		PersonaVO personaVO = this.validacionDao.getPersona(Integer.parseInt(user));
		parametrosNegocio.put("mailPersona", personaVO.getEmail() == null ? "" : personaVO.getEmail());
		
		logger.info("\n\n\nProcesadorMgr:valida:" + idDescriptor);
		int idConvenio = -1;
		int rutEmpresa = -1;
		char tipoProceso = 'X';

		this.causaErrores = this.validacionDao.getTiposCausasErr();
		this.causaAvisos = this.validacionDao.getTiposCausasWarn();
		parametrosNegocio.put("rutEspeciales", this.parametrosDao.getRutsEspeciales());
		
		//TODO 09-05-2012 GMALLEA Se limpia el parametro mapeoFonasa
		parametrosNegocio.put("mapeoFonasa", "");
		
		parametrosNegocio.put("apellAprendidos", new ArrayList());
		NamesParser parser = NamesParser.getInstance();
		parser.setAutoLearning(true);
		List lista = this.parametrosDao.getApellCompuestos();
		for (Iterator it = lista.iterator(); it.hasNext();)
			parser.add((((ApellidoCompuestoVO) it.next()).getApellido()).trim());
		parametrosNegocio.put("NamesParser", parser);
	
		//Solo para pruebas de estres
		TimerStress timerStress = new TimerStress();
		timerStress.inicia(0, "Procesamiento de un archivo de nomina");

		boolean error = true;
		BulkNominaDAO bulkNominaDao = null;
		try
		{
			this.mailEnviado = false;
			this.contadorMvto = 0;
			String []tokens = idDescriptor.split("#");
			rutEmpresa = new Integer(tokens[0]).intValue();
			idConvenio = new Integer(tokens[1]).intValue();
			tipoProceso = tokens[2].charAt(0);
			
			this.convenio = this.validacionDao.getConvenio(idConvenio, rutEmpresa);		
			this.grupoConvenio = this.validacionDao.getGrupoConvenio(this.convenio.getIdGrupoConvenio());
			
			parametrosNegocio.put("grupoConvenio", this.grupoConvenio);		
			
			this.documento = this.validacionDao.getDocumento(tipoProceso, rutEmpresa, idConvenio);
			this.nomina = this.validacionDao.getNomina(tipoProceso, rutEmpresa, idConvenio); // nomina ya se creo al recibir el envio, siempre deberia existir
			
			parametrosNegocio.put("tipoEmpresa", nomina.getEmpresa().getTipo());
			
			if (this.nomina == null)
			{
				logger.error("\n\nERROR: no existe nomina?? (deberia haber sido creada al recibir el envio) tipoProceso:" + tipoProceso + ":idConvenio:" + idConvenio + ":rutEmpresa:" + rutEmpresa
						+ "::");
				reportaError(Mail.ERROR, tipoProceso, rutEmpresa, idConvenio, new StringBuffer(), new StringBuffer("no se encontro nomina en DB."), parametrosNegocio);
				return;
			}
			
			HibernateUtil.disableInterceptor();
			
			this.nomina.setIdEstado(Constants.EST_NOM_NO_CARGADA);
			this.session.update(this.nomina);
			this.session.flush();
			this.session.beginTransaction();
			this.nomina.setIdEstado(Constants.EST_NOM_EN_EJB);
			this.session.update(this.nomina);
			this.session.flush();
			timerStress.inicia(1, "espera desbloqueo1");
			bulkNominaDao = new BulkNominaDAO(HibernateUtil.getSessionFactory().openStatelessSession(this.session.connection()), this.nomina);
			bulkNominaDao.creaBloqueoBD(this.session.connection());
			timerStress.inicia(1,"eliminacion nomina");
			bulkNominaDao.borraPendientes();
			bulkNominaDao.borraCotizaciones();
			long folio = bulkNominaDao.borraComprobante(this.nomina.getIdCodigoBarras());
			this.nomina.setIdCodigoBarras(0);
			bulkNominaDao.borraBloqueoBD(this.session.connection());
			this.session.getTransaction().commit();
			timerStress.inicia(1,"carga parametros y validaciones");
			this.nomina.setIdEstado(Constants.EST_NOM_NO_CARGADA);
			this.session.update(this.nomina);
			this.session.flush();
			this.session.beginTransaction();
			this.nomina.setIdEstado(Constants.EST_NOM_EN_EJB);
			this.session.update(this.nomina);
			this.session.flush();

			// carga datos asociados al procesamiento: convenio, grupoConvenio, datos de empresa, opcion de procesos
			boolean resultData = cargaData(parametrosNegocio, idConvenio, rutEmpresa, tipoProceso);
			if (!resultData) // se detecto un problema con los datos asociados al convenio, por lo que no es posible seguir procesando la nomina
				return;

			HashMap cotizantes = new HashMap();// lista de cotizaciones validadas correctamente
			HashMap cotizantesPendientes = new HashMap();// lista de cotizaciones no validadas
			HashMap cotPendientes = new HashMap();// lista de cotizante no validado
			this.listaCausaAviso = new HashMap();

			setListasValidacion("" + tipoProceso, this.grupoConvenio.getIdMapaNom(tipoProceso));
			this.count = 0;
			// por cada linea recibida, aplica el parseo correspondiente, y las validaciones por tipo de nomina. Registra cotizaciones y pendientes.
			this.listaMapeosValidaciones = this.validacionDao.llenaListaMapeos(this.convenioMod.getIdMapaCod(), rutEmpresa);

			timerStress.inicia(1, "validaciones");
			aplicaValidaciones(mapConceptos, parametrosNegocio, idConvenio, rutEmpresa, tipoProceso, cotizantes, cotizantesPendientes, cotPendientes);
			
			
			
			//Validacion con WS (cotizantes listado OK, cotPendientes NOK)
			
			/*
			 * 1° Pasar de Cotizante a ConsultaRespuesta
			 * 2° enviar a metodo para validar sis
			 * 3° agregar aviso en caso de no debe pagar sis o debe pagar sis
			 */			
			//Ve si aplica SIS o no	
			
			List listaParams = new ArrayList();
			
			listaParams.add(new Integer(Constants.ACTIVO_VALIDACION_SIS_EMPRESA));
			
			ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);
			
			String validacionSIS = paramHash.get("" + Constants.ACTIVO_VALIDACION_SIS_EMPRESA);
			//String validacionSIS = (String)parametrosNegocio.get("" + Constants.ACTIVO_VALIDACION_SIS);
			if (validacionSIS != null && validacionSIS.toUpperCase().equals("SI")){
				if (tipoProceso == 'R'){
					validacionesSis(parametrosNegocio, cotizantes, cotPendientes, tipoProceso);
				}
			}
			
			//Fin validacion con WS
			//timerStress.inicia(1, "espera desbloqueo2");
			//bulkNominaDao.creaBloqueoBD(this.session.connection());
			for (Iterator itCotPend = cotPendientes.keySet().iterator(); itCotPend.hasNext();)
			{
				String idPend = (String)itCotPend.next();
				cotizantes.remove(idPend);
				this.listaCausaAviso.remove(idPend);
			}
			this.count = cotizantes.size() + cotizantesPendientes.size();

			String tipoError = "Guardar Cotizantes validados";
			timerStress.inicia(1, "espera desbloqueo3");
			bulkNominaDao.creaBloqueoBD(this.session.connection());
			timerStress.inicia(1,"guardado cotizantes validados");
			error = bulkNominaDao.guardaCotizantes(cotizantes);
			if (error)
			{
				timerStress.inicia(1,"guardado cotizaciones validadas");
				tipoError = "Guardar Cotizaciones validadas";
				error = bulkNominaDao.guardaCotizaciones(cotizantes.values());
			}
			if (error)
			{
				timerStress.inicia(1,"guardado avisos");
				tipoError = "Guardar Causa Avisos";
				error = bulkNominaDao.guardaCausaAviso(this.listaCausaAviso);
			}
			/*if(error && this.listaCausaAviso.size() > 0){
				System.out.println("AVISOS!!!!");
			}*/
			if (error)
			{
				tipoError = "limpiado de cotizantes";
				timerStress.inicia(1, "limpiado de cotizantes");
				error = bulkNominaDao.confirmaCotizantes();
			}
			bulkNominaDao.borraBloqueoBD(this.session.connection());
			if (error)
			{
				timerStress.inicia(1,"guardado pendientes");
				tipoError = "Guardar Pendientes";
				error = bulkNominaDao.guardaPendientes(cotizantesPendientes);
			}
			//csanchez. En caso de Cotizantes Pendientes (errores) se envía correo con detalle de estos.
			if(error && cotizantesPendientes.size() > 0){
				String cabecera = "En validación de la información de proceso " +
								  (tipoProceso == 'R' ? Constants.NOMINA_R : (tipoProceso == 'G' ? Constants.NOMINA_G : (tipoProceso == 'A' ? Constants.NOMINA_A : Constants.NOMINA_D))) +
								   " remitida en el archivo se informa a Ud. que se ha detectado la(s) siguiente(s) observación(es) que impiden la creación de su comprobante:\n";
				reportaError(Mail.ERROR, tipoProceso, rutEmpresa, idConvenio, new StringBuffer(cabecera), this.cuerpoCorreoErrores(tipoProceso, rutEmpresa, idConvenio), parametrosNegocio);
			}
			timerStress.finaliza(1);
			if (error)
			{
				if (tipoProceso == 'G')
				{
					//TODO 20-04-2012 GMALLEA Se obtiene la fecha de la tabla parametro con esto se obtiene el primer dia y ultimo del mes..
					 String periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO);
					
					 SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
					 
					 int mes= Integer.parseInt(periodoInformado.substring(4, periodoInformado.length()));
					 int ano= Integer.parseInt( periodoInformado.substring(0, 4));
					
					 Calendar cal = GregorianCalendar.getInstance();
					 cal.set(ano, mes-1, 1);
					    		
					 this.inicioNomina =  new java.sql.Date(formatoDelTexto.parse(cal.getActualMinimum(GregorianCalendar.DAY_OF_MONTH)+"/"+ mes +"/"+ ano).getTime());
				     this.finalNomina = new java.sql.Date(formatoDelTexto.parse(cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)+"/"+ mes +"/"+ ano).getTime());
					
					((NominaGRVO) this.nomina).setInicio(this.inicioNomina);
					((NominaGRVO) this.nomina).setTermino(this.finalNomina);
				} else if (tipoProceso == 'A')
				{
					//TODO 20-04-2012 GMALLEA Se obtiene la fecha de la tabla parametro con esto se obtiene el primer dia y ultimo del mes..
					 String periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO);
					
					 SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
					 
					 int mes= Integer.parseInt(periodoInformado.substring(4, periodoInformado.length()));
					 int ano= Integer.parseInt( periodoInformado.substring(0, 4));
					
					 Calendar cal = GregorianCalendar.getInstance();
					 cal.set(ano, mes-1, 1);
					    		
					 this.inicioNomina =  new java.sql.Date(formatoDelTexto.parse(cal.getActualMinimum(GregorianCalendar.DAY_OF_MONTH)+"/"+ mes +"/"+ ano).getTime());
				     this.finalNomina = new java.sql.Date(formatoDelTexto.parse(cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)+"/"+ mes +"/"+ ano).getTime());
					
					((NominaRAVO) this.nomina).setInicio(this.inicioNomina);
					((NominaRAVO) this.nomina).setTermino(this.finalNomina);
				}

				if (cotPendientes.isEmpty())
				{
					this.nomina.setAceptada(new Timestamp(System.currentTimeMillis()));

					logger.info("nomina aprobada! \nrutEmpresa:" + rutEmpresa + " idConvenio:" + idConvenio + "\n\n" + "debe generarse comprobante provisorio\n\n\n");

					timerStress.inicia(1,"generacion cmp");
					generaComprobante(tipoProceso, this.documento.getId(), this.count, folio, cotizantes, parametrosNegocio);

					timerStress.inicia(1,"guardado cmp");
					this.validacionDao.guardaComprobante(this.comprobante, this.secciones);

					this.nomina.setIdCodigoBarras(this.comprobante.getIdCodigoBarra()); // nuevo codigo de barras
					this.convenio.sumNumNominasOK();
				}
				timerStress.inicia(1,"guarda convenio");
				tipoError = "Guardar Convenio";
				error = this.validacionDao.guardaConvenio(this.convenio);
				timerStress.finaliza(1);
			}


			HibernateUtil.enableInterceptor();
			if (error)
			{
				tipoError = "Guardar Nomina";
				timerStress.inicia(1, "guardado nomina");
				HibernateUtil.enableInterceptor();
				this.nomina.setNumCotizaciones(this.count);
				this.nomina.setNumCotizOK(cotizantes.size());
				this.convenio.addNumCotizaciones(this.count);
				this.convenio.addNumCotizacionesOk(cotizantes.size());
				this.convenio.sumNumNominas();
				if (cotPendientes.isEmpty())
					this.nomina.setIdEstado(Constants.EST_NOM_POR_PAGAR);
				else
					this.nomina.setIdEstado(Constants.EST_NOM_CON_ERRORES);
				
				//asepulveda 07-07-2010
				boolean validarSis = this.seDebeValidarSIS(parametrosNegocio);
				this.nomina.setInformaSIS(validarSis ? 1 : 0);				
				this.session.update(this.nomina);
				logger.info("\n\nnomina guardada: estado:" + this.nomina.getIdEstado() + "::");
			}
			// actualiza resultado del procesamiento
			if (error)
			{
				timerStress.inicia(1, "update balanceo");
				this.validacionDao.setNRegDescriptor(tipoProceso, idConvenio, rutEmpresa, this.documento.getIdEnvio(), this.count);
				this.validacionDao.addLineasBalanceoCarga(tipoProceso, idConvenio, rutEmpresa, this.count);
				this.session.getTransaction().commit();
			} else
			{
				logger.error("ERROR: no se pudo procesar:" + tipoError + "::" + tipoProceso + "::" + rutEmpresa + "::" + idConvenio + "::");
				reportaError(parametrosNegocio, idConvenio, rutEmpresa, tipoProceso, new StringBuffer("problemas de Lectura/Escritura"), new Exception("no se pudo procesar"));
			}
			timerStress.finaliza(0);
		} catch (IOException e)
		{
			error = false;
			StringBuffer sb = new StringBuffer("problemas de Lectura/Escritura");
			logger.error(sb, e);
			reportaError(parametrosNegocio, idConvenio, rutEmpresa, tipoProceso, sb, e);
		} catch (ClassNotFoundException e)
		{
			error = false;
			StringBuffer sb = new StringBuffer("problemas al instanciar clases de validaciones");
			logger.error(sb, e);
			reportaError(parametrosNegocio, idConvenio, rutEmpresa, tipoProceso, sb, e);
		} catch (InstantiationException e)
		{
			error = false;
			StringBuffer sb = new StringBuffer("problemas al instanciar clases de validaciones");
			logger.error(sb, e);
			reportaError(parametrosNegocio, idConvenio, rutEmpresa, tipoProceso, sb, e);
		} catch (InvocationTargetException e)
		{
			error = false;
			StringBuffer sb = new StringBuffer("problemas al instanciar clases de validaciones");
			logger.error(sb, e);
			reportaError(parametrosNegocio, idConvenio, rutEmpresa, tipoProceso, sb, e);
		} catch (IllegalAccessException e)
		{
			error = false;
			StringBuffer sb = new StringBuffer("problemas al instanciar clases de validaciones");
			logger.error(sb, e);
			reportaError(parametrosNegocio, idConvenio, rutEmpresa, tipoProceso, sb, e);
		} catch (NoSuchMethodException e)
		{
			error = false;
			StringBuffer sb = new StringBuffer("problemas al instanciar clases de validaciones");
			logger.error(sb, e);
			reportaError(parametrosNegocio, idConvenio, rutEmpresa, tipoProceso, sb, e);
		} catch (SeccionException e)
		{
			error = false;
			reportaError(parametrosNegocio, idConvenio, rutEmpresa, tipoProceso, new StringBuffer("problemas al generar comprobante"), e);
		} catch (DaoException e)
		{
			error = false;
			reportaError(parametrosNegocio, idConvenio, rutEmpresa, tipoProceso, new StringBuffer("problemas con la Dase de Datos"), e);
		} catch (Throwable e)
		{
			error = false;
			StringBuffer sb = new StringBuffer("problema no identificado");
			logger.error(sb, e);
			reportaError(parametrosNegocio, idConvenio, rutEmpresa, tipoProceso, sb, e);
		} finally 
		{
			timerStress.finaliza(0);
			HibernateUtil.enableInterceptor();
			String nominaEnProceso = this.nomina==null?"sin nomina":"[" + this.nomina.getRutEmpresa() + '/' + this.nomina.getIdConvenio() + '/' + this.nomina.getTipoProceso() + ']';
			try
			{
				Transaction tx = this.session.getTransaction();
				if (tx != null && tx.isActive())
				{
					logger.info("Rollback para " + nominaEnProceso);
					tx.rollback();
				}
			} catch (Throwable e)
			{
				logger.error("Problemas en rollback de transaccion bulk");
			}
			if (bulkNominaDao != null && bulkNominaDao.getSession() != null)
			{
				try
				{
					bulkNominaDao.getSession().close();
				} catch (Throwable e)
				{
					logger.error("Problemas liberando la sesion bulk");
				}
			}
			bulkNominaDao = null;
			if (!error && this.nomina != null)
			{
				logger.info("Hubo problemas con la nomina, debe quedar en 'no cargada'");
				try
				{
					this.nomina.setNumCotizaciones(0);
					this.nomina.setNumCotizCorregidas(0);
					this.nomina.setMontoNum(0);
					this.nomina.setIdEstado(Constants.EST_NOM_NO_CARGADA);
					this.session.update(this.nomina);
					logger.info("nomina guardada como 'no cargada'" + nominaEnProceso);
				} catch (Throwable e)
				{
					logger.error("Problemas tratando de actualizar el estado de nomina a 'no cargada'" + nominaEnProceso, e);
				}
			}
		}
		Transaction tx = null;
		try
		{
			tx = this.session.beginTransaction();
			this.parametrosDao.guardaApellAprendidos((List) parametrosNegocio.get("apellAprendidos"));
			tx.commit();
		} catch (Exception e)
		{
			reportaError(parametrosNegocio, idConvenio, rutEmpresa, tipoProceso, new StringBuffer("problema no identificado al finalizar procesamiento"), tx, e);
		} finally
		{
			try
			{
				this.session.close();
			} catch (Exception e)
			{
				logger.error("Problemas liberando la sesion de hibernate", e);
			}
		}
	}

	/**
	 * Ejecuta solamente validaciones de cargas familiares (VN120, VN130, VN140)
	 * 
	 * @param tipoProceso
	 * @param cotizantes
	 * @param parametrosNegocio
	 * @return
	 * @throws Exception
	 */
	public HashMap validaCargas(char tipoProceso, HashMap nominas, HashMap parametrosNegocio, String idPersona) throws Exception {
		try {
			ProcArchThread proceso = new ProcArchThread(tipoProceso, nominas, parametrosNegocio, idPersona, this.session,	this.validacionDao, this.parametrosDao, this.grupoConvenio, this.convenio);
			proceso.start();
			return nominas; 
		} catch (Exception e) {
			logger.error("\n\n\nProcesadorMgr:validaCargas:ERROR:", e);
			throw e;
		}
	}

	/**
	 * Indica si es necesario validar el monto informado
	 * @param isPrivada
	 * @param periodoInformado
	 * @param iniVigSIS
	 * @param minTrabSIS+
	 * -*
	 * @param cantidadCotizantesEnNomina
	 * @return
	 */
	private boolean seDebeValidarSIS(HashMap parametrosNegocio) {
		boolean respuesta = false;
		
		String periodoInformado="";
		if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA)){
			 periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		
		}else if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
			 periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO_INDEPENDIENTE);
		}
		//String periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		String iniVigSIS = (String)parametrosNegocio.get("" + Constants.PARAM_PRIMER_PERIODO_VIG_PLENA_SIS);
		int minTrabSIS = Integer.parseInt((String)parametrosNegocio.get("" + Constants.PARAM_MINIMO_TRABAJADORES_SIS));
		boolean isPrivada = ((Integer) parametrosNegocio.get("empresaPrivada")).intValue() == 0 ? true : false;
		int cantidadCotizantesEnNomina = ((Integer)parametrosNegocio.get("cantidadCotizantes")).intValue();
		boolean informaSisEnNomina = ((Boolean)parametrosNegocio.get("informaSisEnNomina")).booleanValue();

		//Validaciones de Negocio
		if(isPrivada) {
			//Empresa Privada
			if(!respuesta && informaSisEnNomina) respuesta = true;
			if(!respuesta && cantidadCotizantesEnNomina > minTrabSIS) respuesta = true;
			if(!respuesta && Integer.parseInt(periodoInformado) >= Integer.parseInt(iniVigSIS) ) respuesta = true;
		}else{
			//Empresa Pública
			respuesta = true;
		}
		
		return respuesta;
	}	

	private void reportaError(HashMap parametrosNegocio, int idConvenio, int rutEmpresa, char tipoProceso, StringBuffer contenido, Throwable e)
	{
		e.printStackTrace(new PrintWriter(this.sw));
		reportaError(Mail.ERROR, tipoProceso, rutEmpresa, idConvenio, contenido, new StringBuffer(this.sw.toString()), parametrosNegocio);
	}

	private void reportaError(HashMap parametrosNegocio, int idConvenio, int rutEmpresa, char tipoProceso, StringBuffer contenido, Transaction tx2, Throwable e)
	{
		logger.error(contenido, e);
		if (tx2 != null)
			try
			{
				tx2.rollback();
			} catch (Exception rollEx)
			{
				logger.error("Problemas haciendo rollback!", rollEx);
			}
		e.printStackTrace(new PrintWriter(this.sw));
		reportaError(Mail.ERROR, tipoProceso, rutEmpresa, idConvenio, contenido, new StringBuffer(this.sw.toString()), parametrosNegocio);
	}

	/**
	 * carga datos asociados al procesamiento: convenio, grupoConvenio, datos de empresa, opcion de procesos
	 * 
	 * @param parametrosNegocio
	 * @param idConvenio
	 * @param rutEmpresa
	 * @param tipoProceso
	 * @return verdadero si no ocurrieron problemas, falso si no se encontro algun elemento relacionado al convenio
	 * @throws DaoException
	 */
	private boolean cargaData(HashMap parametrosNegocio, int idConvenio, int rutEmpresa, char tipoProceso) throws DaoException
	{
		this.convenio = this.validacionDao.getConvenio(idConvenio, rutEmpresa);
		if (this.convenio == null)
		{
			logger.error("\n\nERROR: no existe convenio?? tipoProceso:" + tipoProceso + ":idConvenio:" + idConvenio + ":rutEmpresa:" + rutEmpresa + "::");
			reportaError(Mail.ERROR, tipoProceso, rutEmpresa, idConvenio, new StringBuffer(), new StringBuffer("no se encontro convenio en DB."), parametrosNegocio);
			return false;
		}
		this.grupoConvenio = this.validacionDao.getGrupoConvenio(this.convenio.getIdGrupoConvenio());
		if (this.grupoConvenio == null)
		{
			logger.error("\n\nERROR: no existe grupo convenio?? tipoProceso:" + tipoProceso + ":idConvenio:" + idConvenio + ":rutEmpresa:" + rutEmpresa + "::");
			reportaError(Mail.ERROR, tipoProceso, rutEmpresa, idConvenio, new StringBuffer(), new StringBuffer("no se encontro grupo convenio en DB. idGrupoConvenio:"
					+ this.convenio.getIdGrupoConvenio() + "::"), parametrosNegocio);
			return false;
		}
		this.convenioMod = new ConvenioVO(this.convenio, this.grupoConvenio);

		EmpresaVO empresa = this.validacionDao.getEmpresa(rutEmpresa);
		if (empresa == null)
		{
			logger.error("\n\nERROR: no existe empresa?? tipoProceso:" + tipoProceso + ":idConvenio:" + idConvenio + ":rutEmpresa:" + rutEmpresa + "::");
			reportaError(Mail.ERROR, tipoProceso, rutEmpresa, idConvenio, new StringBuffer(), new StringBuffer("no se encontro empresa en DB."), parametrosNegocio);
			return false;
		}
		parametrosNegocio.put("empresaPrivada", empresa.getPrivada());

		OpcionProcVO opcion = this.validacionDao.getOpcionProcesos(this.convenioMod.getIdOpcion());
		if (opcion == null)
		{
			logger.error("\n\nERROR: no existe opcionProcesos?? tipoProceso:" + tipoProceso + ":idConvenio:" + idConvenio + ":rutEmpresa:" + rutEmpresa + "::");
			reportaError(Mail.ERROR, tipoProceso, rutEmpresa, idConvenio, new StringBuffer(), new StringBuffer("no se encontro convenio en DB."), parametrosNegocio);
			return false;
		}
		parametrosNegocio.put("opcionProcesos", opcion);
		return true;
	}
	
	/**
	 * Setea en los parametros de negocio la cantidad de Cotizantes informados en la Nómina y si por lo menos uno de los trabajadores informa SIS
	 * @author asepulveda 29-06-2010
	 * 
	 * @param mapConceptos
	 * @param bais
	 * @return HashMap parametrosNegocio
	 * @throws IOException
	 */
	private HashMap obtieneInfoBasicaSISByNomina(Properties mapConceptos, ByteArrayInputStream bais, HashMap parametrosNegocio) throws IOException 
	{
		HashMap cotizantes = new HashMap();
		boolean informaSis = false;
		boolean periodoMalo = false;

		//Se determina la cantidad máxima de conceptos informados en nómina con separador
		int contador;
		MapeoConceptoVO mc = (MapeoConceptoVO) this.listaMapeo.get("" + Constants.CONCEPTO_RUT);
		char tipoProceso = mc.getTipoProceso();

		try {
			int idMapaNom = 0;
			if (tipoProceso == 'R')
				idMapaNom = this.grupoConvenio.getIdMapaNomRem();
			else if (tipoProceso == 'G')
				idMapaNom = this.grupoConvenio.getIdMapaNomGra();
			else if (tipoProceso == 'A')
				idMapaNom = this.grupoConvenio.getIdMapaNomRel();
			else
				idMapaNom = this.grupoConvenio.getIdMapaNomDep();
			contador = this.validacionDao.getCantidadDeConceptos(idMapaNom, tipoProceso);
		} catch (Exception e){
			contador = 0;
		}

		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(bais));
		String line;
		while ((line = br.readLine()) != null) // por cada linea
		{
			logger.debug("linea:" + line + "::");
			/*
			 * a cada conceptoVO le busca su mapeoConceptoVO, para obtener el largo y posicion, entonces llena HashMap con nombreConcepto y valorConcepto, para despues accederlos por properties
			 * cargado desde archivo
			 */
			line = Utils.corrigeLineaConSeparador(this.listaMapeo, line, contador);
			this.mapeoValores = Utils.parseoValores(this.listaConceptos, this.listaMapeo, line);
			
			contadorLinea=contadorLinea+1;
			lineaObtenida=new StringBuffer(line);
			/*
			 * aplica cada una de las validaciones (recuperadas desde la DB y marcadas en 'ejecutarEn' como 'S'), siguiendo el arbol construido en desicionValidacion. A la vez asigna valor (real,
			 * estandar) a cada campo validado.
			 */
			Utils setter = new Utils(mapConceptos, this.mapeoValores);

			String rutTmp = setter.setString("idCotizante");

			// En caso de que el RUT hubiese tenido guión.
//			clillo 12/12/14 Se cambia for
			String rutTmpSinBlancos = rutTmp.replaceAll(" ", "");
			/*for (int x=0; x < rutTmp.length(); x++) {
				//Evaluar si cambiar por línea comentada.
				//if (rut.charAt(x) != ' ' && rut.charAt(x) != '-')			
				if (rutTmp.charAt(x) != ' ')
					rutTmpSinBlancos += rutTmp.charAt(x);
			}*/

			//rutTmp = (!rutTmp.equals("") ? rutTmp.substring(0, rutTmp.length() - 1) : "");
			rutTmp = (!rutTmpSinBlancos.equals("") ? rutTmpSinBlancos.substring(0, rutTmpSinBlancos.length() - 1) : "");
			
			if ( !cotizantes.containsKey("" + rutTmp) )
				cotizantes.put(rutTmp, rutTmp);
			
			
			String periodoInformado="";
			if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA)){
				 periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO);
			
			}else if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
				 periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO_INDEPENDIENTE);
			}
			
			//String periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO);
			periodoInformado = periodoInformado.trim().substring(4,6);
			String mesPeriodo = (String) this.mapeoValores.get("Mes");
			if(!periodoMalo && !periodoInformado.equals(mesPeriodo.trim())) {
				periodoMalo = true;
			}
			
			
			String previsionSIS = setter.setString("previsionSIS");
			if( !informaSis && previsionSIS != null){
				try
				{
					int ps = Integer.parseInt(previsionSIS);
					if(ps>0) informaSis=true;
				} catch (Exception e)
				{
					// TODO: handle exception
				}
			}
		}
		
		parametrosNegocio.put("cantidadCotizantes", new Integer(cotizantes.size()));
		parametrosNegocio.put("informaSisEnNomina", new Boolean(informaSis));
		parametrosNegocio.put("periodoIncorrecto",  new Boolean(periodoMalo));
		return parametrosNegocio;
	}

	/*private boolean isPeriodoIncorrecto(Properties mapConceptos, ByteArrayInputStream bais, HashMap parametrosNegocio) throws IOException {
		boolean periodoMalo = false;

		BufferedReader br = new BufferedReader(new InputStreamReader(bais));
		String line;
		String periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		periodoInformado = periodoInformado.trim().substring(4,6);

		try {
			while ((line = br.readLine()) != null) {
				this.mapeoValores = Utils.parseoValores(this.listaConceptos, this.listaMapeo, line);
				String mesPeriodo = (String) this.mapeoValores.get("Mes");

				if(!periodoInformado.equals(mesPeriodo.trim())) {
					br.close();
					return periodoMalo = true;
				}
			}
		} catch (IOException e) {}
		return periodoMalo;
	}*/

	private void aplicaValidaciones(Properties mapConceptos, HashMap parametrosNegocio, int idConvenio, int rutEmpresa, char tipoProceso, HashMap cotizantes, HashMap cotizantesPendientes,
			HashMap cotPendientes) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, DaoException
	{
		byte[] data = null;
		try
		{
			data = this.parametrosDao.unzipData(this.documento.getData());
		} catch (DaoException e)
		{
			logger.error("ERROR unzipData:", e);
		} catch (Exception e)
		{
			logger.error("ERROR unzipData:", e);
		}

		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		
		//Cuenta la cantidad de Cotizantes informados en la Nómina
		parametrosNegocio = this.obtieneInfoBasicaSISByNomina(mapConceptos, new ByteArrayInputStream(data), parametrosNegocio);		

		EmpresaVO empresa = this.validacionDao.getEmpresa(rutEmpresa);
		parametrosNegocio.put("empresaPrivada", empresa.getPrivada());
		parametrosNegocio.put("tipoEmpresa", empresa.getTipo());

		//System.out.println(this.isPeriodoIncorrecto(mapConceptos, new ByteArrayInputStream(data), parametrosNegocio));

		BufferedReader br = new BufferedReader(new InputStreamReader(bais));

		//Se determina la cantidad máxima de conceptos informados en nómina con separador
		int contador;
		try {
			int idMapaNom = 0;
			if (tipoProceso == 'R')
				idMapaNom = this.grupoConvenio.getIdMapaNomRem();
			else if (tipoProceso == 'G')
				idMapaNom = this.grupoConvenio.getIdMapaNomGra();
			else if (tipoProceso == 'A')
				idMapaNom = this.grupoConvenio.getIdMapaNomRel();
			else
				idMapaNom = this.grupoConvenio.getIdMapaNomDep();
			contador = this.validacionDao.getCantidadDeConceptos(idMapaNom, tipoProceso);
		} catch (Exception e){
			contador = 0;
		}

		//TODO GMALLEA 07-05-2012 Obtenemos el parametrosNegocio
			ConvenioVO convenioVO = this.validacionDao.getConvenio(idConvenio, rutEmpresa);
			GrupoConvenioVO grupoConvenioVO = this.validacionDao.getGrupoConvenio(convenioVO.getIdGrupoConvenio());
			
			int idMapaNom=0;
			if (tipoProceso == 'R')
				idMapaNom = grupoConvenioVO.getIdMapaNomRem();
			else if (tipoProceso == 'G')
				idMapaNom = grupoConvenioVO.getIdMapaNomGra();
			else if (tipoProceso == 'A')
				idMapaNom = grupoConvenioVO.getIdMapaNomRel();
			else if (tipoProceso == 'D')
				idMapaNom = grupoConvenioVO.getIdMapaNomDep();
			
			parametrosNegocio.put("mapeoFonasa", "");
			
				MapeoConceptoVO mapeoConceptoVO =this.validacionDao.getMapeoConcepto(""+tipoProceso, idMapaNom, 133);
			
				if(mapeoConceptoVO.getPosicion() == 0 &&	mapeoConceptoVO.getLargo() == 0){
					
					parametrosNegocio.put("mapeoFonasa", "NOK");
					
				}

				//Inicio clillo 24-04-2014 por Reliquidación
				String periodoCotizacion = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO);
				String periodoInformado= "0";
				//Fin clillo
				
		//Fin GMALLEA
		
		String line;
		HashMap hashCargasFamiliares = this.getHashCargasPorEmpresa(rutEmpresa, this.convenio.getIdCcaf());
		while ((line = br.readLine()) != null) // por cada linea
		{
			logger.debug("linea:" + line + "::");
			this.count++;
			this.mapeoValores = new HashMap();
			boolean resultValidacion = true;
			this.isMovimiento = false;
			
			line = Utils.corrigeLineaConSeparador(this.listaMapeo, line, contador);
			
			contadorLinea=contadorLinea+1;
			lineaObtenida=new StringBuffer(line);
			
			if(!line.trim().equals("") && line.length()>20){
			/*
			 * a cada conceptoVO le busca su mapeoConceptoVO, para obtener el largo y posicion, entonces llena HashMap con nombreConcepto y valorConcepto, para despues accederlos por properties
			 * cargado desde archivo
			 */
			//TODO csanchez. Por requerimiento del día 11/11/11 se hace un mapeo especial para las Nóminas cuyo Grupo de Convenio sea Previred y con separador
			//clillo 21/4/16 se modifica mapeo para Productos Caja, dias trabajados = 30 por defecto	
			this.mapeoValores = Utils.parseoValores(this.listaConceptos, this.listaMapeo, line, this.grupoConvenio.isPrevired(), this.grupoConvenio.isProdCaja());
			
//			clillo 3-12-14 por Reliquidación
			if (tipoProceso == 'A'){
				String fechaRel= (String)this.mapeoValores.get("Fecha Inicio Reliquidacion");
				if(!fechaRel.equals("")){
					periodoInformado= fechaRel.substring(6, 10) + fechaRel.substring(3, 5);
				}
			}
			//fin 
			
			/*
			 * aplica cada una de las validaciones (recuperadas desde la DB y marcadas en 'ejecutarEn' como 'S'), siguiendo el arbol construido en desicionValidacion. A la vez asigna valor (real,
			 * estandar) a cada campo validado.
			 */
			Utils setter = new Utils(mapConceptos, this.mapeoValores);
			CotizanteVO cotizante = new CotizanteVO(tipoProceso, rutEmpresa, idConvenio);
			cotizante.setLinea(line);
			CotizacionVO cotizacion = creaCotizacion(tipoProceso);
			cotizante.setCotizacion(cotizacion);
			
//			clillo 3-12-14 por Reliquidación
			cotizante.setPeriodo(Integer.parseInt(periodoInformado));
			cotizacion.setPeriodo(Integer.parseInt(periodoInformado));
			//fin
			
			if (tipoProceso == 'R')
				cotizante.setAfpVoluntario(((CotizacionREVO) cotizacion).isReforma(setter));

			ValidacionVO siguiente = getPrimeraValidacion();
			HashMap listaAvisos = new HashMap();
			String rutTmp = setter.setString("idCotizante");

			// En caso de que el RUT hubiese tenido guión.
//			clillo 12/12/14 Se cambia for
			String rutTmpSinBlancos = rutTmp.replaceAll(" ", "");
			/*for (int x=0; x < rutTmp.length(); x++) {
				if (rutTmp.charAt(x) != ' ')
					rutTmpSinBlancos += rutTmp.charAt(x);
			}*/

			//rutTmp = (!rutTmp.equals("") ? rutTmp.substring(0, rutTmp.length() - 1) : "");
			rutTmp = (!rutTmpSinBlancos.equals("") ? rutTmpSinBlancos.substring(0, rutTmpSinBlancos.length() - 1) : "");

			long rutPaso = Long.parseLong(rutTmp);
			
//			clillo 3-12-14 por Reliquidación
			//if (!cotizantes.containsKey("" + rutPaso) || this.convenioMod.getCalculoMovPersonal() == Constants.SUMAR_MONTOS_MOVIMIENTO) // si la cotizacion ya existia
			if (!cotizantes.containsKey(periodoInformado + rutPaso) || this.convenioMod.getCalculoMovPersonal() == Constants.SUMAR_MONTOS_MOVIMIENTO ) // si la cotizacion ya existia
			{
				while (siguiente != null)
				{
					Class validacion = Class.forName(siguiente.getClaseValidador().trim());
					Class[] argTypes = { HashMap.class, Utils.class, Session.class, ConvenioVO.class, List.class, List.class, List.class };
					Object[] argValues = { parametrosNegocio, setter, this.session, this.convenioMod, siguiente.getConceptos(), this.listaConceptos, this.listaMapeosValidaciones };
					Validacion valida = (Validacion) validacion.getConstructor(argTypes).newInstance(argValues);
					//logger.debug("siguiente.getClaseValidador(): " + siguiente.getClaseValidador() + "::");
					if (siguiente.getClaseValidador().trim().endsWith("VN110") ||
						siguiente.getClaseValidador().trim().endsWith("VN120") ||
						siguiente.getClaseValidador().trim().endsWith("VN130") ||
						siguiente.getClaseValidador().trim().endsWith("VN140")) {
						Class[] argTypes2 = { HashMap.class, Utils.class, Session.class, ConvenioVO.class, List.class, List.class, List.class, HashMap.class };
						Object[] argValues2 = { parametrosNegocio, setter, this.session, this.convenioMod, siguiente.getConceptos(), this.listaConceptos, this.listaMapeosValidaciones, hashCargasFamiliares };
						valida = (Validacion) validacion.getConstructor(argTypes2).newInstance(argValues2);
					}

					int codResult = valida.valida(cotizante);

					if ((codResult != 0 && isValidable(codResult)) || codResult >= 1000)
					{// por cada validacion, guardo errores encontrados
						logger.debug("\n\n\n validacion incorrecta!:RUT:" + rutTmp + ":validacion fallida:" + siguiente.getIdValidacion() + ":codigo error:" + codResult + "::");
						//System.out.println("Valor informado en nómina: " + valida.getValor());
						if (codResult == Constants.CAIDA_SISTEMA || codResult == Constants.SIN_CONCEPTOS)
							codResult = Constants.INICIO_COD_ERROR_GENERICO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));
						if (codResult == Constants.CONCEPTOS_SIN_VALOR)
							codResult = Constants.INICIO_COD_ERROR_VACIO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));
						int contTrabPes = 0;

						String _codResult = "" + codResult;
						if (_codResult.length() > 4)
						{
							while (contTrabPes < _codResult.length())
							{
								codResult = Integer.parseInt(_codResult.substring(contTrabPes, 3 + contTrabPes));
								if (isValidable(codResult))
								{
									String mensaje = ((valida.getMensaje() == null || valida.getMensaje().trim().equals("")) ? siguiente.getIdValidacion() : valida.getMensaje());
									this.listaMensajesValidacion.put(String.valueOf(new Integer(codResult)), mensaje);
									if (!isAviso(codResult))// es error
									{
										registraPendiente(tipoProceso, rutTmp, codResult, mensaje, cotizantesPendientes, line, resultValidacion, valida.getValor(), periodoInformado);
										cotPendientes.put(rutTmp, cotizante);
										resultValidacion = false;
									} else
										listaAvisos.put("" + codResult, mensaje);
								}
								contTrabPes += 3;
							}
						} else
						{
							logger.debug("\n\n\n validacion incorrecta!:RUT:" + rutTmp + ":validacion fallida:" + siguiente.getIdValidacion() + ":codigo error:" + codResult + "::");
							String mensaje = ((valida.getMensaje() == null || valida.getMensaje().trim().equals("")) ? siguiente.getIdValidacion() : valida.getMensaje());
							this.listaMensajesValidacion.put(String.valueOf(new Integer(codResult)), mensaje);
							if (!isAviso(codResult))// es error
							{
								registraPendiente(tipoProceso, rutTmp, codResult, mensaje, cotizantesPendientes, line, resultValidacion, valida.getValor(), periodoInformado);
								cotPendientes.put(rutTmp, cotizante);
								resultValidacion = false;
							} else
								listaAvisos.put("" + codResult, mensaje);
						}
					}/*
					 * else logger.info("validacion correcta!:RUT:" + cotizante.getIdCotizante() + ":validacion fallida:" + siguiente.getIdValidacion() + "::");
					 */
					siguiente = getSiguienteValidacion(valida.getResultado(), siguiente.getSiguientes(), this.listaValidaciones);
				} // fin validaciones por linea
			}else{
				cotizante.setIdCotizante(Integer.parseInt(rutTmp));
			}
			/*
			 * primero se valida si la nomina es de tipo R, si es asi, se busca dentro de los cotizantes ya procesados (validos) para saber si se trata de un movimiento de personal, y se aplican las
			 * validaciones correspondientes (tipo 'M' en la DB). Si no es tipo R , se verifica que la cotizacion no se encuentre repetida (validada o pendiente), y si se encuentra duplicada,
			 * automaticamente pasan todas las cotizaciones del mismo trabajador a pendiente.
			 * 
			 * si el resultado de las validaciones anteriores son exitosas => la cotizacion se registra como validada.
			 * 
			 * el HashMap de pendientes maneja como llave el correlativo de cotizaciones pendientes (idCotizPendiente) y el HashMap de validados tiene como llave el rut del cotizante.
			 */
			// si es tipo R, valida movimiento de personal
			if (tipoProceso == 'R') // registra movimiento personal
			{
				HashMap listaErrores = registraMovimiento(cotizante, cotizantes, parametrosNegocio, mapConceptos, listaAvisos);
				if (listaErrores != null)
				{
					registraPendiente(tipoProceso, rutTmp, listaErrores, cotizantesPendientes, line, resultValidacion, periodoInformado);
					cotPendientes.put(rutTmp, cotizante);
					resultValidacion = false;
					if (this.isMovimiento)
						continue;
				} else if (this.isMovimiento)
				{
					addCausaAviso(rutTmp, cotizante.getIdCotizante(), listaAvisos, cotizante, tipoProceso);
					//continue;
				}
				//if (!cotizante.isAfpVoluntario() && !this.isMovimiento)// si no es voluntario y no es movimiento busca APVS
				if (!cotizante.isAfpVoluntario() )
				{
					listaErrores = registraAPV(cotizante, cotizantes, parametrosNegocio, mapConceptos, listaAvisos);
					if (listaErrores != null)
					{
						registraPendiente(tipoProceso, rutTmp, listaErrores, cotizantesPendientes, line, resultValidacion, periodoInformado);
						cotPendientes.put(rutTmp, cotizante);
						resultValidacion = false;
					} else
						addCausaAviso(rutTmp, cotizante.getIdCotizante(), listaAvisos, cotizante, tipoProceso);
				}
				
				//TODO 11-06-2012 GMALLEA  SUMAR VALORES SI ESTAN REPETIDOS...
				if(cotizantes.containsKey(periodoInformado + rutPaso)){
					if(!this.grupoConvenio.isPrevired()){
						if(this.convenio.getCalculoMovPersonal() == 1 && (cotizante.getTipoProceso() == 'R' || cotizante.getTipoProceso() == 'G' || cotizante.getTipoProceso() == 'A')){
							cotizante = this.cotizantesRepetiposCalculaMovPersonal(cotizante, parametrosNegocio, setter, rutTmp, tipoProceso, cotizantesPendientes, line, resultValidacion, cotPendientes, listaAvisos,cotizantes);
						}else if(this.convenio.getCalculoMovPersonal() == 0 && cotizante.getTipoProceso() != 'G'){
							cotizante = this.cotizantesRepetiposNOCalculaMovPersonal(cotizante, cotizantes);
						}
					}
				}
			 // validar que cotizante no este repetido, (o registrado con error) antes en la misma nomina
//				clillo 3-12-14 por Reliquidación
			//}else if (cotizantes.containsKey("" + cotizante.getIdCotizante()) || cuentaCotizPendiente("" + cotizante.getIdCotizante(), cotizantesPendientes) > 1)
			}else if (cotizantes.containsKey(periodoInformado + cotizante.getIdCotizante()) || cuentaCotizPendiente(cotizante.getIdCotizante(), periodoInformado, cotizantesPendientes) > 1)
			{
				registraPendiente(tipoProceso, "" + cotizante.getIdCotizante(), Constants.TIPO_CAUSA_RUT_REPETIDO, "cotizante repetido", cotizantesPendientes, line, resultValidacion, null, periodoInformado);
				cotPendientes.put("" + cotizante.getIdCotizante(), cotizante);
				resultValidacion = false;
			}

			if (resultValidacion)// cotizacion esta validada => se agrega como correcta
			{
				addCausaAviso(rutTmp, cotizante.getIdCotizante(), listaAvisos, cotizante, tipoProceso);
				cotizacion.setIdCotizante(cotizante.getIdCotizante());
				
				if (tipoProceso == 'G' || tipoProceso == 'A'){
					setFechasLimite(tipoProceso, cotizacion);
				}
				
//				clillo 3-12-14 por Reliquidación
				//if( !cotizantes.containsKey("" + cotizacion.getIdCotizante())) {
				//	cotizantes.put("" + cotizacion.getIdCotizante(), cotizante);
				if( !cotizantes.containsKey(periodoInformado + cotizacion.getIdCotizante())) {
					cotizantes.put(periodoInformado + cotizacion.getIdCotizante(), cotizante);
				}else{
					if(!this.grupoConvenio.isPrevired()){
						//TODO GMALLEA 13-06-2012 Si movimiento personal es 1 o 0, cotizante repetido y tipo de nomina (r,g,a) se agrega el cotizante 
						if(this.convenio.getCalculoMovPersonal() == 1 && (cotizante.getTipoProceso() == 'R' || cotizante.getTipoProceso() == 'G' || cotizante.getTipoProceso() == 'A')){
							//clillo 4-2-15 por Reliquidación
							cotizantes.put(periodoInformado + cotizacion.getIdCotizante(), cotizante);
						}else if(this.convenio.getCalculoMovPersonal() == 0 && cotizante.getTipoProceso() != 'G'){
							//clillo 4-2-15 por Reliquidación
							cotizantes.put(periodoInformado + cotizacion.getIdCotizante(), cotizante);
						}
					}
				}
				
				
			} else{
				// como la cotizacion tenia errores, se registran en tabla causa los problemas encontrados
				this.hashDatos.put(rutTmp, cotizantesPendientes);
				this.listadoAValidar.put(rutTmp, line);
				registraPendiente(tipoProceso, rutTmp, listaAvisos, cotizantesPendientes, line, resultValidacion, periodoInformado);
			}
		}	
	}
		bais.close();
		br.close();
	}

	private ValidacionVO getPrimeraValidacion()
	{
		for (Iterator it = this.listaValidaciones.iterator(); it.hasNext();)
		{
			ValidacionVO val = (ValidacionVO) it.next();
			if (val.getIdValidacion().trim().equals(Constants.PRIMERA_VALIDACION))
				return val;
		}
		return null;
	}

	private ValidacionVO getPrimeraValidacionMP()
	{
		for (Iterator it = this.lstValidaMovPers.iterator(); it.hasNext();)
		{
			ValidacionVO val = (ValidacionVO) it.next();
			if (val.getIdValidacion().trim().equals(Constants.PRIMERA_VALIDACION_MP))
				return val;
		}
		return null;
	}

	private ValidacionVO getPrimeraValidacionAPV()
	{
		for (Iterator it = this.lstValidaAPVs.iterator(); it.hasNext();)
		{
			ValidacionVO val = (ValidacionVO) it.next();
			if (val.getIdValidacion().trim().equals(Constants.PRIMERA_VALIDACION_APV))
				return val;
		}
		return null;
	}

	/*private ValidacionVO getPrimeraValidacionCargas()
	{
		for (Iterator it = this.listaValidaciones.iterator(); it.hasNext();)
		{
			ValidacionVO val = (ValidacionVO) it.next();
			if (val.getIdValidacion().trim().equals(Constants.PRIMERA_VALIDACION_CARGASFAM))
				return val;
		}
		return null;
	}*/

	private void generaComprobante(char tipoProceso, int idDocumento, int numTrabs, long folio, HashMap cotizantes, HashMap parametros) throws DaoException, SeccionException
	{
		try
		{
			this.comprobante = new ComprobanteVO(0
												,("" + Constants.EST_CMP_POR_PAGAR).charAt(0)
												,idDocumento
												,new Timestamp((new java.util.Date()).getTime())
												,(byte) 0
												,(byte) 0
												,new Timestamp((new java.util.Date(1)).getTime())
												,this.nomina.getNumCotizaciones()
												,0);

			SeccionMgr seccionMgr = new SeccionMgr(this.session, tipoProceso, parametros);
			seccionMgr.cargaProperties();

			this.secciones = seccionMgr.generaSecciones(this.convenioMod, cotizantes);
			this.comprobante.setTotal(seccionMgr.getTotal());
			this.comprobante.setFolioTesoreria(folio);
			this.comprobante.setNumTrabajadores(numTrabs);
			this.comprobante.setRenta_imponible(seccionMgr.getRentaImponible());
			//this.comprobante.setRenta_imponible(this.actualizaRentaImpComprobante());
			logger.info("Fin generaComprobante, folio: " + folio + "::");
		} catch (SeccionException se)
		{
			logger.error("ERROR genera comprobante:", se);
			throw se;
		} catch (DaoException de)
		{
			logger.error("ERROR genera comprobante:", de);
			throw de;
		}
	}

	private void actualizaComprobante(char tipoProceso, int numTrabs, long codBarras, CotizanteVO oldCotiz, CotizanteVO cotizante, HashMap parametros) throws DaoException, SeccionException
	{
		try
		{
			this.comprobante = this.validacionDao.getComprobanteVO(codBarras);

			SeccionMgr seccionMgr = new SeccionMgr(this.session, tipoProceso, parametros);
			seccionMgr.cargaProperties();
			long rentaImponible= this.comprobante.getRenta_imponible();
			this.secciones = seccionMgr.actualizaSecciones(this.convenioMod, oldCotiz, cotizante, this.comprobante.getSecciones(), rentaImponible);
			this.comprobante.setTotal(seccionMgr.getTotal());
			this.comprobante.setSecciones(this.secciones);
			this.comprobante.setNumTrabajadores(numTrabs);
			this.comprobante.setRenta_imponible(seccionMgr.getRentaImponible());
			//this.comprobante.setRenta_imponible(this.actualizaRentaImpComprobante());
			logger.info("Fin actualizaComprobante, cbarra: " + codBarras + "::");
		} catch (SeccionException se)
		{
			logger.error("ERROR genera comprobante:", se);
			throw se;
		} catch (DaoException de)
		{
			logger.error("ERROR genera comprobante:", de);
			throw de;
		}
	}

	private void setFechasLimite(char tipoProceso, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'G')
		{
			if (this.inicioNomina.after(((CotizacionGRVO) cotizacion).getInicio()))
				this.inicioNomina = ((CotizacionGRVO) cotizacion).getInicio();
			if (this.finalNomina.before(((CotizacionGRVO) cotizacion).getTermino()))
				this.finalNomina = ((CotizacionGRVO) cotizacion).getTermino();
		} else if (tipoProceso == 'A')
		{
			if (this.inicioNomina.after(((CotizacionRAVO) cotizacion).getInicio()))
				this.inicioNomina = ((CotizacionRAVO) cotizacion).getInicio();
			//if (this.finalNomina.before(((CotizacionRAVO) cotizacion).getTermino()))
			//	this.finalNomina = ((CotizacionRAVO) cotizacion).getTermino();
		}
	}

	/**
	 * registra las causas encontradas durante validaciones secundarias (apvs, movtos, ...)
	 * 
	 * @param tipoProceso
	 * @param idCotizante
	 * @param listaCausas
	 * @param cotizantesPendientes
	 * @param line
	 * @param flag
	 */
	private void registraPendiente(char tipoProceso, String idCotizante, HashMap listaCausas, HashMap cotizantesPendientes, String line, boolean flag, String periodo)
	{
		int n = cotizantesPendientes.size();
		logger.debug("ProcesadorMgr:registraPendiente1: en nomina:" + idCotizante + ":tipoProceso:" + tipoProceso + ":idCotizPendiente:" + n + ":size:" + cotizantesPendientes.size() + "::");
		CotizacionPendienteVO cp = null;
		if (flag) // si es el primer error encontrado en la liena, lo crea
			cp = creaCotizacionPendiente(tipoProceso, idCotizante, n + 1, line, periodo);
		else if (cotizantesPendientes.containsKey("" + n)) // si ya habia encontrado errores en la linea, lo busca
			cp = (CotizacionPendienteVO) cotizantesPendientes.get("" + n);

		if (cp != null)
		{
		//TODO 06/06/2012 GMALLEA Se recorren las apv para obtener las entidades 
		//ya que no se esta mostrando en el listado de errores.
			String entidadApv="";
			for (Iterator itMP = listaCausas.keySet().iterator(); itMP.hasNext();)
			{
				String idCausa = (String) itMP.next();
				if(idCausa.equals("1700")){
					entidadApv= (String)this.mapeoValores.get("Entidad APV 1");
				}else if(idCausa.equals("1701")){
					entidadApv= (String)this.mapeoValores.get("Entidad APV 2");
				}else if(idCausa.equals("1702")){
					entidadApv= (String)this.mapeoValores.get("Entidad APV 3");
				}else if(idCausa.equals("1703")){
					entidadApv= (String)this.mapeoValores.get("Entidad APV 4");
				}else if(idCausa.equals("1704")){
					entidadApv= (String)this.mapeoValores.get("Entidad APV 5");
				}
				creaCausa(tipoProceso, new Integer(idCausa).intValue(), (String) listaCausas.get(idCausa) + ", RUT:" + idCotizante, cp, entidadApv, periodo);
			}
			cotizantesPendientes.put("" + cp.getIdCotizPendiente(), cp);
		}
		this.listaCausaAviso.remove(periodo + "" + Integer.parseInt(idCotizante));
		logger.debug("ProcesadorMgr:registraPendiente: fin:nPendientes:" + cotizantesPendientes.size() + "::");
	}

	/**
	 * registra las causas encontradas durante validaciones principales
	 * 
	 * @param tipoProceso
	 * @param idCotizante
	 * @param causa
	 * @param msg
	 * @param cotizantesPendientes
	 * @param line
	 * @param flag
	 */
	private void registraPendiente(char tipoProceso, String idCotizante, int causa, String msg, HashMap cotizantesPendientes, String line, boolean flag, String valorInformado, String periodo)
	{
		boolean errorRepetido=false;
		int n = cotizantesPendientes.size();
		logger.debug("ProcesadorMgr:registraPendiente2: en nomina:" + idCotizante + ",Periodo:"+ periodo + ",tipoProceso:" + tipoProceso + ",idCotizPendiente:" + n + ",size:" + cotizantesPendientes.size() + ":causa:"
				+ causa + "::");
		CotizacionPendienteVO cp = null;
		if (flag)// si es el primer error encontrado en la liena, lo crea
			cp = creaCotizacionPendiente(tipoProceso, idCotizante, n + 1, line, periodo);
		else if (cotizantesPendientes.containsKey("" + n))// si ya habia encontrado errores en la linea, lo busca
			cp = (CotizacionPendienteVO) cotizantesPendientes.get("" + n);

		if (cp != null)
		{		
			//TODO GMALLEA 25-06-2012 Se limpian los errores para que no se repitan...
			if(cp.getCausas() != null){
				for(Iterator it = cp.getCausas().iterator() ; it.hasNext();){
					CausaVO causaVO = (CausaVO) it.next();
					if(causaVO.getIdTipoCausa() == causa)
						errorRepetido=true;
				}
			}
			if(!errorRepetido){
				creaCausa(tipoProceso, causa, msg + ", RUT:" + idCotizante, cp, valorInformado, periodo);
				cotizantesPendientes.put("" + cp.getIdCotizPendiente(), cp);
				if(causa!=Constants.TIPO_CAUSA_RUT_REPETIDO){
					this.listaCausaAviso.remove(periodo + ""+ Integer.parseInt(idCotizante));
				}
			}
		}
	}

	private int cuentaCotizPendiente(int idCotizante, String periodo, HashMap cotizantesPendientes)
	{
		int contador = 0;
		for (Iterator it = cotizantesPendientes.values().iterator(); it.hasNext();)
		{
			CotizacionPendienteVO cp2 = (CotizacionPendienteVO) it.next();
			if (cp2 instanceof CotizacionPendienteRAVO) {
				if (Integer.parseInt(cp2.getIdCotizante())==idCotizante && cp2.getPeriodo()== Integer.parseInt(periodo))
					contador++;
			}else{
				if (Integer.parseInt(cp2.getIdCotizante())==idCotizante)
					contador++;
			}
		}
		return contador;
	}

	private HashMap registraAPV(CotizanteVO cotizante, HashMap cotizantes, HashMap parametrosNegocio, Properties mapConceptos, HashMap listaAvisos)
	{
		// logger.info("cotizante: agregando APVs");
		HashMap listaErrores = new HashMap();
		boolean flagValidacion = true;
		try
		{
			ValidacionVO siguiente = getPrimeraValidacionAPV();
			Utils setter = new Utils(mapConceptos, this.mapeoValores);
			if (cotizante.getApvList() == null)
				cotizante.setApvList(new ArrayList());
			while (siguiente != null)
			{
				Class validacion = Class.forName(siguiente.getClaseValidador().trim());
				Class[] argTypes = { HashMap.class, Utils.class, Session.class, ConvenioVO.class, List.class, List.class, List.class };
				Object[] argValues = { parametrosNegocio, setter, this.session, this.convenioMod, siguiente.getConceptos(), this.listaConceptos, this.listaMapeosValidaciones };
				Validacion valida = (Validacion) validacion.getConstructor(argTypes).newInstance(argValues);
				int codResult = valida.valida(cotizante);
				if (codResult != 0 && isValidable(codResult))
				{// por cada validacion, guardo errores encontrados
					String mensaje = ((valida.getMensaje() == null || valida.getMensaje().equals("")) ? siguiente.getIdValidacion() : valida.getMensaje());
					logger.debug("ERROR en APV:" + cotizante.getIdCotizante() + ":result:" + codResult + ", validacion:" + siguiente.getIdValidacion().trim() + "::");
					if (codResult == Constants.CAIDA_SISTEMA || codResult == Constants.SIN_CONCEPTOS)
						codResult = Constants.INICIO_COD_ERROR_GENERICO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));
					if (codResult == Constants.CONCEPTOS_SIN_VALOR)
						codResult = Constants.INICIO_COD_ERROR_VACIO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));
					if (isAviso(codResult))
						listaAvisos.put("" + codResult, mensaje);
					else
					{
						flagValidacion = false;

						this.listaMensajesValidacion.put(String.valueOf(new Integer(codResult)), mensaje);
						listaErrores.put("" + codResult, mensaje);
						logger.debug("\nregistrando:" + codResult + "::");
					}
				}
				siguiente = getSiguienteValidacion(valida.getResultado(), siguiente.getSiguientes(), this.lstValidaAPVs);
			}
			//Cuando tiene más de 5 apv simplemente los elimina hasta dejar 5 
			if (cotizante.getApvList().size() > Constants.nAPVs_COTIZANTE)
				for (int i = Constants.nAPVs_COTIZANTE; i < cotizante.getApvList().size(); i++)
					cotizante.getApvList().remove(i);
			
			logger.debug("procesadorMgr: lista apv:" + cotizante.getApvList().size());
			
			if (flagValidacion)
			{
				if (cotizante.getApv() != null && cotizante.getApv().getIdEntidadApv() > 0)
				{
					if (cotizantes.containsKey("0" + cotizante.getIdCotizante())) // si la cotizacion ya existia
					{
						//clillo 4-2-15 por Reliquidación
						CotizanteVO cotizanteOld = (CotizanteVO) cotizantes.get("0" + cotizante.getIdCotizante());
						cotizante.addApv(cotizanteOld.getApv());
					}
				}
			}

			//2010-10-14
			if (cotizantes.containsKey("0" + cotizante.getIdCotizante())){  // si la cotizacion ya existia
				CotizanteVO cotizanteOld = (CotizanteVO) cotizantes.get("0" + cotizante.getIdCotizante());				
				if(!cotizante.getApvList().isEmpty()){
					Iterator  i = cotizante.getApvList().iterator();
					while (i.hasNext()){
						ApvVO apvVO = (ApvVO)i.next();
						if(apvVO.getIdEntidadApv() > 0){
							
							cotizanteOld.addApv(apvVO);
						}
					}				
				}
				
				//cotizantes.remove("" + cotizante.getIdCotizante());
				//cotizantes.put("" + cotizante.getIdCotizante(), cotizanteOld);
				this.count--;// descuenta del numero de cotizantes
			}	
			
			// fin validaciones por APV
		} catch (Exception e)
		{
			logger.error("ERROR validando apv:" + cotizante.getIdCotizante() + "::", e);
			if (e.getMessage() != null)
				listaErrores.put("1", e.getMessage());
			else
				listaErrores.put("1", "catch validando apv");

			e.printStackTrace(new PrintWriter(this.sw));
			logger.error("\n\nERROR: no se encontraron clases validadoras para APV::");
			reportaError(Mail.ERROR, 'R', cotizante.getRutEmpresa(), cotizante.getIdConvenio(), new StringBuffer("no se encontraron clases validadoras para APV."),
					new StringBuffer(this.sw.toString()), parametrosNegocio);
			return listaErrores;
		}
		if (flagValidacion)
			return null;
		return listaErrores;
	}

	private List registraAPV(CotizanteVO cotizante, HashMap parametrosNegocio)
	{
		List result = new ArrayList();
		try
		{
			
			parametrosNegocio = this.seteaInfoBasicaSISByNomina(parametrosNegocio, cotizante.getTipoProceso(), cotizante.getRutEmpresa(), cotizante.getIdConvenio());			
			
			List apvs = cotizante.getApvList();
			logger.debug("validando:" + apvs.size() + ":: apvs");
			int contador = 0;
			for (Iterator itApvs = apvs.iterator(); itApvs.hasNext();)
			{
				ApvVO apv = (ApvVO) itApvs.next();
				logger.info("\tapv:" + apv.getIdEntidadApv() + "::" + apv.getAhorro() + "::");
			}
			ValidacionVO siguiente = getPrimeraValidacionAPV();
			while (siguiente != null)
			{
				Class validacion = Class.forName(siguiente.getClaseValidador().trim());
				Class[] argTypes = { HashMap.class, Session.class, ConvenioVO.class };
				Object[] argValues = { parametrosNegocio, this.session, this.convenioMod };
				Validacion valida = (Validacion) validacion.getConstructor(argTypes).newInstance(argValues);
				int codResult = valida.validaFromWEB(cotizante);
				if (codResult != 0 && isValidable(codResult))
				{// por cada validacion, guardo errores encontrados
					String mensaje = ((valida.getMensaje() == null || valida.getMensaje().equals("")) ? siguiente.getIdValidacion() : valida.getMensaje());
					if (codResult > 10)
					{
						if (this.listaMensajesValidacion.get(String.valueOf(new Integer(codResult * 10 + (contador / 2)))) == null)
							this.listaMensajesValidacion.put(String.valueOf(new Integer(codResult * 10 + (contador / 2))), mensaje);
						result.add(new Integer(codResult + (contador / 2)));
					} else
					{
						if (codResult == Constants.CAIDA_SISTEMA || codResult == Constants.SIN_CONCEPTOS)
							codResult = Constants.INICIO_COD_ERROR_GENERICO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));
						if (codResult == Constants.CONCEPTOS_SIN_VALOR)
							codResult = Constants.INICIO_COD_ERROR_VACIO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));
						if (this.listaMensajesValidacion.get(String.valueOf(new Integer(codResult))) == null)
							this.listaMensajesValidacion.put(String.valueOf(new Integer(codResult)), mensaje);
						result.add(new Integer(codResult));
					}
					logger.debug("ERR APV:" + codResult + "::" + (codResult * 10 + (contador / 2)) + "::" + (contador / 2) + "::");
				}
				siguiente = getSiguienteValidacion(valida.getResultado(), siguiente.getSiguientes(), this.lstValidaAPVs);
				contador++;
			}// fin validaciones por APV
			if (cotizante.getApvList().size() > Constants.nAPVs_COTIZANTE)
				for (int i = Constants.nAPVs_COTIZANTE; i < cotizante.getApvList().size(); i++)
					cotizante.getApvList().remove(i);
		} catch (Exception e)
		{
			logger.error("ERROR validando APV:" + cotizante.getIdCotizante() + "::", e);

			e.printStackTrace(new PrintWriter(this.sw));
			logger.error("\n\nERROR: no se encontraron clases validadoras para APV::");
			reportaError(Mail.ERROR, 'R', cotizante.getRutEmpresa(), cotizante.getIdConvenio(), new StringBuffer("no se encontraron clases validadoras para APV."),
					new StringBuffer(this.sw.toString()), parametrosNegocio);
			return result;
		}
		return result;
	}

	private HashMap registraMovimiento(CotizanteVO cotizante, HashMap cotizantes, HashMap parametrosNegocio, Properties mapConceptos, HashMap listaAvisos)
	{
		logger.debug("\n\n\ncotizante repetido: movimiento de personal??:DiasTrabajados:" + cotizante.getNumDiasTrabajados() + "::");
		HashMap listaErrores = new HashMap();
		//boolean flagValidacion = true;
		try
		{
			ValidacionVO siguiente = getPrimeraValidacionMP();
			Utils setter = new Utils(mapConceptos, this.mapeoValores);
			if (cotizante.getIdCotizante() != 0 && this.cotizanteIdTmp != cotizante.getIdCotizante())
				this.contadorMvto = 0;
			while (siguiente != null)
			{
				Class validacion = Class.forName(siguiente.getClaseValidador().trim());
				Class[] argTypes = { HashMap.class, Utils.class, Session.class, ConvenioVO.class, List.class, List.class, List.class };
				Object[] argValues = { parametrosNegocio, setter, this.session, this.convenioMod, siguiente.getConceptos(), this.listaConceptos, this.listaMapeosValidaciones };
				Validacion valida = (Validacion) validacion.getConstructor(argTypes).newInstance(argValues);
				int _codResult = valida.valida(cotizante);
				if (_codResult != 0 && isValidable(_codResult))
				{// por cada validacion, guardo errores encontrados
					String mensaje = ((valida.getMensaje() == null || valida.getMensaje().equals("")) ? siguiente.getIdValidacion() : valida.getMensaje());
					int codResult = _codResult;
					if (codResult == Constants.CAIDA_SISTEMA || codResult == Constants.SIN_CONCEPTOS)
						codResult = Constants.INICIO_COD_ERROR_GENERICO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));
					if (codResult == Constants.CONCEPTOS_SIN_VALOR)
						codResult = Constants.INICIO_COD_ERROR_VACIO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));
					codResult = codResult * 10 + this.contadorMvto;
					if (isAviso(codResult))
						listaAvisos.put("" + codResult, mensaje);
					else
					{
						//flagValidacion = false;
						if (_codResult > 10)
						{
							this.listaMensajesValidacion.put(String.valueOf(new Integer(codResult)), mensaje);
							logger.debug("ERROR en mov personal:" + cotizante.getIdCotizante() + ":result:" + codResult + ", validacion:" + siguiente.getIdValidacion().trim() + "::" + codResult
									+ "::");
							listaErrores.put("" + codResult, mensaje);
						} else
						{
							this.listaMensajesValidacion.put(String.valueOf(new Integer(codResult)), mensaje);
							logger.debug("ERROR en mov personal:" + cotizante.getIdCotizante() + ":result:" + codResult + ", validacion:" + siguiente.getIdValidacion().trim() + "::" + codResult
									+ "::");
							listaErrores.put("" + codResult, mensaje);
						}
					}
				}
				siguiente = getSiguienteValidacion(valida.getResultado(), siguiente.getSiguientes(), this.lstValidaMovPers);
			}
			this.contadorMvto++;
			/*if (cotizante.getIdCotizante() != 0 && this.cotizanteIdTmp != cotizante.getIdCotizante())
				this.contadorMvto = 0;*/
			this.cotizanteIdTmp = cotizante.getIdCotizante();
			if (true)//(flagValidacion)// si validacion correcta
			{
				if (!cotizante.isAfpVoluntario())
				{
					if (((CotizacionREVO) cotizante.getCotizacion()).getMovtoPers() != null && ((CotizacionREVO) cotizante.getCotizacion()).getMovtoPers().getIdTipoMovReal() > 0)
					{
						//clillo 4-2-15 Reliquidación
						//if (cotizantes.containsKey("" + cotizante.getIdCotizante())) // si la cotizacion ya existia
						if (cotizantes.containsKey("0" + cotizante.getIdCotizante())) // si la cotizacion ya existia
						{
							logger.debug("cotizante repetido, juntando");
							this.isMovimiento = true;
//							clillo 4-2-15 Reliquidación
							//CotizanteVO cotizanteOld = (CotizanteVO) cotizantes.get("" + cotizante.getIdCotizante());
							CotizanteVO cotizanteOld = (CotizanteVO) cotizantes.get("0" + cotizante.getIdCotizante());
							CotizacionREVO cotizacionOld = (CotizacionREVO) cotizanteOld.getCotizacion();
							CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
							logger.debug("calculoMovPersonal:" + this.convenioMod.getCalculoMovPersonal() + "::");

							if (this.convenioMod.getCalculoMovPersonal() == Constants.SUMAR_MONTOS_MOVIMIENTO)
							{
								cotizanteOld.sumaDias(cotizante);
								cotizacionOld.sumaMontos(cotizacion);
								cotizanteOld.setCotizacion(cotizacionOld);
							}

							cotizacionOld.addMovtosPersonal(cotizacion.getMovimientoPersonal());
//							clillo 4-2-15 Reliquidación
							//cotizantes.remove("" + cotizante.getIdCotizante());
							cotizantes.remove("0" + cotizante.getIdCotizante());
							int contador = 1;
							for (Iterator it = cotizacionOld.getMovimientoPersonal().iterator(); it.hasNext();)
							{
								MovtoPersonalVO mp = (MovtoPersonalVO) it.next();
								mp.setIdMovimiento(contador++);
								logger.debug("\tMOVTO:IdCotizante:" + mp.getIdCotizante() + ":IdMovimiento:" + mp.getIdMovimiento() + ":IdTipoMovReal:" + mp.getIdTipoMovReal() + "::");
							}
//							clillo 4-2-15 Reliquidación
							//cotizantes.put("" + cotizante.getIdCotizante(), cotizanteOld);
							cotizantes.put("0" + cotizante.getIdCotizante(), cotizanteOld);
							this.count--;// descuenta del numero de cotizantes
						}
					}
				} else
				{
					if (((CotizacionREVO) cotizante.getCotizacion()).getMovtoPersAF() != null && ((CotizacionREVO) cotizante.getCotizacion()).getMovtoPersAF().getIdTipoMovReal() > 0)
					{
						//clillo 4-2-15 Reliquidación
						//if (cotizantes.containsKey("0" + cotizante.getIdCotizante())) // si la cotizacion ya existia
						if (cotizantes.containsKey("0" + cotizante.getIdCotizante())) // si la cotizacion ya existia
						{
							logger.debug("cotizante repetido, juntando");
							this.isMovimiento = true;
//							clillo 4-2-15 Reliquidación
							//CotizanteVO cotizanteOld = (CotizanteVO) cotizantes.get("" + cotizante.getIdCotizante());
							CotizanteVO cotizanteOld = (CotizanteVO) cotizantes.get("0" + cotizante.getIdCotizante());
							CotizacionREVO cotizacionOld = (CotizacionREVO) cotizanteOld.getCotizacion();
							CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
							logger.debug("calculoMovPersonal:" + this.convenioMod.getCalculoMovPersonal() + "::");

							if (this.convenioMod.getCalculoMovPersonal() == Constants.SUMAR_MONTOS_MOVIMIENTO)
							{
								cotizanteOld.sumaDias(cotizante);
								cotizacionOld.sumaMontos(cotizacion);
								cotizanteOld.setCotizacion(cotizacionOld);
							}

							cotizacionOld.addMovtosPersonalAF(cotizacion.getMovimientoPersonalAF());
//							clillo 4-2-15 Reliquidación
							//cotizantes.remove("" + cotizante.getIdCotizante());
							cotizantes.remove("0" + cotizante.getIdCotizante());
							int contador = 1;
							for (Iterator it = cotizacionOld.getMovimientoPersonalAF().iterator(); it.hasNext();)
							{
								MvtoPersoAFVO mp = (MvtoPersoAFVO) it.next();
								mp.setIdMovimiento(contador++);
								logger.debug("\tMOVTO:IdCotizante:" + mp.getIdCotizante() + ":IdMovimiento:" + mp.getIdMovimiento() + ":IdTipoMovReal:" + mp.getIdTipoMovReal() + "::");
							}
//							clillo 4-2-15 Reliquidación
							//cotizantes.put("" + cotizante.getIdCotizante(), cotizanteOld);
							cotizantes.put("0" + cotizante.getIdCotizante(), cotizanteOld);
							this.count--;// descuenta del numero de cotizantes
						}
					}
				}
			} // fin validaciones por movimiento
		} catch (Exception e)
		{
			logger.error("ERROR validando movimiento:" + cotizante.getIdCotizante() + "::", e);
			if (e.getMessage() != null)
				listaErrores.put("1", e.getMessage());
			else
				listaErrores.put("1", "catch validando movimientos");

			e.printStackTrace(new PrintWriter(this.sw));
			logger.error("\n\nERROR: no se encontraron clases validadoras para movimiento personal::");
			reportaError(Mail.ERROR, 'R', cotizante.getRutEmpresa(), cotizante.getIdConvenio(), new StringBuffer("no se encontraron clases validadoras para movimiento personal."), new StringBuffer(
					this.sw.toString()), parametrosNegocio);
			return listaErrores;
		}
		if (true)//(flagValidacion)
			return null;
		return listaErrores;
	}

	private List registraMovimiento(boolean isVoluntario, CotizanteVO cotizante, HashMap parametrosNegocio)
	{
		List result = new ArrayList();
		try
		{
			
			parametrosNegocio = this.seteaInfoBasicaSISByNomina(parametrosNegocio, cotizante.getTipoProceso(), cotizante.getRutEmpresa(), cotizante.getIdConvenio());
			
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			List mps = (isVoluntario ? cotizacion.getMovimientoPersonalAF() : cotizacion.getMovimientoPersonal());
			int contador = 0;
			boolean necesitaSIL = false;
			for (Iterator itMps = mps.iterator(); itMps.hasNext();)
			{
				CotizanteVO cotTmp = new CotizanteVO();
				CotizacionREVO cotizacionTmp = new CotizacionREVO();

				List lista = new ArrayList();
				if (isVoluntario)
				{
					MvtoPersoAFVO mp = (MvtoPersoAFVO) itMps.next();
					lista.add(mp);
					cotizacionTmp.setMovimientoPersonal2AF(lista);
					if (mp.getIdTipoMovReal() == Constants.TIPO_MOVTOAF_SIL)
						necesitaSIL = true;
				} else
				{
					MovtoPersonalVO mp = (MovtoPersonalVO) itMps.next();
					lista.add(mp);
					cotizacionTmp.setMovimientoPersonal2(lista);
					if (mp.getIdTipoMovReal() == Constants.TIPO_MOVTO_SIL)
						necesitaSIL = true;
				}

				cotTmp.setCotizacion(cotizacionTmp);
				cotTmp.setAfpVoluntario(isVoluntario);
				cotTmp.setTipoProceso(cotizante.getTipoProceso());
				cotTmp.setNumDiasTrabajados(cotizante.getNumDiasTrabajados());
				cotTmp.setIdEntSil(cotizante.getIdEntSil());

				if (this.lstValidaMovPers.size() > 0)
				{
					ValidacionVO siguiente = getPrimeraValidacionMP();
					while (siguiente != null)
					{
						Class validacion = Class.forName(siguiente.getClaseValidador().trim());
						Class[] argTypes = { HashMap.class, Utils.class, Session.class, ConvenioVO.class, List.class, List.class };
						Object[] argValues = { parametrosNegocio, null, this.session, this.convenioMod, siguiente.getConceptos(),
								this.validacionDao.getListaConceptos(String.valueOf(cotizante.getTipoProceso())) };
						Validacion valida = (Validacion) validacion.getConstructor(argTypes).newInstance(argValues);
						int codResult = valida.validaFromWEB(cotTmp);
						if (codResult != 0 && isValidable(codResult))
						{// por cada validacion, guardo errores encontrados
							String mensaje = (valida.getMensaje().equals("") ? siguiente.getIdValidacion() : valida.getMensaje());
							if (codResult > 10)
								codResult *= 10;
							if (codResult == Constants.CAIDA_SISTEMA || codResult == Constants.SIN_CONCEPTOS)
								codResult = Constants.INICIO_COD_ERROR_GENERICO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));
							if (codResult == Constants.CONCEPTOS_SIN_VALOR)
								codResult = Constants.INICIO_COD_ERROR_VACIO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));
							if (this.listaMensajesValidacion.get(String.valueOf(new Integer(codResult))) == null)
								this.listaMensajesValidacion.put(String.valueOf(new Integer(codResult)), mensaje);
							result.add(new Integer(codResult));
							logger.debug("ERR MOVTO:" + codResult + "::" + (codResult) + "::" + contador + "::");
						}
						siguiente = getSiguienteValidacion(valida.getResultado(), siguiente.getSiguientes(), this.lstValidaMovPers);
					}// fin validaciones por movimiento
				}
				contador++;
			}
			if (!necesitaSIL && cotizante.getIdEntSil() != Constants.ENTSIL_FALSO)
			{
				int codResult = Constants.TIPO_CAUSA_ENTSIL_SOBRA;
				String mensaje = "";
				if (codResult > 10)
					codResult *= 10;
				if (this.listaMensajesValidacion.get(String.valueOf(new Integer(codResult))) == null)
					this.listaMensajesValidacion.put(String.valueOf(new Integer(codResult)), mensaje);
				result.add(new Integer(codResult));
				logger.debug("ERR MOVTO:" + codResult + "::" + (codResult) + "::" + contador + "::");
			}
		} catch (Exception e)
		{
			logger.error("ERROR validando movimiento:" + cotizante.getIdCotizante() + "::", e);

			e.printStackTrace(new PrintWriter(this.sw));
			logger.error("\n\nERROR: no se encontraron clases validadoras para movimiento personal::");
			reportaError(Mail.ERROR, 'R', cotizante.getRutEmpresa(), cotizante.getIdConvenio(), new StringBuffer("no se encontraron clases validadoras para movimiento personal."), new StringBuffer(
					this.sw.toString()), parametrosNegocio);
			return result;
		}
		return result;
	}

	private CotizacionVO creaCotizacion(char tipoProceso)
	{
		if (tipoProceso == 'R')
			return new CotizacionREVO(this.documento.getRutEmpresa(), this.documento.getIdConvenio());
		else if (tipoProceso == 'G')
			return new CotizacionGRVO(this.documento.getRutEmpresa(), this.documento.getIdConvenio());
		else if (tipoProceso == 'A')
			return new CotizacionRAVO(this.documento.getRutEmpresa(), this.documento.getIdConvenio());
		else if (tipoProceso == 'D')
			return new CotizacionDCVO(this.documento.getRutEmpresa(), this.documento.getIdConvenio());

		logger.error("creaCotizacion:tipo proceso no corresponde:" + tipoProceso + "::");
		return null;
	}

	private CotizacionPendienteVO creaCotizacionPendiente(char tipoProceso, String idCotizante, int idCotizPendiente, String detalle, String periodo)
	{
		if (tipoProceso == 'R')
		{
			return new CotizacionPendienteREVO(this.documento.getRutEmpresa(), this.documento.getIdConvenio(), idCotizante, idCotizPendiente, detalle);
		} else if (tipoProceso == 'G')
		{
			return new CotizacionPendienteGRVO(this.documento.getRutEmpresa(), this.documento.getIdConvenio(), idCotizante, idCotizPendiente, detalle);
		} else if (tipoProceso == 'A')
		{
			return new CotizacionPendienteRAVO(this.documento.getRutEmpresa(), this.documento.getIdConvenio(), idCotizante, idCotizPendiente, detalle, Integer.parseInt(periodo));
		} else if (tipoProceso == 'D')
		{
			return new CotizacionPendienteDCVO(this.documento.getRutEmpresa(), this.documento.getIdConvenio(), idCotizante, idCotizPendiente, detalle);
		}
		logger.error("creaCotizacionPendiente:tipo proceso no corresponde:" + tipoProceso + "::");
		return null;
	}

	private void creaCausa(char tipoProceso, int codResult, String msg, CotizacionPendienteVO cp, String valorInformado, String periodo)
	{
		int idCausa = cp.getNCausas() + 1;
		if (msg.length() > 80)
			msg = msg.substring(0, 80);
		if (tipoProceso == 'R')
			cp.addCausa(new CausaREVO((CotizacionPendienteREVO) cp, idCausa, codResult, msg, valorInformado));
		else if (tipoProceso == 'G')
			cp.addCausa(new CausaGRVO((CotizacionPendienteGRVO) cp, idCausa, codResult, msg, valorInformado));
		else if (tipoProceso == 'A')
			cp.addCausa(new CausaRAVO((CotizacionPendienteRAVO) cp, idCausa, codResult, msg, valorInformado, Integer.parseInt(periodo)));
		else if (tipoProceso == 'D')
			cp.addCausa(new CausaDCVO((CotizacionPendienteDCVO) cp, idCausa, codResult, msg, valorInformado));
		else
			logger.error("creaCausa:tipo proceso no corresponde:" + tipoProceso + "::");
	}

	private void setListasValidacion(String tipoProceso, int idMapaNom) throws DaoException
	{
		if (!this.listaConceptosHM.containsKey(tipoProceso + "#" + idMapaNom))
			this.listaConceptosHM.put(tipoProceso + "#" + idMapaNom, this.validacionDao.getListaConceptos(tipoProceso));
		this.listaConceptos = (List) this.listaConceptosHM.get(tipoProceso + "#" + idMapaNom);

		if (!this.listaValidacionesHM.containsKey(tipoProceso))
			this.listaValidacionesHM.put(tipoProceso, this.validacionDao.getListaValidaciones(tipoProceso));
		this.listaValidaciones = (List) this.listaValidacionesHM.get(tipoProceso);

		if (!this.listaMapeoHM.containsKey(tipoProceso + "#" + idMapaNom))
			this.listaMapeoHM.put(tipoProceso + "#" + idMapaNom, this.validacionDao.getListaMapeo(tipoProceso, idMapaNom));
		this.listaMapeo = (HashMap) this.listaMapeoHM.get(tipoProceso + "#" + idMapaNom);

		if (tipoProceso.equals("R"))
		{
			if (this.lstValidaMovPers == null)
				this.lstValidaMovPers = this.validacionDao.getLstValidaMovPers();
			if (this.lstValidaAPVs == null)
				this.lstValidaAPVs = this.validacionDao.getLstValidaAPVs();
		} else if (tipoProceso.equals("G"))
		{
		} else if (tipoProceso.equals("A"))
		{
		} else if (tipoProceso.equals("D"))
		{
		}
	}

	private void reportaError(int severidad, char tipoProceso, int rutEmpresa, int idConvenio, StringBuffer descripcion, StringBuffer body, HashMap parametros)
	{
		if (!this.mailEnviado)
		{
			descripcion.append("\n\nRut Empresa: '" + rutEmpresa + "', idConvenio: '" + idConvenio + "', tipo Proceso:'" + tipoProceso + "'");
			parametros.put("idConvenio", ""+idConvenio);
			parametros.put("tipoProceso", ""+tipoProceso);
			reportaError(severidad, descripcion, body, parametros,rutEmpresa);
			this.mailEnviado = true;
		}
	}

	private void reportaError(int severidad, StringBuffer descripcion, StringBuffer body, HashMap parametros, int rutEmpresa)
	{
		
		try
		{
			if (parametros.containsKey("" + Constants.PARAM_MAIL_TO) &&
				parametros.containsKey("" + Constants.PARAM_MAIL_HOST_LOCAL) && 
				parametros.containsKey("" + Constants.PARAM_MAIL_FROM) &&
				parametros.containsKey("" + Constants.PARAM_MAIL_USER) && 
				parametros.containsKey("" + Constants.PARAM_MAIL_PASS) &&
				parametros.containsKey("" + Constants.PARAM_MAIL_HOST_TO) &&
				parametros.containsKey("" + Constants.PARAM_MAIL_PORT))
			{
					//Mail Administrador
					logger.info("\n\n\nparametros envio mail: Administrador");
					logger.info(" mailTo:"         + (String)parametros.get("" + Constants.PARAM_MAIL_TO) +
								" :mailHostTo:"    + (String)parametros.get("" + Constants.PARAM_MAIL_HOST_TO) +
								" :mailFrom:"      + (String)parametros.get("" + Constants.PARAM_MAIL_FROM)+
								" :userMail:"      + (String)parametros.get("" + Constants.PARAM_MAIL_USER)+
								" :passMail:"      + (String)parametros.get("" + Constants.PARAM_MAIL_PASS)+
								" :mailHostLocal:" + (String)parametros.get("" + Constants.PARAM_MAIL_HOST_LOCAL)+ "::");
					
					Mail mail = new Mail(severidad
										,(String)parametros.get("" + Constants.PARAM_MAIL_TO)
										,(String)parametros.get("" + Constants.PARAM_MAIL_HOST_TO)
										,Integer.parseInt((String)parametros.get("" + Constants.PARAM_MAIL_PORT))
										,(String)parametros.get("" + Constants.PARAM_MAIL_FROM)
										,(String)parametros.get("" + Constants.PARAM_MAIL_USER)
										,(String)parametros.get("" + Constants.PARAM_MAIL_PASS));
					mail.setLocalHost((String)parametros.get("" + Constants.PARAM_MAIL_HOST_LOCAL));
					
					mail.setContenido(body);
	
					InetAddress addr = InetAddress.getLocalHost();
					descripcion.append("\n\nNodo(" + addr.getHostAddress() + ", " + addr.getCanonicalHostName() + ")");
					mail.setDescCorta(descripcion.toString());
					ReportaError.enviar(mail);
				
					//Mail Cliente

					String correoCliente = (String)parametros.get("mailPersona");
					if(!correoCliente.equals("")){
					
									logger.info("\n\n\nparametros envio mail: Cliente");
									logger.info(" mailTo:"     +  correoCliente+
											" :mailHostTo:"    + (String)parametros.get("" + Constants.PARAM_MAIL_HOST_TO) +
											" :mailFrom:"      + (String)parametros.get("" + Constants.PARAM_MAIL_FROM)+
											" :userMail:"      + (String)parametros.get("" + Constants.PARAM_MAIL_USER)+
											" :passMail:"      + (String)parametros.get("" + Constants.PARAM_MAIL_PASS)+
											" :mailHostLocal:" + (String)parametros.get("" + Constants.PARAM_MAIL_HOST_LOCAL)+ "::");
									
									Mail mailCliente = new Mail(severidad
														, correoCliente
														,(String)parametros.get("" + Constants.PARAM_MAIL_HOST_TO)
														,Integer.parseInt((String)parametros.get("" + Constants.PARAM_MAIL_PORT))
														,(String)parametros.get("" + Constants.PARAM_MAIL_FROM)
														,(String)parametros.get("" + Constants.PARAM_MAIL_USER)
														,(String)parametros.get("" + Constants.PARAM_MAIL_PASS));
									mailCliente.setLocalHost((String)parametros.get("" + Constants.PARAM_MAIL_HOST_LOCAL));
									
									//mailCliente.setContenido(body);
									
									//InetAddress addrCiente = InetAddress.getLocalHost();
									//descripcion.append("\n\nNodo(" + addrCiente.getHostAddress() + ", " + addrCiente.getCanonicalHostName() + ")");
									//mailCliente.setDescCorta(descripcion.toString());
									
									//mailCliente.setContenido(body);
									mailCliente.setLinea(new StringBuffer("numero de linea "+this.contadorLinea+ " ("+this.lineaObtenida+") "));
										
									StringBuffer descripcionCliente = new StringBuffer();
									descripcionCliente.append("Estimado cliente \n\n");
									descripcionCliente.append("No sé pudo procesar la nomina para la empresa ");
									descripcionCliente.append(rutEmpresa);
									descripcionCliente.append(", id convenio : " +parametros.get("idConvenio"));
									descripcionCliente.append(", tipo proceso : " +parametros.get("tipoProceso"));
									descripcionCliente.append(" ya que una línea tiene problemas.");
									
									mailCliente.setDescCorta(descripcionCliente.toString());
									
									ReportaError.enviar(mailCliente);
					}else{
						logger.info(" *** No hay mail del cliente para mandar el correo **");
					}
						
						}else{
							logger.info(" *** No estan todos los parametros necesarios para mandar los correos **");
						}
							
					//}	
			} catch (Exception e)
			{
			}
		
	}

	private ValidacionVO getSiguienteValidacion(String valor, List listaSiguientes, List lValidaciones)
	{
		logger.debug("getSiguienteValidacion: buscando valor:" + valor + "::");
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
					if (ns.getIdSiguiente().trim().equals(v.getIdValidacion().trim()))
					{
						this.posSiguiente = ++contador;
						logger.debug("getSiguienteValidacion: encontrado:" + v.getIdValidacion() + "::");
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

	private boolean isValidable(int id)
	{
		if (this.causaErrores.containsKey("" + id) || this.causaAvisos.containsKey("" + id))
			return true;
		return false;
	}

	private boolean isAviso(int id)
	{
		if (this.causaAvisos.containsKey("" + id))
			return true;
		return false;
	}

	private List buscaAvisos(int idCotizante, int periodo)
	{
		if (this.listaCausaAviso.containsKey(periodo + "" + idCotizante))
			return (List) this.listaCausaAviso.get(periodo + "" + idCotizante);
		return new ArrayList();
	}

	private void addCausaAviso(String idCotizante, int idCotizPendiente, HashMap listaAvisos, CotizanteVO cotizante, char tipoProceso) throws NumberFormatException
	{
		if (listaAvisos != null)
		{
			List avisosRegistrados = buscaAvisos(Integer.parseInt(idCotizante), cotizante.getPeriodo());
			int contador = (avisosRegistrados.size() > 0 ? avisosRegistrados.size() : 0) + 1;
			for (Iterator it = listaAvisos.keySet().iterator(); it.hasNext();)
			{
				int j=0;
				String valor = String.valueOf(it.next());
				if (!inCausaAviso(valor, avisosRegistrados, cotizante.getPeriodo()))
				{
//					clillo 3-12-14 por Reliquidación
					//CausaVO cp = new CausaVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), idCotizPendiente, contador, Integer.parseInt(valor), listaAvisos.get(valor) + ", RUT:" + idCotizante);
					CausaVO cp = new CausaVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), idCotizPendiente, contador, Integer.parseInt(valor), listaAvisos.get(valor) + ", RUT:" + idCotizante, cotizante.getPeriodo());
					avisosRegistrados.add(transformCausaAviso(tipoProceso, cp));
					contador++;
				}
			}
			if (contador > 1)
				cotizante.setTieneAviso(1);
			this.listaCausaAviso.put(cotizante.getPeriodo() + "" + Integer.parseInt(idCotizante), avisosRegistrados);
		}
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

	private boolean inCausaAviso(String valor, List lista, int periodo)
	{
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			CausaVO _causa = (CausaVO) it.next();
			if (_causa instanceof CausaAvisoRAVO) {
				if (valor.equals("" + _causa.getIdTipoCausa()) && periodo==_causa.getPeriodo())
					return true;
			}else{
				if (valor.equals("" + _causa.getIdTipoCausa()))
					return true;
			}
			
		}
		return false;
	}

	/**
	 * Crea el cuerpo del correo con listado de errores 
	 * @param tipoNomina
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 */
	private StringBuffer cuerpoCorreoErrores(char tipoNomina, int idEmpresa, int idConvenio){
		StringBuffer cuerpo = new StringBuffer("");
		
		try {
			NominaDAO nominaDAO = new NominaDAO(this.session);
			
			List listadoErrores = nominaDAO.getInformeErrores(String.valueOf(idEmpresa), String.valueOf(idConvenio), String.valueOf(tipoNomina));

			int idCotizante = 0;
			int cantErrores = 0;
			CotizanteVO cot;
			
			for (int i=0 ; i < listadoErrores.size() && cantErrores <= Constants.CANTIDAD_MAXIMA_REGISTROS_ERROR; i++){
										
				Object[] listaInforme = (Object[])listadoErrores.get(i);
				
				CotizacionPendienteVO cotizPendiente = (CotizacionPendienteVO) listaInforme[0];
				this.mapeoValores = Utils.parseoValores(this.listaConceptos, this.listaMapeo, cotizPendiente.getDetalle());
				
				String rutTmp = (String) this.mapeoValores.get("RUT");
				
				//clillo 12-10-12 Se limpia el guión ya que sino se cae.
				rutTmp= rutTmp.replace('-', ' ');
				
				// En caso de que el RUT hubiese tenido guión.
				//clillo 12/12/14 Se cambia for
				String rutTmpSinBlancos = rutTmp.replaceAll(" ", "");
				/*for (int x=0; x < rutTmp.length(); x++) {
					if (rutTmp.charAt(x) != ' ')
						rutTmpSinBlancos += rutTmp.charAt(x);
				}*/
				rutTmp = (!rutTmpSinBlancos.equals("") ? rutTmpSinBlancos.substring(0, rutTmpSinBlancos.length() - 1) : "");
				
				cot = new CotizanteVO(idEmpresa, idConvenio, Integer.parseInt(rutTmp));
				cot.setNombre((String) this.mapeoValores.get("Nombre"));
				cot.setApellidoPat((String) this.mapeoValores.get("Apellidos"));

				TipoCausaVO tca = (TipoCausaVO)listaInforme[2];
				
				if (i == 0){
					cot.setFormatRut(Utils.formatRut(cot.getIdCotizante()));
					cuerpo.append("* " + cot.getNombre() + " " + cot.getApellidoPat() + ", " + cot.getFormatRut() + "\n");
					cuerpo.append("\t - " + tca.getDescripcion() + "\n");
					idCotizante = cot.getIdCotizante();
				} else {
					if (idCotizante != cot.getIdCotizante()) {
						cot.setFormatRut(Utils.formatRut(cot.getIdCotizante()));
						cuerpo.append("\n* " + cot.getNombre() + " " + cot.getApellidoPat() + ", " + cot.getFormatRut() + "\n");
						cuerpo.append("\t - " + tca.getDescripcion() + "\n");
					} else {
						cuerpo.append("\t - " + tca.getDescripcion() + "\n");
						idCotizante = cot.getIdCotizante();
					}					
				}
				cantErrores++;
			}
			if (cantErrores >= Constants.CANTIDAD_MAXIMA_REGISTROS_ERROR)
				cuerpo.append("\nLa nómina cargada presentó más de " + Constants.CANTIDAD_MAXIMA_REGISTROS_ERROR + " errores, para ver el listado completo de estos, diríjase a la opción Avisos-Errores de la opción Mis Reportes.\n");
			cuerpo.append("\nConsecuente con lo anterior, no es posible procesar esta información, por lo que es necesario corregir y reenviar dentro de los horarios establecidos, o bien modifique en línea conectándose a su sitio www.cp.cl");

		} catch (HibernateException e1) {
			logger.error("ProcesadorMgr: ERR: Problemas con la session", e1);
		} catch (DaoException e1) {
			logger.error("ProcesadorMgr: ERR: Problemas con la session", e1);
		}catch (Exception e1) {
			logger.error("ProcesadorMgr: ERR: Problemas con cuerpoCorreoErrores", e1);
		}
		return cuerpo;
	}
	
	class TimerStress
	{
		long[] inicial = {0,0,0};
		String sufijo = null;
		String[] mensaje = {null, null, null};
		
		public TimerStress(){
		}
		
		/**
		 * Emite un mensaje de inicio de proceso en logger
		 * @param nivel valor entre 0 y 2, los niveles se van cerrando desde el mas grande al menor en forma automatica
		 * @param mensaje titulo del proceso
		 */
		public void inicia(int nivel, String msg)
		{
			if (this.mensaje[nivel] != null)
				finaliza(nivel);
			this.inicial[nivel] = System.currentTimeMillis();
			NominaVO nom = ProcesadorMgr.this.nomina;
			if (nom != null && this.sufijo == null)
				this.sufijo = ":[" + nom.getRutEmpresa() + '/' + nom.getIdConvenio() + '/' + nom.getTipoProceso() + "]:";
			if (nom == null && this.sufijo != null)
				this.sufijo = null;
			this.mensaje[nivel] = this.sufijo == null ? msg : msg + this.sufijo;
			logger.info("\n\n iniciando " + this.mensaje[nivel] + ':' + this.inicial[nivel] + "::");
		}
		
		/**
		 * Cierra los mensajes de los procesos desde el nivel nivel
		 * @param nivel valor entre 0 y 2
		 */
		public void finaliza(int nivel)
		{
			long now = System.currentTimeMillis();
			for (int i = 2; i >= nivel; i--)
				if (this.mensaje[i] != null)
				{
					logger.info("\n\n terminando " + this.mensaje[i] + this.sufijo + (now - this.inicial[i]) + "::");
					this.mensaje[i] = null;
				}
		}
	}

	/**
	 * Crea un HashMap con lo siguiente
	 * (1) - Las Cargas Familiares. La llave de estos registros es ID_CCAF;RUT_EMPRESA;RUT_TRABAJADOR.
	 * (2) - Un registro (llave RUT_EMPRESA) que tiene un arreglo con los ID de las Cajas presentadas en CARGA_FAMILIAR
	 * @param rutEmpresa
	 * @param idCcaf
	 * @return
	 */
	private HashMap getHashCargasPorEmpresa(int rutEmpresa, int idCcaf) {
		HashMap hashCargasFamiliares = new HashMap();
		String keyHashMap;

		//(1)
		List cargasFamiliares = this.nominaDao.getCargasFamiliaresPorEmpresa(rutEmpresa, idCcaf);
		for (Iterator itCargas = cargasFamiliares.iterator(); itCargas.hasNext();) {
			CargaFamiliarVO carga = (CargaFamiliarVO) itCargas.next();
			keyHashMap = carga.getIdEntidadCCAF() + ";" + carga.getRutEmpresa() + ";" + carga.getRutTrabajador();
			hashCargasFamiliares.put(keyHashMap, carga);
		}

		//(2)
		int [] cajasPorEmpresa;
		try {
			cajasPorEmpresa = this.nominaDao.getCajasEnCargasPorEmpresa(rutEmpresa);
			hashCargasFamiliares.put(new Integer(rutEmpresa), cajasPorEmpresa);
		} catch (Exception e) {
			hashCargasFamiliares.put(new Integer(rutEmpresa), null);
		}
		
		return hashCargasFamiliares;
	}
	
	
	/**
	 * Obtiene el Digito Verificador dado el Rut
	 * @param rut
	 * @return
	 */
	private String getObtenerDvRut(long rut) {
		String rutString = String.valueOf(rut);
		int M=0,S=1,T=Integer.parseInt(rutString); 
		  for(;T!=0;T/=10)S=(S+T%10*(9-M++%6))%11; 
		return String.valueOf((char)(S!=0?S+47:75));
	}
	
	private void validacionesSis(HashMap parametrosNegocio, HashMap cotizantes, HashMap cotPendientes, char tipoProceso) throws Exception{
		
		String periodoFormatoCP="";
		if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA)){
			periodoFormatoCP = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		
		}else if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
			periodoFormatoCP = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO_INDEPENDIENTE);
		}
		
		//asepulveda 2013-05-29
		//Desde parametro de CP el periodo viene como yyyyMM
		//Pero internamente en SIS el periodo se maneja como MMyyyy
		
		String periodoFormatoSIS = periodoFormatoCP.substring(4, 6) + periodoFormatoCP.substring(0, 4);	
		
		//String periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		String iniVigSIS = (String)parametrosNegocio.get("" + Constants.PARAM_PRIMER_PERIODO_VIG_PLENA_SIS);
		int minTrabSIS = Integer.parseInt((String)parametrosNegocio.get("" + Constants.PARAM_MINIMO_TRABAJADORES_SIS));
		boolean isPrivada = ((Integer) parametrosNegocio.get("empresaPrivada")).intValue() == 0 ? true : false;
		int cantidadCotizantesEnNomina = ((Integer)parametrosNegocio.get("cantidadCotizantes")).intValue();
		boolean informaSisEnNomina = ((Boolean)parametrosNegocio.get("informaSisEnNomina")).booleanValue();
		
		ArrayList listaFull = new ArrayList();
					
		for (Iterator itCot = cotizantes.keySet().iterator(); itCot.hasNext();)
		{
			ConsultaRespuesta consulta = new ConsultaRespuesta();
			String idCotizante = (String)itCot.next();		
			CotizanteVO cotizante = (CotizanteVO)cotizantes.get(idCotizante);
			consulta.setRutCotizante(cotizante.getIdCotizante());
			consulta.setDvRutCotizante(this.getObtenerDvRut(cotizante.getIdCotizante()));			
			consulta.setPeriodo(periodoFormatoSIS);			
			consulta.setCodigoAFP(String.valueOf(cotizante.getIdEntPensionReal()));//que pasa en el caso que tenga INP ?????????				
			listaFull.add(consulta);
		}
		ValidadorSisBusiness validadorSisBusiness = new ValidadorSisBusiness();			
		HashMap hashRespuesta = validadorSisBusiness.validador(listaFull);
		
		//mandar aviso
		HashMap listaAvisos = new HashMap();
		
		//Trabajadores sin aviso
		for (Iterator itCot = cotizantes.keySet().iterator(); itCot.hasNext();)
		{
			listaAvisos = new HashMap();
			String idCotizante = (String)itCot.next();	
			CotizanteVO cotizanteVO = (CotizanteVO)cotizantes.get(idCotizante);
			System.out.println("Rut es : "+idCotizante+ " Sin aviso");
			if (cotizanteVO.getIdEntPensionReal() > 0){
			
				ConsultaRespuesta consultaRespuesta = (ConsultaRespuesta)hashRespuesta.get(String.valueOf(cotizanteVO.getIdCotizante()));

				if(consultaRespuesta != null){		
													
						if (consultaRespuesta.getIdRespuesta() == UtilsAFP.NEG_PERIODO_INVALIDO){
							listaAvisos.put("" + "3821", ConsultaDatosTO.PERIODO_INVALIDO);
							addCausaAviso(String.valueOf(cotizanteVO.getIdCotizante()), cotizanteVO.getIdCotizante(), listaAvisos, cotizanteVO, tipoProceso);
						}
						if (consultaRespuesta.getIdRespuesta() == UtilsAFP.NEG_RUT_INVALIDO){
							listaAvisos.put("" + "3823", ConsultaDatosTO.PERIODO_INVALIDO);
							addCausaAviso(String.valueOf(cotizanteVO.getIdCotizante()), cotizanteVO.getIdCotizante(), listaAvisos, cotizanteVO, tipoProceso);
						}
						/*if (consultaRespuesta.getIdRespuesta() == UtilsAFP.CTR_ERROR_SERVICIO){
							listaAvisos.put("" + "3829", ConsultaDatosTO.ERROR_SERVICIO);
							addCausaAviso(String.valueOf(cotizanteVO.getIdCotizante()), cotizanteVO.getIdCotizante(), listaAvisos, cotizanteVO, tipoProceso);
						}*/
					
					if (consultaRespuesta.getCodigoAFPRespuesta() != null){
						if( !String.valueOf(cotizanteVO.getIdEntPensionReal()).equals( consultaRespuesta.getCodigoAFPRespuesta())){
							if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_CAPITAL))
								listaAvisos.put("" + "3827", "Encontrado Otra AFP");
							if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_CUPRUM))
								listaAvisos.put("" + "3823", "Encontrado Otra AFP");
							if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_HABITAT))
								listaAvisos.put("" + "3824", "Encontrado Otra AFP");	
							if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_MODELO_SA))
								listaAvisos.put("" + "3828", "Encontrado Otra AFP");			
							if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_PLANVITAL))
								listaAvisos.put("" + "3825", "Encontrado Otra AFP");				
							if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_PROVIDA))
								listaAvisos.put("" + "3826", "Encontrado Otra AFP");
							addCausaAviso(String.valueOf(cotizanteVO.getIdCotizante()), cotizanteVO.getIdCotizante(), listaAvisos, cotizanteVO, tipoProceso);
						}
					}else{
						if (consultaRespuesta.getIdRespuesta() == UtilsAFP.NEG_NO_ES_AFILIADO){
							listaAvisos.put("" + "3822", ConsultaDatosTO.NO_ES_AFILIADO);
							addCausaAviso(String.valueOf(cotizanteVO.getIdCotizante()), cotizanteVO.getIdCotizante(), listaAvisos, cotizanteVO, tipoProceso);
						}
					}
					
					if (informaSisEnNomina){
					
						if (consultaRespuesta.getIdRespuesta() == UtilsLocal.NEG_NO_COBRAR_SIS){
							//gmallea se elimina mensaje de aviso ya que no pueden existir los dos 
							if(listaAvisos.containsKey("3492"))
								listaAvisos.remove("3492");
							listaAvisos.put("" + "3820", ConsultaDatosTO.NO_COBRA_SIS);
							addCausaAviso(String.valueOf(cotizanteVO.getIdCotizante()), cotizanteVO.getIdCotizante(), listaAvisos, cotizanteVO, tipoProceso);
						}					
					}else{
						if (consultaRespuesta.getIdRespuesta() == UtilsLocal.NEG_SI_COBRAR_SIS){
							listaAvisos.put("" + "3819", ConsultaDatosTO.COBRA_SIS);
							addCausaAviso(String.valueOf(cotizanteVO.getIdCotizante()), cotizanteVO.getIdCotizante(), listaAvisos, cotizanteVO, tipoProceso);
						}
					}
				}
			}			
		}
		//Trabajadores con aviso
		
		listaFull = new ArrayList();
		
		for (Iterator itCotPend = cotPendientes.keySet().iterator(); itCotPend.hasNext();)
		{
			ConsultaRespuesta consulta = new ConsultaRespuesta();
			String idCotizante = (String)itCotPend.next();			
			CotizanteVO cotizante = (CotizanteVO)cotPendientes.get(idCotizante);
			consulta.setRutCotizante(cotizante.getIdCotizante());
			consulta.setDvRutCotizante(this.getObtenerDvRut(cotizante.getIdCotizante()));
			consulta.setPeriodo(periodoFormatoSIS);
			consulta.setCodigoAFP(String.valueOf(cotizante.getIdEntPensionReal()));//que pasa en el caso que tenga INP ?????????				
			listaFull.add(consulta);
		}
			
		hashRespuesta = validadorSisBusiness.validador(listaFull);
		
		//mandar aviso
		listaAvisos = new HashMap();
		
		//TODO que pasa en el caso que todos tengan aviso valido igual ???			
		//Trabajadores sin aviso
		for (Iterator itCotPend = cotPendientes.keySet().iterator(); itCotPend.hasNext();)
		{
			listaAvisos = new HashMap();
			String idCotizante = (String)itCotPend.next();	
			CotizanteVO cotizanteVO = (CotizanteVO)cotPendientes.get(idCotizante);
			
			System.out.println("Rut es : "+idCotizante+ " Con aviso");
			
			ConsultaRespuesta consultaRespuesta = (ConsultaRespuesta)hashRespuesta.get(String.valueOf(cotizanteVO.getIdCotizante()));
			HashMap cotizantesPend = (HashMap)this.hashDatos.get(idCotizante);
			
			if(consultaRespuesta != null){
					if (consultaRespuesta.getIdRespuesta() == UtilsAFP.NEG_PERIODO_INVALIDO){
						listaAvisos.put("" + "3821", "PERIODO INVALIDO");
						addCausaAviso(String.valueOf(cotizanteVO.getIdCotizante()), cotizanteVO.getIdCotizante(), listaAvisos, cotizanteVO, tipoProceso);
					}			
					if (consultaRespuesta.getIdRespuesta() == UtilsAFP.NEG_RUT_INVALIDO){
						listaAvisos.put("" + "3823", "RUT INVALIDO");
						addCausaAviso(String.valueOf(cotizanteVO.getIdCotizante()), cotizanteVO.getIdCotizante(), listaAvisos, cotizanteVO, tipoProceso);
					}
					/*if (consultaRespuesta.getIdRespuesta() == UtilsAFP.CTR_ERROR_SERVICIO){
						listaAvisos.put("" + "3829", ConsultaDatosTO.ERROR_SERVICIO);
						addCausaAviso(String.valueOf(cotizanteVO.getIdCotizante()), cotizanteVO.getIdCotizante(), listaAvisos, cotizanteVO, tipoProceso);
					}*/
					if (consultaRespuesta.getCodigoAFPRespuesta() != null){
						if( !String.valueOf(cotizanteVO.getIdEntPensionReal()).equals( consultaRespuesta.getCodigoAFPRespuesta())){
							if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_CAPITAL))
								listaAvisos.put("" + "3827", "Encontrado Otra AFP");
							if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_CUPRUM))
								listaAvisos.put("" + "3823", "Encontrado Otra AFP");
							if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_HABITAT))
								listaAvisos.put("" + "3824", "Encontrado Otra AFP");	
							if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_MODELO_SA))
								listaAvisos.put("" + "3828", "Encontrado Otra AFP");			
							if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_PLANVITAL))
								listaAvisos.put("" + "3825", "Encontrado Otra AFP");				
							if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_PROVIDA))
								listaAvisos.put("" + "3826", "Encontrado Otra AFP");
							addCausaAviso(String.valueOf(cotizanteVO.getIdCotizante()), cotizanteVO.getIdCotizante(), listaAvisos, cotizanteVO, tipoProceso);
						}
					}else{
						if (consultaRespuesta.getIdRespuesta() == UtilsAFP.NEG_NO_ES_AFILIADO){
							listaAvisos.put("" + "3822", "NO AFILIADO AFP");
							addCausaAviso(String.valueOf(cotizanteVO.getIdCotizante()), cotizanteVO.getIdCotizante(), listaAvisos, cotizanteVO, tipoProceso);
						}
					}
					
					if (informaSisEnNomina){
					
						if (consultaRespuesta.getIdRespuesta() == UtilsLocal.NEG_NO_COBRAR_SIS){
							//gmallea se elimina mensaje de aviso ya que no pueden existir los dos 
							if(listaAvisos.containsKey("3492"))
								listaAvisos.remove("3492");
							listaAvisos.put("" + "3820", "NO DEBE PAGAR SIS");
							String line = (String)this.listadoAValidar.get(String.valueOf(idCotizante));					
							registraPendiente(tipoProceso, idCotizante, listaAvisos, cotizantesPend, line, false, periodoFormatoCP);
						}					
					}else{
						if (consultaRespuesta.getIdRespuesta() != UtilsLocal.NEG_SI_COBRAR_SIS){
							listaAvisos.put("" + "3819", "NO DEBE PAGAR SIS");
							String line = (String)this.listadoAValidar.get(String.valueOf(idCotizante));
							registraPendiente(tipoProceso, idCotizante, listaAvisos, cotizantesPend, line, false, periodoFormatoCP);
						}
					}
				}
			}
		}
	
	private List validaCotizanteSis(List result, HashMap parametrosNegocio, CotizanteVO cotizante) throws Exception{

		String periodoFormatoCP="";
		if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA)){
			periodoFormatoCP = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		
		}else if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
			periodoFormatoCP = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO_INDEPENDIENTE);
		}
		
		//asepulveda 2013-05-29
		//Desde parametro de CP el periodo viene como yyyyMM
		//Pero internamente en SIS el periodo se maneja como MMyyyy
		
		String periodoFormatoSIS = periodoFormatoCP.substring(4, 6) + periodoFormatoCP.substring(0, 4);	
		
		boolean informaSisEnNomina = ((Boolean)parametrosNegocio.get("informaSisEnNomina")).booleanValue();
		
		ArrayList listaFull = new ArrayList();
		
		ConsultaRespuesta consulta = new ConsultaRespuesta();										
		consulta.setRutCotizante(cotizante.getIdCotizante());
		consulta.setDvRutCotizante(this.getObtenerDvRut(cotizante.getIdCotizante()));			
		consulta.setPeriodo(periodoFormatoSIS);			
		consulta.setCodigoAFP(String.valueOf(cotizante.getIdEntPensionReal()));//que pasa en el caso que tenga INP ?????????				
		listaFull.add(consulta);
		
		ValidadorSisBusiness validadorSisBusiness = new ValidadorSisBusiness();			
		HashMap hashRespuesta = validadorSisBusiness.validador(listaFull);
		
		ConsultaRespuesta consultaRespuesta = (ConsultaRespuesta)hashRespuesta.get(String.valueOf(cotizante.getIdCotizante()));
		
		if(consultaRespuesta != null){
		
			if (consultaRespuesta.getIdRespuesta() == UtilsAFP.NEG_PERIODO_INVALIDO){			
				result.add(new Integer(3821));
			}
			if (consultaRespuesta.getIdRespuesta() == UtilsAFP.NEG_RUT_INVALIDO){
				result.add(new Integer(3823));
			}
			
			/*if (consultaRespuesta.getIdRespuesta() == UtilsAFP.CTR_ERROR_SERVICIO){
				result.add(new Integer(3829));
			}*/
			
			if (consultaRespuesta.getCodigoAFPRespuesta() != null){
				if( !String.valueOf(cotizante.getIdEntPensionReal()).equals( consultaRespuesta.getCodigoAFPRespuesta())){
					if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_CAPITAL))
						result.add(new Integer(3827));
					if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_CUPRUM))
						result.add(new Integer(3823));
					if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_HABITAT))
						result.add(new Integer(3824));	
					if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_MODELO_SA))
						result.add(new Integer(3828));			
					if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_PLANVITAL))
						result.add(new Integer(3825));				
					if (consultaRespuesta.getCodigoAFPRespuesta().equals(UtilsLocal.AFP_PROVIDA))
						result.add(new Integer(3826));
					
				}
			}else{
				if (consultaRespuesta.getIdRespuesta() == UtilsAFP.NEG_NO_ES_AFILIADO){
					result.add(new Integer(3822));
				}
			}
			
			if (informaSisEnNomina){
			
				if (consultaRespuesta.getIdRespuesta() == UtilsLocal.NEG_NO_COBRAR_SIS){
					//gmallea se elimina mensaje de aviso ya que no pueden existir los dos 
					if(result.contains(new Integer(3492)))
						result.remove(new Integer(3492));
					result.add(new Integer(3820));
				}					
			}else{
				if (consultaRespuesta.getIdRespuesta() == UtilsLocal.NEG_SI_COBRAR_SIS){
					result.add(new Integer(3819));
				}
			}
		}
		return result;
	}
	
	private CotizanteVO cotizantesRepetiposCalculaMovPersonal(CotizanteVO cotizanteNew, HashMap parametrosNegocio,Utils setter,String rutTmp,char tipoProceso,HashMap cotizantesPendientes,String line,boolean resultValidacion,
			HashMap cotPendientes,HashMap listaAvisos,HashMap cotizantes) throws ClassNotFoundException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
				
				//clillo 4-2-15 por Reliquidación
				CotizanteVO cotizanteVOOld =  (CotizanteVO)cotizantes.get("0" + cotizanteNew.getIdCotizante());
				cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO cotizacionGRVOOld = (cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO)cotizanteVOOld.getCotizacion();
				
				cotizacionGRVOOld.setRentaImp((!this.mapeoValores.get("Remuneracion Imponible").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Remuneracion Imponible").toString()): 0)
				+ 	cotizacionGRVOOld.getRentaImp());
				
				cotizacionGRVOOld.setAsigFamiliar((!this.mapeoValores.get("Asignacion Familiar").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Asignacion Familiar").toString()): 0)
				+ 	cotizacionGRVOOld.getAsigFamiliar());
				cotizacionGRVOOld.setAsigFamRetro ((!this.mapeoValores.get("Asig. Familiar Retroactiva").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Asig. Familiar Retroactiva").toString()): 0) 
				+	cotizacionGRVOOld.getAsigFamRetro());
				cotizacionGRVOOld.setAsigFamReint((!this.mapeoValores.get("Reintegros Asignacion Familiar").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Reintegros Asignacion Familiar").toString()): 0)
				+ 	cotizacionGRVOOld.getAsigFamReint());
				cotizacionGRVOOld.setSaludObligatorio((!this.mapeoValores.get("Cotizacion Oblig. de Salud").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Cotizacion Oblig. de Salud").toString()): 0)
				+ 	cotizacionGRVOOld.getSaludObligatorio());
				cotizacionGRVOOld.setSaludPactado((!this.mapeoValores.get("Cotizacion Pactada Salud").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Cotizacion Pactada Salud").toString()): 0)
				+	cotizacionGRVOOld.getSaludPactado());
				cotizacionGRVOOld.setSaludAdicional((!this.mapeoValores.get("Cotizacion Adicional Salud").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Cotizacion Adicional Salud").toString()): 0)
				+	cotizacionGRVOOld.getSaludAdicional());
				cotizacionGRVOOld.setSaludTotal ((!this.mapeoValores.get("Cotizacion Total Salud").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Cotizacion Total Salud").toString()): 0)
				+	cotizacionGRVOOld.getSaludTotal());
				cotizacionGRVOOld.setPrevisionObligatorio ((!this.mapeoValores.get("Cotizacion Obligatoria Prev.").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Cotizacion Obligatoria Prev.").toString()): 0)
				+	cotizacionGRVOOld.getPrevisionObligatorio());
				cotizacionGRVOOld.setTotalPrevision ((!this.mapeoValores.get("Total Prevision").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Total Prevision").toString()): 0)
				+	cotizacionGRVOOld.getTotalPrevision());
				cotizacionGRVOOld.setSegCesTrab((!this.mapeoValores.get("Aporte Trab. Seg. Cesantia").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Aporte Trab. Seg. Cesantia").toString()): 0)
				+	cotizacionGRVOOld.getSegCesTrab());
				cotizacionGRVOOld.setSegCesEmpl ((!this.mapeoValores.get("Aporte Empleador Seg. Cesantia").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Aporte Empleador Seg. Cesantia").toString()): 0)
				+	cotizacionGRVOOld.getSegCesEmpl());
				cotizacionGRVOOld.setTrabPesado ((!this.mapeoValores.get("Cotizacion Trabajo Pesado").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Cotizacion Trabajo Pesado").toString()): 0)
				+	cotizacionGRVOOld.getTrabPesado());
				
				cotizacionGRVOOld.setMutualImp ((!this.mapeoValores.get("Cotizacion Mutual").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Cotizacion Mutual").toString()): 0)
				+	cotizacionGRVOOld.getMutualImp());
				
				if(cotizacionGRVOOld.getRentaImp()  > cotizacionGRVOOld.getMutualImp())
					cotizacionGRVOOld.setMutualImp (cotizacionGRVOOld.getRentaImp());
				
				cotizacionGRVOOld.setCcafAporte((!this.mapeoValores.get("Aporte CCAF").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Aporte CCAF").toString()): 0) 
				+   cotizacionGRVOOld.getCcafAporte());
				
				cotizacionGRVOOld.setSegCesRemImp ((!this.mapeoValores.get("Rem. Imponible Seg. Cesantia").toString().equals("") ? Integer.parseInt(this.mapeoValores.get("Rem. Imponible Seg. Cesantia").toString()): 0)
				+	cotizacionGRVOOld.getSegCesRemImp());
				
				if(cotizacionGRVOOld.getRentaImp()  > cotizacionGRVOOld.getSegCesRemImp())
					cotizacionGRVOOld.setSegCesRemImp (cotizacionGRVOOld.getRentaImp());
				
				if(cotizacionGRVOOld.getIdEntDep() == 0 )
					cotizacionGRVOOld.setIdEntDep(-1);
				
				/*
				if(cotizanteVOOld.getApvList().size() > 0){
				
					int totalApvVOPrimero =0;
					List listApv =  cotizanteVOOld.getApvList();
						for(Iterator it = cotizanteVOOld.getApvList().iterator(); it.hasNext();){
							ApvVO apvVO =	(ApvVO) it.next();
							totalApvVOPrimero =apvVO.getAhorro() + totalApvVOPrimero;
						
						}
					ApvVO apvVOPrimero =	(ApvVO)cotizanteVOOld.getApvList().get(0);
					apvVOPrimero.setAhorro(totalApvVOPrimero);
					listApv.clear();
					listApv.add(apvVOPrimero);
					cotizanteVOOld.setApvList(listApv);
				}
				*/
				
				/*ApvVO apvVOFinal=null;
				for( Iterator itApv = cotizanteVOOld.getApvList().iterator(); itApv.hasNext();){
				
					ApvVO apvVO = (ApvVO)itApv.next();
					if(apvVO.getIdEntidadApv() > 0){
					apvVOFinal = apvVO;
					}
				}
				if(apvVOFinal != null){
					cotizanteVOOld.getApvList().clear();
					cotizanteVOOld.addApv(apvVOFinal);
				}*/
				//clillo 4-2-15 por Reliquidación
				String idPend = "0"+cotizanteVOOld.getIdCotizante();
				cotizantes.remove(idPend);
				this.listaCausaAviso.remove(""+cotizanteVOOld.getIdCotizante());
				
				
				
				ValidacionVO siguiente = getPrimeraValidacion();
				HashMap hashCargasFamiliares = this.getHashCargasPorEmpresa(cotizanteVOOld.getRutEmpresa(), this.convenio.getIdCcaf());
				                                         
				while (siguiente != null)                                                                                                                                                                  
					{                                                                                                                                                                                          
						Class validacion = Class.forName(siguiente.getClaseValidador().trim());                                                                                                                  
						Class[] argTypes = { HashMap.class, Utils.class, Session.class, ConvenioVO.class, List.class, List.class, List.class };                                                                  
						Object[] argValues = { parametrosNegocio, setter, this.session, this.convenioMod, siguiente.getConceptos(), this.listaConceptos, this.listaMapeosValidaciones };                         
						Validacion valida = (Validacion) validacion.getConstructor(argTypes).newInstance(argValues);                                                                                             
						//logger.debug("siguiente.getClaseValidador(): " + siguiente.getClaseValidador() + "::");                                                                                                
						if (siguiente.getClaseValidador().trim().endsWith("VN110") ||                                                                                                                            
							siguiente.getClaseValidador().trim().endsWith("VN120") ||                                                                                                                              
							siguiente.getClaseValidador().trim().endsWith("VN130") ||                                                                                                                              
							siguiente.getClaseValidador().trim().endsWith("VN140")) {                                                                                                                              
							Class[] argTypes2 = { HashMap.class, Utils.class, Session.class, ConvenioVO.class, List.class, List.class, List.class, HashMap.class };                                                
							Object[] argValues2 = { parametrosNegocio, setter, this.session, this.convenioMod, siguiente.getConceptos(), this.listaConceptos, this.listaMapeosValidaciones, hashCargasFamiliares };
							valida = (Validacion) validacion.getConstructor(argTypes2).newInstance(argValues2);                                                                                                    
						}                                                                                                                                                                                        
	                                                                                                                                                                                                   
						int codResult = valida.validaFromWEB(cotizanteVOOld);                                                                                                                                              
	                                                                                                                                                                                                   
						if ((codResult != 0 && isValidable(codResult)) || codResult >= 1000)                                                                                                                     
						{// por cada validacion, guardo errores encontrados                                                                                                                                      
							logger.debug("\n\n\n validacion incorrecta!:RUT:" + rutTmp + ":validacion fallida:" + siguiente.getIdValidacion() + ":codigo error:" + codResult + "::");                              
							//System.out.println("Valor informado en nómina: " + valida.getValor());                                                                                                               
							if (codResult == Constants.CAIDA_SISTEMA || codResult == Constants.SIN_CONCEPTOS)                                                                                                      
								codResult = Constants.INICIO_COD_ERROR_GENERICO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));              
							if (codResult == Constants.CONCEPTOS_SIN_VALOR)                                                                                                                                        
								codResult = Constants.INICIO_COD_ERROR_VACIO + (Integer.parseInt(siguiente.getIdValidacion().trim().substring(2, siguiente.getIdValidacion().trim().length() - 1)));                 
							int contTrabPes = 0;                                                                                                                                                                   
	                                                                                                                                                                                                   
							String _codResult = "" + codResult;                                                                                                                                                    
							if (_codResult.length() > 4)                                                                                                                                                           
							{                                                                                                                                                                                      
								while (contTrabPes < _codResult.length())                                                                                                                                            
								{                                                                                                                                                                                    
									codResult = Integer.parseInt(_codResult.substring(contTrabPes, 3 + contTrabPes));                                                                                                  
									if (isValidable(codResult))                                                                                                                                                        
									{                                                                                                                                                                                  
										String mensaje = ((valida.getMensaje() == null || valida.getMensaje().trim().equals("")) ? siguiente.getIdValidacion() : valida.getMensaje());                                   
										this.listaMensajesValidacion.put(String.valueOf(new Integer(codResult)), mensaje);                                                                                               
										if (!isAviso(codResult))// es error                                                                                                                                              
										{                                                                                                                                                                                
											registraPendiente(tipoProceso, rutTmp, codResult, mensaje, cotizantesPendientes, line, resultValidacion, valida.getValor(), String.valueOf(cotizanteVOOld.getPeriodo()));                                                   
											cotPendientes.put(rutTmp, cotizanteVOOld);                                                                                                                                          
											resultValidacion = false;                                                                                                                                                      
										} else                                                                                                                                                                           
											listaAvisos.put("" + codResult, mensaje);                                                                                                                                      
									}                                                                                                                                                                                  
									contTrabPes += 3;                                                                                                                                                                  
								}                                                                                                                                                                                    
							} else                                                                                                                                                                                 
							{                                                                                                                                                                                      
								logger.debug("\n\n\n validacion incorrecta!:RUT:" + rutTmp + ":validacion fallida:" + siguiente.getIdValidacion() + ":codigo error:" + codResult + "::");                            
								String mensaje = ((valida.getMensaje() == null || valida.getMensaje().trim().equals("")) ? siguiente.getIdValidacion() : valida.getMensaje());                                       
								this.listaMensajesValidacion.put(String.valueOf(new Integer(codResult)), mensaje);                                                                                                   
								if (!isAviso(codResult))// es error                                                                                                                                                  
								{                                                                                                                                                                                    
									registraPendiente(tipoProceso, rutTmp, codResult, mensaje, cotizantesPendientes, line, resultValidacion, valida.getValor(), String.valueOf(cotizanteVOOld.getPeriodo()));                                                       
									cotPendientes.put(rutTmp, cotizanteVOOld);                                                                                                                                              
									resultValidacion = false;                                                                                                                                                          
								} else                                                                                                                                                                               
									listaAvisos.put("" + codResult, mensaje);                                                                                                                                          
							}                                                                                                                                                                                      
						}/*                                                                                                                                                                                      
						 * else logger.info("validacion correcta!:RUT:" + cotizante.getIdCotizante() + ":validacion fallida:" + siguiente.getIdValidacion() + "::");                                             
						 */                                                                                                                                                                                      
						siguiente = getSiguienteValidacion(valida.getResultado(), siguiente.getSiguientes(), this.listaValidaciones);                                                                            
					} // fin validaciones por linea                                                                                                                                                            
				                                             
				
				return cotizanteVOOld;
				
			}
	private CotizanteVO cotizantesRepetiposNOCalculaMovPersonal(CotizanteVO cotizanteNew,HashMap cotizantes){

			//TODO GMALLEA INICO 28/05/2012
			//Aclarando…si opción es No Calcular Mov. de Pesonal, solamente debe rescatar las fechas de Mov. de Personal + APV + Dep.Conv. de los registros repetidos de un trabajador 
			//y si es Gratificaciones no debe hacer nada ya que estos conceptos no existen, o sea, ni siquiera debes leerlos
			
			//clillo 4-2-15 por Reliquidación
			CotizanteVO cotizanteVOOld = (CotizanteVO) cotizantes.get("0"+cotizanteNew.getIdCotizante());
			CotizacionREVO cotizacionREVOOld = (CotizacionREVO)cotizanteVOOld.getCotizacion();
			
			CotizacionREVO cotizacionREVONew = (CotizacionREVO)cotizanteNew.getCotizacion();
			/*
				if(cotizacionREVONew.getMovimientoPersonal().size() > 0){
				
					MovtoPersonalVO mp=null;
					for (Iterator it = cotizacionREVONew.getMovimientoPersonal().iterator(); it.hasNext();){
					
					mp = (MovtoPersonalVO) it.next();
					}
					
					MovtoPersonalVO movtoPersonalVOOld =   (MovtoPersonalVO)cotizacionREVOOld.getMovimientoPersonal().get(0);
					
					movtoPersonalVOOld.setInicio(mp.getInicio());  
					movtoPersonalVOOld.setTermino(mp.getTermino());
					
					List movPersonalNew  =new ArrayList(); 
					movPersonalNew.add(movtoPersonalVOOld);
					
					cotizacionREVOOld.setMovimientoPersonal(movPersonalNew);
					cotizanteVOOld.setCotizacion(cotizacionREVOOld);
				}
			*/
			/*ApvVO apvVOFinal=null;
			for( Iterator itApv = cotizanteNew.getApvList().iterator(); itApv.hasNext();){
			
				ApvVO apvVO = (ApvVO)itApv.next();
				if(apvVO.getIdEntidadApv() > 0){
					//apvVOFinal = apvVO;
					cotizanteVOOld.addApv(apvVO);
				}
			}*/
			/*if(apvVOFinal != null){
				cotizanteVOOld.getApvList().clear();
				cotizanteVOOld.addApv(apvVOFinal);
			}*/
			/*
			if(! cotizanteNew.getApvList().isEmpty()){
			ApvVO apvVOFinal=null;
			Iterator  i = cotizanteNew.getApvList().iterator();
			while (i.hasNext()){
			ApvVO apvVO = (ApvVO)i.next();
			if(apvVO.getIdEntidadApv() > 0){
			apvVOFinal = apvVO;
			}
			}
			*/
			//cotizanteVOOld.getApvList().clear();
			//cotizanteVOOld.addApv(apvVOFinal);	
			//}
			
			if(cotizacionREVONew.getIdEntDep() > 0 && cotizacionREVONew.getDepositoConvenido() > 0){
				cotizacionREVOOld.setIdEntDep( cotizacionREVONew.getIdEntDep());
				cotizacionREVOOld.setDepositoConvenido(cotizacionREVONew.getDepositoConvenido());
			}
		return cotizanteVOOld;
	}
	
	
	private long actualizaRentaImpComprobante() throws DaoException{
		
//		GMALLEA 04-06-2014 Obtenimos el total imponible de los cotizantes
		
		List listCotizantes = this.cotizanteDAO.getCotizantesNomina(this.nomina.getEmpresa().getIdEmpresa(), this.nomina.getConvenio().getIdConvenio(), this.nomina.getTipoProceso());
		
		long totalImponible = 0;
		for (Iterator it = listCotizantes.iterator(); it.hasNext();){		
			
			CotizanteVO cotizacion = (CotizanteVO) it.next();
			
			if(this.nomina.getTipoProceso() == Constants.TIPO_NOM_REMUNERACION){
				
				CotizacionREVO cotizacionREVO = (CotizacionREVO)cotizacion.getCotizacion();
				 
				 if(cotizacionREVO.getRentaImp() > 0){
						totalImponible = totalImponible + cotizacionREVO.getRentaImp();
					}else{
						totalImponible = totalImponible + cotizacionREVO.getRentaImpInp();
					}
				 
			}else if(this.nomina.getTipoProceso() == Constants.TIPO_NOM_RELIQUIDACION){
				
				CotizacionRAVO cotizacionRAVO = (CotizacionRAVO)cotizacion.getCotizacion();
				 
				 if(cotizacionRAVO.getRenta() > 0){
					 totalImponible = totalImponible + cotizacionRAVO.getRenta();
					}else{
						totalImponible = totalImponible + cotizacionRAVO.getRentaImpInp();
					}
				 				 
			}else if(this.nomina.getTipoProceso() == Constants.TIPO_NOM_GRATIFICACION){
				
				CotizacionGRVO cotizacionGRVO = (CotizacionGRVO)cotizacion.getCotizacion();
				 
				 if(cotizacionGRVO.getRenta() > 0){
					 totalImponible = totalImponible + cotizacionGRVO.getRenta();
					}else{
						totalImponible = totalImponible + cotizacionGRVO.getRentaImpInp();
					}
				 				 
			}else if(this.nomina.getTipoProceso() == Constants.TIPO_NOM_DEPOSITOCONVENIDO){
				 
				CotizacionDCVO cotizacionDCVO = (CotizacionDCVO)cotizacion.getCotizacion();
				 
				 if(cotizacionDCVO.getRentaImponible() > 0){
					 totalImponible = totalImponible + cotizacionDCVO.getRentaImponible();
					}else{
						totalImponible = totalImponible + cotizacionDCVO.getRentaImpInp();
					}
				}
		}
		
		
		return totalImponible;
		
	}
}