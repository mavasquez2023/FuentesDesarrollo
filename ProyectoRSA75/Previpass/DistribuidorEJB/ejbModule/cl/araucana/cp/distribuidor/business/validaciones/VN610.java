package cl.araucana.cp.distribuidor.business.validaciones;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;

public class VN610 extends Validacion
{
	private static Logger log = Logger.getLogger(VN610.class);
	/*
	 * 1 parametro = VN610: fecha inicio gratificacion
	 * 
	 * Mensajes
	 * 		212: Fecha de Inicio de Gratificacion invalida
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
			
			Date date1 = new Date();
			GrupoConvenioVO grupoConvenioVO = (GrupoConvenioVO)this.parametrosNegocio.get("grupoConvenio");
			if(grupoConvenioVO.isPrevired()){
				date1 = this.fechaPrimerDia(fecha); //entra mmyyyy y devuelve una fecha equivalente al primer día del mes (mm) del año yyyy
			}else{
				date1 = asignaFecha(fecha);	
			}
			
        	CotizacionGRVO cotizacionGRVO = (CotizacionGRVO) cotizante.getCotizacion();
        	if (date1 == null || fecha.equals(""))
        	{
    			if (this.logear)
    				log.info("validacion VN610 ERR:fecha inicio gratificacion invalida:" + fecha + "::");
    			cotizacionGRVO.setInicio(new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS));
        		return 212; //Fecha de Inicio de Gratificacion invalida
        	}

        	cotizacionGRVO.setInicio(new java.sql.Date(date1.getTime()));
        	if (cotizacionGRVO.getInicio().equals(new java.sql.Date(new Date(Constants.FECHA_DEFECTO_MILESIMAS).getTime())))
        	{
    			if (this.logear)
    				log.info("validacion VN610 ERR:fecha inicio gratificacion invalida:" + cotizacionGRVO.getInicio().toString() + "::");
        		return 212; //Fecha de Inicio de Gratificacion invalida
        	}

			if (this.logear)
				log.info("validacion VN610 OK:fecha inicio gratificacion:" + date1.toString() + "::");
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN610::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	/**
	 * entra mmyyyy y devuelve una fecha equivalente al primer día del mes (mm) del año yyyy
	 * @param mmyyyy
	 * @return
	 */
	private Date fechaPrimerDia(String mmyyyy){
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat("ddMMyyyy");
		Date d = null;
		
		try{
			
			d = formatoFecha.parse("01"+mmyyyy);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return d;
	}
	
	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			CotizacionGRVO cotizacionGRVO = (CotizacionGRVO) cotizante.getCotizacion();
			if (cotizacionGRVO.getInicio().equals(new java.sql.Date(new Date(Constants.FECHA_DEFECTO_MILESIMAS).getTime())))
        	{
    			if (this.logear)
    				log.info("validacion VN610 ERR:fecha inicio gratificacion invalida:" + cotizacionGRVO.getInicio().toString() + "::");
        		return 212; //Fecha de Inicio de Gratificacion invalida
        	}

			this.resultado = "";
			if (this.logear)
				log.info("validacion VN610 OK:fecha inicio gratificacion:" + cotizacionGRVO.getInicio().toString() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN610::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN610(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN610(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN610(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
