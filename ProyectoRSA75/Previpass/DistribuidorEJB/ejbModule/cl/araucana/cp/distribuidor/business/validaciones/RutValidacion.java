package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class RutValidacion extends Validacion
{
	private static Logger logger = Logger.getLogger(RutValidacion.class);
	public int valida(CotizanteVO cotizante)
	{
		return this.COD_CUMPLE_VALIDACION;
	}

    public int validaFromWEB(CotizanteVO cotizante)
	{
		return this.COD_CUMPLE_VALIDACION;
	}

	public static boolean valida(String rut)
	{
		try
		{
			char dv = rut.toUpperCase().charAt(rut.length() - 1);
			Integer tmp = NumeroValidacion.valida(rut.substring(0, rut.length() - 1));
			if (tmp == null)
				return false;
			int M = 0, S = 1, T = tmp.intValue();
			for(; T != 0; T /= 10)
				S = (S + T % 10 * (9 - M++ % 6)) % 11;
			if (dv == (char)(S != 0 ? S + 47 : 75))
				return true;
			return false;
		} catch (Exception ex)
		{
			return false;
		}
	}

	public static String transformConSinGuion(String rut)
	{
		int pos = 0;
		if ((pos = rut.indexOf('-')) != -1)
			return rut.substring(0, pos);
		if (!rut.equals(""))
			return rut.substring(0, rut.length() - 1);
		return "";
	}

	public static void main(String[] args) 
	{
		String rut = "12345678-9";
		logger.info("con:"  + RutValidacion.transformConSinGuion(rut) + "::");
		rut = "123456789";
		logger.info("sin:"  + RutValidacion.transformConSinGuion(rut) + "::");
		rut = "";
		logger.info("nada:"  + RutValidacion.transformConSinGuion(rut) + "::");
		rut = "      ";
		logger.info("espacios:"  + RutValidacion.transformConSinGuion(rut) + "::");
		rut = "12345678-9";
		rut = rut.replaceAll("-", "");
		logger.info("rut:" + valida(rut));
		rut = "9705410k";
		rut = rut.replaceAll("-", "");
		logger.info("rut:" + valida(rut));
		rut = "9705410K";
		rut = rut.replaceAll("-", "");
		logger.info("rut:" + valida(rut));
		rut = "9705410-k";
		rut = rut.replaceAll("-", "");
		logger.info("rut:" + valida(rut));
		rut = "9705410-K";
		rut = rut.replaceAll("-", "");
		logger.info("rut:" + valida(rut));
	}

	public RutValidacion(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}
}
          