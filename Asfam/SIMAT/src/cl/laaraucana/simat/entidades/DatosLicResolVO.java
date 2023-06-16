package cl.laaraucana.simat.entidades;

import java.sql.Date;

public class DatosLicResolVO {
	private int id;
	/*
	 * Corresponde al mes del Informe Financiero.
	 */
	private String mes_informacion;
	/*
	 * C�digo del tipo de subsidio seg�n codificaci�n en Anexo n� 3.
	 */
	private int codigo_entidad;
	/*
	 * N�mero de folio completo de la licencia m�dica en caso que el tipo de subsidio 
	 * corresponda a prenatal, postnatal o enfermedad grave del ni�o menor de un a�o. 
	 * El formato requerido es: c�digo de formulario de licencia m�dica (indicado en Anexo n� 3), 
	 * gu�on y n�mero de la licencia sin d�gito verificador, incluyendo ceros despu�s del 
	 * gu�on, en caso que el n�mero tenga un largo inferior a 12, para completar el largo 
	 * del campo. Ej: "1-000019980551"'
	 */
	private String nro_licencia;
	/*
	 * Corresponde indicar si una licencia previamente informada como rechazada fue 
	 * reconsiderada y aprobada, seg�n codificaci�n de Anexo n� 3.
	 */
	private int reconsideracion;
	/*
	 * C�dula de Identidad del beneficiario, con d�gito verificador.
	 */
	private String run_beneficiario;
	/*
	 * Nombres y apellidos del beneficiario.
	 */
	private String nombre_beneficiario;
	/*
	 * C�digo del v�nculo que posee el beneficiario con el menor seg�n codificaci�n de 
	 * Anexo n� 3. En caso de corresponder a un subsidio por reposo prenatal, corresponder� 
	 * informar el c�digo de dominio para Madre.
	 */
	private int vinculo_beneficiario_menor;
	/*
	 * 'Edad del beneficiario (indicado en la secci�n A del Formulario de Licencias M�dicas).
	 */
	private int edad;
	/*
	 * Sexo del beneficiario (indicado en la secci�n A del Formulario de Licencias M�dicas), 
	 * seg�n codificaci�n  en Anexo n� 3.
	 */
	private String sexo;
	/*
	 * Corresponde registrar el c�digo de la actividad laboral del trabajador, indicado en 
	 * la secci�n C del Formulario de Licencias M�dicas, de acuerdo a la codificaci�n en 
	 * Anexo n� 3.
	 */
	private String actividad_laboral_trabajador;
	/*
	 * N�mero de ni�os nacidos en el parto utilizado para el c�lculo de d�as autorizados de la 
	 * licencia postnatal. En caso de corresponder a un subsidio prenatal o por enfermedad 
	 * grave del ni�o menor de un a�o se deber� indicar 0.
	 */
	private int nro_nacimientos;
	/*
	 * Fecha de emisi�n de la licencia m�dica indicada en la secci�n A del Formulario de 
	 * Licencias M�dicas.
	 */
	private Date fecha_emision_licencia;

	private String fecha_emision_licencia_Char;
	/*
	 * Fecha de inicio la incapacidad laboral (indicado en la secci�n B del Formulario de 
	 * Licencias M�dicas).
	 */
	private Date fecha_inicio_reposo;

	private String fecha_inicio_reposo_Char;

	/*
	 * Fecha de t�rmino de la incapacidad laboral (indicado en la secci�n B del Formulario 
	 * de Licencias M�dicas).
	 */
	private Date fecha_termino_reposo;

	private String fecha_termino_reposo_Char;
	/*
	 * N�mero de d�as de duraci�n de la licencia m�dica (secci�n A del Formulario de Licencias 
	 * M�dicas).
	 */
	private int num_dias_licencia;
	/*
	 * N�mero total de d�as autorizados de la licencia (indicados en la secci�n B del Formulario 
	 * de Licencias M�dicas).
	 */
	private int num_dias_licencia_autorizados;
	/*
	 * N�mero total de d�as  rechazados de la licencia.
	 */
	private int num_dias_licencia_rechazados;
	/*
	 * Corresponde registrar la c�dula de identidad del menor enfermo, con d�gito verificador. 
	 * En caso de corresponder a una licencia prenatal o postnatal se deber� dejar vac�o.
	 */
	private String run_menor_enfermo;
	/*
	 * Nombres y apellidos del ni�o menor enfermo. En caso de corresponder a una licencia 
	 * prenatal o postnatal se deber� dejar vac�o.
	 */
	private String nombre_menor_enfermo;
	/*
	 * Fecha nacimiento del ni�o menor enfermo. En caso de corresponder a una licencia 
	 * prenatal o postnatal se deber� dejar vac�o.
	 */
	private Date fecha_nac_menor_enfermo;

