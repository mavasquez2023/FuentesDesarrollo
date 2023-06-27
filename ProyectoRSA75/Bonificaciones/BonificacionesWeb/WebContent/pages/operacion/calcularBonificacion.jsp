<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
<script language="JavaScript">
function cambiarCajas(){
	var cajaPrincipal = document.forms[1].cajaPrincipal;
	var cajas = document.forms[1].indices;

	for (i = 0; i < cajas.length; i++){
		cajas[i].checked = cajaPrincipal.checked ;
	}
}
</script>
</head>
 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
	<tr>
		<td>
			<table width="97%" border="0" cellspacing="2" cellpadding="2">
				<tr>
					<td class="caminito"><%@ include file="/includes/camino.jsp"%> Calcular Bonificaci&oacute;n</td>
				</tr>
			</table>
			<table width="97%" border="0" cellspacing="2" cellpadding="2">
				<tr>
					<td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %></td>
					<td width="77%" valign="top">
						<html:form action="/calcularBonificacion">
							<html:select property="opcion" styleClass="menuDespl">
								<html:options name="opciones.valores" labelName="opciones"/>
							</html:select>
							<html:image page="/images/botones/boton_ir.gif" border="0" />
							<br>
							<table width="50%" border="0" align="center" cellpadding="1" cellspacing="2">
								<tr>
									<td align="center">
										<table width="25%" border="0" cellspacing="1" cellpadding="0">
											<tr>
												<td width="59%"><p class="derecha">Filtrar </p></td>
												<td width="41%">
													<div align="center">
														<html:image value="_filtrar" property="_filtrar" page="/images/botones/boton_ir.gif" border="0" />
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<br>
							<table width="530" border="0" cellpadding="2" cellspacing="2">
								<tr>
									<td class="celdaColor1">
										<input type="checkbox" name="cajaPrincipal" checked="checked" onclick="javascript:cambiarCajas()">
									</td>
									<td class="celdaColor1"><p> <a href="#" class="vinculosUp">Caso ID</a></p></td>
									<td class="celdaColor1"><p> <a href="#" class="vinculosUp">Fecha<br>Ocurrencia</a></p></td>                
									<td class="celdaColor1"><a href="#" class="vinculosUp">Tipo Caso</a></td>
									<td class="celdaColor1"><a href="#" class="vinculosUp">Nombre Socio</a></td>
									<td class="celdaColor1"><a href="#" class="vinculosUp">Convenio</a></td>
								</tr>
								<tr>
									<td bgcolor="#F0F0F0" class="lookup01">&nbsp;</td>
									<td bgcolor="#F0F0F0" class="lookup01">&nbsp;</td>
									<td bgcolor="#F0F0F0" class="lookup01">&nbsp;</td>
									<td bgcolor="#F0F0F0" class="lookup01">                    
										<html:select property="tipoCaso" styleClass="menuDespl">
											<html:option value="">Todos</html:option>
											<html:option value="DESCUENTO">Descuento</html:option>
											<html:option value="REEMBOLSO">Reembolso</html:option>
										</html:select>
									</td>
									<td bgcolor="#F0F0F0" class="lookup01">&nbsp;</td>
									<td bgcolor="#F0F0F0" class="lookup01">&nbsp;</td>
								</tr>
 
								<logic:iterate id="register" name="lista.casos" indexId="i" type="cl.araucana.bienestar.bonificaciones.vo.CasoVO">
									<tr>
										<td bgcolor="#F0F0F0" class="lookup01"><input type="checkbox" name="indices" value="<%=i%>" checked></td>
										<td bgcolor="#F0F0F0" class="lookup01"><html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoID" target="_top"><bean:write name="register" property="casoID"/></html:link></td>
										<td bgcolor="#F0F0F0" class="lookup01"><bean:write name="register" property="fechaDeOcurrencia" formatKey="format.date"/></td>
										<td bgcolor="#F0F0F0" class="lookup01"><bean:write name="register" property="tipoCaso"/></td>
										<td bgcolor="#F0F0F0" class="lookup01"><bean:write name="register" property="nombreSocio"/></td>
										<td bgcolor="#F0F0F0" class="lookup01"><bean:write name="register" property="nombreConvenio"/></td>
									</tr>
								</logic:iterate>
							</table>
						</html:form>  
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
