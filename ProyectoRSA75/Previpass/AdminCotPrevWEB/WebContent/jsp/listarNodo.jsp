<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/html/comun/taglibs.jsp" %>
<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
<script src="<c:url value='/js/comun.js'/>"></script>
<html:html>

<head>
<title>listaNodos</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/listarNodo.do" styleId="formulario">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="nodo" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="nodoLista" />
<html:hidden property="operacion" styleId="operacion" value="" />
<html:hidden property="idUsuarioBorrar" styleId="idUsuarioBorrar" value="" />	
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<html:errors/>
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
		<td align="left" valign="top">
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
            	<tr valign="bottom"> 
              		<td width="38%" height="30" bgcolor="#FFFFFF" class="titulo">
              			<strong>Lista de Nodos</strong>
              		</td>
              		<td width="31%" align="right" bgcolor="#FFFFFF">
						<html:button property="operacion" styleClass="btn4" value="Crear Nodo" onclick="javascript:creaNodo();" />
	                	<html:button property="operacion" styleClass="btn3" value="Imprimir" onclick="javascript:abrirDocImpresion1();"/>
			        </td>
				</tr>
	         </table>
        </td>
	</tr>
	<tr>
	<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
	   			<tr> 
	     			<td align="center" bgcolor="#FFFFFF">
	     			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
	     			<tr>
	     				<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">
	     				<%@ include file="/html/comun/flecha.jspf"%>   Nodo</td>
	     				<td width="25%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Descripci&oacute;n</td>
	     				<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Distribuidor</td>
	     				<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Habilitado</td>
	     				<td width="13%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas" colspan="2">Acci&oacute;n</td>
	     			</tr>
	               		<nested:notEmpty property="consulta" name="NodoForm" >
							<nested:iterate id="filaConsulta" name="NodoForm"  property="consulta" indexId="nFila">
							<c:choose>
					   		    <c:when test="${nFila % 2 == 0}">
					   		    	<c:set var="estilo" value="textos_formularios"/>
					   		    </c:when>
		   						<c:otherwise>
		   							<c:set var="estilo" value="textos-formcolor2"/>
		   						</c:otherwise>
							</c:choose>
								<tr>
			                   		<td width="15%" align="right" nowrap="nowrap" class="${estilo}" >
			                   			<div align="right">${filaConsulta.idNodo}</div>
			                   		</td>	
			                   		
			                   		<td width="25%" align="left" nowrap="nowrap" class="${estilo}" >
			                   			${filaConsulta.descripcion}
			                   		</td>				               		
			                   		
			                   		<td width="15%" align="left" nowrap="nowrap" class="${estilo}" >
			                   			<c:if test="${filaConsulta.distribuidor == 1}">
			                   				Habilitado
			                   			</c:if>
				                   		<c:if test="${filaConsulta.distribuidor == 0}">
			                   				No Habilitado
			                   			</c:if>
			                   		</td>	
			               		
			                   		<td width="15%" align="left" nowrap="nowrap" class="${estilo}" >
			                   			<c:if test="${filaConsulta.habilitado == 1}">
			                   				Habilitado
			                   			</c:if>
				                   		<c:if test="${filaConsulta.habilitado == 0}">
			                   				No Habilitado
			                   			</c:if>
			                   		</td>
			                   			
		                   			<td width="8%" align="left" nowrap="nowrap" class="${estilo}">
		                   				<div align="center">
		                   					<html:link action="/listarNodo.do?accion=admin&subAccion=nodos&subSubAccion=nodoEditar&idNodo=${filaConsulta.idNodo}&operacion=Modificar">
		                   						<img src="<c:url value='/img/iconos/ico_hojap.gif' />" width="14" height="13" border="0" alt="Editar" title="Editar"/>
		                   					</html:link>
		                   				</div>
		                   			</td>
		                   			
		                   			<td width="5%" align="left" nowrap="nowrap" class="${estilo}">
		                   				<div align="center">
		                   					<a href="javascript:borraNodo('${filaConsulta.idNodo}','${filaConsulta.distribuidor}');">
		                   						<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" height="16" border="0" alt="Borrar" title="Borrar" />
		                   					</a>
		                   				</div>
									</td>
			               		</tr>			               		
							</nested:iterate>
	             		</nested:notEmpty>
	             		<nested:empty property="consulta" name="NodoForm" >
	             			<tr>
	             				<td class="textos_formularios" height="20" colspan="5">
	             					No existen Nodos
	             				</td>
	             			</tr>
	             		</nested:empty>
	           		</table>
	       			</td>
	   			</tr>
	   			<!-- Aqui -->
	        </table>
        </td>
	</tr>
	<nested:notEmpty property="consulta" name="NodoForm" >
	<tr>
		<td>
		<!-- Insertando Paginacion -->
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr> 
			<bean:size id="nPags" name="NodoForm" property="numeroFilas"/>
				<c:if test="${nPags > 1}">
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr> 
								<td align="center" valign="middle" class="numeracion">
									<logic:iterate id="paginacion" name="NodoForm" property="numeroFilas">
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
	</nested:notEmpty>
</table>
</html:form>
<script language="javaScript">
<!--
	function lTrim(sStr)
	{
		while (sStr.charAt(0) == " ")
	      sStr = sStr.substr(1, sStr.length - 1);
	     return sStr;
	 }
		
	function rTrim(sStr)
	{
		while (sStr.charAt(sStr.length - 1) == " ")
	      sStr = sStr.substr(0, sStr.length - 1);
	     return sStr;
	}

	function allTrim(sStr)
	{
	     return rTrim(lTrim(sStr));
	}

	function creaNumero(valor)
	{
		var valor1 = allTrim(valor);
		var tmp = valor1.split('.');
		var numTmp = "";
		for (i = 0; i < tmp.length; i++)
			numTmp += tmp[i];
		return new Number(numTmp);
	}

	function borraNodo(idUsuario, distribuidor) 
	{
		if(creaNumero(distribuidor) == 0)
		{
			if (!confirm("Está seguro que desea borrar el Nodo: " + idUsuario))
				return;
		}else
		{
			alert("No está permitido Eliminar nodo distribuidor");
			return ;
		}
		document.getElementById("operacion").value = "borrar";
		document.getElementById("idUsuarioBorrar").value = idUsuario;
		document.getElementById("formulario").submit();
	}
	
	function creaNodo() 
	{
		document.getElementById("operacion").value = "Crear_Nodo";
		document.getElementById("formulario").submit();
	}

	function retornaEnlace(paginacion)
	{
		formu = document.getElementById("formulario");

		document.getElementById("operacion").value = "";
		formu.action = "listarNodo.do?&paginaNumero=" + paginacion;
		formu.submit();
	}
	
function abrirDocImpresion1()
{
  	
   var URL="/AdminCotPrevWEB/listarNodo.do?imprimir=";
   abrirDocImpresion(URL);
}
// -->
</script>
</body>
</html:html>
