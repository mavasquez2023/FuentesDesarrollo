package cl.araucana.cp.distribuidor.hibernate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MovtoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

public class BulkNominaDAO
{
	private static Logger log = Logger.getLogger(BulkNominaDAO.class);

	private StatelessSession session;
	private NominaVO nomina;
	private String sufijo;

	public BulkNominaDAO(StatelessSession session, NominaVO nomina) throws DaoException
	{
		if (session == null)
			throw new DaoException("No se obtuvo session hibernate");
		this.session = session;
		this.nomina = nomina;
		this.sufijo = ":rut/conv/tipo:[" + nomina.getRutEmpresa() + '/' + nomina.getIdConvenio() + '/' + nomina.getTipoProceso() + "]";
	}

	public StatelessSession getSession()
	{
		return this.session;
	}

	public boolean guardaCotizantes(HashMap cotizantes)
	{
		try
		{	
			int i=0;
			log.info("BulkNominaDAO:guardaCotizantes" + this.sufijo);
			Map nominaPreexistente = cargaListaNomina(this.nomina.getRutEmpresa(), this.nomina.getIdConvenio());
			for (Iterator itC = cotizantes.values().iterator(); itC.hasNext();)
			{
				CotizanteVO cotizante = (CotizanteVO) itC.next();
				cotizante.setTipoProceso(this.nomina.getTipoProceso());
				corrigeEntidadesFalsas(cotizante);
				CotizanteVO cotOld = (CotizanteVO) nominaPreexistente.get(cotizante.getPeriodo() + "" + cotizante.getIdCotizante());
				if (cotOld == null)
					this.session.insert(cotizante);
				else
				{
					if (cotizante.getTipoProceso() == 'D')
					{// recuerda valores ya registrados en cotizante, que
						// tipo D no usa
						// (si hay un registro de otro tipo al mismo tiempo,
						// perderia estos valores, ya que D no los usa)
						cotizante.merge(cotOld);
					}
					cotizante.updateTiene(cotizante.getTipoProceso(), cotOld);
					this.session.update(cotizante);
				}
			}
			return true;
		} catch (Exception e)
		{
			log.error("BulkNominaDAO:guardaCotizaciones error" + this.sufijo, e);
			return false;
		}
	}

	private Map cargaListaNomina(int rutEmpresa, int idConvenio)
	{
		List cotizantes = this.session.createCriteria(CotizanteVO.class).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(
				Restrictions.eq("idConvenio", new Integer(idConvenio))).list();
		Map mapaCotizantes = new HashMap();
		for (Iterator it = cotizantes.iterator(); it.hasNext();)
		{
			CotizanteVO cot = (CotizanteVO) it.next();
			mapaCotizantes.put(cot.getPeriodo() + String.valueOf(cot.getIdCotizante()), cot);
		}
		log.info("Cotizantes preexistentes:" + cotizantes.size() + this.sufijo);
		return mapaCotizantes;
	}

