package cl.laaraucana.pubnominas.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cl.laaraucana.pubnominas.dto.pex.CuotaPEXDto;
import cl.laaraucana.pubnominas.dto.pex.OficinaDto;


public interface DaoPex {

	public List<OficinaDto> getOficinas(
			@Param("rutEmpresa") String rutEmpresa,
			@Param("razonSocial") String razonSocial,
			@Param("oficina") String oficina,
			@Param("sucursal") String sucursal
			);

	public List<CuotaPEXDto> getTrabajadores(
			@Param("rutEmpresa") String rutEmpresa, 
			@Param("oficina") String oficina,
			@Param("sucursal") String sucursal);

}
