package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
import java.util.List;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;

/*
 * @(#) Cotizacion.java 1.9 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.9
 */
public class Cotizacion implements Serializable
{
	private static final long serialVersionUID = -4025813800944709808L;
	private long totalINP;
	// campos comunes RE - GR - RA
	private String saludObligISAPRE;
	private String saludObligFONASA;
	private String prevObligatorioAFP;
	private String prevObligatorioINP;
	private String segCesRemImp;
	private String segCesTrab;
	private String segCesEmpl;

	private String tasaTrabPesado;
	private String tipoTrabPesado = "";
	private String trabPesado;
	private String accTrabajoMutual;
	private String accTrabajoINP;// acc trabajo si empresa no tiene mutual
	private String mutualImp;
	private String aporteCaja;
	// RE
	private String rentaImp;
	private String asigFamiliarINP;// asignacion si convenio no tiene caja
	private String asigFamRetroINP;
	private String asigFamReintINP;
	private String asigFamiliar;
	private String asigFamRetro;
	private String asigFamReint;
	private String totalAsigFam;
	private String saludPactado;
	private String saludAdicional;
	private String saludTotal;
	private String previsionAdicional;
	private String previsionAhorro;
	private String previsionTotal;
	private String inpBonificacion;
	private String inpDesahucio;
	private String ccafCredito;
	private String ccafDental;
	private String ccafLeasing;
	private String ccafSeguro;

	private String remImpPension; // remuneraciones con topes para INP

	List movimientoPersonal;
	List apv;

	// reforma
	private String apvcAporteEmpl;
	private String apvcAporteTrab;
	private String apvcNumContr;
	private String afpvRutDpndiente;
	private String afpvNombreDpndiente;
	private String afpvAplldioPatDpndiente;
	private String afpvAplldioMatDpndiente;
	
//	clillo 12-01-15 monto AFBR
	private String otrosAFBR;
	
	// GR
	private String gratificacion;
	private String inicio;
	private String termino;

	// RA
	private String reliquidacion;
//	clillo 3-12-14 por Reliquidación
	private int periodo;
	
	// DC
	private String depositoConvenido;
	private String tipoRegimenPrev;
	private String tasaPactada;
	private String indemAporte;
	private String indemInicio;
	private String indemTermino;
	private String numPeriodos;
	private String idEntDep;
	
	//TODO jlandero : se agregan campos faltantes 
	private String rentaImponibleSIS;
	private String previsionSIS;

	public Cotizacion()
	{
		super();
		this.indemInicio = null;
		this.indemTermino = null;
		this.inicio = null;
		this.termino = null;
	}

