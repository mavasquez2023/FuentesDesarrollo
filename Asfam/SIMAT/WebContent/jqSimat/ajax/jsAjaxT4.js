//[AJAX t4, mantenedor actualizar].---------------------------------------
function doAjaxPost(id) {	
			//alert("en doAjaxPost: "+id);						
			$.ajax({
				type:"POST",
				url:"./AjaxSubmitActualizarSubsTscVig.do",
				data: "idSubsTscVig="+id,
				success: function (response){
					var element = response.split(",");
					
					//var res=response;		
					//alert(res);
					
					$("#formActualizar").find("input[name='mes_informacion']").val(element[0]);
					$("#formActualizar").find("input[name='codigo_entidad']").val(element[1]);
					$("#formActualizar").find("input[name='agencia_entidad']").val(element[2]);
					$("#formActualizar").find("input[name='run_beneficiaria']").val(element[3]);
					$("#formActualizar").find("input[name='nombre_beneficiaria']").val(element[4]);
					
					$("#formActualizar").find("input[name='edad']").val(element[5]);					
					$("#formActualizar").find("input[name='cod_comuna_beneficiaria']").val(element[6]);
					$("#formActualizar").find("input[name='actividad_laboral_trabajador']").val(element[7]);
					$("#formActualizar").find("input[name='nro_licencia']").val(element[8]);
					$("#formActualizar").find("input[name='codigo_diagnostico']").val(element[9]);
					
					$("#formActualizar").find("input[name='nro_nacimientos']").val(element[10]);
					$("#formActualizar").find("input[name='fecha_nacimiento']").val(element[11]);
					$("#formActualizar").find("input[name='num_dias_autorizados']").val(element[12]);					
					$("#formActualizar").find("input[name='fecha_inicio_subsidio']").val(element[13]);
					$("#formActualizar").find("input[name='fecha_termino_subsidio']").val(element[14]);
					
					$("#formActualizar").find("input[name='num_dias_subsidio_pagado']").val(element[15]);
					$("#formActualizar").find("input[name='monto_sub_pagado']").val(element[16]);
					$("#formActualizar").find("input[name='tipo_emision']").val(element[17]);
					$("#formActualizar").find("input[name='mes_nomina']").val(element[18]);
					$("#formActualizar").find("input[name='mod_pago']").val(element[19]);
					
					$("#formActualizar").find("input[name='serie_documento']").val(element[20]);					
					$("#formActualizar").find("input[name='num_documento']").val(element[21]);
					$("#formActualizar").find("input[name='fecha_emision_documento']").val(element[22]);
					$("#formActualizar").find("input[name='monto_documento']").val(element[23]);
					$("#formActualizar").find("input[name='codigo_banco']").val(element[24]);
					
					$("#formActualizar").find("input[name='cta_cte']").val(element[25]);					
					$("#formActualizar").find("input[name='causal_emision']").val(element[26]);
					$("#formActualizar").find("input[name='num_dias_cotiz_pagados']").val(element[27]);
					$("#formActualizar").find("input[name='monto_fp']").val(element[28]);
					$("#formActualizar").find("input[name='monto_salud']").val(element[29]);
					
					$("#formActualizar").find("input[name='monto_cotiz']").val(element[30]);					
					$("#formActualizar").find("input[name='entidad_previsional']").val(element[31]);
					$("#formActualizar").find("input[name='subsidio_iniciado']").val(element[32]);
					
					$("#formActualizar").find("input[name='idSubsTscVig']").val(id);
					
				},
				error: function(e){
					alert("error: "+e);
				}
			});
			
	  }
	  