"use strict";
var validarFiltrar = function validarFiltrar() {
	var guardar= false;
	
	$("[name='empresa_filtrar']:checked").each(   
		    function() {
		        guardar= true;
		    }
		);
	
	if(guardar){
		fln.preloader(1);
		$('#form-filtrar').submit();
	}
};

function setPeriodo(periodo, campo) {
	if(campo!=''){
		periodo= $("#ano-inicio").val() + $("#mes-inicio").val();
	}
	if(periodo!="0" && periodo.length==6){
		if(campo!=''){
			//mostrarContenido('.js-periodo-personalizado', false);
		}
		var url = "setPeriodo.do?periodo="+ periodo + "&perso=" + campo + "&rand=" + Math.random();
		$
		.getJSON(
				url,
				function(data) {
					if (data != null) {
						
						$("#periodo").html("(" + data.periodo + ")");
						if(campo==''){
							$("#mes-inicio").val('');
							$("#ano-inicio").val('');
						}
					}
				});
	}
	
}

function setRutEmpresa(rutemp) {
	if(rutemp==""){
		$("#razonSocial").val(' ');
	}
	
validarFormulario('form-nominas', {
		
		texto : 'Favor revisa ',
		otrosCampos : {
		},
		exito : function exito() {
			var url = "setRutEmpresa.do?rutEmpresa="+ rutemp + "&rand=" + Math.random();
			$
			.getJSON(
					url,
					function(data) {
						if (data != null) {
							//$( "#razonSocial" ).prop( "disabled", false );
							if(data.estado=="OK"){
								document.getElementById('rut-empresa').style.background='#A6D0B4';
								$("#razonSocial").val(data.razonSocial);
								
							}else{
								document.getElementById('rut-empresa').style.background='#D5968F';
								$("#razonSocial").val('');
							}
							//$( "#razonSocial" ).prop( "disabled", true );
							setTimeout("document.getElementById('rut-empresa').style.background='#ffffff';", 1000);
							
						}
					});
		}
	});
		
	
}

function limpiarRUT(){
	$("#rut-empresa").val('');
	$("#razonSocial").val('');
	
}

function activarAviso(activo) {
		
		var parrafo1= $("#parrafo1").val();
		var parrafo2= $("#parrafo2").val();
		var nota= $("#nota").val();
		
		var url = "setAviso.do?activo="+ activo + "&parrafo1=" + parrafo1 + "&parrafo2=" + parrafo2 + "&nota=" + nota + "&rand=" + Math.random();
		$
		.getJSON(
				url,
				function(data) {
					if (data != null) {
						avisar(data.mensaje, 3000);
						//$("#mensaje").html("(" + data.mensaje + ")");
						//$("#mensaje").show();
						//setTimeout("$('#mensaje').hide()", 3000);
					}
				});
	
	
}

function setAnios(cantidad) {
	if(cantidad<=0){
		cantidad=1;
	}
		var url = "setAnios.do?cantidad="+ cantidad + "&rand=" + Math.random();
		$
		.getJSON(
				url,
				function(data) {
					if (data != null) {
						
						 var resultado = document.getElementById("ano-inicio");
				            var salida='';				    
				            for(var f=0;f< data.anios.length;f++)
				            {
				            	
				              salida += "<option value='" + data.anios[f] + "'>" + data.anios[f] + "</option>";
				             
				            }
				            //solo para ver la respuesta borrar despues
				            //$('#resultado').append(salida);
				            resultado.innerHTML = salida;
				            $('#ano-inicio').val(' ');
					}
				});
}

function lock(boton, reg) {
	boton.disabled="disabled";
	//boton.style.background= "#E1A628";
	$('#loading').show();
	setTimeout("document.getElementById('reg" + reg + "').disabled=false;", 3100);
	setTimeout("document.getElementById('reg" + reg + "').style.background='#D1E1E9';", 3100);
	setTimeout("$('#loading').hide()", 3000);
}

var callNominas = function callNominas() {
	fln.preloader(1);
	$('#form-nomina').submit();
};

var callNominasOndemand = function callNominasOndemand() {
	$('#loading').show();
	setTimeout("$('#loading').hide()", 3000);
	$('#form-nomina').submit();
};

