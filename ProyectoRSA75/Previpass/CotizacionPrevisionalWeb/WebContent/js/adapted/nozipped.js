

function AR_initForm() {
    document.senderForm.nomina1.focus();
}

function AR_back() {
    window.history.back();

    return false;
}

function AR_cancelForm() {
	AR_back();
    
    return false;
}

function AR_checkUnicNomForm() {
    var i, j, baseDir, element, path, n, auxN;
    var seleccionadas, ns;
    var nomina, auxNomina, index, idEmpresa, idConvenio;

    // Inicializa la identificaci�n de las n�minas seleccionadas.
    seleccionadas = new Array(MAX_NOMINAS_SELECCIONABLES);
    ns = 0;

    for (i = 0; i < seleccionadas.length; i++) {
        seleccionadas[i] = new Array(2);
    }

    // Determinaci�n y validaci�n de las n�minas seleccionadas.
    for (i = 1; i <= MAX_NOMINAS_SELECCIONABLES; i++) {
        element = AR_getIdNominaElement(i);
        element.value = "";
        element = AR_getNominaElement(i);
        n = i;
        
        var tipoProceso = AR_getObtieneTipoProceso();
	    var tipoConvenio = AR_getObtieneConvenio();
  	    var rutEmpresa = AR_getObtieneRutEmpresa();

  	    path =rutEmpresa.defaultValue + "." + tipoProceso.defaultValue + tipoConvenio.defaultValue;

        // Verifica que la n�mina haya sido seleccionada.
        if (path != "") {
        
            /*
             * Tratamiento especial para comparar drives.
             */
            if (path.length >= 2 && path.charAt(1) == ':') {
	        	path = path.substring(2);
            }
            
			index = path.lastIndexOf("/");
			
			if (index == -1) {
				index = path.lastIndexOf("\\");
			}

			nomina = path.substring(index + 1);	
            /*
             * Verifica que la identificaci�n de la n�mina
             * sea de la forma '.<tipoNomina><convenio>'.
             */
            index = nomina.lastIndexOf(".");

            if (index <= 0 || nomina.length < 5) {
                AR_notifyIdNominaUnicaError(n, nomina);

                return false;
            }
			
            tipoNomina = nomina.charAt(index + 1);
            idConvenio = nomina.substring(index + 2);
			extension= nomina.substring(index + 1);
			extension= extension.toUpperCase();
			
            if(n<11 && (!AR_isTipoNomina(tipoNomina)
                    || !AR_isNumber(idConvenio)
                    || idConvenio.length != 2)) {

                AR_notifyIdNominaError(n, nomina);

                return false;
            }else if(n==11 && extension!="ZIP"){
            	AR_notifyIdZipError(1, nomina);
            	return false;
            }
			if(n<11){
				idEmpresa = AR_getEmpresaElement(n).value;
			
            	/*
             	* Verifica que el usuario est� autorizado para
             	* enviar la n�mina.
             	*/
           	 	if (!AR_isAuthorizedConvenio(idEmpresa, parseInt(idConvenio, 10))) {
                	AR_notifyAuthorizationError(n, nomina);
                return false;
            	}
			}
			/*
			 * Normaliza el nombre de la n�mina a enviar ajust�ndola
			 * a la forma '<rutEmpresa>.<tipoNomina><convenio>'.
			 */
			if(n<11){
				dv= AR_calcularDVRut(idEmpresa);
				nomina = idEmpresa + dv + "." + tipoNomina + idConvenio;
			}
            idNomina = AR_getIdNominaElement(n);
            idNomina.value = nomina;

            seleccionadas[ns][0] = nomina;
            seleccionadas[ns++][1] = n;
        }
    }

    if (ns == 0) {
        alert("Seleccione al menos una n�mina para realizar el env�o.");
        
        return false;
    }
    
    // Verifica la unicidad de las n�minas seleccionadas.
    for (i = 0; i < ns; i++) {
        nomina = seleccionadas[i][0];
        n = seleccionadas[i][1];

        for (j = i + 1; j < ns; j++) {
            auxNomina = seleccionadas[j][0];

            if (nomina == auxNomina) {
                auxN = seleccionadas[j][1];

                AR_notifyDuplicationError(n, nomina, auxN);

                return false;
            }
        }
    }

    /*
     * Determina la lista de empresas asociadas a
     * las n�minas seleccionadas.
     */
    var idEmpresas = new Array();
    var matched, auxIdEmpresa, nombreEmpresa, nss;

    for (i = 0; i < ns; i++) {
        nomina = seleccionadas[i][0];
        index = nomina.indexOf(".");
        idEmpresa = nomina.substring(0, index);
        matched = false;

        for (j = 0; j < idEmpresas.length; j++) {
            auxIdEmpresa = idEmpresas[j];

            if (idEmpresa == auxIdEmpresa) {
                matched = true;

                break;
            }
        }

        if (!matched) {
            idEmpresas[idEmpresas.length] = idEmpresa;
        }
    }

    /*
     * Reporta las n�minas seleccionadas para enviar.
     */
    var report = " N�minas seleccionadas para enviar (normalizadas):\n";
    for (i = 0; i < idEmpresas.length; i++) {
        idEmpresa = idEmpresas[i];
        nombreEmpresa = AR_getNombreEmpresa(idEmpresa);
        if(nombreEmpresa== null){
        	nombreEmpresa= idEmpresa;
        }
        nss = 0;

        for (j = 0; j < ns; j++) {
            nomina = seleccionadas[j][0];
            index = nomina.indexOf(".");
            auxIdEmpresa = nomina.substring(0, index);

            if (idEmpresa == auxIdEmpresa) {
                nss++;
            }
        }

        report += "\n     >> " + nombreEmpresa + " [" + nss + "]:\n\n";

        for (j = 0; j < ns; j++) {
            nomina = seleccionadas[j][0];
            index = nomina.indexOf(".");
            auxIdEmpresa = nomina.substring(0, index);

            if (idEmpresa == auxIdEmpresa) {
                report += "           " + nomina + "\n";
            }
        }
    }

    report += "\n Total de n�minas seleccionadas para enviar: " + ns + ".\n\n";
    report += "Para proceder con el env�o de las n�minas seleccionadas a\n";
    report += "La Araucana C.C.A.F. haga click en el bot�n 'Aceptar'.\n";
    report += "En caso contrario, haga click en el bot�n 'Cancelar'.\n\n";
   	return confirm(report);
}


