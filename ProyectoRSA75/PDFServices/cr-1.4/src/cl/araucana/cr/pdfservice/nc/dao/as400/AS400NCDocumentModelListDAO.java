/**
 * 
 */
package cl.araucana.cr.pdfservice.nc.dao.as400;

/**
 * @author 11648834-5
 *
 */
import cl.araucana.core.integration.DAO.DAOException;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.logging.LogManager;
import cl.araucana.cr.pdfservice.nc.Constants;
import cl.araucana.cr.pdfservice.nc.dao.NCDocumentModelListDAO;
import cl.araucana.fpg.DocumentModelList;
import java.io.PrintStream;
import java.sql.*;
import java.util.logging.Logger;
import javax.sql.DataSource;

// Referenced classes of package cl.araucana.cr.pdfservice.nc.dao.as400:
//            AS400NCDocumentModelList

public class AS400NCDocumentModelListDAO extends NCDocumentModelListDAO
    implements Constants
{
	public static final String DATA_SOURCE = "dataSource";
    public static final String SCHEMA_NAME = "schemaName";
    public static boolean debug = Boolean.getBoolean("dao.debug");
    private static Logger logger = LogManager.getLogger();
    protected DataSource dataSource;
    private boolean initialized;
    private String fechaVencimiento;
    private String sFechaTopePago;
    
    public AS400NCDocumentModelListDAO()
    {
    }

    public void init()
        throws DAOException
    {
        super.init();
        try
        {
            dataSource = (DataSource)getProperty("dataSource");
            if(dataSource == null)
                throw new DAOException("Missed 'dataSource' property.");
        }
        catch(ClassCastException e)
        {
            throw new DAOException("Property 'dataSource' must be a javax.sql.DataSource instance.");
        }
        initialized = true;
    }

    public boolean isInitialized()
    {
        return initialized;
    }

    public DocumentModelList getAllDocumentModelList()
        throws DAOException
    {
        return getDocumentModelList(getGranulariyCondition(0, 0));
    }

    public DocumentModelList getDocumentModelListByOficina(int oficina)
        throws DAOException
    {
        return getDocumentModelList(getGranulariyCondition(1, oficina));
    }

    public DocumentModelList getDocumentModelListByEmpresa(int empresa)
        throws DAOException
    {
        return getDocumentModelList(getGranulariyCondition(2, empresa));
    }

    public DocumentModelList getDocumentModelListByFolio(int folio)
        throws DAOException
    {
        return getDocumentModelList(getGranulariyCondition(3, folio));
    }

    private DocumentModelList getDocumentModelList(String granulariyCondition)
        throws DAOException
    {
        Connection connection;
        PreparedStatement documentHeaderStmt;
        PreparedStatement documentDetailStmt;
        connection = null;
        documentHeaderStmt = null;
        documentDetailStmt = null;
        AS400NCDocumentModelList as400ncdocumentmodellist;
        try{
        	prepareParameters();
        	int documentCount;
        	connection = dataSource.getConnection();
        	String filterCondition = getFilterCondition();
        	documentCount = getDocumentCount(connection, getHeaderCountSQlStmt(filterCondition, granulariyCondition));
        	documentHeaderStmt = connection.prepareStatement(getHeaderSQlStmt(filterCondition, granulariyCondition));
        	documentDetailStmt = connection.prepareStatement(getDetailSQlStmt(filterCondition, granulariyCondition));
        	//documentHeaderStmt.setFetchSize(150);
        	//documentDetailStmt.setFetchSize(1500);
        	logger.info(">>Instanciando AS400NCDocumentModelList, connection=" + !connection.isClosed());
        	logger.info(">>Parametros, period=" + period + ", sFechaTopePago" + sFechaTopePago);
        	as400ncdocumentmodellist=  new AS400NCDocumentModelList(documentCount, documentHeaderStmt, documentDetailStmt, filter, period, sFechaTopePago);
        }catch(Exception e){
        	e.printStackTrace();
        	if(documentHeaderStmt != null)
                try
                {
                    documentHeaderStmt.close();
                }
                catch(SQLException sqlexception) { }
            if(documentDetailStmt != null)
                try
                {
                    documentDetailStmt.close();
                }
                catch(SQLException sqlexception1) { }
            if(connection != null)
                try
                {
                    connection.close();
                }
                catch(SQLException sqlexception2) { }
        	throw new DAOException("Cannot get document model list", e);
        }
        return as400ncdocumentmodellist;
        
        
    }

    private void prepareParameters()
    {
        fechaVencimiento = period.addMonths(1).addDays(-1).toString(3, "");
        AbsoluteDate aDate;
        for(aDate = period.addMonths(1).addDays(9); !aDate.isWorkday(); aDate = aDate.addDays(1));
        sFechaTopePago = aDate.getDay() + " de " + aDate.getMonthName().toUpperCase() + " del " + aDate.getYear();
    }

    private String getFilterCondition()
    {
        String clause = "c.FECHA_VCTO_NOMINA = '" + fechaVencimiento + "'\n ";
        if(filter == 0)
            clause = clause + "   AND c.TIPO_NOMINA = 1 AND c.PRODUCTO <> 'ESR' ";
        else
            clause = clause + "   AND c.TIPO_NOMINA = 4 ";
        return clause;
    }

    private String getGranulariyCondition(int selection, int parameter)
    {
        switch(selection)
        {
        case 0: // '\0'
            return null;

        case 1: // '\001'
            return "c.OFICINA_PAGO = " + parameter + " ";

        case 2: // '\002'
            return "c.RUT_EMPRESA = " + parameter + " ";
        }
        return "c.FOLIO_NOMINA = " + parameter + " ";
    }

    private String getHeaderCountSQlStmt(String filterCondition, String granulariyCondition)
    {	
    	//String sqlStmt = "SELECT COUNT(*) AS N_DOCUMENTOS\n  FROM RCDTA.RCF300 c\n WHERE " + filterCondition + "\n";
    	
    	StringBuffer sqlStmt= new StringBuffer();
    	sqlStmt.append("select count(1) AS N_DOCUMENTOS \n");
    	sqlStmt.append("from ( \n");
    	sqlStmt.append("(SELECT FOLIO_NOMINA \n");
    	sqlStmt.append("FROM NRPDTA.NRP15F1 c \n");
    	sqlStmt.append("WHERE " + filterCondition + " \n");
        
        if(filter == 1)
        	sqlStmt.append("AND c.RUT_EMPRESA <> 61533000 \n");
        if(granulariyCondition != null)
        	sqlStmt.append("AND " + granulariyCondition + " \n");
        sqlStmt.append("AND c.RUT_EMPRESA <> 0 " );
        sqlStmt.append("GROUP BY FOLIO_NOMINA )" );
        sqlStmt.append(" UNION \n");
    	sqlStmt.append("(SELECT FOLIO_NOMINA \n");
    	sqlStmt.append("FROM NRPDTA.NRP15HF1 c \n");
    	sqlStmt.append("WHERE " + filterCondition + " \n");
        
        if(filter == 1)
        	sqlStmt.append("AND c.RUT_EMPRESA <> 61533000 \n");
        if(granulariyCondition != null)
        	sqlStmt.append("AND " + granulariyCondition + " \n");
        sqlStmt.append("AND c.RUT_EMPRESA <> 0 " );
        sqlStmt.append("GROUP BY FOLIO_NOMINA )" );
        sqlStmt.append(	") as A ");
        if(debug)
        {
            System.out.println();
            System.out.println("-------------HeaderCountSQLStmt--------------");
            System.out.print(sqlStmt);
            System.out.println("---------------------------------------------");
        }
        logger.info("-------------HeaderCountSQLStmt--------------");
        logger.info(sqlStmt + "\n");
        logger.info("---------------------------------------------");
        return sqlStmt.toString();
    }

    private String getHeaderSQlStmt(String filterCondition, String granulariyCondition)
    {
        
        //String sqlStmt = "SELECT c.FOLNOM AS FOLIO,\n       (SELECT comp.TEA7A\n          FROM TEDTA.TE07F1 comp\n         WHERE comp.TE3WA = c.FOLNOM) AS CODIGO_BARRA,\n       c.OFICOD AS OFICINA,\n       COALESCE(\n" + razonSocialGetter + "          'NO ENCONTRADA') AS RAZON_SOCIAL,\n" + "       COALESCE(\n" + "          (SELECT TRIM(ax.SE5FAXQ)\n" + "             FROM AFDTA.AF01F1 ax\n" + "            WHERE ax.CMNA = c.EMPRUT\n" + "              AND ax.CMBA = c.OFICOD\n" + "              AND ax.CM1WA = c.EMPNOMCOD),\n" + "          '') AS NOMBRE_NOMINA,\n" + "       c.EMPRUT AS RUT,\n" + "       c.EMPNOMCOD COD_NOMINA,\n" + "       c.NOMMON AS MONTO,\n" + "      (SELECT COUNT(*)\n" + "         FROM RCDTA.RCF310 d\n" + "        WHERE d.FOLNOM = c.FOLNOM) AS NCUOTAS\n" + "  FROM RCDTA.RCF300 c\n" + " WHERE " + filterCondition + " AND c.EMPRUT <> 0 \n";
    	
    	StringBuffer sqlStmt= new StringBuffer();
    	sqlStmt.append("select FOLIO_NOMINA as FOLIO, \n");
    	sqlStmt.append("(SELECT comp.TEA7A FROM TEDTA.TE07F1 comp \n");
    	sqlStmt.append("WHERE comp.TE3WA = c.FOLIO_NOMINA and c.FOLIO_NOMINA>0) AS CODIGO_BARRA, \n");
    	sqlStmt.append("OFICINA_PAGO as OFICINA, \n");
    	sqlStmt.append("NOMBRE_EMPRESA as RAZON_SOCIAL, \n");
    	sqlStmt.append(" COALESCE( (SELECT TRIM(ax.SE5FAXQ) FROM AFDTA.AF01F1 ax \n");
    	sqlStmt.append("WHERE ax.CMNA = c.RUT_EMPRESA AND ax.CMBA = c.OFICINA_PAGO AND ax.CM1WA = c.ANEXO_NOMINA), \n");
    	sqlStmt.append("'') AS NOMBRE_NOMINA, \n");
    	sqlStmt.append("RUT_EMPRESA as RUT, \n");
    	sqlStmt.append("ANEXO_NOMINA as COD_NOMINA, \n");
    	sqlStmt.append("SUM(IMPORTE_CUOTA_MONEDA_LOCAL) as MONTO, \n");
    	sqlStmt.append("COUNT(*)  AS NCUOTAS \n");
    	sqlStmt.append("from NRPDTA.NRP15F1 c \n");
    	sqlStmt.append(" WHERE " + filterCondition + " AND c.RUT_EMPRESA <> 0 AND c.FOLIO_NOMINA>0 \n");
    	if(filter == 1)
        	sqlStmt.append("   AND c.RUT_EMPRESA <> 61533000 \n");
    	if(granulariyCondition != null)
        	sqlStmt.append("   AND " + granulariyCondition + " \n ");
    	sqlStmt.append("GROUP BY FOLIO_NOMINA, OFICINA_PAGO, NOMBRE_EMPRESA, RUT_EMPRESA, ANEXO_NOMINA \n");
    	sqlStmt.append(" UNION \n");
    	sqlStmt.append("select FOLIO_NOMINA as FOLIO, \n");
    	sqlStmt.append("COALESCE((SELECT comp.TEA7A FROM TEDTA.TE07F1 comp \n");
    	sqlStmt.append("WHERE comp.TE3WA = c.FOLIO_NOMINA and c.FOLIO_NOMINA>0), \n");
    	sqlStmt.append("(SELECT comp.TEA7A FROM TEDTA.TE44F1 comp \n");
    	sqlStmt.append("WHERE comp.TE3WA = c.FOLIO_NOMINA and c.FOLIO_NOMINA>0)) AS CODIGO_BARRA, \n");
    	sqlStmt.append("OFICINA_PAGO as OFICINA, \n");
    	sqlStmt.append("NOMBRE_EMPRESA as RAZON_SOCIAL, \n");
    	sqlStmt.append(" COALESCE( (SELECT TRIM(ax.SE5FAXQ) FROM AFDTA.AF01F1 ax \n");
    	sqlStmt.append("WHERE ax.CMNA = c.RUT_EMPRESA AND ax.CMBA = c.OFICINA_PAGO AND ax.CM1WA = c.ANEXO_NOMINA), \n");
    	sqlStmt.append("'') AS NOMBRE_NOMINA, \n");
    	sqlStmt.append("RUT_EMPRESA as RUT, \n");
    	sqlStmt.append("ANEXO_NOMINA as COD_NOMINA, \n");
    	sqlStmt.append("SUM(IMPORTE_CUOTA_MONEDA_LOCAL) as MONTO, \n");
    	sqlStmt.append("COUNT(*)  AS NCUOTAS \n");
    	sqlStmt.append("from NRPDTA.NRP15HF1 c \n");
    	sqlStmt.append(" WHERE " + filterCondition + " AND c.RUT_EMPRESA <> 0 AND c.FOLIO_NOMINA>0 \n");
    	if(filter == 1)
        	sqlStmt.append("   AND c.RUT_EMPRESA <> 61533000 \n");
    	if(granulariyCondition != null)
        	sqlStmt.append("   AND " + granulariyCondition + " \n ");
    	sqlStmt.append("GROUP BY FOLIO_NOMINA, OFICINA_PAGO, NOMBRE_EMPRESA, RUT_EMPRESA, ANEXO_NOMINA \n");
    	sqlStmt.append("ORDER BY FOLIO \n");
       
        if(debug)
        {
            System.out.println();
            System.out.println("----------------HeaderSQLStmt----------------");
            System.out.print(sqlStmt);
            System.out.println("---------------------------------------------");
        }
        logger.info("-------------HeaderSQLStmt--------------");
        logger.info(sqlStmt + "\n");
        logger.info("---------------------------------------------");
        return sqlStmt.toString();
    }

    private String getDetailSQlStmt(String filterCondition, String granulariyCondition)
    {
    	
        //String sqlStmt = "SELECT d.FOLNOM AS FOLIO,\n       d.RUTDEU AS RUT,\n       COALESCE(\n" + nombreDeudorGetter + "       'NO ENCONTRADO') AS NOMBRE,\n" + "       d.XOFIPRO AS OFIPRO,\n" + "       d.XCREFOL AS CREFOL,\n" + "       d.CUONUM AS CUOTA,\n" + "       (CASE WHEN d.XOFIPRO = 600 THEN\n" + "               0\n" + "       ELSE\n" + "\t\t\t(CASE WHEN d.XCREFOL = (SELECT CREFOL FROM CRDTA.CSF1000 aux WHERE d.XCREFOL= aux.CREFOL and d.XOFIPRO = aux.OFIPRO)\n" + "       THEN\n" + "            (SELECT cred.CRECUOTOT\n" + "               FROM CRDTA.CSF1000 cred\n" + "              WHERE d.XOFIPRO = cred.OFIPRO\n" + "                AND d.XCREFOL = cred.CREFOL)\n" + "\t\t\tELSE 0\n" + "\t\t\tEND)\n" + "\t\tEND) as  CASE,\n" + "       d.VALCUOMES + d.CUOVALGRA AS MONTO\n" + "  FROM RCDTA.RCF300 c,\n" + "       RCDTA.RCF310 d\n" + " WHERE " + filterCondition + " AND c.EMPRUT <> 0  \n";
    	StringBuffer sqlStmt= new StringBuffer();
    	sqlStmt.append("SELECT c.FOLIO_NOMINA as FOLIO, \n");
    	sqlStmt.append("c.RUT_PAGADOR as RUT, \n");
    	sqlStmt.append("COALESCE( TRIM(c.APELLIDO_PATERNO) || ' ' || TRIM(c.APELLIDO_MATERNO) || ' ' || TRIM(c.NOMBRE_DEUDOR), 'NO ENCONTRADO')  AS NOMBRE, \n");
    	sqlStmt.append("c.OFICINA_CREDITO as OFIPRO, \n");
    	sqlStmt.append("c.NUMERO_CONTRATO as CREFOL, \n");
    	sqlStmt.append("c.NRO_CUOTA as CUOTA, \n");
    	sqlStmt.append("c.CANTIDAD_CUOTAS as CASE, \n");
    	sqlStmt.append("c.IMPORTE_CUOTA_MONEDA_LOCAL as MONTO \n");
    	sqlStmt.append("FROM NRPDTA.NRP15F1 c \n");
    	sqlStmt.append(" WHERE " + filterCondition + " AND c.RUT_EMPRESA <> 0 AND c.FOLIO_NOMINA>0  \n");
    	
        if(filter == 1)
        	sqlStmt.append("  AND c.RUT_EMPRESA <> 61533000 \n");
        if(granulariyCondition != null)
        	sqlStmt.append("  AND " + granulariyCondition + " \n");
        sqlStmt.append(" UNION \n");
        sqlStmt.append("SELECT c.FOLIO_NOMINA as FOLIO, \n");
    	sqlStmt.append("c.RUT_PAGADOR as RUT, \n");
    	sqlStmt.append("COALESCE( TRIM(c.APELLIDO_PATERNO) || ' ' || TRIM(c.APELLIDO_MATERNO) || ' ' || TRIM(c.NOMBRE_DEUDOR), 'NO ENCONTRADO')  AS NOMBRE, \n");
    	sqlStmt.append("c.OFICINA_CREDITO as OFIPRO, \n");
    	sqlStmt.append("c.NUMERO_CONTRATO as CREFOL, \n");
    	sqlStmt.append("c.NRO_CUOTA as CUOTA, \n");
    	sqlStmt.append("c.CANTIDAD_CUOTAS as CASE, \n");
    	sqlStmt.append("c.IMPORTE_CUOTA_MONEDA_LOCAL as MONTO \n");
    	sqlStmt.append("FROM NRPDTA.NRP15HF1 c \n");
    	sqlStmt.append(" WHERE " + filterCondition + " AND c.RUT_EMPRESA <> 0 AND c.FOLIO_NOMINA>0  \n");
    	
        if(filter == 1)
        	sqlStmt.append("  AND c.RUT_EMPRESA <> 61533000 \n");
        if(granulariyCondition != null)
        	sqlStmt.append("  AND " + granulariyCondition + " \n");
        sqlStmt.append("ORDER BY FOLIO \n");
        if(debug)
        {
            System.out.println();
            System.out.println("----------------DetailSQLStmt----------------");
            System.out.print(sqlStmt);
            System.out.println("---------------------------------------------");
        }
        logger.info("-------------DetailSQLStmt--------------");
        logger.info(sqlStmt + "\n");
        logger.info("---------------------------------------------");
        return sqlStmt.toString();
    }

    private int getDocumentCount(Connection connection, String sqlCountStmt)
        throws SQLException
    {	
    	logger.info("-------------getDocumentCount--------------");
        PreparedStatement documentCountStmt = null;
        ResultSet rs = null;
        int i;
        try
        {	
        	logger.info("Conecction Open=" + !connection.isClosed());
            documentCountStmt = connection.prepareStatement(sqlCountStmt);
            rs = documentCountStmt.executeQuery();
            rs.next();
            i = rs.getInt(1);
            logger.info("Cantidad=" + i);
        }
        finally
        {
            if(rs != null)
                try
                {
                    rs.close();
                }
                catch(SQLException sqlexception) { }
            if(documentCountStmt != null)
                try
                {
                    documentCountStmt.close();
                }
                catch(SQLException sqlexception1) { }
        }
        return i;
    }

    

}
