package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN340 extends Validacion
{
	private static Logger log = Logger.getLogger(VN340.class);

	/*
	 * 1 parametro = VN340: cotizacion desahucio
	 * 
	 * Mensajes 
	 * 159: Monto Cotizacion Desahucio invalido 
	 * 160: Monto Cotizacion Desahucio debe aparecer 
	 * 325: Monto Desahucio no corresponde al no ser INP
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO) this.parametros.get(0);
			this.mensaje = c.getNombre();
			//int monto = asignaValor(c.getValor());
//			clillo 13-05-16 No se recibe ningún pago IPS/Fonasa
			CotizacionREVO cotRevo = (CotizacionREVO) cotizante.getCotizacion();
			cotRevo.setInpDesahucio(0);
			return this.COD_CUMPLE_VALIDACION;
			//return valida(monto, c.getValor(), cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN340::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizanteVO cotizante)
	{
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN340 ERR:montoInpDesahucio invalido:" + valor + "::");
			return 159;// Monto Cotizacion Desahucio invalido
		}

		((CotizacionREVO) cotizante.getCotizacion()).setInpDesahucio(monto);
		if (monto > 0 && cotizante.getTipoPrevision() != Constants.TIPO_PREVISION_INP)
		{
			if (this.logear)
				log.info("validacion VN330 ERR:montoInpDesahucio no corresponde si no es INP::" + monto + "::" + (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP ? "AFP" : "NINGUNA") + "::");
			return 325; //Monto Desahucio no corresponde al no ser INP
		}
		if (this.logear)
			log.info("validacion VN340 OK:montoInpDesahucio:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;

			CotizacionREVO cotRevo = (CotizacionREVO) cotizante.getCotizacion();
			//int monto = cotRevo.getInpDesahucio();
			cotRevo.setInpDesahucio(0);
			return this.COD_CUMPLE_VALIDACION;
			//return valida(monto, "" + monto, cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN340::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN340(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN340(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN340(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
