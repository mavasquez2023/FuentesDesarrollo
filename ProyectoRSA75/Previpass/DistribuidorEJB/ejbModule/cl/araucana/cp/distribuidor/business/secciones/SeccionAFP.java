package cl.araucana.cp.distribuidor.business.secciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.SeccionException;

public class SeccionAFP extends Seccion
{
	private static Logger logger = Logger.getLogger(SeccionAFP.class);
	private int soloAFC = 0;

	public SeccionAFP(Integer numM, ConvenioVO convenio, HashMap propM, HashMap parametros, List tiposSecciones, HashMap entidadesCCAF)
	{
		super(numM, convenio, propM, parametros, tiposSecciones, entidadesCCAF);
	}

	public long finishProcess(char tipoProceso, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (this.logear)
				this.log.info("\n\nfinishProcess AFP");
			int idTipoSeccion = seccion.getIdTipoSeccion();
			if (tipoProceso == 'D' || (tipoProceso == 'R' && idTipoSeccion == Constants.SECCION_DEPOSITOCONVENIDO))// idTipoSeccion, "depositoConvenido"
				return seccion.getM(getIndice(idTipoSeccion, idTipoSeccion + "depositoConvenido")) + seccion.getM(getIndice(idTipoSeccion, idTipoSeccion + "indemAporte"));
			if (this.soloAFC > 0)// asteriscos para solo AFC (pensionados INP declarando AFC)
			{
				int posSegCesTrab = getIndice(idTipoSeccion, idTipoSeccion + "segCesTrab");
				int posSegCesEmpl = getIndice(idTipoSeccion, idTipoSeccion + "segCesEmpl");
				int posTotalAFC = getIndice(idTipoSeccion, idTipoSeccion + "subTotalAFC");
				this.asterisco = "";
				for (int i = 0; i < this.numM; i++)
					if (i == posSegCesTrab || i == posSegCesEmpl || i == posTotalAFC)
						this.asterisco += "1";
					else
						this.asterisco += "0";
			}
			int posTrabPesado = getIndice(idTipoSeccion, idTipoSeccion + "trabPesado");
			if (seccion.getM(posTrabPesado) == 0)
			{
				this.noMostrar = "";
				for (int i = 0; i < this.numM; i++)
					if (i == posTrabPesado)
						this.noMostrar += "1";
					else
						this.noMostrar += "0";
			}
			seccion.setConfigPDF(this.asterisco, this.noMostrar, this.numTrab);
			return seccion.getM(getIndice(idTipoSeccion, idTipoSeccion + "previsionTotal"));
		} catch (Exception e)
		{
			this.log.error("problemas finishProcessAFP", e);
			throw new SeccionException("Problemas en SeccionAFP:finishProcess", e);
		}
	}

	public void generar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (this.logear)
				this.log.info("generando HIJO AFP!! tipoPrevision:" + cotizante.getTipoPrevision() + ":tipoProceso:" + tipoProceso + "::");
			this.mTmp = new double[12];
			asignaAFP(tipoProceso, cotizante.getTipoPrevision(), cotizante.getCotizacion(), seccion.getIdTipoSeccion());
			if (cotizante.isAfpVoluntario())
			{
				if (this.logear)
					this.log.info("adding voluntario:" + cotizante.getIdEntidadAFPVReal() + "::");
				seccion.setDetallesSeccion(cotizante.getIdEntidadAFPVReal(), 1, this.mTmp);
			} else if (tipoProceso == 'D')
				seccion.setDetallesSeccion(((CotizacionDCVO)cotizante.getCotizacion()).getIdEntDep(), 1, this.mTmp);
			else if(tipoProceso == 'R' && seccion.getIdTipoSeccion() == Constants.SECCION_DEPOSITOCONVENIDO)
				seccion.setDetallesSeccion(((CotizacionREVO)cotizante.getCotizacion()).getIdEntDep(), 1, this.mTmp);
			else if (cotizante.getTipoPrevision() == Seccion.IS_AFP)
				seccion.setDetallesSeccion(cotizante.getIdEntPensionReal(), 1, this.mTmp);
			else // es INP, solo cotiza seg de cesantia (AFC) en una AFP
			{
				logger.debug("AFC::" + cotizante.getIdCotizante() + "::" + cotizante.getIdEntAfc() + "::" + cotizante.getIdEntAfcReal() + "::" + cotizante.getTipoPrevision() + "::");
				seccion.setDetallesSeccion(cotizante.getIdEntAfcReal(), 1, this.mTmp);
			}
		} catch (Exception e)
		{
			this.log.error("problemas generarAFP", e);
			throw new SeccionException("Problemas en SeccionAFP:generar", e);
		}
	}

	public void restar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (this.logear)
				this.log.info("restando HIJO AFP!! tipoPrevision:" + cotizante.getTipoPrevision() + ":tipoProceso:" + tipoProceso + "::");

			this.mTmp = new double[12];
			restaAFP(tipoProceso, cotizante.getTipoPrevision(), cotizante.getCotizacion(), seccion.getIdTipoSeccion());

			if (cotizante.isAfpVoluntario())
			{
				if (this.logear)
					this.log.info("resta voluntario:" + cotizante.getIdEntidadAFPVReal() + "::");
				seccion.setDetallesSeccion(cotizante.getIdEntidadAFPVReal(), -1, this.mTmp);
			} else if (tipoProceso == 'D')
				seccion.setDetallesSeccion(((CotizacionDCVO)cotizante.getCotizacion()).getIdEntDep(), -1, this.mTmp);
			else if(tipoProceso == 'R' && seccion.getIdTipoSeccion() == Constants.SECCION_DEPOSITOCONVENIDO)
				seccion.setDetallesSeccion(((CotizacionREVO)cotizante.getCotizacion()).getIdEntDep(), -1, this.mTmp);
			else if (cotizante.getTipoPrevision() == Seccion.IS_AFP)
				seccion.setDetallesSeccion(cotizante.getIdEntPensionReal(), -1, this.mTmp);
			else
			// es INP, solo cotiza seg de cesantia (AFC) en una AFP
			{
				logger.debug("AFC::" + cotizante.getIdCotizante() + "::" + cotizante.getIdEntAfc() + "::" + cotizante.getIdEntAfcReal() + "::" + cotizante.getTipoPrevision() + "::");
				seccion.setDetallesSeccion(cotizante.getIdEntAfcReal(), -1, this.mTmp);
			}
		} catch (Exception e)
		{
			this.log.error("problemas restarAFP", e);
			throw new SeccionException("Problemas en SeccionAFP:restar", e);
		}
	}
	
	
