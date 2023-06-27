package cl.araucana.cp.hibernate.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConfigPDFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.FoliacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

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
	
	/**
	 * cambia estados
	 * 
	 * @throws DaoException
	 * @throws DAOException
	 * @throws SQLException
	 * @throws NamingException
	 */
	//Se ejecuta solo el CronTasks de empresa que incluye para independiente GMALLEA 31-05-2013
	/*public void cambiaEstados() throws DaoException, SQLException, NamingException {
		logger.info("**************** EJECUCION JCRONTAB Cambia Estados Independiente ************");
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
											logger.info("Tipo de Nomina : " + nomina.getEmpresa().getTipo() );  
											if(nomina.getEmpresa().getTipo().equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
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
														
														ParametroVO parametroVO = parametrosDAO.getParametro(new Integer(Constants.PARAM_PERIODO_INDEPENDIENTE).intValue());
														
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
														
														//int periodo=Integer.valueOf(parametroVO.getValor().trim()+13).intValue();
														java.util.Date date = new java.util.Date();
														java.text.SimpleDateFormat sdf  =new java.text.SimpleDateFormat("yyyyMM");
														int periodo = Integer.valueOf(sdf.format(date)+01).intValue();
														
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
															
														
																cl.araucana.aporte.orqOutput.service.OrqOutputServiceImplServiceLocator implServiceLocator;
																
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
											}else{
												logger.info("No se pudo actualizar el registro ya que la empresa es de tipo Empresa");
											}
										}else{
											logger.info("No se pudo actualizar el registro ya que la caja no esta Cerrada estado actual " + rs.getString("TE17A"));
										}
									}
								} catch (Exception e) {
									
									logger.info("Error en ComprobantePagoDAO : "+ e.getMessage());
									
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

								logger.info("\ttes1 encontrado:ba:" + tes1.getTe1ba() + ":ca:" + tes1.getTe1ca() + ":0a:" + tes1.getTe10a() + "::");
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
												ComprobanteVO cmp = new ComprobanteVO(c);
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
			logger.info(" ***********  FIN EJECUCION JCRONTAB Cambia Estados Independiente ***************");
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
	}*/

	/**
	 * Cambia estado "Por Pagar" (3) a "Precursado" (8)
	 *
	 * @throws DaoException
	 * @throws SQLException
	 * @throws NamingException
	 */
	//Se ejecuta solo el CronTasks de empresa que incluye para independiente GMALLEA 31-05-2013
