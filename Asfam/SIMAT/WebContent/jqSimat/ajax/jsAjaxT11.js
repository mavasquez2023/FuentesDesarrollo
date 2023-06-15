//[AJAX t11, mantenedor actualizar].---------------------------------------
function doAjaxPost(id) {	
			//alert("en doAjaxPost: "+id);
						
			$.ajax({
				type:"POST",
				url:"./tablaHomologacionAjax.do",
				data: "id_registro="+id,
				success: function (response){
					//we have the response
					//$("#infoRetornada").html(response);
					
					var element = response.split(",");
					
					//alert("res:+"response);
					$("#formActualizar").find("input[name='id_registro']").val(id);
					$("#formActualizar").find("input[name='clasificacion']").val(element[0]);
					$("#formActualizar").find("textarea[name='descripcion']").val(element[1]);
					$("#formActualizar").find("input[name='codigo_suceso']").val(element[2]);
					$("#formActualizar").find("input[name='codigo_la']").val(element[3]);
					
				},
				error: function(e){
					alert("error: "+e);
				}
			});
			
	  }
	  
	  