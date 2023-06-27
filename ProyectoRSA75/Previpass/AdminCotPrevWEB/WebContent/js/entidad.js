var tipoAf;
/*
function trim(str) 
{
	return str.replace(/^\s+(.*?)\s+$/,'$1');
}
*/
function save(nombre)
{
	if(document.getElementById("agregarFolio").style.display=='none')
		document.getElementById('guardaFolio').value='1';
	else
		document.getElementById('guardaFolio').value='0';
	frm = document.getElementById('formulario');
	frm.folioBorrar.value = '';
	frm.accionInterna.value = 'GUARDAR';
	for(a=0; a<frm.bancos.length; a++){
		if(frm.bancos[a].selected == true){
			document.getElementById("idBancoSeleccionado").value = frm.bancos[a].value;
		}
	}
	if(nombre == 'AFP'){	
		for(a=0; a<frm.afc.length; a++){
			if(frm.afc[a].selected == true){
				document.getElementById("idAfcSeleccionado").value = frm.afc[a].value;
			}
		}
	}
		var sMsje = validar(nombre);
		if(sMsje != ''){
			alert("Errores de validación:\n\n" + sMsje);			
		} else {
			frm.submit();
		}
}
function cancelar() {
	frm = document.getElementById('formulario');
	
	frm.accionInterna.value = 'CANCELAR';
	frm.submit();
}
function listaExCaja() {
	formu = document.getElementById("formulario");
	formu.action = 'ListaEntidadesExCaja.do';
	formu.submit();
}
function saveNewEntidad(nombre){
		frm = document.getElementById('formulario');
		frm.folioBorrar.value = '';
		frm.accionInterna.value = 'NEW';
		if(document.getElementById("agregarFolio").style.display=='none')
			document.getElementById('guardaFolio').value='1';
		else
			document.getElementById('guardaFolio').value='0';
		for(a=0; a<frm.bancos.length; a++){
			if(frm.bancos[a].selected == true){
				document.getElementById("idBancoSeleccionado").value = frm.bancos[a].value;
			}
		}
		if(nombre == 'AFP'){	
			for(a=0; a<frm.afc.length; a++){
				if(frm.afc[a].selected == true){
					document.getElementById("idAfcSeleccionado").value = frm.afc[a].value;
					 
				}
			}
		}
		if(nombre != "EXCAJA"){
			for(a=0; a<frm.entidades.length; a++){
				if(frm.entidades[a].selected == true){
					document.getElementById("idEntidadSeleccionada").value = frm.entidades[a].value;
				}
			}
		}
		var sMsje = validar(nombre);
		if(sMsje != ''){
			alert("Errores de validación:\n\n" + sMsje);			
		} else {
			frm.idEntPagadoraNew.value = 0;
			frm.submit();
		}
}
function validar(nombre){

var sMsje = "";

		if(trim(document.getElementById("codigoEntidad").value) == "" ){
			sMsje += '* Ingrese el Código de la entidad\n';		
		} 
		if(trim(document.getElementById("nombreEntidad").value) == "" ){
			sMsje += '* Ingrese el nombre de la entidad\n';			
		} 
		if(trim(document.getElementById("idCtaCte").value) == "" &&  trim(document.getElementById("bancos").value != "0")){
			sMsje += '* Ingrese el número de la cuenta de Cheques\n';			
		}
		if(trim(document.getElementById("rutEntidad").value) == "" || trim(document.getElementById("rutEntidad").value == "0")){
			sMsje += '* Ingrese el RUT de la Entidad\n';
		} 
		if(validaDV(trim(document.getElementById("rutEntidad").value)) == false){
			sMsje += '* El dígito verificador del RUT no corresponde\n';			
		}
		if (parseInt(trim(document.getElementById("codigoEntidad").value)) <-32768 || parseInt(trim(document.getElementById("codigoEntidad").value)) > 32767){
			sMsje += '* El código entidad debe ser menor a 32767\n';			
		}
		if(trim(document.getElementById("idCtaCteSpl").value) == "" &&  trim(document.getElementById("idBancoSpl").value) != "0"){
			sMsje += '* Ingrese el número de la cuenta de Transferencias\n';			
		}
		if(trim(document.getElementById("correoContacto").value) == "" &&  trim(!validaMail(document.getElementById("correoContacto").value))){
			sMsje += '* Ingrese el Correo Contacto\n';		
		}
		if(!validaMail(trim(document.getElementById("correoContacto").value))){
			sMsje += '* Formato de Correo Contacto inválido\n';		
		}
		if(trim(document.getElementById("nombreContacto").value) == "" ){
			sMsje += '* Ingrese el Nombre Contacto\n';		
		}
		if(trim(document.getElementById("entidadFTP").value) == "" ){
			sMsje += '* Ingrese la Entidad FTP\n';		
		}
		if(trim(document.getElementById("carpetaFTP").value) == "" ){
			sMsje += '* Ingrese la Carpeta FTP\n';		
		}
		if(trim(document.getElementById("usuarioFTP").value) == "" ){
			sMsje += '* Ingrese la Usuario FTP\n';		
		}
		if(trim(document.getElementById("claveFTP").value) == "" ){
			sMsje += '* Ingrese la Clave FTP\n';		
		}
		
		for(a=0; a<frm.bancos.length; a++){
			if(frm.bancos[a].selected == true){
				document.getElementById("idBancoSeleccionado").value = frm.bancos[a].value;
				}
			}
		if(nombre == "SALUD"){
			var tasaSalud = document.getElementById("tasaSalud");
			if(tasaSalud.value == ""){
				sMsje += '* Ingrese la tasa de salud\n';
			} else {
				var tasaStr = new String(tasaSalud.value).replace(',', '.');
				if (parseFloat(tasaStr) == NaN) {
					sMsje += "* Tasa salud inválida\n";
				} else {
					var tasa = tasaSalud.value.split(',');
					if (tasa[0].length > 2) {
						sMsje += "* Tasa salud debe ser menor a 100\n";
					} else {
						var tasaAux = tasaSalud.value;
						tasaSalud.value = parseFloat(tasaStr).toFixed(2);
						if (tasaSalud.value == 'NaN') {
							tasaSalud.value = '';
							sMsje += "* Tasa salud inválida\n";
						} else if (!validaReal(tasaSalud.value)) {
							sMsje += "* Tasa salud inválida\n";
						} else {
							tasaSalud.value = tasaAux;
						}
					}
				}
			}
		}
		if(nombre == "AFP"){
			for(a=0; a<frm.afc.length; a++){
				if(frm.afc[a].selected == true){
					document.getElementById("idAfcSeleccionado").value = frm.afc[a].value;
				}
			}
			/*var tasaNormal = document.getElementById("tasaNormal");
			if(tasaNormal.value == ""){
				sMsje += '* Ingrese la tasa de normal\n';
			} else {
				var tasaStr = new String(tasaNormal.value).replace(',', '.');
				if (parseFloat(tasaStr) == NaN) {
					sMsje += "* Tasa normal inválida\n";
				} else {
					var tasa = tasaNormal.value.split(',');
					if (tasa[0].length > 2) {
						sMsje += "* Tasa normal debe ser menor a 100\n";
					} else {
						var tasaAux = tasaNormal.value;
						tasaNormal.value = parseFloat(tasaStr).toFixed(2);
						if (tasaNormal.value == 'NaN') {
							tasaNormal.value = '';
							sMsje += "* Tasa normal inválida\n";
						} else if (!validaReal(tasaNormal.value)) {
							sMsje += "* Tasa normal inválida\n";
						} else {
							tasaNormal.value = tasaAux;
						}
					}
				}
			}*/
			//TODO Jorge 
			//Eliminados 
			/*var tasaPensionados = document.getElementById("tasaPensionados");
			if(tasaPensionados.value == ""){
				sMsje += '* Ingrese la tasa de pensionados\n';
			} else {
				var tasaStr = new String(tasaPensionados.value).replace(',', '.');
				if (parseFloat(tasaStr) == NaN) {
					sMsje += "* Tasa pensionados inválida\n";
				} else {
					var tasa = tasaPensionados.value.split(',');
					if (tasa[0].length > 2) {
						sMsje += "* Tasa pensionados debe ser menor a 100\n";
					} else {
						var tasaAux = tasaPensionados.value;
						tasaPensionados.value = parseFloat(tasaStr).toFixed(2);
						if (tasaPensionados.value == 'NaN') {
							tasaPensionados.value = '';
							sMsje += "* Tasa pensionados inválida\n";
						} else if (!validaReal(tasaPensionados.value)) {
							sMsje += "* Tasa pensionados inválida\n";
						} else {
							tasaPensionados.value = tasaAux;
						}
					}
				}
			}*/
		}
		return sMsje;
}
function cambiaEntidad(){
	formu = document.getElementById("formulario");	
	for(a=0; a<formu.entidades.length; a++){
			if(formu.entidades[a].selected == true){
				if(formu.entidades[a].value == "SIL"){
					formu.action = 'EdicionEntidadesSil.do?tipoEdicion=NUEVO&tipoEdicionSeleccionada=SIL';
				}
				if(formu.entidades[a].value == "SALUD"){
					formu.action = 'EdicionEntidadesSalud.do?tipoEdicion=NUEVO&tipoEdicionSeleccionada=SALUD';
				}
				if(formu.entidades[a].value == "AFP"){
					formu.action = 'EdicionEntidadesAfp.do?tipoEdicion=NUEVO&tipoEdicionSeleccionada=AFP';
				}
				if(formu.entidades[a].value == "MUTUAL"){
					formu.action = 'EdicionEntidadesMutual.do?tipoEdicion=NUEVO&tipoEdicionSeleccionada=MUTUAL';
				}
				if(formu.entidades[a].value == "APV"){
					formu.action = 'EdicionEntidadesApv.do?tipoEdicion=NUEVO&tipoEdicionSeleccionada=APV';
				}
				if(formu.entidades[a].value == "CCAF"){
					formu.action = 'EdicionEntidadesCcaf.do?tipoEdicion=NUEVO&tipoEdicionSeleccionada=CCAF';
				}
				if(formu.entidades[a].value == "AFC"){
					formu.action = 'EdicionEntidadesAfc.do?tipoEdicion=NUEVO&tipoEdicionSeleccionada=AFC';
				}
			}
		}

	formu.submit();		
}

