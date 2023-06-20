package cl.laaraucana.sms.assembly;

import cl.laaraucana.sms.ibatis.dao.EstadoSMSDAO;
import cl.laaraucana.sms.ibatis.model.EstadoSMS;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestDAOEstadoSMS {

    private static final Logger logger = Logger.getLogger(TestDAOEstadoSMS.class);

    @RequestMapping(value = {"/saveEstadoSMS.test"}, method = RequestMethod.GET)
    public String saveEstadoSMS() {
        // VO
        EstadoSMS estadoSMS = new EstadoSMS();
        estadoSMS.setUsuario("ASSEMBLY");
        estadoSMS.setRut("15636381");
        estadoSMS.setDigitoValidador("15636381");
        estadoSMS.setCelular("56990842361");
        estadoSMS.setCodigoSMS("SMS-TESTCODE-001");
        // estadoSMS.setURL_KEY("URL-TESTCODE-001");
        estadoSMS.setEstado("OK");
        // DAO
        try {
            EstadoSMSDAO dao = new EstadoSMSDAO();
            dao.saveEstadoSMS(estadoSMS);
        } catch (Exception e) {
            logger.error("Error during EstadoURLDAO.saveEstadoSMS operation", e);
        }
        return null;
    }

    @RequestMapping(value = {"/updateEstadoSMS.test"}, method = RequestMethod.GET)
    public String updateEstadoSMS() {
        logger.info("method under construction");
        // VO
        EstadoSMS estadoSMS = new EstadoSMS();
        estadoSMS.setCodigoSMS("SMS-TESTCODE-001");
        // estadoSMS.setURL_KEY("URL-TESTCODE-001");
        // DAO
        try {
            EstadoSMSDAO dao = new EstadoSMSDAO();
            dao.updateEstadoSMS(estadoSMS);
        } catch (Exception e) {
            logger.error("Error during EstadoURLDAO.updateEstadoSMS operation", e);
        }
        return null;
    }

    @RequestMapping(value = {"/selectEstadoSMS.test"}, method = RequestMethod.GET)
    public String selectEstadoSMS() {
        String codeSMS = "SMS-TESTCODE-001";
        // DAO
        try {
            EstadoSMSDAO dao = new EstadoSMSDAO();
            EstadoSMS estadoSMS = dao.selectEstadoSMS(codeSMS);
            boolean result = null != estadoSMS;
            logger.info(String.format("EstadoURLDAO.selectEstadoSMS operation result: %s, codeSMS: %s", result,
                    result ? estadoSMS.getCodigoSMS() : "empty"));
        } catch (Exception e) {
            logger.error("Error during EstadoURLDAO.selectEstadoSMS operation", e);
        }
        return null;
    }
}
