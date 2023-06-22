package cse.model.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cse.dao.factory.impl.DAOFactoryImpl;
import cse.database.dao.EvaluaCreditScoringPersonasDAO;
import cse.database.dao.SolicitudDAO;
import cse.database.dao.exception.DAOException;
import cse.database.objects.Solicitud;
import cse.model.businessobject.CalificacionRiesgoExterna;
import cse.model.businessobject.ClasificacionRiesgoEmpleador;
import cse.model.businessobject.CondicionOtorgamiento;
import cse.model.businessobject.DatosDemograficos;
import cse.model.businessobject.InformacionLaboral;
import cse.model.businessobject.Monto;
import cse.model.businessobject.Rut;
import cse.model.exception.ScoringModuleException;
import cse.model.service.CondicionesOtorgamientoService;
import cse.model.service.data.EvaluarCondicionesResponse;
import cse.model.service.impl.helper.CondicionesOtorgamientoHelper;

public class CondicionesOtorgamientoServiceImpl implements CondicionesOtorgamientoService {

	private static Logger logger = Logger.getLogger(CondicionesOtorgamientoServiceImpl.class
			.getName());

	/**
	 * CondicionesOtorgamientoServiceImpl
	 * 
	 * @throws ScoringModuleException
	 */
	public CondicionesOtorgamientoServiceImpl() throws ScoringModuleException {
		super();
	}