function validaIngresoFolio(fila){
		frm = document.getElementById('formulario');
		var folioActualNew = parseInt(document.getElementById("folioActual" + fila).value);
		var folioInicialNew = parseInt(document.getElementById("folioInicial" + fila).value);
		var folioFinalNew = parseInt(document.getElementById("folioFinal" + fila).value);
		var sMsje="";
		if(document.getElementById("foliosEnUsos" + fila)[0].selected == true ){
			sMsje+='* Debe Seleccionar el estado del folio.\n';
		} 
		if(document.getElementById("folioActual" + fila).value == "" ){
			sMsje+='* Ingrese un número mayor que 0 en el folio actual.\n';
		} 
		if(document.getElementById("folioInicial" + fila).value == "" ){
			sMsje+='* Ingrese un número mayor que 0 en el folio inicial.\n';
		} 
		if(document.getElementById("folioFinal" + fila).value == "" ){
			sMsje+='* Ingrese un número mayor que 0 en el folio final.\n';
		} 
		if(	(folioInicialNew > folioActualNew) || (folioFinalNew < folioActualNew)){
			sMsje+='* El folio actual debe estar entre el folio inicial y el folio final.\n';
		} 
		if(	folioInicialNew == 0){
			sMsje+='* El folio inicial debe ser mayor que 0.\n';
		} 
		if(	folioFinalNew == 0){
			sMsje+='* El folio final debe ser mayor que 0.\n';
		}
		return sMsje;
}
function saveFolioTipo(fila,idFoliacion,idEntPagadora, tipo) {
		tipoAf = tipo;
		saveFolio(fila,idFoliacion,idEntPagadora); 	
}
function saveFolio(fila,idFoliacion,idEntPagadora) {
		
		frm = document.getElementById('formulario');
		frm.folioBorrar.value = '';
		var folioActualNew = parseInt(document.getElementById("folioActual" + fila).value);
		var folioInicialNew = parseInt(document.getElementById("folioInicial" + fila).value);
		var folioFinalNew = parseInt(document.getElementById("folioFinal" + fila).value);
		var sMsje="";
		if(document.getElementById("foliosEnUsos" + fila)[0].selected == true ){
			sMsje+='* Debe Seleccionar el estado del folio.\n';
		} 
		if(document.getElementById("folioActual" + fila).value == "" ){
			sMsje+='* Ingrese un número mayor que 0 en el folio actual.\n';
		} 
		if(document.getElementById("folioInicial" + fila).value == "" ){
			sMsje+='* Ingrese un número mayor que 0 en el folio inicial.\n';
		} 
		if(document.getElementById("folioFinal" + fila).value == "" ){
			sMsje+='* Ingrese un número mayor que 0 en el folio final.\n';
		} 
		if(	(folioInicialNew > folioActualNew) || (folioFinalNew < folioActualNew)){
			sMsje+='* El folio actual debe estar entre el folio inicial y el folio final.\n';
		} 
		if(	folioInicialNew == 0){
			sMsje+='* El folio inicial debe ser mayor que 0.\n';
		} 
		if(	folioFinalNew == 0){
			sMsje+='* El folio final debe ser mayor que 0.\n';
		} 
		var folioEnUso = document.getElementById("foliosEnUsos" + fila);

		for(a=0; a<folioEnUso.length; a++){
				if(folioEnUso[a].selected == true){
					frm.foliosEnUsoNew.value = folioEnUso[a].value;
				}
			}
		if(sMsje == ''){
			document.getElementById('accionInterna').value = 'SAVE';
				
			document.getElementById('folioActualNew').value = folioActualNew;
			document.getElementById('folioInicialNew').value = folioInicialNew;
			document.getElementById('folioFinalNew').value = folioFinalNew;
			if(idEntPagadora == -1){
				idEntPagadora = document.getElementById('idEntPagadora').value;
			}
			
			document.getElementById('idEntPagadoraNew').value = idEntPagadora;
			document.getElementById('idEntPagadora').value = idEntPagadora;
			document.getElementById('idFoliacionNew').value = idFoliacion;

			var totalLista = document.getElementById('listaFolios').value + 
					idFoliacion + "/" +
					idEntPagadora + "/" +
					frm.foliosEnUsoNew.value + "/" +
					folioInicialNew + "/" +
					folioFinalNew + "/" +
					folioActualNew + "*";

			document.getElementById('listaFolios').value = totalLista;
			for(a=0; a<frm.bancos.length; a++){
				if(frm.bancos[a].selected == true){
					document.getElementById("idBancoSeleccionado").value = frm.bancos[a].value;
				}
			}
			if(tipoAf == 'AFP')
			{				
				for(a=0; a<frm.afc.length; a++){
					if(frm.afc[a].selected == true){
						document.getElementById("idAfcSeleccionado").value = frm.afc[a].value;
					}
				}
			}
			tipoAf='';
			frm.submit();	  	 
		} else {
			alert(sMsje);	
		}
  	}
  	function delFolioTipo(idFoliacion, idEntPagadora, nFila, tipo) {
	  	tipoAf = tipo;
	  	delFolio(idFoliacion, idEntPagadora, nFila);
  	}
  	function delFolio(idFoliacion, idEntPagadora, nFila) {
	frm = document.getElementById('formulario');
	if (confirm("¿Está seguro de que desea eliminar el folio?")) {
		frm.accionInterna.value = 'DEL';
		frm.folioBorrar.value = nFila;
		frm.idFoliacionNew.value = idFoliacion;
		frm.idEntPagadora.value = idEntPagadora;
		for(a=0; a<frm.bancos.length; a++){
				if(frm.bancos[a].selected == true){
					document.getElementById("idBancoSeleccionado").value = frm.bancos[a].value;
				}
			}
			if(tipoAf == 'AFP')
			{
				for(a=0; a<frm.afc.length; a++){
					if(frm.afc[a].selected == true){
						document.getElementById("idAfcSeleccionado").value = frm.afc[a].value;
					}
				}
			}
			tipoAf='';
		frm.submit();
	}
}

