<%--
******************************************************************************************************
* Descripción
* author  @Cristian.Ceròn
* version 1.0
* Fecha creación    Ago-2008
* Historia de cambios
   versión   fecha      autor   cambios
   =======   ========== ======= =================================
   1.0                          versión inicial
******************************************************************************************************
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html:html> 

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<link href="css/Interna_Araucana.css" rel="stylesheet" type="text/css" />
<title>La Araucana C.C.A.F. - Lista de Scripts</title>

<style type="text/css">
<!--
#scroll{
     width:700px;
     height:300px;
     background-color:#FFFFFF;
     overflow:auto;
}
.Estilo1 {font-size: 10px}
-->
</style>
</head>

<script>

var lists = new Array();
var dwCntrl = new Array();

var cntList = 0;
var msgERR  = "";

var inicioTabla = "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
inicioTabla += '<td class="barratablas">&nbsp;</td>';
inicioTabla += '<td class="barratablas">Nombre</td>';
inicioTabla += '<td class="barratablas">Tipo</td>';
inicioTabla += '<td class="barratablas" width=200>Especificación</td>';
inicioTabla += '<td class="barratablas">Valor por<br>Defecto</td>';
inicioTabla += '<td class="barratablas">Comentario</td>';
inicioTabla += '</tr>';


<logic:present name="listTypes" scope="request" >
   <logic:iterate id="typ" name="listTypes" >
      lists[cntList++] = "<bean:write name='typ' property='nomLista' />";
   </logic:iterate>
</logic:present>

//lists.sort();

//var tps = new Array("tInteger","tString", "tDate");

//var LstTipos = new Array("Integer","String", "Date", "Period","List"); 


/*  Condiciones de Validacion */
var cnd = new Array("SnSpac",
                    "CntMin",
                    "CntMax",
                    "LstTipos",
                    "SoloNum",
                    "nombGen",
                    "fchYMD");

//var tInteger = new Array("integer");


function putCntl(t, n, v) {
   var res="";

   if (!addCntl(n, t, "1", v)) {   
      res += '  <div name="ctlView_' + n + '" id="ctlView_' + n + '">';
      res += '    <input ';
      res += '           type="text" ';
      res += '           name="' + n + '" ';
      res += '           id="' + n + '" ';
      res += '           value="' + v + '" ';
      res += '    />';
      res += '  </div>';
   }
   
   return res;    
}

function addCntl(c, t, ctrlStat, v) {
	var swFound = false;
	var ic = 0;
	var ctlSpec;
	var typSpec;

	for (ic=0; ic < dwCntrl.length; ic++) {
       ctlSpec = dwCntrl[ic];
       if (ctlSpec[0] == c) {
	      swFound = true;
	      
	      
	      break;
	   }
	   
	}
	if (!swFound) {
	   typSpec = new Array(t, ctrlStat, v, v);
       ctlSpec = new Array(c, typSpec);
       dwCntrl[ic] = ctlSpec;	    
	} else {
       alert("[Ctrl-Display] \n\nEl control ..." + n + " ya existe");
	}

    return swFound;
    	
}



addCntl("_genericTxt","text");
addCntl("_genericLst","list");
addCntl("_genericTAr","area");
addCntl("_genericSel","sel");
addCntl("_genericRad","radio");
addCntl("_genericChk","check");


/*
for (var i=0;i<lists.length;i++) {
    alert ("lists( "+ i + ") =" + lists[i]);
} 
*/




//     document.write('            <td width="65" class="textospie">ID '+xPerfil+' : </td>');


function validaNroSis() {
   if (nSistemas == 1) {
      location.href = "detalle_sistema_1.html?perfil="+xPerfil+"&nSistemas="+nSistemas;
   }
}



function lenOk(v, ts) {
      var vOk = false;
      
      minV = 0;
      maxV = 0;
      
//      alert("valida que " + v + " cumpla (" + ts + ")");

      var arrValid_TS = new Array();
      arrValid_TS = ts.split(",");

      if (arrValid_TS[0] == "*" & arrValid_TS.length == 1) {
         minV = 0;
         maxV = 'n';
      } else {
         if (arrValid_TS[0] == "*" & arrValid_TS.length > 1) {
             minV = 1;
             maxV = arrValid_TS[1];
         } else {
             if (arrValid_TS[0] != "*" & arrValid_TS.length == 1) {
                minV = arrValid_TS[0];
                maxV = arrValid_TS[0];
             } else {
                if (arrValid_TS[1] == "*") {
                   minV = arrValid_TS[0];
                   maxV = 'n';
                } else {             
                   minV = arrValid_TS[0];
                   maxV = arrValid_TS[1];
                }
             }         
         }
      }


//      alert("... el len debe ser  >= " + minV + " y <= " + maxV);
      isOk = false;
      if (maxV == 'n') {
         if (v.length >= minV) {
            isOk = true;
//            alert("... CUMPLE EN X");
         }
      } else {
         if (v.length >= minV & v.length <= maxV) {
//            alert("... CUMPLE EN Y");
            isOk = true;
         }
      }      

      if (!isOk) {      
          msgERR  += "\n* La cantidad de caracteres debe ser ";
      
          if (minV == maxV) 
              msgERR  += "[ " + minV + " ] en forma exacta.";
          else 
              msgERR  += " mayor o igual a [ " + minV + " ]";
      
          if (maxV != "n" & minV != maxV) 
              msgERR  += "\n  y, menor o igual que [ " + maxV + " ]";
      }
      
      return isOk;
}


function validNum(v, ts) {
      var vOk = false;

      if (v.length > 0) 
          vOk = lenOk(v, ts);
      else 
          msgERR  += "\n* Como valor númerico, debe ingresar a lo menos un dígito.";
      
/*
      if (!vOk) 
             alert(v + "... NO CUMPLE(fase 1)");	
      else
             alert(v + "... CUMPLE(fase 1)");	
*/             
      if (vOk) {
         for (var ix=0; ix < v.length; ix++) 
           if (v.substring(ix,ix + 1) < '0' || v.substring(ix,ix + 1) > '9') {
//              alert("esta " + v.substring(ix,ix + 1) + " no cumple en " + v);
              vOk = false;
              msgERR  += "\n* No puede contener caracteres alfanuméricos.";
              break;          
              }
      } 
      


//      if (!vOk) 
//          alert(v + "... NO CUMPLE");	
//      else
//          alert(v + "... CUMPLE");	

      return vOk;

}


function validFileName(o, ext) {
    var res = true;

    var nok = ' _/\:*?"<>|';
    var tToValid = o.value;

    if (tToValid.length == 0) {
        res = validNotNull(o);
    } else {
	   for (var i=0; i< tToValid.length; i++) 
	      if (nok.indexOf(tToValid.substring(i, i + 1)) >= 0) 
	          res = false;
	          
	    if (!(res))
	       alert("Los caracteres '" + nok + "' son de uso reservado por el sistema. Por favor, corrija el valor.");
    }
    
 
    if (res) {
       if (tToValid.indexOf(ext) < 1) {
           alert("Se debe especificar la extensión ["+ext+"]para el archivo. Será agregada.");
           o.value = o.value + ext;
       }
    }

    if (!(res)) {
//       o.focus();
    }
        
//    return res;    
    return true;    
    
}


