<%@ include file="/layout/headerJsp.jsp" %>
    <html>
    <head>
        <jsp:include page="/layout/header.jsp"></jsp:include>
        
        <title>Simulador Reprogramación y Acuerdos de Pago</title>
		
    </head>

    <body>

       <jsp:include flush="true" page="/layout/banner_principal.jsp"></jsp:include>
       <div align="center">
        <fieldset class="form-fieldset">
        <h2>Simuladores Crédito Web</h2>
						<div class="hr"></div>
			<table>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<form
							action="<%=request.getContextPath()%>/simuladorReprogramacion.do"
							method="post" name="formRepro" id="formRepro" class="form">



							<div>
								<a href="#" id="reprogramacion"><img
									src="img/reprogramacion.jpg" width="209" height="227"
									border="0" onmouseover="src='img/reprogramacion_off.jpg'"
									onmouseout="src='img/reprogramacion.jpg'" />
								</a>
							</div>

						</form>
					</td>
					<td width="80">&nbsp;</td>
					<td>
						<form
							action="<%=request.getContextPath()%>/simuladorAcuerdosPago.do"
							method="post" name="formAcuerdo" id="formAcuerdo" class="form">

							<div>
								<a href="#" id="acuerdo"><img src="img/acuerdo_pago.jpg"
									width="209" height="227" border="0"
									onmouseover="src='img/acuerdo_pago_off.jpg'"
									onmouseout="src='img/acuerdo_pago.jpg'" />
								</a>
							</div>

						</form>
					</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
			</table>
		</fieldset>
		</div>
        <div id="loading" style="position:absolute; top:35%; left:40%; display:none; z-index: auto">
            <img src="<%=request.getContextPath() %>/img/3d-loader.gif">
        </div>

        <script type="text/javascript">
            $(document).ready(function () {
            	 $("#reprogramacion").click(function() {
            	 	$("#formRepro")[0].submit();
  					
				 });
				 $("#acuerdo").click(function() {
            	 	$("#formAcuerdo")[0].submit();
  					
				 });
                 
                
            });//fin del ready
        </script>
    </body>

    </html>