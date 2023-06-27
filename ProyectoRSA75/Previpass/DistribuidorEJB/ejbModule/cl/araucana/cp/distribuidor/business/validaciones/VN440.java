package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN440 extends Validacion
{
	private static Logger log = Logger.getLogger(VN440.class);
	/*
	 * 1 parametro = VN440: monto ahorro APV 1, requiere: 430
	 * 
	 * Mensajes
	 *		1710: Monto APV debe existir si existe Codigo de Entidad APV 
	 *		1720: Monto APV invalido
	 *		1730: Monto APV debe ser menor que tope
	 * 		2350: CODIGO ENTIDAD APV DEBE EXISTIR SI EXISTE MONTO APV
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
			ApvVO apv = cotizante.getApv();
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int valor = asignaValor(c.getValor());

			if (valor < 0)
			{
				if(this.logear) log.info("validacion VN440 ERR: Ahorro invalido:" + c.getValor() + "::");
				return 1720 + cotizante.getApvList().size() - 1;//Monto APV invalido
			}
			apv.setAhorro(valor);
			
			if (apv.getIdEntidadApv() == Constants.SIN_APV && valor > 0)
			{
				if(this.logear) log.info("validacion VN440 ERR: Ahorro APV mayor a cero:" + valor + ":pero no se informa entidad:");
				return 2350 + cotizante.getApvList().size() - 1; //CODIGO ENTIDAD APV DEBE EXISTIR SI EXISTE MONTO APV
			}
			
			if (!this.parametrosNegocio.containsKey("" + Constants.PARAM_TOPE_APV) || !this.parametrosNegocio.containsKey("" + Constants.PARAM_UF_ACTUAL))
				return this.SIN_PARAM_NEGOCIO;
			int topeAPV = Math.round((new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_APV)).floatValue() * new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue()));
			//TODO ojo con la validación del tope, debiera ser la suma de todos los apv, no solo del que se está chequeando
			if (valor > topeAPV)
			{
				if(this.logear) log.info("validacion VN440 ERR: Ahorro APV:" + valor + ": mayor a tope:" + topeAPV + "::");
				return 1730 + cotizante.getApvList().size() - 1;//Monto APV debe ser menor que tope
			}
			
			if (apv.getIdEntidadApv() == Constants.APV_INVALIDO)//se informo entidad
			{	
				if(this.logear) log.info("validacion VN440 OK con APV_INVALIDO-->SIN APV");
				apv.setIdEntidadApv(Constants.SIN_APV);
				return this.COD_CUMPLE_VALIDACION;
			}
			
			if (apv.getIdEntidadApv() != Constants.SIN_APV && valor == 0)//se informo entidad sin monto
			{
				if(this.logear) log.info("validacion VN440 ERR: Ahorro debe existir:" + c.getValor() + "::");
				return 1710 + cotizante.getApvList().size() - 1;//Monto APV debe existir si existe Codigo de Entidad APV
			}

			if (apv.getIdEntidadApv() == Constants.SIN_APV && valor == 0)
			{
				if(this.logear) log.info("validacion VN440 OK:");
				return this.COD_CUMPLE_VALIDACION;
			}
			
			if (this.logear) log.info("validacion VN440 OK: Monto APV1:" + valor + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN440::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			ApvVO apv = cotizante.getApv(0);
			if (apv != null)
			{
				if (apv.getAhorro() < 0)
				{
					if(this.logear) log.info("validacion VN440 ERR: Ahorro invalido:" + apv.getAhorro() + "::");
					return 1720;//Monto APV invalido
				} else if (apv.getAhorro() == 0)
				{
					if(this.logear) log.info("validacion VN440 ERR: Ahorro debe existir:" + apv.getAhorro() + "::");
					return 1710;//Monto APV debe existir si existe Codigo de Entidad APV 
				}

				int topeAPV = Math.round((new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_APV)).floatValue() * new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue()));
//				float topeAPV = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_APV)).floatValue();
//				topeAPV *= new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();
	
				if (apv.getAhorro() > topeAPV)
				{
					if(this.logear) log.info("validacion VN440 ERR: Ahorro APV mayor a tope:" + topeAPV + "::");
					return 1730;//Monto APV debe ser menor que tope
				}
				if (this.logear) log.info("validacion VN440 OK: Monto APV:" + apv.getAhorro() + "::");
			}

			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN440::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN440(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN440(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN440(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
