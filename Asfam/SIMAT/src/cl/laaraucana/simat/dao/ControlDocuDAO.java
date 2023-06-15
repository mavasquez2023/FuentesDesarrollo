package cl.laaraucana.simat.dao;

import java.util.ArrayList;

import cl.laaraucana.simat.entidades.ControlDocuVO;

public interface ControlDocuDAO {
	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception;

	public ControlDocuVO BuscarById(ControlDocuVO idControlDocu) throws Exception;

	public void Insertar(ControlDocuVO controlDocu) throws Exception;

	public void Eliminar(ControlDocuVO controlDocu) throws Exception;

	public void Actualizar(ControlDocuVO controlDocu) throws Exception;

	public ArrayList BuscarTodo() throws Exception;

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception;

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception;

	public ArrayList BuscarListaAvanzarEstadoDoc_SMF05(ControlDocuVO controlDocu) throws Exception;

	public ArrayList BuscarListaRetrocederEstadoDoc_SMF05(ControlDocuVO controlDocu) throws Exception;

	public ArrayList getControlDocuByEstadoDoc(ControlDocuVO controlDocu) throws Exception;

	public ArrayList getControlDocuByNumDoc(ControlDocuVO controlDocu) throws Exception;

}
