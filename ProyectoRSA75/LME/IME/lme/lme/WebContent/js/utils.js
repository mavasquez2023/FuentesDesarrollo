// utils.js


function cargarOperador()
{
url = '/lme/admin.do';
	var set = function(data){
		
		addOptionSelected($("ideOpeNew"), data, 'ideOpe', 'nomOpe');
		
	};
	req = new XMLHttp();
	req.get (url,"event=getIdeOpe",set);
}

function addOptionSelected(selectItem, data, value, label)
{

    var ideOpe = document.getElementById("_ideOpe").value;
    
    var setOption = function (i,d){
		v = d[value];
		l = d[label];
		
		if( v==ideOpe )
		   selectItem.options[i]=new Option(l,v,"","selected");
		else
		  selectItem.options[i]=new Option(l,v);
	};
	if (data.length==null){
		setOption(0,data);
	}else{
		for(i=0; i<data.length; i++)
			setOption(i,data[i]);
	}	
}


function Actualizar()
{
	
	var ideOpe = document.getElementById("_ideOpe").value;
	var estLicen = document.getElementById("_estLicen").value;
	var afiRut = document.getElementById("_afiRut").value;
	var numimpre = document.getElementById("_numimpre").value;
	var numimprela = document.getElementById("_numimprela").value; //14022013
	
	var _ideOpeNew = document.getElementById("ideOpeNew").value;
	var _numimpreNew = document.getElementById("numimpreNew").value;
	
	   //alert("ideOpe es:"+ideOpe);
	   //alert("_ideOpeNew es:"+_ideOpeNew);
	   
	if (ideOpe != _ideOpeNew)
	{
		if( isNaN(_numimpreNew) || _numimpreNew=="" )
		{
	   		alert("La LME debe ser numerica.");
	   		return false;
		}else if(_numimpreNew < 0)
		{
	   		alert("La LME debe ser numero positivo.");
	   		return false;
		}else if(_numimpreNew.length > 9)
		{
	   		alert("La LME debe ser numero de maximo 9 digitos.");
	   		return false;
		}else
		{
		   // alert("se debe actualizar el NUM interno en la CCAF.");
		   
		   showWaitMsg(true);
		   
		    url = '/lme/admin.do';	
			var set = function(resp){
			showWaitMsg(false);
			    if( resp=="1" )
			    {  
			    
			       alert("Estimado usuario.\nLa licencia numero "+ _numimpreNew+"-"+Digito_verificador(_numimpreNew)+" fue actualizada con exito.");
			       window.close();
			       window.opener.getLmeCero();
			       	       
			    }else
			    {
			       alert(resp);
			       alert("Ha ocurrido un problema al intentar actualizar la licencia "+ _numimpreNew+"-"+Digito_verificador(_numimpreNew));
			       window.close();
			    }
			}  
		    req = new XMLHttp();
			req.get (url,"event=obtieneNumimprela&ideOpe="+ideOpe+"&numimpre="+numimpre+"&afiRut="+afiRut+"&estLicen="+estLicen+"&_ideOpeNew="+_ideOpeNew+"&_numimpreNew="+_numimpreNew+"&numimprela="+numimprela,set);
		    
			return false;
		}
	}
	
	if( isNaN(_numimpreNew) || _numimpreNew=="" )
	{
	   alert("La LME debe ser numerica.");
	   return false;
	}else if(_numimpreNew < 0)
	{
	  		alert("La LME debe ser numero positivo.");
	  		return false;
	}else if(_numimpreNew.length > 9)
	{
	  		alert("La LME debe ser numero de maximo 9 digitos.");
	  		return false;
	}else
	{
	   showWaitMsg(true);
	   url = '/lme/admin.do';	
		var set = function(resp){
	    showWaitMsg(false);
		    if( resp=="1" )
		    {  
		       alert("Estimado usuario.\nLa licencia numero "+ _numimpreNew+"-"+Digito_verificador(_numimpreNew)+" fue actualizada con exito.");
		       window.close();
		       window.opener.getLmeCero();
		       
		       
		    }else
		    {
		       alert("Ha ocurrido un problema al intentar actualizar la licencia "+ _numimpreNew+"-"+Digito_verificador(_numimpreNew));
		       window.close();
		    }
		}  
	    req = new XMLHttp();
		req.get (url,"event=updateLmeCero&ideOpe="+ideOpe+"&numimpre="+numimpre+"&afiRut="+afiRut+"&estLicen="+estLicen+"&_ideOpeNew="+_ideOpeNew+"&_numimpreNew="+_numimpreNew,set);
	
	
	}

//alert( ideOpe+"/"+estLicen+"/"+afiRut+"/"+numimpre+"/"+_ideOpeNew+"/"+_numimpreNew);
}

function consMasiva()
{
	var opciones="toolbar=yes, width=360, height=200, top=85, left=140";
	//var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, width=360, height=200, top=85, left=140";
       window.open("../upload.jsp?cmd=subida","",opciones);
}

