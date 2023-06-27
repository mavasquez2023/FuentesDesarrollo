package cl.araucana.cp.distribuidor.presentation.beans;

public class CreditoInforme implements java.io.Serializable{
	    private CreditoDetalleInforme[] creditoDetalle;

	    private int monto;

	    private int numRegistros;

	    
		public CreditoDetalleInforme[] getCreditoDetalle() {
			return creditoDetalle;
		}

		public void setCreditoDetalle(CreditoDetalleInforme[] creditoDetalle) {
			this.creditoDetalle = creditoDetalle;
		}

		public int getMonto() {
			return monto;
		}

		public void setMonto(int monto) {
			this.monto = monto;
		}

		public int getNumRegistros() {
			return numRegistros;
		}

		public void setNumRegistros(int numRegistros) {
			this.numRegistros = numRegistros;
		}
	    
	    

}
