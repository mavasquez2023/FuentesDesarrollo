package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN630 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN630.class);
	/*
	 * 1 parametro = VN630: Reliquidacion
	 * 
	 * Mensajes
	 * 		193: Monto de Reliquidacion invalido
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
			int monto = asignaValor(c.getValor());
			if (monto < 0)
			{
				if (this.logear)
					log.info("validacion VN630 ERR:reliquidacion, valor recibido:" + c.getValor() + "::");
        		return 193;//Monto de Reliquidacion invalido (no es numero ni vacio)
			}
			CotizacionRAVO cotizacionRAVO = (CotizacionRAVO) cotizante.getCotizacion();
			cotizacionRAVO.setReliquidacion(monto);

			if (this.logear)
				log.info("validacion VN630 OK:reliquidacion:" + monto + "::");
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN630::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "E";
			this.mensaje = "gratificacion";
			CotizacionRAVO cotizacionRAVO = (CotizacionRAVO) cotizante.getCotizacion();

			if(cotizacionRAVO.getReliquidacion() < 0)
			{
				if (this.logear)
					log.info("validacion VN630 ERR:reliquidacion invalido:" + cotizacionRAVO.getReliquidacion() + "::");
				return 193;//Monto de Reliquidacion invalido
			}
			if (this.logear)
				log.info("validacion VN630 OK:reliquidacion:" + cotizacionRAVO.getReliquidacion() + "::");
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN630::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN630(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN630(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN630(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
