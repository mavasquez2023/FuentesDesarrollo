package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.Calendar;
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

public class VN620 extends Validacion
{
	private static Logger log = Logger.getLogger(VN620.class);
	/*
	 * 1 parametro = VN620: fecha termino gratificacion
	 * 
	 * Mensajes
	 * 		213: Fecha de Termino de Gratificacion invalida
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
				date1 = this.fechaUltimoDia(fecha); //entra mmyyyy y devuelve una fecha equivalente al último día del mes (mm) del año yyyy
			}else{
				date1 = asignaFecha(fecha);	
			}

        	CotizacionGRVO cotizacionGRVO = (CotizacionGRVO) cotizante.getCotizacion();
        	if (date1 == null || fecha.equals(""))
        	{
    			if (this.logear)
    				log.info("validacion VN620 ERR:fecha termino gratificacion invalida:" + fecha + "::");
            	cotizacionGRVO.setTermino(new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS));
        		return 213; //Fecha de Termino de Gratificacion invalida
        	}
        	cotizacionGRVO.setTermino(new java.sql.Date(date1.getTime()));
        	if (cotizacionGRVO.getTermino().equals(new java.sql.Date(new Date(Constants.FECHA_DEFECTO_MILESIMAS).getTime())))
        	{
    			if (this.logear)
    				log.info("validacion VN620 ERR:fecha termino gratificacion invalida:" + cotizacionGRVO.getTermino().toString() + "::");
        		return 212; //Fecha de Inicio de Gratificacion invalida
        	}
			if (cotizacionGRVO.getTermino().before(cotizacionGRVO.getInicio()))
        	{
    			if (this.logear)
    				log.info("validacion VN620 ERR:fecha termino:" + cotizacionGRVO.getTermino().toString() + ":anterior a fecha inicio:" + cotizacionGRVO.getInicio().toString() + "::");
        		return 208; //Fecha inicio debe ser menor o igual a Fecha termino
        	}

			if (this.logear)
				log.info("validacion VN620 OK:fecha termino gratificacion:" + date1.toString() + "::");
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN620::", e);
			return this.CAIDA_SISTEMA;
		}
	}
	
	/**
	 * entra mmyyyy y devuelve una fecha equivalente al último día del mes (mm) del año yyyy
	 * @param mmyyyy
	 * @return
	 */
	private Date fechaUltimoDia(String mmyyyy){
		
		Date d = null;
		
		try{

			int mes = Integer.parseInt(mmyyyy.substring(0, 2));
			int agno = Integer.parseInt(mmyyyy.substring(2, 6));
			
			if(mes < 1 || mes > 12)
				return null;
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, 1); //Primer día del mes
			cal.set(Calendar.MONTH, (mes - 1) );
			cal.set(Calendar.YEAR, agno);
			
			cal.add(Calendar.MONTH, 1); //Sumo un mes
			cal.add(Calendar.DAY_OF_MONTH, -1); //Resto un día
			
			//Ahora en cal tengo el último día del mes
			
			d = cal.getTime();
			
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
			if (cotizacionGRVO.getTermino().equals(new java.sql.Date(new Date(Constants.FECHA_DEFECTO_MILESIMAS).getTime())))
        	{
    			if (this.logear)
    				log.info("validacion VN620 ERR:fecha termino gratificacion invalida:" + cotizacionGRVO.getTermino().toString() + "::");
        		return 212; //Fecha de Inicio de Gratificacion invalida
        	}
			if (cotizacionGRVO.getTermino().before(cotizacionGRVO.getInicio()))
        	{
    			if (this.logear)
    				log.info("validacion VN620 ERR:fecha termino:" + cotizacionGRVO.getTermino().toString() + ":anterior a fecha inicio:" + cotizacionGRVO.getInicio().toString() + "::");
        		return 208; //Fecha inicio debe ser menor o igual a Fecha termino
        	}

			this.resultado = "";
			if (this.logear)
				log.info("validacion VN620 OK:fecha termino gratificacion:" + cotizacionGRVO.getTermino().toString() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN620::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN620(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN620(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN620(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
