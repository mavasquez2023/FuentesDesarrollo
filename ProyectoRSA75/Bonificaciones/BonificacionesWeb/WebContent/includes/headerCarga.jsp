     <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tablaHeaderCobertura1">
              <tr>
                <td width="125"><p class="textoHeader1"><strong>RUT:</strong></p></td>
                <td width="401"><p class="textoHeader1"><html:link page="/setFichaCarga.do" paramId="carga" paramName="carga" paramProperty="rutCarga" target="_top"><bean:write name="carga" property="fullRutCarga"/></html:link></p></td>
                </tr>
              <tr>
                <td><p class="textoHeader1"><strong>Nombre:</strong></p></td>
                <td><p class="textoHeader1"><bean:write name="carga" property="nombreCarga"/> <bean:write name="carga" property="apePatCarga"/> <bean:write name="carga" property="apeMatCarga"/></p></td>
                </tr>
              <tr>
                <td><p class="textoHeader1"><strong>Tipo de Carga:</strong></p></td>
                <td><p class="textoHeader1"><bean:write name="carga" property="tipoCarga"/></p></td>
                </tr>
   </table> 