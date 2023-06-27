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
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;

public class VN310 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN310.class);
	/*
	 * 1 parametro = VN310: total prevision
	 * 
	 * Mensajes
	 * 		152: Monto Total Prevision invalido
	 * 		153: Monto Total Prevision no se puede calcular
	 * 		154: Monto Total Prevision incorrecto de acuerdo a formula AFP
	 * 		155: Monto Total Prevision incorrecto de acuerdo a formula INP
	 * 		329: Monto Total Prevision Excede el limite de 8 digitos
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'D')
				return this.COD_CUMPLE_VALIDACION;
			if (cotizante.isAfpVoluntario())
				this.resultado = "F";
			else
				this.resultado = "";
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			OpcionProcVO opcionProcVO = (OpcionProcVO)this.parametrosNegocio.get("opcionProcesos");
			if (opcionProcVO == null)
				return this.SIN_PARAM_NEGOCIO;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();

			if (opcionProcVO.getCalcTotPrevision() || opcionProcVO.getCalInp()){
				return this.calcular(cotizante, cotizante.getCotizacion());
			}
			
			return validar(asignaValor(c.getValor()), c.getValor(), cotizante, cotizante.getCotizacion(), opcionProcVO);
			
		} catch(Exception e)
		{
			log.error("error validacion VN310::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int validar(int monto, String valor, CotizanteVO cotizante, CotizacionVO cotizacion, OpcionProcVO opcionProcVO) 
	{
		int sumaTotal = 0;
		if (monto > Constants.TOPE_TOTAL_BD)
		{
			if (this.logear)
				log.info("validacion VN310 ERR:total prevision es mayor que el limite de la BD:"+ Constants.TOPE_TOTAL_BD + "::");
			cotizacion.setTotalPrevision(Constants.TOPE_TOTAL_BD);
			return 329; //Monto Total Prevision Excede el limite de 8 digitos
		}
		if (cotizante.isAfpVoluntario())
		{
			if (monto < 0)
			{
				if (this.logear)
					log.info("validacion VN310 ERR:total prevision REFORMA: valor recibido invalido:" + valor + "::");
        		return 152;//Monto Total Prevision invalido (no es numero ni vacio)
			}
			CotizacionREVO cotizacionRE = (CotizacionREVO)cotizacion;
			if (cotizacionRE.getPrevisionObligatorio() + cotizacionRE.getPrevisionAhorro() != monto)
			{
				if (this.logear)
					log.info("validacion VN310 ERR:total prevision(AFP):recibido: " + monto + ": no concuerda con valor calculado:" + (cotizacionRE.getPrevisionObligatorio() + cotizacionRE.getPrevisionAhorro()) + "::");
				cotizacion.setTotalPrevision(monto);
				return 154; //Monto Total Prevision incorrecto de acuerdo a formula AFP
			}
			if (this.logear)
				log.info("validacion VN310 OK REFORMA:total prevision:" + monto + "::");
		} else if (cotizante.getTipoProceso() == 'R' && (cotizante.getIdEntPensionReal() != Constants.ID_AFP_NINGUNA))
		{
			if (monto < 0)
			{
				if (this.logear)
					log.info("validacion VN310 ERR:total prevision: valor recibido invalido:" + valor + "::");
        		return 152;//Monto Total Prevision invalido (no es numero ni vacio)
			}
			if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP)//AFP
			{
				sumaTotal = cotizacion.getSumaTotalAFP();
				if (sumaTotal > Constants.TOPE_TOTAL_BD)
				{
					if (this.logear)
						log.info("validacion VN310 ERR:total prevision es mayor que el limite de la BD:"+ Constants.TOPE_TOTAL_BD + "::");
					cotizacion.setTotalPrevision(Constants.TOPE_TOTAL_BD);
					return 329; //Monto Total Prevision Excede el limite de 8 digitos
				}
				if (sumaTotal < 0)
				{
					if (this.logear)
						log.info("validacion VN310 ERR:total prevision: no se puede calcular por otro valor invalido (AFP)");
					return 153; //Monto Total Prevision no se puede calcular
				} else if (monto != sumaTotal)
				{
					if (this.logear)
						log.info("validacion VN310 ERR:total prevision(AFP):recibido: " + monto + ": no concuerda con valor calculado:" + sumaTotal + "::");
					return 154; //Monto Total Prevision incorrecto de acuerdo a formula AFP
				} 
			} else if(cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP)//INP
			{
				
				sumaTotal = cotizacion.getSumaTotalINP();
				
				if (opcionProcVO.getCalInp())
					monto = sumaTotal;
				
				if (sumaTotal > Constants.TOPE_TOTAL_BD)
				{
					if (this.logear)
						log.info("validacion VN310 ERR:total prevision es mayor que el limite de la BD:"+ Constants.TOPE_TOTAL_BD + "::");
					cotizacion.setTotalPrevision(Constants.TOPE_TOTAL_BD);
					return 329; //Monto Total Prevision Excede el limite de 8 digitos
				}
				if (sumaTotal < 0)
				{
					if (this.logear)
						log.info("validacion VN310 ERR:total prevision: no se puede calcular por otro valor invalido (INP)");
					return 153; //Monto Total Prevision no se puede calcular
				} else if (monto != sumaTotal)
				{
					if (this.logear)
						log.info("validacion VN310 ERR:total prevision(INP):recibido: " + monto + ": no concuerda con valor calculado:" + sumaTotal + "::");
					cotizacion.setTotalPrevision(monto);
					return 155; //Monto Total Prevision incorrecto de acuerdo a formula INP
				} 
			}
		} else if (cotizante.getIdEntPensionReal() == Constants.ID_AFP_NINGUNA && monto != 0)
		{
			if (this.logear)
				log.info("validacion VN310 ERR:total prevision: valor recibido invalido: Debe ser 0 al no tener fondo de pension::");
			cotizacion.setTotalPrevision(monto);
    		return 152; //Monto Total Prevision invalido (no es numero ni vacio)
		}

		cotizacion.setTotalPrevision(monto);
		if (this.logear)
			log.info("validacion VN310 OK:totalPrevision:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	private int calcular(CotizanteVO cotizante, CotizacionVO cotizacion) 
	{
		int monto = 0;
		if (cotizante.isAfpVoluntario())
		{
			CotizacionREVO cotizacionRE = (CotizacionREVO)cotizacion;
			monto = cotizacionRE.getPrevisionObligatorio() + cotizacionRE.getPrevisionAhorro();
		}
		else
		{
			if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP)//AFP
				monto = cotizacion.getSumaTotalAFP();
			else if(cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP)//INP
				monto = cotizacion.getSumaTotalINP();
		}
		cotizacion.setTotalPrevision(monto);
		if (this.logear)
			log.info("validacion VN310 OK:totalPrevision calculado (opcProcesos):" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'D')
				return this.COD_CUMPLE_VALIDACION;
			if (cotizante.isAfpVoluntario())
				this.resultado = "F";
			else
				this.resultado = "";

			OpcionProcVO opcionProcVO = (OpcionProcVO)this.parametrosNegocio.get("opcionProcesos");
			if (opcionProcVO == null)
				return this.SIN_PARAM_NEGOCIO;
			int monto = cotizante.getCotizacion().getTotalPrevision();

			if (opcionProcVO.getCalcTotPrevision() && cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP)
				return calcular(cotizante, cotizante.getCotizacion());
			else if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP)
				return validar(monto, "" + monto, cotizante, cotizante.getCotizacion(), opcionProcVO);
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN310::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN310(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN310(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN310(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
