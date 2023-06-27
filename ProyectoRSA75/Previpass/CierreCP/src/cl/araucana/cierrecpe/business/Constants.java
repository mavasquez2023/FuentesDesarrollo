

/*
 * @(#) Constants.java    1.0 24-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.business;


/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 24-04-2008 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author  Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public interface Constants  {
	/*
	 * Código TIPO SECCION
	 */
	public static final int REMU_AFP=1;
	public static final int REMU_ISAPRE=2;
	public static final int REMU_IPS=3;
	public static final int REMU_MUTUAL=4;
	public static final int REMU_CCAF=5;
	public static final int REMU_APV=6;
	public static final int REMU_APVC=7;
	public static final int GRATI_AFP=40;
	public static final int GRATI_ISAPRE=41;
	public static final int GRATI_IPS=42;
	public static final int GRATI_MUTUAL=43;
	public static final int GRATI_CCAF=44;
	public static final int RELIQ_AFP=20;
	public static final int RELIQ_ISAPRE=21;
	public static final int RELIQ_IPS=22;
	public static final int RELIQ_MUTUAL=23;
	public static final int RELIQ_CCAF=24;
	public static final int DEP_CONV_AFP=60;
	public static final int AFP_TP=99;
	public static final int REMU_AFBR=8;
	public static final int RELIQ_AFBR=25;
	
	/*
	 * Cantidad de líneas página detalle planillas
	 */
	public static final int NUMLINEPAGE=40;
	public static final int NUMLINEPAGE_AFBR=30;
	public static final int NUMLINEPAGE_INP=15;
	
	
	public static final int FACTOR_RANGO_FOLIO=10;

	/*
	 * Tablas de Publicación, cabecera y detalle
	 */
	public String[] tablasPublicacion = {
			"PWF0000",
			"PWF1000",
			"PWF1100",
			"PWF2000",
			"PWF2100",
			"PWF2300",
			"PWF2400",
			"PWF2600",
			"PWF2700",
			"PWF2900",
			"PWF2901",
			"PWF3000",
			"PWF3100",
			"PWF4700",
			"PWF4800",
			"PWF4900",
			"PWF5000",
			"PWF5100",
			"PWF5200",
			"PWF7100",
			"PWF7200",
			"PWF7400",
			"PWF7600",
			"PWF7800",
			"PWF7900"
	};
	/*
	 * Tablas de Publicación, cabecera y detalle
	 */
	public String[] tablasPublicacionCLEAN = {
			"PWF0000",
			"PWF1000",
			"PWF1100",
			"PWF2000",
			"PWF2100",
			"PWF2001",
			"PWF2101",
			"PWF2300",
			"PWF2400",
			"PWF2600",
			"PWF2700",
			"PWF2900",
			"PWF2901",
			"PWF3000",
			"PWF3100",
			"PWF4700",
			"PWF4800",
			"PWF4900",
			"PWF4701",
			"PWF4801",
			"PWF4901",
			"PWF5000",
			"PWF5100",
			"PWF5200",
			"PWF7100",
			"PWF7200",
			"PWF7400",
			"PWF7600",
			"PWF7800",
			"PWF7900"
	};
	
	public String[] tablasPublicacionDNP_FROM = {
			"PWF2001",
			"PWF2101",
			"PWF4701",
			"PWF4801",
			"PWF4901"
	};
	public String[] tablasPublicacionDNP_TO = {
			"PWF2000",
			"PWF2100",
			"PWF4700",
			"PWF4800",
			"PWF4900"
	};

	/*
	 * FILTERS CONSTANTS FOR APV
	 */
	public static final int FILTER_APV_IND = 0;	// APV Individual.
	public static final int FILTER_APV_COL = 1; // APV Colectivo.
	
	/*
	 * FILTERS CONSTANTS FOR AFP
	 */
	public static final int FILTER_AFP_DYP = 0;	// Declaración Y Pago.
	public static final int FILTER_AFP_DNP = 1; // Declaración y No Pago.
	public static final int FILTER_AFP_SIL = 2; // SIL.
	public static final int FILTER_AFP_AFV = 3; // Afiliados Voluntarios.
	public static final int FILTER_INDEPENDIENTE_OBL = 4; // Independiente Obligatorio.
	public static final int FILTER_INDEPENDIENTE_VOL = 5; // Independiente Voluntario.
	public static final String FILTER_NOMBRE_AFC = "ADMINISTRADORA FONDO DE CESANTIA";
	/*
	 * FILTERS CONSTANTS FOR INP DNP
	 */
	public static final int FILTER_INP_DYP = 0;	// Declaración Y Pago.
	public static final int FILTER_INP_DNP = 1; // Declaración y No Pago.
	public static final int FILTER_INP_DNP_PAGADA = 2; // DNP Pagada.
	
	/*
	 * FILTERS CONSTANTS FOR MUTUAL
	 */
	public static final int FILTER_MUTUAL_DYP = 0;	// Declaración Y Pago.
	public static final int FILTER_MUTUAL_DNP = 1; // Declaración y No Pago.
	public static final String INP_MUTUAL= "INP (MUTUAL)";
	
	/*
	 * FILTERS CONSTANTS para Tipo Pago 
	 */
	public static final String PAGO_NORMAL= "100";
	public static final String PAGO_ATRASADA= "010";
	public static final String PAGO_ADELANTADA= "001";
	
	/*
	 * FILTERS CONSTANTS y TASAS para CAJA
	 */
	public static final double TASA_NO_AFILIADO_ISAPRE= 0.6;
	public static final double TASA_AFILIADO_ISAPRE= 0.0;
	public static final String FILTER_CCAF_ADHERIDO_MUTUAL= "10";
	public static final String FILTER_CCAF_NO_ADHERIDO_MUTUAL= "01";
	public static final int CCAF_ATTACHMENT_ANEXOS        	= 0;
	public static final int CCAF_ATTACHMENT_CREDITO         = 1;
	public static final int CCAF_ATTACHMENT_LEASING         = 2;
	public static final int CCAF_ATTACHMENT_CONVENIO_DENTAL = 3;
	public static final int CCAF_ATTACHMENT_SEGURO_VIDA     = 4;
	
	/*
	 * CODIGO SEXO
	 */
	public static final int MASCULINO=1;
	public static final int FEMENINO=2;
	/*
	 * CODIGO TRAMO
	 */
	public static final char TRAMO_A='A';
	public static final char TRAMO_B='B';
	public static final char TRAMO_C='C';
	public static final char TRAMO_D='D';
	
	/*
	 * Porcentaje máximo de comprobantes sin Planillas en generación para abortar proceso 
	 */
	public static final int PORCENTAJE_MAX_COMPROBANTES_SIN_PLANILLAS=20;
	
	 /* CODIGO PAGO ENTIDAD y SECCION
	 */
	public static final int ENTIDAD_PAGADA=1;
	public static final int ENTIDAD_DECLARADA=2;
	public static final int ENTIDAD_NO_PAGADA=3;
	public static final int SECCION_PAGADA=1;
	public static final int SECCION_DECLARADA=2;
	public static final int SECCION_NO_PAGADA=3;
	public static final int SECCION_PAGOINDEFINIDO=0;
	
	/*
	 * FORMAS DE PAGO
	 */
	public static final int FORMAPAGO_SPL_EMPRESA=1;
	public static final int FORMAPAGO_MIXTA_EMPRESA=2;
	public static final int FORMAPAGO_SPL_INDEPENDIENTE=4;
	public static final int FORMAPAGO_MIXTA_INDEPENDIENTE=3;
	public static final char FORMAPAGO_CAJA='C';
	public static final char FORMAPAGO_SPL='P';
	public static final char FORMAPAGO_BOLETA='B';
	/*
	 * TIPO DEPOSITO
	 */
	public static final String DEPOSITO_CHEQUE="CHEQUE";
	public static final String DEPOSITO_TRANSFERENCIA="TRANSFERENCIA";
	public static final String DEPOSITO_TRANSFERENCIA_ABREV="TRANSF.";
	
	/*
	 * TIPO CLIENTE
	 */
	public static final String TIPO_CLIENTE_EMPRESA="EMPRESA";
	public static final String TIPO_CLIENTE_INDEPENDIENTE="INDEPENDIENTE";
	
	
	/*
	 * TIPO ENTIDAD CERTIFICADOS TRABAJADOR 
	 */
	public static final String TIPO_ENTIDAD_AFP_OBL="P";
	public static final String TIPO_ENTIDAD_AFP_CTA_AHO="A";
	public static final String TIPO_ENTIDAD_AFP_SIS="I";
	public static final String TIPO_ENTIDAD_AFP_TP="K";
	public static final String TIPO_ENTIDAD_ISA_OBL="S";
	public static final String TIPO_ENTIDAD_ISA_ADI="J";
	public static final String TIPO_ENTIDAD_AFC_TRA="X";
	public static final String TIPO_ENTIDAD_AFC_EMP="Y";
	public static final String TIPO_ENTIDAD_IPS="P";
	public static final String TIPO_ENTIDAD_DESC_IPSPEN="(Pago Pensión)";
	public static final String TIPO_ENTIDAD_DESC_IPSFON="(Pago Salud FONASA)";
	public static final String TIPO_ENTIDAD_DESC_IPSMUT="(Pago Mutual)";
	public static final String TIPO_ENTIDAD_DESC_IPSCAJA="(Caja)";
	public static final String TIPO_ENTIDAD_DESC_IPSDES="(Pago Desahucio)";
	public static final String TIPO_ENTIDAD_DESC_IPSREB="(Rebaja Ley 15386)";
	public static final String TIPO_ENTIDAD_IPS_DES="E";
	public static final String TIPO_ENTIDAD_IPS_REB="F";
	public static final String TIPO_ENTIDAD_DIAS="H";
	public static final String TIPO_ENTIDAD_FON="S";
	public static final String TIPO_ENTIDAD_MUT="M";
	public static final String TIPO_ENTIDAD_CAJA_AFA="C";
	public static final String TIPO_ENTIDAD_CAJA_CRE="R";
	public static final String TIPO_ENTIDAD_CAJA_LEA="L";
	public static final String TIPO_ENTIDAD_CAJA_06="O";
	public static final String TIPO_ENTIDAD_CAJA_DEN="D";
	public static final String TIPO_ENTIDAD_CAJA_VID="V";
	public static final String TIPO_ENTIDAD_CAJA_RET="T";
	public static final String TIPO_ENTIDAD_CAJA_REI="U";
	public static final String TIPO_ENTIDAD_AFP_COT_VOL="E";
	public static final String TIPO_ENTIDAD_AFP_AHO_VOL="F";
	public static final String TIPO_ENTIDAD_APV1="1";
	public static final String TIPO_ENTIDAD_APV2="2";
	public static final String TIPO_ENTIDAD_APV3="3";
	public static final String TIPO_ENTIDAD_APV4="4";
	public static final String TIPO_ENTIDAD_APV5="5";
	public static final String TIPO_ENTIDAD_APVC_TRA="A";
	public static final String TIPO_ENTIDAD_APVC_EMP="B";
	public static final String TIPO_ENTIDAD_DEP_CONV="D";
	public static final String TIPO_ENTIDAD_DEP_CONV_APO_IND="I";
	public static final String TIPO_ENTIDAD_MOVPER="1";
	public static final String TIPO_ENTIDAD_AFBR="B";
	
	//SECCIONES
	public static final String SECCION_INP="INP";
	public static final String SECCION_AFP="AFP";
	public static final String SECCION_MUTUAL="MUTUAL";
	public static final String SECCION_CAJA="CAJA";
	public static final String SECCION_AFBR="OTROS";
	public static final String CONCEPTO_INP_PENSION="Pensión";
	public static final String CONCEPTO_INP_FONASA="Fonasa";
	public static final String CONCEPTO_INP_ACCIDENTE="Accidente del Trabajo";
	public static final String CONCEPTO_INP_ASFAM="Asignación Familiar";
	public static final String CONCEPTO_CAJA_ASFAM="Asignación Familiar";
	public static final String CONCEPTO_CAJA_APORTE="0.6%";
	public static final String CONCEPTO_CAJA_SFI="S.F.I.";
	public static final String CONCEPTO_CAJA_SFE="S.F.E.";
	public static final String CONCEPTO_CAJA_CREDITO="Créditos";
	public static final String CONCEPTO_CAJA_LEASING="Leasing";
	public static final String CONCEPTO_CAJA_VIDA="Seguros de Vida";
	public static final String CONCEPTO_CAJA_DENTAL="Convenios Dentales";
	
	//Descripción INP en Tabla Comprobante de Pago
	public static final String SECCION_INP_PWF5000="IPS";
	public static final String SECCION_CAJA_PWF5000="CCAF";
}
