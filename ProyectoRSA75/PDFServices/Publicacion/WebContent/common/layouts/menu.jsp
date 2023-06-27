<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<tiles:importAttribute />
<ul>
<logic:iterate id="iterateItem" name="items" >
<bean:define id="item" name="iterateItem" type="java.lang.String" scope="page" />
<li><a href="<bean:message key="menu.link.${item}"/>"><bean:message key="title.${item}"/> </a></li>
</logic:iterate>
</ul>