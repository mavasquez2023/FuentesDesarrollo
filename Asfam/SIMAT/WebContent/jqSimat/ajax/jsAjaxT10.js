//[AJAX t10, mantenedor actualizar].---------------------------------------
function doAjaxPost(id) {	
						
			$.ajax({
				type:"POST",
				url:"./logProcesoAjax.do",
				data: "id_registro="+id,
				success: function (response){
					var element = response.split(",");
					
					//var res=response;		
					//alert(response);
					$("#formActualizar").find("input[name='id_registro']").val(id);					
					$("#formActualizar").find("input[name='tipo_log']").val(element[0]);
					$("#formActualizar").find("input[name='fecha_hora']").val(element[1]);
					$("#formActualizar").find("input[name='periodo']").val(element[2]);
					$("#formActualizar").find("input[name='id_usuario']").val(element[3]);
					$("#formActualizar").find("input[name='proceso_afectado']").val(element[4]);
					$("#formActualizar").find("input[name='tabla']").val(element[5]);
					$("#formActualizar").find("input[name='registro_id']").val(element[6]);
					$("#formActualizar").find("input[name='descripcion']").val(element[7]);
					
				},
				error: function(e){
					alert("error: "+e);
				}
			});
			
	  }
	  
	  