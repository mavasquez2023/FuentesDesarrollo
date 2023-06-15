//[Configuracion para validaciones Mantenedores].-------------------------------------

function selecValidacion(plano,tipoForm){
	var key=false;
	
	switch (plano){
	case 1:
		key=validarReintegros(tipoForm);
		break;
	case 2:
		key=validarSubsPrepostNM(tipoForm);
		break;
	case 3:
		key=validarSubsParental(tipoForm);
		break;
	//plano 4 no dispone de mantenedor.
	case 5:
		key=validarControlDocu(tipoForm);
		break;
	case 6:
		key=validarDocsRevalReem(tipoForm);
		break;
	case 7:
		key=validarDatosLibCob(tipoForm);
		break;
	case 8:
		key=validarDatosLicResol(tipoForm);
		break;
	}
	
	return key;
}
//[var global para seleccion de planos a usar en validacion].-----------------------------
	var planoKey=false;

//[cambiar foco de id no editables.]------------------------------------------------------

$("#formBorrar").find("input[name='id']").click(function() {
	$("#formBorrar").find("input[name='id']").attr("disabled", "disabled");
});

$("#formActualizar").find("input[name='id']").click(function() {
	$("#formActualizar").find("input[name='id']").attr("disabled", "disabled");
});

//[Configuracion estandar para Dialog Mantenedores].----------------------------------------------
	//Pop Up Formulario Insertar         
        function openInsertar(plano) {
        		planoKey=plano;
                $("#insertar-dialog").dialog("open");
            }
            $(document).ready(function(){
                  $("#insertar-dialog").dialog({
                        resizable: false,
                        width: 480,
                        modal: true,
                        autoOpen:false,
                        buttons: {
                             "Ok": function() {
                                   //Validaciones.
                                   var key=false;
                                   key= selecValidacion(planoKey,"formInsertar");
                                   if(key){
                                   	document["formInsertar"].submit();
                                   	$( this ).dialog( "close" );                                    
                                   }
                             },
                             Cancelar: function() {
                                   $( this ).dialog( "close" );
                             }
                        }
                  });
            });
            
	//Pop Up Borrar y  Borrar.            
            function openBorrar(id) {
            	$("#formBorrar").find("input[name='id']").val(id);
                $("#borrar-dialog").dialog("open");
            }
            $(document).ready(function(){
                  $("#borrar-dialog").dialog({
                        resizable: false,
                        width: 480,
                        modal: true,
                        autoOpen:false,
                        buttons: {
                             "Ok": function() {
                                   //Validaciones.
                                   	$("#formBorrar").find("input[name='id']").attr("disabled", false);
                                   
                                   document["formBorrar"].submit();
                                   $( this ).dialog( "close" );
                             },
                             Cancelar: function() {
                                   $( this ).dialog( "close" );
                             }
                        }
                  });
            });
            
	//Pop Up Formulario Actualizar y Actualiza.
        function openActualizar(plano,id) {        
        		planoKey=plano;
        		$("#formActualizar").find("input[name='id']").val(id);
        		doAjaxPost(id);
                $("#actualizar-dialog").dialog("open");                
            }
            $(document).ready(function(){
                  $("#actualizar-dialog").dialog({
                        resizable: false,
                        width: 480,
                        modal: true,
                        autoOpen:false,
                        buttons: {
                             "Ok": function() {
                                   //Validaciones.
                                   var key=false;
                                   key= selecValidacion(planoKey,"formActualizar");
                                   if(key){     
                                   			$("#formActualizar").find("input[name='id']").attr("disabled", false);                              
	                              		document["formActualizar"].submit();                                   
	                              		$( this ).dialog( "close" );
                                   } 
                             },
                             Cancelar: function() {
									$( this ).dialog( "close" );
                             }
                        }
                  });
            });