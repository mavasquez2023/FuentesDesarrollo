package cl.laaraucana.recepcionsil.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.recepcionsil.manager.MainManager;
import cl.laaraucana.recepcionsil.persistencia.DAOFactory;
import cl.laaraucana.recepcionsil.service.vo.EntradaRecepcionSILVO;
import cl.laaraucana.recepcionsil.service.vo.HijoVO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe031VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe033VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe034VO;
import cl.laaraucana.recepcionsil.service.vo.LicenciaNivel2VO;
import cl.laaraucana.recepcionsil.service.vo.RemuneracionVO;
import cl.laaraucana.recepcionsil.service.vo.RespuestaVO;
import cl.laaraucana.recepcionsil.service.vo.SalidaRecepcionSILVO;
import cl.laaraucana.util.objectvalidate.ValidaObject;
import cl.laaraucana.util.objectvalidate.ValidateResultVO;

public class RecepcionSILService {
	Logger log = Logger.getLogger(this.getClass());

	public SalidaRecepcionSILVO ingresoLicencia(EntradaRecepcionSILVO entrada) {
		//crear objetos
		MainManager mg = new MainManager();
		SalidaRecepcionSILVO salida = new SalidaRecepcionSILVO();
		RespuestaVO resp = new RespuestaVO();
		ValidateResultVO resValLic = new ValidateResultVO(false);
		ValidateResultVO resValVal = new ValidateResultVO(false);
		ValidateResultVO resValCon = new ValidateResultVO(false);
		ValidateResultVO resValHijo = new ValidateResultVO(false);
		log.info("Entrada XML ingreso licencia");
		try {
			//valida licencia null
			if (entrada.getLicencia()==null) {
				throw new Exception("Objeto 'licencia' vacío");
			}
			
			//Seteo campos Deprecados y/o nuevos
			{
				if(entrada.getLicencia().getTipoReposo()==null || entrada.getLicencia().getTipoReposo().equals("")){
					entrada.getLicencia().setTipoReposo("1");
				}
				if(entrada.getLicencia().getTrabajadorAfiliadoAFC()==null || entrada.getLicencia().getTrabajadorAfiliadoAFC().equals("")){
					entrada.getLicencia().setTrabajadorAfiliadoAFC("1");
				}
				if(entrada.getLicencia().getContratoDuracionIndefinido()==null || entrada.getLicencia().getContratoDuracionIndefinido().equals("")){
					entrada.getLicencia().setContratoDuracionIndefinido("1");
				}
				if(entrada.getLicencia().getExcepcionCompin()==null || entrada.getLicencia().getExcepcionCompin().equals("")){
					entrada.getLicencia().setExcepcionCompin("0");
				}
				if(entrada.getLicencia().getExcepcionInspTrabajo()==null || entrada.getLicencia().getExcepcionInspTrabajo().equals("")){
					entrada.getLicencia().setExcepcionInspTrabajo("0");
				}
				if(entrada.getLicencia().getExcepcionMaternalSSB()==null || entrada.getLicencia().getExcepcionMaternalSSB().equals("")){
					entrada.getLicencia().setExcepcionMaternalSSB("0");
				}
				if(entrada.getLicencia().getExcepcionTrabajadorNuevo()==null || entrada.getLicencia().getExcepcionTrabajadorNuevo().equals("")){
					entrada.getLicencia().setExcepcionTrabajadorNuevo("0");
				}
				if(entrada.getLicencia().getFechaAfiliacion()==null || entrada.getLicencia().getFechaAfiliacion().equals("")){
					entrada.getLicencia().setFechaAfiliacion("19000101");
				}
				if(entrada.getLicencia().getFechaContrato()==null || entrada.getLicencia().getFechaContrato().equals("")){
					entrada.getLicencia().setFechaContrato("19000101");
				}
				if(entrada.getLicencia().getFechaRecepcionEmpleador()==null || entrada.getLicencia().getFechaRecepcionEmpleador().equals("")){
					entrada.getLicencia().setFechaRecepcionEmpleador("19000101");
				}
				if(entrada.getLicencia().getExcepcionInspTrabajo().equals("1")){
					entrada.getLicencia().setCodigoInstPrevision("0");
					entrada.getLicencia().setCalidadTrabajador("1");
					
				}
			}
			
			if (entrada.getLicencia().getMixtaOFormulario().equals("F")) {
				//campos que vienen vacios
				if (entrada.getLicencia().getDvNroLicenciaExterna()==null) {
					entrada.getLicencia().setDvNroLicenciaExterna(" ");
				
				}if(entrada.getLicencia().getOperador()==null){
					entrada.getLicencia().setOperador("0");
				}
				if (entrada.getLicencia().getIndValidacion()==null) {
					entrada.getLicencia().setIndValidacion(" ");	
				}
				
				resValLic = ValidaObject.validar(entrada.getLicencia(), entrada.getLicencia().getClass());
				resValVal.setValid(true);
				resValCon.setValid(true);				
			}else{
				//valida consumo null
				if (entrada.getConsumo()==null) {
					log.warn("Objeto 'consumo' vacío");
					throw new Exception("Objeto 'consumo' vacío");
				}//valida validacion null
				if (entrada.getValidacion()==null) {
					log.warn("Objeto 'validacion' vacío");
					throw new Exception("Objeto 'validacion' vacío");
				}
				if(entrada.getLicencia().getOperador()!=null && entrada.getLicencia().getOperador().equals("3")){
					entrada.getLicencia().setOperador("2");
				}
				
				//campos que vienen vacios
				if (entrada.getLicencia().getColor()==null) {
					entrada.getLicencia().setColor("0");
				}
				if (entrada.getLicencia().getIndicadorVisada()==null) {
					entrada.getLicencia().setIndicadorVisada(" ");
				}
				
				resValLic = ValidaObject.validar(entrada.getLicencia(), entrada.getLicencia().getClass());
				resValVal = ValidaObject.validar(entrada.getValidacion(), entrada.getValidacion().getClass());
				if(entrada.getValidacion().getCodigoRespuesta()!=null && entrada.getValidacion().getCodigoRespuesta().trim().equals("1")){
					resValVal.getDetailValidate().clear();
					resValVal.setValid(true);
				}
				resValCon = ValidaObject.validar(entrada.getConsumo(), entrada.getConsumo().getClass());
			}
			
			//validar hijos
		
			List<HijoVO> lh = entrada.getLicencia().getHijos();
			if (lh!=null && lh.size()!=0) {
				for (int i = 0; i < lh.size(); i++) {
					HijoVO hijoVO = lh.get(i);
					ValidateResultVO res = ValidaObject.validar(hijoVO, hijoVO.getClass());
					resValHijo = res;
					if (!res.isValid()) {
						break;
					}
				}
			}else{
				resValHijo.setValid(true);
			}

			//evaluar resultado de validaciones
			if (!resValLic.isValid() || !resValVal.isValid() || !resValCon.isValid() || !resValHijo.isValid()) {
				ArrayList<String> listErrores = resValLic.getDetailValidate();
				listErrores.addAll(resValVal.getDetailValidate());
				listErrores.addAll(resValCon.getDetailValidate());
				listErrores.addAll(resValHijo.getDetailValidate());
				String glosa = "";
				for (String string : listErrores) {
					glosa = glosa.concat(string).concat("\n");
				}

				resp.setCodigoRespuesta("5");
				resp.setGlosa(glosa);
				salida.setRespuesta(resp);
				
				log.error("Datos ingresados no validos: \n"+glosa);
				return salida;
			}
			//ejecuta Manager
			//Obtener ServicioSalud asociada aa Oficina de Ingreso
			String servicioSalud= mg.obtenerServicioSalud(Integer.parseInt(entrada.getLicencia().getOficinaIngreso()));
			entrada.getLicencia().setServicioSalud(servicioSalud);
			//Insert ILFSERING
			mg.insertSAPInput(entrada);
			//Insert Pre1000
			String usu= entrada.getLicencia().getIdentifUsuario();
			if(usu.length()>10){
				entrada.getLicencia().setIdentifUsuario(usu.substring(0, 10));
			}
			mg.insertPre1000(entrada);
			
			resp.setCodigoRespuesta("3");
			resp.setGlosa("Ingreso exitoso");
			log.info("Ingreso Exitoso");

		} catch (Exception e) {
			e.printStackTrace();
			resp.setCodigoRespuesta("5");
			resp.setGlosa("EXCEPTION: No es posible ingresar el registro: " + e.getMessage());
			log.error("Error en el ingreso: "+e.getMessage());
			log.error("cause: "+e.getCause());
		}
		salida.setRespuesta(resp);
		return salida;
	}
	
