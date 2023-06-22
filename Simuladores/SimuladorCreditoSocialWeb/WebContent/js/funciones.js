"use strict";

function _classCallCheck(instance, Constructor) {
  if (!(instance instanceof Constructor)) {
    throw new TypeError("Cannot call a class as a function");
  }
}

function _defineProperties(target, props) {
  for (var i = 0; i < props.length; i++) {
    var descriptor = props[i];
    descriptor.enumerable = descriptor.enumerable || false;
    descriptor.configurable = true;
    if ("value" in descriptor) {
      descriptor.writable = true;
    }
    Object.defineProperty(target, descriptor.key, descriptor);
  }
}

function _createClass(Constructor, protoProps, staticProps) {
  if (protoProps) {
    _defineProperties(Constructor.prototype, protoProps);
  }
  if (staticProps) {
    _defineProperties(Constructor, staticProps);
  }
  return Constructor;
}

var _formatearMontos = new FormatearMiles('.formatear-montos', '$');

_formatearMontos.init();

var validarMonto = function validarMonto() {
  var _monto = document.querySelector('#monto');

  var _validar;

  clearTimeout(_validar);

  _monto.addEventListener('blur', function(event) {
    var _el = event.target;
    _validar = setTimeout(function() {
      if (fln.obtener_numeros(_el.value) >= 20000 && fln.obtener_numeros(_el.value) <= 25000000) {
        _el.classList.add('valido');

        _el.classList.remove('invalido');
      } else {
        _el.classList.add('invalido');

        _el.classList.remove('valido');
      }
    }, 150);
  });
};

// validarMonto();
$(".fln-accordion").accordion({
  active: false,
  collapsible: true,
  heightStyle: "content",
  header: ".fln-accordion__header",
  icons: {
    "header": "fln-mas",
    "activeHeader": "fln-menos"
  }
});
$('#tipo-afiliado').change(function(event) {
  if (event.target.value === 'ZFSO') {
    $('#seguro-cesantia').attr('disabled', true).attr('checked', false).parent('.form__checkbox--checked').removeClass('form__checkbox--checked').addClass('form__checkbox--disabled');
  } else {
    $('#seguro-cesantia').attr('disabled', false).parent('.form__checkbox').removeClass('form__checkbox--disabled');
  }
});

function limpiarString(cadena) {
  // Definimos los caracteres que queremos eliminar
  var specialChars = "!@#$^&%*()+=-[]\/{}|:<>?,."; // Los eliminamos todos

  for (var i = 0; i < specialChars.length; i++) {
    cadena = cadena.replace(new RegExp("\\" + specialChars[i], 'gi'), '');
  } // Lo queremos devolver limpio en minusculas


  cadena = cadena.toLowerCase(); // Quitamos espacios y los sustituimos por _ porque nos gusta mas asi

  cadena = cadena.replace(/ /g, "_"); // Quitamos acentos y "ñ". Fijate en que va sin comillas el primer parametro

  cadena = cadena.replace(/á/gi, "a");
  cadena = cadena.replace(/é/gi, "e");
  cadena = cadena.replace(/í/gi, "i");
  cadena = cadena.replace(/ó/gi, "o");
  cadena = cadena.replace(/ú/gi, "u");
  cadena = cadena.replace(/ñ/gi, "n");
  return cadena;
} // Seleccionar regiones


var sRegiones = function sRegiones() {
  var _grupo = $('.seleccion-region');

  var buscarComuna = function buscarComuna(region, comunas) {
    $.ajax({
      url: '/ajax/comunas-regiones.json',
      method: 'GET',
      beforeSend: function beforeSend() {
        fln.preloader(true);
      },
      success: function success(response) {
        comunas.html('');
        comunas.removeClass('valido').focus().blur();
        comunas.append('<option value="" hidden></option>');
        var _a = response;

        var _f = function _f(el) {
          if (el.region_number === region) {
            var _a2 = el.comunas;

            var _f2 = function _f2(el) {
              comunas.append("<option value=\"".concat(limpiarString(el), "\">").concat(el, "</option>"));
            };

            for (var _i2 = 0; _i2 < _a2.length; _i2++) {
              _f2(_a2[_i2], _i2, _a2);
            }

            undefined;
          }
        };

        for (var _i = 0; _i < _a.length; _i++) {
          _f(_a[_i], _i, _a);
        }

        undefined;
        fln.preloader(false);
      }
    });
  };

  _grupo.each(function(i, el) {
    var _regiones = _grupo.find('.regiones');

    var _comunas = _grupo.find('.comunas');

    _regiones.on('change', function(event) {
      buscarComuna(_regiones.val(), _comunas);
    });
  });
};

sRegiones();

