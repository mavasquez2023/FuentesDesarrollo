package cl.araucana.adminCpe.presentation.mgr;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.ConceptoDAO;
import cl.araucana.adminCpe.hibernate.dao.ConvenioDAO;
import cl.araucana.adminCpe.hibernate.dao.EmpresaDAO;
import cl.araucana.adminCpe.hibernate.dao.MapeosDAO;
import cl.araucana.adminCpe.hibernate.dao.NominaDAO;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ActividadEconomicaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaCodigoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAsFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoGeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RepresentanteLegalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMovimientoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) ConvenioMgr.java 1.22 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.22
 */
public class EmpresaMgr
{
	private static Logger logger = Logger.getLogger(EmpresaMgr.class);
	
	private EmpresaDAO empresaDao;
	private ConvenioDAO convenioDao;
	private MapeosDAO mapeosDao;
	private NominaDAO nominaDao;
	private ConceptoDAO conceptoDao;
	
	
	private Map tiposNomina;

	public EmpresaMgr(Session session)
	{
		this.empresaDao = new EmpresaDAO(session);
		this.convenioDao = new ConvenioDAO(session);
		this.mapeosDao = new MapeosDAO(session);
		this.nominaDao = new NominaDAO(session);
		this.conceptoDao = new ConceptoDAO(session);
	}

	/**
	 * enpresa
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresa(int rutEmpresa) throws DaoException
	{
		return this.empresaDao.getEmpresa(rutEmpresa);
	}
	/**
	 * empresa despliegue
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresaDespliegue(int rutEmpresa) throws DaoException
	{
		return this.empresaDao.getEmpresaDespliegue(rutEmpresa);
	}	
	/**
	 * empresa
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresaGet(int rutEmpresa) throws DaoException {
		return this.empresaDao.getEmpresaGet(rutEmpresa);
	}

	/**
	 * lista sucursales
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getSucursales(int rutEmpresa) throws DaoException {
		return this.empresaDao.getSucursales(rutEmpresa);
	}
	/**
	 * lista sucursales
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getSucursales(String rutEmpresa) throws DaoException
	{
		return this.empresaDao.getSucursales(rutEmpresa);
	}

	/**
	 * hashmap sucursales
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public HashMap getSucursalesHash(String rutEmpresa) throws DaoException
	{
		return this.empresaDao.getSucursalesHash(rutEmpresa);
	}

	/**
	 * empresa casa matriz
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresaCasaMatriz(int rutEmpresa) throws DaoException 
	{
		return this.empresaDao.getEmpresaCasaMatriz(rutEmpresa);
	}
	/**
	 * representante legal
	 * @param idRepLegal
	 * @return
	 * @throws DaoException
	 */
	public RepresentanteLegalVO getRepresentanteLegal(int idRepLegal) throws DaoException 
	{
		return this.empresaDao.getRepresentanteLegal(idRepLegal);
	}
	/**
	 * administrador
	 * @param idAdmin
	 * @return
	 * @throws DaoException
	 */
	public AdministradorVO getAdministrador(int idAdmin) throws DaoException 
	{
		return this.empresaDao.getAdministrador(idAdmin);
	}

	/**
	 * lista actividades economicas
	 * @return
	 * @throws DaoException
	 */
	public List getActividadesEconomicas() throws DaoException 
	{
		return this.empresaDao.getActividadesEconomicas();
	}
	/**
	 * map mapa tipod e nominas
	 * @return
	 * @throws DaoException
	 */
	public Map mapaTiposNominas() throws DaoException 
	{

		Map mapa = new HashMap();
		TipoNominaVO tipoNomina;
		for (Iterator it = this.nominaDao.getTiposNominas().iterator(); it.hasNext();) 
		{
			tipoNomina = (TipoNominaVO) it.next();
			mapa.put(tipoNomina.getIdTipoNomina(), tipoNomina);
		}
		return mapa; 
	}

