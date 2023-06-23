<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>

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
				       			<li><a class="title" href="#reportes">Reportes</a></li>	
								<li><html:link page="/informes/comprobanteIngreso.do?_cmd=inicio"><bean:message key="title.menu.posicion1"/></html:link></li>
								
								<!-- 
								<li><a class="title" href="#adminusuarios">Gestión de Usuarios</a></li>	
								<li><html:link page="/admin/crearUsuario.do?_cmd=inicio&_opcid=admin.usuario_crear"><bean:message key="title.menu.posicion2"/></html:link></li>
								<li><html:link page="/admin/eliminarUsuario.do?_cmd=inicio&_opcid=admin.usuario_eliminar"><bean:message key="title.menu.posicion3"/></html:link></li>
						 -->
							</ul>
					</td>
				</tr>
			</table>           

	<br/>

	<br/>
	



