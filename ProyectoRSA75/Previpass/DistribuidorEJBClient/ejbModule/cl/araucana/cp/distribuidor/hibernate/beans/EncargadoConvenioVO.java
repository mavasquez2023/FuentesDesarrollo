package cl.araucana.cp.distribuidor.hibernate.beans;


public class EncargadoConvenioVO extends EncargadoVO
{
	private static final long serialVersionUID = 830204586378087369L;

	private int idEmpresa;
	private int idConvenio;
	private int idNivelAcceso;

	public EncargadoConvenioVO() 
	{
		super();
	}

	public EncargadoConvenioVO(int idEncargado) 
	{
		super(idEncargado);
	}

	public EncargadoConvenioVO(EncargadoConvenioVO otro) 
	{
		super();
		this.idEmpresa = otro.getIdEmpresa();
		this.idConvenio = otro.getIdConvenio();
		this.idEncargado = otro.getIdEncargado();
		this.idNivelAcceso = otro.getIdNivelAcceso();
	}

	public EncargadoConvenioVO(int idEmpresa, int idConvenio, int idEncargado, int idNivelAcceso) 
	{
		super();
		this.idEmpresa = idEmpresa;
		this.idConvenio = idConvenio;
		this.idEncargado = idEncargado;
		this.idNivelAcceso = idNivelAcceso;
	}
	
	public int getIdConvenio() {
		return this.idConvenio;
	}
	public void setIdConvenio(int idConvenio) {
		this.idConvenio = idConvenio;
	}
	public int getIdEmpresa() {
		return this.idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getIdEncargado() {
		return this.idEncargado;
	}
	public void setIdEncargado(int idEncargado) {
		this.idEncargado = idEncargado;
	}
	public int getIdNivelAcceso() {
		return this.idNivelAcceso;
	}
	public void setIdNivelAcceso(int idNivelAcceso) {
		this.idNivelAcceso = idNivelAcceso;
	}
}
