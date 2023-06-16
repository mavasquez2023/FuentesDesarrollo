//[consutar estados proceso].---------------------------------------------------------------------------
//actualiza el estado de proceso carga y validacion de BD SIMAT
			function volverMenu(){
				pageDirec='mostrarMenu.do?metodo=mostrarMenu';
				$.ajax({ 
					type:"GET", 
					url : pageDirec,
					async : true,
					beforeSend : function (){
						myLoad();   
					},
					complete: function(){
		     			$("#loadMenu").hide();
		  			},
					success: function(){
						  
						window.location.href=pageDirec;						
					}					
				}); 				
			}
			
			function getEstadosProcesos(){
				pageDirec='mostrarMenu.do?metodo=mostrarMenu';
				$.ajax({ 
					type:"GET", 
					url : pageDirec,
					async : true,
					beforeSend : function (){
						$("#workSpacePeriodo").hide();
						$("#loadMSG").show();
					},
					complete: function(){						
		     			$("#loadMSG").hide();
		  			},
					success: function(){
						  
						window.location.href=pageDirec;
						$("#workSpacePeriodo").show();
					}					
				}); 				
			}
			
//[Mantenedores].----------------------------------------------------------------------------------------------------
	//Página Tabla1 Reitegros
			function goPag1(){
				url = 'mostrarPag1.do?metodo=mostrarPag1';
				myWindowLocation(url);
			}
			
	//Página Tabla2 SubPrePostNm
			function goPag2(){
				url = 'mostrarPag2.do?metodo=mostrarPag2';
				myWindowLocation(url);
			}
			
	//Página Tabla3 SubParental
			function goPag3(){
				url = 'mostrarPag3.do?metodo=mostrarPag3';
				myWindowLocation(url);
			}
			
	//Página Tabla4 SubStsVig
			function goPag4(){
				url = 'mostrarPag4.do?metodo=mostrarPag4';
				myWindowLocation(url);
			}
			
	//Página Tabla5 ControlDocu
			function goPag5(){
				url = 'mostrarPag5.do?metodo=mostrarPag5';
				myWindowLocation(url);
			}
			
	//Página Tabla6 DocsRevalReem
			function goPag6(){
				url = 'mostrarPag6.do?metodo=mostrarPag6';
				myWindowLocation(url);
			}
			
	//Página Tabla7 DatosLicCob
			function goPag7(){
				url = 'mostrarPag7.do?metodo=mostrarPag7';
				myWindowLocation(url);
			}
			
	//Página Tabla 8
			function goPag8(){
				url = 'mostrarPag8.do?metodo=mostrarPag8';
				myWindowLocation(url);
			}
			
	//Página Tabla9 Usuario
			function goPag9(){
				url = 'mostrarPag9.do?metodo=mostrarPag9';
				myWindowLocation(url);
			}
			
	//Página Tabla10 logproceso
			function goPag10(){
				url = 'mostrarPag10.do?metodo=mostrarPag10';
				myWindowLocation(url);
			}
			
	//Página Tabla11 Códigos de Homologación
			function goPag11(){
				url = 'mostrarPag11.do?metodo=mostrarPag11';
				myWindowLocation(url);
			}			
			
	//Página Tabla 12 Informe Financiero
			function goPag12(){
				url = 'mostrarPag12.do?metodo=mostrarPag12';
				myWindowLocation(url);
			}
			
			function visorIF(){
				url = 'visorIF.do?metodo=visorIF';
				myWindowLocation(url);
			}
