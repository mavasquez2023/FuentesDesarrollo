<%@ include file="../comun/headerJsp.jsp"%>
<div id="accordian">
<h1 style="color: white; font-family: Aharoni, Futura-Normal; font-size: 25px"><b class="titulo_menu">&nbsp;<a href="<%=request.getContextPath() %>/init.do" title="Actualizar menú">La Araucana</a></b></h1>
<!-- table style="width: 980px;border: 0 px;border-color: #FFFFFF;">
  <tr style="border: 0 px;border-color: #FFFFFF;">
    <td style="text-align: left;border: 0 px;border-color: #FFFFFF;"><img src="img/logo_reducido.jpg" /></td>
    <td style="vertical-align: top;border: 0 px;border-color: #FFFFFF;"><a href="index.jsp" title="Volver a Servicios en Línea"><i class="fas fa-home"></a></i></td>
  </tr>
</table-->


	<ul><c:if test="${rol=='Ejecutivo_Atencion' }">
			<li <c:if test="${menu=='atencion'}">class="active" </c:if>>
				<h3>
					<span class="fas fa-angle-down"></span>Activar RUT Empresa
				</h3>
				<ul>	
					<br>
					<form action="<%=request.getContextPath() %>/changeRut.do" method="post">
					<input type="text" size="12" maxlength="12" id="rutemp_menu" name="rutemp_menu" value="${rutEmpresa}" style="margin-left: -40px">&nbsp;
					<input class="boton" id="cambiar" type="submit" value="Activar"/>
					</form>				
					<br>
					<br>
				
				</ul>
			</li>
		</c:if>
		<c:if test="${rol!='Ejecutivo_Cesacion' }">
			<li <c:if test="${menu=='cotizaciones'}">class="active" </c:if>>
				<h3>
					<span class="fas fa-angle-down"></span>Cotizaciones
				</h3>
				<ul>
					<li><a href="#" onclick="Redirect('cotizaciones');">Trabajadores
							No Cotizados</a></li>
					<!-- li><a href="#" onclick="Redirect('cotizaciones', '12');">Trabajadores
							No Cotizados &uacute;ltimos 12 meses</a></li-->
				</ul>
			</li>
			<!-- we will keep this LI open by default -->

			<li <c:if test="${menu=='cargas'}">class="active" </c:if>>
				<h3>
					<span class="fas fa-angle-down"></span>Cargas Familiares
				</h3>
				<ul>
					<li><a href="#" onclick="Redirect('cargas');">Diferencias Pago Cargas Familiares </a></li>
				</ul>
			</li>
<!-- 
			<li <c:if test="${menu=='cesacion'}">class="active" </c:if>>
				<h3>
					<span class="fas fa-angle-down"></span>Movimientos Personal
				</h3>
				<ul>
					<li><a href="#" onclick="Redirect('cesacion');">Informar
							Cesaci&oacute;n Trabajador</a>
					</li>
					<li><a href="#" onclick="Redirect('cesacionfile');">Informar
							Cesaci&oacute;n Masiva</a>
					</li>
				</ul></li>
			<li <c:if test="${menu=='certificado'}">class="active" </c:if>>
				<h3>
					<span class="fas fa-angle-down"></span>Certificados
				</h3>
				<ul>
					<li><a href="#" onclick="Redirect('certificado');">Certificado
							Cesaci&oacute;n Trabajador</a>
					</li>
				</ul></li>
-->
			<li <c:if test="${menu=='administracion'}">class="active" </c:if>>
				<h3>
					<span class="fas fa-angle-down"></span>Administración
				</h3>
				<ul>
					<li><a href="#" onclick="Redirect('encargados');">Encargados</a>
					</li>
<!-- 
					<li><a href="#" onclick="Redirect('correos');">Correos</a>
					</li>
-->
				</ul></li>
		</c:if>
<!-- 
		<c:if test="${rol=='Ejecutivo_Cesacion' }">
			<li <c:if test="${menu=='cesacion'}">class="active" </c:if>>
				<h3>
					<span class="fas fa-angle-down"></span>Movimientos Personal
				</h3>
				<ul>
					<li><a href="#" onclick="Redirect('cesacionfile');">Informar
							Cesaci&oacute;n Masiva</a>
					</li>
				</ul>
			</li>
		</c:if>
-->
	</ul>
	
</div>
<script>
$(document).ready(
	function() {
				
		$("#rutemp_menu").Rut({
			format_on : 'keyup'
		}); 
		
	}); // fin del document ready
function Redirect(destino){
	//var numeromeses= ""; 
	//if(cantidad!=""){
	//	numeromeses= "&numeroMeses=" + cantidad;
	//}
	form1.action=destino + ".do?accion=menu";
	form1.submit();
}

</script>