function getLmeCero()
{
    showWaitMsg(true);
    showDetailLcc(false);
	showExecLcc(false,'');
    showStatus(false);
    
    
    
    url = '/lme/admin.do';	
	var set = function(json){

	showWaitMsg(false); 

     if( json.length==0 )
     {
        
        //document.getElementById("tablaDescripciones").style.display = 'none';
        //document.getElementById("titulo").innerHTML = "No existen LME con ESTLiCEN 0";
        
        document.getElementById("resultados").style.display = 'inline';
        document.getElementById("resultados").style.display = 'none';
        var t = '';
        t+='<table width="100%" border="0" style="border:#999 1px solid;" class="texto">';
		t+='<tr>';
			  t+='<td style="border-bottom:1px dashed #333;">No existen LME con Estado 0 </td>';
		t+='</tr>';	
		t+='</table>'; 
		document.getElementById('contenido').innerHTML = t; 		
		showPrincipal(true);		
        return false;
     }else
     {
     //document.getElementById("tablaDescripciones").style.display = 'inline';
     document.getElementById("resultados").style.display = 'inline';
     
         var t = '';
        t+='<table width="100%" border="0" style="border:#999 1px solid;" class="texto">';
				
         
         for( var i=0; i<json.length;i++ )
         {
			t+='<tr>';
			  t+='<td width="9%" style="border-bottom:1px dashed #333;">'+json[i].ideOpe+'</td>';
			  t+='<td width="9%" style="border-bottom:1px dashed #333;">'+json[i].numimpre+'</td>';
			  t+='<td width="3%" style="border-bottom:1px dashed #333;">'+json[i].dvimpre+'</td>';
			  t+='<td width="9%" style="border-bottom:1px dashed #333;">'+json[i].afiRut+'</td>';
			  t+='<td width="7%" style="border-bottom:1px dashed #333;text-align:center;">'+json[i].estLicen+'</td>';
			  t+='<td width="7%" style="border-bottom:1px dashed #333;text-align:center;">'+json[i].fechaEstado+'</td>';
			  t+='<td width="7%" style="border-bottom:1px dashed #333;text-align:center;">'+json[i].horaEstado+'</td>';
			  t+='<td width="11%" style="border-bottom:1px dashed #333;text-align:center;">'+json[i].fechaOpr+'</td>';
			  t+='<td width="29%" style="border-bottom:1px dashed #333;">'+json[i].msgErr+'</td>';
			  			  
			  //MODIFICACION 14022013
			  t+='<td width="15%" style="border-bottom:1px dashed #333;text-align:center;"><a href="javascript:;" onclick="OPEN('+json[i].ideOpe+','+json[i].numimpre+','+json[i].afiRut+','+json[i].estLicen+','+json[i].numimprela+');" title="Actualizar"><img src="../img/edit.png" alt="actualizar" width="16" height="16" border="0"></a>';
			  //t+='<td width="15%" style="border-bottom:1px dashed #333;text-align:center;"><a href="javascript:;" onclick="OPEN('+json[i].ideOpe+','+json[i].numimpre+','+json[i].afiRut+','+json[i].estLicen+');" title="Actualizar"><img src="../img/edit.png" alt="update" width="16" height="16" border="0"></a>';    // ORIGINAL
			  t+='<a href="javascript:;" onclick="Delete('+json[i].ideOpe+','+json[i].numimpre+','+json[i].afiRut+','+json[i].estLicen+','+json[i].numimprela+');" title="Eliminar"><img src="../img/delete.png" alt="Eliminar" width="16" height="16" border="0"></a>';
			  //t+='<a href="javascript:;" onclick="Delete('+json[i].ideOpe+','+json[i].numimpre+','+json[i].afiRut+','+json[i].estLicen+','+json[i].numimprela+');" title="Actualizar"><img src="../img/delete.png" alt="eliminar" width="16" height="16" border="0"></a>';    //ORIGINAL
			  //FIN MODIFICACION
			  
			  /*FRM MODIFICACION 05022013*/
			  t+='<a href="javascript:;" onclick="consumirLme('+json[i].numimpre+','+json[i].ideOpe+');" title="Consumir"><img src="../img/martillo.PNG" alt="consumir" width="16" height="16" border="0"></a>';
			  //t+='<a href="javascript:;" onclick="consumirLme('+json[i].ideOpe+','+json[i].numimpre');" title="Consumir"><img src="../img/martillo.PNG" alt="consumir" width="16" height="16" border="0"></a>';
			  /**Fin modificacion FRM*/
			  
			  t+='</td>';
			t+='</tr>';

         }
            t+='</table>';
            
			document.getElementById('contenido').innerHTML = t;
            showPrincipal(true);

     }
     

	};
	req = new XMLHttp();
	req.get (url,"event=getLmeCero",set);

}

