package cl.araucana.ctasfam.business.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.araucana.ctasfam.business.service.PropuestaRentasService;
import cl.araucana.ctasfam.business.to.AceptacionPropuestaTO;
import cl.araucana.ctasfam.business.to.AfiliadosPropuestaTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AfiliadosPropuestaDao;
import cl.araucana.ctasfam.integration.jdbc.dao.RentaPropuestasDao;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.AfiliadosPropuestaDaoImp;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.RentaPropuestaDaoImpl;
import cl.araucana.ctasfam.integration.jdbc.exception.RentaPropuestasException;
import cl.araucana.ctasfam.presentation.struts.vo.AceptacionPropuesta;
import cl.araucana.ctasfam.presentation.struts.vo.AfiliadosPropuesta;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ea.ctacte.businesslogic.Empresa;

public class PropuestaRentasServiceImpl implements PropuestaRentasService {

	private static final Log log = LogFactory
			.getLog(PropuestaRentasServiceImpl.class);

	private String dataSource;

	private RentaPropuestasDao propuestaDao;

	private AfiliadosPropuestaDao afiliadosPropuestaDao;

	public PropuestaRentasServiceImpl(String dataSource) throws Exception {
		this.dataSource = dataSource;
		this.propuestaDao = new RentaPropuestaDaoImpl(this.dataSource);
		this.afiliadosPropuestaDao = new AfiliadosPropuestaDaoImp(
				this.dataSource);
	}

	public AceptacionPropuesta obtenerAceptacionPropuesta(Encargado encargado,
			cl.araucana.ctasfam.presentation.struts.vo.Empresa empresa) {
		AceptacionPropuesta result = null;
		try {
			Empresa emp = new Empresa(empresa.getRut(), empresa.getDV() + "",
					empresa.getName());
			int rutEmpresa = Integer.parseInt("" + emp.getRut().getRut());
			List resultList = this.propuestaDao.select(rutEmpresa, emp.getRut()
					.getDv(), encargado.getRut(), "" + encargado.getDV());
			if (resultList.size() > 0) {
				AceptacionPropuestaTO temp = (AceptacionPropuestaTO) resultList
						.get(0);
				result = new AceptacionPropuesta();
				BeanUtils.populate(result, BeanUtils.describe(temp));
			}
		} catch (RentaPropuestasException e) {
			log.error("Error: al seleccionar una aprobacion, "
					+ e.getLocalizedMessage(), e);
		} catch (IllegalAccessException e) {
			log.error("Error: al clonar una aprobacion, "
					+ e.getLocalizedMessage(), e);
		} catch (InvocationTargetException e) {
			log.error("Error: al clonar una aprobacion, "
					+ e.getLocalizedMessage(), e);
		} catch (NoSuchMethodException e) {
			log.error("Error: al clonar una aprobacion, "
					+ e.getLocalizedMessage(), e);
		}
		return result;
	}

	public boolean guardarAceptacionPropuesta(
			AceptacionPropuesta aceptacionPropuesta) {
		try {
			long now = Calendar.getInstance().getTime().getTime();
			aceptacionPropuesta.setFechaCreacion(new Date(now));
			aceptacionPropuesta.setHoraCreacion(new Time(now));
			AceptacionPropuestaTO input = new AceptacionPropuestaTO();
			BeanUtils.populate(input, BeanUtils.describe(aceptacionPropuesta));
			this.propuestaDao.insert(input);
		} catch (RentaPropuestasException e) {
			log.error("Error: al insertar una aprobacion, "
					+ e.getLocalizedMessage(), e);
		} catch (IllegalAccessException e) {
			log.error("Error: al clonar una aprobacion, "
					+ e.getLocalizedMessage(), e);
		} catch (InvocationTargetException e) {
			log.error("Error: al clonar una aprobacion, "
					+ e.getLocalizedMessage(), e);
		} catch (NoSuchMethodException e) {
			log.error("Error: al clonar una aprobacion, "
					+ e.getLocalizedMessage(), e);
		}
		return false;
	}

