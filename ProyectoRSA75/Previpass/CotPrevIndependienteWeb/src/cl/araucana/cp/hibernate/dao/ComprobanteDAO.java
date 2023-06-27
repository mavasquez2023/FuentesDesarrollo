package cl.araucana.cp.hibernate.dao;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.aporte.orqOutput.service.OrqOutputServiceImpl;
import cl.araucana.aporte.orqOutput.service.OrqOutputServiceImplServiceLocator;
import cl.araucana.aporte.orqOutput.service.vo.ErrorResultVO;
import cl.araucana.aporte.orqOutput.service.vo.OrqOutputResultVO;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConfigPDFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleCreditoCcafVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.FoliacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.beans.SPLConvenioVO;
import cl.araucana.cp.hibernate.beans.SPLDetPagoVO;
import cl.araucana.cp.hibernate.beans.SPLMedioPagoVO;
import cl.araucana.cp.hibernate.beans.SPLPagoVO;
import cl.araucana.cp.hibernate.beans.TESTe07f1SPLVO;
import cl.araucana.cp.independiente.mail.EnviarResultadoPago;
import cl.araucana.cp.independiente.mail.data.MailRespuestaPago;
import cl.recursos.Formato;


/*
 * @(#) ComprobanteDao.java 1.23 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.23
 */
public class ComprobanteDAO
{
	private static Logger log = Logger.getLogger(ComprobanteDAO.class);
	private Session session;

	public ComprobanteDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * lista comprobantes
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getComprobantes() throws DaoException
	{
		try
		{
			log.info("ComprobanteDAO:getComprobantes");
			return this.session.createCriteria(ComprobanteVO.class).list();
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:getComprobantes:" + ex);
			throw new DaoException("Problemas obteniendo Lista de comprobantes", ex);
		}
	}

	/**
	 * load configuracion pdf
	 * 
	 * @param secciones
	 * @throws DaoException
	 */
	public void loadConfigPDF(List secciones) throws DaoException
	{
		try
		{
			log.info("ComprobanteDAO:loadConfigPDF");
			for (Iterator it = secciones.iterator(); it.hasNext();)
			{
				SeccionVO seccion = (SeccionVO) it.next();
				ConfigPDFVO config = (ConfigPDFVO) this.session.get(ConfigPDFVO.class, new ConfigPDFVO(seccion.getIdTipoSeccion(), seccion.getIdCodigoBarra()));
				if (config != null)
				{
					config.parse();
					seccion.setConfigPDF(config);
				}
			}
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:loadConfigPDF:" + ex);
			throw new DaoException("Problemas loadConfigPDF", ex);
		}
	}

	/**
	 * lista comprobantes
	 * 
	 * @param listaCodBarras
	 * @return
	 * @throws DaoException
	 */
	public List getComprobantes(List listaCodBarras) throws DaoException
	{
		try
		{
			log.info("ComprobanteDAO:getComprobantes");
			if (!listaCodBarras.isEmpty())
			{
				Criteria crit = this.session.createCriteria(ComprobanteVO.class);
				return crit.add(Restrictions.in("idCodigoBarra", listaCodBarras)).list();
			}
			return new ArrayList();
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:getComprobantes:" + ex);
			throw new DaoException("Problemas obteniendo Lista de comprobantes por codigos de barras", ex);
		}
	}

	/**
	 * comprobante
	 * 
	 * @param idCodigoBarra
	 * @return
	 * @throws DaoException
	 */
	public ComprobanteVO getComprobante(long idCodigoBarra) throws DaoException
	{
		try
		{
			return (ComprobanteVO) this.session.get(ComprobanteVO.class, new Long(idCodigoBarra));
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO.getComprobante");
			throw new DaoException("Error en ComprobanteDAO.getComprobante", ex);
		}
	}

	public List getSecciones(long idCodigoBarra) throws DaoException
	{
		try
		{
			return this.session.createCriteria(SeccionVO.class).add(Restrictions.eq("idCodigoBarra", new Long(idCodigoBarra))).list();
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO.getComprobante");
			throw new DaoException("Error en ComprobanteDAO.getComprobante", ex);
		}
	}

