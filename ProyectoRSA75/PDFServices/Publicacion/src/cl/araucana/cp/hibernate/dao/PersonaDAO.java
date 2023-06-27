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
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
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
		try	{
			return (PersonaVO) this.session.get(PersonaVO.class, new Integer(idPersona));
		} catch (Exception ex) {
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
			List resultado = this.session.createCriteria(EmpresaVO.class).add(Restrictions.eq("idAdmin", new Long(idAdmin))).addOrder(Order.asc("razonSocial")).list();
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
	public void borraPermEncargado(int idEncargado) throws DaoException
	{
		try
		{
			List listaBorrar = this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEncargado", new Integer(idEncargado))).list();
			if(listaBorrar != null && listaBorrar.size() > 0) {
				for (Iterator it = listaBorrar.iterator(); it.hasNext();)
					this.session.delete(it.next());
				this.session.flush();
			}
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

			logger.info("PersonaDAO:getListaEmpresasIn::");
			return this.session.createCriteria(EmpresaVO.class).add(Restrictions.in("idEmpresa", col)).addOrder(Order.asc("idEmpresa")).list();
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
}
