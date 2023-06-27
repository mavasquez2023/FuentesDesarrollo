<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<style type="text/css">
.noframe-framing-table {background-color: #767776; background-image: none; border-right: 1px solid #000000;	border-bottom: 1px solid #000000; border-top: 1px solid #000000; border-left: 1px solid #000000;}
.column-head { padding-left: .35em; font-family: Arial, Helvetica, sans-serif; font-size: 77.0%; font-weight: bold; text-align: left; color: #FFFFFF; background-color: #8691C7; background-image: none;}
.table-text { font-family: Arial, Helvetica, sans-serif; font-size: 12px; background-color: #F7F7F7; background-image: none;}
.login-button-section {padding-left: 0px; font-family: Arial, Helvetica, sans-serif; font-size:10px; font-weight: normal; color: #000000; background-color: #CCCCCC; background-image: none;}
.nota {color: #333366; text-align: justify; vertical-align: top; font:8.5pt tahoma;}

/* FORMS */
FIELDSET {border: 0px}
INPUT {font-family: sans-serif; font-size: 95.0%}
FORM {margin-bottom: 0px; margin-top: 0px; padding-top: 0px; padding-bottom: 0px;}
</style>

<script>
function IsRut(theRut)
	{
	rut = theRut;
	var tmpstr = "";
	largo = rut.length;
	for ( i=0; i < largo; i++ )
		{
		if ( rut.charAt(i) != ' ' && rut.charAt(i) != '.' && rut.charAt(i) != '-' )
			{
			tmpstr = tmpstr + rut.charAt(i);
			}
		}
	rut = tmpstr;
	largo = rut.length;
	if ( largo < 2 )
		{
		return false;
		}

	for (i=0; i < largo ; i++ )
		{
		if ( rut.charAt(i) !="0" && rut.charAt(i) != "1" && rut.charAt(i) !="2" && rut.charAt(i) != "3" && rut.charAt(i) != "4" && rut.charAt(i) !="5" && rut.charAt(i) != "6" && rut.charAt(i) != "7" && rut.charAt(i) !="8" && rut.charAt(i) != "9" && rut.charAt(i) !="k" && rut.charAt(i) != "K" )
			{
			return false;
			}
		}
	var dtexto = "";
	cnt = 0;
	for ( i=largo-1, j=largo-1+3; i>=0; i--, j-- )
		{
		if ( cnt == 3 )
			{
			dtexto = rut.charAt(i) + dtexto;
			dtexto = '.' + dtexto;
			cnt = 1;
			}
		else
			{
			dtexto = rut.charAt(i) + dtexto;
			if( cnt == 0 )
				{
				dtexto = '-' + dtexto;
				}
			cnt++;
			}
		}
	return checkDV(rut);
	}


function checkDV( crut )
	{
	largo = crut.length;
	if ( largo > 2 )
		{
		rut = crut.substring(0, largo - 1);
		}
	else
		{
		rut = crut.charAt(0);
		}

	dv = crut.charAt(largo-1);
	if ( rut == null || dv == null )
		{
		return 0;
		}

	var dvr = '0';
	suma = 0;
	mul  = 2;
	for (i = rut.length - 1 ; i >= 0; i--)
		{
		suma += rut.charAt(i) * mul;
		if (mul == 7)
			{
			mul = 2;
			}
		else
			{
			mul++;
			}
		}
	res = suma % 11;
	if (res == 1)
		{
		dvr = 'k';
		}
	else if (res == 0)
		{
		dvr = '0';
		}
	else
		{
		dvi = 11-res;
		dvr = dvi + "";
		}

	if ( dvr != dv.toLowerCase() )
		{
		return false;
		}

	return true;
	}
</script>

<table width="520" border="0" align="center" cellpadding="1" cellspacing="1">
	<tr>
		<td>&nbsp;
		<td>
	</tr>
	<tr>
		<td>
			<form action="j_security_check" method="post">
			<table border=0 cellpadding=0 cellspacing=0 width="100%" height="100%" align="center">
				<tr> 
					<td class="login">
					<table class="noframe-framing-table" border=0 cellpadding="5" cellspacing="0" width="400" summary="Login Table" align="center">
						<tbody>
							<tr>
								<th colspan="2" class="column-head" scope="rowgroup">Ingresar&nbsp;&nbsp;</th>
							</tr>
							<tr>
								<td valign=top colspan="2" class="table-text" nowrap>&nbsp;</td>
							</tr>
							<tr>
								<td valign=top width="20%" class="table-text" nowrap>
									<label	for="name">Rut:</label>
								</td>
								<td valign=top class="table-text">
									<input type="text" name="j_username" class="short" id="name">
									<font size="1">&nbsp;&nbsp;(incluir guión y dígito verificador)</font>
								</td>
							</tr>
							<tr>
								<td valign=top width="20%" class="table-text" nowrap>
									<label	for="pswd">Contrase&ntilde;a:</label>
								</td>
								<td valign=top class="table-text">
									<input type="password" name="j_password" class="short" id="pswd">
								</td>
							</tr>
							<tr>
								<td valign=top colspan="2" class="login-button-section" nowrap align="center">
									<input type="submit" name="action" class="buttons" value='Aceptar'>
								</td>
							</tr>
						</tbody>
					</table>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td>
						<table>
							<tr>
								<td class="nota" style="width: 35px;"><b>NOTA</b> </td>
								<td class="nota">
									Este servicio se encuentra en etapa de implementaci&oacute;n, 
									por lo que su acceso mediante la creaci&oacute;n y asignaci&oacute;n de clave, 
									esta siendo coordinado mediante un proceso de Solicitudes, 
									por lo que lo invitamos a tomar contacto envi&aacute;ndonos su solicitud 
									a la direcci&oacute;n electr&oacute;nica <a href="mailto:ctorres@laaraucana.cl">ctorres@laaraucana.cl</a>.
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>			
			</form>
		</td>
	</tr>
</table>

<script>
	document.forms[0].j_username.focus();	
</script>