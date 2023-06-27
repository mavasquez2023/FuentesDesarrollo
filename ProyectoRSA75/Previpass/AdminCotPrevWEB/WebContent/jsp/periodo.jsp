<%@ include file="comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<HEAD>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at tde Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="autdor" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<c:url value="/js/jquery-1.3.2.js" />"></script>
	<script src="<c:url value='/js/comun.js'/>"></script>
</HEAD>
  
<body>
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/Periodo" styleId="formulario">
    <html:messages id="msg" message="true">
		<div class="msgExito">${msg}</div>
	</html:messages>
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="3">
			<table width="100%">
	        	<tr valign="bottom"> 
	            	<td width="50%" height="30" bgcolor="#FFFFFF" class="titulo"><strong>Informaci&oacute;n del Per&iacute;odo</strong></td>
	                <td width="50%" align="right" bgcolor="#FFFFFF">
	                	<html:button property="operacion" styleClass="btn3" value="Imprimir" onclick="javascript:imprimir();"/>
			        </td>
	            </tr>
	        </table>
        </td>
    </tr>
    <tr align="center"> 
   		<td valign="top" bgcolor="#CCCCCC">
		<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
			<tr class="subtitulos_tablas">
          		<td width="21%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Datos Empresa</td>
	           	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Cantidad</td>
         	</tr>
       		<tr>
           		<td height="20" align="left" valign="middle" nowrap="nowrap" class="textos_formularios">N&uacute;mero de Trabajadores</td>
           		<td width="32%" nowrap="nowrap" class="textos_formularios"><bean:write name="PeriodoForm" property="numTrabajadores" /></td>
       		</tr>
       		<tr>
           		<td height="20" align="left" valign="middle" nowrap="nowrap" class="textos-formcolor2">N&uacute;mero de N&oacute;minas Remuneraci&oacute;n</td>
           		<td width="32%" nowrap="nowrap" class="textos-formcolor2"><bean:write name="PeriodoForm" property="numNominasRE" /></td>
       		</tr>
       		<tr>
           		<td height="20" align="left" valign="middle" nowrap="nowrap" class="textos_formularios">N&uacute;mero de N&oacute;minas Gratificaci&oacute;n</td>
           		<td width="32%" nowrap="nowrap" class="textos_formularios"><bean:write name="PeriodoForm" property="numNominasGR" /></td>
       		</tr>
       		<tr>
           		<td height="20" align="left" valign="middle" nowrap="nowrap" class="textos-formcolor2">N&uacute;mero de N&oacute;minas Reliquidaci&oacute;n</td>
           		<td width="32%" nowrap="nowrap" class="textos-formcolor2"><bean:write name="PeriodoForm" property="numNominasRA" /></td>
       		</tr>
       		<tr>
           		<td height="20" align="left" valign="middle" nowrap="nowrap" class="textos_formularios">N&uacute;mero de N&oacute;minas Dep&oacute;sitos convenidos</td>
           		<td width="32%" nowrap="nowrap" class="textos_formularios"><bean:write name="PeriodoForm" property="numNominasDC" /></td>
       		</tr>
       		<tr>
           		<td height="20" align="left" valign="middle" nowrap="nowrap" class="textos-formcolor2">N&uacute;mero de Comprobantes</td>
           		<td width="32%" nowrap="nowrap" class="textos-formcolor2"><bean:write name="PeriodoForm" property="numComprobantes" /></td>
       		</tr>
        </table>
		</td>
		<td>&nbsp;&nbsp;</td>
		<td valign="top" bgcolor="#CCCCCC">
			<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				<tr class="subtitulos_tablas">
	          		<td width="21%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Datos Independientes</td>
		           	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Cantidad</td>
	         	</tr>
	       		<tr>
	           		<td height="20" align="left" valign="middle" nowrap="nowrap" class="textos_formularios">N&uacute;mero de Trabajadores</td>
	           		<td width="32%" nowrap="nowrap" class="textos_formularios"><bean:write name="PeriodoForm" property="numTrabajadoresInd" /></td>
	       		</tr>
	       		<tr>
	           		<td height="20" align="left" valign="middle" nowrap="nowrap" class="textos-formcolor2">N&uacute;mero de N&oacute;minas Remuneraci&oacute;n</td>
	           		<td width="32%" nowrap="nowrap" class="textos-formcolor2"><bean:write name="PeriodoForm" property="numNominasREInd" /></td>
	       		</tr>
	       		<tr>
	           		<td height="20" align="left" valign="middle" nowrap="nowrap" class="textos_formularios">N&uacute;mero de N&oacute;minas Gratificaci&oacute;n</td>
	           		<td width="32%" nowrap="nowrap" class="textos_formularios"><bean:write name="PeriodoForm" property="numNominasGRInd" /></td>
	       		</tr>
	       		<tr>
	           		<td height="20" align="left" valign="middle" nowrap="nowrap" class="textos-formcolor2">N&uacute;mero de N&oacute;minas Reliquidaci&oacute;n</td>
	           		<td width="32%" nowrap="nowrap" class="textos-formcolor2"><bean:write name="PeriodoForm" property="numNominasRAInd" /></td>
	       		</tr>
	       		<tr>
	           		<td height="20" align="left" valign="middle" nowrap="nowrap" class="textos_formularios">N&uacute;mero de N&oacute;minas Dep&oacute;sitos convenidos</td>
	           		<td width="32%" nowrap="nowrap" class="textos_formularios"><bean:write name="PeriodoForm" property="numNominasDCInd" /></td>
	       		</tr>
	       		<tr>
	           		<td height="20" align="left" valign="middle" nowrap="nowrap" class="textos-formcolor2">N&uacute;mero de Comprobantes</td>
	           		<td width="32%" nowrap="nowrap" class="textos-formcolor2"><bean:write name="PeriodoForm" property="numComprobantesInd" /></td>
	       		</tr>
	        </table>
		
		</td>
  	</tr>
  	<tr>
  		<td align="center" colspan="3">
		<br />
		<div class="titulos_formularios">Una vez gatillado el Proceso, no se puede reversar.</div>
		<br />
			<div class="titulos_formularios" align="left">Las acciones a realizar para las Empresas son:
				<ul>
					<li>Eliminaci&oacute;n de todas las N&oacute;minas y todas sus cotizaciones asociadas.</li>
					<li>Eliminaci&oacute;n de todos los Comprobantes de Pago generados.</li>
					<li>Reinicio de n&uacute;meros de Folios de Entidades (de acuerdo a configuraci&oacute;n en par&aacute;metros).</li>
					<li>C&aacute;lculo de factor para balanceo de carga, por cada Empresa/Convenio.</li>
					<li>Anulaci&oacute;n de Folios de Tesorer&iacute;a de Comprobantes no Pagados.</li>
				</ul>
			</div>
			<div class="titulos_formularios" align="left">Las acciones a realizar para los Independientes son:
				<ul>
					<li>Actualiza el estado de la nomina.</li>
					<li>Elimina todos los detalles aporte ccaf, crédito ccaf , leasing ccaf.</li>
					<li>Reinicio de n&uacute;meros de Folios de Entidades.</li>
					<li>Actualiza el codigo de barra de la nomina y comprobante.</li>
				</ul>
			</div>
			<input type="button" value="Cierre Empresa" class="btn2" name="operacion" onclick="javascript:enviar1();">
			<input type="button" value="Cierre Independiente" class="btn2" name="operacion" onclick="javascript:enviar2();">
			
			<input type="hidden" value="" name="accion" id="accion" />
		<br />
		<br />
		<br />
		</td>
	</tr>
