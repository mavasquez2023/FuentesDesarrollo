<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="97%" class="title15">
          
          	<logic:present name="nombreSimulador">
              
                <bean:write name="nombreSimulador" scope="session" />
              </logic:present>
          
          </td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr valign="top">
          <td width="78%">
          <div id="tabla_simulador">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td valign="top" ><img src="images/block.gif" width="3" height="1"></td>
              </tr>
              <tr>
                <td valign="top"><br>
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      
                      <td  class="c13az"><b>Ingreso de Datos B&aacute;sicos</b>
                        <div class="hr"></div></td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td align="center" valign="top"><table width="90%" border="0" cellspacing="2" cellpadding="0">
                    <logic:equal name="columna" value='<%=Constants.DATOS_SC_INGRESOS_LIQUIDOS%>'>
                      <td align="right"></td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                    </logic:equal>
                    <logic:present name="afiliado">
                      <tr>
                        <td class="data1" width="20%" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12" now>Remuneraci&oacute;n L&iacute;quida $:</td>
                        <td class="LetraNegraDiezNormalArial"><html:text property="ingresosLiquidos"  size='10' maxlength="9" onkeypress="return acceptNum(event)" onchange="javascript:formatNumber(this);"/></td>
                        <td class="data1" width="20%" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12">Monto Solicitado $:</td>
                        <td class="LetraNegraDiezNormalArial"><html:text property="montoSolicitado" size='10' maxlength="9" onkeypress="return acceptNum(event);" onchange="javascript:formatNumber(this);"/></td>
                      </tr>
                    </logic:present>
                    <logic:notPresent name="afiliado">
                      <tr>
                        <td  class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12" now>Pensi&oacute;n l&iacute;quida $:</td>
                        <td class="LetraNegraDiezNormalArial"><html:text property="ingresosLiquidos"  size='10' maxlength="9" onkeypress="return acceptNum(event)" onchange="javascript:formatNumber(this);"/></td>
                        <td  class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12">Monto Solicitado $:</td>
                        <td class="LetraNegraDiezNormalArial"><html:text property="montoSolicitado" size='10' maxlength="9" onkeypress="return acceptNum(event);" onchange="javascript:formatNumber(this);"/></td>
                      </tr>
                    </logic:notPresent>
                    <tr>
                      <td  class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12" now>Nº Cuotas:</td>
                      <td class="LetraNegraDiezNormalArial"><html:text property="cantidadCuotas" size='10' maxlength="9" onkeypress="return acceptNum(event)"/></td>
                      <td  class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12" now>Fecha Nacimiento:</td>
                      <td valign="middle" class="LetraNegraDiezNormalArial"><script type="text/javascript" language="javascript">
								calFechaNac.writeControl();
							</script></td>
                    </tr>
                    <logic:present name="afiliado">
                      <tr>
                        <td  class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12">Fecha ingreso Empresa</td>
                        <td  valign="middle" class="LetraNegraDiezNormalArial"><script type="text/javascript" language="javascript">
								calFechaIngEmpresa.writeControl(); 
							</script></td>
                        <td  class="data1" nowrap="nowrap">&nbsp;</td>
                        <td class="data1">&nbsp;</td>
                      </tr>
                    </logic:present>
                    <tr>
                      <td  class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12">Sucursal:</td>
                      <td  colspan="2" align="right">
                      	<html:select property="oficina" styleClass="LetraNegraDiezNormalArial">
                          <option value="">----Seleccione----</option>
                          <html:optionsCollection name="Crc468" property="oficinas" label="nombreOficina" value="codigoOficina" />
                        </html:select></td>
                      <td class="data1">&nbsp;</td>
                    </tr>
                    <logic:present name="afiliado">
                      <tr>
                        <td class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12">Tipo Renta:</td>
                        <td class="data1">Fija&nbsp;&nbsp;
                          <input type="radio" name="tipoRenta" value="F" checked="checked"/>
                          Variable&nbsp;&nbsp;
                          <input type="radio" name="tipoRenta" value="V"/></td>
                        <td  class="data1" nowrap="nowrap">&nbsp;</td>
                        <td class="data1">&nbsp;</td>
                      </tr>
                    </logic:present>
					<!--
                    <tr>
                      <td class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12">Servicio Teleasistencia:</td>
                      <td class="data1">Si&nbsp;&nbsp;
                        <input type="radio" name="servicioTeleasistencia" value="S" checked="checked"/>
                        No&nbsp;&nbsp;
                        <input type="radio" name="servicioTeleasistencia" value="N"/></td>
                      <td  class="data1" nowrap="nowrap">&nbsp;</td>
                      <td class="data1">&nbsp;</td>
                    </tr> -->
                  </table></td>
              </tr>
              <tr>
                <td valign="top" ><img src="images/block.gif" width="3" height="1"></td>
              </tr>
              <tr>
                <td valign="top"><br>
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      
                      <td width="97%" class="c13az"><b>Seguros</b><div class="hr"></div></td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td align="center" valign="top"><table width="90%" border="0" cellspacing="2" cellpadding="0" align="center">
                    <tr>
                      <td class="data1" nowrap="nowrap"><img src="images/asteric.gif" width="9" height="12">Seguro de Desgravamen:</td>
                      <td class="data1">Si&nbsp;&nbsp;
                        <input type="radio" name="seguroDesgravamen" value="1" checked="checked" disabled="disabled"/>
                        No&nbsp;&nbsp;
                        <input type="radio" name="seguroDesgravamen" value="0" disabled="disabled" /></td>
                    </tr>
					<!--
                    <tr>
                      <td class="data1"><img src="images/asteric.gif" width="9" height="12">Seguro de Vida:</td>
                      <td class="data1">Si&nbsp;&nbsp;
                        <input type="radio" name="seguroVida" value="1"  checked="checked"/>
			                        No&nbsp;&nbsp;
                        <input type="radio" name="seguroVida" value="0"/>
                       </td>
                    </tr> -->
					<%	
						String estilo = "inline";
						String sc = (String) session.getAttribute("scflag");
						if (sc!=null && sc.equals("1"))
							estilo = "display:none";
					%>
					<html:hidden property="seguroVida" value="0" />
                    <logic:present name="afiliado">
                      <tr style = "<%=estilo%>">
                        <td class="data1"><img src="images/asteric.gif" width="9" height="12">Seguro de Cesant&iacute;a:</td>
                        <td class="data1">Si&nbsp;&nbsp;
                          <input type="radio" name="seguroCesantia" value="1"/>
                          No&nbsp;&nbsp;
                          <input type="radio" name="seguroCesantia" value="0" checked="checked"/></td>
                      </tr>
                    </logic:present>
                    <tr>
                      <td class="data1"></td>
                      <td></td>
                      <td class="data1"></td>
                      <td class="c13az"></td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td valign="top" ><img src="images/block.gif" width="3" height="1"></td>
              </tr>
            </table>
            </div>
            </td>
        </tr>
      </table></td>
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