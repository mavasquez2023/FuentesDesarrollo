package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import cl.araucana.autoconsulta.common.Constants;

/**
 * @author asepulveda
 *
 */
public class CertificadoAsignacionFamiliarVO extends DatosAsignacionFamiliarVO implements Serializable, Validable {
	
	private long id=0;
	private long rut=0;
	private int tramoVigente=-1;
	private int valorCarga=0;
	private String tipoCargaConsultado=null;
	private Collection cargas=new ArrayList(); //CargaFamiliarVO
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
		String glosaCantidadCargas="Cantidad de Cargas";
		DatosValidacionVO datos = new DatosValidacionVO();
		datos.setId(id);
		datos.setTipo(Constants.CERTIFICADO_ASFMAM);
		datos.setFecha(new Date());
		datos.setRut(rut);
		datos.setFullNombre(fullNombreAfiliado);
		datos.setFullRut(fullRutAfiliado);
		ArrayList listaValores = new ArrayList();
		ValorValidableVO variableValor1 = new ValorValidableVO();
		if(tipoCargaConsultado==CargaFamiliarVO.STD_ACTIVA) {
			glosaCantidadCargas=glosaCantidadCargas+" Autorizadas";
			variableValor1.setValor(String.valueOf(cargas.size()));
		}
		else if(tipoCargaConsultado==CargaFamiliarVO.STD_INACTIVA) {
			glosaCantidadCargas=glosaCantidadCargas+" Suspendidas";
			variableValor1.setValor(String.valueOf(cargas.size()));
		}else {
			variableValor1.setValor("No registra Asignaciones Familiares Autorizadas");
		}	
		variableValor1.setVariable(glosaCantidadCargas);
		listaValores.add(variableValor1);
		
		if(!cargas.isEmpty()){
			Iterator icargas = cargas.iterator();
			while(icargas.hasNext()){
				CargaFamiliarVO carga = (CargaFamiliarVO)icargas.next();
				ValorValidableVO variableValor2 = new ValorValidableVO();
				variableValor2.setVariable("Nombre Carga");
				variableValor2.setValor(carga.getFullNombre());
				listaValores.add(variableValor2);				
			}
		}
		
		datos.setListaValores(listaValores);
		
		return datos; 
	}
	

	/**
	 * Devuelve el tramo vigente como string para no desplear el -1 que es un valor no valido
	 * @return
	 */
	public String getDescripcionTramoVigente() {
		if(tramoVigente!=-1)
			return String.valueOf(tramoVigente);
		else
			return null;
	}
	
	/**
	 * Devuelve la cantidad de cargas activas
	 * @return
	 */
	public int getCantidadCargas() {
		return cargas.size();
	}

	/**
	 * @return
	 */
	public int getTramoVigente() {
		return tramoVigente;
	}

	/**
	 * @return
	 */
	public int getValorCarga() {
		return valorCarga;
	}


	/**
	 * @param i
	 */
	public void setTramoVigente(int i) {
		tramoVigente = i;
	}

	/**
	 * @param i
	 */
	public void setValorCarga(int i) {
		valorCarga = i;
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
	public Collection getCargas() {
		return cargas;
	}

	/**
	 * @param collection
	 */
	public void setCargas(Collection collection) {
		cargas = collection;
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
	public String getTipoCargaConsultado() {
		return tipoCargaConsultado;
	}

	/**
	 * @param string
	 */
	public void setTipoCargaConsultado(String string) {
		tipoCargaConsultado = string;
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
