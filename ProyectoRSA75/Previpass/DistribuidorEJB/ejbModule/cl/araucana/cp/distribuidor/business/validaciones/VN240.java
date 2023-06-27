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

public class VN240 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN240.class);
	/*
	 * 1 parametro = VN240: cotizacion obligatoria de salud
	 * 
	 * Mensajes
	 * 		137: Monto Cotizacion Obligatoria de Salud ISAPRE invalido
	 * 		138: Monto Cotizacion Obligatoria de Salud ISAPRE incorrecto
	 * 		316: Monto Cotizacion Obligatoria de Salud supera el tope
	 * 		322: Monto Cotizacion Obligatoria de Salud DEBE SER MAYOR QUE 0
	 * 		227: MONTO COTIZACION OBLIGATORIA DE SALUD FONASA INVALIDO
	 * 		228: Monto Cotizacion Obligatoria de Salud FONASA incorrecto
	 * 		354: MONTO COTIZACION OBLIGATORIA DE SALUD FONASA SUPERA EL TOPE
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
	public int valida(CotizanteVO cotizante, boolean isWeb)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'D')
				return this.COD_CUMPLE_VALIDACION;

			if (!cotizante.isIsapre()){ //Cotizante es FONASA
				this.resultado = "";
				cotizante.getCotizacion().setSaludObligatorio(0);
				return this.COD_CUMPLE_VALIDACION;
			}else{ //Es Isapre
				int montoInformado = 0;
				
				EntidadSaludVO entSalud = null;
				
				this.resultado = "E";
				
				if(isWeb) {
					OpcionProcVO opcionProcVO = (OpcionProcVO)this.parametrosNegocio.get("opcionProcesos");
					if (opcionProcVO == null){
						return this.SIN_PARAM_NEGOCIO;
					}					
					montoInformado = cotizante.getCotizacion().getSaludObligatorio();
					entSalud = (EntidadSaludVO)this.session.get(EntidadSaludVO.class, new Integer(cotizante.getIdEntSaludReal()));
				}else{
					if (this.parametros == null || this.parametros.size() != 1){
						return this.SIN_CONCEPTOS;
					}else{
						ConceptoVO c = (ConceptoVO) this.parametros.get(0);	
						this.mensaje = c.getNombre();
						montoInformado = asignaValor(c.getValor());
						entSalud = (EntidadSaludVO)this.mapeoEntSalud.get(String.valueOf(cotizante.getIdEntSaludReal()));
					}
				}
				
				if (entSalud == null){
					if (this.logear)
						log.info("validacion VN240 ERR:salud Obligatorio, entidadSalud no encontrada:" + cotizante.getIdEntSaludReal() + ": asigna valor recibido:" + montoInformado + "::");
					cotizante.getCotizacion().setSaludObligatorio(montoInformado);
					return 3504; // 'ENTIDAD SALUD NO ENCONTRADA' //this.ERR_VAL_ANTERIOR;
				}				
				
				return validar(false, montoInformado, cotizante, entSalud);
			}			
		} catch(Exception e)
		{
			log.error("error validacion VN240::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int validar(boolean web, int montoInformado, CotizanteVO cotizante, EntidadSaludVO entSalud) 
	{
		if (cotizante.getIdEntSaludReal()== Constants.ID_SALUD_NINGUNA) {
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN240 OK:salud obligatorio mueve 0 Isapre = ninguna :" );
			return this.COD_CUMPLE_VALIDACION;
		}
				
		if (montoInformado < 0) {
			if (this.logear)
				log.info("validacion VN240 ERR:salud Obligatorio ISAPRE, valor recibido:" + montoInformado + "::");
			return 137;//Monto Cotizacion Obligatoria de Salud ISAPRE invalido (no es numero ni vacio)
		}

		cotizante.getCotizacion().setSaludObligatorio(montoInformado);
		
		if (cotizante.getTipoProceso() == 'R' || cotizante.getTipoProceso() == 'G') {
			
			int rentaImponible = cotizante.getCotizacion().getRenta();
			int montoCalculado = Math.round(entSalud.getTasaSalud() * rentaImponible);
			
			if( !this.isValidoConTolerancia(montoInformado, montoCalculado) ){
				if (this.logear)
					log.info("validacion VN240 ERR:salud Obligatorio, valor recibido:" + montoInformado + ":valor calculado:" + Math.round(entSalud.getTasaSalud() * rentaImponible) + ":TasaSalud:" + entSalud.getTasaSalud() + ":rentaImponible:" + rentaImponible + ": ISAPRE:");
					//17-05-2012 GMALLEA Si es incorrecto el monto el resultado se deja en ("") para que entre a siguiente ven que calcula el total isapres vn270
					this.resultado = "";
				return 138; //Monto Cotizacion Obligatoria de Salud ISAPRE incorrecto
			}			
		}
		this.resultado = "";
		if (this.logear)
			log.info("validacion VN240 OK:salud obligatorio:" + montoInformado + "::" + this.resultado + "::");
		return this.COD_CUMPLE_VALIDACION;
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

	public VN240(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN240(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN240(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
