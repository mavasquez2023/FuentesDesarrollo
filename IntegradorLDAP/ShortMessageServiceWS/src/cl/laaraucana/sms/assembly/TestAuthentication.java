package cl.laaraucana.sms.assembly;

import cl.laaraucana.sms.domain.exchange.MessageInput;
import cl.laaraucana.sms.ibatis.dao.UsuarioDAO;
import cl.laaraucana.sms.ibatis.model.Usuario;
import cl.laaraucana.sms.utils.Encryption;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TestAuthentication {

    private static final Logger logger = Logger.getLogger(TestAuthentication.class);

    @RequestMapping(value = {"/authentication.test"}, method = RequestMethod.GET)
    public String authentication(@RequestParam(required = false) HttpServletRequest request, HttpServletResponse response) {
        // Validation
        final String username = "ASSEMBLER";
        final String password = "ASSEMBLY";
        final String rol = "Servicio Web";
        try {
            // ASSEMBLER I3R4/NW5uQ94oS0GsdWwFA==
            // ASSEMBLY  xENhSRuBY5KyG7FEJsVY2A==
            Encryption crypto = new Encryption();

            MessageInput messageInput = new MessageInput();
            messageInput.setUsername(username);
            messageInput.setPassword(password);

            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = dao.selectUsuario(new Usuario(username, rol));
            if (null != usuario) {
                String passwordEncrypted = crypto.AESEncrypt(usuario.getClave());
                logger.info(String.format("Authentication result %s", passwordEncrypted.equals(password)));
            } else {
                logger.info("authentication failed, invalid user");
            }
        } catch (Exception e) {
            logger.error(String.format("Error processing authentication for userId %s", username), e);
        }
        return null;
    }
}
