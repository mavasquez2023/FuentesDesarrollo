/*
 * Created on 13-10-2011
 *
 */
package com.microsystem.lme.ibatis.domain;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author microsystem
 *
 */
public class UrlBorderVO implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = -632264219918104L;
	protected Short ideOpe;//o.IDEOPE
	protected String urlOpe;//o.URLOPE
	protected String codOpe;//o.CODOPE
	protected String urldeTOpe;//d.URLDETOPE
	protected String nombreServicio;//d.NOMSER

	/**
	 * @return Returns the codOpe.
	 */
	public String getCodOpe() {
		return codOpe;
	}

	/**
	 * @param codOpe The codOpe to set.
	 */
	public void setCodOpe(String codOpe) {
		this.codOpe = codOpe;
	}

	/**
	 * @return Returns the ideOpe.
	 */
	public Short getIdeOpe() {
		return ideOpe;
	}

	/**
	 * @param ideOpe The ideOpe to set.
	 */
	public void setIdeOpe(Short ideOpe) {
		this.ideOpe = ideOpe;
	}

	/**
	 * @return Returns the nombreServicio.
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * @param nombreServicio The nombreServicio to set.
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * @return Returns the urldeTOpe.
	 */
	public String getUrldeTOpe() {
		return urldeTOpe;
	}

	/**
	 * @param urldeTOpe The urldeTOpe to set.
	 */
	public void setUrldeTOpe(String urldeTOpe) {
		this.urldeTOpe = urldeTOpe;
	}

	/**
	 * @return Returns the urlOpe.
	 */
	public String getUrlOpe() {
		return urlOpe;
	}

	/**
	 * @param urlOpe The urlOpe to set.
	 */
	public void setUrlOpe(String urlOpe) {
		this.urlOpe = urlOpe;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		try {
			Class c = Class.forName(this.getClass().getName());
			Field[] f = c.getDeclaredFields();
			for (int i = 0; i < f.length; i++)
				sb.append(f[i].getName() + "=").append(f[i].get(this)).append("\n");

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
