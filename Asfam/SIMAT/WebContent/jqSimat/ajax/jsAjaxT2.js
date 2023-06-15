//[AJAX t2, mantenedor actualizar].---------------------------------------
function doAjaxPost(id) {	
			//alert("en doAjaxPost: "+id);
						
			$.ajax({
				type:"POST",
				url:"./AjaxSubmitActualizarSubsPrePostNm.do",
				data: "id="+id,
				success: function (response){
					//we have the response
					//$("#infoRetornada").html(response);
					//alert(response);
					var element = response.split(",");
					
					$("#formActualizar").find("input[name='id']").val(id);
					$("#formActualizar").find("input[name='mes_informacion']").val(element[0]);
					$("#formActualizar").find("input[name='codigo_entidad']").val(element[1]);
					
					$("#formActualizar").find("input[name='agencia_entidad']").val(element[2]);
					$("#formActualizar").find("input[name='rut_empleador']").val(element[3]);
					$("#formActualizar").find("input[name='nombre_empleador']").val(element[4]);
					$("#formActualizar").find("input[name='run_beneficiario']").val(element[5]);
					$("#formActualizar").find("input[name='nombre_beneficiario']").val(element[6]);
					$("#formActualizar").find("input[name='nro_licencia']").val(element[7]);
					$("#formActualizar").find("input[name='codigo_diagnostico']").val(element[8]);
					$("#formActualizar").find("input[name='vinculo_beneficiario_menor']").val(element[9]);
					$("#formActualizar").find("input[name='actividad_laboral_trabajador']").val(element[10]);
					$("#formActualizar").find("input[name='cod_comuna_beneficiario']").val(element[11]);
					
					$("#formActualizar").find("input[name='nro_resolucion']").val(element[12]);
					$("#formActualizar").find("input[name='extension_postnatal']").val(element[13]);
					$("#formActualizar").find("input[name='nro_nacimientos']").val(element[14]);
					$("#formActualizar").find("input[name='num_dias_licencia_autorizados']").val(element[15]);
					$("#formActualizar").find("input[name='fecha_inicio_reposo']").val(element[16]);
					$("#formActualizar").find("input[name='fecha_termino_reposo']").val(element[17]);
					$("#formActualizar").find("input[name='num_dias_sub_pagadados']").val(element[18]);
					$("#formActualizar").find("input[name='tipo_de_pago']").val(element[19]);
					$("#formActualizar").find("input[name='monto_sub_dfl44_1978']").val(element[20]);
					$("#formActualizar").find("input[name='monto_sub_pagado']").val(element[21]);
					
					$("#formActualizar").find("input[name='tipo_emision']").val(element[22]);
					$("#formActualizar").find("input[name='mes_nomina']").val(element[23]);
					$("#formActualizar").find("input[name='mod_pago']").val(element[24]);
					$("#formActualizar").find("input[name='serie_documento']").val(element[25]);
					$("#formActualizar").find("input[name='num_documento']").val(element[26]);
					$("#formActualizar").find("input[name='fecha_emision_documento']").val(element[27]);
					$("#formActualizar").find("input[name='monto_documento']").val(element[28]);
					$("#formActualizar").find("input[name='codigo_banco']").val(element[29]);
					$("#formActualizar").find("input[name='cta_cte']").val(element[30]);
					$("#formActualizar").find("input[name='causal_emision']").val(element[31]);
					
					$("#formActualizar").find("input[name='num_dias_cotiz_pagados']").val(element[32]);
					$("#formActualizar").find("input[name='monto_renum_imponible']").val(element[33]);
					$("#formActualizar").find("input[name='monto_fp']").val(element[34]);
					$("#formActualizar").find("input[name='monto_salud']").val(element[35]);
					$("#formActualizar").find("input[name='monto_salud_ad']").val(element[36]);
					$("#formActualizar").find("input[name='monto_desahucio']").val(element[37]);
					$("#formActualizar").find("input[name='monto_cotiz_fu']").val(element[38]);
					$("#formActualizar").find("input[name='monto_cotiz_sc']").val(element[39]);
					$("#formActualizar").find("input[name='entidad_previsional']").val(element[40]);
					$("#formActualizar").find("input[name='subsidio_iniciado']").val(element[41]);
					$("#formActualizar").find("input[name='indicador_convenio']").val(element[42]);

				},
				error: function(e){
					alert("error: "+e);
				}
			});
			
	  }
	  