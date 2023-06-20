package cl.laaraucana.rendicionpagonomina.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.rendicionpagonomina.dao.GenericDao;
import cl.laaraucana.rendicionpagonomina.entities.ILF4500AEntiti;
import cl.laaraucana.rendicionpagonomina.vo.ILF4500AVo;

@Service
public class ILF4500AServiceImpl implements ILF4500AService {

	@Autowired
	private GenericDao<ILF4500AEntiti> dao;

	@Override
	public List<ILF4500AEntiti> getILF4500A(String rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.getILF4500A(ILF4500AEntiti.class, rut);
	}

	
	@Override
	public void updateILF4500A(ILF4500AVo vo) throws Exception {
		// TODO Auto-generated method stub
		dao.updateILF4500A(ILF4500AEntiti.class, vo);
	}

}
