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


/**
 * Se usan en cualquier parte del sitio como referencia al cargar el sitio
 * @namespace variables_globales
 */

/**
 * Versión de la librería
 * @type {number}
 * @memberof variables_globales
 * @instance
 */
var _version = 2.0;
/**
 * Ancho del viewport
 * @type {number}
 * @memberof variables_globales
 * @instance
 */

var _width = window.innerWidth;
/**
 * Alto del viewport
 * @type {number}
 * @memberof variables_globales
 * @instance
 */

var _height = window.innerHeight;
/**
 * Distancia actual del scroll
 * @type {number}
 * @memberof variables_globales
 * @instance
 */

var _scroll = window.scrollY;
/**
 * Pregunta si es Internet-explorer
 * @type {number}
 * @memberof variables_globales
 * @instance
 */

var _ie = window.navigator.userAgent.indexOf('MSIE ');

if (_ie) {
  _scroll = document.documentElement.scrollTop;
}
/**
 * Objeto que contiene todas las funcionalidades de la librería
 * @type {object}
 * @memberof variables_globales
 * @instance
 */


var fln = {};

fln.wrap = function(el, wrapper) {
  el.parentNode.insertBefore(wrapper, el);
  wrapper.appendChild(el);
};
/**
 * Obtiene una función que se ejecutará al hacer resize y al cumplir las condicionales
 * @namespace responsive
 * @example
 * fln.responsive(() => {
 *   if (_width > 768) console.log('Me ejecuto solo si el viewport es mayor a 768')
 *   else console.log('Me ejecuto solo si el viewport es menor a 768')
 * })
 */

/**
 * @memberof responsive
 * @function
 * @param {function} fun Argumento de tipo función que se debe pasar para ejecutarse, debe venir ya con condiciones para los quiebres de pantalla

 */


fln.responsive = function() {
  var fun = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : function() {
    console.warn("\xA1Debes pasar una funci\xF3n como argumento!\nEl ancho actual del viewport es de: ".concat(_width, "\n"));
  };

  /**
   * Ancho del viewport
   * @type {number}
   * @memberof responsive
   * @instance
   */
  _width = window.innerWidth;
  /**
   * Alto del viewport
   * @type {number}
   * @memberof responsive
   * @instance
   */

  _height = window.innerHeight;

  if (typeof fun === 'function') {
    fun();
    window.addEventListener('resize', function(event) {
      _width = window.innerWidth;
      _height = window.innerHeight;
      fun();
    });
  } else {
    console.warn("fln.responsive(\xBFfunction?) : Se debe pasar una funci\xF3n como argumento");
  }
};
/**
 * Obtiene una función que se ejecutará al hacer scroll y al cumplir las condiciones
 * @namespace scrolling
 * @example
 * fln.scrolling(() => {
 *   if (_scroll > 100) console.log('Me ejecuto solo si el scroll bajó más de 100px')
 *   else console.log('Me ejecuto solo si el scroll es menor a 100px')
 * })
 */

/**
 * @memberof scrolling
 * @function
 * @param {function} fun Funcion que se debe pasar para ejecutarse, debe venir ya con condiciones para las distancias del scroll
 */


fln.scrolling = function() {
  var fun = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : function() {
    console.warn("\xA1Debes pasar una funci\xF3n como argumento!\nLa distancia actual del scroll desde el top es: ".concat(_scroll, "\n"));
  };

  /**
   * Posición actual del scroll
   * @type {number}
   * @memberof scrolling
   * @instance
   *
   */
  var _scroll = window.scrollY;

  if (_ie) {
    _scroll = document.documentElement.scrollTop;
  }

  if (typeof fun === 'function') {
    fun();
    document.addEventListener('scroll', function() {
      _scroll = window.scrollY;

      if (_ie) {
        _scroll = document.documentElement.scrollTop;
      }

      fun();
    });
  } else {
    console.warn('fln.scrolling(¿function?) : Se debe pasar una función como argumento');
  }
};
/**
 * Ejecuta un ancla animada (Anima el scroll) a un selector
 * @namespace scroll_to
 * @example
 * fln.scroll_to('.section', -200, 1000, 500);
 * // El scroll se animará hasta la sección ".section"
 * // Con una diferencia de 200px desde el top
 * // Una velocidad de 1s
 * // Con un retrazo de 500ms
 */

