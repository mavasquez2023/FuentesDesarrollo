<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>Crédito Digital - La Araucana</title>
    <link rel="icon" href="/favicon.ico">
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <!-- <link rel="stylesheet" href="./assets/css/jquery-ui.css"/> -->
		<link rel="stylesheet" href="./assets/css/base.css"/>
		<link rel="stylesheet" href="./fonts/fln-icons.css"/>
    <link rel="stylesheet" href="./assets/css/estilos.css"/>
    <link rel="stylesheet" href="./resources/css/font-awesome-6.4.0.css"/>
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
    	<div id="dataEstadoCivil" data-estadocivil="${estadoCivil}"></div>
    	<div id="dataTipoContrato" data-tipocontrato="${tipoContrato}"></div>
    	<div id="dataRegion" data-region="${codRegion}"></div>
    	<div id="dataComuna" data-comuna="${codComuna}"></div>
      <div class="container container-flujo">
        <div class="marco">
        	<!--  
        	<div id="mySidenav" class="sidenav">	        	      	
				<a href="#" id="contact" onClick="paso2Contacto(); return false;">
					<div class="row justify-content-start">
						<div class="col-3" style="margin-top:8px; margin-left:5px;">
							<i class="fa-solid fa-phone-volume fa-beat-fade fa-2xl"></i>					
						</div>
						<div class="col-7">
							¿Necesitas asistencia telef&oacute;nica?						
						</div>
					</div>
				</a>	        	
			</div>
			-->
			<form action="" class="form" id="validacion-datos">
          <div class="row row--bg row--bg-validacion-dos">
            <div class="col-xs-12 col-lg-5">
              <div class="separador"></div>
              <div class="info">
                <div class="info__box info__box--centrado info__box--bigtitle">
                  <h2>Simula y solicita tu cr&eacute;dito donde te encuentres</h2>
                </div>
                <div class="info__box info__box--midtitle info__box--centrado">
                  <h4>Hola</h4>
                  <h2>${nombreCompleto}</h2>
                </div>
                <div class="info__box info__box--monto">
					<div class="box__minibox">
						<span>Monto solicitado</span>
						<h2>$${monto}</h2>
						<p>en ${cuotas} cuotas</p>
						<c:choose>
						    <c:when test="${afiliado.segmento!='PENSIONADOS'}">
						    	<span>con la empresa ${razonSocial}</span>.
						    </c:when>    
						    <c:otherwise>
						        <span>con la institución ${razonSocial}</span>. 
						    </c:otherwise>
						</c:choose>
					</div>
                </div>
              </div>
            </div>
            <div class="col-xs-12 col-lg-7 col-azul col-azul--datos">
              <div class="pasos-main pasos-main--dos pasos-main--datos">
                <div class="separador"></div>
                <div class="row">
                  <div class="col-xs-12">
                    <div class="pasos">
                      <div class="pasos--uno">
                        <h4>Datos de contacto y <span>autorizaci&oacute;n cr&eacute;dito</span></h4>
                        <div class="pasos__box">
                          <div class="pasos__item pasos__item--uno">
                            <span>1</span>
                          </div>
                          <div class="pasos__item pasos__item--dos">
                            <span>2</span>
                          </div>
                          <div class="pasos__item pasos__item--tres">
                            <span>3</span>
                          </div>
                          <div class="pasos__item pasos__item--cuatro activo">
                            <span>4</span>
                          </div>
                          <div class="pasos__item pasos__item--cinco ">
                            <span>5</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-xs-12 col-lg-12">
                      <div class="form__interior">
                        <h4>Datos del Titular</h4>
                        <div class="box box--form">
                          <div class="form__grupo" data-animacion="placeholder">
                            <input type="text" class="text tipo_calendario calendario requerido" id="nacimiento" name="nacimiento" readonly value="${fechaNacimiento}">
                            <label id="labelNacimiento" for="nacimiento">Fecha de nacimiento</label>
                            <span class="icono fln-calendario"></span>
                          </div>
  
                          <div class="form__grupo" data-animacion="placeholder">
                            <i class="icono bi bi-chevron-down"></i>
                            <select class="requerido" name="estado-civil" id="estado-civil">
                              <optgroup label="estado-civil" hidden="hidden">Estado civil</optgroup>
                              <option value=""></option>
                              <option value="2" class="estadoCivil2">Soltero (a)</option> 
                              <option value="3" class="estadoCivil3">Casado (a)</option>
                              <option value="4" class="estadoCivil4">Viudo (a)</option>
                              <option value="5" class="estadoCivil5">Divorciado (a)</option>
                              <option value="6" class="estadoCivil6">Matrimonio Anulado</option>
                              <option value="7" class="estadoCivil7">Conviviente de hecho</option>
                              <option value="8" class="estadoCivil8">Separado</option>
                              <option value="9" class="estadoCivil9">Conviviente Civil</option>
                            </select>
                            <label id="labelEstadoCivil" for="estado-civil">Estado civil</label>
                          </div>
  
                          <div class="form__grupo" data-animacion="placeholder">
                            <i class="icono bi bi-chevron-down"></i>
                                                  
                              <c:choose>
							    <c:when test="${afiliado.segmento!='PENSIONADOS'}">
							    	<select class="requerido" name="tipo-de-contrato" id="tipo-de-contrato">        
								    	<optgroup label="tipo-de-contrato" hidden="hidden">Tipo de contrato</optgroup>
	                              		<option value=""></option>
								        <option value="02"> Contrato Indefinido</option>
										<option value="03"> Contrato a Plazo Fijo</option>
										<option value="04"> Contrato Por faena</option>
										<option value="05"> Contrato a Honorarios</option>
										<option value="06"> Otro tipo de contrato</option>
										<option value="07"> Estatuto docente</option>
										<option value="08"> Estatuto administ. no docente</option>
										<option value="09"> Estatuto de salud primaria</option>
									</select>
	                          		<label id="labelTipoContrato" for="tipo-de-contrato">Tipo de contrato</label>
							    </c:when>    
							    <c:otherwise>
							    	<select class="requerido" name="tipo-de-contrato" id="tipo-de-contrato">        
								    	<optgroup label="tipo-de-contrato" hidden="hidden">Tipo de pensi&oacute;n</optgroup>
	                              		<option value=""></option>
								        <option value="34"> ANTIGUEDAD </option>
					                    <option value="35"> VEJEZ </option>
					                    <option value="36"> VEJEZ ANTICIPADA </option>
					                    <option value="37"> ORFANDAD </option>
					                    <option value="38"> MONTEPIO </option>
					                    <option value="39"> INVALIDEZ PARCIAL </option>
					                    <option value="40"> INVALIDEZ TOTAL </option>
					                    <option value="41"> ACCIDENTES DEL TRABAJO </option>
					                    <option value="42"> ENFERMEDADES PROFESIONALES </option>
					                    <option value="10"> Pensión retiro programado </option>
					                    <option value="11"> Pensión renta vitalicia </option>
					                    <option value="12"> Retiro prog. y renta vitalicia </option>
					                    <option value="13"> Otra pensión temporal </option>
					                    <option value="14"> Pensión de sobrevivencia </option>
					                    <option value="15"> Pensión de invalidez </option>
					                    <option value="16"> Pensión de Retiro FFAA </option>
					                    <option value="17"> Pensión de Montepío FFAA </option>
				                    </select>
	                          		<label id="labelTipoContrato" for="tipo-de-contrato">Tipo de pensi&oacute;n</label>
							    </c:otherwise>
							  </c:choose>
                            
                          </div>
                        </div>
  
                        <h4>Datos de Contacto</h4>
                        <div class="box box--form">
                          <div class="form__grupo" data-animacion="placeholder">
                            <input type="text" class="text tipo_telefono celular requerido" name="celular" id="celular" data-prefijo="+569" data-prefijo-tipo="celular" data-minlength="12" maxlength="12" onblur="validarPhone(this);" value="${celular}">
                            <label for="celular">Celular</label>
                          </div>
                          <div class="form__grupo" data-animacion="placeholder">
                            <input type="text" class="text tipo_email requerido" name="email" id="email" maxlength="150" value="${email}">
                            <label for="email">Email</label>
                          </div>
                          <div class="form__grupo" data-animacion="placeholder">
                            <input type="text" class="text tipo_telefono" name="telefono" id="telefono" data-prefijo="+56" data-prefijo-tipo="celular" data-minlength="12" maxlength="12" onblur="validarPhone(this);" value="${telefono}">
                            <label for="telefono">Telefono</label>
                          </div>
                          <div class="form__grupo" data-animacion="placeholder">
                            <input type="text" class="text tipo_texto requerido" name="direccion" id="direccion" value="${direccion}">
                            <label for="direccion">Dirección</label>
                          </div>
                          <div class="form__grupo" data-animacion="placeholder">
                            <input type="text" class="text tipo_numerico requerido" name="numero" id="numero" value="${nroDirecion}">
                            <label for="numero">Número</label>
                          </div>
                          <div class="form__grupo" data-animacion="placeholder">
                            <input type="text" class="text tipo_texto" name="poblacion-o-villa" id="poblacion-o-villa" value="${villaPoblacion}">
                            <label for="poblacion-o-villa">Población o Villa</label>
                          </div>
                          <div class="form__grupo" data-animacion="placeholder">
                            <i class="icono bi bi-chevron-down"></i>
                            <select class="requerido" name="region" id="region">
                              <optgroup label="region" hidden="hidden">Región</optgroup>
                              <option value=""></option>
                              <c:forEach items="${regiones}" var="reg">
                              	<option value="${reg.idregion}">${reg.nombre}</option>
                              </c:forEach>
                            </select>
                            <label id="labelRegion" for="region">Región</label>
                          </div>
                          <div class="form__grupo" data-animacion="placeholder">
                            <i class="icono bi bi-chevron-down"></i>
                            <select class="requerido" name="comuna" id="comuna">
                              <optgroup label="comuna" hidden="hidden">Comuna</optgroup>
                              <option value=""></option>
                              <c:forEach items="${comunas}" var="comu">
                              	<option value="${comu.idcomuna}">${comu.nombre}</option>                              
                              </c:forEach>  
                            </select>
                            <label id="labelComuna" for="comuna">Comuna</label>
                          </div>
                        </div>
                     
                      </div>
					</form>                    
					<div class="alerta alerta--error" id="errores_validacion-datos" style="display:none"></div>
					<div class="botonera botonera--right">
						<a href="" class="boton boton--terciario" onclick="validarCamposFormCrearOferta(); return false;">Continuar</a>
					</div>
					<div class="separador"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
    
		
		
    <!-- Preloader buscando ejecutivo -->
	<div class="modal-generico" id="buscando-ejecutivo" style="display: none;">
		<div class="modal-generico__interior preload">
			<svg width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
				<path d="M20.0001 11.8855C15.5186 11.8855 11.8857 15.5185 11.8857 19.9999C11.8857 24.4814 15.5186 28.1143 20.0001 28.1143C24.4815 28.1143 28.1145 24.4814 28.1145 19.9999C28.1145 15.5185 24.4815 11.8855 20.0001 11.8855ZM14.3857 19.9999C14.3857 16.8992 16.8993 14.3855 20.0001 14.3855C23.1008 14.3855 25.6145 16.8992 25.6145 19.9999C25.6145 23.1007 23.1008 25.6143 20.0001 25.6143C16.8993 25.6143 14.3857 23.1007 14.3857 19.9999Z" fill="#1F7ABC"/>
				<path d="M24.4912 3.35844C23.1713 -1.11948 16.8287 -1.11948 15.5088 3.35844L15.2742 4.15448C14.8811 5.48798 13.358 6.11888 12.1372 5.45388L11.4084 5.0569C7.30874 2.82381 2.82382 7.30875 5.0569 11.4084L5.45388 12.1372C6.11888 13.358 5.48798 14.8811 4.15448 15.2742L3.35844 15.5088C-1.11948 16.8287 -1.11948 23.1713 3.35844 24.4912L4.15448 24.7258C5.48798 25.1189 6.11888 26.642 5.45387 27.8628L5.0569 28.5916C2.82382 32.6912 7.30873 37.1762 11.4084 34.9431L12.1372 34.5461C13.358 33.8811 14.8811 34.512 15.2742 35.8455L15.5088 36.6416C16.8287 41.1195 23.1713 41.1195 24.4912 36.6416L24.7258 35.8455C25.1189 34.512 26.642 33.8811 27.8628 34.5461L28.5916 34.9431C32.6913 37.1762 37.1762 32.6913 34.9431 28.5916L34.5461 27.8628C33.8811 26.642 34.512 25.1189 35.8455 24.7258L36.6416 24.4912C41.1195 23.1713 41.1195 16.8287 36.6416 15.5088L35.8455 15.2742C34.512 14.8811 33.8811 13.358 34.5461 12.1372L34.9431 11.4084C37.1762 7.30873 32.6912 2.82382 28.5916 5.05691L27.8628 5.45388C26.642 6.11888 25.1189 5.48798 24.7258 4.15448L24.4912 3.35844ZM17.9068 4.06525C18.522 1.97825 21.478 1.97825 22.0932 4.06525L22.3278 4.86128C23.1711 7.72248 26.4392 9.07616 29.0587 7.64931L29.7875 7.25234C31.6982 6.21157 33.7884 8.30184 32.7477 10.2125L32.3507 10.9413C30.9238 13.5608 32.2775 16.8289 35.1387 17.6722L35.9348 17.9068C38.0217 18.522 38.0217 21.478 35.9348 22.0932L35.1387 22.3278C32.2775 23.1711 30.9238 26.4392 32.3507 29.0587L32.7477 29.7875C33.7884 31.6982 31.6982 33.7884 29.7875 32.7477L29.0587 32.3507C26.4392 30.9238 23.1711 32.2775 22.3278 35.1387L22.0932 35.9348C21.478 38.0217 18.522 38.0217 17.9068 35.9348L17.6722 35.1387C16.8289 32.2775 13.5608 30.9238 10.9413 32.3507L10.2125 32.7477C8.30184 33.7884 6.21157 31.6982 7.25234 29.7875L7.64931 29.0587C9.07615 26.4392 7.72249 23.1711 4.86129 22.3278L4.06525 22.0932C1.97825 21.478 1.97825 18.522 4.06524 17.9068L4.86129 17.6722C7.72249 16.8289 9.07616 13.5608 7.64931 10.9413L7.25234 10.2125C6.21158 8.30184 8.30184 6.21158 10.2125 7.25234L10.9413 7.64931C13.5608 9.07616 16.8289 7.72249 17.6722 4.86129L17.9068 4.06525Z" fill="#1F7ABC"/>
			</svg>
			<h2>Estamos buscando un ejecutivo</h2>
			<p>Espera un momento por favor</p>
		</div>
	</div>
	
	<!-- Preloader evaluando credito-->
	<div class="modal-generico" id="evaluando-credito" style="display: none;">
		<div class="modal-generico__interior preload">
			<svg width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
				<path d="M20.0001 11.8855C15.5186 11.8855 11.8857 15.5185 11.8857 19.9999C11.8857 24.4814 15.5186 28.1143 20.0001 28.1143C24.4815 28.1143 28.1145 24.4814 28.1145 19.9999C28.1145 15.5185 24.4815 11.8855 20.0001 11.8855ZM14.3857 19.9999C14.3857 16.8992 16.8993 14.3855 20.0001 14.3855C23.1008 14.3855 25.6145 16.8992 25.6145 19.9999C25.6145 23.1007 23.1008 25.6143 20.0001 25.6143C16.8993 25.6143 14.3857 23.1007 14.3857 19.9999Z" fill="#1F7ABC"/>
				<path d="M24.4912 3.35844C23.1713 -1.11948 16.8287 -1.11948 15.5088 3.35844L15.2742 4.15448C14.8811 5.48798 13.358 6.11888 12.1372 5.45388L11.4084 5.0569C7.30874 2.82381 2.82382 7.30875 5.0569 11.4084L5.45388 12.1372C6.11888 13.358 5.48798 14.8811 4.15448 15.2742L3.35844 15.5088C-1.11948 16.8287 -1.11948 23.1713 3.35844 24.4912L4.15448 24.7258C5.48798 25.1189 6.11888 26.642 5.45387 27.8628L5.0569 28.5916C2.82382 32.6912 7.30873 37.1762 11.4084 34.9431L12.1372 34.5461C13.358 33.8811 14.8811 34.512 15.2742 35.8455L15.5088 36.6416C16.8287 41.1195 23.1713 41.1195 24.4912 36.6416L24.7258 35.8455C25.1189 34.512 26.642 33.8811 27.8628 34.5461L28.5916 34.9431C32.6913 37.1762 37.1762 32.6913 34.9431 28.5916L34.5461 27.8628C33.8811 26.642 34.512 25.1189 35.8455 24.7258L36.6416 24.4912C41.1195 23.1713 41.1195 16.8287 36.6416 15.5088L35.8455 15.2742C34.512 14.8811 33.8811 13.358 34.5461 12.1372L34.9431 11.4084C37.1762 7.30873 32.6912 2.82382 28.5916 5.05691L27.8628 5.45388C26.642 6.11888 25.1189 5.48798 24.7258 4.15448L24.4912 3.35844ZM17.9068 4.06525C18.522 1.97825 21.478 1.97825 22.0932 4.06525L22.3278 4.86128C23.1711 7.72248 26.4392 9.07616 29.0587 7.64931L29.7875 7.25234C31.6982 6.21157 33.7884 8.30184 32.7477 10.2125L32.3507 10.9413C30.9238 13.5608 32.2775 16.8289 35.1387 17.6722L35.9348 17.9068C38.0217 18.522 38.0217 21.478 35.9348 22.0932L35.1387 22.3278C32.2775 23.1711 30.9238 26.4392 32.3507 29.0587L32.7477 29.7875C33.7884 31.6982 31.6982 33.7884 29.7875 32.7477L29.0587 32.3507C26.4392 30.9238 23.1711 32.2775 22.3278 35.1387L22.0932 35.9348C21.478 38.0217 18.522 38.0217 17.9068 35.9348L17.6722 35.1387C16.8289 32.2775 13.5608 30.9238 10.9413 32.3507L10.2125 32.7477C8.30184 33.7884 6.21157 31.6982 7.25234 29.7875L7.64931 29.0587C9.07615 26.4392 7.72249 23.1711 4.86129 22.3278L4.06525 22.0932C1.97825 21.478 1.97825 18.522 4.06524 17.9068L4.86129 17.6722C7.72249 16.8289 9.07616 13.5608 7.64931 10.9413L7.25234 10.2125C6.21158 8.30184 8.30184 6.21158 10.2125 7.25234L10.9413 7.64931C13.5608 9.07616 16.8289 7.72249 17.6722 4.86129L17.9068 4.06525Z" fill="#1F7ABC"/>
			</svg>
			<h2>Nos encontramos evaluando tu Cr&eacute;dito</h2>
			<p>Espere un momento por favor</p>
		</div>
	</div>
	
    <script src="./assets/js/polyfill.js"></script>
    <script src="./assets/js/jquery-3.6.0.js"></script>
    <script src="./assets/js/jquery-ui.js"></script>
    <script src="./assets/js/automatizar.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
    <script src="./assets/js/popper.min.js"></script>
    <script src="./assets/js/tippy-bundle.umd.js"></script>
    <script src="./assets/js/fln.js"></script>
    <script src="./assets/js/funciones.js"></script>
    <script src="./resources/js/font-awesome-6.4.0.js"></script>
    <script type="text/javascript">
	    var dataEstadoCivil = $('#dataEstadoCivil').data('estadocivil');
	    var dataTipoContrato = $('#dataTipoContrato').data('tipocontrato');
	    var dataRegion = $('#dataRegion').data('region');
	    var dataComuna = $('#dataComuna').data('comuna');
	    
	    $("#estado-civil option").each(function(){
		   if($(this).attr('value') == dataEstadoCivil){
			   $(this).attr('selected', true);
			   $('#labelEstadoCivil').addClass('focus');			   
		   }
		});
	    
	    $("#tipo-de-contrato option").each(function(){
		   if($(this).attr('value') == dataTipoContrato){
			   $(this).attr('selected', true);
			   $('#labelTipoContrato').addClass('focus');			   
		   }
		});
	    
	    $("#region option").each(function(){
		   if($(this).attr('value') == dataRegion){
			   $(this).attr('selected', true);
			   $('#labelRegion').addClass('focus');			   
		   }
		});
	    
	    $("#comuna option").each(function(){
	    	var valueSelect = parseInt($(this).attr('value'));
	    	var valueData = parseInt(dataComuna);
		   if(valueSelect == valueData){
			   $(this).attr('selected', true);
			   $('#labelComuna').addClass('focus');			   
		   }
		});
	    
	    
	    $('#region').change(
            function() {
                $.getJSON('comunas.do', {
                    regionNombre : $(this).val(),
                    ajax : 'true'
                }, function(data) {
                    var html = '<option value=""></option>';
                    var len = data.length;
                    
                    for (var i = 0; i < len; i++) {

                        html += '<option value="' + data[i].idcomuna + '">'
                                + data[i].nombre + '</option>';
                    }
                    html += '</option>';
                    $('#comuna').html(html);
                });
            }
        );
	    
    </script>
    <script>
	    function resizeIframe(e){
	        //console.log('resize');
	        var medidafija = e;
	        var alto = 0;
	        setTimeout(function(){
	            if(medidafija > 0){
	                alto = medidafija;
	                window.scrollTo(0, 0);
	            }else{
	                alto = $('body').innerHeight();
	            }
	            let message = { height: alto, width: '100%' };  
	        
	            // window.top refers to parent window
	            window.top.postMessage(message, "*");
	     
	        }, 200)
	    } resizeIframe(); 
    </script>
  </body>
</html>