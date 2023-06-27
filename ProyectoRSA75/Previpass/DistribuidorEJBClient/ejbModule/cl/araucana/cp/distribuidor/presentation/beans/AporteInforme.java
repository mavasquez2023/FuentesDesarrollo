package cl.araucana.cp.distribuidor.presentation.beans;

public class AporteInforme implements java.io.Serializable{
	
	 private int monto;

	 private int numRegistros;

	 private AporteDetalleInforme[] aporteDetalleInformes;

	public AporteDetalleInforme[] getAporteDetalleInformes() {
		return aporteDetalleInformes;
	}

	public void setAporteDetalleInformes(
			AporteDetalleInforme[] aporteDetalleInformes) {
		this.aporteDetalleInformes = aporteDetalleInformes;
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
