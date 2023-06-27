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

// Captcha Google
var recaptchaCallback = function recaptchaCallback(response) {
  $('.captcha').removeClass('invalido');
  $('.captcha').addClass('valido');
};

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

var documentos = function documentos() {
  var _items = document.querySelectorAll('.documento__item');

  if (_items.length) {
    var _loop = function _loop(i) {
      var el = _items[i]; // const _item =
							// el.querySelector('.documentos__nombre__text');

      var _file = el.querySelector('input[type=file]'); // _file.setAttribute('name',
														// `documento-${i}`)
      // _file.setAttribute('id', `documento-${i}`)


      var _size = 500000;

      if (_file.getAttribute('data-maxsize')) {
        _size = parseInt(_file.getAttribute('data-maxsize')) * 1000;
      } // Formatos permitidos


      var _formatos = [];

      if (_file.getAttribute('data-formatos')) {
        var formato = _file.getAttribute('data-formatos').replace(/\s/g, '').split(',');

        _formatos.push(formato);
      }

      _file.addEventListener('change', function(event) {
        var _name = el.querySelector('.documento__nombre__text');

        if (event.target.files.length) {
          if (!_formatos.length || _formatos[0].includes(event.target.value.split('.').pop())) {
            if (event.target.files[0].size < _size) {
              var _ico = document.createElement('span');

              _name.innerText = resumir_textos(event.target.value.split('\\').pop(), 20, 'centro');

              var _tipo = el.querySelector('.documento__tipo');

              if (_tipo) {
                _tipo.remove();
              }

              _ico.classList.add('documento__tipo');

              el.appendChild(_ico);

              if (el.classList.contains('requerido')) {
                el.classList.remove('invalido');
                el.classList.add('valido');
              }
            } else {
              event.target.value = '';
              _name.innerText = 'Seleccionar archivo';

              if (el.classList.contains('requerido')) {
                el.classList.add('invalido');
                el.classList.remove('valido');
              }

              var _tamano = "".concat(_size / 1000000, "mb");

              alerta({
                texto: "El tama\xF1o supera el m\xE1ximo permitido \r(".concat(_tamano, ")"),
                posicion: 'bottom',
                tipo: 'aviso'
              });
              event.target.value = '';
              _name.innerText = 'Seleccionar archivo';
            }
          } else {
            alerta({
              texto: "El formato agregado no es el permitido, solo se aceptan \r(".concat(_formatos.toString().replace(', ', ', '), ")"),
              posicion: 'bottom',
              tipo: 'aviso'
            });
            event.target.value = '';
            _name.innerText = 'Adjuntar archivo (.pdf)';
          }
        }
      });
    };

    for (var i = 0; i < _items.length; i++) {
      _loop(i);
    }
  }
};

documentos();

var validarPaso1 = function validarPaso1() {
  var _apellidoP = false;

  if ($('#apellido-paterno').val()) {
    if ($('#apellido-paterno').val().length >= 3){ // =
													// $('#apellido-paterno').data('minlength'))
													// {
      _apellidoP = true;
    }
  } else {
    _apellidoP = true;
  }

  var _telefono = false;

  if ($('#telefono').val().length == $('#telefono').data('minlength')) {
    _telefono = true;
  }

  var _celular = false;

  if ($('#celular').val().length == $('#celular').data('minlength')) {
    _celular = true;
  }

  validarFormulario('paso1', {
    texto: 'Debe ingresar ',
    otrosCampos: {
     // 'Apellido Paterno': [_apellidoP, $('#apellido-paterno')],
      'Teléfono': [_telefono, $('#telefono')],
      'Celular': [_celular, $('#celular')]
    },
    exito: function exito() {
      /*
		 * Ingresar metodo para procesar el formulario aquí Puede ser:
		 */
        $('#paso1').submit();
      /*
		 * O un AJAX, depende de como lo integrarán;
		 */
      // alert('Exito');
    }
  });
};

var validarPaso2 = function validarPaso2() {
  var _telefono = false;

  if ($('#telefono').val()) {
    if ($('#telefono').val().length == $('#telefono').data('minlength')) {
      _telefono = true;
    }
  } else {
    _telefono = true;
  }

  validarFormulario('paso2', {
    texto: 'Debe ingresar ',
    otrosCampos: {
      'Teléfono Empresa': [_telefono, $('#telefono')]
    },
    exito: function exito() {
      /*
		 * Ingresar metodo para procesar el formulario aquí Puede ser:
		 */
        $('#paso2').submit();
       /*
		 * O un AJAX, depende de como lo integrarán;
		 */
     // alert('Exito');
    }
  });
};