	/**
	 * carga conceptos
	 * 
	 * @param tipoProceso
	 * @param setter
	 */
	public void cargaConceptos(char tipoProceso, Utils setter)
	{
		if (tipoProceso == 'R')
		{
		//TODO 07/06/2012 GMALLEA Se pregunta por todas las remuneraciones ya que solo se obtenia la remuneracion imponible sola
				if(!setter.getMapeoValores().get("Remuneracion Imponible").equals("0") && !setter.getMapeoValores().get("Remuneracion Imponible").equals(""))
					this.rentaImp = (String)setter.getMapeoValores().get("Remuneracion Imponible");
				
				else if(!setter.getMapeoValores().get("Rem. Imp. INP").equals("0") && !setter.getMapeoValores().get("Rem. Imp. INP").equals(""))
					this.rentaImp = (String)setter.getMapeoValores().get("Rem. Imp. INP");
				
				else if(!setter.getMapeoValores().get("Remuneracion Imponible Mutual").equals("0") && !setter.getMapeoValores().get("Remuneracion Imponible Mutual").equals(""))
					this.rentaImp = (String)setter.getMapeoValores().get("Remuneracion Imponible Mutual");
				
				else if(!setter.getMapeoValores().get("Rem. Imponible Seg. Cesantia").equals("0") && !setter.getMapeoValores().get("Rem. Imponible Seg. Cesantia").equals(""))
					this.rentaImp = (String)setter.getMapeoValores().get("Rem. Imponible Seg. Cesantia");
				
				else if(!setter.getMapeoValores().get("Rem. Imp. Isapre").equals("0") && !setter.getMapeoValores().get("Rem. Imp. Isapre").equals(""))
					this.rentaImp = (String)setter.getMapeoValores().get("Rem. Imp. Isapre");
				
				else if(!setter.getMapeoValores().get("Rem. Imp. Caja").equals("0") && !setter.getMapeoValores().get("Rem. Imp. Caja").equals(""))
					this.rentaImp = (String)setter.getMapeoValores().get("Rem. Imp. Caja");
				
				
					
			this.remImpPension = this.rentaImp;
			this.segCesRemImp = setter.setIntStr("segCesRemImp");
			this.segCesTrab = setter.setIntStr("segCesTrab");
			this.segCesEmpl = setter.setIntStr("segCesEmpl");
			this.tasaTrabPesado = setter.setFloatStr("tasaTrabPesado");
			this.tipoTrabPesado = setter.setString("tipoTrabPesado");
			this.trabPesado = setter.setIntStr("trabPesado");
			this.mutualImp = setter.setIntStr("mutualImp");
			this.aporteCaja = setter.setIntStr("ccafAporte");
			this.inpBonificacion = setter.setIntStr("inpBonificacion");
			this.inpDesahucio = setter.setIntStr("inpDesahucio");
			this.ccafCredito = setter.setIntStr("ccafCredito");
			this.ccafDental = setter.setIntStr("ccafDental");
			this.ccafLeasing = setter.setIntStr("ccafLeasing");
			this.ccafSeguro = setter.setIntStr("ccafSeguro");
			this.accTrabajoMutual = setter.setIntStr("inpMutual");
			this.rentaImponibleSIS = setter.setIntStr("rentaImponibleSIS");
			this.previsionSIS = setter.setIntStr("previsionSIS");
//			clillo 12-01-15 monto AFBR
			this.otrosAFBR = setter.setIntStr("otrosAFBR");

		} else if (tipoProceso == 'G')
		{
			this.gratificacion = setter.setIntStr("gratificacion");
			this.remImpPension = this.gratificacion;
			this.segCesRemImp = setter.setIntStr("segCesRemImp");
			this.segCesTrab = setter.setIntStr("segCesTrab");
			this.segCesEmpl = setter.setIntStr("segCesEmpl");
			this.tasaTrabPesado = setter.setFloatStr("tasaTrabPesado");
			this.tipoTrabPesado = setter.setString("tipoTrabPesado");
			this.trabPesado = setter.setIntStr("trabPesado");
			this.mutualImp = setter.setIntStr("mutualImp");
			this.accTrabajoMutual = setter.setIntStr("inpMutual");
			this.aporteCaja = setter.setIntStr("ccafAporte");
			this.rentaImponibleSIS = setter.setIntStr("rentaImponibleSIS");
			this.previsionSIS = setter.setIntStr("previsionSIS");
		} else if (tipoProceso == 'A')
		{
			this.reliquidacion = setter.setIntStr("reliquidacion");
			this.remImpPension = this.reliquidacion;
			this.segCesRemImp = setter.setIntStr("segCesRemImp");
			this.segCesTrab = setter.setIntStr("segCesTrab");
			this.segCesEmpl = setter.setIntStr("segCesEmpl");
			this.tasaTrabPesado = setter.setFloatStr("tasaTrabPesado");
			this.tipoTrabPesado = setter.setString("tipoTrabPesado");
			this.trabPesado = setter.setIntStr("trabPesado");
			this.mutualImp = setter.setIntStr("mutualImp");
			this.accTrabajoMutual = setter.setIntStr("inpMutual");
			this.aporteCaja = setter.setIntStr("ccafAporte");
			this.rentaImponibleSIS = setter.setIntStr("rentaImponibleSIS");
			this.previsionSIS = setter.setIntStr("previsionSIS");
			String fechareli= setter.setString("inicioReli");
			if(fechareli != null && fechareli.length()==10){
				this.periodo= Integer.parseInt(fechareli.substring(6, 10) + fechareli.substring(3, 5));
			}
//			clillo 27-01-15 monto AFBR
			this.otrosAFBR = setter.setIntStr("otrosAFBR");

		} else if (tipoProceso == 'D')
		{
			this.rentaImp = setter.setIntStr("rentaImp");
			this.depositoConvenido = setter.setIntStr("depositoConvenido");
			this.tipoRegimenPrev = setter.setString("tipoRegimenPrev");
			this.tasaPactada = setter.setFloatStr("tasaPactada");
			this.indemAporte = setter.setIntStr("indemAporte");
			this.indemInicio = setter.setString("indemInicio");
			this.indemTermino = setter.setString("indemTermino");
			this.numPeriodos = setter.setIntStr("numPeriodos");
			this.idEntDep = setter.setIntStr("idEntDep");
			this.rentaImponibleSIS = setter.setIntStr("rentaImponibleSIS");
			this.previsionSIS = setter.setIntStr("previsionSIS");
		}
	}

