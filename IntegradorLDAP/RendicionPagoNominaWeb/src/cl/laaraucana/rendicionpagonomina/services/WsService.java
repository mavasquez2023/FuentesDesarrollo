package cl.laaraucana.rendicionpagonomina.services;


import cl.laaraucana.rendicionpagonomina.vo.EnvioNominaVo;
import cl.laaraucana.rendicionpagonomina.vo.ParamRendicionVO;

public interface WsService {

	public String rendicionWsNominasBES(ParamRendicionVO rendicionVO) throws Exception;

	public String envioNominaWsBES(EnvioNominaVo envioVO) throws Exception;

}