function validClasicVarName(o) {
   var res = true;
   
   var nok = ' _/\:*?"<>|';
    var tToValid = o.value;

    if (tToValid.length == 0) {
        res = validNotNull(o);
    } else {
   for (var i=0; i< tToValid.length; i++) 
      if (nok.indexOf(tToValid.substring(i, i + 1)) >= 0) 
          res = false;
          
    if (!(res)) 
	       alert("Los caracteres '" + nok + "' no pueden ser utilizados como nombre de parámetro.\n Por favor, corrija el valor.");
    }
    
 
    if (res) {
       if (tToValid.substring(0,1).toUpperCase() < "A" || tToValid.substring(0,1).toUpperCase() > "Z") {
           alert("El nombre del parámetro DEBE comenzar con una letra.");
           res = false;
       }
    }

//    if (!(res)) o.focus();
        
    return res;    
}
       
function validNotNull(o) {
   var res = true;
   
   var tToValid = o.value;

   if (tToValid.length == 0) {
        res = false;
//        alert("Este valor es OBLIGATORIO.");
    }
    
//    if (!(res)) o.focus();       
    return res;
}





function validPer(v) {
      var vOk = false;

//      if (v.length > 0) 
//          vOk = lenOk(v, "6");
          
      vOk = validNum(v, "6");
      
/*
      if (!vOk) 
             alert(v + "... NO CUMPLE(fase 1)");	
      else
             alert(v + "... CUMPLE(fase 1)");	
*/             
      if (vOk) {
      
           var anio = parseInt(v.substring(0,4));
           var mes  = parseInt(v.substring(4,6).replace("0",""));

 //          alert("mes [" + mes + "]   anio [" + anio + "]");
           
           if (anio < 1900 || anio > 2100 || mes < 1 || mes > 12) {
              vOk = false;
              msgERR  += "\n* Debe ser un periodo que cumpla con YYYYDD.";
           }
      }


//      if (!vOk) 
//          alert(v + "... NO CUMPLE");	
//      else
//          alert(v + "... CUMPLE");	

      return vOk;

}


function validDate(v) {
      var vOk = false;

//      if (v.length > 0) 
//          vOk = lenOk(v, "6");
          
      vOk = validNum(v, "8");
      
/*
      if (!vOk) 
             alert(v + "... NO CUMPLE(fase 1)");	
      else
             alert(v + "... CUMPLE(fase 1)");	
*/             
      if (vOk) {
      
           var anio = parseInt(v.substring(0,4));
           var mes  = parseInt(v.substring(4,6).replace("0",""));
           var dia  = parseInt(v.substring(6,8).replace("0",""));

//          alert("mes [" + mes + "]   anio [" + anio + "]  dia [" + dia + "]");
           
           if (anio < 1900 || anio > 2100 || mes < 1 || mes > 12 || dia < 1 || dia > 31 ) {
              vOk = false;
              msgERR  += "\n* Debe ser una fecha válida que cumpla con el formato YYYYMMDD.";
           }
      }


//      if (!vOk) 
//          alert(v + "... NO CUMPLE");	
//      else
//          alert(v + "... CUMPLE");	

      return vOk;

}


function validStr(v, ts) {
      var vOk = false;

      vOk = lenOk(v, ts);

//      if (!vOk) 
//          alert(v + "... NO CUMPLE");	

      return vOk;

}


function refrescaFromLista() {
    var oki = true;
    var nSntx = "";

	valor = document.forms[0].nParms.value;

    if (document.getElementById("act").value == "ver") {
  	  for(i2=1;i2<=valor;i2++) {
        document.getElementById("selectParm_$"+i2).disabled = true;
  	  
        document.getElementById("name_$"+i2).disabled = true;
        document.getElementById("type_$"+i2).disabled = true;
        if (document.getElementById("type_spect_$"+i2) != null) 
            document.getElementById("type_spect_$"+i2).disabled = true;
        if (document.getElementById("type_specs_$"+i2) != null) 
            document.getElementById("type_specs_$"+i2).disabled = true;
            
        if (document.getElementById("default_valuet_$"+i2) != null) 
            document.getElementById("default_valuet_$"+i2).disabled = true;
        if (document.getElementById("default_values_$"+i2) != null) 
            document.getElementById("default_values_$"+i2).disabled = true;
            
//        document.getElementById("default_value_$"+i2).disabled = true;
        document.getElementById("comment_$"+i2).disabled = true;
      }
    } else {
      var xn;
      var yn;
  	  for(var i2=1;i2<=valor;i2++) {
        xn = document.getElementById("name_$"+i2).value;
        if (i2 < valor) {
    	   for(var ir= i2 + 1;ir<=valor;ir++) {
              yn = document.getElementById("name_$"+ir).value;
    	      if (xn == yn) {
    	         oki = false;
    	      }
           }
        }
        if (oki) 
           nSntx += ' <' + xn + '>';
        
      }
    
      document.forms[0].sintaxis.value = nSntx;
    }

    return oki;

}





