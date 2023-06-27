/**
 * 
 */
package cl.araucana.lme.liq.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;

import cl.araucana.lme.liq.ibatis.domain.Ilfe011VO;
import cl.araucana.lme.liq.ibatis.domain.Ilfe031VO;
import cl.araucana.lme.liq.ibatis.domain.Ilfe033VO;
import cl.araucana.lme.liq.ibatis.domain.IlfeOpeVO;
import cl.araucana.lme.liq.svc.IAS400Svc_LIQ;
import cl.araucana.lme.liq.svc.SvcFactory_LIQ;
import cl.araucana.lme.liq.svc.exception.SvcException;
import cl.araucana.lme.liq.util.FechaUtil;

/**
 * @author usist199
 *
 */
public class ManagerLME {
	private Logger log = Logger.getLogger(this.getClass());
	private final String TIPO_INSTITUCION = "C";
	private StringBuffer logLcc = new StringBuffer();
	private IAS400Svc_LIQ svc_a = null;
	//private Date dateLcc = null;
	private List lmeList = null;
	
	/**
	 * @param vo
	 */
	public List consumirLiquidacion(IlfeOpeVO vo) {
		

		try {
			svc_a = SvcFactory_LIQ.getAS400Svc_LME();
			Map param= new HashMap();
			param.put("ideope", vo.getIdeOpe());
			
			//Liquidación
			List respuesta = svc_a.getIlfe011_Consumo(param);
			return respuesta;
			
		} catch (Exception e) {
			e.printStackTrace();
			logError(e);
			return null;
		} catch (SvcException e) {
			logError(e);
			e.printStackTrace();
			return null;
		}

	}
	
