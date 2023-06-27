package cl.araucana.adminCpe.hibernate.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConfigPDFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EstadoComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.adminCpe.hibernate.beans.SPLConvenioVO;
import cl.araucana.adminCpe.hibernate.beans.SPLMedioPagoVO;
import cl.araucana.adminCpe.hibernate.beans.SPLPagoVO;

/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.12
 */
public class ComprobanteDAO
{
	private static Logger log = Logger.getLogger(ComprobanteDAO.class);
	private Session session;
	private boolean loggear = true;

	public ComprobanteDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * 
	 * @param idCodigoBarra
	 * @return
	 * @throws DaoException
	 */
	public ComprobanteVO getComprobantePago(long idCodigoBarra) throws DaoException
	{
		try
		{
			if (this.loggear)
				log.info("ComprobanteDAO:getComprobantePago:");
			return (ComprobanteVO) this.session.get(ComprobanteVO.class, new Long(idCodigoBarra));
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO:getComprobantePago", ex);
			throw new DaoException("ComprobanteDAO:getComprobantePago:", ex);
		}
	}

	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public HashMap getEstadosCmps() throws DaoException
	{
		try
		{
			HashMap result = new HashMap();
			if (this.loggear)
				log.info("ComprobanteDAO:getEstadoPago:");

			List tmpEstado = this.session.createCriteria(EstadoComprobanteVO.class).list();
			for (Iterator it = tmpEstado.iterator(); it.hasNext();)
			{
				EstadoComprobanteVO estadoComprobanteVO = (EstadoComprobanteVO) it.next();
				result.put("" + estadoComprobanteVO.getIdEstado(), estadoComprobanteVO.getDescripcion().trim());
			}
			return result;
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO:getEstadoPago", ex);
			throw new DaoException("ComprobanteDAO:getEstadoPago:", ex);
		}
	}

