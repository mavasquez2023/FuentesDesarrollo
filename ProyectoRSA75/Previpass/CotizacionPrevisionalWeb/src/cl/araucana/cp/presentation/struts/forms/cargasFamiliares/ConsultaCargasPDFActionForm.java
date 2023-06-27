package cl.araucana.cp.presentation.struts.forms.cargasFamiliares;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class ConsultaCargasPDFActionForm extends ActionForm
{
	private static final long serialVersionUID = 6687264303807600020L;

	private String periodo;
	private String nombreSucursal;
	private String direccSucursal;
	private String montoTotal;
	private List cargas;
	private boolean cargasPorEmp;

	
	public List getCargas()
	{
		return this.cargas;
	}

	public void setCargas(List cargas)
	{
		this.cargas = cargas;
	}

	public boolean isCargasPorEmp()
	{
		return this.cargasPorEmp;
	}

	public void setCargasPorEmp(boolean cargasPorEmp)
	{
		this.cargasPorEmp = cargasPorEmp;
	}

	public String getPeriodo()
	{
		return this.periodo;
	}

	public void setPeriodo(String periodo)
	{
		this.periodo = periodo;
	}

	public String getDireccSucursal()
	{
		return this.direccSucursal;
	}

	public void setDireccSucursal(String direccSucursal)
	{
		this.direccSucursal = direccSucursal;
	}

	public String getMontoTotal()
	{
		return this.montoTotal;
	}

	public void setMontoTotal(String montoTotal)
	{
		this.montoTotal = montoTotal;
	}

	public String getNombreSucursal()
	{
		return this.nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal)
	{
		this.nombreSucursal = nombreSucursal;
	}
}