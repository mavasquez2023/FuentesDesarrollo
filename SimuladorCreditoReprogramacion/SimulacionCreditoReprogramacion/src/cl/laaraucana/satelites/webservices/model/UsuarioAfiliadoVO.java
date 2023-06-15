package cl.laaraucana.satelites.webservices.model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioAfiliadoVO extends AbstractSalidaVO {

	private String nombreAfiliado;
	private List<DetalleEmpresaAfiliado> detalleEmpresaList = new ArrayList<DetalleEmpresaAfiliado>();

	public String getNombreAfiliado() {
		return nombreAfiliado;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

	public List<DetalleEmpresaAfiliado> getDetalleEmpresaList() {
		return detalleEmpresaList;
	}

	public void setDetalleEmpresaList(List<DetalleEmpresaAfiliado> detalleEmpresaList) {
		this.detalleEmpresaList = detalleEmpresaList;
	}

	


}
