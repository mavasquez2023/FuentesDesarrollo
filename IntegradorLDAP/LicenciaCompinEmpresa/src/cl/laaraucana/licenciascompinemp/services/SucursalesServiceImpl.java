package cl.laaraucana.licenciascompinemp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.licenciascompinemp.dao.GenericDao;
import cl.laaraucana.licenciascompinemp.entities.SucLicencias;
import cl.laaraucana.licenciascompinemp.entities.SucLicenciasDP;

@Service
public class SucursalesServiceImpl implements SucursalesService{

	@Autowired
	private GenericDao<SucLicencias> dao;
	
	@Autowired
	private GenericDao<SucLicenciasDP> daodp;
	
	@Override
	public List<SucLicencias> findByComunaSuc(int idcomuna) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByComunaSuc(SucLicencias.class, idcomuna);
	}
	
	@Override
	public List<SucLicenciasDP> getAllSucursales() throws Exception {
		// TODO Auto-generated method stub
		return daodp.getAllSucursales(SucLicenciasDP.class);
	}

	@Override
	public SucLicenciasDP getSucursalByCodigo(String codSucursal) throws Exception {
		// TODO Auto-generated method stub
		List<SucLicenciasDP> lista= daodp.getSucursalByCodigo(SucLicenciasDP.class, codSucursal);
		if(lista!=null){
			return lista.get(0);
		}else{
			return null;
		}
	}
	
}
