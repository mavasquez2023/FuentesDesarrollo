package cl.araucana.clientewsfonasa.business.services.impl;


public interface WSConsultaFonasaImpl_SEI extends java.rmi.Remote
{
  public cl.araucana.clientewsfonasa.business.to.ResponseFormFonasaTO consultarEstadoFormulario(int color,int numeroLicencia,int rutAfiliado) throws cl.araucana.clientewsfonasa.common.exception.ServiceException,cl.araucana.clientewsfonasa.common.exception.DaoException;
  public cl.araucana.clientewsfonasa.business.to.ResponseWSFonasaTO consultarRutFonasa(java.lang.String rut) throws cl.araucana.clientewsfonasa.common.exception.ServiceException,cl.araucana.clientewsfonasa.common.exception.DaoException;
}