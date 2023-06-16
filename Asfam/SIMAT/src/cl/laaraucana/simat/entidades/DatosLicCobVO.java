package cl.laaraucana.simat.entidades;

import java.sql.Date;

public class DatosLicCobVO {

	private int id;
	/*
	 * Corresponde al mes del Informe Financiero.
	 */
	private String mes_informacion;
	/*
	 *Se debe indicar el código asociado a la entidad pagadora de subsidios, de acuerdo a la 
	 *codificación del Anexo n° 3.
	 */
	private int codigo_entidad;
	/*
	 * En este campo se indicará el número de folio completo de la licencia, el mismo 
	 * utilizado en el archivo plano n° 2. El formato requerido es: código de formulario de 
	 * licencia médica {indicado Anexo n° 3}, guíon y el número de la licencia sin dígito 
	 * verificador, incluyendo ceros después del guíon, en caso que el número tenga un largo 
	 * inferior a 12, para completar el largo del campo.
	 */
	private String nro_licencia;
	/*
	 * Corresponde registrar la cédula de identidad del beneficiario, con dígito verificador.
	 */
	private String run_beneficiario;
	/*
	 * Se debe registrar los nombres y apellidos del beneficiario.
	 */
	private String nombre_beneficiario;
	/*
	 * Edad del beneficiario.
	 */
	private int edad;
	/*
	 * Sexo del beneficiario, según copdificación en Anexo n° 3.
	 */
	private String sexo;
	/*
	 * Fecha emisión de la licencia médica. El formato a utilizar es AAAAMMDD.
	 */
	private Date fecha_emision_licencia;

	private String fecha_emision_licencia_Char;
	/*
	 * En este campo se debe anotar la fecha eb que se inicia la incapacidad laboral 
	 * (los indicados en la sección B del Formulario de Licencias Médicas).
	 */
	private Date fecha_inicio_reposo;

	private String fecha_inicio_reposo_Char;
	/*
	 * En este campo se debe anotar la fecha en que se término la incapacidad laboral 
	 * (los indicados en la sección B del Formulario de Licencias Médicas).
	 */
	private Date fecha_termino_reposo;

	private String fecha_termino_reposo_Char;
	/*
	 * Número de días de duración de la licencia médica.
	 */
	private int nro_dias_licencia;
	/*
	 * Se debe indicar el número total de días autorizados de la licencia (los indicados en 
	 * la sección B del Formulario de Licencias Médicas).
	 */
	private int num_dias_licencia_autorizados;
	/*
	 * Corresponde registrar la cédula de identidad del menor enfermo, con dígito verificador. 
	 * En caso de corresponder a una licencia prenatal o postnatal se deberá dejar vacío.
	 */
	private String run_menor_enfermo;
	/*
	 * Nombres y apellidos del niño menor enfermo. En caso de corresponder a una licencia 
	 * prenatal o postnatal se deberá dejar vacío.
	 */
	private String nombre_menor_enfermo;
	/*
	 * Fecha de nacimiento del niño menor enfermo. En caso de corresponder a una licencia 
	 * prenatal o postnatal se deberá dejar vacío.
	 */
	private Date fecha_nac_menor_enfermo;

	private String fecha_nac_menor_enfermo_Char;

	/*
	 * Codificación del tipo de la licencia médica: código 3 para las licencias prenatales y 
	 * postnatales y código 4 para las licencias por permiso por enfermedad grave del niño 
	 * menor de un año.
	 */
	private int cod_tipo_licencia;
	/*
	 * Se debe indicar en este campo la comuna de residencia del beneficiario. Las 
	 * codificaciones para este efecto se señalan en el Anexo n° 3.
	 */
	private String cod_comuna_beneficiario;
	/*
	 * Corresponde registrar la cédula de identidad del profesional que otorgó la licencia 
	 * médica, con dígito verificador.
	 */
	private String run_profesional;
	/*
	 * Se debe registrar los nombres y apellidos del médico o matrona.
	 */
	private String nombre_profesional;
	/*
	 * Se debe indicar el registro del colegio profesional indicado en la sección A.6 del 
	 * Formulario de Licencias Médicas.
	 */
	private String registro_colegio_profesional;
	/*
	 * Se debe registrar el código correspondiente según la Clasificación Estadística 
	 * Internacional de enfermedades y problemas relacionados con la salud (CIE10). Para el 
	 * caso de la licencia médica prenatal, el código será 650 y para el postnatal 1650.
	 */
	private String codigo_diagnostico;
	/*
	 * Corresponde al Rol Único Tributario del empleador con el dígito verificador.
	 */
	private String rut_empleador;
	/*
	 * Se debe registrar el nombre o razón social del empleador.
	 */
	private String nombre_empleador;
	/*
	 * Se debe indicar la capacidad del trabajador de acuerdo a las codificaciones en el 
	 * Anexo n° 3.
	 */
	private int calidad_trabajador;

