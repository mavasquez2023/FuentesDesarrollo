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

public class VN200 extends Validacion 
{
	private static Logger log = Logger.getLogger(VN200.class);
	/*
	 * 1 parametro = VN200: remuneracion imponible, requiere: VN070
	 * 
	 * Mensajes
	 * 		130: Remuneracion imponible invalida
	 * 		131: Remuneracion imponible mayor al tope permitido
	 */	
	public int valida(CotizanteVO cotizante) 
	{
		try
		{
			if (cotizante.isAfpVoluntario())
				this.resultado = "F";
			else
				this.resultado = "E";

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int monto = asignaValor(c.getValor());
			int respuesta = valida(monto, c.getValor(), cotizante, cotizante.getCotizacion());
			/*if (respuesta == this.COD_CUMPLE_VALIDACION)
				cotizante.getCotizacion().setRentaImpInp(monto);*/
			return respuesta;
		} catch(Exception e)
		{
			log.error("error validacion VN200::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizanteVO cotizante, CotizacionVO cotizacion) 
	{
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN200 ERR:remuneracion imponible:invalido:" + valor + "::");
			return 130; //Remuneracion imponible invalida (no es numero ni vacio)
		}
		cotizacion.setRenta(monto);
//		int diasTrabajados = cotizante.getNumDiasTrabajados();
//		if(diasTrabajados > 0 && monto == 0)
//		{
//			if (this.logear)
//				log.info("validacion VN200 ERR:remuneracion imponible:invalido:" + valor + ": valor debe ser mayor que 0 si hay dias trabajados: dias" + diasTrabajados + "::");
//			this.resultado = "";
//			return 130;//Remuneracion imponible invalida (debe haber monto si los dias trabajados son mayores que 0)
//		}
//		//SI EL codAfp == INP entonces valor UF mes anterior SINO valor UF mes actual
//		else
		if (!cotizante.isAfpVoluntario() && cotizante.getTipoProceso() == 'R')
		{
			if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP)//INP
			{       
		 		float ufAnterior = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_UF_ANTERIOR)).floatValue();
				int topeINP = Math.round(new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_INP)).floatValue() * ufAnterior);
				if (monto > topeINP)
				{
					this.resultado = "";
					cotizacion.setRenta(topeINP);
					cotizacion.setRentaImpInp(topeINP);
					if (this.logear)
						log.info("validacion VN200 ERR:remuneracion imponible sobre el tope: montoRecibido:" + monto + ":tope calculadoINP:" + topeINP + "::");
					return 131; // Remuneracion imponible mayor al tope permitido
				} else {
					cotizacion.setRentaImpInp(monto);
				}
			} else if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP)//AFP
			{
				float ufActual = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();
				int topeAFP = Math.round(new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_AFP)).floatValue() * ufActual);
				if (monto > topeAFP)
				{
					this.resultado = "";
					//TODO. Modificado por problemas al calcular Cotizaciones de Salud
					cotizacion.setRenta(topeAFP);
					//Claudio.
					//cotizacion.setRentaImpInp(topeAFP);
					if (this.logear)
						log.info("validacion VN200 ERR:remuneracion imponible sobre el tope: montoRecibido:" + monto + ":tope calculadoAFP:" + topeAFP + "::");
					return 131; // Remuneracion imponible mayor al tope permitido
				}
			}
		}		

		if (!cotizante.isAfpVoluntario())
			this.resultado = "";
		if (this.logear)
			log.info("validacion VN200 OK:remuneracion imponible:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.isAfpVoluntario())
				this.resultado = "F";
			else
				this.resultado = "";

			int monto = cotizante.getCotizacion().getRenta();
			return valida(monto, "" + monto, cotizante, cotizante.getCotizacion()); 
		} catch(Exception e)
		{
			log.error("error validacion VN200::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN200(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN200(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN200(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
