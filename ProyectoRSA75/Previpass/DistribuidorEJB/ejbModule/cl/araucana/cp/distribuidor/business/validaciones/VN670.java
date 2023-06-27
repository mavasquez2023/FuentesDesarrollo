package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;

public class VN670 extends Validacion
{
	private static Logger log = Logger.getLogger(VN670.class);

	/*
	 * 1 parametro = VN670: tipo regimen previsional, 1: INP 2: AFP
	 * 
	 * Mensajes 
	 * 		196: TIPO DE REGIMEN PROVISIONAL INVALIDO
	 * 		197: TIPO DE REGIMEN PROVISIONAL INCORRECTO
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			GrupoConvenioVO grupoConvenioVO = (GrupoConvenioVO)this.parametrosNegocio.get("grupoConvenio");
			if (cotizante.getTipoProceso() == 'A' || cotizante.getTipoProceso() == 'G' || (cotizante.getTipoProceso() == 'R' && grupoConvenioVO.isPrevired()))
				return this.COD_CUMPLE_VALIDACION;

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int numero = asignaValor(c.getValor());

			return valida(numero, c.getValor(), cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN670::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	//private int valida(int numero, String valor, CotizacionDCVO cotizacion)
	private int valida(int numero, String valor, CotizanteVO cotizante) {
		if (cotizante.getTipoProceso() == 'D' && numero != 1 && numero != 2 && numero != 0) {
			if (this.logear)
				log.info("validacion VN670 tipo regimen previsional ERR:valor recibido:" + valor + ":(debe ser 1: INP 2: AFP):");
			return 197; //TIPO DE REGIMEN PROVISIONAL INCORRECTO
		}
		
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			cotizacion.setTipoRegimenPrev(numero);
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			cotizacion.setTipoRegimenPrev(numero);
		}
		
		
		if (this.logear)
			log.info("validacion VN670 OK:tipo regimen previsional:" + numero + ":(1: INP 2: AFP):");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			GrupoConvenioVO grupoConvenioVO = (GrupoConvenioVO)this.parametrosNegocio.get("grupoConvenio");
			if (cotizante.getTipoProceso() == 'A' || cotizante.getTipoProceso() == 'G' || (cotizante.getTipoProceso() == 'R' && grupoConvenioVO.isPrevired()))
				return this.COD_CUMPLE_VALIDACION;

			//CotizacionDCVO cotizacion = (CotizacionDCVO)cotizante.getCotizacion();
//			int numero = cotizacion.getTipoRegimenPrev();
			int numero = 0;
			if (cotizante.getTipoProceso() == 'D') {
				CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
				numero = cotizacion.getTipoRegimenPrev();
			}
			if (cotizante.getTipoProceso() == 'R') {
				CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
				numero = cotizacion.getTipoRegimenPrev();
			}
			
			return valida(numero, "" + numero, cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN670::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN670(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN670(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN670(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
