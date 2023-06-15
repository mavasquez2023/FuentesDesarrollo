package cl.laaraucana.simat.dao;

import java.util.ArrayList;

import cl.laaraucana.simat.entidades.DocsRevalReemVO;

public interface DocsRevalReemDAO {
	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception;

	public void Insertar(DocsRevalReemVO docsRevalReem) throws Exception;

	public void Eliminar(DocsRevalReemVO docsRevalReem) throws Exception;

	public void Actualizar(DocsRevalReemVO docsRevalReem) throws Exception;

	public DocsRevalReemVO BuscarById(DocsRevalReemVO docsRevalReem) throws Exception;

	public ArrayList BuscarTodo() throws Exception;

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception;

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception;

	public ArrayList BuscarListaAvanzarEstadoDoc(DocsRevalReemVO docsRevalReem) throws Exception;

	public ArrayList BuscarListaRetrocederEstadoDoc(DocsRevalReemVO docsRevalReem) throws Exception;

	public ArrayList getDocsRevalReemByEstadoDoc(DocsRevalReemVO docsRevalReem) throws Exception;

	public ArrayList getDocsRevalReemByNumDoc(DocsRevalReemVO docsRevalReem) throws Exception;
}
