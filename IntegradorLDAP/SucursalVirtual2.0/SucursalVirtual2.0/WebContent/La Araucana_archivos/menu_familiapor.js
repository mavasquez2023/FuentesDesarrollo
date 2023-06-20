var anMenu = 130;
var totalMen = 6;
var imaflecha = 'url(flechitaazul.gif)';
function tunMen(tex,enl,dest,subOp,an){
this.tex = tex;
this.enl = enl;
this.dest = dest;
this.subOp = subOp;
this.an = an;
}
Op_0 = new tunMen('Créditos Personales',null,null,4,150);
	Op_0_0 = new tunMen('Requisitos','index.php?portal=1&seccion=1',null,0,200);
	Op_0_1 = new tunMen('Tasas de Interés',"index.php?portal=1&seccion=2",null,0,200);
	Op_0_2 = new tunMen('Líneas de Crédito',null,null,3,150);
		Op_0_2_0 = new tunMen('Microempresarios',"index.php?portal=1&seccion=3",null,0,120);
		Op_0_2_1 = new tunMen('Especial La Araucana',"index.php?portal=1&seccion=4",null,0,120);
		Op_0_2_2 = new tunMen('Consolide su Deuda',"index.php?portal=1&seccion=5",null,0,120);
	Op_0_3 = new tunMen('Simulador de Crédito','index.php?portal=1&seccion=30',null,0,200);	
//Op_1 = new tunMen('Asignaciones en Dinero',null,null,1,150);
	//Op_1_1 = new tunMen('Natalidad',"index.php?portal=1&seccion=6",null,0,200)
	//Op_1_2 = new tunMen('Reembolso Gasto de Matrícula',"index.php?portal=1&seccion=7",null,0,200)
	//Op_1_3 = new tunMen('Fallecimiento',"index.php?portal=1&seccion=8",null,0,200)
Op_1 = new tunMen('Beneficios Sociales',null,null,7,150)
	Op_1_0 = new tunMen('Nuevos Valores 2007','index.php?portal=1&seccion=35',null,0,200)
	Op_1_1 = new tunMen('Estímulo Enseñanza Básica',"index.php?portal=1&seccion=36",null,0,200);//9 Vacaciones Escolares
	Op_1_2 = new tunMen('Estímulo Enseñanza Media',"index.php?portal=1&seccion=37",null,0,200)//10 Estimulo Escolar
	//Op_2_2 = new tunMen('Estímulo Fin de Año',"index.php?portal=1&seccion=11",null,0,200)
	Op_1_3 = new tunMen('Nupcialidad',"index.php?portal=1&seccion=12",null,0,200)
	Op_1_4 = new tunMen('Aniversario',"index.php?portal=1&seccion=13",null,0,200)
	Op_1_5 = new tunMen('Microempresarios',"index.php?portal=1&seccion=14",null,0,200)
	Op_1_6 = new tunMen('Beneficio Habitacional',"index.php?portal=1&seccion=38",null,0,200)
Op_2 = new tunMen('Regímenes Legales',null,null,3,150)
	Op_2_0 = new tunMen('Asignación Familiar',"index.php?portal=1&seccion=15",null,0,200)
	Op_2_1 = new tunMen('Subsidio de Cesantía',"index.php?portal=1&seccion=16",null,0,200)
	Op_2_2 = new tunMen('Subsidio Incapacidad Laboral',"index.php?portal=1&seccion=17",null,0,200)
Op_3 = new tunMen('Ahorro y Vivienda',null,null,2,150)
	Op_3_0 = new tunMen('Ahorro',"index.php?portal=1&seccion=18",null,0,200)
	Op_3_1 = new tunMen('Vivienda',"index.php?portal=1&seccion=19",null,0,200)
Op_4 = new tunMen('Servicios Previsionales',null,null,2,150)
	Op_4_0 = new tunMen('Orientación de Pensiones',"index.php?portal=1&seccion=20",null,0,200)
	Op_4_1 = new tunMen('Ahorro Voluntario',"index.php?portal=1&seccion=21",null,0,200)
Op_5 = new tunMen('Convenios',"ver_convenio.php",null,0,150)

