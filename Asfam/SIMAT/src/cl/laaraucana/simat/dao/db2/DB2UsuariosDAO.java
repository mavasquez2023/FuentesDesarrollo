package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import cl.laaraucana.simat.dao.UsuariosDAO;
import cl.laaraucana.simat.entidades.UsuariosVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2UsuariosDAO implements UsuariosDAO {

	public void Actualizar(UsuariosVO usuario) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.update("actualizarUsuarios", usuario);
	}

	public UsuariosVO BuscarByName(String nombre) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			//ArrayList listaUsuarios = new ArrayList();
			UsuariosVO usuario = new UsuariosVO();
			usuario.setNombre_usuario(nombre);
			usuario = (UsuariosVO) sqlMap.queryForObject("getUsuariosByName", usuario);

			return usuario;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList BuscarTodo() throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		return (ArrayList) sqlMap.queryForList("getUsuarios", null);
	}

	public void Eliminar(UsuariosVO usuario) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		sqlMap.delete("eliminarUsuarios", usuario);
	}

	public String Insertar(UsuariosVO usuario) throws Exception {

		String msg = "";
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		System.out.println("Apunto de insertar");
		UsuariosVO utest = new UsuariosVO();

		utest = this.BuscarByName(usuario.getNombre_usuario());
		if (utest == null) {
			sqlMap.insert("insertarUsuarios", usuario);
			msg = "el registro se inserto correctamente";
		} else {
			msg = "el registro ya existe en Base Datos SIMAT";
		}

		return msg;
	}//end Insertar

	public UsuariosVO BuscarByIdUnico(UsuariosVO usuarios) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			usuarios = (UsuariosVO) sqlMap.queryForObject("getUsuariosById", usuarios);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
	}

	public void actualizarUsuariosUltimaConeccion(UsuariosVO usuario) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.update("actualizarUsuariosUltimaConeccion", usuario);

	}

}//end class
