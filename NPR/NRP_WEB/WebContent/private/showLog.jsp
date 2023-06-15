
<script src="bootstrap-3.3.7-dist/js/jquery.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<%@page import="cl.liv.archivos.comun.txt.ShowLogFile"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<% 

	final String logPath = request.getParameter("path");

	File f = new File(logPath);
	if(f.exists()){
	
		%>
		EXISTE...
		<table id="tabla_logs"></table>
		<% 
		
		new Thread(new Runnable() {
			
			public void run() {
				
				while(true){
					System.out.println("leyendo...");
					try{
						
						
						final ArrayList<String> lineas = ShowLogFile.getLines(logPath, 100);
						for (final String linea : lineas) {
							System.out.println("->"+ linea);
							final String lineaHTML = linea + "<br/>";
							%>
							<span>test</span>
							
							<%
							
						}
						Thread.sleep(5000);
						
						Thread.sleep(1000);
					}catch(Exception e){
						
					}
				}
				
			}
		}).start();
		
	}
	else{
		%>NO EXISTE !<% 
	}
	%>