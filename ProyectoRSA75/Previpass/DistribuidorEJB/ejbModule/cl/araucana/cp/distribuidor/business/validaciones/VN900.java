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
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;

public class VN900 extends Validacion
{
	private static Logger log = Logger.getLogger(VN900.class);

	/*
	 * 1 parametro = VN900: entidad recaudadora APVC
	 * 
	 * Mensajes
	 * 		300: //Codigo entidad APVC no corresponde a valores posibles
	 * 		301: //Codigo entidad APVC debe existir
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (!(cotizante.getCotizacion() instanceof CotizacionREVO))
				return this.COD_CUMPLE_VALIDACION;

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			// marco cambio clase para soportar el - de rut 
			String codigo = Utils.transformaCodEnt(c.getValor());
			
			if (codigo.equals(""))
			{
				if (this.logear) 
					log.info("validacion VN900:codigo recibido vacio, se asigna codigo 'sin APV'::");
				cotizante.setIdEntidadAPVCReal(Constants.SIN_APV);
				return this.COD_CUMPLE_VALIDACION;
			}
			
    		MapeoVO mApv = (MapeoVO)this.mapeoApv.get(codigo.trim());
    		if(mApv == null)
    			cotizante.setIdEntidadAPVCReal(Constants.SIN_APV);   	
        	else
        	{
        		cotizante.setIdEntidadAPVCReal(mApv.getId());
        		cotizante.setIdEntidadAPVC(codigo);
        	}
			if (this.logear)
				log.info("validacion VN900 OK:" + cotizante.getIdEntidadAPVCReal() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN900::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (!(cotizante.getCotizacion() instanceof CotizacionDCVO))
				return this.COD_CUMPLE_VALIDACION;
			int idEntidadAPVC = cotizante.getIdEntidadAPVCReal();
			if (idEntidadAPVC != Constants.SIN_APV && this.session.get(EntidadApvVO.class, new Integer(idEntidadAPVC)) == null )
			{
				if (this.logear)
					log.info("validacion VN900 OK: Codigo debe existir::");
				return 301; //Codigo entidad APVC debe existir
			}
			if (this.logear)
				log.info("validacion VN900 OK:" + idEntidadAPVC + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN900::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN900(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN900(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN900(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