	public void corrigeEntidadesFalsas(CotizanteVO cotizante)
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
	}

	public boolean guardaCotizaciones(Collection cotizantesLista)
	{
		log.info("Guarda cotizacion" + this.sufijo);
		try
		{
			for (Iterator it = cotizantesLista.iterator(); it.hasNext();)
			{
				CotizanteVO cotizante = (CotizanteVO) it.next();
				CotizacionVO cot = (CotizacionVO) cotizante.getCotizacion();
				this.session.insert(cot);
				if (cot instanceof CotizacionREVO)
				{
					CotizacionREVO cotRe = (CotizacionREVO) cot;
					for (Iterator iter = cotRe.getMovimientoPersonal().iterator(); iter.hasNext();)
						this.session.insert(iter.next());
					for (Iterator iter = cotRe.getMovimientoPersonalAF().iterator(); iter.hasNext();)
						this.session.insert(iter.next());
					for (Iterator iter = Utils.limpiaListaApv(cotizante.getApvList()).iterator(); iter.hasNext();)
					{
						ApvVO apv = (ApvVO) iter.next();
						//System.out.println("Id Cotizante: " + apv.getIdCotizante() + ", Regimen: " + apv.getRegimen() + ", monto=" + apv.getAhorro());
						if ((apv.getIdEntidadApv() != -1 && apv.getIdEntidadApv() != 0 && apv.getIdEntidadApv() != -111) || apv.getAhorro() != 0)
							this.session.insert(apv);
					}
				}
			}
			return true;
		} catch (Throwable e)
		{
			log.error("Problemas guardando cotizacion" + this.sufijo, e);
			System.out.println("Error:" + e);
			return false;
		}
	}

	/**
	 * Los objetos contenidos en el mapa son pasados directamente a la sesion de
	 * hibernate asociada a este Dao
	 * 
	 * @param cotizantesPendientes
	 *            mapa de valores a persistir.
	 * @return false apenas encuentra cualquier problema
	 */
	public boolean guardaPendientes(Map cotizantesPendientes)
	{
		CotizacionPendienteVO cotizPend = null;
		for (Iterator iter = cotizantesPendientes.values().iterator(); iter.hasNext();)
			try
			{
				cotizPend = (CotizacionPendienteVO) iter.next();
				if (cotizPend.getDetalle().length() > 1024)
					cotizPend.setDetalle(cotizPend.getDetalle().substring(0, 1024));
				this.session.insert(cotizPend);
				for (Iterator iterator = cotizPend.getCausas().iterator(); iterator.hasNext();)
					this.session.insert(iterator.next());
			} catch (Exception e)
			{
				log.error("BulkNominaDAO: ERR: Problemas guardando pendientes" + this.sufijo, e);
				return false;
			}
		return true;
	}

	public boolean guardaCausaAviso(HashMap listaCausaAviso)
	{
		try
		{
			for (Iterator it1 = listaCausaAviso.keySet().iterator(); it1.hasNext();)
			{
				List lista = (List) listaCausaAviso.get(it1.next());
				for (Iterator it = lista.iterator(); it.hasNext();)
					this.session.insert(it.next());
			}
			return true;
		} catch (Exception ex)
		{
			log.error("BulkNominaDAO: ERR: guardaCausaAviso:No se pudo guardar" + this.sufijo, ex);
			return false;
		}
	}

	public boolean confirmaCotizantes()
	{
		try
		{
			int idConvenio = this.nomina.getIdConvenio();
			int rutEmpresa = this.nomina.getRutEmpresa();
						//TODO revisar!
			String queryHql = "UPDATE CotizanteVO " +
					             "SET " + CotizanteVO.getTipoTiene(this.nomina.getTipoProceso()) + " = 0" +
					               ", tieneAviso = 0 " +
					           "WHERE rutEmpresa = :empr " +
					             "AND idConvenio = :conv";
			log.info("cotizantes marcados sin cotizaciones para " + this.sufijo + ":"+ this.session.createQuery(queryHql).setInteger("empr", rutEmpresa).setInteger("conv", idConvenio).executeUpdate());
			// PARCHE!!!! no hubo caso con HQL
			String querySQL = "UPDATE Cotizante " +
					             "SET " + CotizanteVO.getTipoTieneReal(this.nomina.getTipoProceso()) + " = 1 " +
					           "WHERE id_Empresa  = ? " +
					             "AND id_Convenio = ? ";
			if(this.nomina.getTipoProceso()=='A'){
				querySQL+= "AND con_Remu =0 AND con_Grat=0 AND con_Depo=0 ";
			}else{
				querySQL+= "AND con_Reli=0 ";
			}
					             
				querySQL+=     "AND id_cotizante in (select cotizacion.id_Cotizante " +
					                "from "	+ this.nomina.getTipoCotizacionReal() + " as cotizacion " +
					                "where cotizacion.id_Empresa  = ? " +
					                "and cotizacion.id_Convenio = ?) ";
			PreparedStatement ps = this.session.connection().prepareStatement(querySQL);
			ps.setInt(1, rutEmpresa);
			ps.setInt(2, idConvenio);
			ps.setInt(3, rutEmpresa);
			ps.setInt(4, idConvenio);
			log.info("cotizantes marcados con cotizaciones para " + this.sufijo + ":" + querySQL + ps.executeUpdate());
						//TODO revisar!
			// estas queries estan en cotizante.hbm.xml
			log.info("cotizantes borrados para " + this.sufijo + ":" +this.session.getNamedQuery("borrasincotizaciones").setInteger("empr", rutEmpresa).setInteger("conv", idConvenio).executeUpdate());
			log.info("avisos en cotizantes para " + this.sufijo + ":" +this.session.getNamedQuery("asignaAvisos").setInteger("empr", rutEmpresa).setInteger("conv", idConvenio).executeUpdate());
			return true;
		} catch (Throwable e)
		{
			log.error("BulkNominaDAO: ERR : Problemas confirmando cotizaciones" + this.sufijo, e);
			return false;
		}
	}

	public long borraComprobante(long idCodBarras) throws DaoException
	{
		long folio = 0;
		try
		{
			if (idCodBarras > 0)
			{
				log.info("BulkNominaDAO::borraComprobante: codBarras:" + idCodBarras + ":, busca cmp para borrar.");
				Long cb = new Long(idCodBarras);
				ComprobanteVO cmp = (ComprobanteVO) this.session.get(ComprobanteVO.class, cb);
				if (cmp != null)
				{
					folio = cmp.getFolioTesoreria();
					Query query = this.session.createQuery("delete from ConfigPDFVO where idCodigoBarra = ?");
					query.setLong(0, idCodBarras);
					query.executeUpdate();
					query = this.session.createQuery("delete from DetalleSeccionVO where idCodigoBarra = ?");
					query.setLong(0, idCodBarras);
					query.executeUpdate();
					query = this.session.createQuery("delete from SeccionVO where idCodigoBarra = ?");
					query.setLong(0, idCodBarras);
					query.executeUpdate();

					log.info("BulkNominaDAO::borraComprobante: codBarras:" + cb + ": cmp encontrado.");
					this.session.delete(cmp);
					log.info("BulkNominaDAO::borraComprobante: codBarras:" + cb + ": cmp borrado.");
				} else
					log.info("BulkNominaDAO::borraComprobante: codBarras:" + cb + ": cmp NO encontrado.");
			} else
				log.info("BulkNominaDAO::borraComprobante: codBarras = 0, NO busca cmp para borrar.");
		} catch (Exception ex)
		{
			log.error("BulkNominaDAO: ERR :borraComprobante", ex);
			throw new DaoException("BulkNominaDAO:borraComprobante:cod barras:" + this.nomina.getIdCodigoBarras() + this.sufijo, ex);
		}
		return folio;
	}

	public void borraPendientes() throws DaoException
	{
		log.info("BulkNominaDAO:borraPendientes" + this.sufijo);
		try
		{
			// las restricciones en BD borran en cascada
			borraDataConvenio(this.nomina.getTipoCotizacionPendiente());
			//borraDataCausaAviso(this.nomina.getTipoCausaAviso());
		} catch (Exception ex)
		{
			log.error("BulkNominaDAO: ERR :borraPendientes" + this.sufijo, ex);
			throw new DaoException("borraPendientes " + this.sufijo, ex);
		}
	}

	public void borraCotizaciones() throws DaoException
	{
		// las restricciones en BD borran en cascada salvo los APV que dependen del cotizante.
		//borraApvs();
		long ini = System.currentTimeMillis();
		log.info("BulkNominaDAO:borraCotizaciones" + this.sufijo);
		Throwable exception = null;
		try
		{
			borraDataConvenio(this.nomina.getTipoCotizacion());
		} catch (Exception ex)
		{
			exception = ex;
			log.error("Error en CotizanteDAO:borraCotizaciones", ex);
		} finally
		{
			long fin = System.currentTimeMillis();
			log.info("BulkNominaDAO:fin borraCotizaciones" + this.sufijo + "diff:" + (fin - ini) + "::");
			ini = fin;
		}
		log.info("BulkNominaDAO:borraCotizantes sin otras cotizaciones:");
		try
		{
			eliminaSinCotizacion();
		} catch (Throwable ex)
		{
			throw new DaoException("BulkNominaDAO:borraCotizaciones" + this.sufijo, ex);
		} finally
		{
			log.info("BulkNominaDAO:fin borraCotizantes sin otras cotizaciones" + this.sufijo + "diff:" + (System.currentTimeMillis() - ini) + "::");
		}
		if (exception != null)
			throw new DaoException("BulkNominaDAO:borraCotizaciones" + this.sufijo, exception);
	}

	public void borraApvs() throws DaoException
	{
		if (!(this.nomina instanceof NominaREVO))
			return;
		log.info("BulkNominaDAO:borraApvs " + this.sufijo);
		try
		{
			borraDataConvenio(ApvVO.class);
		} catch (Exception ex)
		{
			log.error("BulkNominaDAO: ERR : borraApvs", ex);
			throw new DaoException("borraApvs " + this.sufijo, ex);
		}
	}

	private void borraDataConvenio(Class tipoAviso)
	{
		Query query = this.session.createQuery("delete from " + tipoAviso.getName() + " where rutEmpresa = ? and idConvenio = ?");
		query.setInteger(0, this.nomina.getRutEmpresa());
		query.setInteger(1, this.nomina.getIdConvenio());
		log.debug(tipoAviso + this.sufijo +" eliminadas:" + query.executeUpdate());
	}
	
	private void borraDataCausaAviso(Class tipoCausa)
	{
		Query query = this.session.createQuery("delete from " + tipoCausa.getName() + " where rutEmpresa = ? and idConvenio = ?");
		query.setInteger(0, this.nomina.getRutEmpresa());
		query.setInteger(1, this.nomina.getIdConvenio());
		log.debug(tipoCausa + this.sufijo +" eliminadas:" + query.executeUpdate());
	}

	private void eliminaSinCotizacion()
	{
		// Actualiza cotizante			
		//TODO revisar!
		String queryHql = "UPDATE CotizanteVO SET " + CotizanteVO.getTipoTiene(this.nomina.getTipoProceso())
				+ " = 0 WHERE rutEmpresa = :empr AND idConvenio = :conv ";
		Query query = this.session.createQuery(queryHql).setInteger("empr", this.nomina.getRutEmpresa()).setInteger("conv", this.nomina.getIdConvenio());
		log.debug("eliminaSinCotizacion: update:" + this.sufijo + ":" + query.executeUpdate() + " = " + queryHql + "::");
					//TODO revisar!
		queryHql = "DELETE FROM CotizanteVO WHERE rutEmpresa = :empr AND idConvenio = :conv AND tieneRemu = 0 AND tieneGrat = 0 AND tieneReli = 0 AND tieneDepo = 0";
		query = this.session.createQuery(queryHql).setInteger("empr", this.nomina.getRutEmpresa()).setInteger("conv", this.nomina.getIdConvenio());
		log.debug("eliminaSinCotizacion:delete:" + this.sufijo + ":" + query.executeUpdate() + " = " + queryHql + "::");
	}

	public void creaBloqueoBD(Connection conn) throws SQLException
	{
		executeUpdateNomina(conn, "INSERT INTO DUMMY(EMPRESA, CONVENIO, TIPO) VALUES(?, ?, 'X')");
	}

	public void borraBloqueoBD(Connection conn) throws SQLException
	{
		executeUpdateNomina(conn, "DELETE FROM DUMMY WHERE EMPRESA=? and CONVENIO = ? and TIPO = 'X'");
	}

	private void executeUpdateNomina(Connection conn, String query) throws SQLException
	{
		PreparedStatement ps = null;
		try
		{
			ps = conn.prepareStatement(query);
			int params = ps.getParameterMetaData().getParameterCount();
			if (params>0)
				ps.setInt(1, this.nomina.getRutEmpresa());
			if (params>1)
				ps.setInt(2, this.nomina.getIdConvenio());
			if (params>2)
				ps.setString(3, "" + this.nomina.getTipoProceso());
			log.info(ps.executeUpdate() + " al ejecutar: " + query);
		} finally 
		{
			if (ps!=null)
				ps.close();
		}
	}

}
