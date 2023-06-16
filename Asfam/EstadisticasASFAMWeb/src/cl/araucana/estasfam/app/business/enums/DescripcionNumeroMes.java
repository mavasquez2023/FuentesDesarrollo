package cl.araucana.estasfam.app.business.enums;

public enum DescripcionNumeroMes {
	ENERO ("ENERO", 1),
	FEBRERO ("FEBRERO", 2),
	MARZO ("MARZO", 3),
	ABRIL ("ABRIL", 4),
	MAYO ("MAYO", 5),
	JUNIO ("JUNIO", 6),
	JULIO ("JULIO", 7),
	AGOSTO ("AGOSTO", 8),
	SEPTIEMBRE ("SEPTIEMBRE", 9),
	OCTUBRE ("OCTUBRE", 10),
	NOVIEMBRE ("NOVIEMBRE", 11),
	DICIEMBRE ("DICIEMBRE", 12);
	
	private String dMes;
	private int nMes;
	
	private DescripcionNumeroMes(String dMes, int nMes) {
		this.dMes = dMes;
		this.nMes = nMes;
	}
	public String getdMes() {
		return dMes;
	}
	public int getnMes() {
		return nMes;
	}

}
