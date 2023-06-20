 <html>
  <head>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
<!--


google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {

      var data = new google.visualization.DataTable();
      data.addColumn('string', 'Periodo');
      data.addColumn('number', 'Licencias');

      data.addRows([
        ['202003', 17],
        ['202004', 18],
        ['202005', 15],
        ['202006', 21]
      ]);

      var options = {
        hAxis: {
          title: 'Periodo'
        },
        vAxis: {
          title: 'Cantidad'
        }
      };

      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

      chart.draw(data, options);
    }
   //-->
</script>
</head>
<body>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <div id="chart_div" style="width: 900px; height: 500px"></div>
</body>