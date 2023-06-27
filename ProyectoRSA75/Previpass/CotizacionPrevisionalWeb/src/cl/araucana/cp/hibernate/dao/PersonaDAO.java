package cl.araucana.cp.hibernate.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorCajaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NivelAccConvSucVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RepresentanteLegalVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.hibernate.exceptions.UsuarioNoEncontradoException;

/*
 * @(#) PersonaDao.java 1.41 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.41
 */
public class PersonaDAO
{
	private static Logger logger = Logger.getLogger(PersonaDAO.class);
	private Session session;

	public PersonaDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * administrador
	 * 
	 * @param idAdministrador
	 * @return
	 * @throws DaoException
	 */
	public AdministradorVO getAdministrador(int idAdministrador) throws DaoException
	{
		try
		{
			return (AdministradorVO) this.session.get(AdministradorVO.class, new Integer(idAdministrador));
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:getAdministrador: " + ex);
			throw new DaoException("PersonaDAO:getAdministrador: ", ex);
		}
	}

	/**
	 * retorna la persona o null si no existe ya sea instanciada ya sea persistida.
	 * 
	 * @param idPersona
	 * @return
	 * @throws DaoException
	 */
	public PersonaVO getPersona(int idPersona) throws DaoException
	{
		try
		{
			return (PersonaVO) this.session.get(PersonaVO.class, new Integer(idPersona));
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.getPersona: " + ex);
			throw new DaoException("Error al recuperar la persona rut: " + idPersona, ex);
		}
	}

	/**
	 * agrega una excepcion a la busca de persona en caso de no encontrarla
	 * 
	 * @param login
	 * @return
	 * @throws DaoException
	 * @throws UsuarioNoEncontradoException
	 */
	public PersonaVO getPersona(Integer idPersona) throws DaoException, UsuarioNoEncontradoException
	{
		try
		{
			if (idPersona == null)
				throw new UsuarioNoEncontradoException("Usuario null!!");
			PersonaVO result = (PersonaVO)this.session.get(PersonaVO.class, idPersona);
			if (result != null)
				return result;
			throw new UsuarioNoEncontradoException("Usuario no encontrado: " + idPersona + "::");
		} catch (HibernateException ex)
		{
			logger.error("Error en PersonaDAO:getPersona");
			throw new DaoException("Error en PersonaDAO:getPersona", ex);
		}
	}

