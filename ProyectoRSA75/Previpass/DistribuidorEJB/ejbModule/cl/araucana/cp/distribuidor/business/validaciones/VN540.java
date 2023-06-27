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

public class VN540 extends Validacion
{
	private static Logger log = Logger.getLogger(VN540.class);

	/*
	 * 1 parametro = VN540: id trabajo pesado
	 * 
	 * Mensajes 
	 * 176: Identificador de Trabajo Pesado invalido 
	 * 178: Identificador de Trabajo Pesado debe aparecer 
	 * 353: TASA DE TRABAJOS PESADOS DEBE APARECER
	 * 335: NO CORRESPONDE ID TASA TRAB.PESADO AL ESTAR ASOCIADO A INP
	 * 
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
			String valor = Utils.transforma(c.getValor());
			setNombreTrabajo(cotizante.getTipoProceso(), valor, cotizante.getCotizacion());

			return valida(cotizante, valor);
		} catch (Exception e)
		{
			log.error("error validacion VN540::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(CotizanteVO cotizante, String valor)
	{
		if (cotizante.getTipoPrevision() != Constants.TIPO_PREVISION_AFP)
		{
			if (!valor.equals(""))
			{
				if (this.logear)
					log.info("validacion VN540 ERR: Identificador de Trabajo Pesado NO debe aparecer::" + valor + "::");
				return 335; //NO CORRESPONDE ID TASA TRAB.PESADO AL ESTAR ASOCIADO A INP
			}
		} else
		{
			float tasa = getTasa(cotizante.getTipoProceso(), cotizante.getCotizacion());
			if (valor.equals("") && tasa != 0)
			{
				if (this.logear)
					log.info("validacion VN540 ERR: Identificador de Trabajo Pesado debe aparecer ::");
				return 178; // Identificador de Trabajo Pesado debe aparecer
			}
			if (!valor.equals("") && tasa == 0)
			{
				if (this.logear)
					log.info("validacion VN540 ERR: tasa de Trabajo Pesado debe aparecer ::");
				return 353;// TASA DE TRABAJOS PESADOS DEBE APARECER
			}
		}

		if (this.logear)
			log.info("validacion VN540 OK:TipoTrabPesado:" + valor + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	private void setNombreTrabajo(char tipoProceso, String nombre, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			((CotizacionREVO) cotizacion).setTipoTrabPesado(nombre);
		else if (tipoProceso == 'G')
			((CotizacionGRVO) cotizacion).setTipoTrabPesado(nombre);
		else if (tipoProceso == 'A')
			((CotizacionRAVO) cotizacion).setTipoTrabPesado(nombre);
	}

	private float getTasa(char tipoProceso, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			return ((CotizacionREVO) cotizacion).getTasaTrabPesado();
		else if (tipoProceso == 'G')
			return ((CotizacionGRVO) cotizacion).getTasaTrabPesado();
		return ((CotizacionRAVO) cotizacion).getTasaTrabPesado();
	}

	private String getTipoTrabPesado(char tipoProceso, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			return ((CotizacionREVO) cotizacion).getTipoTrabPesado();
		else if (tipoProceso == 'G')
			return ((CotizacionGRVO) cotizacion).getTipoTrabPesado();
		return ((CotizacionRAVO) cotizacion).getTipoTrabPesado();
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{

			this.resultado = "";
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;

			String valor = getTipoTrabPesado(cotizante.getTipoProceso(), cotizante.getCotizacion());

			return valida(cotizante, valor);
		} catch (Exception e)
		{
			log.error("error validacion VN540::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN540(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN540(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN540(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}

}
