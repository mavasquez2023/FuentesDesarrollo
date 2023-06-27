package cl.araucana.cp.distribuidor.hibernate.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ApellidoCompuestoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AvisosCotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.BalanceoCargaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConfigPDFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DescriptorNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DocumentoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadExCajaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSilVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EventoCargaValidacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAsFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoGeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MovtoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegImpositivoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMovimientoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ValidacionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

public class ValidacionDAO
{
	private static Logger log = Logger.getLogger(ValidacionDAO.class);
	private Session session;
	private ParametrosDAO parametrosDao;
	
	public ValidacionDAO(Session session)
	{
		this.session = session;
//		clillo 13-10-2017 AFP
		this.parametrosDao = new ParametrosDAO(session);
	}

	public NominaVO getNomina(char tipoProceso, int rutEmpresa, int idConvenio) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getNomina:tipoProceso:" + tipoProceso + ":rutEmpresa:" + rutEmpresa + ":idConvenio" + idConvenio + "::");
			NominaVO nomina = null;
			if (tipoProceso == 'R')
				nomina = new NominaREVO(idConvenio, rutEmpresa);
			else if (tipoProceso == 'G')
				nomina = new NominaGRVO(idConvenio, rutEmpresa);
			else if (tipoProceso == 'A')
				nomina = new NominaRAVO(idConvenio, rutEmpresa);
			else
				nomina = new NominaDCVO(idConvenio, rutEmpresa);

			return (NominaVO) this.session.get(nomina.getClass(), nomina);
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getNomina:", ex);
		}
	}

	public int getIdDocumento(char tipoProceso, int rutEmpresa, int idConvenio) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getIdDocumento:tipoProceso:" + tipoProceso + ":rutEmpresa:" + rutEmpresa + ":idConvenio" + idConvenio + "::");
			String queryHql = "SELECT doc.id FROM " + DocumentoVO.class.getName() + " doc "
					+ "WHERE doc.rutEmpresa = :empr AND doc.idConvenio = :conv AND doc.tipoProceso = :tp ORDER BY doc.idEnvio DESC";
			Query query = this.session.createQuery(queryHql);
			query.setInteger("empr", rutEmpresa);
			query.setInteger("conv", idConvenio);
			query.setCharacter("tp", tipoProceso);
			query.setMaxResults(1);
			List l = query.list();
			if (l.size() > 0)
				return ((Integer)l.get(0)).intValue();
			return 0;
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getIdDocumento:", ex);
		}
	}

	public EmpresaVO getEmpresa(int rutEmpresa) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getEmpresa:rutEmpresa:" + rutEmpresa + "::");
			return (EmpresaVO) this.session.get(EmpresaVO.class, new Integer(rutEmpresa));
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getEmpresa:", ex);
		}
	}

	public OpcionProcVO getOpcionProcesos(int idOpcion) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getOpcionProcesos:idOpcion:" + idOpcion + "::");
			// if (opcion != null)
			return (OpcionProcVO) this.session.get(OpcionProcVO.class, new Integer(idOpcion));
			// throw new DaoException("ERROR ValidacionDAO:getOpcionProcesos:no se encontro opcion procesos asociado:" + idOpcion + "::");
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getOpcionProcesos:", ex);
		}
	}

	public HashMap getCotizantes(char tipoProceso, NominaVO nomina) throws DaoException
	{
		try
		{
			log.info("\n\nValidacionDAO:getCotizantes:rutEmpresa:" + nomina.getRutEmpresa() + ":idConvenio" + nomina.getIdConvenio() + "::");

			List lista = this.session.createCriteria(CotizanteVO.class)
									 .add(Restrictions.eq("rutEmpresa", new Integer(nomina.getRutEmpresa())))
									 .add(Restrictions.eq("idConvenio", new Integer(nomina.getIdConvenio())))
									 .addOrder( Order.asc("idCotizante") )
									 .list();
			log.info("get Cotizaciones");
			List lista2= null;
			if (tipoProceso == 'R')
			{
				lista2 = this.session.createCriteria(CotizacionREVO.class)
				 .add(Restrictions.eq("rutEmpresa", new Integer(nomina.getRutEmpresa())))
				 .add(Restrictions.eq("idConvenio", new Integer(nomina.getIdConvenio())))
				 .addOrder( Order.asc("idCotizante") )
				 .list();
			} else if (tipoProceso == 'G'){
				lista2 = this.session.createCriteria(CotizacionGRVO.class)
				 .add(Restrictions.eq("rutEmpresa", new Integer(nomina.getRutEmpresa())))
				 .add(Restrictions.eq("idConvenio", new Integer(nomina.getIdConvenio())))
				 .addOrder( Order.asc("idCotizante") )
				 .list();
			}else if (tipoProceso == 'A'){
				lista2 = this.session.createCriteria(CotizacionRAVO.class)
				 .add(Restrictions.eq("rutEmpresa", new Integer(nomina.getRutEmpresa())))
				 .add(Restrictions.eq("idConvenio", new Integer(nomina.getIdConvenio())))
				 .addOrder( Order.asc("idCotizante") )
				 .list();
			}else{
				lista2 = this.session.createCriteria(CotizacionDCVO.class)
				 .add(Restrictions.eq("rutEmpresa", new Integer(nomina.getRutEmpresa())))
				 .add(Restrictions.eq("idConvenio", new Integer(nomina.getIdConvenio())))
				 .addOrder( Order.asc("idCotizante") )
				 .list();
			}
			
			log.info("Lista cotizantes, iterando sobre " + lista.size() + " registros");
			HashMap result = new HashMap();
			int i=1;
			for (Iterator it = lista.iterator(); it.hasNext();)
			{	
				if(i%1000==0){
					log.info("Obteniendo Cotizacion trabajador:" + i);
				}
				CotizanteVO cot = (CotizanteVO) it.next();
				CotizacionVO cotizacion = null;
				cotizacion= (CotizacionVO)lista2.get(i-1);
				
				/*if (tipoProceso == 'R')
				{
					cotizacion = new CotizacionREVO(nomina.getRutEmpresa(), nomina.getIdConvenio(), cot.getIdCotizante());
					List listaAPV = this.session.createCriteria(ApvVO.class)
									    		.add(Restrictions.eq("rutEmpresa", new Integer(nomina.getRutEmpresa())))
									    		.add(Restrictions.eq("idConvenio", new Integer(nomina.getIdConvenio())))
									    		.add(Restrictions.eq("idCotizante", new Integer(cot.getIdCotizante())))
									    		.list();
					cot.setApvList(listaAPV);
				} else if (tipoProceso == 'G')
					cotizacion = new CotizacionGRVO(nomina.getRutEmpresa(), nomina.getIdConvenio(), cot.getIdCotizante());
				else if (tipoProceso == 'A')
					cotizacion = new CotizacionRAVO(nomina.getRutEmpresa(), nomina.getIdConvenio(), cot.getIdCotizante());
				else
					cotizacion = new CotizacionDCVO(nomina.getRutEmpresa(), nomina.getIdConvenio(), cot.getIdCotizante());
				
				cotizacion = (CotizacionVO) this.session.get(cotizacion.getClass(), cotizacion);
				*/
				if (cotizacion != null)
				{
					cot.setCotizacion(cotizacion);
					if (tipoProceso == 'R')
						cot.setAfpVoluntario(((CotizacionREVO) cotizacion).isVoluntario());
					result.put("" + cot.getIdCotizante(), cot);
				}
				i++;
			}
			List listaAPV = this.session.createCriteria(ApvVO.class)
    		.add(Restrictions.eq("rutEmpresa", new Integer(nomina.getRutEmpresa())))
    		.add(Restrictions.eq("idConvenio", new Integer(nomina.getIdConvenio())))
    		.addOrder( Order.asc("idCotizante"))
    		.list();
			
			HashMap mapAPVs = new HashMap();
			for (Iterator it = listaAPV.iterator(); it.hasNext();)
			{
				ApvVO apv = (ApvVO) it.next();
				String idCotizante= "" + apv.getIdCotizante();
				List apvcot= (List)mapAPVs.get(idCotizante);
				if(apvcot==null){
					apvcot= new ArrayList();
				}
				apvcot.add(apv);
				mapAPVs.put(idCotizante, apvcot);	
			}
			
			for (Iterator it = mapAPVs.keySet().iterator(); it.hasNext();)
			{
				String idCotizante = (String) it.next();
				List apvList= (List)mapAPVs.get(idCotizante);
				CotizanteVO cot = (CotizanteVO)result.get(idCotizante);
				cot.setApvList(apvList);
				result.put("" + cot.getIdCotizante(), cot);
			}
			
			return result;
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR Hibernate, ValidacionDAO:getCotizantes: Nomina:" + nomina.getRutEmpresa()+ ", C:"+ nomina.getConvenio(), ex);
		} catch (Exception e){
			throw new DaoException("ERROR Hibernate, ValidacionDAO:getCotizantes: Nomina:" + nomina.getRutEmpresa()+ ", C:"+ nomina.getConvenio(), e);
		}
	}

	public DocumentoVO getDocumento(char tipoProceso, int rutEmpresa, int idConvenio) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getIdDocumento:tipoProceso:" + tipoProceso + ":rutEmpresa:" + rutEmpresa + ":idConvenio" + idConvenio + "::");
			String queryHql = "SELECT doc FROM " + DocumentoVO.class.getName() + " doc "
					+ "WHERE doc.rutEmpresa = :empr AND doc.idConvenio = :conv AND doc.tipoProceso = :tp ORDER BY doc.idEnvio DESC";
			Query query = this.session.createQuery(queryHql);
			query.setInteger("empr", rutEmpresa);
			query.setInteger("conv", idConvenio);
			query.setCharacter("tp", tipoProceso);
			query.setMaxResults(1);
			List l = query.list();
			if (l.size() > 0)
				return (DocumentoVO)l.get(0);
			return null;
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getIdDocumento:", ex);
		}
	}

	public List getListaValidaciones(String tipoProceso) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getListaValidaciones");
			Criteria crit = this.session.createCriteria(ValidacionVO.class);
			crit.add(Restrictions.eq("ejecutarEn", "S"));
			return crit.add(Restrictions.eq("tipoProceso", tipoProceso)).list();
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getListaValidaciones:", ex);
		}
	}

	public List getListaValReforma(String tipoProceso) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getListaValReforma");
			Criteria crit = this.session.createCriteria(ValidacionVO.class);
			crit.add(Restrictions.eq("ejecutarEn", tipoProceso));
			return crit.add(Restrictions.eq("tipoProceso", tipoProceso)).list();
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getListaValReforma:", ex);
		}
	}

	public List getListaConceptos(String tipoProceso) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getListaConceptos:" + tipoProceso);
			Criteria crit = this.session.createCriteria(ConceptoVO.class);
			return crit.add(Restrictions.eq("tipoProceso", tipoProceso)).list();
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getListaConceptos:" + tipoProceso + ":", ex);
		}
	}

	public HashMap getListaMapeo(String tipoProceso, int idMapaNom) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getListaMapeo");
			Criteria crit = this.session.createCriteria(MapeoConceptoVO.class);
			List lista = crit.add(Restrictions.eq("tipoProceso", tipoProceso)).add(Restrictions.eq("idMapa", new Integer(idMapaNom))).list();
			HashMap result = new HashMap();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				MapeoConceptoVO mc = (MapeoConceptoVO) it.next();
				result.put("" + mc.getIdConcepto(), mc);
			}
			return result;
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getListaMapeo:", ex);
		}
	}

	public List getLstValidaMovPers() throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getLstValidaMovPers");
			Criteria crit = this.session.createCriteria(ValidacionVO.class);
			return crit.add(Restrictions.eq("ejecutarEn", "M")).list();
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getLstValidaMovPers:", ex);
		}
	}

	public List getLstValidaAPVs() throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getLstValidaAPVs");
			Criteria crit = this.session.createCriteria(ValidacionVO.class);
			return crit.add(Restrictions.eq("ejecutarEn", "A")).list();
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getLstValidaAPVs:", ex);
		}
	}

	public boolean guardaNomina(NominaVO nomina)
	{
		try
		{
			log.info("ValidacionDAO:guardaNomina");
			log.info(nomina.toString());
			NominaVO nomPK = null;
			if (nomina instanceof NominaREVO)
				nomPK = new NominaREVO(nomina.getIdConvenio(), nomina.getRutEmpresa());
			else if (nomina instanceof NominaGRVO)
				nomPK = new NominaGRVO(nomina.getIdConvenio(), nomina.getRutEmpresa());
			else if (nomina instanceof NominaRAVO)
				nomPK = new NominaRAVO(nomina.getIdConvenio(), nomina.getRutEmpresa());
			else
				nomPK = new NominaDCVO(nomina.getIdConvenio(), nomina.getRutEmpresa());
			// log.info(ToStringBuilder.reflectionToString(nomina, ToStringStyle.MULTI_LINE_STYLE));
			NominaVO n = (NominaVO) this.session.get(nomPK.getClass(), nomPK);
			if (n == null)
				this.session.save(nomina);
			else
				// ya existia
				this.session.merge(nomina);
			return true;
		} catch (Exception e)
		{
			log.error("ValidacionDAO:guardaNomina error:", e);
			return false;
		}
	}

	public boolean guardaConvenio(ConvenioVO convenio)
	{
		try
		{
			log.info("ValidacionDAO:guardaConvenio");
			// log.info(ToStringBuilder.reflectionToString(convenio, ToStringStyle.MULTI_LINE_STYLE));
			convenio.setDescripcion(convenio.getDescripcion().trim());
			this.session.merge(convenio);
			log.info("ValidacionDAO:guardaConvenio2");
			return true;
		} catch (Exception e)
		{
			log.error("ValidacionDAO:guardaConvenio error:", e);
			return false;
		}
	}

	public void setNRegDescriptor(char tipoProceso, int idConvenio, int rutEmpresa, int idEnvio, int nRegistros) throws DaoException
	{
		log.info("ValidacionDAO:setNRegDescriptor:");
		try
		{
			DescriptorNominaVO descriptor = new DescriptorNominaVO(idEnvio, idConvenio, tipoProceso, rutEmpresa);
			descriptor = (DescriptorNominaVO) this.session.load(DescriptorNominaVO.class, descriptor);
			descriptor.setNumRegistros(nRegistros);
			this.session.update(descriptor);
		} catch (Exception e)
		{
			log.info("ERROR:ValidacionDAO:setNRegDescriptor:", e);
			throw new DaoException("ERROR ValidacionDAO:setNRegDescriptor:", e);
		}
	}

	public void addLineasBalanceoCarga(char tipoProceso, int idConvenio, int rutEmpresa, int numLineas) throws DaoException
	{
		log.info("ValidacionDAO:addLineasBalanceoCarga:" + tipoProceso + "::" + idConvenio + "::" + rutEmpresa + ":numLineas:" + numLineas + "::");
		try
		{
			BalanceoCargaVO bc = new BalanceoCargaVO(tipoProceso, rutEmpresa, idConvenio);
			bc.addNumLineas(numLineas);
			BalanceoCargaVO bcTmp = (BalanceoCargaVO) this.session.get(BalanceoCargaVO.class, bc);
			if (bcTmp == null)
			{
				bc.setNumPeriodos(1);
				this.session.save(bc);
			} else
			{
				bc.setNumLineas(numLineas);
				this.session.merge(bc);
			}
		} catch (Exception e)
		{
			log.info("ERROR:ValidacionDAO:addLineasBalanceoCarga:", e);
			throw new DaoException("ERROR ValidacionDAO:addLineasBalanceoCarga:", e);
		}
	}

	public ConvenioVO getConvenio(int idConvenio, int rutEmpresa) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getConvenio:idConvenio:" + idConvenio + ":rutEmpresa:" + rutEmpresa + "::");
			return (ConvenioVO) this.session.get(ConvenioVO.class, new ConvenioVO(rutEmpresa, idConvenio));
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getConvenio:", ex);
		}
	}

	public GrupoConvenioVO getGrupoConvenio(int idGrupoConvenio) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getGrupoConvenio:idGrupoConvenio:" + idGrupoConvenio + "::");
			return (GrupoConvenioVO) this.session.load(GrupoConvenioVO.class, new Integer(idGrupoConvenio));
		} catch (Exception ex)
		{
			log.error("Error en ValidacionDAO:getGrupoConvenio");
			throw new DaoException("Error al leer grupo convenio idGrupoConvenio:" + idGrupoConvenio + "::", ex);
		}
	}

	public ComprobanteVO getComprobanteVO(long idCodigoBarras) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getComprobanteVO:idCodigoBarras:" + idCodigoBarras + "::");
			Long cb = new Long(idCodigoBarras);
			ComprobanteVO cmp = (ComprobanteVO) this.session.get(ComprobanteVO.class, cb);
			List secciones = this.session.createCriteria(SeccionVO.class).add(Restrictions.eq("idCodigoBarra", cb)).list();

			log.info("ValidacionDAO:getComprobanteVO:encontradas:" + secciones.size() + ":secciones:");
			for (Iterator it = secciones.iterator(); it.hasNext();)
			{
				SeccionVO secc = (SeccionVO) it.next();
				ConfigPDFVO config = (ConfigPDFVO) this.session.get(ConfigPDFVO.class, new ConfigPDFVO(secc.getIdTipoSeccion(), idCodigoBarras));
				if (config != null)
					secc.setConfigPDF(config);
				else
					secc.setConfigPDF(new ConfigPDFVO(12));
			}
			cmp.setSecciones(secciones);
			
