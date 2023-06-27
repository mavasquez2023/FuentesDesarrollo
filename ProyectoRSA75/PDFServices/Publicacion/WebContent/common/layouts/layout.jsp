<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><bean:message key="title.general"/> &gt; <tiles:importAttribute name="title"/><bean:message key="title.${title}"/></title>
<tiles:importAttribute name="css"/>
<bean:define name="css" id="css"/>
<link href="<html:rewrite page="/common/css/${css}" />" media="screen" rel="stylesheet" type="text/css"/>
<script src="<html:rewrite page="/common/js/jquery-1.2.6.js" />" type="text/javascript" language="javascript"></script>
<script type="text/javascript">
    $(document).ready(function(){
        /**
         * Alterna colores en las filas de una tabla.
		 * @global.
         */
        $("tr:even").addClass('tr1');
        $("tr:odd").addClass('tr2');
		});
</script>		
</head>
<body>
<tiles:insert attribute="header" />
<tiles:insert attribute="menu" />
<tiles:insert attribute="main" />
<tiles:insert attribute="footer" />
</body>
</html>
