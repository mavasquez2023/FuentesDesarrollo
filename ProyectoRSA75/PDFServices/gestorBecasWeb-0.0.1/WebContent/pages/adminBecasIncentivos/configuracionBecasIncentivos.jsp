<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<script language="javascript">var jsContextRoot = '<c:out value="${contextRoot}"/>';</script>

<link href="${contextRoot}/scripts/jscalendar/calendario.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${contextRoot}/scripts/jscalendar/calendar.js"></script>
<script type="text/javascript" src="${contextRoot}/scripts/jscalendar/lang/calendar-es.js"></script>
<script type="text/javascript" src="${contextRoot}/scripts/calendario.js"></script>
<script type="text/javascript">
$(document).ready(function(){
 	 $('#open').click(function(){
 		limpiaBeneficio();
		$('#popup').fadeIn('slow');
		
	});
	
	$('#close').click(function(){
		$('#popup').fadeOut('slow');
		
	});
	
	$('#closeAreaConcepto').click(function(){
		$('#popupAreaConcepto').fadeOut('slow');
		
	});
	
	$("#montoBeneficio").keyup(function () {
        var valActual = $(this).val();
        var nuevoValor = formato(valActual,'.',3,2);
        $(this).val(nuevoValor);
    });
	$("#notaBeneficioDesde").keyup(function () {
        var valActual = $(this).val();
        var nuevoValor = formato(valActual,'.',1,0);
        $(this).val(nuevoValor);
        var valorPorcen = parseInt((nuevoValor*100)/7);
        $("#porcentajeBeneficioDesde").val(valorPorcen);
    });
	$("#notaBeneficioHasta").keyup(function () {
        var valActual = $(this).val();
        var nuevoValor = formato(valActual,'.',1,0);
        $(this).val(nuevoValor);
        var valorPorcen = parseInt((nuevoValor*100)/7);
        $("#porcentajeBeneficioHasta").val(valorPorcen);
    });
});
</script>
<script language="javascript">
		function actualizaCheckCurso(idNivelEducacional, paso) {
			getCheckList('curso', idNivelEducacional, paso);
			getEvaluacionNivelEdu('evaluacion', idNivelEducacional, paso);
			eliminaBenefico('-1');
		}
		function actualizaCheckBeca(isBeca, paso) {
			getCheckBecaList('beca', isBeca, paso);
		}
		function mustraPlazoEntrega(tipoBeca, paso) {
			getTipoEntrega('tipoBeca', tipoBeca, paso);
			if(tipoBeca == 'ICP'){
				$("#beneficioIncentivo").css("display","");
				$("#beneficioDinero").css("display","none")
				$("#tab_6").css("display","none");
				$('#divLabelPlazoSolicitud').text('Plazo Solicitudes');
			
			}else if(tipoBeca == 'ISP'){
				$("#beneficioIncentivo").css("display","");
				$("#beneficioDinero").css("display","none")
				$("#tab_6").css("display","none");
				$('#divLabelPlazoSolicitud').text('Plazo Solicitudes');
			}else{
				$("#beneficioDinero").css("display","");
				$("#beneficioIncentivo").css("display","none")
				$("#tab_6").css("display","");
				$('#divLabelPlazoSolicitud').text('Plazo Solicitudes y Entrega');
				
			}
			$("#open").css("display","block")
		}
		function agregaBeneficio() {
			
			var opcTipoBeca = $('#opcTipoBeca').val();
				
			var opc = $('#opcBeneficio').val();
			var valorOpc = $("#opcBeneficio option:selected").html();
			
			var nDesde =  jQuery.trim($('#notaBeneficioDesde').val());
			var nHasta =  jQuery.trim($('#notaBeneficioHasta').val());
			
			var poDesde = jQuery.trim($('#porcentajeBeneficioDesde').val());
			var poHasta = jQuery.trim($('#porcentajeBeneficioHasta').val());
			
			var puDesde = jQuery.trim($('#puntajeBeneficioDesde').val());
			var puHasta = jQuery.trim($('#puntajeBeneficioHasta').val());
			
			var monto = $('#montoBeneficio').val();
			
			var incentivo = $('#incentivo').val();
			
			 var nivelEducacional = $('#opcNivelEducacional').val();
			
			var validacion = validaEgregaBeneficio(opc,nDesde,nHasta,poDesde,poHasta,puDesde,puHasta,monto,incentivo);
			if(validacion == false){
				return false;
			}
			agregaBeneficioList('beneficio',opc,valorOpc,nDesde,nHasta,poDesde,poHasta,puDesde,puHasta,monto,incentivo,nivelEducacional);
			
			var opcTipoBeca = $('#opcTipoBeca').val();
			if(opcTipoBeca == 'ICP'){
				$("#open").css("display","none")
			}
			
			limpiaBeneficio();
			$('#popup').fadeOut('slow');
			
		}
		
		function validaEgregaBeneficio(opc,nDesde,nHasta,poDesde,poHasta,puDesde,puHasta,monto,incentivo){
			var msn ="";
			
			if(opc == "-1"){
				msn="* Debe seleccionar al menos un Nombre Beneficio \n";
			}
			if(nDesde == ""){
				msn=msn+"* Debe ingresar la Nota Desde\n";
			}
			if(nHasta == ""){
				msn=msn+"* Debe ingresar la Nota Hasta \n";
			}
			if(poDesde == ""){
				msn=msn+"*  Debe ingresar el Porcentaje Desde \n";
			}
			if(poHasta == ""){
				msn=msn+"* Debe ingresar el Porcentaje Hasta \n";
			}
			if(puDesde == "" && !($('#trPuntaje').is(':hidden'))){
				msn=msn+"* Debe ingresar el Puntaje Desde \n";
			}
			if(puHasta == "" && !($('#trPuntaje').is(':hidden'))){
				msn=msn+"* Debe ingresar el Puntaje Hasta \n";
			}
			var opcTipoBeca = $('#opcTipoBeca').val();
			if(opcTipoBeca == 'DEI' && monto == ""){
				msn=msn+"* Debe ingresar el Monto \n";
			}
			if(opcTipoBeca == 'ISP' && incentivo == ""){
				msn=msn+"* Debe ingresar un Incentivo \n";
			}
			
			var nDesdeInt = parseInt(nDesde);
			var nHastaInt = parseInt(nHasta);
			if(nDesdeInt > nHastaInt){
				msn="* La nota desde debe ser menor o igual al nota hasta  \n";
			}
			if(!($('#trNota').is(':hidden')) && nDesdeInt < 1 || nDesdeInt > 7){
				msn=msn+"* La nota desde es de un rango de 1 a 7 \n";
			}
			if(!($('#trNota').is(':hidden')) && nHastaInt < 1 || nHastaInt > 7){
				msn=msn+"* La nota hasta es de un rango de 1 a 7 \n";
			}
			
			var poDesdeInt = parseInt(poDesde);
			var poHastaInt = parseInt(poHasta);
			if(poDesdeInt > poHastaInt){
				msn=msn+"* El porcentaje desde debe ser menor o igual al porcentaje hasta \n";
			}
			
			if(!($('#trPorcentaje').is(':hidden')) && poDesdeInt < 1 || poDesdeInt > 100){
				msn=msn+"* El porcentaje desde es de un rango de 1% a 100% \n";
			}
			if(!($('#trPorcentaje').is(':hidden')) && poHastaInt < 1 || poHastaInt > 100){
				msn=msn+"* El porcentaje hasta es de un rango de 1% a 100% \n";
			}
			
			var puDesdeInt = parseInt(puDesde);
			var puHastaInt = parseInt(puHasta);
			if(puDesdeInt > puHastaInt){
				msn=msn+"* El punteje desde debe ser manor a igual al puntaje hasta  \n";
			}
			
			var puntMaxiomoPSU =$('#puntajeMaximo').val();
			if((!($('#trPuntaje').is(':hidden'))) && puDesdeInt < 0 || puDesdeInt > puntMaxiomoPSU){
				msn=msn+"* El punteje desde es de un rango de 1 a "+ puntMaxiomoPSU + "\n";
			}
			if((!($('#trPuntaje').is(':hidden'))) && puHastaInt < 0 || puHastaInt > puntMaxiomoPSU){
				msn=msn+"* El punteje hasta es de un rango de 1 a "+ puntMaxiomoPSU + "\n";
			}
			
			if(msn != ""){
				alert(msn);
				return false;
			}else{
				return true;	
			}
			
			
		}
		function limpiaBeneficio(){
			
			$('#opcBeneficio').val('-1');
			
			if ($('#trNota').is(':hidden')){
				$('#notaBeneficioDesde').val('0');
				$('#notaBeneficioHasta').val('0');
					
			}else{
				$('#notaBeneficioDesde').val('');
				$('#notaBeneficioHasta').val('');				
			}
			
			if ($('#trPorcentaje').is(':hidden')){
				$('#porcentajeBeneficioDesde').val('0');
				$('#porcentajeBeneficioHasta').val('0');
			}else{
				$('#porcentajeBeneficioDesde').val('');
				$('#porcentajeBeneficioHasta').val('');
			}
			
			if ($('#trPuntaje').is(':hidden')){
			
				$('#puntajeBeneficioDesde').val('0');
				$('#puntajeBeneficioHasta').val('0');
			}else{
				$('#puntajeBeneficioDesde').val('');
				$('#puntajeBeneficioHasta').val('');				
			}
			
			$('#montoBeneficio').val('');
			$('#incentivo').val('');
		}
		function muestraBeneficio(id){
			 var nivelEducacional = $('#opcNivelEducacional').val();
			 var opcTipoBeca = $('#opcTipoBeca').val();
			cargaDatosBeneficio('muestraBeneficio',id,nivelEducacional,opcTipoBeca);
			$('#popup').fadeIn('slow');
		}
		function eliminaBenefico(id){
			agregaBeneficioList('eliminaBenefico',id);
			
			agregaAreaConceptoList('agregaAreaConceptoList','','');
			
			var opcTipoBeca = $('#opcTipoBeca').val();
			if(opcTipoBeca == 'ICP'){
				$("#open").css("display","block")
			}
		}
		
		function muestraCalendar(id1, id2, showMore)
		{
			var f = new Date();
			
			var formato = "%d-%m-%Y";
			var mes = f.getMonth() +1;
			var anno = f.getFullYear();
			showCalendar(id1, id2, formato, showMore, mes, anno);
		}
		function limpiaFecha(idCajaFecha)
		{
			document.getElementById(idCajaFecha).value = "";
		}
		
		function limpiaTextoCarta(){	
			document.forms[0].textoCarta.value = '';
			document.forms[0].caracteres.value = '';
		}
		function cuenta(){
			document.forms[0].caracteres.value= (500 - document.forms[0].textoCarta.value.length);
		}
		
		function updatePage(){
			if($('#opcTipoBeca').val() != -1){
				mustraPlazoEntrega($('#opcTipoBeca').val());
			}
			if($('#opcNivelEducacional').val() != -1){
				var nivelEducacion =$('#opcNivelEducacional').val();
				getEvaluacionNivelEdu('evaluacion', nivelEducacion, '');
				
				var opc = $('#opcBeneficio').val();
				var valorOpc = $("#opcBeneficio option:selected").html();
				
				var nDesde =  jQuery.trim($('#notaBeneficioDesde').val());
				var nHasta =  jQuery.trim($('#notaBeneficioHasta').val());
				
				var poDesde = jQuery.trim($('#porcentajeBeneficioDesde').val());
				var poHasta = jQuery.trim($('#porcentajeBeneficioHasta').val());
				
				var puDesde = jQuery.trim($('#puntajeBeneficioDesde').val());
				var puHasta = jQuery.trim($('#puntajeBeneficioHasta').val());
				
				var monto = $('#montoBeneficio').val();
				
				var incentivo = $('#incentivo').val();
				
				var nivelEducacional = $('#opcNivelEducacional').val();
				 
				agregaBeneficioList('beneficio',opc,valorOpc,nDesde,nHasta,poDesde,poHasta,puDesde,puHasta,monto,incentivo,nivelEducacional);
				
				agregaAreaConceptoList('agregaAreaConceptoList','','');
				
				var	cantPremiosInicioInt = parseInt($('#cantPremiosInicio').val());
				var tipoBeca = $('#opcTipoBeca').val();
				
				if(cantPremiosInicioInt > 0 && tipoBeca != 'DEI' && tipoBeca != 'ISP'){
					$("#open").css("display","none");
				}else{
					$("#open").css("display","block");
				}
			
			}
			
		}
		function validaForm(){
			
			var cursoValido=false;
			var becasValido=false;			
			var docValido = false
			var segmentoValido = false;
			var areaConceptoValido = true;
			
			var idMaxCurso = $('#idMaxCurso').val();
			if(idMaxCurso != ""){
				var	idMaxCursoInt =  parseInt($('#idMaxCurso').val());
				if(idMaxCursoInt > 0){
					for(i= 1 ;i <= idMaxCursoInt ; i++){
						 if($('#curso'+i).is(':checked')) {  
							 cursoValido=true;
						  }
					}
				}else{
					cursoValido=true;
				}
			}
			var becaCompatibleActivado =$("input[name='compatibleBeca']:checked").val(); 
			if(becaCompatibleActivado == 1){
				var	idMaxBecaInt =  parseInt($('#idMaxBeca').val())+1;
				if(idMaxBecaInt > 0){
					for(i= 0 ;i <= idMaxBecaInt ; i++){
						 if($('#beca'+i).is(':checked')) {  
							 becasValido=true;
						  }
					}
				}else{
					becasValido=true;
				}
			}else{
				becasValido=true;
			}
			
			var	idMaxSegmento =  parseInt($('#idMaxSegmento').val());
			if(idMaxSegmento > 0){
				for(i= 0 ;i <= idMaxSegmento ; i++){
					 if($('#segmento'+i).is(':checked')) {  
						 segmentoValido=true;
					  }
				}
			}else{
				 segmentoValido=true;
			}
			
			if($('#opcTipoBeca').val() == 'DEI'){
				var	idMaxDetBeneficioAC =  parseInt($('#idMaxDetBeneficioAC').val());
				for(i= 0 ;i <= idMaxDetBeneficioAC ; i++){
					 if($('#hArea'+i).val() == 0) {  
						 areaConceptoValido=false;
						 break;
					  }
				}
			}
		
			var msn ="";
			if($('#opcTipoBeca').val() == "-1"){
				msn="* Debe seleccionar al menos un tipo de Beca \n";
			}
			if(jQuery.trim($('#nombreBeca').val()) == ""){
				msn=msn+"* Debe ingresar el Nombre Beca \n";
			}
			if($('#opcNivelEducacional').val() =="-1"){
				msn=msn+"* Debe seleccionar al menos un Nivel Educacional \n";
			}
			if(cursoValido == false){
				msn=msn+"* Debe marcar un curso \n";
			}
			if(jQuery.trim($('#fechaInicioSolicitud').val()) == ""){
				msn=msn+"* Debe seleccionar una fecha de inico Solicitud \n";
			}
			if(jQuery.trim($('#fechaFinSolicitud').val()) == ""){
				msn=msn+"* Debe seleccionar una fecha de fin Solicitud \n";
			}
			var fechaInSolAno = $('#fechaInicioSolicitud').val().substring(6,10);
			var fechaInSolMes = $('#fechaInicioSolicitud').val().substring(3,5);
			var fechaInSolDia = $('#fechaInicioSolicitud').val().substring(0,2);
			
			var fechaInSol = fechaInSolAno+fechaInSolMes+fechaInSolDia;
			var fechaInSolInt = parseInt(fechaInSol);
			
			var fechaFinSolAno = $('#fechaFinSolicitud').val().substring(6,10);
			var fechaFinSolMes = $('#fechaFinSolicitud').val().substring(3,5);
			var fechaFinSolDia = $('#fechaFinSolicitud').val().substring(0,2);
			
			var fechaFinSol = fechaFinSolAno+fechaFinSolMes+fechaFinSolDia;
			var fechaFinSolInt = parseInt(fechaFinSol);
			
			if(fechaInSolInt > fechaFinSolInt){
				msn=msn+"* La fecha inicio solicitud no puede ser mayor a la fecha fin solicitud \n";
			}
						
			var opcTipoBeca = $('#opcTipoBeca').val();
			if(opcTipoBeca != 'DEI' && jQuery.trim($('#fechaInicioEntrega').val()) == "" ){
				msn=msn+"* Debe seleccionar una fecha inicio Entrega \n";
			}
			if(opcTipoBeca != 'DEI' && jQuery.trim($('#fechaFinEntrega').val()) == ""){
				msn=msn+"* Debe seleccionar una fecha fin Entrega \n";
			}
			
			if(opcTipoBeca != 'DEI'){
					
				var fechaInEntregaAno = $('#fechaInicioEntrega').val().substring(6,10);
				var fechaInEntregaMes = $('#fechaInicioEntrega').val().substring(3,5);
				var fechaInEntregaDia = $('#fechaInicioEntrega').val().substring(0,2);
				
				var fechaInEntrega = fechaInEntregaAno+fechaInEntregaMes+fechaInEntregaDia;
				var fechaInEntregaInt = parseInt(fechaInEntrega);
				
				var fechaFinEntregaAno = $('#fechaFinEntrega').val().substring(6,10);
				var fechaFinEntregaMes = $('#fechaFinEntrega').val().substring(3,5);
				var fechaFinEntregaDia = $('#fechaFinEntrega').val().substring(0,2);
				
				var fechaFinEntrega = fechaFinEntregaAno+fechaFinEntregaMes+fechaFinEntregaDia;
				var fechaFinEntregaInt = parseInt(fechaFinEntrega);
				
				if(fechaInEntregaInt > fechaFinEntregaInt){
					msn=msn+"* La fecha inicio entrega no puede ser mayor a la fecha fin entrega \n";
				}
				if(fechaInSolInt > fechaInEntregaInt){
					msn=msn+"* La fecha inicio solicitud no puede ser mayor a la fecha inicio entrega \n";
				}
				if(fechaFinSolInt > fechaFinEntregaInt){
					msn=msn+"* La fecha fin solicitud no puede ser mayor a la fecha fin entrega \n";
				}
			}
			if(becasValido == false){
				msn=msn+"* Debe seleccionar a lo menos una beca compatible \n";
			}
			if(segmentoValido == false){
				msn=msn+"* Debe seleccionar al menos un segmento \n";
			}
			var	idMaxDetBeneficio =  parseInt($('#idMaxDetBeneficio').val());
			if(parseInt(idMaxDetBeneficio) == 0){
				msn=msn+"* Debe agregar al menos un beneficio \n";
			}
			if(jQuery.trim($('#textoCarta').val()) == ""){
				msn=msn+"* Debe agregar el texto de la carta \n";
			}
			if(parseInt($('#caracteres').val()) < 1){
				msn=msn+"* El texto de la carta permite 500 caracteres como maximo\n";
			}
			if(areaConceptoValido == false){
				msn=msn+"* Debe agregar area y conceptos \n";
			}
			areaConceptoValido
			
			if(msn != ""){
				alert(msn);
			}else{
				document.forms[0].submit();	
			}
		}
	function formato(valor,simb,patron,prox) {
		    var nums = new Array();
		    //var simb = "."; //Éste es el separador
		    valor = valor.toString();
		    valor = valor.replace(/\D/g, "");   //Ésta expresión regular solo permitira ingresar números
		    nums = valor.split(""); //Se vacia el valor en un arreglo
		    var long = nums.length - 1; // Se saca la longitud del arreglo
		    //var patron = 3; //Indica cada cuanto se ponen las comas
		    //var prox = 2; // Indica en que lugar se debe insertar la siguiente coma
		    var res = "";
		 
		    while (long > prox) {
		        nums.splice((long - prox),0,simb); //Se agrega la coma
		        prox += patron; //Se incrementa la posición próxima para colocar la coma
		    }
		 
		    for (var i = 0; i <= nums.length-1; i++) {
		        res += nums[i]; //Se crea la nueva cadena para devolver el valor formateado
		    }
		 
		    return res;
		}
	function actualizaAreaConceptoBySegmento(id) {
		if($('#opcTipoBeca').val() == 'DEI'){
			if($('#segmento'+id).is(':checked')) {
				agregaAreaConceptoList('agregaAreaConceptoList',$('#segmento'+id).val(),'true');
			}else{
				agregaAreaConceptoList('agregaAreaConceptoList',$('#segmento'+id).val(),'false');
			}
		}
	}
	function muestraAreaConcepto(idAreaConcepto){
		if($('#opcTipoBeca').val() == 'DEI'){
			muestraAreaConceptoById('muestraAreaConcepto',idAreaConcepto);
			$('#popupAreaConcepto').fadeIn('slow');
		}
	}
	function guardaAreaConcepto(){
		var idAreaConcepto = $('#idAreaConcepto').val();
		var area = jQuery.trim($('#area').val());
		var concepto = jQuery.trim($('#concepto').val());


		
		var msn ="";
		if(area == ""){
			msn=msn+"* Debe ingresar el Área \n";
		}
		if(concepto == ""){
			msn=msn+"* Debe ingresar el Concepto \n";
		}
		if(!isNumero(area)){
			msn=msn+"* El Área debe ser numerico \n";
		}
		if(!isNumero(concepto)){
			msn=msn+"* El Concepto debe ser numerico \n";
		}
		
		if(msn == ""){
			registrarAreaConcepto('registraAreaConcepto',idAreaConcepto,area,concepto);
			$('#popupAreaConcepto').fadeOut('slow');	
		}else{
			alert(msn);
		}
	}
	function isNumero(str) {
		  var flag=true;
		  var i=0;
		  while (i<str.length && flag) {
		    flag = (str.charAt(i)>=0 && str.charAt(i)<=9)
		    i++;    
		  }
		  return flag;
		}
	
