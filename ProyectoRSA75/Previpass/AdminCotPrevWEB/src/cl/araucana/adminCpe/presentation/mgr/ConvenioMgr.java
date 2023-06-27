package cl.araucana.adminCpe.presentation.mgr;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.ConceptoDAO;
import cl.araucana.adminCpe.hibernate.dao.ConvenioDAO;
import cl.araucana.adminCpe.hibernate.dao.EmpresaDAO;
import cl.araucana.adminCpe.hibernate.dao.EntidadesDAO;
import cl.araucana.adminCpe.hibernate.dao.LectorDAO;
import cl.araucana.adminCpe.hibernate.dao.MapeosDAO;
import cl.araucana.adminCpe.hibernate.dao.NominaDAO;
import cl.araucana.adminCpe.hibernate.dao.PersonaDAO;
import cl.araucana.adminCpe.presentation.struts.javaBeans.Usuario;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaEmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaGrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaSucursalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaCodigoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NivelAccConvSucVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Empresa;
import cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio;
import cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector;

/*
 * @(#) ConvenioMgr.java 1.29 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author malvarez
 * 
 * @version 1.29
 */
public class ConvenioMgr
{
	private ConvenioDAO convenioDao;
	private EntidadesDAO entidadesDao;
	private NominaDAO nominaDao;
	private MapeosDAO mapeosDao;
	private ConceptoDAO conceptoDao;
	private LectorDAO lectorDao;
	private PersonaDAO personaDao;
	private EmpresaDAO empresaDao;
	private EmpresaMgr empresaMgr;

	private Map tiposNomina;

	public ConvenioMgr(Session session)
	{
		this.convenioDao = new ConvenioDAO(session);
		this.nominaDao = new NominaDAO(session);
		this.mapeosDao = new MapeosDAO(session);
		this.conceptoDao = new ConceptoDAO(session);
		this.lectorDao = new LectorDAO(session);
		this.personaDao = new PersonaDAO(session);
		this.entidadesDao = new EntidadesDAO(session);
		this.empresaDao = new EmpresaDAO(session);
		this.empresaMgr = new EmpresaMgr(session);
	}

	/**
	 * convenio
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public ConvenioVO getConvenio(int idEmpresa, int idConvenio) throws DaoException
	{
		return this.convenioDao.getConvenio(idEmpresa, idConvenio);
	}

	/**
	 * entidad CCFA
	 * 
	 * @param idCaja
	 * @return
	 * @throws DaoException
	 */
	public EntidadCCAFVO getCaja(int idCaja) throws DaoException
	{
		return this.convenioDao.getCaja(idCaja);
	}

	/**
	 * entidad mutual
	 * 
	 * @param idMutual
	 * @return
	 * @throws DaoException
	 */
	public EntidadMutualVO getMutual(int idMutual) throws DaoException
	{
		return this.convenioDao.getMutual(idMutual);
	}

	/**
	 * grupo convenio
	 * 
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public GrupoConvenioVO getGrupoConvenio(int idGrupoConvenio) throws DaoException
	{
		return this.convenioDao.getGrupoConvenio(idGrupoConvenio);
	}

	/**
	 * grupo convenio habilitado
	 * 
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public boolean isGrupoConvenioHabilitado(int idGrupoConvenio) throws DaoException
	{
		return this.convenioDao.isGrupoConvenioHabilitado(idGrupoConvenio);
	}

	/**
	 * convenio empresa
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getConveniosEmpresa(int idEmpresa) throws DaoException
	{
		return this.convenioDao.getConveniosEmpresa(idEmpresa);
	}

	/**
	 * retorna la lista de convenios asociados a un determinado grupos de convenios
	 * 
	 * @param idGrupo
	 * @return
	 * @throws DaoException
	 */
	public List getConveniosGrupo(int idGrupo) throws DaoException
	{
		return this.convenioDao.getConveniosGrupo(idGrupo);
	}

	/**
	 * entidad mutual empresa
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EntidadMutualVO getMutualEmpresa(int idEmpresa) throws DaoException
	{
		return this.entidadesDao.getMutual((((ConvenioVO) this.getConveniosEmpresa(idEmpresa).get(0)).getIdMutual()));
	}

	/**
	 * guardar convenio
	 * 
	 * @param convenio
	 * @throws DaoException
	 */
	public void guardarConvenio(ConvenioVO convenio) throws DaoException
	{
		this.convenioDao.guardarConvenio(convenio);
	}

