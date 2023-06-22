package cse.external.client.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

/*import java.rmi.RemoteException;
import javax.net.ssl.SSLException;
import com.ibm.websphere.webservices.soap.SOAPException;
import com.ibm.ws.webservices.engine.WebServicesFault;
import com.ibm.xmi.mod.Ids;*/

import cl.dicom.dwww.PlatinumImplProxy;
import cl.equifax.wse.gru01.platinum.PLATINUMOutput;

import cse.dao.factory.DAOFactory;
import cse.dao.factory.impl.DAOFactoryImpl;
import cse.database.dao.exception.DAOException;
import cse.database.dao.jdbc.JdbcUtil;
import cse.external.client.dao.CalificacionRiesgoExternaDAO;
import cse.model.businessobject.CalificacionRiesgoExterna;
import cse.system.helper.PropertyLoader;

public class CalificacionRiesgoExternaDAOImpl implements CalificacionRiesgoExternaDAO {

	private static Logger logger = Logger.getLogger(CalificacionRiesgoExternaDAOImpl.class
			.getName());

	private String ENDPOINT_LOCATION;
	private String NUMERO_SERIE_ID_TEMP;
	private String ICOM_ID_TEMP;
	private String USO_INTERNO_DICOM_ID_TEMP;
	private String USO_INTERNO_DICOM_02_ID_TEMP;
	private String USO_INTERNO_DICOM_03_ID_TEMP;
	private String USUARIO_ID_TEMP;
	private String PASSWORD_ID_TEMP;

	public CalificacionRiesgoExternaDAOImpl() {
		super();
		loadProperties();
	}

	public CalificacionRiesgoExterna findByRut(String solicitudID, String numero, String digitoChequeo)
			throws DAOException {

		logger.entering(this.getClass().getName(), "findByRut", new String[] { solicitudID, numero,
				digitoChequeo });
		CalificacionRiesgoExterna calificacionRiesgoExterna = null;
		PlatinumImplProxy myPlatinumImplProxy = new PlatinumImplProxy();
//		System.out.println("CalificacionRiesgoExternaDAOImpl.findByRut()");
		String myEndPoint = myPlatinumImplProxy.getEndpoint();
		System.out.println("myPlatinumImplProxy.getEndpoint() : ");
		System.out.println(myEndPoint);
//		System.out.println("++++++//***********");

		// TODO EndPoint Location
		myPlatinumImplProxy.setEndpoint(ENDPOINT_LOCATION);

		String rutIDTemp = StringUtils.leftPad(numero, 9, '0') + digitoChequeo;

		logger.log(Level.INFO, "Trust Store configurado: "
				+ (String) System.getProperty("javax.net.ssl.trustStore"));
		logger.log(Level.INFO, "Trust Store configurado: "
				+ (String) System.getProperty("javax.net.ssl.trustStorePassword"));
		logger.log(Level.INFO, "Trust Store configurado: "
				+ (String) System.getProperty("javax.net.ssl.keyStore"));

		
		
		logger.log(Level.INFO, "rut a consultar a Dicom: [" + rutIDTemp + "]");
		// logger.log(Level.INFO, "NUMERO_SERIE_ID_TEMP        :[" +
		// NUMERO_SERIE_ID_TEMP + "]");
		// logger.log(Level.INFO, "ICOM_ID_TEMP                :[" +
		// ICOM_ID_TEMP + "]");
		// logger.log(Level.INFO, "USO_INTERNO_DICOM_ID_TEMP   :[" +
		// USO_INTERNO_DICOM_ID_TEMP + "]");
		// logger.log(Level.INFO, "USO_INTERNO_DICOM_02_ID_TEMP:[" +
		// USO_INTERNO_DICOM_02_ID_TEMP + "]");
		// logger.log(Level.INFO, "USO_INTERNO_DICOM_03_ID_TEMP:[" +
		// USO_INTERNO_DICOM_03_ID_TEMP + "]");

		try {
			
			System.out.println("******************************");
			System.out.println("rut: "+rutIDTemp);
			System.out.println("serie: "+NUMERO_SERIE_ID_TEMP);
			System.out.println("icom: "+ICOM_ID_TEMP);
			System.out.println("intermo1: "+USO_INTERNO_DICOM_ID_TEMP);
			System.out.println("interno2: "+USO_INTERNO_DICOM_02_ID_TEMP);
			System.out.println("interno3: "+USO_INTERNO_DICOM_03_ID_TEMP);
			System.out.println("user: "+USUARIO_ID_TEMP);
			System.out.println("pass: "+PASSWORD_ID_TEMP);
			System.out.println("******************************");
			
			
			PLATINUMOutput getInformeResponse = myPlatinumImplProxy.getInforme(rutIDTemp,
					NUMERO_SERIE_ID_TEMP, ICOM_ID_TEMP, USO_INTERNO_DICOM_ID_TEMP,
					USO_INTERNO_DICOM_02_ID_TEMP, USO_INTERNO_DICOM_03_ID_TEMP, USUARIO_ID_TEMP,
					PASSWORD_ID_TEMP);
			String dato = getInformeResponse.getIndicadorRiesgoCrediticio()
					.getPredictorPersonaNatural();
			int valor = Integer.parseInt(dato);
			String fecNac = getInformeResponse.getIdentificacionPersona().getFechaNacimiento();
			String genero = getInformeResponse.getIdentificacionPersona().getSexo();
			String estCivil = getInformeResponse.getIdentificacionPersona().getEstadoCivil();

			calificacionRiesgoExterna = new CalificacionRiesgoExterna();
			calificacionRiesgoExterna.setValor(new Integer(valor));
			calificacionRiesgoExterna.setFecNac(fecNac);
			calificacionRiesgoExterna.setGenero(genero);
			calificacionRiesgoExterna.setEstCivil(estCivil);
		} catch (Exception theException) {
		theException.printStackTrace();
			String header = theException.getClass().getName();
			String detalle = ErrorHelper.getErrorMessage(theException);
			String errorMessage = theException.getMessage();
			DAOFactory daoFactory = DAOFactoryImpl.getInstance();
			daoFactory.getAlertaSistemaDAO().insert(solicitudID, this.getClass().getName(), 
					header, detalle, errorMessage);			
			logger.log(Level.SEVERE,
					"No se logró consultar a EQUIFAX : " + detalle + "\n"
							+ errorMessage, theException.getCause());
			throw new DAOException("No se logró consultar a EQUIFAX : " + detalle + "\n"
					+ errorMessage, theException.getCause());
		}

		logger.exiting(this.getClass().getName(), "findByRut", calificacionRiesgoExterna);
		return calificacionRiesgoExterna;
	}

