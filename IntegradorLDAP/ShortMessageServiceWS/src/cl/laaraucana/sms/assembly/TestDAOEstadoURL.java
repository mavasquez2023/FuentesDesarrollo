package cl.laaraucana.sms.assembly;

import cl.laaraucana.sms.ibatis.dao.EstadoURLDAO;
import cl.laaraucana.sms.ibatis.model.EstadoURL;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestDAOEstadoURL {

    private static final Logger logger = Logger.getLogger(TestDAOEstadoURL.class);

    @RequestMapping(value = {"/saveEstadoURL.test"}, method = RequestMethod.GET)
    public String saveEstadoURL() {
        logger.info("method under construction");
        // VO
        EstadoURL estadoURL = new EstadoURL();
        estadoURL.setUsuario("ASSEMBLY");
        estadoURL.setCodigoURL("URL-TESTCODE-001");
        estadoURL.setClicks("1");
        estadoURL.setNavegador("Google Chrome");
        estadoURL.setSistemaOperativo("Microsoft Windows 10");
        //estadoURL.setFECHA_APERTURA("");
        //estadoURL.setHORA_APERTURA]("");
        // DAO
        try {
            EstadoURLDAO dao = new EstadoURLDAO();
            dao.saveEstadoURL(estadoURL);
        } catch (Exception e) {
            logger.error("Error during EstadoURLDAO.saveEstadoURL operation", e);
        }
        return null;
    }

    @RequestMapping(value = {"/selectEstadoURL.test"}, method = RequestMethod.GET)
    public String updateEstadoURL() {
        String codeURL = "URL-TESTCODE-001";
        // DAO
        try {
            EstadoURLDAO dao = new EstadoURLDAO();
            EstadoURL estadoURL = dao.selectEstadoURL(codeURL);
            boolean result = null != estadoURL;
            logger.info(String.format("EstadoURLDAO.selectEstadoURL operation result: %s, codeURL: %s", result,
                    result ? estadoURL.getCodigoURL() : "empty"));
        } catch (Exception e) {
            logger.error("Error during EstadoURLDAO.selectEstadoSMS operation", e);
        }
        return null;
    }
}