	/**
	 * listagrupo convenio
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaGruposConvenio() throws DaoException
	{
		return this.convenioDao.getListaGruposConvenio();
	}

	/**
	 * listagrupo convenio
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaGruposConvenioActivos() throws DaoException
	{
		return this.convenioDao.getListaGruposConvenioActivos();
	}

	/**
	 * lista grupos convenio
	 * 
	 * @param idGrupoConvenio
	 * @param nombreGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getListaGruposConvenio(Integer idGrupoConvenio, String nombreGrupoConvenio) throws DaoException
	{
		return this.convenioDao.getListaGruposConvenio(idGrupoConvenio, nombreGrupoConvenio);
	}

	/**
	 * lista opciones procesos
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaOpcionesProcesos() throws DaoException
	{
		return this.convenioDao.getListaOpcionesProcesos();
	}

	/**
	 * convenio No exp
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public ConvenioVO getConvenioNoExcp(int idEmpresa, int idConvenio) throws DaoException
	{
		return this.convenioDao.getConvenioNoExcp(idEmpresa, idConvenio);
	}

	/**
	 * guarda convenio
	 * 
	 * @param convenio
	 * @throws DaoException
	 */
	public void guardaConvenio(ConvenioVO convenio) throws DaoException
	{
		GrupoConvenioVO gConvenio = this.convenioDao.getGrupoConvenio(convenio.getIdGrupoConvenio());
		convenio.setIdMapaCod(gConvenio.getIdMapaCod());
		convenio.setIdMapaNomRem(gConvenio.getIdMapaNomRem());
		convenio.setIdMapaNomGra(gConvenio.getIdMapaNomGra());
		convenio.setIdMapaNomRel(gConvenio.getIdMapaNomRel());
		convenio.setIdMapaNomDep(gConvenio.getIdMapaNomDep());
		convenio.setIdOpcion(gConvenio.getIdOpcion());

		List listaConvenio = this.convenioDao.getConveniosEmpresa(convenio.getIdEmpresa());
		ConvenioVO convenioEmpresa = (ConvenioVO) listaConvenio.get(0);
		convenio.setIdMutual(convenioEmpresa.getIdMutual());
		convenio.setMutualCalculoIndividual(convenioEmpresa.getMutualCalculoIndividual());
		convenio.setMutualNumeroAdherente(convenioEmpresa.getMutualNumeroAdherente());
		convenio.setMutualTasaAdicional(convenioEmpresa.getMutualTasaAdicional());

		Date hoy = new Date();
		convenio.setCreado(new java.sql.Date(hoy.getTime()));
		convenio.setUltimoUso(convenio.getCreado());

		convenio.setNumNominas(0);
		convenio.setNumNominasCorregidas(0);
		convenio.setNumNominasOk(0);
		convenio.setNumCotizaciones(0);
		convenio.setNumCotizacionesCorregidas(0);
		convenio.setNumCotizacionesOk(0);
		convenio.setNumBloqueos(0);

		this.convenioDao.guardarConvenio(convenio);
	}