	public SalidaRecepcionSILVO completarLicencia(LicenciaNivel2VO entrada) {
		MainManager mg = new MainManager();
		
		if(entrada.getTipo().getFechaConcepcion()==null || entrada.getTipo().getFechaConcepcion().equals("")){
			entrada.getTipo().setFechaConcepcion("19000101");
		}
		if(entrada.getReposo().getJornadaReposo()==null || entrada.getReposo().getJornadaReposo().equals("")){
			entrada.getReposo().setJornadaReposo(" ");
		}
		if(entrada.getReposo().getJustificarOtro()==null || entrada.getReposo().getJustificarOtro().equals("")){
			entrada.getReposo().setJustificarOtro(" ");
		}
		if(entrada.getProfesional().getFaxProfesional()==null || entrada.getProfesional().getFaxProfesional().equals("")){
			entrada.getProfesional().setFaxProfesional(" ");
		}
		if(entrada.getReposo().getTipoReposo()==null || entrada.getReposo().getTipoReposo().equals("")){
			entrada.getReposo().setTipoReposo("1");
		}
		if(entrada.getProfesional().getApellidoPaternoProfesional()==null || entrada.getProfesional().getApellidoPaternoProfesional().equals("")){
			entrada.getProfesional().setApellidoPaternoProfesional("PP");
		}
		if(entrada.getProfesional().getApellidoMaternoProfesional()==null || entrada.getProfesional().getApellidoMaternoProfesional().equals("")){
			entrada.getProfesional().setApellidoMaternoProfesional("MM");
		}
		if(entrada.getProfesional().getNombresProfesional()==null || entrada.getProfesional().getNombresProfesional().equals("")){
			entrada.getProfesional().setNombresProfesional("NN");
		}
		if(entrada.getProfesional().getRutProfesional()==null || entrada.getProfesional().getRutProfesional().equals("")){
			entrada.getProfesional().setRutProfesional("1-9");
		}
		if(entrada.getProfesional().getTipoProfesional()==null || entrada.getProfesional().getTipoProfesional().equals("")){
			entrada.getProfesional().setTipoProfesional(" ");
		}
		if(entrada.getProfesional().getRegistroColegioProfesional()==null || entrada.getProfesional().getRegistroColegioProfesional().equals("")){
			entrada.getProfesional().setRegistroColegioProfesional("0");
		}
		if(entrada.getProfesional().getRegistroColegioProfesional()==null || entrada.getProfesional().getRegistroColegioProfesional().equals("")){
			entrada.getProfesional().setRegistroColegioProfesional("0");
		}
		if(entrada.getEmpleador().getComunaEmpleador()==null || entrada.getEmpleador().getComunaEmpleador().equals("")){
			entrada.getEmpleador().setComunaEmpleador("0");
		}
		if(entrada.getEmpleador().getComunaEmpleador().length()>5){
			entrada.getEmpleador().setComunaEmpleador(String.valueOf(Integer.parseInt(entrada.getEmpleador().getComunaEmpleador())));
		}
		if(entrada.getRemuneraciones().getRemuneracionImponibleAFC90()==null || entrada.getRemuneraciones().getRemuneracionImponibleAFC90().equals("")){
			entrada.getRemuneraciones().setRemuneracionImponibleAFC90("0");
		}
		if(entrada.getRemuneraciones().getRemuneracionImponibleAFP60()==null || entrada.getRemuneraciones().getRemuneracionImponibleAFP60().equals("")){
			entrada.getRemuneraciones().setRemuneracionImponibleAFP60("0");
		}
		
		String rutafi= entrada.getRutAfiliado().trim().replaceAll("-", "");
		String rutafi_num= rutafi.substring(0, rutafi.length()-1);
		String ceros= "0000000000";
		ceros= ceros.substring(0, 10-rutafi.length());
		String rutafifull= ceros + rutafi;
		
		//Definiendo parametros
		HashMap<String, String> param= new HashMap<String, String>();
		param.put("rutAfiliado", rutafi_num);
		param.put("rutAfiliadoFull", rutafifull);
		
		
		ValidateResultVO resValLic = new ValidateResultVO(false);
		ValidateResultVO resValTip = new ValidateResultVO(false);
		ValidateResultVO resValRep = new ValidateResultVO(false);
		ValidateResultVO resValPro = new ValidateResultVO(false);
		ValidateResultVO resValEmp = new ValidateResultVO(false);
		ValidateResultVO resValEnt = new ValidateResultVO(false);
		ValidateResultVO resValRems = new ValidateResultVO(false);
		ValidateResultVO resValRem = new ValidateResultVO(false);		
		SalidaRecepcionSILVO salida = new SalidaRecepcionSILVO();
		RespuestaVO resp = new RespuestaVO();
		log.info("Entrada XML completar licencia (Nivel2)");
		
		try {
			if(entrada.getTipo().getFechaConcepcion()==null || entrada.getTipo().getFechaConcepcion().equals("")){
				entrada.getTipo().setFechaConcepcion("19000101");
			}
			
			resValLic = ValidaObject.validar(entrada, entrada.getClass());
			resValTip = ValidaObject.validar(entrada.getTipo(), entrada.getTipo().getClass());
			resValRep = ValidaObject.validar(entrada.getReposo(), entrada.getReposo().getClass());
			resValPro = ValidaObject.validar(entrada.getProfesional(), entrada.getProfesional().getClass());
			resValEmp = ValidaObject.validar(entrada.getEmpleador(), entrada.getEmpleador().getClass());
			resValEnt = ValidaObject.validar(entrada.getEntidadPrevisional(), entrada.getEntidadPrevisional().getClass());
			resValRems = ValidaObject.validar(entrada.getRemuneraciones(), entrada.getRemuneraciones().getClass());
			
			List<RemuneracionVO> lr = entrada.getRemuneraciones().getInformeRemuneraciones();

			for (int i = 0; i < lr.size(); i++) {
				RemuneracionVO remuVO = lr.get(i);
				if(remuVO.getMontoSubsidio()==null || remuVO.getMontoSubsidio().equals("")){
					remuVO.setMontoSubsidio("0");
				}
				if(remuVO.getNumeroDiasSubsidio()==null || remuVO.getNumeroDiasSubsidio().equals("")){
					remuVO.setNumeroDiasSubsidio("0");
				}
				ValidateResultVO res = ValidaObject.validar(remuVO, remuVO.getClass());
				resValRem = res;
				if (!res.isValid()) {
					break;
				}
			}

			//evaluar resultado de validaciones
			if (!resValLic.isValid() || !resValTip.isValid() || !resValRep.isValid() || !resValPro.isValid() || !resValEmp.isValid() || !resValEnt.isValid() || !resValRems.isValid()  || !resValRem.isValid()) {
				ArrayList<String> listErrores = resValLic.getDetailValidate();
				listErrores.addAll(resValTip.getDetailValidate());
				listErrores.addAll(resValRep.getDetailValidate());
				listErrores.addAll(resValPro.getDetailValidate());
				listErrores.addAll(resValEmp.getDetailValidate());
				listErrores.addAll(resValEnt.getDetailValidate());
				listErrores.addAll(resValRems.getDetailValidate());
				listErrores.addAll(resValRem.getDetailValidate());
				String glosa = "";
				for (String string : listErrores) {
					glosa = glosa.concat(string).concat("\n");
				}

				resp.setCodigoRespuesta("5");
				resp.setGlosa(glosa);
				salida.setRespuesta(resp);
				
				log.error("Datos ingresados no validos: "+glosa);
				return salida;
			}

			
			if(entrada.getMixtaOFormulario().equals("M")){
				int nroLicenciaCaja= mg.buscarNumeroLicenciaCaja(Integer.parseInt(entrada.getNroLicencia()));
				if(nroLicenciaCaja==0){
					throw new Exception();
				}
				entrada.setNroLicenciaExterno(String.valueOf(entrada.getNroLicencia()));
				entrada.setNroLicencia(String.valueOf(nroLicenciaCaja));
				
				param.put("nroLicencia", entrada.getNroLicenciaExterno());
				param.put("nroLicenciaExterno", entrada.getNroLicenciaExterno());
			}else{
				param.put("nroLicencia",entrada.getNroLicencia());
			}
			
			//Obteniendo datos de Ilfsering para validar si existe datos Servicio1, sino persistir
			try {
				HashMap<String, BigDecimal> seringMap= DAOFactory.getDaoFactory().getServIngresoDao().getIlfSering(param);
				if(seringMap== null || seringMap.get("CODEST").intValue()==0){
					mg.insertIlfPersist(entrada, param);
					resp.setCodigoRespuesta("3");
					resp.setGlosa("Ingreso exitoso:" + "");
					log.info("Ingreso Exitoso" + "");
					salida.setRespuesta(resp);
					return salida;
				}
			}  catch (Exception e1) {
				resp.setCodigoRespuesta("5");
				resp.setGlosa("Error conectando a ILFSering" + e1.getMessage());
				log.info("Error conectando a ILFSering" + e1.getMessage());
				salida.setRespuesta(resp);
				e1.printStackTrace();
				return salida;
				
			}
			
			//insertar 8600
			mg.updateIlfe8600(entrada);
			//insertar 1100
			mg.insertIlfe1100(entrada);
			//update 1010
			mg.updateIlfe1010(entrada);
			
			Ilfe031VO vo31;
			List<Ilfe033VO>  listvo33;
			List<Ilfe034VO> listvo34;
			//Enviar Zona C para Mixtas
			if(entrada.getMixtaOFormulario().equals("M")){
				//insertar 031
				vo31= mg.insertIlf031(entrada, param);
				if(vo31!=null){
					//insertar 033
					listvo33= mg.insertIlf033();
					//insertar 034
					listvo34= mg.insertIlf034(entrada);

					//Enviar Zona C
					ConsumoOperadorService consumo= new ConsumoOperadorService();
					boolean exito= consumo.enviarZonaC(vo31, listvo33, listvo34);
					if(exito){
						mg.updateIlfe031(vo31);
					}
				}
			}
			
			
			resp.setCodigoRespuesta("3");
			resp.setGlosa("Ingreso exitoso:" + "");
			log.info("Ingreso Exitoso" + "");
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCodigoRespuesta("5");
			resp.setGlosa("EXCEPTION: No es posible completar el registro: " + e.getMessage());
			log.error("Error en el ingreso: "+e.getMessage());
			log.error("cause: "+e.getCause());
		}
		salida.setRespuesta(resp);
		return salida;
	}

}
