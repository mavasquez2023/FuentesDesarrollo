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
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.SeccionException;

public class SeccionINP extends Seccion
{
	public SeccionINP(Integer numM, ConvenioVO convenio, HashMap propM, HashMap parametros, List tiposSecciones, HashMap entidadesCCAF)
	{
		super(numM, convenio, propM, parametros, tiposSecciones, entidadesCCAF);
	}

	public long finishProcess(char tipoProceso, SeccionVO seccion) throws SeccionException
	{
		/*try
		{
			if (tipoProceso == 'D')
				return 0;
			int idTipoSeccion = seccion.getIdTipoSeccion();
			int posASFAM = -1, posMUTUAL = -1;
			if (tipoProceso == 'R' && this.convenio.getIdCcaf() != Constants.ID_INP)// no mostrar ASFAM si empresa tiene CAJA (distinta de INP)
				posASFAM = getIndice(idTipoSeccion, idTipoSeccion + "asigFamiliar");

			if (this.convenio.getIdMutual() != Constants.ID_INP)// no mostrar inpMutual si empresa tiene MUTUAL (distinta de INP)
				posMUTUAL = getIndice(idTipoSeccion, idTipoSeccion + "inpMutual");

			if (posASFAM > -1 || posMUTUAL > -1)
			{
				this.noMostrar = "";
				for (int i = 0; i < this.numM; i++)
					if (i == posMUTUAL || i == posASFAM)
						this.noMostrar += "1";
					else
						this.noMostrar += "0";
			}
			seccion.setConfigPDF(this.asterisco, this.noMostrar, this.numTrab);
			seccion.setNumTrabajadores(this.numTrabajadoresSeccion);
			if (seccion.getDetallesSeccion() != null && seccion.getDetallesSeccion().size() > 0)
				((DetalleSeccionVO)seccion.getDetallesSeccion().get(0)).setNumTrabajadores(this.numTrabajadoresSeccion);
			return seccion.getM(getIndice(idTipoSeccion, idTipoSeccion + "totalINP"));
		} catch (Exception e)
		{
			this.log.error("problemas finishProcessINP", e);
			throw new SeccionException("Problemas en SeccionINP:finishProcess:", e);
		}*/
		return 0;
	}

