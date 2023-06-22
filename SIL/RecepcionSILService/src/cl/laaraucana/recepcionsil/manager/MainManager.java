package cl.laaraucana.recepcionsil.manager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;

import com.ibm.websphere.ce.cm.DuplicateKeyException;

import cl.laaraucana.config.ibatis.DaoException;
import cl.laaraucana.recepcionsil.persistencia.DAOFactory;
import cl.laaraucana.recepcionsil.persistencia.dto.Ilfsering;
import cl.laaraucana.recepcionsil.service.vo.CamposXmlVO;
import cl.laaraucana.recepcionsil.service.vo.EntradaRecepcionSILVO;
import cl.laaraucana.recepcionsil.service.vo.HijoVO;
import cl.laaraucana.recepcionsil.service.vo.Ilf1100VO;
import cl.laaraucana.recepcionsil.service.vo.Ilf8600VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe031VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe033VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe034VO;
import cl.laaraucana.recepcionsil.service.vo.IlfeOpeVO;
import cl.laaraucana.recepcionsil.service.vo.LicenciaNivel2VO;
import cl.laaraucana.recepcionsil.service.vo.MixtaValidacionVO;
import cl.laaraucana.recepcionsil.service.vo.RemuneracionVO;
import cl.laaraucana.recepcionsil.service.vo.RemuneracionesVO;
import cl.laaraucana.util.FechaUtil;
import cl.laaraucana.util.Util;
import cl.laaraucana.util.objectvalidate.ValidaObject;
import conector.lme.ws.cliente.operador.RespuestaDetalleLicencia;

public class MainManager {
	Logger log = Logger.getLogger(this.getClass());
	private List<Ilf1100VO> rentas = new ArrayList<Ilf1100VO>();
	private int rutEmpresa;
	private String dvRutEmpresa;
	
	public void insertSAPInput(EntradaRecepcionSILVO entrada) throws Exception {
		StringBuilder builder = new StringBuilder();

		CamposXmlVO camposXmlLic = new CamposXmlVO();
		if (entrada.getConsumo() != null && LMEManager.decodeBase64XmlLME(entrada.getConsumo().getXmlBase64()).getZonaA()!= null) {
			// obtiene campos de licencia en base64
			camposXmlLic = LMEManager.obtenerCamposXML(entrada.getConsumo().getXmlBase64());
			camposXmlLic.setGlosaConsumo(entrada.getConsumo().getGlosa());
			camposXmlLic.setCodigoRespuestaConsumo(entrada.getConsumo().getCodigoRespuesta());
			// validaciones Especiales
			validacionEstado11(camposXmlLic);
			casosEspeciales(entrada);
		} 
		if (entrada.getValidacion() == null) {
			entrada.setValidacion(new MixtaValidacionVO());
		}

		// construir string***
		// campos de validacion
		builder.append(ValidaObject.objectToString(entrada.getValidacion(), entrada.getValidacion().getClass()));
		log.info("Largo registro validacion:" +  builder.length() + ", texto:" + builder);
		int old= builder.length();
		// campos de licencia
		builder.append(ValidaObject.objectToString(entrada.getLicencia(), entrada.getLicencia().getClass()));
		log.info("Largo registro licencia:" +  builder.length() + ", texto:" + builder.substring(old));
		
		old= builder.length();
		// listado de hijos (arreglo fijo de 10)
		if ( entrada.getLicencia().getHijos()==null) {
			entrada.getLicencia().setHijos(new ArrayList<HijoVO>());
		}
		for (int i = entrada.getLicencia().getHijos().size(); i < 10; i++) {
			entrada.getLicencia().getHijos().add(new HijoVO());
		}
		for (HijoVO hijo : entrada.getLicencia().getHijos()) {
			builder.append(ValidaObject.objectToString(hijo, hijo.getClass()));
			log.info("Largo registro hijo:" +  builder.length() + ", texto:" + builder.substring(old));
			old= builder.length();
		}

		// campos de licencia xml
		builder.append(ValidaObject.objectToString(camposXmlLic, camposXmlLic.getClass()));
		log.info("Largo registro consumo:" +  builder.length() + ", texto:" + builder.substring(old));
		Ilfsering ing = new Ilfsering();
		ing.setAfirut(entrada.getLicencia().getRutAfiliado().trim().replaceAll("-", ""));
		ing.setNumimpre((entrada.getLicencia().getNroLicenciaExterna().trim().concat(entrada.getLicencia().getDvNroLicenciaExterna().trim())));
		ing.setLichasfec(entrada.getLicencia().getFechaHasta());
		ing.setCodest("0");
		ing.setGloest("INGRESADO DESDE WS JAVA");
		ing.setFhingjav((Util.getFechaCompletaAs400(new Date())));
		ing.setFhingas4((Util.getFechaCompletaAs400(new Date())));
		ing.setReintento("0");
		ing.setSapcrm(builder.toString());
		ing.setFecemi(entrada.getLicencia().getFechaEmision());
		ing.setCococp(entrada.getLicencia().getCodComunaUsoCompin());
		ing.setCodprev(entrada.getLicencia().getCodigoInstPrevision());
		ing.setCaltrabaj(entrada.getLicencia().getCalidadTrabajador());
		ing.setCondurind(entrada.getLicencia().getContratoDuracionIndefinido());
		ing.setTraafiafc(entrada.getLicencia().getTrabajadorAfiliadoAFC());
		ing.setExccompin(entrada.getLicencia().getExcepcionCompin());
		if(entrada.getLicencia().getExcepcionInspTrabajo().equals("1")){
			ing.setExcconsol("1");
		}else{
			ing.setExcconsol("0");
		}
		if(entrada.getLicencia().getExcepcionMaternalSSB().equals("1") || entrada.getLicencia().getExcepcionTrabajadorNuevo().equals("1")){
			ing.setExcconsol("2");
		}
		ing.setFecrecep(entrada.getLicencia().getFechaRecepcionEmpleador());
		log.info("Insertando IlfSering, largo registro sapcrm:" +  builder.length());
		// insert table
		DAOFactory.getDaoFactory().getServIngresoDao().ingresarCadena(ing);
				
	}
	
