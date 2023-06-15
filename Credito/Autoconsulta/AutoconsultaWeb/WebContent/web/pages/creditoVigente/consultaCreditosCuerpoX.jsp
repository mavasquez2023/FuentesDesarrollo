<%@page import="cl.araucana.autoconsulta.vo.CuotaCreditoVO"%>

<div>
<span class="tituloconsultas">
<h1>Consulta Cr&eacute;ditos</h1>
</span>
<div class="texto"><bean:write name="datosUsuario" property="nombre"/><br/></div>
<div class="texto">
  <div class="tituloconsultas"></div>
<div class="textobold">
  <div class="textobold"> <br>
  </div>
  <div class="table3">
    <table width="807" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="6" class="celeste" align="left"> Datos del solicitante </td>
      </tr>
      <tr>
        <td width="139" class="c11azul"><strong><bean:message key="label.nombre"/>:</strong></td>
        <td width="356" align="left" class="c11azul"><bean:write name="afiliado.nombre"/></td>
        <td width="150" align="left" class="c11azul"><strong><bean:message key="label.rut"/></strong></td>
        <td width="162" align="left" class="c11azul"><bean:write name="afiliado.rut" /></td>
      </tr>
<logic:notEmpty name="empleadores">
	<logic:iterate id="register" name="empleadores">
      <tr>
        <td width="139"  class="c11azul"><strong><bean:message key="label.empleador"/></strong></td>
        <td align="left" class="c11azul" colspan="3"><bean:write name="register" property="nombre"/></td>
      </tr>
	</logic:iterate>
</logic:notEmpty>
    </table>
  </div>
  </div>
<logic:notEmpty name="listaCreditos">

  <div class="table2"><TABLE cellSpacing=0 cellPadding=0 width=100% border=0>
              <TBODY>
              <TR>
                <Td class="celeste"><strong>Nro de Obligaci&oacute;n</strong></Td>
                <TD class="celeste"><strong>Monto del crédito</strong> </TD>
                <TD class="celeste"><strong>Cantidad cuotas</strong> </TD>
                <TD class="celeste"><strong>Monto cuota</strong> </TD>
                <TD align="center" class="celeste"><strong>Fecha de Otorgamiento</strong> </TD>
                <TD class="celeste"><strong>Estado</strong></TD>
                </TR>
<logic:iterate id="register" name="listaCreditos" type='cl.laaraucana.credito.to.CreditoTO'>
              <TR>
                <TD class=certificado><a href="javascript:doDetalle(<bean:write name="register" property="folio"/>);"><bean:write name="register" property="folio"/></a></TD>
                <TD class=certificado align=middle><bean:write name="register" property="montoReajustado" formatKey="format.money"/></TD>
                <TD class=certificado align=middle><bean:write name="register" property="cantidadCuotas"/></TD>
                <TD class=certificado align=middle><bean:write name="register" property="montoCuota" formatKey="format.money"/></TD>
                <TD align="center" class=certificado><bean:write name="register" property="fechaOtorgamiento" formatKey="format.date"/></TD>
                <TD class=certificado>
                <bean:message key='<%="traductor.estado.credito."+register.getEstado()%>'/>
                </TD>
              </TR>
</logic:iterate>

  	</TBODY></TABLE>
</div>

</logic:notEmpty>
<logic:empty name="listaCreditos">
<br/>
<br/>
<strong><bean:message key="label.consulta.creditos.noCreditosVigentes"/></strong>
</logic:empty>

<logic:present name="cnsCredito.volver">
<span><br/><br/>
<input  class="boton" type="button" name="dummyProperty" value="Volver" onclick="history.back();" onMouseOver="this.className='botonOver'" onMouseOut="this.className='boton'" >			
</span>
</logic:present>
</div>
</div>

<html:form action="/getCreditoDetalle" method="POST">
<html:hidden property="folio" value=""/>

</html:form>

</html:form>

