package cl.araucana.cp.distribuidor.business.mgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.business.secciones.Seccion;
import cl.araucana.cp.distribuidor.hibernate.beans.ConfigPDFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.dao.EntidadesDAO;
import cl.araucana.cp.distribuidor.hibernate.dao.ParametrosDAO;
import cl.araucana.cp.distribuidor.hibernate.dao.ValidacionDAO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.hibernate.exceptions.SeccionException;

public class SeccionMgr
{
	private static Logger logger = Logger.getLogger(SeccionMgr.class);
	private char tipoProceso;
	private ParametrosDAO parametrosDao;
	private ValidacionDAO validacionDao;
	private EntidadesDAO entidadesDao;
	private HashMap parametros;
	private HashMap propSecciones;
	private HashMap propM;
	private HashMap entidadesCCAF;
	private HashMap secciones = new HashMap();
	private List tiposSecciones;
	private HashMap seccionadores = new HashMap();
	private int numM;
	private long total;
	private long rentaImponible;

	public SeccionMgr(Session session, char tipoProceso, HashMap parametros)
	{
		this.tipoProceso = tipoProceso;
		this.parametros = parametros;
		this.parametrosDao = new ParametrosDAO(session);
		this.validacionDao = new ValidacionDAO(session);
		this.entidadesDao = new EntidadesDAO(session);
	}

	public void cargaProperties() throws DaoException
	{
		this.propSecciones = this.parametrosDao.getPropertiesMapeo(Constants.PROP_MAPEO_SECC_TP);
		this.propM = this.parametrosDao.getPropertiesMapeo(Constants.PROP_MAPEO_MX_SECC);
		this.tiposSecciones = this.validacionDao.getTipoSecciones();
		this.entidadesCCAF = this.entidadesDao.cargaCCAF();
	}

	public List generaSecciones(ConvenioVO convenio, HashMap cotizantes) throws SeccionException
	{
		List seccionesReturn = new ArrayList();
		this.numM = new Integer((String) this.parametros.get("" + Constants.PARAM_NUM_M)).intValue();
		logger.info("seccionando:" + this.tipoProceso + "::");
		inicializaSecciones(true, convenio, null);
		logger.debug("ncotizantes:" + cotizantes.size() + "::");

		logger.debug("secciones:" + this.secciones.size() + "::");
		long sumaRenta=0;
		for (Iterator it = cotizantes.keySet().iterator(); it.hasNext();)
		{
			String rutCotizante = (String) it.next();
			CotizanteVO cotizante = (CotizanteVO) cotizantes.get(rutCotizante);
			
			int rentaCotizante= cotizante.getCotizacion().getRenta();
			//logger.info("renta Cotizante:" + rentaCotizante);
			if (cotizante.getIdEntPensionReal() == Constants.ID_INP){
				cotizante.setTipoPrevision(Seccion.IS_INP);
				rentaCotizante= cotizante.getCotizacion().getRentaImpInp();
			}else if (cotizante.getIdEntPensionReal() == Constants.ID_AFP_NINGUNA){
				cotizante.setTipoPrevision(Seccion.IS_AFP_NINGUNA);
			}else{
				// AFP
				cotizante.setTipoPrevision(Seccion.IS_AFP);
			}
			sumaRenta+= rentaCotizante;
			if (cotizante.getIdEntSaludReal() != Constants.ID_FONASA && cotizante.getIdEntSaludReal() != Constants.ID_SALUD_NINGUNA)
				cotizante.setIsapre(true);

			for (Iterator itSecc = this.secciones.keySet().iterator(); itSecc.hasNext();)
			{
				String clave = (String) itSecc.next();
				SeccionVO seccion = (SeccionVO) this.secciones.get(clave);
				Seccion seccionador = (Seccion) this.seccionadores.get(clave);

				// si es cotizante voluntario solo se genera detalle para la seccion AFP (1)
				if ((cotizante.isAfpVoluntario() && seccion.getIdTipoSeccion() == Constants.TIPO_SECCION_AFP) || !cotizante.isAfpVoluntario())
					seccionador.generar(this.tipoProceso, cotizante, seccion);
			}
		}
		logger.info("Total Renta Cotizantes:" + sumaRenta);
		setRentaImponible(sumaRenta);
		this.total = 0;
		for (Iterator it = this.secciones.keySet().iterator(); it.hasNext();)
		{
			String valor = (String) it.next();
			SeccionVO seccion = (SeccionVO) this.secciones.get(valor);
			Seccion seccionador = (Seccion) this.seccionadores.get(valor);
			//csanchez. El método calcula subtotales sólo para el tipo de sección 3
			//ya que para que los tipos 22 y 42, según lo informado en TIPO_SECCION, estos no existen.
			if (valor.equals(Constants.TIPO_SEC_INP) && seccion.getIdTipoSeccion() == Constants.ID_TIPO_SECCION_INP) {
				seccionador.calculaTotalINP(seccion, seccion.getIdTipoSeccion());
			}
			
			if(valor.equals(Constants.TIPO_SEC_CAJA)){
				seccion.sumaTotalesCaja();
			}else{
				seccion.sumaTotales();	
			}
			
			int numTrab = seccion.getNumTrabajadores();
			if (numTrab > 0)
			{
				this.total += ((Seccion) this.seccionadores.get(valor)).finishProcess(this.tipoProceso, seccion);
				logger.info("agregando seccion:" + seccion.getIdTipoSeccion() + ":numTrab:" + numTrab + "::");
				seccionesReturn.add(seccion);
			} else
				logger.info("sacando seccion:" + seccion.getIdTipoSeccion() + ":numTrab:" + convenio.getMutualNumeroAdherente() + "::");
		}
		logger.debug("size:" + seccionesReturn.size() + "::");
		return seccionesReturn;
	}

