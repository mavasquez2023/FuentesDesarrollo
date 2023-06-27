package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;

public class VN590 extends Validacion
{
	private static Logger log = Logger.getLogger(VN590.class);
	/*
	 * 1 parametro = VN590: aporte CCAF (si empresa esta asociada a CCAF, se paga porcentaje por afiliados a FONASA)
	 * 
	 * Mensajes
	 * 		190: Monto de Aporte CCAF invalido
	 * 		191: Monto de Aporte CCAF incorrecto
	 * 		337: NO CORRESPONDE APORTE CCAF AL ESTAR ASOCIADO A ISAPRE
	 * 		352: NO CORRESPONDE APORTE CCAF AL NO TENER CCAF ASOCIADA
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			OpcionProcVO opcionProcVO = (OpcionProcVO)this.parametrosNegocio.get("opcionProcesos");
			if (opcionProcVO == null)
				return this.SIN_PARAM_NEGOCIO;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();

			if (cotizante.isIsapre() || this.datosConvenio.getIdCcaf() == Constants.SIN_CCAF)
				return validaCero(asignaValor(c.getValor()), c.getValor(), cotizante);
			if (opcionProcVO.getCalcCcaf() && !cotizante.isIsapre())
				return calcular(cotizante);
			return validar(asignaValor(c.getValor()), c.getValor(), cotizante);
		} catch(Exception e)
		{
			log.error("error validacion VN590::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int validaCero(int monto, String valor, CotizanteVO cotizante) 
	{
		if (monto != 0)
		{
			if (cotizante.isIsapre() && this.datosConvenio.getIdCcaf() != Constants.SIN_CCAF)
			{
				if (this.logear)
					log.info("validacion VN590 ERR:aporte CCAF debe ser cero por que esta asociado a isapre, valor recibido:" + valor + "::");
				return 337;//NO CORRESPONDE APORTE CCAF AL ESTAR ASOCIADO A ISAPRE
			} 
			if (this.datosConvenio.getIdCcaf() != Constants.SIN_CCAF)
			{
				if (this.logear)
					log.info("validacion VN590 ERR:aporte CCAF debe ser cero por que convenio no tiene CCAF asociada, valor recibido:" + valor + "::");
				return 352;//NO CORRESPONDE APORTE CCAF AL NO TENER CCAF ASOCIADA
			}
		}
		return this.COD_CUMPLE_VALIDACION;
	}

	private int validar(int monto, String valor, CotizanteVO cotizante) 
	{
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN590 ERR:aporte CCAF invalido, valor recibido:" + valor + "::");			
			return 190; //Monto de Aporte CCAF invalido
		}
		setAporteCCAF(cotizante.getTipoProceso(), monto, cotizante.getCotizacion());
		if (!this.parametrosNegocio.containsKey("" + Constants.PARAM_APORTE_CCAF_FONASA))
			return this.SIN_PARAM_NEGOCIO;
		float aporteCCAFFON = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_APORTE_CCAF_FONASA)).floatValue() / 100;
		int rentaImponible = getRenta(cotizante.getTipoProceso(), cotizante.getCotizacion(), cotizante.getTipoPrevision());
		int montoCalculado = Math.round(rentaImponible * aporteCCAFFON);
		if (monto != montoCalculado)
		{
			if (this.logear)
				log.info("validacion VN590 Err:aporteCCAF(FONASA), valor recibido:" + valor + ":valor calculado:" + montoCalculado + "::");
			return 191;//Monto de Aporte CCAF incorrecto
		}
		if (this.logear)
			log.info("validacion VN590 OK:aporteCCAF:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	private int calcular(CotizanteVO cotizante) 
	{
		float aporteCCAFFON = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_APORTE_CCAF_FONASA)).floatValue() / 100;
		int rentaImponible = getRenta(cotizante.getTipoProceso(), cotizante.getCotizacion(), cotizante.getTipoPrevision());
		
		int monto = Math.round(rentaImponible * aporteCCAFFON);
		setAporteCCAF(cotizante.getTipoProceso(), monto, cotizante.getCotizacion());
		if (this.logear)
			log.info("validacion VN590 OK:aporteCCAF calculado (opcProc):" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	private int getRenta(char tipoProceso, CotizacionVO cotizacion, int tipoPrevision)
	{
		if (tipoProceso == 'R') {
			if (tipoPrevision == Constants.TIPO_PREVISION_INP) {
				return ((CotizacionREVO)cotizacion).getRentaImpInp();
			} else {
				return ((CotizacionREVO)cotizacion).getRentaImp();
			}
		}
		if (tipoProceso == 'G') {
			return ((CotizacionGRVO)cotizacion).getGratificacion();
		}

		if (tipoProceso == 'A') {
			return ((CotizacionRAVO)cotizacion).getReliquidacion();
		}

		if (tipoProceso == 'D') {
			return ((CotizacionDCVO)cotizacion).getRentaImponible();
		}

		return 0;
		//return ((CotizacionRAVO)cotizacion).getRentaImpInp();
	}

	private void setAporteCCAF(char tipoProceso, int monto, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			((CotizacionREVO)cotizacion).setCcafAporte(monto);
		else if (tipoProceso == 'G')
			((CotizacionGRVO)cotizacion).setCcafAporte(monto);
		else if (tipoProceso == 'A')
			((CotizacionRAVO)cotizacion).setCcafAporte(monto);
	}

	private int getAporteCCAF(char tipoProceso, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			return ((CotizacionREVO)cotizacion).getCcafAporte();
		else if (tipoProceso == 'G')
			return ((CotizacionGRVO)cotizacion).getCcafAporte();
		else if (tipoProceso == 'A')
			return ((CotizacionRAVO)cotizacion).getCcafAporte();
		return 0;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";

			OpcionProcVO opcionProcVO = (OpcionProcVO)this.parametrosNegocio.get("opcionProcesos");
			if (opcionProcVO == null)
				return this.SIN_PARAM_NEGOCIO;
			String tipoEmpresa = (String) this.parametrosNegocio.get("tipoEmpresa");
			if(tipoEmpresa == null)
				tipoEmpresa = Constants.TIPO_EMPRESA;			
			
			int monto = getAporteCCAF(cotizante.getTipoProceso(), cotizante.getCotizacion());
			
			if(tipoEmpresa.equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
				return this.COD_CUMPLE_VALIDACION;
			}else{
				if (cotizante.isIsapre() || this.datosConvenio.getIdCcaf() == Constants.SIN_CCAF)
					return validaCero(monto, "" + monto, cotizante);
				if (opcionProcVO.getCalcCcaf() && !cotizante.isIsapre())
					return calcular(cotizante);
				return validar(monto, "" + monto, cotizante);
			}
		} catch(Exception e)
		{
			log.error("error validacion VN590::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN590(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN590(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN590(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
