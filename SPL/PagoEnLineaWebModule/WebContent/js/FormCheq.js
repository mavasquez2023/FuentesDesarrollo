// ----------------------------------------------------------------------
//           FormCheq.js (c) ChaTo (i.am/chato) 1998
//              basado en FormChek.js (c) Eric Krock 1997 Netscape Corp.
// ----------------------------------------------------------------------
// Rutinas para verificacion de formularios, basado en FormChek.js
// Parte del curso "TEJEDORES DEL WEB" http://www.dic.uchile.cl/~manual
// ---------------------------------------------------------------------- 
//
// Modificado en Enero del 2000 por Mannungo: arregle el isEmail.
// Modificado en Marzo del 2000 por Mannungo: agrego isDominio, isURL.
// ---------------------------------------------------------------------- 

var defaultEmptyOK = false
var digits = "0123456789";
var lowercaseLetters = "abcdefghijklmnopqrstuvwxyz·ÈÌÛ˙"
var uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ¡…Õ”⁄"

var Letters = "=+&/^#!.-_" + digits  + lowercaseLetters + uppercaseLetters;
var whitespace = " \t\n\r";
var phoneChars = "()- ";

var nameChars = "'-.,!@#$%^&*()_+?" + whitespace;
var nameDireccion = "/-.,!@#$%^&*()_+?" + whitespace;

var mMessage = "Error: no puede dejar este espacio vacio"
var pPrompt = "Error: ";
var pAlphanumeric = "ingrese un texto que contenga solo letras y/o numeros";
var pAlphabetic   = "ingrese un texto que contenga solo letras";
var pInteger = "ingrese un numero entero";
var pNumber = "ingrese un numero";
var pPhoneNumber = "ingrese un numero de telefono";
var pEmail = "ingrese una direccion de correo electronico valida";
var pName = "ingrese un texto que contenga solo letras, numeros o espacios";

function makeArray(n) {
   for (var i = 1; i <= n; i++) {
      this[i] = 0
   } 
   return this
}

function isEmpty(s) {
	return ((s == null) || (s.length == 0))
}

function isNotEmpty(s) {
	return ! isEmpty(s)
}

function isNotWhitespace(s) {
	return ! isWhitespace(s)
}

function isWhitespace (s)
{   var i;
    if (isEmpty(s)) return true;
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        // si el caracter en que estoy no aparece en whitespace,
        // entonces retornar falso
        if (whitespace.indexOf(c) == -1) return false;
    }
    return true;
}


function stripCharsInBag (s, bag)
{   var i;
    var returnString = "";

    // Buscar por el string, si el caracter no esta en "bag", 
    // agregarlo a returnString
    
    for (i = 0; i < s.length; i++)
    {   var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }

    return returnString;
}


function stripCharsNotInBag (s, bag)
{   var i;
    var returnString = "";
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if (bag.indexOf(c) != -1) returnString += c;
    }

    return returnString;
}

function isURL(nada) { return true;}

function stripWhitespace (s)
{   return stripCharsInBag (s, whitespace)
}

function charInString (c, s)
{   for (i = 0; i < s.length; i++)
    {   if (s.charAt(i) == c) return true;
    }
    return false
}

function stripInitialWhitespace (s)
{   var i = 0;
    while ((i < s.length) && charInString (s.charAt(i), whitespace))
       i++;
    return s.substring (i, s.length);
}

function isLetter (c)
{
    return( ( uppercaseLetters.indexOf( c ) != -1 ) ||
            ( lowercaseLetters.indexOf( c ) != -1 ) )
}

function isDigit (c)
{   return ((c >= "0") && (c <= "9"))
}

function isLetterOrDigit (c)
{   return (isLetter(c) || isDigit(c))
}

function isInteger (s)
{
  var i;
  if (isEmpty(s)) 
    if (isInteger.arguments.length == 1)
	   return defaultEmptyOK;
    else
	   return (isInteger.arguments[1] == true);
    
  for (i = 0; i < s.length; i++)
  {
    if ( !isDigit( s.charAt(i) ) )
      return false;
  }

  return true;
}


function isNumber (s)
{
  var i;
  var dotAppeared;
  dotAppeared = false;
  if (isEmpty(s)) 
    if (isNumber.arguments.length == 1) return defaultEmptyOK;
    else return (isNumber.arguments[1] == true);
    
  for (i = 0; i < s.length; i++)
  {   
    var c = s.charAt(i);
    if( i != 0 )
	 {
      if ( c == "." )
      {
        if( !dotAppeared )
          dotAppeared = true;
        else
          return false;
      }
		else     
        if (!isDigit(c)) return false;
    }
	 else
	 { 
      if ( c == "." )
		{
        if( !dotAppeared )
          dotAppeared = true;
        else
        return false;
      }
		else     
        if (!isDigit(c) && (c != "-") || (c == "+")) return false;
    }
  }
  return true;
}