function AR_isNumber(number) {
    var i, ch;

    for (i = 0; i < number.length; i++) {
        ch = number.charAt(i);

        if (ch < '0' || ch > '9') {
            return false;
        }
    }

    return number.length > 0;
}


function AR_isTipoNomina(tipoNomina) {
    var i;

    for (i = 0; i < tipoNominas.length; i++) {
        if (tipoNominas[i] == tipoNomina) {
            return true;
        }
    }

    return false;
}


function AR_isAuthorizedConvenio(idEmpresa, idConvenio) {
    var auxIdConvenio, i, index, rutEmpresa, numConvenio;

    auxIdConvenio = parseInt(idConvenio);

    for (i = 0; i < convenios.length; i++) {
        index = convenios[i].indexOf(",");
        rutEmpresa = convenios[i].substring(0, index);
        numConvenio = convenios[i].substring(index + 1);

        if (idEmpresa == rutEmpresa && auxIdConvenio == numConvenio) {
            return true;
        }
    }

    return false;
}


function AR_getNombreEmpresa(idEmpresa) {
    var i, index, rutEmpresa;

    for (i = 0; i < empresas.length; i++) {
        index = empresas[i].indexOf("=");
        rutEmpresa = empresas[i].substring(0, index);

        if (idEmpresa == rutEmpresa) {
            return empresas[i].substring(index + 1);
        }
    }

    return null;
}


function AR_notifyIdNominaError(n, name) {
    alert(
              "N�mina " + n + ": Extensi�n de "
            + "'" + name + "' es inv�lida."
            + "\n\n"
            + "Las n�minas deben tener extensi�n de la siguiente forma:"
            + "\n\n"
            + "               .<tipoNomina><convenio>\n\n"
            + "<tipoNomina> : Tipo de la n�mina. Los tipos v�lidos son: " + enumTipoNominas + ".\n"
            + "<convenio>   : N�mero del convenio de cotizaci�n. (Siempre con 2 d�gitos)\n\n"
            + "        Ejemplos: 70016160." + tipoNominas[0] + "01, 10450366." + tipoNominas[1] +"03\n\n"
            + "Por favor corrija seleccionando una n�mina v�lida para poder continuar.");
}