function getLmeCeroDelete()
{
    showWaitMsg(true);
    showDetailLcc(false);
	showExecLcc(false,'');
    showStatus(false);
    
    
    
    url = '/lme/admin.do';	
	var set = function(json){

	//showWaitMsg(false); 

     if( json.length==0 )
     {
        
        //document.getElementById("tablaDescripciones").style.display = 'none';
        //document.getElementById("titulo").innerHTML = "No existen LME con ESTLiCEN 0";
        
        document.getElementById("resultados").style.display = 'inline';
        document.getElementById("resultados").style.display = 'none';
        var t = '';
        t+='<table width="100%" border="0" style="border:#999 1px solid;" class="texto">';
		t+='<tr>';
			  t+='<td style="border-bottom:1px dashed #333;">No existen LME con Estado 0 </td>';
		t+='</tr>';	
		t+='</table>'; 
		document.getElementById('contenido').innerHTML = t; 		
		showPrincipal(true);		
        return false;
     }else
     {
     //document.getElementById("tablaDescripciones").style.display = 'inline';
     document.getElementById("resultados").style.display = 'inline';
     
         var t = '';
        t+='<table width="100%" border="0" style="border:#999 1px solid;" class="texto">';
				
         
         for( var i=0; i<json.length;i++ )
         {
			t+='<tr>';
			  t+='<td width="10%" style="border-bottom:1px dashed #333;">'+json[i].ideOpe+'</td>';
			  t+='<td width="14%" style="border-bottom:1px dashed #333;">'+json[i].numimpre+'</td>';
			  t+='<td width="13%" style="border-bottom:1px dashed #333;">'+json[i].afiRut+'</td>';
			  t+='<td width="8%" style="border-bottom:1px dashed #333;text-align:center;">'+json[i].estLicen+'</td>';
			  t+='<td width="12%" style="border-bottom:1px dashed #333;text-align:center;">'+json[i].fechaOpr+'</td>';
			  t+='<td width="35%" style="border-bottom:1px dashed #333;">'+json[i].msgErr+'</td>';
			  			  
			  //MODIFICACION 14022013
			  t+='<td width="15%" style="border-bottom:1px dashed #333;text-align:center;"><a href="javascript:;" onclick="OPEN('+json[i].ideOpe+','+json[i].numimpre+','+json[i].afiRut+','+json[i].estLicen+','+json[i].numimprela+');" title="Actualizar"><img src="../img/edit.png" alt="update" width="16" height="16" border="0"></a>';
			  //t+='<td width="15%" style="border-bottom:1px dashed #333;text-align:center;"><a href="javascript:;" onclick="OPEN('+json[i].ideOpe+','+json[i].numimpre+','+json[i].afiRut+','+json[i].estLicen+');" title="Actualizar"><img src="../img/edit.png" alt="update" width="16" height="16" border="0"></a>';    // ORIGINAL
			  t+='<a href="javascript:;" onclick="Delete('+json[i].ideOpe+','+json[i].numimpre+','+json[i].afiRut+','+json[i].estLicen+','+json[i].numimprela+');" title="Eliminar"><img src="../img/delete.png" alt="Eliminar" width="16" height="16" border="0"></a>';
			  //t+='<a href="javascript:;" onclick="Delete('+json[i].ideOpe+','+json[i].numimpre+','+json[i].afiRut+','+json[i].estLicen+','+json[i].numimprela+');" title="Actualizar"><img src="../img/delete.png" alt="eliminar" width="16" height="16" border="0"></a>';    //ORIGINAL
			  //FIN MODIFICACION
			  
			  /*FRM MODIFICACION 05022013*/
			  t+='<a href="javascript:;" onclick="consumirLme('+json[i].numimpre+','+json[i].ideOpe+');" title="Consumir"><img src="../img/martillo.PNG" alt="consumir" width="16" height="16" border="0"></a>';
			  //t+='<a href="javascript:;" onclick="consumirLme('+json[i].ideOpe+','+json[i].numimpre');" title="Consumir"><img src="../img/martillo.PNG" alt="consumir" width="16" height="16" border="0"></a>';
			  /**Fin modificacion FRM*/
			  
			  t+='</td>';
			t+='</tr>';

         }
            t+='</table>';
            
			document.getElementById('contenido').innerHTML = t;
            showPrincipal(true);

     }
     

	};
	req = new XMLHttp();
	req.get (url,"event=getLmeCero",set);

}

function OPEN(ide,num,afi,est,imprela)
{
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, width=500, height=330, top=85, left=140";
       window.open("../popup.jsp?i="+ide+"&n="+num+"&a="+afi+"&e="+est+"&imp="+imprela,"",opciones);
}

//delete
function Delete(ide,num,afi,est,numla)
{
     
    url = '/lme/admin.do';	
    
	var set = function(resp){
	
	    if( resp=="ILF1000" )
	    {  
	       showWaitMsg(false); 
	       alert("Estimado usuario.\nLa licencia numero ["+ num+"] se encuentra integrada en SIL.");
	       return false;
	    }
	     if( resp!="ILF1000" && resp!="0" )
	     {
	        showWaitMsg(false); 
	        alert(resp);
	        return false;
	     }
	     
	     
	     if( resp=="0" )
	        {
	        showWaitMsg(false); 
	        alert("Estimado usuario.\nLa licencia número ["+ num+"], se ha eliminado con exito.");
	        window.getLmeCero();
	        }
	    
	    
	    
	}  
	
	if( confirm("¿Esta seguro de eliminar\n la licencia número ["+num+"]?") )
	{
	//showWaitMsg(true); 
	req = new XMLHttp();
	req.get (url,"event=DeleteLmeCero&ideOpe="+ide+"&numimpre="+num+"&afiRut="+afi+"&estLicen="+est+"&licimpnum="+numla,set);
	getLmeCeroDelete();
	}
	
}

/**FRM MODIFICACION 05022013*/
function consumirLme(licencia,operador){
	
	url = '/lme/lme.do';
	
	/* var set = function(resp){
	 	if( resp=="0" )
	    {
	       showWaitMsg(false); 
	       alert("Estimado usuario.\nLa licencia número "+ licencia+", se ha consumido con exito.");
	       window.getLmeCero();
	    }
	  }*/
	/*
	 var respuesta = confirm("Estimado usuario ¿Desea consumir la licencia " + licencia + "?");
	 if(respuesta == true){
	  	req = new XMLHttp();
	   	//alert(operador+" - "+licencia);
	   	*/
	   	
		levantarConsumirLicencias(licencia,operador);
		//req.get(url,"event=execLMEDetLcc&numimpre="+licencia + "&ideOpe="+operador,set);
	 //} 
}

