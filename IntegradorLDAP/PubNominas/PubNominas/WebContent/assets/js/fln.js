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

/*!
 * Funciones exclusivas de la librería de FLN
 * author: Feline Interactive
 * versión: 2
 * URL: http://lab.feline.cl/fln/
 */

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
  return function() {
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
  }();
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

var formatear_miles_inputs = function formatear_miles_inputs(selector, antecesor) {
  var _input = document.querySelectorAll(selector);

  var _loop = function _loop(i) {
    var _el = _input[i];
    var _valor = _el.value;

    _el.addEventListener('focus', function(event) {
      _valor = fln.obtener_numeros(_el.value);

      if (_valor === 0 || _valor === undefined) {
        _valor = '';
      }

      _el.value = _valor;
    });

    _el.addEventListener('blur', function(event) {
      if (_el.value == '') {
        _valor = 0;
      } else _valor = fln.formatear_miles(fln.obtener_numeros(_el.value), antecesor ? antecesor : null);

      _el.value = _valor;
    });
  };

  for (var i = 0; i < _input.length; i++) {
    _loop(i);
  }
};

formatear_miles_inputs('.formatear-miles', '$');
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

  if (valor) {
    if (tipo === 'string') {
      numero = valor.replace(/\D/g, '').toString();
    } else numero = parseInt(valor.replace(/\D/g, ''));

    return numero;
  }
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

fln.multipleEventsListeners = function(elem, events, func) {
  var events = events.split(' ');
  var _iteratorNormalCompletion = true;
  var _didIteratorError = false;
  var _iteratorError = undefined;

  try {
    for (var _iterator = events[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
      var event = _step.value;
      elem.addEventListener(event, func, false);
    }
  } catch (err) {
    _didIteratorError = true;
    _iteratorError = err;
  } finally {
    try {
      if (!_iteratorNormalCompletion && _iterator["return"] != null) {
        _iterator["return"]();
      }
    } finally {
      if (_didIteratorError) {
        throw _iteratorError;
      }
    }
  }
};

function detectIE() {
  var ua = window.navigator.userAgent;
  var msie = ua.indexOf('MSIE ');

  if (msie > 0) {
    // IE 10 or older => return version number
    return parseInt(ua.substring(msie + 5, ua.indexOf('.', msie)), 10);
  }

  var trident = ua.indexOf('Trident/');

  if (trident > 0) {
    // IE 11 => return version number
    var rv = ua.indexOf('rv:');
    return parseInt(ua.substring(rv + 3, ua.indexOf('.', rv)), 10);
  }

  var edge = ua.indexOf('Edge/');

  if (edge > 0) {
    // Edge (IE 12+) => return version number
    return parseInt(ua.substring(edge + 5, ua.indexOf('.', edge)), 10);
  } // other browser


  return false;
}
/**
 * Cuenta las palabras y las divide en 2, dependiendo del data que sele pase para definir la cantidad de palabras por lado, es una función auto-ejecutable, no es necesario invocarla, a menos que se ejecute al hacer llamado a un ajax
 * @namespace dividir_palabras
 * @example
 * "este texto tiene en total 10 palabras incluyendo el numero"
 * el "data-fraccion" es de 5 (data-fraccion="5")
 * Entonces tenemos: 10/5 = 2
 * Entonces está funcion contara 2 palabras y el resto las agrupará en un span
 */

/**
 * @function
 * @memberof dividir_palabras
 */


var FLNDividirPalabras =
  /*#__PURE__*/
  function() {
    function FLNDividirPalabras(selector, options) {
      _classCallCheck(this, FLNDividirPalabras);

      this.selector = document.querySelectorAll('.dividir-palabras');
      this.options = {
        fraccion: 2
      };
    }

    _createClass(FLNDividirPalabras, [{
      key: "dividir",
      value: function dividir() {
        var _this2 = this;

        var _palabras = this.selector;

        if (_palabras.length) {
          var _a = _palabras;

          var _f = function _f(el, i) {
            var _palabra = el;

            if (!_palabra.classList.contains('palabra-dividida')) {
              var _fraccion = _this2.options.fraccion;

              if (_palabra.getAttribute('data-fraccion')) {
                _fraccion = _palabra.getAttribute('data-fraccion');
              }

              var _textos = _palabra.innerText;

              var _textosSplit = _textos.split(' ');

              var _indexMitad = Math.round(_textosSplit.length / _fraccion);

              var _textoNuevo = ' ';

              for (var b = 0; b < _textosSplit.length; b++) {
                var _texto = _textosSplit[b];

                if (b === _indexMitad) {
                  _textoNuevo += '<span>';
                }

                _textoNuevo += "".concat(_texto, "\r");
              }

              _textoNuevo += '</span>';

              if (_fraccion === 0) {
                _palabra.innerHTML = "<span>".concat(_textoNuevo, "</span>");
              } else _palabra.innerHTML = _textoNuevo;

              _palabra.classList.add('palabra-dividida');
            }
          };

          for (var _i = 0; _i < _a.length; _i++) {
            _f(_a[_i], _i, _a);
          }

          undefined;
        }
      }
    }, {
      key: "init",
      value: function init() {
        this.dividir();
      }
    }]);

    return FLNDividirPalabras;
  }();

var dividirPalabras = new FLNDividirPalabras();
dividirPalabras.init();
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
          var _a2 = this.selector;

          var _f2 = function _f2(el, i) {
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

          for (var _i2 = 0; _i2 < _a2.length; _i2++) {
            _f2(_a2[_i2], _i2, _a2);
          }

          undefined;
        }
      }
    }]);

    return FormatearMiles;
  }();

