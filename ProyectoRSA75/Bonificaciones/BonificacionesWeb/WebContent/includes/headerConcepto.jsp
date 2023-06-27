			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tablaHeaderConcepto1">
              <tr>
                <td><p class="textoHeader1"><strong>Concepto:</strong></p>
                </td>
                <td><p class="textoHeader1"><bean:write name="concepto" property="descripcion"/></p>
                </td>
              </tr>
              <tr>
                <td width="125"><p class="textoHeader1"><strong>C&oacute;digo:</strong></p>
                </td>
                <td width="401"><p class="textoHeader1"><html:link page="/setFichaConcepto.do" paramId="codigo" paramName="concepto" paramProperty="codigo"><bean:write name="concepto" property="codigo"/></html:link></p>
                </td>
              </tr>
            </table>