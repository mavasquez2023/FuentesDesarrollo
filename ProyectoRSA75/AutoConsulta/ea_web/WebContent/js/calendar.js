//*****************************************
// Funciones para generar un calendario
var dayA = new Array();
var monthA = new Array();
var yearA = new Array();
var targetfield;
var datefield = new Date();
monthA[0]="Enero";
monthA[1]="Febrero";
monthA[2]="Marzo";
monthA[3]="Abril";
monthA[4]="Mayo";
monthA[5]="Junio";
monthA[6]="Julio";
monthA[7]="Agosto"; 
monthA[8]="Septiembre"; 
monthA[9]="Octubre";
monthA[10]="Noviembre"; 
monthA[11]="Diciembre";

for(var i=1;i<=31;i++) { 
     dayA[i]=i; 
} 

for(var i=-40;i<40;i++) { 
     yearA[i]=gy(i); 
}
 
function gm(num) { 
//     var mydate = new Date(); 
     var mydate = datefield; 
     mydate.setDate(1); 
     mydate.setMonth(num-1); 
     var datestr = "" + mydate; 
     return datestr.substring(4,7); 
} 

function gy(num) { 
//     var mydate = new Date(); 
     var mydate = datefield; 
     return (eval(mydate.getYear()) - 4 + num); 
} 

function ud(mon) { 
     var i = mon.selectedIndex; 

     if(mon.options[i].value == "2") { 
          document.forms[0].day.options[30] = null; 
          document.forms[0].day.options[29] = null; 
          var j = document.forms[0].year.selectedIndex; 
          var year = eval(document.forms[0].year.options[j].value); 
          if ( ((year%400)==0) || (((year%100)!=0) && ((year%4)==0)) ) { 
	if (document.forms[0].day.options[28] == null) { 
	     document.forms[0].day.options[28] = new Option("29"); 
        	     document.forms[0].day.options[28].value = "29"; 
      	} 
          } else { 
      	document.forms[0].day.options[28] = null; 
          } 
     } 

     if(mon.options[i].value == "1" || 
          mon.options[i].value == "3" || 
          mon.options[i].value == "5" || 
          mon.options[i].value == "7" || 
          mon.options[i].value == "8" || 
          mon.options[i].value == "10" || 
          mon.options[i].value == "12") { 
	if (document.forms[0].day.options[28] == null) { 
   	     document.forms[0].day.options[28] = new Option("29"); 
      	     document.forms[0].day.options[28].value = "29"; 
    	} 
    	if (document.forms[0].day.options[29] == null) { 
      	     document.forms[0].day.options[29] = new Option("30"); 
      	     document.forms[0].day.options[29].value = "30"; 
    	} 
    	if (document.forms[0].day.options[30] == null) { 
      	     document.forms[0].day.options[30] = new Option("31"); 
      	     document.forms[0].day.options[30].value = "31"; 
    	} 
     } 

     if(mon.options[i].value == "4" || 
          mon.options[i].value == "6" || 
          mon.options[i].value == "9" || 
          mon.options[i].value == "11") { 
    	if (document.forms[0].day.options[28] == null) { 
      	     document.forms[0].day.options[28] = new Option("29"); 
      	     document.forms[0].day.options[28].value = "29"; 
    	} 
    	if (document.forms[0].day.options[29] == null) { 
      	     document.forms[0].day.options[29] = new Option("30"); 
      	     document.forms[0].day.options[29].value = "30"; 
    	} 
    	     document.forms[0].day.options[30] = null; 
     } 

     if (document.forms[0].day.selectedIndex == -1) 
              document.forms[0].day.selectedIndex = 0; 
} 

function showdate() { 
	var i = document.forms[0].month.selectedIndex; 
  	var j = document.forms[0].day.selectedIndex; 
  	var k = document.forms[0].year.selectedIndex; 
  	alert(document.forms[0].day.options[j].value + "/" + 
        	document.forms[0].month.options[i].value + "/" + 
        	document.forms[0].year.options[k].value) 
} 

