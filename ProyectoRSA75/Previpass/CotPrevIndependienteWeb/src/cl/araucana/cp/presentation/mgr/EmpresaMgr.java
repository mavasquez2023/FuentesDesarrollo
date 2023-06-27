package cl.araucana.cp.presentation.mgr;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ActividadEconomicaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RepresentanteLegalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Empresa;
import cl.araucana.cp.hibernate.dao.ConvenioDAO;
import cl.araucana.cp.hibernate.dao.EmpresaDAO;
import cl.araucana.cp.hibernate.dao.NominaDAO;
import cl.araucana.cp.hibernate.dao.PersonaDAO;

/*
 * @(#) EmpresaMgr.java 1.21 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.21
 */
public class EmpresaMgr
{
	private static Logger logger = Logger.getLogger(EmpresaMgr.class);

	private EmpresaDAO empresaDao;
	private PersonaDAO personaDao;
	private ConvenioDAO convenioDao;

	//Para modificar el reenvío de nóminas
	private NominaDAO nominaDao;

	public EmpresaMgr(Session session)
	{
		this.empresaDao  = new EmpresaDAO(session);
		this.personaDao  = new PersonaDAO(session);
		this.convenioDao = new ConvenioDAO(session);
		this.nominaDao   = new NominaDAO(session);
	}

	/**
	 * empresa
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresa(int rutEmpresa) throws DaoException
	{
		return this.empresaDao.getEmpresa(rutEmpresa);
	}

	/**
	 * empresa
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresaGet(int rutEmpresa) throws DaoException
	{
		return this.empresaDao.getEmpresaGet(rutEmpresa);
	}

	/**
	 * sucursales
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getSucursales(int rutEmpresa) throws DaoException
	{
		return this.empresaDao.getSucursales(rutEmpresa);
	}

	/**
	 * sucursales
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getSucursales(String rutEmpresa) throws DaoException
	{
		return this.empresaDao.getSucursales(rutEmpresa);
	}

	/**
	 * sucursales hash
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public HashMap getSucursalesHash(String rutEmpresa) throws DaoException
	{
		return this.empresaDao.getSucursalesHash(rutEmpresa);
	}

	/**
	 * representante legal
	 * 
	 * @param idRepLegal
	 * @return
	 * @throws DaoException
	 */
	public RepresentanteLegalVO getRepresentanteLegal(int idRepLegal) throws DaoException
	{
		return this.empresaDao.getRepresentanteLegal(idRepLegal);
	}

