/**
 * 
 */
package cl.araucana.wsafiliado.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.wsafiliado.vo.CargaVO;
import cl.araucana.wsafiliado.vo.DataAfiliadoVO;
import cl.araucana.wsafiliado.vo.DataCargaVO;
import cl.araucana.wsafiliado.vo.EmpresaVO;
import cl.araucana.wsafiliado.vo.ResponseDataFullWS;
import cl.araucana.wsafiliado.vo.ResponseDataWS;
import cl.araucana.wsafiliado.vo.SegmentoVO;
import cl.araucana.wsafiliado.vo.TitularVO;

/**
 * @author IBM Software Factory
 *
 */
public class GenerarResponseWS {
	final int EXISTE=1;
	final int NO_EXISTE=0;
	private static Logger log = Logger.getLogger(GenerarResponseWS.class );
	public static ResponseDataFullWS getObjetoDataFullWS(List<DataAfiliadoVO> data_afi, List<DataCargaVO> data_car, String rutBeneficiario){
		int j=1;
		//CargaVO cargaVO=null;
		TitularVO titular=null;
		List<EmpresaVO> empresas=null;
		List<CargaVO> cargas=null;
		if(data_afi!= null && data_afi.size()>0){
			for (Iterator iterator = data_afi.iterator(); iterator
					.hasNext();) {
				DataAfiliadoVO dataAfiliadoVO = (DataAfiliadoVO) iterator
						.next();
				if(j==1){
					titular= new TitularVO();
					titular.setNombreTitular(dataAfiliadoVO.getNombre());
					titular.setRutTitular(dataAfiliadoVO.getRut());
					titular.setFechaNacimiento(dataAfiliadoVO.getFechaNacimiento());
					titular.setSexo(dataAfiliadoVO.getSexo());
					titular.setCodigo_titular(1);
					empresas= new ArrayList<EmpresaVO>();
				}
				EmpresaVO empVO= new EmpresaVO();
				empVO.setRutEmpresa(dataAfiliadoVO.getRutemp());
				empVO.setRazonSocial(dataAfiliadoVO.getRazonSocial());
				empVO.setFechaAfiliacion(dataAfiliadoVO.getFechaAfiliacion());
				empVO.setEstado(dataAfiliadoVO.getEstado());
				empVO.setTipo(dataAfiliadoVO.getTipoAfiliado());
				empresas.add(empVO);
				j++;
			}
			titular.setEmpleador(empresas);
		}else if (rutBeneficiario!= null){
			titular= new TitularVO();
			titular.setRutTitular(rutBeneficiario);
			titular.setCodigo_titular(0);
		}
		if(data_car!= null && data_car.size()>0){
			cargas= new ArrayList<CargaVO>();
			for (Iterator iterator = data_car.iterator(); iterator.hasNext();) {
				DataCargaVO dataCargaVO = (DataCargaVO) iterator.next();
				CargaVO cargaVO= new CargaVO();
				cargaVO.setRutCarga(dataCargaVO.getRut());
				cargaVO.setNombreCarga(dataCargaVO.getNombre());
				cargaVO.setFechaNacimientoCarga(dataCargaVO.getFechaNacimiento());
				cargaVO.setSexoCarga(dataCargaVO.getSexo());
				cargaVO.setFechaVencimientoCarga(dataCargaVO.getFechaVencimiento());
				cargaVO.setEstadoCarga(dataCargaVO.getEstado());
				cargaVO.setParentesco(dataCargaVO.getTipoCarga());
				cargas.add(cargaVO);

			}
		}
		ResponseDataFullWS salida= new ResponseDataFullWS();
		salida.setTitular(titular);
		salida.setCarga(cargas);
		salida.setCodigo_respuesta(1);
		if(titular== null && cargas== null){
			salida.setCodigo_respuesta(0);
		}
		
		return salida;
	}
	
	public static ResponseDataWS getObjetoDataWS(List<DataAfiliadoVO> data_afi, List<DataCargaVO> data_car, String rutBeneficiario){
		int j=1;
		boolean afiliado= false;
		ResponseDataWS response= new ResponseDataWS();
		if(data_afi!= null && data_afi.size()>0){
			SegmentoVO segmento= new SegmentoVO();
			segmento.setTrabajador(" ");
			segmento.setPensionado(" ");
			for (Iterator iterator = data_afi.iterator(); iterator
					.hasNext();) {
				DataAfiliadoVO dataAfiliadoVO = (DataAfiliadoVO) iterator
						.next();
				if (rutBeneficiario!= null){
					dataAfiliadoVO.setNombre(data_car.get(0).getNombre());
					dataAfiliadoVO.setCategoria("0");
				}
				if(dataAfiliadoVO.getEstado().equals("1")){
					response.setNombre(dataAfiliadoVO.getNombre());
					response.setEstado(Integer.parseInt(dataAfiliadoVO.getEstado()));
					response.setTipo(dataAfiliadoVO.getCategoria());
					if(dataAfiliadoVO.getTipoAfiliado().equals("TRABAJADOR")){
						segmento.setTrabajador("X");
					}else if(dataAfiliadoVO.getTipoAfiliado().equals("PENSIONADO")){
						segmento.setPensionado("X");
					}
					afiliado= true;
				}
			}
			if(afiliado){
				response.setSegmento(segmento);
			}
			response.setCodigo_respuesta(1);
		}else {
			response.setCodigo_respuesta(1);
			response.setEstado(0);
		}
		log.info("Estado DataAfiliación: " + response.getEstado());
		return response;
	}
}
