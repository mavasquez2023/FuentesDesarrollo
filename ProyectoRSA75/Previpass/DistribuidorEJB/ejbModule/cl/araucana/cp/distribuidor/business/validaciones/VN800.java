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

public class VN800 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN800.class);
	/*
	 * 1 parametro = VN800: remuneracion imponible seg cesantia
	 * 
	 * Mensajes
	 * 		218: Monto Remuneracion Imponible Seguro cesantia invalido
	 * 		219: Monto Remuneracion Imponible Seguro cesantia incorrecto
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "E";
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			String valor = Utils.transforma(c.getValor());

			int monto = 0;
			if (!valor.equals(""))
			{
				Integer integer = NumeroValidacion.valida(valor);
				if (integer != null && integer.intValue() >= 0)
					monto = integer.intValue();
				else{
					if (this.logear)
						log.info("validacion VN800 ERR:Monto Remuneracion Imponible Seguro cesantia invalido:" + valor + "::");
	        		return 218;//Monto Remuneracion Imponible Seguro cesantia invalido (no es numero ni vacio)
				}
			}
			//Asepulveda - cambia el tipo de datos para el tope del Seguro Cesantía desde int a float.
			//int topeCesantia = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_CESANTIA)).intValue();
			float topeCesantia = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_CESANTIA)).floatValue();
			float UFActual = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();

			if (monto > Math.round(topeCesantia * UFActual)){
				monto = Math.round(topeCesantia * UFActual);			
			}
			setMonto(cotizante.getTipoProceso(), monto, cotizante.getCotizacion());
			if (this.logear)
				log.info("validacion VN800 OK:remImpSegCes:" + monto + "::");
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN800::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private void setMonto(char tipoProceso, int monto, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			((CotizacionREVO)cotizacion).setSegCesRemImp(monto);
		else if (tipoProceso == 'G')
			((CotizacionGRVO)cotizacion).setSegCesRemImp(monto);
		else if (tipoProceso == 'A')
			((CotizacionRAVO)cotizacion).setSegCesRemImp(monto);
	}

	private int getSegCesRemImp(char tipoProceso, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			return ((CotizacionREVO)cotizacion).getSegCesRemImp();
		else if (tipoProceso == 'G')
			return ((CotizacionGRVO)cotizacion).getSegCesRemImp();
		return ((CotizacionRAVO)cotizacion).getSegCesRemImp();
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "E";
			int monto = getSegCesRemImp(cotizante.getTipoProceso(), cotizante.getCotizacion());
			if (monto < 0)
			{
				if (this.logear)
					log.info("validacion VN800 ERR:remImpSegCes:" + monto + "::");
				return 219; //Monto Remuneracion Imponible Seguro cesantia incorrecto
			}

			float topeCesantia = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_CESANTIA)).floatValue();
			float UFActual = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();

			if (monto > Math.round(topeCesantia * UFActual))
			{
				monto = Math.round(topeCesantia * UFActual);				
			}
			setMonto(cotizante.getTipoProceso(), monto, cotizante.getCotizacion());
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN800 OK:remImpSegCes:" + monto + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN800::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN800(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN800(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN800(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
