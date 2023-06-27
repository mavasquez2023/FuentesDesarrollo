package cl.araucana.cp.distribuidor.business.mgr;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.NodoSiguienteVO;
import cl.araucana.cp.distribuidor.hibernate.dao.ParametrosDAO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

public class CargaConfigMgr
{
	private HashMap parametros = new HashMap();
	private ParametrosDAO parametrosDao;
	private StringBuffer msg = new StringBuffer();
	private static Logger log = Logger.getLogger(CargaConfigMgr.class);
	private static ResourceBundle params = ResourceBundle.getBundle("cl.araucana.cp.distribuidor.business.mgr.parametrosProcesamiento");

	public CargaConfigMgr(Session session)
	{
		super();
		this.parametrosDao = new ParametrosDAO(session);
	}

	public int valida() throws DaoException
	{
		if (!cargaParametros())
		{
			log.info("\n\nreportando problemas al actualizar parametros en EJB");
			return 1;
		}
		if (!validaConceptos())
		{
			log.info("\n\nreportando problemas al validar conceptos en EJB");
			return 2;
		}
		log.info("\n\nactualizacion EJB OK");
		return 0;
	}

	/*
	 * revision de existencia en DB2 de los parametros necesario para validaciones de nominas. diasXMes periodo UFAnterior topeINP UFActual topeAFP aporteCCAF-FON topeAPV apTrabIndSegCes
	 * apTrabPFSegCes apEmpIndSegCes apEmpPFSegCes minTrabPesado maxTrabPesado tasaFija topeGrati mailTo mailHostLocal mailFrom userMail passMail mailHostTo topeMutual loggearVal
	 */
	private boolean cargaParametros() throws DaoException
	{
		this.parametros = this.parametrosDao.getListaHM();
		boolean result = true;

		Enumeration e = params.getKeys();

		log.info("\n\nCargando parametros para procesamiento::");
		while (e.hasMoreElements())
		{
			String key = (String) e.nextElement();
			if (!this.parametros.containsKey(key))
			{
				this.msg.append("no se encontro parametro: '" + key + "':'" + params.getString(key) + "'.\n");
				log.info("no se encontro parametro: '" + key + "':'" + params.getString(key) + "'.\n");
				result = false;
			} else
				log.info("parametro:" + key + "::" + (String) this.parametros.get(key) + "::");
		}
		return result;
	}

	/*
	 * verifica que cada tipo de proceso, cada validacion tenga al menos un concepto asociado para asignar select idvalidacion from CPEMPDTAD.desicionvalidacion where ID_TIPO_NOMINA='D' and
	 * idValidacion not in (select idvalidacion from CPEMPDTAD.conceptovalidacion where ID_TIPO_NOMINA='D')
	 */
	private boolean validaConceptos()
	{
		HashMap result = this.parametrosDao.getValidacionSinConcepto();
		boolean ok = true;
		log.info("\n\nvalidando conceptos::");
		for (Iterator it = result.keySet().iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			log.info("\tse encontro problemas en:" + key + "::");
			this.msg.append("\tse encontro problemas en:" + key + "::\n");
			for (Iterator it2 = ((List) result.get(key)).iterator(); it2.hasNext();)
			{
				NodoSiguienteVO validacion = (NodoSiguienteVO) it2.next();
				this.msg.append("\t\tvalidacion sin concepto asociado:" + validacion.getIdValidacion() + "::\n");
				log.info("\t\tvalidacion sin concepto asociado:" + validacion.getIdValidacion() + "::");
				ok = false;
			}
		}
		if (!ok)
			this.msg.append("\n\nPara solucionar, asociar conceptos con validaciones en tabla 'conceptoValidacion'.\n\n");
		else
			log.info("todos los conceptos validados correctamente::");
		return ok;
	}

	public StringBuffer getMsg()
	{
		return this.msg;
	}

	public void setMsg(StringBuffer msg)
	{
		this.msg = msg;
	}

	public HashMap getParametros()
	{
		return this.parametros;
	}

	public void setParametros(HashMap parametros)
	{
		this.parametros = parametros;
	}
}