	private String fecha_nac_menor_enfermo_Char;
	/*
	 * C�digo de la comuna de residencia del beneficiario seg�n codificaci�n de Anexo n� 3.
	 */
	private String cod_comuna_beneficiario;
	/*
	 * C�dula de identidad del profesional que otorg� la licencia m�dica, con d�gito 
	 * verificador.
	 */
	private String run_profesional;
	/*
	 * Nombres y apellidos del m�dico o matrona.
	 */
	private String nombre_profesional;
	/*
	 * Registro del colegio profesional indicado en la secci�n A.5  del Formulario de 
	 * Licencias M�dicas.
	 */
	private String registro_colegio_profesional;
	/*
	 * C�digo del tipo de licencia indicado en la secci�n A.3 del Formulario de Licencias 
	 * M�dicas, seg�n codificaci�n Anexo n� 3.
	 */
	private int cod_tipo_licencia;
	/*
	 * C�digo correspondiente seg�n la Clasificaci�n Estad�stica Internacional de enfermedades 
	 * y problemas relacionados con la salud (CIO 10). Para el caso de la licencia m�dica 
	 * prenatal, el c�digo ser� 650 y para el postnatal 1650.
	 */
	private String codigo_diagnostico;
	/*
	 * Rol �nico Tributario del empleador con d�gito verificador.
	 */
	private String rut_empleador;
	/*
	 * Nombre o raz�n social del empleador.
	 */
	private String nombre_empleador;
	/*
	 * C�digo de la calidad del trabajador indicado en la secci�n C del Formulario de 
	 * Licencias M�dicas, seg�n codificaci�n en Anexo n� 3.
	 */
	private int calidad_trabajador;
	/*
	 * C�digo del estado de la tramitaci�n de la licencia c�dica seg�n codificaci�n de 
	 * Anexo n� 3.
	 */
	private int estado_tramitacion;
	/*
	 * C�digo de la causa de rechazo de la licencia m�dica en caso que aplique, seg�n 
	 * codificaci�n de Anexo n� 3.
	 */
	private int causal_rechazo_licencia;

	/*
	 * key para poder mostrar cantidad de registros limitados
	 * */
	private int keyInicio;
	private int keyFin;
	private int paginacion;

	public DatosLicResolVO() {
	}

