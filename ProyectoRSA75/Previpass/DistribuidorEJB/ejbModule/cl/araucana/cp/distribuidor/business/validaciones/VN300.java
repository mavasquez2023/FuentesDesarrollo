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

public class VN300 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN300.class);
	/*
	 * 1 parametro = VN300: ahorro prevision
	 * 
	 * Mensajes
	 * 		149: Monto Cotizacion de Ahorro Prevision invalido
	 * 		150: Monto Cotizacion Ahorro no aplica si se esta asociado a INP
	 * 		151: Monto Cotiz. Ahorro no aplica si no se esta asociado a AFP
	 * 
	 * Mensajes REFORMA
	 * 		306: SUMA DE APORTES DEBE SER MAYOR QUE 0
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R')
				return this.COD_CUMPLE_VALIDACION;
			int monto = 0;
			if (cotizante.isAfpVoluntario())
				this.resultado = "F";
			else
			{
				cotizante.getCotizacion().setPrevisionAhorro(-1);
				this.resultado = "";
			}
			if(this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			monto = asignaValor(c.getValor());

			return valida(monto, c.getValor(), cotizante);
		} catch(Exception e)
		{
			log.error("error validacion VN300::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizanteVO cotizante) 
	{
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN300 Err:ahorro Prevision: valor recibido:" + valor + "::");
			return 149;//Monto Cotizacion de Ahorro Prevision invalido (no es numero ni vacio)
		}
		CotizacionREVO cotizacion = (CotizacionREVO)cotizante.getCotizacion();
		cotizacion.setPrevisionAhorro(monto);
		if(cotizante.getIdEntPensionReal() == Constants.ID_AFP_NINGUNA && monto != 0)
		{
			if (this.logear)
				log.info("validacion VN300 ERR REFORMA:Ahorro debe ser 0 si no esta asociado a AFP::");
			return 151; //Monto Cotiz. Ahorro no aplica si no se esta asociado a AFP
		}
		if(cotizante.getIdEntPensionReal() == Constants.ID_INP && monto != 0)
		{
			if (this.logear)
				log.info("validacion VN300 ERR REFORMA:Ahorro debe ser 0 si no esta asociado a INP::");
			return 150; // Monto Cotizacion Ahorro no aplica si se esta asociado a INP
		}
		
		if (cotizante.isAfpVoluntario())
		{
			if (cotizacion.getPrevisionObligatorio() == 0 && monto == 0)
			{
				if (this.logear)
					log.info("validacion VN300 ERR REFORMA:ahorro Prevision y PrevisionObligatorio son 0::");
				return 306; //SUMA DE APORTES DEBE SER MAYOR QUE 0					
			}
		} 
		if (this.logear)
			log.info("validacion VN300 OK:ahorro Prevision:" + monto + "::" + cotizacion.getPrevisionAhorro() + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R')
				return this.COD_CUMPLE_VALIDACION;
			if (cotizante.isAfpVoluntario())
				this.resultado = "F";
			else
				this.resultado = "";

			int monto = ((CotizacionREVO)cotizante.getCotizacion()).getPrevisionAhorro();
			if (cotizante.getTipoProceso() == 'R')
				return valida(monto, "" + monto, cotizante);
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN300::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN300(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN300(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN300(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
