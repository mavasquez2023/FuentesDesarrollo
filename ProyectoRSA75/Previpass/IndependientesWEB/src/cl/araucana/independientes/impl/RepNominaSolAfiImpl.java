package cl.araucana.independientes.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.araucana.independientes.dao.RepNominaSolAfiDAO;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.vo.LinRepNominaSolAfiVO;
import cl.araucana.independientes.vo.RepNominaSolAfiVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

public class RepNominaSolAfiImpl {

    public String obtenerExcel(String archivo, LinRepNominaSolAfiVO[] lineas, String fechaIni, String fechaFin, String oficina, String estado, String user) throws IOException,SQLException{

        String resultado = "";
        String glosaOficina = "";
        String glosaEstado = "";

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        Parametro[] oficinas = listaParam.getListOficina();
        Parametro[] estados = listaParam.getListEstadoSolicitudAfiliacion();

        if(oficina.equals("0")){
            glosaOficina = "Todas";
        }else{
            glosaOficina = Helper.obtenerDescripcion(oficinas, Integer.parseInt(oficina));
        }

        if(estado.equals("0")){
            glosaEstado = "Todos";
        }else{
            glosaEstado = Helper.obtenerDescripcion(estados, Integer.parseInt(estado));
        }

        FileWriter fw = new FileWriter(archivo);
        String tab ="\t";
        String nl  ="\n";
        StringBuffer linea = new StringBuffer("");

        linea.append("Reporte N�mina de Solicitudes de Afiliaci�n");
        linea.append(nl);
        linea.append(nl);
        linea.append(" Par�metros de B�squeda :");
        linea.append(nl);

        linea.append("Fecha Inicio : ");linea.append(tab);linea.append(fechaIni);linea.append(nl);
        linea.append("Fecha Fin : ");linea.append(tab);linea.append(fechaFin);linea.append(nl);
        linea.append("Oficina : ");linea.append(tab);linea.append(glosaOficina);linea.append(nl);
        linea.append("Estado : ");linea.append(tab);linea.append(glosaEstado);linea.append(nl);

        linea.append(nl);

        linea.append("Fecha Ingreso");   linea.append(tab);
        linea.append("Folio");           linea.append(tab);
        linea.append("RUT");             linea.append(tab);
        linea.append("Apellido Paterno");linea.append(tab);
        linea.append("Apellido Materno");linea.append(tab);
        linea.append("Nombres");         linea.append(tab);
        linea.append("Vendedor");        linea.append(tab);
        linea.append("Lugar Afiliaci�n");linea.append(tab);
        linea.append("Estado Solicitud");linea.append(tab);
        linea.append("Renta Imponible"); linea.append(nl);

        fw.write(linea.toString());

        for(int i = 0; i < lineas.length; i++){
            linea = new StringBuffer("");

            LinRepNominaSolAfiVO reg = new LinRepNominaSolAfiVO();
            reg = lineas[i];

            linea.append(reg.getFechaIngreso());   linea.append(tab);
            linea.append(reg.getFolio());          linea.append(tab);
            linea.append(reg.getRut());            linea.append(tab);
            linea.append(reg.getApellidoPaterno());linea.append(tab);
            linea.append(reg.getApellidoMaterno());linea.append(tab);
            linea.append(reg.getNombres());        linea.append(tab);
            linea.append(reg.getCodigoVendedor()); linea.append(tab);
            linea.append(reg.getLugarAfiliacion());linea.append(tab);
            linea.append(reg.getEstadoSolicitud());linea.append(tab);
            linea.append(reg.getRentaImponible()); linea.append(nl);

            fw.write(linea.toString());
        }

        linea = new StringBuffer("");

        fw.close();

        return resultado;
    }

    public RepNominaSolAfiVO consultaRepNominaSolAfi(String fechaIni, String fechaFin, String oficina, String estado, String user, String fechaActual) throws IOException,SQLException{

        RepNominaSolAfiDAO dao = new RepNominaSolAfiDAO();

        RepNominaSolAfiVO resp = new RepNominaSolAfiVO();

        //int[] arr = new int[3];

        //arr[0] = 4;
        //arr[1] = 3;
        //arr[2] = 1;

        //resp.setPaginaRepNominaSolAfi(arr);
        resp.setPaginaActualRepNominaSolAfi(2);
        resp.setLisRepNominaSolAfi(dao.consultaRepNominaSolAfi(fechaIni, fechaFin, oficina, estado));

        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "dd/MM/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "yyyyMMdd"; // El que se envia a la Vista
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        try{

            date = sdf1.parse(fechaActual);
            fecha = sdf2.format(date);

        }catch (ParseException e) {
            e.printStackTrace();
        }

        // Generacion de Archivo
        try{

            File f = new File(""); // Creamos un objeto file

            RepNominaSolAfiImpl impl = new RepNominaSolAfiImpl();

            StringBuffer excelFile = new StringBuffer("");
            excelFile.append(f.getAbsolutePath());
            excelFile.append(IND_Constants.DIR_INF);
            excelFile.append(user);
            excelFile.append("_");
            excelFile.append(fecha);
            excelFile.append(IND_Constants.SUF_INF_RepNominaSolAfi);
            excelFile.append(IND_Constants.EXT_Excel);

            impl.obtenerExcel(excelFile.toString(), resp.getLisRepNominaSolAfi(), fechaIni, fechaFin, oficina, estado, user);
            resp.setArchivoInforme(excelFile.toString());

        }catch(SQLException e){
            e.printStackTrace();
        }catch(IOException f){
            f.printStackTrace();
        }

        return resp;
    }
}
