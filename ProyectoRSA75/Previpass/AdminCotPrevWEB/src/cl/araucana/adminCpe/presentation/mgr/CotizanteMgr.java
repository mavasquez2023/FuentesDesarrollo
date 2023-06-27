package cl.araucana.adminCpe.presentation.mgr;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.CotizanteDAO;
import cl.araucana.adminCpe.hibernate.dao.EmpresaDAO;
import cl.araucana.adminCpe.hibernate.dao.EntidadesDAO;
import cl.araucana.adminCpe.hibernate.dao.ParametroDAO;
import cl.araucana.adminCpe.hibernate.dao.PersonaDAO;
import cl.araucana.adminCpe.presentation.struts.javaBeans.CorreoSISEmpresa;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAsFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoGeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizante;
import cl.araucana.cp.empresa.correo.sis.data.MailSIS;
import cl.araucana.cp.empresa.correo.sis.mail.EnviarCorreoSIS;
import cl.araucana.validadorSis.bpro.ValidadorSisBusiness;
import cl.araucana.validadorSis.model.bo.dvo.ResumenMensualAfpDVO;
import cl.araucana.wsmail.test.ConsultaMail;
import cl.araucana.wsmail.test.ConsultaMailServiceLocator;

/*
 * @(#) CotizanteMgr.java 1.6 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.6
 */
public class CotizanteMgr
{
	private static Logger log = Logger.getLogger(CotizanteMgr.class);
	private CotizanteDAO cotizanteDao;
	private EntidadesDAO entidadesDao;

	public CotizanteMgr(Session session)
	{
		this.cotizanteDao = new CotizanteDAO(session);
		this.entidadesDao = new EntidadesDAO(session);
	}

	/**
	 * lista cotizantes pendiente
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param filtro
	 * @return
	 * @throws DaoException
	 */
	public List getListaCotizPend(int idEmpresa, int idConvenio, char tipoProceso) throws DaoException
	{
		return this.cotizanteDao.getListaCotizPend(idEmpresa, idConvenio, tipoProceso);
	}

	/**
	 * cotizante
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param rutTrabajador
	 * @return
	 */
	public CotizanteVO getCotizante(int idEmpresa, int idConvenio, char tipoProceso, int rutTrabajador)
	{
		return this.cotizanteDao.getCotizante(idEmpresa, idConvenio, tipoProceso, rutTrabajador);
	}

	/**
	 * cotizante pendiente
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param getCotizPend
	 * @return
	 * @throws DaoException
	 */
	public CotizacionPendienteVO getCotizPend(int idEmpresa, int idConvenio, char tipoProceso, int getCotizPend) throws DaoException
	{
		return this.cotizanteDao.getCotizPend(idEmpresa, idConvenio, tipoProceso, getCotizPend);
	}

	/**
	 * lista apellido compuesto
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getApellCompuestos() throws DaoException
	{
		return this.cotizanteDao.getApellCompuestos();
	}

	/**
	 * hashmap tipo causa
	 * 
	 * @return
	 * @throws DaoException
	 */
	public HashMap getTiposCausa() throws DaoException
	{
		return this.cotizanteDao.getTiposCausa();
	}

