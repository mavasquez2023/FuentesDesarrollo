package cl.araucana.cp.presentation.mgr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.dao.ConvenioDAO;

/*
 * @(#) ConvenioMgr.java 1.20 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author vagurto
 * 
 * @version 1.20
 */
public class ConvenioMgr
{
	private ConvenioDAO convenioDao;

	public ConvenioMgr(Session session)
	{
		this.convenioDao = new ConvenioDAO(session);
	}

	/**
	 * convenio
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public ConvenioVO getConvenio(int idEmpresa, int idConvenio) throws DaoException
	{
		return this.convenioDao.getConvenio(idEmpresa, idConvenio);
	}

	/**
	 * caja
	 * 
	 * @param idCaja
	 * @return
	 * @throws DaoException
	 */
	public EntidadCCAFVO getCaja(int idCaja) throws DaoException
	{
		return this.convenioDao.getCaja(idCaja);
	}

	/**
	 * mutual
	 * 
	 * @param idMutual
	 * @return
	 * @throws DaoException
	 */
	public EntidadMutualVO getMutual(int idMutual) throws DaoException
	{
		return this.convenioDao.getMutual(idMutual);
	}

	/**
	 * grupo convenio
	 * 
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public GrupoConvenioVO getGrupoConvenio(int idGrupoConvenio) throws DaoException
	{
		return this.convenioDao.getGrupoConvenio(idGrupoConvenio);
	}

	/**
	 * grupo convenios
	 * 
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public GrupoConvenioVO getGrupoConvenioGet(int idGrupoConvenio) throws DaoException
	{
		return this.convenioDao.getGrupoConvenioGet(idGrupoConvenio);
	}

	/**
	 * grupo convenios
	 * 
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public boolean getGrupoConvenioGetActivo(int idGrupoConvenio) throws DaoException
	{
		return this.convenioDao.getGrupoConvenioGetActivo(idGrupoConvenio);
	}

	/**
	 * convenios empresa
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getConveniosEmpresa(int idEmpresa) throws DaoException
	{
		return this.convenioDao.getConveniosEmpresa(false, idEmpresa);
	}

	public List getConveniosPermisos(boolean flag, int idPersona, int rutEmpresa) throws DaoException
	{
		return this.convenioDao.getConveniosPermisos(idPersona, rutEmpresa, flag);
	}

	public List getConveniosPermisos(int idPersona, Set listaEmpresas) throws DaoException
	{
		ArrayList listaConvenios = new ArrayList();
		for (Iterator it = listaEmpresas.iterator(); it.hasNext();)
		{
			int rutEmpresa = ((Integer) it.next()).intValue();
			listaConvenios.addAll(this.convenioDao.getConveniosPermisos(idPersona, rutEmpresa, true));
		}
		return listaConvenios;
	}

	public List getConveniosEscritura(int idPersona, Set listaEmpresas) throws DaoException
	{
		ArrayList listaConvenios = new ArrayList();
		for (Iterator it = listaEmpresas.iterator(); it.hasNext();)
		{
			int rutEmpresa = ((Integer) it.next()).intValue();
			listaConvenios.addAll(this.convenioDao.getConveniosEscritura(idPersona, rutEmpresa));
		}
		return listaConvenios;
	}

	/**
	 * guarda convenio
	 * 
	 * @param convenio
	 * @throws DaoException
	 */
	public void guardaConvenio(ConvenioVO convenio) throws DaoException
	{
		GrupoConvenioVO gConvenio = this.convenioDao.getGrupoConvenio(convenio.getIdGrupoConvenio());
		convenio.setHabilitado(Constants.COD_HABILITACION_CONVENIO);
		convenio.setIdMapaCod(gConvenio.getIdMapaCod());
		convenio.setIdMapaNomRem(gConvenio.getIdMapaNomRem());
		convenio.setIdMapaNomGra(gConvenio.getIdMapaNomGra());
		convenio.setIdMapaNomRel(gConvenio.getIdMapaNomRel());
		convenio.setIdMapaNomDep(gConvenio.getIdMapaNomDep());
		convenio.setIdOpcion(gConvenio.getIdOpcion());

		List listaConvenio = this.convenioDao.getConveniosEmpresa(false, convenio.getIdEmpresa());
		ConvenioVO convenioEmpresa = (ConvenioVO) listaConvenio.get(0);
		convenio.setIdMutual(convenioEmpresa.getIdMutual());
		convenio.setMutualCalculoIndividual(convenioEmpresa.getMutualCalculoIndividual());
		convenio.setMutualNumeroAdherente(convenioEmpresa.getMutualNumeroAdherente());
		convenio.setMutualTasaAdicional(convenioEmpresa.getMutualTasaAdicional());

		Date hoy = new Date();
		convenio.setCreado(new java.sql.Date(hoy.getTime()));
		convenio.setUltimoUso(convenio.getCreado());

		convenio.setNumNominas(0);
		convenio.setNumNominasCorregidas(0);
		convenio.setNumNominasOk(0);
		convenio.setNumCotizaciones(0);
		convenio.setNumCotizacionesCorregidas(0);
		convenio.setNumCotizacionesOk(0);
		convenio.setNumBloqueos(0);

		this.convenioDao.guardarConvenio(convenio);
	}

	/**
	 * convenios empresa in
	 * 
	 * @param colEmps
	 * @return
	 * @throws DaoException
	 */
	public List getConveniosEmpresasIn(Collection colEmps) throws DaoException
	{
		return this.convenioDao.getConveniosEmpresasIn(colEmps);
	}

	/**
	 * grupos convenios in
	 * 
	 * @param colGConvs
	 * @return
	 * @throws DaoException
	 */
	public List getGruposConveniosIn(Collection colGConvs) throws DaoException
	{
		return this.convenioDao.getGruposConveniosIn(colGConvs);
	}

	/**
	 * grupos convenios admin
	 * 
	 * @param idPersona
	 * @return
	 * @throws DaoException
	 */
	public List getGruposConveniosAdmin(int idPersona) throws DaoException
	{
		return this.convenioDao.getGruposConveniosAdmin(idPersona);
	}

	/**
	 * convenios in
	 * 
	 * @param colConvs
	 * @return
	 * @throws DaoException
	 */
	public List getConveniosIn(Collection colConvs) throws DaoException
	{
		return this.convenioDao.getConveniosIn(colConvs);
	}

	/**
	 * convenios no excp
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public ConvenioVO getConvenioNoExcp(int idEmpresa, int idConvenio) throws DaoException
	{
		return this.convenioDao.getConvenioNoExcp(idEmpresa, idConvenio);
	}

	/**
	 * lista niveles acceso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaNivelesAcceso() throws DaoException
	{
		return this.convenioDao.getListaNivelesAcceso();
	}

	/**
	 * valida grupo
	 * 
	 * @param convenio
	 * @return
	 * @throws DaoException
	 */
	public boolean validaMapaNomGrupo(int idGrupoConv, String idsTiposNominas)
	{
		return this.convenioDao.validaMapaNomGrupo(idGrupoConv, idsTiposNominas);
	}

	/**
	 * Entrega la glosa del error 904 de Cargas Familiares para su despliegue en el detalle del trabajador
	 * 
	 * @param tipoProceso
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param idCotizPendiente
	 * @return
	 */
	public String errorCargaFamiliarOtraCaja(char tipoProceso, int rutEmpresa, int idConvenio, String idCotizPendiente) {
		return this.convenioDao.errorCargaFamiliarOtraCaja(tipoProceso, rutEmpresa, idConvenio, idCotizPendiente);
	}
}