	/**
	 * 
	 * @return lista comprobantes pago
	 * @throws DaoException
	 */
	public List getListaComprobantePago() throws DaoException
	{
		try
		{
			if (this.loggear)
				log.info("ComprobanteDAO:getListaComprobantePago:");
			return this.session.createCriteria(ComprobanteVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO:getListaComprobantePago", ex);
			throw new DaoException("ComprobanteDAO:getListaComprobantePago:", ex);
		}
	}

	/**
	 * 
	 * @param estados
	 * @return lista comprobantes pago estado
	 * @throws DaoException
	 */
	public List getListaComprobantePagoByEstado(List estados) throws DaoException
	{
		try
		{
			if (!estados.isEmpty())
			{
				if (this.loggear)
					log.info("ComprobanteDAO:getListaComprobantePago:");
				return this.session.createCriteria(ComprobanteVO.class).add(Restrictions.in("idEstado", estados)).list();
			}
			return new ArrayList();
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO:getListaComprobantePago", ex);
			throw new DaoException("ComprobanteDAO:getListaComprobantePago:", ex);
		}
	}

	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaComprobanteSpl() throws DaoException
	{
		try
		{
			Class tipo = ComprobanteVO.class;
			if (this.loggear)
				log.info("ComprobanteDAO:getListaComprobantePago:");
			return this.session.createCriteria(tipo).add(Restrictions.eq("formaPago", new Byte((byte) Constants.FORMA_PAGO_SPL))).addOrder(Order.asc("idCodigoBarra")).list();
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO:getListaComprobantePago", ex);
			throw new DaoException("ComprobanteDAO:getListaComprobantePago:", ex);
		}
	}

	/**
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
			log.error("ERROR ComprobanteDAO:guardaComprobante:", ex);
			throw new DaoException("Problemas guardaComprobante", ex);
		}
	}

	/**
	 * obtiene los comprobantes , antiguo
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getCmpsNoPagados() throws DaoException
	{
		try
		{
			if (this.loggear)
				log.info("ComprobanteDAO:getCmpsNoPagados:");
			List listEstados = new ArrayList();
			listEstados.add(new Integer(Constants.EST_CMP_POR_PAGAR) );
			listEstados.add(new Integer(Constants.EST_CMP_PAGO_PARCIAL) );
			listEstados.add(new Integer(Constants.EST_CMP_PAGADO) );
			
				return this.session.createCriteria(ComprobanteVO.class)
							.add(Restrictions.in("idEstado", listEstados))
							.list();
			
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO:getCmpsNoPagados", ex);
			throw new DaoException("ComprobanteDAO:getCmpsNoPagados:", ex);
		}
	}

	/**
	 * 
	 * @param listaCodBarras
	 * @return
	 * @throws DaoException
	 */
	public List getComprobantes(List listaCodBarras) throws DaoException
	{
		try
		{
			if (!listaCodBarras.isEmpty())
			{
				log.info("ComprobanteDAO:getComprobantes");
				Criteria crit = this.session.createCriteria(ComprobanteVO.class);
				return crit.add(Restrictions.in("idCodigoBarra", listaCodBarras)).list();
			}
			return new ArrayList();
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:getComprobantes:", ex);
			throw new DaoException("Problemas obteniendo Lista de comprobantes por codigos de barras", ex);
		}
	}

	/**
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
			log.error("ERROR ComprobanteDAO:loadConfigPDF:", ex);
			throw new DaoException("Problemas loadConfigPDF", ex);
		}
	}

	/**
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
			log.error("Error en ComprobanteDAO:getComprobante", ex);
			throw new DaoException("Error en ComprobanteDAO:getComprobante", ex);
		}
	}

	/**
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
			log.error("Error en ComprobanteDAO:getComprobante", ex);
			throw new DaoException("Error en ComprobanteDAO:getComprobante", ex);
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
			throw new DaoException("Problemas obteniendo la nomina a partir del comprobante: " + codigoBarra + "::", ex);
		}
	}

	public ConvenioVO getConvenio(int rutEmpresa, int idConvenio)
	{
		return (ConvenioVO)this.session.get(ConvenioVO.class, new ConvenioVO(rutEmpresa, idConvenio));
	}
	
	/**
	 * 
	 * @param comprobante
	 * @throws DaoException
	 */
	public void updateComprobante(ComprobanteVO comprobante) throws DaoException
	{
		try
		{
		
			
			Query query = this.session.createQuery(" update "+ ComprobanteVO.class.getName() + " c set c.idEstado = "+comprobante.getIdEstado()  +" , c.folioTesoreria = "+comprobante.getFolioTesoreria()+
												   " , c.formaPago = "+comprobante.getFormaPago()+" , c.medioPago= "+comprobante.getMedioPago()+
												   " , c.pagado= '1969-12-31' , c.numTrabajadores= "+comprobante.getNumTrabajadores()+" , c.cierre= "+comprobante.getCierre()+"  "+
												   " where c.idCodigoBarra = "+comprobante.getIdCodigoBarra() );
			
			query.executeUpdate();
		
			log.info("ComprobanteDAO:updateComprobante");

		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:updateComprobante:", ex);
			throw new DaoException("Problemas updateComprobante", ex);
		}
	}
	//si
	public List getSecciones(long idCodigoBarra) throws DaoException
	{
		try
		{
			return this.session.createCriteria(SeccionVO.class).add(Restrictions.eq("idCodigoBarra", new Long(idCodigoBarra))).list();
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO.getSecciones");
			throw new DaoException("Error en ComprobanteDAO.getSecciones", ex);
		}
	}
	
	
	public List getConfigPDFVO(long idCodigoBarra) throws DaoException
	{
		try
		{
			return  this.session.createCriteria(ConfigPDFVO.class).add(Restrictions.eq("idCodigoBarra", new Long(idCodigoBarra))).list();
			
			
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO.getConfigPDFVO");
			throw new DaoException("Error en ComprobanteDAO.getConfigPDFVO", ex);
		}
	}
	

	
	//si
	public void eliminarComprobante(ComprobanteVO comprobanteVO, List listConfigPDFVO) throws DaoException
	{
		try
		{	
				for (Iterator it = listConfigPDFVO.iterator(); it.hasNext();){
				
					ConfigPDFVO configPDFVO =  (ConfigPDFVO) it.next();
				
							this.session.delete(configPDFVO);
							log.info(" * configPDFVO Eliminada * ");
				}
			
				this.session.delete(comprobanteVO);
				log.info(" * comprobanteVO Eliminada * ");
			
				this.session.flush();
				
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO.eliminarComprobante");
			throw new DaoException("Error en ComprobanteDAO.eliminarComprobante", ex);
		}
	}
	//jlandero - asepulveda cambios total independiente
	//si
	public ComprobanteVO guardaComprobante(int idDocumento,List secciones, List  configPDF) throws DaoException
	{
		return this.guardaComprobante(idDocumento,secciones, configPDF, 0);
	}
	
	
	public ComprobanteVO guardaComprobante(int idDocumento,List secciones, List  configPDF, long total) throws DaoException
	{
		SeccionVO seccionNew = null;
		DetalleSeccionVO detalleSeccionNew = null;
		List listDetalleSeccion = null;
		ComprobanteVO comprobante = new ComprobanteVO();
		
		try
		{	
			comprobante.setIdEstado('3');
			comprobante.setIdCodigoBarra(0);
			comprobante.setFolioTesoreria(0);
			java.util.Date date = new java.util.Date();
			java.sql.Date utilDate = new java.sql.Date(date.getTime());
			comprobante.setEmitido(new Timestamp(utilDate.getTime()));
			byte by = 0;
			comprobante.setFormaPago(by);
			comprobante.setMedioPago(by);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date res = null;
			String fecha=  "1969-12-31"; 
			 
			res = format.parse(fecha);
			 
			comprobante.setPagado(new Timestamp(res.getTime()));
			comprobante.setNumTrabajadores(1);
			comprobante.setCierre(0);
			comprobante.setSecciones(new ArrayList());
			comprobante.setIdDocumento(idDocumento);
	 
			
			log.info("ValidacionDAO:guardaComprobante::");
			this.session.flush();
			if (comprobante.getTotal() < 0)
				comprobante.setTotal(0);
			else{
				comprobante.setTotal(total);
			}
			this.session.save(comprobante);
			this.session.flush();
			// log.info(ToStringBuilder.reflectionToString(comprobante, ToStringStyle.MULTI_LINE_STYLE));
			long cb = comprobante.getIdCodigoBarra();
			log.info("ValidacionDAO:guardaComprobante:CB:" + cb + "::");
			for (Iterator itSecc = secciones.iterator(); itSecc.hasNext();)
			{
				SeccionVO seccion = (SeccionVO) itSecc.next();
				
				seccionNew = new SeccionVO();
				seccionNew.setIdCodigoBarra(cb);
				seccionNew.setIdTipoSeccion(seccion.getIdTipoSeccion());
				seccionNew.setM(seccion.getM());
				seccionNew.setMm1(seccion.getMm1());
				seccionNew.setMm2(seccion.getMm2());
				seccionNew.setMm3(seccion.getMm3());
				seccionNew.setMm4(seccion.getMm4());
				seccionNew.setMm5(seccion.getMm5());
				seccionNew.setMm6(seccion.getMm6());
				seccionNew.setMm7(seccion.getMm7());
				seccionNew.setMm8(seccion.getMm8());
				seccionNew.setMm9(seccion.getMm9());
				seccionNew.setMm10(seccion.getMm10());
				seccionNew.setMm11(seccion.getMm11());
				seccionNew.setMm12(seccion.getMm12());
				seccionNew.setNumTrabajadores(seccion.getNumTrabajadores());
				seccionNew.setTipoPago(seccion.getTipoPago());
				seccionNew.setTipoSeccion(seccion.getTipoSeccion());
				seccionNew.setConfigPDF(seccion.getConfigPDF());
				seccionNew.setTotal(seccion.getTotal());
				seccionNew.setComprobante(seccion.getComprobante());
				
				//seccion.refreshMUnitarios();
				//seccion.setIdCodigoBarra(cb);

				if (seccion.getDetallesSeccion() != null)
					for (Iterator itDet = seccion.getDetallesSeccion().iterator(); itDet.hasNext();)
					{
						listDetalleSeccion = new ArrayList();
						
						DetalleSeccionVO detalle = (DetalleSeccionVO) itDet.next();
						
						detalleSeccionNew = new DetalleSeccionVO();
						detalleSeccionNew.setIdCodigoBarra(cb);
						detalleSeccionNew.setIdDetalleSeccion(detalle.getIdDetalleSeccion());
						detalleSeccionNew.setIdTipoSeccion(detalle.getIdTipoSeccion());
						detalleSeccionNew.setM(detalle.getM());
						detalleSeccionNew.setM1(detalle.getM1());
						detalleSeccionNew.setM2(detalle.getM2());
						detalleSeccionNew.setM3(detalle.getM3());
						detalleSeccionNew.setM4(detalle.getM4());
						detalleSeccionNew.setM5(detalle.getM5());
						detalleSeccionNew.setM6(detalle.getM6());
						detalleSeccionNew.setM7(detalle.getM7());
						detalleSeccionNew.setM8(detalle.getM8());
						detalleSeccionNew.setM9(detalle.getM9());
						detalleSeccionNew.setM10(detalle.getM10());
						detalleSeccionNew.setM11(detalle.getM11());
						detalleSeccionNew.setM12(detalle.getM12());
						detalleSeccionNew.setNumTrabajadores(detalle.getNumTrabajadores());
						detalleSeccionNew.setTipoPago(detalle.getTipoPago());
						detalleSeccionNew.setTotalDetalleSeccion(detalle.getTotalDetalleSeccion());
						detalleSeccionNew.setSeccion(detalle.getSeccion());
						
						listDetalleSeccion.add(detalleSeccionNew);
												
						// log.info(ToStringBuilder.reflectionToString(detalle, ToStringStyle.MULTI_LINE_STYLE));
					}
				seccionNew.setDetallesSeccion(listDetalleSeccion);
					// log.info(ToStringBuilder.reflectionToString(seccion, ToStringStyle.MULTI_LINE_STYLE));
				this.session.save(seccionNew);
				this.session.flush();
			
			}
			
			
			ConfigPDFVO config = null;
			for(Iterator iterPdf = configPDF.iterator() ; iterPdf.hasNext();){
				
				ConfigPDFVO configPDFVO = (ConfigPDFVO) iterPdf.next();
				config = new ConfigPDFVO();
				config.setIdCodigoBarra(cb);
				config.setIdTipoSeccion(configPDFVO.getIdTipoSeccion());
				config.setMxAsterisco(configPDFVO.getMxAsterisco());
				config.setMxNoMostrar(configPDFVO.getMxNoMostrar());
				config.setNumTrabM1(configPDFVO.getNumTrabM1());
				config.setNumTrabM2(configPDFVO.getNumTrabM2());
				config.setNumTrabM3(configPDFVO.getNumTrabM3());
				config.setNumTrabM4(configPDFVO.getNumTrabM4());
				config.setNumTrabM5(configPDFVO.getNumTrabM5());
				config.setNumTrabM6(configPDFVO.getNumTrabM6());
				config.setNumTrabM7(configPDFVO.getNumTrabM7());
				config.setNumTrabM8(configPDFVO.getNumTrabM8());
				config.setNumTrabM9(configPDFVO.getNumTrabM9());
				config.setNumTrabM10(configPDFVO.getNumTrabM10());
				config.setNumTrabM11(configPDFVO.getNumTrabM11());
				config.setNumTrabM12(configPDFVO.getNumTrabM12());
				
				this.session.save(config);
				this.session.flush();
				
			}
			
			log.info("ValidacionDAO:guardaComprobante: FIN:n secciones:");
		} catch (Exception e)
		{
			log.error("ValidacionDAO:guardaComprobante error:", e);
			throw new DaoException("ERROR ValidacionDAO:guardaComprobante:", e);
		}
		return comprobante;
	}
	
	/**
	 * spl pago
	 * 
	 * @param idTrx
	 * @return
	 * @throws DaoException
	 */
	public SPLPagoVO getSPLPago(long idPago) throws DaoException
	{
		SPLPagoVO pago = null;
		try
		{
			log.info("ComprobanteDAO:getSPLPago:idTrx:" + idPago + "::");
			Criteria crit = this.session.createCriteria(SPLPagoVO.class);
			List result = crit.add(Restrictions.eq("idPago", new Long(idPago))).list();
			if (result != null && result.size() > 0)
			{
				pago = (SPLPagoVO) result.get(0);
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
			return pago;
			//throw new DaoException("No se pudo encontrar transaccion (SPLDTA.PAGO), o datos corruptos idTrx:" + idTrx + "::");
		} catch (Exception ex)
		{
			log.error("ERROR ComprobanteDAO:getSPLPago:" + ex);
			throw new DaoException("No se pudo encontrar transaccion (SPLDTA.PAGO), o datos corruptos idTrx:" + idPago + "::", ex);
		}
	}
	
	/**
	 * Se obtienen los comprobantes de empresa o independiente segun que codigos de barra manden por parametro y que sean igual a  3, 4, 5  
	 * 
	 * @author gmallea
	 * 
	 * @param List listCodigoBarra
	 * @return
	 * @throws DaoException
	 */
	public List getCmpsNoPagadosEmpresaEIndependiente(List listCodigoBarra) throws DaoException
	{
		try
		{
			if (this.loggear)
				log.info("ComprobanteDAO:getCmpsNoPagadosEmpresa:");
			List listEstados = new ArrayList();
			listEstados.add(new Integer(Constants.EST_CMP_POR_PAGAR) );
			listEstados.add(new Integer(Constants.EST_CMP_PAGO_PARCIAL) );
			listEstados.add(new Integer(Constants.EST_CMP_PAGADO) );
			
			List listCodBarra = new ArrayList();
			
				for(Iterator iter = listCodigoBarra.iterator() ; iter.hasNext();){
					listCodBarra.add(new Long(iter.next().toString()) );
				}
			
					return this.session.createCriteria(ComprobanteVO.class)
								.add(Restrictions.in("idEstado", listEstados))
								.add(Restrictions.in("idCodigoBarra", listCodBarra))
								.list();
			
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteDAO:getCmpsNoPagadosEmpresa", ex);
			throw new DaoException("ComprobanteDAO:getCmpsNoPagadosEmpresa:", ex);
		}
	}

}
