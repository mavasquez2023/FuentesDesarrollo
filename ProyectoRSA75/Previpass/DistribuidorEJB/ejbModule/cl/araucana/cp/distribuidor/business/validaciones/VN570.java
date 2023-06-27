package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;


public class VN570 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN570.class);
	/*
	 * 1 parametro = VN570: cotizacion mutual
	 * 
	 * Mutual
	 * 		184: Cotizacion Mutual invalida
	 * 		185: Cotizacion Mutual incorrecta
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";
			OpcionProcVO opcionProcVO = (OpcionProcVO)this.parametrosNegocio.get("opcionProcesos");
			if (opcionProcVO == null)
				return this.SIN_PARAM_NEGOCIO;
			
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			int monto= asignaValor(c.getValor());
			if(this.datosConvenio.getIdMutual() == Constants.SIN_MUTUAL  && this.datosConvenio.getIdGrupoConvenio()== 122 && monto==0) {
				return this.COD_CUMPLE_VALIDACION;
			}
			this.mensaje = c.getNombre();
			if (this.datosConvenio.getIdMutual() == Constants.ID_INP){
				cotizante.getCotizacion().setInpMutual(0);
			}else{
				if (opcionProcVO.getCalMutual())
					return calcular(cotizante.getCotizacion());
				return validar(cotizante.getTipoProceso(), monto, c.getValor(), cotizante.getCotizacion());
			}
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN570::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int validar(char tipoproceso, int monto, String valor, CotizacionVO cotizacion) 
	{	
		
		cotizacion.setInpMutual(monto);
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN570 ERR: Cotizacion Mutual invalida:" + valor + "::");
			if (this.datosConvenio.getIdMutual() != Constants.ID_INP)
				return 184;//Cotizacion Mutual invalida (no es numero ni vacio)
			return 229; //COTIZACION ACC. DEL TRABAJO INP INVALIDA
		}
		if(tipoproceso == 'R' || tipoproceso == 'G')
		{		
			if (!this.parametrosNegocio.containsKey("" + Constants.PARAM_TASA_FIJA))
				return this.SIN_PARAM_NEGOCIO;
			float tasaFija = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TASA_FIJA)).floatValue();
			float tasaAdicional = this.datosConvenio.getMutualTasaAdicional();
			int imponibleMutual = cotizacion.getMutualImp();
			int montoCalculado = Math.round(imponibleMutual  * (tasaFija  + tasaAdicional) / 100);
			if (montoCalculado != monto)
			{
				if (this.logear)
					log.info("validacion VN570 cotizacionMutual ERR: Renta:" + imponibleMutual + " :valor recibido:" + monto + ":valor esperado:" + montoCalculado + ":tasaFija:" + tasaFija + ":tasaAdicional:" + tasaAdicional + ":suma:" + (tasaFija  + tasaAdicional) + "::");
				if (this.datosConvenio.getIdMutual() != Constants.ID_INP)
					return 185; //Cotizacion Mutual incorrecta
//				clillo 16-5-16 sin Pago IPS
				//return 230; //COTIZACION ACC. DEL TRABAJO INP INCORRECTA
			}
		}

		if (this.logear)
			log.info("validacion VN570 OK:cotizacionMutual validada (opProcesos):" + monto + "::");

		return this.COD_CUMPLE_VALIDACION;		
	}

	private int calcular(CotizacionVO cotizacion)
	{
		if (!this.parametrosNegocio.containsKey("" + Constants.PARAM_TASA_FIJA))
			return this.SIN_PARAM_NEGOCIO;
		float tasaFija = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_TASA_FIJA)).floatValue();
		float tasaAdicional = this.datosConvenio.getMutualTasaAdicional();
		int imponibleMutual = cotizacion.getMutualImp();
		int monto = Math.round(imponibleMutual  * (tasaFija  + tasaAdicional) / 100);

		cotizacion.setInpMutual(monto);

		if (this.logear)
			log.info("validacion VN570 OK:cotizacionMutual calculada (opProcesos):" + monto + "::");

		return this.COD_CUMPLE_VALIDACION;		
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			if(cotizante.getTipoProceso() == 'R')
				this.resultado = "";
			int monto = cotizante.getCotizacion().getInpMutual();
			if(this.datosConvenio.getIdMutual() == Constants.SIN_MUTUAL && this.datosConvenio.getIdGrupoConvenio()== 122 && monto==0) {
				return this.COD_CUMPLE_VALIDACION;
			}		
			
			OpcionProcVO opcionProcVO = (OpcionProcVO)this.parametrosNegocio.get("opcionProcesos");
			if (opcionProcVO == null)
				return this.SIN_PARAM_NEGOCIO;

			if (opcionProcVO.getCalMutual())
				if (this.datosConvenio.getIdMutual() == Constants.ID_INP)
					cotizante.getCotizacion().setInpMutual(0);
				else
					return calcular(cotizante.getCotizacion());

			return validar(cotizante.getTipoProceso(), monto, "" + monto, cotizante.getCotizacion());
		} catch(Exception e)
		{
			log.error("error validacion VN570::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN570(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN570(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN570(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
