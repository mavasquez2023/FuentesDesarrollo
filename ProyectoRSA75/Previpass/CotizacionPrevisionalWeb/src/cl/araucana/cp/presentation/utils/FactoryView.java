package cl.araucana.cp.presentation.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;

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
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSilVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MovtoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoSiguienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ValidacionVO;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizacion;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizante;
import cl.araucana.cp.distribuidor.presentation.beans.Empresa;
import cl.araucana.cp.distribuidor.presentation.beans.MovtoPersonal;
import cl.araucana.cp.presentation.struts.javaBeans.Trabajador;

/*
 * @(#) FactoryView.java 1.29 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * @author aacuna
 * 
 * @version 1.29
 */
public class FactoryView
{
	private static Logger logger = Logger.getLogger(FactoryView.class);
	private List listaConceptos = null;
	private HashMap mapeosConcep = null;
	private Properties mapNombres = null;
	private HashMap mapeoValores = null;
	SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");

	//csanchez Se necesita para determinar si es previred en los casos en que la nómina haya tenido separador por caracter.
	private GrupoConvenioVO grupoConvenioVO = null;
	
	public void setGrupoConvenioVO(GrupoConvenioVO grupoConvenio){
		this.grupoConvenioVO = grupoConvenio;
	}
	
	
	/**
	 * lista conceptos
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
	 * Comportamiento idéntico a cotizanteVOtoView(char, List, HashMap), pero recibe por parámetro la sesión
	 * para poder instanciar la cotización y así determinar si el cotizante es Voluntario.
	 * 
	 * @param tipoProceso
	 * @param cotizantes
	 * @param sucursales
	 * @param sesion
	 * @return
	 */
	public static List cotizanteVOtoView(char tipoProceso, List cotizantes, HashMap sucursales, Session sesion)
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
			cot.setSucursal(((SucursalVO) sucursales.get(cotizante.getIdSucursal().trim())) != null ? ((SucursalVO) sucursales.get(cotizante.getIdSucursal().trim())).getNombre() : "");
			//csanchez
			if(tipoProceso == 'R' && cotizante != null)
			{
				try
				{
					Class[] argTypes = { Integer.class
									   , Integer.class
									   , Integer.class };
					Object[] argValues = { new Integer(cotizante.getRutEmpresa())
										 , new Integer(cotizante.getIdConvenio())
										 , new Integer(cotizante.getIdCotizante()) };

					Class classCotizacion = CotizacionREVO.class;
					
					CotizacionVO cotizacion = (CotizacionVO) sesion.get(classCotizacion, (CotizacionVO) classCotizacion.getConstructor(argTypes).newInstance(argValues));
					
					CotizacionREVO cotiz = (CotizacionREVO) cotizacion;
					
					boolean flgVoluntario = cotiz.isVoluntario();
					
					cot.setFlgVoluntario(flgVoluntario);
					
				} catch (Exception e)
				{
					logger.error("FactoryView.cotizanteVOtoView::" + e.getMessage(), e);
				}
			}
			result.add(cot);
		}
		Collections.sort(result);
		return result;
	}
	
	
	/**
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
			cot.setNombre(cotizante.getNombre().trim());
			cot.setApellidos(cotizante.getApellidoPat().trim() + " " + cotizante.getApellidoMat().trim());
			cot.setSucursal(((SucursalVO) sucursales.get(cotizante.getIdSucursal().trim())) != null ? ((SucursalVO) sucursales.get(cotizante.getIdSucursal().trim())).getNombre() : "");
			result.add(cot);
		}
		Collections.sort(result);
		return result;
	}

	/**
	 * trabs to view all
	 * 
	 * @param result
	 * @param listaEmpresas
	 * @param nominasPagadas
	 * @return
	 */
	public List trabsToViewAll(HashMap result, Collection listaEmpresas, HashMap nominasEditables)
	{
		List resultado = new ArrayList();
		for (Iterator it = result.keySet().iterator(); it.hasNext();)
		{/*
			 * estructura: idCotizante, HashMap(empresas) "cotizante", CotizanteVO idEmpresa, HashMap(convenios) idConvenio, Set(tipoProceso)/List(tipoProceso)
			 */
			String idCotizante = (String) it.next();
			HashMap empresas = (HashMap) result.get(idCotizante);

			CotizanteVO cotiz = (CotizanteVO) empresas.remove("cotizante");
			Trabajador trab = new Trabajador("" + cotiz.getIdCotizante());
			trab.setRut(Utils.formatRut(cotiz.getIdCotizante()));
			trab.setNombre(cotiz.getNombre().trim() + " " + cotiz.getApellidoPat().trim() + " " + cotiz.getApellidoMat().trim());

			for (Iterator it2 = empresas.keySet().iterator(); it2.hasNext();)
			{
				String rutEmpresa = (String) it2.next();
				Empresa empresa = buscaEmpresa(trab, rutEmpresa, listaEmpresas);
				if (empresa != null)
				{
					HashMap convenios = (HashMap) empresas.get(rutEmpresa);
					for (Iterator it3 = convenios.keySet().iterator(); it3.hasNext();)
					{
						String idConvenio = (String) it3.next();
						List data = buscaConvenio(idConvenio, empresa, Constants.NIVEL_VAL_NA);
						Set tps = (Set) convenios.get(idConvenio);
						for (Iterator it4 = tps.iterator(); it4.hasNext();)
						{
							String tp = (String) it4.next();
							String subSubAccion = "trabajadorVer";
							if (nominasEditables.containsKey(tp + "#" + rutEmpresa + "#" + idConvenio))
								subSubAccion = "trabajadorFicha";
							data.add(new LabelValueBean("rutEmpresa=" + rutEmpresa + "&idConvenio=" + idConvenio + "&idCotizante=" + trab.getIdCotizante() + "&tipoProceso=" + tp + "&subSubAccion="
									+ subSubAccion, tp));
						}
					}
				}
			}
			resultado.add(trab);
		}
		sortTrabsToViewAll(resultado);
		return resultado;
	}

	/**
	 * trabs to view all
	 * 
	 * @param result
	 * @param listaEmpresas
	 * @param nominasPagadas
	 * @return
	 */
	/*public List trabsToViewAll(List result, Collection listaEmpresas, HashMap nominasEditables)
	{
		List resultado = new ArrayList();
		//for (Iterator it = result.iterator(); it.hasNext();)
		for (int i=0; i < result.size(); i++)
		{
			//String idCotizante = (String) it.next();
			
			Object[] listaInforme = (Object[]) result.get(i);
			
			EmpresaVO empresaVO = (EmpresaVO) listaInforme[1];
			HashMap empresas = (HashMap) result.get(idCotizante);

			CotizanteVO cotiz = (CotizanteVO) empresas.remove("cotizante");
			Trabajador trab = new Trabajador("" + cotiz.getIdCotizante());
			trab.setRut(Utils.formatRut(cotiz.getIdCotizante()));
			trab.setNombre(cotiz.getNombre().trim() + " " + cotiz.getApellidoPat().trim() + " " + cotiz.getApellidoMat().trim());

			for (Iterator it2 = empresas.keySet().iterator(); it2.hasNext();)
			{
				String rutEmpresa = (String) it2.next();
				Empresa empresa = buscaEmpresa(trab, rutEmpresa, listaEmpresas);
				if (empresa != null)
				{
					HashMap convenios = (HashMap) empresas.get(rutEmpresa);
					for (Iterator it3 = convenios.keySet().iterator(); it3.hasNext();)
					{
						String idConvenio = (String) it3.next();
						List data = buscaConvenio(idConvenio, empresa, Constants.NIVEL_VAL_NA);
						Set tps = (Set) convenios.get(idConvenio);
						for (Iterator it4 = tps.iterator(); it4.hasNext();)
						{
							String tp = (String) it4.next();
							String subSubAccion = "trabajadorVer";
							if (nominasEditables.containsKey(tp + "#" + rutEmpresa + "#" + idConvenio))
								subSubAccion = "trabajadorFicha";
							data.add(new LabelValueBean("rutEmpresa=" + rutEmpresa + "&idConvenio=" + idConvenio + "&idCotizante=" + trab.getIdCotizante() + "&tipoProceso=" + tp + "&subSubAccion="
									+ subSubAccion, tp));
						}
					}
				}
			}
			resultado.add(trab);
		}
		sortTrabsToViewAll(resultado);
		return resultado;
	}*/


	/**
	 * sort trabs to view all
	 * 
	 * @param resultado
	 */
	private void sortTrabsToViewAll(List resultado)
	{
		// ordena las 3 listas
		for (Iterator it = resultado.iterator(); it.hasNext();)
		{
			Trabajador trab = (Trabajador) it.next();
			Collections.sort(trab.getEmpresas());
			for (Iterator it2 = trab.getEmpresas().iterator(); it2.hasNext();)
			{
				Empresa empresa = (Empresa) it2.next();
				Collections.sort(empresa.getConvenios(), new Comparator()
				{
					public int compare(Object o1, Object o2)
					{
						return ((LabelValueBean) ((List) o1).get(0)).getValue().compareTo(((LabelValueBean) ((List) o2).get(0)).getValue());
					}
				});
			}
		}
		Collections.sort(resultado);
	}

	/**
	 * trabs to view all pend
	 * 
	 * @param tiposProceso
	 * @param listaEmpresas
	 * @param hashTrabPend
	 * @param filtros
	 * @param hashConceptos
	 * @param hashMapeo
	 * @param mapConceptos
	 * @return
	 */
	public List trabsToViewAllPend(Collection listaEmpresas, HashMap hashTrabPend, HashMap hashConceptos, HashMap hashMapeo, Properties mapConceptos, HashMap nominasEditables)
	{
		logger.info("trabsToViewAll:");
		List resultado = new ArrayList();
		for (Iterator it = hashTrabPend.keySet().iterator(); it.hasNext();)
		{
			String id = (String) it.next();
			String tmp[] = id.split("#");
			String idEmpresa = tmp[0];
			String idConvenio = tmp[1];
			String tipoProceso = tmp[2];
			if (!hashMapeo.containsKey(id) || !hashConceptos.containsKey(tipoProceso))
				continue;
			this.setListasConceptos((List) hashConceptos.get(tipoProceso), (List) hashMapeo.get(id), mapConceptos);
			List listaPend = (List) hashTrabPend.get(id);
			for (Iterator it2 = listaPend.iterator(); it2.hasNext();)
			{
				CotizacionPendienteVO cotPendiente = (CotizacionPendienteVO) it2.next();
				//TODO SE DEBE OBTENER EL GRUPO DE CONVENIO POR TRABAJADOR
				boolean isPrevired = this.grupoConvenioVO == null ? false : this.grupoConvenioVO.isPrevired();
				this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotPendiente.getDetalle(), isPrevired, this.grupoConvenioVO.isProdCaja());
				//this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotPendiente.getDetalle());
				Utils setter = new Utils(this.mapNombres, this.mapeoValores);
				String idCotizante = setter.setString("idCotizante");
			//	if (filtroRut != null && ("" + idCotizante).indexOf(filtroRut) == -1) // filtro por rut
				//	continue;
				String nombre = setter.setString("nombre");

			//	if (filtroNombre != null && nombre.indexOf(filtroNombre) == -1) // filtro por nombre
				//	continue;
				String apellidos = setter.setString("apellido");
				//if (filtroApellido != null && apellidos.indexOf(filtroApellido) == -1) // filtro por apellido
					//continue;
				Trabajador trab = buscaTrabajador(resultado, idCotizante, nombre, apellidos);

				Empresa empresa = buscaEmpresa(idEmpresa, trab, listaEmpresas);
				if (empresa != null)
				{
					List data = buscaConvenio(idConvenio, empresa, Constants.NIVEL_VAL_ERROR);
							//((Integer) nivelErrNominas.get(empresa.getIdEmpresa() + "#" + idConvenio + "#" + cotPendiente.getIdCotizPendiente())).intValue());
					boolean encontrado = false;
					for (Iterator it4 = data.iterator(); it4.hasNext();)
						if (((LabelValueBean) it4.next()).getValue().equals(tipoProceso))
						{
							encontrado = true;
							break;
						}
					if (!encontrado)
					{
						String subSubAccion = "trabajadorVer";
						if (nominasEditables.containsKey(tipoProceso + "#" + idEmpresa + "#" + idConvenio))
							subSubAccion = "trabajadorFicha";
						data.add(new LabelValueBean("rutEmpresa=" + idEmpresa + "&idConvenio=" + idConvenio + "&idCotizPend=" + cotPendiente.getIdCotizPendiente() + "&tipoProceso=" + tipoProceso
								+ "&subSubAccion=" + subSubAccion, tipoProceso));
					}
				}
			}
		}
		sortTrabsToViewAll(resultado);
		return resultado;
	}

	/**
	 * buscar trabajador
	 * 
	 * @param resultado
	 * @param idCotizante
	 * @param nombre
	 * @param apellidos
	 * @return
	 */
	private Trabajador buscaTrabajador(List resultado, String idCotizante, String nombre, String apellidos)
	{
		Trabajador trab = null;
		for (Iterator itTrab = resultado.iterator(); itTrab.hasNext();)
		{
			trab = (Trabajador) itTrab.next();
			if (idCotizante.equals(trab.getIdCotizante()))
				break;
			trab = null;
		}
		if (trab == null)// trabajador aun no agregado
		{
			trab = new Trabajador(idCotizante);
			trab.setRut(trab.getIdCotizante());
			trab.setNombre(nombre + " " + apellidos);
			if (trab.getNombre().length() > 70)
				trab.setNombre(trab.getNombre().substring(0, 70));

			resultado.add(trab);
		}
		return trab;
	}

	/**
	 * buscar empresa
	 * 
	 * @param idEmpresa
	 * @param trab
	 * @param listaEmpresas
	 * @return
	 */
	private Empresa buscaEmpresa(String idEmpresa, Trabajador trab, Collection listaEmpresas)
	{
		Empresa empresa = null;
		for (Iterator itEmp = trab.getEmpresas().iterator(); itEmp.hasNext();)
		{
			empresa = (Empresa) itEmp.next();
			if (idEmpresa.equals("" + empresa.getIdEmpresa()))
				break;
			empresa = null;
		}
		if (empresa == null)// empresa aun no agregada
		{
			EmpresaVO empVO = this.getEmpresa(new Integer(idEmpresa).intValue(), listaEmpresas);
			if (empVO != null)
			{
				empresa = new Empresa(empVO.getIdEmpresa());
				empresa.setRut(Utils.formatRut(empVO.getIdEmpresa()));
				empresa.setRazonSocial(empVO.getRazonSocial());
				trab.getEmpresas().add(empresa);
			}
		}
		return empresa;
	}

	/**
	 * buscar empresa
	 * 
	 * @param trab
	 * @param idEmpresa
	 * @param listaEmpresas
	 * @return
	 */
	private Empresa buscaEmpresa(Trabajador trab, String idEmpresa, Collection listaEmpresas)
	{
		Empresa empresa = null;
		for (Iterator itEmp = trab.getEmpresas().iterator(); itEmp.hasNext();)
		{
			empresa = (Empresa) itEmp.next();
			if (idEmpresa.equals("" + empresa.getIdEmpresa()))
				break;
			empresa = null;
		}
		if (empresa == null)// empresa aun no agregada
		{
			EmpresaVO empVO = this.getEmpresa(new Integer(idEmpresa).intValue(), listaEmpresas);
			if (empVO != null)// nunca deberia suceder
			{
				empresa = new Empresa(empVO.getIdEmpresa(), Utils.formatRut(empVO.getIdEmpresa()), empVO.getRazonSocial());
				trab.getEmpresas().add(empresa);
			}
		}
		return empresa;
	}

	/**
	 * busca convenio
	 * 
	 * @param idConvenio
	 * @param empresa
	 * @return
	 */
	private List buscaConvenio(String idConvenio, Empresa empresa, int idNivelError)
	{
		for (Iterator it = empresa.getConvenios().iterator(); it.hasNext();)
		{
			List convenio = (List) it.next();
			LabelValueBean label = (LabelValueBean) convenio.get(0);
			if (idConvenio.equals(label.getValue()))
				return convenio;
		}
		// convenio no existe, si no existe => agregar
		List data = new ArrayList();
		data.add(new LabelValueBean("", idConvenio));
		if (idNivelError != Constants.NIVEL_VAL_NA)
			data.add(1, new LabelValueBean("flagEA", "" + idNivelError));
		empresa.getConvenios().add(data);
		return data;
	}

	/**
	 * empresa
	 * 
	 * @param idEmpresa
	 * @param listaEmpresas
	 * @return
	 */
	public EmpresaVO getEmpresa(int idEmpresa, Collection listaEmpresas)
	{
		for (Iterator it = listaEmpresas.iterator(); it.hasNext();)
		{
			EmpresaVO empresa = (EmpresaVO) it.next();
			if (idEmpresa == empresa.getIdEmpresa())
				return empresa;
		}
		return null;
	}

	/**
	 * valor concepto
	 * 
	 * @param parametros
	 * @param listaConceptos
	 * @return
	 */
	public String getValorConcepto(List parametros, List listaConcep)
	{
		for (Iterator it = parametros.iterator(); it.hasNext();)
		{
			ConceptoValidacionVO c = (ConceptoValidacionVO) it.next();
			for (Iterator it2 = listaConcep.iterator(); it2.hasNext();)
			{
				ConceptoVO campo = (ConceptoVO) it2.next();
				if (c.getIdConcepto() == campo.getId())
					return campo.getValor().trim();
			}
		}
		return "";
	}

	/**
	 * siguiente validacion
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
			//TODO SE NECESITA EL IS_PREVIRED (GRUPO DE CONVENIO) CUANDO ES SEPARACION POR CARACTER
			boolean isPrevired = this.grupoConvenioVO == null ? false : this.grupoConvenioVO.isPrevired();
			this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle(), isPrevired, this.grupoConvenioVO.isProdCaja());
			//this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle());
		}
		ValidacionVO siguiente = (ValidacionVO) listValidaApvs.get(0);
		while (siguiente != null)
		{
			ApvVO apv = new ApvVO(Constants.APV_INVALIDO);
			try
			{
				String entidad = getValorConcepto(siguiente.getConceptos(), this.listaConceptos);
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
				apv.setMontoFormat(getValorConcepto(siguiente.getConceptos(), this.listaConceptos));
			listaApvs.add(apv);
			if (siguiente != null)
				siguiente = getSiguienteValidacion(siguiente.getSiguientes(), listValidaApvs);
		}
		return listaApvs;
	}

	/**
	 * mp pend to view
	 * 
	 * @param cotizPendVO
	 * @param listValidaMovtos
	 * @param mapeosMovtos
	 * @param entidadesSIL
	 * @return
	 */
	public List mpPendToView(CotizacionPendienteVO cotizPendVO, List mapeosMovtos, List entidadesSIL, CotizanteVO cotizanteGuardado, boolean voluntario)
	{
		List listaMps = new ArrayList();
		int pos = -1;
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MM yyyy");
		formatoFecha.setLenient(false); // Debe hacer esto gc.set (GregorianCalendar. ANNO, 2003);
		if (this.mapeoValores == null) {
			//TODO SE NECESITA EL IS_PREVIRED (GRUPO DE CONVENIO) CUANDO ES SEPARACION POR CARACTER
			boolean isPrevired = this.grupoConvenioVO == null ? false : this.grupoConvenioVO.isPrevired();
			this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle(), isPrevired, this.grupoConvenioVO.isProdCaja());
			//this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle());
		}
		Utils setter = new Utils(this.mapNombres, this.mapeoValores);
		// setter.showValores();
		// setter.showNombres();
		String codigo = setter.setString("idMovPersonal");
		String inicio = setter.setString("inicioMovPersonal");
		String termino = setter.setString("terminoMovPersonal");
		int idEnt = setter.setInt("idEntSil") / 10;

		if (!codigo.equals("") || !inicio.equals("") || !termino.equals(""))
		{
			MovtoPersonal mp = new MovtoPersonal();
			mp.setIdMovimiento(pos++);
			mp.setIdTipoMovReal(-1);
			if (!voluntario)
			{
				for (Iterator it = mapeosMovtos.iterator(); it.hasNext();)
				{
					MapeoTipoMovtoVO mapeoMovto = (MapeoTipoMovtoVO) it.next();
					if (mapeoMovto.getCodigo().trim().equals(codigo))
					{
						mp.setIdTipoMovReal(mapeoMovto.getId());
						break;
					}
				}
			} else
			{
				for (Iterator it = mapeosMovtos.iterator(); it.hasNext();)
				{
					MapeoTipoMovtoAFVO mapeoMovto = (MapeoTipoMovtoAFVO) it.next();
					if (mapeoMovto.getCodigo().trim().equals(codigo))
					{
						mp.setIdTipoMovReal(mapeoMovto.getId());
						break;
					}
				}
			}
			for (Iterator it = entidadesSIL.iterator(); it.hasNext();)
			{
				EntidadSilVO ent = (EntidadSilVO) it.next();
				logger.info("ent:" + ent.getIdEntPagadora() + ":buscado:" + idEnt + "::");
				if (ent.getIdEntPagadora() == idEnt)
				{
					mp.setIdEntidadSil(ent.getId());
					logger.info("idTipo:" + mp.getIdTipoMovReal() + ":sil:" + setter.setInt("idEntSil") + "::" + mp.getIdEntidadSil() + "::");
					break;
				}
			}
			try
			{
				inicio = inicio.replace(' ', '-');
				formatoFecha.parse(inicio, new ParsePosition(0));
				mp.setInicio(formatFecha(inicio));
			} catch (Exception e)
			{
			}
			try
			{
				termino = termino.replace(' ', '-');
				formatoFecha.parse(termino, new ParsePosition(0));
				mp.setTermino(formatFecha(termino));
			} catch (Exception e)
			{
			}

			listaMps.add(mp);
		}
		if (cotizanteGuardado != null) // sumar movtos
		{
			formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
			CotizacionREVO cotGuardada = (CotizacionREVO) cotizanteGuardado.getCotizacion();
			if (cotGuardada.getMovimientoPersonal() != null)
			{
				if (!voluntario)
				{
					for (Iterator it = cotGuardada.getMovimientoPersonal().iterator(); it.hasNext();)
					{
						MovtoPersonalVO mpVO = (MovtoPersonalVO) it.next();

						MovtoPersonal mp = new MovtoPersonal();
						mp.setIdMovimiento(pos++);
						mp.setIdTipoMovReal(mpVO.getIdTipoMovReal());
						mp.setIdEntidadSil(cotizanteGuardado.getIdEntSil());
						//TODO GMALLEA 11-05-2012 Validamos si es null porque cundo se caraga la nomina lo deja asi
						if(mpVO.getInicio() != null)
							mp.setInicio(formatoFecha.format(mpVO.getInicio()));
						if(mpVO.getTermino() != null)
							mp.setTermino(formatoFecha.format(mpVO.getTermino()));
						listaMps.add(mp);
					}
				} else
				{
					for (Iterator it = cotGuardada.getMovimientoPersonal().iterator(); it.hasNext();)
					{
						MvtoPersoAFVO mpVO = (MvtoPersoAFVO) it.next();

						MovtoPersonal mp = new MovtoPersonal();
						mp.setIdMovimiento(pos++);
						mp.setIdTipoMovReal(mpVO.getIdTipoMovReal());
						mp.setIdEntidadSil(cotizanteGuardado.getIdEntSil());
						//TODO GMALLEA 11-05-2012 Validamos si es null porque cundo se caraga la nomina lo deja asi
						if(mpVO.getInicio() != null)
							mp.setInicio(formatoFecha.format(mpVO.getInicio()));
						if(mpVO.getTermino() != null)
							mp.setTermino(formatoFecha.format(mpVO.getTermino()));
						listaMps.add(mp);
					}
				}
			}
		}
		int largo = Constants.NUM_MAX_MOVTO;
		if (voluntario)
			largo = Constants.NUM_MAX_MOVTOAF;
		for (int i = listaMps.size(); i < largo; i++)
			listaMps.add(new MovtoPersonal((i - 1), -1, "", ""));
		return listaMps;
	}
	
	
	/**
	 * pendientes to view
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
	 * cotizante vo to view
	 * 
	 * @param tipoProceso
	 * @param format
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
		//TODO valores AFC / INP
		if (cotizante.getIdEntPensionReal() != Constants.ID_INP && cotizante.getIdEntPensionReal() != Constants.ID_AFP_NINGUNA){
			cot.setIdEntPensionReal(cotizante.getIdEntPensionReal());
			cot.setIdEntAFCReal(cotizante.getIdEntPensionReal());
		}
		else{
			cot.setIdEntPensionReal(cotizante.getIdEntPensionReal());
			cot.setIdEntAFCReal(cotizante.getIdEntAfcReal());
		}
		
		/*if (cotizante.getIdEntSaludReal() == Constants.ID_FONASA){
			cotizante.setIdEntSaludReal(Constants.ID_SALUD_NINGUNA);
		}*/
			
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
	 * cotizante pend vo to view
	 * 
	 * @param tipoProceso
	 * @param cotizPendVO
	 * @param parser
	 * @return
	 */
	public Cotizante cotizPendVOtoView(char tipoProceso, CotizacionPendienteVO cotizPendVO, int nivelError, NamesParser parser)
	{
		//TODO SE NECESITA EL IS_PREVIRED (GRUPO DE CONVENIO) CUANDO ES SEPARACION POR CARACTER
		boolean isPrevired = this.grupoConvenioVO == null ? false : this.grupoConvenioVO.isPrevired();
		//this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle());
		this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle(), isPrevired, this.grupoConvenioVO.isProdCaja());
		Utils setter = new Utils(this.mapNombres, this.mapeoValores);
		Cotizante cot = new Cotizante();
		
		//16-05-2012 GMALLEA Se limpia el rut del cotizante para que quede sin ceros
		String rutCotizanteFull = setter.setString("idCotizante").trim();
		//clillo 8-5-13 Se elimina quion si viene en el rut  
		rutCotizanteFull= rutCotizanteFull.replaceAll("-", "");
		rutCotizanteFull= rutCotizanteFull.replaceAll(" ", "");
		while(rutCotizanteFull.startsWith("0")){
			rutCotizanteFull= rutCotizanteFull.substring(1);
		}
		
		//String rutCotizante =rutCotizanteFull.substring(0, rutCotizanteFull.length()-1);
		//String dvCotizante = rutCotizanteFull.substring(rutCotizanteFull.length()-1, rutCotizanteFull.length());
		
		//int rutCotizanteNuemric = Integer.parseInt(rutCotizante.trim());
		
		//String  rutCotizanteStringFull = String.valueOf(rutCotizanteNuemric) +" "+ dvCotizante.trim() ; 
	
		
		cot.setIdCotizPendiente(cotizPendVO.getIdCotizPendiente());
		cot.setIdCotizante(rutCotizanteFull.substring(0, rutCotizanteFull.length()-1));
		cot.setIdConvenio(cotizPendVO.getIdConvenio());
		String fechareli= setter.setString("inicioReli");
		if(fechareli!=null && fechareli.length()==10){
			cot.setPeriodo(Integer.parseInt(fechareli.substring(6, 10) + fechareli.substring(3, 5)));
		}
		if(setter.setString("rutDependiente") != null && !setter.setString("rutDependiente").equals("")){
		
			
			String rutDependienteFull = setter.setString("rutDependiente").trim();

			String rutDependiente =rutDependienteFull.substring(0, rutDependienteFull.length()-1);
			String dvDependiente = rutDependienteFull.substring(rutDependienteFull.length()-1, rutDependienteFull.length());
			
			StringTokenizer stTexto = new StringTokenizer(rutDependiente);
			String newCuerpoRut =  new String();
			while (stTexto.hasMoreElements())
				newCuerpoRut += stTexto.nextElement();
			
			int rutDependienteNuemric = Integer.parseInt(newCuerpoRut.trim());
			
			
			String rutDependienteStringFull = String.valueOf(rutDependienteNuemric) + dvDependiente.trim() ; 
			
			
			if(rutDependienteStringFull.length() > 2){
				cot.setAfpVoluntario(true);
				cot.setFlgVoluntario(true);
			}
		}
		
		cot.setRutEmpresa(new Integer(cotizPendVO.getRutEmpresa()).intValue());
		cot.setTipoProceso(tipoProceso);
		cot.setRut(rutCotizanteFull);
		cot.setNombre(setter.setString("nombre"));
		cot.setNivelError(nivelError);

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
		if (tipoProceso == 'D'){
			//TODO
			cot.setIdEntidadAPVC(setter.setString("idEntPension"));
		}
		else{
			cot.setIdEntidadAPVC(setter.setString("idEntidadAPVC"));
		}
		cot.setIdEntAFC(setter.setString("idEntAfc"));
		cot.setIdGenero(setter.setString("idGenero"));
		
		
		
