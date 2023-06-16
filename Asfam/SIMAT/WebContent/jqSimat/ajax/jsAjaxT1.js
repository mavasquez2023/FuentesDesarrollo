//[AJAX t1, mantenedor actualizar].---------------------------------------
function doAjaxPost(id) {	
			//alert("en doAjaxPost: "+id);
			$.ajax({
				type:"POST",
				url:"./AjaxSubmitActualizarReintegros.do",
				data: "id="+id,
				success: function (response){
					//we have the response
					//$("#infoRetornada").html(response);					
					var element = response.split(",");
					
					$("#formActualizar").find("input[name='id']").val(id);
					$("#formActualizar").find("input[name='mes_informacion']").val(element[0]);
					$("#formActualizar").find("input[name='codigo_entidad']").val(element[1]);
					$("#formActualizar").find("input[name='mes_nomina']").val(element[2]);
					$("#formActualizar").find("input[name='run_beneficiario']").val(element[3]);
					$("#formActualizar").find("input[name='nombre_beneficiario']").val(element[4]);			
							
					$("#formActualizar").find("input[name='tipo_subsidio']").val(element[5]);					
					$("#formActualizar").find("input[name='nro_licencia']").val(element[6]);
					$("#formActualizar").find("input[name='rut_empleador']").val(element[7]);
					$("#formActualizar").find("input[name='nombre_empleador']").val(element[8]);
					$("#formActualizar").find("input[name='origen_reintegro']").val(element[9]);
					$("#formActualizar").find("input[name='monto_total_a_reintegrar']").val(element[10]);					
					$("#formActualizar").find("input[name='monto_recuperado']").val(element[11]);
					$("#formActualizar").find("input[name='monto_recuperado_acum']").val(element[12]);
					$("#formActualizar").find("input[name='total_saldo_a_reintegrar']").val(element[13]);
					
				},
				error: function(e){
					alert("error: "+e);
				}
			});
	  }