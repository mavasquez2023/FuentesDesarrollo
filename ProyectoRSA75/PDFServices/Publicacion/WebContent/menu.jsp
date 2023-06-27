<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML//EN">
<!-- <html>

<body bgcolor="#F0FCFF" 
	leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
</body>
</html>-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Previpass - Planillas</title>
<link href="common/css/estilo_formularios.css" rel="stylesheet" type="text/css" />
<link href="common/css/collapsible_menu.css" rel="stylesheet" type="text/css" />
<link href="common/css/grid_960.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="common/js/jquery-1.2.6.js"></script>
<script language="JavaScript" type="text/javascript" src="common/js/collapsible_menu.js"></script>
<script type="text/javascript">
<%String usuario=session.getAttribute("TipoUsuario").toString(); %>;
<%String entidades=session.getAttribute("Entidades").toString(); %>;
<%String esAdm=session.getAttribute("esAdm").toString(); %>;


function swapAll(id, imgId)
{
	obj = document.getElementById(id);
	img = document.getElementById(imgId);
    if ( obj.style.display == '' )
    {
		obj.style.display='none';
		img.src   = '/img/ico_mas.gif';
		img.alt   = "Expandir";
		img.title = "Expandir";
	} else
	{
		obj.style.display = '';
		img.src   = '/img/ico_menos.gif';
		img.alt   = "Contraer";
		img.title = "Contraer";
	}
	}
	function scrollPosi(){
		
	}
	
</script>
</head>

<body>
<table width="181" border="0" cellspacing="0" cellpadding="0">
   <tr>
    <td><table width="180" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td valign="top" style="padding-top:25px;">
        <div class="menu">
        <ul class="menu_collapse" id="menu1">
        <%if (usuario.equals("")){ %>
        	<li><font size=2>Usuario No autorizado!!</font></li>
        <%} %>
        <%if (entidades.indexOf("CAJA")>-1 || usuario.equals("empresa")){ %>
    	<li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaCAJA.jsp';window.parent.frames['LIST'].location='fblank.htm'">Planillas Cajas</a></li>
    	<%} %>
    	<%if (entidades.indexOf("AFP")>-1 || usuario.equals("empresa")){ %>
    	<li><a href="javascript:scrollPosi();">Planillas AFPs</a>
	        <ul>
            	<li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaAFP.jsp';window.parent.frames['LIST'].location='fblank.htm';">Declaraci&oacute;n y Pago</a></li>
                <li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaAFPTP.jsp';window.parent.frames['LIST'].location='fblank.htm'">Trabajos Pesados</a></li>
                <li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaCotAPV.jsp';window.parent.frames['LIST'].location='fblank.htm'">Afiliado Voluntario</a></li>
                <li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaAFPDNP.jsp';window.parent.frames['LIST'].location='fblank.htm'">Declaraci&oacute;n y no Pago</a></li>
                <li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaAFPSIL.jsp';window.parent.frames['LIST'].location='fblank.htm'">Subsidio Incap. Laboral</a></li>
            </ul>        
        </li>
       <%} %> 
       <%if (entidades.indexOf("APV")>-1 || usuario.equals("empresa")){ %>
    	<li><a href="javascript:scrollPosi();">Planillas APV</a>
        	<ul>
                <li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaAPV.jsp';window.parent.frames['LIST'].location='fblank.htm'">Planillas APV</a></li>
                <li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaCotAPC.jsp';window.parent.frames['LIST'].location='fblank.htm'">Planillas APV Colectivo</a></li>
            </ul>
        </li>
        <%} %>
        <%if (entidades.indexOf("ISAPRE")>-1 || usuario.equals("empresa")){ %>
    	<li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaIsapre.jsp';window.parent.frames['LIST'].location='fblank.htm'">Planillas Isapres</a></li>
    	<%} %>
    	<%if (entidades.indexOf("MUTUAL")>-1 || usuario.equals("empresa")){ %>
    	<li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaMutual.jsp';window.parent.frames['LIST'].location='fblank.htm'">Planillas Mutuales</a></li>
    	<%} %>
    	<%if (entidades.indexOf("INP")>-1 || usuario.equals("empresa")){ %>
    	<li><a href="javascript:scrollPosi();">Planillas INP</a>
        	<ul>
            	<li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaINP.jsp';window.parent.frames['LIST'].location='fblank.htm'">Declaraci&oacute;n y Pago</a></li>
                <li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaINPDNP.jsp';window.parent.frames['LIST'].location='fblank.htm'">Declaraci&oacute;n y no Pago</a></li>
                <li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaINPPago.jsp';window.parent.frames['LIST'].location='fblank.htm'">Declaraci&oacute;n y Pago Atrasado</a></li>
            </ul>
        </li>
        <%} %>
<!-- <li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillaTodas.htm';window.parent.frames['LIST'].location='fblank.htm'">Todas las Planillas</a></li>  
-->
		<%if (entidades.indexOf("COMPROBANTE")>-1 || usuario.equals("empresa")){ %>
        <li><a onclick="javascript:window.parent.frames['frmFormulario'].location='ComprobantePagoActual.jsp';window.parent.frames['LIST'].location='fblank.htm'">Comprobante de Pago</a></li>
        <%} %>
        <%if (entidades.indexOf("CERTIFICADO")>-1 || usuario.equals("empresa")){ %>
		<li><a onclick="javascript:window.parent.frames['frmFormulario'].location='CertifCotiza.jsp';window.parent.frames['LIST'].location='fblank.htm'">Certificados  Cotizaciones</a></li>
		<%} %>
        <%if (entidades.indexOf("SENCE")>-1 || usuario.equals("empresa")){ %>
  		<li><a onclick="javascript:window.parent.frames['frmFormulario'].location='CertificadoAnual.jsp';window.parent.frames['LIST'].location='fblank.htm'">Certificado Anual Sence</a></li>
  		<%} %>
  		<%if (entidades.indexOf("SENCE")>-1 || usuario.equals("empresa")){ %>
  		<li><a onclick="javascript:window.parent.frames['frmFormulario'].location='ArchivoCertificadoCot.jsp';window.parent.frames['LIST'].location='fblank.htm'">Archivo Direcci&oacute;n Trabajo</a></li>
  		<%} %>
  		<%if (esAdm.equals("1")){ %>
  		<li><a onclick="javascript:window.parent.frames['frmFormulario'].location='AdminHomologacion.jsp';window.parent.frames['LIST'].location='fblank.htm'">Homologaci&oacute;n Direcci&oacute;n T</a></li>
  		<%} %>
  		<%if (entidades.indexOf("CERTIFICADO")>-1 || usuario.equals("empresa")){ %>
		  		<li><a onclick="javascript:window.parent.frames['frmFormulario'].location='PlanillasAfbr.jsp';window.parent.frames['LIST'].location='fblank.htm'">AFBR</a></li>
  		<%} %>
  		</ul>
		</div>
        </td>
       </tr>
	</table>
	</td>
	</tr>
	</table>
</body>
</html>
