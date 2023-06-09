<%@ page language="java"%>


<%@ include file = "/includes/env.jsp" %>
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> Pago Convenios </td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">
            <html:form action="/generarPagosConvenios">
 	        <html:select property="opcion" styleClass="menuDespl">
              	<html:options name="opciones.valores" labelName="opciones"/>
	        </html:select>	
	        <html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />

			<html:hidden property="codigo"/>
            </html:form>         
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="90" class="celdaColor2"><p class="vinculosUp">C�digo Convenio</p>
                </td>
                <td width="165" class="celdaColor2"><p class="vinculosUp">Nombre Convenio</p>
                </td>
                <td width="90" class="celdaColor2"><p class="vinculosUp">RUT Convenio</p>
                </td>
                <td width="115" class="celdaColor2"><p class="vinculosUp">Monto</p>
                </td>
                </tr>
              <logic:iterate id="register" name="pagos" indexId="t">
				<tr>
					<td><p>
						<bean:write name="register" property="codigoConvenio"/>
						</p>
					</td>
					<td><p>
						<bean:write name="register" property="nombreConvenio"/>
						</p>
					</td>
					<td><p>
						<bean:write name="register" property="fullRut"/>
						</p>
					</td>
					<td><p>
						$<bean:write name="register" property="monto" formatKey="format.int"/>
						</p>
					</td>
				</tr>
			</logic:iterate>
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
