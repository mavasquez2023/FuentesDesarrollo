<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Documento sin t&iacute;tulo</title>
<link href="a.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.Estilo2 {color: #000000}
-->
</style>
<script language="javaScript"> 
function enviar(){
	var codigo = document.getElementById('codigo').value;
	if (codigo != '')
		window.location = "/CotPrevIndependienteWeb/BarCode.do?codigo="+codigo;
	else
	return false;
}
</script>
</head>

<body>
<label>
<input type="text" name="textfield" id="codigo"/>
</label>
<label>
<input type="button" name="button" value="Enviar" onclick="enviar();"/>
</label>
</body>
</html>