	public void generar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		/*try
		{
			if (this.logear)
				this.log.info("generando HIJO INP!!");
			this.mTmp = new double[12];
			this.numTrabajadoresSeccion = seccion.getNumTrabajadores();
			//int idTipoSeccion = seccion.getIdTipoSeccion();

			//TODO csanchez
			//Cada vez que se creaba un uevo trabajador, se asumía que no existía ninguno previo.
			if (seccion.getConfigPDF().getNumTrab() != null)
				this.numTrab = seccion.getConfigPDF().getNumTrab();

			asignaINP(tipoProceso, cotizante.getTipoPrevision() == 2, !cotizante.isIsapre(), cotizante.getCotizacion(), seccion.getIdTipoSeccion());
			if (this.cuenta){
				seccion.setDetallesSeccion(Constants.ID_INP, 1, this.mTmp);
			}
			seccion.setNumTrabajadores(this.numTrabajadoresSeccion);
		} catch (Exception e)
		{
			this.log.error("problemas generarINP", e);
			throw new SeccionException("Problemas en SeccionINP:generar:", e);
		}*/
	}

	public void restar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		/*try
		{
			if (this.logear)
				this.log.info("restando HIJO INP!!");

			this.mTmp = new double[12];
			this.numTrab = seccion.getConfigPDF().getNumTrab();
			this.numTrabajadoresSeccion = seccion.getNumTrabajadores();
			restaINP(tipoProceso, cotizante.getTipoPrevision() == 2, !cotizante.isIsapre(), cotizante.getCotizacion(), seccion.getIdTipoSeccion());
			if (this.cuenta)
				seccion.setDetallesSeccion(Constants.ID_INP, -1, this.mTmp);
			seccion.setNumTrabajadores(this.numTrabajadoresSeccion);
		} catch (Exception e)
		{
			this.log.error("problemas restarINP", e);
			throw new SeccionException("Problemas en SeccionINP:restar:", e);
		}*/
	}

	public void asignaINP(char tipoProceso, boolean isINP, boolean isFonasa, CotizacionVO cot, int idTipoSeccion) throws SeccionException
	{
		/*try
		{
			long subTotalCotizaciones = 0, subTotalRebajas = 0;
			this.cuenta = false;

			if (tipoProceso == 'R')
			{
				CotizacionREVO cotizacion = (CotizacionREVO) cot;
				if (this.logear)
					this.log.info("INP:" + cotizacion.getIdCotizante() + "::" + isINP + "::");
				if (isINP)// o sea, es INP
				{
					this.cuenta = true;
					if (this.logear)
						this.log.info("contando INP:" + cotizacion.getIdCotizante() + ":getRentaImp:" + cotizacion.getRentaImp() + "::" + cotizacion.getPrevisionObligatorio() + "::");
					setValores(idTipoSeccion, "remImpPension", cotizacion.getRentaImp());
					subTotalCotizaciones += setValores(idTipoSeccion, "previsionObligatorio", cotizacion.getPrevisionObligatorio());
				}

				if (isFonasa)
				{
					this.cuenta = true;
					subTotalCotizaciones += setValores(idTipoSeccion, "saludObligatorio", cotizacion.getSaludObligatorio());
				}

				if (this.convenio.getIdMutual() == Constants.ID_INP && cotizacion.getInpMutual() > 0)
				{
					this.cuenta = true;
					subTotalCotizaciones += setValores(idTipoSeccion, "inpMutual", cotizacion.getInpMutual());
				}

				if (cotizacion.getInpDesahucio() > 0)
				{
					this.cuenta = true;
					subTotalCotizaciones += setValores(idTipoSeccion, "inpDesahucio", cotizacion.getInpDesahucio());
				}

				if (this.cuenta)
					setValores(idTipoSeccion, "subTotalCotizaciones", subTotalCotizaciones);
				if (this.convenio.getIdCcaf() == Constants.ID_INP && (cotizacion.getAsigFamiliar() + cotizacion.getAsigFamRetro() - cotizacion.getAsigFamReint() ) > 0)
				{
					this.cuenta = true;
					subTotalRebajas += setValores(idTipoSeccion, "asigFamiliar", cotizacion.getAsigFamiliar() + cotizacion.getAsigFamRetro() - cotizacion.getAsigFamReint());
				}
				if (subTotalRebajas > 0)
					this.cuenta = true;

				if (cotizacion.getInpBonificacion() > 0)
				{
					this.cuenta = true;
					subTotalRebajas += setValores(idTipoSeccion, "inpBonificacion", cotizacion.getInpBonificacion());
				}

				setValores(idTipoSeccion, "subTotalRebajas", subTotalRebajas);
				
				long totalINP= subTotalCotizaciones - subTotalRebajas;
				
				setValores(idTipoSeccion, "subTotal", totalINP);
				
				if(totalINP < 0)
					totalINP=0;
				
				setValores(idTipoSeccion, "totalINP", totalINP);
				
				if (this.cuenta)
					this.numTrabajadoresSeccion++;	
				
			} else if (tipoProceso == 'G')
			{
				CotizacionGRVO cotizacion = (CotizacionGRVO) cot;
				if (isINP)// o sea, es INP
				{
					this.cuenta = true;
					subTotalCotizaciones += setValores(idTipoSeccion, "previsionObligatorio", cotizacion.getPrevisionObligatorio());
				}
				if (isFonasa)
				{
					this.cuenta = true;
					subTotalCotizaciones += setValores(idTipoSeccion, "saludObligatorio", cotizacion.getSaludObligatorio());
				}

				if (this.convenio.getIdMutual() == Constants.ID_INP)
				{
					this.cuenta = true;
					subTotalCotizaciones += setValores(idTipoSeccion, "inpMutual", cotizacion.getInpMutual());
				}

				if (this.cuenta)
				{
					this.numTrabajadoresSeccion++;
					subTotalCotizaciones += setValores(idTipoSeccion, "totalINP", subTotalCotizaciones);
				}
			} else if (tipoProceso == 'A')
			{
				CotizacionRAVO cotizacion = (CotizacionRAVO) cot;
				if (isINP)// o sea, es INP
				{
					this.cuenta = true;
					subTotalCotizaciones += setValores(idTipoSeccion, "previsionObligatorio", cotizacion.getPrevisionObligatorio());
				}
				if (isFonasa)
				{
					this.cuenta = true;
					subTotalCotizaciones += setValores(idTipoSeccion, "saludObligatorio", cotizacion.getSaludObligatorio());
				}

				if (this.convenio.getIdMutual() == Constants.ID_INP)
				{
					this.cuenta = true;
					subTotalCotizaciones += setValores(idTipoSeccion, "inpMutual", cotizacion.getInpMutual());
				}

				if (this.cuenta)
				{
					this.numTrabajadoresSeccion++;
					subTotalCotizaciones += setValores(idTipoSeccion, "totalINP", subTotalCotizaciones);
				}
			} else if (tipoProceso == 'D')
			{
				// este tipo proceso no tiene esta seccion!!
				this.log.info("se esta tratando de seccionar la seccion INP, pero el tipoProceso 'D' no cuenta con esta seccion, revisar configuracion properties_mapeo.tipo = 2.");
			}
		} catch (Exception e)
		{
			this.log.error("problemas asignaINP", e);
			throw new SeccionException("Problemas en SeccionINP:asignaINP:", e);
		}*/
	}

	private void restaINP(char tipoProceso, boolean isINP, boolean isFonasa, CotizacionVO cot, int idTipoSeccion) throws SeccionException
	{
		/*try
		{
			long subTotalCotizaciones = 0, subTotalRebajas = 0, totalINP=0;

			if (tipoProceso == 'R')
			{
				CotizacionREVO cotizacion = (CotizacionREVO) cot;
				
				if (isINP && cotizacion.getPrevisionObligatorio() > 0)// o sea, es INP
				{
					this.cuenta = true;
					restaValores(idTipoSeccion, "remImpPension", cotizacion.getRentaImp());
					subTotalCotizaciones += restaValores(idTipoSeccion, "previsionObligatorio", cotizacion.getPrevisionObligatorio());
				}

				if (isFonasa && cotizacion.getSaludObligatorio() > 0)
				{
				
					this.cuenta = true;
					subTotalCotizaciones += restaValores(idTipoSeccion, "saludObligatorio", cotizacion.getSaludObligatorio());
				}
				//GMALLEA 01-02-2012 Se pregunta si el comprobante antiguo tiene INP Mutual, si es esi se resta el valor.. 
				//if ( this.convenio.getIdMutual() == Constants.ID_INP ){
				if (cotizacion.isINPMutualOld()){
					if(cotizacion.getInpMutual() > 0 ){
									
							System.out.println("Entro a restar mutual inp");	
							this.cuenta = true;
							subTotalCotizaciones += restaValores(idTipoSeccion, "inpMutual", cotizacion.getInpMutual());
				
					}
				}
				
				if (cotizacion.getInpDesahucio() > 0)
				{
					this.cuenta = true;
					subTotalCotizaciones += restaValores(idTipoSeccion, "inpDesahucio", cotizacion.getInpDesahucio());
				}

				if (this.cuenta)
					restaValores(idTipoSeccion, "subTotalCotizaciones", subTotalCotizaciones);

				if (this.convenio.getIdCcaf() == Constants.ID_INP && (cotizacion.getAsigFamiliar() + cotizacion.getAsigFamRetro() - cotizacion.getAsigFamReint() > 0))
				{
					this.cuenta = true;
					subTotalRebajas += restaValores(idTipoSeccion, "asigFamiliar", cotizacion.getAsigFamiliar() + cotizacion.getAsigFamRetro() - cotizacion.getAsigFamReint());
				}
				if (subTotalRebajas > 0)
					this.cuenta = true;

				if (cotizacion.getInpBonificacion() > 0)
				{
					this.cuenta = true;
					subTotalRebajas += restaValores(idTipoSeccion, "inpBonificacion", cotizacion.getInpBonificacion());
				}

				restaValores(idTipoSeccion, "subTotalRebajas", subTotalRebajas);
				totalINP= subTotalCotizaciones - subTotalRebajas;
				restaValores(idTipoSeccion, "totalINP", totalINP);
				
				if (this.cuenta)
					this.numTrabajadoresSeccion--;
			} else if (tipoProceso == 'G')
			{
				CotizacionGRVO cotizacion = (CotizacionGRVO) cot;
				if (isINP)// o sea, es INP
				{
					this.cuenta = true;
					subTotalCotizaciones += restaValores(idTipoSeccion, "previsionObligatorio", cotizacion.getPrevisionObligatorio());
				}
				if (isFonasa)
				{
					this.cuenta = true;
					subTotalCotizaciones += restaValores(idTipoSeccion, "saludObligatorio", cotizacion.getSaludObligatorio());
				}

				if (this.convenio.getIdMutual() == Constants.ID_INP)
				{
					this.cuenta = true;
					subTotalCotizaciones += restaValores(idTipoSeccion, "inpMutual", cotizacion.getInpMutual());
				}

				if (this.cuenta)
				{
					this.numTrabajadoresSeccion--;
					subTotalCotizaciones += restaValores(idTipoSeccion, "totalINP", subTotalCotizaciones);
				}
			} else if (tipoProceso == 'A')
			{
				CotizacionRAVO cotizacion = (CotizacionRAVO) cot;
				if (isINP)// o sea, es INP
				{
					this.cuenta = true;
					subTotalCotizaciones += restaValores(idTipoSeccion, "previsionObligatorio", cotizacion.getPrevisionObligatorio());
				}
				if (isFonasa)
				{
					this.cuenta = true;
					subTotalCotizaciones += restaValores(idTipoSeccion, "saludObligatorio", cotizacion.getSaludObligatorio());
				}

				if (this.convenio.getIdMutual() == Constants.ID_INP)
				{
					this.cuenta = true;
					subTotalCotizaciones += restaValores(idTipoSeccion, "inpMutual", cotizacion.getInpMutual());
				}

				if (this.cuenta)
				{
					this.numTrabajadoresSeccion--;
					subTotalCotizaciones += restaValores(idTipoSeccion, "totalINP", subTotalCotizaciones);
				}
			} else if (tipoProceso == 'D')
			{
				// este tipo proceso no tiene esta seccion!!
				this.log.info("se esta tratando de seccionar la seccion INP, pero el tipoProceso 'D' no cuenta con esta seccion, revisar configuracion properties_mapeo.tipo = 2.");
			}
		} catch (Exception e)
		{
			this.log.error("problemas restaINP", e);
			throw new SeccionException("Problemas en SeccionINP:restaINP:", e);
		}*/
	}
	public void calculaTotalINP(SeccionVO seccion, int idTipoSeccion) throws SeccionException
	{
		/*try
		{	
			long totalINP = 0;
			long totalSubTotalCotizaciones = 0;
			long totalSubTotalRebajas = 0;

			DetalleSeccionVO detalle = seccion.getDetalleSeccion(0);
			if (detalle == null){
				detalle = new DetalleSeccionVO();
			}
			int indiceTotalINP= getIndice(idTipoSeccion, idTipoSeccion + "totalINP");
			int indiceSubTotalINP= getIndice(idTipoSeccion, idTipoSeccion + "subTotal");
			int indiceSubTotalCotizaciones= getIndice(idTipoSeccion, idTipoSeccion + "subTotalCotizaciones");
			int indiceSubTotalRebajas= getIndice(idTipoSeccion, idTipoSeccion + "subTotalRebajas");

			totalSubTotalCotizaciones= (long)detalle.getM(indiceSubTotalCotizaciones);
			totalSubTotalRebajas= (long)detalle.getM(indiceSubTotalRebajas);
			
			totalINP = totalSubTotalCotizaciones - totalSubTotalRebajas;
			seccion.setDetallesSeccionArbitrario(0, indiceSubTotalINP, totalINP);			
			
			if (totalINP < 0){
				seccion.setDetallesSeccionArbitrario(0, indiceTotalINP, 0);
			}else{
				seccion.setDetallesSeccionArbitrario(0, indiceTotalINP, totalINP);
			}

		} catch (Exception e)
		{
			this.log.error("problemas calculaTotalINP", e);
			throw new SeccionException("Problemas en SeccionINP:asignaINP:", e);
		}*/
	}
}
