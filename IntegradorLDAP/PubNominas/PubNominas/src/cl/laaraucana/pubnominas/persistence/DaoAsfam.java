package cl.laaraucana.pubnominas.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cl.laaraucana.pubnominas.dto.asfam.AutorizacionesMODDto;
import cl.laaraucana.pubnominas.dto.asfam.CabeceraAUTDto;
import cl.laaraucana.pubnominas.dto.asfam.CabeceraMODDto;
import cl.laaraucana.pubnominas.dto.asfam.CargasAUTDto;
import cl.laaraucana.pubnominas.dto.asfam.PaginasMODDto;
import cl.laaraucana.pubnominas.dto.asfam.PendientesMODDto;
import cl.laaraucana.pubnominas.dto.asfam.SuspensionesMODDto;
import cl.laaraucana.pubnominas.dto.asfam.TotalesAUTDto;


public interface DaoAsfam {

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
