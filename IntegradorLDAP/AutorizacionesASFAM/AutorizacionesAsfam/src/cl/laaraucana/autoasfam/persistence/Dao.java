package cl.laaraucana.autoasfam.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cl.laaraucana.autoasfam.dto.AutorizacionesMODDto;
import cl.laaraucana.autoasfam.dto.CabeceraAUTDto;
import cl.laaraucana.autoasfam.dto.CabeceraMODDto;
import cl.laaraucana.autoasfam.dto.CargasAUTDto;
import cl.laaraucana.autoasfam.dto.PaginasMODDto;
import cl.laaraucana.autoasfam.dto.PendientesMODDto;
import cl.laaraucana.autoasfam.dto.SuspensionesMODDto;
import cl.laaraucana.autoasfam.dto.TotalesAUTDto;


public interface Dao {

	public List<CabeceraAUTDto> getAllCabeceraAUT(@Param("periodo") String periodo, @Param("rutEmpresa") String rutEmpresa);

	public List<CargasAUTDto> getCargasAfiliadoAUT(@Param("periodo") String periodo,
			@Param("rutEmpresa") String rutEmpresa, @Param("rutAfiliado") String rutAfiliado);

	public List<TotalesAUTDto> getTotalesAUT(@Param("periodo") String periodo, @Param("rutEmpresa") String rutEmpresa,
			@Param("rutAfiliado") String rutAfiliado);
	
	public List<CabeceraMODDto> getCabeceraMOD(@Param("periodo") String periodo, @Param("rutEmpresa") String rutEmpresa);
	
	public List<AutorizacionesMODDto> getAutorizacionesMOD(@Param("periodo") String periodo, @Param("rutEmpresa") String rutEmpresa,
			@Param("oficina") String oficina, @Param("sucursal") String sucursal);
	

	public List<SuspensionesMODDto> getSuspensionesMOD(@Param("periodo") String periodo, @Param("rutEmpresa") String rutEmpresa,
			@Param("oficina") String oficina, @Param("sucursal") String sucursal);
	
	public List<PendientesMODDto> getPendientesMOD(@Param("periodo") String periodo, @Param("rutEmpresa") String rutEmpresa,
			@Param("oficina") String oficina, @Param("sucursal") String sucursal);
	
	public List<PaginasMODDto> getPaginaPenMOD(@Param("periodo") String periodo, @Param("rutEmpresa") String rutEmpresa,
			@Param("oficina") String oficina, @Param("sucursal") String sucursal, @Param("registros_pend") int registros_pend);

}
