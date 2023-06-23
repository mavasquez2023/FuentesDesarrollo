package cl.araucana.aporte.orqOutput.delegate;

import java.util.ArrayList;
import java.util.List;

import cl.araucana.aporte.orqOutput.bo.ErrorResultBO;
import cl.araucana.aporte.orqOutput.bo.OrqOutputResultBO;
import cl.araucana.aporte.orqOutput.ejb.OrqOutputLocal;
import cl.araucana.aporte.orqOutput.ejb.OrqOutputRemote;
import cl.araucana.aporte.orqOutput.locator.OrqOutputLocator;
import cl.araucana.aporte.orqOutput.service.vo.ErrorResultVO;
import cl.araucana.aporte.orqOutput.service.vo.OrqOutputResultVO;
import cl.araucana.core.util.Rut;
import cl.laaraucana.capaservicios.manager.RecaudacionMgr;
import cl.laaraucana.capaservicios.persistencia.vo.CabeceraRendicion;
import cl.laaraucana.capaservicios.persistencia.vo.DetalleRendicion;
import cl.laaraucana.capaservicios.persistencia.vo.RespuestaVO;
import cl.laaraucana.config.Constantes;


public class OrqOutputDelegate {
    public OrqOutputResultVO recuperacionPago(int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago, String ofi_folio){
        OrqOutputResultVO orqOutputVO = new OrqOutputResultVO();
        OrqOutputResultBO orqOutputBO = new OrqOutputResultBO();

        ErrorResultBO errorBO = new ErrorResultBO();
        ErrorResultVO errorVO = new ErrorResultVO();
        ErrorResultBO errorCrd= new ErrorResultBO();
        
        try{
            OrqOutputLocal orqOutputLocal = OrqOutputLocator.getEjbSample();
            orqOutputBO = orqOutputLocal.recuperacionPago(rut, montoCredito, montoLeasing, montoAporte, periodoAporte, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
            if(rut <= 0){
                errorCrd.setCodError(-10);
                errorCrd.setGlsError("El campo RUT es obligatorio");
            }
            else if(montoCredito <= 0){
                errorCrd.setCodError(-10);
                errorCrd.setGlsError("Monto Crédito a actualizar es menor o igual a 0");
            }			
            else if(documentoPago <= 0){
                errorCrd.setCodError(-10);
                errorCrd.setGlsError("Documento de Pago debe ser distinto de 0");
            }
            else if(usuario.length() == 0){
                errorCrd.setCodError(-10);
                errorCrd.setGlsError("Debe ingresar el campo Usuario");
            }
            else if(fechaRecaudacion.length() == 0 || fechaRecaudacion.length() < 8 || fechaRecaudacion.length()> 8){
                errorCrd.setCodError(-10);
                errorCrd.setGlsError("Fecha de Recaudación debe tener el formato DDMMAAAA");
            }
            else if(horaRecaudacion.length() == 0 || horaRecaudacion.length() < 5 || horaRecaudacion.length()> 5){
                errorCrd.setCodError(-10);
                errorCrd.setGlsError("Hora de Recaudación debe tener el formato HH:MM");
            }
            else{
            	RecaudacionMgr recaudacion= new RecaudacionMgr();
            	CabeceraRendicion cabecera= new CabeceraRendicion();
            	cabecera.setComprobante(String.valueOf(documentoPago));
            	List<DetalleRendicion> listaDetalles= new ArrayList<DetalleRendicion>();
            	DetalleRendicion detalle= new DetalleRendicion();
            	detalle.setMontoPago(String.valueOf(montoCredito));
            	Rut rutdv= new Rut(rut);
            	detalle.setRut(rutdv.getNumber()+"-" + rutdv.getDV());
            	detalle.setPeriodo(String.valueOf(periodoAporte));
            	detalle.setFechaPago(fechaRecaudacion);
            	detalle.setFolioCredito(ofi_folio);
            	listaDetalles.add(detalle);
            	RespuestaVO rpta=recaudacion.ejecutarRecuperacionSap(listaDetalles, cabecera);
            	if(!rpta.getCodigo().equals(Constantes.WS_COD_SUCCESS)){
            		errorCrd.setCodError(Integer.parseInt(rpta.getCodigo()));
            		errorCrd.setGlsError(rpta.getMensaje());
            		if(orqOutputBO.getErrorBO().getCodError()!=0){
            			orqOutputBO.getErrorBO().setCodError(-7);
            			orqOutputBO.getErrorBO().setGlsError(orqOutputBO.getErrorBO().getGlsError() + "\n" + errorCrd.getGlsError());
            		}else{
            			orqOutputBO.getErrorBO().setCodError(errorCrd.getCodError());
            			orqOutputBO.getErrorBO().setGlsError(errorCrd.getGlsError());
            		}
            	}
            	if (orqOutputBO.getErrorBO().getGlsError().length()> 130){
            		orqOutputBO.getErrorBO().setGlsError(orqOutputBO.getErrorBO().getGlsError().substring(0, 130));
                }
                
            }
        }catch(Exception e){
            e.printStackTrace();
            errorVO.setCodError(-4);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString().substring(0,200));
            }
            orqOutputVO.setErrorVO(errorVO);
            return orqOutputVO;
        }

        errorBO = orqOutputBO.getErrorBO();
        errorVO.setCodError(errorBO.getCodError());
        errorVO.setGlsError(errorBO.getGlsError());

        orqOutputVO.setErrorVO(errorVO);
        return orqOutputVO;
    }

    public OrqOutputResultVO recuperacionPagoRemote (int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago){
        OrqOutputResultVO orqOutputVO = new OrqOutputResultVO();
        OrqOutputResultBO orqOutputBO = new OrqOutputResultBO();

        ErrorResultBO errorBO = new ErrorResultBO();
        ErrorResultVO errorVO = new ErrorResultVO();

        try{
            OrqOutputRemote orqOutputRemote = OrqOutputLocator.getEjbSampleRemote();
            orqOutputBO = orqOutputRemote.recuperacionPago(rut, montoCredito, montoLeasing, montoAporte, periodoAporte, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
        }catch(Exception e){
            e.printStackTrace();
            errorVO.setCodError(-4);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString().substring(0,200));
            }
            orqOutputVO.setErrorVO(errorVO);
            return orqOutputVO;
        }

        errorBO = orqOutputBO.getErrorBO();
        errorVO.setCodError(errorBO.getCodError());
        errorVO.setGlsError(errorBO.getGlsError());

        orqOutputVO.setErrorVO(errorVO);
        return orqOutputVO;
    }
}
