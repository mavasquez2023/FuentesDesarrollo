package cl.laaraucana.integracion.vo;

import org.apache.log4j.Logger;

import cl.laaraucana.integracion.impl.ParserXML;

public class DatoEntradaVO {
	private static Logger log = Logger.getLogger(DatoEntradaVO.class);
	private String usuario = null;
	private String password = null;
	private String rutEmpleador = null;
	private String rutTrabajador = null;
	private String periodo = null;
	private String mes=null;
	private String agno=null;
	private String rutEmpl = null;
	private String dvEmpl = null;
	private String rutTrab = null;
	private String dvTrab=null;
	private int agno2=0;
	private String conBitacora = "S";
	
	
	public DatoEntradaVO(
			String _usuario, 
			String _password, 
			String _rutEmpleador,
			String _rutTrabajador,
			String _periodo){
		
		usuario = _usuario;
		password = _password;
		rutEmpleador = _rutEmpleador;
		rutTrabajador = _rutTrabajador;
		periodo = _periodo;
		
		if(_periodo.length()==6){
			mes = _periodo.substring(4);
			agno = _periodo.substring(0,4);
		}else{
			log.warn("periodo no válido:" + _periodo);
		}
		
		/*
		rutEmpl = rutEmpleador.split("-")[0];
		dvEmpl = rutEmpleador.split("-")[1];
		
		rutTrab = rutTrabajador.split("-")[0];
		dvTrab = rutTrabajador.split("-")[1];*/
		
		try {
			agno2 = Integer.parseInt(agno);
		} catch (NumberFormatException e) {
			mes=null;
			agno=null;
			log.warn("periodo no válido:" + _periodo);
		}
		
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getRutEmpleador() {
		return rutEmpleador;
	}
	public void setRutEmpleador(String rutEmpleador) {
		this.rutEmpleador = rutEmpleador;
	}
	public String getRutTrabajador() {
		return rutTrabajador;
	}
	public void setRutTrabajador(String rutTrabajador) {
		this.rutTrabajador = rutTrabajador;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getAgno() {
		return agno;
	}

	public void setAgno(String agno) {
		this.agno = agno;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
	
	public String getDvEmpl() {
		return dvEmpl;
	}

	public void setDvEmpl(String dvEmpl) {
		this.dvEmpl = dvEmpl;
	}

	public String getDvTrab() {
		return dvTrab;
	}

	public void setDvTrab(String dvTrab) {
		this.dvTrab = dvTrab;
	}

	public String getRutEmpl() {
		return rutEmpl;
	}

	public void setRutEmpl(String rutEmpl) {
		this.rutEmpl = rutEmpl;
	}

	public String getRutTrab() {
		return rutTrab;
	}

	public void setRutTrab(String rutTrab) {
		this.rutTrab = rutTrab;
	}
	

	public int getAgno2() {
		return agno2;
	}

	public void setAgno2(int agno2) {
		this.agno2 = agno2;
	}
	
	public String getConBitacora() {
		return conBitacora;
	}

	public void setConBitacora(String conBitacora) {
		this.conBitacora = conBitacora;
	}

	public String toString(){
		return "usuario:"+  usuario +
		", password:"+  password +
		", rutEmpleador:"+  rutEmpleador +
		", rutTrabajador:"+  rutTrabajador +
		", periodo:"+  periodo + 
		", mes:"+  mes +
		", año:"+  agno +
		", conBitacora:"+  conBitacora ;
		
		
		
	}
	
}
