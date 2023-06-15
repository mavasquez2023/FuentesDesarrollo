package cl.araucana.autoconsulta.common;

/**
 * @author asepulveda
 * Contiene los valores de los códigos utilizados en la aplicación
 */
public class Constants {

	// Código parentesco C=Conyuge
	public static final String COD_PARENTESCO_CONYUGE="C";
	
	// Alias utilizados en los query
	public static final String ALIAS_DATE="DATE";
	public static final String ALIAS_DOUBLE="DOUBLE";
	public static final String ALIAS_INT="INT";
	public static final String ALIAS_STRING="STRING";
	public static final String ALIAS_CODIGO="CODIGO";
	public static final String ALIAS_DESCRIPCION="DESCRIPCION";

	//Estados de una licencia
	public static final String STD_LICENCIA_EN_PROCESO="0";	
	public static final String STD_LICENCIA_AUTORIZADA="1";
	public static final String STD_LICENCIA_PENDIENTE="2";
	public static final String STD_LICENCIA_SIN_DERECHO="3";
	public static final String STD_LICENCIA_OBJETADA="4";
	public static final String STD_LICENCIA_RECHAZADA_COMPIN="5";
	public static final String STD_LICENCIA_CADUCADA="8";
	
	public static final String TXT_LICENCIA_ANTES_COMPIN = "En curso";
	public static final String FECHA_ENVIO_COMPIN_NO_RM = "No disponible para regiones";
	
	public static final String COD_REGION_METROPOLITANA = "13";
	
	
	// Si tiene seguro de cesantia
	public static final String SI_TIENE_SEGURO_CESANTIA="S";
	
	// Estado de pago de la carga
	public static final String STD_PAGO_CARGA_NO="N";
	
	
	// Valores utilizados para saber si tiene Cargas Familiares y de que tipo,
	// además se utiliza para saber que tipo de consulta esta solicitando
	public static final int CARGAS_NO=0;
	public static final int CARGAS_ACTIVAS=1;
	public static final int CARGAS_INACTIVAS=2;
	public static final int CARGAS_ACTIVAS_INACTIVAS=3;
	
	// Tipo de respuesta en la autenticacion
	public static final int AUT_CLAVE_NO_TIENE=0; // No tiene clave registrada
	public static final int AUT_CLAVE_INCORRECTA=1; // La clave ingresada es incorrecta
	public static final int AUT_CLAVE_BLOQUEADA=2; // La clave se encuentra bloqueda
	public static final int AUT_CLAVE_INICIAL_CORRECTA=3; // Autenticado OK contra clave inicial
	public static final int AUT_CLAVE_PERSONAL_CORRECTA=4; // Autenticado OK contra clave personal
	
	// Tipo de certificados Generados
	public static final int CERTIFICADO_ASFMAM=0; // Asignacion Familiar
	public static final int CERTIFICADO_LICMED=1; // Licencias Medicas
	public static final int CERTIFICADO_DEUVIG=2; // Deuda Vigente
	
	// Tipo de usuario
	public static final int USUARIO_EMPRESA=0; // Usuario Empresa
	public static final int USUARIO_PERSONA=1; // Usario Persona
	
	//INGRESO DATOS SOLICITUD DE CREDITO
	public static final String DATOS_SC_INGRESOS_LIQUIDOS="0";
	public static final String DATOS_SC_MONTO_SOLICITADO="1";
	public static final String DATOS_SC_CANTIDAD_CUOTAS="2";
	public static final String DATOS_SC_FECHA_NACIMIENTO="3";
	public static final String DATOS_SC_FECHA_INGRESO_EMPRESA="4";
	public static final String DATOS_SC_SEGUROS="seguros";
	public static final String DATOS_SC_CREDITOS="creditos";
	public static final String DATOS_SC_RESULTADO="resultado";
		
}