function levantarConsumirLicencias(licencia,operador){
   //alert("licencia " +licencia+" operador "+operador);
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=yes, width=500, height=330, top=85, left=140";
	window.open("../consumirLicencias.jsp?n="+licencia+"&i="+operador,"",opciones);
}

function CerrarConsumo(){
	window.close();
}
/*Fin modificacion*/

function setStatus(data){
    showPrincipal(false);
	showDetailLcc(false);
	showExecLcc(false,'');
	showStatus(true);
	data =  eval( "("+data+")" ) ;    
	msg= '<br/>&nbsp;&nbsp;'+data.version+'<br/>&nbsp;&nbsp;'+data.message;
	document.getElementById("divStatus").innerHTML=msg;		
}
//LMEDetLcc
function displayDetailForm(){
    
    showPrincipal(false);
	showStatus(false);
	showExecLcc(false,'');
	showDetailLcc(true);
}
function exec(event){
    showPrincipal(false);
	url = '/lme/lme.do';	
	req = new XMLHttp();
	req.get (url,"event="+event,setStatus);
}
function paso(data){
	//status
	req2 = new XMLHttp();
	req2.get(url,"event=status",setStatus);
	
}
function start(){
    showPrincipal(false);
	url = '/lme/lme.do';	
	req = new XMLHttp();
	req.get (url,"event=start",paso);
	//status
	req2 = new XMLHttp();
	req2.get(url,"event=status",setStatus);
}
//log
function displayLog(){
    showPrincipal(false);
	url = '/lme/log.do';
	var set = function(data){
		msg = '';
		for(i=0; i<data.length; i++){
			if(data==''){
				alert('No se encuenta log para hoy '+ new Date());
				return;
			}
			msg +=	formatDate(data[i].fecha)+' '+
					formatHour(data[i].hora)+' '+
					data[i].tipo+'&nbsp;&nbsp;'+
					data[i].msg+'<br/>';
		}		
		document.getElementById("logDiv").innerHTML=msg;		
	};
	req = new XMLHttp();
	req.onError = function(r){alert(r.responseText);};
	req.get (url,"event=getLog",set);
}

function getDateFormat(){
	now = new Date();
	now.format("dd-MM-yyyy");
}

function formatDate(d){
	return d.substring(6,8)+'/'+d.substring(4,6)+'/'+d.substring(0,4);	
}

function formatHour(h){
	if(h.length==6){
		h = h.substring(0,2)+':'+h.substring(2,4)+':'+ h.substring(4,6);
	}else{
		h = h.substring(0,1)+':'+h.substring(1,3)+':'+ h.substring(3,5);
	}
	
	return h;
}	


