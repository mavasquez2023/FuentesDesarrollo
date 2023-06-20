/**
 * 
 */
package cl.araucana.arsdoc.dao;


import cl.araucana.arsdoc.common.*;
import cl.araucana.arsdoc.common.AppConfig;
import cl.araucana.arsdoc.util.GeneralUtils;
import cl.araucana.arsdoc.util.OrderBy;
import cl.araucana.arsdoc.vo.*;
import com.schema.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

// Referenced classes of package cl.araucana.arsdoc.dao:
//            OnDemandDAO

public class ArsdocOnDemandDAO implements OnDemandDAO {
    static Logger logger= Logger.getLogger(cl.araucana.arsdoc.dao.ArsdocOnDemandDAO.class);;
    private String host;
    private String usuario;
    private String password;
    private String directorioQuery;
    private String directorioGet;
    private String delim;
    private String exe;
    private String expLike;
    private Date fechaReferenciaCalculo;
    
    public ArsdocOnDemandDAO()
    {
        fechaReferenciaCalculo = DateUtils.getDate(31, 12, 1969);
        host = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/host");
        usuario = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/user");
        password = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/pass");
        directorioQuery = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/dir-query");
        exe = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/dir-exe");
        directorioGet = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/dir-get");
        delim = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/delimitador");
        expLike = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/make-like");
    }

