function validaForm() {
var sRut = document.form1.rut.value;
var aRut;
	if(sRut==""){
		alert("Debe ingresar el rut de la empresa");
		return false;
	}
	aRut = sRut.split("-");
	if (aRut.length!=2) {
	   alert("Rut mal ingresado");
	   return false;
	}
	
	document.form1.rutEmpresa.value = "";
	
	document.form1.hRutNum.value = aRut[0];
	document.form1.hRutDig.value = aRut[1];
	if (ChequearRutDigito(document.form1.hRutNum, document.form1.hRutDig)) {
	   document.form1.rutEmpresa.value = aRut[0];
	} else {
	   alert("Rut mal ingresado");
	   return false;
	}
	return true;
}

function doServicios() {
var f1 = document.form1;
   f1.accion.value = "servicios";
   f1.action = "Servicios.do"
   f1.submit();
}

function doEncargados() {
var f1 = document.form1;
   f1.accion.value = "encargados";
   f1.action = "adminEncargado.do"
   f1.submit();
}

function doEmpresa() {
var f1 = document.form1;
   f1.accion.value = "encargados";
   f1.action = "adminEncargado.do"
   f1.submit();
   return true;
}

function doEliminar(rut, nom) {
  if (confirm("Desaea eliminar el encargo " + nom )) {
    alert('eliminando');
  } else {
    alert('Eliminación cancelada')
  }
}

function doModificar(rut) {
var f1 = document.form1;
   f1.accion.value = "modificar";
   f1.rutEncargado.value = rut;
   f1.action = "adminEncargado.do"
   f1.submit();
   return true;
}

function doGrabar(rut) {
var f1 = document.form1;
   f1.accion.value = "grabar";
   f1.action = "adminEncargado.do"
   f1.submit();
   return true;
}
function doVuelveLista() {
var f1 = document.form1;
   f1.accion.value = "volverLista";
   f1.action = "adminEncargado.do"
   f1.submit();
   return true;
}


