$(document).on("submit", "form.formpdf", function (e) { 
            $.fileDownload($(this).prop('action'), {
                preparingMessageHtml: "Estamos preparando su reporte, por favor espere...",
                failMessageHtml: "Hubo problemas al generar su reporte, por favor trate de nuevo.",
                httpMethod: "POST",
                data: $(this).serialize()
            });
            e.preventDefault(); //otherwise a normal form submit would occur
        });
 

function enviar(valor1,valor2, valor3, valor4){

document.formpdf.fechaProceso.value=valor1;
document.formpdf.empag.value=valor2;
document.formpdf.folio.value=valor3;
document.formpdf.rutEmpresa.value=valor4;
 
 }