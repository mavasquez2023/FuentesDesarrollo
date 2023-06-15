package cl.jfactory.planillas.service.util;

public class UtilInteger {
	private Integer valor = new Integer(0);
	public int intValue(){
		return  valor.intValue();
	}
	public void increment(){
		valor = new Integer( valor.intValue() + 1 );
	}
	public void decrement(){
		valor = new Integer( valor.intValue() - 1 );
	}
	public void reiniciar(){
		valor = new Integer(0);
	}
}
