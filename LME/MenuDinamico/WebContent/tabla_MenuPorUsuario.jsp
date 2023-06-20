	<input type="hidden" id="msg_MxU" value="${msg}"/>
	<form action="" name="form_adm_MenuPorUsuario" id="form_adm_MenuPorUsuario">
		<input type="hidden" id="op" name="op">
		<input type="hidden" id="rut_user" name="rut_user">
		<input type="hidden" id="concat" name="concat">
	</form>	
	<script>
	$(document).ready(function(){
		//alert("refresco de msg: "+$("#msg_MxU").val());
		var aux=$("#msg_MxU").val();
		if(aux!=null){
			$("#msgAccion_admMxU").text($("#msg_MxU").val());
		}		
		
		//selecciona las opciones que actualmente tiene el usuario en el arbol de opciones.
		var opcionesUsuario =[${arrayJS}];
		for(var i=0;i<opcionesUsuario.length;i++){
			document.getElementById("opcArbol_"+opcionesUsuario[i]).checked = true;
		}
		
	});
	</script>
	
	