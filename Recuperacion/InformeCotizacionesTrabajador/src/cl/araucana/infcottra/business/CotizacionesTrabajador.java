/**
 * 
 */
package cl.araucana.infcottra.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.infcottra.dao.VO.CotizacionVO;
import cl.araucana.infcottra.dao.VO.ParamVO;
import cl.araucana.infcottra.dao.VO.SalidaVO;
import cl.araucana.infcottra.main.dao.ConsultaCotizacionDAO;
import cl.araucana.infcottra.utils.CertificadoUtils;
import cl.laaraucana.satelites.Utils.RutUtil;
import cl.laaraucana.satelites.Utils.Utils;

/**
 * @author IBM Software Factory
 *
 */
public class CotizacionesTrabajador {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public SalidaVO cotizacionesTrabajador(String rutEmpresa, String rutTrabajador){
		
		logger.info("Ingreso a Informe Cotizaciones Trabajador, RutEmpresa:" + rutEmpresa + ", rutTrabajador:" + rutTrabajador);
		SalidaVO salida= new SalidaVO();
		
		try {
			String idCertificado="";
			//se instancia e invoca DAO para obtener deuda empresa
		
			ConsultaCotizacionDAO cotizaDAO= new ConsultaCotizacionDAO();
			logger.info("Consultando Cotizaciones Trabaajador");
			
			RutUtil rutentrada= new RutUtil();
			ParamVO paramVO= new ParamVO();
			paramVO.setRutEmpresa(rutentrada.obtenerParteNumerica(rutEmpresa));
			paramVO.setRutTrabajador(rutentrada.obtenerParteNumerica(rutTrabajador));
			
			CotizacionVO cotizacionVO= cotizaDAO.consultaCotizacionesTrabajador(paramVO);
			List<CotizacionVO> listaCotizaciones= new ArrayList<CotizacionVO>();
			
			//Se obtiene el periodo inicial y final de cotizaciones
			String periodo_min=cotizacionVO.getFechaDesde();
			int year_inicial= Integer.parseInt(periodo_min.substring(0, 4));
			int mes_inicial= Integer.parseInt(periodo_min.substring(4, 6));
			String periodo_max= cotizacionVO.getFechaHasta();
			int year_final= Integer.parseInt(periodo_max.substring(0, 4));
			int mes_final= Integer.parseInt(periodo_max.substring(4, 6));
			
			//Se recorre los periodos de cotizaciones para armar los rangos
			boolean mes_anterior=false;
			CotizacionVO cotizacion_old=null;
			for (int i = year_inicial; i <= year_final; i++) {
				int mi=1;
				int mf=12;
				if(i==year_inicial){
					mi= mes_inicial;
				}
				if(i==year_final){
					mf=mes_final;
				}
				
				for (int j = mi; j <= mf; j++) {
					boolean mes_actual= false;
					paramVO.setPeriodo((i*100+j) + "");
					cotizacionVO= cotizaDAO.consultaCotizacionesTrabajador(paramVO);
					if(cotizacionVO!= null){
						mes_actual= true;
						if(mes_actual!= mes_anterior && mes_actual){
							cotizacion_old= new CotizacionVO();
							cotizacion_old.setRutEmpresa(cotizacionVO.getRutEmpresa());
							cotizacion_old.setDvEmpresa(cotizacionVO.getDvEmpresa());
							cotizacion_old.setNombreEmpresa(cotizacionVO.getNombreEmpresa());
							cotizacion_old.setDvTrabajador(cotizacionVO.getDvTrabajador());
							cotizacion_old.setRutTrabajador(cotizacionVO.getRutTrabajador());
							cotizacion_old.setNombreTrabajador(cotizacionVO.getNombreTrabajador());
							cotizacion_old.setFechaDesde(CertificadoUtils.toTituloCase(Utils.getMesAAAA(paramVO.getPeriodo())));
							cotizacion_old.setFechaHasta(cotizacionVO.getFechaHasta());
							listaCotizaciones.add(cotizacion_old);
						}
					}else if(mes_actual!= mes_anterior){
						cotizacion_old.setFechaHasta(CertificadoUtils.toTituloCase(Utils.getMesAAAA(Utils.obtenerPeriodoAntecesor(paramVO.getPeriodo(), -1))));
					}
					mes_anterior= mes_actual;
						
				}
			}
			//Se setea último periodo
			cotizacion_old.setFechaHasta(CertificadoUtils.toTituloCase(Utils.getMesAAAA(paramVO.getPeriodo())));
			
			if(listaCotizaciones.size()>0){
				listaCotizaciones.add(0, new CotizacionVO());
				salida.setCotizaciones(listaCotizaciones);
				salida.setParam(listaCotizaciones.get(1));
			}
			


		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en consulta Cotizaciones Trabajador Rut: " + rutTrabajador);
			e.printStackTrace();
		}
		return salida;
	}
}