	/**
	 * generar rango convenios
	 * 
	 * @param datos
	 * @param codInicial
	 * @param codFinal
	 * @throws DaoException
	 */
	public void generarRangoConvenios(ConvenioVO datos, int codInicial, int codFinal) throws DaoException
	{
		GrupoConvenioVO gConvenio = this.convenioDao.getGrupoConvenio(datos.getIdGrupoConvenio());
		ConvenioVO convenioEmpresa = (ConvenioVO) this.convenioDao.getConveniosEmpresa(datos.getIdEmpresa()).get(0);

		ConvenioVO convenio;
		for (int idConvenio = codInicial; idConvenio <= codFinal; idConvenio++)
		{
			convenio = new ConvenioVO();

			convenio.setIdConvenio(idConvenio);
			convenio.setIdEmpresa(datos.getIdEmpresa());
			convenio.setDescripcion(datos.getDescripcion().trim() + " " + idConvenio);

			convenio.setIdGrupoConvenio(datos.getIdGrupoConvenio());
			convenio.setIdMapaCod(gConvenio.getIdMapaCod());
			convenio.setIdMapaNomRem(gConvenio.getIdMapaNomRem());
			convenio.setIdMapaNomGra(gConvenio.getIdMapaNomGra());
			convenio.setIdMapaNomRel(gConvenio.getIdMapaNomRel());
			convenio.setIdMapaNomDep(gConvenio.getIdMapaNomDep());
			convenio.setIdOpcion(gConvenio.getIdOpcion());

			convenio.setCalculoMovPersonal(datos.getCalculoMovPersonal());
			convenio.setHabilitado(datos.getHabilitado());

			convenio.setIdMutual(convenioEmpresa.getIdMutual());
			convenio.setMutualCalculoIndividual(convenioEmpresa.getMutualCalculoIndividual());
			convenio.setMutualNumeroAdherente(convenioEmpresa.getMutualNumeroAdherente());
			convenio.setMutualTasaAdicional(convenioEmpresa.getMutualTasaAdicional());

			Date hoy = new Date();
			convenio.setCreado(new java.sql.Date(hoy.getTime()));
			convenio.setUltimoUso(convenio.getCreado());

			convenio.setIdActividad(datos.getIdActividad());

			convenio.setNumNominas(0);
			convenio.setNumNominasCorregidas(0);
			convenio.setNumNominasOk(0);
			convenio.setNumCotizaciones(0);
			convenio.setNumCotizacionesCorregidas(0);
			convenio.setNumCotizacionesOk(0);
			convenio.setNumBloqueos(0);

			this.convenioDao.guardarConvenio(convenio);
		}
	}

	/**
	 * existe convenio rango
	 * 
	 * @param idEmpresa
	 * @param codInicial
	 * @param codFinal
	 * @return
	 * @throws DaoException
	 */
	public boolean existeConvenioEnRango(int idEmpresa, int codInicial, int codFinal) throws DaoException
	{
		return this.convenioDao.existeConvenioEnRango(idEmpresa, codInicial, codFinal);
	}

	/**
	 * opcion preceso
	 * 
	 * @param idOpcProc
	 * @return
	 * @throws DaoException
	 */
	public OpcionProcVO getOpcionProceso(int idOpcProc) throws DaoException
	{
		return this.convenioDao.getOpcionProceso(idOpcProc);
	}

