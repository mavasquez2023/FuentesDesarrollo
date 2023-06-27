package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;

public class VN1430 extends Validacion
{
	private static Logger log = Logger.getLogger(VN1430.class);

	/*
	 * 1 parametro = VN1430: Cotización Fonasa
	 *
	 * Mensajes
	 * 228: Monto Cotizacion Obligatoria de Salud FONASA incorrecto
	 * 322: Monto Cotizacion Obligatoria de Salud DEBE SER MAYOR QUE 0
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
			this.resultado = ""; //Solo esta devolviendo "", ya que debe continuar con el siguiente VN configurado

			//Inicio gmallea
			 String mapeoFonasa = (String)this.parametrosNegocio.get("mapeoFonasa");
			 OpcionProcVO opcionProcVO = (OpcionProcVO)this.parametrosNegocio.get("opcionProcesos");
			 if (opcionProcVO == null){
				return this.SIN_PARAM_NEGOCIO;
			 }

			if( !cotizante.isIsapre()){ //No es Isapre => FONASA

				int montoInformado = 0;
				EntidadSaludVO entSalud = null;

				if(isWeb) {
					//montoInformado = cotizante.getCotizacion().getSaludObligatorio();
					entSalud = (EntidadSaludVO)this.session.get(EntidadSaludVO.class, new Integer(cotizante.getIdEntSaludReal()));
				}else{
					if (this.parametros == null || this.parametros.size() != 1){
						respuesta = this.SIN_CONCEPTOS;
					} else {
						ConceptoVO c = (ConceptoVO) this.parametros.get(0);
						this.mensaje = c.getNombre();
						//montoInformado = asignaValor(c.getValor());
						entSalud = (EntidadSaludVO)this.mapeoEntSalud.get(String.valueOf(cotizante.getIdEntSaludReal()));
					}
				}

				if (entSalud == null){
					if (this.logear)
						log.info("validacion VN1430 ERR:salud Obligatorio, entidadSalud no encontrada:" + cotizante.getIdEntSaludReal() + ": asigna valor recibido:" + montoInformado + "::");
					cotizante.getCotizacion().setSaludObligatorio(montoInformado);
					return 3504; // 'ENTIDAD SALUD NO ENCONTRADA' //this.ERR_VAL_ANTERIOR;
				}
				//clillo 26-6-12 Se calcula monto para utilizar o comparar contra el Informado
//				clillo 25-05-16 Sin pago para IPS
				//int montoCalculado= this.montoFonasaCalculado(cotizante, entSalud);
				int montoCalculado= 0;
				if( opcionProcVO.isCalFonasa() ){
					cotizante.getCotizacion().setSaludObligatorio( montoCalculado );
				}else{
					//clillo 26-6-12 Si se mapea Fonasa se debe setear valor Informado
				 	if(mapeoFonasa != null && !mapeoFonasa.equals("NOK")){
						cotizante.getCotizacion().setSaludObligatorio( montoInformado );
				 		cotizante.setIdEntSaludReal(Constants.ID_FONASA);
				 		//Si difiere valor Informado con Calculado se genera Aviso
				 		/*if( montoInformado > 0 && !this.isValidoConTolerancia(montoInformado, montoCalculado)) {
							return 228; //Monto Cotizacion Obligatoria de Salud FONASA incorrecto
				 		}*/
				 	}
				}
			}
			return respuesta;
		} catch (Exception e){
			log.error("error validacion VN1430::", e);
			return this.CAIDA_SISTEMA;
		}
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


	/**
	 * Indica si el monto informado es valido aplicando la tolerancia definida
	 * @param montoInformado
	 * @param montoCalculado
	 * @return
	 */
	private boolean isValidoConTolerancia(int montoInformado, int montoCalculado){
		int tolerancia = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_TOLERANCIA_PESO)).intValue();
		if ( montoInformado > (montoCalculado + tolerancia)  || montoInformado < (montoCalculado - tolerancia) )
			return false;
		else
			return true;
	}

	public VN1430(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN1430(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN1430(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}

	public long extraeFonasaDelValorINP(long cotizacionINP, float porcentajeFonasa){

		float montoFonasa = (cotizacionINP * porcentajeFonasa) / 100;

		return  Math.round(montoFonasa);
	}

}
