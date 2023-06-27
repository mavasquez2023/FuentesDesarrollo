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

public class VN1440 extends Validacion 
{
	private static Logger log = Logger.getLogger(VN1440.class);
	/*
	 * 1 parametro = VN1440: remuneracion imponible INP
	 * 
	 * Mensajes
	 * 		130: Remuneracion imponible invalida
	 * 		131: Remuneracion imponible mayor al tope permitido
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
	private int valida(CotizanteVO cotizante, boolean isWeb) {
		try
		{
			int respuesta = this.COD_CUMPLE_VALIDACION; //La respuesta se cambia solo cuando no se cumple la la validación.
			this.resultado = ""; //Solo esta devolviendo "", ya que debe continuar con el siguiente VN configurado
			
			if(isWeb){
				//La renta INP está validada en el VN200
				return respuesta;
			}
			
			if( cotizante.getCotizacion().getRenta() == 0){
				ConceptoVO c = (ConceptoVO)this.parametros.get(0);
				this.mensaje = c.getNombre();
				int monto = asignaValor(c.getValor());
				
				respuesta = this.validaMonto(cotizante, monto);
			}
			
			

			return respuesta;
		} catch(Exception e)
		{
			log.error("error validacion VN600::", e);
			return this.CAIDA_SISTEMA;
		}
	}
	
	private int validaMonto(CotizanteVO cotizante, int monto){
		
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN1440 ERR:remuneracion imponible:invalido:" + monto + "::");
			return 130; //Remuneracion imponible invalida (no es numero ni vacio)
		}
		
		cotizante.getCotizacion().setRentaImpInp(monto);
		
		if(cotizante.getCotizacion().getRenta() == 0 ) {
			cotizante.getCotizacion().setRenta(monto);
		}

		if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP)//INP
		{       
	 		float ufAnterior = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_UF_ANTERIOR)).floatValue();
			int topeINP = Math.round(new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_INP)).floatValue() * ufAnterior);
			if (monto > topeINP)
			{
				this.resultado = "";
				cotizante.getCotizacion().setRenta(topeINP);
				cotizante.getCotizacion().setRentaImpInp(topeINP);
				if (this.logear)
					log.info("validacion VN1440 ERR:remuneracion imponible sobre el tope: montoRecibido:" + monto + ":tope calculadoINP:" + topeINP + "::");
				return 131; // Remuneracion imponible mayor al tope permitido
			}
		} else if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP)//AFP
		{
			float ufActual = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();
			int topeAFP = Math.round(new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_AFP)).floatValue() * ufActual);
			if (monto > topeAFP)
			{
				this.resultado = "";
				cotizante.getCotizacion().setRenta(topeAFP);
				cotizante.getCotizacion().setRentaImpInp(topeAFP);
				if (this.logear)
					log.info("validacion VN1440 ERR:remuneracion imponible sobre el tope: montoRecibido:" + monto + ":tope calculadoAFP:" + topeAFP + "::");
				return 131; // Remuneracion imponible mayor al tope permitido
			}
		}

		return this.COD_CUMPLE_VALIDACION;
	}


	public VN1440(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN1440(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN1440(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
