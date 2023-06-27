function cambiaGratificacion()
{
	var montoGrati = new Number(limpiaNumero(document.getElementById('montoGrati').value));
	document.getElementById('remImpPension').value = formatNumero(montoGrati);
	if (!sinMutual)//con mutual
		document.getElementById('rentaImpMutual').value = formatNumero(montoGrati);

	//SALUD
	recalculaSalud();
	//Prevision
	//	tipoPrevision: 1 = AFP, 2 = INP
	var tipoPrevision = 0;
	if (document.getElementById('idEntExCaja').value != -1 && document.getElementById('idRegimenImp').value != -1)
		tipoPrevision = 2;
	else if (document.getElementById('idEntPensionReal').value != idAFPNinguna && document.getElementById('idEntPensionReal').value != idSinAFP)
		tipoPrevision = 1;

	document.getElementById('tipoPrevision').value = tipoPrevision;
	//AFP
	if (tipoPrevision == 1)
	{
		var tasaNormal = new Number(document.getElementById('entAFP-' + document.getElementById('idEntPensionReal').value).value) / 100;
		document.getElementById('prevObligatorioAFP').value = formatNumero(Math.round(montoGrati * tasaNormal));
		document.getElementById('prevObligatorioINP').value = '0';
	} else if (tipoPrevision == 2)//INP
	{
		var tasaCotINP = document.getElementById('codReg-' + document.getElementById('idRegimenImp').value).value;
		//clillo 24-05-16 Sin pagos para IPS
		//document.getElementById('prevObligatorioINP').value = formatNumero(Math.round(montoGrati * tasaCotINP / 100));
		document.getElementById('prevObligatorioINP').value = 0;
		document.getElementById('prevObligatorioAFP').value = '0';
	}
	recalculaTotalAFP();
	//MUTUAL
	if (!sinMutual)//con mutual
		document.getElementById('cotizacionMutual').value = formatNumero(Math.round(montoGrati / 100 * parseFloat(tasaMutual)));

	//INP (pension y fonasa ya se calculo)
	calculaTotales('INP');
}

function recalculaSalud()
{
	var montoGrati = new Number(limpiaNumero(document.getElementById('montoGrati').value));
	var identsal= document.getElementById('idEntSaludReal').value;
	var tasaSalud = new Number(document.getElementById('tasaSalud_' + identsal).value);
	if (document.getElementById('idEntSaludReal').value != idFONASA &&
	    document.getElementById('idEntSaludReal').value != sinEntidadSalud) //ISAPRE
	{
		document.getElementById('saludObligISAPRE').value = formatNumero(Math.round(montoGrati * tasaSalud));
		document.getElementById('saludObligFONASA').value = '0';
		document.getElementById('aporteCaja').value       = '0';
	} if (document.getElementById('idEntSaludReal').value == idFONASA ||
	      document.getElementById('idEntSaludReal').value == sinEntidadSalud) //FONASA O SIN ENTIDAD
	{
		var aporteCaja = creaDecimal(document.getElementById('aporteCCAFFON').value) / 100;
		if (!sinCaja)//con CCAF
		{
			tasaSalud -= aporteCaja;
			document.getElementById('aporteCaja').value = formatNumero(Math.round(montoGrati * aporteCaja));
		}
		document.getElementById('saludObligISAPRE').value = '0';
		if (document.getElementById('fonasaCheck').checked){
			//clillo 24-05-16 Sin pagos para IPS
			//document.getElementById('saludObligFONASA').value = formatNumero(Math.round(montoGrati * tasaSalud));
			document.getElementById('saludObligFONASA').value = 0;
		}
	}
	calculaTotales('INP');
}

function recalculaMutual()
{
	var monto = creaNumero(document.getElementById('rentaImpMutual').value);
	document.getElementById('cotizacionMutual').value = formatNumero(limpiaNumero(Math.round(monto / 100 * parseFloat(tasaMutual)), ''));
}

function rentaImponible(){
	var rentaImponibleSIS = new Number(limpiaNumero(document.getElementById('rentaImponibleSIS').value, ''));
	
	if (rentaImponibleSIS == 0){
		document.getElementById('rentaImponibleSIS').value = new Number(limpiaNumero(document.getElementById('montoGrati').value, ''));
	}

}

