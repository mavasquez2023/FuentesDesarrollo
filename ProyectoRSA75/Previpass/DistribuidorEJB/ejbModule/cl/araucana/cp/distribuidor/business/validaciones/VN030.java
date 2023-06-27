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

public class VN030 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN030.class);
	/*
	 * 1 parametro = VN030: Nombre del trabajador
	 * 
	 * Mensajes
	 * 		104: NOMBRES INVALIDOS
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			Utils utils = new Utils();
			if (utils.procesaTexto(true, c.getValor()) != Constants.TEXTO_OK)
			{
				if (this.logear)
					log.info("validacion VN030 ERR: Nombre invalido:" + c.getValor() + "::");
				cotizante.setNombre(c.getValor());
				return 104; //NOMBRES INVALIDOS
			}
			String valor = utils.getValor();
			cotizante.setNombre(valor);
			if (this.logear)
				log.info("validacion VN030 OK: Nombre:" + valor + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN030::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			//este valor ya debe venir validado y asignado => no hay nada que hacer aca
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN030 OK: Nombre:" + cotizante.getNombre() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN030::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN030(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN030(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}
	
	public VN030(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
