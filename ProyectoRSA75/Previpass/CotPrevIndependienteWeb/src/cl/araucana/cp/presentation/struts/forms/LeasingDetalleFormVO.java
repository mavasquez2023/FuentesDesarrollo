package cl.araucana.cp.presentation.struts.forms;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingDetalleVO;

public class LeasingDetalleFormVO  implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int codigoOficina;

    private Date fechaVencimiento;

    private int monto;

    private long montoUF;

    private java.lang.String numCuenta;

    public LeasingDetalleFormVO() {
    }

    public ArrayList listLeasingDetalleFormVO(LeasingDetalleVO[] leasingDetalleVOArray) throws ParseException {
    	
    	LeasingDetalleFormVO leasingDetalleFormVO =null;
    	ArrayList leasingDetalleFormVOs = new ArrayList();
    	LeasingDetalleVO leasingDetalleVO=null;
    	
    	for(int i = 0 ; i < leasingDetalleVOArray.length;i++){
    		leasingDetalleVO = leasingDetalleVOArray[i];
    		
    		leasingDetalleFormVO = new LeasingDetalleFormVO();
    		leasingDetalleFormVO.setCodigoOficina(leasingDetalleVO.getCodigoOficina());
    		leasingDetalleFormVO.setFechaVencimiento(this.parseFechaVencimiento(leasingDetalleVO.getFechaVencimiento()));
    		leasingDetalleFormVO.setMonto(leasingDetalleVO.getMonto());
    		leasingDetalleFormVO.setMontoUF(leasingDetalleVO.getMontoUF());
    		leasingDetalleFormVO.setNumCuenta(leasingDetalleVO.getNumCuenta());
    		
    		leasingDetalleFormVOs.add(leasingDetalleFormVO);
    	}
    	return leasingDetalleFormVOs;
    }


    /**
     * Gets the codigoOficina value for this LeasingDetalleVO.
     * 
     * @return codigoOficina
     */
    public int getCodigoOficina() {
        return codigoOficina;
    }


    /**
     * Sets the codigoOficina value for this LeasingDetalleVO.
     * 
     * @param codigoOficina
     */
    public void setCodigoOficina(int codigoOficina) {
        this.codigoOficina = codigoOficina;
    }


    /**
     * Gets the fechaVencimiento value for this LeasingDetalleVO.
     * 
     * @return fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }


    /**
     * Sets the fechaVencimiento value for this LeasingDetalleVO.
     * 
     * @param fechaVencimiento
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }


    /**
     * Gets the monto value for this LeasingDetalleVO.
     * 
     * @return monto
     */
    public int getMonto() {
        return monto;
    }


    /**
     * Sets the monto value for this LeasingDetalleVO.
     * 
     * @param monto
     */
    public void setMonto(int monto) {
        this.monto = monto;
    }


    /**
     * Gets the montoUF value for this LeasingDetalleVO.
     * 
     * @return montoUF
     */
    public long getMontoUF() {
        return montoUF;
    }


    /**
     * Sets the montoUF value for this LeasingDetalleVO.
     * 
     * @param montoUF
     */
    public void setMontoUF(long montoUF) {
        this.montoUF = montoUF;
    }


    /**
     * Gets the numCuenta value for this LeasingDetalleVO.
     * 
     * @return numCuenta
     */
    public java.lang.String getNumCuenta() {
        return numCuenta;
    }


    /**
     * Sets the numCuenta value for this LeasingDetalleVO.
     * 
     * @param numCuenta
     */
    public void setNumCuenta(java.lang.String numCuenta) {
        this.numCuenta = numCuenta;
    }
    
    private Date parseFechaVencimiento(int fehca) throws ParseException{
    	String ano =(""+fehca).substring(0, 4);
    	String mes =(""+fehca).substring(4, 6);
    	String dia =(""+fehca).substring(6, 8);
    	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
    	String strFecha = dia+"-"+mes+"-"+ano;
    	Date date = formatoDelTexto.parse(strFecha);
    	return date;
    	
    }
}