function getLMEDetLcc(){	
	codOpe = document.getElementById("codOpe").value;
	license = document.getElementById("license").value.trim();
	
	
	
	if(isNaN(license) || license==''){
		alert('Licencia inv\u00e1lida');
	
		document.getElementById("license").value='';
		document.getElementById("license").focus();
		return;
	}
	showWaitMsg(true);
	url = '/lme/lcc.do';
	var set = function(data){		
		showWaitMsg(false);		
		//LoadXML("spanLcc",data);		
		
	
		
		xmlDoc = CreateXMLDOM(data);
		//res = LoadXMLDom("spanLcc",xmlDoc);
		
		t = '<br/><br/><table class="texto">';//<tr><td>Item</td><td>Valor</td></tr>
		
			
		if(xmlDoc.getElementsByTagName('LME').length > 0){
			
			//ZONA_01
			xmlFragment = xmlDoc.getElementsByTagName('ZONA_01');
			if(xmlFragment.length > 0){
				t += zoneHtml('ZONA 0');//Inf. Licencia
				t += '<tbody id="ZONA_0">';
				t = t+'<tr><td class="vde-menu">Cod. Operador</td><td align="right" class="vde-menu">'+getNode(xmlFragment[0], 'codigo_operador')+'</td></tr>';		
				t = t+'<tr><td>Rut Operador</td><td align="right">'+getNode(xmlFragment[0], 'rut_operador')+'</td></tr>';
				t = t+'<tr><td class="vde-menu">ID Licencia</td><td align="right" class="vde-menu">'+getNode(xmlFragment[0], 'id_licencia')+'</td></tr>';
				t = t+'<tr><td>Cod. Tipo Formulario</td><td align="right">'+getNode(xmlFragment[0], 'codigo_tipo_formulario')+'</td></tr>';
				l0 = xmlDoc.getElementsByTagName('estado_licencia');
				l1 = xmlDoc.getElementsByTagName('fecha_estado');
				l2 = xmlDoc.getElementsByTagName('estado');
				
				if(0 < l0.length)
					t += '<td colspan="2"><table  class="texto" width="100%"><tr><td class="textos_formcolor">Fecha Estado Licencia</td><td align="right" class="textos_formcolor">Estado</td><td align="right" class="textos_formcolor">Cod.Tramitaci&#243;n</td><td align="right" class="textos_formcolor">Descripci&#243;n Tr&#225;mite</td></tr>';
				
				for(i=0; i< l0.length; i++){
//					t = t+'<tr><td>Estado Licencia</td><td align="right">'+l0[i].childNodes[0].nodeValue+'</td></tr>';
//					t = t+'<tr><td>Fecha Estado</td><td align="right">'+l1[i].childNodes[0].nodeValue+'</td></tr>';
					f = l1[i].childNodes[0].nodeValue;
					if(f.length>10)f = f.substring(0,10);
					c = i%2==0?'':' class="vde-menu"';
					
					ccaf=l2[i].getAttribute('codigo_tramitacion_CCAF');
					desc=codigoTramitacionCCAF(ccaf);
					
					t += '<tr><td'+c+'>'+f+'</td><td align="right"'+c+'>'+l0[i].childNodes[0].nodeValue+'</td ><td align="right"'+c+'>'+ccaf+'</td ><td align="right"'+c+'>'+desc +'</td ></tr>';
				}
				if(0 < l0.length) t += '</table></td>';
				t += '</tbody>';
			}			
			y=1;
			//ZONA_A1
			xmlFragment = xmlDoc.getElementsByTagName('trabajador');
			if(xmlFragment.length > 0){
				t += zoneHtml('ZONA A');						
				t = t+'<tr><td'+' class="vde-menu"'+'>Ap. Paterno Afiliado</td><td align="right"'+' class="vde-menu"'+'>'+getNode(xmlFragment[0], 'apellido_paterno')+'</td></tr>';
				t = t+'<tr><td>Ap. Materno Afiliado</td><td align="right">'+getNode(xmlFragment[0], 'apellido_materno')+'</td></tr>';
				t = t+'<tr><td'+' class="vde-menu"'+'>Nombres Afiliado</td><td align="right"'+' class="vde-menu"'+'>'+getNode(xmlFragment[0], 'nombres')+'</td></tr>';
				t = t+'<tr><td>Rut Afiliado</td><td align="right">'+getNode(xmlFragment[0], 'rut')+'</td></tr>';
				t = t+'<tr><td'+' class="vde-menu"'+'>Edad Afiliado</td><td align="right"'+' class="vde-menu"'+'>'+getNode(xmlFragment[0], 'edad')+' a&#241;os</td></tr>';
				sexo = getNode(xmlFragment[0], 'sexo')=='F'? 'Femenino':'Masculino';
				t = t+'<tr><td>Sexo Afiliado</td><td align="right">'+ sexo +'</td></tr>';//=='F'?'Femenino':'Masculino'
				t = t+'<tr><td'+' class="vde-menu"'+'>Fecha emisi&#243;n LME</td><td align="right"'+' class="vde-menu"'+'>'+getNode(xmlDoc, 'fecha_emision')+'</td></tr>';
				
				t = t+'<tr><td>Fecha inicio reposo</td><td align="right">'+getNode(xmlDoc, 'fecha_inicio_reposo')+'</td></tr>';
				t+= itemHtml('Cod. tipo reposo', xmlDoc, 'codigo_tipo_reposo', y++);
				t+= itemHtml('Cod. lugar reposo', xmlDoc, 'codigo_lugar_reposo', y++);
				
				xmlFragment = xmlDoc.getElementsByTagName('direccion_reposo');
				t+= itemHtml('Direcci&#243;n Reposo', xmlFragment[0], 'calle', y++);
				t+= itemHtml('Cod. Comuna Reposo', xmlFragment[0], 'comuna', y++);
				t+= itemHtml('Pais Reposo', xmlFragment[0], 'pais', y++);
				
				xmlFragment = xmlDoc.getElementsByTagName('profesional');
				t+= itemHtml('Especialidad del Profesional', xmlDoc, 'prof_especialidad', y++);
				t+= itemHtml('Nombres Profesional', xmlFragment[0], 'nombres', y++);
				t+= itemHtml('Ap. Paterno Profesional', xmlFragment[0], 'apellido_paterno', y++);
				t+= itemHtml('Ap. Materno Profesional', xmlFragment[0], 'apellido_materno', y++);
				t+= itemHtml('Rut del Profesional', xmlFragment[0], 'rut', y++);
				t+= itemHtml('E-mail Profesional',xmlDoc, 'prof_email', y++);
				
				
			}
			
			//ZONA_B
			xmlFragment = xmlDoc.getElementsByTagName('ZONA_B1');
			if(xmlFragment.length > 0){
				t+= zoneHtml('ZONA B');
				t+= itemHtml('Entidad', xmlFragment[0], 'entidad', y++);
				t+= itemHtml('Nro. Resoluci&#243;n', xmlFragment[0], 'n_resolucion', y++);
				t+= itemHtml('C&#243;digo Establecimiento', xmlFragment[0], 'codigo_establecimiento', y++);
				t+= itemHtml('C&#243;digo diagnostico', xmlFragment[0], 'codigo_diagnostico', y++);
				t+= itemHtml('C&#243;digo Tipo resoluci&#243;n', xmlFragment[0], 'codigo_tipo_resolucion', y++);
				t+= itemHtml('Dias Aut. Licencia', xmlFragment[0], 'entidad_ndias', y++);
				t+= itemHtml('Fecha Aut. Desde', xmlFragment[0], 'entidad_fecha_desde', y++);
				t+= itemHtml('Fecha Aut. Hasta', xmlFragment[0], 'entidad_fecha_hasta', y++);
			}
			
			//ZONA_C
			xmlFragment = xmlDoc.getElementsByTagName('ZONA_C1');
			if(xmlFragment.length > 0){
				//ZONA_C1
				t+= zoneHtml('ZONA C');
				t+= itemHtml('Nombre Empleador', xmlFragment[0], 'emp_nombre', y++);
				t+= itemHtml('Rut Empleador', xmlFragment[0], 'emp_rut', y++);				
				t+= itemPhoneHtml('Tel&#233;fono Empleador', xmlFragment[0], 'emp_telefono', y++);				
				t+= itemHtml('Direcci&#243;n calle Empleador', xmlFragment[0], 'calle', y++);
				t+= itemHtml('Direcci&#243;n n&#250;mero Empleador', xmlFragment[0], 'numero', y++);				
				t+= itemHtml('C&#243;digo actividad laboral', xmlFragment[0], 'codigo_actividad_laboral', y++);
				
			}
			//ZONA_C2
			xmlFragment = xmlDoc.getElementsByTagName('ZONA_C2');
			if(xmlFragment.length > 0){				
				t+= itemHtml('C&#243;digo tipo r&#233;gimen previsional', xmlFragment[0], 'codigo_tipo_regimen_previsional', y++);				
				t+= itemHtml('C&#243;digo r&#233;gimen previsional', xmlFragment[0], 'codigo_regimen_previsional', y++);
				t+= itemHtml('C&#243;digo calidad trabajador', xmlFragment[0], 'codigo_calidad_trabajador', y++);	
				t+= itemHtml('C&#243;digo seguro AFC', xmlFragment[0], 'codigo_seguro_afc', y++);
				t+= itemHtml('Fecha afiliaci&#243;n', xmlFragment[0], 'fecha_afiliacion', y++);
				t+= itemHtml('Fecha contrato', xmlFragment[0], 'fecha_contrato', y++);
				t+= itemHtml('C&#243;digo entidad pagadora', xmlFragment[0], 'codigo_entidad_pagadora', y++);
				t+=itemHtml('Caja de compensaci&#243;n', xmlFragment[0], 'prev_nombre_pagador', y++);
			}
			//ZONA_CC
			xmlFragment = xmlDoc.getElementsByTagName('ZONA_CC');
			if(xmlFragment.length > 0){
				//Haberes				
				//t+= itemHtmlHaberes(' Haberes C&#243;digo Tramitaci&#243;n', xmlFragment[0], 'codigo_tramitacion_CCAF', y++);
				
				//Rentas
				fecha=xmlFragment[0].getElementsByTagName('ano_mes_renta');
				nombre=xmlFragment[0].getElementsByTagName('nombre_haber');
				monto=xmlFragment[0].getElementsByTagName('monto_haber');
				if(0 < monto.length)
					t += '<td colspan="2"><table width="90%" class="texto"><tr><td class="textos_formcolor">Nombre renta</td><td align="right" class="textos_formcolor">Monto</td><td class="textos_formcolor" align="right">Periodo</td></tr>';
				for(i=0; i<monto.length; i++){
					s= i%2==0? '':' class="vde-menu"';
//					t = t+'<tr><td>Nombre renta</td><td align="right">'+nombre[i].childNodes[0].nodeValue+'</td></tr>';
//					t = t+'<tr><td>Monto renta</td><td align="right">'+monto[i].childNodes[0].nodeValue+'</td></tr>';
//					t = t+'<tr><td>Fecha renta</td><td align="right">'+fecha[i].childNodes[0].nodeValue+'</td></tr>';
					m = monto[i].childNodes[0].nodeValue;
					t += '<tr><td'+s+'>'+nombre[i].childNodes[0].nodeValue+'</td><td align="right"'+s+'>'+m.numFormat(0)+'</td><td'+s+' align="right">'+fecha[i].childNodes[0].nodeValue+'</td></tr>';
				}
				if(0 < monto.length) t += '</table></td>';
				
				url = xmlFragment[0].getElementsByTagName('url_archivo');
				for(i=0; i<url.length; i++){
					s= i%2==0? '':' class="vde-menu"';
					uri = url[i].childNodes[0].nodeValue;
					shortUri= uri.substring(0,25)+'...';
					link = '<a href="'+uri+'" >'+shortUri+'</a>';
					t = t+'<tr><td'+s+'>Haberes Url Archivo</td><td align="right"'+s+'>'+link+'</td></tr>';
				}
				
				
			}
			
			
			
		}else{
			t+= itemHtml('Error', xmlDoc, 'error',0);
		}
		
		
		t += '</table>';		
		$('spanLcc').innerHTML = t;
		
	};	
	
	req = new XMLHttp();
	req.onError = function(r){showWaitMsg(false);alert(r.responseText);}; 
	req.get (url,"event=getLMEDetLcc&codOpe="+codOpe+"&lic="+license,set);	
}

