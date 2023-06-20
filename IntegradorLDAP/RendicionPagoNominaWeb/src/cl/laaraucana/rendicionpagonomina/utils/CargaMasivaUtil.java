package cl.laaraucana.rendicionpagonomina.utils;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.vo.ColumnaFormatoVO;
import cl.laaraucana.rendicionpagonomina.vo.Detalle24HVO;
import cl.laaraucana.rendicionpagonomina.vo.DetalleBEstadoBaseVO;
import cl.laaraucana.rendicionpagonomina.vo.DetalleCreditoVO;
import cl.laaraucana.rendicionpagonomina.vo.FormatoCargaVO;

public class CargaMasivaUtil {

	private static final Logger logger = Logger.getLogger(CargaMasivaUtil.class);
	private static CargaMasivaUtil instance = null;
	private static HashMap<String, FormatoCargaVO> formatos = new HashMap<String, FormatoCargaVO>();
	public final static String FORMATO_24H = "24H";
	public final static String FORMATO_ONLINE = "ONLINE";
	public final static String FORMATO_BESTADO_BASE = "BESTADO_BASE";
	public static CargaMasivaUtil getInstance() {
		if(instance == null) {
			instance = new CargaMasivaUtil();
			instance.init();
		}
		return instance;
	}
	
	public void init() {
		formatos.put(FORMATO_24H, CargaMasivaConfig.getFormato24H());
		formatos.put(FORMATO_ONLINE, CargaMasivaConfig.getFormatoCredito());
		formatos.put(FORMATO_BESTADO_BASE, CargaMasivaConfig.getFormatoBEstadoBase());
		
	}
	
	
	public ArrayList<Object> getDetallesFromFile(String idFormato, String pathFile){
		ArrayList<Object> detallesProcesados = new ArrayList<Object>();
		ArrayList<Object> detallesOut = new ArrayList<Object>();
		ArrayList<HashMap<String, Object>> detalles = new ArrayList<HashMap<String, Object>>();
		BufferedReader br = FileManagmentUtils.getOpenedFileToRead(pathFile, true, "ISO-8859-15");
		
		String registro = FileManagmentUtils.getLineFromFileOpened(br);
		
		while(registro != null) {
			
			detalles.add( getDetalleFromString(idFormato, registro) );
			
			registro = FileManagmentUtils.getLineFromFileOpened(br);
		}
		
		FileManagmentUtils.closeFileToRead(br);
		
		if(idFormato.equals(FORMATO_24H)) {
			for (HashMap<String, Object> det : detalles) {
				if(det != null) {
					detallesOut.add( new Detalle24HVO(det) );
				}
			}
			
			for (Object d1 : detallesOut) {
				Detalle24HVO reg = (Detalle24HVO) d1;
				DetalleEntity det = new DetalleEntity();
				det.setCodigoBeneficio("");
				det.setRutAfiliado( reg.getRutAfiliado() );
				det.setDvAfiliado(reg.getDvRutAfiliado());
				det.setNombres( reg.getNombre().trim() + " " + reg.getApellidoPaterno().trim()+" "+reg.getApellidoMaterno().trim()  );
				det.setCodigoBanco( reg.getCodigoBanco() );
				det.setNumerocuenta(Long.parseLong( reg.getNumeroCuenta() ));
				det.setTipoCuenta(0);
				det.setEmail("");
				det.setCodigoFormaPago( reg.getCodigoFormaPago() );
				
				det.setMonto( reg.getMonto() );
				det.setEstadoPago(2);
				det.setDescripcionEstadoPago("");
				det.setDescripcionRechazo("");
				det.setReferencia1( reg.getReferencia1() );
				det.setReferencia2( "" );
				det.setFechaCambio(new Date());
				det.setIdReferencia(0);
				det.setCodigoNomina(new Long(0));
				detallesProcesados.add(det);
			}
			
		}
		else if(idFormato.equals(FORMATO_ONLINE)) {
			for (HashMap<String, Object> det : detalles) {
				if(det != null) {
					detallesOut.add( new DetalleCreditoVO(det) );
				}
			}
			for (Object d1 : detallesOut) {
				DetalleCreditoVO reg = (DetalleCreditoVO) d1;
				DetalleEntity det = new DetalleEntity();
				det.setRutAfiliado(  reg.getRutAfiliado()  );
				det.setDvAfiliado( reg.getDvRutAfiliado() );
				det.setNombres( reg.getNombreAfiliado()  );
				det.setCodigoBanco( reg.getBancoDestino() );
				det.setNumerocuenta(Long.parseLong( reg.getNumeroCuenta() ));
				det.setTipoCuenta(0);
				det.setEmail(reg.getCorreoElectronico());
				det.setCodigoFormaPago( reg.getTipoPago() );
				det.setMonto(  reg.getMonto() );
				det.setEstadoPago(2);
				det.setDescripcionEstadoPago("");
				det.setDescripcionRechazo("");
				//nro factura
				det.setReferencia1( reg.getNumeroFactura());
				//nro orden compra
				det.setReferencia2( reg.getNumeroOrdenCompra() );
				det.setFechaCambio(new Date());
				det.setIdReferencia(0);
				det.setCodigoNomina(new Long(0));
				
				detallesProcesados.add(det);
			}
		}
		
		else if(idFormato.equals(FORMATO_BESTADO_BASE)) {
			for (HashMap<String, Object> det : detalles) {
				if(det != null) {
					detallesOut.add( new DetalleBEstadoBaseVO(det) );
				}
			}
			for (Object d1 : detallesOut) {
				try {
					DetalleBEstadoBaseVO reg = (DetalleBEstadoBaseVO) d1;
					DetalleEntity det = new DetalleEntity();
					det.setRutAfiliado(  reg.getRutAfiliado()  );
					det.setDvAfiliado( reg.getDvAfiliado() );
					det.setNombres( reg.getNombreAfiliado().trim() + " " + reg.getApellidoPaterno().trim() +" "+ reg.getApellidoMaterno().trim() );
					det.setCodigoBanco( reg.getBancoAfiliado() );
					det.setNumerocuenta(Long.parseLong( reg.getNumeroCuenta() ));
					det.setTipoCuenta(0);
					det.setEmail(reg.getEmailAfiliado());
					det.setCodigoFormaPago( reg.getFormaPago() );
					det.setMonto(  reg.getMontoPago() );
					det.setEstadoPago(2);
					det.setDescripcionEstadoPago("");
					det.setDescripcionRechazo("");
					//nro factura
					try {
						det.setReferencia1( reg.getCorrelativoDetalle().replaceAll(" ", "") );
					}catch(Exception e) {
						logger.error("Error en Detalle al asignar Folio " + reg.getCorrelativoDetalle() + " Rut:" +  reg.getRutAfiliado());
					}
					//nro orden compra
					det.setReferencia2( null);
					det.setFechaCambio(new Date());
					det.setIdReferencia(0 );
					det.setCodigoNomina(new Long(0));
					
					detallesProcesados.add(det);
				}catch(Exception e) {
					logger.debug(e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		
		return detallesProcesados;
	}
	
	
	private static HashMap<String, Object> getDetalleFromString(String idFormato, String registro) {
		HashMap<String, Object> detalle = null;
		
		FormatoCargaVO formato = formatos.get(idFormato);
		if(formato != null) {
			if(formato.getTipoEntrada() == FormatoCargaVO.TIPO_ENTRADA_POR_POSICION) {
				detalle = getDetalleFromStringCSV(idFormato, registro);
			}
			if(formato.getTipoEntrada() == FormatoCargaVO.TIPO_ENTRADA_POR_INDICE) {
				detalle = getDetalleFromStringPlain(idFormato, registro);
			}
			
			
			
		}
		return detalle; 
	}
	
	private static HashMap<String, Object> getDetalleFromStringPlain(String idFormato, String registro) {
		HashMap<String, Object> detalle = null;
		FormatoCargaVO formato = formatos.get(idFormato);
		if(formato != null) {
			try {
				detalle = new HashMap<String, Object>();
				for(int i=0; i< formato.getColumnas().size(); i++) {
					ColumnaFormatoVO columna = formato.getColumnas().get(i);
					if(i< formato.getColumnas().size() - 1) {
						detalle.put(columna.getKey(), registro.substring(columna.getPosicion(), columna.getPosicion()+columna.getLargo()));
					}else {
						if(registro.length() >= columna.getPosicion() ) {
							if(registro.length()>= columna.getPosicion() + columna.getLargo()) {
								detalle.put(columna.getKey(), registro.substring(columna.getPosicion(), columna.getPosicion()+columna.getLargo()));
							}
							else {
								detalle.put(columna.getKey(), registro.substring(columna.getPosicion()));
							}
						}
					}
				}
			}
			catch(Exception e) {
				logger.debug("Error "+e.getMessage()+"-> ["+idFormato+"]->["+registro+"]");
				e.printStackTrace();
			}
//			for (ColumnaFormatoVO columna : formato.getColumnas()) {
//				detalle.put(columna.getKey(), registro.substring(columna.getPosicion(), columna.getPosicion()+columna.getLargo()));
//			}
			
		}
		return detalle;
	}
	private static HashMap<String, Object> getDetalleFromStringCSV(String idFormato, String registro) {
		HashMap<String, Object> detalle = null;
		FormatoCargaVO formato = formatos.get(idFormato);
		if(formato != null) {
			String[] cols = registro.split(formato.getSeparador());
			detalle = new HashMap<String, Object>();
			for (ColumnaFormatoVO columna : formato.getColumnas()) {
				detalle.put(columna.getKey(), cols[columna.getPosicion()]);
			}
			
		}
		return detalle;
	}
	
	
}
