<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<script type="text/javascript" src="./lightbox/jquery.min.js"></script>
	<script type="text/javascript" src="./lightbox/lib/jquery.mousewheel-3.0.6.pack.js"></script>
	<script type="text/javascript" src="./lightbox/jquery.fancybox.js"></script>
	<link rel="stylesheet" type="text/css" href="./lightbox/jquery.fancybox.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="./lightbox/helpers/jquery.fancybox-buttons.css?v=2.0.3" />
	<script type="text/javascript" src="./lightbox/helpers/jquery.fancybox-buttons.js?v=2.0.3"></script>
	<link rel="stylesheet" type="text/css" href="./lightbox/helpers/jquery.fancybox-thumbs.css?v=2.0.3" />
	<script type="text/javascript" src="./lightbox/helpers/jquery.fancybox-thumbs.js?v=2.0.3"></script>
		
<%
	javax.naming.Context init = new javax.naming.InitialContext();
	javax.naming.Context env = (javax.naming.Context)init.lookup("java:comp/env");
	String urlPortalPublico = (String) env.lookup("urlPortalPublico");
%>	
		
	<script type="text/javascript">
	  function redireccionar(){
		  location.replace('<%= urlPortalPublico %>');
	  }
	  	  
	</script>  
	
		<script> 
		function fireOnclick(objID) {
			var target=document.getElementById(objID);
			if(document.dispatchEvent) { // W3C
				var oEvent = document.createEvent( "MouseEvents" );
				oEvent.initMouseEvent("click", true, true,window, 1, 1, 1, 1, 1, false, false, false, false, 0, target);
				target.dispatchEvent( oEvent );
			}
			else if(document.fireEvent) { // IE
				target.fireEvent("onclick");
			}    
		}
	</script>
	
	<style type="text/css">
		.fancybox-custom .fancybox-outer {
			box-shadow: 0 0 50px #222;
		}
	</style>
</head>
<body onload="fireOnclick('login')">
 <a class="fancybox fancybox.iframe" href="/portal" id="login"></a>
	<iframe width="100%"  height="1000" src="<%= urlPortalPublico %>">
	</iframe> 

	<script type="text/javascript">
	//	$(document).ready(function() {

			/*
				Simple image gallery. Use default settings
			*/

			$('.fancybox').fancybox();

			/*
				Different effects
			*/

			// Change title type, overlay opening speed and opacity
			$(".fancybox-effects-a").fancybox({
				helpers: {
					title : {
						type : 'outside'
					},
					overlay : {
						speedIn : 500,
						opacity : 0.95
					}
				}
			});

			// Disable opening and closing animations, change title type
			$(".fancybox-effects-b").fancybox({
				openEffect  : 'none',
				closeEffect	: 'none',

				helpers : {
					title : {
						type : 'over'
					}
				}
			});

			// Set custom style, close if clicked, change title type and overlay color
			$(".fancybox-effects-c").fancybox({
				wrapCSS    : 'fancybox-custom',
				closeClick : true,

				helpers : {
					title : {
						type : 'inside'
					},
					overlay : {
						css : {
							'background-color' : '#eee'	
						}
					}
				}
			});

			// Remove padding, set opening and closing animations, close if clicked and disable overlay
			$(".fancybox-effects-d").fancybox({
				padding: 0,

				openEffect : 'elastic',
				openSpeed  : 150,

				closeEffect : 'elastic',
				closeSpeed  : 150,

				closeClick : true,

				helpers : {
					overlay : null
				}
			});

			/*
				Button helper. Disable animations, hide close button, change title type and content
			*/

			$('.fancybox-buttons').fancybox({
				openEffect  : 'none',
				closeEffect : 'none',

				prevEffect : 'none',
				nextEffect : 'none',

				closeBtn  : false,

				helpers : {
					title : {
						type : 'inside'
					},
					buttons	: {}
				},

				afterLoad : function() {
					this.title = 'Image ' + (this.index + 1) + ' of ' + this.group.length + (this.title ? ' - ' + this.title : '');
				}
			});


			/*
				Thumbnail helper. Disable animations, hide close button, arrows and slide to next gallery item if clicked
			*/

			$('.fancybox-thumbs').fancybox({
				prevEffect : 'none',
				nextEffect : 'none',

				closeBtn  : false,
				arrows    : false,
				nextClick : true,

				helpers : { 
					thumbs : {
						width  : 50,
						height : 50
					}
				}
			});

	//	});
	</script>

</body>

</html>