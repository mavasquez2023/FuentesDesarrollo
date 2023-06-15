package cl.laaraucana.simulacion.webservices.model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioAfiliadoVO extends AbstractSalidaVO {

	private String nombreAfiliado;
	private String rut;
	private List<DetalleEmpresaAfiliado> detalleEmpresaList = new ArrayList<DetalleEmpresaAfiliado>();

	public String getNombreAfiliado() {
		return nombreAfiliado;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public List<DetalleEmpresaAfiliado> getDetalleEmpresaList() {
		return detalleEmpresaList;
	}

	public void setDetalleEmpresaList(List<DetalleEmpresaAfiliado> detalleEmpresaList) {
		this.detalleEmpresaList = detalleEmpresaList;
	}

}
