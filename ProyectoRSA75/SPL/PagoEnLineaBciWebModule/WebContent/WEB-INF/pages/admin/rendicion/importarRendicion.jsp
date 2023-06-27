<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>


<script type="text/javascript" language="JavaScript">
	
	function preview() {
		frm = document.getElementById('formu');
		
		if (validate(frm)) {
			frm.submit();
		}
	}
	
	function validate(frm) {
		var objMedio = document.getElementById('medio');
		if (objMedio.options[objMedio.selectedIndex].value=='') {
			alert('Debe seleccionar el banco.');
			return false;
		}
		if (frm.rendicion.value=='') {
			alert('Debe seleccionar un archivo de rendición.');
			return false;
		}
		return true;
	}

</script>
<table width="97%" border="0" cellpadding="0" cellspacing="0">
<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
  <td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr> 
        <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="1">
            <tr>
              <td height="25" align="left" bgcolor="#FFFFFF" class="tit-13"><strong>Importar Rendici&oacute;n</strong></td>             
            </tr>            
          </table>
        </td>
      </tr>
    </table>
  </td>
</tr>
<tr align="center" valign="top"> 
    <td class="Titulos">
    <html:form action="admin/importarRendicion/preview" enctype="multipart/form-data" styleId="formu">
	<table width="95%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td bgcolor="#CCCCCC">
          	<table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td align="center" bgcolor="#FFFFFF">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td align="left" width="30%" class="barratablas"><span class="Verde">*</span>Banco</td>                      
                      <td align="left" width="70%" bordercolor="#FFFFFF" class="textos-formularios1a">
                            <html:select property="medioPago.id" styleId="medio">
	                        	<html:option value="">Seleccione</html:option>
	                        	<html:optionsCollection property="medios" value="id" label="descripcion"/>
	                        </html:select>
                      </td>                     
                    </tr>
                    <tr> 
                      <td align="left" class="barratablas"><span class="Verde">*</span>Archivo</td>                      
                      <td align="left" bordercolor="#FFFFFF" class="textos-formularios1a">
                      	<html:file property="rendicion" title="Examinar..." styleClass="form"/>
                      </td>                     
                    </tr>                                       
                </table>
                </td>
              </tr>
                            
		      <tr>
		        	<td bgcolor="#FFFFFF" align="right">
				        <table width="318" height="47" cellpadding="0" cellspacing="0">
				          <tr>             
				     		   <td height="25" align="right" bgcolor="#FFFFFF">
				     		   	<span class="titulos_formularios"><strong>
						  			<input name="Button2" type="button" class="btn2" value="Preview importación" onclick="javascript:preview();"/>
				            	</strong></span>
						  		</td>
				          </tr>          
				        </table>
		        	</td>
		      </tr>
            </table>
          </td>        
        </tr>
    </table>
    </html:form>
    </td>
</tr>    
</table>


