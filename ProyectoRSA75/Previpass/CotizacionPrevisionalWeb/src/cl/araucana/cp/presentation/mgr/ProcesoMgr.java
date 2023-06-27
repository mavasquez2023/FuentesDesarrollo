package cl.araucana.cp.presentation.mgr;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;

import cl.araucana.core.business.BusinessException;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
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
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DescriptorNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EnvioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizante;
import cl.araucana.cp.distribuidor.presentation.beans.DetalleInforme;
import cl.araucana.cp.distribuidor.presentation.beans.InformacionDeclaraNoPago;
import cl.araucana.cp.distribuidor.presentation.beans.InformacionNomina;
import cl.araucana.cp.distribuidor.presentation.beans.InformeAvisosError;
import cl.araucana.cp.distribuidor.presentation.beans.ResumenInforme;
import cl.araucana.cp.hibernate.beans.DocumentoVO;
import cl.araucana.cp.hibernate.dao.ComprobanteDAO;
import cl.araucana.cp.hibernate.dao.ConvenioDAO;
import cl.araucana.cp.hibernate.dao.EmpresaDAO;
import cl.araucana.cp.hibernate.dao.EnvioDAO;
import cl.araucana.cp.hibernate.dao.EstadosDAO;
import cl.araucana.cp.hibernate.dao.NominaDAO;
import cl.araucana.cp.hibernate.dao.ParametrosDAO;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.utils.FactoryView;
import cl.araucana.cp.utils.ProxyAS400;

/*
 * @(#) ProcesoMgr.java 1.26 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.26
 */
public class ProcesoMgr
{
	private NominaDAO nominaDao;
	private EmpresaDAO empresaDao;
	private EstadosDAO estadoDao;
	private ComprobanteDAO comprobanteDao;
	private ConvenioDAO convenioDao;
	private ParametrosDAO parametrosDAO;
	private ConvenioMgr convenioMgr;

	// NOMINA_EN_LINEA
	private EnvioDAO envioDao;
	
	static Logger logger = Logger.getLogger(ProcesoMgr.class);

	public ProcesoMgr(Session session)
	{
		this.nominaDao = new NominaDAO(session);
		this.empresaDao = new EmpresaDAO(session);
		this.estadoDao = new EstadosDAO(session);
		this.comprobanteDao = new ComprobanteDAO(session);
		this.convenioDao = new ConvenioDAO(session);
		this.parametrosDAO = new ParametrosDAO(session);
		this.convenioMgr = new ConvenioMgr(session);
		// NOMINA_EN_LINEA
		this.envioDao = new EnvioDAO(session);		
	}

	/**
	 * tipos proceso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public Collection getTiposProceso() throws DaoException
	{
		return this.nominaDao.getTiposNominas();
	}

	/**
	 * tipo proceso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public Collection getTipoProceso(String idTipoProceso) throws DaoException
	{
		return this.nominaDao.getTiposNominas(idTipoProceso);
	}
	
	/**
	 * nomina enviada
	 * 
	 * @param tipoNomina
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 */
	public NominaVO getNominaNoEnviada(String tipoNomina, int idEmpresa, int idConvenio)
	{
		logger.debug("no enviada:" + tipoNomina + ":" + idEmpresa + ":" + idConvenio + "::");
		NominaVO nomina = null;
		if (tipoNomina.equals("R"))
			nomina = new NominaREVO();
		else if (tipoNomina.equals("A"))
			nomina = new NominaRAVO();
		else if (tipoNomina.equals("G"))
			nomina = new NominaGRVO();
		else
			nomina = new NominaDCVO();

		try
		{
			nomina.setConvenio(this.convenioDao.getConvenio(idEmpresa, idConvenio));
			nomina.setIdConvenio(idConvenio);
			nomina.setEmpresa(this.empresaDao.getEmpresa(idEmpresa));
			nomina.setRutEmpresa(idEmpresa);
			nomina.setEstado(this.estadoDao.getEstado(Constants.EST_NOM_NO_ENVIADA));
			nomina.setIdEstado(nomina.getEstado().getId());
			nomina.setNombre("(no enviada)");
		} catch (Exception e)
		{
			return null;
		}

		return nomina;
	}

	/**
	 * nomina
	 * 
	 * @param tipoNomina
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public NominaVO getNomina(String tipoNomina, int idEmpresa, int idConvenio) throws DaoException
	{
		NominaVO nomina = this.nominaDao.getNomina(tipoNomina, idEmpresa, idConvenio);

		if (nomina != null && nomina.getIdCodigoBarras() > 0)
		{
			ComprobanteVO comprobante = this.comprobanteDao.getComprobante(nomina.getIdCodigoBarras());
			if (comprobante != null)
				nomina.setMontoNum(comprobante.getTotal());
		}
		return nomina;
	}

	/**
	 * empresa convenio consulta
	 * 
	 * @param tipoNomina
	 * @param persona
	 * @param usuarioCP
	 * @return
	 * @throws DaoException
	 */
	public HashMap getEmpConvConsulta(char tipoNomina, PersonaVO persona, UsuarioCP usuarioCP) throws DaoException
	{
		HashMap result = new HashMap();
		logger.info("ProcesoMgr:getEmpConvConsulta:persona:" + persona.getIdPersona() + "::" + persona.getIdPersona() + "::");
		List convenios = this.convenioDao.getAllConvenios(usuarioCP.getIdPersona(), usuarioCP.getEmpresasAdmin());
		logger.info("ProcesoMgr:getEmpConvConsulta:persona: convenios encontrados:" + convenios.size() + "::");

		int idEmpresa = -1;
		for (Iterator it = convenios.iterator(); it.hasNext();)
		{
			ConvenioVO convenio = (ConvenioVO) it.next();
			if (convenio.getHabilitado() != Constants.COD_HABILITACION_CONVENIO)
				continue;
			logger.debug("\tconvenio:" + convenio.getIdConvenio() + "::" + convenio.getIdEmpresa() + "::");
			NominaVO nomina = this.nominaDao.getNomina("" + tipoNomina, convenio.getIdEmpresa(), convenio.getIdConvenio());
			if (nomina == null || nomina.getNumCotizaciones() == 0)
			{
				logger.info("ProcesoMgr:getEmpConvConsulta:nomina no existe o sin cotizantes:" + convenio.getIdConvenio() + "::" + convenio.getIdEmpresa() + "::");
				continue;
			}
			//clillo 16-12-2015 si estado nómina es "CON_ERRORES" no se muestra en la Consulta
			if (nomina.getIdEstado() == Constants.EST_NOM_EN_PROCESO || nomina.getIdEstado() == Constants.EST_NOM_NO_CARGADA || nomina.getIdEstado() == Constants.EST_NOM_NO_ENVIADA || nomina.getIdEstado() == Constants.EST_NOM_CON_ERRORES)
			{
				logger.info("ProcesoMgr:getEmpConvConsulta:nomina en proceso, no cargada, no enviada o con errrores:" + convenio.getIdConvenio() + "::" + convenio.getIdEmpresa() + "::");
				continue;
			}
			if (convenio.getIdEmpresa() != idEmpresa)
			{
				idEmpresa = convenio.getIdEmpresa();
				EmpresaVO empresa = this.empresaDao.getEmpresa(idEmpresa);
				empresa.setIdEmpresaFmt(Utils.formatRut(idEmpresa));
				empresa.addConvenio(convenio);
				result.put("" + idEmpresa, empresa);
			} else
			{
				EmpresaVO empresa = (EmpresaVO) result.get("" + idEmpresa);
				empresa.addConvenio(convenio);
			}
		}
		return result;
	}
	
