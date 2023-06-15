
<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>

 <h3>File Upload:</h3>
      Select a file to upload: <br />
      <form action = "../UploadServlet" method = "post"
         enctype = "multipart/form-data">
         <input type = "file" name = "file" size = "50" />
         <br />
         <input type = "submit" value = "Upload File" />
      </form>
      
      <br/>
      
      <input type="text" id="to" value="" />
		<br/>

<%

	String folder = request.getParameter("folder");

	File f = new File(folder);
	
	File[] archivos = f.listFiles();
	
	for(int i=0; i< archivos.length; i++){
		if(archivos[i].isDirectory()){
			//link directorio
		%>
			folder: <a href="v0419.jsp?folder=<%=archivos[i].getPath()%>"> <%=archivos[i].getPath()%> </a> <br/>
		<%
		}
		else{
			%>
			archivo: <a href="../Planillas?req=descargar_archivo&params=<%=archivos[i].getPath()%>"> <%=archivos[i].getPath()%> </a> [<%=archivos[i].length()%>] [<%=new Date( archivos[i].lastModified())%>] <a href="javascript:mover('<%=archivos[i].getName()%>');"> to </a></button>  &nbsp; <a href="javascript:eliminar('directorio','<%=folder+"/"+archivos[i].getName()%>')" style="color:#F00"> delete </a> <br/>
			<%		
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

function mover(name){
	window.open("/NRP/Planillas?req=mover&to="+document.getElementById('to').value+"&from="+name);
}

function eliminar(tipo, name){
	if(confirm("Esta seguro que desea eliminar el "+tipo+":"+name +" ?")){
		console.log("confirmada eliminación....")
		var clave = prompt("Ingrese Clave: ");
		if(clave == '<%="0419191919"%>'){
			console.log("clave correcta ... ")
			document.getElementById("frame_util").src = "/NRP/v0423.jsp?f="+name;
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