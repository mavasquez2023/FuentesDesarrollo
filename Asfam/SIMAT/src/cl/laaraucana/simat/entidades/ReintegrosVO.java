package cl.laaraucana.simat.entidades;

public class ReintegrosVO {

	private int id;

	/*
	 * 'corresponde al mes del informe financiero'
	 * */
	private String mes_informacion;
	/*
	 * 'se debe indicar el codigo asociado a la entidad pagadora de subsidios, de acuerdo a la codificacion del anexo n° 3'
	 * */
	private int codigo_entidad;
	/*
	 * 'en el evento que exista una reliquidacion, y  que corresponda esta a años anteriores al mes del informe, se debera registrar el mes y año en que el beneficio fue imputado como gasto al fondo unico, en caso contrario se debera dejar vacio'
	 * */
	private String mes_nomina;
	/*
	 * 'cedula de identidad del beneficiario, con digito verificador'
	 * */
	private String run_beneficiario;
	/*
	 * 'nombres y apellidos del beneficiario'
	 * */
	private String nombre_beneficiario;
	/*
	 * 'codigo del tipo de subsidio segun codificacion en anexo n°3',
	 * */
	private int tipo_subsidio;
	/*
	 * 'numero de foliocompleto de la licencia medica en caso que el tipo de subsidio corresponda a permiso por reposo prenatal y postnatal, y enfermedad grave del niño menor de un año. en caso de corresponder a un permiso postnatal parental, se debera indicar el folio de licencia por permiso postnatal, el formato requerido es: codigo de formulario de licencia medica(indicado en anexo n°3 ),guion y el numero de la licencia sin digito verificador, incluyendo ceros despues del guion, en caso que el numero tenga un largo inferior a 12, completar el largo del campo ej:"1-000019980551"'
	 * */
	private String nro_licencia;;
	/*
	 * 'rol unico tributario del empleador con digito verificador, tratandose de trabajadores independientes y de madres sin contrato vigente, se debera indicar el run del beneficiario'
	 * */
	private String rut_empleador;
	/*
	 * 'nombre o razon social del empleador. tratandose de trabajadores independientes y de madres sin contrato vigente, se debera indicar el nombre del beneficiario'
	 * */
	private String nombre_empleador;
	/*
	 * 'origen del reintegro (beneficiario, empleador, entidad pagadora), segun codificacion del anexo n°3'
	 * */
	private int origen_reintegro;
	/*
	 * 'monto total de subsidios y cotizaciones mal pagadas o enteradas'
	 * */
	private long monto_total_a_reintegrar;
	/*
	 * 'monto efectivo devuelto al fondo unico en el mes del informe',
	 * */
	private long monto_recuperado;
	/*
	 * 'monto total recuperado al mes del informe',
	 * */
	private long monto_recuperado_acum;
	/*
	 * 'saldo pendiente por reintegrar al mes del informe financiero. de no existir saldo, se debe indicar 0'
	 * */
	private long total_saldo_a_reintegrar;

	/*
	 * key para poder mostrar cantidad de registros limitados
	 * */
	private int keyInicio;
	private int keyFin;
	private int paginacion;

	public ReintegrosVO() {
	}

	public ReintegrosVO(int id, String mes_informacion, int codigo_entidad, String mes_nomina, String run_beneficiario, String nombre_beneficiario, int tipo_subsidio, String nro_licencia,
			String rut_empleador, String nombre_empleador, int origen_reintegro, long monto_total_a_reintegrar, long monto_recuperado, long monto_recuperado_acum, long total_saldo_a_reintegrar,
			int keyInicio, int keyFin) {
		super();
		this.id = id;
		this.mes_informacion = mes_informacion;
		this.codigo_entidad = codigo_entidad;
		this.mes_nomina = mes_nomina;
		this.run_beneficiario = run_beneficiario;
		this.nombre_beneficiario = nombre_beneficiario;
		this.tipo_subsidio = tipo_subsidio;
		this.nro_licencia = nro_licencia;
		this.rut_empleador = rut_empleador;
		this.nombre_empleador = nombre_empleador;
		this.origen_reintegro = origen_reintegro;
		this.monto_total_a_reintegrar = monto_total_a_reintegrar;
		this.monto_recuperado = monto_recuperado;
		this.monto_recuperado_acum = monto_recuperado_acum;
		this.total_saldo_a_reintegrar = total_saldo_a_reintegrar;
		this.keyInicio = keyInicio;
		this.keyFin = keyFin;
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

	public int getCodigo_entidad() {
		return codigo_entidad;
	}

	public String getMes_informacion() {
		return mes_informacion;
	}

	public String getMes_nomina() {
		return mes_nomina;
	}

	public long getMonto_recuperado() {
		return monto_recuperado;
	}

	public long getMonto_recuperado_acum() {
		return monto_recuperado_acum;
	}

	public long getMonto_total_a_reintegrar() {
		return monto_total_a_reintegrar;
	}

	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}

	public String getNombre_empleador() {
		return nombre_empleador;
	}

	public String getNro_licencia() {
		return nro_licencia;
	}

	public int getOrigen_reintegro() {
		return origen_reintegro;
	}

	public String getRun_beneficiario() {
		return run_beneficiario;
	}

	public String getRut_empleador() {
		return rut_empleador;
	}

	public int getTipo_subsidio() {
		return tipo_subsidio;
	}

	public long getTotal_saldo_a_reintegrar() {
		return total_saldo_a_reintegrar;
	}

	public void setCodigo_entidad(int codigo_entidad) {
		this.codigo_entidad = codigo_entidad;
	}

	public void setMes_informacion(String mes_informacion) {
		this.mes_informacion = mes_informacion;
	}

	public void setMes_nomina(String mes_nomina) {
		this.mes_nomina = mes_nomina;
	}

	public void setMonto_recuperado(long monto_recuperado) {
		this.monto_recuperado = monto_recuperado;
	}

	public void setMonto_recuperado_acum(long monto_recuperado_acum) {
		this.monto_recuperado_acum = monto_recuperado_acum;
	}

	public void setMonto_total_a_reintegrar(long monto_total_a_reintegrar) {
		this.monto_total_a_reintegrar = monto_total_a_reintegrar;
	}

	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario;
	}

	public void setNombre_empleador(String nombre_empleador) {
		this.nombre_empleador = nombre_empleador;
	}

	public void setNro_licencia(String nro_licencia) {
		this.nro_licencia = nro_licencia;
	}

	public void setOrigen_reintegro(int origen_reintegro) {
		this.origen_reintegro = origen_reintegro;
	}

	public void setRun_beneficiario(String run_beneficiario) {
		this.run_beneficiario = run_beneficiario;
	}

	public void setRut_empleador(String rut_empleador) {
		this.rut_empleador = rut_empleador;
	}

	public void setTipo_subsidio(int tipo_subsidio) {
		this.tipo_subsidio = tipo_subsidio;
	}

	public void setTotal_saldo_a_reintegrar(long total_saldo_a_reintegrar) {
		this.total_saldo_a_reintegrar = total_saldo_a_reintegrar;
	}

	public int getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(int paginacion) {
		this.paginacion = paginacion;
	}

	//-----------------------------------------------------------

}