function addFolio() {
	document.getElementById("folioNuevo").style.display='';
	document.getElementById("folioNuevo2").style.display='';
	document.getElementById("tituloFolioNuevo").style.display='';
	document.getElementById("agregarFolio").style.display='none';
	//document.getElementById("cancelarFolio").style.display='';
}
function cancelarFolio()
{
	document.getElementById("folioNuevo").style.display='none';
	 	document.getElementById("folioNuevo2").style.display='none';
	document.getElementById("tituloFolioNuevo").style.display='none';
	document.getElementById("cancelarFolio").style.display='none';
	document.getElementById("agregarFolio").style.display='';
}
 function noSave(){
 	document.getElementById("folioNuevo").style.display='none';
 	 	document.getElementById("folioNuevo2").style.display='none';
	document.getElementById("tituloFolioNuevo").style.display='none';
	document.getElementById("agregarFolio").style.display='';
 }

	function validaFormulario(f) 
	{
		var txts = document.getElementsByTagName("input");
		var sMsje = "";
		tmp = new Array(txts.length);
		var count = 0;
		var num;
		for (var i = 0; i < txts.length; i++) 
		{
			var txt = txts[i];
			if (txt.type == "text") 
			{
				if (!validaReq(txt)) 
				{
					num = txt.id.substr(3);
					sMsje = "* Campo " + trim(document.getElementById("div" + num).firstChild.nodeValue) + " requerido\n";
					break;
				} else if (!validaChrs(txt.value)) 
				{
					num = txt.id.substr(3);
					sMsje = "* Caracteres inválidos en campo " + trim(document.getElementById("div" + num).firstChild.nodeValue) + "\n";
					break;
				}
				for (j = 0; j < count; j++)
				{
					if (trim(txt.value) == trim(tmp[j].value))
					{
						numtxt = txt.id.substr(3);
						numtmp = tmp[j].id.substr(3);
						textotxt = trim(document.getElementById("div" + numtxt).firstChild.nodeValue);
						textotmp = trim(document.getElementById("div" + numtmp).firstChild.nodeValue);
						if (textotmp == textotxt) {
							sMsje = "* Campos de " + textotmp + " tienen el mismo valor\n";
						} else {
							sMsje = "* Campos de " + textotmp + " y de " + textotxt + " contienen el mismo valor.\n";//, filas " + (j + 1) + " y " + (count + 1) + "\n";
						}
						txt.focus();
						break;
					}					
				}
				//tmp[count] = txt.value;
				tmp[count] = txt;
				count++;
			}
		}
		
		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			txt.focus();
			return false;
		}
		return true;
	}
	
	

