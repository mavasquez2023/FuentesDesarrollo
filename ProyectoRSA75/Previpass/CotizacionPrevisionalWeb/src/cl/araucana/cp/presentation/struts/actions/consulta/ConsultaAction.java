package cl.araucana.cp.presentation.struts.actions.consulta;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegImpositivoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizacion;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizante;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ComprobanteMgr;
import cl.araucana.cp.presentation.mgr.ConceptoMgr;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.CotizanteMgr;
import cl.araucana.cp.presentation.mgr.EntidadesMgr;
import cl.araucana.cp.presentation.mgr.MapeosMgr;
import cl.araucana.cp.presentation.mgr.ParametroMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.struts.forms.ConsultaActionForm;
import cl.araucana.cp.presentation.utils.FactoryView;
import cl.araucana.cp.util.reporte.ConstructorEXCEL;

import com.bh.talon.User;

/*
 * @(#) ConsultaAction.java 1.21 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.21
 */
public class ConsultaAction extends AppAction
{
	static Logger logger = Logger.getLogger(ConsultaAction.class);
	private char tipoNomina;
	private HashMap entsSalud = new HashMap();
	private HashMap entsAFP = new HashMap();
	private HashMap entsAPV = new HashMap();
	private HashMap apvs = new HashMap();

	/**
	 * consulta action
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ConsultaActionForm actForm = (ConsultaActionForm) form;

		Session session = null;
		try
		{
			session = HibernateUtil.getSession();

			// seteo tipo proceso
			if (actForm.getTiposProcesos() == null || actForm.getTiposProcesos().size() == 0)
				actForm.setTiposProcesos((List) (new ComprobanteMgr(session)).getTiposProceso());
			if (actForm.getTipoProceso() == null || actForm.getTipoProceso().equals(""))
			{
				TipoNominaVO tn = (TipoNominaVO) actForm.getTiposProcesos().get(0);
				this.tipoNomina = tn.getIdTipoNomina().charAt(0);
				actForm.setTipoProceso(tn.getIdTipoNomina());
			} else
				this.tipoNomina = actForm.getTipoProceso().charAt(0);
			
			if (actForm.getAccion() != null && actForm.getAccion().equals("excel"))
				return generaExcel(actForm, session, response);

			logger.info("ConsultaAction:genera lista nominas para tipoProceso:" + this.tipoNomina + "::");
			ProcesoMgr procesoMgr = new ProcesoMgr(session);
			Map result = procesoMgr.getEmpConvConsulta(this.tipoNomina, (PersonaVO) usuario.getUserReference(), (UsuarioCP) usuario);
			List consulta = new ArrayList(result.values());
			logger.info("ConsultaAction: número de empresas con archivo de Consulta:" + consulta.size());
			if (consulta.size() > 0)
			{
				for (Iterator it = consulta.iterator(); it.hasNext();)
				{
					EmpresaVO empresa = (EmpresaVO) it.next();
					List lista = new ArrayList(empresa.getConvenios());
					Collections.sort(lista);
					empresa.setConvenios(lista);
				}
				Collections.sort(consulta);
				int pagina = request.getParameter("paginaNumero") != null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
				HashMap paginacion = Utils.llenarPaginacionCL(pagina, consulta);
				consulta = (List) paginacion.get("data");
				actForm.setNumeroFilas((List) paginacion.get("paginas"));
				actForm.setConsulta(consulta);
			} else
			{
				actForm.setConsulta(null);
				actForm.setNumeroFilas(null);
			}
			return mapping.findForward("consulta");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ConsultaAction.doTask()", ex);
			return mapping.findForward("error");
		}
	}

	/**
	 * busca tasa
	 * 
	 * @param idEntExCaja
	 * @param idRegImp
	 * @param listaRegImp
	 * @return
	 */
	public float buscaTasa(int idEntExCaja, int idRegImp, List listaRegImp)
	{
		if (idEntExCaja != Constants.EXCAJA_FALSO && idRegImp != Constants.CODREGIMP_FALSO && listaRegImp != null)
			for (Iterator it = listaRegImp.iterator(); it.hasNext();)
			{
				RegImpositivoVO regImp = (RegImpositivoVO) it.next();
				if (regImp.getIdEntExCaja() == idEntExCaja && regImp.getIdRegImpositivo() == idRegImp)
					return regImp.getTasaPension();
			}
		return 0;
	}

