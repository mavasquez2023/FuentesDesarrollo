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
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;

public class VN1410 extends Validacion
{
	private static Logger log = Logger.getLogger(VN1410.class);

	/*
	 * 1 parametro = VN1410: Validacion Previsión SIS
	 * 
	 * Mensajes
	 *        3491: SIS - MONTO PREVISION INVALIDO
	 *        3492: SIS - NO INFORMADO
	 *        3495: SIS - PREVISION INP NO CORRESPONDE VALOR
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
	 * Este es el método que hace el trabajo...
	 * @param cotizante
	 * @param web
	 * @return
	 */
	private int valida(CotizanteVO cotizante, boolean isWeb)
	{
		try
		{
			int respuesta = this.COD_CUMPLE_VALIDACION;
			this.resultado = "";
			int monto=0;
			//clillo 13-1-15 por Reliquidación
			//if (cotizante.getTipoProceso() == 'R' || cotizante.getTipoProceso() == 'G' || cotizante.getTipoProceso() == 'A') {
			if (cotizante.getTipoProceso() == 'R' || cotizante.getTipoProceso() == 'G') {
				
				if(isWeb){
					monto = this.getPrevisionSIS(cotizante.getTipoProceso(), cotizante.getCotizacion());
				}else{
					ConceptoVO c = (ConceptoVO) this.parametros.get(0);
					this.mensaje = c.getNombre();
					monto = asignaValor(c.getValor());
				}
				if(cotizante.isAfpVoluntario() || cotizante.getIdEntPensionReal() == Constants.ID_AFP_NINGUNA){
					monto = 0;
				}else if( this.seDebeValidarSIS() ){
					if(cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP){
						respuesta = this.isMontoSISValido(monto, cotizante.getTipoProceso(), cotizante.getIdEntPensionReal(), this.getRentaImponibleSIS(cotizante.getTipoProceso(), cotizante.getCotizacion()), isWeb);
						if(respuesta != this.COD_CUMPLE_VALIDACION) {
							if (this.logear)
								log.info("validacion VN1410 Error: " + respuesta + " MONTO INFORMADO: "+ monto + "::");
						}
					}else if( cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP){
						if(monto != 0){
							if (this.logear)
								log.info("validacion VN1410 SIS - PREVISION INP NO CORRESPONDE VALOR:" + monto + "::");							
							respuesta = 3495;
						}
					}
				}else
					this.seteaRentaImponibleSIS(cotizante.getTipoProceso(), cotizante.getCotizacion(), 0); //Seteo renta imponible
				
				this.seteaPrevisonSIS(cotizante.getTipoProceso(), cotizante.getCotizacion(), monto);				
			}
			
			if (respuesta == this.COD_CUMPLE_VALIDACION && this.logear)
				log.info("validacion VN1410 OK:Previsión SIS:" + monto + "::");			

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
	 * Obtiene la Previsión SIS dependiendo del tipo de Nómina
	 * @param tipoProceso
	 * @param cotizacion
	 * @return
	 */
	private int getPrevisionSIS(char tipoProceso, CotizacionVO cotizacion) {
		int monto = 0;
		if (tipoProceso == 'R') {
			monto = ((CotizacionREVO)cotizacion).getPrevisionSIS();
		}else if (tipoProceso == 'G') {
			monto = ((CotizacionGRVO)cotizacion).getPrevisionSIS();
		}else if (tipoProceso == 'A') {
			monto = ((CotizacionRAVO)cotizacion).getPrevisionSIS();
		}		
		return monto;
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
	
	/**
	 * Setea la Previsión SIS dependiendo del tipo de Nómina
	 * @param tipoProceso
	 * @param cotizacion
	 * @param monto
	 */
	private void seteaPrevisonSIS(char tipoProceso, CotizacionVO cotizacion, int monto){
		if (tipoProceso == 'R') {
			((CotizacionREVO)cotizacion).setPrevisionSIS(monto);
		}else if (tipoProceso == 'G') {
			((CotizacionGRVO)cotizacion).setPrevisionSIS(monto);
		}else if (tipoProceso == 'A') {
			((CotizacionRAVO)cotizacion).setPrevisionSIS(monto);
		}		
	}	
	
	/**
	 * Indica si es necesario validar el monto informado
	 * @param isPrivada
	 * @param periodoInformado
	 * @param iniVigSIS
	 * @param minTrabSIS
	 * @param cantidadCotizantesEnNomina
	 * @return
	 */
	private boolean seDebeValidarSIS() {
		boolean respuesta = false;
		
		String periodoInformado="";
		if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA)){
			 periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		
		}else if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
			 periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO_INDEPENDIENTE);
		}
		