</script>
<style type="text/css"> 
.listBlock {float: left;}
#sortable1, #sortable2 {list-style-type: none; margin: 0; padding: 0; margin-right: 10px; background: #eee; padding: 5px; width: 200px; border: 1px solid black;font-size: 11px;font-style: normal;}
#sortable1 li, #sortable2 li{ cursor: move; margin: 5px; padding: 5px; font-size: 1.2em; width: 180px;  background: none; background-color: white; font-size: 11px;font-style: normal;}
</style> 
<html:form 
	action="${webPrePath}/adminBecasIncentivos/configuracionBecasIncentivos" 
	styleId="dataForm">
	<body onload="javascript:updatePage()">
	<html:hidden property="_cmd" value="guardar" />
	<html:hidden property="idBeca" name="ConfiguracionBecasIncentivosForm" />
	<input type="hidden" name="puntajeMaximo" id="puntajeMaximo" value="${puntajeMaximo}"/>
	<input type="hidden" name="cantPremiosInicio" id="cantPremiosInicio" value="${cantPremiosInicio}"/>
	<div class="panelzone">
	<!-- tabs de seleccion -->
	<input type="hidden" id="configuraFilas" value="a" />
	<ul class="tabs">
	       <li><a href="#tab_1" class="tab" name="tab_1"><bean:message key="label.administracion.config.beca.tipoBeca"/></a></li>
	       <li><a href="#tab_2" class="tab" name="tab_2"><bean:message key="label.administracion.config.beca.plazos"/></a></li>
	       <li><a href="#tab_3" class="tab" name="tab_3"><bean:message key="label.administracion.config.beca.beneficios"/></a></li>
	       <li><a href="#tab_4" class="tab" name="tab_4"><bean:message key="label.administracion.config.beca.becasCompatibles"/></a></li>
	       <li><a href="#tab_5" class="tab" name="tab_5"><bean:message key="label.administracion.config.beca.otros"/></a></li>
	       <li><a href="#tab_6" class="tab" name="tab_6" id="tab_6"><bean:message key="label.administracion.config.beca.areaConcepto"/></a></li>
	        
	</ul>
	
	<div id='tab_1' class="pane" style="display: none;">
		<table width="100%">
		<tr>
			<th colspan="2"><bean:message key="label.administracion.configTipoBeca" /></th>
		</tr>
		<tr>
			<td width="20%" align="left">
				<strong><bean:message key="label.administracion.config.beca.tipoBeca" /> </strong>
			</td>
			<td>
				<html:select property="opcTipoBeca" styleId="opcTipoBeca" onchange="javascript:mustraPlazoEntrega(this.value, '')">
				 			<option value="-1"><bean:message key="label.common.seleccione.text"/></option>
				 			<logic:present name="ConfiguracionBecasIncentivosForm" property="tipoBecaList">
					   			<html:optionsCollection name="ConfiguracionBecasIncentivosForm" property="tipoBecaList" />
					   		</logic:present>
				</html:select>
				
				
			</td>
		</tr>
		<tr id="idPrueba">
			<td width="20%">
				<strong><bean:message key="label.administracion.config.beca.nombreBeca" /> </strong>
			</td>
			<td>
				<html:text property="nombreBeca" size="40" styleId="nombreBeca"></html:text>
			</td>
		</tr>
		<tr>
			<td width="20%">
				<strong><bean:message key="label.administracion.config.beca.nivelEducacional" /> </strong>
			</td>
			<td>
			 	<html:select property="opcNivelEducacional" styleId="opcNivelEducacional" onchange="javascript:actualizaCheckCurso(this.value, '')">
				 			<option value="-1"><bean:message key="label.common.seleccione.text"/></option>
							<logic:present name="ConfiguracionBecasIncentivosForm" property="nivelEducacionalList">
					   			<html:optionsCollection name="ConfiguracionBecasIncentivosForm" property="nivelEducacionalList" />
					   		</logic:present>
				</html:select>
				
			</td>
		</tr>
		<tr>
			<td colspan="2">
					<strong>
						<bean:message key="label.administracion.config.beca.curso" />
					</strong>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;
			</td>
			<td align="left">
					
								 
				 <div id="cursoid">
					<logic:notEmpty name="ConfiguracionBecasIncentivosForm" property="cursos">
						<logic:iterate id="cursosList" name="ConfiguracionBecasIncentivosForm" property="cursos">
								<logic:notEqual name="cursosList"  property="idCurso" value="9">
									<logic:equal name="cursosList"  property="seleccionado" value="true">
										<input type="checkbox" checked="checked" id="curso<bean:write name="cursosList" property="idCurso"/>" name="curso<bean:write name="cursosList" property="idCurso"/>" value="<bean:write name="cursosList" property="idCurso"/>"/>
										<bean:write name="cursosList" property="curso"/></br>
									</logic:equal>
									<logic:equal name="cursosList"  property="seleccionado" value="false">
										<input type="checkbox" id="curso<bean:write name="cursosList" property="idCurso"/>" name="curso<bean:write name="cursosList" property="idCurso"/>" value="<bean:write name="cursosList" property="idCurso"/>"/>
										<bean:write name="cursosList" property="curso"/></br>
									</logic:equal>
								</logic:notEqual>
						</logic:iterate>
					</logic:notEmpty>
				 </div>
				 <input type="hidden" name="idMaxCurso" id="idMaxCurso" value="<bean:write name="idMaxCurso"/>"/>
			</td>
		</tr>
		</table>
	</div>
	
	<div id='tab_2' class="pane">
		<!-- Seccion Plazos -->
		<table width="100%">
		<tr>
			<th colspan="2"><bean:message key="label.administracion.configPazos" />
			</th>
		</tr>
		<tr>
			<td colspan="2">
					<strong><div id="divLabelPlazoSolicitud">
								<bean:message key="label.administracion.config.beca.plazoSolicitudes"/>
							</div>
					</strong>
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">
				<strong><bean:message key="label.administracion.config.beca.fechaInicio" /> </strong>
			</td>
			<td align="left">
			<html:text property="fechaInicioSolicitud" styleId="fechaInicioSolicitud" readonly="true"></html:text>
			
			<a href="#" id="idFecha">
				<img src="${contextRoot}/images/ico_calendario.gif" width="11" height="10" border="0"
				     onClick="limpiaFecha('fechaInicioSolicitud');muestraCalendar('fechaInicioSolicitud', 'idFecha', true);return false;"/>
			</a>
				 </a>
					<a href="#" title="Limpia Fecha">
				<img src="${contextRoot}/images/icono_basurero.gif"  width="16" height="16" border="0" onClick="javascript:limpiaFecha('fechaInicioSolicitud')" title="Limpia Fecha"/>
			</a>
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">
				<strong><bean:message key="label.administracion.config.beca.fechaFin" /> </strong>
			</td>
			<td align="left">
				<html:text property="fechaFinSolicitud" styleId="fechaFinSolicitud" readonly="true"></html:text>
				<a href="#" id="idFecha">
					<img src="${contextRoot}/images/ico_calendario.gif" width="11" height="10" border="0"
					     onClick="limpiaFecha('fechaFinSolicitud');muestraCalendar('fechaFinSolicitud', 'idFecha', true);return false;"/>
				</a>
					<a href="#" title="Limpia Fecha">
				<img src="${contextRoot}/images/icono_basurero.gif"  width="16" height="16" border="0" onClick="javascript:limpiaFecha('fechaFinSolicitud')" title="Limpia Fecha"/>
			</a>
				
			</td>
		</tr>
		</table>
		<div id='divPlazoEntrega'>
			<table width="100%">
					<tr>
						<td colspan="2">
							<strong><bean:message key="label.administracion.config.beca.plazoEntrga" /> </strong>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<strong><bean:message key="label.administracion.config.beca.fechaInicio" /> </strong>
						</td>
						<td align="left">
						<html:text property="fechaInicioEntrega" styleId="fechaInicioEntrega" readonly="true"></html:text>
						<a href="#" id="idFecha">
							<img src="${contextRoot}/images/ico_calendario.gif" width="11" height="10" border="0"
							     onClick="limpiaFecha('fechaInicioEntrega');muestraCalendar('fechaInicioEntrega', 'idFecha', true);return false;"/>
						</a>
						<a href="#" title="Limpia Fecha">
							<img src="${contextRoot}/images/icono_basurero.gif"  width="16" height="16" border="0" onClick="javascript:limpiaFecha('fechaInicioEntrega')" title="Limpia Fecha"/>
						</a>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<strong><bean:message key="label.administracion.config.beca.fechaFin" /> </strong>
						</td>
						<td align="left">
						<html:text property="fechaFinEntrega" styleId="fechaFinEntrega" readonly="true"></html:text>
						<a href="#" id="idFecha">
							<img src="${contextRoot}/images/ico_calendario.gif" width="11" height="10" border="0"
							     onClick="limpiaFecha('fechaFinEntrega');muestraCalendar('fechaFinEntrega', 'idFecha', true);return false;"/>
						</a>
						
						<a href="#" title="Limpia Fecha">
							<img src="${contextRoot}/images/icono_basurero.gif"  width="16" height="16" border="0" onClick="javascript:limpiaFecha('fechaFinEntrega')" title="Limpia Fecha"/>
						</a>
						</td>
					</tr>
				
				</table>
		</div>
		<br/>
		
	</div>	
		
	<div id='tab_3' class="pane">		
	<table width="100%">
		<tr>
			<th colspan="5"><bean:message key="label.administracion.configBeneficios" />
			</th>
		</tr>
		<tr>
		<td colspan="5"><strong><bean:message key="label.administracion.config.beca.listaBeneficios" /> </strong></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message key="label.administracion.config.beca.nombreBeneficio" /> </strong></td>
			<td width="20%"><strong><label id="puntajeOnota"><bean:message key="label.administracion.config.beca.notas" /> </label></strong></td>
			<td width="20%"><strong><bean:message key="label.administracion.config.beca.porcentaje" /></strong></td>
			<td width="20%"><strong><bean:message key="label.administracion.config.beca.montoIncentivoBeneficio" /> </strong></td>
			<td width="5%"></td>
		
		</tr>
		<tr>
					<td><html:hidden property="idMaxDetBeneficio" styleId="idMaxDetBeneficio" value="0" />
						<div id="detNombreBeneficioId"> </div>
					</td>
					<td><div id="detDesdeBeneficioId"> </div>
					</td>
					<td><div id="detHastaBeneficioId"> </div>
					</td>
					<td><div id="detMontoBeneficioId"> </div>			
					</td>
					<td><div id="detEliminaBeneficioId"> </div>			
					</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><html:button property="agregarBeneficio" styleClass="button" styleId="open"><bean:message key="label.administracion.config.beca.botonAgregar" /></html:button></td>
		
		</tr>
	</table>	
	</div>
	
	<div id='tab_4' class="pane">		
		<!-- Lista Documentacion Requerida -->
	<table  width="100%">
		<tr>
			<th colspan="2"><bean:message key="label.administracion.configBecaCompatible" />
			</th>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message key="label.administracion.config.beca.compatibleBeca" /> </strong></td>
			<td align="left">
			<logic:equal name="ConfiguracionBecasIncentivosForm" property="compatibleBeca" value="true">
				<input type="radio" name="compatibleBeca" value="1"  checked="checked" onchange="javascript:actualizaCheckBeca(this.value, '')"/>SI
				<input type="radio" name="compatibleBeca" value="0"  onchange="javascript:actualizaCheckBeca(this.value, '')"/>NO
			</logic:equal>
			<logic:equal name="ConfiguracionBecasIncentivosForm" property="compatibleBeca" value="false"> 
				<input type="radio" name="compatibleBeca" value="1"  onchange="javascript:actualizaCheckBeca(this.value, '')"/>SI
				<input type="radio" name="compatibleBeca" value="0" checked="checked" onchange="javascript:actualizaCheckBeca(this.value, '')"/>NO
			</logic:equal>
			</td>
		</tr>
		<tr>
			<td width="20%">&nbsp;</td>
			<td align="left">
				<div id="becaid">
					<logic:equal name="ConfiguracionBecasIncentivosForm" property="compatibleBeca" value="true">
						<logic:notEmpty name="ConfiguracionBecasIncentivosForm" property="becas">
								<logic:iterate id="beca" name="ConfiguracionBecasIncentivosForm" property="becas">
									<logic:equal name="beca" property="seleccionado" value="true">
										<input id="beca<bean:write name="beca" property="idBeca"/>" type="checkbox" checked="checked" name="beca<bean:write name="beca" property="idBeca" />" value="<bean:write name="beca" property="idBeca" />"/><bean:write name="beca" property="nombre" />
										</br>
									</logic:equal>
									<logic:equal name="beca" property="seleccionado" value="false">
										<input id="beca<bean:write name="beca" property="idBeca"/>" type="checkbox" name="beca<bean:write name="beca" property="idBeca" />" value="<bean:write name="beca" property="idBeca" />"/><bean:write name="beca" property="nombre" />
										</br>
									</logic:equal>
								</logic:iterate>
							</logic:notEmpty>
					</logic:equal>
				 </div>
				 <input type="hidden" name="idMaxBeca" id="idMaxBeca" value="<bean:write name="idMaxBeca"/>"/>
			</td>
		</tr>
	</table>		
	</div>
	
	<div id='tab_5' class="pane">		
		<table width="100%" id="tablaFormatos">
		<tr>
			<th colspan="2"><bean:message key="label.administracion.configOtros" />
			</th>
		</tr>
		<tr>
			<td colspan="2"><strong><bean:message key="label.administracion.config.beca.docRequerido" /> </strong></td>
		</tr>
		<tr>
			<td width="20%">&nbsp;</td>
			<td align="left">
				<logic:notEmpty name="ConfiguracionBecasIncentivosForm" property="docRequeridas">
					<logic:iterate id="docRequerida" name="ConfiguracionBecasIncentivosForm" property="docRequeridas" indexId="idRequerimiento">
				   		<logic:equal name="docRequerida"  property="seleccionado" value="true">
				   			<input type="checkbox" checked="checked" name="requerimiento<bean:write name='docRequerida' property="idDocumentacionRequerida"/>" id="requerimiento<bean:write name='docRequerida' property="idDocumentacionRequerida"/>" value="<bean:write name='docRequerida' property="idDocumentacionRequerida"/>"/>
							<bean:write name="docRequerida" property="documentacionRequerida"/>
							</br>
						</logic:equal>				   	
						<logic:equal name="docRequerida"  property="seleccionado" value="false">
				   			<input type="checkbox" name="requerimiento<bean:write name='docRequerida' property="idDocumentacionRequerida"/>" id="requerimiento<bean:write name='docRequerida' property="idDocumentacionRequerida"/>" value="<bean:write name='docRequerida' property="idDocumentacionRequerida"/>"/>
							<bean:write name="docRequerida" property="documentacionRequerida"/>
							</br>
						</logic:equal>
				   	</logic:iterate>
				 </logic:notEmpty>
				   	<input type="hidden" name="idMaxRequerimiento" id="idMaxRequerimiento"  value="<bean:write name="idMaxDocRequerida"/>"/>
			</td>
		</tr>	
		<tr>
			<td colspan="2"><strong><bean:message key="label.administracion.config.beca.aplicaSegmento" /> </strong></td>
		</tr>
		<tr>	
			<td width="20%">&nbsp;</td>
			<td>
			<logic:notEmpty name="ConfiguracionBecasIncentivosForm" property="segmentos">
				<logic:iterate id="segmento" name="ConfiguracionBecasIncentivosForm" property="segmentos" indexId="idSegmento">
						<logic:equal name="segmento" property="seleccionado" value="true">
							<input type="checkbox" name="segmento${idSegmento}" checked="checked" id="segmento${idSegmento}" value="<bean:write name='segmento' property='idSegmento'/>" onclick="actualizaAreaConceptoBySegmento(${idSegmento});"/>
							<bean:write name="segmento" property="segmento"/>
							</br>	
						</logic:equal>
						<logic:equal name="segmento" property="seleccionado" value="false">
							<input type="checkbox" name="segmento${idSegmento}" id="segmento${idSegmento}" value="<bean:write name='segmento' property='idSegmento'/>" onclick="actualizaAreaConceptoBySegmento(${idSegmento});"/>
							<bean:write name="segmento" property="segmento"/>
							</br>	
						</logic:equal>	
				</logic:iterate>
			</logic:notEmpty>
				<html:hidden property="idMaxSegmento" styleId="idMaxSegmento" value="${idSegmento}"/>
			</td>
		</tr>
		</table>
		<table width="100%">
			<tr>
				<td colspan="2" align="center"><bean:message key="label.administracion.config.beca.textoCarta"/></td>
			</tr>	
			<tr>
				<td colspan="2">
					<logic:notEmpty name="ConfiguracionBecasIncentivosForm" property="textoCarta">
						<textarea name="textoCarta" id="textoCarta" rows="5" cols="65"  onKeyDown="cuenta()" onKeyUp="cuenta()" style="max-width:540px"><bean:write name="ConfiguracionBecasIncentivosForm" property="textoCarta"/></textarea>
					</logic:notEmpty>
					<logic:empty name="ConfiguracionBecasIncentivosForm" property="textoCarta">
						<textarea name="textoCarta" id="textoCarta" rows="5" cols="65"  onKeyDown="cuenta()" onKeyUp="cuenta()" style="max-width:540px"></textarea>
					</logic:empty>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<bean:message key="label.administracion.config.beca.caracteresCompatible"/><input type="text" name="caracteres" id="caracteres" size="3" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<html:button property="btnLimpiarTexto" onclick="limpiaTextoCarta();" styleClass="button"><bean:message key="button.limpiar"/></html:button>
				</td>
			</tr>
			<tr>
				<td class="2">
				
				</td>
			</tr>		
		</table>
	</div>
		