var _fm = new FormatearMiles('.formatear-miles');

_fm.init();

var FLNTabs =
  /*#__PURE__*/
  function() {
    function FLNTabs(selector, options) {
      _classCallCheck(this, FLNTabs);

      this.options = {
        selector: selector || '.fln-tabs',
        active: 0,
        opened: true,
        anclaHash: false,
        anclaHashOffset: 0,
        animatedScroll: true,
        urlVarDistance: -10,
        responsive: {
          active: true,
          keepActive: true,
          headerIcon: {
            active: 'fln-abajo',
            inactive: undefined
          }
        }
      };
      this.beforeCreate = undefined;
      this.beforeCreate = undefined;
      this.afterCreate = undefined;
      this.beforeActive = undefined;
      this.onActive = undefined;
    }

    _createClass(FLNTabs, [{
      key: "tabs",
      value: function tabs() {
        var $this = this;

        var _tabsContenedor = document.querySelectorAll(this.options.selector);

        if (_tabsContenedor.length) {
          var _a3 = _tabsContenedor;

          var _f3 = function _f3(ela, a) {
            var _this = _tabsContenedor[a];

            if (_this.className !== 'fln-tabs') {
              _this.classList.add('fln-tabs');
            }

            var _items = _this.querySelectorAll('.fln-tabs__item');

            var _links = _this.querySelectorAll('.fln-tabs__link');

            var _tabs = _this.querySelectorAll('.fln-tabs__contenido');

            if (!isNaN($this.options.active)) {
              _items[0].classList.add('activo');

              _tabs[0].style.display = '';

              _tabs[0].classList.add('activo');
            }

            if (typeof $this.beforeCreate === 'function') {
              $this.beforeCreate({
                elementos: {
                  _this: _this,
                  _items: _items,
                  _links: _links,
                  _tabs: _tabs
                }
              });
            }

            var _a4 = _links;

            var _f4 = function _f4(elb, b) {
              var _link = _links[b];
              var _tab = _tabs[b];

              if (!_tab.classList.contains('activo')) {
                _tab.style.display = 'none';
              }

              _link.addEventListener('click', function(el) {
                if (typeof $this.beforeActive === 'function') {
                  $this.beforeActive(_link, _tab);
                }

                var _id = this.getAttribute('href').replace('#', '');

                var _a5 = _this.querySelectorAll('.activo');

                var _f5 = function _f5(element) {
                  element.classList.remove('activo');

                  if (element.classList.contains('fln-tabs__contenido')) {
                    element.style.display = 'none';
                  }
                };

                for (var _i5 = 0; _i5 < _a5.length; _i5++) {
                  _f5(_a5[_i5], _i5, _a5);
                }

                undefined;
                this.parentNode.classList.add('activo');
                document.getElementById(_id).style.display = '';
                document.getElementById(_id).classList.add('activo');

                if ($this.options.animatedScroll) {
                  fln.scroll_to(_this, $this.options.urlVarDistance);
                }

                if (typeof $this.onActive === 'function') {
                  $this.onActive(_link, _tab);
                }

                el.preventDefault();
                return false;
              });
            };

            for (var _i4 = 0; _i4 < _a4.length; _i4++) {
              _f4(_a4[_i4], _i4, _a4);
            }

            undefined;

            if ($this.options.responsive.active) {
              $this.responsive(_this, _tabs, _items, _links);
            }

            if (typeof $this.afterCreate === 'function') {
              $this.afterCreate(_items, _links, _tabs);
            }

            if ($this.options.anclaHash && fln.getUrlVars()['tab']) {
              var _link = _this.querySelector(".fln-tabs__link[href=\"".concat(fln.getUrlVars()['tab'], "\"]"));

              var _accordeon = _this.querySelector(".fln-tabs__header[data-id=\"".concat(fln.getUrlVars()['tab'], "\"]"));

              var _tab = _this.querySelector(fln.getUrlVars()['tab']);

              if (_link || _tab || _accordeon) {
                $this.clean(_this);

                _link.parentNode.classList.add('activo');

                _tab.classList.add('activo');

                if (_accordeon) {
                  _accordeon.classList.add('activo');

                  fln.scroll_to(_accordeon, $this.options.urlVarDistance);
                } else {
                  fln.scroll_to(_this, $this.options.urlVarDistance);
                }

                _tab.style.display = '';
              }
            }
          };

          for (var _i3 = 0; _i3 < _a3.length; _i3++) {
            _f3(_a3[_i3], _i3, _a3);
          }

          undefined;
        }
      }
    }, {
      key: "clean",
      value: function clean(container) {
        var _activo = container.querySelectorAll('.activo');

        var _a6 = _activo;

        var _f6 = function _f6(el, i) {
          if (_activo[i].classList.contains('fln-tabs__item') || _activo[i].classList.contains('fln-tabs__contenido') || _activo[i].classList.contains('fln-tabs__header')) {
            _activo[i].classList.remove('activo');
          }

          if (_activo[i].classList.contains('fln-tabs__contenido')) {
            _activo[i].style.display = 'none';
          }
        };

        for (var _i6 = 0; _i6 < _a6.length; _i6++) {
          _f6(_a6[_i6], _i6, _a6);
        }

        undefined;
      }
    }, {
      key: "accordion",
      value: function accordion(container, tabs, items, links, method) {
        // let _this = container;
        // let _items = items;
        // let _tabs = tabs;
        // let _links = links;
        var $this = this;

        if (!method) {
          if (!container.classList.contains('fln-tabs--responsive')) {
            var _a7 = links;

            var _f7 = function _f7(elc, c) {
              var _link = links[c];
              var _tab = tabs[c];

              var _header = document.createElement('div');

              var _icono = document.createElement('span');

              var _linkText = _link.innerHTML;

              _header.classList.add('fln-tabs__header');

              _header.innerHTML = _linkText;

              _icono.classList.add('fln-tabs__header__icono');

              _icono.classList.add('fln-abajo');

              _header.appendChild(_icono);

              _tab.parentNode.insertBefore(_header, _tab);

              container.classList.add('fln-tabs--responsive');

              if (items[c].classList.contains('activo')) {
                _header.classList.add('activo');
              }

              _header.dataset.id = _link.getAttribute('href');

              _header.addEventListener('click', function(event) {
                if (typeof $this.beforeActive === 'function') {
                  $this.beforeActive(_link, _tab);
                }

                if (!_header.classList.contains('activo')) {
                  var _allChildrens = container.querySelectorAll('.fln-tabs__header');

                  $(_allChildrens).not(_header).removeClass('activo').next().slideUp('fast').removeClass('activo');
                  $(items).not(items[c]).removeClass('activo');
                  $(items[c]).addClass('activo');
                  $(_header).next().slideDown('fast', function() {
                    fln.scroll_to(_header);
                  }).addClass('activo');

                  _header.classList.add('activo');
                } else {
                  _header.classList.remove('activo');

                  $(_header).next().slideUp('fast');
                }

                if (typeof $this.onActive === 'function') {
                  $this.onActive(_link, _tab);
                }

                event.preventDefault();
              });
            };

            for (var _i7 = 0; _i7 < _a7.length; _i7++) {
              _f7(_a7[_i7], _i7, _a7);
            }

            undefined;
          }
        } else {
          if (container.classList.contains('fln-tabs--responsive')) {
            var _a8 = tabs;

            var _f8 = function _f8(eld, d) {
              var _headers = container.querySelectorAll('.fln-tabs__header');

              var _a9 = _headers;

              var _f9 = function _f9(ele, e) {
                ele.remove();
              };

              for (var _i9 = 0; _i9 < _a9.length; _i9++) {
                _f9(_a9[_i9], _i9, _a9);
              }

              undefined;
            };

            for (var _i8 = 0; _i8 < _a8.length; _i8++) {
              _f8(_a8[_i8], _i8, _a8);
            }

            undefined;
            container.classList.remove('fln-tabs--responsive');
          }
        }
      }
    }, {
      key: "responsive",
      value: function responsive(el, tabs, items, links) {
        var $this = this;
        fln.responsive(function() {
          if (_width < 768) {
            $this.accordion(el, tabs, items, links);
          } else {
            $this.accordion(el, tabs, items, links, 'destroy');
          }
        });
      }
    }, {
      key: "init",
      value: function init() {
        this.tabs();
      }
    }]);

    return FLNTabs;
  }();
