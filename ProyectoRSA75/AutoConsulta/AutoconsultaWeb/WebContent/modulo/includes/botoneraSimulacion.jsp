
<script> 

function click(caracter){

	opcion=document.PARAMForm.campo.value;
	switch(opcion){
		case '0', "0":
			user=document.PARAMForm.ingresosLiquidos.value;
			switch(caracter){
				case 'D': 
					if(user.length>0) {
					   user = user.slice(0,user.length-1);
					   document.PARAMForm.ingresosLiquidos.value=user;
					}
					break;
				default:
					if(user.length<9)
						user=document.PARAMForm.ingresosLiquidos.value + caracter;
						document.PARAMForm.ingresosLiquidos.value = user;
					break;
			}		
			break;
		case '1',"1":
			user=document.PARAMForm.montoSolicitado.value;
			switch(caracter){
				case 'D': 
					if(user.length>0) {
					   user = user.slice(0,user.length-1);
					   document.PARAMForm.montoSolicitado.value=user;
					}
					break;
				default:
					if(user.length<9)
						user=document.PARAMForm.montoSolicitado.value + caracter;
						document.PARAMForm.montoSolicitado.value = user;
					break;
			}				
			break;
		case '2',"2":
			user=document.PARAMForm.cantidadCuotas.value;
			switch(caracter){
				case 'D': 
					if(user.length>0) {
					   user = user.slice(0,user.length-1);
					   document.PARAMForm.cantidadCuotas.value=user;
					}
					break;
				default:
					if(user.length<9)
						user=document.PARAMForm.cantidadCuotas.value + caracter;
						document.PARAMForm.cantidadCuotas.value = user;
					break;
			}				
			break;
		case '3',"3":
			user=document.PARAMForm.fechaNacimiento.value;
			switch(caracter){
				case 'D': 
					if(user.length>0) {
					   user = user.slice(0,user.length-1);
					   document.PARAMForm.fechaNacimiento.value=user;
					}
					break;
				default:
					if(user.length<9)
						user=document.PARAMForm.fechaNacimiento.value + caracter;
						document.PARAMForm.fechaNacimiento.value = user;
					break;
			}						
			break;
		case '4',"4":
			user=document.PARAMForm.fechaIngresoEmpresa.value;
			switch(caracter){
				case 'D': 
					if(user.length>0) {
					   user = user.slice(0,user.length-1);
					   document.PARAMForm.fechaIngresoEmpresa.value=user;
					}
					break;
				default:
					if(user.length<9)
						user=document.PARAMForm.fechaIngresoEmpresa.value + caracter;
						document.PARAMForm.fechaIngresoEmpresa.value = user;
					break;
			}	
			break;
	}
}


</script> 


<table border="0" cellpadding="1" cellspacing="0" width="50%">
	<tr>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('1')">
			<html:img page="/images/botonera/uno.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('2')">
			<html:img page="/images/botonera/dos.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('3')">
			<html:img page="/images/botonera/tres.gif" border="0"/>
			</html:link>
		</td>
	</tr>
	<tr>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('4')">
			<html:img page="/images/botonera/cuatro.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('5')">
			<html:img page="/images/botonera/cinco.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('6')">
			<html:img page="/images/botonera/seis.gif" border="0"/>
			</html:link>
		</td>
	</tr>
	<tr>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('7')">
			<html:img page="/images/botonera/siete.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('8')">
			<html:img page="/images/botonera/ocho.gif" border="0"/>
			</html:link>
		</td>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('9')">
			<html:img page="/images/botonera/nueve.gif" border="0"/>
			</html:link>
		</td>
	</tr>
	<tr>
		<td>
			<html:link styleClass="subopcion" href="javascript:click('0')">
			<html:img page="/images/botonera/cero.gif" border="0"/>
			</html:link>
		</td>
		<td colspan='2'>
			<html:link styleClass="subopcion" href="javascript:click('D')">
			<html:img page="/images/botonera/borrarh.gif" border="0"/>
			</html:link>
		</td>
	</tr>


</table>

