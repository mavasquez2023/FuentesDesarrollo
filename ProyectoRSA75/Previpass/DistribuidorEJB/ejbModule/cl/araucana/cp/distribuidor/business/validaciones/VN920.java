package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN920 extends Validacion
{
	private static Logger log = Logger.getLogger(VN920.class);

	/*
	 * 1 parametro = VN920: aporte trabajador APVC
	 * 
	 * Mensajes
	 * 		304: Aporte trabajador APVC debe ser numerico
	 * 		305: Aporte trabajador APVC debe existir
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			
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
				else
				{
					if (this.logear)
						log.info("validacion VN920 ERR:aporteTrabajador APVC, valor recibido:" + valor + "::");
					((CotizacionREVO)cotizante.getCotizacion()).setApvcAporteEmpl(-1);
	        		return 304;//Aporte trabajador APVC debe ser numerico (no es numero ni vacio)
				}
			}

			((CotizacionREVO)cotizante.getCotizacion()).setApvcAporteTrab(monto);

			if (this.logear)
				log.info("validacion VN920 OK:aporteTrabajadorAPVC:" + monto + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN920::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			CotizacionREVO cotizacionREVO = (CotizacionREVO)cotizante.getCotizacion();
			int apvAporteTrab = cotizacionREVO.getApvcAporteTrab();

			if ( apvAporteTrab < 0)
			{
				if (this.logear)
					log.info("validacion VN920 ERR: Aporte trabajador APVC debe ser mayor a 0::");
				return  304; //Aporte trabajador APVC debe ser numerico
			}
			if (this.logear)
				log.info("validacion VN920 OK:aporteTrabajadorAPVC:" + apvAporteTrab + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN920::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN920(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN920(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN920(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