	public void insertPre1000(EntradaRecepcionSILVO entrada) throws Exception, DaoException{
		//insert pre1000, se verifica si oficina registra operaciones con formulario proced. manual
		if(entrada.getLicencia().getMixtaOFormulario().equals("F")){
			log.info("Verificando Oficina de Ingreso para insertar Pre1000:" + entrada.getLicencia().getOficinaIngreso());
		}
		if(DAOFactory.getDaoFactory().getServIngresoDao().getOficina(Integer.parseInt(entrada.getLicencia().getOficinaIngreso()))==0 && entrada.getLicencia().getMixtaOFormulario().equals("F")){
			//se formatean algunos datos
			String rutafi= entrada.getLicencia().getRutAfiliado().trim().replaceAll("-", "");
			String rutafi_num= rutafi.substring(0, rutafi.length()-1);
			String rutafi_dv= rutafi.substring(rutafi.length()-1);
			String rutemp= entrada.getLicencia().getRutEmpresa().trim().replaceAll("-", "");
			String rutemp_num= rutemp.substring(0, rutemp.length()-1);
			String rutemp_dv= rutemp.substring(rutemp.length()-1);

			// se actualiza datos en LicenciaVO
			entrada.getLicencia().setRutAfiliado(rutafi_num);
			entrada.getLicencia().setDvRutAfiliado(rutafi_dv);
			entrada.getLicencia().setRutEmpresa(rutemp_num);
			entrada.getLicencia().setDvRutEmpresa(rutemp_dv);
			entrada.getLicencia().setFechaCreacion(FechaUtil.getFechaHoyAs400());
			entrada.getLicencia().setHoraCreacion(FechaUtil.getHoraAs400());
			entrada.getLicencia().setServicioSalud(entrada.getLicencia().getServicioSalud().trim());
			entrada.getLicencia().setIdentifUsuario(entrada.getLicencia().getIdentifUsuario().trim());
			entrada.getLicencia().setIndiceCreacion("3");
			entrada.getLicencia().setEstadoCarga("1");
			String fullname= entrada.getLicencia().getApellidoPaternoAfiliado().trim() + " " + entrada.getLicencia().getApellidoMaternoAfiliado().trim() + " " + entrada.getLicencia().getNombreAfiliado().trim();
			if(fullname.length()>36){
				fullname= fullname.substring(0, 36);
			}
			entrada.getLicencia().setFullName(fullname);
			String nroLicencia= entrada.getLicencia().getNroLicenciaExterna().trim();
			int nl= Integer.parseInt(nroLicencia);
			nroLicencia= String.valueOf(nl);
			if(nroLicencia.length()>7){
				nroLicencia= nroLicencia.substring(nroLicencia.length()-7);
			}
			entrada.getLicencia().setNroLicencia(nroLicencia);
			//Se inserta Pre1000
			log.info("Insertando Pre1000");
			DAOFactory.getDaoFactory().getServIngresoDao().ingresarPre1000(entrada.getLicencia());
		}
	}
	
