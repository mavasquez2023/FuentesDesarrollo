//var brw = new Browser();
//var askBrw=brw.fullName;
/*
if(askBrw=="iexplorer" || askBrw=="MSIE" || askBrw=="Microsoft Internet Explorer"){
      	alert("estas en browser IE");
      }
*/

//[volverMenu].---------------------------------------------------------------------------------------
//volver a menu principal
			function volverMenu(){
				pageDirec='mostrarMenu.do?metodo=mostrarMenu';
				$.ajax({ 
					type:"GET", 
					url : pageDirec,
					async : true,
					beforeSend : function (){
						openLoadMenu();
					},
					success: function(){  
						window.location.href=pageDirec;
					}					
				}); 				
			}
//[recargar paginas de mantenedores].---------------------------------
		

	//Página Tabla1 Reitegros
			function goPage1(){
				url = 'mostrarPag1.do?metodo=mostrarPag1';
				myWindowLocation(url);
			}
			
	//Página Tabla2 SubPrePostNm
			function goPage2(){
				url = 'mostrarPag2.do?metodo=mostrarPag2';
				myWindowLocation(url);
			}
			
	//Página Tabla3 SubParental
			function goPage3(){
				url = 'mostrarPag3.do?metodo=mostrarPag3';
				myWindowLocation(url);
			}
	//Página Tabla5 ControlDocu
	function goPage5(){
		url = 'mostrarPag5.do?metodo=mostrarPag5';
		myWindowLocation(url);
	}
			
	//Página Tabla6 DocsRevalReem
			function goPage6(){
				url = 'mostrarPag6.do?metodo=mostrarPag6';
				myWindowLocation(url);
			}
	//Página Tabla7 DatosLicCob
			function goPage7(){
				url = 'mostrarPag7.do?metodo=mostrarPag7';
				myWindowLocation(url);
			}
			
	//Página Tabla 8
			function goPage8(){
				url = 'mostrarPag8.do?metodo=mostrarPag8';
				myWindowLocation(url);
			}
			
					
//[Paginacion].---------------------------------------------------------------------------------------
//botones para avanzar y retroceder, en mantenedores con paginacion
		function avanzar(key){	
			$("#formAvance").find("input[name='keyAvance']").val(key);
			document["formAvance"].submit();         
		}
         
		function retroceder(key){
			$("#formAvance").find("input[name='keyAvance']").val(key);
			document["formAvance"].submit();        
		}
         
        function avanzarEstadoDoc(key,estado){	
			$("#formAvance").find("input[name='keyAvance']").val(key);
			$("#formAvance").find("input[name='keyEstadoDoc']").val(estado);
			document["formAvance"].submit();         
		}
         
		function retrocederEstadoDoc(key,estado){
			$("#formAvance").find("input[name='keyAvance']").val(key);
			$("#formAvance").find("input[name='keyEstadoDoc']").val(estado);
			document["formAvance"].submit();        
		} 
        

//[Busqueda].------------------------------------------------------------------------------------------
	//para busuqedas:
		function cargarBuscar(){
			$("#botonBuscar").click(function(){
				document["filtroBuscar"].submit();        		
			});
        }
        
//[Informe Financiero].--------------------------------------------------------------------------------
	//Para Generar Cuadro InformeFinanciero
			function generarInformeFinanciero(){
          		//linea que realiza scrolling
          		$('html, body').animate({scrollTop: '0px'}, 800);
          		//se inserta gif carga				
				url = 'generarIF.do?metodo=generarIF';
				myWindowLocation(url)
			}
			
			function cargarIF(){				
				url = 'cargarIF.do?metodo=cargarIF';
				myWindowLocation(url)
			}
//[AJAX].---------------------------------------------------------------------------------------------------------
	//Para cargar gif al realizar operaciones
	function myWindowLocation(pageDirec){
		$.ajax({success:function(){}});
		$.ajax({ 
			type:"POST", 
			url : pageDirec,
			async : true,
			beforeSend :myLoad(),
			complete: function(){
     			$("#load").hide();
  			},
			success: function(){ 			 
				window.location.href=pageDirec;
			}								
		}); 
	}
	
//[DIALOG].-----------------------------------------------------------------------------------------------------------
	//pagina para cargar modal de carga mientras se retorna a menu principal
            $(document).ready(function(){
            	$("#load").hide();
            });			
			function myLoad(){
				setTimeout(function(){$("#load").show()},10);
            	//$("#load").show();
			}
						
			function openLoadMenu() {
				setTimeout(function(){$("#loadMenu").show()},10);     
				//$("#loadMenu").show();
				$("#insertar-dialog" ).dialog( "destroy" );
          		$("#LoadMenu_dialog").dialog("open");
            }
            $(document).ready(function(){
            	
            	  $("#loadMenu").hide();
                  $("#LoadMenu_dialog").dialog({
                        resizable: false,
                        width: 480,
                        modal: true,
                        autoOpen:false,
                        buttons: {
                             "Ok": function() {
                                   $( this ).dialog( "close" );
                             },
                             Cancelar: function() {
                                   $( this ).dialog( "close" );
                             }
                        }
                  });
            });