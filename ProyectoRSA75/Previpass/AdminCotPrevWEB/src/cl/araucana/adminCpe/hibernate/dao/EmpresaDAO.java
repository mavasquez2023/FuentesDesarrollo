package cl.araucana.adminCpe.hibernate.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ActividadEconomicaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RepresentanteLegalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.23
 */
public class EmpresaDAO
{
	private static Logger log = Logger.getLogger(EmpresaDAO.class);
	Session session;
	PersonaDAO personaDao;

	public EmpresaDAO(Session session)
	{
		this.session = session;
		this.personaDao = new PersonaDAO(session);
	}

	/**
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresaGet(int rutEmpresa) throws DaoException
	{

		try
		{
			// log.info("EmpresaDAO:getEmpresaGet");
			return (EmpresaVO) this.session.get(EmpresaVO.class, new Integer(rutEmpresa));
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO.getEmpresaGet(" + rutEmpresa + ")");
			throw new DaoException("Problemas en getEmpresaGet", ex);
		}
	}

	/**
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresa(int rutEmpresa) throws DaoException
	{
		try
		{
			// log.info("EmpresaDAO:getEmpresa");
			EmpresaVO empresa = (EmpresaVO) this.session.get(EmpresaVO.class, new Integer(rutEmpresa));
			if (empresa == null)
				throw new Exception("No se obtuvo empresa rut: " + rutEmpresa);

			return empresa;
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO.getEmpresa(" + rutEmpresa + ")");
			throw new DaoException("Problemas en getEmpresa", ex);
		}
	}
	/**
	 * Retorna la empresa segun su rut si no encuentra este devuelve null
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresaSIS(int rutEmpresa) throws DaoException
	{
		try
		{
			EmpresaVO empresa = (EmpresaVO) this.session.get(EmpresaVO.class, new Integer(rutEmpresa));

			return empresa;
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO.getEmpresa(" + rutEmpresa + ")");
			throw new DaoException("Problemas en getEmpresa", ex);
		}
	}
	/**
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresaDespliegue(int rutEmpresa) throws DaoException
	{
		try
		{
			log.debug("EmpresaDAO:getEmpresaDespliegue:" + rutEmpresa + "::");
			EmpresaVO empresa = null;
			List params = new ArrayList();
			StringBuffer hqlQuery = new StringBuffer("from EmpresaVO em  ");
			hqlQuery.append(" where (cast(cast(em.idEmpresa as integer) as string)) like ?");
			params.add(rutEmpresa + "%");
			Query query = this.session.createQuery(hqlQuery.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				query.setParameter(par++, iter.next());
			log.info("query busqueda:" + hqlQuery.toString() + "::");

			ArrayList result = (ArrayList) query.list();
			if (!result.isEmpty())
				empresa = (EmpresaVO) (result.get(0));// this.session.get(EmpresaVO.class, new Integer(rutEmpresa));
			return empresa;
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO.getEmpresa(" + rutEmpresa + ")", ex);
			throw new DaoException("Problemas en getEmpresa", ex);
		}
	}

	/**
	 * retorna lista de empresas (ordenadas por rut empresa) administradas por la persona identificada por idPersona, sin importar si el admin esta habilitado, o si la empresa lo esta
	 * 
	 * @param idPersona
	 *            identificador del administrador
	 * @return lista empresas administradas
	 * @throws DaoException
	 */
	public List getEmpresasAdministradas(int idPersona) throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:getEmpresasAdministradas:" + idPersona + "::");
			return this.session.createCriteria(EmpresaVO.class).add(Restrictions.eq("idAdmin", new Long(idPersona))).addOrder(Order.asc("idEmpresa")).list();
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO.getEmpresasAdministradas:" + idPersona + "::");
			throw new DaoException("Problemas en getEmpresasAdministradas:" + idPersona + "::", ex);
		}
	}

	/**
	 * retorna lista de empresas (ordenadas por rut empresa) que no son administradas por la persona identificada por idPersona, sin importar si el admin esta habilitado, o si la empresa lo esta,
	 * filtrando por rutEmpresa y/o razon social
	 * 
	 * @param idPersona
	 *            identificador del administrador
	 * @return lista empresas no administradas
	 * @throws DaoException
	 */
	public List getEmpresasNoAdministradas(int idPersona, int rutEmpresa, String razonSocial) throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:getEmpresasNoAdministradas:" + idPersona + ":rutEmpresa:" + rutEmpresa + ":razonSocial:" + razonSocial + "::");
			Criteria crit = this.session.createCriteria(EmpresaVO.class).add(Restrictions.ne("idAdmin", new Long(idPersona)));
			if (rutEmpresa > 0)
				crit.add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa)));
			if (!razonSocial.equals(""))
				crit.add(Restrictions.ilike("razonSocial", "%" + razonSocial + "%"));
			return crit.addOrder(Order.asc("idEmpresa")).list();
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO.getEmpresasNoAdministradas:" + idPersona + ":rutEmpresa:" + rutEmpresa + ":razonSocial:" + razonSocial + "::");
			throw new DaoException("Problemas en getEmpresasNoAdministradas:" + idPersona + ":rutEmpresa:" + rutEmpresa + ":razonSocial:" + razonSocial + "::", ex);
		}
	}

	/**
	 * 
	 * @return lista empresas
	 * @throws DaoException
	 */
	public List getListaEmpresas() throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:getListaEmpresas");
			Criteria crit = this.session.createCriteria(EmpresaVO.class).add(Restrictions.not(Restrictions.eq("idEmpresa", new Integer(0))));
			return crit.addOrder(Order.asc("idEmpresa")).list();
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO.getListaEmpresas()");
			throw new DaoException("Problemas en getListaEmpresas", ex);
		}
	}

	/**
	 * 
	 * @param rutBuscar
	 * @param razSocBuscar
	 * @param nombreGrupoConvenio
	 * @param codigoGrupoConvenio
	 * @return lista empresas
	 * @throws DaoException
	 */
	public List getListaEmpresas(int rutBuscar, String razSocBuscar, String nombreGrupoConvenio, int codigoGrupoConvenio) throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:getListaEmpresas:rutBuscar:" + rutBuscar + ":razSocBuscar:" + razSocBuscar + ":nombreGrupoConvenio:" + nombreGrupoConvenio + ":codigoGrupoConvenio:"
					+ codigoGrupoConvenio + "::");
			if (!nombreGrupoConvenio.equals("") || codigoGrupoConvenio > 0)
				return getEmpresasPorGruposConvenio(nombreGrupoConvenio, codigoGrupoConvenio);

			List params = new ArrayList();
			StringBuffer hqlQuery = new StringBuffer("from EmpresaVO e where e.idEmpresa !=" + new Integer(Constants.RUT_EMPRESA_FALSA));
			if (rutBuscar > 0)
			{
				hqlQuery.append(" and cast(cast(e.idEmpresa as integer) as string) like ?");
				params.add(rutBuscar + "%");
			}
			if (!razSocBuscar.equals(""))
			{
				hqlQuery.append(" and e.razonSocial like ?");
				String tmp = "%" + Utils.transforToDB(razSocBuscar) + "%";
				params.add(tmp);
				if (tmp.indexOf('\\') != -1)
					hqlQuery.append(" ESCAPE '\\'");
			}
 
			hqlQuery.append(" order by e.idEmpresa");
			Query query = this.session.createQuery(hqlQuery.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				query.setString(par++, (String)iter.next());
			log.info("query busqueda:" + hqlQuery.toString() + "::");
			List l = query.list();
			log.info("resultado busqueda empresas:" + l.size() + "::");
			return l;

		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:getListaEmpresas()");
			throw new DaoException("Problemas en getListaEmpresas", ex);
		}
	}

	/**
	 * 
	 * @param nombreGrupoConvenio
	 * @param codigoGrupoConvenio
	 * @return lista empresas grupo convenio
	 * @throws Exception 
	 */
	public List getEmpresasPorGruposConvenio(String nombreGrupoConvenio, int codigoGrupoConvenio) throws Exception
	{
		log.info("EmpresaDAO:getEmpresasPorGruposConvenio");
		List listaGrupoConvenio = null;
		Set idsGrupos = new HashSet();
		Set idsEmpresas = new HashSet();
		List result = new ArrayList();

		List listaConvenios = null;
		StringBuffer hqlQuery = new StringBuffer("from GrupoConvenioVO gc WHERE idGrupoConvenio != 0 ");

		if (!nombreGrupoConvenio.trim().equals(""))
		{
			String tmp = Utils.transforToDB(nombreGrupoConvenio);
			hqlQuery.append(" AND nombre like '%" + tmp + "%'");
			if (tmp.indexOf('\\') != -1)
				hqlQuery.append(" ESCAPE '\\'");
		}
		if (codigoGrupoConvenio > 0)
			hqlQuery.append(" AND idGrupoConvenio = " + codigoGrupoConvenio);
		Query query = this.session.createQuery(hqlQuery.toString());
		listaGrupoConvenio = query.list();

		// Recorremos lista de grupos de convenios encontrados
		if (listaGrupoConvenio.size() > 0)
		{
			log.info("\tresultado idsGrupos:");
			for (Iterator it = listaGrupoConvenio.iterator(); it.hasNext();)
			{
				GrupoConvenioVO grupoConvenio = (GrupoConvenioVO) it.next();
				idsGrupos.add(new Integer(grupoConvenio.getIdGrupoConvenio()));
				log.info("\t\tidsGrupos:" + grupoConvenio.getIdGrupoConvenio() + "::");
			}
			Criteria crit3 = this.session.createCriteria(ConvenioVO.class).add(Restrictions.ne("idConvenio", new Integer(0)));
			if (!idsGrupos.isEmpty())
			{
				listaConvenios = crit3.add(Restrictions.in("idGrupoConvenio", idsGrupos)).list();
				// Recorremos convenios por grupo de convenio
				log.info("\tresultado idsConvenios:");
				for (Iterator it2 = listaConvenios.iterator(); it2.hasNext();)
				{
					ConvenioVO convenio = (ConvenioVO) it2.next();
					idsEmpresas.add(new Integer(convenio.getIdEmpresa()));
					log.info("\t\tidsEmpresa:" + convenio.getIdEmpresa() + "::");
				}
				if (!idsEmpresas.isEmpty())
					result = this.session.createCriteria(EmpresaVO.class).add(Restrictions.ne("idEmpresa", new Integer(Constants.RUT_EMPRESA_FALSA))).add(Restrictions.in("idEmpresa", idsEmpresas))
							.addOrder(Order.asc("idEmpresa")).list();
				else
					return new ArrayList();
			}
		}
		return result;
	}

	/**
	 * actuliza empresa
	 * 
	 * @param empresa
	 * @throws DaoException
	 */
	public void modificaEmpresa(EmpresaVO empresa) throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:modificaEmpresa");
			this.session.save(empresa);
			if (empresa == null)
				throw new Exception("No se modifico empresa : " + empresa);
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO.modificaEmpresa(" + empresa.getIdEmpresa() + ")");
			throw new DaoException("Problemas en getEmpresa", ex);
		}
	}

	/**
	 * guarda empresa
	 * 
	 * @param empresa
	 * @throws DaoException
	 */
	public void guardaEmpresa(EmpresaVO empresa) throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:guardaEmpresa");
			this.session.save(empresa);
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO.guardaEmpresa(" + empresa.getIdEmpresa() + ")");
			throw new DaoException("Problemas en guardaEmpresa", ex);
		}
	}

	/**
	 * guarda sucursal
	 * 
	 * @param sucursal
	 * @throws DaoException
	 */
	public void guardaSucursal(SucursalVO sucursal) throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:guardaSucursal");
			this.session.merge(sucursal);
			this.session.flush();
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:guardaSucursal: " + ex);
			throw new DaoException("Problemas EmpresaDAO.guardaSucursal::", ex);
		}
	}

	/**
	 * guarda sucursal
	 * 
	 * @param sucursal
	 * @throws DaoException
	 */
	public void saveSucursal(SucursalVO sucursal) throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:saveSucursal");
			sucursal.setIdSucursal(sucursal.getIdSucursal().toUpperCase());
			this.session.save(sucursal);
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:saveSucursal: " + ex);
			throw new DaoException("Problemas EmpresaDAO.saveSucursal::", ex);
		}
	}

	/**
	 * 
	 * @param rutEmpresa
	 * @return lista sucursales
	 * @throws DaoException
	 */
	public List getSucursales(int rutEmpresa) throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:getSucursales");
			List lista = this.session.createCriteria(SucursalVO.class).add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa))).list();
			SucursalVO sucursal;
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				sucursal = (SucursalVO) it.next();
				sucursal.setIdSucursal(sucursal.getIdSucursal().trim());
			}
			return lista;
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:getSucursales:" + ex);
			throw new DaoException("Problemas obteniendo sucursales por empresa rut:" + rutEmpresa + "::", ex);
		}
	}

	/**
	 * 
	 * @param rutEmpresa
	 * @return lista sucursales
	 * @throws DaoException
	 */
	public List getSucursales(String rutEmpresa) throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:getSucursales");
			Criteria crit = this.session.createCriteria(SucursalVO.class);
			List result = crit.add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa))).list();
			for (Iterator it = result.iterator(); it.hasNext();)
			{
				SucursalVO suc = (SucursalVO) it.next();
				suc.setIdSucursal(suc.getIdSucursal().trim());
			}
			return result;
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:getSucursales:" + ex);
			throw new DaoException("Problemas obteniendo sucursales por empresa rut:" + rutEmpresa + "::", ex);
		}
	}

	/**
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public HashMap getSucursalesHash(String rutEmpresa) throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:getSucursalesHash");
			Criteria crit = this.session.createCriteria(SucursalVO.class);
			List lista = crit.add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa))).list();
			if (lista != null)
			{
				HashMap result = new HashMap();
				for (Iterator it = lista.iterator(); it.hasNext();)
				{
					SucursalVO sucursal = (SucursalVO) it.next();
					result.put("" + sucursal.getIdSucursal().trim(), sucursal);
				}
				return result;
			}
			throw new DaoException("EmpresaDAO:getSucursalesHash No se encontro ninguna sucursal para la empresa:" + rutEmpresa);
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:getSucursales:" + ex);
			throw new DaoException("Problemas obteniendo sucursales por empresa rut:" + rutEmpresa + "::", ex);
		}
	}

	/**
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresaCasaMatriz(int rutEmpresa) throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:getEmpresaCasaMatriz");
			EmpresaVO empresa = (EmpresaVO) this.session.get(EmpresaVO.class, new Integer(rutEmpresa));
			if (empresa != null)
			{
				SucursalVO casaMatriz = (SucursalVO) this.session.get(SucursalVO.class, new SucursalVO(rutEmpresa, empresa.getIdCasaMatriz()));
				if (casaMatriz != null)
				{
					empresa.setCasaMatriz(casaMatriz);
					return empresa;
				}
				throw new DaoException("getEmpresaCasaMatriz: empresa sin casa matriz?? rut:" + rutEmpresa + "::");
			}
			throw new DaoException("getEmpresaCasaMatriz: no existe empresa?? rut:" + rutEmpresa + "::");
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:getEmpresaCasaMatriz:" + ex);
			throw new DaoException("Problemas obteniendo getEmpresaCasaMatriz rut:" + rutEmpresa + "::", ex);
		}
	}

	/**
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresaSucursales(int rutEmpresa) throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:getEmpresaSucursales");
			EmpresaVO empresa = (EmpresaVO) this.session.load(EmpresaVO.class, new Integer(rutEmpresa));
			if (empresa != null)
			{
				List lista = this.session.createCriteria(SucursalVO.class).add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa))).list();
				if (lista != null && lista.size() > 0)
				{
					empresa.setSucursales(lista);
					return empresa;
				}
				throw new DaoException("getEmpresaSucursales: empresa sin ninguna sucursal?? (al menos deberia tener casa matriz) rut:" + rutEmpresa + "::");
			}
			throw new DaoException("getEmpresaSucursales: no existe empresa?? rut:" + rutEmpresa + "::");
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:getEmpresaSucursales:" + ex);
			throw new DaoException("Problemas obteniendo getEmpresaSucursales rut:" + rutEmpresa + "::", ex);
		}
	}

	/**
	 * 
	 * @param idRepLegal
	 * @return
	 * @throws DaoException
	 */
	public RepresentanteLegalVO getRepresentanteLegal(int idRepLegal) throws DaoException
	{
		try
		{
			log.info("EmpresaDAO:getRepresentanteLegal:" + idRepLegal + "::");
			return (RepresentanteLegalVO) this.session.get(RepresentanteLegalVO.class, new Integer(idRepLegal));
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:getRepresentanteLegal:" + ex);
			throw new DaoException("Problemas obteniendo representante legal idRepLegal:" + idRepLegal + "::", ex);
		}
	}

	/**
	 * guarda representante legal
	 * 
	 * @param personaRepLegal
	 * @throws DaoException
	 */
	public void guardaRepresentanteLegal(PersonaVO personaRepLegal) throws DaoException
	{
		try
		{
			RepresentanteLegalVO representante = (RepresentanteLegalVO) this.session.get(RepresentanteLegalVO.class, personaRepLegal.getIdPersona());
			PersonaVO persona = (PersonaVO) this.session.get(PersonaVO.class, personaRepLegal.getIdPersona());
			if (persona == null)
				this.session.save(personaRepLegal);
			else
			{
				persona.setNombres(personaRepLegal.getNombres());
				persona.setApellidoPaterno(personaRepLegal.getApellidoPaterno());
				persona.setApellidoMaterno(personaRepLegal.getApellidoMaterno());
				this.session.merge(persona);
			}
			if (representante == null)
			{
				RepresentanteLegalVO r = new RepresentanteLegalVO(personaRepLegal.getIdPersona().intValue(), personaRepLegal);
				this.session.save(r);
			}
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:guardaRepresentanteLegal: " + ex);
			throw new DaoException("Problemas en EmpresaDAO:guardaRepresentanteLegal, idRepLegal: " + personaRepLegal.getIdPersona().intValue() + "::", ex);
		}
	}

	/**
	 * guarda administrador
	 * 
	 * @param personaAdmin
	 * @throws DaoException
	 */
	public void guardaAdministrador(PersonaVO personaAdmin) throws DaoException
	{
		try
		{
			AdministradorVO admin = (AdministradorVO) this.session.get(AdministradorVO.class, personaAdmin.getIdPersona());
			PersonaVO persona = (PersonaVO) this.session.get(PersonaVO.class, personaAdmin.getIdPersona());
			if (persona == null)
				this.session.save(personaAdmin);
			else
			{
				persona.setNombres(personaAdmin.getNombres());
				persona.setApellidoPaterno(personaAdmin.getApellidoPaterno());
				persona.setApellidoMaterno(personaAdmin.getApellidoMaterno());
				this.session.merge(persona);
			}
			if (admin == null)
			{
				AdministradorVO a = new AdministradorVO(personaAdmin.getIdPersona().intValue(), personaAdmin, Constants.COD_HABILITACION_ADMIN);
				this.session.save(a);
			}
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:guardaAdministrador: " + ex);
			throw new DaoException("Problemas en EmpresaDAO:guardaAdministrador, idRepLegal: " + personaAdmin.getIdPersona().intValue() + "::", ex);
		}
	}

	/**
	 * 
	 * @param idAdmin
	 * @return
	 * @throws DaoException
	 */
	public AdministradorVO getAdministrador(int idAdmin) throws DaoException
	{
		try
		{
			return (AdministradorVO) this.session.get(AdministradorVO.class, new Integer(idAdmin));
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:getAdministrador:" + ex);
			throw new DaoException("Problemas obteniendo administrador idAdmin:" + idAdmin + "::", ex);
		}
	}

	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getActividadesEconomicas() throws DaoException
	{
		try
		{
			return this.session.createCriteria(ActividadEconomicaVO.class).add(Restrictions.ne("idActividad", new Integer(Constants.ID_RUBRO_DEFAULT))).list();
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:getActividadesEconomicas:" + ex);
			throw new DaoException("Problemas obteniendo Actividades Economicas::", ex);
		}
	}

	/**
	 * 
	 * @param idActividadEconomica
	 * @return
	 * @throws DaoException
	 */
	public ActividadEconomicaVO getActividadEconomica(int idActividadEconomica) throws DaoException
	{
		try
		{
			return (ActividadEconomicaVO) this.session.load(ActividadEconomicaVO.class, new Integer(idActividadEconomica));
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:getActividadEconomica:" + ex);
			throw new DaoException("Problemas obteniendo Actividad Economica id: " + idActividadEconomica + "::", ex);
		}
	}

	/**
	 * 
	 * @param idEmpresa
	 * @param idSucursal
	 * @return
	 * @throws DaoException
	 */
	public SucursalVO getSucursal(int idEmpresa, String idSucursal) throws DaoException
	{
		try
		{
			SucursalVO sucursal = new SucursalVO(idEmpresa, Utils.rellena(6, " ", idSucursal.trim()));
			return (SucursalVO) this.session.get(SucursalVO.class, sucursal);
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:getSucursal:" + ex);
			throw new DaoException("Problemas obteniendo sucursal rutEmpresa: " + idEmpresa + ", idSucursal: \"" + idSucursal + "\"::", ex);
		}
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
		try
		{
			SucursalVO sucursal = this.getSucursal(idEmpresa, idSucursal);
			this.session.delete(sucursal);
			this.session.flush();
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:borraSucursal:" + ex);
			throw new DaoException("Problemas borrando sucursal rutEmpresa: " + idEmpresa + ", idSucursal: \"" + idSucursal + "\"::", ex);
		}
	}

	/**
	 * actualiza sucursal
	 * 
	 * @param empresa
	 * @throws DaoException
	 */
	public void updateEmpresa(EmpresaVO empresa) throws DaoException
	{
		try
		{
			this.session.update(empresa);
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:updateEmpresa:" + ex);
			throw new DaoException("Problemas updateando empresa: " + empresa.getIdEmpresa() + "::", ex);
		}
	}

	/**
	 * 
	 * @param rut
	 * @return
	 * @throws DaoException
	 */
	public PersonaVO getRepresentante(long rut) throws DaoException
	{
		log.info("EmpresaDAO:getRepresentante:" + rut + "::");
		PersonaVO repLegalP = this.personaDao.getRepresentante(rut);
		return repLegalP;
	}
	/**
	 * 
	 * @param rutEmpresa
	 * @paramtipoEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getListTipoEmpresas(int rutEmpresa, String tipoEmpresa) throws DaoException
	{

		try
		{
			List listEmpresa = this.session.createCriteria(EmpresaVO.class).add(Restrictions.eq("idEmpresa",new Integer(rutEmpresa))).add(Restrictions.like("tipo", tipoEmpresa)).list();
			
			return listEmpresa; 
			
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO.getListTipoEmpresas(" + rutEmpresa + ")");
			throw new DaoException("Problemas en getListTipoEmpresas", ex);
		}
	}
	
	/**
	 * 
	 * @param rutEmpresa
	 * @throws DaoException
	 */
	public List getListTipoEmpresas(String tipoEmpresa) throws DaoException
	{

		try
		{
			List listEmpresa = this.session.createCriteria(EmpresaVO.class).add(Restrictions.like("tipo", tipoEmpresa)).list();
			
			return listEmpresa; 
			
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO.getListTipoEmpresas(" + tipoEmpresa + ")");
			throw new DaoException("Problemas en getListTipoEmpresas", ex);
		}
	}
}
