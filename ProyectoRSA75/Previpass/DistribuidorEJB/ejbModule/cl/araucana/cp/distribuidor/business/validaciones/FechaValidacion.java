package cl.araucana.cp.distribuidor.business.validaciones;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class FechaValidacion extends Validacion
{
	private static Logger log = Logger.getLogger(FechaValidacion.class);

    public int valida(CotizanteVO cotizante)
	{
		return this.COD_CUMPLE_VALIDACION;
	}

    public int validaFromWEB(CotizanteVO cotizante)
	{
		return this.COD_CUMPLE_VALIDACION;
	}

	public static Date validaFormato(String fecha)
	{
		Date d = null;
		try 
		{
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd_MM_yyyy");
			formatoFecha.setLenient (false); // Debe hacer esto gc.set (GregorianCalendar. ANNO, 2003); 
			fecha = fecha.replace(' ', '_').replace('-', '_').replace('/', '_');
			String[] componentes = fecha.split("_");
			if (componentes.length < 3) {
	            log.info("FECHA:" + fecha + ":mal:faltan separadores::");
	            return null;
			}
			int agno = Integer.parseInt(componentes[2]);
			int mes = Integer.parseInt(componentes[1]);
			int dia = Integer.parseInt(componentes[0]);
			if (agno<1998) {
	            log.info("FECHA:" + fecha + ":mal:agno invalido::");
	            return null;
			}
			if (mes<1 || mes>12) {
	            log.info("FECHA:" + fecha + ":mal:mes invalido::");
	            return null;
			}
			Calendar cal = Calendar.getInstance();
			cal.set(agno, mes-1, 1);
			if (dia<1 || dia>cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
	            log.info("FECHA:" + fecha + ":mal:dia invalido::");
	            return null;
			}
            d = formatoFecha.parse(fecha);
        } catch (Exception e) 
        {
            log.info("FECHA:" + fecha + ":mal:" + e.getMessage() + "::");
            return null;
        }
        return d; 
	}
	
	public static boolean fechaEnMes(Date fecha, int diasXMes, String periodo)
	{
		// Esta regla de validación fue descartada por La Araucana.
		return true;
		
		/*
		try
		{	
			//log.info("\n\nFechaValidacion:fechaEnMes: periodo:" + periodo + ":diasXMes:" + diasXMes + ":fecha:" + fecha.getTime() + "::");
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMM/dd");
			formatoFecha.setLenient (false); // Debe hacer esto gc.set (GregorianCalendar. ANNO, 2003); 
			Date d = formatoFecha.parse(periodo + "/01", new ParsePosition(0));
			//log.info("inicio mes calculado:" + d.getTime() + ":" + d + "::");
			if (fecha.before(d))
				return false;

			d = formatoFecha.parse(periodo + "/" + (diasXMes < 10 ? "0" : "") + diasXMes, new ParsePosition(0));
			//log.info("fin mes calculadod2:" + d.getTime() + ":" + d + "::");
			if (fecha.after(d))
				return false;
		} catch (Exception e)
		{
            log.error("FECHA:" + fecha + ":periodo:" + periodo, e);
			return false;
		}
		return true;
		*/
	}

	public FechaValidacion(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}
}
