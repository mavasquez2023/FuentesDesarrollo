<%@ include file="layout/headerJsp.jsp"%>
		<!-- Campos Hideen -->
		<input type="hidden" class="ac" id="codPlano" name="codPlano" value="${codPlano}">
		
		
		<c:choose>
		   <c:when test="${KeyErrores==false}">
					<label>${msgMantenedor}</label>
					<!-- Selección entre Pop-Up a abrir, opción 1: Mantenedor, 2: Log Errores. -->
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
					<c:if test="${proceso == '2'}">
						<script>
							$(document).ready(function(){
								
								var plano=$("#codPlano").val();	
								var periodo=obtenerPeriodoMantenedor();
								var buttons = {};
	        					buttons["Aceptar"] = function() {
	        					
	        						cargarListadoLog(plano);						
	        						$("#dialog_form_"+plano).dialog("close");
	        					};
	        					
	        					$("#dialog_form_"+plano).dialog({buttons:buttons});
	        				});
						</script>
					</c:if>
					
					
				<form action="" name="form_mantenedor_LM" id="form_mantenedor_LM">
					<input type="hidden" id="op" name="op">
					<input type="hidden" id="fecproceso" name="fecproceso">
					<input type="hidden" id="fecterrepo" name="fecterrepo">
					<input type="hidden" id="folio" name="folio">
					<input type="hidden" id="ruttrabaj" name="ruttrabaj">
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
				
		    <form action="" name="form_mantenedor_LM" id="form_mantenedor_LM">
		    	<input type="hidden" id="op" name="op">
				<input type="hidden" id="fecproceso" name="fecproceso">
				<input type="hidden" id="correlativ" name="correlativ" value="${registro_LM.correlativ}">
				<input type="hidden" id="archivo" name="archivo" value="${registro_LM.archivo}">
				<input type="hidden" id="licrechaz" name="licrechaz" value="${registro_LM.licrechaz}">
				
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
						<label class="lbl_numero" >( 1)</label>
						<label class="lbl_nombre" >Operador</label>
						<input type="text"  maxlength="1" class="txt_campo" id="operador" name="operador" value="${registro_LM.operador}">
						<label class="lbl_Error" id="operador_error">${glosaError_LM.operador}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >( 2)</label>
						<label class="lbl_nombre" >Tipo formulario</label>
						<input type="text"  maxlength="1" class="txt_campo" id="tipoform" name="tipoform" value="${registro_LM.tipoform}">
						<label class="lbl_Error" id="tipoform_error">${glosaError_LM.tipoform}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >( 3)</label>
						<label class="lbl_nombre" >Folio</label>
						<input type="text"  maxlength="20" class="txt_campo" id="folio" name="folio" value="${registro_LM.folio}">
						<label class="lbl_Error" id="folio_error">${glosaError_LM.folio}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >( 4)</label>
						<label class="lbl_nombre" >Artículo 77 bis</label>
						<input type="text" maxlength="1" class="txt_campo" id="art77bis" name="art77bis" value="${registro_LM.art77bis}">
						<label class="lbl_Error" id="art77bis_error">${glosaError_LM.art77bis}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >( 5)</label>
						<label class="lbl_nombre" >Fecha de información</label>
						<input type="text"  maxlength="8" class="txt_campo" id="fecinform" name="fecinform" value="${registro_LM.fecinform}">
						<label class="lbl_Error" id="fecinform_error">${glosaError_LM.fecinform}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >( 6)</label>
						<label class="lbl_nombre" >Rut trabajador</label>
						<input type="text"  maxlength="10" class="txt_campo" id="ruttrabaj" name="ruttrabaj" value="${registro_LM.ruttrabaj}">
						<label class="lbl_Error" id="ruttrabaj_error">${glosaError_LM.ruttrabaj}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >( 7)</label>
						<label class="lbl_nombre" >Fecha emisión licencia</label>
						<input type="text"   maxlength="8" class="txt_campo" id="fecemision" name="fecemision" value="${registro_LM.fecemision}">
						<label class="lbl_Error" id="fecemision_error">${glosaError_LM.fecemision}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >( 8)</label>
						<label class="lbl_nombre" >Fecha inicio de reposo</label>
						<input type="text"  maxlength="8" class="txt_campo" id="fecinirepo" name="fecinirepo" value="${registro_LM.fecinirepo}">
						<label class="lbl_Error" id="fecinirepo_error">${glosaError_LM.fecinirepo}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >( 9)</label>
						<label class="lbl_nombre" >Fecha término de reposo</label>
						<input type="text"  maxlength="8" class="txt_campo" id="fecterrepo" name="fecterrepo" value="${registro_LM.fecterrepo}">
						<label class="lbl_Error" id="fecterrepo_error">${glosaError_LM.fecterrepo}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(10)</label>
						<label class="lbl_nombre" >Edad trabajador</label>
						<input type="text"  maxlength="2" class="txt_campo" id="edadtrabaj" name="edadtrabaj" value="${registro_LM.edadtrabaj}">
						<label class="lbl_Error" id="edadtrabaj_error">${glosaError_LM.edadtrabaj}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(11)</label>
						<label class="lbl_nombre" >Fecha nacimiento trabajador</label>
						<input type="text"  maxlength="8" class="txt_campo" id="fecnactrab" name="fecnactrab" value="${registro_LM.fecnactrab}">
						<label class="lbl_Error" id="fecnactrab_error">${glosaError_LM.fecnactrab}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(12)</label>
						<label class="lbl_nombre" >Sexo trabajador</label>
						<input type="text"  maxlength="1" class="txt_campo" id="gentrabaj" name="gentrabaj" value="${registro_LM.gentrabaj}">
						<label class="lbl_Error" id="gentrabaj_error">${glosaError_LM.gentrabaj}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(13)</label>
						<label class="lbl_nombre" >N° de días licencia</label>
						<input type="text"  maxlength="3" class="txt_campo" id="numdiaslic" name="numdiaslic" value="${registro_LM.numdiaslic}">
						<label class="lbl_Error" id="numdiaslic_error">${glosaError_LM.numdiaslic}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(14)</label>
						<label class="lbl_nombre" >Licencia maternal suplementaria</label>
						<input type="text"  maxlength="1" class="txt_campo" id="licmatsupl" name="licmatsupl" value="${registro_LM.licmatsupl}">
						<label class="lbl_Error" id="licmatsupl_error">${glosaError_LM.licmatsupl}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(15)</label>
						<label class="lbl_nombre" >Fecha de nacimiento hijo</label>
						<c:choose>
							<c:when test="${registro_LM.fecnachijo==0}">
								<input type="text"  maxlength="8" class="txt_campo" id="fecnachijo" name="fecnachijo" value="">
							</c:when>
							<c:otherwise>
								<input type="text"  maxlength="8" class="txt_campo" id="fecnachijo" name="fecnachijo" value="${registro_LM.fecnachijo}">
							</c:otherwise>
						</c:choose>
						<label class="lbl_Error" id="fecnachijo_error">${glosaError_LM.fecnachijo}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(16)</label>
						<label class="lbl_nombre" >Rut hijo</label>
						<input type="text"  maxlength="10" class="txt_campo" id="ruthijo" name="ruthijo" value="${registro_LM.ruthijo}">
						<label class="lbl_Error" id="ruthijo_error">${glosaError_LM.ruthijo}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(17)</label>
						<label class="lbl_nombre" >Tipo de licencia (sección a)</label>
						<input type="text"  maxlength="1" class="txt_campo" id="tipolic" name="tipolic" value="${registro_LM.tipolic}">
						<label class="lbl_Error" id="tipolic_error">${glosaError_LM.tipolic}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(18)</label>
						<label class="lbl_nombre" >Recuperabilidad laboral</label>
						<input type="text"  maxlength="1" class="txt_campo" id="recuplabor" name="recuplabor" value="${registro_LM.recuplabor}">
						<label class="lbl_Error" id="recuplabor_error">${glosaError_LM.recuplabor}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(19)</label>
						<label class="lbl_nombre" >Inicio tramite de invalidez</label>
						<input type="text"  maxlength="1" class="txt_campo" id="iniinvalid" name="iniinvalid" value="${registro_LM.iniinvalid}">
						<label class="lbl_Error" id="iniinvalid_error">${glosaError_LM.iniinvalid}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(20)</label>
						<label class="lbl_nombre" >Año y mes de la concepción</label>
						<c:choose>
							<c:when test="${registro_LM.fecconcep==0}">
								<input type="text"  maxlength="6" class="txt_campo" id="fecconcep" name="fecconcep" value="">
							</c:when>
							<c:otherwise>
								<input type="text"  maxlength="8" class="txt_campo" id="fecnachijo" name="fecnachijo" value="${registro_LM.fecconcep}">
							</c:otherwise>
						</c:choose>
						<label class="lbl_Error" id="fecconcep_error" >${glosaError_LM.fecconcep}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(21)</label>
						<label class="lbl_nombre" >Tipo de reposo</label>
						<input type="text"  maxlength="1" class="txt_campo" id="tiporeposo" name="tiporeposo" value="${registro_LM.tiporeposo}">
						<label class="lbl_Error" id="tiporeposo_error">${glosaError_LM.tiporeposo}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(22)</label>
						<label class="lbl_nombre" >Jornada reposo</label>
						<input type="text"  maxlength="1" class="txt_campo" id="jorreposo" name="jorreposo" value="${registro_LM.jorreposo}">
						<label class="lbl_Error" id="jorreposo_error">${glosaError_LM.jorreposo}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(23)</label>
						<label class="lbl_nombre" >Lugar de reposo</label>
						<input type="text"  maxlength="1" class="txt_campo" id="lugreposo" name="lugreposo" value="${registro_LM.lugreposo}">
						<label class="lbl_Error" id="lugreposo_error">${glosaError_LM.lugreposo}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(24)</label>
						<label class="lbl_nombre" >Descripción especialidad profesional</label>
						<input type="text" maxlength="30" class="txt_campo" id="especialid" name="especialid" value="${registro_LM.especialid}">
						<label class="lbl_Error" id="especialid_error">${glosaError_LM.especialid}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(25)</label>
						<label class="lbl_nombre" >Tipo de profesional</label>
						<input type="text" maxlength="1" class="txt_campo" id="tipoprofes" name="tipoprofes" value="${registro_LM.tipoprofes}">
						<label class="lbl_Error" id="tipoprofes_error">${glosaError_LM.tipoprofes}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(26)</label>
						<label class="lbl_nombre" >Rut profesional médico</label>
						<input type="text" maxlength="10" class="txt_campo" id="rutprofes" name="rutprofes" value="${registro_LM.rutprofes}">
						<label class="lbl_Error" id="rutprofes_error">${glosaError_LM.rutprofes}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(27)</label>
						<label class="lbl_nombre" >Nombre profesional médico</label>
						<input type="text" maxlength="60" class="txt_campo" id="nomprofes" name="nomprofes" value="${registro_LM.nomprofes}">
						<label class="lbl_Error" id="nomprofes_error">${glosaError_LM.nomprofes}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(28)</label>
						<label class="lbl_nombre" >Licencia modificada</label>
						<input type="text" maxlength="1" class="txt_campo" id="licmodific" name="licmodific" value="${registro_LM.licmodific}">
						<label class="lbl_Error" id="licmodific_error">${glosaError_LM.licmodific}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(29)</label>
						<label class="lbl_nombre" >Código entidad autorizadora</label>
						<input type="text" maxlength="5" class="txt_campo" id="entautoriz" name="entautoriz" value="${registro_LM.entautoriz}">
						<label class="lbl_Error" id="entautoriz_error">${glosaError_LM.entautoriz}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(30)</label>
						<label class="lbl_nombre" >Tipo de licencia médica resuelto</label>
						<input type="text" maxlength="1" class="txt_campo" id="tipolmresu" name="tipolmresu" value="${registro_LM.tipolmresu}">
						<label class="lbl_Error" id="tipolmresu_error">${glosaError_LM.tipolmresu}</label>
					</div>
				</div>
				<div class="visorForm">
					<div class="campoForm">
						<label class="lbl_numero" >(31)</label>
						<label class="lbl_nombre" >Nro. de días de incapacidad autorizados</label>
						<input type="text" maxlength="3" class="txt_campo" id="ndiasincap" name="ndiasincap" value="${registro_LM.ndiasincap}">
						<label class="lbl_Error" id="ndiasincap_error">${glosaError_LM.ndiasincap}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(32)</label>
						<label class="lbl_nombre" >Código diagnóstico resuelto</label>
						<input type="text" maxlength="5" class="txt_campo" id="diagresuel" name="diagresuel" value="${registro_LM.diagresuel}">
						<label class="lbl_Error" id="diagresuel_error">${glosaError_LM.diagresuel}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(33)</label>
						<label class="lbl_nombre" >Periodo</label>
						<input type="text" maxlength="1" class="txt_campo" id="periodo" name="periodo" value="${registro_LM.periodo}">
						<label class="lbl_Error" id="periodo_error">${glosaError_LM.periodo}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(34)</label>
						<label class="lbl_nombre" >N° días previos autorizados</label>
						<input type="text" maxlength="4" class="txt_campo" id="ndiasprev" name="ndiasprev" value="${registro_LM.ndiasprev}">
						<label class="lbl_Error" id="ndiasprev_error">${glosaError_LM.ndiasprev}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(35)</label>
						<label class="lbl_nombre" >Estado de la resolución</label>
						<input type="text" maxlength="1" class="txt_campo" id="estadoreso" name="estadoreso" value="${registro_LM.estadoreso}">
						<label class="lbl_Error" id="estadoreso_error">${glosaError_LM.estadoreso}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(36)</label>
						<label class="lbl_nombre" >Tipo de resolución</label>
						<input type="text" maxlength="1" class="txt_campo" id="tiporesolu" name="tiporesolu" value="${registro_LM.tiporesolu}">
						<label class="lbl_Error" id="tiporesolu_error">${glosaError_LM.tiporesolu}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(37)</label>
						<label class="lbl_nombre" >Redictamen</label>
						<input type="text" maxlength="1" class="txt_campo" id="redictamen" name="redictamen" value="${registro_LM.redictamen}">
						<label class="lbl_Error" id="redictamen_error">${glosaError_LM.redictamen}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(38)</label>
						<label class="lbl_nombre" >Causa de rechazo</label>
						<input type="text" maxlength="1" class="txt_campo" id="causarecha" name="causarecha" value="${registro_LM.causarecha}">
						<label class="lbl_Error" id="causarecha_error">${glosaError_LM.causarecha}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(39)</label>
						<label class="lbl_nombre" >Tipo de reposo autorizado</label>
						<input type="text" maxlength="1" class="txt_campo" id="tiporepoau" name="tiporepoau" value="${registro_LM.tiporepoau}">
						<label class="lbl_Error" id="tiporepoau_error">${glosaError_LM.tiporepoau}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(40)</label>
						<label class="lbl_nombre" >Jornada de reposo autorizada</label>
						<input type="text" maxlength="1" class="txt_campo" id="jorrepoaut" name="jorrepoaut" value="${registro_LM.jorrepoaut}">
						<label class="lbl_Error" id="jorrepoaut_error">${glosaError_LM.jorrepoaut}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(41)</label>
						<label class="lbl_nombre" >Derecho a subsidio</label>
						<input type="text" maxlength="1" class="txt_campo" id="desubsidio" name="desubsidio" value="${registro_LM.desubsidio}">
						<label class="lbl_Error" id="desubsidio_error">${glosaError_LM.desubsidio}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(42)</label>
						<label class="lbl_nombre" >Fecha de recepción entidad autorizadora</label>
						<input type="text" maxlength="8" class="txt_campo" id="fecreceent" name="fecreceent" value="${registro_LM.fecreceent}">
						<label class="lbl_Error" id="fecreceent_error">${glosaError_LM.fecreceent}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(43)</label>
						<label class="lbl_nombre" >Fecha de resolución por entidad autorizadora</label>
						<input type="text" maxlength="8" class="txt_campo" id="fecresoent" name="fecresoent" value="${registro_LM.fecresoent}">
						<label class="lbl_Error" id="fecresoent_error">${glosaError_LM.fecresoent}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(44)</label>
						<label class="lbl_nombre" >Rut contraloría médica</label>
						<input type="text" maxlength="10" class="txt_campo" id="rutcontral" name="rutcontral" value="${registro_LM.rutcontral}">
						<label class="lbl_Error" id="rutcontral_error">${glosaError_LM.rutcontral}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(45)</label>
						<label class="lbl_nombre" >Rut empleador</label>
						<input type="text" maxlength="10" class="txt_campo" id="rutemplead" name="rutemplead" value="${registro_LM.rutemplead}">
						<label class="lbl_Error" id="rutemplead_error">${glosaError_LM.rutemplead}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(46)</label>
						<label class="lbl_nombre" >Fecha recepción licencia por el empleador</label>
						<input type="text" maxlength="8" class="txt_campo" id="fecreceemp" name="fecreceemp" value="${registro_LM.fecreceemp}">
						<label class="lbl_Error" id="fecreceemp_error">${glosaError_LM.fecreceemp}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(47)</label>
						<label class="lbl_nombre" >Región empleador</label>
						<input type="text" maxlength="2" class="txt_campo" id="regionempl" name="regionempl" value="${registro_LM.regionempl}">
						<label class="lbl_Error" id="regionempl_error">${glosaError_LM.regionempl}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(48)</label>
						<label class="lbl_nombre" >Código comuna empleador</label>
						<input type="text" maxlength="5" class="txt_campo" id="comunaempl" name="comunaempl" value="${registro_LM.comunaempl}">
						<label class="lbl_Error" id="comunaempl_error">${glosaError_LM.comunaempl}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(49)</label>
						<label class="lbl_nombre" >Actividad laboral del trabajador</label>
						<input type="text" maxlength="2" class="txt_campo" id="actlabtrab" name="actlabtrab" value="${registro_LM.actlabtrab}">
						<label class="lbl_Error" id="actlabtrab_error">${glosaError_LM.actlabtrab}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(50)</label>
						<label class="lbl_nombre" >Ocupación del trabajador</label>
						<input type="text" maxlength="2" class="txt_campo" id="ocupactrab" name="ocupactrab" value="${registro_LM.ocupactrab}">
						<label class="lbl_Error" id="ocupactrab_error">${glosaError_LM.ocupactrab}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(51)</label>
						<label class="lbl_nombre" >Fecha recepción licencia por la entidad pagadora</label>
						<input type="text" maxlength="8" class="txt_campo" id="fecrecepag" name="fecrecepag" value="${registro_LM.fecrecepag}">
						<label class="lbl_Error" id="fecrecepag_error">${glosaError_LM.fecrecepag}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(52)</label>
						<label class="lbl_nombre" >Tipo de régimen previsional</label>
						<input type="text" maxlength="1" class="txt_campo" id="tipregprev" name="tipregprev" value="${registro_LM.tipregprev}">
						<label class="lbl_Error" id="tipregprev_error">${glosaError_LM.tipregprev}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(53)</label>
						<label class="lbl_nombre" >Calidad del trabajador</label>
						<input type="text" maxlength="1" class="txt_campo" id="calitrabaj" name="calitrabaj" value="${registro_LM.calitrabaj}">
						<label class="lbl_Error" id="calitrabaj_error">${glosaError_LM.calitrabaj}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(54)</label>
						<label class="lbl_nombre" >Tipo entidad pagadora del subsidio</label>
						<input type="text" maxlength="1" class="txt_campo" id="tipoentpag" name="tipoentpag" value="${registro_LM.tipoentpag}">
						<label class="lbl_Error" id="tipoentpag_error">${glosaError_LM.tipoentpag}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(55)</label>
						<label class="lbl_nombre" >Fecha primera afiliación entidad previsional</label>
						<input type="text" maxlength="8" class="txt_campo" id="fecpriafil" name="fecpriafil" value="${registro_LM.fecpriafil}">
						<label class="lbl_Error" id="fecpriafil_error">${glosaError_LM.fecpriafil}</label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(56)</label>
						<label class="lbl_nombre" >Fecha contrato de trabajo</label>
						<input type="text" maxlength="8" class="txt_campo" id="feccontrab" name="feccontrab" value="${registro_LM.feccontrab}">
						<label class="lbl_Error" id="feccontrab_error">${glosaError_LM.feccontrab}</label>
					</div>
				</div>
			</form>
		    </c:otherwise>
		</c:choose>
		
		
		