package cl.araucana.cp.presentation.mgr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.hibernate.beans.AvisosVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.dao.ParametrosDAO;

/*
 * @(#) ParametroMgr.java 1.11 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.11
 */
public class ParametroMgr
{
	private ParametrosDAO parametroDao;
	static Logger logger = Logger.getLogger(ParametroMgr.class);
	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy kk:mm");
	private Date fechaHoy = new Date();

	public ParametroMgr(Session session)
	{
		this.parametroDao = new ParametrosDAO(session);
	}

	/**
	 * parametro
	 * 
	 * @param key
	 * @return
	 * @throws DaoException
	 */
	public ParametroVO getParametro(int id) throws DaoException
	{
		return this.parametroDao.getParametro(id);
	}

	/**
	 * parametros spl
	 * 
	 * @param listaParams
	 * @return
	 * @throws DaoException
	 */
	public ParametrosHash getParametrosHash(List listaParams) throws DaoException
	{
		return this.parametroDao.getParametrosHash(listaParams);
	}

	/**
	 * parametros envio mail
	 * 
	 * @return
	 * @throws DaoException
	 */
	public ParametrosHash getParamEnvioMail() throws DaoException
	{
		List listParam = new ArrayList();
		listParam.add(new Integer(Constants.PARAM_MAIL_TO));
		listParam.add(new Integer(Constants.PARAM_MAIL_HOST_LOCAL));
		listParam.add(new Integer(Constants.PARAM_MAIL_FROM));
		listParam.add(new Integer(Constants.PARAM_MAIL_USER));
		listParam.add(new Integer(Constants.PARAM_MAIL_PASS));
		listParam.add(new Integer(Constants.PARAM_MAIL_HOST_TO));
		listParam.add(new Integer(Constants.PARAM_MAIL_PORT));
		return this.parametroDao.getParametrosHash(listParam);
	}

	/**
	 * 1 si fecha/hora actual es > que la registrada en tabla parametros => plazo cerrado
	 * 
	 * @param comparacion
	 * @return
	 */
	public int plazoCerrado(int idParam)
	{
		int estadoProceso = 0;
		int plazoCerrado = 1;

		try
		{
			estadoProceso = Integer.parseInt(getParametro(Constants.PARAM_ESTADO).getValor().trim());
		} catch (Exception e)
		{
			logger.error("problemas en plazo cerrado", e);
		}
		if (estadoProceso == 1)
		{
			try
			{
				Date fechaLimite = new Date(this.formato.parse(getParametro(idParam).getValor().trim()).getTime());
				if (this.fechaHoy.getTime() < fechaLimite.getTime())
					plazoCerrado = 0;
				else
					plazoCerrado = 1;
			} catch (ParseException e)
			{
				logger.error("problemas en plazo cerrado", e);
			} catch (DaoException e)
			{
				logger.error("problemas en plazo cerrado", e);
			}
		}
		return plazoCerrado;
	}

	/**
	 * periodo format
	 * 
	 * @return
	 * @throws DaoException
	 */
	public String getPeriodoFormat() throws DaoException
	{
		String periodo = getParametro(Constants.PARAM_PERIODO_INDEPENDIENTE).getValor().trim();
		return periodo.substring(periodo.length() - 2) + "/" + periodo.substring(0, 4);
	}

	/**
	 * parametros
	 * 
	 * @param nombre
	 * @return
	 * @throws DaoException
	 */
	public String getParam(int idParam) throws DaoException
	{
		logger.debug("ParametroMgr:getParam:id:" + idParam + "::");
		ParametroVO parametro = this.getParametro(idParam);
		if (parametro != null)
		{
			logger.debug("ParametroMgr:getParam:idParam:" + idParam + ":parametro encontrado:");
			if (parametro.getValor() != null)
			{
				logger.debug("ParametroMgr:getParam:idParam:" + idParam + ":valor a retornar:" + parametro.getValor() + "::");
				return parametro.getValor().trim();
			}
		}

		logger.debug("ParametroMgr:getParam:idParam:" + idParam + ":no encontrado o valor nulo, retornando vacio:");
		return "";
	}

	/**
	 * aviso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public AvisosVO getAviso() throws DaoException
	{
		return this.parametroDao.getAviso();
	}

	/**
	 * calendario
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getCalendario() throws DaoException
	{
		return this.parametroDao.getCalendario();
	}

	/**
	 * @return
	 * @throws DaoException
	 */
	public HashMap getTiposCausasWarn() throws DaoException
	{
		return this.parametroDao.getTiposCausasWarn();
	}

	/**
	 * @return
	 */
	public List getRelacionTipoCausa() throws DaoException
	{
		return this.parametroDao.getRelacionTipoCausa();
	}
}
