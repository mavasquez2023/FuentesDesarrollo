package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;

import cl.araucana.bienestar.bonificaciones.vo.RangoCoberturaSinFormatoVO;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class Rango implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private double rangoID = 0;
	private double rangoInicio = 0;
	private double rangoFin = 0;
	private double rangoPorcentajeCobertura = 0;
	
	
	/*
	 * Constructor por defecto
	 */
	public Rango() {
	}
	
	/*
	 * Constructor a partir de RangoCoberturaSinFormatoVO
	 */
	public Rango(RangoCoberturaSinFormatoVO param) {
		rangoID=param.getRangoID();
		rangoInicio=param.getRangoInicio();
		rangoFin=param.getRangoFin();
		rangoPorcentajeCobertura=param.getRangoPorcentajeCobertura();
	}
	

	/**
	 * @return
	 */
	public double getRangoFin() {
		return rangoFin;
	}

	/**
	 * @return
	 */
	public double getRangoID() {
		return rangoID;
	}

	/**
	 * @return
	 */
	public double getRangoInicio() {
		return rangoInicio;
	}

	/**
	 * @return
	 */
	public double getRangoPorcentajeCobertura() {
		return rangoPorcentajeCobertura;
	}

	/**
	 * @param d
	 */
	public void setRangoFin(double d) {
		rangoFin = d;
	}

	/**
	 * @param d
	 */
	public void setRangoID(double d) {
		rangoID = d;
	}

	/**
	 * @param d
	 */
	public void setRangoInicio(double d) {
		rangoInicio = d;
	}

	/**
	 * @param d
	 */
	public void setRangoPorcentajeCobertura(double d) {
		rangoPorcentajeCobertura = d;
	}

}
