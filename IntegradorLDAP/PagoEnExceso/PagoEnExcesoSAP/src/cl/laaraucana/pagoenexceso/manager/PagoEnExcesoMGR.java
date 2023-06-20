package cl.laaraucana.pagoenexceso.manager;


import org.apache.log4j.Logger;

import cl.laaraucana.pagoenexceso.persistencia.vo.ConsultaPagoEnExcesoOut;
import cl.laaraucana.pagoenexceso.persistencia.vo.PagoEnExcesoDTO;
import cl.laaraucana.pagoenexceso.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.pagoenexceso.ws.ClienteInfoAfiliado;
import cl.laaraucana.pagoenexceso.ws.ClientePexSap;
import cl.laaraucana.util.RutUtil;
import cl.laaraucana.util.Utils;

public class PagoEnExcesoMGR {
	Logger log = Logger.getLogger(this.getClass());
	
	public static void main(String[] args) {
		PagoEnExcesoMGR mgr = new PagoEnExcesoMGR();
		ConsultaPagoEnExcesoOut salida = mgr.consultarPagoEnExceso("61533000-0");
		
		System.out.println("nobmre: " + salida.getPagoEnExceso().getNombreCompleto());
		System.out.println("monto: " + salida.getPagoEnExceso().getMonto());
		System.out.println("fecha: " + salida.getPagoEnExceso().getFechaCreacion());
		System.out.println("tipo: " + salida.getPagoEnExceso().getTipo());
		System.out.println(salida.getMensaje());
	}
	
	public ConsultaPagoEnExcesoOut consultarPagoEnExceso(String rut){
		ConsultaPagoEnExcesoOut salida = new ConsultaPagoEnExcesoOut();
		//SAP
		if(!RutUtil.IsRutValido(rut)){
			salida.setCodRespuesta("5");
			salida.setMensaje("Ingrese un rut valido");
			return salida;
		}
		
		try {
			
			
			ClientePexSap clienteSap= new ClientePexSap();
			Integer monto= clienteSap.getDataPex(rut);
			
			PagoEnExcesoDTO pagoEnExceso = new PagoEnExcesoDTO();
			pagoEnExceso.setRut(rut);
			pagoEnExceso.setNombreCompleto("");
			pagoEnExceso.setTipo(0);
			
			if(monto==null){
				salida.setCodRespuesta("5");
				salida.setMensaje("Error al consultar pago en exceso en servicio SAP");
			}else if(monto==0){
				salida.setCodRespuesta("1");
				salida.setMensaje("Rut ingresado no registra devoluciones de pagos en exceso");
			}else{
				ClienteInfoAfiliado info= new ClienteInfoAfiliado();
				SalidainfoAfiliadoVO infoCliente= info.getDataAfiliado(rut);
				if(infoCliente.getCodigoError().equals("3") && infoCliente.getNombreCompleto()!=null){
					String nombreCompleto = infoCliente.getNombreCompleto();
					pagoEnExceso.setNombreCompleto(nombreCompleto);
					pagoEnExceso.setTipo(1);
				}
				pagoEnExceso.setFechaCreacion(Utils.fechaWeb());
				pagoEnExceso.setRut(rut);
				salida.setCodRespuesta("3");
				salida.setMensaje("Pago en exceso obtenido correctamente");
			}
			pagoEnExceso.setMonto(monto.intValue());
			
			salida.setPagoEnExceso(pagoEnExceso);
		} catch (Exception e) {
			log.error(e);
			salida.setCodRespuesta("5");
			salida.setMensaje("Error al consultar pago en exceso: " + e.getCause());
		}
		
		return salida;
	}
	
}
