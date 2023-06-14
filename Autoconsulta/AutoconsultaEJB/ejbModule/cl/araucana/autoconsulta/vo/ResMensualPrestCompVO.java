/*
 * Creado el 25-11-2005
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.autoconsulta.vo;

import java.io.Serializable;

/**
 * @author USIST15
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class ResMensualPrestCompVO implements Serializable{
	private String nombreTrabajador = null;
	private String rutTrabajador = null;
	private double fondoInicial = 0;
	private String fechaAumentoFondoInicial1 = null;
	private double montoAumentoInicial1 = 0;
	private String fechaAumentoFondoInicial2 = null;
	private double montoAumentoInicial2 = 0;
	private String fechaAumentoFondoInicial3 = null;
	private double montoAumentoInicial3 = 0;
	private String fechaAumentoFondoInicial4 = null;
	private double montoAumentoInicial4 = 0;
	
	private String fechaComision1 = null;
	private double montoAumentoComision1 = 0;
	private String fechaComision2 = null;
	private double montoAumentoComision2= 0;
	private String fechaComision3 = null;
	private double montoAumentoComision3 = 0;
	private String fechaComision4 = null;
	private double montoAumentoComision4 = 0;

	private String fechaValorPagado1 = null;
	private double montoValorPagado1 = 0;
	private String fechaValorPagado2 = null;
	private double montoValorPagado2 = 0;
	private String fechaValorPagado3 = null;
	private double montoValorPagado3 = 0;
	private String fechaValorPagado4 = null;
	private double montoValorPagado4 = 0;

	private String fechaMontoActualizado1 = null;
	private double montoActualizado1 = 0;
	private String fechaMontoActualizado2 = null;
	private double montoActualizado2 = 0;
	private String fechaMontoActualizado3 = null;
	private double montoActualizado3 = 0;
	private String fechaMontoActualizado4= null;
	private double montoActualizado4 = 0;
	
	
	
	private String fechaRetiroSolEmp1 = null;
	private double montoRetiroSolEmp1 = 0;
	private String fechaRetiroSolEmp2 = null;
	private double montoRetiroSolEmp2 = 0;
	private String fechaRetiroSolEmp3 = null;
	private double montoRetiroSolEmp3 = 0;
	private String fechaRetiroSolEmp4= null;
	private double montoRetiroSolEmp4 = 0;
	
	
	private double montoInteres = 0;
	private double montoReajuste = 0;
	private double montoFondoFinal = 0;
	private String periodo = null;  
	private long rutEmpresa = 0; 
	private long convenioID = 0;
	private String fechaCierre = null;



	/**
	 * @return
	 */
	public long getConvenioID() {
		return convenioID;
	}

	/**
	 * @return
	 */
	public String getFechaAumentoFondoInicial1() {
		return fechaAumentoFondoInicial1;
	}

	/**
	 * @return
	 */
	public String getFechaAumentoFondoInicial2() {
		return fechaAumentoFondoInicial2;
	}

	/**
	 * @return
	 */
	public String getFechaAumentoFondoInicial3() {
		return fechaAumentoFondoInicial3;
	}

	/**
	 * @return
	 */
	public String getFechaAumentoFondoInicial4() {
		return fechaAumentoFondoInicial4;
	}

	/**
	 * @return
	 */
	public String getFechaComision1() {
		return fechaComision1;
	}

	/**
	 * @return
	 */
	public String getFechaComision2() {
		return fechaComision2;
	}

	/**
	 * @return
	 */
	public String getFechaComision3() {
		return fechaComision3;
	}

	/**
	 * @return
	 */
	public String getFechaComision4() {
		return fechaComision4;
	}

	/**
	 * @return
	 */
	public String getFechaMontoActualizado1() {
		return fechaMontoActualizado1;
	}

	/**
	 * @return
	 */
	public String getFechaMontoActualizado2() {
		return fechaMontoActualizado2;
	}

	/**
	 * @return
	 */
	public String getFechaMontoActualizado3() {
		return fechaMontoActualizado3;
	}

	/**
	 * @return
	 */
	public String getFechaMontoActualizado4() {
		return fechaMontoActualizado4;
	}

	/**
	 * @return
	 */
	public String getFechaValorPagado1() {
		return fechaValorPagado1;
	}

	/**
	 * @return
	 */
	public String getFechaValorPagado2() {
		return fechaValorPagado2;
	}

	/**
	 * @return
	 */
	public String getFechaValorPagado3() {
		return fechaValorPagado3;
	}

	/**
	 * @return
	 */
	public String getFechaValorPagado4() {
		return fechaValorPagado4;
	}

	/**
	 * @return
	 */
	public double getFondoInicial() {
		return fondoInicial;
	}

	/**
	 * @return
	 */
	public double getMontoActualizado1() {
		return montoActualizado1;
	}

	/**
	 * @return
	 */
	public double getMontoActualizado2() {
		return montoActualizado2;
	}

	/**
	 * @return
	 */
	public double getMontoActualizado3() {
		return montoActualizado3;
	}

	/**
	 * @return
	 */
	public double getMontoActualizado4() {
		return montoActualizado4;
	}


	/**
	 * @return
	 */
	public double getMontoAumentoInicial1() {
		return montoAumentoInicial1;
	}

	/**
	 * @return
	 */
	public double getMontoAumentoInicial2() {
		return montoAumentoInicial2;
	}

	/**
	 * @return
	 */
	public double getMontoAumentoInicial3() {
		return montoAumentoInicial3;
	}

	/**
	 * @return
	 */
	public double getMontoAumentoInicial4() {
		return montoAumentoInicial4;
	}

	/**
	 * @return
	 */
	public double getMontoFondoFinal() {
		return montoFondoFinal;
	}

	/**
	 * @return
	 */
	public double getMontoInteres() {
		return montoInteres;
	}

	/**
	 * @return
	 */
	public double getMontoReajuste() {
		return montoReajuste;
	}

	/**
	 * @return
	 */
	public double getMontoValorPagado1() {
		return montoValorPagado1;
	}

	/**
	 * @return
	 */
	public double getMontoValorPagado2() {
		return montoValorPagado2;
	}

	/**
	 * @return
	 */
	public double getMontoValorPagado3() {
		return montoValorPagado3;
	}

	/**
	 * @return
	 */
	public double getMontoValorPagado4() {
		return montoValorPagado4;
	}

	/**
	 * @return
	 */
	public String getNombreTrabajador() {
		return nombreTrabajador;
	}

	/**
	 * @return
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @return
	 */
	public long getRutEmpresa() {
		return rutEmpresa;
	}

	/**
	 * @return
	 */
	public String getRutTrabajador() {
		return rutTrabajador;
	}

	/**
	 * @param l
	 */
	public void setConvenioID(long l) {
		convenioID = l;
	}

	/**
	 * @param string
	 */
	public void setFechaAumentoFondoInicial1(String string) {
		fechaAumentoFondoInicial1 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaAumentoFondoInicial2(String string) {
		fechaAumentoFondoInicial2 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaAumentoFondoInicial3(String string) {
		fechaAumentoFondoInicial3 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaAumentoFondoInicial4(String string) {
		fechaAumentoFondoInicial4 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaComision1(String string) {
		fechaComision1 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaComision2(String string) {
		fechaComision2 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaComision3(String string) {
		fechaComision3 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaComision4(String string) {
		fechaComision4 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaMontoActualizado1(String string) {
		fechaMontoActualizado1 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaMontoActualizado2(String string) {
		fechaMontoActualizado2 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaMontoActualizado3(String string) {
		fechaMontoActualizado3 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaMontoActualizado4(String string) {
		fechaMontoActualizado4 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaValorPagado1(String string) {
		fechaValorPagado1 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaValorPagado2(String string) {
		fechaValorPagado2 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaValorPagado3(String string) {
		fechaValorPagado3 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaValorPagado4(String string) {
		fechaValorPagado4 = string;
	}

	/**
	 * @param d
	 */
	public void setFondoInicial(double d) {
		fondoInicial = d;
	}

	/**
	 * @param d
	 */
	public void setMontoActualizado1(double d) {
		montoActualizado1 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoActualizado2(double d) {
		montoActualizado2 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoActualizado3(double d) {
		montoActualizado3 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoActualizado4(double d) {
		montoActualizado4 = d;
	}


	/**
	 * @param d
	 */
	public void setMontoAumentoInicial1(double d) {
		montoAumentoInicial1 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoAumentoInicial2(double d) {
		montoAumentoInicial2 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoAumentoInicial3(double d) {
		montoAumentoInicial3 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoAumentoInicial4(double d) {
		montoAumentoInicial4 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoFondoFinal(double d) {
		montoFondoFinal = d;
	}

	/**
	 * @param d
	 */
	public void setMontoInteres(double d) {
		montoInteres = d;
	}

	/**
	 * @param d
	 */
	public void setMontoReajuste(double d) {
		montoReajuste = d;
	}

	/**
	 * @param d
	 */
	public void setMontoValorPagado1(double d) {
		montoValorPagado1 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoValorPagado2(double d) {
		montoValorPagado2 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoValorPagado3(double d) {
		montoValorPagado3 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoValorPagado4(double d) {
		montoValorPagado4 = d;
	}

	/**
	 * @param string
	 */
	public void setNombreTrabajador(String string) {
		nombreTrabajador = string;
	}

	/**
	 * @param string
	 */
	public void setPeriodo(String string) {
		periodo = string;
	}

	/**
	 * @param l
	 */
	public void setRutEmpresa(long l) {
		rutEmpresa = l;
	}

	/**
	 * @param string
	 */
	public void setRutTrabajador(String string) {
		rutTrabajador = string;
	}

	/**
	 * @return
	 */
	public double getMontoAumentoComision1() {
		return montoAumentoComision1;
	}

	/**
	 * @return
	 */
	public double getMontoAumentoComision2() {
		return montoAumentoComision2;
	}

	/**
	 * @return
	 */
	public double getMontoAumentoComision3() {
		return montoAumentoComision3;
	}

	/**
	 * @return
	 */
	public double getMontoAumentoComision4() {
		return montoAumentoComision4;
	}

	/**
	 * @param d
	 */
	public void setMontoAumentoComision1(double d) {
		montoAumentoComision1 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoAumentoComision2(double d) {
		montoAumentoComision2 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoAumentoComision3(double d) {
		montoAumentoComision3 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoAumentoComision4(double d) {
		montoAumentoComision4 = d;
	}

	/**
	 * @return
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * @param string
	 */
	public void setFechaCierre(String string) {
		fechaCierre = string;
	}



	/**
	 * @return
	 */
	public double getMontoRetiroSolEmp1() {
		return montoRetiroSolEmp1;
	}

	/**
	 * @return
	 */
	public double getMontoRetiroSolEmp2() {
		return montoRetiroSolEmp2;
	}

	/**
	 * @return
	 */
	public double getMontoRetiroSolEmp3() {
		return montoRetiroSolEmp3;
	}

	/**
	 * @return
	 */
	public double getMontoRetiroSolEmp4() {
		return montoRetiroSolEmp4;
	}


	/**
	 * @param d
	 */
	public void setMontoRetiroSolEmp1(double d) {
		montoRetiroSolEmp1 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoRetiroSolEmp2(double d) {
		montoRetiroSolEmp2 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoRetiroSolEmp3(double d) {
		montoRetiroSolEmp3 = d;
	}

	/**
	 * @param d
	 */
	public void setMontoRetiroSolEmp4(double d) {
		montoRetiroSolEmp4 = d;
	}







	/**
	 * @return
	 */
	public String getFechaRetiroSolEmp1() {
		return fechaRetiroSolEmp1;
	}

	/**
	 * @return
	 */
	public String getFechaRetiroSolEmp2() {
		return fechaRetiroSolEmp2;
	}

	/**
	 * @return
	 */
	public String getFechaRetiroSolEmp3() {
		return fechaRetiroSolEmp3;
	}

	/**
	 * @return
	 */
	public String getFechaRetiroSolEmp4() {
		return fechaRetiroSolEmp4;
	}

	/**
	 * @param string
	 */
	public void setFechaRetiroSolEmp1(String string) {
		fechaRetiroSolEmp1 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaRetiroSolEmp2(String string) {
		fechaRetiroSolEmp2 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaRetiroSolEmp3(String string) {
		fechaRetiroSolEmp3 = string;
	}

	/**
	 * @param string
	 */
	public void setFechaRetiroSolEmp4(String string) {
		fechaRetiroSolEmp4 = string;
	}

}
