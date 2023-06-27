package com.microsystem.lme.ibatis.domain;

public class Ilf1000VO extends Ilfe002VO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4300384217596733596L;
	protected Integer licimpnum;
	protected long afiRut;

	public long getAfiRut() {
		return afiRut;
	}

	public void setAfiRut(long afiRut) {
		this.afiRut = afiRut;
	}

	public Integer getLicimpnum() {
		return licimpnum;
	}

	public void setLicimpnum(Integer licimpnum) {
		this.licimpnum = licimpnum;
	}

}