	public void sumaMontos(CotizacionREVO c, boolean isFONASA, boolean isINP, boolean isMUTUAL, boolean isCCAF)
	{
		int sumaTmp = 0;
		try
		{
			this.rentaImp = "" + (new Integer(this.rentaImp).intValue() + c.getRentaImp());
		} catch (Exception e)
		{
		}
		try
		{
			if (isCCAF)
				this.asigFamiliar = "" + (new Integer(this.asigFamiliar).intValue() + c.getAsigFamiliar());
			else
			{
				sumaTmp = new Integer(this.asigFamiliarINP).intValue() + c.getAsigFamiliar();
				this.asigFamiliarINP = "" + (sumaTmp);
				this.totalINP += sumaTmp;
			}
		} catch (Exception e)
		{
		}
		try
		{
			if (isCCAF)
				this.asigFamRetro = "" + (new Integer(this.asigFamRetro).intValue() + c.getAsigFamRetro());
			else
			{
				sumaTmp = new Integer(this.asigFamRetroINP).intValue() + c.getAsigFamRetro();
				this.asigFamRetroINP = "" + (sumaTmp);
				this.totalINP += sumaTmp;
			}
		} catch (Exception e)
		{
		}
		try
		{
			if (isCCAF)
				this.asigFamReint = "" + (new Integer(this.asigFamReint).intValue() + c.getAsigFamReint());
			else
			{
				sumaTmp = new Integer(this.asigFamReintINP).intValue() + c.getAsigFamRetro();
				this.asigFamRetro = "" + sumaTmp;
				this.totalINP += sumaTmp;
			}
		} catch (Exception e)
		{
		}
		try
		{
			if (isFONASA)
			{
				sumaTmp = new Integer(this.saludObligFONASA).intValue() + c.getSaludObligatorio();
				this.saludObligFONASA = "" + sumaTmp;
				this.totalINP += sumaTmp;
			} else
				this.saludObligISAPRE = "" + (new Integer(this.saludObligISAPRE).intValue() + c.getSaludObligatorio());
		} catch (Exception e)
		{
		}
		try
		{
			if (!isFONASA)
				this.saludPactado = "" + (new Integer(this.saludPactado).intValue() + c.getSaludPactado());
		} catch (Exception e)
		{
		}
		try
		{
			if (!isFONASA)
				this.saludAdicional = "" + (new Integer(this.saludAdicional).intValue() + c.getSaludAdicional());
		} catch (Exception e)
		{
		}
		try
		{
			if (!isFONASA)
				this.saludTotal = "" + (new Integer(this.saludTotal).intValue() + c.getSaludTotal());
		} catch (Exception e)
		{
		}
		try
		{
			if (isINP)
			{
				sumaTmp = new Integer(this.prevObligatorioINP).intValue() + c.getPrevisionObligatorio();
				this.prevObligatorioINP = "" + sumaTmp;
				this.totalINP += sumaTmp;
			} else
				this.prevObligatorioAFP = "" + (new Integer(this.prevObligatorioAFP).intValue() + c.getPrevisionObligatorio());
		} catch (Exception e)
		{
		}
		try
		{
			if (!isINP)
				this.previsionTotal = "" + (new Integer(this.previsionTotal).intValue() + c.getPrevisionTotal());
		} catch (Exception e)
		{
		}
		try
		{
			this.segCesTrab = "" + (new Integer(this.segCesTrab).intValue() + c.getSegCesTrab());
		} catch (Exception e)
		{
		}
		try
		{
			this.segCesEmpl = "" + (new Integer(this.segCesEmpl).intValue() + c.getSegCesEmpl());
		} catch (Exception e)
		{
		}
		try
		{
			if (!isINP)
				this.trabPesado = "" + (new Integer(this.trabPesado).intValue() + c.getTrabPesado());
		} catch (Exception e)
		{
		}
		try
		{
			if (!isMUTUAL)
				this.mutualImp = "" + (new Integer(this.mutualImp).intValue() + c.getMutualImp());
		} catch (Exception e)
		{
		}
		try
		{
			if (!isINP)
				this.aporteCaja = "" + (new Integer(this.aporteCaja).intValue() + c.getCcafAporte());
		} catch (Exception e)
		{
		}
		try
		{
			if (!isINP)
				this.segCesRemImp = "" + (new Integer(this.segCesRemImp).intValue() + c.getSegCesRemImp());
		} catch (Exception e)
		{
		}
	}

