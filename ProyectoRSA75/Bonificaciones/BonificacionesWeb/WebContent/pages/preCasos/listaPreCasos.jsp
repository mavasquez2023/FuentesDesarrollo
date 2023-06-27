<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">

<script language="JavaScript">
function cambiarCajas3(){
	var cajaPrincipal3 = document.forms[3].cajaPrincipal3;
	var cajas = document.forms[3].indices3;

	for (i = 0; i < cajas.length; i++){
		cajas[i].checked = cajaPrincipal3.checked ;
	}
}

function confirmarActivar(valor){

	if(valor==4){
		return confirm('Desea realmente terminar este pre-caso?');	
	}

}

function verificaCantidadSeleccionados() {

	var cajas = document.forms[2].indices1;
	var seleccionados = 0;
	var MAX = 9;

	for (i = 0; i < cajas.length; i++){
		if(cajas[i].checked){
			seleccionados=seleccionados+1;
		}
	}
	
	if(seleccionados > MAX){
		alert("No puede seleccionar más de " + MAX + " Pre-Casos");
		return false;
	}else
		return true;

}
</script>

</head>
 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td>
    	<table width="97%" border="0" cellspacing="2" cellpadding="2">
	      <tr>
    	    <td class="caminito"><%@ include file="/includes/camino.jsp"%> Lista de Pre-Casos</td>
	      </tr>
    	</table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td width="77%" valign="top">
          	<html:errors/>
          
          	<html:form action="/setFichaCaso.do?source=preCaso">
 	        	<html:select property="opcion" styleClass="menuDespl">
            	  	<html:options name="opciones.valores" labelName="opciones"/>
	        	</html:select>
	        	<html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />
			</html:form>
          
		  <h4 class="txtHomeSmall">Lista de Pre-Casos por Generar Egreso</h4>
		            
          <html:form action="/prepareEgreso">
			<logic:notEmpty name="listaPreCasosPorGenerarPrimerEgreso">
				<logic:present name="puedeGenerarEgreso">
		            <table width="50%" border="0" align="center" cellpadding="1" cellspacing="2">
		              <tr>
		                <td align="center">
		                  <table width="25%" border="0" cellspacing="1" cellpadding="0">
		                    <tr>
		                      <td nowrap><p class="derecha">Generar Egreso</p>
		                      </td>
		                      <td width="41%"><div align="center"><html:image page="/images/botones/boton_ir.gif" alt="Generar Egreso" border="0" onclick="return verificaCantidadSeleccionados();"/></div>
		                      </td>
		                    </tr>
		                  </table>
		                </td>
		              </tr>
		            </table>
		        </logic:present>
            </logic:notEmpty>
            <table width="529" border="0" cellspacing="2" cellpadding="2">
              <tr>
				<td class="celdaColor1">
					&nbsp;
				</td>
                <td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.caso.id"/></p></td>
                <td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.socio.rut"/></p></td>
                <td class="celdaColor1"><p class="vinculosUp">Convenio</p></td>
                <td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.monto"/></p></td>
                <td class="celdaColor1"><p class="vinculosUp">Ingreso Caso</p></td>
                <td class="celdaColor1"><p class="vinculosUp">Concepto</p></td>
                <td width="19">&nbsp;</td>
              </tr>
            <!-- despliegue de los Casos -->
			<logic:notEmpty name="listaPreCasosPorGenerarPrimerEgreso">
	      		<logic:iterate id="register" name="listaPreCasosPorGenerarPrimerEgreso" indexId="i" type="cl.araucana.bienestar.bonificaciones.vo.CasoVO">
	              <tr class="lookup01">
					<td bgcolor="#F0F0F0" class="lookup01">
						<input type="checkbox" name="indices1" value="<%=i%>" >
					</td>
	                <td bgcolor="#F0F0F0"><html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoID" target="_top"><bean:write name="register" property="casoID"/></html:link></td>
	                <td bgcolor="#F0F0F0"><html:link page="/setFichaSocio.do?source=socio" paramId="rut" paramName="register" paramProperty="rutSocio" target="_top"><bean:write name="register" property="rutSocio"/></html:link></td>
	                <td bgcolor="#F0F0F0"><bean:write name="register" property="nombreConvenio"/></td>
	                <td bgcolor="#F0F0F0">$<bean:write name="register" property="montoEgresoTesoreria" formatKey="format.int"/></td>
	                <td bgcolor="#F0F0F0"><bean:write name="register" property="fechaIngreso" formatKey="format.date"/></td>
					<td bgcolor="#F0F0F0"><bean:write name="register" property="descripcionConcepto"/></td>                
	              </tr>
	            </logic:iterate>
            </logic:notEmpty>
            <logic:empty name="listaPreCasosPorGenerarPrimerEgreso">
	            <tr bgcolor="#ffffff" class="lookup01">
		            <td height="19" colspan='6'>
			            <h1 class="centrado">
			            	<br><br>
			            		<bean:message key="label.noCoincidencias"/>
			            </h1>
		            </td>
	            </tr>
			</logic:empty>
            </table>
          </html:form>
          
		  <h4 class="txtHomeSmall">Lista de Pre-Casos por Generar Ingreso/Egreso/Terminar</h4>

          <html:form action="/prepareOpcionPreCaso">
			<logic:notEmpty name="listaPreCasosConPorLoMenosUnEgresoGenerado">          
				<logic:present name="puedeGenerarIngreso">
		            <table width="50%" border="0" align="center" cellpadding="1" cellspacing="2">
		              <tr>
		                <td align="center">
		                   <table width="100%" border="0" cellspacing="1" cellpadding="0">
		                    <tr>
		                      <td width="100%">
		                      	<div align="center">
					        	<html:select property="accion" styleClass="menuDespl">
				            	  	<html:options name="acciones.valores" labelName="acciones"/>
					        	</html:select>
					        	<html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" onclick="return confirmarActivar(this.form.accion.value);"/>
					        	</div>
		                      </td>
		                    </tr>
		                   </table>
		                </td>
		              </tr>
		            </table>
		        </logic:present>
	        </logic:notEmpty>
            <table width="529" border="0" cellspacing="2" cellpadding="2">
              <tr>
				<td class="celdaColor1">
					<input type="checkbox" name="cajaPrincipal3" onclick="javascript:cambiarCajas3()">
				</td>
                <td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.caso.id"/></p></td>
                <td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.socio.rut"/></p></td>
                <td class="celdaColor1"><p class="vinculosUp">Convenio</p></td>
                <td class="celdaColor1"><p class="vinculosUp">Ingreso Caso</p></td>
                <td class="celdaColor1"><p class="vinculosUp">Concepto</p></td>
                <td class="celdaColor1"><p class="vinculosUp">Ver Detalle</p></td>
                <td width="19">&nbsp;</td>
              </tr>

            <!-- despliegue de los Casos -->
			<logic:notEmpty name="listaPreCasosConPorLoMenosUnEgresoGenerado">
	      		<logic:iterate id="register" name="listaPreCasosConPorLoMenosUnEgresoGenerado" indexId="i" type="cl.araucana.bienestar.bonificaciones.vo.CasoVO">
	              <tr class="lookup01">
					<td bgcolor="#F0F0F0" class="lookup01">
						<input type="checkbox" name="indices3" value="<%=i%>" >
					</td>	              
	                <td bgcolor="#F0F0F0"><html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoID" target="_top"><bean:write name="register" property="casoID"/></html:link></td>
	                <td bgcolor="#F0F0F0"><html:link page="/setFichaSocio.do?source=socio" paramId="rut" paramName="register" paramProperty="rutSocio" target="_top"><bean:write name="register" property="rutSocio"/></html:link></td>
	                <td bgcolor="#F0F0F0"><bean:write name="register" property="nombreConvenio"/></td>
	                <td bgcolor="#F0F0F0"><bean:write name="register" property="fechaIngreso" formatKey="format.date"/></td>
					<td bgcolor="#F0F0F0"><bean:write name="register" property="descripcionConcepto"/></td>                
	                <td bgcolor="#F0F0F0">
	                	<html:link page="/verDetalleTesoreria.do" paramId="casoId" paramName="register" paramProperty="casoID" target="_top">
		                	<html:img page="/images/view.gif" border="0" />
		                </html:link>
	                </td>
	              </tr>
	            </logic:iterate>
            </logic:notEmpty>
            <logic:empty name="listaPreCasosConPorLoMenosUnEgresoGenerado">
	            <tr bgcolor="#ffffff" class="lookup01">
		            <td height="19" colspan='6'>
			            <h1 class="centrado">
			            	<br><br>
			            		<bean:message key="label.noCoincidencias"/>
			            </h1>
		            </td>
	            </tr>
			</logic:empty>
            </table>
          </html:form>		  
          
          </td>
        </tr>
      </table>      
      <p class="lookup01">&nbsp;</p>
      </td>
  </tr>
</table>
<%@ include file="/includes/pie.jsp" %>
<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>
