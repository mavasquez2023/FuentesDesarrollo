package cl.araucana.cp.hibernate.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
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
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RepresentanteLegalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
 * @(#) EmpresaDao.java 1.33 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.33
 */
public class EmpresaDAO
{
	private static Logger logger = Logger.getLogger(EmpresaDAO.class);
	Session session;
	PersonaDAO personaDao;

	public EmpresaDAO(Session session)
	{
		this.session = session;
		this.personaDao = new PersonaDAO(session);
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
		try
		{
			EmpresaVO empresa = (EmpresaVO) this.session.get(EmpresaVO.class, new Integer(rutEmpresa));
			if (empresa == null)
				throw new Exception("No se obtuvo empresa rut: " + rutEmpresa);

			return empresa;
		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO.getEmpresa(" + rutEmpresa + ")");
			throw new DaoException("Problemas en getEmpresa", ex);
		}
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
		try
		{
			return (EmpresaVO) this.session.get(EmpresaVO.class, new Integer(rutEmpresa));
		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO.getEmpresaGet(" + rutEmpresa + ")");
			throw new DaoException("Problemas en getEmpresaGet", ex);
		}
	}

	/**
	 * modifica empresa
	 * 
	 * @param empresa
	 * @throws DaoException
	 */
	public void modificaEmpresa(EmpresaVO empresa) throws DaoException
	{
		try
		{
			logger.info(ToStringBuilder.reflectionToString(empresa, ToStringStyle.MULTI_LINE_STYLE));
			this.session.flush();
			logger.info("EmpresaDAO:modificaEmpresa");

			if (empresa == null)
				throw new Exception("No se modifico empresa == null:");

			EmpresaVO emp = (EmpresaVO) this.session.get(EmpresaVO.class, new Integer(empresa.getIdEmpresa()));
			if (emp == null)
			{
				logger.debug("save");
				this.session.save(empresa);
			} else
			{
				logger.debug("merge");
				/*ConvenioMgr convenioMgr = new ConvenioMgr(this.session);
				List lstConvenios = convenioMgr.getConveniosEmpresa(empresa.getIdEmpresa());
				if (lstConvenios.size() > 0) {
					for (Iterator it = lstConvenios.iterator(); it.hasNext(); ) {
						
					}
				}*/
				this.session.merge(empresa);
			}
			this.session.flush();
			logger.debug("despues flush empresa");
		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO.modificaEmpresa(" + empresa.getIdEmpresa() + ")");
			throw new DaoException("Problemas en getEmpresa", ex);
		}
	}

	/**
	 * representante
	 * 
	 * @param rut
	 * @return
	 * @throws DaoException
	 */
	public PersonaVO getRepresentante(long rut) throws DaoException
	{
		logger.info("EmpresaDAO:getRepresentante:" + rut + "::");
		PersonaVO repLegalP = this.personaDao.getRepresentante(rut);
		return repLegalP;
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
			logger.info("EmpresaDAO:guardaSucursal");
			this.session.merge(sucursal);
			this.session.flush();
		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO:guardaSucursal: " + ex);
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
			logger.info("EmpresaDAO:saveSucursal antes flush");
			this.session.flush();
			logger.info("EmpresaDAO:saveSucursal");
			logger.debug("A punto de guardar sucursal: " + sucursal.toString());
			this.session.save(sucursal);
			this.session.flush();
		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO:saveSucursal: " + ex);
			throw new DaoException("Problemas EmpresaDAO.saveSucursal::", ex);
		}
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
		try
		{
			logger.info("EmpresaDAO:getSucursales");
			List lista = this.session.createCriteria(SucursalVO.class).add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa))).addOrder(Order.asc("nombre")).list();
			SucursalVO sucursal;
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				sucursal = (SucursalVO) it.next();
				sucursal.setIdSucursal(sucursal.getIdSucursal().trim());
			}
			return lista;
		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO:getSucursales:" + ex);
			throw new DaoException("Problemas obteniendo sucursales por empresa rut:" + rutEmpresa + "::", ex);
		}
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
		try
		{
			logger.info("EmpresaDAO:getSucursales");
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
			logger.error("ERROR EmpresaDAO:getSucursales:" + ex);
			throw new DaoException("Problemas obteniendo sucursales por empresa rut:" + rutEmpresa + "::", ex);
		}
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
		try
		{
			logger.info("EmpresaDAO:getSucursalesHash");
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
			throw new DaoException("EmpresaDAO:getSucursalesHash No se encontro ninguna Direccion para el Independiente:" + rutEmpresa);
		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO:getSucursales:" + ex);
			throw new DaoException("Problemas obteniendo sucursales por empresa rut:" + rutEmpresa + "::", ex);
		}
	}

	/**
	 * empresa casa matriz
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresaCasaMatriz(int rutEmpresa) throws DaoException
	{
		try
		{
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
			logger.error("ERROR EmpresaDAO:getEmpresaCasaMatriz:" + ex);
			throw new DaoException("Problemas obteniendo getEmpresaCasaMatriz rut:" + rutEmpresa + "::", ex);
		}
	}

	/**
	 * empresa sucursales
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresaSucursales(int rutEmpresa) throws DaoException
	{
		try
		{
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
			logger.error("ERROR EmpresaDAO:getEmpresaSucursales:" + ex);
			throw new DaoException("Problemas obteniendo getEmpresaSucursales rut:" + rutEmpresa + "::", ex);
		}
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
		try
		{
			return (RepresentanteLegalVO) this.session.get(RepresentanteLegalVO.class, new Integer(idRepLegal));
		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO:getRepresentanteLegal:" + ex);
			throw new DaoException("Problemas obteniendo representante legal idRepLegal:" + idRepLegal + "::", ex);
		}
	}

	/**
	 * guarda administrador
	 * 
	 * @param idPersona
	 * @throws DaoException
	 */
	public void guardaAdministrador(int idPersona) throws DaoException
	{
		try
		{
			logger.error("EmpresaDAO:guardaAdministrador: " + idPersona + "::");
			AdministradorVO admin = (AdministradorVO) this.session.get(AdministradorVO.class, new Integer(idPersona));
			if (admin == null)
			{
				PersonaVO persona = (PersonaVO) this.session.get(PersonaVO.class, new Integer(idPersona));
				AdministradorVO a = new AdministradorVO(idPersona, persona, Constants.COD_HABILITACION_ADMIN);
				this.session.save(a);
				this.session.flush();
			}
		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO:guardaAdministrador: " + ex);
			throw new DaoException("Problemas en EmpresaDAO:guardaAdministrador, idAdmin: " + idPersona + "::", ex);
		}
	}

	/**
	 * guarda representante legal
	 * 
	 * @param repLegal
	 * @throws DaoException
	 */
	public void guardaRepresentanteLegal(RepresentanteLegalVO repLegal) throws DaoException
	{
		try
		{
			this.session.save(repLegal);
		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO:guardaRepresentanteLegal: " + ex);
			throw new DaoException("Problemas en EmpresaDAO:guardaRepresentanteLegal, idRepLegal: " + repLegal.getIdRepresentanteLegal() + "::", ex);
		}
	}

	/**
	 * actividades economicas
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
			logger.error("ERROR EmpresaDAO:getActividadesEconomicas:" + ex);
			throw new DaoException("Problemas obteniendo Actividades Economicas::", ex);
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
		try
		{
			return (ActividadEconomicaVO) this.session.load(ActividadEconomicaVO.class, new Integer(idActividadEconomica));
		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO:getActividadEconomica:" + ex);
			throw new DaoException("Problemas obteniendo Actividad Economica id: " + idActividadEconomica + "::", ex);
		}
	}

	/**
	 * sucursal
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
			logger.error("ERROR EmpresaDAO:getSucursal:" + ex);
			throw new DaoException("Problemas obteniendo sucursal rutEmpresa: " + idEmpresa + ", idSucursal: \"" + idSucursal + "\"::", ex);
		}
	}

	/**
	 * borrar sucursal
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
			logger.error("ERROR EmpresaDAO:borraSucursal:" + ex);
			throw new DaoException("Problemas borrando sucursal rutEmpresa: " + idEmpresa + ", idSucursal: \"" + idSucursal + "\"::", ex);
		}
	}

	/***************************************************************************************************************************************************************************************************
	 * listas con empresas filtrados por permisos
	 **************************************************************************************************************************************************************************************************/

	/**
	 * Retorna lista ordenada de empresas para las cuales el usuario asociado a 'login' posee algun tipo de permiso es decir, si es administrador o encargado escritor o lector
	 * 
	 * @param idPersona
	 *            identificador del usuario
	 * @return lista empresaVO
	 */
	public List getEmpresasPermisos(int idPersona) throws DaoException
	{
		try
		{
			logger.debug("EmpresaDAO:getEmpresasPermisos");
			Set conjuntoEmpresas = new HashSet(); // para que no se repitan

			// empresas habilitadas, para las cuales la persona es administrador (y esta habilitado)
			//TODO GMALLEA Se agrega la restriccion tipo para que retorne solo los Independientes
			AdministradorVO adminVO = (AdministradorVO) this.session.get(AdministradorVO.class, new Integer(idPersona));
			if (adminVO != null && adminVO.getHabilitado() == Constants.COD_HABILITACION_ADMIN)// La persona es administrador, y esta habilitado
					conjuntoEmpresas.addAll(this.session.createCriteria(EmpresaVO.class).add(Restrictions.eq("idAdmin", new Long(idPersona))).add(
						Restrictions.eq("habilitada", new Integer(Constants.COD_HABILITACION_EMPRESA))).add(Restrictions.like("tipo", Constants.TIPO_EMPRESA_INDEPENDIENTE)).list());
			//logger.debug("n emp admin:" + conjuntoEmpresas.size() + "::");

			// empresas habilitadas, para las cuales la persona es encargado (lector o escritor, y esta habilitado)
			EncargadoVO encVO = (EncargadoVO) this.session.get(EncargadoVO.class, new Integer(idPersona));
			if (encVO != null && encVO.getHabilitado() == Constants.COD_HABILITACION_ENCARGADO)// La persona es encargado, y esta habilitado
			{
				List listaEncargado = this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEncargado", new Integer(idPersona))).list();
				Set idsEmpresas = new HashSet();
				for (Iterator it = listaEncargado.iterator(); it.hasNext();)
					idsEmpresas.add(new Integer(((EncargadoConvenioVO) it.next()).getIdEmpresa()));
				logger.debug("n emp enc:" + idsEmpresas.size() + "::");
				//TODO GMALLEA Se agrega la restriccion tipo para que retorne solo los Independientes
				if (!idsEmpresas.isEmpty())
					conjuntoEmpresas.addAll(this.session.createCriteria(EmpresaVO.class).add(Restrictions.in("idEmpresa", idsEmpresas)).add(
							Restrictions.eq("habilitada", new Integer(Constants.COD_HABILITACION_EMPRESA))).add(Restrictions.like("tipo", Constants.TIPO_EMPRESA_INDEPENDIENTE)).list());
			}

			List listaEmpresas = new ArrayList(conjuntoEmpresas);
			Collections.sort(listaEmpresas);
			logger.debug("n emp:" + listaEmpresas.size() + "::");

			return listaEmpresas;
		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO:getEmpresasPermisos:persona:" + idPersona + ":", ex);
			throw new DaoException("Problemas obteniendo empresas con cualquier permiso para persona:" + idPersona + "::", ex);
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
	public List getListaEmpresas(int rutBuscar, String razonSocial) throws DaoException
	{
		try
		{
			logger.info("EmpresaDAO:getListaEmpresas:rut:" + rutBuscar + ":razonSocial:" + razonSocial + "::");

			List params = new ArrayList();
			StringBuffer hqlQuery = new StringBuffer("FROM EmpresaVO e WHERE e.idEmpresa !=" + new Integer(Constants.RUT_EMPRESA_FALSA));
			if (rutBuscar > 0)
			{
				hqlQuery.append(" AND CAST(CAST(e.idEmpresa AS integer) AS string) LIKE ?");
				params.add(rutBuscar + "%");
			}
			if (!razonSocial.equals(""))
			{
				hqlQuery.append(" AND e.razonSocial LIKE ?");
				String tmp = "%" + Utils.transforToDB(razonSocial) + "%";
				params.add(tmp);
				if (tmp.indexOf('\\') != -1)
					hqlQuery.append(" ESCAPE '\\'");
			}
 
			hqlQuery.append(" ORDER BY e.idEmpresa");
			Query query = this.session.createQuery(hqlQuery.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				query.setString(par++, (String)iter.next());
			logger.info("query busqueda:" + hqlQuery.toString() + "::");
			List l = query.list();
			logger.info("resultado busqueda empresas:" + l.size() + "::");
			return l;

		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO:getListaEmpresas()");
			throw new DaoException("Problemas en getListaEmpresas", ex);
		}
	}
	
	public List getListTipoEmpresas(Set arrayRutEmpresa, String tipoEmpresa) throws DaoException
	{

		try
		{
			List listEmpresa = this.session.createCriteria(EmpresaVO.class).add(Restrictions.in("idEmpresa",arrayRutEmpresa)).add(Restrictions.like("tipo", tipoEmpresa)).list();
			
			return listEmpresa;
			
		} catch (Exception ex)
		{
			logger.error("ERROR EmpresaDAO.getListTipoEmpresas");
			throw new DaoException("Problemas en getListTipoEmpresas", ex);
		}
	}
}
