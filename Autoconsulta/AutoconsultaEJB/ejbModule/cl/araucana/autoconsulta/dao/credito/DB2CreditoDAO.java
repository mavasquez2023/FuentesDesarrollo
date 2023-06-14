package cl.araucana.autoconsulta.dao.credito;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;


import org.apache.log4j.Logger;

import cl.araucana.common.env.AppConfig;
import cl.laaraucana.credito.to.CreditoTO;
import cl.laaraucana.credito.to.CuotaTO;
import cl.laaraucana.credito.to.PagoCuotaTO;
import cl.laaraucana.credito.to.SeguroComprometidoTO;
import cl.laaraucana.utils.ContantesCredito;
import cl.laaraucana.utils.GetValueXML;
import cl.laaraucana.utils.Utils;

import com.schema.util.FileSettings;
import com.schema.util.GeneralException;
import com.schema.util.XmlBeansSettings;
import com.schema.util.dao.DB2Utils;

public final class DB2CreditoDAO implements ICreditoDAO {
	private static Logger logger = Logger.getLogger(DB2CreditoDAO.class);
	private DB2Utils creditoUtil;
	private GetValueXML getValueXml = new GetValueXML();
	
	public DB2CreditoDAO() {
		
		
		String datasource =
			FileSettings.getValue(
				AppConfig.getInstance().settingsFileName,
				"/application-settings/jdbc/autoconsulta");	
		creditoUtil = new DB2Utils(datasource, this);
		logger.info("datasource = [" + datasource + "]");
	}

	public Collection getCreditosAval(long rutAfiliado) {
		Object []param = new Object[]{new Long( rutAfiliado ), new Long( rutAfiliado )};
		logger.info("getCreditosAval = [" + rutAfiliado + "]");
		return getCreditos(param, ContantesCredito.BUSCAR_AVAL);	
	}

	public Collection getCreditosEmpresa(long rutEmpresa) {
		Object []param = new Object[]{new Long( rutEmpresa )};
		logger.info("getCreditosEmpresa = [" + rutEmpresa + "]");
		return getCreditos(param, ContantesCredito.BUSCAR_EMPRESA);
	}

	public Collection getCreditosEmpresaAfiliado(long rutEmpresa, long rutAfiliado) {
		Object []param = new Object[]{new Long( rutEmpresa ), new Long( rutAfiliado )};
		logger.info("getCreditosEmpresaAfiliado = [" + rutEmpresa + "][" + rutAfiliado + "]");
		return getCreditos(param, ContantesCredito.BUSCAR_EMPRESA_TITULAR);
	}

	public Collection getCreditosFolio(String oficina, String folio) {
		Object []param = new Object[2];
		param[0] = new Long(oficina);
		param[1] = new Long(folio);
		logger.info("getCreditosFolio = [" + oficina + "][" + folio + "]");
		return getCreditos(param, ContantesCredito.BUSCAR_FOLIO);		
	}

	public Collection getCreditosTitular(long rutAfiliado) {
		Object []param = new Object[]{new Long( rutAfiliado )};
		logger.info("getCreditosTitular = [" + rutAfiliado + "]");
		return getCreditos(param, ContantesCredito.BUSCAR_TITULAR);
	}
	
	private Collection getCreditos(Object[] params, int tipoBusqueda) {
		
		try {
			logger.info("getCreditos preparando parametros");
			creditoUtil.prepareCall(getSqlConsultaCredito(tipoBusqueda));
			for (int i = 0; i < params.length; i++)  {
				creditoUtil.getStatement().setLong(i+1, ((Long)params[i]).longValue());
			}
			logger.info("getCreditos iniciando consulta");
			return creditoUtil.executeQuery(CreditoTO.class);
		} catch (GeneralException e) {
			logger.error("GeneralException = [" + e.getMessage() + "]");
		} catch (SQLException e) {
			logger.error("SQLException = [" + e.getMessage() + "]");
		} catch (Exception e) {
			logger.error("Exception = [" + e.getMessage() + "]");
		}
		return null;

	}