var tunIex=navigator.appName=="Microsoft Internet Explorer"?true:false;
if(tunIex && navigator.userAgent.indexOf('Opera')>=0){tunIex = false}
var manita = tunIex ? 'hand' : 'pointer'
var subOps = new Array()
function construye(){
cajaMenu = document.createElement('ul')
cajaMenu.style.width = anMenu + "px"
cajaMenu.style.zIndex = 0
cajaMenu.style.marginLeft = 7
cajaMenu.style.paddingLeft = 0
document.getElementById('me').appendChild(cajaMenu)
for(m=0; m < totalMen; m++){
	opchon = eval('Op_'+m)
	boton = document.createElement('li')
	boton.style.marginLeft = 0
	boton.style.paddingLef = 0
	boton.style.listStyleType = 'none'
	boton.style.position = 'relative'
	boton.style.zIndex = (1000 - m)
	boton.className = 'botones'//PRIMER NIVEL DE MENUS
	boton.style.cursor = manita
	boton.id = 'op_' + m
	if(opchon.enl){
		lin = document.createElement('a')
		lin.className = 'enlacesmenu2'//ESTE ES PARA LOS ENLACES DE AFUERA
		lin.href = opchon.enl
		if(opchon.dest){
			lin.target = opchon.dest
		}
	boton.appendChild(lin)
	lin.appendChild(document.createTextNode(opchon.tex))
	}
	else{
		boton.appendChild(document.createTextNode(opchon.tex))
	}
	boton.onmouseover = function(){
		mostrar(this)
		}
	boton.onmouseout=function(){
		ocultar(this)
		}
	cajaMenu.appendChild(boton)
	if(opchon.subOp > 0 ){
		espan = document.createElement('span')
		espan.style.position = 'absolute'
		espan.style.right = 0
		espan.style.top = 2
		boton.style.backgroundImage = imaflecha
		boton.style.backgroundPosition = 'center right'
		boton.style.backgroundRepeat = 'no-repeat'
		espan.className = 'espan'
		boton.appendChild(espan)
		subOps[subOps.length] = boton.id.replace(/o/,"O")
		}
	}
if(subOps.length >0){subMes()}
}
function subMes(){
lar = subOps.length
for(t=0;t<subOps.length;t++){
	opc =eval(subOps[t])
	for(v=0;v<opc.subOp;v++){
		if(eval(subOps[t] + "_" + v + ".subOp") >0){
			subOps[subOps.length] = subOps[t] + "_" + v
			}
		}
	}
construyeSub()
}
function construyeSub(){
for(y=0; y<subOps.length;y++){
opchon = eval(subOps[y])
capa = document.createElement('ul')
capa.className = 'subMe'
capa.style.width = opchon.an + "px"
capa.style.visibility = 'hidden'
capa.style.position = 'absolute'
capa.style.marginLeft = 0
capa.style.paddingLeft = 0
document.getElementById(subOps[y].toLowerCase()).firstChild.nextSibling.appendChild(capa)
	for(s=0;s < opchon.subOp; s++){
		sopchon = eval(subOps[y] + "_" + s)
		opc = document.createElement('li')
		opc.style.marginLeft = 0
		opc.style.paddingLeft = 0
		opc.style.listStyleType = 'none'
		opc.className = 'botonessub'
		opc.id = subOps[y].toLowerCase() + "_" + s
		if(sopchon.enl){
			lin = document.createElement('a')
			lin.className = 'enlacesmenu'
			lin.href = sopchon.enl
			if(sopchon.dest){
				lin.target = sopchon.dest
			}
		opc.appendChild(lin)
		lin.appendChild(document.createTextNode(sopchon.tex))
		}
		else{
			opc.appendChild(document.createTextNode(sopchon.tex))
		}
		capa.appendChild(opc)
		opc.style.cursor = manita
		opc.onmouseover = function(){
			mostrar(this)
			}
		opc.onmouseout=function(){
			ocultar(this)
			}
		if(sopchon.subOp > 0 ){
			espan = document.createElement('span')
			espan.style.position = 'absolute'
			espan.style.right = 0
			opc.style.backgroundImage = imaflecha
			opc.style.backgroundPosition = 'center right'
			opc.style.backgroundRepeat = 'no-repeat'
			espan.className = 'espan'
			opc.appendChild(espan)
			}
		}
	}
}
function mostrar(cual){
if(document.styleSheets.length > 0 && !document.styleSheets[0].disabled){
	if(tunIex){cual.className = 'botonesHover'}
	if(cual.childNodes.length > 1){
		cual.firstChild.nextSibling.firstChild.style.visibility = 'visible'
		}
	}
}
function ocultar(cual){
	if(document.styleSheets.length > 0 && !document.styleSheets[0].disabled){
	if(tunIex){cual.className = 'botones'}
		if(cual.childNodes.length > 1){
		cual.firstChild.nextSibling.firstChild.style.visibility = 'hidden'
		}
	}
}
function enlace(cual){
enla = eval('O' + cual.substr(1)).enl
targ = eval('O' + cual.substr(1)).dest
if(targ && targ.toLowerCase() == '_blank'){
	window.open(enla,'','')
	}
else{
	location.href = enla
	}

}
construye()