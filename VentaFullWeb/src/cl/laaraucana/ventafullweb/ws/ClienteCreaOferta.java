package cl.laaraucana.ventafullweb.ws;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;

import com.lautaro.xi.CRM.Legacy.CrearOfertaFDReq_DT;
import com.lautaro.xi.CRM.Legacy.CrearOfertaFDRes_DT;
import com.lautaro.xi.CRM.Legacy.Os_CrearOfertaFD_SIBindingStub;

import cl.laaraucana.ventafullweb.util.Configuraciones;
import cl.laaraucana.ventafullweb.util.Utils;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import cl.laaraucana.ventafullweb.vo.FormCrearOfertaVO;
import cl.laaraucana.ventafullweb.vo.SalidaCreaOfertaVO;


public class ClienteCreaOferta {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public SalidaCreaOfertaVO getRespuestaWSCreaOferta(AfiliadoVo afiliado, FormCrearOfertaVO form) throws AxisFault {
		String ep = Configuraciones.getConfig("ep.WSCrearOferta");
		String username = Configuraciones.getConfig("servicios.sap.username");
		String password= Configuraciones.getConfig("servicios.sap.pass");
		SalidaCreaOfertaVO respuestaServicio = new SalidaCreaOfertaVO();
		
		Os_CrearOfertaFD_SIBindingStub stub = new Os_CrearOfertaFD_SIBindingStub();	
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(Os_CrearOfertaFD_SIBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);

		CrearOfertaFDReq_DT req = new CrearOfertaFDReq_DT();
		
		// Formatear fechas
		String fechaNacimientoForm = form.getFechaNacimientoFormulario();
		String fechaNacimientoEquifax = afiliado.getFechaNacimiento();
		
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat inputFormatEquifax = new SimpleDateFormat("yyyyMMdd");
	    SimpleDateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy");
	    Date date;
	    Date dateEquifax;
	    String fechaNacimientoFormFormateada = null;
	    String fechaNacimientoEquifaxFormateada = null;
		
	    try {
			date = inputFormat.parse(fechaNacimientoForm);
			fechaNacimientoFormFormateada = outputFormat.format(date);
			
			dateEquifax = inputFormatEquifax.parse(fechaNacimientoEquifax);
			fechaNacimientoEquifaxFormateada = outputFormat.format(dateEquifax);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setRUT_AFILIADO(afiliado.getRutAfiliado());
		req.setMONTO_SIMULADO(Integer.toString(afiliado.getMontoSimulacion()));
		req.setPLAZO_SIMULADO(Integer.toString(afiliado.getCuotas()));
		req.setSEGURO_CESANTIA(afiliado.getSeguroCesantia());
		req.setRENTA_COTIZADA(afiliado.getRentaPromedio());
		req.setSEGMENTO((afiliado.getSegmento().equals("PENSIONADOS"))?"ZAFPEN":"ZAFTRA");		
		req.setRUT_EMPRESA_CAMP(afiliado.getRutEmpresa());		
		req.setID_ANEXO_CAMP(Integer.toString(afiliado.getIdCampagna()));
		req.setNRO_INSC_PENS_CAMP(afiliado.getInscripcionPension());
		req.setFECHA_NACIMIENTO_DIG_AFIL(fechaNacimientoFormFormateada);
		req.setESTADO_CIVIL(form.getTipoContratoFormulario());
		req.setCOD_TIPO_CONTRATO(form.getTipoContratoFormulario()); 
		req.setDIR_PART_CALLE(form.getDireccionFormulario());
		req.setDIR_PART_NUMERO(form.getNumeroDireccionFormulario());
		req.setDIR_PART_DPTO(afiliado.getDpto());
		req.setDIR_PART_VILLA_POBL(form.getVillaPoblacionFormulario());
		req.setDIR_PART_REGION(form.getRegionFormulario());
		req.setDIR_PART_PROVINCIA(Utils.getProvincia(form.getComunaFormulario()));
		req.setDIR_PART_COMUNA(form.getComunaFormulario());
		req.setMAIL(form.getEmailFormulario());
		req.setTEL_CELULAR(form.getCelularFormulario());
		req.setTEL_FIJO(form.getTelefonoFormulario());
		req.setCTA_BANCO_NUMERO(afiliado.getBancoNumeroCuenta());
		req.setCTA_BANCO_TIPO(afiliado.getBancoTipoCuenta());
		req.setCTA_BANCO_NOMBRE(afiliado.getBancoCodigoBanco());
		req.setNOM_COMPL_EQUIFAX(afiliado.getNombreAfiliado());
		req.setESTADO_CIVIL_EQUIFAX(afiliado.getEstadoCivil());
		req.setFECHA_NACIMIENTO(fechaNacimientoEquifaxFormateada);
		req.setNACIONALIDAD(afiliado.getNacionalidad());
		
		CrearOfertaFDReq_DT reqFormateado = llenarCamposVacios(req);
		
		CrearOfertaFDRes_DT salida = new CrearOfertaFDRes_DT();		
					
		try {
			salida = stub.os_CrearOfertaFD_SI(reqFormateado);
		} catch (RemoteException e) {
			e.printStackTrace();
		}	
		
		if(salida == null ) {
			logger.error("Error al ejecutar WS WSCrearOferta. Respuesta null");
		} else {			
			logger.info("Se ha ejecutado correctamente el WSCrearOferta.");
			respuestaServicio = mapear(salida);
		}
		
		return respuestaServicio;	
		
		
	}
	
