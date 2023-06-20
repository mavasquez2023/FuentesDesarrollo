package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import cl.laaraucana.silmsil.dao.SIL_DAO;
import cl.laaraucana.silmsil.manager.ManagerProcesar;
import cl.laaraucana.silmsil.vo.SIL_GlosaErrores_VO;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSS");
		String hours = sdf.format(new Date());
		
		System.out.println("Hours : " + hours);
		
	}

}
