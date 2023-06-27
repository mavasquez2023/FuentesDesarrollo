<%@ include file="/html/comun/taglibs.jsp" %>
<html:form action="/DetalleMapeoArchivos" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden name="accion" property="accion" styleId="accion" value="mapeo" />
<html:hidden name="subAccion" property="subAccion" styleId="subAccion" value="archivosLista" />
<html:hidden name="subSubAccion" property="subSubAccion" styleId="subSubAccion" value="archivosEditar" />
<c:set var="tipoSeparador"><%=request.getAttribute("tipoSeparador")%></c:set>
<c:set var="caracterSeparador"><%=request.getAttribute("caracterSeparador")%></c:set>
<table width="590" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td>
		<html:errors />
	</td>
</tr>
<tr>
	<td>
		<html:messages id="msg" message="true">
			<div class="msgExito">${msg}</div>
		</html:messages>
	</td>
</tr>
<tr>
	<td valign="top">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td>
          <table border="0" cellpadding="0" cellspacing="0" class="textos-formularios3">
          	<tr> 
            	<td colspan="2">&nbsp;</td>
         	</tr>
          	<tr> 
	            <td width="30%"><strong>Grupo de Convenios:</strong></td>
	            <td>
	            	<nested:select property="opcGrupoConvenio" styleClass="campos" onchange="javascript:bCancel=true;submit();">
	            		<nested:optionsCollection property="gruposConvenio" />
	            	</nested:select>
               	</td>
          	</tr>
          	<tr>
	            <td><strong>Tipo de N&oacute;mina:</strong></td>
	            <td>
	            	<nested:select property="opcTipoNomina" styleClass="campos" onchange="javascript:bCancel=true;submit();">
	            		<nested:optionsCollection property="tiposNomina" />
	            	</nested:select>
               	</td>
          	</tr>
          	<c:if test="${tipoSeparador == 2}">
	          	<tr>
		            <td><strong>Caracter Separador:</strong></td>
		            <td>
		            	<c:out value="${caracterSeparador}"/>
	               	</td>
	          	</tr>
          	</c:if>
          	<tr> 
            	<td colspan="2">&nbsp;</td>
         	</tr>
            </table>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        	<tr valign="bottom"> 
            	<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Edici&oacute;n de Mapeo de campos de archivo</strong></td>
            </tr>
       		</table>

       		<c:choose>
       			<c:when test="${tipoSeparador == 1}">
       				<c:set var="estiloCabecera" value='class="barra_tablas"'/>
       				<c:set var="estiloCelda"    value='class="textos_formularios"'/>
       			</c:when>
       			<c:otherwise>
       				<c:set var="estiloCabecera" value='style="display:none;"'/>
       				<c:set var="estiloCelda"    value='style="display:none;"'/>
       			</c:otherwise>
       		</c:choose>
       		
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
            	<tr> 
                    <td align="center" bgcolor="#FFFFFF">
               		<table border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
						<tr>
		                 	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;Nº</td>
		                 	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
		                 	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Posici&oacute;n <c:if test="${tipoSeparador == 1}">Inicial</c:if> *</td>
		                 	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" ${estiloCabecera}>Largo *</td>
	               		</tr>
	               		<nested:notEmpty property="consulta">
	               			<nested:iterate property="consulta" indexId="nFila">
	               				<c:choose>
	               					<c:when test="${(consulta.idConcepto < 7 || consulta.idConcepto== 34 || consulta.idConcepto== 35 || consulta.idConcepto== 36 || consulta.idConcepto== 37 ) && prodCaja== true}">
	               						<tr>
				               				<nested:hidden property="idConcepto" styleId="con${nFila}"/>
				                    		<td align="right" valign="middle" nowrap="nowrap" class="textos_formularios">
				                    			${nFila+1}
				                    		</td>
				                    		<td align="right" valign="middle" nowrap="nowrap" class="textos_formularios">
				                    			<nested:hidden property="nombre" write="true" styleId="txt${nFila}"/>
				                    		</td>
				                    		<td align="right" valign="middle" nowrap="nowrap" class="textos_formularios">
				                    			<nested:text property="posicion" styleId="pos${nFila}" styleClass="campos" maxlength="4" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
				                    		</td>
				                    		<td align="right" valign="middle" nowrap="nowrap" ${estiloCelda}>
				                    			<nested:text property="largo" styleId="lar${nFila}" styleClass="campos" maxlength="4" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
				                    		</td>
				                    		<nested:hidden property="tipoSeparador" />
				                    		<nested:hidden property="caracterSeparador" />
				                  		</tr>
	               					</c:when>
	               					<c:when test="${(consulta.idConcepto < 134 || consulta.idConcepto > 136) && prodCaja== false}">
				               			<tr>
				               				<nested:hidden property="idConcepto" styleId="con${nFila}"/>
				                    		<td align="right" valign="middle" nowrap="nowrap" class="textos_formularios">
				                    			${nFila+1}
				                    		</td>
				                    		<td align="right" valign="middle" nowrap="nowrap" class="textos_formularios">
				                    			<nested:hidden property="nombre" write="true" styleId="txt${nFila}"/>
				                    		</td>
				                    		<td align="right" valign="middle" nowrap="nowrap" class="textos_formularios">
				                    			<nested:text property="posicion" styleId="pos${nFila}" styleClass="campos" maxlength="4" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
				                    		</td>
				                    		<td align="right" valign="middle" nowrap="nowrap" ${estiloCelda}>
				                    			<nested:text property="largo" styleId="lar${nFila}" styleClass="campos" maxlength="4" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
				                    		</td>
				                    		<nested:hidden property="tipoSeparador" />
				                    		<nested:hidden property="caracterSeparador" />
				                  		</tr>
	               					</c:when>
	               					<c:otherwise>
		               					<logic:equal name="tienePrevired" value="true">
		               						<tr>
					               				<nested:hidden property="idConcepto" />
					                    		<td align="right" valign="middle" nowrap="nowrap" class="textos_formularios">
					                    			${nFila+1}
					                    		</td>
					                    		<td align="right" valign="middle" nowrap="nowrap" class="textos_formularios">
					                    			<nested:hidden property="nombre" write="true" styleId="txt${nFila}"/>
					                    		</td>
					                    		<td align="right" valign="middle" nowrap="nowrap" class="textos_formularios">
					                    			<nested:text property="posicion" styleId="pos${nFila}" styleClass="campos" maxlength="4" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
					                    		</td>
					                    		<td align="right" valign="middle" nowrap="nowrap" ${estiloCelda}>
					                    			<nested:text property="largo" styleId="lar${nFila}" styleClass="campos" maxlength="4" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
					                    		</td>
					                    		<nested:hidden property="tipoSeparador" />
					                    		<nested:hidden property="caracterSeparador" />
				                    		</tr>
		               					</logic:equal>
									</c:otherwise>
	               				</c:choose>
	                  		</nested:iterate>
                  		</nested:notEmpty>
                  		<nested:empty property="consulta">
                  			<tr>
	                    		<td align="right" valign="middle" nowrap="nowrap" class="textos_formularios" colspan="4">
	                    			No hay mapeo de campos de archivo definidos para este grupo de convenios y Tipo de N&oacute;mina
	                    		</td>
	                    	</tr>
                  		</nested:empty>
					</table>
					</td>
				</tr>
            </table>
            </td>
        </tr>
        <tr align="center">
         	<td valign="top"><br />
         		<html:submit property="operacion" value="${guardar}"  styleClass="btn_grande"/>
         		<html:cancel property="operacion" value="${cancelar}" styleClass="btn_grande"/>
            </td>
		</tr>
	</table>
    </td>
