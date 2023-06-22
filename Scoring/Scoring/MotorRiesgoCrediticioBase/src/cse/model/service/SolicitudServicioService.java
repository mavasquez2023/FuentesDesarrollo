package cse.model.service;

import cse.model.businessobject.SolicitudServicio;

public interface SolicitudServicioService {

	public SolicitudServicio findSolicitudServicio(String idSolicitud);
	public String createSolicitudServicio();
	
}