	/**
	 * asignacion familiar
	 * 
	 * @return
	 */
	public String getAsigFamiliar()
	{
		return this.asigFamiliar;
	}

	/**
	 * asignacion familiar
	 * 
	 * @param asigFamiliar
	 */
	public void setAsigFamiliar(String asigFamiliar)
	{
		this.asigFamiliar = asigFamiliar;
	}

	/**
	 * asignacion familiar retro
	 * 
	 * @return
	 */
	public String getAsigFamRetro()
	{
		return this.asigFamRetro;
	}

	/**
	 * asignacion familiar reto
	 * 
	 * @param asigFamRetro
	 */
	public void setAsigFamRetro(String asigFamRetro)
	{
		this.asigFamRetro = asigFamRetro;
	}

	/**
	 * ccaf credito
	 * 
	 * @return
	 */
	public String getCcafCredito()
	{
		return this.ccafCredito;
	}

	/**
	 * ccaf credito
	 * 
	 * @param ccafCredito
	 */
	public void setCcafCredito(String ccafCredito)
	{
		this.ccafCredito = ccafCredito;
	}

	/**
	 * ccaf dental
	 * 
	 * @return
	 */
	public String getCcafDental()
	{
		return this.ccafDental;
	}

	/**
	 * ccaf dental
	 * 
	 * @param ccafDental
	 */
	public void setCcafDental(String ccafDental)
	{
		this.ccafDental = ccafDental;
	}

	/**
	 * ccaf leasing
	 * 
	 * @return
	 */
	public String getCcafLeasing()
	{
		return this.ccafLeasing;
	}

	/**
	 * ccaf leasing
	 * 
	 * @param ccafLeasing
	 */
	public void setCcafLeasing(String ccafLeasing)
	{
		this.ccafLeasing = ccafLeasing;
	}

	/**
	 * ccaf seguro
	 * 
	 * @return
	 */
	public String getCcafSeguro()
	{
		return this.ccafSeguro;
	}

	/**
	 * ccaf seguro
	 * 
	 * @param ccafSeguro
	 */
	public void setCcafSeguro(String ccafSeguro)
	{
		this.ccafSeguro = ccafSeguro;
	}

	/**
	 * inp bonificacion
	 * 
	 * @return
	 */
	public String getInpBonificacion()
	{
		return this.inpBonificacion;
	}

	/**
	 * inp bonificacion
	 * 
	 * @param inpBonificacion
	 */
	public void setInpBonificacion(String inpBonificacion)
	{
		this.inpBonificacion = inpBonificacion;
	}

	/**
	 * inp desahucio
	 * 
	 * @return
	 */
	public String getInpDesahucio()
	{
		return this.inpDesahucio;
	}

	/**
	 * inp bonificacion desahucio
	 * 
	 * @param inpDesahucio
	 */
	public void setInpDesahucio(String inpDesahucio)
	{
		this.inpDesahucio = inpDesahucio;
	}

	/**
	 * movimiento personal
	 * 
	 * @return
	 */
	public List getMovimientoPersonal()
	{
		return this.movimientoPersonal;
	}

	/**
	 * movimiento personal
	 * 
	 * @param movimientoPersonal
	 */
	public void setMovimientoPersonal(List movimientoPersonal)
	{
		this.movimientoPersonal = movimientoPersonal;
	}

	/**
	 * mutual imp
	 * 
	 * @return
	 */
	public String getMutualImp()
	{
		return this.mutualImp;
	}

	/**
	 * mutual imp
	 * 
	 * @param mutualImp
	 */
	public void setMutualImp(String mutualImp)
	{
		this.mutualImp = mutualImp;
	}

	/**
	 * prevision adicional
	 * 
	 * @return
	 */
	public String getPrevisionAdicional()
	{
		return this.previsionAdicional;
	}

	/**
	 * prevision adicional
	 * 
	 * @param previsionAdicional
	 */
	public void setPrevisionAdicional(String previsionAdicional)
	{
		this.previsionAdicional = previsionAdicional;
	}

	/**
	 * prevision adicional
	 * 
	 * @return
	 */
	public String getPrevisionAhorro()
	{
		return this.previsionAhorro;
	}

	/**
	 * prevision ahorro
	 * 
	 * @param previsionAhorro
	 */
	public void setPrevisionAhorro(String previsionAhorro)
	{
		this.previsionAhorro = previsionAhorro;
	}

	/**
	 * prevision obligatorio afp
	 * 
	 * @return
	 */
	public String getPrevObligatorioAFP()
	{
		return this.prevObligatorioAFP;
	}

