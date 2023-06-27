package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;

public class VN690 extends Validacion
{
	private static Logger log = Logger.getLogger(VN690.class);

	/*
	 * 1 parametro = VN690: aporte indemnizacion
	 * 
	 * Mensajes
	 * 		200: MONTO INDEMNIZACION INVALIDO
	 * 		201: MONTO INDEMNIZACION INCORRECTO
	 * 		202: MONTO INDEMNIZACION DEBE SER MAYOR QUE 0 
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			GrupoConvenioVO grupoConvenioVO = (GrupoConvenioVO)this.parametrosNegocio.get("grupoConvenio");
			if (cotizante.getTipoProceso() == 'A' || cotizante.getTipoProceso() == 'G' || (cotizante.getTipoProceso() == 'R' && grupoConvenioVO.isPrevired()))
				return this.COD_CUMPLE_VALIDACION;

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int monto = asignaValor(c.getValor());

			return valida(monto, c.getValor(), cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN690::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	//private int valida(int monto, String valor, CotizacionDCVO cotizacion)
	private int valida(int monto, String valor, CotizanteVO cotizante) {

		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN690 aporte indemnizacion ERR:valor recibido:" + valor + "::");
			this.setIndemAporte(-1, cotizante);
			return 200; //MONTO INDEMNIZACION INVALIDO
		}
		int rentaImponible = this.getRentaImponible(cotizante);
		float tasa = this.getTasaPactada(cotizante);
		this.setIndemAporte(monto, cotizante);
		tasa /= 100;
		if (monto != Math.round(rentaImponible * tasa))
		{
			if (this.logear)
				log.info("validacion VN690 aporte indemnizacion ERR:valor incorrecto:recibido:" + monto + ":valor calculado:" + Math.round(rentaImponible * tasa) + "::");
			return 201; //MONTO INDEMNIZACION INCORRECTO
		}
		if (this.logear)
			log.info("validacion VN690 OK:aporte indemnizacion:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			GrupoConvenioVO grupoConvenioVO = (GrupoConvenioVO)this.parametrosNegocio.get("grupoConvenio");
			if (cotizante.getTipoProceso() == 'A' || cotizante.getTipoProceso() == 'G' || (cotizante.getTipoProceso() == 'R' && grupoConvenioVO.isPrevired()))
				return this.COD_CUMPLE_VALIDACION;

			int monto = this.getIndemAporte(cotizante);

			return valida(monto, "" + monto, cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN690::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private void setIndemAporte(int monto, CotizanteVO cotizante) {
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			cotizacion.setIndemAporte(monto);
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			cotizacion.setIndemAporte(monto);
		}
	}
	
	private int getRentaImponible(CotizanteVO cotizante) {
		int rentaImponible = 0;
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			rentaImponible = cotizacion.getRentaImponible();
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			rentaImponible = cotizacion.getRentaImp();
		}
		return rentaImponible;
	}

	private float getTasaPactada(CotizanteVO cotizante) {
		float tasa = 0;
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			tasa = cotizacion.getTasaPactada();
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			tasa = cotizacion.getTasaPactada();
		}
		return tasa;
	}
	
	private int getIndemAporte(CotizanteVO cotizante) {
		int monto = 0;
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			monto = cotizacion.getIndemAporte();
		}
		if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			monto = cotizacion.getIndemAporte();
		}
		return monto;
	}

	public VN690(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN690(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN690(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
