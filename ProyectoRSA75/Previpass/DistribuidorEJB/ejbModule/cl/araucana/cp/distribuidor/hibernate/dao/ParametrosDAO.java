package cl.araucana.cp.distribuidor.hibernate.dao;

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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.hibernate.beans.ApellidoCompuestoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PropertiesMapeoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RutEspecialesVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

public class ParametrosDAO
{
	private static Logger logger = Logger.getLogger(ParametrosDAO.class);
	Session session;

	public ParametrosDAO(Session session)
	{
		this.session = session;
	}

	// para validacion nominas
	public HashMap getListaHM() throws DaoException
	{
		try
		{
			logger.info("ParametrosDAO:getLista");
			List result = this.session.createCriteria(ParametroVO.class).list();
			if (result != null && result.size() > 0)
			{
				HashMap resultHM = new HashMap();
				for (Iterator it = result.iterator(); it.hasNext();)
				{
					ParametroVO p = (ParametroVO) it.next();
					resultHM.put("" + p.getId(), p.getValor().trim());
				}
				return resultHM;
			}
			throw new DaoException("ERROR ParametrosDAO:getLista: no se encontraron parametros");
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ParametrosDAO:getLista:", ex);
		}
	}

	public List getCritDistribucion() throws DaoException
	{
		try
		{
			logger.info("ParametrosDAO:getCritDistribucion");
			Criteria crit = this.session.createCriteria(ParametroVO.class);
			ArrayList listaIds = new ArrayList();
			listaIds.add(new Integer(Constants.PARAM_CRIT_DISTRIBUCION_1));
			listaIds.add(new Integer(Constants.PARAM_CRIT_DISTRIBUCION_2));
			List result = crit.add(Restrictions.in("id", listaIds)).addOrder(Order.asc("nombre")).list();
			if (result != null && result.size() > 0)
				return result;
			throw new DaoException("ERROR ParametrosDAO:getCritDistribucion:parametro no encontrado::");
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ParametrosDAO:getCritDistribucion:", ex);
		}
	}

	public List getFactoresCarga() throws DaoException
	{
		try
		{
			logger.info("ParametrosDAO:getFactoresCarga");
			Criteria crit = this.session.createCriteria(ParametroVO.class);
			ArrayList listaIds = new ArrayList();
			listaIds.add(new Integer(Constants.PARAM_FACTOR_REMU));
			listaIds.add(new Integer(Constants.PARAM_FACTOR_GRATI));
			listaIds.add(new Integer(Constants.PARAM_FACTOR_RELI));
			listaIds.add(new Integer(Constants.PARAM_FACTOR_DEPO));
			List result = crit.add(Restrictions.in("id", listaIds)).list();
			if (result != null && result.size() > 0)
				return result;
			throw new DaoException("ERROR ParametrosDAO:getFactoresCarga:parametro no encontrado::");
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ParametrosDAO:getFactoresCarga:", ex);
		}
	}

	public HashMap getPropertiesMapeo(int tipo) throws DaoException
	{
		try
		{
			logger.info("ParametrosDAO:getPropertiesMapeo");
			List result = this.session.createCriteria(PropertiesMapeoVO.class).add(Restrictions.eq("tipo", new Integer(tipo))).list();
			if (result != null && result.size() > 0)
			{
				HashMap resultHM = new HashMap();
				for (Iterator it = result.iterator(); it.hasNext();)
				{
					PropertiesMapeoVO pm = (PropertiesMapeoVO) it.next();
					resultHM.put(pm.getClave().trim(), pm.getValor().trim());
				}
				return resultHM;
			}
			throw new DaoException("ERROR ParametrosDAO:getPropertiesMapeo: no se encontraron parametros para tipo:" + tipo + "::");
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ParametrosDAO:getPropertiesMapeo: para tipo:" + tipo + "::", ex);
		}
	}

	public List getApellCompuestos() throws DaoException
	{
		try
		{
			logger.info("ParametrosDAO:getApellCompuestos");
			List lista = this.session.createCriteria(ApellidoCompuestoVO.class).list();
			if (lista == null)
				return new ArrayList();
			return lista;
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ParametrosDAO:getApellCompuestos::", ex);
		}
	}

	public void guardaApellAprendidos(List apellAprendidos) throws DaoException
	{
		try
		{
			logger.info("ParametrosDAO:guardaApellAprendidos");
			for (Iterator it = apellAprendidos.iterator(); it.hasNext();)
			{
				String apell = (String) it.next();
				ApellidoCompuestoVO apc = (ApellidoCompuestoVO) this.session.get(ApellidoCompuestoVO.class, apell);
				if (apc == null)
					this.session.save(new ApellidoCompuestoVO(apell));
				else
					this.session.merge(new ApellidoCompuestoVO(apell));
			}
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ParametrosDAO:guardaApellAprendidos::", ex);
		}
	}

	public List getRutsEspeciales() throws DaoException
	{
		try
		{
			logger.info("ParametrosDAO:getRutsEspeciales");
			return this.session.createCriteria(RutEspecialesVO.class).list();
		} catch (Exception ex)
		{
			logger.error("ERROR ParametrosDAO:getRutsEspeciales:" + ex);
			throw new DaoException("Problemas obteniendo Lista de ruts Especiales", ex);
		}
	}

	public HashMap getValidacionSinConcepto()
	{
		HashMap result = new HashMap();
		List tiposProcesos = this.session.createCriteria(TipoNominaVO.class).addOrder(Order.asc("orden")).list();
		logger.debug("tipos:" + tiposProcesos.size() + "::");
		for (Iterator it = tiposProcesos.iterator(); it.hasNext();)
		{
			/*
			 * select idvalidacion from CPEMPDTAD.desicionvalidacion where ID_TIPO_NOMINA='D' and idValidacion not in (select idvalidacion from CPEMPDTAD.conceptovalidacion where ID_TIPO_NOMINA='D')
			 */
			TipoNominaVO tn = (TipoNominaVO) it.next();
			Query q = this.session.getNamedQuery("validacionSinConcepto");
			q.setString(0, tn.getIdTipoNomina());
			q.setString(1, tn.getIdTipoNomina());
			List validaciones = q.list();
			if (validaciones.size() > 0)
			{
				logger.debug("agregando::" + tn.getIdTipoNomina() + "::");
				result.put(tn.getIdTipoNomina(), validaciones);
			}
		}
		logger.debug("size:" + result.size() + "::");
		return result;
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
		} catch (Exception e)
		{
			logger.error("problemas unzip:", e);
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
	
	/**
	 * obtiene los parametros semgun id
	 * 
	 * @param listaParams
	 * @return
	 * @throws DaoException
	 */
	public ParametrosHash getParametrosHash(List listaParams) throws DaoException
	{
		try
		{
			logger.debug("ParametrosDAO:getParametrosHash");
			ParametrosHash params = new ParametrosHash();
			List result = this.session.createCriteria(ParametroVO.class).add(Restrictions.in("id", listaParams)).list();
			if (result.size() != listaParams.size())
				throw new DaoException("ERROR ParametrosDAO:getParametrosHash:parametros no encontrados por lista:buscados:" + listaParams.size() + ":encontrados:" + result.size() + "::");

			for (Iterator it = result.iterator(); it.hasNext();)
			{
				ParametroVO param = (ParametroVO)it.next();
				params.add("" + param.getId(), param.getValor().trim());
			}

			return params;
		} catch (Exception ex)
		{
			logger.error("ERROR ParametrosDAO:getParametrosHash");
			throw new DaoException("ERROR ParametrosDAO:getParametrosHash:", ex);
		}
	}
}
