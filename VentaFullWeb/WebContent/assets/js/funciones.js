// "use strict";

function _typeof(obj) {
  if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") {
    _typeof = function _typeof(obj) {
      return typeof obj;
    };
  } else {
    _typeof = function _typeof(obj) {
      return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj;
    };
  }
  return _typeof(obj);
}


var disableKeydownRepeat = function disableKeydownRepeat(selector, callback) {
  selector = document.querySelectorAll(selector);
  var _iteratorNormalCompletion = true;
  var _didIteratorError = false;
  var _iteratorError = undefined;
  try {
    for (var _iterator = selector[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
      var input = _step.value;
      input.addEventListener("keydown", function(event) {
        if (event.repeat && event.key !== "Backspace") {
          event.preventDefault();
        }

        if (typeof callback === "function") {
          callback();
        }
      });
    }
  } catch (err) {
    _didIteratorError = true;
    _iteratorError = err;
  } finally {
    try {
      if (!_iteratorNormalCompletion && _iterator.return != null) {
        _iterator.return();
      }
    } finally {
      if (_didIteratorError) {
        throw _iteratorError;
      }
    }
  }
};
var paso1;


function resumir_textos(texto, n, pos) {
  if (texto && n) {
    if (texto.length > n) {
      if (pos == 'centro') {
        return "".concat(texto.substr(0, (n - 3) / 2), "...").concat(texto.substr(texto.length - (n - 3) / 2, texto.length));
      } else if (pos == 'inicio') {
        return "...".concat(texto.substr(texto.length - n, texto.length));
      } else {
        return "".concat(texto.substr(0, n), "...");
      }
    }
  } else {
    console.warn('Se debe pasar como argumento el texto y la cantidad de palabras que se espera retornar.');
  }
  return texto;
}


var _mostrar_alerta;
var alerta = function alerta(opciones) {
  if (_typeof(opciones) == 'object') {
    if (typeof opciones.texto == 'undefined') {
      opciones.texto = 'Se debe pasar el texto dentro del objeto';
    }
    if (typeof opciones.tipo == 'undefined') {
      opciones.tipo = 'error';
    }
    if (typeof opciones.posicion == 'undefined') {
      opciones.posicion = 'top';
    }
    if (typeof opciones.tiempo == 'undefined') {
      opciones.tiempo = 5000;
    }
    if (typeof opciones.ocultar == 'undefined') {
      opciones.ocultar = true;
    }
    clearTimeout(_mostrar_alerta);
    var _alerta = $('#alerta-general');
    _alerta.removeClass('alerta--error').removeClass('alerta--aviso').removeClass('alerta--exito').removeClass('alerta--info');
    _alerta.addClass("alerta--".concat(opciones.tipo, "\r alerta--fixed-").concat(opciones.posicion)).html(opciones.texto);
    _alerta.slideDown(200);
    if (opciones.ocultar) {
      _mostrar_alerta = setTimeout(function() {
        _alerta.slideUp(200);
      }, opciones.tiempo);
    }
  } else {
    console.error("El parametro debe ser del tipo objeto. \n{\n  texto:     'xxxxxx(string)',\n  tipo:      'error, exito, info, aviso',\n  posicion:  'top o bottom',\n  duracion:  '1234(numero)'\n}");
  }
};


var calendario = function calendario() {
	$.datepicker.regional['es'] = {
    closeText: 'Cerrar',
    prevText: '< Ant',
    nextText: 'Sig >',
    currentText: 'Hoy',
    monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
    dayNames: ['Domingo', 'Lunes', 'Martes', 'Mi\u00E9rcoles', 'Jueves', 'Viernes', 'S\u00E1bado'],
    dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mi\u00E9', 'Juv', 'Vie', 'S\u00E1b'],
    dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'S\u00E1'],
    weekHeader: 'Sm',
    dateFormat: 'dd/mm/yy',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: ''
	};
	$.datepicker.setDefaults($.datepicker.regional['es']);
	$('.calendario').each(function(i, el) {
    var calendario = $(el);
    var rangoMax = $(el).data('max');
    var rangoMin = $(el).data('min');
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1;
    var yyyy = today.getFullYear();
	if (dd < 10) {
		dd = '0' + dd;
	}
	if (mm < 10) {
		mm = '0' + mm;
	}
	today = dd + '/' + mm + '/' + yyyy;
	if (calendario.hasClass('hoy')) {
		calendario.val(today);
	}
	$(el).datepicker({
		changeYear: true,
		minDate: rangoMin,
		maxDate: rangoMax,
		yearRange: "-100:+0",
		nextText: '',
		prevText: '',
		// nextText: '<span class="fln-derecha"></span>',
		// prevText: '<span class="fln-izquierda"></span>',
		onChangeMonthYear: function onChangeMonthYear() {
		  botonesCalendario();
		},
		showButtonPanel: true,
		beforeShow: function beforeShow() {
		  botonesCalendario();
      setTimeout(() => {
        $('.ui-icon-circle-triangle-w').addClass('fln-izquierda');
        $('.ui-icon-circle-triangle-e').addClass('fln-derecha');
      }, 200);
      setTimeout(function() {
        $('.ui-datepicker').css('z-index', 999);
      }, 0);
      },
		onSelect: function onSelect(e) {
      $(el).val(e);
      setTimeout(function() {
        $(el).blur().change();
      }, 100);
      el.value = e;
		},
		onUpdateDatepicker: function onUpdateDatepicker(e) {
			setTimeout(function() {
				$(el).blur().change();
				$('.ui-icon-circle-triangle-w').addClass('fln-izquierda');
				$('.ui-icon-circle-triangle-e').addClass('fln-derecha');
			}, 10);
		}
	});

	
	function botonesCalendario() {
		setTimeout(function() {
      // $('.ui-datepicker-buttonpane button').show().addClass('minilink');
      $('.ui-datepicker-buttonpane button:eq(0)').hide();
      // $('.ui-datepicker-buttonpane button:eq(0)').addClass('minilink minilink--secundario');
      // $('.ui-datepicker-buttonpane button:eq(1)').addClass('btn--primario btn--link');
		}, 1);
	}
	});
};
calendario();


