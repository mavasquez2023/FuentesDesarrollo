package cl.laaraucana.recepcionsil.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import cl.laaraucana.recepcionsil.manager.MainManager;
import cl.laaraucana.recepcionsil.service.vo.EntradaRecepcionSILVO;
import cl.laaraucana.recepcionsil.service.vo.HijoVO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe031VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe033VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe034VO;
import cl.laaraucana.recepcionsil.service.vo.LicenciaNivel2VO;
import cl.laaraucana.recepcionsil.service.vo.RemuneracionVO;
import cl.laaraucana.recepcionsil.service.vo.RespuestaVO;
import cl.laaraucana.recepcionsil.service.vo.SalidaRecepcionSILVO;
import cl.laaraucana.util.objectvalidate.ValidaObject;
import cl.laaraucana.util.objectvalidate.ValidateResultVO;
import javax.jws.WebService;


@WebService (targetNamespace="http://service.recepcionsil.laaraucana.cl/", serviceName="RecepcionSILServiceService", portName="RecepcionSILServicePort")
public class RecepcionSILServiceDelegate{

    cl.laaraucana.recepcionsil.service.RecepcionSILService _recepcionSILService = null;

    public SalidaRecepcionSILVO ingresoLicencia (EntradaRecepcionSILVO entrada) {
        return _recepcionSILService.ingresoLicencia(entrada);
    }

    public SalidaRecepcionSILVO completarLicencia (LicenciaNivel2VO entrada) {
        return _recepcionSILService.completarLicencia(entrada);
    }

    public RecepcionSILServiceDelegate() {
        _recepcionSILService = new cl.laaraucana.recepcionsil.service.RecepcionSILService(); }

}