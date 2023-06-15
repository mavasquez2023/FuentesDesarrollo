<logic:empty name="detallePagos">
<strong><bean:message key="label.consulta.creditos.noCreditosDetallePago"/></strong><br/>
</logic:empty>

<logic:notEmpty name="detallePagos">
     <div class="table2">
     <span class="certificado"><br/><strong><bean:message key='label.consulta.creditos.detPagos'/></strong><br/></span>
     <br/>
     <form action="" method="get" enctype="application/x-www-form-urlencoded">
        <table cellspacing="2" cellpadding="0" width="100%" border="0" id="results" class="certificado">
        <tr>
          <th align="center" >Nro Cuota</th>
          <th align="center" >Fecha de Pago</th>
          <th align="center" >Oficina de Pago</th>
          <th align="center" >Documento de Pago</th>
          <th align="center" >Monto</th>
          <th align="center" >Estado al Pago</th>
        </tr>
	<logic:iterate id="pagos" name="detallePagos" type="cl.laaraucana.credito.to.PagoCuotaTO">
      <tr>
        <td align="center" class="c11azul"><bean:write name="pagos" property="cuotaNumero"/></td>
        <td align="center" class="c11azul"><bean:write name="pagos" property="fechaPago" formatKey="format.date"/></td>
        <td align="left"   class="c11azul"><bean:write name="pagos" property="oficicinaPago" /></td>
        <td align="right"  class="c11azul"><bean:write name="pagos" property="documentoPago" /> - <bean:write name="pagos" property="tipoDocumentoPago" /></td>
        <td align="right"  class="c11azul"><bean:write name="pagos" property="monto" formatKey="format.money"/>&nbsp;</td>
        <td class="c11azul">&nbsp;
           <bean:message key='<%="traductor.estado.credito.pago."+pagos.getEstado()%>'/>
        </td>
      </tr>
	</logic:iterate>

       </table>
<div id="pageNavPosition"></div>
</form>

<script type="text/javascript"><!--
    var pager = new Pager('results', 10); 
    pager.init(); 
    pager.showPageNav('pager', 'pageNavPosition'); 
    pager.showPage(1);
//--></script>
</div>
</div>
</logic:notEmpty>

