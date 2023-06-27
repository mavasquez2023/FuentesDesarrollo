/**
 * 
 */
package cl.domino.rentaspro;

/**
 * @author usist24
 *
 */
public class AfiliadoRPTO {
	private int periodo; 
	private int rutEmpresa;
	private char dvRutEmpresa;
	private int rutAfiliado;
	private char dvRutAfiliado;
	private String apellidoPaterno;
	private String apellidoMaterno;  
	private String nombres; 
	int remuMismoEmpleador; 
	int remuOtroEmpleador; 
	int remuIndependiente; 
	int subsidio; 
	int pensiones; 
	int totalIngresos;
	int numMeses; 
	int promedioMensual; 
	short declaracion; 
	char origen;
	char estado;
	/**
	 * @return el apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * @param apellidoMaterno el apellidoMaterno a establecer
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * @return el apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * @param apellidoPaterno el apellidoPaterno a establecer
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * @return el declaracion
	 */
	public short getDeclaracion() {
		return declaracion;
	}
	/**
	 * @param declaracion el declaracion a establecer
	 */
	public void setDeclaracion(short declaracion) {
		this.declaracion = declaracion;
	}
	/**
	 * @return el dvRutAfiliado
	 */
	public char getDvRutAfiliado() {
		return dvRutAfiliado;
	}
	/**
	 * @param dvRutAfiliado el dvRutAfiliado a establecer
	 */
	public void setDvRutAfiliado(char dvRutAfiliado) {
		this.dvRutAfiliado = dvRutAfiliado;
	}
	/**
	 * @return el dvRutEmpresa
	 */
	public char getDvRutEmpresa() {
		return dvRutEmpresa;
	}
	/**
	 * @param dvRutEmpresa el dvRutEmpresa a establecer
	 */
	public void setDvRutEmpresa(char dvRutEmpresa) {
		this.dvRutEmpresa = dvRutEmpresa;
	}
	/**
	 * @return el estado
	 */
	public char getEstado() {
		return estado;
	}
	/**
	 * @param estado el estado a establecer
	 */
	public void setEstado(char estado) {
		this.estado = estado;
	}
	/**
	 * @return el nombres
	 */
	public String getNombres() {
		return nombres;
	}
	/**
	 * @param nombres el nombres a establecer
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**
	 * @return el numMeses
	 */
	public int getNumMeses() {
		return numMeses;
	}
	/**
	 * @param numMeses el numMeses a establecer
	 */
	public void setNumMeses(int numMeses) {
		this.numMeses = numMeses;
	}
	/**
	 * @return el origen
	 */
	public char getOrigen() {
		return origen;
	}
	/**
	 * @param origen el origen a establecer
	 */
	public void setOrigen(char origen) {
		this.origen = origen;
	}
	/**
	 * @return el pensiones
	 */
	public int getPensiones() {
		return pensiones;
	}
	/**
	 * @param pensiones el pensiones a establecer
	 */
	public void setPensiones(int pensiones) {
		this.pensiones = pensiones;
	}
	/**
	 * @return el periodo
	 */
	public int getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo el periodo a establecer
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return el promedioMensual
	 */
	public int getPromedioMensual() {
		return promedioMensual;
	}
	/**
	 * @param promedioMensual el promedioMensual a establecer
	 */
	public void setPromedioMensual(int promedioMensual) {
		this.promedioMensual = promedioMensual;
	}
	/**
	 * @return el remuIndependiente
	 */
	public int getRemuIndependiente() {
		return remuIndependiente;
	}
	/**
	 * @param remuIndependiente el remuIndependiente a establecer
	 */
	public void setRemuIndependiente(int remuIndependiente) {
		this.remuIndependiente = remuIndependiente;
	}
	/**
	 * @return el remuMismoEmpleador
	 */
	public int getRemuMismoEmpleador() {
		return remuMismoEmpleador;
	}
	/**
	 * @param remuMismoEmpleador el remuMismoEmpleador a establecer
	 */
	public void setRemuMismoEmpleador(int remuMismoEmpleador) {
		this.remuMismoEmpleador = remuMismoEmpleador;
	}
	/**
	 * @return el remuOtroEmpleador
	 */
	public int getRemuOtroEmpleador() {
		return remuOtroEmpleador;
	}
	/**
	 * @param remuOtroEmpleador el remuOtroEmpleador a establecer
	 */
	public void setRemuOtroEmpleador(int remuOtroEmpleador) {
		this.remuOtroEmpleador = remuOtroEmpleador;
	}
	/**
	 * @return el rutAfiliado
	 */
	public int getRutAfiliado() {
		return rutAfiliado;
	}
	/**
	 * @param rutAfiliado el rutAfiliado a establecer
	 */
	public void setRutAfiliado(int rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}
	/**
	 * @return el rutEmpresa
	 */
	public int getRutEmpresa() {
		return rutEmpresa;
	}
	/**
	 * @param rutEmpresa el rutEmpresa a establecer
	 */
	public void setRutEmpresa(int rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * @return el subsidio
	 */
	public int getSubsidio() {
		return subsidio;
	}
	/**
	 * @param subsidio el subsidio a establecer
	 */
	public void setSubsidio(int subsidio) {
		this.subsidio = subsidio;
	}
	/**
	 * @return el totalIngresos
	 */
	public int getTotalIngresos() {
		return totalIngresos;
	}
	/**
	 * @param totalIngresos el totalIngresos a establecer
	 */
	public void setTotalIngresos(int totalIngresos) {
		this.totalIngresos = totalIngresos;
	}
	
}