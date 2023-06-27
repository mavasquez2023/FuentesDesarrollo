           <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tablaHeaderConvenio1">
             <tr>
               <td width="125"><p class="textoHeader1"><strong>Convenio:</strong></p>
               </td>
               <td width="401"><p class="textoHeader1"><bean:write name="convenio" property="nombre"/></p>
               </td>
             </tr>
             <tr>
               <td><p class="textoHeader1"><strong>C&oacute;digo:</strong></p>
               </td>
               <td><p class="textoHeader1"><html:link page="/setFichaConvenio.do" paramId="convenio" paramName="convenio" paramProperty="codigo" target="_top"><bean:write name="convenio" property="codigo"/></html:link></p>
               </td>
             </tr>
             <tr>
               <td><p class="textoHeader1"><strong>Concepto:</strong></p>
               </td>
               <td><p class="textoHeader1"><bean:write name="convenio" property="descripcionConcepto"/></p>
               </td>
             </tr>
           </table>