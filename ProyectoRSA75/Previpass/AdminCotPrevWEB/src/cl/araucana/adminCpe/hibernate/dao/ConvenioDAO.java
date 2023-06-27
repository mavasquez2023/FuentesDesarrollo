package cl.araucana.adminCpe.hibernate.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NivelAccConvSucVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/**
 * @author ccostagliola
 * @author malvarez
 * 
 * @version 1.28
 */
public class ConvenioDAO
{
	private static Logger log = Logger.getLogger(ConvenioDAO.class);
	private Session session;

	public ConvenioDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public ConvenioVO getConvenioNoExcp(int idEmpresa, int idConvenio) throws DaoException
	{
		try
		{
			ConvenioVO convenio = new ConvenioVO(idEmpresa, idConvenio);
			return (ConvenioVO) this.session.get(ConvenioVO.class, convenio);
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getConvenio", ex);
			throw new DaoException("Error al leer convenio idEmpresa: " + idEmpresa + ", idConvenio: " + idConvenio, ex);
		}
	}

	/**
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public ConvenioVO getConvenio(int idEmpresa, int idConvenio) throws DaoException
	{
		try
		{
			// log.info("ConvenioDAO:getConvenio:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + "::");
			ConvenioVO convenio = new ConvenioVO(idEmpresa, idConvenio);
			convenio = (ConvenioVO) this.session.get(ConvenioVO.class, convenio);

			if (convenio == null)
				throw new Exception("No encontro convenio idConvenio: " + idConvenio + ", idEmpresa: " + idEmpresa);
			return convenio;
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getConvenio", ex);
			throw new DaoException("Error al leer convenio idEmpresa: " + idEmpresa + ", idConvenio: " + idConvenio, ex);
		}
	}

	/**
	 * 
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public GrupoConvenioVO getGrupoConvenio(int idGrupoConvenio) throws DaoException
	{
		try
		{
			// log.info("ConvenioDAO:getGrupoConvenio:idGrupoConvenio" + idGrupoConvenio + "::");
			GrupoConvenioVO grupo = (GrupoConvenioVO) this.session.get(GrupoConvenioVO.class, new Integer(idGrupoConvenio));
			return grupo;
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getGrupoConvenio", ex);
			throw new DaoException("Error al leer grupo convenio idGrupoConvenio: " + idGrupoConvenio + "::", ex);
		}
	}

	/**
	 * 
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public boolean isGrupoConvenioHabilitado(int idGrupoConvenio) throws DaoException
	{
		try
		{
			return this.session.createCriteria(GrupoConvenioVO.class).add(Restrictions.eq("habilitado", new Integer("1")))
						.add(Restrictions.eq("idGrupoConvenio", new Integer(idGrupoConvenio))).list().size()>0;
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:isGrupoConvenioHabilitado", ex);
			throw new DaoException("Error al leer grupo convenio idGrupoConvenio: " + idGrupoConvenio + "::", ex);
		}
	}
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaGruposConvenio() throws DaoException
	{
		try
		{
			// log.info("ConvenioDAO:getListaGruposConvenio::");
			return this.session.createCriteria(GrupoConvenioVO.class).addOrder(Order.asc("nombre")).list();
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getListaGruposConvenio", ex);
			throw new DaoException("Error al leer lista de grupos de convenios::", ex);
		}
	}
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaGruposConvenioActivos() throws DaoException
	{
		try
		{
			// log.info("ConvenioDAO:getListaGruposConvenio::");
			return this.session.createCriteria(GrupoConvenioVO.class).add(Restrictions.eq("habilitado", new Integer("1")))
						.addOrder(Order.asc("nombre")).list();
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getListaGruposConvenio", ex);
			throw new DaoException("Error al leer lista de grupos de convenios::", ex);
		}
	}

	/**
	 * 
	 * @param idGrupoConvenio
	 * @param nombreGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getListaGruposConvenio(Integer idGrupoConvenio, String nombreGrupoConvenio) throws DaoException
	{
		try
		{
			log.info("ConvenioDAO:getListaGruposConvenio::");
			StringBuffer hqlQuery = new StringBuffer("from GrupoConvenioVO gc");
			boolean flag = false;

			if (idGrupoConvenio != null)
			{
				flag = true;
				hqlQuery.append(" WHERE idGrupoConvenio = " + idGrupoConvenio);
			}
			if (nombreGrupoConvenio != null)
			{
				String tmp = "%" + Utils.transforToDB(nombreGrupoConvenio) + "%";
				if (!flag)
					hqlQuery.append(" WHERE");
				else
					hqlQuery.append(" AND");

				hqlQuery.append(" nombre like '%" + Utils.transforToDB(nombreGrupoConvenio) + "%'");
				if (tmp.indexOf('\\') != -1)
					hqlQuery.append(" ESCAPE '\\'");
			}
			return this.session.createQuery(hqlQuery.toString() + " ORDER BY nombre").list();
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getListaGruposConvenio", ex);
			throw new DaoException("Error al leer lista de grupos de convenios::", ex);
		}
	}

	/**
	 * 
	 * @param idCaja
	 * @return
	 * @throws DaoException
	 */
	public EntidadCCAFVO getCaja(int idCaja) throws DaoException
	{
		try
		{
			log.info("ConvenioDAO:getCaja:idCaja:" + idCaja + "::");
			EntidadCCAFVO caja = (EntidadCCAFVO) this.session.get(EntidadCCAFVO.class, new Integer(idCaja));

			if (caja == null)
				throw new Exception("No encontro caja idCaja: " + idCaja + "::");
			return caja;
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getCaja", ex);
			throw new DaoException("Error al cargar caja:" + idCaja + ":", ex);
		}
	}

