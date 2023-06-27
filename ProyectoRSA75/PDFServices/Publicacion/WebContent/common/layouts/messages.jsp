<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1><bean:message key="h1.informacion"/></h1>
<h2>

<c:choose>
   <c:when test="${not empty requestScope['message.info']}">
   <bean:define id="msg" name="message.info" />
   <bean:message key="${msg}"/></c:when>
   <c:otherwise></c:otherwise>
</c:choose>
<BR><BR>
<input type="submit" value="Volver" onclick="window.location='<html:rewrite page="" />/home.do';" />
<BR>
</h2>