	/*
	 * key para poder mostrar cantidad de registros limitados
	 * */
	private int keyInicio;
	private int keyFin;
	private int paginacion;

	public DatosLicCobVO() {
	}

	public DatosLicCobVO(int id, String mes_informacion, int codigo_entidad, String nro_licencia, String run_beneficiario, String nombre_beneficiario, int edad, String sexo,
			Date fecha_emision_licencia, Date fecha_inicio_reposo, Date fecha_termino_reposo, int nro_dias_licencia, int num_dias_licencia_autorizados, String run_menor_enfermo,
			String nombre_menor_enfermo, Date fecha_nac_menor_enfermo, int cod_tipo_licencia, String cod_comuna_beneficiario, String run_profesional, String nombre_profesional,
			String registro_colegio_profesional, String codigo_diagnostico, String rut_empleador, String nombre_empleador, int calidad_trabajador) {
		super();
		this.id = id;
		this.mes_informacion = mes_informacion;
		this.codigo_entidad = codigo_entidad;
		this.nro_licencia = nro_licencia;
		this.run_beneficiario = run_beneficiario;
		this.nombre_beneficiario = nombre_beneficiario;
		this.edad = edad;
		this.sexo = sexo;
		this.fecha_emision_licencia = fecha_emision_licencia;
		this.fecha_inicio_reposo = fecha_inicio_reposo;
		this.fecha_termino_reposo = fecha_termino_reposo;
		this.nro_dias_licencia = nro_dias_licencia;
		this.num_dias_licencia_autorizados = num_dias_licencia_autorizados;
		this.run_menor_enfermo = run_menor_enfermo;
		this.nombre_menor_enfermo = nombre_menor_enfermo;
		this.fecha_nac_menor_enfermo = fecha_nac_menor_enfermo;
		this.cod_tipo_licencia = cod_tipo_licencia;
		this.cod_comuna_beneficiario = cod_comuna_beneficiario;
		this.run_profesional = run_profesional;
		this.nombre_profesional = nombre_profesional;
		this.registro_colegio_profesional = registro_colegio_profesional;
		this.codigo_diagnostico = codigo_diagnostico;
		this.rut_empleador = rut_empleador;
		this.nombre_empleador = nombre_empleador;
		this.calidad_trabajador = calidad_trabajador;
	}

	//	----------------------------------------------------------
	public int getKeyFin() {
		return keyFin;
	}

	public int getKeyInicio() {
		return keyInicio;
	}

	public void setKeyFin(int keyFin) {
		this.keyFin = keyFin;
	}

	public void setKeyInicio(int keyInicio) {
		this.keyInicio = keyInicio;
	}

	//-----------------------------------------------------------

	public String getId() {
		return String.valueOf(id);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCalidad_trabajador() {
		return calidad_trabajador;
	}

	public int getCod_tipo_licencia() {
		return cod_tipo_licencia;
	}

	public String getCodigo_diagnostico() {
		return codigo_diagnostico;
	}

	public int getCodigo_entidad() {
		return codigo_entidad;
	}

	public int getEdad() {
		return edad;
	}

	public Date getFecha_emision_licencia() {
		return fecha_emision_licencia;
	}

	public Date getFecha_inicio_reposo() {
		return fecha_inicio_reposo;
	}

	public Date getFecha_nac_menor_enfermo() {
		return fecha_nac_menor_enfermo;
	}

	public Date getFecha_termino_reposo() {
		return fecha_termino_reposo;
	}

	public String getMes_informacion() {
		return mes_informacion;
	}

	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}

	public String getNombre_empleador() {
		return nombre_empleador;
	}

	public String getNombre_menor_enfermo() {
		return nombre_menor_enfermo;
	}

	public String getNombre_profesional() {
		return nombre_profesional;
	}

	public int getNro_dias_licencia() {
		return nro_dias_licencia;
	}

	public String getNro_licencia() {
		return nro_licencia;
	}

	public int getNum_dias_licencia_autorizados() {
		return num_dias_licencia_autorizados;
	}

	public String getRegistro_colegio_profesional() {
		return registro_colegio_profesional;
	}

	public String getRun_beneficiario() {
		return run_beneficiario;
	}

	public String getRun_menor_enfermo() {
		return run_menor_enfermo;
	}

