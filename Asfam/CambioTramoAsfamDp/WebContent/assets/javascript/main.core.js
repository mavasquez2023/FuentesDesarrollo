function marcarTodos(value) {
	if (document.forms[0].TODOS_BONO.checked) {
	    marcarTodosBonos(value);
	}
	document.forms[0].TODOS_BONO.checked = false;
}

function marcarTodosBonos(value) {
	for (j = 0; j < document.forms[0].elements.length; j++) {
		if (document.forms[0].elements[j].name.indexOf('option') == 0) {
			marcarItem(document.forms[0].elements[j], value);
		}
	}
}

function modificar(){

 document.forms[0].estado.value="1";

 

}

function habilitar(){
 
 document.forms[0].estado.value="0";
 
 

}
 
 

function descheckeabono(){

if(document.forms[0].TODOS_BONO.checked==true){
document.forms[0].TODOS_BONO.checked=false;
}

} 

function obtenerchecked(){

var temporal=document.forms[0].estado.value;


var c=0;var e=0;var rut='';
 for(var i=0;i<document.forms[0].elements.length;i++){
 if(document.forms[0].elements[i].type=='checkbox'){
 e++;
  if(document.forms[0].elements[i].checked==true){
  c++;
  rut+=document.forms[0].elements[i].value;
  }
  }
  }
  
 if(e==1){
alert('No hay empresas para realizar la operación.');
return false;
 }  
 
 if(c==0){
alert('Por favor, seleccione al menos una empresa.')
return false;
 }
 else if(temporal==1&&c>1){
alert('Seleccione solo una empresa, solo se puede subir un archivo.')
return false;
 
 }
 
 
 var c=0;
 for(var i=0;i<document.forms[0].elements.length;i++){
 if(document.forms[0].elements[i].type=='radio'){
  if(document.forms[0].elements[i].checked==true){
  c++;
  }
  }
  }
 
 if(c==0){
alert('Por favor, seleccione si acepta o no la propuesta.')
return false;
 }
  
 var valor="";
 for(var i=0;i<document.forms[0].elements.length;i++){
 if(document.forms[0].elements[i].type=='checkbox'){
  if(document.forms[0].elements[i].checked==true){
  if(document.forms[0].elements[i].value!='on'){
  valor+=document.forms[0].elements[i].value + ';';
  }
  }
  }
 }
 
document.forms[0].rutt.value=valor;
document.forms[0].ruts.value=rut;

return true;
  
}

function checkedall(){

 if(document.forms[0].TODOS_BONO.checked==true){
 
 for(var i=0;i<document.forms[0].elements.length;i++){
 if(document.forms[0].elements[i].type=='checkbox'&&document.forms[0].elements[i].id!='TODOS_BONO'){
      document.forms[0].elements[i].checked=true;
                       }
                 
          }
 
 }
 else{
 for(var i=0;i<document.forms[0].elements.length;i++){
 if(document.forms[0].elements[i].type=='checkbox'&&document.forms[0].elements[i].id!='TODOS_BONO'){
      document.forms[0].elements[i].checked=false;
                       }
                 
          }
      }                     
}

function obtenercheckeddescarga(){

var c=0;var e=0;
 for(var i=0;i<document.forms[0].elements.length;i++){
 if(document.forms[0].elements[i].type=='checkbox'){
 e++;
  if(document.forms[0].elements[i].checked==true){
  c++;
  }
  }
  }
 if(e==1){
alert('No hay empresas para realizar la operación.');
return false;
 }
 
   
 if(c==0){
alert('Por favor, debe seleccionar al menos una empresa.')
return false;
 }
 
 
  
  
 var valor="";
 for(var i=0;i<document.forms[0].elements.length;i++){
 if(document.forms[0].elements[i].type=='checkbox'){
  if(document.forms[0].elements[i].checked==true){
  if(document.forms[0].elements[i].value!='on'){
  valor+=document.forms[0].elements[i].value + ';';
  }
  }
  }
 }
 
 
document.forms[0].rutt.value=valor;
 
return true;
  
}


function marcarItem(item, value) {
	item.checked = value;
	item.disabled = false;
	item.value = "ALL";
}

function checkInput() {
	for (j = 0; j < document.forms[0].elements.length; j++) {
		if (document.forms[0].elements[j].name.indexOf('propuestas') == 0 && document.forms[0].elements[j].checked) {
			document.forms[0].action = document.forms[0].action + "?step=descargaPropuestaZip";
			return true;
		}
	}
	alert("Por favor, seleccione los bonos requeridos por usted.");
	return false;
}

function subirArchivo() {
	alert("Estamos trabajando para usted.");
}

function checkTerminosPropuestaForm(checkbox) {
	var f = document.forms[0];
	if(checkbox['checked']) {
		f['btnSubmit'].disabled = "";
	} else {
		if(!validarCheckEmpresas(f)) {
			f['btnSubmit'].disabled = "disabled";
		}
	}
}