	/**
	 * comprobante por pagar
	 * 
	 * @param idCodigoBarra
	 * @return
	 * @throws DaoException
	 */
	public ComprobanteVO getCmpPorPagar(long idCodigoBarra) throws DaoException
	{
		try
		{
			Criteria crit = this.session.createCriteria(ComprobanteVO.class).add(Restrictions.eq("idCodigoBarra", new Long(idCodigoBarra)));
			crit = crit.add(Restrictions.eq("idEstado", "" + Constants.EST_CMP_POR_PAGAR));
			List result = crit.list();
			if (result != null && result.size() > 0)
				return (ComprobanteVO) result.get(0);
			return null;
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO.getComprobante");
			throw new DaoException("Error en ComprobanteDAO.getComprobante", ex);
		}
	}

	/**
	 * comprobante
	 * 
	 * @param idConvenio
	 * @param tipoProceso
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public ComprobanteVO getComprobante(int idConvenio, String tipoProceso, String rutEmpresa) throws DaoException
	{
		try
		{
			NominaVO nomina;
			if (tipoProceso.equals("R"))
				nomina = new NominaREVO(idConvenio, rutEmpresa);
			else if (tipoProceso.equals("A"))
				nomina = new NominaRAVO(idConvenio, rutEmpresa);
			else if (tipoProceso.equals("G"))
				nomina = new NominaGRVO(idConvenio, rutEmpresa);
			else
				nomina = new NominaDCVO(idConvenio, rutEmpresa);

			nomina = (NominaVO) this.session.get(nomina.getClass(), nomina);

			if (nomina != null)
				return (ComprobanteVO) this.session.get(ComprobanteVO.class, new Long(nomina.getIdCodigoBarras()));
			return null;
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO.getComprobante");
			throw new DaoException("Error en ComprobanteDAO.getComprobante", ex);
		}
	}

	/**
	 * comprobante por pagar
	 * 
	 * @param idConvenio
	 * @param tipoProceso
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public ComprobanteVO getCmpPorPagar(int idConvenio, String tipoProceso, String rutEmpresa) throws DaoException
	{
		try
		{
			Class classNomina;
			if (tipoProceso.equals("R"))
				classNomina = NominaREVO.class;
			else if (tipoProceso.equals("A"))
				classNomina = NominaRAVO.class;
			else if (tipoProceso.equals("G"))
				classNomina = NominaGRVO.class;
			else
				classNomina = NominaDCVO.class;

			Criteria crit = this.session.createCriteria(classNomina).add(Restrictions.eq("idConvenio", new Integer(idConvenio)));
			crit = crit.add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(Restrictions.eq("idEstado", new Integer(Constants.EST_NOM_POR_PAGAR)));
			List result = crit.list();

			if (result != null && result.size() > 0)
				return (ComprobanteVO) this.session.get(ComprobanteVO.class, new Long(((NominaVO) result.get(0)).getIdCodigoBarras()));
			return null;
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO.getComprobante");
			throw new DaoException("Error en ComprobanteDAO.getComprobante", ex);
		}
	}

	/**
	 * nomina
	 * 
	 * @param codigoBarra
	 * @return
	 * @throws DaoException
	 */
	public NominaVO getNomina(long codigoBarra) throws DaoException
	{
		NominaVO nomina;
		try
		{
			log.info("ComprobanteDAO:getNomina(codigoBarra: " + codigoBarra + ")");

			nomina = (NominaVO) this.session.createCriteria(NominaREVO.class).add(Restrictions.eq("idCodigoBarras", new Long(codigoBarra))).uniqueResult();
			if (nomina != null)
				return nomina;

			nomina = (NominaVO) this.session.createCriteria(NominaGRVO.class).add(Restrictions.eq("idCodigoBarras", new Long(codigoBarra))).uniqueResult();
			if (nomina != null)
				return nomina;

			nomina = (NominaVO) this.session.createCriteria(NominaRAVO.class).add(Restrictions.eq("idCodigoBarras", new Long(codigoBarra))).uniqueResult();
			if (nomina != null)
				return nomina;

			nomina = (NominaVO) this.session.createCriteria(NominaDCVO.class).add(Restrictions.eq("idCodigoBarras", new Long(codigoBarra))).uniqueResult();
			if (nomina != null)
				return nomina;

			return null;
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:getNomina codigoBarra:" + codigoBarra + ": ", ex);
			throw new DaoException("Problemas obteniendo la nómina a partir del comprobante: " + codigoBarra + "::", ex);
		}
	}