	public String obtenerServicioSalud(int oficinaIngreso) throws Exception{
		String codigoSS= DAOFactory.getDaoFactory().getServIngresoDao().getServicioSalud(oficinaIngreso);
		return codigoSS;
	}
	
	public void updateIlfe8600(LicenciaNivel2VO licN2) throws Exception{
		Ilf8600VO vo8600= new Ilf8600VO();
		String rutafi= licN2.getRutAfiliado().trim().replaceAll("-", "");
		String rut_num= rutafi.substring(0, rutafi.length()-1);
		String rut_dv= rutafi.substring(rutafi.length()-1);
		
		vo8600.setRutAfiliado(Integer.parseInt(rut_num));
		vo8600.setDvRutAfiliado(rut_dv);
		vo8600.setNroLicencia(Integer.parseInt(licN2.getNroLicencia()));
		
		String rutpro= licN2.getProfesional().getRutProfesional().trim().replaceAll("-", "");
		rut_num= rutpro.substring(0, rutpro.length()-1);
		rut_dv= rutpro.substring(rutpro.length()-1);
		
		vo8600.setRutProfesional(Integer.parseInt(rut_num));
		vo8600.setDvRutProfesional(rut_dv);
		vo8600.setActividadLaboralTrabajador(licN2.getEmpleador().getActividadLaboralTrabajador());
		vo8600.setOcupacionTrabajador(licN2.getEmpleador().getOcupacionTrabajador());
		vo8600.setInicioTramiteInvalidez(licN2.getTipo().getInicioTramiteInvalidez());
		vo8600.setRecuperabilidad(licN2.getTipo().getRecuperabilidad());
		vo8600.setTipoReposo(Integer.parseInt(licN2.getReposo().getTipoReposo()));
		vo8600.setJornadaReposo(licN2.getReposo().getJornadaReposo());
		vo8600.setLugarReposo(licN2.getReposo().getLugarReposo());
		vo8600.setJustificarOtro(licN2.getReposo().getJustificarOtro());
		vo8600.setDireccionReposo(licN2.getReposo().getDireccionReposo());
		vo8600.setTelefonoReposo(licN2.getReposo().getTelefonoReposo());
		vo8600.setMesConcepcion(Integer.parseInt(licN2.getTipo().getFechaConcepcion().substring(4, 6)));
		String estado="3";
		if(licN2.getMixtaOFormulario().equals("F")){
			estado="2";
		}
		vo8600.setEstadoLicencia(estado);
		vo8600.setDireccionEmpleador(licN2.getEmpleador().getDireccionEmpleador());
		vo8600.setTelefonoEmpleador(licN2.getEmpleador().getTelefonoEmpleador());
		vo8600.setRentaImponible(Integer.parseInt(licN2.getRemuneraciones().getRemuneracionImponibleAFP60()));
		vo8600.setRemuneracionMesAnterior(Integer.parseInt(licN2.getRemuneraciones().getRemuneracionImponibleAFC90()));
		vo8600.setFechaAfiliacion(Integer.parseInt(licN2.getEntidadPrevisional().getFechaPrimeraAfiliacion()));
		vo8600.setFechaContrato(Integer.parseInt(licN2.getEntidadPrevisional().getFechaContratoTrabajo()));
		int i=1;
		for (Iterator iterator = licN2.getRemuneraciones().getInformeRemuneraciones().iterator(); iterator.hasNext();) {
			RemuneracionVO remuVO = (RemuneracionVO) iterator.next();
			String periodoRenta= remuVO.getAnioRemuneracion() + "" + remuVO.getMesRemuneracion() ;
			switch (i) {
			case 1: vo8600.setPeriodoRenta1(Integer.parseInt(periodoRenta));
			break;
			case 2: vo8600.setPeriodoRenta2(Integer.parseInt(periodoRenta));
			break;
			case 3: vo8600.setPeriodoRenta3(Integer.parseInt(periodoRenta));
			break;
			case 4: vo8600.setPeriodoRenta4(Integer.parseInt(periodoRenta));
			break;
			case 5: vo8600.setPeriodoRenta5(Integer.parseInt(periodoRenta));
			break;
			case 6: vo8600.setPeriodoRenta6(Integer.parseInt(periodoRenta));
			break;
			}
			i++;
		}
		
		//insert 8600
		log.info("Updating 8600");
		DAOFactory.getDaoFactory().getServIngresoDao().update8600(vo8600);
	}
	
