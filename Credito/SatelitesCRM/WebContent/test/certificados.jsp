<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<body>

<iframe src="laImpresion.jsp" width="100%" height="480" frameborder="0" allowtransparency="true" scrolling="no" name="iData" id="iData" onload="this.contentWindow.document.documentElement.scrollTop=0"></iframe> 
	
	<div class="container_12">
		<div class="grid_12 omega">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="30%">
					<span id='spIdImprimeOff' style=''><a href="#" onclick="vbPrintPage(window.iData);">boton imprimir... </a> </span> <span id='spIdImprimeOn' style='text-decoration: blink; display: none;'><img src="img/loader1.gif" /> &nbsp;Imprimiendo...</span>
					</td>
					<td align="center"><a href="#" onclick="doBajar()"><img src="img/flecha_down.png" border="0" /> </a> &nbsp; &nbsp; &nbsp;<a href="#" onclick="doSubir()"><img src="img/flecha_up.png"
							border="0" /> </a></td>
					<td align="center"><a href="logout.do">boton salir </a></td>
				</tr>
			</table>
		</div>
		<div style="clear: left;"></div>
	</div>
	<OBJECT ID="WB" WIDTH="0" HEIGHT="0" CLASSID="clsid:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>



	<script>
var yCor = 00;
function doBajar() {
	var obj = document.getElementById("iData");
	yCor += 100;
	try {
		obj.contentWindow.scrollTo(0,yCor);
	} catch (ex) {
	   alert(ex);
	}
}

function doSubir() {
	var obj = document.getElementById("iData");
	yCor -= 100;
	try {
		obj.contentWindow.scrollTo(0,yCor);
	} catch (ex) {
	   alert(ex);
	}
}
function btnPrint(opc) {
var obj1 = document.getElementById("spIdImprimeOff");
var obj2 = document.getElementById("spIdImprimeOn");

  if (opc==0) {
    obj1.style.display = "none";
    obj2.style.display = "block";
  } else {
    obj1.style.display = "block";
    obj2.style.display = "none";
  }
}
</script>
</body>
</html>
