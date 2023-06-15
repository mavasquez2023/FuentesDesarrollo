//[AJAX t10, Detalle Log].---------------------------------------
function doAjaxPost(id) {	
						
			$.ajax({
				type:"POST",
				url:"./logProcesoAjax.do",
				data: "id_registro="+id,
				success: function (response){
					var element = response.split(",");
					
					//var res=response;		
					//alert(response);
					$("#formDetalle").find("input[name='id_registro']").val(id);					
					$("#formDetalle").find("input[name='tipo_log']").val(element[0]);
					$("#formDetalle").find("input[name='fecha_hora']").val(element[1]);
					$("#formDetalle").find("input[name='periodo']").val(element[2]);
					$("#formDetalle").find("input[name='id_usuario']").val(element[3]);
					$("#formDetalle").find("input[name='proceso_afectado']").val(element[4]);
					$("#formDetalle").find("input[name='tabla']").val(element[5]);
					$("#formDetalle").find("input[name='registro_id']").val(element[6]);
					$("#formDetalle").find("textarea[name='descripcion']").val(element[7]);
					
				},
				error: function(e){
					alert("error: "+e);
				}
			});
			
	  }
	  
	  