function recalculaPrevisionSIS()
{
	if(document.getElementById('tipoProcesoEdicion').value != 'D')
	{
		if (document.getElementById('idEntPensionReal').value != idAFPNinguna && document.getElementById('idEntPensionReal').value != idSinAFP)
		{
			var rentaImponibleSIS = new Number(limpiaNumero(document.getElementById('rentaImponibleSIS').value, ''));
			var previsionSIS = new Number(document.getElementById('porcentSis-' + document.getElementById('idEntPensionReal').value).value) / 100;
			document.getElementById('previsionSIS').value = formatNumero(Math.round(rentaImponibleSIS * previsionSIS));
		}
	}
}

function recalculaTotalAFP()
{/*	idEntPensionReal
	prevObligatorioAFP
		totalAfp
	segCesRemImp
	segCesTrabajador
	segCesEmpresa
		totalAfcDiv
	idTasaTraPesa
	nombreTrabPesado
	montoTrabPesado
		totalPagarAfpDiv*/

	recalculaTipoPrevision();
	if(document.getElementById('idEntPensionReal').value == idAFPNinguna)
	{
		document.getElementById('prevObligatorioAFP').value = 0;
		document.getElementById('segCesRemImp').value = 0;
		document.getElementById('segCesTrabajador').value = 0;
		document.getElementById('segCesEmpresa').value = 0;
		document.getElementById('idTasaTraPesa').value = -1;
		document.getElementById('nombreTrabPesado').value = '';
		document.getElementById('montoTrabPesado').value = 0;			
	}
	var totalAfcDiv        = new Number(0);
	var prevObligatorioAFP = new Number(limpiaNumero(document.getElementById('prevObligatorioAFP').value, ''));
	var segCesRemImp	   = new Number(limpiaNumero(document.getElementById('segCesRemImp').value, ''));
	var segCesTrabajador   = new Number(limpiaNumero(document.getElementById('segCesTrabajador').value, ''));
	var segCesEmpresa      = new Number(limpiaNumero(document.getElementById('segCesEmpresa').value, ''));
	var montoTrabPesado    = new Number(limpiaNumero(document.getElementById('montoTrabPesado').value, ''));
	
	var previsionSIS = new Number(limpiaNumero(document.getElementById('previsionSIS').value, ''));

	if (prevObligatorioAFP > 0)
	{
		document.getElementById('idEntExCaja').value = -1;
		document.getElementById('idRegimenImp').value = -1;

		document.getElementById('tasaCotINP').innerHTML = '0.0 %';	
	}

	if (segCesTrabajador > 0)
		totalAfcDiv += segCesTrabajador;
	if (segCesEmpresa > 0)
		totalAfcDiv += segCesEmpresa;
	
	//csanchez. Solicitado en mantención 25/08/11
	//totalAfcDiv = totalAfcDiv + previsionSIS;

	document.getElementById("totalAfcDiv").innerHTML = '$ ' + formatNumero(totalAfcDiv);
	//csanchez. Solicitado en mantención 22/12/11 (Mantis 0001612 | Agregar SIS).
	//document.getElementById("totalPagarAfpDiv").innerHTML = '$ ' + formatNumero(prevObligatorioAFP + totalAfcDiv + montoTrabPesado);
	document.getElementById("totalPagarAfpDiv").innerHTML = '$ ' + formatNumero(prevObligatorioAFP + totalAfcDiv + montoTrabPesado + previsionSIS);
}

function recalculaINP() 
{
	var rentaImpINP = new Number(limpiaNumero(document.getElementById('remImpPension').value));
	if (document.getElementById('idEntSaludReal').value == idFONASA) //FONASA y aporte CCAF
	{
		var tasaSalud = new Number(document.getElementById('tasaSalud_' + document.getElementById('idEntSaludReal').value).value);
		var aporteCaja = creaDecimal(document.getElementById('aporteCCAFFON').value) / 100;
		if (!sinCaja)//con CCAF
		{
			tasaSalud -= aporteCaja;
			document.getElementById('aporteCaja').value = formatNumero(Math.round(rentaImpINP * aporteCaja));
		}
		document.getElementById('saludObligISAPRE').value = '0';
		//clillo 24-05-16 Sin pagos para IPS
		//document.getElementById('saludObligFONASA').value = formatNumero(Math.round(rentaImpINP * tasaSalud));
		document.getElementById('saludObligFONASA').value = 0;
	}
	var tipoPrevision = 0;
	if (document.getElementById('idEntExCaja').value != -1 && document.getElementById('idRegimenImp').value != -1)
		tipoPrevision = 2;
	else if (document.getElementById('idEntPensionReal').value != idAFPNinguna && document.getElementById('idEntPensionReal').value != idSinAFP)
		tipoPrevision = 1;

	document.getElementById('tipoPrevision').value = tipoPrevision;
	if (tipoPrevision == 2)//INP
	{
		var tasaCotINP = document.getElementById('codReg-' + document.getElementById('idRegimenImp').value).value;
		//clillo 24-05-16 Sin pago para IPS
		//document.getElementById('prevObligatorioINP').value = formatNumero(Math.round(rentaImpINP * tasaCotINP / 100));
		document.getElementById('prevObligatorioINP').value = 0;
		document.getElementById('prevObligatorioAFP').value = '0';
	}

	calculaTotales('INP');
}