	public String getRun_profesional() {
		return run_profesional;
	}

	public String getRut_empleador() {
		return rut_empleador;
	}

	public String getSexo() {
		return sexo;
	}

	public void setCalidad_trabajador(int calidad_trabajador) {
		this.calidad_trabajador = calidad_trabajador;
	}

	public void setCod_tipo_licencia(int cod_tipo_licencia) {
		this.cod_tipo_licencia = cod_tipo_licencia;
	}

	public void setCodigo_diagnostico(String codigo_diagnostico) {
		this.codigo_diagnostico = codigo_diagnostico.trim();
	}

	public void setCodigo_entidad(int codigo_entidad) {
		this.codigo_entidad = codigo_entidad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setFecha_emision_licencia(Date fecha_emision_licencia) {
		this.fecha_emision_licencia = fecha_emision_licencia;
	}

	public void setFecha_inicio_reposo(Date fecha_inicio_reposo) {
		this.fecha_inicio_reposo = fecha_inicio_reposo;
	}

	public void setFecha_nac_menor_enfermo(Date fecha_nac_menor_enfermo) {
		this.fecha_nac_menor_enfermo = fecha_nac_menor_enfermo;
	}

	public void setFecha_termino_reposo(Date fecha_termino_reposo) {
		this.fecha_termino_reposo = fecha_termino_reposo;
	}

	public void setMes_informacion(String mes_informacion) {
		this.mes_informacion = mes_informacion.trim();
	}

	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario.trim();
	}

	public void setNombre_empleador(String nombre_empleador) {
		this.nombre_empleador = nombre_empleador.trim();
	}

	public void setNombre_menor_enfermo(String nombre_menor_enfermo) {
		this.nombre_menor_enfermo = nombre_menor_enfermo.trim();
	}

	public void setNombre_profesional(String nombre_profesional) {
		this.nombre_profesional = nombre_profesional.trim();
	}

	public void setNro_dias_licencia(int nro_dias_licencia) {
		this.nro_dias_licencia = nro_dias_licencia;
	}

	public void setNro_licencia(String nro_licencia) {
		this.nro_licencia = nro_licencia.trim();
	}

	public void setNum_dias_licencia_autorizados(int num_dias_licencia_autorizados) {
		this.num_dias_licencia_autorizados = num_dias_licencia_autorizados;
	}

	public void setRegistro_colegio_profesional(String registro_colegio_profesional) {
		this.registro_colegio_profesional = registro_colegio_profesional.trim();
	}

	public void setRun_beneficiario(String run_beneficiario) {
		this.run_beneficiario = run_beneficiario.trim();
	}

	public void setRun_menor_enfermo(String run_menor_enfermo) {
		this.run_menor_enfermo = run_menor_enfermo.trim();
	}

	public void setRun_profesional(String run_profesional) {
		this.run_profesional = run_profesional.trim();
	}

	public void setRut_empleador(String rut_empleador) {
		this.rut_empleador = rut_empleador.trim();
	}

	public void setSexo(String sexo) {
		this.sexo = sexo.trim();
	}

	public int getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(int paginacion) {
		this.paginacion = paginacion;
	}

	public String getCod_comuna_beneficiario() {
		return cod_comuna_beneficiario;
	}

	public void setCod_comuna_beneficiario(String cod_comuna_beneficiario) {
		this.cod_comuna_beneficiario = cod_comuna_beneficiario;
	}

	public String getFecha_emision_licencia_Char() {
		return fecha_emision_licencia_Char;
	}

	public String getFecha_inicio_reposo_Char() {
		return fecha_inicio_reposo_Char;
	}

	public String getFecha_nac_menor_enfermo_Char() {
		return fecha_nac_menor_enfermo_Char;
	}

	public String getFecha_termino_reposo_Char() {
		return fecha_termino_reposo_Char;
	}

	public void setFecha_emision_licencia_Char(String fecha_emision_licencia_Char) {
		this.fecha_emision_licencia_Char = fecha_emision_licencia_Char;
	}

	public void setFecha_inicio_reposo_Char(String fecha_inicio_reposo_Char) {
		this.fecha_inicio_reposo_Char = fecha_inicio_reposo_Char;
	}

	public void setFecha_nac_menor_enfermo_Char(String fecha_nac_menor_enfermo_Char) {
		this.fecha_nac_menor_enfermo_Char = fecha_nac_menor_enfermo_Char;
	}

	public void setFecha_termino_reposo_Char(String fecha_termino_reposo_Char) {
		this.fecha_termino_reposo_Char = fecha_termino_reposo_Char;
	}

}
