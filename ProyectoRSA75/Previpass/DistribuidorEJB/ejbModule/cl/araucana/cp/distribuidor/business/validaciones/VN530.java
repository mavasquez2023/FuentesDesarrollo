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

public class VN530 extends Validacion
{
	private static Logger log = Logger.getLogger(VN530.class);

	/*
	 * 1 parametro = VN530: tasa de trabajo pesado, requiere VN070
	 * 
	 * Mensajes 
	 * 174: TASA DE TRABAJOS PESADOS INVALIDA 
	 * 175: TASA DE TRABAJOS PESADOS NO CORRESPONDE A VALORES DE MAPEO 
	 * 353: TASA DE TRABAJOS PESADOS DEBE APARECER
	 * 334: NO CORRESPONDE TASA TRAB.PESADO AL ESTAR ASOCIADO A INP 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			float tasa = asignaValorF(c.getValor());

			return valida(tasa, c.getValor(), cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN530::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(float tasa, String valor, CotizanteVO cotizante)
	{
		if (cotizante.getTipoPrevision() != Constants.TIPO_PREVISION_AFP && tasa > 0)
		{
			if (this.logear)
				log.info("validacion VN530 ERR:Tasa no corresponde si NO esta afiliado a una AFP::");
			return 334; //NO CORRESPONDE TASA TRAB.PESADO AL ESTAR ASOCIADO A INP
		}
		if (tasa < 0)
		{
			setTasa(cotizante.getTipoProceso(), -1, cotizante.getCotizacion());
			if (this.logear)
				log.info("validacion VN530 ERR:TasaTrabPesado:valor recibido:" + valor + "::");
			return 174;// TASA DE TRABAJOS PESADOS INVALIDA
		}
		if (tasa > 100)
		{
			setTasa(cotizante.getTipoProceso(), -1, cotizante.getCotizacion());
			if (this.logear)
				log.info("validacion VN530 ERR:TasaTrabPesado:valor recibido:" + valor + "::");
			return 174;// TASA DE TRABAJOS PESADOS INVALIDA
		}
		setTasa(cotizante.getTipoProceso(), tasa, cotizante.getCotizacion());

		if (!this.parametrosNegocio.containsKey("" + Constants.PARAM_MIN_TRAB_PESADO) || !this.parametrosNegocio.containsKey("" + Constants.PARAM_MAX_TRAB_PESADO))
			return this.SIN_PARAM_NEGOCIO;
		float minimo = new Float((String) this.parametrosNegocio.get("" + Constants.PARAM_MIN_TRAB_PESADO)).floatValue();
		float maximo = new Float((String) this.parametrosNegocio.get("" + Constants.PARAM_MAX_TRAB_PESADO)).floatValue();
		if (tasa != 0 && tasa != maximo && tasa != minimo)
		{
			if (this.logear)
				log.info("validacion VN530 ERR:TasaTrabPesado:valor recibido:" + valor + "::");
			return 175;// TASA DE TRABAJOS PESADOS NO CORRESPONDE A VALORES DE MAPEO
		}
		if (this.logear)
			log.info("validacion VN530 OK:TasaTrabPesado:" + tasa + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	private void setTasa(char tipoProceso, float tasa, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			((CotizacionREVO) cotizacion).setTasaTrabPesado(tasa);
		else if (tipoProceso == 'G')
			((CotizacionGRVO) cotizacion).setTasaTrabPesado(tasa);
		else if (tipoProceso == 'A')
			((CotizacionRAVO) cotizacion).setTasaTrabPesado(tasa);
	}

	private float getTasa(char tipoProceso, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			return ((CotizacionREVO) cotizacion).getTasaTrabPesado();
		else if (tipoProceso == 'G')
			return ((CotizacionGRVO) cotizacion).getTasaTrabPesado();
		else if (tipoProceso == 'A')
			return ((CotizacionRAVO) cotizacion).getTasaTrabPesado();
		return 0;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;

			float tasa = getTasa(cotizante.getTipoProceso(), cotizante.getCotizacion());
			if (tasa == -1)
				tasa = 0;
			return valida(tasa, "" + tasa, cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN530::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN530(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN530(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN530(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}

}
