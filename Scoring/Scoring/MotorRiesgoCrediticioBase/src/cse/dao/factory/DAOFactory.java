package cse.dao.factory;

import cse.database.dao.AlertaSistemaDAO;
import cse.database.dao.ClasificacionempresaDAO;
import cse.database.dao.CondicionotorgamientoDAO;
import cse.database.dao.EquifaxCacheDAO;
import cse.database.dao.EstadocivilDAO;
import cse.database.dao.EvaluaCreditScoringPersonasDAO;
import cse.database.dao.SolicitudDAO;
import cse.database.dao.SolicitudcondicionotorgamientoDAO;
import cse.database.dao.traza.TrazaCalificacionRiesgoExternaDAO;
import cse.external.client.dao.CalificacionRiesgoExternaDAO;
import cse.legacy.dao.ClasificacionRiesgoDAO;
import cse.legacy.dao.DatosDemograficosDAO;
import cse.legacy.dao.InformacionLaboralDAO;


public interface DAOFactory {

	public DatosDemograficosDAO getDatosDemograficosDAO();

	public InformacionLaboralDAO getInformacionLaboralDAO();

	public ClasificacionRiesgoDAO getClasificacionRiesgoDAO();

	public CalificacionRiesgoExternaDAO getCalificacionRiesgoExternaDAO();
	
	public SolicitudDAO getSolicitudDAO();
	
	public ClasificacionempresaDAO getClasificacionEmpresaDAO();

	public EstadocivilDAO getEstadoCivilDAO();

	public SolicitudcondicionotorgamientoDAO getSolicitudCondicionOtorgamientoDAO();
	
	public CondicionotorgamientoDAO getCondicionOtorgamientoDAO();

	public EvaluaCreditScoringPersonasDAO getEvaluaCreditScoringPersonasDAO();

	public EquifaxCacheDAO getEquifaxCacheDAO();
	
	public AlertaSistemaDAO getAlertaSistemaDAO();

	public TrazaCalificacionRiesgoExternaDAO getTrazaCalificacionRiesgoExternaDAO();

	

	
	

}