package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

import cl.araucana.cp.distribuidor.hibernate.beans.AuditableVO;

public class EntidadPagadoraVO extends AuditableVO
{
	private static final long serialVersionUID = -6180133078636847046L;
	private int idEntPagadora;
	private int idCtoBanco;
	private String idCtaCte;
	private String nombre;
	private boolean tieneConvenio;
	private boolean imprime;
	private int idBancoSpl;
	private String idCtaCteSpl;
	private boolean generaCheque;
	private boolean reiniciaFolio;
	
	private String correoContacto;
	private String nombreContacto;
	private String entidadFTP;
	private String carpetaFTP;	
	private String usuarioFTP;
	private String claveFTP;
	private Integer tipoArchMovimiento;

	
	public EntidadPagadoraVO(Integer idEntPagadora, Integer idCtoBanco, String idCtaCte, 
			String nombre, Boolean tieneConvenio, Boolean imprime, Integer idBancoSpl, String idCtaCteSpl, Boolean generaCheque)
	{
		super();
		this.idEntPagadora = idEntPagadora.intValue();
		this.idCtoBanco = idCtoBanco.intValue();
		this.idCtaCte = idCtaCte;
		this.nombre = nombre;
		this.tieneConvenio = tieneConvenio.booleanValue();
		this.imprime = imprime.booleanValue();
		this.idBancoSpl = idBancoSpl.intValue();
		this.idCtaCteSpl = idCtaCteSpl;
		this.generaCheque = generaCheque.booleanValue();
	}
		
	public EntidadPagadoraVO(int idEntPagadora, int idCtoBanco, String idCtaCte, 
			String nombre, boolean tieneConvenio, boolean imprime, int idBancoSpl, String idCtaCteSpl, boolean generaCheque)
	{
		super();
		this.idEntPagadora = idEntPagadora;
		this.idCtoBanco = idCtoBanco;
		this.idCtaCte = idCtaCte;
		this.nombre = nombre;
		this.tieneConvenio = tieneConvenio;
		this.imprime = imprime;
		this.idBancoSpl = idBancoSpl;
		this.idCtaCteSpl = idCtaCteSpl;
		this.generaCheque = generaCheque;
		
	}
	public EntidadPagadoraVO(EntidadPagadoraVO e)
	{
		super();
		this.idEntPagadora = e.getIdEntPagadora();
		this.idCtoBanco = e.getIdCtoBanco();
		this.idCtaCte = e.getIdCtaCte();
		this.nombre = e.getNombre();
		this.tieneConvenio = e.getTieneConvenio();
		this.imprime = e.getImprime();
		this.idBancoSpl = e.getIdBancoSpl();
		this.idCtaCteSpl = e.getIdCtaCteSpl();
		this.generaCheque = e.isGeneraCheque();
		
	}
	public EntidadPagadoraVO()
	{
		super();
		this.idCtaCteSpl = "0";
	}
	
	public int getIdEntPagadora()
	{
		return this.idEntPagadora;
	}
	public void setIdEntPagadora(int idEntPagadora)
	{
		this.idEntPagadora = idEntPagadora;
	}
	
	public int getIdCtoBanco()
	{
		return this.idCtoBanco;
	}
	public void setIdCtoBanco(int idCtoBanco)
	{
		this.idCtoBanco = idCtoBanco;
	}
	
	public String getIdCtaCte()
	{
		return this.idCtaCte;
	}
	public void setIdCtaCte(String idCtaCte)
	{
		this.idCtaCte = idCtaCte;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public boolean getTieneConvenio()
	{
		return this.tieneConvenio;
	}
	public void setTieneConvenio(boolean tieneConvenio)
	{
		this.tieneConvenio = tieneConvenio;
	}
	
	public boolean getImprime()
	{
		return this.imprime;
	}
	public void setImprime(boolean imprime)
	{
		this.imprime = imprime;
	}

	public int getIdBancoSpl()
	{
		return this.idBancoSpl;
	}

	public void setIdBancoSpl(int idBancoSpl)
	{
		this.idBancoSpl = idBancoSpl;
	}

	public String getIdCtaCteSpl()
	{
		return this.idCtaCteSpl;
	}

	public void setIdCtaCteSpl(String idCtaCteSpl)
	{
		this.idCtaCteSpl = idCtaCteSpl;
	}
	
	public boolean isGeneraCheque()
	{
		return this.generaCheque;
	}

	public void setGeneraCheque(boolean generaCheque)
	{
		this.generaCheque = generaCheque;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idEntPagadora));
		parametros.put("2", String.valueOf(this.idCtoBanco));
		parametros.put("3", String.valueOf(this.idCtaCte));
		parametros.put("4", String.valueOf(this.nombre));
		parametros.put("5", String.valueOf(this.tieneConvenio));
		parametros.put("6", String.valueOf(this.imprime));
		parametros.put("7", String.valueOf(this.idBancoSpl));
		parametros.put("8", String.valueOf(this.idCtaCteSpl));
		parametros.put("9", String.valueOf(this.generaCheque));
		return parametros;
	}

	public boolean isReiniciaFolio()
	{
		return this.reiniciaFolio;
	}

	public void setReiniciaFolio(boolean reiniciaFolio)
	{
		this.reiniciaFolio = reiniciaFolio;
	}

	public String getClaveFTP() {
		return claveFTP;
	}

	public void setClaveFTP(String claveFTP) {
		this.claveFTP = claveFTP;
	}

	public String getCorreoContacto() {
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getUsuarioFTP() {
		return usuarioFTP;
	}

	public void setUsuarioFTP(String usuarioFTP) {
		this.usuarioFTP = usuarioFTP;
	}

	public String getCarpetaFTP() {
		return carpetaFTP;
	}

	public void setCarpetaFTP(String carpetaFTP) {
		this.carpetaFTP = carpetaFTP;
	}

	public String getEntidadFTP() {
		return entidadFTP;
	}

	public void setEntidadFTP(String entidadFTP) {
		this.entidadFTP = entidadFTP;
	}

	public Integer getTipoArchMovimiento() {
		return tipoArchMovimiento;
	}

	public void setTipoArchMovimiento(Integer tipoArchMovimiento) {
		this.tipoArchMovimiento = tipoArchMovimiento;
	}

	
}