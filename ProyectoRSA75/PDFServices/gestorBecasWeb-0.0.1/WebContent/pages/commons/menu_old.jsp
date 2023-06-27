<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/araucana-gestorBecas" prefix="gesBecas" %>

<script type="text/javascript">
	$(document).ready(function() {
		var o = document.getElementById( '<%= (String)session.getAttribute( "_opcid" ) %>' );
		if ( o ) {
			o.className="menu_active";
		}	

	});
	function muestraMenu(id)
	{
		obj = document.getElementById(id);
		if ( obj.style.display == '' )
	    {
			obj.style.display='none';
		} else
		{
			obj.style.display = '';
		}
	}
	
</script>

	<table class="menu" border="0" cellpadding="0" cellspacing="0">
		<tr> 
			<th colspan="2"><bean:message key="title.gestion.becas"/></th>
		</tr>
		<tr>
	       <td width="90%">
			<table class="menu" border="0" cellpadding="0" cellspacing="0">
				
				<gesBecas:tieneRol roles="administradorRole">				
				
				<tr id="title.gestorBecas.administracion" class="menu_noactive">
					<td align="left">
								<a onclick="muestraMenu('menuAdmin');" style="cursor:pointer;"><bean:message key="title.gestorBecas.administracion"/></a>
								<div id="menuAdmin" style="display: none;">
									<p>&nbsp;&nbsp;&nbsp;<html:link page="/adminBecasIncentivos/ListaBecasIncentivos.do?_opcid=gestorBecas.administracion.becasIncentivos"><bean:message key="title.gestorBecas.administracion.becasIncentivos"/></html:link></p>
                                    <p>&nbsp;&nbsp;&nbsp;<html:link page="/adminBecasIncentivos/inicioBuscarPersonaEliminaBeca.do?_cmd=inicio&_opcid=gestorBecas.administracion.eliminaBeca"><bean:message key="title.gestorBecas.administracion.eliminaBeca"/></html:link></p>
									<p>&nbsp;&nbsp;&nbsp;<html:link page="/adminBecasIncentivos/listaTipoPremioDocReque.do?_cmd=tipoPremio&_opcid=gestorBecas.administracion.tipoPremio"><bean:message key="title.gestorBecas.administracion.tipoPremio"/></html:link></p>
									<p>&nbsp;&nbsp;&nbsp;<html:link page="/adminBecasIncentivos/listaTipoPremioDocReque.do?_cmd=docRequerido&_opcid=gestorBecas.administracion.docRequerido"><bean:message key="title.gestorBecas.administracion.docRequerido"/></html:link></p>
									<p>&nbsp;&nbsp;&nbsp;<html:link page="/cargaResultado/inicioCargaResultado.do?_cmd=inicio&_opcid=gestorBecas.administracion.cargaResultado"><bean:message key="title.gestorBecas.administracion.cargaResultado"/></html:link></p>
								</div>
					</td>
				
				</tr>

				</gesBecas:tieneRol>
				
				<gesBecas:tieneRol roles="administradorRole;ejecutivoRole">
				       <tr id="title.gestorBecas.solicitud" class="menu_noactive"><td align="left"><html:link page="/solicitudBecasIncentivos/inicioBusquedaPersona.do?_opcid=gestorBecas.busqueda.persona"><bean:message key="title.gestorBecas.solicitud"/></html:link></td></tr>
				       <tr id="title.gestorBecas.entrega" class="menu_noactive"><td align="left"><html:link   page="/entregaIncentivos/inicioBusquedaIncentivosByPersona.do?_opcid=gestorBecas.entrega"><bean:message key="title.gestorBecas.entrega"/></html:link></td></tr>
				</gesBecas:tieneRol>

				<gesBecas:tieneRol roles="administradorRole;ejecutivoRole">
				       <tr id="title.gestorBecas.consulta.becas.incentivos" class="menu_noactive"><td align="left"><html:link page="/consultaBecasIncentivos/inicioConsultaBecasIncentivosByPersona.do?_opcid=gestorBecas.consulta.becas.incentivos"><bean:message key="title.gestorBecas.consulta.becas.incentivos"/></html:link></td></tr>
				</gesBecas:tieneRol>
			</table>           
	       </td>
	       <td rowspan="20" style="background-color: #FFFFFF;">&nbsp;</td>
	    </tr>
	</table>
	<br/>

	<gesBecas:tieneRol roles="administradorRole;ejecutivoRole">
	<table class="menu" border="0" cellpadding="0" cellspacing="0">
		<tr> 
			<th colspan="2"><bean:message key="title.menu.informes"/></th>
		</tr>
		<tr>
	       <td width="90%">
			<table class="menu" border="0" cellpadding="0" cellspacing="0">
				<tr id="title.gestorBecas.informe.becasByFecha" class="menu_noactive"><td align="left"><html:link page="/informes/becasByFecha.do?_opcid=gestorBecas.informe.becasByFecha&_cmd=inicio"><bean:message key="title.gestorBecas.informe.becasByFecha"/></html:link></td></tr>
			</table>           
	       </td>
	       <td rowspan="20" style="background-color: #FFFFFF;">&nbsp;</td>
	    </tr>
	</table>
	</gesBecas:tieneRol>
	<br/>



