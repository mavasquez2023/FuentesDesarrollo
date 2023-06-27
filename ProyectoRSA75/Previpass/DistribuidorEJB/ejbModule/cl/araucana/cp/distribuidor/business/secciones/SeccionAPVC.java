package cl.araucana.cp.distribuidor.business.secciones;

import java.util.HashMap;
import java.util.List;

import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.SeccionException;

public class SeccionAPVC extends Seccion
{
	public SeccionAPVC(Integer numM, ConvenioVO convenio, HashMap propM, HashMap parametros, List tiposSecciones, HashMap entidadesCCAF)
	{
		super(numM, convenio, propM, parametros, tiposSecciones, entidadesCCAF);
	}

	public long finishProcess(char tipoProceso, SeccionVO seccion) throws SeccionException
	{
		if (tipoProceso != 'R')
			return 0;
		return seccion.getM(getIndice(seccion.getIdTipoSeccion(), seccion.getIdTipoSeccion() + "totalAPVC"));
	}

	public void generar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (this.logear) this.log.info("generando HIJO APVC!!");
			if (tipoProceso != 'R' || cotizante.isAfpVoluntario())
				return;
			this.numTrab = new int[12];
			this.mTmp = new double[12];
			int idTipoSeccion = seccion.getIdTipoSeccion();
			CotizacionREVO cotizacion = (CotizacionREVO)cotizante.getCotizacion();
			long total = setValores(idTipoSeccion, "APVCTrabajador", cotizacion.getApvcAporteTrab());
			total += setValores(idTipoSeccion, "APVCEmpleador", cotizacion.getApvcAporteEmpl());
			if (total > 0)//si no aporta, no suma
			{
				setValores(idTipoSeccion, "totalAPVC", total);
				if (this.logear) this.log.info("\n\nAPVC: trab:" + cotizacion.getApvcAporteTrab() + ":emp:" + cotizacion.getApvcAporteEmpl() + ":total:" + total + "::");
				seccion.setDetallesSeccion(cotizante.getIdEntidadAPVCReal(), 1, this.mTmp);
			}
		} catch (Exception e)
		{
			this.log.error("problemas generarAPVC", e);
			throw new SeccionException("Problemas en SeccionAPVC:generar", e);
		}
	}

	public void restar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (this.logear) this.log.info("restando HIJO APVC!!");
			if (tipoProceso != 'R' || cotizante.isAfpVoluntario())
				return;
			this.numTrab = new int[12];

			this.mTmp = new double[12];
			int idTipoSeccion = seccion.getIdTipoSeccion();
			CotizacionREVO cotizacion = (CotizacionREVO)cotizante.getCotizacion();
			long total = restaValores(idTipoSeccion, "APVCTrabajador", cotizacion.getApvcAporteTrab());
			total += restaValores(idTipoSeccion, "APVCEmpleador", cotizacion.getApvcAporteEmpl());
			if (total > 0)//si no aporta, no suma
			{
				restaValores(idTipoSeccion, "totalAPVC", total);
				if (this.logear) this.log.info("\n\nAPVC: trab:" + cotizacion.getApvcAporteTrab() + ":emp:" + cotizacion.getApvcAporteEmpl() + ":total:" + total + "::");
				seccion.setDetallesSeccion(cotizante.getIdEntidadAPVCReal(), -1, this.mTmp);
			}
		} catch (Exception e)
		{
			this.log.error("problemas restarAPVC", e);
			throw new SeccionException("Problemas en SeccionAPVC:restar", e);
		}
	}
}
