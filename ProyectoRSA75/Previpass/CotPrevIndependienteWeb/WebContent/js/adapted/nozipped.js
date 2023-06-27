

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

function AR_checkForm() {
    var i, j, baseDir, element, path, n, auxN;
    var seleccionadas, ns;
    var nomina, auxNomina, index, idEmpresa, idConvenio;

    // Inicializa la identificación de las nóminas seleccionadas.
    seleccionadas = new Array(MAX_NOMINAS_SELECCIONABLES);
    ns = 0;

    for (i = 0; i < seleccionadas.length; i++) {
        seleccionadas[i] = new Array(2);
    }

    // Determinación y validación de las nóminas seleccionadas.
    for (i = 1; i <= MAX_NOMINAS_SELECCIONABLES; i++) {
        element = AR_getIdNominaElement(i);
        element.value = "";
        element = AR_getNominaElement(i);
        n = i;
        path = element.value;

        // Verifica que la nómina haya sido seleccionada.
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
             * Verifica que la identificación de la nómina
             * sea de la forma '.<tipoNomina><convenio>'.
             */
            index = nomina.lastIndexOf(".");

            if (index <= 0 || nomina.length < 5) {
                AR_notifyIdNominaError(n, nomina);

                return false;
            }
			
            tipoNomina = nomina.charAt(index + 1);
            idConvenio = nomina.substring(index + 2);

            if(!AR_isTipoNomina(tipoNomina)
                    || !AR_isNumber(idConvenio)
                    || idConvenio.length != 2) {

                AR_notifyIdNominaError(n, nomina);

                return false;
            }

			idEmpresa = AR_getEmpresaElement(n).value;
			
            /*
             * Verifica que el usuario esté autorizado para
             * enviar la nómina.
             */
            if (!AR_isAuthorizedConvenio(idEmpresa, idConvenio)) {
                AR_notifyAuthorizationError(n, nomina);

                return false;
            }

			/*
			 * Normaliza el nombre de la nómina a enviar ajustándola
			 * a la forma '<rutEmpresa>.<tipoNomina><convenio>'.
			 */
			nomina = idEmpresa + "." + tipoNomina + idConvenio;
			
            idNomina = AR_getIdNominaElement(n);
            idNomina.value = nomina;

            seleccionadas[ns][0] = nomina;
            seleccionadas[ns++][1] = n;
        }
    }

    if (ns == 0) {
        alert("Seleccione al menos una nómina para realizar el envío.");
        
        return false;
    }
    
    // Verifica la unicidad de las nóminas seleccionadas.
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
     * las nóminas seleccionadas.
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
     * Reporta las nóminas seleccionadas para enviar.
     */
    var report = " Nóminas seleccionadas para enviar (normalizadas):\n";

    for (i = 0; i < idEmpresas.length; i++) {
        idEmpresa = idEmpresas[i];
        nombreEmpresa = AR_getNombreEmpresa(idEmpresa);
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

    report += "\n Total de nóminas seleccionadas para enviar: " + ns + ".\n\n";
    report += "Para proceder con el envío de las nóminas seleccionadas a\n";
    report += "La Araucana C.C.A.F. haga click en el botón 'Aceptar'.\n";
    report += "En caso contrario, haga click en el botón 'Cancelar'.\n\n";
    
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
              "Nómina " + n + ": Extensión de "
            + "'" + name + "' es inválida."
            + "\n\n"
            + "Las nóminas deben tener extensión de la siguiente forma:"
            + "\n\n"
            + "               .<tipoNomina><convenio>\n\n"
            + "<tipoNomina> : Tipo de la nómina. Los tipos válidos son: " + enumTipoNominas + ".\n"
            + "<convenio>   : Número del convenio de cotización. (Siempre con 2 dígitos)\n\n"
            + "        Ejemplos: 70016160." + tipoNominas[0] + "01, 10450366." + tipoNominas[1] +"03\n\n"
            + "Por favor corrija seleccionando una nómina válida para poder continuar.");
}


function AR_notifyAuthorizationError(n, name) {
    alert(
              "Nómina " + n + ": Usted no está autorizado para enviar la nómina "
            + "'" + name + "'."
            + "\n\n"
            + "Por favor corrija seleccionando una nómina autorizada para usted y así "
            + "poder continuar.");
}


function AR_notifyDuplicationError(n, name, duplicatedN) {
    alert(
              "Nómina " + n + ": '" + name + "' está duplicada "
            + "con la nómina " + duplicatedN + ".\n\n"
            + "Por favor corrija seleccionando una sóla vez cada nómina a enviar "
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
