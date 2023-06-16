<%@ include file="../comun/headerJsp.jsp"%>
<div id="accordian">
<h1 style="color: white; font-family: Aharoni, Futura-Normal; font-size: 25px"><b>&nbsp;La Araucana</b></h1>
<!-- table style="width: 980px;border: 0 px;border-color: #FFFFFF;">
  <tr style="border: 0 px;border-color: #FFFFFF;">
    <td style="text-align: left;border: 0 px;border-color: #FFFFFF;"><img src="img/logo_reducido.jpg" /></td>
    <td style="vertical-align: top;border: 0 px;border-color: #FFFFFF;"><a href="index.jsp" title="Inicio"><i class="fas fa-home"></a></i></td>
  </tr>
</table-->


	<ul>

			<li <c:if test="${menu=='fonasa'}">class="active" </c:if>>
				<h3>
					<span class="fas fa-angle-down"></span>Fonasa
				</h3>
				<ul>
					<li><a href="#" onclick="Redirect('fonasa', '');">Estado Licencias</a>
					</li>
					<li><a href="#" onclick="Redirect('fonasafile', '');">Estado Licencias Masiva</a>
					</li>
					<li><a href="#" onclick="Redirect('bitacora', '');">Consulta Bitácora</a>
					</li>
				</ul>
			</li>
			
		
	</ul>
</div>
<script>
function Redirect(destino, cantidad){
	form1.action=destino + ".do?accion=menu";
	form1.submit();
}
</script>
