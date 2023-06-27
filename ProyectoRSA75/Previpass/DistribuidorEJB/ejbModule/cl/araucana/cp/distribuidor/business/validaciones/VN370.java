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

public class VN370 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN370.class);
	/*
	 * 1 parametro= VN370: convenios dentales
	 * 
	 * Mensajes 
	 * 		162: Monto Convenio Dental invalido
	 * 		356: MONTO CONVENIO DENTAL NO APLICA SI CONVENIO NO TIENE CCAF
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
			log.error("error validacion VN370::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizacionREVO cotizacionREVO)
	{
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN370 ERR:CcafDental recibido:" + valor + "::");
			return 162;//Monto Convenio Dental invalido (no es numero ni vacio)				
		}
		cotizacionREVO.setCcafDental(monto);
		if(this.datosConvenio.getIdCcaf() == Constants.SIN_CCAF && monto > 0)
		{
			if (this.logear)
				log.info("validacion VN360 ERR: Empresa es INP-CAJA: Monto CcafDental debe ser 0:recibido:" + monto + "::");
			return 356;//MONTO CONVENIO DENTAL NO APLICA SI CONVENIO NO TIENE CCAF
		}
		cotizacionREVO.setCcafDental(monto);

		if (this.logear)
			log.info("validacion VN370 OK:CcafDental:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.mensaje = "dentalCCAF";
			CotizacionREVO cotizacionREVO = (CotizacionREVO) cotizante.getCotizacion();

			return valida(cotizacionREVO.getCcafDental(),"" + cotizacionREVO.getCcafDental(), cotizacionREVO);
		} catch(Exception e)
		{
			log.error("error validacion VN370::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN370(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN370(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN370(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
