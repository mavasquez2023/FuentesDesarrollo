// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 19-04-2023 12:28:16
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   LicenciaService.java

package cl.araucana.wsatento.business.service;

import cl.araucana.wsatento.business.to.LicenciaRestTO;
import cl.araucana.wsatento.integration.exception.WSAtentoException;
import java.util.List;

public interface LicenciaRestService
{

    public abstract List<LicenciaRestTO> getLicenicas(String s)
        throws WSAtentoException;
}
