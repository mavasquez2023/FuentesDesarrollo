package cl.araucana.cp.webServices.aporte.orqInput.service.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AporteDetalleFormVO implements java.io.Serializable{
	
	 private Date fechaVencimiento;

    private int interesesReajuste;

    private int monto;

    private int rentaPromedio;

    public AporteDetalleFormVO(){
    }
    public ArrayList  listAporteDetalleFormVO (AporteDetalleVO[] aporteDetalleVOArray) throws ParseException {

    	AporteDetalleFormVO aporteDetalleFormVO =null;
    	ArrayList aporteDetalleFormVOs = new ArrayList();
    	AporteDetalleVO aporteDetalleVO=null;
    	
    	for(int i = 0 ; i < aporteDetalleVOArray.length;i++){
    		aporteDetalleVO = aporteDetalleVOArray[i];
    		
    		aporteDetalleFormVO= new AporteDetalleFormVO();
    		aporteDetalleFormVO.setFechaVencimiento(this.parseFechaVencimiento(aporteDetalleVO.getFechaVencimiento()));
    		aporteDetalleFormVO.setInteresesReajuste(aporteDetalleVO.getInteresesReajuste());
    		aporteDetalleFormVO.setMonto(aporteDetalleVO.getMonto());
    		aporteDetalleFormVO.setRentaPromedio(aporteDetalleVO.getRentaPromedio());
    		aporteDetalleFormVOs.add(aporteDetalleFormVO);
    		
    	} 
    	return aporteDetalleFormVOs;
    }
    public AporteDetalleFormVO(AporteDetalleVO aporteDetalleVO) throws ParseException {
            this.fechaVencimiento = this.parseFechaVencimiento(aporteDetalleVO.getFechaVencimiento());
            this.interesesReajuste = aporteDetalleVO.getInteresesReajuste();
            this.monto = aporteDetalleVO.getMonto();
            this.rentaPromedio = aporteDetalleVO.getRentaPromedio();
     }
    
	public int getInteresesReajuste() {
		return interesesReajuste;
	}

	public void setInteresesReajuste(int interesesReajuste) {
		this.interesesReajuste = interesesReajuste;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public int getRentaPromedio() {
		return rentaPromedio;
	}

	public void setRentaPromedio(int rentaPromedio) {
		this.rentaPromedio = rentaPromedio;
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
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
    
}
