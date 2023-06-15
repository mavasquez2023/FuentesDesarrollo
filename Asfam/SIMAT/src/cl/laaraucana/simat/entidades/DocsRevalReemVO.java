package cl.laaraucana.simat.entidades;

import java.sql.Date;

public class DocsRevalReemVO {
	private int id;
	/*
	 * Corresponde al mes del Informe Financiero.
	 */
	private String mes_informacion;
	/*
	 * Se debe inidicar el código asociado a la entidad pagadora de subsidios, de acuerdo a 
	 * la codificación del Anexo n° 3.
	 */
	private int codigo_entidad;
	/*
	 * Código del tipo de subsidio según codificación en Anexo n° 3.
	 */
	private int tiposubsidio;
	/*
	 * Corresponde informar todos los campos del documento original, el cual fue caducado o 
	 * anulado, e informado previamente en el archivo plano n°4 y posterirormente revalidado 
	 * a reemitido.
	 */
	private int mod_pago_original;
	/*
	 * Corresponde informar todos los campos del documento original, el cual fue caducado o 
	 * anulado, e informado previamente en el archivo plano n°4 y posterirormente revalidado 
	 * a reemitido.
	 */
	private String codigo_banco_original;
	/*
	 * Corresponde informar todos los campos del documento original, el cual fue caducado o 
	 * anulado, e informado previamente en el archivo plano n°4 y posterirormente revalidado 
	 * a reemitido.
	 */
	private String serie_documento_original;
	/*
	 * Corresponde informar todos los campos del documento original, el cual fue caducado o 
	 * anulado, e informado previamente en el archivo plano n°4 y posterirormente revalidado 
	 * a reemitido.
	 */
	private String num_documento_original;
	/*
	 * Los dominios de los campos modalidad de pago, código del banco y estado del documento 
	 *  corresponde a la codificación del Anexo n°3.
	 */
	private Date fecha_emision_documento_original;
	private String fecha_emision_documento_original_Char;
	/*
	 * Los dominios de los campos modalidad de pago, código del banco y estado del documento 
	 * corresponde a la codificación del Anexo n°3.
	 */
	private long monto_documento_original;
	/*
	 * Los dominios de los campos modalidad de pago, código del banco y estado del documento 
	 * corresponde a la codificación del Anexo n°3.
	 */
	private int estado_documento_original;
	/*
	 * Corresponde informar todos los campos del nuevo documento el cual fue informado en el 
	 * archivo plano n° 2 o 3 que reemplaza al anterior, ya sea porque éste fue caducado o 
	 * anulado.
	 */
	private int mod_pago_nuevo;
	/*
	 * Corresponde informar todos los campos del nuevo documento el cual fue informado en el 
	 * archivo plano n° 2 o 3 que reemplaza al anterior, ya sea porque éste fue caducado o 
	 * anulado.
	 */
	private String codigo_banco_nuevo;
	/*
	 * Corresponde informar todos los campos del nuevo documento el cual fue informado en el 
	 * archivo plano n° 2 o 3 que reemplaza al anterior, ya sea porque éste fue caducado o 
	 * anulado.
	 */
	private String serie_documento_nuevo;
	/*
	 * Corresponde informar todos los campos del nuevo documento el cual fue informado en el 
	 * archivo plano n° 2 o 3 que reemplaza al anterior, ya sea porque éste fue caducado o 
	 * anulado.
	 */
	private String num_documento_nuevo;
	/*
	 * Los dominios de los campos modalidad de pago, código del banco corresponde según 
	 * la codificación del Anexo n° 3.
	 */
	private Date fecha_emision_documento_nuevo;

	private String fecha_emision_documento_nuevo_Char;
	/*
	 * Los dominios de los campos modalidad de pago, código del banco corresponde según la 
	 * codificación del Anexo n° 3.
	 */
	private long monto_documento_nuevo;
	
	private int indicadorConvenio;
	
	/*
	 * key para poder mostrar cantidad de registros limitados
	 * */
	private int keyInicio;
	private int keyFin;
	private int paginacion;

	public DocsRevalReemVO() {
	}

