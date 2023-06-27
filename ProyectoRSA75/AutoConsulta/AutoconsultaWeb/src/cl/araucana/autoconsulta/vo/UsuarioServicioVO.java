package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class UsuarioServicioVO   implements Serializable {
	private static final long serialVersionUID = 6473504204840163730L;
	private PersonaVO empresa;
	private PersonaVO usuario;
	private PersonaVO afiliado;
	private Collection accesos;
	
	public UsuarioServicioVO() {
		accesos = new ArrayList();
	}
	
	public Collection getAccesos() {
		return accesos;
	}
	public void setAccesos(Collection accesos) {
		this.accesos = accesos;
	}
	public PersonaVO getAfiliado() {
		return afiliado;
	}
	public void setAfiliado(PersonaVO afiliado) {
		this.afiliado = afiliado;
	}
	public PersonaVO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PersonaVO empresa) {
		this.empresa = empresa;
	}
	public PersonaVO getUsuario() {
		return usuario;
	}
	public void setUsuario(PersonaVO usuario) {
		this.usuario = usuario;
	}
}
