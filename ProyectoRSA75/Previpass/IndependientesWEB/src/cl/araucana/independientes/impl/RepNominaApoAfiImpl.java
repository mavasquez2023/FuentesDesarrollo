package cl.araucana.independientes.impl;

import java.io.IOException;
import java.sql.SQLException;
import cl.araucana.independientes.dao.RepNominaApoAfiDAO;
import cl.araucana.independientes.vo.EstadoSolAfiVO;
import cl.araucana.independientes.vo.LinRepNominaApoAfiResultVO;
import cl.araucana.independientes.vo.RepNominaApoAfiVO;

public class RepNominaApoAfiImpl {

    public RepNominaApoAfiVO consultaRepNominaApoAfi(int rut) throws IOException,SQLException{

        RepNominaApoAfiDAO dao = new RepNominaApoAfiDAO();	
        RepNominaApoAfiVO resp = new RepNominaApoAfiVO();		
        LinRepNominaApoAfiResultVO linRep = new LinRepNominaApoAfiResultVO();
        int[] arr = new int[3];

        arr[0] = 4;
        arr[1] = 3;
        arr[2] = 1;

        resp.setPaginaRepNominaApoAfi(arr);
        resp.setPaginaActualRepNominaApoAfi(2);
        try{
            linRep = dao.consultaRepNominaApoAfi(rut);
            resp.setNombreAfiliado(linRep.getNombreAfiliado());
            resp.setApellidoPaternoAfiliado(linRep.getApellidoPaternoAfiliado());
            resp.setApellidoMaternoAfiliado(linRep.getApellidoMaternoAfiliado());
            resp.setEstadoAfiliado(linRep.getEstadoAfiliado());
            resp.setOficinaAfiliacion(linRep.getOficinaAfiliacion());
            resp.setRepNominaApoAfi(linRep.getRepNominaApoAfi());

            return resp;
        }catch (Exception e) {
            e.printStackTrace();
            return resp;
        }		
    }

    public EstadoSolAfiVO consultaRepNominaApoAfiEstados(int rut) {
        RepNominaApoAfiDAO dao = new RepNominaApoAfiDAO();	
        return dao.consultaRepNominaApoAfiEstados(rut);
    }



}