	/**
	 * crea grupo convenio
	 * 
	 * @param grupo
	 * @param opcProc
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public int crearGrupoConvenio(GrupoConvenioVO grupo, OpcionProcVO opcProc, int idGrupoConvenio, Character caracterSeparador) throws DaoException
	{
		java.util.Date hoy = new java.util.Date();
		this.tiposNomina = mapaTiposNominas();

		// Guarda el mapa de codigos a ser utilizado por el grupo de convenios
		// y todos los codigos para todas las entidades
		MapaCodigoVO mapaCod = new MapaCodigoVO();
		Integer keyMapaCod;
		mapaCod.setIdMapaCodigo(Constants.ID_UNSAVED_MAPACOD);
		mapaCod.setNumBloqueos(Constants.NUM_BLOQUEOS_DEFAULT);
		String mapeoDescripcion = (idGrupoConvenio == Constants.ID_GRUPO_CONVENIO_DEFAULT ? "Mapa POR DEFECTO GC.: " : "Mapa del GC.: " + idGrupoConvenio);
		mapaCod.setDescripcion(mapeoDescripcion);
		keyMapaCod = this.mapeosDao.saveMapaCodigo(mapaCod);
		//Ésta estaba en CVS, pero no realizaba una copia de OTRO Grupo Convenio, siempre lo hacía por defecto.
		//this.empresaMgr.crearMapaCodDefault(keyMapaCod.intValue());
		this.empresaMgr.crearMapaCodDefault(keyMapaCod.intValue(), idGrupoConvenio);

		// Guarda una nueva opcion de procesos
		opcProc.setNumBloqueos(Constants.NUM_BLOQUEOS_DEFAULT);
		Integer keyOpcionProc = this.convenioDao.guardarOpcionProceso(opcProc);

		// Guarda el grupo de convenios
		GrupoConvenioVO grupoNew = new GrupoConvenioVO();
		grupoNew.setIdEmpresa(0);
		grupoNew.setIdGrupoConvenio(Constants.ID_UNSAVED_GRUPO_CONVENIO);
		grupoNew.setIdMapaCod(keyMapaCod.intValue());
		grupoNew.setNumBloqueos(Constants.NUM_BLOQUEOS_DEFAULT);
	
		// Crea los mapas de nóminas para todos los conceptos
		// y setea los campos correspondientes del grupo de convenio base seleccionado
		grupoNew.setIdMapaNomRem(this.crearMapaNomina('R', idGrupoConvenio, caracterSeparador));
		grupoNew.setIdMapaNomGra(this.crearMapaNomina('G', idGrupoConvenio, caracterSeparador));
		grupoNew.setIdMapaNomRel(this.crearMapaNomina('A', idGrupoConvenio, caracterSeparador));
		grupoNew.setIdMapaNomDep(this.crearMapaNomina('D', idGrupoConvenio, caracterSeparador));

		grupoNew.setNombre(grupo.getNombre());
		grupoNew.setIdOpcion(keyOpcionProc.intValue());
		grupoNew.setHabilitado(grupo.getHabilitado());
		grupoNew.setCreado(new Timestamp(hoy.getTime()));
		grupoNew.setPrevired(grupo.isPrevired());
		grupoNew.setProdCaja(getGrupoConvenio(idGrupoConvenio).isProdCaja());
		
		Integer keyGrupoConvenio = this.convenioDao.guardarGrupoConvenio(grupoNew);

		mapaCod = this.mapeosDao.getMapaCodigo(keyMapaCod.intValue());
		MapaNominaVO mapaNomR = this.mapeosDao.getMapaNomina(grupoNew.getIdMapaNomRem());
		MapaNominaVO mapaNomG = this.mapeosDao.getMapaNomina(grupoNew.getIdMapaNomGra());
		MapaNominaVO mapaNomA = this.mapeosDao.getMapaNomina(grupoNew.getIdMapaNomRel());
		MapaNominaVO mapaNomD = this.mapeosDao.getMapaNomina(grupoNew.getIdMapaNomDep());

		mapaCod.setDescripcion(mapaCod.getDescripcion() + " " + keyGrupoConvenio.intValue());
		mapaNomR.setDescripcion(mapaNomR.getDescripcion() + " " + keyGrupoConvenio.intValue());
		mapaNomG.setDescripcion(mapaNomG.getDescripcion() + " " + keyGrupoConvenio.intValue());
		mapaNomA.setDescripcion(mapaNomA.getDescripcion() + " " + keyGrupoConvenio.intValue());
		mapaNomD.setDescripcion(mapaNomD.getDescripcion() + " " + keyGrupoConvenio.intValue());

		return keyGrupoConvenio.intValue();
	}

	/**
	 * mapa tipos nomina
	 * 
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
	 * crea mapa nómina
	 *
	 * @param tipoNomina
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	private int crearMapaNomina(char tipoNomina, int idGrupoConvenio, Character caracSeparador) throws DaoException
	{
		GrupoConvenioVO grupoConvenio = this.convenioDao.getGrupoConvenio(idGrupoConvenio); //Se cambió Constants.ID_GRUPO_CONVENIO_DEFAULT por idGrupoConvenio
		int idMapaNom;
		switch (tipoNomina)
		{
		case 'R':
			idMapaNom = grupoConvenio.getIdMapaNomRem();
			break;
		case 'G':
			idMapaNom = grupoConvenio.getIdMapaNomGra();
			break;
		case 'A':
			idMapaNom = grupoConvenio.getIdMapaNomRel();
			break;
		case 'D':
			idMapaNom = grupoConvenio.getIdMapaNomDep();
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
		String sDesc = "Mapeo de " + ((TipoNominaVO) this.tiposNomina.get(String.valueOf(tipoNomina))).getDescripcion().trim() + " GC. ";
		mapaNom.setDescripcion(sDesc);
		keyMapaNom = this.mapeosDao.guardarMapaNomina(mapaNom);

		MapeoConceptoVO mapeoConceptoDef, mapeoConcepto;
		List listaConceptos = this.conceptoDao.getListaMapeosConcepto(idMapaNom, "" + tipoNomina);
		for (Iterator it = listaConceptos.iterator(); it.hasNext();)
		{
			mapeoConceptoDef = (MapeoConceptoVO) it.next();
			mapeoConcepto = new MapeoConceptoVO();
			mapeoConcepto.setTipoProceso(mapeoConceptoDef.getTipoProceso());
			mapeoConcepto.setPosicion(mapeoConceptoDef.getPosicion());

			//TODO csanchez
			if (caracSeparador != null && !caracSeparador.toString().trim().equals("")) {
				mapeoConcepto.setLargo(0);
				mapeoConcepto.setCaracterSeparador(caracSeparador);
				mapeoConcepto.setTipoSeparador(Constants.TIPO_SEPARADOR_CARACTER);
			} else {
				mapeoConcepto.setLargo(mapeoConceptoDef.getLargo());
				mapeoConcepto.setTipoSeparador(Constants.TIPO_SEPARADOR_POSICION);
				
			}

			mapeoConcepto.setIdMapa(keyMapaNom.intValue());
			mapeoConcepto.setIdConcepto(mapeoConceptoDef.getIdConcepto());
			mapeoConcepto.setConcepto(mapeoConceptoDef.getConcepto());
			this.mapeosDao.saveMapeoConcepto(mapeoConcepto);
		}
		return keyMapaNom.intValue();
	}

	/**
	 * lista grupos convenio empresa agrupado
	 * 
	 * @param idGrupo
	 * @return
	 * @throws DaoException
	 */
	public List getListaGruposConvenioPorEmpresaAgrupado(int idGrupo) throws DaoException
	{
		return this.convenioDao.getListaGruposConvenioPorEmpresaAgrupado(idGrupo);
	}