	public Collection getCuotas(String oficina, String folio) {
		try {
			creditoUtil.prepareCall(getSqlConsultaCuotas());
			creditoUtil.getStatement().setString(1, oficina);
			creditoUtil.getStatement().setString(2, folio);
			return creditoUtil.executeQuery(CuotaTO.class);
		} catch (GeneralException e) {
			logger.error(e.getMessage());
	
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public Collection getPagos(String oficina, String folio) {
		try {
			creditoUtil.prepareCall(getSqlConsultaPagos());
			creditoUtil.getStatement().setString(1, oficina);
			creditoUtil.getStatement().setString(2, folio);
			return creditoUtil.executeQuery(PagoCuotaTO.class);
		} catch (GeneralException e) {
			logger.error(e.getMessage());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
		
	}

	/**
	 * Objeto: Consulta de seguros comprometidos
	 * @param String oficina, String folio
	 * @return coleccion de SeguroComprometidoTO
	 * @author adiaz ( Artered Chile )
	 * @version 1.0
	 */
	public Collection getSegurosCredito(String oficina, String folio) {
		try {
			creditoUtil.prepareCall(getSqlConsultaCreditoSeguro());
			creditoUtil.getStatement().setString(1, oficina);
			creditoUtil.getStatement().setString(2, folio);
			return creditoUtil.executeQuery(SeguroComprometidoTO.class);
		} catch (GeneralException e) {
			logger.error(e.getMessage());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	
	/**
	 * @return SeguroComprometidoTO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static SeguroComprometidoTO buildSeguroComprometidoTO(ResultSet rs) throws SQLException  {
		SeguroComprometidoTO seguro = new SeguroComprometidoTO();
		try {
			seguro.setCargo(null != rs.getObject( ContantesCredito.SEGURO_SEGCARGO  ) ? rs.getInt( ContantesCredito.SEGURO_SEGCARGO ) : 0);
			seguro.setEstado(null != rs.getObject( ContantesCredito.SEGURO_ESTADO   ) ? rs.getInt( ContantesCredito.SEGURO_ESTADO  ) : 0);
			seguro.setFechaEstado(null != rs.getObject( ContantesCredito.SEGURO_SEGFECEST  ) ?  
					Utils.convertirStringADate( ContantesCredito.FECHA_PATTERN3, rs.getString( ContantesCredito.SEGURO_SEGFECEST  ) ) 
					: new Date());
			
			seguro.setFechaInicioCobro(null != rs.getObject( ContantesCredito.SEGURO_FECINICOB   ) ?  
					Utils.convertirStringADate( ContantesCredito.FECHA_PATTERN3, rs.getString( ContantesCredito.SEGURO_FECINICOB   ) ) 
					: new Date());

			seguro.setFolio(null != rs.getObject( ContantesCredito.SEGURO_CREFOL  ) ? rs.getLong( ContantesCredito.SEGURO_CREFOL ) : 0);
			seguro.setMonto(null != rs.getObject( ContantesCredito.SEGURO_MONTOSEG   ) ? rs.getLong( ContantesCredito.SEGURO_MONTOSEG  ) : 0);
			seguro.setOficina(null != rs.getObject( ContantesCredito.SEGURO_CREFOL ) ? rs.getString( ContantesCredito.SEGURO_CREFOL ) : "");
			seguro.setPoliza(null != rs.getObject( ContantesCredito.SEGURO_SEGPOLIZA ) ? rs.getLong( ContantesCredito.SEGURO_SEGPOLIZA ) : 0);
			seguro.setPorPoliza(null != rs.getObject( ContantesCredito.SEGURO_PORPOLIZA ) ? rs.getInt( ContantesCredito.SEGURO_PORPOLIZA ) : 0);
			seguro.setRutNroAfiliado(null != rs.getObject( ContantesCredito.SEGURO_AFIRUT ) ? rs.getLong( ContantesCredito.SEGURO_AFIRUT ) : 0);
			seguro.setTipoSeguro(null != rs.getObject( ContantesCredito.SEGURO_TIPOSEG  ) ? rs.getString( ContantesCredito.SEGURO_TIPOSEG  ) : "");

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return seguro;
	}
	/**
	 * @return PagoCuotaTO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static CreditoTO buildCreditoTO(ResultSet rs) throws SQLException  {
		CreditoTO credito = new CreditoTO();
		try {
			credito.setCantidadCuotas(null != rs.getObject( ContantesCredito.CRECUOTOT ) ? rs.getInt( ContantesCredito.CRECUOTOT ) : 0);
			credito.setCantidadCuotasPagadas(null != rs.getObject( ContantesCredito.CANTCUOTPAG ) ? rs.getInt( ContantesCredito.CANTCUOTPAG ) : 0);
			credito.setEstado(null != rs.getObject( ContantesCredito.CREESTPTM ) ? rs.getInt( ContantesCredito.CREESTPTM ) : 0);
			credito.setEstadoCobranza(null != rs.getObject( ContantesCredito.COBEST ) ? rs.getInt( ContantesCredito.COBEST ) : 0);
			credito.setEstadoUltimaCuota(1); // calcular
			credito.setFechaOtorgamiento(null != rs.getObject( ContantesCredito.CREOTOFEC ) ?  
					Utils.convertirStringADate( ContantesCredito.FECHA_PATTERN2, rs.getString( ContantesCredito.CREOTOFEC ) ) 
					: new Date());

			credito.setFolio(null != rs.getObject( ContantesCredito.CREFOL ) ? rs.getLong( ContantesCredito.CREFOL ) : 0);
			
			credito.setImpuesto(null != rs.getObject( ContantesCredito.CREIMPMON ) ? rs.getLong( ContantesCredito.CREIMPMON ) : 0); 
			
			credito.setIndicadorSeguroCesantia("No");  
			credito.setIndicadorSeguroDeshaucio("No"); 
			credito.setIndicadorSeguroInvalidez("No"); // se calcula en el bo
			
			credito.setLinea(null != rs.getObject( ContantesCredito.CRELINGLO ) ? rs.getString( ContantesCredito.CRELINGLO ) : ""); 
			
			credito.setMontoCuota(null != rs.getObject( ContantesCredito.CRECUOMON ) ? rs.getLong( ContantesCredito.CRECUOMON ) : 0);
			credito.setMontoNominal(null != rs.getObject( ContantesCredito.CRENOMMON ) ? rs.getLong( ContantesCredito.CRENOMMON ) : 0);
			credito.setMontoPagado(null != rs.getObject( ContantesCredito.CRENOMACU ) ? rs.getLong( ContantesCredito.CRENOMACU ) : 0);
			credito.setMontoReajustado(null != rs.getObject( ContantesCredito.CREREAMON ) ? rs.getLong( ContantesCredito.CREREAMON ) : 0);
			credito.setOficina(null != rs.getObject( ContantesCredito.OFIPRO ) ? rs.getString( ContantesCredito.OFIPRO ) :"");
			credito.setProximaCuota(1);  // calcular
			credito.setProximoVencimiento(null != rs.getObject( ContantesCredito.FECPROXVENC ) ?  
					Utils.convertirStringADate( ContantesCredito.FECHA_PATTERN3, rs.getString( ContantesCredito.FECPROXVENC ) ) 
					: new Date());
			credito.setTasa(null != rs.getObject( ContantesCredito.CRETAS ) ? rs.getFloat( ContantesCredito.CRETAS ) : (float)0.0);
			credito.setTipoOperacion(null != rs.getObject( ContantesCredito.CRETOP ) ? rs.getInt( ContantesCredito.CRETOP ) : 0);
			credito.setRutAval1(null != rs.getObject( ContantesCredito.RUTAVAL1 ) ? rs.getLong( ContantesCredito.RUTAVAL1 ) : 0);
			credito.setRutAval2(null != rs.getObject( ContantesCredito.RUTAVAL2 ) ? rs.getLong( ContantesCredito.RUTAVAL2 ) : 0);
			credito.setRutEmpresa(null != rs.getObject( ContantesCredito.EMPRUT ) ? rs.getLong( ContantesCredito.EMPRUT ) : 0);
			credito.setRutTitular(null != rs.getObject( ContantesCredito.AFIRUT ) ? rs.getLong( ContantesCredito.AFIRUT ) : 0);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return credito;
	}
	/**
	 * @return PagoCuotaTO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static PagoCuotaTO buildPagoCuotaTO(ResultSet ors) throws SQLException  {
		PagoCuotaTO pago = new PagoCuotaTO();
		try {
			pago.setCapital(ors.getLong( ContantesCredito.PAGO_CUOCAPPAG));
			pago.setCreditoNumero(ors.getLong(ContantesCredito.PAGO_FOLIO));
			pago.setCuotaNumero(ors.getInt(ContantesCredito.PAGO_CUONUM));
			pago.setInteres(ors.getFloat(ContantesCredito.PAGO_CUOINTPAG));
			pago.setDocumentoPago(ors.getString(ContantesCredito.PAGO_CUODOCPAG));
			pago.setEstado(ors.getInt(ContantesCredito.PAGO_CUOESTPAG));
			pago.setFechaPago(
					null != ors.getObject( ContantesCredito.PAGO_CUOFECPAG ) ? 
							Utils.convertirStringADate( ContantesCredito.FECHA_PATTERN3, 
							ors.getString( ContantesCredito.PAGO_CUOFECPAG  ) )
							: new Date());
			pago.setMonto(pago.getCapital() + pago.getInteres());
			pago.setOficicina(ors.getString(ContantesCredito.PAGO_OFICINA));
			pago.setOficicinaPago(ors.getString(ContantesCredito.PAGO_CMCA));
			pago.setTipoDocumentoPago(ors.getString(ContantesCredito.PAGO_CUODOCTIP));
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return pago;
	}
	/**
	 * @return CuotaTO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static CuotaTO buildCuotaTO(ResultSet rs) throws SQLException  {
		CuotaTO cuota = new CuotaTO();
		try {
			cuota.setAbono(null != rs.getObject( ContantesCredito.CUOTA_CUOMONABO ) ? rs.getInt( ContantesCredito.CUOTA_CUOMONABO ) : 0);
			cuota.setCapital(null != rs.getObject( ContantesCredito.CUOTA_CUOMONCAP ) ? rs.getFloat( ContantesCredito.CUOTA_CUOMONCAP ) : 0);
			cuota.setCreditoNumero(null != rs.getObject( ContantesCredito.CUOTA_FOLIO ) ? rs.getInt( ContantesCredito.CUOTA_FOLIO ) : 0);
			cuota.setCuotaNumero(null != rs.getObject( ContantesCredito.CUOTA_CUONUM ) ? rs.getInt( ContantesCredito.CUOTA_CUONUM ) : 0);
			
			cuota.setEstado(null != rs.getObject( ContantesCredito.CUOTA_CUOEST ) ? rs.getInt( ContantesCredito.CUOTA_CUOEST ) : 0);
			cuota.setFechaVencimiento(null != rs.getObject( ContantesCredito.CUOTA_CUOVENFEC ) ? 
					Utils.convertirStringADate( ContantesCredito.FECHA_PATTERN3, rs.getString( ContantesCredito.CUOTA_CUOVENFEC ) )
					 : new Date()
					);
			cuota.setInteres(null != rs.getObject( ContantesCredito.CUOTA_CUOMONINT ) ? rs.getFloat( ContantesCredito.CUOTA_CUOMONINT ) : 0);
			cuota.setMonto((float)cuota.getInteres() + cuota.getCapital());
			cuota.setOficicina(null != rs.getObject( ContantesCredito.CUOTA_OFICINA ) ? rs.getString( ContantesCredito.CUOTA_OFICINA ) : "");
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return cuota;
	}

	private String getSqlConsultaPagos() {
		return getValueXml.getValue("/creditos/pagos");
	}
	
	private String getSqlConsultaCuotas() {
		return getValueXml.getValue("/creditos/cuotas");
	}

	
	private String getSqlConsultaCreditoSeguro() {
		return getValueXml.getValue("/creditos/seguros");
	}
	
	private String getSqlConsultaCredito( int tipoFiltro) {
		StringBuffer sql = new StringBuffer();
		String cabecera = getValueXml.getValue("/creditos/consulta/cabecera");

		sql.append(cabecera)
		   .append(" ")
		   .append(getTipoFiltroCredito(tipoFiltro));
		return sql.toString();
	}

	private String getTipoFiltroCredito(int tipoFiltro) {
		switch (tipoFiltro) {
		case ContantesCredito.BUSCAR_TITULAR:
			return getValueXml.getValue("/creditos/consulta/where-titular");

		case ContantesCredito.BUSCAR_FOLIO:
			return getValueXml.getValue("/creditos/consulta/where-folio");
					
		case ContantesCredito.BUSCAR_EMPRESA:
			return getValueXml.getValue("/creditos/consulta/where-empresa");
					
		case ContantesCredito.BUSCAR_EMPRESA_TITULAR:
			return getValueXml.getValue("/creditos/consulta/where-titular");
			
		case ContantesCredito.BUSCAR_AVAL:
			return getValueXml.getValue("/creditos/consulta/where-aval");
					
		case ContantesCredito.BUSCAR_AFILIADO:
			
			return getValueXml.getValue("/creditos/consulta/where-afiliado");
					
		}
		return ("WHERE AFIRUT = 0");
	}
	
}
