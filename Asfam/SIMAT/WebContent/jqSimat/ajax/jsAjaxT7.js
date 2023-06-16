//[AJAX t7, mantenedor actualizar].---------------------------------------
function doAjaxPost(id) {	
			//alert("en doAjaxPost: "+id);
						
			$.ajax({
				type:"POST",
				url:"./AjaxSubmitActualizardatosLicCob.do",
				data: "id="+id,
				success: function (response){
					var element = response.split(",");
					
					//var res=response;		
					//alert(res);
					$("#formActualizar").find("input[name='id']").val(id);
					
					$("#formActualizar").find("input[name='mes_informacion']").val(element[0]);
					$("#formActualizar").find("input[name='codigo_entidad']").val(element[1]);
					$("#formActualizar").find("input[name='nro_licencia']").val(element[2]);
					$("#formActualizar").find("input[name='run_beneficiario']").val(element[3]);
					$("#formActualizar").find("input[name='nombre_beneficiario']").val(element[4]);
					$("#formActualizar").find("input[name='edad']").val(element[5]);
					$("#formActualizar").find("input[name='sexo']").val(element[6]);
					$("#formActualizar").find("input[name='fecha_emision_licencia']").val(element[7]);
					$("#formActualizar").find("input[name='fecha_inicio_reposo']").val(element[8]);
					$("#formActualizar").find("input[name='fecha_termino_reposo']").val(element[9]);
					$("#formActualizar").find("input[name='nro_dias_licencia']").val(element[10]);
					$("#formActualizar").find("input[name='num_dias_licencia_autorizados']").val(element[11]);
					$("#formActualizar").find("input[name='run_menor_enfermo']").val(element[12]);
					$("#formActualizar").find("input[name='nombre_menor_enfermo']").val(element[13]);
					$("#formActualizar").find("input[name='fecha_nac_menor_enfermo']").val(element[14]);
					$("#formActualizar").find("input[name='cod_tipo_licencia']").val(element[15]);
					$("#formActualizar").find("input[name='cod_comuna_beneficiario']").val(element[16]);
					$("#formActualizar").find("input[name='run_profesional']").val(element[17]);
					$("#formActualizar").find("input[name='nombre_profesional']").val(element[18]);
					$("#formActualizar").find("input[name='registro_colegio_profesional']").val(element[19]);
					$("#formActualizar").find("input[name='codigo_diagnostico']").val(element[20]);
					$("#formActualizar").find("input[name='rut_empleador']").val(element[21]);
					$("#formActualizar").find("input[name='nombre_empleador']").val(element[22]);
					$("#formActualizar").find("input[name='calidad_trabajador']").val(element[23]);
					
				},
				error: function(e){
					alert("error: "+e);
				}
			});
			
	  }
	  