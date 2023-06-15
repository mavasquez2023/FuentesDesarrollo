
<%


int memoriaLibre = ((int)Runtime.getRuntime().freeMemory()/(1024*1024)) ;
int memoriaTotal = ((int)Runtime.getRuntime().totalMemory()/(1024*1024)) ;
String memoria = "memoria total|libre [ "+memoriaTotal+"mb |  "+memoriaLibre+"mb ]";
%>

memoria -> <%=memoria%>