		//String periodoInformado = (String)this.parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		String iniVigSIS = (String)this.parametrosNegocio.get("" + Constants.PARAM_PRIMER_PERIODO_VIG_PLENA_SIS);
		int minTrabSIS = Integer.parseInt((String)this.parametrosNegocio.get("" + Constants.PARAM_MINIMO_TRABAJADORES_SIS));
		boolean isPrivada = ((Integer) this.parametrosNegocio.get("empresaPrivada")).intValue() == 0 ? true : false;
		int cantidadCotizantesEnNomina = ((Integer)this.parametrosNegocio.get("cantidadCotizantes")).intValue();
		boolean informaSisEnNomina = ((Boolean)this.parametrosNegocio.get("informaSisEnNomina")).booleanValue();

		//Jlandero 28/07 - No debe validar SIS
		//Validaciones de Negocio
		if(isPrivada) {
			//Empresa Privada
			if(!respuesta && informaSisEnNomina) respuesta = true;
			if(!respuesta && cantidadCotizantesEnNomina > minTrabSIS) respuesta = true;
			if(!respuesta && Integer.parseInt(periodoInformado) >= Integer.parseInt(iniVigSIS) ) respuesta = true;
		}else{
			//Empresa Pública
			respuesta = true;
		}
		
		return respuesta;
	}
	
	/**
	 * Valida el monto informado aplicando la tolerancia parametrizada. 
	 * @param valorInformado
	 * @param valorCalculado
	 * @return
	 */
	private boolean isValidoAplicandoTolerancia(int valorInformado, int valorCalculado ){

		boolean respuesta = true;
		int tolerancia = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_TOLERANCIA_PESO)).intValue();
						
		if ( (valorInformado > (valorCalculado + tolerancia)) || (valorInformado < (valorCalculado - tolerancia))) 
			respuesta = false;
		
		return respuesta;
	}
	
	/**
	 * Valida si el monto informado es válido
	 * @param monto
	 * @param tipoProceso
	 * @return
	 */
	private int isMontoSISValido(int monto, char tipoProceso, int idEntPensionReal, int rentaImponibleSIS, boolean isWeb){

		int respuesta=this.COD_CUMPLE_VALIDACION;
		
		if(monto<0)
			respuesta = 3491;
		else if(monto == 0)
			respuesta = 3492;
		else if(tipoProceso == 'R'){
			EntidadPensionVO entidadPension = null;
			if (isWeb)
				entidadPension = (EntidadPensionVO) this.session.get(EntidadPensionVO.class, new Integer(idEntPensionReal));
			else
			{
				if (this.mapeoEntPension.containsKey(String.valueOf(idEntPensionReal)))
					entidadPension = (EntidadPensionVO) this.mapeoEntPension.get(String.valueOf(idEntPensionReal));
			}
			if (entidadPension == null)
			{
				if (this.logear)
					log.info("validacion VN280 cot pension obligatoria ERR: entidad prevision invalida (monto no se valida), asigna valor:" + monto + "::");
			} else {
				if( !this.isValidoAplicandoTolerancia( monto, Math.round( rentaImponibleSIS * entidadPension.getSis() / 100)))
					respuesta = 3491;
			}
		}
		
		return respuesta;
	}	


	public VN1410(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN1410(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN1410(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