    public IndiceVO getConsultaQuery(ParametrosConsultaVO parametros)
        throws Exception
    {
        String folder = parametros.getFolder();
        long maxHits = parametros.getMaxHits();
        String archivo = parametros.getSesionID();
        String applicationGroup1 = null;
        String applicationGroup2 = null;
        if(parametros.getApplicationGroup() != null && parametros.getApplicationGroup().size() > 0)
        {
            applicationGroup1 = (String)parametros.getApplicationGroup().get(0);
            applicationGroup2 = (String)parametros.getApplicationGroup().get(1);
        }
        CommandLine comando = null;
        IndiceVO indice = null;
        String cmd = null;
        cmd = exe + "arsdoc query -h " + host + " -u " + usuario + " -p " + password + " ";
        cmd = cmd + "-f \"" + folder + "\" ";
        cmd = cmd + "-d \"" + directorioQuery + "\" ";
        cmd = cmd + "-o \"" + archivo + "\" ";
        if(parametros.getMaxHits() != 0L)
            cmd = cmd + "-L \"" + parametros.getMaxHits() + "\" ";
        cmd = cmd + "-i \"WHERE ";
        Map where = parametros.getParametrosWhere();
        int cont = 0;
        for(Iterator it = where.entrySet().iterator(); it.hasNext();)
        {
            java.util.Map.Entry e = (java.util.Map.Entry)it.next();
            if(!e.getKey().equals(parametros.getNameAdditionalField()))
            {
                if(cont != 0)
                    cmd = cmd + " AND ";
                cmd = cmd + e.getKey();
                cmd = cmd + GeneralUtils.prepareComandoSql(e, parametros, expLike);
            }
            cont++;
        }

        boolean comandoEjecutado = false;
        if(parametros.getDateAdditionalField() != null && !parametros.getDateAdditionalField().equals(""))
        {
            String atributo = parametros.getNameComparedField();
            String operacionSql = (String)parametros.getTipoOperacion().get(atributo);
            if(operacionSql.equalsIgnoreCase("BETWEEN"))
            {
                SimpleDateFormat format = new SimpleDateFormat((String)parametros.getFormatos().get(atributo));
                StringTokenizerUtils tokens = new StringTokenizerUtils((String)parametros.getParametrosWhere().get(atributo), " ");
                Date fecha1 = format.parse(tokens.getFirstToken());
                Date fecha2 = format.parse(tokens.getLastToken());
                if(fecha1.getTime() >= parametros.getDateAdditionalField().getTime())
                {
                    logger.debug("Caso 1 between");
                    String newCmd = GeneralUtils.prepareComandoSqlAdditionalField(parametros, cmd, applicationGroup2, delim, expLike);
                    comando = new CommandLine(newCmd, archivo, directorioQuery, delim);
                    indice = comando.ejecutarComando();
                } else
                if(fecha2.getTime() < parametros.getDateAdditionalField().getTime())
                {
                    logger.debug("Caso 2 between");
                    cmd = cmd + GeneralUtils.prepareOrderBy(parametros);
                    cmd = cmd + " \" -G \"" + applicationGroup1 + "\" ";
                    cmd = cmd + " -e \"" + delim + "\" -v -H";
                    comando = new CommandLine(cmd, archivo, directorioQuery, delim);
                    indice = comando.ejecutarComando();
                } else
                if(fecha1.getTime() < parametros.getDateAdditionalField().getTime() && fecha2.getTime() >= parametros.getDateAdditionalField().getTime())
                {
                    logger.debug("Caso 3 between");
                    indice = execDobleConsulta(cmd, applicationGroup1, applicationGroup2, archivo, parametros);
                }
            } else
            if(operacionSql.equals(">") || operacionSql.equals(">="))
            {
                SimpleDateFormat format = new SimpleDateFormat((String)parametros.getFormatos().get(atributo));
                Date fecha = format.parse((String)parametros.getParametrosWhere().get(atributo));
                if(fecha.getTime() >= parametros.getDateAdditionalField().getTime())
                {
                    logger.debug("Caso 1 > y >=");
                    String newCmd = GeneralUtils.prepareComandoSqlAdditionalField(parametros, cmd, applicationGroup2, delim, expLike);
                    comando = new CommandLine(newCmd, archivo, directorioQuery, delim);
                    indice = comando.ejecutarComando();
                } else
                if(fecha.getTime() < parametros.getDateAdditionalField().getTime())
                {
                    logger.debug("Caso 2 > y >=");
                    indice = execDobleConsulta(cmd, applicationGroup1, applicationGroup2, archivo, parametros);
                }
            } else
            if(operacionSql.equals("<") || operacionSql.equals("<="))
            {
                SimpleDateFormat format = new SimpleDateFormat((String)parametros.getFormatos().get(atributo));
                Date fecha = format.parse((String)parametros.getParametrosWhere().get(atributo));
                if(fecha.getTime() < parametros.getDateAdditionalField().getTime())
                {
                    logger.debug("Caso 1 < y <=");
                    cmd = cmd + GeneralUtils.prepareOrderBy(parametros);
                    cmd = cmd + " \" -G \"" + applicationGroup1 + "\" ";
                    cmd = cmd + " -e \"" + delim + "\" -v -H";
                    comando = new CommandLine(cmd, archivo, directorioQuery, delim);
                    indice = comando.ejecutarComando();
                } else
                if(fecha.getTime() > parametros.getDateAdditionalField().getTime() || fecha.getTime() == parametros.getDateAdditionalField().getTime() && parametros.getTipoOperacion().get(atributo).equals("<="))
                {
                    logger.debug("Caso 2 < y <=");
                    indice = execDobleConsulta(cmd, applicationGroup1, applicationGroup2, archivo, parametros);
                }
            } else
            if(operacionSql.equals("="))
            {
                SimpleDateFormat format = new SimpleDateFormat((String)parametros.getFormatos().get(atributo));
                Date fecha = format.parse((String)parametros.getParametrosWhere().get(atributo));
                if(fecha.getTime() < parametros.getDateAdditionalField().getTime())
                {
                    logger.debug("Caso 1 =");
                    cmd = cmd + GeneralUtils.prepareOrderBy(parametros);
                    cmd = cmd + " \" -G \"" + applicationGroup1 + "\" ";
                    cmd = cmd + " -e \"" + delim + "\" -v -H";
                    comando = new CommandLine(cmd, archivo, directorioQuery, delim);
                    indice = comando.ejecutarComando();
                } else
                if(fecha.getTime() >= parametros.getDateAdditionalField().getTime())
                {
                    logger.debug("Caso 2 =");
                    String newCmd = GeneralUtils.prepareComandoSqlAdditionalField(parametros, cmd, applicationGroup2, delim, expLike);
                    comando = new CommandLine(newCmd, archivo, directorioQuery, delim);
                    indice = comando.ejecutarComando();
                }
            }
            comandoEjecutado = true;
        }
        if(!comandoEjecutado)
        {
            cmd = cmd + GeneralUtils.prepareOrderBy(parametros);
            cmd = cmd + " \" ";
            cmd = cmd + " -e \"" + delim + "\" -v -H";
            comando = new CommandLine(cmd, archivo, directorioQuery, delim);
            indice = comando.ejecutarComando();
        }
        return indice;
    }

