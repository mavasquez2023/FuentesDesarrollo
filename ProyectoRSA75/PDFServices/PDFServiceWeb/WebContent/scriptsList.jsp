<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html:html>

<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />

<link href="css/Interna_Araucana.css" rel="stylesheet" type="text/css" />
<title>La Araucana C.C.A.F. - Lista de Scripts</title>

<style type="text/css">

	
	div.tableContainer {
		width: 767px;		/* table width will be 99% of this*/
		height: 300px; 	/* must be greater than tbody*/
		overflow: auto;
		margin: 0 auto;
		}
	
	tableFixHead {
		width: 99%;		/*100% of container produces horiz. scroll in Mozilla*/
		border: none;
	
		}
	
	tableFixHead>tbodyFixHead	{  /* child selector syntax which IE6 and older do not support*/
		overflow: auto;
		height: 290px;
		overflow-x: hidden;
		border-color: green;
		border-style: solid;
		}
	
	thead tr	{
		position:relative;
		/*top: expression(offsetParent.scrollTop); /*IE5+ only*/*/
		}
	
	thead td, thead th {
		text-align: center;
		font-size: 14px;
		background-color: #FFFFFF;
		color: steelblue;
		font-weight: bold;
		/*border-top: solid 1px #d8d8d8;*/
		}
	
	td	{
		color: #000;
		padding-right: 2px;
		font-size: 12px;
		/*text-align: left;*/
		/*border-bottom: solid 1px #d8d8d8;*/
		/*border-left: solid 1px #d8d8d8;*/
		}
	
	tableFixHead tfoot tr { /*idea of Renato Cherullo to help IE*/
	      position: relative;
	      overflow-x: hidden;
	      top: expression(parentNode.parentNode.offsetHeight >=
		  offsetParent.offsetHeight ? 0 - parentNode.parentNode.offsetHeight + offsetParent.offsetHeight + offsetParent.scrollTop : 0);
	      }
	
	
	tfoot td	{
		text-align: center;
		font-size: 11px;
		font-weight: bold;
		background-color: #FFFFFF;
		color: steelblue;
		/*border-top: solid 1px slategray;*/
		}
	
	td:last-child {padding-right: 20px;} /*prevent Mozilla scrollbar from hiding cell content*/
	
	</style>
	
	
	
	<!-- print style sheet -->
	<style type="text/css" media="print">
	div.tableContainer {
	  overflow: visible;	
	}
	tableFixHead>tbodyFixHead	{
	  overflow: visible; 
	}
	td {
	   height: 14pt;
	} /*adds control for test purposes*/
	
	thead td	{font-size: 11pt;	}
	tfoot td	{
		text-align: center;
		font-size: 9pt;
		border-bottom: solid 1px slategray;
		}
	
	thead	{display: table-header-group;	}
	tfoot	{display: table-footer-group;	}
	thead th, thead td	{position: static; }
	
	thead tr	{position: static; } /*prevent problem if print after scrolling table*/
	tableFixHead tfoot tr {     position: static;    }
	
	
</style>


