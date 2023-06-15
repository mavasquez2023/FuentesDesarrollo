package cl.araucana.wsafiliado.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import cl.araucana.wsafiliado.client.CRM.CRMClient;
import cl.araucana.wsafiliado.sqlmap.SqlMapLocator;
import cl.araucana.wsafiliado.vo.DataCargaVO;
import cl.araucana.wsafiliado.vo.DataAfiliadoVO;
import cl.araucana.wsafiliado.vo.SegmentoVO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_RES;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatus;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusResponse;

public class AfiliacionDAO {

	private static Logger log = Logger.getLogger(AfiliacionDAO.class);
	
	@SuppressWarnings("unchecked")
	public static Map obtenerEstadoAfiliacionDB2(int rutAfiliado) {
		Map estado=null;
		
		try {
			SqlMapClient sqlMap = SqlMapLocator.getInstance();

			estado = (Map)sqlMap.queryForObject("afiliacion.obtenerEstadoAfiliacion", rutAfiliado );
			if(estado==null){
				estado= new HashMap();
				estado.put("ESTADO", new Integer(0));
				estado.put("RUTBENEF", new BigDecimal(0));
			}
		} catch (SQLException e) {
			log.warn("Error en conectar al Sistema " + e.getMessage());
			e.printStackTrace();
		}
		
		return estado;
	}
	
	@SuppressWarnings("unchecked")
	public static Map obtenerEstadoAfiliacionDB2SinCargas(int rutAfiliado) {
		Map estado=null;
		
		try {
			SqlMapClient sqlMap = SqlMapLocator.getInstance();

			estado = (Map)sqlMap.queryForObject("afiliacion.obtenerEstadoAfiliacionSinCargas", rutAfiliado );
			if(estado==null){
				estado= new HashMap();
				estado.put("ESTADO", new Integer(0));
				estado.put("RUTBENEF", new BigDecimal(0));
			}
		} catch (SQLException e) {
			log.warn("Error en conectar al Sistema " + e.getMessage());
			e.printStackTrace();
		}
		
		return estado;
	}
	
	public static int obtenerEstadoAfiliacionCRM(String rutAfiliado) {
		int estado=0;
		
		CRMClient clienteCRM= new CRMClient();
		QueryBPStatusResponse respuesta= clienteCRM.call(rutAfiliado);
		if(respuesta!=null && Integer.parseInt(respuesta.getResultCode())==3){
			for (int i = 0; i < respuesta.getQueryBPStatus().length; i++) {
				QueryBPStatus empresa= respuesta.getQueryBPStatus()[i];
				if(empresa.getEstadoAfiliacion().equals("ACTIVO")){
					estado= 1;
					break;
				}
			}
		}else{
			estado=-1;
		}
		log.info("QueryBPStatus RUT " + rutAfiliado +", estado: "  + estado);
		return estado;
	}
	
	public static List<DataAfiliadoVO> obtenerDataAfiliacionCRM(String rutAfiliado) {
		List<DataAfiliadoVO> salida= null;
		CRMClient clienteCRM= new CRMClient();
		DT_INFO_AFILIADO_RES respuesta= clienteCRM.callInfo(rutAfiliado);
		if(respuesta!=null){
			String[] tipos=null;
			int estados=0;
			if(respuesta.getTRABAJADOR()!=null && respuesta.getPENSIONADO()!=null){
				estados=2;
				tipos= new String[2];
				tipos[0]="TRABAJADOR";
				tipos[1]="PENSIONADO";
				log.info("RUT " + rutAfiliado + ", Tipo: TRA y PEN" );
			}else if(respuesta.getTRABAJADOR()!=null || respuesta.getPENSIONADO()!=null){
				estados=1;
				tipos= new String[1];
				if(respuesta.getTRABAJADOR()!=null){
					tipos[0]="TRABAJADOR";
					log.info("RUT " + rutAfiliado + ", Tipo: TRA" );
				}else if(respuesta.getPENSIONADO()!=null){
					tipos[0]="PENSIONADO";
					log.info("RUT " + rutAfiliado + ", Tipo: PEN" );
				}
			}
			salida= new ArrayList<DataAfiliadoVO>();
			if(estados>0){
				for (int i = 0; i < estados; i++) {
					DataAfiliadoVO dataAfil= new DataAfiliadoVO();
					dataAfil.setNombre(respuesta.getNOMBRE());
					dataAfil.setEstado("1");
					dataAfil.setTipoAfiliado(tipos[i]);
					dataAfil.setCategoria("1");
					salida.add(dataAfil);
				}
			}else{
				log.info("RUT " + rutAfiliado + ", Tipo: DEU" );
			}
		}
		
		return salida;
	}
	
public static int obtenerStatus(){
	
	Integer status;
	try {
		SqlMapClient sqlMap = SqlMapLocator.getInstance();

		status = (Integer)sqlMap.queryForObject("afiliacion.obtenerStatus");
	} catch (SQLException e) {
		e.printStackTrace();
		return 0;
	}
	
	return status.intValue();
}

public static List<DataAfiliadoVO> obtenerDatosAfiliacion(int rutAfiliado) throws SQLException{
	
	
	
	SqlMapClient sqlMap = SqlMapLocator.getInstance();

	List<DataAfiliadoVO> data = (ArrayList<DataAfiliadoVO>)sqlMap.queryForList("afiliacion.obtenerDatosAfiliacion", rutAfiliado );
	
	return data;
}

public static List<DataAfiliadoVO> obtenerDatosAfiliacionSinCargas(int rutAfiliado) throws SQLException{
	
	
	
	SqlMapClient sqlMap = SqlMapLocator.getInstance();

	List<DataAfiliadoVO> data = (ArrayList<DataAfiliadoVO>)sqlMap.queryForList("afiliacion.obtenerDatosAfiliacionSinCargas", rutAfiliado );
	
	return data;
}

public static List<DataCargaVO> obtenerDatosCarga(int rutCarga) throws SQLException{
	
	
	
	SqlMapClient sqlMap = SqlMapLocator.getInstance();

	List<DataCargaVO> data = (ArrayList<DataCargaVO>)sqlMap.queryForList("afiliacion.obtenerDatosCarga", rutCarga );
	
	return data;
}

public static List<DataAfiliadoVO> obtenerDatosExcepcion(int rutAfiliado) throws SQLException{
	
	
	
	SqlMapClient sqlMap = SqlMapLocator.getInstance();

	List<DataAfiliadoVO> data = (ArrayList<DataAfiliadoVO>)sqlMap.queryForList("afiliacion.obtenerDatosExcepcionAfiliado", rutAfiliado );
	
	return data;
}

}