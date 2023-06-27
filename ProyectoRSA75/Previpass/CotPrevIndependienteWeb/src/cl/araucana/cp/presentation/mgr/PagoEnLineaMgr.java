package cl.araucana.cp.presentation.mgr;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.dao.ParametrosDAO;
import cl.araucana.cp.presentation.struts.javaBeans.PagoEnLinea;

/*
 * @(#) PagoEnLineaMgr.java 1.7 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.7
 */
public class PagoEnLineaMgr
{
	private ParametrosDAO parametrosDao;
	private Logger log = Logger.getLogger(PagoEnLineaMgr.class);

	public PagoEnLineaMgr(Session session)
	{
		this.parametrosDao = new ParametrosDAO(session);
	}

// SECURITY_SUPPORT
	
	/**
	 * prepara pago
	 * 
	 * @param pagador
	 * @param listaCodBarras
	 * @param session
	 * @return
	 */
	public PagoEnLinea preparaPago(String pagador, List listaCodBarras, Session session)
	{
		PagoEnLinea pago = new PagoEnLinea();
		try
		{
			ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);
			List listaParams = new ArrayList();
			
			listaParams.add(new Integer(Constants.PARAM_BANCOS_SPL));
			listaParams.add(new Integer(Constants.PARAM_URL_RETORNO_SPL_INDEPEMDIENTE));
			listaParams.add(new Integer(Constants.PARAM_URL_NOTIFICACION_SPL_INDEPENDIENTE));
			
			ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);
			String urlRetorno = paramHash.get("" + Constants.PARAM_URL_RETORNO_SPL_INDEPEMDIENTE);
			String urlNotificacion = paramHash.get("" + Constants.PARAM_URL_NOTIFICACION_SPL_INDEPENDIENTE);
			
			System.out.println("Pago en Linea: urlRetorno=" + urlRetorno + ", urlNotificacion=" + urlNotificacion);
			
			pago = new PagoEnLinea("Pago de Nominas", pagador, urlRetorno, urlNotificacion);
			pago.setRutCliente(pagador);
			
			List newList = comprobanteMgr.verificaParaPago(listaCodBarras);
			if (newList.size() > 0)
			{
				pago = comprobanteMgr.getFolio(pago, newList);
				this.log.debug("result" + pago.getResult());
				if (!pago.getResult().equals(""))
					throw new Exception("Problemas obteniendo folio:" + pago.getResult());
				pago.setBancos(paramHash.get("" + Constants.PARAM_BANCOS_SPL));
				this.log.info(pago.toXml());
			} else
				pago.setResult("PAGADOS");
			return pago;
		} catch (Exception e)
		{
			this.log.error("ERROR en PagoEnLineaMgr:preparaPago:", e);
		}
		return null;
	}

	public ParametrosHash getParametrosHash(List listaParams) throws DaoException
	{
		return this.parametrosDao.getParametrosHash(listaParams);
	}
}
