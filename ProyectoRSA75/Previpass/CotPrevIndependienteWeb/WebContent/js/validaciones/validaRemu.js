/*
	reCalcular si aplica:
		- ISAPRE 
		- AFP 
		- INP 
		- MUTUAL 
		- aporte CCAF
*/
function cambiaRentaImp()
{
	var rentaImp = new Number(limpiaNumero(document.getElementById('rentaImp').value));
	if (document.getElementById('idEntExCaja').value != -1) {
		document.getElementById('remImpPension').value = formatNumero(rentaImp);
	}
// MARCO actualiza la renta imponible mutual no importa si empresa no tiene, monto es utilizado para el cálculo del accidente trabajo INP	
//	if (!sinMutual)//con mutual
		document.getElementById('rentaImpMutual').value = formatNumero(rentaImp);

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
		var tasaSIS = 0;
		if (document.getElementById('datosSis').value == 'false') {
			tasaSIS = new Number(document.getElementById('porcentSis-' + document.getElementById('idEntPensionReal').value).value);
		}
		var tasaAFP = new Number(document.getElementById('entAFP-' + document.getElementById('idEntPensionReal').value).value);
		var tasaNormal = (tasaAFP + tasaSIS) / 100;
		document.getElementById('prevObligatorioAFP').value = formatNumero(Math.round(rentaImp * tasaNormal));
		document.getElementById('prevObligatorioINP').value = '0';
	} else if (tipoPrevision == 2)//INP
	{
		var tasaCotINP = document.getElementById('codReg-' + document.getElementById('idRegimenImp').value).value;
		//clillo 24-05-16 Sin pagos para IPS
		//document.getElementById('prevObligatorioINP').value = formatNumero(Math.round(rentaImp * tasaCotINP / 100));
		document.getElementById('prevObligatorioINP').value = 0;
		document.getElementById('prevObligatorioAFP').value = '0';
	}
	recalculaTotalAFP();
	//MUTUAL
	if (!sinMutual)//con mutual
		document.getElementById('cotizacionMutual').value = formatNumero(Math.round(rentaImp / 100 * parseFloat(tasaMutual)));

	//INP (pension y fonasa ya se calculó)
	recalculaTotalINP();
	

	
	
	if(document.getElementById('fonasaCheck').checked){
	
		var tasaSalud = new Number(document.getElementById('tasaSalud_' + document.getElementById('idEntSaludReal').value).value);
		document.getElementById('saludObligFONASA').value = formatNumero(Math.round(rentaImp * tasaSalud));
		document.getElementById('remImpPension').value = formatNumero(rentaImp);
		
		var totalCotizINPDiv = new Number(0);
		var totalAsignFamDivINP = new Number(0);
		var remImpPension = new Number(limpiaNumero(document.getElementById('remImpPension').value, ''));
		var prevObligatorioINP = new Number(limpiaNumero(document.getElementById('prevObligatorioINP').value, ''));
		var accTrabajoINP = new Number(limpiaNumero(document.getElementById('accTrabajoINP').value, ''));
		var inpDesahucio = new Number(limpiaNumero(document.getElementById('inpDesahucio').value, ''));
		var saludObligFONASA = new Number(limpiaNumero(document.getElementById('saludObligFONASA').value, ''));
		var accTrabajoINP = new Number(limpiaNumero(document.getElementById('accTrabajoINP').value, ''));
		var inpDesahucio = new Number(limpiaNumero(document.getElementById('inpDesahucio').value, ''));
		var asigFamiliarINP = new Number(limpiaNumero(document.getElementById('asigFamiliarINP').value, ''));
		var asigFamRetroINP = new Number(limpiaNumero(document.getElementById('asigFamRetroINP').value, ''));
		var reintegroAsigFamINP = new Number(limpiaNumero(document.getElementById('reintegroAsigFamINP').value, ''));
		var inpBonificacion = new Number(limpiaNumero(document.getElementById('inpBonificacion').value, ''));
		
		
		if (prevObligatorioINP > 0)
			totalCotizINPDiv += prevObligatorioINP;
	
		if (saludObligFONASA > 0)
			totalCotizINPDiv += saludObligFONASA;
	
		if (accTrabajoINP > 0)
			totalCotizINPDiv += accTrabajoINP;
		if (inpDesahucio > 0)
			totalCotizINPDiv += inpDesahucio;
		document.getElementById("totalCotizINPDiv").innerHTML = '$ ' + formatNumero(totalCotizINPDiv);
					
		if (sinCaja)//sin caja
	{
		if (asigFamiliarINP > 0)
			totalAsignFamDivINP += asigFamiliarINP;
		if (asigFamRetroINP > 0)
			totalAsignFamDivINP += asigFamRetroINP;
		if (reintegroAsigFamINP > 0)
			totalAsignFamDivINP -= reintegroAsigFamINP;
		document.getElementById("totalAsignFamDivINP").innerHTML = '$ ' + formatNumero(totalAsignFamDivINP);
		document.getElementById('totASigFamHiddINP').value = totalAsignFamDivINP;
	}
	
	if (inpBonificacion < 0)
		inpBonificacion = 0;
document.getElementById("totalPagarINPDiv").innerHTML = '$ ' + formatNumero(totalCotizINPDiv - (totalAsignFamDivINP + inpBonificacion));			
					
					
	}
}

function cambiaRentaImpAfp()
{
	var rentaImp = new Number(limpiaNumero(document.getElementById('rentaImp').value));
	if (document.getElementById('idEntExCaja').value != -1) {
		document.getElementById('remImpPension').value = formatNumero(rentaImp);
	}
// MARCO actualiza la renta imponible mutual no importa si empresa no tiene, monto es utilizado para el cálculo del accidente trabajo INP	
//	if (!sinMutual)//con mutual
		document.getElementById('rentaImpMutual').value = formatNumero(rentaImp);

	//SALUD
	recalculaSaludAfp();
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
		var tasaSIS = 0;
		if (document.getElementById('datosSis').value == 'false') {
			tasaSIS = new Number(document.getElementById('porcentSis-' + document.getElementById('idEntPensionReal').value).value);
		}
		var tasaAFP = new Number(document.getElementById('entAFP-' + document.getElementById('idEntPensionReal').value).value);
		var tasaNormal = (tasaAFP + tasaSIS) / 100;
		document.getElementById('prevObligatorioAFP').value = formatNumero(Math.round(rentaImp * tasaNormal));
		document.getElementById('prevObligatorioINP').value = '0';
	} else if (tipoPrevision == 2)//INP
	{
		var tasaCotINP = document.getElementById('codReg-' + document.getElementById('idRegimenImp').value).value;
		document.getElementById('prevObligatorioINP').value = formatNumero(Math.round(rentaImp * tasaCotINP / 100));
		document.getElementById('prevObligatorioAFP').value = '0';
	}
	recalculaTotalAFP();
	//MUTUAL
	if (!sinMutual)//con mutual
		document.getElementById('cotizacionMutual').value = formatNumero(Math.round(rentaImp / 100 * parseFloat(tasaMutual)));

	//INP (pension y fonasa ya se calculó)
	recalculaTotalINP();
}

