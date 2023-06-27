/*    */ package cl.araucana.autoconsulta.ui.actions.admin;
/*    */ 
/*    */ import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
/*    */ import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
/*    */ import cl.araucana.autoconsulta.vo.EmpresaVO;
/*    */ import cl.araucana.common.ui.UserInformation;
/*    */ import java.util.ArrayList;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.struts.action.Action;
/*    */ import org.apache.struts.action.ActionForm;
/*    */ import org.apache.struts.action.ActionForward;
/*    */ import org.apache.struts.action.ActionMapping;
/*    */ import org.apache.struts.validator.DynaValidatorActionForm;
/*    */ 
/*    */ public class RegistrarEncargadoAction extends Action
/*    */ {
/* 29 */   private static Logger logger = Logger.getLogger(RegistrarEncargadoAction.class);
/*    */ 
/*    */   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
/*    */     throws Exception
/*    */   {
/* 39 */     HttpSession session = request.getSession(true);
/* 40 */     ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
/* 41 */     String target = null;
/* 42 */     DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
/*    */ 
/* 44 */     ArrayList listaOficinasSucursalesEncargado = (ArrayList)request.getSession(false).getAttribute("listaOficinasSucursalesEncargado");
/*    */ 
/* 46 */     String[] indiceOficinasSeleccionadas = request.getParameterValues("indice");
/*    */ 
/* 48 */     UserInformation usuarioModificador = (UserInformation)session.getAttribute("application.userinformation");
/*    */ 
/* 50 */     EmpresaVO empresa = (EmpresaVO)session.getAttribute("empresa");
/* 51 */     long rutEncargado = Long.parseLong((String)daf.get("rut"));
/*    */ 
/* 53 */     String nombre = (String)daf.get("nombre");
/* 54 */     String apellidop = (String)daf.get("apellidop");
/* 55 */     String apellidom = (String)daf.get("apellidom");
/*    */ 
/* 58 */     EmpresaACargoVO encargado = new EmpresaACargoVO();
/*    */ 
/* 60 */     encargado.setRut(empresa.getRut());
/* 61 */     encargado.setRutEncargado(rutEncargado);
/* 62 */     encargado.setDv(empresa.getDv());
/* 63 */     encargado.setListaOficinasSucursalesACargo(listaOficinasSucursalesEncargado);
/*    */ 
/* 65 */     encargado.setNombre(nombre.trim().toUpperCase());
/* 66 */     encargado.setApellidoPaterno(apellidop.trim().toUpperCase());
/* 67 */     encargado.setApellidoMaterno(apellidom.trim().toUpperCase());
/*    */ 
/* 69 */     delegate.registrarEncargado(encargado, indiceOficinasSeleccionadas, usuarioModificador.getRut());
/*    */ 
/* 71 */     logger.debug("registro encargado rut: " + encargado.getRutEncargado());
/* 72 */     logger.debug("registro encargado en empresa rut: " + encargado.getRut());
/*    */ 
/* 74 */     ArrayList listaEncargados = (ArrayList)delegate.getEncargados(empresa.getRut());
/* 75 */     logger.debug("listaEncargados.size() : " + listaEncargados.size());
/* 76 */     session.setAttribute("encargados", listaEncargados);
/*    */ 
/* 79 */     for (int x = 0; x < listaEncargados.size(); x++) {
/* 80 */       EmpresaACargoVO encargadoVO = (EmpresaACargoVO)listaEncargados.get(x);
/* 81 */       if (encargadoVO.getRutEncargado() == rutEncargado) {
/* 82 */         session.setAttribute("encargado", encargadoVO);
/* 83 */         logger.debug("encargado: " + encargadoVO.getFullNombre());
/* 84 */         break;
/*    */       }
/*    */     }
/*    */ 
/* 88 */     daf.set("rut", "");
/* 89 */     daf.set("nombre", "");
/* 90 */     daf.set("apellidop", "");
/* 91 */     daf.set("apellidom", "");
/* 92 */     target = "detalles";
/*    */ 
/* 94 */     return mapping.findForward(target);
/*    */   }
/*    */ }

/* Location:           C:\tmp\araucaFte\prod\ear\AutoconsultaAdminWeb\WEB-INF\classes\
 * Qualified Name:     cl.araucana.autoconsulta.ui.actions.admin.RegistrarEncargadoAction
 * JD-Core Version:    0.6.0
 */