	/**
	 * spl pago
	 * 
	 * @param idTrx
	 * @return
	 * @throws DaoException
	 */
	public SPLPagoVO getSPLPago(long idTrx) throws DaoException
	{
		try
		{
			log.info("ComprobanteDAO:getSPLPago:idTrx:" + idTrx + "::");
			Criteria crit = this.session.createCriteria(SPLPagoVO.class);
			List result = crit.add(Restrictions.eq("idTrx", new Long(idTrx))).list();
			if (result != null && result.size() > 0)
			{
				SPLPagoVO pago = (SPLPagoVO) result.get(0);
				crit = this.session.createCriteria(SPLConvenioVO.class);
				crit.add(Restrictions.eq("id", new Long(pago.getIdConvenio())));
				result = crit.list();
				if (result != null && result.size() > 0)
				{
					SPLConvenioVO convenio = (SPLConvenioVO) result.get(0);
					crit = this.session.createCriteria(SPLMedioPagoVO.class);
					result = crit.add(Restrictions.eq("id", new Integer(convenio.getIdMedioPago()))).list();
					if (result != null && result.size() > 0)
					{
						SPLMedioPagoVO medioPago = (SPLMedioPagoVO) result.get(0);
						pago.setCodBanco(medioPago.getCodBanco());
						pago.setCtaCte(convenio.getCtaCte());
						pago.setIdMedioPago(medioPago.getId());
						return pago;
					}
				}
			}
			throw new DaoException("No se pudo encontrar transaccion (SPLDTA.PAGO), o datos corruptos idTrx:" + idTrx + "::");
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:getSPLPago:" + ex);
			throw new DaoException("No se pudo encontrar transaccion (SPLDTA.PAGO), o datos corruptos idTrx:" + idTrx + "::", ex);
		}
	}

	/**
	 * guarda seccion
	 * 
	 * @param seccion
	 * @throws DaoException
	 */
	public void guardaSeccion(SeccionVO seccion) throws DaoException
	{
		try
		{
			log.info("ComprobanteDAO:guardaSeccion");
			this.session.save(seccion);
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:guardaSeccion:" + ex);
			throw new DaoException("Problemas guardaSeccion", ex);
		}
	}

	/**
	 * cursa tesoreria
	 * 
	 * @param user
	 * @param listaFolios
	 * @throws DaoException
	 */
	public void cursaTesoreria(String user, Set listaFolios) throws DaoException
	{
		try
		{
			log.info("ComprobanteDAO:cursaTesoreria");
			Date d = new Date((new java.util.Date()).getTime());
			Locale.setDefault(Locale.US);
			Time t = new Time(d.getTime());

			for (Iterator it = listaFolios.iterator(); it.hasNext();)
			{
				SPLDetPagoVO detalle = (SPLDetPagoVO) it.next();

				TESTe07f1SPLVO tes = new TESTe07f1SPLVO('C', d, t, 'P', d, t, Utils.rellena(10, " ", user));
				tes.setTe3wa(detalle.getFolio());
				log.info("ComprobanteDAO:cursaTesoreria1:folio:" + detalle.getFolio() + "::");
				log.info(ToStringBuilder.reflectionToString(tes, ToStringStyle.MULTI_LINE_STYLE));
				this.session.saveOrUpdate(tes);
				log.info("ComprobanteDAO:cursaTesoreria2");
			}
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:cursaTesoreria:" + ex);
			throw new DaoException("Problemas actualizando estado de folios en tesoreria", ex);
		}
	}