//[Documentos].----------------------------------------------------------------------------------------------------
	//Página T13 generar planos txt, csv
			function generarPlano_1(){				
				url = 'generarPlano_1.do?metodo=generarPlano_1';
				myWindowLocation(url);
			}
			function generarPlano_2(){				
				url = 'generarPlano_2.do?metodo=generarPlano_2';
				myWindowLocation(url);
			}
			function generarPlano_3(){				
				url = 'generarPlano_2.do?metodo=generarPlano_3';
				myWindowLocation(url);
			}
			function generarPlano_4(){				
				url = 'generarPlano_4.do?metodo=generarPlano_4';
				myWindowLocation(url);
			}
			function generarPlano_5(){				
				url = 'generarPlano_5.do?metodo=generarPlano_5';
				myWindowLocation(url);
			}
			function generarPlano_6(){				
				url = 'generarPlano_6.do?metodo=generarPlano_6';
				myWindowLocation(url);
			}
			function generarPlano_7(){				
				url = 'generarPlano_7.do?metodo=generarPlano_7';
				myWindowLocation(url);
			}
			function generarPlano_8(){				
				url = 'generarPlano_8.do?metodo=generarPlano_8';
				myWindowLocation(url);
			}
	//Página T14 generar cuadros estadisticos
			function generarCuadro_1(){				
				url = 'generarCuadro_1.do?metodo=generarCuadro_1';
				myWindowLocation(url);
			}
			function generarCuadro_2(){				
				url = 'generarCuadro_2.do?metodo=generarCuadro_2';
				myWindowLocation(url);
			}
			function generarCuadro_3(){
				url = 'generarCuadro_3.do?metodo=generarCuadro_3';				
				myWindowLocation(url);
			}
			function generarCuadro_4(){
				url = 'generarCuadro_4.do?metodo=generarCuadro_4';
				myWindowLocation(url);
			}
			function generarCuadro_5(){
				url = 'generarCuadro_5.do?metodo=generarCuadro_5';
				myWindowLocation(url);
			}
			function generarCuadro_6(){
				url = 'generarCuadro_6.do?metodo=generarCuadro_6';
				myWindowLocation(url);
			}
			
	//Página Generar Resumen de cotizaciones previsionales
			function generarRCP(){				
				url = 'generarRCP.do?metodo=generarRCP';
				myWindowLocation(url);
			}
	//Página Generar Cuadro ILF4501
			function generarILF4501(){				
				url = 'generarILF4501.do?metodo=generarILF4501';
				myWindowLocation(url);
			}

//[Procedimientos].-----------------------------------------------------------------------------------
	//Para llamar a procedimiento cobol para validacion de tablas.
			function ProcedimientoValidar(){				
				url = 'ProcedimientoValidar.do?metodo=ProcedimientoValidar';
				myWindowLocation(url);
			}
	//Para llamar a procedimiento cobol para cargar archivos
			function ProcesoGenerarArchivos(){
				url = 'ProcedimientoGenerarArchivos.do?metodo=procesoGenerarArchivos';
				myWindowLocation(url);
			}
	//Para llamar a procedimiento cobol para distribuir. "no usar"
			function ProcesoDistribucion(){				
				url = 'ProcedimientoGenerarArchivos.do?metodo=ProcedimientoDistribucion';
				myWindowLocation(url);
			}			
//[Movimiento Menu].---------------------------------------------------------------------------------------------
	//Opcion Salir		
			function salir(){
				url = 'mostrarPag15.do?metodo=cerrarSession';
				myWindowLocation(url);
			}
			function closeSesion(){		
				window.open('', '_self', ''); 
				window.close();
			}
			
	//Página cambiar periodo
	  		function cambiarPeriodo(){	  			
	  			url = 'cambiarPeriodo.do?metodo=cambiarPeriodo';
				myWindowLocation(url);				
			}

//[Pase contable Menu].---------------------------------------------------------------------------------------------	  		
	  		function generarPlanoPaseContable(){
	  			url = "validarPreBalance.do";
	  			myWindowLocation(url);	
	  		}
	  		
//[Efecto mensajes Periodo].--------------------------------------------------------------------------------------
function destacarMSG(){	  			
	$("workSpacePeriodo").show( "highlight", 2000 );
}

//[AJAX].---------------------------------------------------------------------------------------------------------
	//para cargar gif al hacer operaciones en menu
	function myWindowLocation(pageDirec){
		window.location.href=pageDirec;
/*		$.ajax({ 
			type:"POST", 
			url : null,
			async : true,
			beforeSend : function (){
				myLoad();   
			},
			complete: function(){
     			$("#loadMenu").hide();
  			},
			success: function(){  
				window.location.href=pageDirec;
			}					
		}); */
	}

//[Loading Menu].---------------------------------------------------------------------------------------------------
	//imagen de loading mientras se realizan algunas operaciones
	  		function myLoad(){
				//$("#loadMenu").show();				
				$("#RespuestaEjecucion").text("");
				setTimeout(function(){$("#loadMenu").show()},10);  
			}
            $(document).ready(
            	function(){
            		$("#loadMenu").hide();
            		$("#loadMSG").hide();
            		
            	}
            );