//			clillo 13-10-2017 AFP
			List listaParams = new ArrayList();
			listaParams.add(new Integer(Constants.PARAM_FIN_PAGO_CAJA));
			listaParams.add(new Integer(Constants.PARAM_FIN_PAGO_CAJA_IND));
			ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);
				
			String diaFinPagoCaja = paramHash.get("" + Constants.PARAM_FIN_PAGO_CAJA).substring(0, 2);
			cmp.setDiaFinPagoCaja(Integer.parseInt(diaFinPagoCaja));
			
			return new ComprobanteVO(cmp);
		} catch (Exception ex)
		{
			log.error("Error en ValidacionDAO:getComprobanteVO");
			throw new DaoException("Error al leer grupo convenio idCodigoBarras:" + idCodigoBarras + "::", ex);
		}
	}

//	clillo 3-12-14 por Reliquidación
	//public boolean guardaCotizante(char tipoProceso, String oldRut, CotizanteVO cotizante)
	public boolean guardaCotizante(char tipoProceso, String oldRut, CotizanteVO cotizante, int periodo_old)
	{
		try
		{
			log.info("\n\n\nguardaCotizante:oldRut:" + oldRut + ":id:" + cotizante.getIdCotizante() + ":tipoProceso:" + tipoProceso + "::");

			int rutAntiguo = 0;
			boolean cambioRut = false;
			if (!oldRut.equals("new"))
			{
				rutAntiguo = new Integer(oldRut).intValue();
				if (rutAntiguo > 0 && cotizante.getIdCotizante() != rutAntiguo)
					cambioRut = true;
			}

			CotizanteVO cotizante2 = corrigeEntidadesFalsas(cotizante);
			cotizante2.setTipoProceso(tipoProceso);
//			clillo 3-12-14 por Reliquidación
			if (tipoProceso=='A' && cotizante.getPeriodo()!= periodo_old & periodo_old!=0)
			{
				log.info("periodo:old:" + periodo_old + ":new" + cotizante.getPeriodo() + ":: va a eliminar");
				eliminaTrab(cotizante2.getRutEmpresa(), cotizante2.getIdConvenio(), tipoProceso, cotizante2.getIdCotizante(), periodo_old);
			}
			//fin
			if (cambioRut)
			{
				log.info("cambioRut:old:" + rutAntiguo + ":new" + cotizante2.getIdCotizante() + ":: va a eliminar");
//				clillo 3-12-14 por Reliquidación
				//eliminaTrab(cotizante2.getRutEmpresa(), cotizante2.getIdConvenio(), tipoProceso, rutAntiguo);
				eliminaTrab(cotizante2.getRutEmpresa(), cotizante2.getIdConvenio(), tipoProceso, rutAntiguo, cotizante2.getPeriodo());
			}
//			clillo 3-12-14 por Reliquidación
			//CotizanteVO cot = (CotizanteVO) this.session.get(CotizanteVO.class, new CotizanteVO(cotizante2.getRutEmpresa(), cotizante2.getIdConvenio(), cotizante2.getIdCotizante()));
			CotizanteVO cot = (CotizanteVO) this.session.get(CotizanteVO.class, new CotizanteVO(cotizante2.getRutEmpresa(), cotizante2.getIdConvenio(), cotizante2.getIdCotizante(), cotizante.getPeriodo()));
			if (cot == null)
			{
				log.debug("CotizanteVO no existe, save:rut:" + cotizante2.getIdCotizante() + "::");
				cotizante2.setTiene(tipoProceso);
				this.session.save(cotizante2);
			} else
			{
				log.debug("CotizanteVO existe, merge");
				cotizante2.updateTiene(tipoProceso, cot);
				if (tipoProceso == 'D')
				{// recuerda valores ya registrados en cotizante, que tipo D no usa
					// (si hay un registro de otro tipo al mismo tiempo, perderia estos valores, ya que D no los usa)
					cotizante2.merge(cot);
				}
				this.session.merge(cotizante2);
			}

			cotizante2.getCotizacion().setIdCotizante(cotizante2.getIdCotizante());

			CotizacionVO cotizacion = null;
			if (tipoProceso == 'R')
				cotizacion = new CotizacionREVO(cotizante2.getRutEmpresa(), cotizante2.getIdConvenio(), cotizante2.getIdCotizante());
			else if (tipoProceso == 'G')
				cotizacion = new CotizacionGRVO(cotizante2.getRutEmpresa(), cotizante2.getIdConvenio(), cotizante2.getIdCotizante());
			else if (tipoProceso == 'A')
				//cotizacion = new CotizacionRAVO(cotizante2.getRutEmpresa(), cotizante2.getIdConvenio(), cotizante2.getIdCotizante());
				cotizacion = new CotizacionRAVO(cotizante2.getRutEmpresa(), cotizante2.getIdConvenio(), cotizante2.getIdCotizante(), cotizante2.getPeriodo());
			else
				cotizacion = new CotizacionDCVO(cotizante2.getRutEmpresa(), cotizante2.getIdConvenio(), cotizante2.getIdCotizante());
			CotizacionVO cotizGuardada = (CotizacionVO) this.session.get(cotizacion.getClass(), cotizacion);

			if (cotizGuardada == null)
				this.session.save(cotizante2.getCotizacion());
			else
			{
				if (tipoProceso == 'R')
					preparaMvtoPersonal(cotizante2.getCotizacion());
				this.session.merge(cotizante2.getCotizacion());
			}
			if (tipoProceso == 'R' && !cotizante.isAfpVoluntario())
				return guardaApvs(cotizante2);// guarda apvs

			return true;
		} catch (Exception e)
		{
			log.error("ValidacionDAO:guardaCotizante error:", e);
			return false;
		}
	}

	private void preparaMvtoPersonal(CotizacionVO cotizacion)
	{
		CotizacionREVO cot = (CotizacionREVO) cotizacion;
		this.session.flush();
		List movsOld = this.session.createCriteria(MovtoPersonalVO.class).add(Restrictions.eq("rutEmpresa", new Integer(cot.getRutEmpresa()))).add(
				Restrictions.eq("idConvenio", new Integer(cot.getIdConvenio()))).add(Restrictions.eq("idCotizante", new Integer(cot.getIdCotizante()))).list();
		log.info("a borrar movtos:" + movsOld.size() + "::");
		for (Iterator itMovsOld = movsOld.iterator(); itMovsOld.hasNext();)
			this.session.delete(itMovsOld.next());
		movsOld = this.session.createCriteria(MvtoPersoAFVO.class).add(Restrictions.eq("rutEmpresa", new Integer(cot.getRutEmpresa()))).add(
				Restrictions.eq("idConvenio", new Integer(cot.getIdConvenio()))).add(Restrictions.eq("idCotizante", new Integer(cot.getIdCotizante()))).list();
		for (Iterator itMovsOld = movsOld.iterator(); itMovsOld.hasNext();)
			this.session.delete(itMovsOld.next());
		this.session.flush();
		List listaMovPers = cot.getMovimientoPersonal();
		int i = 1;
		if (listaMovPers != null)
		{
			log.info("movtos recibidos:" + listaMovPers.size() + "::");
			for (Iterator it = listaMovPers.iterator(); it.hasNext();)
			{
				MovtoPersonalVO mp = (MovtoPersonalVO) it.next();
				if (mp.getIdTipoMovReal() >= 0)
				{
					mp.setIdMovimiento(i++);
					log.info("\n\nid movimiento:" + mp.getIdMovimiento() + "::");
					this.session.save(mp);
				} else
					log.info("\n\nmovimiento vacio?:" + mp.getIdMovimiento() + "::");
			}
			cot.setMovimientoPersonal(new ArrayList());
		}
		listaMovPers = cot.getMovimientoPersonalAF();
		if (listaMovPers != null)
		{
			i = 1;
			for (Iterator it = listaMovPers.iterator(); it.hasNext();)
			{
				MvtoPersoAFVO mp = (MvtoPersoAFVO) it.next();
				mp.setIdMovimiento(i++);
				this.session.save(mp);
			}
			cot.setMovimientoPersonalAF(new ArrayList());
		}
	}

	private boolean guardaApvs(CotizanteVO cotizante)
	{
		try
		{
			List apvOld = this.session.createCriteria(ApvVO.class).add(Restrictions.eq("rutEmpresa", new Integer(cotizante.getRutEmpresa()))).add(
					Restrictions.eq("idConvenio", new Integer(cotizante.getIdConvenio()))).add(Restrictions.eq("idCotizante", new Integer(cotizante.getIdCotizante()))).list();
			log.info("borrando APVS:" + apvOld.size() + "::");
			for (Iterator itApvsOld = apvOld.iterator(); itApvsOld.hasNext();)
				this.session.delete(itApvsOld.next());
			this.session.flush();
			List apvList = Utils.limpiaListaApv(cotizante.getApvList());
			for (Iterator it = apvList.iterator(); it.hasNext();)
				this.session.save(it.next());
			this.session.flush();
			return true;
		} catch (Exception ex)
		{
			log.info("ValidacioonDAO: guardaApvs: ERR: No se pudo guardar los apvs::");
			ex.printStackTrace();
			return false;
		}
	}

	public CotizanteVO corrigeEntidadesFalsas(CotizanteVO cotizante)
	{
		if (cotizante.getApellidoMat() == null)
			cotizante.setApellidoMat("");
		if (cotizante.getApellidoPat() == null)
			cotizante.setApellidoPat("");
		if (cotizante.getNombre() == null)
			cotizante.setNombre("");
		if (cotizante.getIdSucursal() == null)
			cotizante.setIdSucursal("");

		if (cotizante.getApellidoMat().length() > 20)
			cotizante.setApellidoMat(cotizante.getApellidoMat().substring(0, 20));
		if (cotizante.getApellidoPat().length() > 20)
			cotizante.setApellidoPat(cotizante.getApellidoPat().substring(0, 20));
		if (cotizante.getIdSucursal().length() > 6)
			cotizante.setIdSucursal(cotizante.getIdSucursal().substring(0, 6));
		if (cotizante.getNombre().length() > 30)
			cotizante.setNombre(cotizante.getNombre().substring(0, 30));

		if (cotizante.getTipoProceso() == 'D')
		{
			cotizante.setIdEntSaludReal(Constants.ID_SALUD_NINGUNA);
			cotizante.setIdTramoReal(Constants.TRAMO_ASIGFAM_FALSO);
			cotizante.setIdEntExCaja(Constants.EXCAJA_FALSO);
			cotizante.setIdRegimenImp(Constants.CODREGIMP_FALSO);
			cotizante.setIdEntPensionReal(Constants.ID_AFP_NINGUNA);
			cotizante.setIdEntidadAFPVReal(Constants.ID_AFP_NINGUNA);
			cotizante.setIdEntSil(Constants.ENTSIL_FALSO);
			cotizante.setIdEntAfcReal(Constants.AFC_FALSO);
			cotizante.setNumCargaSimple(0);
			cotizante.setNumCargaMaterna(0);
			cotizante.setNumCargaInvalidez(0);
			cotizante.setNumDiasTrabajados(0);
			if (((CotizacionDCVO) cotizante.getCotizacion()).getTipoRegimenPrev() < 1 || ((CotizacionDCVO) cotizante.getCotizacion()).getTipoRegimenPrev() > 2)
				//csanchez Se cambia a 0, ya que por defecto, se estaba dejando INP (1)
				((CotizacionDCVO) cotizante.getCotizacion()).setTipoRegimenPrev(0);
			if (((CotizacionDCVO) cotizante.getCotizacion()).getRentaImponible() < 0)
				((CotizacionDCVO) cotizante.getCotizacion()).setRentaImponible(0);
			if (((CotizacionDCVO) cotizante.getCotizacion()).getIndemAporte() < 0)
				((CotizacionDCVO) cotizante.getCotizacion()).setIndemAporte(0);
		} else
		{
			if (cotizante.getIdTramoReal() == -111)
				cotizante.setIdTramoReal(Constants.TRAMO_ASIGFAM_FALSO);
			if (cotizante.getIdEntidadAFPVReal() == -111)
				cotizante.setIdEntidadAFPVReal(Constants.ID_AFP_NINGUNA);
			if (cotizante.getIdEntAfcReal() == -1)
				cotizante.setIdEntAfcReal(Constants.AFC_FALSO);
			if (cotizante.getIdEntExCaja() == -1)
				cotizante.setIdEntExCaja(Constants.EXCAJA_FALSO);
			if (cotizante.getIdRegimenImp() == -1)
				cotizante.setIdRegimenImp(Constants.CODREGIMP_FALSO);
			if (cotizante.getIdEntSil() == -1)
				cotizante.setIdEntSil(Constants.ENTSIL_FALSO);
			if (cotizante.getTipoProceso() == 'G')
			{
				if (((CotizacionGRVO) cotizante.getCotizacion()).getSegCesEmpl() < 0)
					((CotizacionGRVO) cotizante.getCotizacion()).setSegCesEmpl(0);
				if (((CotizacionGRVO) cotizante.getCotizacion()).getGratificacion() < 0)
					((CotizacionGRVO) cotizante.getCotizacion()).setGratificacion(0);
				if (((CotizacionGRVO) cotizante.getCotizacion()).getInpMutual() < 0)
					((CotizacionGRVO) cotizante.getCotizacion()).setInpMutual(0);
				if (((CotizacionGRVO) cotizante.getCotizacion()).getSegCesTrab() < 0)
					((CotizacionGRVO) cotizante.getCotizacion()).setSegCesTrab(0);
				if (((CotizacionGRVO) cotizante.getCotizacion()).getCcafAporte() < 0)
					((CotizacionGRVO) cotizante.getCotizacion()).setCcafAporte(0);
				if (((CotizacionGRVO) cotizante.getCotizacion()).getSaludObligatorio() < 0)
					((CotizacionGRVO) cotizante.getCotizacion()).setSaludObligatorio(0);
				if (((CotizacionGRVO) cotizante.getCotizacion()).getSegCesRemImp() < 0)
					((CotizacionGRVO) cotizante.getCotizacion()).setSegCesRemImp(0);
				if (((CotizacionGRVO) cotizante.getCotizacion()).getPrevisionObligatorio() < 0)
					((CotizacionGRVO) cotizante.getCotizacion()).setPrevisionObligatorio(0);
				if (((CotizacionGRVO) cotizante.getCotizacion()).getTrabPesado() < 0)
					((CotizacionGRVO) cotizante.getCotizacion()).setTrabPesado(0);
				if (((CotizacionGRVO) cotizante.getCotizacion()).getMutualImp() < 0)
					((CotizacionGRVO) cotizante.getCotizacion()).setMutualImp(0);
			}
			if (cotizante.getTipoProceso() == 'A')
			{
				if (((CotizacionRAVO) cotizante.getCotizacion()).getReliquidacion() < 0)
					((CotizacionRAVO) cotizante.getCotizacion()).setReliquidacion(0);
				if (((CotizacionRAVO) cotizante.getCotizacion()).getSegCesEmpl() < 0)
					((CotizacionRAVO) cotizante.getCotizacion()).setSegCesEmpl(0);
				if (((CotizacionRAVO) cotizante.getCotizacion()).getInpMutual() < 0)
					((CotizacionRAVO) cotizante.getCotizacion()).setInpMutual(0);
				if (((CotizacionRAVO) cotizante.getCotizacion()).getSegCesTrab() < 0)
					((CotizacionRAVO) cotizante.getCotizacion()).setSegCesTrab(0);
				if (((CotizacionRAVO) cotizante.getCotizacion()).getCcafAporte() < 0)
					((CotizacionRAVO) cotizante.getCotizacion()).setCcafAporte(0);
				if (((CotizacionRAVO) cotizante.getCotizacion()).getSaludObligatorio() < 0)
					((CotizacionRAVO) cotizante.getCotizacion()).setSaludObligatorio(0);
				if (((CotizacionRAVO) cotizante.getCotizacion()).getSegCesRemImp() < 0)
					((CotizacionRAVO) cotizante.getCotizacion()).setSegCesRemImp(0);
				if (((CotizacionRAVO) cotizante.getCotizacion()).getPrevisionObligatorio() < 0)
					((CotizacionRAVO) cotizante.getCotizacion()).setPrevisionObligatorio(0);
				if (((CotizacionRAVO) cotizante.getCotizacion()).getTrabPesado() < 0)
					((CotizacionRAVO) cotizante.getCotizacion()).setTrabPesado(0);
				if (((CotizacionRAVO) cotizante.getCotizacion()).getMutualImp() < 0)
					((CotizacionRAVO) cotizante.getCotizacion()).setMutualImp(0);

			}
			if (cotizante.getTipoProceso() == 'R')
			{

				if (((CotizacionREVO) cotizante.getCotizacion()).getRentaImp() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setRentaImp(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getSegCesEmpl() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setSegCesEmpl(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getSegCesTrab() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setSegCesTrab(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getCcafAporte() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setCcafAporte(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getSaludObligatorio() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setSaludObligatorio(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getSegCesRemImp() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setSegCesRemImp(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getPrevisionObligatorio() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setPrevisionObligatorio(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getTrabPesado() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setTrabPesado(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getMutualImp() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setMutualImp(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getInpPension() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setInpPension(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getAsigFamReint() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setAsigFamReint(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getApvcAporteEmpl() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setApvcAporteEmpl(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getCcafSeguro() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setCcafSeguro(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getImpuestoUnico() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setImpuestoUnico(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getCcafLeasing() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setCcafLeasing(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getCcafAporte() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setCcafAporte(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getAsigFamiliar() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setAsigFamiliar(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getCcafCredito() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setCcafCredito(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getInpOtrosAportes() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setInpOtrosAportes(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getSaludAdicional() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setSaludAdicional(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getSaludBonificacion() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setSaludBonificacion(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getCcafDental() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setCcafDental(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getAfectoImpuesto() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setAfectoImpuesto(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getPrevisionAdicional() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setPrevisionAdicional(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getApvcAporteTrab() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setApvcAporteTrab(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getCcafOtros() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setCcafOtros(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getInpDesahucio() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setInpDesahucio(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getInpBonificacion() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setInpBonificacion(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getPrevisionTotal() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setPrevisionTotal(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getAsigFamRetro() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setAsigFamRetro(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getRentaTributable() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setRentaTributable(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getSaludPactado() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setSaludPactado(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getPrevisionAhorro() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setPrevisionAhorro(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getRentaImponibleSIS() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setRentaImponibleSIS(0);
				if (((CotizacionREVO) cotizante.getCotizacion()).getPrevisionSIS() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setPrevisionSIS(0);
				//Depósito Convenido
				if (((CotizacionREVO) cotizante.getCotizacion()).getIndemAporte() < 0)
					((CotizacionREVO) cotizante.getCotizacion()).setIndemAporte(0);
				
				if (!cotizante.isAfpVoluntario())
				{
					List lista = ((CotizacionREVO) cotizante.getCotizacion()).getMovimientoPersonalAF();
					for (Iterator it = lista.iterator(); it.hasNext();)
					{
						MovtoPersonalVO mp = (MovtoPersonalVO) it.next();
						if (mp != null)
						{
							if (mp.getInicio() == null)
								mp.setInicio(new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS));
							if (mp.getTermino() == null)
								mp.setTermino(new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS));
						}
					}
				} else
				{
					List lista = ((CotizacionREVO) cotizante.getCotizacion()).getMovimientoPersonalAF();
					for (Iterator it = lista.iterator(); it.hasNext();)
					{
						MvtoPersoAFVO mf = (MvtoPersoAFVO) it.next();
						if (mf != null)
						{
							if (mf.getInicio() == null)
								mf.setInicio(new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS));
							if (mf.getTermino() == null)
								mf.setTermino(new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS));
						}
					}
				}
			}
		}
		return cotizante;
	}

	public HashMap getListaHM(Class tipo, int idMapaCod) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getListaHM:" + tipo.getName());
			List result = this.session.createCriteria(tipo).add(Restrictions.eq("idMapaCod", new Integer(idMapaCod))).list();
			if (result != null && result.size() > 0)
			{
				HashMap resultHM = new HashMap();
				for (Iterator it = result.iterator(); it.hasNext();)
				{
					MapeoVO m = (MapeoVO) it.next();
					resultHM.put(m.getCodigo().trim(), new Integer(m.getId()));
				}
				return resultHM;
			}
			throw new DaoException("ERROR ValidacionDAO:getListaHM: no se encontraron parametros:" + tipo.getName());
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getListaHM:" + tipo.getName(), ex);
		}
	}

	public void guardaComprobante(ComprobanteVO comprobante, List secciones) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:guardaComprobante::");
			this.session.flush();
			if (comprobante.getTotal() < 0)
				comprobante.setTotal(0);
			this.session.save(comprobante);
			this.session.flush();
			// log.info(ToStringBuilder.reflectionToString(comprobante, ToStringStyle.MULTI_LINE_STYLE));
			long cb = comprobante.getIdCodigoBarra();
			log.info("ValidacionDAO:guardaComprobante:CB:" + cb + "::");
			for (Iterator itSecc = secciones.iterator(); itSecc.hasNext();)
			{
				SeccionVO seccion = (SeccionVO) itSecc.next();
				seccion.refreshMUnitarios();
				seccion.setIdCodigoBarra(cb);

				if (seccion.getDetallesSeccion() != null)
					for (Iterator itDet = seccion.getDetallesSeccion().iterator(); itDet.hasNext();)
					{
						DetalleSeccionVO detalle = (DetalleSeccionVO) itDet.next();
						detalle.setIdCodigoBarra(cb);
						// log.info(ToStringBuilder.reflectionToString(detalle, ToStringStyle.MULTI_LINE_STYLE));
					}
				// log.info(ToStringBuilder.reflectionToString(seccion, ToStringStyle.MULTI_LINE_STYLE));
				this.session.save(seccion);
				this.session.flush();
				ConfigPDFVO config = seccion.getConfigPDF();
				config.setIdCodigoBarra(cb);
				config.setIdTipoSeccion(seccion.getIdTipoSeccion());
				this.session.save(config);
				// log.info(ToStringBuilder.reflectionToString(config, ToStringStyle.MULTI_LINE_STYLE));
				this.session.flush();
			}
			log.info("ValidacionDAO:guardaComprobante: FIN:n secciones:");
		} catch (Exception e)
		{
			log.error("ValidacionDAO:guardaComprobante error:", e);
			throw new DaoException("ERROR ValidacionDAO:guardaComprobante:", e);
		}
	}

	public List getTipoSecciones() throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:getTipoSecciones:");
			List result = this.session.createCriteria(TipoSeccionVO.class).list();
			if (result != null && result.size() > 0)
				return result;
			throw new DaoException("ERROR ValidacionDAO:getTipoSecciones: no se encontraron parametros:");
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:getTipoSecciones:", ex);
		}
	}

//	clillo 3-12-14 por Reliquidación
	//public void borraPendiente(char tipoProceso, int idCotizPend, int idConvenio, int rutEmpresa) throws DaoException
	public void borraPendiente(char tipoProceso, int idCotizPend, int idConvenio, int rutEmpresa, int periodo) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:borraPendiente:idEmpresa: " + rutEmpresa + ", idConvenio: " + idConvenio + ":tipoProceso:" + tipoProceso + "::" + idCotizPend + "::");
			CotizacionPendienteVO cotPend = null;
			Class tipoCausa = null;
			if (tipoProceso == 'R')
			{
				cotPend = new CotizacionPendienteREVO(rutEmpresa, idConvenio, idCotizPend);
				tipoCausa = CausaREVO.class;
			} else if (tipoProceso == 'G')
			{
				cotPend = new CotizacionPendienteGRVO(rutEmpresa, idConvenio, idCotizPend);
				tipoCausa = CausaGRVO.class;
			} else if (tipoProceso == 'A')
			{
//				clillo 3-12-14 por Reliquidación
				//cotPend = new CotizacionPendienteRAVO(rutEmpresa, idConvenio, idCotizPend);
				cotPend = new CotizacionPendienteRAVO(rutEmpresa, idConvenio, idCotizPend, periodo);
				tipoCausa = CausaRAVO.class;
			} else
			{
				cotPend = new CotizacionPendienteDCVO(rutEmpresa, idConvenio, idCotizPend);
				tipoCausa = CausaDCVO.class;
			}

			Criteria crit = this.session.createCriteria(tipoCausa).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio)));
			List causas = crit.add(Restrictions.eq("idCotizPendiente", new Integer(idCotizPend))).list();
			if (causas != null)
				for (Iterator itCausa = causas.iterator(); itCausa.hasNext();)
					this.session.delete(itCausa.next());

			cotPend = (CotizacionPendienteVO) this.session.get(cotPend.getClass(), cotPend);
			if (cotPend != null)
				this.session.delete(cotPend);
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:borraPendiente:idEmpresa: " + rutEmpresa + ", idConvenio: " + idConvenio + ":tipoProceso:" + tipoProceso + "::" + idCotizPend + "::", ex);
		}
	}

//	clillo 3-12-14 por Reliquidación
	//public CotizanteVO eliminaTrab(int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador) throws DaoException
	public CotizanteVO eliminaTrab(int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador, int periodo) throws DaoException
	{
		try
		{
			log.debug("ValidacionDAO:eliminaTrab:" + rutEmpresa + "::" + idConvenio + "::" + idTrabajador + "::");
			CotizacionVO cot = null;
			CotizacionVO cotTmp = null;
			List apvsOldTmp = new ArrayList();

			if (tipoProceso == 'R')
			{
				List apvsOld = this.session.createCriteria(ApvVO.class).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio))).add(
						Restrictions.eq("idCotizante", new Integer(idTrabajador))).list();
				apvsOldTmp = apvsOld;
				for (Iterator itApvsOld = apvsOld.iterator(); itApvsOld.hasNext();)
					this.session.delete(itApvsOld.next());
				this.session.flush();
				CotizacionREVO cot2 = (CotizacionREVO) this.session.get(CotizacionREVO.class, new CotizacionREVO(rutEmpresa, idConvenio, idTrabajador));
				if (cot2 != null)
				{
					cotTmp = new CotizacionREVO(cot2);
					List movsOld = this.session.createCriteria(MovtoPersonalVO.class).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(
							Restrictions.eq("idConvenio", new Integer(idConvenio))).add(Restrictions.eq("idCotizante", new Integer(idTrabajador))).list();
					for (Iterator itMovsOld = movsOld.iterator(); itMovsOld.hasNext();)
						this.session.delete(itMovsOld.next());
					movsOld = this.session.createCriteria(MvtoPersoAFVO.class).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
							.add(Restrictions.eq("idCotizante", new Integer(idTrabajador))).list();
					for (Iterator itMovsOld = movsOld.iterator(); itMovsOld.hasNext();)
						this.session.delete(itMovsOld.next());
					this.session.delete(cot2);
					this.session.flush();
				}
			} else
			{
				if (tipoProceso == 'G')
				{
					cot = (CotizacionGRVO) this.session.get(CotizacionGRVO.class, new CotizacionGRVO(rutEmpresa, idConvenio, idTrabajador));
					if (cot != null)
						cotTmp = new CotizacionGRVO((CotizacionGRVO) cot);
				} else if (tipoProceso == 'A')
				{
//					clillo 3-12-14 por Reliquidación
					//cot = (CotizacionRAVO) this.session.get(CotizacionRAVO.class, new CotizacionRAVO(rutEmpresa, idConvenio, idTrabajador));
					cot = (CotizacionRAVO) this.session.get(CotizacionRAVO.class, new CotizacionRAVO(rutEmpresa, idConvenio, idTrabajador, periodo));
					if (cot != null)
						cotTmp = new CotizacionRAVO((CotizacionRAVO) cot);
				} else
				{
					cot = (CotizacionDCVO) this.session.get(CotizacionDCVO.class, new CotizacionDCVO(rutEmpresa, idConvenio, idTrabajador));
					if (cot != null)
						cotTmp = new CotizacionDCVO((CotizacionDCVO) cot);
				}
				if (cot != null)
				{
					this.session.delete(cot);
					this.session.flush();
				}
			}
//			clillo 3-12-14 por Reliquidación
			//CotizanteVO cotizante = eliminaCotizante(rutEmpresa, idConvenio, tipoProceso, idTrabajador);
			CotizanteVO cotizante = eliminaCotizante(rutEmpresa, idConvenio, tipoProceso, idTrabajador, periodo);
			if (cotizante != null)
			{
				cotizante.setCotizacion(cotTmp);
				cotizante.setApvList(apvsOldTmp);
			}
			return cotizante;
		} catch (HibernateException ex)
		{
			log.error("problemas elimina trabajador:", ex);
			throw new DaoException("ERROR ValidacionDAO:eliminaTrab:", ex);
		} catch (Exception ex)
		{
			log.error("problemas elimina trabajador:", ex);
			throw new DaoException("ERROR ValidacionDAO222:eliminaTrab:", ex);
		}
	}

	private void actualizaSinCotizacion(char tipoProceso, int idConvenio, int rutEmpresa)
	{
		String queryHql = "SELECT cotiz.idCotizante, "
				+ (tipoProceso == 'R' ? "1, " : "(SELECT COUNT(cot.idCotizante) FROM " + CotizacionVO.getTipoCotizacion('R').getName() + " cot "
						+ "WHERE cot.rutEmpresa = :empr AND cot.idConvenio = :conv AND cotiz.idCotizante = cot.idCotizante), ")
				+ (tipoProceso == 'G' ? "1, " : "(SELECT COUNT(cot.idCotizante) FROM " + CotizacionVO.getTipoCotizacion('G').getName() + " cot "
						+ "WHERE cot.rutEmpresa = :empr AND cot.idConvenio = :conv AND cotiz.idCotizante = cot.idCotizante), ")
				+ (tipoProceso == 'A' ? "1, " : "(SELECT COUNT(cot.idCotizante) FROM " + CotizacionVO.getTipoCotizacion('A').getName() + " cot "
						+ "WHERE cot.rutEmpresa = :empr AND cot.idConvenio = :conv AND cotiz.idCotizante = cot.idCotizante), ")
				+ (tipoProceso == 'D' ? "1 " : "(SELECT COUNT(cot.idCotizante) FROM " + CotizacionVO.getTipoCotizacion('D').getName() + " cot "
						+ "WHERE cot.rutEmpresa = :empr AND cot.idConvenio = :conv AND cotiz.idCotizante = cot.idCotizante)") + " FROM " + CotizanteVO.class.getName() + " cotiz "
				+ "WHERE cotiz.rutEmpresa = :empr AND cotiz.idConvenio = :conv ";
		Query query = this.session.createQuery(queryHql);
		query.setInteger("empr", rutEmpresa);
		query.setInteger("conv", idConvenio);
		List l = query.list();
		for (Iterator it = l.iterator(); it.hasNext();)
		{
			Object[] tupla = (Object[]) it.next();
			int tieneR = (tipoProceso == 'R' ? 0 : ((Integer)tupla[1]).intValue());
			int tieneG = (tipoProceso == 'G' ? 0 : ((Integer)tupla[2]).intValue());
			int tieneA = (tipoProceso == 'A' ? 0 : ((Integer)tupla[3]).intValue());
			int tieneD = (tipoProceso == 'D' ? 0 : ((Integer)tupla[4]).intValue());
			CotizanteVO c = (CotizanteVO)this.session.get(CotizanteVO.class, new CotizanteVO(rutEmpresa, idConvenio, ((Integer)tupla[0]).intValue()));
			if (c != null)
			{
				if (tieneR + tieneG + tieneA + tieneD == 0)
					this.session.delete(c);
				else
				{
					c.setTieneRemu(tieneR);
					c.setTieneGrat(tieneG);
					c.setTieneReli(tieneA);
					c.setTieneDepo(tieneD);
					this.session.merge(c);
				}
			}
		}
		this.session.flush();
	}

//	clillo 3-12-14 por Reliquidación
	//public CotizanteVO eliminaCotizante(int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador) throws DaoException
	public CotizanteVO eliminaCotizante(int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador, int periodo) throws DaoException
	{
		try
		{	
//			clillo 3-12-14 por Reliquidación
			//CotizanteVO cot = (CotizanteVO) this.session.get(CotizanteVO.class, new CotizanteVO(rutEmpresa, idConvenio, idTrabajador));
			CotizanteVO cot = (CotizanteVO) this.session.get(CotizanteVO.class, new CotizanteVO(rutEmpresa, idConvenio, idTrabajador, periodo));
			if (cot != null)
			{
				CotizanteVO cTmp = new CotizanteVO(cot);
				int suma = cot.getTieneRemu() + cot.getTieneGrat() + cot.getTieneReli() + cot.getTieneDepo(); 
				if (suma == 1)// cotizante no se encontro en otra nomina, por convenio empresa => se borra
				{
					try
					{
						this.session.delete(cot);
						log.info("ValidacionDAO:eliminaCotizante: trabajador borrado");
						this.session.flush();
					} catch (Exception e)
					{
						actualizaSinCotizacion(tipoProceso, idConvenio, rutEmpresa);
					}
				} else
				{
//					clillo 3-12-14 por Reliquidación
					//AvisosCotizanteVO ac = (AvisosCotizanteVO)this.session.get(AvisosCotizanteVO.class, new AvisosCotizanteVO(cot.getRutEmpresa(), cot.getIdConvenio(), cot.getIdCotizante()));
					AvisosCotizanteVO ac = (AvisosCotizanteVO)this.session.get(AvisosCotizanteVO.class, new AvisosCotizanteVO(cot.getRutEmpresa(), cot.getIdConvenio(), cot.getIdCotizante(), periodo));
					if (ac == null)
						cot.setTieneAviso(0);
					else
						cot.setTieneAviso(1);
					if (tipoProceso == 'R')
						cot.setTieneRemu(0);
					else if (tipoProceso == 'G')
						cot.setTieneGrat(0);
					else if (tipoProceso == 'A')
						cot.setTieneReli(0);
					else if (tipoProceso == 'D')
						cot.setTieneDepo(0);
					this.session.merge(cot);
					log.info("ValidacionDAO:eliminaCotizante: trabajador actualizado 'tiene'");
				}
				return cTmp;
			}

			return null;
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:eliminaCotizante:", ex);
		}
	}

	public void eliminarNomina(NominaVO nomina) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:eliminarNomina:" + nomina.getRutEmpresa() + "::" + nomina.getIdConvenio() + "::" + nomina.getIdCodigoBarras() + "::");
			if (this.session.get(nomina.getClass(), nomina) != null)
			{
				eliminarComprobante(nomina.getIdCodigoBarras());
				this.session.delete(nomina);
				this.session.flush();
			}
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ValidacionDAO:eliminarNomina:", ex);
		}
	}

	public void eliminarComprobante(long idCodBarras) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:eliminarComprobante:" + idCodBarras + "::");
			borraSecciones(idCodBarras);
			Query query = this.session.createQuery("delete from ComprobanteVO where idCodigoBarra = ?");
			query.setLong(0, idCodBarras);
			query.executeUpdate();
			this.session.flush();
		} catch (HibernateException ex)
		{	
			throw new DaoException("ERROR ValidacionDAO:eliminarComprobante:", ex);
		}
	}

	public void borraSecciones(long idCodBarras) throws DaoException
	{
		log.info("ValidacionDAO:borraSecciones:" + idCodBarras + "::");
		try
		{
			
			Query query = this.session.createQuery("delete from DetalleAporteCcafVO where idCodigoBarra = ?");
			query.setLong(0, idCodBarras);
			query.executeUpdate();
			this.session.flush();
			query = this.session.createQuery("delete from DetalleCreditoCcafVO where idCodigoBarra = ?");
			query.setLong(0, idCodBarras);
			query.executeUpdate();
			this.session.flush();
			query = this.session.createQuery("delete from DetalleLeasingCcafVO where idCodigoBarra = ?");
			query.setLong(0, idCodBarras);
			query.executeUpdate();
			this.session.flush();
			
			query = this.session.createQuery("delete from ConfigPDFVO where idCodigoBarra = ?");
			query.setLong(0, idCodBarras);
			query.executeUpdate();
			this.session.flush();
			query = this.session.createQuery("delete from DetalleSeccionVO where idCodigoBarra = ?");
			query.setLong(0, idCodBarras);
			query.executeUpdate();
			this.session.flush();
			query = this.session.createQuery("delete from SeccionVO where idCodigoBarra = ?");
			query.setLong(0, idCodBarras);
			query.executeUpdate();

			this.session.flush();
		} catch (Exception e)
		{
			log.error("ValidacionDAO:borraSecciones error:idCodBarras:" + idCodBarras, e);
			throw new DaoException("ERROR ValidacionDAO:borraSecciones:idCodBarras:" + idCodBarras + ":", e);
		}
	}

	public void guardaApelCpto(String apellido) throws DaoException
	{
		try
		{
			log.info("ValidacionDAO:guardaApelCpto:" + apellido + "::");
			ApellidoCompuestoVO apelVO = (ApellidoCompuestoVO) this.session.get(ApellidoCompuestoVO.class, apellido);
			if (apelVO == null)
				this.session.save(new ApellidoCompuestoVO(apellido));
		} catch (Exception e)
		{
			log.error("\n\nERROR guardaApelCpto:", e);
			throw new DaoException("ERROR ValidacionDAO:guardaApelCpto:" + apellido, e);
		}
	}

	public int getNumPendientes(char tipoProceso, int idConvenio, int rutEmpresa) throws DaoException
	{
		try
		{
			Class tipo = null;
			if (tipoProceso == 'R')
				tipo = CotizacionPendienteREVO.class;
			else if (tipoProceso == 'G')
				tipo = CotizacionPendienteGRVO.class;
			else if (tipoProceso == 'A')
				tipo = CotizacionPendienteRAVO.class;
			else if (tipoProceso == 'D')
				tipo = CotizacionPendienteDCVO.class;
			return this.session.createCriteria(tipo).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio))).list().size();
		} catch (Exception e)
		{
			log.error("\n\nERROR getNumPendientes:" + tipoProceso + "::" + idConvenio + "::" + rutEmpresa, e);
			throw new DaoException("ERROR ValidacionDAO:getNumPendientes:" + tipoProceso + "::" + idConvenio + "::" + rutEmpresa + "::", e);
		}
	}

	public HashMap getTiposCausasErr() throws DaoException
	{
		try
		{
			HashMap result = new HashMap();
			List lista = this.session.createCriteria(TipoCausaVO.class).add(Restrictions.eq("error", new Integer(Constants.NIVEL_VAL_ERROR))).list();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				TipoCausaVO tc = (TipoCausaVO) it.next();
				result.put("" + tc.getId(), tc);
			}
			return result;
		} catch (Exception e)
		{
			log.error("\n\nERROR getTiposCausasErr::", e);
			throw new DaoException("ERROR ValidacionDAO:getTiposCausasErr::", e);
		}
	}

	public HashMap getTiposCausasWarn() throws DaoException
	{
		try
		{
			HashMap result = new HashMap();
			List lista = this.session.createCriteria(TipoCausaVO.class).add(Restrictions.eq("error", new Integer(Constants.NIVEL_VAL_AVISO))).list();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				TipoCausaVO tc = (TipoCausaVO) it.next();
				result.put("" + tc.getId(), tc);
			}
			return result;
		} catch (Exception e)
		{
			log.error("\n\nERROR getTiposCausasWarn::", e);
			throw new DaoException("ERROR ValidacionDAO:getTiposCausasWarn::", e);
		}
	}

	public void borraCausaAviso(char tipoProceso, int rutEmpresa, int idConvenio)
	{
		try
		{
			if (tipoProceso == 'R')
			{
				List lista = this.session.createCriteria(CausaAvisoREVO.class).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
						.list();
				for (Iterator it = lista.iterator(); it.hasNext();)
					this.session.delete(it.next());
			} else if (tipoProceso == 'G')
			{
				List lista = this.session.createCriteria(CausaAvisoGRVO.class).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
						.list();
				for (Iterator it = lista.iterator(); it.hasNext();)
					this.session.delete(it.next());
			} else if (tipoProceso == 'A')
			{
				List lista = this.session.createCriteria(CausaAvisoRAVO.class).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
						.list();
				for (Iterator it = lista.iterator(); it.hasNext();)
					this.session.delete(it.next());
			} else if (tipoProceso == 'D')
			{
				List lista = this.session.createCriteria(CausaAvisoDCVO.class).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
						.list();
				for (Iterator it = lista.iterator(); it.hasNext();)
					this.session.delete(it.next());
			}
			this.session.flush();
		} catch (HibernateException ex)
		{
		}
	}

//	clillo 3-12-14 por Reliquidación
	//public void borraCausaAviso(char tipoProceso, int rutEmpresa, int idConvenio, int idCotizante)
	public void borraCausaAviso(char tipoProceso, int rutEmpresa, int idConvenio, int idCotizante, int periodo)
	{
		try
		{
			if (tipoProceso == 'R')
			{
				List lista = this.session.createCriteria(CausaAvisoREVO.class)
										 .add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa)))
										 .add(Restrictions.eq("idCotizPendiente", new Integer(idCotizante)))
										 .add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
										 .list();
				for (Iterator it = lista.iterator(); it.hasNext();)
					this.session.delete(it.next());
			} else if (tipoProceso == 'G')
			{
				List lista = this.session.createCriteria(CausaAvisoGRVO.class)
										 .add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa)))
										 .add(Restrictions.eq("idCotizPendiente", new Integer(idCotizante)))
										 .add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
										 .list();
				for (Iterator it = lista.iterator(); it.hasNext();)
					this.session.delete(it.next());
			} else if (tipoProceso == 'A')
			{
				List lista = this.session.createCriteria(CausaAvisoRAVO.class)
										 .add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa)))
										 .add(Restrictions.eq("idCotizPendiente", new Integer(idCotizante)))
										 .add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
										 //	clillo 3-12-14 por Reliquidación
										 .add(Restrictions.eq("periodo", new Integer(periodo)))
										 .list();
				for (Iterator it = lista.iterator(); it.hasNext();)
					this.session.delete(it.next());
			} else if (tipoProceso == 'D')
			{
				List lista = this.session.createCriteria(CausaAvisoDCVO.class)
										 .add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa)))
										 .add(Restrictions.eq("idCotizPendiente", new Integer(idCotizante)))
										 .add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
										 .list();
				for (Iterator it = lista.iterator(); it.hasNext();)
					this.session.delete(it.next());
			}
			this.session.flush();
		} catch (HibernateException ex) {}
	}

	/**
	 * Elimina los avisos de las nóminas solo relacionados a cargas familiares
	 * 
	 * @param tipoProceso
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param idCotizante
	 */
	public void borraAvisosCargas(char tipoProceso, int rutEmpresa, int idConvenio, int idCotizante)
	{
		try
		{
			if (tipoProceso == 'R') {
				List lista = this.session.createCriteria(CausaAvisoREVO.class)
										 .add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa)))
										 .add(Restrictions.eq("idCotizPendiente", new Integer(idCotizante)))
										 .add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
										 .add(Restrictions.in("idTipoCausa", Constants.AVISOS_CARGASFAM))
										 .list();
				for (Iterator it = lista.iterator(); it.hasNext();)
					this.session.delete(it.next());
			} else if (tipoProceso == 'G') {
				List lista = this.session.createCriteria(CausaAvisoGRVO.class)
										 .add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa)))
										 .add(Restrictions.eq("idCotizPendiente", new Integer(idCotizante)))
										 .add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
										 .add(Restrictions.in("idTipoCausa", Constants.AVISOS_CARGASFAM))
										 .list();
				for (Iterator it = lista.iterator(); it.hasNext();)
					this.session.delete(it.next());
			} else if (tipoProceso == 'A') {
				List lista = this.session.createCriteria(CausaAvisoRAVO.class)
										 .add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa)))
										 .add(Restrictions.eq("idCotizPendiente", new Integer(idCotizante)))
										 .add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
										 .add(Restrictions.in("idTipoCausa", Constants.AVISOS_CARGASFAM))
										 .list();
				for (Iterator it = lista.iterator(); it.hasNext();)
					this.session.delete(it.next());
			}
			this.session.flush();
		} catch (HibernateException ex) {
			log.error("ValidacionDAO: ERR: borraAvisosCargas: ERROR", ex);
		}
	}

	public boolean guardaCausaAviso(HashMap listaCausaAviso, CotizanteVO cotizante)
	{
		try
		{
			this.session.flush();
			guardaCausasAvisos(listaCausaAviso);
			AvisosCotizanteVO ac = (AvisosCotizanteVO) this.session.get(AvisosCotizanteVO.class,
					//new AvisosCotizanteVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), cotizante.getIdCotizante()));
					new AvisosCotizanteVO(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), cotizante.getIdCotizante(), cotizante.getPeriodo()));
			if (ac == null)
				cotizante.setTieneAviso(0);
			else
				cotizante.setTieneAviso(1);
			this.session.merge(cotizante);
			this.session.flush();
			return true;
		} catch (Exception ex)
		{
			log.error("ValidacionDAO: ERR: guardaCausaAviso:No se pudo guardar:", ex);
			return false;
		}
	}

	private void guardaCausasAvisos(HashMap listaCausaAviso)
	{
		for (Iterator it1 = listaCausaAviso.keySet().iterator(); it1.hasNext();)
		{
			List lista = (List) listaCausaAviso.get(it1.next());
			for (Iterator it = lista.iterator(); it.hasNext();)
				this.session.save(it.next());
		}
		this.session.flush();
	}

	public List llenaListaMapeos(int idMapaCod, int rutEmpresa)
	{
		List listaMapeos = new ArrayList();

		// genero
		HashMap tmp = new HashMap();
		List lista = this.session.createCriteria(MapeoGeneroVO.class).add(Restrictions.eq("idMapaCod", new Integer(idMapaCod))).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			MapeoGeneroVO obj = (MapeoGeneroVO) it.next();
			tmp.put(obj.getCodigo().trim(), obj);
		}
		listaMapeos.add(tmp);

		// sucursal
		tmp = new HashMap();
		lista = this.session.createCriteria(SucursalVO.class).add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa))).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			SucursalVO obj = (SucursalVO) it.next();
			log.debug("ASEPULVEDA: " + obj.getIdSucursal().trim());
			tmp.put(obj.getIdSucursal().trim(), obj);
		}
		listaMapeos.add(tmp);

		// salud
		tmp = new HashMap();
		lista = this.session.createCriteria(MapeoSaludVO.class).add(Restrictions.eq("idMapaCod", new Integer(idMapaCod))).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			MapeoSaludVO obj = (MapeoSaludVO) it.next();
			tmp.put(obj.getCodigo().trim(), obj);
		}
		listaMapeos.add(tmp);

		// ccaf
		tmp = new HashMap();
		lista = this.session.createCriteria(EntidadExCajaVO.class).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			EntidadExCajaVO obj = (EntidadExCajaVO) it.next();
			tmp.put(String.valueOf(obj.getId()), "");
		}
		listaMapeos.add(tmp);

		// pension
		tmp = new HashMap();
		lista = this.session.createCriteria(MapeoPensionVO.class).add(Restrictions.eq("idMapaCod", new Integer(idMapaCod))).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			MapeoPensionVO obj = (MapeoPensionVO) it.next();
			tmp.put(obj.getCodigo().trim(), obj);
		}
		listaMapeos.add(tmp);

		// asignación familiar
		tmp = new HashMap();
		lista = this.session.createCriteria(MapeoAsFamVO.class).add(Restrictions.eq("idMapaCod", new Integer(idMapaCod))).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			MapeoAsFamVO obj = (MapeoAsFamVO) it.next();
			tmp.put(obj.getCodigo().trim(), obj);
		}
		listaMapeos.add(tmp);

		// mvto
		tmp = new HashMap();
		lista = this.session.createCriteria(MapeoTipoMovtoVO.class).add(Restrictions.eq("idMapaCod", new Integer(idMapaCod))).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			MapeoTipoMovtoVO obj = (MapeoTipoMovtoVO) it.next();
			tmp.put(obj.getCodigo().trim(), obj);
		}
		listaMapeos.add(tmp);

		// mvto voluntario
		tmp = new HashMap();
		lista = this.session.createCriteria(MapeoTipoMovtoAFVO.class).add(Restrictions.eq("idMapaCod", new Integer(idMapaCod))).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			MapeoTipoMovtoAFVO obj = (MapeoTipoMovtoAFVO) it.next();
			tmp.put(obj.getCodigo().trim(), obj);
		}
		listaMapeos.add(tmp);

		// tipo mvto
		tmp = new HashMap();
		lista = this.session.createCriteria(TipoMovimientoPersonalVO.class).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			TipoMovimientoPersonalVO obj = (TipoMovimientoPersonalVO) it.next();
			tmp.put(String.valueOf(obj.getId()), obj);
		}
		listaMapeos.add(tmp);

		// tipo mvto voluntario
		tmp = new HashMap();
		lista = this.session.createCriteria(TipoMvtoPersoAFVO.class).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			TipoMvtoPersoAFVO obj = (TipoMvtoPersoAFVO) it.next();
			tmp.put(String.valueOf(obj.getId()), obj);
		}
		listaMapeos.add(tmp);

		// sil
		tmp = new HashMap();
		lista = this.session.createCriteria(EntidadSilVO.class).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			EntidadSilVO obj = (EntidadSilVO) it.next();
			tmp.put(String.valueOf(obj.getIdEntPagadora()), obj);
		}
		listaMapeos.add(tmp);

		// apv
		tmp = new HashMap();
		lista = this.session.createCriteria(MapeoAPVVO.class).add(Restrictions.eq("idMapaCod", new Integer(idMapaCod))).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			MapeoAPVVO obj = (MapeoAPVVO) it.next();
			tmp.put(obj.getCodigo().trim(), obj);
		}
		listaMapeos.add(tmp);

		// entidad pension
		tmp = new HashMap();
		lista = this.session.createCriteria(EntidadPensionVO.class).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			EntidadPensionVO obj = (EntidadPensionVO) it.next();
			tmp.put(String.valueOf(obj.getId()), obj);
		}
		listaMapeos.add(tmp);

		// asigFamiliar
		tmp = new HashMap();
		lista = this.session.createCriteria(AsigFamVO.class).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			AsigFamVO obj = (AsigFamVO) it.next();
			tmp.put(String.valueOf(obj.getId()), obj);
		}
		listaMapeos.add(tmp);

		// entidad salud
		tmp = new HashMap();
		lista = this.session.createCriteria(EntidadSaludVO.class).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			EntidadSaludVO obj = (EntidadSaludVO) it.next();
			tmp.put(String.valueOf(obj.getId()), obj);
		}
		listaMapeos.add(tmp);

		// regimen impositivo
		tmp = new HashMap();
		lista = this.session.createCriteria(RegImpositivoVO.class).addOrder(Order.asc("idEntExCaja")).list();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			RegImpositivoVO obj = (RegImpositivoVO) it.next();
			tmp.put(String.valueOf(obj.getIdEntExCaja() + "#" + obj.getIdRegImpositivo()), "");
		}
		listaMapeos.add(tmp);

		return listaMapeos;
	}

