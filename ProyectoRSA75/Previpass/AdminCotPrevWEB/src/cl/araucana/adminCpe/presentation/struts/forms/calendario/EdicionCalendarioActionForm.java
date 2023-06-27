package cl.araucana.adminCpe.presentation.struts.forms.calendario;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) EdicionCalendarioActionForm.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author vagurto
 * 
 * @version 1.1
 */
public class EdicionCalendarioActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -1L;
	
	private List consulta;
	private int [] id;
	private String [] mes;
	private String [] fecha1;
	private String [] fecha2;
	private String [] fecha3;
	private String [] fecha4;
	private String [] hora;
	private String [] min;
	private String [] informacion;
	
	/**
	 * hora
	 * @return
	 */
	public String[] getHora() {
		return this.hora;
	}
	/**
	 * hora
	 * @param hora
	 */
	public void setHora(String[] hora) {
		this.hora = hora;
	}
	/**
	 * minuto
	 * @return
	 */
	public String[] getMin() {
		return this.min;
	}
	/**
	 * minuto
	 * @param min
	 */
	public void setMin(String[] min) {
		this.min = min;
	}
	/**
	 * fecha1
	 * @return
	 */
	public String[] getFecha1() {
		return this.fecha1;
	}
	/**
	 * fecha1
	 * @param fecha1
	 */
	public void setFecha1(String[] fecha1) {
		this.fecha1 = fecha1;
	}
	/**
	 * fecha2
	 * @return
	 */
	public String[] getFecha2() {
		return this.fecha2;
	}
	/**
	 * fecha2
	 * @param fecha2
	 */
	public void setFecha2(String[] fecha2) {
		this.fecha2 = fecha2;
	}
	/**
	 * fecha3
	 * @return
	 */
	public String[] getFecha3() {
		return this.fecha3;
	}
	/**
	 * fecha3
	 * @param fecha3
	 */
	public void setFecha3(String[] fecha3) {
		this.fecha3 = fecha3;
	}
	/**
	 * fecha4
	 * @return
	 */
	public String[] getFecha4() {
		return this.fecha4;
	}
	/**
	 * fecha4
	 * @param fecha4
	 */
	public void setFecha4(String[] fecha4) {
		this.fecha4 = fecha4;
	}
	/**
	 * mes
	 * @return
	 */
	public String[] getMes() {
		return this.mes;
	}
	/**
	 * mes
	 * @param mes
	 */
	public void setMes(String[] mes) {
		this.mes = mes;
	}
	/**
	 * consulta
	 * @return
	 */
	public List getConsulta() {
		return this.consulta;
	}
	/**
	 * consulta
	 * @param consulta
	 */
	public void setConsulta(List consulta) {
		this.consulta = consulta;
	}
	/**
	 * id
	 * @return
	 */
	public int[] getId() {
		return this.id;
	}
	/**
	 * id
	 * @param id
	 */
	public void setId(int[] id) {
		this.id = id;
	}
	/**
	 * @return the informacion
	 */
	public String[] getInformacion()
	{
		return this.informacion;
	}
	/**
	 * @param informacion the informacion to set
	 */
	public void setInformacion(String[] informacion)
	{
		this.informacion = informacion;
	}	
}
