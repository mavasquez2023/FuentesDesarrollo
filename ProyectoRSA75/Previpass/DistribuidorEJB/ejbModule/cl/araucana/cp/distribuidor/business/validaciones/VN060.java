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
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;

public class VN060 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN060.class);
	/*
	 * 1 parametro = VN060: Sucursal del trabajador
	 * 
	 * Mensajes
	 * 		109: Codigo Sucursal invalido
	 * 		110: Codigo Sucursal no aparece en mapeo
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
			Utils utils = new Utils();

			/**
			 * Asepulveda 23-04-2010. Remplaza método "procesaTexto" por método "procesaCodigoSucursal"
			 */
			String codigo = Utils.transformaCodEnt(c.getValor() != null ? c.getValor().toUpperCase() : null);
			if (utils.procesaCodigoSucursal(true, codigo) != Constants.TEXTO_OK)
			{
    			if (this.logear)
    				log.info("validacion VN060 ERR: Sucursal:valor recibido vacio:" + codigo + "::");
        		return 109; //Codigo Sucursal invalido
			}
			String valor = utils.getValor();
			cotizante.setIdSucursal(valor);
        	if (!this.mapeoSucursales.containsKey(valor))
        	{
    			if (this.logear)
    				log.info("validacion VN060 ERR: Sucursal:valor recibido:" + valor + "::");
        		return 110; //Codigo Sucursal no aparece en mapeo
        	}
			if (this.logear)
				log.info("validacion VN060 OK: Sucursal:" + cotizante.getIdSucursal() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN060::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (this.session.get(SucursalVO.class, new SucursalVO(cotizante.getRutEmpresa(), cotizante.getIdSucursal())) == null)
			{
    			if (this.logear)
    				log.info("validacion VN060 ERR: Sucursal:valor recibido:" + cotizante.getIdSucursal() + "::");
    			return 110; //Codigo Sucursal no aparece en mapeo
			}
			if (this.logear)
				log.info("validacion VN060 OK: Sucursal:" + cotizante.getIdSucursal() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN060::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN060(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN060(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN060(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
