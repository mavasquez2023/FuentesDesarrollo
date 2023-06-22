package cl.laaraucana.continuidad.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.continuidad.persistencia.DAOFactory;
import cl.laaraucana.continuidad.persistencia.dao.ContinuidadRentasDaoI;
import cl.laaraucana.continuidad.service.vo.EntradaContRentas;
import cl.laaraucana.continuidad.service.vo.Licencia;
import cl.laaraucana.continuidad.service.vo.Renta;
import cl.laaraucana.continuidad.service.vo.RespuestaVO;
import cl.laaraucana.continuidad.service.vo.SalidaContRentas;
import cl.laaraucana.util.Constantes;
import cl.laaraucana.util.RutUtil;
import cl.laaraucana.util.objectvalidate.Funciones;

public class ContinuidadMgr {
	private Logger log = Logger.getLogger(this.getClass());
	
	public SalidaContRentas continuidad(EntradaContRentas entrada){
		SalidaContRentas salida = new SalidaContRentas();
		RespuestaVO resp = new RespuestaVO();
		
		if(!RutUtil.IsRutValido(entrada.getRutAfiliado())){
			resp.setCodigoRespuesta(Constantes.SERVICE_ERROR_CODE);
			resp.setGlosa("Ingrese rut válido");
		}else if(!Funciones.isNumeric(entrada.getCantidadRentas())
				|| Integer.parseInt(entrada.getCantidadRentas())<1){
			resp.setCodigoRespuesta(Constantes.SERVICE_ERROR_CODE);
			resp.setGlosa("Ingrese cantidad de rentas válida");
		}else{
			try {
				ContinuidadRentasDaoI rentasDao = DAOFactory.getDaoFactory().getContinuidadRentasDao();
				
				String salidaCall  =rentasDao.consultaContinuidadRentas(entrada.getRutAfiliado(), entrada.getCantidadRentas());
				int auxPos = 0;

				resp.setCodigoRespuesta(salidaCall.substring(auxPos,auxPos += 1));
				resp.setGlosa(salidaCall.substring(auxPos,auxPos += 60));
				
				if(resp.getCodigoRespuesta().equals(Constantes.SERVICE_SUCCESS_CODE)){
					List<Licencia> licenciaList = new ArrayList<Licencia>();
					//lista de licencias
					for(int i = 0 ; i<5; i++){
						Licencia auxLic = new Licencia();
						auxLic.setTipo(salidaCall.substring(auxPos,auxPos += 2));
						auxLic.setLicencia(salidaCall.substring(auxPos,auxPos += 10));
						auxLic.setFechaDesde(salidaCall.substring(auxPos,auxPos += 8));
						auxLic.setFechaHasta(salidaCall.substring(auxPos,auxPos += 8));
						auxLic.setTipoLicencia(salidaCall.substring(auxPos,auxPos += 1));
						auxLic.setCantidadDias(salidaCall.substring(auxPos,auxPos += 3));
						auxLic.setRutPadreParental(salidaCall.substring(auxPos,auxPos += 10));
						
						if(Integer.parseInt(auxLic.getLicencia())!=0){
							licenciaList.add(auxLic);
						}
					}
					
					List<Renta> rentaList  =new ArrayList<Renta>();
					//Lista de rentas
					for (int i = 0; i < 36; i++) {
						Renta auxRenta = new Renta();
						auxRenta.setRutEmpresa(salidaCall.substring(auxPos,auxPos += 10));
						auxRenta.setPeriodo(salidaCall.substring(auxPos,auxPos += 6));
						if(!auxRenta.getPeriodo().trim().isEmpty()){
							rentaList.add(auxRenta);
						}
					}
					
					salida.setLicencias(licenciaList);
					salida.setRentas(rentaList);
				}
			} catch (Exception e) {
				log.error("Error al mapear respeusta de AS400",e);
				resp.setCodigoRespuesta(Constantes.SERVICE_ERROR_CODE);
				resp.setGlosa(e.getMessage());
			}
		}
		salida.setRespuesta(resp);
		return salida;
	}
}
