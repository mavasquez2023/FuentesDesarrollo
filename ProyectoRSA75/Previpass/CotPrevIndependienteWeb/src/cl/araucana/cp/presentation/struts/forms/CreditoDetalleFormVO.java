package cl.araucana.cp.presentation.struts.forms;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cl.araucana.cp.webServices.aporte.orqInput.service.vo.CreditoDetalleVO;

public class CreditoDetalleFormVO implements java.io.Serializable {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int capital;

	    private int codigoOficina;

	    private int estadoCouta;

	    private Date fechaVencimiento;

	    private int folioCredito;

	    private int gravamenes;

	    private int intereses;

	    private int lineaCredito;

	    private int montoAbonado;

	    private int montoDescuento;

	    private int multas;

	    private int numCuota;

	    private int seguros;

	    private int totalCoutas;

	    private int valorCouta;

	    public CreditoDetalleFormVO() {
	    }
	    public ArrayList  listCreditoDetalleFormVO (CreditoDetalleVO[] creditoDetalleVOArray) throws ParseException {

	    	CreditoDetalleFormVO creditoDetalleFormVO =null;
	    	ArrayList creditoDetalleFormVOs = new ArrayList();
	    	CreditoDetalleVO creditoDetalleVO=null;
	    	
	    	for(int i = 0 ; i < creditoDetalleVOArray.length;i++){
	    		creditoDetalleVO = creditoDetalleVOArray[i];
	    		
	    		creditoDetalleFormVO = new CreditoDetalleFormVO();
	    		creditoDetalleFormVO.setCapital(creditoDetalleVO.getCapital());
	    		creditoDetalleFormVO.setCodigoOficina(creditoDetalleVO.getCodigoOficina());
	    		creditoDetalleFormVO.setEstadoCouta(creditoDetalleVO.getEstadoCouta());
	    		creditoDetalleFormVO.setFechaVencimiento(this.parseFechaVencimiento(creditoDetalleVO.getFechaVencimiento()));
	    		creditoDetalleFormVO.setFolioCredito(creditoDetalleVO.getFolioCredito());
	    		creditoDetalleFormVO.setGravamenes(creditoDetalleVO.getGravamenes());
	    		creditoDetalleFormVO.setIntereses(creditoDetalleVO.getIntereses());
	    		creditoDetalleFormVO.setLineaCredito(creditoDetalleVO.getLineaCredito());
	    		creditoDetalleFormVO.setMontoAbonado(creditoDetalleVO.getMontoAbonado());
	    		creditoDetalleFormVO.setMontoDescuento(creditoDetalleVO.getMontoDescuento());
	    		creditoDetalleFormVO.setMultas(creditoDetalleVO.getMultas());
	    		creditoDetalleFormVO.setNumCuota(creditoDetalleVO.getNumCuota());
	    		creditoDetalleFormVO.setSeguros(creditoDetalleVO.getSeguros());
	    		creditoDetalleFormVO.setTotalCoutas(creditoDetalleVO.getTotalCoutas());
	    		creditoDetalleFormVO.setValorCouta(creditoDetalleVO.getValorCouta());
	            creditoDetalleFormVOs.add(creditoDetalleFormVO);
	    	} 
	    	return creditoDetalleFormVOs;
	    }
	    
	    /**
	     * Gets the capital value for this CreditoDetalleVO.
	     * 
	     * @return capital
	     */
	    public int getCapital() {
	        return capital;
	    }


	    /**
	     * Sets the capital value for this CreditoDetalleVO.
	     * 
	     * @param capital
	     */
	    public void setCapital(int capital) {
	        this.capital = capital;
	    }


	    /**
	     * Gets the codigoOficina value for this CreditoDetalleVO.
	     * 
	     * @return codigoOficina
	     */
	    public int getCodigoOficina() {
	        return codigoOficina;
	    }


	    /**
	     * Sets the codigoOficina value for this CreditoDetalleVO.
	     * 
	     * @param codigoOficina
	     */
	    public void setCodigoOficina(int codigoOficina) {
	        this.codigoOficina = codigoOficina;
	    }


	    /**
	     * Gets the estadoCouta value for this CreditoDetalleVO.
	     * 
	     * @return estadoCouta
	     */
	    public int getEstadoCouta() {
	        return estadoCouta;
	    }


	    /**
	     * Sets the estadoCouta value for this CreditoDetalleVO.
	     * 
	     * @param estadoCouta
	     */
	    public void setEstadoCouta(int estadoCouta) {
	        this.estadoCouta = estadoCouta;
	    }


	    /**
	     * Gets the fechaVencimiento value for this CreditoDetalleVO.
	     * 
	     * @return fechaVencimiento
	     */
	    public Date getFechaVencimiento() {
	        return fechaVencimiento;
	    }


