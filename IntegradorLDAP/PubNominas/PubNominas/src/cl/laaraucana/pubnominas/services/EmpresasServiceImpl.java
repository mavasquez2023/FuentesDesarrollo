/**
 * 
 */
package cl.laaraucana.pubnominas.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cl.laaraucana.pubnominas.persistence.DaoAsfam;
import cl.laaraucana.pubnominas.persistence.DaoEmpresa;
import cl.laaraucana.pubnominas.services.ReporteService;
import cl.laaraucana.pubnominas.vo.EmpresasVO;


/**
 * @author J-Factory
 *
 */
@Service
public class EmpresasServiceImpl implements EmpresasService{
	
	@Autowired
	private DaoEmpresa daoEmp;
	
	private Map<String, EmpresasVO> empresas= new HashMap<String, EmpresasVO>();
	

	/**
	 * @return the empresas
	 */
	public Map<String, EmpresasVO> getEmpresas() {
		if(empresas==null || empresas.size()==0){
			List<EmpresasVO> listaEmpresas= daoEmp.getListaEmpresas();
			for (Iterator iterator = listaEmpresas.iterator(); iterator
					.hasNext();) {
				EmpresasVO empresasVO = (EmpresasVO) iterator.next();
				this.empresas.put(empresasVO.getID(), empresasVO);
				
			}
		}
		return empresas;
	}


		
}