function recalculaSalud()
{
	var tasaSalud = new Number(document.getElementById('tasaSalud_' + document.getElementById('idEntSaludReal').value).value);
	if (document.getElementById('idEntSaludReal').value != idFONASA &&
	    document.getElementById('idEntSaludReal').value != sinEntidadSalud) //ISAPRE
	{
		var rentaImp = new Number(limpiaNumero(document.getElementById('rentaImp').value));
		document.getElementById('saludObligISAPRE').value = formatNumero(Math.round(rentaImp * tasaSalud));
		document.getElementById('saludObligFONASA').value = '0';
		//document.getElementById('aporteCaja').value       = '0';
	}

	if (document.getElementById('idEntSaludReal').value == idFONASA || document.getElementById('idEntSaludReal').value == sinEntidadSalud)//FONASA O SIN ENTIDAD
	{
		var rentaImpINP = new Number(limpiaNumero(document.getElementById('remImpPension').value));
		var aporteCaja = creaDecimal(document.getElementById('aporteCCAFFON').value) / 100;
		if (!sinCaja)//con CCAF
		{
			//tasaSalud -= aporteCaja;
			if (document.getElementById('fonasaCheck').checked)
				var r="tem";
				//document.getElementById('aporteCaja').value = formatNumero(Math.round(rentaImpINP * aporteCaja));
		}
		document.getElementById('saludObligISAPRE').value = '0';
		if (document.getElementById('fonasaCheck').checked) {
			//clillo 24-05-16 Sin pagos para IPS
			//document.getElementById('saludObligFONASA').value = formatNumero(Math.round(rentaImpINP * tasaSalud));
			document.getElementById('saludObligFONASA').value = 0;
		}
	}
	recalculaTotalSalud();
}

function recalculaSaludAfp()
{
	var tasaSalud = new Number(document.getElementById('tasaSalud_' + document.getElementById('idEntSaludReal').value).value);
	if (document.getElementById('idEntSaludReal').value != idFONASA &&
	    document.getElementById('idEntSaludReal').value != sinEntidadSalud) //ISAPRE
	{
		var rentaImp = new Number(limpiaNumero(document.getElementById('rentaImp').value));
		document.getElementById('saludObligISAPRE').value = formatNumero(Math.round(rentaImp * tasaSalud));
		document.getElementById('saludObligFONASA').value = '0';
		//document.getElementById('aporteCaja').value       = '0';
	}
	if (document.getElementById('idEntSaludReal').value == idFONASA || document.getElementById('idEntSaludReal').value == sinEntidadSalud)//FONASA O SIN ENTIDAD
	{
		var rentaImpINP = new Number(limpiaNumero(document.getElementById('remImpPension').value));
		var aporteCaja = creaDecimal(document.getElementById('aporteCCAFFON').value) / 100;
		if (!sinCaja)//con CCAF
		{
			tasaSalud -= aporteCaja;
			if (document.getElementById('fonasaCheck').checked)
				var r="tem";
				//document.getElementById('aporteCaja').value = formatNumero(Math.round(rentaImpINP * aporteCaja));
		}
		document.getElementById('saludObligISAPRE').value = '0';
		//if (document.getElementById('fonasaCheck').checked) {
		//	document.getElementById('saludObligFONASA').value = formatNumero(Math.round(rentaImpINP * tasaSalud));
		//}		
	
	}
	recalculaTotalSalud();
}

function recalculaTotalSalud()
{
	var total = new Number(0);

	if(document.getElementById('idEntSaludReal').value == idFONASA)
	{
		document.getElementById("totalCotiSaludDiv").innerHTML = '$0';//total
		document.getElementById("totalSaludDiv").innerHTML     = '$0';//pactada
			
		document.getElementById("saludPactado").value = 0;
		document.getElementById("saludTotal").value   = 0;
		return;
	}

	var saludObligISAPRE = new Number(limpiaNumero(document.getElementById('saludObligISAPRE').value, ''));
	var saludAdicional   = new Number(limpiaNumero(document.getElementById('saludAdicional').value,   ''));

	total = saludObligISAPRE + saludAdicional;

	document.getElementById("totalSaludDiv").innerHTML     = '$ ' + formatNumero(total);//pactada
	document.getElementById("totalCotiSaludDiv").innerHTML = '$ ' + formatNumero(total);//total

	document.getElementById("saludPactado").value = total;		
	document.getElementById("saludTotal").value   = total;
}

