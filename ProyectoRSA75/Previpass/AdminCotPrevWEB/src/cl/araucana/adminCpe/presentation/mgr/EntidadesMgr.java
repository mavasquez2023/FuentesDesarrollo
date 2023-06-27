package cl.araucana.adminCpe.presentation.mgr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.EntidadesDAO;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.CiudadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComunaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadAFCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadExCajaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPagadoraVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSilVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegImpositivoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
 * @(#) EntidadesMgr.java 1.17 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.17
 */
public class EntidadesMgr
{
	static Logger log = Logger.getLogger(EntidadesMgr.class);
	private EntidadesDAO entidadesDAO;

	/**
	 * entidades
	 * 
	 * @param session
	 */
	public EntidadesMgr(Session session)
	{
		this.entidadesDAO = new EntidadesDAO(session);
	}

	/**
	 * eliminar entidad regimen impositivo
	 * 
	 * @param idEntidadExCaja
	 * @param idEntidad
	 */
	public void borraEntRegimenImpositivo(int idEntidadExCaja, int idEntidad)
	{
		this.entidadesDAO.borraEntRegimenImpositivo(idEntidadExCaja, idEntidad);
	}

	/**
	 * eliminar entidad ccaf
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public EntidadCCAFVO getCaja(int id) throws DaoException
	{
		return this.entidadesDAO.getCaja(id);
	}

	/**
	 * ciudad
	 * 
	 * @param idCiudad
	 * @return
	 * @throws DaoException
	 */
	public CiudadVO getCiudad(int idCiudad) throws DaoException
	{
		return this.entidadesDAO.getCiudad(idCiudad);
	}

	/**
	 * lista ciudades
	 * 
	 * @param idRegion
	 * @return
	 * @throws DaoException
	 */
	public List getCiudades(int idRegion) throws DaoException
	{
		return this.entidadesDAO.getCiudades(idRegion);
	}

	/**
	 * lista codigo regimen impositivo
	 * 
	 * @param idExCaja
	 * @return
	 * @throws DaoException
	 */
	public List getCodRegImp(int idExCaja) throws DaoException
	{
		return this.entidadesDAO.getCodRegImp(idExCaja);
	}

	/**
	 * comuna
	 * 
	 * @param idComuna
	 * @return
	 * @throws DaoException
	 */
	public ComunaVO getComuna(int idComuna) throws DaoException
	{
		return this.entidadesDAO.getComuna(idComuna);
	}

	/**
	 * lista comunas
	 * 
	 * @param idCiudad
	 * @return
	 * @throws DaoException
	 */
	public List getComunas(int idCiudad) throws DaoException
	{
		return this.entidadesDAO.getComunas(idCiudad);
	}

	/**
	 * entidad pagadora
	 * 
	 * @param idEntPagadora
	 * @return
	 * @throws DaoException
	 */
	public EntidadPagadoraVO getEntidadPagadora(int idEntPagadora) throws DaoException
	{
		return this.entidadesDAO.getEntidadPagadora(idEntPagadora);
	}

	/**
	 * lista entidad afc
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getEntsAFC() throws DaoException
	{
		return this.entidadesDAO.getEntsAFC();
	}

	/**
	 * entidad afc
	 * 
	 * @param idEntPagadora
	 * @return
	 * @throws DaoException
	 */
	public EntidadAFCVO getEntsAFC(int idEntPagadora) throws DaoException
	{
		return this.entidadesDAO.getEntsAFC(idEntPagadora);
	}

	/**
	 * lista entidad apv
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getEntsApvs() throws DaoException
	{
		return this.entidadesDAO.getEntsApvs();
	}

	/**
	 * lista entidad ccaf
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getEntsCCAF() throws DaoException
	{
		return this.entidadesDAO.getEntsCCAF();
	}

	/**
	 * entidad ccaf
	 * 
	 * @param id
	 * @param idEntPagadora
	 * @return
	 * @throws DaoException
	 */
	public EntidadCCAFVO getEntsCCAF(int id, int idEntPagadora) throws DaoException
	{
		return this.entidadesDAO.getEntsCCAF(id, idEntPagadora);
	}

	/**
	 * entidad ccaf
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public EntidadCCAFVO getEntsCCAF(int id) throws DaoException
	{
		return this.entidadesDAO.getEntsCCAF(id);
	}


	/**
	 * lista entidad ex caja
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getEntsExCaja() throws DaoException
	{
		return this.entidadesDAO.getEntsExCaja();
	}

	/**
	 * lista entidad ex caja
	 * 
	 * @param idEntPagadora
	 * @return
	 * @throws DaoException
	 */
	public List getEntsExCaja(int idEntPagadora) throws DaoException
	{
		return this.entidadesDAO.getEntsExCaja(idEntPagadora);
	}

	/**
	 * lista entidad ex caja
	 * 
	 * @param idEntPagadora
	 * @param codigo
	 * @return
	 * @throws DaoException
	 */
	public List getEntsExCaja(int idEntPagadora, int codigo) throws DaoException
	{
		return this.entidadesDAO.getEntsExCaja(idEntPagadora, codigo);
	}

	/**
	 * lista entidad mutual
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getEntsMutual() throws DaoException
	{
		return this.entidadesDAO.getEntsMutual();
	}

	/**
	 * lista entidad pension
	 * 
	 * @param flag
	 * @return
	 * @throws DaoException
	 */
	public List getEntsPension(boolean flag) throws DaoException
	{
		return this.entidadesDAO.getEntsPension(flag);
	}

	/**
	 * entidad pension
	 * 
	 * @param id
	 * @param idEntPagadora
	 * @param flag
	 * @return
	 * @throws DaoException
	 */
	public EntidadPensionVO getEntsPension(int id, int idEntPagadora, boolean flag) throws DaoException
	{
		return this.entidadesDAO.getEntsPension(id, idEntPagadora, flag);
	}

	/**
	 * lista entidad salud
	 * 
	 * @param flag
	 * @return
	 * @throws DaoException
	 */
	public List getEntsSalud(boolean flag) throws DaoException
	{
		return this.entidadesDAO.getEntidades(flag, Constants.ID_FONASA, EntidadSaludVO.class);
	}

	/**
	 * lista entidad salud
	 * 
	 * @param flag
	 * @return
	 * @throws DaoException
	 */
	public HashMap getEntidadesHash(boolean flag, int valor, Class tipo) throws DaoException
	{
		List lista = this.entidadesDAO.getEntidades(flag, valor, tipo);
		HashMap result = new HashMap();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			EntidadVO ent = (EntidadVO) it.next();
			result.put("" + ent.getId(), ent.getNombre().trim());
		}
		return result;
	}

	/**
	 * entidad salud
	 * 
	 * @param idEntPagadora
	 * @param flag
	 * @return
	 * @throws DaoException
	 */
	public EntidadSaludVO getEntsSalud(int idEntPagadora, boolean flag) throws DaoException
	{
		return this.entidadesDAO.getEntsSalud(idEntPagadora, flag);
	}

	/**
	 * lista entidad sil
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getEntsSIL() throws DaoException
	{
		return this.entidadesDAO.getEntsSIL();
	}

	/**
	 * existe
	 * 
	 * @param obj
	 * @param id
	 * @param col
	 * @param id2
	 * @param col2
	 * @return
	 */
	public boolean getExiste(Class obj, int id, String col, int id2, String col2)
	{
		return this.entidadesDAO.getExiste(obj, id, col, id2, col2);
	}

	/**
	 * lista generos
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getGeneros() throws DaoException
	{
		return this.entidadesDAO.getGeneros();
	}

	/**
	 * entidad mutual
	 * 
	 * @param idMutual
	 * @return
	 * @throws DaoException
	 */
	public EntidadMutualVO getMutual(int idMutual) throws DaoException
	{
		return this.entidadesDAO.getMutual(idMutual);
	}

	/**
	 * region
	 * 
	 * @param idRegion
	 * @return
	 * @throws DaoException
	 */
	public RegionVO getRegion(int idRegion) throws DaoException
	{
		return this.entidadesDAO.getRegion(idRegion);
	}

	/**
	 * lista regiones
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getRegiones() throws DaoException
	{
		return this.entidadesDAO.getRegiones();
	}

	/**
	 * lista tipo movimiento personal
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getTiposMovPersonal() throws DaoException
	{
		return this.entidadesDAO.getTiposMovPersonal();
	}

	/**
	 * lista tramos asignacion familiar
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getTramosAsigFam() throws DaoException
	{
		return this.entidadesDAO.getTramosAsigFam();
	}

	/**
	 * guardar entidad regimen empositivo
	 * 
	 * @param regimen
	 * @throws DaoException
	 */
	public void guardaEntsRegimenImpositivo(RegImpositivoVO regimen) throws DaoException
	{
		this.entidadesDAO.guardaEntsRegimenImpositivo(regimen);
	}

	/**
	 * consulta banco asociado entidad
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public int consultaBancoAociadoEntidad(int id) throws DaoException
	{
		return this.entidadesDAO.consultaBancoAociadoEntidad(id);
	}

	/**
	 * nueva entidad regimen impositivo
	 * 
	 * @param regimen
	 * @throws DaoException
	 */
	public void nuevaEntsRegimenImpositivo(RegImpositivoVO regimen) throws DaoException
	{
		this.entidadesDAO.nuevaEntsRegimenImpositivo(regimen);
	}

	/**
	 * reinicio folios
	 * 
	 * @throws DaoException
	 */
	public void reinicioFolios() throws DaoException
	{
		this.entidadesDAO.reinicioFolios();
	}

	/**
	 * lista entidad pension
	 * 
	 * @param flag
	 * @param sacarNinguna
	 * @return
	 * @throws DaoException
	 */
	public List getEntsPension(boolean flag, boolean sacarNinguna) throws DaoException
	{
		return this.entidadesDAO.getEntsPension(flag, sacarNinguna);
	}

	/**
	 * tipos movimiento personal af
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getTiposMovPersonalAF() throws DaoException
	{
		return this.entidadesDAO.getTiposMovPersonalAF();
	}

	/**
	 * lista regimen impositivo
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getRegImp() throws DaoException
	{
		return this.entidadesDAO.getRegImp();
	}

	/**
	 * lista regimen impositivo
	 * 
	 * @param idExCaja
	 * @return
	 * @throws DaoException
	 */
	public List getRegImp(int idExCaja) throws DaoException
	{
		return this.entidadesDAO.getRegImp(idExCaja);
	}

	/**
	 * regimen impositivo
	 * 
	 * @param idExCaja
	 * @param idEntidad
	 * @return
	 * @throws DaoException
	 */
	public RegImpositivoVO getRegImp(int idExCaja, int idEntidad) throws DaoException
	{
		return this.entidadesDAO.getRegImp(idExCaja, idEntidad);
	}

	/**
	 * actualiza mapeo
	 * 
	 * @param idEntPagadora
	 * @param nuevoCodigo
	 * @param tipo
	 * @throws DaoException
	 */
	public void creaMapeos(EntidadVO entidad) throws DaoException
	{
		this.entidadesDAO.creaMapeos(entidad);
	}

	/**
	 * entidad ex caja
	 * 
	 * @param idEntPagadora
	 * @param idEntidad
	 * @return
	 * @throws DaoException
	 */
	public EntidadExCajaVO getEntExCaja(int idEntPagadora, int idEntidad) throws DaoException
	{
		return this.entidadesDAO.getEntExCaja(idEntPagadora, idEntidad);
	}

	/**
	 * actualizacion entidad ex caja
	 * 
	 * @param entidadExCajaVO
	 * @return
	 * @throws DaoException
	 */
	public boolean actualizaEntidadExCaja(EntidadExCajaVO entidadExCajaVO) throws DaoException
	{
		return this.entidadesDAO.actualizaEntidadExCaja(entidadExCajaVO);
	}

	/**
	 * elimina regimen
	 * 
	 * @param id
	 */
	public int eliminaRegimen(int id)
	{
		return this.entidadesDAO.eliminaRegimen(id);
	}

	/**
	 * guarda regimen
	 * 
	 * @param listaRegimenVO
	 */
	public int guardaRegimen(List listaRegimenVO)
	{
		return this.entidadesDAO.guardaRegimen(listaRegimenVO);
	}

	/**
	 * actualiza regimen
	 * 
	 * @param listaRegimenVO
	 */
	public int actualizaRegimen(List listaRegimenVO)
	{
		return this.entidadesDAO.actualizaRegimen(listaRegimenVO);
	}

	/**
	 * guarda entidad ex caja
	 * 
	 * @param entidadExCajaVO
	 * @throws DaoException
	 */
	public int guardarEntExCaja(EntidadExCajaVO entidadExCajaVO)
	{
		return this.entidadesDAO.guardarEntExCaja(entidadExCajaVO);
	}

	/**
	 * elimina entidad ex caja
	 * 
	 * @param i
	 */
	public int eliminaEntidadExCaja(int i)
	{
		return this.entidadesDAO.eliminaEntidadExCaja(i);
	}

	/**
	 * @param entidadPagadoraVO
	 * @return
	 */
	public int updateEntidadPagadora(boolean force, EntidadPagadoraVO entidadPagadoraVO)
	{
		return this.entidadesDAO.updateEntidadPagadora(force, entidadPagadoraVO);
	}

	/**
	 * @param tipo
	 * @param idCodAntiguo
	 * @param entidad
	 * @return
	 */
	public int updateEntidad(boolean flag, Class tipo, int idCodAntiguo, EntidadVO entidad)
	{
		return this.entidadesDAO.updateEntidad(flag, tipo, idCodAntiguo, entidad);
	}

	/**
	 * @param tipo
	 * @param entidad
	 * @return
	 */
	public int delEntidad(Class tipo, EntidadVO entidad)
	{
		return this.entidadesDAO.delEntidad(tipo, entidad);
	}

	/**
	 * @param entidadPagadoraVO
	 * @return
	 */
	public int delEntidadPagadora(EntidadPagadoraVO entidadPagadoraVO)
	{
		return this.entidadesDAO.delEntidadPagadora(entidadPagadoraVO);
	}

	/**
	 * @param entidadPagadoraVO
	 * @return
	 */
	public boolean saveEntidadPagadora(EntidadPagadoraVO entidadPagadoraVO, boolean flag)
	{
		return this.entidadesDAO.saveEntidadPagadora(entidadPagadoraVO, flag);
	}

	/**
	 * @param tipo
	 * @param entidad
	 * @return
	 */
	public boolean saveEntidad(Class tipo, EntidadVO entidad)
	{
		return this.entidadesDAO.saveEntidad(tipo, entidad);
	}

	/**
	 * @param idEntidadPagadora
	 * @return
	 * @throws DaoException
	 */
	public EntidadSilVO getEntsSil(int idEntidadPagadora) throws DaoException
	{
		return this.entidadesDAO.getEntsSil(idEntidadPagadora);
	}

	/**
	 * @param idEntidadPagadora
	 * @return
	 * @throws DaoException
	 */
	public EntidadMutualVO getEntsMutual(int idEntidadPagadora) throws DaoException
	{
		return this.entidadesDAO.getEntsMutual(idEntidadPagadora);
	}

	/**
	 * @param idEntidadPagadora
	 * @return
	 * @throws DaoException
	 */
	public EntidadApvVO getEntsApv(int idEntidadPagadora) throws DaoException
	{
		return this.entidadesDAO.getEntsApv(idEntidadPagadora);
	}

	/**
	 * @param idEntidadPagadora
	 * @return
	 * @throws DaoException
	 */
	public EntidadPensionVO getEntsPension(int idEntidadPagadora) throws DaoException
	{
		return this.entidadesDAO.getEntsPension(idEntidadPagadora);
	}

	/**
	 * @param idEntidadPagadora
	 * @return
	 * @throws DaoException
	 */
	public EntidadCCAFVO getEntsCcaf(int idEntidadPagadora) throws DaoException
	{
		return this.entidadesDAO.getEntsCcaf(idEntidadPagadora);
	}
}
