package cl.araucana.spl.action.roles;


import java.io.Serializable;

public class InfoUsuarioBO  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6944580014811019469L;
	/** Informacion del Usuario **/
	private InfoUsuarioDVO infoUsuarioDVO;
	/** Opciones a las que tiene acceso **/
	private OpcionDVO[] opcionDVOs;
	
	/** Indica si el usuario es Administrador **/
	private boolean administrador;
	
	public InfoUsuarioDVO getInfoUsuarioDVO() {
		return infoUsuarioDVO;
	}
	public void setInfoUsuarioDVO(InfoUsuarioDVO infoUsuarioDVO) {
		this.infoUsuarioDVO = infoUsuarioDVO;
	}
	public OpcionDVO[] getOpcionDVOs() {
		return opcionDVOs;
	}
	public void setOpcionDVOs(OpcionDVO[] opcionDVOs) {
		this.opcionDVOs = opcionDVOs;
	}
	public boolean isAdministrador() {
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

}