function validarPhone() {
  setTimeout(function() {
      var _cel = $("#celular").val();
      var _tel = $("#telefono").val();      
      if (_cel || _tel) {
          if (_cel.length == 12 || _tel.length == 12) {
              aplicarClases($("#celular"), "valido");
              aplicarClases($("#telefono"), "valido");
          } else {
              aplicarClases($("#celular"), "invalido");
              aplicarClases($("#telefono"), "invalido");
          }
      } else {
          aplicarClases($("#celular"), "");
          aplicarClases($("#telefono"), "");
      }
      if (_cel && _tel) {
          if (_tel.length < 12) {
              $("#telefono").removeClass("valido");
              $("#celular").removeClass("valido");
              aplicarClases($("#telefono"), "invalido");
              $("#errores_telefono").slideDown();
              setTimeout(function() {
              $("#errores_telefono").slideUp();
              }, 5000);
          }
          if (_cel.length < 12) {
              $("#celular").removeClass("valido");
              $("#telefono").removeClass("valido");
              aplicarClases($("#celular"), "invalido");
              $("#errores_celular").slideDown();
              setTimeout(function() {
              $("#errores_celular").slideUp();
              }, 5000);
          }
      }
      if ($('#telefono').val() == ''){
          $('#telefono').siblings('label').removeClass('focus');
      }
      if ($('#celular').val() == ''){
          $('#celular').siblings('label').removeClass('focus');
      }
  }, 10);
}


function addCommas(nStr) {
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + '.' + '$2');
	}
	return x1 + x2;
}


var minLength = function minLength() {
  var _minLength = document.querySelectorAll('[data-minlength]');
  if (_minLength.length) {
    var _a2 = _minLength;
    var _f2 = function _f2(el, i) {
      el.addEventListener('blur', function(event) {
        setTimeout(function() {
          if (el.value.length >= Number(el.getAttribute('data-minlength'))) {
            el.classList.remove('invalido');
            el.classList.add('valido');
          } else {
            el.classList.add('invalido');
            el.classList.remove('valido');
          }
        }, 1);
      });
    };
    for (var _i2 = 0; _i2 < _a2.length; _i2++) {
      _f2(_a2[_i2], _i2, _a2);
    }
    undefined;
  }
};
minLength();


function validaLargoSerie() {
	var primer = $("#serie").val().substring(0, 1);
	if (primer == 'A') {
		$('#serie').attr('maxlength', 10);
	} else {
		$("#serie").attr('maxlength', 9);
	}
}


function validarFormatoSerie() {
	var _camp = $("#serie").get(0);
	var _val = $("#serie").val();
	var _alpha = /^[aA]{1}[0-9]{8,9}/.test(_val);
	var _number = /^[0-9]{8,9}/.test(_val);
	if (_alpha || _number) {
		_camp.classList.remove('invalido');
		_camp.classList.add('valido');
		return true;
	} else {
		_camp.classList.add('invalido');
		_camp.classList.remove('valido');
		return false;
	}
}


var validarSerie = function validarSerie() {
	var _inputs = document.querySelectorAll('.tipo_serie');
	if (_inputs.length) {
		var _clearValidar;
		var _a = _inputs;
		var _f = function _f(el, i) {
			clearTimeout(_clearValidar);
			_clearValidar = setTimeout(function() {
				multipleEventsListeners(el, 'blur paste', function(event) {
					var _val = el.value;
					var _alpha = /^[aA]{1}[0-9]{8,9}/.test(_val);
					var _number = /^[0-9]{8,9}/.test(_val);
					if (_alpha || _number) {
						if (_alpha) {
							el.setAttribute('maxlength', 10);
						} else {
							el.setAttribute('maxlength', 9);
						}
						el.classList.remove('invalido');
						el.classList.add('valido');
					} else {
						el.classList.add('invalido');
						el.classList.remove('valido');
					}
				});
			}, 100);
		};
		for (var _i = 0; _i < _a.length; _i++) {
			_f(_a[_i], _i, _a);
		}
		undefined;
	}
};
validarSerie();