    public ArchivoGetVO getConsultaGet(ParametrosConsultaVO parametros)
        throws Exception
    {
        BASE64Encoder encoder;
        BASE64Decoder decoder;
        byte bytes[];
        String tipo;
        String cmd = null;
        cmd = exe + "arsdoc get -h " + host + " -u " + usuario + " -p " + password + " ";
        cmd = cmd + "-f \"" + parametros.getFolder() + "\" ";
        cmd = cmd + "-d \"" + directorioGet + "\" ";
        cmd = cmd + "-o \"" + parametros.getSesionID() + "\" ";
        String applicationGroup1 = null;
        String applicationGroup2 = null;
        String applicationGroup = null;
        if(parametros.getApplicationGroup() != null && parametros.getApplicationGroup().size() > 0)
        {
            applicationGroup1 = (String)parametros.getApplicationGroup().get(0);
            applicationGroup2 = (String)parametros.getApplicationGroup().get(1);
        }
        if(parametros.getParametrosWhere().containsKey(parametros.getNameAdditionalField()))
        {
            if(parametros.getParametrosWhere().get(parametros.getNameAdditionalField()) != null)
                applicationGroup = applicationGroup2;
            else
                applicationGroup = applicationGroup1;
        } else
        {
            applicationGroup = applicationGroup1;
        }
        if(parametros.getNameAdditionalField() != null && !parametros.getNameAdditionalField().equals(""))
            cmd = cmd + "-G \"" + applicationGroup + "\" ";
        cmd = cmd + "-i \"WHERE ";
        Map where = parametros.getParametrosWhere();
        Iterator it = where.entrySet().iterator();
        boolean yaTieneUnaCondicion = false;
        while(it.hasNext()) 
        {
            java.util.Map.Entry e = (java.util.Map.Entry)it.next();
            if(parametros.getParametrosWhere().get(e.getKey()) != null)
                if(parametros.getExcludedFieldsInGet() != null)
                {
                    if(!parametros.getExcludedFieldsInGet().contains(e.getKey()))
                    {
                        if(yaTieneUnaCondicion)
                            cmd = cmd + " AND ";
                        cmd = cmd + e.getKey();
                        cmd = cmd + " = ";
                        cmd = cmd + GeneralUtils.preparaDato((String)e.getValue(), e, parametros);
                        yaTieneUnaCondicion = true;
                    }
                } else
                {
                    cmd = cmd + e.getKey();
                    cmd = cmd + " = ";
                    cmd = cmd + GeneralUtils.preparaDato((String)e.getValue(), e, parametros);
                    if(it.hasNext())
                        cmd = cmd + " AND ";
                }
        }
        cmd = cmd + "\"";
        cmd = cmd + " -L 1 -v -a";
        logger.debug("!--> exe get : " + cmd);
        try
        {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            ArsdocLog errorGobbler = new ArsdocLog(proc.getErrorStream(), "ERROR");
            ArsdocLog outputGobbler = new ArsdocLog(proc.getInputStream(), "OUTPUT");
            errorGobbler.start();
            outputGobbler.start();
            int exitVal = proc.waitFor();
            logger.debug("!--> exe exitVal : " + exitVal);
            if(exitVal == 0)
                logger.debug("!--> exe arsdoc : get terminado ok.");
        }
        catch(Exception ex)
        {
            logger.debug("!--> exe arsdoc : Error al ejecutar arsdoc get.");
            logger.debug("!--> exe arsdoc : " + ex.getMessage());
        }
        InputStream is = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileOutputStream fos = null;
        encoder = new BASE64Encoder();
        decoder = new BASE64Decoder();
        byte almacenamiento[] = new byte[4096];
        tipo = null;
        try
        {
            is = new BufferedInputStream(new FileInputStream(new File(directorioGet + parametros.getSesionID())));
            int bytesLeidos;
            for(int count = 0; (bytesLeidos = is.read(almacenamiento)) != -1; count++)
            {
                bos.write(almacenamiento, 0, bytesLeidos);
                if(count < 1)
                    tipo = new String(bos.toByteArray(), 0, 4);
            }

            bytes = bos.toByteArray();
        }
        finally
        {
            if(is != null)
                is.close();
            if(bos != null)
                bos.close();
            if(fos != null)
                fos.close();
        }
        ArchivoGetVO archivo = new ArchivoGetVO();
        archivo.setArchivo(decoder.decodeBuffer(encoder.encodeBuffer(bytes)));
        if(tipo.equalsIgnoreCase("%PDF"))
        {
            archivo.setContentType("application/pdf");
            archivo.setNombreArchivo("archivo.pdf");
        } else
        {
            archivo.setContentType("application/afp");
            archivo.setNombreArchivo("archivo.afp");
        }
        File f = new File(directorioGet + parametros.getSesionID());
        f.delete();
        return archivo;
    }

