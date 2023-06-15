<%@ include file="../layout/headerJsp.jsp" %>
    <html>
    <head>
        <jsp:include page="../layout/header.jsp"></jsp:include>
        
        <title>Simulador Crédito Reprogramacion</title>
		<script>
			function init(){
				var error= "${errorMsg}";
				if($("#rutConsulta").val()!="" && error!=""){
					setTimeout('$("#formParam")[0].submit()', 3000);
				}
				if($("#rutConsulta").val()!="" && $("#contrato").val()!=""){
					getCabeceras();
					getCuotas();
					getMesesGracia();
					getCesantia();
					getDesgravamen();
				}
				
			}
		</script>
    </head>

    <body onload="init();">

       <jsp:include flush="true" page="../layout/banner.jsp"></jsp:include>
       <div align="center">
        <fieldset class="form-fieldset">
        <form action="<%=request.getContextPath()%>/getContratos.do" method="post" name="formParam" id="formParam" class="form">
           
              
                    <h2>Ingreso de datos</h2>
                    <div class="hr"></div>

                    <div class="field li">
                        <label class="span3-8">RUT</label>
                        <input type="text" name="rutConsulta" id="rutConsulta" value="${rutAfiliado}" class="text" onKeyPress="keyRut()" maxlength="12"/>&nbsp;&nbsp;
                        <input type="button" id="buscar" value="Buscar" class="boton" />
                        <label id="rutvalido" style="display:none;color:red">Ingrese un RUT válido</label>
                    </div>

        </form>
        <form action="<%=request.getContextPath()%>/resultadoSimuladorReprogramacion.do" method="post" name="formCalc" id="formCalc" class="form">          
                    <input type="hidden" name="rutAfiliado" id="rutAfiliado" value="${rutAfiliado}"  />
                    <input type="hidden" name="nombreAfiliado" id="nombreAfiliado" value="${nombreCliente}"  />
                    <c:if test="${nombreCliente!=null}">
                    <div class="field li" id="nombre">
                        <label class="span3-8">Nombre</label>
                        ${nombreCliente}
                    </div>
                    </c:if>
                    <div class="field li">	
                        <label class="span3-8">Contrato a Simular</label>
                        <select name="contrato" id="contrato" onchange="getCabeceras();">
                            <option value="">Seleccione</option>
                            <c:forEach items="${listaCreditosVigentes}" var="credito">
                            	<option <c:if test="${numContratos==1 }">selected</c:if> value="${credito.folio}">${credito.folio}</option>
                            </c:forEach>
                        </select>
                        <html:errors property="contrato" />
                    </div>
                   
                    <div class="field li">
                        <label class="span3-8">Tipo afiliado</label>
                        <select name="tipoAfiliado" id="tipoAfiliado" onchange="getProductos();getMesesGracia();">
                            <option value="">Seleccione</option>
                            <c:forEach items="${afiliadosMap}" var="afil">
                                <option <c:if test="${numAfiliacion==1 }">selected</c:if> value="${afil.key}">${afil.value}</option>
                            </c:forEach>
                        </select>
                        <html:errors property="tipoAfiliado" />
                    </div>
                    
                    <div class="field li">	
                        <label class="span3-8">Producto reprogramación</label>
                        <select name="productos" id="productos" onchange="getCuotas();getMesesGracia();validaMontoAbonoxCesante();getCesantia();getDesgravamen();">
                            <option value="">Seleccione</option>
                            <c:forEach items="${listaProductos}" var="producto">
                            	<option value="${producto}">${producto}</option>
                            </c:forEach>
                        </select>
                        <html:errors property="productos" />
                    </div>
                    
                    <div class="field li">
                        <label class="span3-8">Cuotas</label>
                        <select name="cuotas" id="cuotas">
                            <option value="">Seleccione</option>
                            <!-- 
                            <c:forEach var="num" begin="3" end="60">
                                <option value="${num}">${num}</option>
                            </c:forEach>
                             -->
                        </select>
                        <html:errors property="cuotas" />
                    </div>
                    
                    <div class="field li">
                     <label class="span3-8">Meses de Gracia</label>
                     <select name="mesesGracia" id="mesesGracia">
                            <option value="" selected>Seleccione</option>
                        </select>
                        <html:errors property="mesesGracia" />
                    </div>
                    <div class="field li">
                        <label class="span3-8">Monto Abono Inmediato</label>
                        <input type="text" name="montoAbono" id="montoAbono" class="text" value="" maxlength="9"/><span id="mensajeMonto" style="color:red;">${errorMsg}</span>
                    </div>
                     <div class="field li">
                        <label class="span3-8">Renta del cliente</label>
                        <input type="text" name="renta" id="renta" class="text" value="" maxlength="9"/>
                        <html:errors property="renta" />
                    </div>
                                      
                    <div class="right" id="calcular">
                    	 <input type="reset" value="Limpiar" class="boton" onclick="limpiar();"/>
                        <input type="submit" id="bt_calcular" value="Calcular cuota" class="boton"/>
                    </div>
                </div>
                <br>
                <input type="hidden" name="insolvencia" id="insolvencia" value="${insolvencia }" />
                 <input type="hidden" name="cesantia" id="cesantia" value="${cesantia }" />
                  <input type="hidden" name="desgravamen" id="desgravamen" value="${desgravamen }" />
                  <input type="hidden" name="cuotas_selected" id="cuotas_selected" value="" />
                  <input type="hidden" name="mesesGracia_selected" id="mesesGracia_selected" value="" />
                  <input type="hidden" name="productos_selected" id="productos_selected" value="" />
                  <input type="hidden" name="edad" id="edad" value="${edad }" />
                  <input type="hidden" name="anexos" id="anexos" value="${anexos }" />
                  <input type="hidden" name="oficina" id="anexos" value="50000158" />
                  <input type="hidden" name="segCesantia" id="segCesantia" value="" />
                  <input type="hidden" name="segDesgravamen" id="segDesgravamen" value="" />
                  <input type="hidden" name="dctoGascob" id="dctoGascob" value="100" />
                  <input type="hidden" name="dctoGasint" id="dctoGasint" value="100" />
                  <input type="hidden" name="dctoGravam" id="dctoGravam" value="100" />

                  
        </form>
        </fieldset>
		</div>
        <div id="loading" style="position:absolute; top:35%; left:40%; display:none; z-index: auto">
            <img src="<%=request.getContextPath() %>/img/3d-loader.gif">
        </div>

        <script type="text/javascript">
            $(document).ready(function () {
            	 $( "#buscar" ).click(function() {
            	 	
  					if(!validaRut($("#rutConsulta").val())){
  						$("#rutvalido").show();
  						$("#rutConsulta").focus();
  						setTimeout('$("#rutvalido").hide()', 3000);
  						
  					}else{
  					 document.formParam.submit();
  					}
				 });
                //Validaciones del formulario Calc
                $("#formCalc").validate({
                    ignore: [],
                    rules: {
                        contrato: "required",
                        tipoAfiliado: "required",
                        productos: "required",
                        cuotas: "required",
                        renta:"required",
                        mesesGracia:"required"
                    },
                    messages: {
                        
                    },
                    errorPlacement: function(error, element) {
                       error.insertAfter(element);
                    }
                });
				

                $(window).bind('beforeunload', function () {
                    $('#loading').show();
                });
                
                //format RUT
                $("#rutConsulta").Rut({
					format_on : 'keyup',
				});
				$("#rutConsulta").keyup(function() {
        			$(this).val($(this).val().toUpperCase());
        		});
        		$("#rutConsulta").change(function() {
        			$(this).val($(this).val().toUpperCase());
       			 });
                
                //format montoAbono
                $('#montoAbono').number( true, 0, ',', '.' ); 
                 
                 //format renta
                 $('#renta').number( true, 0, ',', '.' ); 
				
				/*definitions: {
				'9': "[0-9]",
				'a': "[A-Za-z]",
				'*': "[A-Za-z0-9]"
				}*/
				$('.date').mask('11/11/1111');
      			$('.time').mask('00:00:00');
      			$('.date_time').mask('99/99/9999 00:00:00');
      			$('.phone').mask('9-9999-9999');
      			$('.phone_with_ddd').mask('(99) 99 999 99 99');
            });//fin del ready
        </script>
		<script>
			 <c:if test="${mensaje_edad!=null}">
			 alert("${mensaje_edad}");
			 bloqueaRepro();
			 </c:if>
		</script>
    </body>

    </html>