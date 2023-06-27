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
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
<script >
<!--
	if(top==self)
		top.location.replace("<c:url value='/' />");
// -->
</script>
</head>
<body onLoad="poneFoco('rutEmpresa')">
 
<html:form action="/NominaXestadoBuscar" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden property="rutEmpresaBuscar" styleId="rutEmpresaBuscar"  ></html:hidden>
<html:hidden property="grupoConvenioBuscar" styleId="grupoConvenioBuscar"  ></html:hidden>
<html:hidden property="tipoNominaIdBuscar" styleId="tipoNominaIdBuscar"  ></html:hidden>
<html:hidden property="estadoIdBuscar" styleId="estadoIdBuscar"  ></html:hidden>
<html:errors />
<table width="590" border="0" cellspacing="0" cellpadding="0">
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
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        	<tr valign="bottom"> 
            	<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Administraci&oacute;n de N&oacute;minas</strong></td>
            </tr>
       		</table>
          	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
          	<tr>
	            <td width="30%">
	            	<strong>RUT Empresa:</strong>
	            </td>
	            <td>
	            	<html:text property="rutEmpresa" styleId="rutEmpresa" maxlength="13" styleClass="campos" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);" onkeypress="javascript:checkCampoBusqueda(document.forms['NominaForm'], 'E');"/>
               	</td>          	 
	            <td width="30%">
	            	<strong>Cód. grupo convenio:</strong>
	            </td>
	            <td>
	            	<html:text property="grupoConvenio" styleId="grupoConvenio" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);" maxlength="8" onkeypress="javascript:checkCampoBusqueda(document.forms['NominaForm'], 'G');"/>
               	</td>
          	</tr>
          	<tr>
	            <td width="30%">
	            	<strong>Tipo proceso:</strong>
	            </td>
	            <td>
	            	<html:select property="tipoNominaId"  styleClass="campos" styleId="tipoNominaId" onchange="">
	            		<html:optionsCollection property="tiposNomina" />
	            	</html:select>
               	</td>               	          	 
	            <td width="30%"><strong>Estados n&oacute;mina:</strong></td>
	            <td>
	            	<html:select property="estadoId" styleId="estadoId" styleClass="campos" onchange="">
	            		<html:option value="0">Todos</html:option>
	            		<html:optionsCollection property="estados" />
	            	</html:select>
               	</td>
          	</tr>
          	
          	<tr>
	            <td colspan="4" align="right">
	            	<html:button property="operacion" styleClass="btn3" value="Buscar" onclick="javascript:listar();"/>
               	</td>
          	</tr>          	
            <tr> 
               	<td height="4" colspan="6" bgcolor="#85b4be"></td>
            </tr>
            </table>

          </td>
        </tr>
	</table>
    </td>
</tr>
<tr>
		<td>
			<table>
	        	<tr valign="bottom"> 
	            	<td width="100%" height="30" bgcolor="#FFFFFF" class="titulo"><strong>N&oacute;minas</strong></td>
	            </tr>
	        </table>
        </td>