</tr>
</table>
</html:form>
<script language="javaScript"> 
var control='<%=request.getAttribute("errorLargo")%>';
if(control!='null'){
	alert("Errores de validación:\n\n" +control);
} 

	function validaFormulario(f) 
	{
		//GMALLEA 13/02/2012 Si es GRATIFICACION o RELIQUIDACION no valida el separador 
		var indice = document.MapeoArchivosActionForm.opcTipoNomina.selectedIndex; 
		var textoEscogido = document.MapeoArchivosActionForm.opcTipoNomina.options[indice].text;
   			  
	   if('GRATIFICACION' == textoEscogido || 'RELIQUIDACION' == textoEscogido){
	      return true;
	   }

		var txts = document.getElementsByTagName("input");
		var sMsje = "";
		var largo = 0;
		var tipoSeparador = ${tipoSeparador};
		for (var i = 0; i < txts.length; i++) 
		{
			var txt = txts[i];
			if (txt.type == "text") 
			{
				num = txt.id.substr(3);
				var texto = document.getElementById("txt" + num).value;
				if (!validaReq(txt)) 
				{
					sMsje = "* Valor para ítem \"" + document.getElementById("txt" + num).value + "\" es requerido\n";
					break;
				} else if (!validaUInt(txt.value) || !parseInt(txt.value, 10) || (parseInt(txt.value, 10) < 0)){
					//Campo obligatorio y con LARGO como tipo de separador
					if(texto.substr(texto.length-1, texto.length)=='*' && tipoSeparador == 1){
						sMsje = "* Un campo del ítem \"" + document.getElementById("txt" + num).value + "\" no es un número mayor que 0\n";
						break;
					}
				}

				if (txt.id.substring(0,3) == "pos")
					largo++;
			}
		}

		if (sMsje == "")
			sMsje = validaSolapamiento(largo);

		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		return true;
	}

	<c:choose>
		<c:when test="${tipoSeparador == 1}">
			//Función solapamiento para Separación de nómina por Largo
			function validaSolapamiento(largo) 
			{
				var aMapeo = new Array(largo);
				for (var i = 0; i < largo; i++)
				{
					aMapeo[i] = new Array(2);
					aMapeo[i][0] = parseInt(document.getElementById("pos" + i).value);
					aMapeo[i][1] = parseInt(document.getElementById("lar" + i).value);
				}
				
				for (var i = 0; i < largo; i++) 
				{
					for (var j = i + 1; j < largo; j++) 
					{
						if ((aMapeo[i][0] < aMapeo[j][0]) && ((aMapeo[i][0] + aMapeo[i][1] - 1) >= aMapeo[j][0]))
							return "Los ítemes \"" + document.getElementById("txt" + i).value + "\" y \"" + document.getElementById("txt" + j).value + "\" se solapan.\n";
						else if ((aMapeo[i][1] > 0) && (aMapeo[i][0] >= aMapeo[j][0]) && (aMapeo[i][0] <= (aMapeo[j][0] + aMapeo[j][1] - 1)))
							return "Los ítemes \"" + document.getElementById("txt" + i).value + "\" y \"" + document.getElementById("txt" + j).value + "\" se solapan.\n";
					}
				}
				return "";
			}
		</c:when>
		<c:otherwise>
			//Función solapamiento para Separación de nómina por caracter separador
			function validaSolapamiento(largo) {
				var aMapeo = new Array(largo);
			
				for (var i = 0; i < largo; i++) {
					aMapeo[i] = parseInt(document.getElementById("pos" + i).value);
				}
				
				for (var i = 0; i < largo; i++) {
					for (var j = i + 1; j < largo; j++) {
						if (aMapeo[i] == aMapeo[j] && (aMapeo[i] != "0")) {
							// Solo los conceptos Entidad Ex-Caja (9) y Regimen Impositivo (10) pueden tener el mismo valor
							if (!(document.getElementById("con" + i).value == 9 && document.getElementById("con" + j).value == 10) && !(document.getElementById("con" + i).value == 10 && document.getElementById("con" + j).value == 9))
							return "Los ítemes \"" + document.getElementById("txt" + i).value + "\" y \"" + document.getElementById("txt" + j).value + "\" se solapan.\n";
						}
					}
				}
				return ""
			}
		</c:otherwise>
	</c:choose>	

	function foco()
	{
		if(document.getElementById('pos0'))
			document.getElementById('pos0').focus();
	}
	foco();
</script>