	/**
	 * persona
	 * 
	 * @param login
	 * @return
	 * @throws DaoException
	 */
	public PersonaVO getPersona(String idPersona) throws DaoException
	{

		try
		{
			PersonaVO result = (PersonaVO)this.session.get(PersonaVO.class, new Integer(idPersona));
			if (result == null)
				logger.error("Persona no encontrada:" + idPersona + "::");
			return result;
		} catch (Exception ex)
		{
			logger.error("Error en PersonaDAO:getPersona", ex);
			throw new DaoException("Error en PersonaDAO:getPersona", ex);
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
		try
		{
			return (PersonaVO) this.session.createCriteria(PersonaVO.class).add(Restrictions.eq("idPersona", new Integer(String.valueOf(rut)))).uniqueResult();
		} catch (Exception ex)
		{
			logger.error("Error en PersonaDAO:getRepresentante");
			throw new DaoException("Error en PersonaDAO:getPersona", ex);
		}
	}

	/**
	 * guarda encargado
	 * 
	 * @param encargado
	 * @throws DaoException
	 */
	public void guardaEncargado(EncargadoVO encargado) throws DaoException
	{
		try
		{
			EncargadoVO enc = (EncargadoVO) this.session.get(EncargadoVO.class, new Integer(encargado.getIdEncargado()));
			if (enc == null)
			{
				this.session.save(encargado);
			} else
			{
				this.session.merge(encargado);
			}
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:guardaEncargado:" + encargado.getIdEncargado() + "::" + ex);
			throw new DaoException("Error al guardar el encargado: ", ex);
		}
	}

	/**
	 * lista encargados
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getListaEncargados(int idEmpresa, int idConvenio) throws DaoException
	{
		try
		{
			return this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEmpresa", new Integer(idEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
					.addOrder(Order.asc("idEmpresa")).addOrder(Order.asc("idConvenio")).list();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:getListaEncargados: " + ex);
			throw new DaoException("PersonaDAO:getListaEncargados: ", ex);
		}
	}

	/**
	 * elimina acceso encargado
	 * 
	 * @param idEncargado
	 * @throws DaoException
	 */
	public void deleteAccesoEncargado(int idEncargado) throws DaoException
	{
		try
		{
			List listaBorrar = this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEncargado", new Integer(idEncargado))).list();
			logger.debug("a borrar:" + listaBorrar.size() + ":idEncargado:" + idEncargado + "::");
			for (Iterator it = listaBorrar.iterator(); it.hasNext();)
				this.session.delete(it.next());

			this.session.flush();
			logger.debug("despues borrado:" + this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEncargado", new Integer(idEncargado))).list().size() + "::");
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:deleteAccesoEncargado: " + ex);
			throw new DaoException("PersonaDAO:deleteAccesoEncargado: ", ex);
		}
	}

	/**
	 * encargado
	 * 
	 * @param idEncargado
	 * @return
	 * @throws DaoException
	 */
	public EncargadoVO getEncargado(int idEncargado) throws DaoException
	{
		try
		{
			return (EncargadoVO) this.session.get(EncargadoVO.class, new Integer(idEncargado));
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.getEncargado: " + ex);
			throw new DaoException("PersonaDAO.getEncargado: ", ex);
		}
	}

	/**
	 * lista encargados
	 * 
	 * @param colEmpAdmins
	 * @return
	 * @throws DaoException
	 */
	public List getListaEncargados(Collection colEmpAdmins) throws DaoException
	{
		try
		{
			logger.info("\n\n\ngetListaEncargados:colEmpAdmins:" + colEmpAdmins + "::");
			if ((colEmpAdmins == null) || colEmpAdmins.isEmpty())
				return Collections.EMPTY_LIST;
			Set idsEncargados = new HashSet(CollectionUtils.collect(this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.in("idEmpresa", colEmpAdmins)).list(), new Transformer()
			{
				public Object transform(Object input)
				{
					return new Integer(((EncargadoConvenioVO) input).getIdEncargado());
				}
			}));
			if (idsEncargados.size() == 0)
				return new ArrayList();
			return this.session.createCriteria(PersonaVO.class).add(Restrictions.in("idPersona", idsEncargados)).addOrder(Order.asc("idPersona")).list();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:getListaEncargados: " + ex);
			throw new DaoException("PersonaDAO:getListaEncargados: ", ex);
		}
	}

	/**
	 * Método que lista los Encargados filtrándolos según el criterio que corresponda
	 * 
	 * @param colEmpAdmins
	 * @param filtros
	 * @return
	 * @throws DaoException
	 */
	public List getListaEncargados(Collection colEmpAdmins, HashMap filtros) throws DaoException
	{
		List params = new ArrayList();
		String filtroRut      = (filtros.containsKey("idEncargado") ? ((Integer) filtros.get("idEncargado")).toString() : null);
		String filtroNombre   = (filtros.containsKey("nombre")      ? (String)   filtros.get("nombre")                  : null);
		String filtroApellido = (filtros.containsKey("apellidos")   ? (String)   filtros.get("apellidos")               : null);

		try
		{
			logger.info("\n\n\ngetListaEncargados:colEmpAdmins:" + colEmpAdmins + "::");
			if ((colEmpAdmins == null) || colEmpAdmins.isEmpty())
				return Collections.EMPTY_LIST;
			Set idsEncargados = new HashSet(CollectionUtils.collect(this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.in("idEmpresa", colEmpAdmins)).list(), new Transformer()
			{
				public Object transform(Object input)
				{
					return new Integer(((EncargadoConvenioVO) input).getIdEncargado());
				}
			}));
			if (idsEncargados.size() == 0)
				return new ArrayList();
			//TODO GMALLEA  Se agrega la resticcion empresa para que retorne los encargados de las empresas.
			List listEncargado = this.session.createCriteria(EncargadoVO.class).add(Restrictions.in("idEncargado", idsEncargados)).add(Restrictions.like("empresa", new Integer(1))).list();
			if(listEncargado.size() == 0){
				return listEncargado;
			}
			StringBuffer sb = new StringBuffer("SELECT pers FROM cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO AS pers ");
			sb.append("WHERE pers.idPersona IN (");
			
			for (Iterator it = listEncargado.iterator(); it.hasNext();)
			{
				EncargadoVO encargadoVO = (EncargadoVO)it.next();
				sb.append(encargadoVO.getIdEncargado());
				//sb.append(it.next().toString());
				if (it.hasNext())
					sb.append(", ");
			}
			sb.append(") ");

			if (filtroRut != null)
			{
				sb.append("AND pers.idPersona = ? ");
				params.add(new Integer(filtroRut));
			}

			if (filtroNombre != null)
			{
				sb.append("AND UPPER(pers.nombres) LIKE ? ");
				params.add("%" + filtroNombre + "%");
			}

			if (filtroApellido != null)
			{
				sb.append("AND UPPER(CONCAT(CONCAT(RTRIM(pers.apellidoPaterno),' '), pers.apellidoMaterno)) LIKE ? ");
				params.add("%" + filtroApellido + "%");
			}

			sb.append("ORDER BY pers.idPersona");
			
			Query q = this.session.createQuery(sb.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				q.setParameter(par++, iter.next());

			return q.list();
		
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:getListaEncargados: " + ex);
			throw new DaoException("PersonaDAO:getListaEncargados: ", ex);
		}
	}
	
	/**
	 * convenios encargado
	 * 
	 * @param idUsuario
	 * @return
	 * @throws DaoException
	 */
	public HashMap getConveniosEncargado(int idUsuario) throws DaoException
	{
		try
		{
			List resultado = this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEncargado", new Integer(idUsuario))).list();
			HashMap result = new HashMap();
			for (Iterator it = resultado.iterator(); it.hasNext();)
			{
				EncargadoConvenioVO enc = (EncargadoConvenioVO) it.next();
				
				EmpresaVO empresa = (EmpresaVO)this.session.createCriteria(EmpresaVO.class)
						.add(Restrictions.eq("idEmpresa", new Integer(enc.getIdEmpresa())))
						.add(Restrictions.eq("tipo", Constants.TIPO_EMPRESA))
						.uniqueResult();

				if(empresa != null)
					result.put("" + enc.getIdEmpresa() + "#" + enc.getIdConvenio(), new Integer(enc.getIdNivelAcceso()));
			}
			return result;
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:getConveniosEncargado:" + ex);
			throw new DaoException("PersonaDAO:getConveniosEncargado:", ex);
		}
	}

	/**
	 * lista empresas administradas
	 * 
	 * @param idAdmin
	 * @return
	 * @throws DaoException
	 */
	public List getListaEmpresasAdministradas(int idAdmin) throws DaoException
	{

		try
		{
			List resultado = this.session.createCriteria(EmpresaVO.class).add(Restrictions.eq("idAdmin", new Long(idAdmin)))
					.add(Restrictions.eq("tipo", Constants.TIPO_EMPRESA))
					.addOrder(Order.asc("razonSocial")).list();
			
			EmpresaVO empresa;
			for (Iterator it = resultado.iterator(); it.hasNext();)
			{
				empresa = (EmpresaVO) it.next();
				this.session.evict(empresa);
			}
			return resultado;
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.getListaEmpresasAdministradas: " + ex);
			throw new DaoException("PersonaDAO.getListaEmpresasAdministradas: ", ex);
		}
	}

	/**
	 * lista niveles acceso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaNivelesAcceso() throws DaoException
	{
		try
		{
			return this.session.createCriteria(NivelAccConvSucVO.class).addOrder(Order.asc("nombre")).list();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:getListaNivelesAcceso: " + ex);
			throw new DaoException("PersonaDAO:getListaNivelesAcceso: ", ex);
		}
	}

	/**
	 * save persona
	 * 
	 * @param persona
	 * @throws DaoException
	 */
	public void savePersona(PersonaVO persona) throws DaoException
	{
		try
		{
			this.session.save(persona);
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.savePersona: " + ex);
			throw new DaoException("Error al guardar la persona: ", ex);
		}
	}

	/**
	 * eliminar permiso encargado
	 * 
	 * @param idEncargado
	 * @throws DaoException
	 */
	public void borraPermEncargado(int idEncargado,int idAdmin) throws DaoException
	{
		try
		{
			
			Query query = this.session.createQuery(" DELETE FROM  cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO "+
											     " WHERE idEmpresa IN ("+
											     " SELECT idEmpresa FROM cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO WHERE idAdmin = ? AND tipo = ?)"+
											     " AND idEncargado = ? ");
			
			query.setInteger(0, idAdmin);
			query.setString(1, Constants.TIPO_EMPRESA);
			query.setInteger(2, idEncargado);
			
			query.executeUpdate();
			
			this.session.flush();
			
			logger.info("ID Encargado " + idEncargado);
			logger.info("ID Administrador " + idAdmin);
			logger.info("Se borran todos los registros de la tabla encargado convenio para porder registrar los nuevos convenios");
			
			//List listaBorrar = this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEncargado", new Integer(idEncargado))).list();
			//if(listaBorrar != null && listaBorrar.size() > 0) {
			//	for (Iterator it = listaBorrar.iterator(); it.hasNext();)
			//		this.session.delete(it.next());
				
			//}
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:borraPermEncargado:", ex);
			throw new DaoException("PersonaDAO:borraPermEncargado:", ex);
		}
	}

	/**
	 * guarda permiso encargado
	 * 
	 * @param lista
	 * @throws DaoException
	 */
	public void guardaPermEncargado(List lista) throws DaoException
	{
		try
		{
			for (Iterator it = lista.iterator(); it.hasNext();)
				this.session.save(it.next());
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:guardaPermEncargado: " + ex);
			throw new DaoException("PersonaDAO:guardaPermEncargado: ", ex);
		}
	}

	/**
	 * guarda encargado
	 * 
	 * @param encargado
	 * @param force
	 * @throws DaoException
	 */
	public void guardaEncargado(EncargadoVO encargado, boolean force) throws DaoException
	{
		try
		{
			Object e = this.session.get(EncargadoVO.class, new Integer(encargado.getIdEncargado()));
			logger.debug("enc:" + encargado.getHabilitado() + "::" + e + "::");
			if (e == null && force)
				this.session.save(encargado);
			else
				this.session.merge(encargado);
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.guardaEncargado: " + ex);
			throw new DaoException("Error al guardar el encargado: ", ex);
		}
	}

	/**
	 * save representante legal
	 * 
	 * @param repLegal
	 * @throws DaoException
	 */
	public void saveRepLegal(RepresentanteLegalVO repLegal) throws DaoException
	{
		try
		{
			this.session.save(repLegal);
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.saveRepLegal: " + ex);
			throw new DaoException("Error al guardar el representante legal: ", ex);
		}
	}

	/**
	 * borra permiso encargado
	 * 
	 * @param empresas
	 * @param idEncargado
	 * @throws DaoException
	 */
	public void borraPermisosEncargado(Collection empresas, int idEncargado) throws DaoException
	{
		try
		{
			List listaBorrar;
			if (!empresas.isEmpty())
				listaBorrar = this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEncargado", new Integer(idEncargado))).add(Restrictions.in("idEmpresa", empresas)).list();
			else
				listaBorrar = new ArrayList();

			for (Iterator it = listaBorrar.iterator(); it.hasNext();)
				this.session.delete(it.next());

			this.session.flush();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:borraPermisosEncargado: " + ex);
			throw new DaoException("PersonaDAO:borraPermisosEncargado: ", ex);
		}
	}

	/**
	 * Método que lista Empresas filtrándolas según el criterio que corresponda
	 * 
	 * @param col
	 * @param filtros
	 * @return
	 * @throws DaoException
	 */
	public List getListaEmpresas(Collection col, HashMap filtros) throws DaoException
	{
		String filtroRut      = (filtros.containsKey("rutEmpresa")  ? (filtros.get("rutEmpresa")).toString()  : null);
		String filtroRazon    = (filtros.containsKey("razonSocial") ? (filtros.get("razonSocial")).toString() : null);
		String filtroProceso  = (filtros.containsKey("proceso")     ? (filtros.get("proceso")).toString()     : null);
		String filtroConvenio = (filtros.containsKey("convenio")    ? (filtros.get("convenio")).toString()    : null);

		try
		{
			if (col.isEmpty() || col == null)
				return Collections.EMPTY_LIST;

			logger.info("PersonaDAO:getListaEmpresas::");

			StringBuffer sb = new StringBuffer("SELECT DISTINCT e FROM EmpresaVO e ");

			if(filtroProceso != null)
			{
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION)))
				{
					sb.append(", CotizanteVO c, CausaAvisoRAVO ca ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION)))
				{
					sb.append(", CotizanteVO c, CausaAvisoGRVO ca ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION)))
				{
					sb.append(", CotizanteVO c, CausaAvisoREVO ca ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO)))
				{
					sb.append(", CotizanteVO c, CausaAvisoDCVO ca ");
				}

				sb.append("WHERE e.idEmpresa         = c.rutEmpresa");
				sb.append("  AND e.tipo              = '"+Constants.TIPO_EMPRESA+"'");
				sb.append("  AND ca.idCotizPendiente = c.idCotizante");
				sb.append("  AND ca.idConvenio       = c.idConvenio");
				sb.append("  AND ca.rutEmpresa       = e.idEmpresa ");
				sb.append("  AND ");
			} else
			{
				sb.append("WHERE ");
			}

			sb.append("e.idEmpresa IN (");
			for (Iterator it = col.iterator(); it.hasNext();)
			{
				sb.append(it.next().toString());
				if (it.hasNext())
					sb.append(", ");
			}
			sb.append(") ");

			if (filtroRut != null && !filtroRut.trim().equals("") && !filtroRut.trim().equals("0"))
			{
				sb.append("AND e.idEmpresa = " + filtroRut + " ");
			}

			if (filtroRazon != null && !filtroRazon.trim().equals(""))
			{
				sb.append("AND UPPER(e.razonSocial) LIKE '%" + filtroRazon + "%' ");
			}

			if (filtroConvenio != null && !filtroConvenio.trim().equals("0"))
			{
				sb.append("AND e.idEmpresa IN (SELECT co.idEmpresa FROM ConvenioVO co WHERE co.idGrupoConvenio = " + filtroConvenio + ") ");				
			}

			sb.append("ORDER BY e.idEmpresa");

			Query q = this.session.createQuery(sb.toString());

			return q.list();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:getListaEmpresas::" + ex);
			throw new DaoException("getListaEmpresas: ", ex);
		}
	}
	
	
	/**
	 * Método que lista Empresas filtrándolas según el criterio que corresponda
	 * 
	 * @param col
	 * @param filtros
	 * @return
	 * @throws DaoException
	 */
	public List getListaEmpresasCausa(Collection col, HashMap filtros) throws DaoException
	{
		String filtroRut      = (filtros.containsKey("rutEmpresa")  ? (filtros.get("rutEmpresa")).toString()  : null);
		String filtroRazon    = (filtros.containsKey("razonSocial") ? (filtros.get("razonSocial")).toString() : null);
		String filtroProceso  = (filtros.containsKey("proceso")     ? (filtros.get("proceso")).toString()     : null);
		String filtroConvenio = (filtros.containsKey("convenio")    ? (filtros.get("convenio")).toString()    : null);

		try
		{
			if (col.isEmpty() || col == null)
				return Collections.EMPTY_LIST;

			logger.info("PersonaDAO:getListaEmpresas::");

			StringBuffer sb = new StringBuffer("SELECT DISTINCT e FROM EmpresaVO e ");

			if(filtroProceso != null)
			{
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION)))
				{
					sb.append(", CotizanteVO c, CausaRAVO ca ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION)))
				{
					sb.append(", CotizanteVO c, CausaGRVO ca ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION)))
				{
					sb.append(", CotizanteVO c, CausaREVO ca ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO)))
				{
					sb.append(", CotizanteVO c, CausaDCVO ca ");
				}

				sb.append("WHERE e.idEmpresa         = c.rutEmpresa");
				sb.append("  AND e.tipo              = '"+Constants.TIPO_EMPRESA+"'");
				//sb.append("AND ca.idCotizPendiente = c.idCotizante");
				sb.append("  AND ca.idConvenio       = c.idConvenio");
				sb.append("  AND ca.rutEmpresa       = e.idEmpresa ");
				sb.append("  AND ");
			} else
			{
				sb.append("WHERE ");
			}

			sb.append("e.idEmpresa IN (");
			for (Iterator it = col.iterator(); it.hasNext();)
			{
				sb.append(it.next().toString());
				if (it.hasNext())
					sb.append(", ");
			}
			sb.append(") ");

			if (filtroRut != null && !filtroRut.trim().equals("") && !filtroRut.trim().equals("0"))
			{
				sb.append("AND e.idEmpresa = " + filtroRut + " ");
			}

			if (filtroRazon != null && !filtroRazon.trim().equals(""))
			{
				sb.append("AND UPPER(e.razonSocial) LIKE '%" + filtroRazon + "%' ");
			}

			if (filtroConvenio != null && !filtroConvenio.trim().equals("0"))
			{
				sb.append("AND e.idEmpresa IN (SELECT co.idEmpresa FROM ConvenioVO co WHERE co.idGrupoConvenio = " + filtroConvenio + ") ");				
			}

			sb.append("ORDER BY e.idEmpresa");

			Query q = this.session.createQuery(sb.toString());

			return q.list();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:getListaEmpresas::" + ex);
			throw new DaoException("getListaEmpresas: ", ex);
		}
	}


	/**
	 * lista empresas in
	 * 
	 * @param col
	 * @return
	 * @throws DaoException
	 */
	public List getListaEmpresasIn(Collection col) throws DaoException
	{
		try
		{
			if (col.isEmpty())
				return Collections.EMPTY_LIST;
			//TODO GMALLEA Se agrega la rectriccion tipo para que retorne solo las empresas.
			logger.info("PersonaDAO:getListaEmpresasIn::");
			return this.session.createCriteria(EmpresaVO.class).add(Restrictions.in("idEmpresa", col)).addOrder(Order.asc("idEmpresa")).add(Restrictions.like("tipo", Constants.TIPO_EMPRESA)).list();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:getListaEmpresasIn::" + ex);
			throw new DaoException("getListaEmpresasIn: ", ex);
		}
	}

	/**
	 * escargado empresa
	 * 
	 * @param login
	 * @return
	 * @throws DaoException
	 */
	public boolean isEscargadoEmpresa(String login) throws DaoException
	{
		try
		{
			List lista = this.session.createCriteria(EncargadoVO.class).add(Restrictions.eq("idEncargado", new Integer(login))).add(
					Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_ENCARGADO))).list();


			if (lista.size() > 0)
			{
				logger.debug("es encargado habilitado");
				List perms = this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEncargado", new Integer(login))).add(
						Restrictions.ne("idNivelAcceso", new Integer(Constants.NIVEL_ACC_CONV_SUC_NADA))).list();
				int idEmpresa = -1;
				EmpresaVO empresa = null;
				logger.debug("n permisos:" + perms.size() + "::");
				for (Iterator it = perms.iterator(); it.hasNext();)
				{
					EncargadoConvenioVO enc = (EncargadoConvenioVO) it.next();
					if (idEmpresa != enc.getIdEmpresa())
					{
						logger.debug("get empresa:" + enc.getIdEmpresa() + "::");
						empresa = (EmpresaVO) this.session.get(EmpresaVO.class, new Integer(enc.getIdEmpresa()));
						idEmpresa = enc.getIdEmpresa();
						logger.debug("emp habilitada:" + empresa.getHabilitada().intValue() + "::");
					}

					if (empresa != null && empresa.getHabilitada().intValue() == 1)
					{
						List convenios = this.session.createCriteria(ConvenioVO.class).add(Restrictions.eq("idEmpresa", new Integer(idEmpresa))).add(
								Restrictions.eq("idConvenio", new Integer(enc.getIdConvenio()))).add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_CONVENIO))).list();
						logger.debug("convenio encontrado:idEmpresa" + idEmpresa + ":idConvenio:" + enc.getIdConvenio() + ":habilitado:" + (convenios.size() > 0 ? true : false) + "::");
						if (convenios.size() > 0)
							return true;
					}
				}
			}

			return false;
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:isEscargadoEmpresa:: " + ex);
			throw new DaoException("PersonaDAO:isEscargadoEmpresa: ", ex);
		}
	}

	/**
	 * admin empresa
	 * 
	 * @param idPersona
	 * @return
	 * @throws DaoException
	 */
	public boolean isAdminEmpresa(String idPersona) throws DaoException
	{
		try
		{
			List lista = this.session.createCriteria(AdministradorVO.class).add(Restrictions.eq("idAdmin", new Integer(idPersona))).add(
					Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_ADMIN))).list();

			if (lista.size() > 0)
			{
				lista = this.session.createCriteria(EmpresaVO.class).add(Restrictions.eq("idAdmin", new Long(idPersona))).add(
						Restrictions.eq("habilitada", new Integer(Constants.COD_HABILITACION_EMPRESA))).list();
				if (lista.size() > 0)
					return true;
			}

			return false;
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:isAdminEmpresa:" + ex);
			throw new DaoException("PersonaDAO:isAdminEmpresa:", ex);
		}
	}

	/**
	 * Indica si el usuario es Administrador de alguna Caja
	 * 
	 * @param idAdminCaja
	 * @return
	 * @throws DaoException
	 */
	public boolean isAdministradorCaja(int idAdminCaja) throws DaoException {
		try {
			boolean isAdminCaja = false;

			List lista = this.session.createCriteria(AdministradorCajaVO.class).add(Restrictions.eq("idAdminCaja", new Integer(idAdminCaja))).list();
			if (lista.size() > 0)
				isAdminCaja = true;

			return isAdminCaja;
		} catch (Exception ex) {
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:isAdminCaja:" + ex);
			throw new DaoException("PersonaDAO:isAdminCaja:", ex);
		}
	}
	
	/**
	 * 
	 * @return lista niveles acceso
	 * @throws DaoException
	 */
	public List getListTipoPersona(String rut) throws DaoException
	{
		List listTipoPesona = new ArrayList();
		try
		{
			
			 listTipoPesona = this.session.createCriteria(AdministradorVO.class).add(Restrictions.eq("idAdmin", new Integer(rut))).add(
					Restrictions.eq("empresa", new Integer(1))).list();
			
			 if(listTipoPesona.size() == 0){
				 listTipoPesona= this.session.createCriteria(EncargadoVO.class).add(Restrictions.eq("idEncargado", new Integer(rut)))
					 																.add(Restrictions.eq("empresa",  new Integer(1))).list();
			}
			
			return listTipoPesona;
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:getListTipoPersona: " + ex);
			throw new DaoException("PersonaDAO:getListTipoPersona: ", ex);
		}
	}
	
}