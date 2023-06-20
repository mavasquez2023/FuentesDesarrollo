package cl.laaraucana.rendicionpagonomina.services;

import java.util.List;

import cl.laaraucana.rendicionpagonomina.entities.ILF4500AEntiti;
import cl.laaraucana.rendicionpagonomina.vo.ILF4500AVo;

public interface ILF4500AService {
	
	public List<ILF4500AEntiti> getILF4500A(String rut) throws Exception;
	
	public void updateILF4500A(ILF4500AVo vo) throws Exception;
}
