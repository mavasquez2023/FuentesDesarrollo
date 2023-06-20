// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 19-04-2023 12:23:04
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   LicenciaServiceImpl.java

package cl.araucana.wsatento.business.service.impl;

import cl.araucana.wsatento.business.dao.LicenciaDao;
import cl.araucana.wsatento.business.dao.LicenciaRestDao;
import cl.araucana.wsatento.business.dao.impl.LicenciaDaoImpl;
import cl.araucana.wsatento.business.dao.impl.LicenciaRestDaoImpl;
import cl.araucana.wsatento.business.service.LicenciaRestService;
import cl.araucana.wsatento.business.service.LicenciaService;
import cl.araucana.wsatento.business.to.LicenciaRestTO;
import cl.araucana.wsatento.common.util.RutUtil;
import cl.araucana.wsatento.integration.exception.WSAtentoException;
import java.util.List;

public class LicenciaRestServiceImpl
    implements LicenciaRestService
{

    public LicenciaRestServiceImpl()
    {
    }

    public List<LicenciaRestTO> getLicenicas(String rut)
        throws WSAtentoException
    {
        Integer rutEntero = RutUtil.getParteEnteraRut(rut);
        LicenciaRestDao licenciaDao = new LicenciaRestDaoImpl();
        List<LicenciaRestTO> listaLicencias = licenciaDao.getLicenciasByRut(rutEntero);
        return listaLicencias;
    }
}
