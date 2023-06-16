package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.DocsRevalReemDAO;
import cl.laaraucana.simat.entidades.DocsRevalReemVO;

public class DocsRevalReemMannager {
	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception {
		System.out.println("Entro al mannager para select por mes de DocsRevalReem.");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DocsRevalReemDAO DocsRevalReemDAO = df.getDocsRevalReemDAO();
		return (ArrayList) DocsRevalReemDAO.BuscarByMesInf(mes_informacion);
	}

	public void Insertar(DocsRevalReemVO docsRevalReem) throws Exception {
		System.out.println("Llego al Mannager DocsRevalReem");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DocsRevalReemDAO docsRevalReemDAO = df.getDocsRevalReemDAO();

		System.out.println("Llego al MannagerDocsRevalReem, a la parte insertar");

		docsRevalReemDAO.Insertar(docsRevalReem);
	}

	public void Eliminar(DocsRevalReemVO docsRevalReem) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DocsRevalReemDAO docsRevalReemDAO = df.getDocsRevalReemDAO();

		docsRevalReemDAO.Eliminar(docsRevalReem);
	}

	public void actualizar(DocsRevalReemVO docsRevalReem) throws Exception {
		System.out.println("Llego al Mannager DocsRevalReem");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DocsRevalReemDAO docsRevalReemDAO = df.getDocsRevalReemDAO();
		System.out.println("OBS:mannager UP, " + docsRevalReem.getMonto_documento_original());
		docsRevalReemDAO.Actualizar(docsRevalReem);
	}

	public DocsRevalReemVO BuscarByID(DocsRevalReemVO docsRevalReem) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DocsRevalReemDAO docsRevalReemDAO = df.getDocsRevalReemDAO();
		System.out.println("Llego al Mannager buscarid");
		docsRevalReem = docsRevalReemDAO.BuscarById(docsRevalReem);

		return docsRevalReem;
	}

	public ArrayList BuscarTodo() {
		try {
			System.out.println("Entro al mannager para select por mes de DocsRevalReem.");

			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			DocsRevalReemDAO DocsRevalReemDAO = df.getDocsRevalReemDAO();

			ArrayList listaDocsRevalReem = new ArrayList();
			listaDocsRevalReem = (ArrayList) DocsRevalReemDAO.BuscarTodo();

			return listaDocsRevalReem;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {
		//int keyFin
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DocsRevalReemDAO DocsRevalReemDAO = df.getDocsRevalReemDAO();
		return (DocsRevalReemDAO.BuscarListaAvanzar(keyFin));
	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {//int keyInicio
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DocsRevalReemDAO DocsRevalReemDAO = df.getDocsRevalReemDAO();
		return (DocsRevalReemDAO.BuscarListaRetroceder(keyInicio));
	}

	/*Paginacion por estadoDocumento*/
	public ArrayList BuscarListaAvanzarEstadoDoc(DocsRevalReemVO docsRevalReem) throws Exception {
		//int keyFin
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DocsRevalReemDAO DocsRevalReemDAO = df.getDocsRevalReemDAO();
		return (DocsRevalReemDAO.BuscarListaAvanzarEstadoDoc(docsRevalReem));
	}

	public ArrayList BuscarListaRetrocederEstadoDoc(DocsRevalReemVO docsRevalReem) throws Exception {//int keyInicio
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DocsRevalReemDAO DocsRevalReemDAO = df.getDocsRevalReemDAO();
		return (DocsRevalReemDAO.BuscarListaRetrocederEstadoDoc(docsRevalReem));
	}

	/*END paginacion por estadoDocumento*/

	public ArrayList getDocsRevalReemByEstadoDoc(DocsRevalReemVO docsRevalReem) throws Exception {
		System.out.println("Entro al mannager para select por mes de DocsRevalReem.");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DocsRevalReemDAO DocsRevalReemDAO = df.getDocsRevalReemDAO();
		return (ArrayList) DocsRevalReemDAO.getDocsRevalReemByEstadoDoc(docsRevalReem);
	}

	public ArrayList getDocsRevalReemByNumDoc(DocsRevalReemVO docsRevalReem) throws Exception {
		System.out.println("Entro al mannager para select por mes de DocsRevalReem.");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DocsRevalReemDAO DocsRevalReemDAO = df.getDocsRevalReemDAO();
		return (ArrayList) DocsRevalReemDAO.getDocsRevalReemByNumDoc(docsRevalReem);
	}

}
