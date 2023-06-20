package cl.laaraucana.pubnominas.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cl.laaraucana.pubnominas.dto.cotizacion.CabeceraDto;
import cl.laaraucana.pubnominas.dto.cotizacion.CargasDto;
import cl.laaraucana.pubnominas.dto.cotizacion.CotizacionDto;
import cl.laaraucana.pubnominas.dto.cotizacion.RetroactivosDto;
import cl.laaraucana.pubnominas.dto.cotizacion.TramoDto;


public interface DaoCotizacion {

	public List<CabeceraDto> getAllCabecera(@Param("periodo") String periodo, @Param("rutEmpresa") String rutEmpresa, @Param("codigoOficina") String codigoOficina, @Param("codigoSucursal") String codigoSucursal);

	public List<CabeceraDto> getAllCabeceraFull(@Param("periodo") String periodo,
			@Param("rutEmpresa") String rutEmpresa, @Param("sucursal") String sucursal,
			@Param("codOficina") int codOficina);

	public List<TramoDto> getValorTramo(@Param("periodo") String periodo);

	public List<CargasDto> getCargas(@Param("periodo") String periodo, @Param("rutEmpresa") String rutEmpresa,
			@Param("sucursal") String sucursal, @Param("codOficina") String codOficina);

	public List<RetroactivosDto> getRetroactivos(@Param("periodo") String periodo,
			@Param("rutEmpresa") String rutEmpresa, @Param("sucursal") String sucursal,
			@Param("codOficina") String codOficina);

	public CotizacionDto getCotizacion(@Param("periodo") String periodo, @Param("tipo") int tipo);

}
