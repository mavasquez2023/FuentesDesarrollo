<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<TITLE>Control de Procesos</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
	<script src="<c:url value='/js/comun.js'/>"></script>
	
	<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
</head>
<body>
<html:form action="/ControlProcesosListar" styleId="formulario">
	<table width="990" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<html:errors/>
			</td>
		</tr>
	<tr>
		<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom"> 
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Control de Procesos</strong></td>
				</tr>
			</table>
		</td>
	</tr>
		<tr>
			<td>
				<html:messages id="msg" message="true">
					<div class="msgExito">${msg}</div>
				</html:messages>
			</td>
		</tr>
  		<tr align="center"> 
   			<td valign="top" bgcolor="#CCCCCC">
   				<table width="100%" border="0" cellpadding="0" cellspacing="1">
		   			<tr> 
		     			<td colspan="2" align="center" bgcolor="#FFFFFF">
		     				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
			             		<tr class="subtitulos_tablas">
				             		<td width="2%" align="center" valign="middle"  bordercolor="#CCCCCC" class="barra_tablas">&nbsp;</td>
			                		<td width="5%" align="center" valign="middle"  bordercolor="#CCCCCC" class="barra_tablas">Categor&iacute;a de pago</td>
					               	<td width="10%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Grupo</td>
					               	<td width="10%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">RUT Empresa</td>
					               	<td width="10%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n social</td>
									<td width="10%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Convenio</td>
									<td width="10%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Tipo Proceso</td>
									<td width="14%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Nº Comprobante (c&oacute;digo barra)</td>
									<td width="10%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Modo de Pago</td>
									<td width="10%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Total Pagado</td>
									<td width="6%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Total Trab.</td>
					         	</tr>
					         </table>
						</td>
					</tr>		  
		         	<tr>
		         		<td width="1%"  class="textos_formularios" align="left">
		         			<div align="center">
								<a href="javascript:;" onclick="swapAll('pagadas', 'img1');">
									<img id="img1" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Contraer" title="Contraer" />
								</a>
							</div>
		         		</td>
		         		<td width="98%"  class="textos_formularios" align="left">
							<strong>Pagadas</strong>
		         		</td>
		         	</tr>
	         		<nested:notEmpty property="listaPagadas">
		         		<tr id='pagadas' style="display:none">
		         			<td colspan="2" >
		         				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
									<nested:iterate id="filaConsulta" property="listaPagadas" indexId="nFila">
										<c:choose>
									    	<c:when test="${nFila % 2 == 0}">
									   		   	<c:set var="estilo" value="textos_formularios"/>
									   	    </c:when>
						   					<c:otherwise>
						   						<c:set var="estilo" value="textos-formcolor2"/>
						   					</c:otherwise>
										</c:choose>
					           			<tr align="right">
							           		<td width="2%" height="20" align="left" valign="middle"  class="${estilo}">
												&nbsp;
					                   		</td>
						               		<td width="6%" height="20" align="left" valign="middle"  class="${estilo}">
												&nbsp;
					                   		</td>
					                   		<td width="11%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="grupo"/>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				<div align="right">&nbsp;<nested:write property="rutEmpresa"/></div>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="razonSocial"/>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="convenio"/>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="tipoProceso"/>
					                   		</td>
					                   		<td width="14%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				<div align="right">&nbsp;<nested:write property="idCodigoBarra"/></div>
					                   		</td>
					                   		<td width="11%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="modoPago"/>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				<div align="right">$&nbsp;<nested:write property="totalPagadoMonto"/></div>
					                   		</td>
					                   		<td width="9%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				<div align="right">&nbsp;<nested:write property="totalTrabajadores"/></div>
					                   		</td>
					               		</tr>		               		
			             			</nested:iterate>
			             			<c:choose>
									    <c:when test="${nFila % 2 != 0}">
								   		   	<c:set var="estilo" value="textos_formularios"/>
								   	    </c:when>
					   					<c:otherwise>
					   						<c:set var="estilo" value="textos-formcolor2"/>
					   					</c:otherwise>
									</c:choose>
				               		<tr>
						           		<td width="41%" colspan="5" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;Total categor&iacute;a Pagadas de <nested:write property="numEmpresasPA"/> Empresas
				                   		</td>		                   		
				                   		<td width="9%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;
				                   		</td>
				                   		<td width="30%" colspan="3" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;
				                   		</td>
				                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				<div align="right">$&nbsp;<nested:write property="totPagadoPAMonto"/></div>
				                   		</td>
				                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				<div align="right">&nbsp;<nested:write property="numTrabajadoresPA"/></div>
				                   		</td>
				               		</tr>
					  			</table>
					  		</td>
					  	</tr>
				  	</nested:notEmpty>
				  	<nested:empty property="listaPagadas">
			   			<tr id='pagadas' style="display:none">
				   			<td colspan="11"  class="textos_formularios" align="left">
								No Existen datos
			         		</td>
			   			</tr>
			   		</nested:empty>			   		
		         	<tr>
		         		<td width="1%"  class="textos_formularios" align="left">
		         			<div align="center">
								<a href="javascript:;" onclick="swapAll('noPagadas', 'img2');">
									<img id="img2" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Contraer" title="Contraer" />
								</a>
							</div>
		         		</td>
		         		<td width="98%"  class="textos_formularios" align="left">
							<strong>No Pagadas</strong>
		         		</td>
		         	</tr>
	         		<nested:notEmpty property="listaNoPagadas">
		         		<tr id='noPagadas' style="display:none">
		         			<td colspan="2">
			         			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
									<nested:iterate id="filaConsulta" property="listaNoPagadas" indexId="nFila">
										<c:choose>
										    <c:when test="${nFila % 2 == 0}">
									   		   	<c:set var="estilo" value="textos_formularios"/>
									   	    </c:when>
						   					<c:otherwise>
						   						<c:set var="estilo" value="textos-formcolor2"/>
						   					</c:otherwise>
										</c:choose>
						           		<tr>
							           		<td width="2%" height="20" align="left" valign="middle"  class="${estilo}">
												&nbsp;
					                   		</td>
						               		<td width="6%" height="20" align="left" valign="middle"  class="${estilo}">
												&nbsp;
					                   		</td>
					                   		<td width="11%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="grupo"/>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				<div align="right">&nbsp;<nested:write property="rutEmpresa"/></div>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="razonSocial"/>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="convenio"/>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="tipoProceso"/>
					                   		</td>
					                   		<td width="14%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				<div align="right">&nbsp;<nested:write property="idCodigoBarra"/></div>
					                   		</td>
					                   		<td width="11%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="modoPago"/>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				<div align="right">$&nbsp;<nested:write property="totalPagadoMonto"/></div>
					                   		</td>
					                   		<td width="9%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				<div align="right">&nbsp;<nested:write property="totalTrabajadores"/></div>
					                   		</td>
					               		</tr>
				               		</nested:iterate>
				               		<c:choose>
									    <c:when test="${nFila % 2 != 0}">
								   		   	<c:set var="estilo" value="textos_formularios"/>
								   	    </c:when>
					   					<c:otherwise>
					   						<c:set var="estilo" value="textos-formcolor2"/>
					   					</c:otherwise>
									</c:choose>
				               		<tr>
						           		<td width="41%" colspan="5" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;Total categor&iacute;a No Pagadas de <nested:write property="numEmpresasNP"/> Empresas
				                   		</td>
				                   		<td width="9%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;
				                   		</td>
				                   		<td width="30%" colspan="3" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;
				                   		</td>
				                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				<div align="right">$&nbsp;<nested:write property="totPagadoNPMonto"/></div>
				                   		</td>
				                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				<div align="right">&nbsp;<nested:write property="numTrabajadoresNP"/></div>
				                   		</td>
				               		</tr>	             		
			               		</table>
					  		</td>
					  	</tr>
				  	</nested:notEmpty>				  	
			       	<nested:empty property="listaNoPagadas">
			   			<tr id='noPagadas' style="display:none">
				   			<td colspan="11"  class="textos_formularios" align="left">
								No Existen datos
			         		</td>
			   			</tr>
			   		</nested:empty>			   		
		         	<tr>
		         		<td width="1%"  class="textos_formularios" align="left">
		         			<div align="center">
								<a href="javascript:;" onclick="swapAll('conDeclaro', 'img3');">
									<img id="img3" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Contraer" title="Contraer" />
								</a>
							</div>
		         		</td>
		         		<td width="98%"  class="textos_formularios" align="left">
							<strong>Con Declaro y No Pago</strong>
		         		</td>
		         	</tr>
	         		<nested:notEmpty property="listaConDeclaro">
	         			<tr id='conDeclaro' style="display:none">
	         				<td colspan="2">
	         					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
									<nested:iterate id="filaConsulta" property="listaConDeclaro" indexId="nFila">
										<c:choose>
										    <c:when test="${nFila % 2 == 0}">
									   		   	<c:set var="estilo" value="textos_formularios"/>
									   	    </c:when>
						   					<c:otherwise>
						   						<c:set var="estilo" value="textos-formcolor2"/>
						   					</c:otherwise>
										</c:choose>
						           		<tr>
							           		<td width="5%" height="20" align="left" valign="middle"  class="${estilo}">
												&nbsp;
					                   		</td>
						               		<td width="5%" height="20" align="left" valign="middle"  class="${estilo}">
												&nbsp;
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="grupo"/>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				<div align="right">&nbsp;<nested:write property="rutEmpresa"/></div>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="razonSocial"/>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="convenio"/>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="tipoProceso"/>
					                   		</td>
					                   		<td width="14%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				<div align="right">&nbsp;<nested:write property="idCodigoBarra"/></div>
					                   		</td>
					                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				&nbsp;<nested:write property="modoPago"/>
					                   		</td>
					                   		<td width="10%" height="20" align="right" valign="middle"  class="${estilo}">
				                   				<div align="right">$&nbsp;<nested:write property="totalPagadoMonto"/></div>
					                   		</td>
					                   		<td width="16%" height="20" align="left" valign="middle"  class="${estilo}">
				                   				<div align="right">&nbsp;<nested:write property="totalTrabajadores"/></div>
					                   		</td>
					               		</tr>
		               				</nested:iterate>
				               		<c:choose>
									    <c:when test="${nFila % 2 != 0}">
								   		   	<c:set var="estilo" value="textos_formularios"/>
								   	    </c:when>
					   					<c:otherwise>
					   						<c:set var="estilo" value="textos-formcolor2"/>
					   					</c:otherwise>
									</c:choose>
				               		<tr>
						           		<td width="41%" colspan="5" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;Total categor&iacute;a Con Declaro y no pago de <nested:write property="numEmpresasCD"/> Empresas
				                   		</td>
				                   		<td width="9%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;
				                   		</td>
				                   		<td width="30%" colspan="3" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;
				                   		</td>
				                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				<div align="right">$&nbsp;<nested:write property="totPagadoCDMonto"/></div>
				                   		</td>
				                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				<div align="right">&nbsp;<nested:write property="numTrabajadoresCD"/></div>
				                   		</td>
				               		</tr>	  
		               			</table>
		               		</td>
		               	</tr>           		
				  	</nested:notEmpty>
			       	<nested:empty property="listaConDeclaro">
			   			<tr id='conDeclaro' style="display:none">
				   			<td colspan="11"  class="textos_formularios" align="left">
								No Existen datos
			         		</td>
			   			</tr>
			   		</nested:empty>			   		
		         	<tr>
				   		<td colspan="2">
			       			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
			       				<tr>
					           		<td width="41%" colspan="5" height="20" align="left" valign="middle"  class="textos_formularios">
			                  				&nbsp;Total de procesos en <nested:write property="numEmpresasTotal"/> Empresas
			                   		</td>
			                   		<td width="9%" height="20" align="left" valign="middle"  class="textos_formularios">
			                  				&nbsp;
			                   		</td>
			                   		<td width="30%" colspan="3" height="20" align="left" valign="middle"  class="textos_formularios">
			                  				&nbsp;
			                   		</td>
			                   		<td width="10%" height="20" align="left" valign="middle"  class="textos_formularios">
			                  				<div align="right">$&nbsp;<nested:write property="totPagadoTotalMonto"/></div>
			                   		</td>
			                   		<td width="10%" height="20" align="left" valign="middle"  class="textos_formularios">
			                  				<div align="right">&nbsp;<nested:write property="numTrabajadoresTotal"/></div>
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
<script language="javaScript">
<!--
img = new Image();
img.src = "/AdminCotPrevWEB/img/ico_menos.gif";
	function swapAll(id, imgId) 
	{
		obj = document.getElementById(id);
		img = document.getElementById(imgId);
		
	    if ( obj.style.display=='')
	    {
			obj.style.display='none';
			img.src = '<c:url value="/img/ico_mas.gif" />';
			img.alt = "Expandir";
			img.title = "Expandir";
		} else		
		{
			obj.style.display='';
			img.src = "/AdminCotPrevWEB/img/ico_menos.gif";
			img.alt = "Contraer";
			img.title = "Contraer";
		}
	}
// -->
</script>
</body>
</html:html>
