package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class InformeReemBancoVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	/** resumen del Reembolso */
	private ReembolsoTotalVO resumenReembolsoTotal;

	/** Detalle por banco **/
	private DetalleBancoVO[] detalleBanco;

	public DetalleBancoVO[] getDetalleBanco() {
		return detalleBanco;
	}

	public void setDetalleBanco(DetalleBancoVO[] detalleBanco) {
		this.detalleBanco = detalleBanco;
	}

	public ReembolsoTotalVO getResumenReembolsoTotal() {
		return resumenReembolsoTotal;
	}

	public void setResumenReembolsoTotal(ReembolsoTotalVO resumenReembolsoTotal) {
		this.resumenReembolsoTotal = resumenReembolsoTotal;
	}


}