function recalculaTotalINP()
{
	calculaTotales('INP');
}

/*function recalculaTotalAFP()
{
	calculaTotales('AFP');
}*/

function calculaTotales(tab)
{
	if (tab == '' || tab == 'AFP')
	{	//AFP
		var prevObligatorioAFP = creaNumero(document.getElementById('prevObligatorioAFP').value);
		var segCesTrabajador = creaNumero(document.getElementById('segCesTrabajador').value);
		var segCesEmpresa = creaNumero(document.getElementById('segCesEmpresa').value);
		var montoTrabPesado = creaNumero(document.getElementById('montoTrabPesado').value);
		var previsionSIS = creaNumero(document.getElementById('previsionSIS').value);
		var totalAFP = new Number(0);
		var totalSeguro = new Number(0);
	
		totalSeguro = segCesEmpresa + segCesTrabajador;
		totalAFP = prevObligatorioAFP + totalSeguro + montoTrabPesado;
		totalAFP = totalAFP + previsionSIS;
		document.getElementById('totalAfcDiv').innerHTML = "$ " + formatNumero(totalSeguro);
		document.getElementById('totalPagarAfpDiv').innerHTML = "$ " + formatNumero(totalAFP);
	}
	if (tab == '' || tab == 'INP')
	{	
		var totalINP = new Number(0);

		var prevObligatorioINP = creaNumero(document.getElementById('prevObligatorioINP').value);
		var saludObligFONASA = creaNumero(document.getElementById('saludObligFONASA').value);
		var accTrabajoINP = creaNumero(document.getElementById('accTrabajoINP').value);
	
		totalINP = prevObligatorioINP + saludObligFONASA + accTrabajoINP;
		document.getElementById("totalCotizINPDiv").innerHTML = '$ ' + formatNumero(totalINP);
		document.getElementById("totalPagarINPDiv").innerHTML = '$ ' + formatNumero(totalINP);
	}
}

function recalculaPensionAFP()
{
	if(document.getElementById('idEntPensionReal').value == idAFPNinguna || document.getElementById('idEntPensionReal').value == idSinAFP)
	{
		document.getElementById('prevObligatorioAFP').value = 0;
		document.getElementById('segCesRemImp').value = 0;
		document.getElementById('segCesTrabajador').value = 0;
		document.getElementById('segCesEmpresa').value = 0;
		document.getElementById('idTasaTraPesa').value = -1;
		document.getElementById('nombreTrabPesado').value = '';
		document.getElementById('montoTrabPesado').value = 0;			
	}
	if(document.getElementById('idEntPensionReal').value == idSinAFP)
		var tasaNormal = 0;
	else
		var tasaNormal = new Number(document.getElementById('entAFP-' + document.getElementById('idEntPensionReal').value).value) / 100;
	var totalAfpDiv = new Number(0);
	var totalAfcDiv = new Number(0);
	var prevObligatorioAFP = Math.round(new Number(limpiaNumero(document.getElementById('montoGrati').value, '')) * tasaNormal);
	var segCesRemImp = new Number(limpiaNumero(document.getElementById('segCesRemImp').value, ''));
	var segCesTrabajador = new Number(limpiaNumero(document.getElementById('segCesTrabajador').value, ''));
	var segCesEmpresa = new Number(limpiaNumero(document.getElementById('segCesEmpresa').value, ''));
	var montoTrabPesado = new Number(limpiaNumero(document.getElementById('montoTrabPesado').value, ''));
	var previsionSIS = new Number(limpiaNumero(document.getElementById('previsionSIS').value, ''));
	document.getElementById('prevObligatorioAFP').value = formatNumero(prevObligatorioAFP);

	if (prevObligatorioAFP > 0)
	{
		document.getElementById('idEntExCaja').value = -1;
		document.getElementById('idRegimenImp').value = -1;

		document.getElementById('tasaCotINP').innerHTML = '0.0 %';		
		totalAfpDiv += prevObligatorioAFP;
	}

	if (segCesTrabajador > 0)
		totalAfcDiv += segCesTrabajador;
	if (segCesEmpresa > 0)
		totalAfcDiv += segCesEmpresa;
		
	document.getElementById("totalAfcDiv").innerHTML = '$ ' + formatNumero(totalAfcDiv);
	
	document.getElementById("idEntAFCReal").value = document.getElementById('idEntPensionReal').value;
	document.getElementById("idEntAFCReal").disabled = true;

	document.getElementById("totalPagarAfpDiv").innerHTML = '$ ' + formatNumero(totalAfpDiv + totalAfcDiv + montoTrabPesado);
	
	if(document.getElementById('idEntPensionReal').value != idAFPNinguna && document.getElementById('idEntPensionReal').value != idSinAFP){
		recalculaPrevisionSIS();
	}
}