/* Ejemplo de Uso */
// let _tabs = new FLNTabs();
// _tabs.init();


var Pasos =
  /*#__PURE__*/
  function() {
    function Pasos(selector) {
      _classCallCheck(this, Pasos);

      this.selector = selector;
    }

    _createClass(Pasos, [{
      key: "items",
      value: function items() {
        return $(".".concat(this.selector, "__item")).length;
      }
    }, {
      key: "atributos",
      value: function atributos() {
        $(".".concat(this.selector)).find(".".concat(this.selector, "__item")).each(function(a, el) {
          var _this = $(el);

          _this.attr('id', "pasos-item-".concat(a));

          _this.find('.form__grupo').not('.no-aplicar').each(function(b, el) {
            var _name = $(el).data('name');

            $(el).attr('id', "pasos-grupo-".concat(a, "-").concat(b));
            $(el).find('label').each(function(c, el) {
              if (c === 0) {
                $(el).attr('for', _name);
              } else {
                $(el).attr('for', "".concat(_name, "-").concat(c));
              }
            });
            $(el).find('.text,.select,textarea').each(function(c, el) {
              if (c === 0) {
                $(el).attr('id', _name).attr('name', _name);
              } else {
                $(el).attr('id', "".concat(_name, "-").concat(c)).attr('name', "".concat(_name, "-").concat(c));
              }
            });
            $(el).find('.radio').each(function(c, el) {
              $(el).attr('name', _name).attr('value', $(el).parents('.form__input').find('label').text());

              if (c === 0) {
                $(el).attr('id', _name);
              } else {
                $(el).attr('id', "".concat(_name, "-").concat(c));
              }
            });
            $(el).find('.checkbox').each(function(c, el) {
              $(el).attr('name', "".concat(_name, "[]")).attr('value', $(el).parents('.form__input').find('label').text());

              if (c === 0) {
                $(el).attr('id', _name);
              } else {
                $(el).attr('id', "".concat(_name, "-").concat(c));
              }
            });
          });
        });
      }
    }, {
      key: "sgte",
      value: function sgte(n, validar) {
        var _items = this.items();

        var _selector = this.selector;
        var callback = $("#pasos-item-".concat(n)).data('callback');

        function avanzar() {
          var _percent = 100 * (n + 1) / _items;

          wizard.actualizar(100 * n / _items, _percent);
          $(".".concat(_selector, "__item")).hide();
          $("#pasos-item-".concat(n)).fadeIn('fast');
          fln.scroll_to('.wizard', _distancia);
        }

        if (validar) {
          if (this.validar(n - 1)) {
            avanzar();

            if ($("#pasos-item-".concat(n)).data('callback')) {
              eval(callback + '()');
            }
          } else {
            alerta({
              texto: 'Debe completar todos los campos requeridos.',
              posicion: 'bottom'
            });
          }
        } else {
          avanzar();

          if ($("#pasos-item-".concat(n)).data('callback')) {
            eval(callback + '()');
          }
        }
      }
    }, {
      key: "validar",
      value: function validar(n) {
        var _valido = false;
        $('.requerido:visible').each(function(i, el) {
          if (!$(el).hasClass('valido')) {
            $(el).addClass('invalido');
            fln.scroll_to('.requerido.invalido:visible:eq(0)', _distancia - 40);
            setTimeout(function() {
              $('.requerido.invalido:visible:eq(0)').focus();
            }, 600);
          } else {
            $(el).removeClass('invalido');
          }
        });

        if ($('#pasos-item-' + n + ' .valido:not(.ignorar-valido):visible').length === $('#pasos-item-' + n + ' .requerido:not(.ignorar-valido):visible').length) {
          _valido = true;
        }

        return _valido;
      }
    }, {
      key: "checkmark",
      value: function checkmark() {
        var _selector = this.selector;
        $(".".concat(_selector, "__item")).each(function(a, el) {
          var _parent = $(el);

          _parent.find('.form__input--seleccion').each(function(a, el) {
            var _radioParent = $(el).parent();

            var _ds = $(el).find('[data-show]');

            $(el).find('> .radio').each(function(c, el) {
              if ($(el).is(':checked')) {
                $(el).prop('checked', false);
              }

              $(el).on('change', function(el, event) {
                _radioParent.removeClass('valido');

                if (this.checked) {
                  if (typeof _ds.data('show') === 'string') {
                    $("#".concat(_ds.data('show'))).slideDown('fast');
                    fln.scroll_to("#".concat(_ds.data('show')), -110);
                  }

                  _radioParent.addClass('valido').removeClass('invalido');
                } else {
                  _radioParent.removeClass('valido');
                }
              });
            }).not('[data-show]').each(function(b, el) {
              $(el).on('change', function(el, event) {
                $(el).parents('.form__grupo').next('.pasos__hidden').slideUp('fast');
              });
            });
            $(el).find('.checkbox').each(function(c, el) {
              if ($(el).is(':checked')) {
                $(el).prop('checked', false);
              }

              $(el).on('change', function(el, event) {
                if (_radioParent.find('.checkbox:checked').length >= 1) {
                  _radioParent.addClass('valido').removeClass('invalido');
                } else {
                  _radioParent.removeClass('valido');
                }
              });
            });
          });
        });
      }
    }, {
      key: "init",
      value: function init() {
        this.checkmark();
        this.atributos();
      }
    }]);

    return Pasos;
  }();

