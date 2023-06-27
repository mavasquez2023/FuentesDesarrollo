
<script> 

function click(caracter){
	user=document.<%= form+"."+field %>.value;
	switch(caracter){
		case 'D': 
			if(user.length>0) {
			   user = user.slice(0,user.length-1);
			   document.<%= form+"."+field %>.value=user;
			}
			break;
		case '':
			document.<%= form %>.submit();
		default:
			if(user.length<9)
				user=document.<%= form+"."+field %>.value + caracter;
				document.<%= form+"."+field %>.value = user;
			break;
	}
	if(user.length==9)
		document.<%= form %>.submit();
}


</script> 


<table border="0" cellpadding="1" cellspacing="0" width="50%">
	<tr>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('1')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/uno.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('2')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/dos.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('3')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/tres.gif" border="0"/>
			</html:link>
		</td>
		<td rowspan='2'>
			<html:link styleClass="subopcion" href="javascript:click('D')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/borrarv.gif" border="0"/>
			</html:link>
		</td>
	</tr>
	<tr>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('4')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/cuatro.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('5')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/cinco.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('6')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/seis.gif" border="0"/>
			</html:link>
		</td>
	</tr>
	<tr>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('7')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/siete.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('8')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/ocho.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('9')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/nueve.gif" border="0"/>
			</html:link>
		</td>
		<td rowspan='2'>
			<html:link styleClass="subopcion" href="javascript:click('')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/entrar.gif" border="0"/>
			</html:link>
		</td>

	</tr>
	<tr>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('0')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/cero.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('K')">
			<html:img src="/AutoconsultaWeb/modulo/images/botonera/k.gif" border="0"/>
			</html:link>
		</td>
		<td>
		</td>
	</tr>


</table>

