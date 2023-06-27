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

public class VN260 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN260.class);
	/*
	 * 1 parametro = VN260: adicional salud, totalSalud = pactadaSalud = obligatoria + adicional
	 * 
	 * Mensajes
	 * 		141: Monto Cotizacion Adicional de Salud invalido
	 * 		350: MONTO COTIZACION PACTADA DE SALUD INCORRECTO
	 * 		351: SALUD ADICIONAL NO CORRESPONDE SI AFILIADO A FONASA
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int monto = asignaValor(c.getValor());
			if (cotizante.isIsapre())
				return valida(monto, c.getValor(), cotizante);

			return validaCero(monto, c.getValor(), cotizante);
		} catch(Exception e)
		{
			log.error("error validacion VN260::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int validaCero(int monto, String valor, CotizanteVO cotizante) 
	{
		CotizacionREVO cotiz = (CotizacionREVO)cotizante.getCotizacion();	
		cotiz.setSaludAdicional(monto);
		if (monto != 0)
		{
			if (this.logear)
				log.info("validacion VN260 ERR: en FONASA monto adicional DEBE ser cero, valor recibido:" + valor + "::");
    		return 351;//SALUD ADICIONAL NO CORRESPONDE SI AFILIADO A FONASA
		}
		
		return this.COD_CUMPLE_VALIDACION;
	}

	private int valida(int monto, String valor, CotizanteVO cotizante)
	{
		CotizacionREVO cotiz = (CotizacionREVO)cotizante.getCotizacion();
		
		if (cotizante.getIdEntSaludReal()== Constants.ID_SALUD_NINGUNA)
		{
			monto = 0;
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN260 OK: Adicional Salud mueve 0 Isapre = ninguna :" );
			return this.COD_CUMPLE_VALIDACION;	
		}
		
		
		if (monto < 0)
		{
			cotiz.setSaludAdicional(0);
			if (this.logear)
				log.info("validacion VN260 ERR:adicional Salud invalido, valor recibido:" + valor + "::");
    		return 141;//Monto Cotizacion Adicional de Salud invalido (no es numero ni vacio)
		}
		cotiz.setSaludAdicional(monto);
		if ( (cotiz.getSaludPactado() > 0) && ((cotiz.getSaludPactado() - cotiz.getSaludObligatorio()) != monto) ) 
		{
			if (this.logear)
				log.info("validacion VN260 ERR:adicional Salud debe ser la diferencia entre la cotizacion pactada y la cotizacion obligatoria: debe ser: " +(cotiz.getSaludPactado() - cotiz.getSaludObligatorio())+ ": valor recibido:" + valor + "::");
			//Si esta condición se cumple, el VN250 NO arrojó ningún error.
			//Se colocó este IF para evitar que apareciese el error 140 y el 350 a la vez.
			if (!(cotiz.getSaludPactado() < cotiz.getSaludObligatorio()))
				return 350;//MONTO COTIZACION PACTADA DE SALUD INCORRECTO
		}
		if (this.logear)
			log.info("validacion VN260 OK:adicionalSalud:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";
			int monto = ((CotizacionREVO)cotizante.getCotizacion()).getSaludAdicional();
			if (cotizante.isIsapre())
				return valida(monto, "" + monto, cotizante);

			return validaCero(monto, "" + monto, cotizante);
		} catch(Exception e)
		{
			log.error("error validacion VN260::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN260(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN260(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN260(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
