<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>RESULTADO NRP</title>
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
	<p class="titulo">PROCESO EN EJECUCIÓN</p>
	<form action="init.do" name="form1" method="post">
	</form>
	<div id="bienvenida" style="display: none;">
	<p>	

	<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF;">

	<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: left;">
			Resultado del proceso NRP será enviado por correo.
		</td>
		
	</tr>
	<tr><td style="border: 0 px;border-color: #FFFFFF;">&nbsp;</td></tr>
	<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: left;">En la opción Estadísticas del menú puede revisar los gráficos estadísticos.
		</td>
		
	</tr>
	
	<tr><td style="border: 0 px;border-color: #FFFFFF;">&nbsp;</td></tr>
	</table>
	
 	</p>
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