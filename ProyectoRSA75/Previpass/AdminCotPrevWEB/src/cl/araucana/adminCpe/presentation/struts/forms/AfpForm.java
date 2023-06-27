package cl.araucana.adminCpe.presentation.struts.forms;

import org.apache.struts.action.ActionForm;

public class AfpForm extends ActionForm
{

	private static final long serialVersionUID = -468192642461147540L;
	private String _cmd;
	private String codigo;
	private String periodo;
	
	public String get_cmd()
	{
		return this._cmd;
	}
	public void set_cmd(String _cmd)
	{
		this._cmd = _cmd;
	}
	public String getCodigo()
	{
		return this.codigo;
	}
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
}