	/**
	 * crear empresa
	 * @param empresa
	 * @param personaRepLegal
	 * @param personaAdministrador
	 * @param sucursal
	 * @param convenio
	 * @param opcProc
	 * @throws DaoException
	 */
	public void crearEmpresa(EmpresaVO empresa, PersonaVO personaRepLegal, PersonaVO personaAdministrador, SucursalVO sucursal, ConvenioVO convenio, OpcionProcVO opcProc) throws DaoException 
	{
		java.util.Date hoy = new java.util.Date();
		this.tiposNomina = mapaTiposNominas();

		//Guarda el representante legal y admin
		this.empresaDao.guardaRepresentanteLegal(personaRepLegal);
		this.empresaDao.guardaAdministrador(personaAdministrador);

		//Guarda la empresa
		EmpresaVO empresaNew = new EmpresaVO();
		empresaNew.setIdEmpresa(empresa.getIdEmpresa());
		empresaNew.setPrivada(empresa.getPrivada());
		empresaNew.setRazonSocial(empresa.getRazonSocial());
		empresaNew.setVigenciaRepLegal(empresa.getVigenciaRepLegal());
		empresaNew.setIdCasaMatriz(sucursal.getIdSucursal());
		empresaNew.setHabilitada(empresa.getHabilitada());
		empresaNew.setIdAdmin(empresa.getIdAdmin());
		empresaNew.setIdRepLegal(empresa.getIdRepLegal());
		empresaNew.setIniciacion(new Date(hoy.getTime()));
		empresaNew.setTipo(Constants.TIPO_EMPRESA);
		empresaNew.setTipoPago(empresa.getTipoPago());
		this.empresaDao.guardaEmpresa(empresaNew);
		
		//Guarda la sucursal casa matriz
		this.empresaDao.saveSucursal(sucursal);

		GrupoConvenioVO grupoNew = null;
		Integer keyOpcionProc = null;
		if (convenio.getIdGrupoConvenio() > 0) //usara grupo de convenios existente
		{
			grupoNew = this.convenioDao.getGrupoConvenio(convenio.getIdGrupoConvenio());
			if(grupoNew == null)
				 throw new DaoException("ERROR_GRUPO_CONVENIO"); 
			keyOpcionProc = new Integer(grupoNew.getIdOpcion());
		} else //copia grupo convenios por defecto
		{		
			//Guarda el mapa de codigos a ser utilizado por el grupo de convenios
			//y todos los codigos para todas las entidades
			MapaCodigoVO mapaCod = new MapaCodigoVO();
			Integer keyMapaCod;
			mapaCod.setIdMapaCodigo(Constants.ID_UNSAVED_MAPACOD);
			mapaCod.setNumBloqueos(Constants.NUM_BLOQUEOS_DEFAULT);
			mapaCod.setDescripcion("Mapa POR DEFECTO EMP.: " + empresaNew.getIdEmpresa());
			keyMapaCod = this.mapeosDao.saveMapaCodigo(mapaCod);
			//this.crearMapaCodDefault(keyMapaCod.intValue()); ESTA VA!!!
			this.crearMapaCodDefault(keyMapaCod.intValue(), Constants.ID_GRUPO_CONVENIO_DEFAULT);
			
			//Guarda una nueva opcion de procesos
			opcProc.setNumBloqueos(0);
			keyOpcionProc = this.convenioDao.guardarOpcionProceso(opcProc);
			
			//Guarda el grupo de convenios por defecto para poderselo asignar al convenio por defecto 
			grupoNew = new GrupoConvenioVO();
			Integer keyGrupoNew;
			grupoNew.setIdEmpresa(empresaNew.getIdEmpresa());
			grupoNew.setIdGrupoConvenio(Constants.ID_UNSAVED_GRUPO_CONVENIO);
			grupoNew.setIdMapaCod(keyMapaCod.intValue());
			grupoNew.setNumBloqueos(Constants.NUM_BLOQUEOS_DEFAULT);
			
			//Crea los mapas de nominas para todos los conceptos
			//y setea los campos correspondientes del grupo de convenios
			grupoNew.setIdMapaNomRem(this.crearMapaNomina('R', empresaNew.getIdEmpresa()));
			grupoNew.setIdMapaNomGra(this.crearMapaNomina('G', empresaNew.getIdEmpresa()));
			grupoNew.setIdMapaNomRel(this.crearMapaNomina('A', empresaNew.getIdEmpresa()));
			grupoNew.setIdMapaNomDep(this.crearMapaNomina('D', empresaNew.getIdEmpresa()));
	
			grupoNew.setNombre("GC POR DEFECTO EMP.:" + empresaNew.getIdEmpresa());
			grupoNew.setIdOpcion(keyOpcionProc.intValue());
			grupoNew.setHabilitado(Constants.COD_HABILITACION_GRUPO_CONVENIO);
			grupoNew.setCreado(new Timestamp(hoy.getTime()));
			
			keyGrupoNew = this.convenioDao.guardarGrupoConvenio(grupoNew);
			grupoNew.setIdGrupoConvenio(keyGrupoNew.intValue());
		}
		//Guarda el convenio por defecto para guardar los datos de la mutual
		ConvenioVO convenioNew = new ConvenioVO();
		convenioNew.setIdEmpresa(empresaNew.getIdEmpresa());
		convenioNew.setIdConvenio(Constants.ID_CONVENIO_DEFAULT);
		convenioNew.setIdGrupoConvenio(grupoNew.getIdGrupoConvenio());
		convenioNew.setIdMapaCod(grupoNew.getIdMapaCod());
		convenioNew.setIdMapaNomRem(grupoNew.getIdMapaNomRem());
		convenioNew.setIdMapaNomGra(grupoNew.getIdMapaNomGra());
		convenioNew.setIdMapaNomRel(grupoNew.getIdMapaNomRel());
		convenioNew.setIdMapaNomDep(grupoNew.getIdMapaNomDep());
		convenioNew.setIdOpcion(keyOpcionProc.intValue());
		convenioNew.setCreado(new Date(hoy.getTime()));
		convenioNew.setUltimoUso(convenioNew.getCreado());
		convenioNew.setCalculoMovPersonal(Constants.OPC_CALC_MOV_PERSONAL);
		convenioNew.setDescripcion("CONVENIO EMP " + empresaNew.getIdEmpresa());
		convenioNew.setHabilitado(Constants.COD_HABILITACION_CONVENIO);
		convenioNew.setIdMutual(convenio.getIdMutual());
		convenioNew.setMutualCalculoIndividual(convenio.getMutualCalculoIndividual());
		convenioNew.setMutualNumeroAdherente(convenio.getMutualNumeroAdherente());
		convenioNew.setMutualTasaAdicional(convenio.getMutualTasaAdicional());
		convenioNew.setNumBloqueos(Constants.NUM_BLOQUEOS_DEFAULT);
		convenioNew.setNumCotizaciones(0);
		convenioNew.setNumCotizacionesCorregidas(0);
		convenioNew.setNumCotizacionesOk(0);
		convenioNew.setNumNominas(0);
		convenioNew.setNumNominasCorregidas(0);
		convenioNew.setNumNominasOk(0);
		
		convenioNew.setIdActividad(convenio.getIdActividad());
		convenioNew.setIdCcaf(convenio.getIdCcaf());
		
		this.convenioDao.guardarConvenio(convenioNew);
	}
	
