package cl.araucana.independientes.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cl.araucana.independientes.config.ConfiguracionSqlMap;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.vo.EstadoSolAfiVO;
import cl.araucana.independientes.vo.LinRepNominaApoAfiResultVO;
import cl.araucana.independientes.vo.LinRepNominaApoAfiVO;
import cl.araucana.independientes.vo.LinRepNominaApoAfiVO2;
import cl.araucana.independientes.vo.PersonaVO;
import cl.araucana.independientes.vo.SolicitudNegocioVO;
import cl.araucana.independientes.vo.SolicitudVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;


import com.ibatis.sqlmap.client.SqlMapClient;

public class RepNominaApoAfiDAO {
    public LinRepNominaApoAfiResultVO consultaRepNominaApoAfi(int rut) 
    {
        /**
         * IMPLEMENTA LA BUSQUEDA DEL RUT PARA RETORNAR
         * DATOS DEL APORTE ASOCIADO AL AFILIADO
         * INDEPENDIENTE, SE IMPLEMENTA LOGICA PARA CARGAR 
         * VARIABLE DE MONTO PENDIENTE
         */
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        HashMap paramMap = new HashMap();
        List datos = null;
        LinRepNominaApoAfiResultVO afiResult = new LinRepNominaApoAfiResultVO();
        LinRepNominaApoAfiVO[] result = null;
        int montoPendiente = 0;
        int montoPendienteSuma = 0;
        String nombreAfiliado = new String();
        String apellidoPaternoAfiliado = new String();
        String apellidoMaternoAfiliado = new String();
        String estadoAfiliado = new String();
        String oficinaAfiliacion = new String();
        ListadoParametros listParametros = ListadoParametros.getInstancia();
        Parametro[] param1 = listParametros.getListTipoPagoAporte();
        //RAC-19887 JLGN 16-05-2013
        boolean flagPendiente = true;

        try {
            paramMap.put("P_RUT", new Integer(rut));
            paramMap.put("P_NOMBRES", new String());
            paramMap.put("P_APELLIDOPATERNO", new String());
            paramMap.put("P_APELLIDOMATERNO", new String());
            paramMap.put("P_ESTADO", new String());
            paramMap.put("P_OFICINAAFILIACION", new String());

            System.out.println("antes de llamar a SP_CONSAPTPREV");
            datos = sqlMap.queryForList("RepNominaApoAfi.SP_CONSAPTPREV",paramMap);
            System.out.println("despues de llamar a SP_CONSAPTPREV");

            nombreAfiliado = (String)paramMap.get("P_NOMBRES");
            apellidoPaternoAfiliado = (String)paramMap.get("P_APELLIDOPATERNO");
            apellidoMaternoAfiliado = (String)paramMap.get("P_APELLIDOMATERNO");		    
            estadoAfiliado = (String)paramMap.get("P_ESTADO");
            oficinaAfiliacion = (String)paramMap.get("P_OFICINAAFILIACION");

            afiResult.setNombreAfiliado(nombreAfiliado);
            afiResult.setApellidoPaternoAfiliado(apellidoPaternoAfiliado);
            afiResult.setApellidoMaternoAfiliado(apellidoMaternoAfiliado);
            afiResult.setEstadoAfiliado(estadoAfiliado);
            afiResult.setOficinaAfiliacion(oficinaAfiliacion);

            result = (LinRepNominaApoAfiVO[])datos.toArray(new LinRepNominaApoAfiVO[datos.size()]);
            int datosSize = 0;
            // se obtiene valor de arreglo con solo los aporte
            for(int i = 0; i < result.length ; i++)
            {		    	
                LinRepNominaApoAfiVO lineaTemp = result[i];		
                if (lineaTemp.getCodigoEventoContable()!= 4)
                {
                    datosSize++;
                }
            }
            LinRepNominaApoAfiVO2[] result2 = new LinRepNominaApoAfiVO2[datosSize];

            int j = 0;
            for(int i = 0; i < result.length ; i++)
            {		    	
                LinRepNominaApoAfiVO lineaTemp = result[i];		   
                LinRepNominaApoAfiVO lineaTemp2;	
                // si no es un pago
                if (lineaTemp.getCodigoEventoContable()!= 4)
                {
                    String mesAporte =  Integer.toString(lineaTemp.getMesAporte());
                    String fechaVigencia = Integer.toString(lineaTemp.getFechaVigencia());
                    String fechaPago = Integer.toString(lineaTemp.getFechaPago());
                    String tipoPago = lineaTemp.getTipoPago();
                    String valorPago = lineaTemp.getValorPago();
                    LinRepNominaApoAfiVO2 lineaTempResult = new LinRepNominaApoAfiVO2();
                    int monto = 0;
                    // carga campos con formatos
                    if (mesAporte != "0"){
                        lineaTempResult.setMesAporte(mesAporte.substring(0, 4)+"/"+ mesAporte.substring(4,6));
                    }
                    else{
                        lineaTempResult.setMesAporte("");
                    }
                    if (fechaVigencia != "0"){
                        lineaTempResult.setFechaVigencia(fechaVigencia.substring(6, 8)+"/" + fechaVigencia.substring(4,6)+"/"+fechaVigencia.substring(0,4));
                    }
                    else{
                        lineaTempResult.setFechaVigencia("");
                    }
                    if (fechaPago != "0"){
                        lineaTempResult.setFechaPago(fechaPago.substring(6, 8)+"/" + fechaPago.substring(4,6)+"/"+fechaPago.substring(0,4));
                    }
                    else{
                        lineaTempResult.setFechaPago("");
                    }

                    if(lineaTemp.getFormaPago()!= null){
                        lineaTempResult.setFormaPago(lineaTemp.getFormaPago());
                    }
                    else{
                        lineaTempResult.setFormaPago("");
                    }

                    lineaTempResult.setMontoAporte(Helper.separadorDeMiles(String.valueOf(lineaTemp.getMonto())));
                    lineaTempResult.setEstado(lineaTemp.getEstado());
                    // si posee solo un registro el monto pago es 0
                    if (result.length == 1){	
                        monto = 0;
                        // si el estado es distinto a Cancelado
                        if (lineaTemp.getCodigoEstado()!= 1)
                        {
                            montoPendiente =  montoPendiente + lineaTemp.getMonto();
                        }
                        // si es cancelado
                        else{
                            montoPendiente = 0;
                        }
                    }
                    // si el registro no es el ultimo registros
                    else if (i < result.length -1){
                        lineaTemp2 = result[i+1];	
                        //si la fecha del pago es igual a la fecha del registro siguiente y el registro siguiente no es un pago
                        if (lineaTemp.getFechaPago() == lineaTemp2.getFechaPago()&& lineaTemp2.getCodigoEventoContable()!= 4){
                            if (lineaTemp.getCodigoEstado()!= 1){
                                montoPendiente = montoPendiente + lineaTemp.getMonto();
                            }
                            monto = 0;
                        }
                        //si la fecha del pago es igual a la fecha del registro siguiente y el registro siguiente es un pago
                        else if (lineaTemp.getFechaPago() == lineaTemp2.getFechaPago() && lineaTemp2.getCodigoEventoContable()== 4){
                            //Inicio RAC-19887 JLGN 16-05-2013
                            //montoPendiente = 0;
                            monto = lineaTemp.getMonto();
                            flagPendiente = false;
                            lineaTempResult.setMontoPago(Helper.separadorDeMiles(String.valueOf(monto)));
                            lineaTempResult.setMontoPendiente(Helper.separadorDeMiles(String.valueOf(0)));
                            //Fin RAC-19887 JLGN 16-05-2013
                        }
                        //si la fecha de pago es distinta a la fecha del registro siguiente
                        else {
                            //montoPendiente = 0;
                            // si el estado del siguiente registro no es cancelado
                            if (lineaTemp2.getCodigoEstado()!= 1 && montoPendienteSuma == 0){
                                montoPendiente =  montoPendiente + lineaTemp.getMonto();
                                if (lineaTemp2.getCodigoEstado()== 5){
                                    montoPendienteSuma = montoPendiente;
                                }
                            }	
                            else if (lineaTemp2.getCodigoEstado()!= 1 && montoPendienteSuma != 0){				    			
                                montoPendiente =  montoPendienteSuma + lineaTemp.getMonto();
                                montoPendienteSuma = 0;
                            }
                            else{				    			
                                montoPendiente = montoPendiente + lineaTemp.getMonto();
                            }				    		
                            monto = 0;
                        }
                    }
                    //si es el ultimo registro el monto de pago es 0
                    else if (i == result.length -1){
                        monto = 0;
                        // si el estado es distinto a Cancelado
                        //System.out.println("montoPendienteSuma: "+ montoPendienteSuma);
                        //System.out.println("montoPendiente: "+ montoPendiente);

                        if (lineaTemp.getCodigoEstado()!= 1)
                        {
                            //Inicio RAC-19887 JLGN 16-05-2013
                            /*if (montoPendienteSuma == 0){
			    				montoPendiente =  montoPendiente + lineaTemp.getMonto();
			    			}
			    			else{
			    				montoPendiente =  montoPendienteSuma + lineaTemp.getMonto();
			    			}*/

                            if (montoPendiente != 0){
                                montoPendiente =  montoPendiente + lineaTemp.getMonto();
                            }
                            else{
                                montoPendiente =  montoPendienteSuma + lineaTemp.getMonto();
                            }
                            //Fin RAC-19887 JLGN 16-05-2013
                        }
                        // si es cancelado
                        else{
                            montoPendiente = 0;
                        }
                    }	

                    //Inicio RAC-19887 JLGN 16-05-2013
                    if(flagPendiente){
                        lineaTempResult.setMontoPago(Helper.separadorDeMiles(String.valueOf(monto)));
                        lineaTempResult.setMontoPendiente(Helper.separadorDeMiles(String.valueOf(montoPendiente)));
                    }		
                    flagPendiente = true;
                    //Fin RAC-19887 JLGN 16-05-2013

                    if (tipoPago == null || tipoPago == ""){
                        lineaTempResult.setTipoPago("");
                    }
                    else{			
                        int codigo = Integer.parseInt(tipoPago);		    	
                        tipoPago = Helper.obtenerDescripcion(param1, codigo);
                        lineaTempResult.setTipoPago(tipoPago);
                    }

                    if (valorPago == null || valorPago == ""){
                        lineaTempResult.setValorPago("");
                    }
                    else{						
                        valorPago = Helper.separadorDeMiles(valorPago);
                        lineaTempResult.setValorPago(valorPago);
                    }
                    result2[j] = lineaTempResult;		  
                    j++;
                }
            }

            afiResult.setRepNominaApoAfi(result2);
            return afiResult;

        }   
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace(); }

        }
        return afiResult;
    }

    public EstadoSolAfiVO consultaRepNominaApoAfiEstados(int rut) 
    {
        EstadoSolAfiVO resp = new EstadoSolAfiVO();
        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "MM/dd/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista

        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;

        try {
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", String.valueOf(rut));
            datos = sqlMap.queryForList("RepNominaApoAfi.obtenerEstSolAfi",parametros);

            if(datos != null && datos.size() >0 ){

                resp = (EstadoSolAfiVO)datos.get(0);

                fecha = resp.getFechaVigencia();
                date = sdf1.parse(fecha);

                resp.setFechaVigencia(sdf2.format(date));
                //System.out.println("fechaaaaaaaa"+resp.getFechaVigencia());
                //System.out.println("tipo solicitud"+resp.getTipoSolicitud());
                //System.out.println("tipo estado solicitud"+resp.getTipoEstadoSolicitud());
                //System.out.println("tipo estado afiliado"+resp.getTipoEstadoAfiliado());

            }else{
                return resp;
            }

        }   
        catch (SQLException e) {

            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace(); }

        }

        return resp;
    }

}
