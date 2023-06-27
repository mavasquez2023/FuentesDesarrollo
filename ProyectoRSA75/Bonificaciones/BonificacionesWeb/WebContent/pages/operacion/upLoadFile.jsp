<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
<script language="javascript" src="<html:rewrite page="/includes/jquery-1.3.2.js" />"></script>
<script language="javascript" src="<html:rewrite page="/includes/jquery.blockUI.js" />"></script>
</head>
 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/includes/arriba.jsp" %>
<script type="text/javascript">
$(document).ready(function() { 
	$("#enviar").click(function() { 
	if (confirm('Esta operación es irreversible. Desea realmente continuar?')){
        $.blockUI({ message: "Espere por Favor..." }); 
        $("#formUpload").submit();
       }
       else 
       	return false;
	});		
});
</script>

<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> Cargar Archivos</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top">
          	<%@ include file="/includes/menu.jspf" %>
          </td>
          <td width="77%" valign="top">
			<table>
		        <tr>
				  <td>
					  <P>
					  <bean:message key="label.carga.archivo.prepare.mensaje"/>
					  <br><br>
					  </P>
				  </td>
				</tr>
				
				<form action="/BonificacionesWeb/upLoadFile.do" enctype="multipart/form-data" method="post" id="formUpload">
				
				<tr>
                    <td>
                    	<br>
                    	<p><bean:message key="label.carga.archivo.prepare.convenio"/></p>
                    	<p><strong><bean:message key="label.carga.archivo.convenio"/> <bean:message key="label.obligatorio"/>:</strong>
                    	<logic:present name="convenio">
                    		<bean:write name="convenio" property="nombre"/>
							<bean:define id="convenio" name="convenio" type="cl.araucana.bienestar.bonificaciones.model.Convenio"/>
	                    	<input type="hidden" name="codigoConvenio" value='<%= convenio.getCodigo() %>' />
	                    	<input type="hidden" name="nombreConvenio" value='<%= convenio.getNombre() %>' />
                    	</logic:present>
                    	<html:link page="/prepareLookUpUpLoadFile.do?destino=convenios" target="_top">
                    	<html:img page="/images/botones/boton_look_up.gif" alt="Convenio" width="14" height="14" border="0"/>
                    	</html:link>
                    	</p>
                    </td>
                </tr>
                <logic:present name="buscarCobertura">
				<tr>
                    <td>
						<br>
						<p><bean:message key="label.carga.archivo.prepare.producto"/></p>
                    	<p><strong><bean:message key="label.carga.archivo.producto"/> <bean:message key="label.obligatorio"/>:</strong>
                    	<logic:present name="producto">
	                    	<bean:write name="producto" property="cobertura.descripcion"/>
	                    	<bean:define id="producto" name="producto" type="cl.araucana.bienestar.bonificaciones.model.Producto"/>
	                    	<input type="hidden" name="codigoProducto" value='<%= producto.getCobertura().getCodigo() %>' />
	                    	<input type="hidden" name="nombreProducto" value='<%= producto.getCobertura().getDescripcion() %>' />
	                    </logic:present>
                    	<html:link page="/prepareLookUpUpLoadFile.do?destino=productos" paramId="codigoConvenio" paramName="convenio" paramProperty="codigo" target="_top">
                    	<html:img page="/images/botones/boton_look_up.gif" alt="Cobertura" width="14" height="14" border="0"/>
                    	</html:link>
                    	</p>
                    </td>
                </tr>
                </logic:present>
				<tr>
					<td class="textos2" >
						<br>
						<p><bean:message key="label.carga.archivo.prepare.archivo"/></p>
						<input size="50" type="file" name="file">
					</td>
				</tr>

				<tr>
					<td>
						<br>
						<p>
							<font color='red'>
								<bean:message key="label.carga.archivo.prepare.archivo.asegurese"/>
							</font>
						</p>						
							<input type="button" id="enviar" value="Procesar"/>
					</td>
				</tr>
			</table>
		  </td>
		</tr>
      </table>      
    </td>
  </tr>
</table>
<%@ include file="/includes/pie.jsp" %>
<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>
