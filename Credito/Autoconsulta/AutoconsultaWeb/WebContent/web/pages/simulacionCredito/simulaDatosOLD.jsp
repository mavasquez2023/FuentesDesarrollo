  <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>  
            <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        
                <tr>
                  <td width="97%" class="title15">Simulador de Cr&eacute;dito</td>
                </tr>
              </table></td> 
          </tr>    
    <tr>
      <td valign="top"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr valign="top">
            <td width="78%"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td valign="top" background="images/divider_blue.gif"><img src="images/block.gif" width="3" height="1"></td>
                </tr>
                <tr>
                  <td valign="top"><br> <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="3%"><img src="images/arrow2.gif" width="13" height="14"></td>
                        <td width="97%" class="c13az"><b>Ingreso de Datos B&aacute;sicos</b></td>
                      </tr>
                    </table></td>
                </tr>
                <tr>
                  <td align="center" valign="top"> <table width="90%" border="0" cellspacing="2" cellpadding="0">
						<logic:equal name="columna" value='<%=Constants.DATOS_SC_INGRESOS_LIQUIDOS%>'>
							<td align="right">
								
							</td>
							<td>
								&nbsp;
							</td> 
							<td>
								&nbsp;
							</td>								
						</logic:equal> 
					
						<logic:present name="afiliado">  
	                      <tr>
	                        <td  class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12" now>Remuneraci&oacute;n L&iacute;quida $:</td>
	                        <td class="data1"><html:text property="ingresosLiquidos"  size='10' maxlength="9" onkeypress="return acceptNum(event)" onchange="javascript:formatNumber(this);"/> </td>
	                        <td  class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12">Monto Solicitado $:</td>
	                        <td class="data1"><html:text property="montoSolicitado" size='10' maxlength="9" onkeypress="return acceptNum(event);" onchange="javascript:formatNumber(this);"/></td>                          
	                      </tr>
                       </logic:present>
						<logic:notPresent name="afiliado">  
	                      <tr>
	                        <td  class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12" now>Pensi&oacute;n l&iacute;quida $:</td>
	                        <td class="data1"><html:text property="ingresosLiquidos"  size='10' maxlength="9" onkeypress="return acceptNum(event)" onchange="javascript:formatNumber(this);"/> </td>
	                        <td  class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12">Monto Solicitado $:</td>
	                        <td class="data1"><html:text property="montoSolicitado" size='10' maxlength="9" onkeypress="return acceptNum(event);" onchange="javascript:formatNumber(this);"/></td>                          
	                      </tr>						               
                      </logic:notPresent>                      
                      <tr>
                        <td  class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12" now>Nº Cuotas:</td>
                        <td class="data1"><html:text property="cantidadCuotas" size='10' maxlength="9" onkeypress="return acceptNum(event)"/></td>
                        <td  class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12">Fecha Nacimiento
						<td nowrap="nowrap" class="data1"><html:text property="fechaNacimiento" size="10" readonly="true"  /> 
						<a href="#"	onClick="cal1.select(document.forms[0].fechaNacimiento,'anchor1','dd-MM-yyyy'); return false;"
								title="Ingrese la fecha de nacimiento"
								name="anchor1" id="anchor1"> <img
								src="images/calendario/ic_calendar.gif"
								border="0"> </a>
                         </td>  
                      </tr> 
                      <logic:present name="afiliado">
                      <tr>
                        <td  class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12">Fecha ingreso Empresa
							<td nowrap="nowrap" class="data1"><html:text property="fechaIngresoEmpresa" size="10" readonly="true" /> <a href="#"
								onClick="cal1.select(document.forms[0].fechaIngresoEmpresa,'anchor2','dd-MM-yyyy'); return false;"
								title="Ingrese la fecha de ingreso a la empresa"
								name="anchor1" id="anchor2"> <img
								src="images/calendario/ic_calendar.gif"
								border="0"> </a>
						  </td>                           
                        <td  class="data1" nowrap="nowrap">&nbsp;</td>
                        <td class="data1">&nbsp;</td>                                                 
                      </tr> 
                      </logic:present>                                          
                    </table>
                    </td>
                </tr>
                <tr>
                  <td valign="top" background="images/divider_blue.gif"><img src="images/block.gif" width="3" height="1"></td>
                </tr>
               <tr>
                  <td valign="top"><br> <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="3%"><img src="images/arrow2.gif" width="13" height="14"></td>
                        <td width="97%" class="c13az"><b>Seguros</b></td>
                      </tr>
                    </table></td>
                </tr>
                <tr>
                  <td align="center" valign="top"> 
                  	<table width="90%" border="0" cellspacing="2" cellpadding="0" align="center">
                      <tr>
                        <td class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12">Seguro de Desgravamen:</td>
                        <td class="data1">Si&nbsp;&nbsp;<input type="radio" name="seguroDesgravamen" value="1" checked="checked"/>No&nbsp;&nbsp;<input type="radio" name="seguroDesgravamen" value="0"/></td>
                      </tr>
                      <tr>
                        <td class="data1"><img src="images/asteric.gif" width="9" height="12">Seguro de Vida:</td>
                        <td class="data1">Si&nbsp;&nbsp;<input type="radio" name="seguroVida" value="1"  checked="checked"/>No&nbsp;&nbsp;<input type="radio" name="seguroVida" value="0"/></td>
                      </tr>  
                      <tr>
                        <td class="data1"><img src="images/asteric.gif" width="9" height="12">Seguro de Cesant&iacute;a:</td>
                        <td class="data1">Si&nbsp;&nbsp;<input type="radio" name="seguroCesantia" value="1" checked="checked"/>No&nbsp;&nbsp;<input type="radio" name="seguroCesantia" value="0"/></td>
                      </tr>                                           
                      <tr>
                        <td class="data1"></td>
                        <td></td>
                        <td class="data1"></td>
                        <td class="c13az"></td>
                      </tr>
                    </table></td>
                </tr>
         <tr>
                  <td valign="top" background="images/divider_blue.gif"><img src="images/block.gif" width="3" height="1"></td>
                </tr>

              </table></td>
          </tr>
        </table>
        </td>
    </tr>
  </table>  
<script>
var fechaNac='<bean:write name="PARAMForm" property="fechaNacimiento"/>'
if(fechaNac!="" && fechaNac!="--"){
	var dia= fechaNac.substring(0,2);
	var mes= fechaNac.substring(2,4);
	var agno= fechaNac.substring(4,8);
	var fechaNacimiento=  dia + "-" +  mes + "-" + agno;
	document.PARAMForm.fechaNacimiento.value = fechaNacimiento;
}
/*SETEO FECHA DE INGRESO A EMPRESA PARA VOLVER A SIMULAR*/

var fechaEmpresa='<bean:write name="PARAMForm" property="fechaIngresoEmpresa"/>'
if(fechaEmpresa!="" && fechaNac!="--"){
	var diaE= fechaEmpresa.substring(0,2);
	var mesE= fechaEmpresa.substring(2,4);
	var agnoE= fechaEmpresa.substring(4,8);
	var fechaIngresoEmpresa=  diaE + "-" +  mesE + "-" + agnoE;
	document.PARAMForm.fechaIngresoEmpresa.value = fechaIngresoEmpresa;
}
</script>
