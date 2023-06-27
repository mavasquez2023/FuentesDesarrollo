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

public class VN650 extends Validacion
{
	private static Logger log = Logger.getLogger(VN650.class);
	/*
	 * 1 parametro = VN650: fecha termino reliquidacion
	 * 
	 * Mensajes
	 * 		215: Fecha de Termino de reliquidacion invalida
	 * 		208: Fecha inicio debe ser menor o igual a Fecha termino
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
//			clillo 12/12/14 por Reliquidación, no se usa fecha término.
			
			/*if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
			
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			String fecha = c.getValor().trim();

        	Date date1 = asignaFecha(fecha);

        	if (date1 == null || fecha.equals(""))
        	{
    			if (this.logear)
    				log.info("validacion VN650 ERR:fecha termino reliquidacion invalida:" + fecha + "::");
        		return 215; //Fecha de Termino de reliquidacion invalida
        	}
        	CotizacionRAVO cotizacionRAVO = (CotizacionRAVO) cotizante.getCotizacion();
        	cotizacionRAVO.setTermino(new java.sql.Date(date1.getTime()));
        	if (cotizacionRAVO.getTermino().before(cotizacionRAVO.getInicio()))
        	{
    			if (this.logear)
    				log.info("validacion VN650 ERR:fecha termino:" + fecha + ":anterior a fecha inicio:" + cotizacionRAVO.getInicio().toString() + "::");
        		return 208; //Fecha inicio debe ser menor o igual a Fecha termino
        	}
        	
			if (this.logear)
				log.info("validacion VN650 OK:fecha termino reliquidacion:" + date1.toString() + "::");
			*/
			CotizacionRAVO cotizacionRAVO = (CotizacionRAVO) cotizante.getCotizacion();
        	cotizacionRAVO.setTermino(new java.sql.Date(new java.util.Date().getTime()));
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN650::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			/*
			CotizacionRAVO cotizacionRAVO = (CotizacionRAVO) cotizante.getCotizacion();
			if (cotizacionRAVO.getTermino().equals(new java.sql.Date(new Date(Constants.FECHA_DEFECTO_MILESIMAS).getTime())))
        	{
    			if (this.logear)
    				log.info("validacion VN650 ERR:fecha termino reliquidacion invalida:" + cotizacionRAVO.getTermino().toString() + "::");
        		return 215; //Fecha de Inicio de reliquidacion invalida
        	}
			if (cotizacionRAVO.getTermino().before(cotizacionRAVO.getInicio()))
        	{
    			if (this.logear)
    				log.info("validacion VN650 ERR:fecha termino:" + cotizacionRAVO.getTermino().toString() + ":anterior a fecha inicio:" + cotizacionRAVO.getInicio().toString() + "::");
        		return 208; //Fecha inicio debe ser menor o igual a Fecha termino
        	}
			
			if (this.logear)
				log.info("validacion VN650 OK:fecha termino reliquidacion:" + cotizacionRAVO.getTermino().toString() + "::");
			*/
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN650::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN650(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN650(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN650(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