	public void inicializaSecciones(boolean generarSecciones, ConvenioVO convenio, HashMap hashSecciones) throws SeccionException
	{
		try
		{
			SeccionVO seccion;
			for (Iterator it = this.propSecciones.keySet().iterator(); it.hasNext();)
			{
				String claveTmp = ((String) it.next()).trim();// "RAFP"
				if (claveTmp.startsWith("" + this.tipoProceso))
				{// inicializa un objeto seccion por cada una de las posibles de acuerdo al tipo de proceso, este objeto contendra los valores reales.
					String clave = claveTmp.substring(1);
					String idTipoSeccion = (String) this.propSecciones.get(claveTmp);// idTipoSeccion: "1"
					
						//if(clave.equals(Constants.DETALLE_SECCION_CAJA) ){//|| clave.equals(Constants.DETALLE_SECCION_MUTUAL)){
							/*
							 * GMALLEA 26-01-2012
							 * Si el detalle secion es caja o mutual, se agregan el objeto vacio para que despues se generen desde el convenio 
							 * y no de la tabla detalle seccion
							 */
							/*seccion = new SeccionVO(0, Constants.EST_SECCION_PAGO, new Integer(idTipoSeccion).intValue());
							seccion.setConfigPDF(new ConfigPDFVO(this.numM));
							this.secciones.put(clave, seccion);
							
							TipoSeccionVO ts = getTipoSeccion(new Integer(idTipoSeccion).intValue());
							Class seccionador = Class.forName(ts.getClaseSeccionador().trim());
							Class[] argTypes = { Integer.class, ConvenioVO.class, HashMap.class, HashMap.class, List.class, HashMap.class };
							Object[] argValues = { new Integer(this.numM), convenio, this.propM, this.parametros, this.tiposSecciones, this.entidadesCCAF };
							Seccion seccionadorIns = (Seccion) seccionador.getConstructor(argTypes).newInstance(argValues);
							this.seccionadores.put(clave, seccionadorIns);
							
						}else{*/
						
							/*
							 * GMALLEA 26-01-2012
							 * Camino normal si no es caja o mutual. 
							 */
								if (generarSecciones || !hashSecciones.containsKey(idTipoSeccion)){
									int finPagoCaja=   Integer.parseInt(((String) this.parametros.get("" + Constants.PARAM_FIN_PAGO_CAJA)).substring(0, 2));						
									seccion = new SeccionVO(0, Constants.EST_SECCION_PAGO, new Integer(idTipoSeccion).intValue(), finPagoCaja);
									seccion.setConfigPDF(new ConfigPDFVO(this.numM));
									this.secciones.put(clave, seccion);
								} else
									this.secciones.put(clave, hashSecciones.get(idTipoSeccion));
						// identifica la clase que debe seccionar y asignar estos valores
						TipoSeccionVO ts = getTipoSeccion(new Integer(idTipoSeccion).intValue());
						Class seccionador = Class.forName(ts.getClaseSeccionador().trim());
						Class[] argTypes = { Integer.class, ConvenioVO.class, HashMap.class, HashMap.class, List.class, HashMap.class };
						Object[] argValues = { new Integer(this.numM), convenio, this.propM, this.parametros, this.tiposSecciones, this.entidadesCCAF };
						Seccion seccionadorIns = (Seccion) seccionador.getConstructor(argTypes).newInstance(argValues);
						this.seccionadores.put(clave, seccionadorIns);
				//}
			}
		}
		} catch (Exception e)
		{
			logger.error("problemas al inicializar las secciones", e);
			throw new SeccionException("problemas al inicializar las secciones, revisar configuracion");
		}
	}

