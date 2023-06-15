/*     */ package cl.araucana.autoconsulta.ui.actions.liquidacionReembolsos;
/*     */ 
/*     */ import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
/*     */ import cl.araucana.autoconsulta.vo.EmpresaPublicaVO;
/*     */ import cl.araucana.autoconsulta.vo.EmpresaVO;
/*     */ import cl.araucana.autoconsulta.vo.LiquidacionesVO;
/*     */ import cl.araucana.autoconsulta.vo.UsuarioVO;
/*     */ import java.io.PrintStream;
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
/*     */ public class GetListaLiquidacionesAction extends Action
/*     */ {
/*     */   private String textRutAfiliado;
/*  31 */   private static Logger logger = Logger.getLogger(GetListaLiquidacionesAction.class);
/*     */   public static final String FORWARD_listaLiquidaciones = "listaLiquidaciones";
/*     */   public static final String FORWARD_muestraLiquidacion = "getLiquidacionReembolso";
/*     */   public static final String FORWARD_periodoLiquidacion = "definirConvPeriodo";
/*     */   public static final String FORWARD_sinLiquidacion = "sinLiquidacion";
/*     */   public static final String FORWARD_periodoLiquidacionAdm = "definirConvPeriodoAdm";
/*     */   public static final String GLOBAL_FORWARD_definirEmpresa = "definirEmpresa";
/*     */   public static final String GLOBAL_FORWARD_definirPersona = "definirEmpleado";
/*     */ 
/*     */   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/*  53 */     HttpSession session = request.getSession(true);
/*  54 */     DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
/*     */ 
/*  56 */     if (!daf.get("rut").equals("")) {
/*  57 */       session.setAttribute("rutDelEmpleado", daf.get("rut"));
/*  58 */       session.setAttribute("rutDVDelEmpleado", daf.get("dv"));
/*     */     }
/*     */ 
/*  61 */     ServicesAutoconsultaDelegate delegate = 
/*  62 */       new ServicesAutoconsultaDelegate();
/*  63 */     String target = null;
/*     */ 
/*  65 */     //String dispositivo = request.getRemoteAddr();
/*  66 */     //logger.debug("IP: " + dispositivo);
/*     */ 
/*  68 */     UsuarioVO usuario = (UsuarioVO)session.getAttribute("datosUsuario");
/*  69 */     // long rut = usuario.getRut();
              usuario.setRutAfiliado(usuario.getRut());
              usuario.setIpConexion(request.getRemoteAddr());
/*  70 */     String nombreConsulta = usuario.getNombre();
/*  71 */     String rutConsulta = usuario.getFullRut();
/*     */ 
/*  74 */     //logger.debug("Rut: " + rut);
/*     */ 
/*  76 */     if ((usuario.isEsEmpresa()) || (usuario.isEsEmpresaPublica())) {
/*  77 */       request.setAttribute("liqReembol", "empresa");
/*     */ 
/*  79 */       String textRutEmpresa = String.valueOf(usuario.getRut());
/*  80 */       String nroConv = (String)daf.get("nroConv");
/*  81 */       logger.debug("nroConv :" + nroConv);
/*  82 */       String periodo = (String)daf.get("periodo");
/*  83 */       logger.debug("periodo : " + periodo);
/*     */       Collection listaPeriodos;
/*  84 */       if ((nroConv == null) || (nroConv.length() == 0) || 
/*  85 */         (periodo == null) || (periodo.length() == 0)) {
/*  86 */         System.out.println(
/*  87 */           "consulta listado de periodos prest. compl.");
/*  88 */         listaPeriodos = delegate.getPeriodosPrestCompl(usuario.getRutAfiliado());
/*  89 */         System.out.println("sale consulta listado de periodos.");
/*  90 */         session.setAttribute("lista.periodos", listaPeriodos);
/*  91 */         target = "definirConvPeriodo";
/*     */ 
/*  93 */         logger.debug("A definir periodo");
/*     */       }
/*     */       else {
/*  96 */         listaPeriodos = 
/*  97 */           delegate.getResumenMensualByEmpresa(
/*  98 */           usuario.getRutAfiliado(), 
/*  99 */           Long.parseLong(nroConv), 
/* 100 */           periodo);
/*     */       }
/*     */ 
/* 106 */       return mapping.findForward(target);
/*     */     }
/*     */ 
/* 109 */     request.setAttribute("liqReembol", "persona");
/* 110 */     if (request.getParameter("tipo") != null)
/*     */     {
/* 113 */       request.getSession().removeAttribute("validation.message");
/* 114 */       if (request.getParameter("tipo").equalsIgnoreCase("RPC"))
/*     */       {
/* 116 */         String textRutEmpresa = (String)daf.get("rut");
/* 117 */         String textDigEmpresa = (String)daf.get("dv");
/*     */ 
/* 119 */         if (textRutEmpresa.equals(""))
/*     */         {
/* 121 */           session.setAttribute(
/* 122 */             "volverA", 
/* 123 */             "getListaLiquidaciones?tipo=RPC");
/* 124 */           request.getSession().removeAttribute(
/* 125 */             "datosEmpresaLiqReembCOSAL");
/* 126 */           request.getSession().setAttribute(
/* 127 */             "validation.message", 
/* 128 */             "");
/* 129 */           target = "definirEmpresa";
/*     */         }
/*     */         else {
/* 132 */           session.removeAttribute("validation.message");
/* 133 */           long rutEmpresa = new Long(textRutEmpresa).longValue();
/* 134 */           String nroConv = (String)daf.get("nroConv");
/* 135 */           String periodo = (String)daf.get("periodo");
/*     */ 
/* 137 */           if ((nroConv == null) || (nroConv.length() == 0) || 
/* 138 */             (periodo == null) || (periodo.length() == 0))
/*     */           {
/* 140 */             Collection listaPeriodos = 
/* 141 */               delegate.getPeriodosPrestCompl(rutEmpresa);
/* 142 */             session.setAttribute(
/* 143 */               "lista.periodos", 
/* 144 */               listaPeriodos);
/* 145 */             target = "definirConvPeriodoAdm";
/*     */ 
/* 148 */             Collection listaEmpresas = 
/* 149 */               delegate.getDatosEmpresa(rutEmpresa);
/* 150 */             if ((listaEmpresas == null) || 
/* 151 */               (listaEmpresas.size() <= 0)) {
/* 152 */               listaEmpresas = delegate.getDatosEmpresaPublica(rutEmpresa);
/* 153 */               if ((listaEmpresas == null) || 
/* 154 */                 (listaEmpresas.size() <= 0))
/*     */               {
/* 156 */                 daf.set("rut", "");
/* 157 */                 daf.set("dv", "");
/* 158 */                 session.setAttribute(
/* 159 */                   "validation.message", 
/* 160 */                   "errors.validation.empresaSinLiquidaciones");
/* 161 */                 target = "definirEmpresa";
/*     */               }
/*     */               else {
/* 164 */                 UsuarioVO usuarioLiqReemb = new UsuarioVO();
/* 165 */                 EmpresaPublicaVO empresaPublica = 
/* 166 */                   (EmpresaPublicaVO)listaEmpresas.iterator().next();
/* 167 */                 usuarioLiqReemb.setDv(textDigEmpresa);
/* 168 */                 usuarioLiqReemb.setRut(rutEmpresa);
/* 169 */                 usuarioLiqReemb.setNombre(empresaPublica.getNombre());
/* 170 */                 request.getSession().setAttribute(
/* 171 */                   "adminCOSAL", 
/* 172 */                   "true");
/* 173 */                 request.getSession().setAttribute(
/* 174 */                   "datosEmpresaLiqReembCOSAL", 
/* 175 */                   usuarioLiqReemb);
/*     */               }
/*     */             }
/*     */             else {
/* 179 */               UsuarioVO usuarioLiqReemb = new UsuarioVO();
/* 180 */               EmpresaVO empresa = 
/* 181 */                 (EmpresaVO)listaEmpresas.iterator().next();
/* 182 */               usuarioLiqReemb.setDv(textDigEmpresa);
/* 183 */               usuarioLiqReemb.setRut(rutEmpresa);
/* 184 */               usuarioLiqReemb.setNombre(empresa.getNombre());
/* 185 */               request.getSession().setAttribute(
/* 186 */                 "adminCOSAL", 
/* 187 */                 "true");
/* 188 */               request.getSession().setAttribute(
/* 189 */                 "datosEmpresaLiqReembCOSAL", 
/* 190 */                 usuarioLiqReemb);
/*     */             }
/*     */           }
/*     */           else {
/* 194 */             Collection listaResumen = 
/* 195 */               delegate.getResumenMensualByEmpresa(
/* 196 */               usuario.getRutAfiliado(), 
/* 197 */               Long.parseLong(nroConv), 
/* 198 */               periodo);
/* 199 */             target = "definirConvPeriodoAdm";
/*     */           }
/*     */         }
/*     */ 
/* 203 */         return mapping.findForward(target);
/*     */       }
/*     */ 
/* 206 */       if (request.getParameter("tipo").equalsIgnoreCase("LRE"))
/*     */       {
/* 208 */         String textRutPersona = (String)daf.get("rut");
/* 209 */         String textDigPersona = (String)daf.get("dv");
/*     */ 
/* 211 */         if (textRutPersona.equals(""))
/*     */         {
/* 213 */           session.setAttribute(
/* 214 */             "volverA", 
/* 215 */             "getListaLiquidaciones?tipo=LRE");
/* 216 */           request.getSession().removeAttribute(
/* 217 */             "datosPersonaLiqReembCOSAL");
/* 218 */           target = "definirEmpleado";
/*     */         } else {
/* 220 */           //long rutPersona = new Long(textRutPersona).longValue();
	                usuario.setRutAfiliado(new Long(textRutPersona).longValue());
/* 221 */           UsuarioVO usuarioLiqReemb = 
/* 222 */             delegate.getDatosUsuario(usuario.getRutAfiliado());
/*     */ 
/* 224 */           Collection listaLiquidaciones = 
/* 225 */             delegate.getLiquidacionReembolsosByRut(usuario);
/* 226 */           if (listaLiquidaciones.size() > 0) {
/* 227 */             if (listaLiquidaciones.size() > 1) {
/* 228 */               target = "listaLiquidaciones";
/* 229 */               session.setAttribute(
/* 230 */                 "lista.liquidaciones", 
/* 231 */                 listaLiquidaciones);
/*     */             } else {
/* 233 */               Iterator itLiq = listaLiquidaciones.iterator();
/* 234 */               LiquidacionesVO liquidacion = 
/* 235 */                 (LiquidacionesVO)itLiq.next();
/* 236 */               liquidacion.getNroLiquidacion();
/* 237 */               session.setAttribute(
/* 238 */                 "lista.liquidaciones", 
/* 239 */                 listaLiquidaciones);
/* 240 */               daf.set(
/* 241 */                 "nroliq", 
/* 242 */                 String.valueOf(
/* 243 */                 liquidacion.getNroLiquidacion()));
/*     */ 
/* 245 */               target = "getLiquidacionReembolso";
/*     */             }
/* 247 */             usuarioLiqReemb.setDv(textDigPersona);
/* 248 */             usuarioLiqReemb.setRut(usuario.getRutAfiliado());
/* 249 */             request.getSession().setAttribute(
/* 250 */               "adminCOSAL", 
/* 251 */               "true");
/* 252 */             request.getSession().setAttribute(
/* 253 */               "datosPersonaLiqReembCOSAL", 
/* 254 */               usuarioLiqReemb);
/*     */           }
/*     */           else {
/* 257 */             logger.debug(
/* 258 */               "No tiene Liquidaciones ******: " + 
/* 259 */               this.textRutAfiliado);
/* 260 */             target = "sinLiquidacion";
/* 261 */             session.setAttribute(
/* 262 */               "sin.datos", 
/* 263 */               "label.liquidacion.sin.liquidaciones");
/*     */           }
/*     */         }
/*     */ 
/* 267 */         return mapping.findForward(target);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 274 */     Collection listaLiquidaciones = 
/* 275 */       delegate.getLiquidacionReembolsosByRut(usuario);
/* 276 */     if (listaLiquidaciones.size() > 0) {
/* 277 */       if (listaLiquidaciones.size() > 1) {
/* 278 */         logger.debug("Lista Liquidaciones : " + this.textRutAfiliado);
/* 279 */         target = "listaLiquidaciones";
/* 280 */         session.setAttribute(
/* 281 */           "lista.liquidaciones", 
/* 282 */           listaLiquidaciones);
/* 283 */         this.textRutAfiliado = String.valueOf(usuario.getRut());
/*     */       } else {
/* 285 */         Iterator itLiq = listaLiquidaciones.iterator();
/* 286 */         LiquidacionesVO liquidacion = 
/* 287 */           (LiquidacionesVO)itLiq.next();
/* 288 */         liquidacion.getNroLiquidacion();
/* 289 */         logger.debug("Solo 1 liquidacion: " + this.textRutAfiliado);
/* 290 */         session.setAttribute(
/* 291 */           "lista.liquidaciones", 
/* 292 */           listaLiquidaciones);
/* 293 */         daf.set(
/* 294 */           "nroliq", 
/* 295 */           String.valueOf(liquidacion.getNroLiquidacion()));
/*     */ 
/* 299 */         target = "getLiquidacionReembolso";
/* 300 */         this.textRutAfiliado = String.valueOf(usuario.getRut());
/*     */       }
/*     */     } else {
/* 303 */       logger.debug(
/* 304 */         "No tiene Liquidaciones ******: " + this.textRutAfiliado);
/*     */ 
/* 308 */       target = "sinLiquidacion";
/* 309 */       session.setAttribute(
/* 310 */         "sin.datos", 
/* 311 */         "label.liquidacion.sin.liquidaciones");
/* 312 */       logger.debug("target : " + target);
/*     */     }
/*     */ 
/* 315 */     return mapping.findForward(target);
/*     */   }
/*     */ }

