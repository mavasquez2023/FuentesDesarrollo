//funciones que validan formularios sobre tipo de datos (int / String)

/* formulario LM
		operador
		tipoform
		folio
		art77bis
		fecinform
		ruttrabaj
		fecemision
		fecinirepo
		fecterrepo
		edadtrabaj
		fecnactrab
		gentrabaj
		numdiaslic
		licmatsupl
		fecnachijo
		ruthijo
		tipolic
		recuplabor
		iniinvalid
		fecconcep
		tiporeposo
		jorreposo
		lugreposo
		especialid
		tipoprofes
		rutprofes
		nomprofes
		licmodific
		entautoriz
		tipolmresu
		ndiasincap
		diagresuel
		periodo
		ndiasprev
		estadoreso
		tiporesolu
		redictamen
		causarecha
		tiporepoau
		jorrepoaut
		desubsidio
		fecreceent
		fecresoent
		rutcontral
		rutemplead
		fecreceemp
		regionempl
		comunaempl
		actlabtrab
		ocupactrab
		fecrecepag
		tipregprev
		calitrabaj
		tipoentpag
		fecpriafil
		feccontrab
   	*/

/*formulario SIL
	 codope
	tpofor
	nrofol
	ruttrabaj
	fecemi
	diasub
	mtoliq
	mtocot
	codint
	finipa
	mocope
	baseca
	idlice
	inimes
	tpolic
	ndicot
	ndiinc
	ndipag
	mtsbpa
	mtsbdi
	mcsegc
	motcot
	ofipgo
	ccopgo
	inssal
	submat
	tpoliq
	fecpgo
	mliqpa
	rimpms
	#
	lichasfec
	pagfol
 * */

function validarForm(plano,tipoForm){	
	
	if(plano=="SIL"){
		return validarForm_SIL(tipoForm);
	}else if(plano=="LM"){
		return validarForm_LM(tipoForm);
	}	 
}//end validarForm

