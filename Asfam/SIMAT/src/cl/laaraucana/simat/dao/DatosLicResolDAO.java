package cl.laaraucana.simat.dao;

import java.util.ArrayList;

import cl.laaraucana.simat.entidades.DatosLicResolVO;

public interface DatosLicResolDAO {
	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception;

	public void Insertar(DatosLicResolVO datosLicResol) throws Exception;

	public void Eliminar(DatosLicResolVO datosLicResol) throws Exception;

	public void Actualizar(DatosLicResolVO datosLicResol) throws Exception;

	public DatosLicResolVO BuscarById(DatosLicResolVO datosLicResol) throws Exception;

	public ArrayList BuscarTodo() throws Exception;

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception;

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception;

	public ArrayList getDatosLicResolByRutBenef(DatosLicResolVO datosLicResol) throws Exception;
}
