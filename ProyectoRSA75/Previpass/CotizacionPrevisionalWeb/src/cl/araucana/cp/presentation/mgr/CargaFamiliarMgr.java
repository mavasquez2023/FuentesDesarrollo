package cl.araucana.cp.presentation.mgr;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ArchivoCargaFamiliarVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Empresa;
import cl.araucana.cp.distribuidor.presentation.beans.InformacionCargaFamiliar;
import cl.araucana.cp.hibernate.dao.CargaFamiliarDAO;
import cl.araucana.cp.hibernate.dao.EmpresaDAO;
import cl.araucana.cp.presentation.struts.javaBeans.Trabajador;

public class CargaFamiliarMgr {
	
	private CargaFamiliarDAO cargaFamiliarDAO;
	private EmpresaDAO empresaDAO;
	
	public CargaFamiliarMgr(Session session) {
		this.cargaFamiliarDAO = new CargaFamiliarDAO(session);
		this.empresaDAO = new EmpresaDAO(session);
	}

	/**
	 * Obtiene los Trabajadores que informan Cargas Familiares
	 * 
	 * @param cantidad
	 * @param primerReg
	 * @param filtros
	 * @param col
	 * @return
	 * @throws DaoException
	 */
	public List getTrabajadores(HashMap filtros, Collection col) throws DaoException {

		List listado = this.cargaFamiliarDAO.getTrabajadores(filtros, col);
		List listadoInforme = new ArrayList();

		for (int i=0 ; i < listado.size() ; i++) {
			Object[] listaInforme = (Object[]) listado.get(i);
			CargaFamiliarVO cargaFamiliarVO = (CargaFamiliarVO) listaInforme[0];
			//EntidadCCAFVO entidadCaja = (EntidadCCAFVO) listaInforme[1];
			EmpresaVO empresaVO = (EmpresaVO) listaInforme[1];
			Trabajador trab = new Trabajador("" + cargaFamiliarVO.getRutTrabajador());
			trab.setNombre(cargaFamiliarVO.getNombreTrabajador().trim());
			trab.setRut(Utils.formatRut(cargaFamiliarVO.getRutTrabajador()));
			empresaVO.setIdEmpresaFmt(Utils.formatRut(empresaVO.getIdEmpresa()));
			empresaVO.setIdCaja(cargaFamiliarVO.getIdEntidadCCAF());
			trab.getEmpresas().add(empresaVO);
			trab.setIdCaja(cargaFamiliarVO.getIdEntidadCCAF());
			
			listadoInforme.add(trab);
		}

		return listadoInforme;
	}
	
	/**
	 * Obtiene las Empresas que informan Cargas Familiares
	 * 
	 * @param cantidad
	 * @param primerReg
	 * @param filtros
	 * @param col
	 * @return
	 * @throws DaoException
	 */
	public List getEmpresas(HashMap filtros, Collection col) throws DaoException {

		List listaEmpresas = this.cargaFamiliarDAO.getEmpresas(filtros, col);
		List listadoInforme = new ArrayList();
		Object[] listaInforme;
		Empresa empresa;
		for (int i=0 ; i < listaEmpresas.size() ; i++) {
			listaInforme = (Object[]) listaEmpresas.get(i);

			EmpresaVO empresaVO = (EmpresaVO) listaInforme[0];
			EntidadCCAFVO entidadCaja = (EntidadCCAFVO) listaInforme[1];

			empresa = new Empresa();
			empresa.setIdEmpresa(empresaVO.getIdEmpresa());
			empresa.setRazonSocial(empresaVO.getRazonSocial());
			empresa.setRut(Utils.formatRut(empresaVO.getIdEmpresa()));
			empresa.setIdCaja(entidadCaja.getId());
			empresa.setNombreCaja(entidadCaja.getNombre());

			listadoInforme.add(empresa);
		}

		return listadoInforme;
	}

