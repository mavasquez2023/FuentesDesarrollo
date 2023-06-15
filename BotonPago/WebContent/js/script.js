$('form').validate({
		onChange : true,
		description : {
			clave : {
	            required : '<div class="error">* Ingrese clave actual</div>',
	            pattern: '<div class="error">* Ingrese Clave actual válida</div>'
	        },
	        claveNueva : {
	            required : '<div class="error">* Ingrese clave nueva</div>',
	            pattern: '<div class="error">* Ingrese Clave nueva válida</div>',
	            conditional : '<div class="error">* La clave nueva debe ser diferente de la actual</div>'
	        },
	        confirmaClave : {
	            pattern: '<div class="error">* Las claves ingresadas no coinciden</div>',
	            conditional : '<div class="error">* Las claves ingresadas no coinciden</div>'
	        }
	    }
});

jQuery.validateExtend({
	clave : {
        required : true,
        pattern : /^[0-9]{4}$/
    },
    claveNueva : {
        required : true,
        pattern : /^[0-9]{4}$/,
        conditional: function(value){ return value != $("#claveInicial").val()
        }
    },
    confirmaClave : {
        pattern : /^[0-9]{4}$/,
        conditional : function(value) {
            return value == $("#claveNueva").val();
        }
    }
});

function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

//onkeypress="return isNumberKey(event)"