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

public class VN390 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN390.class);
	/*
	 * 1 parametro = VN390: seguros de vida
	 * 
	 * Mensajes
	 * 		164: Monto Seguro de Vida invalido
	 * 		358: MONTO SEGURO DE VIDA NO APLICA SI CONVENIO NO TIENE CCAF
	 * 		359: MONTO SEGURO DE VIDA NO APLICA SI EMPRESA PUBLICA
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
			log.error("error validacion VN390::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizacionREVO cotizacionREVO)
	{
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN390 ERR:CcafSeguro recibido:" + valor + "::");
			return 164;//Monto Seguro de Vida invalido (no es numero ni vacio)
		}
		cotizacionREVO.setCcafSeguro(monto);
		int isPrivada = ((Integer)this.parametrosNegocio.get("empresaPrivada")).intValue();
		if (isPrivada == 1 && monto > 0) 
		{
			if (this.logear)
				log.info("validacion VN390 OK: Empresa es CAJA-FISCAL: Monto 0::");
			return 359;//MONTO SEGURO DE VIDA NO APLICA SI EMPRESA PUBLICA
		}
		
		if(this.datosConvenio.getIdCcaf() == Constants.SIN_CCAF && monto > 0)
		{
			if (this.logear)
				log.info("validacion VN390 OK: Empresa es INP-CAJA: Monto 0::");
			return 358; //MONTO SEGURO DE VIDA NO APLICA SI CONVENIO NO TIENE CCAF
		}

		if (this.logear)
			log.info("validacion VN390 OK:CcafSeguro:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.mensaje = "CCAFSeguros";
			CotizacionREVO cotizacionREVO = (CotizacionREVO) cotizante.getCotizacion();

			return valida(cotizacionREVO.getCcafSeguro(), "" + cotizacionREVO.getCcafSeguro(), cotizacionREVO);
		} catch(Exception e)
		{
			log.error("error validacion VN390::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN390(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN390(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN390(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