	/**
	 * empresa convenio consulta
	 * 
	 * @param tipoNomina
	 * @param persona
	 * @param usuarioCP
	 * @return
	 * @throws DaoException
	 */
	public HashMap getEmpConvDescargaPC(char tipoNomina, PersonaVO persona, UsuarioCP usuarioCP) throws DaoException
	{
		HashMap result = new HashMap();
		logger.info("ProcesoMgr:getEmpConvDescargaPC:persona:" + persona.getIdPersona() + "::" + persona.getIdPersona() + "::");
		List convenios = this.convenioDao.getAllConvenios(usuarioCP.getIdPersona(), usuarioCP.getUnionEmpresasEscritura());
		logger.info("ProcesoMgr:getEmpConvDescargaPC:persona: convenios encontrados:" + convenios.size() + "::");

		int idEmpresa = -1;
		for (Iterator it = convenios.iterator(); it.hasNext();)
		{
			ConvenioVO convenio = (ConvenioVO) it.next();
			if (convenio.getHabilitado() != Constants.COD_HABILITACION_CONVENIO)
				continue;
			logger.debug("\tconvenio:" + convenio.getIdConvenio() + "::" + convenio.getIdEmpresa() + "::");
			GrupoConvenioVO grupo= this.convenioDao.getGrupoConvenio(convenio.getIdGrupoConvenio());
			//GrupoConvenioVO grupo= this.convenioMgr.getGrupoConvenio(convenio.getIdGrupoConvenio());
			if (grupo == null || !grupo.isProdCaja())
			{
				logger.info("ProcesoMgr:getEmpConvDescargaPC: convenio no es Productos Caja:" + convenio.getIdConvenio() + "::" + convenio.getIdEmpresa() + "::");
				continue;
			}
			if (convenio.getIdEmpresa() != idEmpresa)
			{
				idEmpresa = convenio.getIdEmpresa();
				EmpresaVO empresa = this.empresaDao.getEmpresa(idEmpresa);
				empresa.setIdEmpresaFmt(Utils.formatRut(idEmpresa));
				empresa.addConvenio(convenio);
				result.put("" + idEmpresa, empresa);
			} else
			{
				EmpresaVO empresa = (EmpresaVO) result.get("" + idEmpresa);
				empresa.addConvenio(convenio);
			}
		}
		return result;
	}
	
	/**
	 * empresa convenio sabana
	 * 
	 * @param tipoNomina
	 * @param persona
	 * @param regPorPag
	 * @param desde
	 * @return
	 * @throws DaoException
	 */
	public HashMap getEmpConvSabana(char tipoNomina, PersonaVO persona) throws DaoException
	{
		HashMap result = new HashMap();
		logger.info("ProcesoMgr:getEmpConvSabana:" + persona.getIdPersona() + "::" + persona.getIdPersona() + "::");
		List empresasPermisos = this.empresaDao.getEmpresasPermisos(persona.getIdPersona().intValue());
		int idEmpresa = -1;
		for (Iterator it = empresasPermisos.iterator(); it.hasNext();)
		{
			EmpresaVO empresa = (EmpresaVO) it.next();
			logger.debug("\tempresa:" + empresa.getIdEmpresa() + "::");
			idEmpresa = empresa.getIdEmpresa();
			empresa.setIdEmpresaFmt(Utils.formatRut(idEmpresa));
			List conveniosPermisos = this.convenioDao.getConveniosPermisos(persona.getIdPersona().intValue(), idEmpresa, true);
			for (Iterator it2 = conveniosPermisos.iterator(); it2.hasNext();)
			{
				ConvenioVO convenio = (ConvenioVO) it2.next();
				NominaVO nomina = this.nominaDao.getNomina("" + tipoNomina, empresa.getIdEmpresa(), convenio.getIdConvenio());
				if (nomina != null && nomina.getIdCodigoBarras() > 0)
				{
					ComprobanteVO comp = this.comprobanteDao.getComprobante(nomina.getIdCodigoBarras());
					if (comp != null)
					{
						convenio.addComprobante("" + nomina.getIdCodigoBarras());
						empresa.addConvenio(convenio);
						result.put("" + idEmpresa, empresa);
					}
				} else
					logger.debug("\telimina:" + convenio.getIdConvenio() + "::" + convenio.getIdEmpresa() + ":nomina:" + nomina + ":codBarras:" + (nomina == null ? -1 : nomina.getIdCodigoBarras())
							+ "::");
			}
		}
		return result;
	}

	public String getNombreTipoNomina(String tipoNomina) throws DaoException
	{
		return this.nominaDao.getNombreTipoNomina(tipoNomina);
	}
	
