package cl.araucana.ctasfam.presentation.struts.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.ctasfam.business.service.impl.PropuestaRentasServiceImpl;
import cl.araucana.ctasfam.business.to.DetalleExcelTO;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.Mejoras2016DaoImpl;
import cl.araucana.ctasfam.integration.jdbc.exception.RentaPropuestasException;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.EmpresaComprobante;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;

public class EstadosProcesamientosDPAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Encargado enc1 =new Encargado();
		enc1=(Encargado)request.getSession().getAttribute("edocs_encargado");

        String empresas="";
		List<EmpresaComprobante> listEmpComp = new ArrayList<EmpresaComprobante>();
		for (Iterator iter = enc1.getEmpresas().iterator(); iter.hasNext();) {
			Empresa empresa = (Empresa) iter.next();
			
			
//			String rutEmp = empresa.getRut() + "-" + empresa.getDV();
			empresas =empresa.getRut()+"," ; 
		}
		empresas = empresas.substring(0, empresas.length()-1);
		
		System.out.println("EMPRESAS!!");
		System.out.println(empresas);
		
		
		
		 List resultResumen = new ArrayList();
		 List resultDetalle = new ArrayList();
		  
		 Mejoras2016DaoImpl dao = new Mejoras2016DaoImpl();
		 
		try {
			resultResumen = dao.selectResumenXLS(empresas);
			resultDetalle = dao.selectDetalleXLS(empresas);
		} catch (RentaPropuestasException e) {
			e.printStackTrace();
		}
		  
		  request.getSession().setAttribute("resumen_xls", resultResumen);
		  request.getSession().setAttribute("detalle_xls", resultDetalle);
		  
		  response.setHeader("Content-disposition", "attachment;filename=EstadosProcesamientosDP.xls");
		  response.setContentType("application/vnd.ms-excel");
		
		
		
		
		return mapping.findForward("onSuccess");
	}

}