function itemPhoneHtml(label,xmlDoc, tagName, i){
	s= i%2==0? '':' class="vde-menu"';
	row = "";
	empTel = xmlDoc.getElementsByTagName(tagName);
	area = "";
	pais = "";
	if(empTel.length > 0){
		area=null==empTel[0].getAttribute('codigo_area')?'':empTel[0].getAttribute('codigo_area')+'-';
		pais=null==empTel[0].getAttribute('codigo_pais')?'':empTel[0].getAttribute('codigo_pais')+'-';
	}
	n = xmlDoc.getElementsByTagName('telefono');
	if(n.length > 0)
		row = '<tr><td'+s+'>'+label+'</td><td align="right"'+s+'>'+pais+area+n[0].childNodes[0].nodeValue+'</td></tr>';
	
	return row;	
}

function codigoTramitacionCCAF(cod){
	desc = '';
	try{
		cod = cod*1;
		switch (cod) {
		case 10100:
			desc = 'NO SE TRAMITA EN CCAF';
			break;
		case 10101:
			desc = 'SE TRAMITA EN CCAF 18 DE SEPTIEMBRE<';
			break;
		case 10102:
			desc = 'SE TRAMITA EN CCAF LOS ANDES';
			break;	
		case 10103:
			desc = 'SE TRAMITA EN CCAF GABRIELA MISTRAL';
			break;	
		case 10105:
			desc = 'SE TRAMITA EN CCAF LA ARAUCANA';
			break;
		case 10106:
			desc = 'SE TRAMITA EN CCAF LOS HEROES';
			break;
		default:
			break;
		}
		//row = '<tr><td'+s+'>'+label+'</td><td align="right"'+s+'>'+cod+'-'+desc+'</td></tr>';
		//desc = desc+'-'+cod;
	}catch(e){
		desc = cod;
	}
	return desc;
}


