package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;


public class NumeroValidacion extends Validacion 
{
	public int valida(CotizanteVO cotizante)
	{
		return this.COD_CUMPLE_VALIDACION;
	}

    public int validaFromWEB(CotizanteVO cotizante)
	{
		return this.COD_CUMPLE_VALIDACION;
	}

	public static Integer valida(String value)
	{
		Integer n = null;
		try
		{
			n = new Integer(value);
		} catch (NumberFormatException e)
		{
			return null;
		} catch (Exception e)
		{
			return null;
		}
		return n;
	}

	public static Float validaF(String value)
	{
		Float n = null;
		try
		{
			n = new Float(value);
		} catch (NumberFormatException e)
		{
			return null;
		} catch (Exception e)
		{
			return null;
		}
		return n;
	}

	public NumeroValidacion(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}
}