	public DocsRevalReemVO(int id, String mes_informacion, int codigo_entidad, int tiposubsidio, int mod_pago_original, String codigo_banco_original, String serie_documento_original,
			String num_documento_original, Date fecha_emision_documento_original, int monto_documento_original, int estado_documento_original, int mod_pago_nuevo, String codigo_banco_nuevo,
			String serie_documento_nuevo, String num_documento_nuevo, Date fecha_emision_documento_nuevo, int monto_documento_nuevo) {
		super();
		this.id = id;
		this.mes_informacion = mes_informacion;
		this.codigo_entidad = codigo_entidad;
		this.tiposubsidio = tiposubsidio;
		this.mod_pago_original = mod_pago_original;
		this.codigo_banco_original = codigo_banco_original;
		this.serie_documento_original = serie_documento_original;
		this.num_documento_original = num_documento_original;
		this.fecha_emision_documento_original = fecha_emision_documento_original;
		this.monto_documento_original = monto_documento_original;
		this.estado_documento_original = estado_documento_original;
		this.mod_pago_nuevo = mod_pago_nuevo;
		this.codigo_banco_nuevo = codigo_banco_nuevo;
		this.serie_documento_nuevo = serie_documento_nuevo;
		this.num_documento_nuevo = num_documento_nuevo;
		this.fecha_emision_documento_nuevo = fecha_emision_documento_nuevo;
		this.monto_documento_nuevo = monto_documento_nuevo;
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

	public int getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(int paginacion) {
		this.paginacion = paginacion;
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

	public int getEstado_documento_original() {
		return estado_documento_original;
	}

	public Date getFecha_emision_documento_nuevo() {
		return fecha_emision_documento_nuevo;
	}

	public Date getFecha_emision_documento_original() {
		return fecha_emision_documento_original;
	}

	public String getMes_informacion() {
		return mes_informacion;
	}

	public int getMod_pago_nuevo() {
		return mod_pago_nuevo;
	}

	public int getMod_pago_original() {
		return mod_pago_original;
	}

	public long getMonto_documento_nuevo() {
		return monto_documento_nuevo;
	}

	public long getMonto_documento_original() {
		return monto_documento_original;
	}

	public String getNum_documento_nuevo() {
		return num_documento_nuevo;
	}

	public String getNum_documento_original() {
		return num_documento_original;
	}

	public String getSerie_documento_nuevo() {
		return serie_documento_nuevo;
	}

	public String getSerie_documento_original() {
		return serie_documento_original;
	}

	public int getTiposubsidio() {
		return tiposubsidio;
	}

	public void setCodigo_entidad(int codigo_entidad) {
		this.codigo_entidad = codigo_entidad;
	}

	public void setEstado_documento_original(int estado_documento_original) {
		this.estado_documento_original = estado_documento_original;
	}

	public void setFecha_emision_documento_nuevo(Date fecha_emision_documento_nuevo) {
		this.fecha_emision_documento_nuevo = fecha_emision_documento_nuevo;
	}

	public void setFecha_emision_documento_original(Date fecha_emision_documento_original) {
		this.fecha_emision_documento_original = fecha_emision_documento_original;
	}

	public void setMes_informacion(String mes_informacion) {
		this.mes_informacion = mes_informacion.trim();
	}

	public void setMod_pago_nuevo(int mod_pago_nuevo) {
		this.mod_pago_nuevo = mod_pago_nuevo;
	}

	public void setMod_pago_original(int mod_pago_original) {
		this.mod_pago_original = mod_pago_original;
	}

	public void setMonto_documento_nuevo(long monto_documento_nuevo) {
		this.monto_documento_nuevo = monto_documento_nuevo;
	}

	public void setMonto_documento_original(long monto_documento_original) {
		this.monto_documento_original = monto_documento_original;
	}

	public void setNum_documento_nuevo(String num_documento_nuevo) {
		this.num_documento_nuevo = num_documento_nuevo.trim();
	}

	public void setNum_documento_original(String num_documento_original) {
		this.num_documento_original = num_documento_original.trim();
	}

	public void setSerie_documento_nuevo(String serie_documento_nuevo) {
		this.serie_documento_nuevo = serie_documento_nuevo.trim();
	}

	public void setSerie_documento_original(String serie_documento_original) {
		this.serie_documento_original = serie_documento_original.trim();
	}

	public void setTiposubsidio(int tiposubsidio) {
		this.tiposubsidio = tiposubsidio;
	}

	public String getCodigo_banco_nuevo() {
		return codigo_banco_nuevo;
	}

	public String getCodigo_banco_original() {
		return codigo_banco_original;
	}

	public void setCodigo_banco_nuevo(String codigo_banco_nuevo) {
		this.codigo_banco_nuevo = codigo_banco_nuevo;
	}

	public void setCodigo_banco_original(String codigo_banco_original) {
		this.codigo_banco_original = codigo_banco_original;
	}

	public String getFecha_emision_documento_nuevo_Char() {
		return fecha_emision_documento_nuevo_Char;
	}

	public String getFecha_emision_documento_original_Char() {
		return fecha_emision_documento_original_Char;
	}

	public void setFecha_emision_documento_nuevo_Char(String fecha_emision_documento_nuevo_Char) {
		this.fecha_emision_documento_nuevo_Char = fecha_emision_documento_nuevo_Char;
	}

	public void setFecha_emision_documento_original_Char(String fecha_emision_documento_original_Char) {
		this.fecha_emision_documento_original_Char = fecha_emision_documento_original_Char;
	}

	public int getIndicadorConvenio() {
		return indicadorConvenio;
	}

	public void setIndicadorConvenio(int indicadorConvenio) {
		this.indicadorConvenio = indicadorConvenio;
	}

}
