<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<title>Cotizaci&oacute;n Previsional Electr&oacute;nica :: cp.cl</title>
	<link href="<c:url value="/css/Interna_Araucana.css" />" rel="stylesheet" type="text/css">
</head>
<body>
<table width="590" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="590" height="95" valign="top">
			<img src="<c:url value="/img/logo_imp.jpg" />" border="0" width="590" height="95" />
		</td>
	</tr>
	<tr>
		<td width="590" height="7" valign="top">
			<img src="<c:url value="/img/sombra_imp.jpg" />" border="0" width="590" height="7" />
		</td>
	</tr>
</table>
<html:form action="/DetalleComprobante">
<table width="590" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
	<tr >
		<td width="100%">
			<center><span class="Totales_Numeros"><strong>Informe No V&aacute;lido para Pago</strong></span></center>
		</td>
	</tr>
	<tr >	
		<td width="100%">
			<center><span class="Totales_Numeros">Detalle por Instituci&oacute;n</span></center>
		</td>
	</tr>
	<tr>
		<td width="50%">&nbsp;</td>
		<td width="50%" align = "left">Periodo&nbsp;${DetalleActionForm.periodo}</td>
	</tr>
	<tr> 
    	<td height="4" colspan="4" bgcolor="#85b4be"></td>
	</tr>
</table>
<br/>
<table width="590" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">			
	<tr> 
		<td width="50%"><strong>RUT Empresa:&nbsp;</strong>	${DetalleActionForm.rutEmpresaFormat}</td>
        <td width="50%"><strong>Raz&oacute;n Social:&nbsp;</strong>${DetalleActionForm.razonSocial}</td>
    </tr>
    <tr> 
        <td><strong>Convenio:&nbsp;</strong>${DetalleActionForm.convenio}</td>
        <td><strong>Tipo de Nómina: ${DetalleActionForm.tipoProceso}</strong></td>
    </tr>	
    <tr> 
    	<td height="4" colspan="4" bgcolor="#85b4be"></td>
    </tr>  	
</table>
<br/>
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr> 
		<td>
		<logic:present name="msg">
		    <html:messages id="msg" message="true">
				<div class="msgExito">${msg}</div>
			</html:messages>
		</logic:present>		
      	<logic:notPresent name="msg">
	         <table width="100%" border="0" cellpadding="0" cellspacing="1" bordercolor="#FFFFFF" bgcolor="#FFFFFF">
	         <logic:iterate id="seccion" name="DetalleActionForm" property="secciones" indexId="count"><c:choose>
   		    	<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
	   				<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
	   		 	</c:choose>
	         	<tr valign="top"> 
					<td width="18%" align="center" class="${estilo}"><strong>${seccion.nombre}</strong></td>
	           		<td width="82%" align="left" bordercolor="#CCCCCC" class="${estilo}"> 
	            	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="txt2">
					<logic:iterate id="detalle" name="seccion" property="detalles">
	            		<tr> 
							<td width="79%" align="left" valign="middle" class="textos_formularios">
								<a href="#${seccion.idTipoSeccion}-${detalle.idDetalleSeccion}">
									<img id="img-${seccion.idTipoSeccion}-${detalle.idDetalleSeccion}" src="<c:url value="/img/ico_menos.gif" />" width="11" height="11" border="0" />
								</a>
								${detalle.nombre}
							</td>
							<td width="5%" align="right" valign="middle" class="textos_formularios"><div align="right">$</div></td>
	                   		<td width="13%" align="right" valign="middle" class="textos_formularios"><div align="right">${detalle.total}</div></td>
	                   		<td width="3%" class="textos_formularios">&nbsp;</td>
						</tr>
						<c:if test="${seccion.nombre == 'C.C.A.F.'}">
							<c:if test="${seccion.sfe !=''}">
								<tr>
									<td colspan="3" align="right" valign="middle" class="textos_formularios"><div align="right">(Saldo Favor Empresa) $${seccion.sfe}</div></td>
									<td width="3%" class="textos_formularios">&nbsp;</td>
								</tr>
							</c:if>
							<c:if test="${seccion.sfi !=''}">
								<tr>
									<td colspan="3" align="right" valign="middle" class="textos_formularios"><div align="right">(Saldo Favor Institución) $${seccion.sfi}</div></td>
									<td width="3%" class="textos_formularios">&nbsp;</td>
								</tr>
							</c:if>
						</c:if>
						<c:if test="${seccion.nombre == 'INP'}">
							<c:if test="${seccion.sfe !=''}">
								<tr>
									<td colspan="3" align="right" valign="middle" class="textos_formularios"><div align="right">(Saldo Favor Empresa) $${seccion.sfe}</div></td>
									<td width="3%" class="textos_formularios">&nbsp;</td>
								</tr>
							</c:if>
							<c:if test="${seccion.sfi !=''}">
								<tr>
									<td colspan="3" align="right" valign="middle" class="textos_formularios"><div align="right">(Saldo Favor Institución) $${seccion.sfi}</div></td>
									<td width="3%" class="textos_formularios">&nbsp;</td>
								</tr>
							</c:if>
						</c:if>
						</tr>
	               		<tr id="${seccion.idTipoSeccion}-${detalle.idDetalleSeccion}"> 
	              			<td colspan="4" align="center" class="textos_formcolor">
	                  		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
	                  			<tr> 
	                      			<td height="19" colspan="3" align="left" valign="middle" class="barra_tablas">Detalle ${detalle.nombre}</td>
	                      		</tr>
	                            <logic:iterate id="detalleDetalle" name="detalle" property="listaDetalles">
		                            <tr> 
		                            	<td width="60%" height="19" align="left" valign="middle" class="textos-formularios2">${detalleDetalle.label}</td>
		                                <td align="right" width="40%" valign="middle" class="textos-formularios2">$ ${detalleDetalle.value}</td>
		                            </tr>
		                        </logic:iterate>
							</table>
							</td>
						</tr>
					</logic:iterate>
		                <tr class="textos_formcolor"> 
		                  	<td align="left" valign="middle" class="Totales_Numeros"><strong>TOTAL ${seccion.nombre}</strong></td>
		                  	<td colspan="3" align="right" valign="middle" class="Totales_Numeros">$${seccion.total}</td>
		                </tr>
		            </table>
		            </td>
				</tr>
			</logic:iterate>
	            <tr> 
					<td>&nbsp;</td>
				</tr>
			</table>
		</logic:notPresent>
  		</td>
	</tr>
</table>
<table width="590" border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
	<tr> 
		<td width = "30%"><span class="Totales_Numeros">Monto Total</span></td>
        <td align="center" class=""><div align="right"><p class="Totales_Numeros">$${DetalleActionForm.totalCmp}</p></div></td>
    </tr>
</table> 
</html:form>
</body>
<script language="javaScript">
	window.print();
	//window.setTimeout("window.close()", 0);
</script>
</html>
