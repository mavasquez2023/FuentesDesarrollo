var isIE = false;

function ajustarIE() {
	if (navigator.userAgent.indexOf("MSIE") > -1) {
		isIE = true;
		$("input").css("width", "155px");
		$(".select_normal").css("width", "130px");
		$(".select_grande").css("width", "400px");

		$("#codigo_telefono").css("width", "30px");
		$("#telefono").css("width", "100px");

	}
}