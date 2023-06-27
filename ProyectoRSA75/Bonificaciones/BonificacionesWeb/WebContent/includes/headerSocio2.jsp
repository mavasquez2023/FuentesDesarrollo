     <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tablaHeaderSocio1">
              <tr>
                <td width="125"><p class="textoHeader1"><strong>RUT:</strong></p></td>
                <td width="401"><p class="textoHeader1"><html:link page="/setFichaSocio.do?source=socio" paramId="rut" paramName="socio" paramProperty="rut" target="_top"><bean:write name="socio" property="fullRut"/></html:link></p></td>
                </tr>
              <tr>
                <td><p class="textoHeader1"><strong>Nombre:</strong></p></td>
                <td><p class="textoHeader1"><bean:write name="socio" property="nombre"/> <bean:write name="socio" property="apePat"/> <bean:write name="socio" property="apeMat"/></p></td>
                </tr>
   </table>
       