	/**
	 * apvs
	 * 
	 * @param apvs
	 * @return
	 */
	private List setApvs(String idCotizante)
	{
		List apvsTmp = new ArrayList();
		int count = 0;
		List listaApvs = (List)this.apvs.get(idCotizante);
		if (listaApvs != null)
			for (Iterator it = listaApvs.iterator(); it.hasNext();)
			{
				if (count == 5)
					break;
				ApvVO apv = (ApvVO) it.next();
				apv.setMontoFormat(Utils.formatMonto(apv.getAhorro()));
				apvsTmp.add(apv);
				count++;
			}
		for (int i = apvsTmp.size(); i < 5; i++)
			apvsTmp.add(new ApvVO());
		return apvsTmp;
	}

	private List setApvs()
	{
		List apvsTmp = new ArrayList();
		for (int i = 0; i < 5; i++)
			apvsTmp.add(new ApvVO());
		return apvsTmp;
	}

	/**
	 * genera excel
	 * 
	 * @param actForm
	 * @param usuario
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward generaExcel(ConsultaActionForm actForm, Session session, HttpServletResponse response) throws Exception
	{
		int idConvenio=0;
		int rutEmpresa=0;
		String listaConvenios="";
		String filename="";
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");		
		//PrintWriter writer = null;
		OutputStream output= response.getOutputStream();
		ZipOutputStream zos= null;
		boolean zip= false;
		try {
			idConvenio = Integer.parseInt(actForm.getIdConvenio());
			rutEmpresa = Integer.parseInt(actForm.getRutEmpresa());
			filename="Consulta-" + rutEmpresa + "-Conv." + idConvenio + "-" + this.tipoNomina + ".xls";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			listaConvenios=rutEmpresa+"_"+ idConvenio;
			//writer = response.getWriter();
		} catch (Exception e) {
			listaConvenios= actForm.getListaConvenios();
			filename="Consulta-" + this.tipoNomina  + ".zip";
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			if(listaConvenios==null){
				listaConvenios="";
			}
			zip= true;
			//output= response.getOutputStream();
		}
		
		String[] lista= listaConvenios.split(",");

		ConvenioMgr convenioMgr = new ConvenioMgr(session);
		CotizanteMgr cotizanteMgr = new CotizanteMgr(session);
		ParametroMgr parametroMgr = new ParametroMgr(session);
		ConceptoMgr conceptoMgr = new ConceptoMgr(session);
		EntidadesMgr entidadesMgr = new EntidadesMgr(session);
		
		boolean generaEncabezado=true;
		boolean genProdCaja=true;
		if(zip){
			System.out.println("Opción ZIP");
			zos = new ZipOutputStream(output);
			ZipEntry entry = new ZipEntry("Detalle Nominas.xls");
			zos.putNextEntry(entry);
		}
		for (int i = 0; i < lista.length; i++) {
			String par= lista[i];
			String[] rutconv= par.split("_");
			rutEmpresa= Integer.parseInt(rutconv[0]);
			idConvenio= Integer.parseInt(rutconv[1]);
			
			// seteo datos comunes a todos los cotizantes
			ConvenioVO convenio = convenioMgr.getConvenio(rutEmpresa, idConvenio);
			int idGrupoConvenio = convenio.getIdGrupoConvenio();
			GrupoConvenioVO grupoConvenio = convenioMgr.getGrupoConvenio(idGrupoConvenio);
			List listaRegImp = entidadesMgr.getRegImp();

			EntidadCCAFVO caja = entidadesMgr.getCaja(convenio.getIdCcaf());
			boolean isCCAF = (caja != null ? true : false);
			EntidadMutualVO mutual = entidadesMgr.getMutual(convenio.getIdMutual());
			boolean isMUTUAL = (mutual != null ? true : false);
			boolean isProdCaja= grupoConvenio.isProdCaja();
			genProdCaja= genProdCaja&&isProdCaja;

			List cotizantes = cotizanteMgr.getCotizantesNomina(rutEmpresa, idConvenio, this.tipoNomina);
			System.out.println("total cotizantes nómina=" + cotizantes.size());
			if (cotizantes.size() > 0)
			{
				this.entsSalud = entidadesMgr.getEntidadesHash(false, 0, EntidadSaludVO.class);
				this.entsAFP = entidadesMgr.getEntidadesHash(false, 0, EntidadPensionVO.class);
				this.entsAPV = entidadesMgr.getEntidadesHash(false, 0, EntidadApvVO.class);
				if (this.tipoNomina == 'R')
					this.apvs = cotizanteMgr.getApvsHash(rutEmpresa, idConvenio);
				System.out.println("Ordenando Lista Cotizantes");
				Collections.sort(cotizantes);
			}
			List detalleExcel = new ArrayList();
			System.out.println("Obteniendo Cotizaciones de Cotizantes");
			int j=1;
			for (Iterator it = cotizantes.iterator(); it.hasNext();)
			{
				CotizanteVO cotVO = (CotizanteVO) it.next();
				Cotizante cotizante = FactoryView.cotizanteVOtoView(this.tipoNomina, cotVO);
				logger.debug("procesando:" + cotizante.getIdCotizante() + "::");
				Cotizacion cotizacion = FactoryView.cotizacionVOtoView(this.tipoNomina, false, cotVO, isCCAF, isMUTUAL);
				if(j%1000==0){
					System.out.println("Obteniendo Cotización Trabajador " + j);
				}
				if (this.tipoNomina == 'R')
				{
					CotizacionREVO cotREVO = (CotizacionREVO) cotVO.getCotizacion();
					if (cotREVO.isVoluntario())
					{
						cotizante.setIdEntPensionReal(cotizante.getIdEntidadAFPVReal());
						cotizante.setApvs(setApvs());
					} else
					{
						cotizante.setApvs(setApvs(cotizante.getIdCotizante()));
						cotizante.setTasaCotizacion("" + buscaTasa(cotizante.getIdEntExCaja(), cotizante.getIdRegimenImp(), listaRegImp));
						cotizacion.setSaludObligFONASA("" + (new Integer(cotizacion.getSaludObligFONASA()).intValue() + new Integer(cotizacion.getSaludObligISAPRE()).intValue()));
					}
					//TODO jlandero : se agregan los datos faltantes
					cotizacion.setRentaImponibleSIS(String.valueOf(cotREVO.getRentaImponibleSIS()));
					cotizacion.setPrevisionSIS(String.valueOf(cotREVO.getPrevisionSIS()));

					//TODO csanchez: Depósito Convenido
					cotizacion.setDepositoConvenido(String.valueOf(cotREVO.getIdEntDep()));
					cotizacion.setTipoRegimenPrev(cotizacion.getTipoRegimenPrev().equals("2") ? "AFP" : cotizacion.getTipoRegimenPrev().equals("1") ? "INP" : "");
					cotizacion.setTasaPactada(String.valueOf(cotREVO.getTasaPactada()));
					cotizacion.setIndemAporte(String.valueOf(cotREVO.getIndemAporte()));
					cotizacion.setIdEntDep(String.valueOf(cotREVO.getIdEntDep()));
					cotizante = setEntidadDeposito(cotREVO, cotizante);

				} else if (this.tipoNomina == 'G')
					cotizante.setTasaCotizacion("" + buscaTasa(cotizante.getIdEntExCaja(), cotizante.getIdRegimenImp(), listaRegImp));
				else if (this.tipoNomina == 'A')
					cotizante.setTasaCotizacion("" + buscaTasa(cotizante.getIdEntExCaja(), cotizante.getIdRegimenImp(), listaRegImp));
				else if (this.tipoNomina == 'D') {
					cotizacion.setTipoRegimenPrev(cotizacion.getTipoRegimenPrev().equals("2") ? "AFP" : cotizacion.getTipoRegimenPrev().equals("1") ? "INP" : "");
					CotizacionDCVO cotDCVO = (CotizacionDCVO) cotVO.getCotizacion();
					cotizante = setEntidadDeposito(cotDCVO, cotizante);
				}

				cotizante = setNombresReales(this.tipoNomina, cotizante);
				cotizante.setCotizacion(cotizacion);
				detalleExcel.add(cotizante);
				j++;
			}
			// PENDIENTES
			//clillo 16-12-2015 si estado nómina es "CON_ERRORES" no se muestra en la Consulta el convenio
			//por la tanto queda obsoleta la consulta de PENDIENTES
			
			/*List cotizPendientes = cotizanteMgr.getListaCotizPend(rutEmpresa, idConvenio, this.tipoNomina, null);
			System.out.println("Numero Cotizaciones Pendientes= " + cotizPendientes.size());
			
			if (cotizPendientes.size() > 0)
			{
				List listaConceptos = conceptoMgr.getListaConceptos("" + this.tipoNomina);
				List listaMapeo = conceptoMgr.getListaMapeosConcepto(grupoConvenio.getIdMapaNom(this.tipoNomina), "" + this.tipoNomina);
				Properties mapNombres = new Properties();
				mapNombres.load(getClass().getResourceAsStream("/files/mapeoConceptos.properties"));

				HashMap mapeosConcep = new HashMap();
				for (Iterator itM = listaMapeo.iterator(); itM.hasNext();)
				{
					MapeoConceptoVO mc = (MapeoConceptoVO) itM.next();
					mapeosConcep.put("" + mc.getIdConcepto(), mc);
				}

				FactoryView fv = new FactoryView();
				fv.setListasConceptos(listaConceptos, listaMapeo, mapNombres);
				int k=1;
				for (Iterator it = cotizPendientes.iterator(); it.hasNext();) // por cada Pendiente
				{
					CotizacionPendienteVO cotizPendVO = (CotizacionPendienteVO) it.next();
					Cotizante cot = fv.cotizPendVOtoView(this.tipoNomina, cotizPendVO, null);
					cot = cotizanteMgr.setNombresReales(this.tipoNomina, false, grupoConvenio.getIdMapaCod(), cot);
					Cotizacion cotizacion = fv.cotizPendVOtoView(this.tipoNomina, cot, cotizPendVO, isCCAF, isMUTUAL, null, null);
					if(k%1000==0){
						System.out.println("Obteniendo Cotización Pendiente " + j);
					}
					if (this.tipoNomina == 'R' && cot.isAfpVoluntario())
					{
						cot.setIdEntPension(cot.getIdEntidadAFPV());
						cot.setApvs(setApvs());
					} else if (this.tipoNomina == 'R')
					{
						cot.setTasaCotizacion("" + buscaTasa(cot.getIdEntExCaja(), cot.getIdRegimenImp(), listaRegImp));
						cotizacion.setSaludObligFONASA("" + (new Integer(cotizacion.getSaludObligFONASA()).intValue() + new Integer(cotizacion.getSaludObligISAPRE()).intValue()));
						MapeosMgr mapeosMgr = new MapeosMgr(session);
						List listValidaApvs = conceptoMgr.getLstValidaAPVs();
						cot.setApvs(fv.apvPendToView(cotizPendVO, listValidaApvs, mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAPVVO.class, EntidadApvVO.class), new ArrayList()));
					} else if (this.tipoNomina == 'G' || this.tipoNomina == 'A')
						cot.setTasaCotizacion("" + buscaTasa(cot.getIdEntExCaja(), cot.getIdRegimenImp(), listaRegImp));
					else if (this.tipoNomina == 'D')
						cotizacion.setTipoRegimenPrev(cotizacion.getTipoRegimenPrev().equals("2") ? "AFP" : cotizacion.getTipoRegimenPrev().equals("1") ? "INP" : "");
					cot.setCotizacion(cotizacion);
					detalleExcel.add(cot);
					k++;
				}
			}
		*/
		