function isAlphabetic (s)
{   var i;

    if (isEmpty(s)) 
       if (isAlphabetic.arguments.length == 1) return defaultEmptyOK;
       else return (isAlphabetic.arguments[1] == true);
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is letter.
        var c = s.charAt(i);

        if (!isLetter(c))
          return false;
    }
    return true;
}

function isAlphanumeric (s)
{   var i;

    if (isEmpty(s)) 
       if (isAlphanumeric.arguments.length == 1) return defaultEmptyOK;
       else return (isAlphanumeric.arguments[1] == true);

    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if (! (isLetter(c) || isDigit(c) || isWhitespace(c) ) )
          return false;
    }

    return true;
}

function isName (s)
{
    if (isEmpty(s)) 
       if (isName.arguments.length == 1) return defaultEmptyOK;
       else return (isAlphanumeric.arguments[1] == true);
    
    return( isAlphanumeric( stripCharsInBag( s, nameChars ) ) );
}

function isDireccion(s)
{
    if (isEmpty(s)) 
       if (isDireccion.arguments.length == 1) return defaultEmptyOK;
       else return (isAlphanumeric.arguments[1] == true);
    
    return( isAlphanumeric( stripCharsInBag( s, nameDireccion ) ) );
}

function isPhoneNumber (s)
{   var modString;
    if (isEmpty(s)) 
       if (isPhoneNumber.arguments.length == 1) return defaultEmptyOK;
       else return (isPhoneNumber.arguments[1] == true);
    modString = stripCharsInBag( s, phoneChars );
    return (isInteger(modString))
}


function isEmail (s)
{
  if (isEmpty(s))
    if (isEmail.arguments.length == 1) 
	  return defaultEmptyOK;
    else 
	  return (isEmail.arguments[1] == true);
 
  if (isWhitespace(s)) return false;
  var i = 1;
  var sLength = s.length;
       
  // Verifica que tenga 1 simbolo "@":
  i = s.indexOf("@");
  if ( i <= 0 || i >= sLength ) return false;
         
  // Verifica que tenga un nombre y un dominio no vacios:
  user = s.substring( 0, i );
  dominio = s.substring( i+1, sLength );
  if( ! isDominio( dominio ) ) 
    return false;
  if ( user.length <= 0 ) 
    return false;
   
  // Verifica que el nombre tenga caracteres correctos
  for( i = 0; i < user.length; i++ )
    if ( Letters.indexOf(user.charAt(i)) == -1) // || user.charAt(i) == '_' ) 
	  return false;

  // Verifica que no comience con . o - y qye no tenga dos puntos seguidos.
  if( user.indexOf("..") != -1 || user.charAt(0) == '.' || user.charAt(0) == '_' )
    return false;

  return true;
}

function isDominio( s )
{
  if (isEmpty(s))
    if (isDominio.arguments.length == 1) return defaultEmptyOK;
    else return (isDominio.arguments[1] == true);
  if ( isWhitespace(s) ) return false;
  var i = 1;
  var sLength = s.length;
  // Verifica que el dominio tenga al menos 1 punto.
  i = s.indexOf(".");
  if ( i <= 0 || i >= sLength )
    return false;
 
  // Verifica que el dominio tenga caracteres correctos
  for( i = 0; i < sLength; i++ )
    if ( Letters.indexOf(s.charAt(i)) == -1 ) 
	  return false;

  // Verifica que el dominio satisfaga la expresion regular ((L*(L|-)*L).)+LLL
  // donde L son los caracteres validos (letras, numeros y '-')
  for ( i = s.indexOf("."); i != -1; i = s.indexOf(".") )
    {
    aux = s.substring( 0, i );
    s = s.substring( i+1, sLength );
    if ( aux.length == 0 || aux.charAt(0) == '-' || aux.charAt(aux.length-1) == '-' ) 
	  return false;
    }
  if ( s.length == 0 || s.length > 4 || s.charAt(0) == '-' || s.charAt(aux.length-1) == '-' ) 
    return false;

  return true;
}


function statBar (s)
{   window.status = s
}

function warnEmpty (theField, s)
{
	theField.focus();
    statBar(s);
    alert(s);
    return false;
}

