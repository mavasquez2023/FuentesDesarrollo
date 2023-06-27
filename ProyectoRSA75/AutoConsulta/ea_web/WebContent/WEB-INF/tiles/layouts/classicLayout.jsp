<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="catch-control" content="no-cache" />
<meta http-equiv="Content-Style-Type" content="text/css" />

<jsp:useBean id="software" class="cl.araucana.core.util.ApplicationBean" scope="application" />

<meta name="APPLICATION_ORGANIZATION_NAME" content="<%= software.getOrganizationName() %>" />
<meta name="APPLICATION_NAME" content="<%= software.getName() %>" />
<meta name="APPLICATION_TITLE" content="<%= software.getTitle() %>" />
<meta name="APPLICATION_VERSION" content="<%= software.getVersion() %>" />
<meta name="APPLICATION_RELEASE_DATE" content="<%= software.getReleaseDate() %>" />
<meta name="APPLICATION_COPYRIGHT" content="<%= software.getCopyright() %>" />

<link href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/theme/estilos.css" rel="stylesheet" type="text/css" />

<title><tiles:getAsString name="title" /></title>

<script language="JavaScript" type="text/JavaScript">
function MM_preloadImages() { //v3.0
  var d=document; 
  if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
</script>

</head>
<body onLoad="MM_preloadImages('<%=request.getContextPath()%>/images/botonera_izq/opciones_simulacion_f2.gif','<%=request.getContextPath()%>/images/botonera_izq/solicitud_apertura_f2.gif','<%=request.getContextPath()%>/images/botonera_izq/cartola_f2.gif','<%=request.getContextPath()%>/images/botonera_izq/descto_planilla_f2.gif','<%=request.getContextPath()%>/images/botonera_izq/solicitud_giro_f2.gif','<%=request.getContextPath()%>/images/botonera_izq/consulta_estado_f2.gif','<%=request.getContextPath()%>/images/botonera_izq/simulacion_leasing_f2.gif','<%=request.getContextPath()%>/images/botonera_izq/abertura_operacion_f2.gif')" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

	<table border="0" width="800" cellpadding="0" cellspacing="0">
		<tbody>
		
			<!-- Header -->
			<tr>
				<td colspan="3"><tiles:get name="header" flush="true" /></td>
			</tr>

			<!-- Second Row -->
			<tr>
				<td>
					<table style="width : 100%; height : 460px;" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
							
								<!-- Menu -->
								<td style="width: 160px; vertical-align: top;">
									<tiles:get name="menu" />
								</td>

								<td style="width: 1px; " class="verticalgray">
									<html:img page="/img/c.gif" alt="" style="width : 1px; height: 1px;" />
								</td>

								<!-- Body -->
								<td style="width : 640px; vertical-align: top;">
									<tiles:get name="body" />
								</td>

								<td style="width: 1px; " class="verticalgray">
									<html:img page="/img/c.gif" alt="" style="width : 1px; height: 1px;" />
								</td>
							
							</tr>
						</tbody>
					</table>
				</td>
			</tr>

			<!-- Footer -->
			<tr>
				<td colspan="3"><tiles:get name="footer" /></td>
			</tr>
		</tbody>
	</table>

</body>
</HTML>
