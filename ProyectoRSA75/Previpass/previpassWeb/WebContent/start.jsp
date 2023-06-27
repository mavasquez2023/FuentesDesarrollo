<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form name="dispatch" action="<c:url value="/menu.jsp" />" target="_top" />
<script type="text/javascript">
	document.dispatch.submit();
</script>