		//TODO GMALLEA: Se manda " " porque se valida si es INDEPENDIENTE...
			String tipoAdmin="";
			if(genProdCaja){
				tipoAdmin= Constants.GRUPO_CONV_PRODUCTOS_CAJA;
			}
			if(zip){	
				System.out.println("Zipeando registros");
				ConstructorEXCEL.generaTabla(this.tipoNomina, detalleExcel, zos, idGrupoConvenio, Utils.formatRut(rutEmpresa), idConvenio, 0, (isCCAF ? caja.getNombre().trim() : ""), 
						(isMUTUAL ? mutual.getNombre().trim() : ""), parametroMgr.getParam(Constants.PARAM_TASA_FIJA), "" + convenio.getMutualTasaAdicional(), tipoAdmin, generaEncabezado);
				generaEncabezado= false;
			}else{
				ConstructorEXCEL.generaTabla(this.tipoNomina, detalleExcel, output, idGrupoConvenio, Utils.formatRut(rutEmpresa), idConvenio, 0, (isCCAF ? caja.getNombre().trim() : ""), 
						(isMUTUAL ? mutual.getNombre().trim() : ""), parametroMgr.getParam(Constants.PARAM_TASA_FIJA), "" + convenio.getMutualTasaAdicional(), tipoAdmin, generaEncabezado);
				generaEncabezado= false;
			}
			
		}
		if(zip){
			zos.flush();
			zos.close();
		}
		//writer.close();
		output.close();
		return null;
	}
	
	public void zipConsultaExcel(String filename, OutputStream out, ByteArrayInputStream data){
		try {
			byte b[] = new byte[2048];
			System.out.println("genrando ZIP");
			ZipOutputStream zos = new ZipOutputStream(out);
			ZipEntry entry = new ZipEntry("Detalle Nominas.xls");
			zos.putNextEntry(entry);
		    int len;
		    while ((len = data.read(b)) != -1) {
		        zos.write(b, 0, len);
		    }
			zos.flush();
			zos.close();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	

	/**
	 * cotizantes nombre reales
	 * 
	 * @param tipoProceso
	 * @param cot
	 * @return
	 * @throws DaoException
	 */
	public Cotizante setNombresReales(char tipoProceso, Cotizante cot)
	{
		if (tipoProceso != 'D')
		{
			if (this.entsSalud.containsKey("" + cot.getIdEntSaludReal()))
				cot.setIdEntSalud((String)this.entsSalud.get("" + cot.getIdEntSaludReal()));
			if (this.entsAFP.containsKey("" + cot.getIdEntPensionReal()))
				cot.setIdEntPension((String)this.entsAFP.get("" + cot.getIdEntPensionReal()));
			if (cot.getIdEntAFCReal() != Constants.ID_INP && this.entsAFP.containsKey("" + cot.getIdEntAFCReal()))
				cot.setIdEntAFC((String)this.entsAFP.get("" + cot.getIdEntAFCReal()));
		} else if (this.entsAPV.containsKey("" + cot.getIdEntidadAPVCReal()))
			cot.setIdEntidadAPVC((String)this.entsAPV.get("" + cot.getIdEntidadAPVCReal()));
		return cot;
	}

	/**
	 * Setea en el cotizante el nombre de la entidad del Depósito Convenido.
	 * Sólo para Remuneraciones y Depósitos Convenidos.
	 * 
	 * @param cotizacionVO
	 * @param cot
	 * @return
	 */
	private Cotizante setEntidadDeposito(CotizacionVO cotizacionVO, Cotizante cotizante) {
		if (cotizacionVO instanceof CotizacionDCVO) {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizacionVO;
			if (this.entsAPV.containsKey("" + cotizacion.getIdEntDep() ))
				cotizante.setIdEntDep((String)this.entsAPV.get("" + cotizacion.getIdEntDep()));
		} else if (cotizacionVO instanceof CotizacionREVO) {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizacionVO;
			if (this.entsAPV.containsKey("" + cotizacion.getIdEntDep() ))
				cotizante.setIdEntDep((String)this.entsAPV.get("" + cotizacion.getIdEntDep()));
		}
		return cotizante;
	}
}