	/**
	 * prevision obligatorio afp
	 * 
	 * @param prevObligatorioAFP
	 */
	public void setPrevObligatorioAFP(String prevObligatorioAFP)
	{
		this.prevObligatorioAFP = prevObligatorioAFP;
	}

	/**
	 * prevision obligatorio inp
	 * 
	 * @return
	 */
	public String getPrevObligatorioINP()
	{
		return this.prevObligatorioINP;
	}

	/**
	 * prevision obligatorio inp
	 * 
	 * @param prevObligatorioINP
	 */
	public void setPrevObligatorioINP(String prevObligatorioINP)
	{
		this.prevObligatorioINP = prevObligatorioINP;
	}

	/**
	 * prevision total
	 * 
	 * @return
	 */
	public String getPrevisionTotal()
	{
		return this.previsionTotal;
	}

	/**
	 * prevision total
	 * 
	 * @param previsionTotal
	 */
	public void setPrevisionTotal(String previsionTotal)
	{
		this.previsionTotal = previsionTotal;
	}

	/**
	 * remuneracion imp pension
	 * 
	 * @return
	 */
	public String getRemImpPension()
	{
		return this.remImpPension;
	}

	/**
	 * remuneracion imp pension
	 * 
	 * @param remImpPension
	 */
	public void setRemImpPension(String remImpPension)
	{
		this.remImpPension = remImpPension;
	}

	/**
	 * renta imponible
	 * 
	 * @return
	 */
	public String getRentaImp()
	{
		return this.rentaImp;
	}

	/**
	 * renta imponible
	 * 
	 * @param rentaImp
	 */
	public void setRentaImp(String rentaImp)
	{
		this.rentaImp = rentaImp;
	}

	/**
	 * salud adicional
	 * 
	 * @return
	 */
	public String getSaludAdicional()
	{
		return this.saludAdicional;
	}

	/**
	 * salud adicional
	 * 
	 * @param saludAdicional
	 */
	public void setSaludAdicional(String saludAdicional)
	{
		this.saludAdicional = saludAdicional;
	}

	/**
	 * salud obligatorio fonasa
	 * 
	 * @return
	 */
	public String getSaludObligFONASA()
	{
		return this.saludObligFONASA;
	}

	/**
	 * salud obligatorio fonasa
	 * 
	 * @param saludObligFONASA
	 */
	public void setSaludObligFONASA(String saludObligFONASA)
	{
		this.saludObligFONASA = saludObligFONASA;
	}

	/**
	 * salud obligatorio isapre
	 * 
	 * @return
	 */
	public String getSaludObligISAPRE()
	{
		return this.saludObligISAPRE;
	}

	/**
	 * salud obligatorio isapre
	 * 
	 * @param saludObligISAPRE
	 */
	public void setSaludObligISAPRE(String saludObligISAPRE)
	{
		this.saludObligISAPRE = saludObligISAPRE;
	}

	/**
	 * salud pactado
	 * 
	 * @return
	 */
	public String getSaludPactado()
	{
		return this.saludPactado;
	}

	/**
	 * salud pactado
	 * 
	 * @param saludPactado
	 */
	public void setSaludPactado(String saludPactado)
	{
		this.saludPactado = saludPactado;
	}

	/**
	 * salud total
	 * 
	 * @return
	 */
	public String getSaludTotal()
	{
		return this.saludTotal;
	}

	/**
	 * salud total
	 * 
	 * @param saludTotal
	 */
	public void setSaludTotal(String saludTotal)
	{
		this.saludTotal = saludTotal;
	}

	/**
	 * seguro cesantia empl
	 * 
	 * @return
	 */
	public String getSegCesEmpl()
	{
		return this.segCesEmpl;
	}

	/**
	 * seguro cesantia empl
	 * 
	 * @param segCesEmpl
	 */
	public void setSegCesEmpl(String segCesEmpl)
	{
		this.segCesEmpl = segCesEmpl;
	}

	/**
	 * seguro cesantia remuneracion imp
	 * 
	 * @return
	 */
	public String getSegCesRemImp()
	{
		return this.segCesRemImp;
	}

	/**
	 * seguro cesantia remuneracion imp
	 * 
	 * @param segCesRemImp
	 */
	public void setSegCesRemImp(String segCesRemImp)
	{
		this.segCesRemImp = segCesRemImp;
	}

	/**
	 * segruro cesantia trabajador
	 * 
	 * @return
	 */
	public String getSegCesTrab()
	{
		return this.segCesTrab;
	}