function checkEntries(ope, v) {

//    alert ("campo:" + v.name );

	var posP = v.name.indexOf("$") + 1;
	var nam  = v.name.substring(0,posP - 2); 
	var num  = v.name.substring(posP);
    
    /* ACTUAL (onchange)  */
    var val  = v.value;


    
    var valt = document.getElementById("type_$"+num).value;
    
    var eDynTS; 
    var vDynTS;


      if (eDynTS = document.getElementById("type_spect_$"+num) == null) {
         eDynTS = document.getElementById("type_specs_$"+num);
      } else {
         eDynTS = document.getElementById("type_spect_$"+num);
      }
         

      var valde;
      if (dDynTS = document.getElementById("default_valuet_$"+num) == null) {
         dDynTS = document.getElementById("default_values_$"+num);
      } else {
         dDynTS = document.getElementById("default_valuet_$"+num);
      }
    
    var valde= dDynTS.value;



    var isOk = true;

//	alert ("campo:" + nam + ":num=" + num + " ["+val+"]");




	/***************************************************************/
	/*  Estoy actualizando el "nombre"                               */
	/***************************************************************/
	if (nam == "name") {
	      var ts = "";
	      var de = "";
	      var co = "";
//	      var nomOk = true;
	      
	      var nNam = "";

          isOk = validClasicVarName(v);
	      if (isOk) {
	          if (!(isOk = refrescaFromLista())) 
	              msgERR += "* El nombre del parámetro debe ser UNICO.";
	      }
//		      document.forms[0].sintaxis.value += ' <' + document.getElementById("name_$"+num).value + '>';
  

/*
	      for (var ix=0; ix < val.length; ix++) 
	          if ( (val.substring(ix,ix + 1) >= '0' & val.substring(ix,ix + 1) <= '9') ||
	               (val.substring(ix,ix + 1).toUpperCase() >= 'A' & val.substring(ix,ix + 1).toUpperCase() <= 'Z') ) 
	               nNam += val.substring(ix,ix + 1);
	          else {
	              isOk = false;
	              msgERR += "El Nombre del parámetro es inválido. Debe ser un nombre 'genérico'.   Ha sido corregido.";
              }
*/
//          if (ope == 'edt') {
//	          if (nNam != document.getElementById("name_$"+num).value) {
//			      document.getElementById("name_$"+num).value = nNam;
//	          }              
//		      document.forms[0].sintaxis.value += ' <' + document.getElementById("name_$"+num).value + '>';
//	          document.getElementById("type_$"+num).focus();
//          }              
      }

	/***************************************************************/
	/*  Estoy actualizando el "type"                               */
	/***************************************************************/
	if (nam == "type") {
	   if (val == "date" || val == "period") {

          /************************************************/
          /* Tipos : date y period                        */
          /************************************************/
	      var ts = "";
	      var de = "";
	      var co = "";
	      if (val == "date") {
	         ts  = "";
	         de  = "current";
	         co  = "";
	      }
	      if (val == "period") {
	         ts  = "";
	         de  = "current";
	         de  = "";
	         co  = "";
	      }
          if (ope == 'edt') {
//		      document.getElementById("default_value_$"+num).value = de;
		      document.getElementById("comment_$"+num).value = co;
//  	          document.getElementById("default_value_$"+num).focus();
		  }
	      eDynTS.style.visibility = "hidden";
//	      dDynTS.style.visibility = "hidden";

	   } else {
          /************************************************/
          /* Tipos : integer, string y list               */
          /************************************************/
	      var ts = "";
	      var de = "";
	      var va = "";
	      
	      if (ope == "shw") {
  	         va = eDynTS.value;
  	         de = dDynTS.value;
	      }
	      
	      var co = "";
	      if (val == "integer") {
	         ts  = "*";
//	         de  = "0";
	         co  = "";
	         //                     eDynTS.value
	         eDynTS = cambiaTypeSpec(num, va , "xx", true);
	         cambiaDefault(num, de , "xx");
//             eDynTS = document.getElementById("type_spect_$"+num);
	      }
	      if (val == "string") {
	         ts  = "*";
//	         de  = "";
	         co  = "";
	         eDynTS = cambiaTypeSpec(num, va , "xx", true);
	         cambiaDefault(num, de , "xx");
//             eDynTS = document.getElementById("type_spect_$"+num);
	      }
	      if (val == "list") {
	         ts  = "";
//	         de  = "";
	         co  = "";
	         eDynTS = cambiaTypeSpec(num, va , "aL", true);
	         dDynTS = cambiaDefault(num, de , eDynTS.value);

//             document.getElementById("default_value_$"+num).value = de;
//             document.getElementById("comment_$"+num).value = co;
	      } else {
          if (ope == 'edt') {
 	            eDynTS.value = ts;
 	            dDynTS.value = ts;
//              document.getElementById("default_value_$"+num).value = de;
              document.getElementById("comment_$"+num).value = co;
          }
	   }
 //         eDynTS.focus();
	   }
	
	}

//alert("pasa con ?" + nam);


	if (nam.substring(0,6) == "type_s") {  
          /************************************************/
          /* Tipos : integer, string y list               */
          /************************************************/
	      var de = "";
//	      var tsOk = false;
//          var tsOk = "Error no catalogado!";

          if (ope == 'edt') {
	      
		      if (valt == "integer" ||
	  	          valt == "list" ||
		          valt == "string") {
		          
//alert("pasa con ?" + de);

			      if (valt == "list") {
			         ts  = "";
			         de  = "";
			         co  = "";
			         dDynTS = cambiaDefault(num, de, val );
			      } else {
			         dDynTS = cambiaDefault(num, de , "xx");
		          
		             minT = 0;
		             if (valt == "integer") {
		                minT = 1;
		             }
		          
	                 var arrValid_TS = new Array();
	                 arrValid_TS = val.split(",");
	 //                alert("... son " + arrValid_TS.length + " args en :" + val);
	 //                alert("... y el val del 1 es " + arrValid_TS[0]);
	
	                 if (arrValid_TS.length > 2) {
  	                    isOk = false;
	                    msgERR += "TYPE_SPEC utiliza como máximo 2 argumentos. \n\nNo pueden ser : " + arrValid_TS.length;
	                //    tsOk = "TYPE_SPEC utiliza como máximo 2 argumentos. \n\nNo pueden ser : " + arrValid_TS.length;
	                 } else {
	                    if (arrValid_TS.length > 0 & arrValid_TS[0] != "") {
	                       if (isNaN(arrValid_TS[0]) & arrValid_TS[0] != "*") {
    	                       isOk = false;
	                           msgERR += "El valor [" + arrValid_TS[0] + "] es incorrecto";
	                      //     tsOk = "el valor [" + arrValid_TS[0] + "] es incorrecto";
	                       } else {
	                           if (arrValid_TS[0] == "*" || parseInt(arrValid_TS[0]) >= minT) { // vamos bien
	                             if (arrValid_TS.length > 1) {
				                   if (isNaN(arrValid_TS[1]) & arrValid_TS[1] != "*") {
     	                               isOk = false;
	                                   msgERR += "El valor [" + arrValid_TS[1] + "] es incorrecto";
				                   //    tsOk = "el valor [" + arrValid_TS[1] + "] es incorrecto";
	                               } else {
	                                   if (arrValid_TS[0] == "*" || arrValid_TS[1] == "*") {
	                                         if (arrValid_TS[0] == "*" & arrValid_TS[1] == "*") {
       	                                          isOk = false;
	                                              msgERR += "Ha utilizado incorrectamente el comodín '*'.";
		      		                       //       tsOk = "Ha utilizado incorrectamente el comodín '*'";
	                                         } else { isOk = true; }
	                                   } else {
	                                         if (arrValid_TS[0] > arrValid_TS[1]) {
       	                                         isOk = false;
	                                             msgERR += "El primer argumento debe ser menor.";
		    		                       //      tsOk = "El primer argumento debe ser menor.";
	                                         } else { isOk = true; }
	                                   }
	                                }                                  
	                             } else { isOk = true;   }
	                           } else {
                                   isOk = false;
                                   msgERR += "Error en el min. valor para el tipo.";
	                    //           tsOk = "Error en el min. valor para el tipo.";  
	                           }
	                        }
	                    } else { 
                            isOk = false;
                            msgERR += "Este tipo requiere un 'type_spec'.";
	                   //     tsOk = "Este tipo requiere un 'type_spec'."; 
	                    } 
	                }
//	                if (tsOk != "") {
//	                    alert("Error! (" + val + ")\n" + tsOk);
//	                } else {
//				        document.getElementById("default_value_$"+num).focus();
//	                }
	           }
	      }
	      }

//	      document.getElementById("type_spec_$"+num).value = ts;
//	      document.getElementById("type_spec_$"+num).style.visibility = "";
//	      document.getElementById("type_spec_$"+num).focus();

             /* validar que <default> cumpla con el nuevo valor de 
                type_spec                                           */
	
	}


	if (nam.substring(0,9) == "default_v") {
          /************************************************/
          /* Tipos : integer, string, list, date y period */
          /************************************************/
	      var de = "";

//          alert("a validar defa de " + val + " para " + valt);   

          if (ope == 'edt') {
		      if (valt == "date" || valt == "period") {
		         if (val.substring(0,1) == "+" || val.substring(0,1) == "-" ) {
		               var zcnt = parseInt(val.substring(1));
		               if (isNaN(zcnt)) {  
	 	                  isOk = false;
		                  msgERR += "Debe definir un valor numérico para este Type_Spec";   
		               }
	             } else {
		             if (   val != "current" 
		               &  val != "next"
		               &  val != "previous"  ) {
		               isOk = false;
		               msgERR += "Los valores posibles son 'current', 'next', 'previous' y +/-n";   
	                 }             
	             }
		      }
	
	
		      if (valt == "integer") {
		         isOk = validNum(val, eDynTS.value);
		      }
	
		      if (valt == "string")  {
	                 xPlantilla = evalString(val);   //getPlantilla
		         if (!validStr(xPlantilla, eDynTS.value)) {
 	                    isOk    = false;
 	                    msgERR += "El Type_Spec no cumple con el tamaño";
		         }
		      }
		      
/*		      if (valt == "list") {
		         if (   val != "<first>" 
		             &  val != "<selected>" ) {
		               isOk    = false;
		               msgERR += "Los valores posibles son <first> y <selected>";   
		             } 
		      }
*/		      
//	          if (!isOk) {
//	               alert("ERROR ! (" + val + ")\n" + isOk);   
//	          } else {
//	  	           document.getElementById("comment_$"+num).focus();
//	          }
          }	
	}


      if (!isOk) { // & msgERR.length > 0) {
//         v.focus();
         alert("ERROR !\nEl valor ingresado debe cumplir con los siguientes requisitos:\n" + msgERR);
         msgERR  = "";
//         v.value = document.getElementById("param_$"+num).value;
      }          
      return isOk;

	
}



