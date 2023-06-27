<%@ page import="cl.araucana.core.util.*" %>

<jsp:useBean id="receiptReport" class="cl.araucana.cp.receipt.ReceiptReport" scope="request" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- receiptNo=<%= Padder.lpad(receiptReport.getReceiptNo(), 8, '0') %> -->
<html>
<head>
<title>Reporte del Env&iacute;o de N&oacute;minas - cp.cl</title>
<style>
  td.main-header-left-red 
  {
    text-align: left;
    vertical-align: top;
    font-family: sans-serif,Tahoma,Arial;
    font-weight: bold;
    font-size: 12;
    color: white;
    background: #FF0000;
  }

  td.row-left 
  {
    text-align: left;
    vertical-align: middle;
    font-family: sans-serif,Tahoma,Arial;
    font-size: 12;
    color: black;
    background: white;
  }
</style>
</head>
<body bgcolor="white">
<br /><br />
<table border="0" width="100%" align="center">
	<tr>
		<td>
			<table border="1" width="90%" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<td class="main-header-left-red" align="center">
						<b>Error de procesamiento</b>
					</td>
				</tr>
				<tr>
					<td class="row-left">
						En estos momentos el servicio de env&iacute;o de n&oacute;minas, se encuentra ocupado.
						<br />
						Por favor  reintentar el env&iacute;o nuevamente.
						<br />
						Si el problema persiste, por favor contactase con nuestra mesa de ayuda.
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>
