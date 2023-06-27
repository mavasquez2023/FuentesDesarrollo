<%@ page import="java.util.*, cl.araucana.core.util.*, cl.araucana.cp.receipt.*" %>


<jsp:useBean id="receiptReport" class="cl.araucana.cp.receipt.ReceiptReport" scope="request" />

<%
 	List acceptedNominas = receiptReport.getAcceptedFiles();
 	List rejectedNominas = receiptReport.getRejectedFiles();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- receiptNo=<%= Padder.lpad(receiptReport.getReceiptNo(), 8, '0') %> -->

<html>

<head>

<title>Reporte del Env&iacute;o de N&oacute;minas - cp.cl</title>

<style>
  td.main-header-left-green 
  {
    text-align: left;
    vertical-align: top;
    font-family: sans-serif,Tahoma,Arial;
    font-weight: bold;
    font-size: 12;
    color: white;
    background: #009933;
  }

  td.header-left-green 
  {
    text-align: left;
    vertical-align: top;
    font-family: sans-serif,Tahoma,Arial;
    font-weight: bold;
    font-size: 12;
    color: black;
    background: #00FF66;
  }

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

  td.header-left-red 
  {
    text-align: left;
    vertical-align: top;
    font-family: sans-serif,Tahoma,Arial;
    font-weight: bold;
    font-size: 12;
    color: black;
    background: #FF9999;
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

  td.row-left-blue 
  {
    text-align: left;
    vertical-align: middle;
    font-family: sans-serif,Tahoma,Arial;
    font-size: 12;
    color: blue;
    background: white;
  }
</style>

</head>
<body bgcolor="white">
    <table border="0" width="100%" align="center">
    	<tr>
    		<td>
				<table border="1" width="90%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td width="15%" class="row-left">&nbsp;<b>#Env&iacute;o:</b></td>
						<td width="35%" class="row-left-blue">&nbsp;<%= Padder.lpad(receiptReport.getReceiptNo(), 8, '0') %></td>
						<td width="15%" class="row-left">&nbsp;<b>Recibido:</b></td>
						<td width="35%" class="row-left-blue">&nbsp;${receiptReport.received}</td>
					</tr>
					<tr>
						<td width="15%" class="row-left">&nbsp;<b>M&eacute;todo:</b></td>
						<td width="35%" class="row-left-blue">&nbsp;${methodName}</td>
						<td width="15%" class="row-left">&nbsp;<b>Duraci&oacute;n:</b></td>
						<td width="35%" class="row-left-blue">&nbsp;${receiptReport.serviceTime} ms</td>
					</tr>
					<tr>
						<td width="15%" class="row-left">&nbsp;<b>N&oacute;minas:</b></td>
						<td width="35%" class="row-left-blue">&nbsp;${receiptReport.NFilesReceived}</td>
						<td width="15%" class="row-left">&nbsp;<b>Aceptadas:</b></td>
						<td width="35%" class="row-left-blue">&nbsp;${receiptReport.NAcceptedFiles}</td>
					</tr>
					<tr>
						<td width="15%" class="row-left">&nbsp;<b>Estado:</b></td>
						<td width="35%" colspan="3" class="row-left">&nbsp;
						<%
							int status = receiptReport.getStatus();
							if (status == 0) 
							{	%>
								<font color="blue"><b>OK</b></font>
						<%  } else if (status == 1) 
							{	%>
							    <font color="orange"><b>ADVERTENCIA (${receiptReport.statusMessage})</b> </font>
						<%  } else 
							{	%>
						        <font color="red"><b>ERROR (${receiptReport.statusMessage})</b></font>
						<%  }	%>
						</td>
					</tr>
				</table>
				<br>
				<br>
			</td>
		</tr>
		<%
			if (acceptedNominas.size() > 0) 
			{
		%>
				<tr>
					<td>
						<table border="1" width="90%" align="center" cellpadding="3" cellspacing="0">
							<tr>
								<td colspan="2" class="main-header-left-green">&nbsp;<b>N&oacute;minas Aceptadas</b></td>
							</tr>
							<tr>
								<td width="20%" class="header-left-green">&nbsp;<b>N&oacute;mina</b></td>
								<td class="header-left-green">&nbsp;<b>Comentario</b></td>
							</tr>
						<%	for (int i = 0; i < acceptedNominas.size(); i++) 
							{
								AcceptedFile acceptedNomina = (AcceptedFile) acceptedNominas.get(i);
							%>
								<tr>
									<td width="20%" class="row-left">&nbsp;<%= acceptedNomina.getID() %></td>
									<td class="row-left">
							<%	if (!acceptedNomina.getID().equals(acceptedNomina.getFileName())) 
								{	%>
									&nbsp; (<%= acceptedNomina.getFileName() %>) 
							<%	}	%>
									&nbsp; <%= acceptedNomina.getComment() %>
									</td>
								</tr>
							<% 	}	%>
						</table>
					</td>
				</tr>
		<%	}
			if (rejectedNominas.size() > 0) 
			{	%>
				<tr>
					<td>
						<br>
						<table border="1" width="90%" align="center" cellpadding="3" cellspacing="0">
							<tr>
								<td colspan="2" class="main-header-left-red">&nbsp;<b>N&oacute;minas Rechazadas</b></td>
							</tr>
							<tr>
								<td width="20%" class="header-left-red">&nbsp;<b>N&oacute;mina</b></td>
								<td class="header-left-red">&nbsp;<b>Causa del Rechazo</b></td>
							</tr>
						<%
							for (int i = 0; i < rejectedNominas.size(); i++) 
							{
								RejectedFile rejectedNomina = (RejectedFile) rejectedNominas.get(i);
						%>
								<tr>
									<td width="20%" class="row-left">&nbsp;<%= rejectedNomina.getID() %></td>
									<td class="row-left">&nbsp;
							<%
								if (!rejectedNomina.getID().equals(rejectedNomina.getFileName())) 
								{	%>
									(<%= rejectedNomina.getFileName() %>)
							<%	}	%>
									<%= rejectedNomina.getReason() %>
								</td>
							</tr>
						<% 	}	%>
						</table>
					</td>
				</tr>
		<%	}	%>
    </table>
</body>
</html>
