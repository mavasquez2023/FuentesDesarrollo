package cl.araucana.independientes.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.araucana.independientes.dao.ConsMasivaApoDAO;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.vo.ConsMasivaApoVO;
import cl.araucana.independientes.vo.LinConsMasivaApoVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;


public class ConsMasivaApoImpl {

    public String obtenerExcel(String archivo, LinConsMasivaApoVO[] lineas, String fechaIni, String fechaFin, String oficina, String estado, String user) throws IOException,SQLException{

        String resultado = "";
        String glosaOficina = "";
        String glosaEstado = "";

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        Parametro[] oficinas = listaParam.getListOficina();
        Parametro[] estados = listaParam.getListEstadoAfiliado();

        if(oficina.equals("0")){
            glosaOficina = "Todas";
        }else{
            glosaOficina = Helper.obtenerDescripcion(oficinas, Integer.parseInt(oficina));
        }

        if(estado.equals("-1")){
            glosaEstado = "Todos";
        }else{
            glosaEstado = Helper.obtenerDescripcion(estados, Integer.parseInt(estado));
        }

        FileWriter fw = new FileWriter(archivo);
        String tab ="\t";
        String nl  ="\n";
        StringBuffer linea = new StringBuffer("");

        linea.append("Reporte Nómina de Aportes de Afiliado");
        linea.append(nl);
        linea.append(nl);
        linea.append(" Parámetros de Búsqueda :");
        linea.append(nl);

        linea.append("Mes Aporte Desde : ");linea.append(tab);linea.append(fechaIni);linea.append(nl);
        linea.append("Mes Aporte Hasta : ");linea.append(tab);linea.append(fechaFin);linea.append(nl);
        linea.append("Estado Aporte : ");linea.append(tab);linea.append(glosaEstado);linea.append(nl);
        linea.append("Oficina : ");linea.append(tab);linea.append(glosaOficina);linea.append(nl);


        linea.append(nl);

        linea.append("RUT");             linea.append(tab);
        linea.append("Apellido Paterno");linea.append(tab);
        linea.append("Apellido Materno");linea.append(tab);
        linea.append("Nombres");         linea.append(tab);
        linea.append("Estado Afiliado"); linea.append(tab);
        linea.append("Oficina Afiliado");linea.append(tab);
        linea.append("Mes Aporte");      linea.append(tab);
        linea.append("Fecha Vigencia");  linea.append(tab);
        linea.append("Fecha Pago");      linea.append(tab);
        linea.append("Forma Pago");      linea.append(tab);
        linea.append("Tipo Pago");       linea.append(tab);
        linea.append("Valor Pago");      linea.append(tab);
        linea.append("Monto Aporte");    linea.append(tab);
        linea.append("Estado");          linea.append(nl);

        fw.write(linea.toString());

        for(int i = 0; i < lineas.length; i++){
            linea = new StringBuffer("");

            LinConsMasivaApoVO reg = new LinConsMasivaApoVO();
            reg = lineas[i];

            linea.append(reg.getRut());                     linea.append(tab);
            linea.append(reg.getApellidoPaternoAfiliado()); linea.append(tab);
            linea.append(reg.getApellidoMaternoAfiliado()); linea.append(tab);
            linea.append(reg.getNombreAfiliado());          linea.append(tab);
            linea.append(reg.getEstadoAfiliado());          linea.append(tab);
            linea.append(reg.getOficinaAfiliacion());       linea.append(tab);
            linea.append(reg.getMesAporte());               linea.append(tab);
            linea.append(reg.getFechaVigencia());           linea.append(tab);
            linea.append(reg.getFechaPago());               linea.append(tab);
            linea.append(reg.getFormaPago());               linea.append(tab);
            linea.append(reg.getTipoPago());                linea.append(tab);
            linea.append(reg.getValorPago());               linea.append(tab);
            linea.append(reg.getMontoAporte());             linea.append(tab);
            linea.append(reg.getEstadoAporte());            linea.append(nl);

            fw.write(linea.toString());
        }

        linea = new StringBuffer("");

        fw.close();

        return resultado;
    }

    public ConsMasivaApoVO consultaMasivaApo(String fechaIni, String fechaFin, String estado, String oficina, String user, String fechaActual)throws IOException,SQLException
    {
        ConsMasivaApoDAO dao = new ConsMasivaApoDAO();
        ConsMasivaApoVO resp = new ConsMasivaApoVO();

        resp.setPaginaActualConsModMasivaSol(2);
        try{
            resp.setLisConsMasivaApoVO(dao.consultaMasivaApo(fechaIni, fechaFin, estado, oficina));			
        }catch (Exception e) {
            e.printStackTrace();
            return resp;
        }

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

            ConsMasivaApoImpl impl = new ConsMasivaApoImpl();

            StringBuffer excelFile = new StringBuffer("");
            excelFile.append(f.getAbsolutePath());
            excelFile.append(IND_Constants.DIR_INF);
            excelFile.append(user);
            excelFile.append("_");
            excelFile.append(fecha);
            excelFile.append(IND_Constants.SUF_INF_RepNominaSolAfi);
            excelFile.append(IND_Constants.EXT_Excel);

            impl.obtenerExcel(excelFile.toString(), resp.getLisConsMasivaApoVO(), fechaIni, fechaFin, oficina, estado, user);
            resp.setArchivoInforme(excelFile.toString());

        }catch(SQLException e){
            e.printStackTrace();
        }catch(IOException f){
            f.printStackTrace();
        }
        return resp;
    }
}
