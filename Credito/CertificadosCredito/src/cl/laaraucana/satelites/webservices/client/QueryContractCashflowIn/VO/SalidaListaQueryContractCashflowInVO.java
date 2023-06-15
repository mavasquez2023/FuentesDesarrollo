package cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaQueryContractCashflowInVO extends AbstractSalidaVO {
	private List<SalidaQueryContractCashflowInVO> listaCuotas = new ArrayList<SalidaQueryContractCashflowInVO>();
	private String nroCuenta;
	private String lineaComercial;
	private String cantidadTotalCuotas;
	
	//agregado J-Factory 04-12-2019
	private String cuotasPagadas;
	private String cuotasMorosas;
	private String cuotasFuturas;
	private CondonacionCashFlowVO condonacion;

	public SalidaListaQueryContractCashflowInVO() {
	}

	public SalidaListaQueryContractCashflowInVO(List<SalidaQueryContractCashflowInVO> listaCuotas) {
		super();
		this.listaCuotas = listaCuotas;
	}

	public List<SalidaQueryContractCashflowInVO> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(List<SalidaQueryContractCashflowInVO> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getLineaComercial() {
		return lineaComercial;
	}

	public void setLineaComercial(String lineaComercial) {
		this.lineaComercial = lineaComercial;
	}

	public String getCantidadTotalCuotas() {
		return cantidadTotalCuotas;
	}

	public void setCantidadTotalCuotas(String cantidadTotalCuotas) {
		this.cantidadTotalCuotas = cantidadTotalCuotas;
	}

	/**
	 * @return the cuotasPagadas
	 */
	public String getCuotasPagadas() {
		return cuotasPagadas;
	}

	/**
	 * @param cuotasPagadas the cuotasPagadas to set
	 */
	public void setCuotasPagadas(String cuotasPagadas) {
		this.cuotasPagadas = cuotasPagadas;
	}

	/**
	 * @return the cuotasMorosas
	 */
	public String getCuotasMorosas() {
		return cuotasMorosas;
	}

	/**
	 * @param cuotasMorosas the cuotasMorosas to set
	 */
	public void setCuotasMorosas(String cuotasMorosas) {
		this.cuotasMorosas = cuotasMorosas;
	}

	/**
	 * @return the cuotasFuturas
	 */
	public String getCuotasFuturas() {
		return cuotasFuturas;
	}

	/**
	 * @param cuotasFuturas the cuotasFuturas to set
	 */
	public void setCuotasFuturas(String cuotasFuturas) {
		this.cuotasFuturas = cuotasFuturas;
	}

	/**
	 * @return the condonacion
	 */
	public CondonacionCashFlowVO getCondonacion() {
		return condonacion;
	}

	/**
	 * @param condonacion the condonacion to set
	 */
	public void setCondonacion(CondonacionCashFlowVO condonacion) {
		this.condonacion = condonacion;
	}
	
	
}
