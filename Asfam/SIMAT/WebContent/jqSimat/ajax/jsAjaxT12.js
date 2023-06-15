//[AJAX t12,no usar].---------------------------------------
function doAjaxPost(id) {	
			//alert("en doAjaxPost: "+id);
						
			$.ajax({
				type:"POST",
				url:"./informeFinancieroAjax.do",
				data: "id_inf_fin="+id,
				success: function (response){
					var element = response.split(",");
					
					//var res=response;		
					//alert(res);
					
					$("#formActualizar").find("input[name='periodo']").val(element[0]);
					$("#formActualizar").find("input[name='a_1']").val(element[1]);
					$("#formActualizar").find("input[name='a_2']").val(element[2]);
					$("#formActualizar").find("input[name='a_3_1']").val(element[3]);
					$("#formActualizar").find("input[name='a_3_2']").val(element[4]);
					$("#formActualizar").find("input[name='a_4_1']").val(element[5]);					
					$("#formActualizar").find("input[name='a_4_2']").val(element[6]);
					$("#formActualizar").find("input[name='c_1']").val(element[7]);
					$("#formActualizar").find("input[name='c_2']").val(element[8]);
					$("#formActualizar").find("input[name='c_3']").val(element[9]);
					
					$("#formActualizar").find("input[name='c_4']").val(element[10]);
					$("#formActualizar").find("input[name='c_5']").val(element[11]);					
					$("#formActualizar").find("input[name='c_6_1']").val(element[12]);
					$("#formActualizar").find("input[name='c_6_2']").val(element[13]);
					$("#formActualizar").find("input[name='c_6_3']").val(element[14]);
					$("#formActualizar").find("input[name='c_6_4']").val(element[15]);
					$("#formActualizar").find("input[name='c_6_5']").val(element[16]);
					$("#formActualizar").find("input[name='c_7_1']").val(element[17]);
					$("#formActualizar").find("input[name='c_7_2']").val(element[18]);
					$("#formActualizar").find("input[name='c_7_3']").val(element[19]);
					
					$("#formActualizar").find("input[name='c_7_4']").val(element[20]);
					$("#formActualizar").find("input[name='c_7_5']").val(element[21]);
					$("#formActualizar").find("input[name='c_8_1']").val(element[22]);
					$("#formActualizar").find("input[name='c_8_2']").val(element[23]);
					$("#formActualizar").find("input[name='c_8_3']").val(element[24]);
					$("#formActualizar").find("input[name='c_8_4']").val(element[25]);
					$("#formActualizar").find("input[name='c_8_5']").val(element[26]);
					$("#formActualizar").find("input[name='c_9_1']").val(element[27]);
					$("#formActualizar").find("input[name='c_9_2']").val(element[28]);
					$("#formActualizar").find("input[name='c_9_3']").val(element[29]);
					
					$("#formActualizar").find("input[name='c_9_4']").val(element[30]);
					$("#formActualizar").find("input[name='c_9_5']").val(element[31]);
					$("#formActualizar").find("input[name='e_1_1']").val(element[32]);
					$("#formActualizar").find("input[name='e_1_2']").val(element[33]);
					$("#formActualizar").find("input[name='e_1_3']").val(element[34]);
					$("#formActualizar").find("input[name='e_1_4']").val(element[35]);
					$("#formActualizar").find("input[name='e_1_5']").val(element[36]);
					$("#formActualizar").find("input[name='f_1_1']").val(element[37]);
					$("#formActualizar").find("input[name='f_1_2']").val(element[38]);
					$("#formActualizar").find("input[name='f_1_3']").val(element[39]);
					
					$("#formActualizar").find("input[name='f_1_4']").val(element[40]);
					$("#formActualizar").find("input[name='f_1_5']").val(element[41]);
					$("#formActualizar").find("input[name='g_1_1']").val(element[42]);
					$("#formActualizar").find("input[name='g_1_2']").val(element[43]);
					$("#formActualizar").find("input[name='g_1_3']").val(element[44]);
					$("#formActualizar").find("input[name='g_1_4']").val(element[45]);
					
					$("#formActualizar").find("input[name='id_inf_fin']").val(id);
					
				},
				error: function(e){
					alert("error: "+e);
				}
			});
			
	  }
	  