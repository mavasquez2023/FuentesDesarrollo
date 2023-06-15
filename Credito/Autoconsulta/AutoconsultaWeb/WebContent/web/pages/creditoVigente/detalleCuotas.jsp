<logic:empty name="detalleCuotas">
<strong><bean:message key="label.consulta.creditos.noCreditosVigentes"/></strong><br/>
</logic:empty>
<logic:notEmpty name="detalleCuotas">
     <div class="table2">
     <span class="certificado"><br/><strong><bean:message key='label.consulta.creditos.detCuotas'/></strong><br/></span>
     <br/>
     <form action="" method="get" enctype="application/x-www-form-urlencoded">
        <table cellspacing="2" cellpadding="0" width="100%" border="0"  id="results" class="certificado">
        <tr>
          <th align="center" >Nro Cuota</th>
          <th align="center" >Vencimiento</th>
          <th align="center" >Capital</th>
          <th align="center" >Inter&eacute;s</th>
          <%if(session.getAttribute("seguros_desplegar")!=null && session.getAttribute("seguros_desplegar").equals("1")){%>
        	  <th align="center" >Seguros</th>
          <%}%>
          <th align="center" >Monto Cuota</th>
          <th align="center" >Abono</th>
          <th align="center" >Estado</th>
        </tr>
	<logic:iterate id="cuotas" name="detalleCuotas"  type="cl.laaraucana.credito.to.CuotaTO">
      <tr style="border:0px; padding:0;">
        <td align="center"  class="c11azul"><bean:write name="cuotas" property="cuotaNumero"/></td>
        <td align="center"  class="c11azul"><bean:write name="cuotas" property="fechaVencimiento" formatKey="format.date"/></td>
        <td align="right"  class="c11azul"><bean:write name="cuotas" property="capital" formatKey="format.money"/></td>
        <td align="right"  class="c11azul"><bean:write name="cuotas" property="interes" formatKey="format.money"/></td>
         <%if(session.getAttribute("seguros_desplegar")!=null && session.getAttribute("seguros_desplegar").equals("1")){%>
       		 <td align="right"  class="c11azul"><bean:write name="cuotas" property="seguros" formatKey="format.money"/></td>
       	<%}%>
        <td align="right"  class="c11azul"><bean:write name="cuotas" property="monto" formatKey="format.money"/></td>
        <td align="right"  class="c11azul"><bean:write name="cuotas" property="abono" formatKey="format.money"/>&nbsp; &nbsp; </td>
        <td class="c11azul">
          <bean:message key='<%="traductor.estado.credito."+cuotas.getEstado()%>'/>
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

