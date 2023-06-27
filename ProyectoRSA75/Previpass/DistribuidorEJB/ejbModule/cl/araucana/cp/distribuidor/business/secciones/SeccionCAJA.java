package cl.araucana.cp.distribuidor.business.secciones;

import java.util.HashMap;
import java.util.List;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.SeccionException;

public class SeccionCAJA extends Seccion
{
	public SeccionCAJA(Integer numM, ConvenioVO convenio, HashMap propM, HashMap parametros, List tiposSecciones, HashMap entidadesCCAF)
	{
		super(numM, convenio, propM, parametros, tiposSecciones, entidadesCCAF);
	}

	public long finishProcess(char tipoProceso, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (tipoProceso == 'D')
				return 0;
			if (this.convenio.getIdCcaf() == Constants.ID_INP)// no considerar si INP actua como ccaf
				return 0;
			if (this.logear)
				this.log.info("\n\nsetConfigPDF CAJA");
			int idTipoSeccion = seccion.getIdTipoSeccion();

			if (tipoProceso == 'R')
			{
				int posFONASA = getIndice(idTipoSeccion, idTipoSeccion + "ccafAporte");
				EntidadCCAFVO caja = (EntidadCCAFVO) this.entidadesCCAF.get("" + this.convenio.getIdCcaf());
				int posCreditos = -1, posSeguros = -1, posLeasig = -1, posDental = -1, posASFAM = -1;

				if (caja.getAsigFam() == 1)
					posASFAM = getIndice(idTipoSeccion, idTipoSeccion + "asigFamiliar");
				if (caja.getCreditos() == 1)
					posCreditos = getIndice(idTipoSeccion, idTipoSeccion + "ccafCredito");
				if (caja.getSegurosVida() == 1)
					posSeguros = getIndice(idTipoSeccion, idTipoSeccion + "ccafSeguro");
				if (caja.getLeasing() == 1)
					posLeasig = getIndice(idTipoSeccion, idTipoSeccion + "ccafLeasing");
				if (caja.getDental() == 1)
					posDental = getIndice(idTipoSeccion, idTipoSeccion + "ccafDental");

				this.asterisco = "";
				for (int i = 0; i < this.numM; i++)
					if (i == posFONASA || i == posASFAM || i == posCreditos || i == posSeguros || i == posLeasig || i == posDental)
						this.asterisco += "1";
					else
						this.asterisco += "0";
			}
			seccion.setConfigPDF(this.asterisco, this.noMostrar, this.numTrab);
			seccion.setNumTrabajadores(this.numTrabajadoresSeccion);
			if (this.logear)
				this.log.info("numTrabs en caja:" + this.numTrabajadoresSeccion + "::");
			return seccion.getM(getIndice(idTipoSeccion, idTipoSeccion + "totalCaja"));
		} catch (Exception e)
		{
			this.log.error("problemas finishProcessCAJA", e);
			throw new SeccionException("Problemas en SeccionCAJA:finishProcess:", e);
		}
	}

	public void generar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (this.convenio.getIdCcaf() == Constants.ID_INP)// no considerar si INP actua como ccaf
				return;
			if (this.logear)
				this.log.info("generando HIJO CAJA!!");
			this.mTmp = new double[12];

			int idTipoSeccion = seccion.getIdTipoSeccion();
			asignaCAJA(tipoProceso, !cotizante.isIsapre(), cotizante.getCotizacion(), idTipoSeccion);
			if (this.cuenta)
			{
				seccion.setDetallesSeccion(this.convenio.getIdCcaf(), 1, this.mTmp);
				if (tipoProceso == 'R')
					calculaTotalAsignaCAJA(seccion, idTipoSeccion,this.convenio.getIdCcaf());
			}
		} catch (Exception e)
		{
			this.log.error("problemas generarCAJA", e);
			throw new SeccionException("Problemas en SeccionCAJA:generar:", e);
		}
	}

	public void restar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			/*
			 * GMALLEA 26-01-2012
			 *	Se agrega la validacion por detalle seccion si es null significa que no se creo desde la tabla, con esto el sistema toma los valores del covenio
			 */
			if ( seccion.getDetallesSeccion() == null) //this.convenio.getIdCcaf() == Constants.ID_INP  ||)// no considerar si INP actua como ccaf
				return;
			if (this.logear)
				this.log.info("restando HIJO CAJA!!");
			
			int idDetalleSeccion = 0;
			for (int i = 0; i < seccion.getDetallesSeccion().size(); i++){
				DetalleSeccionVO det = (DetalleSeccionVO) seccion.getDetallesSeccion().get(i);
				idDetalleSeccion = det.getIdDetalleSeccion();
			}
			
			this.mTmp = new double[12];
			this.numTrab = seccion.getConfigPDF().getNumTrab();
			this.numTrabajadoresSeccion = seccion.getNumTrabajadores();

			int idTipoSeccion = seccion.getIdTipoSeccion();
			restaCAJA(tipoProceso, !cotizante.isIsapre(), cotizante.getCotizacion(), idTipoSeccion);

			if (this.cuenta)
			{				
				seccion.setDetallesSeccion((idDetalleSeccion == 0 ? this.convenio.getIdCcaf() : idDetalleSeccion) , -1, this.mTmp);
				if (tipoProceso == 'R')
					calculaTotalAsignaCAJA(seccion, idTipoSeccion,(idDetalleSeccion == 0 ? this.convenio.getIdCcaf() : idDetalleSeccion) );
			}
		} catch (Exception e)
		{
			this.log.error("problemas restarCAJA", e);
			throw new SeccionException("Problemas en SeccionCAJA:restar:", e);
		}
	}

	public void asignaCAJA(char tipoProceso, boolean isFONASA, CotizacionVO cot, int idTipoSeccion) throws SeccionException
	{
		try
		{
			this.cuenta = false;
			if (tipoProceso == 'R')
			{
				CotizacionREVO cotizacion = (CotizacionREVO) cot;
				//if (isFONASA)
				if(cotizacion.getCcafAporte() > 0)
				{
					setValores(idTipoSeccion, "ccafAporte", cotizacion.getCcafAporte());
					this.cuenta = true;
				}
				int totalAsigFamiliar = cotizacion.getAsigFamiliar() + cotizacion.getAsigFamRetro() - cotizacion.getAsigFamReint();
				//si es totalAsigFamiliar siempre se ejecuta gamllea
				//if (totalAsigFamiliar < 0)
				//{
					this.cuenta = true;
					setValores(idTipoSeccion, "asigFamiliar", totalAsigFamiliar);
				//}

				if (cotizacion.getCcafCredito() > 0)
				{
					this.cuenta = true;
					setValores(idTipoSeccion, "ccafCredito", cotizacion.getCcafCredito());
				}
				if (cotizacion.getCcafDental() > 0)
				{
					this.cuenta = true;
					setValores(idTipoSeccion, "ccafDental", cotizacion.getCcafDental());
				}
				if (cotizacion.getCcafLeasing() > 0)
				{
					this.cuenta = true;
					setValores(idTipoSeccion, "ccafLeasing", cotizacion.getCcafLeasing());
				}
				if (cotizacion.getCcafSeguro() > 0)
				{
					this.cuenta = true;
					setValores(idTipoSeccion, "ccafSeguro", cotizacion.getCcafSeguro());
				}
				if (this.cuenta)
				{
					if (this.logear)
						this.log.info("contando en caja:" + cotizacion.getIdCotizante() + "::");
					this.numTrabajadoresSeccion++;
				}
			} else if (tipoProceso == 'G')
			{
				CotizacionGRVO cotizacion = (CotizacionGRVO) cot;
				if (isFONASA)
				{
					this.cuenta = true;
					setValores(idTipoSeccion, "totalCaja", cotizacion.getCcafAporte());
					this.numTrabajadoresSeccion++;
				}
			} else if (tipoProceso == 'A')
			{
				CotizacionRAVO cotizacion = (CotizacionRAVO) cot;
				if (isFONASA)
				{
					this.cuenta = true;
					setValores(idTipoSeccion, "totalCaja", cotizacion.getCcafAporte());
					this.numTrabajadoresSeccion++;
				}
			} else if (tipoProceso == 'D')
			{
				// este tipo proceso no tiene esta seccion!!
				if (this.logear)
					this.log.info("se esta tratando de seccionar la seccion CAJA, pero el tipoProceso 'D' no cuenta con esta seccion, revisar configuracion properties_mapeo.tipo = 2.");
			}
			if (this.logear)
				this.log.info("seccionCCAF:cuenta:" + this.cuenta + "::");
		} catch (Exception e)
		{
			this.log.error("problemas asignaCAJA", e);
			throw new SeccionException("Problemas en SeccionCAJA:asignaCAJA:", e);
		}
	}

	public void calculaTotalAsignaCAJA(SeccionVO seccion, int idTipoSeccion,int idDetalleSeccion) throws SeccionException
	{
		try
		{
			long subTotalCompensado = 0, subTotalOtros = 0;
			EntidadCCAFVO caja = (EntidadCCAFVO) this.entidadesCCAF.get("" + idDetalleSeccion);
			DetalleSeccionVO detalle = seccion.getDetalleSeccion(idDetalleSeccion);
			if (detalle == null)
				detalle = new DetalleSeccionVO();

			if (detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafAporte")) > 0)
				subTotalCompensado += detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafAporte"));

			//if (detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "asigFamiliar")) > 0)
			{
				if (caja.getAsigFam() == 1)
					subTotalCompensado -= detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "asigFamiliar"));
				else
					subTotalCompensado += detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "asigFamiliar"));
			}
			if (detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafCredito")) > 0)
			{
				if (caja.getCreditos() == 1){
					subTotalCompensado += detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafCredito"));
				}
				else
					subTotalOtros += detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafCredito"));
			}
			if (detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafDental")) > 0)
			{
				if (caja.getDental() == 1){
					subTotalCompensado += detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafDental"));
				}
				else
					subTotalOtros += detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafDental"));
			}
			if (detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafLeasing")) > 0)
			{
				if (caja.getLeasing() == 1){
					subTotalCompensado += detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafLeasing"));
				}
				else
					subTotalOtros += detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafLeasing"));
			}
			if (detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafSeguro")) > 0)
			{
				if (caja.getSegurosVida() == 1){
					subTotalCompensado += detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafSeguro"));
				}
				else
					subTotalOtros += detalle.getM(getIndice(idTipoSeccion, idTipoSeccion + "ccafSeguro"));
			}

			if (subTotalCompensado > 0) {// SFI
				subTotalOtros = subTotalOtros + subTotalCompensado;
			}
			seccion.setDetallesSeccionTotalCaja(idDetalleSeccion, getIndice(idTipoSeccion, idTipoSeccion + "subTotalCompensado"), getIndice(idTipoSeccion, idTipoSeccion + "totalCaja"),
					subTotalCompensado, subTotalOtros);

		} catch (Exception e)
		{
			this.log.error("problemas calculaTotalCAJA", e);
			throw new SeccionException("Problemas en SeccionCAJA:asignaCAJA:", e);
		}
	}

	private void restaCAJA(char tipoProceso, boolean isFONASA, CotizacionVO cot, int idTipoSeccion) throws SeccionException
	{
		try
		{
			this.cuenta = false;
			if (tipoProceso == 'R')
			{
				CotizacionREVO cotizacion = (CotizacionREVO) cot;
				//if (isFONASA)
				if (cotizacion.getCcafAporte() > 0)
				{
					restaValores(idTipoSeccion, "ccafAporte", cotizacion.getCcafAporte());
					this.cuenta = true;
				}
				int totalAsigFamiliar = cotizacion.getAsigFamiliar() + cotizacion.getAsigFamRetro() - cotizacion.getAsigFamReint();
				//if (totalAsigFamiliar > 0)
				{
					this.cuenta = true;
					restaValores(idTipoSeccion, "asigFamiliar", totalAsigFamiliar);
				}

				if (cotizacion.getCcafCredito() > 0)
				{
					this.cuenta = true;
					restaValores(idTipoSeccion, "ccafCredito", cotizacion.getCcafCredito());
				}
				if (cotizacion.getCcafDental() > 0)
				{
					this.cuenta = true;
					restaValores(idTipoSeccion, "ccafDental", cotizacion.getCcafDental());
				}
				if (cotizacion.getCcafLeasing() > 0)
				{
					this.cuenta = true;
					restaValores(idTipoSeccion, "ccafLeasing", cotizacion.getCcafLeasing());
				}
				if (cotizacion.getCcafSeguro() > 0)
				{
					this.cuenta = true;
					restaValores(idTipoSeccion, "ccafSeguro", cotizacion.getCcafSeguro());
				}
				if (this.cuenta)
				{
					if (this.logear)
						this.log.info("contando en caja:" + cotizacion.getIdCotizante() + "::");
					this.numTrabajadoresSeccion--;
				}
			} else if (tipoProceso == 'G')
			{
				CotizacionGRVO cotizacion = (CotizacionGRVO) cot;
				if (isFONASA)
				{
					this.cuenta = true;
					restaValores(idTipoSeccion, "totalCaja", cotizacion.getCcafAporte());
					this.numTrabajadoresSeccion--;
				}
			} else if (tipoProceso == 'A')
			{
				CotizacionRAVO cotizacion = (CotizacionRAVO) cot;
				if (isFONASA)
				{
					this.cuenta = true;
					restaValores(idTipoSeccion, "totalCaja", cotizacion.getCcafAporte());
					this.numTrabajadoresSeccion--;
				}
			} else if (tipoProceso == 'D')
			{
				// este tipo proceso no tiene esta seccion!!
				this.log.info("se esta tratando de seccionar la seccion CAJA, pero el tipoProceso 'D' no cuenta con esta seccion, revisar configuracion properties_mapeo.tipo = 2.");
			}
			if (this.logear)
				this.log.info("seccionCCAF:cuenta:" + this.cuenta + "::");
		} catch (Exception e)
		{
			this.log.error("problemas restaCAJA", e);
			throw new SeccionException("Problemas en SeccionCAJA:restaCAJA:", e);
		}
	}
}
