"use strict";

var validarPaso1 = function validarPaso1() {
  validarFormulario('paso1', {
    texto: 'Debe ingresar ',
    otrosCampos: {
    },
    exito: function exito() {
      /*
        Ingresar metodo para procesar el formulario aquí
        Puede ser:
        $('#paso1').submit();
        O un AJAX, depende de como lo integrarán;
      */
    	$('#paso1').submit();
    }
  });
};

var validarPaso2 = function validarPaso2() {

  validarFormulario('paso2', {
    texto: 'Debe ingresar ',
    otrosCampos: {
    },
    exito: function exito() {
      /*
        Ingresar metodo para procesar el formulario aquí
        Puede ser:
        $('#paso2').submit();
        O un AJAX, depende de como lo integrarán;
      */
    	$('#paso2').submit();
    }
  });
};

var validarPaso3 = function validarPaso3() {

	  validarFormulario('paso3', {
	    texto: 'Debe ingresar ',
	    otrosCampos: {
	    },
	    exito: function exito() {
	      /*
	        Ingresar metodo para procesar el formulario aquí
	        Puede ser:
	        $('#paso2').submit();
	        O un AJAX, depende de como lo integrarán;
	      */
	    	$('#paso3').submit();
	    }
	  });
	};
	
//validarFecha();

var validarTelefono = function validarTelefono() {
  var _inputs = document.querySelectorAll('.tipo_fono');

  if (_inputs.length) {
    var _clearValidar;

    var _a2 = _inputs;

    var _f2 = function _f2(el, i) {
      var _dataPrefijo = el.getAttribute('data-prefijo');

      var _dataPrefijoTipo = el.getAttribute('data-prefijo-tipo');

      var _blur = new Event('blur');

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
            el.dispatchEvent(_blur);
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

function finalizar(){
	$('#paso4').submit();
}

function showLoading() {
	$('#loading').show();
}