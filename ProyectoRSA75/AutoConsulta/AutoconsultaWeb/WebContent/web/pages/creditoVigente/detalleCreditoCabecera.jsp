<br/>
<div>
<span class="certificado">
<div align='center'><font size='3'><b><bean:message key='<%=(String)session.getAttribute("cnsCredito.titulo") %>'/></b></font></div>
</span>
<div class="texto"><bean:write name="datosUsuario" property="nombre"/><br/></div>
<div class="texto">
  <div class="tituloconsultas"></div>
<div class="textobold">
  <div class="table3">
  <br/>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="4" class="celeste" align="left"> Datos del solicitante </td>
      </tr>
      <tr>
        <td class="c11azul"><strong><bean:message key="label.nombre"/>:</strong></td>
        <td align="left" class="c11azul"><bean:write name="afiliado.nombre"/></td>
        <td align="left" class="c11azul"><strong><bean:message key="label.rut"/></strong></td>
        <td align="left" class="c11azul"><bean:write name="afiliado.rut" /></td>
      </tr>
      <tr>
        <td colspan="4" class="c11azul" align="left">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="4" class="celeste" align="left"> Informaci&oacute;n del cr&eacute;dito</td>
      </tr>
      <tr>
        <td class="c11azul"><strong>N&uacute;mero de Crédito</strong></td>
        <td align="left" class="c11azul"><bean:write name="datosCredito" property="oficina"/> - <bean:write name="datosCredito" property="folio"/></td>
        <td align="left" class="c11azul"><strong>Estado del cr&eacute;dito</strong></td>
        <td align="left" class="c11azul">
        	<bean:define id="datosCredito" name="datosCredito" type="cl.laaraucana.credito.to.CreditoTO"/>
            <bean:message key='<%="traductor.estado.credito."+datosCredito.getEstado()%>'/>
        </td>
      </tr>
      <tr>
        <td class="c11azul"><strong>Fecha Otorgamiento</strong></td>
        <td class="c11azul" align="left"><bean:write name="datosCredito" property="fechaOtorgamiento" formatKey="format.date"/></td>
        <td class="c11azul" align="left">&nbsp;</td>
        <td class="c11azul" align="left">&nbsp;</td>
      </tr>
      <tr>
        <td class="c11azul"><strong>Monto Nominal</strong></td>
        <td class="c11azul" align="left"><bean:write name="datosCredito" property="montoNominal" formatKey="format.money"/></td>
        <td class="c11azul" align="left"><strong>Monto Reajustado</strong></td>
        <td class="c11azul" align="left"><bean:write name="datosCredito" property="montoReajustado" formatKey="format.money"/></td>
      </tr>
      <tr>
        <td class="c11azul"><strong>Tasa</strong></td>
        <td class="c11azul" align="left"><bean:write name="datosCredito" property="tasa"  formatKey="format.float3"/></td>
        <td class="c11azul" align="left"><strong>Cantidad Cuotas</strong></td>
        <td class="c11azul" align="left"><bean:write name="datosCredito" property="cantidadCuotas" /></td>
      </tr>
      <tr>
        <td class="c11azul" colspan="4">&nbsp;</td>
      </tr>

      <tr>
        <td colspan="4" class="celeste" align="left"> Informaci&oacute;n de Seguros</td>
      </tr>
      <tr>
        <td class="c11azul"><strong>Seguro Cesant&iacute;a</strong></td>
        <td class="c11azul"><bean:write name="datosCredito" property="indicadorSeguroCesantia" /></td>
        <td class="c11azul"><strong>Seguro Desgravamen</strong></td>
        <td class="c11azul"><bean:write name="datosCredito" property="indicadorSeguroDeshaucio" /></td>
      </tr>
      <tr>
        <td class="c11azul"><strong>Seguro Vida</strong></td>
        <td class="c11azul"><bean:write name="datosCredito" property="indicadorSeguroInvalidez" /></td>
        <td class="c11azul">&nbsp;</td>
        <td class="c11azul">&nbsp;</td>
      </tr>
      <tr>
        <td class="c11azul" colspan="4">&nbsp;</td>
      </tr>

      <tr>
        <td colspan="4" class="celeste" align="left"> Informaci&oacute;n de cuotas</td>
      </tr>
      <tr>
        <td class="c11azul"><strong>Valor Cuota</strong></td>
        <td class="c11azul" align="left"><bean:write name="datosCredito" property="montoCuota" formatKey="format.money"/></td>
        <td class="c11azul" align="left">&nbsp;</td>
        <td class="c11azul" align="left">&nbsp;</td>
      </tr>
      <tr>
        <td class="c11azul"><strong>Cantidad de cuota pagadas</strong></td>
        <td class="c11azul" align="left"><bean:write name="datosCredito" property="cantidadCuotasPagadas" /></td>
        <td class="c11azul" align="left"><strong>Estado</strong></td>
        <td class="c11azul" align="left">
            <bean:message key='<%="traductor.estado.credito."+datosCredito.getEstado()%>'/>
        </td>
      </tr>
      <tr>
        <td class="c11azul">&nbsp;</td>
        <td class="c11azul" align="left">&nbsp;</td>
        <td class="c11azul" align="left">&nbsp;</td>
        <td class="c11azul" align="left">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="4" class="celeste" align="left"> Información de aváles</td>
      </tr>
      <tr>
        <td class="c11azul"><strong>Datos Aval 1</strong></td>
        <td class="c11azul" align="left">&nbsp;
<logic:present name="aval1"><bean:write name="aval1" property="nombre" /></logic:present>        
        </td>
        <td class="c11azul">&nbsp;</td>
        <td class="c11azul" align="left">&nbsp;
        </td>
      </tr>
      <tr>
        <td class="c11azul"><strong>Datos Aval 2</strong></td>
        <td class="c11azul" align="left">&nbsp;
<logic:present name="aval2"><bean:write name="aval2" property="nombre" /></logic:present>        
        </td>
        <td class="c11azul">&nbsp;</td>
        <td class="c11azul" align="left">&nbsp;</td>
      </tr>
    </table>

  </div>
  </div>

</div>
</div>

