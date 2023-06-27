<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />	
		<title><bean:message key="title.application"/></title>	

		<script language="javascript">var jsContextRoot = '<c:out value="${contextRoot}"/>';</script>

		<link href="${contextRoot}/styles/araucana.css" rel="stylesheet" type="text/css"/>
		<link href="${contextRoot}/styles/jquery.tools.css" rel="stylesheet" type="text/css"/>
		<script src="${contextRoot}/scripts/utilsbox.js" language="javascript" type="text/javascript"></script>		
		<script src="${contextRoot}/scripts/jquery.tools.full.min.js" language="javascript" type="text/javascript"></script>
		<script src="${contextRoot}/scripts/autoNumeric.js" language="javascript" type="text/javascript"></script>		
		<script src="${contextRoot}/scripts/jquery.scrollTo.js" language="javascript" type="text/javascript"></script>		
		<script src="${contextRoot}/scripts/jquery.blockUI.js" language="javascript" type="text/javascript"></script>		
		<script src="${contextRoot}/scripts/jquery.livequery.js" language="javascript" type="text/javascript"></script>	
		<script src="${contextRoot}/scripts/contenidoCajas.js" language="javascript" type="text/javascript"></script>
		<script src="${contextRoot}/scripts/jquery.Rut.min.js" language="javascript" type="text/javascript"></script>
		<script type="text/javascript">

			$(document).ready(function() {

				// Inputs numericos
				$('input.integer').each(function(){
				    $(this).autoNumeric();
				    if ($(this).attr('id')) 
				        $(this).val($.fn.autoNumeric.Format($(this).attr('id'), $(this).val()));
				});				
				$('input.decimal').each(function(){
				    $(this).autoNumeric({mDec: 2});
				    if ($(this).attr('id')) 
				        $(this).val($.fn.autoNumeric.Format($(this).attr('id'), $(this).val(), {mDec: 2}));
				});				
				$('input.bigdecimal').each(function(){
				    $(this).autoNumeric({mDec: 4});
				    if ($(this).attr('id')) 
				        $(this).val($.fn.autoNumeric.Format($(this).attr('id'), $(this).val(), {mDec: 4}));
				});				
				
								
				// para calendarios
				if (!isOldFashionBrowser() ) {
						$.tools.dateinput.localize("es",  {
							   months:        'enero,febrero,marzo,abril,mayo,junio,julio,agosto,septiembre,octubre,noviembre,diciembre',
							   shortMonths:   'ene,feb,mar,abr,may,jun,jul,ago,sep,oct,nov,dic',
							   days:          'domingo,lunes,martes,miercoles,jueves,viernes,sabado',
							   shortDays:     'dom,lun,mar,mie,jue,vie,sab'
						});
						$(".date").dateinput({ 
						selectors: true,
						yearRange: [-70, 5],	
						lang: 'es', 
						firstDay: 1,
						format: 'dd/mm/yyyy'
					});
				}

				// tool Tip				
				if (!isOldFashionBrowser() )
					 $(".addToolTip").tooltip();	

				// TABs y paneles
				$("ul.tabs").tabs("> .pane");
				
				
				$("#cargandoPagina").hide();
				
			});
		</script>
		
	</head>

	
	<body onbeforeunload="javascript:showLoading();">
	
		<div id="loading" style="position:fixed; top:30%; left:54%; display:none; z-index: auto" >
			<html:img page='/images/3d-loader.gif' />
		</div>

		<div id="div-body">
        	<center>
				<tiles:insert attribute="header"/>
				
				<table width="980" border="0" cellpadding="0" cellspacing="0" class="table_transparente" id="table-body">
					<tr  id="tr-body">
				        <td width="180" align="left" valign="top" id="td_uno_body">
							<tiles:insert attribute="menu"/>
				        </td>
				        <td width="600" valign="top" align="left" id="td_dos_body">
							<tiles:insert attribute="messages"/>
							
							<tiles:insert attribute="body"/>
				        </td>
					</tr>
				</table>
				<tiles:insert attribute="footer"/>
			</center>
			
		</div>
	</body>
	
	
</html>