	// NOMINA_EN_LINEA
	public void crearNominaEnLinea(int idPersona, int idEmpresa, int idConvenio, char tipoNomina) throws DaoException {
		
		Date now = new Date();
		Timestamp nowTS = new Timestamp(now.getTime());
		
		EnvioVO envio = new EnvioVO();
		
		envio.setIdEstado(0);
		envio.setIdNodo(0);
		envio.setIdEncargado(idPersona);
		envio.setRecibido(nowTS);
		envio.setDuracion(0);
		envio.setNumNominas(1);
		envio.setNumAceptadas(1);

		envioDao.guardaEnvio(envio);
		
		DescriptorNominaVO descriptorNomina = new DescriptorNominaVO();
		
		descriptorNomina.setIdConvenio(idConvenio);
		descriptorNomina.setIdEnvio(envio.getId());
		descriptorNomina.setTipoProceso(tipoNomina);
		descriptorNomina.setRutEmpresa(idEmpresa);
		descriptorNomina.setIdRechazo(0);
		descriptorNomina.setIdGrupoConvenio(0);
		descriptorNomina.setNormalSize(0);
		descriptorNomina.setComprimidoSize(0);
		descriptorNomina.setNumRegistros(0);
		
		envioDao.guardaDescriptor(descriptorNomina);
		
		DocumentoVO documento = new DocumentoVO();
		
		documento.setIdConvenio(idConvenio);
		documento.setIdEnvio(envio.getId());
		documento.setTipoProceso(tipoNomina);
		documento.setRutEmpresa(idEmpresa);
		documento.setNombre("NOMINA CREADA EN LINEA");
		documento.setData(new byte[0]);

		envioDao.guardaDocumento(documento);
		
		NominaVO nomina;
		
		switch (tipoNomina) {
			case Constants.TIPO_NOM_REMUNERACION:
				
				nomina = new NominaREVO();
				
				break;
				
			case Constants.TIPO_NOM_GRATIFICACION:
				
				NominaGRVO nominaGR = new NominaGRVO();
				
				nominaGR.setInicio(new java.sql.Date(1));
				nominaGR.setTermino(new java.sql.Date(1));
				
				nomina = nominaGR;
				
				break;
				
			case Constants.TIPO_NOM_DEPOSITOCONVENIDO:
				
				nomina = new NominaDCVO();
				
				break;
				
			default:	// Nómina de Reliquidaciones.
				
				NominaRAVO nominaRA = new NominaRAVO();
				
				nominaRA.setInicio(new java.sql.Date(1));
				nominaRA.setTermino(new java.sql.Date(1));
				
				nomina = nominaRA;
			
				break;						
		}
		
		nomina.setRutEmpresa(idEmpresa);
		nomina.setIdConvenio(idConvenio);
		nomina.setIdEstado(Constants.EST_NOM_CREADA_EN_LINEA);
		nomina.setIdCodigoBarras(0);
		nomina.setIdEnvio(envio.getId());
		nomina.setNombre("NOMINA CREADA EN LINEA");
		nomina.setRecibida(nowTS);
		nomina.setAceptada(nowTS);
		nomina.setCrc(0);
		nomina.setNumReenvios(0);
		nomina.setNumCotizaciones(0);
		nomina.setNumCotizOK(0);
		nomina.setNumCotizCorregidas(0);
		
		nominaDao.crearNomina(nomina);
	}
	
