package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import cl.laaraucana.silmsil.dao.PLANOSDAO;
import cl.laaraucana.silmsil.manager.ManagerArchivosPlanos;
import cl.laaraucana.silmsil.vo.ILFSIL052VO;

public class testEstadistico {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String ahno = "2013";
		String usuario = "USUARIOJAVA";
		boolean resp=false;
		ArrayList checkBoxSIL = new ArrayList(); 
		ArrayList checkBoxLM = new ArrayList();
		
		checkBoxSIL.add("chk_SIL03");
		checkBoxLM.add("chk_LM03");
		
		checkBoxSIL.add("chk_SIL04");
		checkBoxLM.add("chk_LM04");
		
		
		ArrayList procLista=new ArrayList();
		
		ManagerArchivosPlanos ceMGR=new ManagerArchivosPlanos();
	//	resp=ceMGR.generarPlanoEstadistico(checkBoxSIL, checkBoxLM, ahno, usuario);
		System.out.println("RESP GE: "+resp);
		//ceMGR.generarPlanoEstadistico(procLista, fecha, usuario);
		
	}//end main
}//end class