	/**
	 * seguro cesantia trabajador
	 * 
	 * @param segCesTrab
	 */
	public void setSegCesTrab(String segCesTrab)
	{
		this.segCesTrab = segCesTrab;
	}

	/**
	 * tasa trabajo pesado
	 * 
	 * @return
	 */
	public String getTasaTrabPesado()
	{
		return this.tasaTrabPesado;
	}

	/**
	 * tasa trabajo pesado
	 * 
	 * @param tasaTrabPesado
	 */
	public void setTasaTrabPesado(String tasaTrabPesado)
	{
		this.tasaTrabPesado = tasaTrabPesado;
	}

	/**
	 * tipo trabajo pesado
	 * 
	 * @return
	 */
	public String getTipoTrabPesado()
	{
		return this.tipoTrabPesado;
	}

	/**
	 * tipo trabajo pesado
	 * 
	 * @param tipoTrabPesado
	 */
	public void setTipoTrabPesado(String tipoTrabPesado)
	{
		this.tipoTrabPesado = tipoTrabPesado;
	}

	/**
	 * trabajo pesado
	 * 
	 * @return
	 */
	public String getTrabPesado()
	{
		return this.trabPesado;
	}

	/**
	 * trabajo pesado
	 * 
	 * @param trabPesado
	 */
	public void setTrabPesado(String trabPesado)
	{
		this.trabPesado = trabPesado;
	}

	/**
	 * asignacion familiar reint
	 * 
	 * @return
	 */
	public String getAsigFamReint()
	{
		return this.asigFamReint;
	}

	/**
	 * asignacion familiar reint
	 * 
	 * @param asigFamReint
	 */
	public void setAsigFamReint(String asigFamReint)
	{
		this.asigFamReint = asigFamReint;
	}

	/**
	 * inicio
	 * 
	 * @return
	 */
	public String getInicio()
	{
		return this.inicio;
	}

	/**
	 * inicio
	 * 
	 * @param inicio
	 */
	public void setInicio(String inicio)
	{
		this.inicio = inicio;
	}

	/**
	 * termino
	 * 
	 * @return
	 */
	public String getTermino()
	{
		return this.termino;
	}

	/**
	 * termino
	 * 
	 * @param termino
	 */
	public void setTermino(String termino)
	{
		this.termino = termino;
	}

	/**
	 * indem inicio
	 * 
	 * @return
	 */
	public String getIndemInicio()
	{
		return this.indemInicio;
	}

	/**
	 * indem inicio
	 * 
	 * @param indemInicio
	 */
	public void setIndemInicio(String indemInicio)
	{
		this.indemInicio = indemInicio;
	}

	/**
	 * indem termino
	 * 
	 * @return
	 */
	public String getIndemTermino()
	{
		return this.indemTermino;
	}

	/**
	 * indem termino
	 * 
	 * @param indemTermino
	 */
	public void setIndemTermino(String indemTermino)
	{
		this.indemTermino = indemTermino;
	}

	/**
	 * deposito convenio
	 * 
	 * @return
	 */
	public String getDepositoConvenido()
	{
		return this.depositoConvenido;
	}

	/**
	 * deposito convenio
	 * 
	 * @param depositoConvenido
	 */
	public void setDepositoConvenido(String depositoConvenido)
	{
		this.depositoConvenido = depositoConvenido;
	}

	/**
	 * gratificacion
	 * 
	 * @return
	 */
	public String getGratificacion()
	{
		return this.gratificacion;
	}

	/**
	 * gratificacion
	 * 
	 * @param gratificacion
	 */
	public void setGratificacion(String gratificacion)
	{
		this.gratificacion = gratificacion;
	}

	/**
	 * indem aporte
	 * 
	 * @return
	 */
	public String getIndemAporte()
	{
		return this.indemAporte;
	}

	/**
	 * indem aporte
	 * 
	 * @param indemAporte
	 */
	public void setIndemAporte(String indemAporte)
	{
		this.indemAporte = indemAporte;
	}

	/**
	 * numero periodos
	 * 
	 * @return
	 */
	public String getNumPeriodos()
	{
		return this.numPeriodos;
	}

	/**
	 * numero periodos
	 * 
	 * @param numPeriodos
	 */
	public void setNumPeriodos(String numPeriodos)
	{
		this.numPeriodos = numPeriodos;
	}

	/**
	 * Codigo de la entidad para el deposito convenido
	 * 
	 * @return
	 */
	public String getIdEntDep()
	{
		return this.idEntDep;
	}

