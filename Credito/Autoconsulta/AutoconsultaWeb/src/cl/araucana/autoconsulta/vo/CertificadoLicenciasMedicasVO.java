package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import cl.araucana.autoconsulta.common.Constants;

/**
 * @author asepulveda
 *
 */
public class CertificadoLicenciasMedicasVO implements Serializable, Validable {
	
	private long id=0;
	private long rut=0;
	private boolean tieneLicencias=false;
	private int totalSubsidioCesantia = 0;
	private int totalSubsidioPagado=0;
	private int totalCotizacionPension=0;
	private int totalSalud=0;
	private int sumaTotal=0;
	private Date fechaHoy=new Date();
	private Collection licencias = new ArrayList(); //LicenciaMedicaCertificadoVO
	private String fullRutAfiliado=null;
	private String fullNombreAfiliado=null;


	/**
	 * Devuelve el objeto que se utiliza para registrar los datos que se
	 * utilizan para validar posteriormente el certificado.
	 * @param no tiene
	 * @return DatosValidacionVO
	 */
	public DatosValidacionVO getDatosValidacion() throws Exception {
		NumberFormat nf = NumberFormat.getInstance();
		
		DatosValidacionVO datos = new DatosValidacionVO();
		datos.setId(id);
		datos.setTipo(Constants.CERTIFICADO_LICMED);
		datos.setFecha(new Date());
		datos.setRut(rut);
		datos.setFullNombre(fullNombreAfiliado);
		datos.setFullRut(fullRutAfiliado);
		ArrayList listaValores = new ArrayList();
		ValorValidableVO variableValor1 = new ValorValidableVO();
		variableValor1.setVariable("Cantidad de Licencias");
		variableValor1.setValor(String.valueOf(licencias.size()));
		listaValores.add(variableValor1);
		ValorValidableVO variableValor2 = new ValorValidableVO();
		variableValor2.setVariable("Monto Total");
		variableValor2.setValor("$" + nf.format(sumaTotal));
		listaValores.add(variableValor2);
		
		datos.setListaValores(listaValores);
		
		return datos; 
	}
	

	/**
	 * @return
	 */
	public Collection getLicencias() {
		return licencias;
	}

	/**
	 * @return
	 */
	public int getSumaTotal() {
		return sumaTotal;
	}

	/**
	 * @return
	 */
	public int getTotalCotizacionPension() {
		return totalCotizacionPension;
	}

	/**
	 * @return
	 */
	public int getTotalSalud() {
		return totalSalud;
	}

	/**
	 * @return
	 */
	public int getTotalSubsidioCesantia() {
		return totalSubsidioCesantia;
	}

	/**
	 * @return
	 */
	public int getTotalSubsidioPagado() {
		return totalSubsidioPagado;
	}

	/**
	 * @param collection
	 */
	public void setLicencias(Collection collection) {
		licencias = collection;
	}

	/**
	 * @param i
	 */
	public void setSumaTotal(int i) {
		sumaTotal = i;
	}

	/**
	 * @param i
	 */
	public void setTotalCotizacionPension(int i) {
		totalCotizacionPension = i;
	}

	/**
	 * @param i
	 */
	public void setTotalSalud(int i) {
		totalSalud = i;
	}

	/**
	 * @param i
	 */
	public void setTotalSubsidioCesantia(int i) {
		totalSubsidioCesantia = i;
	}

	/**
	 * @param i
	 */
	public void setTotalSubsidioPagado(int i) {
		totalSubsidioPagado = i;
	}

	/**
	 * @return
	 */
	public Date getFechaHoy() {
		return fechaHoy;
	}

	/**
	 * @param date
	 */
	public void setFechaHoy(Date date) {
		fechaHoy = date;
	}

	/**
	 * @return
	 */
	public boolean isTieneLicencias() {
		return tieneLicencias;
	}

	/**
	 * @param b
	 */
	public void setTieneLicencias(boolean b) {
		tieneLicencias = b;
	}

	/**
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return
	 */
	public long getRut() {
		return rut;
	}

	/**
	 * @param l
	 */
	public void setId(long l) {
		id = l;
	}

	/**
	 * @param l
	 */
	public void setRut(long l) {
		rut = l;
	}

	/**
	 * @return
	 */
	public String getFullNombreAfiliado() {
		return fullNombreAfiliado;
	}

	/**
	 * @return
	 */
	public String getFullRutAfiliado() {
		return fullRutAfiliado;
	}

	/**
	 * @param string
	 */
	public void setFullNombreAfiliado(String string) {
		fullNombreAfiliado = string;
	}

	/**
	 * @param string
	 */
	public void setFullRutAfiliado(String string) {
		fullRutAfiliado = string;
	}

}