	public boolean guardarAceptacionPropuesta(
			cl.araucana.ctasfam.presentation.struts.vo.Empresa empresa,
			Encargado encargado) {
		AceptacionPropuesta vo = new AceptacionPropuesta();
		vo.setRutEmpresa(empresa.getRut());
		vo.setDvRutEmpresa(empresa.getDV() + "");
		vo.setRazonSocial(empresa.getName());
		vo.setRutEncargado(encargado.getRut());
		vo.setDvRutEncargado(encargado.getDV() + "");
		vo.setMailEncargado("");
		vo.setMailEncargado2("");
		vo.setMailEncargado3("");
		vo.setEstadoProceso("0");
		vo.setFormatoArchivo("");
		vo.setCantiadadArchivos(0);
		return this.guardarAceptacionPropuesta(vo);
	}

	public List obtenerAfiliadosPropuestaInformados(int oficina,
			int rutEmpresa, String dvRutEmpresa, int sucursal) {
		List result = null;
		List daoResult = null;
		AfiliadosPropuestaTO to = new AfiliadosPropuestaTO(oficina, rutEmpresa,
				dvRutEmpresa, sucursal);
		try {
			daoResult = this.afiliadosPropuestaDao.select("<>", to);
			if (daoResult.size() > 0) {
				to = null;
				result = new ArrayList();
				for (Iterator iter = daoResult.iterator(); iter.hasNext();) {
					to = (AfiliadosPropuestaTO) iter.next();
					AfiliadosPropuesta vo = new AfiliadosPropuesta();
					BeanUtils.populate(vo, BeanUtils.describe(to));
					result.add(vo);
				}
			}
		} catch (Exception e) {
			log.error(
					"Error: al obtener afiliados, " + e.getLocalizedMessage(),
					e);
		}
		return result;
	}

	public List obtenerAfiliadosPropuesta(int oficina, int rutEmpresa,
			String dvRutEmpresa, int sucursal) {
		List result = null;
		List daoResult = null;
		AfiliadosPropuestaTO to = new AfiliadosPropuestaTO(oficina, rutEmpresa,
				dvRutEmpresa, sucursal);
		try {
			daoResult = this.afiliadosPropuestaDao.select("=", to);
			if (daoResult.size() > 0) {
				to = null;
				result = new ArrayList();
				for (Iterator iter = daoResult.iterator(); iter.hasNext();) {
					to = (AfiliadosPropuestaTO) iter.next();
					AfiliadosPropuesta vo = new AfiliadosPropuesta();
					BeanUtils.populate(vo, BeanUtils.describe(to));
					result.add(vo);
				}
			}
		} catch (Exception e) {
			log.error(
					"Error: al obtener afiliados, " + e.getLocalizedMessage(),
					e);
		}
		return result;
	}

	public List obtenerAfiliadosPropuestaSaldos(int oficina, int rutEmpresa,
			String dvRutEmpresa, int sucursal) {
		List result = null;
		List daoResult = null;
		AfiliadosPropuestaTO to = new AfiliadosPropuestaTO(oficina, rutEmpresa,
				dvRutEmpresa, sucursal);
		try {
			daoResult = this.afiliadosPropuestaDao.selectPropuestaSaldos(to);
			if (daoResult.size() > 0) {
				to = null;
				result = new ArrayList();
				for (Iterator iter = daoResult.iterator(); iter.hasNext();) {
					to = (AfiliadosPropuestaTO) iter.next();
					AfiliadosPropuesta vo = new AfiliadosPropuesta();
					BeanUtils.populate(vo, BeanUtils.describe(to));
					result.add(vo);
				}
			}
		} catch (Exception e) {
			log.error("Error: al obtener saldo afiliados, "
					+ e.getLocalizedMessage(), e);
		}
		return result;
	}
}