var Simulador =
  /*#__PURE__*/
  function() {
    function Simulador(selector) {
      _classCallCheck(this, Simulador);

      this.selector = selector;
    }

    _createClass(Simulador, [{
      key: "sgte",
      value: function sgte(n) {
        var _selector = this.selector;
        $("".concat(_selector, "__item")).hide();
        $("".concat(_selector, "__nav__item")).removeClass('activo');
        $("[data-paso=".concat(n, "]")).addClass('activo');
        $("#paso-".concat(n)).fadeIn('fast');
        fln.scroll_to(this.selector, 0);
      }
    }]);

    return Simulador;
  }();

var simulador = new Simulador('.simulador'); // let sinRows = 0;

var tablasPaginadas = function tablasPaginadas() {
  $('.tabla--paginada').each(function(i) {
    // $('.tabla--paginada tbody tr').filter(
    //   function () {
    //     return $(this).find('td').length == $(this).find('td').filter(function () {
    //       return $(this).text().trim() == '';
    //     }).length;
    //   }).remove();
    // $(".tabla--paginada tbody tr").filter(function () {
    //   return $(this).text() === "-";
    // }).parent().remove();
    var id = $(this).attr('id'),
      buscador = $(this).find('.filtrar'),
      tabla = $(this).find('table'),
      tablaTr = $(this).find('tbody tr'),
      tablaTrTotal = tabla.data('filas'),
      alerta = $(this).find('.alerta'),
      paginador = $(this).find('.paginador');
    paginador.attr('id', 'paginador' + i);
    buscador.attr('id', 'buscador' + i);
    i++; // var pagina = 0;
    // $('.carousel-tablas tbody tr.actual').first().each(function () {
    //   var posicion = $(this).index();
    //   if (posicion != 0) {
    //     pagina = Math.floor(posicion / 10);
    //     //console.log(pagina);
    //   }
    // });

    if (tablaTrTotal === undefined) {
      tablaTrTotal = 10;
    }

    var pagerOptions = {
      container: paginador,
      size: tablaTrTotal,
      page: 0,
      cssNext: '.siguiente',
      cssPrev: '.anterior',
      cssFirst: '.primero',
      cssLast: '.ultimo',
      cssGoto: '.gotoPage',
      output: '<span class="en-pantalla">{endRow}</span> <span class="total">de {filteredRows}</span>',
      updateArrows: true,
      savePages: false,
      cssPageDisplay: '.salida',
      cssPageSize: '.filas',
      cssDisabled: 'deshabilitado',
      cssErrorRow: 'tablesorter-errorRow'
    };
    var $table = tabla.tablesorter({
      // sortClassAsc: 'headerSortUp',
      // sortClassDesc: 'headerSortDown',
      // initWidgets: true,
      widgets: ['zebra'] // widgets: ['zebra', 'filter'],
      // widgetOptions: {
      //   filter_external: buscador,
      //   filter_columnFilters: false,
      //   filter_defaultAttrib: 'data-value',
      //   filter_reset: '.reset'
      // },

    });
    $table.tablesorterPager(pagerOptions); // $table.tablesorterPager(sinRows);
  });
};

var validarDatos = function validarDatos() {
  var _monto = document.querySelector('#monto');

  var _montoValido = false;

  if (fln.obtener_numeros(_monto.value) >= 20000 && fln.obtener_numeros(_monto.value) <= 25000000) {
    _montoValido = true;
  } else _montoValido = false;

  validarFormulario('simulador-datos', {
    texto: "Debes ingresar ",
    otrosCampos: {
      'Monto superior a $20.000 e inferior a $25.000.000': [_montoValido, _monto]
    },
    exito: function exito() {
        document.forms["simulador-datos"].submit();
      /** INSTRUCCIONES
       * exito() es para la validación de los campos, aquí se debe ejecutar lo que pasará si la validación es éxitosa.
       * fln.preloader(1) activa el preloader
       * fln.preloader(0) desactiva el preloader
       * */
      //fln.preloader(1); // ejecutar lo sgte si la respuesta para avanzar al paso 2 es éxitosa

      //simulador.sgte(2);
      /** Si es error ejecutar lo sgte */
      // $('.simulador').hide()
      // $('.respuesta__item--error').fadeIn('fast');

      //fln.scroll_to('.respuesta', 0);
      //fln.preloader(0);
    }
  });
};

var validarSolicitud = function validarSolicitud() {
  validarFormulario('simulador-solicitar', {
    texto: "Debes ingresar ",
    exito: function exito() {
      /** INSTRUCCIONES
       * exito() es para la validación de los campos, aquí se debe ejecutar lo que pasará si la validación es éxitosa.
       * fln.preloader(1) activa el preloader
       * fln.preloader(0) desactiva el preloader
       * */
      fln.preloader(1);
      $('.simulador').hide();
      $('.respuesta__item--exito').fadeIn('fast');
      /** Si es error ejecutar lo sgte */
      // $('.respuesta__item--error').fadeIn('fast');

      fln.scroll_to('.respuesta', 0);
      fln.preloader(0);
    }
  });
};

window.addEventListener("load", function(event) {
  tablasPaginadas();
}, false);