//		cot.set
//		rutDependiente
		
		
		return cot;
	}

	/**
	 * cotizante pend vo to view
	 * 
	 * @param tipoProceso
	 * @param cotizPendVO
	 * @param parser
	 * @return
	 */
	public Cotizante cotizPendVOtoView(char tipoProceso, CotizacionPendienteVO cotizPendVO, NamesParser parser)
	{
		//TODO SE NECESITA EL IS_PREVIRED (GRUPO DE CONVENIO) CUANDO ES SEPARACION POR CARACTER
		boolean isPrevired = this.grupoConvenioVO == null ? false : this.grupoConvenioVO.isPrevired();
		//this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle());
		this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle(), isPrevired, this.grupoConvenioVO.isProdCaja());
		Utils setter = new Utils(this.mapNombres, this.mapeoValores);
		Cotizante cot = new Cotizante();
		
		//16-05-2012 GMALLEA Se limpia el rut del cotizante para que quede sin ceros
			String rutCotizanteFull = setter.setString("idCotizante").trim();
			//clillo 8-5-13 Se elimina quion si viene en el rut  
			rutCotizanteFull= rutCotizanteFull.replaceAll("-", "");
			rutCotizanteFull= rutCotizanteFull.replaceAll(" ", "");
			while(rutCotizanteFull.startsWith("0")){
				rutCotizanteFull= rutCotizanteFull.substring(1);
			}
			//clillo 8-5-13 se quita espacio al rut ya que provoca error al cambiar Excaja
			
			//String rutCotizante =rutCotizanteFull.substring(0, rutCotizanteFull.length()-1);
			//String dvCotizante = rutCotizanteFull.substring(rutCotizanteFull.length()-1, rutCotizanteFull.length());
			
			//int rutCotizanteNuemric = Integer.parseInt(rutCotizante.trim());
			
			//String rutCotizanteStringFull  = String.valueOf(rutCotizanteNuemric) +" "+ dvCotizante.trim() ;

		//FIN GMALLEA
			
		cot.setIdCotizPendiente(cotizPendVO.getIdCotizPendiente());
		cot.setIdCotizante(rutCotizanteFull);
		cot.setIdConvenio(cotizPendVO.getIdConvenio());
		cot.setRutEmpresa(new Integer(cotizPendVO.getRutEmpresa()).intValue());
		cot.setTipoProceso(tipoProceso);
		cot.setRut(rutCotizanteFull);
		cot.setNombre(setter.setString("nombre"));
		//clillo 10/12/14 por Reliquidación
		String fechareli= setter.setString("inicioReli");
		if(fechareli != null && fechareli.length()==10){
			cot.setPeriodo(Integer.parseInt(fechareli.substring(6, 10) + fechareli.substring(3, 5)));
		}
		
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
		if (tipoProceso == 'D') {
			cot.setIdEntidadAPVC(setter.setString("idEntPension"));
			cot.setIdEntDep(setter.setString("idEntDep"));
		} else
			cot.setIdEntidadAPVC(setter.setString("idEntidadAPVC"));
		cot.setIdEntAFC(setter.setString("idEntAfc"));
		cot.setIdGenero(setter.setString("idGenero"));
		
		int previsionAdicional=0;
		try{
		 previsionAdicional = Integer.parseInt(setter.setString("previsionAdicional").trim());
		}catch (Exception e) {
			previsionAdicional=1;
		}
		List listAPV = new ArrayList();
		if(previsionAdicional !=0 || !setter.setString("regimenAPVVol").trim().equals("")){
			ApvVO apvVOVol = new ApvVO();
			apvVOVol.setMontoFormat(setter.setString("previsionAdicional").trim());
			apvVOVol.setIdEntidadApvEmp(setter.setString("idEntPension").trim().equals("") ? "0" : setter.setString("idEntPension").trim());
			apvVOVol.setRegimen(setter.setString("regimenAPVVol").trim().equals("") ? ' ' : setter.setString("regimenAPVVol").trim().charAt(0));
			listAPV.add(apvVOVol);
			
			ApvVO apvVO1 = new ApvVO();
			apvVO1.setMontoFormat(setter.setString("ahorroAPV1").trim());
			apvVO1.setIdEntidadApvEmp(setter.setString("idEntApv1").trim().equals("") ? "0" : setter.setString("idEntApv1").trim());
			apvVO1.setRegimen(setter.setString("regimenAPV1").trim().equals("") ? ' ' : setter.setString("regimenAPV1").trim().charAt(0));
			listAPV.add(apvVO1);
			
			ApvVO apvVO2 = new ApvVO();
			apvVO2.setMontoFormat(setter.setString("ahorroAPV2").trim());
			apvVO2.setIdEntidadApvEmp(setter.setString("idEntApv2").trim().equals("") ? "0" : setter.setString("idEntApv2").trim() );
			apvVO2.setRegimen(setter.setString("regimenAPV2").trim().equals("") ? ' ' : setter.setString("regimenAPV2").trim().charAt(0));
			listAPV.add(apvVO2);
			
			ApvVO apvVO3 = new ApvVO();
			apvVO3.setMontoFormat(setter.setString("ahorroAPV3").trim());
			apvVO3.setIdEntidadApvEmp(setter.setString("idEntApv3").trim().equals("") ? "0" : setter.setString("idEntApv3").trim() );
			apvVO3.setRegimen(setter.setString("regimenAPV3").trim().equals("") ? ' ' : setter.setString("regimenAPV3").trim().charAt(0));
			listAPV.add(apvVO3);
			
			ApvVO apvVO4 = new ApvVO();
			apvVO4.setMontoFormat(setter.setString("ahorroAPV4").trim());
			apvVO4.setIdEntidadApvEmp(setter.setString("idEntApv4").trim().equals("") ? "0" : setter.setString("idEntApv4").trim() );
			apvVO4.setRegimen(setter.setString("regimenAPV4").trim().equals("") ? ' ' : setter.setString("regimenAPV4").trim().charAt(0));
			listAPV.add(apvVO4);
		}else{
		
			ApvVO apvVO1 = new ApvVO();
			apvVO1.setMontoFormat(setter.setString("ahorroAPV1").trim());
			apvVO1.setIdEntidadApvEmp(setter.setString("idEntApv1").trim().equals("") ? "0" : setter.setString("idEntApv1").trim() );
			apvVO1.setRegimen(setter.setString("regimenAPV1").trim().equals("") ? ' ' : setter.setString("regimenAPV1").trim().charAt(0));
			listAPV.add(apvVO1);
			
			ApvVO apvVO2 = new ApvVO();
			apvVO2.setMontoFormat(setter.setString("ahorroAPV2").trim());
			apvVO2.setIdEntidadApvEmp(setter.setString("idEntApv2").trim().equals("") ? "0" : setter.setString("idEntApv2").trim() );
			apvVO2.setRegimen(setter.setString("regimenAPV2").trim().equals("") ? ' ' : setter.setString("regimenAPV2").trim().charAt(0));
			listAPV.add(apvVO2);
			
			ApvVO apvVO3 = new ApvVO();
			apvVO3.setMontoFormat(setter.setString("ahorroAPV3").trim());
			apvVO3.setIdEntidadApvEmp(setter.setString("idEntApv3").trim().equals("") ? "0" : setter.setString("idEntApv3").trim() );
			apvVO3.setRegimen(setter.setString("regimenAPV3").trim().equals("") ? ' ' : setter.setString("regimenAPV3").trim().charAt(0));
			listAPV.add(apvVO3);
			
			ApvVO apvVO4 = new ApvVO();
			apvVO4.setMontoFormat(setter.setString("ahorroAPV4").trim());
			apvVO4.setIdEntidadApvEmp(setter.setString("idEntApv4").trim().equals("") ? "0" : setter.setString("idEntApv4").trim() );
			apvVO4.setRegimen(setter.setString("regimenAPV4").trim().equals("") ? ' ' : setter.setString("regimenAPV4").trim().charAt(0));
			listAPV.add(apvVO4);
			
			ApvVO apvVO5 = new ApvVO();
			apvVO5.setMontoFormat(setter.setString("ahorroAPV5").trim());
			apvVO5.setIdEntidadApvEmp(setter.setString("idEntApv5").trim().equals("") ? "0" : setter.setString("idEntApv5").trim() );
			apvVO5.setRegimen(setter.setString("regimenAPV5").trim().equals("") ? ' ' : setter.setString("regimenAPV5").trim().charAt(0));
			listAPV.add(apvVO5);
		}
		cot.setApvs(listAPV);
		
		return cot;
	}

	/**
	 * cotizante pend vo to view
	 * 
	 * @param tipoProceso
	 * @param cotizante
	 * @param cotizPendVO
	 * @param caja
	 * @param mutual
	 * @param parser
	 * @return
	 */
	public Cotizacion cotizPendVOtoView(char tipoProceso, Cotizante cotizante, CotizacionPendienteVO cotizPendVO, boolean isCCAF, boolean isMUTUAL, NamesParser parser, CotizanteVO cotizanteGuardado)
	{
		Cotizacion cot = new Cotizacion();

		if (this.mapeoValores == null) {
			//TODO SE NECESITA EL IS_PREVIRED (GRUPO DE CONVENIO) CUANDO ES SEPARACION POR CARACTER
			boolean isPrevired = this.grupoConvenioVO == null ? false : this.grupoConvenioVO.isPrevired();
			this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle(), isPrevired, this.grupoConvenioVO.isProdCaja());
			//this.mapeoValores = cl.araucana.cp.distribuidor.base.Utils.parseoValores(this.listaConceptos, this.mapeosConcep, cotizPendVO.getDetalle());
		}
		Utils setter = new Utils(this.mapNombres, this.mapeoValores);
		cot.cargaConceptos(tipoProceso, setter);

		if (tipoProceso == 'R')
		{
			boolean isFONASA = (cotizante.getIdEntSaludReal() == Constants.ID_FONASA ? true : false);
			boolean isINP = (cotizante.getIdEntPensionReal() == Constants.ID_INP ? true : false);
			// reforma
			
			if (!setter.setString("rutDependiente").equals("") || !setter.setString("nombreDependiente").equals("") || !setter.setString("apellidoDep").equals("")){
			
				//16-05-2012 GMALLEA Se limpia el rut de dependiente para que quede sin ceros
					
						String rutDependienteFull = setter.setString("rutDependiente").trim();
		
						String rutDependiente =rutDependienteFull.substring(0, rutDependienteFull.length()-1);
						String dvDependiente = rutDependienteFull.substring(rutDependienteFull.length()-1, rutDependienteFull.length());
						
						StringTokenizer stTexto = new StringTokenizer(rutDependiente);
						String newCuerpoRut =  new String();
						while (stTexto.hasMoreElements())
							newCuerpoRut += stTexto.nextElement();

						int rutDependienteNuemric = Integer.parseInt(newCuerpoRut.trim());
						
						String rutDependienteStringFull = String.valueOf(rutDependienteNuemric) + dvDependiente.trim() ; 
				
						if(rutDependienteStringFull.length() > 2)
							cotizante.setAfpVoluntario(true);
					}
				//FIN GMALLEA

			if (isFONASA)
			{
				cot.setSaludObligISAPRE("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				//cot.setSaludObligFONASA(setter.setString("saludObligatorio"));
				cot.setSaludObligFONASA("0");
				cot.setSaludTotal("" + 0);
			} else
			{
				cot.setSaludObligISAPRE(setter.setString("saludObligatorio"));
				cot.setSaludObligFONASA("" + 0);
				cot.setSaludTotal(setter.setString("saludTotal"));
			}
			cot.setSaludPactado(setter.setString("saludPactado"));
			cot.setSaludAdicional(setter.setString("saludAdicional"));
			if (isINP)
			{
				cot.setPrevObligatorioAFP("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				//cot.setPrevObligatorioINP(setter.setString("previsionObligatorio"));
				cot.setPrevObligatorioINP("0");
//				clillo 17-05-16 No hay pagos al IPS
				cot.setInpDesahucio("0");
				cot.setInpBonificacion("0");
				
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
			if (isMUTUAL)// hay mutual asociada a la empresa
			{
				cot.setAccTrabajoMutual(setter.setString("inpMutual"));
				cot.setAccTrabajoINP("" + 0);
			} else
			{
				cot.setAccTrabajoMutual("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				//cot.setAccTrabajoINP(setter.setString("inpMutual"));
				cot.setAccTrabajoINP("0");
			}
			if (isCCAF)
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
//				clillo 17-05-16 No hay pagos al IPS
				//cot.setAsigFamiliarINP(setter.setString("asigFamiliar"));
				cot.setAsigFamiliarINP("0");
				cot.setAsigFamiliar("0");
				cot.setAsigFamRetro("0");
				cot.setAsigFamReint("0");
				cot.setTotalAsigFam("0");
				cotizante.setNumCargaSimple("0");
				cotizante.setNumCargaMaterna("0");
				cotizante.setNumCargaInvalidez("0");
			}

			// reforma
			cot.setApvcAporteEmpl(setter.setString("apvcAporteEmpl"));
			cot.setApvcAporteTrab(setter.setString("apvcAporteTrab"));
			cot.setApvcNumContr(setter.setString("apvcNumContrato").trim());
			cot.setAfpvRutDpndiente(setter.setString("rutDependiente"));
			cot.setAfpvNombreDpndiente(setter.setString("nombreDependiente").trim());
			
			//clillo 23/01/15 AFBR
			cot.setOtrosAFBR(setter.setString("otrosAFBR"));
			
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
			if (cotizanteGuardado != null)// sumar
			{
				cot.sumaMontos((CotizacionREVO) cotizanteGuardado.getCotizacion(), isFONASA, isINP, isMUTUAL, isCCAF);
				cotizante.sumaDias(cotizanteGuardado);
			}
			cot.calculaTotalINP();

			//csanchez Por Depósito Convenido
			if (cot.getIndemInicio() != null)
				cot.setIndemInicio(cot.getIndemInicio().replaceAll("_", "-").replaceAll(" ", "-"));
			if (cot.getIndemTermino() != null)
				cot.setIndemTermino(cot.getIndemTermino().replaceAll("_", "-").replaceAll(" ", "-"));
		
		} else if (tipoProceso == 'G')
		{
			cot.setInicio(formatFecha((setter.setString("inicio")).replace(' ', '-')));
			cot.setTermino(formatFecha((setter.setString("termino")).replace(' ', '-')));

			if (cotizante.getIdEntSaludReal() == Constants.ID_FONASA)
			{
				cot.setSaludObligISAPRE("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				//cot.setSaludObligFONASA(setter.setString("saludObligatorio"));
				cot.setSaludObligFONASA("0");
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
//				clillo 17-05-16 No hay pagos al IPS
				//cot.setPrevObligatorioINP(setter.setString("previsionObligatorio"));
				cot.setPrevObligatorioINP("0");
//				clillo 17-05-16 No hay pagos al IPS
				cot.setInpDesahucio("0");
				cot.setInpBonificacion("0");
				
				cotizante.setIdEntPensionReal(cotizante.getIdEntAFCReal());
			} else
			{
				cot.setPrevObligatorioAFP(setter.setString("previsionObligatorio"));
				cot.setPrevObligatorioINP("" + 0);
			}
			cot.setPrevisionTotal(setter.setString("previsionTotal"));
			if (isMUTUAL)// hay mutual asociada a la empresa
			{
				cot.setAccTrabajoMutual(setter.setString("inpMutual"));
				cot.setAccTrabajoINP("" + 0);
			} else
			{
				cot.setAccTrabajoMutual("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				//cot.setAccTrabajoINP(setter.setString("inpMutual"));
				cot.setAccTrabajoINP("0");
			}
			if(cotizante.getIdEntExCaja() == Constants.ID_EXCAJA){
				cot.setRemImpPension("" + 0);
			}
		} else if (tipoProceso == 'A')
		{
			cot.setInicio(formatFecha((setter.setString("inicioReli")).replace(' ', '-')));
			cot.setTermino(formatFecha((setter.setString("terminoReli")).replace(' ', '-')));
			//clillo 10/12/14 por Reliquidación
			String fechareli= setter.setString("inicioReli");
			if(fechareli != null && fechareli.length()==10){
				cot.setPeriodo(Integer.parseInt(fechareli.substring(6, 10) + fechareli.substring(3, 5)));
			}
			if (cotizante.getIdEntSaludReal() == Constants.ID_FONASA)
			{
				cot.setSaludObligISAPRE("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				//cot.setSaludObligFONASA(setter.setString("saludObligatorio"));
				cot.setSaludObligFONASA("0");
			} else
			{
				cot.setSaludObligISAPRE(setter.setString("saludObligatorio"));
				cot.setSaludObligFONASA("" + 0);
			}
			if (cotizante.getIdEntPensionReal() == Constants.ID_INP)
			{
				cot.setPrevObligatorioAFP("" + 0);
				cot.setPrevisionTotal("0");
//				clillo 17-05-16 No hay pagos al IPS
				//cot.setPrevObligatorioINP(setter.setString("previsionObligatorio"));
				cot.setPrevObligatorioINP("0");
//				clillo 17-05-16 No hay pagos al IPS
				cot.setInpDesahucio("0");
				cot.setInpBonificacion("0");
				
				cotizante.setIdEntPensionReal(cotizante.getIdEntAFCReal());
			} else
			{
				cot.setPrevObligatorioAFP(setter.setString("previsionObligatorio"));
				cot.setPrevObligatorioINP("" + 0);
				cot.setPrevisionTotal("0");
			}
			if (isMUTUAL)// hay mutual asociada a la empresa
			{
				cot.setAccTrabajoMutual(setter.setString("inpMutual"));
				cot.setAccTrabajoINP("" + 0);
			} else
			{
				cot.setAccTrabajoMutual("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				//cot.setAccTrabajoINP(setter.setString("inpMutual"));
				cot.setAccTrabajoINP("0");
			}
//			clillo 23/01/15 AFBR
			cot.setOtrosAFBR(setter.setString("otrosAFBR"));
			
		} else if (tipoProceso == 'D')
		{
			if (cot.getIndemInicio() != null)
				cot.setIndemInicio(cot.getIndemInicio().replaceAll("_", "-").replaceAll(" ", "-"));
			if (cot.getIndemTermino() != null)
				cot.setIndemTermino(cot.getIndemTermino().replaceAll("_", "-").replaceAll(" ", "-"));
			cot.setIdEntDep(cotizante.getIdEntDep());
		}
		return cot;
	}

	/**
	 * formatea fecha
	 * 
	 * @param fecha
	 * @return
	 */
	public String formatFecha(String fecha)
	{
		java.util.Date init = new java.util.Date(1);
		if (!fecha.equals(""))
		{
			try
			{
				java.util.Date date = this.dateFormat.parse(fecha.replaceAll("_", "-").replaceAll("/", "-"));
				if (date.equals(init))
					return "";
				return fecha;
			} catch (Exception e)
			{}
		}
		return "";
	}

	public static Date desformatFecha(String fecha)
	{
		try
		{
			SimpleDateFormat df = new java.text.SimpleDateFormat("dd-MM-yyyy");
			return new Date(df.parse(fecha.replaceAll("_", "-").replaceAll("/", "-").replaceAll(" ", "-")).getTime());
		} catch (Exception e)
		{}
		return new Date(1);
	}

	/**
	 * cotizacion vo to view
	 * 
	 * @param tipoProceso
	 * @param format
	 * @param cotizante
	 * @param caja
	 * @param mutual
	 * @return
	 */
	public static Cotizacion cotizacionVOtoView(char tipoProceso, boolean format, CotizanteVO cotizante, boolean isCCAF, boolean isMUTUAL)
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

			if (!cotiz.isVoluntario())
			{
				if (cotiz.getMovimientoPersonal() != null)
				{
					List lista = new ArrayList();
					for (Iterator it = cotiz.getMovimientoPersonal().iterator(); it.hasNext();)
						lista.add((MovtoPersonalVO) it.next());
					cot.setMovimientoPersonal(lista);
				}
			} else
			{
				if (cotiz.getMovimientoPersonalAF() != null)
				{
					List lista = new ArrayList();
					for (Iterator it = cotiz.getMovimientoPersonalAF().iterator(); it.hasNext();)
						lista.add((MvtoPersoAFVO) it.next());
					cot.setMovimientoPersonal(lista);
				}
			}

			if (cotizante.getIdEntSaludReal() == Constants.ID_FONASA)
			{
				cot.setSaludObligISAPRE("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				cot.setSaludObligFONASA(format ? Utils.formatMonto(cotiz.getSaludObligatorio()) : "" + cotiz.getSaludObligatorio());
				//cot.setSaludObligFONASA("0");
				cot.setSaludPactado("" + 0);
				cot.setSaludAdicional("" + 0);
				cot.setSaludTotal("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
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
//				clillo 17-05-16 No hay pagos al IPS
				cot.setPrevObligatorioINP(format ? Utils.formatMonto(cotiz.getPrevisionObligatorio()) : "" + cotiz.getPrevisionObligatorio());
				//cot.setPrevObligatorioINP("0");
				cot.setPrevisionAdicional("" + 0);
				cot.setPrevisionAhorro("" + 0);
				cot.setPrevisionTotal("" + (cotiz.getSegCesEmpl() + cotiz.getSegCesTrab()));
//				clillo 17-05-16 No hay pagos al IPS
				totalInp += cotiz.getPrevisionObligatorio();
			} else
			{
				cot.setPrevObligatorioAFP(format ? Utils.formatMonto(cotiz.getPrevisionObligatorio()) : "" + cotiz.getPrevisionObligatorio());
				cot.setPrevObligatorioINP("" + 0);
				cot.setPrevisionAhorro(format ? Utils.formatMonto(cotiz.getPrevisionAhorro()) : "" + cotiz.getPrevisionAhorro());
				cot.setRentaImponibleSIS(format ? Utils.formatMonto(cotiz.getRentaImponibleSIS()) : "" + cotiz.getRentaImponibleSIS());
				cot.setPrevisionSIS(format ? Utils.formatMonto(cotiz.getPrevisionSIS()) : "" + cotiz.getPrevisionSIS());				
				int totalAFP = cotiz.getSegCesEmpl() + cotiz.getSegCesTrab() + cotiz.getTrabPesado() + cotiz.getPrevisionObligatorio() + cotiz.getPrevisionAhorro() + cotiz.getPrevisionSIS();
				cot.setPrevisionTotal(format ? Utils.formatMonto(totalAFP) : "" + totalAFP);
			}
			cot.setSegCesEmpl(format ? Utils.formatMonto(cotiz.getSegCesEmpl()) : "" + cotiz.getSegCesEmpl());
			cot.setSegCesRemImp(format ? Utils.formatMonto(cotiz.getSegCesRemImp()) : "" + cotiz.getSegCesRemImp());
			cot.setSegCesTrab(format ? Utils.formatMonto(cotiz.getSegCesTrab()) : "" + cotiz.getSegCesTrab());						

			cot.setTasaTrabPesado("" + Float.parseFloat("" + cotiz.getTasaTrabPesado()));
			cot.setTipoTrabPesado(cotiz.getTipoTrabPesado().trim());
			cot.setTrabPesado(format ? Utils.formatMonto(cotiz.getTrabPesado()) : "" + cotiz.getTrabPesado());
			cot.setMutualImp(format ? Utils.formatMonto(cotiz.getMutualImp()) : "" + cotiz.getMutualImp());
			cot.setAporteCaja(format ? Utils.formatMonto(cotiz.getCcafAporte()) : "" + cotiz.getCcafAporte());
			if (isMUTUAL)// hay mutual asociada a la empresa
			{
				cot.setAccTrabajoMutual(format ? Utils.formatMonto(cotiz.getInpMutual()) : "" + cotiz.getInpMutual());
				cot.setAccTrabajoINP("" + 0);
			} else
			{
				cot.setAccTrabajoMutual("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				cot.setAccTrabajoINP(format ? Utils.formatMonto(cotiz.getInpMutual()) : "" + cotiz.getInpMutual());
				//cot.setAccTrabajoINP("0");
//				clillo 17-05-16 No hay pagos al IPS
				totalInp += cotiz.getInpMutual();
			}
			if (isCCAF)
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
//				clillo 17-05-16 No hay pagos al IPS
				cot.setAsigFamiliarINP(format ? Utils.formatMonto(cotiz.getAsigFamiliar()) : "" + cotiz.getAsigFamiliar());
				cot.setAsigFamReintINP(format ? Utils.formatMonto(cotiz.getAsigFamReint()) : "" + cotiz.getAsigFamReint());
				cot.setAsigFamRetroINP(format ? Utils.formatMonto(cotiz.getAsigFamRetro()) : "" + cotiz.getAsigFamRetro());
				//cot.setAsigFamiliarINP("0");
				//cot.setAsigFamReintINP("0");
				//cot.setAsigFamRetroINP("0");
				cot.setAsigFamiliar("" + 0);
				cot.setAsigFamRetro("" + 0);
				cot.setAsigFamReint("" + 0);
				cot.setTotalAsigFam("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				totalInp = totalInp - cotiz.getAsigFamiliar() + cotiz.getAsigFamReint() - cotiz.getAsigFamRetro();
			}
//			clillo 17-05-16 No hay pagos al IPS
			cot.setInpBonificacion(format ? Utils.formatMonto(cotiz.getInpBonificacion()) : "" + cotiz.getInpBonificacion());
			totalInp -= cotiz.getInpBonificacion();
			cot.setInpDesahucio(format ? Utils.formatMonto(cotiz.getInpDesahucio()) : "" + cotiz.getInpDesahucio());
			totalInp += cotiz.getInpDesahucio();
			//cot.setInpBonificacion("0");
			//cot.setInpDesahucio("0");
			cot.setCcafCredito(format ? Utils.formatMonto(cotiz.getCcafCredito()) : "" + cotiz.getCcafCredito());
			cot.setCcafDental(format ? Utils.formatMonto(cotiz.getCcafDental()) : "" + cotiz.getCcafDental());
			cot.setCcafLeasing(format ? Utils.formatMonto(cotiz.getCcafLeasing()) : "" + cotiz.getCcafLeasing());
			cot.setCcafSeguro(format ? Utils.formatMonto(cotiz.getCcafSeguro()) : "" + cotiz.getCcafSeguro());

			cot.setRemImpPension(format ? Utils.formatMonto(cotiz.getRentaImpInp()) : "" + cotiz.getRentaImpInp());
			
//			clillo 17-05-16 No hay pagos al IPS
			cot.setTotalINP(totalInp);
			//cot.setTotalINP(0);
			// reforma
			cot.setApvcAporteEmpl(format ? Utils.formatMonto(cotiz.getApvcAporteEmpl()) : "" + cotiz.getApvcAporteEmpl());
			cot.setApvcAporteTrab(format ? Utils.formatMonto(cotiz.getApvcAporteTrab()) : "" + cotiz.getApvcAporteTrab());
			cot.setApvcNumContr(cotiz.getApvcNumContr().trim());
			cot.setAfpvRutDpndiente(cotiz.getAfpvRutDpndiente() > 0 ? Utils.formatRut(new Integer(cotiz.getAfpvRutDpndiente()).intValue()) : "");
			cot.setAfpvNombreDpndiente(cotiz.getAfpvNombreDpndiente().trim());

			cot.setAfpvAplldioPatDpndiente(cotiz.getAfpvAplldioPatDpndiente().trim());
			cot.setAfpvAplldioMatDpndiente(cotiz.getAfpvAplldioMatDpndiente().trim());
			
			//Jlandero 28/07/2010
			cot.setRentaImponibleSIS(format ? Utils.formatMonto(cotiz.getRentaImponibleSIS()) : "" + cotiz.getRentaImponibleSIS());
			cot.setPrevisionSIS(format ? Utils.formatMonto(cotiz.getPrevisionSIS()) : "" + cotiz.getPrevisionSIS());
			
			//csanchez Depósito Convenido
			//cot.setRentaImp(format ? Utils.formatMonto(cotiz.getRentaImponible()) : "" + cotiz.getRentaImponible());
			cot.setDepositoConvenido(format ? Utils.formatMonto(cotiz.getDepositoConvenido()) : "" + cotiz.getDepositoConvenido());
			cot.setTipoRegimenPrev("" + cotiz.getTipoRegimenPrev());
			cot.setTasaPactada(format ? Utils.formatMontoF(cotiz.getTasaPactada()) : "" + cotiz.getTasaPactada());
			int aporteIndem = cotiz.getIndemAporte();
			/*if (aporteIndem == 0)
				aporteIndem = Math.round(cotiz.getRentaImponible() * cotiz.getTasaPactada() / 100);*/
			cot.setIndemAporte(format ? Utils.formatMonto(aporteIndem) : "" + aporteIndem);
			cot.setIndemInicio(cotiz.getIndemInicio() != null && !cotiz.getIndemInicio().toString().equals(init) ? dateFormat.format(cotiz.getIndemInicio()) : "");
			cot.setIndemTermino(cotiz.getIndemTermino() != null && !cotiz.getIndemTermino().toString().equals(init) ? dateFormat.format(cotiz.getIndemTermino()) : "");
			cot.setNumPeriodos(format ? Utils.formatMonto(cotiz.getNumPeriodos()) : "" + cotiz.getNumPeriodos());
			cot.setIdEntDep("" + cotiz.getIdEntDep());
//			clillo 23-12-15 AFBR
			cot.setOtrosAFBR(format ? Utils.formatMonto(cotiz.getOtrosAFBR()) : "" + cotiz.getOtrosAFBR());

		} else if (tipoProceso == 'G')
		{
			CotizacionGRVO cotiz = (CotizacionGRVO) cotizacion;
			cot.setGratificacion(format ? Utils.formatMonto(cotiz.getGratificacion()) : "" + cotiz.getGratificacion());
			cot.setInicio((cotiz.getInicio() != null ? dateFormat.format(cotiz.getInicio()) : ""));
			cot.setTermino((cotiz.getTermino() != null ? dateFormat.format(cotiz.getTermino()) : ""));

			long totalInp = 0;
			// campos comunes
			if (cotizante.getIdEntSaludReal() == Constants.ID_FONASA)
			{
				cot.setSaludObligISAPRE("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				cot.setSaludObligFONASA(format ? Utils.formatMonto(cotiz.getSaludObligatorio()) : "" + cotiz.getSaludObligatorio());
				//cot.setSaludObligFONASA("0");
//				clillo 17-05-16 No hay pagos al IPS
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
//				clillo 17-05-16 No hay pagos al IPS
				cot.setPrevObligatorioINP(format ? Utils.formatMonto(cotiz.getPrevisionObligatorio()) : "" + cotiz.getPrevisionObligatorio());
				//cot.setPrevObligatorioINP("0");
				cot.setPrevisionTotal("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				totalInp += cotiz.getPrevisionObligatorio();
				cot.setPrevisionTotal("" + (cotiz.getSegCesRemImp() + cotiz.getSegCesTrab()));
			} else
			{
				cot.setPrevObligatorioAFP(format ? Utils.formatMonto(cotiz.getPrevisionObligatorio()) : "" + cotiz.getPrevisionObligatorio());
				cot.setPrevObligatorioINP("" + 0);
				cot.setPrevisionTotal("" + (cotiz.getPrevisionObligatorio() + cotiz.getSegCesRemImp() + cotiz.getSegCesTrab() + cotiz.getTrabPesado()));
				cot.setRentaImponibleSIS(format ? Utils.formatMonto(cotiz.getRentaImponibleSIS()) : "" + cotiz.getRentaImponibleSIS());
				cot.setPrevisionSIS(format ? Utils.formatMonto(cotiz.getPrevisionSIS()) : "" + cotiz.getPrevisionSIS());				
			}
			cot.setSegCesEmpl(format ? Utils.formatMonto(cotiz.getSegCesEmpl()) : "" + cotiz.getSegCesEmpl());
			cot.setSegCesRemImp(format ? Utils.formatMonto(cotiz.getSegCesRemImp()) : "" + cotiz.getSegCesRemImp());
			cot.setSegCesTrab(format ? Utils.formatMonto(cotiz.getSegCesTrab()) : "" + cotiz.getSegCesTrab());
			cot.setTasaTrabPesado("" + Float.parseFloat("" + cotiz.getTasaTrabPesado()));
			cot.setTipoTrabPesado(cotiz.getTipoTrabPesado().trim());
			cot.setTrabPesado(format ? Utils.formatMonto(cotiz.getTrabPesado()) : "" + cotiz.getTrabPesado());
			cot.setMutualImp(format ? Utils.formatMonto(cotiz.getMutualImp()) : "" + cotiz.getMutualImp());
			cot.setAporteCaja(format ? Utils.formatMonto(cotiz.getCcafAporte()) : "" + cotiz.getCcafAporte());
			if (isMUTUAL)// hay mutual asociada a la empresa
			{
				cot.setAccTrabajoMutual(format ? Utils.formatMonto(cotiz.getInpMutual()) : "" + cotiz.getInpMutual());
				cot.setAccTrabajoINP("" + 0);
			} else
			{
				cot.setAccTrabajoMutual("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				cot.setAccTrabajoINP(format ? Utils.formatMonto(cotiz.getInpMutual()) : "" + cotiz.getInpMutual());
				//cot.setAccTrabajoINP("0");
				//totalInp += cotiz.getInpMutual();
			}

			cot.setRemImpPension(format ? Utils.formatMonto(cotiz.getRentaImpInp()) : "" + cotiz.getRentaImpInp());
//			clillo 17-05-16 No hay pagos al IPS
			cot.setTotalINP(totalInp);
			//cot.setTotalINP(0);
			
			//Jlandero 28/07/2010
			cot.setRentaImponibleSIS(format ? Utils.formatMonto(cotiz.getRentaImponibleSIS()) : "" + cotiz.getRentaImponibleSIS());
			cot.setPrevisionSIS(format ? Utils.formatMonto(cotiz.getPrevisionSIS()) : "" + cotiz.getPrevisionSIS());
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
//				clillo 17-05-16 No hay pagos al IPS
				cot.setSaludObligFONASA(format ? Utils.formatMonto(cotiz.getSaludObligatorio()) : "" + cotiz.getSaludObligatorio());
				//cot.setSaludObligFONASA("0");
//				clillo 17-05-16 No hay pagos al IPS
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
//				clillo 17-05-16 No hay pagos al IPS
				cot.setPrevObligatorioINP(format ? Utils.formatMonto(cotiz.getPrevisionObligatorio()) : "" + cotiz.getPrevisionObligatorio());
				//cot.setPrevObligatorioINP("0");
//				clillo 17-05-16 No hay pagos al IPS
				totalInp += cotiz.getPrevisionObligatorio();
				cot.setPrevisionTotal("" + (cotiz.getSegCesEmpl() + cotiz.getSegCesTrab()));
			} else
			{
				cot.setPrevObligatorioAFP(format ? Utils.formatMonto(cotiz.getPrevisionObligatorio()) : "" + cotiz.getPrevisionObligatorio());
				cot.setPrevObligatorioINP("" + 0);
				cot.setPrevisionTotal("" + (cotiz.getSegCesEmpl() + cotiz.getSegCesTrab() + cotiz.getTrabPesado() + cotiz.getPrevisionObligatorio()));
				cot.setRentaImponibleSIS(format ? Utils.formatMonto(cotiz.getRentaImponibleSIS()) : "" + cotiz.getRentaImponibleSIS());
				cot.setPrevisionSIS(format ? Utils.formatMonto(cotiz.getPrevisionSIS()) : "" + cotiz.getPrevisionSIS());				
			}
			cot.setSegCesEmpl(format ? Utils.formatMonto(cotiz.getSegCesEmpl()) : "" + cotiz.getSegCesEmpl());
			cot.setSegCesRemImp(format ? Utils.formatMonto(cotiz.getSegCesRemImp()) : "" + cotiz.getSegCesRemImp());
			cot.setSegCesTrab(format ? Utils.formatMonto(cotiz.getSegCesTrab()) : "" + cotiz.getSegCesTrab());
			cot.setTasaTrabPesado("" + Float.parseFloat("" + cotiz.getTasaTrabPesado()));
			cot.setTipoTrabPesado(cotiz.getTipoTrabPesado().trim());
			cot.setTrabPesado(format ? Utils.formatMonto(cotiz.getTrabPesado()) : "" + cotiz.getTrabPesado());
			cot.setMutualImp(format ? Utils.formatMonto(cotiz.getMutualImp()) : "" + cotiz.getMutualImp());
			cot.setAporteCaja(format ? Utils.formatMonto(cotiz.getCcafAporte()) : "" + cotiz.getCcafAporte());
			if (isMUTUAL)// hay mutual asociada a la empresa
			{
				cot.setAccTrabajoMutual(format ? Utils.formatMonto(cotiz.getInpMutual()) : "" + cotiz.getInpMutual());
				cot.setAccTrabajoINP("" + 0);
			} else
			{
				cot.setAccTrabajoMutual("" + 0);
//				clillo 17-05-16 No hay pagos al IPS
				cot.setAccTrabajoINP(format ? Utils.formatMonto(cotiz.getInpMutual()) : "" + cotiz.getInpMutual());
				//cot.setAccTrabajoINP("0");
//				clillo 17-05-16 No hay pagos al IPS
				totalInp += cotiz.getInpMutual();
			}
//			clillo 17-05-16 No hay pagos al IPS
			cot.setRemImpPension(format ? Utils.formatMonto(cotiz.getRentaImpInp()) : "" + cotiz.getRentaImpInp());
			cot.setTotalINP(totalInp);
			//cot.setTotalINP(0);
			//Jlandero 28/07/2010
			cot.setRentaImponibleSIS(format ? Utils.formatMonto(cotiz.getRentaImponibleSIS()) : "" + cotiz.getRentaImponibleSIS());
			cot.setPrevisionSIS(format ? Utils.formatMonto(cotiz.getPrevisionSIS()) : "" + cotiz.getPrevisionSIS());
			//clillo 23-12-15 AFBR
			cot.setPeriodo(cotiz.getPeriodo());
			cot.setOtrosAFBR(format ? Utils.formatMonto(cotiz.getOtrosAFBR()) : "" + cotiz.getOtrosAFBR());
		} else if (tipoProceso == 'D')
		{
			CotizacionDCVO cotiz = (CotizacionDCVO) cotizacion;
			cot.setRentaImp(format ? Utils.formatMonto(cotiz.getRentaImponible()) : "" + cotiz.getRentaImponible());
			cot.setDepositoConvenido(format ? Utils.formatMonto(cotiz.getDepositoConvenido()) : "" + cotiz.getDepositoConvenido());
			cot.setTipoRegimenPrev("" + cotiz.getTipoRegimenPrev());
			cot.setTasaPactada(format ? Utils.formatMontoF(cotiz.getTasaPactada()) : "" + cotiz.getTasaPactada());
			int aporteIndem = cotiz.getIndemAporte();
			/*if (aporteIndem == 0)
				aporteIndem = Math.round(cotiz.getRentaImponible() * cotiz.getTasaPactada() / 100);*/
			cot.setIndemAporte(format ? Utils.formatMonto(aporteIndem) : "" + aporteIndem);
			cot.setIndemInicio(cotiz.getIndemInicio() != null && !cotiz.getIndemInicio().toString().equals(init) ? dateFormat.format(cotiz.getIndemInicio()) : "");
			cot.setIndemTermino(cotiz.getIndemTermino() != null && !cotiz.getIndemTermino().toString().equals(init) ? dateFormat.format(cotiz.getIndemTermino()) : "");
			cot.setNumPeriodos(format ? Utils.formatMonto(cotiz.getNumPeriodos()) : "" + cotiz.getNumPeriodos());
			cot.setIdEntDep("" + cotiz.getIdEntDep());
		}
		return cot;
	}

	/**
	 * view to cotizacion vo
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
	//public static CotizacionVO viewToCotizacionVO(char tipoProceso, Cotizante cotizante, Cotizacion cotiz, int rutEmpresa, int idConvenio, int idCotizante, int tipoPrevision, int idCaja, int idMutual, List mps)
//	clillo 3-12-14 por Reliquidación
	//public static CotizacionVO viewToCotizacionVO(char tipoProceso, Cotizante cotizante, Cotizacion cotiz, int rutEmpresa, int idConvenio, int idCotizante, int tipoPrevision, int idCaja, int idMutual, List mps, boolean informaSIS)	
	public static CotizacionVO viewToCotizacionVO(char tipoProceso, Cotizante cotizante, Cotizacion cotiz, int rutEmpresa, int idConvenio, int idCotizante, int tipoPrevision, int idCaja, int idMutual, List mps, boolean informaSIS, int periodo)	
	{
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
//				clillo 16-05-16 No hay pagos al IPS
				//cot.setSaludObligatorio(0);
				//cot.setSaludTotal(0);
				cot.setSaludObligatorio(Utils.desFormatMonto(cotiz.getSaludObligFONASA()));
				cot.setSaludTotal(cot.getSaludObligatorio());
				cot.setSaludAdicional(0);
				cot.setSaludPactado(0);
				cotizante.setIdEntSaludReal(Constants.ID_FONASA);
			}

			int totalAsigFamINP = 0;
			if (idCaja == Constants.SIN_CCAF)
			{
//				clillo 16-05-16 No hay pagos al IPS
				cot.setAsigFamiliar(Utils.desFormatMonto(cotiz.getAsigFamiliarINP()));
				cot.setAsigFamRetro(Utils.desFormatMonto(cotiz.getAsigFamRetroINP()));
				cot.setAsigFamReint(Utils.desFormatMonto(cotiz.getAsigFamReintINP()));
				totalAsigFamINP = cot.getAsigFamiliar() + cot.getAsigFamReint() - cot.getAsigFamRetro();
				
				//cot.setAsigFamiliar(0);
				//cot.setAsigFamRetro(0);
				//cot.setAsigFamReint(0);
				//totalAsigFamINP = 0;
			} else
			{
				cot.setAsigFamiliar(Utils.desFormatMonto(cotiz.getAsigFamiliar()));
				cot.setAsigFamRetro(Utils.desFormatMonto(cotiz.getAsigFamRetro()));
				cot.setAsigFamReint(Utils.desFormatMonto(cotiz.getAsigFamReint()));
			}

			cot.setSegCesEmpl(Utils.desFormatMonto(cotiz.getSegCesEmpl()));
			cot.setSegCesRemImp(Utils.desFormatMonto(cotiz.getSegCesRemImp()));
			cot.setSegCesTrab(Utils.desFormatMonto(cotiz.getSegCesTrab()));
			
//			clillo 17-05-16 No hay pagos al IPS
			cot.setInpDesahucio(Utils.desFormatMonto(cotiz.getInpDesahucio()));
			cot.setInpBonificacion(Utils.desFormatMonto(cotiz.getInpBonificacion()));
			//cot.setInpDesahucio(0);
			//cot.setInpBonificacion(0);
			if (tipoPrevision == Constants.TIPO_PREVISION_AFP)// es AFP
			{
				cot.setPrevisionObligatorio(Utils.desFormatMonto(cotiz.getPrevObligatorioAFP()));
				cot.setPrevisionAhorro(Utils.desFormatMonto(cotiz.getPrevisionAhorro()));
				cot.setPrevisionTotal(cot.getPrevisionObligatorio() + cot.getPrevisionAhorro() + cot.getSegCesEmpl() + cot.getSegCesTrab() + (informaSIS ? Utils.desFormatMonto(cotiz.getPrevisionSIS()) : 0) );
			} else if (tipoPrevision == Constants.TIPO_PREVISION_INP)// es INP
			{
//				clillo 17-05-16 No hay pagos al IPS
				cot.setPrevisionObligatorio(Utils.desFormatMonto(cotiz.getPrevObligatorioINP()));
				//cot.setPrevisionObligatorio(0);
				cot.setPrevisionAhorro(0);
				cot.setPrevisionTotal(cot.getPrevisionObligatorio() + cot.getInpDesahucio() + cot.getInpBonificacion() - totalAsigFamINP);
				//cot.setPrevisionTotal(0);
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
			
			//clillo 16-05-16 No hay pagos al IPS
			if (idMutual == Constants.SIN_MUTUAL)
				cot.setInpMutual(Utils.desFormatMonto(cotiz.getAccTrabajoINP()));
				//cot.setInpMutual(0);
			else
				cot.setInpMutual(Utils.desFormatMonto(cotiz.getAccTrabajoMutual()));

			cot.setRentaImpInp(Utils.desFormatMonto(cotiz.getRemImpPension()));
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
			
			//Se agregan nuevos montos segun definiciones
			cot.setRentaImponibleSIS(Utils.desFormatMonto(cotiz.getRentaImponibleSIS()));
			cot.setPrevisionSIS(Utils.desFormatMonto(cotiz.getPrevisionSIS()));

			//csanchez Depósito Convenido
			//cot.setRentaImponible(Utils.desFormatMonto(cotiz.getRentaImp()));
			cot.setDepositoConvenido(Utils.desFormatMonto(cotiz.getDepositoConvenido()));
			cot.setTipoRegimenPrev(new Integer(cotiz.getTipoRegimenPrev()==null?"0":cotiz.getTipoRegimenPrev()).intValue()); //Para el caso en que se permita el ingreso de un trabajador sin INP ni AFP (Deposito Convenido)
			cot.setTasaPactada(Utils.desFormatMontoF(cotiz.getTasaPactada()));
			cot.setIndemAporte(Utils.desFormatMonto(cotiz.getIndemAporte()));
			cot.setIndemInicio(cotiz.getIndemInicio() != null && !cotiz.getIndemInicio().equals("") ? desformatFecha(cotiz.getIndemInicio()) : init);
			cot.setIndemTermino(cotiz.getIndemTermino() != null && !cotiz.getIndemTermino().equals("") ? desformatFecha(cotiz.getIndemTermino()) : init);
			cot.setNumPeriodos(Utils.desFormatMonto(cotiz.getNumPeriodos()));
			cot.setIdEntDep(Integer.parseInt(cotiz.getIdEntDep()));
			//clillo 23-1-15 AFBR
			cot.setOtrosAFBR(Utils.desFormatMonto(cotiz.getOtrosAFBR()));
			return cot;
		} else if (tipoProceso == 'G')
		{
			CotizacionGRVO cot = new CotizacionGRVO(new Integer(rutEmpresa).intValue(), new Integer(idConvenio).intValue(), new Integer(idCotizante).intValue());
			cot.setGratificacion(Utils.desFormatMonto(cotiz.getGratificacion()));
			cot.setInicio(cotiz.getInicio() != null && !cotiz.getInicio().equals("") ? desformatFecha(cotiz.getInicio()) : init);
			cot.setTermino(cotiz.getTermino() != null && !cotiz.getTermino().equals("") ? desformatFecha(cotiz.getTermino()) : init);

			int valor = Utils.desFormatMonto(cotiz.getSaludObligISAPRE());
			if (valor > 0) // es ISAPRE
				cot.setSaludObligatorio(valor);
			else
			// es FONASA
			{
//				clillo 16-05-16 No hay pagos al IPS
				cot.setSaludObligatorio(Utils.desFormatMonto(cotiz.getSaludObligFONASA()));
				//cot.setSaludObligatorio(0);
				cotizante.setIdEntSaludReal(Constants.ID_FONASA);
			}
			if (tipoPrevision == Constants.TIPO_PREVISION_AFP)// es AFP
				cot.setPrevisionObligatorio(Utils.desFormatMonto(cotiz.getPrevObligatorioAFP()));
//			clillo 16-05-16 No hay pagos al IPS
			else if (tipoPrevision == Constants.TIPO_PREVISION_INP)// es INP
				cot.setPrevisionObligatorio(Utils.desFormatMonto(cotiz.getPrevObligatorioINP()));
			else
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
//				clillo 16-05-16 No hay pagos al IPS
				cot.setInpMutual(Utils.desFormatMonto(cotiz.getAccTrabajoINP()));
				//cot.setInpMutual(0);
			else
				cot.setInpMutual(Utils.desFormatMonto(cotiz.getAccTrabajoMutual()));

			cot.setRentaImpInp(Utils.desFormatMonto(cotiz.getRemImpPension()));
			//Se agregan nuevos montos segun definiciones
			cot.setRentaImponibleSIS(Utils.desFormatMonto(cotiz.getRentaImponibleSIS()));
			cot.setPrevisionSIS(Utils.desFormatMonto(cotiz.getPrevisionSIS()));			
			
			return cot;
		} else if (tipoProceso == 'A')
		{
//			clillo 3-12-14 por Reliquidación
			//CotizacionRAVO cot = new CotizacionRAVO(new Integer(rutEmpresa).intValue(), new Integer(idConvenio).intValue(), new Integer(idCotizante).intValue());
			CotizacionRAVO cot = new CotizacionRAVO(new Integer(rutEmpresa).intValue(), new Integer(idConvenio).intValue(), new Integer(idCotizante).intValue(), new Integer(periodo).intValue());
			cot.setReliquidacion(Utils.desFormatMonto(cotiz.getReliquidacion()));

			cot.setInicio(cotiz.getInicio() != null && !cotiz.getInicio().equals("") ? desformatFecha(cotiz.getInicio()) : init);
			cot.setTermino(cotiz.getTermino() != null && !cotiz.getTermino().equals("") ? desformatFecha(cotiz.getTermino()) : init);

			int valor = Utils.desFormatMonto(cotiz.getSaludObligISAPRE());
			if (valor > 0) // es ISAPRE
				cot.setSaludObligatorio(valor);
			else
			// es FONASA
			{
//				clillo 16-05-16 No hay pagos al IPS
				cot.setSaludObligatorio(Utils.desFormatMonto(cotiz.getSaludObligFONASA()));
				//cot.setSaludObligatorio(0);
				cotizante.setIdEntSaludReal(Constants.ID_FONASA);
			}

			if (tipoPrevision == Constants.TIPO_PREVISION_AFP)// es AFP
			{
				cot.setPrevisionObligatorio(Utils.desFormatMonto(cotiz.getPrevObligatorioAFP()));
			} else if (tipoPrevision == Constants.TIPO_PREVISION_INP)// es INP
			{
//				clillo 16-05-16 No hay pagos al IPS
				cot.setPrevisionObligatorio(Utils.desFormatMonto(cotiz.getPrevObligatorioINP()));
				//jlandero 26/08/2010
				cotizante.setIdEntAFCReal(cotizante.getIdEntAFCReal());
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
//				clillo 16-05-16 No hay pagos al IPS
				cot.setInpMutual(Utils.desFormatMonto(cotiz.getAccTrabajoINP()));
				//cot.setInpMutual(0);
			else
				cot.setInpMutual(Utils.desFormatMonto(cotiz.getAccTrabajoMutual()));
			
			cot.setRentaImpInp(Utils.desFormatMonto(cotiz.getRemImpPension()));
			//Se agregan nuevos montos segun definiciones
			cot.setRentaImponibleSIS(Utils.desFormatMonto(cotiz.getRentaImponibleSIS()));
			cot.setPrevisionSIS(Utils.desFormatMonto(cotiz.getPrevisionSIS()));
//			clillo 27-1-15 AFBR
			cot.setOtrosAFBR(Utils.desFormatMonto(cotiz.getOtrosAFBR()));
			return cot;
		} else if (tipoProceso == 'D')
		{
			CotizacionDCVO cot = new CotizacionDCVO(new Integer(rutEmpresa).intValue(), new Integer(idConvenio).intValue(), new Integer(idCotizante).intValue());
			cot.setRentaImponible(Utils.desFormatMonto(cotiz.getRentaImp()));
			cot.setDepositoConvenido(Utils.desFormatMonto(cotiz.getDepositoConvenido()));
			cot.setTipoRegimenPrev(new Integer(cotiz.getTipoRegimenPrev()==null?"0":cotiz.getTipoRegimenPrev()).intValue()); //Para el caso en que se permita el ingreso de un trabajador sin INP ni AFP (Deposito Convenido)
			cot.setTasaPactada(Utils.desFormatMontoF(cotiz.getTasaPactada()));
			cot.setIndemAporte(Utils.desFormatMonto(cotiz.getIndemAporte()));
			cot.setIndemInicio(cotiz.getIndemInicio() != null && !cotiz.getIndemInicio().equals("") ? desformatFecha(cotiz.getIndemInicio()) : init);
			cot.setIndemTermino(cotiz.getIndemTermino() != null && !cotiz.getIndemTermino().equals("") ? desformatFecha(cotiz.getIndemTermino()) : init);
			cot.setNumPeriodos(Utils.desFormatMonto(cotiz.getNumPeriodos()));
			cot.setIdEntDep(Integer.parseInt(cotiz.getIdEntDep()));
			return cot;
		}
		return null;
	}

	/**
	 * view to cotizante vo
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
	 * @param isPrevired
	 * @return
	 */
//	clillo 3-12-14 por Reliquidación
	//public static CotizanteVO viewToCotizanteVO(char tipoProceso, Cotizante cotizante, int rutEmpresa, int idConvenio, int idCotizante, int tipoPrevision, int idEntSil, int idCaja, List listApvs)
	public static CotizanteVO viewToCotizanteVO(char tipoProceso, Cotizante cotizante, int rutEmpresa, int idConvenio, int idCotizante, int tipoPrevision, int idEntSil, int idCaja, List listApvs, int periodo)
	{
//		clillo 3-12-14 por Reliquidación
		//CotizanteVO cot = new CotizanteVO(rutEmpresa, idConvenio, idCotizante);
		CotizanteVO cot = new CotizanteVO(rutEmpresa, idConvenio, idCotizante, periodo);
		cot.setTipoProceso(tipoProceso);
		cot.setNombre(cotizante.getNombre());
		cot.setApellidoPat(cotizante.getApellidoPat());
		cot.setApellidoMat(cotizante.getApellidoMat());
		cot.setIdSucursal(cotizante.getIdSucursal());
		cot.setTipoPrevision(tipoPrevision);

		cot.setIdEntSaludReal(cotizante.getIdEntSaludReal() == -1 ? Constants.ID_FONASA : cotizante.getIdEntSaludReal());
		if (idCaja == Constants.SIN_CCAF)
		{
			cot.setIdTramoReal(cotizante.getIdTramoRealINP() != -1 ? cotizante.getIdTramoRealINP() : Constants.TRAMO_ASIGFAM_NINGUNO);
			int numCargaTmp = 0;
			try
			{
				numCargaTmp = new Integer(cotizante.getNumCargaSimpleINP()).intValue();
			} catch (Exception e)
			{
			}
			cot.setNumCargaSimple(numCargaTmp);
			numCargaTmp = 0;
			try
			{
				numCargaTmp = new Integer(cotizante.getNumCargaMaternaINP()).intValue();
			} catch (Exception e)
			{
			}
			cot.setNumCargaMaterna(numCargaTmp);
			numCargaTmp = 0;
			try
			{
				numCargaTmp = new Integer(cotizante.getNumCargaInvalidezINP()).intValue();
			} catch (Exception e)
			{
			}
			cot.setNumCargaInvalidez(numCargaTmp);
		} else
		{
			cot.setIdTramoReal(cotizante.getIdTramoReal() != -1 ? cotizante.getIdTramoReal() : Constants.TRAMO_ASIGFAM_NINGUNO);
			int numCargaTmp = 0;
			try
			{
				numCargaTmp = new Integer(cotizante.getNumCargaSimple()).intValue();
			} catch (Exception e)
			{
			}
			cot.setNumCargaSimple(numCargaTmp);
			numCargaTmp = 0;
			try
			{
				numCargaTmp = new Integer(cotizante.getNumCargaMaterna()).intValue();
			} catch (Exception e)
			{
			}
			cot.setNumCargaMaterna(numCargaTmp);
			numCargaTmp = 0;
			try
			{
				numCargaTmp = new Integer(cotizante.getNumCargaInvalidez()).intValue();
			} catch (Exception e)
			{
			}
			cot.setNumCargaInvalidez(numCargaTmp);
		}
		cot.setIdEntidadAFPVReal(cotizante.getIdEntidadAFPVReal());
		cot.setIdEntidadAPVCReal(cotizante.getIdEntidadAPVCReal());
		cot.setIdEntExCaja(cotizante.getIdEntExCaja());
		cot.setIdRegimenImp(cotizante.getIdRegimenImp());
		cot.setIdSucursal(cotizante.getIdSucursal());
		cot.setIdEntSil(idEntSil);
		cot.setIdGeneroReal(new Integer(cotizante.getIdGeneroReal()).intValue());
		cot.setNumDiasTrabajados(cotizante.getNumDiasTrabajados() != null && !cotizante.getNumDiasTrabajados().equals("") ? new Integer(cotizante.getNumDiasTrabajados()).intValue() : 0);
		cot.setApellidoPat(cotizante.getApellidoPat());
		cot.setApellidoMat(cotizante.getApellidoMat());
		cot.setApvList(listApvs);
		cot.setIdEntAfcReal(cotizante.getIdEntAFCReal());
		//TODO Consultar la lógica de ésto
		if (tipoProceso != 'D') {
			if (tipoPrevision == Constants.TIPO_PREVISION_AFP) { // AFP
				cot.setIdEntPensionReal(cotizante.getIdEntPensionReal());
				//clillo 1/12/14 Se agrega condición para no eliminar AFC si la AFP es Ninguna
				if(cotizante.getIdEntPensionReal()!= Constants.ID_AFP_NINGUNA){
					cot.setIdEntAfcReal(cotizante.getIdEntPensionReal());
				}
			} else if (tipoPrevision == Constants.TIPO_PREVISION_INP) {// INP
				cot.setIdEntPensionReal(Constants.ID_INP);
				if(cotizante.getIdEntPensionReal()>0){
					cot.setIdEntAfcReal(cotizante.getIdEntPensionReal());
				}
			} else { // ninguna
				cot.setIdEntPensionReal(Constants.ID_AFP_NINGUNA);
			}
			//jlandero 26/08/2010
		} else {
			cot.setIdEntPensionReal(cotizante.getIdEntPensionReal() == -1 ? Constants.ID_INP : cotizante.getIdEntPensionReal());
			cot.setIdEntAfcReal(cotizante.getIdEntPensionReal());
		}

		return cot;
	}
	/**
	 * Comportamiento idéntico a cotizanteAvisoVOtoView(char, List, HashMap), pero recibe por parámetro la sesión
	 * para poder instanciar la cotización y así determinar si el cotizante es Voluntario.
	 * 
	 * @param tipoProceso
	 * @param cotizantes
	 * @param sucursales
	 * @param sesion
	 * @return
	 */
	public static List cotizanteAvisoVOtoView(char tipoProceso, List cotizantes, HashMap sucursales, Session sesion)
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
			//csanchez
			if(tipoProceso == 'R' && cotizante != null)
			{
				try
				{
					Class[] argTypes = { Integer.class
									   , Integer.class
									   , Integer.class };
					Object[] argValues = { new Integer(cotizante.getRutEmpresa())
										 , new Integer(cotizante.getIdConvenio())
										 , new Integer(cotizante.getIdCotizante()) };

					Class classCotizacion = CotizacionREVO.class;
					
					CotizacionVO cotizacion = (CotizacionVO) sesion.get(classCotizacion, (CotizacionVO) classCotizacion.getConstructor(argTypes).newInstance(argValues));
					
					CotizacionREVO cotiz = (CotizacionREVO) cotizacion;
					
					boolean flgVoluntario = cotiz.isVoluntario();
					
					cot.setFlgVoluntario(flgVoluntario);
					
				} catch (Exception e)
				{
					logger.error("FactoryView.cotizanteAvisoVOtoView::" + e.getMessage(), e);
				}
			}

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
