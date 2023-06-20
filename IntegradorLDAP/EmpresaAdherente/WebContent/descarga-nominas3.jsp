<%-- 
    Document   : descarga-nominas
    Created on : 22-02-2022, 17:17:19
    Author     : TestGroup
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.util.*"%>
<%@page import="com.araucana.controller.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--  %@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %-->
<%@ page import="java.text.SimpleDateFormat"%>

<%
    List<String> lista = (List<String>) request.getAttribute("");
%>

<!DOCTYPE html>
<html lang="es">
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover"/>
    <!--    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>-->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="format-detection" content="telephone=no"/>
    <title>La Araucana - Sucursal Virtual - Empresa</title>
    <link rel="icon" href="favicon.ico"/>
    <link rel="stylesheet" href="assets/css/fln.css"/>
    <link rel="stylesheet" href="fonts/fln-icons.css"/>
    <link rel="stylesheet" href="assets/css/estilos.css"/>
    <script>
        WebFontConfig = {
            google: {
                families: ["Open+Sans:300,400,600,700"]
            }
        };
        (function () {
            var wf = document.createElement('script');
            wf.src = ('https:' == document.location.protocol ? 'https' : 'http') +
                    '://ajax.googleapis.com/ajax/libs/webfont/1.5.18/webfont.js';
            wf.type = 'text/javascript';
            wf.async = 'true';
            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(wf, s);
        })();
    </script>
    <body>

        <main>
            <header class="header"><a class="header__item header__item--logo" href="#"><img src="img/logo.svg"/>
                    <div class="header__text oculto-xs block-md"><span>Sucursal</span> Virtual</div></a>
 
                <div class="header__item header__item--user oculto-xs flex-xl align-middle-xl">
                    <div class="header-user">
                        <div class="header-user__info">
                        
                             <%
                                String txtRUTnombre = (String) request.getAttribute("txtRUTnombre");
                            %>                                                   
                        
                            <div class="header-user__text">
                                <div class="header-user__hi">Bienvenido</div>
                                <div class="header-user__name"><%=txtRUTnombre%></div>
                            </div>
                            
                            <div class="header-user__icon fln-abajo-light"></div>
                            
                            <div class="header-user__options">
                                <a class="header-user__option" href="CloseSession">
                                    <div class="header-user__title">Cerrar</div>
                                    <div class="header-user__ico"><svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" viewBox="0 0 80 80"><title>  salir</title><path d="M73 38.7c-0.1-0.4-0.2-0.8-0.4-1.1L58.3 17.8c-0.4-0.7-1.3-0.9-1.9-0.5 -0.2 0.1-0.3 0.2-0.4 0.4 -0.6 1-0.6 2.2 0 3.2L67.5 37H38.3c-0.9 0-1.7 1-1.7 2.3s0.8 2.3 1.7 2.3H68L55.9 59.1c-0.6 1-0.6 2.2 0.1 3.2 0.3 0.4 0.7 0.6 1.1 0.6 0.5 0 1-0.3 1.2-0.7l14.4-21C73.2 40.4 73.3 39.5 73 38.7z"/><path d="M35.8 71H15c-2.9-0.3-5.1-2.9-4.9-5.8V14.9C9.9 11.9 12 9.3 15 9h20.8c1-0.1 1.7-1 1.7-2 0.1-1-0.7-1.9-1.7-2H15c-4.5 0-8.2 4.4-8.2 9.9v50.3c0 5.4 3.7 9.9 8.2 9.9h20.8c1.1 0 2-0.9 2-2S36.9 71 35.8 71z"/></svg></div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="header__item header__item--burger oculto-xl flex-xs align-middle-xs"><a href="#menu-responsive" onclick="MENU_RESPONSIVE.toggle(); return false;"><svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" viewBox="0 0 35 35"><rect x="2.5" y="24.1" width="30" height="1.9"/><rect x="2.5" y="9.1" width="30" height="1.9"/><rect x="2.5" y="16.6" width="30" height="1.9"/></svg></a></div>
            </header>
            
 
                        
            <section class="container-fluid contenido-interior">
                <div class="marco">
                    <div class="contenido-dinamico">

                        <%
                            String fecha1 = "";
                            String fecha2 = "";

                            SimpleDateFormat ft = new SimpleDateFormat("MM/yyyy");

                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MONTH, -1);
                            Date dateC = calendar.getTime();

                            Calendar calendar2 = Calendar.getInstance();
                            Date dateC2 = calendar2.getTime();

                            String fechaMesAnteior = ft.format(dateC);
                            String fechaMesActual = ft.format(dateC2);

                        %>

                        <%                            if (fecha1 == null) {
                                fecha1 = fechaMesAnteior;
                            }

                        %>


                        <h1>Descarga Nóminas</h1>
                        <%-- <p><strong>Encargado de Empresa:</strong> <br> LA ARAUCANA C C A F Usuario Empresa</p> --%>

                        <p>Esta página le permitirá obtener las Nóminas de Crédito, Nóminas de Ahorro y Anexos de Trabajadores, correspondientes a los períodos indicados abajo y que pertenecen a aquellas empresas que están bajo su administración. Las opciones disponibles le permitirán seleccionar y especificar la organización y formato de la información requerida.</p>

                        <!--FORM-->
                        <form action="ControladorServlet1" method="post" id="form-id"> 

                            <div class="bloque">
                                <%
                                    String[] oi = (String[]) request.getAttribute("oi");
                                %>

                                <lefth><h5>Organización de la información</h5></lefth>
                                <div class="form js-organizacion">
                                    <div class="form__seleccion js-organizacion-informacion">

                                        <% if (oi[0].equals("S")) {  //EDITAR1 %> 
                                        <div class="form__checkbox form__checkbox--inline">
                                            <input checked type="checkbox" name="organizacion-informacion" id="organizacion-informacion-0" value="1"/>
                                            <label for="organizacion-informacion-0">Separada por empresas</label>
                                        </div>
                                        <% } else { %> 
                                        <div class="form__checkbox form__checkbox--inline">
                                            <input type="checkbox" name="organizacion-informacion" id="organizacion-informacion-0" value="1"/>
                                            <label for="organizacion-informacion-0">Separada por empresas</label>
                                        </div>
                                        <% } %>

                                        <% if (oi[1].equals("S")) {   %>
                                        <div class="form__checkbox form__checkbox--inline">
                                            <input checked type="checkbox" name="organizacion-informacion" id="organizacion-informacion-1" value="2"/>
                                            <label for="organizacion-informacion-1">Separada por nómina de crédito</label>
                                        </div>
                                        <% } else if (oi[0].equals("S") && oi[1].equals("N")) { %>  
                                        <div class="form__checkbox form__checkbox--inline">
                                            <input type="checkbox" name="organizacion-informacion" id="organizacion-informacion-1" value="2"/>
                                            <label for="organizacion-informacion-1">Separada por nómina de crédito</label>
                                        </div>
                                        <% } else { %>
                                        <div class="form__checkbox form__checkbox--inline">
                                            <input type="checkbox" name="organizacion-informacion" id="organizacion-informacion-1" disabled="disabled" value="2"/>
                                            <label for="organizacion-informacion-1">Separada por nómina de crédito</label>
                                        </div>
                                        <% } %>

                                        <% if (oi[2].equals("S")) {   %>
                                        <div class="form__checkbox form__checkbox--inline">
                                            <input checked type="checkbox" name="organizacion-informacion" id="organizacion-informacion-2" value="3"/>
                                            <label for="organizacion-informacion-2">Separada por nómina de ahorro</label>
                                        </div>
                                        <% } else if (oi[0].equals("S") && oi[2].equals("N")) { %> 
                                        <div class="form__checkbox form__checkbox--inline">
                                            <input type="checkbox" name="organizacion-informacion" id="organizacion-informacion-2" value="3"/>
                                            <label for="organizacion-informacion-2">Separada por nómina de ahorro</label>
                                        </div>
                                        <% } else { %>
                                        <div class="form__checkbox form__checkbox--inline">
                                            <input type="checkbox" name="organizacion-informacion" id="organizacion-informacion-2" disabled="disabled" value="3"/>
                                            <label for="organizacion-informacion-2">Separada por nómina de ahorro</label>
                                        </div>
                                        <% } %>

                                        <% if (oi[3].equals("S")) {   %>
                                        <div class="form__checkbox form__checkbox--inline">
                                            <input checked type="checkbox" name="organizacion-informacion" id="organizacion-informacion-3" value="4"/>
                                            <label for="organizacion-informacion-3">Separada por anexo de trabajadores</label>
                                        </div>
                                        <% } else if(oi[0].equals("S") && oi[3].equals("N")) { %> 
                                        <div class="form__checkbox form__checkbox--inline">
                                            <input type="checkbox" name="organizacion-informacion" id="organizacion-informacion-3" value="4"/>
                                            <label for="organizacion-informacion-3">Separada por anexo de trabajadores</label>
                                        </div>
                                        <% } else { %> 
                                        <div class="form__checkbox form__checkbox--inline">
                                            <input type="checkbox" name="organizacion-informacion" id="organizacion-informacion-3" disabled="disabled" value="4"/>
                                            <label for="organizacion-informacion-3">Separada por anexo de trabajadores</label>
                                        </div>
                                        <% } %>

                                    </div>
                                </div>
                            </div>

                            <input type="hidden" name="fechaPasada" value="<%= fechaMesAnteior%>" >
                            <input type="hidden" name="fechaActual" value="<%= fechaMesActual%>" >
                            
                            <%
                                String rutEjecutivo = (String) request.getAttribute("rutEjecutivo");
                                String rutEjecutivoAnt = (String) request.getAttribute("rutEjecutivoAnt");
                                String xRol = (String) request.getAttribute("xRol");
                                String txtEjecutivo = (String) request.getAttribute("txtEjecutivo");
                            %>  
                            
                            <%
                            if (xRol.equals("A")) {
                            %>
                               <input type="hidden" name="rutEjecutivo" value="<%= rutEjecutivo%>">
                            <%
                            } else {
                            %>
                               <h6>
                               <label for="rutEjecutivo">Ingrese RUT con DV (con Guión) . . :</label>
                               <input name="rutEjecutivo" value="<%= rutEjecutivo%>" size=15>
                                
                               <label for="rutEjecutivo"><a href="#" id="buscar" >&nbsp;Buscar</a></label>
                               </h6>
                            <%
                            }
                            %>                           
                            
                            <input type="hidden" name="rutEjecutivoAnt" value="<%= rutEjecutivoAnt%>">
                            
                            <%
                                String perAnteriorOculto = (String) request.getAttribute("perAnteriorOculto");
                            %>                            

                            <input type="hidden" name="perAnteriorOculto" value=<%=perAnteriorOculto%> >
                                            <script>
                                                function postServlet()
                                                {
                                                    document.getElementById('form-id').submit()
                                                }
                                            </script>
                                            
                            <div class="row align-bottom-xs">
                                <div class="col-xs-12 col-md-3">
                                    <div class="form__grupo js-formato-exportacion form__grupo--single"><span class="icono fln-abajo"></span>

                                        <select  name="txtPer" id="periodo-seleccionado" onchange="postServlet()">

                                        <%
                                            String xValor1 = "";
                                            List<String> funPeriodos1 = (List<String>) request.getAttribute("funPeriodos");
                                            int xCuenta = funPeriodos1.size();
                                            
                                            if (xCuenta > 0) {
                                            for (int ii = 0; ii < xCuenta; ii++) {
                                                xValor1 = funPeriodos1.get(ii);
                                                
                                                if (perAnteriorOculto.equals(xValor1)) {
                                        %>    

                                                    <option selected value="<%= xValor1%>"><%= xValor1%></option>

                                             <% } else {%>
                                            
                                                    <option value="<%= xValor1%>"><%= xValor1%></option>

                                             <% } 
                                                } %>
                                         <% } %>

                                        </select>
                                        
                                        <h5>Período de Cotización</h5>
                                    </div>
                                    
                                    

                                    <div class="separador--small oculto-md"></div>
                                    
                                </div>
                                <div class="col-xs-12 col-md-8">
                                    <p>Estimado Empleador, ante cualquier duda o problema en la descarga de sus archivos de descuento mensual, solicitamos pueda tomar contacto mediante el siguiente formulario <a href="https://qa-araucana.laaraucana.cl/soporte-nomina-empresa/" target="_blank">Clic aquí</a></p>
                                    </div>
                            </div>

                            <div class="flex-xs align-middle-xs justify-end-xs">
                                <p style="margin-bottom:0">Seleccionar tipo nómina: </p>
                                <div class="form__check-select js-seleccionar">
                                    <div class="form__check-select__item fln-check">
                                        <!--<input class="text-align-right-xs" type="checkbox" name="selecPer" id="seleccionar-per" />-->
                                        <input class="text-align-right-xs" type="checkbox" name="seleccionar" id="seleccionar-nominas" onChange="SELECCIONAR_CHECKBOXS.toggle('.js-nominas')"/>
                                    </div>
                                    <div class="form__check-select__icon fln-abajo"></div>
                                    <div class="form__check-select__options">
                                        <div class="form__check-select__option" value="todo" onclick="CHECK_SELECT.toggle('.js-seleccionar', true), SELECCIONAR_CHECKBOXS.seleccionar('.js-nominas', '.nominas-credito, .nominas-ahorro, .nominas-trabajadores', true);">Todo</div>
                                        <div class="form__check-select__option" value="nominas-de-credito" onclick="CHECK_SELECT.toggle('.js-seleccionar', false), SELECCIONAR_CHECKBOXS.seleccionar('.js-nominas', '.nominas-credito', false)">Nóminas de crédito</div>
                                        <div class="form__check-select__option" value="nominas-de-ahorro" onclick="CHECK_SELECT.toggle('.js-seleccionar', false), SELECCIONAR_CHECKBOXS.seleccionar('.js-nominas', '.nominas-ahorro', false)">Nóminas de ahorro</div>
                                        <div class="form__check-select__option" value="anexos-de-trabajadores" onclick="CHECK_SELECT.toggle('.js-seleccionar', false), SELECCIONAR_CHECKBOXS.seleccionar('.js-nominas', '.nominas-trabajadores', false)">Anexos de trabajadores</div>
                                    </div>
                                </div>
                            </div>

                            <div class="separador--small"></div>
                            <div class="tabla tabla--borde tabla--full tabla--responsive js-nominas">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>RUT empresa</th>
                                            <th>Razón social</th>
                                            <th>Nominas de crédito</th>
                                            <th>Nominas de ahorro</th>
                                            <th>Anexos de trabajadores</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        int ii = 0;
                                        String xRut = "";
                                        String xNombre = "";
                                        String xPeriodo = "";
                                        String[] marcaNC = (String[]) request.getAttribute("marcaNC");
                                        String[] marcaNL = (String[]) request.getAttribute("marcaNL");
                                        String[] marcaCF = (String[]) request.getAttribute("marcaCF");
                                        Map<String, String> lista_empresas1 = (Map<String, String>) request.getAttribute("lista_empresas1");
                                        String xPerSeleccionado = (String) request.getAttribute("perSeleccionado");
                                        String xFormatoDescarga = (String) request.getAttribute("formatoDescarga");
                                    %>

                                    <%
                                        Iterator it = lista_empresas1.keySet().iterator();
                                        while (it.hasNext()) {
                                            ii += 1;

                                            String key = (String) it.next();

                                            xRut = key;
                                            xNombre = lista_empresas1.get(key);
                                    

                                     

                                
                                    String valor = "";
                                    String valor1 = "";
                                    String valor2 = "";
                                    String valor3 = "";

                                    Verificar_Check vc = new Verificar_Check();
                                    valor = vc.funcionCheck(xPerSeleccionado, xRut);
                                    
                                    if (!valor.equals("XXX")) {
                                    
                                    %>
                                        <td class="text-align-center-md" data-th="RUT empresa" > <%=xRut%> 
                                        <input type="hidden" name="RUT" value="<%=xRut%>" /> 
                                    	</td>                                  
                                    	<td class="text-align-center-md" data-th="Razón social"  > <%=xNombre%> 
                                        <input type="hidden" name="razonSocial" />
                                    	</td>                                       	
                                        
                                    <%
                                        
                                        valor1 = valor.substring(0, 1);
                                        valor2 = valor.substring(1, 2);
                                        valor3 = valor.substring(2, 3);

                                        String marcaNC1 = marcaNC[ii - 1];
                                        String marcaNL1 = marcaNL[ii - 1];
                                        String marcaCF1 = marcaCF[ii - 1];

                                        if (valor1.equals("S")) {   
                                    %>
                                    
                                    <td class="text-align-center-md" data-th="Nominas de crédito">
                                        <div class="form__checkbox form__checkbox--inline" style="margin:0">
                                            <% if (marcaNC1.equals("S")) {%>
                                            <input checked class="nominas-credito" type="checkbox" name="nominas-credito" value="<%=ii%>" />
                                            <% } else {%>
                                            <input class="nominas-credito" type="checkbox" name="nominas-credito" value="<%=ii%>" />
                                            <% } %>
                                        </div>
                                    </td>
                                    <% } else {%>
                                    <td class="text-align-center-md" data-th="Nominas de crédito">
                                        <div class="form__checkbox form__checkbox--inline" style="display: none">
                                            <input type="checkbox" value="<%=ii%>" />
                                        </div>
                                    </td>
                                    <% } %>

                                    <% if (valor2.equals("S")) {   %>
                                    
                                    <td class="text-align-center-md" data-th="Nominas de ahorro">
                                        <div class="form__checkbox form__checkbox--inline" style="margin:0">
                                            <% if (marcaNL1.equals("S")) {%>
                                            <input checked class="nominas-ahorro" type="checkbox" name="nominas-ahorro" value="<%=ii%>" />
                                            <% } else {%>
                                            <input class="nominas-ahorro" type="checkbox" name="nominas-ahorro" value="<%=ii%>" />
                                            <% } %>
                                        </div>
                                    </td>
                                    <% } else {%>
                                    <td class="text-align-center-md" data-th="Nominas de ahorro">
                                        <div class="form__checkbox form__checkbox--inline" style="display: none">
                                            <input  type="checkbox"  value="<%=ii%>" />
                                        </div>
                                    </td>
                                    <% } %>


                                    <% if (valor3.equals("S")) {   %>
                                    
                                    <td class="text-align-center-md" data-th="Anexos de trabajadores">
                                        <div class="form__checkbox form__checkbox--inline" style="margin:0">
                                            <% if (marcaCF1.equals("S")) {%>
                                            <input checked class="nominas-trabajadores" type="checkbox" name="nominas-trabajadores" value="<%=ii%>" />
                                            <% } else {%>
                                            <input class="nominas-trabajadores" type="checkbox" name="nominas-trabajadores" value="<%=ii%>" />
                                            <% } %>
                                        </div>
                                    </td>
                                    <% } else {%>
                                    <td class="text-align-center-md" data-th="Anexos de trabajadores">
                                        <div class="form__checkbox form__checkbox--inline" style="display: none">
                                            <input type="checkbox" value="<%=ii%>" />
                                        </div>
                                    </td>
                                    <% }%>
                                    </tr>
                                    
                               <% } %>

                               <%--end while--%>
                               <% } %>
                                    </tbody>
                                </table>
                            </div>
                            <div class="form">
                                <div class="row justify-end-xs align-bottom-xs">
                                	
                                    <div class="col-xs-12 col-md-3">
                                        <div class="form__grupo js-formato-exportacion form__grupo--single"><span class="icono fln-abajo"></span>
                                            <select name="formato-exportacion" id="formato-exportacion">
                                                <% if (xFormatoDescarga.equals("csv2")) { %>
                                                    <option selected value="csv2">Texto CSV con punto y coma</option>
                                                <% } else { %>
                                                <option value="csv2">Texto CSV con punto y coma</option>
                                                <% } %>
                                                 <% if (xFormatoDescarga.equals("csv")) { %>
                                                    <option selected value="csv">Texto CSV con coma</option>
                                                <% } else { %>
                                                <option value="csv">Texto CSV con coma</option>
                                                <% } %> 
                                                <% if (xFormatoDescarga.equals("txt")) { %>
                                                <option selected value="txt">Texto plano</option>
                                                <% } else { %>
                                                    <option value="txt">Texto plano</option>
                                                <% } %>
                                               
                                            </select>
                                            <label for="formato-exportacion">Seleccionar formato de descarga</label>
                                        </div>
                                        <div class="separador--small oculto-md"></div>
                                    </div>


                                    <!--<div class="col-xs-12 col-md-2"> <a href=""> <input  value="Buscar" class="btn btn--primario btn--block-xs btn--relevante" > </a> </div>--> 
                                    <div class="col-xs-12 col-md-2"> <input type="submit" value="Descargar" id="Descargar" name="btn1" class="btn btn--primario btn--block-xs btn--relevante" > </div>
                                    <!--<div class="col-xs-12 col-md-2"><a class="btn btn--primario btn--block-xs btn--relevante" href="/SucursalVirtualWeb/Descarga?filename=comprimido.zip">Descargar</a></div>-->
                                    <!--<div class="col-xs-12 col-md-2"><a class="btn btn--primario btn--block-xs btn--relevante" href="#">Descargar</a></div>-->
                                    <!--BOTON-->
                                    
                                </div>
                                
                            </div>
							
                        </form>
                        <div class="row justify-end-md align-bottom-md" >
                                    <div class="col-xs-12 col-md-8">
                                 		<p style="margin-bottom:0 font-size: small;margin-top: -20px">
                                 			Formato de descarga
                                 		</p>
                                 		<p style="margin-bottom:0; font-size: x-small;"> 
                                    		<ul style="font-size: small;">
                                    			<li><b>Texto CSV separado por punto y coma:</b> formato comptible con excel, separa la información en columnas en forma automática</li>
                                    			<li><b>Texto CSV separado por coma:</b> formato para desarrollo de integraciones o para Excel configurado separado ","</li>
                                    			<li><b>Texto plano:</b> formato de largo fijo, para desarrollo de integraciones o leer la información por posiciones fijas</li>
                                    		</ul>
                                		</p>
                         			</div>
									</div>
                        <div class="separador--big"></div>
                    </div>
                </div>
            </section>
            <!-- <div class="container-fluid">
              <div class="marco">
                <div class="row">
                  <div class="col-xs-12">
                    <div class="btn__grupo"><a class="btn--volver" href="" onclick="window.history.back();return false;" type="button"><span class="fln-izquierda-light"></span> Volver</a></div>
                    <div class="separador"></div>
                  </div>
                </div>
              </div>
            </div> -->
            <footer class="footer">   
                <div class="footer__bottom">
                    <div class="footer__marco marco">
                        <div class="footer__container">
                            <div class="footer__logo"><img src="img/logo.svg" width="180" height="50"/></div>
                            <div class="footer__textos">
                                <p>Las Cajas de Compensación son fiscalizadas por la Superintendencia de Seguridad Social <a href="http://www.suseso.cl" target="_blank">www.suceso.cl</a></p>
                                <p>Todos los Derechos Reservados La Araucana ©Copyright <script>document.write(new Date().getFullYear())</script></p>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
        </main>

        <div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
        
        
	
        <script src="assets/js/polyfill.js"></script>
        <script src="assets/js/jquery-3.4.1.js"></script>
        <script src="assets/js/jquery-ui.js"></script>
        <script src="assets/js/jquery.Rut.js"></script>
        <script src="assets/js/automatizar.js"></script>
        <script src="assets/js/lozad.js"></script>
        <script src="assets/js/jquery.fancybox.js"></script>
        <script src="assets/js/owl.carousel.js"></script>
        <script src="assets/js/fln.js"></script>
        <script src="assets/js/funciones.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/alertifyjs@1.11.0/build/css/alertify.min.css" rel="stylesheet"/>
        <script src="https://cdn.jsdelivr.net/npm/alertifyjs@1.11.0/build/alertify.min.js"></script>
        
        <script>
			$(document).ready(function() {
			$('#Descargar').click(function(){
				$('#Descargar').prop('disabled', true);
				setTimeout("$('#Descargar').prop('disabled', false)", 5000);
				$('#form-id').submit();
    		});
    		$('#buscar').click(function(){
				$('#form-id').submit();
    		});
			
		});
	</script>
	
    </body>
</html>