	/**
	 * cotizante id reales
	 * 
	 * @param tipoProceso
	 * @param idMapaCod
	 * @param cot
	 * @return
	 * @throws DaoException
	 */
	public Cotizante setIdsReales(char tipoProceso, int idMapaCod, Cotizante cot) throws DaoException
	{
		if (tipoProceso != 'D')
		{
			MapeoVO mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoSaludVO.class, cot.getIdEntSalud());
			if (mapeo != null)
				cot.setIdEntSaludReal(mapeo.getId());
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoPensionVO.class, cot.getIdEntPension());
			if (mapeo != null)
				cot.setIdEntPensionReal(mapeo.getId());
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoPensionVO.class, cot.getIdEntidadAFPV());
			if (mapeo != null)
				cot.setIdEntidadAFPVReal(mapeo.getId());
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoPensionVO.class, cot.getIdEntAFC());
			if (mapeo != null)
				cot.setIdEntAFCReal(mapeo.getId());
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoAsFamVO.class, cot.getIdTramo());
			if (mapeo != null)
			{
				cot.setIdTramoReal(mapeo.getId());
				cot.setIdTramoRealINP(mapeo.getId());
			}
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoGeneroVO.class, cot.getIdGenero());
			if (mapeo != null)
				cot.setIdGeneroReal("" + mapeo.getId());
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoAPVVO.class, cot.getIdEntidadAPVC());
			if (mapeo != null)
				cot.setIdEntidadAPVCReal(mapeo.getId());
		} else
		{
			MapeoVO mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoAPVVO.class, cot.getIdEntidadAPVC());
			if (mapeo != null)
				cot.setIdEntidadAPVCReal(mapeo.getId());
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoGeneroVO.class, cot.getIdGenero());
			if (mapeo != null)
				cot.setIdGeneroReal("" + mapeo.getId());
		}
		return cot;
	}

	/**
	 * lista apv
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param idCotizante
	 * @return
	 * @throws DaoException
	 */
	public List getApvs(int idEmpresa, int idConvenio, int idCotizante) throws DaoException
	{
		return this.cotizanteDao.getApvs(idEmpresa, idConvenio, idCotizante);
	}

	/**
	 * lista causas
	 * 
	 * @param tipoProceso
	 * @param cotPend
	 * @return
	 * @throws DaoException
	 */
	public List getCausas(char tipoProceso, CotizacionPendienteVO cotPend) throws DaoException
	{
		return this.cotizanteDao.getCausas(tipoProceso, cotPend);
	}

	/**
	 * lista cotizantes nomina
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
	public List getCotizantesNomina(int idEmpresa, int idConvenio, char tipoProceso) throws DaoException
	{
		return this.cotizanteDao.getCotizantesNomina(idEmpresa, idConvenio, tipoProceso);
	}

	/**
	 * cotizante nombre reales
	 * 
	 * @param tipoProceso
	 * @param setGenero
	 * @param idMapaCod
	 * @param cot
	 * @return
	 * @throws DaoException
	 */
	public Cotizante setNombresReales(char tipoProceso, boolean setGenero, int idMapaCod, Cotizante cot) throws DaoException
	{
		EntidadVO entidad = null;
		if (tipoProceso != 'D')
		{
			MapeoVO mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoSaludVO.class, cot.getIdEntSalud());
			if (mapeo != null)
			{
				entidad = this.getEntSalud(mapeo.getId());
				cot.setIdEntSalud(entidad.getNombre().trim());
			}
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoPensionVO.class, cot.getIdEntPension());
			if (mapeo != null)
			{
				entidad = this.getEntFondoPension(mapeo.getId());
				cot.setIdEntPension(entidad.getNombre().trim());
			}
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoPensionVO.class, cot.getIdEntidadAFPV());
			if (mapeo != null)
			{
				entidad = this.getEntFondoPension(mapeo.getId());
				cot.setIdEntidadAFPV(entidad.getNombre().trim());
			}
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoPensionVO.class, cot.getIdEntAFC());
			if (mapeo != null)
			{
				entidad = this.getEntFondoPension(mapeo.getId());
				cot.setIdEntAFC(entidad.getNombre().trim());
			}
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoAsFamVO.class, cot.getIdTramo());
			if (mapeo != null)
			{
				entidad = this.getAsigFam(mapeo.getId());
				cot.setIdTramo(entidad.getNombre().trim());
			}
			if (setGenero)
			{
				mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoGeneroVO.class, cot.getIdGenero());
				if (mapeo != null)
				{
					entidad = this.getGenero(mapeo.getId());
					cot.setIdGenero(entidad.getNombre().trim());
				}
			}
		} else
		{
			MapeoVO mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoAPVVO.class, cot.getIdEntidadAPVC());
			if (mapeo != null)
			{
				entidad = this.getEntApv(mapeo.getId());
				cot.setIdEntidadAPVC(entidad.getNombre().trim());
			}

			if (setGenero)
			{
				mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoGeneroVO.class, cot.getIdGenero());
				if (mapeo != null)
				{
					entidad = this.getGenero(mapeo.getId());
					cot.setIdGenero(entidad.getNombre().trim());
				}
			}
		}
		return cot;
	}

	/**
	 * genero
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public GeneroVO getGenero(int id) throws DaoException
	{
		return this.entidadesDao.getGenero(id);
	}

	/**
	 * asignacion familiar
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public AsigFamVO getAsigFam(int id) throws DaoException
	{
		return this.entidadesDao.getAsigFam(id);
	}

	/**
	 * entidad salud
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public EntidadSaludVO getEntSalud(int id) throws DaoException
	{
		return this.entidadesDao.getEntSalud(id);
	}

	/**
	 * entidad pension
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public EntidadPensionVO getEntFondoPension(int id) throws DaoException
	{
		return this.entidadesDao.getEntFondoPension(id);
	}

	/**
	 * entidad apv
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public EntidadApvVO getEntApv(int id) throws DaoException
	{
		return this.entidadesDao.getEntApv(id);
	}

	/**
	 * Retorna un HashMap con tres listas adentro: ['avisos', descripcion de avisos asociados a la cotizacion pendiente] ['errores', descripcion de Errores asociados a la cotizacion pendiente]
	 * ['descripcionError', lista de descripciones de todos avisos y errores asociados a la cotizacion pendiente]
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param causas
	 * @return
	 * @throws DaoException
	 */
	public HashMap getNivelErrorTipoCausa(List causas) throws DaoException
	{
		HashMap tiposCausa = this.cotizanteDao.getTiposCausa();
		HashMap result = new HashMap();
		HashMap descripcionError = new HashMap();
		List errores = new ArrayList();
		List avisos = new ArrayList();
		for (Iterator it = causas.iterator(); it.hasNext();)
		{
			Integer idCausa = (Integer) it.next();
			if (tiposCausa.containsKey(idCausa))
			{
				TipoCausaVO tc = (TipoCausaVO) tiposCausa.get(idCausa);
				if (tc.getError() == Constants.NIVEL_VAL_ERROR)
				{
					descripcionError.put(idCausa, "<span class=\"mensaje_error\">" + tc.getDescripcion().trim() + "</span>");
					errores.add(tc.getDescripcion().trim());
				} else if (tc.getError() == Constants.NIVEL_VAL_AVISO)
				{
					descripcionError.put(idCausa, "<span class=\"mensaje_aviso\">" + tc.getDescripcion().trim() + "</span>");
					avisos.add(tc.getDescripcion().trim());
				}
			}
		}
		result.put("errores", errores);
		result.put("avisos", avisos);
		result.put("descripcionError", descripcionError);
		return result;
	}

	public List getCausasAvisos(char tipoProceso, int idEmpresa, int idConvenio, int idCotizante) throws DaoException
	{
		return this.cotizanteDao.getCausasAvisos(tipoProceso, idEmpresa, idConvenio, idCotizante);
	}

	public HashMap getTrabPaginados(int pagina, int primerReg, int rutEmpresa, int idConvenio, char tipoProceso, CotizanteVO cotizante) throws DaoException
	{
		HashMap result = new HashMap();
		List pend = new ArrayList();
		List avisos = new ArrayList();
		List aprobados = new ArrayList();

		//valores con filtro aplicado
		int numAprobados = this.cotizanteDao.getNumAprobados(rutEmpresa, idConvenio, tipoProceso, cotizante);
		int numAvisos = this.cotizanteDao.getNumAvisos(rutEmpresa, idConvenio, tipoProceso, cotizante);
		int numPendientes = this.cotizanteDao.getNumPendientes(rutEmpresa, idConvenio, tipoProceso, cotizante);
		int count = 0;
		numAprobados -= numAvisos;
		log.info("paginando: pag solicitada:" + pagina + ":primerRegistro:" + primerReg + ":nPendientes:" + numPendientes + ":nAviso:" + numAvisos + ":nAprobados:" + numAprobados + "::");

		//trae todos los pendientes, despues se deben parsear, ordenar y paginar
		if (numPendientes > 0 && primerReg < numPendientes)
			pend = this.cotizanteDao.getListaCotizPend(rutEmpresa, idConvenio, tipoProceso, cotizante);
		count = Math.min(numPendientes, primerReg + Constants.NUM_REG_PAG_ADMIN) - primerReg; //suma aporte pendientes a result
		if (count < 0)	count = 0;
		log.info("add n pend (todos):" + numPendientes + ":aporte pendientes a result:" + count + "::");
		//avisos
		if (numAvisos > 0 && count < Constants.NUM_REG_PAG_ADMIN)
		{
			int posIni = (primerReg <= numPendientes ? 0 : primerReg - numPendientes);
			avisos = this.cotizanteDao.getListaCotizantesAvisos(posIni, Constants.NUM_REG_PAG_ADMIN - count, rutEmpresa, idConvenio, tipoProceso, cotizante);
		}
		log.info("add n avisos:" + avisos.size() + "::");
		count += avisos.size();
		//aprobados
		if (numAprobados > 0 && count < Constants.NUM_REG_PAG_ADMIN)
		{
			int posIni = (primerReg <= numPendientes + numAvisos ? 0 : primerReg - (numPendientes + numAvisos));
			aprobados = this.cotizanteDao.getListaCotizantes(posIni, Constants.NUM_REG_PAG_ADMIN - count, rutEmpresa, idConvenio, tipoProceso, cotizante);
		}
		log.info("add n aprobados:" + aprobados.size() + "::");

		result.put("pendientes", pend);
		result.put("avisos", avisos);
		result.put("aprobados", aprobados);
		int suma = numAprobados + numAvisos + numPendientes;
		result.put("nPaginas", new Integer(suma % Constants.NUM_REG_PAG_ADMIN == 0 ? suma / Constants.NUM_REG_PAG_ADMIN : (int) Math.round((suma / Constants.NUM_REG_PAG_ADMIN) + 0.5)));
		return result;
	}

	public HashMap getApvsHash(int rutEmpresa, int idConvenio) throws DaoException
	{
		HashMap result = new HashMap();
		List lista = this.cotizanteDao.getApvs(rutEmpresa, idConvenio, 0);
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			ApvVO apv = (ApvVO)it.next();
			List listaTmp = new ArrayList();
			if (result.containsKey("" + apv.getIdCotizante()))
				listaTmp = (List)result.get("" + apv.getIdCotizante());
			listaTmp.add(apv);
			result.put("" + apv.getIdCotizante(), listaTmp);
		}
		return result;
	}
	
	
	public byte[] getInconsistenciasSIS(String periodo, String codAfp, boolean flagMailEmp, Session session, String afpName) throws Exception
	{
		log.info("--> Entramos a getInconsistenciasSIS ");
		ValidadorSisBusiness validadorSisDelegate = new ValidadorSisBusiness();
		
		ResumenMensualAfpDVO[] resumenMensualAfpDVOs   = validadorSisDelegate.getContingenciaArray(codAfp ,periodo);
		
		log.info("--> Cantidad de registros validador SIS: "+ resumenMensualAfpDVOs.length);
		
		if(resumenMensualAfpDVOs.length > 0 ){
			if(flagMailEmp){//envia mail
			 HashMap hasInconsistenciasOrdenadas =	this.ordenarByEmpresa(resumenMensualAfpDVOs, session);
			 
			 this.generaArchivoCSVBYEmpresa(hasInconsistenciasOrdenadas,session,afpName);
			 
			 return new byte[1]  ;
			}else{//genera archivo
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(baos));
				
				this.generaArchivoCSV(bufferedWriter, resumenMensualAfpDVOs,session);
				bufferedWriter.close();
				return baos.toByteArray();
			}
		}
		
		return null;
		
		
		
		
	}
	public HashMap ordenarByEmpresa(ResumenMensualAfpDVO[] resumenMensualAfpDVOs, Session session) throws DaoException{
		
		HashMap hsEmIncosistentes = new HashMap();
		CorreoSISEmpresa correoSISEmpresa = null;;
		ResumenMensualAfpDVO mensualAfpDVO = null;
		List listCotizantes = null;
		
		for (int i=0 ; i < resumenMensualAfpDVOs.length ; i++) {
			mensualAfpDVO =  resumenMensualAfpDVOs[i];
			correoSISEmpresa = new CorreoSISEmpresa();
			
			correoSISEmpresa.setPeriodo(mensualAfpDVO.getPeriodo());
			correoSISEmpresa.setRutEmpresa(""+mensualAfpDVO.getRutEmpresa());
			correoSISEmpresa.setDvEmpresa(""+mensualAfpDVO.getDvEmpresa());
			correoSISEmpresa.setRazonSocial(mensualAfpDVO.getRazonSocial());
			correoSISEmpresa.setRutTrabajador(""+mensualAfpDVO.getRutCotizante());
			correoSISEmpresa.setDvTrabajador(""+mensualAfpDVO.getDvCotizante());
			correoSISEmpresa.setNombreTrabajador(mensualAfpDVO.getNombre().trim() +" "+ mensualAfpDVO.getApellido());
			correoSISEmpresa.setCodAFP(mensualAfpDVO.getCodigoAfp());
			correoSISEmpresa.setMontoCancelado(""+mensualAfpDVO.getMontoSisPagado());
			
			if(hsEmIncosistentes.get(""+correoSISEmpresa.getRutEmpresa()) == null){
				listCotizantes = new ArrayList();
				listCotizantes.add(correoSISEmpresa);
				hsEmIncosistentes.put(""+correoSISEmpresa.getRutEmpresa(), listCotizantes);
			}else{
				 listCotizantes =(List) hsEmIncosistentes.get(""+correoSISEmpresa.getRutEmpresa());
				 listCotizantes.add(correoSISEmpresa);
				 hsEmIncosistentes.put(""+correoSISEmpresa.getRutEmpresa(), listCotizantes);
			}	
		}
		return hsEmIncosistentes;
	}
	
	/**
	 * Retorna un BufferedWriter con los datos del archivo<p>
	 * 
	 * Registro de Versiones:<ul>
	 * <li>17/06/2014 [gmallea - schema ltda.]: Version Inicial</li>
	 * </ul><p>  
	 * 
	 * @param  bufferedWriter
	 * @param  configuracionUsuarioBO
	 * @param  filas
	 * @param  separador
	 * @throws Exception 
	 */
	private void generaArchivoCSV(BufferedWriter bufferedWriter, ResumenMensualAfpDVO[] resumenMensualAfpDVOs, Session session) throws Exception {
		
		log.info("--> Entramos a generaArchivoCSV ");
		String separador = ";";
		EmpresaDAO empresaDAO = new EmpresaDAO(session);
		PersonaDAO personaDAO = new PersonaDAO(session);
		PersonaVO  personaVO = null;
		List listSucursal = null;
		SucursalVO sucursalVO = null;
		EmpresaVO empresaVO = null;
		
		ResumenMensualAfpDVO mensualAfpDVO = null;
		CorreoSISEmpresa correoSISEmpresa = null;
		
		for (int i=0 ; i < resumenMensualAfpDVOs.length ; i++) {
			mensualAfpDVO =  resumenMensualAfpDVOs[i];
			correoSISEmpresa = new CorreoSISEmpresa();
			String correoEmpresa = "";
			String fonoEmpresa ="";
			
			correoSISEmpresa.setPeriodo(mensualAfpDVO.getPeriodo());
			correoSISEmpresa.setRutEmpresa(""+mensualAfpDVO.getRutEmpresa());
			correoSISEmpresa.setDvEmpresa(""+mensualAfpDVO.getDvEmpresa());
			correoSISEmpresa.setRazonSocial(mensualAfpDVO.getRazonSocial());
			correoSISEmpresa.setNombreTrabajador(mensualAfpDVO.getNombre().trim() +" " +mensualAfpDVO.getApellido() );
			
			try{
				log.info("--> Invocamos a WS ConsultaMail rut empresa: "+ correoSISEmpresa.getRutEmpresa()+"-"+correoSISEmpresa.getDvEmpresa());
				//invocamos a WS Marco tiene que mandar la ruta
				ConsultaMailServiceLocator consultaMailServiceLocator = new ConsultaMailServiceLocator();
				log.info("--> URL WS " + consultaMailServiceLocator.getConsultaMailAddress());

				ConsultaMail consultaMail = consultaMailServiceLocator.getConsultaMail();
				
				correoEmpresa = consultaMail.getMailAdmin(correoSISEmpresa.getRutEmpresa()+"-"+correoSISEmpresa.getDvEmpresa());
				
				log.info("--> Respuesta WS ConsultaMail: " + correoEmpresa);
				}catch(Exception ex){
					log.info("--***> Problemas con el Web Services : " + ex.getMessage());				
					log.error("--***> Problemas con el Web Services : " + ex.getMessage());
			}
				
			//obtenemos el correo y telefono de la tabla persona por el rut empresa
			personaVO =  personaDAO.getPersona((int)mensualAfpDVO.getRutEmpresa());
			if(personaVO != null){
				if(correoEmpresa.equals(""))
					correoEmpresa = personaVO.getEmail().trim();
					log.info("--> Correo Empresa encontrado en Persona : " +correoEmpresa+ " por rutEmpresa: "+mensualAfpDVO.getRutEmpresa());
				if(fonoEmpresa.equals(""))
					fonoEmpresa = personaVO.getTelefono().trim();
					log.info("--> Fono Empresa encontrado en Persona : " +correoEmpresa+ " por rutEmpresa: "+mensualAfpDVO.getRutEmpresa());
			}
			
			//obtenemos el correo de la tabla persona por el rut administrador que lo sacamos de la tabla empresa
			empresaVO = new EmpresaVO();
			if(correoEmpresa.trim().equals("") || fonoEmpresa.trim().equals("")){
				empresaVO = empresaDAO.getEmpresaSIS((int)mensualAfpDVO.getRutEmpresa());
				if(empresaVO != null){
					personaVO =  personaDAO.getPersona(empresaVO.getIdAdmin());
					if(personaVO != null){
							if(correoEmpresa.equals("")){
								correoEmpresa = personaVO.getEmail().trim();
								log.info("--> Correo Empresa encontrado en Persona : " +correoEmpresa+ " por idAdmin: "+empresaVO.getIdAdmin());
							}
							if(fonoEmpresa.equals("")){
								fonoEmpresa = personaVO.getTelefono().trim();
								log.info("--> Fono Empresa encontrado en Persona : " +correoEmpresa+ " por idAdmin: "+empresaVO.getIdAdmin());
							}
					}
				}
			}
			//obtenemos el correo de la tabla sucursal
			if(correoEmpresa.trim().equals("") || fonoEmpresa.trim().equals("")){	
				listSucursal =  empresaDAO.getSucursales((int)mensualAfpDVO.getRutEmpresa());
				for(Iterator its = listSucursal.iterator() ;its.hasNext();){
					sucursalVO = (SucursalVO) its.next();
						if(correoEmpresa.equals("")){
							correoEmpresa = sucursalVO.getEmail().trim();
							log.info("--> Correo Empresa encontrado en sucursal : " +correoEmpresa+ " por rutEmpresa: "+mensualAfpDVO.getRutEmpresa());
						}
						if(fonoEmpresa.equals("")){
							fonoEmpresa = sucursalVO.getTelefono().trim();
							log.info("--> Fono Empresa encontrado en sucursal : " +correoEmpresa+ " por rutEmpresa: "+mensualAfpDVO.getRutEmpresa());
						}
							break;
						
				}
			}
			
			if(correoEmpresa.trim().equals(""))
				correoEmpresa="N/A";
			
			if(fonoEmpresa.trim().equals(""))
				fonoEmpresa="N/A";
			
			
			correoSISEmpresa.setRutTrabajador(""+mensualAfpDVO.getRutCotizante());
			correoSISEmpresa.setDvTrabajador(""+mensualAfpDVO.getDvCotizante());
			correoSISEmpresa.setCodAFP(mensualAfpDVO.getCodigoAfp());
			correoSISEmpresa.setMontoCancelado(""+mensualAfpDVO.getMontoSisPagado());
		
			bufferedWriter.write(correoSISEmpresa.getPeriodo().trim() + separador + correoSISEmpresa.getRutEmpresa().trim() + separador + 
					correoSISEmpresa.getDvEmpresa().trim()+ separador + correoSISEmpresa.getRazonSocial().trim() + separador + 
					correoSISEmpresa.getRutTrabajador().trim() + separador + correoSISEmpresa.getDvTrabajador().trim()+ separador +
					correoSISEmpresa.getNombreTrabajador().trim() + separador + correoSISEmpresa.getCodAFP().trim() + separador + correoSISEmpresa.getMontoCancelado().trim()+
					separador + correoEmpresa.trim()+ separador +fonoEmpresa.trim());	
		
			bufferedWriter.write("\n");	
		}
	}
	
	/**
	 * Retorna un BufferedWriter con los datos del archivo<p>
	 * 
	 * Registro de Versiones:<ul>
	 * <li>17/06/2014 [gmallea - schema ltda.]: Version Inicial</li>
	 * </ul><p>  
	 * 
	 * @param  bufferedWriter
	 * @param  configuracionUsuarioBO
	 * @param  filas
	 * @param  separador
	 * @throws Exception 
	 */
	private void generaArchivoCSVBYEmpresa(HashMap hasIncosistencias,Session session,String afpName) throws Exception {
		
		log.info("--> Entramos a generaArchivoCSVBYEmpresa");
		
		ByteArrayOutputStream baos = null;
		BufferedWriter bufferedWriter = null;
		
		String correoEmpresa = "";
		PersonaDAO personaDAO = new PersonaDAO(session);
		EmpresaDAO empresaDAO = new EmpresaDAO(session);
		CorreoSISEmpresa correoSISEmpresa = null;
		String empresa= "";
		Map.Entry e = null;
		List inconsistenciasList = null;
		PersonaVO personaVO = null;
		List listSucursal = null;
		SucursalVO sucursalVO = null;
		ParametroDAO  parametroDAO = new ParametroDAO(session);
		String separador = ";";
		Iterator it = hasIncosistencias.entrySet().iterator();
		MailSIS mail = null;
		EmpresaVO empresaVO = null;
		
		List listParam = new ArrayList();
		listParam.add(new Integer(Constants.PARAM_MAIL_HOST_LOCAL));
		listParam.add(new Integer(Constants.PARAM_MAIL_FROM));
		listParam.add(new Integer(Constants.PARAM_MAIL_USER));
		listParam.add(new Integer(Constants.PARAM_MAIL_PASS));
		listParam.add(new Integer(Constants.PARAM_MAIL_HOST_TO));
		listParam.add(new Integer(Constants.PARAM_MAIL_PORT));
		
		ParametrosHash parametros = parametroDAO.getParametrosHash(listParam);
		
		while (it.hasNext()) {
			e = (Map.Entry)it.next();
			empresa =(String) e.getKey();
			inconsistenciasList = (List)e.getValue();
			
			try{
				log.info("--> Invocamos a WS ConsultaMail rut empresa: " +empresa+"-"+Utils.generaDV(new Integer(empresa).intValue()));
				//invocamos a WS Marco tiene que mandar la ruta
				ConsultaMailServiceLocator consultaMailServiceLocator = new ConsultaMailServiceLocator();
				
				ConsultaMail consultaMail = consultaMailServiceLocator.getConsultaMail();
				
				correoEmpresa = consultaMail.getMailAdmin(empresa+"-"+Utils.generaDV(new Integer(empresa).intValue()));
				
				log.info("--> Respuesta WS ConsultaMail: " + correoEmpresa);
			}catch(Exception ex){
				log.info("--**> Problemas con el Web Services : " + ex.getMessage());				
			}
			
			
			//obtenemos el correo de la tabla persona por el rut empresa
			personaVO =  personaDAO.getPersona(empresa);
			if(personaVO != null){
					if(correoEmpresa.equals("")){
						correoEmpresa = personaVO.getEmail().trim();
						log.info("--> Correo Empresa encontrado en Persona : " +correoEmpresa+ " por rutEmpresa: "+empresa);
					}
				}
			
			//obtenemos el correo de la tabla persona por el rut administrador que lo sacamos de la tabla empresa
			empresaVO = new EmpresaVO();
			if(correoEmpresa.trim().equals("")){
				empresaVO = empresaDAO.getEmpresaSIS(new Integer(empresa).intValue());
				if(empresaVO != null){
					personaVO =  personaDAO.getPersona(empresaVO.getIdAdmin());
					if(personaVO != null){
							if(correoEmpresa.equals("")){
								correoEmpresa = personaVO.getEmail().trim();
								log.info("--> Correo Empresa encontrado en Persona : " +correoEmpresa+ " por idAdmin: "+empresaVO.getIdAdmin());
							}
					}
				}
			}
			
			//obtenemos el correo de la tabla sucursal
			if(correoEmpresa.equals("")){	
				listSucursal =  empresaDAO.getSucursales(empresa);
				for(Iterator its = listSucursal.iterator() ;its.hasNext();){
					sucursalVO = (SucursalVO) its.next();
					if(!sucursalVO.getEmail().trim().equals("")){
						correoEmpresa =sucursalVO.getEmail().trim();
						log.info("--> Correo Empresa encontrado en sucursal : " +correoEmpresa);
						break;
					}
				}
			}
			
			if(Utils.checkEmail(correoEmpresa)){
				baos = new ByteArrayOutputStream();
				bufferedWriter = new BufferedWriter(new OutputStreamWriter(baos));
			
						
				for (int i=0 ; i < inconsistenciasList.size() ; i++) {
					correoSISEmpresa = (CorreoSISEmpresa) inconsistenciasList.get(i);
					
					
					bufferedWriter.write(correoSISEmpresa.getPeriodo().trim() + separador + correoSISEmpresa.getRutEmpresa().trim() + separador + 
							correoSISEmpresa.getDvEmpresa().trim()+ separador + correoSISEmpresa.getRazonSocial().trim() + separador + 
							correoSISEmpresa.getRutTrabajador().trim() + separador + correoSISEmpresa.getDvTrabajador().trim()+ separador +
							correoSISEmpresa.getNombreTrabajador().trim() + separador + correoSISEmpresa.getCodAFP().trim() + separador + correoSISEmpresa.getMontoCancelado().trim());	
					bufferedWriter.write("\n");				
				}
				bufferedWriter.close();
				byte[] csv = baos.toByteArray();			
				
				log.info("--> Inicio enviar Mails");
				
	
					mail = new MailSIS( Integer.parseInt((String)parametros.get("" + Constants.PARAM_MAIL_PORT)),
						correoEmpresa,
						(String)parametros.get("" + Constants.PARAM_MAIL_HOST_TO),
						(String)parametros.get("" + Constants.PARAM_MAIL_FROM),
						(String)parametros.get("" + Constants.PARAM_MAIL_USER),
						(String)parametros.get("" + Constants.PARAM_MAIL_PASS),
						(String)parametros.get("" + Constants.PARAM_MAIL_HOST_LOCAL),
						 csv,
						 empresa,
						 correoSISEmpresa.getRazonSocial(),
						 correoSISEmpresa.getPeriodo(),
						 afpName);
		
					EnviarCorreoSIS.enviar(mail);
			}else{
				log.info("--***> Correo incorrecto : " +correoEmpresa + "no se pudo enviar el Mail rut empresa : " +empresa );
			}
			log.info("--> Fin enviar Mails");	
		}
	}
}
