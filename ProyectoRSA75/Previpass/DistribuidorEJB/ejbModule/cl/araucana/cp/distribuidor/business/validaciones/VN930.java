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

public class VN930 extends Validacion
{
	private static Logger log = Logger.getLogger(VN930.class);

	/*
	 * 1 parametro= VN930: codigo contrato APVC: valida que si algun campo de APVC tiene valor, todos deben ser validos
	 * 
	 * Mensajes
	 * 		300: Codigo entidad APVC no corresponde a valores posibles
	 * 		301: Codigo entidad APVC debe existir
	 * 		302: Aporte empleador APVC debe ser numerico
	 * 		303: Aporte Empleador APVC debe existir
	 * 		304: Aporte trabajador APVC debe ser numerico
	 * 		305: Aporte trabajador APVC debe existir
	 * 		307: Numero de contrato APVC debe existir
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			String codigo = Utils.transforma(c.getValor()).trim();

			boolean existe = false;
			CotizacionREVO cotizacionREVO = (CotizacionREVO)cotizante.getCotizacion();
			cotizacionREVO.setApvcNumContr(codigo);
			if (this.logear)
				log.info("validacion VN930 verificando si hay APVC:idEnt:" + cotizante.getIdEntidadAPVCReal() + ":empl:" + cotizacionREVO.getApvcAporteEmpl() + ":trab:" + cotizacionREVO.getApvcAporteTrab() + ":codigo:" + codigo + "::");
			if (cotizante.getIdEntidadAPVCReal() != Constants.SIN_APV || 
					(cotizacionREVO.getApvcAporteEmpl() > 0 || cotizacionREVO.getApvcAporteEmpl() == -1)
					|| (cotizacionREVO.getApvcAporteTrab() > 0 || cotizacionREVO.getApvcAporteTrab() == -1)
					|| !codigo.equals(""))
				existe = true;

			if (existe)
			{				
				if (cotizante.getIdEntidadAPVCReal() == Constants.SIN_APV)
					return 301; //Codigo entidad APVC debe existir
				if (cotizacionREVO.getApvcAporteEmpl() < 0)
					return 303;	//Aporte Empleador APVC debe existir
				if (cotizacionREVO.getApvcAporteTrab() < 0)
					return 305;//Aporte trabajador APVC debe existir
				if (codigo.equals(""))
					return 307;//Numero de contrato APVC debe existir
			}

			if (this.logear)
				log.info("validacion VN930 OK:" + codigo + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN930::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			boolean existe = false;
			CotizacionREVO cotizacionREVO = (CotizacionREVO)cotizante.getCotizacion();
			String codigo = cotizacionREVO.getApvcNumContr().trim();
			if (cotizante.getIdEntidadAPVCReal() != Constants.SIN_APV || 
					cotizacionREVO.getApvcAporteEmpl() > 0 || cotizacionREVO.getApvcAporteEmpl() == -1
					|| cotizacionREVO.getApvcAporteTrab() > 0 || cotizacionREVO.getApvcAporteTrab() == -1
					|| !codigo.equals(""))
				existe = true;

			if (existe)
			{
				if (this.logear)
					log.info("validacion VN930 OK:a APVC: revisando valores:IdEntidadAPVC:" + cotizante.getIdEntidadAPVCReal() + 
							":AporteEmpl:" + cotizacionREVO.getApvcAporteEmpl() + ":AporteTrab:" + cotizacionREVO.getApvcAporteTrab() + 
							":NumContr:" + codigo + "::");
				if (cotizante.getIdEntidadAPVCReal() == Constants.SIN_APV)
				{
					if (this.logear)
						log.info("validacion VN930 ERR: Codigo entidad APVC debe existir::");
					return 301; //Codigo entidad APVC debe existir
				}
				if (cotizacionREVO.getApvcAporteEmpl() < 0)
				{
					if (this.logear)
						log.info("validacion VN930 ERR:Aporte Empleador APVC debe existir::");
					return 303;	//Aporte Empleador APVC debe existir
				}
				if (cotizacionREVO.getApvcAporteTrab() < 0)
				{
					if (this.logear)
						log.info("validacion VN930 ERR:Aporte trabajador APVC debe existir::");
					return 305;//Aporte trabajador APVC debe existir
				}
				if (codigo.equals(""))
				{
					if (this.logear)
						log.info("validacion VN930 ERR:Numero de contrato APVC debe existir::");
					return 307;//Numero de contrato APVC debe existir
				}
			}

			this.resultado = "";
			if (this.logear)
				log.info("validacion VN930 OK:" + cotizacionREVO.getApvcNumContr() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN930::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN930(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN930(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN930(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
