<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<logic:present name="ea_user_profile" scope="session">
	<logic:forward name="home" />
</logic:present>

<logic:notPresent name="ea_user_profile" scope="session">
	<logic:forward name="logout" />
</logic:notPresent>