	/**
	 * registra pago
	 * 
	 * @param pago
	 * @return
	 * @throws DaoException
	 */
	public List registraPago(SPLPagoVO pago) throws DaoException
	{
		try
		{
			log.info("ComprobanteDAO:registraPago");
			List listaComprobantes = new ArrayList();
			for (Iterator it = pago.getDetallePago().iterator(); it.hasNext();)// para cada comprobante (folio)
			{
				SPLDetPagoVO detalle = (SPLDetPagoVO) it.next();
				List result = this.session.createCriteria(ComprobanteVO.class).add(Restrictions.eq("folioTesoreria", new Long(detalle.getFolio()))).list();
				if (result != null && result.size() > 0)
				{
					if (result.size() > 1)
						throw new DaoException("registrando pago comprobante, se encontro mas de un comprobante con folio:" + detalle.getFolio());

					ComprobanteVO comprobante = (ComprobanteVO) result.get(0);
					comprobante.setPagado(new Timestamp(pago.getFechaContable().getTime()));
					// TODO verificar valores de medioDePago
					int estadoPago = getEstadoPago(comprobante);
					comprobante.setIdEstado(("" + (estadoPago == 0 ? Constants.EST_CMP_PAGO_PARCIAL : Constants.EST_CMP_PAGADO)).charAt(0));
					
					//INICIO GMALLEA SI ES INDEPENDIENTE SE ACTIALIZA EL COMPROBANTE COMO FORMA DE PAGO 4
					
					//GMALLEA 21-02-2012 Validamos si es empresa sigue su curso normal
							NominaREVO nominaREVO = (NominaREVO) session.createCriteria(NominaREVO.class).add(Restrictions.eq("idCodigoBarras",new Long(comprobante.getIdCodigoBarra())))
												.uniqueResult();
							//clillo 22-06-16
							DetalleCreditoCcafVO detalleCredito = (DetalleCreditoCcafVO) session.createCriteria(DetalleCreditoCcafVO.class).add(Restrictions.eq("idCodigoBarra",new Long(comprobante.getIdCodigoBarra()))).add(Restrictions.eq("idCredito",new Integer(1)))
							.uniqueResult();
							if(detalleCredito==null){
								detalleCredito= new DetalleCreditoCcafVO();
							}
							
							if(nominaREVO.getEmpresa().getTipo().equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
								
										comprobante.setFormaPago((byte) Constants.FORMA_PAGO_SPL_INDEPENDIENTE);
										
										comprobante.setMedioPago((byte) 1);
										comprobante.setPagado(new Timestamp(new java.util.Date().getTime()));
										this.session.update(comprobante);
					
										NominaVO nomina = this.getNomina(comprobante.getIdCodigoBarra());
										nomina.setIdEstado(estadoPago == 0 ? Constants.EST_CMP_PAGO_PARCIAL : Constants.EST_NOM_PAGADO);
										this.session.update(nomina);
										listaComprobantes.add(comprobante);
										
										//Se llama el WS para informar del pago por linea
										
										CotizacionREVO cotizacionREVO =(CotizacionREVO)session.createCriteria(CotizacionREVO.class)
																		.add(Restrictions.eq("rutEmpresa", new Integer(nominaREVO.getRutEmpresa())))
																		.add(Restrictions.eq("idCotizante", new Integer(nominaREVO.getRutEmpresa())))
																		.uniqueResult();
										
										
										ParametrosDAO parametrosDAO = new ParametrosDAO(session);
										
										ParametroVO parametroVO = parametrosDAO.getParametro(new Integer(Constants.PARAM_PERIODO_INDEPENDIENTE).intValue());
										
										
										java.util.Date date = new java.util.Date();
										
										SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
										SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm");
										
										String fechaRecaudacion = formato.format(date);
										String horaRecaudacion = formatoHora.format(date);
										
										String rutPagador =pago.getPagador()+"-"+ Utils.generaDV((int)pago.getPagador());
										 
										java.text.SimpleDateFormat sdf  =new java.text.SimpleDateFormat("yyyyMM");
										int periodo = Integer.valueOf(sdf.format(date)+"01").intValue();
										
										//int periodo=Integer.valueOf(parametroVO.getValor().trim()+13).intValue();
										
										log.info("Objetos enviados a WS recuperacionPago: "+"\n" +
													" RutEmpresa: "+cotizacionREVO.getRutEmpresa()+"\n" +
												  	" CcafCredito: "+cotizacionREVO.getCcafCredito()+ "\n" +
												  	" CcafLeasing: "+cotizacionREVO.getCcafLeasing()+"\n" +
												  	" CcafAporte: "+cotizacionREVO.getCcafAporte()+"\n" +
												  	" Periodo: " +periodo+"\n" +
												  	" Fecha Recaudacion: "+fechaRecaudacion+"\n" +
												  	" hora Recaudacion: "+horaRecaudacion+"\n" +
												  	" Usuario: "+rutPagador+ "\n" +
												  	" Folio: "+(int)comprobante.getFolioTesoreria()+ "\n" +
												  	" Folio Credito: " + detalleCredito.getFolioCredito() + "\n" + 
												  	" Cod. Oficina: " + detalleCredito.getCodigoOficina());
//										clillo 22-06-16 Se agrega FolioCredito
										String folioCred= Formato.paddingLeft(String.valueOf(detalleCredito.getCodigoOficina()), 3, '0') + "" + Formato.paddingLeft(String.valueOf(detalleCredito.getFolioCredito()), 9, '0');
										
												cl.araucana.aporte.orqOutput.service.OrqOutputServiceImplServiceLocator implServiceLocator;
											
												implServiceLocator = new OrqOutputServiceImplServiceLocator();
												
												OrqOutputServiceImpl orqOutputServiceImpl =  implServiceLocator.getOrqOutputServiceImpl();
												
												OrqOutputResultVO orqOutputResultVO = null;
											
											try{
//												clillo 22-06-16 Se agrega FolioCredito
												orqOutputResultVO = orqOutputServiceImpl.recuperacionPago(	cotizacionREVO.getRutEmpresa(),
																					  	cotizacionREVO.getCcafCredito(), 
																					  	cotizacionREVO.getCcafLeasing(),
																					  	cotizacionREVO.getCcafAporte(),
																					  	periodo, 
																					  	fechaRecaudacion, 
																					  	horaRecaudacion, 
																					  	""+rutPagador, 
																					  	(int)comprobante.getFolioTesoreria(),
																					  	folioCred);
												
												log.info("Respuesta Web Services OrqOutputServiceImpl Codigo : " + orqOutputResultVO.getErrorVO().getCodError());
												log.info("Respuesta Web Services OrqOutputServiceImpl Glosa : " +orqOutputResultVO.getErrorVO().getGlsError());
												
											}catch(Exception e){
												
												orqOutputResultVO= new OrqOutputResultVO();
												ErrorResultVO errorResultVO = new ErrorResultVO();
												
												errorResultVO.setCodError(0);
												errorResultVO.setGlsError("No se a podido Conectar al Web Services para Informar el Pago");
												
												orqOutputResultVO.setErrorVO(errorResultVO);
												
												log.error("No se a podido comunicar con el Web Services OrqOutputServiceImpl" , e);
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
													
													this.enviarMailInependientePago(mailTo, 
																					parametros.getValores(),
																					orqOutputResultVO.getErrorVO().getCodError(), 
																					orqOutputResultVO.getErrorVO().getGlsError(),
																					cotizacionREVO.getRutEmpresa()+"-"+Utils.generaDV((int)cotizacionREVO.getRutEmpresa()),
																					Integer.valueOf(parametroVO.getValor().trim()).intValue(),
																					formatDinero.format(cotizacionREVO.getCcafCredito()), 
																					formatDinero.format(cotizacionREVO.getCcafLeasing()),
																					formatDinero.format(cotizacionREVO.getCcafAporte()),
																					formatDinero.format(comprobante.getTotal()));
												}
										}
								//FIN GMALLEA
				} else
					throw new DaoException("registrando pago comprobante, no se encontro folio:" + detalle.getFolio());
			}
			log.info("ComprobanteDAO:registraPago2");
			return listaComprobantes;
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:registraPago:" + ex);
			throw new DaoException("Problemas actualizando pago", ex);
		}
	}

