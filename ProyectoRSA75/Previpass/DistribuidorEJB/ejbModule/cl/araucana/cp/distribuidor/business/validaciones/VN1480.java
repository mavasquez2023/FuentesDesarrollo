package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;

public class VN1480 extends Validacion
{
	private static Logger log = Logger.getLogger(VN1480.class);

	/*
	 * 1 parametro= VN660: deposito convenido
	 * 
	 * 
	 * Mensajes
	 * 		194: MONTO DE DEPOSITO CONVENIDO INVALIDO
	 * 		195: MONTO DE DEPOSITO CONVENIDO INCORRECTO
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";

			if (cotizante.getTipoProceso() != 'R' && cotizante.getTipoProceso() != 'A' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int monto = asignaValor(c.getValor());

			return valida(monto, c.getValor(), cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN1480::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizanteVO cotizante) {
		
		if (monto < 0 ){
			if (this.logear)
				log.info("validacion VN1480 AFBR ERRor: valor recibido:" + valor + "::");
			return 1480; //MONTO AFBR INVALIDO
		}

		/*if (monto < 0) {
			if (this.logear)
				log.info("validacion VN660 deposito convenido ERR:valor recibido:" + valor + "::");
			return 194; //MONTO DE DEPOSITO CONVENIDO INVALIDO
		}*/

		if (cotizante.getTipoProceso() == 'A') {
			CotizacionRAVO cotizacion = (CotizacionRAVO) cotizante.getCotizacion();
			cotizacion.setOtrosAFBR(monto);
			
		}
		if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			cotizacion.setOtrosAFBR(monto);
		}

		if (this.logear)
			log.info("validacion VN1480 OK: AFBR:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() != 'R' && cotizante.getTipoProceso() != 'A' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;

			int monto = 0;
			if (cotizante.getTipoProceso() == 'A') {
				CotizacionRAVO cotizacion = (CotizacionRAVO) cotizante.getCotizacion();
				monto = cotizacion.getOtrosAFBR();
			} else if (cotizante.getTipoProceso() == 'R') {
				CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
				monto = cotizacion.getOtrosAFBR();
			}

			return valida(monto, "" + monto, cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN1480::", e);
			return this.CAIDA_SISTEMA;
		}
	}

		
	public VN1480(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN1480(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN1480(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