	public TipoSeccionVO getTipoSeccion(int id) throws SeccionException
	{
		for (Iterator it = this.tiposSecciones.iterator(); it.hasNext();)
		{
			TipoSeccionVO ts = (TipoSeccionVO) it.next();
			if (id == ts.getId())
				return ts;
		}
		throw new SeccionException("tipo seccion no encontrado, revisar configuracion properties_mapeo id:" + id + "::");
	}

	public long getTotal()
	{
		return this.total;
	}

	public void setTotal(long total)
	{
		this.total = total;
	}

	public List actualizaSecciones(ConvenioVO convenio, CotizanteVO oldCotiz, CotizanteVO newCotiz, List oldSecciones, long totalRentaImponible) throws SeccionException
	{
		List seccionesReturn = new ArrayList();
		this.numM = new Integer((String) this.parametros.get("" + Constants.PARAM_NUM_M)).intValue();
		logger.info("actualizando cmp:" + this.tipoProceso + ":n secc origin:" + oldSecciones.size() + "::");
		HashMap hashSecciones = new HashMap();

		for (Iterator it = oldSecciones.iterator(); it.hasNext();)
		{
			SeccionVO secc = (SeccionVO) it.next();
			secc.refreshListaM();
			for (Iterator itDet = secc.getDetallesSeccion().iterator(); itDet.hasNext();)
				((DetalleSeccionVO) itDet.next()).refreshListaM();
			secc.getConfigPDF().parse();
			hashSecciones.put("" + secc.getIdTipoSeccion(), secc);
		}
		inicializaSecciones(false, convenio, hashSecciones);
		logger.info("Total renta Imponible Comprobante=" + totalRentaImponible);
		if (oldCotiz != null)
		{
			int rentaCotizante= oldCotiz.getCotizacion().getRenta();
			logger.info("renta old Cotizante:" + rentaCotizante);
			
			if (oldCotiz.getIdEntPensionReal() == Constants.ID_INP){
				oldCotiz.setTipoPrevision(Seccion.IS_INP);
				rentaCotizante= oldCotiz.getCotizacion().getRentaImpInp();
			}else if (oldCotiz.getIdEntPensionReal() == Constants.ID_AFP_NINGUNA){
				oldCotiz.setTipoPrevision(Seccion.IS_AFP_NINGUNA);
			}else{
				// AFP
				oldCotiz.setTipoPrevision(Seccion.IS_AFP);
			}
			totalRentaImponible-= rentaCotizante;
			
			if (oldCotiz.getIdEntSaludReal() != Constants.ID_FONASA && oldCotiz.getIdEntSaludReal() != Constants.ID_SALUD_NINGUNA)
				oldCotiz.setIsapre(true);
			if (this.tipoProceso == 'R')
				oldCotiz.setAfpVoluntario(((CotizacionREVO)oldCotiz.getCotizacion()).isVoluntario());

			for (Iterator itSecc = this.secciones.keySet().iterator(); itSecc.hasNext();)
			{
				String clave = (String) itSecc.next();
				SeccionVO seccion = (SeccionVO) this.secciones.get(clave);
				Seccion seccionador = (Seccion) this.seccionadores.get(clave);

				if(seccion.getNumTrabajadores() > 0){
					// si es cotizante voluntario solo se considera detalle para la seccion AFP (1)
					if ((oldCotiz.isAfpVoluntario() && (seccion.getIdTipoSeccion() == Constants.TIPO_SECCION_AFP               ||
														seccion.getIdTipoSeccion() == Constants.TIPO_SECCION_AFP_RELIQUIDACION ||
														seccion.getIdTipoSeccion() == Constants.TIPO_SECCION_AFP_GRATIFICACION)) || !oldCotiz.isAfpVoluntario())
						seccionador.restar(this.tipoProceso, oldCotiz, seccion);
					else if (oldCotiz.isAfpVoluntario())
						seccionador.setNumTrabajadoresSeccion(seccion.getNumTrabajadores());	
				}
			}
		}

		if (newCotiz != null)
		{	
			int rentaCotizante= newCotiz.getCotizacion().getRenta();
			logger.info("renta new Cotizante:" + rentaCotizante);
			
			if (newCotiz.getIdEntPensionReal() == Constants.ID_INP){
				newCotiz.setTipoPrevision(Seccion.IS_INP);
				rentaCotizante= newCotiz.getCotizacion().getRentaImpInp();
			}else if (newCotiz.getIdEntPensionReal() == Constants.ID_AFP_NINGUNA){
				newCotiz.setTipoPrevision(Seccion.IS_AFP_NINGUNA);
			}else{
				// AFP
				newCotiz.setTipoPrevision(Seccion.IS_AFP);
			}
			totalRentaImponible += rentaCotizante;
			if (newCotiz.getIdEntSaludReal() != Constants.ID_FONASA && newCotiz.getIdEntSaludReal() != Constants.ID_SALUD_NINGUNA)
				newCotiz.setIsapre(true);
			if (this.tipoProceso == 'R')
				newCotiz.setAfpVoluntario(((CotizacionREVO)newCotiz.getCotizacion()).isVoluntario());

			for (Iterator itSecc = this.secciones.keySet().iterator(); itSecc.hasNext();)
			{
				String clave = (String) itSecc.next();
				SeccionVO seccion = (SeccionVO) this.secciones.get(clave);
				Seccion seccionador = (Seccion) this.seccionadores.get(clave);

				// si es cotizante voluntario solo se considera detalle para la seccion AFP (1)
				if ((newCotiz.isAfpVoluntario() && seccion.getIdTipoSeccion() == Constants.TIPO_SECCION_AFP) || !newCotiz.isAfpVoluntario())
					seccionador.generar(this.tipoProceso, newCotiz, seccion);
				else if (newCotiz.isAfpVoluntario())
					seccionador.setNumTrabajadoresSeccion(seccion.getNumTrabajadores());
			}
		}
		setRentaImponible(totalRentaImponible);
		this.total = 0;
		for (Iterator it = this.secciones.keySet().iterator(); it.hasNext();)
		{
			String valor = (String) it.next();
			SeccionVO seccion = (SeccionVO) this.secciones.get(valor);
			Seccion seccionador = (Seccion) this.seccionadores.get(valor);
			//csanchez. El método calcula subtotales sólo para el tipo de sección 3
			//ya que para que los tipos 22 y 42, según lo informado en TIPO_SECCION, no existen.
			if (valor.equals(Constants.TIPO_SEC_INP) && seccion.getIdTipoSeccion() == Constants.ID_TIPO_SECCION_INP) {
				seccionador.calculaTotalINP(seccion, seccion.getIdTipoSeccion());
			}
			if(valor.equals(Constants.TIPO_SEC_CAJA)){
				seccion.sumaTotalesCaja();
			}else{
				seccion.sumaTotales();	
			}
			seccionador = (Seccion) this.seccionadores.get(valor);

			int numTrab = seccion.getNumTrabajadores();
			
			if (numTrab > 0)
			{
				long tot = seccionador.finishProcess(this.tipoProceso, seccion);
				logger.info("seccion:" + seccion.getIdTipoSeccion() + ":NumTrab:" + numTrab + ":TipoPago:" + seccion.getTipoPago() + ":total:" + tot + "::");
				this.total += tot;
				seccionesReturn.add(seccion);
			}
		}
		if (this.total < 0)
			this.total = 0;
		logger.debug("\n\n\nn seccionesReturn:" + seccionesReturn.size() + "::");
		return seccionesReturn;
	}

	/**
	 * @return el rentaImponible
	 */
	public long getRentaImponible() {
		return rentaImponible;
	}

	/**
	 * @param rentaImponible el rentaImponible a establecer
	 */
	public void setRentaImponible(long rentaImponible) {
		this.rentaImponible = rentaImponible;
	}
}
