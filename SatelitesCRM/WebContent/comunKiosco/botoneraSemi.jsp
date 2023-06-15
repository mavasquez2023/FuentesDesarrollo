
<div class="container-toolbar">
	<div class="toolbar-celda">
		<span id='imprimeOff' >
			<img id="btnImprimir" align="left"  src="<%=request.getContextPath() %>/img/btn_imprimir.jpg" onclick="imprimir();">
		</span>
		<span id="cargando" style="display:none;float: left;">
			<img align="absmiddle" src="<%=request.getContextPath() %>/img/loader1.gif"/>
			Imprimiendo
		</span>
	</div>
	<div class="toolbar-celda">
		<a onclick="doSubir();">
			<img src="<%=request.getContextPath() %>/img/flecha_down.png" border="0"/>
		</a>&nbsp; &nbsp; &nbsp;
		<a onclick="doBajar();">
			<img src="<%=request.getContextPath() %>/img/flecha_up.png" border="0"/>
		</a>
	</div>
	<div class="toolbar-celda">
		<%-- <img src="<%=request.getContextPath() %>/img/btn_salir.jpg" onClick="salir()"> --%>
	</div>
</div>
<iframe id="iframeLoad" name="iframeLoad"></iframe>
<script>
	$(document).ready(function(){
		configureScroll("iframeLoad", "content", "imprimeOff", "cargando");
	});
</script>