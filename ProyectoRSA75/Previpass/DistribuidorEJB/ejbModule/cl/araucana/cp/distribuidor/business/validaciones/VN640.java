package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN640 extends Validacion
{
	private static Logger log = Logger.getLogger(VN640.class);
	/*
	 * 1 parametro = VN640: fecha inicio reliquidacion
	 * 
	 * Mensajes
	 * 		214: Fecha de Inicio de Gratificacion invalida
	 * 		208: Fecha inicio debe ser menor o igual a Fecha termino
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			String fecha = c.getValor().trim();

        	Date date1 = asignaFecha(fecha);

        	if (date1 == null || fecha.equals(""))
        	{
    			if (this.logear)
    				log.info("validacion VN640 ERR:fecha inicio reliquidacion invalida:" + fecha + "::");
        		return 214; //Fecha de Inicio de reliquidacion invalida
        	}

        	CotizacionRAVO cotizacionRAVO = (CotizacionRAVO) cotizante.getCotizacion();
        	cotizacionRAVO.setInicio(new java.sql.Date(date1.getTime()));

			if (this.logear)
				log.info("validacion VN640 OK:fecha inicio reliquidacion:" + date1.toString() + "::");
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN640::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			CotizacionRAVO cotizacionRAVO = (CotizacionRAVO) cotizante.getCotizacion();
			if (cotizante.getPeriodo()==0 && (cotizacionRAVO.getInicio().equals(new java.sql.Date(new Date(Constants.FECHA_DEFECTO_MILESIMAS).getTime())) || cotizacionRAVO.getInicio().toString().equals("1969-12-31")))
        	{
    			if (this.logear)
    				log.info("validacion VN640 ERR:fecha inicio reliquidacion invalida:" + cotizacionRAVO.getInicio().toString() + "::");
        		return 214; //Fecha de Inicio de reliquidacion invalida
        	}
			if (String.valueOf(cotizante.getPeriodo()).length()<6 )
        	{
    			if (this.logear)
    				log.info("validacion VN640 ERR:fecha inicio reliquidacion invalida:" + cotizacionRAVO.getInicio().toString() + "::");
        		return 214; //Fecha de Inicio de reliquidacion invalida
        	}
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN640 OK:fecha inicio reliquidacion:" + cotizacionRAVO.getInicio().toString() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN640::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN640(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN640(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN640(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
