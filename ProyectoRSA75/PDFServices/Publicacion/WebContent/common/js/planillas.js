function razon_rut(){
		form1.RutEmpresa.value = form1.RazonSocial.value;
	}
	function rut_razon(){
		form1.RazonSocial.value = form1.RutEmpresa.value;
	}
	function VeRut(dest,orig){
		document.all.item(dest).value = JDigitoRut(orig.value);
		if(document.all.item(dest).value == "0" && orig.value > 1){
			document.all.item(dest).value = "";
		}
	}

	function ValidaFeinicotiza(Obj, Obj2, inicotiza){
		if (Obj.value == ""){
			return;
		}
		if (Obj.value < inicotiza) {
	     alert ("Fecha de incio de cotización es " + inicotiza);
	     Obj.value = "";
	     ValidaRango(Obj, Obj2);
	     Obj.focus();
	    }
	    return;
	}

	function ValidaRango(Obj, Obj2){
		if (Obj2.value == ""){
			return;
		}
		if (Obj.value == ""){
			Obj.value = Obj2.value;
			Obj2.value = "";
			ValidaFeinicotiza(Obj, Obj2);
			return;
		}
		if (Obj.value > Obj2.value){
			alert ("Período de Fechas no es válido");
			Obj2.focus();
			Obj2.select();
		}
	}


	function validaFolio(){
			if (JTrim(form1.FolioPlanilla.value) != ""){
				form1.FolioPlanilla.value = rellena(form1.FolioPlanilla.value, 10, "0", 2);
			}
		}
		
	function validaComprobante(){
		if (JTrim(form1.NumeroComprobante.value) != ""){
			form1.NumeroComprobante.value = rellena(form1.NumeroComprobante.value, 13, "0", 2);
			form1.NumeroComprobante.value = rellena(form1.NumeroComprobante.value, 14, "8", 2);
		       }
		}

	function DejaRut(){
		if(form1.RutEmpresa.value != ""){
			form1.RazonSocial.value = "";
			form1.RazonSocial.disabled = true;
		}else{
			form1.RazonSocial.disabled = false;
			form1.dv1.value = ""
		}

	}

	function DejaRazon(){
		if(JTrim(form1.RazonSocial.value) != ""){
			form1.RutEmpresa.value = "";
			form1.dv1.value = "";
			form1.RutEmpresa.disabled = true;
		}else{
			form1.RutEmpresa.disabled = false;
		}
	}


	function ValidaFecha(Obj){
		var EsError,a;
		if (Obj.value == ""){
			return;}
		aux = Obj.value;
		if (aux.length != 6){
			alert("Fecha Inválida");
			if (!document.all){
				Obj.value = "";
			}
			Obj.focus();
			Obj.select();
			return;
		}
		EsError=CheckDate("01" + Obj.value)
		if (EsError == "si"){
			alert ("Fecha Inválida");
			if (!document.all){
				Obj.value = "";
			}
			Obj.focus();
			Obj.select();
			return;
		}
		else{
			//Obj.value = aux.substring(0,2) + "/" + aux.substring(2,4) +"/" + aux.substring(4,8);
			return;
		}
	}

		function sizeRows()
		{
			var frameset = parent.document.getElementById("main_frame");
			frameset.rows = "208,*";
		}