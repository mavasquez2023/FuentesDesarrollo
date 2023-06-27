<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>Solicitud Clave La Araucana</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/assets/css/fln.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources//fonts/fln-icons.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources//assets/css/estilos.css"/>
    <script>
      WebFontConfig = {
          google: {
              families: ['Open Sans:300,400,500,700']
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
  </head>
  <body>
    <main>
      <div class="container">
        <div class="separador"></div>
        <div class="row">
          <div class="col xs12 lg2 text-align-center-xs"><svg class="svg svg--secundario" xmlns="http://www.w3.org/2000/svg" width="155" height="140" viewBox="0 0 466.7 418.8"><path d="M198.9 218.8c60.4 0 109.4-49 109.4-109.4C308.3 49 259.3 0 198.9 0 138.5 0 89.5 49 89.5 109.4 89.5 169.8 138.5 218.8 198.9 218.8zM198.9 19.9c49.4 0 89.5 40.1 89.5 89.5 0 49.4-40.1 89.5-89.5 89.5 -49.4 0-89.5-40.1-89.5-89.5C109.5 60 149.5 19.9 198.9 19.9z"/><path d="M440.8 237.6h-46.5v-15c0-13-10.6-23.5-23.5-23.5h-67.9c-13 0-23.5 10.6-23.5 23.5v4.8c-76.2-15.7-155.8-8.9-228.9 20.6C19.6 260.4 0 286.9 0 316v42.6h206.8v34.2c0 14.3 11.6 26 26 26h208c6.9 0 13.4-2.7 18.3-7.6 4.9-4.9 7.6-11.4 7.6-18.3V263.6C466.7 249.3 455.1 237.6 440.8 237.6zM300.7 222.6c0-1.2 1-2.2 2.2-2.2h67.9c1.2 0 2.2 1 2.2 2.2v15h-54.4c-5.9-1.9-11.8-3.6-17.8-5.2V222.6zM19.9 338.7V316c0-20.8 14.9-40.3 37.9-49.5 52.6-21.2 108.8-30.1 164.5-26.6 -9.1 4-15.5 13.2-15.5 23.7v75.2H19.9zM445.3 392.8c0 2.5-2 4.6-4.6 4.6h-208c-2.5 0-4.6-2-4.6-4.6V301l84.5 30.1v17c0 5.9 4.8 10.7 10.7 10.7h26.7c5.9 0 10.7-4.8 10.7-10.7v-17l84.5-30.1V392.8zM445.3 278.1l-108.5 38.7 -108.6-38.6v-14.7c0-2.5 2-4.6 4.6-4.6h208c2.5 0 4.6 2 4.6 4.6V278.1z"/></svg>
            <div class="separador--big"></div>
          </div>
          <div class="col xs12 lg10">
            <div class="pasos">
              <div class="row justify-center-xs justify-start-sm">
                <div class="col xs12 sm12 md4 oculto-xs block-md">
                  <div class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-md">
                    <div class="pasos__icono"><svg class="svg" xmlns="http://www.w3.org/2000/svg" width="60" height="60" viewBox="0 0 60 60"><rect class="svg__fondo" width="60" height="60"/><path d="M53.4 12.3H6.6c-2.3 0-4.1 1.8-4.1 4.1v27.2c0 1.1 0.4 2.1 1.2 2.9 0.8 0.8 1.8 1.2 2.9 1.2h46.8c2.3 0 4.1-1.8 4.1-4.1V16.4C57.5 14.2 55.7 12.3 53.4 12.3zM6.6 14.7h46.8c0.9 0 1.7 0.8 1.7 1.7v27.2c0 0.9-0.8 1.7-1.7 1.7H6.6c-0.9 0-1.7-0.8-1.7-1.7l0-27.2C4.9 15.5 5.6 14.7 6.6 14.7z"/><path d="M29.7 26h20.7c0.6 0 1.2-0.5 1.2-1.2 0-0.6-0.5-1.2-1.2-1.2H29.7c-0.6 0-1.2 0.5-1.2 1.2 0 0.3 0.1 0.6 0.3 0.8C29.1 25.9 29.4 26 29.7 26z"/><path d="M29.7 31.2h20.7c0.6 0 1.2-0.5 1.2-1.2 0-0.6-0.5-1.2-1.2-1.2H29.7c-0.6 0-1.2 0.5-1.2 1.2 0 0.3 0.1 0.6 0.3 0.8C29.1 31 29.4 31.2 29.7 31.2z"/><path d="M29.7 36.3h8.9c0.6 0 1.2-0.5 1.2-1.2 0-0.6-0.5-1.2-1.2-1.2h-8.9c-0.6 0-1.2 0.5-1.2 1.2 0 0.3 0.1 0.6 0.3 0.8C29.1 36.2 29.4 36.3 29.7 36.3z"/><path d="M16.3 31.3c2.6 0 4.7-2.1 4.7-4.7 0-0.2 0-0.3 0-0.5l0 0c0-0.2-0.1-0.5-0.1-0.7 -0.1-0.2-0.1-0.4-0.2-0.7 -0.7-1.7-2.4-2.9-4.3-2.9 -1.9 0-3.6 1.1-4.3 2.9 -0.1 0.2-0.2 0.4-0.2 0.7 -0.1 0.2-0.1 0.5-0.1 0.7 0 0.2 0 0.3 0 0.5C11.6 29.2 13.7 31.3 16.3 31.3zM14.4 24.3c0.1-0.1 0.3-0.2 0.5-0.3 0.1 0 0.2-0.1 0.3-0.1 0.2-0.1 0.4-0.1 0.5-0.2 0.2 0 0.4-0.1 0.6-0.1 1.6 0 2.9 1.3 2.9 2.9 0 0.2 0 0.4-0.1 0.6 0 0.2-0.1 0.4-0.2 0.5 0 0.1-0.1 0.2-0.1 0.3h0c-0.1 0.2-0.2 0.3-0.3 0.5 -0.6 0.7-1.4 1.1-2.3 1.1 -0.2 0-0.4 0-0.6-0.1 -0.2 0-0.4-0.1-0.5-0.2 -0.1 0-0.2-0.1-0.3-0.1 -0.2-0.1-0.3-0.2-0.5-0.3 -0.1-0.1-0.3-0.3-0.4-0.4 -0.1-0.1-0.2-0.3-0.3-0.5 0-0.1-0.1-0.2-0.1-0.3 -0.1-0.2-0.1-0.4-0.2-0.5 0-0.2-0.1-0.4-0.1-0.6C13.4 25.7 13.7 24.9 14.4 24.3z"/><path d="M24.4 35.6c0-0.6-0.2-1.1-0.5-1.6 -0.1-0.2-0.3-0.4-0.5-0.6 -0.1-0.1-0.1-0.1-0.2-0.2 -0.2-0.2-0.4-0.3-0.6-0.4 -0.2-0.1-0.3-0.2-0.5-0.2 -3.8-1.5-8-1.5-11.8 0 -0.2 0.1-0.3 0.1-0.5 0.2 -0.2 0.1-0.4 0.3-0.6 0.4 -0.1 0.1-0.1 0.1-0.2 0.2 -0.2 0.2-0.3 0.4-0.5 0.6 -0.3 0.5-0.5 1.1-0.5 1.6v2.1h16.2V35.6zM11.1 34.2c0.6-0.2 1.3-0.5 1.9-0.6 0.6-0.2 1.3-0.3 2-0.3 0.2 0 0.4 0 0.7 0 1.1-0.1 2.2 0 3.3 0.2 0.9 0.2 1.7 0.4 2.6 0.8 0.1 0 0.2 0.1 0.3 0.1 0.1 0 0.2 0.1 0.2 0.1 0.1 0.1 0.1 0.1 0.2 0.2 0.1 0.1 0.3 0.3 0.3 0.5 0 0 0 0.1 0 0.1 0 0.1 0 0.1 0.1 0.2 0 0.1 0 0.1 0 0.2V36H9.9v-0.4c0-0.1 0-0.1 0-0.2 0-0.1 0-0.1 0.1-0.2 0 0 0-0.1 0-0.1 0.1-0.2 0.2-0.3 0.3-0.5 0.1-0.1 0.1-0.1 0.2-0.2 0.1-0.1 0.1-0.1 0.2-0.1C10.9 34.3 11 34.3 11.1 34.2z"/></svg></div>
                    <div class="pasos__data"><span>Paso 1</span>DATOS SOLICITANTE</div>
                  </div>
                </div>
                <div class="col xs12 sm12 md4">
                  <div class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-md activo">
                    <div class="pasos__icono"><svg xmlns="http://www.w3.org/2000/svg" class="svg__fondo" width="70" height="60" viewBox="0 0 835.2 420.7"><defs><rect width="835.2" height="420.7"/></defs><clipPath><use xlink:href="#SVGID_1_"/></clipPath><path class="st0" d="M835.2 379c0-27.8-18.9-53.4-48.2-65.2l0 0c-36.9-14.9-75.4-23.6-114.9-26.2 45.6-10.9 79.7-52.1 79.7-101 0-57.3-46.6-103.9-103.9-103.9 -57.3 0-103.9 46.6-103.9 103.9 0 49 34 90.1 79.7 101 -10.9 0.7-21.7 1.9-32.5 3.5 -0.2-0.1-0.4-0.2-0.6-0.2 -111.8-45-236.6-45-348.4 0 -10.2-1.5-20.4-2.6-30.7-3.3 45.6-10.9 79.7-52.1 79.7-101 0-57.3-46.6-103.9-103.9-103.9 -57.3 0-103.9 46.6-103.9 103.9 0 49 34 90.1 79.7 101 -39.4 2.5-78 11.3-114.9 26.2C18.9 325.6 0 351.2 0 379v41.7h183 466.7 185.5V379zM566.5 186.5c0.1-44.8 36.6-81.3 81.4-81.4 44.9 0 81.4 36.5 81.4 81.4 0 44.9-36.5 81.4-81.4 81.4C603 267.9 566.5 231.4 566.5 186.5M634.5 321.2c-0.8-1.8-1.2-6.3-1.3-11.6 10.2-0.4 20.6-0.4 30.8 0.1 0 5.3-0.5 9.7-1.3 11.5 -1.7 4.1-9.1 7.7-12.2 8.7l-1.9 0.6 -1.9-0.6C643.3 328.8 636.2 325.1 634.5 321.2M455.9 306.8c2.9-6.7 3.4-16.9 3.4-24.2 41.7 4.1 83 14.1 122.6 30 27 10.9 44.5 33.7 44.5 58.1v26.7H474.9c0.7-7 0.1-15.7-3.4-26 -5.4-15.9-21.2-35.6-31.4-47.3C446.1 320.2 452.6 314.5 455.9 306.8M395.4 280.9c14.6-0.7 29.2-0.7 43.8 0.1 0.1 6.6-0.3 14.5-1.8 17.9 -2.7 6.4-13.4 11.2-16.9 12.4l-3.2 1.1 -3.2-1.1c-4.4-1.4-14.3-6.4-16.9-12.4C395.8 295.5 395.3 287.6 395.4 280.9M378.8 306.8c3.3 7.8 9.8 13.4 15.8 17.2 -10.2 11.7-26 31.4-31.4 47.3 -3.5 10.3-4.1 19.1-3.4 26H206.3v-26.7c0-24.4 17.5-47.2 44.5-58.1 40.2-16.2 82.2-26.3 124.6-30.2C375.4 289.7 375.9 300 378.8 306.8M190 330l-1.9 0.6 -1.9-0.6c-3.4-1.1-10.6-4.8-12.2-8.7 -0.8-1.8-1.2-6.3-1.3-11.6 10.2-0.4 20.6-0.4 30.8 0.1 0 3.5-0.2 6.6-0.6 8.8 -2.2 2.6-4.3 5.3-6.2 8.1C194.3 328.3 191.6 329.4 190 330M105.9 186.5c0.1-44.8 36.6-81.3 81.4-81.4 44.9 0 81.4 36.5 81.4 81.4 0 44.9-36.5 81.4-81.4 81.4C142.4 267.9 105.9 231.4 105.9 186.5M140.3 398.1H22.5V379c0-18.6 13.4-35.9 34.1-44.3 30.8-12.4 63.1-20.4 96.2-23.6 0.1 5.8 0.7 12.9 2.9 18 2.1 5 6 9.5 11.4 13.3 -8.9 10.4-19.8 24.6-23.8 36.5C141 385.4 140 391.9 140.3 398.1M160.2 398.1c-0.2-3.1 0-7.5 1.8-12.9 3.4-9.9 15.2-24.6 23.4-33.8 -1.6 6.2-2.5 12.7-2.5 19.2v27.5H160.2zM454.7 397.3H380c-0.7-4.6-0.7-11.2 2.2-19.6 5.4-15.7 26.5-39.9 35.2-49.1 8.7 9.1 29.8 33.3 35.2 49.1C455.4 386.1 455.4 392.7 454.7 397.3M676.6 398.1h-26.9v-27.5c0-6.9-1-13.7-2.7-20.2 0.6-0.7 1.2-1.3 1.8-1.9 8.1 8.7 22.3 25.7 26.1 36.7C676.6 390.7 676.8 395 676.6 398.1M812.6 398.1H696.5c0.3-6.2-0.7-12.7-3-19.3 -4.1-11.9-14.9-26.1-23.8-36.5 5.4-3.8 9.3-8.3 11.4-13.3 2.1-5 2.8-12.1 2.9-17.8 32.5 3.4 64.3 11.3 94.6 23.5 20.7 8.3 34.1 25.7 34.1 44.3V398.1z"/><path class="st0" d="M416.3 256.7c70.9 0 128.3-57.5 128.3-128.3C544.7 57.5 487.2 0 416.3 0S288 57.5 288 128.3C288 199.2 345.5 256.7 416.3 256.7M416.3 23.3c58 0 105 47 105 105 0 58-47 105-105 105s-105-47-105-105C311.4 70.4 358.4 23.4 416.3 23.3"/></svg></div>
                    <div class="pasos__data"><span>Paso 2</span>DATOS EMPRESA</div>
                  </div>
                </div>
                <div class="col xs12 sm12 md4 oculto-xs block-md">
                  <div class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-md">
                    <div class="pasos__icono"><svg xmlns="http://www.w3.org/2000/svg" class="svg__fondo" width="55" height="60" viewBox="0 0 374.3 315"><defs><rect width="374.3" height="315"/></defs><clipPath><use xlink:href="#SVGID_1_"/></clipPath><path class="st0" d="M340.8 57h-73.5V30c0-16.5-13.4-30-30-30H137c-16.5 0-30 13.4-30 30v27H33.5C15 57 0 72 0 90.5v191C0 300 15 315 33.5 315h307.3c8.9 0 17.4-3.5 23.7-9.8 6.3-6.3 9.8-14.8 9.8-23.7v-191C374.3 72 359.3 57 340.8 57M245.3 30v27H129V30c0-4.4 3.6-8 8-8h100.3C241.7 22 245.3 25.6 245.3 30M352.4 90.5v25l-165.2 58.9L22 115.6V90.5c0-6.4 5.2-11.6 11.6-11.6h307.3C347.2 78.9 352.4 84.1 352.4 90.5M195.9 204.3h-17.4v-9.7l5.1 1.8c2.4 0.8 5 0.8 7.4 0l5-1.8V204.3zM22 138.9l134.5 47.8v28.5c0 6.1 4.9 11 11 11h39.4c6.1 0 11-4.9 11-11v-28.5l134.5-48v142.7c0 6.4-5.2 11.6-11.6 11.6H33.5c-6.4 0-11.6-5.2-11.6-11.6V138.9z"/></svg></div>
                    <div class="pasos__data"><span>Paso 3</span>DATOS REPRESENTANTE LEGAL</div>
                  </div>
                </div>
              </div>
            </div>
            <div class="separador--big"></div>
            <div class="row">
              <div class="col xs12 lg6">
                <form class="form" action="/ClaveEmpresasWeb/paso3" method="post" id="paso2">
                  <div class="form__grupo" data-animacion="placeholder" data-comentario="Sin puntos ni guión. Ej: 12345678k">
                    <input class="text requerido tipo_rut" type="text" id="rut" name="rutemp" maxlength="9"/>
                    <label for="rut">RUT Empresa</label>
                  </div>
                  <div class="form__grupo" data-animacion="placeholder">
                    <input class="text requerido tipo_texto" type="text" id="nombre" name="nomEmp" data-minlength="3" maxlength="80"/>
                    <label for="nombre">Nombre Empresa</label>
                  </div>
                  <div class="form__grupo" data-animacion="placeholder" data-comentario="Ej: +56221234567">
                    <input class="text tipo_fono tipo_numerico" type="text" id="telefono" name="telEmp" data-prefijo="+56" data-prefijo-tipo="fijo" data-minlength="12" maxlength="12"/>
                    <label for="teléfono" data-comentario="(Opcional)">Teléfono Empresa</label>
                  </div>
                  <div class="form__grupo" data-animacion="placeholder" data-comentario="Ej: nombre@correo.cl">
                    <input class="text requerido tipo_email" type="text" id="email" name="emailEmp"/>
                    <label for="email">Email Empresa</label>
                  </div>
                  <div class="separador"></div>
                  <div class="alerta alerta--error" id="errores_paso2" style="display:none"></div>
                  <div class="btn__grupo text-align-right-md text-align-center-xs"><a class="btn btn--primario" href="#" onClick="validarPaso2(); return false;">Siguiente</a>
                  </div>
                </form>
              </div>
              <div class="col xs12 lg6">
                <div class="bloque">
                  <h4 class="bloque__titulo color--secundario">Para solicitar su <span class="bold">clave de empresa</span> siga los siguientes pasos</h4>
                  <div class="separador--small"></div>
                  <div class="row">
                    <div class="col xs12 md12">
                      <div class="bloque__item">
                        <div class="bloque__cabecera">
                          <div class="bloque__icono"><span>1</span></div>
                          <p class="bloque__texto bloque__texto__titulo">Descargue la solicitud de clave.</p>
                        </div>
                        <div class="row">
                          <div class="col xs12 lg12 xl6"><a class="bloque__descarga" href="<%=request.getContextPath() %>/resources/pdf/Sol_Clave_Empresa.pdf" target="_blank">Descargar aqu&iacute;­ <span class="icon fln-descargar"></span></a>
                            <p class="bloque__texto bloque__texto__descarga">Si requiere una clave para empresa.</p>
                          </div>
                          <div class="col xs12 lg12 xl6"><a class="bloque__descarga" href="<%=request.getContextPath() %>/resources/pdf/Sol_Clave_Holding.pdf" target="_blank">Descargar aqu&iacute;­ <span class="icon fln-descargar"></span></a>
                            <p class="bloque__texto bloque__texto__descarga">Si la empresa pertenece a un holding o requiere mas de una clave Encargado, quien tendr&aacute; acceso a la informaci&oacute;n de todas las empresas asignadas.</p>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col xs12 md12">
                      <div class="bloque__item">
                        <div class="bloque__cabecera">
                          <div class="bloque__icono"><span>2</span></div>
                          <p class="bloque__texto">Imprima la solicitud y complete los datos requeridos. La solicitud debe ser autorizada por el representante legal u otra persona con firma certificada ante la caja.</p>
                        </div>
                      </div>
                    </div>
                    <div class="col xs12 md12">
                      <div class="bloque__item">
                        <div class="bloque__cabecera">
                          <div class="bloque__icono"><span>3</span></div>
                          <p class="bloque__texto">Escanee la solicitud y guarde el archivo en formato PDF.</p>
                        </div>
                      </div>
                    </div>
                    <div class="col xs12 md12">
                      <div class="bloque__item">
                        <div class="bloque__cabecera">
                          <div class="bloque__icono"><span>4</span></div>
                          <p class="bloque__texto">Complete los datos y adjunte su solicitud escaneada directamente en el formulario y presione Enviar.</p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
    <script>
      var _captcha = document.getElementById('captcha')
      var onloadCallback = function() {
        if (_captcha) {
          grecaptcha.render(_captcha, {
            'sitekey': '6LcSiKkUAAAAACvNghZpRuyOQgKN9RgwDNtmgMMu',
            'callback': recaptchaCallback,
          })
        }
      }
    </script>
    <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&amp;render=explicit"></script>
    <script src="<%=request.getContextPath() %>/resources/assets/js/polyfill.js"></script>
    <script src="<%=request.getContextPath() %>/resources/assets/js/jquery-3.3.1.js"></script>
    <script src="<%=request.getContextPath() %>/resources/assets/js/jquery-ui.js"></script>
    <script src="<%=request.getContextPath() %>/resources/assets/js/automatizar.js"></script>
    <script src="<%=request.getContextPath() %>/resources/assets/js/fln.js"></script>
    <script src="<%=request.getContextPath() %>/resources/assets/js/funciones.js"></script>
  </body>
</html>