	public DatosLicResolVO(int id, String mes_informacion, int codigo_entidad, String nro_licencia, int reconsideracion, String run_beneficiario, String nombre_beneficiario,
			int vinculo_beneficiario_menor, int edad, String sexo, String actividad_laboral_trabajador, int nro_nacimientos, Date fecha_emision_licencia, Date fecha_inicio_reposo,
			Date fecha_termino_reposo, int num_dias_licencia, int num_dias_licencia_autorizados, int num_dias_licencia_rechazados, String run_menor_enfermo, String nombre_menor_enfermo,
			Date fecha_nac_menor_enfermo, String cod_comuna_beneficiario, String run_profesional, String nombre_profesional, String registro_colegio_profesional, int cod_tipo_licencia,
			String codigo_diagnostico, String rut_empleador, String nombre_empleador, int calidad_trabajador, int estado_tramitacion, int causal_rechazo_licencia) {
		super();
		this.id = id;
		this.mes_informacion = mes_informacion;
		this.codigo_entidad = codigo_entidad;
		this.nro_licencia = nro_licencia;
		this.reconsideracion = reconsideracion;
		this.run_beneficiario = run_beneficiario;
		this.nombre_beneficiario = nombre_beneficiario;
		this.vinculo_beneficiario_menor = vinculo_beneficiario_menor;
		this.edad = edad;
		this.sexo = sexo;
		this.actividad_laboral_trabajador = actividad_laboral_trabajador;
		this.nro_nacimientos = nro_nacimientos;
		this.fecha_emision_licencia = fecha_emision_licencia;
		this.fecha_inicio_reposo = fecha_inicio_reposo;
		this.fecha_termino_reposo = fecha_termino_reposo;
		this.num_dias_licencia = num_dias_licencia;
		this.num_dias_licencia_autorizados = num_dias_licencia_autorizados;
		this.num_dias_licencia_rechazados = num_dias_licencia_rechazados;
		this.run_menor_enfermo = run_menor_enfermo;
		this.nombre_menor_enfermo = nombre_menor_enfermo;
		this.fecha_nac_menor_enfermo = fecha_nac_menor_enfermo;
		this.cod_comuna_beneficiario = cod_comuna_beneficiario;
		this.run_profesional = run_profesional;
		this.nombre_profesional = nombre_profesional;
		this.registro_colegio_profesional = registro_colegio_profesional;
		this.cod_tipo_licencia = cod_tipo_licencia;
		this.codigo_diagnostico = codigo_diagnostico;
		this.rut_empleador = rut_empleador;
		this.nombre_empleador = nombre_empleador;
		this.calidad_trabajador = calidad_trabajador;
		this.estado_tramitacion = estado_tramitacion;
		this.causal_rechazo_licencia = causal_rechazo_licencia;
	}

	//----------------------------------------------------------
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

	public int getCausal_rechazo_licencia() {
		return causal_rechazo_licencia;
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

	public int getEstado_tramitacion() {
		return estado_tramitacion;
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

	public String getNro_licencia() {
		return nro_licencia;
	}

	public int getNro_nacimientos() {
		return nro_nacimientos;
	}

	public int getNum_dias_licencia() {
		return num_dias_licencia;
	}

	public int getNum_dias_licencia_autorizados() {
		return num_dias_licencia_autorizados;
	}

	public int getNum_dias_licencia_rechazados() {
		return num_dias_licencia_rechazados;
	}

	public int getReconsideracion() {
		return reconsideracion;
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

	public int getVinculo_beneficiario_menor() {
		return vinculo_beneficiario_menor;
	}

	public void setCalidad_trabajador(int calidad_trabajador) {
		this.calidad_trabajador = calidad_trabajador;
	}

	public void setCausal_rechazo_licencia(int causal_rechazo_licencia) {
		this.causal_rechazo_licencia = causal_rechazo_licencia;
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

	public void setEstado_tramitacion(int estado_tramitacion) {
		this.estado_tramitacion = estado_tramitacion;
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

	public void setNro_licencia(String nro_licencia) {
		this.nro_licencia = nro_licencia.trim();
	}

	public void setNro_nacimientos(int nro_nacimientos) {
		this.nro_nacimientos = nro_nacimientos;
	}

	public void setNum_dias_licencia(int num_dias_licencia) {
		this.num_dias_licencia = num_dias_licencia;
	}

	public void setNum_dias_licencia_autorizados(int num_dias_licencia_autorizados) {
		this.num_dias_licencia_autorizados = num_dias_licencia_autorizados;
	}

	public void setNum_dias_licencia_rechazados(int num_dias_licencia_rechazados) {
		this.num_dias_licencia_rechazados = num_dias_licencia_rechazados;
	}

	public void setReconsideracion(int reconsideracion) {
		this.reconsideracion = reconsideracion;
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
		this.rut_empleador = rut_empleador;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo.trim();
	}

	public void setVinculo_beneficiario_menor(int vinculo_beneficiario_menor) {
		this.vinculo_beneficiario_menor = vinculo_beneficiario_menor;
	}

	public int getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(int paginacion) {
		this.paginacion = paginacion;
	}

	public String getActividad_laboral_trabajador() {
		return actividad_laboral_trabajador;
	}

	public String getCod_comuna_beneficiario() {
		return cod_comuna_beneficiario;
	}

	public void setActividad_laboral_trabajador(String actividad_laboral_trabajador) {
		this.actividad_laboral_trabajador = actividad_laboral_trabajador;
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
