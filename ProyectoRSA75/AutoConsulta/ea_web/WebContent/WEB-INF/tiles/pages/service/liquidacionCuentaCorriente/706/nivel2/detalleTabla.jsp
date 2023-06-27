<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<style>
.nota {color: #333366; text-align: justify; vertical-align: top; font:8.5pt tahoma;}
</style>


<logic:notEmpty name="detalle" scope="request">
<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
	<tbody>
		<tr style="height : 18px;">
			<td class="smallfontreverse">
				<b>
					<bean:message key="liquidacion.706.nivel2.table.header" />&nbsp;
					<bean:message key="global.text.unidadMonetaria" />
				</b>
			</td>														
		</tr>
		<tr>
			<td>
				<table style="width : 100%; border : 0px; border-color : #cccccc;" cellspacing=1 cellpadding=2>
					<tbody>
						<tr style="background-color : #cccccc;">
							<td colspan="2" style="width : 55%; text-align : left;">
							</td>
							<td class="smalltableheader" style="width : 20%; vertical-align: top;">
								Valores Referenciales
							</td>
							<td class="smalltableheader" style="width : 20%; vertical-align: top;">
								Valores Finales
							</td>
							<td style="width : 5%; color: red;">								
							</td>																												
						</tr>

						<tr>
							<td colspan="2" class="smallprompt" style="text-align : left;">
								1. Cotizaci&oacute;n 0.6%
							</td>
							<td class="smallpropmt">
							</td>
							<td class="smallcurrency">
								<bean:write name="detalle" property="cotizacionCalculada" filter="true" formatKey="global.monto" ignore="true" />
							</td>
							<td class="smallprompt" style="color: red;">
																
							</td>																												
						</tr>
						<tr>
							<td class="smallprompt" style="width: 10%; text-align : left;">
								&nbsp;									
							</td>
							<td class="smallprompt" style="width: 45%;">
								Informado por la Empresa
							</td>
							<td class="smallcurrency" >
								<bean:write name="detalle" property="cotizacionInformada" filter="true" formatKey="global.monto" ignore="true" />
							</td>																				
							<td colspan="2" class="smallprompt" >
							</td>																				
						</tr>
						
						<tr>
							<td class="smallprompt" style="text-align : left;">
								&nbsp;									
							</td>
							<td class="smallprompt">
								Calculado por la Caja
							</td>
							<td class="smallcurrency" >
								<bean:write name="detalle" property="cotizacionCalculada" filter="true" formatKey="global.monto" ignore="true" />
							</td>																				
							<td colspan="2" class="smallprompt" >
							</td>																				
						</tr>

						<tr>
							<td colspan="2" class="smallprompt" style="text-align : left;">
								2. Asignaci&oacute;n Familiar
							</td>
							<td class="smallpropmt">
							</td>
							<td class="smallcurrency">
								<bean:write name="detalle" property="asignacionFamiliarAceptada" filter="true" formatKey="global.monto" ignore="true" />
							</td>
							<td class="smallprompt" style="color: red;"></td>																												
						</tr>

						<tr>
							<td class="smallprompt" style="text-align : left;">
								&nbsp;									
							</td>
							<td class="smallprompt">
								Compensado por la Empresa
							</td>
							<td class="smallcurrency" >
								<bean:write name="detalle" property="asignacionFamiliarInformada" filter="true" formatKey="global.monto" ignore="true" />
							</td>																				
							<td colspan="2" class="smallprompt" >
							</td>																				
						</tr>
						
						<tr>
							<td class="smallprompt" style="text-align : left;">
								&nbsp;									
							</td>
							<td class="smallprompt"">
								Autorizado por la Caja
							</td>
							<td class="smallcurrency" >
								<bean:write name="detalle" property="asignacionFamiliarAutorizada" filter="true" formatKey="global.monto" ignore="true" />
							</td>																				
							<td colspan="2" class="smallprompt" >
							</td>																				
						</tr>
				
						<tr>
							<td class="smallprompt" colspan="3">
								3. Monto pagado por la Empresa
							</td>
							<td class="smallcurrency" >
								<bean:write name="detalle" property="montoPagadoPorEmpresa" scope="request" filter="true" formatKey="global.monto" ignore="true" />
							</td>
							<td class="smallprompt" style="color: red;">
								</td>
						</tr>
																								
						<tr>
							<td class="smallprompt" colspan="3">
								4. Saldo a Favor Empresa
							</td>
							<td class="smallcurrency" >
								<bean:write name="detalle" property="saldoAFavorEmpresa" scope="request" filter="true" formatKey="global.monto" ignore="true" />
							</td>
							<td class="smallprompt" style="color: red;">
								</td>
						</tr>

						<tr>
							<td colspan="5" style="height : 1px; background-color : #006666;"></td>
						</tr>

						<tr>
							<td colspan="3" class="smalltableheader" style="text-align : left;">
								Saldo Final Determinado
							</td>								
							<td class="smallcurrency" >
								<bean:write name="detalle" property="saldo" filter="true" formatKey="global.monto" ignore="true" />
							</td>																				
							<td class="smallprompt" style="color: red;">
							</td>																				
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<br />
				<table width="430px">
					<tr>
						<td class="nota" colspan="2">Nota:</td>
					</tr>
					<tr>
						<td class="nota">1. </td>
						<td class="nota">El monto considerado por Cotizaciones es el calculado por la Caja</td>
					</tr>
					<tr>
						<td class="nota">2. </td>
						<td class="nota">La Asignaci&oacute;n Familiar determinada, ser&aacute; la menor que resulta de la comparaci&oacute;n entre la compensada por la Empresa versus la autorizada por la Caja</td>
					</tr>
				</table>
			</td>
		</tr>
	</tbody>
</table>

</logic:notEmpty>