function cambiaTypeSpec(xDiv, val, nTip, shw) {

	resul = document.getElementById("d$" + xDiv);
    resul.innerHTML="";

	var tablas;
	tablas = getTypeSpecObject(xDiv, val, nTip, shw);


    resul.innerHTML = resul.innerHTML + tablas;

     if (nTip == "aL")
         e = "s";
     else 
         e = "t";

     o = document.getElementById("type_spec" + e + "_$" + xDiv);		
     return o;

}

//Para cambiar el ancho de especificadores de tipo
//cambiar propiedad style="width:"
function getTypeSpecObject(xDiv, val, nTip, shw) {

	var tablas="";
    var sepa; 
    var sepa2;
     if (nTip != "xx") {
         tablas = tablas + '<select style="width:210px;" '
         tablas = tablas + ' id="type_specs_$'+xDiv+'"';
         tablas = tablas + ' name="type_specs_$'+xDiv+'"'; 
         tablas = tablas + ' value="'+val+'" '; 
         tablas = tablas + ' onchange="javascript:return checkEntries(\'edt\',this);">';
         var sepa;
         var xtL = nTip;
         for (var i=0;i<lists.length;i++) {
              sepa = lists[i].split("?");
              if (sepa[0] != xtL) {
                 xtL = sepa[0];
                 tablas += "<option value='" + xtL  + "' ";
                 if (xtL == val) tablas += " selected ";
                 tablas += " >" + getJustif(xtL, 10, "i", " ") + "</option>";
              }
         }

         tablas = tablas + '</select>';
     } else {     
		 tablas = tablas + "<input  size='12'  style='width:100px;' ";
		 tablas = tablas + "id='type_spect_$"+xDiv+"' ";
		 tablas = tablas + "name='type_spect_$"+xDiv+"' ";
		 tablas = tablas + 'value="'+val+'" ';
         tablas = tablas + ' onchange="javascript:return checkEntries(\'edt\',this);" ';
		 tablas = tablas + "type='";
         if (!(shw))
            tablas = tablas + "hidden";
         else 
            tablas = tablas + "text";
         		 
		 tablas = tablas + "'/>";
		}         

		return tablas;

}


function getJustif(t, l, j, r) {
   var wrk = t;
   var res;
	
   for (var i = 1; i <= l; i++) {
       if (j == "i")
           wrk += r;
       else 
         wrk = r + wrk;
   }
   if (j == "i")
      res = wrk.substring(0, l);
   else 
      res = wrk.rigth(l);
	
   //return res;      
   return t;      
}



function cambiaDefault(xDiv, val, nTip) {

	resul = document.getElementById("dd$" + xDiv);
    resul.innerHTML="";

	var tablas;
	tablas = getDefaultObject(xDiv, val, nTip);


    resul.innerHTML = resul.innerHTML + tablas;

     if (nTip == "aL")
         e = "s";
     else 
         e = "t";

     o = document.getElementById("default_value" + e + "_$" + xDiv);		
     return o;

}


function getDefaultObject(xDiv, val, nTip) {

	var tablas="";
    var sepa; 
    var sepa2;
     if (nTip != "xx") {
         tablas = tablas + '<select style="width:120px;" '
         tablas = tablas + ' id="default_values_$'+xDiv+'"';
         tablas = tablas + ' name="default_values_$'+xDiv+'"'; 
         tablas = tablas + ' value="'+val+'" '; 
         tablas = tablas + ' onchange="javascript:return checkEntries(\'edt\',this);">';

         var iSel = "";
         for (var i=0;i<lists.length;i++) {
              sepa = lists[i].split("?");
              if (sepa[0] == nTip) {
                 sepa2 = sepa[1].split("(");
                 tablas = tablas + "<option value='" + sepa2[0] + "' ";
                 if (sepa2[0] == val) {
                    tablas += " selected ";
                 }

                 if (sepa2[1] == "*)") {
                    iSel = "(*)";
                 }
                 tablas += " >" + getJustif(sepa2[0] + iSel , 17, "i", " ") + "</option>";
                 iSel = "";
              }
         }
         
         tablas = tablas + '</select>';
     } else {     
		 tablas = tablas + "<input  size='15' style='width:120px;' ";
		 tablas = tablas + "id='default_valuet_$"+xDiv+"' ";
		 tablas = tablas + "name='default_valuet_$"+xDiv+"' ";
		 tablas = tablas + 'value="'+val+'" ';
         tablas = tablas + ' onchange="javascript:return checkEntries(\'edt\',this);" ';
		 tablas = tablas + "type='text'/>";
		}         

		return tablas;

}


// DEPRECAR ****************************************************************
function XcambiaDefaultAsValue(xDiv, ts, val, nTip) {

	resul = document.getElementById("d$" + xDiv);
	
//	alert ("trabajaremos con " + resul.innerHTML);
	var tablas="";

    resul.innerHTML="";

	
	var sepa;
     if (nTip == "aL") {
         e = "s";
         tablas = tablas + '<select style="width:235px;" '
         tablas = tablas + ' id="values_$'+xDiv+'"';
         tablas = tablas + ' name="values_$'+xDiv+'"'; 
         tablas = tablas + ' value="'+val+'" '; 
         tablas = tablas + ' onchange="javascript:return checkValue(\'edt\',this);">';
         for (var i=0;i<lists.length;i++) {
              sepa = lists[i].split("?");
              if (sepa[0] == ts) {
                 tablas = tablas + "<option value='" + sepa[1] + "' >" + sepa[1] + "</option>";
              }
         }
         tablas = tablas + '</select>';
     } else {     
         e = "t";
		 tablas = tablas + '<input  style="width:235px;" ';
		 tablas = tablas + 'id="valuet_$'+xDiv+'" ';
		 tablas = tablas + 'name="valuet_$'+xDiv+'" ';
//         tablas = tablas + ' size="30" '; 
		 tablas = tablas + 'value="'+val+'" ';
         tablas = tablas + ' onchange="javascript:return checkValue(\'edt\',this);" ';
		 tablas = tablas + 'type="text"/>';
		}         
		

 	    
//		alert(tablas);
		resul.innerHTML = resul.innerHTML + tablas;
        o = document.getElementById("value" + e + "_$" + xDiv);		
		return o;

}





var dte = (new Date((new Date()).getTime())).toGMTString();
var arrDte = new Array();
arrDte = dte.split(" ");

year4  = parseInt(arrDte[3]);
year2  = parseInt(arrDte[3].substring(2,4).replace("0",""));

