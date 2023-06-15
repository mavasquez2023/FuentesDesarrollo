<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html:html>
<head>
    <title><bean:message key="welcome.title"/></title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>La Araucana - Simulador de crédito social</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover">

	<link rel="stylesheet" href="css/fln-icons.css">
	<link rel="stylesheet" href="css/fln.css">
	<link rel="stylesheet" href="css/estilos.css">

	<script>
		WebFontConfig = {
			google: {
				families: ['Open Sans:300,400,600,700']
			}
		};
		(function () {
			var wf = document.createElement('script');
			wf.src = ('https:' == document.location.protocol ? 'https' : 'http') +
				'://ajax.googleapis.com/ajax/libs/webfont/1.5.18/webfont.js';
			wf.type = 'text/javascript';
			wf.async = 'true';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(wf, s);
		})();
	</script>
</head>

<body>
<display:table id="data" name="sessionScope.TestForm.testList"
    requestURI="/test2.do" pagesize="10">
    <display:column property="numeroCuota" title="Numero Cuota" sortable="true"/>
    <display:column property="vencimiento" title="Fecha Vencimiento" sortable="true"/>
    <display:column property="montoCapital" title="Monto Capital" sortable="true"/>
    <display:column property="montoInteres" title="Monto Interes" sortable="true"/>
    <display:column property="seguroDesgravamen" title="Seguro Desgravamen" sortable="true"/>
    <display:column property="seguroCesantia" title="Seguro Cesantia" sortable="true"/>
    <display:column property="valorCuota" title="Valor Cuota" sortable="true"/>
</display:table>

    <div class="tabla tabla--zebra tabla--full tabla--responsive tabla--paginada">
        <table data-filas="12">
        <thead>
            <tr>
                <th class="sorter-false filter-false">N° Cuota</th>
                <th class="sorter-false filter-false">Vencimiento</th>
                <th class="sorter-false filter-false">Monto Capital $</th>
                <th class="sorter-false filter-false">Monto Interés $</th>
                <th class="sorter-false filter-false">Seguro Desgravamen $</th>
                <th class="sorter-false filter-false">Seguro de Cesantía $</th>
                <th class="sorter-false filter-false">Valor Cuota $</th>
            </tr>
        </thead>
        <tbody>
            <logic:iterate id="testDataId" name="testData">
            <tr>
                <td data-th="N° Cuota" class="md-text-center"><bean:write name="testDataId" property="numeroCuota"/></td>
                <td data-th="Vencimiento" class="md-text-center"><bean:write name="testDataId" property="fechaVencimiento"/></td>
                <td data-th="Monto Capital $" class="md-text-center"><bean:write name="testDataId" property="montoCapital"/></td>
                <td data-th="Monto Interés $" class="md-text-center"><bean:write name="testDataId" property="montoInteres"/></td>
                <td data-th="Seguro Desgravamen $" class="md-text-center"><bean:write name="testDataId" property="seguroDesgravamen"/></td>
                <td data-th="Seguro Cesantía $" class="md-text-center"><bean:write name="testDataId" property="seguroCesantia"/></td>
                <td data-th="Valor Cuota $" class="md-text-center"><bean:write name="testDataId" property="valorCuota"/></td>
            </tr>
            </logic:iterate>
        </tbody>
    </table>
    <div class="paginador">
        <div class="paginador__nav">
            <span class="paginador__item paginador__flecha primero deshabilitado"><i class="fln-izquierda-doble"></i></span>
            <span class="paginador__item paginador__flecha anterior deshabilitado"><i class="fln-izquierda"></i></span>
            <span class="paginador__item salida">
                <span class="visibles">0 </span>
                <span class="total">de 0</span>
            </span>
            <span class="paginador__item total"></span>
            <span class="paginador__item paginador__flecha siguiente"><i class="fln-derecha"></i></span>
            <span class="paginador__item paginador__flecha ultimo"><i class="fln-derecha-doble"></i></span>
        </div>
        <div class="paginador__filas">
            <span class="paginador__item texto">Cantidad de filas</span>
            <span class="paginador__item paginador__select">
                <div class="paginador__input">
                    <select name="paginador__select" class="paginador__select select filas">
                        <option value="all">Todas</option>
                        <option value="12" selected>12</option>
                        <option value="24">24</option>
                        <option value="36">36</option>
                        <option value="48">48</option>
                    </select>
                    <i class="fln-abajo"></i>
                </div>
            </span>
        </div>
      </div>
    </div>

<div class="preloader-general" id="preloader-general" data-tipo="pill" data-posicion="bottom" style="display: none"></div>

<script type="text/javascript" src="js/polyfill.min.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/automatizar.min.js"></script>
<script type="text/javascript" src="js/fln.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.pager.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.widgets.js"></script>
<script type="text/javascript" src="js/funciones.js"></script>
<!-- <script type="text/javascript" src="js/modernizr.js"></script>
<script type="text/javascript" src="js/tabsOrAccordion.min.js"></script>
<script type="text/javascript" src="js/jquery.magnific-popup.min.js"></script>
<script type="text/javascript" src="js/jquery.nav.js"></script>
<script type="text/javascript" src="js/prism.js"></script>
<script type="text/javascript" src="js/fln.min.js"></script>
<script type="text/javascript" src="js/funciones.js"></script> -->
</body>

</html:html>
