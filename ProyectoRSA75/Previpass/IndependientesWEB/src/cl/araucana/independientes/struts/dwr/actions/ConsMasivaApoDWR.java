package cl.araucana.independientes.struts.dwr.actions;

import cl.araucana.independientes.impl.ConsMasivaApoImpl;
import cl.araucana.independientes.vo.ConsMasivaApoVO;

public class ConsMasivaApoDWR{

    public ConsMasivaApoVO consultaMasivaApo (String fechaIni, String fechaFin, String estado, String oficina, String user, String fecha){

        ConsMasivaApoImpl impl = new ConsMasivaApoImpl();		
        ConsMasivaApoVO ret = new ConsMasivaApoVO();

        try{		
            ret = impl.consultaMasivaApo(fechaIni, fechaFin, estado, oficina, user, fecha);
            return ret;

        }catch(Exception e){
            e.printStackTrace();
        }		
        return ret;
    }

}



