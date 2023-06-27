package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;

public class VN680 extends Validacion
{
	private static Logger log = Logger.getLogger(VN680.class);

	/*
	 * 1 parametro = VN680: tasa pactada indemnizacion
	 * 
	 * Mensajes 198: VALOR TASA INDEMNIZACION INVALIDA
	 * 			199: VALOR TASA INDEMNIZACION INCORRECTA
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

			if (!this.parametrosNegocio.containsKey("" + Constants.PARAM_MIN_TASA_INDEM) || !this.parametrosNegocio.containsKey("" + Constants.PARAM_MAX_TASA_INDEM))
				return this.SIN_PARAM_NEGOCIO;

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO) this.parametros.get(0);
			this.mensaje = c.getNombre();
			float tasa = asignaValorF(c.getValor());

			return valida(tasa, c.getValor(), cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN680::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	//private int valida(float tasa, String valor, CotizacionDCVO cotizacionDCVO)
	private int valida(float tasa, String valor, CotizanteVO cotizante) {
	
		if (tasa < 0)
		{
			if (this.logear)
				log.info("validacion VN680 tasa pactada indemnizacion ERR:valor recibido invalido:" + valor + "::");
			this.setTasaPactada(tasa, cotizante);
			return 198; // VALOR TASA INDEMNIZACION INVALIDA
		} else if (tasa == 0)
		{
			if (this.logear)
				log.info("validacion VN680 OK:tasa pactada indemnizacion 0::");
			return this.COD_CUMPLE_VALIDACION;
		} else if (tasa > 100)
		{
			if (this.logear)
				log.info("validacion VN680 ERR:tasa pactada indemnizacion invalida. debe ser menor que 100:" + tasa + "::");
			this.setTasaPactada(tasa, cotizante);
			return 198; // VALOR TASA INDEMNIZACION INVALIDA
		} else
		{
			float tasaMin = new Float((String) this.parametrosNegocio.get("" + Constants.PARAM_MIN_TASA_INDEM)).floatValue();
			float tasaMax = new Float((String) this.parametrosNegocio.get("" + Constants.PARAM_MAX_TASA_INDEM)).floatValue();
			if (tasa < tasaMin || tasa > tasaMax)
			{
				if (this.logear)
					log.info("validacion VN680 tasa pactada indemnizacion ERR:valor fuera de limites:recibido:" + tasa + ":tasaMinima:" + tasaMin + ":tasaMaxima:" + tasaMax + "::");
				this.setTasaPactada(tasa, cotizante);
				return 199; // VALOR TASA INDEMNIZACION INCORRECTA
			}
			this.setTasaPactada(tasa, cotizante);
		}
		if (this.logear)
			log.info("validacion VN680 OK:tasa pactada indemnizacion:" + this.getTasaPactada(cotizante) + "::");
		return this.COD_CUMPLE_VALIDACION;
	}
	
	private void setTasaPactada(float tasa, CotizanteVO cotizante) {
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			cotizacion.setTasaPactada(tasa);
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			cotizacion.setTasaPactada(tasa);
		}
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
	
	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			GrupoConvenioVO grupoConvenioVO = (GrupoConvenioVO)this.parametrosNegocio.get("grupoConvenio");
			if (cotizante.getTipoProceso() == 'A' || cotizante.getTipoProceso() == 'G' || (cotizante.getTipoProceso() == 'R' && grupoConvenioVO.isPrevired()))
				return this.COD_CUMPLE_VALIDACION;

			if (!this.parametrosNegocio.containsKey("" + Constants.PARAM_MIN_TASA_INDEM) || !this.parametrosNegocio.containsKey("" + Constants.PARAM_MAX_TASA_INDEM))
				return this.SIN_PARAM_NEGOCIO;

			float tasa = this.getTasaPactada(cotizante);

			return valida(tasa, "" + tasa, cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN680::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN680(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN680(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN680(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
