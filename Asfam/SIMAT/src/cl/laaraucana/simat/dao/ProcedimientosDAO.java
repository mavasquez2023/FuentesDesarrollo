package cl.laaraucana.simat.dao;

public interface ProcedimientosDAO {
	public void callProcedureValidar(String periodo) throws Exception;

	public void callProcedureCargaArchivosDB2(String periodo) throws Exception;

	public void callProcedureDistribucion(String periodo) throws Exception;

	public void callProcedureRespaldoHistorico(String periodo, String tabla) throws Exception;
	//public void callProcedureFechaPorDefecto(String tabla)throws Exception;
}