function addEntidad(){
	formu = document.getElementById("formulario");	
	formu.action = 'EdicionEntidadesSalud.do?tipoEdicion=NUEVO&idEntPagadora=0';
	formu.submit();
}										
function editEntidad(nombre, id){
	formu = document.getElementById("formulario");	
	if(nombre == "SALUD")
		formu.action = 'EdicionEntidadesSalud.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id;
	if(nombre == "SIL")
		formu.action = 'EdicionEntidadesSil.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id;
	if(nombre == "AFP")
		formu.action = 'EdicionEntidadesAfp.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id;
	if(nombre == "APV")
		formu.action = 'EdicionEntidadesApv.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id;	
	if(nombre == "AFC")
		formu.action = 'EdicionEntidadesAfc.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id;	
	if(nombre == "MUTUAL")
		formu.action = 'EdicionEntidadesMutual.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id;	
	if(nombre == "EXCAJA")
		formu.action = 'EdicionEntidadesExCaja.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id;		
	formu.submit();
}	
function delEntidad(nombre,id){
	if (confirm("¿Está seguro de que desea eliminar la entidad y sus folios?"))
	{
		formu = document.getElementById("formulario");	
		if(nombre == "SALUD")
			formu.action = 'EdicionEntidadesSalud.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id+'&accionInterna=DEL_ENTIDAD';
		if(nombre == "SIL")
			formu.action = 'EdicionEntidadesSil.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id+'&accionInterna=DEL_ENTIDAD';
		if(nombre == "AFP")
			formu.action = 'EdicionEntidadesAfp.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id+'&accionInterna=DEL_ENTIDAD';
		if(nombre == "APV")
			formu.action = 'EdicionEntidadesApv.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id+'&accionInterna=DEL_ENTIDAD';	
		if(nombre == "CCAF")
			formu.action = 'EdicionEntidadesCcaf.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id+'&accionInterna=DEL_ENTIDAD';	
		if(nombre == "AFC")
			formu.action = 'EdicionEntidadesAfc.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id+'&accionInterna=DEL_ENTIDAD';	
		if(nombre == "MUTUAL")
			formu.action = 'EdicionEntidadesMutual.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id+'&accionInterna=DEL_ENTIDAD';	
		if(nombre == "EXCAJA")
			formu.action = 'EdicionEntidadesexCaja.do?accion=mapeo&subAccion=entidadFicha&subSubAccion=entidadEditar&tipoEdicion='+nombre+'&idEntPagadora='+id+'&accionInterna=DEL_ENTIDAD';		
		formu.submit();
		}
	}
