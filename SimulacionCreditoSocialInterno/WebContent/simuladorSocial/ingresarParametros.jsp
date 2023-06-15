<%@ include file="../layout/headerJsp.jsp" %>
    <html>
    <head>
        <jsp:include page="../layout/header.jsp"></jsp:include>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/autoNumeric.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.Rut.min.js"></script>
        <title></title>
    </head>

    <body>

        <p class="titulo">Simulador de Crédito Social Interno</p>
        <form action="<%=request.getContextPath()%>/resultadoSimuladorSocial.do" method="post" name="formCalc" id="formCalc" class="form">
            <fieldset class="form-fieldset">
                <div class="padding-s">
                    <h2>Ingreso de datos</h2>
                    <div class="hr"></div>

                    <div class="field li">
                        <label class="span2-8">RUT</label>
                        <input type="text" name="rutAfiliado" id="rutAfiliado" value="${rutAfiliado}" class="text" />
                        <html:errors property="rut" />
                    </div>
                    
                    <div class="field li">
                        <label class="span2-8">Monto a Solicitar(*)</label>
                        <input type="text" name="montoShow" id="montoShow" class="text" value="${montoPreAprobado}"/><span id="mensajeMonto"></span>
                        <html:errors property="monto" />
                    </div>
                   
                    <div class="field li">
                        <label class="span2-8">Cuotas</label>
                        <select name="cuotas">
                            <option value="">Seleccione</option>
                            <c:forEach var="num" begin="3" end="60">
                                <option value="${num}">${num}</option>
                            </c:forEach>
                        </select>
                        <html:errors property="cuotas" />
                    </div>
                    <div class="field li">
                        <label class="span2-8">Oficina de solicitud</label>
                        <select name="oficina">
                            <option value="">Seleccione</option>
                            <c:forEach items="${oficinasList}" var="oficina">
                            	<option value="${oficina.codigo}">${oficina.descripcion}</option>
                            </c:forEach>
                            <%-- <c:forEach items="${oficinasMap}" var="ofi">
                                <option value="${ofi.value}">${ofi.key}</option>
                                </c:forEach>--%>
                        </select>
                        <html:errors property="oficina" />
                    </div>
                    <div class="field li">
                        <label class="span2-8">Tipo afiliado</label>
                        <select name="tipoAfiliado" id="tipoAfiliado">
                            <option value="">Seleccione</option>
                            <c:forEach items="${afiliadosMap}" var="afil">
                                <option value="${afil.value}">${afil.key}</option>
                            </c:forEach>
                        </select>
                        <html:errors property="tipoAfiliado" />
                    </div>
                    
                    <div class="field li">
                        <label class="span2-8">Seguro Cesantía</label>
                        <select name="segCesantia" id="segCesantia">
                            <option value="">No</option>
                            <option value="S">Si</option>
                        </select>
                        <html:errors property="segCesantia" />
                    </div>
                     <div class="field li">
                        <label class="span2-8">Tasa mensual</label>
                        <input type="text" name="tasaShow" id="tasaShow" class="text" value=""/><span id="mensajePorcentaje"></span>
                        <html:errors property="tasaMensual" />
                    </div>
                    
                    <div class="right">
                    	 <input type="hidden" name="monto" id="monto" />
                    	 <input type="hidden" name="tasaMensual" id="tasaMensual" />
                    	 <!-- <input type="hidden" name="segCesantia" id="segCesantia" value=""/> -->
                        <input type="submit" value="Calcular cuota" class="boton" />
                    </div>
                </div>
                <br>
            </fieldset>
        </form>

        <div id="loading" style="position:absolute; top:35%; left:40%; display:none; z-index: auto">
            <img src="<%=request.getContextPath() %>/img/3d-loader.gif">
        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                //inicio del ready
                
                
                var montoJs = $("#montoShow").val().split('.').join(''); 
	 			montoJs = montoJs.split('$').join(''); 
	 			$("#monto").val(montoJs); 
	 			
	 			var tasaJs = $("#tasaShow").val().split('%').join(''); 
	 			$("#tasaMensual").val(tasaJs); 
 			
                //Validaciones del formulario
                $("#formCalc").validate({
                    ignore: [],
                    rules: {
                    	rutAfiliado: {
							required: true,
							rut: true
						},
                        monto: {
                            required: true,
                            min: parseInt("${min_social}"),
                            max: parseInt("${max_social}")
                        },
                        tasaMensual: {
                            required: true,
                            min: parseFloat("${min_tasa_mensual_social}"),
                            max: parseFloat("${max_tasa_mensual_social}")
                        },
                        cuotas: "required",
                        oficina: "required",
                        tipoAfiliado: "required"
                    },
                    messages: {
                        monto: {
                            min: '* Monto menor a <fmt:formatNumber type="currency" currencySymbol="$" value="${min_social}" />',
                            max : "* Monto mayor a <fmt:formatNumber type="currency" currencySymbol="$" value="${max_social}" />" 
                        },
                        tasaMensual: {
                            min: "* Tasa mensual menor a %${min_tasa_mensual_social}",
                            max : "* Tasa mensual mayor a %${max_tasa_mensual_social}" 
                        }
                    },
                    errorPlacement: function(error, element) {
                        if (element.attr("name") == "monto" ) {
                            error.appendTo("#mensajeMonto");
                        } else if (element.attr("name") == "tasaMensual" ) {
                            error.appendTo("#mensajePorcentaje");
                        }
                        else
                        	error.insertAfter(element);
                    }
                });

                jQuery(function ($) {
                    $('#montoShow').autoNumeric('init', {
                        aSep: '.',
                        aDec: ',',
                        mDec: '0',
                        aSign: '$'
                    });
                });

                $("#montoShow").bind(
                    'change keyup',
                    function () {
                        var montoJs = $("#montoShow").val()
                            .split('.').join('');
                        montoJs = montoJs.split('$').join('');
                        $("#monto").val(montoJs);
                        $("#formCalc").validate().element(
                            "#monto");
                    });
               $("#tasaShow").bind(
                    'change keyup',
                    function () {
                        var tasaJs = $("#tasaShow").val()
                            .split('%').join('');
                        $("#tasaMensual").val(tasaJs);
                        $("#formCalc").validate().element(
                            "#tasaMensual");
                    });

                $(window).bind('beforeunload', function () {
                    $('#loading').show();
                });
                
                $("#rutAfiliado").Rut({
					format_on : 'keyup'
				}); 
                
                 $("#rutAfiliado").Rut({
					format_on : 'onload'
				});  
                 
                 
                $("#tipoAfiliado").change(function(){
                	
                	if($(this).val() == "${cod_pensionado}")
                	  {
                		$("#segCesantia").val("");
                		$("#segCesantia").attr('disabled', 'disabled');
                	  }
                	  else
                	  {
                		  $('#segCesantia').removeAttr('disabled');
                	  }
                		  
                });
                
            });//fin del ready
        </script>

    </body>

    </html>