function validaFormGrati()
{
	if (document.getElementById("botonGuardar").value == "1") //para que el boton se apriete solo una vez
		return false;

	document.getElementById('fechaIni').value = document.getElementById('fechaIniGrat').value;
	document.getElementById('fechaFin').value = document.getElementById('fechaFinGrati').value;
//campos formulario:
	//basicos
		//newRutTrabajador
		//newNombre
		//apellidoPat
		//apellidoMat
		//idGenero
		//idSucursal
	//texto
		//nombreTrabPesado		--
	//lista
		//idEntPensionReal		--
		//idEntSaludReal		--
		//idEntExCaja			--
		//idRegimenImp			--
		//idTramo				--
		//idTramoINP			--
		//idTasaTraPesa			--
	//numeros
		//cargasSimples			--
		//cargasSimplesINP		--
		//cargasMaternales		--
		//cargasMaternalesINP	--
		//cargasInvalidez		--
		//cargasInvalidezINP	--
	//montos
		//saludObligISAPRE		--
		//saludObligFONASA		--
		//prevObligatorioAFP	--
		//prevObligatorioINP	--
		//segCesRemImp			--
		//segCesTrabajador		--
		//segCesEmpresa			--
		//montoTrabPesado		--
		//cotizacionMutual		--
		//accTrabajoINP			--
		//rentaImpMutual		--
		//aporteCaja			--
		//montoGrati			--
	//fechas (ya estan restringidas, no se validan)
		//fechaIniGrat			--
		//fechaFinGrati			--
	var msg = validaDataBasicaForm();
	var alertaPrevision = "";

//DETALLES
	if (!validaNumero(document.getElementById('montoGrati').value, ''))
		msg += " - Monto para Gratificación inválido.\n";
	var montoGrati = creaNumero(document.getElementById('montoGrati').value);
	if (montoGrati < 0)
		msg += " - Monto para Gratificación debe ser positivo.\n";

	if (document.getElementById("fechaIniGrat").value == '')
		msg += " - Debe seleccionar una Fecha de Inicio para la Gratificación. \n";
	else if (!validarFecha(document.getElementById("fechaIniGrat").value))
		msg += " - Fecha inicio inválida. \n";
	if (document.getElementById("fechaFinGrati").value == '')
		msg += " - Debe seleccionar una Fecha de término para la Gratificación. \n";
	else if (!validarFecha(document.getElementById("fechaFinGrati").value))
		msg += " - Fecha término inválida. \n";

//SALUD
	if (!validaNumero(document.getElementById('saludObligFONASA').value, ''))
		msg += " - Monto Cotización FONASA inválido.\n";
	if (!validaNumero(document.getElementById('saludObligISAPRE').value, ''))
		msg += " - Monto Cotización ISAPRE inválido.\n";
	var saludObligFONASA = creaNumero(document.getElementById('saludObligFONASA').value);
	var saludObligISAPRE = creaNumero(document.getElementById('saludObligISAPRE').value);
	if (saludObligFONASA > 0 && saludObligISAPRE > 0)
		msg += " - Salud posee valores tanto para ISAPRE como para FONASA. Sólo se permite uno de los dos.\n";
//	else if (saludObligFONASA <= 0 && saludObligISAPRE <= 0   && montoGrati > 0)
// marco 	
//		msg += " - Salud debe poseer valor para ISAPRE ó FONASA.\n";
	else
	{
		if (saludObligISAPRE > 0)
		{
			if (document.getElementById('idEntSaludReal').value == -1)
				msg += " - Debe seleccionar una Entidad para ISAPRE. \n";
		}
	}

//PREVISION
	if (!validaNumero(document.getElementById('prevObligatorioAFP').value, ''))
		msg += " - Monto Cotización Obligatoria de Previsión AFP inválido.\n";
	if (!validaNumero(document.getElementById('prevObligatorioINP').value, ''))
		msg += " - Monto Cotización Obligatoria de Previsión INP inválido.\n";
	var prevObligatorioAFP = creaNumero(document.getElementById('prevObligatorioAFP').value);
	var prevObligatorioINP = creaNumero(document.getElementById('prevObligatorioINP').value);
	var tipoPrevision = "ninguna";
	if (prevObligatorioAFP > 0 && prevObligatorioINP > 0)
		msg += " - Monto Previsión posee valores tanto para AFP como para INP. Sólo se permite uno de los dos.\n";
	else if (prevObligatorioAFP < 0 && prevObligatorioINP < 0)
		msg += " - Monto Previsión debe poseer valor para AFP ó INP.\n";
	else
	{
		if (prevObligatorioAFP == 0 && prevObligatorioINP == 0)
			alertaPrevision = " - Trabajador no cuenta con Cotización en Monto Previsión, ¿Esta seguro que desea continuar?\n";
		if (prevObligatorioAFP > 0)//AFP
		{
			document.getElementById('tipoPrevision').value = 1;
			document.getElementById('idEntExCaja').value = -1;
			document.getElementById('idRegimenImp').value = -1;
			if (document.getElementById('idEntPensionReal').value == -1)
				msg += " - Debe seleccionar una Entidad para Sistema Previsional. \n";
			tipoPrevision = "AFP";
		} else
		{
			if ( ((document.getElementById('idEntPensionReal').value == '-1' || document.getElementById('idEntPensionReal').value == '-100' ) &&
				  (document.getElementById('idEntAFCReal').value == '-1' || document.getElementById('idEntAFCReal').value == '-100' )) &&
			     (document.getElementById('segCesTrabajador').value > 0 || document.getElementById('segCesEmpresa').value > 0  ))
					msg += " - Debe seleccionar una Entidad para Seguro de Cesantía. \n";
			if (prevObligatorioINP > 0) //INP
			{
				document.getElementById('tipoPrevision').value = 2;
				tipoPrevision = "INP";
			if (document.getElementById('idEntExCaja').value == -1)
				msg += " - Debe seleccionar una Entidad Ex Caja para Sistema Previsional INP. \n";
			if (document.getElementById('idRegimenImp').value == -1)
				msg += " - Debe seleccionar un Código de Regimen Impositivo para Sistema Previsional INP. \n";
			}
		}
	}

// elimina validación seguro cesantia marco 
/*
	if (!validaNumero(document.getElementById('segCesRemImp').value, ''))
		msg += " - Monto Remuneración Imponible para Seguro de Cesantía inválido.\n";
	if (!validaNumero(document.getElementById('segCesTrabajador').value, ''))
		msg += " - Monto Aporte Trabajador para Seguro de Cesantía inválido.\n";
	if (!validaNumero(document.getElementById('segCesEmpresa').value, ''))
		msg += " - Monto Aporte Empleador para Seguro de Cesantía inválido.\n";
	var segCesRemImp = creaNumero(document.getElementById('segCesRemImp').value);
	var segCesRemTope = creaNumero(document.getElementById('topeCesantia').value);
	var _segCesRemTope = Math.round(document.getElementById('topeCesantia').value);
	var segCesTrabajador = creaNumero(document.getElementById('segCesTrabajador').value);
	var segCesEmpresa = creaNumero(document.getElementById('segCesEmpresa').value);
	var sciTrabCalculado = calcula(segCesRemImp, document.getElementById('apTrabIndSegCes').value);
	var scpfTrabCalculado = calcula(segCesRemImp, document.getElementById('apTrabPFSegCes').value);
	var sciEmpCalculado = calcula(segCesRemImp, document.getElementById('apEmpIndSegCes').value);
	var scpfEmpCalculado = calcula(segCesRemImp, document.getElementById('apEmpPFSegCes').value);
	if (segCesRemImp < 0)
		msg += " - Debe ingresar un monto para Remuneración Imponible Seguro de Cesantía. \n";
	if (segCesRemImp > _segCesRemTope)
		msg += " - Monto para Remuneración Imponible Seguro de Cesantía mayor al tope (" + _segCesRemTope + "). \n";
	if ((segCesTrabajador == sciTrabCalculado && segCesEmpresa == sciEmpCalculado) 
		|| (segCesTrabajador == scpfTrabCalculado && segCesEmpresa == scpfEmpCalculado))
	{
		//OK
	} else
	{
		msg += " - Montos aportes Trabajador/Empresa no corresponden a valores posibles: \n";
		msg += "   Contrato Indefinido  Trabajador= " + Math.round(sciTrabCalculado) + ", Empresa= " + Math.round(sciEmpCalculado) + ",\n";
		msg += "   Contrato Plazo Fijo  Trabajador= " + Math.round(scpfTrabCalculado) + ", Empresa= " + Math.round(scpfEmpCalculado) + ".\n";
	}
*/

	if (!validaNumero(document.getElementById('montoTrabPesado').value, ''))
		msg += " - Monto Trabajo Pesado inválido.\n";
	var montoTrabPesado = creaNumero(document.getElementById('montoTrabPesado').value);
	var tieneTP = 0;
	if (montoTrabPesado > 0 || document.getElementById('idTasaTraPesa').value != -1 || document.getElementById('nombreTrabPesado').value != '')
		tieneTP = 1;
	
	if (tipoPrevision == "AFP")
	{
		if (tieneTP == 1)
		{
			if (document.getElementById('idTasaTraPesa').value == -1)
				msg += " - Debe seleccionar una Tasa para Trabajo Pesado. \n";
			if (document.getElementById('nombreTrabPesado').value == '')
				msg += " - Debe ingresar un nombre para Trabajo Pesado. \n";
			if(montoTrabPesado > 0 && document.getElementById('idTasaTraPesa').value != -1 && montoGrati > 0)
			{
				if(Math.round(montoGrati * document.getElementById('idTasaTraPesa').value / 100) != montoTrabPesado)
					msg += " - Monto Trabajo Pesado debe ser " + Math.round(montoGrati * document.getElementById('idTasaTraPesa').value / 100) + ". \n";
			}
		}
	} else if (tipoPrevision == "INP" && tieneTP == 1)
		msg += " - Cotizante es afiliado a INP, por lo que no debe cotizar Trabajo Pesado. \n";

//CAJA
	if (!sinCaja)//con caja
	{
		if (document.getElementById('idTramo').value == -1)
			//csanchez. Se replican las reglas de Remuneración
/*			msg += " - Debe seleccionar un Tramo de Asignación Familiar. \n";
		else */
		{
			var cargasSimples = creaNumero(document.getElementById('cargasSimples').value);
			var cargasMaternales = creaNumero(document.getElementById('cargasMaternales').value);
			var cargasInvalidez = creaNumero(document.getElementById('cargasInvalidez').value);
			var tieneCargas = (cargasSimples > 0 || cargasMaternales > 0 || cargasInvalidez > 0  ? true : false);
			if (!validaNumero(document.getElementById('cargasSimples').value) || cargasSimples < 0)
				msg += " - Número de cargas Simples inválido. \n";
			if (!validaNumero(document.getElementById('cargasMaternales').value) || cargasMaternales < 0)
				msg += " - Número de cargas Maternales inválido. \n";
			if (!validaNumero(document.getElementById('cargasInvalidez').value) || cargasInvalidez < 0)
				msg += " - Número de cargas de Invalidez inválido. \n";
			if (document.getElementById('idTramo').value == tramoASigFamNinguno && tieneCargas)
				msg += " - No corresponde número de cargas familiares, si no selecciona un Tramo de Asignación Familiar. \n";
		}

		if (document.getElementById('idEntSaludReal').value == idFONASA)
		{
			if (!validaNumero(document.getElementById('aporteCaja').value, ''))
				msg += " - Monto Aporte CCAF inválido. \n";
			else if (creaNumero(document.getElementById('aporteCaja').value) < 0)
				msg += " - Monto Aporte CCAF incorrecto (" + monto + "). \n";
			else
			{
				var aportePorcentajeCaja = creaDecimal(document.getElementById('aporteCCAFFON').value);
				var remImpPension = creaNumero(document.getElementById('remImpPension').value);
				var aporteCaja = creaNumero(document.getElementById('aporteCaja').value);
				var calculadoCaja = calcula(remImpPension / 100, aportePorcentajeCaja);
				if (aporteCaja != calculadoCaja)
					msg += " - Monto Aporte CCAF incorrecto (" + calculadoCaja + "). \n";
			}
		} else if (document.getElementById('idEntSaludReal').value != idFONASA && creaNumero(document.getElementById('aporteCaja').value) > 0 && !document.getElementById("fonasaCheck").checked)
			msg += " - Monto Aporte CCAF no debe aparecer si no es afiliado a FONASA. \n";
	}
//MUTUAL
	if (!sinMutual) //con mutual
	{
		monto = creaNumero(document.getElementById('rentaImpMutual').value);
		if (!validaNumero(document.getElementById('rentaImpMutual').value, '') || monto < 0)
			msg += " - Monto Renta Imponible MUTUAL inválido. \n";
		if (!validaNumero(document.getElementById('cotizacionMutual').value, ''))
			msg += " - Monto Cotización MUTUAL inválido.\n";
	}

//INP
//remImpPension
	if (!validaNumero(document.getElementById('remImpPension').value) || creaNumero(document.getElementById('remImpPension').value) < 0)
		msg += " - Monto Remuneración Imponible INP inválido. \n";
	if (sinMutual)
		if (!validaNumero(document.getElementById('accTrabajoINP').value, '') || creaNumero(document.getElementById('accTrabajoINP').value) < 0)
			msg += " - Monto Accidentes del Trabajo inválido. \n";
	if (sinCaja)
	{
		//csanchez. Se replican las reglas de Remuneración.
		//if (document.getElementById('idTramoINP').value == -1)
			//msg += " - Debe seleccionar un Tramo de Asignación Familiar. \n";
		if (!validaNumero(document.getElementById('cargasSimplesINP').value) || creaNumero(document.getElementById('cargasSimplesINP').value) < 0)
			msg += " - Número de cargas Simples inválido. \n";
		if (!validaNumero(document.getElementById('cargasMaternalesINP').value) ||creaNumero(document.getElementById('cargasMaternalesINP').value) < 0)
			msg += " - Número de cargas Maternales inválido. \n";
		if (!validaNumero(document.getElementById('cargasInvalidezINP').value) || creaNumero(document.getElementById('cargasInvalidezINP').value) < 0)
			msg += " - Número de cargas de Invalidez inválido. \n";
	}
	if (msg != '')
	{
		alert(msg);
		return false;
	} else if (alertaPrevision != '')
	{
		if (confirm(alertaPrevision))
		{
			document.getElementById('tipoPrevision').value = 0;			
			document.getElementById("botonGuardar").value = "1";
			return true;
		} else 
			return false;
	}
	
	document.getElementById("botonGuardar").value = "1";
	return true;
}

function limpiaAFP()
{
	document.getElementById('idEntPensionReal').value = idAFPNinguna;
	document.getElementById('prevObligatorioAFP').value = 0;
	document.getElementById('segCesRemImp').value = 0;
	document.getElementById('segCesTrabajador').value = 0;
	document.getElementById('segCesEmpresa').value = 0;
	document.getElementById('idTasaTraPesa').value = '-1';
	document.getElementById('nombreTrabPesado').value = '';
	document.getElementById('montoTrabPesado').value = 0;
	document.getElementById('rentaImponibleSIS').value = 0;
	document.getElementById('segCesRemImp').value = 0;
	document.getElementById('previsionSIS').value = 0;
	document.getElementById('totalAfcDiv').innerHTML = '$ 0';	
	document.getElementById('totalPagarAfpDiv').innerHTML = '$ 0';		
	
	document.getElementById("idEntAFCReal").disabled = false;	
}