	public EvaluarCondicionesResponse evaluarCondicionesOtorgamientoFull(Rut myRut, int tipoAfiliado, Monto myMonto,
			String genero, String fechaNac, String estadoCivil, double remuneracion,
			String diasMorosidad, int numCreditosAnteriores, int numDiasLicencia,
			String antiguedadLaboral, Rut empRut, String clasifRiesgoEmpresa) throws ScoringModuleException {

		logger.entering(this.getClass().getName(), "evaluarCondicionesOtorgamientoFull",
				new Object[] { 	myRut,
			myMonto,
			genero,
			fechaNac,
			estadoCivil,
			new Double(remuneracion),
			diasMorosidad,
			new Integer(numCreditosAnteriores),
			new Integer(numDiasLicencia),
			antiguedadLaboral,
			empRut,
			clasifRiesgoEmpresa });
		EvaluarCondicionesResponse response = null;


		//Quitar posibles espacios en blanco//
		genero = genero.trim(); 
		fechaNac = fechaNac.trim(); 
		estadoCivil = estadoCivil.trim();
		diasMorosidad = diasMorosidad.trim(); 
		antiguedadLaboral = antiguedadLaboral.trim(); 
		empRut.setNumero(empRut.getNumero().trim());
		empRut.setDigitoChequeo(empRut.getDigitoChequeo().trim());
		clasifRiesgoEmpresa = clasifRiesgoEmpresa.trim();

		/*Validemos los nulos y formatos */
		if (myRut == null || myRut.getNumero()==null || (myRut.getNumero().length()==0)){
			logger.log(Level.SEVERE, "Parametro rutSolicitante nulo o vacio");
			throw new ScoringModuleException("Parametro rutSolicitante nulo o vacio");
		}
		if (myRut == null || (myRut.getDigitoChequeo()==null) || (myRut.getDigitoChequeo().length()!=1)){
			logger.log(Level.SEVERE, "Parametro digito verificador nulo o vacio");
			throw new ScoringModuleException("Parametro digito verificador nulo o vacio");
		}		
		if (tipoAfiliado >10 || tipoAfiliado < 0 || String.valueOf(tipoAfiliado).length() != 1) {
			logger.log(Level.SEVERE, "Parametro tipoAfiliado negativo o de mas de un digito");
			throw new ScoringModuleException("Parametro tipoAfiliado negativo o de mas de un digito");
		}		
		if (myMonto == null || myMonto.getValor()<=0 ) {
			logger.log(Level.SEVERE, "Parametro monto nulo o cero o negativo");
			throw new ScoringModuleException("Parametro monto nulo o negativo");
		}
		//		if (genero == null || genero.length()==0) {
		//			logger.log(Level.SEVERE, "Parametro genero nulo o vacio");
		//			throw new ScoringModuleException("Parametro genero nulo o vacio");}

		if (!(genero.equals("M") || genero.equals("F"))) {
			genero = null;
		}

		//		}
		//		if (fechaNac == null || (fechaNac.length()==0) ||
		//				(fechaNac.length()!=8)) {
		//			logger.log(Level.SEVERE, "Parametro fechaNac nulo o vacio");
		//			throw new ScoringModuleException("Parametro fechaNac nulo o vacio");
		//		}

		// Fecha de nacimiento se convierte en Null cuando viene mala, porque se aplica regla de
		// precedencia Equifax
		try {
			Integer.parseInt(fechaNac);
		} catch (NumberFormatException e) {
			fechaNac = "00000000";
		}



		//		if (estadoCivil == null || estadoCivil.length()==0) {
		//			logger.log(Level.SEVERE, "Parametro estadoCivil nulo");
		//			throw new ScoringModuleException("Parametro estadoCivil nulo");
		//		}


		if (!(estadoCivil.equals("D") || !(estadoCivil.equals("S") || !estadoCivil.equals("V") || !estadoCivil.equals("C")))) {
			logger.log(Level.SEVERE, "Parametro estadoCivil distinto de S, C, D o V");
			estadoCivil = null;
		}

		if (diasMorosidad==null || (diasMorosidad.length()==0) ||
				(diasMorosidad.length() != 8)) {
			logger.log(Level.SEVERE, "Parametro diasMorosidad nulo o vacio");
			throw new ScoringModuleException("Parametro diasMorosidad nulo o vacio");
		}
		if(antiguedadLaboral==null || (antiguedadLaboral.length()==0) ||
				(antiguedadLaboral.length()!= 8)) {
			logger.log(Level.SEVERE, "Parametro antiguedadLaboral nulo o vacio o de longitud distinta a 8");
			throw new ScoringModuleException("Parametro antiguedadLaboral nulo o vacio o de longitud distinta a 8");
		}
		if(empRut.getNumero()==null || empRut.getNumero().length()==0) {
			logger.log(Level.SEVERE, "Parametro rutEmpleador nulo o vacio");
			throw new ScoringModuleException("Parametro numero de rut Empleador nulo");
		}
		if(empRut.getDigitoChequeo()==null || empRut.getDigitoChequeo().length()==0) {
			logger.log(Level.SEVERE, "Parametro rutEmpleador nulo o vacio");
			throw new ScoringModuleException("Parametro Digito Verificador de rut Empleador nulo");
		}
		if(null==clasifRiesgoEmpresa || clasifRiesgoEmpresa.length() != 2) {
			logger.log(Level.SEVERE, "Parametro clasifRiesgoEmpresa nulo o vacio o de largo distinto a 2");
			throw new ScoringModuleException("Parametro clasifRiesgoEmpresa nulo o vacio o de largo distinto a 2");
		}


		/* Aqui es donde la magia comienza */
		/* Se recupera la informacion de AS400 y Equifax */
		/* Luego se crear un registro en la tabla solicitud */
		String idSolicitud =
			this.beforeStoredProcedureExecutionFullVersion(
					myRut,
					tipoAfiliado,
					myMonto,
					genero,
					fechaNac,
					estadoCivil,
					remuneracion,
					diasMorosidad,
					numCreditosAnteriores,
					numDiasLicencia,
					antiguedadLaboral,
					empRut,
					clasifRiesgoEmpresa);
		/* Se corre el procedimiento almacenado */
		this.duringStoredProcedureExecution(idSolicitud);
		/*
		 * Cuando el SP hizo lo suyo debo simplemente consultar la tablas
		 * (solicitud y condicion)
		 */
		response = this.afterStoredProcedureExecution(idSolicitud);
		logger.exiting(this.getClass().getName(), "evaluarCondicionesOtorgamientoFull", response);
		return response;
	}

