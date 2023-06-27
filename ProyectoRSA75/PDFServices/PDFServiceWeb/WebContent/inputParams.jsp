<%--
******************************************************************************************************
* Descripcin
* author  @Cristian.Cern
* version 1.0
* Fecha creacin    Ago-2008
* Historia de cambios
   versin   fecha      autor   cambios
   =======   ========== ======= =================================
   1.0                          versin inicial
******************************************************************************************************
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<link href="css/Interna_Araucana.css" rel="stylesheet" type="text/css" />


<title>La Araucana C.C.A.F. - Ingreso de parametros (PDFServiceWeb)</title>
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
      res += '<td class="textos-formularios1">';
      res += '  <div name="ctlView_' + n + '" id="ctlView_' + n + '">';
      res += '    <input ';
      res += '           type="text" ';
      res += '           name="' + n + '" ';
      res += '           id="' + n + '" ';
      res += '           value="' + v + '" ';
      res += '    />';
      res += '  </div>';
      res += '</td>';
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
       //alert("[Ctrl-Display] \n\nEl control ..." + n + " ya existe");
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
          msgERR  += "\n* Como valor nmerico, debe ingresar a lo menos un dgito.";
      
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
              msgERR  += "\n* No puede contener caracteres alfanumricos.";
              break;          
              }
      } 
      


//      if (!vOk) 
//          alert(v + "... NO CUMPLE");	
//      else
//          alert(v + "... CUMPLE");	

      return vOk;

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
              msgERR  += "\n* Debe ser una fecha vlida que cumpla con el formato YYYYMMDD.";
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





function checkValue(ope, v) {

	var posP = v.name.indexOf("$") + 1;
	var nam  = v.name.substring(0,posP - 2); 
	var num  = v.name.substring(posP);

/*    
    var eDynTS; 
    var vDynTS;
    if (valt == "list") {
       eDynTS = document.getElementById(v);
    } else {
       eDynTS = document.getElementById(v);
    }
*/

//    alert ("campo:" + v.name );


    /* ACTUAL (onChange)  */
    var val  = v.value;

    
    var valt  = document.getElementById("type_$"+num).value;
    var valts = document.getElementById("type_spec_$"+num).value;
    var valde = document.getElementById("default_value_$"+num).value;
    
//    alert ("[campo:" + nam + "]\n[num=" + num + "]\n[val de Valor(default)="+valde+"]\n[t="+valt+"]\n[ts="+valts+"]");


     var xPlantilla = valde;
     var isOk = true;
       
      if (valt == "integer") {
         if (ope == 'shw') { v = cambiaDefaultAsValue(num, valts, xPlantilla, "xx"); }
         if (ope == 'edt') { isOk = validNum(val, valts); }
      }

      if (valt == "string") {
	     if (ope == 'shw') { v = cambiaDefaultAsValue(num, valts, evalString(xPlantilla), "xx");  }
	     if (ope == 'edt') { isOk = validStr(val, valts);  }
      }
      //alert("v:" + valts + ":" + xPlantilla);
      if (valt == "list") {
	     if (ope == 'shw') { v = cambiaDefaultAsValue(num, valts, xPlantilla, "aL"); }
	  } 

      if (valt == "date") {
         if (ope == 'shw') { v.value = addDays(xPlantilla); } //evalDate(xPlantilla);  }
         if (ope == 'edt') { isOk = validDate(val, valts);  }

      }
      if (valt == "period") {
	      if (ope == 'shw') {  v.value = evalPeriod(xPlantilla);    }
          if (ope == 'edt') {  isOk = validPer(val, valts);  }
	      
      }

      if (!isOk) {
//         v.focus();
         alert("ERROR !\nEl valor ingresado debe cumplir con los siguientes requisitos:\n" + msgERR);
         msgERR  = "";
//         v.value = document.getElementById("param_$"+num).value;
      } else {
		 document.getElementById("param_$"+num).value = v.value;      
      }          
      return isOk;

	
}



