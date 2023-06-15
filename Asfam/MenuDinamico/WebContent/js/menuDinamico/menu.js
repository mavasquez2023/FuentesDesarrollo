/**
 * Métodos para manejo menú aplicativo.
 */
	function goMenu(codMenu, linkInterno, flgHoja, seguridad, enlace, nodoPadre){
		var path = $("#path").val();
		
		$("#codMenu").val(codMenu);
		$("#flgHoja").val(flgHoja);
		$("#nodoPadre").val(nodoPadre);
		
//		alert('codMenu : '+codMenu+ ',linkInterno : '+linkInterno+
//			', flgHoja : '+flgHoja+', seguridad : '+seguridad+
//			', enlace : '+enlace+', nodoPadre : ' + nodoPadre);
		
		if(flgHoja=='0'){//NO = 0, SI = 1
			$("#op").val("goMenuPrincipal");
			//var op = $("#op").val();
			$("#formMenuUno").submit();
		}else if(linkInterno=='1'){//NO = 0, SI = 1
			url = enlace;
			window.location.href=url;
		}else{
			if(seguridad=='1'){//NO = 0, SI = 1
				window.open(path+enlace,"name").focus();
			}else{
				window.open(enlace);
			}
		}
	}
	
	//Manejo botón Volver de JSP Administración.
	function goBackMenu(nodoPadre){
		url = 'menuPrincipal.do?op=goMenuPrincipal&codMenu='+nodoPadre+'&flgHoja=0';
		window.location.href=url;
	}
	