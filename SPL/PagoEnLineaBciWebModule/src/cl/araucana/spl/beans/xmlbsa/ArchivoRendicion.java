package cl.araucana.spl.beans.xmlbsa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ArchivoRendicion {
	private TotalizadorPagos totalizador;
	private List detalles = new ArrayList();
	
	public String toString() {
		StringBuffer sb = new StringBuffer("[ArchivoRendicion::");
		sb.append("totalizador=" + totalizador);
		for (Iterator iter = detalles.iterator(); iter.hasNext();) {
			DetallePagos detalle = (DetallePagos) iter.next();
			sb.append(",detalle=" + detalle);
		}
		sb.append("]");
		return sb.toString();
	}		
	
	public List getDetalles() {
		return detalles;
	}
	public void setDetalles(List detalles) {
		this.detalles = detalles;
	}
	public TotalizadorPagos getTotalizador() {
		return totalizador;
	}
	public void setTotalizador(TotalizadorPagos totalizador) {
		this.totalizador = totalizador;
	}
	public void addDetalle(DetallePagos detallePagos) {
		detalles.add(detallePagos);
	}
}
