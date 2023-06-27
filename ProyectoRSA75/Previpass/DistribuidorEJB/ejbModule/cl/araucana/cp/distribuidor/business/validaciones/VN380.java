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

public class VN380 extends Validacion
{
	private static Logger log = Logger.getLogger(VN380.class);

	/*
	 * 1 parametro = VN380: leasing CCAF
	 * 
	 * Mensajes 
	 * 163: Monto Leasing invalido 
	 * 357: MONTO LEASING NO APLICA SI CONVENIO NO TIENE CCAF
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

			ConceptoVO c = (ConceptoVO) this.parametros.get(0);
			this.mensaje = c.getNombre();
			int monto = asignaValor(c.getValor());

			return valida(monto, c.getValor(), cotizacionREVO);
		} catch (Exception e)
		{
			log.error("error validacion VN380::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizacionREVO cotizacionREVO)
	{
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN380 ERR:CcafLeasing recibido:" + valor + "::");
			return 163;// Monto Leasing invalido (no es numero ni vacio)				
		}
		cotizacionREVO.setCcafLeasing(monto);
		if (this.datosConvenio.getIdCcaf() == Constants.SIN_CCAF && monto > 0)
		{
			if (this.logear)
				log.info("validacion VN380 ERR: no aplica INP-CAJA: Monto recibido::" + monto + "::");
			return 357; //MONTO LEASING NO APLICA SI CONVENIO NO TIENE CCAF
		}

		if (this.logear)
			log.info("validacion VN380 OK:CcafLeasing:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.mensaje = "CCAFLeasing";
			CotizacionREVO cotizacionREVO = (CotizacionREVO) cotizante.getCotizacion();

			return valida(cotizacionREVO.getCcafLeasing(), "" + cotizacionREVO.getCcafLeasing(), cotizacionREVO);
		} catch (Exception e)
		{
			log.error("error validacion VN380::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN380(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN380(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN380(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
