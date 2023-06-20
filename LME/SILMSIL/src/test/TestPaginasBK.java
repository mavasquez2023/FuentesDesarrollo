package test;

import java.util.ArrayList;
import java.util.Iterator;

import cl.laaraucana.silmsil.dao.LM_DAO;
import cl.laaraucana.silmsil.manager.LM_Manager;
import cl.laaraucana.silmsil.manager.ManagerProcesar;
import cl.laaraucana.silmsil.vo.CountVO;
import cl.laaraucana.silmsil.vo.LM_VO;
import cl.laaraucana.silmsil.vo.Paginacion_VO;

public class TestPaginasBK {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Start");
				
		try {
			Iterator it;
			LM_VO lmVO;
			ArrayList lmList=null;
			LM_Manager lmMGR=new LM_Manager();		
			
			Paginacion_VO pag=new Paginacion_VO(5);			
			pag.setFechaProceso("201405");
			
			
			System.out.println("info PAG-------------");
			System.out.println("fechaproceso: "+pag.getFechaProceso());
			System.out.println("ultimo: "+pag.getUltimoLista());
			System.out.println("primero: "+pag.getPrimeroLista());
			System.out.println("pagina Actual: "+pag.getPaginaActual());
			System.out.println("BD: "+pag.getCantidad_BD());
			System.out.println("-------------");
			
			System.out.println("\n1)-------------");
			pag.setPrimeroLista("6");
			pag.setUltimoLista("10");
			pag.setPaginaActual(2);
			pag=lmMGR.paginarRetroceso(pag);
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
			pag=lmMGR.paginarRetroceso(pag);
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
			pag=lmMGR.paginarRetroceso(pag);
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
			pag=lmMGR.paginarRetroceso(pag);
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
			pag=lmMGR.paginarRetroceso(pag);
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
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("CATCH : "+e);
			e.printStackTrace();
		}
		
		System.out.println("END");
		
	}//END main
}//end class