	/**
	 * lista convenios empresa grupo
	 * 
	 * @param idGrupo
	 * @param empresa
	 * @return
	 * @throws DaoException
	 */
	public List getListaConveniosEmpresaPorGrup(int idGrupo, Integer empresa) throws DaoException
	{
		return this.convenioDao.getListaConveniosEmpresaPorGrup(idGrupo, empresa);
	}

	/**
	 * lista encargado grupo convenio
	 * 
	 * @param rutEmpre
	 * @return
	 * @throws DaoException
	 */
	public List getEncargadoGrupoConvenio(int rutEmpre) throws DaoException
	{
		return this.convenioDao.getEncargadoGrupoConvenio(rutEmpre);
	}

	/**
	 * lista lector planillas empresa
	 * 
	 * @param empresa
	 * @return
	 * @throws DaoException
	 */
	public List getLectorPlanillasEmpresa(int empresa) throws DaoException
	{
		List empresas = new ArrayList();
		List planillaEmpresa = this.convenioDao.getLectorPlanillasEmpresa(empresa);
		if (planillaEmpresa != null)
		{
			Iterator t = planillaEmpresa.iterator();
			while (t.hasNext())
			{
				LectorEmpresaEmpresaVO lectorEmpresaVO = (LectorEmpresaEmpresaVO) t.next();
				empresas.add(this.personaDao.getPersona(lectorEmpresaVO.getIdLectorEmpresa()));
			}
		}
		return empresas;
	}

	/**
	 * lista lector planillas convenio
	 * 
	 * @param empresa
	 * @return
	 * @throws DaoException
	 */
	public List getLectorPlanillasConvenio(int empresa) throws DaoException
	{
		List convenios = new ArrayList();
		List planillaConvenio = this.convenioDao.getLectorPlanillasConvenio(empresa);
		if (planillaConvenio != null)
		{
			Iterator t = planillaConvenio.iterator();
			while (t.hasNext())
			{
				LectorEmpresaConvenioVO lectorConvenioVO = (LectorEmpresaConvenioVO) t.next();
				convenios.add(this.personaDao.getPersona(lectorConvenioVO.getIdLectorEmpresa()));
			}
		}
		return convenios;
	}

	/**
	 * lista lector planillas sucursales
	 * 
	 * @param empresa
	 * @return
	 * @throws DaoException
	 */
	public List getLectorPlanillasSucursal(int empresa) throws DaoException
	{
		List sucursales = new ArrayList();
		List planillaSucursal = this.convenioDao.getLectorPlanillasSucursal(empresa);
		Iterator t = planillaSucursal.iterator();
		while (t.hasNext())
		{
			LectorEmpresaConvenioVO lectorSucursalVO = (LectorEmpresaConvenioVO) t.next();
			sucursales.add(this.personaDao.getPersona(lectorSucursalVO.getIdLectorEmpresa()));
		}
		return sucursales;
	}