function recalculaINP()
{
	var rentaImpINP = new Number(limpiaNumero(document.getElementById('remImpPension').value));
	if (document.getElementById('idEntSaludReal').value == idFONASA) //FONASA y aporte CCAF
	{
		var aporteCaja = creaDecimal(document.getElementById('aporteCCAFFON').value) / 100;
		var tasaSalud = new Number(document.getElementById('tasaSalud_' + document.getElementById('idEntSaludReal').value).value);
		if (!sinCaja)//con CCAF
		{
			tasaSalud -= aporteCaja;
			//document.getElementById('aporteCaja').value = formatNumero(Math.round(rentaImpINP * aporteCaja));
		}
		document.getElementById('saludObligISAPRE').value = '0';
		//clillo 24-05-16 Sin pago para IPS
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
	//INP (pension y fonasa ya se calculo)
	recalculaTotalINP();
}

function recalculaTotalINP()
{
/*	idEntExCaja
	idRegimenImp
		tasaCotINP
	remImpPension
	prevObligatorioINP
	saludObligFONASA
	accTrabajoINP
	inpDesahucio
		totalCotizINPDiv
	idTramoINP
	cargasSimplesINP
	cargasMaternalesINP
	cargasInvalidezINP
	asigFamiliarINP
	asigFamRetroINP
	reintegroAsigFamINP
		totalAsignFamDivINP
		totASigFamHiddINP
	inpBonificacion
		totalRebajasINPDiv
		totalPagarINPDiv
*/
	// tipoPrevision = 1 AFP, 2 INP, 0 Ninguno
	recalculaTipoPrevision();
	var totalCotizINPDiv = new Number(0);
	var totalAsignFamDivINP = new Number(0);
	var remImpPension = new Number(limpiaNumero(document.getElementById('remImpPension').value, ''));
	var prevObligatorioINP = new Number(limpiaNumero(document.getElementById('prevObligatorioINP').value, ''));
	var saludObligFONASA = new Number(limpiaNumero(document.getElementById('saludObligFONASA').value, ''));
	var accTrabajoINP = new Number(limpiaNumero(document.getElementById('accTrabajoINP').value, ''));
	var inpDesahucio = new Number(limpiaNumero(document.getElementById('inpDesahucio').value, ''));
	var asigFamiliarINP = new Number(limpiaNumero(document.getElementById('asigFamiliarINP').value, ''));
	var asigFamRetroINP = new Number(limpiaNumero(document.getElementById('asigFamRetroINP').value, ''));
	var reintegroAsigFamINP = new Number(limpiaNumero(document.getElementById('reintegroAsigFamINP').value, ''));
	var inpBonificacion = new Number(limpiaNumero(document.getElementById('inpBonificacion').value, ''));

	if (prevObligatorioINP > 0)
		totalCotizINPDiv += prevObligatorioINP;

	if (saludObligFONASA > 0)
		totalCotizINPDiv += saludObligFONASA;

	if (accTrabajoINP > 0)
		totalCotizINPDiv += accTrabajoINP;
	if (inpDesahucio > 0)
		totalCotizINPDiv += inpDesahucio;
	document.getElementById("totalCotizINPDiv").innerHTML = '$ ' + formatNumero(totalCotizINPDiv);

	if (sinCaja)//sin caja
	{
		if (asigFamiliarINP > 0)
			totalAsignFamDivINP += asigFamiliarINP;
		if (asigFamRetroINP > 0)
			totalAsignFamDivINP += asigFamRetroINP;
		if (reintegroAsigFamINP > 0)
			totalAsignFamDivINP -= reintegroAsigFamINP;
		document.getElementById("totalAsignFamDivINP").innerHTML = '$ ' + formatNumero(totalAsignFamDivINP);
		document.getElementById('totASigFamHiddINP').value = totalAsignFamDivINP;
	}

	if (inpBonificacion < 0)
		inpBonificacion = 0;
	document.getElementById("totalRebajasINPDiv").innerHTML = '$ ' + formatNumero(totalAsignFamDivINP + inpBonificacion);	
	document.getElementById("totalPagarINPDiv").innerHTML = '$ ' + formatNumero(totalCotizINPDiv - (totalAsignFamDivINP + inpBonificacion));
}

function rentaImponible(){
	var rentaImponibleSIS = new Number(limpiaNumero(document.getElementById('rentaImponibleSIS').value, ''));
	
	if (rentaImponibleSIS == 0 && document.getElementById('datosSis').value == 'true' && document.getElementById('idEntPensionReal').value != -1){
		document.getElementById('rentaImponibleSIS').value = formatNumero(new Number(limpiaNumero(document.getElementById('rentaImp').value, '')));
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
	afpAhorro
		totalAfpDiv
		totalAfp
	segCesRemImp
	segCesTrabajador
	segCesEmpresa
		totalAfcDiv
	idTasaTraPesa
	nombreTrabPesado
	montoTrabPesado
		totalPagarAfpDiv*/

	if(document.getElementById('idEntPensionReal').value == idAFPNinguna)
	{
		document.getElementById('prevObligatorioAFP').value	= 0;
		document.getElementById('afpAhorro').value			= 0;	
		document.getElementById('idTasaTraPesa').value		= -1;
		document.getElementById('nombreTrabPesado').value	= '';
		document.getElementById('montoTrabPesado').value	= 0;

		//Permite que, existiendo una INP, los valores del seguro de cesantía no se borren.
		if(document.getElementById('idEntExCaja').value == '-1')
		{
			document.getElementById('segCesRemImp').value		= 0;
			document.getElementById('segCesTrabajador').value	= 0;
			document.getElementById('segCesEmpresa').value		= 0;
		}
	}
	var totalAfpDiv = new Number(0);
	var totalAfcDiv = new Number(0);
	var prevObligatorioAFP = new Number(limpiaNumero(document.getElementById('prevObligatorioAFP').value, ''));
	var afpAhorro = new Number(limpiaNumero(document.getElementById('afpAhorro').value, ''));
	var segCesRemImp = new Number(limpiaNumero(document.getElementById('segCesRemImp').value, ''));
	var segCesTrabajador = new Number(limpiaNumero(document.getElementById('segCesTrabajador').value, ''));
	var segCesEmpresa = new Number(limpiaNumero(document.getElementById('segCesEmpresa').value, ''));
	var montoTrabPesado = new Number(limpiaNumero(document.getElementById('montoTrabPesado').value, ''));
	var previsionSIS = new Number(limpiaNumero(document.getElementById('previsionSIS').value, ''));

	if (prevObligatorioAFP > 0 || document.getElementById('idEntPensionReal').value == '-100')
	{
		document.getElementById('idEntExCaja').value = -1;
		document.getElementById('idRegimenImp').value = -1;
		document.getElementById('prevObligatorioINP').value = '0';
		document.getElementById('remImpPension').value = '0';		

		document.getElementById('tasaCotINP').innerHTML = '0.0 %';
		recalculaTotalINP();
		totalAfpDiv += prevObligatorioAFP;
	}
	if (afpAhorro > 0)
		totalAfpDiv += afpAhorro;
		
	totalAfpDiv = totalAfpDiv + previsionSIS;
	document.getElementById("totalAfp").value = totalAfpDiv;
	document.getElementById("totalAfpDiv").innerHTML = '$ ' + formatNumero(totalAfpDiv);

	if (segCesTrabajador > 0)
		totalAfcDiv += segCesTrabajador;
	if (segCesEmpresa > 0)
		totalAfcDiv += segCesEmpresa;
	document.getElementById("totalAfcDiv").innerHTML = '$ ' + formatNumero(totalAfcDiv);
	document.getElementById("totalPagarAfpDiv").innerHTML = '$ ' + formatNumero(totalAfpDiv + totalAfcDiv + montoTrabPesado);
}

function recalculaCaja()
{/*
	aporteCaja
	idTramo
	cargasSimples
	cargasMaternales
	cargasInvalidez
	asigFam
	asigFamRetro
	reintegroAsigFam
		totalAsignFamDiv
	creditoPersonal
	leasing
	convenioDental
	seguro*/
	var totalAsignFamDiv = new Number(0);
	var asigFam = new Number(limpiaNumero(document.getElementById('asigFam').value, ''));
	var asigFamRetro = new Number(limpiaNumero(document.getElementById('asigFamRetro').value, ''));
	var reintegroAsigFam = new Number(limpiaNumero(document.getElementById('reintegroAsigFam').value, ''));

	if (asigFam > 0)
		totalAsignFamDiv += asigFam;
	if (asigFamRetro > 0)
		totalAsignFamDiv += asigFamRetro;
	if (reintegroAsigFam > 0)
		totalAsignFamDiv -= reintegroAsigFam;
	document.getElementById("totalAsignFamDiv").innerHTML = '$ ' + formatNumero(totalAsignFamDiv);
}

function recalculaAPVC()
{
	var aporteTraAPVC = creaNumero(document.getElementById('aporteTraAPVC').value);
	var aporteEmpAPVC = creaNumero(document.getElementById('aporteEmpAPVC').value);
	var total = new Number();

	if (aporteTraAPVC > 0)
		total += aporteTraAPVC;
	if (aporteEmpAPVC > 0)
		total += aporteEmpAPVC;
	document.getElementById("totalAPVC").innerHTML = '$ ' + formatNumero(total);	
}

function isMPSubsidio()
{
	for (i = 0; i < numMaxMovimientos; i++){
		if (document.getElementById("idTipoMovReal" + i).value == '3')
		{
			return true;
		}
	}
	return false;
}

function showMP()
{
	for (i = 1; i < numMaxMovimientos; i++)
		if (document.getElementById("idTipoMovReal" + i).value == '-1')
		{
			document.getElementById("mpTable" + i).style.display = 'block';
			break;
		}
}

function hideMP()
{
	for (i = numMaxMovimientos - 1; i >= 0; i--)
	{
		if (document.getElementById("mpTable" + i).style.display == 'block' || document.getElementById("mpTable" + i).style.display == '')
		{
			document.getElementById("idTipoMovReal" + i).value = -1;
			document.getElementById("inicioMP" + i).value = "";
			document.getElementById("terminoMP" + i).value = "";
			document.getElementById("idEntidadSil" + i).value = -1;
			if (i >= 1)
				document.getElementById("mpTable" + i).style.display = 'none';
			break;
		}
	}
}

function showAPV()
{
	for (i = 0; i < numMaxAPVs; i++)
	{
		if (document.getElementById("idEntidadApv" + i).value == '-1')
		{
			document.getElementById("apvTable" + i).style.display = 'block';
			break;
		}
	}
}

function hideAPV()
{
	for (i = numMaxAPVs - 1; i >= 0; i--)
	{
		if (document.getElementById("apvTable" + i).style.display == 'block' || document.getElementById("apvTable" + i).style.display == '')
		{
			document.getElementById("idEntidadApv" + i).value = -1;
			document.getElementById("ahorro" + i).value = 0;
			if (i >= 1)
				document.getElementById("apvTable" + i).style.display = 'none';
			break;
		}
	}
}

function validaFormRemu()
{
	if (document.getElementById("botonGuardar").value == "1") //para que el boton se apriete solo una vez
		return false;

	var msg = validaDataBasicaForm();
	var alertaPrevision = "";

	if (!validaNumero(document.getElementById('rentaImp').value, ''))
		msg += " - Renta imponible inválida.\n";
	var rentaImp = creaNumero(document.getElementById('rentaImp').value);
	
	if (document.getElementById('idEntExCaja').value != -1)	
	{
		
		var topeImpINPPesos = document.getElementById('topeImpINPPesos').value

		if(rentaImp>topeImpINPPesos) {
			msg += " - Renta imponible es mayor que el tope legal. $" + formatNumero(topeImpINPPesos) + "\n";
			document.getElementById('rentaImp').value = formatNumero(topeImpINPPesos);
			cambiaRentaImp();
		}
	}else{
		var topeImpAFPPesos = document.getElementById('topeImpAFPPesos').value
	
		if(rentaImp>topeImpAFPPesos){
			msg += " - Renta imponible es mayor que el tope legal. $" + formatNumero(topeImpAFPPesos) + "\n";
			document.getElementById('rentaImp').value = formatNumero(topeImpAFPPesos);
			cambiaRentaImp();
		}
	}
	// marco  actualiza la variable es usada en varios procesos 
	rentaImp = creaNumero(document.getElementById('rentaImp').value);
		
//SALUD
	if (!validaNumero(document.getElementById('saludObligFONASA').value, ''))
		msg += " - Monto Cotización FONASA inválido.\n";
	if (!validaNumero(document.getElementById('saludObligISAPRE').value, ''))
		msg += " - Monto Cotización ISAPRE inválido.\n";
	if (!validaNumero(document.getElementById('saludAdicional').value, ''))
		msg += " - Monto Cotización de Salud Adicional inválido.\n";
	var saludObligFONASA = creaNumero(document.getElementById('saludObligFONASA').value);
	var saludObligISAPRE = creaNumero(document.getElementById('saludObligISAPRE').value);
	var saludAdicionalISAPRE = creaNumero(document.getElementById('saludAdicional').value);
	if (saludObligFONASA > 0 && saludObligISAPRE > 0)
		msg += " - Salud posee valores tanto para ISAPRE como para FONASA. Sólo se permite uno de los dos.\n";
// marco		
//	else if (saludObligFONASA < 0 && saludObligISAPRE < 0 && rentaImp > 0)
//		msg += " - Salud debe poseer valor para ISAPRE ó FONASA.\n";
	else
	{
		if (saludObligISAPRE > 0) //ISAPRE
		{
			msg += valLimiteMonto("Monto Cotización Obligatoria ISAPRE", saludObligISAPRE);
			if (document.getElementById('idEntSaludReal').value <= idFONASA)
				msg += " - Debe seleccionar una Entidad para ISAPRE. \n";
			if (creaNumero(document.getElementById('saludAdicional').value) < 0)
				msg += " - Monto Cotización Adicional Salud inválido. \n";
			msg += valLimiteMonto("Monto Cotización Adicional ISAPRE", creaNumero(document.getElementById('saludAdicional').value));
		} else //FONASA
		{
			//if (document.getElementById('idEntSaludReal').value != idFONASA)
			//	msg += " - Cotizante es afiliado a FONASA, por lo que no puede estar asociado a una Entidad ISAPRE. \n";
			if (creaNumero(document.getElementById('saludAdicional').value) > 0 && document.getElementById('idEntSaludReal').value == idFONASA)
				msg += " - Cotizante es afiliado a FONASA, por lo que no puede cotizar Salud Adicional.\n";
		}
	}

	if (document.getElementById('idEntSaludReal').value != -1 && (saludObligISAPRE + saludAdicionalISAPRE) == 0) {
		var rentaImponible = creaNumero(document.getElementById('rentaImp').value);
		var numDiasTrab    = creaNumero(document.getElementById('numDiasTrabajados').value);
		if (rentaImponible > 0 && numDiasTrab > 0) {
			//msg += " - Debe ingresar montos para ISAPRE. \n";
		}
	}

//PREVISION
	if (!validaNumero(document.getElementById('prevObligatorioAFP').value, ''))
		msg += " - Monto Cotización Obligatoria de Previsión AFP inválido.\n";
	if (!validaNumero(document.getElementById('prevObligatorioINP').value, ''))
		msg += " - Monto Cotización Obligatoria de Previsión INP inválido.\n";
	if (!validaNumero(document.getElementById('afpAhorro').value, ''))
		msg += " - Monto Cotización Ahorro de Previsión inválido.\n";
	if (!validaNumero(document.getElementById('inpDesahucio').value, ''))
		msg += " - Monto Cotización Desahucio inválido.\n";
	if (!validaNumero(document.getElementById('inpBonificacion').value, ''))
		msg += " - Monto Bonificación Art. 19 Ley 15.386 inválido.\n";
	var prevObligatorioAFP = creaNumero(document.getElementById('prevObligatorioAFP').value);
	var prevAhorroAFP = creaNumero(document.getElementById('afpAhorro').value);
	var prevSISAFP = creaNumero(document.getElementById('previsionSIS').value);
	var prevTotalAFP = prevObligatorioAFP + prevAhorroAFP + prevSISAFP;
	
	var prevObligatorioINP = creaNumero(document.getElementById('prevObligatorioINP').value);
	var prevDesahucioINP = creaNumero(document.getElementById('inpDesahucio').value);
	var prevTotalINP = prevObligatorioINP + prevDesahucioINP;
	
	var tipoPrevision = "ninguna";	
	if (prevObligatorioAFP > 0 && prevObligatorioINP > 0)
		msg += " - Monto Previsión posee valores tanto para AFP como para INP. Solo se permite uno de los dos.\n";
	else
	{
		if (prevObligatorioAFP < 0 && prevObligatorioINP < 0)
			msg += " - Monto Previsión debe poseer valor para AFP ó INP.\n";
		else
		{
			if (prevObligatorioAFP == 0 && prevObligatorioINP == 0)
	//			alertaPrevision = " - Trabajador no cuenta con Cotización en Monto Previsión, ¿Está seguro que desea continuar?\n";
			if (prevTotalAFP > 0)//AFP
			//if (prevObligatorioAFP > 0)//AFP
			{
				document.getElementById('tipoPrevision').value = 1;
				if (document.getElementById('idEntExCaja').value != -1)
					msg += " - Cotizante es afiliado a una AFP, por lo que no puede estar asociado a una Ex-Caja (INP). \n";	
				if (document.getElementById('idRegimenImp').value != -1)
					msg += " - Cotizante es afiliado a una AFP, por lo que no puede estar asociado a un Régimen Impositivo (INP). \n";	
				if (creaNumero(document.getElementById('inpDesahucio').value) > 0)
					msg += " - Cotizante es afiliado a una AFP, por lo que no puede cotizar Desahucio (INP). \n";	
				if (creaNumero(document.getElementById('inpBonificacion').value) > 0)
					msg += " - Cotizante es afiliado a una AFP, por lo que no puede cotizar Bonificación (INP). \n";
	
				if (document.getElementById('idEntPensionReal').value == -1)
					msg += " - Debe seleccionar una Entidad para Sistema Previsional. \n";
				if (creaNumero(document.getElementById("afpAhorro").value) < 0)
					msg += " - Monto Cotización Ahorro Previsión inválido. \n";
				tipoPrevision = "AFP";
			} else //INP
			{
				//if ( (document.getElementById('idEntPensionReal').value == '-1' || document.getElementById('idEntPensionReal').value == '-100' ) && ( document.getElementById('segCesRemImp').value > 0 || document.getElementById('segCesTrabajador').value > 0 || document.getElementById('segCesEmpresa').value > 0  )) {
				if (((document.getElementById('idEntPensionReal').value == '-1' || document.getElementById('idEntPensionReal').value == '-100' ) && document.getElementById('idEntAFCReal').value == '-1') &&
					 (document.getElementById('segCesTrabajador').value  > 0    || document.getElementById('segCesEmpresa').value     > 0      )) {
	
					msg += " - Debe seleccionar una Entidad para Seguro de Cesantía. \n";
				}
				if (document.getElementById('idEntPensionReal').value == '-1' && document.getElementById('segCesRemImp').value == 0 && document.getElementById('segCesTrabajador').value == 0 && document.getElementById('segCesEmpresa').value == 0  ) {
					document.getElementById('idEntPensionReal').value = '-100';
				}

				if (prevTotalINP > 0)				
				//if (prevObligatorioINP > 0) //INP
				{
					tipoPrevision = "INP";
					document.getElementById('tipoPrevision').value = 2;
					if (creaNumero(document.getElementById("afpAhorro").value) > 0)
						msg += " - Cotizante es afiliado a INP, por lo que no puede cotizar Previsión Ahorro. \n";
					if (document.getElementById('idEntExCaja').value == -1)
						msg += " - Debe seleccionar una Entidad Ex Caja para Sistema Previsional INP. \n";
					if (document.getElementById('idRegimenImp').value == -1)
						msg += " - Debe seleccionar un Código de Régimen Impositivo para Sistema Previsional INP. \n";
					if (creaNumero(document.getElementById('inpDesahucio').value) < 0)
						msg += " - Monto Cotización Desahucio inválido. \n";
					if (creaNumero(document.getElementById('inpBonificacion').value) < 0)
						msg += " - Monto Bonificación Art. 19 Ley 15.386 inválido. \n";
				}
			}

			/*if (tipoPrevision != "AFP" && document.getElementById('afpAhorro').value != 0)
				msg += " - Cotizante es afiliado a INP, por lo que no puede cotizar Previsión Ahorro. \n";*/
		}
	}

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
	
	// Marco si no trabajo 30 días se calcula en base a renta imponible remuneracion 
	// en caso contrario se calcula con la renta imponible seguro cesantia.
	var numDiasTrabajados = new Number(limpiaNumero(document.getElementById('numDiasTrabajados').value, ''));	
	var diaMes = new Number(limpiaNumero(document.getElementById('diasXMes').value, ''));			
	
	var rentaImpAux = segCesRemImp; 
	if (numDiasTrabajados < diaMes) {
	   rentaImpAux = rentaImp;
	}
	if (rentaImp > segCesRemImp) {
	   rentaImpAux = segCesRemImp;
	}
	// marco 
	var sciTrabCalculado  = calcula(rentaImpAux, document.getElementById('apTrabIndSegCes').value);
	var scpfTrabCalculado = calcula(rentaImpAux, document.getElementById('apTrabPFSegCes').value);
	var tolerancia = creaNumero(document.getElementById('tolerancia').value);
						
   //	var sciTrabCalculado = calcula(segCesRemImp, document.getElementById('apTrabIndSegCes').value);
   //	var scpfTrabCalculado = calcula(segCesRemImp, document.getElementById('apTrabPFSegCes').value);
	
	var sciEmpCalculado = calcula(segCesRemImp, document.getElementById('apEmpIndSegCes').value);
	var scpfEmpCalculado = calcula(segCesRemImp, document.getElementById('apEmpPFSegCes').value);
	if (segCesRemImp < 0)
		msg += " - Debe ingresar un monto para Remuneración Imponible Seguro de Cesantía. \n";
	if (segCesRemImp > _segCesRemTope)
		msg += " - Monto para Remuneración Imponible Seguro de Cesantía mayor al tope (" + _segCesRemTope + "). \n";
    
// marco se incorpora totelrenacia en el calculo 

// marco elimina validacion
//	var total =  segCesTrabajador + segCesEmpresa;
	
//	if ( ((total > (sciTrabCalculado + sciEmpCalculado) + tolerancia) || (total < (sciTrabCalculado + sciEmpCalculado)-tolerancia)) 
//	    ||
//	     ((total > (scpfTrabCalculado+scpfEmpCalculado) + tolerancia) || (total < (scpfTrabCalculado + scpfEmpCalculado)-tolerancia))  )
//	   {
//		msg += " - Montos aportes Trabajador/Empresa no corresponden a valores posibles: \n";
//		msg += "   Contrato Indefinido  Trabajador= " + Math.round(sciTrabCalculado) + ", Empresa= " + Math.round(sciEmpCalculado) + ",\n";
//		msg += "   Contrato Plazo Fijo  Trabajador= " + Math.round(scpfTrabCalculado) + ", Empresa= " + Math.round(scpfEmpCalculado) + ".\n";
//	    }
	 
      
//    if ((segCesTrabajador == sciTrabCalculado && segCesEmpresa == sciEmpCalculado) 
//		|| (segCesTrabajador == scpfTrabCalculado && segCesEmpresa == scpfEmpCalculado))		
//	{
//		//OK
//	}
//	else
//	{
//		msg += " - Montos aportes Trabajador/Empresa no corresponden a valores posibles: \n";
//		msg += "   Contrato Indefinido  Trabajador= " + Math.round(sciTrabCalculado) + ", Empresa= " + Math.round(sciEmpCalculado) + ",\n";
//		msg += "   Contrato Plazo Fijo  Trabajador= " + Math.round(scpfTrabCalculado) + ", Empresa= " + Math.round(scpfEmpCalculado) + ".\n";
//	}


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
			if (montoTrabPesado <= 0)
				msg += " - Monto Trabajo Pesado debe ser mayor a 0. \n";
			if (document.getElementById('idTasaTraPesa').value == -1)
				msg += " - Debe seleccionar una Tasa para Trabajo Pesado. \n";
			if (document.getElementById('nombreTrabPesado').value == '')
				msg += " - Debe ingresar un nombre para Trabajo Pesado. \n";
			if(montoTrabPesado > 0 && document.getElementById('idTasaTraPesa').value != -1 && rentaImp > 0)
			{
				if(Math.round(rentaImp * (document.getElementById('idTasaTraPesa').value / 100)) != montoTrabPesado)
					msg += " - Monto Trabajo Pesado debe ser " + Math.round(rentaImp * document.getElementById('idTasaTraPesa').value / 100) + ". \n";
			}
		}
	} else
	{
		if (tipoPrevision == "INP" && tieneTP == 1)
			msg += " - Cotizante es afiliado a INP, por lo que no debe cotizar Trabajo Pesado. \n";
	}
//Valida Renta Imponible INP/FONASA
	var remImpPensionValidar = creaNumero(document.getElementById('remImpPension').value);
	
	if (document.getElementById('idEntExCaja').value != -1)	
	{
		var topeImpINPPesos = document.getElementById('topeImpINPPesos').value

		if(remImpPensionValidar>topeImpINPPesos) {
			msg += " - Remuneración Imponible para INP es mayor que el tope legal. $" + formatNumero(topeImpINPPesos) + "\n";
			document.getElementById('remImpPension').value = formatNumero(topeImpINPPesos);
			recalculaINP();
		}
	}else{
		var topeImpAFPPesos = document.getElementById('topeImpAFPPesos').value
	
		if(remImpPensionValidar>topeImpAFPPesos){
			msg += " - Remuneración Imponible para INP es mayor que el tope legal. $" + formatNumero(topeImpAFPPesos) + "\n";
			document.getElementById('remImpPension').value = formatNumero(topeImpAFPPesos);
			recalculaINP();
		}
	}
	
//CAJA
	if (!sinCaja)//con caja
	{
		if (document.getElementById('idTramo').value != -1)
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
	
			var asigFam = creaNumero(document.getElementById('asigFam').value);
			var asigFamRetro = creaNumero(document.getElementById('asigFamRetro').value);
			var reintegroAsigFam = creaNumero(document.getElementById('reintegroAsigFam').value);
			if (!validaNumero(document.getElementById('asigFam').value) || asigFam < 0)
				msg += " - Monto Asignación Familiar inválido. \n";
			if (!validaNumero(document.getElementById('asigFamRetro').value) || asigFamRetro < 0)
				msg += " - Monto Asignación Familiar Retroactiva inválido. \n";
			if (!validaNumero(document.getElementById('reintegroAsigFam').value) || reintegroAsigFam < 0)
				msg += " - Monto Asignación Familiar Reintegros inválido. \n";
			if (!tieneCargas && asigFam > 0)
				msg += " - No corresponde Monto Asignación Familiar, si no se informan cargas familiares. \n";
		}
		if (document.getElementById('idEntSaludReal').value == idFONASA)
		{
			if (!validaNumero(document.getElementById('aporteCaja').value, ''))
				var r="tem";
				//msg += " - Monto Aporte CCAF inválido. \n";
			else if (creaNumero(document.getElementById('aporteCaja').value) < 0)
				var r="tem";
				//msg += " - Monto Aporte CCAF incorrecto (" + monto + "). \n";
			else
			{
				var aportePorcentajeCaja = creaDecimal(document.getElementById('aporteCCAFFON').value);
				var remImpPension = creaNumero(document.getElementById('remImpPension').value);				
				
				var aporteCaja = creaNumero(document.getElementById('aporteCaja').value);
				var calculadoCaja = calcula(remImpPension / 100, aportePorcentajeCaja);
				if (aporteCaja != calculadoCaja)
					var r="tem";
					//msg += " - Monto Aporte CCAF incorrecto (" + calculadoCaja + "). \n";
			}
		} else if (document.getElementById('idEntSaludReal').value != idFONASA && creaNumero(document.getElementById('aporteCaja').value) > 0 && !document.getElementById("fonasaCheck").checked)
			var r="tem";
			//msg += " - Monto Aporte CCAF no debe aparecer si no es afiliado a FONASA. \n";

		if (!validaNumero(document.getElementById('creditoPersonal').value) || creaNumero(document.getElementById('creditoPersonal').value) < 0)
			msg += " - Monto de Crédito Personal de CCAF inválido. \n";
		if (!validaNumero(document.getElementById('leasing').value) || creaNumero(document.getElementById('leasing').value) < 0)
			msg += " - Monto de Leasing de CCAF inválido. \n";
		if (!validaNumero(document.getElementById('convenioDental').value) || creaNumero(document.getElementById('convenioDental').value) < 0)
			msg += " - Monto de Convenio Dental de CCAF inválido. \n";
		if (!validaNumero(document.getElementById('seguro').value) || creaNumero(document.getElementById('seguro').value) < 0)
			msg += " - Monto de Seguro de Vida de CCAF inválido. \n";
	}
	
	

