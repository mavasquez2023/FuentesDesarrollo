package cl.araucana.independientes.helper;

public class IND_Constants {

    //Constantes
    public static String Fec_Comodin = "01/01/1900";
    public static String Fec_Comodin2 = "01/01/2001"; //TODO Despues de la mejora se debe eliminar

    public static String JNDI_Independientes = "jdbc/independientesdesa";

    //Reportes

    //Directorios
    public static String DIR_INF = "\\archivos\\independientes\\reportes\\";
    public static String DIR_PROP = "\\archivos\\independientes\\propiedades\\";

    //directorio para archivo de intercaja
    public static String DIR_SITC = "\\archivos\\independientes\\intercaja\\";

    //Directorio para uploadFile de Intercaja.
    public static String DIR_UPLFILE = "\\archivos\\independientes\\uploadFile\\";

    //Sufijos
    public static String SUF_INF_RepNominaSolAfi = "_rnsa";
    public static String SUF_INF_RepNominaSolDesafAfi = "_rnsda";
    public static String SUF_INF_ConsMasivaApo = "_rnapt";

    //sufijo para intercaja
    public static String SUF_INF_SalidaIntercaja = "_sitc";

    //Tipos de Archivos
    public static String EXT_Excel = ".xls";
    //tipo de archivo para intercaja
    public static String EXT_texto = ".txt";

    //Ambientes
    public static String Ambiente_Desa = "DESA";
    public static String Ambiente_QA = "QQAA";
    public static String Ambiente_Prod = "PROD";

    public static String Libreria_IIDTA = "bd.libreria.IIDTA";
    public static String Libreria_CPEDTA = "bd.libreria.CPEDTA";
    public static String Libreria_AFDTA = "bd.libreria.AFDTA";
    public static String Libreria_CMDTA = "bd.libreria.CMDTA";
    //Inicio REQ 6988 JLGN 11-02-2013
    public static String Libreria_TEDTA = "bd.libreria.TEDTA";
    //Fin REQ 6988 JLGN 11-02-2013

    //Estados
    public static String str_est_vigente = "2";

    //constantes de Intercaja (proceso archivo de salida).

    public static String str_cod_int_2 = "0000000000000";
    public static String str_ceros = "00000000";
    public static String str_cod_int_1 = "00";
    public static String str_blancos = " ";
    public static String str_salida = "MA";

    /*Proceso de archivo de entrada */
    //Para envio ftp.TODO FRM verificar si es necesario tener estos parametros de conexion tanto para ftp como as400
    public static String ftp_server = "146.83.1.5";
    public static String ftp_user = "SISTEMAS";
    public static String ftp_password = "SISTEMAS";

    //para envio a maquina AS400.
    public static String as400_server = "146.83.1.5";
    public static String as400_user = "SISTEMAS";
    public static String as400_password = "SISTEMAS";


    //tipos de archivos de Intercaja.
    public static String str_traspasados = "TRASPASADOS";
    public static String str_colisiones = "COLISIONES";
    public static String str_recibidos = "RECIBIDOS";
    public static String str_estadistica = "ESTADISTICA";
    public static String str_errores = "ERRORES";
    public static String str_fileFlujo4 = "30";
    public static String str_baseComunAfiliaciones = "BASE COMUN";

    public static String str_ganaAfiliado = "SI";
    public static String str_pierdeAfiliado = "NO";

    //valor defecto logErrorAS400
    public static String str_logAS400 = "000000000000";

    //Para dar nombre al periodo del archivo en casos pendientes.
    public static String str_enero = "ENERO";
    public static String str_febrero = "FEBRERO";
    public static String str_marzo = "MARZO";
    public static String str_abril = "ABRIL";
    public static String str_mayo = "MAYO";
    public static String str_junio = "JUNIO";
    public static String str_julio = "JULIO";
    public static String str_agosto = "AGOSTO";
    public static String str_septiembre = "SEPTIEMBRE";
    public static String str_octubre = "OCTUBRE";
    public static String str_noviembre = "NOVIEMBRE";
    public static String str_diciembre = "DICIEMBRE";

    //Tipos de Listas (GeograficoDWR)
    public static String lst_tipo_doc = "ListTipoDocumentoBox";
    public static String lst_est_doc = "ListEstadoDocumentoBox";
    public static String lst_ent = "ListEntidadesBox";
    public static String lst_tipo_pago = "ListTipoPagoApoBox";
    public static String lst_tipo_pago_ben = "ListTipoPagoBenBox";
    public static String lst_tipo_org = "ListTipoOrgBox";
    public static String lst_tipo_cargo = "ListTipoCargoBox";
    public static String lst_tipo_perf = "ListPerfiles";
    public static String lst_est = "ListEstados";
    public static String lst_benef = "ListBeneficiosBox";
    public static String lst_docs = "ListDocumentosBox";
    public static String lst_tipo_sol = "ListTipoSolBox";
    public static String lst_oficinas = "ListOficinasBox";

    //Tipos de Listas (MantenedoresDWR)
    public static String lst_nacionalidad = "ListNacionalidadBox";
    public static String lst_est_civl = "ListEstadoCivilBox";
    public static String lst_nvl_educ = "ListNivelEducacionalBox";
    public static String lst_prof = "ListProfesionBox";
    public static String lst_afp = "ListadoAfpBox";
    public static String lst_reg_salud = "ListRegimenSaludBox";
    public static String lst_vlr_apo = "TxtValorCalculoAporte";

    //Datos estáticos en llamadas Cobol de Beneficios
    public static String ben_str_formapago_caja = "C";
    public static String ben_str_tipopago_efectivo = "E";
    public static String ben_str_emitefactura_no = "N";
    public static String ben_str_codigorea_ind = "051";
    public static int ben_int_tipocomprobante_egreso = 1;
    public static int ben_int_tipocomprobante_ingreso = 2;

}
