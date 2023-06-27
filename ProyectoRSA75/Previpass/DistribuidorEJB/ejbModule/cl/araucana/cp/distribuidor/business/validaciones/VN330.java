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

public class VN330 extends Validacion
{
	private static Logger log = Logger.getLogger(VN330.class);

	/*
	 * 1 parametro = VN330: bonificacion ley 15.386, articulo 19, requiere VN070
	 * 
	 * Mensaje 
	 * 157: Monto Bonificacion Articulo 19, Ley 15.386 invalido 
	 * 158: Monto Bonificacion Articulo 19, Ley 15.386 debe aparecer 
	 * 324: Monto Bonificacion no corresponde al no ser INP
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
//			clillo 13-05-16 No se recibe ningún pago IPS/Fonasa
			//int monto = asignaValor(c.getValor());
			CotizacionREVO cotRevo = (CotizacionREVO) cotizante.getCotizacion();
			cotRevo.setInpBonificacion(0);
			//return valida(monto, c.getValor(), cotizante);
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN330::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizanteVO cotizante)
	{
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN330 ERR: monto Bonificacion invalido:" + valor + "::");
			return 157;// Monto Bonificacion Articulo 19, Ley 15.386 invalido
		}
		((CotizacionREVO) cotizante.getCotizacion()).setInpBonificacion(monto);
		if (monto > 0 && cotizante.getTipoPrevision() != Constants.TIPO_PREVISION_INP)
		{
			if (this.logear)
				log.info("validacion VN330 ERR: monto Bonificacion no corresponde si no es INP:" + monto + "::" + (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP ? "AFP" : "NINGUNA") + "::");
			return 324;//Monto Bonificacion no corresponde al no ser INP
		}
		if (this.logear)
			log.info("validacion VN330 OK:montoBonificacion:" + monto + "::");
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
//			clillo 13-05-16 No se recibe ningún pago IPS/Fonasa
			//int monto = cotRevo.getInpBonificacion();
			cotRevo.setInpBonificacion(0);
			return this.COD_CUMPLE_VALIDACION;
			//return valida(monto, "" + monto, cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN330::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN330(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN330(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN330(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
