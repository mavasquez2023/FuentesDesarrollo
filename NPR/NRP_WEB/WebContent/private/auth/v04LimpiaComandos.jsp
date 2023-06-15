
<%@page import="java.util.ArrayList"%>
<%@page import="cl.jfactory.planillas.service.helper.GeneradorNominasHelper"%>
<%
System.out.println("Comandos antes->"+ GeneradorNominasHelper.comandosGeneracion.size());
GeneradorNominasHelper.comandosGeneracion.clear();

System.out.println("Comandos despues->"+ GeneradorNominasHelper.comandosGeneracion.size());

%>