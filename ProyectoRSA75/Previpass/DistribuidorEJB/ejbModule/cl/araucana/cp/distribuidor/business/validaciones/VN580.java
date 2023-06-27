package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;

public class VN580 extends Validacion
{
	private static Logger log = Logger.getLogger(VN580.class);

	/*
	 * 1 parametro = VN580: remuneracion imponible mutual
	 * 
	 * Mensajes
	 * 	   187: Monto Imponible Mutual invalido
	 *     188: Monto Imponible Mutual incorrecto
	 *     340: Monto Mutual mayor a tope
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;

			OpcionProcVO opcionProcVO = (OpcionProcVO)this.parametrosNegocio.get("opcionProcesos");
			if (opcionProcVO == null)
				return this.SIN_PARAM_NEGOCIO;
			
			//Siempre se moverá la renta imponible a la renta Imponible mutual
			//clillo 07-1-13
			/*if(this.datosConvenio.getIdMutual() == Constants.SIN_MUTUAL ) {
				return this.COD_CUMPLE_VALIDACION;
			}*/

			this.resultado = "";
			ConceptoVO c = (ConceptoVO) this.parametros.get(0);
			this.mensaje = c.getNombre();
			int monto = asignaValor(c.getValor());

			return valida(cotizante.getTipoProceso(), true, monto, cotizante.getIdEntPensionReal(), c.getValor(), cotizante.getCotizacion());
		} catch (Exception e)
		{
			log.error("error validacion VN580::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(char tipoProceso, boolean comparar, int monto, int idEntPensionReal, String valor, CotizacionVO cotizacion)
	{
		{
			// si es negativo (invalido) envia error, si es cero se calcula, si no se valida
			if (!this.parametrosNegocio.containsKey("" + Constants.PARAM_UF_ACTUAL) || !this.parametrosNegocio.containsKey("" + Constants.PARAM_TOPE_AFP))
				return this.SIN_PARAM_NEGOCIO;
			if (monto < 0)
			{
				this.resultado = "E";
				if (this.logear)
					log.info("validacion VN580 ERR:mutual imponible:valor recibido invalido:" + valor + "::");
				return 187;// Monto Imponible Mutual invalido (no es numero ni vacio)
			}
			int paramtope;
			if(idEntPensionReal == Constants.ID_INP){
				paramtope= Constants.PARAM_TOPE_INP;
			}else{
				paramtope= Constants.PARAM_TOPE_AFP;
			}
			float UFActual= new Float((String) this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();
			float UFAnterior = new Float((String) this.parametrosNegocio.get("" + Constants.PARAM_UF_ANTERIOR)).floatValue();
			float topeImponibleUF= new Float((String) this.parametrosNegocio.get("" + paramtope)).floatValue();
			int montocontope = getRentaConTope(idEntPensionReal == Constants.ID_INP
					   ,topeImponibleUF
					   ,UFActual
					   ,UFAnterior
					   ,cotizacion);
			if (monto == 0) // calcular monto automaticamente: con tope por UFActual.
			{	
				monto = montocontope;
				cotizacion.setMutualImp(monto);
				if (this.logear)
					log.info("validacion VN580:recibido cero, calculado:" + monto + "::");
			} else{
				cotizacion.setMutualImp(monto);
				if (tipoProceso == 'R' || tipoProceso == 'G')
				{	
					int rentatope;
					if (idEntPensionReal == Constants.ID_INP){
						rentatope = Math.round(topeImponibleUF * UFAnterior);
					} else { 
						rentatope =	Math.round(topeImponibleUF * UFActual);
					}
					if (monto > rentatope)
					{
						cotizacion.setMutualImp(montocontope);
						if (this.logear)
							log.info("validacion VN570 ERR: Imponible mayor a tope :imponible inbformado: " + monto + " , tope:" + rentatope + "::");
						return 340; // Monto Mutual mayor a tope
					}
					if (comparar)
					{
						if (monto != montocontope )
						{
							if (this.logear)
								log.info("validacion VN580 ERR:mutual imponible:valor recibido invalido:" + valor + ":calculado:" + montocontope + "::");
							return 188;// Monto Imponible Mutual incorrecto
						}
					}
				}
			}

			if (this.logear)
				log.info("validacion VN580 OK:mutual imponible:" + monto + "::");
			this.resultado = "";
		}
		return this.COD_CUMPLE_VALIDACION;
	}

	private int getRentaConTope(boolean isINP, float topeUFs, float UFActual, float UFAnterior, CotizacionVO cotizacion)
	{
		int renta = cotizacion.getRenta();

		// marco segun  felipe utilizar siempre el tope renta Imponible
		if (isINP){
			// transforma 60 UFAnterior en 60 UFActual
			// marco Controla el tope 
		    if (renta > Math.round(topeUFs * UFAnterior)){
			    renta = Math.round(topeUFs * UFAnterior);
		    }
		} else { 
		    	
		  if (renta > Math.round(topeUFs * UFActual))
			 return Math.round(topeUFs * UFActual);
		}
		
		return renta;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			
			if(this.datosConvenio.getIdMutual() == Constants.SIN_MUTUAL ) {
				return this.COD_CUMPLE_VALIDACION;
			}			
			
			this.resultado = "E";
			int monto = cotizante.getCotizacion().getMutualImp();
			return valida(cotizante.getTipoProceso(), true, monto, cotizante.getIdEntPensionReal(), "" + monto, cotizante.getCotizacion());
		} catch (Exception e)
		{
			log.error("error validacion VN580::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN580(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN580(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN580(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