function AR_notifyIdNominaUnicaError(n, name) {
    alert("Debe seleccionar una empresa para enviar la n�mina.");
}
function AR_notifyIdZipError(n, name) {
    alert(
              "Archivo Zip " + n + ": Extensi�n de "
            + "'" + name + "' es inv�lida."
            + "\n\n"
            + "El archivo a enviar debe tener extensi�n de la siguiente forma:"
            + "\n\n"
            + "               <nombre>.zip    , donde:\n\n"
            + "<nombre> : Nombre del archivo comprimido.\n\n"
            + "Y las n�minas contenidas dentro del Zip deben tener extensi�n de la siguiente forma:"
            + "\n\n"
            + "               <rutempresa>.<tipoNomina><convenio>   , donde:\n\n"
            + "<rutempresa> : Rut de la empresa incluido el digito verificador, sin guion.\n"
            + "<tipoNomina> : Tipo de la n�mina. Los tipos v�lidos son: " + enumTipoNominas + ".\n"
            + "<convenio>   : N�mero del convenio de cotizaci�n. (Siempre con 2 d�gitos)\n\n"
            + "        Ejemplo zip: remu_junio.zip  , que contiene las n�minas:\n\n"
            + "                    70016160." + tipoNominas[0] + "01, 10450366." + tipoNominas[1] +"03\n\n"
            + "Por favor corrija seleccionando un archivo v�lido para poder continuar.");
}

function AR_notifyAuthorizationError(n, name) {
    alert(
              "N�mina " + n + ": Usted no est� autorizado para enviar la n�mina "
            + "'" + name + "'."
            + "\n\n"
            + "Por favor corrija seleccionando una n�mina autorizada para usted y as� "
            + "poder continuar.");
}


function AR_notifyDuplicationError(n, name, duplicatedN) {
    alert(
              "N�mina " + n + ": '" + name + "' est� duplicada "
            + "con la n�mina " + duplicatedN + ".\n\n"
            + "Por favor corrija seleccionando una s�la vez cada n�mina a enviar "
            + "para poder continuar.");
}


function AR_getNominaElement(n) {
    var i, elements, element;

    elements = document.senderForm.elements;

    for (i = 0; i < elements.length; i++) {
        element = elements[i];

        if (element.name == "nomina" + n) {
            return element;
        }
    }

    return null;
}


function AR_getIdNominaElement(n) {
    var i, elements, element;

    elements = document.senderForm.elements;

    for (i = 0; i < elements.length; i++) {
        element = elements[i];

        if (element.name == "idNomina" + n) {
            return element;
        }
    }

    return null;
}

function AR_getObtieneTipoProceso() {
    var elements, element;

    elements = document.senderForm.elements;

    element = elements["_tipoNomina"];

     return element;    
}

function AR_getObtieneConvenio() {
    var elements, element;

    elements = document.senderForm.elements;

    element = elements["_convenio"];

     return element;    
}

function AR_getObtieneRutEmpresa() {
    var elements, element;

    elements = document.senderForm.elements;

    element = elements["_rutEmpresa"];

     return element;    
}

function AR_getEmpresaElement(n) {
    var i, elements, element;

    elements = document.senderForm.elements;

    for (i = 0; i < elements.length; i++) {
        element = elements[i];

        if (element.name == "empresa" + n) {
            return element;
        }
    }

    return null;
}
function AR_calcularDVRut(rut){
	var Suma = 2;
	var TotalRut=0;
	var resto;
	var dvcalculado;
	var largo= rut.length;
	for (var i = largo-1; i>=0; i--) {
		if (Suma>7) 
			Suma=2;
		TotalRut=TotalRut + (rut.substr(i, 1))*Suma;
          	Suma=Suma+1;
	}
	resto=TotalRut % 11;
	dvcalculado= 11- resto
	if (dvcalculado== 10)
		dvcalculado= "K";	
	if (dvcalculado== 11)
		dvcalculado= "0";	
return dvcalculado;
}

