<%@ include file="/html/comun/taglibs.jsp" %>
<table cellpadding="5" cellspacing="0" border="0">

<tr>
	<td width="70">&nbsp;</td>
	<td class="titulo" align="center">
    	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0" width="380" height="148">
      		<param name="movie" value="img/images/empresa.swf" />
      		<param name="quality" value="high" />
      		<param name="wmode" value="transparent" />
      		<embed src="img/images/empresa.swf" width="380" height="148" quality="high" pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" wmode="transparent"></embed>
    	</object>	
	<td>&nbsp;</td>
</tr>
<tr>
	<td colspan="4">&nbsp;</td>
</tr>
</table>
<logic:notEmpty name="aviso">
<div id="pop-aviso" STYLE="position:absolute; top:170px; left:180px; width:${aviso.ancho}px; height:${aviso.alto}px; z-index:5; visibility: visible; border: 1px solid black"">
	<!--[if lte IE 6.5]><iframe></iframe><![endif]-->
	<table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">	
	<tr>
		<td height="14" colspan="4" bgcolor="#85b4be">&nbsp;</td>
	</tr>
	<tr>
		<td width="14" bgcolor="#85b4be">&nbsp;</td>
		<td class="tabla-datos"><b>${aviso.titulo}</b></td>
		<td align="right" class="tabla-datos"><a href="javascript:muestraAviso();" class="links">Salir</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td width="14" bgcolor="#85b4be">&nbsp;</td>
	</tr>
	<tr>
		<td width="14" bgcolor="#85b4be">&nbsp;</td>
		<td align="center" colspan="2">
			<table width="100%" height="100%" cellpadding="1" cellspacing="1" class="tabla-datos">
		  		<tr> 
		    		<td>${aviso.contenido}</td>
		  		</tr>
		  		<logic:notEmpty name="aviso" property="link">
			  		<tr> 
			    		<td align="right"><a href="${aviso.link}" target="_blank" class="links">Leer m&aacute;s</a></td>
			  		</tr>
		  		</logic:notEmpty>
			</table>
		</td>
		<td width="14" bgcolor="#85b4be">&nbsp;</td>
	</tr>
	<tr>
		<td height="14" colspan="4" bgcolor="#85b4be">&nbsp;</td>
	</tr>
	</table>
</div>
</logic:notEmpty>
<script language="javaScript">
window.setTimeout("window.location.href='ListarNominas.do?accion=inicio&limpiaPath='" , 4000);
</script>