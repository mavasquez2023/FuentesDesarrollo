/**
*
* Validador de Rut
* Descargado desde http://www.juque.cl/
* Modificado por 
* IBM Software Factory
*
**/
function revisarDigito( dvr ){	
	var dv = dvr + ""	
	if ( dv != '0' 
		&& dv != '1' 
		&& dv != '2' 
		&& dv != '3' 
		&& dv != '4' 
		&& dv != '5' 
		&& dv != '6' 
		&& dv != '7' 
		&& dv != '8' 
		&& dv != '9' 
		&& dv != 'k'  
		&& dv != 'K')	
	{		
		//alert("Debe ingresar un digito verificador valido");
		return false;	
	}
	//alert("rd true: "+dv);	
	return true;
}//end revisarDigito

function revisarDigito2( crut ){	
	//alert("r2: "+crut);	
	var largo = crut.length;
	//alert("r2 largo: "+largo);
	if ( largo < 2 ) {		
		//alert("Debe ingresar el rut completo");
		return false;	
	}
		
	if ( largo > 2 ){
		//alert("r2: if >2");			
		var rut = crut.substring(0, largo - 1);	
		var dv=crut.charAt(largo-1);
	}/*else{		
		rut = crut.charAt(0);	
		dv = crut.charAt(largo-1);	
		revisarDigito( dv );	
	}*/
	//alert("r2 dv: "+dv);
	//alert("r2 rut:"+rut);
	if ( rut == null || dv == null ){
		//alert("r2: return false");	
		return false;	
	}
	var dvr = '0';
	var suma = 0;	
	var mul  = 2;	
	var i=0;
	for (i= rut.length -1 ; i >= 0; i--){	
		suma = suma + rut.charAt(i) * mul;
			
		if (mul == 7){			
			mul = 2;		
		}else{    			
			mul++;	
		}
	}
	
	res = suma % 11	
	if (res==1){		
		dvr = 'k';	
	}else if (res==0){		
		dvr = '0';	
	}else{		
		dvi = 11-res;		
		dvr = dvi + "";	
	}
	//alert("r2 dvr: "+dvr);	
	if ( dvr != dv.toLowerCase() ){		
		//alert("EL rut es incorrecto");	
		return false	
	}
	return true
}//end 

function RutMod11(rutEntrada) {	
	var texto=rutEntrada;
	//alert("llega: "+texto);	
	var tmpstr = "";	
	var i=0;
	for ( i=0; i < texto.length ; i++ ){		
		if ( texto.charAt(i) != ' ' && texto.charAt(i) != '.' && texto.charAt(i) != '-' ){
			tmpstr = tmpstr + texto.charAt(i);
		}
	}
	texto = tmpstr;	
	var largo = texto.length;	
	//alert("texto: "+texto)	;
	if ( largo < 2 ) {		
		//alert("Debe ingresar el rut completo");	
		return false;	
	}	
	//validacion sobre caracteres permitidos formato rut.
	i=0;
	for (i=0; i < largo ; i++ )	{			
		if ( texto.charAt(i) !="0" 
			&& texto.charAt(i) != "1" 
			&& texto.charAt(i) !="2" 
			&& texto.charAt(i) != "3" 
			&& texto.charAt(i) != "4" 
			&& texto.charAt(i) !="5" 
			&& texto.charAt(i) != "6" 
			&& texto.charAt(i) != "7" 
			&& texto.charAt(i) !="8" 
			&& texto.charAt(i) != "9" 
			&& texto.charAt(i) !="k" 
			&& texto.charAt(i) != "K" )
 		{			
			//alert("El valor ingresado no corresponde a un R.U.T valido");	
			return false;		
		}	
	}	
	//
	var invertido = "";	
	i=0;
	for ( i=(largo-1),j=0; i>=0; i--,j++ ){		
		invertido = invertido + texto.charAt(i);	
	}
	var dtexto = "";	
	dtexto = dtexto + invertido.charAt(0);	
	dtexto = dtexto + '-';	
	var cnt = 0;	
	i=0;
	for ( i=1,j=2; i<largo; i++,j++ ){		
		if ( cnt == 3 ){			
			j++;			
			dtexto = dtexto + invertido.charAt(i);			
			cnt = 1;		
		}else{				
			dtexto = dtexto + invertido.charAt(i);			
			cnt++;
		}	
	}	

	var invertido = "";
	i=0;	
	for ( i=(dtexto.length-1),j=0; i>=0; i--,j++ ){		
		invertido = invertido + dtexto.charAt(i);	
	}	

	if ( revisarDigito2(texto) ){
		//alert("return true");
		return true;
	}	
	//alert("return false");
	return false;
}//end Rut()
