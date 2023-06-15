package cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO;


import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;


public class CondonacionCashFlowVO extends AbstractSalidaVO {

	private double cond_gravamenes= 0.0;
	private double cond_gasto_cobranza=0.0;
	private double cond_honorarios=0.0;
	
	public CondonacionCashFlowVO(){}
	
	public CondonacionCashFlowVO(double cond_gravamenes, double cond_gasto_cobranza, double cond_honorarios) {
		super();
		
		this.cond_gravamenes = cond_gravamenes;
		this.cond_gasto_cobranza = cond_gasto_cobranza;
		this.cond_honorarios = cond_honorarios;
	
	}

	/**
	 * @return the cond_gravamenes
	 */
	public double getCond_gravamenes() {
		return cond_gravamenes;
	}

	/**
	 * @param cond_gravamenes the cond_gravamenes to set
	 */
	public void setCond_gravamenes(double cond_gravamenes) {
		this.cond_gravamenes = cond_gravamenes;
	}

	/**
	 * @return the cond_gasto_cobranza
	 */
	public double getCond_gasto_cobranza() {
		return cond_gasto_cobranza;
	}

	/**
	 * @param cond_gasto_cobranza the cond_gasto_cobranza to set
	 */
	public void setCond_gasto_cobranza(double cond_gasto_cobranza) {
		this.cond_gasto_cobranza = cond_gasto_cobranza;
	}

	/**
	 * @return the cond_honorarios
	 */
	public double getCond_honorarios() {
		return cond_honorarios;
	}

	/**
	 * @param cond_honorarios the cond_honorarios to set
	 */
	public void setCond_honorarios(double cond_honorarios) {
		this.cond_honorarios = cond_honorarios;
	}
	

	}