	private SalidaCreaOfertaVO mapear (CrearOfertaFDRes_DT salida) {
		SalidaCreaOfertaVO respuestaMapeada = new SalidaCreaOfertaVO();
		
		respuestaMapeada.setESTADO_APROB_CLIENTE(salida.getESTADO_APROB_CLIENTE());
		respuestaMapeada.setESTADO_OFERTA(salida.getESTADO_OFERTA());
		respuestaMapeada.setFOLIO_GENERADO(salida.getFOLIO_GENERADO());
		respuestaMapeada.setMENSAJE(salida.getMENSAJE());
		respuestaMapeada.setNRO_OFERTA_GENERADA(salida.getNRO_OFERTA_GENERADA());
		respuestaMapeada.setRUT_AFILIADO(salida.getRUT_AFILIADO());
		respuestaMapeada.setSUCURSAL_OFERTA(salida.getSUCURSAL_OFERTA());		
		
		return respuestaMapeada;
	}
	
	private CrearOfertaFDReq_DT llenarCamposVacios(CrearOfertaFDReq_DT req) {
		CrearOfertaFDReq_DT newReq = new CrearOfertaFDReq_DT();
		
		newReq.setRUT_AFILIADO((req.getRUT_AFILIADO() == null)?"":req.getRUT_AFILIADO());
		newReq.setMONTO_SIMULADO((req.getMONTO_SIMULADO() == null)?"":req.getMONTO_SIMULADO());
        newReq.setPLAZO_SIMULADO((req.getPLAZO_SIMULADO() == null)?"":req.getPLAZO_SIMULADO());
        newReq.setSEGURO_CESANTIA((req.getSEGURO_CESANTIA() == null)?"":req.getSEGURO_CESANTIA());
        newReq.setRENTA_COTIZADA((req.getRENTA_COTIZADA() == null)?"":req.getRENTA_COTIZADA());
        newReq.setSEGMENTO((req.getSEGMENTO() == null)?"":req.getSEGMENTO());
        newReq.setRUT_EMPRESA_CAMP((req.getRUT_EMPRESA_CAMP() == null)?"":req.getRUT_EMPRESA_CAMP());
        newReq.setID_ANEXO_CAMP((req.getID_ANEXO_CAMP() == null)?"":req.getID_ANEXO_CAMP());
        newReq.setNRO_INSC_PENS_CAMP((req.getNRO_INSC_PENS_CAMP() == null)?"":req.getNRO_INSC_PENS_CAMP());
        newReq.setFECHA_NACIMIENTO_DIG_AFIL((req.getFECHA_NACIMIENTO_DIG_AFIL() == null)?"":req.getFECHA_NACIMIENTO_DIG_AFIL());
        newReq.setESTADO_CIVIL((req.getESTADO_CIVIL() == null)?"":req.getESTADO_CIVIL());
        newReq.setCOD_TIPO_CONTRATO((req.getCOD_TIPO_CONTRATO() == null)?"":req.getCOD_TIPO_CONTRATO());
        newReq.setDIR_PART_CALLE((req.getDIR_PART_CALLE() == null)?"":req.getDIR_PART_CALLE());
        newReq.setDIR_PART_NUMERO((req.getDIR_PART_NUMERO() == null)?"":req.getDIR_PART_NUMERO());
        newReq.setDIR_PART_DPTO((req.getDIR_PART_DPTO() == null)?"":req.getDIR_PART_DPTO());
        newReq.setDIR_PART_VILLA_POBL((req.getDIR_PART_VILLA_POBL() == null)?"":req.getDIR_PART_VILLA_POBL());
        newReq.setDIR_PART_REGION((req.getDIR_PART_REGION() == null)?"":req.getDIR_PART_REGION());
        newReq.setDIR_PART_PROVINCIA((req.getDIR_PART_PROVINCIA() == null)?"":req.getDIR_PART_PROVINCIA());
        newReq.setDIR_PART_COMUNA((req.getDIR_PART_COMUNA() == null)?"":req.getDIR_PART_COMUNA());
        newReq.setMAIL((req.getMAIL() == null)?"":req.getMAIL());
        newReq.setTEL_CELULAR((req.getTEL_CELULAR() == null)?"":req.getTEL_CELULAR());
        newReq.setTEL_FIJO((req.getTEL_FIJO() == null)?"":req.getTEL_FIJO());
        newReq.setCTA_BANCO_NUMERO((req.getCTA_BANCO_NUMERO() == null)?"":req.getCTA_BANCO_NUMERO());
        newReq.setCTA_BANCO_TIPO((req.getCTA_BANCO_TIPO() == null)?"":req.getCTA_BANCO_TIPO());
        newReq.setCTA_BANCO_NOMBRE((req.getCTA_BANCO_NOMBRE() == null)?"":req.getCTA_BANCO_NOMBRE());
        newReq.setNOM_COMPL_EQUIFAX((req.getNOM_COMPL_EQUIFAX() == null)?"":req.getNOM_COMPL_EQUIFAX());
        newReq.setESTADO_CIVIL_EQUIFAX((req.getESTADO_CIVIL_EQUIFAX() == null)?"":req.getESTADO_CIVIL_EQUIFAX());
        newReq.setFECHA_NACIMIENTO((req.getFECHA_NACIMIENTO() == null)?"":req.getFECHA_NACIMIENTO());
        newReq.setNACIONALIDAD((req.getNACIONALIDAD() == null)?"":req.getNACIONALIDAD());
		
		return newReq;
	}
	 
	
}