package test;

import java.util.ArrayList;
import java.util.Iterator;

import oasis.names.tc.saml._2_0.metadata.SSODescriptorType;

import cl.laaraucana.silmsil.dao.LM_DAO;
import cl.laaraucana.silmsil.manager.LM_Manager;
import cl.laaraucana.silmsil.manager.ManagerProcesar;
import cl.laaraucana.silmsil.vo.CountVO;
import cl.laaraucana.silmsil.vo.LM_VO;
import cl.laaraucana.silmsil.vo.Paginacion_VO;

public class TestPaginasFW {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Start");
				
		try {
			
			System.out.println("START paginas");
			Paginacion_VO pag=new Paginacion_VO(10);
			LM_Manager lm_MGR=new LM_Manager();
			pag.setFechaProceso("201405");
			pag= lm_MGR.paginaInicio(pag);
			System.out.println("BD: "+pag.getCantidad_BD());
			System.out.println("paginas calculadas: "+pag.getCantidadPaginas());
			System.out.println("test: "+(30%10));
			System.out.println("END paginas");
			/*
			Iterator it;
			LM_VO lmVO;
			LM_VO lmVOAux;
			ArrayList lmList=new ArrayList();
			LM_Manager lmMGR=new LM_Manager();		
			
			lmVO=new LM_VO();
			lmVO.setFecproceso(201405);			
			lmVO.setFolio("0001324375-1");
			lmVO.setRuttrabaj("3631990-9");
			lmVO.setFecterrepo(20130222);
			System.out.println("aqui gil main");
			lmList=LM_DAO.buscar(lmVO);			
			System.out.println("tamaño: "+lmList.size());
			LM_DAO.eliminar(lmVO);
			
			
			
			
			Paginacion_VO pag=new Paginacion_VO(5);			
			pag.setFechaProceso("201405");
			
			
			System.out.println("info PAG-------------");
			System.out.println("fechaproceso: "+pag.getFechaProceso());
			System.out.println("ultimo: "+pag.getUltimoLista());
			System.out.println("primero: "+pag.getPrimeroLista());
			System.out.println("pagina Actual: "+pag.getPaginaActual());
			System.out.println("BD: "+pag.getCantidad_BD());
			System.out.println("-------------");
			
			pag=lmMGR.paginarAvance(pag);
			System.out.println("info PAG de consulta-------------");
			System.out.println("fechaproceso: "+pag.getFechaProceso());
			System.out.println("ultimo: "+pag.getUltimoLista());
			System.out.println("primero: "+pag.getPrimeroLista());
			System.out.println("pagina Actual: "+pag.getPaginaActual());
			System.out.println("BD: "+pag.getCantidad_BD());
			System.out.println("-------------");			
			lmList=pag.getListaActual();
			it=lmList.iterator();
			while (it.hasNext()) {
				lmVO=(LM_VO) it.next();
				System.out.println("1) correlativ: "+lmVO.getCorrelativ());
			}
			
			System.out.println("2)-------------");
			pag=lmMGR.paginarAvance(pag);
			System.out.println("info PAG de consulta-------------");
			System.out.println("fechaproceso: "+pag.getFechaProceso());
			System.out.println("ultimo: "+pag.getUltimoLista());
			System.out.println("primero: "+pag.getPrimeroLista());
			System.out.println("pagina Actual: "+pag.getPaginaActual());
			System.out.println("BD: "+pag.getCantidad_BD());
			System.out.println("-------------");
			lmList=pag.getListaActual();
			it=lmList.iterator();
			while (it.hasNext()) {
				lmVO=(LM_VO) it.next();
				System.out.println("2) correlativ: "+lmVO.getCorrelativ());
			}
			
			System.out.println("3)-------------");
			pag=lmMGR.paginarAvance(pag);
			System.out.println("info PAG de consulta-------------");
			System.out.println("fechaproceso: "+pag.getFechaProceso());
			System.out.println("ultimo: "+pag.getUltimoLista());
			System.out.println("primero: "+pag.getPrimeroLista());
			System.out.println("pagina Actual: "+pag.getPaginaActual());
			System.out.println("BD: "+pag.getCantidad_BD());
			System.out.println("-------------");
			lmList=pag.getListaActual();
			it=lmList.iterator();
			while (it.hasNext()) {
				lmVO=(LM_VO) it.next();
				System.out.println("3) correlativ: "+lmVO.getCorrelativ());
			}
			
			
			System.out.println("4)-------------");
			pag=lmMGR.paginarAvance(pag);
			System.out.println("info PAG de consulta-------------");
			System.out.println("fechaproceso: "+pag.getFechaProceso());
			System.out.println("ultimo: "+pag.getUltimoLista());
			System.out.println("primero: "+pag.getPrimeroLista());
			System.out.println("pagina Actual: "+pag.getPaginaActual());
			System.out.println("BD: "+pag.getCantidad_BD());
			System.out.println("-------------");
			lmList=pag.getListaActual();
			it=lmList.iterator();
			while (it.hasNext()) {
				lmVO=(LM_VO) it.next();
				System.out.println("4) correlativ: "+lmVO.getCorrelativ());
			}
			
			System.out.println("\n5)-------------");
			pag=lmMGR.paginarAvance(pag);
			System.out.println("info PAG de consulta-------------");
			System.out.println("fechaproceso: "+pag.getFechaProceso());
			System.out.println("ultimo: "+pag.getUltimoLista());
			System.out.println("primero: "+pag.getPrimeroLista());
			System.out.println("pagina Actual: "+pag.getPaginaActual());
			System.out.println("tamaños lista: "+pag.getLargolistaActual());
			System.out.println("BD: "+pag.getCantidad_BD());
			System.out.println("-------------");
			lmList=pag.getListaActual();
			it=lmList.iterator();
			while (it.hasNext()) {
				lmVO=(LM_VO) it.next();
				System.out.println("5) correlativ: "+lmVO.getCorrelativ());
			}
			
			
			//LM_DAO.upCorrelativo("5","201405");
			int c=LM_DAO.count_LM("201405");
			System.out.println("count: "+c);
			
			
			
			int registrosPorPagina=5;
			ArrayList lmList=null;
			
			Paginacion_VO pag=new Paginacion_VO(registrosPorPagina);
			//pag.setUltimoLista(0);
			lmList=LM_DAO.pagina_Avanzar(pag);
			
			System.out.println("lmLIST: "+lmList.size());
			
			LM_VO lmVOaux=new LM_VO();
			Iterator it=lmList.iterator();
			while (it.hasNext()) {
				lmVO=(LM_VO) it.next();
				System.out.println("1) correlativ: "+lmVO.getCorrelativ());
			}
			
			lmVOaux=(LM_VO) lmList.get((lmList.size()-1));			
			System.out.println("siguiente 2");
			pag.setUltimoLista(String.valueOf(lmVOaux.getCorrelativ()));
			lmList=LM_DAO.pagina_Avanzar(pag);			
			
			it=lmList.iterator();
			while (it.hasNext()) {
				lmVO=(LM_VO) it.next();
				System.out.println("2) correlativ: "+lmVO.getCorrelativ());
			}
			
			lmVOaux=(LM_VO) lmList.get((lmList.size()-1));			
			System.out.println("siguiente 3");
			pag.setUltimoLista(String.valueOf(lmVOaux.getCorrelativ()));
			lmList=LM_DAO.pagina_Avanzar(pag);				
			it=lmList.iterator();
			while (it.hasNext()) {
				lmVO=(LM_VO) it.next();
				System.out.println("3) correlativ: "+lmVO.getCorrelativ());
			}
			
			lmVOaux=(LM_VO) lmList.get((lmList.size()-1));			
			System.out.println("siguiente 4");
			pag.setUltimoLista(String.valueOf(lmVOaux.getCorrelativ()));
			lmList=LM_DAO.pagina_Avanzar(pag);				
			it=lmList.iterator();
			while (it.hasNext()) {
				lmVO=(LM_VO) it.next();
				System.out.println("4) correlativ: "+lmVO.getCorrelativ());
			}
			
			System.out.println("retroceder 1: correlativo "+lmVOaux.getCorrelativ());
			lmVOaux=(LM_VO) lmList.get(0);						
			pag.setPrimeroLista(String.valueOf(lmVOaux.getCorrelativ()));
			lmList=LM_DAO.pagina_Retroceder(pag);			
			it=lmList.iterator();
			while (it.hasNext()) {
				lmVO=(LM_VO) it.next();
				System.out.println("4) correlativ: "+lmVO.getCorrelativ());
			}
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("CATCH : "+e);
			e.printStackTrace();
		}
		
		System.out.println("END");
		
	}//END main
}//end class
