package cl.araucana.cp.hibernate.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImpl;
import cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImplServiceLocator;
import cl.araucana.aporte.orqOutput.cliente.service.vo.ErrorResultVO;
import cl.araucana.aporte.orqOutput.cliente.service.vo.OrqOutputResultVO;
import cl.araucana.core.integration.DAO.DAOException;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConfigPDFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.beans.TESTe07f1VO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.mgr.ComprobanteMgr;

/*
 * @(#) ComprobantePagoDao.java 1.7 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cmeli
 * @author cchamblas
 * 
 * @version 1.7
 */
public class ComprobantePagoDAO
{
	private static Logger logger = Logger.getLogger(ComprobantePagoDAO.class);
	private static String JNDI_TES = "jdbc/cp_tes";
	private static String ID_ESTADO_CURSADO = "C";
	private static String ID_ESTADO_ANULADO = "A";
	private static String ESTADO_CAJA_CERRADA = "C";
	private static String ESTADO_CAJA_ABIERTA = "A";
	private static String ID_ESTADO_IMPRESO = "I";
	private ParametrosDAO parametrosDao;

	/**
	 * cambia estados
	 * 
	 * @throws DaoException
	 * @throws DAOException
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ServiceException 
	 */
	public void cambiaEstados() throws DaoException, SQLException, NamingException, ServiceException {
		logger.info("***************** EJECUCION JCRONTAB Cambia Estados Empresa ******************");
		List codBarra = new ArrayList();
		Transaction tx = null;
		java.sql.Connection conn = null;
		try 
		{
			Session session = HibernateUtil.getSession("CPCRONTAB");
			tx = session.beginTransaction();
			//clillo 13-10-2017 AFP
			this.parametrosDao = new ParametrosDAO(session);
			//List listaEstados = session.createCriteria(ComprobanteVO.class).add(Restrictions.eq("idEstado", "" + Constants.EST_CMP_PRECURSADO)).list();
			
			List listaEstados = session.createQuery("select c from " + ComprobanteVO.class.getName() +" c where c.idEstado like '"+ Constants.EST_CMP_PRECURSADO+
			"' and c.folioTesoreria > 0").list();

			
			logger.info("se obtuvieron:" + listaEstados.size() + ":comprobantes precursados:");
			if (listaEstados.size() > 0)
			{
				try
				{
					logger.info("conectando a TES directamente");
					javax.naming.InitialContext initialContext = new javax.naming.InitialContext();
					javax.sql.DataSource dataSource = (javax.sql.DataSource) initialContext.lookup(JNDI_TES);
					conn = dataSource.getConnection();
					logger.info("TES conectado");

					Session sessionTES = HibernateUtil.getSession("TESCRONTAB");
					for (Iterator it = listaEstados.iterator(); it.hasNext();)
					{
						ComprobanteVO c = (ComprobanteVO) it.next();
						logger.info("codBarra:" + c.getIdCodigoBarra() + ":Folio:" + c.getFolioTesoreria() + "::");

						TESTe07f1VO tes1 = (TESTe07f1VO) sessionTES.get(TESTe07f1VO.class, new Long(c.getFolioTesoreria()));
						if (tes1 != null)
						{
							if( ID_ESTADO_CURSADO.equals( ("" + tes1.getTe3ya()) ) ){
								logger.info("\ttes1 encontrado:ba:" + tes1.getTe1ba() + ":ca:" + tes1.getTe1ca() + ":0a:" + tes1.getTe10a() + "::");
								PreparedStatement ps = null;
								ResultSet rs = null;
								String sql = " select TE17A from TEDTA.TE03F1 where TE1BA = ? and  TE1CA  = ? and TE10A = ? ";
								try
								{
									ps = conn.prepareStatement(sql);
									ps.setFloat(1, tes1.getTe1ba());
									ps.setDate(2, tes1.getTe1ca());
									ps.setFloat(3, tes1.getTe10a());
									rs = ps.executeQuery();

									while (rs.next())
									{
										logger.info("\t\testado encontrado:" + rs.getString("TE17A") + "::");

										boolean pagoTotal = true;

										if (ESTADO_CAJA_CERRADA.equals(rs.getString("TE17A")))
										{
											NominaVO nomina = null;
											NominaREVO nre = (NominaREVO) session.createCriteria(NominaREVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra())))
													.uniqueResult();
											if (nre != null)
											{
												nomina = nre;
												logger.debug(" -> comprobante de remuneracion con estado cursado -> revisar si todas las secciones estan declaradas pagas");

												List secciones = c.getSecciones();
												for (Iterator iter = secciones.iterator(); iter.hasNext();)
												{
													SeccionVO seccionVO = (SeccionVO) iter.next();

													if (seccionVO.getTipoPago() != 1)
													{
														pagoTotal = false;
													}
												}

											} else
											{
												NominaGRVO ngr = (NominaGRVO) session.createCriteria(NominaGRVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra())))
														.uniqueResult();
												if (ngr != null)
													nomina = ngr;
												else
												{
													NominaRAVO nra = (NominaRAVO) session.createCriteria(NominaRAVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra())))
															.uniqueResult();
													if (nra != null)
														nomina = nra;
													else
													{
														NominaDCVO ndc = (NominaDCVO) session.createCriteria(NominaDCVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra())))
																.uniqueResult();
														if (ndc != null)
															nomina = ndc;
													}
												}
											}
											
											logger.info("------ Tipo de Nomina : " + nomina.getEmpresa().getTipo() + " Rut Empresa: "+nomina.getEmpresa().getIdEmpresa() +"-----");
											if(nomina.getEmpresa().getTipo().equals(Constants.TIPO_EMPRESA)){
												if (nomina != null)
												{
													if (pagoTotal)
													{
														nomina.setIdEstado(Constants.EST_NOM_PAGADO);
														c.setIdEstado(("" + Constants.EST_CMP_PAGADO).charAt(0));// cambio de estado a pagado
														logger.info("Se paga el total del comprobante idEstado Nomina " + Constants.EST_NOM_PAGADO +" idEstado Comprobante " + "" + Constants.EST_CMP_PAGADO);
													} else
													{
														nomina.setIdEstado(Constants.EST_NOM_PAGADO_PARCIALMENTE);
														c.setIdEstado(("" + Constants.EST_CMP_PAGO_PARCIAL).charAt(0));// cambio de estado a pagado
														logger.info("Se paga parcialmente del comprobante idEstado Nomina " + Constants.EST_NOM_PAGADO_PARCIALMENTE +" idEstado Comprobante " + "" + Constants.EST_CMP_PAGO_PARCIAL);
													}
													
														c.setFormaPago((byte) Constants.FORMA_PAGO_CAJA);
														c.setPagado(new Timestamp(new Date().getTime()));
														
														session.merge(nomina);
														session.merge(c);
														
														codBarra.add(new Long(c.getIdCodigoBarra()));
														
														logger.info("Se a actualizado correctamente el comprobante \n" +
																" Folio Tesoreria :"+ c.getFolioTesoreria()+"\n" +
																" Codigo de Barra :"+ c.getIdCodigoBarra()+"\n" +
																" Forma de Pago :"+ c.getFormaPago()+"\n" +
																" Pagado :"+ c.getPagado());
														
														logger.info("Se a actualizado correctamente la nomina \n" +
																" ID Estado :"+ nomina.getIdEstado());		
														
														//GMALLEA ENVIA MAIL 
														this.enviarMailPagoBySpl(session,c,nomina);
														
													}else{
														logger.info("No se pudo actualizar el registro ya que no existe nomina para este comprobante");
													}
											}else{
												//Si es Independiente..
												this.cambioEstadoIndependiente(nomina, pagoTotal, c, session, codBarra, tes1);
												

											}
										}else{
											logger.info("No se pudo actualizar el registro ya que la caja no esta Cerrada estado actual " + rs.getString("TE17A"));
										}
									}
								} finally
								{
									try
									{
										if (rs != null)
											rs.close();

									} catch (Exception e)
									{
									}
									try
									{
										if (ps != null)
											ps.close();
									} catch (Exception e)
									{
									}
								}								
							} else if( ID_ESTADO_ANULADO.equals( "" + tes1.getTe3ya() ) ) {

								logger.info("\ttes1 encontrado comprobante anulado :ba:" + tes1.getTe1ba() + ":ca:" + tes1.getTe1ca() + ":0a:" + tes1.getTe10a() + "::");
								PreparedStatement ps = null;
								ResultSet rs = null;
								String sql = " select TE17A from TEDTA.TE03F1 where TE1BA = ? and  TE1CA  = ? and TE10A = ? ";
								try {
									ps = conn.prepareStatement(sql);
									ps.setFloat(1, tes1.getTe1ba());
									ps.setDate(2, tes1.getTe1ca());
									ps.setFloat(3, tes1.getTe10a());
									rs = ps.executeQuery();

									while (rs.next()) {
										logger.info("\t\testado encontrado:" + rs.getString("TE17A") + "::");

										if (ESTADO_CAJA_CERRADA.equals(rs.getString("TE17A"))) {
											if (c.getFolioTesoreria() == tes1.getTe3wa()) {
												
												//clillo 13-10-2017 AFP
												List listaParams = new ArrayList();
												listaParams.add(new Integer(Constants.PARAM_FIN_PAGO_CAJA));
												listaParams.add(new Integer(Constants.PARAM_FIN_PAGO_CAJA_IND));
												ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);
													
												String diaFinPagoCaja = paramHash.get("" + Constants.PARAM_FIN_PAGO_CAJA).substring(0, 2);
												c.setDiaFinPagoCaja(Integer.parseInt(diaFinPagoCaja));
												
												ComprobanteVO cmp = new ComprobanteVO(c);
												cmp.setDiaFinPagoCaja(Integer.parseInt(diaFinPagoCaja));
												eliminarComprobante(c.getIdCodigoBarra(), session);
												guardaComprobante(cmp, cmp.getSecciones(), session);
											}
										}
									}
								} finally {
									try {
										if (rs != null)
											rs.close();
									} catch (Exception e) {}
									try {
										if (ps != null)
											ps.close();
									} catch (Exception e) {}
								}
							} else if( ID_ESTADO_IMPRESO.equals( "" + tes1.getTe3ya() ) ) {
								
								logger.info("El comprobante esta con esta Impreso em tesoreria ");
								//CAMBIAR ESTADO COMRPOBANTE A 3
								c.setIdEstado(("" + Constants.EST_CMP_POR_PAGAR).charAt(0));// cambio de estado a pagado
								logger.info("Se cambia el id estado a 3 del comprobante ya en tesoreria esta Impreso ");
								session.merge(c);
								
								//CAMBIAR ESTADO NOMINA A 3
								NominaVO nomina = null;
								NominaREVO nre = (NominaREVO) session.createCriteria(NominaREVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra()))).uniqueResult();
								if (nre != null)
								{
									nomina = nre;
									
								} else{
									NominaGRVO ngr = (NominaGRVO) session.createCriteria(NominaGRVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra()))).uniqueResult();
									if (ngr != null)
										nomina = ngr;
									else
									{
										NominaRAVO nra = (NominaRAVO) session.createCriteria(NominaRAVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra()))).uniqueResult();
										if (nra != null)
											nomina = nra;
										else
										{
											NominaDCVO ndc = (NominaDCVO) session.createCriteria(NominaDCVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra()))).uniqueResult();
											if (ndc != null)
												nomina = ndc;
										}
									}
								}
								
								nomina.setIdEstado(Constants.EST_NOM_POR_PAGAR);
								logger.info("Se cambia el id estado a 3 de la nomina ya en tesoreria esta Impreso ");
								session.merge(nomina);
								
							}
						} else
							logger.info("\tTESTe07f1VO no encontrado");
					}
				} finally
				{
					try
					{
						HibernateUtil.closeSession("TESCRONTAB");
					} catch (Exception e)
					{
					}
					try
					{
						if (conn != null)
							conn.close();
					} catch (Exception e)
					{
					}
				}
				logger.info("largo lista codigos de barras:" + codBarra.size() + "::");
				for (int i = 0; i < codBarra.size(); i++)
					logger.info("\tcodBarra:" + codBarra.get(i) + "::");

				ComprobanteMgr comp = new ComprobanteMgr(session);
				if (codBarra.size() > 0)
				{
					List listaComprobantes = comp.getComprobantes(codBarra);
					comp.cargaOnDemand(listaComprobantes);
				} else
					logger.info("No existen archivos cursados");
			}
			tx.commit();
			logger.info("****************  FIN EJECUCION JCRONTAB Cambia Estados Empresa *****************");
		} catch (HibernateException ex)
		{
			logger.error("error al procesar pre-cursados", ex);
			throw new DaoException("ERROR alcomprobar estado de pago::", ex);
		} finally
		{
			try
			{
				HibernateUtil.closeSession("CPCRONTAB");
			} catch (Exception e)
			{
			}
		}
	}

	/**
	 * Cambia estado "Por Pagar" (3) a "Precursado" (8)
	 *
	 * @throws DaoException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void precursarComprobantes() throws DaoException, SQLException, NamingException {
		logger.info("************** EJECUCION JCRONTAB Precursar Comprobantes Empresa **************");
		List codBarra = new ArrayList();
		Transaction tx = null;
		java.sql.Connection conn = null;
		try {
			Session session = HibernateUtil.getSession("CPCRONTAB");
			tx = session.beginTransaction();
//			clillo 13-10-2017 AFP
			this.parametrosDao = new ParametrosDAO(session);
			/*List listaEstados = session.createCriteria(ComprobanteVO.class)
									   .add(Restrictions.eq("idEstado", "" + Constants.EST_CMP_POR_PAGAR))
								   .list();
			*/
			
			List listaEstados = session.createQuery("select c from " + ComprobanteVO.class.getName() +" c where c.idEstado like '"+ Constants.EST_CMP_POR_PAGAR+
													"' and c.folioTesoreria > 0").list();
			
			logger.info("se obtuvieron:" + listaEstados.size() + ":comprobantes por pagar:");
			if (listaEstados.size() > 0) {
				try {
					logger.info("conectando a TES directamente");
					javax.naming.InitialContext initialContext = new javax.naming.InitialContext();
					javax.sql.DataSource dataSource = (javax.sql.DataSource) initialContext.lookup(JNDI_TES);
					conn = dataSource.getConnection();
					logger.info("TES conectado");

					Session sessionTES = HibernateUtil.getSession("TESCRONTAB");
					for (Iterator it = listaEstados.iterator(); it.hasNext();) {
						ComprobanteVO c = (ComprobanteVO) it.next();
						logger.info("codBarra:" + c.getIdCodigoBarra() + ":Folio:" + c.getFolioTesoreria() + "::");

						TESTe07f1VO tes1 = (TESTe07f1VO) sessionTES.get(TESTe07f1VO.class, new Long(c.getFolioTesoreria()));
						if (tes1 != null) {
							if( ID_ESTADO_CURSADO.equals( "" + tes1.getTe3ya() ) ) {
								logger.info("\ttes1 encontrado:ba:" + tes1.getTe1ba() + ":ca:" + tes1.getTe1ca() + ":0a:" + tes1.getTe10a() + "::");
								/*PreparedStatement ps = null;
								ResultSet rs = null;
								String sql = "SELECT TE17A FROM TEDTA.TE03F1 WHERE TE1BA = ? AND TE1CA  = ? AND TE10A = ?";
								try {
									ps = conn.prepareStatement(sql);
									ps.setFloat(1, tes1.getTe1ba());
									ps.setDate(2,  tes1.getTe1ca());
									ps.setFloat(3, tes1.getTe10a());
									rs = ps.executeQuery();

									while (rs.next()) {
										logger.info("\t\testado encontrado:" + rs.getString("TE17A") + "::");

										if (ESTADO_CAJA_ABIERTA.equals(rs.getString("TE17A"))) {*/
											NominaVO nomina = null;
											NominaREVO nre = (NominaREVO) session.createCriteria(NominaREVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra()))).uniqueResult();
											if (nre != null) {
												nomina = nre;
												//logger.debug(" -> comprobante de remuneracion con estado cursado -> revisar si todas las secciones estan declaradas pagas");
												/*List secciones = c.getSecciones();
												for (Iterator iter = secciones.iterator(); iter.hasNext();) {
													SeccionVO seccionVO = (SeccionVO) iter.next();

													if (seccionVO.getTipoPago() != 1)
														pagoTotal = false;
												}*/

											} else {
												NominaGRVO ngr = (NominaGRVO) session.createCriteria(NominaGRVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra()))).uniqueResult();
												if (ngr != null)
													nomina = ngr;
												else {
													NominaRAVO nra = (NominaRAVO) session.createCriteria(NominaRAVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra()))).uniqueResult();
													if (nra != null)
														nomina = nra;
													else {
														NominaDCVO ndc = (NominaDCVO) session.createCriteria(NominaDCVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra()))).uniqueResult();
														if (ndc != null)
															nomina = ndc;
													}
												}
											}
											if (nomina != null) {
												
												nomina.setIdEstado(Constants.EST_NOM_PRECURSADA);
												c.setIdEstado(("" + Constants.EST_CMP_PRECURSADO).charAt(0));// cambio de estado a precursado

												session.merge(nomina);
												session.merge(c);
												
												codBarra.add(new Long(c.getIdCodigoBarra()));
												
												logger.info("Se a actualizado correctamente el comprobante \n" +
														" Folio Tesoreria :"+ c.getFolioTesoreria()+"\n" +
														" Codigo de Barra :"+ c.getIdCodigoBarra()+"\n" +
														" ID Estado :"+ c.getIdEstado());
												
												logger.info("Se a actualizado correctamente la nomina ID Estado :"+ nomina.getIdEstado());
												
											}else{
												logger.info("No se pudo actualizar el registro ya que no existe nomina para este comprobante");
											}
										/*}else{si
											logger.info("No se pudo actualizar el registro ya que la caja no esta Abierta estado actual " + rs.getString("TE17A"));
										}
									}si
								} finally {si
									try {
										if (rs != null)
											rs.close();

									} catch (Exception e) {}
									try {
										if (ps != null)
											ps.close();
									} catch (Exception e) {}
								}*/
							} else if( ID_ESTADO_ANULADO.equals( "" + tes1.getTe3ya() ) ) {

								logger.info("\ttes1 encontrado:ba:" + tes1.getTe1ba() + ":ca:" + tes1.getTe1ca() + ":0a:" + tes1.getTe10a() + "::");
								PreparedStatement ps = null;
								ResultSet rs = null;
								String sql = "SELECT TE17A FROM TEDTA.TE03F1 WHERE TE1BA = ? AND TE1CA  = ? AND TE10A = ?";
								try {
									ps = conn.prepareStatement(sql);
									ps.setFloat(1, tes1.getTe1ba());
									ps.setDate(2, tes1.getTe1ca());
									ps.setFloat(3, tes1.getTe10a());
									rs = ps.executeQuery();

									while (rs.next()) {
										logger.info("\t\testado encontrado:" + rs.getString("TE17A") + "::");

										if (ESTADO_CAJA_ABIERTA.equals(rs.getString("TE17A"))) {
											if (c.getFolioTesoreria() == tes1.getTe3wa()) {
												
												//clillo 13-10-2017 AFP
												List listaParams = new ArrayList();
												listaParams.add(new Integer(Constants.PARAM_FIN_PAGO_CAJA));
												listaParams.add(new Integer(Constants.PARAM_FIN_PAGO_CAJA_IND));
												ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);
													
												String diaFinPagoCaja = paramHash.get("" + Constants.PARAM_FIN_PAGO_CAJA).substring(0, 2);
												c.setDiaFinPagoCaja(Integer.parseInt(diaFinPagoCaja));
												
												ComprobanteVO cmp = new ComprobanteVO(c);
												cmp.setDiaFinPagoCaja(Integer.parseInt(diaFinPagoCaja));
												
												eliminarComprobante(c.getIdCodigoBarra(), session);
												guardaComprobante(cmp, cmp.getSecciones(), session);
											}
										}
									}
								} finally {
									try {
										if (rs != null)
											rs.close();
									} catch (Exception e) {}
									try {
										if (ps != null)
											ps.close();
									} catch (Exception e) {}
								}
							}
						} else
							logger.info("\tTESTe07f1VO no encontrado");
					}
				} finally {
					try {
						HibernateUtil.closeSession("TESCRONTAB");
					} catch (Exception e) {}
					try {
						if (conn != null)
							conn.close();
					} catch (Exception e) {}
				}
				logger.info("largo lista codigos de barras:" + codBarra.size() + "::");
				for (int i = 0; i < codBarra.size(); i++)
					logger.info("\tcodBarra:" + codBarra.get(i) + "::");

				//ComprobanteMgr comp = new ComprobanteMgr(session);
				//if (codBarra.size() > 0) {
				//	List listaComprobantes = comp.getComprobantes(codBarra);
				//	comp.cargaOnDemand(listaComprobantes);
				//} else
				//	logger.info("No existen archivos cursados");
			}
			tx.commit();
			logger.info("**********  FIN EJECUCION JCRONTAB Precursar Comprobantes Empresa *********");
		} catch (HibernateException ex) {
			logger.error("error al procesar pre-cursados", ex);
			throw new DaoException("ERROR al comprobar estado de pago::", ex);
		} finally {
			try {
				HibernateUtil.closeSession("CPCRONTAB");
			} catch (Exception e) {}
		}
	}
	
	
	private void eliminarComprobante(long idCodBarras, Session session) throws DaoException {
		try	{
			borraSecciones(idCodBarras, session);
			Query query = session.createQuery("DELETE FROM ComprobanteVO WHERE idCodigoBarra = ?");
			query.setLong(0, idCodBarras);
			query.executeUpdate();
			session.flush();
		} catch (HibernateException ex) {
			throw new DaoException("ERROR ComprobantePagoDAO:eliminarComprobante:", ex);
		}
	}
	
	private void borraSecciones(long idCodBarras, Session session) throws DaoException {
		try {
			Query query = session.createQuery("DELETE FROM ConfigPDFVO WHERE idCodigoBarra = ?");
			query.setLong(0, idCodBarras);
			query.executeUpdate();
			session.flush();
			query = session.createQuery("DELETE FROM DetalleSeccionVO WHERE idCodigoBarra = ?");
			query.setLong(0, idCodBarras);
			query.executeUpdate();
			session.flush();
			query = session.createQuery("DELETE FROM SeccionVO WHERE idCodigoBarra = ?");
			query.setLong(0, idCodBarras);
			query.executeUpdate();

			session.flush();
		} catch (Exception e) {
			throw new DaoException("ERROR ComprobantePagoDAO:borraSecciones:idCodBarras:" + idCodBarras + ":", e);
		}
	}
	
	private void guardaComprobante(ComprobanteVO comprobante, List secciones, Session session) throws DaoException {
		try {
			session.flush();
			if (comprobante.getTotal() < 0)
				comprobante.setTotal(0);
			session.save(comprobante);
			session.flush();
			long cb = comprobante.getIdCodigoBarra();
			SeccionVO seccion;
			for (Iterator itSecc = secciones.iterator(); itSecc.hasNext();) {
				seccion = (SeccionVO) itSecc.next();
				seccion.refreshMUnitarios();
				seccion.setIdCodigoBarra(cb);

				if (seccion.getDetallesSeccion() != null) {
					for (Iterator itDet = seccion.getDetallesSeccion().iterator(); itDet.hasNext();) {
						DetalleSeccionVO detalle = (DetalleSeccionVO) itDet.next();
						detalle.setIdCodigoBarra(cb);
					}
				}
				session.save(seccion);
				session.flush();
				ConfigPDFVO config = seccion.getConfigPDF();
				config.setIdCodigoBarra(cb);
				config.setIdTipoSeccion(seccion.getIdTipoSeccion());
				session.save(config);
				session.flush();
			}
		} catch (Exception e) {
			throw new DaoException("ERROR ComprobantePagoDAO:guardaComprobante:", e);
		}
	}
	
	private void cambioEstadoIndependiente( NominaVO nomina , boolean pagoTotal, ComprobanteVO c , Session session, List codBarra, TESTe07f1VO tes1) throws DaoException, ServiceException{
		

		if (nomina != null)
		{
			if (pagoTotal)
			{
				nomina.setIdEstado(Constants.EST_NOM_PAGADO);
				c.setIdEstado(("" + Constants.EST_CMP_PAGADO).charAt(0));// cambio de estado a pagado
				logger.info("Se paga el total del comprobante idEstado Nomina " + Constants.EST_NOM_PAGADO +" idEstado Comprobante " + "" + Constants.EST_CMP_PAGADO);
			} else
			{
				nomina.setIdEstado(Constants.EST_NOM_PAGADO_PARCIALMENTE);
				c.setIdEstado(("" + Constants.EST_CMP_PAGO_PARCIAL).charAt(0));// cambio de estado a pagado
				logger.info("Se paga parcialmente del comprobante idEstado Nomina " + Constants.EST_NOM_PAGADO_PARCIALMENTE +" idEstado Comprobante " + "" + Constants.EST_CMP_PAGO_PARCIAL);
			}
			
				c.setFormaPago((byte) Constants.FORMA_PAGO_CAJA_INDEPENDIENTE);
				
				c.setPagado(new Timestamp(new Date().getTime()));
					
				session.merge(nomina);
				session.merge(c);
				
				codBarra.add(new Long(c.getIdCodigoBarra()));
				
				logger.info("Se a actualizado correctamente el comprobante \n" +
							" Folio Tesoreria :"+ c.getFolioTesoreria()+"\n" +
							" Codigo de Barra :"+ c.getIdCodigoBarra()+"\n" +
							" Forma de Pago :"+ c.getFormaPago()+"\n" +
							" Pagado :"+ c.getPagado());
				
				logger.info("Se a actualizado correctamente la nomina \n" +
						" ID Estado :"+ nomina.getIdEstado());
				
				
				
				//Se llama el WS para informar del pago por linea
				
				CotizacionREVO cotizacionREVO =(CotizacionREVO)session.createCriteria(CotizacionREVO.class)
												.add(Restrictions.eq("rutEmpresa", new Integer(nomina.getRutEmpresa())))
												.add(Restrictions.eq("idCotizante", new Integer(nomina.getRutEmpresa())))
												.uniqueResult();
				
				ComprobanteDAO comprobanteDAO = new ComprobanteDAO(session);
				
				ParametrosDAO parametrosDAO = new ParametrosDAO(session);
				
				ParametroVO parametroVO = parametrosDAO.getParametro(new Integer(Constants.PARAM_PERIODO).intValue());
				
				SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
				SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm");
				
				String fechaRecaudacion = formato.format(tes1.getTe40a());
				String horaRecaudacion = formatoHora.format(tes1.getTe1ta());
				
				//Se parsea a int para ver si es numerico ya que puede venir el nombre del usuario o el rut.
				String rutPagador="";
				try{
					Integer.parseInt(tes1.getSajkm92());
					rutPagador =tes1.getSajkm92()+"-"+ Utils.generaDV(Integer.parseInt(tes1.getSajkm92()));
					
				}catch(Exception e){
					rutPagador =tes1.getSajkm92();	
				}
				
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat sdf  =new java.text.SimpleDateFormat("yyyyMM");
				int periodo = Integer.valueOf(sdf.format(date)+"01").intValue();
				
				//int periodo=Integer.valueOf(parametroVO.getValor().trim()+13).intValue();
				
				logger.info("Objetos enviados a WS recuperacionPago: "+"\n" +
							" RutEmpresa: "+cotizacionREVO.getRutEmpresa()+"\n" +
						  	" CcafCredito: "+cotizacionREVO.getCcafCredito()+ "\n" +
						  	" CcafLeasing: "+cotizacionREVO.getCcafLeasing()+"\n" +
						  	" CcafAporte: "+cotizacionREVO.getCcafAporte()+"\n" +
						  	" Periodo: " +periodo+"\n" +
						  	" Fecha Recaudacion: "+fechaRecaudacion+"\n" +
						  	" hora Recaudacion: "+horaRecaudacion+"\n" +
						  	" Usuario: "+rutPagador+ "\n" +
						  	" Folio: "+(int)(tes1.getTe3wa()));
				
				if(cotizacionREVO.getCcafCredito() > 0 || cotizacionREVO.getCcafLeasing() > 0 || cotizacionREVO.getCcafAporte() > 0 ){
					
				
						cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImplServiceLocator implServiceLocator;
						
						implServiceLocator = new OrqOutputServiceImplServiceLocator();
						
						OrqOutputServiceImpl orqOutputServiceImpl =  implServiceLocator.getOrqOutputServiceImpl();
						
						OrqOutputResultVO orqOutputResultVO = null;
						
					try{
						orqOutputResultVO = orqOutputServiceImpl.recuperacionPago(	cotizacionREVO.getRutEmpresa(),
															  	cotizacionREVO.getCcafCredito(), 
															  	cotizacionREVO.getCcafLeasing(),
															  	cotizacionREVO.getCcafAporte(),
															  	periodo, 
															  	fechaRecaudacion, 
															  	horaRecaudacion, 
															  	""+rutPagador, 
															  	(int)tes1.getTe3wa());
						
							logger.info("Respuesta Web Services OrqOutputServiceImpl Codigo : " + orqOutputResultVO.getErrorVO().getCodError());
							logger.info("Respuesta Web Services OrqOutputServiceImpl Glosa : " +orqOutputResultVO.getErrorVO().getGlsError());
						
					}catch(Exception e){
							
							orqOutputResultVO= new OrqOutputResultVO();
							ErrorResultVO errorResultVO = new ErrorResultVO();
							
							errorResultVO.setCodError(0);
							errorResultVO.setGlsError("No se a podido Conectar al Web Services para Informar el Pago");
							
							orqOutputResultVO.setErrorVO(errorResultVO);
							
							logger.error("No se a podido comunicar con el Web Services OrqOutputServiceImpl" , e);
					}
					
						ParametroVO parametroMail =parametrosDAO.getParametro(new Integer(Constants.PARAM_MAIL_INFORME_PAGO).intValue());
						
						String[] listaMail =parametroMail.getValor().trim().split(";");
						
						List listParam = new ArrayList();
						listParam.add(new Integer(Constants.PARAM_MAIL_HOST_LOCAL));
						listParam.add(new Integer(Constants.PARAM_MAIL_FROM));
						listParam.add(new Integer(Constants.PARAM_MAIL_USER));
						listParam.add(new Integer(Constants.PARAM_MAIL_PASS));
						listParam.add(new Integer(Constants.PARAM_MAIL_HOST_TO));
						listParam.add(new Integer(Constants.PARAM_MAIL_PORT));
						
						ParametrosHash parametros = parametrosDAO.getParametrosHash(listParam);
						
						DecimalFormat formatDinero = new DecimalFormat("$ ###,###.##");
						
						for (int i = 0; i < listaMail.length; i++)
						{
							String mailTo = listaMail[i];
							
							comprobanteDAO.enviarMailInependientePago(	mailTo, 
																		parametros.getValores(),
																		orqOutputResultVO.getErrorVO().getCodError(), 
																		orqOutputResultVO.getErrorVO().getGlsError(),
																		cotizacionREVO.getRutEmpresa()+"-"+Utils.generaDV(cotizacionREVO.getRutEmpresa()),
																		Integer.valueOf(parametroVO.getValor().trim()).intValue(),
																		formatDinero.format(cotizacionREVO.getCcafCredito()), 
																		formatDinero.format(cotizacionREVO.getCcafLeasing()),
																		formatDinero.format(cotizacionREVO.getCcafAporte()),
																		formatDinero.format(c.getTotal()));
						}
				}else{
					logger.info("No se conecto al WS ya que ningun monto a pagar (CcafCredito, CcafLeasing, CcafAporte) es mayor a 0 Cero ");
				}
						
			}else{
				logger.info("No se pudo actualizar el registro ya que no existe nomina para este comprobante");
			}
	
		
	}

	public void enviarMailPagoBySpl(Session session , ComprobanteVO comprobante , NominaVO nomina) throws DaoException{
		//GMALLEA ENVIAR MAIL

		ParametrosDAO parametrosDAO = new ParametrosDAO(session);
		ParametroVO parametroMail =parametrosDAO.getParametro(new Integer(Constants.PARAM_MAIL_PAGO_BY_SPL).intValue());
		
		String[] listaMail =parametroMail.getValor().trim().split(";");
		
		List listParam = new ArrayList();
		listParam.add(new Integer(Constants.PARAM_MAIL_HOST_LOCAL));
		listParam.add(new Integer(Constants.PARAM_MAIL_FROM));
		listParam.add(new Integer(Constants.PARAM_MAIL_USER));
		listParam.add(new Integer(Constants.PARAM_MAIL_PASS));
		listParam.add(new Integer(Constants.PARAM_MAIL_HOST_TO));
		listParam.add(new Integer(Constants.PARAM_MAIL_PORT));
		
		ParametrosHash parametros = parametrosDAO.getParametrosHash(listParam);
		
		ComprobanteDAO comprobanteDAO = new ComprobanteDAO(session);
		
		for (int i = 0; i < listaMail.length; i++)
		{
			String mailTo = listaMail[i];
			
			comprobanteDAO.enviarMailPagoBySPL(mailTo, parametros.getValores(), ""+nomina.getRutEmpresa(), nomina.getEmpresa().getRazonSocial() ,  nomina.getIdConvenio(),
									 comprobante.getTotal(), comprobante.getMedioPago(), comprobante.getFolioTesoreria());
			
		}
		//FIN GMALLEA ENVIAR MAIL
	}
	
}
