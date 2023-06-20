package cl.laaraucana.sms.assembly;

import cl.laaraucana.sms.ibatis.dao.UsuarioDAO;
import cl.laaraucana.sms.ibatis.model.Usuario;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TestDAOUsuario {

    private static final Logger logger = Logger.getLogger(TestDAOUsuario.class);

    @RequestMapping(value = {"/selectUsuario.test"}, method = RequestMethod.GET)
    public String selectUsuario(@RequestParam(required = false) String userId, HttpServletRequest request, HttpServletResponse response) {
        if (null == userId)
            userId = "ASSEMBLER";
        // DAO
        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = dao.selectUsuario(new Usuario(userId, "Servicio Web"));
            boolean result = null != usuario;
            logger.info(String.format("UsuarioDAO.selectUsuario operation result: %s", result));
            if (result) {
                logger.info(String.format("USR_SISTEMA        : %s", usuario.getUsuario()));
                logger.info(String.format("PASS               : %s", usuario.getClave()));
                logger.info(String.format("DESCRIPCION_SISTEMA: %s", usuario.getDescripcion()));
                logger.info(String.format("ESTADO             : %s", usuario.getEstado()));
            }
        } catch (Exception e) {
            logger.error("Error during UsuarioDAO.selectUsuario operation", e);
        }
        return null;
    }
}
