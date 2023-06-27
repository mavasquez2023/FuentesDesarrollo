// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 07/07/2010 15:05:55
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FuncionarioGlobalRole.java

package cl.araucana.core.registry.global;

import cl.araucana.core.business.TO.RutTO;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserNotFoundUserRegistryException;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.core.util.Rut;
import java.sql.*;
import java.util.*;
import javax.sql.DataSource;

// Referenced classes of package cl.araucana.core.registry.global:
//            DBLegacyGlobalRole, LegacyGlobalRole

public class FuncionarioGlobalRole extends DBLegacyGlobalRole
{

    public FuncionarioGlobalRole()
    {
    }

    public void setArgs(String args[])
    {
        super.setArgs(args);
        if(args.length != 1)
        {
            throw new IllegalArgumentException("Table name not specified.");
        } else
        {
            funcionarioTableName = args[0];
            config("FuncionarioGlobalRole: tableName = " + funcionarioTableName);
            return;
        }
    }

    public Collection getEnterprises(Object context, String userID)
        throws UserRegistryException
    {
        if(isUserMember(context, userID))
        {
            UserRegistryConnection connection = new UserRegistryConnection();
            List enterprises = new ArrayList();
            cl.araucana.core.registry.Enterprise enterprise = connection.getEnterprise(super.localEnterpriseID);
            connection.close();
            enterprises.add(enterprise);
            return enterprises;
        } else
        {
            return new ArrayList();
        }
    }

    public boolean isUserMember(Object context, String userID)
        throws UserRegistryException
    {
        return isUserMember(userID, super.localEnterpriseID);
    }

    public boolean isUserMember(Object context, String userID, String enterpriseID)
        throws UserRegistryException
    {
        if(!enterpriseID.equals(super.localEnterpriseID))
            return false;
        else
            return isEjecutivo(userID);
    }

    public Collection getUsers(Object context)
        throws UserRegistryException
    {
        return getUsers(super.localEnterpriseID);
    }

    public Collection getUsers(Object context, String enterpriseID)
        throws UserRegistryException
    {
        if(!enterpriseID.equals(super.localEnterpriseID))
            return new ArrayList();
        else
            return getEjecutivos();
    }

    private boolean isEjecutivo(String userID)
        throws UserRegistryException
    {
        String iUserID = iConvertUserID(userID);
        boolean found = false;
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        debug("FuncionarioGlobalRole: isEjecutivo: |" + userID + "|" + iUserID + "|");
        try
        {
            connection = super.dataSource.getConnection();
            stmt = connection.createStatement();
            String sqlStmt = "SELECT 1  FROM " + funcionarioTableName + " WHERE INT(SUBSTRING(FNCODFUN, 1, LENGTH(FNCODFUN) - 2))" + "    || SUBSTRING(FNCODFUN, LENGTH(FNCODFUN) - 1, 2)" + "       = '" + iUserID + "'" + "   AND FNESTADO = 'V' ";
            rs = stmt.executeQuery(sqlStmt);
            if(rs.next())
            {
                UserRegistryConnection URconnection = null;
                try
                {
                    URconnection = new UserRegistryConnection();
                    cl.araucana.core.registry.User user = URconnection.getUser(iUserID);
                    found = true;
                }
                catch(UserNotFoundUserRegistryException usernotfounduserregistryexception) { }
                finally
                {
                    if(URconnection != null)
                        URconnection.close();
                }
            }
        }
        catch(SQLException e)
        {
            debug("FuncionarioGlobalRole.isEjecutivo: >< " + e.getMessage());
        }
        finally
        {
            if(rs != null)
                try
                {
                    rs.close();
                }
                catch(SQLException sqlexception) { }
            if(stmt != null)
                try
                {
                    stmt.close();
                }
                catch(SQLException sqlexception1) { }
            if(connection != null)
                try
                {
                    connection.close();
                }
                catch(SQLException e) { }
        }
        return found;
    }

    private Collection getEjecutivos()
        throws UserRegistryException
    {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        UserRegistryConnection URconnection = null;
        List users = new ArrayList();
        try
        {
            connection = super.dataSource.getConnection();
            stmt = connection.createStatement();
            String sqlStmt = "SELECT DISTINCT       INT(SUBSTRING(FNCODFUN, 1, LENGTH(FNCODFUN) - 2))    || SUBSTRING(FNCODFUN, LENGTH(FNCODFUN) - 1, 2)       AS RUT  FROM " + funcionarioTableName + " WHERE FNESTADO = 'V'" + "   AND FNTIPOFUN IN ('PLA', 'EJE')";
            rs = stmt.executeQuery(sqlStmt);
            URconnection = new UserRegistryConnection();
            while(rs.next()) 
            {
                String userID = rs.getString("RUT");
                try
                {
                    Rut rut = new Rut(userID, true, false);
                    cl.araucana.core.registry.User user = URconnection.getUser(rut.toString(false));
                    users.add(user);
                }
                catch(IllegalArgumentException e)
                {
                    debug("FuncionarioGlobalRole: getEjecutivos() Bad rut '" + userID + "' was ignored.");
                }
                catch(UserNotFoundUserRegistryException e)
                {
                    debug("FuncionarioGlobalRole: getEjecutivos() User '" + userID + "' not found");
                }
            }
        }
        catch(SQLException e)
        {
            debug("FuncionarioGlobalRole: getEjecutivos: >< " + e.getMessage());
        }
        finally
        {
            if(rs != null)
                try
                {
                    rs.close();
                }
                catch(SQLException sqlexception) { }
            if(stmt != null)
                try
                {
                    stmt.close();
                }
                catch(SQLException sqlexception1) { }
            if(connection != null)
                try
                {
                    connection.close();
                }
                catch(SQLException sqlexception2) { }
            if(URconnection != null)
                URconnection.close();
        }
        return users;
    }

    private String iConvertUserID(String userID)
    {
        Rut rut = new Rut(userID, true, true);
        return rut.toString(false);
    }

    private String funcionarioTableName;
}