function warnInvalid (theField, s)
{   theField.focus()
    theField.select()
    alert(s)
    statBar(pPrompt + s)
    return false
}

function isPwd(s)
{
  if ( s.length < 4 )
    return false;

  return ( isInteger(s) );
}

function isUsername(s)
{
//  if ( s.length < 4 )
//   return false;

//  return ( isAlphanumeric(s) );
  return ( isRut(s) );
}

function isDate2(s) {
  var dia, mes, ano, j, k;
  var str = s;

  j = str.indexOf("-");
  if (j<0)
    return false;

  dia = str.substring(0, j);

  k = str.indexOf("-", j+1);
  if (k<0)
    return false;

  mes = str.substring(j+1, k);
  ano = str.substring(k+1, str.length);

  return isDate(dia + "/" + mes + "/" + ano);
}

function isDate(s)
{
  var dia, mes, ano, j, k;
  var str = s;
  var bis = 0;

  j = str.indexOf("/");
  if (j<0)
    return false;

  dia = str.substring(0, j);

  k = str.indexOf("/", j+1);
  if (k<0)
    return false;

  mes = str.substring(j+1, k);
  ano = str.substring(k+1, str.length);


  if ( !isInteger(dia) || !isInteger(mes) || !isInteger(ano) )
      return false;

  if ( (mes<1 || mes>12) || (dia<1 || dia>31) )
    return false;

  if ( mes==4 || mes==6 || mes==9 || mes==11 )
  {
     if ( dia > 30 )
      return false;
  }
  if ( mes==2 )
  {
     if ( (ano%4) == 0)
       bis = 1;

     if (dia > 28+bis )
       return false;
  }

  return true;
}

function VerificaEmail ( user, dominio, msg )
{
  var s = user.value + "@" + dominio.value;

  if ( !isEmail(s) )
   return warnInvalid(user, msg);

  return true;
}

function VerificaRut ( rut, dig, msg )
{
  var s = rut.value + "-" + dig.value;

  if ( !isRut(s) )
   return warnInvalid(rut, msg);

  return true;
}

function isRut( s )
{
  var i, c, m=2, sum=0;
  var rut, dig_in, dig;

  j = s.indexOf("-");
  if ( j < 0 )
    return false;

  rut		= s.substring(0, j);
  dig_in	= s.substring(j+1, s.length);

  if ( isEmpty(rut) || isEmpty(dig_in) )
     return false;

  for (i=rut.length-1; i>=0; i--)
  {
    c = rut.charAt(i);
	 if ( !isDigit(c) )
	   return false;
    sum += c*m;
    m++;
    if (m>7)
	  m=2;
  }

  var dig=sum%11;
  dig=11-dig;
  if (dig==11) dig=0;
  if (dig==10) dig="k";

  dig_in	= dig_in.toLowerCase();
  if (dig_in==dig)
    return true;

  return false;
}

function stripCeros( s )
{
    var i = 0;
    while ( (i < s.length) && (s.charAt(i)=='0') )
       i++;
    return s.substring (i, s.length);
}

function formatRut( campo )
{
  var i, c;
  var rut, dig;
  //campo.value = stripCeros(campo.value);
  var s = campo.value;
  if ( s.length < 2 || s.indexOf("-") > 0 )
    return;

  rut	= s.substring(0, s.length-1);
  dig	= s.substring(s.length-1, s.length);
  
  campo.value = rut + "-" + dig;
  return;

}
function isSelected(theField, s)
{
  if (theField.selectedIndex == 0)
    return warnEmpty( theField, s );
  
  return true;
}

function checkField (theField, theFunction, emptyOK, s)
{   
    var msg;
    if (checkField.arguments.length < 3) emptyOK = defaultEmptyOK;
    if (checkField.arguments.length == 4) {
        msg = s;
    } else {
        if( theFunction == isAlphabetic ) msg = pAlphabetic;
        if( theFunction == isAlphanumeric ) msg = pAlphanumeric;
        if( theFunction == isInteger ) msg = pInteger;
        if( theFunction == isNumber ) msg = pNumber;
        if( theFunction == isEmail ) msg = pEmail;
        if( theFunction == isPhoneNumber ) msg = pPhoneNumber;
        if( theFunction == isName ) msg = pName;
    }
    
    if ((emptyOK == true) && (isEmpty(theField.value))) return true;

    if ((emptyOK == false) && (isEmpty(theField.value))) 
        return warnEmpty(theField, msg);

	if (theFunction(theField.value) == true) 
		return true;
	else
		return warnInvalid(theField, msg);
}

//
// Fin de FormCheq
//