// Slider de montos 
var montoMaximo = $('#dataMontoMaximo').data("montomaximo");
var montoMaximoLabel = $('#dataMontoMaximoLabel').data("montomaximolabel");
$(function() {
  $("#monto-valor").slider({
    range: "max",
    min: 40000,
    max: parseInt(montoMaximo),
    value: 20000,
    step: 10000,
    slide: function( event, ui ) {
      $("#valor").val(addCommas(ui.value));
      $(ui.value).val($('#valor').val());
    }
  });
});
$("#valor").on('change', function(event) {
  $("#monto-valor").slider("value" , parseInt($(this).val()));
  setTimeout(() => {
    $("#valor").val(addCommas($(this).val()));
  }, 10);
  // console.log(/^[0-9]+(?:\.[0-9]+)?$/.test($("#valor").val()) ? "Yes" : "No");

  if ($('#valor').val() >= (parseInt(montoMaximo)+1)) {
    $(this).val(parseInt(montoMaximo));
    $('#less-more').slideDown();
    $('#less-more').text('M\u00E1ximo de valor para cr\u00E9dito es $' + montoMaximoLabel);
    setTimeout(() => {
      $('#less-more').slideUp();
    }, 6000);
  }

  if ($('#valor').val() <= 40001) {
    $(this).val(40000);
    $('#less-more').slideDown();
    $('#less-more').text('M\u00EDnimo de valor para cr\u00E9dito es 40.000');
    setTimeout(() => {
      $('#less-more').slideUp();
    }, 6000);
  }
});
$('#valor').on('focus', function(event) {
  var valor = $("#valor").val();
  $(this).val(valor.replace(/(\d)[\s.]+(?=\d)/g, '$1'));
  $('#monto-valor').slider('value' , parseInt($(this).val()));
});
$('#valor').keypress(function(e){
  if(e.which == 13){
    var valor = $("#valor").val();
    $(this).val(valor.replace(/(\d)[\s.]+(?=\d)/g, '$1'));
    $("#monto-valor").slider("value" , parseInt($(this).val()));
    $(this).blur();    
  }
});


// Slider de cuotas 
$(function() {
  $('#numero-cuotas').slider({
    range: "max",
    min: 03,
    max: 60,
    value: 03,
    slide: function( event, ui ) {
      $('#monto').val(ui.value);
      $("#monto").val(ui.value);
      $(ui.value).val($('#numero-cuotas').val());
    }
  });
  $('#monto').val($('#numero-cuotas').slider('value'));
});
$("#monto").change(function() {
  $("#numero-cuotas").slider("value" , $(this).val());

  if ($('#monto').val() >= 61) {
    $(this).val(60);
    $('#min-max').slideDown();
    $('#min-max').text('M\u00E1ximo de cuotas es 60');
    setTimeout(() => {
      $('#min-max').slideUp();
    }, 6000);
  }
  if ($('#monto').val() <= 2) {
    $(this).val(3);
    $('#min-max').slideDown();
    $('#min-max').text('M\u00EDnimo de cuotas es 3');
    setTimeout(() => {
      $('#min-max').slideUp();
    }, 6000);
  }
});
// ---------------

if ($('.tippy').length) {
  tippy(".tippy", { 
    allowHTML: !0,
    // trigger: 'click'
  });
}

$('.detalle-simulacion').on('click', function (){
  $('#detalle-credito').modal('show');
  return false;
});

$('.link-hidden').on('click', function (){
  $('#validacion-incorrecta').modal('show');
  return false;
});

setTimeout(() => {
  $('.cerrar-detalle-simulacion').on('click', function (){
    $('#detalle-credito').modal('hide');
  });

  $('.cerrar-validacion-incorrecta').on('click', function (){
    $('#validacion-incorrecta').modal('hide');
  });
}, 200);

$('#seguro-cesantia').on("click", function() {
  if (this.checked) {
      this.setAttribute("checked", "checked");
      $('.modulo-check').removeClass('activo');
  } else {
      this.removeAttribute("checked");
      $('.modulo-check').addClass('activo');
  }
});

$('.modulo-check-volver').on('click', function(){
  $('.modulo-check').removeClass('activo');
});

if($('#validacion-identidad').length){
  $('input[type="radio"]').change(function(){
      var padre =  $(this).parent();
      if(padre.hasClass('form__radio--checked')){
          setTimeout( function() {
              padre.parent().parent().addClass('check');
              padre.parent().parent().removeClass('invalido');
          }, 100);
      }
  });
}

$('#autorizar-check').on('click', function (){
  $('#autorizar-check').parent().find('.fln-check').removeClass('invalido');
});

$('#nacimiento').on('click', function(){
  $(this).parent().find('label').addClass('focus');
  if(!$(this).val() == '' ){
    $(this).siblings('label').addClass('focus');
  }
});

$('#nacimiento').on('change', function(){
  $(this).parent().find('label').addClass('focus');
  if(!$(this).val() == '' ){
    $(this).siblings('label').addClass('focus');
  }
});

$('#nacimiento').on('focus', function(){
  $(this).parent().find('label').addClass('focus');
  if(!$(this).val() == '' ){
    $(this).siblings('label').addClass('focus');
  }
});

$('#nacimiento').on('blur', function(){
  if ($(this).parent().find('label').hasClass('focus')) {
    $(this).parent().find('label').removeClass('focus');
  }
});

$('.form__radio').click(function () {
  $('.form__radio').find('label').removeClass('selected');
  $(this).find('label').addClass('selected');
});

$('.form__radio input:checked').parent().addClass('selected');

$('.form__checkbox [type=checkbox]').on('change', function(){
  $(this).removeClass('invalido').addClass('valido');
  $(this).parent().find('.form__checkbox__item').removeClass('invalido');
});

$('#botonCerrarModalContactar').on('click', function(){
	$('#modal-contactanos').fadeOut();
	$('body').removeClass('stop-scrolling');
});

$('#botonCerrarModalMayor50UF').on('click', function(){
	$('#modal-mayor50uf').fadeOut();
	$('body').removeClass('stop-scrolling');
});

$('#botonCerrarModalMotorAISError').on('click', function(){
	$('#modal-motorAISError').fadeOut();
	$('body').removeClass('stop-scrolling');
});

