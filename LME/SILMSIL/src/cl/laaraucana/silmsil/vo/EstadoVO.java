package cl.laaraucana.silmsil.vo;
/**
 * Objeto POJO para parámetro de estado, los estados posibles son:
 *  - btnCorregir: Habilita botón CORREGIR. 
 *  - btnDescargar: Habilita botón DESCARGAR. 
 *  - sinBoton: Informa que no es necesario habilitar ningún botón.
 * @author usist42
 *
 */
public class EstadoVO {

	//Texto del estado a mostrar.
	private String texto;
	//Botón acargar para estado;
	private String boton;
	//periodo para acciones de botones sobre periodo especifico
	private String proceso_periodo;
	
	
	public EstadoVO() {
		super();
	}



	public EstadoVO(String texto, String boton) {
		super();
		this.texto = texto;
		this.boton = boton;		
	}

	public EstadoVO(String texto, String boton, String proceso_periodo) {
		super();
		this.texto = texto;
		this.boton = boton;
		this.proceso_periodo=proceso_periodo;
	}
	


	public String getTexto() {
		return texto;
	}



	public void setTexto(String texto) {
		this.texto = texto;
	}



	public String getBoton() {
		return boton;
	}



	public void setBoton(String boton) {
		this.boton = boton;
	}



	public String getProceso_periodo() {
		return proceso_periodo;
	}



	public void setProceso_periodo(String proceso_periodo) {
		this.proceso_periodo = proceso_periodo;
	}

}
