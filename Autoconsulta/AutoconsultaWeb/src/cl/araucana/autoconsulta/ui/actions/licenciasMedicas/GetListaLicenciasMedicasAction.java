/*     */ package cl.araucana.autoconsulta.ui.actions.licenciasMedicas;
/*     */ 
/*     */ import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
/*     */ import cl.araucana.autoconsulta.vo.AfiliadoVO;
/*     */ import cl.araucana.autoconsulta.vo.CodigoDescripcionVO;
/*     */ import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
/*     */ import cl.araucana.autoconsulta.vo.EmpresaVO;
/*     */ import cl.araucana.autoconsulta.vo.LicenciaMedicaVO;
/*     */ import cl.araucana.autoconsulta.vo.StringVO;
/*     */ import cl.araucana.autoconsulta.vo.UsuarioVO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
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
/*     */ public class GetListaLicenciasMedicasAction extends Action
/*     */ {
/*  36 */   private static Logger logger = Logger.getLogger(GetListaLicenciasMedicasAction.class);
/*     */   public static final String GLOBAL_FORWARD_listaLicencias = "listaLicencias";
/*     */   public static final String GLOBAL_FORWARD_definirEmpleado = "definirEmpleado";
/*     */ 
/*     */   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/*  47 */     HttpSession session = request.getSession(true);
/*  48 */     DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
/*  49 */     ServicesAutoconsultaDelegate delegate = 
/*  50 */       new ServicesAutoconsultaDelegate();
/*  51 */     String target = null;
/*  52 */     String textRutAfiliado = null;
/*  53 */     String textRutEmpresa = null;
/*  54 */     String textDvRutAfiliado = null;
/*  55 */     String nombreConsulta = null;
/*  56 */     String rutConsulta = null;
/*  57 */     boolean pedirRutAfiliado = false;
/*  58 */     long rutAfiliado = 0L;
/*  59 */     boolean empleadoEncontrado = false;
/*     */ 
/*  61 */     if (!daf.get("rut").equals("")) {
/*  62 */       session.setAttribute("rutDelEmpleado", daf.get("rut"));
/*  63 */       session.setAttribute("rutDVDelEmpleado", daf.get("dv"));
/*     */     }
/*     */ 
/*  66 */     //String dispositivo = request.getRemoteAddr();
/*  67 */     //logger.debug("IP: " + dispositivo);
/*     */ 
/*  69 */     session.removeAttribute("validation.message");
/*     */ 
/*  71 */     UsuarioVO usuario = (UsuarioVO)session.getAttribute("datosUsuario");
              usuario.setIpConexion(request.getRemoteAddr());
/*     */ 
/*  73 */     Collection listaDeEmpresas = 
/*  74 */       (Collection)session.getAttribute("empresasACargo");
/*     */ 
/*  76 */     Iterator empresaSeleccionada = null;
/*     */ 
/*  78 */     if (listaDeEmpresas != null) {
/*  79 */       empresaSeleccionada = listaDeEmpresas.iterator();
/*     */     }
/*  81 */     if (usuario.isEsEmpresa()) {
/*     */       do {
/*  83 */         logger.debug("El usuario es Empresa");
/*     */ 
/*  86 */         if (empresaSeleccionada != null)
/*  87 */           textRutEmpresa = "" +
/*  88 */             ((EmpresaACargoVO)empresaSeleccionada.next()).getRut();
/*     */         else {
/*  90 */           textRutEmpresa = "" + usuario.getRut();
/*     */         }
/*  92 */         textRutAfiliado = (String)daf.get("rut");
/*  93 */         textDvRutAfiliado = (String)daf.get("dv");
/*  94 */         if ((textRutAfiliado != null) && (textRutAfiliado.length() > 0))
/*     */         {
/*  99 */           String subapp = 
/* 100 */             (String)session.getAttribute("struts.subapplication");
/* 101 */           if ((subapp != null) && (subapp.equals("modulo")) && 
/* 102 */             (textRutAfiliado != null) && (textRutAfiliado.length() > 3))
/*     */           {
/* 104 */             logger.debug("Ajustando rut por canal 'modulo': " + 
/* 105 */               textRutAfiliado);
/* 106 */             textRutAfiliado = textRutAfiliado.substring(0, 
/* 107 */               textRutAfiliado.length() - 1);
/* 108 */             logger.debug("Nuevo rut: " + textRutAfiliado);
/*     */           }
/*     */ 
/* 111 */           AfiliadoVO afiliado = delegate.getDatosEmpleado(
/* 112 */             Long.parseLong(textRutAfiliado), 
/* 113 */             Long.parseLong(textRutEmpresa));
/* 114 */           if (afiliado != null) {
/* 115 */             empleadoEncontrado = true;
/* 116 */             if ((usuario.isEsEmpresa()) && (!usuario.isEsEncargadoEmpresa())) {
/* 117 */               nombreConsulta = afiliado.getFullNombre();
/* 118 */               rutConsulta = afiliado.getFullRut(); break;
/* 119 */             }if ((usuario.isEsEncargadoEmpresa()) && 
/* 120 */               (delegate.usuarioPuedeConsultarPorAfiliado(
/* 121 */               usuario.getRutusuarioAutenticado(), 
/* 122 */               Long.parseLong(textRutEmpresa), afiliado)))
/*     */             {
/* 124 */               nombreConsulta = afiliado.getFullNombre();
/* 125 */               rutConsulta = afiliado.getFullRut(); break;
/*     */             }
/*     */ 
/* 130 */             target = "definirEmpleado";
/* 131 */             session.setAttribute("validation.message", 
/* 132 */               "errors.validation.noPertenceSucursalAutorizada");
/* 133 */             session.setAttribute("volverA", "getAsignacionFamiliar");
/* 134 */             return mapping.findForward(target);
/*     */ 
/* 136 */             //break;
/*     */           }
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/* 150 */           logger.debug("A definir empleado");
/* 151 */           target = "definirEmpleado";
/* 152 */           session.setAttribute("volverA", "getListaLicenciasMedicas");
/* 153 */           return mapping.findForward(target);
/*     */         }
/*     */       }
/* 155 */       while ((!empleadoEncontrado) && (empresaSeleccionada != null) && (
/* 156 */         empresaSeleccionada.hasNext()));
/*     */ 
/* 158 */       if ((!empleadoEncontrado) && (textRutAfiliado.length() != 0))
/*     */       {
/* 161 */         target = "definirEmpleado";
/* 162 */         session.setAttribute("validation.message", 
/* 163 */           "errors.validation.noPertenceEmpresa");
/* 164 */         session.setAttribute("volverA", "getAsignacionFamiliar");
/* 165 */         return mapping.findForward(target);
/*     */       }
/*     */     } else {
/* 168 */       logger.debug("El usuario es Persona");
/*     */ 
/* 170 */       textRutAfiliado = String.valueOf(usuario.getRut());
/*     */ 
/* 172 */       nombreConsulta = usuario.getNombre();
/* 173 */       rutConsulta = usuario.getFullRut();
/*     */     }
/*     */ 
/* 176 */     rutAfiliado = Long.parseLong(textRutAfiliado);
/* 177 */     logger.debug("Rut afiliado: " + rutAfiliado);
/*     */     usuario.setRutAfiliado(rutAfiliado);
/* 180 */     Collection empleadores = new ArrayList();
/* 181 */     Collection listaEmpleadores = 
/* 182 */       delegate.getEmpleadoresByEmpleado(usuario);
/*     */ 
/* 184 */     if ((textRutEmpresa != null) && (textRutEmpresa.length() > 0)) {
/* 185 */       if (!listaEmpleadores.isEmpty()) {
/* 186 */         Iterator ile = listaEmpleadores.iterator();
/* 187 */         while (ile.hasNext()) {
/* 188 */           EmpresaVO empleador = (EmpresaVO)ile.next();
/* 189 */           if (Long.parseLong(textRutEmpresa) == empleador.getRut())
/* 190 */             empleadores.add(empleador);
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 195 */       empleadores = listaEmpleadores;
/*     */     }
/*     */ 
/* 198 */     boolean tieneLicencias = false;
/*     */ 	
				//TODO rut dummy
             // usuario.setRutAfiliado(rutAfiliado);
/*usuario.setRutAfiliado(2926294);
rutConsulta = String.valueOf(2926294);
			usuario.setRutAfiliado(6472983);
			rutConsulta = String.valueOf(6472983);
			usuario.setRutAfiliado(4010332);
			rutConsulta = String.valueOf(4010332);
usuario.setRutAfiliado(16245524);
rutConsulta = String.valueOf(16245524);
			*/
/* 200 */     Collection licencias = delegate.getConsultaLicenciasMedicas(usuario);
/* 201 */     logger.debug("licencias: " + licencias.size());
/* 202 */     Iterator it = licencias.iterator();
/*     */ 
/* 204 */     while (it.hasNext()) {
/* 205 */       LicenciaMedicaVO licencia = (LicenciaMedicaVO)it.next();
/*     */ 
/* 207 */       if ((licencia.getCodigoEstadoLicencia().equals("1")) && 
/* 208 */         (licencia.getVisada() != 2))
/*     */       {
/* 211 */         Collection listaOficinasPago = 
/* 212 */           (Collection)session.getAttribute("lista.oficinas.pago");
/*     */ 
/* 214 */         if ((listaOficinasPago == null) || (listaOficinasPago.isEmpty())) {
/* 215 */           listaOficinasPago = delegate.getListaOficinasPago();
/* 216 */           session.setAttribute("lista.oficinas.pago", listaOficinasPago);
/*     */         }
/*     */ 
/* 220 */         System.out.println("Antes de comparciòn");
/* 221 */         System.out.println("Ofipago:" + licencia.getOficinaPago());
/*     */ 
/* 224 */         if (licencia.getOficinaPago() == null) {
/* 225 */           System.out.println("Entro al If");
/* 226 */           licencia.setOficinaPago(
/* 227 */             traducirOficinaPago(listaOficinasPago, 
/* 227 */             licencia.getCodOficinaPago()));
/*     */         }
/* 229 */         Collection listaObservaciones = 
/* 230 */           (Collection)session.getAttribute("lista.observaciones");
/*     */ 
/* 232 */         if ((listaObservaciones == null) || (listaObservaciones.isEmpty())) {
/* 233 */           listaObservaciones = delegate.getListaObservaciones();
/* 234 */           session.setAttribute("lista.observaciones", listaObservaciones);
/*     */         }
/* 236 */         licencia = traducirObservaciones(listaObservaciones, licencia);
/*     */       }
/*     */       else {
/* 239 */         if (licencia.getVisada() != 1)
/*     */           continue;
/* 241 */         Collection listaObservaciones = 
/* 242 */           (Collection)session.getAttribute("lista.observaciones");
/*     */ 
/* 244 */         if ((listaObservaciones == null) || (listaObservaciones.isEmpty())) {
/* 245 */           listaObservaciones = delegate.getListaObservaciones();
/* 246 */           session.setAttribute("lista.observaciones", 
/* 247 */             listaObservaciones);
/*     */         }
/*     */ 
/* 250 */         licencia = traducirObservaciones(listaObservaciones, licencia);
/* 251 */         logger.debug("rut Afiliado***************: " + rutAfiliado);
/* 252 */         logger.debug("Numero Licencia***************: " + 
/* 253 */           licencia.getNumeroLicencia());
/* 254 */         Collection listaObs = delegate.listaObservacionesCompin(
/* 255 */           rutAfiliado, licencia.getNumeroLicencia());
/* 256 */         StringVO newStringVO = new StringVO();
/*     */ 
/* 258 */         if (!listaObs.isEmpty()) {
/* 259 */           Iterator i = listaObs.iterator();
/*     */ 
/* 261 */           while (i.hasNext()) {
/* 262 */             StringVO stringVO = (StringVO)i.next();
/* 263 */             if (newStringVO.getTexto() == null)
/* 264 */               newStringVO.setTexto(stringVO.getTexto());
/*     */             else {
/* 266 */               newStringVO.setTexto(newStringVO.getTexto() + " " + 
/* 267 */                 stringVO.getTexto());
/*     */             }
/*     */           }
/*     */ 
/* 271 */           licencia.getListaObservacionesCompin().add(newStringVO);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 		
/* 277 */     session.setAttribute("lista.licencias", licencias);
/* 278 */     session.setAttribute("lista.empleadores", empleadores);
/* 279 */     session.setAttribute("nombre", nombreConsulta);
/* 280 */     session.setAttribute("rut", rutConsulta);
/* 281 */     session.setAttribute("fechaHoy", new Date());
/* 282 */     logger.debug("A desplegar Lista licencias");
/* 283 */     target = "listaLicencias";
/* 284 */     return mapping.findForward(target);
/*     */   }
/*     */ 
/*     */   public String traducirOficinaPago(Collection listaOficinasPago, int codigo)
/*     */   {
/* 296 */     String glosa = null;
/*     */ 
/* 298 */     logger.debug("Son: " + listaOficinasPago.size() + " Oficinas");
/* 299 */     Iterator iOficina = listaOficinasPago.iterator();
/* 300 */     while (iOficina.hasNext()) {
/* 301 */       CodigoDescripcionVO traductor = (CodigoDescripcionVO)iOficina.next();
/* 302 */       logger.debug("Licencia Oficina Código: " + codigo);
/* 303 */       logger.debug("Traductor Código: " + traductor.getCodigo());
/* 304 */       if (codigo == traductor.getCodigo()) {
/* 305 */         glosa = traductor.getDescripcion();
/* 306 */         break;
/*     */       }
/*     */     }
/* 309 */     return glosa;
/*     */   }
/*     */ 
/*     */   public LicenciaMedicaVO traducirObservaciones(Collection listaObservaciones, LicenciaMedicaVO licencia)
/*     */   {
/* 322 */     logger.debug("Son: " + listaObservaciones.size() + " Observaciones Definidas");
/* 323 */     boolean encontroObs1 = false;
/* 324 */     boolean encontroObs2 = false;
/* 325 */     boolean encontroObs3 = false;
/* 326 */     Iterator iObservaciones = listaObservaciones.iterator();
/*     */ 
/* 328 */     while (iObservaciones.hasNext()) {
/* 329 */       if ((encontroObs1) && (encontroObs2) && (encontroObs3)) break;
/* 330 */       CodigoDescripcionVO traductor = 
/* 331 */         (CodigoDescripcionVO)iObservaciones.next();
/* 332 */       logger.debug("Licencia Observacion1 Código: " + 
/* 333 */         licencia.getCodigoObservacion1());
/* 334 */       logger.debug("Licencia Observacion2 Código: " + 
/* 335 */         licencia.getCodigoObservacion2());
/* 336 */       logger.debug("Licencia Observacion3 Código: " + 
/* 337 */         licencia.getCodigoObservacion3());
/* 338 */       logger.debug("Traductor Código: " + traductor.getCodigo());
/* 339 */       logger.debug("Traductor Descripcion: " + traductor.getDescripcion());
/*     */ 
/* 341 */       if (licencia.getCodigoObservacion1() == traductor.getCodigo()) {
/* 342 */         licencia.setObservacion1(traductor.getDescripcion());
/* 343 */         encontroObs1 = true;
/*     */       }
/* 345 */       if (licencia.getCodigoObservacion2() == traductor.getCodigo()) {
/* 346 */         licencia.setObservacion2(traductor.getDescripcion());
/* 347 */         encontroObs2 = true;
/*     */       }
/* 349 */       if (licencia.getCodigoObservacion3() == traductor.getCodigo()) {
/* 350 */         licencia.setObservacion3(traductor.getDescripcion());
/* 351 */         encontroObs3 = true; continue;
/*     */ 
/* 354 */         //break;
/*     */       }
/*     */     }
/*     */ 
/* 358 */     return licencia;
/*     */   }
/*     */ }

/* Location:           C:\tmp\araucaFte\prod\ear\AutoconsultaWeb\WEB-INF\classes\
 * Qualified Name:     cl.araucana.autoconsulta.ui.actions.licenciasMedicas.GetListaLicenciasMedicasAction
 * JD-Core Version:    0.6.0
 */