function cambiaDefaultAsValue(xDiv, ts, val, nTip) {

	resul = document.getElementById("d$" + xDiv);
	
//	alert ("trabajaremos con " + resul.innerHTML);
	var tablas="";

    resul.innerHTML="";

	
	var sepa;
	var sepa2;
	var iSel = "";
     if (nTip == "aL") {
         e = "s";
         tablas = tablas + '<select style="width:235px;" '
         tablas = tablas + ' id="values_$'+xDiv+'"';
         tablas = tablas + ' name="values_$'+xDiv+'"'; 
         tablas = tablas + ' value="'+val+'" '; 
         tablas = tablas + ' onChange="javascript:return checkValue(\'edt\',this);">';
         
         var iSel2 = "";
         var pusoSel = "no";
         for (var i=0;i<lists.length;i++) {
              sepa = lists[i].split("?");
              if (sepa[0] == ts) {
                 sepa2 = sepa[1].split("(");
                 tablas = tablas + "<option value='" + sepa2[0] + "' ";
                 if (val == "-first-" && pusoSel == "no") {  // == "<selected>") {
                      pusoSel = "si";
                      iSel = "selected";
                 }
                 if (val == "-selected-" && sepa2[1] == "*)") {  // == "<selected>") {
                      pusoSel = "si";
                      iSel = "selected";
                 }
                 if (sepa2[0] == val) {  // == "<selected>") {
//                    if (sepa2[1] == "*)") {
                       iSel = "selected";
//                    }
                 }
                 
                 if (sepa2[1] == "*)") {
                    iSel2 = "(*)";
                 }
                 
                  tablas += iSel + " >" + sepa2[0] + iSel2 + "</option>";
                  iSel2 = "";
                  iSel = "";
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
         tablas = tablas + ' onChange="javascript:return checkValue(\'edt\',this);" ';
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



function addDays(num) {
  var nDays, mDia, mMes;
  if (num == "current") { nDays = 0; }
  if (num == "next") { nDays = 1; }
  if (num == "previous") { nDays = -1; }
  
  if (isNaN(nDays)) {
     nDays = parseInt(num);
  }
  
  hoy = new Date();
  hoy.setTime(hoy.getTime() + (nDays * 24)*60*60*1000); 
  
  mMes = hoy.getMonth() + 1;
  if (mMes < 10) { mMes = "0" + mMes; }

  mDia = hoy.getDate();
  if (mDia < 10) { mDia = "0" + mDia; }
  
  fecha = "" + hoy.getFullYear() + "" + mMes + "" + mDia;
  return fecha; //alert(nDays + "..." + fecha); 
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
	//alert(day);
   if (v == "next") {
   
       cntInc = 1; 
   } else if (v == "previous") {
       cntInc = -1;
       day = day -1;
       xPlant = year4 + "" + month + "" + day;
       return xPlant;
   } else if (v == "current") {
       xPlant = year4 + "" + month + "" + day;
       return xPlant ;

       
   } else  {
	
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
       
  
 
           
   //alert("inc=" + cntInc);

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

      achange = parseInt(mchangeN / 12);
      zy4     = year4 + achange;
		
   }


   if (zmn < 10) {
      zmn = "0" + zmn;   
   }
   if (zdy < 10) {
      zdy = "0" + zdy;   
   }
     
   xPlant = zy4 + "" + zmn + "" +  zdy;
   
   //alert("Test Default:\n  [year=" + zy4 + "]\n[zmn=" + zmn + "]\n[d=" + zdy + "]");
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
   
  // alert("Test Default:\n  [year=" + zy4 + "]\n[zmn=" + zmn + "]\n[d=" + zdy + "]");
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
   } else {
   
      vInc  = vInc * -1;
      vInc = vInc - ((aAct - aCal) * 12)
      if ((m - vInc) < 1) {
         mchangeI = 12 + (m - vInc);
      } else {
         mchangeI = m - vInc;
      }

      m = mchangeI;

//      achange = parseInt(mchangeN / 12);
//      zy4     = year4 + achange;
   }


   if (m < 10) {
      m = "0" + m;   
   }

   return "" + m;
}




var u = document.location.href;






</script>


<body >

	<%@ include file="header.jspf" %>
	
	<center>
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
							<%@ include file="p.jspf" %> 
							<a href="InputParameters.do?idscript=${idscript}">Ejecutar</a>
							<%@ include file="p.jspf" %>
							<font style="font-size: 10px" >${script.nomScript}</font>
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

		<form name="actualizaScriptForm" method="post" action="RunScript.do">
		<input type="hidden" name="idAutor"  value="${script.idAutor}"/>
		<input type="hidden" name="idsistema"  value="${idsistema}"/>
		<input type="hidden" name="idusuario"  value="${idusuario}"/>
		<input type="hidden" name="idscript"  value="${idscript}"/>
		<input type="hidden" name="nParms"  value="${nParms}"/>
		<input type="hidden" name="actividad"  value="${actividad}"/>
		<input type="hidden" name="sysID"  value="${sistema.identificador}"/>
		
		
		<table width="767" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		
		  </tr>
		 <tr>
				<td>
					&nbsp;
				</td>
			</tr>				
		
			<tr>
				<td width="767">
					<%@ include file="contextSystem.jspf" %>
				</td>
			</tr>
		  <tr>
			 <td>
			 	&nbsp;
			 </td>
		</tr>
		  <tr>
		    <td rowspan="32">
		    <logic:present name="listaParametros" scope="request" >
				<table width="767" border="0" cellpadding="0" cellspacing="0">
		
		 		 	<tr>
						
		
		    			<td class="barra_tablas">Datos Generales</td>
		  			</tr>
				</table>
				<table width="767" border="1" cellpadding="0" cellspacing="0" bordercolor="#B4D6D8">
				  <tr>
				    <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
				      <tr>
				        <td class="barratablas">Sistema</td>
				        <td class="textos-formularios1" colspan=4>
				        ${script.idSistema}</td>
				      </tr>
				      <tr>
				        <td class="barratablas">Autor</td>
				        <td class="textos-formularios1">
				           ${script.autor}
				        </td>
         <td class="barratablas">Fecha creaci&oacute;n</td>
				        <td class="textos-formularios2">
				        ${script.fchCreacion}
				        </td>
				      </tr>
				      <tr>
				        <td class="barratablas">Nombre</td>
				        <td class="textos-formularios2">
				            ${script.nomScript}
				        </td>
          <td class="barratablas">Permite ejecuciones simult&aacute;neas</td>
				          <td class="textos-formularios1">
				             ${multiExec}
				          </td>
				      </tr>
				      <tr>
				        <td class="barratablas">Objetivo</td>
				          <td class="textos-formularios1" colspan=4 height=80 width=300>
				          <!-- textarea name="objetivo" disabled rows=5 cols=80-->
				          ${script.objetivo}
				          <!-- /textarea-->
				          </td>
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
				<table width="767" border="1" cellpadding="0" cellspacing="0" bordercolor="#B4D6D8">
				  <tr>
				    <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
				      <tr>
				        <td class="barratablas">Sintaxis</td>
				        <td colspan="3" class="textos-formularios1">
				        ${script.sintaxis}
				        </td>
				      </tr>
				      <tr>
				        <td class="barratablas">Ejemplo</td>
				        <td colspan="3" class="textos-formularios1">
				        ${script.ejemplo}
				        </td>
				      </tr>
				
				
				    </table></td>
				  </tr>
				</table>
				<br>
				<table width="767" border="0" cellpadding="0" cellspacing="0">
				  <tr>
    <td class="barra_tablas">Parmetros</td>
				  </tr>
				</table>
				<table width="760" border="0" cellpadding="0" cellspacing="0" bordercolor="#B4D6D8">
				  <tr>
				    <td>
				
						<div id="parametros">
				
				<table width="767" border="1" cellpadding="0" cellspacing="0">
				  <tr>
				    <td class="textos-formularios1">Nombre</td>
				    <td class="textos-formularios1">Valor</td>
				    <td class="textos-formularios1">Comentario</td>
				  </tr>

            
            
		              <c:forEach var="parametro" items="${listaParametros}">
		              	<tr>
			              	<td class="barratablas">
			                      ${parametro.name}
			                      <input 
			                      id="default_value_${parametro.num}"   
			                      name="default_value_${parametro.num}"  
			                      type="hidden"  
			                      value="${parametro.default_value}" />
			
			                     <input size="15"  
			                      id="name_${parametro.num}"
			                      name="name_${parametro.num}"
			                      type="hidden" value="${parametro.name}"/>
			
			                     <input 
			                      id="type_${parametro.num}"
			                      name="type_${parametro.num}"
			                      type="hidden" value="${parametro.type}"/>
			
			                     <input 
				                      id="type_spec_${parametro.num}"
			                      name="type_spec_${parametro.num}"
			                      type="hidden" value="${parametro.type_spec}" />
			
			                     <input 
			                      id="param_${parametro.num}"
			                      name="param_${parametro.num}"
			                      type="hidden" value="${parametro.default_value}" />
			              
			                
			                </td>
		
			                <td width="140" class="textos-formularios1">
                   				 <div id="d${parametro.num}">
				                     <input size="30" 
				                      id="valuet_${parametro.num}" 
				                      name="valuet_${parametro.num}" 
				                      type="text" 
				                      value="${parametro.default_value}" 
				                      onChange="javascript:return checkValue('edt',this);" />
			                    </div>
			                </td>
			                <td class="barratablas">
			                	${parametro.comment}
			                	
			                </td>
		              	</tr>
              <tr>
              	
			                <script>
			//                    checkValue("shw",document.getElementById("name_<bean:write name='parametro' property='num' />"));
			//                    checkValue("shw",document.getElementById("type_<bean:write name='parametro' property='num' />"));
			//                    checkValue("shw",document.getElementById("type_spect_<bean:write name='parametro' property='num' />"));
                    checkValue("shw",document.getElementById("valuet_${parametro.num}"));
			//                    checkValue("shw",document.getElementById("comment_<bean:write name='parametro' property='num' />"));
			                </script>
              
              
		              </c:forEach>
				</table>
		    </logic:present>          
		</div>  
		  
			</td>
		  </tr>
		</table>
		<br>
		<table width="767" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="9"></td>
		  </tr>
		  <tr>
		
		    <td align="center">
		    	<input name="Submit" type="button" class="btn2" value="Ejecutar" onClick="javascript:sm();"/>
		    	&nbsp; 
				<input name="Cancelar" type="button" class="btn2" value="Cancelar" onClick="javascript:cancelar();"/>
			</td>
		  </tr>
		  <tr>
		    <td height="12"></td>
		  </tr>
		</table>
		
		
			</td>
		  </tr>
		</table>
		</form>
		
		</logic:present>
		<logic:notPresent name="username" scope="session">
			<% response.sendRedirect(request.getContextPath()+"/index.jsp"); %> 
		</logic:notPresent>
	</center>
	
<SCRIPT>


/*  SUbmit */
function sm() {
	if(confirmation())
	    document.forms[0].submit();
    return false;
}
function cancelar() {
	document.location.href='<%=cPath%>/LoadScripts.do?sysID=${sistema.identificador}#${idscript}';

}

function confirmation() {
		var answer = confirm('Est seguro que desea ejecutar el script: \n ${idscript} ?' );
		return answer;
	}
	

</SCRIPT>
</body>
</html>