	public void insertIlfe1100(LicenciaNivel2VO licN2) throws Exception{
		RemuneracionesVO rentas= licN2.getRemuneraciones();
		String rutafi= licN2.getRutAfiliado().trim().replaceAll("-", "");
		String rutafi_num= rutafi.substring(0, rutafi.length()-1);
		
		HashMap<String, Integer> param= new HashMap<String, Integer>();
		param.put("rutAfiliado", new Integer(rutafi_num));
		param.put("nroLicencia", new Integer(licN2.getNroLicencia()));
		HashMap<String, String> empleador= DAOFactory.getDaoFactory().getServIngresoDao().getEmpleador(param);
		if(empleador!=null){
			setRutEmpresa(new BigDecimal(empleador.get("EMPRUT")).intValue());
			setDvRutEmpresa(empleador.get("EMPRUTDV"));
		}
		for (Iterator iterator = rentas.getInformeRemuneraciones().iterator(); iterator.hasNext();) {
			RemuneracionVO remuVO = (RemuneracionVO) iterator.next();
			Ilf1100VO vo1100= new Ilf1100VO();
			vo1100.setRutAfiliado(Integer.parseInt(rutafi_num));
			vo1100.setRutEmpleador(getRutEmpresa());
			vo1100.setPeriodoRenta(Integer.parseInt(remuVO.getAnioRemuneracion() + "" + remuVO.getMesRemuneracion()));
			vo1100.setCodigoEntidadPrevisional(Integer.parseInt(remuVO.getCodigoEntidadPrevisional()));
			vo1100.setTotalRemuneraciones(Integer.parseInt(remuVO.getTotalRemuneraciones()));
			vo1100.setMontoSubsidio(Integer.parseInt(remuVO.getMontoSubsidio()));
			vo1100.setNumeroDiasSubsidio(Integer.parseInt(remuVO.getNumeroDiasSubsidio()));
			
			//insert 1100
			try {
				DAOFactory.getDaoFactory().getServIngresoDao().ingresar1100(vo1100);
				//se guarda objeto para insertar en 034
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.warn("Registro duplicado en 1100, afiliado:" + rutafi_num + ", empresa:" + getRutEmpresa());
			}
			getRentas().add(vo1100);
		}
	}
	