//MUTUAL
	if (!sinMutual) //con mutual
	{
		if (!validaNumero(document.getElementById('rentaImpMutual').value))
			msg += " - Monto Renta Imponible MUTUAL inválido. \n";
		monto = creaNumero(document.getElementById('rentaImpMutual').value);
		if (monto < 0)
			msg += " - Monto Renta Imponible MUTUAL debe ser mayor o igual a cero. \n";
		var calculo = Math.round(monto / 100 * parseFloat(tasaMutual));

		if (!validaNumero(document.getElementById('cotizacionMutual').value))
			msg += " - Monto Cotización MUTUAL inválido. \n";
			
		var rentaImpMutualValidar = creaNumero(document.getElementById('rentaImpMutual').value);
		
		var topeImpAFPPesos = document.getElementById('topeImpAFPPesos').value
	
		if(rentaImpMutualValidar>topeImpAFPPesos){
			msg += " - Remuneración Imponible Mutual es mayor que el tope legal. $" + formatNumero(topeImpAFPPesos) + "\n";
			document.getElementById('rentaImpMutual').value = formatNumero(topeImpAFPPesos);
			calculaCotizacionMutual();
		}
	}
//INP
	if (sinMutual)
	{
		if (!validaNumero(document.getElementById('accTrabajoINP').value))
			msg += " - Monto Accidentes del Trabajo inválido. \n";
		else if (creaNumero(document.getElementById('accTrabajoINP').value) < 0)
			msg += " - Monto Accidentes del Trabajo debe ser mayor o igual a cero. \n";
	}
	if (sinCaja)
	{
		//if (document.getElementById('idTramoINP').value == -1)
		//	msg += " - Debe seleccionar un Tramo de Asignación Familiar. \n";
		if (!validaNumero(document.getElementById('cargasSimplesINP').value))
			msg += " - Número de cargas Simples inválido. \n";
		else if (creaNumero(document.getElementById('cargasSimplesINP').value) < 0)
			msg += " - Número de cargas Simples inválido. \n";
		if (!validaNumero(document.getElementById('cargasMaternalesINP').value))
			msg += " - Número de cargas Maternales inválido. \n";
		else if (creaNumero(document.getElementById('cargasMaternalesINP').value) < 0)
			msg += " - Número de cargas Maternales inválido. \n";
		if (!validaNumero(document.getElementById('cargasInvalidezINP').value))
			msg += " - Número de cargas de Invalidez inválido. \n";
		else if (creaNumero(document.getElementById('cargasInvalidezINP').value) < 0)
			msg += " - Número de cargas de Invalidez inválido. \n";
	}
	
