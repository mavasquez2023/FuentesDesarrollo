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

public class VN250 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN250.class);
	/*
	 * 1 parametro = VN250: cotizacion pactada salud: totalSalud = pactadaSalud = obligatoria + adicional
	 * 
	 * Mensajes
	 * 		139: Monto Cotizacion Pactada de Salud invalido
	 * 		140: Monto Cotiz. Pactada Salud menor a Cotiz. Obligatoria
	 * 		350: Monto Salud Pactado incorrecto
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "E";
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int monto = asignaValor(c.getValor());
			if (cotizante.getIdEntSaludReal() != Constants.ID_FONASA) //si es ISAPRE
				return valida(monto, c.getValor(), cotizante);

			this.resultado = "";
			return validaCero(monto, c.getValor(), cotizante);
		} catch(Exception e)
		{
			log.error("error validacion VN250::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizanteVO cotizante)
	{
		CotizacionREVO cotiz = (CotizacionREVO)cotizante.getCotizacion();
		
		if (cotizante.getIdEntSaludReal()== Constants.ID_SALUD_NINGUNA)
		{
			monto = 0;
			if (this.logear)
				log.info("validacion VN250 OK:pactada Salud mueve 0 Isapre = Ninguna :");		
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		}
		
		
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN250 ERR:pactado Salud, valor recibido:" + valor + "::");
				return 139; //Monto Cotización Pactada de Salud inválido (no es número ni vacío)
		}
		cotiz.setSaludPactado(monto);
		if ( (monto > 0) && (monto < cotiz.getSaludObligatorio() ) )
		{
			if (this.logear)
				log.info("validacion VN250 ERR:pactado Salud, valor recibido:" + valor + ":SaludObligatorio:" + cotiz.getSaludObligatorio() + "::");
			//csanchez. 21/12/11
			//Por petición de clillo se permite que después de este error de validación se siga con el flujo normal de VN (VN250->VN260->VN270).
			this.resultado = "";				
    		return 140; //Monto Cotiz. Pactada Salud menor a Cotiz. Obligatoria
		}
		if (this.logear)
			log.info("validacion VN250 OK:pactada Salud:" + monto + "::");		
		this.resultado = "";
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "E";
			int monto = ((CotizacionREVO)cotizante.getCotizacion()).getSaludPactado();
			if (cotizante.isIsapre())
				return valida(monto, "" + monto, cotizante);

			this.resultado = "";
			return validaCero(monto, "" + monto, cotizante);
		} catch(Exception e)
		{
			log.error("error validacion VN250::", e);
			return this.CAIDA_SISTEMA;
		}
	}
	
	private int validaCero(int monto, String valor, CotizanteVO cotizante) 
	{
		if(monto != 0)
		{
			if (this.logear)
				log.info("validacion VN250 ERR:En FONASA el monto DEBE ser 0: monto recibido:" + valor + "::");
			CotizacionREVO cotiz = (CotizacionREVO)cotizante.getCotizacion();
			cotiz.setSaludPactado(monto);
			return 350; //MONTO COTIZACION PACTADA NO CORRESPONDE SI AFILIADO A FONASA
		}
		return this.COD_CUMPLE_VALIDACION;
	}

	public VN250(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN250(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN250(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