<div id='tab_6' class="pane">
	<table width="100%">
		<tr>
			<th colspan="4"><bean:message key="label.administracion.configAreaConcepto" />
			</th>
		</tr>
		<tr>
			<td><strong><bean:message key="label.administracion.config.beca.beneficios" /> </strong></td>
			<td><strong><bean:message key="label.administracion.config.beca.segmento" /> </strong></td>
			<td><strong><bean:message key="label.administracion.config.beca.area" /> </strong></td>
			<td><strong><bean:message key="label.administracion.config.beca.concepto" /> </strong></td>
		</tr>
		<tr>
			<td><html:hidden property="idMaxDetBeneficioAC" styleId="idMaxDetBeneficioAC" value="0" /> <div id="detBeneficioAC"> </div></td>
			<td><div id="detSegmentoAC"> </div></td>
			<td><div id="detAreaAC"> </div></td>
			<td><div id="detConceptoAC"> </div></td>
		</tr>
	</table>
</div>

	<table width="100%">
			<tr>
				<td align="center"><html:button property="aceptar" onclick="validaForm()" styleClass="button"><bean:message key="label.administracion.config.beca.botonAceptar" /></html:button></td>
			</tr>
	</table>
</div>
<div id="popup" style="display: none;">
	<div class="content-popup">
	<div class="close"><a href="#" id="close"><img src="${contextRoot}/images/action_delete.png"/></a></div>
	<table width="100%">
		<tr>
			<th colspan="4"><bean:message key="label.administracion.config.beca" />
			</th>
		</tr>
		<tr>
			<td colspan="4">
				<strong><bean:message key="label.administracion.config.beca.beneficio" /> </strong>
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">
				<strong><bean:message key="label.administracion.config.beca.nombre" /> </strong>
			</td>
			<td colspan="3">
				<html:select property="opcBeneficio" styleId="opcBeneficio">
				 			<option value="-1"><bean:message key="label.common.seleccione.text"/></option>
							<logic:present name="ConfiguracionBecasIncentivosForm" property="beneficiosList">
					   			<html:optionsCollection name="ConfiguracionBecasIncentivosForm" property="beneficiosList" />
					   		</logic:present>
				</html:select>
			</td>
		</tr>		
		<tr id="trNota">
		
				<td width="20%" align="right">
					<strong><bean:message key="label.administracion.config.beca.notaDesde" /> </strong>
				</td>
				<td>
					<html:text property="notaBeneficioDesde" styleId="notaBeneficioDesde" styleClass="notaBeneficioDesde"  maxlength="2" size="10"></html:text>
				</td>
				<td>
					<strong><bean:message key="label.administracion.config.beca.notaHasta" /> </strong>
				</td>
				<td>
					<html:text property="notaBeneficioHasta" styleId="notaBeneficioHasta" styleClass="notaBeneficioHasta"  maxlength="2" size="10"></html:text>
				</td>		
			</tr>		
		<tr id="trPorcentaje">
			<td width="20%" align="right">
				<strong><bean:message key="label.administracion.config.beca.porcentajeDesde" /> </strong>
			</td>
			<td>
				<html:text property="porcentajeBeneficioDesde" styleId="porcentajeBeneficioDesde" maxlength="3" size="10"></html:text>
			</td>
			<td>
				<strong><bean:message key="label.administracion.config.beca.porcentajeHasta" /> </strong>
			</td>
			<td>
				<html:text property="porcentajeBeneficioHasta" styleId="porcentajeBeneficioHasta" maxlength="3" size="10"></html:text>
			</td>
		</tr>
		
		<tr id="trPuntaje">
			<td width="20%" align="right">
				<strong><bean:message key="label.administracion.config.beca.notaPuntajeDesde" /> </strong>
			</td>
			<td>
				<html:text property="puntajeBeneficioDesde" styleId="puntajeBeneficioDesde" maxlength="3" size="10"></html:text>
			</td>
			<td>
				<strong><bean:message key="label.administracion.config.beca.notaPuntajeHasta" /> </strong>
			</td>
			<td>
				<html:text property="puntajeBeneficioHasta" styleId="puntajeBeneficioHasta"  maxlength="3" size="10"></html:text>
			</td>
		</tr>
		<tr id="beneficioDinero">
			<td width="20%" align="right">
				<strong><bean:message key="label.administracion.config.beca.monto" /> </strong>
			</td>
			<td colspan="3">
				<html:text property="montoBeneficio" styleId="montoBeneficio" styleClass="montoBeneficio" size="10"></html:text>
			</td>
		</tr>
		<tr id="beneficioIncentivo">
			<td width="20%" align="right">
				<strong><bean:message key="label.administracion.config.beca.incentivo" /> </strong>
			</td>
			<td colspan="3">
				<html:text property="incentivo" styleId="incentivo" styleClass="incentivo" size="30"></html:text>
			</td>
		</tr>		
		<tr>			
			<td align="center" colspan="4">&nbsp; &nbsp; &nbsp; &nbsp;<html:button property="aceptar" styleId="aceptar" styleClass="button" 
					onclick="javascript:agregaBeneficio();"><bean:message key="label.administracion.config.beca.botonAceptar" /></html:button></td>
		</tr>
	</table>
	</div>