	public Ilfe031VO insertIlf031(LicenciaNivel2VO licN2, HashMap<String, String> param) throws Exception{
		//insert 031
		/*String rutafi= licN2.getRutAfiliado().trim().replaceAll("-", "");
		String rutafi_num= rutafi.substring(0, rutafi.length()-1);
		String rutafifull= "0000000000" + rutafi;
		rutafifull= rutafifull.substring(rutafifull.length()-rutafi.length()-1);
		*/
		//Obteniendo datos de Empresa
		/*HashMap<String, String> param= new HashMap<String, String>();
		param.put("rutAfiliado", rutafi_num);
		param.put("rutAfiliadoFull", rutafifull);
		if(licN2.getMixtaOFormulario().equals("F")){
			param.put("nroLicencia",licN2.getNroLicencia());
		}else{
			param.put("nroLicencia", licN2.getNroLicenciaExterno());
			param.put("nroLicenciaExterno", licN2.getNroLicenciaExterno());
		}*/
		//Obteniendo datos de Empresa
		param.put("rutEmpresa", String.valueOf(getRutEmpresa()));
		
		//Obteniendo datos de Ilfsering
		HashMap<String, BigDecimal> seringMap= DAOFactory.getDaoFactory().getServIngresoDao().getIlfSering(param);
		
		if(seringMap.get("EXCCONSOL").intValue()==1){
			log.info("Licencia " + licN2.getNroLicencia() + " No se debe Consolidar");
			return null;
		}
		String nomEmpresa= DAOFactory.getDaoFactory().getServIngresoDao().getNomEmpresa(getRutEmpresa());
		
		//Obteniendo datos de Operador
		IlfeOpeVO opeVO= (IlfeOpeVO)DAOFactory.getDaoFactory().getServIngresoDao().getIlfeOpe(param);
		
		Ilfe031VO vo31= new Ilfe031VO();
		vo31.setIdeOpe(opeVO.getIdeOpe());
		vo31.setCodOpe(opeVO.getCodOpe());
		vo31.setRutAfiliado(Integer.parseInt(param.get("rutAfiliado")));
		vo31.setNroLicencia(Integer.parseInt(licN2.getNroLicenciaExterno()));
		vo31.setDvNroLicencia("");
		vo31.setEnviada("1");
		vo31.setFechaProceso(Integer.parseInt(FechaUtil.getFechaHoyAs400()));
		vo31.setRespuestaWS("");
		vo31.setMarcaProcesado("N");
		vo31.setNombreEmpleador(nomEmpresa);
		vo31.setRutEmpleador(getRutEmpresa());
		vo31.setDvRutEmpleador(getDvRutEmpresa());
		vo31.setFechaRecepcionEmpresa(seringMap.get("FECRECEP").intValue());
		vo31.setDireccionEmpleador(licN2.getEmpleador().getDireccionEmpleador());
		vo31.setNumeroDireccionEmpleador(licN2.getEmpleador().getNumeroEmpleador());
		vo31.setDeptoDireccionEmpleador(licN2.getEmpleador().getDeptoEmpleador());
		vo31.setTelefonoEmpleador((licN2.getEmpleador().getTelefonoEmpleador()));
		vo31.setCiudadEmpleador(licN2.getEmpleador().getCiudadEmpleador());
		vo31.setPaisEmpleador("CHILE");
		vo31.setComunaEmpleador(licN2.getEmpleador().getComunaEmpleador());
		vo31.setCodigoComunaCompin(seringMap.get("COCOCP").intValue());
		vo31.setCodigoActividadLaboral(Integer.parseInt(licN2.getEmpleador().getActividadLaboralTrabajador()));
		vo31.setCodigoOcupacion(Integer.parseInt(licN2.getEmpleador().getOcupacionTrabajador()));
		vo31.setOtraOcupacion(licN2.getEmpleador().getOcupacionTrabajadorOtro());
		vo31.setFechaRecepcionCaja(Integer.parseInt(FechaUtil.getFechaHoyAs400()));
		vo31.setCodigoTipoRegimenPrevisional("1");
		if(seringMap.get("CODPREV").intValue()>1000 && seringMap.get("CODPREV").intValue()<2000){
			vo31.setCodigoTipoRegimenPrevisional("2");
		}
		vo31.setCodigoEntidadPrevisonal(seringMap.get("CODPREV").intValue());
		vo31.setCodigoLetraCaja(licN2.getEntidadPrevisional().getLetraCajaPrevisional());
		//Se rescata Nombre Entidad
		String codent= seringMap.get("CODPREV").toString();
		if(Integer.parseInt(codent)>1000 && Integer.parseInt(codent)<2000){
			codent= "1" + codent.substring(2);
		}
		String nombreEntidadPrevisional= DAOFactory.getDaoFactory().getServIngresoDao().getNombreEntidad(codent).trim();
		if(nombreEntidadPrevisional.length()>20){
			nombreEntidadPrevisional.substring(0, 20);
		}
		vo31.setNombreEntidadPrevisional(nombreEntidadPrevisional);
		vo31.setCodigoCalidadTrabajador(seringMap.get("CALTRABAJ").intValue());
		vo31.setTrabajadorAfiliadoAFC(seringMap.get("TRAAFIAFC").intValue());
		vo31.setContratoDuracionIndefinido(seringMap.get("CONDURIND").intValue());
		vo31.setFechaAfiliacion(Integer.parseInt(licN2.getEntidadPrevisional().getFechaPrimeraAfiliacion()));
		vo31.setFechaContrato(Integer.parseInt(licN2.getEntidadPrevisional().getFechaContratoTrabajo()));
		vo31.setCodigoEntidadPagadora("C");
		vo31.setNombreEntidadPagadora("CCAF LA ARAUCANA");
		vo31.setRentaImponible(Integer.parseInt(licN2.getRemuneraciones().getRemuneracionImponibleAFP60())); //PENDIENTE
		
		vo31.setLicenciasAnteriores("NO");		
		int countLic = DAOFactory.getDaoFactory().getServIngresoDao().getTieneLicenciasAnteriores(param);
		if(countLic>0){
			vo31.setLicenciasAnteriores("SI");
		}
		
		vo31.setCodCcaf(opeVO.getCodCcaf().trim()); //CODCCAF
		vo31.setPwdCcaf(opeVO.getPwdCcaf().trim()); //PWDCCAF
		vo31.setUrlOpe(opeVO.getUrlOpe().trim()); //URLOPE
		vo31.setUsuarioModifica("LME");
		vo31.setNombreConsumo("");
		vo31.setHoraEnvio(Integer.parseInt(FechaUtil.getHoraAs400()));
		vo31.setGlosaRespuesta("");
		vo31.setFechaRespuesta(0); //FECRESP
		vo31.setHoraRespuesta(0); //HORRESP
		log.info("Insertando 031");
		DAOFactory.getDaoFactory().getServIngresoDao().ingresar031(vo31);
		return vo31;
	}
	
