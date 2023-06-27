package month;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilMonth.Fecha;
import cl.araucana.cp.hibernate.beans.MesesbeanVO;
import cl.araucana.cp.hibernate.dao.monthDAO;

 
import java.util.*;
 

 
public class listMonth extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2092218645846416088L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public listMonth() {
		super();
	}   	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fecha1=request.getParameter("fechaProceso");
		String fecha2=request.getParameter("FechaProceso2");
		String meses=request.getParameter("ListaMeses");
		String rutaf=request.getParameter("listaRut");
		String rutem=request.getParameter("RutEmpresa");
		String sucursal=request.getParameter("sucursal");
		String convenio=request.getParameter("Convenio");
		String tipoproceso=request.getParameter("TipoProceso");
		String rutaf1=request.getParameter("RutTrabajador");
		String nombreTrabajador=request.getParameter("NombreTrabajador");
		String holding=request.getParameter("holdingA");
		String listaRut="";
		String Convenio=""; 
		String rutEmpresa="";
		String csucursal="";
		String sucur2="";
		String RutTrab="";
		String cholding="";


		String rutaf2="";
		if(rutaf.indexOf(" ")>0){
			rutaf=rutaf.replaceAll("  ", " ");
			rutaf=rutaf.trim().replaceAll(" ",",");

			String lista[]=rutaf.split(",");
			for(int i=0;i<lista.length;i++){

				while(lista[i].length()<9){
					lista[i]=" ".concat(lista[i]);
				}

				rutaf2=rutaf2 + lista[i] + ",";
			}

			rutaf2=rutaf2.substring(0,rutaf2.length()-1);
			listaRut=rutaf2;
		}else{
			if(rutaf!=null && !rutaf.trim().equals("")){
				while(rutaf.length()<9){
					rutaf=" ".concat(rutaf);
				}
			}
			listaRut=rutaf;
		}
		
		if(sucursal.trim().indexOf(" ")>0){
			sucursal=sucursal.replaceAll("  ", " ");
			csucursal=sucursal.trim().replaceAll(" ","','");
		}else{
			csucursal= sucursal;
		}
		csucursal=csucursal.trim();

		if(holding.trim().indexOf(" ")>0){
			holding=holding.replaceAll("  ", " ");
			cholding=holding.trim().replaceAll(" ",",");
		}else{
			cholding= holding;
		}
		cholding=cholding.trim();

		String rutEmpresa2="";
		if(rutem.indexOf(" ")>-1){
			rutem=rutem.replaceAll("  ", " ");
			rutEmpresa=rutem.trim().replaceAll(" ",",");

			String lista[]=rutEmpresa.split(",");
			for(int i=0;i<lista.length;i++){
				while(lista[i].length()<9){
					lista[i]=" ".concat(lista[i]);
				}
				rutEmpresa2=rutEmpresa2 + lista[i] + ",";
			}
			rutEmpresa2=rutEmpresa2.substring(0,rutEmpresa2.length()-1);
			rutEmpresa=rutEmpresa2;
		}else{	
			while(rutem.length()<9)	{
				rutem= " ".concat(rutem);
			}
			rutEmpresa=rutem;
		}

		//System.out.println(">>rut empresa" + rutEmpresa);

		if(convenio.trim().indexOf(" ")>-1){
			Convenio=convenio.trim().replaceAll(" ", "','");
		}
		else{
			Convenio=convenio;
		}
		if(rutaf1==null){
			rutaf1="";
		}
		if(nombreTrabajador==null){
			nombreTrabajador="";
		}
		if(rutem==null){
			rutem="";
		}
		HttpSession sesion=request.getSession(true);
		sesion.setAttribute("tipoproceso",tipoproceso);
		MesesbeanVO omeses=new MesesbeanVO();

		if (meses != null) {
			Fecha fecha=new Fecha();  
			Integer fechaFinal=fecha.getFecha(meses);
			omeses.setFecha(fechaFinal);
		}else {
			omeses.setFecha1(fecha1);
			omeses.setFecha2(fecha2);
		}

		omeses.setRutEmpresa(rutEmpresa);
		omeses.setConvenio(Convenio);
		omeses.setRutTrabajador(rutaf1.trim());
		omeses.setTipoProceso(tipoproceso.trim());
		omeses.setCentid(csucursal);
		omeses.setMes(meses);
		omeses.setListaRut(listaRut);
		omeses.setNombreTrabajador(nombreTrabajador);
		omeses.setHolding(cholding);

		monthDAO mdao= new monthDAO();
		List lista = null; 
		String aux = request.getParameter("aux");

		if (aux.equals("1")) {
			lista=mdao.getDatos(omeses);
		}else {
			lista=mdao.getDatos2(omeses);
		}

		if(lista!=null){ 
			sesion.setAttribute("parametros",lista);
			response.sendRedirect("test.jsp");
		}else{
			response.sendRedirect("emptyList.html"); 
		}
	}   	  	   
}