package cl.laaraucana.EnvioASFAMEmpresa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.EnvioASFAMEmpresa.dao.GenericDao;
import cl.laaraucana.EnvioASFAMEmpresa.entities.SucAsfam;
import cl.laaraucana.EnvioASFAMEmpresa.entities.Sucursales;


@Service
public class SucursalServiceImpl implements SucursalService {

	@Autowired
	private GenericDao<SucAsfam> daoAsfam;
	
	@Autowired
	private GenericDao<Sucursales> dao;

	@Override
	public List<Sucursales> findAllSucursal() throws Exception {
		// TODO Auto-generated method stub
		return dao.findAll(Sucursales.class);
	}

	@Override
	public List<SucAsfam> findAnalistaByIdSucursal(String id) throws Exception {
		// TODO Auto-generated method stub
		return daoAsfam.findAnalistasBySucursal(SucAsfam.class, id);
	}
	
	@Override
	public Sucursales findByIdSucursal(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.findBySucursal(Sucursales.class, id);
	}
}