function validarForm_LM(tipoForm){
	var key=true;
	var keyAux=true;
	var msg="";
	//validar campos
	campo="operador";
	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="tipoform";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="folio";
   	if(valNull($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNull();
   	}else{
   		key=true;
   		msg="";
   	}
	marcarCampo(tipoForm,campo,key,msg);
	
	campo="art77bis";
	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
	marcarCampo(tipoForm,campo,key,msg);
//asdasd	
	campo="fecinform";
	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
	marcarCampo(tipoForm,campo,key,msg);
	
	campo="ruttrabaj";
	if(valNull($("#"+tipoForm).find("#"+campo).val())){
   		key=false;
		keyAux=false;
		msg=msgNull();
   	}else{
   		key=true;
   		msg="";
   	}
	marcarCampo(tipoForm,campo,key,msg);
	
	if(valFormatoRut($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgRut();
   	}else{
   		key=true;
   		msg="";
   	}
	marcarCampo(tipoForm,campo,key,msg);
	
	/*campo="rut_empleador";
	if(valFormatoRut($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgRut();
   	}else{
   		key=true;
   		msg="";
   	}
	marcarCampo(tipoForm,campo,key,msg);*/
	
	campo="fecemision";
	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
	marcarCampo(tipoForm,campo,key,msg);
	
	campo="fecinirepo";
	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
	marcarCampo(tipoForm,campo,key,msg);
	
	campo="fecterrepo";
	
	if(valNull($("#"+tipoForm).find("#"+campo).val())){
   		key=false;
		keyAux=false;
		msg=msgNull();
   	}else{
   		key=true;
   		msg="";
   	}
	marcarCampo(tipoForm,campo,key,msg);
	
	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="edadtrabaj";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="fecnactrab";
   	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="numdiaslic";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="licmatsupl";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="fecnachijo";
  //campo no obligatorio: validar solo si existe contenido.
   	if (isNotNull($("#"+tipoForm).find("#"+campo).val())){
	   	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   		msg=msgFechas();
	   	}else{
	   		key=true;
	   		msg="";
	   	}
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="ruthijo";
  //campo no obligatorio: validar solo si existe contenido.
   	if (isNotNull($("#"+tipoForm).find("#"+campo).val())){
	   	if(valFormatoRut($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
			msg=msgRut();
	   	}else{
	   		key=true;
	   		msg="";
	   	}
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="tipolic";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="recuplabor";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="iniinvalid";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="fecconcep";
  //campo no obligatorio: validar solo si existe contenido.
   	if (isNotNull($("#"+tipoForm).find("#"+campo).val())){
	   	if(valFormaDateAM($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
			msg=msgFechas();
	   	}else{
	   		key=true;
	   		msg="";
	   	}
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="tiporeposo";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="jorreposo";
   	if(valLetras($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNull();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="lugreposo";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="especialid";
   /*	if(valLetras($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgLetras();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	*/
   	campo="tipoprofes";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="rutprofes";
   	if(valFormatoRut($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgRut();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	   	
   	campo="nomprofes";
   /*	if(valLetras($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgLetras();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	*/
   	campo="licmodific";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="entautoriz";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="tipolmresu";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="ndiasincap";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="diagresuel";
   	/*if(valLetras($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgLetras();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	*/
   	campo="periodo";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="ndiasprev";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="estadoreso";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="tiporesolu";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="redictamen";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="causarecha";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="tiporepoau";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="jorrepoaut";
   	/*if(valLetras($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgLetras();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	*/
   	campo="desubsidio";
   /*	if(valLetras($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgLetras();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
*/
   	campo="fecreceent";
   	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="fecresoent";
   	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="rutcontral";
   	//campo no obligatorio: validar solo si existe contenido.
   	if (isNotNull($("#"+tipoForm).find("#"+campo).val())){
   		if(valFormatoRut($("#"+tipoForm).find("#"+campo).val())){
   			key=false;
   			keyAux=false;
   			msg=msgRut();
   	   	}else{
   	   		key=true;
   	   		msg="";
   	   	}   	   	
   	}else{
   		key=true;
	   	msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	   	
   	campo="rutemplead";
   	if(valFormatoRut($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgRut();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="fecreceemp";
   	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="regionempl";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="comunaempl";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="actlabtrab";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="ocupactrab";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="fecrecepag";
   	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="tipregprev";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="calitrabaj";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="tipoentpag";
  /* 	if(valLetras($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgLetras();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	*/
   	campo="fecpriafil";
   	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="feccontrab";
   	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	
   	
    return keyAux;
}

function validarForm_SIL(tipoForm){
	var key=true;
	var keyAux=true;	
	var msg="";
	//validar campos
	campo="codope";
	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="tpofor";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="nrofol";
	if(valNull($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNull();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="ruttrabaj";
   	if(valNull($("#"+tipoForm).find("#"+campo).val())){
   		key=false;
		keyAux=false;
		msg=msgNull();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	if(valFormatoRut($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgRut();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="fecemi";
   	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="diasub";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="mtoliq";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="mtocot";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="codint";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="finipa";
   	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="mocope";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="baseca";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="idlice";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="inimes";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="tpolic";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="ndicot";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="ndiinc";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="ndipag";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="mtsbpa";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="mtsbdi";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="mcsegc";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="motcot";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="ofipgo";
  /* 	if(valLetras($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgLetras();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
*/
   	campo="ccopgo";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="inssal";
  /* 	if(valLetras($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgLetras();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
*/
   	campo="submat";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="tpoliq";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="fecpgo";
   	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="mliqpa";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){ 	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);

   	campo="rimpms";
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="lichasfec";
   	if(valNull($("#"+tipoForm).find("#"+campo).val())){
   		key=false;
		keyAux=false;
		msg=msgNull();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	if(valDateISO($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgFechas();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="pagfol";
   	if(valNull($("#"+tipoForm).find("#"+campo).val())){
   		key=false;
		keyAux=false;
		msg=msgNull();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	if(valNumerico($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
		msg=msgNumerico();
   	}else{
   		key=true;
   		msg="";
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	return keyAux;
}

//evaluaciones genericas-----------------------------------------------------------------------------------


function valNull(valor){
//retorna true si el valor contiene null o vacio
	var key=false;	
	if(valor=="" || valor==null){
		key=true;
   	}	
	return key;
}

function isNotNull(valor){
	//retorna true si el valor no es nulo
		var key=false;	
		if(valor=="" || valor==null){
			key=false;
	   	}else{
	   		key=true;
	   	}	
		return key;
	}

function valNumerico(valor){
//retorna true si el valor contiene letras.
	var key=false;
	if (!/^([0-9])*$/.test(valor)){
		key=true;
   	}
	return key;
}

function valEdad(valor){
//retorna true si el valor contiene letras o es menor que quince o mayor a 89
	var key=false;
	
	if (!/^([1][5-9]|[2-8][0-9])$/.test(valor)){
		key=true;
   	}
	return key;
}

function valLetras(valor){
//
	var key=false;
	if (!/^[a-zA-Z]*$/.test(valor)){
		key=true;
   	}
	return key;
}

function valFormaDateAM(valor){
//retorna true si el valor contiene letras o contiene un mes no valido
	var key=false;
	
	if (!/^\d{4}(0[1-9]|1[012])$/.test(valor)){
		key=true;
   	}
	return key;
}

function valFormaDateISO(valor){
//retorna true si el valor(en formato) no es fecha ISO (aaaa-MM-dd)
var key=true;

if (/^\d{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])$/.test(valor)){
		key=false;
   	}
return key;
}

function valDateISO(valor){
	//retorna true si el valor(en formato) no es fecha ISO (aaaaMMdd)
	var key=true;

	if (/^\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/.test(valor)){
			key=false;
	   	}
	return key;
	}

function valFormatoRut(valor){
	//retorna false si el valor no tiene formato rut, 999999999-9 (y no menores a 1 millon "1000000-k")
	//o si su digito verificador no corresponde.
	var aux=true;	
	if (/^\b\d{7,9}\-[K|k|0-9]$/.test(valor) && RutMod11(valor)){
		aux=false;
   	}	
	return aux;
}//end valFormatoRut

//[START: funciones de mensajes]---------------------------

function msgNull(){
	return "Debe ingresar un valor";
}

function msgRut(){
	return "Debe ingresar un rut valido";
}

function msgNumerico(){
	return "Debe ingresar un valor numerico";
}

function msgLetras(){
	return "Debe ingresar un valor sin numeros";
}

function msgFechas(){
	return "Debe ingresar una fecha valida";
}
//[END: funciones de mensajes]---------------------------


function marcarCampo(tipoForm,campo,key,msg){
	if(key){
		$("#"+tipoForm).find("#"+campo).css({'background-color' : 'white'});
		$("#"+tipoForm).find("#"+campo+"_error").text("");
	}else{
		$("#"+tipoForm).find("#"+campo+"_error").text("( *) "+msg);
		$("#"+tipoForm).find("#"+campo+"_error").css({'color' : '#cd0a0a'});
		//$("#"+tipoForm).find("#"+campo).val("");		
		//$("#"+tipoForm).find("#"+campo).css({'background-color' : '#fef1ec'});
		//$("#"+tipoForm).find("#"+campo).css({'background-color' : '#daa6a9'});
	}
	return true;
}