</tr>
<tr align="center"> 
   		<td valign="top" bgcolor="#CCCCCC">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
   			<tr> 
     			<td align="center" bgcolor="#FFFFFF">
     			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
             		<tr class="subtitulos_tablas">
                		<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><%@ include file="/html/comun/flecha.jspf"%>  RUT Emp.</td>
		               	<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
		               	<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Convenio</td>
		               	<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Estado</td>
		               	<td width="20%" colspan="2" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acciones</td>
               		</tr>
               		<logic:notEmpty property="nominas" name="NominaForm">
						<logic:iterate id="nomina" property="nominas" name="NominaForm" indexId="nFila" type="cl.araucana.cp.distribuidor.hibernate.beans.NominaVO">
							<c:choose>
					   		    <c:when test="${nFila % 2 == 0}">
					   		    	<c:set var="estilo" value="textos_formularios"/>
					   		    </c:when>
		   						<c:otherwise>
		   							<c:set var="estilo" value="textos-formcolor2"/>
		   						</c:otherwise>
							</c:choose>
							
		               		<tr>
		                   		<td height="20" valign="middle" nowrap="nowrap" class="${estilo}"><div align="right">${nomina.formatoRut}</div>
		                   			<input type="hidden" name="rutEmpresaAux_<c:out value="${nFila}"/>" id="rutEmpresa_<c:out value="${nFila}"/>" value="${nomina.rutEmpresa}">
		                   		</td>
		                   		<td class="${estilo}">${nomina.empresa.razonSocial}</td>
		                   		<td align="right" class="${estilo}">${nomina.convenio.idConvenio}
		                   			<input type="hidden" name="grupoConvenioAux_<c:out value="${nFila}"/>" id="grupoConvenio_<c:out value="${nFila}"/>" value="${nomina.convenio.idGrupoConvenio}">
		                   			<input type="hidden" name="idConvenioAux_<c:out value="${nFila}"/>" id="idConvenio_<c:out value="${nFila}"/>" value="${nomina.convenio.idConvenio}">
		                   		</td>
		                   		<td nowrap="nowrap" class="${estilo}">${nomina.estado.descripcion}</td>
		                   		<td nowrap="nowrap" class="${estilo}">
		                   		     <table cellpadding="0" cellspacing="0" width="100%">
		                   		     <tr>
		                   		     <td width="25%" valign="top">&nbsp;
		                   		     <c:if test="${nomina.estado.id == 2 || nomina.estado.id == 3 || nomina.estado.id == 4 || nomina.estado.id == 5 || nomina.estado.id == 6}">	                   					
	                   					<a href="javascript:verTrab(<c:out value="${nFila}"/>);" title="Ver trabajadores" class="links"><img align="middle" title="Ver trabajadores" border="0" src="<c:url value="/img/iconos/lupa.gif" />" /></a>&nbsp;&nbsp;
	                   				</c:if>
		                   		     </td>
		                   		     <td width="25%" valign="top">&nbsp;
		                   		     <c:if test="${nomina.estado.id == 3 || nomina.estado.id == 4 || nomina.estado.id == 5}">
	                   					<a href="#" onclick="verComprobante('${nomina.idCodigoBarras}');" title="Ver comprobante" class="links">
									<img align="middle" title="Ver comprobante" border="0" src="<c:url value="/img/ico_pdf.gif" />" />
									</a></c:if>         
		                   		     </td>
		                   		     <td width="25%" valign="top">&nbsp;
		                   		     <c:if test="${nomina.estado.id == 2 || nomina.estado.id == 3 || nomina.estado.id == 4 || nomina.estado.id == 5}">
	                   					<a href="#" onclick="javascript:rescatar(<c:out value="${nFila}"/>);"  title="Rescate archivo" class="links">
	                   					<img align="middle" title="Rescate archivo" border="0" src="<c:url value="/img/iconos/ico_hojap.gif" />" />
	                   					</a>
	                   				</c:if>	
		                   		     </td>
		                   		     <td width="25%" valign="top">&nbsp;
		                   		     <c:if test="${nomina.estado.id == 2 || nomina.estado.id == 3 || nomina.estado.id == 4 || nomina.estado.id == 5}">
	                   					<a href="#" title="Consulta archivo" onclick="javascript:consultarExcel(<c:out value="${nFila}"/>);" class="links">
	                   					<img align="middle" title="Consulta archivo" border="0" src="<c:url value="/img/iconos/ico_excel.gif" />" />
	                   					</a>
	                   				</c:if>		  
		                   		     </td>
		                   		     </tr>
		                   		     </table>
		                   		  
	                   				

	                   				
	                   				
	                   				                 						                   				
		                   		</td>
		               		</tr>							
							
	             		</logic:iterate>
             		</logic:notEmpty>
             		<logic:empty property="nominas" name="NominaForm">
             			<tr>
             				<td class="textos_formularios" height="20" colspan="6">
             					No existen n&oacute;minas que cumplan con los criterios especificados
             				</td>
             			</tr>
             		</logic:empty>
           		</table>
       			</td>
   			</tr>
   			<!-- Aqui -->
        </table>
        
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr> 
			<bean:size id="nPags" name="NominaForm" property="numeroFilas"/>
				<c:if test="${nPags > 1}">
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr> 
								<td align="center" valign="middle" class="numeracion">
									<logic:iterate id="paginacion" name="NominaForm" property="numeroFilas">
										${paginacion}
									</logic:iterate>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</c:if>			
			</tr>
		</table>
 


		</td>
</tr>



</table>
 
