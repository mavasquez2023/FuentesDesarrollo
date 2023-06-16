var isIE = false;

function ajustarIE() {
	if (navigator.userAgent.indexOf("MSIE") > -1) {
		isIE = true;
		$("input").css("width", "155px");
		$(".select_normal").css("width", "130px");
		$(".select_grande").css("width", "400px");

		$("#codigo_telefono_fijo_admin").css("width", "30px");
		$("#telefono_fijo_admin").css("width", "100px");
		$("#codigo_fax_admin").css("width", "30px");
		$("#fax_admin").css("width", "100px");

		$("#fax_casa_matriz").css("width", "100px");
		$("#codigo_fax_casa_matriz").css("width", "30px");

		$("#codigo_telefono_casa_matriz").css("width", "30px");
		$("#telefono_fijo_casa_matriz").css("width", "100px");
	}
}