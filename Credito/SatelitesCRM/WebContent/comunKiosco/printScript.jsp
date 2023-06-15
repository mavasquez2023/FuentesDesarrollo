<!-- Dejar al fin del body -->
<OBJECT ID="WB" WIDTH="0" HEIGHT="0" CLASSID="clsid:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>
<SCRIPT LANGUAGE="VBScript">
Sub window_onunload
	On Error Resume Next
	Set WB = nothing
End Sub
Sub vbPrintPage2(frame)
	frame.focus()
	On Error Resume Next
	WB.ExecWB 6, 2, 3, 0
End Sub
</SCRIPT>
<script type="text/javascript">
	try{
		vbPrintPage2(window);
	}catch(err){
		alert('Se produjo un error al imprimir, por favor intente nuevamente');
	}
	parent.document.getElementById('cargando').style.display = "none";
	parent.document.getElementById('imprimeOff').style.display = "";
</script>