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
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegImpositivoVO;

public class VN1420 extends Validacion
{
	private static Logger log = Logger.getLogger(VN1420.class);

	/*
	 * 1 parametro = VN1420: Monto INP
	 *
	 * Mensajes
	 * 3497: INP - NO INFORMADO
	 * 3498: INP - MONTO INVALIDO
	 * 3499: INP - MONTO DEBE SER MAYOR O IGUAL A 0
	 * 3500: INP - REGIMEN IMPOSITIVO INCORRECTO
	 *
	 */
	public int valida(CotizanteVO cotizante){
		return this.valida(cotizante, false);
	}

	public int validaFromWEB(CotizanteVO cotizante){
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
			int respuesta = this.COD_CUMPLE_VALIDACION; //La respuesta se cambia solo cuando no se cumple la la validación.
			this.resultado = ""; //Solo está devolviendo "", ya que debe continuar con el siguiente VN configurado

			int montoInformado = 0;
			EntidadSaludVO entSalud = null;
			if(isWeb) {
				//montoInformado = cotizante.getCotizacion().getPrevisionObligatorio();
				entSalud = (EntidadSaludVO)this.session.get(EntidadSaludVO.class, new Integer(cotizante.getIdEntSaludReal()));
			}else{
				if (this.parametros == null || this.parametros.size() != 1)
					respuesta = this.SIN_CONCEPTOS;
				else {
					ConceptoVO c = (ConceptoVO) this.parametros.get(0);
					this.mensaje = c.getNombre();
					//montoInformado = asignaValor(c.getValor());
					entSalud = (EntidadSaludVO)this.mapeoEntSalud.get(String.valueOf(cotizante.getIdEntSaludReal()));
				}
			}
			OpcionProcVO opcionProcVO = (OpcionProcVO)this.parametrosNegocio.get("opcionProcesos");
			String mapeoFonasa = (String)this.parametrosNegocio.get("mapeoFonasa");
			
			if (cotizante.isAfpVoluntario()){

				cotizante.getCotizacion().setPrevisionObligatorio(0);

				if (this.logear)
					log.info("validacion VN1420 OK:Monto INP:" + montoInformado + "::");

			}else if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP) {
				
				//chequeo el regimen impositivo
				RegImpositivoVO regimen = (RegImpositivoVO) this.session.get(RegImpositivoVO.class, new RegImpositivoVO(cotizante.getIdEntExCaja(), cotizante.getIdRegimenImp()));
				if (regimen == null){
					if (this.logear)
						log.info("validacion VN1420 Monto INP ERR: regimen impositivo invalido ::");
					cotizante.getCotizacion().setPrevisionObligatorio(montoInformado);
					respuesta = 3500; //INP - REGIMEN IMPOSITIVO INCORRECTO en VN280 devuelve 339
				}else {
					//Siempre calculo el monto INP, ya que lo utilizaré para setearlo o para validar el monto informado.

					int rentaParaCalculo = 0;

					if(cotizante.getTipoProceso() == 'R'){
						rentaParaCalculo = cotizante.getCotizacion().getRentaImpInp();
					}else if(cotizante.getTipoProceso() == 'G'){
						rentaParaCalculo = ((CotizacionGRVO)cotizante.getCotizacion()).getGratificacion();
					}else if(cotizante.getTipoProceso() == 'A'){
						rentaParaCalculo = ((CotizacionRAVO)cotizante.getCotizacion()).getReliquidacion();
					}
					//clillo 25-05-16 Sin pago para IPS
					//int montoINPCalculado = calcularMontoINP( rentaParaCalculo, regimen.getTasaPension() );
					int montoINPCalculado = 0;
					
					if ( opcionProcVO.getCalInp() ) {
						if (this.logear)
						    log.info("validacion VN1420 OK:Monto INP: valor calculado:" + montoINPCalculado + "::");

						cotizante.getCotizacion().setPrevisionObligatorio(montoINPCalculado);
					}else{
					//Si se rescata valor informado de INP, para un trabajador que está en Fonasa
					//si se cumple condición que no se calcula Fonasa y Fonasa no se está mapeando
					//entonces se separa extrae valor de Fonasa en valor Informado
						
						if(!cotizante.isIsapre() && !opcionProcVO.isCalFonasa() && (mapeoFonasa != null && mapeoFonasa.equals("NOK")) ){
//							clillo 25-05-16 Sin pago para IPS
							//int montoFonasaCalculado = this.montoFonasaCalculado(cotizante, entSalud);
							int montoFonasaCalculado = 0;
							//Fonasa
							cotizante.getCotizacion().setSaludObligatorio(montoFonasaCalculado);
					 		//INP
					 		cotizante.getCotizacion().setPrevisionObligatorio(montoInformado - montoFonasaCalculado);
						}else{
							cotizante.getCotizacion().setPrevisionObligatorio(montoInformado);
						}

						/*if(montoInformado < 0 )
							respuesta = 3499; //INP - MONTO INP DEBE SER MAYOR O IGUAL A 0
						else if(montoInformado == 0)
							respuesta = 3497; //INP - NO INFORMADO
						else{
							if(!isValidoAplicandoTolerancia(montoInformado, montoINPCalculado )){
								if (this.logear)
									log.info("validacion VN1420 MONTO INP INVALIDO:" + montoInformado + "::");
								respuesta = 3498; //INP - MONTO INP INVALIDO
							}
						}
						*/
					}
				}
			//clillo 26-6-12
			//Previsión Trabajador es AFP
			}/*else{
				if(!cotizante.isIsapre()){
					cotizante.getCotizacion().setSaludObligatorio(montoInformado);
					int montoFonasaCalculado = this.montoFonasaCalculado(cotizante, entSalud);
					if ( !opcionProcVO.getCalInp() ) {
						if(montoInformado>0 && !isValidoAplicandoTolerancia(montoInformado, montoFonasaCalculado )){
								if (this.logear)
									log.info("validacion VN1420 MONTO FONASA INVALIDO:" + montoInformado + "::");
								respuesta = 228; //INP - MONTO FONASA INVALIDO
						}

					}
				}


			}*/

