/*    */ package cl.araucana.autoconsulta.ui.actions.admin;
/*    */ 
/*    */ import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
/*    */ import java.util.Collection;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.struts.action.Action;
/*    */ import org.apache.struts.action.ActionForm;
/*    */ import org.apache.struts.action.ActionForward;
/*    */ import org.apache.struts.action.ActionMapping;
/*    */ import org.apache.struts.action.DynaActionForm;
/*    */ 
/*    */ public class ManagePublicAction extends Action
/*    */ {
/*    */   public static final String FORWARD_muestraLiquidacion = "listaPublicidad";
/* 28 */   private static Logger logger = Logger.getLogger(ManagePublicAction.class);
/*    */ 
/*    */   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
/*    */     throws Exception
/*    */   {
/* 36 */     HttpSession session = request.getSession(true);
/* 37 */     ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
/* 38 */     String target = null;
/*    */ 
/* 40 */     DynaActionForm daf = (DynaActionForm)form;
/* 41 */     session.removeAttribute("publicidad");
/* 42 */     session.removeAttribute("actualizacion");
/*    */     try
/*    */     {
/* 46 */       String texto = (String)daf.get("texto");
/* 47 */       String accion = (String)daf.get("accion");
/* 48 */       logger.debug("texto : " + texto);
/* 49 */       logger.debug("accion : " + accion);
/* 50 */       if ((accion.equals("get")) || (accion.equals(""))) {
/* 51 */         Collection colPublic = delegate.getPublicidad();
/* 52 */         session.setAttribute("publicidad", colPublic);
/*    */ 
/* 54 */         target = "listaPublicidad";
/*    */       } else {
/* 56 */         delegate.putPublicidad(texto.trim());
/* 57 */         Collection colPublic = delegate.getPublicidad();
/* 58 */         session.setAttribute("publicidad", colPublic);
/* 59 */         session.setAttribute("actualizacion", "mensaje.no.actualizado");
/* 60 */         target = "listaPublicidad";
/*    */       }
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 65 */       logger.debug("Exception : " + e.getMessage());
/*    */     }
/*    */ 
/* 73 */     return mapping.findForward(target);
/*    */   }
/*    */ }

/* Location:           C:\tmp\araucaFte\prod\ear\AutoconsultaPublicWeb\WEB-INF\classes\
 * Qualified Name:     cl.araucana.autoconsulta.ui.actions.admin.ManagePublicAction
 * JD-Core Version:    0.6.0
 */