package cl.araucana.cp.presentation.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.hibernate.beans.SPLPagoVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.mgr.ComprobanteMgr;
import cl.araucana.cp.presentation.mgr.ParametroMgr;
import cl.araucana.spl.util.crypto.SimpleEncoder;
import cl.araucana.spl.util.crypto.TripleDesEngine;
import cl.araucana.spl.util.crypto.TripleDesEngineException;

/*
* @(#) ServletNotificacionMgr.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.2
 */
public class ServletNotificacionManual extends HttpServlet 
{
	// TODO validar tiempo de ejecucion de este proceso, ya que el que espera la respuesta (SPL) tiene un timeout pequenno
	private static final long serialVersionUID = 872724554592704514L;
	static Logger log = Logger.getLogger(ServletNotificacionManual.class);
	public boolean loggear = true;
	/**
	 * post
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws IOException 
	{
		log.info("ServletNotificacion: pasando por POST, redireccionando a GET");
		doGet(arg0, arg1);
	}
	/**
	 * get
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		log.info("ServletNotificacionManual Empresa: entrando a GET");
		response.setContentType("text/plain");
		PrintWriter writer = response.getWriter();
		Transaction tx = null;
		Transaction txSPL = null;
		//Transaction txTES = null;
		try
		{
			Session sessionSPL = HibernateUtil.getSession("SPL");
			txSPL = sessionSPL.beginTransaction();
			//Session sessionTES = HibernateUtil.getSession("TES");
			//txTES = sessionTES.beginTransaction();
			Session session = HibernateUtil.getSession("");
			tx = session.beginTransaction();

			String data = request.getParameter("trx");
			log.info("\n\ndata recibida:" + data + "::");
			String iv = request.getParameter("vector");
	
			log.info("\n\niv recibida:" + iv + "::");
			
			TripleDesEngine cipher = new TripleDesEngine();
			SimpleEncoder encoder = new SimpleEncoder();
			byte[] bData = encoder.hexToBytes(data);
			byte[] bIv = encoder.hexToBytes(iv);

			String plain;
			try
			{
				ParametroMgr parametroMgr = new ParametroMgr(session);
				plain = cipher.decrypt(parametroMgr.getParam(Constants.PARAM_CLAVE_SPL), bIv, bData, "ISO-8859-1");
			} catch (TripleDesEngineException e)
			{
				throw new ServletException(e);
			}
			String idTrx = String.valueOf(plain);
			//String idTrx = "16165";
			log.info("Transaccion notificada:" + idTrx + "::");
			ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);
/*
 * primero, despues de decodificar el idTrx, va a la DB de SPL a buscar los datos de la transaccion en curso (SPLPagoVO pago)
 * si este pago dice que la transaccion realmente fue pagada:
 * 		- actualiza libro blanco (ejecucion programa TEP990.PGM)
 * 		- si no hay problemas, entonces cursa el comprobante en tesoreria (actualiza tabla TEDTA.TE07F1)
 * 		- despues actualiza estado y fecha de pago en DB de la aplicacion, tabla COMPROBANTE_PAGO
 * 		- genera y publica Comprobantes Ondemand.
 * retorna respuesta: 
 * 		- 1: OK, 
 * 		- otra cosa: ha ocurrido alguna eventualidad, se intenta deshacer lo que se pueda (ejecucion programa TEP990.PGM no se puede deshacer)
 * 
 * si el pago de la transaccion no se pudo realizar (El sistema SPL indica que el pago no fue exitoso), se retorna algún valor de error
 */
			SPLPagoVO pago = (new ComprobanteMgr(sessionSPL)).getSPLPago(new Long(idTrx).longValue());//se pide informacion de trx a SPL
			if (pago != null && pago.getPagado() == 1)
			{
				log.debug(pago.toString());
				//int result = comprobanteMgr.updateLibroBanco(pago);//actualizacion libro blanco (programa TEP990.PGM)
				int result=1;
				if (result == 1)
				{
					//(new ComprobanteMgr(sessionTES)).cursaTesoreria("curse", pago.getDetallePago());//cursa en tesoreria para cada folio (para cada comprobante)
					List listaComprobantes = comprobanteMgr.registraPago(pago);//registra pago para cada comprobante, en DB de la app					
					//comprobanteMgr.cargaOnDemand(listaComprobantes);
					tx.commit();
					txSPL.commit();
					//txTES.commit();
					response.sendRedirect("/AdminCotPrevWEB/notificacionSPL.do?operacion=ok");
				} else
					response.sendRedirect("/AdminCotPrevWEB/notificacionSPL.do?operacion=error");
			} else
				response.sendRedirect("/AdminCotPrevWEB/notificacionSPL.do?operacion=error");
		} catch (Exception e)
		{
			log.error("NotificacionSPLManualAction: ERROR al recibir notificacion:", e);
			if (tx != null)
				tx.rollback();
			if (txSPL != null)
				txSPL.rollback();
			//if (txTES != null)
			//	txTES.rollback();
			response.sendRedirect("/AdminCotPrevWEB/notificacionSPL.do?operacion=error");
		} finally 
		{
			HibernateUtil.closeSession("SPL");
			writer.close();
		}
	}
}