	/**
	 * estado pago
	 * 
	 * @param comprobante
	 * @return
	 */
	public int getEstadoPago(ComprobanteVO comprobante)
	{
		for (Iterator it = comprobante.getSecciones().iterator(); it.hasNext();)
		{

			SeccionVO seccion = (SeccionVO) it.next();
			if (seccion.getTipoPago() != Constants.EST_SECCION_PAGO)
				return 0;
		}
		return 1;
	}

	/**
	 * lista tipos secciones
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getTiposSecciones() throws DaoException
	{
		try
		{
			log.info("ComprobanteDAO:getTiposSecciones");
			return this.session.createCriteria(ComprobanteVO.class).list();
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:getTiposSecciones:" + ex);
			throw new DaoException("Problemas obteniendo Lista de tipos de secciones", ex);
		}
	}

	/**
	 * tipos proceso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getTiposProceso() throws DaoException
	{
		try
		{
			log.info("ComprobanteDAO:getTiposProceso");
			return this.session.createCriteria(TipoNominaVO.class).addOrder(Order.asc("orden")).list();
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:getTiposProceso:" + ex);
			throw new DaoException("Problemas obteniendo Lista de tipos de procesos", ex);
		}
	}

	/**
	 * guarda comprobante
	 * 
	 * @param comprobante
	 * @throws DaoException
	 */
	public void guardaComprobante(ComprobanteVO comprobante) throws DaoException
	{
		try
		{
			log.info("ComprobanteDAO:guardaComprobante");
			this.session.save(comprobante);
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:guardaComprobante:" + ex);
			throw new DaoException("Problemas guardaComprobante", ex);
		}
	}