function opencalendar(formIndex, temptargetfield) {
	var field= eval("document.forms[" + formIndex + "]." + temptargetfield);
	targetfield = "opener.document.forms[" + formIndex + "]." + temptargetfield + ".value";
	var mydate;

	if(field.value == "" || field.value.length < 10 || field.value.length >10) {
//	mydate = new Date(); 
	mydate = datefield; 

	} else {
		var tempday = field.value.charAt(0).toString() + field.value.charAt(1).toString();
		var tempmonth = (parseInt((parsemonth(field.value.charAt(3).toString(), field.value.charAt(4).toString())))-1).toString();
		var tempyear = (parseInt(field.value.charAt(6).toString() + field.value.charAt(7).toString() + field.value.charAt(8).toString() + field.value.charAt(9).toString())).toString();
		mydate = new Date(tempyear, tempmonth, tempday ); 
	}

	for(var i=0;i<=monthA.length;i++) { 
		if (mydate.getMonth() == i) {
			month = i+1;
		}
	} 

//	year = mydate.getYear();			//  <--- Método obsoleto...
	year = mydate.getFullYear();
	//yearA[4].toString();
	var t = ((screen.height-143)/2);
	var l = ((screen.width-220)/2) ;
	calwin=open("","calwin","scrollbars=no,resizable=no,height=143,width=220,top="+t+", left="+l);
	if (calwin.opener==null) calwin.opener=self;
		calccal(calwin,month,year,targetfield); 
	//calwin=open("","calwin","scrollbars=no,resizable=no,height=270,width=210");
	//if (calwin.opener==null) calwin.opener=self;
	//	calccal(calwin,month,year,targetfield); 
} 

function parsemonth(tmpm1, tmpm2) {
	var m1;
	var m2;
	var m3;
	if (tmpm1 == "0") {
		m1 = "";
	} else {
		m1 = "1";
	}
	m2 = tmpm2;
	m3 = m1.toString() + m2.toString();
	return m3;
}

