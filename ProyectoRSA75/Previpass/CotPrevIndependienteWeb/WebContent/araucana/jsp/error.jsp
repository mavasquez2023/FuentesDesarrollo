<%@ include file="/html/comun/taglibs.jsp" %>
<br />
<div class="titulo"><strong>Ha ocurrido un error, por favor int&eacute;ntelo nuevamente.</strong></div>
<br />
<html:messages id="msg" message="true">
	<div class="msgExito">${msg}</div>
</html:messages>
<br />			
<form action="<c:url value="/ListarNominas.do" />" onsubmit="javascript: if (window.opener) {window.opener.location.href=this.action;window.close(); return false;}">
	<input type="submit" class="btn3" value="Volver"/>
</form>