<%@ include file="/comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="/comun/header.jsp" flush="true" />
<title>RESULTADO NRP</title>
<script language="JavaScript 1.2" type="text/javascript">


function EjecutarProceso(accion, tipo){
	form1.proceso.value= tipo;	
	form1.action.value=accion + ".do?proceso=" + tipo;
	form1.submit();
}



</script>
</head>
<body>

<jsp:include page="main/banner.jsp" flush="true" />
<jsp:include page="main/menuServices.jsp" flush="true" />

	<p class="titulo">TOTAL REGISTROS - CONSOLIDACIÓN NRP</p>
	<form action="" name="form1" id="form1">
	<div style="margin-left: 325px">
	<!-- Seleccione tipo gráfico:  -->
	<!-- input type="radio" name="tipoGrafico" id="tipoGrafico" value="registros" <c:if test="${tipoGrafico=='REGISTROS' }"> checked="checked"</c:if> onClick="EjecutarProceso('resultConsolidacion', this.value);">Registros &nbsp;&nbsp;  -->
	<!-- input type="radio" name="tipoGrafico" id="tipoGrafico" value="montos" <c:if test="${tipoGrafico=='MONTOS' }"> checked="checked"</c:if> onClick="EjecutarProceso('resultConsolidacion', this.value);"> Montos  -->
    </div>
    <input type="hidden" name="proceso" value="">
    </form>
    <canvas id="myCanvas" style="background: white;"></canvas>
    <legend for="myCanvas"></legend>
   	<script type="text/javascript">
   		var myCanvas = document.getElementById("myCanvas");
			myCanvas.width = 800;
			myCanvas.height = 300;
   
		var ctx = myCanvas.getContext("2d");

function draw_example() {
		//ctx = document.getElementById('myCanvas').getContext('2d');
		ctx.fillStyle = "rgb(200,0,0)";
		ctx.fillRect (10, 10, 55, 50);

		ctx.fillStyle = "rgba(0, 0, 200, 0.5)";
		ctx.fillRect (30, 30, 55, 50);
}

function drawLine(ctx, startX, startY, endX, endY,color){
    ctx.save();
    ctx.strokeStyle = color;
    ctx.beginPath();
    ctx.moveTo(startX,startY);
    ctx.translate(300, 0);
    ctx.lineTo(endX,endY);
    ctx.stroke();
    ctx.restore();
}
 
function drawBar(ctx, upperLeftCornerX, upperLeftCornerY, width, height,color, textbar){
    ctx.save();
    ctx.fillStyle=color;
    ctx.translate(330, 0);
    ctx.fillRect(upperLeftCornerX,upperLeftCornerY,width,height);
    ctx.fillStyle = "white";
    ctx.font = "bold 14px Arial";
 	ctx.fillText(textbar, upperLeftCornerX +10,upperLeftCornerY+30);
    ctx.restore();
}

var myVinyls = {
	<c:forEach var="entry" varStatus="vs" items="${datos}">
		"${entry.PERIODO}": ${entry.VALOR},
	</c:forEach>
};
 
var Barchart = function(options){
    this.options = options;
    this.canvas = options.canvas;
    this.ctx = this.canvas.getContext("2d");
    this.colors = options.colors;
  
    this.draw = function(){
        var maxValue = 0;
        for (var categ in this.options.data){
            maxValue = Math.max(maxValue,this.options.data[categ]);
        }
        var canvasActualHeight = this.canvas.height - this.options.padding * 2;
        var canvasActualWidth = this.canvas.width - this.options.padding * 2;

        //drawing the grid lines
        var gridValue = 0;
        while (gridValue <= maxValue){
            var gridY = canvasActualHeight * (1 - gridValue/maxValue) + this.options.padding;
            drawLine(
                this.ctx,
                300,
                gridY,
                this.canvas.width,
                gridY,
                this.options.gridColor
            );
             
            //writing grid markers
            this.ctx.save();
            this.ctx.fillStyle = this.options.gridColor;
            this.ctx.textBaseline="bottom"; 
            this.ctx.font = "bold 10px Arial";
            ctx.translate(300, 0);
            this.ctx.fillText(gridValue, 10,gridY - 2);
            this.ctx.restore();
 
            gridValue+=this.options.gridScale;
        }      
  
        //drawing the bars
        var barIndex = 0;
        var numberOfBars = Object.keys(this.options.data).length;
        var barSize = (canvasActualWidth-300)/numberOfBars;
 
        for (categ in this.options.data){
            var val = this.options.data[categ];
            var barHeight = Math.round( canvasActualHeight * val/maxValue) ;
            drawBar(
                this.ctx,
                this.options.padding + barIndex * barSize,
                this.canvas.height - barHeight - this.options.padding,
                barSize,
                barHeight,
                this.colors[barIndex%this.colors.length],
                new Intl.NumberFormat("de-DE").format(val)
            );
 
            barIndex++;
        }
 
        //drawing series name
        this.ctx.save();        
        this.ctx.textBaseline="bottom";
        this.ctx.textAlign="center";
        this.ctx.fillStyle = "#000000";
        this.ctx.font = "bold 14px Arial";
		this.ctx.translate(150, 0);
        this.ctx.fillText(this.options.seriesName, this.canvas.width/2,this.canvas.height);
        this.ctx.restore();  
         
        //draw legend
        barIndex = 0;
        var legend = document.querySelector("legend[for='myCanvas']");
        var ul = document.createElement("ul");
        ul.style.marginLeft = "300px";
        legend.append(ul);
        for (categ in this.options.data){
            var li = document.createElement("li");
            li.style.listStyle = "none";
            li.style.borderLeft = "20px solid "+this.colors[barIndex%this.colors.length];
            li.style.padding = "5px";
            li.textContent = categ;
            ul.append(li);
            barIndex++;
        }
    }
}
 
 
var myBarchart = new Barchart(
    {
        canvas:myCanvas,
        seriesName:"Total Registros",
        padding:20,
        gridScale:50000,
        gridColor:"#cccccc",
        data:myVinyls,
        colors:["#2127A2", "#3358CF","#67b6c7", "#85F1C5","#eb9743","#D8C221"]
    }
);
myBarchart.draw();
   	</script>
</body>
</html>