//MOVIMIENTO PERSONAL
	//1=Obligatorio
	//0=Omitible si se indica fecha de inicio o termino

// elimina validacion Marco 
/*
	var opcionMvto = new Array(tipoMovimiento);
	var ordenIdMovimiento = new Array(ordenIdMovimiento);
	
	for (kk = 0; kk < numMaxMovimientos; kk++)
	{
		var fechaIni = document.getElementById("inicioMP" + kk).value;
		var fechaFin = document.getElementById("terminoMP" + kk).value;
		var flag = 0;

		if (fechaIni != '' && !validarFecha(fechaIni))
		{
			msg += " - Fecha inicio inválida para el Movimiento de Personal " + (kk + 1) + "\n";
			flag = 1;
		}
		if (fechaFin != '' && !validarFecha(fechaFin))
		{
			flag = 1;
			msg += " - Fecha término inválida para el Movimiento de Personal " + (kk + 1) + "\n";
		}

		if (flag == 0)
		{
			document.getElementById("inicioMP" + kk + "Hidd").value = fechaIni;
			document.getElementById("terminoMP" + kk + "Hidd").value = fechaFin;
						
			var opI = document.getElementById("idTipoMovReal" + kk).value;	
			var op = 0;
			for(a=0; a < ordenIdMovimiento.length; a++)
			{
				if(opI == ordenIdMovimiento[a])
					op = a;
			}		
			if (fechaIni == '' && document.getElementById("idTipoMovReal" + kk).value != '-1')
					msg += " - Verifique la fecha de inicio ingresada para el Movimiento de Personal " + (kk + 1) + "\n";			
			if(opcionMvto[op] == 1)
			{
				if (fechaFin == '' && document.getElementById("idTipoMovReal" + kk).value != '-1')
					msg += " - Verifique la fecha de término ingresada para el Movimiento de Personal " + (kk + 1) + "\n";			
			}
			if (fechaIni != '' && fechaFin != '')
			{	
				var fechaIArray = fechaIni.split("-");
				var fechaFArray = fechaFin.split("-");
//				alert("aaa: " + fechaIArray[2]);
//				alert("fech: " + creaNumero(fechaIArray[2]+fechaIArray[1]+fechaIArray[0]));

				if(creaNumero(fechaIArray[2]+fechaIArray[1]+fechaIArray[0]) > creaNumero(fechaFArray[2]+fechaFArray[1]+fechaFArray[0]))
					msg += " - Fechas de Movimiento de Personal " + (kk + 1) + ": Fecha Inicio debe ser menor a Fecha Término\n";
			}
			if (document.getElementById("idTipoMovReal" + kk).value == tipoMovtoEntidadSIL && document.getElementById("idEntidadSil" + kk).value == '-1')
				msg += " - Debe seleccionar una Entidad Pagadora de Subsidio para Movimiento de Personal " + (kk + 1) + "\n";
		}
	}
	
*/

