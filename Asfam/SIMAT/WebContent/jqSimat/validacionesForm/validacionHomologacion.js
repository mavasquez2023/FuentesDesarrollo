//funciones que validan formularios de jsp tabla_plano11_homologaction


function maximaLongitud(texto,maxlong){

	var tecla, int_value, out_value;
	
	if (texto.value.length > maxlong){
		/*con estas 3 sentencias se consigue que el texto se reduzca
		al tamaño maximo permitido, sustituyendo lo que se haya
		introducido, por los primeros caracteres hasta dicho limite*/
		in_value = texto.value;
		out_value = in_value.substring(0,maxlong);
		texto.value = out_value;
		alert("La longitud máxima es de " + maxlong + " caractéres");
		return false;
	}
 return true;
}




        

