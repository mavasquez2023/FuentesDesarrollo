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

public class VN500 extends Validacion
{
	private static Logger log = Logger.getLogger(VN500.class);

	/*
	 * 1 parametro = VN500: monto ahorro APV 4, requiere: 490
	 * 
	 *		1713: Monto APV debe existir si existe Codigo de Entidad APV 
	 *		1723: Monto APV invalido
	 *		1733: Monto APV debe ser menor que tope
	 * 		2353: CODIGO ENTIDAD APV DEBE EXISTIR SI EXISTE MONTO APV
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
			ConceptoVO c = (ConceptoVO) this.parametros.get(0);
			this.mensaje = c.getNombre();
			int valor = asignaValor(c.getValor());

			if (valor < 0)
			{
				if(this.logear) log.info("validacion VN500 ERR: Ahorro invalido:" + c.getValor() + "::");
				return 1720 + cotizante.getApvList().size() - 1;//Monto APV invalido
			}
			apv.setAhorro(valor);
			
			if (apv.getIdEntidadApv() == Constants.SIN_APV && valor > 0)
			{
				if(this.logear) log.info("validacion VN500 ERR: Ahorro APV mayor a cero:" + valor + ":pero no se informa entidad:");
				return 2350 + cotizante.getApvList().size() - 1; //CODIGO ENTIDAD APV DEBE EXISTIR SI EXISTE MONTO APV
			}
			
			if (!this.parametrosNegocio.containsKey("" + Constants.PARAM_TOPE_APV) || !this.parametrosNegocio.containsKey("" + Constants.PARAM_UF_ACTUAL))
				return this.SIN_PARAM_NEGOCIO;
			int topeAPV = Math.round((new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_APV)).floatValue() * new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue()));
			//TODO ojo con la validación del tope, debiera ser la suma de todos los apv, no solo del que se está chequeando
			if (valor > topeAPV)
			{
				if(this.logear) log.info("validacion VN500 ERR: Ahorro APV:" + valor + ": mayor a tope:" + topeAPV + "::");
				return 1730 + cotizante.getApvList().size() - 1;//Monto APV debe ser menor que tope
			}
			
			if (apv.getIdEntidadApv() == Constants.APV_INVALIDO)//se informo entidad
			{	
				if(this.logear) log.info("validacion VN500 OK con APV_INVALIDO-->SIN APV");
				apv.setIdEntidadApv(Constants.SIN_APV);
				return this.COD_CUMPLE_VALIDACION;
			}
			
			if (apv.getIdEntidadApv() != Constants.SIN_APV && valor == 0)//se informo entidad sin monto
			{
				if(this.logear) log.info("validacion VN500 ERR: Ahorro debe existir:" + c.getValor() + "::");
				return 1710 + cotizante.getApvList().size() - 1;//Monto APV debe existir si existe Codigo de Entidad APV
			}

			if (apv.getIdEntidadApv() == Constants.SIN_APV && valor == 0)
			{
				if(this.logear) log.info("validacion VN500 OK:");
				return this.COD_CUMPLE_VALIDACION;
			}

			if (this.logear)
				log.info("validacion VN500 OK: Monto APV4:" + valor + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN500::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			ApvVO apv = cotizante.getApv(3);
			if (apv != null)
			{
				if (apv.getAhorro() < 0)
				{
					if (this.logear)
						log.info("validacion VN500 ERROR Ahorro invalido:" + apv.getAhorro() + "::");
					return 172;// Monto APV invalido
				} else if (apv.getAhorro() == 0)
				{
					if (this.logear)
						log.info("validacion VN500 ERROR Ahorro debe existir:" + apv.getAhorro() + "::");
					return 171;// Monto APV debe existir si existe Codigo de Entidad APV
				}
				int topeAPV = Math.round(new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_APV)).floatValue() * new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue());
//				int topeAPV = new Integer((String) this.parametrosNegocio.get("" + Constants.PARAM_TOPE_APV)).intValue();
//				topeAPV *= new Float((String) this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();

				if (apv.getAhorro() > topeAPV)
				{
					if (this.logear)
						log.info("validacion VN500 ERR: Ahorro APV mayor a tope:" + topeAPV + "::");
					return 173;// Monto APV debe ser menor que tope
				}
			}

			this.resultado = "";
			if (this.logear)
				log.info("validacion VN500 OK:");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN500::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN500(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN500(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN500(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
