package cl.laaraucana.simulacion.webservices.client.ConsultaDatosAfiliacionAs400.VO;

import java.util.List;
import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;

public class SalidaListaConsultaDatosAfiliacionAs400 extends AbstractSalidaVO{
	
	private String nombre;
	private String totalEmpresa;
	private List<SalidaConsultaDatosAfiliacionAs400> detalleEmpresa;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTotalEmpresa() {
		return totalEmpresa;
	}
	public void setTotalEmpresa(String totalEmpresa) {
		this.totalEmpresa = totalEmpresa;
	}
	public List<SalidaConsultaDatosAfiliacionAs400> getDetalleEmpresa() {
		return detalleEmpresa;
	}
	public void setDetalleEmpresa(
			List<SalidaConsultaDatosAfiliacionAs400> detalleEmpresa) {
		this.detalleEmpresa = detalleEmpresa;
	}

}