var validarLogin = function validarLogin() {
	var valrut = RUT.validar($('#rut-credito-social').val());
  
    validarFormulario('form-rut-credito-social', {
    	texto: 'Debes ingresar ',
    	otrosCampos: {
    		'Rut Valido': [valrut, $('#form-rut-credito-social').find('.rut-credito-social')],
    	},
    	exito: function exito() {
    		fln.preloader(1);
    		//validaAfiliadoLogin();
    		$('#form-rut-credito-social').submit();
    	}
    });
};
/*
var validaAfiliadoLogin = function validaAfiliadoLogin(){
	var formData = {
		"celularFormulario" : $('#celular-login').val(),
		"rutAfiliadoFormulario" : $('#rut-credito-social').val(),
		"serieFormulario" : $('#serie').val(),
		"captchaFormulario" : $('#g-recaptcha-response').val()
	}
	
	$.ajax({
		type: 'POST',
		url: 'validaAfiliado.do',
		data: JSON.stringify(formData),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		success: function (data) {	
			var tipoRespuesta = data.codigoRespuesta;
			var descRespuesta = data.descRespuesta;
			
			if(tipoRespuesta == 1){
				window.location.href=descRespuesta;
			} else {
				$("#errores_form-rut-credito-social").text(descRespuesta);
				$("#errores_form-rut-credito-social").show();
				setTimeout(function() {
					$("#errores_form-rut-credito-social").hide();
				}, 5000);
				
			}
		},
		error: function(jqXHR) {
			alert('Error: ' + jqXHR.status);
		}
	})
}
*/
var validarPaso1 = function validarPaso1() {
  var seleccionado = false;
  if ($('#sucursal').is('.valido')) {
    seleccionado = true;
  } else {
    $('#sucursal').addClass('invalido');
  }
  validarFormulario('paso1', {
    texto: 'Debes ',
    otrosCampos: { 
      "seleccionar una sucursal": [seleccionado, $("#paso1").find('.sucursal')],
    }, 
    exito: function exito() {
      //paso1 = $('#paso1').serialize(); 

      $('#procesando-credito').fadeIn();
      $('body').addClass('stop-scrolling');
      $(window).scrollTop(0);
      setTimeout(() => {
        //window.location.href="paso2.do";
        $('#paso1').submit();
      }, 1000);

    }    
  });
};


var validarPaso2 = function validarPaso2() {
	var autorizar= "0";
	if (!$('#autorizar').is(':checked')) {
	    setTimeout(() => {
	      $('#autorizar').parent().find('.form__checkbox__item').addClass('invalido');
	    }, 200);
	} else {
		$('#autorizar-check').parent().find('.form__checkbox__item').removeClass('invalido');
		autorizar="1";
	}	
	
	var dobleAfiliacion = $('#dataDobleAfiliacion').data("dobleafiliacion");
	var relacionLaboral = $('#dataRelacionLaboral').data("relacionlaboral");	
	
	validarFormulario('paso2', {
	    texto: 'Debes confirmar ',
	    exito: function exito() {
	        paso2 = $('#paso2').serialize(); 
	        $('#textoMayor50uf').empty();
	      
	        $('#evaluando-credito').fadeIn();
			$('body').addClass('stop-scrolling');
			$(window).scrollTop(0);
			$.ajax({
				type: 'POST',
				url: 'ofertasVigentes.do',
				success: function (data) {	
					var id_estado_solicitud = data.ID_ESTADO_ACTUAL;
					if(id_estado_solicitud == "E0019" || id_estado_solicitud == "E0021" || id_estado_solicitud == "E0045" || id_estado_solicitud == "E0064" || id_estado_solicitud == "E0099" || id_estado_solicitud == "E0174"){
						window.location.href="ofertaEnCurso.do";
					} else {
						if(dobleAfiliacion == true || relacionLaboral == true){
							setTimeout(() => {
								window.location.href="agenda.do?autorizar=" + autorizar + "&mens=1";
				  	        }, 1000);
				  	  	} else {
					  	  	$.ajax({
					  			type: 'POST',
					  			url: 'evaluarCredito.do',
					  			success: function (data) {
					  				var tipoRespuesta = data.tipoRespuesta;	  				  
					  				$('#evaluando-credito').fadeOut();
					  				$('body').removeClass('stop-scrolling');
					  				if(tipoRespuesta == "1"){
					  					// 1 = Mayor a 50 UF
					  					$('#textoMayor50uf').append(data.textoRespuesta);
					  					$('#modal-mayor50uf').fadeIn();
					  					$('body').addClass('stop-scrolling');
					  					$(window).scrollTop(0);
					  				} else if(tipoRespuesta == "2") {
					  					// 2 = Propuesta aprobada
					  					$('#oferta-aprobada').fadeIn();
					  					$('body').addClass('stop-scrolling');
					  					$(window).scrollTop(0);
					  				} else if(tipoRespuesta == "3"){
					  					// 3 = Propuesta aprobada con 3 opciones
					  					window.location.href="aprobadoConOpciones.do";
					  				} else if(tipoRespuesta == "4"){
					  					// 4 = Propuesta Rechazada
					  					window.location.href="creditoRechazado.do";
					  				} else if(tipoRespuesta == "5"){
					  					// 5 = Derivado
					  					window.location.href="agenda.do?autorizar=" + autorizar + "&mens=1";
					  				} else if (tipoRespuesta == "6") {
					  					// 6 = Supera veces renta
					  					window.location.href="agenda.do?autorizar=" + autorizar + "&mens=1";
					  				} else if (tipoRespuesta == "7") {
					  					// 7 = Supera max endeudamiento normativo
					  					window.location.href="agenda.do?autorizar=" + autorizar + "&mens=1";
					  				} else if (tipoRespuesta == "8"){
					  					// 8 = Afiliado sin renta cotizada
					  					window.location.href="agenda.do?autorizar=" + autorizar + "&mens=1";
					  				} else if (tipoRespuesta == "0"){
					  					// 0 = Alerta
					  					window.location.href="agenda.do?autorizar=" + autorizar + "&mens=1";
					  				} else if(tipoRespuesta == "-1"){
					  					// -1 = Servicio no responde
					  					window.location.href="paginaEnDesarrollo.do";
					  				} else if(tipoRespuesta == "-2"){
					  					// -2 = Error AIS
					  					$('#textoMotorAISError').append(data.textoRespuesta);
					  					$('#modal-motorAISError').fadeIn();
					  					$('body').addClass('stop-scrolling');
					  					$(window).scrollTop(0);
					  				}					  
					  			},
					  			error: function(jqXHR) {
					  				alert('Error: ' + jqXHR.status);
					  			}
					  		});
				  	  	}
					}
				},
				error: function(jqXHR) {
					alert('Error: ' + jqXHR.status);
				}
			});
			
			
	      
	      
	    }
	});
};

