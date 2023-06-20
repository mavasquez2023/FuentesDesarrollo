"use strict";

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

$('#rut').Rut({
  on_error: function on_error() {
    $('.btn--primario').addClass('disabled');
    console.log('error');
    $('.alerta--rut').slideDown();
    setTimeout(function() {
      $('.alerta--rut').slideUp();
    }, 5000);
  },
  on_success: function on_success() {
    $('.btn--primario').removeClass('disabled');
  },
  format_on: 'keyup'
});

//Captcha Google
var recaptchaCallback = function recaptchaCallback(response) {
	$('.captcha').removeClass('invalido');
	$('.captcha').addClass('valido');
};

var validarPaso1 = function validarPaso1() {
	
	var captchaValido = grecaptcha.getResponse() ? true : false;
  validarFormulario('validar-rut', {
    texto: 'Debes ingresar ',
     otrosCampos: {
    	 'Captcha' : [ captchaValido, $('#validar-rut').find('.captcha') ],
     },
    exito: function exito() {
    	fln.preloader(1);
    	$('#validar-rut').submit();
    	
   /*   $.ajax({
        method: 'POST',
        url: '/SaldoaFavor/ajax/validar-rut.php',
        data: $('#validar-rut').serialize(),
        beforeSend: function beforeSend() {
          fln.preloader(1);
        },
        success: function success(data) {
          if (data === 'exito') {
        	  $('#validar-rut').submit();
          } else {
            window.location.href = '/init.do';
          }

          fln.preloader(0);
        }
      });
      */
    }
  });
}; // const reintentar = () => {
//   let respuestaError = $('.respuesta');
//   let form = $('#paso1');
//   respuestaError.slideUp();
//   form.slideDown();
//   $('.pasos__item--dos').removeClass('activo');
//   $('.pasos__item--uno').addClass('activo');
// };


var minLength = function minLength() {
  var _minLength = document.querySelectorAll('[data-minlength]');

  if (_minLength.length) {
    var _a = _minLength;

    var _f = function _f(el, i) {
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

    for (var _i = 0; _i < _a.length; _i++) {
      _f(_a[_i], _i, _a);
    }

    undefined;
  }
};

minLength();