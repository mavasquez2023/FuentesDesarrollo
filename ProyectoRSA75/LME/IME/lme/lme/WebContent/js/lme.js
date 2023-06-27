// lme.js

function showExecLcc(b,option){	
    showPrincipal(false);
	tittle = option=='execLMEEvenLcc'? 'Consumir Estados':'Consumir Licencias';
	$("ultimoEstado").value='';
	if(option=='execLMEDetLcc72'){
		tittle='Consumir Estado 72';
		$("ultimoEstado").value='72';
	}
	tittle = option=='execLMEDetLcc72'? 'Consumir Estado 72':'Consumir Licencias';
	$("executeLcc").value = option;
	showStatus(false);
	showDetailLcc(false);
	$("divExecLcc").style.display = b? 'inline':'none';
	$("divExecTitle").innerHTML  = '<br/>&nbsp;&nbsp;'+tittle+'<br/>';
	$('divExecReturn').innerHTML = '';
	$('mensajeUpload').innerHTML = '';
	//
	$('divExecEvenLcc').style.display = option=='execLMEEvenLcc' ? 'inline':'none';
	$('divExecDetLcc').style.display = option=='execLMEEvenLcc' ? 'none':'inline';
}

// 'LMEEvenLcc'
function execLcc(){
	url = '/lme/exe.do';
	o = new jObject();
	o.list['event']=$('executeLcc').value;
	o.list['dateLcc']=$('dateLcc').value;
	o.list['horaDesde']=$('horaDesde').value;
	o.list['horaHasta']=$('horaHasta').value;
	
	if(o.list['dateLcc']==''){
		alert('Debe ingresar fecha');
		return;
	}
	showWaitMsg(true);
	var set = function(data){
		showWaitMsg(false);
		$('divExecReturn').innerHTML = data;
	};
	
	r = new XMLHttp();
	r.onError = function(r){showWaitMsg(false);alert(r.responseText);}; 
	r.get (url,o.toURLString(),set);
}

//Original
function execDetLcc()
{
	url = '/lme/exe.do';
	o = new jObject();
	o.list['event']=$('executeLcc').value;
	o.list['license']=$('exelicense').value;
	var license = $('exelicense').value;
	o.list['codOpe']=$('codOpe_').value;
	var codOpe=$('codOpe_').value;
	var ultimoEstado=$('ultimoEstado').value;
	var file= $('file').value;
    //alert('event - ' + $('executeLcc').value);
	$('mensajeUpload').innerHTML = "";
	
	if(isNaN(o.list['license']) || o.list['license']=='')
	{
		alert('Licencia inv\u00e1lida');
		$("executeLcc").value='';
		$("executeLcc").focus();
		return;
	}
	showWaitMsg(true);
    var set = function(data){

		showWaitMsg(false);
		$('divExecReturn').innerHTML = data;
	};

	//	    if( data=="1OK" )
	//	    {  
	//	       alert("Estimado usuario.\nLa licencia número "+ license+" fue consumida con éxito.");
	//	       	       
	//	    }else
	//	    {
	//	       alert("Ha ocurrido un problema al intentar consumir la licencia número "+ license+"\n\n\n "+data);
	//	    }
	//}
	
	r = new XMLHttp();
	r.onError = function(r){showWaitMsg(false);alert(r.responseText);};
	//alert('el url .string es:'+o.toURLString());
	//r.get (url,o.toURLString(),set);
	r.get (url,'event=execLMEDetLcc&license='+ license + '&codOpe='+codOpe + '&file='+file + '&ultimoEstado='+ultimoEstado,set);
}

function execDetLccCSV()
{	
	var file=$('file').value;
	var extension= file.substr(file.lastIndexOf('.')+1);
	extension= extension.toUpperCase();
	if(file=='' || (extension!='TXT' && extension!='CSV'))
	{
		alert('Ingrese un archivo de licencias válido');
		$("file").focus();
		return;
	}
	showWaitMsg(true);
	document.form3.submit();
}

//Copia
function execDetLccNew(_ideOpe,_numimpre)
{
	//alert(_ideOpe+'-'+_numimpre.value);
	url = '/lme/exe.do';
	o = new jObject();
	var operador;
	o.list['event']='execLMEDetLcc';
	o.list['license']=_numimpre.value;
	
	if(_ideOpe.value == 2){
		//o.list['codOpe'] = 3; //se cambia el ID del operador por el registrado en los parametros
		operador = 3;
		}
	else {
		o.list['codOpe'] = _ideOpe.value;
		operador = _ideOpe.value;
		}

    showWaitMsg(true);
    var set = function(data){

		showWaitMsg(false);
		//$('divExecReturn').innerHTML = data;
	//};
		
		
		   alert(data);
	       window.close();
	       window.opener.getLmeCero();
	       
		    /*if( data=="OK" )
		    {  
		       //alert("Estimado usuario.\nLa licencia numero "+ _numimpre.value+" fue consumida con exito.");
		       //alert("Se agregó el consumo particular de la licencia "+ _numimpre.value+" a la cola");
		       window.close();
		       window.opener.getLmeCero();
		       	       
		    }else
		    {
		    	
		       //alert("Ha ocurrido un problema al intentar guardar la licencia numero "+ _numimpre.value+"\n\n" + data );
		       window.close();
		       window.opener.getLmeCero();
		    }*/
	}


    r = new XMLHttp();
	r.onError = function(r){showWaitMsg(false);alert(r.responseText);};
	r.get (url,'event=execLMEDetLcc&license='+ _numimpre.value + '&codOpe='+operador,set);
}