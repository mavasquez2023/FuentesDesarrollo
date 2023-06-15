<%@page import="cl.araucana.autoconsulta.vo.CuotaCreditoVO"%>
<br/>
<div>
<span class="certificado">
<div align='center'><h1><bean:message key='<%=(String)session.getAttribute("cnsCredito.titulo") %>'/></h1></div>
</span>
<div class="texto"><bean:write name="datosUsuario" property="nombre"/><br/></div>
<div class="texto">
  <div class="tituloconsultas"></div>
<div class="textobold">
  <div class="textobold"> <br/>
  </div>
  <br/>
  <div class="table3">
    <table width="100%" border="0" cellspacing="2" cellpadding="0">
      <tr>
        <td colspan="4" class="certificado" align="left"> Datos del solicitante</td>
      </tr>
      <tr>
        <td class="certificado"><strong><bean:message key="label.nombre"/>:</strong></td>
        <td align="left" class="certificado"><bean:write name="afiliado.nombre"/></td>
        <td align="left" class="certificado"><strong><bean:message key="label.rut"/></strong></td>
        <td align="left" class="certificado"><bean:write name="afiliado.rut" /></td>
      </tr>
<logic:notEmpty name="empleadores">
	<logic:iterate id="register" name="empleadores">
      <tr>
        <td class="certificado"><strong><bean:message key="label.empleador"/></strong></td>
        <td align="left" class="certificado" colspan="3"><bean:write name="register" property="nombre"/></td>
      </tr>
	</logic:iterate>
</logic:notEmpty>
    </table>
  </div>
  </div>
<logic:notEmpty name="listaCreditos">
<br/>
  <div class="table2"><TABLE cellSpacing="2" cellPadding="0" width="100%" border="0">
              <TBODY>
              <TR>
                <Td class="celeste"><strong>Nro de Obligaci&oacute;n</strong></Td>
                <TD class="celeste"><strong>Monto del crédito</strong> </TD>
                <TD class="celeste"><strong>Cantidad cuotas</strong> </TD>
                <TD class="celeste"><strong>Monto cuota</strong> </TD>
                <!--  TD align="center" class="celeste"><strong>Fecha de Otorgamiento</strong> </TD -->
                <TD class="celeste"><strong>Estado</strong></TD>
                </TR>
               <logic:iterate id="register" name="listaCreditos" type='cl.laaraucana.credito.to.CreditoTO'>
              <TR>
                <logic:equal name="register" property="estado" value="8">
                  <TD class=certificado><bean:write name="register" property="oficina"/>-<bean:write name="register" property="folio"/></TD>
                </logic:equal>
                <logic:notEqual name="register" property="estado" value="8">
                  <TD class=certificado><a href="javascript:doDetalle(<bean:write name="register" property="folio"/>);"><bean:write name="register" property="oficina"/>-<bean:write name="register" property="folio"/></a></TD>
                </logic:notEqual>
                <TD class=certificado align=middle><bean:write name="register" property="montoReajustado" formatKey="format.money"/></TD>
                <TD class=certificado align=middle><bean:write name="register" property="cantidadCuotas"/></TD>
                <TD class=certificado align=middle><bean:write name="register" property="montoCuota" formatKey="format.money"/></TD>
                <!-- TD align="center" class=certificado><bean:write name="register" property="fechaOtorgamiento" formatKey="format.date"/></TD -->
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
<strong><bean:message key="label.consulta.creditos.noHayCreditos"/></strong>
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

<SCRIPT type="text/javascript">
<!-- 
 var gomez={ 
		 gs: new Date().getTime(), 
		 acctId:'906502', 
		 pgId:'LAARAUCANA-CONSULTA-ESTADO-CREDITO', 
		 grpId:'',
		 wrate:'1'
 }; 
 //--> 
 </SCRIPT> 

</html:form>


