package cl.araucana.ctasfam.business.service;

import java.util.List;
import java.util.Map;

import cl.araucana.ctasfam.presentation.struts.vo.AceptacionPropuesta;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;

public interface PropuestaRentasService {

	public boolean guardarAceptacionPropuesta(
			AceptacionPropuesta aceptacionPropuesta);

	public boolean guardarAceptacionPropuesta(Empresa empresa,
			Encargado encargado);

	public List obtenerAfiliadosPropuestaInformados(int oficina,
			int rutEmpresa, String dvRutEmpresa, int sucursal);

	public List obtenerAfiliadosPropuesta(int oficina, int rutEmpresa,
			String dvRutEmpresa, int sucursal);

	public AceptacionPropuesta obtenerAceptacionPropuesta(Encargado encargado,
			Empresa empresa);

	public List obtenerAfiliadosPropuestaSaldos(int oficina, int rutEmpresa,
			String dvRutEmpresa, int sucursal);
	
}