	public List<Ilfe033VO> insertIlf033() throws Exception{
		List<Ilfe033VO> list033= new ArrayList<Ilfe033VO>();
		
		for (Iterator rentas = getRentas().iterator(); rentas.hasNext();) {
			Ilf1100VO rentaVO = (Ilf1100VO) rentas.next();
			Ilfe033VO vo33= new Ilfe033VO();
			vo33.setRutAfiliado(rentaVO.getRutAfiliado());
			vo33.setRutEmpleador(rentaVO.getRutEmpleador());
			vo33.setCodigoEntidadPrevisional(rentaVO.getCodigoEntidadPrevisional());
			vo33.setPeriodoRenta(rentaVO.getPeriodoRenta());
			vo33.setNumeroDiasRemuneracion(rentaVO.getNumeroDiasRemuneracion());
			vo33.setTotalRemuneraciones(rentaVO.getTotalRemuneraciones());
			vo33.setMontoSubsidio(rentaVO.getMontoSubsidio());
			vo33.setNumeroDiasSubsidio(rentaVO.getNumeroDiasSubsidio());
			vo33.setFechaProceso(Integer.parseInt(FechaUtil.getFechaHoyAs400()));
			try {
				log.info("Insertando 033");
				DAOFactory.getDaoFactory().getServIngresoDao().ingresar033(vo33);
			} catch (Exception e) {
				log.warn("Registro dulicado en 033, rutafi= " + rentaVO.getRutAfiliado() + ", rutemp=" + rentaVO.getRutEmpleador() + ", periodo= " + rentaVO.getPeriodoRenta());
			}
			list033.add(vo33);
		}
		return list033;
	}
	
	public List<Ilfe034VO> insertIlf034(LicenciaNivel2VO licN2) throws Exception{
		String rutafi= licN2.getRutAfiliado().trim().replaceAll("-", "");
		String rutafi_num= rutafi.substring(0, rutafi.length()-1);
		
		HashMap<String, Integer> param= new HashMap<String, Integer>();
		param.put("rutAfiliado", new Integer(rutafi_num));
		param.put("rutEmpresa", getRutEmpresa());
		
		List<HashMap<String, BigDecimal>> listalic= DAOFactory.getDaoFactory().getServIngresoDao().getLicAnteriores(param);
		List<Ilfe034VO> list034= new ArrayList<Ilfe034VO>();
		for (Iterator iterator = listalic.iterator(); iterator.hasNext();) {
			HashMap<String, BigDecimal> lic = (HashMap<String, BigDecimal>) iterator.next();
			Ilfe034VO vo34= new Ilfe034VO();
			vo34.setRutAfiliado(Integer.parseInt(rutafi_num));
			vo34.setRutEmpleador(getRutEmpresa());
			vo34.setNroLicencia(Integer.parseInt(licN2.getNroLicencia()));
			vo34.setNumeroDias(lic.get("LICDIAS").intValue());
			vo34.setFechaDesde(lic.get("LICDESFEC").intValue());
			vo34.setFechaHasta(lic.get("LICHASFEC").intValue());
			log.info("Insertando 034");
			DAOFactory.getDaoFactory().getServIngresoDao().ingresar034(vo34);
			list034.add(vo34);
		}	
		return list034;
	}
	
