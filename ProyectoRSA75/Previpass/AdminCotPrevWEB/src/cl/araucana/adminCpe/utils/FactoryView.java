package cl.araucana.adminCpe.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import cl.araucana.core.util.NamesParser;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoValidacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSilVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MovtoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoSiguienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ValidacionVO;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizacion;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizante;
import cl.araucana.cp.distribuidor.presentation.beans.MovtoPersonal;

/*
 * @(#) FactoryView.java 1.7 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * @author cchamblas
 * 
 * @version 1.7
 */
public class FactoryView
{
	private static Logger logger = Logger.getLogger(FactoryView.class);
	private List listaConceptos = null;
	private HashMap mapeosConcep = null;
	private Properties mapNombres = null;
	private HashMap mapeoValores = null;
	SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");

	/**
	 * listas conceptos
	 * 
	 * @param listaConceptos
	 * @param listaMapeo
	 * @param mapNombres
	 */
	public void setListasConceptos(List listaConceptos, List listaMapeo, Properties mapNombres)
	{
		this.listaConceptos = listaConceptos;
		this.mapeosConcep = new HashMap();
		this.mapNombres = mapNombres;

		for (Iterator itM = listaMapeo.iterator(); itM.hasNext();)
		{
			MapeoConceptoVO mc = (MapeoConceptoVO) itM.next();
			this.mapeosConcep.put("" + mc.getIdConcepto(), mc);
		}
	}

	/**
	 * lista cotizante vo
	 * 
	 * @param tipoProceso
	 * @param cotizantes
	 * @param sucursales
	 * @return
	 */
	public static List cotizanteVOtoView(char tipoProceso, List cotizantes, HashMap sucursales)
	{
		List result = new ArrayList();
		for (Iterator it = cotizantes.iterator(); it.hasNext();)
		{
			CotizanteVO cotizante = (CotizanteVO) it.next();
//			clillo 3-12-14 por Reliquidación
			//Cotizante cot = new Cotizante("" + cotizante.getIdCotizante(), cotizante.getIdConvenio(), cotizante.getRutEmpresa(), tipoProceso);
			Cotizante cot = new Cotizante("" + cotizante.getIdCotizante(), cotizante.getIdConvenio(), cotizante.getRutEmpresa(), tipoProceso, cotizante.getPeriodo());
			cot.setRut(Utils.formatRut(cotizante.getIdCotizante()));
			cot.setNombre(cotizante.getNombre());
			cot.setApellidos(cotizante.getApellidoPat() + " " + cotizante.getApellidoMat());
			if (sucursales.get(cotizante.getIdSucursal().trim()) != null)
				cot.setSucursal(((SucursalVO) sucursales.get(cotizante.getIdSucursal().trim())).getNombre());
			result.add(cot);
		}
		Collections.sort(result);
		return result;
	}

	/**
	 * lista pendientes
	 * 
	 * @param tipoProceso
	 * @param listaTrabPend
	 * @param sucursales
	 * @return
	 */
	public List pendientestoView(char tipoProceso, List listaTrabPend, HashMap sucursales)
	{
		List result = new ArrayList();
		for (Iterator it = listaTrabPend.iterator(); it.hasNext();)
		{
			CotizacionPendienteVO cotizPendiente = (CotizacionPendienteVO) it.next();
			Cotizante cot = cotizPendVOtoView(tipoProceso, cotizPendiente, Constants.NIVEL_VAL_ERROR, null);
			if (sucursales.containsKey(cot.getIdSucursal()))
				cot.setSucursal(((SucursalVO) sucursales.get(cot.getIdSucursal())).getNombre());
			result.add(cot);
		}
		Collections.sort(result);
		return result;
	}

	/**
	 * Invoca al metodo cotizPendVOtoView(char, CotizacionPendienteVO, int, NamesParser) enviando un 2 como nivelError
	 * 
	 * @param tipoProceso
	 * @param cotizPendVO
	 * @param parser
	 * @return
	 */
	public Cotizante cotizPendVOtoView(char tipoProceso, CotizacionPendienteVO cotizPendVO, NamesParser parser)
	{
		return cotizPendVOtoView(tipoProceso, cotizPendVO, 2, parser);
	}

	/**
	 * cotizante pendiente vo
	 * 
	 * @param tipoProceso
	 * @param cotizPendVO
	 * @param nivelError
	 * @param parser
	 * @return
	 */
	public Cotizante cotizPendVOtoView(char tipoProceso, CotizacionPendienteVO cotizPendVO, int nivelError, NamesParser parser)
	{
		this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle());
		Utils setter = new Utils(this.mapNombres, this.mapeoValores);
		Cotizante cot = new Cotizante();
		cot.setIdCotizPendiente(cotizPendVO.getIdCotizPendiente());
		cot.setIdCotizante(setter.setString("idCotizante"));
		cot.setIdConvenio(cotizPendVO.getIdConvenio());
		cot.setRutEmpresa(new Integer(cotizPendVO.getRutEmpresa()).intValue());
		cot.setTipoProceso(tipoProceso);
		cot.setNivelError(nivelError);
		String rut = setter.setString("idCotizante");
		// quitar dv
		if (rut.length() > 2)
			rut = rut.substring(0, rut.length() - 1);
		// agregar formato
		cot.setRut(Utils.formatRut(Integer.parseInt(rut)));
		cot.setNombre(setter.setString("nombre"));

		if (parser != null)
		{
			String[] names = parser.parse(setter.setString("apellido"));

			if (names != null && names.length >= 2)
			{
				cot.setApellidoPat(names[0].substring(0, (Math.min(20, names[0].length()))));
				cot.setApellidoMat(names[1].substring(0, (Math.min(20, names[1].length()))));
			} else
			{
				cot.setApellidoPat(setter.setString("apellido"));
				cot.setApellidoMat("");
			}
		} else
		{
			cot.setApellidoPat(setter.setString("apellido"));
			cot.setApellidoMat("");
		}

		cot.setApellidos(setter.setString("apellido"));
		cot.setIdSucursal(setter.setString("idSucursal"));
		cot.setSucursal(setter.setString("idSucursal"));
		cot.setIdEntExCaja(setter.setInt("idEntExCaja", -1));
		cot.setIdRegimenImp(setter.setInt("idRegimenImp", -1));
		cot.setIdEntSil(setter.setInt("idEntSil", -1));
		cot.setNumCargaSimple(setter.setString("numCargaSimple"));
		cot.setNumCargaMaterna(setter.setString("numCargaMaterna"));
		cot.setNumCargaInvalidez(setter.setString("numCargaInvalidez"));
		cot.setNumCargaSimpleINP(setter.setString("numCargaSimple"));
		cot.setNumCargaMaternaINP(setter.setString("numCargaMaterna"));
		cot.setNumCargaInvalidezINP(setter.setString("numCargaInvalidez"));
		cot.setNumDiasTrabajados(setter.setString("numDiasTrabajados"));

		cot.setIdEntSalud(setter.setString("idEntSalud"));
		cot.setIdTramo(setter.setString("idTramo"));
		cot.setIdTramo(setter.setString("idTramo"));
		cot.setIdEntPension(setter.setString("idEntPension"));
		cot.setIdEntidadAFPV(setter.setString("idEntidadAFPV"));
		if (tipoProceso == 'D')
			cot.setIdEntidadAPVC(setter.setString("idEntPension"));
		else
			cot.setIdEntidadAPVC(setter.setString("idEntidadAPVC"));
		cot.setIdEntAFC(setter.setString("idEntAfc"));
		cot.setIdGenero(setter.setString("idGenero"));

