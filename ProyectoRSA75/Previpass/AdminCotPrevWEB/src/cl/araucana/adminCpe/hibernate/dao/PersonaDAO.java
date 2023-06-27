package cl.araucana.adminCpe.hibernate.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorCajaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaEmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaGrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaSucursalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NivelAccConvSucVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RepresentanteLegalVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.35
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
	 * 
	 * @param idPersona
	 * @return persona
	 * @throws DaoException
	 */
	public PersonaVO getPersona(int idPersona) throws DaoException
	{
		try
		{
			logger.info("PersonaDAO:getPersona: " + idPersona + "::");
			return (PersonaVO) this.session.get(PersonaVO.class, new Integer(idPersona));
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.getPersona: " + ex);
			throw new DaoException("Error al recuperar la persona rut: " + idPersona, ex);
		}
	}

	/**
	 * 
	 * @param login
	 * @return
	 * @throws DaoException
	 */
	public PersonaVO getPersona(Object login) throws DaoException
	{
		try
		{
			if (login == null)
				return null;
			PersonaVO result = (PersonaVO) this.session.get(PersonaVO.class, new Integer(login.toString()));
			if (result != null)
				return result;
			logger.error("Persona no encontrada:" + login + "::");
			return null;
		} catch (Exception ex)
		{
			logger.error("Error en PersonaDAO:getPersona");
			throw new DaoException("Error en PersonaDAO:getPersona", ex);
		}
	}

	/**
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
	 * 
	 * @param persona
	 * @throws DaoException
	 */
	public void guardaPersona(PersonaVO persona) throws DaoException
	{
		try
		{
			PersonaVO p = (PersonaVO) this.session.get(PersonaVO.class, persona.getIdPersona());
			if (p == null)
				this.session.save(persona);
			else
				this.session.merge(persona);
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.guardaPersona: " + ex);
			throw new DaoException("Error al guardra la persona: ", ex);
		}
	}

	/**
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
	 * 
	 * @param rut
	 * @param nombre
	 * @param apellido
	 * @param nombreGC
	 * @param codigoGC
	 * @param razonS
	 * @param rutE
	 * @return lista de personas
	 * @throws DaoException
	 */
	public List getListaEncargados(String rut, String nombre, String apellido, String nombreGC, String codigoGC, String razonS, String rutE) throws DaoException
	{
		return getListaPersonas(rut, nombre, apellido, codigoGC, nombreGC, rutE, razonS, "EncargadoVO");
	}

	/**
	 * 
	 * @param rut
	 * @param nombre
	 * @param apellido
	 * @param nombreGC
	 * @param codigoGC
	 * @param razonS
	 * @param rutE
	 * @return lista de lectores
	 * @throws Exception 
	 * @throws HibernateException 
	 */
	public List getListaLectores(String rut, String nombre, String apellido, String nombreGC, String codigoGC, String razonS, String rutE) throws HibernateException, Exception
	{
		Set ids = new HashSet();
		boolean buscaGrupo = false, buscaEmpresa = false;
		if ((codigoGC != null && !"".equals(codigoGC.trim())) || (nombreGC != null && !"".equals(nombreGC.trim())))
			buscaGrupo = true;
		if ((rutE != null && !"".equals(rutE.trim())) || (razonS != null && !"".equals(razonS.trim())))
			buscaEmpresa = true;

		if (buscaGrupo)
		{
			Criteria crit = this.session.createCriteria(LectorEmpresaGrupoConvenioVO.class);
			boolean flag = false;
			if (codigoGC != null && !"".equals(codigoGC.trim()))
			{
				flag = true;
				crit = crit.add(Restrictions.eq("idGrupoConvenio", new Integer(codigoGC)));
			}

			if (nombreGC != null && !"".equals(nombreGC.trim()))
			{
				String q = "from GrupoConvenioVO gc WHERE idGrupoConvenio != 0 AND nombre like '%";
				String tmp = Utils.transforToDB(nombreGC);
				q += tmp + "%'" + (tmp.indexOf('\\') != -1 ? " ESCAPE '\\' " : "");
				List grupos = this.session.createQuery(q).list();
				if (grupos.size() > 0)
				{
					flag = true;
					Set idsGrupos = new HashSet();
					for (Iterator it = grupos.iterator(); it.hasNext();)
						idsGrupos.add(new Integer(((GrupoConvenioVO) it.next()).getIdGrupoConvenio()));
					if (!idsGrupos.isEmpty())
						crit = crit.add(Restrictions.in("idGrupoConvenio", idsGrupos));
				}
			}
			if (flag)
				for (Iterator it = crit.list().iterator(); it.hasNext();)
					ids.add(new Integer(((LectorEmpresaGrupoConvenioVO) it.next()).getIdLectorEmpresa()));
		}
		if (buscaEmpresa)
		{
			Set idsEmpresas = new HashSet();
			if (razonS != null && !"".equals(razonS.trim()))
			{
				String q = "from EmpresaVO e WHERE razonSocial like '%";
				String tmp = Utils.transforToDB(razonS.toUpperCase());
				q += tmp + "%'" + (tmp.indexOf('\\') != -1 ? " ESCAPE '\\' " : "");
				List empresas = this.session.createQuery(q).list();
				for (Iterator it = empresas.iterator(); it.hasNext();)
					idsEmpresas.add(new Integer(((EmpresaVO) it.next()).getIdEmpresa()));
			}
			if (rutE != null && !"".equals(rutE.trim()))
				idsEmpresas.add(new Integer(Utils.desFormatRut(rutE)));

			if (idsEmpresas.size() > 0)
			{
				Criteria crit = this.session.createCriteria(LectorEmpresaEmpresaVO.class).add(Restrictions.in("idEmpresa", idsEmpresas));
				for (Iterator it = crit.list().iterator(); it.hasNext();)
					ids.add(new Integer(((LectorEmpresaEmpresaVO) it.next()).getIdLectorEmpresa()));
				crit = this.session.createCriteria(LectorEmpresaConvenioVO.class).add(Restrictions.in("idEmpresa", idsEmpresas));
				for (Iterator it = crit.list().iterator(); it.hasNext();)
					ids.add(new Integer(((LectorEmpresaConvenioVO) it.next()).getIdLectorEmpresa()));
				crit = this.session.createCriteria(LectorEmpresaSucursalVO.class).add(Restrictions.in("idEmpresa", idsEmpresas));
				for (Iterator it = crit.list().iterator(); it.hasNext();)
					ids.add(new Integer(((LectorEmpresaSucursalVO) it.next()).getIdLectorEmpresa()));
			}
		}
		if ((buscaGrupo || buscaEmpresa) && ids.size() == 0)
			return new ArrayList();

		/**
		 * GMALLEA 03-12-2013 Se prueba con HQL ya que de la otra forma se cae en la consulta a la  bd (el in del where es muy grande)
		 */

		StringBuffer hqlQuery = new StringBuffer("SELECT p FROM PersonaVO p, LectorEmpresaVO l WHERE p.idPersona =  l.idLectorEmpresa");
		
		if (rut != null && !"".equals(rut.trim()))
			hqlQuery.append(" AND p.idPersona = "+Utils.desFormatRut(rut));
		if (nombre != null && !"".equals(nombre.trim()))
			hqlQuery.append(" AND p.nombres like '%" + nombre + "%'");
		if (apellido != null && !"".equals(apellido.trim()))
			hqlQuery.append(" AND p.apellidoPaterno like '%" + apellido + "%' AND p.apellidoMaterno like '%" + apellido + "%'" );
		
		if (ids.size() > 0){
			String idsString = ids.toString().replace('[',' ').replace(']', ' ');
			hqlQuery.append(" AND p.idPersona in ("+ idsString+")");
		}
		Query query = this.session.createQuery(hqlQuery.toString());
			
		//FIN GMALLEA
		/*
	   Criteria crit = (this.session.createCriteria(PersonaVO.class).add(Restrictions.ne("idPersona", new Integer(0))));
		
		List lectorEmpresa = this.session.createCriteria(LectorEmpresaVO.class).list();
		if (lectorEmpresa.size() > 0)
		{
			Set idsLectEmps = new HashSet();
			for (Iterator it = lectorEmpresa.iterator(); it.hasNext();)
				idsLectEmps.add(new Integer(((LectorEmpresaVO) it.next()).getIdLectorEmpresa()));
			if (!idsLectEmps.isEmpty())
				crit = crit.add(Restrictions.in("idPersona", idsLectEmps));
		}
		
		// estar en lectoemp
		if (rut != null && !"".equals(rut.trim()))
			crit = crit.add(Restrictions.eq("idPersona", new Integer(Utils.desFormatRut(rut))));
		if (nombre != null && !"".equals(nombre.trim()))
			crit = crit.add(Restrictions.ilike("nombres", "%" + nombre + "%"));
		if (apellido != null && !"".equals(apellido.trim()))
			crit = crit.add(Restrictions.ilike("apellidoPaterno", "%" + apellido + "%")).add(Restrictions.ilike("apellidoMaterno", "%" + apellido + "%"));

		if (ids.size() > 0)
			crit = crit.add(Restrictions.in("idPersona", ids));

		
		long time_start, time_end;
		time_start = System.currentTimeMillis();
		
		List lisetc =crit.list();
		
		time_end = System.currentTimeMillis();
		System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");
*/
		return query.list();
	}

	/**
	 * 
	 * @param rut
	 * @param nombre
	 * @param apellido
	 * @param nombreGC
	 * @param codigoGC
	 * @param razonS
	 * @param rutE
	 * @return lista de administradores
	 * @throws DaoException
	 */
	public List getListaAdministrador(String rut, String nombre, String apellido, String nombreGC, String codigoGC, String razonS, String rutE) throws DaoException
	{
		return getListaPersonas(rut, nombre, apellido, codigoGC, nombreGC, rutE, razonS, "AdministradorVO");
	}

	public AdministradorVO getAdministrador(int idAdministrador) throws DaoException
	{
		try
		{
			return (AdministradorVO) this.session.get(AdministradorVO.class, new Integer(idAdministrador));
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.getEncargado: " + ex);
			throw new DaoException("PersonaDAO.getEncargado: ", ex);
		}
	}

	/**
	 * 
	 * @param grupoConvenio
	 * @param nombreGrupo
	 * @param idUsuario
	 * @return lista empresas por grupo y administrador
	 * @throws DaoException
	 */
	public List getListaEmpresasGrupoAdmin(String grupoConvenio, String nombreGrupo, int idUsuario) throws DaoException
	{
		try
		{
			List grupo = new ArrayList();
			List idEmpresaList = new ArrayList();
			int newGrupoConvenio = 0;
			if (!"".equals(grupoConvenio))
				newGrupoConvenio = Integer.parseInt(grupoConvenio);
			Criteria crit = this.session.createCriteria(GrupoConvenioVO.class).add(Restrictions.ne("idGrupoConvenio", new Integer(Constants.ID_GRUPO_CONVENIO_DEFAULT)));
			if (!"".equals(grupoConvenio))
				crit = crit.add(Restrictions.eq("idGrupoConvenio", new Integer(newGrupoConvenio)));
			if (!"".equals(nombreGrupo))
				crit = crit.add(Restrictions.ilike("nombre", "%" + nombreGrupo + "%"));

			grupo = crit.list();

			if (grupo.size() > 0)
			{
				GrupoConvenioVO grupoConv;
				List idGC = new ArrayList();
				for (int i = 0; i < grupo.size(); i++)
				{
					grupoConv = (GrupoConvenioVO) grupo.get(i);
					idGC.add(new Integer(grupoConv.getIdGrupoConvenio()));
				}
				List listConvenio;
				if (!idGC.isEmpty())
				{
					Criteria ct2 = this.session.createCriteria(ConvenioVO.class)
					// .add(Restrictions.ne("idConvenio", new Integer(Constants.ID_CONVENIO_DEFAULT)))
							.add(Restrictions.in("idGrupoConvenio", idGC));
					listConvenio = ct2.list();
				} else
					listConvenio = new ArrayList();

				if (listConvenio.size() > 0)
				{
					List idConvenioList = new ArrayList();
					ConvenioVO convenio = new ConvenioVO();
					for (int i = 0; i < listConvenio.size(); i++)
					{
						convenio = (ConvenioVO) listConvenio.get(i);
						idConvenioList.add(new Integer(convenio.getIdConvenio()));
						idEmpresaList.add(new Integer(convenio.getIdEmpresa()));
					}
				}
			} else
				return new ArrayList();
			if (idEmpresaList.size() > 0)
				return this.session.createCriteria(EmpresaVO.class).add(Restrictions.ne("idEmpresa", new Integer(0))).add(Restrictions.ne("idAdmin", new Long(idUsuario))).add(
						Restrictions.in("idEmpresa", idEmpresaList)).addOrder(Order.asc("idEmpresa")).list();
			return new ArrayList();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.getListaPersonas:", ex);
			throw new DaoException("PersonaDAO.getListaPersonas: ", ex);
		}
	}

	/**
	 * 
	 * @param idPersona
	 * @return lista permisos por encargado
	 * @throws DaoException
	 */
	public List getPermisosEncargado(int idPersona) throws DaoException
	{
		try
		{
			return this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.ne("idEncargado", new Integer(Constants.ID_USUARIO_DEFAULT))).add(
					Restrictions.eq("idEncargado", new Integer(idPersona))).addOrder(Order.asc("idEmpresa")).addOrder(Order.asc("idConvenio")).list();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:getPermisosEncargado: " + ex);
			throw new DaoException("PersonaDAO:getPermisosEncargado: ", ex);
		}
	}

	/**
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @return lista permisos Encargado
	 * @throws DaoException
	 */
	public List getPermisosEncargado(int idEmpresa, int idConvenio) throws DaoException
	{
		try
		{
			return this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.ne("idEncargado", new Integer(Constants.ID_USUARIO_DEFAULT))).add(
					Restrictions.eq("idEmpresa", new Integer(idEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio))).addOrder(Order.asc("idEmpresa"))
					.addOrder(Order.asc("idConvenio")).list();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:getPermisosEncargado: " + ex);
			throw new DaoException("PersonaDAO:getPermisosEncargado: ", ex);
		}
	}

	/**
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
	 * 
	 * @param idEncargado
	 * @return encargado
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
	 * 
	 * @param idLectorEmpresa
	 * @return lector empresa
	 * @throws DaoException
	 */
	public LectorEmpresaVO getLectorEmpresa(int idLectorEmpresa) throws DaoException
	{
		try
		{
			return (LectorEmpresaVO) this.session.get(LectorEmpresaVO.class, new Integer(idLectorEmpresa));
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.getLectorEmpresa: " + ex);
			throw new DaoException("PersonaDAO.getLectorEmpresa: ", ex);
		}
	}

	/**
	 * 
	 * @param idAdmin
	 * @throws DaoException
	 */
	public void borraAdmin(int idAdmin) throws DaoException
	{
		try
		{
			AdministradorVO admin = (AdministradorVO) this.session.get(AdministradorVO.class, new Integer(idAdmin));
			if (admin != null)
				this.session.delete(admin);

			this.session.flush();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.borraAdmin: " + ex);
			throw new DaoException("PersonaDAO.borraAdmin: ", ex);
		}
	}

	/**
	 * 
	 * @param idAdmin
	 * @throws DaoException
	 */
	public void borraTodosPermisosAdmin(int idAdmin) throws DaoException
	{
		try
		{
			this.session.flush();
			List listaBorrar = this.session.createCriteria(EmpresaVO.class).add(Restrictions.eq("idAdmin", new Long(idAdmin))).list();
			EmpresaVO empresa;
			for (Iterator it = listaBorrar.iterator(); it.hasNext();)
			{
				empresa = (EmpresaVO) it.next();
				empresa.setIdAdmin(new Long(Constants.ID_USUARIO_DEFAULT));
				this.session.save(empresa);
			}
			this.session.flush();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.borraTodosPermisosAdmin: " + ex);
			throw new DaoException("PersonaDAO.borraTodosPermisosAdmin: ", ex);
		}
	}

	/**
	 * 
	 * @param idEncargado
	 * @throws DaoException
	 */
	public void borraPermEncargado(int idEncargado) throws DaoException
	{
		try
		{
			List listaBorrar = this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEncargado", new Integer(idEncargado))).list();
			for (Iterator it = listaBorrar.iterator(); it.hasNext();)
				this.session.delete(it.next());
			this.session.flush();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:borraPermEncargado:", ex);
			throw new DaoException("PersonaDAO:borraPermEncargado:", ex);
		}
	}

	/**
	 * 
	 * @param idLector
	 * @throws DaoException
	 */
	public void borraTodosPermisosConvenioRolLector(int idLector) throws DaoException
	{
		try
		{
			List listaBorrar = this.session.createCriteria(LectorEmpresaConvenioVO.class).add(Restrictions.eq("idLectorEmpresa", new Integer(idLector))).list();
			for (Iterator it = listaBorrar.iterator(); it.hasNext();)
				this.session.delete(it.next());
			this.session.flush();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.borraTodosPermisosConvSucuRolLector: " + ex);
			throw new DaoException("PersonaDAO.borraTodosPermisosGrupoConv: ", ex);
		}
	}

	/**
	 * 
	 * @param idAdmin
	 * @return
	 * @throws DaoException
	 */
	public List getListaEmpresasAdministradas(int idAdmin) throws DaoException
	{
		try
		{
			return this.session.createCriteria(EmpresaVO.class).add(Restrictions.eq("idAdmin", new Long(idAdmin))).addOrder(Order.asc("razonSocial")).list();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.getListaEmpresasAdministradas: " + ex);
			throw new DaoException("PersonaDAO.getListaEmpresasAdministradas: ", ex);
		}
	}

	/**
	 * 
	 * @param idNivel
	 * @return nivel acceso sucursal
	 * @throws DaoException
	 */
	public NivelAccConvSucVO getNivelAccesoConvSucu(int idNivel) throws DaoException
	{
		try
		{
			return (NivelAccConvSucVO) this.session.load(NivelAccConvSucVO.class, new Integer(idNivel));
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.getNivelAccesoConvSucu: " + ex);
			throw new DaoException("PersonaDAO.getNivelAccesoConvSucu: ", ex);
		}
	}

	/**
	 * 
	 * @return niveles acceso sucursal por convenio
	 * @throws DaoException
	 */
	public List getListaNivelesAccesoConvSucu() throws DaoException
	{
		try
		{
			return this.session.createCriteria(NivelAccConvSucVO.class).addOrder(Order.asc("nombre")).list();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.getListaNivelesAccesoConvSucu: " + ex);
			throw new DaoException("PersonaDAO.getListaNivelesAccesoConvSucu: ", ex);
		}
	}

	/**
	 * 
	 * @param admin
	 * @throws DaoException
	 */
	public void guardaAdministrador(AdministradorVO admin) throws DaoException
	{
		try
		{
			AdministradorVO a = (AdministradorVO) this.session.get(AdministradorVO.class, new Integer(admin.getIdAdmin()));
			if (a == null)
				this.session.save(admin);
			else
				this.session.merge(admin);
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.guardaAdministrador: " + ex);
			throw new DaoException("PersonaDAO.guardaAdministrador: ", ex);
		}
	}

	/**
	 * 
	 * @param rutInicio
	 * @param rutDestino
	 * @throws DaoException
	 */
	public void transferirPermisos(int rutInicio, int rutDestino) throws DaoException
	{
		try
		{
			logger.info("transferirPermisos:rutInicio:" + rutInicio + ":rutDestino:" + rutDestino + "::");
			PersonaVO personaDestino = (PersonaVO) this.session.get(PersonaVO.class, new Integer(rutDestino));
			List permEnc = this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEncargado", new Integer(rutInicio))).list();
			logger.info("transferirPermisos:numPermisosEncargado encontrados:" + permEnc.size() + "::");
			if (permEnc.size() > 0)
				this.transfEncargado(rutDestino, permEnc, personaDestino);

			logger.info("transferirPermisos:a transferir permisos lector::");
			this.transfLector(rutInicio, rutDestino);

			List permAdmin = this.session.createCriteria(EmpresaVO.class).add(Restrictions.eq("idAdmin", new Long(rutInicio))).list();
			logger.info("transferirPermisos:numPermisosAdmin encontrados:" + permAdmin.size() + "::");
			if (permAdmin.size() > 0)
				this.transfAdministrador(rutDestino, permAdmin);
			logger.info("transferirPermisos:fin proceso: rutInicio:" + rutInicio + ":rutDestino:" + rutDestino + "::");
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:transferirPermisos: " + ex);
			throw new DaoException("PersonaDAO:transferirPermisos: ", ex);
		}
	}

	/**
	 * 
	 * @param rutDestino
	 * @param permAdmin
	 */
	private void transfAdministrador(int rutDestino, List permAdmin)
	{
		AdministradorVO admin = (AdministradorVO) this.session.get(AdministradorVO.class, new Integer(rutDestino));
		if (admin == null)
		{
			logger.info("transferirPermisos:usuario destino no era admin, agregando::");
			admin = new AdministradorVO(rutDestino);
			admin.setHabilitado(Constants.COD_HABILITACION_ADMIN);
			this.session.save(admin);
		}

		for (Iterator it = permAdmin.iterator(); it.hasNext();)
		{
			EmpresaVO empresa = (EmpresaVO) it.next();
			empresa.setIdAdmin(new Long(rutDestino));
			this.session.merge(empresa);
		}
	}

	/**
	 * 
	 * @param rutInicio
	 * @param rutDestino
	 * @param personaDestino
	 */
	private void transfLector(int rutInicio, int rutDestino)
	{
		LectorEmpresaVO lector = (LectorEmpresaVO) this.session.get(LectorEmpresaVO.class, new Integer(rutDestino));
		if (lector == null)
		{
			logger.info("transferirPermisos:usuario destino no era lector, agregando::");
			lector = new LectorEmpresaVO(rutDestino);
			lector.setHabilitado(Constants.COD_HABILITACION_LECTOR);
			this.session.save(lector);
		}

		List permisosGrupo = this.session.createCriteria(LectorEmpresaGrupoConvenioVO.class).add(Restrictions.eq("idLectorEmpresa", new Integer(rutInicio))).list();
		for (Iterator it = permisosGrupo.iterator(); it.hasNext();)
		{
			LectorEmpresaGrupoConvenioVO permOld = (LectorEmpresaGrupoConvenioVO) it.next();
			LectorEmpresaGrupoConvenioVO permNew = new LectorEmpresaGrupoConvenioVO(rutDestino, permOld.getIdGrupoConvenio());

			List listTmp = this.session.createCriteria(LectorEmpresaGrupoConvenioVO.class).add(Restrictions.eq("idGrupoConvenio", new Integer(permOld.getIdGrupoConvenio()))).add(
					Restrictions.eq("idLectorEmpresa", new Integer(rutDestino))).list();
			if (listTmp.size() == 0)
				this.session.save(permNew);
			this.session.delete(permOld);
			this.session.flush();
		}

		List permisosEmpresa = this.session.createCriteria(LectorEmpresaEmpresaVO.class).add(Restrictions.eq("idLectorEmpresa", new Integer(rutInicio))).list();
		for (Iterator it = permisosEmpresa.iterator(); it.hasNext();)
		{
			LectorEmpresaEmpresaVO permOld = (LectorEmpresaEmpresaVO) it.next();
			LectorEmpresaEmpresaVO permNew = new LectorEmpresaEmpresaVO(rutDestino, permOld.getIdEmpresa());

			List listTmp = this.session.createCriteria(LectorEmpresaEmpresaVO.class).add(Restrictions.eq("idEmpresa", new Integer(permOld.getIdEmpresa()))).add(
					Restrictions.eq("idLectorEmpresa", new Integer(rutDestino))).list();
			if (listTmp.size() == 0)
				this.session.save(permNew);
			this.session.delete(permOld);
			this.session.flush();
		}

		List permisosConvenio = this.session.createCriteria(LectorEmpresaConvenioVO.class).add(Restrictions.eq("idLectorEmpresa", new Integer(rutInicio))).list();
		for (Iterator it = permisosConvenio.iterator(); it.hasNext();)
		{
			LectorEmpresaConvenioVO permOld = (LectorEmpresaConvenioVO) it.next();
			LectorEmpresaConvenioVO permNew = new LectorEmpresaConvenioVO(rutDestino, permOld.getIdConvenio(), permOld.getIdEmpresa());

			List listTmp = this.session.createCriteria(LectorEmpresaConvenioVO.class).add(Restrictions.eq("idEmpresa", new Integer(permOld.getIdEmpresa()))).add(
					Restrictions.eq("idConvenio", new Integer(permOld.getIdConvenio()))).add(Restrictions.eq("idLectorEmpresa", new Integer(rutDestino))).list();
			if (listTmp.size() == 0)
				this.session.save(permNew);
			this.session.delete(permOld);
			this.session.flush();
		}

		List permisosSucursal = this.session.createCriteria(LectorEmpresaSucursalVO.class).add(Restrictions.eq("idLectorEmpresa", new Integer(rutInicio))).list();
		for (Iterator it = permisosSucursal.iterator(); it.hasNext();)
		{
			LectorEmpresaSucursalVO permOld = (LectorEmpresaSucursalVO) it.next();
			LectorEmpresaSucursalVO permNew = new LectorEmpresaSucursalVO(rutDestino, permOld.getIdEmpresa(), permOld.getIdConvenio(), permOld.getIdSucursal());

			List listTmp = this.session.createCriteria(LectorEmpresaSucursalVO.class).add(Restrictions.eq("idEmpresa", new Integer(permOld.getIdEmpresa()))).add(
					Restrictions.eq("idConvenio", new Integer(permOld.getIdConvenio()))).add(Restrictions.eq("idSucursal", permOld.getIdSucursal())).add(
					Restrictions.eq("idLectorEmpresa", new Integer(rutDestino))).list();
			if (listTmp.size() == 0)
				this.session.save(permNew);
			this.session.delete(permOld);
			this.session.flush();
		}
	}

	/**
	 * 
	 * @param rutDestino
	 * @param permEnc
	 * @param personaDestino
	 */
	private void transfEncargado(int rutDestino, List permEnc, PersonaVO personaDestino)
	{
		EncargadoVO enc = (EncargadoVO) this.session.get(EncargadoVO.class, new Integer(rutDestino));
		if (enc == null)
		{
			logger.info("transferirPermisos:usuario destino no era encargado, agregando::");
			enc = new EncargadoVO(rutDestino);
			enc.setHabilitado(Constants.COD_HABILITACION_ENCARGADO);
			this.session.save(enc);
		}

		for (Iterator it = permEnc.iterator(); it.hasNext();)
		{
			EncargadoConvenioVO permOld = (EncargadoConvenioVO) it.next();
			EncargadoConvenioVO permNew = new EncargadoConvenioVO(permOld);
			permNew.setIdEncargado(rutDestino);
			permNew.setPersona(personaDestino);

			List listTmp = this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEmpresa", new Integer(permNew.getIdEmpresa()))).add(
					Restrictions.eq("idConvenio", new Integer(permNew.getIdConvenio()))).add(Restrictions.eq("idEncargado", new Integer(rutDestino))).list();
			EncargadoConvenioVO permTmp = null;
			for (Iterator it2 = listTmp.iterator(); it2.hasNext();)
			{
				permTmp = (EncargadoConvenioVO) it2.next();
				this.session.delete(permTmp);
			}
			this.session.flush();

			this.session.save(permNew);
			this.session.delete(permOld);
			this.session.flush();
		}
	}

	/**
	 * 
	 * @param rut
	 * @param nombre
	 * @param apellidos
	 * @param codigoGrupoConvenio
	 * @param nombreGrupoConvenio
	 * @param rutEmpresa
	 * @param razonSocial
	 * @param tipoObjeto
	 * @return lista personas
	 * @throws DaoException
	 */
	private List getListaPersonas(String rut, String nombre, String apellidos, String codigoGrupoConvenio, String nombreGrupoConvenio, String rutEmpresa, String razonSocial, String tipoObjeto)
			throws DaoException
	{
		try
		{
			StringBuffer hqlQuery = new StringBuffer("select p from PersonaVO p , "+tipoObjeto + " e where p.id = e.id ");
			//StringBuffer hqlQuery = new StringBuffer("from PersonaVO p where p.id != 0 and p.id in (select e.id from " + tipoObjeto + " e)");
			List params = new ArrayList();
			if (rut != null && !("".equals(rut.trim())))
			{
				String rutTmp = rut.trim().replaceAll("\\.", "");
				int pos = rutTmp.indexOf('-');
				if (pos > -1)
					rutTmp = rutTmp.substring(0, pos);
				logger.info("rut persona busqueda sin - ni DV:" + rutTmp + "::");

				hqlQuery.append(" and cast((cast(p.id as integer)) as string) like '" + rutTmp + "%' ");
			}
			if (nombre != null && !"".equals(nombre.trim()))
			{
				hqlQuery.append(" and p.nombres like ?");
				params.add("%" + nombre + "%");
			}
			if (apellidos != null && !"".equals(apellidos.trim()))
			{
				String apels[] = apellidos.split(" ");
				if (apels.length > 1)
				{
					hqlQuery.append(" and (p.apellidoPaterno like ? or p.apellidoMaterno like ?)");
					params.add("%" + apels[0] + "%");
					params.add("%" + apels[apels.length - 1] + "%");					
				} else
				{
					hqlQuery.append(" and (p.apellidoPaterno like ? or p.apellidoMaterno like ?)");
					params.add("%" + apellidos + "%");
					params.add("%" + apellidos + "%");
				}
			}
			boolean nombreGrupo = nombreGrupoConvenio != null && !"".equals(nombreGrupoConvenio.trim());
			boolean codigoGrupo = codigoGrupoConvenio != null && !"".equals(codigoGrupoConvenio.trim());
			if (codigoGrupo || nombreGrupo)
			{
				if (tipoObjeto.equals("EncargadoVO"))
					hqlQuery
							.append(" and p.id in (select distinct eacs.idEncargado from EncargadoConvenioVO eacs, ConvenioVO c where eacs.idConvenio=c.idConvenio and eacs.idEmpresa = c.idEmpresa and c.idGrupoConvenio != 0 and c.idGrupoConvenio");
				else
					hqlQuery
							.append(" and p.id in (select distinct empresa.idAdmin from EmpresaVO empresa, ConvenioVO c where empresa.idEmpresa = c.idEmpresa and c.idGrupoConvenio != 0 and c.idGrupoConvenio");
				if (codigoGrupo)
				{
					hqlQuery.append(" = ?");
					params.add(new Integer(codigoGrupoConvenio));
				}
				if (nombreGrupo)
				{
					if (codigoGrupo)
						hqlQuery.append(" and c.idGrupoConvenio");
					hqlQuery.append(" in (select gc.idGrupoConvenio from GrupoConvenioVO gc where gc.nombre like ?");
					String tmp = "%" + Utils.transforToDB(nombreGrupoConvenio) + "%";
					params.add(tmp);
					if (tmp.indexOf('\\') != -1)
						hqlQuery.append(" ESCAPE '\\'");
					hqlQuery.append(")");
				}
				hqlQuery.append(")");
			}
			boolean rutE = rutEmpresa != null && !"".equals(rutEmpresa.trim());
			boolean razon = razonSocial != null && !"".equals(razonSocial.trim());
			if (rutE || razon)
			{
				if (tipoObjeto.equals("EncargadoVO"))
					hqlQuery.append(" and p.id in (select distinct eacs.idEncargado from EncargadoConvenioVO eacs, EmpresaVO e where eacs.idEmpresa != 0 and eacs.idEmpresa=e.idEmpresa");
				else if (tipoObjeto.equals("AdministradorVO"))
					hqlQuery.append(" and p.id in (select distinct e.idAdmin from EmpresaVO e where e.idEmpresa != " + Constants.RUT_EMPRESA_FALSA);
				if (rutE)
				{
					String rutEmpresaTmp = rutEmpresa.trim().replaceAll("\\.", "");
					logger.info("rut Empresa busqueda sin puntos:" + rutEmpresaTmp + "::");
					int pos = rutEmpresaTmp.indexOf('-');
					if (pos > -1)
						rutEmpresaTmp = rutEmpresaTmp.substring(0, pos);
					logger.info("rut Empresa busqueda sin - ni DV:" + rutEmpresaTmp + "::");
					hqlQuery.append(" and cast((cast(e.idEmpresa as integer)) as string) like '" + rutEmpresaTmp + "%' ");
				}
				if (razon)
				{
					hqlQuery.append(" and e.idEmpresa in (select emp.idEmpresa from EmpresaVO emp where emp.razonSocial like ?");
					String tmp = "%" + Utils.transforToDB(razonSocial.toUpperCase()) + "%";
					params.add(tmp);
					if (tmp.indexOf('\\') != -1)
						hqlQuery.append(" ESCAPE '\\'");
					hqlQuery.append(")");
				}
				hqlQuery.append(")");
			}
			hqlQuery.append(" order by p.id");
			Query query = this.session.createQuery(hqlQuery.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				query.setParameter(par++, iter.next());

			logger.info("query busqueda:" + hqlQuery.toString() + "::");
			
			return query.list();
		} catch (Exception ex)
		{
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:getListaPersonas: " + ex);
			throw new DaoException("PersonaDAO:getListaPersonas: ", ex);
		}
	}

	/**
	 * 
	 * @param rut
	 * @return lista representantes
	 * @throws DaoException
	 */
	public PersonaVO getRepresentante(long rut) throws DaoException
	{
		PersonaVO representante = null;
		try
		{
			representante = (PersonaVO) this.session.createCriteria(PersonaVO.class).add(Restrictions.eq("idPersona", new Integer(String.valueOf(rut)))).uniqueResult();
		} catch (Exception ex)
		{
			logger.error("Error en PersonaDAO:getRepresentante");
			throw new DaoException("Error en PersonaDAO:getPersona", ex);
		}
		return representante;
	}

	/**
	 * 
	 * @return lista niveles acceso
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
	 * Asigna al Administrador en una o más Cajas
	 * 
	 * @param idAdminCaja
	 * @param idCCAF
	 * @throws DaoException
	 */
	public void guardaAdministradorCajas(int idAdminCaja, int idCCAF) throws DaoException {
		try {
			AdministradorCajaVO adminCaja;
			EntidadCCAFVO entCaja;
			if (idCCAF == Constants.ID_CCAF_TODAS) {
				List entidadesCCAF = this.session.createCriteria(EntidadCCAFVO.class).add(Restrictions.ne("id", new Integer(0))).list();
				for (Iterator it = entidadesCCAF.iterator(); it.hasNext();) {
					entCaja = (EntidadCCAFVO) it.next();
					adminCaja = new AdministradorCajaVO(idAdminCaja, entCaja.getId());
					this.session.save(adminCaja);
					this.session.flush();
				}
			} else {
				adminCaja = new AdministradorCajaVO(idAdminCaja, idCCAF);
				this.session.save(adminCaja);
				this.session.flush();
			}
		} catch (Exception ex) {
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.guardaAdministradorCajas: " + ex);
			throw new DaoException("PersonaDAO.guardaAdministradorCajas: ", ex);
		}
	}

	/**
	 * Obtiene los registros (idAdministrador, idCaja) que indican sobre cuales cajas es administrador el usuario
	 * 
	 * @param idAdmCaja
	 * @return
	 * @throws DaoException
	 */
	public List getAdmCajas(int idAdmCaja)throws DaoException {
		try {
			List admCajas = this.session.createCriteria(AdministradorCajaVO.class)
										.add(Restrictions.eq("idAdminCaja", new Integer(idAdmCaja)))
										.list();
			return admCajas;
		} catch (Exception ex) {
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO.getAdmCajas: " + ex);
			throw new DaoException("PersonaDAO.getAdmCajas: ", ex);
		}
	}

	/**
	 * Elimina los registros que indican sobre cuales cajas es administrador el usuario
	 * 
	 * @param idAdminCaja
	 * @throws DaoException
	 */
	public void borraAdministradorCajas(int idAdminCaja) throws DaoException {
		try	{

			List listaBorrar = this.getAdmCajas(idAdminCaja);
			AdministradorCajaVO admCaja;

			for (Iterator it = listaBorrar.iterator(); it.hasNext();) {
				admCaja = (AdministradorCajaVO) it.next();
				this.session.delete(admCaja);
			}

			this.session.flush();
		} catch (Exception ex) {
			logger.error("Ha ocurrido la siguiente excepcion en PersonaDAO:borraAdministradorCajas:", ex);
			throw new DaoException("PersonaDAO:borraAdministradorCajas:", ex);
		}
	}
}