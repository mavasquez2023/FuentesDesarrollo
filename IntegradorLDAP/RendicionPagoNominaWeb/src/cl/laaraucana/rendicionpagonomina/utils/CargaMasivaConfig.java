package cl.laaraucana.rendicionpagonomina.utils;

import cl.laaraucana.rendicionpagonomina.vo.FormatoCargaVO;
import cl.laaraucana.rendicionpagonomina.vo.ColumnaFormatoVO;

public class CargaMasivaConfig {
	
	public static FormatoCargaVO getFormato24H() {
	
		FormatoCargaVO formato = FormatoCargaVO.createForPlainFile(CargaMasivaUtil.FORMATO_24H, null);
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("rut_afiliado", 0, 8) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("dv_rut_afiliado", 8, 9) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("apellido_paterno", 9, 24) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("apellido_materno", 24, 39) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("nombre", 39, 54) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("codigo_banco", 61, 64) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("numero_cuenta", 64, 84) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("codigo_forma_pago", 58, 61) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("monto", 103, 113) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("referencia_1", 90, 102) );
		
		return formato;
	}
	
	public static FormatoCargaVO getFormatoCredito() {
		
		FormatoCargaVO formato = FormatoCargaVO.createForCSVFile(CargaMasivaUtil.FORMATO_ONLINE, ";", null);
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("num_cuenta_cargo", 1) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("banco_destino", 2) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("rut_afiliado", 3) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("dv_afiliado", 4) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("nombre_afiliado", 5) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("monto_transferencia", 6) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("nro_factura", 7) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("nro_orden_compra", 8) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("tipo_pago", 9) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("correo_electronico", 11) );
		
		return formato;
	}
	
	public static FormatoCargaVO getFormatoBEstadoBase() {
		
		
		/*
				<columna alineamiento="derecha" data_ej="" header=" " largo="1" nombre="relleno" posicion="1" relleno="0" valor_default=""></columna>
				<columna alineamiento="derecha" data_ej="" header=" " largo="9" nombre="rut_afiliado" posicion="1" relleno="0" valor_default=""></columna>
				<columna alineamiento="derecha" data_ej="" header=" " largo="1" nombre="dv_afiliado" posicion="1" relleno="0" valor_default=""></columna>
				<columna alineamiento="izquierda" data_ej="" header=" " largo="30" nombre="nombre_afiliado" posicion="1" relleno=" " valor_default=""></columna>
				<columna alineamiento="izquierda" data_ej="" header=" " largo="15" nombre="apellido_paterno" posicion="1" relleno=" " valor_default=""></columna>
				<columna alineamiento="izquierda" data_ej="" header=" " largo="15" nombre="apellido_materno" posicion="1" relleno=" " valor_default=""></columna>
				<columna alineamiento="izquierda" data_ej="" header=" " largo="2" nombre="forma_pago" posicion="1" relleno=" " valor_default=""></columna>
				<columna alineamiento="derecha" data_ej="" header=" " largo="3" nombre="banco_afiliado" posicion="1" relleno="0" valor_default=""></columna>
				<columna alineamiento="derecha" data_ej="" header=" " largo="17" nombre="numero_cuenta" posicion="1" relleno="0" valor_default=""></columna>
				<columna alineamiento="derecha" data_ej="" header=" " largo="13" nombre="monto_pago" posicion="1" relleno="0" valor_default=""></columna>
				<columna alineamiento="izquierda" data_ej="" header=" " largo="40" nombre="email_afiliado" posicion="1" relleno=" " valor_default=""></columna>
				<columna alineamiento="derecha" data_ej="" header=" " largo="15" nombre="correlativo_detalle" posicion="1" relleno="0" valor_default=""></columna>
				<columna alineamiento="derecha" data_ej="" header=" " largo="3" nombre="sucursal_BES" posicion="1" relleno="0" valor_default=""></columna>
				<columna alineamiento="derecha" data_ej="" header=" " largo="2" nombre="sector_financiero" posicion="1" relleno="0" valor_default=""></columna>
				<columna alineamiento="izquierda" data_ej="" header=" " largo="456" nombre="blancos_456" posicion="1" relleno=" " valor_default=""></columna>
				<columna alineamiento="derecha" data_ej="" header=" " largo="4" nombre="codigo_seguridad" posicion="1" relleno="0" valor_default=""></columna>

		*/
		FormatoCargaVO formato = FormatoCargaVO.createForPlainFile(CargaMasivaUtil.FORMATO_BESTADO_BASE, null);
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("relleno", 			0, 1) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("rut_afiliado", 		1, 10) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("dv_afiliado", 		10, 11) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("nombre_afiliado", 	11, 41) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("apellido_paterno", 	41, 56) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("apellido_materno", 	56, 71) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("forma_pago", 			71, 73) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("banco_afiliado", 		73, 76) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("numero_cuenta", 		76, 93) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("monto_pago", 			93, 106) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("email_afiliado", 		106, 146) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("correlativo_detalle", 146, 161) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("sucursal_BES", 		161, 164) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("sector_financiero", 	164, 166) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("blancos_456", 		166, 621) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("codigo_seguridad", 	621, 625) );
		
		return formato;
	}
	
}