	/**
	 * Codigo de la entidad para el deposito convenido
	 * 
	 * @param idEntDep
	 */
	public void setIdEntDep(String idEntDep)
	{
		this.idEntDep = idEntDep;
	}

	/**
	 * reliquidacion
	 * 
	 * @return
	 */
	public String getReliquidacion()
	{
		return this.reliquidacion;
	}

	/**
	 * reliquidacion
	 * 
	 * @param reliquidacion
	 */
	public void setReliquidacion(String reliquidacion)
	{
		this.reliquidacion = reliquidacion;
	}

	/**
	 * tasa pactada
	 * 
	 * @return
	 */
	public String getTasaPactada()
	{
		return this.tasaPactada;
	}

	/**
	 * tasa pactada
	 * 
	 * @param tasaPactada
	 */
	public void setTasaPactada(String tasaPactada)
	{
		this.tasaPactada = tasaPactada;
	}

	/**
	 * tipo regimen previsional
	 * 
	 * @return
	 */
	public String getTipoRegimenPrev()
	{
		return this.tipoRegimenPrev;
	}

	/**
	 * tipo regimen previsional
	 * 
	 * @param tipoRegimenPrev
	 */
	public void setTipoRegimenPrev(String tipoRegimenPrev)
	{
		this.tipoRegimenPrev = tipoRegimenPrev;
	}

	/**
	 * asignacion familiar inp
	 * 
	 * @return
	 */
	public String getAsigFamiliarINP()
	{
		return this.asigFamiliarINP;
	}

	/**
	 * asignacion familiar inp
	 * 
	 * @param asigFamiliarINP
	 */
	public void setAsigFamiliarINP(String asigFamiliarINP)
	{
		this.asigFamiliarINP = asigFamiliarINP;
	}

	/**
	 * acc trabajo inp
	 * 
	 * @return
	 */
	public String getAccTrabajoINP()
	{
		return this.accTrabajoINP;
	}

	/**
	 * acc trabajo inp
	 * 
	 * @param accTrabajoINP
	 */
	public void setAccTrabajoINP(String accTrabajoINP)
	{
		this.accTrabajoINP = accTrabajoINP;
	}

	/**
	 * acc trabajo mutual
	 * 
	 * @return
	 */
	public String getAccTrabajoMutual()
	{
		return this.accTrabajoMutual;
	}

	/**
	 * acc trabajo mutual
	 * 
	 * @param accTrabajoMutual
	 */
	public void setAccTrabajoMutual(String accTrabajoMutual)
	{
		this.accTrabajoMutual = accTrabajoMutual;
	}

	/**
	 * apv
	 * @return
	 */
	public List getApv()
	{
		return this.apv;
	}

	/**
	 * apv
	 * @param apv
	 */
	public void setApv(List apv)
	{
		this.apv = apv;
	}

	/**
	 * aporte caja
	 * @return
	 */
	public String getAporteCaja()
	{
		return this.aporteCaja;
	}

	/**
	 * aporte caja
	 * 
	 * @param aporteCaja
	 */
	public void setAporteCaja(String aporteCaja)
	{
		this.aporteCaja = aporteCaja;
	}

	/**
	 * asignacion familiar reint inp
	 * 
	 * @return
	 */
	public String getAsigFamReintINP()
	{
		return this.asigFamReintINP;
	}

	/**
	 * asignacion familiar reint inp
	 * 
	 * @param asigFamReintINP
	 */
	public void setAsigFamReintINP(String asigFamReintINP)
	{
		this.asigFamReintINP = asigFamReintINP;
	}

	/**
	 * asignacion familar retro inp
	 * 
	 * @return
	 */
	public String getAsigFamRetroINP()
	{
		return this.asigFamRetroINP;
	}

	/**
	 * asignacion familiar retro inp
	 * 
	 * @param asigFamRetroINP
	 */
	public void setAsigFamRetroINP(String asigFamRetroINP)
	{
		this.asigFamRetroINP = asigFamRetroINP;
	}

	/**
	 * total asignacion familiar
	 * 
	 * @return
	 */
	public String getTotalAsigFam()
	{
		return this.totalAsigFam;
	}

	/**
	 * total asignacion familiar
	 * 
	 * @param totalAsigFam
	 */
	public void setTotalAsigFam(String totalAsigFam)
	{
		this.totalAsigFam = totalAsigFam;
	}

	/**
	 * total inp
	 * 
	 * @return
	 */
	public long getTotalINP()
	{
		return this.totalINP;
	}