function itemHtml(label,xmlDoc, tagName, i){
	row = "";
	s= i%2==0? '':' class="vde-menu"';
	n = xmlDoc.getElementsByTagName(tagName);
	try{
	if(n.length > 0)
		row = '<tr><td'+s+'>'+label+'</td><td align="right"'+s+'>'+n[0].childNodes[0].nodeValue+'</td></tr>';
	}catch(e){
		row = "";
	}
	return row;
}

function zoneHtml(s){
//	zone = "'ZONE_0'";
//	onclick="showTable('+zone+')"
	return '<tr><td colspan="2" align="center">'+s+'</td></tr>';
	
}
function getNode(xmlDoc, tag){
	n = xmlDoc.getElementsByTagName(tag);	
	return n[0].childNodes[0].nodeValue;
}

function showTable(tbody){
	document.getElementById(tbody).style.display = 'none';
}

function showPrincipal(b)
{
  document.getElementById("principal").style.display = b? 'inline':'none';
}
function showDetailLcc(b){
	document.getElementById("divLcc").style.display = b? 'inline':'none';
}
function showStatus(b){
	document.getElementById("divStatus").style.display = b? 'inline':'none';
}
function showWaitMsg(b){
	document.getElementById("loadingmsg").style.display = b? 'inline':'none';
}

// init status
function schedulerStatus() {
	//divStatus	divLcc
	adm = document.getElementById("admin").value=='admin'? true:false;	
	showStatus(adm);
	showDetailLcc(!adm);
	//
	if(adm){
		url = '/lme/lme.do';	
		req = new XMLHttp();
		req.onError = function(r){alert(r.responseText);};
		req.get (url,"event=status",setStatus);
	}
	opeVordel();
}

// get operator
function opeVordel(){
	url = '/lme/lcc.do';
	var set = function(data){
		
		addOption($("codOpe"), data, 'codOpe', 'nomOpe');
		addOption($("codOpe_"), data, 'codOpe', 'nomOpe');
	};
	req = new XMLHttp();
	req.get (url,"event=getOpeVordel",set);
}


//lof file
function getLogFile(){
	url = '/lme/log.do';
	window.location.href=url+"?event=exportLogFile";	
}

function getDateFormat(){
	now = new Date();
	return now.format("Y-m-d");
}

function Digito_verificador(rut) {     
	var ag=rut.split('').reverse()     
	for(total=0,n=2,i=0;i<ag.length;((n==7) ? n=2 : n++),i++)     
	{         
		total+=ag[i]*n     
	}     
	var resto=11-(total%11)     
	return (resto<10)?resto:((resto>10)?0:'k') 
}

function XMLHttp(){
	this.async  = true;
	this.method = 'post';	// ajax method: post or get, post by default
	// public methods to redefine: w3schools.com
    this.onRequest	= function(){};                  // method to do before all process
    this.onSetUp	= function(){};                  // method to do when The request has been set up
    this.onSend		= function(){};                  // method to do when The request has been sent
    this.onProcess 	= function(){};                  // method to do when The request is in process
    this.onComplete = function(){};                  // method to do when The request is complete
    this.onError 	= function(){};                  // method to do when The request return error
	                           
	var XMLHttpReq = function() {
	  if (typeof XMLHttpRequest != 'undefined')
	  	return new XMLHttpRequest();
	  else if (window.ActiveXObject) {
	    var avers = ["Microsoft.XmlHttp", "MSXML2.XmlHttp", "MSXML2.XmlHttp.3.0",  "MSXML2.XmlHttp.4.0", "MSXML2.XmlHttp.5.0", "Msxml2.XMLHTTP.6.0"];
	    for (var i = avers.length -1; i >= 0; i--) {
	      try {httpObj = new ActiveXObject(avers[i]);
	      return httpObj;
	      } catch(e) {}
	    }
	  }
	  throw new Error('XMLHttp (AJAX) not supported');
	};
	var req = XMLHttpReq();	
	this.get = function(url,params,callback) {
	  var t = this;
	  req.open(t.method, url, t.async);
	  req.onreadystatechange = function(){
	  	switch(req.readyState)
	  	{
	        case 1:                         // if readystate is 1 The request has been set up
	            t.onSetUp();
	            break;
	        case 2:                         // if readystate is 2 The request has been sent
	            t.onSend();
	            break;
	        case 3:                         // if readystate is 3 The request is in process
	            t.onProcess();
	            break;
	        case 4:                         // if readystate is 4 the request is complete
	            processRequest(t, req, callback);
	            break;
         }
	  };
	  req.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
      req.send(params);
       
	};
	var processRequest = function(t,r,callback) {//alert(r.status);
		var cType = r.getResponseHeader('Content-Type').split(';')[0];
		if(r.status==200)                  // if status is 200 petition OK     
            if(cType=='text/xml'){          // XML response
                t.onComplete();
                callback(r.responseXML);
            }else if(cType=='text/json'){   // JSON response
            	var jsonExpression = eval( "("+r.responseText+")" ) ;            	
            	//var data = jsonExpression.DTO_array != null ? jsonExpression.DTO_array.DTO: jsonExpression.DTO;            	
                t.onComplete();
                
                //alert(jsonExpression);
                
                callback(jsonExpression);
            }else{                          // plain text response
                t.onComplete();
                callback(r.responseText);
            }
        else
            t.onError(r);           
	};
}

