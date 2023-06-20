var anMenuemp = 130
var totalMenemp = 4
var imaflecha = 'url(flechitaazul.gif)'
function tunMenemp(texemp,enlemp,destemp,subOpemp,anemp){
this.texemp = texemp;
this.enlemp = enlemp;
this.destemp = destemp;
this.subOpemp = subOpemp;
this.anemp = anemp;
}
Opemp_0 = new tunMenemp('Afiliación','index.php?portal=2&seccion=3',null,0,200)
Opemp_1 = new tunMenemp('Regímenes Legales',null,null,4,200)
	Opemp_1_0 = new tunMenemp('Asignación Familiar','index.php?portal=2&seccion=4',null,0,280)
	Opemp_1_1 = new tunMenemp('Subsidio de Cesantía','index.php?portal=2&seccion=5',null,0,280)
	Opemp_1_2 = new tunMenemp('Subsidio Incapacidad Laboral','index.php?portal=2&seccion=6',null,0,280)
	Opemp_1_3 = new tunMenemp('Descarga de Archivos (PDF)','index.php?portal=2&seccion=19',null,0,280)
/*	Opemp_1_3 = new tunMenemp('Declaración Jurada','../upload/declaracion_jurada2006.pdf','_blank',0,280)
	Opemp_1_4 = new tunMenemp('Jefe de Personal','../upload/jefe_personal.pdf','_blank',0,280)*/
Opemp_2 = new tunMenemp('Cotización Electrónica',null,null,3,200)
	Opemp_2_0 = new tunMenemp('Características del Servicio','index.php?portal=2&seccion=9',null,0,280)
	Opemp_2_1 = new tunMenemp('Requerimientos Técnicos','index.php?portal=2&seccion=15',null,0,280)
	Opemp_2_2 = new tunMenemp('Asistencia del Sistema','index.php?portal=2&seccion=16',null,0,280)
Opemp_3 = new tunMenemp('Agencia Móvil','index.php?portal=2&seccion=10',null,0,200)
/*Opemp_4 = new tunMenemp('Cargas Familiares',null,null,4,200)
	Opemp_4_0 = new tunMenemp('Tramo 1 valor $ 4126','index.php?portal=2&seccion=11',null,0,280)
	Opemp_4_1 = new tunMenemp('Tramo 2 valor $ 4014','index.php?portal=2&seccion=12',null,0,280)
	Opemp_4_2 = new tunMenemp('Tramo 3 valor $ 1307','index.php?portal=2&seccion=13',null,0,280)
	Opemp_4_3 = new tunMenemp('Tramo 4 valor $ 0','index.php?portal=2&seccion=14',null,0,280)*/
/*Opemp_4 = new tunMenemp('Bono Extraordinario Ley N° 20.111','index.php?portal=2&seccion=19',null,0,200)*/