		return cot;
	}

	/**
	 * cotizante vo
	 * 
	 * @param tipoProceso
	 * @param cotizante
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param idCotizante
	 * @param tipoPrevision
	 * @param idEntSil
	 * @param idCaja
	 * @param listApvs
	 * @return
	 */
	public static CotizanteVO viewToCotizanteVO(char tipoProceso, Cotizante cotizante, int rutEmpresa, int idConvenio, int idCotizante, int tipoPrevision, int idEntSil, int idCaja, List listApvs)
	{
		CotizanteVO cot = new CotizanteVO(rutEmpresa, idConvenio, idCotizante);
		cot.setTipoProceso(tipoProceso);
		cot.setNombre(cotizante.getNombre());
		cot.setApellidoPat(cotizante.getApellidoPat());
		cot.setApellidoMat(cotizante.getApellidoMat());
		cot.setIdSucursal(cotizante.getIdSucursal());
		cot.setTipoPrevision(tipoPrevision);
		//
		cot.setIdEntSaludReal(cotizante.getIdEntSaludReal() == -1 ? Constants.ID_FONASA : cotizante.getIdEntSaludReal());
		if (idCaja == Constants.SIN_CCAF)
		{
			cot.setIdTramoReal(cotizante.getIdTramoRealINP() != -1 ? cotizante.getIdTramoRealINP() : Constants.TRAMO_ASIGFAM_NINGUNO);
			cot.setNumCargaSimple(new Integer(cotizante.getNumCargaSimpleINP()).intValue());
			cot.setNumCargaMaterna(new Integer(cotizante.getNumCargaMaternaINP()).intValue());
			cot.setNumCargaInvalidez(new Integer(cotizante.getNumCargaInvalidezINP()).intValue());
		} else
		{
			cot.setIdTramoReal(cotizante.getIdTramoReal() != -1 ? cotizante.getIdTramoReal() : Constants.TRAMO_ASIGFAM_NINGUNO);
			cot.setNumCargaSimple(new Integer(cotizante.getNumCargaSimple()).intValue());
			cot.setNumCargaMaterna(new Integer(cotizante.getNumCargaMaterna()).intValue());
			cot.setNumCargaInvalidez(new Integer(cotizante.getNumCargaInvalidez()).intValue());
		}
		cot.setIdEntidadAFPVReal(cotizante.getIdEntidadAFPVReal());
		cot.setIdEntidadAPVCReal(cotizante.getIdEntidadAPVCReal());
		cot.setIdEntExCaja(cotizante.getIdEntExCaja());
		cot.setIdRegimenImp(cotizante.getIdRegimenImp());
		cot.setIdSucursal(cotizante.getIdSucursal());
		cot.setIdEntSil(idEntSil);
		cot.setIdGeneroReal(new Integer(cotizante.getIdGeneroReal()).intValue());
		cot.setNumDiasTrabajados(new Integer(cotizante.getNumDiasTrabajados()).intValue());
		cot.setApellidoPat(cotizante.getApellidoPat());
		cot.setApellidoMat(cotizante.getApellidoMat());
		cot.setApvList(listApvs);
		if (tipoProceso != 'D')
		{
			if (tipoPrevision == 1)// APF
			{
				cot.setIdEntPensionReal(cotizante.getIdEntPensionReal());
				cot.setIdEntAfcReal(cotizante.getIdEntPensionReal());
			} else if (tipoPrevision == 2)// INP
			{
				cot.setIdEntPensionReal(Constants.ID_INP);
				cot.setIdEntAfcReal(cotizante.getIdEntPensionReal());
			} else
			// ninguna
			{
				cot.setIdEntPensionReal(Constants.ID_AFP_NINGUNA);
				cot.setIdEntAfcReal(cotizante.getIdEntPensionReal());
			}
		} else
		{
			cot.setIdEntPensionReal(cotizante.getIdEntPensionReal() == -1 ? Constants.ID_INP : cotizante.getIdEntPensionReal());
			cot.setIdEntAfcReal(cotizante.getIdEntPensionReal());
		}

		return cot;
	}

	/**
	 * cotizante vo
	 * 
	 * @param tipoProceso
	 * @param cotizante
	 * @param cotiz
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param idCotizante
	 * @param tipoPrevision
	 * @param idCaja
	 * @param idMutual
	 * @param mps
	 * @return
	 * @throws ParseException
	 */
	public static CotizacionVO viewToCotizacionVO(char tipoProceso, Cotizante cotizante, Cotizacion cotiz, int rutEmpresa, int idConvenio, int idCotizante, int tipoPrevision, int idCaja,
			int idMutual, List mps) throws ParseException
	{
		SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
		java.sql.Date init = new java.sql.Date(1);
		if (tipoProceso == 'R')
		{
			CotizacionREVO cot = new CotizacionREVO(rutEmpresa, idConvenio, idCotizante);
			cot.setRentaImp(Utils.desFormatMonto(cotiz.getRentaImp()));
			// campos comunes
			int valor = Utils.desFormatMonto(cotiz.getSaludObligISAPRE());
			if (valor > 0) // es ISAPRE
			{
				cot.setSaludObligatorio(valor);
				cot.setSaludAdicional(Utils.desFormatMonto(cotiz.getSaludAdicional()));
				cot.setSaludTotal(valor + cot.getSaludAdicional());
				cot.setSaludPactado(cot.getSaludTotal());
			} else
			// es FONASA
			{
				cot.setSaludObligatorio(Utils.desFormatMonto(cotiz.getSaludObligFONASA()));
				cot.setSaludAdicional(0);
				cot.setSaludTotal(cot.getSaludObligatorio());
				cot.setSaludPactado(cot.getSaludTotal());
				cotizante.setIdEntSaludReal(Constants.ID_FONASA);
			}

			int totalAsigFamINP = 0;
			if (idCaja == Constants.SIN_CCAF)
			{
				cot.setAsigFamiliar(Utils.desFormatMonto(cotiz.getAsigFamiliarINP()));
				cot.setAsigFamRetro(Utils.desFormatMonto(cotiz.getAsigFamRetroINP()));
				cot.setAsigFamReint(Utils.desFormatMonto(cotiz.getAsigFamReintINP()));
				totalAsigFamINP = cot.getAsigFamiliar() + cot.getAsigFamReint() - cot.getAsigFamRetro();
			} else
			{
				cot.setAsigFamiliar(Utils.desFormatMonto(cotiz.getAsigFamiliar()));
				cot.setAsigFamRetro(Utils.desFormatMonto(cotiz.getAsigFamRetro()));
				cot.setAsigFamReint(Utils.desFormatMonto(cotiz.getAsigFamReint()));
			}

			cot.setSegCesEmpl(Utils.desFormatMonto(cotiz.getSegCesEmpl()));
			cot.setSegCesRemImp(Utils.desFormatMonto(cotiz.getSegCesRemImp()));
			cot.setSegCesTrab(Utils.desFormatMonto(cotiz.getSegCesTrab()));

			cot.setInpDesahucio(Utils.desFormatMonto(cotiz.getInpDesahucio()));
			cot.setInpBonificacion(Utils.desFormatMonto(cotiz.getInpBonificacion()));
			if (tipoPrevision == 1)// es AFP
			{
				cot.setPrevisionObligatorio(Utils.desFormatMonto(cotiz.getPrevObligatorioAFP()));
				cot.setPrevisionAhorro(Utils.desFormatMonto(cotiz.getPrevisionAhorro()));
				cot.setPrevisionTotal(cot.getPrevisionObligatorio() + cot.getPrevisionAhorro() + cot.getSegCesEmpl() + cot.getSegCesTrab());
			} else if (tipoPrevision == 2)// es INP
			{
				cot.setPrevisionObligatorio(Utils.desFormatMonto(cotiz.getPrevObligatorioINP()));
				cot.setPrevisionAhorro(0);
				cot.setPrevisionTotal(cot.getPrevisionObligatorio() + cot.getInpDesahucio() + cot.getInpBonificacion() - totalAsigFamINP);
			} else
			// ninguna
			{
				cot.setPrevisionObligatorio(0);
				cot.setPrevisionAhorro(0);
				cot.setPrevisionTotal(0);
			}

			cot.setTasaTrabPesado(cotiz.getTasaTrabPesado().equals("-1") ? 0 : (new Float(cotiz.getTasaTrabPesado()).floatValue()));
			cot.setTipoTrabPesado(cotiz.getTipoTrabPesado());
			cot.setTrabPesado(Utils.desFormatMonto(cotiz.getTrabPesado()));
			cot.setMutualImp(Utils.desFormatMonto(cotiz.getMutualImp()));
			cot.setCcafAporte(Utils.desFormatMonto(cotiz.getAporteCaja()));

			if (idMutual == Constants.SIN_MUTUAL)
				cot.setInpMutual(Utils.desFormatMonto(cotiz.getAccTrabajoINP()));
			else
				cot.setInpMutual(Utils.desFormatMonto(cotiz.getAccTrabajoMutual()));

			cot.setCcafCredito(Utils.desFormatMonto(cotiz.getCcafCredito()));
			cot.setCcafDental(Utils.desFormatMonto(cotiz.getCcafDental()));
			cot.setCcafLeasing(Utils.desFormatMonto(cotiz.getCcafLeasing()));
			cot.setCcafSeguro(Utils.desFormatMonto(cotiz.getCcafSeguro()));
			if (cotizante.isAfpVoluntario())
				cot.setMovimientoPersonalAF(mps);
			else
				cot.setMovimientoPersonal(mps);
			// reforma
			cot.setApvcAporteEmpl(Utils.desFormatMonto(cotiz.getApvcAporteEmpl()));
			cot.setApvcAporteTrab(Utils.desFormatMonto(cotiz.getApvcAporteTrab()));
			cot.setApvcNumContr(cotiz.getApvcNumContr() == null ? "" : cotiz.getApvcNumContr());
			cot.setAfpvRutDpndiente(cotiz.getAfpvRutDpndiente() != null ? Utils.desFormatRut(cotiz.getAfpvRutDpndiente()) : 0);
			cot.setAfpvNombreDpndiente(cotiz.getAfpvNombreDpndiente() == null ? "" : cotiz.getAfpvNombreDpndiente());
			cot.setAfpvAplldioPatDpndiente(cotiz.getAfpvAplldioPatDpndiente() == null ? "" : cotiz.getAfpvAplldioPatDpndiente());
			cot.setAfpvAplldioMatDpndiente(cotiz.getAfpvAplldioMatDpndiente() == null ? "" : cotiz.getAfpvAplldioMatDpndiente());

			return cot;
		} else if (tipoProceso == 'G')
		{
			CotizacionGRVO cot = new CotizacionGRVO(new Integer(rutEmpresa).intValue(), new Integer(idConvenio).intValue(), new Integer(idCotizante).intValue());
			cot.setGratificacion(Utils.desFormatMonto(cotiz.getGratificacion()));
			cot.setInicio(cotiz.getInicio() != null && !cotiz.getInicio().equals("") ? new Date(dateFormat.parse(cotiz.getInicio()).getTime()) : init);
			cot.setTermino(cotiz.getTermino() != null && !cotiz.getTermino().equals("") ? new Date(dateFormat.parse(cotiz.getTermino()).getTime()) : init);

			int valor = Utils.desFormatMonto(cotiz.getSaludObligISAPRE());
			if (valor > 0) // es ISAPRE
				cot.setSaludObligatorio(valor);
			else
			// es FONASA
			{
				cot.setSaludObligatorio(Utils.desFormatMonto(cotiz.getSaludObligFONASA()));
				cotizante.setIdEntSaludReal(Constants.ID_FONASA);
			}

			if (tipoPrevision == 1)// es AFP
			{
				cot.setPrevisionObligatorio(Utils.desFormatMonto(cotiz.getPrevObligatorioAFP()));
			} else if (tipoPrevision == 1)// es INP
			{
				cot.setPrevisionObligatorio(Utils.desFormatMonto(cotiz.getPrevObligatorioINP()));
				// cotizante.setIdEntAFCReal(cotizante.getIdEntPensionReal());
				// cotizante.setIdEntPensionReal(Constants.ID_INP);
			} else
				// NINGUNA
				cot.setPrevisionObligatorio(0);

			cot.setSegCesEmpl(Utils.desFormatMonto(cotiz.getSegCesEmpl()));
			cot.setSegCesRemImp(Utils.desFormatMonto(cotiz.getSegCesRemImp()));
			cot.setSegCesTrab(Utils.desFormatMonto(cotiz.getSegCesTrab()));
			cot.setTasaTrabPesado(new Float(cotiz.getTasaTrabPesado()).floatValue());
			cot.setTipoTrabPesado(cotiz.getTipoTrabPesado());
			cot.setTrabPesado(Utils.desFormatMonto(cotiz.getTrabPesado()));
			cot.setMutualImp(Utils.desFormatMonto(cotiz.getMutualImp()));
			cot.setCcafAporte(Utils.desFormatMonto(cotiz.getAporteCaja()));
			if (idMutual == Constants.SIN_MUTUAL)
				cot.setInpMutual(Utils.desFormatMonto(cotiz.getAccTrabajoINP()));
			else
				cot.setInpMutual(Utils.desFormatMonto(cotiz.getAccTrabajoMutual()));
			return cot;
		} else if (tipoProceso == 'A')
		{
			CotizacionRAVO cot = new CotizacionRAVO(new Integer(rutEmpresa).intValue(), new Integer(idConvenio).intValue(), new Integer(idCotizante).intValue());
			cot.setReliquidacion(Utils.desFormatMonto(cotiz.getReliquidacion()));

			cot.setInicio(cotiz.getInicio() != null && !cotiz.getInicio().equals("") ? new Date(dateFormat.parse(cotiz.getInicio()).getTime()) : init);
			cot.setTermino(cotiz.getTermino() != null && !cotiz.getTermino().equals("") ? new Date(dateFormat.parse(cotiz.getTermino()).getTime()) : init);

			int valor = Utils.desFormatMonto(cotiz.getSaludObligISAPRE());
			if (valor > 0) // es ISAPRE
				cot.setSaludObligatorio(valor);
			else
			// es FONASA
			{
				cot.setSaludObligatorio(Utils.desFormatMonto(cotiz.getSaludObligFONASA()));
				cotizante.setIdEntSaludReal(Constants.ID_FONASA);
			}

			if (tipoPrevision == 1)// es AFP
			{
				cot.setPrevisionObligatorio(Utils.desFormatMonto(cotiz.getPrevObligatorioAFP()));
			} else if (tipoPrevision == 1)// es INP
			{
				cot.setPrevisionObligatorio(Utils.desFormatMonto(cotiz.getPrevObligatorioINP()));
				// cotizante.setIdEntAFCReal(cotizante.getIdEntPensionReal());
				// cotizante.setIdEntPensionReal(Constants.ID_INP);
			} else
				// NINGUNA
				cot.setPrevisionObligatorio(0);

			cot.setSegCesEmpl(Utils.desFormatMonto(cotiz.getSegCesEmpl()));
			cot.setSegCesRemImp(Utils.desFormatMonto(cotiz.getSegCesRemImp()));
			cot.setSegCesTrab(Utils.desFormatMonto(cotiz.getSegCesTrab()));
			cot.setTasaTrabPesado(new Float(cotiz.getTasaTrabPesado()).floatValue());
			cot.setTipoTrabPesado(cotiz.getTipoTrabPesado());
			cot.setTrabPesado(Utils.desFormatMonto(cotiz.getTrabPesado()));
			cot.setMutualImp(Utils.desFormatMonto(cotiz.getMutualImp()));
			cot.setCcafAporte(Utils.desFormatMonto(cotiz.getAporteCaja()));
			if (idMutual == Constants.SIN_MUTUAL)
				cot.setInpMutual(Utils.desFormatMonto(cotiz.getAccTrabajoINP()));
			else
				cot.setInpMutual(Utils.desFormatMonto(cotiz.getAccTrabajoMutual()));
			return cot;
		} else if (tipoProceso == 'D')
		{
			CotizacionDCVO cot = new CotizacionDCVO(new Integer(rutEmpresa).intValue(), new Integer(idConvenio).intValue(), new Integer(idCotizante).intValue());
			cot.setRentaImponible(Utils.desFormatMonto(cotiz.getRentaImp()));
			cot.setDepositoConvenido(Utils.desFormatMonto(cotiz.getDepositoConvenido()));
			cot.setTipoRegimenPrev(new Integer(cotiz.getTipoRegimenPrev()).intValue());
			cot.setTasaPactada(Utils.desFormatMontoF(cotiz.getTasaPactada()));
			cot.setIndemAporte(Utils.desFormatMonto(cotiz.getIndemAporte()));
			cot.setIndemInicio(cotiz.getIndemInicio() != null && !cotiz.getIndemInicio().equals("") ? new java.sql.Date(dateFormat.parse(cotiz.getIndemInicio()).getTime()) : init);
			cot.setIndemTermino(cotiz.getIndemTermino() != null && !cotiz.getIndemTermino().equals("") ? new java.sql.Date(dateFormat.parse(cotiz.getIndemTermino()).getTime()) : init);
			cot.setNumPeriodos(Utils.desFormatMonto(cotiz.getNumPeriodos()));
			cot.setIdEntDep(Integer.parseInt(cotiz.getIdEntDep()));
			return cot;
		}
		return null;
	}

	/**
	 * cortizante vo
	 * 
	 * @param tipoProceso
	 * @param format
	 * @param cotizante
	 * @param caja
	 * @param mutual
	 * @return
	 */
	public static Cotizacion cotizacionVOtoView(char tipoProceso, boolean format, CotizanteVO cotizante, EntidadCCAFVO caja, EntidadMutualVO mutual)
	{
		CotizacionVO cotizacion = cotizante.getCotizacion();
		if (cotizacion == null)
			return null;
		Cotizacion cot = new Cotizacion();

		String init = new java.sql.Date(1).toString();
		SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
		if (tipoProceso == 'R')
		{
			CotizacionREVO cotiz = (CotizacionREVO) cotizacion;
			cot.setRentaImp(format ? Utils.formatMonto(cotiz.getRentaImp()) : "" + cotiz.getRentaImp());
			long totalInp = 0;
			if (cotiz.isVoluntario()) 
				cot.setMovimientoPersonal(new ArrayList(cotiz.getMovimientoPersonalAF()));
			else
			if (cotiz.getMovimientoPersonal() != null)
			{
				List lista = new ArrayList();
				for (Iterator it = cotiz.getMovimientoPersonal().iterator(); it.hasNext();)
				{
					MovtoPersonalVO mp = (MovtoPersonalVO) it.next();
					lista.add(mp);
				}
				cot.setMovimientoPersonal(lista);
			}

			if (cotizante.getIdEntSaludReal() == Constants.ID_FONASA)
			{
				cot.setSaludObligISAPRE("" + 0);
				cot.setSaludObligFONASA(format ? Utils.formatMonto(cotiz.getSaludObligatorio()) : "" + cotiz.getSaludObligatorio());
				cot.setSaludPactado("" + 0);
				cot.setSaludAdicional("" + 0);
				cot.setSaludTotal("" + 0);
				totalInp += cotiz.getSaludObligatorio();
			} else
			{
				cot.setSaludObligISAPRE(format ? Utils.formatMonto(cotiz.getSaludObligatorio()) : "" + cotiz.getSaludObligatorio());
				cot.setSaludObligFONASA("" + 0);
				cot.setSaludPactado(format ? Utils.formatMonto(cotiz.getSaludPactado()) : "" + cotiz.getSaludPactado());
				cot.setSaludAdicional(format ? Utils.formatMonto(cotiz.getSaludAdicional()) : "" + cotiz.getSaludAdicional());
				cot.setSaludTotal(format ? Utils.formatMonto(cotiz.getSaludTotal()) : "" + cotiz.getSaludTotal());
			}
			if (cotizante.getIdEntPensionReal() == Constants.ID_INP)
			{
				cot.setPrevObligatorioAFP("" + 0);
				cot.setPrevObligatorioINP(format ? Utils.formatMonto(cotiz.getPrevisionObligatorio()) : "" + cotiz.getPrevisionObligatorio());
				cot.setPrevisionAdicional("" + 0);
				cot.setPrevisionAhorro("" + 0);
				cot.setPrevisionTotal("" + cotiz.getSegCesEmpl() + cotiz.getSegCesTrab());
				totalInp += cotiz.getPrevisionObligatorio();
			} else
			{
				cot.setPrevObligatorioAFP(format ? Utils.formatMonto(cotiz.getPrevisionObligatorio()) : "" + cotiz.getPrevisionObligatorio());
				cot.setPrevObligatorioINP("" + 0);
				// cot.setPrevisionAdicional(format ? Utils.formatMonto(cotiz.getPrevisionAdicional()) : "" + cotiz.getPrevisionAdicional());
				cot.setPrevisionAhorro(format ? Utils.formatMonto(cotiz.getPrevisionAhorro()) : "" + cotiz.getPrevisionAhorro());
				int totalAFP = cotiz.getSegCesEmpl() + cotiz.getSegCesTrab() + cotiz.getTrabPesado() + cotiz.getPrevisionObligatorio();
				cot.setPrevisionTotal(format ? Utils.formatMonto(totalAFP) : "" + totalAFP);
			}
			cot.setSegCesEmpl(format ? Utils.formatMonto(cotiz.getSegCesEmpl()) : "" + cotiz.getSegCesEmpl());
			cot.setSegCesRemImp(format ? Utils.formatMonto(cotiz.getSegCesRemImp()) : "" + cotiz.getSegCesRemImp());
			cot.setSegCesTrab(format ? Utils.formatMonto(cotiz.getSegCesTrab()) : "" + cotiz.getSegCesTrab());

			cot.setTasaTrabPesado("" + cotiz.getTasaTrabPesado());
			cot.setTipoTrabPesado(cotiz.getTipoTrabPesado().trim());
			cot.setTrabPesado(format ? Utils.formatMonto(cotiz.getTrabPesado()) : "" + cotiz.getTrabPesado());
			cot.setMutualImp(format ? Utils.formatMonto(cotiz.getMutualImp()) : "" + cotiz.getMutualImp());
			cot.setAporteCaja(format ? Utils.formatMonto(cotiz.getCcafAporte()) : "" + cotiz.getCcafAporte());
			if (mutual != null)// hay mutual asociada a la empresa
			{
				cot.setAccTrabajoMutual(format ? Utils.formatMonto(cotiz.getInpMutual()) : "" + cotiz.getInpMutual());
				cot.setAccTrabajoINP("" + 0);
			} else
			{
				cot.setAccTrabajoMutual("" + 0);
				cot.setAccTrabajoINP(format ? Utils.formatMonto(cotiz.getInpMutual()) : "" + cotiz.getInpMutual());
				totalInp += cotiz.getInpMutual();
			}
			if (caja != null)
			{
				cot.setAsigFamiliar(format ? Utils.formatMonto(cotiz.getAsigFamiliar()) : "" + cotiz.getAsigFamiliar());
				cot.setAsigFamRetro(format ? Utils.formatMonto(cotiz.getAsigFamRetro()) : "" + cotiz.getAsigFamRetro());
				cot.setAsigFamReint(format ? Utils.formatMonto(cotiz.getAsigFamReint()) : "" + cotiz.getAsigFamReint());
				cot.setAsigFamiliarINP("" + 0);
				cot.setAsigFamRetroINP("" + 0);
				cot.setAsigFamReintINP("" + 0);
				cot.setTotalAsigFam("" + (cotiz.getAsigFamiliar() + cotiz.getAsigFamRetro() - cotiz.getAsigFamReint()));
			} else
			{
				cot.setAsigFamiliarINP(format ? Utils.formatMonto(cotiz.getAsigFamiliar()) : "" + cotiz.getAsigFamiliar());
				cot.setAsigFamReintINP(format ? Utils.formatMonto(cotiz.getAsigFamReint()) : "" + cotiz.getAsigFamReint());
				cot.setAsigFamRetroINP(format ? Utils.formatMonto(cotiz.getAsigFamRetro()) : "" + cotiz.getAsigFamRetro());
				cot.setAsigFamiliar("" + 0);
				cot.setAsigFamRetro("" + 0);
				cot.setAsigFamReint("" + 0);
				cot.setTotalAsigFam("" + 0);
				totalInp = totalInp - cotiz.getAsigFamiliar() + cotiz.getAsigFamReint() - cotiz.getAsigFamRetro();
			}
			cot.setInpBonificacion(format ? Utils.formatMonto(cotiz.getInpBonificacion()) : "" + cotiz.getInpBonificacion());
			totalInp -= cotiz.getInpBonificacion();
			cot.setInpDesahucio(format ? Utils.formatMonto(cotiz.getInpDesahucio()) : "" + cotiz.getInpDesahucio());
			totalInp += cotiz.getInpDesahucio();
			cot.setCcafCredito(format ? Utils.formatMonto(cotiz.getCcafCredito()) : "" + cotiz.getCcafCredito());
			cot.setCcafDental(format ? Utils.formatMonto(cotiz.getCcafDental()) : "" + cotiz.getCcafDental());
			cot.setCcafLeasing(format ? Utils.formatMonto(cotiz.getCcafLeasing()) : "" + cotiz.getCcafLeasing());
			cot.setCcafSeguro(format ? Utils.formatMonto(cotiz.getCcafSeguro()) : "" + cotiz.getCcafSeguro());

			cot.setRemImpPension(format ? Utils.formatMonto(cotiz.getRentaImpInp()) : "" + cotiz.getRentaImpInp());
			cot.setTotalINP(totalInp);
			// reforma
			cot.setApvcAporteEmpl(format ? Utils.formatMonto(cotiz.getApvcAporteEmpl()) : "" + cotiz.getApvcAporteEmpl());
			cot.setApvcAporteTrab(format ? Utils.formatMonto(cotiz.getApvcAporteTrab()) : "" + cotiz.getApvcAporteTrab());
			cot.setApvcNumContr(cotiz.getApvcNumContr().trim());
			cot.setAfpvRutDpndiente(cotiz.getAfpvRutDpndiente() > 0 ? Utils.formatRut(new Integer(cotiz.getAfpvRutDpndiente()).intValue()) : "");
			cot.setAfpvNombreDpndiente(cotiz.getAfpvNombreDpndiente().trim());

			cot.setAfpvAplldioPatDpndiente(cotiz.getAfpvAplldioPatDpndiente() != null ? cotiz.getAfpvAplldioPatDpndiente().trim() : "");

			cot.setAfpvAplldioMatDpndiente(cotiz.getAfpvAplldioMatDpndiente().trim());
		} else if (tipoProceso == 'G')
		{
			CotizacionGRVO cotiz = (CotizacionGRVO) cotizacion;
			cot.setGratificacion(format ? Utils.formatMonto(cotiz.getGratificacion()) : "" + cotiz.getGratificacion());
			cot.setInicio((cotiz.getInicio() != null && cotiz.getInicio().getTime()>Constants.FECHA_DEFECTO_FACTIBLE? dateFormat.format(cotiz.getInicio()) : ""));
			cot.setTermino((cotiz.getTermino() != null && cotiz.getTermino().getTime()>Constants.FECHA_DEFECTO_FACTIBLE ? dateFormat.format(cotiz.getTermino()) : ""));
			long totalInp = 0;
			// campos comunes
			if (cotizante.getIdEntSaludReal() == Constants.ID_FONASA)
			{
				cot.setSaludObligISAPRE("" + 0);
				cot.setSaludObligFONASA(format ? Utils.formatMonto(cotiz.getSaludObligatorio()) : "" + cotiz.getSaludObligatorio());
				totalInp += cotiz.getSaludObligatorio();
				cot.setSaludTotal("" + 0);
			} else
			{
				cot.setSaludObligISAPRE(format ? Utils.formatMonto(cotiz.getSaludObligatorio()) : "" + cotiz.getSaludObligatorio());
				cot.setSaludObligFONASA("" + 0);
				cot.setSaludTotal(cot.getSaludObligISAPRE());
			}
			if (cotizante.getIdEntPensionReal() == Constants.ID_INP)
			{
				cot.setPrevObligatorioAFP("" + 0);
				cot.setPrevObligatorioINP(format ? Utils.formatMonto(cotiz.getPrevisionObligatorio()) : "" + cotiz.getPrevisionObligatorio());
				cot.setPrevisionTotal("" + 0);
				totalInp += cotiz.getPrevisionObligatorio();
				cot.setPrevisionTotal("" + (cotiz.getSegCesRemImp() + cotiz.getSegCesTrab()));
			} else
			{
				cot.setPrevObligatorioAFP(format ? Utils.formatMonto(cotiz.getPrevisionObligatorio()) : "" + cotiz.getPrevisionObligatorio());
				cot.setPrevObligatorioINP("" + 0);
				cot.setPrevisionTotal("" + (cotiz.getPrevisionObligatorio() + cotiz.getSegCesRemImp() + cotiz.getSegCesTrab() + cotiz.getTrabPesado()));
			}
			cot.setSegCesEmpl(format ? Utils.formatMonto(cotiz.getSegCesEmpl()) : "" + cotiz.getSegCesEmpl());
			cot.setSegCesRemImp(format ? Utils.formatMonto(cotiz.getSegCesRemImp()) : "" + cotiz.getSegCesRemImp());
			cot.setSegCesTrab(format ? Utils.formatMonto(cotiz.getSegCesTrab()) : "" + cotiz.getSegCesTrab());
			cot.setTasaTrabPesado("" + cotiz.getTasaTrabPesado());
			cot.setTipoTrabPesado(cotiz.getTipoTrabPesado().trim());
			cot.setTrabPesado(format ? Utils.formatMonto(cotiz.getTrabPesado()) : "" + cotiz.getTrabPesado());
			cot.setMutualImp(format ? Utils.formatMonto(cotiz.getMutualImp()) : "" + cotiz.getMutualImp());
			cot.setAporteCaja(format ? Utils.formatMonto(cotiz.getCcafAporte()) : "" + cotiz.getCcafAporte());
			if (mutual != null)// hay mutual asociada a la empresa
			{
				cot.setAccTrabajoMutual(format ? Utils.formatMonto(cotiz.getInpMutual()) : "" + cotiz.getInpMutual());
				cot.setAccTrabajoINP("" + 0);
			} else
			{
				cot.setAccTrabajoMutual("" + 0);
				cot.setAccTrabajoINP(format ? Utils.formatMonto(cotiz.getInpMutual()) : "" + cotiz.getInpMutual());
				totalInp += cotiz.getInpMutual();
			}
			cot.setTotalINP(totalInp);
		} else if (tipoProceso == 'A')
		{
			CotizacionRAVO cotiz = (CotizacionRAVO) cotizacion;
			cot.setReliquidacion(format ? Utils.formatMonto(cotiz.getReliquidacion()) : "" + cotiz.getReliquidacion());
			cot.setInicio((cotiz.getInicio() != null ? dateFormat.format(cotiz.getInicio()) : ""));
			cot.setTermino((cotiz.getTermino() != null ? dateFormat.format(cotiz.getTermino()) : ""));
			long totalInp = 0;
			// campos comunes
			if (cotizante.getIdEntSaludReal() == Constants.ID_FONASA)
			{
				cot.setSaludObligISAPRE("" + 0);
				cot.setSaludObligFONASA(format ? Utils.formatMonto(cotiz.getSaludObligatorio()) : "" + cotiz.getSaludObligatorio());
				totalInp += cotiz.getSaludObligatorio();
				cot.setSaludTotal("" + 0);
			} else
			{
				cot.setSaludObligISAPRE(format ? Utils.formatMonto(cotiz.getSaludObligatorio()) : "" + cotiz.getSaludObligatorio());
				cot.setSaludObligFONASA("" + 0);
				cot.setSaludTotal(cot.getSaludObligISAPRE());
			}
			if (cotizante.getIdEntPensionReal() == Constants.ID_INP)
			{
				cot.setPrevObligatorioAFP("" + 0);
				cot.setPrevObligatorioINP(format ? Utils.formatMonto(cotiz.getPrevisionObligatorio()) : "" + cotiz.getPrevisionObligatorio());
				totalInp += cotiz.getPrevisionObligatorio();
				cot.setPrevisionTotal("" + (cotiz.getSegCesEmpl() + cotiz.getSegCesTrab()));
			} else
			{
				cot.setPrevObligatorioAFP(format ? Utils.formatMonto(cotiz.getPrevisionObligatorio()) : "" + cotiz.getPrevisionObligatorio());
				cot.setPrevObligatorioINP("" + 0);
				cot.setPrevisionTotal("" + (cotiz.getSegCesEmpl() + cotiz.getSegCesTrab() + cotiz.getTrabPesado() + cotiz.getPrevisionObligatorio()));
			}
			cot.setSegCesEmpl(format ? Utils.formatMonto(cotiz.getSegCesEmpl()) : "" + cotiz.getSegCesEmpl());
			cot.setSegCesRemImp(format ? Utils.formatMonto(cotiz.getSegCesRemImp()) : "" + cotiz.getSegCesRemImp());
			cot.setSegCesTrab(format ? Utils.formatMonto(cotiz.getSegCesTrab()) : "" + cotiz.getSegCesTrab());
			cot.setTasaTrabPesado("" + cotiz.getTasaTrabPesado());
			cot.setTipoTrabPesado(cotiz.getTipoTrabPesado().trim());
			cot.setTrabPesado(format ? Utils.formatMonto(cotiz.getTrabPesado()) : "" + cotiz.getTrabPesado());
			cot.setMutualImp(format ? Utils.formatMonto(cotiz.getMutualImp()) : "" + cotiz.getMutualImp());
			cot.setAporteCaja(format ? Utils.formatMonto(cotiz.getCcafAporte()) : "" + cotiz.getCcafAporte());
			if (mutual != null)// hay mutual asociada a la empresa
			{
				cot.setAccTrabajoMutual(format ? Utils.formatMonto(cotiz.getInpMutual()) : "" + cotiz.getInpMutual());
				cot.setAccTrabajoINP("" + 0);
			} else
			{
				cot.setAccTrabajoMutual("" + 0);
				cot.setAccTrabajoINP(format ? Utils.formatMonto(cotiz.getInpMutual()) : "" + cotiz.getInpMutual());
				totalInp += cotiz.getInpMutual();
			}
			cot.setTotalINP(totalInp);
		} else if (tipoProceso == 'D')
		{
			CotizacionDCVO cotiz = (CotizacionDCVO) cotizacion;
			cot.setRentaImp(format ? Utils.formatMonto(cotiz.getRentaImponible()) : "" + cotiz.getRentaImponible());
			cot.setDepositoConvenido(format ? Utils.formatMonto(cotiz.getDepositoConvenido()) : "" + cotiz.getDepositoConvenido());
			cot.setTipoRegimenPrev("" + cotiz.getTipoRegimenPrev());
			cot.setTasaPactada(format ? Utils.formatMontoF(cotiz.getTasaPactada()) : "" + cotiz.getTasaPactada());
			int aporteIndem = cotiz.getIndemAporte();
			if (aporteIndem == 0)
				aporteIndem = Math.round(cotiz.getRentaImponible() * cotiz.getTasaPactada() / 100);
			cot.setIndemAporte(format ? Utils.formatMonto(aporteIndem) : "" + aporteIndem);
			cot.setIndemInicio(cotiz.getIndemInicio() != null && !cotiz.getIndemInicio().toString().equals(init) ? dateFormat.format(cotiz.getIndemInicio()) : "");
			cot.setIndemTermino(cotiz.getIndemTermino() != null && !cotiz.getIndemTermino().toString().equals(init) ? dateFormat.format(cotiz.getIndemTermino()) : "");
			cot.setNumPeriodos(format ? Utils.formatMonto(cotiz.getNumPeriodos()) : "" + cotiz.getNumPeriodos());
			cot.setIdEntDep("" + cotiz.getIdEntDep());
		}
		return cot;
	}

	/**
	 * cotizante vo
	 * 
	 * @param tipoProceso
	 * @param cotizante
	 * @return
	 */
	public static Cotizante cotizanteVOtoView(char tipoProceso, CotizanteVO cotizante)
	{
		if (cotizante == null)
			return null;
//		clillo 3-12-14 por Reliquidación
		//Cotizante cot = new Cotizante("" + cotizante.getIdCotizante(), cotizante.getIdConvenio(), cotizante.getRutEmpresa(), tipoProceso);
		Cotizante cot = new Cotizante("" + cotizante.getIdCotizante(), cotizante.getIdConvenio(), cotizante.getRutEmpresa(), tipoProceso, cotizante.getPeriodo());
		cot.setRut(Utils.formatRut(cotizante.getIdCotizante()));
		cot.setNombre(cotizante.getNombre().trim());
		cot.setApellidos(cotizante.getApellidoPat().trim() + " " + cotizante.getApellidoMat().trim());
		cot.setSucursal(cotizante.getIdSucursal().trim());

		cot.setIdEntSaludReal(cotizante.getIdEntSaludReal());
		cot.setIdTramoReal(cotizante.getIdTramoReal());
		cot.setIdTramoRealINP(cotizante.getIdTramoReal());
		if (cotizante.getIdEntPensionReal() != Constants.ID_INP && cotizante.getIdEntPensionReal() != Constants.ID_AFP_NINGUNA)
			cot.setIdEntPensionReal(cotizante.getIdEntPensionReal());
		else
			cot.setIdEntPensionReal(cotizante.getIdEntAfcReal());
		cot.setIdEntidadAFPVReal(cotizante.getIdEntidadAFPVReal());
		cot.setIdEntidadAPVCReal(cotizante.getIdEntidadAPVCReal());
		cot.setIdEntAFCReal(cotizante.getIdEntAfcReal());
		cot.setIdEntExCaja(cotizante.getIdEntExCaja());
		cot.setIdRegimenImp(cotizante.getIdRegimenImp());
		cot.setIdSucursal(cotizante.getIdSucursal().trim());
		cot.setIdEntSil(cotizante.getIdEntSil());
		cot.setIdGeneroReal("" + cotizante.getIdGeneroReal());
		cot.setNumCargaSimple("" + cotizante.getNumCargaSimple());
		cot.setNumCargaMaterna("" + cotizante.getNumCargaMaterna());
		cot.setNumCargaInvalidez("" + cotizante.getNumCargaInvalidez());
		cot.setNumCargaSimpleINP("" + cotizante.getNumCargaSimple());
		cot.setNumCargaMaternaINP("" + cotizante.getNumCargaMaterna());
		cot.setNumCargaInvalidezINP("" + cotizante.getNumCargaInvalidez());
		cot.setNumDiasTrabajados("" + cotizante.getNumDiasTrabajados());
		cot.setApellidoPat(cotizante.getApellidoPat().trim());
		cot.setApellidoMat(cotizante.getApellidoMat().trim());

		return cot;
	}

	/**
	 * cotizante pendiente vo
	 * 
	 * @param tipoProceso
	 * @param cotizante
	 * @param cotizPendVO
	 * @param caja
	 * @param mutual
	 * @param parser
	 * @return
	 */
	public Cotizacion cotizPendVOtoView(char tipoProceso, Cotizante cotizante, CotizacionPendienteVO cotizPendVO, EntidadCCAFVO caja, EntidadMutualVO mutual, NamesParser parser)
	{
		Cotizacion cot = new Cotizacion();

		if (this.mapeoValores == null)
			this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle());
		Utils setter = new Utils(this.mapNombres, this.mapeoValores);
		cot.cargaConceptos(tipoProceso, setter);

		if (tipoProceso == 'R')
		{
			// reforma
			if (!setter.setString("rutDependiente").equals("") || !setter.setString("nombreDependiente").equals("") || !setter.setString("apellidoDep").equals(""))
				cotizante.setAfpVoluntario(true);
			if (cotizante.getIdEntSaludReal() == Constants.ID_FONASA)
			{
				cot.setSaludObligISAPRE("" + 0);
				cot.setSaludObligFONASA(setter.setString("saludObligatorio"));
				cot.setSaludPactado("" + 0);
				cot.setSaludAdicional("" + 0);
				cot.setSaludTotal("" + 0);
			} else
			{
				cot.setSaludObligISAPRE(setter.setString("saludObligatorio"));
				cot.setSaludObligFONASA("" + 0);
				cot.setSaludPactado(setter.setString("saludPactado"));
				cot.setSaludAdicional(setter.setString("saludAdicional"));
				cot.setSaludTotal(setter.setString("saludTotal"));
			}
			if (cotizante.getIdEntPensionReal() == Constants.ID_INP)
			{
				cot.setPrevObligatorioAFP("" + 0);
				cot.setPrevObligatorioINP(setter.setString("previsionObligatorio"));
				cot.setPrevisionAdicional("" + 0);
				cot.setPrevisionAhorro("" + 0);
				cot.setPrevisionTotal("" + 0);
				cotizante.setIdEntPensionReal(cotizante.getIdEntAFCReal());
			} else
			{
				cot.setPrevObligatorioAFP(setter.setString("previsionObligatorio"));
				cot.setPrevObligatorioINP("" + 0);
				cot.setPrevisionAdicional(setter.setString("previsionAdicional"));
				cot.setPrevisionAhorro(setter.setString("previsionAhorro"));
				cot.setPrevisionTotal(setter.setString("previsionTotal"));
			}
			if (mutual != null)// hay mutual asociada a la empresa
			{
				cot.setAccTrabajoMutual(setter.setString("inpMutual"));
				cot.setAccTrabajoINP("" + 0);
			} else
			{
				cot.setAccTrabajoMutual("" + 0);
				cot.setAccTrabajoINP(setter.setString("inpMutual"));
			}
			if (caja != null)
			{
				cot.setAsigFamiliar(setter.setString("asigFamiliar"));
				cot.setAsigFamRetro(setter.setString("asigFamRetro"));
				cot.setAsigFamReint(setter.setString("asigFamReint"));
				cotizante.setNumCargaSimpleINP("0");
				cotizante.setNumCargaMaternaINP("0");
				cotizante.setNumCargaInvalidezINP("0");
				cot.setAsigFamiliarINP("" + 0);
				cot.setTotalAsigFam("" + (setter.setInt("asigFamiliar") + setter.setInt("asigFamRetro") - setter.setInt("asigFamReint")));
			} else
			{
				cot.setAsigFamiliarINP(setter.setString("asigFamiliar"));
				cot.setAsigFamiliar("0");
				cot.setAsigFamRetro("0");
				cot.setAsigFamReint("0");
				cot.setTotalAsigFam("0");
				cotizante.setNumCargaSimple("0");
				cotizante.setNumCargaMaterna("0");
				cotizante.setNumCargaInvalidez("0");
			}
			long totalInp = 0;
			try
			{
				totalInp += new Integer(cot.getAccTrabajoINP()).intValue();
			} catch (Exception e)
			{
			}
			try
			{
				totalInp += new Integer(cot.getAsigFamiliarINP()).intValue();
			} catch (Exception e)
			{
			}
			try
			{
				totalInp += new Integer(cot.getAsigFamRetroINP()).intValue();
			} catch (Exception e)
			{
			}
			try
			{
				totalInp -= new Integer(cot.getAsigFamReintINP()).intValue();
			} catch (Exception e)
			{
			}
			try
			{
				totalInp += new Integer(cot.getInpBonificacion()).intValue();
			} catch (Exception e)
			{
			}
			try
			{
				totalInp += new Integer(cot.getInpDesahucio()).intValue();
			} catch (Exception e)
			{
			}
			try
			{
				totalInp += new Integer(cot.getPrevObligatorioINP()).intValue();
			} catch (Exception e)
			{
			}
			try
			{
				totalInp += new Integer(cot.getSaludObligFONASA()).intValue();
			} catch (Exception e)
			{
			}
			cot.setTotalINP(totalInp);

			// reforma
			cot.setApvcAporteEmpl(setter.setString("apvcAporteEmpl"));
			cot.setApvcAporteTrab(setter.setString("apvcAporteTrab"));
			cot.setApvcNumContr(setter.setString("apvcNumContrato").trim());
			cot.setAfpvRutDpndiente(setter.setString("rutDependiente"));
			cot.setAfpvNombreDpndiente(setter.setString("nombreDependiente").trim());

			if (parser != null)
			{
				String[] names = parser.parse(setter.setString("apellidoDep").trim());

				if (names != null && names.length >= 2)
				{
					cot.setAfpvAplldioPatDpndiente(names[0].substring(0, (Math.min(20, names[0].length()))));
					cot.setAfpvAplldioMatDpndiente(names[1].substring(0, (Math.min(20, names[1].length()))));
				} else
				{
					cot.setAfpvAplldioPatDpndiente(setter.setString("apellidoDep").trim());
					cot.setAfpvAplldioMatDpndiente("");
				}
			} else
			{
				cot.setAfpvAplldioPatDpndiente(setter.setString("apellidoDep").trim());
				cot.setAfpvAplldioMatDpndiente("");
			}
		} else if (tipoProceso == 'G')
		{
			cot.setInicio(formatFecha(setter.setString("inicio")));
			cot.setTermino(formatFecha(setter.setString("termino")));

			if (cotizante.getIdEntSaludReal() == Constants.ID_FONASA)
			{
				cot.setSaludObligISAPRE("" + 0);
				cot.setSaludObligFONASA(setter.setString("saludObligatorio"));
				cot.setSaludTotal("" + 0);
			} else
			{
				cot.setSaludObligISAPRE(setter.setString("saludObligatorio"));
				cot.setSaludObligFONASA("" + 0);
				cot.setSaludTotal(setter.setString("saludObligatorio"));
			}
			if (cotizante.getIdEntPensionReal() == Constants.ID_INP)
			{
				cot.setPrevObligatorioAFP("" + 0);
				cot.setPrevObligatorioINP(setter.setString("previsionObligatorio"));
				cotizante.setIdEntPensionReal(cotizante.getIdEntAFCReal());
			} else
			{
				cot.setPrevObligatorioAFP(setter.setString("previsionObligatorio"));
				cot.setPrevObligatorioINP("" + 0);
			}
			cot.setPrevisionTotal(setter.setString("previsionTotal"));
			if (mutual != null)// hay mutual asociada a la empresa
			{
				cot.setAccTrabajoMutual(setter.setString("inpMutual"));
				cot.setAccTrabajoINP("" + 0);
			} else
			{
				cot.setAccTrabajoMutual("" + 0);
				cot.setAccTrabajoINP(setter.setString("inpMutual"));
			}
		} else if (tipoProceso == 'A')
		{
			cot.setInicio(formatFecha(setter.setString("inicioReli")));
			//clillo 10/12/14 por Reliquidación
			String fechareli= setter.setString("inicioReli");
			if(fechareli != null && fechareli.length()==10){
				cot.setPeriodo(Integer.parseInt(fechareli.substring(6, 10) + fechareli.substring(3, 5)));
			}
			cot.setTermino(formatFecha(setter.setString("terminoReli")));
			if (cotizante.getIdEntSaludReal() == Constants.ID_FONASA)
			{
				cot.setSaludObligISAPRE("" + 0);
				cot.setSaludObligFONASA(setter.setString("saludObligatorio"));
			} else
			{
				cot.setSaludObligISAPRE(setter.setString("saludObligatorio"));
				cot.setSaludObligFONASA("" + 0);
			}
			if (cotizante.getIdEntPensionReal() == Constants.ID_INP)
			{
				cot.setPrevObligatorioAFP("" + 0);
				cot.setPrevisionTotal("0");
				cot.setPrevObligatorioINP(setter.setString("previsionObligatorio"));
				cotizante.setIdEntPensionReal(cotizante.getIdEntAFCReal());
			} else
			{
				cot.setPrevObligatorioAFP(setter.setString("previsionObligatorio"));
				cot.setPrevObligatorioINP("" + 0);
				cot.setPrevisionTotal("0");
			}
			if (mutual != null)// hay mutual asociada a la empresa
			{
				cot.setAccTrabajoMutual(setter.setString("inpMutual"));
				cot.setAccTrabajoINP("" + 0);
			} else
			{
				cot.setAccTrabajoMutual("" + 0);
				cot.setAccTrabajoINP(setter.setString("inpMutual"));
			}
		} else if (tipoProceso == 'D')
		{
			if (cot.getIndemInicio() != null)
				cot.setIndemInicio(cot.getIndemInicio().replaceAll("_", "-"));
			if (cot.getIndemTermino() != null)
				cot.setIndemTermino(cot.getIndemTermino().replaceAll("_", "-"));
		}

		return cot;
	}

	/**
	 * format fecha
	 * 
	 * @param fecha
	 * @return
	 */
	public String formatFecha(String fecha)
	{
		java.util.Date init = new java.util.Date(1);
		if (fecha != null && !fecha.equals(""))
		{
			try
			{
				java.util.Date date = this.dateFormat.parse(fecha);
				if (date.equals(init))
					return "";
				return fecha;
			} catch (Exception e)
			{
			}
		}
		return "";
	}

	/**
	 * pend to view
	 * 
	 * @param cotizPendVO
	 * @param listValidaApvs
	 * @param mapeosApvs
	 * @return
	 */
	public List apvPendToView(CotizacionPendienteVO cotizPendVO, List listValidaApvs, List mapeosApvs, List listaApvs)
	{
		if (this.mapeoValores == null) {
			this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle());
		}
		ValidacionVO siguiente = (ValidacionVO) listValidaApvs.get(0);
		while (siguiente != null)
		{
			ApvVO apv = new ApvVO(Constants.APV_INVALIDO);
			try
			{
				String entidad = getValorConcepto(siguiente.getConceptos());
				if (!entidad.equals(""))
				{
					for (Iterator it = mapeosApvs.iterator(); it.hasNext();)
					{
						MapeoAPVVO mapeo = (MapeoAPVVO) it.next();
						if (mapeo.getCodigo().trim().equals(entidad))
						{
							apv.setIdEntidadApv(mapeo.getId());
							break;
						}
					}
				}
			} catch (Exception e)
			{
			}
			siguiente = getSiguienteValidacion(siguiente.getSiguientes(), listValidaApvs);
			if (siguiente != null)
				apv.setMontoFormat(getValorConcepto(siguiente.getConceptos()));
			listaApvs.add(apv);
			if (siguiente != null)
				siguiente = getSiguienteValidacion(siguiente.getSiguientes(), listValidaApvs);
		}
		return listaApvs;
	}

	/**
	 * validacion vo siguiente
	 * 
	 * @param listaSiguientes
	 * @param listaValidaciones
	 * @return
	 */
	private ValidacionVO getSiguienteValidacion(List listaSiguientes, List listaValidaciones)
	{
		for (Iterator it = listaSiguientes.iterator(); it.hasNext();)
		{
			NodoSiguienteVO ns = (NodoSiguienteVO) it.next();
			for (Iterator it2 = listaValidaciones.iterator(); it2.hasNext();)
			{
				ValidacionVO v = (ValidacionVO) it2.next();
				if (ns.getIdSiguiente().equals(v.getIdValidacion()))
					return v;
			}
		}
		return null;
	}

	/**
	 * valor concepto
	 * 
	 * @param parametros
	 * @return
	 */
	public String getValorConcepto(List parametros)
	{
		for (Iterator it = parametros.iterator(); it.hasNext();)
		{
			ConceptoValidacionVO c = (ConceptoValidacionVO) it.next();
			for (Iterator it2 = this.listaConceptos.iterator(); it2.hasNext();)
			{
				ConceptoVO campo = (ConceptoVO) it2.next();
				if (c.getIdConcepto() == campo.getId())
					return campo.getValor().trim();
			}
		}
		return "";
	}

	/**
	 * lista mp pendiente
	 * 
	 * @param cotizPendVO
	 * @param mapeosMovtos
	 * @param entidadesSIL
	 * @return
	 */
	public List mpPendToView(CotizacionPendienteVO cotizPendVO, List mapeosMovtos, List entidadesSIL, int tipoMovtoSIL)
	{
		List listaMps = new ArrayList();
		if (this.mapeoValores == null)
			this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle());
		Utils setter = new Utils(this.mapNombres, this.mapeoValores);
		String codigo = setter.setString("idMovPersonal");
		String inicio = setter.setString("inicioMovPersonal");
		String termino = setter.setString("terminoMovPersonal");

		if (!codigo.equals("") || !inicio.equals("") || !termino.equals(""))
		{
			MovtoPersonal mp = new MovtoPersonal();
			mp.setIdMovimiento(1);
			mp.setIdTipoMovReal(-1);
			for (Iterator it = mapeosMovtos.iterator(); it.hasNext();)
			{
				MapeoTipoMovtoVO mapeoMovto = (MapeoTipoMovtoVO) it.next();
				if (mapeoMovto.getCodigo().trim().equals(codigo))
				{
					mp.setIdTipoMovReal(mapeoMovto.getId());
					break;
				}
			}
			logger.debug("idTipo:" + mp.getIdTipoMovReal() + ":sil:" + setter.setInt("idEntSil") + "::");
			if (mp.getIdTipoMovReal() == tipoMovtoSIL)
			{
				int idEnt = setter.setInt("idEntSil") / 10;
				for (Iterator it = entidadesSIL.iterator(); it.hasNext();)
				{
					EntidadSilVO ent = (EntidadSilVO) it.next();
					logger.debug("ent:" + ent.getIdEntPagadora() + ":buscado:" + idEnt + "::");
					if (ent.getIdEntPagadora() == idEnt)
					{
						mp.setIdEntidadSil(ent.getId());
						logger.debug("idTipo:" + mp.getIdTipoMovReal() + ":sil:" + setter.setInt("idEntSil") + "::" + mp.getIdEntidadSil() + "::");
						break;
					}
				}
			}
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MM yyyy");
			formatoFecha.setLenient(false); // Debe hacer esto gc.set (GregorianCalendar. ANNO, 2003);
			try
			{
				formatoFecha.parse(inicio, new ParsePosition(0));
				mp.setInicio(formatFecha(inicio));
			} catch (Exception e)
			{
			}
			try
			{
				formatoFecha.parse(termino, new ParsePosition(0));
				mp.setTermino(formatFecha(termino));
			} catch (Exception e)
			{
			}

			listaMps.add(mp);
		}
		for (int i = listaMps.size(); i < 7; i++)
			listaMps.add(new MovtoPersonal((i - 1), -1, "", ""));
		return listaMps;
	}

	public static List cotizanteVOtoView(char tipoProceso, List cotizantes, HashMap nivelErrorTrab, HashMap sucursales)
	{
		List result = new ArrayList();
		for (Iterator it = cotizantes.iterator(); it.hasNext();)
		{
			CotizanteVO cotizante = (CotizanteVO) it.next();
//			clillo 3-12-14 por Reliquidación
			//Cotizante cot = new Cotizante("" + cotizante.getIdCotizante(), cotizante.getIdConvenio(), cotizante.getRutEmpresa(), tipoProceso);
			Cotizante cot = new Cotizante("" + cotizante.getIdCotizante(), cotizante.getIdConvenio(), cotizante.getRutEmpresa(), tipoProceso, cotizante.getPeriodo());
			cot.setRut(Utils.formatRut(cotizante.getIdCotizante()));
			cot.setNombre(cotizante.getNombre().trim());
			cot.setApellidos(cotizante.getApellidoPat().trim() + " " + cotizante.getApellidoMat().trim());
			if ((SucursalVO) sucursales.get(cotizante.getIdSucursal().trim()) != null)
				cot.setSucursal(((SucursalVO) sucursales.get(cotizante.getIdSucursal().trim())).getNombre());
			if ((Integer) nivelErrorTrab.get("" + cotizante.getIdCotizante()) != null)
				cot.setNivelError(((Integer) nivelErrorTrab.get("" + cotizante.getIdCotizante())).intValue());
			result.add(cot);
		}
		Collections.sort(result);
		return result;
	}

	public static List cotizanteAvisoVOtoView(char tipoProceso, List cotizantes, HashMap sucursales)
	{
		List result = new ArrayList();
		for (Iterator it = cotizantes.iterator(); it.hasNext();)
		{
			CotizanteVO cotizante = (CotizanteVO) it.next();
//			clillo 3-12-14 por Reliquidación
			//Cotizante cot = new Cotizante("" + cotizante.getIdCotizante(), cotizante.getIdConvenio(), cotizante.getRutEmpresa(), tipoProceso);
			Cotizante cot = new Cotizante("" + cotizante.getIdCotizante(), cotizante.getIdConvenio(), cotizante.getRutEmpresa(), tipoProceso, cotizante.getPeriodo());
			cot.setRut(Utils.formatRut(cotizante.getIdCotizante()));
			cot.setNombre(cotizante.getNombre().trim());
			cot.setApellidos(cotizante.getApellidoPat().trim() + " " + cotizante.getApellidoMat().trim());
			SucursalVO suc = (SucursalVO) sucursales.get(cotizante.getIdSucursal().trim());
			cot.setSucursal(suc != null ? suc.getNombre() : "");
			cot.setNivelError(Constants.NIVEL_VAL_AVISO);
			result.add(cot);
		}
		Collections.sort(result);
		return result;
	}
}