var month;
if (arrDte[2] == "Jan") {month = 1;}
if (arrDte[2] == "Feb") {month = 2;}
if (arrDte[2] == "Mar") {month = 3;}
if (arrDte[2] == "Apr") {month = 4;}
if (arrDte[2] == "May") {month = 5;}
if (arrDte[2] == "Jun") {month = 6;}
if (arrDte[2] == "Jul") {month = 7;}
if (arrDte[2] == "Aug") {month = 8;}
if (arrDte[2] == "Sep") {month = 9;}
if (arrDte[2] == "Oct") {month = 10;}
if (arrDte[2] == "Nov") {month = 11;}
if (arrDte[2] == "Dec") {month = 12;}

var day = arrDte[1];
if (parseInt(arrDte[1]) < 10) {
    day = "0" + parseInt(arrDte[1]);
}


function evalString(v) {
   var xPlant = "";
   var posI = 0;
   var posT = 0;
      
   var pes = "$";
   xPlant = v.replace(pes + "{O","$_O");

   posI = xPlant.indexOf("$_O") + 1;
   var cntInc = 0;
   if (posI > 0)    {
      posT        = xPlant.indexOf("}", posI + 1);

      posTdefTestMas = xPlant.indexOf("=+", posI + 1);
      if (posTdefTestMas > 1) {
          cntInc = parseInt(xPlant.substring(posTdefTestMas + 2, posT));
      } else {      
          posTdefTestMen = xPlant.indexOf("=-", posI + 1);
          cntInc = parseInt(xPlant.substring(posTdefTestMen + 2, posT)) * -1;
      }
            
      xPlant      = xPlant.substring(0, posI - 1) + xPlant.substring(posT + 1);
   }                 

   var achange = 0;
   var mchange = 0;

   var zy2 = year2;
   var zy4 = year4;
   var zmn = month;
   
   if (cntInc > 0) {
      mchange = month + cntInc;
      achange = parseInt(mchange / 12);
      zy4     = year4 + achange;
      zy2     = year2 + achange;
      zmn     = mchange - (achange * 12);
   }
   if (cntInc < 0) {
      cntInc  = cntInc * -1;
      achange = parseInt(cntInc / 12);
      mchange = month - (cntInc - achange * 12);
      zy4     = year4  - achange;
      zy2     = year2  - achange;
      zmn     = mchange;
   }


//   zy2 = year2;   
   if (zy2 < 10) {
      zy2 = "0" + zy2;   
   }
//   zm = month;   
   if (zmn < 10) {
      zmn = "0" + zmn;   
   }

   xPlant = xPlant.replace(pes + "{Y}",zy4);
   xPlant = xPlant.replace(pes + "{y}",zy2);
   xPlant = xPlant.replace(pes + "{m}",zmn);
   xPlant = xPlant.replace(pes + "{d}",day);
//   xPlant = xPlant.replace(pes + "{d}",day);
   
//   alert("Test Default:" + xPlant +"\nlen=" + xPlant.length);
   return xPlant;
}

function evalDate(v) {
   var xPlant = "";
   var posI = 0;
   var posT = 0;


   var achange = 0;
   var mchange = 0;

   var zy4 = parseInt(year4);
   var zmn = parseInt(month);
   var zdy = parseInt(day);

   var cntInc = 0;

   if (v == "next") {
       cntInc = 1; 
   } else {
       if (v == "previous") {
           cntInc = -1;
       } else {
           posI = v.indexOf("+") + 1;
           if (posI > 0) {
               cntInc = parseInt(v.substring(posI));
           } else {
               posI = v.indexOf("-") + 1;
               if (posI > 0) {
                   cntInc = parseInt(v.substring(posI)) * -1;
               }
           }
       }
   }
           
//   alert("inc=" + cntInc);
   
   if (cntInc > 0) {
      dchangeT = zdy + cntInc;
      mchangeI = parseInt(dchangeT / 30); 
      mchangeN = month + mchangeI;
      achange = parseInt(mchangeN / 12);
      zy4     = year4 + achange;
      zmn     = mchangeN - (achange * 12);
      zdy     = dchangeT - (mchangeI * 30);
   }

   if (cntInc < 0) {
      cntInc  = cntInc * -1;
      if ((zdy - cntInc) < 1) {
         mchangeI = parseInt(cntInc / 30);
         dchangeT = 30 - (cntInc - mchangeI * 30) + zdy;
         mchangeT = parseInt((cntInc + dchangeT) / 30);
      } else {
         mchangeI = 0;
         mchangeT = 0;
         dchangeT = zdy - cntInc;
      }

      zmn      = zmn - mchangeT;
      zdy      = dchangeT;

//      achange = parseInt(mchangeN / 12);
//      zy4     = year4 + achange;
   }


   if (zmn < 10) {
      zmn = "0" + zmn;   
   }
   if (zdy < 10) {
      zdy = "0" + zdy;   
   }
     
   xPlant = zy4 + zmn + zdy;
   
//   alert("Test Default:\n  [year=" + zy4 + "]\n[zmn=" + zmn + "]\n[d=" + zdy + "]");
   return xPlant;
}

function evalPeriod(v) {
   var xPlant = "";
   var posI = 0;
   var posT = 0;


   var achange = 0;
   var mchange = 0;

   var zy4 = parseInt(year4);
   var zmn = parseInt(month);
   var zdy = parseInt(day);

   var cntInc = 0;

   if (v == "next") {
       cntInc = 1; 
   } else {
       if (v == "previous") {
           cntInc = -1;
       } else {
           posI = v.indexOf("+") + 1;
           if (posI > 0) {
               cntInc = parseInt(v.substring(posI));
           } else {
               posI = v.indexOf("-") + 1;
               if (posI > 0) {
                   cntInc = parseInt(v.substring(posI)) * -1;
               }
           }
       }
   }
           
//   alert("inc=" + cntInc);

   zy4 = getAnio(cntInc);
   zmn = getMes(cntInc);   

   xPlant = zy4 + zmn;
   
//   alert("Test Default:\n  [year=" + zy4 + "]\n[zmn=" + zmn + "]\n[d=" + zdy + "]");
   return xPlant;
}



function getAnio(vInc) {
   var a = parseInt(year4);
   
   if (vInc > 0) {
      mchangeN = month + vInc - 1;
      achange = parseInt(mchangeN / 12);
      a       = a + achange;
   }
   if (vInc < 0) {
      vInc        = vInc * -1;
      if ((month - vInc) < 1) {
         mchangeI = parseInt(vInc / 12);
      } else {
         mchangeI = 0;
      }
      a        = a - mchangeI;
   }

   return "" + a;
}


function getMes(vInc) {
   var m = parseInt(month);
   
   aCal = parseInt(getAnio(vInc));
   aAct = parseInt(getAnio(0));
   
   if (vInc > 0) {
      vInc = vInc - ((aCal - aAct) * 12)

      mchangeN = m + vInc;
      if (mchangeN > 12) {
          achange  = parseInt(mchangeN / 12);
      } else {
          achange  = 0;
      }
      m        = mchangeN - (achange * 12);
   }

   if (vInc < 0) {
      vInc  = vInc * -1;
      vInc = vInc - ((aAct - aCal) * 12)
      if ((m - vInc) < 1) {
         mchangeI = 12 + (m - vInc);
      } else {
         mchangeI = m - vInc;
      }

      m        = mchangeI;

//      achange = parseInt(mchangeN / 12);
//      zy4     = year4 + achange;
   }


   if (m < 10) {
      m = "0" + m;   
   }

   return "" + m;
}


