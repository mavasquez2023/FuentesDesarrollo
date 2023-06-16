//[AJAX t9, mantenedor actualizar].---------------------------------------
function doAjaxPost(id) {	
			//alert("en doAjaxPost: "+id);
						
			$.ajax({
				type:"POST",
				url:"./usuariosAjax.do",
				data: "id="+id,
				success: function (response){
					var element = response.split(",");
					
					//var res=response;		
					//alert(res);
					$("#formActualizar").find("input[name='id']").val(element[0]);
					$("#formActualizar").find("input[name='nombre_usuario']").val(element[1]);
					$("#formActualizar").find("input[name='acceso']").val("total");
					$("#formActualizar").find("input[name='ultima_coneccion']").val(element[3]);
					
					
				},
				error: function(e){
					alert("error: "+e);
				}
			});
	  }
	  