//	private int getPrevisionSIS(char tipoProceso, CotizacionVO cotizacion) {
//		int monto = 0;
//		if (tipoProceso == 'R') {
//			monto = ((CotizacionREVO)cotizacion).getPrevisionSIS();
//		}else if (tipoProceso == 'G') {
//			monto = ((CotizacionGRVO)cotizacion).getPrevisionSIS();
//		}else if (tipoProceso == 'A') {
//			monto = ((CotizacionRAVO)cotizacion).getPrevisionSIS();
//		}		
//		return monto;
//	}

	public void asignaAFP(char tipoProceso, int tipoPrevision, CotizacionVO cot, int idTipoSeccion) throws SeccionException
	{
		try
		{
			long total = 0, subTotalAFC = 0;
			this.cuenta = false;

			if (tipoProceso == 'R' && idTipoSeccion != Constants.SECCION_DEPOSITOCONVENIDO)
			{
				CotizacionREVO cotizacion = (CotizacionREVO) cot;
				
				//Asepulveda 05-07-2010
				setValores(idTipoSeccion, "previsionSIS", cotizacion.getPrevisionSIS());
				
				if (cotizacion.isVoluntario())
				{
					if (cotizacion.getPrevisionObligatorio() > 0 || cotizacion.getPrevisionAhorro() > 0)
					{
						if (this.logear)
							this.log.info("adding voluntario:" + cotizacion.getIdCotizante() + ":obl:" + cotizacion.getPrevisionObligatorio() + ":ahorro:" + cotizacion.getPrevisionAhorro() + "::");
						total = setValores(idTipoSeccion, "previsionAdicional", cotizacion.getPrevisionObligatorio() + cotizacion.getPrevisionAhorro());
						setValores(idTipoSeccion, "remImpPension", cotizacion.getRentaImp());
						setValores(idTipoSeccion, "previsionTotal", total);
					}
					return;
				}
				if (tipoPrevision == Seccion.IS_AFP && (cotizacion.getPrevisionObligatorio() > 0 || cotizacion.getPrevisionAhorro() > 0))
				{
					setValores(idTipoSeccion, "remImpPension", cotizacion.getRentaImp());
					total = setValores(idTipoSeccion, "previsionObligatorio", cotizacion.getPrevisionObligatorio()) + setValores(idTipoSeccion, "previsionAhorro", cotizacion.getPrevisionAhorro());
					setValores(idTipoSeccion, "subTotalPension", total);
				}

				if (cotizacion.getSegCesEmpl() > 0)
				{
					setValores(idTipoSeccion, "segCesRemImp", cotizacion.getSegCesRemImp());
					subTotalAFC = setValores(idTipoSeccion, "segCesTrab", cotizacion.getSegCesTrab()) + setValores(idTipoSeccion, "segCesEmpl", cotizacion.getSegCesEmpl());
					setValores(idTipoSeccion, "subTotalAFC", subTotalAFC);

					if (subTotalAFC > 0 && tipoPrevision == Seccion.IS_INP) // es INP, osea agregar asteriscos
						this.soloAFC++;
				}

				if (tipoPrevision == Seccion.IS_AFP && cotizacion.getTrabPesado() > 0)
					total += setValores(idTipoSeccion, "trabPesado", cotizacion.getTrabPesado());
				
				//asepulveda 06-07-2010
				//setValores(idTipoSeccion, "previsionTotal", total + subTotalAFC);
				setValores(idTipoSeccion, "previsionTotal", total + subTotalAFC + cotizacion.getPrevisionSIS());
			} else if (tipoProceso == 'G')
			{
				CotizacionGRVO cotizacion = (CotizacionGRVO) cot;
				
				//Asepulveda 05-07-2010
				setValores(idTipoSeccion, "previsionSIS", cotizacion.getPrevisionSIS());				
				
				if (tipoPrevision == Seccion.IS_AFP && cotizacion.getPrevisionObligatorio() > 0)
					total += setValores(idTipoSeccion, "previsionObligatorio", cotizacion.getPrevisionObligatorio());

				if (cotizacion.getSegCesEmpl() > 0)
				{
					subTotalAFC = setValores(idTipoSeccion, "segCesTrab", cotizacion.getSegCesTrab()) + setValores(idTipoSeccion, "segCesEmpl", cotizacion.getSegCesEmpl());
					setValores(idTipoSeccion, "subTotalAFC", subTotalAFC);
				}
				if (tipoPrevision == Seccion.IS_AFP && cotizacion.getTrabPesado() > 0)
					total += setValores(idTipoSeccion, "trabPesado", cotizacion.getTrabPesado());
				
				
				//asepulveda 06-07-2010
				//setValores(idTipoSeccion, "previsionTotal", total + subTotalAFC);
				setValores(idTipoSeccion, "previsionTotal", total + subTotalAFC + cotizacion.getPrevisionSIS());
			} else if (tipoProceso == 'A')
			{
				CotizacionRAVO cotizacion = (CotizacionRAVO) cot;
				
				//Asepulveda 05-07-2010
				setValores(idTipoSeccion, "previsionSIS", cotizacion.getPrevisionSIS());
				
				logger.debug("addAFP:" + cotizacion.getPrevisionObligatorio() + "::");
				if (tipoPrevision == Seccion.IS_AFP && cotizacion.getPrevisionObligatorio() > 0)
					total += setValores(idTipoSeccion, "previsionObligatorio", cotizacion.getPrevisionObligatorio());

				if (cotizacion.getSegCesEmpl() > 0)
				{
					subTotalAFC = setValores(idTipoSeccion, "segCesTrab", cotizacion.getSegCesTrab()) + setValores(idTipoSeccion, "segCesEmpl", cotizacion.getSegCesEmpl());
					setValores(idTipoSeccion, "subTotalAFC", subTotalAFC);
				}
				if (tipoPrevision == Seccion.IS_AFP && cotizacion.getTrabPesado() > 0)
					total += setValores(idTipoSeccion, "trabPesado", cotizacion.getTrabPesado());
				
				//asepulveda 06-07-2010
				//setValores(idTipoSeccion, "previsionTotal", total + subTotalAFC);
				setValores(idTipoSeccion, "previsionTotal", total + subTotalAFC + cotizacion.getPrevisionSIS());

			} else if (tipoProceso == 'D')
			{
				CotizacionDCVO cotizacion = (CotizacionDCVO) cot;
				if (cotizacion.getDepositoConvenido() > 0 || cotizacion.getIndemAporte() > 0)
				{
					setValores(idTipoSeccion, "depositoConvenido", cotizacion.getDepositoConvenido());
					setValores(idTipoSeccion, "indemAporte", cotizacion.getIndemAporte());
				}
			} else if (tipoProceso == 'R' && idTipoSeccion == Constants.SECCION_DEPOSITOCONVENIDO)
			{
				CotizacionREVO cotizacion = (CotizacionREVO) cot;
				if (cotizacion.getDepositoConvenido() > 0 || cotizacion.getIndemAporte() > 0)
				{
					setValores(idTipoSeccion, "depositoConvenido", cotizacion.getDepositoConvenido());
					setValores(idTipoSeccion, "indemAporte", cotizacion.getIndemAporte());
				}
			}			
		} catch (Exception e)
		{
			this.log.error("problemas asignaAFP", e);
			throw new SeccionException("Problemas en SeccionAFP:asignaAFP", e);
		}
	}

	private void restaAFP(char tipoProceso, int tipoPrevision, CotizacionVO cot, int idTipoSeccion) throws SeccionException
	{
		try
		{
			long total = 0, subTotalAFC = 0;
	
			if (tipoProceso == 'R' && idTipoSeccion != Constants.SECCION_DEPOSITOCONVENIDO)
			{	
				CotizacionREVO cotizacion = (CotizacionREVO) cot;

				//clillo 25-10-2010
				restaValores(idTipoSeccion, "previsionSIS", cotizacion.getPrevisionSIS());
				
				if (cotizacion.isVoluntario())
				{
					if (cotizacion.getPrevisionObligatorio() > 0 || cotizacion.getPrevisionAhorro() > 0)
					{
						if (this.logear)
							this.log.info("resta voluntario:" + cotizacion.getIdCotizante() + ":obl:" + cotizacion.getPrevisionObligatorio() + ":ahorro:" + cotizacion.getPrevisionAhorro() + "::");
						total = restaValores(idTipoSeccion, "previsionAdicional", cotizacion.getPrevisionObligatorio() + cotizacion.getPrevisionAhorro());
						restaValores(idTipoSeccion, "previsionTotal", total);
						restaValores(idTipoSeccion, "remImpPension", cotizacion.getRentaImp());
					}
					return;
				}
				if (tipoPrevision == Seccion.IS_AFP && (cotizacion.getPrevisionObligatorio() > 0 || cotizacion.getPrevisionAhorro() > 0))
				{
					restaValores(idTipoSeccion, "remImpPension", cotizacion.getRentaImp());
					total = restaValores(idTipoSeccion, "previsionObligatorio", cotizacion.getPrevisionObligatorio()) + restaValores(idTipoSeccion, "previsionAhorro", cotizacion.getPrevisionAhorro());
					restaValores(idTipoSeccion, "subTotalPension", total);
				}

				if (cotizacion.getSegCesEmpl() > 0)
				{
					restaValores(idTipoSeccion, "segCesRemImp", cotizacion.getSegCesRemImp());
					subTotalAFC = restaValores(idTipoSeccion, "segCesTrab", cotizacion.getSegCesTrab()) + restaValores(idTipoSeccion, "segCesEmpl", cotizacion.getSegCesEmpl());
					restaValores(idTipoSeccion, "subTotalAFC", subTotalAFC);

					if (subTotalAFC > 0 && tipoPrevision == Seccion.IS_INP) // es INP, osea agregar asteriscos
						this.soloAFC++;
				}

				if (tipoPrevision == Seccion.IS_AFP && cotizacion.getTrabPesado() > 0)
					total += restaValores(idTipoSeccion, "trabPesado", cotizacion.getTrabPesado());
				
				//asepulveda 07-07-2010
				//restaValores(idTipoSeccion, "previsionTotal", total + subTotalAFC);
				restaValores(idTipoSeccion, "previsionTotal", total + subTotalAFC + cotizacion.getPrevisionSIS());
			} else if (tipoProceso == 'G')
			{
				CotizacionGRVO cotizacion = (CotizacionGRVO) cot;

				//csanchez 27/12/2012
				restaValores(idTipoSeccion, "previsionSIS", cotizacion.getPrevisionSIS());

				if (tipoPrevision == Seccion.IS_AFP && cotizacion.getPrevisionObligatorio() > 0)
					total += restaValores(idTipoSeccion, "previsionObligatorio", cotizacion.getPrevisionObligatorio());

				if (cotizacion.getSegCesEmpl() > 0)
				{
					subTotalAFC = restaValores(idTipoSeccion, "segCesTrab", cotizacion.getSegCesTrab()) + restaValores(idTipoSeccion, "segCesEmpl", cotizacion.getSegCesEmpl());
					restaValores(idTipoSeccion, "subTotalAFC", subTotalAFC);
				}
				if (tipoPrevision == Seccion.IS_AFP && cotizacion.getTrabPesado() > 0)
					total += restaValores(idTipoSeccion, "trabPesado", cotizacion.getTrabPesado());
				
				//asepulveda 07-07-2010
				//restaValores(idTipoSeccion, "previsionTotal", total + subTotalAFC);
				restaValores(idTipoSeccion, "previsionTotal", total + subTotalAFC + cotizacion.getPrevisionSIS());
			} else if (tipoProceso == 'A')
			{
				CotizacionRAVO cotizacion = (CotizacionRAVO) cot;
				
				//csanchez 27/12/2012
				restaValores(idTipoSeccion, "previsionSIS", cotizacion.getPrevisionSIS());

				logger.info("restando AFP para A:tipoPrevision:" + tipoPrevision + ":PrevisionOblig:" + cotizacion.getPrevisionObligatorio() + ":segCesEmpl:" + cotizacion.getSegCesEmpl() + "::");
				if (tipoPrevision == Seccion.IS_AFP && cotizacion.getPrevisionObligatorio() > 0)
					total += restaValores(idTipoSeccion, "previsionObligatorio", cotizacion.getPrevisionObligatorio());

				if (cotizacion.getSegCesEmpl() > 0)
				{
					subTotalAFC = restaValores(idTipoSeccion, "segCesTrab", cotizacion.getSegCesTrab()) + restaValores(idTipoSeccion, "segCesEmpl", cotizacion.getSegCesEmpl());
					restaValores(idTipoSeccion, "subTotalAFC", subTotalAFC);
				}
				if (tipoPrevision == Seccion.IS_AFP && cotizacion.getTrabPesado() > 0)
					total += restaValores(idTipoSeccion, "trabPesado", cotizacion.getTrabPesado());
				
				//asepulveda 07-07-2010
				//restaValores(idTipoSeccion, "previsionTotal", total + subTotalAFC);
				restaValores(idTipoSeccion, "previsionTotal", total + subTotalAFC + cotizacion.getPrevisionSIS());
			} else if (tipoProceso == 'D')
			{
				CotizacionDCVO cotizacion = (CotizacionDCVO) cot;
				if (cotizacion.getDepositoConvenido() > 0 || cotizacion.getIndemAporte() > 0)
					restaValores(idTipoSeccion, "depositoConvenido", cotizacion.getDepositoConvenido());
					restaValores(idTipoSeccion, "indemAporte", cotizacion.getIndemAporte());
			} else if (tipoProceso == 'R' && idTipoSeccion == Constants.SECCION_DEPOSITOCONVENIDO)
			{
				CotizacionREVO cotizacion = (CotizacionREVO) cot;
				if (cotizacion.getDepositoConvenido() > 0 || cotizacion.getIndemAporte() > 0) {
					restaValores(idTipoSeccion, "depositoConvenido", cotizacion.getDepositoConvenido());
					restaValores(idTipoSeccion, "indemAporte", cotizacion.getIndemAporte());
				}
			}			
		} catch (Exception e)
		{
			this.log.error("problemas restaAFP", e);
			throw new SeccionException("Problemas en SeccionAFP:restaAFP", e);
		}
	}
}