	/**
	 * total inp
	 * 
	 * @param totalINP
	 */
	public void setTotalINP(long totalINP)
	{
		this.totalINP = totalINP;
	}

	/**
	 * afpv apellido materno dependiente
	 * 
	 * @return
	 */
	public String getAfpvAplldioMatDpndiente()
	{
		return this.afpvAplldioMatDpndiente;
	}

	/**
	 * afpv apellido materno dependiente
	 * 
	 * @param afpvAplldioMatDpndiente
	 */
	public void setAfpvAplldioMatDpndiente(String afpvAplldioMatDpndiente)
	{
		this.afpvAplldioMatDpndiente = afpvAplldioMatDpndiente;
	}

	/**
	 * afpv apillido peterno dependiente
	 * 
	 * @return
	 */
	public String getAfpvAplldioPatDpndiente()
	{
		return this.afpvAplldioPatDpndiente;
	}

	/**
	 * afpv apellido paterno dependiente
	 * 
	 * @param afpvAplldioPatDpndiente
	 */
	public void setAfpvAplldioPatDpndiente(String afpvAplldioPatDpndiente)
	{
		this.afpvAplldioPatDpndiente = afpvAplldioPatDpndiente;
	}

	/**
	 * afpv nombre dependiente
	 * 
	 * @return
	 */
	public String getAfpvNombreDpndiente()
	{
		return this.afpvNombreDpndiente;
	}

	/**
	 * afpv nombre dependiente
	 * 
	 * @param afpvNombreDpndiente
	 */
	public void setAfpvNombreDpndiente(String afpvNombreDpndiente)
	{
		this.afpvNombreDpndiente = afpvNombreDpndiente;
	}

	/**
	 * afpv rut dependiente
	 * 
	 * @return
	 */
	public String getAfpvRutDpndiente()
	{
		return this.afpvRutDpndiente;
	}

	/**
	 * afpv rut dependiente
	 * 
	 * @param afpvRutDpndiente
	 */
	public void setAfpvRutDpndiente(String afpvRutDpndiente)
	{
		this.afpvRutDpndiente = afpvRutDpndiente;
	}

	/**
	 * apvc aporte empl
	 * 
	 * @return
	 */
	public String getApvcAporteEmpl()
	{
		return this.apvcAporteEmpl;
	}

	/**
	 * apvc aporte empl
	 * 
	 * @param apvcAporteEmpl
	 */
	public void setApvcAporteEmpl(String apvcAporteEmpl)
	{
		this.apvcAporteEmpl = apvcAporteEmpl;
	}

	/**
	 * apvc aporte trab
	 * 
	 * @return
	 */
	public String getApvcAporteTrab()
	{
		return this.apvcAporteTrab;
	}

	/**
	 * apvc aporte trab
	 * 
	 * @param apvcAporteTrab
	 */
	public void setApvcAporteTrab(String apvcAporteTrab)
	{
		this.apvcAporteTrab = apvcAporteTrab;
	}

	/**
	 * apvc numero contrato
	 * 
	 * @return
	 */
	public String getApvcNumContr()
	{
		return this.apvcNumContr;
	}

	/**
	 * apvc numero contrato
	 * 
	 * @param apvcNumContr
	 */
	public void setApvcNumContr(String apvcNumContr)
	{
		this.apvcNumContr = apvcNumContr;
	}

	public void calculaTotalINP()
	{
		try
		{
			this.totalINP += new Integer(this.accTrabajoINP).intValue();
		} catch (Exception e)
		{
		}
		try
		{
			this.totalINP += new Integer(this.inpBonificacion).intValue();
		} catch (Exception e)
		{
		}
		try
		{
			this.totalINP += new Integer(this.inpDesahucio).intValue();
		} catch (Exception e)
		{
		}
	}

	public String getPrevisionSIS()
	{
		return this.previsionSIS;
	}

	public void setPrevisionSIS(String previsionSIS)
	{
		this.previsionSIS = previsionSIS;
	}

	public String getRentaImponibleSIS()
	{
		return this.rentaImponibleSIS;
	}

	public void setRentaImponibleSIS(String rentaImponibleSIS)
	{
		this.rentaImponibleSIS = rentaImponibleSIS;
	}
	
	/**
	 * @return el periodo
	 */
	public int getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo el periodo a establecer
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return el montoAFBR
	 */
	public String getOtrosAFBR() {
		return otrosAFBR;
	}

	/**
	 * @param montoAFBR el montoAFBR a establecer
	 */
	public void setOtrosAFBR(String otrosAFBR) {
		this.otrosAFBR = otrosAFBR;
	}


}