//	clillo 3-12-14 por Reliquidación
	//public CotizanteVO getCotizante(char tipoProceso, int rutEmpresa, int idConvenio, String oldRut, int idCotizante)
	public CotizanteVO getCotizante(char tipoProceso, int rutEmpresa, int idConvenio, String oldRut, int idCotizante, int periodo)
	{
		try
		{
			int rutAntiguo = 0;
			if (!oldRut.equals("new"))
				rutAntiguo = new Integer(oldRut).intValue();
			int rutBuscar = (rutAntiguo > 0 ? rutAntiguo : idCotizante);
//			clillo 3-12-14 por Reliquidación
			//CotizanteVO cotTmp = (CotizanteVO) this.session.get(CotizanteVO.class, new CotizanteVO(rutEmpresa, idConvenio, rutBuscar));
			CotizanteVO cotTmp = (CotizanteVO) this.session.get(CotizanteVO.class, new CotizanteVO(rutEmpresa, idConvenio, rutBuscar, periodo));
			if (cotTmp == null)
				return null;
			CotizanteVO cot = new CotizanteVO(cotTmp);

			if (tipoProceso == 'R')
			{
				CotizacionREVO cotizacion = (CotizacionREVO) this.session.get(CotizacionVO.getTipoCotizacion(tipoProceso), new CotizacionREVO(rutEmpresa, idConvenio, rutBuscar));
				if (cotizacion != null)
				{
					cot.setCotizacion(new CotizacionREVO(cotizacion));
					//cot.setCotizacion(cotizacion);
					cot.setApvList(this.session.createCriteria(ApvVO.class).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa)))
																		   .add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
																		   .add(Restrictions.eq("idCotizante", new Integer(rutBuscar)))
																		   .list());
					return cot;
				}
			} else if (tipoProceso == 'G')
			{
				CotizacionGRVO cotizacion = (CotizacionGRVO) this.session.get(CotizacionVO.getTipoCotizacion(tipoProceso), new CotizacionGRVO(rutEmpresa, idConvenio, rutBuscar));
				if (cotizacion != null)
				{
					cot.setCotizacion(new CotizacionGRVO(cotizacion));
					return cot;
				}
			} else if (tipoProceso == 'A')
			{
//				clillo 3-12-14 por Reliquidación
				//CotizacionRAVO cotizacion = (CotizacionRAVO) this.session.get(CotizacionVO.getTipoCotizacion(tipoProceso), new CotizacionRAVO(rutEmpresa, idConvenio, rutBuscar));
				CotizacionRAVO cotizacion = (CotizacionRAVO) this.session.get(CotizacionVO.getTipoCotizacion(tipoProceso), new CotizacionRAVO(rutEmpresa, idConvenio, rutBuscar, periodo));
				if (cotizacion != null)
				{
					cot.setCotizacion(new CotizacionRAVO(cotizacion));
					return cot;
				}
			} else
			{
				CotizacionDCVO cotizacion = (CotizacionDCVO) this.session.get(CotizacionVO.getTipoCotizacion(tipoProceso), new CotizacionDCVO(rutEmpresa, idConvenio, rutBuscar));
				if (cotizacion != null)
				{
					cot.setCotizacion(new CotizacionDCVO(cotizacion));
					return cot;
				}
			}

		} catch (Exception e)
		{
			log.error("ValidacionDAO:getCotizante error:" + e.getMessage() + ":tipoProceso:" + tipoProceso + ":rutEmpresa:" + rutEmpresa + ":idConvenio:" + idConvenio + ":Rut:" + idCotizante + "::", e);
		}
		return null;
	}
	
	/**
	 * Determina la cantidad de conceptos que tiene un mapeos de archivos
	 * 
	 * @param idMapa
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */	
	public int getCantidadDeConceptos(int idMapa, char tipoProceso) throws DaoException {
		int contador = 0;

		log.info("ValidacionDAO:getCantidadDeConceptos: idMapa: " + "tipoProceso: " + tipoProceso + "::");
		contador = ((Integer) this.session.createCriteria(MapeoConceptoVO.class)
										  .add(Restrictions.eq("idMapa", new Integer(idMapa)))
										  .add(Restrictions.eq("tipoProceso", String.valueOf(tipoProceso)))
										  .setProjection(Projections.max("posicion"))
										  .list()
										  .get(0)).intValue();
		return contador;
	}
	
	/**
	 * Obtiene un Mapeo de Concepto particular de acuerdo a su llave
	 * 
	 * @param tipoNomina
	 * @param idMapaCod
	 * @param idConcepto
	 * @return
	 * @throws DaoException
	 */
	public MapeoConceptoVO getMapeoConcepto(String tipoNomina, int idMapaNom, int idConcepto) throws DaoException{
		try {
			log.info("ValidacionDAO:getMapeoConcepto::");
			MapeoConceptoVO  mapeoConceptoVO =(MapeoConceptoVO) this.session.createCriteria(MapeoConceptoVO.class)
												 .add(Restrictions.eq("tipoProceso", tipoNomina))
												 .add(Restrictions.eq("idConcepto", new Integer(idConcepto)))
												 .add(Restrictions.eq("idMapa", new Integer(idMapaNom)))
												 .list()
												 .get(0);
			
			return mapeoConceptoVO;
		} catch(Exception ex) {
			log.error("Error en ValidacionDAO:getMapeoConcepto: " + ex);
			throw new DaoException("Problemas en ValidacionDAO.getMapeoConcepto", ex);
		}
	}

	/**
	 * Devuelve la Entidad CCAF dado su id
	 *
	 * @param idCCAF
	 * @return
	 * @throws DaoException
	 */
	public EntidadCCAFVO getEntidadCCAF(int idCCAF) throws DaoException {
		try {
			return (EntidadCCAFVO) this.session.createCriteria(EntidadCCAFVO.class)
											   .add(Restrictions.eq("id", new Integer(idCCAF)))
											   .uniqueResult();
		} catch (Exception e) {
			log.error("ValidacionDAO:getEntidadCCAF error:" + e.getMessage() + "::", e);
			return null;
		}
	}


	public void guardaEventoCargaValidacion(EventoCargaValidacionVO evento) {
		try {
			this.session.save(evento);
		} catch (Exception e) {
			log.error("ValidacionDAO:guardaEventoCargaValidacion error:" + e.getMessage() + "::", e);
		}
	}

	/**
	 * Obtiene el máximo ID_CAUSA de la tabla CAUSAAVISOCXP (donde X es el tipo de nómina)
	 * 
	 * @param tipoProceso
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param idCotizante
	 * 
	 * @return idMaxCausa
	 */
	public int getMaxIdCausaAviso(char tipoProceso, int rutEmpresa, int idConvenio, int idCotizante) {
		int idMaxCausa = 0;
		Integer id = null;
		try	{
			if (tipoProceso == 'R') {
				
				id = (Integer) this.session.createCriteria(CausaAvisoREVO.class)
										   .setProjection(Projections.max("idCausa"))
										   .add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa)))
										   .add(Restrictions.eq("idCotizPendiente", new Integer(idCotizante)))
										   .add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
										   .uniqueResult();
				if (id != null)
					idMaxCausa = id.intValue(); 
			} else if (tipoProceso == 'G') {
				id = (Integer) this.session.createCriteria(CausaAvisoGRVO.class)
										   .setProjection(Projections.max("idCausa"))
										   .add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa)))
										   .add(Restrictions.eq("idCotizPendiente", new Integer(idCotizante)))
										   .add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
										   .uniqueResult();
				if (id != null)
					idMaxCausa = id.intValue(); 
			} else if (tipoProceso == 'A') {
				id = (Integer) this.session.createCriteria(CausaAvisoRAVO.class)
										   .setProjection(Projections.max("idCausa"))
										   .add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa)))
										   .add(Restrictions.eq("idCotizPendiente", new Integer(idCotizante)))
										   .add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
										   .uniqueResult();
				if (id != null)
					idMaxCausa = id.intValue(); 
			}
			return idMaxCausa;
		} catch (HibernateException ex) {
			return idMaxCausa;	
		}
	}
	/**
	 * retorna la persona o null si no existe ya sea instanciada ya sea persistida.
	 * 
	 * @param idPersona
	 * @return
	 * @throws DaoException
	 */
	public PersonaVO getPersona(int idPersona) throws DaoException
	{
		try
		{
			return (PersonaVO) this.session.get(PersonaVO.class, new Integer(idPersona));
		} catch (Exception ex)
		{
			log.error("Ha ocurrido la siguiente excepcion en ValidacionDAO.getPersona: " + ex);
			throw new DaoException("Error al recuperar la persona rut: " + idPersona, ex);
		}
	}
}