	public EvaluarCondicionesResponse evaluarCondicionesOtorgamiento(Rut rutSolicitante,
			Monto monto, int tipoAfiliado) throws ScoringModuleException {
		logger.entering(this.getClass().getName(), "evaluarCondicionesOtorgamiento", new Object[] {
			rutSolicitante, monto, new Integer(tipoAfiliado) });

		EvaluarCondicionesResponse response = null;

		/*Validemos los nulos*/
		if (rutSolicitante == null|| rutSolicitante.getNumero()==null || (rutSolicitante.getNumero().length()==0)){
			logger.log(Level.WARNING, "Parametro rutSolicitante nulo o vacio");
			throw new ScoringModuleException("Parametro rutSolicitante nulo o vacio");
		}
		if (monto == null || monto.getValor()==0 ){
			logger.log(Level.WARNING, "Parametro monto nulo o vacio");
			throw new ScoringModuleException("Parametro monto nulo o vacio");
		}		

		/* Aqui es donde la magia comienza */
		/* Se recupera la informacion de Equifax */
		/* Luego se crear un registro en la tabla solicitud */
		String idSolicitud = this.beforeStoredProcedureExecution(rutSolicitante, monto,
				tipoAfiliado);
		/* Se corre el procedimiento almacenado */
		this.duringStoredProcedureExecution(idSolicitud);
		/*
		 * Cuando el SP hizo lo suyo debo simplemente consultar la tablas
		 * (solicitud y condicion)
		 */
		response = this.afterStoredProcedureExecution(idSolicitud);
		logger.exiting(this.getClass().getName(), "evaluarCondicionesOtorgamiento", response);
		return response;
	}

	private EvaluarCondicionesResponse afterStoredProcedureExecution(String idSolicitud)
	throws ScoringModuleException {
		EvaluarCondicionesResponse response = null;
		logger.entering(this.getClass().getName(), "afterStoredProcedureExecution", idSolicitud);
		try {
			SolicitudDAO solicitudDAO = DAOFactoryImpl.getInstance().getSolicitudDAO();
			Solicitud solicitudRecord = solicitudDAO.selectByPrimaryKey(idSolicitud);

			String perfilRiesgo = solicitudRecord.getPerfilriesgo();

			if (null == perfilRiesgo) {
				throw new ScoringModuleException("Perfil de Riesgo se recupera nulo desde tabla Solicitud del Motor");
			}

			//TODO: Replicar para el resto de los datos esenciales

			String strNumSueldos = solicitudRecord.getNrosueldos();
			if (null == strNumSueldos) {
				throw new ScoringModuleException("Numero de sueldos se recupera nulo desde tabla Solicitud del Motor");
			} 

			Integer numSueldos;

			try {
				numSueldos = CondicionesOtorgamientoHelper.translateNroSueldos(strNumSueldos);
			} catch (Exception e) {
				throw new ScoringModuleException("Numero sueldos no es numérico en tabla Solicitud del Motor");
			}

			BigDecimal score = solicitudRecord.getScoremodelopersonas();
			BigDecimal scoreEquifax = solicitudRecord.getRiesgoexterno();
			Integer endeudMax = solicitudRecord.getMaximomontonominalotorgable();

			//TODO: Replicar para el resto de los datos esenciales

			List condicionesAsList = CondicionesOtorgamientoHelper.getCondiciones(idSolicitud);

			CondicionOtorgamiento[] condiciones = (CondicionOtorgamiento[]) condicionesAsList
			.toArray(new CondicionOtorgamiento[] {});
			response = new EvaluarCondicionesResponse();
			response.setCondiciones(condiciones);
			response.setPerfilRiesgo(perfilRiesgo);

			response.setScore(new Float(score.floatValue()*100));

			response.setScoreEquifax(new Integer(scoreEquifax.intValue()));
			response.setNumSueldos(numSueldos);
			response.setEndeudMax(endeudMax);
			response.setIdSolicitud(idSolicitud);
		} catch (DAOException daoe) {
			logger.log(Level.SEVERE,
					"[afterStoredProcedureExecution]: Hubo un problema en el DAO ", daoe);
			throw new ScoringModuleException("Problemas de DAO en afterStoredProcedureExecution", daoe);
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"[afterStoredProcedureExecution]: Hubo un problema generico no manejado ", e);
			throw new ScoringModuleException("Problemas afterStoredProcedureExecution", e);
		}

