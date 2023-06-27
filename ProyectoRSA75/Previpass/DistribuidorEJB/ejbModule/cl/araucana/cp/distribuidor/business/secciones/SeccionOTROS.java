package cl.araucana.cp.distribuidor.business.secciones;

import java.util.HashMap;
import java.util.List;

import cl.araucana.cp.distribuidor.hibernate.beans.ConfigPDFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.SeccionException;

public class SeccionOTROS extends Seccion
{
	public SeccionOTROS(Integer numM, ConvenioVO convenio, HashMap propM, HashMap parametros, List tiposSecciones, HashMap entidadesCCAF)
	{
		super(numM, convenio, propM, parametros, tiposSecciones, entidadesCCAF);
	}

	public long finishProcess(char tipoProceso, SeccionVO seccion) throws SeccionException
	{
		try
		{
			seccion.setConfigPDF(new ConfigPDFVO(12));
			return seccion.getM(getIndice(seccion.getIdTipoSeccion(), seccion.getIdTipoSeccion() + "otrosAFBR"));
		} catch (Exception e)
		{
			this.log.error("problemas finishProcessOTROS", e);
			throw new SeccionException("Problemas en SeccionOTROS:finishProcess:tipoProceso:" + tipoProceso + "::", e);
		}
	}

	public void generar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (cotizante.getIdEntAFBRReal()!=1)
				return;
			if (this.logear) this.log.info("generando OTROS HIJO!!");
			this.numTrab = new int[12];
			this.mTmp = new double[12];
			asignaOTROS(tipoProceso, cotizante.getCotizacion(), seccion.getIdTipoSeccion());
			seccion.setDetallesSeccion(cotizante.getIdEntAFBRReal(), 1, this.mTmp);
		} catch (Exception e)
		{
			this.log.error("problemas generarOTROS", e);
			throw new SeccionException("Problemas en SeccionOTROS:generar:", e);
		}
	}

	public void restar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (cotizante.getIdEntAFBRReal()!=1)
				return;
			if (this.logear) this.log.info("restando OTROS HIJO!!");
			this.numTrab = new int[12];
			this.mTmp = new double[12];
			restaOTROS(tipoProceso, cotizante.getCotizacion(), seccion.getIdTipoSeccion());
			seccion.setDetallesSeccion(cotizante.getIdEntAFBRReal(), -1, this.mTmp);
		} catch (Exception e)
		{
			this.log.error("problemas restarOTROS", e);
			throw new SeccionException("Problemas en SeccionOTROS:restar:", e);
		}
	}

	public void asignaOTROS(char tipoProceso, CotizacionVO cot, int idTipoSeccion) throws SeccionException
	{
		try
		{
			if (tipoProceso == 'R')
			{
				CotizacionREVO cotizacion = (CotizacionREVO)cot;
				if (cotizacion.getOtrosAFBR() > 0)
				{
					setValores(idTipoSeccion, "otrosAFBR", cotizacion.getOtrosAFBR());
				}
			}else if (tipoProceso == 'A')
			{
				CotizacionRAVO cotizacion = (CotizacionRAVO)cot;
				if (cotizacion.getOtrosAFBR() > 0)
				{
					setValores(idTipoSeccion, "otrosAFBR", cotizacion.getOtrosAFBR());
				}
			} 
		} catch (Exception e)
		{
			this.log.error("problemas asignaOTROS", e);
			throw new SeccionException("Problemas en SeccionOTROS:asignaOTROS:", e);
		}
	}
	
	private void restaOTROS(char tipoProceso, CotizacionVO cot, int idTipoSeccion) throws SeccionException
	{
		try
		{
			if (tipoProceso == 'R')
			{
				CotizacionREVO cotizacion = (CotizacionREVO)cot;
				if (cotizacion.getOtrosAFBR() > 0)
				{
					restaValores(idTipoSeccion, "otrosAFBR", cotizacion.getOtrosAFBR());
				}
			} else if (tipoProceso == 'A')
			{
				CotizacionRAVO cotizacion = (CotizacionRAVO)cot;
				if (cotizacion.getOtrosAFBR() > 0)
				{
					restaValores(idTipoSeccion, "otrosAFBR", cotizacion.getOtrosAFBR());
				}
			} 
		} catch (Exception e)
		{
			this.log.error("problemas restaOTROS", e);
			throw new SeccionException("Problemas en SeccionOTROS:restaOTROS:", e);
		}
	}
}