	    /**
	     * Sets the fechaVencimiento value for this CreditoDetalleVO.
	     * 
	     * @param fechaVencimiento
	     */
	    public void setFechaVencimiento(Date fechaVencimiento) {
	        this.fechaVencimiento = fechaVencimiento;
	    }


	    /**
	     * Gets the folioCredito value for this CreditoDetalleVO.
	     * 
	     * @return folioCredito
	     */
	    public int getFolioCredito() {
	        return folioCredito;
	    }


	    /**
	     * Sets the folioCredito value for this CreditoDetalleVO.
	     * 
	     * @param folioCredito
	     */
	    public void setFolioCredito(int folioCredito) {
	        this.folioCredito = folioCredito;
	    }


	    /**
	     * Gets the gravamenes value for this CreditoDetalleVO.
	     * 
	     * @return gravamenes
	     */
	    public int getGravamenes() {
	        return gravamenes;
	    }


	    /**
	     * Sets the gravamenes value for this CreditoDetalleVO.
	     * 
	     * @param gravamenes
	     */
	    public void setGravamenes(int gravamenes) {
	        this.gravamenes = gravamenes;
	    }


	    /**
	     * Gets the intereses value for this CreditoDetalleVO.
	     * 
	     * @return intereses
	     */
	    public int getIntereses() {
	        return intereses;
	    }


	    /**
	     * Sets the intereses value for this CreditoDetalleVO.
	     * 
	     * @param intereses
	     */
	    public void setIntereses(int intereses) {
	        this.intereses = intereses;
	    }


	    /**
	     * Gets the lineaCredito value for this CreditoDetalleVO.
	     * 
	     * @return lineaCredito
	     */
	    public int getLineaCredito() {
	        return lineaCredito;
	    }


	    /**
	     * Sets the lineaCredito value for this CreditoDetalleVO.
	     * 
	     * @param lineaCredito
	     */
	    public void setLineaCredito(int lineaCredito) {
	        this.lineaCredito = lineaCredito;
	    }


	    /**
	     * Gets the montoAbonado value for this CreditoDetalleVO.
	     * 
	     * @return montoAbonado
	     */
	    public int getMontoAbonado() {
	        return montoAbonado;
	    }


	    /**
	     * Sets the montoAbonado value for this CreditoDetalleVO.
	     * 
	     * @param montoAbonado
	     */
	    public void setMontoAbonado(int montoAbonado) {
	        this.montoAbonado = montoAbonado;
	    }


	    /**
	     * Gets the montoDescuento value for this CreditoDetalleVO.
	     * 
	     * @return montoDescuento
	     */
	    public int getMontoDescuento() {
	        return montoDescuento;
	    }


	    /**
	     * Sets the montoDescuento value for this CreditoDetalleVO.
	     * 
	     * @param montoDescuento
	     */
	    public void setMontoDescuento(int montoDescuento) {
	        this.montoDescuento = montoDescuento;
	    }


	    /**
	     * Gets the multas value for this CreditoDetalleVO.
	     * 
	     * @return multas
	     */
	    public int getMultas() {
	        return multas;
	    }


	    /**
	     * Sets the multas value for this CreditoDetalleVO.
	     * 
	     * @param multas
	     */
	    public void setMultas(int multas) {
	        this.multas = multas;
	    }


	    /**
	     * Gets the numCuota value for this CreditoDetalleVO.
	     * 
	     * @return numCuota
	     */
	    public int getNumCuota() {
	        return numCuota;
	    }


	    /**
	     * Sets the numCuota value for this CreditoDetalleVO.
	     * 
	     * @param numCuota
	     */
	    public void setNumCuota(int numCuota) {
	        this.numCuota = numCuota;
	    }


	    /**
	     * Gets the seguros value for this CreditoDetalleVO.
	     * 
	     * @return seguros
	     */
	    public int getSeguros() {
	        return seguros;
	    }


	    /**
	     * Sets the seguros value for this CreditoDetalleVO.
	     * 
	     * @param seguros
	     */
	    public void setSeguros(int seguros) {
	        this.seguros = seguros;
	    }


	    /**
	     * Gets the totalCoutas value for this CreditoDetalleVO.
	     * 
	     * @return totalCoutas
	     */
	    public int getTotalCoutas() {
	        return totalCoutas;
	    }


	    /**
	     * Sets the totalCoutas value for this CreditoDetalleVO.
	     * 
	     * @param totalCoutas
	     */
	    public void setTotalCoutas(int totalCoutas) {
	        this.totalCoutas = totalCoutas;
	    }


	    /**
	     * Gets the valorCouta value for this CreditoDetalleVO.
	     * 
	     * @return valorCouta
	     */
	    public int getValorCouta() {
	        return valorCouta;
	    }


	    /**
	     * Sets the valorCouta value for this CreditoDetalleVO.
	     * 
	     * @param valorCouta
	     */
	    public void setValorCouta(int valorCouta) {
	        this.valorCouta = valorCouta;
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