/**
 * @memberof scroll_to
 * @function
 * @param {object} el Selector el cual es el destino de la animación
 * @param {number} offset Distancia de diferencia entre el scroll y el selector
 * @param {number} duracion Duración de la animación del scroll
 * @param {number} delay Retraso para que se ejecuté la función
 */


fln.scroll_to = function(el) {
  var offset = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 0;
  var duracion = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : 600;
  var delay = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : 0;

  /**
   * Pasa el selector al selector de jQuery
   * @type {object}
   * @memberof scroll_to
   * @instance
   */
  var _el = $(el);

  setTimeout(function() {
    $('html, body').animate({
      scrollTop: _el.offset().top + offset
    }, duracion);
  }, delay);
};
/**
 * Funcionalidad para formatear números a miles
 * @namespace formatear_miles
 * @example
 * //Tenemos esta variable:
 * let _n = 123456789;
 * //Aplicamos la función:
 * fln.formatear_miles(_n, '$');
 * // nos retornará como string el sgte. valor
 * "$123.456.789"
 */

/**
 * @memberof formatear_miles
 * @function
 * @param {number} numero Valor que será formateado, debe ser tipo númerico
 * @param {string} antecesor Signo que irá antes del valor formateado, ej "$"
 * @returns Devuelve el valor formateado a miles
 */


fln.formatear_miles = function(numero) {
  var antecesor = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : '';

  /**
   * Se convierte "numero" a un array
   * @type {array}
   * @memberof formatear_miles
   * @instance
   */
  var _str = numero.toString().split('');
  /**
   * Array que contendrá los nuevos valores
   * @type {array}
   * @memberof formatear_miles
   * @instance
   */


  var _number = [];
  /**
   * Array que contendrá los nuevos valores
   * @type {array}
   * @memberof formatear_miles
   * @instance
   */

  var _count = Math.ceil(_str.length / 3) - 1;

  for (var i = _str.length - 1; i >= 0; i -= 3) {
    var num1 = _str[i] ? _str[i] : '';
    var num2 = _str[i - 1] ? _str[i - 1] : '';
    var num3 = _str[i - 2] ? _str[i - 2] : '';
    _number[_count] = num3 + num2 + num1;
    _count--;
  }

  _str = _number.join('.');
  return antecesor + _str;
};
/** 
 * Resume textos agregando 3 puntos, por defecto al final
 * @namespace resumir_textos 
 * @default final, el cual los 3 puntos se insertan al final
 * @example
 * let _texto = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Alias necessitatibus omnis impedit"
 * fln.resumir_textos(_texto, 20, 'medio'); // retornará "Lorem ip...s impedit"

*/

/**
 * @memberof resumir_textos
 * @function
 * @param {string} texto Texto que se pasa como string
 * @param {number} n cantidad de palabras que dejará despues del resumen
 * @param {string} pos posición de los 3 puntos "centro" o "inicio", por defecto es al final
 * @returns Retorna el texto resumido
 */


fln.resumir_textos = function(texto, n) {
  var pos = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : 'final';

  if (texto && n) {
    if (texto.length > n) {
      if (pos === 'centro') {
        return "".concat(texto.substr(0, (n - 3) / 2), "...").concat(texto.substr(texto.length - (n - 3) / 2, texto.length));
      } else if (pos === 'inicio') {
        return "...".concat(texto.substr(texto.length - n, texto.length));
      } else {
        return "".concat(texto.substr(0, n), "...");
      }
    }
  } else {
    console.warn('Se debe pasar como argumento el texto y la cantidad de palabras que se espera retornar.');
  }

  return texto;
};
/**
 * Desde un string retorna solo los números dentro de este
 * @namespace obtener_numeros
 * @returns Retorna los numeros dentro del string
 * @example
 * let _string = "$123.456";
 * fln.obtener_numeros(_string, 'string') // Retornará "123456"
 */

/**
 * @memberof obtener_numeros
 * @function
 * @param {string} valor String del cual se obtendran los números
 * @param {string} tipo Tipo de retorno (String o Número)
 */


fln.obtener_numeros = function(valor, tipo) {
  var numero;

  if (tipo === 'string') {
    numero = valor.replace(/\D/g, '').toString();
  } else numero = parseInt(valor.replace(/\D/g, ''));

  return numero;
};
/**
 * getUrlVars()["fecha"]
 * 
 */


fln.getUrlVars = function() {
  var _vars = {};

  var _parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m, key, value) {
    _vars[key] = value;
  });

  return _vars;
};

