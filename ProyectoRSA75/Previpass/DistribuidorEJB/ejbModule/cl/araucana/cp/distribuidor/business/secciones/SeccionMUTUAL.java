package cl.araucana.cp.distribuidor.business.secciones;

import java.util.HashMap;
import java.util.List;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ConfigPDFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.SeccionException;

public class SeccionMUTUAL extends Seccion
{
	private long total = 0;

	public SeccionMUTUAL(Integer numM, ConvenioVO convenio, HashMap propM, HashMap parametros, List tiposSecciones, HashMap entidadesCCAF)
	{
		super(numM, convenio, propM, parametros, tiposSecciones, entidadesCCAF);
	}

	public long finishProcess(char tipoProceso, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (tipoProceso == 'D')
				return 0;
			if (this.convenio.getIdMutual() == Constants.ID_INP)//no considerar si INP actua como mutual
				return 0;
			seccion.setConfigPDF(new ConfigPDFVO(12));
			double tasa= (long)(this.convenio.getMutualTasaAdicional() * 100) + (long)(new Float((String)this.parametros.get("" + Constants.PARAM_TASA_FIJA)).floatValue() * 100);
			seccion.setMArbitrario(getIndice(seccion.getIdTipoSeccion(), seccion.getIdTipoSeccion() + "tasaFijaAdicional"), (long)tasa);
			//Si no es cálculo individual se calcula Total Mutual por la Sumatoria de las Remuneraciones por la tasa total
			if (this.convenio.getMutualCalculoIndividual() != Constants.OPC_MUTUAL_CALC_IND)//no calcular individual, asignar el primero informado
			{
				//if (tipoProceso == 'R')
				//	seccion.setMArbitrario(getIndice(seccion.getIdTipoSeccion(), seccion.getIdTipoSeccion() + "mutualImp"), this.remImp);
				long mutualImponible= seccion.getM(getIndice(seccion.getIdTipoSeccion(), seccion.getIdTipoSeccion() + "mutualImp"));
				double totalizado= (mutualImponible * (tasa/100))/100;
				this.total= Math.round(totalizado);
				seccion.setMArbitrario(getIndice(seccion.getIdTipoSeccion(), seccion.getIdTipoSeccion() + "totalMutual"), this.total);				
				return this.total;
			}
		
			return seccion.getM(getIndice(seccion.getIdTipoSeccion(), seccion.getIdTipoSeccion() + "totalMutual"));
		} catch (Exception e)
		{
			this.log.error("problemas finishProcessMUTUAL", e);
			throw new SeccionException("Problemas en SeccionMUTUAL:finishProcess:", e);
		}
	}

	public void generar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (tipoProceso == 'D' || this.convenio.getIdMutual() == Constants.ID_INP)//no considerar si INP actua como mutual
				return;
			if (this.logear) this.log.info("generar mutual: calculo individual:" + (this.convenio.getMutualCalculoIndividual() == Constants.OPC_MUTUAL_CALC_IND) + "::");
			
			this.numTrab = seccion.getConfigPDF().getNumTrab();
			this.mTmp = new double[12];
			asignaMUTUAL(tipoProceso, cotizante.getCotizacion(), seccion.getIdTipoSeccion());
			
			if (this.cuenta)
				seccion.setDetallesSeccion(this.convenio.getIdMutual(), 1, this.mTmp);
		} catch (Exception e)
		{
			this.log.error("problemas generarMUTUAL", e);
			throw new SeccionException("Problemas en SeccionMUTUAL:generar:", e);
		}
	}

	public void restar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			/*
			 * GMALLEA 26-01-2012
			 * Se agrega la validacion por detalle seccion si es null significa que no se creo desde la tabla, con esto el sistema toma los valores del covenio
			 */
			if (tipoProceso == 'D' ||  seccion.getDetallesSeccion() == null )//no considerar si INP actua como mutual
				return;
			
			if (this.logear) this.log.info("restando HIJO MUTUAL!!");
			this.numTrab = seccion.getConfigPDF().getNumTrab();
			this.mTmp = new double[12];
			restaMUTUAL(tipoProceso, cotizante.getCotizacion(), seccion.getIdTipoSeccion());
			if (this.cuenta)
				seccion.setDetallesSeccion(this.convenio.getIdMutual(), -1, this.mTmp);
		} catch (Exception e)
		{
			this.log.error("problemas restarMUTUAL", e);
			throw new SeccionException("Problemas en SeccionMUTUAL:restar:", e);
		}
	}

	public void asignaMUTUAL(char tipoProceso, CotizacionVO cot, int idTipoSeccion) throws SeccionException
	{
		try
		{
			//this.cuenta = false;
			if (this.logear) this.log.info("asignaMUTUAL MUTUAL!!");
			if (tipoProceso == 'R')
			{
				CotizacionREVO cotizacion = (CotizacionREVO)cot;
				if (cotizacion.getInpMutual() > 0)
				{	
					this.cuenta = true;
					setValores(idTipoSeccion, "mutualImp", cotizacion.getMutualImp());
					setValores(idTipoSeccion, "totalMutual", cotizacion.getInpMutual());
				}
			} else if (tipoProceso == 'G')
			{
				CotizacionGRVO cotizacion = (CotizacionGRVO)cot;
				if (cotizacion.getInpMutual() > 0)
				{	
					this.cuenta = true;
					setValores(idTipoSeccion, "totalMutual", cotizacion.getInpMutual());
				}
			} else if (tipoProceso == 'A')
			{
				CotizacionRAVO cotizacion = (CotizacionRAVO)cot;
				if (cotizacion.getInpMutual() > 0)
				{	
					this.cuenta = true;
					setValores(idTipoSeccion, "totalMutual", cotizacion.getInpMutual());
				}
			} else if (tipoProceso == 'D')
			{
				//este tipo proceso no tiene esta seccion!!
				this.log.info("se esta tratando de seccionar la seccion MUTUAL, pero el tipoProceso 'D' no cuenta con esta seccion, revisar configuracion properties_mapeo.tipo = 2.");
			}
		} catch (Exception e)
		{
			this.log.error("problemas asignaMUTUAL", e);
			throw new SeccionException("Problemas en SeccionMUTUAL:asignaMUTUAL:", e);
		}
	}

	private void restaMUTUAL(char tipoProceso, CotizacionVO cot, int idTipoSeccion) throws SeccionException
	{
		try
		{
			this.cuenta = false;
			if (tipoProceso == 'R')
			{
				CotizacionREVO cotizacion = (CotizacionREVO)cot;
				if (cotizacion.getInpMutual() > 0)
				{	
					this.cuenta = true;
					restaValores(idTipoSeccion, "mutualImp", cotizacion.getMutualImp());
					restaValores(idTipoSeccion, "totalMutual", cotizacion.getInpMutual());
				}
			} else if (tipoProceso == 'G')
			{
				CotizacionGRVO cotizacion = (CotizacionGRVO)cot;
				if (cotizacion.getInpMutual() > 0)
				{	
					this.cuenta = true;
					restaValores(idTipoSeccion, "totalMutual", cotizacion.getInpMutual());
				}
			} else if (tipoProceso == 'A')
			{
				CotizacionRAVO cotizacion = (CotizacionRAVO)cot;
				if (cotizacion.getInpMutual() > 0)
				{	
					this.cuenta = true;
					restaValores(idTipoSeccion, "totalMutual", cotizacion.getInpMutual());
				}
			} else if (tipoProceso == 'D')
			{
				//este tipo proceso no tiene esta seccion!!
				this.log.info("se esta tratando de seccionar la seccion MUTUAL, pero el tipoProceso 'D' no cuenta con esta seccion, revisar configuracion properties_mapeo.tipo = 2.");
			}
		} catch (Exception e)
		{
			this.log.error("problemas restaMUTUAL", e);
			throw new SeccionException("Problemas en SeccionMUTUAL:restaMUTUAL:", e);
		}
	}
}