	/**
	 * 
	 * @param idMutual
	 * @return
	 * @throws DaoException
	 */
	public EntidadMutualVO getMutual(int idMutual) throws DaoException
	{
		try
		{
			log.info("ConvenioDAO:getMutual:idMutual" + idMutual + "::");
			EntidadMutualVO mutual = (EntidadMutualVO) this.session.get(EntidadMutualVO.class, new Integer(idMutual));

			if (mutual == null)
				throw new Exception("No encontro caja idCaja: " + idMutual + "::");
			return mutual;
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getMutual", ex);
			throw new DaoException("Error al cargar caja:" + idMutual + ":", ex);
		}
	}

	/**
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getConveniosEmpresa(int idEmpresa) throws DaoException
	{
		try
		{//TODO cambiar para LA
			List params = new ArrayList();
			StringBuffer hqlQuery = new StringBuffer("from ConvenioVO Conv  ");
			hqlQuery.append(" where cast(Conv.idEmpresa as string) like ?");
			params.add(idEmpresa + "%");
			hqlQuery.append(" order by Conv.idGrupoConvenio,Conv.descripcion");
			Query query = this.session.createQuery(hqlQuery.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				query.setParameter(par++, iter.next());
			log.info("query busqueda:" + hqlQuery.toString() + "::");
			return query.list();
/*
			return this.session.createCriteria(ConvenioVO.class)
			.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa)))
			.addOrder(Order.asc("idGrupoConvenio"))
			.addOrder(Order.asc("descripcion"))
			.list();*/ 
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getConveniosEmpresa: ", ex);
			throw new DaoException("Error en ConvenioDAO:getConveniosEmpresa::", ex);
		}
	}

	/**
	 * 
	 * @param idGrupo
	 * @return
	 * @throws DaoException
	 */
	public List getConveniosGrupo(int idGrupo) throws DaoException
	{
		try
		{
			return this.session.createCriteria(ConvenioVO.class).add(Restrictions.eq("idGrupoConvenio", new Integer(idGrupo))).list();
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getConveniosGrupo:", ex);
			throw new DaoException("Error en ConvenioDAO:getConveniosGrupo::", ex);
		}
	}

	/**
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getGruposConveniosEmpresa(int idEmpresa) throws DaoException
	{
		try
		{
			log.info("ConvenioDAO:getGruposConveniosEmpresa: " + idEmpresa + "::");
			List convenios = this.session.createCriteria(ConvenioVO.class).add(Restrictions.eq("idEmpresa", new Integer(idEmpresa))).list();
			if (convenios.size() == 0)
				return new ArrayList();

			List idsGrupos = new ArrayList();
			for (Iterator it = convenios.iterator(); it.hasNext();)
				idsGrupos.add(new Integer(((ConvenioVO) it.next()).getIdGrupoConvenio()));
			if (!idsGrupos.isEmpty())
				return this.session.createCriteria(GrupoConvenioVO.class).add(Restrictions.in("idGrupoConvenio", idsGrupos)).list();
			return new ArrayList();

		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getGruposConveniosEmpresa:", ex);
			throw new DaoException("Error en ConvenioDAO:getGruposConveniosEmpresa::", ex);
		}
	}

	/**
	 * 
	 * @param convenio
	 * @throws DaoException
	 */
	public void guardarConvenio(ConvenioVO convenio) throws DaoException
	{
		try
		{
			log.info("ConvenioDAO:guardarConvenio::" + convenio + "::");

			log.info(ToStringBuilder.reflectionToString(convenio, ToStringStyle.MULTI_LINE_STYLE));
			this.session.save(convenio);
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:guardarConvenio:", ex);
			throw new DaoException("Error en ConvenioDAO:guardarConvenio::", ex);
		}
	}

	/**
	 * 
	 * @param opcProc
	 * @return
	 * @throws DaoException
	 */
	public Integer guardarOpcionProceso(OpcionProcVO opcProc) throws DaoException
	{
		try
		{
			log.info("ConvenioDAO:guardarOpcionProceso::");
			List l = this.session.createCriteria(OpcionProcVO.class).setProjection(Projections.max("idOpcion")).list();
			int oldId = ((Integer) l.get(0)).intValue();
			opcProc.setIdOpcion(oldId + 1);

			return (Integer) this.session.save(opcProc);
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:guardarOpcionProceso: ", ex);
			throw new DaoException("Error en ConvenioDAO:guardarOpcionProceso::", ex);
		}
	}

	/**
	 * 
	 * @param idOpcProc
	 * @return
	 * @throws DaoException
	 */
	public OpcionProcVO getOpcionProceso(int idOpcProc) throws DaoException
	{
		try
		{
			log.info("ConvenioDAO:getOpcionProceso::");
			return (OpcionProcVO) this.session.load(OpcionProcVO.class, new Integer(idOpcProc));
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getOpcionProceso: ", ex);
			throw new DaoException("Error en ConvenioDAO:getOpcionProceso::", ex);
		}
	}

	/**
	 * 
	 * @param gConvenio
	 * @return
	 * @throws DaoException
	 */
	public Integer guardarGrupoConvenio(GrupoConvenioVO gConvenio) throws DaoException
	{
		try
		{
			log.info("ConvenioDAO:guardarGrupoConvenio::");

			Integer id = (Integer) this.session.save(gConvenio);
			return id;
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:guardarGrupoConvenio: ", ex);
			throw new DaoException("Error en ConvenioDAO:guardarGrupoConvenio::", ex);
		}
	}

	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaOpcionesProcesos() throws DaoException
	{
		try
		{
			log.info("ConvenioDAO:getListaOpcionesProcesos::");
			return this.session.createCriteria(OpcionProcVO.class).addOrder(Order.asc("idOpcion")).list();
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getListaOpcionesProcesos: ", ex);
			throw new DaoException("Error en ConvenioDAO:getListaOpcionesProcesos::", ex);
		}
	}

	/**
	 * 
	 * @param idEmpresa
	 * @param codInicial
	 * @param codFinal
	 * @return
	 * @throws DaoException
	 */
	public boolean existeConvenioEnRango(int idEmpresa, int codInicial, int codFinal) throws DaoException
	{
		try
		{
			log.info("ConvenioDAO:existeConvenioEnRango::");
			return !this.session.createCriteria(ConvenioVO.class).add(Restrictions.eq("idEmpresa", new Integer(idEmpresa))).add(Restrictions.ge("idConvenio", new Integer(codInicial))).add(
					Restrictions.le("idConvenio", new Integer(codFinal))).list().isEmpty();
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:existeConvenioEnRango: ", ex);
			throw new DaoException("Error en ConvenioDAO:existeConvenioEnRango::", ex);
		}
	}

	/**
	 * 
	 * @param grupo
	 * @return
	 * @throws DaoException
	 */
	// metodo para obtenet todas las empresas asociadas a grupo de convenio
	public List getListaGruposConvenioPorEmpresaAgrupado(int grupo) throws DaoException
	{
		try
		{
			return this.session.createCriteria(ConvenioVO.class).setProjection(Projections.distinct(Projections.property("idEmpresa"))).add(Restrictions.eq("idGrupoConvenio", new Integer(grupo)))
			// .addOrder(Order.asc("nombre"))
					.list();
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getListaGruposConvenio", ex);
			throw new DaoException("Error al leer lista de grupos de convenios::", ex);
		}
	}

	/**
	 * 
	 * @param grupo
	 * @param empresa
	 * @return
	 * @throws DaoException
	 */
	// busca todos los convenios que tiene asociado una empresa atraves de su grupo de convenio
	public List getListaConveniosEmpresaPorGrup(int grupo, Integer empresa) throws DaoException
	{
		try
		{
			return this.session.createCriteria(ConvenioVO.class).add(Restrictions.eq("idGrupoConvenio", new Integer(grupo))).add(Restrictions.eq("idEmpresa", empresa)).addOrder(
					Order.asc("idConvenio")).list();
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getListaGruposConvenio", ex);
			throw new DaoException("Error al leer lista de grupos de convenios::", ex);
		}
	}

	/**
	 * 
	 * @param rutEmpre
	 * @return
	 * @throws DaoException
	 */
	public List getEncargadoGrupoConvenio(int rutEmpre) throws DaoException
	{
		try
		{
			List tmp = null;
			List retorno = new ArrayList();
			EncargadoConvenioVO encargadoConvenioVO;
			List params = new ArrayList();
			StringBuffer hqlQuery = new StringBuffer("from EncargadoConvenioVO encCon  ");
			//TODO cambiar para LA
			hqlQuery.append(" where (cast(cast(encCon.idEmpresa as integer) as string)) like ?");
			params.add(rutEmpre + "%");
			//hqlQuery.append(" where encCon.idEmpresa = ?");
			//params.add(new Integer(rutEmpre));

			Query query = this.session.createQuery(hqlQuery.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				query.setParameter(par++, iter.next());
			log.info("query busqueda:" + hqlQuery.toString() + "::");
			tmp = query.list();
			// tmp = this.session.createCriteria(EncargadoConvenioVO.class).list();
			this.session.flush();

			if (tmp.size() > 0)
			{
				for (Iterator itt = tmp.iterator(); itt.hasNext();)
				{
					encargadoConvenioVO = (EncargadoConvenioVO) itt.next();
					StringBuffer hqlQueryPersona = new StringBuffer("from PersonaVO p where p.idPersona =" + encargadoConvenioVO.getIdEncargado());
					PersonaVO per = (PersonaVO) (this.session.createQuery(hqlQueryPersona.toString()).list().get(0));
					if (per != null)
					{
						per.setRut(Utils.formatRut(per.getIdPersona().intValue()));
						List listaAcceso = this.session.createCriteria(NivelAccConvSucVO.class).add(Restrictions.eq("idNivelAcceso", new Integer(encargadoConvenioVO.getIdNivelAcceso()))).list();
						NivelAccConvSucVO niv = (NivelAccConvSucVO) listaAcceso.get(0);
						per.setNivelAcceso(niv.getNombre());
						retorno.add(per);
					} else
					{
						PersonaVO personanula = new PersonaVO();
						personanula.setNombres("No se encontro Dato");
						personanula.setRut(String.valueOf(encargadoConvenioVO.getIdEncargado()));
						retorno.add(personanula);
					}
				}
				return retorno;
			}
			return new ArrayList();
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getEncargadoGrupoConvenio", ex);
			throw new DaoException("Error al leer lista de grupos de convenios::", ex);
		}
	}

	/**
	 * 
	 * @param idLector
	 * @return
	 * @throws DaoException
	 */
	public LectorEmpresaVO getLectorEmpresa(Integer idLector) throws DaoException
	{
		try
		{
			LectorEmpresaVO lectorEmpresaVO;
			lectorEmpresaVO = (LectorEmpresaVO) this.session.get(LectorEmpresaVO.class, idLector);

			return lectorEmpresaVO;
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getEncargadoGrupoConvenio", ex);
			throw new DaoException("Error al leer lista de grupos de convenios::", ex);
		}
	}

	/**
	 * 
	 * @param rutEmpre
	 * @return
	 * @throws DaoException
	 */
	public List getLectorPlanillasEmpresa(int rutEmpre) throws DaoException
	{
		try
		{
			List retorno = new ArrayList();
			LectorEmpresaEmpresaVO lectorEmpresaVO;
			List tmp = this.session.createCriteria(LectorEmpresaEmpresaVO.class).add(Restrictions.eq("idEmpresa", new Integer(rutEmpre))).list();
			if (tmp.size() > 0)
			{
				for (Iterator itt = tmp.iterator(); itt.hasNext();)
				{
					lectorEmpresaVO = (LectorEmpresaEmpresaVO) itt.next();
					Integer idLector = new Integer(lectorEmpresaVO.getIdLectorEmpresa());
					LectorEmpresaVO lector = getLectorEmpresa(idLector);
					if (lector.getHabilitado() != 0)
						retorno.add(lectorEmpresaVO);
				}
			}
			return retorno;
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getEncargadoGrupoConvenio", ex);
			throw new DaoException("Error al leer lista de grupos de convenios::", ex);
		}
	}

	/**
	 * 
	 * @param rutEmpre
	 * @return
	 * @throws DaoException
	 */
	public List getLectorPlanillasConvenio(int rutEmpre) throws DaoException
	{
		try
		{
			List retorno = new ArrayList();
			LectorEmpresaConvenioVO lectorConvenioVO;
			List tmp = this.session.createCriteria(LectorEmpresaConvenioVO.class).add(Restrictions.eq("idEmpresa", new Integer(rutEmpre))).list();
			if (tmp.size() > 0)
			{
				for (Iterator itt = tmp.iterator(); itt.hasNext();)
				{
					lectorConvenioVO = (LectorEmpresaConvenioVO) itt.next();
					Integer idLector = new Integer(lectorConvenioVO.getIdLectorEmpresa());
					LectorEmpresaVO lector = getLectorEmpresa(idLector);
					if (lector.getHabilitado() != 0)
						retorno.add(lectorConvenioVO);
				}
			}
			return retorno;
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getEncargadoGrupoConvenio", ex);
			throw new DaoException("Error al leer lista de grupos de convenios::", ex);
		}
	}

	/**
	 * 
	 * @param rutEmpre
	 * @return
	 * @throws DaoException
	 */
	public List getLectorPlanillasSucursal(int rutEmpre) throws DaoException
	{
		try
		{
			List retorno = new ArrayList();
			LectorEmpresaSucursalVO lectorSucursalVO;
			List tmp = this.session.createCriteria(LectorEmpresaSucursalVO.class).add(Restrictions.eq("idEmpresa", new Integer(rutEmpre))).list();
			if (tmp.size() > 0)
			{
				for (Iterator itt = tmp.iterator(); itt.hasNext();)
				{
					lectorSucursalVO = (LectorEmpresaSucursalVO) itt.next();
					Integer idLector = new Integer(lectorSucursalVO.getIdLectorEmpresa());
					LectorEmpresaVO lector = getLectorEmpresa(idLector);
					if (lector.getHabilitado() != 0)
						retorno.add(lectorSucursalVO);
				}
			}
			return retorno;
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getEncargadoGrupoConvenio", ex);
			throw new DaoException("Error al leer lista de grupos de convenios::", ex);
		}
	}

	public List getLectorPlanillasGrupoConvenio(int grupo) throws DaoException
	{
		try
		{
			List retorno = new ArrayList();
			LectorEmpresaGrupoConvenioVO lectorGrupoConvenioVO;
			List tmp = this.session.createCriteria(LectorEmpresaGrupoConvenioVO.class).add(Restrictions.eq("idGrupoConvenio", new Integer(grupo))).list();
			if (tmp.size() > 0)
			{
				for (Iterator itt = tmp.iterator(); itt.hasNext();)
				{
					lectorGrupoConvenioVO = (LectorEmpresaGrupoConvenioVO) itt.next();
					Integer idLector = new Integer(lectorGrupoConvenioVO.getIdLectorEmpresa());
					LectorEmpresaVO lector = getLectorEmpresa(idLector);
					if (lector.getHabilitado() != 0)
						retorno.add(lectorGrupoConvenioVO);
				}
			}
			return retorno;
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getEncargadoGrupoConvenio", ex);
			throw new DaoException("Error al leer lista de grupos de convenios::", ex);
		}
	}

	/**
	 * 
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public int getNumEmpsHabilitadas(int idGrupoConvenio) throws DaoException
	{
		try
		{
			List result = new ArrayList();
			List listConvenios = this.session.createCriteria(ConvenioVO.class).add(Restrictions.eq("idGrupoConvenio", new Integer(idGrupoConvenio))).list();
			Set idsEmpresas = new HashSet();
			if (listConvenios.size() > 0)
			{
				for (Iterator it = listConvenios.iterator(); it.hasNext();)
					idsEmpresas.add(new Integer(((ConvenioVO) it.next()).getIdEmpresa()));
				if (!idsEmpresas.isEmpty())
					result = this.session.createCriteria(EmpresaVO.class).add(Restrictions.ne("idEmpresa", new Integer(Constants.RUT_EMPRESA_FALSA))).add(
							Restrictions.eq("habilitada", new Integer(Constants.COD_HABILITACION_EMPRESA))).add(Restrictions.in("idEmpresa", idsEmpresas)).list();
			}
			return result.size();
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getNumConvsHabilitados", ex);
			throw new DaoException("Error al leer lista de getNumConvsHabilitados::", ex);
		}
	}

	/**
	 * 
	 * @param idEmpresa
	 * @param nombreEmpresa
	 * @param idGrConvenio
	 * @param nombreGrConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getPermisosConvSucuEncargado(String idEmpresa, String nombreEmpresa, String idGrConvenio, String nombreGrConvenio) throws DaoException
	{
		try
		{
			StringBuffer hqlQuery = new StringBuffer("select c from ConvenioVO c where c.idConvenio != 0");
			List params = new ArrayList();
			if (idEmpresa != null && !"".equals(idEmpresa))
			{
				hqlQuery.append(" and c.idEmpresa = ?");
				params.add(new Integer(Utils.desFormatRut(idEmpresa)));
			}
			if (nombreEmpresa != null && !"".equals(nombreEmpresa))
			{
				hqlQuery.append(" and c.idEmpresa in (select e.idEmpresa from EmpresaVO e where e.idEmpresa != 0 and e.razonSocial like ?)");
				params.add("%" + nombreEmpresa + "%");
			}
			if (idGrConvenio != null && !"".equals(idGrConvenio))
			{
				hqlQuery.append(" and c.idGrupoConvenio = ?");
				params.add(new Integer(idGrConvenio));
			}
			if (nombreGrConvenio != null && !"".equals(nombreGrConvenio))
			{
				hqlQuery.append(" and c.idGrupoConvenio in (select gc.idGrupoConvenio from GrupoConvenioVO gc where gc.idGrupoConvenio != 0 and gc.nombre like ?)");
				params.add("%" + nombreGrConvenio + "%");
			}
			hqlQuery.append(" order by c.idEmpresa, c.idConvenio");
			Query query = this.session.createQuery(hqlQuery.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				query.setParameter(par++, iter.next());
			return query.list();
		} catch (Exception ex)
		{
			log.error("Ha ocurrido la siguiente excepcion en PersonaDAO.getListaPersonas:", ex);
			throw new DaoException("PersonaDAO.getListaPersonas: ", ex);
		}
	}

	/**
	 * 
	 * @param opcionProcesos
	 * @throws DaoException
	 */
	public void guardaOpcionProcesos(OpcionProcVO opcionProcesos) throws DaoException
	{
		try
		{
			log.info("ConvenioDAO:guardaOpcionProcesos:idOpcion" + opcionProcesos.getIdOpcion() + "::");
			OpcionProcVO op = (OpcionProcVO) this.session.get(OpcionProcVO.class, new Integer(opcionProcesos.getIdOpcion()));
			if (op == null)
				this.session.save(opcionProcesos);
			else
				this.session.merge(opcionProcesos);
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:guardaOpcionProcesos:", ex);
			throw new DaoException("Error al guardar opcion procesos idOpcion: " + opcionProcesos.getIdOpcion() + "::", ex);
		}
	}

	/**
	 * 
	 * @param idEmpresa
	 * @param nombreEmpresa
	 * @param idGrConvenio
	 * @param nombreGrConvenio
	 * @param idConvenio
	 * @param nombreConvenio
	 * @param orden
	 * @return
	 * @throws DaoException
	 */
	public List getPermisosRolLector(String idEmpresa, String nombreEmpresa, String idGrConvenio, String nombreGrConvenio, String idConvenio, String nombreConvenio, String orden) throws DaoException
	{
		try
		{
			StringBuffer hqlQuery = new StringBuffer("select c from ConvenioVO c where c.idConvenio != 0");
			List params = new ArrayList();
			if (idEmpresa != null && !"".equals(idEmpresa))
			{
				hqlQuery.append(" and c.idEmpresa = ?");
				params.add(new Integer(Utils.desFormatRut(idEmpresa)));
			}
			if (nombreEmpresa != null && !"".equals(nombreEmpresa))
			{
				hqlQuery.append(" and c.idEmpresa in (select e.idEmpresa from EmpresaVO e where e.idEmpresa != 0 and e.razonSocial like ?)");
				params.add("%" + nombreEmpresa + "%");
			}
			if (idGrConvenio != null && !"".equals(idGrConvenio))
			{
				hqlQuery.append(" and c.idGrupoConvenio = ?");
				params.add(new Integer(idGrConvenio));
			}
			if (nombreGrConvenio != null && !"".equals(nombreGrConvenio))
			{
				hqlQuery.append(" and c.idGrupoConvenio in (select gc.idGrupoConvenio from GrupoConvenioVO gc where gc.idGrupoConvenio != 0 and gc.nombre like ?)");
				params.add("%" + nombreGrConvenio + "%");
			}
			if (idConvenio != null && !"".equals(idConvenio))
			{
				hqlQuery.append(" and c.idConvenio = ?");
				params.add(new Integer(idConvenio));
			}
			if (nombreConvenio != null && !"".equals(nombreConvenio))
			{
				hqlQuery.append(" and c.idConvenio in (select c.idConvenio from ConvenioVO c where c.idConvenio != 0 and c.descripcion like ?)");
				params.add("%" + nombreConvenio + "%");
			}
			hqlQuery.append(" order by " + orden + " ");
			Query query = this.session.createQuery(hqlQuery.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				query.setParameter(par++, iter.next());
			return query.list();
		} catch (Exception ex)
		{
			log.error("Ha ocurrido la siguiente excepcion en PersonaDAO.getListaPersonas: ", ex);
			throw new DaoException("PersonaDAO.getListaPersonas: ", ex);
		}
	}

	/**
	 * 
	 * @param idEmpresa
	 * @param nombreEmpresa
	 * @param idGrConvenio
	 * @param nombreGrConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getConveniosEmpGrupo(int idEmpresa, String nombreEmpresa, int idGrConvenio, String nombreGrConvenio) throws DaoException
	{
		try
		{
			Set idsEmpresas = new HashSet();
			if (!nombreEmpresa.equals(""))
			{
				List empresas = this.session.createCriteria(EmpresaVO.class).add(Restrictions.ne("idEmpresa", new Integer(Constants.RUT_EMPRESA_FALSA))).add(
						Restrictions.ilike("razonSocial", '%' + nombreEmpresa + '%')).list();
				if (empresas.size() == 0)
					return new ArrayList();
				for (Iterator it = empresas.iterator(); it.hasNext();)
					idsEmpresas.add(new Integer(((EmpresaVO) it.next()).getIdEmpresa()));
			}

			Set idsGrupos = new HashSet();
			if (!nombreGrConvenio.equals(""))
			{
				List grupos = this.session.createCriteria(GrupoConvenioVO.class).add(Restrictions.ilike("nombre", '%' + nombreGrConvenio + '%')).list();
				if (grupos.size() == 0)
					return new ArrayList();
				for (Iterator it = grupos.iterator(); it.hasNext();)
					idsGrupos.add(new Integer(((GrupoConvenioVO) it.next()).getIdGrupoConvenio()));
			}

			Criteria crit = this.session.createCriteria(ConvenioVO.class);
			if (idEmpresa != Constants.RUT_EMPRESA_FALSA)
				crit.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa)));
			if (!idsEmpresas.isEmpty())
				crit.add(Restrictions.in("idEmpresa", idsEmpresas));
			if (idGrConvenio != Constants.ID_GRUPO_FALSO)
				crit.add(Restrictions.eq("idGrupoConvenio", new Integer(idGrConvenio)));
			if (!idsGrupos.isEmpty())
				crit.add(Restrictions.in("idGrupoConvenio", idsGrupos));
			crit.addOrder(Order.asc("idGrupoConvenio")).addOrder(Order.asc("idEmpresa")).addOrder(Order.asc("idConvenio"));
			return crit.list();
		} catch (Exception ex)
		{
			log.error("Error en ConvenioDAO:getConveniosEmpGrupo:", ex);
			throw new DaoException("Error al leer lista de getConveniosEmpGrupo::", ex);
		}
	}

	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getGrupoConveniosBase() throws DaoException {
		try
		{
			return this.session.createCriteria(GrupoConvenioVO.class).add(Restrictions.eq("configBase", new Integer(1))).list();
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO:getGrupoConveniosBase:" + ex);
			throw new DaoException("Problemas obteniendo los tipos de GrupoConvenio Bases::", ex);
		}
	}
}