var multipleEventsListeners = function multipleEventsListeners(elem, events, func) {
  var event = events.split(' ');

  for (var i = 0; i < event.length; i++) {
    elem.addEventListener(event[i], func, false);
  }
};

var isIE9 = function isIE9() {
  if (navigator.appVersion.indexOf('MSIE 9.') !== -1) {
    return true;
  } else {
    return false;
  }
};
/**
 * Funcionalidad para formatear miles a inputs mientras se escribe en estos
 * @class
 */


var FormatearMiles =
  /*#__PURE__*/
  function() {
    /**
     * Obtener los datos del selector
     * @param {object} selector Objeto que se se obtiene como selector
     * @param {string} signo Signo que irá como antecesor
     */
    function FormatearMiles(selector, signo) {
      _classCallCheck(this, FormatearMiles);

      this.signo = '';

      if (signo) {
        this.signo = signo;
      }
      /**
       * Selecciona todos los objetos con el selector pasado como argumento
       * @type {object}
       * @memberof formatear_miles_inputs
       * @instance
       */


      this.selector = document.querySelectorAll(selector);
    }

    _createClass(FormatearMiles, [{
      key: "init",
      value: function init() {
        if (this.selector.length) {
          var _a = this.selector;

          var _f = function _f(el, i) {
            /**
             * Selecciona cada uno de los objetos
             * @type {object}
             * @memberof formatear_miles_inputs
             * @instance
             */
            var _el = el;
            /**
             * Obtiene en value del objeto
             * @type {object}
             * @memberof formatear_miles_inputs
             * @instance
             */

            var _valor = _el.value;

            _el.addEventListener('focus', function(el) {
              _valor = fln.obtener_numeros(el.target.value);

              if (isNaN(_valor) || _valor === 0) {
                _valor = '';
              }

              this.value = _valor;
            });

            _el.addEventListener('blur', function(el) {
              if (this.value === '') {
                _valor = 0;
              } else {
                _valor = fln.formatear_miles(fln.obtener_numeros(el.target.value), this.signo);
              }

              this.value = _valor;
            });
          };

          for (var _i = 0; _i < _a.length; _i++) {
            _f(_a[_i], _i, _a);
          }

          undefined;
        }
      }
    }]);

    return FormatearMiles;
  }();

var _fm = new FormatearMiles('.formatear-miles');

_fm.init();

var _clearAlerta;

fln.alerta = function(opciones) {
  var _opciones = {
    texto: opciones.texto ? opciones.texto : 'Aquí deberias pasar tu mensaje',
    tipo: opciones.tipo ? opciones.tipo : 'error',
    posicion: opciones.posicion ? opciones.posicion : 'top',
    tiempo: opciones.tiempo ? opciones.tiempo : 5000,
    cascada: opciones.cascada ? opciones.cascada : false
  };

  if (!$('.alerta__contenedor').length && _opciones.cascada) {
    $('body').append('<div class="alerta__contenedor"></div>');
  }

  var _alerta = $('<div/>');

  _alerta.addClass('alerta alerta--global');

  _alerta.removeClass('alerta--error').removeClass('alerta--aviso').removeClass('alerta--exito').removeClass('alerta--info');

  var _contenedor = $('.alerta__contenedor');

  if (_opciones.cascada) {
    $('.alerta__contenedor').prepend(_alerta);

    _contenedor.addClass("alerta__contenedor--fixed-".concat(_opciones.posicion));

    _alerta.addClass("alerta--".concat(_opciones.tipo)).text(_opciones.texto);

    if (!_alerta.hasClass('visible')) {
      setTimeout(function() {
        _alerta.addClass('visible');
      }, 100);
      setTimeout(function() {
        _alerta.removeClass('visible').addClass('alerta--hidden');
      }, _opciones.tiempo);
      setTimeout(function() {
        _alerta.remove();
      }, _opciones.tiempo + 300);
    }
  } else {
    $('.alerta--global').remove();
    $('body').append(_alerta);

    _alerta.addClass("alerta--".concat(_opciones.tipo, " alerta--fixed-").concat(_opciones.posicion)).text(_opciones.texto);
  }

  clearInterval(_clearAlerta);
  _clearAlerta = setInterval(function() {
    if (_opciones.cascada) {
      if (!$('.alerta--global').length) {
        _contenedor.remove();
      }
    } else {
      $('.alerta--global').remove();
    }
  }, 5000);
};
/**
 * Preloader general para cargas dentro del sitio
 * @namespace preloader
 * @example
 * fln.preloader(1); // se ejecuta el preloader
 * fln.preloader(0); // se cierra el preloader
 */

