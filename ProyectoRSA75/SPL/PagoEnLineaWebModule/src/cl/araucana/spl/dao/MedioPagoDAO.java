package cl.araucana.spl.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import cl.araucana.spl.beans.CTACTE;
import cl.araucana.spl.beans.Convenio;
import cl.araucana.spl.beans.MedioPago;

import com.ibatis.dao.client.Dao;

public interface MedioPagoDAO extends Dao {
	public MedioPago getMedioPagoById(BigDecimal idMedioPago);
	public MedioPago getMedioPagoByCodigo(String codigo);
	public List getMediosPago();
	public List getMediosPagoBatch();
	public CTACTE getMontoPagadoPorMedioPago(HashMap map);
	public Convenio getConvenioByCodigoBanco(String codigoBanco);
	public int actualizaMarcaLibroBanco(HashMap map);
}
