package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;
import java.util.Set;

/**
 * Rapresenta el permiso raiz para la lectura de documentos de parte de las entidades
 * @author araucana
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LectorEntidadVO implements Serializable
{
	private static final long serialVersionUID = 2743922932412574348L;
	
	private int idLectorEntidad;
	private int habilitado;
	
	Set entidadesPension;
	Set entidadesSalud;
	Set entidadesApv;
	Set entidadesCcaf;
	Set entidadesMutual;

	public LectorEntidadVO(int idLectorEntidad, int habilitado) {
		super();
		this.idLectorEntidad = idLectorEntidad;
		this.habilitado = habilitado;
	}

	public LectorEntidadVO() {
		super();
	}
	
	public LectorEntidadVO(int idLectorEntidad) {
		this.idLectorEntidad = idLectorEntidad;
	}
	public int getHabilitado() {
		return this.habilitado;
	}
	public void setHabilitado(int habilitado) {
		this.habilitado = habilitado;
	}
	public int getIdLectorEntidad() {
		return this.idLectorEntidad;
	}
	public void setIdLectorEntidad(int idLectorEntidad) {
		this.idLectorEntidad = idLectorEntidad;
	}

	public Set getEntidadesPension() {
		return entidadesPension;
	}

	public void setEntidadesPension(Set entidadesPension) {
		this.entidadesPension = entidadesPension;
	}

	public Set getEntidadesSalud() {
		return entidadesSalud;
	}

	public void setEntidadesSalud(Set entidadesSalud) {
		this.entidadesSalud = entidadesSalud;
	}

	public Set getEntidadesApv() {
		return entidadesApv;
	}

	public void setEntidadesApv(Set entidadesApv) {
		this.entidadesApv = entidadesApv;
	}

	public Set getEntidadesCcaf() {
		return entidadesCcaf;
	}

	public void setEntidadesCcaf(Set entidadesCcaf) {
		this.entidadesCcaf = entidadesCcaf;
	}

	public Set getEntidadesMutual() {
		return entidadesMutual;
	}

	public void setEntidadesMutual(Set entidadesMutual) {
		this.entidadesMutual = entidadesMutual;
	}
}