var validarPaso2Agenda = function validarPaso2Agenda() {
	var autorizar= "0";
	  if (!$('#autorizar').is(':checked')) {
	    setTimeout(() => {
	      $('#autorizar').parent().find('.form__checkbox__item').addClass('invalido');
	    }, 200);
	  } else {
	    $('#autorizar-check').parent().find('.form__checkbox__item').removeClass('invalido');
	    autorizar="1";
	  }
	  
	  
	  validarFormulario('paso2', {
	    texto: 'Debes confirmar ',
	    exito: function exito() {
	      paso2 = $('#paso2').serialize(); 

	      $('#procesando-credito').fadeIn();
	      $('body').addClass('stop-scrolling');
	      $(window).scrollTop(0);
	      setTimeout(() => {
	        window.location.href="agenda.do?autorizar=" + autorizar + "&mens=1";
	      }, 1000);
	    }
	  });
	};

	
var validarPaso2Contacto = function validarPaso2Contacto() {
	var autorizar= "0";
	if (!$('#autorizar').is(':checked')) {
		setTimeout(() => {
			$('#autorizar').parent().find('.form__checkbox__item').addClass('invalido');
		}, 200);
	} else {
		$('#autorizar-check').parent().find('.form__checkbox__item').removeClass('invalido');
		 aceptar="1";
	}
	
	
	validarFormulario('paso2', {
		texto: 'Debes confirmar ',
		exito: function exito() {
			paso2 = $('#paso2').serialize(); 

			$('#procesando-credito').fadeIn();
			$('body').addClass('stop-scrolling');
			$(window).scrollTop(0);
			setTimeout(() => {
				window.location.href="contacto.do?autorizar=" + autorizar;
			}, 1000);
		}
	});
};

var paso2Contacto = function paso2Contacto() {
	$('#buscando-ejecutivo').fadeIn();
	$('body').addClass('stop-scrolling');
	$(window).scrollTop(0);
	$('#texto1').empty();
	$('#texto2').empty();
	
	var valores = window.location.search;
	var urlParams = new URLSearchParams(valores);
	var tipoMensaje = urlParams.get('mens');
	
	$.ajax({
        type: 'POST',
        url: 'contactoPopup.do',
        success: function (data) {
        	var tipoRespuesta = data.tipoRespuesta;
        	$('#buscando-ejecutivo').fadeOut();
        	$('body').removeClass('stop-scrolling');
        	if(tipoRespuesta == "1" & tipoMensaje == "2"){
        		
        		window.location.href="agendaFinalContactar.do";
        		
        	} else if (tipoRespuesta == "1" & tipoMensaje == "1") {
        		$('#texto1').append(data.texto);
        		$('#texto2').append("Por favor debes estar atento a tu celular");
        		$('#modal-contactanos').fadeIn();
        		$('body').addClass('stop-scrolling');
        		$(window).scrollTop(0);
            	/*
            	setTimeout( function() {
            		$('#modal-contactanos').fadeOut();
                }, 10000);
            	window.location.href="paso2.do";
            	*/
        	} else {
        		$('#texto1').append(data.texto);
        		$('#modal-contactanos').fadeIn();
        		$('body').addClass('stop-scrolling');
        		$(window).scrollTop(0);
            	setTimeout( function() {
            		$('#modal-contactanos').fadeOut();
            		$('body').removeClass('stop-scrolling');
                }, 10000);
        	}
        	
        
        },
        error: function(jqXHR) {
            alert('Error: ' + jqXHR.status);
        }
    });
    
    
};


var validarOpcionUno = function validarOpcionUno() {
  validarFormulario('opcion-1', {
    texto: 'Debe ingresar ',
    exito: function exito() {
      paso1 = $('#opcion-1').serialize(); 

      $('#procesando-credito').fadeIn();
      $('body').addClass('stop-scrolling');
      $(window).scrollTop(0);
      setTimeout(() => {
        window.location.href="/validacion-identidad.html";
      }, 1000);

    }
  });
};


var validarOpcionDos = function validarOpcionDos() {
  validarFormulario('opcion-2', {
    texto: 'Debe ingresar ',
    exito: function exito() {
      paso2 = $('#opcion-2').serialize(); 

      $('#procesando-credito').fadeIn();
      $('body').addClass('stop-scrolling');
      $(window).scrollTop(0);
      setTimeout(() => {
        window.location.href="/validacion-identidad.html";
      }, 1000);

    }
  });
};


