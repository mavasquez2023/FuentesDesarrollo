// utils.js

function keyRut(){
	//guion= event.keyCode!= 45
	if ( !isKeyNum() && !(event.keyCode== 75 || event.keyCode==107 || event.keyCode==45 || event.charCode== 75 || event.charCode==107 || event.charCode==45)) event.returnValue = false;

}
//permite key presses de solo números.
function keyNum(){
	if (!isKeyNum()) event.returnValue = false;
}

function isKeyNum(){
	if (event.keyCode >= 48 && event.keyCode <= 57) return true;
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

function getNode(xmlDoc, tag){
	n = xmlDoc.getElementsByTagName(tag);	
	return n[0].childNodes[0].nodeValue;
}

function showTable(tbody){
	document.getElementById(tbody).style.display = 'none';
}


function showWaitMsg(b){
	document.getElementById("loadingmsg").style.display = b? 'inline':'none';
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

function addCabeceras(data, concepto, valor){
	var setItem = function (d){
		c = d[concepto];
		v = d[valor];
		document.getElementById(c).value=v;
	};
	if (data.length==null){
		setItem(data);
	}else{
		for(i=0; i<data.length; i++){
			setItem(data[i]);
		}
	}	
}

function addCampanha(data, concepto, valor){
	var setItem = function (d){
		c = d[concepto];
		v = d[valor];
		document.getElementById(c).innerHTML=v;
	};
	if (data.length==null){
		setItem(data);
	}else{
		for(i=0; i<data.length; i++){
			setItem(data[i]);
		}
	}	
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
		for(i=0; i<data.length; i++){
			setOption(i,data[i]);
		}
	}	
}

function addOption_Disabled(selectItem, data, value, label){
	var setOption = function (i,d){
		v = d[value];
		l = d[label];
		selectItem.options[i]=new Option(l,v);
	};
	var setDisabled = function (d){
		d = d['disabled'];
		if(d=='true'){
			selectItem.setAttribute("disabled", "disabled");
		}else{
			selectItem.removeAttribute("disabled");
		}
	};
	if (data.length==null){
		setOption(0,data);
	}else{
		for(i=0; i<data.length; i++){
			if(i==data.length-1){
				setDisabled(data[i]);
			}else{
				setOption(i,data[i]);
			}
		}
		
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

//Valida si el DV del rut es correcto
//como parametro se debe ingrsar el valor del campo Rut con formato
//retorna true o false
function validaRut(rut){
var sRut = quitaFormato(rut, '.-');
var sDV = new String(sRut.charAt(sRut.length-1));
sRut = sRut.substring(0, sRut.length-1);
if(sDV.toUpperCase() == getDV(sRut)){
  return true;
}
else{
  return false;
}
}

//Calcula el DV de un rut
//como parámetro se debe ingresar la parte numerica del rut
//retorna el DV del rut
function getDV(rut) {
  var sum, factor, i, rest, dv;

  sum = 0;
  factor  = 2;

  for (i = rut.length - 1; i >= 0; i--) {
      sum = sum + rut.charAt(i) * factor;
      
      if (factor == 7) {
          factor = 2;
      } else {
          factor++;
      }
  }

  rest = sum % 11;

  if (rest == 1) {
      dv = 'K';
  } else if (rest == 0) {
      dv = '0';
  } else {
      dv = (11 - rest) + "";
  }
  return dv;
}

//Quita todos los caracteres que que estan en "bag" del string "s".
//como primer parámetro se debe ingresar el valor del campo y como segundo parámetro los caracteres a eliminar
//retorna string numérico sin formato
function quitaFormato (s, bag){   
  var returnString = "";

  // Buscar por el string, si el caracter no esta en "bag", 
  // agregarlo a returnString
  for (var i = 0; i < s.length; i++)
  {   var c = s.charAt(i);
      if (bag.indexOf(c) == -1) returnString += c;
  }

  return returnString;
}
