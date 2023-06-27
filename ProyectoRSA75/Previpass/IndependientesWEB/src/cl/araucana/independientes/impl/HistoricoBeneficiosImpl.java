package cl.araucana.independientes.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.araucana.independientes.dao.HistoricoBeneficiosDAO;
import cl.araucana.independientes.dao.SolBeneficiosDAO;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.vo.AfiliadoBeneficiosVO;
import cl.araucana.independientes.vo.BeneficioVO;
import cl.araucana.independientes.vo.HistoricoBeneficiosVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Retorno;

public class HistoricoBeneficiosImpl {

    public static HistoricoBeneficiosVO consultaHistoricoBeneficios(String rut) throws IOException,SQLException
    {
        ListadoParametros listaParam = ListadoParametros.getInstancia();

        AfiliadoBeneficiosVO afi = new AfiliadoBeneficiosVO();
        HistoricoBeneficiosVO resp = new HistoricoBeneficiosVO();
        BeneficioVO[] listadoBeneficios = new BeneficioVO[0];
        int cantOtorgados = 0;
        long sumaMontosOtorgados = 0;

        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "MM/dd/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        afi = SolBeneficiosDAO.obtenerDatosAfiliado(rut);
        resp.setIdPersonaAfiliado(afi.getIdDocumentoPersona());

        resp.setNombreAfiliado(afi.getNombres() + " " + afi.getApellidoPaterno() + " " + afi.getApellidoMaterno());
        resp.setEstadoAfiliado(Helper.obtenerDescripcion(listaParam.getListEstadoAfiliado(), afi.getTipoEstadoAfiliado()));

        listadoBeneficios = HistoricoBeneficiosDAO.consultaHistoricoBeneficios(rut);

        for(int i = 0; i < listadoBeneficios.length; i++){

            BeneficioVO beneficio = new BeneficioVO();

            beneficio = listadoBeneficios[i];

            try{

                fecha = beneficio.getStrFechaSolicitud();
                date = sdf1.parse(fecha);

                beneficio.setStrFechaSolicitud(sdf2.format(date));

            }catch (ParseException e) {
                e.printStackTrace();
            }

            beneficio.setGlosaCortaBeneficio(Helper.obtenerDatoBeneficio(beneficio.getIdbeneficio(), 2));
            beneficio.setStrRutCausante(Helper.separadorDeMiles(""+beneficio.getRutCausante()) + "-" + Helper.digitoVerificadorRut(""+beneficio.getRutCausante()));
            beneficio.setStrMontoPagar("$" + Helper.separadorDeMiles(""+beneficio.getMontoPagar()));
            beneficio.setGlosaEstado(Helper.obtenerDescripcion(listaParam.getListEstadoBeneficio(), beneficio.getEstado()));
            beneficio.setStrIdAnalista(Helper.separadorDeMiles(""+ beneficio.getIdAnalista())+"-"+Helper.digitoVerificadorRut(""+beneficio.getIdAnalista()));

            if(beneficio.getEstado() == 3 && beneficio.getTipoComprobante() == 1){

                cantOtorgados++;
                sumaMontosOtorgados = sumaMontosOtorgados + beneficio.getMontoPagar();
            }
        }

        resp.setCantReembolsos(Helper.separadorDeMiles(Integer.toString(cantOtorgados)));
        resp.setMontoReembolsado("$" + Helper.separadorDeMiles(Long.toString(sumaMontosOtorgados)));
        resp.setListaBeneficios(listadoBeneficios);

        return resp;
    }

    public static Retorno anularBeneficio(long folio){

        Retorno ret = HistoricoBeneficiosDAO.anularBeneficioCobol(folio);

        if(ret.getCodError() == 0){

            ret = HistoricoBeneficiosDAO.anularBeneficio(folio);
        }

        return ret;
    }

    public static Retorno reversarBeneficio(long folio)
    {
        Retorno ret = HistoricoBeneficiosDAO.reversarBeneficioCobol(folio);
        /* AL REVERSAR COMPROBANTE, EL COMPROBANTE INICIAL DEBE MANTENER ESTADO.
		if(ret.getCodError() == 0){

			ret = HistoricoBeneficiosDAO.reversarBeneficio(folio);
		}
         */
        return ret;
    }

    //Inicio REQ 6988 JLGN 11-02-2013
    public static Retorno actualizarBeneficio(String rut){		
        BeneficioVO[] listadoBeneficios = new BeneficioVO[0];
        Retorno ret = new Retorno();
        ret.setCodError(0);
        ret.setDesError("OK");

        listadoBeneficios = HistoricoBeneficiosDAO.consultaBeneficiosTesoreria(rut);

        //Recorro Los Folios del Afiliado que cambian de Estado		
        for(int i = 0; i < listadoBeneficios.length; i++){

            BeneficioVO beneficio = new BeneficioVO();			
            beneficio = listadoBeneficios[i];

            try{
                //Valido la forma de pago
                //Solo se actualiza cuando forma de pago es Efectivo
                if("E".equals(beneficio.getFormaPago().trim())){
                    //Valido El Estado del Beneficio
                    //Cuando Se anula un pago en TESORERIA (regresa a estado Impreso), no se ve reflejado el cambio de estado en STI
                    if("I".equals(beneficio.getGlosaEstado().trim())){
                        ret = HistoricoBeneficiosDAO.anularPagoBeneficio(beneficio.getFolio());
                    }else if("A".equals(beneficio.getGlosaEstado().trim())){
                        ret = HistoricoBeneficiosDAO.anularBeneficio(beneficio.getFolio());
                    }

                    if(ret.getCodError() != 0){
                        return ret;
                    } 
                }

            }catch (Exception e) {
                e.printStackTrace();
            }			
        }
        return ret;
    }
    //Fin REQ 6988 JLGN 11-02-2013
}
