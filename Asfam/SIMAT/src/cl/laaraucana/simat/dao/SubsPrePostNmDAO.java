package cl.laaraucana.simat.dao;

import java.util.ArrayList;

import cl.laaraucana.simat.entidades.SubsPrePostNMVO;

public interface SubsPrePostNmDAO {
	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception;

	public void Insertar(SubsPrePostNMVO subs) throws Exception;

	public void Eliminar(SubsPrePostNMVO subs) throws Exception;

	public void Actualizar(SubsPrePostNMVO subs) throws Exception;

	public SubsPrePostNMVO BuscarById(SubsPrePostNMVO subsPrePostNM) throws Exception;

	public ArrayList BuscarTodo() throws Exception;

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception;

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception;

	public ArrayList getSubsPrePostNMByRutBenef(SubsPrePostNMVO subsPrePostNM) throws Exception;

	public ArrayList getSubsPrePostNMByNumDoc(SubsPrePostNMVO subsPrePostNM) throws Exception;
}
