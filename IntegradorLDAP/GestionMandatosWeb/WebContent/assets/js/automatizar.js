/*
Librería de automatización para jQuery
Autor: Fèline Interactive
URL: http://www.feline.cl
*/

var version = '1.6.4';
var toAviso;
var toFormularios;
var habilitados = [0, 8, 13]; // teclas habilitadas para presionar en todos los campos
var dateHabilitado = verificarDate();
var patrones = [];
patrones['tipo_alfanumerico'] = /\w/;
patrones['tipo_numerico'] = /\d/;
patrones['tipo_rut'] = /[kK0-9\-]/;
patrones['tipo_fecha'] = /[0-9\/]/;
patrones['tipo_email'] = /[a-zA-Z\@\.\-\_0-9]/;
patrones['tipo_telefono'] = patrones['tipo_numerico'];
patrones['tipo_texto'] = /[a-zA-Z \-'áéíóúÁÉÍÓÚñÑäëïöüÄËÏÖÜâêîôûÂÊÎÔÛ]/;
patrones['tipo_nombre'] = patrones['tipo_texto'];
var antipatrones = [];
antipatrones['tipo_alfanumerico'] = /^\w/g;
antipatrones['tipo_numerico'] = /[^0-9]/g;
antipatrones['tipo_rut'] = /[^kK0-9\-]/g;
antipatrones['tipo_fecha'] = /[^0-9\/]/g;
antipatrones['tipo_email'] = /[^a-zA-Z\@\.\-\_0-9]/g;
antipatrones['tipo_telefono'] = antipatrones['tipo_numerico'];
antipatrones['tipo_texto'] = /[^a-zA-Z \-'áéíóúÁÉÍÓÚñÑäëïöüÄËÏÖÜâêîôûÂÊÎÔÛ]/g;
antipatrones['tipo_nombre'] = antipatrones['tipo_texto'];

$(document).ready(function () {
  automatizar_init();
});

function automatizar_init () {
  // readonly para los calendarios si está disponible el type="date"
  if (dateHabilitado) {
    $(':text').each(function (i, el) {
      if ($(el).hasClass('calendario')) {
        $(el).attr('readonly', true);
      }
    });
  }

  $(':text, :password, textarea, select').each(function (i, el) {
    // memorizar datos iniciales
    var valor = '';
    if ($(el).hasClass('requerido')) {
      $(el).data('requerido', true);
    }
    if ($(el).prop('tagName') != 'SELECT') {
      valor = $(el)[0].defaultValue;
    } else {
      valor = $(el).find('option[selected]').val();
      if (typeof valor === 'undefined') {
        valor = $(el).find('option:first').val();
      }
    }
    $(el).data('value', valor);
    setTimeout(function () {
      $(el).val(valor);
    }, 0);

    // clase "activo" para los campos de formularios
    $(el).focus(function () {
      $(this).addClass('activo');
    });
    $(el).blur(function () {
      $(this).removeClass('activo');
      if (
        $(this).prop('tagName') == 'SELECT' &&
        $(this).parent().hasClass('select')
      ) {
        $(this).parent().removeClass('activo');
      }
    });

    // forzar a que se validen los campos que no sean requeridos en caso de tener algún caracter
    if (!$(el).data('requerido')) {
      $(el).blur(function () {
        if ($(this).hasClass('tipo_telefono') && this.value == '+') {
          $(this).removeClass('requerido');
        } else if (this.value) {
          $(this).addClass('requerido');
          aplicarClases(this, validarBasico(this.value));
        } else {
          $(this).removeClass('requerido');
          aplicarClases(this, '');
        }
      });
    }
  });

  $('.select').each(function (i, el) {
    $(el).click(function () {
      $(this).addClass('activo');
    });
  });

  // automatización para inputs type="password"
  $(':password').each(function (i, el) {
    $(el).data('tipo', 'tipo_password');
    aplicarEventos(el);
  });

  // automatización para inputs de texto y textarea
  $(':text, textarea').each(function (i, el) {
    // placeholder para IE6+
    if (!soportado('textarea', 'placeholder')) {
      $(el).data('placeholder', $(el).attr('placeholder'));
      $(el).val($(el).data('placeholder'));
      $(el).focus(function () {
        if ($(this).val() == $(el).data('placeholder')) {
          $(this).val('');
          $(this).removeClass('sin_modificar');
        }
      });
      $(el).blur(function () {
        if ($(this).val() == '') {
          $(this).val($(el).data('placeholder'));
          $(this).addClass('sin_modificar');
        }
      });
      $(el).addClass('sin_modificar');
    }

    // bloqueo de teclas según patrones
    for (key in patrones) {
      if ($(el).hasClass(key)) {
        // patrones
        $(el).data('tipo', key);
        $(el).keypress(function (event) {
          return verificarPatron(event, this);
        });
        $(el).bind('paste', function (event) {
          event.preventDefault();
          if (typeof window.clipboardData !== 'undefined') {
            var data = window.clipboardData.getData('Text');
          } else {
            // para IE
            var data = event.originalEvent.clipboardData.getData('Text');
          }
          var pasteValue = data.replace(antipatrones[$(el).data('tipo')], '');
          if ($(el).attr('maxlength')) {
            pasteValue = pasteValue.substr(
              0,
              parseInt($(el).attr('maxlength'))
            );
          }
          $(el).val(pasteValue).blur().focus();
        });
        // eventos
        aplicarEventos(el);
      }
    }

    // validar requeridos básicos
    var tiposNoConsiderados = [
      'tipo_email',
      'tipo_rut',
      'tipo_telefono',
      'tipo_nombre'
    ];
    var validar = 1;
    if ($(el).hasClass('requerido')) {
      for (i in tiposNoConsiderados) {
        if ($(el).hasClass(tiposNoConsiderados[i])) {
          validar = 0;
        }
      }
      if (validar) {
        $(el).blur(function () {
          this.value = this.value.trim();
          aplicarClases(this, validarBasico(this.value));
        });
      }
    }
  });

  // aplicar requerido a los selects
  $('select').each(function (i, el) {
    if ($(el).hasClass('requerido')) {
      $(el).val('');
    }
    $(this).change(function () {
      if ($(this).parent().hasClass('select')) {
        if (this.value) {
          aplicarClases($(this).parent(), validarSelect(this.value));
        } else {
          aplicarClases($(this).parent(), '');
        }
      }
      if (this.value) {
        aplicarClases(this, validarSelect(this.value));
      } else {
        aplicarClases(this, '');
      }
    });
    $(this).blur(function () {
      if ($(this).parent().hasClass('select')) {
        if (this.value) {
          aplicarClases($(this).parent(), validarSelect(this.value));
        } else {
          aplicarClases($(this).parent(), '');
        }
      }
      if (this.value) {
        aplicarClases(this, validarSelect(this.value));
      } else {
        aplicarClases(this, '');
      }
    });
  });

  // habilitar maxlength para los textarea
  if (!soportado('textarea', 'maxLength')) {
    $('textarea').each(function (i, el) {
      var maxlength = $(el).attr('maxlength');
      $(el).keypress(function (event) {
        var code = event.which;
        if ($.inArray(code, habilitados) >= 0) {
          return true;
        }
        if ($(this).val().length >= maxlength) {
          return false;
        }
      });
    });
  }

  // hovers
  $('.hover').each(function (i, el) {
    $(el).mouseover(function () {
      $(this).addClass('hover');
    });
    $(el).mouseout(function () {
      $(this).removeClass('hover');
    });
    $(el).removeClass('hover');
  });

  // créditos
  console.log(
    'Se cargó la librería de automatización de Fèline Interactive v' +
      version +
      '.'
  );
}

function aplicarEventos (el) {
  var tipo = $(el).data('tipo');
  if (tipo == 'tipo_password') {
    $(el).attr('maxlength', 32);
    $(el).blur(function () {
      var minLength = 4;
      if (typeof $(el).data('minlength') === 'number') {
        minLength = $(el).data('minlength');
      }
      aplicarClases(this, validarMinimo(this.value, minLength));
    });
  }
  if (tipo == 'tipo_nombre') {
    $(el).attr('maxlength', 30);
    $(el).blur(function () {
      this.value = this.value.trim();
      if (
        $(this).data('requerido') ||
        (this.value && !$(this).data('placeholder')) ||
        ($(this).data('placeholder') &&
          this.value != $(this).data('placeholder'))
      ) {
        aplicarClases(this, validarNombre(this.value));
      } else {
        aplicarClases(this, '');
      }
    });
  }
  if (tipo == 'tipo_rut') {
    if (!$(el).attr('maxlength')) $(el).attr('maxlength', 10);
    $(el).blur(function () {
      if (
        $(this).data('requerido') ||
        (this.value && !$(this).data('placeholder')) ||
        ($(this).data('placeholder') &&
          this.value != $(this).data('placeholder'))
      ) {
        formatearRut(this);
        aplicarClases(this, validarRut(this.value));
      } else {
        aplicarClases(this, '');
      }
    });
    $(el).focus(function () {
      this.value = this.value.replace(/[^\dkK]/g, '');
    });
  }
  if (tipo == 'tipo_telefono') {
    $(el).attr('maxlength', 14);
    $(el).blur(function () {
      if (
        !$(this).data('requerido') &&
        this.value == $(this).data('valueRequerido')
      ) {
        aplicarClases(this, '');
      } else {
        aplicarClases(this, validarTelefono(this.value));
      }
    });
    bloquearCampo(el, '+');
  }
  if (tipo == 'tipo_email') {
    $(el).attr('maxlength', 255);
    $(el).attr('type', 'email');
    $(el).blur(function () {
      if (
        $(this).data('requerido') ||
        (this.value && !$(this).data('placeholder')) ||
        ($(this).data('placeholder') &&
          this.value != $(this).data('placeholder'))
      ) {
        aplicarClases(this, validarEmail(this.value));
      } else {
        aplicarClases(this, '');
      }
    });
  }
}

function obtenerPosicionCursor (oField) {
  var iCaretPos = 0;
  if (document.selection) {
    // IE Support
    oField.focus();
    var oSel = document.selection.createRange();
    oSel.moveStart('character', -oField.value.length);
    iCaretPos = oSel.text.length;
  } else if (oField.selectionStart || oField.selectionStart == '0') {
    // Firefox support
    iCaretPos = oField.selectionStart;
  }
  return iCaretPos;
}

function bloquearCampo (el, val) {
  $(el).data('valueRequerido', val);
  if ($(el).val()) {
  } else if ($(el).data('placeholder')) {
    $(el).val($(el).data('placeholder'));
  } else {
    $(el).val('');
  }

  $(el).focus(function (event) {
    if (!this.value) {
      this.value = $(this).data('valueRequerido');
      setTimeout(
        function () {
  this.setSelectionRange(this.value.length, this.value.length);
}.bind(this),
        0
      );
    }
  });
  $(el).click(function (event) {
    if (this.value == $(this).data('valueRequerido')) {
      this.setSelectionRange(this.value.length, this.value.length);
    } else if (this.selectionStart < $(this).data('valueRequerido').length) {
      this.setSelectionRange(
        $(this).data('valueRequerido').length,
        $(this).data('valueRequerido').length
      );
    }
  });
  $(el).keypress(function (event) {
    var code = event.which;
    if (this.selectionStart === 0 && this.selectionEnd === this.value.length) {
      // todo seleccionado
      return verificarPatron(event, el);
    } else if (obtenerPosicionCursor($(el)[0]) == 0) {
      if ($.inArray(code, habilitados) < 0) {
        return false;
      }
    }
  });
  $(el).keydown(function (event) {
    var code = event.which;
    var continuar = true;
    if (obtenerPosicionCursor($(el)[0]) == val.length) {
      // bloquear el backspace
      if (code === 8) {
        continuar = false;
      }
    } else if (obtenerPosicionCursor($(el)[0]) < val.length) {
      // bloquear el delete
      if (
        this.selectionStart === 0 &&
        this.selectionEnd === this.value.length
      ) {
      } else if (code === 46) {
        continuar = false;
      }
    }

    if (continuar) {
      $(this).data('textoAntes', this.value);
    } else {
      return false;
    }
  });
  $(el).keyup(function (event) {
    if (this.value == $(this).data('valueRequerido')) {
      this.setSelectionRange(this.value.length, this.value.length);
    } else if (this.selectionStart < $(this).data('valueRequerido').length) {
      var code = event.keyCode || event.which;
      if (code != 9 && code != 16) {
        // no considerar la tecla tab ni la tecla shift
        this.setSelectionRange(
          $(this).data('valueRequerido').length,
          $(this).data('valueRequerido').length
        );
      } else {
        this.setSelectionRange(this.value.length, this.value.length);
      }
    }

    var nuevoValor = this.value;
    if (this.value.indexOf($(this).data('valueRequerido')) != 0) {
      if (!this.value || this.value == $(this).data('valueRequerido')) {
        this.value = $(this).data('valueRequerido');
      } else if (nuevoValor.length == 1) {
        this.value = $(this).data('valueRequerido') + nuevoValor;
      } else if (
        $(this).data('textoAntes').indexOf($(this).data('valueRequerido')) == 0
      ) {
        this.value = $(this).data('textoAntes') + this.value;
      } else {
        this.value =
          $(this).data('valueRequerido') + $(this).data('textoAntes');
      }
    }
  });
  $(el).blur(function () {
    if (this.value == $(this).data('valueRequerido')) {
      if ($(el).data('placeholder')) {
        $(el).val($(el).data('placeholder'));
      } else {
        $(el).val('');
      }
    }
  });
}

function aplicarClases (el, clase) {
  var clases = ['valido', 'invalido'];
  for (i in clases) {
    $(el).removeClass(clases[i]);
    var campo_error = $('#error_' + $(el).attr('id'));
    if (campo_error.length > 0) {
      campo_error.hide('fast');
    }
  }
  if (clase) {
    $(el).addClass(clase);
    var campo_error = $('#error_' + $(el).attr('id'));
    if (campo_error.length > 0 && clase == 'invalido') {
      campo_error.show('fast');
    } else {
      campo_error.hide('fast');
    }
  }
}

function verificarPatron (event, el) {
  var tipo = $(el).data('tipo');
  var code = event.which;
  if (event.ctrlKey && code == 99) {
    return true;
  } // permitir control+c
  if (event.ctrlKey && code == 118) {
    return true;
  } // permitir control+v
  if ($.inArray(code, habilitados) >= 0) {
    return true;
  }
  return patrones[tipo].test(String.fromCharCode(code));
}

function soportado (element, attribute) {
  var test = document.createElement(element);
  if (attribute in test) {
    return true;
  } else {
    return false;
  }
}

function verificarDate () {
  var input = document.createElement('input');
  input.setAttribute('type', 'date');

  var notADateValue = 'not-a-date';
  input.setAttribute('value', notADateValue);

  return input.value !== notADateValue && /Mobi/.test(navigator.userAgent);
}

function formatearRut (campo) {
  var str = new String(campo.value);
  str = str.replace(/[^\dkK]/g, '');
  var dv = str.substr(str.length - 1, 1);
  str = str.substr(0, str.length - 1);
  if (dv.length && str.length) {
    var n = [];
    str = str.split('');
    count = Math.ceil(str.length / 3) - 1;
    for (i = str.length - 1; i >= 0; i -= 3) {
      num1 = str[i] ? str[i] : '';
      num2 = str[i - 1] ? str[i - 1] : '';
      num3 = str[i - 2] ? str[i - 2] : '';
      n[count] = num3 + num2 + num1;
      count--;
    }
    str = n.join('.') + '-' + dv;
    $(campo).val(str);
  }
}
function validarRut (rut) {
  var estado = 'invalido';
  if (rut.length < 8) {
    return estado;
  }
  if (
    rut.substr(rut.length - 1, 1) != 'K' &&
    rut.substr(rut.length - 1, 1) != 'k'
  ) {
    var dv = rut.substr(rut.length - 1, 1);
    rut = rut.substr(0, rut.length - 1);
  } else {
    dv = 'K';
  }
  rut = rut.replace(/\D/g, '');

  var largo = rut.length;
  var suma = 0;
  var mult = 2;
  largo--;

  while (largo >= 0) {
    suma = suma + rut.charAt(largo) * mult;
    if (mult > 6) {
      mult = 2;
    } else {
      mult++;
    }
    largo--;
  }

  var resto = suma % 11;
  var digito = 11 - resto;

  if (digito == 10) {
    digito = 'K';
  }
  if (digito == 11) {
    digito = 0;
  }

  if (!rut || !dv) {
    estado = 'invalido';
  } else if (digito != dv) {
    estado = 'invalido';
  } else {
    estado = 'valido';
  }

  return estado;
}

function validarTelefono (telefono) {
  var estado = 'invalido';
  if (telefono.length >= 10) {
    estado = 'valido';
  }
  return estado;
}

function validarNombre (nombre) {
  nombre = nombre.trim();
  var estado = 'invalido';
  var nombres = nombre.split(' ');
  if (
    nombres.length > 1 &&
    nombres[0] != '' &&
    nombres[1] != '' &&
    nombre.length > 4
  ) {
    estado = 'valido';
  }
  return estado;
}

function validarEmail (email) {
  var estado = 'valido';
  var patron = /^(.+\@.+\..+)$/;
  if (!patron.test(email)) {
    estado = 'invalido';
  }
  return estado;
}

function validarSelect (valor) {
  valor = valor.trim();
  if (valor) {
    return 'valido';
  } else {
    return 'invalido';
  }
}

function validarBasico (valor) {
  valor = valor.trim();
  if (valor.length) {
    return 'valido';
  } else {
    return 'invalido';
  }
}

function validarMinimo (valor, minimo) {
  valor = valor.trim();
  if (valor.length >= minimo) {
    return 'valido';
  } else {
    return 'invalido';
  }
}

function formatearErrores (errores, texto, separador) {
  if (!texto) {
    texto = 'Por favor ingrese su ';
  }
  if (!separador) {
    separador = 'y';
  }
  for (i = 0; i < errores.length; i++) {
    texto += errores[i];
    if (i < errores.length - 2) {
      texto += ', ';
    } else if (i == errores.length - 2) {
      texto += ' ' + separador + ' ';
    }
  }
  return texto;
}

/* FUNCIONES ON DEMAND ************/
function validarFormulario (id_formulario, variables) {
  if (typeof variables !== 'object') {
    variables = {};
  }
  if (typeof variables.action === 'undefined') {
    variables.action = '';
  }
  if (typeof variables.texto === 'undefined') {
    variables.texto = '';
  }
  if (typeof variables.separador === 'undefined') {
    variables.separador = '';
  }
  if (typeof variables.otrosCampos === 'undefined') {
    variables.otrosCampos = {};
  }
  if (typeof variables.exito !== 'function') {
    variables.exito = function () {
      $('#' + id_formulario).attr('action', variables.action);
      $('#' + id_formulario).submit();
    };
  }
  if (typeof variables.error !== 'function') {
    variables.error = function () {
      if ($('#errores_' + id_formulario).length > 0) {
        $('#errores_' + id_formulario).html(errores_formateados);
        $('#errores_' + id_formulario).slideDown('fast');
        if (toFormularios) {
          clearTimeout(toFormularios);
        }
        toFormularios = setTimeout(function () {
          $('#errores_' + id_formulario).slideUp('fast');
        }, 5000);
      } else {
        avisar(errores_formateados);
      }
    };
  }

  var errores = [];
  $('#' + id_formulario + ' .requerido:visible').each(function (i, el) {
    $(el).blur();
    var valido = 1;
    if (
      $(el).attr('type') == 'password' ||
      $(el).attr('type') == 'date' ||
      $(el).attr('type') == 'email' ||
      $(el).attr('type') == 'text' ||
      el.tagName.toLowerCase() == 'select' ||
      el.tagName.toLowerCase() == 'textarea'
    ) {
      if (!$(el).hasClass('valido')) {
        valido = 0;
      }
    } else if (
      $(el).attr('type') == 'checkbox' ||
      $(el).attr('type') == 'radio'
    ) {
      if (!el.checked) {
        valido = 0;
      } else {
        aplicarClases(el, 'valido');
      }
    }
    if (!valido) {
      errores.push($("label[for='" + $(el).attr('id') + "']").html());
      aplicarClases(el, 'invalido');
    }
  });
  for (i in variables.otrosCampos) {
    if (variables.otrosCampos[i][0] == false) {
      errores.push(i);
      aplicarClases(variables.otrosCampos[i][1], 'invalido');
    } else {
      aplicarClases(variables.otrosCampos[i][1], 'valido');
    }
  }
  if (errores.length) {
    var errores_formateados = formatearErrores(
      errores,
      variables.texto,
      variables.separador
    );
    variables.error();
  } else {
    variables.exito();
  }
}

function limpiarFormulario (id_formulario) {
  $('#' + id_formulario)[0].reset();
  $(
    '#' +
      id_formulario +
      ' :text, #' +
      id_formulario +
      ' textarea, #' +
      id_formulario +
      ' select'
  ).each(function (i, el) {
  $(el).removeClass('invalido');
  $(el).removeClass('valido');
  if ($(el).data('value')) {
    $(el).val($(el).data('value'));
  }
  if ($(el).data('placeHolder')) {
    $(el).addClass('sin_modificar');
    $(el).val($(el).data('placeHolder'));
  }
  if ('#error_' + $(el).attr('id').length) {
    $('#error_' + $(el).attr('id')).hide('fast');
  }
});
  if ($('#errores_' + id_formulario).length) {
    $('#errores_' + id_formulario).slideUp('fast');
  }
}

function subir (tiempo) {
  if (typeof tiempo !== 'number') {
    tiempo = 800;
  }
  $('html, body').animate(
    {
      scrollTop: 0
    },
    tiempo
  );
  return false;
}

function paralaxes (items, variables) {
  if (typeof variables !== 'object') {
    variables = {};
  }
  if (typeof variables.factor !== 'number') {
    variables.factor = 5;
  }
  if (typeof variables.offset !== 'number') {
    variables.offset = 0;
  }
  items.each(function (i, el) {
    var img = $(el).find('img');
    $(el).css({
      position: 'relative',
      overflow: 'hidden'
    });
    var distancia = $(document).scrollTop() - $(el).offset().top;
    if (!$(el).data('factor')) {
      $(el).data('factor', variables.factor);
    }
    img.css({
      position: 'absolute',
      top: distancia / $(el).data('factor') + variables.offset,
      left: 0
    });
  });
  $(document).scroll(function () {
    items.each(function (i, el) {
      var img = $(el).find('img');
      var factor = variables.factor;
      if ($(el).parent().data('factor')) {
        factor = $(el).parent().data('factor');
      }
      var distancia = $(document).scrollTop() - $(el).offset().top;
      img.css({ top: distancia / $(el).data('factor') + variables.offset });
    });
  });
}

function popup (items, variables) {
  if (typeof variables !== 'object') {
    variables = {};
  }
  if (typeof variables.width !== 'number') {
    variables.width = 640;
  }
  if (typeof variables.height !== 'number') {
    variables.height = 390;
  }
  $(items).each(function (i, el) {
    $(el).click(function () {
      var url = $(this).attr('href');
      var left = screen.width / 2 - variables.width / 2;
      var top = screen.height / 2 - variables.height / 2;
      window.open(
        url,
        '',
        'width=' +
          variables.width +
          ',height=' +
          variables.height +
          ',location=no,menubar=no,scrollbars=no,directories=no,status=no,titlebar=no,toolbar=no,resizable=no,top=' +
          top +
          ',left=' +
          left
      );
      return false;
    });
  });
}

function avisar (texto, tiempo) {
  sinTiempo = 0;
  if (tiempo == 0) {
    sinTiempo = 1;
  } else if (typeof tiempo === 'undefined') {
    tiempo = 3000;
  }
  if (toAviso) {
    clearTimeout(toAviso);
    if ($('#aviso').length) {
      $('#aviso').fadeOut('fast');
    }
  }
  if (!$('#aviso').length) {
    $('body').append(
      $('<div/>', {
  id: 'aviso',
  style: 'display:none;'
})
    );
  }
  $('#aviso').html(texto);
  $('#aviso').fadeIn('fast');
  if (!sinTiempo) {
    toAviso = setTimeout(function () {
      $('#aviso').fadeOut('fast');
    }, tiempo);
  }
}

function avisar2 (texto, tiempo) {
	  sinTiempo = 0;
	  if (tiempo == 0) {
	    sinTiempo = 1;
	  } else if (typeof tiempo === 'undefined') {
	    tiempo = 3000;
	  }
	  if (toAviso) {
	    clearTimeout(toAviso);
	    if ($('#aviso2').length) {
	      $('#aviso2').fadeOut('fast');
	    }
	  }
	  if (!$('#aviso2').length) {
	    $('body').append(
	      $('<div/>', {
	  id: 'aviso2',
	  style: 'display:none;'
	})
	    );
	  }
	  $('#aviso2').html(texto);
	  $('#aviso2').fadeIn('fast');
	  if (!sinTiempo) {
	    toAviso = setTimeout(function () {
	      $('#aviso2').fadeOut('fast');
	    }, tiempo);
	  }
	}
/**********************************/
