<%@ include file="layout/headerJsp.jsp"%>
		
		<input type="hidden" class="ac" id="codPlano" name="codPlano" value="${codPlano}">
		
		<c:choose>
		   <c:when test="${KeyErrores==false}">
					<label>${msgMantenedor}</label>
					<!-- 1: LM -->
					<c:if test="${proceso == '1'}">
						<script>
							$(document).ready(function(){
								var plano=$("#codPlano").val();	
								var periodo=obtenerPeriodoMantenedor();
								var buttons = {};
	        					buttons["Aceptar"] = function() {
	        						cargarListado(plano,periodo);						
	        						$("#dialog_form_"+plano).dialog("close");
	        					};
	        					
	        					$("#dialog_form_"+plano).dialog({buttons:buttons});
	        				});
						</script>
					</c:if>
					<!-- 2: SIL -->
					<c:if test="${proceso == '2'}">
						<script>
							$(document).ready(function(){
							
								var plano=$("#codPlano").val();	
								var buttons = {};
	        					buttons["Aceptar"] = function() {
	        					
	        						cargarListadoLog(plano);						
	        						$("#dialog_form_"+plano).dialog("close");
	        					};
	        					
	        					$("#dialog_form_"+plano).dialog({buttons:buttons});
	        				});
						</script>
					</c:if>
					
					
				<form action="" name="form_mantenedor_SIL" id="form_mantenedor_SIL">
					<input type="hidden" id="op" name="op">
					<input type="hidden" id="perpag" name="perpag">		
					<input type="hidden" id="nrofol" name="nrofol">
					<input type="hidden" id="ruttrabaj" name="ruttrabaj">
					<input type="hidden" id="lichasfec" name="lichasfec">
					<input type="hidden" id="pagfol" name="pagfol">
					<input type="hidden" id="correlativ" name="correlativ">
				</form>			
					
			</c:when>
		    <c:otherwise>
		    	<script>
					$(document).ready(function(){			    	
				    	if($(".lbl_Error").text()!=""){
				    		//$(".lbl_Error").css("background-color","#fef1ec");
	    					$(".lbl_Error").css("color","#cd0a0a");
				    	}			    	
				    });
				</script>
		    
		    <form action="" name="form_mantenedor_SIL" id="form_mantenedor_SIL">
		    	<input type="hidden" id="op" name="op">
				<input type="hidden" id="perpag" name="perpag">
				<input type="hidden" id="correlativ" name="correlativ" value="${registro_SIL.correlativ}">
				<input type="hidden" id="archiv" name="archiv" value="${registro_SIL.archiv}">
		        
		        <div>
		        	<center><label id="txt_msj" style="color: red;"></label></center>
		        	<c:choose>
		        		<c:when test="${flagAct=='1'}">
		        			<center>
		        			<label id="txt_msj_ok" style="color: #00FF00;"></label>
		        			</center>
		        		</c:when>
		        		<c:otherwise>
		        			<center>
		        			<label id="txt_msj_nok" style="color: red;"></label>
		        			</center>
		        		</c:otherwise>
		        	</c:choose>
		        </div>
			    <br>
		        <div class="visorForm">
		        	<div class="campoForm">
						<label class="lbl_numero" >(1)</label>
						<label class="lbl_nombre" >Folio de pago</label>
						<input maxlength="7" class="txt_campo" id="pagfol" name="pagfol" value="${registro_SIL.pagfol}">
						<label class="lbl_Error" id="pagfol_error">${glosaError_SIL.pagfol}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(2)</label>
						<label class="lbl_nombre" >Fecha de t&eacute;rmino licencia</label>
						<input maxlength="8" class="txt_campo" id="lichasfec" name="lichasfec" value="${registro_SIL.lichasfec}">
						<label class="lbl_Error" id="lichasfec_error">${glosaError_SIL.lichasfec}</label>
					</div>
					
					<div class="campoForm">
						<label class="lbl_numero" >(3)</label>
						<label class="lbl_nombre" >Operador</label>
						<input maxlength="1" class="txt_campo" id="codope" name="codope" value="${registro_SIL.codope}">
						<label class="lbl_Error" id="codope_error">${glosaError_SIL.codope}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(4)</label>
						<label class="lbl_nombre" >Tipo formulario</label>
						<input maxlength="1" class="txt_campo" id="tpofor" name="tpofor" value="${registro_SIL.tpofor}">
						<label class="lbl_Error" id="tpofor_error">${glosaError_SIL.tpofor}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(5)</label>
						<label class="lbl_nombre" >Folio</label>
						<input maxlength="20" class="txt_campo" id="nrofol" name="nrofol" value="${registro_SIL.nrofol}">
						<label class="lbl_Error" id="nrofol_error">${glosaError_SIL.nrofol}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(6)</label>
						<label class="lbl_nombre" >Rut trabajador</label>
						<input maxlength="10" class="txt_campo" id="ruttrabaj" name="ruttrabaj" value="${registro_SIL.ruttrabaj}">
						<label class="lbl_Error" id="ruttrabaj_error">${glosaError_SIL.ruttrabaj}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(7)</label>
						<label class="lbl_nombre" >Fecha emisi&oacute;n licencia</label>
						<input maxlength="8" class="txt_campo" id="fecemi" name="fecemi" value="${registro_SIL.fecemi}">
						<label class="lbl_Error" id="fecemi_error">${glosaError_SIL.fecemi}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(8)</label>
						<label class="lbl_nombre" >Nro. de d&iacute;as de subsidio a pagar</label>
						<input maxlength="3" class="txt_campo" id="diasub" name="diasub" value="${registro_SIL.diasub}">
						<label class="lbl_Error" id="diasub_error">${glosaError_SIL.diasub}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(9)</label>
						<label class="lbl_nombre" >Monto subsidio l&iacute;quido</label>
						<input maxlength="9" class="txt_campo" id="mtoliq" name="mtoliq" value="${registro_SIL.mtoliq}">
						<label class="lbl_Error" id="mtoliq_error">${glosaError_SIL.mtoliq}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(10)</label>
						<label class="lbl_nombre" >Monto cotizaciones salud</label>
						<input maxlength="9" class="txt_campo" id="mtocot" name="mtocot" value="${registro_SIL.mtocot}">
						<label class="lbl_Error" id="mtocot_error">${glosaError_SIL.mtocot}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(11)</label>
						<label class="lbl_nombre" >Código instituci&oacute;n previsional</label>
						<input maxlength="5" class="txt_campo" id="codint" name="codint" value="${registro_SIL.codint}">
						<label class="lbl_Error" id="codint_error">${glosaError_SIL.codint}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(12)</label>
						<label class="lbl_nombre" >Fecha de inicio del pago</label>
						<input maxlength="8" class="txt_campo" id="finipa" name="finipa" value="${registro_SIL.finipa}">
						<label class="lbl_Error" id="finipa_error">${glosaError_SIL.finipa}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(13)</label>
						<label class="lbl_nombre" >Monto cotizaciones para pensi&oacute;n</label>
						<input maxlength="8" class="txt_campo" id="mocope" name="mocope" value="${registro_SIL.mocope}">
						<label class="lbl_Error" id="mocope_error">${glosaError_SIL.mocope}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(14)</label>
						<label class="lbl_nombre" >Monto base calculo subsidio</label>
						<input maxlength="8" class="txt_campo" id="baseca" name="baseca" value="${registro_SIL.baseca}">
						<label class="lbl_Error" id="baseca_error">${glosaError_SIL.baseca}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(15)</label>
						<label class="lbl_nombre" >Identificaci&oacute;n licencia continua</label>
						<input maxlength="10" class="txt_campo" id="idlice" name="idlice" value="${registro_SIL.idlice}">
						<label class="lbl_Error" id="idlice_error">${glosaError_SIL.idlice}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(16)</label>
						<label class="lbl_nombre" >Iniciado en el mes que se informa</label>
						<input maxlength="1" class="txt_campo" id="inimes" name="inimes" value="${registro_SIL.inimes}">
						<label class="lbl_Error" id="inimes_error">${glosaError_SIL.inimes}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(17)</label>
						<label class="lbl_nombre" >Tipo de licencia m&eacute;dica resuelta</label>
						<input maxlength="1" class="txt_campo" id="tpolic" name="tpolic" value="${registro_SIL.tpolic}">
						<label class="lbl_Error" id="tpolic_error">${glosaError_SIL.tpolic}</label>
					</div>
				</div>
				<div class="visorForm">
				
					<div class="campoForm">
						<label class="lbl_numero" >(18)</label>
						<label class="lbl_nombre" >N&uacute;mero de d&iacute;as de cotizaciones pagados</label>
						<input maxlength="3" class="txt_campo" id="ndicot" name="ndicot" value="${registro_SIL.ndicot}">
						<label class="lbl_Error" id="ndicot_error">${glosaError_SIL.ndicot}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(19)</label>
						<label class="lbl_nombre" >N&uacute;mero de d&iacute;as de incapacidad autorizados</label>
						<input maxlength="3" class="txt_campo" id="ndiinc" name="ndiinc" value="${registro_SIL.ndiinc}">
						<label class="lbl_Error" id="ndiinc_error">${glosaError_SIL.ndiinc}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(20)</label>
						<label class="lbl_nombre" >N&uacute;mero de d&iacute;as pagados en período informado</label>
						<input maxlength="3" class="txt_campo" id="ndipag" name="ndipag" value="${registro_SIL.ndipag}">
						<label class="lbl_Error" id="ndipag_error">${glosaError_SIL.ndipag}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(21)</label>
						<label class="lbl_nombre" >Monto subsidio pagado</label>
						<input maxlength="8" class="txt_campo" id="mtsbpa" name="mtsbpa" value="${registro_SIL.mtsbpa}">
						<label class="lbl_Error" id="mtsbpa_error">${glosaError_SIL.mtsbpa}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(22)</label>
						<label class="lbl_nombre" >Monto subsidio diario</label>
						<input maxlength="8" class="txt_campo" id="mtsbdi" name="mtsbdi" value="${registro_SIL.mtsbdi}">
						<label class="lbl_Error" id="mtsbdi_error">${glosaError_SIL.mtsbdi}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(23)</label>
						<label class="lbl_nombre" >Monto cotizaciones seguro cesant&iacute;a</label>
						<input maxlength="8" class="txt_campo" id="mcsegc" name="mcsegc" value="${registro_SIL.mcsegc}">
						<label class="lbl_Error" id="mcsegc_error">${glosaError_SIL.mcsegc}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(24)</label>
						<label class="lbl_nombre" >Monto otras cotizaciones</label>
						<input maxlength="8" class="txt_campo" id="motcot" name="motcot" value="${registro_SIL.motcot}">
						<label class="lbl_Error" id="motcot_error">${glosaError_SIL.motcot}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(25)</label>
						<label class="lbl_nombre" >Oficina de pago</label>
						<input maxlength="40" class="txt_campo" id="ofipgo" name="ofipgo" value="${registro_SIL.ofipgo}">
						<label class="lbl_Error" id="ofipgo_error">${glosaError_SIL.ofipgo}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(26)</label>
						<label class="lbl_nombre" >C&oacute;digo comuna de pago</label>
						<input maxlength="5" class="txt_campo" id="ccopgo" name="ccopgo" value="${registro_SIL.ccopgo}">
						<label class="lbl_Error" id="ccopgo_error">${glosaError_SIL.ccopgo}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(27)</label>
						<label class="lbl_nombre" >Instituci&oacute;n de salud</label>
						<input maxlength="10" class="txt_campo" id="inssal" name="inssal" value="${registro_SIL.inssal}">
						<label class="lbl_Error" id="inssal_error">${glosaError_SIL.inssal}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(28)</label>
						<label class="lbl_nombre" >Tipo de subsidio maternal</label>
						<input maxlength="1" class="txt_campo" id="submat" name="submat" value="${registro_SIL.submat}">
						<label class="lbl_Error" id="submat_error">${glosaError_SIL.submat}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(29)</label>
						<label class="lbl_nombre" >Tipo de liquidaci&oacute;n</label>
						<input maxlength="1" class="txt_campo" id="tpoliq" name="tpoliq" value="${registro_SIL.tpoliq}">
						<label class="lbl_Error" id="tpoliq_error">${glosaError_SIL.tpoliq}</label>
					</div>	
					<div class="campoForm">
						<label class="lbl_numero" >(30)</label>
						<label class="lbl_nombre" >Fecha de pago del subsidio</label>
						<input maxlength="8" class="txt_campo" id="fecpgo" name="fecpgo" value="${registro_SIL.fecpgo}">
						<label class="lbl_Error" id="fecpgo_error">${glosaError_SIL.fecpgo}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(31)</label>
						<label class="lbl_nombre" >Monto l&iacute;quido pagado</label>
						<input maxlength="8" class="txt_campo" id="mliqpa" name="mliqpa" value="${registro_SIL.mliqpa}">
						<label class="lbl_Error" id="mliqpa_error">${glosaError_SIL.mliqpa}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(32)</label>
						<label class="lbl_nombre" >Remuneraci&oacute;n imponible mes anterior a licencia</label>
						<input maxlength="8" class="txt_campo" id="rimpms" name="rimpms" value="${registro_SIL.rimpms}">
						<label class="lbl_Error" id="rimpms_error">${glosaError_SIL.rimpms}</label>
					</div>	
				</div>
			</form>
		    </c:otherwise>
		</c:choose>
		
		
		