package cl.araucana.cp.hibernate.mgr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;

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
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NivelAccConvSucVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.permisos.PermConvenio;
import cl.araucana.cp.distribuidor.hibernate.beans.permisos.PermEmpresa;
import cl.araucana.cp.distribuidor.hibernate.beans.permisos.PermGrupoConvenio;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Empresa;
import cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio;
import cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector;
import cl.araucana.cp.hibernate.dao.ConvenioDAO;
import cl.araucana.cp.hibernate.dao.EmpresaDAO;
import cl.araucana.cp.hibernate.dao.LectorDAO;
import cl.araucana.cp.hibernate.dao.PersonaDAO;

/*
 * @(#) PersonaMgr.java 1.21 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * @author ccappelletti schema ltda
 * 
 * @version 1.21
 */
public class PersonaMgr
{
	private PersonaDAO personaDao;
	private LectorDAO lectorDao;
	private ConvenioDAO convenioDao;
	private EmpresaDAO empresaDao;
	
	private static Logger log = Logger.getLogger(PersonaMgr.class);

	public PersonaMgr(Session session)
	{
		this.lectorDao = new LectorDAO(session);
		this.personaDao = new PersonaDAO(session);
		this.convenioDao = new ConvenioDAO(session);
		this.empresaDao = new EmpresaDAO(session);
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
	 * lista encargados
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getListaEncargados(int idEmpresa, int idConvenio) throws DaoException
	{
		return this.personaDao.getListaEncargados(idEmpresa, idConvenio);
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
	 * guarda encargado
	 * 
	 * @param encargado
	 * @throws DaoException
	 */
	public void guardaEncargado(EncargadoVO encargado) throws DaoException
	{
		this.personaDao.guardaEncargado(encargado);
	}

	/**
	 * 
	 * @param idAdmin
	 * @param idUsuario
	 * @param tipoOperacion
	 * @return
	 * @throws DaoException
	 */
	public List getPermEncLectorAdministrados(int idAdmin, int idUsuario) throws DaoException
	{
		log.info("getPermEncLectorAdministrados: idAdmin:" + idAdmin + ":idUsuario:" + idUsuario + "::");

		// Carga los hash de los permisos lector directos que tenga el usuario
		HashMap gruposLector = this.lectorDao.getGruposLector(idUsuario);// hash de grupos para los que tiene asignado permiso de lector
		HashMap empresasLector = this.lectorDao.getEmpresasLector(idUsuario);// hash de empresas para las que tiene asigando permiso de lector
		HashMap conveniosLector = this.lectorDao.getConveniosLector(idUsuario);// hash de convenios para los que tiene asigando permiso de lector
		HashMap sucursalesLector = this.lectorDao.getSucsLector(idUsuario);// hash de sucursales para las que tiene asigando permiso de lector
		
		List listaGruposAdmin = this.convenioDao.getGruposConveniosAdmin(idAdmin);// lista grupos administrados

		List listaEmpresasAdmin = this.personaDao.getListaEmpresasAdministradas(idAdmin);
		
		HashMap encargadoConvenio = this.personaDao.getConveniosEncargado(idUsuario);// hash de convenios para los que tiene asigando permiso de encargado
		
		/*
		 * Obtiene grupos de convenios administrados, genera estructura jerarquica:
		 * grupo-->empresa-->convenio-->sucursal
		 * 
		 * Si el usuario buscado es nuevo (idUsuario == 0), a todos los convenios por defecto se les da el nivel de acceso "editor",
		 * si el usuario buscado ya existe, se busca en la base de datos los permisos que ya tiene asignados.
		 * Se ordenan las listas
		 * 
		 * tipoOperacion: CREAR = 0; EDITAR = 1; FICHA = 2;
		 */
		HashMap result = new HashMap();		
		for (Iterator it = listaEmpresasAdmin.iterator(); it.hasNext();)
		{
			EmpresaVO empresaVO = (EmpresaVO) it.next();
			log.info("empresa:" + empresaVO.getIdEmpresa() + "::");
			List listaConvenios = this.convenioDao.getConveniosEmpresa(true, empresaVO.getIdEmpresa());
			for (Iterator itC = listaConvenios.iterator(); itC.hasNext();)
			{
				ConvenioVO convenio = (ConvenioVO) itC.next();
				GrupoConvenio grupo = null;
				if (result.containsKey("" + convenio.getIdGrupoConvenio()))
					grupo = (GrupoConvenio) result.get("" + convenio.getIdGrupoConvenio());
				else
				{
					String nombre = "";
					for (Iterator itG = listaGruposAdmin.iterator(); itG.hasNext();)
					{
						GrupoConvenioVO grupoVO = (GrupoConvenioVO) itG.next();
						if (grupoVO.getIdGrupoConvenio() == convenio.getIdGrupoConvenio())
						{
							nombre = grupoVO.getNombre();
							break;
						}
					}
					grupo = new GrupoConvenio(convenio.getIdGrupoConvenio(), gruposLector.containsKey("" + convenio.getIdGrupoConvenio()), nombre);
					result.put("" + convenio.getIdGrupoConvenio(), grupo);
				}
				Empresa empresa = grupo.getEmpresa(convenio.getIdEmpresa());
				if (empresa == null)
				{
					empresa = new Empresa(convenio.getIdEmpresa(), empresasLector.containsKey("" + convenio.getIdEmpresa()), Utils.formatRut(convenio.getIdEmpresa()), empresaVO.getRazonSocial());
					grupo.addEmpresa(empresa);
				}
				int nivel = (encargadoConvenio.containsKey("" + convenio.getIdEmpresa() + "#" + convenio.getIdConvenio()) ? ((Integer) encargadoConvenio.get("" + convenio.getIdEmpresa() + "#"
						+ convenio.getIdConvenio())).intValue() : 0);
				String sucs = (sucursalesLector.containsKey("" + convenio.getIdEmpresa() + "#" + convenio.getIdConvenio()) ? (String) sucursalesLector.get("" + convenio.getIdEmpresa() + "#"
						+ convenio.getIdConvenio()) : "");

				PermEncargadoLector perm = new PermEncargadoLector(convenio.getIdConvenio(), nivel, getDescNivel(nivel, getHashNiveles()), conveniosLector.containsKey("" + convenio.getIdEmpresa() + "#"
						+ convenio.getIdConvenio()), sucs);
				empresa.addPermEncargadoLector(perm);
			}
		}
		
		// ordenamiento resultados
		List resultadoOrdenado = new ArrayList();
		for (Iterator it = result.values().iterator(); it.hasNext();)
		{
			GrupoConvenio gc = (GrupoConvenio) it.next();
			for (Iterator it2 = gc.getEmpresas().iterator(); it2.hasNext();)
			{
				Empresa emp = (Empresa) it2.next();
				Collections.sort(emp.getConvenios());
			}
			Collections.sort(gc.getEmpresas());
			resultadoOrdenado.add(gc);
		}
		Collections.sort(resultadoOrdenado);

		return resultadoOrdenado;
	}

	
	/**
	 * Permisos Modelo Rol Lector Empresa
	 * Deveulve Hashmap de PermGrupoConvenio
	 * 
	 * @param idUsuario
	 * @return lista gerarquica de permisos de lectura
	 * @throws DaoException
	 */
	public HashMap getPermisosRolLectorEmpresa(int idUsuario) throws DaoException
	{
		// Esta será la jerarquia devuelta con estructura de grupo-convenio-->empresa-->convenio-->sucursal
		HashMap grupoConveniosConPermisos = new HashMap();
		
		// Construye la gerarquia de mayor permiso a menor permiso ...
		
		// PERMISOS A NIVÉL DE GRUPO CONVENIO
		getPermisosRolLectorEmpresaGrupoConvenio(idUsuario, grupoConveniosConPermisos);
		
		// PERMISOS A NIVÉL DE EMPRESA
		getPermisosRolLectorEmpresaEmpresa(idUsuario, grupoConveniosConPermisos);

		// PERMISOS A NIVÉL DE EMPRESA CONVENIO
		getPermisosRolLectorEmpresaConvenio(idUsuario, grupoConveniosConPermisos);
		
		// PERMISOS A NIVÉL DE EMPRESA SUCURSAL
		getPermisosRolLectorEmpresaSucursal(idUsuario, grupoConveniosConPermisos);
		
		return grupoConveniosConPermisos;
	}
	
	/**
	 * Carga los permisos debidos a grupo convenio
	 * @param gruposLector lista de permisos a nivél de grupo convenio
	 * @param grupoConveniosConPermisos gerarquia de grupos convenio que se está construyendo
	 * @throws DaoException
	 */
	private void getPermisosRolLectorEmpresaGrupoConvenio (int idUsuario, HashMap grupoConveniosConPermisos) throws DaoException {
		List gruposLector = this.lectorDao.getGruposLectorList(idUsuario); 
		for  (Iterator itGruposLector = gruposLector.iterator(); itGruposLector.hasNext();) {
			// por cada grupo busca las empresas, para estas empresas naturalmente tendrá permiso para todos los convenios
			LectorEmpresaGrupoConvenioVO lector = (LectorEmpresaGrupoConvenioVO) itGruposLector.next();
			log.info("Usuario " + lector.getIdLectorEmpresa() + " tiene permiso de lectura sobre empresas de grupo convenio " + lector.getIdGrupoConvenio());
			PermGrupoConvenio permGrupoConvenio = new PermGrupoConvenio(lector.getIdGrupoConvenio(), true);

			List convenios = convenioDao.getConvenios(lector.getIdGrupoConvenio());
			for (Iterator itConvenios = convenios.iterator(); itConvenios.hasNext();) {
				ConvenioVO convenio = (ConvenioVO) itConvenios.next();
				
				EmpresaVO empresa = empresaDao.getEmpresa(convenio.getIdEmpresa());
				PermEmpresa permEmpresa = new PermEmpresa(empresa, true);
				permGrupoConvenio.getPermEmpresas().put(new Integer(convenio.getIdEmpresa()), permEmpresa);

				for (Iterator itConv = convenioDao.getConveniosEmpresa(true, empresa.getIdEmpresa()).iterator(); itConv.hasNext();) {
					// Agrega individualmente los permisos para todos los convenios (redundante, pero puede ser útil)
					ConvenioVO c = (ConvenioVO) itConv.next();
					PermConvenio permConv = new PermConvenio(c, true);
					permEmpresa.addPermConvenio(permConv);
					
					// Agrega individualmente los permisos para todas las sucursales de cada convenio (redundante, pero puede ser útil)
					permConv.setSucursales(empresaDao.getSucursalesHash(String.valueOf(empresa.getIdEmpresa())));
				}
			}
			grupoConveniosConPermisos.put(new Integer(permGrupoConvenio.getIdGrupoConvenio()), permGrupoConvenio);
		}
	}
	
	/**
	 * Carga los permisos debidos a empresa
	 * @param empresasLector lista de permisos a nivél de empresa
	 * @param grupoConveniosConPermisos gerarquia de grupos convenio que se está construyendo
	 * @throws DaoException
	 */
	private void getPermisosRolLectorEmpresaEmpresa (int idUsuario, HashMap grupoConveniosConPermisos) throws DaoException {
		List empresasLector = this.lectorDao.getEmpresasLectorList(idUsuario);
		for (Iterator itEmpresasLector = empresasLector.iterator(); itEmpresasLector.hasNext();) {
			LectorEmpresaEmpresaVO lector = (LectorEmpresaEmpresaVO) itEmpresasLector.next();
			
			// Hay que encontrar el grupo convenio correspondiente ...
			
			EmpresaVO empresa = empresaDao.getEmpresa(lector.getIdEmpresa());
			PermEmpresa permEmpresa = new PermEmpresa(empresa, true);

			// para encontrar el grupo convenio hay que mirar los convenios
			for (Iterator itConv = convenioDao.getConveniosEmpresa(true, empresa.getIdEmpresa()).iterator(); itConv.hasNext();) {
				ConvenioVO c = (ConvenioVO) itConv.next();

				// si no está el grupo convenio hay que agregarlo con permiso false (el permiso es a nivel de empresa)
				PermGrupoConvenio permGrupoConvenio = (PermGrupoConvenio) grupoConveniosConPermisos.get(new Integer(c.getIdGrupoConvenio()));
				if (permGrupoConvenio == null) {
					permGrupoConvenio = new PermGrupoConvenio(c.getIdGrupoConvenio(), false);
					
					grupoConveniosConPermisos.put(new Integer(permGrupoConvenio.getIdGrupoConvenio()), permGrupoConvenio);					
					permGrupoConvenio.addPermEmpresa(permEmpresa);
					
					// agrega individualmente los permisos para todos los convenios (redundante pero puede ser útil)				
					PermConvenio permConv = new PermConvenio(c, true);
					permEmpresa.addPermConvenio(permConv);
					permConv.setSucursales(empresaDao.getSucursalesHash(String.valueOf(empresa.getIdEmpresa())));
				} else if (permGrupoConvenio.isPermiso() == false) {
					// ... si ya está pero no tiene permiso de gerarquia mayor tiene que agregar el permiso especifico para la empresa
					permGrupoConvenio.addPermEmpresa(permEmpresa);
					
					// agrega individualmente los permisos para todos los convenios (redundante pero puede ser útil)				
					PermConvenio permConv = new PermConvenio(c, true);
					permEmpresa.addPermConvenio(permConv);
					permConv.setSucursales(empresaDao.getSucursalesHash(String.valueOf(empresa.getIdEmpresa())));
				} // ... si está y tiene permiso mayor ... ya estára el permiso también para la empresa ... y no hago nada
			}
		}
	}

	/**
	 * carga los permisos debidos a empresaConvenio
	 * @param idUsuario
	 * @param grupoConveniosConPermisos
	 * @throws DaoException
	 */
	private void getPermisosRolLectorEmpresaConvenio (int idUsuario, HashMap grupoConveniosConPermisos) throws DaoException {
		// permisos a nivel de empresa convenio 
		List conveniosLector = this.lectorDao.getConveniosLectorList(idUsuario);
		for (Iterator itConveniosLector = conveniosLector.iterator(); itConveniosLector.hasNext();) {
			LectorEmpresaConvenioVO lector = (LectorEmpresaConvenioVO) itConveniosLector.next();
			
			ConvenioVO convenio = convenioDao.getConvenio(lector.getIdEmpresa(),lector.getIdConvenio());
			PermGrupoConvenio permGrupoConvenio = (PermGrupoConvenio) grupoConveniosConPermisos.get(new Integer(convenio.getIdGrupoConvenio()));
			
			// si no existe el grupo convenio correspondiente lo creo con permiso a false y con la empresa en objeto del convenio a false 
			if (permGrupoConvenio == null) {
				permGrupoConvenio = new PermGrupoConvenio (convenio.getIdGrupoConvenio(), false);
				grupoConveniosConPermisos.put(new Integer(permGrupoConvenio.getIdGrupoConvenio()), permGrupoConvenio);
				EmpresaVO empresa = empresaDao.getEmpresa(lector.getIdEmpresa());
				PermEmpresa permEmpresa = new PermEmpresa(empresa, false); // false porqué el permiso es a nivel de convenio ... no de empresa
				permGrupoConvenio.addPermEmpresa(permEmpresa);
				
				// agrega individualmente los permisos para el convenio				
				PermConvenio permConv = new PermConvenio(convenio, true);
				permEmpresa.addPermConvenio(permConv);
				// ... y taqmbién las sucursales
				permConv.setSucursales(empresaDao.getSucursalesHash(String.valueOf(empresa.getIdEmpresa())));
				
			// si existe, no tiene el permiso y no tiene la empresa la agregamos
			} else if (permGrupoConvenio.isPermiso() == false && permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()))== null ) {
				EmpresaVO empresa = empresaDao.getEmpresa(lector.getIdEmpresa());
				PermEmpresa permEmpresa = new PermEmpresa(empresa, false); // false porqué el permiso es a nivel de convenio ... no de empresa
				permGrupoConvenio.addPermEmpresa(permEmpresa);
				
				// agrega individualmente los permisos para el convenio				
				PermConvenio permConv = new PermConvenio(convenio, true);
				permEmpresa.addPermConvenio(permConv);
				// ... y taqmbién las sucursales
				permConv.setSucursales(empresaDao.getSucursalesHash(String.valueOf(empresa.getIdEmpresa())));
				
			// si existe perm de grupo pero no tiene el permiso y tiene la empresa pero sin permiso... agregamos el permiso a nivel de convenio				
			} else if (permGrupoConvenio.isPermiso() == false && permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()))!= null
					&& ((PermEmpresa)permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()))).isPermiso() == false ) {
				
				PermEmpresa permEmpresa = (PermEmpresa) permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()));
				PermConvenio permConv = new PermConvenio(convenio, true);
				permEmpresa.addPermConvenio(permConv);
				// ... y taqmbién las sucursales
				permConv.setSucursales(empresaDao.getSucursalesHash(String.valueOf(permEmpresa.getEmpresa().getIdEmpresa())));
			}
			
			// si existe perm de grupo pero no tiene el permiso y tiene la empresa con permisos no hacemos nada
			// si existe perm de grupo con permiso no hacemos nada 

		}		
	}
	
	/**
	 * Carga los permisos a nivél de sucursal
	 * @param idUsuario
	 * @param grupoConveniosConPermisos
	 * @throws DaoException
	 */
	private void getPermisosRolLectorEmpresaSucursal (int idUsuario, HashMap grupoConveniosConPermisos) throws DaoException {
		List sucursalesLector = this.lectorDao.getSucsLectorList(idUsuario);		
		for (Iterator itSucursalesLector = sucursalesLector.iterator(); itSucursalesLector.hasNext();) {
			LectorEmpresaSucursalVO lector = (LectorEmpresaSucursalVO) itSucursalesLector.next();

			ConvenioVO convenio = convenioDao.getConvenio(lector.getIdEmpresa(),lector.getIdConvenio());
			PermGrupoConvenio permGrupoConvenio = (PermGrupoConvenio) grupoConveniosConPermisos.get( new Integer (convenio.getIdGrupoConvenio()) );
			
			if (permGrupoConvenio == null) {
				permGrupoConvenio = new PermGrupoConvenio (convenio.getIdGrupoConvenio(), false);
				grupoConveniosConPermisos.put(new Integer(permGrupoConvenio.getIdGrupoConvenio()), permGrupoConvenio);
				EmpresaVO empresa = empresaDao.getEmpresa(lector.getIdEmpresa());
				PermEmpresa permEmpresa = new PermEmpresa(empresa, false); // false porqué el permiso es a nivel de convenio ... no de empresa
				permGrupoConvenio.addPermEmpresa(permEmpresa);
				
				// agrega individualmente los permisos para el convenio				
				PermConvenio permConv = new PermConvenio(convenio, false);
				permEmpresa.addPermConvenio(permConv);
				SucursalVO s = empresaDao.getSucursal(lector.getIdEmpresa(), lector.getIdSucursal());
				permConv.addSucursal(s);

				// si existe, no tiene el permiso y no tiene la empresa la agregamos
			} else if (permGrupoConvenio.isPermiso() == false && permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()))== null ) {
				EmpresaVO empresa = empresaDao.getEmpresa(lector.getIdEmpresa());
				PermEmpresa permEmpresa = new PermEmpresa(empresa, false); // false porqué el permiso es a nivel de convenio ... no de empresa
				permGrupoConvenio.addPermEmpresa(permEmpresa);
				
				// agrega individualmente los permisos para el convenio				
				PermConvenio permConv = new PermConvenio(convenio, false);
				permEmpresa.addPermConvenio(permConv);
				// ... y taqmbién las sucursales
				SucursalVO s = empresaDao.getSucursal(lector.getIdEmpresa(), lector.getIdSucursal());
				permConv.addSucursal(s);
				
			// si existe perm de grupo pero no tiene el permiso y tiene la empresa pero sin permiso... 
			// y tiene el convenio pero sin permiso	agregamos el permiso a nivel de sucursal
			} else if (permGrupoConvenio.isPermiso() == false && permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()))!= null
					&& ((PermEmpresa)permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()))).isPermiso() == false 
					&& ((PermEmpresa)permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()))).getPermConvenios().get(new Integer(lector.getIdConvenio())) != null
					&& ((PermConvenio)((PermEmpresa)permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()))).getPermConvenios().get(new Integer(lector.getIdConvenio()))).isPermiso() == false ) {
				
				SucursalVO s = empresaDao.getSucursal(lector.getIdEmpresa(), lector.getIdSucursal());
				((PermConvenio)((PermEmpresa)permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()))).getPermConvenios().get(new Integer(lector.getIdConvenio()))).addSucursal(s);

			// si existe perm de grupo pero no tiene el permiso y tiene la empresa pero sin permiso... 
			// y no tiene el convenio agrego todo lo faltante				
			}  else if (permGrupoConvenio.isPermiso() == false && permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()))!= null
					&& ((PermEmpresa)permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()))).isPermiso() == false 
					&& ((PermEmpresa)permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()))).getPermConvenios().get(new Integer(lector.getIdConvenio())) == null ) {
				// agrega individualmente los permisos para el convenio				
				PermConvenio permConv = new PermConvenio(convenio, false);
				((PermEmpresa)permGrupoConvenio.getPermEmpresas().get(new Integer(convenio.getIdEmpresa()))).addPermConvenio(permConv);
				// ... y también las sucursales
				SucursalVO s = empresaDao.getSucursal(lector.getIdEmpresa(), lector.getIdSucursal());
				permConv.addSucursal(s);
			}
			
			// todo lo demás ya está bien...
		}		
	}
	
	
	private ConvenioVO getConvenio (int idConvenio, Collection convenios) {
		for (Iterator i = convenios.iterator(); i.hasNext();) {
			ConvenioVO c = (ConvenioVO) i.next();
			if (c.getIdConvenio() == idConvenio)
				return c;
		}
		return null;
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
	 * lista encargados
	 * 
	 * @param colEmpAdmins
	 * @return
	 * @throws DaoException
	 */
	public List getListaEncargados(Collection colEmpAdmins) throws DaoException
	{
		return this.personaDao.getListaEncargados(colEmpAdmins);
	}

	/**
	 * elimina permisos encargado
	 * 
	 * @param empresas
	 * @param idEncargado
	 * @throws DaoException
	 */
	public void borraPermisosEncargado(Collection empresas, int idEncargado) throws DaoException
	{
		this.personaDao.borraPermisosEncargado(empresas, idEncargado);
	}

	/**
	 * guarda acceso encargado lector
	 * 
	 * @param encargado
	 * @param lista
	 * @throws DaoException
	 */
	public void guardaAccesoEncargadoLector(EncargadoVO encargado, List lista) throws DaoException
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
	 * lista empresas in
	 * 
	 * @param col
	 * @return
	 * @throws DaoException
	 */
	public List getListaEmpresasIn(Collection col) throws DaoException
	{
		return this.personaDao.getListaEmpresasIn(col);
	}

	/**
	 * admin empresa
	 * 
	 * @param login
	 * @return
	 * @throws DaoException
	 */
	public boolean isAdminEmpresa(String login) throws DaoException
	{
		return this.personaDao.isAdminEmpresa(login);
	}

	/**
	 * encargado empresa
	 * 
	 * @param login
	 * @return
	 * @throws DaoException
	 */
	public boolean isEscargadoEmpresa(String login) throws DaoException
	{
		return this.personaDao.isEscargadoEmpresa(login);
	}

	/**
	 * Devuelve la lista de Convenios por encargado
	 * @param idPersona
	 * @return mapa de convenios para el encargado
	 * @throws DaoException 
	 */
	public Map getConveniosEncargado (int idUsuario) throws DaoException {
		return personaDao.getConveniosEncargado(idUsuario);
	}

	/**
	 * Devuelve la lista de empresas para las que el usuario tiene algún permiso
	 * @param idPersona
	 * @return Lista de empresas
	 * @throws DaoException
	 */
	public Collection getEmpresasPermisos (int idUsuario) throws DaoException {
		return empresaDao.getEmpresasPermisos(idUsuario);
	}
	
	/**
	 * dice si es un lector empresa habilitado
	 * @param idUsuario
	 * @return
	 * @throws DaoException
	 */
	public boolean isLectorEmpresaHabilitado (int idUsuario) throws DaoException {
		LectorEmpresaVO lector = lectorDao.getLectorEmpresa(idUsuario);
		return lector == null ? false : lector.getHabilitado() == 1;  
	}

	/**
	 * dice si es un lector entidad habilitado
	 * @param idUsuario
	 * @return
	 * @throws DaoException
	 */
	public boolean isLectorEntidadHabilitado (int idUsuario) throws DaoException {
		LectorEntidadVO lector = lectorDao.getLectorEntidad(idUsuario);
		return lector == null ? false : lector.getHabilitado() == 1;  
	}	

	
	private HashMap getHashNiveles () throws DaoException {
		HashMap hashNiveles = new HashMap();		
		for (Iterator it = getListaNivelesAcceso().iterator(); it.hasNext();)
		{
			NivelAccConvSucVO nivel = (NivelAccConvSucVO) it.next();
			hashNiveles.put("" + nivel.getIdNivelAcceso(), nivel.getDescripcion().trim());
		}
		return hashNiveles;
	}
	
	public LectorEntidadVO getLectorEntidad(int idLectorEntidad) throws DaoException {
		return this.lectorDao.getLectorEntidad(idLectorEntidad);
	}

}