//	public void precursarComprobantes() throws DaoException, SQLException, NamingException {
//		logger.info("************** EJECUCION JCRONTAB Precursar Comprobantes Independiente ***********");
//		List codBarra = new ArrayList();
//		Transaction tx = null;
//		java.sql.Connection conn = null;
//		try {
//			Session session = HibernateUtil.getSession("CPCRONTAB");
//			tx = session.beginTransaction();
//
//			List listaEstados = session.createCriteria(ComprobanteVO.class)
//									   .add(Restrictions.eq("idEstado", "" + Constants.EST_CMP_POR_PAGAR))
//									   .list();
//			logger.info("se obtuvieron:" + listaEstados.size() + ":comprobantes por pagar:");
//			if (listaEstados.size() > 0) {
//				try {
//					logger.info("conectando a TES directamente");
//					javax.naming.InitialContext initialContext = new javax.naming.InitialContext();
//					javax.sql.DataSource dataSource = (javax.sql.DataSource) initialContext.lookup(JNDI_TES);
//					conn = dataSource.getConnection();
//					logger.info("TES conectado");
//
//					Session sessionTES = HibernateUtil.getSession("TESCRONTAB");
//					for (Iterator it = listaEstados.iterator(); it.hasNext();) {
//						ComprobanteVO c = (ComprobanteVO) it.next();
//						logger.info("codBarra:" + c.getIdCodigoBarra() + ":Folio:" + c.getFolioTesoreria() + "::");
//
//						TESTe07f1VO tes1 = (TESTe07f1VO) sessionTES.get(TESTe07f1VO.class, new Long(c.getFolioTesoreria()));
//						if (tes1 != null) {
//							if( ID_ESTADO_CURSADO.equals( "" + tes1.getTe3ya() ) ) {
//								logger.info("\ttes1 encontrado:ba:" + tes1.getTe1ba() + ":ca:" + tes1.getTe1ca() + ":0a:" + tes1.getTe10a() + "::");
//								PreparedStatement ps = null;
//								ResultSet rs = null;
//								String sql = "SELECT TE17A FROM TEDTA.TE03F1 WHERE TE1BA = ? AND TE1CA  = ? AND TE10A = ?";
//								try {
//									ps = conn.prepareStatement(sql);
//									ps.setFloat(1, tes1.getTe1ba());
//									ps.setDate(2,  tes1.getTe1ca());
//									ps.setFloat(3, tes1.getTe10a());
//									rs = ps.executeQuery();
//
//									while (rs.next()) {
//										logger.info("\t\testado encontrado:" + rs.getString("TE17A") + "::");
//
//										if (ESTADO_CAJA_ABIERTA.equals(rs.getString("TE17A"))) {
//											NominaVO nomina = null;
//											NominaREVO nre = (NominaREVO) session.createCriteria(NominaREVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra()))).uniqueResult();
//											if (nre != null) {
//												logger.info("Se a encontrado la nomina tipo RE con el Codigo de Barra "+c.getIdCodigoBarra());
//												nomina = nre;
//												//logger.debug(" -> comprobante de remuneracion con estado cursado -> revisar si todas las secciones estan declaradas pagas");
//												/*List secciones = c.getSecciones();
//												for (Iterator iter = secciones.iterator(); iter.hasNext();) {
//													SeccionVO seccionVO = (SeccionVO) iter.next();
//
//													if (seccionVO.getTipoPago() != 1)
//														pagoTotal = false;
//												}*/
//
//											} else {
//												NominaGRVO ngr = (NominaGRVO) session.createCriteria(NominaGRVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra()))).uniqueResult();
//												if (ngr != null)
//													nomina = ngr;
//												else {
//													NominaRAVO nra = (NominaRAVO) session.createCriteria(NominaRAVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra()))).uniqueResult();
//													if (nra != null)
//														nomina = nra;
//													else {
//														NominaDCVO ndc = (NominaDCVO) session.createCriteria(NominaDCVO.class).add(Restrictions.eq("idCodigoBarras", new Long(c.getIdCodigoBarra()))).uniqueResult();
//														if (ndc != null)
//															nomina = ndc;
//													}
//												}
//											}
//											if (nomina != null) {
//												nomina.setIdEstado(Constants.EST_NOM_PRECURSADA);
//												c.setIdEstado(("" + Constants.EST_CMP_PRECURSADO).charAt(0));// cambio de estado a precursado
//
//												session.merge(nomina);
//												session.merge(c);
//												
//												codBarra.add(new Long(c.getIdCodigoBarra()));
//												
//												logger.info("Se a actualizado correctamente el comprobante \n" +
//														" Folio Tesoreria :"+ c.getFolioTesoreria()+"\n" +
//														" Codigo de Barra :"+ c.getIdCodigoBarra()+"\n" +
//														" ID Estado :"+ c.getIdEstado());
//																										
//												logger.info("Se a actualizado correctamente la nomina ID Estado :"+ nomina.getIdEstado());
//												
//											}else{
//												logger.info("No se pudo actualizar el registro ya que no existe nomina para este comprobante");
//											}
//										}else{
//											logger.info("No se pudo actualizar el registro ya que la caja no esta Abierta estado actual " + rs.getString("TE17A"));
//										}
//									}
//								} finally {
//									try {
//										if (rs != null)
//											rs.close();
//
//									} catch (Exception e) {}
//									try {
//										if (ps != null)
//											ps.close();
//									} catch (Exception e) {}
//								}
//							} else if( ID_ESTADO_ANULADO.equals( "" + tes1.getTe3ya() ) ) {
//
//								logger.info("\ttes1 encontrado:ba:" + tes1.getTe1ba() + ":ca:" + tes1.getTe1ca() + ":0a:" + tes1.getTe10a() + "::");
//								PreparedStatement ps = null;
//								ResultSet rs = null;
//								String sql = "SELECT TE17A FROM TEDTA.TE03F1 WHERE TE1BA = ? AND TE1CA  = ? AND TE10A = ?";
//								try {
//									ps = conn.prepareStatement(sql);
//									ps.setFloat(1, tes1.getTe1ba());
//									ps.setDate(2, tes1.getTe1ca());
//									ps.setFloat(3, tes1.getTe10a());
//									rs = ps.executeQuery();
//
//									while (rs.next()) {
//										logger.info("\t\testado encontrado:" + rs.getString("TE17A") + "::");
//
//										if (ESTADO_CAJA_ABIERTA.equals(rs.getString("TE17A"))) {
//											if (c.getFolioTesoreria() == tes1.getTe3wa()) {
//												ComprobanteVO cmp = new ComprobanteVO(c);
//												eliminarComprobante(c.getIdCodigoBarra(), session);
//												guardaComprobante(cmp, cmp.getSecciones(), session);
//											}
//										}
//									}
//								} finally {
//									try {
//										if (rs != null)
//											rs.close();
//									} catch (Exception e) {}
//									try {
//										if (ps != null)
//											ps.close();
//									} catch (Exception e) {}
//								}
//							}
//						} else
//							logger.info("\tTESTe07f1VO no encontrado");
//					}
//				} finally {
//					try {
//						HibernateUtil.closeSession("TESCRONTAB");
//					} catch (Exception e) {}
//					try {
//						if (conn != null)
//							conn.close();
//					} catch (Exception e) {}
//				}
//				logger.info("largo lista codigos de barras:" + codBarra.size() + "::");
//				for (int i = 0; i < codBarra.size(); i++)
//					logger.info("\tcodBarra:" + codBarra.get(i) + "::");
//
//				ComprobanteMgr comp = new ComprobanteMgr(session);
//				if (codBarra.size() > 0) {
//					List listaComprobantes = comp.getComprobantes(codBarra);
//					comp.cargaOnDemand(listaComprobantes);
//				} else
//					logger.info("No existen archivos cursados");
//			}
//			tx.commit();
//			logger.info("************** FIN EJECUCION JCRONTAB Precursar Comprobantes Independiente ************");
//		} catch (HibernateException ex) {
//			logger.error("error al procesar pre-cursados", ex);
//			throw new DaoException("ERROR al comprobar estado de pago::", ex);
//		} finally {
//			try {
//				HibernateUtil.closeSession("CPCRONTAB");
//			} catch (Exception e) {}
//		}
//	}
	
	
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
	/**
	 * @author gmallea
	 * 
	 * 24-02-2012  Aumenta y devuelve el siguiente correlativo
	 * @return int
	 * @throws DaoException
	 */
	public int getSecuenciaFolio(int idEntPagadora, Session session) throws DaoException 
	{
		int	folioActualizado =0;
		try 
		{
			Integer folio = (Integer)session.createCriteria(FoliacionVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).setProjection(Projections.max("folioActual")).uniqueResult();
			
				folioActualizado = folio.intValue() + 1;
	    	
	    	
	    	Query query = session.createQuery("UPDATE " + FoliacionVO.class.getName() +
	    					" SET folioActual    = ? " +
							"WHERE idEntPagadora =  ? ");
	    			
	    			query.setInteger(0,folioActualizado);
	    			query.setInteger(1,idEntPagadora);
					query.executeUpdate();
		    	
		} catch(Exception ex) 
		{
			logger.error("ComprobantePagoDAO:getSecuenciaFolio error:", ex);
			throw new DaoException("Problemas en ComprobantePagoDAO.getSecuenciaFolio", ex);
		}
		return folioActualizado;
	}
	/**
	 * @author gmallea
	 * 
	 * 27-02-2012  Actualiza el folio impresion en la tabla detalle seccion
	 * @throws DaoException
	 */
	public void actualizaFolioDetalleSeccion(long idCodigoBarra, int tipo, int newFolio , Session session) throws DaoException
	{
		try
		{
		    	Query query = session.createQuery("UPDATE DetalleSeccionVO "+
							    			    	   " SET folioImpresion  = ? " +
													   " WHERE idCodigoBarra =  ? "+
													   " and idTipoSeccion = ?");
							    			
		    			query.setInteger(0,newFolio);
		    			query.setLong(1,idCodigoBarra);
		    			query.setInteger(2,tipo);
						query.executeUpdate();
			
			
		} catch (Exception ex)
		{
			logger.error("Error en ComprobantePagoDAO.actualizaFolioDetalleSeccion");
			throw new DaoException("ComprobantePagoDAO:actualizaFolioDetalleSeccion:", ex);
		}
	}
}
