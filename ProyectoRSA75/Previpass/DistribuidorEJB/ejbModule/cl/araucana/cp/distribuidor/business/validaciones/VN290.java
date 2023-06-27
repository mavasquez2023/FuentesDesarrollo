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

public class VN290 extends Validacion
{
	private static Logger log = Logger.getLogger(VN290.class);

	/*
	 * 1 parametro = VN290: prevision voluntaria prevision, si este valor es correcto entonces se transforma en APV
	 * 
	 * Mensajes 
	 * 1720: Monto APV invalido 
	 * 1730: Monto APV debe ser menor que tope 
	 * 3490: no se puede informar ahorro voluntario en INP
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			int monto = 0;
			this.resultado = "";
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO) this.parametros.get(0);
			this.mensaje = c.getNombre();
			monto = asignaValor(c.getValor());
			return valida(monto, c.getValor(), cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN290::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizanteVO cotizante)
	{
		if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP && monto != 0)
		{
			if (this.logear)
				log.info("validacion VN290 ERR:no se puede informar ahorro voluntario en INP::");
			return 349;// no se puede informar ahorro voluntario en INP
		}
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN290 ERR:pension voluntario: valor recibido invalido:" + valor + ": asigna 0:");
			cotizante.addApv(cotizante.getIdEntPensionReal(), 0);
			return 1720;// Monto APV invalido (no es numero ni vacio)
		}
		if (monto > 0)
		{
			cotizante.addApv(cotizante.getIdEntPensionReal(), monto);
			if (!this.parametrosNegocio.containsKey("" + Constants.PARAM_UF_ACTUAL) || !this.parametrosNegocio.containsKey("" + Constants.PARAM_TOPE_APV))
				return this.SIN_PARAM_NEGOCIO;
			//float ufActual = new Float((String) this.parametrosNegocio.get("UFActual")).floatValue();
			//float topeAhorroAPV = new Float((String) this.parametrosNegocio.get("topeAPV")).floatValue();

			float ufActual = new Float((String) this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();
			float topeAhorroAPV = new Float((String) this.parametrosNegocio.get("" + Constants.PARAM_TOPE_APV)).floatValue();
			
			if (monto > Math.round(ufActual * topeAhorroAPV))
			{
				if (this.logear)
					log.info("validacion VN290 ERR:pension voluntario: excede tope:" + monto + "::");
				return 1730;// Monto APV debe ser menor que tope (no es numero ni vacio)
			}
			if (this.logear)
				log.info("validacion VN290 OK:previsionVoluntaria(APV1):" + monto + "::");
		}
		this.resultado = "";
		if (this.logear)
			log.info("validacion VN290 OK:previsionVoluntaria(APV1):" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		/*
		 * esto se validara en lista APV, no aca
		 */
		try
		{
			if (cotizante.getTipoProceso() != 'R')
				return this.COD_CUMPLE_VALIDACION;
			if (this.logear)
				log.info("validacion VN290 OK: esto se valida en APVs");
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN290::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN290(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN290(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN290(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
