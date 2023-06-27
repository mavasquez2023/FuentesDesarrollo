// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 15-04-2016 13:34:59
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   InformeChequeDao.java

package cl.laaraucana.persistencia.dao.impl;

import cl.laaraucana.config.Config;
import cl.laaraucana.config.Database;
import cl.laaraucana.exception.DaoException;
import cl.laaraucana.persistencia.dao.InformesChequeDaoI;
import cl.laaraucana.util.Utils;
import java.sql.*;

public class InformeChequeDao
    implements InformesChequeDaoI
{

    public InformeChequeDao()
    {
        schema = Config.getConfig("SCHEMA");
        schemaTes = Config.getConfig("SCHEMA_TES");
    }

    public ResultSet getInformeAdmin(String codOficina, String codCajero, String fechaInicio, String fechaFin)
        throws DaoException
    {
        Connection conn = Database.getConnection();
        ResultSet res = null;
        String sql = "";
        String whereOfi = "";
        String whereCaj = "";
        try
        {
            codOficina = Utils.rellenaConCeros(codOficina, 3);
            codCajero = Utils.rellenaConCeros(codCajero, 3);
            if(!Utils.isZero(codOficina))
                whereOfi = " AND CMBA=" + codOficina;
            if(!Utils.isZero(codCajero))
                whereCaj = " AND TE1BA=" + codCajero;
            sql = "SELECT  F1.TE1BA CODCAJERO, F1.CMBA CODOFI, F2.TE9CA FOLIOING, TES1.TEA7A CODBARRA, TES2.TE1YA CODCONC, TES1.TEQA CODAREA, F1.TE2FA CODBANCO, F1.TE25A NROCTACTE, F1.TE40A FECGESBAN, F2.TEDIA NROPAPLTA, SUMA.SUMA, F2.TE5GA NROCHEQUE, F2.TE7RA MTOCHEQUE,  CASE WHEN F2.TE50A = 'E' THEN F1.TE3WA ELSE F1.TEP7A END NROEGRESO, F2.TE7RA MTORECAUDADO, F2.TE50A TIPODEPOS  FROM  (\tSELECT * FROM   \t\t" + schema + ".TE012F1  " + "\t\tWHERE\tTE40A\tBETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'  " + whereCaj + whereOfi + ") F1  " + "JOIN  " + "(\tSELECT * FROM   " + "\t\t" + schema + ".TE012F2  " + "\t\tWHERE\tTE40A\tBETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'  " + whereCaj + whereOfi + ")  F2  " + "ON  F1.CMBA = F2.CMBA  AND F1.TE1BA = F2.TE1BA  AND F1.TE40A = F2.TE40A  AND F1.TE2FA = F2.TE2FA AND F1.TE25A = F2.TE25A  " + "JOIN  " + "(  " + "\t\tSELECT COUNT(TEDIA) CANT, SUM(TE7RA) SUMA, TEDIA, TE1BA, CMBA, TE40A   " + "\t\tFROM " + schema + ".TE012F2   " + "\t\tWHERE\tTE40A\tBETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'  " + whereCaj + whereOfi + "\t\tGROUP BY TE1BA, CMBA, TE40A, TEDIA  " + ") SUMA  " + "ON  F2.TEDIA = SUMA.TEDIA AND F2.TE1BA = SUMA.TE1BA AND F2.CMBA  = SUMA.CMBA AND F2.TE40A = SUMA.TE40A  " + "JOIN " + schemaTes + ".TE07F1 TES1 ON F2.TE9CA = TES1.TE9CA  " + "LEFT JOIN " + schemaTes + ".TE07F2 TES2 ON TES1.TE3WA = TES2.TE3WA WHERE F2.TE50A = 'C' ";
            sql = sql + "union all SELECT  F1.TE1BA CODCAJERO, F1.CMBA CODOFI, F2.TE9CA FOLIOING, '' CODBARRA, 0, 0 CODAREA, F1.TE2FA CODBANCO, F1.TE25A NROCTACTE, F1.TE40A FECGESBAN, F2.TEDIA NROPAPLTA, SUMA.SUMA, F2.TE5GA NROCHEQUE, F2.TE7RA MTOCHEQUE,  CASE WHEN F2.TE50A = 'E' THEN F1.TE3WA ELSE F1.TEP7A END NROEGRESO, F2.TE7RA MTORECAUDADO, F2.TE50A TIPODEPOS  FROM  (\tSELECT * FROM   \t\t" + schema + ".TE012F1  " + "\t\tWHERE\tTE40A\tBETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'  " + whereCaj + whereOfi + ") F1  " + "JOIN  " + "(\tSELECT * FROM   " + "\t\t" + schema + ".TE012F2  " + "\t\tWHERE\tTE40A\tBETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'  " + whereCaj + whereOfi + ")  F2  " + "ON  F1.CMBA = F2.CMBA  AND F1.TE1BA = F2.TE1BA  AND F1.TE40A = F2.TE40A  AND F1.TE2FA = F2.TE2FA AND F1.TE25A = F2.TE25A  " + "JOIN  " + "(  " + "\t\tSELECT COUNT(TEDIA) CANT, SUM(TE7RA) SUMA, TEDIA, TE1BA, CMBA, TE40A   " + "\t\tFROM " + schema + ".TE012F2   " + "\t\tWHERE\tTE40A\tBETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'  " + whereCaj + whereOfi + "\t\tGROUP BY TE1BA, CMBA, TE40A, TEDIA  " + ") SUMA  " + "ON  F2.TEDIA = SUMA.TEDIA AND F2.TE1BA = SUMA.TE1BA AND F2.CMBA  = SUMA.CMBA AND F2.TE40A = SUMA.TE40A  WHERE F2.TE50A = 'E' ";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            res = pStmt.executeQuery();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new DaoException("Error al ejecutar consulta: " + e.getCause());
        }
        return res;
    }

    public ResultSet getInformeCajero(String codOficina, String codCajero, String fecha)
        throws DaoException
    {
        Connection conn = Database.getConnection();
        ResultSet res = null;
        try
        {
            String sql = "SELECT F1.TE1BA, F1.CMBA, F1.TE40A, F1.TE2FA, F1.TE25A, F2.TEDIA, CANT.CANT,  CASE WHEN F2.TE50A = 'E' THEN F1.TE3WA ELSE F1.TEP7A END FOLIO, F2.TE7RA, F2.TE50A  FROM (\tSELECT * FROM   \t\t" + schema + ".TE012F1  " + "\t\tWHERE\tTE1BA\t= ?  " + "\t\tAND\t\tCMBA\t= ?  " + "\t\tAND\t\tTE40A\t= '" + fecha + "'  " + "\t) F1  " + "JOIN (\tSELECT * FROM   " + "\t\t" + schema + ".TE012F2  " + "\t\tWHERE\tTE1BA\t= ?  " + "\t\tAND\t\tCMBA\t= ?  " + "\t\tAND\t\tTE40A\t= '" + fecha + "'  " + "\t)  F2  " + "ON  F1.CMBA = F2.CMBA AND F1.TE1BA = F2.TE1BA AND F1.TE40A = F2.TE40A AND F1.TE2FA = F2.TE2FA AND F1.TE25A = F2.TE25A  " + "LEFT JOIN (  " + "\t\tSELECT COUNT(TEDIA) CANT, TEDIA   " + "\t\tFROM " + schema + ".TE012F2   " + "\t\tWHERE\tTE1BA\t= ?  " + "\t\tAND\t\tCMBA\t= ?  " + "\t\tAND\t\tTE40A\t= '" + fecha + "'  " + "\t\tGROUP BY TEDIA  " + "\t  ) CANT  " + "ON F2.TEDIA = CANT.TEDIA";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, codCajero);
            pStmt.setString(2, codOficina);
            pStmt.setString(3, codCajero);
            pStmt.setString(4, codOficina);
            pStmt.setString(5, codCajero);
            pStmt.setString(6, codOficina);
            res = pStmt.executeQuery();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new DaoException("Error al ejecutar consulta: " + e.getMessage());
        }
        return res;
    }

    public static String schema;
    public static String schemaTes;
}
