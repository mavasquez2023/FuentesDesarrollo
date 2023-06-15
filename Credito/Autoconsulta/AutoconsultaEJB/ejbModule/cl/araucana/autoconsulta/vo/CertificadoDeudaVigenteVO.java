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
public class CertificadoDeudaVigenteVO implements Serializable, Validable {
	
	private long id=0;
	private long rut=0;
	private Collection creditosDirectos = new ArrayList(); //InformacionCreditoVO
	private Collection creditosIndirectos = new ArrayList(); //InformacionCreditoVO
	private int saldoTotalTitular=0;
	private int saldoTotalAval=0;
	private Date fechaHoy=null;
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
		datos.setTipo(Constants.CERTIFICADO_DEUVIG);
		datos.setFecha(new Date());
		datos.setRut(rut);
		datos.setFullNombre(fullNombreAfiliado);
		datos.setFullRut(fullRutAfiliado);
		ArrayList listaValores = new ArrayList();
		ValorValidableVO variableValor1 = new ValorValidableVO();
		variableValor1.setVariable("Cantidad de Créditos Directos");
		variableValor1.setValor(String.valueOf(creditosDirectos.size()));
		listaValores.add(variableValor1);
		ValorValidableVO variableValor2= new ValorValidableVO();
		variableValor2.setVariable("Total Deuda Directa");
		variableValor2.setValor("$" + nf.format(saldoTotalTitular));
		listaValores.add(variableValor2);
		ValorValidableVO variableValor3= new ValorValidableVO();
		variableValor3.setVariable("Cantidad de Créditos Indirectos");
		variableValor3.setValor(String.valueOf(creditosIndirectos.size()));
		listaValores.add(variableValor3);
		ValorValidableVO variableValor4= new ValorValidableVO();
		variableValor4.setVariable("Total Deuda Indirecta");
		variableValor4.setValor("$" + nf.format(saldoTotalAval));
		listaValores.add(variableValor4);
		
		datos.setListaValores(listaValores);
		
		return datos; 
	}
	

	/**
	 * Retorna la deuda total del socio
	 * @return
	 */
	public int getDeudaTotal() {
		return saldoTotalTitular + saldoTotalAval;
	}

	/**
	 * @return
	 */
	public Collection getCreditosDirectos() {
		return creditosDirectos;
	}

	/**
	 * @return
	 */
	public Collection getCreditosIndirectos() {
		return creditosIndirectos;
	}

	/**
	 * @return
	 */
	public int getSaldoTotalAval() {
		return saldoTotalAval;
	}

	/**
	 * @return
	 */
	public int getSaldoTotalTitular() {
		return saldoTotalTitular;
	}

	/**
	 * @param collection
	 */
	public void setCreditosDirectos(Collection collection) {
		creditosDirectos = collection;
	}

	/**
	 * @param collection
	 */
	public void setCreditosIndirectos(Collection collection) {
		creditosIndirectos = collection;
	}

	/**
	 * @param i
	 */
	public void setSaldoTotalAval(int i) {
		saldoTotalAval = i;
	}

	/**
	 * @param i
	 */
	public void setSaldoTotalTitular(int i) {
		saldoTotalTitular = i;
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
