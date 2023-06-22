
<!-- menu vertical -->
<nav id="menu" class="left">
  <ul>
    <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
    <li><a href="#"><i class="fa fa-info-circle"></i>About</a></li>
    <li> <a href="#"><i class="fa fa-laptop"></i>Cotizaciones <i class="fa fa-caret-down"></i></a>
      <ul>
        <li><a href="#" style="font-size: 12px;">Trabajadores No Cotizados Mes en Proceso</a></li>
        <li><a href="#">Trabajadores No Cotizados &uacute;ltimos 12 meses</a></li>
      </ul>
    </li>
    <li> <a href="#"><i class="fa fa-laptop"></i>Cargas Familiares <i class="fa fa-caret-down"></i></a>
		      <ul>
		        <li><a href="#">Cargas Familiares No Canceladas</a></li>
		      </ul>
    </li>
    <li> <a href="#" class="active"><i class="fa fa-laptop"></i>Movimientos Personal <i class="fa fa-caret-down"></i></a>
	      <ul>
	        <li><a href="#" onclick="Redirect();">Informar Cesaci&oacute;n Trabajador</a></li>
	        <li><a href="#">Informar Cesaci&oacute;n Masiva</a></li>
	      </ul>
    </li>
    <li> <a href="#"><i class="fa fa-laptop"></i>Certificados <i class="fa fa-caret-down"></i></a>
			      <ul>
			        <li><a href="#">Certificado Cesaci&oacute;n Trabajador</a></li>
			      </ul>
    </li>
    <li><a href="#"><i class="fa fa-phone"></i>Contact</a></li>
  </ul>
  <a href="#" id="showmenu"> <i class="fa fa-align-justify"></i> </a> 
</nav>
<!-- /menu vertical -->


<script type="text/javascript" src="https://pagead2.googlesyndication.com/pagead/show_ads.js">
</script></div>
</div>
<!-- /contenido de pagina, realmente no importa -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
$("#showmenu").click(function(e){
			e.preventDefault();
			$("#menu").toggleClass("show");
		});
		$("#menu a").click(function(event){
			event.preventDefault();
			if($(this).next('ul').length){
				$(this).next().toggle('fast');
				$(this).children('i:last-child').toggleClass('fa-caret-down fa-caret-left');
			}
		});
</script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>


