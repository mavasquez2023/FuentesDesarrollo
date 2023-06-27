package cl.araucana.adminCpe.presentation.mgr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.ConvenioDAO;
import cl.araucana.adminCpe.hibernate.dao.EmpresaDAO;
import cl.araucana.adminCpe.hibernate.dao.LectorDAO;
import cl.araucana.adminCpe.hibernate.dao.PersonaDAO;
import cl.araucana.adminCpe.utils.ProxyLDAP;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaEmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaGrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaSucursalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NivelAccConvSucVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Empresa;
import cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio;
import cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector;
import cl.araucana.cp.mail.EnviarClave;
import cl.araucana.cp.mail.data.MailClave;

/*
 * @(#) PersonaMgr.java 1.30 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.30
 */
public class PersonaMgr
{
	private PersonaDAO personaDao;
	private ConvenioDAO convenioDao;
	private EmpresaDAO empresaDao;
	private LectorDAO lectorDao;

	private static final Logger log = Logger.getLogger(PersonaMgr.class);

	public PersonaMgr(Session session)
	{
		this.personaDao = new PersonaDAO(session);
		this.convenioDao = new ConvenioDAO(session);
		this.empresaDao = new EmpresaDAO(session);
		this.lectorDao = new LectorDAO(session);
	}

	/**
	 * persona
	 * 
	 * @param idPersona
	 * @return
	 * @throws DaoException
	 */
	public PersonaVO getPersona(int idPersona) throws DaoException
	{
		return this.personaDao.getPersona(idPersona);
	}

	/**
	 * guarda persona
	 * 
	 * @param persona
	 * @throws DaoException
	 */
	public void guardaPersona(PersonaVO persona) throws DaoException
	{
		this.personaDao.guardaPersona(persona);
	}

	/**
	 * encargado
	 * 
	 * @param idEncargado
	 * @return
	 * @throws DaoException
	 */
	public EncargadoVO getEncargado(int idEncargado) throws DaoException
	{
		return this.personaDao.getEncargado(idEncargado);
	}

	/**
	 * lector empresa
	 * 
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public LectorEmpresaVO getLectorEmpresa(int idLectorEmpresa) throws DaoException
	{
		return this.personaDao.getLectorEmpresa(idLectorEmpresa);
	}

	/**
	 * elimina encargado lector
	 * 
	 * @param idEncargado
	 * @throws DaoException
	 */
	public void borraEncargadoLector(int idEncargado) throws DaoException
	{
		this.personaDao.borraPermEncargado(idEncargado);
		this.lectorDao.borraPermiso(idEncargado, "idLectorEmpresa", LectorEmpresaSucursalVO.class);
		this.lectorDao.borraPermiso(idEncargado, "idLectorEmpresa", LectorEmpresaConvenioVO.class);
		this.lectorDao.borraPermiso(idEncargado, "idLectorEmpresa", LectorEmpresaEmpresaVO.class);
		this.lectorDao.borraPermiso(idEncargado, "idLectorEmpresa", LectorEmpresaGrupoConvenioVO.class);
		log.info("permisos del usuario:" + idEncargado + ":: borrados exitosamente");
	}

	/**
	 * guarda administrador
	 * 
	 * @param admin
	 * @throws DaoException
	 */
	public void guardaAdministrador(AdministradorVO admin) throws DaoException
	{
		this.personaDao.guardaAdministrador(admin);
	}

	/**
	 * nivel acceso conv sucursal
	 * 
	 * @param idNivel
	 * @return
	 * @throws DaoException
	 */
	public NivelAccConvSucVO getNivelAccesoConvSucu(int idNivel) throws DaoException
	{
		return this.personaDao.getNivelAccesoConvSucu(idNivel);
	}