//APV
	for (gg = 0; gg < numMaxAPVs; gg++)
	{
		if (document.getElementById("idEntidadApv" + gg)         &&
			document.getElementById("idEntidadApv" + gg) != null &&
			document.getElementById("ahorro" + gg)               &&
			document.getElementById("ahorro" + gg)       != null)
		{
			var ahorroAPV = creaNumero(document.getElementById("ahorro" + gg).value);
			if (document.getElementById("ahorro" + gg).value.lenght > 0 && !validaNumero(document.getElementById("ahorro" + gg).value))
				msg += " - Monto Ahorro para APV " + (gg + 1) + " inválido. \n";
			if (document.getElementById("idEntidadApv" + gg).value != -1 && ahorroAPV == 0)
				msg += " - Debe ingresar un monto de Ahorro para APV " + (gg + 1) + "\n";
			if (ahorroAPV > 0 && document.getElementById("idEntidadApv" + gg).value == -1)
				msg += " - Debe seleccionar una entidad de Ahorro para APV " + (gg + 1) + "\n";
			if (ahorroAPV > 0 && document.getElementById("idEntidadApv" + gg).value != -1 && document.getElementById("regimen" + gg).value == 0)
			msg += " - Debe seleccionar un Regimen APV " + (gg + 1) + "\n";
		}
	}

	//jlandero. Validación para APV Colectivo sobre montos no ingresados.
	if (document.getElementById("idAPVC").value != "-1")
	{
		if (document.getElementById("numContratoAPVC").value.lenght > 0 && !validaNumero(document.getElementById("numContratoAPVC").value))
			msg += " - Número de contrato para el APV Colectivo inválido. \n";
		if (creaNumero(document.getElementById("numContratoAPVC").value) == 0)
			msg += " - Debe ingresar un monto de ahorro para el APV Colectivo. \n";

		if (creaNumero(document.getElementById("aporteTraAPVC").value) == 0)
			msg += " - Debe ingresar el aporte del trabajador para el APV Colectivo. \n";
		if (creaNumero(document.getElementById("aporteEmpAPVC").value) == 0)
			msg += " - Debe ingresar el aporte del Independiente para el APV Colectivo. \n";
	}

