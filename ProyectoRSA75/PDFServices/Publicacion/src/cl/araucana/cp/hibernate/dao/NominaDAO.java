package cl.araucana.cp.hibernate.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
 * @(#) NominaDao.java 1.19 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author pfrigolett
 * @author cchamblas
 * 
 * @version 1.19
 */
public class NominaDAO
{
	private static Logger log = Logger.getLogger(NominaDAO.class);
	private Session session;	

	public NominaDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * busca en DB2 la nomina asociada al tipo de nomina, empresa y convenio indicado
	 * 
	 * @param tipoNomina
	 * @param idEmpresa
	 * @param idConvenio
	 * @return retorna nomina encontrada, o null si no existe
	 * @throws DaoException
	 */
	public NominaVO getNomina(String tipoNomina, long idEmpresa, int idConvenio) throws DaoException
	{
		NominaVO n = null;
		if (tipoNomina.equals("R") || tipoNomina.equals("r"))
			n = new NominaREVO(idConvenio, (int) idEmpresa);
		else if (tipoNomina.equals("A") || tipoNomina.equals("a"))
			n = new NominaRAVO(idConvenio, (int) idEmpresa);
		else if (tipoNomina.equals("G") || tipoNomina.equals("g"))
			n = new NominaGRVO(idConvenio, (int) idEmpresa);
		else
			n = new NominaDCVO(idConvenio, (int) idEmpresa);

		try
		{
			Object o = this.session.get(n.getClass(), n);
			if (o != null)
				return (NominaVO) o;
			return null;
		} catch (Exception ex)
		{
			log.error("Ha ocurrido una excepcion en NominaDAO:getNomina:", ex);
			throw new DaoException("No se encontro la nomina: " + tipoNomina + ", rut: " + idEmpresa + ", convenio: " + idConvenio);
		}
	}

	/**
	 * busca en DB2 la nomina asociada a la clave primaria indicada en la nomina recibida
	 * 
	 * @param nomina
	 * @return retorna nomina encontrada, o null si no existe
	 * @throws DaoException
	 */
	public NominaVO getNomina(NominaVO nomina) throws DaoException
	{
		try
		{
			return (NominaVO) this.session.load(NominaVO.class, nomina);
		} catch (Exception ex)
		{
			log.error("problemas al obtener nomina", ex);
			throw new DaoException("Problemas en NominaDAO:getNomina", ex);
		}
	}

	/**
	 * retorna la lista de tipos de nominas registrados en DB2
	 * 
	 * @return lista de tipos de nominas recuperados
	 * @throws DaoException
	 */
	public Collection getTiposNominas() throws DaoException
	{
		try
		{
			return this.session.createCriteria(TipoNominaVO.class).addOrder(Order.asc("orden")).list();
		} catch (Exception ex)
		{
			throw new DaoException("Error en NominaDAO:getNominas", ex);
		}
	}

	/**
	 * genera un string con todos los identificadores de tipos de nominas registrados en DB2
	 * 
	 * @return cadena de caracteres con los identificadores de tipos de nominas registrados en DB2
	 * @throws DaoException
	 */
	public String getIdsTiposNominas() throws DaoException
	{
		try
		{
			List lista = this.session.createCriteria(TipoNominaVO.class).addOrder(Order.asc("orden")).list();
			String ids = "";
			for (Iterator it = lista.iterator(); it.hasNext();)
				ids += ((TipoNominaVO) it.next()).getIdTipoNomina().trim();
			return ids;
		} catch (Exception ex)
		{
			throw new DaoException("Error en NominaDAO:getIdsTiposNominas:", ex);
		}
	}

	/**
	 * de acuerto al tipo de nomina recibida ('R', 'G', 'A', 'D') retorna el nombre almacenado en DB2
	 * 
	 * @param tipoNomina
	 * @return
	 * @throws DaoException
	 */
	public String getNombreTipoNomina(String tipoNomina) throws DaoException
	{
		try
		{
			TipoNominaVO tn = (TipoNominaVO) this.session.get(TipoNominaVO.class, tipoNomina);
			if (tn != null)
				return tn.getDescripcion().trim();
			return "";
		} catch (Exception ex)
		{
			throw new DaoException("Error en NominaDAO:getNombreTipoNomina:", ex);
		}
	}

	/**
	 * busca crc
	 * 
	 * @param crc
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
	public boolean buscaCRC(long crc, char tipoProceso) throws DaoException
	{
		log.debug("EnvioDAO:buscaCRC");
		try
		{
			if ((tipoProceso == 'R' || tipoProceso == 'r' ) && this.session.createCriteria(NominaREVO.class).add(Restrictions.eq("crc", new Long(crc))).list().size() > 0)
				return true;
			else if ((tipoProceso == 'G' || tipoProceso == 'g') && this.session.createCriteria(NominaGRVO.class).add(Restrictions.eq("crc", new Long(crc))).list().size() > 0)
				return true;
			else if ((tipoProceso == 'A' || tipoProceso == 'a')&& this.session.createCriteria(NominaRAVO.class).add(Restrictions.eq("crc", new Long(crc))).list().size() > 0)
				return true;
			else if ((tipoProceso == 'D' || tipoProceso == 'd') && this.session.createCriteria(NominaDCVO.class).add(Restrictions.eq("crc", new Long(crc))).list().size() > 0)
				return true;
			return false;
		} catch (Exception ex)
		{
			log.info("\n\nERROR buscaCRC:" + ex);
			throw new DaoException("Problemas buscando CRC", ex);
		}
	}

	// BUG_RESEND
	public boolean haSidoEnviada(int idEmpresa, int idConvenio, char tipoNomina,
			long crc) throws DaoException {

		try {
			Criteria criteria;
			
			if (tipoNomina == 'R') {
				criteria = session.createCriteria(NominaREVO.class);
			} else if (tipoNomina == 'G') {
				criteria = session.createCriteria(NominaGRVO.class);
			} else if (tipoNomina == 'D') {
				criteria = session.createCriteria(NominaDCVO.class);
			} else {	// tipoNomina == 'A'
				criteria = session.createCriteria(NominaRAVO.class);
			}
			
			criteria
					.add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa)))
					.add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
					.add(Restrictions.eq("crc", new Long(crc)));
			
			return criteria.list().size() > 0;
		} catch (Exception e) {
			log.info("\n\nhaSidoEnviada:" + e);
			
			throw new DaoException("Problemas en haSidoEnviada", e);
		}
	}
	
	/**
	 * guarda la nomina recibida, en la tabla asociada al tipo de proceso indicado
	 * 
	 * @param tipoProceso
	 * @param nomina
	 * @throws DaoException
	 */
	public void guardaNominaProceso(NominaVO nomina) throws DaoException
	{
		try
		{
			log.info("guardando nomina en proceso empresa:" + nomina.getRutEmpresa() + ":idconvenio:" + nomina.getIdConvenio() + ":tipo:" + nomina.getTipoProceso() + "::");
			this.session.flush();
			log.info("comienza guardado nomina");
			NominaVO n = (NominaVO) nomina.getClass().newInstance();
			n.setRutEmpresa(nomina.getRutEmpresa());
			n.setIdConvenio(nomina.getIdConvenio());
			
			if (nomina instanceof NominaGRVO)
			{
				((NominaGRVO) nomina).setInicio(new Date(1));
				((NominaGRVO) nomina).setTermino(new Date(1));
			} else if (nomina instanceof NominaRAVO)
			{
				((NominaRAVO) nomina).setInicio(new Date(1));
				((NominaRAVO) nomina).setTermino(new Date(1));
			}
			nomina.setAceptada(new Timestamp(1));
			nomina.setRecibida(new Timestamp(System.currentTimeMillis())); // BUG_TIMESTAMP

			nomina.setNumCotizaciones(0);
			nomina.setNumCotizCorregidas(0);
			nomina.setNumCotizOK(0);

			log.debug(ToStringBuilder.reflectionToString(nomina, ToStringStyle.MULTI_LINE_STYLE));
			this.session.flush();
			n = (NominaVO) this.session.get(nomina.getClass(), n);
			if (n == null)
			{
				nomina.addNumReenvios();
				this.session.save(nomina);
			} else
			{
				nomina.setNumReenvios(n.getNumReenvios() + 1);
				nomina.setIdCodigoBarras(n.getIdCodigoBarras());
				this.session.merge(nomina);
			}
			this.session.flush();
		} catch (Exception ex)
		{
			log.error("Error en NominaDAO:setNominaProceso:", ex);
			throw new DaoException("Error en NominaDAO:setNominaProceso", ex);
		}
	}

	/**
	 * cambia el estado de cada nomina de la lista (si existe) a 'no cargada'
	 * 
	 * @param listaNominas
	 */
	public void rechazaNominas(List listaNominas)
	{
		try
		{
			for (Iterator it = listaNominas.iterator(); it.hasNext();)
			{
				try
				{
					NominaVO nomina = (NominaVO) it.next();
					if (nomina != null)
					{
						log.info("rechazando nomina:" + nomina.toString() + "::");
						NominaVO n = (NominaVO) this.session.get(nomina.getClass(), nomina);
						if (n != null && n.getIdEstado() != Constants.EST_NOM_PAGADO && n.getIdEstado() != Constants.EST_NOM_PAGADO_PARCIALMENTE && n.getIdEstado() != Constants.EST_NOM_PRECURSADA && n.getIdEstado() != Constants.EST_NOM_EN_PROCESO)
						{
							n.setIdEstado(Constants.EST_NOM_NO_CARGADA);
							n.setCrc(0);
							this.session.merge(n);
						}
					} else
						log.info("rechazando nomina nula??");
				} catch (Exception e)
				{
					log.error("problemas al rechazar nomina:", e);
				}
			}
			this.session.flush();
		} catch (Exception e)
		{
			log.error("problemas al rechazar nomina:", e);
		}
	}

	public void borraCRC(int rutEmpresa, int idConvenio, char tipoProceso) throws DaoException
	{
		try
		{
			NominaVO nomina = this.getNomina("" + tipoProceso, rutEmpresa, idConvenio);
			if (nomina != null)
			{
				nomina.setCrc(0);
				this.session.merge(nomina);
			}
		} catch (Exception ex)
		{
			log.error("problemas al borraCRC:", ex);
			throw new DaoException("ERROR NominaDAO:borraCRC:", ex);
		}
	}

	public boolean getDummy(NominaVO nomina) throws DaoException 
	{
		try
		{
			Query query = this.session.createSQLQuery("SELECT * FROM DUMMY WHERE EMPRESA = ? AND CONVENIO = ? AND TIPO = ?");
			return query.setInteger(0, nomina.getRutEmpresa()).setInteger(1, nomina.getIdConvenio()).setCharacter(2, nomina.getTipoProceso()).list().size()>0;
		} catch (Throwable e)
		{
			throw new DaoException("Problemas obteniendo registro dummy", e);
		}
	}
	
	/**
	 * crea la cabecera de una nómina de cotizaciones.
	 * 
	 * @param nomina
	 * @throws DaoException
	 */
	public void crearNomina(NominaVO nomina) throws DaoException {	// NOMINA_EN_LINEA
		
		String idNomina = nomina.getID();
		
		try {
			log.info("creando cabecera para la nomina:" + idNomina);
			
			NominaVO nominaAux = (NominaVO) nomina.getClass().newInstance();
			
			nominaAux.setRutEmpresa(nomina.getRutEmpresa());
			nominaAux.setIdConvenio(nomina.getIdConvenio());
			
			session.flush();
			
			nominaAux = (NominaVO) this.session.get(nomina.getClass(), nominaAux);
			
			if (nominaAux != null) {
				throw new DaoException("Nomina " + idNomina + " ya existe");
			}
			
			session.save(nomina);
			session.flush();
			
			log.info("Cabecera para la nomina:" + idNomina + "creada");
		} catch (Exception e) {
			log.error("Error en NominaDAO:crearNomina " + idNomina);
			
			throw new DaoException("Error en NominaDAO:crearNomina " + idNomina, e);
		}
	}
	
	public void guardarCabeceraNomina(NominaVO nomina) throws DaoException {
		
		session.flush();
		session.save(nomina);
		session.flush();		
	}
	
	//jlandero 19/08/2010
	/**
	 * Muestra la lista de errores dado la empresa y el id del convenio
	 * @param idEmpresa
	 * @param nombreEmpresa
	 * @param idGrConvenio
	 * @param nombreGrConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getInforme(String idEmpresa, String idConvenio, String tipoNomina, String aviso) throws DaoException
	{
		try
		{
			List params = new ArrayList();			
			
			String select = "select cot, cav, tca ";
			
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION))){
				select+=" from   CotizanteVO cot, CausaAvisoRAVO cav, TipoCausaVO tca " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION))){
				select+=" from   CotizanteVO cot, CausaAvisoGRVO cav, TipoCausaVO tca " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION))){
				select+=" from   CotizanteVO cot, CausaAvisoREVO cav, TipoCausaVO tca " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO))){
				select+=" from   CotizanteVO cot, CausaAvisoDCVO cav, TipoCausaVO tca " ;
			}				
				
			select +=" where  cot.rutEmpresa    = cav.rutEmpresa "+
			" and    cot.idConvenio    = cav.idConvenio "+
			" and    cot.idCotizante  = cav.idCotizPendiente "+
			" and    cav.idCausa = tca.id "+
			" and    cot.rutEmpresa    = "+idEmpresa+" "+
			" and    cot.tieneAviso    = "+aviso+
			" and    cot.idConvenio   = "+idConvenio;			
			
			StringBuffer hqlQuery = new StringBuffer(select);
			
			Query query = this.session.createQuery(hqlQuery.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				query.setParameter(par++, iter.next());
			return query.list();
		} catch (Exception ex)
		{
			log.error("Ha ocurrido la siguiente excepcion en NominaDAO.getErroresNominaByEmpresa:", ex);
			throw new DaoException("NominaDAO.getErroresNominaByEmpresa: ", ex);
		}
	}
	
	//	jlandero 23/08/2010
	/**
	 * Muestra la lista de empresas que tiene avisos o errores
	 * @param idEmpresa
	 * @param nombreEmpresa
	 * @param idGrConvenio
	 * @param nombreGrConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getEmpresasAvisosErrores(String idEmpresa, String razonSocial, String tipoNomina) throws DaoException
	{
		try
		{
			List params = new ArrayList();			
						
			String select = "select distinct ca.idConvenio, e.idEmpresa, e.razonSocial, e.habilitada ";				
				
				if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION))){
					select+=" from   EmpresaVO e , CotizanteVO c,  CausaAvisoRAVO ca " ;
				}
				if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION))){
					select+=" from   EmpresaVO e , CotizanteVO c, CausaAvisoGRVO ca " ;
				}
				if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION))){
					select+=" from   EmpresaVO e , CotizanteVO c, CausaAvisoREVO ca " ;
				}
				if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO))){
					select+=" from   EmpresaVO e , CotizanteVO c, CausaAvisoDCVO ca " ;
				}
								
				select+=" where e.idEmpresa = c.rutEmpresa "+
				" and   ca.idCotizPendiente = c.idCotizante "+
				" and   ca.idConvenio = c.idConvenio "+
				" and   ca.rutEmpresa  = e.idEmpresa ";
				if (idEmpresa != null && !idEmpresa.trim().equals(""))
					select+=" and   e.idEmpresa = "+idEmpresa;
				if (razonSocial != null && !razonSocial.trim().equals(""))
					select+=" and   e.razonSocial = '"+razonSocial+"'";
			
			StringBuffer hqlQuery = new StringBuffer(select);
			
			Query query = this.session.createQuery(hqlQuery.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				query.setParameter(par++, iter.next());
			return query.list();
		} catch (Exception ex)
		{
			log.error("Ha ocurrido la siguiente excepcion en NominaDAO.getEmpresasAvisosErrores:", ex);
			throw new DaoException("NominaDAO.getEmpresasAvisosErrores: ", ex);
		}
	}
}