var FLNGaleria =
  /*#__PURE__*/
  function() {
    function FLNGaleria() {
      var selector = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : '.js-galeria';
      var opciones = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {
        cantidadItems: undefined
      };

      _classCallCheck(this, FLNGaleria);

      this.selector = selector;
      this.item = 'fln-galeria__item';
      this.opciones = opciones;
    }

    _createClass(FLNGaleria, [{
      key: "crear",
      value: function crear() {
        var _this3 = this;

        var _galerias = document.querySelectorAll(this.selector);

        var _a10 = _galerias;

        var _f10 = function _f10(el, i) {
          var _this = el;

          var _items = _this.querySelectorAll('.fln-galeria__item');

          if (_this.getAttribute('data-items')) {
            _this3.opciones.cantidadItems = Number(_this.getAttribute('data-items'));
          }

          var _n = Number(_this3.opciones.cantidadItems);

          if (_n) {
            var _restantes = _items.length;

            if (_restantes > _n) {
              var _num = _restantes - _n;

              var _lastItem = _items[_n - 1];

              var _capa = document.createElement('span');

              _lastItem.querySelector('.fln-galeria__ico').remove();

              _capa.classList.add('fln-galeria__mas', 'fln-galeria__capa');

              _capa.innerText = "+ ".concat(_num);

              _lastItem.appendChild(_capa);

              for (var _i11 = 0; _i11 < _items.length; _i11++) {
                if (_i11 >= _n) {
                  _items[_i11].style.display = 'none';
                }
              }
            }
          }

          $(_items).attr('data-fancybox', "group".concat(i));
        };

        for (var _i10 = 0; _i10 < _a10.length; _i10++) {
          _f10(_a10[_i10], _i10, _a10);
        }

        undefined;
      }
    }, {
      key: "init",
      value: function init() {
        this.crear();
      }
    }]);

    return FLNGaleria;
  }();