	/**
	 * actividades economicas
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getActividadesEconomicas() throws DaoException
	{
		return this.empresaDao.getActividadesEconomicas();
	}

	/**
	 * crear empresa
	 * 
	 * @param empresa
	 * @param personaRepLegal
	 * @param sucursal
	 * @param convenio
	 * @throws DaoException
	 */
	public void crearEmpresa(EmpresaVO empresa, PersonaVO personaRepLegal, SucursalVO sucursal, ConvenioVO convenio) throws DaoException
	{
		java.util.Date hoy = new java.util.Date();

		// Guarda el representante legal y admin
		PersonaVO personaRep = this.personaDao.getPersona(personaRepLegal.getIdPersona().intValue());
		if (personaRep == null)
		{
			logger.info("No encontro Persona:" + personaRepLegal.getIdPersona().intValue() + ":representante legal nueva empresa:");
			PersonaVO personaNew = new PersonaVO();
			personaNew.setIdPersona(personaRepLegal.getIdPersona());
			personaNew.setNombres(personaRepLegal.getNombres());
			personaNew.setApellidoPaterno(personaRepLegal.getApellidoPaterno());
			personaNew.setApellidoMaterno(personaRepLegal.getApellidoMaterno());
			personaNew.setCelular(0);
			personaNew.setDireccion("");
			personaNew.setNumero("");
			personaNew.setDpto("");
			personaNew.setEmail("");
			personaNew.setFax("");
			personaNew.setIdComuna(new Integer(Constants.ID_COMUNA_DEFAULT));
			personaNew.setTelefono("");
			this.personaDao.savePersona(personaNew);

			RepresentanteLegalVO repNew = new RepresentanteLegalVO();
			repNew.setIdRepresentanteLegal(personaNew.getIdPersona().intValue());
			repNew.setRepresentanteLegal(this.personaDao.getPersona(repNew.getIdRepresentanteLegal()));
			this.personaDao.saveRepLegal(repNew);
		} else
		{
			RepresentanteLegalVO rep = this.empresaDao.getRepresentanteLegal(empresa.getIdRepLegal().intValue());
			if (rep == null)
			{
				RepresentanteLegalVO repLeg = new RepresentanteLegalVO();
				repLeg.setIdRepresentanteLegal(personaRep.getIdPersona().intValue());
				repLeg.setRepresentanteLegal(this.personaDao.getPersona(repLeg.getIdRepresentanteLegal()));
				this.personaDao.saveRepLegal(repLeg);
			}
			personaRep.setNombres(personaRepLegal.getNombres());
			personaRep.setApellidoPaterno(personaRepLegal.getApellidoPaterno());
			personaRep.setApellidoMaterno(personaRepLegal.getApellidoMaterno());
		}
		this.empresaDao.guardaAdministrador(empresa.getIdAdmin().intValue());

		// Guarda la empresa
		this.empresaDao.modificaEmpresa(empresa);

		// Guarda la sucursal casa matriz
		this.empresaDao.saveSucursal(sucursal);

		// Guarda el grupo de convenios por defecto para poderselo asignar al convenio por defecto
		GrupoConvenioVO grupoConvenios = this.convenioDao.getGrupoConvenio(convenio.getIdGrupoConvenio());

		// Guarda el convenio por defecto para guardar los datos de la mutual
		ConvenioVO convenioNew = new ConvenioVO();
		convenioNew.setIdEmpresa(empresa.getIdEmpresa());
		convenioNew.setIdConvenio(Constants.ID_CONVENIO_DEFAULT);
		convenioNew.setIdGrupoConvenio(convenio.getIdGrupoConvenio());
		convenioNew.setIdMapaCod(grupoConvenios.getIdMapaCod());
		convenioNew.setIdMapaNomRem(grupoConvenios.getIdMapaNomRem());
		convenioNew.setIdMapaNomGra(grupoConvenios.getIdMapaNomGra());
		convenioNew.setIdMapaNomRel(grupoConvenios.getIdMapaNomRel());
		convenioNew.setIdMapaNomDep(grupoConvenios.getIdMapaNomDep());
		convenioNew.setIdOpcion(grupoConvenios.getIdOpcion());
		convenioNew.setCreado(new Date(hoy.getTime()));
		convenioNew.setUltimoUso(convenioNew.getCreado());
		convenioNew.setCalculoMovPersonal(Constants.OPC_CALC_MOV_PERSONAL);
		convenioNew.setDescripcion("CONVENIO EMP " + empresa.getIdEmpresa());
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
	 * modifica empresa
	 * 
	 * @param empresa
	 * @param personaRepLegal
	 * @param sucursal
	 * @param convenio
	 * @throws DaoException
	 */
	public void modificaEmpresa(EmpresaVO empresa, PersonaVO personaRepLegal, SucursalVO sucursal, ConvenioVO convenio) throws DaoException
	{
		java.util.Date hoy = new java.util.Date();
		// Guarda el representante legal
		
		PersonaVO personaRep = this.personaDao.getPersona(personaRepLegal.getIdPersona().intValue());
		if (personaRep == null)
		{
			logger.info("No encontro Persona: " + personaRepLegal.getIdPersona().intValue());
			PersonaVO personaNew = new PersonaVO();
			personaNew.setIdPersona(personaRepLegal.getIdPersona());
			personaNew.setNombres(personaRepLegal.getNombres());
			personaNew.setApellidoPaterno(personaRepLegal.getApellidoPaterno());
			personaNew.setApellidoMaterno(personaRepLegal.getApellidoMaterno());
			personaNew.setCelular(0);
			personaNew.setDireccion("");
			personaNew.setNumero("");
			personaNew.setDpto("");
			personaNew.setEmail("");
			personaNew.setFax("");
			personaNew.setIdComuna(new Integer(Constants.ID_COMUNA_DEFAULT));
			personaNew.setTelefono("");
			this.personaDao.savePersona(personaNew);

			RepresentanteLegalVO repNew = new RepresentanteLegalVO();
			repNew.setIdRepresentanteLegal(personaNew.getIdPersona().intValue());
			repNew.setRepresentanteLegal(this.personaDao.getPersona(repNew.getIdRepresentanteLegal()));
			this.personaDao.saveRepLegal(repNew);
		} else
		{
			RepresentanteLegalVO rep = this.empresaDao.getRepresentanteLegal(empresa.getIdRepLegal().intValue());
			if (rep == null)
			{
				RepresentanteLegalVO repLeg = new RepresentanteLegalVO();
				repLeg.setIdRepresentanteLegal(personaRep.getIdPersona().intValue());
				repLeg.setRepresentanteLegal(this.personaDao.getPersona(repLeg.getIdRepresentanteLegal()));
				this.personaDao.saveRepLegal(repLeg);
			}
			personaRep.setNombres(personaRepLegal.getNombres());
			personaRep.setApellidoPaterno(personaRepLegal.getApellidoPaterno());
			personaRep.setApellidoMaterno(personaRepLegal.getApellidoMaterno());
		}

		// Descomentados segun lo especificado por JP
		this.empresaDao.modificaEmpresa(empresa);
		this.empresaDao.guardaSucursal(sucursal);

		empresa.setIdCasaMatriz(sucursal.getIdSucursal().trim());
		
		//Guaradr convenio		
		//si es independiente el convenio es 1 por defecto
		ConvenioVO convenioVO = this.convenioDao.getConvenio(empresa.getIdEmpresa(), 1);
		
		convenioVO.setUltimoUso(new Date(hoy.getTime()));
		convenioVO.setIdActividad(convenio.getIdActividad());
		convenioVO.setIdCcaf(convenio.getIdCcaf());
		
		
		this.convenioDao.guardarConvenio(convenioVO);
		
				
		List listaConvenios = this.convenioDao.getConveniosEmpresa(false, empresa.getIdEmpresa());
		ConvenioVO convenioMod;
		for (Iterator it = listaConvenios.iterator(); it.hasNext();)
		{
			convenioMod = (ConvenioVO) it.next();
			convenioMod.setIdMutual(convenio.getIdMutual());
			convenioMod.setMutualNumeroAdherente(convenio.getMutualNumeroAdherente());
			convenioMod.setMutualCalculoIndividual(convenio.getMutualCalculoIndividual());
			convenioMod.setMutualTasaAdicional(convenio.getMutualTasaAdicional());
		}
	}

	/**
	 * actividad economica
	 * 
	 * @param idActividadEconomica
	 * @return
	 * @throws DaoException
	 */
	public ActividadEconomicaVO getActividadEconomica(int idActividadEconomica) throws DaoException
	{
		return this.empresaDao.getActividadEconomica(idActividadEconomica);
	}

	/**
	 * sucursales
	 * 
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
	 * 
	 * @param sucursal
	 * @throws DaoException
	 */
	public void guardaSucursal(SucursalVO sucursal) throws DaoException
	{
		this.empresaDao.guardaSucursal(sucursal);
	}

	/**
	 * elimina sucursal
	 * 
	 * @param idEmpresa
	 * @param idSucursal
	 * @throws DaoException
	 */
	public void borraSucursal(int idEmpresa, String idSucursal) throws DaoException
	{
		this.empresaDao.borraSucursal(idEmpresa, idSucursal);
	}

	/**
	 * save sucursal
	 * 
	 * @param sucursal
	 * @throws DaoException
	 */
	public void saveSucursal(SucursalVO sucursal) throws DaoException
	{
		this.empresaDao.saveSucursal(sucursal);
	}

	/**
	 * debe retornar: 'empresa', EmpresaVO 'empresas', list(LabelValueBean(formatRut, rut)) 'objEmpresas', Empresa (dentro lleva la lista de convenioVO con permisos)
	 * 
	 * @param idPersona
	 * @param rutEmpresa
	 * @return
	 */
	public HashMap getCombosPermisos(boolean cualquiera, int idPersona, int rutEmpresa, int idConvenio) throws DaoException
	{
		HashMap result = new HashMap();
		EmpresaVO empresa = null;
		List listaEmpresas = this.empresaDao.getEmpresasPermisos(idPersona);
		List comboEmpresas = new ArrayList();
		List objEmpresas = new ArrayList();
		if (listaEmpresas == null || listaEmpresas.size() == 0)// no encontro la empresa buscada: desabilitada? desabilitado como encargado? administrador?
		{
			logger.info("EmpresaMgr:getCombosPermisos: no encontro ninguna empresa en la que sea administrador ni encargado (lector o escritor). Retorna 'sin permisos'" +
					"\ncualquiera:" + cualquiera + ":persona:" + idPersona + ":rutEmpresa:" + rutEmpresa + ":idConvenio:" + idConvenio + "::");
			return null;
		}
		for (Iterator it = listaEmpresas.iterator(); it.hasNext();)
		{
			EmpresaVO empTmp = (EmpresaVO) it.next();
			List listaConvenios = this.convenioDao.getConveniosPermisos(idPersona, empTmp.getIdEmpresa(), true);
			if (listaConvenios == null || listaConvenios.size() == 0)
				continue;
			if (rutEmpresa == empTmp.getIdEmpresa())
			{
				boolean encontrado = false;
				for (Iterator it2 = listaConvenios.iterator(); it2.hasNext();)
					if (((ConvenioVO) it2.next()).getIdConvenio() == idConvenio)
					{
						encontrado = true;
						break;
					}
				if (!encontrado)
				{
					logger.info("EmpresaMgr:getCombosPermisos: Pidio info de una empresa/convenio para la que no tiene ningun permiso habilitado?. Retorna 'sin permisos'" +
							"\ncualquiera:" + cualquiera + ":persona:" + idPersona + ":rutEmpresa:" + rutEmpresa + ":idConvenio:" + idConvenio + "::");
					return null;
				}
				empresa = empTmp;
			}

			comboEmpresas.add(new LabelValueBean(Utils.formatRut(empTmp.getIdEmpresa()), "" + empTmp.getIdEmpresa()));
			Empresa emp = new Empresa(empTmp.getIdEmpresa(), empTmp.getRazonSocial().trim());
			emp.setConvenios(listaConvenios);
			objEmpresas.add(emp);
		}
		if (cualquiera && empresa == null)
			empresa = (EmpresaVO)listaEmpresas.get(0);
		if (empresa == null) // no encontro la empresa buscada: desabilitada? desabilitado como encargado? administrador?
		{
			logger.info("EmpresaMgr:getCombosPermisos: Pidio info de una empresa para la que no tiene ningun permiso habilitado?. Retorna 'sin permisos'" +
					"\ncualquiera:" + cualquiera + ":persona:" + idPersona + ":rutEmpresa:" + rutEmpresa + ":idConvenio:" + idConvenio + "::");
			return null;
		}
		if (comboEmpresas.size() == 0)// encontro empresas, pero ninguna tenia convenios habilitados??
		{
			logger.info("EmpresaMgr:getCombosPermisos: encontro empresas con permisos, pero ninguna tenia un convenio habilitado??" +
					"\ncualquiera:" + cualquiera + ":persona:" + idPersona + ":rutEmpresa:" + rutEmpresa + ":idConvenio:" + idConvenio + "::");
			return null;
		}
		result.put("empresa", empresa);
		result.put("empresas", comboEmpresas);
		result.put("objEmpresas", objEmpresas);
		return result;
	}


	/**
	 * Borra CRC de todas las nóminas asociadas a una empresa.
	 * 
	 * @param idEmpresa
	 * @throws DaoException
	 */
	public void borraCRCporEmpresa(int idEmpresa) throws DaoException
	{
		this.nominaDao.borraCRCporEmpresa(idEmpresa);
	}

	/**
	 * Borra CRC de todas las nóminas asociadas a una empresa y un convenio específicos.
	 * 
	 * @param idConvenio
	 * @param idEmpresa
	 * @throws DaoException
	 */
	public void borraCRCporEmpresa(int idConvenio, int idEmpresa) throws DaoException
	{
		this.nominaDao.borraCRCporEmpresa(idConvenio, idEmpresa);
	}

	public List getListaEmpresas(int rutBuscar, String razonSocial) throws DaoException 
	{
		return this.empresaDao.getListaEmpresas(rutBuscar, razonSocial);
	}
	
	public List getListTipoEmpresas(Set arrayRutEmpresa, String tipoEmpresa) throws DaoException{
	
		return this.empresaDao.getListTipoEmpresas(arrayRutEmpresa, tipoEmpresa);
	}
}
