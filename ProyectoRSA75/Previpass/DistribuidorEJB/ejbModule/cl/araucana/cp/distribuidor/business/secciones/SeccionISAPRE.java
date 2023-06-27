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

public class SeccionISAPRE extends Seccion
{
	public SeccionISAPRE(Integer numM, ConvenioVO convenio, HashMap propM, HashMap parametros, List tiposSecciones, HashMap entidadesCCAF)
	{
		super(numM, convenio, propM, parametros, tiposSecciones, entidadesCCAF);
	}

	public long finishProcess(char tipoProceso, SeccionVO seccion) throws SeccionException
	{
		try
		{
			seccion.setConfigPDF(new ConfigPDFVO(12));
			return seccion.getM(getIndice(seccion.getIdTipoSeccion(), seccion.getIdTipoSeccion() + "saludTotal"));
		} catch (Exception e)
		{
			this.log.error("problemas finishProcessISAPRE", e);
			throw new SeccionException("Problemas en SeccionISAPRE:finishProcess:tipoProceso:" + tipoProceso + "::", e);
		}
	}

	public void generar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (!cotizante.isIsapre())
				return;
			if (this.logear) this.log.info("generando ISAPRE HIJO!!");
			this.numTrab = new int[12];
			this.mTmp = new double[12];
			asignaISAPRE(tipoProceso, cotizante.getCotizacion(), seccion.getIdTipoSeccion());
			seccion.setDetallesSeccion(cotizante.getIdEntSaludReal(), 1, this.mTmp);
		} catch (Exception e)
		{
			this.log.error("problemas generarISAPRE", e);
			throw new SeccionException("Problemas en SeccionISAPRE:generar:", e);
		}
	}

	public void restar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (!cotizante.isIsapre())
				return;
			if (this.logear) this.log.info("restando ISAPRE HIJO!!");
			this.numTrab = new int[12];
			this.mTmp = new double[12];
			restaISAPRE(tipoProceso, cotizante.getCotizacion(), seccion.getIdTipoSeccion());
			seccion.setDetallesSeccion(cotizante.getIdEntSaludReal(), -1, this.mTmp);
		} catch (Exception e)
		{
			this.log.error("problemas restarISAPRE", e);
			throw new SeccionException("Problemas en SeccionISAPRE:restar:", e);
		}
	}

	public void asignaISAPRE(char tipoProceso, CotizacionVO cot, int idTipoSeccion) throws SeccionException
	{
		try
		{
			if (tipoProceso == 'R')
			{
				CotizacionREVO cotizacion = (CotizacionREVO)cot;
				if (cotizacion.getSaludObligatorio() + cotizacion.getSaludAdicional() > 0)
				{
					setValores(idTipoSeccion, "remImpISAPRE", cotizacion.getRentaImp());
					setValores(idTipoSeccion, "saludObligatorio", cotizacion.getSaludObligatorio());
					setValores(idTipoSeccion, "saludAdicional", cotizacion.getSaludAdicional());
					setValores(idTipoSeccion, "saludTotal", cotizacion.getSaludObligatorio() + cotizacion.getSaludAdicional());
				}
			} else if (tipoProceso == 'G')
			{
				CotizacionGRVO cotizacion = (CotizacionGRVO)cot;
				if (cotizacion.getSaludObligatorio() > 0)
				{
					setValores(idTipoSeccion, "saludObligatorio", cotizacion.getSaludObligatorio());
					setValores(idTipoSeccion, "saludTotal", cotizacion.getSaludObligatorio());
				}
			} else if (tipoProceso == 'A')
			{
				CotizacionRAVO cotizacion = (CotizacionRAVO)cot;
				if (cotizacion.getSaludObligatorio() > 0)
				{
					setValores(idTipoSeccion, "saludObligatorio", cotizacion.getSaludObligatorio());
					setValores(idTipoSeccion, "saludTotal", cotizacion.getSaludObligatorio());
				}
			} else if (tipoProceso == 'D')
			{
				//este tipo proceso no tiene esta seccion!!
				this.log.info("se esta tratando de seccionar la seccion ISAPRE, pero el tipoProceso 'D' no cuenta con esta seccion, revisar configuracion properties_mapeo.tipo = 2.");
			}
		} catch (Exception e)
		{
			this.log.error("problemas asignaISAPRE", e);
			throw new SeccionException("Problemas en SeccionISAPRE:asignaISAPRE:", e);
		}
	}
	
	private void restaISAPRE(char tipoProceso, CotizacionVO cot, int idTipoSeccion) throws SeccionException
	{
		try
		{
			if (tipoProceso == 'R')
			{
				CotizacionREVO cotizacion = (CotizacionREVO)cot;
				if (cotizacion.getSaludObligatorio() > 0)
				{
					restaValores(idTipoSeccion, "remImpISAPRE", cotizacion.getRentaImp());
					restaValores(idTipoSeccion, "saludObligatorio", cotizacion.getSaludObligatorio());
					restaValores(idTipoSeccion, "saludAdicional", cotizacion.getSaludAdicional());
					restaValores(idTipoSeccion, "saludTotal", cotizacion.getSaludObligatorio() + cotizacion.getSaludAdicional());
				}
			} else if (tipoProceso == 'G')
			{
				CotizacionGRVO cotizacion = (CotizacionGRVO)cot;
				if (cotizacion.getSaludObligatorio() > 0)
				{
					restaValores(idTipoSeccion, "saludObligatorio", cotizacion.getSaludObligatorio());
					restaValores(idTipoSeccion, "saludTotal", cotizacion.getSaludObligatorio());
				}
			} else if (tipoProceso == 'A')
			{
				CotizacionRAVO cotizacion = (CotizacionRAVO)cot;
				if (cotizacion.getSaludObligatorio() > 0)
				{
					restaValores(idTipoSeccion, "saludObligatorio", cotizacion.getSaludObligatorio());
					restaValores(idTipoSeccion, "saludTotal", cotizacion.getSaludObligatorio());
				}
			} else if (tipoProceso == 'D')
			{
				//este tipo proceso no tiene esta seccion!!
				this.log.info("se esta tratando de seccionar la seccion ISAPRE, pero el tipoProceso 'D' no cuenta con esta seccion, revisar configuracion properties_mapeo.tipo = 2.");
			}
		} catch (Exception e)
		{
			this.log.error("problemas restaISAPRE", e);
			throw new SeccionException("Problemas en SeccionISAPRE:restaISAPRE:", e);
		}
	}
}
