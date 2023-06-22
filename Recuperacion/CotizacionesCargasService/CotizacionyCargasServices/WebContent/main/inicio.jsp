<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>SERVICIOS COTIZACION Y CARGAS</title>
<script language="JavaScript 1.2" type="text/javascript">
function init(){
		<c:if test="${dataemp!=null}">
			$( "#bienvenida" ).hide();
			$( "#dialog" ).dialog( "open" );
			setTimeout('$( "#dialog" ).dialog( "close" );', 4000);
			setTimeout('$( "#bienvenida" ).fadeIn();', 4100);
		</c:if>
		<c:if test="${dataemp==null}">
			setTimeout('$( "#bienvenida" ).fadeIn();', 500);
		</c:if>
	}

</script>
<script>	
  $( function() {
    $( "#dialog" ).dialog({
      autoOpen: false,
      width: 500,
      height: 150,
      position: 100,
      show: {
        effect: "blind",
        duration: 300
      },
      hide: {
        effect: "blind",
        duration: 150
      }
    });

  } );
  </script>
</head>
<body onLoad="init();">
<jsp:include page="banner.jsp" flush="true" />
<jsp:include page="menuServices.jsp" flush="true" />
<div style="position: relative; left: 30px; width: 1170px;">
	<p class="titulo">BIENVENIDO</p>
	<form action="init.do" name="form1" method="post">
	</form>
	<div id="bienvenida" style="display: none;">
	<p>	

	<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF;">
	<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: left;">
			Desde el menú de Cotizaciones, podrá descargar y validar los trabajadores que se encuentran Activos en nuestro registros y que por algún motivo no se ha recepcionado el pago de sus cotizaciones.
		</td>
		
	</tr>
	<tr><td style="border: 0 px;border-color: #FFFFFF;">&nbsp;</td></tr>
	<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: left;">
			Si ya no se encuentran prestando servicios en su empresa  puede regularizar esta información  desde el menú  de Movimiento Personal, lo que permitirá realizar la cesación del  afiliado en nuestros registros.
		</td>
		
	</tr>
	<tr><td style="border: 0 px;border-color: #FFFFFF;">&nbsp;</td></tr>
	<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: left;">
			Desde el menú de Cargas Familiares, podrá descargar el detalle  relacionado con el pagos de las asignaciones familiares a su persona, identificándose por trabajador las diferencias que podrían existir entre los montos autorizados y los cancelados en el último proceso de cotizaciones.
		</td>
		
	</tr>
	<tr><td style="border: 0 px;border-color: #FFFFFF;">&nbsp;</td></tr>
	</table>
	
 	</p>
	</div>
	<div id="dialog" title="Empresa Activada" style="background: #ccc; display: none;">
		<table align="center" class="tabla-creditos"
			style="width: 400px; border-color: #FFFFFF;">

			<c:forEach var="entry" varStatus="vs" items="${dataemp}">
				<tr>
					<th class="certificadoLeft">${entry.key}</th>
					<th class="certificadoLeft">${entry.value}</th>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
	<script>
	$(document).ready(function(){
		$("#accordian h3").click(function(){
			//slide up all the link lists
			$("#accordian ul ul").slideUp();
			//slide down the link list below the h3 clicked - only if its closed
			if(!$(this).next().is(":visible"))
			{
				$(this).next().slideDown();
			}
		});
	});
	</script>
</body>
</html>