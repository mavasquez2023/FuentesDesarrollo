<%-- 
    Document   : menues
    Created on : 10-04-2012, 12:06:36 PM
    Author     : desajee
--%>


<%
int estaOpcionMnu;
String impSelected = " class='selected' ";
String impMenu = "";

estaOpcion = "" + (String)session.getAttribute("md2_opcion");
sOpcionMnu = "" + (String)request.getParameter("md2_opcionMnu");

if (estaOpcion.equals("cns")) {
    try { estaOpcionMnu = Integer.parseInt(sOpcionMnu); } catch (Exception ex) {estaOpcionMnu=1;}%>
<div id='menu'><a href="javascript:doOpcionMenu(1)" <%= (estaOpcionMnu==1 ? impSelected:impMenu) %>>Estado Licencia M&eacute;dica</a></div><div class="lineamenu"></div>
<div id='menu'><a href="javascript:doOpcionMenu(2)" <%= (estaOpcionMnu==2 ? impSelected:impMenu) %>>Cartola de Ahorro</a></div><div class="lineamenu"></div>
<div id='menu'><a href="javascript:doOpcionMenu(3)" <%= (estaOpcionMnu==3 ? impSelected:impMenu) %>>Cr&eacute;ditos Vigentes</a></div><div class="lineamenu"></div>
<div id='menu'><a href="javascript:doOpcionMenu(4)" <%= (estaOpcionMnu==4 ? impSelected:impMenu) %>>Liquidaci&oacute;n de Reembolsos</a></div>

<% } else {
  try { estaOpcionMnu = Integer.parseInt(sOpcionMnu); } catch (Exception ex) {estaOpcionMnu=5;}
%>

<div id='menu'><a href="javascript:doOpcionMenu(5)" <%= (estaOpcionMnu==5 ? impSelected:impMenu) %>>Asignaci&oacute;n Familiar</a></div><div class="lineamenu"></div>
<div id='menu'><a href="javascript:doOpcionMenu(6)" <%= (estaOpcionMnu==6 ? impSelected:impMenu) %>>Licencias M&eacute;dicas</a></div><div class="lineamenu"></div>
<div id='menu'><a href="javascript:doOpcionMenu(7)" <%= (estaOpcionMnu==7 ? impSelected:impMenu) %>>Deuda Vigente</a></div><div class="lineamenu"></div>
<div id='menu'><a href="javascript:doOpcionMenu(8)" <%= (estaOpcionMnu==8 ? impSelected:impMenu) %>>Afiliaci&oacute;n</a></div>


<% }
%>