function addOption(selectItem, data, value, label){
	var setOption = function (i,d){
		v = d[value];
		l = d[label];
		selectItem.options[i]=new Option(l,v);
	};
	if (data.length==null){
		setOption(0,data);
	}else{
		for(i=0; i<data.length; i++)
			setOption(i,data[i]);
	}	
}
//
function jObject(){
	this.list = {};
	this.toURLString = function(){
		var t = this;
		o = t.list;
		var a = [];
		for (var n in o){
			a[a.length] = encodeURIComponent(n) + '=' + encodeURIComponent(o[n]);
		}
		return a.join('&');
	};
}

//
function $(element){
	return document.getElementById(element);
}
Date.prototype.format=function(format){var returnStr='';var replace=Date.replaceChars;for(var i=0;i<format.length;i++){var curChar=format.charAt(i);if(i-1>=0&&format.charAt(i-1)=="\\"){returnStr+=curChar}else if(replace[curChar]){returnStr+=replace[curChar].call(this)}else if(curChar!="\\"){returnStr+=curChar}}return returnStr};Date.replaceChars={shortMonths:['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],longMonths:['January','February','March','April','May','June','July','August','September','October','November','December'],shortDays:['Sun','Mon','Tue','Wed','Thu','Fri','Sat'],longDays:['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'],d:function(){return(this.getDate()<10?'0':'')+this.getDate()},D:function(){return Date.replaceChars.shortDays[this.getDay()]},j:function(){return this.getDate()},l:function(){return Date.replaceChars.longDays[this.getDay()]},N:function(){return this.getDay()+1},S:function(){return(this.getDate()%10==1&&this.getDate()!=11?'st':(this.getDate()%10==2&&this.getDate()!=12?'nd':(this.getDate()%10==3&&this.getDate()!=13?'rd':'th')))},w:function(){return this.getDay()},z:function(){var d=new Date(this.getFullYear(),0,1);return Math.ceil((this-d)/86400000)}, W:function(){var d=new Date(this.getFullYear(),0,1);return Math.ceil((((this-d)/86400000)+d.getDay()+1)/7)},F:function(){return Date.replaceChars.longMonths[this.getMonth()]},m:function(){return(this.getMonth()<9?'0':'')+(this.getMonth()+1)},M:function(){return Date.replaceChars.shortMonths[this.getMonth()]},n:function(){return this.getMonth()+1},t:function(){var d=new Date();return new Date(d.getFullYear(),d.getMonth(),0).getDate()},L:function(){var year=this.getFullYear();return(year%400==0||(year%100!=0&&year%4==0))},o:function(){var d=new Date(this.valueOf());d.setDate(d.getDate()-((this.getDay()+6)%7)+3);return d.getFullYear()},Y:function(){return this.getFullYear()},y:function(){return(''+this.getFullYear()).substr(2)},a:function(){return this.getHours()<12?'am':'pm'},A:function(){return this.getHours()<12?'AM':'PM'},B:function(){return Math.floor((((this.getUTCHours()+1)%24)+this.getUTCMinutes()/60+this.getUTCSeconds()/ 3600) * 1000/24)}, g:function(){return this.getHours()%12||12},G:function(){return this.getHours()},h:function(){return((this.getHours()%12||12)<10?'0':'')+(this.getHours()%12||12)},H:function(){return(this.getHours()<10?'0':'')+this.getHours()},i:function(){return(this.getMinutes()<10?'0':'')+this.getMinutes()},s:function(){return(this.getSeconds()<10?'0':'')+this.getSeconds()},u:function(){var m=this.getMilliseconds();return(m<10?'00':(m<100?'0':''))+m},e:function(){return"Not Yet Supported"},I:function(){return"Not Yet Supported"},O:function(){return(-this.getTimezoneOffset()<0?'-':'+')+(Math.abs(this.getTimezoneOffset()/60)<10?'0':'')+(Math.abs(this.getTimezoneOffset()/60))+'00'},P:function(){return(-this.getTimezoneOffset()<0?'-':'+')+(Math.abs(this.getTimezoneOffset()/60)<10?'0':'')+(Math.abs(this.getTimezoneOffset()/60))+':00'},T:function(){var m=this.getMonth();this.setMonth(0);var result=this.toTimeString().replace(/^.+ \(?([^\)]+)\)?$/,'$1');this.setMonth(m);return result},Z:function(){return-this.getTimezoneOffset()*60},c:function(){return this.format("Y-m-d\\TH:i:sP")},r:function(){return this.toString()},U:function(){return this.getTime()/1000}};
String.prototype.trim = function(){
	a = this.replace(/^\s+/,'');
	return a.replace(/\s+$/,'');
};
String.prototype.numFormat = function(n) {
	nn = (this.replace(',','.') * 1).toFixed(n);
	nStr = nn+'';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? ',' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + '.' + '$2');
	}
	return x1 + x2;
};