function validaDecimal(valor){
	var retorno = true;
	if(valor != null){
		var largo = valor.length;
		var acum=0;
		for(a=0;a<largo;a++){
			if(a==0){
				if(valor.substr(0,1) == '.') retorno = false;
			}
			if(valor.substr(a,1) == '.') acum++;
			if(a==largo-1){
				if(valor.substr(largo-1,1) == '.') retorno = false;
			}
		}
		if(acum > 1) retorno = false;		
	}
	return retorno;
}

function actualizaFolios()
{
		var largoFolios = document.getElementById('largoFolios').value;
		frm = document.getElementById('formulario');
		document.getElementById('listaFolios').value="";
		var sMsje="";
		for(fila=0; fila < largoFolios; fila++)
		{
			var flag = false;
			if (document.getElementById("folioActual" + fila) && document.getElementById("folioInicial" + fila) && 
				document.getElementById("folioFinal" + fila) && document.getElementById("idFoliacion" + fila))
				flag = true;
			if (!flag)
				fila = largoFolios;
			else
			{
				folioActualNew = parseInt(document.getElementById("folioActual" + fila).value);
				folioInicialNew = parseInt(document.getElementById("folioInicial" + fila).value);
				folioFinalNew = parseInt(document.getElementById("folioFinal" + fila).value);
				idFoliacion = parseInt(document.getElementById("idFoliacion" + fila).value);
				foliosEnUsoNew = 0;
				
				if(document.getElementById("folioActual" + fila).value == "" ){
					sMsje+='* Ingrese un número mayor que 0 en el folio actual.\n';
				} 
				if(document.getElementById("folioInicial" + fila).value == "" ){
					sMsje+='* Ingrese un número mayor que 0 en el folio inicial.\n';
				} 
				if(document.getElementById("folioFinal" + fila).value == "" ){
					sMsje+='* Ingrese un número mayor que 0 en el folio final.\n';
				} 
				if(	(folioInicialNew > folioActualNew) || (folioFinalNew < folioActualNew)){
					sMsje+='* El folio actual debe estar entre el folio inicial y el folio final.\n';
				} 
				if(	folioInicialNew == 0){
					sMsje+='* El folio inicial debe ser mayor que 0.\n';
				} 
				if(	folioFinalNew == 0){
					sMsje+='* El folio final debe ser mayor que 0.\n';
				} 
				var folioEnUso = document.getElementById("foliosEnUsos" + fila);
		
				for(a=0; a<folioEnUso.length; a++)
				{
						if(folioEnUso[a].selected == true)
							foliosEnUsoNew = folioEnUso[a].value;
				}
			
				var totalLista = document.getElementById('listaFolios').value + 
							idFoliacion + "/" +
							0 + "/" +
							foliosEnUsoNew + "/" +
							folioInicialNew + "/" +
							folioFinalNew + "/" +
							folioActualNew + "*";
		
				document.getElementById('listaFolios').value = totalLista;
			}		
		}

		if(sMsje == '')
			return true;	  	 
		else
		{
			alert(sMsje);	
			return false;
		}
	}
	
function validaRazonSocial(razonSocial)
{
	var regex = new RegExp("/[^A-Z1-9\.-_&' ]/");
	var regex2 = /^ +$/
	try
  	{
		return !regex.test(razonSocial) && !regex2.test(razonSocial);
	} catch (err)
	{
		alert(":" + err.description + "::");
	}
}
