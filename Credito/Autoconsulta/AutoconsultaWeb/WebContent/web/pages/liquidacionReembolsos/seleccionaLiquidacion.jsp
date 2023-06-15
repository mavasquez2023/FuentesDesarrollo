<font class="certificado">
<br>  
<br>
<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td nowrap class="certificado">
			<div align='center'>
				<h1><bean:message key="label.liquidacion.titulo.linea5"/></h1>
			</div>
		</td>
	</tr>
	
</table>
<br>
<br>
<html:form action='/getLiquidacionReembolsos'>  
<logic:empty name="lista.liquidaciones">
	<bean:message key="label.liquidacion.sin.lquidacion"/>
</logic:empty>
<logic:notEmpty name="lista.liquidaciones">
<table border="0" cellpadding="2" cellspacing="2" width="50%">
		<tr>
			<td class="certificado" nowrap class="textobold"> 
				<bean:message key="label.liquidacion.seleccion"/>:
			</td>
			<td class="certificado" class="texto">
			  	<html:select property="nroliq" styleClass="combo">
    			    <html:options collection="lista.liquidaciones" labelProperty="fechaLiquidacion"  property="nroLiquidacion"/>
		        </html:select>
			</td>
			<td class="texto" colspan='2' align='center'>
				<html:image page='/images/aceptar.gif'/>
			</td>
		</tr>
</table>
</logic:notEmpty>
</html:form>

</font>


