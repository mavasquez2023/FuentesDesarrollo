
<script> 

function clickPsw(caracter){
	clave=document.<%= form+"."+field %>.value;
	switch(caracter){
		case 'D': 
			if(clave.length>0) {
			   clave = clave.slice(0,clave.length-1);
			   document.<%= form+"."+field %>.value=clave;
			}
			break;
		default:
			if(clave.length<4)
				clave=document.<%= form+"."+field %>.value + caracter;
				document.<%= form+"."+field %>.value = clave;
			break;
	}
	if(clave.length==4){
		user=document.<%= form+"."+fieldUser %>.value;
		document.<%= form+"."+fieldUser %>.value = user.substring(0, user.length -1) + "-" + user.charAt(user.length-1);
		document.<%= form %>.submit();
	}
}

</script> 


<table border="0" cellpadding="1" cellspacing="0" width="50%">
	<tr>
		<td>
			<html:link styleClass="subopcion" href="javascript:clickPsw('1')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/uno.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:clickPsw('2')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/dos.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:clickPsw('3')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/tres.gif" border="0"/>
			</html:link>
		</td>
	</tr>
	<tr>
		<td>
			<html:link styleClass="subopcion" href="javascript:clickPsw('4')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/cuatro.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:clickPsw('5')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/cinco.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:clickPsw('6')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/seis.gif" border="0"/>
			</html:link>
		</td>
	</tr>
	<tr>
		<td>
			<html:link styleClass="subopcion" href="javascript:clickPsw('7')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/siete.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:clickPsw('8')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/ocho.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:clickPsw('9')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/nueve.gif" border="0"/>
			</html:link>
		</td>
	</tr>
	<tr>
		<td>
			<html:link styleClass="subopcion" href="javascript:clickPsw('0')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/cero.gif" border="0"/>
			</html:link>
		</td>
		<td colspan='2'>
			<html:link styleClass="subopcion" href="javascript:clickPsw('D')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/borrarh.gif" border="0"/>
			</html:link>

		</td>
		<td>
		</td>
	</tr>


</table>

