package cl.laaraucana.satelites.main.dao;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.main.VO.UsuarioVO;

public class UsuarioDAO {

	private List<UsuarioVO> usuarios;

	public UsuarioDAO() {

		UsuarioVO u1 = new UsuarioVO("1", "123", 2000000, 0);
		
		u1.setPrimerNombre("Francisco");
		u1.setApellido("Quinteros");
		u1.setRutEmpresa("111111-2");
		u1.setNombreEmpresa("Araucana");
		u1.setTipoAfiliado(1);
		
		UsuarioVO u2 = new UsuarioVO("2", "123", 0, 1500000);
		
		u2.setPrimerNombre("Alberto");
		u2.setApellido("Canifrú");
		u2.setRutEmpresa("123123-1");
		u2.setNombreEmpresa("KIM");
		u2.setTipoAfiliado(2);
		
		UsuarioVO u3 = new UsuarioVO("3", "123", 2500000, 1500000);
		
		u3.setPrimerNombre("Álvaro");
		u3.setApellido("López");
		u3.setRutEmpresa("5643242-1");
		u3.setNombreEmpresa("Top People");
		u3.setTipoAfiliado(2);
		
		UsuarioVO u4 = new UsuarioVO("4", "123", 0, 0);
		
		u4.setPrimerNombre("Josué");
		u4.setApellido("Pérez");
		u4.setRutEmpresa("4212342-1");
		u4.setNombreEmpresa("IT People");
		u4.setTipoAfiliado(2);
		
		UsuarioVO u5 = new UsuarioVO("17232086-4", "123", 0, 0);
		
		u5.setPrimerNombre("ENRIQUE HUGO");
		u5.setApellido("MARTINEZ BARRIA");
		u5.setRutEmpresa("22443286-0");
		u5.setNombreEmpresa("La araucana");
		u5.setTipoAfiliado(2);
		
		usuarios = new ArrayList<UsuarioVO>();
		this.usuarios.add(u1);
		this.usuarios.add(u2);
		this.usuarios.add(u3);
		this.usuarios.add(u4);
		this.usuarios.add(u5);
	}

	public UsuarioVO getUsuario(String rut, String clave) {
		UsuarioVO usuario = null;

		for (int i = 0; i < this.usuarios.size(); i++) {
			UsuarioVO aux = this.usuarios.get(i);
			if(aux.getClave().equals(clave) && aux.getRut().equals(rut)){
				return aux;
			}
		}

		return usuario;
	}
	
	public UsuarioVO getUsuario(String rut) {
		UsuarioVO usuario = null;

		for (int i = 0; i < this.usuarios.size(); i++) {
			UsuarioVO aux = this.usuarios.get(i);
			if(aux.getRut().equals(rut)){
				return aux;
			}
		}

		return usuario;
	}
	
	public UsuarioVO getPreAprobadoUsuario(String rut){
		UsuarioVO usuario = null;

		for (int i = 0; i < this.usuarios.size(); i++) {
			UsuarioVO aux = this.usuarios.get(i);
			if(aux.getRut().equals(rut)){
				return aux;
			}
		}

		return usuario;
	}

}
