//[AJAX t6, mantenedor actualizar].---------------------------------------
function doAjaxPost(id) {	
			//alert("en doAjaxPost: "+id);
						
			$.ajax({
				type:"POST",
				url:"./AjaxSubmitActualizarDocsRevalReem.do",
				data: "id="+id,
				success: function (response){
					var element = response.split(",");
					
					//var res=response;		
					//alert(res);
					$("#formActualizar").find("input[name='mes_informacion']").val(element[0]);
					$("#formActualizar").find("input[name='codigo_entidad']").val(element[1]);
					$("#formActualizar").find("input[name='tiposubsidio']").val(element[2]);
					$("#formActualizar").find("input[name='mod_pago_original']").val(element[3]);
					$("#formActualizar").find("input[name='codigo_banco_original']").val(element[4]);
					$("#formActualizar").find("input[name='serie_documento_original']").val(element[5]);
					$("#formActualizar").find("input[name='num_documento_original']").val(element[6]);
					$("#formActualizar").find("input[name='fecha_emision_documento_original']").val(element[7]);
					$("#formActualizar").find("input[name='monto_documento_original']").val(element[8]);
					$("#formActualizar").find("input[name='estado_documento_original']").val(element[9]);
					$("#formActualizar").find("input[name='modo_pago_nuevo']").val(element[10]);
					$("#formActualizar").find("input[name='codigo_banco_nuevo']").val(element[11]);
					$("#formActualizar").find("input[name='serie_documento_nuevo']").val(element[12]);
					$("#formActualizar").find("input[name='num_documento_nuevo']").val(element[13]);
					$("#formActualizar").find("input[name='fecha_emision_documento_nuevo']").val(element[14]);
					$("#formActualizar").find("input[name='monto_documento_nuevo']").val(element[15]);
					
					$("#formActualizar").find("input[name='id']").val(id);
					
				},
				error: function(e){
					alert("error: "+e);
				}
			});
			
	  }
	  