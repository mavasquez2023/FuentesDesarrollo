/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.ibatis.vo;

/**
 * @author IBM Software Factory
 *
 */
public class DetalleCargaPagoManualVo {
	private String fechaPago;
	private int rutAfiliado;
	private String dvAfiliado;
	private String nombreAfiliado;
	private String descripcionPago;
	private int montoPago;
	private String email;
	private int codbanco;
	private String numcuenta;
	private int idtipcta;
	private String codBeneficio;
	private String referencia1;
	private String referencia2;
	
	/**
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}
	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	/**
	 * @return the rutAfiliado
	 */
	public int getRutAfiliado() {
		return rutAfiliado;
	}
	/**
	 * @param rutAfiliado the rutAfiliado to set
	 */
	public void setRutAfiliado(int rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}
	/**
	 * @return the dvAfiliado
	 */
	public String getDvAfiliado() {
		return dvAfiliado;
	}
	/**
	 * @param dvAfiliado the dvAfiliado to set
	 */
	public void setDvAfiliado(String dvAfiliado) {
		this.dvAfiliado = dvAfiliado;
	}
	/**
	 * @return the nombreAfiliado
	 */
	public String getNombreAfiliado() {
		return nombreAfiliado;
	}
	/**
	 * @param nombreAfiliado the nombreAfiliado to set
	 */
	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}
	/**
	 * @return the descripcionPago
	 */
	public String getDescripcionPago() {
		return descripcionPago;
	}
	/**
	 * @param descripcionPago the descripcionPago to set
	 */
	public void setDescripcionPago(String descripcionPago) {
		this.descripcionPago = descripcionPago;
	}
	/**
	 * @return the montoPago
	 */
	public int getMontoPago() {
		return montoPago;
	}
	/**
	 * @param montoPago the montoPago to set
	 */
	public void setMontoPago(int montoPago) {
		this.montoPago = montoPago;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the codbanco
	 */
	public int getCodbanco() {
		return codbanco;
	}
	/**
	 * @param codbanco the codbanco to set
	 */
	public void setCodbanco(int codbanco) {
		this.codbanco = codbanco;
	}
	/**
	 * @return the numcuenta
	 */
	public String getNumcuenta() {
		return numcuenta;
	}
	/**
	 * @param numcuenta the numcuenta to set
	 */
	public void setNumcuenta(String numcuenta) {
		this.numcuenta = numcuenta;
	}
	/**
	 * @return the idtipcta
	 */
	public int getIdtipcta() {
		return idtipcta;
	}
	/**
	 * @param idtipcta the idtipcta to set
	 */
	public void setIdtipcta(int idtipcta) {
		this.idtipcta = idtipcta;
	}
	
	
	/**
	 * @return the codBeneficio
	 */
	public String getCodBeneficio() {
		return codBeneficio;
	}
	/**
	 * @param codBeneficio the codBeneficio to set
	 */
	public void setCodBeneficio(String codBeneficio) {
		this.codBeneficio = codBeneficio;
	}
	/**
	 * @return the referencia1
	 */
	public String getReferencia1() {
		return referencia1;
	}
	/**
	 * @param referencia1 the referencia1 to set
	 */
	public void setReferencia1(String referencia1) {
		this.referencia1 = referencia1;
	}
	/**
	 * @return the referencia2
	 */
	public String getReferencia2() {
		return referencia2;
	}
	/**
	 * @param referencia2 the referencia2 to set
	 */
	public void setReferencia2(String referencia2) {
		this.referencia2 = referencia2;
	}
	public String toString(){
		StringBuffer registro= new StringBuffer();
		registro.append(this.fechaPago);
		registro.append(";");
		registro.append(this.rutAfiliado);
		registro.append(";");
		registro.append(this.dvAfiliado);
		registro.append(";");
		registro.append(this.nombreAfiliado);
		registro.append(";");
		registro.append(this.descripcionPago);
		registro.append(";");
		registro.append(this.montoPago);
		registro.append(";");
		registro.append(this.codBeneficio);
		registro.append(";");
		registro.append(this.referencia1);
		registro.append(";");
		registro.append(this.referencia2);
		registro.append("\n");
		return registro.toString();
	}
}
