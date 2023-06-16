

/*
 * @(#) Inicio.java    1.0 23/09/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.dao.SQLDAO;
import cl.araucana.cierrecpe.dao.TesoDAO;
import cl.recursos.ConectaDB2;




/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 23/09/2010 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class SQLAction extends Action {
	//private ConectaDB2 db2;
	private TesoDAO tesoDAO=null;
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception 
	{	
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		List sqlin= new ArrayList();
		List sqlres= new ArrayList();
		String sqlactual="";
		String listsql[]=null;
		int insert=0;

		try{
			tesoDAO= new TesoDAO();
			forward = mapping.findForward("OK");
			HttpSession sesion= request.getSession();
			String sql= request.getParameter("sql");
			String clave= request.getParameter("clave");
			
			if(clave.equals("4321")){
				listsql= sql.split(";");
				for (int i = 0; i < listsql.length; i++) {
					if(!listsql[i].trim().equals("")){
						if (listsql[i].length()>2 && !listsql[i].substring(0, 2).equals("--") && ( listsql[i].indexOf("insert into")>-1 || listsql[i].indexOf("update")>-1)){
							sqlin.add(listsql[i]+";");
						}
					}
				}
				if(sqlin.size()>0){
					//tesoDAO= new TesoDAO();
					if(!tesoDAO.getConnection().isClosed()){
						SQLDAO sqlDAO= new SQLDAO(tesoDAO.getConnection());
						for (int i = 0; i < listsql.length; i++) {
							if(!listsql[i].trim().equals("")){
								if (listsql[i].length()>2 && !listsql[i].substring(0, 2).equals("--") && ( listsql[i].indexOf("insert into")>-1 || (listsql[i].indexOf("update")>-1) && listsql[i].indexOf("where")>-1)){
									sqlactual= listsql[i].trim();
									int num= sqlDAO.insert(sqlactual);
									insert+= num;
									sqlres.add(insert  + ". --> OK (" + num +"):" + listsql[i]);
									sqlin.remove(0);
								}else{
									sqlres.add(insert  + ". --> ERROR (Funciones permitidas INSERT o UPDATE condicionadas con WHERE):" + listsql[i]);
									sqlin.remove(0);
								}
							}
						}
					}else{
						sqlres.add("<font color='#ff0000'>" + "Error de Conexión a DB2" + "</font");
					}
				}
			}else{
				sqlres.add("<font color='#ff0000'>" + "Error de Autorización" + "</font");
			}
			
			
		}catch (Exception e) {
			sqlres.add("<font color='#ff0000'>" + insert  + ". --> ERROR :" + sqlactual + "</font");
			sqlres.add("Mensaje:" + e.getMessage());
		}
		finally{
			request.setAttribute("consulta", sqlin);
			request.setAttribute("resultado", sqlres);
			if (tesoDAO!= null){
				tesoDAO.closeConnectionDAO();
			}
		}
		return forward;
	}
	
}