function AR_checkForm() {
    var i, j, baseDir, element, path, n, auxN;
    var seleccionadas, ns;
    var nomina, auxNomina, index, idEmpresa, idConvenio;

    // Inicializa la identificaci�n de las n�minas seleccionadas.
    seleccionadas = new Array(MAX_NOMINAS_SELECCIONABLES);
    ns = 0;

    for (i = 0; i < seleccionadas.length; i++) {
        seleccionadas[i] = new Array(2);
    }

    // Determinaci�n y validaci�n de las n�minas seleccionadas.
    for (i = 1; i <= MAX_NOMINAS_SELECCIONABLES; i++) {
        element = AR_getIdNominaElement(i);
        element.value = "";
        element = AR_getNominaElement(i);
        n = i;
        path = element.value;
        // Verifica que la n�mina haya sido seleccionada.
        if (path != "") {
        
            /*
             * Tratamiento especial para comparar drives.
             */
            if (path.length >= 2 && path.charAt(1) == ':') {
	        	path = path.substring(2);
            }
            
			index = path.lastIndexOf("/");
			
			if (index == -1) {
				index = path.lastIndexOf("\\");
			}

			nomina = path.substring(index + 1);	
            /*
             * Verifica que la identificaci�n de la n�mina
             * sea de la forma '.<tipoNomina><convenio>'.
             */
            index = nomina.lastIndexOf(".");

            if (index <= 0 || nomina.length < 5) {
                AR_notifyIdNominaError(n, nomina);

                return false;
            }
			
            tipoNomina = nomina.charAt(index + 1);
            idConvenio = nomina.substring(index + 2);
			extension= nomina.substring(index + 1);
			extension= extension.toUpperCase();
			
            if(n<11 && (!AR_isTipoNomina(tipoNomina)
                    || !AR_isNumber(idConvenio)
                    || idConvenio.length != 2)) {

                AR_notifyIdNominaError(n, nomina);

                return false;
            }else if(n==11 && extension!="ZIP"){
            	AR_notifyIdZipError(1, nomina);
            	return false;
            }
			if(n<11){
				idEmpresa = AR_getEmpresaElement(n).value;
			
            	/*
             	* Verifica que el usuario est� autorizado para
             	* enviar la n�mina.
             	*/
           	 	if (!AR_isAuthorizedConvenio(idEmpresa, parseInt(idConvenio, 10))) {
                	AR_notifyAuthorizationError(n, nomina);
                return false;
            	}
			}
			/*
			 * Normaliza el nombre de la n�mina a enviar ajust�ndola
			 * a la forma '<rutEmpresa>.<tipoNomina><convenio>'.
			 */
			if(n<11){
				dv= AR_calcularDVRut(idEmpresa);
				nomina = idEmpresa + dv + "." + tipoNomina + idConvenio;
			}
            idNomina = AR_getIdNominaElement(n);
            idNomina.value = nomina;

            seleccionadas[ns][0] = nomina;
            seleccionadas[ns++][1] = n;
        }
    }

    if (ns == 0) {
        alert("Seleccione al menos una n�mina para realizar el env�o.");
        
        return false;
    }
    
    // Verifica la unicidad de las n�minas seleccionadas.
    for (i = 0; i < ns; i++) {
        nomina = seleccionadas[i][0];
        n = seleccionadas[i][1];

        for (j = i + 1; j < ns; j++) {
            auxNomina = seleccionadas[j][0];

            if (nomina == auxNomina) {
                auxN = seleccionadas[j][1];

                AR_notifyDuplicationError(n, nomina, auxN);

                return false;
            }
        }
    }

    /*
     * Determina la lista de empresas asociadas a
     * las n�minas seleccionadas.
     */
    var idEmpresas = new Array();
    var matched, auxIdEmpresa, nombreEmpresa, nss;

    for (i = 0; i < ns; i++) {
        nomina = seleccionadas[i][0];
        index = nomina.indexOf(".");
        idEmpresa = nomina.substring(0, index);
        matched = false;

        for (j = 0; j < idEmpresas.length; j++) {
            auxIdEmpresa = idEmpresas[j];

            if (idEmpresa == auxIdEmpresa) {
                matched = true;

                break;
            }
        }

        if (!matched) {
            idEmpresas[idEmpresas.length] = idEmpresa;
        }
    }

    /*
     * Reporta las n�minas seleccionadas para enviar.
     */
    var report = " N�minas seleccionadas para enviar (normalizadas):\n";
    for (i = 0; i < idEmpresas.length; i++) {
        idEmpresa = idEmpresas[i];
        nombreEmpresa = AR_getNombreEmpresa(idEmpresa);
        if(nombreEmpresa== null){
        	nombreEmpresa= idEmpresa;
        }
        nss = 0;

        for (j = 0; j < ns; j++) {
            nomina = seleccionadas[j][0];
            index = nomina.indexOf(".");
            auxIdEmpresa = nomina.substring(0, index);

            if (idEmpresa == auxIdEmpresa) {
                nss++;
            }
        }

        report += "\n     >> " + nombreEmpresa + " [" + nss + "]:\n\n";

        for (j = 0; j < ns; j++) {
            nomina = seleccionadas[j][0];
            index = nomina.indexOf(".");
            auxIdEmpresa = nomina.substring(0, index);

            if (idEmpresa == auxIdEmpresa) {
                report += "           " + nomina + "\n";
            }
        }
    }

    report += "\n Total de n�minas seleccionadas para enviar: " + ns + ".\n\n";
    report += "Para proceder con el env�o de las n�minas seleccionadas a\n";
    report += "La Araucana C.C.A.F. haga click en el bot�n 'Aceptar'.\n";
    report += "En caso contrario, haga click en el bot�n 'Cancelar'.\n\n";
   	return confirm(report);
}