		logger.exiting(this.getClass().getName(), "afterStoredProcedureExecution", response);
		return response;
	}

	private void duringStoredProcedureExecution(String idSolicitud) throws ScoringModuleException {
		logger.entering(this.getClass().getName(), "duringStoredProcedureExecution", idSolicitud);
		try {
			EvaluaCreditScoringPersonasDAO evaluaScorePersonasDAO = DAOFactoryImpl.getInstance()
			.getEvaluaCreditScoringPersonasDAO();
			evaluaScorePersonasDAO.execute(idSolicitud);
		} catch (SQLException sqle) {
			logger.log(Level.SEVERE,
					"[duringStoredProcedureExecution]: Hubo un problema en el SQL ", sqle);
			throw new ScoringModuleException("Problemas duringStoredProcedureExecution", sqle);
		} catch (DAOException daoe) {
			logger.log(Level.SEVERE, "duringStoredProcedureExecution", daoe);
			throw new ScoringModuleException("Problemas duringStoredProcedureExecution", daoe);
		}
		logger.exiting(this.getClass().getName(), "duringStoredProcedureExecution");
	}

	private String beforeStoredProcedureExecution(Rut rutSolicitante, Monto monto, int tipoAfiliado)
	throws ScoringModuleException {

		logger.entering(this.getClass().getName(), "beforeStoredProcedureExecution", new Object[] {
			rutSolicitante, monto });

		String newSolicitudID = null;
		try {
			
			SolicitudDAO solicitudDAO = DAOFactoryImpl.getInstance().getSolicitudDAO();

			newSolicitudID = solicitudDAO.createNewSolicitud();

			Solicitud newSolicitud = new Solicitud();
			newSolicitud.setIdsolicitud(newSolicitudID);

			//cambio en la firma de los metodos para incluir referencia al id de solicitud
			DatosDemograficos demograficos = CondicionesOtorgamientoHelper.findDatosDemograficos(newSolicitudID,
					rutSolicitante, tipoAfiliado);

			InformacionLaboral laborales = CondicionesOtorgamientoHelper.findInfoLaboral(newSolicitudID,
					rutSolicitante, tipoAfiliado);

			ClasificacionRiesgoEmpleador riesgoEmpleador = CondicionesOtorgamientoHelper
			.findRiesgoEmpleador(newSolicitudID, rutSolicitante, tipoAfiliado);

			CalificacionRiesgoExterna calificacionRiesgoExterna = CondicionesOtorgamientoHelper
			.findRiesgoExterno(newSolicitudID, rutSolicitante,riesgoEmpleador.getNombre());

			if(demograficos!=null){
				newSolicitud.setCreditosanteriores(new Integer(demograficos.getCreditosAnteriores()));
				newSolicitud.setFechamorosidad(demograficos.getFechaMorosidad());
				newSolicitud.setFechanacimiento(demograficos.getFechaNacimiento());
				newSolicitud.setIdgenero(demograficos.getGenero());
				newSolicitud.setRemuneracion(new Integer((int) demograficos.getRemuneracion()));
			}
			if(laborales != null){
				newSolicitud.setFechaantiguedadlaboral(laborales.getAntiguedadLaboral());	
				newSolicitud.setLicenciamedica(new Integer(laborales.getLicenciasMedicas()));

				newSolicitud.setRutempresa(Integer.valueOf(laborales.getRutEmpleador().getNumero()));
				//NUEVO SCORING
				newSolicitud.setDvempresa(String.valueOf(laborales.getRutEmpleador().getDigitoChequeo()));
			}

			if(calificacionRiesgoExterna != null && calificacionRiesgoExterna.getValor() != null){
				newSolicitud.setRiesgoexterno(new BigDecimal(calificacionRiesgoExterna.getValor()
						.toString()));	
			} else {
				logger.log(Level.WARNING, "Evaluando sin informacion EQUIFAX para soliciud : "+ newSolicitudID);
			}

			//TODO validar solucion definitivo de DV no numnerico en tablas

			newSolicitud.setRut(Integer.valueOf(rutSolicitante.getNumero()));
			newSolicitud.setDv(rutSolicitante.getDigitoChequeo());
			Integer montoInteger = new Integer((int) monto.getValor());
			newSolicitud.setMontonominalsolicitado(montoInteger);

			Integer idClasificacionEmpresa = CondicionesOtorgamientoHelper.translateClasificacionEmpresa(riesgoEmpleador);
			newSolicitud.setIdclasificacionempresa(idClasificacionEmpresa);

			Integer idEstadoCivil = CondicionesOtorgamientoHelper
			.translateEstadoCivil(demograficos);
			newSolicitud.setIdestadocivil(idEstadoCivil);

			newSolicitud.setNrosueldos(null); //no se usa


			solicitudDAO.updateByPrimaryKey(newSolicitud);

		} catch (SQLException sqle) {
			logger.log(Level.SEVERE,
					"[beforeStoredProcedureExecution]: Hubo un problema en el SQL ", sqle);
			throw new ScoringModuleException("Problemas beforeStoredProcedureExecution", sqle);
		} catch (DAOException daoe) {
			logger.log(Level.SEVERE,
					"[beforeStoredProcedureExecution]: Hubo un problema en el DAO ", daoe);
			throw new ScoringModuleException("Problemas beforeStoredProcedureExecution", daoe);
		}
		logger.exiting(this.getClass().getName(), "beforeStoredProcedureExecution", newSolicitudID);
		return newSolicitudID;
	}

	private String beforeStoredProcedureExecutionFullVersion(Rut rutSolicitante, int tipoAfiliado, Monto monto, String genero, String fechaNac, String estadoCivil,
			double remuneracion, String diasMorosidad, int numCreditosAnteriores,
			int numDiasLicencia, String antiguedadLaboral, Rut rutEmpleador,
			String clasifRiesgoEmpresa)
	throws ScoringModuleException {

		logger.entering(this.getClass().getName(), "beforeStoredProcedureExecution", new Object[] {
			rutSolicitante, monto });

		String newSolicitudID = null;
		try {
			

			//SolicitudDAO solicitudDAO = DAOFactoryImpl.getInstance().getSolicitudDAO();
			//newSolicitudID = solicitudDAO.createNewSolicitud();

//			CalificacionRiesgoExterna calificacionRiesgoExterna = CondicionesOtorgamientoHelper
//			.findRiesgoExterno(newSolicitudID, rutSolicitante, clasifRiesgoEmpresa);
			CalificacionRiesgoExterna calificacionRiesgoExterna = CondicionesOtorgamientoHelper
					.findRiesgoExterno("925301F9-013C-6EC9-5E0E-C43640A0D9C6", rutSolicitante, clasifRiesgoEmpresa);
			
			Solicitud newSolicitud = new Solicitud();
			newSolicitud.setIdsolicitud(newSolicitudID);
			newSolicitud.setCreditosanteriores(new Integer(numCreditosAnteriores));
			newSolicitud.setFechaantiguedadlaboral(antiguedadLaboral);
			newSolicitud.setFechamorosidad(diasMorosidad);
			newSolicitud.setFechanacimiento(fechaNac);

			Integer idClasificacionEmpresa = CondicionesOtorgamientoHelper.translateClasificacionRiesgoEmpresa(clasifRiesgoEmpresa);
			newSolicitud.setIdclasificacionempresa(idClasificacionEmpresa);

			Integer idEstadoCivil = CondicionesOtorgamientoHelper.translateEstadoCivil(estadoCivil);
			newSolicitud.setIdestadocivil(idEstadoCivil);

			newSolicitud.setIdgenero(genero);
			newSolicitud.setLicenciamedica(new Integer(numDiasLicencia));

			Integer montoInteger = new Integer((int) monto.getValor());
			newSolicitud.setMontonominalsolicitado(montoInteger);
			newSolicitud.setNrosueldos(null);//no se usa
			// TODO mapping de los numeric double, integer, BDecimal 
			newSolicitud.setRemuneracion(new Integer((int)remuneracion));

			if(calificacionRiesgoExterna != null){
				newSolicitud.setRiesgoexterno(new BigDecimal(calificacionRiesgoExterna.getValor()
						.toString()));	
			}

			newSolicitud.setTipoAfiliado(tipoAfiliado);
			newSolicitud.setRut(Integer.valueOf(rutSolicitante.getNumero()));
			newSolicitud.setDv(rutSolicitante.getDigitoChequeo());
			newSolicitud.setRutempresa(Integer.valueOf(rutEmpleador.getNumero()));
			newSolicitud.setDvempresa(rutEmpleador.getDigitoChequeo());

			//solicitudDAO.updateByPrimaryKey(newSolicitud);

		} catch (SQLException sqle) {
			logger.log(Level.SEVERE,
					"[beforeStoredProcedureExecution]: Hubo un problema en el SQL ", sqle);
			throw new ScoringModuleException("Problemas beforeStoredProcedureExecution", sqle);
		} catch (DAOException daoe) {
			logger.log(Level.SEVERE,
					"[beforeStoredProcedureExecution]: Hubo un problema en el DAO ", daoe);
			throw new ScoringModuleException("Problemas beforeStoredProcedureExecution", daoe);
		}
		logger.exiting(this.getClass().getName(), "beforeStoredProcedureExecution", newSolicitudID);
		return newSolicitudID;
	}



}
