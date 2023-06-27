/*    */ package cl.araucana.autoconsulta.ui.actions.admin;
/*    */ 
/*    */ import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
/*    */ import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
/*    */ import cl.araucana.autoconsulta.vo.EmpresaVO;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
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
/*    */ public class PrepareDetalleEncargadoAction extends Action
/*    */ {
/* 30 */   private static Logger logger = Logger.getLogger(PrepareDetalleEncargadoAction.class);
/*    */ 
/*    */   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
/*    */     throws Exception
/*    */   {
/* 39 */     HttpSession session = request.getSession(true);
/* 40 */     ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
/* 41 */     String target = null;
/* 42 */     DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
/*    */ 
/* 44 */     long rutEncargado = 0L;
/*    */ 
/* 46 */     String rutEncarga = (String)daf.get("rut");
/*    */ 
/* 48 */     if ((rutEncarga == null) || (rutEncarga.equals(""))) {
/* 49 */       rutEncarga = request.getParameter("rutEncargado");
/*    */     }
/* 51 */     rutEncargado = Long.parseLong(rutEncarga);
/*    */ 
/* 53 */     daf.set("rut", rutEncarga);
/*    */ 
/* 55 */     logger.debug("rutEncargado: " + rutEncargado);
/*    */ 
/* 57 */     EmpresaVO empresa = (EmpresaVO)session.getAttribute("empresa");
/*    */ 
/* 59 */     session.removeAttribute("encargado");
/*    */ 
/* 61 */     ArrayList listaEncargados = (ArrayList)session.getAttribute("encargados");
/* 62 */     logger.debug("Lista: " + listaEncargados.size());
/*    */ 
/* 64 */     boolean loEncontro = false;
/* 65 */     EmpresaACargoVO encargado = new EmpresaACargoVO();
/* 66 */     if (!listaEncargados.isEmpty())
/*    */     {
/* 68 */       for (int x = 0; x < listaEncargados.size(); x++) {
/* 69 */         encargado = (EmpresaACargoVO)listaEncargados.get(x);
/* 70 */         if (encargado.getRutEncargado() == rutEncargado) {
/* 71 */           session.setAttribute("encargado", encargado);
/* 72 */           loEncontro = true;
/* 73 */           logger.debug("encargado: " + encargado.getFullNombre());
/* 74 */           break;
/*    */         }
/*    */       }
/*    */     }
/* 78 */     if (!loEncontro)
/*    */     {
/* 80 */       session.setAttribute("encargado", delegate.getCliente(rutEncargado));
/*    */     }
/*    */ 
/* 84 */     Collection listaOficinasSucursalesEncargado = delegate.getListaOficinasSucursalesByEmpresaEncargado(rutEncargado, empresa.getRut());
/*    */ 
/* 86 */     session.setAttribute("listaOficinasSucursalesEncargado", listaOficinasSucursalesEncargado);
/* 87 */     target = "detalleEncargado";
/*    */ 
/* 89 */     return mapping.findForward(target);
/*    */   }
/*    */ }

/* Location:           C:\tmp\araucaFte\prod\ear\AutoconsultaAdminWeb\WEB-INF\classes\
 * Qualified Name:     cl.araucana.autoconsulta.ui.actions.admin.PrepareDetalleEncargadoAction
 * JD-Core Version:    0.6.0
 */