</table>
</html:form>
<script language="javaScript"> 
function enviar1()
{
	if (confirm("¿Está seguro de que desea eliminar toda la información asociada al período de EMPRESAS?"))
	{
		if (confirm("Esta información de nóminas y comprobantes de todas las Empresas/Convenios/Tipos de Procesos, no podrá ser recuperada.\n\n¿Está seguro de que desea continuar?"))
		{
			document.getElementById("accion").value = "cerrarE";
			document.getElementById("formulario").submit();
		}
	}
}
function enviar2()
{
	if (confirm("¿Está seguro de que desea eliminar toda la información asociada al período de INDEPENDIENTES?"))
	{
		if (confirm("Esta información de nóminas y comprobantes de todas los Independientes/Convenios/Tipos de Procesos, no podrá ser recuperada.\n\n¿Está seguro de que desea continuar?"))
		{
			document.getElementById("accion").value = "cerrarI";
			document.getElementById("formulario").submit();
		}
	}
}	

    $(document).ready(function() {
        $( "#mensaje" ).hide();     // Por defecto, ocultamos el DIV que contine el mensaje emergente.
        $('#disparar_mensaje').click(     // Ubicamos el evento click en el botón disparar_mensaje
            function(){
                $( "#mensaje" ).show(); // Ponemos en visible en contenedor del mensaje
                $( "#mensaje" ).dialog(); // Utilizamos el método "dialog" que disparar el mensaje emergente
            }
        );
    });
</script>
</body>
</html:html>