</head>
<script>
	function algo(){
		var d = expression(offsetParent.scrollTop);
		alert("d:"+d);
	}
	
	
	function confirmation() {
		var answer = confirm("¿Está seguro que desea eliminar el script ${idscript}?")
		return answer;
	}
	
	function doAction(action){
	
		if('Editar' == action){
			document.forms[0].idscript.value = getItemSelect();
			document.forms[0].act.value = "edt";
			document.mantenedorDeScriptForm.action = "AdminScripts.do";
			document.mantenedorDeScriptForm.submit();
		} 
		if('Ver' == action ){
			document.forms[0].idscript.value = getItemSelect();
			document.forms[0].act.value = "ver";
			document.mantenedorDeScriptForm.action = "AdminScripts.do";
			document.mantenedorDeScriptForm.submit();
		} 
		if('Ejecutar' == action){
			document.forms[0].idscript.value = getItemSelect();
			document.mantenedorDeScriptForm.action = "InputParameters.do";
			document.mantenedorDeScriptForm.submit();
		}
		if('Eliminar' == action){
			if(confirmation()){
				document.forms[0].idscript.value = getItemSelect();
				document.mantenedorDeScriptForm.action = "DeleteScript.do";
				document.mantenedorDeScriptForm.submit();
			}
		}
		if('Crear' == action){
			document.forms[0].idscript.value = "";
			document.forms[0].act.value = "new";
			document.mantenedorDeScriptForm.action = "AdminScripts.do";
			document.mantenedorDeScriptForm.submit();
		}
		
		
	}

	function getItemSelect() {
	  var res;
	  var d = document;
	  for (i=0;i<d.forms.length;i++) {
	     for (ii=0;ii<d.forms[i].length;ii++) {
	        var element = d.forms[i].elements[ii];
	        var type = element.type;
			if(element.checked) {
		         res = element.value;
		         break;
		    }
		 }
		      
	  }
	  return res;
	}

	function ver() {
	   if (v = getItemSelect()) {
	       document.forms[0].act.value = "ver";
	       document.forms[0].idscript.value = v;
	   } else {
	       return false;
	   }
	}
	
	


</script>

