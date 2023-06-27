package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cl.araucana.bienestar.bonificaciones.vo.RangoCoberturaSinFormatoVO;
import cl.araucana.common.BusinessException;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class VigenciaRango implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private long codigo = 0;
	private long codigoCobertura = 0;
	private Date inicioVigencia=null;
	private Date finVigencia=null;
	private ArrayList rangos=new ArrayList(); //de tipo Rango
	
	Calendar cal = Calendar.getInstance();	
	
	/*
	 * Constructor por defecto
	 */
	public VigenciaRango() {
	}
	
	/*
	 * Constructor a partir de RangoCoberturaSinFormatoVO
	 */
	public VigenciaRango(RangoCoberturaSinFormatoVO param) {
		codigo=param.getCodigo();
		codigoCobertura=param.getCodigoCobertura();
		setInicioVigencia(param.getInicioVigencia());
		setFinVigencia(param.getFinVigencia());
	}
	
	/**
	 * @param date
	 */
	public void setFinVigencia(Date date) {
		
		if(date!=null){
			cal.setTime(date);
			cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),23,59,59);
			finVigencia = cal.getTime();
		}else
			finVigencia=date;
			
	}

	/**
	 * @param date
	 */
	public void setInicioVigencia(Date date) {

		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),0,0,0);
		inicioVigencia = cal.getTime();
	}	
	
	/**
	 * Agrega un rango
	 * @param Rango rango
	 * @param String tipoCobertura
	 */
	public void addRango(Rango ran,String tipoCobertura) throws BusinessException {
		if (rangos == null) {
			rangos = new ArrayList();
		}
		if(tipoCobertura.equals(Cobertura.TIPO_ESPECIAL) && rangos.size()>0)
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"Una cobertura de tipo Especial, no puede tener más de un rango");

		rangos.add(ran);
	}
	
	/**
	 * Elimina un rango
	 */
	public void removeRango(int index) {
		if (rangos.size() > 0 && index < rangos.size())
			rangos.remove(index);
	}
	

	/**
	 * @return
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public long getCodigoCobertura() {
		return codigoCobertura;
	}

	/**
	 * @return
	 */
	public Date getFinVigencia() {
		return finVigencia;
	}

	/**
	 * @return
	 */
	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	/**
	 * @return
	 */
	public ArrayList getRangos() {
		return rangos;
	}

	/**
	 * @param l
	 */
	public void setCodigo(long l) {
		codigo = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoCobertura(long l) {
		codigoCobertura = l;
	}

	/**
	 * @param list
	 */
	public void setRangos(ArrayList list) {
		rangos = list;
	}

}