function validarCheckEmpresas(form) {
	var result = false;
	for (j = 0; j < document.forms[0].elements.length; j++) {
		if (document.forms[0].elements[j].name.indexOf('empresa_') == 0) {
			if(document.forms[0].elements[j]['checked']) {
				result = true;
			}
		}
	}
	return result;
}

function submitTerminosPropuestaForm() {
	var f = document.forms[0];
	if(validarCheckEmpresas(f)) {
		f['action'] = f['action'] + "?step=processValicacionTerminos";
		return true;
	}
	return false;
}

function goToUrl(url) {
	window.location = url;
}


 function rut_validkey(e){
    var key;

    if(window.event) e = window.event // Windows Support
    key = e.keyCode ? e.keyCode : e.which; // Firefox & Chromium support

    // control keys
    if(key==8 || key==9 || key==13 || key==27  ||(key > 36 && key < 41))
        return true;
    
    if(key==45)    
		return true;

    if(key == 107 || key == 75 || (key > 47 && key < 58))
        return true;
    return false;
}
 

function trim(cadena)
{
 
  

	for(i=0; i<cadena.length; )
	{
		if(cadena.charAt(i)==" ")
			cadena=cadena.substring(i+1, cadena.length);
		else
			break;
	}

	for(i=cadena.length-1; i>=0; i=cadena.length-1)
	{
		if(cadena.charAt(i)==" ")
			cadena=cadena.substring(0,i);
		else
			break;
	}
	
	 
	return cadena;
}



function onSubmitConsulta1() {

 var objeto=document.forms[0].empresa;
 
 if(trim(objeto.value)==''){
 alert("Por favor ingrese una empresa");
 return false;
 }
 
  
 
 return Valida_Rut(objeto);
 
	var f = document.forms[0];
	var result = true;
	//if(strTrim(f['oficina'].value) == "") {
	//	result = false;
  //  }
	if(strTrim(f['empresa'].value) == "") {
		result = false;
	}
 //	if(strTrim(f['sucursal'].value) == "") {
	//	result = false;
//	}
	
	if(result) {
		f['action'] = f['action'] + "?step=consultarEstadoAfiliados";
		return result;
	} else {
		alert("Debe Ingresar la empresa.");
		return result;
	}
	
	 
	
 
}

function onSubmitConsulta2() {

var objeto=document.forms[0].empresa;

 
 
 if(trim(objeto.value)==''){
 alert("Por favor ingrese una empresa");
 return false;
 }
 
 return Valida_Rut(objeto);
 
 
	var f = document.forms[0];
	var result = true;
	if(strTrim(f['empresa'].value) == "") {
		result = false;
	}
	if(result) {
		f['action'] = f['action'] + "?step=consultarPropuestaWeb";
		return result;
	} else {
		alert("Debe completar todos los campos requeridos.");
		return result;
	}
	 
}

function strTrim(value) {
	return value.replace(/^\s+|\s+$/g,"");
}

function isAlphaNumRut(e) {
    var tecla = (document.all) ? e.keyCode : e.which;
    if (tecla >= 48 && tecla <= 57) {
        return true;
    }
    if (tecla == 45 || tecla == 75 || tecla == 107) {
        return true;
    }
    return false;
}


function Valida_Rut(Objeto) {

	var tmpstr = "";
	var intlargo = Objeto.value
	if (intlargo.length> 0) {
		crut = Objeto.value
		largo = crut.length;
		c=0;
		for(i=0;i<largo;i++)
		{
		if(crut.charAt(i)=='-' || crut.charAt(i)==' ')
		c++;
		}
		if(c!=1)
		{
			alert('rut inválido')
			Objeto.focus()
			return false;
	
		}
		if ( largo <2 ) {
			alert('rut inválido')
			Objeto.focus()
			return false;
		}
		
		for ( i=0; i <crut.length ; i++ )
			if ( crut.charAt(i) != ' ' && crut.charAt(i) != '.' && crut.charAt(i) != '-' ){
				tmpstr = tmpstr + crut.charAt(i);
			}
			
		rut = tmpstr;
		crut=tmpstr;
		largo = crut.length;
	
		if ( largo> 2 )
			rut = crut.substring(0, largo - 1);
		else
			rut = crut.charAt(0);
	
		dv = crut.charAt(largo-1);
	
		if ( rut == null || dv == null )
		return 0;
	
		var dvr = '0';
		suma = 0;
		mul  = 2;
	
		for (i= rut.length-1 ; i>= 0; i--) {
			suma = suma + rut.charAt(i) * mul;
			if (mul == 7)
				mul = 2;
			else
				mul++;
		}
	
		res = suma % 11;
		if (res==1)
			dvr = 'k';
		else if (res==0)
			dvr = '0';
		else {
			dvi = 11-res;
			dvr = dvi + "";
		}
	
		if ( dvr != dv.toLowerCase() ) {
			alert('El Rut Ingresado es Invalido')
			Objeto.focus()
			return false;
		}
		//alert('El Rut Ingresado es Correcto!')
		Objeto.focus()
		return true;
	}
}

 

  
 function botonover(){


var boton=document.getElementById('button');
boton.className='botonOver';	
}

