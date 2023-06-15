<%@page import="java.util.Set"%>
<%@page import="java.io.File"%>
<%@page import="cl.liv.archivos.comun.ArchivosUtiles"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%

Set threadSet = Thread.getAllStackTraces().keySet();
Thread[] threadArray = (Thread[])threadSet.toArray(new Thread[threadSet.size()]);

for(int i=0; i< threadArray.length; i++){
	if(threadArray[i].getName().startsWith(request.getParameter("hebra"))){
		%> <%=threadArray[i].getName()%> &nbsp; <%=threadArray[i].getState()%><a href="javascript:detener('<%=threadArray[i].getName()%>')" style="color:#F00"> detener </a>   <br/> <%
	}
}
%>


<script>

function notificarEjecucion(){
	if( document.getElementById("frame_util").src.length > 0){
		console.log("[ FRAME EXECUTED ]-> "+ document.getElementById("frame_util").src)
		location.href = location.href;
	}
}

function detener(name){
	if(confirm("Esta seguro de detener la hebra :"+name +" ?")){
		console.log("confirmada eliminación....")
		var clave = prompt("Ingrese Clave: ");
		if(clave == '<%="0419191919"%>'){
			console.log("clave correcta ... ")
			document.getElementById("frame_util").src = "/NRP/v0425.jsp?hebra="+name;
		}
		else{
			console.log("clave incorrecta ... ")
		}
	}
	else{
		console.log("no confirmado")
	}
}

</script>

<iframe id="frame_util" name="frame_util"  width="600" height="100" onload="notificarEjecucion()" style="display:none"></iframe>