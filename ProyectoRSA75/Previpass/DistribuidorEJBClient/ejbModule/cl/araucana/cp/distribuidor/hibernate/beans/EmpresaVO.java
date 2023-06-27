package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class EmpresaVO extends AuditableVO implements Comparable
{
	private static final long serialVersionUID = 4848670940015244545L;
	int idEmpresa;
	private String idEmpresaFmt;
	String idCasaMatriz;
	Long idAdmin;
	Long idRepLegal;
	String razonSocial;
	Integer privada;
	Integer habilitada;
	Date iniciacion;
	Date vigenciaRepLegal;
	boolean editable;
	int idCaja;
	Collection convenios;

	SucursalVO casaMatriz;
	List sucursales;

	int grupoConvenio;
	//GMALLEA Se agrega el atributo tipo para identificar que tipo de empresa es.
	String tipo;

	String tipoPago;
	
	private boolean esAdmin;

	public EmpresaVO(int idEmpresa)
	{
		super();
		this.idEmpresa = idEmpresa;
	}

	public int compareTo(Object o)
	{
		Integer este = new Integer(this.idEmpresa);
		return este.compareTo(new Integer(((EmpresaVO) o).getIdEmpresa()));
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idEmpresa;
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EmpresaVO other = (EmpresaVO) obj;
		if (this.idEmpresa != other.idEmpresa)
			return false;
		return true;
	}

	public EmpresaVO()
	{
	}

	public Integer getHabilitada()
	{
		return this.habilitada;
	}

	public void setHabilitada(Integer habilitada)
	{
		this.habilitada = habilitada;
	}

	public Long getIdAdmin()
	{
		return this.idAdmin;
	}

	public void setIdAdmin(Long idAdmin)
	{
		this.idAdmin = idAdmin;
	}

	public String getIdCasaMatriz()
	{
		return this.idCasaMatriz;
	}

	public void setIdCasaMatriz(String idCasaMatriz)
	{
		this.idCasaMatriz = idCasaMatriz;
	}

	public int getIdEmpresa()
	{
		return this.idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa)
	{
		this.idEmpresa = idEmpresa;
	}

	public Long getIdRepLegal()
	{
		return this.idRepLegal;
	}

	public void setIdRepLegal(Long idRepLegal)
	{
		this.idRepLegal = idRepLegal;
	}

	public Date getIniciacion()
	{
		return this.iniciacion;
	}

	public void setIniciacion(Date iniciacion)
	{
		this.iniciacion = iniciacion;
	}

	public Integer getPrivada()
	{
		return this.privada;
	}

	public void setPrivada(Integer privada)
	{
		this.privada = privada;
	}

	public String getRazonSocial()
	{
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	public Date getVigenciaRepLegal()
	{
		return this.vigenciaRepLegal;
	}

	public void setVigenciaRepLegal(Date vigenciaRepLegal)
	{
		this.vigenciaRepLegal = vigenciaRepLegal;
	}

	public Collection getConvenios()
	{
		return this.convenios;
	}

	public void setConvenios(Collection convenios)
	{
		this.convenios = convenios;
	}

	public void addConvenio(ConvenioVO convenio)
	{
		if (this.convenios == null)
			this.convenios = new ArrayList();
		this.convenios.add(convenio);
	}

	public SucursalVO getCasaMatriz()
	{
		return this.casaMatriz;
	}

	public void setCasaMatriz(SucursalVO casaMatriz)
	{
		this.casaMatriz = casaMatriz;
	}

	public List getSucursales()
	{
		return this.sucursales;
	}

	public void setSucursales(List sucursales)
	{
		this.sucursales = sucursales;
	}

	public String toString()
	{
		return "";
		/*
		 * return this."Empresa[idEmpresa: " + this.idEmpresa + ", razonSocial: \"" + (this.razonSocial != null ? this.razonSocial.trim() : "(null)") + "\"" + ", idCasaMatriz: " +
		 * this.idCasaMatriz.trim() + ", idRepLegal: " + (this.idRepLegal != null ? this.idRepLegal.toString() : "(null)") + ", idAdmin: " + (this.idAdmin != null ? this.idAdmin.toString() : "(null)") + ",
		 * privada: " + (this.privada != null ? this.privada.toString() : "(null)") + ", habilitada: " + (this.habilitada != null ? this.habilitada.toString() : "(null)") + ", iniciacion: " +
		 * this.iniciacion + ", vigenciaRepLegal: " + this.vigenciaRepLegal + "]";
		 */
	}

	public String getIdEmpresaFmt()
	{
		return this.idEmpresaFmt;
	}

	public void setIdEmpresaFmt(String idEmpresaFmt)
	{
		this.idEmpresaFmt = idEmpresaFmt;
	}

	public boolean isEsAdmin()
	{
		return this.esAdmin;
	}

	public void setEsAdmin(boolean esAdmin)
	{
		this.esAdmin = esAdmin;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idEmpresa));
		parametros.put("3", String.valueOf(this.idCasaMatriz));
		parametros.put("4", String.valueOf(this.idAdmin));
		parametros.put("5", String.valueOf(this.idRepLegal));
		parametros.put("6", String.valueOf(this.razonSocial));
		parametros.put("7", String.valueOf(this.privada));
		parametros.put("8", String.valueOf(this.habilitada));
		parametros.put("9", String.valueOf(this.iniciacion));
		parametros.put("10", String.valueOf(this.vigenciaRepLegal));
		return parametros;
	}

	public boolean isEditable()
	{
		return this.editable;
	}

	public void setEditable(boolean editable)
	{
		this.editable = editable;
	}

	public int getGrupoConvenio()
	{
		return this.grupoConvenio;
	}

	public void setGrupoConvenio(int grupoConvenio)
	{
		this.grupoConvenio = grupoConvenio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public int getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}

}