</div>


<div id="popupAreaConcepto" style="display: none;">
	<div class="content-popupAreaConcepto">
	<div class="close"><a href="#" id="closeAreaConcepto"><img src="${contextRoot}/images/action_delete.png"/></a></div>
	<table width="100%">
		<tr>
			<th colspan="4"><bean:message key="label.administracion.config.areaConcepto.titulo" /></th>
		</tr>
		<tr>
			<td><strong><bean:message key="label.administracion.config.areaConcepto.beneficio" /></strong> </td>
			<td><div id="labelBeneficio"> </div> <input type="hidden"  name="idAreaConcepto" id="idAreaConcepto"/></td>
			
			<td><strong><bean:message key="label.administracion.config.areaConcepto.segmento" /></strong></td>
			<td><div id="labelSegmento"> </div></td>
		</tr>
		<tr>
			<td><strong><bean:message key="label.administracion.config.areaConcepto.area" /></strong> </td>
			<td><html:text property="area" styleId="area"></html:text></td>
			
			<td><strong><bean:message key="label.administracion.config.areaConcepto.concepto" /></strong> </td>
			<td><html:text property="concepto" styleId="concepto"></html:text></td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<html:button property="registraAreaConcepto" styleClass="button" styleId="registraAreaConcepto" onclick="javascript:guardaAreaConcepto();"><bean:message key="label.administracion.config.beca.botonAceptar" /></html:button>
			</td>
		</tr>
		
	</table>
</div>
</div>

</body>
</html:form>