package cl.araucana.sivegam.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


import cl.araucana.sivegam.dao.GenerarReportesAFCDAO;
import cl.araucana.sivegam.dao.GenerarReportesCesantiaDAO;
import cl.araucana.sivegam.vo.CodigoErrorVO;
import cl.araucana.sivegam.vo.DominioVO;
import cl.araucana.sivegam.vo.LinAfcAFF01EVO;
import cl.araucana.sivegam.vo.LinAfcAFF01VO;
import cl.araucana.sivegam.vo.LinCesantia041VO;
import cl.araucana.sivegam.vo.LinCesantia042VO;
import cl.araucana.sivegam.vo.LinCesantia043VO;
import cl.araucana.sivegam.vo.LinCesantia044VO;
import cl.araucana.sivegam.vo.LinCesantiaError041VO;
import cl.araucana.sivegam.vo.LinCesantiaError042VO;
import cl.araucana.sivegam.vo.LinCesantiaError043VO;
import cl.araucana.sivegam.vo.LinCesantiaError044VO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class VOFillerDivPrevisional {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public static LinCesantia041VO llenarLinCesatia041VO(int id, List lista, String periodo) {
        LinCesantia041VO vo = new LinCesantia041VO();

        try {
            for (int i = 0; i < lista.size(); i++) {
                switch (i) {
                case 0:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    if (lista.get(i).equals(periodo)) {
                        vo.setMes_if(Long.parseLong(lista.get(i).toString().trim()));
                    } else {
                        vo.setMes_if(Long.parseLong(periodo));
                        insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), periodo, i);
                    }
                    break;
                case 1:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setCodigo_entidad(Integer.parseInt(lista.get(i).toString().trim()));
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    break;
                case 2:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setCodigo_archivo(Integer.parseInt(lista.get(i).toString().trim()));
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "41", i);
                    break;
                case 3:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setMes_emision(Long.parseLong(lista.get(i).toString().trim()));
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    break;
                case 4:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setTipo_egreso(Integer.parseInt(lista.get(i).toString().trim()));
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    break;
                case 5:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setRut_beneficiario(Long.parseLong(lista.get(i).toString().trim()));
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    break;
                case 6:
//                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setDv_beneficiario(lista.get(i).toString().trim());
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    break;
                case 7:
                    vo.setNombre_beneficiario(lista.get(i).toString().trim());
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    break;
                case 9:
                    String comunaTmp = lista.get(i).toString().trim();
                    if ("".equals(comunaTmp)) {
                        comunaTmp = "0";
                    }
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), comunaTmp, "", i);
                    vo.setComuna(Integer.parseInt(comunaTmp));
                    break;
                case 10:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    vo.setFecha_cesantia(Long.parseLong(lista.get(i).toString().trim()));
                    break;
                case 11:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    vo.setFecha_solicitud(Long.parseLong(lista.get(i).toString().trim()));
                    break;
                case 12:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    vo.setFecha_inicio_cuota(Long.parseLong(lista.get(i).toString().trim()));
                    break;
                case 13:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    vo.setFecha_termino_cuota(Long.parseLong(lista.get(i).toString().trim()));
                    break;
                case 14:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    vo.setMonto_subsidio_cesantia(Long.parseLong(lista.get(i).toString().trim()));
                    break;
                case 15:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    vo.setMod_pago(Integer.parseInt(lista.get(i).toString().trim()));
                    break;
                case 16:
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    vo.setSerie_documento(Helper.paddingString(lista.get(i).toString().trim(), 10, ' ', false));
                    break;
                case 17:
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    vo.setNumero_documento(Helper.paddingString(lista.get(i).toString().trim(), 15, ' ', false));
                    break;
                case 18:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    vo.setMonto_documento(Long.parseLong(lista.get(i).toString().trim()));
                    break;

                case 19:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    vo.setFecha_emision_documento(Long.parseLong(lista.get(i).toString().trim()));
                    break;
                case 20:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    insertaError41(id, lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(7).toString().trim(), lista.get(i).toString().trim(), "", i);
                    vo.setCodigo_banco(Integer.parseInt(lista.get(i).toString().trim()));
                    break;
                default:
 //                   logger.debug("opcion no encontrada");
                    break;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return vo;
    }

    public static LinCesantia042VO llenarLinCesantia042VO(int id, List lista, String periodo) {
        LinCesantia042VO vo = new LinCesantia042VO();
        periodo = periodo.trim();
        String aux = "0";
        
        for (int i = 0; i < lista.size(); i++) {
            switch (i) {
            case 0:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                if (lista.get(i).equals(periodo)) {
                    vo.setMes_if(Long.parseLong(lista.get(i).toString().trim()));
                } else {
                    vo.setMes_if(Long.parseLong(periodo));
                    insertaError42(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), periodo, i);
                }
                break;
            case 1:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setCodigo_entidad(Integer.parseInt(lista.get(i).toString().trim()));
                insertaError42(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 2:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setCodigo_archivo(Integer.parseInt(lista.get(i).toString().trim()));
                insertaError42(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "42", i);
                break;
            case 3:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setTipo_reintegro(Integer.parseInt(lista.get(i).toString().trim()));
                insertaError42(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 4:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setRut_beneficiario(Long.parseLong(lista.get(i).toString().trim()));
                insertaError42(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 5:
                vo.setDv_beneficiario(lista.get(i).toString().trim());
                insertaError42(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 6:
                vo.setNombre_beneficiario(lista.get(i).toString().trim());
                insertaError42(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 8:
                String comunaTmp = lista.get(i).toString().trim();
                if ("".equals(comunaTmp)) {
                    comunaTmp = "0";
                }
                vo.setComuna(Integer.parseInt(comunaTmp));
                insertaError42(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 9:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setMonto_sub_cesantia_reintegrado(Long.parseLong(lista.get(i).toString().trim()));
                insertaError42(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 10:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setFecha_inicio_reintegro(Long.parseLong(lista.get(i).toString().trim()));
                insertaError42(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 11:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setFecha_termino_reintegro(Long.parseLong(lista.get(i).toString().trim()));
                insertaError42(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            default:
  //              logger.debug("opcion no encontrada");
                break;
            }
        }
        return vo;
    }

    public static LinCesantia043VO llenarLinCesantia043VO(int id, List lista, String periodo) {
        LinCesantia043VO vo = new LinCesantia043VO();

        for (int i = 0; i < lista.size(); i++) {
            switch (i) {
            case 0:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                if (lista.get(i).equals(periodo)) {
                    vo.setMes_if(Long.parseLong(lista.get(i).toString().trim()));
                } else {
                    vo.setMes_if(Long.parseLong(periodo));
                    insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), periodo, i);
                }
                break;
            case 1:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setCodigo_entidad(Integer.parseInt(lista.get(i).toString().trim()));
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 2:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setCodigo_archivo(Integer.parseInt(lista.get(i).toString().trim()));
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "43", i);
                break;
            case 3:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setMes_emision(Long.parseLong(lista.get(i).toString().trim()));
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 4:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setRut_beneficiario(Long.parseLong(lista.get(i).toString().trim()));
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 5:
                vo.setDv_beneficiario(lista.get(i).toString().trim());
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 6:
                vo.setNombre_beneficiario(lista.get(i).toString().trim());
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 8:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setTipo_egreso(Integer.parseInt(lista.get(i).toString().trim()));
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 9:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setMod_pago(Integer.parseInt(lista.get(i).toString().trim()));
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 10:
                vo.setSerie_documento(lista.get(i).toString().trim());
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 11:
                vo.setNumero_documento(lista.get(i).toString().trim());
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 12:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setMonto_subsidio_cesantia(Long.parseLong(lista.get(i).toString().trim()));
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 13:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setMonto_documento(Long.parseLong(lista.get(i).toString().trim()));
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 14:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setFecha_emision_documento(Long.parseLong(lista.get(i).toString().trim()));
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 15:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setCodigo_banco(Integer.parseInt(lista.get(i).toString().trim()));
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 16:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
//                String fechaCambioEstado = (String) lista.get(i);
//                logger.debug("fecha CambioEstado: " + fechaCambioEstado);
                vo.setFecha_cambio_estado_documento(Long.parseLong(lista.get(i).toString().trim()));
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 17:
                vo.setNumero_cartola(lista.get(i).toString().trim());
                insertaError43(id, lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(6).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            default:
 //               logger.debug("opcion no encontrada");
                break;
            }
        }
        return vo;
    }

    public static LinCesantia044VO llenarLinCesantia044VO(int id, List lista, String periodo) {
        LinCesantia044VO vo = new LinCesantia044VO();

        for (int i = 0; i < lista.size(); i++) {
            switch (i) {
            case 0:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                if (lista.get(i).equals(periodo)) {
                    vo.setMes_if(Long.parseLong(lista.get(i).toString().trim()));
                } else {
                    vo.setMes_if(Long.parseLong(periodo));
                    insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), periodo, i);
                }
                break;
            case 1:
            	if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setCod_ent(Integer.parseInt(lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 2:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setCod_arc(Integer.parseInt(lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "44", i);
                break;
            case 3:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setRut_ben(Long.parseLong(lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 4:
                vo.setDv_ben(lista.get(i).toString().trim());
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 5:
                vo.setNom_ben(lista.get(i).toString().trim());
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 6:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setMes_doc_inf(Long.parseLong(lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 8:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setMod_pag_doc_ori(Integer.parseInt(lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 9:
                vo.setSer_doc_ori(lista.get(i).toString().trim());
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 10:
                vo.setNum_doc_ori(lista.get(i).toString().trim());
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 11:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setMto_sub_ori(Long.parseLong(lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 12:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setMto_doc_ori(Long.parseLong(lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 13:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setFec_emi_doc_ori(Long.parseLong(lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 14:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setCod_bco_doc_ori(Integer.parseInt(lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 15:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setMod_pag_nvo(Integer.parseInt(lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 16:
                vo.setSer_doc_nvo(lista.get(i).toString().trim());
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 17:
                vo.setNum_doc_nvo(lista.get(i).toString().trim());
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 18:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setMto_sub_nvo(Long.parseLong(lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 19:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setMto_doc_nvo(Long.parseLong(lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 20:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setFec_emi_doc_nvo(Long.parseLong(lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            case 21:
                if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                vo.setCod_bco_doc_nvo(Integer.parseInt((String) lista.get(i).toString().trim()));
                insertaError44(id, lista.get(3).toString().trim(), lista.get(4).toString().trim(), lista.get(5).toString().trim(), lista.get(i).toString().trim(), "", i);
                break;
            default:
 //               logger.debug("opcion no encontrada");
                break;
            }
        }
        return vo;
    }

    	public static LinAfcAFF01VO llenarLinAfcAFF01VO(int id, List lista, String periodo, String tipoArchivo) {
    		LinAfcAFF01VO vo = new LinAfcAFF01VO();
        try {
            for (int i = 0; i < lista.size(); i++) {
                switch (i) {
                case 0:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setRut_Afiliado(Long.parseLong(lista.get(i).toString().trim()));
                  /*insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,""); --correcto */
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim()); 
                    break;
                case 1:
                    vo.setDigito_Verificador_Afiliado(lista.get(i).toString().trim());
                   /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 2:
                    vo.setNombres_Afiliado(lista.get(i).toString().trim());
                   /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,""); */
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 3:
                    vo.setApellido_Paterno_Afiliado(lista.get(i).toString().trim());
                   /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 4:
                    vo.setApellido_Materno_Afiliado(lista.get(i).toString().trim());
                  /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 5:
                    vo.setTramo(lista.get(i).toString().trim());
                  /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 6:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i,"0");}
                    vo.setRut_Causante(Long.parseLong(lista.get(i).toString().trim()));
                  /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 7:
                    vo.setDigito_Verificador_Causante(lista.get(i).toString().trim());
                  /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 8:
                    vo.setNombres_Causantes(lista.get(i).toString().trim());
                  /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 10:
                    vo.setApellido_Paterno_Causante(lista.get(i).toString().trim());
                  /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 11:
                    vo.setApellido_Materno_Causante(lista.get(i).toString().trim());
                  /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 12:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setFecha_de_Nacimeinto(Long.parseLong(lista.get(i).toString().trim()));
                  /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"",lista.get(13).toString().trim(),lista.get(5).toString().trim());
                    break;
                case 13:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setCodigo_Tipo_Causa(Integer.parseInt(lista.get(i).toString().trim()));
                  /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 14:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setTipo_Causante(lista.get(i).toString().trim());
                  /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 15:
                    vo.setTipo_Asignacion_familiar(lista.get(i).toString().trim());
                  /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 16:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setNumero_Solicitud(Long.parseLong(lista.get(i).toString().trim()));
                  /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 17:
                    vo.setTipo_Solicitud(lista.get(i).toString().trim());
                 /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 18:
                    vo.setSexo(lista.get(i).toString().trim());
                 /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 19:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setMonto(Long.parseLong(lista.get(i).toString().trim()));
                 /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,lista.get(20).toString().trim()); */
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,lista.get(20).toString().trim(),"",lista.get(5).toString().trim());
                    break;
                case 20:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setRenta(Long.parseLong(lista.get(i).toString().trim()));
                 /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,""); */
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 21:
                    String comunaTmp = lista.get(i).toString().trim();
                    if (comunaTmp.equals("")) {
                        comunaTmp = "0";
                    }
                    vo.setComuna(Integer.parseInt(comunaTmp));
                 /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim()); 
                    break;
                case 22:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setRegion(Integer.parseInt(lista.get(i).toString().trim()));
                 /*  insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                case 23:
                    if("".equalsIgnoreCase(lista.get(i).toString().trim())){lista.set(i, "0");}
                    vo.setTipo_Beneficiario(Integer.parseInt(lista.get(i).toString().trim()));
                 /* insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"");*/
                    insertaErrorAFC(periodo, tipoArchivo, id, lista.get(0).toString().trim(), lista.get(1).toString().trim(), lista.get(2).toString().trim(), lista.get(i).toString().trim(), "", i,"","",lista.get(5).toString().trim());
                    break;
                default:
                    break;
                }
            }
        } catch (Exception e) {
            return vo;
        }
        return vo;
    }

    /*private static void insertaErrorAFC(String periodo, String tipoArchivo, int id, String rut, String dv, String nom, String valor, String comparacion, int opcion, String renta) { -->correcto */
    	private static void insertaErrorAFC(String periodo, String tipoArchivo, int id, String rut, String dv, String nom, String valor, String comparacion, int opcion, String renta, String codCausante,String tramo) {	
        RespuestaVO resp = new RespuestaVO();
        LinAfcAFF01EVO voErr = new LinAfcAFF01EVO();
        int codError = 0;
        boolean resultado = false;
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        CodigoErrorVO[] codigosError = listaParam.getListCodigoErrorAFC();
        int largo = 0;
        long x = 0;
        boolean cadena = false;
        boolean valida = true;
        String claveDominio = "";
        valor = valor.trim();
        switch (opcion) {

        case 0:// Rut Afiliado
            codError = 1;
            largo = 10;
            break;

        case 1:// DV Afiliado
            cadena = true;
            codError = 2;
            largo = 1;
            x = 0;
            try {
                x = Integer.parseInt(valor);
            } catch (NumberFormatException nfe) {
                if (!"k".equalsIgnoreCase(valor)) {
                    resultado = true;
                    break;
                }
            }
            if (x < 0 || x > 9) {
                resultado = true;
            }
            if(x == 0){
                valor = "1";
            }
            break;

        case 2:// Nombre Afiliado
            cadena = true;
           codError = 3;
            largo = 20;
            break;

        case 3:// Apellido Paterno Afiliado
            cadena = true;
            codError = 4;
            largo = 20;
            break;

        case 4:// Apellido Materno Afiliado
            cadena = true;
           codError = 5;
            largo = 20;
            break;

        case 5:// Tramo
            cadena = true;
            codError = 6;
            largo = 1;
            break;

        case 6:// Rut Causante
            codError = 7;
            largo = 10;
            break;

        case 7:// DV Causante
            cadena = true;
           codError = 8;
            largo = 1;
            x = 0;
            try {
                x = Integer.parseInt(valor);
            } catch (NumberFormatException nfe) {
                if (!"k".equalsIgnoreCase(valor)) {
                    resultado = true;
                    break;
                }
            }
            if (x < 0 || x > 9) {
                resultado = true;
            }
            if(x == 0){
                valor = "1";
            }
            break;

        case 8:// Nombre Causante
            cadena = true;
            codError = 9;
            largo = 20;
            break;

        case 10:// Apellido Paterno Causante
            cadena = true;
            codError = 10;
            largo = 20;
            break;

        case 11:// Apellido Materno Causante
            cadena = true;
            codError = 11;
            largo = 20;
            break;

        case 12:// Fecha Nacimiento
            codError = 12;
            largo = 8;
            Calendar c = Calendar.getInstance();
            if(valor.length() > 4){
                int anioNac = Integer.parseInt(valor.substring(0, 4));
                int anioActual = c.get(Calendar.YEAR);
                long dif = anioActual - anioNac;
                if("6".equalsIgnoreCase(codCausante)){
                    if(dif > 24){
                        codError = 24;
                        resultado = true;
                    }else if(dif <= 18) {
                        codError = 24;
                        resultado = true;
                    }
                }
                if("4".equalsIgnoreCase(codCausante)){
                    if(dif > 18){
                        codError = 24;
                        resultado = true;
                    }
                }
            }
            break;

        case 13:// Código Tipo Causante
            codError = 13;
            largo = 2;
            break;

        case 14:// Tipo Causante
            cadena = true;
            codError = 14;
            largo = 20;
            break;

        case 15:// Tipo Asignación Familiar
            cadena = true;
            codError = 15;
            largo = 1;
            break;

        case 16:// Número Solicitud
            codError = 16;
            largo = 10;
            break;

        case 17:// Tipo Solicitud
            cadena = true;
            codError = 17;
            largo = 2;
            break;

        case 18:// Sexo
            cadena = true;
            codError = 18;
            largo = 1;
            break;

        case 19:// Monto
            codError = 19;
            largo = 10;
            try {
                x = Long.parseLong(valor);
            } catch (NumberFormatException nfe) {
                resultado = true;
                break;
            }
            //si la renta y el monto son cero  
           /* if(valor.equalsIgnoreCase("0") &&  renta.equalsIgnoreCase("0")){ -->correcto */
              if(valor.equalsIgnoreCase("0") &&  !tramo.equalsIgnoreCase("D")){
                resultado = true;
                break;
            }
            
            if(x == 0){
                valor = "1";
            }
            break;

        case 20:// Renta
            codError = 20;
            largo = 13;
            try {
                x = Long.parseLong(valor);
            } catch (NumberFormatException nfe) {
                resultado = true;
                break;
            }

            if (x < 0) {
                resultado = true;
            }
            break;

        case 21:// Comuna
            codError = 21;
            largo = 5;
            claveDominio = "CODCOMUNA";
            valor = Helper.paddingString(valor.trim(), 5, '0', true);
            break;

        case 22:// Región
            codError = 22;
            largo = 2;
            claveDominio = "CODREGION";
            valor = Helper.paddingString(valor.trim(), 2, '0', true);
            break;

        case 23:// Tipo Beneficiario
            codError = 23;
            largo = 2;
            break;

        default:
            break;
        }
        // Validacion de datos vacios y formatos.
        if (valida) {
            if (!cadena) {
                if (validaNumerico(valor)) {
                    resultado = true;
                }
            }
            if (validaCero(valor)) {
                resultado = true;
            } else if (validaLargo(valor, largo)) {
                resultado = true;
            } else if (comparaValor(valor, comparacion, claveDominio)) {
                resultado = true;
            }
        }
        // Ingreso a tabla de errores
        if (resultado) {
            voErr.setID_Registro_AFC(id);
            if (rut.length() > 9) {
                voErr.setRut_beneficiario(rut.trim().substring(0, 9));
            } else {
                voErr.setRut_beneficiario(rut.trim());
            }
            if (dv.length() > 1) {
                voErr.setDv_beneficiario(dv.trim().substring(0, 1));
            } else {
                voErr.setDv_beneficiario(dv.trim());
            }
            if (dv.length() > 50) {
                voErr.setNombre_beneficiario(nom.trim().substring(0, 30));
            } else {
                voErr.setNombre_beneficiario(nom.trim());
            }
            voErr.setCodigo_error(codError);
            for (int i = 0; i < codigosError.length; i++) {
                if (codigosError[i].getId_codigo_error() == codError) {
                    voErr.setDescripcion_error(codigosError[i].getDesc_codigo_error());
                }
            }
            voErr.setPeriodo(periodo);
            voErr.setTipArch(tipoArchivo);
            try {
                resp = GenerarReportesAFCDAO.insertarLineaAFCErr(voErr);
            } catch (Exception e) {
                resp.setMsgRespuesta("Error en SQL");
            }
 //           logger.debug("respuesta " + resp.getCodRespuesta());
        }

    }

    private static void insertaError41(int id, String rut, String dv, String nom, String valor, String comparacion, int opcion) {

        RespuestaVO resp = new RespuestaVO();
        LinCesantiaError041VO voErr = new LinCesantiaError041VO();
        int codError = 0;
        boolean resultado = false;
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        CodigoErrorVO[] codigosError = listaParam.getListCodigoError();
        int largo = 0;
        long x = 0;
        boolean cadena = false;
        boolean valida = true;
        String claveDominio = "";
        switch (opcion) {
        case 0:// mes informado
            codError = 1;
            largo = 6;
            break;
        case 1:// codigo entidad
            codError = 2;
            largo = 5;
            claveDominio = "CODENTIDAD";
            break;
        case 2:// codigo archivo
            codError = 3;
            largo = 2;
            break;
        case 3:// mes emision
            codError = 4;
            largo = 6;
            break;
        case 4:// tipo egreso
            codError = 5;
            largo = 1;
            claveDominio = "TIPEGRESO";
            valor = Helper.paddingString(valor.trim(), 2, '0', true);
            break;
        case 5:// rut beneficiario
            codError = 6;
            largo = 8;
            break;
        case 6:// dv beneficiario
            valida = false;
            codError = 7;
            largo = 1;
            try {
                x = Integer.parseInt(valor);
            } catch (NumberFormatException nfe) {
                if (!"k".equalsIgnoreCase(valor)) {
                    resultado = true;
                    break;
                }
            }
            if (x < 0 || x > 9) {
                resultado = true;
            }
            break;
        case 7:// nombre beneficiario
            codError = 8;
            largo = 50;
            cadena = true;
            break;
        case 9:// comuna
            codError = 9;
            largo = 5;
            claveDominio = "CODCOMUNA";
            valor = Helper.paddingString(valor.trim(), 5, '0', true);
            break;
        case 10:// fecha cesantia
            codError = 10;
            largo = 8;
            break;
        case 11:// fecha solicitud
            codError = 11;
            largo = 8;
            break;
        case 12:// fecha ini cuota
            codError = 12;
            largo = 8;
            break;
        case 13:// fecha fin cuota
            codError = 13;
            largo = 8;
            break;
        case 14:// monto subsidio cesantia
//            valida = false;
            codError = 14;
            try {
                x = Long.parseLong(valor);
            } catch (NumberFormatException nfe) {
                resultado = true;
                break;
            }

            if (x < 0) {
                resultado = true;
            }
            break;
        case 15:// modo pago
            codError = 15;
            largo = 1;
            claveDominio = "MODPAGO";
            valor = Helper.paddingString(valor.trim(), 2, '0', true);
            break;
        case 16:// serie DOC
            codError = 16;
            largo = 10;
            cadena = true;
            break;
        case 17:// numero doc
            codError = 17;
            largo = 15;
            break;
        case 18:// monto doc
//            valida = false;
            codError = 18;
            try {
                x = Long.parseLong(valor);
            } catch (NumberFormatException nfe) {
                resultado = true;
                break;
            }

            if (x < 0) {
                resultado = true;
            }
            break;
        case 19:// fec emicion doc
            codError = 19;
            largo = 8;
            break;
        case 20:// cod banco
            codError = 20;
            largo = 5;
            claveDominio = "CODBANCO";
            break;
        default:
            break;
        }

        if (valida) {
            if (!cadena) {
                if (validaNumerico(valor)) {
                    resultado = true;
                }
            }
            if (validaCero(valor)) {
                resultado = true;
            } else if (validaLargo(valor, largo)) {
                resultado = true;
            } else if (comparaValor(valor, comparacion, claveDominio)) {
                resultado = true;
            }
        }

        if (resultado) {
            voErr.setId_sc041(id);
            try {
                x = Long.parseLong(rut.trim());
            } catch (NumberFormatException nfe) {
                rut = "0";
            }

            if (rut.length() > 9) {
                voErr.setRut_beneficiario(Long.parseLong(rut.trim().substring(0, 9)));
            } else {
                if(rut.trim().equalsIgnoreCase("")){
                    rut = "0";
                }
                voErr.setRut_beneficiario(Long.parseLong(rut.trim()));
            }
            if (dv.length() > 1) {
                voErr.setDv_beneficiario(dv.trim().substring(0, 1));
            } else {
                voErr.setDv_beneficiario(dv.trim());
            }
            if (nom.length() > 50) {
                voErr.setNombre_beneficiario(nom.trim().substring(0, 30));
            } else {
                voErr.setNombre_beneficiario(nom.trim());
            }
            voErr.setCodigo_error(codError);
            for (int i = 0; i < codigosError.length; i++) {
                if (codigosError[i].getId_codigo_error() == codError) {
                    voErr.setDescripcion_error(codigosError[i].getDesc_codigo_error());
                    break;
                }
            }
            try {
                resp = GenerarReportesCesantiaDAO.insertarLineaCesantia041Err(voErr);
            } catch (Exception e) {
                resp.setMsgRespuesta("Error en SQL");
            }
 //           logger.debug("respuesta " + resp.getCodRespuesta());
        }
    }

    private static void insertaError42(int id, String rut, String dv, String nom, String valor, String comparacion, int opcion) {

        RespuestaVO resp = new RespuestaVO();
        LinCesantiaError042VO voErr = new LinCesantiaError042VO();
        int codError = 0;
        boolean resultado = false;
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        CodigoErrorVO[] codigosError = listaParam.getListCodigoError();
        int largo = 0;
        long x = 0;
        boolean cadena = false;
        boolean valida = true;
        String claveDominio = "";
        switch (opcion) {
        case 0:// mes informado
            codError = 21;
            largo = 6;
            break;
        case 1:// codigo entidad
            codError = 22;
            largo = 5;
            claveDominio = "CODENTIDAD";
            break;
        case 2:// codigo archivo
            codError = 23;
            largo = 2;
            break;
        case 3:// tipo reintegro
            codError = 24;
            largo = 2;
            claveDominio = "TIPREINTEGRO";
            valor = Helper.paddingString(valor.trim(), 2, '0', true);
            break;
        case 4:// rut beneficiario
            codError = 25;
            largo = 8;
            break;
        case 5:// dv beneficiario
            valida = false;
            codError = 26;
            largo = 1;
            try {
                x = Integer.parseInt(valor);
            } catch (NumberFormatException nfe) {
                if (!"k".equalsIgnoreCase(valor)) {
                    resultado = true;
                    break;
                }
            }
            if (x < 0 || x > 9) {
                resultado = true;
            }
            break;
        case 6:// nombre beneficiario
            codError = 27;
            largo = 50;
            cadena = true;
            break;
        case 8:// comuna
            codError = 28;
            largo = 5;
            claveDominio = "CODCOMUNA";
            valor = Helper.paddingString(valor.trim(), 5, '0', true);
            break;
        case 9:// monto reintegro
//            valida = false;
            codError = 29;
            try {
                x = Long.parseLong(valor);
            } catch (NumberFormatException nfe) {
                resultado = true;
                break;
            }

            if (x < 0) {
                resultado = true;
            }
            break;
        case 10:// fecha ini reintegro
            codError = 30;
            largo = 8;
            break;
        case 11:// fecha ini reintegro
            codError = 31;
            largo = 8;
            break;
        default:
            break;
        }

        if (valida) {
            if (!cadena) {
                if (validaNumerico(valor)) {
                    resultado = true;
                }
            }
            if (validaCero(valor)) {
                resultado = true;
            } else if (validaLargo(valor, largo)) {
                resultado = true;
            } else if (comparaValor(valor, comparacion, claveDominio)) {
                resultado = true;
            }
        }

        if (resultado) {
            voErr.setId_sc042(id);
            try {
                x = Long.parseLong(rut.trim());
            } catch (NumberFormatException nfe) {
                rut = "0";
            }

            if (rut.length() > 9) {
                voErr.setRut_beneficiario(Long.parseLong(rut.trim().substring(0, 9)));
            } else {
                if(rut.trim().equalsIgnoreCase("")){
                    rut = "0";
                }
                voErr.setRut_beneficiario(Long.parseLong(rut.trim()));
            }
            if (dv.length() > 1) {
                voErr.setDv_beneficiario(dv.trim().substring(0, 1));
            } else {
                voErr.setDv_beneficiario(dv.trim());
            }
            if (nom.length() > 50) {
                voErr.setNombre_beneficiario(nom.trim().substring(0, 30));
            } else {
                voErr.setNombre_beneficiario(nom.trim());
            }
            voErr.setCodigo_error(codError);
            for (int i = 0; i < codigosError.length; i++) {
                if (codigosError[i].getId_codigo_error() == codError) {
                    voErr.setDescripcion_error(codigosError[i].getDesc_codigo_error());
                    break;
                }
            }
            try {
                resp = GenerarReportesCesantiaDAO.insertarLineaCesantia042Err(voErr);
            } catch (Exception e) {
                resp.setMsgRespuesta("Error en SQL");
            }
  //          logger.debug("respuesta " + resp.getCodRespuesta());
        }

    }

    private static void insertaError43(int id, String rut, String dv, String nom, String valor, String comparacion, int opcion) {

        RespuestaVO resp = new RespuestaVO();
        LinCesantiaError043VO voErr = new LinCesantiaError043VO();
        int codError = 0;
        boolean resultado = false;
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        CodigoErrorVO[] codigosError = listaParam.getListCodigoError();
        int largo = 0;
        long x = 0;
        boolean cadena = false;
        boolean valida = true;
        String claveDominio = "";
        switch (opcion) {
        case 0:// mes informado
            codError = 32;
            largo = 6;
            break;
        case 1:// codigo entidad
            codError = 33;
            largo = 5;
            claveDominio = "CODENTIDAD";
            break;
        case 2:// codigo archivo
            codError = 34;
            largo = 2;
            break;
        case 3:// mes emision
            codError = 35;
            largo = 8;
            break;
        case 4:// rut beneficiario
            codError = 36;
            largo = 8;
            break;
        case 5:// dv beneficiario
            valida = false;
            codError = 37;
            largo = 1;
            try {
                x = Integer.parseInt(valor);
            } catch (NumberFormatException nfe) {
                if (!"k".equalsIgnoreCase(valor)) {
                    resultado = true;
                    break;
                }
            }
            if (x < 0 || x > 9) {
                resultado = true;
            }
            break;
        case 6:// nombre beneficiario
            codError = 38;
            largo = 50;
            cadena = true;
            break;
        case 8:// tipo egreso
            codError = 39;
            largo = 2;
            claveDominio = "TIPEGRESO";
            valor = Helper.paddingString(valor.trim(), 2, '0', true);
            break;
        case 9:// modo pago
            codError = 40;
            largo = 1;
            claveDominio = "MODPAGO";
            valor = Helper.paddingString(valor.trim(), 2, '0', true);
            break;
        case 10:// serie doc
            codError = 41;
            largo = 10;
            cadena = true;
            break;
        case 11:// num doc
            codError = 42;
            largo = 15;
            break;
        case 12:// monto subsidio
//            valida = false;
            codError = 43;
            try {
                x = Long.parseLong(valor);
            } catch (NumberFormatException nfe) {
                resultado = true;
                break;
            }

            if (x < 0) {
                resultado = true;
            }
            break;
        case 13:// monto documento
//          valida = false;
            codError = 44;
            try {
                x = Long.parseLong(valor);
            } catch (NumberFormatException nfe) {
                resultado = true;
                break;
            }

            if (x < 0) {
                resultado = true;
            }
            break;
        case 14:// fecha emision doc
            codError = 45;
            largo = 8;
            break;
        case 15:// cod banco
            codError = 46;
            largo = 5;
            claveDominio = "CODBANCO";
            break;
        case 16:// fecha cambio estado doc
            codError = 47;
            largo = 8;
            break;
        case 17:// numero cartola
            codError = 48;
            largo = 8;
            break;
        default:

            break;
        }

        if (valida) {
            if (!cadena) {
                if (validaNumerico(valor)) {
                    resultado = true;
                }
            }
            if (validaCero(valor)) {
                resultado = true;
                if(codError == 48){resultado = false;}
            } else if (validaLargo(valor, largo)) {
                resultado = true;
            } else if (comparaValor(valor, comparacion, claveDominio)) {
                resultado = true;
            }
        }

        if (resultado) {
            voErr.setId_sc043(id);
            
            try {
                x = Long.parseLong(rut.trim());
            } catch (NumberFormatException nfe) {
                rut = "0";
            }

            if (rut.length() > 9) {
                voErr.setRut_beneficiario(Long.parseLong(rut.trim().substring(0, 9)));
            } else {
                if(rut.trim().equalsIgnoreCase("")){
                    rut = "0";
                }
                voErr.setRut_beneficiario(Long.parseLong(rut.trim()));
            }
            if (dv.length() > 1) {
                voErr.setDv_beneficiario(dv.trim().substring(0, 1));
            } else {
                voErr.setDv_beneficiario(dv.trim());
            }
            if (nom.length() > 50) {
                voErr.setNombre_beneficiario(nom.trim().substring(0, 30));
            } else {
                voErr.setNombre_beneficiario(nom.trim());
            }
            voErr.setCodigo_error(codError);
            for (int i = 0; i < codigosError.length; i++) {
                if (codigosError[i].getId_codigo_error() == codError) {
                    voErr.setDescripcion_error(codigosError[i].getDesc_codigo_error());
                    break;
                }
            }
            try {
                resp = GenerarReportesCesantiaDAO.insertarLineaCesantia043Err(voErr);
            } catch (Exception e) {
                resp.setMsgRespuesta("Error en SQL");
            }
  //          logger.debug("respuesta " + resp.getCodRespuesta());
        }

    }

    private static void insertaError44(int id, String rut, String dv, String nom, String valor, String comparacion, int opcion) {

        RespuestaVO resp = new RespuestaVO();
        LinCesantiaError044VO voErr = new LinCesantiaError044VO();
        int codError = 0;
        boolean resultado = false;
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        CodigoErrorVO[] codigosError = listaParam.getListCodigoError();
        int largo = 0;
        long x = 0;
        boolean cadena = false;
        boolean valida = true;
        String claveDominio = "";
        switch (opcion) {
        case 0:// mes informado
            codError = 50;
            largo = 6;
            break;
        case 1:// cod entidad
            codError = 51;
            largo = 5;
            claveDominio = "CODENTIDAD";
            break;
        case 2:// cod archivo
            codError = 52;
            largo = 2;
            break;
        case 3:// rut beneficiario
            codError = 53;
            largo = 8;
            break;
        case 4:// dv beneficiario
            valida = false;
            codError = 54;
            largo = 1;
            try {
                x = Integer.parseInt(valor);
            } catch (NumberFormatException nfe) {
                if (!"k".equalsIgnoreCase(valor)) {
                    resultado = true;
                    break;
                }
            }
            if (x < 0 || x > 9) {
                resultado = true;
            }
            break;
        case 5:// nombre beneficiario
            codError = 55;
            largo = 50;
            cadena = true;
            break;
        case 6:// fecha doc
            codError = 56;
            largo = 8;
            break;
        case 8:// modo pago
            codError = 57;
            largo = 1;
            claveDominio = "MODPAGO";
            valor = Helper.paddingString(valor.trim(), 2, '0', true);
            break;
        case 9:// serie doc
            codError = 58;
            largo = 10;
            cadena = true;
            break;
        case 10:// num doc
            codError = 59;
            largo = 15;
            break;
        case 11:// monto subsidio
            //valida = false;
            codError = 60;
            try {
                x = Long.parseLong(valor);
            } catch (NumberFormatException nfe) {
                resultado = true;
                break;
            }

            if (x < 0) {
                resultado = true;
            }
            break;
        case 12:// monto doc
            //valida = false;
            codError = 61;
            try {
                x = Long.parseLong(valor);
            } catch (NumberFormatException nfe) {
                resultado = true;
                break;
            }

            if (x < 0) {
                resultado = true;
            }
            break;
        case 13:// fecha emision
            codError = 62;
            largo = 8;
            break;
        case 14:// cod banco
            codError = 63;
            largo = 5;
            claveDominio = "CODBANCO";
            break;
        case 15:// mod pago
            codError = 64;
            largo = 1;
            claveDominio = "MODPAGO";
            valor = Helper.paddingString(valor.trim(), 2, '0', true);
            break;
        case 16:// serie doc
            codError = 65;
            largo = 10;
            cadena = true;
            break;
        case 17:// num doc
            codError = 66;
            largo = 15;
            break;
        case 18:// monto subsidio
            //valida = false;
            codError = 67;
            try {
                x = Long.parseLong(valor);
            } catch (NumberFormatException nfe) {
                resultado = true;
                break;
            }

            if (x < 0) {
                resultado = true;
            }
            break;
        case 19:// monto doc
            //valida = false;
            codError = 68;
            try {
                x = Long.parseLong(valor);
            } catch (NumberFormatException nfe) {
                resultado = true;
                break;
            }

            if (x < 0) {
                resultado = true;
            }
            break;
        case 20:// fecha emision
            codError = 69;
            largo = 8;
            break;
        case 21:// cod banco
            codError = 70;
            largo = 5;
            claveDominio = "CODBANCO";
            break;
        default:
            valida = false;
            resultado = false;
            break;
        }

        if (valida) {
            if (!cadena) {
                if (validaNumerico(valor)) {
                    resultado = true;
                }
            }
            if (validaCero(valor)) {
                resultado = true;
            } else if (validaLargo(valor, largo)) {
                resultado = true;
            } else if (comparaValor(valor, comparacion, claveDominio)) {
                resultado = true;
            }
        }

        if (resultado) {
            voErr.setId_sc044(id);
            try {
                x = Long.parseLong(rut.trim());
            } catch (NumberFormatException nfe) {
                rut = "0";
            }

            if (rut.length() > 9) {
                voErr.setRut_beneficiario(Long.parseLong(rut.trim().substring(0, 9)));
            } else {
                if(rut.trim().equalsIgnoreCase("")){
                    rut = "0";
                }
                voErr.setRut_beneficiario(Long.parseLong(rut.trim()));
            }
            if (dv.length() > 1) {
                voErr.setDv_beneficiario(dv.trim().substring(0, 1));
            } else {
                voErr.setDv_beneficiario(dv.trim());
            }
            if (nom.length() > 50) {
                voErr.setNombre_beneficiario(nom.trim().substring(0, 30));
            } else {
                voErr.setNombre_beneficiario(nom.trim());
            }
            voErr.setCodigo_error(codError);
            for (int i = 0; i < codigosError.length; i++) {
                if (codigosError[i].getId_codigo_error() == codError) {
                    voErr.setDescripcion_error(codigosError[i].getDesc_codigo_error());
                    break;
                }
            }
            try {
                resp = GenerarReportesCesantiaDAO.insertarLineaCesantia044Err(voErr);
            } catch (Exception e) {
                resp.setMsgRespuesta("Error en SQL");
            }
  //          logger.debug("respuesta " + resp.getCodRespuesta());
        }

    }

    private static boolean comparaValor(String valor, String comparacion, String claveDominio) {
        if (!"".equalsIgnoreCase(claveDominio)) {
            ListadoParametros listaParam = ListadoParametros.getInstancia();
            DominioVO[] dominio = listaParam.getListDominio();
            for (int i = 0; i < dominio.length; i++) {
                if ((dominio[i].getId_dominio().equalsIgnoreCase(valor)) && (dominio[i].getClave_dominio().equalsIgnoreCase(claveDominio))) {
                    return false;
                }
            }
        } else {
            if (!"".equalsIgnoreCase(comparacion)) {
                if (!comparacion.equalsIgnoreCase(valor)) {
                    return true;
                }else{
                    return false;    
                }
                
            }else{
                return false;   	
            }
        }

        return true;
    }

    private static boolean validaLargo(String valor, int i) {

        return false;
    }

    private static boolean validaCero(String valor) {
        if ("0".equalsIgnoreCase(valor) || "".equalsIgnoreCase(valor)) {
            return true;
        }
        return false;
    }

    private static boolean validaNumerico(String valor) {
        try {
            long x = Long.parseLong(valor);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

}
