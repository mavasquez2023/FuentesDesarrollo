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

public class VN360 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN360.class);
	/*
	 * 1 parametro= VN360: creditos personales caja
	 * 
	 * Mensajes
	 * 		161: Monto Credito Personal invalido
	 * 		355: MONTO CREDITO PERSONAL NO APLICA SI CONVENIO NO TIENE CCAF
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			CotizacionREVO cotizacionREVO = (CotizacionREVO) cotizante.getCotizacion();
			
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
						
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int monto = asignaValor(c.getValor());

			return valida(monto, c.getValor(), cotizacionREVO);
		} catch(Exception e)
		{
			log.error("error validacion VN360::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizacionREVO cotizacionREVO)
	{
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN360 ERR:CcafCredito, valor recibido:" + valor + "::");
			return 161;//Monto Credito Personal invalido (no es numero ni vacio)
		}
		cotizacionREVO.setCcafCredito(monto);
		if(this.datosConvenio.getIdCcaf() == Constants.SIN_CCAF && monto > 0)
		{
			if (this.logear)
				log.info("validacion VN360 ERR: Empresa es INP-CAJA: Monto CcafCredito debe ser 0:recibido:" + monto + "::");
			return 355;//MONTO CREDITO PERSONAL NO APLICA SI CONVENIO NO TIENE CCAF
		}

		if (this.logear)
			log.info("validacion VN360 OK:CcafCredito:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.mensaje = "creditoPresonalCCAF";
			CotizacionREVO cotizacionREVO = (CotizacionREVO) cotizante.getCotizacion();
			
			return valida(cotizacionREVO.getCcafCredito(), "" + cotizacionREVO.getCcafCredito(), cotizacionREVO);
		} catch(Exception e)
		{
			log.error("error validacion VN360::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN360(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN360(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN360(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
