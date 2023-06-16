package cl.laaraucana.simat.dao;

import java.util.ArrayList;

import cl.laaraucana.simat.entidades.DatosLicCobVO;

public interface DatosLicCobDAO {
	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception;

	public void Insertar(DatosLicCobVO datosLicCob) throws Exception;

	public void Eliminar(DatosLicCobVO datosLicCob) throws Exception;

	public void Actualizar(DatosLicCobVO datosLicCob) throws Exception;

	public DatosLicCobVO BuscarById(DatosLicCobVO datosLicCob) throws Exception;

	public ArrayList BuscarTodo() throws Exception;

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception;

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception;

	public ArrayList getDatosLicCobByRutBenef(DatosLicCobVO datosLicCob) throws Exception;
}
