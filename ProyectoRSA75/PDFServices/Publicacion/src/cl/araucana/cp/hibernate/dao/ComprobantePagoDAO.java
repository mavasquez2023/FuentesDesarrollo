package cl.araucana.cp.hibernate.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cl.araucana.core.integration.DAO.DAOException;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.beans.TESTe07f1VO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
// import cl.araucana.cp.presentation.mgr.ComprobanteMgr;

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
	public static String JNDI_TES = "jdbc/cp_tes";
	public static String ID_ESTADO_CURSADO = "C";

	/**
	 * cambia estados
	 * 
	 * @throws DaoException
	 * @throws DAOException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void cambiaEstados() throws DaoException, SQLException, NamingException
	{
		logger.info("\n\n\nEJECUCION JCRONTAB");
		List codBarra = new ArrayList();
		Transaction tx = null;
		java.sql.Connection conn = null;
		try
		{
			Session session = HibernateUtil.getSession("CPCRONTAB");
			tx = session.beginTransaction();

			List listaEstados = session.createCriteria(ComprobanteVO.class).add(Restrictions.eq("idEstado", "" + Constants.EST_CMP_PRECURSADO)).list();
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

									if (ID_ESTADO_CURSADO.equals(rs.getString("TE17A")))
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
										if (nomina != null)
										{
											if (pagoTotal)
											{
												nomina.setIdEstado(Constants.EST_NOM_PAGADO);
												c.setIdEstado(("" + Constants.EST_CMP_PAGADO).charAt(0));// cambio de estado a pagado
											} else
											{
												nomina.setIdEstado(Constants.EST_NOM_PAGADO_PARCIALMENTE);
												c.setIdEstado(("" + Constants.EST_CMP_PAGO_PARCIAL).charAt(0));// cambio de estado a pagado
											}
											c.setFormaPago((byte) Constants.FORMA_PAGO_CAJA);
											c.setPagado(new Timestamp(new Date().getTime()));
											// definir medio de pago!
											// c.setMedioPago((byte) 1);
											session.merge(nomina);
											session.merge(c);

											codBarra.add(new Long(c.getIdCodigoBarra()));
										}
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

				// resolver
				// ComprobanteMgr comp = new ComprobanteMgr(session);
				if (codBarra.size() > 0)
				{
					/*List listaComprobantes = comp.getComprobantes(codBarra);
					comp.cargaOnDemand(listaComprobantes);*/
				} else
					logger.info("No existen archivos cursados");
			}

			tx.commit();
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
}