	/**
	 * lista lector planillas grupo convenio
	 * 
	 * @param grupo
	 * @return
	 * @throws DaoException
	 */
	public List getLectorPlanillasGrupoConvenio(int grupo) throws DaoException
	{
		List grupos = new ArrayList();
		List planillaGrupo = this.convenioDao.getLectorPlanillasGrupoConvenio(grupo);
		if (planillaGrupo != null)
		{
			Iterator t = planillaGrupo.iterator();
			while (t.hasNext())
			{
				LectorEmpresaGrupoConvenioVO lectorConvenioVO = (LectorEmpresaGrupoConvenioVO) t.next();
				grupos.add(this.personaDao.getPersona(lectorConvenioVO.getIdLectorEmpresa()));
			}
		}
		return grupos;
	}

	/**
	 * numero empresas habilitadas
	 * 
	 * @param idGrupo
	 * @return
	 * @throws DaoException
	 */
	public int getNumEmpsHabilitadas(int idGrupo) throws DaoException
	{
		return this.convenioDao.getNumEmpsHabilitadas(idGrupo);
	}

	/**
	 * guarda opcion procesos
	 * 
	 * @param opcionProcesos
	 * @throws DaoException
	 */
	public void guardaOpcionProcesos(OpcionProcVO opcionProcesos) throws DaoException
	{
		this.convenioDao.guardaOpcionProcesos(opcionProcesos);
	}

	/**
	 * genera estructura con permisos ya asignados (retorno indice cero), y realiza busqueda por parametros recibidos de permisos aun no asignados (retorno indice 1), sin repetir el mismo permiso en
	 * ambos resultados. si ya viene permisos en lista 'permisos', se reutiliza dicha lista
	 * 
	 * @param idConvenio
	 * @param idEmpresa
	 * @return
	 * @throws Exception
	 */
	public List getConvenioEncLector(int idConvenio, int idEmpresa) throws Exception
	{
		List tmp = new ArrayList();
		HashMap perm = new HashMap();

		ConvenioVO convenio = this.convenioDao.getConvenio(idEmpresa, idConvenio);
		EmpresaVO empresa = this.empresaDao.getEmpresa(idEmpresa);
		GrupoConvenioVO grupo = this.convenioDao.getGrupoConvenio(convenio.getIdGrupoConvenio());
		cargaPermLector(idConvenio, idEmpresa, perm, convenio, empresa, grupo);
		cargaPermEncargado(idConvenio, idEmpresa, perm, convenio, empresa, grupo);
		// ordenamiento resultados
		for (Iterator it = perm.values().iterator(); it.hasNext();)
		{
			Usuario user = (Usuario) it.next();
			for (Iterator itG = user.getAsignaciones().iterator(); itG.hasNext();)
			{
				GrupoConvenio gc = (GrupoConvenio) itG.next();
				for (Iterator it2 = gc.getEmpresas().iterator(); it2.hasNext();)
				{
					Empresa emp = (Empresa) it2.next();
					Collections.sort(emp.getConvenios());
				}
				Collections.sort(gc.getEmpresas());
			}
			Collections.sort(user.getAsignaciones());
			tmp.add(user);
		}
		Collections.sort(tmp);
		return tmp;
	}

