$("#refreshimg").click(function() {
	$.post('/IngresoNuevaEmpresa/Captcha');
	$("#captchaimage").load('/IngresoNuevaEmpresa/Captcha');
	return false;	
});