function getDia(vInc) {
   if (day.substring(0,1) == "0") day = day.replace("0","");
   var d = parseInt(day);
   
   if (d < 10) {
      d = "0" + d;   
   }

   return "" + d;
}





var u = document.location.href;

var xAccion = u.substring(u.indexOf('=') + 1);


function avisoDuplicaScript(o) {
//   res = false;
//   if (validFileName(document.forms[0].nomScript, ".pss")) {
//      res = true;
	  q = 'Presione <Aceptar> para Crear un script nuevo a partir de este (o <cancelar> para anular operación)';
	  answer = confirm(q);
	  if (answer) {
	      msg = "<b>Duplicando Script.</b>";
	      document.forms[0].fchCreacion.value = document.forms[0].hoy.value;
	      document.getElementById("xact").innerHTML = msg;  
	  }
//   } else {
 //     o.focus();
//   }
//   return res;
}

function setFecha() {
	      var fc = getDia(0) + "/" + getMes(0) + "/" + getAnio(0);
	      return "" + fc;
}

function Agrega() {
   o = agregando();
   setTimeout("o.focus();",500); 
}

function agregando() {
	resul = document.getElementById('parametros');
	var valor=0;
	var tablas="";

	var finTabla = "</table>";
	
	var iniData  = "<tr>";
	
	
	
    iniData += "<td class='textos-formcolor2'>";

	var endData  = "</td></tr>";

	var despliegue="";
	valor = parseInt(document.forms[0].nParms.value);
	
	for(i=1;i<=valor;i++) {
		 tablas = tablas + iniData;

         /* ex-getRow-code */
         tablas += getRowAs(i,i);

		 tablas = tablas + endData;

		}

         xVal = '';

		 tablas = tablas + iniData;

         tablas += getRowAs(-1, valor + 1);

  	     nParms = parseInt(valor) + 1;
	     document.forms[0].nParms.value = nParms;
		 
//		 alert("nParms:" + nParms);

		 tablas = tablas + endData;

 	    resul.innerHTML="";
 	    
		despliegue = inicioTabla+tablas+finTabla;
		resul.innerHTML = resul.innerHTML + despliegue;

        xVal = document.getElementById('name_$'+nParms);
        return xVal;
}

function Eliminar()
{
	resul = document.getElementById('parametros');
	var valor=0;
	var tablas="";


	var finTabla = "</table>";
	
	var iniData  = "<tr>";
    iniData = iniData + "<td class='textos-formcolor2'>";

	var endData  = "</td></tr>";

	var despliegue="";
	valor = document.forms[0].nParms.value;
	var i = 1;
	
	for(i2=1;i2<=valor;i2++) {
         xCheck = document.getElementById('selectParm_$'+i2);
         if (xCheck.type == "radio") {
	         if (!xCheck.checked) {
				 tablas = tablas + iniData;
	             tablas += getRowAs(i2, i);
				 tablas = tablas + endData;
				 i += 1;
				}         
		
			}
    }
	nParms = parseInt(valor) - 1;
	document.forms[0].nParms.value = nParms;

 	    resul.innerHTML="";
 	    
		despliegue = inicioTabla+tablas+finTabla;
		resul.innerHTML = resul.innerHTML + despliegue;

    refrescaFromLista();
}




function getRowAs(ii, i) {

    var tablas = "";
//	var rLast = document.forms[0].nParms.value;
    var xVal = "";
    
		 tablas = tablas + "<input  size='10' ";
		 tablas = tablas + "id='selectParm_$"+i+"' ";
		 tablas = tablas + "name='selectParm_$"+i+"' ";
		 tablas = tablas + "value='$" + i + "' ";
		 tablas = tablas + "type='radio'/>";
		 tablas = tablas + "</td>";

		 tablas = tablas + "<td class='textos-formcolor2'>";
		 if (ii > 0)
             xVal = document.getElementById('name_$'+ii).value;
		 tablas = tablas + "<input  size='11' width='45px' ";
		 tablas = tablas + "id='name_$"+i+"' ";
		 tablas = tablas + "name='name_$"+i+"' ";
		 tablas = tablas + "value='" + xVal + "' ";
		 tablas = tablas + 'type="text" onchange="javascript:return checkEntries(\'edt\',this);" />';
		 tablas = tablas + "</td>";

         /* type */
         /* ---------------------------------------------------- */
		 tablas = tablas + "<td class='textos-formcolor2'>";
		 if (ii > 0)
             xVal = document.getElementById('type_$'+ii).value;
         var tt = "t";
         var nTip = "xx";
         var shw  = true;
         if (xVal == "list") {
            tt = "s";
            nTip = "aL";
         }
         if (xVal == "date" || xVal == "period") {
            shw = false;
         }
         

        var iSel="";
        var sSel="";
        var dSel="";
        var lSel="";
        var pSel="";

        if (xVal == "integer") iSel="Selected";
        if (xVal == "string")  sSel="Selected";
        if (xVal == "date")    dSel="Selected";
        if (xVal == "list")    lSel="Selected";
        if (xVal == "period")  pSel="Selected";

         xtablas = " <select ";
		 xtablas = xtablas + "   id='type_$"+i+"' ";
		 xtablas = xtablas + "   name='type_$"+i+"' ";
		 xtablas = xtablas + "   value='" + xVal + "' ";
		 xtablas = xtablas + '   onchange="javascript:return checkEntries(\'edt\',this);" >';
		 if (ii < 1)
             xtablas = xtablas + "   <option value=''               ></option>";
         xtablas = xtablas + "   <option value='date'    "+dSel+" >date</option>";
         xtablas = xtablas + "   <option value='integer' "+iSel+" >integer</option>";
         xtablas = xtablas + "   <option value='list'    "+lSel+" >list</option>";
         xtablas = xtablas + "   <option value='period'  "+pSel+" >period</option>";
         xtablas = xtablas + "   <option value='string'  "+sSel+" >string</option>";
         xtablas = xtablas + " </select> "; 
  		 xtablas = xtablas + " </td> ";
 
         tablas += xtablas;
//         alert (xtablas);
               


         /* type_spec */
         /* ---------------------------------------------------- */
		 xtablas = "<td class='textos-formcolor2' width='85px'>";
         xtablas = xtablas + "<div id='d$" + i + "'>";

		 if (ii > 0)
             xVal = document.getElementById('type_spec' + tt + '_$'+ii).value;
         
         xtablas += getTypeSpecObject(i, xVal, nTip, shw);

         xtablas = xtablas + "</div>";
		 xtablas = xtablas + "</td>";

         tablas += xtablas;


         /* default_value */
         /* ---------------------------------------------------- */
		 xtablas = "<td class='textos-formcolor2'>";

         xtablas = xtablas + "<div id='dd$" + i + "'>";
//alert("con nTip:" + nTip);

         if (nTip != "xx") nTip = xVal;
		 if (ii > 0)
             xVal = document.getElementById('default_value' + tt + '_$'+ii).value;
         

         xtablas += getDefaultObject(i, xVal, nTip);

         xtablas = xtablas + "</div>";
		 xtablas = xtablas + "</td>";

         tablas += xtablas;

/*
		 if (ii > 0)
             xVal = document.getElementById('default_value_$'+ii).value;
		 tablas = tablas + "<input  size='12' ";
		 tablas = tablas + "id='default_value_$"+i+"' ";
		 tablas = tablas + "name='default_value_$"+i+"' ";
		 tablas = tablas + "value='" + xVal + "' ";
		 tablas = tablas + 'type="text"  onchange="javascript:return checkEntries(\'edt\',this);" />';
		 tablas = tablas + "</td>";
*/


		 tablas = tablas + "<td class='textos-formcolor2'>";
		 if (ii > 0)
             xVal = document.getElementById('comment_$'+ii).value;
		 tablas = tablas + "<input   size='47' ";
		 tablas = tablas + "id='comment_$"+i+"' ";
		 tablas = tablas + "name='comment_$"+i+"' ";
		 tablas = tablas + "value='" + xVal + "' ";
		 tablas = tablas + 'type="text"  onchange="javascript:return checkEntries(\'edt\',this);" />';
		 tablas = tablas + "</td>";


   return tablas;

}