	/**
	 * lista niveles acceso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaNivelesAcceso() throws DaoException
	{
		return this.personaDao.getListaNivelesAcceso();
	}

	/**
	 * carga permisos encargado
	 * 
	 * @param idConvenio
	 * @param idEmpresa
	 * @param perm
	 * @param convenio
	 * @param empresa
	 * @param gc
	 * @throws DaoException
	 */
	private void cargaPermEncargado(int idConvenio, int idEmpresa, HashMap perm, ConvenioVO convenio, EmpresaVO empresa, GrupoConvenioVO gc) throws DaoException
	{
		List permEncargado = this.personaDao.getPermisosEncargado(idEmpresa, idConvenio);
		List niveles = getListaNivelesAcceso();
		HashMap hashNiveles = new HashMap();
		for (Iterator it = niveles.iterator(); it.hasNext();)
		{
			NivelAccConvSucVO nivel = (NivelAccConvSucVO) it.next();
			hashNiveles.put("" + nivel.getIdNivelAcceso(), nivel.getNombre().trim());
		}
		for (Iterator it = permEncargado.iterator(); it.hasNext();)
		{
			EncargadoConvenioVO enc = (EncargadoConvenioVO) it.next();
			Usuario user = null;
			if (perm.containsKey(new Integer(enc.getIdEncargado())))
				user = (Usuario) perm.get(new Integer(enc.getIdEncargado()));
			else
			{
				PersonaVO persona = this.personaDao.getPersona(enc.getIdEncargado());
				user = new Usuario(persona.getIdPersona().intValue(), Utils.formatRut(persona.getIdPersona().intValue()), persona.getNombres(), persona.getApellidoPaterno().trim() + " "
						+ persona.getApellidoMaterno().trim());
				perm.put(persona.getIdPersona(), user);
			}
			GrupoConvenio grupo = user.getGrupoConvenio(convenio.getIdGrupoConvenio());
			if (grupo == null)
			{
				grupo = new GrupoConvenio(convenio.getIdGrupoConvenio(), false, gc.getNombre());
				user.addGrupoConvenio(grupo);
			}
			Empresa em = grupo.getEmpresa(idEmpresa);
			if (em == null)
			{
				em = new Empresa(idEmpresa, false, Utils.formatRut(idEmpresa), empresa.getRazonSocial());
				grupo.addEmpresa(em);
			}
			PermEncargadoLector permiso = em.getPermEncargadoLector(enc.getIdConvenio());
			if (permiso == null)
				em.addPermEncargadoLector(new PermEncargadoLector(convenio.getIdConvenio(), enc.getIdNivelAcceso(), getDescNivel(enc.getIdNivelAcceso(), hashNiveles), false, ""));
			else
			{
				permiso.setIdNivel(enc.getIdNivelAcceso());
				permiso.setNombreNivel(getDescNivel(enc.getIdNivelAcceso(), hashNiveles));
			}
		}
	}

	/**
	 * desc nivel
	 * 
	 * @param id
	 * @param hashNiveles
	 * @return
	 */
	public String getDescNivel(int id, HashMap hashNiveles)
	{
		String key = "" + id;
		return (hashNiveles.containsKey(key) ? (String) hashNiveles.get(key) : "Sin Acceso");
	}