	/**
	 * guarda comprobante, cambiando el codigo de barras asignado
	 * 
	 * @param comprobante
	 * @throws DaoException
	 */
	public long guardaNuevoComprobante(ComprobanteVO comprobante) throws DaoException
	{
		try
		{
			log.info("ComprobanteDAO:guardaNuevoComprobante:" + comprobante.getIdCodigoBarra() + "::");
//			clillo 13-10-2017
			comprobante.setDiaFinPagoCaja(30);
			ComprobanteVO newCmp = new ComprobanteVO(comprobante);
			newCmp.setDiaFinPagoCaja(30);
			
			// log.info(ToStringBuilder.reflectionToString(newCmp, ToStringStyle.MULTI_LINE_STYLE));

			long cb = comprobante.getIdCodigoBarra();
			List lista = this.session.createCriteria(ConfigPDFVO.class).add(Restrictions.eq("idCodigoBarra", new Long(cb))).list();
			for (Iterator it = lista.iterator(); it.hasNext();)
				this.session.delete(it.next());
			this.session.flush();
			log.info("ComprobanteDAO::guardaNuevoComprobante: codBarras:" + cb + ": configs borrados.");

			NominaVO nomina = this.getNomina(cb);
			nomina.setIdCodigoBarras(0);
			this.session.merge(nomina);
			this.session.flush();
			log.info("ComprobanteDAO::guardaNuevoComprobante: codBarras:" + cb + ": nomina sin codigo de barras.");

			this.session.delete(comprobante);
			log.info("ComprobanteDAO::guardaNuevoComprobante: codBarras:" + cb + ": cmp borrando.");
			this.session.flush();
			log.info("ComprobanteDAO::guardaNuevoComprobante: codBarras:" + cb + ": cmp borrado.");
			List secciones = newCmp.getSecciones();
			newCmp.setSecciones(new ArrayList());
			this.session.save(newCmp);
			log.info("ComprobanteDAO::guardaNuevoComprobante: codBarras:" + cb + ": guardando.");
			this.session.flush();
			log.info("ComprobanteDAO::guardaNuevoComprobante: codBarras:" + cb + ": cmp nuevo guardado.");
			log.info(ToStringBuilder.reflectionToString(newCmp, ToStringStyle.MULTI_LINE_STYLE));

			cb = newCmp.getIdCodigoBarra();

			for (Iterator it = secciones.iterator(); it.hasNext();)
			{
				SeccionVO secc = (SeccionVO) it.next();
				secc.setIdCodigoBarra(cb);
				for (Iterator it2 = secc.getDetallesSeccion().iterator(); it2.hasNext();)
				{
					DetalleSeccionVO det = (DetalleSeccionVO) it2.next();
					det.setIdCodigoBarra(cb);
				}
				secc.getConfigPDF().setIdCodigoBarra(cb);
				this.session.save(secc);
			}
			log.info("ComprobanteDAO::guardaNuevoComprobante: codBarras:" + cb + ": secciones, detalles y configs guardados.");

			nomina.setIdCodigoBarras(cb);
			this.session.merge(nomina);
			this.session.flush();
			log.info("ComprobanteDAO::guardaNuevoComprobante: codBarras:" + cb + ": nomina con nuevo codigo asignado.");

			return cb;
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:guardaComprobante:", ex);
			throw new DaoException("Problemas guardaComprobante", ex);
		}
	}