	//	jlandero 19/08/2010
	/**
	 * Muestra la lista de errores dado la empresa y el id del convenio
	 * @param idEmpresa
	 * @param idGrConvenio
	 * @param tipoNomina
	 * @param aviso
	 * @param listaEmpresas
	 * @return
	 * @throws DaoException
	 */
	public List getInformeAvisos(String idEmpresa, String idConvenio, String tipoNomina, String aviso, List listaEmpresas, Integer[] tipoCausas) throws DaoException
	{
		List listadoErrores = nominaDao.getInformeAvisos(idEmpresa, idConvenio, tipoNomina, aviso, listaEmpresas, tipoCausas);

		int idCotizante = 0;
		int periodo=0;
		List listadoInforme = new ArrayList();
		ArrayList listadoDetalleInforme = new ArrayList();

		ResumenInforme informe = new ResumenInforme();	
		CotizanteVO cot;

		for (int i=0 ; i < listadoErrores.size() ; i++){

			Object[] listaInforme = (Object[])listadoErrores.get(i);

			cot = (CotizanteVO)listaInforme[0];

			//Detalle del informe
			CausaVO cau = new CausaVO();

			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION))){
				CausaAvisoRAVO causa = (CausaAvisoRAVO)listaInforme[1];
				cau.setIdCausa(causa.getIdCausa());
				cau.setIdConvenio(causa.getIdConvenio());
				cau.setIdCotizPendiente(causa.getIdCotizPendiente());
				cau.setIdTipoCausa(causa.getIdTipoCausa());
				cau.setRutEmpresa(causa.getRutEmpresa());
				cau.setTexto(causa.getTexto());
				cau.setPeriodo(causa.getPeriodo());
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION))){
				CausaAvisoGRVO causa = (CausaAvisoGRVO)listaInforme[1];
				cau.setIdCausa(causa.getIdCausa());
				cau.setIdConvenio(causa.getIdConvenio());
				cau.setIdCotizPendiente(causa.getIdCotizPendiente());
				cau.setIdTipoCausa(causa.getIdTipoCausa());
				cau.setRutEmpresa(causa.getRutEmpresa());
				cau.setTexto(causa.getTexto());
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION))){
				CausaAvisoREVO causa = (CausaAvisoREVO)listaInforme[1];
				cau.setIdCausa(causa.getIdCausa());
				cau.setIdConvenio(causa.getIdConvenio());
				cau.setIdCotizPendiente(causa.getIdCotizPendiente());
				cau.setIdTipoCausa(causa.getIdTipoCausa());
				cau.setRutEmpresa(causa.getRutEmpresa());
				cau.setTexto(causa.getTexto());
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO))){
				CausaAvisoDCVO causa = (CausaAvisoDCVO)listaInforme[1];
				cau.setIdCausa(causa.getIdCausa());
				cau.setIdConvenio(causa.getIdConvenio());
				cau.setIdCotizPendiente(causa.getIdCotizPendiente());
				cau.setIdTipoCausa(causa.getIdTipoCausa());
				cau.setRutEmpresa(causa.getRutEmpresa());
				cau.setTexto(causa.getTexto());
			}
						
			TipoCausaVO tca = (TipoCausaVO)listaInforme[2];
			
			
			/*if (idCotizante == cot.getIdCotizante()){				
				listadoDetalleInforme.add(cau);
				listadoDetalleInforme.add(tca);
			}else{
				listadoDetalleInforme = new ArrayList();
				informe = new InformeAvisos(); 				
				
				
				informe.setCotizante(cot);
				listadoDetalleInforme.add(cau);
				listadoDetalleInforme.add(tca);
				
				if (listadoDetalleInforme != null){
					informe.setListadoDetalle(listadoDetalleInforme);
					listadoInforme.add(informe);
				}

			}if (i == listadoErrores.size()-1){
				informe.setListadoDetalle(listadoDetalleInforme);
				listadoInforme.add(informe);
			}*/
			
			if (i == 0){
				cot.setFormatRut(Utils.formatRut(cot.getIdCotizante()));
				informe.setCotizante(cot);
				
				DetalleInforme det = new DetalleInforme();
				det.setCausa(cau);
				det.setTipoCausa(tca);
				listadoDetalleInforme.add(det);
				idCotizante = cot.getIdCotizante();
				periodo = cot.getPeriodo();
				
			}else {
				if  (idCotizante != cot.getIdCotizante() || periodo!= cot.getPeriodo()){				
				
					informe.setListadoDetalle(listadoDetalleInforme);
					listadoInforme.add(informe);
					
					listadoDetalleInforme = new ArrayList();
					informe = new ResumenInforme(); 
					cot.setFormatRut(Utils.formatRut(cot.getIdCotizante()));
					informe.setCotizante(cot);
					DetalleInforme det = new DetalleInforme();
					det.setCausa(cau);
					det.setTipoCausa(tca);
					listadoDetalleInforme.add(det);
					idCotizante = cot.getIdCotizante();
					periodo = cot.getPeriodo();
				} else /*if (idCotizante == cot.getIdCotizante())*/ {
					DetalleInforme det = new DetalleInforme();
					det.setCausa(cau);
					det.setTipoCausa(tca);
					listadoDetalleInforme.add(det);
					idCotizante = cot.getIdCotizante();
					periodo = cot.getPeriodo();
				}					
			}if (i == listadoErrores.size()-1){
				informe.setListadoDetalle(listadoDetalleInforme);
				listadoInforme.add(informe);
			}								
		}
		
		return listadoInforme;
	}

	/**
	 * Muestra la lista de errores dado la empresa y el id del convenio
	 * @param idEmpresa
	 * @param idGrConvenio
	 * @param tipoNomina
	 * @param aviso
	 * @param listaEmpresas
	 * @return
	 * @throws DaoException
	 */
	public List getInformeAvisosPendientes(String idEmpresa, String idConvenio, String tipoNomina, String aviso, List listaEmpresas, int origenAvisos, FactoryView fact) throws DaoException
	{
		List listadoErrores = nominaDao.getInformeAvisosPendientes(idEmpresa, idConvenio, tipoNomina, aviso, listaEmpresas);

		int idCotizante = 0;
		
		List listadoInforme = new ArrayList();
		ArrayList listadoDetalleInforme = new ArrayList();
		
		ResumenInforme informe = new ResumenInforme();	
		CotizanteVO cot;
		
		for (int i=0 ; i < listadoErrores.size() ; i++){
									
			Object[] listaInforme = (Object[])listadoErrores.get(i);
			
			CotizacionPendienteVO cotizPendiente = (CotizacionPendienteVO) listaInforme[0];
			Cotizante cotizante = fact.cotizPendVOtoView(tipoNomina.charAt(0), cotizPendiente, Constants.NIVEL_VAL_ERROR, null);
			cot = new CotizanteVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), cotizante.getIdCotizPendiente());
			cot.setNombre(cotizante.getNombre());
			cot.setApellidoPat(cotizante.getApellidoPat());
			cot.setApellidoMat(cotizante.getApellidoMat());

			//Detalle del informe			
			CausaVO cau = new CausaVO();
			
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION))) {
				CausaRAVO causa = (CausaRAVO)listaInforme[1];
				cau.setIdCausa(causa.getIdCausa());
				cau.setIdConvenio(causa.getIdConvenio());
				cau.setIdCotizPendiente(causa.getIdCotizPendiente());
				cau.setIdTipoCausa(causa.getIdTipoCausa());
				cau.setRutEmpresa(causa.getRutEmpresa());
				cau.setTexto(causa.getTexto());
				cau.setPeriodo(causa.getPeriodo());
				cot.setPeriodo(causa.getPeriodo());
				cotizPendiente.setPeriodo(causa.getPeriodo());
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION))) {
				CausaGRVO causa = (CausaGRVO)listaInforme[1];
				cau.setIdCausa(causa.getIdCausa());
				cau.setIdConvenio(causa.getIdConvenio());
				cau.setIdCotizPendiente(causa.getIdCotizPendiente());
				cau.setIdTipoCausa(causa.getIdTipoCausa());
				cau.setRutEmpresa(causa.getRutEmpresa());
				cau.setTexto(causa.getTexto());
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION))) {
				CausaREVO causa = (CausaREVO)listaInforme[1];
				cau.setIdCausa(causa.getIdCausa());
				cau.setIdConvenio(causa.getIdConvenio());
				cau.setIdCotizPendiente(causa.getIdCotizPendiente());
				cau.setIdTipoCausa(causa.getIdTipoCausa());
				cau.setRutEmpresa(causa.getRutEmpresa());
				cau.setTexto(causa.getTexto());
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO))) {
				CausaDCVO causa = (CausaDCVO)listaInforme[1];
				cau.setIdCausa(causa.getIdCausa());
				cau.setIdConvenio(causa.getIdConvenio());
				cau.setIdCotizPendiente(causa.getIdCotizPendiente());
				cau.setIdTipoCausa(causa.getIdTipoCausa());
				cau.setRutEmpresa(causa.getRutEmpresa());
				cau.setTexto(causa.getTexto());
			}
						
			TipoCausaVO tca = (TipoCausaVO)listaInforme[2];
			
			if (i == 0){
				//cot.setFormatRut(Utils.formatRut(cot.getIdCotizante()));
				cot.setFormatRut(Utils.formatRut(Integer.parseInt(cotizante.getIdCotizante().trim().replaceAll(" ","").substring(0, cotizante.getIdCotizante().trim().replaceAll(" ","").length()-1))));
				//cot.setFormatRut(Utils.formatRut(Integer.parseInt(cotizante.getIdCotizante().trim().replaceAll(" ",""))));
				informe.setCotizante(cot);
				
				DetalleInforme det = new DetalleInforme();
				det.setCausa(cau);
				det.setTipoCausa(tca);
				listadoDetalleInforme.add(det);
				idCotizante = cot.getIdCotizante();
			} else {
				if (idCotizante != cot.getIdCotizante()) {				
				
					informe.setListadoDetalle(listadoDetalleInforme);
					listadoInforme.add(informe);
					
					listadoDetalleInforme = new ArrayList();
					informe = new ResumenInforme(); 
					//cot.setFormatRut(Utils.formatRut(cot.getIdCotizante()));
					cot.setFormatRut(Utils.formatRut(Integer.parseInt(cotizante.getIdCotizante().trim().replaceAll(" ","").substring(0, cotizante.getIdCotizante().trim().replaceAll(" ","").length()-1))));
					//cot.setFormatRut(Utils.formatRut(Integer.parseInt(cotizante.getIdCotizante().trim().replaceAll(" ",""))));
					informe.setCotizante(cot);
					DetalleInforme det = new DetalleInforme();
					det.setCausa(cau);
					det.setTipoCausa(tca);
					listadoDetalleInforme.add(det);
					idCotizante = cot.getIdCotizante();
				} else {
					DetalleInforme det = new DetalleInforme();
					det.setCausa(cau);
					det.setTipoCausa(tca);
					listadoDetalleInforme.add(det);
					idCotizante = cot.getIdCotizante();
				}					
			} if (i == listadoErrores.size()-1) {
				informe.setListadoDetalle(listadoDetalleInforme);
				listadoInforme.add(informe);
			}								
		}
		
		return listadoInforme;
	}
	
	
	/**
	 * Muestra la lista de errores dado la empresa y el convenio
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoNomina
	 * @param aviso
	 * @param tipoProceso
	 * @param fact
	 * @param listaEmpresas
	 * @return
	 * @throws DaoException
	 */
	public List getInformeErrores(String idEmpresa, String idConvenio, String tipoNomina, String aviso, char tipoProceso, FactoryView fact, List listaEmpresas) throws DaoException
	{
		
		List listadoErrores = nominaDao.getInformeErrores(idEmpresa, idConvenio, tipoNomina, aviso, listaEmpresas);
		
		int idCotizante = 0;
		int periodo= 0;
		List listadoInforme = new ArrayList();
		ArrayList listadoDetalleInforme = new ArrayList();
		
		ResumenInforme informe = new ResumenInforme();	
		//FactoryView fact = new FactoryView();
		
		for (int i=0 ; i < listadoErrores.size() ; i++){
									
			Object[] listaInforme = (Object[])listadoErrores.get(i);
			
			//CotizanteVO cot = (CotizanteVO)listaInforme[0];
			
			CotizacionPendienteVO cotizPendiente = (CotizacionPendienteVO) listaInforme[0];
			Cotizante cot = fact.cotizPendVOtoView(tipoProceso, cotizPendiente, Constants.NIVEL_VAL_ERROR, null);
			
			//Detalle del informe			
			CausaVO cau = new CausaVO();
			
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION))){
				CausaRAVO causa = (CausaRAVO)listaInforme[1];
				cau.setIdCausa(causa.getIdCausa());
				cau.setIdConvenio(causa.getIdConvenio());
				cau.setIdCotizPendiente(causa.getIdCotizPendiente());
				cau.setIdTipoCausa(causa.getIdTipoCausa());
				cau.setRutEmpresa(causa.getRutEmpresa());
				cau.setTexto(causa.getTexto());
				cau.setValorInformado(causa.getValorInformado());
				cau.setPeriodo(causa.getPeriodo());
				cot.setPeriodo(causa.getPeriodo());
				cotizPendiente.setPeriodo(causa.getPeriodo());
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION))){
				CausaGRVO causa = (CausaGRVO)listaInforme[1];
				cau.setIdCausa(causa.getIdCausa());
				cau.setIdConvenio(causa.getIdConvenio());
				cau.setIdCotizPendiente(causa.getIdCotizPendiente());
				cau.setIdTipoCausa(causa.getIdTipoCausa());
				cau.setRutEmpresa(causa.getRutEmpresa());
				cau.setTexto(causa.getTexto());
				cau.setValorInformado(causa.getValorInformado());
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION))){
				CausaREVO causa = (CausaREVO)listaInforme[1];
				cau.setIdCausa(causa.getIdCausa());
				cau.setIdConvenio(causa.getIdConvenio());
				cau.setIdCotizPendiente(causa.getIdCotizPendiente());
				cau.setIdTipoCausa(causa.getIdTipoCausa());
				cau.setRutEmpresa(causa.getRutEmpresa());
				cau.setTexto(causa.getTexto());
				cau.setValorInformado(causa.getValorInformado());
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO))){
				CausaDCVO causa = (CausaDCVO)listaInforme[1];
				cau.setIdCausa(causa.getIdCausa());
				cau.setIdConvenio(causa.getIdConvenio());
				cau.setIdCotizPendiente(causa.getIdCotizPendiente());
				cau.setIdTipoCausa(causa.getIdTipoCausa());
				cau.setRutEmpresa(causa.getRutEmpresa());
				cau.setTexto(causa.getTexto());
				cau.setValorInformado(causa.getValorInformado());
			}

			TipoCausaVO tca = (TipoCausaVO)listaInforme[2];

			/*if (idCotizante == cot.getIdCotizante()){				
				listadoDetalleInforme.add(cau);
				listadoDetalleInforme.add(tca);
			}else{
				listadoDetalleInforme = new ArrayList();
				informe = new InformeAvisos(); 				
				informe.setCotizante(cot);
				listadoDetalleInforme.add(cau);
				listadoDetalleInforme.add(tca);
				if (listadoDetalleInforme != null){
					informe.setListadoDetalle(listadoDetalleInforme);
					listadoInforme.add(informe);
				}
			}if (i == listadoErrores.size()-1){
				informe.setListadoDetalle(listadoDetalleInforme);
				listadoInforme.add(informe);
			}*/

			if (i == 0){
				cot.setRut(Utils.formatRut(cot.getIdCotizPendiente()));
				informe.setCotizantePendiente(cot);

				DetalleInforme det = new DetalleInforme();
				det.setCausa(cau);
				det.setTipoCausa(tca);
				listadoDetalleInforme.add(det);
				idCotizante = cot.getIdCotizPendiente();
				periodo= cot.getPeriodo();
			}else {
				if  (idCotizante != cot.getIdCotizPendiente() || periodo != cot.getPeriodo()){				
				
					informe.setListadoDetalle(listadoDetalleInforme);
					listadoInforme.add(informe);
					
					listadoDetalleInforme = new ArrayList();
					informe = new ResumenInforme(); 
					cot.setRut(Utils.formatRut(cot.getIdCotizPendiente()));
					informe.setCotizantePendiente(cot);
					DetalleInforme det = new DetalleInforme();
					det.setCausa(cau);
					det.setTipoCausa(tca);
					listadoDetalleInforme.add(det);
					idCotizante = cot.getIdCotizPendiente();
					periodo = cot.getPeriodo();
				}if (idCotizante == cot.getIdCotizPendiente() && periodo == cot.getPeriodo()){
					DetalleInforme det = new DetalleInforme();
					det.setCausa(cau);
					det.setTipoCausa(tca);
					listadoDetalleInforme.add(det);
					idCotizante = cot.getIdCotizPendiente();
					periodo= cot.getPeriodo();
				}					
			}if (i == listadoErrores.size()-1){
				informe.setListadoDetalle(listadoDetalleInforme);
				listadoInforme.add(informe);
			}								
		}
		
		return listadoInforme;
	}
	
	/**
	 * Muestra la lista de empresas que tiene avisos o errores
	 * @param idEmpresa
	 * @param razonSocial
	 * @param tipoNomina
	 * @param listaEmpresas
	 * @return
	 * @throws DaoException
	 */
	public List getEmpresasAvisosErrores(String idEmpresa, String razonSocial, String tipoNomina, List listaEmpresas) throws DaoException
	{
		//Se obtienen los errores y avisos de CAUSACXP
		List listado = nominaDao.getEmpresasAvisosErrores(idEmpresa, razonSocial, tipoNomina, listaEmpresas);
		
		//List empresasExcluir = new ArrayList();
		List listadoFinal = new ArrayList();

		int idConvenioPrev = 0;
		int rutEmpresaPrev = 0;
		int idConvenioActual;
		int rutEmpresaPrevActual;
		InformeAvisosError inf = null;
		for (int i=0 ; i < listado.size() ; i++) {
			Object[] listaInforme = (Object[])listado.get(i);
			
			idConvenioActual = ((Integer) listaInforme[0]).intValue();
			rutEmpresaPrevActual = ((Integer) listaInforme[1]).intValue();
			
			if(idConvenioPrev != idConvenioActual || rutEmpresaPrev != rutEmpresaPrevActual){
				if(inf!=null)
					listadoFinal.add(inf);
				
				inf = new InformeAvisosError();
				inf.setIdConvenio(idConvenioActual);
				inf.setRutEmpresa(rutEmpresaPrevActual);
				inf.setRazonSocial((String)listaInforme[2]);
				inf.setHabilitada(((Integer) listaInforme[3]));
				inf.setTieneError( ((Integer) listaInforme[4]).intValue() == 0 ? true : false );
				inf.setTieneAviso( ((Integer) listaInforme[4]).intValue() == 1 ? true : false );
				inf.setRutFormateado(Utils.formatRut(inf.getRutEmpresa()));
				
				idConvenioPrev = idConvenioActual;
				rutEmpresaPrev = rutEmpresaPrevActual;
			} else {
				inf.setTieneAviso( ((Integer) listaInforme[4]).intValue() == 1 ? true : false );
			}
			
			if (((Integer) listaInforme[4]).intValue() == 1 ? true : false) {
				inf.setOrigenTablaAviso(Constants.AVISOS_TABLA_CAUSACXP);
				//empresasExcluir.add(inf);
			}
		}
		if(inf!=null)
			listadoFinal.add(inf);

		inf = null;
		idConvenioPrev = 0;
		rutEmpresaPrev = 0;

		//Se buscan los Avisos en CAUSAAVISOCXP. Los avisos no coexisten en ambas tablas
		List listadoAvisos = nominaDao.getEmpresasAvisos(idEmpresa, razonSocial, tipoNomina, listaEmpresas);

		for (int i=0 ; i < listadoAvisos.size() ; i++) {
			Object[] listaInforme = (Object[])listadoAvisos.get(i);
			
			idConvenioActual = ((Integer) listaInforme[0]).intValue();
			rutEmpresaPrevActual = ((Integer) listaInforme[1]).intValue();
			
			if(idConvenioPrev != idConvenioActual || rutEmpresaPrev != rutEmpresaPrevActual){
				if(inf!=null)
					listadoFinal.add(inf);
				
				inf = new InformeAvisosError();
				inf.setIdConvenio(idConvenioActual);
				inf.setRutEmpresa(rutEmpresaPrevActual);
				inf.setRazonSocial((String)listaInforme[2]);
				inf.setHabilitada(((Integer) listaInforme[3]));
				inf.setTieneError(false);
				inf.setOrigenTablaAviso(Constants.AVISOS_TABLA_CAUSAAVISOCXP);
				inf.setTieneAviso(true);
				inf.setRutFormateado(Utils.formatRut(inf.getRutEmpresa()));

				idConvenioPrev = idConvenioActual;
				rutEmpresaPrev = rutEmpresaPrevActual;
			}
		}
		if(inf!=null)
			listadoFinal.add(inf);
		
		return listadoFinal;
	}
	/**
	 * Muestra la lista de empresas DNP
	 * @param idEmpresa
	 * @param razonSocial
	 * @param tipoNomina
	 * @param listaEmpresas
	 * @return
	 * @throws DaoException
	 */
	public List getEmpresasDNP(String idEmpresa, String razonSocial, String tipoNomina, List listaEmpresas) throws DaoException
	{
		//Se obtienen los errores y avisos de CAUSACXP
		List listado = nominaDao.getEmpresasAvisosErrores(idEmpresa, razonSocial, tipoNomina, listaEmpresas);
		
		//List empresasExcluir = new ArrayList();
		List listadoFinal = new ArrayList();

		int idConvenioPrev = 0;
		int rutEmpresaPrev = 0;
		int idConvenioActual;
		int rutEmpresaPrevActual;
		InformeAvisosError inf = null;
		for (int i=0 ; i < listado.size() ; i++) {
			Object[] listaInforme = (Object[])listado.get(i);
			
			idConvenioActual = ((Integer) listaInforme[0]).intValue();
			rutEmpresaPrevActual = ((Integer) listaInforme[1]).intValue();
			
			if(idConvenioPrev != idConvenioActual || rutEmpresaPrev != rutEmpresaPrevActual){
				if(inf!=null)
					listadoFinal.add(inf);
				
				inf = new InformeAvisosError();
				inf.setIdConvenio(idConvenioActual);
				inf.setRutEmpresa(rutEmpresaPrevActual);
				inf.setRazonSocial((String)listaInforme[2]);
				inf.setHabilitada(((Integer) listaInforme[3]));
				inf.setTieneError( ((Integer) listaInforme[4]).intValue() == 0 ? true : false );
				inf.setTieneAviso( ((Integer) listaInforme[4]).intValue() == 1 ? true : false );
				inf.setRutFormateado(Utils.formatRut(inf.getRutEmpresa()));
				
				idConvenioPrev = idConvenioActual;
				rutEmpresaPrev = rutEmpresaPrevActual;
			} else {
				inf.setTieneAviso( ((Integer) listaInforme[4]).intValue() == 1 ? true : false );
			}
			
			if (((Integer) listaInforme[4]).intValue() == 1 ? true : false) {
				inf.setOrigenTablaAviso(Constants.AVISOS_TABLA_CAUSACXP);
				//empresasExcluir.add(inf);
			}
		}
		if(inf!=null)
			listadoFinal.add(inf);

		inf = null;
		idConvenioPrev = 0;
		rutEmpresaPrev = 0;

		//Se buscan los Avisos en CAUSAAVISOCXP. Los avisos no coexisten en ambas tablas
		List listadoAvisos = nominaDao.getEmpresasAvisos(idEmpresa, razonSocial, tipoNomina, listaEmpresas);

		for (int i=0 ; i < listadoAvisos.size() ; i++) {
			Object[] listaInforme = (Object[])listadoAvisos.get(i);
			
			idConvenioActual = ((Integer) listaInforme[0]).intValue();
			rutEmpresaPrevActual = ((Integer) listaInforme[1]).intValue();
			
			if(idConvenioPrev != idConvenioActual || rutEmpresaPrev != rutEmpresaPrevActual){
				if(inf!=null)
					listadoFinal.add(inf);
				
				inf = new InformeAvisosError();
				inf.setIdConvenio(idConvenioActual);
				inf.setRutEmpresa(rutEmpresaPrevActual);
				inf.setRazonSocial((String)listaInforme[2]);
				inf.setHabilitada(((Integer) listaInforme[3]));
				inf.setTieneError(false);
				inf.setOrigenTablaAviso(Constants.AVISOS_TABLA_CAUSAAVISOCXP);
				inf.setTieneAviso(true);
				inf.setRutFormateado(Utils.formatRut(inf.getRutEmpresa()));

				idConvenioPrev = idConvenioActual;
				rutEmpresaPrev = rutEmpresaPrevActual;
			}
		}
		if(inf!=null)
			listadoFinal.add(inf);
		
		return listadoFinal;
	}
	/**
	 * Muestra la lista de empresas que tiene avisos o errores
	 * @param idEmpresa
	 * @param razonSocial
	 * @param tipoNomina
	 * @param listaEmpresas
	 * @return
	 * @throws DaoException
	 */
	public List getEmpresasAvisosErroresByConvenio(String idEmpresa, String razonSocial, String tipoNomina, String convenio) throws DaoException
	{
		//Se obtienen los errores y avisos de CAUSACXP
		List listado = nominaDao.getEmpresasAvisosErroresByConvenio(idEmpresa, razonSocial, tipoNomina, convenio);
		
		//List empresasExcluir = new ArrayList();
		List listadoFinal = new ArrayList();

		int idConvenioPrev = 0;
		int rutEmpresaPrev = 0;
		int idConvenioActual;
		int rutEmpresaPrevActual;
		InformeAvisosError inf = null;
		for (int i=0 ; i < listado.size() ; i++) {
			Object[] listaInforme = (Object[])listado.get(i);
			
			idConvenioActual = ((Integer) listaInforme[0]).intValue();
			rutEmpresaPrevActual = ((Integer) listaInforme[1]).intValue();
			
			if(idConvenioPrev != idConvenioActual || rutEmpresaPrev != rutEmpresaPrevActual){
				if(inf!=null)
					listadoFinal.add(inf);
				
				inf = new InformeAvisosError();
				inf.setIdConvenio(idConvenioActual);
				inf.setRutEmpresa(rutEmpresaPrevActual);
				inf.setRazonSocial((String)listaInforme[2]);
				inf.setHabilitada(((Integer) listaInforme[3]));
				inf.setTieneError( ((Integer) listaInforme[4]).intValue() == 0 ? true : false );
				inf.setTieneAviso( ((Integer) listaInforme[4]).intValue() == 1 ? true : false );
				inf.setRutFormateado(Utils.formatRut(inf.getRutEmpresa()));
				
				idConvenioPrev = idConvenioActual;
				rutEmpresaPrev = rutEmpresaPrevActual;
			} else {
				inf.setTieneAviso( ((Integer) listaInforme[4]).intValue() == 1 ? true : false );
			}
			
			if (((Integer) listaInforme[4]).intValue() == 1 ? true : false) {
				inf.setOrigenTablaAviso(Constants.AVISOS_TABLA_CAUSACXP);
				//empresasExcluir.add(inf);
			}
		}
		if(inf!=null)
			listadoFinal.add(inf);

		inf = null;
		idConvenioPrev = 0;
		rutEmpresaPrev = 0;

		//Se buscan los Avisos en CAUSAAVISOCXP. Los avisos no coexisten en ambas tablas
		List listadoAvisos = nominaDao.getEmpresasAvisosbyConvenio(idEmpresa, razonSocial, tipoNomina, convenio);

		for (int i=0 ; i < listadoAvisos.size() ; i++) {
			Object[] listaInforme = (Object[])listadoAvisos.get(i);
			
			idConvenioActual = ((Integer) listaInforme[0]).intValue();
			rutEmpresaPrevActual = ((Integer) listaInforme[1]).intValue();
			
			if(idConvenioPrev != idConvenioActual || rutEmpresaPrev != rutEmpresaPrevActual){
				if(inf!=null)
					listadoFinal.add(inf);
				
				inf = new InformeAvisosError();
				inf.setIdConvenio(idConvenioActual);
				inf.setRutEmpresa(rutEmpresaPrevActual);
				inf.setRazonSocial((String)listaInforme[2]);
				inf.setHabilitada(((Integer) listaInforme[3]));
				inf.setTieneError(false);
				inf.setOrigenTablaAviso(Constants.AVISOS_TABLA_CAUSAAVISOCXP);
				inf.setTieneAviso(true);
				inf.setRutFormateado(Utils.formatRut(inf.getRutEmpresa()));

				idConvenioPrev = idConvenioActual;
				rutEmpresaPrev = rutEmpresaPrevActual;
			}
		}
		if(inf!=null)
			listadoFinal.add(inf);
		
		return listadoFinal;
	}

	//jlandero 13/09/2010
	/**
	 * Muestra la informacion de envio acerca de la nomina
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoNomina
	 * @return 
	 * @throws DaoException
	 */
	public InformacionNomina getInformacionNomina(long idEmpresa, long idConvenio, String tipoNomina) throws DaoException
	{
		List listado = nominaDao.getInformacionNomina(idEmpresa, idConvenio, tipoNomina);
		InformacionNomina inf = new InformacionNomina();
		//String desFormatRut = String.valueOf(Utils.desFormatRut(form.getRutEmpresa().trim()));
		for (int i=0 ; i < listado.size() ; i++){
			Object[] listaInforme = (Object[])listado.get(i);
			inf.setRutEmpresa(((Integer)listaInforme[0]).intValue());
			inf.setFormatRutEmpresa(Utils.formatRut((int)inf.getRutEmpresa()));
			inf.setIdConvenio(((Integer)listaInforme[1]).intValue());
			inf.setFechaEnvio(((Timestamp)listaInforme[2]));
			inf.setFechaAceptada(((Timestamp)listaInforme[3]));
			inf.setTotalTrabajadores(((Integer)listaInforme[4]).intValue());
			inf.setTotalOk(((Integer)listaInforme[5]).intValue());
			inf.setTotalErroneos(((Integer)listaInforme[6]).intValue());
			inf.setRutEncargado(((Integer)listaInforme[7]).intValue());
			inf.setFormatRutEncargado(Utils.formatRut((int)inf.getRutEncargado()));
			inf.setNormalSize(((Integer)listaInforme[8]).intValue());
			inf.setComprimidoSize(((Integer)listaInforme[9]).intValue());		
			inf.setFechaRecibido(((Timestamp)listaInforme[10]));
		}	
		return inf;
	}
	
	/**
	 * Obtiene el comprobante de Pago dado su Código de Barras
	 * 
	 * @param idCodigoBarras
	 * @return
	 * @throws DaoException
	 */
	public int getEstadoComprobante(long idCodigoBarras) throws DaoException {
		ComprobanteVO cmp = (ComprobanteVO) this.comprobanteDao.getComprobante(idCodigoBarras);
		int cmpEstado = 0;
		if (cmp != null)
			cmpEstado = new Integer("" + cmp.getIdEstado()).intValue();
		return cmpEstado;
	}

	/**
	 * Determina si la nómina tiene avisos de Cargas Familiares
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoNomina
	 * @return
	 * @throws DaoException
	 */
	public boolean isNominaConAvisosDeCarga(long idEmpresa, long idConvenio, String tipoNomina) throws DaoException {
		boolean resultado = false;
		int cantAvisos = nominaDao.getCantidadAvisosCargas(idEmpresa, idConvenio, tipoNomina);
		if (cantAvisos > 0)
			resultado = true;
		return resultado;
	}
	/**
	 * Muestra la lista de empresas que tienen declaraciones y no pago
	 * @param idEmpresa
	 * @param razonSocial
	 * @param tipoNomina
	 * @param listaEmpresas
	 * @return
	 * @throws DaoException
	 */
	public List getEmpresasDeclaraNoPago(String idEmpresa, String razonSocial, String tipoNomina, List listaEmpresas, String periodo) throws DaoException
	{
		List listado =nominaDao.getEmpresasDeclaraNoPago(idEmpresa, razonSocial, tipoNomina, listaEmpresas,periodo);
		
		List listEmpresas = new ArrayList();
		InformacionDeclaraNoPago informacionDeclaraNoPago; 
 		for (int i=0 ; i < listado.size() ; i++) {
			Object[] listaInforme = (Object[])listado.get(i);
			
			informacionDeclaraNoPago = new InformacionDeclaraNoPago();
			informacionDeclaraNoPago.setRutEmpresa(((Integer) listaInforme[0]).intValue());
			informacionDeclaraNoPago.setIdConvenio(((Integer) listaInforme[1]).intValue());
			informacionDeclaraNoPago.setRazonSocial((String) listaInforme[2]);
			informacionDeclaraNoPago.setCodigoBarra(((Long) listaInforme[3]).longValue());
			informacionDeclaraNoPago.setPeriodo(periodo);			
			informacionDeclaraNoPago.setRutFormateado(Utils.formatRut(new Long(informacionDeclaraNoPago.getRutEmpresa()).intValue()));
			listEmpresas.add(informacionDeclaraNoPago);
		}
 		return listEmpresas;
 	}
	/**
	 * 08/10/2013
	 * @author gmallea
	 * Borra las nominas de un empresa
	 * 
	 * @param int rutEmpresa
	 * @return
	 * @throws Exception
	 */
	public void borrarNomina(int rutEmpresa,int idConevio) throws Exception{
		
		try{
					List listCodigoBarraEmpresa = this.nominaDao.getCodigoBarraByRutEmpresa(rutEmpresa, idConevio);
					if(listCodigoBarraEmpresa.size() > 0){
						List listNominaEmpresa = new ArrayList();
						listNominaEmpresa.add(new Integer(rutEmpresa));
					
						this.nominaDao.borraNominasEmpresa(listNominaEmpresa, idConevio);
	
						actualizaComprobanteEmpresa(listCodigoBarraEmpresa);
						
						this.nominaDao.borraComprobantesEmpresa(listCodigoBarraEmpresa);
					}
					
		}catch (Exception ex) {
			throw new DaoException("Error en ProcesoMGR.borrarNomina", ex);
		}
	}
	/**
	 * 08/10/2013
	 * Actualiza los comprobantes entregados en una lista de codigos de barra y anula el folio para las empresas
	 * 
	 * @author gmallea
	 * 
	 * @param List listCodigoBarra
	 * @return
	 * @throws DaoException 
	 * @throws Exception
	 */
	public void actualizaComprobanteEmpresa(List listCodigoBarra) throws DaoException
	{
		List cmpsNoPagados = this.comprobanteDao.getComprobantes(listCodigoBarra);
		List listaParams = new ArrayList();
		listaParams.add(new Integer(Constants.PARAM_INT_TES_URL_SISTEMA));
		listaParams.add(new Integer(Constants.PARAM_INT_TES_USER_CONN));
		listaParams.add(new Integer(Constants.PARAM_INT_TES_PASS_CONN));
		listaParams.add(new Integer(Constants.PARAM_LIB_ANULA_FOLIO));
		ParametrosHash paramHash = this.parametrosDAO.getParametrosHash(listaParams);
		
		try{
			for (Iterator it = cmpsNoPagados.iterator(); it.hasNext();)
			{
				ComprobanteVO comprobante = (ComprobanteVO) it.next();
				//log.info("Proceso de Cierre Comprobante:" + comprobante.getFolioTesoreria() + "::" + comprobante.getIdCodigoBarra() + "::");
								try{
									this.anulaFolio(comprobante.getFolioTesoreria(), paramHash);
								}catch (Exception e) {
									//log.error("Error en ComprobanteMgr.actualizaComprobanteEmpresa anulafolio ", e);
								}
								//comprobante.setFolioTesoreria(0);
								//this.comprobanteDao.guardaComprobante(comprobante);
				}
		} catch (Exception ex)
		{
			//log.error("Error en ComprobanteMgr.actualizaComprobanteEmpresa", ex);
			//log.info("No se pudo anular el folio :" + ex);
		}
	}
	/**
	 * Se anula el folio del comprobante
	 * 08/10/2013
	 * @author gmallea
	 * 
	 * @param folio
	 * @throws DaoException
	 * @throws Exception
	 * @throws BusinessException
	 */
	private void anulaFolio(long folio, ParametrosHash paramHash) throws DaoException, Exception
	{
		ProxyAS400 proxyAS400 = new ProxyAS400();
		String result = proxyAS400.anularComprobante(folio, paramHash);
		if (!result.trim().equals(""))
			throw new Exception("problemas al anular folio:" + result.trim());
	}
}