/**
 * @memberof preloader
 * @function
 * @param {boolean} event Si es true, se muestra el preloader, con false se cierra
 * @throws Advertencia a la consola indicando que no existe el objeto con el ID "preloader-general"
 */


fln.preloader = function(event) {
  /**
   * Selecciona el ID del preloader
   * @type {object}
   * @memberof preloader
   * @instance
   */
  var _preloader = $('#preloader-general');

  if (_preloader.length) {
    if (event) {
      _preloader.fadeIn('fast');

      setTimeout(function() {
        _preloader.addClass('activo');
      }, 10);
    } else {
      _preloader.addClass('activo');

      setTimeout(function() {
        _preloader.fadeOut('fast');
      }, 10);
    }
  } else {
    console.warn('Se debe crear el contenedor con el id "preloader-general"');
  }
};
/**
 * Funcionalidades e interacciones en los elementos de formularios
 * @namespace formularios
 */

/**
 * @function
 * @memberof formularios
 */


fln.formularios = {
  /**
   * Animación a inputs de los formularios
   * @function
   * @memberof formularios
   */
  animacion: function animacion() {
    /**
     * Selecciona todos los elementos con la clase "form__grupo--animado"
     * @type {object}
     * @memberof formularios
     * @instance
     */
    var _grupoAnimado = document.querySelectorAll('[data-animacion]');

    if (_grupoAnimado.length) {
      var _a2 = _grupoAnimado;

      var _f2 = function _f2(el, i) {
        /**
         * Selecciona cada uno de los elementos con la clase "form__grupo--animado"
         * @type {object}
         * @memberof formularios
         * @instance
         */
        var _grupo = el;
        /**
         * Selecciona todos los elementos ".text" y "textarea" dentrp de "_grupo"
         * @type {object}
         * @memberof formularios
         * @instance
         */

        var _input = _grupo.querySelector('.text, textarea, select');

        if (_input.value) {
          _grupo.querySelector('label').classList.add('focus');
        }

        _input.addEventListener('change', function(event) {
          if (event.target.value.length !== 0) {
            _grupo.querySelector('label').classList.add('focus');
          }
        });

        _input.addEventListener('focus', function(event) {
          _grupo.querySelector('label').classList.add('focus');
        });

        _input.addEventListener('blur', function(event) {
          if (event.target.value.length === 0 || event.target.classList.contains('tipo_telefono') && event.target.value === '+') {
            _grupo.querySelector('label').classList.remove('focus');
          }
        });
      };

      for (var _i2 = 0; _i2 < _a2.length; _i2++) {
        _f2(_a2[_i2], _i2, _a2);
      }

      undefined;
    }
  },
  seleccion: function seleccion() {
    var atributos = function atributos(el, name, icon) {
      if (el.length) {
        var _loop = function _loop(i) {
          var _element = el[i]; // let _parent = _element.parentNode;

          if (!_element.querySelector(".form__".concat(name, "__item"))) {
            var _newElement = document.createElement('div');

            _newElement.classList.add("form__".concat(name, "__item"));

            if (icon) {
              _newElement.classList.add(icon);
            }

            if (name === 'toggle') {
              _element.appendChild(_newElement, _element.firstChild);
            } else {
              _element.insertBefore(_newElement, _element.firstChild);
            }
          }

          _element.querySelector('input').addEventListener('change', function(event) {
            if (event.target.checked) {
              if (event.target.type === 'radio') {
                var _radios = event.target.parentNode.parentNode.parentNode.querySelectorAll('[type=radio]');

                for (var r = 0; r < _radios.length; r++) {
                  var _radio = _radios[r];

                  if (_radio.parentNode.classList.contains("form__".concat(name, "--checked"))) {
                    _radio.parentNode.classList.remove("form__".concat(name, "--checked"));
                  }
                }
              }

              _element.classList.add("form__".concat(name, "--checked"));
            } else {
              _element.classList.remove("form__".concat(name, "--checked"));
            }
          }); // if (_element.parentNode.querySelector('input[type=radio]:not([disabled])')) {
          //     _element.querySelector('input:not(disabled)').addEventListener('click', (event) => {
          //       console.log(event);
          //       let _anterior = _element.parentNode.parentNode.querySelector(`.form__${name}--checked`);
          //       _anterior.classList.remove(`form__${name}--checked`);
          //       // event.target.parentNode.classList.add(`form__${name}--checked`);
          //     })
          // }


          if (_element.querySelector('input').checked) {
            _element.classList.add("form__".concat(name, "--checked"));
          }

          if (_element.querySelector('input').disabled) {
            _element.classList.add("form__".concat(name, "--disabled"));
          }
        };

        for (var i = 0; i < el.length; i++) {
          _loop(i);
        }
      }
    };

    atributos(document.querySelectorAll('.form__checkbox'), 'checkbox', 'fln-check');
    atributos(document.querySelectorAll('.form__toggle'), 'toggle');
    atributos(document.querySelectorAll('.form__radio'), 'radio');
  },
  fallback: function fallback() {
    if (isIE9()) {
      $('.form__grupo').each(function(i, el) {
        var _label = el.find('label');

        var _input = el.find('input, select, textarea');

        $(_label).insertBefore(_input);
      });
    }
  },

  /** MEJORAR MAS ADELANTE
  checkboxs: document.querySelectorAll('input[type=checkbox]'),
  observar() {
    let config = {
      attributes: true,
      childList: true,
      characterData: true,
      subtree: true,
      attributeOldValue: true,
      characterDataOldValue: true,
    };
    const observer = new MutationObserver(function (mutations) {
      console.log(mutations);
      mutations.forEach(function (mutation) {
        if (mutation.nextSibling) {
          console.log(mutation.nextSibling);
          let _checkbox = mutation.nextSibling.previousSibling;
          if (_checkbox.classList.contains('form__hover')) {
            console.log(_checkbox);
          }
        }
      });
    });
    for (let a = 0; a < this.checkboxs.length; a++) {
      observer.observe(this.checkboxs[a].parentNode, config);
    }
  },
  */
  init: function init() {
    // this.observar();
    this.seleccion();
    this.animacion();
    this.fallback();
  }
};
fln.formularios.init();
/**
 * Funcionalidades para los botones
 * @namespace botones
 */

