package cl.araucana.adminCpe.hibernate.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RelacionTipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
 * @(#) ParametroDao.java 1.9 10/06/2009
 *
 * Este codigo fuente pertenece a la Caja de Compensacion de Asignacion Familiar
 * La Araucana (C.C.A.F.). Su utilizacion y reproduccion es confidencial y esta
 * restringido a los sistemas de informacion propios de la institucion.
 */

/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.9
 */
public class ParametroDAO
{
	private static Logger log = Logger.getLogger(ParametroDAO.class);
	private Session session;
	private boolean loggear = true;

	public ParametroDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * 
	 * @return lista factores carga
	 * @throws DaoException
	 */
	public List getFactoresCarga() throws DaoException
	{
		try
		{
			log.info("ParametrosDAO:getFactoresCarga");
			ArrayList listaIds = new ArrayList();
			listaIds.add(new Integer(Constants.PARAM_FACTOR_REMU));
			listaIds.add(new Integer(Constants.PARAM_FACTOR_GRATI));
			listaIds.add(new Integer(Constants.PARAM_FACTOR_RELI));
			listaIds.add(new Integer(Constants.PARAM_FACTOR_DEPO));
			Criteria crit = this.session.createCriteria(ParametroVO.class);
			List result = crit.add(Restrictions.in("id", listaIds)).list();
			if (result != null && result.size() > 0)
				return result;
			throw new DaoException("ERROR ParametrosDAO:getFactoresCarga:parametro no encontrado::");
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ParametrosDAO:getFactoresCarga:", ex);
		}
	}

	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getParametrosNegocio() throws DaoException
	{
		try
		{
			Class tipo = ParametroVO.class;
			if (this.loggear)
				log.info("ParametroDAO:getParametros:");
			List lista = this.session.createCriteria(tipo).add(Restrictions.eq("tipoParametro", "NEG")).addOrder(Order.asc("nombre")).list();
			return lista;
		} catch (Exception ex)
		{
			log.error("Error en ParametroDAO.getParametros");
			throw new DaoException("ParametroDAO:getParametros:", ex);
		}
	}

	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getParametrosSistema() throws DaoException
	{
		try
		{
			Class tipo = ParametroVO.class;
			if (this.loggear)
				log.info("ParametroDAO:getParametros:");
			List lista = this.session.createCriteria(tipo).add(Restrictions.eq("tipoParametro", "SIS")).addOrder(Order.asc("nombre")).list();
			return lista;
		} catch (Exception ex)
		{
			log.error("Error en ParametroDAO.getParametros");
			throw new DaoException("ParametroDAO:getParametros:", ex);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public ParametroVO getParametro(int id) throws DaoException
	{
		try
		{
			if (this.loggear)
				log.info("ParametroDAO:getParametro:id:" + id + "::");
			return (ParametroVO)this.session.get(ParametroVO.class, new Integer(id));
		} catch (Exception ex)
		{
			log.error("Error en ParametroDAO:getParametro");
			throw new DaoException("ParametroDAO:getParametro:", ex);
		}
	}

	/**
	 * 
	 * @param ParametroVO
	 * @throws DaoException
	 */
	public void update(ParametroVO ParametroVO) throws DaoException
	{
		try
		{
			log.info("ParametroDAO:update" + ParametroVO.getValor());

			this.session.update(ParametroVO);
		} catch (Exception ex)
		{
			log.error("ParametroDAO:update error: " + ex.getCause().toString());
			throw new DaoException("Problemas en ParametroDAO:update", ex);
		}
	}

	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public ParametrosHash getParametrosHash() throws DaoException
	{
		try
		{
			ParametrosHash param = new ParametrosHash();
			List result = this.session.createCriteria(ParametroVO.class).list();
			if (result != null && result.size() > 0)
			{
				for (Iterator it = result.iterator(); it.hasNext();)
				{
					ParametroVO p = (ParametroVO) it.next();
					param.add("" + p.getId(), p.getValor().trim());
				}
			} else
				throw new DaoException("ERROR ParametrosDAO:getParametrosHash:no se encontro ningun parametro::");

			return param;
		} catch (Exception ex)
		{
			log.error("ERROR ParametrosDAO:getParametrosHash");
			throw new DaoException("ERROR ParametrosDAO:getParametrosHash:", ex);
		}
	}

	/**
	 * 
	 * @param listaParams
	 * @return
	 * @throws DaoException
	 */
	public ParametrosHash getParametrosHash(List listaParams) throws DaoException
	{
		try
		{
			log.debug("ParametrosDAO:getParametrosSPL");
			ParametrosHash param = new ParametrosHash();
			List result = this.session.createCriteria(ParametroVO.class).add(Restrictions.in("id", listaParams)).list();
			if (result.size() == 0)
				throw new DaoException("ERROR ParametrosDAO:getParametrosSPL:parametros no encontrados::");

			for (Iterator it = result.iterator(); it.hasNext();)
			{
				ParametroVO p = (ParametroVO) it.next();
				param.add("" + p.getId(), p.getValor().trim());
			}

			return param;
		} catch (Exception ex)
		{
			log.error("ERROR ParametrosDAO:getParametrosSPL");
			throw new DaoException("ERROR ParametrosDAO:getParametrosSPL:", ex);
		}
	}

	/**
	 * retorna un hashMap con clave el tipo de nomina, y con valor, el factor registrado en DB2
	 * 
	 * @param tiposNomina
	 * @return
	 * @throws DaoException
	 */
	public HashMap getFactores(List tiposNomina) throws DaoException
	{
		try
		{
			HashMap result = new HashMap();
			for (Iterator it = tiposNomina.iterator(); it.hasNext();)
			{
				TipoNominaVO tn = (TipoNominaVO) it.next();
				List valores = this.session.createCriteria(ParametroVO.class).add(Restrictions.eq("nombre", "factor" + tn.getIdTipoNomina())).list();
				if (valores != null && valores.size() > 0)
					result.put(tn.getIdTipoNomina(), ((ParametroVO) valores.get(0)).getValor().trim());
			}
			return result;
		} catch (Exception ex)
		{
			log.error("ERROR ParametrosDAO:getFactor::");
			throw new DaoException("ERROR ParametrosDAO:getParametro:", ex);
		}
	}

	public List getRelacionTipoCausa() throws DaoException
	{
		try
		{
			return this.session.createCriteria(RelacionTipoCausaVO.class).list();
		} catch (Exception ex)
		{
			log.error("ERROR ParametrosDAO:getRelacionTipoCausa::");
			throw new DaoException("ERROR ParametrosDAO:getRelacionTipoCausa:", ex);
		}
	}

	public byte[] unzipData(byte[] zippedData) throws Exception
	{
		ByteArrayInputStream input = null;
		ZipInputStream zipInput = null;
		ZipEntry zipEntry = null;
		byte[] data = null;
		List zipEntries = new ArrayList();

		try
		{
			input = new ByteArrayInputStream(zippedData);
			zipInput = new ZipInputStream(input);

			while ((zipEntry = zipInput.getNextEntry()) != null)
			{
				zipInput.closeEntry();
				zipEntries.add(zipEntry);
			}

			zipInput.close();
			zipInput = null;

			input.close();
			input = null;

			input = new ByteArrayInputStream(zippedData);
			zipInput = new ZipInputStream(input);

			int nEntry = 0;

			while ((zipEntry = zipInput.getNextEntry()) != null)
			{
				zipEntry = (ZipEntry) zipEntries.get(nEntry++);
				try
				{
					int entrySize = (int) zipEntry.getSize();
					data = new byte[entrySize];
					int nTotalBytesReaded = 0;

					while (nTotalBytesReaded < entrySize)
					{
						int nBytesReaded = zipInput.read(data, nTotalBytesReaded, entrySize - nTotalBytesReaded);

						if (nBytesReaded == -1)
							break;
						nTotalBytesReaded += nBytesReaded;
					}
				} catch (Exception e)
				{
				}
				zipInput.closeEntry();
			}
			if (nEntry == 0)
				data = zippedData;
		} catch (Exception e)
		{
			return null;
		} finally
		{
			if (zipEntry != null)
			{
				try
				{
					if (zipInput != null)
						zipInput.closeEntry();
				} catch (IOException e)
				{
				}
			}

			if (zipInput != null)
			{
				try
				{
					zipInput.close();
				} catch (IOException e)
				{
				}
			}

			if (input != null)
			{
				try
				{
					input.close();
				} catch (IOException e)
				{
				}
			}
		}
		return data;
	}
}
