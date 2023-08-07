// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 18-04-2023 19:52:03
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   LicenciaDaoImpl.java

package cl.araucana.wsatento.business.dao.impl;

import cl.araucana.wsatento.business.dao.AbstractDao;
import cl.araucana.wsatento.business.dao.LicenciaDao;
import cl.araucana.wsatento.business.dao.LicenciaRestDao;
import cl.araucana.wsatento.business.to.LicenciaRestTO;
import cl.araucana.wsatento.business.to.LicenciaTO;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class LicenciaRestDaoImpl extends AbstractDao
    implements LicenciaRestDao
{
	private Logger logger = Logger.getLogger(LicenciaRestDaoImpl.class);
	
    public LicenciaRestDaoImpl()
        throws WSAtentoException
    {
        openConnection();
    }

    public List<LicenciaRestTO> getLicenciasByRut(Integer rut)
        throws WSAtentoException
    {
        List<LicenciaRestTO> listaLicencias;
        listaLicencias = new ArrayList();
        String call = "{ call PSOBJ.GET_LICENCIAS(?) }";
        try
        {
            CallableStatement cstmt = getConnection().prepareCall(call);
            cstmt.setInt(1, rut.intValue());
            ResultSet rsLicencias = cstmt.executeQuery();
            SimpleDateFormat sdfyyyyMMdd = new SimpleDateFormat("yyyyMMdd");
            LicenciaRestTO objLic;
            for(; rsLicencias.next(); listaLicencias.add(objLic))
            {
                objLic = new LicenciaRestTO();
                String fechadesde= rsLicencias.getBigDecimal(4).toString();         
                objLic.setFechaDesde(fechadesde.substring(0, 4) + "-" + fechadesde.substring(4, 6) + "-" + fechadesde.substring(6, 8));
                objLic.setCompin(new Integer(rsLicencias.getInt(1)));
                objLic.setDias(new Integer(rsLicencias.getInt(5)));
                objLic.setCodSucPago((new StringBuffer(String.valueOf(rsLicencias.getInt(6)))).toString());
                String fechapago= rsLicencias.getBigDecimal(7).toString();
                objLic.setFechaPago(fechapago.substring(0, 4) + "-" + fechapago.substring(4, 6) + "-" + fechapago.substring(6, 8));
                objLic.setTipo(new Integer(rsLicencias.getInt(8)));
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new WSAtentoException("0252", "Error interno, comuniquese con el administrador.");
        }

        catch(Exception e)
        {
            e.printStackTrace();
            throw new WSAtentoException("0251", "Error interno, comuniquese con el administrador.");
        }
        finally
        {
            closeConnection();
        }
        return listaLicencias;
    }
}