var validarOpcionTres = function validarOpcionTres() {
  validarFormulario('opcion-3', {
    texto: 'Debe ingresar ',
    exito: function exito() {
      paso3 = $('#opcion-3').serialize(); 

      $('#procesando-credito').fadeIn();
      $('body').addClass('stop-scrolling');
      $(window).scrollTop(0);
      setTimeout(() => {
        window.location.href="/validacion-identidad.html";
      }, 1000);

    }
  });
};


var validacionId = function validacionId() {
  var checks = $('input[type="radio"]:checked').length === $('input[type="radio"]').length/4 ? true : false;
  // console.log(checks);
  // console.log($('input[type="radio"]').length/4);
  validarFormulario('validacion-identidad', {
      texto: 'Debe responder ',
      otrosCampos: { 
        "todas las preguntas para continuar": [checks, $(".form__radio-box").not('.check')],
      },
      exito: function exito() {
    	  paso3 = $('#paso3').serialize(); 

          $('#procesando-credito').fadeIn();
          
          setTimeout(() => {
            window.location.href="paso4.do";
          }, 1000);

      }
  });
};


var confirmarIdentidad = function confirmarIdentidad() {
  var autorizacion = false;
  if (!$('#confirmar-id').is(':checked')) {
    setTimeout(() => {
      $('#confirmar-id').parent().find('.form__checkbox__item').addClass('invalido');
    }, 200);
  } else {
    autorizacion = true;
    $('#confirmar-id').parent().find('.form__checkbox__item').removeClass('invalido');
  }
  validarFormulario('confirmar-identidad', {
    texto: 'Debe confirmar ',
    otrosCampos: { 
      "declaraci\u00FAn jurada de vigencia con empleador": [autorizacion, $("#confirmar-identidad").find('#confirmar-id')],
    },
    exito: function exito() {
      paso4 = $('#confirmar-identidad').serialize(); 

      $('#procesando-credito').fadeIn();
      setTimeout(() => {
        window.location.href="paso5.do";
      }, 1000);

    }
  });
};


var validacionDatos = function validacionDatos() {
  if (!$('#autorizar-check').is(':checked')) {
    setTimeout(() => {
      $('#autorizar-check').parent().find('.form__checkbox__item').addClass('invalido');
    }, 200);
  } else {
    $('#autorizar-check').parent().find('.form__checkbox__item').removeClass('invalido');
  }

  if ($('#nacimiento').hasClass('valido')) {
    setTimeout(() => {
      $('#nacimiento').parent().find('label').addClass('focus');
    }, 200);
  }
  var celular = $('#celular').val();
  var telefono = $('#telefono').val();
  var numeroValido = celular.length == 12 || telefono.length == 12? true:false;
  validarFormulario('validacion-datos', {
      texto: 'Debe ingresar ',
      otrosCampos: {
        'Celular o Tel\u00E9fono': [numeroValido, $('#validacion-datos').find('.tipo_telefono')],
      },
      exito: function exito() {
          $('#procesando-credito').fadeIn();
          setTimeout(() => {
            window.location.href="cursaCredito.do";
          }, 1000);
      }
  });
};


var validacionNumerica = function validacionNumerica() {
  var codigoMail = false;
  var codigoTelefono = false;
  $('.codigo-mail').each(function (){
    if ($(this).val().length > 0 && !$(this).val() == '') {
      codigoMail = true;
    } else {
      codigoMail = false;
      $('.form-modal__interior.grupo-mail').addClass('invalido');
    }
  });
  $('.codigo-telefono').each(function (){
    if ($(this).val().length > 0 && !$(this).val() == '') {
      codigoTelefono = true;
    } else {
      codigoTelefono = false;
      $('.form-modal__interior.grupo-telefono').addClass('invalido');
    }
  });
  $('#validacion-numerica .form-modal__interior.grupo-mail input').on('click', function (){
    $('.form-modal__interior.grupo-mail').removeClass('invalido');
  });
  $('#validacion-numerica .form-modal__interior.grupo-telefono input').on('click', function (){
    $('.form-modal__interior.grupo-telefono').removeClass('invalido');
  });
  var validarCodigoMail = codigoMail ? true : false;
  var validarCodigoTelefono = codigoTelefono ? true : false;
  validarFormulario('validacion-numerica', {
    texto: 'Debe ingresar la clave de seguridad enviada a su ',
    otrosCampos: { 
      'email': [validarCodigoMail,$('#validacion-numerica').find('.codigo-mail')],
      'celular': [validarCodigoTelefono,$('#validacion-numerica').find('.codigo-telefono')],
    },
    exito: function exito() {
        $.ajax({
        method: 'POST',
        url: 'ajax/validacion-numerica.php',
        data: $('#validacion-numerica').serialize(),
        beforeSend: function beforeSend() {
          $('#procesando-credito').fadeIn();
        },
        success: function success(data) {
            var respuesta = jQuery.parseJSON(data);
            if (respuesta.status == 'exito') {
              setTimeout(() => {
                window.location.href="/validacion-exitosa.html";
              }, 1000);
            } else {
              setTimeout(() => {
                window.location.href="/validacion-erronea.html";
              }, 1000);
            }
          $('#procesando-credito').fadeOut();
          $('body').removeClass('stop-scrolling');
        }
        });
    }
});
};