var tunIexemp=navigator.appName=="Microsoft Internet Explorer"?true:false;
if(tunIexemp && navigator.userAgent.indexOf('Opera')>=0){tunIexemp = false}
var manitaemp = tunIexemp ? 'hand' : 'pointer'
var subOpsemp = new Array()
function construyepor(){
cajaMenuemp = document.createElement('ul')
cajaMenuemp.style.width = anMenuemp + "px"
cajaMenuemp.style.zIndex = 0
cajaMenuemp.style.marginLeft = 7
cajaMenuemp.style.paddingLeft = 0
document.getElementById('mi').appendChild(cajaMenuemp)
for(mm=0; mm < totalMenemp; mm++){
	opchonemp = eval('Opemp_'+mm)
	botonemp = document.createElement('li')
	botonemp.style.marginLeft = 0
	botonemp.style.paddingLef = 5
	botonemp.style.listStyleType = 'none'
	botonemp.style.position = 'relative'
	botonemp.style.zIndex = (100 - mm)
	botonemp.className = 'botones'
	botonemp.style.cursor = manitaemp
	botonemp.id = 'Opemp_' + mm
	if(opchonemp.enlemp){
		linemp = document.createElement('a')
		linemp.className = 'enlacesmenu2'
		linemp.href = opchonemp.enlemp
		if(opchonemp.destemp){
			linemp.target = opchonemp.destemp
		}
	botonemp.appendChild(linemp)
	linemp.appendChild(document.createTextNode(opchonemp.texemp))
	}
	else{
		botonemp.appendChild(document.createTextNode(opchonemp.texemp))
	}
	botonemp.onmouseover = function(){
		mostraremp(this)
		}
	botonemp.onmouseout=function(){
		ocultaremp(this)
		}
	cajaMenuemp.appendChild(botonemp)
	if(opchonemp.subOpemp > 0 ){
		espanemp = document.createElement('span')
		espanemp.style.position = 'absolute'
		espanemp.style.right = 0
		espanemp.style.top = 2
		botonemp.style.backgroundImage = imaflecha
		botonemp.style.backgroundPosition = 'center right'
		botonemp.style.backgroundRepeat = 'no-repeat'
		espanemp.className = 'espanemp'
		botonemp.appendChild(espanemp)
		subOpsemp[subOpsemp.length] = botonemp.id.replace(/o/,"O")
		}
	}
if(subOpsemp.length >0){subMesemp()}
}
function subMesemp(){
lar = subOpsemp.length
for(t=0;t<subOpsemp.length;t++){
	opcemp =eval(subOpsemp[t])
	for(v=0;v<opcemp.subOpemp;v++){
		if(eval(subOpsemp[t] + "_" + v + ".subOpemp") >0){
			subOpsemp[subOpsemp.length] = subOpsemp[t] + "_" + v
			}
		}
	}
construyeSubemp()
}
function construyeSubemp(){
for(y=0; y<subOpsemp.length;y++){
opchonemp = eval(subOpsemp[y])
capaemp = document.createElement('ul')
capaemp.className = 'subMe'
capaemp.style.width = opchonemp.anemp + "px"
capaemp.style.visibility = 'hidden'
capaemp.style.position = 'absolute'
capaemp.style.marginLeft = 0
capaemp.style.paddingLeft = 0
document.getElementById(subOpsemp[y].toLowerCase()).firstChild.nextSibling.appendChild(capaemp)
	for(s=0;s < opchonemp.subOpemp; s++){
		sopchonemp = eval(subOpsemp[y] + "_" + s)
		opcemp = document.createElement('li')
		opcemp.style.marginLeft = 0
		opcemp.style.paddingLeft = 0
		opcemp.style.listStyleType = 'none'
		opcemp.className = 'botones'
		opcemp.id = subOpsemp[y].toLowerCase() + "_" + s
		if(sopchonemp.enlemp){
			linemp = document.createElement('a')
			linemp.className = 'enlacesmenu'
			linemp.href = sopchonemp.enlemp
			if(sopchonemp.destemp){
				linemp.target = sopchonemp.destemp
			}
		opcemp.appendChild(linemp)
		linemp.appendChild(document.createTextNode(sopchonemp.texemp))
		}
		else{
			opcemp.appendChild(document.createTextNode(sopchonemp.texemp))
		}
		capaemp.appendChild(opcemp)
		opcemp.style.cursor = manitaemp
		opcemp.onmouseover = function(){
			mostraremp(this)
			}
		opcemp.onmouseout=function(){
			ocultaremp(this)
			}
		if(sopchonemp.subOpemp > 0 ){
			espanemp = document.createElement('span')
			espanemp.style.position = 'absolute'
			espanemp.style.right = 0
			opcemp.style.backgroundImage = imaflecha
			opcemp.style.backgroundPosition = 'center right'
			opcemp.style.backgroundRepeat = 'no-repeat'
			espanemp.className = 'espanemp'
			opcemp.appendChild(espanemp)
			}
		}
	}
}
function mostraremp(cualemp){
if(document.styleSheets.length > 0 && !document.styleSheets[0].disabled){
	if(tunIexemp){cualemp.className = 'botonesHover'}
	if(cualemp.childNodes.length > 1){
		cualemp.firstChild.nextSibling.firstChild.style.visibility = 'visible'
		}
	}
}
function ocultaremp(cualemp){
	if(document.styleSheets.length > 0 && !document.styleSheets[0].disabled){
	if(tunIexemp){cualemp.className = 'botones'}
		if(cualemp.childNodes.length > 1){
		cualemp.firstChild.nextSibling.firstChild.style.visibility = 'hidden'
		}
	}
}
function enlaceemp(cualemp){
enlaemp = eval('O' + cualemp.substr(1)).enlemp
targemp = eval('O' + cualemp.substr(1)).destemp
if(targemp && targemp.toLowerCase() == '_blank'){
	window.open(enlaemp,'','')
	}
else{
	location.href = enlaemp
	}

}
construyepor()