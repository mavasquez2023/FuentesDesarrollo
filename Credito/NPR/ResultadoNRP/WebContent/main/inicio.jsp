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
	<p class="titulo">BIENVENIDO</p>
	<form action="init.do" name="form1" method="post">
	</form>
	<div id="bienvenida" style="display: none;">
	<p>	

	<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF;">
	<c:if test="${error==0}">
	<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: left;">
			Desde el men� de Resultados Proceso NRP, podr�  ejecutar y revisar los resultados del proceso de consolidaci�n, generaci�n y disponibilizaci�n.
		</td>
		
	</tr>
	<tr><td style="border: 0 px;border-color: #FFFFFF;">&nbsp;</td></tr>
	<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: left;">
			Configure los correos por cada proceso para ser informado autom�ticamente por correo el resultado de cada operaci�n.
		</td>
		
	</tr>
	<tr><td style="border: 0 px;border-color: #FFFFFF;">&nbsp;</td></tr>
	<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: left;">Adem�s por la p�gina revise los gr�ficos estad�sticos para una mejor evaluaci�n.
		</td>
		
	</tr>
	
	</c:if>
	<c:if test="${error==1}">
		<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: left;">
			${errorMsg}
		</td>
		
	</tr>

	</c:if>
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