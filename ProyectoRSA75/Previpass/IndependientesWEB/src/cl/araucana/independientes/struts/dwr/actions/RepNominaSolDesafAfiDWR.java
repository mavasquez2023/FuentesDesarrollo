package cl.araucana.independientes.struts.dwr.actions;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.independientes.helper.EnviaMailUtil;
import cl.araucana.independientes.impl.RepNominaSolDesafAfiImpl;
import cl.araucana.independientes.vo.RepNominaSolDesafAfiVO;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

public class RepNominaSolDesafAfiDWR{

    public RepNominaSolDesafAfiVO consultaRepNominaSolDesafAfi (String fechaIni, String fechaFin, String oficina, String estado, String user, String fechaActual){

        RepNominaSolDesafAfiImpl impl = new RepNominaSolDesafAfiImpl();

        RepNominaSolDesafAfiVO ret = new RepNominaSolDesafAfiVO();

        try{

            ret = impl.consultaRepNominaSolDesafAfi(fechaIni, fechaFin, oficina, estado, user, fechaActual);

            return ret;

        }catch(IOException e){

        }catch(SQLException f){

        }

        return ret;
    }

    public String enviarMail(String archivoEntrada, String user, String fechaIni, String fechaFin, String oficina){

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        Parametro[] oficinas = listaParam.getListOficina();

        String txtOficina = "";

        if(oficina.equals("0")){
            txtOficina = "Todas";
        }else{
            txtOficina = Helper.obtenerDescripcion(oficinas, Integer.parseInt(oficina));
        }	

        txtOficina = txtOficina.trim(); 

        ListadoParametros listaParamMail = ListadoParametros.getInstancia();
        Parametro[] mailToNomina = listaParamMail.getListMailToNominaSolicitudes();

        String mailTokens = new String();
        mailTokens = "";
        for(int i=0; i<mailToNomina.length; i++){
            if (mailToNomina[i].getEstado() == 1){
                mailTokens = mailTokens + mailToNomina[i].getGlosa() + "#";
            }
        }

        String subject = "Oficina: " + txtOficina + " - Usuario: " + user;
        String cuerpo = "";

        if(!archivoEntrada.equals("")){//Esta validacion es para señalar que no hubo solicitudes

            cuerpo = "Estimados: \n\nAdjunta sigue la nómina de solicitudes de desafiliación ";

            if (fechaIni.equals(fechaFin)){
                if(oficina.equals("0")){
                    cuerpo = cuerpo + "con fecha " + fechaIni + ", correspondiente a todas las oficinas del país.\n\nAtentamente,\n" + user;
                }else{
                    cuerpo = cuerpo + "con fecha " + fechaIni + ", correspondiente a la oficina " + txtOficina + ".\n\nAtentamente,\n" + user;
                }	
            }else{
                if(oficina.equals("0")){
                    cuerpo = cuerpo + "para el periodo " + fechaIni + " - " + fechaFin + ", correspondiente a todas las oficinas del país.\n\nAtentamente,\n" + user;
                }else{
                    cuerpo = cuerpo + "para el periodo " + fechaIni + " - " + fechaFin + ", correspondiente a la oficina " + txtOficina + ".\n\nAtentamente,\n" + user;
                }	
            }
        }else{

            cuerpo = "Estimados: \n\n";

            if (fechaIni.equals(fechaFin)){
                if(oficina.equals("0")){
                    cuerpo = cuerpo + "El día " + fechaIni + " no hubo ingreso de solicitudes de afiliación, por lo cual no se adjunta nómina correspondiente a todas las oficinas del país.\n\nAtentamente,\n" + user;
                }else{
                    cuerpo = cuerpo + "El día " + fechaIni + " no hubo ingreso de solicitudes de afiliación, por lo cual no se adjunta nómina correspondiente a la oficina " + txtOficina + ".\n\nAtentamente,\n" + user; 
                }	
            }else{

                if(oficina.equals("0")){
                    cuerpo = cuerpo + "Para los días " + fechaIni + " - " + fechaFin + " no hubo ingreso de solicitudes de afiliación, por lo cual no se adjunta nómina correspondiente a todas las oficinas del país.\n\nAtentamente,\n" + user;
                }else{
                    cuerpo = cuerpo + "Para los días " + fechaIni + " - " + fechaFin + " no hubo ingreso de solicitudes de afiliación, por lo cual no se adjunta nómina correspondiente a la oficina " + txtOficina + ".\n\nAtentamente,\n" + user;
                }	
            }
        }

        String ruta = "";
        String archivo = "";
        int flag = 0;
        if(!archivoEntrada.equals(""))//Esta validacion es para señalar que no hubo solicitudes por lo que no se envia archivo
        {
            ruta = archivoEntrada;
            archivo = archivoEntrada.substring(archivoEntrada.lastIndexOf("\\") + 1, archivoEntrada.length());
        }

        EnviaMailUtil.EnviarMail(mailTokens, subject, cuerpo, ruta, archivo, flag);
        return "OK";
    }

}



