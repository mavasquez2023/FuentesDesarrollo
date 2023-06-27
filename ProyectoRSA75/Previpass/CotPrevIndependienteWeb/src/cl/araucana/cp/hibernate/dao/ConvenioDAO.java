package cl.araucana.cp.hibernate.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NivelAccConvSucVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) ConvenioDao.java 1.30 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.30
 */
public class ConvenioDAO
{
	private static Logger logger = Logger.getLogger(ConvenioDAO.class);
	private Session session;

	public ConvenioDAO(Session session)
	{
		this.session = session;
	}
	/**
	 * convenio
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public ConvenioVO getConvenio(int idEmpresa, int idConvenio) throws DaoException
	{
		try
		{
			ConvenioVO convenio = new ConvenioVO(idEmpresa, idConvenio);
			convenio = (ConvenioVO) this.session.get(ConvenioVO.class, convenio);

			if (convenio == null)
				throw new Exception("No encontro convenio idConvenio: " + idConvenio + ", idEmpresa: " + idEmpresa);
			return convenio;
		} catch (Exception ex)
		{
			logger.error("Error en ConvenioDAO:getConvenio");
			throw new DaoException("Error al leer convenio idEmpresa: " + idEmpresa + ", idConvenio: " + idConvenio, ex);
		}
	}
	/**
	 * grupo convenio
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public GrupoConvenioVO getGrupoConvenioGet(int idGrupoConvenio) throws DaoException {

		try
		{
			return (GrupoConvenioVO) this.session.get(GrupoConvenioVO.class, new Integer(idGrupoConvenio));			
		} catch (Exception ex)
		{
			logger.error("Error en ConvenioDAO:getGrupoConvenio");
			throw new DaoException("Error al leer grupo convenio getGrupoConvenioGet: " + idGrupoConvenio + "::", ex);
		}
	}
	/**
	 * grupo convenio
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public boolean getGrupoConvenioGetActivo(int idGrupoConvenio) throws DaoException {

		try
		{
			Criteria crit = this.session.createCriteria(GrupoConvenioVO.class)
				.add(Restrictions.eq("idGrupoConvenio", new Integer(idGrupoConvenio)))
				.add(Restrictions.eq("habilitado", new Integer("1")));
			if(crit.list().size()>0)
				return true;
			return false;
		} catch (Exception ex)
		{
			logger.error("Error en ConvenioDAO.getGrupoConvenio");
			throw new DaoException("Error al leer grupo convenio getGrupoConvenioGet: " + idGrupoConvenio + "::", ex);
		}
	}
	/**
	 * grupo convenio
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public GrupoConvenioVO getGrupoConvenio(int idGrupoConvenio) throws DaoException
	{
		try
		{
			return (GrupoConvenioVO) this.session.load(GrupoConvenioVO.class, new Integer(idGrupoConvenio));
		} catch (Exception ex)
		{
			logger.error("Error en ConvenioDAO.getGrupoConvenio");
			throw new DaoException("Error al leer grupo convenio idGrupoConvenio: " + idGrupoConvenio + "::", ex);
		}
	}

	/**
	 * entidad caja
	 * @param idCaja
	 * @return
	 * @throws DaoException
	 */
	public EntidadCCAFVO getCaja(int idCaja) throws DaoException
	{
		try
		{
			logger.info("ConvenioDAO:getCaja:idCaja:" + idCaja + "::");
			EntidadCCAFVO caja = (EntidadCCAFVO) this.session.get(EntidadCCAFVO.class, new Integer(idCaja));

			if (caja == null)
				throw new Exception("No encontro caja idCaja: " + idCaja + "::");
			return caja;
		} catch (Exception ex)
		{
			logger.error("Error en ConvenioDAO:getCaja");
			throw new DaoException("Error al cargar caja:" + idCaja + ":", ex);
		}
	}
	/**
	 * entidad mutual
	 * @param idMutual
	 * @return
	 * @throws DaoException
	 */
	public EntidadMutualVO getMutual(int idMutual) throws DaoException
	{
		try
		{
			logger.info("ConvenioDAO:getMutual:idMutual" + idMutual + "::");
			EntidadMutualVO mutual = (EntidadMutualVO) this.session.get(EntidadMutualVO.class, new Integer(idMutual));

			if (mutual == null)
				throw new Exception("No encontro caja idCaja: " + idMutual + "::");
			return mutual;
		} catch (Exception ex)
		{
			logger.error("Error en ConvenioDAO:getMutual");
			throw new DaoException("Error al cargar mutual:" + idMutual + ":", ex);
		}
	}
	/**
	 * convenios empresa
	 * @param idEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getConveniosEmpresa(boolean flag, int idEmpresa) throws DaoException
	{
		try
		{
			Criteria crit = this.session.createCriteria(ConvenioVO.class)
				.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa)));
			if (flag)
				crit = crit.add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_CONVENIO)));
			return crit.addOrder(Order.asc("descripcion")).list();
		} catch (Exception ex)
		{
			logger.error("Error en ConvenioDAO.getConveniosEmpresa: " + ex);
			throw new DaoException("Error en ConvenioDAO.getConveniosEmpresa::", ex);
		}
	}
	/**
	 * guardar convenio
	 * @param convenio
	 * @throws DaoException
	 */
	public void guardarConvenio(ConvenioVO convenio) throws DaoException
	{
		try
		{
			logger.info("ConvenioDAO:guardarConvenio::");
			this.session.save(convenio);
		} catch (Exception ex)
		{
			logger.error("Error en ConvenioDAO.guardarConvenio: " + ex);
			throw new DaoException("Error en ConvenioDAO.guardarConvenio::", ex);
		}
	}
	/**
	 * convenios empresa in
	 * @param colEmps
	 * @return
	 * @throws DaoException
	 */
	public List getConveniosEmpresasIn(Collection colEmps) throws DaoException {
		try {
			if ((colEmps == null) || colEmps.isEmpty())
				return Collections.EMPTY_LIST;
			
			return this.session.createCriteria(ConvenioVO.class)
				.add(Restrictions.in("idEmpresa", colEmps))
				.add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_CONVENIO)))
				.addOrder(Order.asc("idEmpresa"))
				.addOrder(Order.asc("descripcion"))
				.list();
		} catch (Exception ex) {
			logger.error("Error en ConvenioDAO.getConveniosEmpresasIn::" + ex);
			throw new DaoException("Error en ConvenioDAO.getConveniosEmpresasIn::", ex);
		}
	}
	
	/**
	 * grupos convenios in
	 * @param colGConvs
	 * @return
	 * @throws DaoException
	 */
	public List getGruposConveniosIn(Collection colGConvs) throws DaoException 
	{
		try 
		{
			if ((colGConvs == null) || colGConvs.isEmpty())
				return Collections.EMPTY_LIST;
			return this.session.createCriteria(GrupoConvenioVO.class)
				.add(Restrictions.in("idGrupoConvenio", colGConvs))
				.add(Restrictions.ne("habilitado", new Integer(0)))
				.list();
		} catch (Exception ex) {
			logger.error("Error en ConvenioDAO.getGruposConveniosIn::" + ex);
			throw new DaoException("Error en ConvenioDAO.getGruposConveniosIn::", ex);
		}
	}
	/**
	 * grupos convenios admin
	 * @param idPersona
	 * @return
	 * @throws DaoException
	 */
	public List getGruposConveniosAdmin(int idPersona) throws DaoException 
	{
		try 
		{
			Set idsGrupos = new HashSet();
			Set idsEmpresas = new HashSet();
			//List listaEmpresas = this.session.createCriteria(EmpresaVO.class).add(Restrictions.eq("idAdmin", new Long(idPersona))).list();
			List listaEmpresas = this.session.createCriteria(EmpresaVO.class).add(Restrictions.eq("idAdmin", new Long(idPersona))).add(Restrictions.eq("tipo", Constants.TIPO_EMPRESA_INDEPENDIENTE)).list();
			if (listaEmpresas.size() > 0)
			{
				for (Iterator it = listaEmpresas.iterator(); it.hasNext();)
					idsEmpresas.add(new Integer(((EmpresaVO)it.next()).getIdEmpresa()));
				List listaConvenios;
				if (!idsEmpresas.isEmpty())
					listaConvenios= this.session.createCriteria(ConvenioVO.class).add(Restrictions.in("idEmpresa", idsEmpresas)).list();
				else
					listaConvenios=new ArrayList();
				
				if (listaConvenios.size() > 0)
				{
					for (Iterator it = listaConvenios.iterator(); it.hasNext();)
						idsGrupos.add(new Integer(((ConvenioVO)it.next()).getIdGrupoConvenio()));
					if (!idsGrupos.isEmpty())
						return this.session.createCriteria(GrupoConvenioVO.class).add(Restrictions.in("idGrupoConvenio", idsGrupos))
							.add(Restrictions.eq("habilitado", new Integer("1"))).list();
					return new ArrayList();
				}
			}
			return new ArrayList();
		} catch (Exception ex) 
		{
			logger.error("Error en ConvenioDAO.getGruposConveniosAdmin::", ex);
			throw new DaoException("Error en ConvenioDAO.getGruposConveniosAdmin::", ex);
		}
	}

	/**
	 * convenios in
	 * @param colConvs
	 * @return
	 * @throws DaoException
	 */
	public List getConveniosIn(Collection colConvs) throws DaoException 
	{
		try 
		{
			if ((colConvs == null) || colConvs.isEmpty())
				return Collections.EMPTY_LIST;
			List lista = new ArrayList();
			ConvenioVO idConvenio;
			for (Iterator it = colConvs.iterator(); it.hasNext();) 
			{
				idConvenio = (ConvenioVO) it.next();
				lista.add(this.session.createCriteria(ConvenioVO.class)
						.add(Restrictions.eq("idEmpresa", new Integer(idConvenio.getIdEmpresa())))
						.add(Restrictions.eq("idConvenio", new Integer(idConvenio.getIdConvenio())))
						.uniqueResult());
			}
			return lista;
		} catch (Exception ex) 
		{
			logger.error("Error en ConvenioDAO.getConveniosIn::" + ex);
			throw new DaoException("Error en ConvenioDAO.getConveniosIn::", ex);
		}
	}

	/**
	 * convenio no excp
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public ConvenioVO getConvenioNoExcp(int idEmpresa, int idConvenio) throws DaoException
	{
		try
		{
			logger.info("ConvenioDAO:getConvenio:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + "::");
			ConvenioVO convenio = new ConvenioVO(idEmpresa, idConvenio);
			return (ConvenioVO) this.session.get(ConvenioVO.class, convenio);
		} catch (Exception ex)
		{
			logger.error("Error en ConvenioDAO:getConvenio");
			throw new DaoException("Error al leer convenio idEmpresa: " + idEmpresa + ", idConvenio: " + idConvenio, ex);
		}
	}

	/**
	 * lista niveles acceso
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
			logger.error("Ha ocurrido la siguiente excepcion en ConvenioDAO:getListaNivelesAcceso: " + ex);
			throw new DaoException("ConvenioDAO:getListaNivelesAcceso: ", ex);
		}
	}

	/************************
	 * listas con convenios filtrados por permisos
	 ************************/

	/**
	 * Retorna lista ordenada de convenios para las cuales el usuario asociado a 'login' posee permisos de escritura
	 * es decir, si es administrador o encargado escritor
	 * @param idPersona identificador del usuario
	 * @param rutEmpresa identificador de la empresa
	 * @return lista ConvenioVO
	 */
	public List getConveniosEscritura(int idPersona, int rutEmpresa) throws DaoException
	{
		try
		{
			logger.info("ConvenioDAO:getConveniosEscritura");
			Set conjuntoConvenios = new HashSet(); //para que no se repitan

			//empresas habilitadas, para las cuales la persona es administrador (y esta habilitado)
			AdministradorVO adminVO = (AdministradorVO)this.session.get(AdministradorVO.class, new Integer(idPersona));
			if (adminVO != null && adminVO.getHabilitado() == Constants.COD_HABILITACION_ADMIN)//La persona es administrador, y esta habilitado
			{
				EmpresaVO emp = (EmpresaVO)this.session.get(EmpresaVO.class, new Integer(rutEmpresa));
				if (emp != null && emp.getHabilitada().intValue() == Constants.COD_HABILITACION_EMPRESA && emp.getIdAdmin().intValue() == idPersona)
					conjuntoConvenios.addAll(this.session.createCriteria(ConvenioVO.class).add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa)))
						.add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_CONVENIO))).list());
			}

			//convenios habilitados, para los cuales la persona es encargado (escritor, y esta habilitado)
			EncargadoVO encVO = (EncargadoVO)this.session.get(EncargadoVO.class, new Integer(idPersona));
			if (encVO != null && encVO.getHabilitado() == Constants.COD_HABILITACION_ENCARGADO)//La persona es encargado, y esta habilitado
			{
				List listaEncargado = this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEncargado", new Integer(idPersona)))
						.add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa))).add(Restrictions.eq("idNivelAcceso", new Integer(Constants.NIVEL_ACC_CONV_SUC_EDITOR))).list();
				Set idsConvenios = new HashSet();
				for (Iterator it = listaEncargado.iterator(); it.hasNext();)
					idsConvenios.add(new Integer(((EncargadoConvenioVO)it.next()).getIdConvenio()));
				if (!idsConvenios.isEmpty())
					conjuntoConvenios.addAll(this.session.createCriteria(ConvenioVO.class).add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa)))
							.add(Restrictions.in("idConvenio", idsConvenios)).add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_CONVENIO))).list());
			}

			List listaConvenios = new ArrayList(conjuntoConvenios);
			Collections.sort(listaConvenios);
			return listaConvenios;
		} catch (Exception ex)
		{
			logger.error("ERROR ConvenioDAO:getConveniosEscritura:persona:" + idPersona + ":empresa:" + rutEmpresa + "::", ex);
			throw new DaoException("Problemas obteniendo convenio con permisos de escritura para persona:" + idPersona + ":empresa:" + rutEmpresa + "::", ex);
		}
	}

	/**
	 * Retorna lista ordenada de convenios para las cuales el usuario asociado a 'login' posee algun tipo de permiso 
	 * es decir, si es administrador o encargado escritor o lector
	 * @param idPersona identificador del usuario
	 * @param rutEmpresa identificador de la empresa
	 * @return lista ConvenioVO
	 */
	public List getConveniosPermisos(int idPersona, int rutEmpresa, boolean flag) throws DaoException
	{
		try
		{
			logger.debug("ConvenioDAO:getConveniosPermisos:" + idPersona + ":rutEmpresa:" + rutEmpresa + "::");
			Set conjuntoConvenios = new HashSet(); //para que no se repitan
			Criteria crit;

			//empresas habilitadas, para las cuales la persona es administrador (y esta habilitado)
			AdministradorVO adminVO = (AdministradorVO)this.session.get(AdministradorVO.class, new Integer(idPersona));
			if (adminVO != null && adminVO.getHabilitado() == Constants.COD_HABILITACION_ADMIN)//La persona es administrador, y esta habilitado
			{
				EmpresaVO emp = (EmpresaVO)this.session.get(EmpresaVO.class, new Integer(rutEmpresa));
				if (emp != null && emp.getHabilitada().intValue() == Constants.COD_HABILITACION_EMPRESA && emp.getIdAdmin().intValue() == idPersona)
				{
					crit = this.session.createCriteria(ConvenioVO.class).add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa)));
					if (flag)
						crit = crit.add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_CONVENIO)));
					conjuntoConvenios.addAll(crit.list());
				}
			}
			logger.debug("n conv admin:" + conjuntoConvenios.size() + "::");

			//convenios habilitados, para los cuales la persona es encargado (escritor, y esta habilitado)
			EncargadoVO encVO = (EncargadoVO)this.session.get(EncargadoVO.class, new Integer(idPersona));
			if (encVO != null && encVO.getHabilitado() == Constants.COD_HABILITACION_ENCARGADO)//La persona es encargado, y esta habilitado
			{
				List listaEncargado = this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEncargado", new Integer(idPersona)))
						.add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa))).list();
				Set idsConvenios = new HashSet();
				for (Iterator it = listaEncargado.iterator(); it.hasNext();)
					idsConvenios.add(new Integer(((EncargadoConvenioVO)it.next()).getIdConvenio()));
				logger.debug("n conv enc:" + idsConvenios.size() + "::");
				logger.debug("data:rutEmpresa:" + rutEmpresa + ":codH:" + Constants.COD_HABILITACION_CONVENIO + "::" + idsConvenios.size() + "::");
				if (!idsConvenios.isEmpty())
				{
					crit = this.session.createCriteria(ConvenioVO.class).add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa))).add(Restrictions.in("idConvenio", idsConvenios));
					if (flag)
						crit = crit.add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_CONVENIO)));
					conjuntoConvenios.addAll(crit.list());
				}
			}

			List listaConvenios = new ArrayList(conjuntoConvenios);
			Collections.sort(listaConvenios);
			logger.debug("n conv:" + listaConvenios.size() + "::");
			return listaConvenios;
		} catch (Exception ex)
		{
			logger.error("ERROR ConvenioDAO:getConveniosPermisos:persona:" + idPersona + ":empresa:" + rutEmpresa + "::", ex);
			throw new DaoException("Problemas obteniendo convenio con cualquier permiso para persona:" + idPersona + ":empresa:" + rutEmpresa + "::", ex);
		}
	}

	/**
	 * Retorna el nivel de permisos sobre empresa/convenio, para la persona identificada por idPersona 
	 * 	- si es administrador o encargado escritor: Constants.NIVEL_ACC_CONV_SUC_EDITOR
	 * 	- si es encargado lector: Constants.NIVEL_ACC_CONV_SUC_LECTOR
	 * @param idPersona identificador del usuario
	 * @param idConvenio identificador del convenio
	 * @param rutEmpresa identificador de la empresa
	 * @return nivel acceso
	 */
	public int getNivelPermiso(int idPersona, int idConvenio, int rutEmpresa)
	{
		logger.debug("ConvenioDAO:getNivelPermiso:idPersona:" + idPersona + ":idConvenio:" + idConvenio + ":rutEmpresa:" + rutEmpresa + "::");
		AdministradorVO adminVO = (AdministradorVO)this.session.get(AdministradorVO.class, new Integer(idPersona));
		if (adminVO != null && adminVO.getHabilitado() == Constants.COD_HABILITACION_ADMIN)//La persona es administrador, y esta habilitado
		{
			EmpresaVO emp = (EmpresaVO)this.session.get(EmpresaVO.class, new Integer(rutEmpresa));
			if (emp != null && emp.getHabilitada().intValue() == Constants.COD_HABILITACION_EMPRESA && emp.getIdAdmin().intValue() == idPersona)
				if (this.session.createCriteria(ConvenioVO.class).add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa)))
						.add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_CONVENIO))).list().size() > 0)
				{
					logger.debug("ConvenioDAO:getNivelPermiso es admin empresa = editor");
					return Constants.NIVEL_ACC_CONV_SUC_EDITOR;
				}
		}
		
		EncargadoVO encVO = (EncargadoVO)this.session.get(EncargadoVO.class, new Integer(idPersona));
		if (encVO != null && encVO.getHabilitado() == Constants.COD_HABILITACION_ENCARGADO)//La persona es encargado, y esta habilitado
		{
			List listaEncargado = this.session.createCriteria(EncargadoConvenioVO.class).add(Restrictions.eq("idEncargado", new Integer(idPersona)))
					.add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio))).list();

			if (listaEncargado.size() > 0)
			{
				for (Iterator it2 = listaEncargado.iterator(); it2.hasNext();)
				{
					EncargadoConvenioVO enc = (EncargadoConvenioVO)it2.next();
					logger.debug("ConvenioDAO:getNivelPermiso es encargado:" + enc.getIdNivelAcceso() + "::");
					return enc.getIdNivelAcceso();
				}
			}
		}
		return Constants.NIVEL_ACC_CONV_SUC_NADA;
	}

	/**
	 * valida los mapeos de nominas asociados a un grupo de convenios
	 * valida grupo
	 * @param convenio
	 * @return
	 * @throws DaoException
	 */
	public boolean validaMapaNomGrupo(int idGrupoConv, String idsTiposNominas)
	{
		try
		{
			logger.info("ConvenioDAO: validaMapaNomGrupo:idGrupoConv" + idGrupoConv + "::");
			GrupoConvenioVO grupo = (GrupoConvenioVO)this.session.get(GrupoConvenioVO.class, new Integer(idGrupoConv));
			if (grupo == null)
			{
				logger.error("ConvenioDAO: validaMapaNomGrupo:buscando GrupoConv" + idGrupoConv + ": no existe!:");
				return false;
			}
			int mapeo = 0;
			for (int j = 0; j < idsTiposNominas.length(); j++)
			{
				char tp = idsTiposNominas.charAt(j);
				mapeo = grupo.getIdMapaNom(tp);

				List idConceptos = this.session.createCriteria(MapeoConceptoVO.class).setProjection(Projections.distinct(Projections.property("idConcepto")))
				.add(Restrictions.eq("idMapa", new Integer(mapeo)))
				.add(Restrictions.eq("tipoProceso", "" + tp)).list();

				List idConceptosObl = this.session.createCriteria(ConceptoVO.class).setProjection(Projections.distinct(Projections.property("id")))
				.add(Restrictions.eq("tipoProceso", "" + tp)).list();				

				for(int i = 0; i < idConceptosObl.size(); i++)
					if(!idConceptos.contains(idConceptosObl.get(i)))
					{
						logger.info("rechaza validacion:tp:" + tp + ":IdMapaNom:" + mapeo + "::" + ((Integer)idConceptosObl.get(i)).intValue() + 
								":idConceptos:" + idConceptos.size() + ":idConceptosObl:" + idConceptosObl.size() + "::");
						return false;
					}
			}		
		}catch (Exception e)
		{
			logger.error("problemas en validaMapaNomGrupo:", e);
			return false;
		}		
		return true;
	}

	/**
	 * Obtiene los convenios habilitados, no repetidos, del usuario.
	 * @param idUsuario
	 * @param colEmps
	 * @return
	 * @throws DaoException
	 */
	public List getAllConvenios(int idUsuario, Collection colEmps) throws DaoException
	{
		try
		{
			logger.info("ConvenioDAO:getAllConvenios");
			Set resultadoConvenios = new HashSet();

			logger.debug("\nempresas admin:");
			for (Iterator it = colEmps.iterator(); it.hasNext();)
				logger.debug("\tempresa:" + ((Integer)it.next()).intValue() + "::");

			//TODO GMALLEA Se agrega la restriccion tipo para que retorne solo los Independientes
			Collection colEmpsFiltradas= new ArrayList(); 
			List listEmpresaConvenio=null;
			if(!colEmps.isEmpty()){
				listEmpresaConvenio = this.session.createCriteria(EmpresaVO.class).add(Restrictions.in("idEmpresa",colEmps)).add(Restrictions.like("tipo", Constants.TIPO_EMPRESA_INDEPENDIENTE)) .list();
				//listEmpresaConvenio = new ArrayList();
				
				for(Iterator it = listEmpresaConvenio.iterator(); it.hasNext(); ){
					EmpresaVO empresaVO = (EmpresaVO) it.next();
					colEmpsFiltradas.add(new Integer( empresaVO.getIdEmpresa()));
					}
			}
			
			AdministradorVO admVO = (AdministradorVO)this.session.get(AdministradorVO.class, new Integer(idUsuario));
			if (admVO != null && admVO.getHabilitado() == Constants.COD_HABILITACION_ENCARGADO && !colEmps.isEmpty())
			{
				if (!colEmpsFiltradas.isEmpty())
					resultadoConvenios.addAll(this.session.createCriteria(ConvenioVO.class)
						.add(Restrictions.in("idEmpresa", colEmpsFiltradas))
						.add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_CONVENIO)))
						.list());
			}
			logger.debug("\nconvenios admin:");
			for (Iterator it = resultadoConvenios.iterator(); it.hasNext();)
			{
				ConvenioVO c = (ConvenioVO)it.next();
				logger.debug("\tempresa:" + c.getIdEmpresa() + ":conv:" + c.getIdConvenio() + "::");
			}

			EncargadoVO encVO = (EncargadoVO)this.session.get(EncargadoVO.class, new Integer(idUsuario));
			if (encVO != null && encVO.getHabilitado() == Constants.COD_HABILITACION_ENCARGADO)
			{
				//Obtiene los convenios del encargado, esten habilitados o no
				List listaEncargadoConvenio = this.session.createCriteria(EncargadoConvenioVO.class)
					.add(Restrictions.eq("idEncargado", new Integer(idUsuario)))
					.list();

				//Obtiene todas las empresas (no repetidas) de un encargado
				Set listaEmp = new HashSet();
				logger.debug("\nempresas encargado:");
				for (Iterator it = listaEncargadoConvenio.iterator(); it.hasNext();)
				{
					int idEmpresa = ((EncargadoConvenioVO)it.next()).getIdEmpresa();
					listaEmp.add(new Integer(idEmpresa));
					logger.debug("\tempresa:" + idEmpresa + "::");
				}
				//Dejo en un Hash las empresas habilitadas del encargado
				HashMap empsHabilitadas = new HashMap();
				if (!listaEmp.isEmpty())
				{
					//Obtiene las empresas que estan habilitadas y que son del encargado
					//TODO GMALLEA Se agrega la restriccion tipo para que retorne solo los Independientes
					List listaEmps = this.session.createCriteria(EmpresaVO.class)
						.add(Restrictions.in("idEmpresa", listaEmp))
						.add(Restrictions.eq("habilitada", new Integer(Constants.COD_HABILITACION_EMPRESA)))
						.add(Restrictions.like("tipo", Constants.TIPO_EMPRESA_INDEPENDIENTE))
						.list();

					logger.debug("\nempresas encargado habilitada:");
					for (Iterator it = listaEmps.iterator(); it.hasNext();)
					{
						int idEmpresa = ((EmpresaVO)it.next()).getIdEmpresa();
						empsHabilitadas.put("" + idEmpresa, null);
						logger.debug("\tempresa:" + idEmpresa + "::");
					}
				}

				logger.debug("\nconvenios encargado habilitada:");
				for (Iterator it = listaEncargadoConvenio.iterator(); it.hasNext();)
				{
					EncargadoConvenioVO enc = (EncargadoConvenioVO)it.next();
					if (empsHabilitadas.containsKey("" + enc.getIdEmpresa()))
					{
						ConvenioVO c = (ConvenioVO)this.session.get(ConvenioVO.class, new ConvenioVO(enc.getIdEmpresa(), enc.getIdConvenio()));
						if (c != null)
						{
							logger.debug("\tempresa:" + c.getIdEmpresa() + ":conv:" + c.getIdConvenio() + "::");
							resultadoConvenios.add(c);
						}
					}
				}
			}
			List listaConvenios = new ArrayList(resultadoConvenios);
			Collections.sort(listaConvenios);
			return listaConvenios;
		}
		catch (Exception ex) 
		{
			logger.error("Ha ocurrido la siguiente excepcion en ConvenioDAO:getAllConvenios: ", ex);
			throw new DaoException("ConvenioDAO:getAllConvenios: ", ex);
		}
	}

	public int validaHabilitados(int idPersona, int idConvenio, int rutEmpresa)
	{
		AdministradorVO adminVO = (AdministradorVO)this.session.get(AdministradorVO.class, new Integer(idPersona));
		EncargadoVO encVO = (EncargadoVO)this.session.get(EncargadoVO.class, new Integer(idPersona));
		if (adminVO == null && encVO == null)
			return Constants.EST_RECH_USU_DESHABILITADO;
		boolean habilitado = false;		
		if (adminVO != null && adminVO.getHabilitado() == Constants.COD_HABILITACION_ADMIN)
			habilitado = true;
		if (!habilitado && encVO != null && encVO.getHabilitado() == Constants.COD_HABILITACION_ENCARGADO)
			habilitado = true;
		if (!habilitado)
			return Constants.EST_RECH_USU_DESHABILITADO;
		EmpresaVO empresa = (EmpresaVO)this.session.get(EmpresaVO.class, new Integer(rutEmpresa));
		logger.info("\n\n\nestEmp:" +  empresa.getHabilitada().intValue() + "::");
		if (empresa == null || empresa.getHabilitada().intValue() != Constants.COD_HABILITACION_EMPRESA)
			return Constants.EST_RECH_EMP_DESHABILITADA;
		ConvenioVO convenio = (ConvenioVO)this.session.get(ConvenioVO.class, new ConvenioVO(rutEmpresa, idConvenio));
		if (convenio == null || convenio.getHabilitado() != Constants.COD_HABILITACION_CONVENIO)
			return Constants.EST_RECH_CONV_DESHABILITADO;
		return 0;
	}
}