$('#apellido-materno').addClass('valido-especial');

var validarPaso3 = function validarPaso3() {
  var _apellidoM = false;

  if ($('#apellido-materno').val()) {
    if ($('#apellido-materno').val().length >=3){ 
    		// == $('#apellido-materno').data('minlength')) {
      _apellidoM = true;
    }
  } else {
    _apellidoM = true;
  }

  var adjunto = false;

  if (!$('.documento__item.valido').length) {
    adjunto = true;
    $('#alerta-documento').slideDown('fast');
    setTimeout(function() {
      $('#alerta-documento').slideUp('fast');
    }, 5000);
  }
  
    
  var captchaValido = grecaptcha.getResponse() ? true : false; //poner false
  validarFormulario('paso3', {
    texto: 'Debe ingresar ',
    otrosCampos: {
      'Apellido Materno': [_apellidoM, $('#apellido-materno')],
      'Adjuntar archivo': [adjunto, $('.validar3')],
      'Captcha': [captchaValido, $('#paso3').find('.captcha')]
    },
    exito: function exito() {
    // var formData = new FormData();

     // for (var i = 0; i < $("input[type='file']").length; i++) {
     // if ($("input[type='file']").eq(i)[0].files[0]) {
      // formData.append('documento' + i,
		// $("input[type='file']").eq(i)[0].files[0]);
      // }
     // }

     // var datos = $('#form').serializeArray();
    // formData.append('datos', JSON.stringify(datos));
      /*
		 * Ingresar metodo para procesar el formulario aquí Puede ser:
		 */
        $('#paso3').submit();
       /*
		 * O un AJAX, depende de como lo integrarán;
		 */

     // alert('Exito');
    }
  });
};

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

var validarTelefono = function validarTelefono() {
  var _inputs = document.querySelectorAll('.tipo_fono');

  if (_inputs.length) {
    var _clearValidar;

    var _a2 = _inputs;

    var _f2 = function _f2(el, i) {
      var _dataPrefijo = el.getAttribute('data-prefijo');

      var _dataPrefijoTipo = el.getAttribute('data-prefijo-tipo');

      var _pattern;

      var _max;

      if (_dataPrefijoTipo === 'celular') {
        _pattern = /^[+569]{1}[0-9]{11,12}/;
        _max = 12;
      } else if (_dataPrefijoTipo === 'fijo') {
        _pattern = /^[+56]{1}[0-9]{10,12}/;
        _max = 12;
      }

      if (!el.getAttribute('maxlength')) {
        el.setAttribute('maxlength', _max);
      }

      multipleEventsListeners(el, 'keyup blur paste', function(event) {
        var _val = el.value;

        var _patron = _pattern.test(_val);

        clearTimeout(_clearValidar);
        _clearValidar = setTimeout(function() {
          if (_patron) {
            el.classList.remove('invalido');
            el.classList.add('valido');
          } else {
            el.classList.add('invalido');
            el.classList.remove('valido');
          }
        });
      });
      multipleEventsListeners(el, 'focus paste', function(event) {
        var _val = el.value;

        if (_dataPrefijo && !el.value) {
          el.value = _dataPrefijo;
        } else if (el.value) {
          el.value = _val;
        } else {
          el.value = '';
        }
      });
      el.addEventListener('blur', function(event) {
        var _val = el.value;

        if (_dataPrefijo && el.value === _dataPrefijo) {
          el.value = '';
          setTimeout(function() {
            $(el).blur();
          }, 10);
        } else {
          el.value = _val;
        }
      });
    };

    for (var _i2 = 0; _i2 < _a2.length; _i2++) {
      _f2(_a2[_i2], _i2, _a2);
    }

    undefined;
  }
};

validarTelefono();

function limpiar(el) {
  $(el).parents('.documento__item').removeClass('valido').find('input[type=file]').val('');
  $(el).parents('.documento__item').find('.documento__nombre__text').text('Adjuntar archivo (.pdf)');
  return false;
}

var minLength = function minLength() {
  var _minLength = document.querySelectorAll('[data-minlength]');

  if (_minLength.length) {
    var _a3 = _minLength;

    var _f3 = function _f3(el, i) {
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

    for (var _i3 = 0; _i3 < _a3.length; _i3++) {
      _f3(_a3[_i3], _i3, _a3);
    }

    undefined;
  }
};

minLength();