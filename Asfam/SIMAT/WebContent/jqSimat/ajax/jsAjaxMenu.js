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
					
					$("#workSpacePeriodo").find("input[name='id']").val(id);
					$("#formActualizar").find("input[name='mes_informacion']").val(element[0]);
					$("#formActualizar").find("input[name='codigo_entidad']").val(element[1]);
				},
				error: function(e){
					alert("error: "+e);
				}
			});
	  }