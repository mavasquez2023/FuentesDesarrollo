<%@ include file="../layout/headerJsp.jsp" %>
    <html>
    <head>
        <jsp:include page="../layout/header.jsp"></jsp:include>
      
      <title>Simulador Acuerdos de Pago</title>
		<script>
			function init(){
				var error= "${errorMsg}";
				if($("#rutConsulta").val()!="" && error!=""){
					setTimeout('$("#formParam")[0].submit()', 3000);
				}
				if($("#rutConsulta").val()!="" && $("#contrato").val()!=""){
					getCuotasAcuerdo();
				}
			}
		</script>
    </head>

    <body onload="init();">

       <jsp:include flush="true" page="../layout/banner_acuerdos.jsp"></jsp:include>
       <div align="center">
        <fieldset class="form-fieldset">
        <form action="<%=request.getContextPath()%>/getContratosAcuerdos.do" method="post" name="formParam" id="formParam" class="form">
           
              
                    <h2>Ingreso de datos</h2>
                    <div class="hr"></div>

                    <div class="field li">
                        <label class="span3-8">RUT</label>
                        <input type="text" name="rutConsulta" id="rutConsulta" value="${rutAfiliado}" class="text" onKeyPress="keyRut()" maxlength="12"/>&nbsp;&nbsp;
                        <input type="button" id="buscar" value="Buscar" class="boton" />
                        <label id="rutvalido" style="display:none;color:red">Ingrese un RUT válido</label>
                    </div>

        </form>
        <form action="<%=request.getContextPath()%>/resultadoSimuladorAcuerdosPago.do" method="post" name="formCalc" id="formCalc" class="form">          
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
                        <select name="contrato" id="contrato" onchange="getCampanhasAP();getCuotasAcuerdo();">
                            <option value="">Seleccione</option>
                            <c:forEach items="${listaCreditosVigentes}" var="credito">
                            	<option <c:if test="${numContratos==1 }">selected</c:if> value="${credito.folio}">${credito.folio}</option>
                            </c:forEach>
                        </select>
                        <html:errors property="contrato" />
                    </div>
                   
                    <div class="field li">
                        <label class="span3-8">Tipo afiliado</label>
                        <select name="tipoAfiliado" id="tipoAfiliado">
                            <option value="">Seleccione</option>
                            <c:forEach items="${afiliadosMap}" var="afil">
                                <option <c:if test="${numAfiliacion==1 }">selected</c:if> value="${afil.key}">${afil.value}</option>
                            </c:forEach>
                        </select>
                        <html:errors property="tipoAfiliado" />
                    </div>
                    
                    <div class="field li">
                        <label class="span3-8">Cuotas</label>
                        <select name="cuotas" id="cuotas" onchange="validaMontoAbono();">
                            <option value="">Seleccione</option>
                            <%-- 
                            <c:forEach var="num" begin="2" end="84">
                                <option value="${num}">${num}</option>
                            </c:forEach>
                            --%>
                        </select>
                        <html:errors property="cuotas" />
                    </div>
                    
                    <div class="field li">
                        <label class="span3-8">Monto Abono Inmediato</label>
                        <input type="text" name="montoAbono" id="montoAbono" class="text" value="${montoAbono}" maxlength="9"/><span id="mensajeError" style="color:red;">${errorMsg}</span>
                    </div>
                    
                    <div class="field li">
                        <label class="span3-8">% Condonación de capital</label>
                        <select name="dctoCapital" id="dctoCapital">
                        	<option value="0">No</option>
                        	<c:forEach var="num" begin="1" end="20">
                                <option value="${num*5}">${num*5}</option>
                        	</c:forEach>
                        </select>
                        <html:errors property="dctoCapital" />
                    </div>
                    <br/>
                    <div class="field li">
                        <label class="span3-8">Campaña Acuerdos de Pago</label>
                       	<div id="mensajeCampanha">${mensajeCampanha}<br/></div>                       
                    </div>
                    <br/><br/>
                    <div class="right" id="calcular">
                    	 <input type="reset" value="Limpiar" class="boton" onclick="limpiarAcuerdos();"/>
                        <input type="submit" id="bt_calcular" value="Calcular cuota" class="boton"/>
                         <input type="hidden" name="cuotas_selected" id="cuotas_selected" value="" />
                    </div>
                <br>
                
                  
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
                        cuotas: "required"
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
                 
                
            });//fin del ready
        </script>
    </body>

    </html>