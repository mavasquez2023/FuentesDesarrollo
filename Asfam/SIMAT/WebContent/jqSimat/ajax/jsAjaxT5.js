//[AJAX t5, mantenedor actualizar].---------------------------------------
function doAjaxPost(id) {	
			//alert("en doAjaxPost: "+id);
						
			$.ajax({
				type:"POST",
				url:"./AjaxSubmitActualizarControlDocu.do",
				data: "id="+id,
				success: function (response){
					var element = response.split(",");
					
					//var res=response;		
					//alert(res);
					
					$("#formActualizar").find("input[name='mes_informacion']").val(element[0]);
					$("#formActualizar").find("input[name='codigo_entidad']").val(element[1]);
					$("#formActualizar").find("input[name='tipo_subsidio']").val(element[2]);
					$("#formActualizar").find("input[name='rut_empleador']").val(element[3]);
					$("#formActualizar").find("input[name='nombre_empleador']").val(element[4]);
					
					$("#formActualizar").find("input[name='run_beneficiario']").val(element[5]);					
					$("#formActualizar").find("input[name='nombre_beneficiario']").val(element[6]);
					$("#formActualizar").find("input[name='mod_pago']").val(element[7]);
					$("#formActualizar").find("input[name='serie_documento']").val(element[8]);
					$("#formActualizar").find("input[name='num_documento']").val(element[9]);
					
					$("#formActualizar").find("input[name='fecha_emision_documento']").val(element[10]);
					$("#formActualizar").find("input[name='monto_documento']").val(element[11]);
					$("#formActualizar").find("input[name='codigo_banco']").val(element[12]);					
					$("#formActualizar").find("input[name='estado_documento']").val(element[13]);
					$("#formActualizar").find("input[name='fecha_cambio_estado']").val(element[14]);
					
					$("#formActualizar").find("input[name='id']").val(id);
					
				},
				error: function(e){
					alert("error: "+e);
				}
			});
			
	  }
	  