	public void updateIlfe1010(LicenciaNivel2VO licN2) throws Exception{
		String rutafi= licN2.getRutAfiliado().trim().replaceAll("-", "");
		String rutafi_num= rutafi.substring(0, rutafi.length()-1);
		
		String rutpro= licN2.getProfesional().getRutProfesional().trim().replaceAll("-", "");
		String rutpro_num= rutpro.substring(0, rutpro.length()-1);
		String rutpro_dv= rutpro.substring(rutpro.length()-1);
		
		HashMap<String, Object> param= new HashMap<String, Object>();
		param.put("rutAfiliado", new Integer(rutafi_num));
		param.put("nroLicencia", new Integer(licN2.getNroLicencia()));
		param.put("rutProfesional", new Integer(rutpro_num));
		param.put("dvRutProfesional", rutpro_dv);
		param.put("fechaContrato", new Integer(licN2.getEntidadPrevisional().getFechaContratoTrabajo()));
		log.info("Updating 1010");
		DAOFactory.getDaoFactory().getServIngresoDao().update1010(param);
		
	}
	
	public void updateIlfe031(Ilfe031VO vo31) throws Exception {
		DAOFactory.getDaoFactory().getServIngresoDao().update031(vo31);
	}
	
	public int buscarNumeroLicenciaCaja(int numeroLicenciaOperador) throws Exception {
		
		return DAOFactory.getDaoFactory().getServIngresoDao().getNumeroLicenciaCaja(numeroLicenciaOperador);
	}
	
	private void casosEspeciales(EntradaRecepcionSILVO entrada) throws Exception {
		RespuestaDetalleLicencia respuesta = LMEManager.decodeBase64XmlLME(entrada.getConsumo().getXmlBase64());
		if (entrada.getLicencia().getMixtaOFormulario().equals("M")) {
			
			// ------------------
			String edad = String.valueOf(respuesta.getZonaA().getZONAA1().getTrabajador().getEdad()).trim();
			if (edad != null && !edad.isEmpty())
				entrada.getLicencia().setEdadAfiliado(edad);
			// ------------------
			String sexo = respuesta.getZonaA().getZONAA1().getTrabajador().getSexo().toString().trim();
			if (sexo != null && !sexo.isEmpty())
				entrada.getLicencia().setSexoAfiliado(sexo);
			// ------------------
			String tipoReposo = String.valueOf(respuesta.getZonaA().getZONAA4().getCodigoTipoReposo()).trim();
			if (tipoReposo != null && !tipoReposo.isEmpty())
				entrada.getLicencia().setTipoReposo(tipoReposo);
			// ------------------
			String tipoLicencia = String.valueOf(respuesta.getZonaA().getZONAA3().getCodigoTipoLicencia()).trim();
			if (tipoLicencia != null && !tipoLicencia.isEmpty())
				entrada.getLicencia().setTipoLicencia(tipoLicencia);

		}

	}
	
	public boolean insertIlfPersist(LicenciaNivel2VO licN2, HashMap param) throws Exception{
		
		
		boolean resultado= false;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			//byte[] data = org.apache.commons.lang.SerializationUtils.serialize(licN2);
			out = new ObjectOutputStream(bos);   
			out.writeObject(licN2);
			out.flush();
			byte[] licbytes = bos.toByteArray();
			param.put("licencia", licbytes);
			DAOFactory.getDaoFactory().getServIngresoDao().insertIlfPersist(param);
			resultado= true;
			//se guarda objeto 
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Error:" + e.getMessage());
		}finally {
			try {
				bos.close();
			} catch (IOException ex) {
				// ignore close exception
			}
		}
	return resultado;	
}
	
	private void validacionEstado11(CamposXmlVO camposXmlLic) throws Exception {
		if (!camposXmlLic.getEstadoLicencia().trim().equals("11")) {
			throw new Exception("la licencia no tiene estado 11");
		}

	}
	
	/**
	 * @return the rentas
	 */
	public List<Ilf1100VO> getRentas() {
		return rentas;
	}

	/**
	 * @param rentas the rentas to set
	 */
	public void setRentas(List<Ilf1100VO> rentas) {
		this.rentas = rentas;
	}

	/**
	 * @return the rutEmpresa
	 */
	public int getRutEmpresa() {
		return rutEmpresa;
	}

	/**
	 * @param rutEmpresa the rutEmpresa to set
	 */
	public void setRutEmpresa(int rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	/**
	 * @return the dvRutEmpresa
	 */
	public String getDvRutEmpresa() {
		return dvRutEmpresa;
	}

	/**
	 * @param dvRutEmpresa the dvRutEmpresa to set
	 */
	public void setDvRutEmpresa(String dvRutEmpresa) {
		this.dvRutEmpresa = dvRutEmpresa;
	}

	
	
}
