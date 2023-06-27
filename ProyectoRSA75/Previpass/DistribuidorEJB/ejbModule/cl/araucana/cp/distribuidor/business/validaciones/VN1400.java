package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN1400 extends Validacion
{
	private static Logger log = Logger.getLogger(VN1400.class);

	/*
	 * 1 parametro = VN1400: Validacion Renta Imponible SIS
	 * 
	 * Mensajes
	 *        3493: SIS - REMUNERACION IMPONIBLE DEBE SER MAYOR O IGUAL A 0
	 *        3494: SIS - REMUNERACION IMPONIBLE MAYOR AL TOPE PERMITIDO
	 *        3496: SIS - REMUNERACION IMPONIBLE INP NO CORRESPONDE
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		return this.valida(cotizante, false);
	}
	
	public int validaFromWEB(CotizanteVO cotizante)
	{
		return this.valida(cotizante, true);
	}
	
	/**
	 * Este es el método que hace la pega...
	 * @param cotizante
	 * @param isWeb
	 * @return
	 */
	private int valida(CotizanteVO cotizante, boolean isWeb)
	{
		try
		{
			int respuesta = this.COD_CUMPLE_VALIDACION;
			this.resultado = "";
			if (cotizante.getTipoProceso() == 'R' || cotizante.getTipoProceso() == 'G' || cotizante.getTipoProceso() == 'A') {
				
				int monto = 0;
				String sValor = null;
				if(isWeb){
					monto = this.getRentaImponibleSIS(cotizante.getTipoProceso(), cotizante.getCotizacion());
					sValor = String.valueOf(monto);
				}else{
					ConceptoVO c = (ConceptoVO) this.parametros.get(0);
					this.mensaje = c.getNombre();
					sValor = c.getValor();
					monto = asignaValor(c.getValor());
				}
				respuesta = this.valida(monto, sValor, cotizante, cotizante.getCotizacion());
			}
			return respuesta;
		} catch (Exception e)
		{
			log.error("error validacion VN1400::", e);
			return this.CAIDA_SISTEMA;
		}
	}
	
	/**
	 * Obtiene la renta imponible SIS dependiendo del tipo de Nómina
	 * @param tipoProceso
	 * @param cotizacion
	 * @return
	 */
	private int getRentaImponibleSIS(char tipoProceso, CotizacionVO cotizacion) {
		int monto = 0;
		if (tipoProceso == 'R') {
			monto = ((CotizacionREVO)cotizacion).getRentaImponibleSIS();
		}else if (tipoProceso == 'G') {
			monto = ((CotizacionGRVO)cotizacion).getRentaImponibleSIS();
		}else if (tipoProceso == 'A') {
			monto = ((CotizacionRAVO)cotizacion).getRentaImponibleSIS();
		}		
		return monto;
	}
	
	/**
	 * Valida y setea un valor válido en renta imponible SIS
	 * @param monto
	 * @param valor
	 * @param cotizante
	 * @param cotizacion
	 * @return
	 */
	private int valida(int monto, String valor, CotizanteVO cotizante, CotizacionVO cotizacion)
	{
		int respuesta = this.COD_CUMPLE_VALIDACION;
		
		this.seteaRentaImponibleSIS(cotizante.getTipoProceso(), cotizacion, monto);
		
		if (cotizante.isAfpVoluntario()) {
			if (this.logear)
				log.info("validacion VN1400 OK:SIS - Remuneracion Imponible:" + monto + "::");
			this.seteaRentaImponibleSIS(cotizante.getTipoProceso(), cotizacion, 0);	
		}else if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN1400 ERR:SIS - REMUNERACION IMPONIBLE DEBE SER MAYOR O IGUAL A 0:" + valor + "::");
			respuesta = 3493;
		}else if (cotizante.getTipoProceso() == 'R' || cotizante.getTipoProceso() == 'G' || cotizante.getTipoProceso() == 'A') {
			if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP && monto > 0){
				if (this.logear)
					log.info("validacion VN1400 OK: SIS - REMUNERACION IMPONIBLE INP NO CORRESPONDE:" + monto + "::");
				respuesta = 3496;
			}else if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP) {
				if(monto==0)
					this.seteaRentaImponibleSIS(cotizante.getTipoProceso(), cotizacion, cotizacion.getRenta()); //Seteo renta imponible
				else if( cotizante.getTipoProceso() == 'R') {
					float ufActual = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();
					int topeAFP = Math.round(new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_AFP)).floatValue() * ufActual);
					if (monto > topeAFP)
					{
						this.seteaRentaImponibleSIS(cotizante.getTipoProceso(), cotizacion, cotizacion.getRenta()); //Seteo renta imponible
						//this.seteaRentaImponibleSIS(cotizante.getTipoProceso(), cotizacion, topeAFP); //Seteo renta imponible
						if (this.logear)
							log.info("validacion VN1400 ERR:SIS - REMUNERACION IMPONIBLE MAYOR AL TOPE PERMITIDO: montoRecibido:" + monto + ":tope calculadoAFP:" + topeAFP + "::");
						return 3494;
					}
				}
			}
		}
		
		if (respuesta == this.COD_CUMPLE_VALIDACION && this.logear)
			log.info("validacion VN1400 OK:Renta Imponible SIS:" + monto + "::");
		
		return respuesta;
	}
	
	/**
	 * Setea la renta imponible SIS dependiendo del tipo de Nómina
	 * @param tipoProceso
	 * @param cotizacion
	 * @param monto
	 */
	private void seteaRentaImponibleSIS(char tipoProceso, CotizacionVO cotizacion, int monto){
		if (tipoProceso == 'R') {
			((CotizacionREVO)cotizacion).setRentaImponibleSIS(monto);
		}else if (tipoProceso == 'G') {
			((CotizacionGRVO)cotizacion).setRentaImponibleSIS(monto);
		}else if (tipoProceso == 'A') {
			((CotizacionRAVO)cotizacion).setRentaImponibleSIS(monto);
		}		
	}

	public VN1400(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN1400(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN1400(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
