package cl.araucana.cp.distribuidor.presentation.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AporteDetalleInforme implements java.io.Serializable{
	 	private Date fechaVencimiento;

	    private int interesesReajuste;

	    private int monto;

	    private int rentaPromedio;

	    public AporteDetalleInforme(){
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
		
		public Date parseFechaVencimiento(int fehca) throws ParseException{
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