var setPeriodoEstadisticas = function setPeriodoEstadisticas() {

	validarFormulario('paso2', {
		texto : 'Debes ingresar ',
		otrosCampos : {

		// 'Aceptar condiciones': [_check, $('#terminos').parent()]
		},
		exito : function exito() {
			fln.preloader(1);
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso2').submit();
		}
	});
};

var disableKeydownRepeat = function disableKeydownRepeat(selector, callback) {
  selector = document.querySelectorAll(selector);
  var keys = [8, 46, 16, 37, 39];
  var _iteratorNormalCompletion = true;
  var _didIteratorError = false;
  var _iteratorError = undefined;

  try {
    for (var _iterator = selector[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
      var input = _step.value;
      input.addEventListener('keydown', function(event) {
        if (event.repeat && keys.indexOf(event.keyCode) < 0) {
          event.preventDefault();
        }

        if (typeof callback === 'function') {
          callback();
        }
      });
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

var validarTelefono = function validarTelefono() {
  var _inputs = document.querySelectorAll('.tipo_fono');

  if (_inputs.length) {
    var _clearValidar;

    var _a = _inputs;

    var _f = function _f(el, i) {
      var _dataPrefijo = el.getAttribute('data-prefijo');

      var _dataPrefijoTipo = el.getAttribute('data-prefijo-tipo');

      var _pattern; // let _max;


      if (_dataPrefijoTipo === 'celular') {
        _pattern = /^(\+?56)?(\s?)(0?9)(\s?)[9876543]\d{7}$/; // _max = 12;
      } else if (_dataPrefijoTipo === 'fijo') {
        _pattern = /\D*([+56]\d[2-9])(\d{4})(\d{4})\D*/; // _max = 12;
      } // if (!el.getAttribute('maxlength')) {
      //   el.setAttribute('maxlength', _max);
      // }


      fln.multipleEventsListeners(el, 'keyup blur paste', function(event) {
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
      fln.multipleEventsListeners(el, 'focus paste', function(event) {
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
            $(el).blur().next().removeClass('focus');
          }, 10);
        } else {
          el.value = _val;
        }
      });
    };

    for (var _i = 0; _i < _a.length; _i++) {
      _f(_a[_i], _i, _a);
    }

    undefined;
  }
};

validarTelefono();
var MENU_RESPONSIVE = {
  menu: document.querySelector('.js-menu-responsive'),
  abrir: document.querySelectorAll('a[href="#menu-responsive"]'),
  abierto: false,
  iconMenu: "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"35\" height=\"35\" viewBox=\"0 0 35 35\"><rect x=\"2.5\" y=\"24.1\" width=\"30\" height=\"1.9\"/><rect x=\"2.5\" y=\"9.1\" width=\"30\" height=\"1.9\"/><rect x=\"2.5\" y=\"16.6\" width=\"30\" height=\"1.9\"/></svg>",
  iconCerrar: '<svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" viewBox="0 0 35 35"><title>  cerrar</title><path d="M29.1 27.7C29.1 27.7 29.1 27.7 29.1 27.7L18.9 17.5 29.1 7.3c0.4-0.4 0.4-1 0-1.4 -0.4-0.4-1-0.4-1.4 0L17.5 16.1 7.3 5.9c-0.4-0.4-1-0.4-1.4 0 -0.4 0.4-0.4 1 0 1.4l10.2 10.2L5.9 27.7c-0.4 0.4-0.4 1 0 1.4 0 0 0 0 0 0 0.2 0.2 0.4 0.3 0.7 0.3 0.3 0 0.5-0.1 0.7-0.3l10.2-10.2 10.2 10.2c0.2 0.2 0.4 0.3 0.7 0.3 0.3 0 0.5-0.1 0.7-0.3C29.5 28.7 29.5 28.1 29.1 27.7z"/></svg>',
  toggle: function toggle() {
    this.abierto = !this.abierto;
    var icon = this.abierto ? this.iconCerrar : this.iconMenu;
    this.menu.classList.toggle('menu-responsive--active');
    var _a2 = this.abrir;

    var _f2 = function _f2(x) {
      x.innerHTML = icon;
    };

    var _r2 = [];

    for (var _i2 = 0; _i2 < _a2.length; _i2++) {
      _r2.push(_f2(_a2[_i2], _i2, _a2));
    }

    _r2;
  },
  cerrar: function cerrar() {
    this.abierto = false;
    var icon = this.iconMenu;
    this.menu.classList.remove('menu-responsive--active');
    var _a3 = this.abrir;

    var _f3 = function _f3(x) {
      x.innerHTML = icon;
    };

    var _r3 = [];

    for (var _i3 = 0; _i3 < _a3.length; _i3++) {
      _r3.push(_f3(_a3[_i3], _i3, _a3));
    }

    _r3;
  }
};
var LOGIN = {
  validar: function validar() {
    validarFormulario('login', {
      texto: 'Debes ingresar ',
      exito: function exito() {
        fln.preloader(1);
        $.ajax({
          method: 'POST',
          url: '/personas/ajax/login.php',
          data: $('#login').serialize(),
          success: function success(data) {
            var _data = JSON.parse(data);

            if (_data.status === 'exito') {
              //alert('exito');
            } else {
              $('.alerta--datos-login').slideDown();
              setTimeout(function() {
                $('.alerta--datos-login').slideUp();
              });
            }

            fln.preloader(0);
          }
        });
      }
    });
  }
};
$('#login #rut').Rut({
  on_error: function on_error() {
    $('.btn--login').addClass('disabled');
    $('.alerta--aviso-login').slideDown();
    setTimeout(function() {
      $('.alerta--aviso-login').slideUp();
    }, 5000);
  },
  on_success: function on_success() {
    $('.btn--login').removeClass('disabled');
  },
  format_on: 'keyup'
});
$('#login #rut-empresa').Rut({
  on_error: function on_error() {
    $('.btn--login').addClass('disabled');
    $('.alerta--aviso-login').slideDown();
    setTimeout(function() {
      $('.alerta--aviso-login').slideUp();
    }, 5000);
  },
  on_success: function on_success() {
    $('.btn--login').removeClass('disabled');
  },
  format_on: 'keyup'
}); // $('#form-declaracion #rut-empresa, #form-nominas #rut-empresa, #form-devolucion #rut-empresa, #form-leasing #rut-empresa, #form-anexo #rut-empresa, #form-autorizaciones #rut-empresa, #form-cartas #rut-empresa').Rut({
//   on_error: function() {
//     $('.btn--primario').addClass('disabled');
//     $('.alerta--aviso-rut').slideDown();
//     setTimeout(function () {
//       $('.alerta--aviso-rut').slideUp();
//     }, 5000);
//   },
//   on_success: function() {
//     $('.btn--primario').removeClass('disabled');
//   },
//   format_on: 'keyup'
// });

var rutvalido = function rutvalido() {
  var _ruts = document.querySelectorAll('.rut');

  if (_ruts.length) {
    $('.rut').Rut({
      on_error: function on_error() {
        $('.btn--primario').addClass('disabled');
        $('form').find('.alerta--aviso-rut').slideDown();
        setTimeout(function() {
          $('.alerta--aviso-rut').slideUp();
        }, 5000);
      },
      on_success: function on_success() {
        $('form').find('.btn--primario').removeClass('disabled');
      },
      format_on: 'keyup'
    });
  }
};

rutvalido();
$('#licencia-medica').Rut({
  on_error: function on_error() {
    $('.btn-licencia-medica').addClass('disabled');
    $('.alerta--aviso-licencias').slideDown();
    setTimeout(function() {
      $('.alerta--aviso-licencias').slideUp();
    }, 5000);
  },
  on_success: function on_success() {
    $('.btn-licencia-medica').removeClass('disabled');
  },
  format_on: 'keyup'
});
var RUT = {
  selector: '.rut',
  keys: [8, 9, 46, 16, 37, 39],
  limpiar: function limpiar(rut) {
    return rut.replace(/^0+|[^0-9kK]+/g, '').toUpperCase();
  },
  formatear: function formatear(rut) {
    rut = this.limpiar(rut);
    var result = rut.slice(-4, -1) + '-' + rut.substr(rut.length - 1);

    for (var i = 4; i < rut.length; i += 3) {
      result = rut.slice(-3 - i, -i) + '.' + result;
    }

    return result;
  },
  validar: function validar(rut) {
    if (typeof rut !== 'string') {
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

    var v = s > 0 ? '' + (s - 1) : 'K';
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
        input.setAttribute('maxlength', 12);
        input.addEventListener('keydown', function(event) {
          // console.log(event.keyCode);
          if (!/[kK0-9\-]/.test(event.key) && _this.keys.indexOf(event.keyCode) < 0) {
            event.preventDefault();
          }

          _this.limpiar(input.value);
        });
        input.addEventListener('keyup', function(event) {
          input.value = input.value.length > 1 ? _this.formatear(input.value) : input.value;
        });
        input.addEventListener('blur', function(event) {
          setTimeout(function() {
            if (_this.validar(input.value)) {
              input.classList.remove('invalido');
              input.classList.add('valido');
            } else {
              input.classList.remove('valido');
              input.classList.add('invalido');
            }
          }, 10);
        });
      };

      for (var _iterator2 = this.selector[Symbol.iterator](), _step2; !(_iteratorNormalCompletion2 = (_step2 = _iterator2.next()).done); _iteratorNormalCompletion2 = true) {
        _loop();
      }
    } catch (err) {
      _didIteratorError2 = true;
      _iteratorError2 = err;
    } finally {
      try {
        if (!_iteratorNormalCompletion2 && _iterator2["return"] != null) {
          _iterator2["return"]();
        }
      } finally {
        if (_didIteratorError2) {
          throw _iteratorError2;
        }
      }
    }
  }
};
RUT.aplicar();
var CHECK_SELECT = {
  items: document.querySelectorAll('.form__check-select'),
  iniciar: function iniciar() {
    if (this.items.length) {
      var _iteratorNormalCompletion3 = true;
      var _didIteratorError3 = false;
      var _iteratorError3 = undefined;

      try {
        var _loop2 = function _loop2() {
          var item = _step3.value;

          var _checkbox = item.querySelector('input[type="checkbox"]');

          var _select = item.querySelector('.form__check-select__icon');

          _checkbox.addEventListener('change', function(event) {
            event.currentTarget.checked ? item.classList.add('form__check-select--checked') : item.classList.remove('form__check-select--checked');
          });

          _select.addEventListener('click', function(event) {
            item.classList.toggle('form__check-select--selected');
            event.stopPropagation();
          });

          document.addEventListener('click', function(event) {
            item.classList.remove('form__check-select--selected');
          });
        };

        for (var _iterator3 = this.items[Symbol.iterator](), _step3; !(_iteratorNormalCompletion3 = (_step3 = _iterator3.next()).done); _iteratorNormalCompletion3 = true) {
          _loop2();
        }
      } catch (err) {
        _didIteratorError3 = true;
        _iteratorError3 = err;
      } finally {
        try {
          if (!_iteratorNormalCompletion3 && _iterator3["return"] != null) {
            _iterator3["return"]();
          }
        } finally {
          if (_didIteratorError3) {
            throw _iteratorError3;
          }
        }
      }
    }
  },
  toggle: function toggle(item, activar) {
    item = document.querySelector(item);
    var event = new Event('change');

    if (item) {
      var _checkbox = item.querySelector('input[type="checkbox"]');

      if (activar) {
        item.classList.add('form__check-select--selected');
        _checkbox.checked = true;
      } else {
        item.classList.remove('form__check-select--selected');
        _checkbox.checked = false;
      }

      _checkbox.dispatchEvent(event);
    }
  }
};
CHECK_SELECT.iniciar();
var SELECCIONAR_CHECKBOXS = {
  event: new Event('change'),
  allActive: false,
  seleccionar: function seleccionar(padre, name, all) {
    padre = document.querySelector(padre);
    name = padre.querySelectorAll(name);

    var _checkboxs = padre.querySelectorAll('input[type="checkbox"]');

    var _iteratorNormalCompletion4 = true;
    var _didIteratorError4 = false;
    var _iteratorError4 = undefined;

    try {
      for (var _iterator4 = _checkboxs[Symbol.iterator](), _step4; !(_iteratorNormalCompletion4 = (_step4 = _iterator4.next()).done); _iteratorNormalCompletion4 = true) {
        var _item = _step4.value;
        _item.checked = false;

        _item.dispatchEvent(this.event);
      }
    } catch (err) {
      _didIteratorError4 = true;
      _iteratorError4 = err;
    } finally {
      try {
        if (!_iteratorNormalCompletion4 && _iterator4["return"] != null) {
          _iterator4["return"]();
        }
      } finally {
        if (_didIteratorError4) {
          throw _iteratorError4;
        }
      }
    }

    if (name.length) {
      var _iteratorNormalCompletion5 = true;
      var _didIteratorError5 = false;
      var _iteratorError5 = undefined;

      try {
        for (var _iterator5 = name[Symbol.iterator](), _step5; !(_iteratorNormalCompletion5 = (_step5 = _iterator5.next()).done); _iteratorNormalCompletion5 = true) {
          var item = _step5.value;
          item.checked = true;
          item.dispatchEvent(this.event);
        }
      } catch (err) {
        _didIteratorError5 = true;
        _iteratorError5 = err;
      } finally {
        try {
          if (!_iteratorNormalCompletion5 && _iterator5["return"] != null) {
            _iterator5["return"]();
          }
        } finally {
          if (_didIteratorError5) {
            throw _iteratorError5;
          }
        }
      }
    }

    if (all) {
      this.allActive = true;
    }
  },
  toggle: function toggle(padre) {
    padre = document.querySelector(padre);

    var _checkboxs = padre.querySelectorAll('input[type="checkbox"]');

    var _iteratorNormalCompletion6 = true;
    var _didIteratorError6 = false;
    var _iteratorError6 = undefined;

    try {
      for (var _iterator6 = _checkboxs[Symbol.iterator](), _step6; !(_iteratorNormalCompletion6 = (_step6 = _iterator6.next()).done); _iteratorNormalCompletion6 = true) {
        var item = _step6.value;
        item.checked = !this.allActive;
        item.dispatchEvent(this.event);
      }
    } catch (err) {
      _didIteratorError6 = true;
      _iteratorError6 = err;
    } finally {
      try {
        if (!_iteratorNormalCompletion6 && _iterator6["return"] != null) {
          _iterator6["return"]();
        }
      } finally {
        if (_didIteratorError6) {
          throw _iteratorError6;
        }
      }
    }

    this.allActive = !this.allActive;
  }
};

var checkboxsOrganizacion = function checkboxsOrganizacion() {
  var _padre = document.querySelector('.js-organizacion');

  var eventChange = new Event('change');

  if (_padre) {
    var _checkboxs = _padre.querySelectorAll('input[type="checkbox"]');

    _checkboxs[0].addEventListener('change', function(event) {
      if (event.currentTarget.checked) {
        var _a4 = _checkboxs;

        var _f4 = function _f4(el, i) {
          if (i > 0) {
            el.disabled = false;
            el.dispatchEvent(eventChange);
            el.parentElement.classList.remove('form__checkbox--disabled');
          }
        };

        for (var _i4 = 0; _i4 < _a4.length; _i4++) {
          _f4(_a4[_i4], _i4, _a4);
        }

        undefined;
      } else {
        var _a5 = _checkboxs;

        var _f5 = function _f5(el, i) {
          if (i > 0) {
            el.disabled = true;
            el.checked = false;
            el.dispatchEvent(eventChange);
            el.parentElement.classList.add('form__checkbox--disabled');
          }
        };

        for (var _i5 = 0; _i5 < _a5.length; _i5++) {
          _f5(_a5[_i5], _i5, _a5);
        }

        undefined;
      }
    });
  }
};

checkboxsOrganizacion();

var owls = function owls() {
  var _accesos = $('.js-accesos');

  fln.responsive(function() {
    if (_width < 992) {
      _accesos.addClass('owl-carousel');

      _accesos.owlCarousel({
        // items: 2,
        loop: true,
        dots: true,
        autoplay: true,
        responsive: {
          0: {
            items: 1
          },
          544: {
            items: 2
          }
        }
      });
    } else {
      if (_accesos.hasClass('owl-carousel')) {
        _accesos.removeClass('owl-carousel');

        _accesos.owlCarousel('destroy');
      }
    }
  });
};

owls();

var mostrarContenido = function mostrarContenido(target, condition) {
  target = document.querySelector(target);

  if (condition) {
    $(target).slideDown('fast');
  } else $(target).slideUp('fast');
};


function scroll_to(el, offset, duracion) {
  var _el = $(el),
    _offset = 0,
    _duracion = 600;

  if (offset) {
    _offset = offset;
  }

  if (duracion) {
    _duracion = duracion;
  }

  setTimeout(function() {
    $("html, body").animate({
      scrollTop: _el.offset().top + _offset
    }, _duracion);
  }, 200);
} // DESPLEGABLES DEMO


$(".desplegable").accordion({
  active: false,
  collapsible: true,
  heightStyle: "content",
  header: ".desplegable__cabecera",
  icons: {
    "header": "fln-abajo",
    "activeHeader": "fln-arriba"
  }
});