/**
 * Objeto con varios métodos y propiedades de utilidades para los botones
 * @function
 * @memberof botones
 * @property {object} selector Todos los elementos con la clase ".btn"
 * @property {object} selector_gato Todos los botones con "#" en su href
 * @property {object} selector_ripple Todos los botones con el atributo de animación ripple
 * @property {method} return Evita que los botones con "#" en su href tengan ancla
 * @property {method} init Da inicio a los métodos de "fln.botones" y además incluye la función de animacion Ripple 
 */

fln.botones = {
  selector: document.querySelectorAll('.btn'),
  selector_gato: document.querySelectorAll('.btn[href="#"]'),
  selector_ripple: document.querySelectorAll('[data-animation="ripple"]'),
  return: function _return() {
    var _a3 = this.selector_gato;

    var _f3 = function _f3(el, i) {
      el.addEventListener('click', function(event) {
        event.preventDefault();
        return false;
      });
    };

    for (var _i3 = 0; _i3 < _a3.length; _i3++) {
      _f3(_a3[_i3], _i3, _a3);
    }

    undefined;
  },
  init: function init() {
    if (this.selector.length) {
      this.return();
      rippleBtn(this.selector_ripple);
    }
  }
};
/**
 * Funcionalidad para crear la animación de Ripple a los botones
 * @function
 * @memberof botones
 * @param {object} selector Obtiene el selector en común el cual desencadenará la animación de Ripple
 */

function rippleBtn(selector) {
  for (var b = 0; b < selector.length; b++) {
    selector[b].addEventListener('click', function(event) {
      var _x = event.layerX - 10;

      var _y = event.layerY - 10;

      var ripple = document.createElement('span');
      ripple.className = 'btn__ripple';
      ripple.style.left = _x + 'px';
      ripple.style.top = _y + 'px';
      this.appendChild(ripple);
      setTimeout(function() {
        ripple.parentNode.removeChild(ripple);
      }, 500);
    });
  }
}

fln.botones.init();
console.log("\n----------------------------------\n\uD835\uDDD9\uD835\uDDDF\uD835\uDDE1 ".concat(_version, " se ha cargado\n----------------------------------\ndesarrollado por: J-Factory Solutions\n  "));