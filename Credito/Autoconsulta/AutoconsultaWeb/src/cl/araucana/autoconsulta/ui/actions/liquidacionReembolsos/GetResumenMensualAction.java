/*     */ package cl.araucana.autoconsulta.ui.actions.liquidacionReembolsos;
/*     */ 
/*     */ import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
/*     */ import cl.araucana.autoconsulta.vo.ConvenioEmpresaVO;
/*     */ import cl.araucana.autoconsulta.vo.ResMensualPrestCompVO;
/*     */ import cl.araucana.autoconsulta.vo.TotalesPorEmpresaVO;
/*     */ import cl.araucana.autoconsulta.vo.UsuarioVO;
/*     */ import java.io.PrintStream;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
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
/*     */ public class GetResumenMensualAction extends Action
/*     */ {
/*  35 */   private String textRutAfiliado = null;
/*  36 */   private String periodoConsulta = null;
/*  37 */   private String c1 = "no"; private String c2 = "no"; private String c3 = "no"; private String c4 = "no"; private String c5 = "no"; private String c6 = "no"; private String c7 = "no"; private String c8 = "no";
/*  38 */   private String c9 = "no"; private String c10 = "no"; private String c11 = "no"; private String c12 = "no"; private String c13 = "no"; private String c14 = "no"; private String c15 = "no"; private String c16 = "no";
/*  39 */   private String c18 = "no"; private String c19 = "no"; private String c20 = "no"; private String c21 = "no";
/*     */   private String fecha1;
/*     */   private String fecha2;
/*     */   private String fecha3;
/*     */   private String fecha4;
/*     */   private String fecha5;
/*     */   private String fecha6;
/*     */   private String fecha7;
/*     */   private String fecha8;
/*     */   private String fecha9;
/*     */   private String fecha10;
/*     */   private String fecha11;
/*     */   private String fecha12;
/*     */   private String fecha13;
/*     */   private String fecha14;
/*     */   private String fecha15;
/*     */   private String fecha16;
/*     */   private String fecha17;
/*     */   private String fechaCierre;
/*     */   private String fecha18;
/*     */   private String fecha19;
/*     */   private String fecha20;
/*     */   private String fecha21;
/*  44 */   private static Logger logger = Logger.getLogger(GetResumenMensualAction.class);
/*     */   public static final String FORWARD_listaResumen = "listaResumen";
/*     */   public static final String FORWARD_sinLiquidacion = "sinLiquidacion";
/*     */ 
/*     */   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/*  55 */     HttpSession session = request.getSession(true);
/*  56 */     DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
/*     */ 
/*  58 */     ServicesAutoconsultaDelegate delegate = 
/*  59 */       new ServicesAutoconsultaDelegate();
/*  60 */     String target = null;
/*  61 */     String periodo = null;
/*  62 */     long agno = 0L;
/*  63 */     long mes = 0L;
/*  64 */     int agnoAnt = 0;
/*  65 */     int mesAnt = 0;
/*  66 */     long rut = 0L;
/*  67 */     long nroCnv = 0L;
/*     */ 
/*  69 */     String dispositivo = request.getRemoteAddr();
/*  70 */     logger.debug("Generando resumen mensual");
/*  71 */     periodo = (String)daf.get("periodo");
/*  72 */     System.out.println("periodo : " + periodo);
/*  73 */     logger.debug("Periodo : " + periodo);
/*  74 */     this.periodoConsulta = periodo;
/*     */ 
/*  76 */     String dateOut = null;
/*  77 */     SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM yyyy");
/*  78 */     SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyyMM");
/*     */ 
/*  80 */     Date today = null;
/*     */     try {
/*  82 */       today = dateFormatter1.parse(periodo);
/*     */     } catch (ParseException e) {
/*  84 */       dateOut = "enero 1900";
/*     */     }
/*  86 */     dateOut = dateFormatter.format(today);
/*  87 */     System.out.println("dateOut: " + dateOut);
/*  88 */     agno = Long.parseLong(periodo.substring(0, 4));
/*  89 */     mes = Long.parseLong(periodo.substring(4, 6));
/*  90 */     System.out.println("mes periodo: " + mes);
/*     */ 
/*  92 */     agnoAnt = Integer.parseInt(periodo.substring(0, 4));
/*  93 */     mesAnt = Integer.parseInt(periodo.substring(4, 6));
/*  94 */     System.out.println("mesAnt periodo: " + mesAnt);
/*     */ 
/*  96 */     UsuarioVO usuario = null;
/*  97 */     if ((request.getSession().getAttribute("adminCOSAL") != null) && (request.getSession().getAttribute("adminCOSAL").equals("true")))
/*  98 */       usuario = (UsuarioVO)session.getAttribute("datosEmpresaLiqReembCOSAL");
/*     */     else {
/* 100 */       usuario = (UsuarioVO)session.getAttribute("datosUsuario");
/*     */     }
/*     */ 
/* 103 */     rut = usuario.getRut();
/*     */ 
/* 105 */     logger.debug("Rut: " + rut);
/*     */     try
/*     */     {
/* 109 */       Collection Lconvenio = delegate.getNumeroConvenio(rut);
/* 110 */       if (!Lconvenio.isEmpty()) {
/* 111 */         Iterator itLconvenio = Lconvenio.iterator();
/* 112 */         if (itLconvenio.hasNext()) {
/* 113 */           ConvenioEmpresaVO cnv = 
/* 114 */             (ConvenioEmpresaVO)itLconvenio.next();
/* 115 */           Collection tips = delegate.getTip(agno, mes, cnv.getTipFija(), cnv.getTipValor());
/* 116 */           nroCnv = cnv.getNroConvevio();
/* 117 */           logger.debug("Numero convenio: " + nroCnv);
/* 118 */           Collection listaResumen = 
/* 119 */             delegate.getResumenMensualByEmpresa(
/* 120 */             rut, 
/* 121 */             nroCnv, 
/* 122 */             periodo);
/* 123 */           System.out.println("listaResumen.size" + listaResumen.size());
/* 124 */           if (!listaResumen.isEmpty()) {
/* 125 */             session.setAttribute("lista.resumen", listaResumen);
/* 126 */             session.setAttribute("empresa.convenio", cnv);
/* 127 */             session.setAttribute("periodo", dateOut);
/* 128 */             session.setAttribute("tips", tips);
/* 129 */             session.setAttribute("fechaHoy", new Date());
/* 130 */             session.setAttribute(
/* 131 */               "totales", 
/* 132 */               getTotales(listaResumen, agnoAnt, mesAnt - 1));
/*     */ 
/* 134 */             session.setAttribute("c1", this.c1);
/* 135 */             session.setAttribute("c2", this.c2);
/* 136 */             session.setAttribute("c3", this.c3);
/* 137 */             session.setAttribute("c4", this.c4);
/* 138 */             session.setAttribute("c5", this.c5);
/* 139 */             session.setAttribute("c6", this.c6);
/* 140 */             session.setAttribute("c7", this.c7);
/* 141 */             session.setAttribute("c8", this.c8);
/* 142 */             session.setAttribute("c9", this.c9);
/* 143 */             session.setAttribute("c10", this.c10);
/* 144 */             session.setAttribute("c11", this.c11);
/* 145 */             session.setAttribute("c12", this.c12);
/* 146 */             session.setAttribute("c13", this.c13);
/* 147 */             session.setAttribute("c14", this.c14);
/* 148 */             session.setAttribute("c15", this.c15);
/* 149 */             session.setAttribute("c16", this.c16);
/* 150 */             session.setAttribute("c18", this.c18);
/* 151 */             session.setAttribute("c19", this.c19);
/* 152 */             session.setAttribute("c20", this.c20);
/* 153 */             session.setAttribute("c21", this.c21);
/*     */ 
/* 155 */             session.setAttribute("fecha1", this.fecha1);
/* 156 */             session.setAttribute("fecha2", this.fecha2);
/* 157 */             session.setAttribute("fecha3", this.fecha3);
/* 158 */             session.setAttribute("fecha4", this.fecha4);
/* 159 */             session.setAttribute("fecha5", this.fecha5);
/* 160 */             session.setAttribute("fecha6", this.fecha6);
/* 161 */             session.setAttribute("fecha7", this.fecha7);
/* 162 */             session.setAttribute("fecha8", this.fecha8);
/* 163 */             session.setAttribute("fecha9", this.fecha9);
/* 164 */             session.setAttribute("fecha10", this.fecha10);
/* 165 */             session.setAttribute("fecha11", this.fecha11);
/* 166 */             session.setAttribute("fecha12", this.fecha12);
/* 167 */             session.setAttribute("fecha13", this.fecha13);
/* 168 */             session.setAttribute("fecha14", this.fecha14);
/* 169 */             session.setAttribute("fecha15", this.fecha15);
/* 170 */             session.setAttribute("fecha16", this.fecha16);
/* 171 */             session.setAttribute("fecha17", this.fecha17);
/*     */ 
/* 173 */             session.setAttribute("fecha18", this.fecha18);
/* 174 */             session.setAttribute("fecha19", this.fecha19);
/* 175 */             session.setAttribute("fecha20", this.fecha20);
/* 176 */             session.setAttribute("fecha21", this.fecha21);
/*     */ 
/* 178 */             session.setAttribute("fechaCierre", this.fechaCierre);
/*     */ 
/* 180 */             target = "listaResumen";
/* 181 */             logger.debug("target : " + target);
/*     */           } else {
/* 183 */             logger.debug("No tiene Liquidaciones ******: " + this.textRutAfiliado);
/*     */ 
/* 187 */             target = "sinLiquidacion";
/* 188 */             session.setAttribute("sin.datos", "label.liquidacion.sin.movimientos");
/* 189 */             logger.debug("target : " + target);
/*     */           }
/*     */         }
/*     */       }
/*     */       else {
/* 194 */         target = "sinLiquidacion";
/* 195 */         session.setAttribute("sin.datos", "label.liquidacion.sin.movimientos");
/* 196 */         logger.debug("target : " + target);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 200 */       logger.debug("Exception: " + e.toString());
/*     */     }
/*     */ 
/* 203 */     return mapping.findForward(target);
/*     */   }
/*     */ 
/*     */   private TotalesPorEmpresaVO getTotales(Collection listaResumen, int yy, int mm)
/*     */   {
/* 210 */     Iterator lista = listaResumen.iterator();
/* 211 */     TotalesPorEmpresaVO totales = new TotalesPorEmpresaVO();
/*     */ 
/* 213 */     double fondoInicial = 0.0D;
/* 214 */     double montoAumentoInicial1 = 0.0D;
/* 215 */     double montoAumentoInicial2 = 0.0D;
/* 216 */     double montoAumentoInicial3 = 0.0D;
/* 217 */     double montoAumentoInicial4 = 0.0D;
/*     */ 
/* 219 */     double montoAumentoComision1 = 0.0D;
/* 220 */     double montoAumentoComision2 = 0.0D;
/* 221 */     double montoAumentoComision3 = 0.0D;
/* 222 */     double montoAumentoComision4 = 0.0D;
/*     */ 
/* 224 */     double montoValorPagado1 = 0.0D;
/* 225 */     double montoValorPagado2 = 0.0D;
/* 226 */     double montoValorPagado3 = 0.0D;
/* 227 */     double montoValorPagado4 = 0.0D;
/*     */ 
/* 229 */     double montoActualizado1 = 0.0D;
/* 230 */     double montoActualizado2 = 0.0D;
/* 231 */     double montoActualizado3 = 0.0D;
/* 232 */     double montoActualizado4 = 0.0D;
/*     */ 
/* 234 */     double montoRetSolicitud1 = 0.0D;
/* 235 */     double montoRetSolicitud2 = 0.0D;
/* 236 */     double montoRetSolicitud3 = 0.0D;
/* 237 */     double montoRetSolicitud4 = 0.0D;
/*     */ 
/* 239 */     double montoInteres = 0.0D;
/* 240 */     double montoReajuste = 0.0D;
/* 241 */     double montoFondoFinal = 0.0D;
/*     */ 
/* 243 */     ResMensualPrestCompVO reg = null;
/* 244 */     while (lista.hasNext()) {
/* 245 */       logger.debug("*********SUmando");
/* 246 */       System.out.println("*********Sumando");
/* 247 */       reg = (ResMensualPrestCompVO)lista.next();
/*     */ 
/* 249 */       logger.debug("fondoInicial : " + fondoInicial);
/* 250 */       fondoInicial += reg.getFondoInicial();
/* 251 */       logger.debug("montoAumentoInicial1 : " + montoAumentoInicial1);
/*     */ 
/* 253 */       montoAumentoInicial1 = montoAumentoInicial1 + 
/* 253 */         reg.getMontoAumentoInicial1();
/*     */ 
/* 255 */       montoAumentoInicial2 = montoAumentoInicial2 + 
/* 255 */         reg.getMontoAumentoInicial2();
/*     */ 
/* 257 */       montoAumentoInicial3 = montoAumentoInicial3 + 
/* 257 */         reg.getMontoAumentoInicial3();
/*     */ 
/* 259 */       montoAumentoInicial4 = montoAumentoInicial4 + 
/* 259 */         reg.getMontoAumentoInicial4();
/* 260 */       logger.debug("montoAumentoComision1 : " + montoAumentoComision1);
/*     */ 
/* 262 */       montoAumentoComision1 = montoAumentoComision1 + 
/* 262 */         reg.getMontoAumentoComision1();
/*     */ 
/* 264 */       montoAumentoComision2 = montoAumentoComision2 + 
/* 264 */         reg.getMontoAumentoComision2();
/*     */ 
/* 266 */       montoAumentoComision3 = montoAumentoComision3 + 
/* 266 */         reg.getMontoAumentoComision3();
/*     */ 
/* 268 */       montoAumentoComision4 = montoAumentoComision4 + 
/* 268 */         reg.getMontoAumentoComision4();
/* 269 */       logger.debug("montoValorPagado1 : " + montoValorPagado1);
/* 270 */       montoValorPagado1 += reg.getMontoValorPagado1();
/* 271 */       montoValorPagado2 += reg.getMontoValorPagado2();
/* 272 */       montoValorPagado3 += reg.getMontoValorPagado3();
/* 273 */       montoValorPagado4 += reg.getMontoValorPagado4();
/* 274 */       logger.debug("montoActualizado1 : " + montoActualizado1);
/* 275 */       montoActualizado1 += reg.getMontoActualizado1();
/* 276 */       montoActualizado2 += reg.getMontoActualizado2();
/* 277 */       montoActualizado3 += reg.getMontoActualizado3();
/* 278 */       montoActualizado4 += reg.getMontoActualizado4();
/*     */ 
/* 280 */       System.out.println("-------------------------------------------");
/* 281 */       montoRetSolicitud1 += reg.getMontoRetiroSolEmp1();
/* 282 */       montoRetSolicitud2 += reg.getMontoRetiroSolEmp2();
/* 283 */       montoRetSolicitud3 += reg.getMontoRetiroSolEmp3();
/* 284 */       montoRetSolicitud4 += reg.getMontoRetiroSolEmp4();
/*     */ 
/* 286 */       montoInteres += reg.getMontoInteres();
/* 287 */       montoReajuste += reg.getMontoReajuste();
/* 288 */       System.out.println("montoReajuste : " + montoReajuste);
/* 289 */       montoFondoFinal += reg.getMontoFondoFinal();
/* 290 */       System.out.println("montoFondoFinal : " + montoFondoFinal);
/*     */     }
/*     */ 
/* 300 */     System.out.println("periodo : " + this.periodoConsulta);
/* 301 */     this.fecha1 = getUltFechaPeriodoAnterior(this.periodoConsulta, 30);
/*     */ 
/* 303 */     System.out.println("fecha1 periodo : " + this.fecha1);
/*     */ 
/* 308 */     logger.debug("fecha1 : " + this.fecha1);
/* 309 */     this.fecha2 = reg.getFechaAumentoFondoInicial1();
/* 310 */     this.fecha3 = reg.getFechaAumentoFondoInicial2();
/* 311 */     this.fecha4 = reg.getFechaAumentoFondoInicial3();
/* 312 */     this.fecha5 = reg.getFechaAumentoFondoInicial4();
/* 313 */     this.fecha6 = reg.getFechaComision1();
/* 314 */     this.fecha7 = reg.getFechaComision2();
/* 315 */     this.fecha8 = reg.getFechaComision3();
/* 316 */     this.fecha9 = reg.getFechaComision4();
/* 317 */     this.fecha10 = reg.getFechaMontoActualizado1();
/* 318 */     this.fecha11 = reg.getFechaMontoActualizado2();
/* 319 */     this.fecha12 = reg.getFechaMontoActualizado3();
/* 320 */     this.fecha13 = reg.getFechaMontoActualizado4();
/* 321 */     this.fecha14 = reg.getFechaValorPagado1();
/* 322 */     this.fecha15 = reg.getFechaValorPagado2();
/* 323 */     this.fecha16 = reg.getFechaValorPagado3();
/* 324 */     this.fecha17 = reg.getFechaValorPagado4();
/*     */ 
/* 326 */     this.fecha18 = reg.getFechaRetiroSolEmp1();
/* 327 */     this.fecha19 = reg.getFechaRetiroSolEmp2();
/* 328 */     this.fecha20 = reg.getFechaRetiroSolEmp3();
/* 329 */     this.fecha21 = reg.getFechaRetiroSolEmp4();
/*     */ 
/* 331 */     System.out.println("fecha18: " + this.fecha18);
/* 332 */     System.out.println("fecha19: " + this.fecha19);
/* 333 */     System.out.println("fecha20: " + this.fecha20);
/* 334 */     System.out.println("fecha21: " + this.fecha21);
/*     */ 
/* 336 */     this.fechaCierre = reg.getFechaCierre();
/* 337 */     totales.setTotalFondoInicial(fondoInicial);
/* 338 */     this.c9 = (montoActualizado1 > 0.0D ? "si" : "no");
/* 339 */     totales.setTotalMontoActualizado1(montoActualizado1);
/* 340 */     this.c10 = (montoActualizado2 > 0.0D ? "si" : "no");
/* 341 */     totales.setTotalMontoActualizado2(montoActualizado2);
/* 342 */     this.c11 = (montoActualizado3 > 0.0D ? "si" : "no");
/* 343 */     totales.setTotalMontoActualizado3(montoActualizado3);
/* 344 */     this.c12 = (montoActualizado4 > 0.0D ? "si" : "no");
/* 345 */     totales.setTotalMontoActualizado4(montoActualizado4);
/*     */ 
/* 347 */     this.c5 = (montoAumentoComision1 > 0.0D ? "si" : "no");
/* 348 */     totales.setTotalMontoAumentoComision1(montoAumentoComision1);
/* 349 */     this.c6 = (montoAumentoComision2 > 0.0D ? "si" : "no");
/* 350 */     totales.setTotalMontoAumentoComision2(montoAumentoComision2);
/* 351 */     this.c7 = (montoAumentoComision3 > 0.0D ? "si" : "no");
/* 352 */     totales.setTotalMontoAumentoComision3(montoAumentoComision3);
/* 353 */     this.c8 = (montoAumentoComision4 > 0.0D ? "si" : "no");
/* 354 */     totales.setTotalMontoAumentoComision4(montoAumentoComision4);
/*     */ 
/* 356 */     this.c1 = (montoAumentoInicial1 > 0.0D ? "si" : "no");
/* 357 */     totales.setTotalMontoAumentoInicial1(montoAumentoInicial1);
/* 358 */     this.c2 = (montoAumentoInicial2 > 0.0D ? "si" : "no");
/* 359 */     totales.setTotalMontoAumentoInicial2(montoAumentoInicial2);
/* 360 */     this.c3 = (montoAumentoInicial3 > 0.0D ? "si" : "no");
/* 361 */     totales.setTotalMontoAumentoInicial3(montoAumentoInicial3);
/* 362 */     this.c4 = (montoAumentoInicial4 > 0.0D ? "si" : "no");
/* 363 */     totales.setTotalMontoAumentoInicial4(montoAumentoInicial4);
/*     */ 
/* 365 */     this.c13 = (montoValorPagado1 > 0.0D ? "si" : "no");
/* 366 */     totales.setTotalMontoValorPagado1(montoValorPagado1);
/* 367 */     this.c14 = (montoValorPagado2 > 0.0D ? "si" : "no");
/* 368 */     totales.setTotalMontoValorPagado2(montoValorPagado2);
/* 369 */     this.c15 = (montoValorPagado3 > 0.0D ? "si" : "no");
/* 370 */     totales.setTotalMontoValorPagado3(montoValorPagado3);
/* 371 */     this.c16 = (montoValorPagado4 > 0.0D ? "si" : "no");
/* 372 */     totales.setTotalMontoValorPagado4(montoValorPagado4);
/*     */ 
/* 374 */     this.c18 = (montoRetSolicitud1 > 0.0D ? "si" : "no");
/* 375 */     totales.setTotalMontoRetSolicitud1(montoRetSolicitud1);
/* 376 */     this.c19 = (montoRetSolicitud2 > 0.0D ? "si" : "no");
/* 377 */     totales.setTotalMontoRetSolicitud2(montoRetSolicitud2);
/* 378 */     this.c20 = (montoRetSolicitud3 > 0.0D ? "si" : "no");
/* 379 */     totales.setTotalMontoRetSolicitud3(montoRetSolicitud3);
/* 380 */     System.out.println("monto3: " + montoRetSolicitud3);
/* 381 */     this.c21 = (montoRetSolicitud4 > 0.0D ? "si" : "no");
/* 382 */     totales.setTotalMontoRetSolicitud4(montoRetSolicitud4);
/* 383 */     System.out.println("c20: " + this.c20);
/* 384 */     totales.setTotalMontoInteres(montoInteres);
/* 385 */     totales.setTotalMontoReajuste(montoReajuste);
/* 386 */     totales.setTotalMontoFondoFinal(montoFondoFinal);
/*     */ 
/* 388 */     return totales;
/*     */   }
/*     */ 
/*     */   public static String getUltFechaPeriodoAnterior(String periodo, int decrementoEnDias)
/*     */   {
/* 396 */     String nuevoString = "";
/*     */     try
/*     */     {
/* 401 */       String mesPeriodo = periodo.substring(4, 6);
/* 402 */       String anoPeriodo = periodo.substring(0, 4);
/* 403 */       int mes = Integer.parseInt(mesPeriodo);
/* 404 */       int ano = Integer.parseInt(anoPeriodo);
/*     */ 
/* 406 */       if (mes == 1) {
/* 407 */         mes = 12;
/* 408 */         ano--;
/*     */       } else {
/* 410 */         mes--;
/*     */       }
/*     */ 
/* 413 */       Calendar c = new GregorianCalendar(ano, mes - 1, 1);
/* 414 */       int dia_fin = c.getActualMaximum(5);
/* 415 */       c.set(5, dia_fin);
/*     */ 
/* 417 */       SimpleDateFormat formatoFechas = new SimpleDateFormat("dd/MM/yyyy");
/* 418 */       String fecha_nueva = formatoFechas.format(c.getTime());
/* 419 */       return fecha_nueva;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 424 */       System.err.println("[ERROR:FUNCTION]: Funciones: DecrementaFecha: " + e.getMessage());
/* 425 */     }return nuevoString;
/*     */   }
/*     */ }

/* Location:           C:\tmp\araucaFte\prod\ear\AutoconsultaWeb\WEB-INF\classes\
 * Qualified Name:     cl.araucana.autoconsulta.ui.actions.liquidacionReembolsos.GetResumenMensualAction
 * JD-Core Version:    0.6.0
 */