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
			<table class="menu" width="230" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="left">	
							<ul>	
							<li><a class="title" href="#adminbecas">Gestión de Becas</a></li>	
							<gesBecas:tieneRol roles="administradorRole">		
								<li><html:link page="/adminBecasIncentivos/ListaBecasIncentivos.do?_opcid=gestorBecas.administracion.becasIncentivos"><bean:message key="title.gestorBecas.administracion.becasIncentivos"/></html:link></li>
                            	<li><html:link page="/adminBecasIncentivos/inicioBuscarPersonaEliminaBeca.do?_cmd=inicio&_opcid=gestorBecas.administracion.eliminaBeca"><bean:message key="title.gestorBecas.administracion.eliminaBeca"/></html:link></li>
								<li><html:link page="/adminBecasIncentivos/listaTipoPremioDocReque.do?_cmd=tipoPremio&_opcid=gestorBecas.administracion.tipoPremio"><bean:message key="title.gestorBecas.administracion.tipoPremio"/></html:link></li>
								<li><html:link page="/adminBecasIncentivos/listaTipoPremioDocReque.do?_cmd=docRequerido&_opcid=gestorBecas.administracion.docRequerido"><bean:message key="title.gestorBecas.administracion.docRequerido"/></html:link></li>
							</gesBecas:tieneRol>
							<gesBecas:tieneRol roles="administradorRole;ejecutivoRole">
				       			<li><html:link page="/solicitudBecasIncentivos/inicioBusquedaPersona.do?_opcid=gestorBecas.busqueda.persona"><bean:message key="title.gestorBecas.solicitud"/></html:link></li>
				     		  <li><html:link page="/consultaBecasIncentivos/inicioConsultaBecasIncentivosByPersona.do?_opcid=gestorBecas.consulta.becas.incentivos"><bean:message key="title.gestorBecas.consulta.becas.incentivos"/></html:link></li>
								<li><a class="title" href="#reportes">Reportes</a></li>	
								<li><html:link page="/informes/becasByFecha.do?_opcid=gestorBecas.informe.becasByFecha&_cmd=inicio"><bean:message key="title.gestorBecas.informe.becasByFecha"/></html:link></li>
							</gesBecas:tieneRol>		
							<gesBecas:tieneRol roles="administradorRole">
								<li><a class="title" href="#adminusuarios">Gestión de Usuarios</a></li>	
								<li><html:link page="/adminBecasIncentivos/crearUsuario.do?_cmd=inicio&_opcid=gestorBecas.administracion.crearUsuario"><bean:message key="title.gestorBecas.administracion.crearUsuario"/></html:link></li>
								<li><html:link page="/adminBecasIncentivos/eliminarUsuario.do?_cmd=inicio&_opcid=gestorBecas.administracion.eliminarUsuario"><bean:message key="title.gestorBecas.administracion.eliminarUsuario"/></html:link></li>
								<li><html:link page="/adminBecasIncentivos/cargaMasivaUsuarios.do?_cmd=inicio&_opcid=gestorBecas.administracion.cargaMasivaUsuarios"><bean:message key="title.gestorBecas.administracion.cargaMasivaUsuarios"/></html:link></li>
							</gesBecas:tieneRol>
							</ul>
					</td>
				</tr>
			</table>           

	<br/>

	<br/>
	