	/**
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param idCotizante
	 * @return
	 * @throws DaoException
	 */
	public List getApvs(int idEmpresa, int idConvenio) throws DaoException
	{
		try
		{
			log.info("ComprobanteDAO:getApvs::" + idEmpresa + "::" + idConvenio + "::");

			return this.session.createCriteria(ApvVO.class).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio))).addOrder(
					Order.desc("idCotizante")).list();
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO:getApvs:", ex);
			throw new DaoException("ComprobanteDAO:getApvs:", ex);
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
			this.session.flush();
			
			
				FoliacionVO foliacionVO =  (FoliacionVO)this.session.createCriteria(FoliacionVO.class)
										.add(Restrictions.eq("idEntPagadora",new Integer(idEntPagadora)))
										.uniqueResult();

			
				folioActualizado = foliacionVO.getFolioActual() + 1;
	    	
				FoliacionVO foliacionVO2 =foliacionVO;
				foliacionVO2.setFolioActual(folioActualizado);
				
				this.session.save(foliacionVO2);
				this.session.flush();
				
		} catch(Exception ex) 
		{
			log.error("ComprobanteDAO:getSecuenciaFolio error:", ex);
			throw new DaoException("Problemas en ComprobanteDAO.getSecuenciaFolio", ex);
		}
		return folioActualizado;
	}
	
	/**
	 * @author gmallea
	 * 
	 * 27-02-2012  Actualiza el folio impresion en la tabla detalle seccion
	 * @throws DaoException
	 */
	public void actualizaFolioDetalleSeccion(long idCodigoBarra, int tipo, int newFolio) throws DaoException
	{
		try
		{
		    	Query query = this.session.createQuery("UPDATE DetalleSeccionVO "+
							    			    	   " SET folioImpresion  = ? " +
													   " WHERE idCodigoBarra =  ? "+
													   " and idTipoSeccion = ?");
							    			
		    			query.setInteger(0,newFolio);
		    			query.setLong(1,idCodigoBarra);
		    			query.setInteger(2,tipo);
						query.executeUpdate();
			
			
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO.actualizaFolioDetalleSeccion");
			throw new DaoException("ComprobanteDAO:actualizaFolioDetalleSeccion:", ex);
		}
	}
	/**
	 * 
	 * @param mailTo
	 * @param fullNombre
	 * @param passwordDefault
	 * @param parametros
	 */
	public void enviarMailInependientePago(String mailTo, HashMap parametros,int codigoErrorPago, String glosaErrorPago ,String rutEmpresa, int periodo, String montoCredito,
											String montoLeasing, String montoAporte, String totalComprobante)
	{
		if (parametros.containsKey("" + Constants.PARAM_MAIL_HOST_LOCAL) && 
				parametros.containsKey("" + Constants.PARAM_MAIL_FROM) && parametros.containsKey("" + Constants.PARAM_MAIL_USER) && 
				parametros.containsKey("" + Constants.PARAM_MAIL_PASS) &&  parametros.containsKey("" + Constants.PARAM_MAIL_HOST_TO) &&
				parametros.containsKey("" + Constants.PARAM_MAIL_PORT))
		{
			try
			{
				log.info("\n\n\nparametros envio mail Respuesta Pago:");
				log.info(" mailTo:" + mailTo + " mailHostTo:" + (String) parametros.get("" + Constants.PARAM_MAIL_HOST_TO) + " mailFrom:" + (String) parametros.get("" + Constants.PARAM_MAIL_FROM) + " userMail:"
						+ (String) parametros.get("" + Constants.PARAM_MAIL_USER) + " passMail:" + (String) parametros.get("" + Constants.PARAM_MAIL_PASS) + " mailHostLocal:" + (String) parametros.get("" + Constants.PARAM_MAIL_HOST_LOCAL) + "::");
				MailRespuestaPago mail = new MailRespuestaPago( Integer.parseInt((String)parametros.get("" + Constants.PARAM_MAIL_PORT)),
											mailTo,
											(String)parametros.get("" + Constants.PARAM_MAIL_HOST_TO),
											(String)parametros.get("" + Constants.PARAM_MAIL_FROM),
											(String)parametros.get("" + Constants.PARAM_MAIL_USER),
											(String)parametros.get("" + Constants.PARAM_MAIL_PASS),
											(String)parametros.get("" + Constants.PARAM_MAIL_HOST_LOCAL),
											codigoErrorPago,
											glosaErrorPago,
											rutEmpresa,
											periodo,
											montoCredito,
											montoLeasing,
											montoAporte,
											totalComprobante);


				EnviarResultadoPago.enviar(mail);
			} catch (Exception e)
			{
			}
		}
	}
}