	protected void loadProperties() {
		Properties prop = new Properties();
		prop = PropertyLoader.loadProperties("equifax.properties");
		// get the property value and print it out
		ENDPOINT_LOCATION = prop.getProperty("ENDPOINT_LOCATION");
		NUMERO_SERIE_ID_TEMP = prop.getProperty("NUMERO_SERIE_ID_TEMP");
		ICOM_ID_TEMP = prop.getProperty("ICOM_ID_TEMP");
		USO_INTERNO_DICOM_ID_TEMP = prop.getProperty("USO_INTERNO_DICOM_ID_TEMP");
		USO_INTERNO_DICOM_02_ID_TEMP = prop.getProperty("USO_INTERNO_DICOM_02_ID_TEMP");
		USO_INTERNO_DICOM_03_ID_TEMP = prop.getProperty("USO_INTERNO_DICOM_03_ID_TEMP");
		USUARIO_ID_TEMP = prop.getProperty("USUARIO_ID_TEMP");
		PASSWORD_ID_TEMP = prop.getProperty("PASSWORD_ID_TEMP");
	}
	
	public int getDefaultScoreByClasificacion(String clasifEmpresa)
	throws DAOException {
		int score=0;
		try {
			Connection conn = JdbcUtil.getNonXADBConnection();
		
			ResultSet resultSet = null;
			PreparedStatement stmtSelect = null;
			StringBuffer sb = new StringBuffer("SELECT ScorePorDefecto FROM dbo.ScorePorDefecto S ");
			sb.append("JOIN dbo.ClasificacionEmpresa C ON C.IdClasificacionEmpresa = S.IdClasificacionEmpresa ");
			sb.append("WHERE C.Nombre = ?");
			
			stmtSelect = conn.prepareStatement(sb.toString());
			stmtSelect.setString(1, clasifEmpresa.trim().toUpperCase());
			
			resultSet = stmtSelect.executeQuery();
			if(resultSet.next()){
				score = resultSet.getInt("ScorePorDefecto");
			}else{
				throw new Exception("Clasificación de empresa no encontrada: " + clasifEmpresa);
			}
		} catch (Exception e) {
			throw new DAOException("Problema al consultar el score por defecto, clasif. empresa: " + clasifEmpresa, e);
		}
		return score;
	}

}