var agendarCita = function agendarCita() {
  validarFormulario('agendar-cita', {
      texto: 'Debe ingresar ',
      exito: function exito() {
          $.ajax({
          method: 'POST',
          url: 'ajax/agendar-cita.php',
          data: $('#agendar-cita').serialize(),
          beforeSend: function beforeSend() {
            $('#procesando-credito').fadeIn();
            $('body').addClass('stop-scrolling');
            $(window).scrollTop(0);
          },
          success: function success(data) {
              var respuesta = jQuery.parseJSON(data);
              if (respuesta.status == 'exito') {
                $('.respuesta-negativa').hide();
                $('.agendar-exito').slideDown();
              } else {
                $('.link-hidden').trigger('click');
              }
              $('#procesando-credito').fadeOut();
              $('body').removeClass('stop-scrolling');
          }
          });
      }
  });
};


$('.codigo-mail').keyup(function () {
  if (this.value.length == this.maxLength) {
    $(this).parent().next('.form-modal__grupo').find('.codigo-mail').focus();
  }
});

$('.codigo-telefono').keyup(function () {
  if (this.value.length == this.maxLength) {
    $(this).parent().next('.form-modal__grupo').find('.codigo-telefono').focus();
  }
});

$('.codigo-mail').on('input', function (){
  this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');
});

$('.codigo-telefono').on('input', function (){
  this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');
});

var RUT = {
  selector: ".rut",
  keys: [8, 9, 46, 16, 37, 39],
  limpiar: function limpiar(rut) {
      return rut.replace(/^0+|[^0-9kK]+/g, "").toUpperCase();
  },
  formatear: function formatear(rut) {
      rut = this.limpiar(rut);
      var result = rut.slice(-4, -1) + "-" + rut.substr(rut.length - 1);
      for (var i = 4; i < rut.length; i += 3) {
      result = rut.slice(-3 - i, -i) + "." + result;
      }
      return result;
  },
  validar: function validar(rut) {
      if (typeof rut !== "string") {
      return false;
      }
      if (!/^0*(\d{1,3}(\.?\d{3})*)-?([\dkK])$/.test(rut)) {
      return false;
      }
      rut = this.limpiar(rut);
      var t = parseInt(rut.slice(0, -1), 10);
      var m = 0;
      var s = 1;
      while (t > 0) {
      s = (s + t % 10 * (9 - m++ % 6)) % 11;
      t = Math.floor(t / 10);
      }
      var v = s > 0 ? "" + (s - 1) : "K";
      return v === rut.slice(-1);
  },
  aplicar: function aplicar() {
      var _this = this;
      disableKeydownRepeat(this.selector);
      this.selector = document.querySelectorAll(this.selector);
      var _iteratorNormalCompletion2 = true;
      var _didIteratorError2 = false;
      var _iteratorError2 = undefined;
      try {
      var _loop = function _loop() {
          var input = _step2.value;
          input.setAttribute("maxlength", 12);
          input.addEventListener("keydown", function(event) {
          // console.log(event.keyCode);
          if (
              !/[kK0-9\-]/.test(event.key) &&
              _this.keys.indexOf(event.keyCode) < 0
          ) {
              event.preventDefault();
          }
          _this.limpiar(input.value);
          });
          input.addEventListener("keyup", function(event) {
          input.value =
              input.value.length > 1 ? _this.formatear(input.value) : input.value;
          });
          input.addEventListener("blur", function(event) {
          setTimeout(function() {
              if (_this.validar(input.value)) {
              input.classList.remove("invalido");
              input.classList.add("valido");
              } else {
              input.classList.remove("valido");
              input.classList.add("invalido");
              }
          }, 10);
          });
      };
      for (
          var _iterator2 = this.selector[Symbol.iterator](), _step2;
          !(_iteratorNormalCompletion2 = (_step2 = _iterator2.next()).done);
          _iteratorNormalCompletion2 = true
      ) {
          _loop();
      }
      } catch (err) {
      _didIteratorError2 = true;
      _iteratorError2 = err;
      } finally {
      try {
          if (!_iteratorNormalCompletion2 && _iterator2.return != null) {
          _iterator2.return();
          }
      } finally {
          if (_didIteratorError2) {
          throw _iteratorError2;
          }
      }
      }
  },
};
RUT.aplicar();

/**** JS para login *****/
function Validate(event) {
  var regex = new RegExp("^[0-9-!@#$%&*?]");
  var key = String.fromCharCode(event.charCode ? event.which : event.charCode);
  if (!regex.test(key)) {
      event.preventDefault();
      return false;
  }
} 

function formatearCel(event) {
  if ($('#celular-login').val() == '') {
    $('#celular-login').val("+569");
    $('#celular-login').unbind();
  }
} 

function SimulaOtro() {
	 fln.preloader(1);
	 $('#paso2').submit();

}

$('#rut-credito-social').on('focusout', function(){
  var rutMax = parseInt($("#rut-credito-social").val().split('.').join('').split('-').join('').slice(0, -1));
  var rutValido = $("#rut-credito-social").val().length <= 12 && $("#rut-credito-social").val().length >= 7 && rutMax < 50000000;
  
  if(rutValido){
      $("#rut-credito-social").addClass('valido');
  }else if ($("#rut-credito-social").val().length === 0 && $("#rut-credito-social").val() === '') {
      $("#rut-credito-social").removeClass('invalido').val('');
      $('#error-rut-credito-social').text('Debes ingresar un RUT.');
      $('#error-rut-credito-social').slideDown('fast');
      setTimeout(function(){
          $('#error-rut-credito-social').slideUp('fast'); 
      }, 4000);
  } else {
      setTimeout(function(){
          $("#rut-credito-social").removeClass('valido');
          $("#rut-credito-social").addClass('invalido');
          $('#error-rut-credito-social').text('Debes ingresar un RUT v\u00E1lido.');
          $('#error-rut-credito-social').slideDown('fast');
          setTimeout(function(){
              $('#error-rut-credito-social').slideUp('fast'); 
          }, 4000);        
      },300);
  }
});

