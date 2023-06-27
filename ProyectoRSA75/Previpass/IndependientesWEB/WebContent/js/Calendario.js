function ShowCalendarFor(campoTxtIO)
{
	var L = 190;            // Largo de ventana Modal
  	var A = 162;            // Ancho de Ventana Modal
 	var X = event.screenX;
  	var Y = event.screenY;
  	tam = "dialogTop:"+ (Y) +"px;dialogLeft:"+ (X)  +"px;dialogHeight:"+ L +"px;dialogWidth:"+ A +"px;directories:no;menubar:no;status:no;help:no";      
  	elvalor = showModalDialog("/IndependientesWEB/pages/General/Calendario.htm", 'xx', tam);
  	fechaAux = campoTxtIO.value;
  	if (elvalor == "") elvalor = fechaAux;
  	campoTxtIO.value = elvalor;
}

function Comparar_Fecha(Obj1,Obj2) 
{
	//Retorna True si Obj1 es mayor o igual que Obj2
 	String1 = Obj1;
 	String2 = Obj2;
 	
  	dia1=String1.substring(0,2);
  	mes1=String1.substring(3,5);
  	anyo1=String1.substring(6,10);
  	dia2=String2.substring(0,2);
  	mes2=String2.substring(3,5);
  	anyo2=String2.substring(6,10);
  	
  	dia1=parseInt(dia1,10);    
  	dia2=parseInt(dia2,10);
  	mes1=parseInt(mes1,10);    
  	mes2=parseInt(mes2,10);
  	anyo1=parseInt(anyo1,10);  
  	anyo2=parseInt(anyo2,10);
  	
  	if ((anyo1==anyo2) && (mes1==mes2) && (dia1==dia2))
   	{
   		return true;
   	}
  
  	if (anyo1>anyo2)
   	{
   		return true;
   	}
  	if ((anyo1==anyo2) && (mes1>mes2))
   	{
   		return true;
   	}
  	if ((anyo1==anyo2) && (mes1==mes2) && (dia1>dia2))
   	{
   		return true;
   	}
 
  	return false;
}

function Comparar_Fecha_Anyo(Obj1,Obj2,Anyo){

	//Retorna True si (Obj1 + Anyo) es mayor que Obj2

	String1 = Obj1;
 	String2 = Obj2;
 	
  	dia1=String1.substring(0,2);
  	mes1=String1.substring(3,5);
  	anyo1=String1.substring(6,10);
  	dia2=String2.substring(0,2);
  	mes2=String2.substring(3,5);
  	anyo2=String2.substring(6,10);
  	
  	dia1=parseInt(dia1,10);    
  	dia2=parseInt(dia2,10);
  	mes1=parseInt(mes1,10);    
  	mes2=parseInt(mes2,10);
  	anyo1=parseInt(anyo1,10);  
  	anyo2=parseInt(anyo2,10);
  
  	anyo3= parseInt(Anyo,10);
  
  	anyo2 = anyo2 + anyo3;
	
	if ((anyo1==anyo2) && (mes1==mes2) && (dia1==dia2))
   	{
   		return false;
   	}
  
  	if (anyo1>anyo2)
   	{
   		return false;
   	}
  	if ((anyo1==anyo2) && (mes1>mes2))
   	{
   		return false;
   	}
  	if ((anyo1==anyo2) && (mes1==mes2) && (dia1>dia2))
   	{
   		return false;
   	}
 
  	return true;
}