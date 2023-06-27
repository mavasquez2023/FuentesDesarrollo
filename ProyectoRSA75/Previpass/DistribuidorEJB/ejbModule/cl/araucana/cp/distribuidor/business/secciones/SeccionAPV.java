package cl.araucana.cp.distribuidor.business.secciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.SeccionException;

public class SeccionAPV extends Seccion
{
	public SeccionAPV(Integer numM, ConvenioVO convenio, HashMap propM, HashMap parametros, List tiposSecciones, HashMap entidadesCCAF)
	{
		super(numM, convenio, propM, parametros, tiposSecciones, entidadesCCAF);
		//this.logear = true;
	}

	public long finishProcess(char tipoProceso, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (tipoProceso != 'R')
				return 0;
			if (this.logear) this.log.info("TOTAL APV:idSeccion:" + seccion.getIdTipoSeccion() + ":indice:" + getIndice(seccion.getIdTipoSeccion(), seccion.getIdTipoSeccion() + "totalAPV") + ":valor:" + seccion.getM(getIndice(seccion.getIdTipoSeccion(), seccion.getIdTipoSeccion() + "totalAPV")) + "::");
			seccion.setNumTrabajadores(this.numTrabajadoresSeccion);
			if (this.logear) this.log.info("numTrabajadoresSeccion:" + this.numTrabajadoresSeccion + "::");
			return seccion.getM(getIndice(seccion.getIdTipoSeccion(), seccion.getIdTipoSeccion() + "totalAPV"));
		} catch (Exception e)
		{
			this.log.error("problemas finishProcessAPV", e);
			throw new SeccionException("Problemas en SeccionAPV:finishProcess:", e);
		}
	}

	public void generar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (this.logear) this.log.info("generando HIJO APV!!");
			if (tipoProceso != 'R')
				return;
			this.numTrab = new int[12];
			this.mTmp = new double[12];
			List lista = cotizante.getApvList();
			boolean aporta = false;
			List idsDetalles = new ArrayList();//para contar a esta persona solo 1 vez en un detalle, aunque tenga varios aportes
			if (lista != null)
			{
				for (Iterator it = lista.iterator(); it.hasNext();)
				{
					ApvVO apv = (ApvVO)it.next();
					if (this.logear) this.log.info("APV encontrado:" + cotizante.getIdCotizante() + ":monto:" + apv.getAhorro() + ":entidad:" + apv.getIdEntidadApv() + ":id:" + apv.getId() + "::");
					if (apv != null && apv.getAhorro() > 0)//se suma solo si trabajador aporta con algo
					{
						aporta = true;
						int idTipoSeccion = seccion.getIdTipoSeccion();
						long a = setValores(idTipoSeccion, "APVTrabajador",  apv.getAhorro());
						long b = setValores(idTipoSeccion, "APVEmpleador", 0);
						long total = setValores(idTipoSeccion, "totalAPV", apv.getAhorro());
						if (this.logear) this.log.info("\ttrab" + a + ":emp:" + b + ":total:" + total + "::");

//						para contar a esta persona solo 1 vez en un detalle, aunque tenga varios aportes
						int contar = 0;
						Integer idDetalle = new Integer(apv.getIdEntidadApv());
						if (!idsDetalles.contains(idDetalle))
						{
							contar = 1;
							idsDetalles.add(idDetalle);
						}
						seccion.setDetallesSeccion(apv.getIdEntidadApv(), contar, this.mTmp);
					}
				}
			}
			if (aporta)
				this.numTrabajadoresSeccion++;
		} catch (Exception e)
		{
			this.log.error("problemas generarAPV", e);
			throw new SeccionException("Problemas en SeccionAPV:generar", e);
		}
	}

	public void restar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (this.logear) this.log.info("restando HIJO APV!!");
			if (tipoProceso != 'R')
				return;
			this.numTrab = new int[12];
			this.mTmp = new double[12];
			this.numTrabajadoresSeccion = seccion.getNumTrabajadores();

			HashMap apvsEntidades = new HashMap();
			boolean aporta = false;
			for (Iterator it = cotizante.getApvList().iterator(); it.hasNext();)
			{
				ApvVO apv = (ApvVO)it.next();
				if (apv != null)
				{
					if (this.logear && apv.getAhorro() > 0) this.log.info("APV encontrado:" + cotizante.getIdCotizante() + ":monto:" + apv.getAhorro() + ":entidad:" + apv.getIdEntidadApv() + ":id:" + apv.getId() + "::");
					if (apv.getAhorro() > 0)//se suma solo si trabajador aporta con algo
					{
						aporta = true;
						if (apvsEntidades.containsKey("" + apv.getIdEntidadApv()))
						{
							Integer i = (Integer)apvsEntidades.get("" + apv.getIdEntidadApv());
							apvsEntidades.put("" + apv.getIdEntidadApv(), new Integer(apv.getAhorro() + i.intValue()));
						} else
							apvsEntidades.put("" + apv.getIdEntidadApv(), new Integer(apv.getAhorro()));
					}
				}
			}
			for (Iterator it = apvsEntidades.keySet().iterator(); it.hasNext();)
			{
				int idEntidad = new Integer((String)it.next()).intValue();
				int valor = ((Integer)apvsEntidades.get("" + idEntidad)).intValue();
				restaValores(seccion.getIdTipoSeccion(), "APVTrabajador",  valor);
				restaValores(seccion.getIdTipoSeccion(), "totalAPV", valor);
				seccion.setDetallesSeccion(idEntidad, -1, this.mTmp);
			}
			if (aporta)
				this.numTrabajadoresSeccion--;
		} catch (Exception e)
		{
			this.log.error("problemas restarAPV", e);
			throw new SeccionException("Problemas en SeccionAPV:restar", e);
		}
	}
}