			return respuesta;
		} catch (Exception e)
		{
			log.error("error validacion VN1420::", e);
			return this.CAIDA_SISTEMA;
		}
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
	 * Calcula el Monto INP
	 * @param rentaImpInp
	 * @param tasaPension
	 * @return
	 */
	private int calcularMontoINP(int rentaImpInp, float tasaPension ) {

		int montoINP = Math.round(rentaImpInp * tasaPension / 100);

		return montoINP;
	}

	/**
		 * Calcula el monto FONASA
		 * @param cotizante
		 * @param entSalud
		 * @return
		 */
		private int montoFonasaCalculado(CotizanteVO cotizante, EntidadSaludVO entSalud) {

			float restaCCAFFON = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_APORTE_CCAF_FONASA)).floatValue() / 100;

			int montoCalculado = 0;
			int renta = 0;
			if(cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP){
				renta = cotizante.getCotizacion().getRentaImpInp();
			}else { //Se asume TIPO_PREVISION_AFP
				renta = cotizante.getCotizacion().getRenta();
			}

			if (this.datosConvenio.getIdCcaf() == Constants.SIN_CCAF) {  //sin caja => todo a INP

				 montoCalculado = this.getCotizacionFonasa(renta, entSalud.getTasaSalud());

			} else{ //con caja => resta porcentaje aporte caja
				String tipoEmpresa = (String) this.parametrosNegocio.get("tipoEmpresa");

				if(tipoEmpresa == null)
					tipoEmpresa = Constants.TIPO_EMPRESA;

				if(tipoEmpresa.equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){

					montoCalculado = this.getCotizacionFonasa(renta, (entSalud.getTasaSalud()) );

				}else{
					montoCalculado = this.getCotizacionFonasa(renta, (entSalud.getTasaSalud() - restaCCAFFON) );
				}


			}
			return montoCalculado;
		}

		/**
		 * Redondea el monto de la renta por la tasa de salud informada
		 * @param renta
		 * @param tasaSalud
		 * @return
		 */
		private int getCotizacionFonasa(int renta, float tasaSalud){
			return Math.round(tasaSalud * renta);

	}

	public VN1420(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN1420(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN1420(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
