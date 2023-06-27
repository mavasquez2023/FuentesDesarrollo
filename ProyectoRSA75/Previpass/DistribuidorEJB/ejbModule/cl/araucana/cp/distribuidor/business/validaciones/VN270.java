package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;

public class VN270 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN270.class);
	/*
	 * 1 parametro = VN270: total salud
	 * 
	 * Mensajes	 
	 * 		142: Monto cotizacion Total de Salud invalido
	 * 		143: Monto cotizacion Total de Salud incorrecto
	 * 		344: MONTO COTIZACION TOTAL DE SALUD INVALIDO
	 * 		345: MONTO COTIZACION TOTAL DE SALUD INCORRECTO
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'D')
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			OpcionProcVO opcionProcVO = (OpcionProcVO)this.parametrosNegocio.get("opcionProcesos");
			if (opcionProcVO == null)
				return this.SIN_PARAM_NEGOCIO;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();

			if (opcionProcVO.getCalcTotSalud() && cotizante.isIsapre())
				return calcular(cotizante);
			else if (cotizante.isIsapre())
				return validar(asignaValor(c.getValor()), c.getValor(), cotizante);
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN270::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int validar(int monto, String valor, CotizanteVO cotizante) 
	{
		if (cotizante.getIdEntSaludReal()== Constants.ID_SALUD_NINGUNA)
		{
			monto = 0;
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN270 OK:total salud, mueve 0 Isapre = ninguna :" );
			return this.COD_CUMPLE_VALIDACION;
		}
		
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN270 ERR:total salud, valor recibido:" + valor + "::");
			if(cotizante.getIdEntSaludReal() != Constants.ID_FONASA)
				return 142;//Monto cotizacion Total de Salud invalido (no es numero ni vacio)
			return 344; //MONTO COTIZACION TOTAL DE SALUD INVALIDO
		}

		int montoCalculado = cotizante.getCotizacion().setGetSumaSalud();
		if (monto != montoCalculado)
		{
			if (this.logear)
				log.info("validacion VN270 ERR:total salud, valor incorrecto recibido:" + monto + ":valor calculado:" + montoCalculado + "::");
			if(cotizante.getIdEntSaludReal() != Constants.ID_FONASA)
				return 143;//Monto cotizacion Total de Salud incorrecto
			return 345; //MONTO COTIZACION TOTAL DE SALUD INCORRECTO
		}
		if(montoCalculado > Constants.TOPE_TOTAL_BD)
		{
			if (this.logear)
				log.info("validacion VN270 ERR:total salud excede el limite de la BD::");
			if(cotizante.getIdEntSaludReal() != Constants.ID_FONASA)
				return 330;//Monto cotizacion Total de Salud excede la cantidad de digitos de la BD
			return 346;
		}
		if (this.logear)
			log.info("validacion VN270 OK:total salud:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	private int calcular(CotizanteVO cotizante) 
	{
		int monto = cotizante.getCotizacion().setGetSumaSalud();
		if (this.logear)
			log.info("validacion VN270 OK:total salud calculado (opcprocesos):" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";
			if (cotizante.isIsapre())
			{
				OpcionProcVO opcionProcVO = (OpcionProcVO)this.parametrosNegocio.get("opcionProcesos");
				if (opcionProcVO == null)
					return this.SIN_PARAM_NEGOCIO;
				int monto = cotizante.getCotizacion().getTotalSalud();

				if (opcionProcVO.getCalcTotSalud())
					return calcular(cotizante);

				return validar(monto, "" + monto, cotizante);
			}
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN270::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN270(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN270(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN270(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