<body onload="setAnchor('null');">
	<%@ include file="header.jspf" %>
	<CENTER>
	<logic:present name="username" scope="session">

	<c:set var="idscript" value="${sessionScope.idscript}"/>
		<table width="767" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%">
						<%@ include file="contextUser.jspf" %>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="contextUser" width="5%">
								Contexto:
							</td>
							<td class="contextNav" align="left">
							<a href="<%= cPath %>/LoadConfUser.do" >Sistemas</a> 
							<%@ include file="p.jspf" %>
								<c:choose>
									<c:when test="${idscript != null}">
										<a href="<%= cPath %>/LoadScripts.do?sysID=${sistema.identificador}#${idscript}" >Scripts</a>
									</c:when>
									<c:otherwise>
										<a href="<%= cPath %>/LoadScripts.do?sysID=${sistema.identificador}" >Scripts</a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<%@ include file="barTop.jspf" %>
				</td>
				
			</tr>
		</table>  
	 
		<form name="mantenedorDeScriptForm" method="post" action="AdminScripts.do" >
			<input type="hidden" name="idusuario" value="${usuario.identificador}">
			<input type="hidden" name="idsistema" value="${sistema.identificador}">
			<input type="hidden" name="act" value="">
			<input type="hidden" name="idscript" value="">
			
	
				<table width="767" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td width="100%" align="center">
							<%@ include file="contextSystem.jspf" %>
						</td>
					</tr>
				</table>
				<br/>
				
				<table width="767" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td>
							<table align="center" border="0" cellpadding="0" cellspacing="0">
								<tr>
								<%-- PARA VERIFICAR SI EL USUARIO ES ADMINISTRADOR --%> 
									<logic:present name="admin" scope="session">
										<td width="88"><input name="crear" type="button" class="btn2"
											value="Crear" onclick="doAction('Crear')" /></td>
										<td>&nbsp;</td>
										<td width="88"><input name="editar" type="button" class="btn2"
											value="Editar" onclick="doAction('Editar')" /></td>
										<td>&nbsp;</td>
										<td width="88"><input name="eliminar" type="button" class="btn2"
											value="Eliminar" onclick="doAction('Eliminar')" /></td>
									</logic:present>

									<td width="88"><input name="ver" type="button" class="btn2"
										value="Ver" onclick="doAction('Ver')" /></td>
									<td>&nbsp;</td>
									<td width="88"><input name="ejecutar" type="button" class="btn2"
										value="Ejecutar" onclick="doAction('Ejecutar')" /></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<div id="containerHead" style="width: 767px; height: 10px; padding-right: 5pt; padding-top: 10pt;" align="left">
					<table width="752" border="0" cellpadding="0" cellspacing="0"
											bordercolor="#B4D6D8" class="tableFixHead" align="left">
					<thead>
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" bordercolor="#B4D6D8" >
									<tr>
										<td colspan="4" class="headertablas" style="border-bottom: solid; border-bottom-color: white; border-bottom-width: 1pt"> 
											LISTA DE SCRIPTS
										</td>
									</tr>
									<tr>
										<td width="25" class="headertablas">&nbsp;</td> 
										<td width="125" class="headertablas">Nombre</td>
										<td width="125" class="headertablas">Fecha Creaci&oacute;n</td>
										<td width="470" class="headertablas">Descripci&oacute;n</td>	
									</tr>
								</table>
							</td>
						</tr>
					</thead>
					</table>
				</div>
				<div id="container" style="padding-top: -25pt; background-color: #D1E6E7; height: 300px; width: 767px;">
					<div class="tableContainer" >
					
					<!--div style=" overflow: scroll  ; height: 300px; width: 767px " -->
						<table>
							<tr>
								<td>

								</td>
							</tr>
								
							
							<tr>
								<td>	
									<table border="1" cellpadding="0" cellspacing="0"
										bordercolor="#B4D6D8" class="tableFixHead" >
										    <tbody class="tbodyFixHead">
												<logic:present name="listaScriptsAsociados" scope="session">
													<c:set var="style" value="class='textos-formcolor2'"/>
													<c:set var="count" value="${1}" /> 
													<c:set var="checked" value="" />
													
													<c:forEach var="scriptsAsociado" items="${listaScriptsAsociados}" >
													
														<c:choose>
															<c:when test="${idscript == scriptsAsociado.nombreArchivoScript}">
																<c:set var="checked" value="checked='checked'"/>
															</c:when>
															<c:otherwise>
																<c:if test="${count==1 && idscript == scriptsAsociado.nombreArchivoScript}">
																	<c:set var="checked" value="checked='checked'"/>
																</c:if>
																<c:if test="${count==1 && idscript == null}">
																	<c:set var="checked" value="checked='checked'"/>
																</c:if>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${checked != '' && checked != null}">
																<c:set var="style" value="class='selected'"/>
															</c:when>
															<c:otherwise>
																<c:set var="style" value="class='textos-formcolor2'"/>
															</c:otherwise>
														</c:choose>
																
										              	<tr >
											                <td width="23" ${style} >
																<input type="radio" ${checked}
																		name="script"
																		value="${scriptsAsociado.nombreArchivoScript}" 
																		id="${scriptsAsociado.nombreArchivoScript}"
																		onclick="javascript:setAnchor(this.value);" 
																	/>	

															</td>
										                	<td width="120"  ${style} >${scriptsAsociado.nombreArchivoScript}</td>
										                	<td width="120"  ${style} >${scriptsAsociado.fechaCreacion}</td>
										                	<td width="482"  ${style} >${scriptsAsociado.objetivo}</td>
										             	</tr>
										             <c:set var="count" value="${2}"/>
										             <c:set var="checked" value=""/>
										             <c:set var="style" value=""/>
										             
										     		</c:forEach> 
												</logic:present>
												<logic:notPresent name="listaScriptsAsociados" scope="session">
													<tr>
														<td width="100%" class="textos-formcolor2" colspan="4">
															No se encontraron scripts para el sistema: ${sistema.identificador}
														</td>
													</tr>
												</logic:notPresent>
											</tbody>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<br/>
		</form>
	</logic:present>
	<logic:notPresent name="username" scope="session">
		<% response.sendRedirect(request.getContextPath()+"/index.jsp"); %> 
	</logic:notPresent>
</CENTER>
<SCRIPT>

	function setAnchor(idScript) { 
		//alert(idScript);
		if(idScript == null || idScript == '' || idScript == 'null'){
			document.location.href='<%=cPath%>/LoadScripts.do?sysID=${sistema.identificador}#${idscript}';
			//alert(${varId});
		} else {

			document.location.href='<%=cPath%>/LoadScripts.do?sysID=${sistema.identificador}&idscript=' + idScript;
		}
		
	}
	
	function setStyle(obj){
	alert(obj);
	obj.className='selected';
	
	
	}

</SCRIPT>

</body>



</html:html>