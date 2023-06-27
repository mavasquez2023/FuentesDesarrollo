function doOpcion(opc) {
    if (opc=="cns") {
        document.fOpc.action = "consultas.do";

    } else {
        document.fOpc.action = "certificados.do";
    }
    document.fOpc.md2_opcion.value = opc;
    document.fOpc.submit();
   
}

function doOpcionMenu(opcmnu) {

    if (document.fOpc.md2_opcion.value=="cns") {
        document.fOpc.action = "consultas.do";
    } else {
        document.fOpc.action = "certificados.do";
    }
    document.fOpc.md2_opcionMnu.value = opcmnu;
    document.fOpc.submit();

}

function doEncargado() {
    
    if (document.fEncargado.md2_opcion.value=="cns") {
        document.fEncargado.action = "consultas.do";
    } else {
        document.fEncargado.action = "certificados.do";
    }
    document.fEncargado.rutAfiliado.value = document.form1.rutAfiliado.value;
//    alert('voy a encargado con [' + document.fEncargado.rutAfiliado.value + ']');
    document.fEncargado.submit();

}

function doImprime() {
    window.frames[0].focus();
    window.frames[0].print();
}