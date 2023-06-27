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
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LdapVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
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
	 * Retorna la nómina según el filtro indicado
	 * 
	 * @param  nomina
	 * @return lista de tipos de nominas recuperados
	 * @throws DaoException
	 */
	public Collection getTiposNominas(String nomina) throws DaoException
	{
		try
		{
			return this.session.createCriteria(TipoNominaVO.class)
							   .add(Restrictions.eq("idTipoNomina", nomina))
							   .addOrder(Order.asc("orden"))
							   .list();
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
	public void rechazaNominas(List listaNominas, boolean rechazoPorNodo)
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
						if (n != null) {
							if (rechazoPorNodo) {
								n.setIdEstado(Constants.EST_NOM_NO_PROCESADA);
								n.setCrc(0);
								this.session.merge(n);
							} else if (n.getIdEstado() != Constants.EST_NOM_PAGADO &&
									   n.getIdEstado() != Constants.EST_NOM_PAGADO_PARCIALMENTE &&
									   n.getIdEstado() != Constants.EST_NOM_PRECURSADA &&
									   n.getIdEstado() != Constants.EST_NOM_EN_PROCESO) {
								n.setIdEstado(Constants.EST_NOM_NO_CARGADA);
								n.setCrc(0);
								this.session.merge(n);
							}
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
	
	/**
	 * Borra CRC de todas las nóminas asociadas a un grupo de Convenio y a un tipo de nómina específicos.
	 *  
	 * @param tipoNomina
	 * @param idGrupoConvenio
	 * @throws DaoException
	 */
	public void borraCRCMasivo(char tipoNomina, int idGrupoConvenio) throws DaoException
	{
		try
		{
			//StringBuffer sb = new StringBuffer();

			String n, select;
			if (tipoNomina == 'R')
				n = "NominaREVO";
			else if (tipoNomina == 'A')
				n = "NominaRAVO";
			else if (tipoNomina == 'G')
				n = "NominaGRVO";
			else
				n = "NominaDCVO";

			select = "SELECT n" +
					 "  FROM "  + n + " AS n"  +
					 " WHERE EXISTS (SELECT 1" +
					  				"  FROM ConvenioVO       AS c"                 +
					  				"     , GrupoConvenioVO  AS g"                 +
					  				" WHERE n.rutEmpresa      = c.idEmpresa"       +
					  				"   AND n.idConvenio      = c.idConvenio"      +
					  				"   AND c.idGrupoConvenio = g.idGrupoConvenio" +
					  				"   AND g.idGrupoConvenio = " + String.valueOf(idGrupoConvenio) + ")";
			
			/*sb.append("SELECT n FROM " + n + " AS n ");
			sb.append("WHERE EXISTS (SELECT 1 FROM ConvenioVO AS c, GrupoConvenioVO AS g WHERE ");
			sb.append("n.rutEmpresa = c.idEmpresa  AND ");
			sb.append("n.idConvenio = c.idConvenio AND ");
			sb.append("c.idGrupoConvenio = g.idGrupoConvenio AND ");
			sb.append("g.idGrupoConvenio = " + String.valueOf(idGrupoConvenio) + ")");

			
			List result = this.session.createQuery(sb.toString()).list();*/
			List result = this.session.createQuery(select).list();
			
			for (Iterator it = result.iterator(); it.hasNext(); )
			{
				NominaVO nomina = (NominaVO) it.next();
				this.borraCRC(nomina.getRutEmpresa(), nomina.getIdConvenio(), tipoNomina);
			}

		} catch (Exception ex)
		{
			log.error("problemas al borraCRCMasivo:", ex);
			throw new DaoException("ERROR NominaDAO:borraCRCMasivo:", ex);
		}
	}
	
	/**
	 * Borra CRC de todas las nóminas asociadas a un grupo de Convenio específico.
	 *  
	 * @param idGrupoConvenio
	 * @throws DaoException
	 */
	public void borraCRCMasivo(int idGrupoConvenio) throws DaoException
	{
		try
		{
			String select;
			String[] nominas = { "NominaREVO"
							   , "NominaRAVO"
							   , "NominaGRVO"
							   , "NominaDCVO" };
			char[] tipoNomina = { 'R'
								, 'A'
								, 'G'
								, 'D' };

			for (int i = 0; i < nominas.length; i++)
			{
				select = new String();
				select = "SELECT n" 												   +
						 "  FROM " + nominas[i] + " AS n" 							   +
						 " WHERE EXISTS (SELECT 1"									   +
						 				"  FROM ConvenioVO      AS c"				   +
						 				"     , GrupoConvenioVO AS g" 				   +
						 				" WHERE n.rutEmpresa      = c.idEmpresa" 	   +
						 				"   AND n.idConvenio      = c.idConvenio" 	   +
						 				"   AND c.idGrupoConvenio = g.idGrupoConvenio" +
						 				"   AND g.idGrupoConvenio = " + String.valueOf(idGrupoConvenio) + ")";

				List result = this.session.createQuery(select).list();

				if (result.size() > 0)
				{
					for (Iterator it = result.iterator(); it.hasNext(); )
					{
						NominaVO nomina = (NominaVO) it.next();
						this.borraCRC(nomina.getRutEmpresa(), nomina.getIdConvenio(), tipoNomina[i]);
					}
				}
			}
		} catch (Exception ex)
		{
			log.error("problemas al borraCRCMasivo:", ex);
			throw new DaoException("ERROR NominaDAO:borraCRCMasivo:", ex);
		}
	}

	/**
	 * Borra CRC de todas las nóminas asociadas a una empresa.
	 *  
	 * @param idEmpresa
	 * @throws DaoException
	 */
	public void borraCRCporEmpresa(int idEmpresa) throws DaoException
	{
		try
		{
			String select;
			String[] nominas = { "NominaREVO"
							   , "NominaRAVO"
							   , "NominaGRVO"
							   , "NominaDCVO" };
			char[] tipoNomina = { 'R'
								, 'A'
								, 'G'
								, 'D' };

			for (int i = 0; i < nominas.length; i++)
			{
				select = new String();
				select = "SELECT n" 												   +
						 "  FROM " + nominas[i] + " AS n" 							   +
						 " WHERE EXISTS (SELECT 1"									   +
						 				"  FROM ConvenioVO AS c"				       +
						 				" WHERE n.rutEmpresa      = c.idEmpresa" 	   +
						 				"   AND n.idConvenio      = c.idConvenio" 	   +
						 				"   AND c.idEmpresa       = " + String.valueOf(idEmpresa) + ")";
				
				List result = this.session.createQuery(select).list();

				if (result.size() > 0)
				{
					for (Iterator it = result.iterator(); it.hasNext(); )
					{
						NominaVO nomina = (NominaVO) it.next();
						//System.out.println(nomina.getRutEmpresa() + ", " + nomina.getIdConvenio() + ", " + tipoNomina[i]);
						this.borraCRC(nomina.getRutEmpresa(), nomina.getIdConvenio(), tipoNomina[i]);
					}
				}
			}
		} catch (Exception ex)
		{
			log.error("problemas al borraCRCporEmpresa:", ex);
			throw new DaoException("ERROR NominaDAO:borraCRCporEmpresa:", ex);
		}
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
		try
		{
			String select;
			String[] nominas = { "NominaREVO"
							   , "NominaRAVO"
							   , "NominaGRVO"
							   , "NominaDCVO" };
			char[] tipoNomina = { 'R'
								, 'A'
								, 'G'
								, 'D' };

			for (int i = 0; i < nominas.length; i++)
			{
				select = new String();
				select = "SELECT n" 											   +
						 "  FROM " + nominas[i] + " AS n" 						   +
						 " WHERE n.rutEmpresa      = " + String.valueOf(idEmpresa) +
						 "   AND n.idConvenio      = " + String.valueOf(idConvenio);
				
				List result = this.session.createQuery(select).list();

				if (result.size() > 0)
				{
					for (Iterator it = result.iterator(); it.hasNext(); )
					{
						NominaVO nomina = (NominaVO) it.next();
						//System.out.println(nomina.getRutEmpresa() + ", " + nomina.getIdConvenio() + ", " + tipoNomina[i]);
						this.borraCRC(nomina.getRutEmpresa(), nomina.getIdConvenio(), tipoNomina[i]);
					}
				}
			}
		} catch (Exception ex)
		{
			log.error("problemas al borraCRCporEmpresa:", ex);
			throw new DaoException("ERROR NominaDAO:borraCRCporEmpresa:", ex);
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
			
			NominaVO nominaAux = (NominaVO)nomina.getClass().newInstance();
			
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
	 * @param idGrConvenio
	 * @param tipoNomina
	 * @param aviso
	 * @param listaEmpresas
	 * @return
	 * @throws DaoException
	 */
	public List getInformeAvisos(String idEmpresa, String idConvenio, String tipoNomina, String aviso, List listaEmpresas, Integer[] tipoCausas) throws DaoException
	{
		try
		{			
			
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
			" and    cot.idConvenio  = cav.idConvenio "+
			" and    cot.idCotizante = cav.idCotizPendiente "+
			" and    cav.idTipoCausa = tca.id "+
			" and    cot.rutEmpresa  = "+idEmpresa+" "+
			" and    cot.tieneAviso  = "+aviso+
			" and    cot.idConvenio  = "+idConvenio+
			" and    tca.error       = "+Constants.NIVEL_VAL_AVISO;
			
			if (tipoCausas != null) {
				select+=" and tca.id in (";
				for (int i = 0; i < tipoCausas.length; i++ ){
					select += tipoCausas[i].toString() + ",";
				}
				select = select.substring(0, select.length()-1);
				select+=")";
			}
			
			if(listaEmpresas.size()>0){
				select+=" and cot.rutEmpresa in (";
				for (Iterator iter = listaEmpresas.iterator(); iter.hasNext();){
					EmpresaVO empresa = (EmpresaVO)iter.next();
					select+= empresa.getIdEmpresa() + ",";
				}
				select = select.substring(0, select.length()-1); //Le quito la ultima coma
				select+=")";
			}			
			
			StringBuffer hqlQuery = new StringBuffer(select);
			
			Query query = this.session.createQuery(hqlQuery.toString());

			return query.list();
		} catch (Exception ex)
		{
			log.error("Ha ocurrido la siguiente excepcion en NominaDAO.getErroresNominaByEmpresa:", ex);
			throw new DaoException("NominaDAO.getErroresNominaByEmpresa: ", ex);
		}
	}
	
	//	jlandero 23/08/2010
	/**
	 * List listaEmpresas
	 * @param idEmpresa
	 * @param razonSocial
	 * @param tipoNomina
	 * @param listaEmpresas
	 * @return
	 * @throws DaoException
	 */

	public List getEmpresasAvisos(String idEmpresa, String razonSocial, String tipoNomina, List listaEmpresas) throws DaoException
	{
		try
		{
			String select = "select distinct ca.idConvenio, e.idEmpresa, e.razonSocial, e.habilitada, tc.error ";				
				
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION))){
				select+=" from EmpresaVO e, CotizanteVO c, CausaAvisoRAVO ca, TipoCausaVO tc " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION))){
				select+=" from EmpresaVO e, CotizanteVO c, CausaAvisoGRVO ca, TipoCausaVO tc " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION))){
				select+=" from EmpresaVO e, CotizanteVO c, CausaAvisoREVO ca, TipoCausaVO tc " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO))){
				select+=" from EmpresaVO e, CotizanteVO c, CausaAvisoDCVO ca, TipoCausaVO tc " ;
			}

			select+=" where e.idEmpresa    = c.rutEmpresa "+
			" and ca.idCotizPendiente = c.idCotizante "+
			" and ca.idConvenio  = c.idConvenio "+
			" and ca.rutEmpresa  = c.rutEmpresa "+ //" and ca.rutEmpresa  = e.idEmpresa "+
			" and tc.id          = ca.idTipoCausa "+
			" and ca.idTipoCausa <> 3492 AND ca.idTipoCausa <> 338"+ //TODO GMALLEA Se traen todos los registros menos los de tipo SIS Y AFP (id = 3492 , 338)ya que la empresa es Independiente
			" and e.tipo = '"+ Constants.TIPO_EMPRESA_INDEPENDIENTE +"'";
			if (idEmpresa != null && !idEmpresa.trim().equals("") && !idEmpresa.trim().equals("0")){
				select+=" and e.idEmpresa = "+idEmpresa;
			}
			if (razonSocial != null && !razonSocial.trim().equals("")){
				select+=" and UPPER(e.razonSocial) LIKE '%"+razonSocial.toUpperCase()+"%'";
			}

			if(listaEmpresas.size()>0){
				select+=" and e.idEmpresa in (";
				for (Iterator iter = listaEmpresas.iterator(); iter.hasNext();){
					EmpresaVO empresa = (EmpresaVO)iter.next();
					select+= empresa.getIdEmpresa() + ",";
				}
				select = select.substring(0, select.length()-1); //Se remueve la última coma
				select+=")";
			}

			select+= " order by ca.idConvenio, e.idEmpresa, e.razonSocial, e.habilitada, tc.error";

			StringBuffer hqlQuery = new StringBuffer(select);
			
			Query query = this.session.createQuery(hqlQuery.toString());

			return query.list();
		} catch (Exception ex)
		{
			log.error("Ha ocurrido la siguiente excepcion en NominaDAO.getEmpresasAvisosErrores:", ex);
			throw new DaoException("NominaDAO.getEmpresasAvisosErrores: ", ex);
		}
	}
	
	//jlandero 13/09/2010
	/**
	 * Muestra la informacion de envio acerca de la nomina
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoNomina
	 * @return 
	 * @throws DaoException
	 */
	public List getInformacionNomina(long idEmpresa, long idConvenio, String tipoNomina) throws DaoException
	{
		try
		{
			List params = new ArrayList();
						
			String select = " select n.rutEmpresa, "+ 
			" n.idConvenio,  "+
			" n.recibida  as fechaEnvio, "+
			" n.aceptada  as fechaAceptada, "+
			" n.numCotizaciones as totalTrabajadores, "+
			" n.numCotizOK as totalOk, "+
			" (n.numCotizaciones - n.numCotizOK) as totalErroneos, "+
			" e.idEncargado as rutEncargado, "+
			" d.normalSize, "+
			" d.comprimidoSize, "+
			" e.recibido as fechaRecibido ";
									
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION))){
				select += " from NominaRAVO n, EnvioVO e, DescriptorNominaVO d ";
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION))){
				select += " from NominaGRVO n, EnvioVO e, DescriptorNominaVO d ";
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION))){
				select += " from NominaREVO n, EnvioVO e, DescriptorNominaVO d ";
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO))){
				select += " from NominaDCVO n, EnvioVO e, DescriptorNominaVO d ";
			}
			
			select += " where n.rutEmpresa = "+idEmpresa+
				" and   n.idConvenio = "+idConvenio+
				" and   d.idEnvio = e.id "+
				" and   n.idEnvio = e.id "+
				" and   n.idEnvio = d.idEnvio "+
				" and   n.idConvenio = d.idConvenio "+
				" and   d.rutEmpresa = n.rutEmpresa";
						
			StringBuffer hqlQuery = new StringBuffer(select);
			
			Query query = this.session.createQuery(hqlQuery.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				query.setParameter(par++, iter.next());
			return query.list();
		} catch (Exception ex)
		{
			log.error("Ha ocurrido la siguiente excepcion en NominaDAO.getInformacionNomina:", ex);
			throw new DaoException("NominaDAO.getInformacionNomina: ", ex);
		}
	}
	
	/**
	 * Muestra la lista de errores dado la empresa y el id del convenio
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoNomina
	 * @param aviso
	 * @param listaEmpresas
	 * @return
	 * @throws DaoException
	 */
	public List getInformeErrores(String idEmpresa, String idConvenio, String tipoNomina, String aviso, List listaEmpresas) throws DaoException
	{
		try
		{
			String select = "select cot, cau, tca ";
			
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION))){
				select+=" from   CotizacionPendienteRAVO cot, CausaRAVO cau, TipoCausaVO tca " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION))){
				select+=" from   CotizacionPendienteGRVO cot, CausaGRVO cau, TipoCausaVO tca " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION))){
				select+=" from   CotizacionPendienteREVO cot, CausaREVO cau, TipoCausaVO tca " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO))){
				select+=" from   CotizacionPendienteDCVO cot, CausaDCVO cau, TipoCausaVO tca " ;
			}				
			
			select +=" where cau.rutEmpresa = "+idEmpresa+
			" and cau.idConvenio = "+idConvenio+
			" and cau.idCotizPendiente = cot.idCotizPendiente "+
			//" and tca.id = cau.idCausa "+idTipoCausa
			" and tca.id = cau.idTipoCausa "+
			" and cot.rutEmpresa = "+idEmpresa+
			" and cot.idConvenio = "+idConvenio+
			//TODO. Si solo se buscan errores, se obtienen solo estos.
			" and tca.error = "+String.valueOf(Constants.NIVEL_VAL_ERROR);
					
			if(listaEmpresas.size()>0){
				select+=" and cot.rutEmpresa in (";
				for (Iterator iter = listaEmpresas.iterator(); iter.hasNext();){
					EmpresaVO empresa = (EmpresaVO)iter.next();
					select+= empresa.getIdEmpresa() + ",";
				}
				select = select.substring(0, select.length()-1); //Le quito la ultima coma
				select+=")";
			}			
			
			//StringBuffer hqlQuery = new StringBuffer(select);

			Query query = this.session.createQuery(select).setMaxResults(Constants.CANTIDAD_MAXIMA_REGISTROS_ERROR);

			return query.list();
		} catch (Exception ex)
		{
			log.error("Ha ocurrido la siguiente excepcion en NominaDAO.getInformeErrores:", ex);
			throw new DaoException("NominaDAO.getInformeErrores: ", ex);
		}
	}
	
	/**
	 * 
	 * @param idEmpresa
	 * @param razonSocial
	 * @param tipoNomina
	 * @param listaEmpresas
	 * @return
	 * @throws DaoException
	 */
	public List getEmpresasAvisosErrores(String idEmpresa, String razonSocial, String tipoNomina, List listaEmpresas) throws DaoException {
		try {
			
			String select = "SELECT DISTINCT c.idConvenio, e.idEmpresa, e.razonSocial, e.habilitada, t.error FROM EmpresaVO e, TipoCausaVO t, ";

			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION)))
				select += "CausaRAVO c ";

			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION)))
				select += "CausaGRVO c ";

			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION))){
				select += "CausaREVO c ";
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO))){
				select += "CausaDCVO c ";
			}	
			//TODO GMALLEA Se agrega la rectriccion tipo para que retorne solo los Independientes.
			select += "WHERE c.rutEmpresa = e.idEmpresa" +
					  "  AND t.id = c.idTipoCausa"+
					  "  and e.tipo = '"+Constants.TIPO_EMPRESA_INDEPENDIENTE +"'";
			

			if (idEmpresa != null && !idEmpresa.trim().equals("") && !idEmpresa.trim().equals("0")){
				select+=" AND e.idEmpresa = "+idEmpresa;
			}
			if (razonSocial != null && !razonSocial.trim().equals("")){
				select+=" AND UPPER(e.razonSocial) LIKE '%"+razonSocial.toUpperCase()+"%'";
			}
			
			if(listaEmpresas.size()>0){
				select+=" AND e.idEmpresa IN (";
				for (Iterator iter = listaEmpresas.iterator(); iter.hasNext();){
					EmpresaVO empresa = (EmpresaVO)iter.next();
					select+= empresa.getIdEmpresa() + ",";
				}
				select = select.substring(0, select.length()-1); //Se remueve la última coma
				select+=")";
			}
			
			select += " ORDER BY c.idConvenio, e.idEmpresa, e.razonSocial, e.habilitada, t.error";

			StringBuffer hqlQuery = new StringBuffer(select);

			Query query = this.session.createQuery(hqlQuery.toString());

			return query.list();

		} catch (Exception ex) {
			log.error("Ha ocurrido la siguiente excepcion en NominaDAO.getInformeAvisosErrores:", ex);
			throw new DaoException("NominaDAO.getInformeAvisosErrores: ", ex);
		}
	}
	

	public List getInformeAvisosPendientes(String idEmpresa, String idConvenio, String tipoNomina, String aviso, List listaEmpresas) throws DaoException {
		try {

			String select = "select cot, cau, tca ";
			
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION))){
				select+=" from   CotizacionPendienteRAVO cot, CausaRAVO cau, TipoCausaVO tca " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION))){
				select+=" from   CotizacionPendienteGRVO cot, CausaGRVO cau, TipoCausaVO tca " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION))){
				select+=" from   CotizacionPendienteREVO cot, CausaREVO cau, TipoCausaVO tca " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO))){
				select+=" from   CotizacionPendienteDCVO cot, CausaDCVO cau, TipoCausaVO tca " ;
			}			
				
			select +=" where  cot.rutEmpresa    = cau.rutEmpresa "+
			" and    cot.idConvenio    = cau.idConvenio "+
			" and    cot.idCotizPendiente  = cau.idCotizPendiente "+
			//" and    cau.idCausa = tca.id "+
			" and    cau.idTipoCausa = tca.id "+
			" and    cot.rutEmpresa   = "+idEmpresa+
			" and    cot.idConvenio   = "+idConvenio+
			" and    tca.error        = "+String.valueOf(Constants.NIVEL_VAL_AVISO);
			
			if(listaEmpresas.size()>0){
				select+=" and cot.rutEmpresa in (";
				for (Iterator iter = listaEmpresas.iterator(); iter.hasNext();){
					EmpresaVO empresa = (EmpresaVO)iter.next();
					select+= empresa.getIdEmpresa() + ",";
				}
				select = select.substring(0, select.length()-1);
				select+=")";
			}			

			Query query = this.session.createQuery(select);

			return query.list();
		} catch (Exception ex) {
			log.error("Ha ocurrido la siguiente excepcion en NominaDAO.getInformeCausa:", ex);
			throw new DaoException("NominaDAO.getInformeCausa: ", ex);
		}
	}
	public void crearIndependiente(PersonaVO personaVO , int genero, int codigoActividadEconomica,String tipoDireccion) throws DaoException{
		
			try{
							
				Query query = this.session.getNamedQuery("procCreaInd");  
				query.setLong("rutPer",new Long( personaVO.getRut()).longValue() );  
				query.setInteger("idComuna",personaVO.getIdComuna().intValue());
				query.setLong("celular",new Long( personaVO.getCelular()).longValue() );  
				query.setString("telefono", personaVO.getTelefono());
				query.setString("fax", personaVO.getFax());
				query.setString("direccion", personaVO.getDireccion());
				query.setString("numDireccion", personaVO.getNumero());
				query.setString("numDepartamento", personaVO.getDpto());
				query.setString("nombres", personaVO.getNombres());
				query.setString("apellidoPat", personaVO.getApellidoPaterno());
				query.setString("apellidoMat", personaVO.getApellidoMaterno());
				query.setString("email", personaVO.getEmail());
				//query.setLong("codBarra",codBarra);
				query.setInteger("idGenero", genero);
				query.setLong("idActividad", codigoActividadEconomica);
				query.setString("tipoDireccion", tipoDireccion);
				query.setString("tipoEmpresa", Constants.TIPO_EMPRESA_INDEPENDIENTE);
				query.setString("tipoPago", Constants.TIPO_PAGO_OBLIGATORIO);
				query.setLong("idParamGrupoConvenio", Constants.GRUPO_CONV_TRAB_INDEPENDIENTE_VALOR);
				
				query.uniqueResult();
				
			}catch(Exception e){
				log.error("Ha ocurrido la siguiente excepcion en NominaDAO.crearIndependiente:", e);
				throw new DaoException(""+ e.getCause() , e);
			}
		}
	
	public void registrarLDAP(LdapVO ldapVO) throws DaoException{
		
		try{
			this.session.save(ldapVO);
		
		} catch (Exception e) {
			log.error("Error en NominaDAO:registrarLDAP " + ldapVO.getUserName());
			
			throw new DaoException("Error registrarLDAP " + ldapVO.getUserName() + e.getCause(), e);
		}
		
	}
	}
