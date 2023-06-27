function ignoreSpaces(string) {
	var temp = "";
	string = '' + string;
	splitstring = string.split(" ");
	for(i = 0; i < splitstring.length; i++)
	temp += splitstring[i];
	return temp;
}

function cantidadcaracteres(caracteres) {
	var temp = 0;
	temp=caracteres.length;
	return temp;
}

function trimval(str) {
  var ini=0;
  var fin=str.length-1;
  while(ini<=fin && str.charAt(ini)==" ") 
    ini++;
    if (ini<=fin) {
        while(str.charAt(fin)==" ") 
            fin--;
        if (fin<str.length-1) {
            fin++;
            return str.substring(ini,fin);
        } else 
            return str.substr(ini);
    } else
        return '';
}

function isNumero(str) {
  var flag=true;
  var i=0;
  while (i<str.length && flag) {
    flag = (str.charAt(i)>=0 && str.charAt(i)<=9)
    i++;    
  }
  return flag;
}

function trimobj(obj) {
  obj.value=trimval(obj.value);
  return obj.value;
}

function validaM11(rut,dv) {
  var suma=0;
  var mul=2;
  var i=0;
  for (i=rut.length-1;i>=0;i--) {
    suma=suma+rut.charAt(i) * mul;
    mul= mul==7 ? 2 : mul+1;
  }
  var dvr = ''+(11 - suma % 11);
  if (dvr=='10') 
    dvr = 'K';
  else 
    if (dvr=='11') 
        dvr = '0';
        if (dvr!=dv) 
            return false;
    else 
        return true;
}

function digitoM11(rut) {
  var suma=0;
  var mul=2;
  var i=0;

  for (i=rut.length-1;i>=0;i--) {
    suma=suma+rut.charAt(i) * mul;
    mul= mul==7 ? 2 : mul+1;
  }

  var dvr = ''+(11 - suma % 11);

  if (dvr=='10') 
    dvr = 'K';
  else 
    if (dvr=='11') 
        dvr = '0';
	return dvr
}


function getYear(d) { 
   return (d < 1000) ? d + 1900 : d;
}

function fecha(year, month, day) {
   // month argument must be in the range 1 - 12
   
   month = month - 1;  // javascript month range : 0- 11
   
   var tempDate = new Date(year,month,day);
   if ( (getYear(tempDate.getYear()) == year) &&
      (month == tempDate.getMonth()) &&
      (day == tempDate.getDate()) )
       return true;
   else
      return false
}