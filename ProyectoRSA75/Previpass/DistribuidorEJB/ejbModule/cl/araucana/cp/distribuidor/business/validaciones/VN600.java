package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN600 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN600.class);
	/*
	 * 1 parametro = VN600: gratificacion
	 * 
	 * Mensajes
	 * 		192: Monto de Gratificacion invalido
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
					log.info("validacion VN600 ERR:gratificacion, valor recibido:" + c.getValor() + "::");
        		return 192;//Monto de Gratificacion invalido (no es numero ni vacio)
			}
			CotizacionGRVO cotizacionGRVO = (CotizacionGRVO) cotizante.getCotizacion();
			cotizacionGRVO.setGratificacion(monto);
			if (this.logear)
				log.info("validacion VN600 OK:gratificacion:" + monto + "::");

			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN600::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "E";
			CotizacionGRVO cotizacionGRVO = (CotizacionGRVO) cotizante.getCotizacion();

			if(cotizacionGRVO.getGratificacion() < 0)
			{
				if (this.logear)
					log.info("validacion VN600 ERR:gratificacion invalido:" + cotizacionGRVO.getGratificacion() + "::");
				return 192;//Monto de Gratificacion invalido
			}

			if (this.logear)
				log.info("validacion VN600 OK:gratificacion:" + cotizacionGRVO.getGratificacion() + "::");
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN600::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN600(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN600(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN600(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