    private IndiceVO execDobleConsulta(String cmd, String applicationGroup1, String applicationGroup2, String archivo, ParametrosConsultaVO parametros)
        throws Exception
    {
        IndiceVO indice = new IndiceVO();
        CommandLine comando = null;
        String cmd1 = cmd;
        cmd1 = cmd1 + GeneralUtils.prepareOrderBy(parametros);
        cmd1 = cmd1 + " \" -G \"" + applicationGroup1 + "\" ";
        cmd1 = cmd1 + " -e \"" + delim + "\" -v -H";
        comando = new CommandLine(cmd1, archivo, directorioQuery, delim);
        IndiceVO indice1 = comando.ejecutarComando();
        IndiceVO indice2 = null;
        if(!indice1.isMaxHitsTrunco())
        {
            String cmd2 = GeneralUtils.reemplazaMaxHits(cmd, parametros.getMaxHits(), (parametros.getMaxHits() - (long)indice1.getFilas().length) + 1L);
            String newCmd = GeneralUtils.prepareComandoSqlAdditionalField(parametros, cmd2, applicationGroup2, delim, expLike);
            CommandLine comando2 = new CommandLine(newCmd, archivo, directorioQuery, delim);
            indice2 = comando2.ejecutarComando();
            indice.setMaxHitsTrunco(indice2.isMaxHitsTrunco());
        } else
        {
            indice.setMaxHitsTrunco(indice1.isMaxHitsTrunco());
        }
        if(indice1 != null && indice2 != null)
        {
            FilaVO header = null;
            header = indice2.getFilas()[0];
            FilaVO filas[] = GeneralUtils.mergeFilas(indice1, indice2);
            if(!parametros.getSortFieldOrder().isEmpty())
            {
                FilaVO filasFormateadas[] = GeneralUtils.formateaFilas(filas, parametros, true, header);
                OrderBy order = new OrderBy(header, filasFormateadas, parametros.getSortFieldOrder());
                FilaVO filasOrdenadas[] = order.order();
                FilaVO filasReFormateadas[] = GeneralUtils.formateaFilas(filasOrdenadas, parametros, false, header);
                ArrayList resultados = new ArrayList();
                resultados.add(header);
                for(int i = 0; i < filasReFormateadas.length; i++)
                    resultados.add(filasReFormateadas[i]);

                indice.setFilas((FilaVO[])resultados.toArray(new FilaVO[resultados.size()]));
            } else
            {
                indice.setFilas(filas);
            }
        } else
        if(indice1 != null)
            indice.setFilas(indice1.getFilas());
        else
        if(indice2 != null)
            indice.setFilas(indice2.getFilas());
        return indice;
    }



}