	/**
	 * lista nivel acceso conv sucursal
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaNivelesAccesoConvSucu() throws DaoException
	{
		return this.personaDao.getListaNivelesAccesoConvSucu();
	}

	/**
	 * guarda acceso encargado lector
	 * 
	 * @param encargado
	 * @param lista
	 * @param listaNew
	 * @throws DaoException
	 */
	public void guardaAccesoEncargadoLector(EncargadoVO encargado, List lista, List listaNew) throws DaoException
	{
		int idUsuario = encargado.getIdEncargado();
		this.personaDao.borraPermEncargado(idUsuario);
		this.lectorDao.borraPermiso(idUsuario, "idLectorEmpresa", LectorEmpresaSucursalVO.class);
		this.lectorDao.borraPermiso(idUsuario, "idLectorEmpresa", LectorEmpresaConvenioVO.class);
		this.lectorDao.borraPermiso(idUsuario, "idLectorEmpresa", LectorEmpresaEmpresaVO.class);
		this.lectorDao.borraPermiso(idUsuario, "idLectorEmpresa", LectorEmpresaGrupoConvenioVO.class);
		log.info("permisos anteriores del usuario:" + idUsuario + ":: borrados exitosamente");

		LectorEmpresaVO lector = new LectorEmpresaVO(idUsuario, encargado.getHabilitado());

		List permsEncargados = new ArrayList();
		guardaPermisos(encargado, lista, idUsuario, lector, permsEncargados);
		guardaPermisos(encargado, listaNew, idUsuario, lector, permsEncargados);
		this.personaDao.guardaPermEncargado(permsEncargados);
	}