function startForm() {

   setTimeout("document.forms[0].nomScript.focus();",400); 
     
}




</script>


<body>
	<%@ include file="header.jspf" %>
	<CENTER>
		<logic:present name="username" scope="session">
		<table width="767" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%">
						<%@ include file="contextUser.jspf" %>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%">
						<tr>
							<td class="contextUser" width="5%">
								Contexto:
							</td>
							<td class="contextNav" align="left">
							<a href="<%= cPath %>/LoadConfUser.do" >Sistemas</a> 
							<%@ include file="p.jspf" %>
							<a href="<%= cPath %>/LoadScripts.do?sysID=${sistema.identificador}" >Scripts</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<%@ include file="barTop.jspf" %>
				</td>
				
			</tr>
		</table>  

		<form name="actualizaScriptForm" method="post" action="ActualizaScript.do">
		<input type="hidden" name="idAutor"  value="${script.idAutor}"/>
		<input type="hidden" name="idsistema"  value="${sistema.identificador}"/>
		<input type="hidden" name="idusuario"  value="${idusuario}"/>
		<input type="hidden" name="idscript"  value="${idscript}"/>
		<input type="hidden" name="nParms"  value="${nParms}"/>
		<input type="hidden" name="actividad"  value="${actividad}"/>
		<input type="hidden" name="stat"  value="${stat}"/>
		<input type="hidden" name="hoy"  value="${hoy}"/>
		<input type="hidden" name="act"  value="${act}"/>
		<input type="hidden" name="sysID"  value="${sistema.identificador}"/>
		
			<table width="767" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="100%" align="center">
						<%@ include file="contextSystem.jspf" %>
					</td>
				</tr>
			</table>
					
		<table width="767" border="0" cellpadding="0" cellspacing="0">
		
		  <tr>
		    <td rowspan="32">
		    	<table width="767" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td>
							<table align="center">
								<tr>
									

									<td width="100%" colspan=4>
										<font color="red" size="1"><html:errors/></font>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<logic:present name="stat" scope="request">
					<br/>
						<table class="tdcuerpotabla" width="400" align="center">
							<tr>
								<td align="center">
									${stat} 
								</td>
							</tr>
						</table>
					<br/>
				</logic:present>
				<table width="767" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="barra_tablas">
							<div id="xact" align="center">
				     			${actividad}
						     </div>
						</td>
					</tr>
		  		<tr>
		    <td class="barra_tablas">Datos Generales</td>
		  </tr>
		</table>
		<table width="767" border="1" cellpadding="0" cellspacing="0" bordercolor="#B4D6D8">
		  <tr>
		    <td>
		      <table width="100%" border="0" cellpadding="0" cellspacing="0">
		        <tr class="textos-formularios1" >
		          <td class="barratablas">Fecha de Creación</td>
		          <td class="textos-formularios1" colspan=4 >
		            <!-- script>document.write(putCntl("text", "fchCreacion", "${script.fchCreacion}"));</script-->
					<script>
		              var vOut = '<input type="text" name="fchCreacion" value="${script.fchCreacion}" ';
		              vOut = vOut + ' readonly ';
		              vOut = vOut + '/>';
		              document.write(vOut);
		          </script>
		            
		            <!-- input type="text" name="fchCreacion" value="${script.fchCreacion}"/-->
		        </td>
		      </tr>
		      <tr>
        <td class="barratablas">Nombre (*)</td>
		        <td class="textos-formularios1">
		        <script>
		          var vOut = '<input type="text" name="nomScript" value="${script.nomScript}" size=19 maxlength=19 ';
          if (document.forms[0].actividad.value == 'editando...') {
             vOut = vOut + ' onchange="javascript:return avisoDuplicaScript(this);" ' ;
		          } else {
              vOut = vOut + ' onchange="javascript:return validFileName(this, \'.pss\');" ' ;
		          }
		          vOut = vOut + '/>';
		          document.write(vOut);
          

		          </script>
          			
		        </td>
		          <td class="barratablas">Permite Ejecuciones Simultáneas</td>
		          <td class="textos-formularios1">
		             <input TYPE=checkbox name ="multiplesEjecuciones" ${multiExec} onclick="showWarning()"/>
		          </td>
		      </tr>
		      <tr>
        <td class="barratablas">Objetivo (*)</td>
		          <td class="textos-formularios1" colspan=4><textarea name="objetivo" rows=5 cols=78 wrap=off >${script.objetivo}</textarea></td>
		        <!--td class="textos-formularios1"><input type="text" name="textfield4"/></td>
		        <td colspan="2" class="textos-formularios1">&nbsp;</td-->
		      </tr>
		
		    </table></td>
		  </tr>
		</table>
		<br>
		<table width="767" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td class="barra_tablas">Uso</td>
		  </tr>
		</table>
		<table width="767" border="1" cellpadding="0" cellspacing="0">
		  <tr>
		    <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
		      <tr>
		        <td class="barratablas">Sintaxis</td>
		        <td colspan="3" class="textos-formularios1"><input name="sintaxis" type="text" readonly value="${script.sintaxis}" size="110" readonly/></td>
		      </tr>
		      <tr>
		        <td class="barratablas">Ejemplo</td>
		        <td colspan="3" class="textos-formularios1"><input name="ejemplo" type="text" value="${script.ejemplo}" size="110"/></td>
		      </tr>
		
		
		    </table></td>
		  </tr>
		</table>
		<br>
		<table width="767" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td class="barra_tablas">Descripci&oacute;n de par&aacute;metros</td>
		  </tr>
		</table>