	public boolean limpiaIlfe011(Ilfe011VO vo11){
		try {
			vo11.setGlosaEstado("");
			vo11.setEnviada("0");// respWs.equals("0")? "1" : "0"
			vo11.setFechaEstado(vo11.getFechaEstado());
			vo11.setHoraEstado(vo11.getHoraEstado());
			
			log.info(":::UpdateIlfe011::: " + svc_a.limpiaIlfe011(vo11));
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
public Ilfe031VO existe31(Ilfe011VO vo) {

		try {
			svc_a = SvcFactory_LIQ.getAS400Svc_LME();
			
			//Liquidación
				Ilfe031VO vo31= (Ilfe031VO)svc_a.getIlfe031(vo);
				if(vo31!=null){
					return vo31;
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			logError(e);
		} catch (SvcException e) {
			logError(e);
			e.printStackTrace();
		}
		return null;
	}


public List insertIlfe033(Ilfe011VO licencia, List listrentas) throws Exception{
	String salida="";
	List list033= new ArrayList();
	for (Iterator rentas = listrentas.iterator(); rentas.hasNext();) {
		Integer renta = (Integer) rentas.next();
		Ilfe033VO vo33= new Ilfe033VO();
		vo33.setRutAfiliado(licencia.getAfiRut().intValue());
		vo33.setRutEmpleador(licencia.getRutEmp().intValue());
		vo33.setCodigoEntidadPrevisional(0);
		vo33.setPeriodoRenta(renta.intValue());
		vo33.setNumeroDiasRemuneracion(0);
		vo33.setTotalRemuneraciones(0);
		vo33.setMontoSubsidio(0);
		vo33.setNumeroDiasSubsidio(0);
		vo33.setFechaProceso(Integer.parseInt(FechaUtil.getFechaHoyAs400()));
		try {
			log.info("Insertando 033, periodo:" + renta.intValue());
			svc_a = SvcFactory_LIQ.getAS400Svc_LME();
			salida= svc_a.insertIlfe033(vo33);
			if(salida.equals("OK")){
				log.info("Periodo Renta insertado, estado=" + salida );
			}
		} catch (Exception e) {
			log.warn("Registro dulicado en 033, rutafi= " + licencia.getAfiRut().intValue() + ", rutemp=" + licencia.getRutEmp().intValue() + ", periodo= " + renta.intValue());
		}finally{
			list033.add(vo33);
		}
	}
	return list033;
}

public boolean updateIlfe031(Ilfe031VO vo31){
	try {
		svc_a = SvcFactory_LIQ.getAS400Svc_LME();
		log.info(":::UpdateIlfe011::: " + svc_a.updateIlfe031(vo31));
	} catch (Exception e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
		log.error(e.getMessage());
		return false;
	}
	return true;
}

	/*public Ilfe031VO insertIlf031(Ilfe011VO licencia, IlfeOpeVO opeVO) throws Exception{
		svc_a = SvcFactory_LME.getAS400Svc_LME();
				
		
		Ilfe031VO vo31= new Ilfe031VO();
		vo31.setIdeOpe(opeVO.getIdeOpe().intValue());
		vo31.setCodOpe(opeVO.getCodOpe());
		vo31.setRutAfiliado(licencia.getAfiRut().intValue());
		vo31.setNroLicencia(Integer.parseInt(licencia.getNumImpre()));
		vo31.setDvNroLicencia("");
		vo31.setEnviada("1");
		vo31.setFechaProceso(Integer.parseInt(FechaUtil.getFechaHoyAs400()));
		vo31.setRespuestaWS("");
		vo31.setMarcaProcesado("N");
		vo31.setNombreEmpleador(nomEmpresa);
		vo31.setRutEmpleador(licencia.getEmpRut().intValue());
		vo31.setDvRutEmpleador(licencia.getEmpRutDV());
		vo31.setFechaRecepcionEmpresa(seringMap.get("FECRECEP").intValue());
		vo31.setDireccionEmpleador(licencia.getEmpleador().getDireccionEmpleador());
		vo31.setNumeroDireccionEmpleador(licencia.getEmpleador().getNumeroEmpleador());
		vo31.setDeptoDireccionEmpleador(licencia.getEmpleador().getDeptoEmpleador());
		vo31.setTelefonoEmpleador((licencia.getEmpleador().getTelefonoEmpleador()));
		vo31.setCiudadEmpleador(licencia.getEmpleador().getCiudadEmpleador());
		vo31.setPaisEmpleador("CHILE");
		vo31.setComunaEmpleador(licencia.getEmpleador().getComunaEmpleador());
		vo31.setCodigoComunaCompin(seringMap.get("COCOCP").intValue());
		vo31.setCodigoActividadLaboral(Integer.parseInt(licencia.getEmpleador().getActividadLaboralTrabajador()));
		vo31.setCodigoOcupacion(Integer.parseInt(licencia.getEmpleador().getOcupacionTrabajador()));
		vo31.setOtraOcupacion(licencia.getEmpleador().getOcupacionTrabajadorOtro());
		vo31.setFechaRecepcionCaja(Integer.parseInt(FechaUtil.getFechaHoyAs400()));
		vo31.setCodigoTipoRegimenPrevisional("1");
		if(seringMap.get("CODPREV").intValue()>1000 && seringMap.get("CODPREV").intValue()<2000){
			vo31.setCodigoTipoRegimenPrevisional("2");
		}
		vo31.setCodigoEntidadPrevisonal(seringMap.get("CODPREV").intValue());
		vo31.setCodigoLetraCaja(licencia.getEntidadPrevisional().getLetraCajaPrevisional());
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
		vo31.setFechaAfiliacion(Integer.parseInt(licencia.getEntidadPrevisional().getFechaPrimeraAfiliacion()));
		vo31.setFechaContrato(Integer.parseInt(licencia.getEntidadPrevisional().getFechaContratoTrabajo()));
		vo31.setCodigoEntidadPagadora("C");
		vo31.setNombreEntidadPagadora("CCAF LA ARAUCANA");
		vo31.setRentaImponible(Integer.parseInt(licencia.getRemuneraciones().getRemuneracionImponibleAFP60())); //PENDIENTE
		
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
	}*/	
	
	private void logError(Throwable e) {
		log.error(e.getClass() + "; " + e.getMessage());
		// Date now = new Date();
		// svc.insertLog(new LmeLogVO("ERROR", proceso, "",sdf.format(now),
		// shf.format(now), dataTruncation(e.getClass() + "; "+
		// e.getMessage(),500)));
	}
	
}