	/**
	 * carga permisos lector
	 * 
	 * @param idConvenio
	 * @param idEmpresa
	 * @param perm
	 * @param convenio
	 * @param empresa
	 * @param gc
	 * @throws Exception
	 */
	private void cargaPermLector(int idConvenio, int idEmpresa, HashMap perm, ConvenioVO convenio, EmpresaVO empresa, GrupoConvenioVO gc) throws Exception
	{
		// agrega directamente grupos de convenios al resultado
		for (Iterator itG = this.lectorDao.getLectorXGrupo(convenio.getIdGrupoConvenio()).iterator(); itG.hasNext();)
		{
			PersonaVO persona = (PersonaVO) itG.next();
			Usuario user = new Usuario(persona.getIdPersona().intValue(), Utils.formatRut(persona.getIdPersona().intValue()), persona.getNombres(), persona.getApellidoPaterno().trim() + " "
					+ persona.getApellidoMaterno().trim());
			user.addGrupoConvenio(new GrupoConvenio(gc.getIdGrupoConvenio(), true, gc.getNombre()));
			perm.put(persona.getIdPersona(), user);

		}
		for (Iterator itC = this.lectorDao.getLectorXConvenio(idEmpresa, idConvenio).iterator(); itC.hasNext();)
		{
			Usuario user = null;
			PersonaVO persona = (PersonaVO) itC.next();
			if (perm.containsKey(persona.getIdPersona()))
				user = (Usuario) perm.get(persona.getIdPersona());
			else
			{
				user = new Usuario(persona.getIdPersona().intValue(), Utils.formatRut(persona.getIdPersona().intValue()), persona.getNombres(), persona.getApellidoPaterno().trim() + " "
						+ persona.getApellidoMaterno().trim());
				perm.put(persona.getIdPersona(), user);
			}
			GrupoConvenio grupo = user.getGrupoConvenio(convenio.getIdGrupoConvenio());
			if (grupo == null)
			{
				grupo = new GrupoConvenio(gc.getIdGrupoConvenio(), false, gc.getNombre());
				user.addGrupoConvenio(grupo);
			}
			Empresa em = grupo.getEmpresa(idEmpresa);
			if (em == null)
			{
				em = new Empresa(idEmpresa, false, Utils.formatRut(idEmpresa), empresa.getRazonSocial());
				grupo.addEmpresa(em);
			}
			em.addPermEncargadoLector(new PermEncargadoLector(idConvenio, 0, "", true, ""));
		}
		// agrega sucursales al resultado, si no existe grupo lo agrega, si dentro de grupo no existe empresa, la agrega, y agrega convenio (para considerar sucursales)
		for (Iterator itS = this.lectorDao.getLectoresSucursal(idEmpresa, idConvenio).iterator(); itS.hasNext();)
		{
			LectorEmpresaSucursalVO s = (LectorEmpresaSucursalVO) itS.next();
			Usuario user = null;
			if (perm.containsKey(new Integer(s.getIdLectorEmpresa())))
				user = (Usuario) perm.get(new Integer(s.getIdLectorEmpresa()));
			else
			{
				PersonaVO persona = this.personaDao.getPersona(s.getIdLectorEmpresa());
				user = new Usuario(persona.getIdPersona().intValue(), Utils.formatRut(persona.getIdPersona().intValue()), persona.getNombres(), persona.getApellidoPaterno().trim() + " "
						+ persona.getApellidoMaterno().trim());
				perm.put(persona.getIdPersona(), user);
			}
			GrupoConvenio grupo = user.getGrupoConvenio(convenio.getIdGrupoConvenio());
			if (grupo == null)
			{
				grupo = new GrupoConvenio(gc.getIdGrupoConvenio(), false, gc.getNombre());
				user.addGrupoConvenio(grupo);
			}
			Empresa em = grupo.getEmpresa(idEmpresa);
			if (em == null)
			{
				em = new Empresa(idEmpresa, false, Utils.formatRut(idEmpresa), empresa.getRazonSocial());
				grupo.addEmpresa(em);
			}
			PermEncargadoLector permiso = em.getPermEncargadoLector(idConvenio);
			if (permiso == null)
				em.addPermEncargadoLector(new PermEncargadoLector(idConvenio, 0, "", true, s.getIdSucursal()));
			else
				permiso.setSucursalLector(permiso.getSucursalLector() + s.getIdSucursal() + "<br />");
		}
		// agrega empresas al resultado, si no existe grupo lo agrega, si dentro de grupo no existe empresa, la agrega
		for (Iterator itE = this.lectorDao.getLectorXEmpresa(idEmpresa).iterator(); itE.hasNext();)
		{
			Usuario user = null;
			PersonaVO persona = (PersonaVO) itE.next();
			if (perm.containsKey(persona.getIdPersona()))
				user = (Usuario) perm.get(persona.getIdPersona());
			else
			{
				user = new Usuario(persona.getIdPersona().intValue(), Utils.formatRut(persona.getIdPersona().intValue()), persona.getNombres(), persona.getApellidoPaterno().trim() + " "
						+ persona.getApellidoMaterno().trim());
				perm.put(persona.getIdPersona(), user);
			}
			GrupoConvenio grupo = user.getGrupoConvenio(convenio.getIdGrupoConvenio());
			if (grupo == null)
			{
				grupo = new GrupoConvenio(gc.getIdGrupoConvenio(), false, gc.getNombre());
				user.addGrupoConvenio(grupo);
			}
			Empresa em = grupo.getEmpresa(idEmpresa);
			if (em == null)
			{
				em = new Empresa(idEmpresa, true, Utils.formatRut(idEmpresa), empresa.getRazonSocial());
				grupo.addEmpresa(em);
			} else
				em.setPermLector(true);
		}
	}

	/**
	 * lista grupos de convenio base
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getGrupoConveniosBase() throws DaoException
	{
		return this.convenioDao.getGrupoConveniosBase();
	}
}