	/**
	 * Crea un mapa de nomina, creando mapeos de conceptos con posicion 0 y largo 0
	 * para todos los conceptos
	 * @param tipoNomina
	 * @param idEmpresa
	 * @return
	 * @throws DaoException
	 */
	public int crearMapaNomina(char tipoNomina, int idEmpresa) throws DaoException 
	{
		GrupoConvenioVO grupoConvenioDef = this.convenioDao.getGrupoConvenio(Constants.ID_GRUPO_CONVENIO_DEFAULT);
		int idMapaNomDef;
		switch (tipoNomina)	{
			case 'R':
				idMapaNomDef = grupoConvenioDef.getIdMapaNomRem();
				break;
			case 'G':
				idMapaNomDef = grupoConvenioDef.getIdMapaNomGra();
				break;
			case 'A':
				idMapaNomDef = grupoConvenioDef.getIdMapaNomRel();
				break;
			case 'D':
				idMapaNomDef = grupoConvenioDef.getIdMapaNomDep();
				break;
			default:
				throw new DaoException("Código de nómina inválido: " + tipoNomina);
		}
		
		MapaNominaVO mapaNom = new MapaNominaVO();
		Integer keyMapaNom;
		mapaNom.setIdTipoNomina(String.valueOf(tipoNomina));
		mapaNom.setIdMapaNom(Constants.ID_UNSAVED_MAPANOM);
		mapaNom.setLargoRegistro(0);
		mapaNom.setNumBloqueos(Constants.NUM_BLOQUEOS_DEFAULT);
		String sDesc = "Mapeo de "
			+ ((TipoNominaVO) this.tiposNomina.get(String.valueOf(tipoNomina))).getDescripcion().trim()
			+ " Emp. " + Utils.formatRut(idEmpresa);
		mapaNom.setDescripcion(sDesc);
		keyMapaNom = this.mapeosDao.guardarMapaNomina(mapaNom);

		MapeoConceptoVO mapeoConceptoDef, mapeoConcepto;
		for (Iterator it = this.conceptoDao.getListaMapeosConcepto(idMapaNomDef, String.valueOf(tipoNomina)).iterator(); it.hasNext();) {
			mapeoConceptoDef = (MapeoConceptoVO) it.next();
			mapeoConcepto = new MapeoConceptoVO();
			mapeoConcepto.setTipoProceso(mapeoConceptoDef.getTipoProceso());
			mapeoConcepto.setPosicion(mapeoConceptoDef.getPosicion());
			mapeoConcepto.setLargo(mapeoConceptoDef.getLargo());
			mapeoConcepto.setIdMapa(keyMapaNom.intValue());
			mapeoConcepto.setIdConcepto(mapeoConceptoDef.getIdConcepto());
			mapeoConcepto.setConcepto(mapeoConceptoDef.getConcepto());
			mapeoConcepto.setTipoSeparador(mapeoConceptoDef.getTipoSeparador());
			this.mapeosDao.saveMapeoConcepto(mapeoConcepto);
		}
		
		return keyMapaNom.intValue();
	}
	/**
	 * crea mapa cod default
	 * @param idMapaCod
	 * @throws DaoException
	 */
	//Ésta estaba en CVS, pero no realizaba una copia de OTRO Grupo Convenio, siempre lo hacía por defecto.
	//public void crearMapaCodDefault(int idMapaCod) throws DaoException
	public void crearMapaCodDefault(int idMapaCod, int idGrupoConvenio) throws DaoException
	{
		logger.info("\n\ncreando codigos por defecto:" + idMapaCod + "::");

		//Ésta estaba en CVS, pero no realizaba una copia de OTRO Grupo Convenio, siempre lo hacía por defecto.
		//GrupoConvenioVO grupoConvDef = this.convenioDao.getGrupoConvenio(Constants.ID_GRUPO_CONVENIO_DEFAULT);
		GrupoConvenioVO grupoConvDef = this.convenioDao.getGrupoConvenio(idGrupoConvenio);

		int idMapaCodDef = grupoConvDef.getIdMapaCod();

		MapeoPensionVO mapeoPensDef, mapeoPens;
		for (Iterator it = this.mapeosDao.getMapeos(idMapaCodDef, MapeoPensionVO.class, EntidadPensionVO.class).iterator(); it.hasNext();) {
			mapeoPensDef = (MapeoPensionVO) it.next();
			mapeoPens = new MapeoPensionVO();
			mapeoPens.setId(mapeoPensDef.getId());
			mapeoPens.setIdMapaCod(idMapaCod);
			mapeoPens.setEntidad(mapeoPensDef.getEntidad());
			mapeoPens.setCodigo(mapeoPensDef.getCodigo());
			this.mapeosDao.saveMapeo(mapeoPens);
		}
		
		MapeoAPVVO mapeoApvDef, mapeoApv;
		for (Iterator it = this.mapeosDao.getMapeos(idMapaCodDef, MapeoAPVVO.class, EntidadApvVO.class).iterator(); it.hasNext();) {
			mapeoApvDef = (MapeoAPVVO) it.next();
			mapeoApv = new MapeoAPVVO();
			mapeoApv.setId(mapeoApvDef.getId());
			mapeoApv.setIdMapaCod(idMapaCod);
			mapeoApv.setEntidad(mapeoApvDef.getEntidad());
			mapeoApv.setCodigo(mapeoApvDef.getCodigo());
			this.mapeosDao.saveMapeo(mapeoApv);
		}
		
		MapeoSaludVO mapeoSaludDef, mapeoSalud;
		for (Iterator it = this.mapeosDao.getMapeos(idMapaCodDef, MapeoSaludVO.class, EntidadSaludVO.class).iterator(); it.hasNext();) {
			mapeoSaludDef = (MapeoSaludVO) it.next();
			mapeoSalud = new MapeoSaludVO();
			mapeoSalud.setId(mapeoSaludDef.getId());
			mapeoSalud.setIdMapaCod(idMapaCod);
			mapeoSalud.setEntidad(mapeoSaludDef.getEntidad());
			mapeoSalud.setCodigo(mapeoSaludDef.getCodigo());
			this.mapeosDao.saveMapeo(mapeoSalud);
		}

		MapeoAsFamVO mapeoAsFamDef, mapeoAsFam;
		for (Iterator it = this.mapeosDao.getMapeos(idMapaCodDef, MapeoAsFamVO.class, AsigFamVO.class).iterator(); it.hasNext();) {
			mapeoAsFamDef = (MapeoAsFamVO) it.next();
			mapeoAsFam = new MapeoAsFamVO();
			mapeoAsFam.setId(mapeoAsFamDef.getId());
			mapeoAsFam.setIdMapaCod(idMapaCod);
			mapeoAsFam.setEntidad(mapeoAsFamDef.getEntidad());
			mapeoAsFam.setCodigo(mapeoAsFamDef.getCodigo());
			this.mapeosDao.saveMapeo(mapeoAsFam);
		}

		MapeoGeneroVO mapeoGeneroDef, mapeoGenero;
		for (Iterator it = this.mapeosDao.getMapeos(idMapaCodDef, MapeoGeneroVO.class, GeneroVO.class).iterator(); it.hasNext();) {
			mapeoGeneroDef = (MapeoGeneroVO) it.next();
			mapeoGenero = new MapeoGeneroVO();
			mapeoGenero.setId(mapeoGeneroDef.getId());
			mapeoGenero.setIdMapaCod(idMapaCod);
			mapeoGenero.setEntidad(mapeoGeneroDef.getEntidad());
			mapeoGenero.setCodigo(mapeoGeneroDef.getCodigo());
			this.mapeosDao.saveMapeo(mapeoGenero);
		}
		
		MapeoTipoMovtoVO mapeoTipoMvtoDef, mapeoTipoMvto;
		for (Iterator it = this.mapeosDao.getMapeos(idMapaCodDef, MapeoTipoMovtoVO.class, TipoMovimientoPersonalVO.class).iterator(); it.hasNext();) {
			mapeoTipoMvtoDef = (MapeoTipoMovtoVO) it.next();
			mapeoTipoMvto = new MapeoTipoMovtoVO();
			mapeoTipoMvto.setId(mapeoTipoMvtoDef.getId());
			mapeoTipoMvto.setIdMapaCod(idMapaCod);
			mapeoTipoMvto.setEntidad(mapeoTipoMvtoDef.getEntidad());
			mapeoTipoMvto.setCodigo(mapeoTipoMvtoDef.getCodigo());
			this.mapeosDao.saveMapeo(mapeoTipoMvto);
		}
		
		MapeoTipoMovtoAFVO mapeoTipoMovtoAFDef, mapeoTipoMovtoAF;
		for (Iterator it = this.mapeosDao.getMapeos(idMapaCodDef, MapeoTipoMovtoAFVO.class, TipoMvtoPersoAFVO.class).iterator(); it.hasNext();) {
			mapeoTipoMovtoAFDef = (MapeoTipoMovtoAFVO) it.next();
			mapeoTipoMovtoAF = new MapeoTipoMovtoAFVO();
			mapeoTipoMovtoAF.setId(mapeoTipoMovtoAFDef.getId());
			mapeoTipoMovtoAF.setIdMapaCod(idMapaCod);
			mapeoTipoMovtoAF.setEntidad(mapeoTipoMovtoAFDef.getEntidad());
			mapeoTipoMovtoAF.setCodigo(mapeoTipoMovtoAFDef.getCodigo());
			this.mapeosDao.saveMapeo(mapeoTipoMovtoAF);
		}
	}
	/**
	 * midifica empresa
	 * @param empresa
	 * @param personaRepLegal
	 * @param personaAdmin
	 * @param sucursal
	 * @param convenio
	 * @throws DaoException
	 */
	public void modificaEmpresa(EmpresaVO empresa, PersonaVO personaRepLegal, PersonaVO personaAdmin, SucursalVO sucursal, ConvenioVO convenio) throws DaoException 
	{
		this.empresaDao.guardaRepresentanteLegal(personaRepLegal);
		this.empresaDao.guardaAdministrador(personaAdmin);
		
		this.empresaDao.guardaSucursal(sucursal);
		empresa.setIdCasaMatriz(sucursal.getIdSucursal());
		this.empresaDao.modificaEmpresa(empresa);

		List listaConvenios = this.convenioDao.getConveniosEmpresa(empresa.getIdEmpresa());
		ConvenioVO convenioMod;
		for (Iterator it = listaConvenios.iterator(); it.hasNext();) 
		{
			convenioMod = (ConvenioVO) it.next();
			convenioMod.setIdMutual(convenio.getIdMutual());
			convenioMod.setMutualNumeroAdherente(convenio.getMutualNumeroAdherente());
			convenioMod.setMutualCalculoIndividual(convenio.getMutualCalculoIndividual());
			convenioMod.setMutualTasaAdicional(convenio.getMutualTasaAdicional());
			convenioMod.setCalculoMovPersonal(convenio.getCalculoMovPersonal());
			this.convenioDao.guardarConvenio(convenioMod);
		}
	}

