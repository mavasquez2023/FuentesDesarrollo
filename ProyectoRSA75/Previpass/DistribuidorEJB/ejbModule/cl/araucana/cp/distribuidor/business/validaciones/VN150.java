package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN150 extends Validacion
{
	private static Logger log = Logger.getLogger(VN150.class);
	/*
	 * 1 parametro = VN150: dias trabajados por el trabajador
	 * 
	 * Mensajes
	 * 		122: Dias trabajados invalidos
	 * 		123: Dias trabajados mayor a parametro de dias del mes en curso
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.isAfpVoluntario())
				this.resultado = "F";
			else
				this.resultado = "E";
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int numDias = asignaValor(c.getValor());
			return valida(numDias, c.getValor(), cotizante);
		} catch(Exception e)
		{
			log.error("error validacion VN150::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int numDias, String valor, CotizanteVO cotizante) 
	{
		if (numDias < 0)
		{
			if (this.logear)
				log.info("validacion VN150 ERR:NumDiasTrabajados:invalido:" + valor + "::");
			return 122; //Dias trabajados invalidos (no es numero ni vacio)
		}
		//Asepulveda
//		int diasXMes = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_DIAS_X_MES)).intValue();
		int diasXMes = Constants.MAX_DIAS_MES;		
		cotizante.setNumDiasTrabajados(numDias);
		if (numDias > diasXMes) 
		{
			if (this.logear)
				log.info("validacion VN150 ERR:NumDiasTrabajados:mayor al permitido:valor recibido" + numDias + ":diasEnElMes:" + diasXMes + "::");
			return 123;//Dias trabajados mayor a parametro de dias del mes en curso
		}

		if (this.logear)
			log.info("validacion VN150 OK:NumDiasTrabajados:" +  numDias + "::");

		if (!cotizante.isAfpVoluntario())
			this.resultado = "";
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.isAfpVoluntario())
				this.resultado = "F";
			else
				this.resultado = "E";
			return valida(cotizante.getNumDiasTrabajados(), "" + cotizante.getNumDiasTrabajados(), cotizante);
		} catch(Exception e)
		{
			log.error("error validacion VN150::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN150(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN150(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN150(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
