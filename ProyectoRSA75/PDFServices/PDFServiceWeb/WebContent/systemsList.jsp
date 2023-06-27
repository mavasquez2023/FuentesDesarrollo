<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="css/Interna_Araucana.css" rel="stylesheet" type="text/css" />
<title>La Araucana C.C.A.F. - Sistemas PDFServiceWeb</title>
 
</head>  
<script> 
var u = document.location.href;

var xPerfil = u.substring(u.indexOf('=') + 1, u.indexOf('&'));

var nSistemas = u.substring(u.indexOf('s=') + 2);

//     document.write('            <td width="65" class="textospie">ID '+xPerfil+' : </td>');


function validaNroSis() {
   if (nSistemas == 1) {
      location.href = "detalle_sistema_1.html?perfil="+xPerfil+"&nSistemas="+nSistemas;
   }
}

</script>

<body onLoad="javascript:validaNroSis();">

	<%@ include file="header.jspf" %>
	<center>
	<logic:present name="username" scope="session">
		<table width="767" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%">
					<%@ include file="contextUser.jspf" %>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td class="contextUser" width="5%">
							Contexto:
						</td>
						<td class="contextNav" align="left">
							<a href="<%= cPath %>/LoadConfUser.do" >Sistemas</a> 
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<%@ include file="barTop.jspf" %>
			</td>
			
		</tr>
	</table> 
	<form name="listaSistemasForm" >
			<br/>
			<table width="767" border="1" align="center" cellpadding="0"
				cellspacing="0" bordercolor="#B4D6D8">
				<tr>
					<td>
						<table width="100%" border="0" align="left" cellpadding="0"
							cellspacing="0" bordercolor="#99C8CA">
							<tr> 
								<td class="barra_tablas" colspan="4" align="center">LISTA DE SISTEMAS</td>
							</tr>
							<tr>
								<td width="15" class="barra_tablas">Sistema</td>
								<td width="400" class="barra_tablas">Descripci&oacute;n</td>
								<td width="80" class="barra_tablas" >Cnt Scripts</td>
							</tr>  
				              <c:forEach var="sistemaAsociado" items="${listaSistemasAsociados}" >
				              	<tr>
					                <td width="15" class="textos-formcolor2">
						                <a href="LoadScripts.do?sysID=${sistemaAsociado.identificador}"> 
         							                ${sistemaAsociado.identificador}
										</a>
									</td>
				                <td width="400" class="textos-formcolor2">${sistemaAsociado.descripcion}</td>
				                <td width="80" class="textos-formcolor2" align="center">${sistemaAsociado.cntScripts}</td>
				              </tr>
						              
						     </c:forEach> 
						           
				
						</table>
					</td>
				</tr>
			</table>
		
		</form>
			</center>
	</logic:present>
	
	<logic:notPresent name="username" scope="session">
		<% response.sendRedirect(request.getContextPath()+"/index.jsp"); %> 
	</logic:notPresent>
	</body>
</html:html>