function calccal(targetwin,month,year,targetfield) { 
	var monthname = new Array(12); 
  	monthname[0] = "Enero"; 
  	monthname[1] = "Febrero"; 
  	monthname[2] = "Marzo"; 
  	monthname[3] = "Abril"; 
  	monthname[4] = "Mayo"; 
  	monthname[5] = "Junio"; 
  	monthname[6] = "Julio"; 
  	monthname[7] = "Agosto"; 
  	monthname[8] = "Septiembre"; 
  	monthname[9] = "Octubre"; 
  	monthname[10] = "Noviembre"; 
  	monthname[11] = "Diciembre"; 

 	var endday = calclastday(eval(month),eval(year)); 

  	mystr = month + "/01/" + year; 
  	mydate = new Date(mystr); 
  	firstday = mydate.getDay(); 
  	firstday = (firstday + 6) % 7;		// Con esto se corren los dias en 1
  	var cnt = 0; 

  	var day = new Array(6); 
  	for (var i=0; i<6; i++) 
    		day[i] = new Array(7); 

  	for (var r=0; r<6; r++) { 
    		for (var c=0; c<7; c++) { 
      		if ((cnt==0) && (c!=firstday)) 
        			continue; 
      		cnt++; 
      		day[r][c] = cnt; 
      		if (cnt==endday) 
        		break; 
    		} 
    		if (cnt==endday) 
      	break; 
  	} 
	//fechahoy = new Date(); 
	fechahoy = datefield; 
	dd = fechahoy.getDate();
	mm = fechahoy.getMonth()+1;
	if ( mm < 10 ) mm = "0"+mm;
	aa = fechahoy.getYear();
	fechoystr = dd +"/"+ mm +"/"+ aa;
	targetwin.document.open();
	targetwin.document.writeln("<HTML>");
//	targetwin.document.writeln("<TITLE> Hoy es "+ fechoystr +" </TITLE>");   // Titulo en la ventana

	targetwin.document.writeln("<HEAD>");
	targetwin.document.writeln("<TITLE>Calendario</TITLE>");
	targetwin.document.writeln("<STYLE>");
	targetwin.document.writeln("	BODY, TD, A, FONT { font-family: Verdana, Helvetica, sans-serif; font-size: 11px; color: #000000; }");
	targetwin.document.writeln("	.tablaceleste {background-color: #EEEEEE; border: 1px solid #B7D0E6; padding: 3px 3px 3px 3px; width: 100%; }");
	targetwin.document.writeln("	.linkHead { color: #FFFFFF; font-weight: bold; text-decoration: none; }");
	targetwin.document.writeln("	.linkHead:hover { color: #FFFFFF; text-decoration: underline; }");
	targetwin.document.writeln("	.linkMain { color: #597391; font-weight: bold; text-decoration: none; }");
	targetwin.document.writeln("	.linkMain:hover { color: 317DBF; text-decoration: underline; }");
	targetwin.document.writeln("</STYLE>");
	targetwin.document.writeln("</HEAD>");

	targetwin.document.writeln("<BODY leftmargin='5' topmargin='5' style='border-style: none'>");
	targetwin.document.writeln("<FORM>");

  	var prevyear = eval(year) - 1; 
  	var prevmonth = calcprevmonth(month); 
  	var prevmonthyear = calcprevyear(month,year); 
	var index = eval(month) - 1; 
  	var nextmonth = calcnextmonth(month); 
  	var nextmonthyear = calcnextyear(month,year); 
  	var nextyear = eval(year) + 1; 
	
	targetwin.document.writeln("<TABLE width='100%' border='0' cellpadding='0' cellspacing='0'>");
	targetwin.document.writeln("<TR bgcolor='#00309C' height='25'>");
	targetwin.document.writeln("<TD width='20' align='right'><A class='linkHead' title='"+ monthname[month-1] + " " + prevyear + "' href='#' onClick='document.close();opener.calccal(opener.calwin,"+month+","+prevyear+",\""+targetfield+"\")'><<</A></TD>");	
	targetwin.document.writeln("<TD width='18' align='right'><A class='linkHead' title='"+ monthname[prevmonth-1] + " " + prevmonthyear + "' href='#' onClick='document.close();opener.calccal(opener.calwin,"+prevmonth+","+prevmonthyear+",\""+targetfield+"\")'><</A></TD>");
	targetwin.document.writeln("<TD align='center' nowrap><A class='linkHead'>" + monthname[index] + "&nbsp;" +  year + "</A></TD>");
	targetwin.document.writeln("<TD width='18' align='left'><A class='linkHead' title='"+ monthname[nextmonth-1] + " " + nextmonthyear + "' href='#' onClick='document.close();opener.calccal(opener.calwin,"+nextmonth+","+nextmonthyear+",\""+targetfield+"\")'>></A></TD>");
  	targetwin.document.writeln("<TD width='20' align='left'><A class='linkHead' title='"+ monthname[month-1] + " " + nextyear + "' href='#' onClick='document.close();opener.calccal(opener.calwin,"+month+","+nextyear+",\""+targetfield+"\")'>>></A></TD>");
	targetwin.document.writeln("</TR>");
	targetwin.document.writeln("<TR><TD colspan=5 height='5'></TD></TR>");
	targetwin.document.writeln("</TABLE>");

	targetwin.document.writeln("<TABLE class='tablaceleste'>");
	targetwin.document.writeln("<TR>");
	targetwin.document.writeln("<TD>");
	targetwin.document.writeln("<TABLE width='100%' border='0' cellpadding='0' cellspacing='0'>");
  	targetwin.document.writeln("<TR bgcolor='#D7E5F1' height='20'>");
  	targetwin.document.writeln("<TD align='center'><B>Lu</B></TD>"); 
  	targetwin.document.writeln("<TD align='center'><B>Ma</B></TD>"); 
  	targetwin.document.writeln("<TD align='center'><B>Mi</B></TD>"); 
  	targetwin.document.writeln("<TD align='center'><B>Ju</B></TD>"); 
  	targetwin.document.writeln("<TD align='center'><B>Vi</B></TD>"); 
  	targetwin.document.writeln("<TD align='center'><B>Sa</B></TD>");      
  	targetwin.document.writeln("<TD align='center'><B>Do</B></TD>"); 
  	targetwin.document.writeln("</TR>"); 

  	var selectedmonth = eval(month) - 1; 
  	//var today = new Date(); 
	var today = datefield; 
  	var thisyear = today.getYear(); 
  	var selectedyear = eval(year) - thisyear + 4; 

  	var conditionalpadder = ""; 

  	for(r=0; r<6; r++) { 
   		targetwin.document.write("<TR>"); 
   		for(c=0; c<7; c++) { 
			targetwin.document.write("<TD align='center'>"); 
    			if(day[r][c] != null) { 
      			if (day[r][c] < 10) {
        				conditionalpadder = "&nbsp;";
        				strDia = "0"+(day[r][c]).toString();
				} else {
        				conditionalpadder = ""; 
	  				strDia = (day[r][c]).toString();
				}

      			if (selectedmonth+1 < 10) {
        				strMes = "0"+(selectedmonth+1).toString();
				} else {
	  				strMes = (selectedmonth+1).toString();
				}

// Se requiere que al ingresar la fecha el focus quede posicionado en el cambo respectivo.
var campoorig = eval("'" + targetfield + "'");
var pos = campoorig.indexOf('.value');
var campo = campoorig.substring(0, pos);

//      			targetwin.document.write("<INPUT TYPE=BUTTON NAME="+day[r][c]+         " VALUE=" + conditionalpadder + day[r][c] + conditionalpadder +  
//				" onClick=\"window.close();" +targetfield+"='"+ strDia + "/"+  strMes + "/" + yearA[selectedyear.toString()]+"';\"; style='cursor:hand'>"); 
//				" onClick=\"window.close();" +targetfield+"='"+ strDia + "/"+  strMes + "/" + yearA[selectedyear.toString()]+"';" +campo +".focus();\";>"); 


			targetwin.document.write("<A class='linkMain' href='#' onClick=\"window.close();" +targetfield+"='"+ strDia + "/"+  strMes + "/" + yearA[selectedyear.toString()]+"';" +campo +".focus();\";>" + conditionalpadder + day[r][c] + conditionalpadder +"</A>"); 

			} 
    			targetwin.document.write("</TD>"); 
   		} 
   		targetwin.document.write("</TR>"); 
  	} 
  	targetwin.document.write("</TABLE></TD></TR>"); 
  	targetwin.document.write("</TABLE></FORM>"); 
	targetwin.document.write("</BODY></HTML>");
} 

function calclastday(month,year) { 
	if ((month==2) && ((year%4)==0)) 
		return 29; 

	if ((month==2) && ((year%4)!=0)) 
		return 28; 

	if ((month==1) || (month == 3) || (month == 5) || (month == 7) || 
		(month==8) || (month == 10) || (month ==12)) 
		return 31; 

	return 30; 
} 

function calcnextmonth(month) { 
	if (month=="12") 
		return "1"; 
	else 
		return (eval(month)+1); 
} 

function calcnextyear(month,year) { 
	if (month=="12") 
		return (eval(year)+1); 
	else 
		return (year); 
} 

function calcprevmonth(month) { 
	if (month=="1") 
		return "12"; 
	else 
		return (eval(month)-1); 
} 

function calcprevyear(month,year) { 
	if (month=="1") 
		return (eval(year)-1); 
	else 
		return (year); 
} 

function rip(s, token) {
	s.replace()
}