<table width="755" border="0" cellpadding="0" cellspacing="0" bordercolor="#B4D6D8">
		  <tr>
		    <td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td width="85" class="barratablas">Parámetros</td>
		    <!--td width="43" class="barra_titulos"><input name="cantidad" type="text" size="5" /></td-->
    <td width="380" class="barra_titulos"><!--label-->
			<script>
		        if (document.getElementById("act").value != "ver") {
		            document.write('<input name="buttonA" type="button" class="btn2" onClick="Agrega();return false;" value="Agregar"/>');
		            document.write('<input name="buttonE" type="button" class="btn2" onClick="Eliminar();return false;" value="Eliminar"/>');
		        } 
		     </script> 
		    <!--/label--></td>
		  </tr>
		</table>
		<div id="parametros">
		
		   <script>document.write (inicioTabla);</script>
		
		<logic:present name="listaParametros" scope="request" >
		              <logic:iterate id="parametro" name="listaParametros" >
		              <tr>
		                <td width="10" class="textos-formcolor2">
		                   <input type="radio" 
		                       id="selectParm_<bean:write name='parametro' property='num' />" 
		                       name="selectParm_<bean:write name='parametro' property='num' />" 
		                       value="<bean:write name='parametro' property='num' />"/>
		                </td>
		                <td class="textos-formcolor2" width="25">
                     <input size="11"  width="25"  
		                      id="name_<bean:write name='parametro' property='num' />"
		                      name="name_<bean:write name='parametro' property='num' />"
		                      type="text" value='<bean:write name="parametro" property="name" />'
                      onchange="javascript:return checkEntries('edt',this);"/>
		                </td>
		                
		                
		                     <script>
		                        var p = "<bean:write name="parametro" property="type" />";                     
		                        var iSel="";
		                        var sSel="";
		                        var dSel="";
		                        var lSel="";
		                        var pSel="";
		
		                        if (p == "integer") iSel="Selected";
		                        if (p == "string")  sSel="Selected";
		                        if (p == "date")    dSel="Selected";
		                        if (p == "list")    lSel="Selected";
		                        if (p == "period")  pSel="Selected";
		                     </script>
		                
		                <td width="70" class="textos-formcolor2">
		                     <select 
		                      id="type_<bean:write name='parametro' property='num' />" 
		                      name="type_<bean:write name='parametro' property='num' />" 
		                      value="<bean:write name='parametro' property='type' />" 
                      onchange="javascript:return checkEntries('edt',this);">
		                      <script>
		                         document.write("<option value='date'    " + dSel + " >date</option>");
		                         document.write("<option value='integer' " + iSel + " >integer</option>");
		                         document.write("<option value='list'    " + lSel + " >list</option>");
		                         document.write("<option value='period'  " + pSel + " >period</option>");
		                         document.write("<option value='string'  " + sSel + " >string</option>");
		                      </script>
		                     </select>
		                     
		                </td>
		
		
		
		                <td width="70" class="textos-formcolor2">
		                    <div id="d<bean:write name='parametro' property='num' />">
		                     <input size="12" 
		                      id="type_spect_<bean:write name='parametro' property='num' />" 
		                      name="type_spect_<bean:write name='parametro' property='num' />" 
		                      type="text" 
		                      value='<bean:write name="parametro" property="type_spec" />'
                      onchange="javascript:return checkEntries('edt',this);" />
		                    </div>
		                </td>
		                
		                
                <td width="100" class="textos-formcolor2"> 
                    <div id="dd<bean:write name='parametro' property='num' />">
                     <input size="15" 
                      id="default_valuet_<bean:write name='parametro' property='num' />" 
                      name="default_valuet_<bean:write name='parametro' property='num' />" 
		                      type="text" value='<bean:write name="parametro" property="default_value" />'
                      onchange="javascript:return checkEntries('edt',this);"/>
                    </div>
		                </td>
                
                <td width="80" class="textos-formcolor2">
		                     <input size="31" 
		                      id="comment_<bean:write name='parametro' property='num' />" 
		                      name="comment_<bean:write name='parametro' property='num' />" 
		                      type="text" value='<bean:write name="parametro" property="comment" />' 
		                       />
		                </td>
		              </tr>
		                <script>
		//                    checkEntries("shw",document.getElementById("name_<bean:write name='parametro' property='num' />"));
		                    checkEntries("shw",document.getElementById("type_<bean:write name='parametro' property='num' />"));
		//                    checkEntries("shw",document.getElementById("type_spect_<bean:write name='parametro' property='num' />"));
		//                    checkEntries("shw",document.getElementById("default_value_<bean:write name='parametro' property='num' />"));
		//                    checkEntries("shw",document.getElementById("comment_<bean:write name='parametro' property='num' />"));
		                </script>
		              </logic:iterate>
		</logic:present>              
		</table>
		</div>  
		
		<br>
		<table width="767" border="0" cellpadding="0" cellspacing="0">
		  <tr>
    <td class="barratablas">Cuerpo (*)</td>
		  </tr>
		</table>
<table width="760" border="1" cellpadding="0" cellspacing="0" >
		  <tr>
		    <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
		      <tr>
		        <td class="textos-formularios1">
		          <textarea name="textoScript" cols="92" rows="15" wrap="off" style="font-family: monospace;">${script.textoScript}</textarea></td>
		      </tr>
		    </table></td>
		  </tr>
		</table>
		<table width="767" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="9"></td>
		  </tr>
		  <tr>
		    <td align="right">
				<table align="center">
					<tr>
						<td>&nbsp;</td>
						<td width="88">	
							<input name="Submit" type="button" class="btn2" value="Grabar" onclick="javascript:sm();"/>
						</td>
						<td>&nbsp;</td>
						<td width="88">
							<input name="Cancelar" type="button" class="btn2" value="Cancelar" onClick="javascript:cancelar();"/>
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
		    </td>
		  </tr>
		  <tr>
		    <td height="12"></td>
		  </tr>
		</table>
			</td>
		  </tr>
		</table>
		<script>
		   if (document.getElementById("act").value == "ver") {
		   	      document.getElementById("xact").innerHTML = "";  
		   	      document.forms[0].nomScript.disabled = true;
		   	      document.forms[0].fchCreacion.disabled = true;
		   	      document.forms[0].multiplesEjecuciones.disabled = true;
		   	      document.forms[0].objetivo.disabled = true;
		   	      document.forms[0].sintaxis.disabled = true;
		   	      document.forms[0].ejemplo.disabled = true;
		   	      document.forms[0].textoScript.disabled = true;
		   	      document.forms[0].Submit.disabled = true;
		   	      refrescaFromLista();
		   } else {
		      startForm();
		   }
		</script>
		</form>
		</logic:present>
		<logic:notPresent name="username" scope="session">
			<% response.sendRedirect(request.getContextPath()+"/index.jsp"); %> 
		</logic:notPresent>
	</CENTER>
<SCRIPT>


	/*  SUbmit */
	function sm() {
		valor = document.forms[0].nParms.value;

		if(document.forms[0].nomScript.value == '' || document.forms[0].nomScript.value.length == 0 ){
			alert("Favor complete el nombre del script");
			document.forms[0].nomScript.focus();
		} else if (document.forms[0].objetivo.value == '' || document.forms[0].objetivo.value.length == 0 ){
			alert("Favor especifique un objetivo para su script");
			document.forms[0].objetivo.focus();
		} else if (document.forms[0].textoScript.value == '' || document.forms[0].textoScript.value.length == 0 ){
			alert("Favor especifique texto válido, en el campo Cuerpo de su script ");
			document.forms[0].textoScript.focus();
		} else {
		
			document.forms[0].submit();
		
		}
	    
	}


	function cancelar() {
		document.location.href='<%=cPath%>/LoadScripts.do?sysID=${sistema.identificador}#${idscript}';
	}
	
	function showWarning(){
		if(document.forms[0].multiplesEjecuciones.checked){
			alert(   	"Estimado Usuario:\n"
					+	"No se recomienda que un script permita múltiples ejecuciones,\n"
			       	+ 	"ya que tal condición, en algunos casos, podría generar resultados inesperados.\n\n"
			       	+ 	"Al marcar esta opción, Ud. está aceptándo que esto puede ocurrir."
			     );
		} 
	}
	
</SCRIPT>
</body>
</html:html>