$('.fln-galeria__item').fancybox({
  caption: function caption(instance, item) {
    return $(this).find('figcaption').html();
  },
  lang: 'es',
  i18n: {
    es: {
      CLOSE: 'Cerrar',
      NEXT: 'Siguiente',
      PREV: 'Anterior',
      ERROR: 'El contenido no se pudo cargar.',
      PLAY_START: 'Comenzar presentación',
      PLAY_STOP: 'Pausar presentación',
      FULL_SCREEN: 'Pantalla completa',
      THUMBS: 'Miniaturas',
      DOWNLOAD: 'Descargar',
      SHARE: 'Compartir',
      ZOOM: 'Zoom'
    }
  }
});

var _galeria = new FLNGaleria();

_galeria.init();

var Observar =
  /*#__PURE__*/
  function() {
    function Observar(options) {
      _classCallCheck(this, Observar);

      this.options = {
        selector: '.observar',
        threshold: 0.5,
        keepActive: false
      };

      if (_typeof(options) === 'object') {
        if (options.selector) {
          this.options.selector = options.selector;
        }

        if (options.threshold) {
          this.options.threshold = options.threshold;
        }

        if (options.keepActive) {
          this.options.keepActive = options.keepActive;
        }
      }
    }

    _createClass(Observar, [{
      key: "observar",
      value: function observar() {
        var _this4 = this;

        var _items = document.querySelectorAll(this.options.selector);

        var _keepActive = this.options.keepActive;
        var _a11 = _items;

        var _f11 = function _f11(el, i) {
          var _item = el;

          function callback(entries, observer) {
            if (entries[0].isIntersecting) {
              _item.classList.add('observar--activo');
            } else if (!_keepActive) {
              _item.classList.remove('observar--activo');
            }
          }

          if (_item && !detectIE()) {
            var options = {
              threshold: _this4.options.threshold
            };
            var observer = new IntersectionObserver(callback, options);
            observer.observe(_item);
          } else {
            _item.classList.add('observar--activo');
          }
        };

        for (var _i12 = 0; _i12 < _a11.length; _i12++) {
          _f11(_a11[_i12], _i12, _a11);
        }

        undefined;
      }
    }]);

    return Observar;
  }();

