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

public class VN1200 extends Validacion
{
	private static Logger log = Logger.getLogger(VN1200.class);

	/*
	 * 1 parametro= VN1200: nombre trabajador dependiente afiliacion voluntaria
	 * return 312;//Nombre Trabajador dependiente invalido
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (!cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "F";
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			Utils utils = new Utils();
			if (utils.procesaTexto(true, c.getValor()) != Constants.TEXTO_OK)
			{
				if (this.logear)
					log.info("validacion VN1200 ERR: Nombre invalido:" + c.getValor() + "::");
				cotizante.setNombre(c.getValor());
				return 312; //NOMBRES INVALIDOS
			}
			String valor = utils.getValor();
			((CotizacionREVO)cotizante.getCotizacion()).setAfpvNombreDpndiente(valor);
			if (this.logear)
				log.info("validacion VN1200 OK: Nombre dependiente:" + valor + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN1200::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (!cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "F";
			if (this.logear)
				log.info("validacion VN1200 OK:AfpvNombreDpndiente:" + ((CotizacionREVO)cotizante.getCotizacion()).getAfpvNombreDpndiente() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN1200::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN1200(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN1200(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN1200(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
