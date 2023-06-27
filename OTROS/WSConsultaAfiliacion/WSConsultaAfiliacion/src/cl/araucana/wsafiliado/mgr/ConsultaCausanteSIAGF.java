/**
 * 
 */
package cl.araucana.wsafiliado.mgr;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.wsafiliado.to.TuplaTO;
import cl.araucana.wsafiliado.util.ClienteSIAGF;
import cl.araucana.wsafiliado.vo.CargaVO;
import cl.araucana.wsafiliado.vo.DataCargaVO;

/**
 * @author IBM Software Factory
 *
 */
public class ConsultaCausanteSIAGF {
	Logger log = Logger.getLogger(this.getClass());
	
	public String getConsultaBeneficiarioSIAGF(String rutCausante) {
				
		//Consulta Rut
		ClienteSIAGF siagf = new ClienteSIAGF();
		ArrayList<TuplaTO> tuplasCausanteList = null;
		int rutInt= 0;
		String rutBeneficiario=null;
		try {
			
			log.info("Consultando en SIAGF RUT: "+rutCausante);
			int numtuplas=0;
			tuplasCausanteList = siagf.consultaCausante(rutCausante);
			numtuplas= tuplasCausanteList.size();
			log.info("Tuplas encontradas:" + numtuplas);
			
			//Por cada tupla.....
			TuplaTO oTupla = null;
			String rutInteger= (rutCausante.split("-"))[0];
			
			for (int i = 0; i < numtuplas; i++) {
				oTupla = (TuplaTO) tuplasCausanteList.get(i);
				String estadoTupla= oTupla.getCodEstadoTupla();
				
				//System.out.println("estado Tupla=" + estadoTupla);
				//Si tupla coincide rutcausante y es Vigente
				if(rutInteger.equals(oTupla.getRutCausante()) && estadoTupla.equals("1")){
					log.info("Encontrado estado vigente");
					rutBeneficiario= oTupla.getRutBeneficiario();
					break;
				}	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rutBeneficiario;
	}

	public List<DataCargaVO> getConsultaCausanteSIGAF(String rutCausante) {
		List<DataCargaVO> cargas= new ArrayList<DataCargaVO>();
		//Consulta Rut
		ClienteSIAGF siagf = new ClienteSIAGF();
		ArrayList<TuplaTO> tuplasCausanteList = null;
		int rutInt= 0;
		String rutBeneficiario=null;
		try {
			
			log.info("Consultando en SIAGF RUT: "+rutCausante);
			int numtuplas=0;
			tuplasCausanteList = siagf.consultaCausante(rutCausante);
			numtuplas= tuplasCausanteList.size();
			log.info("Tuplas encontradas:" + numtuplas);
			
			//Por cada tupla.....
			TuplaTO oTupla = null;
			String rutInteger= (rutCausante.split("-"))[0];
			int contador=1;
			for (int i = 0; i < numtuplas; i++) {
				oTupla = (TuplaTO) tuplasCausanteList.get(i);
				String estadoTupla= oTupla.getCodEstadoTupla();
				
				log.info("estado Tupla=" + estadoTupla);
				//Si tupla coincide rutcausante y es Vigente
				if(rutInteger.equals(oTupla.getRutCausante()) && estadoTupla.equals("1") || contador==numtuplas){
					log.info("Encontrado estado vigente");
					DataCargaVO cargaVO= new DataCargaVO();
					cargaVO.setRut(oTupla.getRutCausante());
					cargaVO.setNombre(oTupla.getNomCausante());
					cargaVO.setFechaNacimiento(oTupla.getFecNacCausante());
					cargaVO.setSexo(oTupla.getSexoCausante());
					if(estadoTupla.equals("1")){
						cargaVO.setEstado("ACTIVO");
					}else{
						cargaVO.setEstado("INACTIVO");
					}
					cargaVO.setFechaVencimiento(oTupla.getFecExtCausante());
					cargaVO.setRutBeneficiario(Integer.parseInt(oTupla.getRutBeneficiario().split("-")[0]));
					cargas.add(cargaVO);
					break;
				}
				contador++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cargas;
	}
}