	/**
	 * guarda permisos
	 * 
	 * @param encargado
	 * @param lista
	 * @param idUsuario
	 * @param lector
	 * @param permsEncargados
	 * @throws DaoException
	 */
	private void guardaPermisos(EncargadoVO encargado, List lista, int idUsuario, LectorEmpresaVO lector, List permsEncargados) throws DaoException
	{
		boolean permisoGrupo = false;
		boolean flagEnc = false, flagLector = false;
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			GrupoConvenio gc = (GrupoConvenio) it.next();
			if (gc.isPermLector())
			{
				if (!flagLector)
				{
					flagLector = true;
					this.lectorDao.guardaLectorEmpresa(lector);
				}
				this.lectorDao.guardaGrupoConvenio(idUsuario, gc.getIdGrupo());
				permisoGrupo = true;
			}
			boolean permisoEmpresa = false;
			for (Iterator itp = gc.getEmpresas().iterator(); itp.hasNext();)
			{
				Empresa empresa = (Empresa) itp.next();
				if (empresa.isPermLector() && !permisoGrupo)
				{
					if (!flagLector)
					{
						flagLector = true;
						this.lectorDao.guardaLectorEmpresa(lector);
					}
					this.lectorDao.guardaEmpresa(idUsuario, empresa.getIdEmpresa());
					permisoEmpresa = true;
				}
				for (Iterator itC = empresa.getConvenios().iterator(); itC.hasNext();)
				{
					PermEncargadoLector perm = (PermEncargadoLector) itC.next();

					if (perm.isConvenioLector() && !permisoGrupo && !permisoEmpresa)
					{
						if (!flagLector)
						{
							flagLector = true;
							this.lectorDao.guardaLectorEmpresa(lector);
						}
						this.lectorDao.guardaConvenio(idUsuario, empresa.getIdEmpresa(), perm.getIdConvenio());
					} else if (perm.getSucursalLector() != null && perm.getSucursalLector().trim().length() > 0 && !permisoGrupo && !permisoEmpresa)
					{
						if (!flagLector)
						{
							flagLector = true;
							this.lectorDao.guardaLectorEmpresa(lector);
						}
						String sucs[] = perm.getSucursalLector().trim().split("#");
						for (int i = 0; i < sucs.length; i++)
							this.lectorDao.guardaSucursal(idUsuario, empresa.getIdEmpresa(), perm.getIdConvenio(), sucs[i].trim());
					}

					if (perm.getIdNivel() != Constants.NIVEL_ACC_CONV_SUC_NADA)
					{
						if (!flagEnc)
						{
							flagEnc = true;
							this.personaDao.guardaEncargado(encargado, true);
						}
						permsEncargados.add(new EncargadoConvenioVO(empresa.getIdEmpresa(), perm.getIdConvenio(), idUsuario, perm.getIdNivel()));
					}
				}
				permisoEmpresa = false;
			}
			permisoGrupo = false;
		}
		if (lista.size() == 0)
			this.personaDao.guardaEncargado(encargado, false);
	}

	/**
	 * vagurto, quedan todas las empresas como asociadas comentadas.
	 * 
	 * @param idUsuario
	 * @return
	 * @throws DaoException
	 */
	public List getListaEmpresasEsAdmin(int idUsuario) throws DaoException
	{
		List empresas = this.empresaDao.getListaEmpresas();
		List resultado = new ArrayList();
		EmpresaVO empresa, empresaP;
		for (Iterator it = empresas.iterator(); it.hasNext();)
		{
			empresa = (EmpresaVO) it.next();
			empresaP = new EmpresaVO();
			empresaP.setIdEmpresa(empresa.getIdEmpresa());
			empresaP.setIdEmpresaFmt(Utils.formatRut(empresaP.getIdEmpresa()));
			empresaP.setRazonSocial(empresa.getRazonSocial().trim());
			if ((empresa.getIdAdmin() != null) && (empresa.getIdAdmin().intValue() == idUsuario))
				empresaP.setEsAdmin(true);
			else
				empresaP.setEsAdmin(false);
			resultado.add(empresaP);
		}
		return resultado;
	}

	/**
	 * lista empresa es admin
	 * 
	 * @param idUsuario
	 * @return
	 * @throws DaoException
	 */
	public List getEmpresasEsAdmin(int idUsuario) throws DaoException
	{
		List empresas = this.empresaDao.getEmpresasAdministradas(idUsuario);
		List resultado = new ArrayList();
		EmpresaVO empresa, empresaP;
		for (Iterator it = empresas.iterator(); it.hasNext();)
		{
			empresa = (EmpresaVO) it.next();
			empresaP = new EmpresaVO();
			empresaP.setIdEmpresa(empresa.getIdEmpresa());
			empresaP.setIdEmpresaFmt(Utils.formatRut(empresaP.getIdEmpresa()));
			empresaP.setRazonSocial(empresa.getRazonSocial().trim());
			resultado.add(empresaP);
		}
		return resultado;
	}

	/**
	 * lista empresas no es admin
	 * 
	 * @param idUsuario
	 * @param rutEmpresa
	 * @param razonSocial
	 * @return
	 * @throws DaoException
	 */
	public List getEmpresasNoEsAdmin(int idUsuario, int rutEmpresa, String razonSocial) throws DaoException
	{
		List empresas = this.empresaDao.getEmpresasNoAdministradas(idUsuario, rutEmpresa, razonSocial);
		List resultado = new ArrayList();
		EmpresaVO empresa, empresaP;
		for (Iterator it = empresas.iterator(); it.hasNext();)
		{
			empresa = (EmpresaVO) it.next();
			empresaP = new EmpresaVO();
			empresaP.setIdEmpresa(empresa.getIdEmpresa());
			empresaP.setIdEmpresaFmt(Utils.formatRut(empresaP.getIdEmpresa()));
			empresaP.setRazonSocial(empresa.getRazonSocial().trim());
			resultado.add(empresaP);
		}
		return resultado;
	}

	/**
	 * guarda empresa es admin
	 * 
	 * @param admin
	 * @param listaNew
	 * @throws DaoException
	 */
	public void guardaEmpresasEsAdmin(AdministradorVO admin, List listaNew) throws DaoException
	{
		EmpresaVO empresa, empresaP;
		for (Iterator it = listaNew.iterator(); it.hasNext();)
		{
			empresa = (EmpresaVO) it.next();
			empresaP = this.empresaDao.getEmpresa(empresa.getIdEmpresa());
			if (empresa.isEsAdmin())
				empresaP.setIdAdmin(new Long(admin.getIdAdmin()));
		}
	}

	/**
	 * valida deshabilitacion
	 * 
	 * @param idEstado
	 * @param idAdmin
	 * @throws Exception
	 */
	public void validaDeshabilitacion(int idEstado, int idAdmin) throws Exception
	{
		if (idEstado == 1) // admin habilitado, no genera problema
			return;
		List emps = this.empresaDao.getEmpresasAdministradas(idAdmin);
		for (Iterator it = emps.iterator(); it.hasNext();)
			if (((EmpresaVO) it.next()).getHabilitada().intValue() == 1)// empresa habilitada
				throw new Exception("ERROR_EMP_HABILITADA");
	}

	/**
	 * elimina todos permisos admin
	 * 
	 * @param idAdmin
	 * @throws DaoException
	 */
	public void borraTodosPermisosAdmin(int idAdmin) throws DaoException
	{
		this.personaDao.borraTodosPermisosAdmin(idAdmin);
	}

	/**
	 * elimina todos permisos convenio rol lector
	 * 
	 * @param idLector
	 * @throws DaoException
	 */
	public void borraTodosPermisosConvenioRolLector(int idLector) throws DaoException
	{
		this.personaDao.borraTodosPermisosConvenioRolLector(idLector);
	}

	/**
	 * guarda persona
	 * 
	 * @param password
	 * @param persona
	 * @throws DaoException
	 */
	public boolean savePersona(String password, PersonaVO persona) throws DaoException
	{
		this.personaDao.savePersona(persona);
		// TODO descomentar!!
		return ProxyLDAP.createUser(password, persona);
	}

	/**
	 * lista empresa administradas
	 * 
	 * @param idAdmin
	 * @return
	 * @throws DaoException
	 */
	public List getListaEmpresasAdministradas(int idAdmin) throws DaoException
	{
		return this.personaDao.getListaEmpresasAdministradas(idAdmin);
	}

	/**
	 * lista encargado lector
	 * 
	 * @param rut
	 * @param nombre
	 * @param apellido
	 * @param nombreGC
	 * @param codigoGC
	 * @param razonS
	 * @param rutE
	 * @return
	 * @throws DaoException
	 */
	public List getListaEncLector(String rut, String nombre, String apellido, String nombreGC, String codigoGC, String razonS, String rutE) throws Exception
	{
		Set result = new HashSet();
		result.addAll(this.personaDao.getListaEncargados(rut, nombre, apellido, nombreGC, codigoGC, razonS, rutE));
		result.addAll(this.personaDao.getListaLectores(rut, nombre, apellido, nombreGC, codigoGC, razonS, rutE));
		List retorno = new ArrayList(result);
		Collections.sort(retorno);
		return retorno;
	}

	/**
	 * lista administrador
	 * 
	 * @param rut
	 * @param nombre
	 * @param apellido
	 * @param nombreGC
	 * @param codigoGC
	 * @param razonS
	 * @param rutE
	 * @return
	 * @throws DaoException
	 */
	public List getListaAdministrador(String rut, String nombre, String apellido, String nombreGC, String codigoGC, String razonS, String rutE) throws DaoException
	{
		return this.personaDao.getListaAdministrador(rut, nombre, apellido, nombreGC, codigoGC, razonS, rutE);
	}

	/**
	 * administrador
	 * 
	 * @param idEncargado
	 * @return
	 * @throws DaoException
	 */
	public AdministradorVO getAdministrador(int idEncargado) throws DaoException
	{
		return this.personaDao.getAdministrador(idEncargado);
	}

	/**
	 * lista empresas es admin grupo
	 * 
	 * @param idGrupo
	 * @param nombreGrupo
	 * @param idUsuario
	 * @return
	 * @throws DaoException
	 */
	public List getListaEmpresasEsAdminGrupo(String idGrupo, String nombreGrupo, int idUsuario) throws DaoException
	{
		List empresas = this.personaDao.getListaEmpresasGrupoAdmin(idGrupo, nombreGrupo, idUsuario);
		List resultado = new ArrayList();
		EmpresaVO empresa, empresaP;
		for (Iterator it = empresas.iterator(); it.hasNext();)
		{
			empresa = (EmpresaVO) it.next();
			empresaP = new EmpresaVO();
			empresaP.setIdEmpresa(empresa.getIdEmpresa());
			empresaP.setIdEmpresaFmt(Utils.formatRut(empresaP.getIdEmpresa()));
			empresaP.setRazonSocial(empresa.getRazonSocial().trim());
			empresaP.setEsAdmin(true);
			resultado.add(empresaP);
		}
		return resultado;
	}

	/**
	 * guarda password
	 * 
	 * @param newPassword
	 * @param idUsuario
	 * @return
	 */
	public boolean guardarPassword(String newPassword, int idUsuario)
	{
		return ProxyLDAP.changePassword(idUsuario, newPassword);
	}

	/**
	 * transfiere permisos
	 * 
	 * @param rutInicio
	 * @param rutDestino
	 * @throws DaoException
	 */
	public void transferirPermisos(int rutInicio, int rutDestino) throws DaoException
	{
		this.personaDao.transferirPermisos(rutInicio, rutDestino);
	}

	/**
	 * lista niveles acceso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaNivelesAcceso() throws DaoException
	{
		return this.personaDao.getListaNivelesAcceso();
	}

	/**
	 * desc nivel
	 * 
	 * @param id
	 * @param hashNiveles
	 * @return
	 */
	public String getDescNivel(int id, HashMap hashNiveles)
	{
		String key = "" + id;
		return (hashNiveles.containsKey(key) ? (String) hashNiveles.get(key) : "Sin Acceso");
	}

	/**
	 * genera estructura con permisos ya asignados (retorno indice cero), y realiza busqueda por parametros recibidos de permisos aun no asignados (retorno indice 1), sin repetir el mismo permiso en
	 * ambos resultados. si ya viene permisos en lista 'permisos', se reutiliza dicha lista
	 * 
	 * @param idUsuario
	 * @param idEmpresa
	 * @param nombreEmpresa
	 * @param idGrConvenio
	 * @param nombreGrConvenio
	 * @return
	 * @throws Exception
	 */
	public List getPermEncargadoLector(int idUsuario, int idEmpresa, String nombreEmpresa, int idGrConvenio, String nombreGrConvenio) throws Exception
	{
		List result = new ArrayList();
		List tmp = new ArrayList();
		HashMap perm = new HashMap();
		// permisos ya asignados
		cargaPermLector(idUsuario, perm);
		cargaPermEncargado(idUsuario, perm);
		// ordenamiento resultados
		for (Iterator it = perm.values().iterator(); it.hasNext();)
		{
			GrupoConvenio gc = (GrupoConvenio) it.next();
			for (Iterator it2 = gc.getEmpresas().iterator(); it2.hasNext();)
			{
				Empresa emp = (Empresa) it2.next();
				Collections.sort(emp.getConvenios());
			}
			Collections.sort(gc.getEmpresas());
			tmp.add(gc);
		}
		Collections.sort(tmp);
		result.add(tmp);

		// permisos por asignar
		if (idEmpresa > 0 || !"".equals(nombreEmpresa) || idGrConvenio > 0 || !"".equals(nombreGrConvenio))
		{
			List convenios = this.convenioDao.getConveniosEmpGrupo(idEmpresa, nombreEmpresa, idGrConvenio, nombreGrConvenio);
			HashMap sinPerm = cargaSinPermisos(perm, convenios);
			// ordenamiento resultados
			ArrayList tmp2 = new ArrayList();
			for (Iterator it = sinPerm.values().iterator(); it.hasNext();)
			{
				GrupoConvenio gc = (GrupoConvenio) it.next();
				for (Iterator it2 = gc.getEmpresas().iterator(); it2.hasNext();)
				{
					Empresa emp = (Empresa) it2.next();
					Collections.sort(emp.getConvenios());
				}
				Collections.sort(gc.getEmpresas());
				tmp2.add(gc);
			}
			Collections.sort(tmp2);
			result.add(tmp2);
		} else
			// no habia filtro de busqueda
			result.add(new ArrayList());
		return result;
	}

	/**
	 * hashmap carga sin permisos
	 * 
	 * @param perm
	 * @param convenios
	 * @return
	 * @throws DaoException
	 */
	private HashMap cargaSinPermisos(HashMap perm, List convenios) throws DaoException
	{
		HashMap sinPerm = new HashMap();
		GrupoConvenio gc = null;
		Empresa em = null;
		for (Iterator itC = convenios.iterator(); itC.hasNext();)
		{
			ConvenioVO convenio = (ConvenioVO) itC.next();
			boolean yaEsta = convenioExiste(convenio, perm);
			if (!yaEsta)
			{
				if (sinPerm.containsKey("" + convenio.getIdGrupoConvenio()))
					gc = (GrupoConvenio) sinPerm.get("" + convenio.getIdGrupoConvenio());
				else
				{
					GrupoConvenioVO gl = this.convenioDao.getGrupoConvenio(convenio.getIdGrupoConvenio());
					gc = new GrupoConvenio(gl.getIdGrupoConvenio(), false, gl.getNombre());
					sinPerm.put("" + gc.getIdGrupo(), gc);
				}
				em = gc.getEmpresa(convenio.getIdEmpresa());
				if (em == null)
				{
					EmpresaVO empresa = this.empresaDao.getEmpresa(convenio.getIdEmpresa());
					em = new Empresa(convenio.getIdEmpresa(), false, Utils.formatRut(convenio.getIdEmpresa()), empresa.getRazonSocial());
					gc.addEmpresa(em);
				}
				em.addPermEncargadoLector(new PermEncargadoLector(convenio.getIdConvenio(), Constants.NIVEL_ACC_CONV_SUC_NADA, "", false, ""));
			}
		}
		return sinPerm;
	}

	/**
	 * convenio existe
	 * 
	 * @param convenio
	 * @param perm
	 * @return
	 */
	private boolean convenioExiste(ConvenioVO convenio, HashMap perm)
	{
		if (!perm.containsKey("" + convenio.getIdGrupoConvenio()))
			return false; // grupo aun no existe
		GrupoConvenio gc = (GrupoConvenio) perm.get("" + convenio.getIdGrupoConvenio());
		Empresa em = gc.getEmpresa(convenio.getIdEmpresa());
		if (em == null)
			return false; // empresa aun no existe, aunque grupo si
		if (em.getPermEncargadoLector(convenio.getIdConvenio()) == null)
			return false; // convenio aun no existe, aunque grupo y empresa si
		return true;
	}

	/**
	 * carga permisos escargado
	 * 
	 * @param idUsuario
	 * @param perm
	 * @throws DaoException
	 */
	private void cargaPermEncargado(int idUsuario, HashMap perm) throws DaoException
	{
		List permEncargado = this.personaDao.getPermisosEncargado(idUsuario);
		List niveles = getListaNivelesAcceso();
		HashMap hashNiveles = new HashMap();
		for (Iterator it = niveles.iterator(); it.hasNext();)
		{
			NivelAccConvSucVO nivel = (NivelAccConvSucVO) it.next();
			hashNiveles.put("" + nivel.getIdNivelAcceso(), nivel.getNombre().trim());
		}
		GrupoConvenio gc = null;
		Empresa em = null;
		for (Iterator it = permEncargado.iterator(); it.hasNext();)
		{
			EncargadoConvenioVO enc = (EncargadoConvenioVO) it.next();
			ConvenioVO convenio = this.convenioDao.getConvenio(enc.getIdEmpresa(), enc.getIdConvenio());
			if (perm.containsKey("" + convenio.getIdGrupoConvenio()))
				gc = (GrupoConvenio) perm.get("" + convenio.getIdGrupoConvenio());
			else
			{
				GrupoConvenioVO gl = this.convenioDao.getGrupoConvenio(convenio.getIdGrupoConvenio());
				gc = new GrupoConvenio(gl.getIdGrupoConvenio(), false, gl.getNombre());
				perm.put("" + gc.getIdGrupo(), gc);
			}
			em = gc.getEmpresa(convenio.getIdEmpresa());
			if (em == null)
			{
				EmpresaVO empresa = this.empresaDao.getEmpresa(convenio.getIdEmpresa());
				em = new Empresa(convenio.getIdEmpresa(), false, Utils.formatRut(convenio.getIdEmpresa()), empresa.getRazonSocial());
				gc.addEmpresa(em);
			}
			PermEncargadoLector permiso = em.getPermEncargadoLector(enc.getIdConvenio());
			if (permiso == null)
				em.addPermEncargadoLector(new PermEncargadoLector(convenio.getIdConvenio(), enc.getIdNivelAcceso(), getDescNivel(enc.getIdNivelAcceso(), hashNiveles), false, ""));
			else
			{
				permiso.setIdNivel(enc.getIdNivelAcceso());
				permiso.setNombreNivel(getDescNivel(enc.getIdNivelAcceso(), hashNiveles));
			}
		}
	}

	/**
	 * carga permiso lector
	 * 
	 * @param idUsuario
	 * @param perm
	 * @throws Exception
	 */
	private void cargaPermLector(int idUsuario, HashMap perm) throws Exception
	{
		// agrega directamente gruposd e convenios al resultado
		for (Iterator itG = this.lectorDao.getGruposConvenioVO(idUsuario).iterator(); itG.hasNext();)
		{
			GrupoConvenioVO gl = (GrupoConvenioVO) itG.next();
			perm.put("" + gl.getIdGrupoConvenio(), new GrupoConvenio(gl.getIdGrupoConvenio(), true, gl.getNombre()));
		}
		GrupoConvenio gc = null;
		Empresa em = null;
		// agrega convenios al resultado, si no existe grupo lo agrega, si dentro de grupo no existe empresa, la agrega, y agrega convenio
		for (Iterator itC = this.lectorDao.getConveniosVO(idUsuario).iterator(); itC.hasNext();)
		{
			ConvenioVO c = (ConvenioVO) itC.next();

			if (perm.containsKey("" + c.getIdGrupoConvenio()))
				gc = (GrupoConvenio) perm.get("" + c.getIdGrupoConvenio());
			else
			{
				GrupoConvenioVO gl = this.convenioDao.getGrupoConvenio(c.getIdGrupoConvenio());
				gc = new GrupoConvenio(gl.getIdGrupoConvenio(), false, gl.getNombre());
				perm.put("" + gc.getIdGrupo(), gc);
			}
			em = gc.getEmpresa(c.getIdEmpresa());
			if (em == null)
			{
				EmpresaVO empresa = this.empresaDao.getEmpresa(c.getIdEmpresa());
				em = new Empresa(c.getIdEmpresa(), false, Utils.formatRut(c.getIdEmpresa()), empresa.getRazonSocial());
				gc.addEmpresa(em);
			}
			em.addPermEncargadoLector(new PermEncargadoLector(c.getIdConvenio(), 0, "", true, ""));
		}
		// agrega sucursales al resultado, si no existe grupo lo agrega, si dentro de grupo no existe empresa, la agrega, y agrega convenio (para considerar sucursales)
		for (Iterator itS = this.lectorDao.getSucursales(idUsuario).iterator(); itS.hasNext();)
		{
			LectorEmpresaSucursalVO s = (LectorEmpresaSucursalVO) itS.next();
			ConvenioVO conv = this.convenioDao.getConvenio(s.getIdEmpresa(), s.getIdConvenio());

			if (perm.containsKey("" + conv.getIdGrupoConvenio()))
				gc = (GrupoConvenio) perm.get("" + conv.getIdGrupoConvenio());
			else
			{
				GrupoConvenioVO gl = this.convenioDao.getGrupoConvenio(conv.getIdGrupoConvenio());
				gc = new GrupoConvenio(gl.getIdGrupoConvenio(), false, gl.getNombre());
				perm.put("" + gc.getIdGrupo(), gc);
			}
			em = gc.getEmpresa(s.getIdEmpresa());
			if (em == null)
			{
				EmpresaVO empresa = this.empresaDao.getEmpresa(s.getIdEmpresa());
				em = new Empresa(s.getIdEmpresa(), false, Utils.formatRut(s.getIdEmpresa()), empresa.getRazonSocial());
				gc.addEmpresa(em);
			}
			PermEncargadoLector permSuc = em.getPermEncargadoLector(conv.getIdConvenio());
			if (permSuc == null)
				em.addPermEncargadoLector(new PermEncargadoLector(conv.getIdConvenio(), 0, "", false, s.getIdSucursal().trim() + "#"));
			else
				permSuc.setSucursalLector(permSuc.getSucursalLector() + s.getIdSucursal().trim() + "#");
		}
		// agrega empresas al resultado, si no existe grupo lo agrega, si dentro de grupo no existe empresa, la agrega
		for (Iterator itE = this.lectorDao.getEmpresasVO(idUsuario).iterator(); itE.hasNext();)
		{
			EmpresaVO e = (EmpresaVO) itE.next();
			List grupos = this.convenioDao.getGruposConveniosEmpresa(e.getIdEmpresa());
			for (Iterator itG = grupos.iterator(); itG.hasNext();)
			{
				GrupoConvenioVO grupo = (GrupoConvenioVO) itG.next();
				if (perm.containsKey("" + grupo.getIdGrupoConvenio()))
					gc = (GrupoConvenio) perm.get("" + grupo.getIdGrupoConvenio());
				else
				{
					GrupoConvenioVO gl = this.convenioDao.getGrupoConvenio(grupo.getIdGrupoConvenio());
					gc = new GrupoConvenio(gl.getIdGrupoConvenio(), false, gl.getNombre());
					perm.put("" + gc.getIdGrupo(), gc);
				}
				em = gc.getEmpresa(e.getIdEmpresa());
				if (em == null)
				{
					em = new Empresa(e.getIdEmpresa(), true, Utils.formatRut(e.getIdEmpresa()), e.getRazonSocial());
					gc.addEmpresa(em);
				} else
					em.setPermLector(true);
			}
		}
	}

	/**
	 * listasucursales permiso lector
	 * 
	 * @param idUusario
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getSucursalesPermLector(int idUusario, int idEmpresa, int idConvenio) throws DaoException
	{
		return this.lectorDao.getSucursalesVO(idUusario, idEmpresa, idConvenio);
	}
	
	/**
	 * 
	 * @param mailTo
	 * @param fullNombre
	 * @param passwordDefault
	 * @param parametros
	 */
	public void enviarMailClaveInicial(String mailTo, String fullNombre, HashMap parametros, String password)
	{
		if (parametros.containsKey("" + Constants.PARAM_MAIL_HOST_LOCAL) && 
				parametros.containsKey("" + Constants.PARAM_MAIL_FROM) && parametros.containsKey("" + Constants.PARAM_MAIL_USER) && 
				parametros.containsKey("" + Constants.PARAM_MAIL_PASS) &&  parametros.containsKey("" + Constants.PARAM_MAIL_HOST_TO) &&
				parametros.containsKey("" + Constants.PARAM_MAIL_PORT))
		{
			try
			{
				log.info("\n\n\nparametros envio mail con clave:");
				log.info("mailTo:" + mailTo + ":mailHostTo:" + (String) parametros.get("" + Constants.PARAM_MAIL_HOST_TO) + ":mailFrom:" + (String) parametros.get("" + Constants.PARAM_MAIL_FROM) + ":userMail:"
						+ (String) parametros.get("" + Constants.PARAM_MAIL_USER) + ":passMail:" + (String) parametros.get("" + Constants.PARAM_MAIL_PASS) + ":mailHostLocal:" + (String) parametros.get("" + Constants.PARAM_MAIL_HOST_LOCAL) + "::");
				MailClave mail = new MailClave( Integer.parseInt((String)parametros.get("" + Constants.PARAM_MAIL_PORT)),
											mailTo,
											(String)parametros.get("" + Constants.PARAM_MAIL_HOST_TO),
											(String)parametros.get("" + Constants.PARAM_MAIL_FROM),
											(String)parametros.get("" + Constants.PARAM_MAIL_USER),
											(String)parametros.get("" + Constants.PARAM_MAIL_PASS),
											(String)parametros.get("" + Constants.PARAM_MAIL_HOST_LOCAL),
											fullNombre,
											password);


				EnviarClave.enviar(mail);
			} catch (Exception e)
			{
			}
		}
	}

	/**
	 * Asigna al Administrador en una o más Cajas
	 * 
	 * @param idAdminCaja
	 * @param idCCAF
	 * @throws DaoException
	 */
	public void guardaAdministradorCajas(int idAdminCaja, int idCCAF) throws DaoException {
		//Primero se deben borrar los registros (si es que los tuviera).
		this.personaDao.borraAdministradorCajas(idAdminCaja);
		this.personaDao.guardaAdministradorCajas(idAdminCaja, idCCAF);
	}
	/**
	 * Devuelve los registros de las cajas que tiene asociado el usuario 
	 * @param admCaja
	 * @return
	 * @throws DaoException
	 */
	public List getAdmCajas(int admCaja) throws DaoException {
		return this.personaDao.getAdmCajas(admCaja);
	}
}