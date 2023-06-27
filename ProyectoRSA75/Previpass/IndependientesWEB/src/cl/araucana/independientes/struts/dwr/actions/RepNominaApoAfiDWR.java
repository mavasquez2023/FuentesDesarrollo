package cl.araucana.independientes.struts.dwr.actions;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.independientes.dao.RepNominaApoAfiDAO;
import cl.araucana.independientes.impl.RepNominaApoAfiImpl;
import cl.araucana.independientes.vo.EstadoSolAfiVO;
import cl.araucana.independientes.vo.RepNominaApoAfiVO;

public class RepNominaApoAfiDWR{

    public RepNominaApoAfiVO consultaRepNominaApoAfi (int rut){

        RepNominaApoAfiImpl impl = new RepNominaApoAfiImpl();		
        RepNominaApoAfiVO ret = new RepNominaApoAfiVO();

        try{

            ret = impl.consultaRepNominaApoAfi(rut);
            return ret;

        }catch(IOException e){
            e.printStackTrace();
        }catch(SQLException f){
            f.printStackTrace();
        }

        return ret;
    }

    public EstadoSolAfiVO consultaRepNominaApoAfiEstados(int rut) {
        RepNominaApoAfiImpl impl = new RepNominaApoAfiImpl();		
        return impl.consultaRepNominaApoAfiEstados(rut);
    }

}



