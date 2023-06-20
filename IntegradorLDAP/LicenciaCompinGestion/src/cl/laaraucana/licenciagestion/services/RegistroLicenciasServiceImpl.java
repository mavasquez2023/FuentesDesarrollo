package cl.laaraucana.licenciagestion.services;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cl.laaraucana.licenciagestion.ibatis.dao.ConsultaLicenciasDAO;
import cl.laaraucana.licenciagestion.vo.OficinasLicenciasVO;
import cl.laaraucana.licenciagestion.vo.LicenciasPeriodoVO;
import cl.laaraucana.licenciagestion.vo.LicenciasViaIngresoVO;


@Service
public class RegistroLicenciasServiceImpl implements RegistroLicenciasService{


	@Override
	public int rangoLicencias(String periodo) throws Exception {
		ConsultaLicenciasDAO licenciaDao= new ConsultaLicenciasDAO();
		HashMap<String, Integer> param= new HashMap<String, Integer>();
		param.put("year", Integer.parseInt(periodo.substring(0, 4)));
		param.put("month", Integer.parseInt(periodo.substring(4)));
		int maxRango= licenciaDao.maxRangoLicencias(param);
		int digitos= Integer.toString(maxRango).length();
		double rangoRedondeado= Math.ceil(maxRango/(Math.pow(10, digitos-1))) * Math.pow(10, digitos-2);
		return (int)rangoRedondeado;
	}

	@Override
	public List<LicenciasPeriodoVO> licenciasxMes() throws Exception {
		ConsultaLicenciasDAO licenciaDao= new ConsultaLicenciasDAO();
		List<LicenciasPeriodoVO> lista= licenciaDao.licenciasxMes();
		return lista;
	}

	@Override
	public List<OficinasLicenciasVO> OficinasxRango(Map<String, Integer> rangos, String periodo) throws Exception {
		ConsultaLicenciasDAO licenciaDao= new ConsultaLicenciasDAO();

		rangos.put("year", Integer.parseInt(periodo.substring(0, 4)));
		rangos.put("month", Integer.parseInt(periodo.substring(4)));
		List<OficinasLicenciasVO> lista= licenciaDao.oficinasxRango(rangos);
		return lista;
	}

	@Override
	public List<LicenciasViaIngresoVO> licenciasxViaIngeso(String periodo) throws Exception {
		ConsultaLicenciasDAO licenciaDao= new ConsultaLicenciasDAO();
		HashMap<String,Integer> param= new HashMap<String, Integer>();
		param.put("year", Integer.parseInt(periodo.substring(0, 4)));
		param.put("month", Integer.parseInt(periodo.substring(4)));
		List<LicenciasViaIngresoVO> lista= licenciaDao.licenciasxViaIngeso(param);
		return lista;
	}
	

}
