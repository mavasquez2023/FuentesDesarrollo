package cl.araucana.cp.hibernate.dao;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.DescriptorNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EnvioVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.beans.DocumentoVO;

/*
 * @(#) EnvioDao.java 1.17 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.17
 */
public class EnvioDAO
{
	private static Logger log = Logger.getLogger(EnvioDAO.class);
	Session session;

	public EnvioDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * guarda envio
	 * 
	 * @param envio
	 * @return
	 * @throws DaoException
	 */
	public boolean guardaEnvio(EnvioVO envio) throws DaoException
	{
		try
		{
			log.debug("EnvioDAO:guardaEnvio:idEnvio:" + envio.getId() + "::");
			if (this.session.get(EnvioVO.class, new Integer(envio.getId())) == null)
				this.session.save(envio);
			else
				this.session.merge(envio);

			log.info("EnvioDAO:guardaEnvio: id envio guardado:" + envio.getId() + ":");
			log.debug(ToStringBuilder.reflectionToString(envio, ToStringStyle.MULTI_LINE_STYLE));
			this.session.flush();
			return true;
		} catch (Exception e)
		{
			log.error("\n\nEnvioDAO:guardaEnvio error:", e);
			throw new DaoException("Error en EnvioDAO:guardaEnvio", e);
		}
	}

	/**
	 * envio
	 * 
	 * @param idNodo
	 * @return
	 * @throws DaoException
	 */
	public List getEnvio(int idNodo) throws DaoException
	{
		try
		{
			log.debug("EnvioDAO:getEnvio:idNodo:" + idNodo + "::");
			return this.session.createCriteria(EnvioVO.class).add(Restrictions.eq("idNodo", new Integer(idNodo))).list();
		} catch (Exception e)
		{
			log.error("\n\nEnvioDAO:guardaEnvio error:", e);
			throw new DaoException("Error en EnvioDAO.guardaEnvio", e);
		}
	}

	/**
	 * guarda documento
	 * 
	 * @param doc
	 * @return
	 * @throws DaoException
	 */
	public boolean guardaDocumento(DocumentoVO doc) throws DaoException
	{
		try
		{
			log.info("EnvioDAO:guardaDocumento:" + doc.getId() + "::");
			this.session.flush();
			if (this.session.get(DocumentoVO.class, new Integer(doc.getId())) != null)
				this.session.merge(doc);
			else
				this.session.save(doc);
			this.session.flush();
			return true;
		} catch (Exception e)
		{
			log.error("\n\nEnvioDAO:guardaDocumento error:", e);
			throw new DaoException("Error en EnvioDAO.guardaDocumento", e);
		}
	}

	/**
	 * guarda descriptor
	 * 
	 * @param descriptor
	 * @return
	 * @throws DaoException
	 */
	public boolean guardaDescriptor(DescriptorNominaVO descriptor) throws DaoException
	{
		try
		{
			log.debug("EnvioDAO:guardaDescriptor:antes");
			this.session.flush();
			log.debug("EnvioDAO:guardaDescriptor:idEnvio:" + descriptor.getIdEnvio() + "::");
			if (this.session.get(DescriptorNominaVO.class, descriptor) == null)
				this.session.save(descriptor);
			else
				this.session.merge(descriptor);
			this.session.flush();
			return true;
		} catch (Exception e)
		{
			log.error("EnvioDAO:guardaDescriptorerror:", e);
			throw new DaoException("Error en EnvioDAO.guardaDescriptor", e);
		}
	}

	/**
	 * retorna la lista de descriptores asociados a un envio
	 * 
	 * @param idEnvio
	 * @return
	 * @throws DaoException
	 */
	public List getDescriptores(int idEnvio) throws DaoException
	{
		try
		{
			log.debug("EnvioDAO:getDescriptores:idEnvio:" + idEnvio + "::");
			return this.session.createCriteria(DescriptorNominaVO.class).add(Restrictions.eq("idEnvio", new Integer(idEnvio))).list();
		} catch (Exception e)
		{
			log.error("\n\nEnvioDAO:getDescriptores, idEnvio:" + idEnvio, e);
			throw new DaoException("Error en EnvioDAO:getDescriptores", e);
		}
	}
}