	/**
	 * actividad economica
	 * @param idActividadEconomica
	 * @return
	 * @throws DaoException
	 */
	public ActividadEconomicaVO getActividadEconomica(int idActividadEconomica) throws DaoException 
	{
		return this.empresaDao.getActividadEconomica(idActividadEconomica);
	}

	/**
	 * sucursal
	 * @param idEmpresa
	 * @param idSucursal
	 * @return
	 * @throws DaoException
	 */
	public SucursalVO getSucursal(int idEmpresa, String idSucursal) throws DaoException 
	{
		return this.empresaDao.getSucursal(idEmpresa, idSucursal);
	}

	/**
	 * guarda sucursal
	 * @param sucursal
	 * @throws DaoException
	 */
	public void guardaSucursal(SucursalVO sucursal) throws DaoException 
	{
		this.empresaDao.guardaSucursal(sucursal);
	}

	/**
	 * elimina sucursal
	 * @param idEmpresa
	 * @param idSucursal
	 * @throws DaoException
	 */
	public void borraSucursal(int idEmpresa, String idSucursal) throws DaoException 
	{
		this.empresaDao.borraSucursal(idEmpresa, idSucursal);
	}

	/**
	 * guarda sucursal
	 * @param sucursal
	 * @throws DaoException
	 */
	public void saveSucursal(SucursalVO sucursal) throws DaoException 
	{
		this.empresaDao.saveSucursal(sucursal);
	}

	/**
	 * lista empresas
	 * @return
	 * @throws DaoException
	 */
	public List getListaEmpresas() throws DaoException 
	{
		return this.empresaDao.getListaEmpresas();
	}

	/**
	 * lista empresas
	 * @param rutBuscar
	 * @param razSocBuscar
	 * @param nombreGrupoConvenio
	 * @param codGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getListaEmpresas(int rutBuscar, String razSocBuscar, String nombreGrupoConvenio, int codGrupoConvenio) throws DaoException 
	{
		return this.empresaDao.getListaEmpresas(rutBuscar, razSocBuscar, nombreGrupoConvenio, codGrupoConvenio);
	}
	
	/**
	 * getListTipoEmpresas
	 * @param rutEmpresa
	 * @param tipoEmpresa
	 * @return List
	 * @throws DaoException
	 */
	public List getListTipoEmpresas(int rutEmpresa, String tipoEmpresa) throws DaoException
	{
		return this.empresaDao.getListTipoEmpresas(rutEmpresa, tipoEmpresa);
	}
	
	public List getListTipoEmpresas(String tipoEmpresa) throws DaoException
	{
		return this.empresaDao.getListTipoEmpresas(tipoEmpresa);
	}
}