var _observar = new Observar({
  selector: '.observar',
  threshold: 1 // keepActive: true

});

_observar.observar();

var _clearAlerta;

fln.alerta = function(opciones) {
  var _opciones = {
    selector: opciones.selector,
    texto: opciones.texto ? opciones.texto : 'Aquí deberias pasar tu mensaje',
    tipo: opciones.tipo ? opciones.tipo : false,
    altoContraste: opciones.altoContraste ? opciones.altoContraste : false,
    // TODO: Ver la posibilidad de asignar ancho full y automatico
    //ancho: opciones.ancho ? opciones.ancho : '',
    // TODO: Ver la posibilidad de mostrar distintos tamaños de alerta
    //size: opciones.size ? opciones.size : '',
    posicion: opciones.posicion ? opciones.posicion : 'top',
    tiempo: opciones.tiempo ? opciones.tiempo : 5000
  };
  var _tipoAlertas = ['alerta--exito', 'alerta--error', 'alerta--info', 'alerta--aviso', 'alerta--hc'];

  if (!_opciones.selector) {
    var _alerta = document.createElement('div');

    var _alertaAnterior = document.querySelector('.alerta--global');

    var _body = document.querySelector('body');

    if (_alertaAnterior) {
      _alertaAnterior.remove();
    }

    _alerta.className = "alerta alerta--global ".concat(_opciones.tipo ? "alerta--".concat(_opciones.tipo) : '', " alerta--fixed-").concat(_opciones.posicion, " ").concat(_opciones.altoContraste ? "alerta--hc" : '');
    _alerta.innerText = _opciones.texto;

    _body.appendChild(_alerta);

    clearInterval(_clearAlerta);
    _clearAlerta = setInterval(function() {
      _alerta.remove();
    }, _opciones.tiempo);
  } else {
    var _alerta2 = document.querySelector(_opciones.selector);

    if (_opciones.texto) {
      _alerta2.innerText = _opciones.texto;
    }

    if (_opciones.tipo) {
      var _alerta2$classList;

      (_alerta2$classList = _alerta2.classList).remove.apply(_alerta2$classList, _tipoAlertas);

      _alerta2.classList.add("alerta--".concat(_opciones.tipo));
    }

    $(_alerta2).slideDown('fast');
    clearInterval(_clearAlerta);
    _clearAlerta = setInterval(function() {
      $(_alerta2).slideUp('fast');
    }, _opciones.tiempo);
  }
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
      var _a12 = _grupoAnimado;

      var _f12 = function _f12(el, i) {
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

      for (var _i13 = 0; _i13 < _a12.length; _i13++) {
        _f12(_a12[_i13], _i13, _a12);
      }

      undefined;
    }
  },
  seleccion: function seleccion() {
    var atributos = function atributos(el, name, icon) {
      if (el.length) {
        var _loop2 = function _loop2(i) {
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
          _loop2(i);
        }
      }
    };

    atributos(document.querySelectorAll('.form__checkbox'), 'checkbox', 'fln-check');
    atributos(document.querySelectorAll('.form__toggle'), 'toggle');
    atributos(document.querySelectorAll('.form__radio'), 'radio');
  },
  fallback: function fallback() {
    if (detectIE() && detectIE() < 10) {
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
 * Funcionalidad que genera menués desplegables, hasta 3 niveles
 * @namespace menu_desplegable
 */

/**
 * @function
 * @memberof menu_desplegable
 */

fln.menu_desplegable = function() {
  /**
   * Define el item activo dentro del menú para poder abrir el menú
   * @type {boolean}
   * @memberof menu_desplegable
   * @instance
   */
  var _activo = false;
  /**
   * Selecciona todos los objetos con la clase "menu-desplegable" para iniciar la funcionalidad
   * @type {object}
   * @memberof menu_desplegable
   * @instance
   */

  var _menuDesplegable = $('.menu-desplegable');

  if (_menuDesplegable.length) {
    _menuDesplegable.each(function(i, el) {
      $(el).find('ul').addClass('submenu');
      $(el).find('> ul').addClass('submenu--uno');
      $(el).find('.submenu--uno > li').has('ul').each(function(i, el) {
        if ($(el).hasClass('abierto')) {
          _activo = i;
        } else _activo = false;
      });
      var abrirSubmenu0 = $(el).find('.submenu--uno > li:has(ul) > a');
      $(el).find('.submenu--uno').accordion({
        animate: 200,
        active: _activo,
        collapsible: true,
        heightStyle: 'content',
        header: abrirSubmenu0,
        icons: {
          'header': 'fln-mas',
          'activeHeader': 'fln-menos'
        }
      });
      $(el).find('.submenu--uno > li > ul').addClass('submenu--dos');
      _activo = false;
      $(el).find('.submenu--dos li').has('ul').each(function(i, el) {
        if ($(el).hasClass('abierto')) {
          _activo = i;
        } else _activo = false;
      });
      var abrirSubmenu1 = $(el).find('.submenu--dos > li:has(ul) > a');
      $(el).find('.submenu--dos').accordion({
        animate: 200,
        active: _activo,
        collapsible: true,
        heightStyle: 'content',
        header: abrirSubmenu1,
        icons: {
          'header': 'fln-mas',
          'activeHeader': 'fln-menos'
        }
      });
      $(el).find('.submenu--dos > li > ul').addClass('submenu--tres');
      _activo = false;
      $(el).find('.submenu--tres li').has('ul').each(function(i, el) {
        if ($(el).hasClass('abierto')) {
          _activo = i;
        } else _activo = false;
      });
      var abrirSubmenu2 = $(el).find('.submenu--tres > li:has(ul) > a');
      $(el).find('.submenu--tres').accordion({
        animate: 200,
        active: _activo,
        collapsible: true,
        heightStyle: 'content',
        header: abrirSubmenu2,
        icons: {
          'header': 'fln-mas',
          'activeHeader': 'fln-menos'
        }
      });
    });
  }
};

fln.menu_desplegable();
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
  "return": function _return() {
    var _a13 = this.selector_gato;

    var _f13 = function _f13(el, i) {
      el.addEventListener('click', function(event) {
        event.preventDefault();
        return false;
      });
    };

    for (var _i14 = 0; _i14 < _a13.length; _i14++) {
      _f13(_a13[_i14], _i14, _a13);
    }

    undefined;
  },
  init: function init() {
    if (this.selector.length) {
      this["return"]();
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
/**
 * Funcionalidades para las tablas
 * @namespace tablas
 */

/**
 * Funcionalidad que agarra las tablas dentro de "contenido-dinamico" y le aplica las propiedades de las tablas de la librería, dependiendo de las clases que se le asignen en el TinyMce
 * @function
 * @memberof tablas
 */

fln.tablas = function() {
  /**
   * Objeto que agarra todas las clases "contenido-dinamico"
   * @type {object}
   * @memberof tablas
   * @instance
   */
  var _contenidoDinamico = document.querySelectorAll('.contenido-dinamico');

  if (_contenidoDinamico.length) {
    var _a14 = _contenidoDinamico;

    var _f14 = function _f14(el, i) {
      /**
       * Separa todos los elementos del objeto "_contenidoDinamico"
       * @type {object}
       * @memberof tablas
       * @instance
       */
      var _cD = el;
      /**
       * Busca todas las etiquetas "table" dentro de "_cD"
       * @type {object}
       * @memberof tablas
       * @instance
       */

      var _tablas = _cD.querySelectorAll('table');

      if (_tablas.length) {
        for (var b = 0; b < _tablas.length; b++) {
          var _tabla = _tablas[b];
          var _classes = _tabla.className;

          if (!_tabla.parentNode.classList.contains('tabla')) {
            var _nuevaTabla = document.createElement('div');

            if (!_classes.length) {
              fln.wrap(_tabla, _nuevaTabla);
            } else {
              _nuevaTabla.className = _classes;
              fln.wrap(_tabla, _nuevaTabla);
              _tabla.className = '';
            }

            _nuevaTabla.classList.add('tabla');

            if (_nuevaTabla.classList.contains('tabla--responsive')) {
              var _ths = _nuevaTabla.querySelectorAll('th');

              var _thTextos = [];

              for (var c = 0; c < _ths.length; c++) {
                _thTextos.push(_ths[c].innerText);
              }

              var _tbodys = _nuevaTabla.querySelectorAll('tbody');

              for (var d = 0; d < _tbodys.length; d++) {
                var _tbody = _tbodys[d];

                var _trs = _tbody.querySelectorAll('tr');

                for (var e = 0; e < _trs.length; e++) {
                  var _tr = _trs[e];

                  for (var _d = 0; _d < _thTextos.length; _d++) {
                    var _thTexto = _thTextos[_d];

                    var _td = _tr.querySelectorAll('td');

                    _td[_d].setAttribute('data-th', _thTexto);
                  }
                }
              }
            }
          }
        }
      }
    };

    for (var _i15 = 0; _i15 < _a14.length; _i15++) {
      _f14(_a14[_i15], _i15, _a14);
    }

    undefined;
  }
};

fln.tablas();
console.log("\n----------------------------------\n\uD835\uDDD9\uD835\uDDDF\uD835\uDDE1 ".concat(_version, " se ha cargado\n----------------------------------\ndesarrollado por:\nF\xE8line Interactive (https://feline.cl)\n  "));
console.clear();