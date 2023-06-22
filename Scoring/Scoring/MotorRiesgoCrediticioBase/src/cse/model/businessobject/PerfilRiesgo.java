package cse.model.businessobject;

public class PerfilRiesgo {

	String nombre;
	double minValue;
	double maxValue;
	
	public PerfilRiesgo(String nombre, double minValue, double maxValue) {
		setNombre(nombre);
		setMinValue(minValue);
		setMaxValue(maxValue);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getMinValue() {
		return minValue;
	}
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}
	public double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

}
