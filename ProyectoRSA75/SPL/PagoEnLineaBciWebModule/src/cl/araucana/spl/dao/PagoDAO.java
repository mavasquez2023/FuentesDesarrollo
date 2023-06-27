package cl.araucana.spl.dao;

import java.math.BigDecimal;
import java.util.List;

import cl.araucana.spl.beans.FiltroConcluirPago;
import cl.araucana.spl.beans.FiltroInconsistencias;
import cl.araucana.spl.beans.FiltroRendicion;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBci;

public interface PagoDAO {
	//Transacciones BCI:
	public void insert(TransaccionBci trx);
	public TransaccionBci findTransaccionByIdPago(BigDecimal idPago);
	public void updateFinTransaccion(TransaccionBci trx);
	public void updateFinPago(Pago pago);
	
	//Pagos:
	public BigDecimal getIdPago();
	public void insertPago(Pago pago);
	public Pago findPagoById(BigDecimal idPago);
	public List findPagosNoRendidos(FiltroRendicion filtroRendicion);

	public List findInconsistenciasPaginadas(FiltroInconsistencias filtro);
	public List findInconsistenciasPaginaPreviaInvertida(FiltroInconsistencias filtro);
	
	public void updatePagoTrxRendida(Pago pago);
	public int updatePagoTrxCursada(Pago pago);
	public List findFoliosByPago(BigDecimal idPago);
	
	public void updatePagoBitacora(Pago pago);
	public BigDecimal getSumMontoPagoByFiltro(FiltroInconsistencias filtro);
	public BigDecimal getSumMontoBancoByFiltro(FiltroInconsistencias filtro);
	public BigDecimal getCountPagoByFiltro(FiltroInconsistencias filtro);
	public BigDecimal getCountBancoByFiltro(FiltroInconsistencias filtro);
	
	public Pago findPagoByIdPagoIdConvenio(Pago pago);
	
	public List findConcluirPagoBCH(FiltroConcluirPago filtro);
	public List findConcluirPagoBCI(FiltroConcluirPago filtro);
	public List findConcluirPagoBSA(FiltroConcluirPago filtro);
	public List findConcluirPagoBES(FiltroConcluirPago filtro);
	public List findConcluirPagoBIT(FiltroConcluirPago filtro);
	public List findConcluirPagoBBV(FiltroConcluirPago filtro);
}
