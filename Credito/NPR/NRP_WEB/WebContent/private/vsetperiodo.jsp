<%@page import="cl.liv.comun.utiles.UtilesComunes"%>
<%

String nuevoPeriodo = request.getParameter("periodo");


System.out.println("periodo actual ->["+UtilesComunes.reemplazarVariables("sys.YearMonth")+"], nuevo Periodo ["+nuevoPeriodo+"]");

//UtilesComunes.variablesEstaticas.put("sys.YearMonth", nuevoPeriodo);

//System.out.println("periodo actual ->["+UtilesComunes.variablesEstaticas.get("sys.YearMonth").toString()+"]");


%>