	/**
	 * Obtiene las Cargas Familiares dado un Trabajador y su Empresa
	 * 
	 * @param rutTrabajador
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getCargasPorTrabajador(int rutTrabajador, int rutEmpresa, int idCCAF) throws DaoException {
		
		List listado = this.cargaFamiliarDAO.getCargasPorTrabajador(rutTrabajador, rutEmpresa, idCCAF); 
		List listadoCargas = new ArrayList();
		InformacionCargaFamiliar inf;
		Object[] listaInforme;
		for (int i=0 ; i < listado.size() ; i++){

			inf = new InformacionCargaFamiliar();
			listaInforme = (Object[]) listado.get(i);

			inf.setRutTrabajador(((Integer)listaInforme[0]).intValue());
			inf.setRutTrabajadorFmt(Utils.formatRut((int)inf.getRutTrabajador()));
			inf.setNombreTrabajador(String.valueOf(listaInforme[1]).trim());
			inf.setRutCarga(((Integer)listaInforme[2]).intValue());
			inf.setRutCargaFmt(Utils.formatRut((int)inf.getRutCarga()));
			inf.setNombreCarga(String.valueOf(listaInforme[3]).trim());
			inf.setFecNacCarga(((Timestamp)listaInforme[4]));
			inf.setFecIniVigencia(((Timestamp)listaInforme[5]));
			inf.setFecFinVigencia(((Timestamp)listaInforme[6]));
			inf.setParentesco(String.valueOf(listaInforme[7]).trim());
			inf.setTipo(String.valueOf(listaInforme[8]).trim());
			inf.setRutEmpresa(((Integer)listaInforme[9]).intValue());
			inf.setRutEmpresaFmt(Utils.formatRut(((Integer)listaInforme[9]).intValue()));
			listadoCargas.add(inf);
		}


		return listadoCargas;
	}
	
	/**
	 * Obtiene las Cargas Familiares dado un Trabajador y su Empresa
	 * 
	 * @param rutTrabajador
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public HashMap getCargasPorTrabajadorPDF(int rutTrabajador, int rutEmpresa, int idCCAF) throws DaoException {
		
		HashMap result = new HashMap();

		List listado = this.cargaFamiliarDAO.getCargasPorTrabajador(rutTrabajador, rutEmpresa, idCCAF); 
		List listadoCargas = new ArrayList();
		InformacionCargaFamiliar inf;
		Object[] listaInforme;
		int idTipoCarga;
		int valorTipoCarga;
		int montoTotal = 0;
		String nombreSucursal = new String(" ");
		String direccSucursal = new String(" ");
		
		
		
		if (rutTrabajador != 0) {
			CotizanteVO cotizante = this.cargaFamiliarDAO.getCotizante(rutTrabajador, rutEmpresa);
			if (cotizante != null) {
				SucursalVO sucursal = this.empresaDAO.getSucursal(rutEmpresa, cotizante.getIdSucursal());
				if (sucursal != null) {
					nombreSucursal = sucursal.getNombre();
					direccSucursal = sucursal.getComuna().getNombre().trim() + ", " +
									 sucursal.getComuna().getCiudad().getNombre().trim() + ", " +
									 sucursal.getComuna().getCiudad().getRegion().getNombre().trim();
				}
			}
		}
		
		for (int i=0 ; i < listado.size() ; i++){
			idTipoCarga = 0;
			valorTipoCarga = 0;

			inf = new InformacionCargaFamiliar();
			listaInforme = (Object[]) listado.get(i);

			inf.setRutTrabajador(((Integer)listaInforme[0]).intValue());
			inf.setRutTrabajadorFmt(Utils.formatRut((int)inf.getRutTrabajador()));
			inf.setNombreTrabajador(String.valueOf(listaInforme[1]).trim());
			inf.setRutCarga(((Integer)listaInforme[2]).intValue());
			inf.setRutCargaFmt(Utils.formatRut((int)inf.getRutCarga()));
			inf.setNombreCarga(String.valueOf(listaInforme[3]).trim());
			inf.setFecNacCarga(((Timestamp)listaInforme[4]));
			inf.setFecIniVigencia(((Timestamp)listaInforme[5]));
			inf.setFecFinVigencia(((Timestamp)listaInforme[6]));
			inf.setParentesco(String.valueOf(listaInforme[7]).trim());
			inf.setTipo(String.valueOf(listaInforme[8]).trim());
			inf.setRutEmpresaFmt(Utils.formatRut(((Integer)listaInforme[9]).intValue()));
			inf.setRazonSocialEmpresa(String.valueOf(listaInforme[10]).trim());
			inf.setCasaMatrizEmpresa(String.valueOf(listaInforme[11]).trim());
			idTipoCarga = ((Integer)listaInforme[13]).intValue();
			valorTipoCarga = ((Integer)listaInforme[12]).intValue();			
			valorTipoCarga = idTipoCarga == Constants.TIPO_CARGA_INVALIDEZ ? valorTipoCarga * 2 : valorTipoCarga;
			inf.setValorCargaFamiliar(Utils.formatMonto(valorTipoCarga));
			inf.setNombreCaja(String.valueOf(listaInforme[14]).trim());
			inf.setTramoAsigFam(String.valueOf(listaInforme[15]).trim());
			listadoCargas.add(inf);
			montoTotal += valorTipoCarga;
		}

		result.put("informe", listadoCargas);
		result.put("nombreSucursal", nombreSucursal);
		result.put("direccSucursal", direccSucursal);
		result.put("montoTotal", Utils.formatMonto(montoTotal));

		return result;
	}
	
	/**
	 * Obtiene la Fecha de envio dado la caja
	 * 
	 * @param idCaja
	 * @return ArchivoCargaFamiliarVO
	 * @throws DaoException
	 */
	public List getFechaEnvioByCaja(int idCaja) throws DaoException {
		return cargaFamiliarDAO.getFechaEnvioByCaja(idCaja);		
	}
}