var validarAgenda = function validarAgenda() {
	  var dia = $('#diaAgenda').val();
	  var diaAgenda = false;
	  var horario = false;
	  const radios = document.getElementsByName('horario');
	  for (var i = 0; i <  radios.length; i++) {
	    if (radios[i].checked) {
	    	horario = true;
	      break;
	    }
	  }
	  if(dia!='') { diaAgenda=true }
	  validarFormulario('form-agenda', {
	      texto: 'Debes ingresar ',
	      otrosCampos: {
	        'D&iacute;a': [diaAgenda, $('#form-agenda').find('.diaAgenda')],
	        'Rango de horario': [horario, $('#form-agenda').find('.horario')],
	      },
	      exito: function exito() {
	    	  fln.preloader(1);
	    	  $('#procesando-credito').fadeIn();
	    	  $('body').addClass('stop-scrolling');
	    	  $(window).scrollTop(0);
	          setTimeout(() => {
	        	  $('#form-agenda').submit();
	          }, 1000);
		    	
	      }
	  });
	};
	
$('.cerrar-modal-contactanos').on('click', function (){
	$('#detalle-credito').modal('hide');
});

$('.rechazarOfertaAIS').on('click', function (){
	window.location.href="rechazarOferta.do";
});

/*
$('.aceptarOfertaAIS').on('click', function (){
	window.location.href="aceptarOferta.do";
});
*/

var validarCamposFormCrearOferta = function validarCamposFormCrearOferta() {	
	validarFormulario('validacion-datos', {
		texto: 'Debes ingresar ',
		exito: function exito() {
			fln.preloader(1);
			crearOfertaWS();
		}
	});	
	$('#labelNacimiento').addClass('focus');
	
}

var crearOfertaWS = function crearOfertaWS() {	
	var formData = {
		"fechaNacimientoFormulario" : $('#nacimiento').val(),
		"estadoCivilFormulario" : $('#estado-civil').val(),
		"tipoContratoFormulario" : $('#tipo-de-contrato').val(),
		"celularFormulario" : $('#celular').val(),
		"emailFormulario" : $('#email').val(),
		"telefonoFormulario": $('#telefono').val(),
		"direccionFormulario": $('#direccion').val(),
		"numeroDireccionFormulario": $('#numero').val(),
		"villaPoblacionFormulario": $('#poblacion-o-villa').val(),
		"regionFormulario": $('#region').val(),
		"comunaFormulario": $('#comuna').val()
	}
	
	$('#evaluando-credito').fadeIn();
	$('body').addClass('stop-scrolling');
	$(window).scrollTop(0);
	urlPadre('centrarScroll');
	$.ajax({
		type: 'POST',
		url: 'crearOferta.do',
		data: JSON.stringify(formData),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		success: function (data) {			
			var codigoRespuesta = data.Estado_Aprob_Cliente;
			var mensajeRespuesta = data.Mensaje;
			if(codigoRespuesta == "1"){
				window.location.href="agenda.do?autorizar=1&mens=2";
				$('#evaluando-credito').fadeOut();
				$('body').removeClass('stop-scrolling');
			} else {
				
			}
			
		},
		error: function(jqXHR) {
			alert('Error: ' + jqXHR.status);
		}
	});
		
};

var obtenerOpcionSeleccionada = function obtenerOpcionSeleccionada(opcionSeleccionada) {	
	$('#simulando-credito').fadeIn();
	$('body').addClass('stop-scrolling');
	$(window).scrollTop(0);
	urlPadre('centrarScroll');
	var formData;
	
	if(opcionSeleccionada == "1"){
		let opcionMonto1 = $('#dataOpcionMonto1').data("opcionmonto1");
		let opcionPlazo1 = $('#dataOpcionPlazo1').data("opcionplazo1");
		let montoCuota1  = $('#dataMontoCuota12').data("montocuota12");
		
		formData = {
			"opcionMonto" : opcionMonto1.toString(),
			"opcionPlazo" : opcionPlazo1.toString(),
			"montoCuota"  : montoCuota1.toString()
		}
	} else if (opcionSeleccionada == "2"){
		let opcionMonto2 = $('#dataOpcionMonto2').data("opcionmonto2");
		let opcionPlazo2 = $('#dataOpcionPlazo2').data("opcionplazo2");
		let montoCuota2  = $('#dataMontoCuota24').data("montocuota24");
		
		formData = {
			"opcionMonto" : opcionMonto2.toString(),
			"opcionPlazo" : opcionPlazo2.toString(),
			"montoCuota"  : montoCuota2.toString()
		}
	} else if (opcionSeleccionada == "3"){
		let opcionMonto3 = $('#dataOpcionMonto3').data("opcionmonto3");
		let opcionPlazo3 = $('#dataOpcionPlazo3').data("opcionplazo3");
		let montoCuota3  = $('#dataMontoCuota36').data("montocuota36");
		
		formData = {
			"opcionMonto" : opcionMonto3.toString(),
			"opcionPlazo" : opcionPlazo3.toString(),
			"montoCuota"  : montoCuota3.toString()
		}
	} 
	
	$.ajax({
        url: 'obtenerOpcionSeleccionada.do',
		type: 'POST',
		data: JSON.stringify(formData),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success: function () {	
			$('#formOpciones').submit();			
		},
		error: function(jqXHR) {
			alert('Error: ' + jqXHR.status);
		}
	});
		
};

function urlPadre(parametro){
    let message = { msj: parametro };
    window.top.postMessage(message, "*");
} 


