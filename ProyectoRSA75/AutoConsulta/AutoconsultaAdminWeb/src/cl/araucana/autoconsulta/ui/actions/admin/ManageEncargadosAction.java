/*     */ package cl.araucana.autoconsulta.ui.actions.admin;
/*     */ 
/*     */ import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
/*     */ import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
/*     */ import cl.araucana.autoconsulta.vo.EmpresaPublicaVO;
/*     */ import cl.araucana.autoconsulta.vo.EmpresaVO;
/*     */ import cl.araucana.common.BusinessException;
/*     */ import cl.araucana.common.ui.UserInformation;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.struts.action.Action;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.validator.DynaValidatorActionForm;
/*     */ 
/*     */ public class ManageEncargadosAction extends Action
/*     */ {
/*  32 */   private static Logger logger = Logger.getLogger(ManageEncargadosAction.class);
/*     */   public static final String GLOBAL_FORWARD_welcome = "welcome";
/*     */   public static final String FORWARD_rutEmpresa = "rutEmpresa";
/*     */   public static final String FORWARD_detalles = "detalles";
/*     */ 
/*     */   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/*  45 */     HttpSession session = request.getSession(true);
/*  46 */     ServicesAutoconsultaDelegate delegate = 
/*  47 */       new ServicesAutoconsultaDelegate();
/*  48 */     String target = null;
/*  49 */     DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
/*     */ 
/*  51 */     String command = request.getParameter("command");
/*  52 */     if (command != null)
/*     */     {
/*  54 */       if (command.equalsIgnoreCase("rutEmpresa")) {
/*  55 */         session.removeAttribute("empresa");
/*  56 */         session.removeAttribute("encargados");
/*  57 */         target = "rutEmpresa";
/*     */       }
/*     */ 
/*  61 */       if (command.equalsIgnoreCase("details")) {
/*  62 */         long rutEmpresa = Long.parseLong((String)daf.get("rut"));
/*     */ 
/*  64 */         EmpresaVO empresa = new EmpresaVO();
/*  65 */         Collection listaEmpresas = delegate.getDatosEmpresa(rutEmpresa);
/*  66 */         if ((listaEmpresas == null) || (listaEmpresas.size() <= 0)) {
/*  67 */           Collection listaEmpresasPublicas = delegate.getDatosEmpresaPublica(rutEmpresa);
/*  68 */           if ((listaEmpresasPublicas == null) || (listaEmpresasPublicas.size() <= 0)) {
/*  69 */             throw new BusinessException(
/*  70 */               "CCAF_BONIF_PARAMETROINVALIDO", 
/*  71 */               "Rut " + rutEmpresa + " no existe");
/*     */           }
/*  73 */           EmpresaPublicaVO empresaPublica = 
/*  74 */             (EmpresaPublicaVO)listaEmpresasPublicas.iterator().next();
/*  75 */           empresa.setDv(empresaPublica.getDv());
/*  76 */           empresa.setEstado(empresaPublica.getEstado());
/*  77 */           empresa.setNombre(empresaPublica.getNombre());
/*  78 */           empresa.setRut(empresaPublica.getRut());
/*  79 */           empresa.setTipo(empresaPublica.getTipo());
/*     */         }
/*     */         else {
/*  82 */           empresa = (EmpresaVO)listaEmpresas.iterator().next();
/*     */         }
/*     */ 
/*  85 */         session.setAttribute("empresa", empresa);
/*  86 */         session.setAttribute(
/*  87 */           "encargados", 
/*  88 */           delegate.getEncargados(rutEmpresa));
/*     */ 
/*  91 */         if (session.getAttribute("usuarios") == null) {
/*  92 */           session.setAttribute("usuarios", delegate.getUsuarios());
/*     */         }
/*     */ 
/*  95 */         target = "detalles";
/*     */       }
/*     */ 
/*  99 */       if (command.equalsIgnoreCase("delete")) {
/* 100 */         EmpresaVO empresa = (EmpresaVO)session.getAttribute("empresa");
/* 101 */         long rutEncargado = Long.parseLong(request.getParameter("rut"));
/* 102 */         EmpresaACargoVO encargado = new EmpresaACargoVO();
/* 103 */         encargado.setRut(empresa.getRut());
/* 104 */         encargado.setRutEncargado(rutEncargado);
/* 105 */         encargado.setDv(empresa.getDv());
/* 106 */         UserInformation usuarioModificador = (UserInformation)session.getAttribute("application.userinformation");
/*     */ 
/* 108 */         delegate.deleteEncargado(encargado, usuarioModificador.getRut());
/* 109 */         session.setAttribute(
/* 110 */           "encargados", 
/* 111 */           delegate.getEncargados(empresa.getRut()));
/* 112 */         target = "detalles";
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 117 */     daf.set("rut", "");
/* 118 */     return mapping.findForward(target);
/*     */   }
/*     */ }

/* Location:           C:\tmp\araucaFte\prod\ear\AutoconsultaAdminWeb\WEB-INF\classes\
 * Qualified Name:     cl.araucana.autoconsulta.ui.actions.admin.ManageEncargadosAction
 * JD-Core Version:    0.6.0
 */