</html:form>
<script language="javaScript">
<!--

	function verComprobante(barra){
	url='GenerarComprobPDF.do?accion=pdf&codigo='+barra;
	window.open(url,"nueva","toolbar=no,location=no,directories=<no,status=no,menubar=no,scrollbar=no,resizable=si,width=900,height=600");
	}
	function listar() 
	{
		accion='<%=request.getContextPath()%>/NominaXestadoBuscar.do';
		if (validate()) 
		{
			rut_a = document.getElementById("rutEmpresa");
		  rut_n = document.getElementById("rutEmpresaBuscar");
		  if(rut_a!=rut_n)
	 			rut_n.value=rut_a.value;

		  nom_a = document.getElementById("grupoConvenio");
		  nom_n = document.getElementById("grupoConvenioBuscar");
		  if(nom_a!=nom_n)
	 			nom_n.value=nom_a.value;

		  ap_a = document.getElementById("tipoNominaId");
		  ap_n = document.getElementById("tipoNominaIdBuscar");
		  if(ap_a!=ap_n)
	 			ap_n.value=ap_a.value;

	 	  ng_a = document.getElementById("estadoId");
		  ng_n = document.getElementById("estadoIdBuscar");
		  if(ng_a!=ng_n)
	 			ng_n.value=ng_a.value;

			frm.action = accion+'?accion=listar';
			frm.submit();
		}
	}
	
	function checkCampoBusqueda(frm, campoBusqueda)
	{
		if (campoBusqueda == 'E') //Empresa
			frm.grupoConvenio.value = '';
		else 
			frm.rutEmpresa.value = '';
	}
	
	function validate() 
	{
		frm = document.getElementById('formulario');
		var posGuion = document.getElementById("rutEmpresa").value.indexOf('-');
		var rutTMP = limpiaRutIncremental(document.getElementById("rutEmpresa").value);
		if (document.getElementById("rutEmpresa").value.length > 0 && rutTMP.length < 4)
		{
			alert("* Debe ingresar al menos 4 dígitos de rut para el usuario a buscar");
			document.getElementById("rutEmpresa").focus();
			return (false);
		//} else if(document.getElementById("rutEmpresa").value.length > 0 && posGuion > 0 && !validaDV(rutTMP))
		} else if(document.getElementById("rutEmpresa").value.length > 0 && posGuion > 0 && !validaDV(document.getElementById("rutEmpresa").value))
		{
			alert(' * Dígito verificador de Rut de empresa a buscar incorrecto');
			return false;
		}
		
		if (frm.tipoNominaId.options[frm.tipoNominaId.selectedIndex].value == '') 
		{
			alert('Debe seleccionar el Tipo de nómina');
			frm.tipoNominaId.focus();
			return (false);
		}	
		return (true);
	}

	function verTrab(index) 
	{
		frm = document.getElementById('formulario');
		var idConv = document.getElementById('idConvenio_' + index).value;
		var rutEmp = document.getElementById('rutEmpresa_' + index).value;
		var tipoNom = frm.tipoNominaId.options[frm.tipoNominaId.selectedIndex].value;
		var LabeltipoNom = frm.tipoNominaId.options[frm.tipoNominaId.selectedIndex].text;
		
		//Enviamos los filtros
		rutEmpBus = document.getElementById("rutEmpresaBuscar").value;
		convenio = document.getElementById("grupoConvenioBuscar").value;
		tipo = document.getElementById("tipoNominaIdBuscar").value;
		estado = document.getElementById("estadoIdBuscar").value;
		
		frm.action = 'NominasTrabajadoresVer.do?idConv=' + idConv + '&rutEmp=' + rutEmp + '&tipoNom=' + tipoNom+'&tipo='+LabeltipoNom+'&rutEmpresaBuscar='+rutEmpBus+'&grupoConvenioBuscar='+convenio+'&tipoNominaIdBuscar='+tipo+'estadoIdBuscar='+estado;
		frm.submit();
	}

	function rescatar(index) 
	{
		frm = document.getElementById('formulario');
		 idConv = document.getElementById('idConvenio_' + index).value;
		 rutEmp = document.getElementById('rutEmpresa_' + index).value;
		 tipoNom = frm.tipoNominaId.options[frm.tipoNominaId.selectedIndex].value;
		 url='RescatarArchivo.do?idConv=' + idConv + '&rutEmp=' + rutEmp + '&tipoNom=' + tipoNom;
		window.open(url,"nueva","toolbar=no,location=no,directories=<no,status=no,menubar=no,scrollbar=no,resizable=si,width=900,height=600");
		return false;
	}
	
	function consultarExcel(index) 
	{
		frm = document.getElementById('formulario');
		 idConv = document.getElementById('idConvenio_' + index).value;
		 rutEmp = document.getElementById('rutEmpresa_' + index).value;
		 tipoNom = frm.tipoNominaId.options[frm.tipoNominaId.selectedIndex].value;
		//frm.action = 'ConsultaExcel.do?idConv=' + idConv + '&rutEmp=' + rutEmp + '&tipoNom=' + tipoNom;
		url='ConsultaExcel.do?idConv=' + idConv + '&rutEmp=' + rutEmp + '&tipoNom=' + tipoNom;
		window.open(url,"nueva","toolbar=no,location=no,directories=<no,status=no,menubar=no,scrollbar=no,resizable=si,width=900,height=600");
	}

	function retornaEnlace(paginacion)
	{
		rut_a = document.getElementById("rutEmpresa");
		  rut_n = document.getElementById("rutEmpresaBuscar");
		  if(rut_a!=rut_n)
	 			rut_a.value=rut_n.value;

		  nom_a = document.getElementById("grupoConvenio");
		  nom_n = document.getElementById("grupoConvenioBuscar");
		  if(nom_a!=nom_n)
	 			nom_a.value=nom_n.value;

		  ap_a = document.getElementById("tipoNominaId");
		  ap_n = document.getElementById("tipoNominaIdBuscar");
		  if(ap_a!=ap_n)
	 			ap_a.value=ap_n.value;

	 	  ng_a = document.getElementById("estadoId");
		  ng_n = document.getElementById("estadoIdBuscar");
		  if(ng_a!=ng_n)
	 			ng_a.value=ng_n.value;

		formu = document.getElementById("formulario");
		formu.action = 'NominaXestadoBuscar.do?accion=listar&paginaNumero=' + paginacion;
		formu.submit();
	}
	
// -->
</script>
</body>
</html:html>
