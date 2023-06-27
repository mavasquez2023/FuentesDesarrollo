
            <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tablaHeaderCaso1">
              <tr>
                <td width="125"><p class="textoHeader1"><strong>Caso ID:</strong></p>
                </td>
                <td width="401"><p class="textoHeader1"><html:link page="/setFichaCaso.do" paramId="codigo" paramName="caso" paramProperty="casoID" target="_top"><bean:write name="caso" property="casoID"/></html:link></p>
                </td>
              </tr>
              <tr>
                <td><p class="textoHeader1"><strong>Tipo Caso:</strong></p>
                </td>
                <td><p class="textoHeader1"><bean:write name="caso" property="tipoCaso"/></p>
                </td>
              </tr>
              <tr>
                <td><p class="textoHeader1"><strong>Estado:</strong></p>
                </td>
                <td><p class="textoHeader1"><bean:write name="caso" property="estado"/></p>
                </td>
              </tr>
            </table>


</BODY>