//Depósito Convenido
	//document.getElementById('fechaIni').value = document.getElementById('fechaIniDep').value;
	//document.getElementById('fechaFin').value = document.getElementById('fechaFinDep').value;
	//campos formulario:
		//lista
			//idEntDep
		//radio
			//RegPrevDepINP: 1
			//RegPrevDepAFP: 2
		//montos
			//depositoConvenido
			//indemAporte
			//rentaImp
			//numPeriodos
		//porcentajes
			//tasaPactada
		//fechas (ya estan restringidas, no se validan)
			//fechaIniDep
			//fechaFinDep
	var flgDepConvenido    = 0;
	var flgIndemnizaciones = 0;

	if (document.getElementById("RegPrevDepINP").checked || document.getElementById("RegPrevDepAFP").checked) {
		flgIndemnizaciones = 1;
	}
	
	if (document.getElementById('idEntDep').value != -1 || (document.getElementById("depositoConvenido").value != "" && document.getElementById("depositoConvenido").value != 0)) {
		flgDepConvenido = 1;
	}
	
	if (flgDepConvenido == 1 && flgIndemnizaciones == 0) {
		msg += validaDepConvenido();
	} else if (flgDepConvenido == 0 && flgIndemnizaciones == 1) {
		msg += validaIndemnizaciones();
	} else if (flgDepConvenido == 1 && flgIndemnizaciones == 1) {
		msg += validaDepConvenido();
		msg += validaIndemnizaciones();
	}

	if (msg != '')
	{
		alert(msg);
		return false;
	}
	else
	{
		if (alertaPrevision != '')
		{
			if (confirm(alertaPrevision))
			{
				document.getElementById("botonGuardar").value = "1";
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	document.getElementById("botonGuardar").value = "1";
	return true;	
}

function recalculaPensionAFP()
{
	recalculaTipoPrevision();
	if(document.getElementById('idEntPensionReal').value == idAFPNinguna || document.getElementById('idEntPensionReal').value == idSinAFP)
	{
		document.getElementById('prevObligatorioAFP').value = 0;
		document.getElementById('afpAhorro').value          = 0;
		document.getElementById('segCesRemImp').value       = 0;
		document.getElementById('segCesTrabajador').value   = 0;
		document.getElementById('segCesEmpresa').value      = 0;
		document.getElementById('idTasaTraPesa').value      = -1;
		document.getElementById('nombreTrabPesado').value   = '';
		document.getElementById('montoTrabPesado').value    = 0;
		document.getElementById('rentaImponibleSIS').value  = 0;
		document.getElementById('previsionSIS').value       = 0;
		document.getElementById("totalAfp").value = 0;
		document.getElementById("totalAfpDiv").innerHTML = '$ ' + '0';
		
	}
	if(document.getElementById('idEntPensionReal').value == '-1')
		var tasaAFP = 0;
	else
		var tasaAFP = new Number(document.getElementById('entAFP-' + document.getElementById('idEntPensionReal').value).value);
	var totalAfpDiv = new Number(0);
	var totalAfcDiv = new Number(0);
	
	var tasaSIS = 0;
	if (document.getElementById('datosSis').value == 'false') {
		tasaSIS = new Number(document.getElementById('porcentSis-' + document.getElementById('idEntPensionReal').value).value);
	}
	var tasaNormal = (tasaAFP + tasaSIS) / 100;
	var prevObligatorioAFP = Math.round(new Number(limpiaNumero(document.getElementById('rentaImp').value, '')) * tasaNormal);
	var afpAhorro = new Number(limpiaNumero(document.getElementById('afpAhorro').value, ''));
	var segCesRemImp = new Number(limpiaNumero(document.getElementById('segCesRemImp').value, ''));
	var segCesTrabajador = new Number(limpiaNumero(document.getElementById('segCesTrabajador').value, ''));
	var segCesEmpresa = new Number(limpiaNumero(document.getElementById('segCesEmpresa').value, ''));
	var montoTrabPesado = new Number(limpiaNumero(document.getElementById('montoTrabPesado').value, ''));
	document.getElementById('prevObligatorioAFP').value = formatNumero(prevObligatorioAFP);

	if (prevObligatorioAFP > 0)
	{
		document.getElementById('prevObligatorioINP').value = '0';
		document.getElementById('idEntExCaja').value = -1;
		document.getElementById('idRegimenImp').value = -1;
		document.getElementById('prevObligatorioINP').value = '0';

		document.getElementById('tasaCotINP').innerHTML = '0.0 %';
		recalculaTotalINP();
		totalAfpDiv += prevObligatorioAFP;
	}
	if (afpAhorro > 0)
		totalAfpDiv += afpAhorro;
	document.getElementById("totalAfp").value = totalAfpDiv;
	document.getElementById("totalAfpDiv").innerHTML = '$ ' + formatNumero(totalAfpDiv);

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

function recalculaAsigFamINP()
{
	var cargasSimplesINP = new Number(limpiaNumero(document.getElementById('cargasSimplesINP').value, ''));
	var cargasMaternalesINP = new Number(limpiaNumero(document.getElementById('cargasMaternalesINP').value, ''));
	var cargasInvalidezINP = new Number(limpiaNumero(document.getElementById('cargasInvalidezINP').value, ''));
	var AsignFamTmp = new Number(0);
	var totalAsignFamDivINP = new Number(0);

	if (document.getElementById("idTramoINP").value != "-1")
	{
		var valorTramo = document.getElementById(document.getElementById("idTramoINP").value + "-trAsigFam").value;
		
		if (cargasSimplesINP > 0)
			AsignFamTmp += cargasSimplesINP;
		if (cargasMaternalesINP > 0)
			AsignFamTmp += cargasMaternalesINP;
		if (cargasInvalidezINP > 0)
			AsignFamTmp += cargasInvalidezINP * 2;
	
		
		var asigFamiliarINP = valorTramo * AsignFamTmp;
		if(!isMPSubsidio()){
			var numDiasTrabajados = new Number(limpiaNumero(document.getElementById('numDiasTrabajados').value, ''));			
			if (numDiasTrabajados < new Number(limpiaNumero(document.getElementById('diasTopeAF').value, '')))
				asigFamiliarINP = Math.round((asigFamiliarINP * numDiasTrabajados) / 30);
		}
		//clillo 24-05-16 Sin pagos para IPS
		//document.getElementById('asigFamiliarINP').value = asigFamiliarINP;
		document.getElementById('asigFamiliarINP').value = 0;
	}
	recalculaTotalINP();
}

function recalculaAsigFam()
{
	var cargasSimples = new Number(limpiaNumero(document.getElementById('cargasSimples').value, ''));
	var cargasMaternales = new Number(limpiaNumero(document.getElementById('cargasMaternales').value, ''));
	var cargasInvalidez = new Number(limpiaNumero(document.getElementById('cargasInvalidez').value, ''));
	var AsignFamTmp = new Number(0);
	var totalAsignFamDiv = new Number(0);

	if (document.getElementById("idTramo").value != "-1") 
	{
		var valorTramo = document.getElementById(document.getElementById("idTramo").value + "-trAsigFam").value;
	  	if (cargasSimples > 0)
			AsignFamTmp += cargasSimples;
		if (cargasMaternales > 0)
			AsignFamTmp += cargasMaternales;
		if (cargasInvalidez > 0)
			AsignFamTmp += cargasInvalidez * 2;
	
		var asigFam = valorTramo * AsignFamTmp;
		if(!isMPSubsidio()){
			var numDiasTrabajados = new Number(limpiaNumero(document.getElementById('numDiasTrabajados').value, ''));
			if (numDiasTrabajados < new Number(limpiaNumero(document.getElementById('diasTopeAF').value, '')))
				asigFam = Math.round((asigFam * numDiasTrabajados) / 30);
		}
		document.getElementById('asigFam').value = asigFam;
	}
	recalculaCaja();
}

function limpiaApv(count)
{
	if(document.getElementById(('idEntidadApv'+count)).value == '-1')
		document.getElementById(('ahorro'+count)).value = 0;
		document.getElementById(('regimen'+count)).value = 0;
}

function calculaCotizacionMutual() 
{
	var tasaMutual = document.getElementById("tasaMutual").value;
	var rentaImpMutual = document.getElementById("rentaImpMutual").value.replace(/\./gi, "");
	var cotizacionMutual = Math.round( (tasaMutual * rentaImpMutual)/100 );
	document.getElementById("cotizacionMutual").value = formatNumero(limpiaNumero(cotizacionMutual, ''));
}

function limpiaAFP()
{	
	//document.getElementById('idEntPensionReal').value = idAFPNinguna;
	document.getElementById('idEntPensionReal').value = 0;
	document.getElementById('prevObligatorioAFP').value = 0;
	document.getElementById('segCesRemImp').value = 0;
	document.getElementById('segCesTrabajador').value = 0;
	document.getElementById('segCesEmpresa').value = 0;
	document.getElementById('idTasaTraPesa').value = '-1';
	document.getElementById('nombreTrabPesado').value = '';
	document.getElementById('montoTrabPesado').value = 0;
	document.getElementById('rentaImponibleSIS').value = 0;
	document.getElementById('previsionSIS').value = 0;
	document.getElementById('totalAfpDiv').innerHTML = '$ 0';
	document.getElementById('totalAfcDiv').innerHTML = '$ 0';	
	document.getElementById('totalPagarAfpDiv').innerHTML = '$ 0';		
	document.getElementById('totalAfp').value = 0;		
	document.getElementById('afpAhorro').value = 0;	
	
	document.getElementById("idEntAFCReal").disabled = false;	
}

//jlandero: Validación propia de Depósito Convenido
function validaDepConvenido() {
	var msg = '';

	if (document.getElementById('idEntDep').value == -1) {
		msg += " - Debe seleccionar una Entidad. \n";
	} else {
		if (document.getElementById("depositoConvenido").value == "" || document.getElementById("depositoConvenido").value == 0)
			msg += " - Debe ingresar un monto para Depósito Convenido. \n";
		else if (!validaNumero(document.getElementById("depositoConvenido").value))
			msg += " - Monto para Depósito Convenido debe corresponder a un número. \n";
	}

	return msg;
}

//jlandero: Validación propia de Depósito Convenido
function validaIndemnizaciones() {
	var msg = '';
	
	if (!document.getElementById("RegPrevDepINP").checked && !document.getElementById("RegPrevDepAFP").checked)
		msg += " - Debe seleccionar un valor para Tipo Régimen Previsional. \n";

	var topeIndemn    = new Number(limpiaNumero(document.getElementById('topeIndemn').value, ''));
	var tasaPactada   = parseFloat(document.getElementById('tasaPactada').value.replace(/,/,'.'));
	var minTasaIndemn = new Number(document.getElementById('minTasaIndemn').value);
	var maxTasaIndemn = new Number(document.getElementById('maxTasaIndemn').value);

	if (tasaPactada > 0) {
		if (tasaPactada > maxTasaIndemn)
			msg += " - Valor para Tasa Pactada es superior al máximo permitido (" + maxTasaIndemn + "). \n";
		if (tasaPactada < minTasaIndemn)
			msg += " - Valor para Tasa Pactada es inferior al mínimo permitido (" + minTasaIndemn + "). \n";
	}

	if (document.getElementById("rentaImp").value == "" || document.getElementById("rentaImp").value == 0)
		msg += " - Debe ingresar un monto para Remuneración Imponible. \n";

	if (document.getElementById("indemAporte").value == "" || document.getElementById("indemAporte").value == 0)
		msg += " - Debe ingresar un monto para Aporte Indemnización. \n";

	if (new Number(limpiaNumero(document.getElementById('indemAporte').value, '')) > topeIndemn)
		msg += " - Monto para Aporte Indemnización supera al tope permitido (" + topeIndemn + "). \n";

	if (document.getElementById("numPeriodos").value == "" || document.getElementById("numPeriodos").value == 0)
		msg += " - Debe ingresar un valor para Períodos Indemnización. \n";

	if (document.getElementById("fechaIniDep").value == '')
		msg += " - Debe seleccionar una Fecha de Inicio. \n";
	else if (!validarFecha(document.getElementById("fechaIniDep").value))
		msg += " - Fecha inicio inválida. \n";

	if (document.getElementById("fechaFinDep").value == '')
		msg += " - Debe seleccionar una Fecha de Término. \n";
	else if (!validarFecha(document.getElementById("fechaFinDep").value))
		msg += " - Fecha término inválida. \n";

	return msg;
}