function empresaact(){

var valor=document.getElementById('empresa');
valor.checked=true;
document.forms[0].tipo.value='empresa';

 
}

function botondown(){


var boton=document.getElementById('button');
boton.className='boton';	
}


function empresas(){
var valor=document.getElementById('empresa').value;

document.forms[0].tipo.value=valor;

}

function holdings(){


var valor=document.getElementById('holding').value;
document.forms[0].tipo.value=valor;	
}



function validar(){


var file=document.getElementById('archivo').value;


if(file==''){
alert('Por favor, debe seleccionar un archivo.');
return false;

var valor=document.forms[0].tipo.value;
if(valor!='empresa'&&valor!='holding')
{
alert('Por favor, Seleccione empresa o holding');
}

}  
 
var nombre=file.substring(file.lastIndexOf('\\')+1,file.length);



var rut=nombre.split('.'); 
var numero=rut[0];
 
var ext=rut[1];

var uext=ext.toUpperCase();
var txt='txt'.toUpperCase();
var csv='csv'.toUpperCase();
var xls='xls'.toUpperCase();
var zip='zip'.toUpperCase();	
 

 
if(uext!=txt&&uext!=csv&&uext!=xls&&uext!=zip)
{
alert('Extensión de archivo no válida. Sólo se permite extensiones txt, csv, xls o zip.');
return false;
}

if(uext!=zip){

var c=0;
for(var i=0;i<numero.length;i++)
{
	 if(numero.charAt(i)=='-')
	 {
		 c++;
	 }
}

var num=0;
var vector=new Array(1,2,3,4,5,6,7,8,9,0);
for(var k=0;k<numero.length;k++){

for(var l=0;l<vector.length;l++)
{
	if(numero.charAt(k)==vector[l])
	{
		num++;
	}
}


}
 

if(num!=numero.length)
{
alert('Nombre de archivo no válido. Debe corresponder al Rut de la Empresa sin dígito verificador.');
return false;
}


if(c!=0){
alert('Nombre de archivo no válido. Debe corresponder al Rut de la Empresa sin dígito verificador.');
return false;
}

if(numero>99999999){
alert('Nombre de archivo no válido. Debe corresponder al Rut de la Empresa sin dígito verificador.');
return false;
}
}

 
return true;



}


function validarHolding(){


var file=document.getElementById('archivo').value;
 
if(file==''){
alert('Por favor ingrese un archivo.');
return false;
}  

 
 
var nombre=file.substring(file.lastIndexOf('\\')+1,file.length);




var rut=nombre.split('.'); 
var numero=rut[0];
 
var ext=rut[1];

 var uext=ext.toUpperCase();
var txt='txt'.toUpperCase();
var csv='csv'.toUpperCase();
var xls='xls'.toUpperCase();

 
 

 
if(uext!=txt&&uext!=csv&&uext!=xls)
{
alert('Extensión de archivo no válida. Sólo se permite extensiones txt, csv y xls.');
return false;
}
 

 

var c=0;
for(var i=0;i<numero.length;i++)
{
	 if(numero.charAt(i)=='-')
	 {
		 c++;
	 }
 }

var num=0;
var vector=new Array(1,2,3,4,5,6,7,8,9,0);
for(var k=0;k<numero.length;k++){

for(var l=0;l<vector.length;l++)
{
	if(numero.charAt(k)==vector[l])
	{
		num++;
	}
}


}
 

if(num!=numero.length)
{
alert('Nombre de archivo no válido. Debe corresponder al Rut de la Empresa sin dígito verificador.');
return false;
}


if(c!=0){
alert('Nombre de archivo no válido. Debe corresponder al Rut de la Empresa sin dígito verificador.');
return false;
}

if(numero>99999999){
alert('Nombre de archivo no válido. Debe corresponder al Rut de la Empresa sin dígito verificador.');
return false;
}


 
return true;



}

function maxchar(){

var valor=document.getElementById('cuerpo').value;
var c=valor.length;
var oculto=document.getElementById('oculto').value=c;
 
if(oculto>=400)
{alert('Maximo permitido: 400 caracteres');
  return false;
}
}
 
function validaform(){
var para=document.getElementById('para').value;
var asunto=document.getElementById('asunto').value;
var cuerpo=document.getElementById('cuerpo').value; 

var j=0;
for(var i=0;i<para.length;i++){

if(para.charAt(i)==' ')
{
j++;
}
}
 
if(j==para.length)
{
para='';
}

var j=0;
for(var i=0;i<asunto.length;i++){

if(asunto.charAt(i)==' ')
{
j++;
}
}

if(j==asunto.length)
{
asunto='';
}

var j=0;
for(var i=0;i<cuerpo.length;i++){

if(cuerpo.charAt(i)==' ')
{
j++;
}
}

if(j==cuerpo.length)
{
cuerpo='';
}



if(para.length==0){
alert('Ingrese una dirección de correo');
return false;
}
if(asunto.length==0){
alert('Ingrese un asunto de correo');
return false;
}
if(cuerpo.length==0){
alert('El cuerpo del mensaje esta vacio');
return false;
}
} 

 




  