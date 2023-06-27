		<bean:define id="cobertura" name="producto" property="cobertura" type="cl.araucana.bienestar.bonificaciones.model.Cobertura"/>
          <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tablaHeaderCobertura1">
            <tr>
              <td width="125"><p class="textoHeader1"><strong>Producto:</strong></p>
              </td>
              <td width="401"><p class="textoHeader1"><bean:write name="cobertura" property="descripcion"/></p>
              </td>
            </tr>
            <tr>
              <td><p class="textoHeader1"><strong>C&oacute;digo:</strong></p>
              </td>
              <td><p class="textoHeader1"><html:link page="/setFichaProducto.do" paramId="producto" paramName="cobertura" paramProperty="codigo"><bean:write name="cobertura" property="codigo"/></html:link></p>
              </td>
            </tr>
          </table>